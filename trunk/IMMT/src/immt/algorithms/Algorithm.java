package immt.algorithms;

import ij.ImagePlus;
import immt.ui.ShellWindow;
import java.util.concurrent.ExecutionException;
import javax.swing.SwingWorker;

public abstract class Algorithm extends SwingWorker<Boolean, String> {

    private String name;
    private String description;
    private ImagePlus resultingImage;
    private ImagePlus originalImage;
    private ShellWindow parent;

    /**
     * Constructor for an Algorithm. Keep in mind that this is an abstract
     * class, you need to extend from here into your own algorithm.
     *
     * @param name name of the Algorithm.
     * @param description simple description of what the Algorithm does.
     * @param parent parent frame. Needed to update status/progress
     */
    public Algorithm(String name, String description, ShellWindow parent) {
        this.name = name;
        this.description = description;
        this.parent = parent;
    }

    /**
     * Runs the algorithm based on the originalImage. After its completed, it
     * sets the resulting image.
     */
    public abstract void runAlgorithm();

    /**
     * Computes a result in a new Thread Throws an exception if unable to do so.
     */
    @Override
    protected Boolean doInBackground() {
        parent.setStatus("Running " + getName());
        this.runAlgorithm();
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
            parent.createNewTab(this);
        } catch (InterruptedException e) {
            // TODO: Code here after the SW was cancelled
        } catch (ExecutionException e) {
            // TODO: Log exception
        }
    }

    /**
     * Sets the originalImage to the parameters value. So the originalImage is
     * not modified, it makes a duplicate.
     * 
     * @param originalImage this is the image to process
     */
    public void setOriginalImage(ImagePlus originalImage) {
        this.originalImage = originalImage.duplicate();
    }

    public ImagePlus getOriginalImage() {
        return originalImage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ImagePlus getResultingImage() {
        return resultingImage;
    }

    public void setResultingImage(ImagePlus resultingImage) {
        this.resultingImage = resultingImage;
    }

    public ShellWindow getParent() {
        return parent;
    }

    public void setParent(ShellWindow parent) {
        this.parent = parent;
    }

    @Override
    public String toString() {
        return name;
    }
}
