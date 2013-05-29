package immt.algorithms;

import ij.ImagePlus;

public class MeanFilter extends Algorithm {

    public MeanFilter(){
        super("Mean Filter", "this is the description");
    }
    
    @Override
    public void run(ImagePlus image) {
        System.out.println("corri el algoritmo");
        setResultingImage(image);
    }

}
