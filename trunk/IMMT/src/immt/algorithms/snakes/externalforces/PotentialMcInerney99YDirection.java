package immt.algorithms.snakes.externalforces;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import immt.algorithms.snakes.Gradient;
import immt.algorithms.snakes.tsnake.polar.TSnakePolar;
import immt.algorithms.structures.GreyScaleImage;

public class PotentialMcInerney99YDirection extends AbstractPotential{
	
	private Gradient gaussianGradient;
	private GreyScaleImage gaussianFiltered;
	private double cScalePotential;
	
	public GreyScaleImage moduleGradients;
	public double minModuleGradient;
	public double maxModuleGradient;
	
	public PotentialMcInerney99YDirection(GreyScaleImage filtrada, int radiusGaussFilter, double potentialScale){
		super(filtrada.converToBufferedImage());
		cScalePotential = potentialScale;
		//TODO::filtro en el punto por la intensidad original (apendice A, formula 4)
                
		gaussianGradient = new Gradient(filtrada);
                gaussianFiltered = filtrada;
		moduleGradients = new GreyScaleImage(gaussianFiltered.getWidth(),gaussianFiltered.getHeight());
		minModuleGradient = maxModuleGradient = -1;
		calculatePotential();
	}
	
	private double getGradientY(double x, double y){
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
				double moduleGradient = Math.sqrt(getGradientY(x, y)*getGradientY(x, y));
				double P = -cScalePotential*moduleGradient;
				if(minModuleGradient==-1 || P<minModuleGradient) minModuleGradient=P;
				if(maxModuleGradient==-1 || P>maxModuleGradient) maxModuleGradient=P;
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

	public void convertToZeroAroundSnake(int[] tSnakePolar){
		int w = tSnakePolar.length;
		boolean potentialNegative = false;
		for(int i=0;i<w;i++){
			if(tSnakePolar[i]!=TSnakePolar.noSnakePoint){
				int j = tSnakePolar[i] + 1;//el pixel inmediato inferior
				double potential = getPotentialGradient(i, j);
				potentialNegative = (potential<0); 
				while((potentialNegative && potential<0)||(!potentialNegative && potential>0)){
					moduleGradients.setIntensity(i, j, 0);
					j++;
					potential = getPotentialGradient(i, j);
				}
			}
		}
	}


}
