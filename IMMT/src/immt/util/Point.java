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
public class Point {
    
    private double xCoord;
    private double yCoord;

    public Point(int x, int y)
    {
        xCoord = x;
        yCoord = y;
    }
    
    public Point(double x, double y)
    {
        xCoord = x;
        yCoord = y;
    }
    
    public Point(){};
            
    public double getxCoord() {
        return xCoord;
    }

    public void setxCoord(double xCoord) {
        this.xCoord = xCoord;
    }

    public double getyCoord() {
        return yCoord;
    }

    public void setyCoord(double yCoord) {
        this.yCoord = yCoord;
    }
    
    @Override
    public String toString()
    {
       return "x: " + xCoord + "  y: " + yCoord;        
    }
    
}
