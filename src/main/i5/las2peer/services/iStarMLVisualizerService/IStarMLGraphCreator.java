package i5.las2peer.services.iStarMLVisualizerService;



import i5.las2peer.services.iStarMLVisualizerService.data.IStarMLEdge;
import i5.las2peer.services.iStarMLVisualizerService.data.IStarMLNode;
import i5.las2peer.services.iStarMLVisualizerService.drawing.yFilesCustomEdges;
import i5.las2peer.services.iStarMLVisualizerService.drawing.yFilesCustomNodes;



import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Stroke;
import java.awt.geom.Arc2D;
import java.awt.geom.Path2D;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


/*
//import y.base.Edge;
import B.A.H;

//import y.base.Node;
import B.A.Y;

//import y.geom.YRectangle;
import B.J.K;

//import y.layout.Layouter;
import B.G.m;

//import y.layout.organic.SmartOrganicLayouter;
import B.G.kzu.F;

//import y.layout.router.OrganicEdgeRouter;
import B.G.tew.A;

//import y.view.AbstractCustomNodePainter;
import B.I.F;

//import y.view.Arrow;
import B.I.D;

//import y.view.BridgeCalculator;
import B.I.DB;

//import y.view.DefaultGraph2DRenderer;
import B.I.X;

//import y.view.GenericEdgeRealizer;
import B.I.p;

//import y.view.GenericNodeRealizer;
import B.I.FA;

//import y.view.Graph2D;
import B.I.QA;

//import y.view.Graph2DLayoutExecutor;
import B.I.BA;

//import y.view.Graph2DView;
import B.I.S;

//import y.view.LineType;
import B.I.dA;

//import y.view.NodeLabel;
import B.I.cB;

//import y.view.EdgeLabel;
import B.I.EA;

//import yext.svg.io.SVGIOHandler;
import A.A.vtt.A;
*/



/**
 * Generates an SVG graph from given node and edge data
 * @author Alexander
 *
 */
public class IStarMLGraphCreator
{
    private static final String BELIEF_CONF = "BeliefConf";
    private static final String SOFT_GOAL_CONF = "SoftGoalConf";
    private static final String GOAL_CONF = "GoalConf";
    private static final String TASK_CONF = "TaskConf";
    private static final String RESOURCE_CONF = "ResourceConf";
    private static final String POSITION_CONF = "PositionConf";
    private static final String ROLE_CONF = "RoleConf";
    private static final String AGENT_CONF = "AgentConf";
    private static final String ACTOR_CONF = "ActorConf";

    //private static final LineType _defaultNodeLineType = LineType.LINE_2;
    private static final A.I.dA _defaultNodeLineType = A.I.dA.I;

    private static final Color _defaultNodeColor = new Color(111,236,108);
    private static final String ACTORLINKARROW = "ActorLinkArrow";
    private static final String INS_LINKCONF = "INSLink";
    private static final String PLAYS_LINKCONF = "PlaysLink";
    private static final String OCCUPIES_LINKCONF = "OccupiesLink";
    private static final String IS_PART_OF_LINKCONF = "IsPartOfLink";
    private static final String COVERS_LINKCONF = "CoversLink";
    private static final String ISA_LINKCONF = "ISALink";
    private static final String DEPENDENCYCONF = "dependency";
    private static final String DEPENDENCYARROW = "D";
    //private Layouter _layouter = null;
    private A.G.m _layouter = null;
    private double _defaultNodeSize=150;
    private double _fontScalingFactor=2;
    //private Graph2DView _view;
    private A.I.S _view;
    //private GenericEdgeRealizer _edgeRealizer;
    private A.I.p _edgeRealizer;
    //private GenericNodeRealizer _nodeRealizer;
    private A.I.FA _nodeRealizer;
    private static final Color _defaultEdgeColor = new Color(0, 0, 0);

    //private static final LineType _defaultEdgeLineType = LineType.LINE_2;
    private static final A.I.dA _defaultEdgeLineType = A.I.dA.I;

    //private Map<Node, IStarMLNode> _nodeAttributesMap = new HashMap<Node, IStarMLNode>();
    private Map<A.A.Y, IStarMLNode> _nodeAttributesMap = new HashMap<A.A.Y, IStarMLNode>();
    //private Map<Edge, IStarMLEdge> _edgeAttributesMap = new HashMap<Edge, IStarMLEdge>();
    private Map<A.A.H, IStarMLEdge> _edgeAttributesMap = new HashMap<A.A.H, IStarMLEdge>();


