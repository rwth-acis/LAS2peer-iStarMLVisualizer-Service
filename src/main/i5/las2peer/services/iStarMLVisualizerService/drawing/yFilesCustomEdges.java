package i5.las2peer.services.iStarMLVisualizerService.drawing;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.GeneralPath;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

//import y.view.Arrow;
//import A.I.D;

//import y.view.Bend;
//import A.I.IB;

//import y.view.BendList;
//import A.I.U;

//import y.view.EdgeRealizer;
//import A.I.o;

//import y.view.GenericEdgePainter;
//import A.I.KB;

//import y.view.GenericEdgeRealizer;
//import A.I.p;

/**
 * Stores all custom edge types used in the graph generation process
 * classes are cloned and modified versions of the yFiles demo classes
 * @author Alexander
 *
 */
public class yFilesCustomEdges
{
    //Dependency
    //public static final class DependencyArrowPainter implements GenericEdgeRealizer.ArrowPainter {
    public static final class DependencyArrowPainter implements A.I.p._B {
        //Arrow targetArrow;
        A.I.D targetArrow;

        public DependencyArrowPainter(String arrow) {
            //targetArrow=Arrow.getCustomArrow(arrow); //D arrow
            targetArrow=A.I.D.A(arrow); //D arrow
        }
        //draws arrow in center
        //public void paintArrows(EdgeRealizer context, BendList bends, GeneralPath path, Graphics2D gfx) {
        public void A(A.I.o context, A.I.U bends, GeneralPath path, Graphics2D gfx) {
            if (targetArrow != null) {

                //Point2D sourceIntersection = context.getSourceIntersection();
                Point2D sourceIntersection = context.Ê();
                //Point2D targetIntersection = context.getTargetIntersection();
                Point2D targetIntersection = context.ß();

                if (bends.size() > 0) {
                    int mid = bends.size() / 2;
                    if (mid > 0) {
                        //Bend bend = context.getBend(mid - 1);
                        A.I.IB bend = context.H(mid - 1);
                        //sourceIntersection.setLocation(bend.getX(), bend.getY());
                        sourceIntersection.setLocation(bend.B(), bend.E());
                    }
                    {
                        //Bend bend = context.getBend(mid);
                        A.I.IB bend = context.H(mid);
                        //targetIntersection.setLocation(bend.getX(), bend.getY());
                        targetIntersection.setLocation(bend.B(), bend.E());
                    }
                }

                double centerX = (targetIntersection.getX() + sourceIntersection.getX()) * 0.5d;
                double centerY = (targetIntersection.getY() + sourceIntersection.getY()) * 0.5d;
                double dx = (targetIntersection.getX() - sourceIntersection.getX());
                double dy = (targetIntersection.getY() - sourceIntersection.getY());
                double l = Math.sqrt(dx * dx + dy * dy);
                //double arrowScaleFactor = context.getArrowScaleFactor();
                double arrowScaleFactor = context.Æ();
                if (l > 0) {
                    //targetArrow.paint(gfx, centerX, centerY, arrowScaleFactor * dx / l, arrowScaleFactor * dy / l);
                    targetArrow.A(gfx, centerX, centerY, arrowScaleFactor * dx / l, arrowScaleFactor * dy / l);
                }
            }
        }
    }
    //draws text in center
    //static final class SignatureEdgePainter extends GenericEdgePainter {
    public static final class SignatureEdgePainter extends A.I.KB {
        private static final int DEFAULTLINKFONTSIZE = 20;
        private String signature;

        /**
         * Constructor
         * @param signature text to draw on edge
         */
        public SignatureEdgePainter(String signature) {
            this.signature = signature;
        }

        //protected void paintPath(EdgeRealizer context, BendList bends, GeneralPath path, Graphics2D gfx, boolean selected) {
        protected void F(A.I.o context, A.I.U bends, GeneralPath path, Graphics2D gfx, boolean selected) {
            // Draw path "normally"
            super.F(context, bends, path, gfx, selected);

            //Point2D sourceIntersection = context.getSourceIntersection();
            Point2D sourceIntersection = context.Ê();
            //Point2D targetIntersection = context.getTargetIntersection();
            Point2D targetIntersection = context.ß();

            if (bends.size() > 0) {
                int mid = bends.size() / 2;
                if (mid > 0) {
                    //Bend bend = context.getBend(mid - 1);
                    A.I.IB bend = context.H(mid - 1);
                    //sourceIntersection.setLocation(bend.getX(), bend.getY());
                    sourceIntersection.setLocation(bend.B(), bend.E());
                }
                {
                    //Bend bend = context.getBend(mid);
                    A.I.IB bend = context.H(mid);
                    //targetIntersection.setLocation(bend.getX(), bend.getY());
                    targetIntersection.setLocation(bend.B(), bend.E());
                }
            }

            double centerX = (targetIntersection.getX() + sourceIntersection.getX()) * 0.5d;
            double centerY = (targetIntersection.getY() + sourceIntersection.getY()) * 0.5d;
            double dx = (targetIntersection.getX() - sourceIntersection.getX());
            double dy = (targetIntersection.getY() - sourceIntersection.getY());

            double theta = Math.atan2(dy, dx);
            AffineTransform oldTransform = gfx.getTransform();
            AffineTransform newTransform = gfx.getTransform();
            newTransform.rotate(theta, centerX, centerY);
            gfx.setTransform(newTransform);
            Font newFont = new Font("Tahoma",Font.PLAIN,DEFAULTLINKFONTSIZE);
            Font oldFont=gfx.getFont();
            gfx.setFont(newFont);
            Color oldColor=gfx.getColor();
            gfx.setColor(Color.WHITE);

            FontMetrics fm = gfx.getFontMetrics();
            Rectangle2D r = fm.getStringBounds(signature+" ", gfx);

            //creates a white background before drawing the text (for readability)
            gfx.fillRect((int)(centerX-r.getCenterX()), (int)(centerY+r.getCenterY()), (int)r.getWidth(), (int)r.getHeight());
            gfx.setColor(oldColor);
            gfx.drawString(signature, (float) (centerX-r.getCenterX()), (float) (centerY-r.getCenterY()));


            gfx.setFont(oldFont);
            gfx.setTransform(oldTransform);
        }
    }
}
