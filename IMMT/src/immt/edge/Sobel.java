package immt.edge;

import ij.ImagePlus;
import immt.ui.ShellWindow;

public class Sobel extends EdgeOperator {

    /**
     * *
     * Sobel Constructor
     *
     * @param parent main window of the application
     */
    public Sobel(ShellWindow parent) {
        super("Sobel", parent);
    }

    /**
     * *
     * Runs the Sobel Operator
     */
    @Override
    public void runOperator() {
        ImagePlus image = getImageToProcess();

        resultingImage = image;
    }

    /**
     * *
     * Clones the EdgeOperator
     *
     * @return a clone of the Sobel Operator
     */
    @Override
    public EdgeOperator clone() {
        Sobel clone = new Sobel(parent);
        return clone;
    }

}
