import static org.junit.Assert.*;


import i5.las2peer.services.iStarMLVisualizerService.data.IStarMLEdge;
import i5.las2peer.services.iStarMLVisualizerService.data.IStarMLNode;
import i5.las2peer.services.iStarMLVisualizerService.IStarMLParser;
import i5.las2peer.services.iStarMLVisualizerService.data.StringTuple;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import java.util.Hashtable;


import org.junit.Test;

public class IStarMLParserTest {

	private static String _basePath="./testXMLFiles/";
	
	public static String getFile(String path)throws Exception 
	{
		   String content = null;
		   File file = new File(_basePath+path+".xml"); //for ex foo.txt
		   FileReader reader = null;
		   try {
		       reader = new FileReader(file);
		       char[] chars = new char[(int) file.length()];
		       reader.read(chars);
		       content = new String(chars);
		       reader.close();
		   } catch (IOException e) {
		       e.printStackTrace();
		   }finally {
			   reader.close();
		   }
		   
		   return content;
	}
	@Test
	public void testIStarMLParser() {
		try {
			@SuppressWarnings("unused")
			IStarMLParser parser=new IStarMLParser();
		} catch (Exception e) {
			fail("Exception: "+e.getMessage());
		}
	}

	@Test
	public void testLoadIStarML() {
		try {
			IStarMLParser parser=new IStarMLParser();
			parser.loadIStarML(getFile("testFile1"));
			
		} catch (Exception e) {
			fail("Exception: "+e.getMessage());
		}
	}

	@Test
	public void testGetElements() {
		try {
			IStarMLParser parser=new IStarMLParser();
			parser.loadIStarML(getFile("testFile1"));
			
			ArrayList<IStarMLNode> nodes=parser.getNodes();
			ArrayList<IStarMLEdge> edges=parser.getEdges();
			
			Hashtable<String, IStarMLNode> testNodes =new Hashtable<String, IStarMLNode>();
			testNodes.put("1", new IStarMLNode("actor", "1", "Medium", new StringTuple[]{}));
			testNodes.put("2", new IStarMLNode("actor", "2", "Group", new StringTuple[]{}));
			testNodes.put("3", new IStarMLNode("agent", "3", "Teacher", new StringTuple[]{}));
			testNodes.put("4", new IStarMLNode("goal", "4", "Ask", new StringTuple[]{}));
			testNodes.put("5", new IStarMLNode("task", "5", "Check", new StringTuple[]{}));
			
			if (nodes.size()!=testNodes.size())
				fail("Wrong amount of nodes found!");
			
			for (int i = 0; i < nodes.size(); i++) {
				IStarMLNode node=nodes.get(i);
				if(testNodes.containsKey(node.getID()))
				{					
					IStarMLNode testNode=testNodes.get(node.getID());					
					if(testNode.getID().equals(node.getID())
							&& testNode.getType().equals(node.getType())
							&& testNode.getName().equals(node.getName()))
					{
						
						testNodes.remove(node.getID());
					}
				}
			}
			if(testNodes.size()>0)
				fail("Not all nodes found correctly!");
			
			Hashtable<String, IStarMLEdge> testEdges =new Hashtable<String, IStarMLEdge>();
			testEdges.put("12", new IStarMLEdge("is_a","1","2","",new StringTuple[]{}));
			testEdges.put("23", new IStarMLEdge("is_part_of","2","3","",new StringTuple[]{}));
			testEdges.put("41", new IStarMLEdge("depender","4","1","",new StringTuple[]{}));
			testEdges.put("42", new IStarMLEdge("depender","4","2","",new StringTuple[]{}));
			testEdges.put("34", new IStarMLEdge("dependee","3","4","",new StringTuple[]{}));
			
			if (edges.size()!=testEdges.size())
				fail("Wrong amount of nodes found!");
			
			for (int i = 0; i < edges.size(); i++) {
				IStarMLEdge edge=edges.get(i);
				String key=edge.getFromID()+edge.getToID();
				
				if(testEdges.containsKey(key))
				{
					IStarMLEdge testEdge=testEdges.get(key);
					if(testEdge.getType().equals(edge.getType())
							&& testEdge.getFromID().equals(edge.getFromID())
							&& testEdge.getToID().equals(edge.getToID()))
					{
						
						testEdges.remove(key);
					}
				}
			}
			
			
			if(testEdges.size()>0)
				fail("Not all edges found correctly! "+testEdges.size());
			
		} catch (Exception e) {
			fail("Exception: "+e.getMessage());
		}
	}

	

}
