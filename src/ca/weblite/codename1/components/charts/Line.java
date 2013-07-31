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
public class Line extends GeomElement {
    private Boolean zero;
    private Boolean steps;
    public Line zero(Boolean zero){this.zero=zero; return this;}
    public Boolean zero(){return zero;}
    public Line steps(Boolean steps){this.steps=steps; return this;}
    public Boolean steps(){return steps;}

}
