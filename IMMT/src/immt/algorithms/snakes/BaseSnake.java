package immt.algorithms.snakes;

import immt.algorithms.structures.GreyScaleImage;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;


public class BaseSnake {
	
	protected double centroX = 230;
	protected double centroY = 230;
	public double[] x;
	public double[] y;
	protected double stepVectors = 0.1f;
	
	
	//protected double alpha = 0.008f;
	//protected double beta = 0.02f;
	//protected double gamma = 100f;
	protected int iterations = 4000;

	
	protected void initXYVectors(){
		double dosPI = 2*Math.PI;
		int size = (int)(dosPI / (double)stepVectors);
		//System.out.println("dosPI = " + dosPI + "  --stepVector = " + stepVectors + "  --size = " + size );
		x = new double[size+1];
		y = new double[size+1];
		int j=0;
		for (double i=0;i<dosPI;i+=stepVectors){
			x[j] = centroX + 120 * Math.cos(i);
			y[j] = centroY + 170 * Math.sin(i);
			j++;
		}
		/*x = new double[8];
		x[0] = x[7] = x[6] = 7;
		x[2] = x[3] = x[4] = 9;
		x[1] = x[5] = 8;
		y = new double[8];
		y[0] = y[1] = y[2] = 7;
		y[6] = y[5] = y[4] = 9;
		y[3] = y[7] = 8;*/
	}
	
	protected void printXY(){
		for(int i=0;i<y.length;i++){
			if(x!=null)
				System.out.print("x: "+x[i]+" ");
			if(y!=null)
				System.out.print("-y:"+y[i]+"\n");
		}
	}
	
	public BufferedImage generarImg(String label, int step, int color, GreyScaleImage original,
			double[] normalX, double[] normalY){
		
		BufferedImage img = original.converToBufferedImage();
		//System.out.println("clonacion w: " + img.getWidth() + " h: " + img.getHeight());
		int size = y.length;
		if(normalX!=null){
			dibujarNormales(img, normalX, normalY);
		}
		if(x!=null)
			for(int i=0;i<size;i++){
				img.setRGB((int)x[i], (int)y[i], color);
			}
		else
			for(int i=0;i<size;i++){
				int yT =  (int)y[i];
				if(yT>=0 && yT<original.getHeight())
					img.setRGB(i, (int)y[i], color);
			}
		return img;
	}
	
	public BufferedImage generarImg(int color, GreyScaleImage original, int[] tSnake){
		
		BufferedImage img = original.converToBufferedImage();
		//System.out.println("clonacion w: " + img.getWidth() + " h: " + img.getHeight());
		int size= tSnake.length;
		for(int i=0;i<size;i++){
				if((int)tSnake[i]!=-1)
					img.setRGB(i, (int)tSnake[i], color);
			}
		return img;
	}
	
	protected BufferedImage generarImg(String label, int step, int color, BufferedImage original,
			double[] normalX, double[] normalY){
		
		BufferedImage img = clona(original);
		//System.out.println("clonacion w: " + img.getWidth() + " h: " + img.getHeight());
		int size = y.length;
		if(normalX!=null){
			dibujarNormales(img, normalX, normalY);
		}
		if(x!=null)
			for(int i=0;i<size;i++){
				img.setRGB((int)x[i], (int)y[i], color);
			}
		else
			for(int i=0;i<size;i++){
				if((int)y[i]!=-1)
					img.setRGB(i, (int)y[i], color);
			}
		return img;
	}
	
	public BufferedImage volcarSnakesEnImg(BufferedImage polar, int[] yTSnake, int rgb1){
		BufferedImage img;
		img = clona(polar);
		for(int i=0;i<yTSnake.length;i++){
			try{
			if(yTSnake[i]>=0 && yTSnake[i]<polar.getHeight()){
				img.setRGB(i,yTSnake[i],rgb1);
			}
			}catch (Exception e) {
				e.printStackTrace();
				System.out.println("excepcion en i:"+i+" cuando yTSnake lenght" + yTSnake.length + " y la img mide:" + img.getWidth() + " h:" + img.getHeight());
			}
		}
		return img;
	}
	
	private void dibujarNormales(BufferedImage img, double[] normalX,double[] normalY){
		Graphics2D g = img.createGraphics();
		g.setColor(Color.BLUE);
		for(int i=0;i<x.length;i++){
			g.drawLine(i, (int)(y[i]), (int)(i+normalX[i]*3), (int)(y[i]+normalY[i]*3));
		}
	}


	private BufferedImage clona(BufferedImage imagen){
		BufferedImage copia=new BufferedImage(imagen.getWidth(),imagen.getHeight(),imagen.getType());
		copia.setData(imagen.getData());
		return copia;
	}
	

}
