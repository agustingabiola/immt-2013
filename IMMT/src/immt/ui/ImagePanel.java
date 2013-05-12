package immt.ui;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;

public class ImagePanel extends JPanel {
    
    private BufferedImage image;
    
    /**
    * Creates an ImagePanel which extends from JPanel
    */
    public ImagePanel(){
    
    }
    
    /**
     * Creates an ImagePanel which extends from JPanel
     * @param image Image to set inside the panel
     */
    public ImagePanel(BufferedImage image) {
        this.image = image;
    }
    
    /**
     * Returns the BufferedImage inside the panel
     * @return Return the buffered image
     */
    public BufferedImage getImage() {
        return image;
    }
    
    public void setImage(BufferedImage image){
        this.image = image; 
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        g.drawImage(image, 0, 0, getSize().width, getSize().height, this);
    }

}
