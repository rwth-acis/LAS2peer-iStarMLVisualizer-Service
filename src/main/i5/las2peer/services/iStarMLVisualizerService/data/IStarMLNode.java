package i5.las2peer.services.iStarMLVisualizerService.data;

/**
 * Stores information of an iStarML node
 * @author Alexander
 *
 */
public class IStarMLNode 
{

	private StringTuple[] _attributes;
	public StringTuple[] getAttributes() {
		return _attributes;
	}
	
	public String getID() {
		return _ID;
	}
	
	public String getType() {
		return _type;
	}
	public void setType(String type) {
		_type=type;
	}


	public String getName() {
		return _name;
	}
	
	private String _ID="";
	private String _type="";
	private String _name="";
	
	
	public IStarMLNode(String type, String id, String name, StringTuple[] attributes)
	{
		_type=type;		
		_ID=id;
		_name=name;
		_attributes=attributes;		
	}
	public String toString()
	{
		return _name+" "+_ID+" "+_type;
	}
	
	

}
