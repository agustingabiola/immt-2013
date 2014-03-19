package immt.algorithms.snakes.externalforces;

import immt.algorithms.snakes.Gradient;
import immt.algorithms.structures.GreyScaleImage;

public class PotentialKass88 extends AbstractPotential{
	
	private Gradient gaussianGradient;
	private GreyScaleImage gaussianFiltered;
	
	public PotentialKass88(GreyScaleImage filtrada, int radiusGaussFilter){
		super(filtrada.converToBufferedImage());
		gaussianFiltered = filtrada;
		gaussianGradient = new Gradient(gaussianFiltered);
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
		double moduleGradient = Math.sqrt(getGradientX(x, y)*getGradientX(x, y)+getGradientY(x, y)*getGradientY(x, y));
		double P = moduleGradient;
		return P;
	}
	
	public double getPotentialGradient(int x1, int y1){
		return getPotential(x1, y1+1) - getPotential(x1, y1);
	}


}
