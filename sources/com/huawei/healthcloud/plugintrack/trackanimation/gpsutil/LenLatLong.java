package com.huawei.healthcloud.plugintrack.trackanimation.gpsutil;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes4.dex */
public class LenLatLong extends LatLong {
    public static final Parcelable.Creator<LenLatLong> CREATOR = new Parcelable.Creator<LenLatLong>() { // from class: com.huawei.healthcloud.plugintrack.trackanimation.gpsutil.LenLatLong.3
        @Override // android.os.Parcelable.Creator
        /* renamed from: c, reason: merged with bridge method [inline-methods] */
        public LenLatLong[] newArray(int i) {
            return new LenLatLong[i];
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: aYj_, reason: merged with bridge method [inline-methods] */
        public LenLatLong createFromParcel(Parcel parcel) {
            return new LenLatLong(parcel);
        }
    };
    public static final int STATE_STOP = 0;
    public static final int STATE_STRAIGHT = 1;
    public static final int STATE_TURN = -2;
    private static final String TAG = "Track_LenLatLong";
    private static final long serialVersionUID = -5358408678364755794L;
    private int mState;

    @Override // com.huawei.healthcloud.plugintrack.trackanimation.gpsutil.LatLong, android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public LenLatLong() {
        this.mState = 1;
    }

    public LenLatLong(double d, double d2) {
        super(d, d2);
        this.mState = 1;
    }

    public LenLatLong(double d, double d2, int i) {
        super(d, d2);
        this.mState = 1;
        setIndex(i);
    }

    public LenLatLong(LatLong latLong) {
        super(latLong.getLatLng());
        this.mState = 1;
        setIndex(latLong.getIndex());
    }

    public LenLatLong(Parcel parcel) {
        super(parcel);
        this.mState = 1;
        this.mState = parcel.readInt();
    }

    public LenLatLong setState(int i) {
        this.mState = i;
        return this;
    }

    public int getState() {
        return this.mState;
    }

    public boolean isStraightState() {
        return this.mState > 0;
    }

    public boolean isTurnState() {
        return this.mState < 0;
    }

    public boolean isStopState() {
        return this.mState == 0;
    }

    public void forceStraight() {
        this.mState = Math.abs(this.mState);
    }

    public void toOriginalState() {
        if (Math.abs(this.mState) == Math.abs(1)) {
            this.mState = 1;
        } else if (Math.abs(this.mState) == Math.abs(-2)) {
            this.mState = -2;
        } else {
            this.mState = 0;
        }
    }

    @Override // com.huawei.healthcloud.plugintrack.trackanimation.gpsutil.LatLong, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeInt(this.mState);
    }
}
