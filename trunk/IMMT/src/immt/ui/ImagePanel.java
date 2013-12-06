package immt.ui;

import ij.ImagePlus;
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/**
 * Panel to paint an image of type Imageplus 
 */
public class ImagePanel extends JPanel implements MouseMotionListener, MouseListener {

    private ImagePlus image;
    private boolean isImageLoaded;
    private Point startPoint;
    private Rectangle selectedRoi;

    public ImagePanel() {
        addMouseListener(this);
        addMouseMotionListener(this);
    }

    public ImagePanel(ImagePlus image) {
        addMouseListener(this);
        addMouseMotionListener(this);
        this.image = image;
    }

    public ImagePlus getImage() {
        return image;
    }

    public void setImage(final ImagePlus image) {
        this.image = image;
        isImageLoaded = true;
               /* SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new ScreenCaptureRectangle(image.getBufferedImage());
            }
        });*/
    }

    @Override
    protected void paintComponent(Graphics g) {
        if (image != null) {
            g.drawImage(image.getBufferedImage(), 0, 0, getSize().width, getSize().height, this);
        }
    }

    @Override
    public void mouseDragged(MouseEvent me) {
        if(isImageLoaded){
            Point end = me.getPoint();
            selectedRoi = new Rectangle(startPoint,
                    new Dimension(end.x-startPoint.x, end.y-startPoint.y));
            
            //setOpaque(true); 
            //Graphics2D g2 = (Graphics2D)getGraphics();
            //g2.draw(selectedRoi);
            //g2.setColor(new Color(255,255,255,150));
            //g2.fill(selectedRoi);
            //paintComponent(g2);
            //repaint();
        }
    }


    @Override
    public void mouseMoved(MouseEvent me) {
        if(isImageLoaded){
            startPoint = me.getPoint(); 
            BufferedImage screen = getImage().getBufferedImage();
            BufferedImage screenCopy = new BufferedImage(
                screen.getWidth(),
                screen.getHeight(),
                screen.getType());            
        }
    }    
   

    @Override
    public void mouseClicked(MouseEvent me) {
    }

    @Override
    public void mousePressed(MouseEvent me) {
    }

    @Override
    public void mouseReleased(MouseEvent me) {
        repaint();
    }

    @Override
    public void mouseEntered(MouseEvent me) {
    }

    @Override
    public void mouseExited(MouseEvent me) {
    }
    
    
}
