package immt.algorithms.snakes;

import java.awt.Color;
import java.awt.image.BufferedImage;

import Jama.Matrix;
import immt.algorithms.structures.GreyScaleImage;

public class Gradient {
	
	private Matrix xGradient;
	private Matrix yGradient;
	private Matrix gradient;
	private int width;
	private int height;
	private GreyScaleImage img;
	private double maxGX, maxGY, minGX, minGY;
	
	public Gradient(GreyScaleImage img_){
		img = img_;
		width = img.getWidth();
		height = img.getHeight();
		xGradient = new Matrix(width,height);
		yGradient = new Matrix(width,height);
		gradient = new Matrix(width,height);
		//Matrix auxiliar = new Matrix(width,height);
		//System.out.println("column: "+ xGradient.getColumnDimension());
		//System.out.println("row: "+ xGradient.getRowDimension());
		maxGX = maxGY = minGX = minGY = 0;
		double g;
		for(int i=0;i<width;i++){
			for(int j=0;j<height;j++){
				if(i==0 || j==0 || i==width-1 || j==height-1){
					xGradient.set(i, j, 0);
					yGradient.set(i, j, 0);
				}else{
					g = gradienteX(i, j);
					xGradient.set(i, j, g);
					g = gradienteY(i, j);
					yGradient.set(i, j, g);
				}
			}
		}
		
		for(int i=0;i<width;i++){
			for(int j=0;j<height;j++){
				if(i==0 || j==0 || i==width-1 || j==height-1){
					gradient.set(i, j, 0);
				}else{
					g = getGradiente(i, j);
					gradient.set(i, j, g);
				}
			}
		}
		
		/*FiltroGaussiano.gaussianGradients(img, radio);
		xGradient = FiltroGaussiano.matrixGradientX;
		System.out.println("xGradient");
		MatrizP.imprimirMatriz(xGradient);
		yGradient = FiltroGaussiano.matrixGradientY;*/
		/*System.out.println("xGradient");
		MatrizP.imprimirMatriz(xGradient);
		System.out.println("yGradient");
		MatrizP.imprimirMatriz(yGradient);*/
		
	}
	/*
	public double[] getFExtX(int x){
		double[] xColumn = new double[height];
		for(int i=0;i<height;i++){
			xColumn[i] = getGradienteX(x, i);
		}
		return xColumn;
	}*/
	
	public double[] getFExtX(double[] x, double[] y){
		int size = x.length;
		double[] xColumn = new double[size];
		for(int i=0;i<size;i++){
			xColumn[i] = getGradienteX((int)x[i], (int)y[i]);//LOVER:: ojo con esto! tendria que ser una interpolacion
		}
		return xColumn;
	}
	/*
	public double[] getFExtY(int y){
		double[] yRow = new double[width];
		for(int i=0;i<width;i++){
			yRow[i] = getGradienteY(i, y);
		}
		return yRow;
	}*/
	
	public double[] getFExtY(double[] x, double[] y){
		int size = y.length;
		double[] yColumn = new double[size];
		for(int i=0;i<size;i++){
			yColumn[i] = getGradienteY((int)x[i], (int)y[i]);//LOVER:: ojo con esto! tendria que ser una interpolacion
		}
		return yColumn;
	}
	
	public double getGradienteX(int x, int y){
		if(x<0 || y<0) return 0;
		double g = xGradient.get(x, y);
		return g;
	}
	
	public double getGradienteY(int x, int y){
		if(x<0 || y<0) return 0;
		double g = yGradient.get(x, y);
		return g;
	}
	
	public double getGradiente(int x, int y){
		if(x<0 || y<0) return 0;
		double g = Math.sqrt(getGradienteX(x, y) * getGradienteX(x, y) + getGradienteY(x, y) * getGradienteY(x, y));
		return g;
	}
	
	private double gradienteX(int x, int y){
		float i = img.getIntensity(x, y);
		//rgb = (rgb & 0xff);
		float i10 = img.getIntensity(x+1, y);
		//rgb10 = (rgb10 & 0xff);
		double g = (double)i10-(double)i;
		if(g<minGX) minGX=g;
		if(g>maxGX) maxGX=g;
		return g/255;
	}
	
	private double gradienteY(int x, int y){
		float i = img.getIntensity(x, y);
		//rgb = (rgb & 0xff);
		float i01 = img.getIntensity(x, y+1);
		//rgb01 = (rgb01 & 0xff);
		double g = (double)i01-(double)i;
		if(g<minGY) minGY = g;
		if(g>maxGY) maxGY = g;
		return g/255;
	}
	
	public BufferedImage getImageGradientX(){
		BufferedImage ret = new BufferedImage(width, height,  BufferedImage.TYPE_3BYTE_BGR);
		int rgb;
		double g;
		for(int i=0;i<width;i++){
			for(int j=0;j<height;j++){
				g = xGradient.get(i,j);
				if(g<0.)
					rgb = 15790320;
				else{
					if (g>0.)
						rgb = 3289650;
				else
					rgb = 16711680;}
				ret.setRGB(i, j, (new Color(rgb,rgb,rgb)).getRGB());
			}
		}
		return ret;
	}
	
	public BufferedImage getImageGradientY(){
		BufferedImage ret = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);
		int rgb;
		double g;
		for(int i=0;i<width;i++){
			for(int j=0;j<height;j++){
				g = yGradient.get(i,j);
				if(g<0.)
					rgb = 15790320;
				else{ if (g>0.)
					rgb = 3289650;
				else
					rgb = 16711680;}
				ret.setRGB(i, j, (new Color(rgb,rgb,rgb)).getRGB());
			}
		}
		return ret;
	}
	
	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public GreyScaleImage getImg() {
		return img;
	}

	public double getMaxGX() {
		return maxGX;
	}

	public double getMaxGY() {
		return maxGY;
	}

	public double getMinGX() {
		return minGX;
	}

	public double getMinGY() {
		return minGY;
	}

}
