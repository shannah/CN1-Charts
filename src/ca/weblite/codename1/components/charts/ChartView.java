/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.weblite.codename1.components.charts;


//import ca.weblite.codename1.js.JSObject;
//import ca.weblite.codename1.js.JavascriptContext;
import ca.weblite.codename1.components.charts.event.ChartEvent;
import ca.weblite.codename1.components.charts.event.ChartListener;
import com.codename1.io.Log;
import com.codename1.javascript.JSFunction;
import com.codename1.javascript.JSObject;
import com.codename1.javascript.JavascriptContext;
import com.codename1.ui.BrowserComponent;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;

import com.codename1.ui.layouts.BorderLayout;
import java.util.ArrayList;
import java.util.List;

import org.json.me.JSONArray;
import org.json.me.JSONException;
import org.json.me.JSONObject;

/**
 *
 * @author shannah
 */
public class ChartView extends Container {
    List<ChartListener> chartListeners = new ArrayList<ChartListener>();
    private BrowserComponent browser;
    //private JSObject flotChart;
    private Object lock = new Object();
    private boolean initialized = false;
    private boolean initializationError = false;
    private Exception initializationException = null;
    private Chart chartModel;
    private JavascriptContext c;
    private String jsChartConfig, jsOptionsConfig;
    
    public ChartView(Chart model){
        this.chartModel = model;
        
    }
    
    public ChartView(String jsChartConfig, String jsOptionsConfig){
        this.jsChartConfig = jsChartConfig;
        this.jsOptionsConfig = jsOptionsConfig;
    }
    
    
    public void setChartModel(Chart model){
        this.chartModel = model;
        
    }
    
    public Chart getChartModel(){
        return this.chartModel;
    }
    
    
    public void addChartListener(ChartListener l){
        chartListeners.add(l);
    }
    
    public void removeChartListener(ChartListener l){
        chartListeners.remove(l);
    }
    
    protected void firePlotHovered(ChartEvent evt){
        for ( ChartListener l : chartListeners ){
            l.plotHovered(evt);
        }
    }
    
