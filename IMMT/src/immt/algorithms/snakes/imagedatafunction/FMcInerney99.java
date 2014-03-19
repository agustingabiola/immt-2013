package immt.algorithms.snakes.imagedatafunction;

import immt.algorithms.structures.GreyScaleImage;

public class FMcInerney99 extends AbstractBinaryFunction{
	double T;
	GreyScaleImage img;
	int width;
	int height;
	public FMcInerney99(GreyScaleImage image, double threshold){
		T = threshold;
		this.img = image;
		width = img.getWidth();
		height = img.getHeight();
	}
	
	public double F(double x, double y){
		//TODO:: interpolar!
		if(x>=0 && y>=0 && x<width && y<height){
			float grey = img.getIntensity((int)x, (int)y);
			if (grey>T) return 1;
		}
		//System.out.println("Fuera de rango!! x: " + x + " y: " +y);
		return -1;
	}
}
