package i5.las2peer.services.iStarMLVisualizerService.drawing;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.geom.Arc2D;
import java.awt.geom.CubicCurve2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Path2D;
import java.awt.geom.QuadCurve2D;
import java.awt.geom.Rectangle2D;

//import y.view.AbstractCustomNodePainter;
//import A.I.F;

//import y.view.GenericNodeRealizer;
//import A.I.FA;

//import y.view.NodeRealizer;
//import A.I.$A;

/**
 * Stores all custom node types used in the graph generation process
 * classes are cloned and modified versions of the yFiles demo classes
 * @author Alexander
 *
 */
public class yFilesCustomNodes
{
    //Belief
    public static class BeliefNodePainter
            //extends AbstractCustomNodePainter
            extends A.I.F
            //implements GenericNodeRealizer.ContainsTest {
            implements A.I.FA._K {


        //protected void paintNode(
        protected void B(
                //final NodeRealizer context,
                final A.I.$A context,
                final Graphics2D graphics,
                final boolean sloppy
                        ) {
            final Ellipse2D.Double path = getShape(context);


            //final Color fc = getFillColor(context, false);
            final Color fc = D(context, false);
            if (fc != null) {
                graphics.setColor(fc);
                graphics.fill(path);
            }

            //final Color lc = getLineColor(context, false);
            final Color lc = A(context, false);
            //final Stroke ls = getLineStroke(context, false);
            final Stroke ls = C(context, false);
            if (lc != null && ls != null) {
                graphics.setColor(lc);
                graphics.setStroke(ls);
                graphics.draw(path);
            }
        }
        //draws a simple ellipse
        //private Ellipse2D.Double getShape(final NodeRealizer context) {
        private Ellipse2D.Double getShape(final A.I.$A context) {
            //final double w = context.getWidth();
            final double w = context.B();
            //final double h = context.getHeight();
            final double h = context.D();
            final double d = Math.min(w*2/3, h);
            //final double x = context.getX();
            final double x = context.C();
            //final double y = context.getY()+(h-d)/2;
            final double y = context.A()+(h-d)/2;

            final Ellipse2D.Double path = new Ellipse2D.Double();
            path.setFrame(x, y, w, d);
            return path;
        }

        //public boolean contains(
        public boolean B(
                //final NodeRealizer context,
                final A.I.$A context,
                final double x,
                final double y
                        ) {
            final Ellipse2D.Double path = getShape(context);
            return path.contains(x, y);
        }


    }
    //Resource
    public static class ResourceNodePainter
            extends A.I.F
            implements A.I.FA._K {


        protected void B(
                final A.I.$A context,
                final Graphics2D graphics,
                final boolean sloppy
                        ) {
            final Rectangle2D.Double rect = getShape(context);



            final Color fc = D(context, false);
            if (fc != null) {
                graphics.setColor(fc);
                graphics.fill(rect);
            }

            final Color lc = A(context, false);
            final Stroke ls = C(context, false);
            if (lc != null && ls != null) {
                graphics.setColor(lc);
                graphics.setStroke(ls);
                graphics.draw(rect);
            }
        }
        //simple rectangle
        private Rectangle2D.Double getShape(final A.I.$A context) {
            final double w = context.B();
            final double h = context.D();
            final double d = Math.min(w*2/3, h);

            final Rectangle2D.Double rect = new Rectangle2D.Double();
            rect.setFrame(context.C(), context.A()+(h-d)/2, w, d);
            return rect;
        }

        public boolean B(
                final A.I.$A context,
                final double x,
                final double y
                        ) {
		/*	final double w = context.B();
			final double h = context.D();
			final double d = Math.min(w*2/3, h);*/
            final Rectangle2D.Double rect = getShape(context);
            return rect.contains(x, y);
        }


    }
    //Softgoal
    public static class SoftGoalNodePainter
            extends A.I.F
            implements A.I.FA._K {

        protected void B(
                final A.I.$A context,
                final Graphics2D graphics,
                final boolean sloppy
                        ) {
            final Path2D.Double path = getShape(context);



            final Color fc = D(context, false);
            if (fc != null) {
                graphics.setColor(fc);
                graphics.fill(path);
            }

            final Color lc = A(context, false);
            final Stroke ls = C(context, false);
            if (lc != null && ls != null) {
                graphics.setColor(lc);
                graphics.setStroke(ls);
                graphics.draw(path);
            }
        }
        //complicated shape with bezier curves
        private Path2D.Double getShape(final A.I.$A context) {
            final double w = context.B();
            final double h = context.D();
            final double d = Math.min(w*2/3, h);
            final double x = context.C();
            final double y = context.A()+(h-d)/2;

            final Path2D.Double path = new Path2D.Double();
            //mainly arbitrary values retrieved from trail&error experimentation
            CubicCurve2D.Double c1=new CubicCurve2D.Double(x+w/6, y+d/14, x+w/3, y-d/10, x+3*w/8, y+d/4, x+4*w/6, y+d/15);
            CubicCurve2D.Double c2=new CubicCurve2D.Double(x+4*w/6, y+d/15, x+w*1.1, y-d/20, x+w*0.9, y+d*1.1, x+w*0.65, y+7*d/8);
            CubicCurve2D.Double c3=new CubicCurve2D.Double(x+w*0.65, y+7*d/8, x+w*0.5, y+d*0.7, x+w*0.3, y+d*0.8, x+w*0.3, y+d*0.8);
            CubicCurve2D.Double c4=new CubicCurve2D.Double(x+w*0.3, y+d*0.8, x+w*0.1, y+d*0.9, x, y+d/3, x+w/6, y+d/14);
            path.append(c1, true);
            path.append(c2, true);
            path.append(c3, true);
            path.append(c4, true);
            path.closePath();
            return path;
        }

        public boolean B(
                final A.I.$A context,
                final double x,
                final double y
                        ) {
            final Path2D.Double path = getShape(context);
            return path.contains(x, y);
        }


    }
    //Goal
    public static class GoalNodePainter
            extends A.I.F
            implements A.I.FA._K {

        protected void B(
                final A.I.$A context,
                final Graphics2D graphics,
                final boolean sloppy
                        ) {
            final Path2D.Double path = getShape(context);



            final Color fc = D(context, false);
            if (fc != null) {
                graphics.setColor(fc);
                graphics.fill(path);
            }

            final Color lc = A(context, false);
            final Stroke ls = C(context, false);
            if (lc != null && ls != null) {
                graphics.setColor(lc);
                graphics.setStroke(ls);
                graphics.draw(path);
            }
        }
        //two open arcs on left and right, connected
        private Path2D.Double getShape(final A.I.$A context) {
            final double w = context.B();
            final double h = context.D();
            final double d = Math.min(w*2/3, h);
            final double x = context.C();
            final double y = context.A()+(h-d)/2;


            final Path2D.Double path = new Path2D.Double();
            Arc2D.Double arcLeft = new Arc2D.Double(x, y, d, d, 90, 180, Arc2D.OPEN);
            path.append(arcLeft, true);
            Arc2D.Double arcRight = new Arc2D.Double(x+d/2, y, d, d, -90, 180, Arc2D.OPEN);
            path.append(arcRight, true);
            path.closePath();
            return path;
        }

        public boolean B(
                final A.I.$A context,
                final double x,
                final double y
                        ) {
            final Path2D.Double path = getShape(context);
            return path.contains(x, y);
        }


    }
    //Task
    public static class TaskNodePainter
            extends A.I.F
            implements A.I.FA._K {


        protected void B(
                final A.I.$A context,
                final Graphics2D graphics,
                final boolean sloppy
                        ) {
            final Path2D.Double path = getShape(context);

            final Color fc = D(context, false);
            if (fc != null) {
                graphics.setColor(fc);
                graphics.fill(path);
            }

            final Color lc = A(context, false);
            final Stroke ls = C(context, false);
            if (lc != null && ls != null) {
                graphics.setColor(lc);
                graphics.setStroke(ls);
                graphics.draw(path);
            }
        }
        //Set of multiple lines
        private Path2D.Double getShape(final A.I.$A context) {
            final Path2D.Double path = new Path2D.Double();
            final double w = context.B();
            final double h = context.D();
            final double d = Math.min(w*2/3, h);
            final double x = context.C();
            final double y = context.A()+(h-d)/2;

            path.moveTo(x, y+d/2);
            path.lineTo(x+w/6, y);
            path.lineTo(x+5*w/6, y);
            path.lineTo(x+w, y+d/2);
            path.lineTo(x+5*w/6, y+d);
            path.lineTo(x+w/6, y+d);
            path.closePath();
            return path;
        }

        public boolean B(
                final A.I.$A context,
                final double x,
                final double y
                        ) {


            final Path2D.Double path = getShape(context);
            return path.contains(x, y);
        }


    }
    //Position
    public static class PositionNodePainter
            extends A.I.F
            implements A.I.FA._K {
        Path2D.Double shape=new Path2D.Double();
        private final Arc2D.Double N = new Arc2D.Double();
        private final Arc2D.Double E = new Arc2D.Double();
        private final Arc2D.Double S = new Arc2D.Double();
        private final Arc2D.Double W = new Arc2D.Double();


        protected void B(
                final A.I.$A context,
                final Graphics2D graphics,
                final boolean sloppy
                        ) {
            final double w = context.B();
            final double h = context.D();
            final double d = Math.min(w, h);
            final double x=context.C();
            final double y=context.A();

            //four connected arcs
            shape = new Path2D.Double();
            N.setArc(x+d/4, y, d/2, d/2, 0, 180, Arc2D.OPEN);
            E.setArc(x+d/2, y+d/4, d/2, d/2, 270, 180, Arc2D.OPEN);
            S.setArc(x+d/4, y+d/2, d/2, d/2, 180, 180, Arc2D.OPEN);
            W.setArc(x, y+d/4, d/2, d/2, -270, 180, Arc2D.OPEN);

            shape.append(N, false);
            shape.append(E, false);
            shape.append(S, false);
            shape.append(W, false);
            //shape.closePath();

            final Color fc = D(context, false);
            if (fc != null) {
                graphics.setColor(fc);

                graphics.fill(new Ellipse2D.Double(x+d/8,y+d/8,6*d/8,6*d/8));//fill empty hole in center
                graphics.fill(shape);

            }

            final Color lc = A(context, false);
            final Stroke ls = C(context, false);
            if (lc != null && ls != null) {
                graphics.setColor(lc);
                graphics.setStroke(ls);
                graphics.draw(shape);
            }
        }

        private Rectangle2D.Double rect;
        private Ellipse2D.Double elW;
        private Ellipse2D.Double elE;
        private Ellipse2D.Double elN;
        private Ellipse2D.Double elS;
        public boolean B(
                final A.I.$A context,
                final double x,
                final double y
                        ) {
            final double w = context.B();
            final double h = context.D();
            final double tx = context.C() + w * 0.5 - x;
            final double ty = context.A() + h * 0.5 - y;
            final double d = Math.min(w, h);


            if(Math.sqrt(tx*tx + ty*ty) <= Math.min(w, h) * 0.5)//circle approx test
            {
                rect = new Rectangle2D.Double(context.C()+d/4, context.A()+d/4, d/2, d/2);
                elW = new Ellipse2D.Double(context.C(), context.A()+d/4, d/2, d/2);
                elN = new Ellipse2D.Double(context.C()+d/4, context.A(), d/2, d/2);
                elE = new Ellipse2D.Double(context.C()+d/2, context.A()+d/4, d/2, d/2);
                elS = new Ellipse2D.Double(context.C()+d/4, context.A()+d/2, d/2, d/2);



                if(rect.contains(x, y))
                {
                    return true;
                }
                if(elN.contains(x, y))
                {
                    return true;
                }
                if(elW.contains(x, y))
                {
                    return true;
                }
                if(elS.contains(x, y))
                {
                    return true;
                }
                if(elE.contains(x, y))
                {
                    return true;
                }
            }
            return false;




        }


    }
    //Circle (basis for Actor, Agent and Role), taken from yFiles demo examples
    public static class CircleNodePainter
            extends A.I.F
            implements A.I.FA._K {
        private final Ellipse2D.Double circle = new Ellipse2D.Double();

        protected void B(
                final A.I.$A context,
                final Graphics2D graphics,
                final boolean sloppy
                        ) {
            final double w = context.B();
            final double h = context.D();
            final double d = Math.min(w, h);
            circle.setFrame(
                    context.C() + (w - d) * 0.5,
                    context.A() + (h - d) * 0.5, d, d);


            final Color fc = D(context, false);
            if (fc != null) {
                graphics.setColor(fc);
                graphics.fill(circle);
            }

            final Color lc = A(context, false);
            final Stroke ls = C(context, false);
            if (lc != null && ls != null) {
                graphics.setColor(lc);
                graphics.setStroke(ls);
                graphics.draw(circle);
            }
        }

        public boolean B(
                final A.I.$A context,
                final double x,
                final double y
                        ) {
            final double w = context.B();
            final double h = context.D();
            final double tx = context.C() + w * 0.5 - x;
            final double ty = context.A() + h * 0.5 - y;
            return Math.sqrt(tx*tx + ty*ty) <= Math.min(w, h) * 0.5;
        }


    }
    //Actor
    public static class ActorNodePainter extends CircleNodePainter
    {
    }
    //Agent
    public static class AgentNodePainter extends CircleNodePainter
    {
        private final Line2D.Double line = new Line2D.Double();
        @Override
        protected void B(
                final A.I.$A context,
                final Graphics2D graphics,
                final boolean sloppy
                        )
        {
            super.B(context, graphics, sloppy);
            final double w = context.B();
            final double h = context.D();
            final double d = Math.min(w, h);

            final double tx = context.C() + w * 0.5;
            final double ty = context.A() + h * 0.5;

            //trigonometric magic
            double angle=Math.PI/6; //30°
            double x1=tx-Math.cos(angle)*d*0.5;
            double y1=ty-Math.sin(angle)*d*0.5;
            double x2=tx+Math.cos(angle)*d*0.5;
            double y2=y1;
            line.setLine(x1, y1, x2, y2);

            final Color lc = A(context, false);
            final Stroke ls = C(context, false);
            if (lc != null && ls != null) {
                graphics.setColor(lc);
                graphics.setStroke(ls);
                graphics.draw(line);
            }
        }
    }
    //Role
    public static class RoleNodePainter extends CircleNodePainter
    {
        private final QuadCurve2D.Double curve = new QuadCurve2D.Double();
        @Override
        protected void B(
                final A.I.$A context,
                final Graphics2D graphics,
                final boolean sloppy
                        )
        {
            super.B(context, graphics, sloppy);
            final double w = context.B();
            final double h = context.D();
            final double d = Math.min(w, h);

            final double tx = context.C() + w * 0.5;
            final double ty = context.A() + h * 0.5;

            //trigonometric magic
            double angle=Math.PI/6; //30°
            double x1=tx-Math.cos(angle)*d*0.5;
            double y1=ty+Math.sin(angle)*d*0.5;
            double x2=tx+Math.cos(angle)*d*0.5;
            double y2=y1;
            double ctrlx=tx;
            double ctrly=y2-(context.A()+d*0.5-y2);//some arbitrary control point at the bottom
            curve.setCurve(x1, y1, ctrlx, ctrly, x2, y2);

            final Color lc = A(context, false);
            final Stroke ls = C(context, false);
            if (lc != null && ls != null) {
                graphics.setColor(lc);
                graphics.setStroke(ls);
                graphics.draw(curve);
            }
        }
    }
}