    private Color _nodeLabelTextColor=Color.BLACK;
    /**
     * Constructor Initializes layout and custom element visualizations
     */
    public IStarMLGraphCreator()
    {
        int minimalNodeDistance = 130;
		/*CircularLayouter circularLayouter_compact = new CircularLayouter();
		circularLayouter_compact.setLayoutStyle(CircularLayouter.BCC_COMPACT);
		circularLayouter_compact.setConsiderNodeLabelsEnabled(true);
		circularLayouter_compact.getSingleCycleLayouter().setMinimalNodeDistance(minimalNodeDistance);
		circularLayouter_compact.getBalloonLayouter().setAllowOverlaps(false);*/

        //SmartOrganicLayouter organicLayouter = new SmartOrganicLayouter();
        A.G.kzu.F organicLayouter = new A.G.kzu.F();

        //organicLayouter.setDeterministic(true);
        organicLayouter.u(true);
        //organicLayouter.setQualityTimeRatio(0.8);
        organicLayouter.d(0.8);
        //organicLayouter.setNodeOverlapsAllowed(false);
        organicLayouter.w(false);

        //organicLayouter.setLabelLayouterEnabled(true);



        //organicLayouter.setNodeSizeAware(true);
        organicLayouter.s(true);
        //organicLayouter.setConsiderNodeLabelsEnabled(true);
        organicLayouter.r(true);
        //organicLayouter.setMinimalNodeDistance(minimalNodeDistance);
        organicLayouter.b(minimalNodeDistance);
        //organicLayouter.setCompactness(0.7);
        organicLayouter.e(0.7);

        _layouter = organicLayouter;

        //_view = new Graph2DView();
        _view = new A.I.S();
        //_view.setAntialiasedPainting(true);
        _view.J(true);

        //_edgeRealizer = new GenericEdgeRealizer();
        _edgeRealizer = new A.I.p();
        //_edgeRealizer.setLineType(_defaultEdgeLineType);
        _edgeRealizer.C(_defaultEdgeLineType);
        //_edgeRealizer.setLineColor(_defaultEdgeColor);
        _edgeRealizer.I(_defaultEdgeColor);

        //_nodeRealizer = new GenericNodeRealizer();
        _nodeRealizer = new A.I.FA();
        //_nodeRealizer.setFillColor(_defaultNodeColor);
        _nodeRealizer.B(_defaultNodeColor);
        //_nodeRealizer.setLineType(_defaultNodeLineType);
        _nodeRealizer.A(_defaultNodeLineType);
        //_view.getGraph2D().setDefaultEdgeRealizer(_edgeRealizer);
        _view.H().A(_edgeRealizer);
        //_view.getGraph2D().setDefaultNodeRealizer(_nodeRealizer);
        _view.H().B(_nodeRealizer);

        //BridgeCalculator bridgeCalculator = new BridgeCalculator();
        A.I.DB bridgeCalculator = new A.I.DB();
        //((DefaultGraph2DRenderer) _view.getGraph2DRenderer()).setBridgeCalculator(bridgeCalculator);
        ((A.I.X) _view.á()).A(bridgeCalculator);

        //bridgeCalculator.setCrossingMode(BridgeCalculator.CROSSING_MODE_ORDER_INDUCED);
        bridgeCalculator.C(A.I.DB.O);
        //bridgeCalculator.setCrossingStyle(BridgeCalculator.CROSSING_STYLE_GAP);
        bridgeCalculator.B(A.I.DB.S);
        //bridgeCalculator.setOrientationStyle(BridgeCalculator.ORIENTATION_STYLE_POSITIVE);
        bridgeCalculator.A(A.I.DB.H);

        configureArrows();
        configureEdgeStyles();
        configureNodeStyles();
        //_nodeRealizer.setConfiguration(ACTOR_CONF);
        _nodeRealizer.H(ACTOR_CONF);



    }
    /**
     * Constructor with color specification
     * @param nodeColor Background color of nodes
     * @param textColor Color of node labels
     */
    public IStarMLGraphCreator(Color nodeColor, Color textColor )
    {
        this();
        _nodeRealizer.B(nodeColor);
        _nodeLabelTextColor=textColor;

    }
    /**
     * Adds a new configuration to the list of available node configurations (types of nodes)
     * @param factory manages configurations
     * @param painter defines how to draw a node
     * @param config name of the config for later referring
     */
    @SuppressWarnings({"unchecked","rawtypes"})
    //private void addNodeConfiguration(GenericNodeRealizer.Factory factory, AbstractCustomNodePainter painter, String config)
    private void addNodeConfiguration(A.I.FA._P factory, A.I.F painter, String config)
    {
        //Map implementationsMap = factory.createDefaultConfigurationMap();
        Map implementationsMap = factory.A();
        //implementationsMap.put(GenericNodeRealizer.Painter.class, painter);
        implementationsMap.put(A.I.FA._L.class, painter);
        //implementationsMap.put(GenericNodeRealizer.ContainsTest.class, painter);
        implementationsMap.put(A.I.FA._K.class, painter);
        //factory.addConfiguration(config, implementationsMap);
        factory.A(config, implementationsMap);
    }
    /**
     * Makes all needed iStar node types available to yFiles
     */
    private void configureNodeStyles()
    {
        //GenericNodeRealizer.Factory factory = GenericNodeRealizer.getFactory();
        A.I.FA._P factory = A.I.FA.ć();

        //Actor
        addNodeConfiguration(factory,  new yFilesCustomNodes.ActorNodePainter(),ACTOR_CONF);
        //Agent
        addNodeConfiguration(factory,  new yFilesCustomNodes.AgentNodePainter(),AGENT_CONF);
        //Role
        addNodeConfiguration(factory,  new yFilesCustomNodes.RoleNodePainter(),ROLE_CONF);
        //Position
        addNodeConfiguration(factory,  new yFilesCustomNodes.PositionNodePainter(),POSITION_CONF);


        //Resource
        addNodeConfiguration(factory,  new yFilesCustomNodes.ResourceNodePainter(),RESOURCE_CONF);
        //Task
        addNodeConfiguration(factory,  new yFilesCustomNodes.TaskNodePainter(),TASK_CONF);
        //Goal
        addNodeConfiguration(factory,  new yFilesCustomNodes.GoalNodePainter(),GOAL_CONF);
        //Softgoal
        addNodeConfiguration(factory,  new yFilesCustomNodes.SoftGoalNodePainter(),SOFT_GOAL_CONF);
        //Belief
        addNodeConfiguration(factory,  new yFilesCustomNodes.BeliefNodePainter(),BELIEF_CONF);
    }
    /**
     * Switches to a certain node type, which will later be created
     * @param conf name of the config to use
     */
    private void setNodeMode(String conf)
    {
        //_nodeRealizer.setConfiguration(conf);
        _nodeRealizer.H(conf);
    }
    /**
     * Switches to dependency edge type, which will later be created
     */
    private void setEdgeModeDependency()
    {
        //_edgeRealizer.setConfiguration(DEPENDENCYCONF);
        _edgeRealizer.D(DEPENDENCYCONF);
        //_edgeRealizer.setArrow(Arrow.getCustomArrow(DEPENDENCYARROW));
        _edgeRealizer.B(A.I.D.A(DEPENDENCYARROW));
    }
    /**
     * Switches to a certain actor link type, which will later be created
     * @param conf name ofthe config to use
     */
    private void setEdgeModeActorLink(String conf)
    {
        //_edgeRealizer.setConfiguration(conf);
        _edgeRealizer.D(conf);
        //_edgeRealizer.setArrow(Arrow.getCustomArrow(ACTORLINKARROW));
        _edgeRealizer.B(A.I.D.A(ACTORLINKARROW));
    }
    /**
     * Creates custom arrows for the new edges
     */
    private void configureArrows()
    {
        //Dependency
        Double arrowWidth=15d;
        Double arrowHeight=15d;
        Arc2D.Double arc = new Arc2D.Double(-arrowWidth/2,-arrowHeight/2,arrowWidth,arrowHeight,90d,180d,Arc2D.CHORD);
        Stroke s=new BasicStroke(2f);
        //Arrow.addCustomArrow(DEPENDENCYARROW, arc,Color.WHITE,s,Color.BLACK);
        A.I.D.A(DEPENDENCYARROW, arc,Color.WHITE,s,Color.BLACK);

        Path2D.Double path = new Path2D.Double();
        path.moveTo(0, 0);
        path.lineTo(-arrowHeight, -arrowWidth/2);
        path.moveTo(0, 0);
        path.lineTo(-arrowHeight, arrowWidth/2);
        //Arrow.addCustomArrow(ACTORLINKARROW, path,Color.WHITE,s,Color.BLACK);
        A.I.D.A(ACTORLINKARROW, path,Color.WHITE,s,Color.BLACK);

    }
    /**
     * Adds a new type of actor link to the available list yFiles can use for drawing
     * @param factory manages available edges
     * @param text the text which is displayed on the edge (type dependent)
     * @param config name of the config to use
     */

