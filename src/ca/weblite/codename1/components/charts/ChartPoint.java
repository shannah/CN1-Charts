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
public class ChartPoint extends GeomElement {
    public enum Symbol {
        CIRCLE("circle");
        
        private String val;
        Symbol(String val){
            this.val = val;
        }
        
        public String toString(){
            return val;
        }
    }
    
    private Double radius;
    
    private Symbol symbol;
    
    public ChartPoint radius(Double radius){
        this.radius = radius;
        return this;
    }
    
    public Double radius(){
        return radius;
    }
    
    public Symbol symbol(){
        return symbol;
    }
    
    public ChartPoint symbol(Symbol symbol){
        this.symbol = symbol;
        return this;
    }
    
}
