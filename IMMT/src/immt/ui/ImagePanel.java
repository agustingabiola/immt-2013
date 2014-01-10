package immt.ui;

import ij.ImagePlus;
import ij.io.FileSaver;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.JPanel;

/**
 * Panel to paint an image of type ImagePlus
 */
public class ImagePanel extends JPanel implements MouseListener, MouseMotionListener {

    // The image displayed in the panel
    private ImagePlus image;

    // Rectangle representing the selected ROI
    private Rectangle selectedRoi;

    // Variables to draw the ROI rectangle in the panel
    int currentX, currentY, startX, startY;
    boolean dragMode = false;

    /**
     * *
     * Constructor for the ImagePanel, with a default image
     */
    public ImagePanel() {
        addMouseListener(new RightClickListener(this));
        addMouseListener(this);
        addMouseMotionListener(this);
    }

    /**
     * *
     * Constructor for the ImagePanel, with a default image
     *
     * @param image image to be shown inside panel
     */
    public ImagePanel(ImagePlus image) {
        addMouseListener(new RightClickListener(this));
        addMouseListener(this);
        addMouseMotionListener(this);
        this.image = image;
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

    /**
     * *
     * Paints the image inside the panel
     *
     * @param g
     */
    @Override
    protected void paintComponent(Graphics g) {
        if (image != null) {
            g.drawImage(image.getBufferedImage(), 0, 0, getSize().width, getSize().height, this);
            int beginX, beginY, width, height;
            if (dragMode == true) {
                beginX = Math.min(startX, currentX);
                beginY = Math.min(startY, currentY);
                width = Math.abs(currentX - startX);
                height = Math.abs(currentY - startY);
                g.setColor(Color.YELLOW);
                g.drawRect(beginX, beginY, width, height);
                selectedRoi = new Rectangle(new Point(startX, startY), new Dimension(width, height));
            }
        }
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

    @Override
    public void mousePressed(MouseEvent me) {
        if (image != null) {
            dragMode = true;
            startX = me.getX();
            startY = me.getY();
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
}