    @SuppressWarnings({"unchecked","rawtypes"})
    //private void addLinkConfiguration(GenericEdgeRealizer.Factory factory, String text, String config)
    private void addLinkConfiguration(A.I.p._N factory, String text, String config)
    {

        //Map implementationsMap = factory.createDefaultConfigurationMap();
        Map implementationsMap = factory.C();
        //implementationsMap.put(GenericEdgeRealizer.Painter.class, new yFilesCustomEdges.SignatureEdgePainter(text));
        implementationsMap.put(A.I.p._H.class, new yFilesCustomEdges.SignatureEdgePainter(text));
        //factory.addConfiguration(config, implementationsMap);
        factory.A(config, implementationsMap);
    }
    /**
     * Adds all needed graphical representations of edges
     */
    @SuppressWarnings({"unchecked","rawtypes"})
    private void configureEdgeStyles()
    {
        //GenericEdgeRealizer.Factory factory = GenericEdgeRealizer.getFactory();
        A.I.p._N factory = A.I.p.Ă();
        //Map implementationsMap = factory.createDefaultConfigurationMap();
        Map implementationsMap = factory.C();

        //Dependency
        yFilesCustomEdges.DependencyArrowPainter dependencyPainter = new yFilesCustomEdges.DependencyArrowPainter(DEPENDENCYARROW);
        //implementationsMap.put(GenericEdgeRealizer.ArrowPainter.class, dependencyPainter);
        implementationsMap.put(A.I.p._B.class, dependencyPainter);
        //factory.addConfiguration(DEPENDENCYCONF, implementationsMap);
        factory.A(DEPENDENCYCONF, implementationsMap);

        //ISA
        addLinkConfiguration(factory, "ISA", ISA_LINKCONF);
        //Covers
        addLinkConfiguration(factory, "Covers", COVERS_LINKCONF);
        //Is Part Of
        addLinkConfiguration(factory, "Is part of", IS_PART_OF_LINKCONF);
        //Occupies
        addLinkConfiguration(factory, "Occupies", OCCUPIES_LINKCONF);
        //Plays
        addLinkConfiguration(factory, "Plays", PLAYS_LINKCONF);
        //INS
        addLinkConfiguration(factory, "INS", INS_LINKCONF);
    }
    /**
     * Creates an SVG directly from provided nodes and edges
     * @param nodeList
     * @param edgeList
     * @return
     * @throws Exception
     */
    public String createSVG (ArrayList<IStarMLNode> nodeList, ArrayList<IStarMLEdge> edgeList)throws Exception
    {
        //Graph2D graph=createGraph(nodeList, edgeList);
        A.I.QA graph=createGraph(nodeList, edgeList);
        String result=createSVG(graph);
        return result;
    }

