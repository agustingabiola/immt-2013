package immt.algorithms.snakes.tsnake.polar;

import immt.algorithms.snakes.BaseSnake;
import immt.algorithms.snakes.externalforces.AbstractPotential;
import immt.algorithms.snakes.imagedatafunction.AbstractBinaryFunction;
import immt.algorithms.structures.GreyScaleImage;
import immt.algorithms.structures.UndeterminedColumns;
import java.awt.image.BufferedImage;
import java.util.Vector;


////// McInerney and Terzopoulos 99. T-Snakes. /////////
public class TSnakePolar extends BaseSnake{
	
	public static int GRADIENTE_EN_EL_SENTIDO_DE_LA_NORMAL_ = 1;
	public static int GRADIENTE_CONTRARIO_A_LA_NORMAL_ = -1;
	public static int GRADIENTE_LIBRE = 0;
	
	private double[] internalTensileForcesY;
	private double[] internalFlexuralForceY;
	private double[] externalForceY;
	private double[] normalVectorY;
	private double[] roY;
	
	private GreyScaleImage original;
	private AbstractPotential potentialP;
	private AbstractBinaryFunction functionF;
	double gamma_damping;
	private boolean enableBackward = false;
	
	private double resistanceToStretching;
	private double resistanceToBending;
	private double strengthOfExtF;
	private int direccionDelGradiente;
	
	GreyScaleImage gradienteDelPotential;
	public double minModuleGradient;
	public double maxModuleGradient;
	
	public static final int noSnakePoint = -1;
	
	public boolean exportarIntermedio;
	public Vector<int[]> intermedios;
	
	//Modificacion para zonas indefinidas
	private void initYVectors(UndeterminedColumns columns, int[] internal){
		y = new double[original.getWidth()];
		if(internal!=null)
			for(int i=0;i<original.getWidth();i++){
				y[i]=internal[i];
			}
	
		//Seteo en la snake los puntos que no deben ser de la snake
		for(int i=0; i< columns.getNumberOfColumns(); i++){
			try{
			y[columns.getColumn(i)]=noSnakePoint;}
			catch(Exception e){e.printStackTrace(); System.out.println("out of bound xq i:" +i +" columns son" + columns.getNumberOfColumns() + " y.lenght" + y.length);}
			}
	}
	
	public double[] getY(){
		int size = original.getWidth();
		double[] yInt = new double[size];
		for(int i=0;i<size;i++){
			yInt[i]= y[i];
		}
		return yInt;
	}
	
	public BufferedImage ejecutar(//Gradient g, BufferedImage original_, float radiusGaussFilter,
			GreyScaleImage polar_, UndeterminedColumns undetermineds,
			AbstractPotential potentialP_, AbstractBinaryFunction functionF_,
			double gamma_damping_, double amplitude_inflation_force,
			double a_stretching, double b_bending, double p_strengthOfExtF, double deltaT,
			int iterations_, int[] internal, boolean enableBackward_, int direccionDelGradiente_){
		
		enableBackward = enableBackward_;
		
		potentialP = potentialP_;
		gradienteDelPotential = new GreyScaleImage(polar_.getWidth(),polar_.getHeight());
		minModuleGradient = maxModuleGradient = -1;
		
		functionF = functionF_;
		
		gamma_damping = gamma_damping_;
		iterations = iterations_;
		double q = amplitude_inflation_force;
		resistanceToStretching = a_stretching;
		resistanceToBending = b_bending;
		strengthOfExtF = p_strengthOfExtF;
		direccionDelGradiente = direccionDelGradiente_; 
		
		original = polar_;
		initYVectors(undetermineds, internal);
		
		intermedios = new Vector<int[]>();
		
		for(int i=0;i<iterations;i++){
			if(((i< 20) || i%10==0) && exportarIntermedio)
				salvarSnake();
			
			//TODO:: PASO en la snake recalcularXY(g,P);
			calculateInternalForces();
			//calculateUnitNormalVector();
			calculateInflationForces(q);
			calculateExternalForces();
			motionByEuler(deltaT);
			
			//printXY();
		}
		
		BufferedImage bmp = generarImg("tsnakePolar-Stre"+resistanceToStretching+"-Bend"+resistanceToBending,iterations_,16776960,polar_,null,normalVectorY);
		return bmp;
	}
	
