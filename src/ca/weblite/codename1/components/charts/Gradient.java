/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.weblite.codename1.components.charts;


import com.codename1.io.Log;
import java.util.ArrayList;
import java.util.List;

import org.json.me.JSONArray;
import org.json.me.JSONException;
import org.json.me.JSONObject;

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
    
    JSONObject toJS(Object c){
        try {
            Gradient gradient = this;
            if ( gradient == null ){
                return null;
            }
            
            JSONObject out = new JSONObject();
            
            JSONArray colors = new JSONArray();
            out.put("colors", colors);
            for ( Color color : gradient.colors()){
                System.out.println("About to push color "+color.stringVal());
                colors.put(color.toJS(c));
            }
            System.out.println("Finished pushing color objects");
            
            return out;
        } catch (JSONException ex) {
            Log.e(ex);
            return null;
                    
        }
        
    }
    
}
