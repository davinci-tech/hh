package com.huawei.android.airsharing.api;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes2.dex */
public enum ERequestedCapability implements Parcelable {
    RENDERING_ON_VIRTUAL_DISPLAY("RenderingOnVirtualDisplay"),
    TRANSFER_MODE_SOFTBUS("TransferModeSoftbus"),
    RENDERING_ON_VIRTUAL_DISPLAY_SYSTEM("RenderingOnVirtualDisplaySystem"),
    RENDERING_ON_VIRTUAL_DISPLAY_TRANSIENT("RenderingOnVirtualDisplayTransient"),
    RELEASE_RENDERING_ON_VIRTUAL_DISPLAY("ReleaseRenderingOnVirtualDisplay");

    public static final Parcelable.Creator<ERequestedCapability> CREATOR = new Parcelable.Creator<ERequestedCapability>() { // from class: com.huawei.android.airsharing.api.ERequestedCapability.2
        @Override // android.os.Parcelable.Creator
        /* renamed from: dD_, reason: merged with bridge method [inline-methods] */
        public ERequestedCapability createFromParcel(Parcel parcel) {
            return ERequestedCapability.valueOf(parcel.readString());
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: c, reason: merged with bridge method [inline-methods] */
        public ERequestedCapability[] newArray(int i) {
            return new ERequestedCapability[i];
        }
    };
    private String requestedCapability;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    ERequestedCapability(String str) {
        this.requestedCapability = str;
    }

    public String getRequestedCapability() {
        return this.requestedCapability;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        if (parcel != null) {
            parcel.writeString(name());
        }
    }
}
