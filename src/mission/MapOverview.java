/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mission;

import java.util.ArrayList;
import java.util.Arrays;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import mission.components.Hotspot;

/**
 *
 * @author Admin
 */
public class MapOverview extends Mission        // TODO necessary fields & optional fields
{
    private StringProperty mapkind;
    private IntegerProperty zoomlevel;
    private ArrayList<Hotspot> hotspot;

    public MapOverview()
    {
        super("MapOverview");
        mapkind = new SimpleStringProperty("");
        zoomlevel = new SimpleIntegerProperty(0);
        hotspot = new ArrayList<>();
    }

    public String getMapkind()
    {
        return mapkind.get();
    }

    public int getZoomlevel()
    {
        return zoomlevel.get();
    }

    public ArrayList<Hotspot> getHotspot()
    {
        return hotspot;
    }

    public void setMapkind(String mapkind)
    {
        this.mapkind.set(mapkind);
    }

    public void setZoomlevel(int zoomlevel)
    {
        this.zoomlevel.set(zoomlevel);
    }

    public void addHotspot(Hotspot... hotspot)
    {
        this.hotspot.addAll(Arrays.asList(hotspot));
    }

    public StringProperty mapkindProperty()
    {
        return mapkind;
    }

    public IntegerProperty zoomlevelProperty()
    {
        return zoomlevel;
    }

//    public String createSpecificMissionHeader()
//    {
//        String returnString = "";
//        if(!mapkind.get().isEmpty())
//        {
//            returnString += utilitys.mapkind(mapkind.get());
//        }
//        if(0 <= zoomlevel.get() && zoomlevel.get() <= 18)
//        {
//            returnString += utilitys.zoomlevel(zoomlevel.get());
//        }
//        return returnString;
//    }

    @Override
    public String assemble()
    {
//        return createMissionHeader() + utilitys.createStringFromArrayList(hotspot) + buildMissionTail();
        return buildMissionHead() + utilitys.createStringFromArrayList(hotspot) + buildMissionTail();
    }

//    @Override
//    public Boolean isComplete()
//    {
//        Boolean isComplete = true;
//        if (getId().isEmpty())
//        {
//            isComplete = false;
//        }
//        for (Hotspot spot : hotspot)
//        {
//            if (!spot.isComplete())
//            {
//                isComplete = false;
//            }
//        }
//        return isComplete;
//    }

    @Override
    protected void addNecessaryAndOptionalFields()
    {
        addNecessaryNonHeaderFields(getFieldByString("hotspot"));
        addOptionalHeaderFields(getFieldByString("mapkind"), getFieldByString("zoomlevel"));
    }
}
