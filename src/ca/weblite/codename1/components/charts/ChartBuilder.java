/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.weblite.codename1.components.charts;

import ca.weblite.codename1.components.charts.Axis.Tick;
import ca.weblite.codename1.components.charts.PieOptions.StrokeOptions;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author shannah
 */
public class ChartBuilder {
    
    public Chart newPieChart(double[] values, String[] labels){
        Chart chart = new Chart();
        for ( int i=0; i<values.length; i++){
            Series s = new Series();
            s.add(new double[]{1, values[i]}).label(labels[i]);
            chart.addSeries(s);
        }
        
        Options opts = new Options();
        Series seriesConfig = new Series();
        seriesConfig.pie(new PieOptions()
                .show(Boolean.TRUE).tilt(new Double(0.5)));
        opts.series(seriesConfig);
        opts.legend(new LegendOptions().show(Boolean.FALSE));
        chart.options(opts);
                
        return chart;
    }
    
    
    /**
     * Creates a new series for use in a bar chart with the specified values.
     * The bars will have the magnitudes specified at the xcoordinates of
     * the value index+offset.
     * @param values
     * @param offset
     * @return A series.
     */
    public Series newBarChartSeries(double[] values, double offset){
        double[] vals = new double[values.length*2];
        for ( int i=0; i<values.length; i++){
            vals[2*i] = i+offset;
            vals[2*i+1] = values[i];
        }
        
        
        Series series = new Series()
                .bars((Bar)new Bar()
                    .barWidth(1.0)
                    .show(Boolean.TRUE)
                )
                .add(vals);
        return series;
    }
    
    /**
     * Creates a new bar chart that shows multiple series' values.
     * @param values 2-dimensional array of values.  One array per series.
     * @param seriesLabels The labels for the series.  This should be the same length
     * as the "values" array.
     * @param labels The labels for the ticks on the x-axis.  This should be the same
     * length as each value array in the values structure.
     * @return The Chart model.
     */
    public Chart newBarChart(double[][] values, String[] seriesLabels, String[] labels){
        Chart chart = new Chart();
        for ( int i=0; i<values.length; i++){
            Series s = newBarChartSeries(values[i], ((double)i)/(values.length+1))
                        .label(seriesLabels[i]);
            s.bars().barWidth(1d/(values.length+1));
            chart.addSeries(s);
        }
        
        
        Options opts = new Options();
        List<Tick> ticksArr = new ArrayList<Tick>();
        for ( int i=0; i<labels.length; i++){
            Tick t = new Tick()
                    .label(labels[i])
                    .value(new Double(i+0.5));
            ticksArr.add(t);
        }
        
        Axis xAxis = new Axis()
                .ticksArr(ticksArr);
        List<Axis> xAxes = new ArrayList<Axis>();
        xAxes.add(xAxis);
        opts.xAxes(xAxes);
        
        chart.options(opts);
        return chart;
    }
    
    /**
     * Creates a new bar chart model to represent the given values and labels.
     * @param values The values of the bars.  The cardinality of this array should
     * match the cardinality of the labels array.
     * @param labels The labels for the chart.
     * @return A basic chart model for this bar chart.
     */
    public Chart newBarChart(double[] values, String[] labels){
        Chart chart = new Chart();
        chart.addSeries(newBarChartSeries(values, 0));
        
        Options opts = new Options();
        List<Tick> ticksArr = new ArrayList<Tick>();
        for ( int i=0; i<labels.length; i++){
            Tick t = new Tick()
                    .label(labels[i])
                    .value(new Double(i+0.5));
            ticksArr.add(t);
        }
        
        Axis xAxis = new Axis()
                .ticksArr(ticksArr);
        List<Axis> xAxes = new ArrayList<Axis>();
        xAxes.add(xAxis);
        opts.xAxes(xAxes);
        
        chart.options(opts);
        return chart;
    }
}
