package com.example.codingcontests;

public class SitesModel {

    private int imageId;
    private String site;

    public SitesModel(int imageId, String site) {
        this.imageId = imageId;
        this.site = site;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

}
