package immt.util;

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
}
