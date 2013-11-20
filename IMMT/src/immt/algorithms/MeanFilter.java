package immt.algorithms;

import ij.ImagePlus;
import immt.ui.ShellWindow;
import immt.util.Functions;
import immt.util.Matrix;
import immt.util.Point;

public class MeanFilter extends Algorithm {

    /**
     * Radio to take into account to calculate the mean.
     */
    private int radio;

    public MeanFilter(ShellWindow parent) {
        super("Mean Filter", "The Mean Filter is a simple one, and does not remove the \n"
                + "speckles but averages it into the data. Generally speaking, this is \n"
                + "the least satisfactory method of speckle noise reduction as it \n"
                + "results in loss of detail and resolution. However, it can be used \n"
                + "for applications where resolution is not the first concern.", parent);
                
    }

    @Override
    public void runAlgorithm() {
        //ImagePlus result = getOriginalImage().duplicate();
                
        //IJ.run(result, "Mean...", "radius=" + radio);
        /*
        Matrix newMatrix = new Matrix(5,5);
        newMatrix.setElementAt(0, 0, 5);        
        Matrix window = Functions.GetWindow(newMatrix, new Point(0,0), 3);        
        float[] row = window.getColumn(1);        
        window.print();
        */     
        
        int sizeWindow = 3;
        
        ImagePlus image = getOriginalImage();

        image.setProcessor(image.getProcessor().convertToFloat());
        
        int heigth = image.getHeight();
        int width = image.getWidth();        

        float[] imagePixels = (float[]) image.getProcessor().getPixelsCopy();   
        
        float[] result = new float[width * heigth];
        
        double meanOfWholeImage = Functions.MeanOfPixels(imagePixels, width, heigth);
        double varianceOfWholeImage = Functions.VarianceOfPixels(imagePixels,  width,  heigth,  meanOfWholeImage);
        
        Matrix window;
        Matrix imageMatrix = new Matrix(heigth, width, imagePixels);
        double localVariance;
        double localMean;
        double kFactor;
        
        for(int i = 0 ; i < heigth ; i++)
        {
            for(int j = 0 ; j < width ; j++)
            {
                if(j == 0 && i == 651)
                {
                    int b = 0;
                }
                window = Functions.GetWindow(imageMatrix, new Point(i,j), sizeWindow);
          
                localMean = Functions.MeanOfPixels(window.getMatrixData(), window.getWidth(), window.getHeight());

                localVariance = Functions.VarianceOfPixels(window.getMatrixData(), window.getWidth(), window.getHeight(), localMean);
       
                kFactor = getKFactor(varianceOfWholeImage, localVariance, localMean);
          
                result[width * i + j] = (float)(localMean + kFactor * (imageMatrix.getElementAt(i, j) - localMean));
             
                //System.out.println(i + "," + j);
                //System.out.println(result[width * i + j]);
            }
        }
        
        image.getProcessor().setPixels(result);
        setResultingImage(image);
    }

    private double getKFactor(double globalVariance, double localVariance, double localMean){
        return ( 1 - (Math.sqrt(localMean) * globalVariance)) / (globalVariance * (1 + localVariance));
        //return Math.sqrt(localVariance) / () (Math.sqrt(localMean) * Math.sqrt(globalVariance)) + 
    }
            
    
    public void setRadio(int radio) {
        this.radio = radio;
    }

    @Override
    public Algorithm clone() {
        MeanFilter newAlgorithm = new MeanFilter(getParent());
        newAlgorithm.setRadio(radio);
        newAlgorithm.setOriginalImage(getOriginalImage());
        return newAlgorithm;
    }
}
