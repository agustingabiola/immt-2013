package immt.ui;

import ij.ImagePlus;
import ij.io.FileSaver;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;

/**
 * Panel to paint an image of type ImagePlus
 */
public class ImagePanel extends JPanel implements MouseListener, MouseMotionListener, KeyListener {

    // The image displayed in the panel
    private ImagePlus image;

    // Rectangle representing the selected ROI
    private Rectangle selectedRoi;

    // Variables to draw the ROI rectangle in the panel
    int currentX, currentY, startX, startY;
    boolean dragMode = false;

    private boolean isControlPressed = false;
    
    private ShellWindow parent;
    
    ArrayList<Point> points;
    
    /**
     * *
     * Constructor for the ImagePanel, with a default image
     * @param parent
     */
    public ImagePanel(ShellWindow parent) {
        addMouseListener(new RightClickListener(this));
        addMouseListener(this);
        addMouseMotionListener(this);
        addKeyListener(this);
        this.parent = parent;
        points = new ArrayList<Point>();
         toPaintPoints = new ArrayList<immt.util.Point>();
    }

        public ImagePanel() {
        addMouseListener(new RightClickListener(this));
        addMouseListener(this);
        addMouseMotionListener(this);
        addKeyListener(this);
         points = new ArrayList<Point>();
          toPaintPoints = new ArrayList<immt.util.Point>();
    }
    
    /**
     * *
     * Constructor for the ImagePanel, with a default image
     *
     * @param image image to be shown inside panel
     */
    public ImagePanel(ImagePlus image, ShellWindow parent) {
        addMouseListener(new RightClickListener(this));
        addMouseListener(this);
        addMouseMotionListener(this);        
        addKeyListener(this);
        this.image = image;
        this.parent = parent;
         points = new ArrayList<Point>();
         toPaintPoints = new ArrayList<immt.util.Point>();
    }

    
    
    /**
     * *
     * Save the image as an BMP
     */
    public void saveImage() {
        // We create a FileSaver, implemented in ImageJ
        FileSaver fs = new FileSaver(image);

        // Save the image as .BMP
        fs.saveAsBmp();
    }
    
    public void saveImage(String path){
        // We create a FileSaver, implemented in ImageJ
        FileSaver fs = new FileSaver(image);

        // Save the image as .BMP
        fs.saveAsBmp(path + ".bmp");
    }
    
    public void resetPanel()
    {
        image = null;
        selectedRoi = null;
        top = 0;
        left = 0;
        ResetAllPoints();
    }

    public void ResetAllPoints()
    {
        ClearPoints();
        points = new ArrayList<Point>();
        toPaintPoints = new ArrayList<immt.util.Point>();
    }
    
    
    /**
     * *
     * Get the image of the panel
     *
     * @return the image of the panel
     */
    public ImagePlus getImage() {
        return image;
    }

    /**
     * *
     * Set the image of the panel
     *
     * @param image image to be shown in the panel
     */
    public void setImage(final ImagePlus image) {
        this.image = image;
    }

    /**
     * *
     * Returns the selected ROI of the image
     *
     * @return the selected ROI
     */
    public Rectangle getSelectedRoi() {
        return selectedRoi;
    }

    public void resetRoi()
    {
        selectedRoi = null;
    }
    
    boolean paintMappedPoints = false;
    
    ArrayList<immt.util.Point> pointsMapped;
    
    
    public void paintPoints(ArrayList<immt.util.Point> pointsMapped)
    {      
        paintMappedPoints = true;
        this.pointsMapped = pointsMapped;
        repaint();
    }
    
    private ArrayList<immt.util.Point> toPaintPoints;
    
    public void PaintPoint(double x, double y)
    {
        toPaintPoints.add(new immt.util.Point(x, y));
        repaint();
    }
    
    int left = 0;
    int top = 0;
    
    public void setImageWithRoi(ImagePlus img, Rectangle roi)
    {
        left = 400 - (roi.width / 2) + 1;
        top = 327 - (roi.height / 2) + 1; 
        image = img;          
    }
    
