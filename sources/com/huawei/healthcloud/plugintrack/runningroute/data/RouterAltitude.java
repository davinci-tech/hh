package com.huawei.healthcloud.plugintrack.runningroute.data;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/* loaded from: classes4.dex */
public class RouterAltitude implements Parcelable {
    public static final Parcelable.Creator<RouterAltitude> CREATOR = new Parcelable.Creator<RouterAltitude>() { // from class: com.huawei.healthcloud.plugintrack.runningroute.data.RouterAltitude.5
        @Override // android.os.Parcelable.Creator
        /* renamed from: aWU_, reason: merged with bridge method [inline-methods] */
        public RouterAltitude createFromParcel(Parcel parcel) {
            return new RouterAltitude(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: c, reason: merged with bridge method [inline-methods] */
        public RouterAltitude[] newArray(int i) {
            return new RouterAltitude[i];
        }
    };

    @SerializedName("altitude")
    @Expose
    private float mAltitude;

    @SerializedName("distance")
    @Expose
    private float mDistance;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public RouterAltitude(float f, float f2) {
        this.mDistance = f;
        this.mAltitude = f2;
    }

    protected RouterAltitude(Parcel parcel) {
        this.mDistance = parcel.readFloat();
        this.mAltitude = parcel.readFloat();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeFloat(this.mDistance);
        parcel.writeFloat(this.mAltitude);
    }

    public void setDistance(float f) {
        this.mDistance = f;
    }

    public float getAltitude() {
        return this.mAltitude;
    }

    public void setAltitude(float f) {
        this.mAltitude = f;
    }

    public float getDistance() {
        return this.mDistance;
    }
}
