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
        super("Mean Filter", "this is the description", parent);
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
}
