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
        super("Frost Filter", "The Frost Filter Noise Reduction replaces the pixel of interest with \n"
                +"a weighted sum of the values within the M*M kernel. The weighting \n"
                +"factors decrease with distance from the pixel of interest. The \n"
                +"weighting factors increase for the central pixels as variance within \n"
                +"the kernel increases. This filter assumes multiplicative noise and \n"
                + "stationary noise statistics and follows the following formula: \n", parent);
    }

    @Override
    public void runAlgorithm() {

        ImagePlus image = getOriginalImage();

        image.setProcessor(image.getProcessor().convertToFloat());

        float[] imagePixels = (float[]) image.getProcessor().getPixelsCopy();
        float[] result = new float[imagePixels.length];

        int heigth = image.getHeight();
        int width = image.getWidth();

        int mask = radio;
        
        /*
        float[] imageTest = new float[16];
        for(int i = 0; i < 16; i++)
            imageTest[i] = i;
        
        Functions.GetNeighborhoodOfAnImage(1, 2, 1, 2, imageTest, 4);
        
        */
        
        int floorOfMask = (int)Math.floor(mask / 2);
        int sizeOfRow = (floorOfMask * 2) + 1;        
        int[] row = new int[sizeOfRow];
        
        int index = 0;
        for(int x = floorOfMask * -1; x<=floorOfMask ;x++)
        {
            row[index] = x;
            index++;
        }
        
        float[][] matrix = new float[sizeOfRow][sizeOfRow];
        for(int a = 0; a< sizeOfRow ; a++)
        {
            for(int b=0; b<sizeOfRow;b++)
            {
                matrix[a][b] = row[b];
            }
        }
        
        float[][] matrixSquared = new float[sizeOfRow][sizeOfRow];
        for(int a = 0; a< sizeOfRow ; a++)
        {
            for(int b=0; b<sizeOfRow;b++)
            {
                matrixSquared[a][b] = -1 *((matrix[a][b] * matrix[a][b]) + (matrix[a][b] * matrix[a][b])) ;
            }
        }
        /*
        float[][] matrixSquared = new float[sizeOfRow][sizeOfRow];
        for(int a = 0; a< sizeOfRow ; a++)
        {
            for(int b=0; b<sizeOfRow;b++)
            {
                matrixSquared[a][b] = -1 *((matrix[a][b] * matrix[a][b]) + (matrix[a][b] * matrix[a][b])) ;
            }
        }
                */
        double localMean;
        double localVariance;        
        double alpha;
        
        for(int i= (int)Math.ceil(mask/2) ; i <= width - Math.floor(mask/2) ; i++)
        {
            for(int j= (int)Math.ceil(mask/2) ; i <= heigth - Math.floor(mask/2) ; j++)
            {
                float[] localNeigh = Functions.GetNeighborhoodOfAnImage(i - floorOfMask , i + floorOfMask, j - floorOfMask, j + floorOfMask, imagePixels, mask);
                
                localMean = Functions.MeanOfPixels(localNeigh, mask, mask);
                
                localVariance = Functions.VarianceOfPixels(localNeigh, mask, mask, localMean);
                
                alpha = Math.sqrt(localVariance) / localMean;
                        
            
            }
        }
            
        
        /*
        double mean = Functions.MeanOfPixels(imagePixels, width, heigth);
        setProgress(2);

        double variance = Functions.VarianceOfPixels(imagePixels, width, heigth, mean);
        setProgress(4);

        double deviation = Functions.StandardDeviationCoefficient(variance);
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
                
                */
        image.getProcessor().setPixels(result);
        setResultingImage(image);
    }

    public void setRadio(int radio) {
        this.radio = radio;
    }

    @Override
    public Algorithm clone() {
        FrostFilter newAlgorithm = new FrostFilter(getParent());
        newAlgorithm.setRadio(radio);
        newAlgorithm.setOriginalImage(getOriginalImage());
        return newAlgorithm;
    }
}
