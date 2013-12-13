package immt.algorithms;

import ij.IJ;
import ij.ImagePlus;
import immt.ui.ShellWindow;
import java.awt.Rectangle;

public class MeanFilter extends Algorithm {

    private int windowSize;
    
    /***
     * Mean Filter
     * @param parent The main window of the application 
     */
    public MeanFilter(ShellWindow parent) {
        super("Media", "Se le asigna al píxel central la media de todos los píxeles incluidos en la ventana", parent);                
    }    
    
    /***
     * Set the size of the window. It will be a window of [size, size]
     * @param size size of the window
     */
    public void setWindowSize(int size){
        windowSize = size;
    }
    
    /***
     * Get the size of the window
     * @return size of the window
     */
    public int getWindowSize(){
        return windowSize;
    }            
    
    /***
     * Runs the mean filter
     * @param roi the selected Region of interest. If null, the whole image will be processed
     */
    @Override
    public void runAlgorithm(Rectangle roi) {
        ImagePlus result = getOriginalImage().duplicate();
        IJ.run(result, "Mean...", "radius=" + windowSize);
        setResultingImage(result);
    }
    
    /***
     * Returns a copy of the Mean Algorithm
     * @return copy of the Mean Filter algorithm
     */
    @Override
    public Algorithm clone() {
        MeanFilter newAlgorithm = new MeanFilter(getParent());
        newAlgorithm.setWindowSize(windowSize);
        newAlgorithm.setOriginalImage(getOriginalImage());
        return newAlgorithm;
    }
    
}
