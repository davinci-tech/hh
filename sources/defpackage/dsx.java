package defpackage;

import com.google.gson.annotations.SerializedName;

/* loaded from: classes3.dex */
public class dsx {

    @SerializedName("duration")
    private int mDuration;

    @SerializedName("rpmMax")
    private int mRpmMax;

    @SerializedName("rpmMin")
    private int mRpmMin;

    @SerializedName("stageName")
    private String mStageName;

    @SerializedName("stageNo")
    private int mStageNo;

    @SerializedName("zoneMax")
    private float mZoneMax;

    @SerializedName("zoneMin")
    private float mZoneMin;

    @SerializedName("zoneType")
    private String mZoneType;

    public int getStageNo() {
        return this.mStageNo;
    }

    public float getZoneMax() {
        return this.mZoneMax;
    }

    public float getZoneMin() {
        return this.mZoneMin;
    }

    public String getZoneType() {
        return this.mZoneType;
    }

    public String getStageName() {
        return this.mStageName;
    }

    public int getDuration() {
        return this.mDuration;
    }

    public int getRpmMin() {
        return this.mRpmMin;
    }

    public int getRpmMax() {
        return this.mRpmMax;
    }
}
