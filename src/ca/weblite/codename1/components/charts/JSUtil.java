/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.weblite.codename1.components.charts;

import ca.weblite.codename1.js.JSObject;

/**
 *
 * @author shannah
 */
class JSUtil {
    static void set(JSObject o, String key, Object value){
        if ( value != null ){
            o.set(key, value);
        }
    }
}
