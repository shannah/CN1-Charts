/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.weblite.codename1.components.charts;


import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author shannah
 */
public class Series {
    private Color color;
    private List<Point> data = new ArrayList<Point>();
    private String label;
    private Line lines;
    private Bar bars;
    private ChartPoint points;
    private Integer xAxis;
    private Integer yAxis;
    private Boolean clickable;
    private Boolean hoverable;
    private Double shadowSize;
    private Color highlightColor;
    private PieOptions pie;
    
    public Series(double[] points){
        add(points);
    }
    
    public Series(){
        
    }
 
    public Series add(double[] points){
        for ( int i=0; i<points.length; i+=2){
            data.add(new Point(points[i], points[i+1]));
        }
        return this;
    }
    public Series color(Color color){this.color=color; return this;}
    public Color color(){return color;}
    public Series data(List<Point> data){this.data=data; return this;}
    public List<Point> data(){return data;}
    public Series label(String label){this.label=label; return this;}
    public String label(){return label;}
    public Series lines(Line lines){this.lines=lines; return this;}
    public Line lines(){return lines;}
    public Series bars(Bar bars){this.bars=bars; return this;}
    public Bar bars(){return bars;}
    public Series points(ChartPoint points){this.points=points; return this;}
    public ChartPoint points(){return points;}
    public Series xAxis(Integer xAxis){this.xAxis=xAxis; return this;}
    public Integer xAxis(){return xAxis;}
    public Series yAxis(Integer yAxis){this.yAxis=yAxis; return this;}
    public Integer yAxis(){return yAxis;}
    public Series clickable(Boolean clickable){this.clickable=clickable; return this;}
    public Boolean clickable(){return clickable;}
    public Series hoverable(Boolean hoverable){this.hoverable=hoverable; return this;}
    public Boolean hoverable(){return hoverable;}
    public Series shadowSize(Double shadowSize){this.shadowSize=shadowSize; return this;}
    public Double shadowSize(){return shadowSize;}
    public Series highlightColor(Color highlightColor){this.highlightColor=highlightColor; return this;}
    public Color highlightColor(){return highlightColor;}
    public Series pie(PieOptions pie){this.pie=pie; return this;}
    public PieOptions pie(){return pie;}

    
}
