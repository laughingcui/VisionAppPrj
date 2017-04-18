package com.android.cuisy.visionappprj.entity;

/**
 * Created by cuisy on 2017/4/10.
 */

public class Camera {
    String name = "";
    String des = "";
    String channel = "";
    String domain = "";
    String type = "";
    String depart = "";
    String status = "";
    String lat = "";
    String longitude = "";
    String netstatus = "";
    String parent = "";
    String preset = "";

    private String cameraName;
    private int cameraImageId;

    public void setName(String name) {
        this.name = name;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setDepart(String depart) {
        this.depart = depart;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public void setNetstatus(String netstatus) {
        this.netstatus = netstatus;
    }

    public void setPreset(String preset) {
        this.preset = preset;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }

    public String getName() {
        return name;
    }

    public String getDes() {
        return des;
    }

    public String getChannel() {
        return channel;
    }

    public String getDomain() {
        return domain;
    }

    public String getType() {
        return type;
    }

    public String getDepart() {
        return depart;
    }

    public String getStatus() {
        return status;
    }

    public String getLat() {
        return lat;
    }

    public String getLongitude() {
        return longitude;
    }

    public String getNetstatus() {
        return netstatus;
    }

    public String getParent() {
        return parent;
    }

    public String getPreset() {
        return preset;
    }

    public Camera(String name, int imageId) {
        this.cameraName = name;
        this.cameraImageId = imageId;
    }

    public Camera(){}
    public String getCameraName() {
        return cameraName;
    }
    public int getCameraImageId() {
        return cameraImageId;
    }
}

