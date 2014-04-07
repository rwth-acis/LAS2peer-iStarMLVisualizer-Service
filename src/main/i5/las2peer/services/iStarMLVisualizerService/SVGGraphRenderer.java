package i5.las2peer.services.iStarMLVisualizerService;



import i5.las2peer.services.iStarMLVisualizerService.data.IStarMLEdge;
import i5.las2peer.services.iStarMLVisualizerService.data.IStarMLNode;
import i5.las2peer.services.iStarMLVisualizerService.data.StringTuple;


import java.util.HashMap;
import java.util.Map;
/*
//import y.base.Edge;
import B.A.H;

//import y.base.Node;
import B.A.Y;

//import yext.svg.io.SVGDOMEnhancer;
import A.A.vtt.D;*/

/**
 * Adds additional attributes to the output SVG
 * @author Alexander
 *
 */
//public class SVGGraphRenderer extends SVGDOMEnhancer
public class SVGGraphRenderer extends yext.A.D.D
{
    //private Map<Node, IStarMLNode> nodeAttributesMap = new HashMap<Node, IStarMLNode>();
    private Map<A.A.Y, IStarMLNode> nodeAttributesMap = new HashMap<A.A.Y, IStarMLNode>();
    //private Map<Edge, IStarMLEdge> edgeAttributesMap = new HashMap<Edge, IStarMLEdge>();
    private Map<A.A.H, IStarMLEdge> edgeAttributesMap = new HashMap<A.A.H, IStarMLEdge>();


    @Override
    //protected void initializeDOM() {
    protected void O() {
        try {
            //super.initializeDOM();
            super.O();
        }
        catch(Exception e) {
            //TODO
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }
    @Override
    //protected void nodeAddedToDOM(Node node, org.w3c.dom.Element element) {
    protected void A(A.A.Y node, org.w3c.dom.Element element) {
        try {
            IStarMLNode originalNode = this.nodeAttributesMap.get(node);

            StringTuple[] attributes = originalNode.getAttributes();
            for (int i = 0; i < attributes.length; i++) {
                element.setAttribute(attributes[i].getS1(), attributes[i].getS2());
            }
            element.setAttribute("type", originalNode.getType());

        }
        catch(Exception e) {
            //TODO
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }
    @Override
    //protected void edgeAddedToDOM(Edge yEdge, org.w3c.dom.Element element) {
    protected void A(A.A.H yEdge, org.w3c.dom.Element element) {
        try {
            IStarMLEdge originalEdge = this.edgeAttributesMap.get(yEdge);

            StringTuple[] attributes = originalEdge.getAttributes();
            for (int i = 0; i < attributes.length; i++) {
                element.setAttribute(attributes[i].getS1(), attributes[i].getS2());
            }
            element.setAttribute("type", originalEdge.getType());
        }
        catch(Exception e) {
            //TODO
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }
    /**
     * Adds the required information for all node elements
     * @param nodeAttributesMap original node data from IStarML
     */
    //public void setNodeAttributesMap(Map<Node, IStarMLNode> nodeAttributesMap) {
    public void setNodeAttributesMap(Map<A.A.Y, IStarMLNode> nodeAttributesMap) {
        this.nodeAttributesMap = nodeAttributesMap;
    }
    /**
     * Adds the required information for all edge elements
     * @param edgeAttributesMap original edge data from IStarML
     */
    //public void setEdgeAttributesMap(Map<Edge, IStarMLEdge> edgeAttributesMap) {
    public void setEdgeAttributesMap(Map<A.A.H, IStarMLEdge> edgeAttributesMap) {
        this.edgeAttributesMap = edgeAttributesMap;
    }

}
