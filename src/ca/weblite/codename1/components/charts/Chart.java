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
public class Chart {
    
    private List<Series> series = new ArrayList<Series>();
    private Options options;

    
    public Chart series(List<Series> series){this.series=series; return this;}
    public Chart addSeries(Series series){this.series.add(series); return this;}
    public Chart removeSeries(Series series){this.series.remove(series); return this;}
    public List<Series> series(){return series;}
    public Chart options(Options options){this.options=options; return this;}
    public Options options(){return options;}

}
