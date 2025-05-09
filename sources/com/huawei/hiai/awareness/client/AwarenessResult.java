package com.huawei.hiai.awareness.client;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.Locale;

/* loaded from: classes4.dex */
public class AwarenessResult implements Parcelable {
    public static final Parcelable.Creator<AwarenessResult> CREATOR = new Parcelable.Creator<AwarenessResult>() { // from class: com.huawei.hiai.awareness.client.AwarenessResult.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public AwarenessResult createFromParcel(Parcel parcel) {
            return new AwarenessResult(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public AwarenessResult[] newArray(int i) {
            return new AwarenessResult[i];
        }
    };
    public static final String MESSAGE_TYPE = "context_awareness_result";
    private int code;
    private String extra;

    /* loaded from: classes8.dex */
    public static final class Code {
        public static final int ERROR_INVALID_PARAMETER = 3;
        public static final int ERROR_LOCATION_FENCE_SWITCH_IS_OFF = 5;
        public static final int ERROR_PLACE_SWITCH_IS_OFF = 6;
        public static final int ERROR_SMART_ASSISTANT_LABEL_DISABLE = 8;
        public static final int ERROR_SMART_ASSISTANT_SWITCH_IS_OFF = 7;
        public static final int ERROR_UNAUTHORIZED = 4;
        public static final int EXCEED_THE_UPPER_LIMIT = 1;
        public static final int FAILURE = 0;
        public static final int INCONSISTENT_TYPE = 2;
        public static final int SUCCESS = 10000;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public AwarenessResult(int i) {
        this.code = i;
    }

    public AwarenessResult(int i, String str) {
        this(i);
        this.extra = str;
    }

    private AwarenessResult(Parcel parcel) {
        this.code = parcel.readInt();
        this.extra = parcel.readString();
    }

    public int code() {
        return this.code;
    }

    public String getExtra() {
        return this.extra;
    }

    public boolean isSuccessful() {
        return code() >= 10000;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.code);
        parcel.writeString(this.extra);
    }

    public String toString() {
        return String.format(Locale.ENGLISH, "AwarenessResult{%d} - %s", Integer.valueOf(code()), getExtra());
    }

    @Deprecated
    public String reason() {
        return "";
    }
}
