package com.huawei.watchface.videoedit.param;

import androidx.constraintlayout.motion.widget.Key;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.huawei.operation.ble.BleConstants;

/* loaded from: classes9.dex */
public class Videos {

    @SerializedName("category_id")
    @Expose
    private String categoryId;

    @SerializedName("category_name")
    @Expose
    private String categoryName;

    @SerializedName("crop")
    @Expose
    private Crop crop;

    @SerializedName("crop_ratio")
    @Expose
    private String cropRatio;

    @SerializedName("crop_scale")
    @Expose
    private int cropScale;

    @SerializedName("duration")
    @Expose
    private int duration;

    @SerializedName("height")
    @Expose
    private int height;

    @SerializedName("id")
    @Expose
    private String id;

    @SerializedName("intensifies_audio_path")
    @Expose
    private String intensifiesAudioPath;

    @SerializedName("intensifies_path")
    @Expose
    private String intensifiesPath;

    @SerializedName("material_id")
    @Expose
    private String materialId;

    @SerializedName("material_name")
    @Expose
    private String materialName;

    @SerializedName(BleConstants.KEY_PATH)
    @Expose
    private String path;

    @SerializedName("reverse_intensifies_path")
    @Expose
    private String reverseIntensifiesPath;

    @SerializedName("reverse_path")
    @Expose
    private String reversePath;

    @SerializedName(Key.ROTATION)
    @Expose
    private float rotation;

    @SerializedName("type")
    @Expose
    private String type;

    @SerializedName("width")
    @Expose
    private int width;

    public Videos(MaterialInfo materialInfo) {
        this.type = materialInfo.getType();
        this.width = materialInfo.getWidth();
        this.height = materialInfo.getHeight();
        this.rotation = materialInfo.getRotation();
        this.categoryName = materialInfo.getCategoryName();
        this.path = materialInfo.getPath();
        this.reversePath = materialInfo.getMontagePath();
        this.duration = materialInfo.getDuration();
    }

    public void setId(String str) {
        this.id = str;
    }

    public String getId() {
        return this.id;
    }

    public Videos withId(String str) {
        this.id = str;
        return this;
    }

    public void setType(String str) {
        this.type = str;
    }

    public String getType() {
        return this.type;
    }

    public Videos withType(String str) {
        this.type = str;
        return this;
    }

    public void setDuration(int i) {
        this.duration = i;
    }

    public int getDuration() {
        return this.duration;
    }

    public Videos withDuration(int i) {
        this.duration = i;
        return this;
    }

    public void setPath(String str) {
        this.path = str;
    }

    public String getPath() {
        return this.path;
    }

    public Videos withPath(String str) {
        this.path = str;
        return this;
    }

    public void setReversePath(String str) {
        this.reversePath = str;
    }

    public String getReversePath() {
        return this.reversePath;
    }

    public Videos withReversePath(String str) {
        this.reversePath = str;
        return this;
    }

    public void setIntensifiesPath(String str) {
        this.intensifiesPath = str;
    }

    public String getIntensifiesPath() {
        return this.intensifiesPath;
    }

    public Videos withIntensifiesPath(String str) {
        this.intensifiesPath = str;
        return this;
    }

    public void setReverseIntensifiesPath(String str) {
        this.reverseIntensifiesPath = str;
    }

    public String getReverseIntensifiesPath() {
        return this.reverseIntensifiesPath;
    }

    public Videos withReverseIntensifiesPath(String str) {
        this.reverseIntensifiesPath = str;
        return this;
    }

    public void setIntensifiesAudioPath(String str) {
        this.intensifiesAudioPath = str;
    }

    public String getIntensifiesAudioPath() {
        return this.intensifiesAudioPath;
    }

    public Videos withIntensifiesAudioPath(String str) {
        this.intensifiesAudioPath = str;
        return this;
    }

    public void setWidth(int i) {
        this.width = i;
    }

    public int getWidth() {
        return this.width;
    }

    public Videos withWidth(int i) {
        this.width = i;
        return this;
    }

    public void setHeight(int i) {
        this.height = i;
    }

    public int getHeight() {
        return this.height;
    }

    public Videos withHeight(int i) {
        this.height = i;
        return this;
    }

    public void setCategoryName(String str) {
        this.categoryName = str;
    }

    public String getCategoryName() {
        return this.categoryName;
    }

    public Videos withCategoryName(String str) {
        this.categoryName = str;
        return this;
    }

    public void setCategoryId(String str) {
        this.categoryId = str;
    }

    public String getCategoryId() {
        return this.categoryId;
    }

    public Videos withCategoryId(String str) {
        this.categoryId = str;
        return this;
    }

    public void setMaterialName(String str) {
        this.materialName = str;
    }

    public String getMaterialName() {
        return this.materialName;
    }

    public Videos withMaterialName(String str) {
        this.materialName = str;
        return this;
    }

    public void setMaterialId(String str) {
        this.materialId = str;
    }

    public String getMaterialId() {
        return this.materialId;
    }

    public Videos withMaterialId(String str) {
        this.materialId = str;
        return this;
    }

    public void setCrop(Crop crop) {
        this.crop = crop;
    }

    public Crop getCrop() {
        return this.crop;
    }

    public Videos withCrop(Crop crop) {
        this.crop = crop;
        return this;
    }

    public void setCropRatio(String str) {
        this.cropRatio = str;
    }

    public String getCropRatio() {
        return this.cropRatio;
    }

    public Videos withCropRatio(String str) {
        this.cropRatio = str;
        return this;
    }

    public void setCropScale(int i) {
        this.cropScale = i;
    }

    public int getCropScale() {
        return this.cropScale;
    }

    public Videos withCropScale(int i) {
        this.cropScale = i;
        return this;
    }

    public void setRotation(float f) {
        this.rotation = f;
    }

    public float getRotation() {
        return this.rotation;
    }

    public Videos withRotation(float f) {
        this.rotation = f;
        return this;
    }
}
