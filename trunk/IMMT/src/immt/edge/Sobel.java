package immt.edge;

import ij.ImagePlus;
import immt.algorithms.Algorithm;
import immt.ui.ShellWindow;
import immt.ui.parameters.SobelParams;

public class Sobel extends Algorithm {

    int sobel_x[][] = {{-1,0,1},
                       {-2,0,2},
                       {-1,0,1}};

    int sobel_y[][] = {{-1,-2,-1},
                       { 0, 0, 0},
                       { 1, 2, 1}};
    
    /**
     * *
     * Sobel Constructor
     *
     * @param parent main window of the application
     */
    public Sobel(ShellWindow parent) {
         super("Sobel", "Sobel Description", parent);
    
    }
    
    public int calculatePixelValue(int i, int j,int[][] matrix, float[] pixels, int w ){
        int sobelI = 0;
        int sobelJ = 0;
        int pixelI, pixelJ;
        int sum = 0;
        int currentVal;
        for(pixelI = -1; pixelI <= 1; pixelI++){
            for(pixelJ = -1; pixelJ <= 1; pixelJ++){
                //System.out.println("[" + sobelI + "]" + "[" + sobelJ + "] --------   " + pixelI +"," + pixelJ);
                currentVal = (int) pixels[w * (j + pixelJ) + (i + pixelI)];
                sum += matrix[sobelI][sobelJ] * currentVal;    
                sobelI++;
            }
            sobelJ++;
            sobelI = 0;
        }
        return sum;
        
    }

    @Override
    public void runAlgorithm() {
        ImagePlus originalImage = getOriginalImage();
        originalImage.setProcessor(originalImage.getProcessor().convertToFloat());
        
        float[] pixelsCopy = (float[])originalImage.getProcessor().getPixelsCopy();
        
        int pixelX, pixelY;
        
        int width = originalImage.getWidth();
        int height = originalImage.getHeight();
        
        for (int i=1; i < width-2; i++){
            for (int j=1; j < height-2; j++){
                
                pixelX = calculatePixelValue(i,j,sobel_x, pixelsCopy, width);
                
                pixelY = calculatePixelValue(i,j,sobel_y, pixelsCopy, width);
                
                int val =  (int)Math.sqrt((pixelX * pixelX) + (pixelY * pixelY));
                
                if(val < 0)
                {
                   val = 0;
                }

                if(val > 255)
                {
                   val = 255;
                }

                originalImage.getProcessor().putPixelValue(i,j,val);
            }
        }       
        
        setResultingImage(originalImage);
    }

    @Override
    public void showAlgorithmOptions() {
        SobelParams p_MeanFilterParams = new SobelParams(parent, this);
        parent.showAlgorithmOption(p_MeanFilterParams);}

    @Override
    public Algorithm clone() {
         Sobel clone = new Sobel(parent);
         return clone;
    }

}
