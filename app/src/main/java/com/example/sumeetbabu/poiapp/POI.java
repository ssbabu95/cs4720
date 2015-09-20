package com.example.sumeetbabu.poiapp;

import android.location.Location;
import android.media.Image;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by sumeetbabu on 9/20/15.
 */
public class POI {

    private String name;
    private Date createdate;
    private Location location;
    private Image location_pic;
    private String creator;

    public POI() {
        this.createdate = Calendar.getInstance().getTime();

    }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public Date getDate() {
        return createdate;
    }

    public void setDate(Date date) {
        this.createdate = date;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location loc) {
        this.location = loc;
    }

    public Image getPic() {
        return location_pic;
    }

    public void setPic(Image img) {
        this.location_pic = img;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creat) {
        this.creator = creat;
    }

}
