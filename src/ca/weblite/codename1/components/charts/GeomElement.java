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
public class GeomElement {
    private Boolean show;
    private Integer lineWidth;
    private Boolean fill;
    private Color fillColor;
    
    public GeomElement show(Boolean show){this.show=show; return this;}
    public Boolean show(){return show;}
    public GeomElement lineWidth(Integer lineWidth){this.lineWidth=lineWidth; return this;}
    public Integer lineWidth(){return lineWidth;}
    public GeomElement fill(Boolean fill){this.fill=fill; return this;}
    public Boolean fill(){return fill;}
    public GeomElement fillColor(Color fillColor){this.fillColor=fillColor; return this;}
    public Color fillColor(){return fillColor;}
}
