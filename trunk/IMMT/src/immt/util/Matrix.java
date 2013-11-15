/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package immt.util;

/**
 *
 * @author gclaret
 */
public class Matrix {

    private float[] data;

    public int getHeight() {
        return numRows;
    }

    public int getWidth() {
        return numColumns;
    }

    private int numRows;
    private int numColumns;

    public Matrix(int rows, int columns, float[] originalData) {
        data = new float[rows * columns];
        numRows = rows;
        numColumns = columns;
        System.arraycopy(originalData, 0, data, 0, rows * columns);        
    }

    public Matrix(int rows, int columns) {
        data = new float[rows * columns];
        numRows = rows;
        numColumns = columns;
    }

    public float getElementAt(int row, int column) {
        int position = numColumns * row + column;
        System.out.println("GET: " + position);
        return data[position];
    }

    public void setElementAt(int row, int column, float d) {
        int position = numColumns * row + column;
        data[position] = d;
    }

    public float[] getRow(int pos) {
        float[] result = new float[numColumns];
        int iterator = 0;
        int begin = pos * numColumns;
        int end = (pos * numColumns) + numColumns;
        for (int i = begin ; i <end ; i++) {
            result[iterator] = data[i];
            iterator++;
        }
        return result;
    }

    public float[] getColumn(int pos) {
        float[] result = new float[numRows];
        int iterator = 0;
        int begin = pos;
        int end = numRows * numColumns;
        for (int i = begin ; i < end ; i = i + numColumns) {
            result[iterator] = data[i];
            iterator++;
        }
        return result;
    }
    
    public float[] getMatrixData(){
        return data;
    }
    

    public void print() {
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numColumns; j++) {
                System.out.print(data[(i * numColumns) + j] + " ");
            }
            System.out.println(" ");
        }
    }

}
