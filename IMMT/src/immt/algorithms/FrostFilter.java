package immt.algorithms;

import ij.ImagePlus;
import immt.ui.ShellWindow;
import immt.util.Functions;

public class FrostFilter extends Algorithm {

    /**
     * Radio to take into account to calculate the mean.
     */
    private int radio;

    public FrostFilter(ShellWindow parent) {
        super("Frost Filter", "this is the description for frost filter", parent);
    }

    @Override
    public void runAlgorithm() {

        ImagePlus image = getOriginalImage();

        image.setProcessor(image.getProcessor().convertToFloat());

        float[] imagePixels = (float[]) image.getProcessor().getPixelsCopy();
        float[] result = new float[imagePixels.length];

        int heigth = image.getHeight();
        int width = image.getWidth();

        double mean = Functions.MeanOfPixels(imagePixels, width, heigth);
        setProgress(2);

        double variance = Functions.VarianceOfPixels(imagePixels, width, heigth, mean);
        setProgress(4);

        double deviation = Functions.StandarDeviationCoefficient(variance);
        setProgress(6);

        double variationCoefficient = Functions.CoefficientOfVariation(deviation, mean);
        setProgress(8);

        double variantionSquared = Math.pow(variationCoefficient, 2);
        setProgress(10);

        // Normalization constant
        float k = (float) 1 / (float) (2 * radio + 1); 


        double localMean;
        double localVariance;

        float totalComputo = (heigth - radio) - (radio + 1);
        float progress = 10;

        for (int y = radio + 1; y < heigth - radio; y++) {
            setProgress((int) progress);
            progress += 90 / totalComputo;
            for (int x = radio + 1; x < width - radio; x++) {

                double pixel = 0;
                double localSize = (float) Math.pow(2 * radio + 1, 2);

                localMean = Functions.MeanOfPixelsWithRadio(imagePixels, x, y, radio, width);

                localVariance = Functions.VarianceOfPixelsWithRadio(imagePixels, x, y, localMean, radio, width);

                double alpha = (4 / (localSize * variantionSquared)) * ((localVariance * localVariance) / (localMean * localMean + 0.00000000001));
                
                for (int ry = y - radio; ry < y + radio + 1; ry++) {
                    for (int rx = x - radio; rx < x + radio + 1; rx++) {
                        int distanceX = Math.abs(x - rx);
                        int distanceY = Math.abs(y - ry);
                        int t = distanceX + distanceY;
                        pixel += k * alpha * Math.exp(-alpha * t);
                    }
                }
                result[x + y * width] = (float) pixel;
            }
        }
        image.getProcessor().setPixels(result);
        setResultingImage(image);
    }

    public void setRadio(int radio) {
        this.radio = radio;
    }
}
