/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.weblite.codename1.components.charts;

/**
 *
 * @author shannah
 */
public class BorderColors {
    private Color top;
    private Color right;
    private Color bottom;
    private Color left;
    
    public BorderColors(Color c){
        top = right = bottom = left = c;
    }
    
    public BorderColors(Color ns, Color ew){
        top = bottom = ns;
        left = right = ew;
    }
    
    public BorderColors(Color top, Color right, Color bottom, Color left){
        this.top = top;
        this.right = right;
        this.bottom = bottom;
        this.left = left;
    }
    
    public BorderColors top(Color top){this.top=top; return this;}
    public Color top(){return top;}
    public BorderColors right(Color right){this.right=right; return this;}
    public Color right(){return right;}
    public BorderColors bottom(Color bottom){this.bottom=bottom; return this;}
    public Color bottom(){return bottom;}
    public BorderColors left(Color left){this.left=left; return this;}
    public Color left(){return left;}
}
