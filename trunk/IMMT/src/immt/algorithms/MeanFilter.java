package immt.algorithms;

import ij.ImagePlus;
import immt.ui.ShellWindow;
import immt.ui.parameters.MeanFilterParams;
import immt.util.Functions;
import immt.util.Matrix;
import immt.util.Point;
import java.awt.Rectangle;

public class MeanFilter extends Algorithm {

    /**
     * *
     * The window will be squared
     */
    private int windowSize;

    /**
     * *
     * Mean Filter
     *
     * @param parent The main window of the application
     */
    public MeanFilter(ShellWindow parent) {
        super("Media", "Se le asigna al píxel central la media de todos los píxeles incluidos en la ventana", parent);
    }

    /**
     * *
     * Set the size of the window. It will be a window of [size, size]
     *
     * @param size size of the window
     */
    public void setWindowSize(int size) {
        windowSize = size;
    }

    /**
     * *
     * Get the size of the window
     *
     * @return size of the window
     */
    public int getWindowSize() {
        return windowSize;
    }

    /**
     * *
     * Runs the mean filter
     *
     */
    @Override
    public void runAlgorithm() {
        ImagePlus originalImage = parent.getImageToProcess();
        originalImage.setProcessor(originalImage.getProcessor().convertToFloat());

        // Parameters of the original image
        int imageWidth = originalImage.getWidth();
        int imageHeight = originalImage.getHeight();

        // Kernell 
        int[] kernel = new int[windowSize * windowSize];
        for (int i = 0; i < (windowSize * windowSize); i++) {
            kernel[i] = 1;
        }

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

                window = Functions.GetWindow(imageMatrix, new Point(i, j), windowSize);

                resultingImage[imageWidth * j + i] = applyFilter(window, kernel);
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
     * @param kernel kernel which contains the weights
     * @return
     */
    public int applyFilter(Matrix image, int[] kernel) {
        float sum = 0;
        for (int i = 0; i < windowSize ; i++) {
            for (int j = 0; j < windowSize ; j++) {
                sum += kernel[j * (windowSize - 1) + i] * image.getElementAt(i, j);
            }
        }
        return (int) (sum / Math.pow(windowSize, 2));
    }

    /**
     * *
     * Returns a copy of the Mean Algorithm
     *
     * @return copy of the Mean Filter algorithm
     */
    @Override
    public Algorithm clone() {
        MeanFilter newAlgorithm = new MeanFilter(getParent());
        newAlgorithm.setWindowSize(windowSize);
        newAlgorithm.setOriginalImage(getOriginalImage());
        return newAlgorithm;
    }

    @Override
    public void showAlgorithmOptions() {
        MeanFilterParams p_MeanFilterParams = new MeanFilterParams(parent, this);
        parent.showAlgorithmOption(p_MeanFilterParams);
    }

}
