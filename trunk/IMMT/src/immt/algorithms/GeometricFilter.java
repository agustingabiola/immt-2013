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
                
        int imageWidth = originalImage.getWidth();
        int imageHeight = originalImage.getHeight();
        
        // Original image array
        float[] image = (float[]) originalImage.getProcessor().getPixelsCopy(); // G
        
        // The result will be stored here, to be later shown in the screen. 
        // It is initialized with the original image
        float[] resultingImage = image.clone(); // F


               
        for(int it = 1; it <= iterations; it++)
        {
            // If its not the first iteration, we use the partial result as our image
            if(it >= 2){
                image = resultingImage.clone();
            }
            
            int a, b, c, dir;
            float param1, param2;          
            float maxi, maxin1, maxin;
                    
            a = 1;
            b = 0;            
            c = 3;  
            
            while(c >= 0){
                
                for(dir = 0; dir <= 1; dir++){
                    
                    for(int j = 1; j < imageHeight -1 ; j++){
                        for(int i = 1; i < imageWidth -1  ; i++){    
                            
                            param1 = image[imageWidth * (j - a) + (i - b)] - 1;
                            if(param1 > 255)
                                param1 = 255;
                            param2 = image[imageWidth * j + i] + 1;
                            if(param2 > 255)
                                param2 = 255;
                            maxi = Math.min(param1,param2);
                            resultingImage[imageWidth * j + i] = Math.max(image[imageWidth * j + i], maxi);
                        }
                    }  
                    
                    
                    
                    for(int j = 1; j < imageHeight - 1 ; j++){
                        for(int i = 1; i < imageWidth - 1 ; i++){                            
                            maxin1 = Math.min(resultingImage[imageWidth * (j - a) + (i - b)], image[imageWidth * j + i] + 1);
                            maxin = Math.min(maxin1, resultingImage[imageWidth * (j + a) + (i + b)] + 1);
                            //System.out.println(maxin1 + "," + maxin);
                            image[imageWidth * j + i] = Math.max(resultingImage[imageWidth * j + i], maxin);
                        }
                    }

                    
                    if(dir == 0){
                        a = -a;
                        b = -b;
                    }
                }
                
                for(dir = 0; dir <= 1; dir++){
                    
                    for(int j = 1; j < imageHeight - 1 ; j++){
                        for(int i = 1; i < imageWidth - 1 ; i++){                            
                            param1 = image[imageWidth * (j - a) + (i - b)] + 1;
                            if(param1 > 255)
                                param1 = 255;
                            param2 = image[imageWidth * j + i] - 1;
                            if(param2 > 255)
                                param2 = 255;
                            maxi = Math.max(param1,param2);
                            resultingImage[imageWidth * j + i] = Math.min(image[imageWidth * j + i], maxi);
                        }
                    }  
                    

                    
                    for(int j = 1; j < imageHeight - 1 ; j++){
                        for(int i = 1; i < imageWidth - 1 ; i++){                            
                            maxin1 = Math.max(resultingImage[imageWidth * (j - a) + (i - b)], image[imageWidth * j + i] - 1);
                            maxin = Math.max(maxin1, resultingImage[imageWidth * (j + a) + (i + b)] - 1);
                            image[imageWidth * j + i] = Math.min(resultingImage[imageWidth * j + i], maxin);
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
                            a = 1;
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
    
    public void print(float[] array, int w, int h)
    {
            for(int j =0;j<h;j++){
        for(int i =0; i < w; i++)
        
            {
                System.out.print(array[w * j + i] + "\t");
            }
            System.out.println();
        }
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