    /**
     * Creates a Graph2D object which represents the iStar Graph
     * @param nodeList list of all nodes to use in the graph
     * @param edgeList list of all edges to use in the graph
     * @return object, which stores the graph information
     * @throws Exception
     */
    //public Graph2D createGraph(ArrayList<IStarMLNode> nodeList, ArrayList<IStarMLEdge> edgeList) throws Exception
    public A.I.QA createGraph(ArrayList<IStarMLNode> nodeList, ArrayList<IStarMLEdge> edgeList) throws Exception
    {

        _nodeAttributesMap.clear();
        _edgeAttributesMap.clear();
        //Graph2D graph = _view.getGraph2D();
        A.I.QA graph = _view.H();
        //graph.getDefaultNodeRealizer().setSize(_defaultNodeSize, _defaultNodeSize);
        graph.Ď().B(_defaultNodeSize, _defaultNodeSize);


        //HashMap<String, Node> nodeMap=new HashMap<String,Node>();
        HashMap<String, A.A.Y> nodeMap=new HashMap<String,A.A.Y>();

        //first create nodes
        for (int i = 0; i < nodeList.size(); i++)
        {
            IStarMLNode n = nodeList.get(i);

            if(nodeMap.containsKey(n.getID()))
                throw new Exception("Element with ID "+n.getID()+" already existing");
            String type=n.getType();
            if(type.isEmpty())
            {
                type="actor";
            }

            setNodeType(type);
            n.setType(type);

            //Node ynode=graph.createNode();
            A.A.Y ynode=graph.É();

            nodeMap.put(n.getID(), ynode);

            String tempString=n.getName();

            if(n.getName().indexOf(' ')>0)//auto line breaks for too long texts
            {

                int stringLength=tempString.length();
                int lines= 1;
                if(stringLength<10)
                {
                    lines=1;
                }
                else if (stringLength<20)
                {
                    lines=2;
                }
                else if (stringLength<30)
                {
                    lines=3;
                }
                else if (stringLength<40)
                {
                    lines=4;
                }
                else
                    lines=5;
                String[] words=tempString.split(" ");
                int k=0;
                String line="";
                tempString="";
                for (int j = 0; j < lines; j++) {
                    line="";
                    while(k<(j+1)*(words.length/lines+1)&&k<words.length)
                    {
                        line+=words[k++]+" ";
                    }
                    tempString+=line.trim()+"\n";
                }

            }

            //NodeLabel nodeLabel = new NodeLabel(tempString.trim());
            A.I.cB nodeLabel = new A.I.cB(tempString.trim());
            //nodeLabel.setTextColor(_nodeLabelTextColor);
            nodeLabel.C(_nodeLabelTextColor);
            //nodeLabel.setFontSize((int)(nodeLabel.getFontSize()*_fontScalingFactor));
            nodeLabel.B((int)(nodeLabel.P()*_fontScalingFactor));

            //nodeLabel.setConfiguration("CroppingLabel");


            //nodeLabel.setAutoSizePolicy(NodeLabel.AUTOSIZE_NODE_WIDTH);
            nodeLabel.G(A.I.cB.¤);


            //graph.getRealizer(ynode).setLabel(nodeLabel);
            graph.f(ynode).D(nodeLabel);
            //YRectangle box=nodeLabel.getBox(); //do a test, if label does not fit into node: decrease size
            A.J.K box=nodeLabel.l(); //do a test, if label does not fit into node: decrease size
            //double withDiff=box.getWidth()-_defaultNodeSize;
            double withDiff=box.Ë()-_defaultNodeSize;




            if(withDiff>0)//check width and shrink if necessary
            {
                //double factor=0.95*_defaultNodeSize/box.getWidth();
                double factor=0.95*_defaultNodeSize/box.Ë();
                //nodeLabel.setFontSize((int)(nodeLabel.getFontSize() * factor));
                nodeLabel.B((int)(nodeLabel.P() * factor));
            }
            //box=nodeLabel.getBox();
            box=nodeLabel.l();
            //double heightDiff=box.getHeight()-_defaultNodeSize/2;
            double heightDiff=box.Ì()-_defaultNodeSize/2;
            if(heightDiff>0)//check height and shrink if necessary
            {
                //double factor=0.95*_defaultNodeSize/2/box.getHeight();
                double factor=0.95*_defaultNodeSize/2/box.Ì();
                //nodeLabel.setFontSize((int)(nodeLabel.getFontSize() * factor));
                nodeLabel.B((int)(nodeLabel.P() * factor));
            }
            _nodeAttributesMap.put(ynode, n);


        }
        //then create edges
        for (int i = 0; i < edgeList.size(); i++)
        {
            IStarMLEdge e =edgeList.get(i);
            //Node fromNode=nodeMap.get(e.getFromID());
            A.A.Y fromNode=nodeMap.get(e.getFromID());
            //Node toNode=nodeMap.get(e.getToID());;
            A.A.Y toNode=nodeMap.get(e.getToID());;
            String type=e.getType();
            if(fromNode==null)
                throw new Exception("Source node with ID "+e.getFromID()+" does not exist");
            if(toNode==null)
                throw new Exception("Target node with ID "+e.getToID()+" does not exist");

            setEdgeType(type);
            //Edge yedge=graph.createEdge(fromNode, toNode);
            A.A.H yedge=graph.A(fromNode, toNode);
            if(!e.getName().isEmpty())
            {
                //EdgeLabel edgeLabel = new EdgeLabel(e.getName());
                A.I.EA edgeLabel = new A.I.EA(e.getName());
                //edgeLabel.setFontSize((int)(edgeLabel.getFontSize() * _fontScalingFactor));
                edgeLabel.B((int)(edgeLabel.P() * _fontScalingFactor));
                //graph.getRealizer(yedge).addLabel(edgeLabel);
                graph.X(yedge).B(edgeLabel);
            }
            _edgeAttributesMap.put(yedge, e);
        }
        return graph;
    }
    /**
     * Activates the desired node mode for node creation
     * @param type node type to activate
     * @throws Exception
     */
    private void setNodeType(String type) throws Exception {
        String s = type.toLowerCase();
        if(s.equals("actor"))
        {
            setNodeMode(ACTOR_CONF);

        }
        else if(s.equals("agent"))
        {
            setNodeMode(AGENT_CONF);

        }
        else if(s.equals("position"))
        {
            setNodeMode(POSITION_CONF);

        }
        else if(s.equals("role"))
        {
            setNodeMode(ROLE_CONF);

            //ielements
        }
        else if(s.equals("resource"))
        {
            setNodeMode(RESOURCE_CONF);

        }
        else if(s.equals("task"))
        {
            setNodeMode(TASK_CONF);

        }
        else if(s.equals("goal"))
        {
            setNodeMode(GOAL_CONF);

        }
        else if(s.equals("softgoal"))
        {
            setNodeMode(SOFT_GOAL_CONF);

        }
        else if(s.equals("belief"))
        {
            setNodeMode(BELIEF_CONF);

            //default: throw new Exception("Unknown actor type: "+type);
        }
        else
        {
            setNodeMode(ACTOR_CONF);

        }
    }
    /**
     * Activates the desired edge mode for edge creation
     * @param type edge type to use
     * @throws Exception
     */
    private void setEdgeType(String type) throws Exception {
        String s = type.toLowerCase();
        if(s.equals("depender"))
        {
            setEdgeModeDependency();

        }
        else if(s.equals("dependee"))
        {
            setEdgeModeDependency();

        }
        else if(s.equals("is_part_of"))
        {
            setEdgeModeActorLink(IS_PART_OF_LINKCONF);

        }
        else if(s.equals("is_a"))
        {
            setEdgeModeActorLink(ISA_LINKCONF);

        }
        else if(s.equals("instance_of"))
        {
            setEdgeModeActorLink(INS_LINKCONF);

        }
        else if(s.equals("plays"))
        {
            setEdgeModeActorLink(PLAYS_LINKCONF);

        }
        else if(s.equals("covers"))
        {
            setEdgeModeActorLink(COVERS_LINKCONF);

        }
        else if(s.equals("occupies"))
        {
            setEdgeModeActorLink(OCCUPIES_LINKCONF);

        }
        else
        {
            throw new Exception("Unknown edge type: " + type);
        }
    }
    /**
     * Generates an SVG String from the graph
     * @param graphData graph from which an SVG is to be generated
     * @return SVG as a string
     * @throws IOException
     */
    //public String createSVG(Graph2D graphData) throws IOException
    public String createSVG(A.I.QA graphData) throws IOException
    {


        String svgGraphString;
        //new Graph2DLayoutExecutor().doLayout(graphData, _layouter);
        new A.I.BA().A(graphData, _layouter);


        //OrganicEdgeRouter router = new OrganicEdgeRouter();
		/*B.G.tew.A router = new B.G.tew.A();
		//router.setCoreLayouter(_layouter);

		router.setEdgeNodeOverlapAllowed(false);
		router.setMinimalDistance(50);
		router.setRoutingAll(true);*/
        //router.doLayout(graphData);

        //_view.fitContent();
        _view.Þ();


        //graphData.updateViews();
        graphData.ę();


        //SVGIOHandler ioh = new SVGIOHandler();
        yext.A.D.E ioh = new yext.A.D.E();

        //ioh.setCanvasSizeAssigned(false);
        ioh.J(false);
        //ioh.setUseCSS(true);
        //ioh.K(true);

        SVGGraphRenderer renderer = new SVGGraphRenderer();
        //renderer.setDrawEdgesFirst(true);
        renderer.C(true);

        renderer.setNodeAttributesMap(_nodeAttributesMap);
        renderer.setEdgeAttributesMap(_edgeAttributesMap);
        //ioh.setSVGGraph2DRenderer(renderer);
        ioh.A(renderer);

        //_view.setPaintDetailThreshold(0.0);
        _view.L(0.0);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        //ioh.write(graphData, outputStream);
        ioh.A(graphData, outputStream);
        //ioh.A(graphData, "T:/tt.xml");







        svgGraphString = outputStream.toString("UTF-8");

        return svgGraphString;
    }


}
