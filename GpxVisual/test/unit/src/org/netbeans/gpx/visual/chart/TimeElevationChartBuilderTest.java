/*
 * (C) Copyright Dilax Intelcom GmbH.
 * 
 *  All Rights Reserved.
 */
package org.netbeans.gpx.visual.chart;

import com.sun.org.apache.xerces.internal.jaxp.datatype.XMLGregorianCalendarImpl;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.xml.datatype.XMLGregorianCalendar;
import org.jfree.chart.JFreeChart;
import org.junit.Test;
import static org.junit.Assert.*;
import org.netbeans.gpx.model.entity.Waypoint;

/**
 *
 * @author msc
 */
public class TimeElevationChartBuilderTest {

    private TimeElevationChartBuilder classUnderTest;

    private List<Waypoint> points;

    public TimeElevationChartBuilderTest() {
	classUnderTest = new TimeElevationChartBuilder();
	buildPoints();
    }

    private void buildPoints() {
	points = new ArrayList<Waypoint>();
	
	Waypoint point = new Waypoint();
	XMLGregorianCalendar cal = new XMLGregorianCalendarImpl();
	cal.setTime(10, 0, 0);
	point.setTime(cal);
	point.setElevation(BigDecimal.ZERO);
	points.add(point);
	
	point = new Waypoint();
	cal = new XMLGregorianCalendarImpl();
	cal.setTime(10, 0, 2);
	point.setTime(cal);
	point.setElevation(BigDecimal.valueOf(5.0));
	points.add(point);
	
	point = new Waypoint();
	cal = new XMLGregorianCalendarImpl();
	cal.setTime(10, 0, 4);
	point.setTime(cal);
	point.setElevation(BigDecimal.valueOf(10.0));
	points.add(point);
    }

    /**
     * Test of buildChart method, of class TimeElevationChartBuilder.
     */
    @Test
    public void testBuildChart() {

	JFreeChart chart = classUnderTest.buildChart(points);
	assertNotNull(chart);
    }
    
}