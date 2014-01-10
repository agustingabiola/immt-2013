package immt.edge;

import ij.ImagePlus;
import immt.ui.ImagePanel;
import immt.ui.ShellWindow;
import java.awt.Rectangle;
import java.util.concurrent.ExecutionException;
import javax.swing.SwingWorker;

public abstract class EdgeOperator extends SwingWorker<Boolean, String> {

    // Main window of the application
    protected ShellWindow parent;

    // The resulting image of applying the EdgeOperator
    protected ImagePlus resultingImage;

    // Name of the edge operator
    private final String name;

    /**
     * *
     * Constructor of the EdgeOperator
     *
     * @param name name of the EdgeOperator
     * @param parent main window of the application
     */
    public EdgeOperator(String name, ShellWindow parent) {
        this.name = name;
        this.parent = parent;
    }

    /**
     * *
     * Used to get the resulting image, once the algorithm is run
     *
     * @return the resulting image of applying the EdgeOperator
     */
    public ImagePlus getResultingImage() {
        return resultingImage;
    }

    /**
     * *
     * Used to get the name of the EdgeOperator
     *
     * @return name of the EdgeOperator
     */
    public String getName() {
        return name;
    }

    /**
     * *
     * Abstract method where the logic of the EdgeOperator is implemented
     */
    public abstract void runOperator();

    /**
     * *
     * Returns a clone of the EdgeOperator
     *
     * @return clone of the EdgeOperator
     */
    @Override
    public abstract EdgeOperator clone();

    /**
     * Computes a result in a new Thread Throws an exception if unable to do so.
     *
     * @return true if it was successfully run
     */
    @Override
    protected Boolean doInBackground() {
        this.runOperator();
        return true;
    }

    /**
     * Called when the doInBackground is finished. You can safely update the GUI
     * from this method
     */
    @Override
    protected void done() {
        try {
            // Waits until doInBackground is finished
            get();
            setProgress(100);
            parent.setStatus("Completed with " + getName());
            parent.createEdgeResultWindow(this);
        } catch (InterruptedException | ExecutionException e) {
            // TODO: Code here after the SW was cancelled
        }
    }

    /**
     * *
     * Override method of the toString, to return the name of the EdgeOperator
     *
     * @return the name of the EdgeOperator
     */
    @Override
    public String toString() {
        return getName();
    }

    /**
     * *
     * Gets the image to be processed from the parent window
     *
     * @return the image to which apply the EdgeOperator
     */
    public ImagePlus getImageToProcess() {
        ImagePanel panel = parent.getCurrentImagePanelSelected();
        Rectangle roi = panel.getSelectedRoi();
        // if there is no ROI, just return the image
        if (roi == null) {
            return panel.getImage();
        } else {
            ImagePlus image = panel.getImage();
            image.setRoi(roi);
            return new ImagePlus("", image.getProcessor().crop());
        }
    }
}
