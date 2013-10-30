/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.weblite.codename1.components.charts;

import ca.weblite.codename1.js.JSFunction;
import ca.weblite.codename1.js.JSObject;
import ca.weblite.codename1.js.JavascriptContext;
import com.codename1.io.Log;
import com.codename1.ui.BrowserComponent;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;

import com.codename1.ui.layouts.BorderLayout;
import java.util.List;

/**
 *
 * @author shannah
 */
public class ChartView extends Container {
    
    private BrowserComponent browser;
    private JSObject flotChart;
    private Object lock = new Object();
    private boolean initialized = false;
    private boolean initializationError = false;
    private Exception initializationException = null;
    private Chart chartModel;
    private JavascriptContext c;
    
    
    public ChartView(Chart model){
        this.chartModel = model;
        
    }
    
    
    public void setChartModel(Chart model){
        this.chartModel = model;
        
    }
    
    public Chart getChartModel(){
        return this.chartModel;
    }
    
    
    
    private void init(final Runnable afterInit){
        Display.getInstance().setProperty("WebLoadingHidden", "true");
        browser = new BrowserComponent();
       
        this.setLayout(new BorderLayout());
        this.addComponent(BorderLayout.CENTER, browser);
        
        browser.addWebEventListener("onLoad", new ActionListener(){

            public void actionPerformed(ActionEvent evt) {
                try {
                    c = new JavascriptContext(browser);
                    
                    JSObject window = (JSObject)c.get("window");
                    flotChart = (JSObject)window.call("init", new Object[]{createDataObject(chartModel),  createOptionsObject(chartModel.options())});
                    initialized = true;
                    
                } catch ( Exception ex){
                    initializationError = true;
                    initializationException = ex;
                    
                    
                }
                
                afterInit.run();
            }
            
        });
        //browser.setURL("jar:///ca/weblite/codename1/components/flot/resources/flot.html");
        browser.setPage(new Resources().getFlotHtml(), null);
    }
    
    public void initLater(Runnable afterInit){
        init(afterInit);
    }
    
    public void initLater(){
        init(new Runnable(){public void run(){}});
    }
    
    public void initAndWait(){
        init(new Runnable(){

            public void run() {
                synchronized (lock){
                    lock.notifyAll();
                }
            }
            
        });
        
        Display.getInstance().invokeAndBlock(new Runnable(){

            public void run() {
                while ( !initialized && !initializationError ){
                    
                    synchronized(lock){
                        try {
                            lock.wait();
                        } catch (InterruptedException ex) {
                            initializationError = true;
                            initializationException = ex;
                        }
                    }
                }
            }
            
        });
    }
    
    
    public Exception getInitializationException(){
        return initializationException;
    }
    
    
    public void update(){
        JSObject window = (JSObject)c.get("window");
        flotChart = (JSObject)window.call("init", new Object[]{createDataObject(chartModel),  createOptionsObject(chartModel.options())});
        //JSObject data = this.createDataObject(chartModel);
        //flotChart.call("setData", new Object[]{data});
        //flotChart.call("setupGrid");
        //flotChart.call("draw");
        
        
    }
    
    
    private JSObject createDataObject(Chart chartModel){
        JSObject data = (JSObject)c.get("[]");
        for ( Series s : chartModel.series() ){
            //Log.p("Creating series object for seriese "+s);
            data.call("push", new Object[]{ createSeriesObject(s)});
            //Log.p("Finished creating series object for series "+s);
        }
        
        
        return data;
    }
    
    static void set(JSObject obj, String key, Object val){
        if ( val != null ){
            obj.set(key, val);
        }
    }
    
    private JSObject createSeriesObject(Series s){
        if ( s == null ){
            return null;
        }
        JSObject out = (JSObject)c.get("{}");
        if ( s.color() != null ) set(out, "color", s.color().toJS(c));
        set(out, "data", createRawDataForSeries(s.data()) );
        set(out, "label", s.label());
        set(out, "lines", createLinesConfig(s.lines()));
        set(out, "bars", createBarsConfig(s.bars()));
        set(out, "points", createPointsConfig(s.points()));
        set(out, "clickable", s.clickable());
        set(out, "hoverable", s.hoverable());
        set(out, "shadowSize", s.shadowSize());
        set(out, "xaxis", s.xAxis());
        set(out, "yaxis", s.yAxis());
        if ( s.highlightColor() != null ) set(out, "highlightColor", s.highlightColor().toJS(c));
        if ( s.pie() != null ) set(out, "pie", s.pie().toJS(c));
        return out;
    }
    
    private Object createRawDataForSeries(List<Point> points){
        //if ( points.size() > 0 ){
            JSObject out = (JSObject)c.get("[]");
            for ( Point p : points ){
                JSObject pt = (JSObject)c.get("[]");
                pt.call("push", new Object[]{new Double(p.x)});
                pt.call("push", new Object[]{new Double(p.y)});
                out.call("push", new Object[]{pt});
            }
            return out;
        //} else {
        //    System.out.println("Point "+points.get(0).y);
        //    return new Double(points.get(0).y);
        //}
        
        
    }
    
    private JSObject createLinesConfig(Line line){
        if ( line == null ){
            return null;
        }
        JSObject out = (JSObject)c.get("{}");
        set(out, "show", line.show());
        set(out, "lineWidth", line.lineWidth());
        set(out, "fill", line.fill());
        if ( line.fillColor() != null ) set(out, "fillColor", line.fillColor().toJS(c));
        set(out, "zero", line.zero());
        set(out, "steps", line.steps());
        return out;
    }
    
