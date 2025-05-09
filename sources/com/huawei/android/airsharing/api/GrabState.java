package com.huawei.android.airsharing.api;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Process;

/* loaded from: classes2.dex */
public class GrabState implements Parcelable {
    public static final Parcelable.Creator<GrabState> CREATOR = new Parcelable.Creator<GrabState>() { // from class: com.huawei.android.airsharing.api.GrabState.2
        @Override // android.os.Parcelable.Creator
        /* renamed from: d, reason: merged with bridge method [inline-methods] */
        public GrabState[] newArray(int i) {
            return new GrabState[i];
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: dG_, reason: merged with bridge method [inline-methods] */
        public GrabState createFromParcel(Parcel parcel) {
            return new GrabState(parcel.readInt(), parcel.readInt());
        }
    };
    public static final int GRAB_ALLOWED = 1;
    public static final int GRAB_NOT_ALLOWED = 0;
    private int mPid;
    private int mState;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public GrabState() {
        this(Process.myPid(), 1);
    }

    public GrabState(int i, int i2) {
        this.mPid = i;
        this.mState = i2;
    }

    public void setPid(int i) {
        this.mPid = i;
    }

    public int getPid() {
        return this.mPid;
    }

    public void setState(int i) {
        this.mState = i;
    }

    public boolean isGrabAllowed(int i) {
        return this.mPid == i || this.mState != 0;
    }

    public int getState() {
        return this.mState;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        if (parcel == null) {
            return;
        }
        parcel.writeInt(this.mPid);
        parcel.writeInt(this.mState);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("[pid: ");
        sb.append(this.mPid);
        sb.append(", grab allowed: ");
        sb.append(this.mState == 1);
        sb.append("]");
        return sb.toString();
    }
}
