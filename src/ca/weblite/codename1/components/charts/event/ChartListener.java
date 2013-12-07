/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ca.weblite.codename1.components.charts.event;

/**
 *
 * @author shannah
 */
public interface ChartListener {
    public void plotClicked(ChartEvent evt);
    public void plotHovered(ChartEvent evt);
}
