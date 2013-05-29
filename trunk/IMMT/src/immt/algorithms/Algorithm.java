package immt.algorithms;

import ij.ImagePlus;
import java.awt.image.BufferedImage;

public abstract class Algorithm {

    private String name;
    private String description;
    private ImagePlus resultingImage;

    public Algorithm(String name, String description){
        this.name = name;
        this.description = description;
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

    @Override
    public String toString() {
        return name;
    }

    public ImagePlus getResultingImage() {
        return resultingImage;
    }

    public void setResultingImage(ImagePlus resultingImage) {
        this.resultingImage = resultingImage;
    }

    
    public abstract void run(ImagePlus image);
}
