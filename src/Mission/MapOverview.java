/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Mission;

import Main.AssembleInterface;
import static Main.AssembleInterface.utilitys;
import Mission.Components.Hotspot;
import java.util.ArrayList;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Admin
 */
public class MapOverview extends Mission implements AssembleInterface
{
    private StringProperty mapkind;
    private IntegerProperty zoomlevel;
    private ArrayList<Hotspot> hotspots;
    
    public MapOverview()
    {
        super("MapOverview");
        this.mapkind = new SimpleStringProperty();
        this.zoomlevel = new SimpleIntegerProperty();
        this.hotspots = new ArrayList<>();
    }
    
    public void addHotspots(Hotspot hotspot)
    {
        hotspots.add(hotspot);
    }
    
    public StringProperty mapkindProperty()
    {
        return mapkind;
    }
    
    public IntegerProperty zoomlevelProperty()
    {
        return zoomlevel;
    }
    
    @Override
    public String createSpecificMissionHeader()
    {
        return utilitys.mapkind(this.mapkind.get()) + utilitys.zoomlevel(this.zoomlevel.get());
    }

    @Override
    public String assemble()
    {
        return createMissionHeader() + utilitys.createStringFromArrayList(hotspots) + createMissionTrailer();
    }
    
}