    protected void firePlotClicked(ChartEvent evt){
        for ( ChartListener l : chartListeners ){
            l.plotClicked(evt);
        }
    }
    
    
    private ChartEvent buildEvent(JSObject pos, JSObject item){
        final ChartEvent evt = new ChartEvent(ChartView.this);
        if ( pos != null ){
            double x = -1; double y = -1;
            try {
                x = pos.getDouble("x");
                y = pos.getDouble("y");
            } catch (NullPointerException npe){}
            Point pt = new Point(x, y);
            List<Point> points = new ArrayList<Point>();
            points.add(pt);
            evt.setAxisPoints(points);
        }
        if ( item != null ){
            
            JSObject series = item.getObject("series");
            if ( series != null ){
                Series s = new Series();
                s.color(new Color(series.getString("color")))
                        .label(series.getString("label"))
                        ;
                evt.setSeries(s);
                evt.setSeriesIndex(item.getInt("seriesIndex"));
            }

            JSObject dataPoints = item.getObject("datapoint");
            if ( dataPoints != null ){
                int len = dataPoints.getInt("length");
                if ( len > 0 ){
                    Object dataPoint = dataPoints.get(0);
                    if ( dataPoint != null ){
                        Point dataPoint0 = new Point(0,0);
                        if ( dataPoint instanceof Double ){
                            dataPoint0 = new Point((Double)dataPoint, 0);
                        } else if ( dataPoint instanceof JSObject ){
                            JSObject jdataPoint = (JSObject)dataPoint;
                            dataPoint0 = new Point(jdataPoint.getDouble("x"), jdataPoint.getDouble("y"));
                        }
                        
                        evt.setDataPoint(dataPoint0);
                        evt.setDataPointIndex(item.getInt("dataIndex"));
                    }

                }
            }
            try {
                evt.setPageX(item.getInt("pageX"));
                evt.setPageY(item.getInt("pageY"));
            } catch ( NullPointerException npe){}


        }
        return evt;
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
                    
                    //
                    //JSObject jsChartModel = (JSObject)c.get(createDataObject(chartModel).toString());
                    //JSObject jsChartModelOptions = (JSObject)c.get(createOptionsObject(chartModel.options()).toString());
                    //flotChart = (JSObject)window.call("init", new Object[]{jsChartModel,  jsChartModelOptions});
                    
                    String chartConfigStr = null;
                    String optionsConfigStr = null;
                    if ( chartModel != null ){
                        chartConfigStr = createDataObject(chartModel).toString();
                        optionsConfigStr = createOptionsObject(chartModel.options()).toString();
                    } else {
                        chartConfigStr = jsChartConfig;
                        optionsConfigStr = jsOptionsConfig;
                    }
                    JSObject window = (JSObject)c.get("window");
                    window.set("plotClicked", new JSFunction(){

                        public void apply(JSObject self, Object[] args) {
                            JSObject pos = (JSObject)args[1];
                            JSObject item  = (JSObject)args[2];
                            final ChartEvent evt = buildEvent(pos, item);
                            Display.getInstance().callSerially(new Runnable(){

                                public void run() {
                                    firePlotClicked(evt);
                                }

                            });
                        }
                        
                    });
                    
                    window.set("plotHovered", new JSFunction(){

                        public void apply(JSObject self, Object[] args) {
                            JSObject pos = (JSObject)args[1];
                            JSObject item  = (JSObject)args[2];
                            final ChartEvent evt = buildEvent(pos, item);
                            
                            Display.getInstance().callSerially(new Runnable(){

                                public void run() {
                                    firePlotHovered(evt);
                                }

                            });
                        }
                        
                    });
                    browser.execute("init("+chartConfigStr+","+optionsConfigStr+");");
                    
                    
                    
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
        browser.execute("init("+createDataObject(chartModel).toString()+","+createOptionsObject(chartModel.options()).toString()+");");
        //JSObject window = (JSObject)c.get("window");
        //JSObject jsChartModel = (JSObject)c.get(createDataObject(chartModel).toString());
        //JSObject jsChartModelOptions = (JSObject)c.get(createOptionsObject(chartModel.options()).toString());
        //flotChart = (JSObject)window.call("init", new Object[]{jsChartModel,  jsChartModelOptions});
        //JSObject data = this.createDataObject(chartModel);
        //flotChart.call("setData", new Object[]{data});
        //flotChart.call("setupGrid");
        //flotChart.call("draw");
        
        
    }
    
    
    private JSONArray createDataObject(Chart chartModel){
        JSONArray data = new JSONArray();
        for ( Series s : chartModel.series() ){
            //Log.p("Creating series object for seriese "+s);
            data.put(createSeriesObject(s));
            //data.call("push", new Object[]{ createSeriesObject(s)});
            //Log.p("Finished creating series object for series "+s);
        }
        
        
        return data;
    }
    
    static void set(JSONObject obj, String key, Object val){
        if ( val != null ){
            try {
                obj.put(key, val);
            } catch (JSONException ex) {
                Log.e(ex);
            }
        }
    }
    
