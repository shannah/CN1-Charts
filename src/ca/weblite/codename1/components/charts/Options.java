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
public class Options {
    private Series series;
    private LegendOptions legend;
    private GridOptions grid;
    private List<Axis> xAxes = new ArrayList<Axis>();
    private List<Axis> yAxes = new ArrayList<Axis>();
    
    public Options series(Series series){this.series=series; return this;}
    public Series series(){return series;}
    public Options legend(LegendOptions legend){this.legend=legend; return this;}
    public LegendOptions legend(){return legend;}
    public Options grid(GridOptions grid){this.grid=grid; return this;}
    public GridOptions grid(){return grid;}

    public Options xAxes(List<Axis> xAxes){this.xAxes=xAxes; return this;}
    public List<Axis> xAxes(){return xAxes;}
    public Options yAxes(List<Axis> yAxes){this.yAxes=yAxes; return this;}
    public List<Axis> yAxes(){return yAxes;}
    
}
