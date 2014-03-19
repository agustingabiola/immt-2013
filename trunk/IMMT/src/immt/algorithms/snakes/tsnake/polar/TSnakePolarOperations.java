package immt.algorithms.snakes.tsnake.polar;

import immt.algorithms.structures.Pair;
import java.util.Vector;

public class TSnakePolarOperations {
	
	public boolean detectColitions(int[] polarEdgeInterior, int[] polarEdgeExterior, boolean intercambiar){
		boolean encontrado = false;
		int aux;
		for(int i=0;i<polarEdgeInterior.length;i++){
			if(polarEdgeInterior[i]!=TSnakePolar.noSnakePoint){
				if(polarEdgeInterior[i]>polarEdgeExterior[i]){
					encontrado = true;
					if(intercambiar){
						aux = polarEdgeInterior[i];
						polarEdgeInterior[i] = polarEdgeExterior[i];
						polarEdgeExterior[i] = aux;
					}
					else
						return true;
					
				}
			}
		}
		return encontrado;
	}
	
	
	public int[] moveSnake(int[] polarEdge, boolean internal, float scaleFactor, float displacementInPX){
		int size = polarEdge.length;
		float point;
		int[] devolver = new int[size];
		float factor = scaleFactor;
		
		float min = -1;
		float max = -1;
		
		for(int i=0;i<size;i++){
			point = polarEdge[i];
			if(point != -1){
				if(min==-1 || point<min) min = point;
				if(point>max) max = point;
			}
		}
		
		System.out.println("INICIAL min: " + min + " max:" + max + " factor:" + factor);
		
		min = -1;
		max = -1;
		
		if(internal){
			//Los mas lejos al cateter se contraen mas
			for(int i=0;i<size;i++){
				point = polarEdge[i];
				if(point != TSnakePolar.noSnakePoint){
					devolver[i] = (int) (point + (min-point) * factor - displacementInPX);
					if(min==-1 || devolver[i]<min) min = devolver[i];
					if(devolver[i]>max) max = devolver[i];
				}
				else
					devolver[i] = TSnakePolar.noSnakePoint;
			}
		}
		else{
			//Los mas cercanos al cateter se expanden m�s
			for(int i=0;i<size;i++){
				point = polarEdge[i];
				if(point != TSnakePolar.noSnakePoint){
					devolver[i] = (int) (point + (max-point) * factor + displacementInPX);
					if(min==-1 || devolver[i]<min) min = devolver[i];
					if(devolver[i]>max) max = devolver[i];
				}
				else
					devolver[i] = TSnakePolar.noSnakePoint;
			}
		}
		
		System.out.println("INICIAL2 min: " + min + " max:" + max + " factor:" + factor);
		
		
		return devolver;
	}
	
	public int[] suavizarTSnakePolar(int[] t, int radio){
		int size = t.length;
		int[] devolver = new int[size];
		for(int i=0;i<size;i++){
			devolver[i]=calculateSmoothedPoint(t, radio, i);
		}
		return devolver;
	}
		
	private int calculateSmoothedPoint(int[] t, int radio, int x){
		int size = t.length;
		int suma = 0;
		int contador = 0;
		if(t[x]==TSnakePolar.noSnakePoint) return TSnakePolar.noSnakePoint;
		for(int i=x-radio;i<=x+radio;i++){
			if(i>=8 && i<=size-8 && t[i]!=TSnakePolar.noSnakePoint){
				suma += t[i];
				contador++;
			}
			else{
				if(i<-8 && t[size+i]>TSnakePolar.noSnakePoint){ suma +=t[size+i]; contador++; }
				if(i>size+8 && t[i-size]>TSnakePolar.noSnakePoint){ suma +=t[i-size]; contador++; }
				
			}
		}
		if (contador == 0) return TSnakePolar.noSnakePoint;
		return suma / contador;
	}
	
	public int[] suavizarTSnakePolarMediana(int[] t, int radio){
		int size = t.length;
		int[] devolver = new int[size];
		for(int i=0;i<size;i++){
			devolver[i]=calculateSmoothedPointMediana(t, radio, i);
		}
		return devolver;
	}
		
	private int calculateSmoothedPointMediana(int[] t, int radio, int x){
		int size = t.length;
		Vector<Integer> v = new Vector<Integer>();
		if(t[x]==TSnakePolar.noSnakePoint) return TSnakePolar.noSnakePoint;
		for(int i=x-radio;i<=x+radio;i++){
			if(i>=8 && i<size-8 && t[i]!=TSnakePolar.noSnakePoint){ //Los extremos estan viciados por el filtro anisotropico
				v.add(new Integer(t[i]));
			}
			else{
				if(i<-8 && t[size+i]>TSnakePolar.noSnakePoint)
					v.add(new Integer(t[size+i]));
				if(i>=size+8 && t[i-size]>TSnakePolar.noSnakePoint)
					v.add(new Integer(t[i-size]));
			}
		}
		
		if (v.size() == 0) return TSnakePolar.noSnakePoint;
		
		int mediana = v.size() / 2;
		//System.out.println("mediana: " + mediana + " total: " + v.size());
		return v.get(mediana).intValue();
	}
	
	public Vector<Pair> toVectorOfPair(int[] tsnake){
		Vector<Pair> v = new Vector<Pair>();
		int size = tsnake.length;
		Pair aux;
		for(int i=0;i<size;i++){
			aux = new Pair(i, tsnake[i]);
			v.add(aux);
		}
		return v;
	}

