package com.huawei.watchface.videoedit.param;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.huawei.watchface.videoedit.gles.Constant;

/* loaded from: classes9.dex */
public class Canvases {

    @SerializedName("album_image")
    @Expose
    private String albumImage;

    @SerializedName("blur")
    @Expose
    private int blur;

    @SerializedName("color")
    @Expose
    private String color;

    @SerializedName("id")
    @Expose
    private String id;

    @SerializedName(Constant.TYPE_PHOTO)
    @Expose
    private String image;

    @SerializedName("image_id")
    @Expose
    private String imageId;

    @SerializedName("image_name")
    @Expose
    private String imageName;

    @SerializedName("platform")
    @Expose
    private String platform;

    @SerializedName("type")
    @Expose
    private String type;

    public void setPlatform(String str) {
        this.platform = str;
    }

    public String getPlatform() {
        return this.platform;
    }

    public Canvases withPlatform(String str) {
        this.platform = str;
        return this;
    }

    public void setId(String str) {
        this.id = str;
    }

    public String getId() {
        return this.id;
    }

    public Canvases withId(String str) {
        this.id = str;
        return this;
    }

    public void setType(String str) {
        this.type = str;
    }

    public String getType() {
        return this.type;
    }

    public Canvases withType(String str) {
        this.type = str;
        return this;
    }

    public void setBlur(int i) {
        this.blur = i;
    }

    public int getBlur() {
        return this.blur;
    }

    public Canvases withBlur(int i) {
        this.blur = i;
        return this;
    }

    public void setImage(String str) {
        this.image = str;
    }

    public String getImage() {
        return this.image;
    }

    public Canvases withImage(String str) {
        this.image = str;
        return this;
    }

    public void setAlbumImage(String str) {
        this.albumImage = str;
    }

    public String getAlbumImage() {
        return this.albumImage;
    }

    public Canvases withAlbumImage(String str) {
        this.albumImage = str;
        return this;
    }

    public void setImageId(String str) {
        this.imageId = str;
    }

    public String getImageId() {
        return this.imageId;
    }

    public Canvases withImageId(String str) {
        this.imageId = str;
        return this;
    }

    public void setImageName(String str) {
        this.imageName = str;
    }

    public String getImageName() {
        return this.imageName;
    }

    public Canvases withImageName(String str) {
        this.imageName = str;
        return this;
    }

    public void setColor(String str) {
        this.color = str;
    }

    public String getColor() {
        return this.color;
    }

    public Canvases withColor(String str) {
        this.color = str;
        return this;
    }
}
