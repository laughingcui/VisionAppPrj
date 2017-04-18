package com.android.cuisy.visionappprj.entity;

/**
 * Created by cuisy on 2017/4/10.
 */

public class Depart {
    String name = "";
    String des = "";
    String type = "";
    String parent = "";
    String domain = "";
    String remote = "";
    String status = "";

    private String departName;
    private int imageId;

    public void setName(String name) {
        this.name = name;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public void setRemote(String remote) {
        this.remote = remote;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public String getDes() {
        return des;
    }

    public String getType() {
        return type;
    }

    public String getParent() {
        return parent;
    }

    public String getDomain() {
        return domain;
    }

    public String getRemote() {
        return remote;
    }

    public String getStatus() {
        return status;
    }

    public Depart(String name, int imageId) {
        this.departName = name;
        this.imageId = imageId;
    }

    public Depart(){}
    public String getDepartName() {
        return departName;
    }
    public int getImageId() {
        return imageId;
    }
}
