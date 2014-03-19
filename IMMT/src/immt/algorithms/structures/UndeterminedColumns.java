package immt.algorithms.structures;

import java.util.Vector;

//Aca se manejan las zonas intedeterminadas del snake, por bifurcaciones, calcificaciones
//y otras yerbas

public class UndeterminedColumns {
	
	private Vector<Integer> columns;
	
	public UndeterminedColumns(){
		columns = new Vector<Integer>();
	}

	public void addColumn(int column){
		columns.add(new Integer(column));
	}
	
	public int getColumn(int numberOfColumn){
		int devolver = columns.get(numberOfColumn).intValue();
		return devolver;
	}
	
	public int getNumberOfColumns(){
		int devolver = columns.size();
		return devolver;
	}
}
