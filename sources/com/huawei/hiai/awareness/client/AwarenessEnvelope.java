package com.huawei.hiai.awareness.client;

import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;
import java.util.Locale;

/* loaded from: classes4.dex */
public class AwarenessEnvelope implements Parcelable {
    public static final Parcelable.Creator<AwarenessEnvelope> CREATOR = new Parcelable.Creator<AwarenessEnvelope>() { // from class: com.huawei.hiai.awareness.client.AwarenessEnvelope.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public AwarenessEnvelope createFromParcel(Parcel parcel) {
            return new AwarenessEnvelope(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public AwarenessEnvelope[] newArray(int i) {
            return new AwarenessEnvelope[i];
        }
    };
    public static final String MESSAGE_TYPE = "context_awareness_envelope";
    private Bundle args;
    private String messageType;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    private AwarenessEnvelope(String str) {
        this.messageType = str;
    }

    private AwarenessEnvelope(Parcel parcel) {
        this.messageType = parcel.readString();
        this.args = parcel.readBundle();
    }

    public static AwarenessEnvelope create(String str) {
        return new AwarenessEnvelope(str);
    }

    public String getMessageType() {
        return this.messageType;
    }

    @Deprecated
    public Bundle getArgs() {
        if (this.args != null) {
            return new Bundle(this.args);
        }
        return null;
    }

    public AwarenessEnvelope putArg(String str, int i) {
        if (this.args == null) {
            this.args = new Bundle();
        }
        this.args.putInt(str, i);
        return this;
    }

    public AwarenessEnvelope putArg(String str, long j) {
        if (this.args == null) {
            this.args = new Bundle();
        }
        this.args.putLong(str, j);
        return this;
    }

    public AwarenessEnvelope putArg(String str, boolean z) {
        if (this.args == null) {
            this.args = new Bundle();
        }
        this.args.putBoolean(str, z);
        return this;
    }

    public AwarenessEnvelope putArg(String str, String str2) {
        if (this.args == null) {
            this.args = new Bundle();
        }
        this.args.putString(str, str2);
        return this;
    }

    public AwarenessEnvelope putArg(String str, double d) {
        if (this.args == null) {
            this.args = new Bundle();
        }
        this.args.putDouble(str, d);
        return this;
    }

    public AwarenessEnvelope putArg(String str, ArrayList<String> arrayList) {
        if (this.args == null) {
            this.args = new Bundle();
        }
        this.args.putStringArrayList(str, arrayList);
        return this;
    }

    public AwarenessEnvelope putArg(String str, byte b) {
        if (this.args == null) {
            this.args = new Bundle();
        }
        this.args.putByte(str, b);
        return this;
    }

    public AwarenessEnvelope putArg(String str, byte[] bArr) {
        if (this.args == null) {
            this.args = new Bundle();
        }
        this.args.putByteArray(str, bArr);
        return this;
    }

    public AwarenessEnvelope putArg(String str, Parcelable parcelable) {
        if (this.args == null) {
            this.args = new Bundle();
        }
        this.args.putParcelable(str, parcelable);
        return this;
    }

    public AwarenessEnvelope putArg(String str, IBinder iBinder) {
        if (this.args == null) {
            this.args = new Bundle();
        }
        this.args.putBinder(str, iBinder);
        return this;
    }

    public int getInt(String str) {
        Bundle bundle = this.args;
        if (bundle != null) {
            return bundle.getInt(str, 0);
        }
        return 0;
    }

    public long getLong(String str) {
        Bundle bundle = this.args;
        if (bundle != null) {
            return bundle.getLong(str, 0L);
        }
        return 0L;
    }

    public boolean getBoolean(String str) {
        Bundle bundle = this.args;
        if (bundle != null) {
            return bundle.getBoolean(str);
        }
        return false;
    }

    public double getDouble(String str) {
        Bundle bundle = this.args;
        if (bundle != null) {
            return bundle.getDouble(str, 0.0d);
        }
        return 0.0d;
    }

    public String getString(String str) {
        Bundle bundle = this.args;
        if (bundle != null) {
            return bundle.getString(str);
        }
        return null;
    }

    public int[] getIntArray(String str) {
        Bundle bundle = this.args;
        if (bundle != null) {
            return bundle.getIntArray(str);
        }
        return null;
    }

    public double[] getDoubleArray(String str) {
        Bundle bundle = this.args;
        if (bundle != null) {
            return bundle.getDoubleArray(str);
        }
        return null;
    }

    public long[] getLongArray(String str) {
        Bundle bundle = this.args;
        if (bundle != null) {
            return bundle.getLongArray(str);
        }
        return null;
    }

    public String[] getStringArray(String str) {
        Bundle bundle = this.args;
        if (bundle != null) {
            return bundle.getStringArray(str);
        }
        return null;
    }

    public Object getObject(String str) {
        Bundle bundle = this.args;
        if (bundle != null) {
            return bundle.get(str);
        }
        return null;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.messageType);
        parcel.writeBundle(this.args);
    }

    public String toString() {
        return String.format(Locale.ENGLISH, "AwarenessEnvelope(%s)", this.messageType);
    }

    @Deprecated
    public void setArgsClassLoader() {
        Bundle bundle = this.args;
        if (bundle != null) {
            bundle.setClassLoader(getClass().getClassLoader());
        }
    }
}
