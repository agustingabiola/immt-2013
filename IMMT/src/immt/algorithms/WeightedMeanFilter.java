package immt.algorithms;

import ij.ImagePlus;
import immt.ui.ShellWindow;
import immt.ui.parameters.WeightedMeanFilterParams;
import immt.util.Functions;
import immt.util.Matrix;
import immt.util.Point;
import java.awt.Rectangle;

public class WeightedMeanFilter extends Algorithm {

    /**
     * *
     * The weighted kernel
     */
    private int[] kernel;

    /**
     * *
     * The size of the window
     */
    private final int windowSize;

    /**
     * *
     * Weighted Mean Filter
     *
     * @param parent The main window of the application
     */
    public WeightedMeanFilter(ShellWindow parent) {
        super("Media Ponderada", "Se le asigna al píxel central la media de todos los píxeles incluidos en la ventana \n"
                + ", pero el peso de cada vecino es ponderado con un kernel del [3x3]", parent);
        windowSize = 3;
    }

    /**
     * *
     * Set the size of the window. It will be a window of [size, size]
     *
     * @param kernel kernel to use
     */
    public void setKernel(int[] kernel) {
        this.kernel = kernel;
    }

    /**
     * *
     * Runs the mean filter
     *
     * @param roi the selected Region of interest. If null, the whole image will
     * be processed
     */
    @Override
    public void runAlgorithm() {
        ImagePlus originalImage = getOriginalImage().duplicate();
        originalImage.setProcessor(originalImage.getProcessor().convertToFloat());

        // Parameters of the original image
        int imageWidth = originalImage.getWidth();
        int imageHeight = originalImage.getHeight();

        // Original image array
        float[] imagePixels = (float[]) originalImage.getProcessor().getPixelsCopy();

        Matrix imageMatrix = new Matrix(imageHeight, imageWidth, imagePixels);
        Matrix window;

        // The result will be stored here, to be later shown in the screen. 
        // It is initialized with the original image
        float[] resultingImage = imagePixels.clone();

        // The radius of the window
        int radius = (int) Math.floor(windowSize / 2);

        for (int i = radius; i < imageWidth - 1 - radius; i++) {
            for (int j = radius; j < imageHeight - 1 - radius; j++) {

                window = Functions.GetWindow(imageMatrix, new Point(j, i), windowSize);

                resultingImage[imageWidth * j + i] = applyFilter(window, i, j, kernel);
            }
        }

        // Show the resulting image in the screen
        originalImage.getProcessor().setPixels(resultingImage);
        setResultingImage(originalImage);
    }

    /**
     * *
     * Returns an scalar value, that is the mean inside the window, taking into
     * account the weights of the kernel
     *
     * @param image matrix of the window
     * @param x x position
     * @param y y position
     * @param kernel kernel which contains the weights
     * @return
     */
    public int applyFilter(Matrix image, int x, int y, int[] kernel) {
        float sum = 0;
        for (int i = 0; i < windowSize - 1; i++) {
            for (int j = 0; j < windowSize - 1; j++) {
                sum += kernel[j * (windowSize - 1) + i] * image.getElementAt(i, j);
            }
        }
        return (int) (sum / getKernelTotalWeight());
    }

    /**
     * *
     * Returns the sum of all the elements in the kernel
     *
     * @return the sum of the kernel
     */
    public int getKernelTotalWeight() {
        int sum = 0;
        for (int i = 0; i < kernel.length; i++) {
            sum += kernel[i];
        }
        return sum;
    }

    /**
     * *
     * Returns a copy of the Mean Algorithm
     *
     * @return copy of the Mean Filter algorithm
     */
    @Override
    public Algorithm clone() {
        WeightedMeanFilter newAlgorithm = new WeightedMeanFilter(getParent());
        newAlgorithm.setKernel(kernel);
        newAlgorithm.setOriginalImage(getOriginalImage());
        return newAlgorithm;
    }

    @Override
    public void showAlgorithmOptions() {
        WeightedMeanFilterParams p_WeightedMeanFilterParams = new WeightedMeanFilterParams(parent, this);
        parent.showAlgorithmOption(p_WeightedMeanFilterParams);
    }
}
