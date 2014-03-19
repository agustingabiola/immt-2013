package immt.algorithms.snakes.externalforces;

import java.awt.image.BufferedImage;


public abstract class AbstractPotential{
	
	BufferedImage original;
	
	public AbstractPotential(BufferedImage original){
		this.original = original;
	}
	
	public abstract double getPotential(double x, double y);
	public abstract double getPotentialGradient(int x1, int y1);
	//public abstract double getExternalForceY(double x, double y);
	
	/*protected double getGradientX(int x, int y){
		//TODO:: si es subpixel, calcular vecinos e interpolar en X
		//es el subpixel de dip_image de Matlab
	}
	
	protected double getGradientY(int x, int y){
		//TODO:: si es subpixel, calcular vecinos e interpolar en Y
		//es el subpixel de dip_image de Matlab
	}*/

}
