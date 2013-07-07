package immt.algorithms;

import ij.IJ;
import ij.ImagePlus;
import immt.ui.ShellWindow;

public class MeanFilter extends Algorithm {

    /**
     * Radio to take into account to calculate the mean.
     */
    private int radio;

    public MeanFilter(ShellWindow parent) {
        super("Mean Filter", "The Mean Filter is a simple one, and does \n"
                + "not remove the speckles but averages it into the data. Generally \n"
                + "speaking, this is the least satisfactory method of speckle noise \n"
                + "reduction as it results in loss of detail and resolution. However, \n"
                + "it can be used for applications where resolution is not the first \n"
                + "concern.", parent);
    }

    @Override
    public void runAlgorithm() {
        ImagePlus result = getOriginalImage().duplicate();
        IJ.run(result, "Mean...", "radius=" + radio);
        setResultingImage(result);
    }

    public void setRadio(int radio) {
        this.radio = radio;
    }

    @Override
    public Algorithm clone() {
        MeanFilter newAlgorithm = new MeanFilter(getParent());
        newAlgorithm.setRadio(radio);
        newAlgorithm.setOriginalImage(getOriginalImage());
        return newAlgorithm;
    }
}
