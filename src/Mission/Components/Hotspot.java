/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Mission.Components;

import Main.GameComponent;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Admin
 */
public class Hotspot extends GameComponent
{
    private DoubleProperty latitude;
    private DoubleProperty longitude;
    private IntegerProperty radius;
    private StringProperty img;
    private BooleanProperty initialVisibility;

    public Hotspot()
    {
        super();
        initialVisibility = new SimpleBooleanProperty(true);
        img = new SimpleStringProperty("");
        latitude = new SimpleDoubleProperty(0.0);
        longitude = new SimpleDoubleProperty(0.0);
        radius = new SimpleIntegerProperty(15);
    }

    @Override
    protected final void addNecessaryAndOptionalFields()
    {
        addNecessaryHeaderFields(getFieldByString("longitude"), getFieldByString("latitude"), getFieldByString("radius"));
    }

    public Double getLatitude()
    {
        return latitude.get();
    }

    public Double getLongitude()
    {
        return longitude.get();
    }

    public Integer getRadius()
    {
        return radius.get();
    }

    public String getImg()
    {
        return img.get();
    }

    public Boolean getInitialVisibility()
    {
        return initialVisibility.get();
    }

    public void setLatitude(Double latitude)
    {
        this.latitude.set(latitude);
    }

    public void setLongitude(Double longitude)
    {
        this.longitude.set(longitude);
    }

    public void setRadius(Integer radius)
    {
        this.radius.set(radius);
    }

    public void setImg(String img)
    {
        this.img.set(img);
    }

    public void setInitialVisibility(Boolean initialVisibility)
    {
        this.initialVisibility.set(initialVisibility);
    }

    public DoubleProperty latitudeProperty()
    {
        return latitude;
    }

    public DoubleProperty longitudeProperty()
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
        return "<hotspot " + utilitys.id(getId()) + utilitys.latitude(latitude.get()) + utilitys.longitude(longitude.get()) + utilitys.radius(radius.get()) + utilitys.img(img.get()) + utilitys.initialVisibility(initialVisibility.get()) + ">" + utilitys.createStringFromArrayList(getTrigger()) + "</hotspot>";
    }
}
