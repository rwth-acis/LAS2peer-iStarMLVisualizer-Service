package i5.las2peer.services.iStarMLVisualizerService;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashSet;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import i5.las2peer.services.iStarMLVisualizerService.data.IStarMLEdge;
import i5.las2peer.services.iStarMLVisualizerService.data.IStarMLNode;
import i5.las2peer.services.iStarMLVisualizerService.data.StringTuple;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.NodeList;
import org.w3c.dom.Element;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

/**
 * Parses an XML file and extracts nodes and edges
 * @author Alexander
 *
 */
public class IStarMLParser 
{

	private static final String DEPENDENCYTAG = "dependency";
	private static final String AREFTAG = "aref";
	private static final String DEFAULTNAME = "";
	private static final String TYPETAG = "type";
	private static final String NAMETAG = "name";
	private static final String IDTAG = "id";
	private static final String ACTORLINKTAG = "actorLink";
	
	private Document _doc;
    private DocumentBuilder _dBuilder;
	private XPath _xPath;
	/**
	 * Constructor initializes needed objects to work on an XML
	 * @throws ParserConfigurationException
	 */
	public IStarMLParser() throws ParserConfigurationException
	{
        DocumentBuilderFactory _dbFactory = DocumentBuilderFactory.newInstance();
		_dBuilder = _dbFactory.newDocumentBuilder();
		_xPath =  XPathFactory.newInstance().newXPath();
	}
	/**
	 * Loads an XML as String to perform operations on it later
	 * @param xml XML code
	 * @throws SAXException
	 * @throws IOException
	 */
	public void loadIStarML(String xml) throws SAXException, IOException
	{
		_doc= _dBuilder.parse(new InputSource(new StringReader(xml)));
		_doc.getDocumentElement().normalize();
	}
	/**
	 * Retrieves an array of all attribute value pairs of a given XML element
	 * @param el element to take the attributes from
	 * @return array auf key value pairs
	 */
	private StringTuple[] getAttributes(Element el)
	{
		StringTuple[] st;
		
		NamedNodeMap nodeMap= el.getAttributes();
		st=new StringTuple[nodeMap.getLength()];
		for (int j = 0; j < nodeMap.getLength(); j++) 
		{
			Attr attr =(Attr) nodeMap.item(j);
			String name=attr.getName();
			String value= attr.getValue();
			st[j]=new StringTuple(name, value);
		}
		return st;
	}
	/**
	 * Gets all node type elements (means they can be represented as a graph node)
	 * @return list of all nodes
	 * @throws Exception
	 */
	public ArrayList<IStarMLNode> getNodes() throws Exception
	{
		//gets all elements which have an id tag (only nodes have (iStarML definition))
		NodeList nodeList =(NodeList) _xPath.compile("//*[@"+IDTAG+"]").evaluate(_doc, XPathConstants.NODESET);
		ArrayList<IStarMLNode> result= new ArrayList<IStarMLNode>();
		HashSet<String> ids= new HashSet<String>(); //to check for duplicates
		for(int i=0; i<nodeList.getLength();i++)
		{
			Element node=(Element)nodeList.item(i);
			StringTuple[] st=getAttributes(node);//gets all attributes of the node
			
			String elName=node.getAttribute(NAMETAG).trim();//some important attributes are stored separately for easier access
			String elID=node.getAttribute(IDTAG).trim();
			String elType=node.getAttribute(TYPETAG).trim();
			if(elID.isEmpty())
				throw new Exception("Node-Element without ID");			
			if(elName.isEmpty())			
				elName=DEFAULTNAME;
			
			if(elType.isEmpty())
			{
				elType=node.getTagName();//use tag name if no type given (and let the graph generator handle this)
			}
			
			if(!ids.add(elID))//add returns false if element already in set (prevents duplicate ids)
			{
				throw new Exception("Node-Element with ID: "+elID+" already existing");		
			}
			result.add(new IStarMLNode(elType, elID, elName, st));
			
		}
		return result;
	}
	/**
	 * Gets all edge type elements (means they connect two elements in a graph)
	 * @return list of all edges
	 * @throws Exception
	 */
	public ArrayList<IStarMLEdge> getEdges() throws Exception
	{
		ArrayList<IStarMLEdge> result= new ArrayList<IStarMLEdge>();
		//gets ActorLinks (by tag)
		NodeList nodeList =(NodeList) _xPath.compile("//"+ACTORLINKTAG).evaluate(_doc, XPathConstants.NODESET);
		for(int i=0; i<nodeList.getLength();i++)
		{
			Element node=(Element)nodeList.item(i);
			StringTuple[] st=getAttributes(node);
			
			String elName=node.getAttribute(NAMETAG).trim();
			String elTo=node.getAttribute(AREFTAG).trim();
			String elType=node.getAttribute(TYPETAG).trim();
			
			String elParentID=((Element)node.getParentNode()).getAttribute(IDTAG);
			if(elTo.isEmpty())
				throw new Exception("ActorLink has no aref attribute");			
			if(elParentID.isEmpty())
				throw new Exception("No ParentID of ActorLink with aref: "+elTo);
			if(elName.isEmpty())			
				elName=DEFAULTNAME;
			
			if(elType.isEmpty())
			{
				elType=node.getTagName();
			}
			result.add(new IStarMLEdge(elType, elParentID,elTo, elName, st));			
		}
		//Dependencies
		nodeList =(NodeList) _xPath.compile("//"+DEPENDENCYTAG+"/*").evaluate(_doc, XPathConstants.NODESET);
		for(int i=0; i<nodeList.getLength();i++)
		{
			Element node=(Element)nodeList.item(i);
			StringTuple[] st=getAttributes(node);
			
			String elName=node.getAttribute(NAMETAG);
			String elTo=node.getAttribute(AREFTAG);
			String elType=node.getAttribute(TYPETAG);
			
			String elFrom=((Element)node.getParentNode().getParentNode()).getAttribute(IDTAG);
			if(elTo.isEmpty())
				throw new Exception("Dependency has no aref attribute");			
			if(elFrom.isEmpty())
				throw new Exception("No ParentID of Dependency with aref: "+elTo);
			if(elName.isEmpty())			
				elName=DEFAULTNAME;
			
			if(elType.isEmpty())
			{
				elType=node.getTagName();
			}
			
			if(node.getTagName().equals("dependee"))//if dependee, then switch from/to direction
			{
				String temp=elFrom;
				elFrom=elTo;
				elTo=temp;
			}
			result.add(new IStarMLEdge(elType, elFrom, elTo, elName, st));			
		}
		return result;
		
	}

}