	private void motionByEuler(double deltaT){
		double yTemporal;
		double[] yN1 = new double[y.length];
		for(int i=0;i<y.length;i++){
			if(y[i]!=noSnakePoint){
				
				yTemporal = y[i] - (deltaT/gamma_damping) * (resistanceToStretching * internalTensileForcesY[i] + resistanceToBending * internalFlexuralForceY[i] - roY[i] - externalForceY[i]);
				
				if(yTemporal<0.)yTemporal=0;
				if(enableBackward) // No me importa si va para atras o adelante
					yN1[i] = yTemporal;
				else{
					if(y[i]<=yTemporal)
						yN1[i]=yTemporal; //Si es igual o mayor, lo reemplazo
					else //else queda igual
						yN1[i]= y[i];
				}
			}
			else
				yN1[i] = noSnakePoint;
		}
		y = yN1;
	}
	
	private void calculateInternalForces(){
		internalTensileForcesY = new double[y.length];
		for(int i=0;i<y.length;i++){
			internalTensileForcesY[i] = calculateInternalTensileForceY(i);
		}
		
		internalFlexuralForceY = new double[y.length];
		for(int i=0;i<y.length;i++){
			internalFlexuralForceY[i] = calculateInternalFlexuralForceY(i);
		}
		
	}
	
	//Modificado para columnas indeterminadas
	private double calculateInternalTensileForceY(int i){
		int i_1;
		int i1;
		
		if(i==0) i_1 = y.length-1;
		else i_1 = i-1;
		
		if(i==y.length-1) i1 = 0;
		else i1 = i+1;
		
		double alpha = 0;
		
		if(y[i]==noSnakePoint) return 0;
		int multiplicador = 1;
		
		if(y[i_1]!=noSnakePoint){//No sumo la derivada de puntos indeterminados
			alpha += y[i]-y[i_1];
		} else multiplicador++;
		
		if(y[i1]!=noSnakePoint){//No sumo la derivada de puntos indeterminados
			alpha += y[i]-y[i1];
		} else multiplicador++;//Para que no me desbalance la cuenta, duplico la fuerza del lado que existe
		
		return alpha * multiplicador;
	}
	
	private double calculateInternalFlexuralForceY(int i){
		
		int i_1;
		int i1;
		
		if(i==0) i_1 = y.length-1;
		else i_1 = i-1;
		
		if(i==y.length-1) i1 = 0;
		else i1 = i+1;
		
		double beta = 0;
		int multiplicador = 1;
		
		if(y[i]==noSnakePoint) return 0;
		
		if(y[i_1]!=noSnakePoint){//No sumo la derivada de puntos indeterminados
			beta += internalTensileForcesY[i]-internalTensileForcesY[i_1];
		}  else multiplicador++;//Para que no me desbalance la cuenta, duplico la fuerza del lado que existe
		
		if(y[i1]!=noSnakePoint){//No sumo la derivada de puntos indeterminados
			beta += internalTensileForcesY[i]-internalTensileForcesY[i1];
		}  else multiplicador++;//Para que no me desbalance la cuenta, duplico la fuerza del lado que existe
		
		return beta * multiplicador;
	
	}
	

	private void calculateInflationForces(double q){
		roY = new double[y.length];
		for(int i=0;i<roY.length;i++){
			if(y[i]!=noSnakePoint)
				roY[i] = q * F(i);
			else 
				roY[i] = 0;
		}
	}
	
	private double F(int i){
		double x1 = i;
		double y1 = y[i];
		double f = functionF.F(x1, y1);
		return f;
	}
	
	private void calculateExternalForces(){
		externalForceY = new double[y.length];

		for(int i=0;i<externalForceY.length;i++){
			double gPY = gradientPY(i);
			gradienteDelPotential.setIntensity(i, (int)y[i], (float)gPY);
			if(minModuleGradient==-1 || gPY<minModuleGradient) minModuleGradient=gPY;
			if(maxModuleGradient==-1 || gPY>maxModuleGradient) maxModuleGradient=gPY;
			externalForceY[i] = strengthOfExtF * gPY;
		}	
	}
	
	private double gradientPY(int i){
		//Aca se usa el abstract external force
		double x1 = i;
		double y1 = y[i];
		//if(y1!=noSnakePoint){
		if(y1>=0){
			double gradientpy = potentialP.getPotentialGradient((int)x1, (int)y1);
			if((direccionDelGradiente==GRADIENTE_EN_EL_SENTIDO_DE_LA_NORMAL_ && gradientpy > 0) || (direccionDelGradiente==GRADIENTE_CONTRARIO_A_LA_NORMAL_ && gradientpy < 0)) 
				return gradientpy;
			//El 0 es que no importa
			if(direccionDelGradiente == GRADIENTE_LIBRE)
				return gradientpy;
			//En otro caso, que no mueva la snake
			return gradientpy * -1;
			}
		else
			return 0;
	}
	
	private void salvarSnake(){
		int[] copia = new int[y.length];
		//ToPolar topolar = new ToPolar();
		for(int i=0;i<y.length;i++){
			copia[i]=(int)(y[i]);
		}
		intermedios.add(copia);
	}
	
}
