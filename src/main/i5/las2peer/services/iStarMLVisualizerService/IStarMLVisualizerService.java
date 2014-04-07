package i5.las2peer.services.iStarMLVisualizerService;

import java.awt.Color;
//import java.util.HashMap;

import i5.las2peer.api.Service;
import i5.las2peer.restMapper.RESTMapper;
import i5.las2peer.restMapper.annotations.*;
import i5.las2peer.services.iStarMLVisualizerService.utils.Util;

@Path("IStarMLVisualizerService")
public class IStarMLVisualizerService extends Service
{
	private static final String OK = "OK";

	public IStarMLVisualizerService()
	{

	}
    public String getRESTMapping()
    {
        String result="";
        try
        {
            result= RESTMapper.getMethodsAsXML(this.getClass());
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return result;
    }

	
	@POST
	public String createVisualization(@QueryParam(name="nr",defaultValue = "0") int r1, @QueryParam(name="ng",defaultValue = "73") int g1, @QueryParam(name="nb", defaultValue = "199") int b1, @QueryParam(name="lr",defaultValue = "255") int r2, @QueryParam(name="lg",defaultValue = "255") int g2, @QueryParam(name="lb",defaultValue = "255") int b2, @ContentParam() String xml) throws Exception
	{		
		//clamp, to be on the safe side
		try
		{
			r1= Util.clamp(r1, 0, 255);
			r2=Util.clamp(r2,0,255);
			g1=Util.clamp(g1,0,255);
			g2=Util.clamp(g2,0,255);
			b1=Util.clamp(b1,0,255);
			b2=Util.clamp(b2,0,255);

			IStarMLParser parser = new IStarMLParser();
		    parser.loadIStarML(xml);	
		   
		    IStarMLGraphCreator creator = new IStarMLGraphCreator(new Color(r1,g1,b1), new Color(r2,g2,b2));   
		    String svgGraphString = creator.createSVG(parser.getNodes(), parser.getEdges());
		    return svgGraphString;
		}
		catch(Exception e)
		{
			return e.getMessage();
		}
		
	}
	@GET
	public String loginCheck()
	{
		return OK;
	}

}
