package immt.algorithms;

import ij.ImagePlus;

public abstract class Algorithm {

    private String name;
    private String description;
    private ImagePlus resultingImage;
    
    /**
     * Constructor for an Algorithm.
     * Keep in mind that this is an abstract class, you 
     * need to extend from here into your own algorithm.
     * @param name name of the Algorithm.
     * @param description  simple description of what the Algorithm does.
     */
    public Algorithm(String name, String description){
        this.name = name;
        this.description = description;
    }
    
     /**
     * Runs the algorithm, and sets the resulting image.
     * @param image the image to process.
     */
    public abstract void run(ImagePlus image);
    
    
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
    
    @Override
    public String toString() {
        return name;
    }
    

}
