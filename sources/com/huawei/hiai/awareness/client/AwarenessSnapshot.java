package com.huawei.hiai.awareness.client;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import java.util.Locale;

/* loaded from: classes8.dex */
public class AwarenessSnapshot implements Parcelable {
    public static final Parcelable.Creator<AwarenessSnapshot> CREATOR = new Parcelable.Creator<AwarenessSnapshot>() { // from class: com.huawei.hiai.awareness.client.AwarenessSnapshot.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public AwarenessSnapshot createFromParcel(Parcel parcel) {
            return new AwarenessSnapshot(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public AwarenessSnapshot[] newArray(int i) {
            return new AwarenessSnapshot[i];
        }
    };
    public static final String MESSAGE_TYPE = "context_awareness_snapshot";
    private Bundle args;
    private int type;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    private AwarenessSnapshot(Parcel parcel) {
        this.type = parcel.readInt();
        this.args = parcel.readBundle();
    }

    private AwarenessSnapshot(int i) {
        this.type = i;
    }

    public static AwarenessSnapshot create(int i) {
        return new AwarenessSnapshot(i);
    }

    public int getType() {
        return this.type;
    }

    public Bundle getArgs() {
        if (this.args != null) {
            return new Bundle(this.args);
        }
        return null;
    }

    public AwarenessSnapshot putArg(String str, int i) {
        if (this.args == null) {
            this.args = new Bundle();
        }
        this.args.putInt(str, i);
        return this;
    }

    public AwarenessSnapshot putArg(String str, long j) {
        if (this.args == null) {
            this.args = new Bundle();
        }
        this.args.putLong(str, j);
        return this;
    }

    public AwarenessSnapshot putArg(String str, boolean z) {
        if (this.args == null) {
            this.args = new Bundle();
        }
        this.args.putBoolean(str, z);
        return this;
    }

    public AwarenessSnapshot putArg(String str, String str2) {
        if (this.args == null) {
            this.args = new Bundle();
        }
        this.args.putString(str, str2);
        return this;
    }

    public AwarenessSnapshot putArg(String str, float f) {
        if (this.args == null) {
            this.args = new Bundle();
        }
        this.args.putFloat(str, f);
        return this;
    }

    public AwarenessSnapshot putArg(String str, double d) {
        if (this.args == null) {
            this.args = new Bundle();
        }
        this.args.putDouble(str, d);
        return this;
    }

    public AwarenessSnapshot putArg(String str, String[] strArr) {
        if (this.args == null) {
            this.args = new Bundle();
        }
        this.args.putStringArray(str, strArr);
        return this;
    }

    public AwarenessSnapshot putArg(String str, byte[] bArr) {
        if (this.args == null) {
            this.args = new Bundle();
        }
        this.args.putByteArray(str, bArr);
        return this;
    }

    public AwarenessSnapshot putArg(String str, byte b) {
        if (this.args == null) {
            this.args = new Bundle();
        }
        this.args.putByte(str, b);
        return this;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.type);
        parcel.writeBundle(this.args);
    }

    public String toString() {
        return String.format(Locale.ENGLISH, "AwarenessSnapshot(%d)", Integer.valueOf(this.type));
    }
}
