/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.weblite.codename1.components.charts;

/**
 *
 * @author shannah
 */
public class BorderWidths {
    private Integer top;
    private Integer bottom;
    private Integer left;
    private Integer right;
    
    public BorderWidths(int w){
        top = bottom = left = right = new Integer(w);
    }
    
    public BorderWidths(int ns, int ew){
        top = bottom = new Integer(ns);
        left = right = new Integer(ew);
    }
    
    public BorderWidths(int top, int right, int bottom, int left){
        this.top = new Integer(top);
        this.right = new Integer(right);
        this.bottom = new Integer(bottom);
        this.left = new Integer(left);
    }
    
    public BorderWidths top(Integer top){this.top=top; return this;}
    public Integer top(){return top;}
    public BorderWidths bottom(Integer bottom){this.bottom=bottom; return this;}
    public Integer bottom(){return bottom;}
    public BorderWidths left(Integer left){this.left=left; return this;}
    public Integer left(){return left;}
    public BorderWidths right(Integer right){this.right=right; return this;}
    public Integer right(){return right;}
}
