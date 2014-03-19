package immt.algorithms.snakes.externalforces;

import immt.algorithms.structures.GreyScaleImage;


public class Intensidad extends AbstractPotential{
	
	private GreyScaleImage gaussianFiltered;
	private double cScalePotential;
	
	public Intensidad(GreyScaleImage filtrada, int radiusGaussFilter, double potentialScale){
		super(filtrada.converToBufferedImage());
		cScalePotential = potentialScale;
		gaussianFiltered = filtrada;
		//TODO::filtro en el punto por la intensidad original (apendice A, formula 4)
	}
	
	//Descarto el Apendice A.4 - Lo uso para el gradiente de la intensidad, no el gradiente del gradiente de la intensidad
	public double getPotential(double x, double y){
		return - cScalePotential * gaussianFiltered.getIntensity((int)x, (int)y);
	}
	
	public double getPotentialGradient(int x1, int y1){
		return getPotential(x1, y1+1) - getPotential(x1, y1);
	}

}
