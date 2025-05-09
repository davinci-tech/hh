package com.huawei.watchface.videoedit.param;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.huawei.watchface.videoedit.gles.Constant;

/* loaded from: classes9.dex */
public class CanvasInfo {
    public static final String BLACK = "black";
    public static final String BLUR = "blur";

    @SerializedName("album_image")
    @Expose
    private String albumPath;

    @SerializedName("blur")
    @Expose
    private float blur;

    @SerializedName("color")
    @Expose
    private long color;

    @SerializedName("id")
    @Expose
    private String id;

    @SerializedName("image_id")
    @Expose
    private String imageId;

    @SerializedName("image_name")
    @Expose
    private String imageName;

    @SerializedName(Constant.TYPE_PHOTO)
    @Expose
    private String path;

    @SerializedName("type")
    @Expose
    private String type = BLACK;

    public String getType() {
        return this.type;
    }

    public void setType(String str) {
        this.type = str;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String str) {
        this.id = str;
    }

    public long getColor() {
        return this.color;
    }

    public void setColor(long j) {
        this.color = j;
    }

    public float getBlur() {
        return this.blur;
    }

    public void setBlur(float f) {
        this.blur = f;
    }

    public String getPath() {
        return this.path;
    }

    public void setPath(String str) {
        this.path = str;
    }

    public String getAlbumPath() {
        return this.albumPath;
    }

    public void setAlbumPath(String str) {
        this.albumPath = str;
    }

    public String getImageId() {
        return this.imageId;
    }

    public void setImageId(String str) {
        this.imageId = str;
    }

    public String getImageName() {
        return this.imageName;
    }

    public void setImageName(String str) {
        this.imageName = str;
    }
}
