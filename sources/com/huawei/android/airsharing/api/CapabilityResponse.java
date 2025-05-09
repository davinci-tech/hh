package com.huawei.android.airsharing.api;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes2.dex */
public class CapabilityResponse implements Parcelable {
    public static final int BASE = 100;
    public static final Parcelable.Creator<CapabilityResponse> CREATOR = new Parcelable.Creator<CapabilityResponse>() { // from class: com.huawei.android.airsharing.api.CapabilityResponse.2
        @Override // android.os.Parcelable.Creator
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public CapabilityResponse[] newArray(int i) {
            return new CapabilityResponse[i];
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: dr_, reason: merged with bridge method [inline-methods] */
        public CapabilityResponse createFromParcel(Parcel parcel) {
            return new CapabilityResponse(parcel.readInt(), parcel.readInt());
        }
    };
    public static final int ERROR_CODE_INVALID_CAPABILITY = 101;
    public static final int ERROR_CODE_NOT_SUPPORT_SOFTBUS = 104;
    public static final int ERROR_CODE_VIRTUAL_DISPLAY_BUSY = 102;
    public static final int ERROR_CODE_WRONG_DISPLAY_NAME = 103;
    public static final int ERROR_UNKNOWN = 100;
    public static final int SUCCESS = 1;
    private int mDisplayId;
    private int mResultCode;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public CapabilityResponse(int i, int i2) {
        this.mResultCode = i;
        this.mDisplayId = i2;
    }

    public CapabilityResponse(int i) {
        this(i, -1);
    }

    public int getResultCode() {
        return this.mResultCode;
    }

    public int getDisplayId() {
        return this.mDisplayId;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        if (parcel == null) {
            return;
        }
        parcel.writeInt(this.mResultCode);
        parcel.writeInt(this.mDisplayId);
    }

    public String toString() {
        return "CapabilityResponse:[" + this.mResultCode + ", " + this.mDisplayId + "]";
    }
}
