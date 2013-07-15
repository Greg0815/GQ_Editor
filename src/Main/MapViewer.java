/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import Mission.Components.Hotspot;
import java.net.URL;
import java.util.ArrayList;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Worker;
import javafx.scene.layout.Region;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import netscape.javascript.JSObject;

/**
 *
 * @author Gregor
 */
public class MapViewer extends Region
{
    WebView webView = new WebView();
    WebEngine webEngine = webView.getEngine();
    JavaApp javaApp;
    ArrayList<Hotspot> hotspots;

    public MapViewer(final String[] missionIds)
    {
        final URL url = getClass().getResource("Map.html");
        webEngine.load(url.toExternalForm());
        getChildren().add(webView);
        webEngine.getLoadWorker().stateProperty().addListener(
                new ChangeListener<Worker.State>()
                {
                    @Override
                    public void changed(ObservableValue<? extends Worker.State> ov,
                                        Worker.State oldState, Worker.State newState)
                    {
                        if (newState == Worker.State.SUCCEEDED)
                        {
                            JSObject win = (JSObject) webEngine.executeScript("window");
                            win.setMember("app", new JavaApp(win, webView, missionIds));
                        }
                    }
                });
    }

    public MapViewer( final ArrayList<Hotspot> hotspots)
    {
        final URL url = getClass().getResource("Map.html");
        webEngine.load(url.toExternalForm());
        getChildren().add(webView);
        webEngine.getLoadWorker().stateProperty().addListener(
                new ChangeListener<Worker.State>()
                {
                    @Override
                    public void changed(ObservableValue<? extends Worker.State> ov,
                                        Worker.State oldState, Worker.State newState)
                    {
                        if (newState == Worker.State.SUCCEEDED)
                        {
                            JSObject win = (JSObject) webEngine.executeScript("window");
                            win.setMember("app", new JavaApp(win, webView, hotspots));
                        }
                    }
                });
    }
    
    public MapViewer(String resourceString, final String[] missionIds)
    {
        final URL url = getClass().getResource(resourceString);

        webEngine.load(url.toExternalForm());
        getChildren().add(webView);
        webEngine.getLoadWorker().stateProperty().addListener(
                new ChangeListener<Worker.State>()
                {
                    @Override
                    public void changed(ObservableValue<? extends Worker.State> ov,
                                        Worker.State oldState, Worker.State newState)
                    {
                        if (newState == Worker.State.SUCCEEDED)
                        {
                            JSObject win = (JSObject) webEngine.executeScript("window");
                            win.setMember("app", new JavaApp(win, webView, missionIds));
                        }
                    }
                });
    }

    public MapViewer(String resourceString, final ArrayList<Hotspot> hotspots)
    {
        final URL url = getClass().getResource("Map.html");
        webEngine.load(url.toExternalForm());
        getChildren().add(webView);
        webEngine.getLoadWorker().stateProperty().addListener(
                new ChangeListener<Worker.State>()
                {
                    @Override
                    public void changed(ObservableValue<? extends Worker.State> ov,
                                        Worker.State oldState, Worker.State newState)
                    {
                        if (newState == Worker.State.SUCCEEDED)
                        {
                            JSObject win = (JSObject) webEngine.executeScript("window");
                            win.setMember("app", new JavaApp(win, webView, hotspots));
                        }
                    }
                });
    }
}
