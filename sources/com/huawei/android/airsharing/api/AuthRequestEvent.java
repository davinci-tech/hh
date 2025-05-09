package com.huawei.android.airsharing.api;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes2.dex */
public class AuthRequestEvent extends Event implements Parcelable {
    public static final int AUTH_TYPE_PIN = 2;
    public static final int AUTH_TYPE_PWD = 1;
    public static final Parcelable.Creator<AuthRequestEvent> CREATOR = new Parcelable.Creator<AuthRequestEvent>() { // from class: com.huawei.android.airsharing.api.AuthRequestEvent.1
        @Override // android.os.Parcelable.Creator
        /* renamed from: dp_, reason: merged with bridge method [inline-methods] */
        public AuthRequestEvent createFromParcel(Parcel parcel) {
            return new AuthRequestEvent(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: c, reason: merged with bridge method [inline-methods] */
        public AuthRequestEvent[] newArray(int i) {
            return new AuthRequestEvent[i];
        }
    };
    private int mAuthType;
    private ProjectionDevice mDevice;

    @Override // com.huawei.android.airsharing.api.Event, android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public AuthRequestEvent(int i, ProjectionDevice projectionDevice, int i2) {
        super(i);
        this.mDevice = projectionDevice;
        this.mAuthType = i2;
    }

    protected AuthRequestEvent(Parcel parcel) {
        super(parcel);
        this.mDevice = (ProjectionDevice) parcel.readParcelable(ProjectionDevice.class.getClassLoader());
        this.mAuthType = parcel.readInt();
    }

    protected AuthRequestEvent(int i, Parcel parcel) {
        super(i);
        this.mDevice = (ProjectionDevice) parcel.readParcelable(ProjectionDevice.class.getClassLoader());
        this.mAuthType = parcel.readInt();
    }

    @Override // com.huawei.android.airsharing.api.Event, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        if (parcel == null) {
            return;
        }
        super.writeToParcel(parcel, i);
        parcel.writeParcelable(this.mDevice, i);
        parcel.writeInt(this.mAuthType);
    }

    public ProjectionDevice getDevice() {
        return this.mDevice;
    }

    public void setDevice(ProjectionDevice projectionDevice) {
        this.mDevice = projectionDevice;
    }

    public int getAuthType() {
        return this.mAuthType;
    }
}
