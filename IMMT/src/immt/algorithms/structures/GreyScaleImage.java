package immt.algorithms.structures;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.awt.image.DataBuffer;
import java.awt.image.Raster;
import java.awt.image.SampleModel;
import java.io.File;

import javax.imageio.ImageIO;

public class GreyScaleImage {
	
	BufferedImage imgOriginalBuffered;
	Raster imgOriginalRaster;
	GreyScaleImage imgOriginalGrey;
	public static final int imageType =  BufferedImage.TYPE_3BYTE_BGR;
	float[][] grayScale;
	int height;
	int width;
	
	public GreyScaleImage(int w, int h){
		height = h;
		width = w;
		grayScale = new float[width][height];
	}
	
	public GreyScaleImage(BufferedImage _imgOriginal){
		imgOriginalBuffered = _imgOriginal;
		height = _imgOriginal.getHeight();
		width = _imgOriginal.getWidth();
		convertBufferedImage();
	}
	
	public void save(String NombreArchivo, BufferedImage img){
		try {
			// retrieve image
			File outputfile = new File("D:\\DOCTORADO\\SEGMENTACIONES GUILLERMO\\"+ NombreArchivo + ".png");
			System.out.println("output sale en: " + outputfile.getAbsoluteFile());
			boolean b = ImageIO.write(img, "png", outputfile);
			System.out.println(b);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void save(String NombreArchivo, BufferedImage img, String path){
		try {
			// retrieve image
			File outputfile = new File(path + "\\"+ NombreArchivo + ".png");
			System.out.println("output sale en: " + outputfile.getAbsoluteFile());
			boolean b = ImageIO.write(img, "png", outputfile);
			System.out.println(b);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public GreyScaleImage(GreyScaleImage _imgOriginal){
		imgOriginalGrey = _imgOriginal;
		height = _imgOriginal.getHeight();
		width = _imgOriginal.getWidth();
		grayScale = new float[width][height];
		for(int i=0;i<width;i++){
			for(int j=0;j<height;j++){
				grayScale[i][j]= imgOriginalGrey.getIntensity(i, j);
			}
		}
	}
	
	public GreyScaleImage(GreyScaleImage _imgOriginal, int w, int h){
		imgOriginalGrey = _imgOriginal;
		height = h;
		width = w;
		grayScale = new float[width][height];
		for(int i=0;i<width;i++){
			for(int j=0;j<height;j++){
				grayScale[i][j]= imgOriginalGrey.getIntensity(i, j);
			}
		}
	}
	
	public GreyScaleImage(Raster _imgOriginal){
		imgOriginalRaster = _imgOriginal;
		width = imgOriginalRaster.getWidth();
		height = imgOriginalRaster.getHeight();
		convertRaster();
	}
	
	private void convertRaster(){
		System.out.println("bands: " + imgOriginalRaster.getNumBands() + " data elements: " + imgOriginalRaster.getNumDataElements());
		System.out.println("width: " + imgOriginalRaster.getWidth() + " height: " + imgOriginalRaster.getHeight());
		System.out.println("minX: " + imgOriginalRaster.getMinX() + " minY: " + imgOriginalRaster.getMinY());
		SampleModel sample = imgOriginalRaster.getSampleModel();
		DataBuffer data = imgOriginalRaster.getDataBuffer();
		//float r;
		//int[] iArray = null;// = new int[1];
		grayScale = new float[width][height];
		for(int i=0;i<width;i++){
			for(int j=0;j<height;j++){
				//iArray = imgOriginalRaster.getPixel(i, j, iArray);
				//r = iArray[0];
				//int b = imgOriginalRaster.getSample(i, j, 0);
				int[] a = null;
				a = sample.getPixel(i, j, a, data);
				int b = a[0];		
				//grayScale[i][j]= r;
				grayScale[i][j]= b;
			}
		}
	}
	
	private void convertBufferedImage(){
		int argb;
		int g;
		grayScale = new float[width][height];
		for(int i=0;i<width;i++){
			for(int j=0;j<height;j++){
				argb = imgOriginalBuffered.getRGB(i, j);
				g = (argb >> 8) & 0xFF;
				grayScale[i][j]= g;
			}
		}
	}
	
	public BufferedImage converToBufferedImage(){
		BufferedImage img = new BufferedImage(width, height,imageType);
		int argb = -1;
		Color c;
		
		for(int i=0;i<width;i++){
			for(int j=0;j<height;j++){
			try{
				argb = (int)(grayScale[i][j]);
				if(argb>255.) argb=255;
				if(argb<0.) argb=0;
				c = new Color(argb,argb,argb);
				img.setRGB(i, j, c.getRGB());
			}catch(Exception e){e.printStackTrace();
			System.out.println("argb: " + argb + " i:" + i + " j: " + j);}
			}
		}
		
		return img;
	}
	

	public float getIntensity(int x, int y){
		if(x>=0 && y>=0 && x<width && y<height)
			return grayScale[x][y];
		return 0;
	}
	
	public int getHeight() {
		return height;
	}

	public int getWidth() {
		return width;
	}
	
	public void setIntensity(int x, int y, float grey){
		if(x<width && y<height && y>=0 && x>=0)
			grayScale[x][y]=grey;
	}

}