    private JSObject createBarsConfig(Bar bar){
        if ( bar == null ){
            return null;
        }
        JSObject out = (JSObject)c.get("{}");
        set(out, "show", bar.show());
        set(out, "lineWidth", bar.lineWidth());
        set(out, "fill", bar.fill());
        if ( bar.fillColor() != null ) set(out, "fillColor", bar.fillColor().toJS(c));
        set(out, "zero", bar.zero());
        set(out, "barWidth", bar.barWidth());
        if ( bar.align() != null ) out.set("align", bar.align().toString());
        set(out, "horizontal", bar.horizontal());
        return out;
    }
    
    private JSObject createPointsConfig(ChartPoint point){
        if ( point == null) {
            return null;
        }
        JSObject out = (JSObject)c.get("{}");
        set(out, "show", point.show());
        set(out, "lineWidth", point.lineWidth());
        set(out, "fill", point.fill());
        if ( point.fillColor() != null ) set(out, "fillColor", point.fillColor());
        set(out, "radius", point.radius());
        if ( point.symbol() != null ) out.set("symbol", point.symbol().toString());
        return out;
    }
    
    private JSObject createOptionsObject(Options opts){
        if ( opts == null ){
            return null;
        }
        JSObject out = (JSObject)c.get("{}");
        set(out, "series", createSeriesObject(opts.series()));
        set(out, "legend", createLegendObject(opts.legend()));
        set(out, "grid", createGridObject(opts.grid()));
        
        if ( opts.xAxes() != null ){
            if ( opts.xAxes().size() == 1 ){
                set(out, "xaxis", opts.xAxes().get(0).toJS(c));
            } else {
                JSObject xaxes = (JSObject)c.get("[]");
                for ( Axis axis : opts.xAxes() ){
                    xaxes.call("push", new Object[]{axis.toJS(c)});
                }
                set(out, "xaxes", xaxes);
           }
        }
        
        if ( opts.yAxes() != null ){
            if ( opts.yAxes().size() == 1 ){
                set(out, "yaxis", opts.yAxes().get(0).toJS(c));
            } else {
                JSObject yaxes = (JSObject)c.get("[]");
                for ( Axis axis : opts.yAxes() ){
                    yaxes.call("push", new Object[]{axis.toJS(c)});
                }
                set(out, "yaxes", yaxes);
           }
        }

        
        
        return out;
    }
    
    private JSObject createLegendObject(LegendOptions legend){
        if ( legend == null ){
            return null;
        }
        JSObject out = (JSObject)c.get("{}");
        set(out, "show", legend.show());
        if ( legend.labelBoxBorderColor() != null ) set(out, "labelBoxBorderColor", legend.labelBoxBorderColor().toJS(c));
        set(out, "noColumns", legend.noColumns());
        set(out, "position", legend.position().toString());
        set(out, "margin", legend.margin());
        if ( legend.backgroundColor()!= null ) set(out, "backgroundColor", legend.backgroundColor().toJS(c));
        set(out, "backgroundOpacity", legend.backgroundOpacity());
        set(out, "sorted", legend.sorted().toString());
        return out;
    }
    
   
    
   
    
    private JSObject createGridObject(GridOptions grid){
        if ( grid == null ){
            return null;
            
        }
        
        JSObject out = (JSObject)c.get("{}");
        set(out, "show", grid.show());
        set(out, "aboveData", grid.aboveData());
        if ( grid.color() != null ) set(out, "color", grid.color().toJS(c));
        if ( grid.backgroundColor()!=null) set(out, "backgroundColor", grid.backgroundColor().toJS(c));
        set(out, "margin", createBorderWidthsObject(grid.margin()));
        set(out, "labelMargin", grid.labelMargin());
        set(out, "axisMargin", grid.axisMargin());
        set(out, "borderWidth", createBorderWidthsObject(grid.borderWidth()));
        set(out, "borderColor", createBorderColorsObject(grid.borderColor()));
        set(out, "minBorderMargin", grid.minBorderMargin());
        set(out, "clickable", grid.clickable());
        set(out, "hoverable", grid.hoverable());
        set(out, "autoHighlight", grid.autoHighlight());
        return out;
    }
    
    private Object createBorderWidthsObject(BorderWidths widths){
        if ( widths == null ){
            return null;
        }
        Integer l = widths.left();
        Integer r = widths.right();
        Integer t = widths.top();
        Integer b = widths.bottom();
        
        JSObject out = (JSObject)c.get("{}");
        out.set("left", l);
        out.set("right", r);
        out.set("top", t);
        out.set("bottom", b);
        return out;
    }
    
    private Object createBorderColorsObject(BorderColors colors){
        if ( colors == null ){
            return null;
        }
        
        JSObject out = (JSObject)c.get("{}");
        if ( colors.top() != null ) set(out, "top", colors.top().toJS(c));
        if ( colors.left()!= null) set(out, "left", colors.left().toJS(c));
        if ( colors.bottom() != null ) set(out, "bottom", colors.bottom().toJS(c));
        if ( colors.right() != null ) set(out, "right", colors.right().toJS(c));
        return out;
    }
   
    
    
    
    
    
    
}
