package com.huawei.android.airsharing.api;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes2.dex */
public class ErrorInfoEvent extends Event implements Parcelable {
    public static final int BASE = 100;
    public static final int CODE_CAST_STREAM_NOT_IN_CONDITION = 103;
    public static final int CODE_CAST_STREAM_RESOURCE_VIOLATION = 104;
    public static final int CODE_CAST_STREAM_UNSUPPORTED = 102;
    public static final int CODE_LISTENER_UNREGISTERED = 101;
    public static final int CODE_WIFI_DISABLED = 105;
    public static final Parcelable.Creator<ErrorInfoEvent> CREATOR = new Parcelable.Creator<ErrorInfoEvent>() { // from class: com.huawei.android.airsharing.api.ErrorInfoEvent.4
        @Override // android.os.Parcelable.Creator
        /* renamed from: dE_, reason: merged with bridge method [inline-methods] */
        public ErrorInfoEvent createFromParcel(Parcel parcel) {
            return new ErrorInfoEvent(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: d, reason: merged with bridge method [inline-methods] */
        public ErrorInfoEvent[] newArray(int i) {
            return new ErrorInfoEvent[i];
        }
    };
    public static final String DESC_CAST_STREAM_NOT_IN_CONDITION = "device is in condition which is not allowed to use cast stream.";
    public static final String DESC_CAST_STREAM_RESOURCE_VIOLATION = "access violation of local resource.";
    public static final String DESC_CAST_STREAM_UNSUPPORTED = "peer device doesn't support cast stream.";
    public static final String DESC_LISTENER_UNREGISTERED = "IAidlMediaPlayerListener unregistered.";
    public static final String DESC_WIFI_ENABLED_CANCEL = "user cancelled the wifi enable request";
    private int mCode;
    private String mDescription;

    @Override // com.huawei.android.airsharing.api.Event, android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public ErrorInfoEvent(int i, int i2) {
        super(i);
        this.mDescription = "no description";
        this.mCode = i2;
    }

    public ErrorInfoEvent(int i, int i2, String str) {
        super(i);
        this.mCode = i2;
        this.mDescription = str;
    }

    protected ErrorInfoEvent(Parcel parcel) {
        super(parcel);
        this.mCode = 0;
        this.mDescription = "no description";
        this.mCode = parcel.readInt();
        this.mDescription = parcel.readString();
    }

    protected ErrorInfoEvent(int i, Parcel parcel) {
        super(i);
        this.mCode = 0;
        this.mDescription = "no description";
        this.mCode = parcel.readInt();
        this.mDescription = parcel.readString();
    }

    @Override // com.huawei.android.airsharing.api.Event, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        if (parcel == null) {
            return;
        }
        super.writeToParcel(parcel, i);
        parcel.writeInt(this.mCode);
        parcel.writeString(this.mDescription);
    }

    public int getErrorCode() {
        return this.mCode;
    }

    public String getErrorDescription() {
        return this.mDescription;
    }
}
