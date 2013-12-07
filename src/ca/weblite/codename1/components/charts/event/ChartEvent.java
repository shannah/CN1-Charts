/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ca.weblite.codename1.components.charts.event;

import ca.weblite.codename1.components.charts.ChartView;
import ca.weblite.codename1.components.charts.Point;
import ca.weblite.codename1.components.charts.Series;
import java.util.ArrayList;

import java.util.List;

/**
 *
 * @author shannah
 */
public class ChartEvent  {

    private ChartView source;
    private Series series;
    private Point dataPoint;
    private int seriesIndex, dataPointIndex, pageX, pageY;
    private List<Point> axisPoints = new ArrayList<Point>();
    
    public ChartEvent(ChartView source) {
        this.source = source;
    }

    /**
     * @return the series
     */
    public Series getSeries() {
        return series;
    }

    /**
     * @param series the series to set
     */
    public void setSeries(Series series) {
        this.series = series;
    }

    /**
     * @return the dataPoint
     */
    public Point getDataPoint() {
        return dataPoint;
    }

    /**
     * @param dataPoint the dataPoint to set
     */
    public void setDataPoint(Point dataPoint) {
        this.dataPoint = dataPoint;
    }

    /**
     * @return the seriesIndex
     */
    public int getSeriesIndex() {
        return seriesIndex;
    }

    /**
     * @param seriesIndex the seriesIndex to set
     */
    public void setSeriesIndex(int seriesIndex) {
        this.seriesIndex = seriesIndex;
    }

    /**
     * @return the dataPointIndex
     */
    public int getDataPointIndex() {
        return dataPointIndex;
    }

    /**
     * @param dataPointIndex the dataPointIndex to set
     */
    public void setDataPointIndex(int dataPointIndex) {
        this.dataPointIndex = dataPointIndex;
    }

    /**
     * @return the pageX
     */
    public int getPageX() {
        return pageX;
    }

    /**
     * @param pageX the pageX to set
     */
    public void setPageX(int pageX) {
        this.pageX = pageX;
    }

    /**
     * @return the pageY
     */
    public int getPageY() {
        return pageY;
    }

    /**
     * @param pageY the pageY to set
     */
    public void setPageY(int pageY) {
        this.pageY = pageY;
    }

    /**
     * @return the axisPoints
     */
    public List<Point> getAxisPoints() {
        return axisPoints;
    }

    /**
     * @param axisPoints the axisPoints to set
     */
    public void setAxisPoints(List<Point> axisPoints) {
        this.axisPoints = axisPoints;
    }

    /**
     * @return the source
     */
    public ChartView getSource() {
        return source;
    }

    /**
     * @param source the source to set
     */
    public void setSource(ChartView source) {
        this.source = source;
    }
    
    
    
}
