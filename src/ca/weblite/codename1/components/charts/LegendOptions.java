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
public class LegendOptions {
    
    public enum Position {
        NE("ne"),
        NW("nw"),
        SE("se"),
        SW("sw");
        
        private String val;
        Position(String val){ this.val = val;}
        @Override
        public String toString(){
            return val;
        }
    }
    
    public enum Sort {
        ASCENDING("ascending"),
        DESCENDING("descending"),
        REVERSE("reverse");
        
        private String val;
        Sort(String val){ this.val = val;}
        @Override
        public String toString(){
            return val;
        }
        
    }
    
    private Boolean show = false;
    private Color labelBoxBorderColor;
    private Integer noColumns = 2;
    private Position position = Position.NE;
    private Integer margin = 5;
    private Color backgroundColor;
    private Double backgroundOpacity = 1d;
    private Sort sorted = Sort.ASCENDING;


    public LegendOptions show(Boolean show){this.show=show; return this;}
    public Boolean show(){return show;}
    public LegendOptions labelBoxBorderColor(Color labelBoxBorderColor){this.labelBoxBorderColor=labelBoxBorderColor; return this;}
    public Color labelBoxBorderColor(){return labelBoxBorderColor;}
    public LegendOptions noColumns(Integer noColumns){this.noColumns=noColumns; return this;}
    public Integer noColumns(){return noColumns;}
    public LegendOptions position(Position position){this.position=position; return this;}
    public Position position(){return position;}
    public LegendOptions margin(Integer margin){this.margin=margin; return this;}
    public Integer margin(){return margin;}
    public LegendOptions backgroundColor(Color backgroundColor){this.backgroundColor=backgroundColor; return this;}
    public Color backgroundColor(){return backgroundColor;}
    public LegendOptions backgroundOpacity(Double backgroundOpacity){this.backgroundOpacity=backgroundOpacity; return this;}
    public Double backgroundOpacity(){return backgroundOpacity;}
    public LegendOptions sorted(Sort sorted){this.sorted=sorted; return this;}
    public Sort sorted(){return sorted;}

    
    
            
}
