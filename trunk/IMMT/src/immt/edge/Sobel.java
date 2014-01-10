package immt.edge;

import ij.ImagePlus;
import immt.ui.ShellWindow;

public class Sobel extends EdgeOperator {

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
        super("Sobel", parent);
    }

    /**
     * *
     * Runs the Sobel Operator
     */
    @Override
    public void runOperator() {
        ImagePlus image = getImageToProcess();

        image.setProcessor(image.getProcessor().convertToFloat());
        
        float[] pixelsCopy = (float[])image.getProcessor().getPixelsCopy();
        
        int pixelX, pixelY;
        
        int width = image.getWidth();
        int height = image.getHeight();
        
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

                image.getProcessor().putPixelValue(i,j,val);
            }
        }       
        resultingImage = image;
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
