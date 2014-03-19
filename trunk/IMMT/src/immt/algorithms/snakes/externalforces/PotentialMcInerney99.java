package immt.algorithms.snakes.externalforces;

import immt.algorithms.snakes.Gradient;
import immt.algorithms.structures.GreyScaleImage;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class PotentialMcInerney99 extends AbstractPotential{
	
	private Gradient gaussianGradient;
	private GreyScaleImage gaussianFiltered;
	private double cScalePotential;
	private GreyScaleImage moduleGradients;
	
	public PotentialMcInerney99(GreyScaleImage filtrada, int radiusGaussFilter, double potentialScale){
		super(filtrada.converToBufferedImage());
		cScalePotential = potentialScale;
		//TODO::filtro en el punto por la intensidad original (apendice A, formula 4)
		gaussianGradient = new Gradient(filtrada);
		moduleGradients = new GreyScaleImage(gaussianFiltered.getWidth(),gaussianFiltered.getHeight());
		calculatePotential();
	}
	
	public double getGradientX(double x, double y){
		//TODO::cambiar a una interpolacion
		double gX = gaussianGradient.getGradienteX((int)x, (int)y);
		return gX;
	}
	
	public double getGradientY(double x, double y){
		//TODO::cambiar a una interpolacion
		double gY = gaussianGradient.getGradienteY((int)x, (int)y);
		return gY;
	}
	

	public double getPotential(double x, double y){
		return moduleGradients.getIntensity((int)x, (int)y);
	}
	
	public double getPotentialGradient(int x1, int y1){
		return getPotential(x1, y1+1) - getPotential(x1, y1);
	}
	
	private void calculatePotential(){
		for(int x=0;x<moduleGradients.getWidth();x++){
			for(int y=0;y<moduleGradients.getHeight();y++){
				double moduleGradient = Math.sqrt(getGradientX(x, y)*getGradientX(x, y)+getGradientY(x, y)*getGradientY(x, y));
				double P = -cScalePotential*moduleGradient;
				moduleGradients.setIntensity(x, y, (float)P);
			}
		}
	}
	
	private void exportarGradientes(){
		BufferedImage img = gaussianGradient.getImageGradientX();
		save("gradienteXMcInerney",img);
		img = gaussianGradient.getImageGradientY();
		save("gradienteYMcInerney",img);
		
	}
	
	public void save(String NombreArchivo, BufferedImage img){
		try {
			// retrieve image
			File outputfile = new File("M:\\"+ NombreArchivo + ".bmp");
			ImageIO.write(img, "bmp", outputfile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


}
