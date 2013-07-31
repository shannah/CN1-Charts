#Codename One Charts

A library for creating visual charts in Codename One.

##Synopsis

This library allows you to create visual charts (e.g. bar charts, pie charts, time series charts, line charts, etc..) and embed them
in your [Codename One](http://www.codenameone.com/) application.  It was built by wrapping the powerful [Flot](http://www.flotcharts.org/) Javascript library
and exposing it's configuration options and methods as a Java API.

##License

[LGPL2](http://www.gnu.org/licenses/lgpl-2.1.html)

##Requirements

1. [The Codename One Javascript Bridge](https://github.com/shannah/CodenameOneJS)
2. [Codename One](http://www.codenameone.com/) 1.0 or higher

##Platform Compatibility

1. Android.  Tested, Works.
2. Java SE (i.e. in the simulator).  Tested, Works
3. iOS Tested, Works (using [Offline build](http://sjhannah.com/blog/?p=228) only because it requires support for namespaced resources which the build
server currently doesn't support.  See [issue 809](https://code.google.com/p/codenameone/issues/detail?id=809)).
4. Not tested on Windows Phone but likely won't work due to same [issue 809](https://code.google.com/p/codenameone/issues/detail?id=809).

##Usage

The general usage of this chart involves:

1. Create a Chart object which is the model of the chart.
2. Create a ChartView object with a Chart as its model, and initialize it.
3. Add the ChartView to the component hierarchy.

E.g. Creating a Pie Chart

    Form hi = new Form("Pie Chart");
    hi.setLayout(new BorderLayout());
    ChartBuilder b = new ChartBuilder();
    Chart chart = b.newPieChart(
            new double[]{10, 22, 45},
            new String[]{"BC", "Alberta", "Saskatchewan"}
    );
    ChartView v = new ChartView(chart);
    v.initLater();
    hi.addComponent(BorderLayout.CENTER, v);
    hi.show();

E.g. Creating a Bar Chart

    Form hi = new Form("Bar Chart");
    hi.setLayout(new BorderLayout());
    ChartBuilder b = new ChartBuilder();
    Chart chart = b.newBarChart(
            new double[][]{
                {1, 3, 2, 5},
                {3, 1, 2, 4},
                {7, 4, 1, 6},
                {2, 3, 4, 1}
            }, 
            new String[]{"BC", "Alberta", "Ontario", "Saskatchewan"},
            new String[]{"June", "July", "August", "Sept"}
    );
    ChartView v = new ChartView(chart);
    v.initLater();
    hi.addComponent(BorderLayout.CENTER, v);
    hi.show();

The ChartBuilder class is just a convenience class to build some common types of charts but
you can configure your chart using the specific configuration options themselves also:

E.g. Adding multiple Series to a chart, and configuring it to be a pie chart 
with specific "ticks".

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

Most [configuration options for Flot](https://github.com/flot/flot/blob/master/API.md) have been wrapped in a Java API so you should be able
to look at examples of the Flot javascript library and translate them directly to the Java API.

##Documentation

*Javadocs
*[Flot Documentation](https://github.com/flot/flot/blob/master/API.md)

##Contact

Steve Hannah [@shannah78](http://www.twitter.com/shannah78)