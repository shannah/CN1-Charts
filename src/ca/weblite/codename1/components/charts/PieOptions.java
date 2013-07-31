/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.weblite.codename1.components.charts;

import ca.weblite.codename1.js.JSObject;
import ca.weblite.codename1.js.JavascriptContext;
import static ca.weblite.codename1.components.charts.JSUtil.set;

/**
 *
 * @author shannah
 */
public class PieOptions  {
    
    public static class ShadowOptions {
        private Integer top;
        private Integer left;
        private Double alpha;
        
        public ShadowOptions top(Integer top){this.top=top; return this;}
        public Integer top(){return top;}
        public ShadowOptions left(Integer left){this.left=left; return this;}
        public Integer left(){return left;}
        public ShadowOptions alpha(Double alpha){this.alpha=alpha; return this;}
        public Double alpha(){return alpha;}
        
        JSObject toJS(JavascriptContext c){
            JSObject out = (JSObject)c.get("{}");
            set(out, "top", top);
            set(out, "left", left);
            set(out, "alpha", alpha);
            return out;
        }
        
    }
    
    public static class OffsetOptions {
        private Integer top;
        private Integer left;
        
        public OffsetOptions top(Integer top){this.top=top; return this;}
        public Integer top(){return top;}
        public OffsetOptions left(Integer left){this.left=left; return this;}
        public Integer left(){return left;}
        
        JSObject toJS(JavascriptContext c){
            JSObject out = (JSObject)c.get("{}");
            set(out, "top", top);
            set(out, "left", left);
            return out;
        }
    }
    
    public static class StrokeOptions {
        private Color color;
        private Integer width;
        
        public StrokeOptions color(Color color){this.color=color; return this;}
        public Color color(){return color;}
        public StrokeOptions width(Integer width){this.width=width; return this;}
        public Integer width(){return width;}
        
        JSObject toJS(JavascriptContext c){
            JSObject out = (JSObject)c.get("{}");
            if ( color != null ) set(out, "color", color.toJS(c));
            set(out, "width", width);
            return out;
        }
        
    }
    
    public static class LabelOptions {
        private Boolean show;
        private Double radius;
        private Double threshold;
        
        public LabelOptions show(Boolean show){this.show=show; return this;}
        public Boolean show(){return show;}
        public LabelOptions radius(Double radius){this.radius=radius; return this;}
        public Double radius(){return radius;}
        public LabelOptions threshold(Double threshold){this.threshold=threshold; return this;}
        public Double threshold(){return threshold;}
        
        Object toJS(JavascriptContext c){
            JSObject out = (JSObject)c.get("{}");
            set(out, "show", show);
            set(out, "radius", radius);
            set(out, "threshold", threshold);
            return out;
        }
    }
    
    public static class CombineOptions {
        private Double threshold;
        private Color color;
        private String label;
        
        public CombineOptions threshold(Double threshold){this.threshold=threshold; return this;}
        public Double threshold(){return threshold;}
        public CombineOptions color(Color color){this.color=color; return this;}
        public Color color(){return color;}
        public CombineOptions label(String label){this.label=label; return this;}
        public String label(){return label;}
        
        
        Object toJS(JavascriptContext c){
            JSObject out = (JSObject)c.get("{}");
            set(out, "threshold", threshold);
            set(out, "color", color.toJS(c));
            set(out, "label", label);
            return out;
        }
    }
    
    private Boolean show;
    private Double radius;
    private Double innerRadius;
    private Double startAngle;
    private Double tilt;
    
    private ShadowOptions shadow;
    private OffsetOptions offset;
    private StrokeOptions stroke;
    
    private LabelOptions label;
    private Color backgroundColor;
    private CombineOptions combine;

    private Double highlightOpacity;
    
    Object toJS(JavascriptContext c){
        JSObject out = (JSObject)c.get("{}");
        set(out, "show", show);
        set(out, "radius", radius);
        set(out, "innerRadius", innerRadius);
        set(out, "startAngle", startAngle);
        set(out, "tilt", tilt);
        if ( shadow != null ) set(out, "shadow", shadow.toJS(c));
        if ( offset != null ) set(out, "offset", offset.toJS(c));
        if ( stroke != null ) set(out, "stroke", stroke.toJS(c));
        if ( label != null ) set(out, "label", label.toJS(c));
        if ( backgroundColor != null ){
            JSObject bg = (JSObject)c.get("{}");
            set(bg, "color", backgroundColor.stringVal());
            set(bg, "opacity", backgroundColor.opacity());
            set(out, "background", bg);
        }
        if ( combine != null ) set(out, "combine", combine.toJS(c));
        set(out, "highlightOpacity", highlightOpacity);
        return out;
    }
         
    
    public PieOptions show(Boolean show){this.show=show; return this;}
    public Boolean show(){return show;}
    public PieOptions radius(Double radius){this.radius=radius; return this;}
    public Double radius(){return radius;}
    public PieOptions innerRadius(Double innerRadius){this.innerRadius=innerRadius; return this;}
    public Double innerRadius(){return innerRadius;}
    public PieOptions startAngle(Double startAngle){this.startAngle=startAngle; return this;}
    public Double startAngle(){return startAngle;}
    public PieOptions tilt(Double tilt){this.tilt=tilt; return this;}
    public Double tilt(){return tilt;}
    public PieOptions shadow(ShadowOptions shadow){this.shadow=shadow; return this;}
    public ShadowOptions shadow(){return shadow;}
    public PieOptions offset(OffsetOptions offset){this.offset=offset; return this;}
    public OffsetOptions offset(){return offset;}
    public PieOptions stroke(StrokeOptions stroke){this.stroke=stroke; return this;}
    public StrokeOptions stroke(){return stroke;}
    public PieOptions label(LabelOptions label){this.label=label; return this;}
    public LabelOptions label(){return label;}
    public PieOptions backgroundColor(Color backgroundColor){this.backgroundColor=backgroundColor; return this;}
    public Color backgroundColor(){return backgroundColor;}
    public PieOptions combine(CombineOptions combine){this.combine=combine; return this;}
    public CombineOptions combine(){return combine;}
    public PieOptions highlightOpacity(Double highlightOpacity){this.highlightOpacity=highlightOpacity; return this;}
    public Double highlightOpacity(){return highlightOpacity;}
    
}
