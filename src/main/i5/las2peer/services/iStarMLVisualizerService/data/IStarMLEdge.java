package i5.las2peer.services.iStarMLVisualizerService.data;

/**
 * Stores information of an iStarML edge
 * @author Alexander
 *
 */
public class IStarMLEdge 
{

	private StringTuple[] _attributes;
	public StringTuple[] getAttributes() {
		return _attributes;
	}


	public String getFromID() {
		return _fromID;
	}


	public String getToID() {
		return _toID;
	}


	public String getType() {
		return _type;
	}


	public String getName() {
		return _name;
	}


	private String _fromID="";
	private String _toID="";
	private String _type="";
	private String _name="";
	
	
	public IStarMLEdge(String type, String from, String to, String name, StringTuple[] attributes)
	{
		_type=type;
		_fromID=from;
		_toID=to;
		_name=name;
		_attributes=attributes;		
	}
	public String toString()
	{
		return _name+" "+_type+" "+_fromID+" "+_toID;
	}
}
