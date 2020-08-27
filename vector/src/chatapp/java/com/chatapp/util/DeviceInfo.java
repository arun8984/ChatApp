package com.chatapp.util;

import com.google.android.gms.maps.model.LatLng;

public class DeviceInfo {

    public String IMIE;
    public String Manufacture;
    public String Model;
    public String TrackCode;
    public String LastTriedPhone;
    public String LastModified;
    public LatLng latLng;

    public DeviceInfo(){}

    public DeviceInfo(String IMIE, String Manufacture, String Model, String TrackCode, String LastTriedPhone, String LastModified) {
        this.IMIE = IMIE;
        this.Manufacture = Manufacture;
        this.Model = Model;
        this.TrackCode = TrackCode;
        this.LastTriedPhone = LastTriedPhone;
        this.LastModified = LastModified;
    }
}