    private JSONObject createSeriesObject(Series s){
        if ( s == null ){
            return null;
        }
        JSONObject out = new JSONObject();
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
    
    private JSONArray createRawDataForSeries(List<Point> points){
        //if ( points.size() > 0 ){
            JSONArray out = new JSONArray();
            for ( Point p : points ){
                try {
                    JSONArray pt = new JSONArray();
                    pt.put(p.x);
                    pt.put(p.y);
                    out.put(pt);
                } catch (JSONException ex) {
                    Log.e(ex);
                }
            }
            return out;
        //} else {
        //    System.out.println("Point "+points.get(0).y);
        //    return new Double(points.get(0).y);
        //}
        
        
    }
    
    private JSONObject createLinesConfig(Line line){
        if ( line == null ){
            return null;
        }
        JSONObject out = new JSONObject();
        set(out, "show", line.show());
        set(out, "lineWidth", line.lineWidth());
        set(out, "fill", line.fill());
        if ( line.fillColor() != null ) set(out, "fillColor", line.fillColor().toJS(c));
        set(out, "zero", line.zero());
        set(out, "steps", line.steps());
        return out;
    }
    
    private JSONObject createBarsConfig(Bar bar){
        try {
            if ( bar == null ){
                return null;
            }
            JSONObject out = new JSONObject();
            set(out, "show", bar.show());
            set(out, "lineWidth", bar.lineWidth());
            set(out, "fill", bar.fill());
            if ( bar.fillColor() != null ) set(out, "fillColor", bar.fillColor().toJS(c));
            set(out, "zero", bar.zero());
            set(out, "barWidth", bar.barWidth());
            if ( bar.align() != null ) out.put("align", bar.align().toString());
            set(out, "horizontal", bar.horizontal());
            return out;
        } catch (JSONException ex) {
            Log.e(ex);
            return null;
        }
        
    }
    
    private JSONObject createPointsConfig(ChartPoint point){
        try {
            if ( point == null) {
                return null;
            }
            JSONObject out = new JSONObject();
            set(out, "show", point.show());
            set(out, "lineWidth", point.lineWidth());
            set(out, "fill", point.fill());
            if ( point.fillColor() != null ) set(out, "fillColor", point.fillColor());
            set(out, "radius", point.radius());
            if ( point.symbol() != null ) out.put("symbol", point.symbol().toString());
            return out;
        } catch (JSONException ex) {
            Log.e(ex);
            return null;
        }
    }
    
    private JSONObject createOptionsObject(Options opts){
        if ( opts == null ){
            return null;
        }
        JSONObject out = new JSONObject();
        set(out, "series", createSeriesObject(opts.series()));
        set(out, "legend", createLegendObject(opts.legend()));
        set(out, "grid", createGridObject(opts.grid()));
        
        if ( opts.xAxes() != null ){
            if ( opts.xAxes().size() == 1 ){
                set(out, "xaxis", opts.xAxes().get(0).toJS(c));
            } else {
                JSONArray xaxes = new JSONArray();
                for ( Axis axis : opts.xAxes() ){
                    xaxes.put(axis.toJS(c));
                }
                set(out, "xaxes", xaxes);
           }
        }
        
        if ( opts.yAxes() != null ){
            if ( opts.yAxes().size() == 1 ){
                set(out, "yaxis", opts.yAxes().get(0).toJS(c));
            } else {
                JSONArray yaxes = new JSONArray();
                for ( Axis axis : opts.yAxes() ){
                    yaxes.put(axis.toJS(c));
                }
                set(out, "yaxes", yaxes);
           }
        }

        
        
        return out;
    }
    
    private JSONObject createLegendObject(LegendOptions legend){
        if ( legend == null ){
            return null;
        }
        JSONObject out = new JSONObject();
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
    
   
    
   
    
    private JSONObject createGridObject(GridOptions grid){
        if ( grid == null ){
            return null;
            
        }
        
        JSONObject out = new JSONObject();
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
    
    private JSONObject createBorderWidthsObject(BorderWidths widths){
        try {
            if ( widths == null ){
                return null;
            }
            Integer l = widths.left();
            Integer r = widths.right();
            Integer t = widths.top();
            Integer b = widths.bottom();
            
            JSONObject out = new JSONObject();
            out.put("left", l);
            out.put("right", r);
            out.put("top", t);
            out.put("bottom", b);
            return out;
        } catch (JSONException ex) {
            Log.e(ex);
            return null;
        }
    }
    
    private JSONObject createBorderColorsObject(BorderColors colors){
        if ( colors == null ){
            return null;
        }
        
        JSONObject out = new JSONObject();
        if ( colors.top() != null ) set(out, "top", colors.top().toJS(c));
        if ( colors.left()!= null) set(out, "left", colors.left().toJS(c));
        if ( colors.bottom() != null ) set(out, "bottom", colors.bottom().toJS(c));
        if ( colors.right() != null ) set(out, "right", colors.right().toJS(c));
        return out;
    }
   
    
    
    
    
    
    
}
