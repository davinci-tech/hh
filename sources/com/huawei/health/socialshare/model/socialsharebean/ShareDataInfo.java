package com.huawei.health.socialshare.model.socialsharebean;

import com.google.gson.annotations.SerializedName;
import com.huawei.operation.ble.BleConstants;
import com.huawei.pluginachievement.manager.model.ParsedFieldTag;
import java.io.Serializable;

/* loaded from: classes4.dex */
public abstract class ShareDataInfo implements Serializable {
    private static final long serialVersionUID = -8644212039141955298L;

    @SerializedName("attribute")
    private int mAttribute;

    @SerializedName("country")
    private int mCountry;

    @SerializedName("deadTime")
    private long mDeadTime;

    @SerializedName("effectiveTime")
    private long mEffectiveTime;

    @SerializedName("id")
    private int mId;

    @SerializedName("imageSize")
    private int mImageSize;

    @SerializedName(ParsedFieldTag.TASK_MODIFY_TIME)
    private long mModifyTime;

    @SerializedName(BleConstants.KEY_PATH)
    private String mPath;

    @SerializedName("sportTypes")
    private String mSportTypes;

    @SerializedName("type")
    private int mType;

    @SerializedName("imageUrl")
    private String mUrl;

    @SerializedName("validityType")
    private int mValidityType;

    @SerializedName("weight")
    private int mWeight;

    public int getId() {
        return this.mId;
    }

    public void setId(int i) {
        this.mId = i;
    }

    public int getAttribute() {
        return this.mAttribute;
    }

    public void setAttribute(int i) {
        this.mAttribute = i;
    }

    public int getType() {
        return this.mType;
    }

    public void setType(int i) {
        this.mType = i;
    }

    public String getUrl() {
        return this.mUrl;
    }

    public void setUrl(String str) {
        this.mUrl = str;
    }

    public int getWeight() {
        return this.mWeight;
    }

    public void setWeight(int i) {
        this.mWeight = i;
    }

    public String getPath() {
        return this.mPath;
    }

    public void setPath(String str) {
        this.mPath = str;
    }

    public int getValidityType() {
        return this.mValidityType;
    }

    public void setValidityType(int i) {
        this.mValidityType = i;
    }

    public long getEffectiveTime() {
        return this.mEffectiveTime;
    }

    public void setEffectiveTime(long j) {
        this.mEffectiveTime = j;
    }

    public long getDeadTime() {
        return this.mDeadTime;
    }

    public void setDeadTime(long j) {
        this.mDeadTime = j;
    }

    public long getModifyTime() {
        return this.mModifyTime;
    }

    public void setModifyTime(long j) {
        this.mModifyTime = j;
    }

    public int getCountry() {
        return this.mCountry;
    }

    public void setCountry(int i) {
        this.mCountry = i;
    }

    public int getImageSize() {
        return this.mImageSize;
    }

    public void setImageSize(int i) {
        this.mImageSize = i;
    }

    public String getSportTypes() {
        return this.mSportTypes;
    }

    public void setSportTypes(String str) {
        this.mSportTypes = str;
    }

    public boolean equals(Object obj) {
        return (obj instanceof ShareDataInfo) && this.mId == ((ShareDataInfo) obj).mId;
    }

    public int hashCode() {
        return (this.mId * 10) + this.mType;
    }
}
