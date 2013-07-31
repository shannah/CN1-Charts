/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.weblite.codename1.components.charts;

import ca.weblite.codename1.js.JSObject;
import ca.weblite.codename1.js.JavascriptContext;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author shannah
 */
public class Gradient extends Color {
    public Gradient(){
        super("#ffffff");
    }
    
    
    
    private List<Color> colors = new ArrayList<Color>();
    
    public Gradient add(Color color){
        colors.add(color)
                ;
        return this;
    }
    
    
    
    public Gradient colors(List<Color> colors){this.colors=colors; return this;}
    public List<Color> colors(){return colors;}
    
    JSObject toJS(JavascriptContext c){
        Gradient gradient = this;
        if ( gradient == null ){
            return null;
        }
        
        JSObject out = (JSObject)c.get("{colors:[]}");
        JSObject colors = (JSObject)out.get("colors");
        for ( Color color : gradient.colors()){
            System.out.println("About to push color "+color.stringVal());
            colors.call("push", new Object[]{color.toJS(c)});
        }
        System.out.println("Finished pushing color objects");
        
        return out;
        
    }
    
}
