package immt.algorithms;

import ij.IJ;
import ij.ImagePlus;

public class MeanFilter extends Algorithm {

    /*
     * Radio to take into account to calculate the mean.
     */
    private int radio;
    
    public MeanFilter(){
        super("Mean Filter", "this is the description");
    }
    
    public void setRadio(int radio){
        this.radio = radio;
    }
    
    @Override
    public void run(ImagePlus image) {
        ImagePlus result = image.duplicate();
        IJ.run(result, "Mean...", "radius="+radio);
        setResultingImage(result);
    }

}
