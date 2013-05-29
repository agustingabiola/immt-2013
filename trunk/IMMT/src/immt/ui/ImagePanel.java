package immt.ui;

import ij.ImagePlus;
import java.awt.Graphics;
import javax.swing.JPanel;

public class ImagePanel extends JPanel {
    
    private ImagePlus image;
    

    public ImagePanel(){
    
    }
    
    public ImagePanel(ImagePlus image) {
        this.image = image;
    }

    public ImagePlus getImage() {
        return image;
    }
    
    public void setImage(ImagePlus image){
        this.image = image; 
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        if (image != null){
            g.drawImage(image.getBufferedImage(), 0, 0, getSize().width, getSize().height, this);
        }
    }

}
