package immt.util;

import ij.ImagePlus;

public class Compare {
    
    public static double pearsonCorrelation(ImagePlus image1, ImagePlus image2){
        int width1 = image1.getWidth();
        int width2 = image2.getWidth();
        int heigth1 = image1.getHeight();
        int heigth2 = image2.getHeight();
        
        if(width1 != width2 || heigth1 != heigth2){
            System.out.println("Dimensions dont match!");
            return -999;
        }
        
        double mean1 = Functions.MeanOfPixels((float[])image1.getProcessor().getPixels(), width1, heigth1);
        image2.setProcessor(image2.getProcessor().convertToFloat());    
        double mean2 = Functions.MeanOfPixels((float[])image2.getProcessor().getPixels(), width2, heigth2);
        
        double topParam = 0;
        double bottomLeft = 0;
        double bottomRight = 0;
        
        for(int i = 0; i < width1; i++){
            for(int j = 0; j < width1; j++){
                topParam += (image1.getPixel(j, j)[0] - mean1) * (image2.getPixel(j, j)[0] - mean2);
                bottomLeft += Math.pow(image1.getPixel(j, j)[0] - mean1, 2);
                bottomRight += Math.pow(image2.getPixel(j, j)[0] - mean2, 2);
            }
        }
        
        return topParam / (Math.sqrt(bottomLeft) * Math.sqrt(bottomRight));
    }
}
