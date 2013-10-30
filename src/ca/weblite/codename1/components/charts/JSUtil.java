/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.weblite.codename1.components.charts;

import com.codename1.io.Log;
import org.json.me.JSONException;
import org.json.me.JSONObject;


/**
 *
 * @author shannah
 */
class JSUtil {
    static void set(JSONObject o, String key, Object value){
        if ( value != null ){
            try {
                o.put(key, value);
            } catch (JSONException ex) {
                Log.e(ex);
            }
        }
    }
}