/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.weblite.codename1.components.charts;

import static ca.weblite.codename1.components.charts.JSUtil.set;

import org.json.me.JSONObject;

/**
 *
 * @author shannah
 */
public class Color {
    private String stringVal;
    private Double opacity;
    private Double brightness;

    public Color(String stringVal){
        this.stringVal = stringVal;
    }
    
    public Color(String stringVal, double opacity){
        this.stringVal = stringVal;
        this.opacity = new Double(opacity);
        
    }
    
    public Color(String stringVal, double opacity, double brightness){
        this.stringVal = stringVal;
        this.opacity = new Double(opacity);
        this.brightness = new Double(brightness);
    }
    
    public Color stringVal(String stringVal){this.stringVal=stringVal; return this;}
    public String stringVal(){return stringVal;}
    public Color opacity(Double opacity){this.opacity=opacity; return this;}
    public Double opacity(){return opacity;}
    public Color brightness(Double brightness){this.brightness=brightness; return this;}
    public Double brightness(){return brightness;}
    
    
    Object toJS(Object c){
        Color color = this;
        
        if ( color.brightness() == null && color.opacity() == null ){
            
            return color.stringVal();
        } else {
            JSONObject out = new JSONObject();
            set(out, "opacity", color.opacity());
            set(out, "brightness", color.brightness());
            return out;
        }
  
    }
}
