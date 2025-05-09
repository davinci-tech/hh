package com.huawei.hiai.awareness.client;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import java.util.Locale;

/* loaded from: classes4.dex */
public class ProfileLabel implements Parcelable {
    public static final Parcelable.Creator<ProfileLabel> CREATOR = new Parcelable.Creator<ProfileLabel>() { // from class: com.huawei.hiai.awareness.client.ProfileLabel.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ProfileLabel createFromParcel(Parcel parcel) {
            return new ProfileLabel(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ProfileLabel[] newArray(int i) {
            return new ProfileLabel[i];
        }
    };
    public static final String MESSAGE_TYPE = "context_awareness_profile_label";
    private Bundle args;
    private int type;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    private ProfileLabel(Parcel parcel) {
        this.type = parcel.readInt();
        this.args = parcel.readBundle();
    }

    private ProfileLabel(int i) {
        this.type = i;
    }

    public static ProfileLabel create(int i) {
        return new ProfileLabel(i);
    }

    public int getType() {
        return this.type;
    }

    public Bundle getArgs() {
        return this.args != null ? new Bundle(this.args) : new Bundle();
    }

    public ProfileLabel putArg(String str, int i) {
        if (this.args == null) {
            this.args = new Bundle();
        }
        this.args.putInt(str, i);
        return this;
    }

    public ProfileLabel putArg(String str, long j) {
        if (this.args == null) {
            this.args = new Bundle();
        }
        this.args.putLong(str, j);
        return this;
    }

    public ProfileLabel putArg(String str, boolean z) {
        if (this.args == null) {
            this.args = new Bundle();
        }
        this.args.putBoolean(str, z);
        return this;
    }

    public ProfileLabel putArg(String str, String str2) {
        if (this.args == null) {
            this.args = new Bundle();
        }
        this.args.putString(str, str2);
        return this;
    }

    public ProfileLabel putArg(String str, float f) {
        if (this.args == null) {
            this.args = new Bundle();
        }
        this.args.putFloat(str, f);
        return this;
    }

    public ProfileLabel putArg(String str, double d) {
        if (this.args == null) {
            this.args = new Bundle();
        }
        this.args.putDouble(str, d);
        return this;
    }

    public ProfileLabel putArg(String str, String[] strArr) {
        if (this.args == null) {
            this.args = new Bundle();
        }
        this.args.putStringArray(str, strArr);
        return this;
    }

    public ProfileLabel putArg(String str, byte[] bArr) {
        if (this.args == null) {
            this.args = new Bundle();
        }
        this.args.putByteArray(str, bArr);
        return this;
    }

    public ProfileLabel putArg(String str, byte b) {
        if (this.args == null) {
            this.args = new Bundle();
        }
        this.args.putByte(str, b);
        return this;
    }

    public ProfileLabel putArg(String str, Bundle bundle) {
        if (this.args == null) {
            this.args = new Bundle();
        }
        this.args.putBundle(str, bundle);
        return this;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.type);
        parcel.writeBundle(this.args);
    }

    public String toString() {
        return String.format(Locale.ENGLISH, "ProfileLabel(%d)", Integer.valueOf(this.type));
    }
}