    public int getYofPoints (){
        if (point1==null & point2==null)
            return top-5;
        if ((point1.y-top)< (point2.y-top))
            return point1.y-top;
        return point2.y-top;
    }    
    
    /**
     * *
     * Paints the image inside the panel
     *
     * @param g
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (image != null) {              
            g.drawImage(image.getBufferedImage(), left, top, image.getWidth(), image.getHeight(), this);
            
       if(toPaintPoints != null)
            for(immt.util.Point p: toPaintPoints)
            {
                g.setColor(Color.red);
                g.fillRect((int)p.getxCoord(), (int)p.getyCoord(), 1, 1);
            }
            
            for(Point p : points)
            {                
                g.setColor(Color.BLUE);
                g.fillRect((int) p.getX(), (int) p.getY(), 4, 4);
            }
            
            if(paintMappedPoints)
            {
                paintMappedPoints = false;
                g.setColor(Color.PINK);
                for(immt.util.Point p: pointsMapped)
                    g.fillRect((int) p.getxCoord(),(int) p.getyCoord(), 4, 4);
            }
                        
            int beginX, beginY, width, height;
            
            if (dragMode == true) {
                beginX = Math.min(startX, currentX);
                beginY = Math.min(startY, currentY);
                width = Math.abs(currentX - startX);
                height = Math.abs(currentY - startY);
                g.setColor(Color.YELLOW);
                g.drawRect(beginX, beginY, width, height);
                selectedRoi = new Rectangle(new Point(startX, startY), new Dimension(width, height));  
                MinXRoi = startX;
            }
            if(point1 != null)
            {
                g.setColor(Color.YELLOW);
                g.fillRect((int) point1.getX(), (int) point1.getY(), 4, 4);
                if(!points.contains(point1))
                    points.add(point1);               
            }
            if(point2 != null)
            {
                g.setColor(Color.YELLOW);
                g.fillRect((int) point2.getX(), (int) point2.getY(), 4, 4);
                points.add(point2);
                distanceBetweenPoints();
            }                 
            
            this.requestFocus();
        }
    }

    public void ClearPoints()
    {
        point1 = null;
        point2 = null; 
        repaint();
    }
    
    private void distanceBetweenPoints()
    {
       double distance = Math.sqrt(Math.pow(point2.x - point1.x, 2) + Math.pow(point2.y - point1.y, 2));
    }
    
    @Override
    public void mouseDragged(MouseEvent me) {
        if (image != null) {
            if (dragMode == true) {
                currentX = me.getX();
                currentY = me.getY();
                repaint();
            }
        }
    }

    private Point point1;
    private Point point2;
    
    public int MinXRoi;
    
    public ArrayList<Point> GetPoints()
    {
        return points;
    }
    
    public Point GetPoint1()
    {
        return point1;
    }
    
    public Point GetPoint2()
    {
        return point2;
    }
    
    @Override
    public void mousePressed(MouseEvent me) {
        if (image != null) {
            if(isControlPressed)
            {
                if(point1 == null)
                {
                    point1 = new Point(me.getX(), me.getY());
                    repaint();
                }
                else if (point2 == null)
                {
                    point2 = new Point(me.getX(), me.getY());
                    repaint();                
                }
            }
            else
            {
                dragMode = true;
                startX = me.getX();
                startY = me.getY();
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent me) {
        dragMode = false;
    }

    @Override
    public void mouseClicked(MouseEvent me) {

    }

    @Override
    public void mouseEntered(MouseEvent me) {
    }

    @Override
    public void mouseExited(MouseEvent me) {
    }

    @Override
    public void mouseMoved(MouseEvent me) {
    }

    @Override
    public void keyTyped(KeyEvent e) {
        }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode()==KeyEvent.VK_CONTROL)
            isControlPressed = true;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if(e.getKeyCode()==KeyEvent.VK_CONTROL)
            isControlPressed = false;
    }
}
