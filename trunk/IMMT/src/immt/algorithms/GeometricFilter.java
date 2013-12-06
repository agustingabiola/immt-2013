package immt.algorithms;

import ij.ImagePlus;
import ij.process.ImageProcessor;
import immt.ui.ShellWindow;
import java.awt.Rectangle;

public class GeometricFilter extends Algorithm {

    private int iterations;
    
    public GeometricFilter(ShellWindow parent) {
        super("Filtro Geometrico", "asdasdasdasdsadasdasdasd", parent);                
    }

    @Override
    public void runAlgorithm(Rectangle roi) {
        // Methods needed by ImageJ
        ImagePlus originalImage = getOriginalImage();
        originalImage.setProcessor(originalImage.getProcessor().convertToFloat());
        ImageProcessor imageProcessor = originalImage.getProcessor();        
        
        //Rectangle r = imageProcessor.getRoi(); 
        
        // The result will be stored here, to be later shown in the screen.
        // It's initialized with the value of the pixels original image.       
        float[] resultingImage = (float[]) originalImage.getProcessor().getPixelsCopy();

        int imageWidth = originalImage.getWidth();
        int imageHeight = originalImage.getHeight();
               
        for(int it = 1; it <= iterations; it++)
        {
            int a, b, c, dir;
            float i1, i2, i3;          
                    
            a = 1;
            b = 0;            
            c = 3;  
            while(c >= 0){
                
                for(dir = 0; dir <= 1; dir++){
                    
                    for(int i = 1; i < imageWidth - 1 ; i++){
                        for(int j = 1; j < imageHeight - 1 ; j++){
                                i1 = resultingImage[imageWidth * (j - b) + (i - a)]; //imageProcessor.getf(i - a, j - b);
                                i2 = resultingImage[imageWidth * j + i]; //imageProcessor.getf(i,j);
                                i3 = resultingImage[imageWidth * (j + b) + (i + a)]; //imageProcessor.getf(i + a, j + b);
                                resultingImage[imageWidth * j + i] = CalculateCenterPixel(i1, i2, i3);
                        }
                    }          
                    
                    if(dir == 0){
                        a = -a;
                        b = -b;
                    }
                }
                
                switch (c){
                        case 3:
                            a = 0;
                            b = 1;
                            c = 2;
                            break;
                        case 2:
                            a = 1;
                            b = 1;
                            c = 1;
                            break;
                        case 1:
                            a = 0;
                            b = -1;
                            c = 0;
                            break;
                        case 0:
                            c = -1;
                            break;
                }    
            }   
        }
                
        
        
        // Show the resulting image in the screen
        originalImage.getProcessor().setPixels(resultingImage);
        //originalImage.getProcessor().findEdges();
        setResultingImage(originalImage);
    }

    public float CalculateCenterPixel(float a, float b, float c){
        if(a >= b + 2)
            return b + 1;
        if ((a > b) && (b <= c))
            return b + 1;
        if((c > b) && (b <= a))
            return b + 1;
        if(c >= b + 2)
            return b + 1;
        
        if(a <= b - 2)
            return b - 1;
        if ((a < b) && (b >= c))
            return b - 1;
        if((c < b) && (b >= a))
            return b - 1;
        if(c <= b + 2)
            return b - 1;
        
        return b;
    }
    
    public void setIterations(int it){
        iterations = it;
    }
           
    
    @Override
    public Algorithm clone() {
        GeometricFilter newAlgorithm = new GeometricFilter(getParent());
        newAlgorithm.setOriginalImage(getOriginalImage());
        return newAlgorithm;
    }
}
