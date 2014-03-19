package immt.algorithms.snakes.imagedatafunction;

import immt.algorithms.structures.GreyScaleImage;

public class FDelFresno extends AbstractBinaryFunction{
	public double T;
	public GreyScaleImage img;
	public int width;
	public int height;
	public int window;
	public double maxStandardDeviation, maxCoefficent;
	boolean inverseInecuation;
	int[][] matrixF;
	
	public FDelFresno(GreyScaleImage image, double threshold, int _window, boolean inverseInecuation_){
		inverseInecuation = inverseInecuation_;
		T = threshold;
		this.img = image;
		width = img.getWidth();
		height = img.getHeight();
		window = _window;
		calculateF();
		//System.out.println("maxStD: " + maxStandardDeviation + " maxCoe: " + maxCoefficent);
	}
	
	public double F(double x, double y){
		if(x>=0 && y>=0 && x<width && y<height){
			return matrixF[(int)x][(int)y];
		}
		return -1;
	}
	
	private void calculateF(){
		double coefficient;
		maxStandardDeviation = 0;
		maxCoefficent = 0;
		int result = -1;
		matrixF = new int[width][height];
		for(int i=0;i<width;i++){
			for(int j=0;j<height;j++){
				
				coefficient = calculateCoefficient(i,j);
				
				if(coefficient>maxCoefficent) maxCoefficent = coefficient;

				if(coefficient<T)
					result = +1;
				else
					result = -1;
				
				if(inverseInecuation) result = result*(-1);
				matrixF[i][j] = result;

			}
		}
	}

	private double calculateCoefficient(int i, int j){
		double intensity;
		double mean;
		double standardDeviation;
		double coefficient;
		intensity = img.getIntensity(i, j);
		mean = calculateMean(i,j);
		
		standardDeviation = calculateStandardDeviation(i,j,mean);
		if(standardDeviation>maxStandardDeviation) maxStandardDeviation = standardDeviation;
		
		intensity = Math.abs(intensity-mean);
		coefficient = intensity / standardDeviation;
		coefficient = Math.abs(coefficient);
		return coefficient;
	}

	private double calculateMean(int i, int j){
		int sum = 0;
		int total = 0;
		int iaux;
		for(int i2=i-window;i2<=i+window;i2++){
			for(int j2=j-window;j2<=j+window;j2++){
				if(j2>=0 && j2 < img.getHeight()){
					iaux = i2;
					//En polares, los extremos son continuos
					if(iaux<0) iaux = img.getWidth()+iaux;
					if(iaux>=img.getWidth()) iaux=iaux-img.getWidth();

					sum += img.getIntensity(iaux, j2);
					total++;
				}
			}
		}
		return sum / total;
	}

	private double calculateStandardDeviation(int i, int j, double mean){
		double sumaCuadrada = 0;
		double intensity;
		int total = 0;
		int iaux;
		for(int i2=i-window;i2<=i+window;i2++){
			for(int j2=j-window;j2<=j+window;j2++){
				if(j2>=0 && j2 < img.getHeight()){
					iaux = i2;
					//En polares, los extremos son continuos
					if(iaux<0) iaux = img.getWidth()+iaux;
					if(iaux>=img.getWidth()) iaux=iaux-img.getWidth();

					intensity = img.getIntensity(iaux, j2);
					sumaCuadrada += (intensity-mean)*(intensity-mean);
					total++;
				}
			}
		}
		sumaCuadrada = sumaCuadrada / total;
		return Math.sqrt(sumaCuadrada);
	}



}