	/////////////////COMPLETAR NO SNAKE POINTS/////////////////////////////
	private float calcularPendiente(int x1, int y1, int x2, int y2){
		return ((float)y1-y2)/((float)x1-x2);
	}
	
	private float calcularOrdenada(int x1, int y1, float pendiente){
		return ((float)y1)-pendiente*x1;
	}
	
	private int getFirstNoSnakePoint(int[] tsnake, int i, int height){
		int pos = i;
		int size = tsnake.length;
		//System.out.println("getFirstNoSnakePoint - Primer punto:" + pos);
		while(pos<size){
			if(tsnake[pos]==TSnakePolar.noSnakePoint){
				//System.out.println("getFirstNoSnakePoint:" + pos);
				return pos;
			}
			else pos++;
		}
		return -1;
	}

	private int getLastNoSnakePoint(int[] tsnake, int i, int height){
		int pos = i+1;
		int size = tsnake.length;
		//System.out.println("getLastNoSnakePoint - Primer punto:" + pos);
		while(pos!=i){
			if(tsnake[pos]!=TSnakePolar.noSnakePoint && tsnake[pos]>=0 && tsnake[pos]<height){
				//System.out.println("getLastNoSnakePoint:" + pos);
				return pos;
			}
			else{
				if(pos==size-1) pos = 0;//Volver al ppcio de la snake
				else pos++;
			}
		}
		return pos-1;
	}
	
	private void interpolar(int[] tsnake, int[] devolver, int firstNoSnakePoint, int lastSnakePoint, float pendiente, float ordenada){
		int size = tsnake.length;
		int fin = lastSnakePoint;
		if(lastSnakePoint<firstNoSnakePoint) fin = size; 
		for(int i=firstNoSnakePoint;i<fin;i++){ //De first hacia adelante
			float y = pendiente*i + ordenada;
			devolver[i]=(int)y;
		}
		
		if(fin==size)
			for(int i=0;i<lastSnakePoint && i<size;i++){ //Por si paso la vuelta
				float y = pendiente*(size+i) + ordenada;
				devolver[i]=(int)y;
			}
	}

	public int[] completarTSnake(int[] tsnake, int height){
		int size = tsnake.length;
		int[] devolver = new int[size];
		
		for(int i=0;i<size;i++) devolver[i] = tsnake[i];//copio una en otra
		
		for(int i=getLastNoSnakePoint(tsnake, 0, height);i<size;i++){ //Ni me enrosco hacia atras, empiezo a iterar desde donde seguro hay algo
			int firstNoSnakePoint = getFirstNoSnakePoint(tsnake, i, height);
			int lastSnakePoint = getLastNoSnakePoint(tsnake, firstNoSnakePoint, height);
			if(firstNoSnakePoint>-1){
				int x2;
				if(lastSnakePoint<firstNoSnakePoint) x2 = size+lastSnakePoint;
				else x2 = lastSnakePoint;
					
				float pendiente = calcularPendiente(firstNoSnakePoint-1, tsnake[firstNoSnakePoint-1], x2, tsnake[lastSnakePoint]);
				float ordenada = calcularOrdenada(firstNoSnakePoint-1, tsnake[firstNoSnakePoint-1], pendiente);
				interpolar(tsnake, devolver, firstNoSnakePoint, lastSnakePoint, pendiente, ordenada);
				if(lastSnakePoint<firstNoSnakePoint) return devolver;//Pego la vuelta e interpolo, devolverlo
				else i=lastSnakePoint;
			}
		}
		return devolver;
	}
	
	public int[] completarTSnake2(int[] tsnake, int height){
		int size = tsnake.length;
		int[] devolver = new int[size];
		int i,i2=0;
		for(i=0;i<size;i++) devolver[i] = tsnake[i];//copio una en otra
		
		for(i=0;i<size;i++){ //Ni me enrosco hacia atras, empiezo a iterar desde donde seguro hay algo
			while(devolver[i]==TSnakePolar.noSnakePoint && i<size)i++;
			if(i<size){
				i2=i+1;
				while(i2<size && devolver[i2]==TSnakePolar.noSnakePoint)i2++;
				if(i2<size){
					float pendiente = calcularPendiente(i, tsnake[i], i2, tsnake[i2]);
					float ordenada = calcularOrdenada(i, tsnake[i], pendiente);
					interpolar(tsnake, devolver, i, i2, pendiente, ordenada);
				}
				i=i2-1;
			}
		}
		
		//Completar inicio y fin de la imagen, la parte por donde se corta
		int x1=size-1;
		int x2=0;
		while(x1>0 && devolver[x1]==TSnakePolar.noSnakePoint)x1--;
		while(x2<size && devolver[x2]==TSnakePolar.noSnakePoint)x2++;
		
		int x2prima = size + x2;
		float pendiente = calcularPendiente(x1, tsnake[x1], x2prima, tsnake[x2]);
		float ordenada = calcularOrdenada(x1, tsnake[x1], pendiente);
		//Completar fin de la imagen
		interpolar(tsnake, devolver, x1, size-1, pendiente, ordenada);
		//Ultimo pixel
		while(x1>0 && devolver[x1]==TSnakePolar.noSnakePoint)x1--;
		int yaux = devolver[x1];
		while(x1<=size-1) {devolver[x1]=yaux; x1++;}
		
		//Completar inicio de la imagen
		
		
		return devolver;
	}



}
