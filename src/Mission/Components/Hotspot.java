/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Mission.Components;

import Main.AssembleInterface;
import static Main.AssembleInterface.utilitys;
import Main.GameElement;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.FloatProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Admin
 */
public class Hotspot extends GameElement implements AssembleInterface
{
    FloatProperty latitude;
    FloatProperty longitude;
    IntegerProperty radius;
    StringProperty img;
    BooleanProperty initialVisibility;
    
    public Hotspot()
    {
        super();
        this.initialVisibility = new SimpleBooleanProperty();
        this.img = new SimpleStringProperty();
        this.latitude = new SimpleFloatProperty();
        this.longitude = new SimpleFloatProperty();
        this.radius = new SimpleIntegerProperty();
    }
    
    public FloatProperty latitudeProperty()
    {
        return latitude;
    }
    
    public FloatProperty longitudeProperty()
    {
        return longitude;
    }
    
    public IntegerProperty radiusProperty()
    {
        return radius;
    }
    
    public StringProperty imgProperty()
    {
        return img;
    }

    public BooleanProperty initialVisibilityProperty()
    {
        return initialVisibility;
    }
    
    @Override
    public String assemble()
    {
        return "<hotspot " + utilitys.id(this.getId()) + utilitys.latitude(this.latitude.get()) + utilitys.longitude(this.longitude.get()) + utilitys.radius(this.radius.get()) + utilitys.img(this.img.get()) + utilitys.initialVisibility(this.initialVisibility.get()) + ">" + utilitys.createStringFromArrayList(this.getTriggers()) + "</hotspot>";
    }

    @Override
    public Boolean isComplete()
    {
        Boolean isComplete = true;
        if(latitude == null || longitude == null)       // TODO: bessere lösung
        {
            isComplete = false;
        }
        return isComplete;
    }
    
}
