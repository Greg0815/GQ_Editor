/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import mission.components.Hotspot;
import java.util.ArrayList;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import netscape.javascript.JSException;
import netscape.javascript.JSObject;

/**
 *
 * @author Admin
 */
public class JavaApp
{
    WebView webView;
    WebEngine webEngine;
    JSObject win;
    ArrayList<Hotspot> returnHotspots = new ArrayList<>();
//        JSObject jsHotspots;

    public JavaApp(JSObject win, WebView webView, String[] missionIds)
    {
        this.webView = webView;
        this.webEngine = webView.getEngine();
//        this.webEngine.executeScript("if (!document.getElementById('FirebugLite')){E = document['createElement' + 'NS'] && document.documentElement.namespaceURI;E = E ? document['createElement' + 'NS'](E, 'script') : document['createElement']('script');E['setAttribute']('id', 'FirebugLite');E['setAttribute']('src', 'https://getfirebug.com/' + 'firebug-lite.js' + '#startOpened');E['setAttribute']('FirebugLite', '4');(document['getElementsByTagName']('head')[0] || document['getElementsByTagName']('body')[0]).appendChild(E);E = new Image;E['setAttribute']('src', 'https://getfirebug.com/' + '#startOpened');}");
        this.win = win;
        JSObject ids = (JSObject)this.win.getMember("ids");
        for(int i=0; i<missionIds.length; i++)
        {
            ids.setSlot(i, missionIds[i]);
        }
    }

    public JavaApp(JSObject win, WebView webView, ArrayList<Hotspot> hotspots)
    {
        System.out.println("hier drinnen");
        this.webView = webView;
        this.webEngine = webView.getEngine();
//        this.webEngine.executeScript("if (!document.getElementById('FirebugLite')){E = document['createElement' + 'NS'] && document.documentElement.namespaceURI;E = E ? document['createElement' + 'NS'](E, 'script') : document['createElement']('script');E['setAttribute']('id', 'FirebugLite');E['setAttribute']('src', 'https://getfirebug.com/' + 'firebug-lite.js' + '#startOpened');E['setAttribute']('FirebugLite', '4');(document['getElementsByTagName']('head')[0] || document['getElementsByTagName']('body')[0]).appendChild(E);E = new Image;E['setAttribute']('src', 'https://getfirebug.com/' + '#startOpened');}");
        this.win = win;
        JSObject jsHotspots = (JSObject)this.win.getMember("hotspots");
        for(int i=0; i<hotspots.size(); i++)
        {
            Double longitude = hotspots.get(i).getLongitude();
            Double latitude = hotspots.get(i).getLatitude();
            Integer radius = hotspots.get(i).getRadius();
            String id = hotspots.get(i).getId();
            Boolean initialVisibility = hotspots.get(i).getInitialVisibility();
            JSObject newHotspot = (JSObject)this.win.eval("new Hotspot()");
            newHotspot.setMember("latitude", latitude);
            newHotspot.setMember("longitude", longitude);
            newHotspot.setMember("id", id);
            newHotspot.setMember("initialVisibility", initialVisibility);
            newHotspot.setMember("radius", radius);
            jsHotspots.setSlot(i, newHotspot);
        }
        this.win.call("makeMapByHotspots");
    }

    public ArrayList<Hotspot> getHotspotsFromMap()
    {
        return returnHotspots;
    }

    public void passHotspotsFromMapToEditor()
    {
        try
        {
            JSObject obj = (JSObject) win.getMember("hotspots");
                for (int i = 0; i < (Integer) win.call("getHotspotsSize"); i++)
                {
                    Hotspot newHS = new Hotspot();
                    newHS.setLatitude((Double)((JSObject)obj.getSlot(i)).getMember("latitude"));
                    newHS.setLongitude((Double)((JSObject)obj.getSlot(i)).getMember("longitude"));
                    newHS.setId((String)((JSObject)obj.getSlot(i)).getMember("id"));
                    newHS.setRadius((Integer)((JSObject)obj.getSlot(i)).getMember("radius"));
                    newHS.setInitialVisibility((Boolean)((JSObject)obj.getSlot(i)).getMember("initialVisibility"));
                    returnHotspots.add(newHS);
                }
        }
        catch (JSException e)
        {
            System.out.println(e);
        }
        System.out.println("ende");
    }
}
