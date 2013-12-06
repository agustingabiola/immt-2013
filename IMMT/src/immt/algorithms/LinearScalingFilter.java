package immt.algorithms;

import ij.ImagePlus;
import ij.process.ImageProcessor;
import immt.ui.ShellWindow;
import immt.util.Functions;
import immt.util.Matrix;
import immt.util.Point;
import java.awt.Rectangle;

public class LinearScalingFilter extends Algorithm {
    
    private int sizeWindow;
    
    public LinearScalingFilter(ShellWindow parent){
        super("Linear Scaling Filter", "this is the description", parent);
        sizeWindow = 3;
    }
            
            
    @Override
    public void runAlgorithm(Rectangle roi) {
        ImagePlus originalImage = getOriginalImage();
        
        originalImage.setProcessor(originalImage.getProcessor().convertToFloat());
        
        int heigth = originalImage.getHeight();
        int width = originalImage.getWidth();        

        float[] imageInPixels = (float[]) originalImage.getProcessor().getPixelsCopy();   
        
        float[] result = new float[width * heigth];
        
        result = imageInPixels;
        
        double meanOfWholeImage = Functions.MeanOfPixels(imageInPixels, width, heigth);
        double varianceOfWholeImage = Functions.VarianceOfPixels(imageInPixels,  width,  heigth,  meanOfWholeImage);
        
        Matrix window;
        Matrix imageMatrix = new Matrix(heigth, width, result);
        double localVariance;
        double localMean;
        double kFactor;
        
        int a = 1;
        while(a < 3)
        {
        
        imageMatrix = new Matrix(heigth, width, result);
        
        for(int i = 0 ; i < heigth ; i++)
        {
            for(int j = 0 ; j < width ; j++)
            {
                window = Functions.GetWindow(imageMatrix, new Point(i,j), sizeWindow);
          
                localMean = Functions.MeanOfPixels(window.getMatrixData(), window.getWidth(), window.getHeight());

                localVariance = Functions.VarianceOfPixels(window.getMatrixData(), window.getWidth(), window.getHeight(), localMean);
       
                kFactor = getKFactor(varianceOfWholeImage, localVariance, localMean);
          
                result[width * i + j] = (float)(localMean + kFactor * (imageMatrix.getElementAt(i, j) - localMean));
            }
        }
        a++;
        }
        originalImage.getProcessor().setPixels(result);
        setResultingImage(originalImage);
    }

    private double getKFactor(double globalVariance, double localVariance, double localMean){
        return ( 1 - (Math.sqrt(localMean) * globalVariance)) / (globalVariance * (1 + localVariance));
    }
    
    @Override
    public Algorithm clone() {
        LinearScalingFilter newAlgorithm = new LinearScalingFilter(getParent());
        newAlgorithm.setOriginalImage(getOriginalImage());
        return newAlgorithm;
    }
    
}
