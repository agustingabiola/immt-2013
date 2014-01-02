package immt.edge;

import ij.ImagePlus;
import immt.ui.ShellWindow;

public class Sobel extends EdgeOperator {

    public Sobel(ShellWindow parent) {
        super("Sobel", parent);
    }
   
    
    @Override
    public void runOperator() {
        ImagePlus image = parent.getCurrentImageInTab();
        
        resultingImage = image;
    }

    @Override
    public EdgeOperator clone() {
        Sobel clone = new Sobel(parent);
        return clone;
    }
    
}
