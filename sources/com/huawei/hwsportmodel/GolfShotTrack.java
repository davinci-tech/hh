package com.huawei.hwsportmodel;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/* loaded from: classes5.dex */
public class GolfShotTrack implements Parcelable {
    public static final Parcelable.Creator<GolfShotTrack> CREATOR = new Parcelable.Creator<GolfShotTrack>() { // from class: com.huawei.hwsportmodel.GolfShotTrack.2
        @Override // android.os.Parcelable.Creator
        /* renamed from: bSm_, reason: merged with bridge method [inline-methods] */
        public GolfShotTrack createFromParcel(Parcel parcel) {
            return new GolfShotTrack(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public GolfShotTrack[] newArray(int i) {
            return new GolfShotTrack[i];
        }
    };

    @SerializedName("dis")
    @Expose
    private int mDistance;

    @SerializedName("lat")
    @Expose
    private double mLatitude;

    @SerializedName("lon")
    @Expose
    private double mLongitude;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    private GolfShotTrack(e eVar) {
        this.mLongitude = eVar.e.doubleValue();
        this.mLatitude = eVar.b.doubleValue();
        this.mDistance = eVar.d;
    }

    public GolfShotTrack(Parcel parcel) {
        this.mLongitude = parcel.readDouble();
        this.mLatitude = parcel.readDouble();
        this.mDistance = parcel.readInt();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeDouble(this.mLongitude);
        parcel.writeDouble(this.mLatitude);
        parcel.writeInt(this.mDistance);
    }

    public static final class e {
        private Double b;
        private int d;
        private Double e;

        public e b(Double d) {
            this.e = d;
            return this;
        }

        public e e(Double d) {
            this.b = d;
            return this;
        }

        public e d(int i) {
            this.d = i;
            return this;
        }

        public GolfShotTrack c() {
            return new GolfShotTrack(this);
        }
    }

    public double getLongitude() {
        return this.mLongitude;
    }

    public double getLatitude() {
        return this.mLatitude;
    }

    public int getDistance() {
        return this.mDistance;
    }

    public String toString() {
        return "GolfShotTrack{mLongitude=" + this.mLongitude + ", mLatitude=" + this.mLatitude + ", mDistance=" + this.mDistance + '}';
    }
}
