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
    
    private int xCoord;
    private int yCoord;

    public Point(int x, int y)
    {
        xCoord = x;
        yCoord = y;
    }
    
    public int getxCoord() {
        return xCoord;
    }

    public void setxCoord(int xCoord) {
        this.xCoord = xCoord;
    }

    public int getyCoord() {
        return yCoord;
    }

    public void setyCoord(int yCoord) {
        this.yCoord = yCoord;
    }
    
}
