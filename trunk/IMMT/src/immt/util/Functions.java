package immt.util;

import immt.algorithms.structures.GreyScaleImage;
import javax.security.sasl.Sasl;

/**
 * Functions that may be of transversal use * 
 */
public class Functions {
    
    /**
     * Returns the coefficient of variation
     * 
     * @param deviation standard deviation coefficient
     * @param mean mean
     * @return coefficient of variation
     */
    public static double CoefficientOfVariation(double deviation, double mean){
        return deviation / mean;
    }
    
    /**
     * Returns the standard deviation
     * 
     * @param variance variance
     * @return standard deviation coefficient
     */
    public static double StandardDeviationCoefficient(double variance){
        return Math.sqrt(variance);
    }
    
    /**
     * Returns the mean of an image.
     * 
     * @param imageInPixels array of pixels (float) that compose the image
     * @param width width of the image 
     * @param heigth height of the image
     * @return mean of the image
     */
    public static double MeanOfPixels(float[] imageInPixels, int width, int heigth){
        double totalSize = width * heigth;
        double sum = 0;
        for (int y = 0; y < heigth; y++) {
            for (int x = 0; x < width; x++) {
                sum += imageInPixels[x + y * width];
            }
        }
        return sum / totalSize;
    }

    /**
     * Returns the mean of an image, given a certain radio.
     *
     * @param imageInPixels array of pixels (float) that compose the image
     * @param x starting x coordinate
     * @param y starting y coordinate
     * @param radio radio to consider
     * @param width width of the image 
     * @return mean of the section of the image
     */
    public static double MeanOfPixelsWithRadio(float[] imageInPixels, int x, int y, int radio, int width){
        double localSize = (float) Math.pow(2 * radio + 1, 2);
        double sum = 0;
        for (int j = y - radio; j < y + radio +1; j++) {
            for (int k = x - radio; k < x + radio +1; k++) {
                sum += imageInPixels[k + j * width];
            }
        }
        return sum / localSize;
    }
    
    /**
     * Returns the variance in an image.
     * 
     * @param imageInPixels array of pixels (float) that compose the image
     * @param width width of the image 
     * @param heigth height of the image 
     * @param mean mean of the pixels of the image
     * @return  variance of the image
     */
    public static double VarianceOfPixels(float[] imageInPixels, int width, int heigth, double mean){
        double totalSize = width * heigth;
        double sum = 0;
        for (int y = 0; y < heigth; y++) {
            for (int x = 0; x < width; x++) {
                sum += Math.pow(imageInPixels[x + y * width] - mean, 2);
            }
        }
        return sum / (totalSize - 1);
    }
    
    /**
     * Returns the variance in an image, given a certain radio
     * 
     * @param imageInPixels array of pixels (float) that compose the image
     * @param x starting x coordinate
     * @param y starting y coordinate
     * @param mean mean of the pixels of the image
     * @param radio radio to consider
     * @param width width of the image
     * @return 
     */
    public static double VarianceOfPixelsWithRadio(float[] imageInPixels, int x, int y, double mean, int radio, int width){
        double localSize = (float) Math.pow(2 * radio + 1, 2);
        double sum = 0;
        for (int j = y - radio; j < y + radio +1; j++) {
            for (int k = x - radio; k < x + radio +1; k++) {
                sum += Math.pow(imageInPixels[k + j * width] - mean, 2);
            }
        }
        return sum / (localSize - 1);
    }
    
    public static float[] GetNeighborhoodOfAnImage(int xStart, int xFinish, int yStart, int yFinish, float[] originalImage, int widthOriginalImage)
    {
        int sizeOfResult = (xFinish - xStart + 1) * (yFinish - yStart +1);
        float[] result = new float[sizeOfResult];
        int currentPosition = 0;
        for(int y = yStart ; y <= yFinish; y++)
        {
            for(int x = xStart; x <= xFinish ; x++)
            {
                result[currentPosition] = originalImage[widthOriginalImage * y + x];
                currentPosition++;
            }            
        }   
        return result;    
    }

    public static Matrix GetWindow(Matrix image, Point center, int sizeOfWindow)
    {
        Matrix result = new Matrix(sizeOfWindow, sizeOfWindow);
        
        int offsetOfWindow = (int) Math.floor(sizeOfWindow / 2);
        int resultX = 0;
        int resultY = 0;        
        
        
        for(int i= (int)center.getxCoord() - offsetOfWindow ; i < center.getxCoord() + offsetOfWindow ; i++)
        {
            for(int j= (int)center.getyCoord() - offsetOfWindow ; j < center.getyCoord() + offsetOfWindow ; j++)
            {
                if((i >= 0 && j >= 0) && (i < image.getWidth() && j < image.getHeight()))
                {
                    result.setElementAt(resultX, resultY, image.getElementAt(j, i));
                }
                else
                {
                    result.setElementAt(resultX, resultY, 0);
                }
                resultY++;
            }
            resultY = 0;
            resultX++;
        }
        return result;
    }
    
    public static Matrix GetWindow(GreyScaleImage img, Point center, int sizeOfWindow)
    {
        Matrix result = new Matrix(sizeOfWindow, sizeOfWindow);
        
        int offsetOfWindow = (int) Math.floor(sizeOfWindow / 2);
        int resultX = 0;
        int resultY = 0;        
        
        for(int i= (int)center.getxCoord() - offsetOfWindow ; i < center.getxCoord() + offsetOfWindow ; i++)
        {
            for(int j= (int)center.getyCoord() - offsetOfWindow ; j < center.getyCoord() + offsetOfWindow ; j++)
            {
                if((i >= 0 && j >= 0) && (i < img.getWidth() && j < img.getHeight()))
                {
                    result.setElementAt(resultX, resultY, img.getIntensity(j, i));
                }
                else
                {
                    result.setElementAt(resultY, resultX, 0);
                }
                resultY++;
            }
            resultY = 0;
            resultX++;
        }
        return result;
    }

}
