/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.weblite.codename1.components.charts;

import static ca.weblite.codename1.components.charts.JSUtil.set;

import com.codename1.ui.Font;
import java.util.List;
import org.json.me.JSONArray;
import org.json.me.JSONObject;

/**
 *
 * @author shannah
 */
public class Axis {
    public enum Position {
        BOTTOM("bottom"),
        TOP("top"),
        LEFT("left"),
        RIGHT("right");
        
        private String val;
        
        Position(String val){
            this.val = val;
        }
        
        @Override
        public String toString(){
            return val;
        }
    }
    
    public enum Mode {
        TIME("time");
        
        private String val;
        Mode(String val){
            this.val = val;
        }
        
        public String toString(){
            return val;
        }
    }
    
    public static class Tick {
        private String label;
        private Double value;
        
        public Tick label(String label){this.label=label; return this;}
        public String label(){return label;}
        public Tick value(Double value){this.value=value; return this;}
        public Double value(){return value;}
        
        Object toJS(Object c){
            if ( label != null ){
                JSONArray out = new JSONArray();
                out.put(value);
                out.put(label);
                return out;
            } else {
                return value;
            }
            
        }
    }
    
    private Boolean show;
    private Position position;
    private Mode mode;
    private String timeZone;
    private Color color;
    private Color tickColor;
    private String font;
    private Double min;
    private Double max;
    private Double autoscaleMargin;
    private Integer ticks;
    private Double tickSize;
    private List<Double> tickSizeArr;
    private List<Tick> ticksArr;
    private Double minTickSize;
    
    private Double labelWidth;
    private Double labelHeight;
    private Double reserveSpace;
    private Integer tickLength;
    private Integer alignTicksWithAxis;
    
    
    public JSONObject toJS(Object c){
        JSONObject out = new JSONObject();
        set(out, "show", show);
        set(out, "position", position);
        set(out, "mode", mode);
        set(out, "timezone", timeZone);
        if ( color != null ) set(out, "color", color.toJS(c));
        if ( tickColor != null ) set(out, "tickColor", tickColor.toJS(c));
        set(out, "font", font);
        set(out, "min", min);
        set(out, "max", max);
        set(out, "autoscaleMargin", autoscaleMargin);
        set(out, "ticks", ticks);
        if ( ticksArr != null ){
            JSONArray ticksa = new JSONArray();
            for ( Tick t : ticksArr ){
                ticksa.put(t.toJS(c));
            
            }
            set(out, "ticks", ticksa);
        }
        
        set(out, "minTickSize", minTickSize);
        set(out, "tickSize", tickSize);
        if ( tickSizeArr != null ){
            JSONArray tickSizeA = new JSONArray();
            for ( Double d : tickSizeArr){
                tickSizeA.put(d);
            }
            set(out, "tickSize", tickSizeA);
        }
        
        set(out, "labelWidth", labelWidth);
        set(out, "labelHeight", labelHeight);
        set(out, "reserveSpace", reserveSpace);
        set(out, "tickLength", tickLength);
        set(out, "alignTicksWithAxis", alignTicksWithAxis);
        return out;
        
    }

    
    public Axis show(Boolean show){this.show=show; return this;}
    public Boolean show(){return show;}
    public Axis position(Position position){this.position=position; return this;}
    public Position position(){return position;}
    public Axis mode(Mode mode){this.mode=mode; return this;}
    public Mode mode(){return mode;}
    public Axis timeZone(String timeZone){this.timeZone=timeZone; return this;}
    public String timeZone(){return timeZone;}
    public Axis color(Color color){this.color=color; return this;}
    public Color color(){return color;}
    public Axis tickColor(Color tickColor){this.tickColor=tickColor; return this;}
    public Color tickColor(){return tickColor;}
    public Axis font(String font){this.font=font; return this;}
    public String font(){return font;}
    public Axis min(Double min){this.min=min; return this;}
    public Double min(){return min;}
    public Axis max(Double max){this.max=max; return this;}
    public Double max(){return max;}
    public Axis autoscaleMargin(Double autoscaleMargin){this.autoscaleMargin=autoscaleMargin; return this;}
    public Double autoscaleMargin(){return autoscaleMargin;}
    public Axis ticks(Integer ticks){this.ticks=ticks; return this;}
    public Integer ticks(){return ticks;}
    public Axis tickSize(Double tickSize){this.tickSize=tickSize; return this;}
    public Double tickSize(){return tickSize;}
    public Axis tickSizeArr(List<Double> tickSizeArr){this.tickSizeArr=tickSizeArr; return this;}
    public List<Double> tickSizeArr(){return tickSizeArr;}
    public Axis ticksArr(List<Tick> ticksArr){this.ticksArr=ticksArr; return this;}
    public List<Tick> ticksArr(){return ticksArr;}
    public Axis minTickSize(Double minTickSize){this.minTickSize=minTickSize; return this;}
    public Double minTickSize(){return minTickSize;}
    public Axis labelWidth(Double labelWidth){this.labelWidth=labelWidth; return this;}
    public Double labelWidth(){return labelWidth;}
    public Axis labelHeight(Double labelHeight){this.labelHeight=labelHeight; return this;}
    public Double labelHeight(){return labelHeight;}
    public Axis reserveSpace(Double reserveSpace){this.reserveSpace=reserveSpace; return this;}
    public Double reserveSpace(){return reserveSpace;}
    public Axis tickLength(Integer tickLength){this.tickLength=tickLength; return this;}
    public Integer tickLength(){return tickLength;}
    public Axis alignTicksWithAxis(Integer alignTicksWithAxis){this.alignTicksWithAxis=alignTicksWithAxis; return this;}
    public Integer alignTicksWithAxis(){return alignTicksWithAxis;}



    
}
