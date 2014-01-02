package immt.edge;

import ij.ImagePlus;
import immt.ui.ShellWindow;
import java.util.concurrent.ExecutionException;
import javax.swing.SwingWorker;

public abstract class EdgeOperator extends SwingWorker<Boolean, String> {

    protected ShellWindow parent;
    protected ImagePlus resultingImage;
    private String name;

    public EdgeOperator(String name, ShellWindow parent) {
        this.name = name;
        this.parent = parent;
    }
    
    public ImagePlus getResultingImage(){
        return resultingImage;
    }
    
    public String getName() {
        return name;
    }

    public abstract void runOperator();

    /**
     * Computes a result in a new Thread Throws an exception if unable to do so.
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
    
    @Override
    public String toString(){
        return getName();
    }
    
    @Override
    public abstract EdgeOperator clone();          

}
