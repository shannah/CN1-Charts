/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.weblite.codename1.components.charts;

/**
 *
 * @author shannah
 */
public class GridOptions {
    private Boolean show;
    private boolean aboveData;
    private Color color;
    private Color backgroundColor;
    private BorderWidths margin;
    private Integer labelMargin;
    private Integer axisMargin;
    private BorderWidths borderWidth;
    private BorderColors borderColor;
    private Integer minBorderMargin;
    private Boolean clickable;
    private Boolean hoverable;
    private Boolean autoHighlight;
    
    
    public GridOptions show(Boolean show){this.show=show; return this;}
    public Boolean show(){return show;}
    public GridOptions aboveData(boolean aboveData){this.aboveData=aboveData; return this;}
    public boolean aboveData(){return aboveData;}
    public GridOptions color(Color color){this.color=color; return this;}
    public Color color(){return color;}
    public GridOptions backgroundColor(Color backgroundColor){this.backgroundColor=backgroundColor; return this;}
    public Color backgroundColor(){return backgroundColor;}
    public GridOptions margin(BorderWidths margin){this.margin=margin; return this;}
    public BorderWidths margin(){return margin;}
    public GridOptions labelMargin(Integer labelMargin){this.labelMargin=labelMargin; return this;}
    public Integer labelMargin(){return labelMargin;}
    public GridOptions axisMargin(Integer axisMargin){this.axisMargin=axisMargin; return this;}
    public Integer axisMargin(){return axisMargin;}
    public GridOptions borderWidth(BorderWidths borderWidth){this.borderWidth=borderWidth; return this;}
    public BorderWidths borderWidth(){return borderWidth;}
    public GridOptions borderColor(BorderColors borderColor){this.borderColor=borderColor; return this;}
    public BorderColors borderColor(){return borderColor;}
    public GridOptions minBorderMargin(Integer minBorderMargin){this.minBorderMargin=minBorderMargin; return this;}
    public Integer minBorderMargin(){return minBorderMargin;}
    public GridOptions clickable(Boolean clickable){this.clickable=clickable; return this;}
    public Boolean clickable(){return clickable;}
    public GridOptions hoverable(Boolean hoverable){this.hoverable=hoverable; return this;}
    public Boolean hoverable(){return hoverable;}
    public GridOptions autoHighlight(Boolean autoHighlight){this.autoHighlight=autoHighlight; return this;}
    public Boolean autoHighlight(){return autoHighlight;}
    
}
