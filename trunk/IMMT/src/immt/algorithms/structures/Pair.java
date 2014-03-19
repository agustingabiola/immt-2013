/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package immt.algorithms.structures;

/**
 *
 * @author José Ignacio Orlando
 */
public class Pair {
 
    private int x;
    private int y;

    
    public Pair(int x, int y) {
        this.x=x;
        this.y=y;
    }
    
    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
    
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
    
    @Override
    public boolean equals(Object o) {
        Pair p = (Pair) o;
        return ((p.getX()==this.getX()) && (p.getY()==this.getY()));
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + this.x;
        hash = 71 * hash + this.y;
        return hash;
    }
    
    public Pair getOpposite() {
        return new Pair(this.y,this.x);
    }
    
    @Override
    public String toString() {
        return "(" + this.x + "," + this.y + ")";
    }
    
}
