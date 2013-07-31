/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.weblite.codename1.components.charts;

/**
 *
 * @author shannah
 */
public class Bar extends GeomElement {
    public enum Align {
        LEFT("left"),
        CENTER("center"),
        RIGHT("right");
        private String val;
        Align(String val){
            this.val = val;
        }
        
        public String toString(){
            return this.val;
        }
    }
    private Boolean zero;
    private Double barWidth;
    private Align align;
    private Double horizontal;
    

    public Bar zero(Boolean zero){this.zero=zero; return this;}
    public Boolean zero(){return zero;}
    public Bar barWidth(Double barWidth){this.barWidth=barWidth; return this;}
    public Double barWidth(){return barWidth;}
    public Bar align(Align align){this.align=align; return this;}
    public Align align(){return align;}
    public Bar horizontal(Double horizontal){this.horizontal=horizontal; return this;}
    public Double horizontal(){return horizontal;}

    
}
