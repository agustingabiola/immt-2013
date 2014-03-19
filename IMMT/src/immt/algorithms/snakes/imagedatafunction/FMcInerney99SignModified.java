package immt.algorithms.snakes.imagedatafunction;

import immt.algorithms.structures.GreyScaleImage;


public class FMcInerney99SignModified extends AbstractBinaryFunction{
	double T;
	GreyScaleImage img;
	int width;
	int height;
	boolean inverseInecuation;
	public FMcInerney99SignModified(GreyScaleImage image, double threshold, boolean inverseInecuation_){
		T = threshold;
		this.img = image;
		width = img.getWidth();
		height = img.getHeight();
		inverseInecuation = inverseInecuation_;
	}
	
	public double F(double x, double y){
		//TODO:: interpolar!
		double result = -1; 
		if(x>=0 && y>=0 && x<width && y<height){
			float grey = img.getIntensity((int)x, (int)y);
			if (grey>T) result = 1;
		}
		//System.out.println("Fuera de rango!! x: " + x + " y: " +y);
		if(inverseInecuation) return result*(-1);
		else return result;
	}
}
