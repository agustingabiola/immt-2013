package immt.util;

public class Functions {
    
    public static double CoefficientOfVariation(double deviation, double mean){
        return deviation / mean;
    }
    
    public static double StandarDeviationCoefficient(double variance){
        return Math.sqrt(variance);
    }
    
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
