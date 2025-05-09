package com.huawei.hiai.awareness.client;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;
import java.util.Locale;

/* loaded from: classes8.dex */
public final class AwarenessFence implements Parcelable {
    public static final String CHECK_SWITCH = "check_fence_switch";
    public static final Parcelable.Creator<AwarenessFence> CREATOR = new Parcelable.Creator<AwarenessFence>() { // from class: com.huawei.hiai.awareness.client.AwarenessFence.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public AwarenessFence createFromParcel(Parcel parcel) {
            return new AwarenessFence(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public AwarenessFence[] newArray(int i) {
            return new AwarenessFence[i];
        }
    };
    public static final String MESSAGE_TYPE = "context_awareness_fence";
    private Bundle args;
    private String identifier;
    private FenceState state;
    private String type;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    private AwarenessFence(Parcel parcel) {
        this.type = parcel.readString();
        this.identifier = parcel.readString();
        this.state = (FenceState) parcel.readParcelable(FenceState.class.getClassLoader());
        this.args = parcel.readBundle();
    }

    private AwarenessFence(String str) {
        this.type = str;
    }

    public static AwarenessFence create(String str) {
        return new AwarenessFence(str);
    }

    public String getType() {
        return this.type;
    }

    public String getIdentifier() {
        return this.identifier;
    }

    public AwarenessFence setIdentifier(String str) {
        this.identifier = str;
        return this;
    }

    public FenceState getState() {
        return this.state;
    }

    public void setState(FenceState fenceState) {
        this.state = fenceState;
    }

    public Bundle getArgs() {
        Bundle bundle = this.args;
        if (bundle != null) {
            bundle.setClassLoader(AwarenessFence.class.getClassLoader());
        }
        return this.args;
    }

    public AwarenessFence putArg(String str, int i) {
        if (this.args == null) {
            this.args = new Bundle();
        }
        this.args.putInt(str, i);
        return this;
    }

    public AwarenessFence putArg(String str, int[] iArr) {
        if (this.args == null) {
            this.args = new Bundle();
        }
        this.args.putIntArray(str, iArr);
        return this;
    }

    public AwarenessFence putArg(String str, long j) {
        if (this.args == null) {
            this.args = new Bundle();
        }
        this.args.putLong(str, j);
        return this;
    }

    public AwarenessFence putArg(String str, long[] jArr) {
        if (this.args == null) {
            this.args = new Bundle();
        }
        this.args.putLongArray(str, jArr);
        return this;
    }

    public AwarenessFence putArg(String str, byte b) {
        if (this.args == null) {
            this.args = new Bundle();
        }
        this.args.putByte(str, b);
        return this;
    }

    public AwarenessFence putArg(String str, byte[] bArr) {
        if (this.args == null) {
            this.args = new Bundle();
        }
        this.args.putByteArray(str, bArr);
        return this;
    }

    public AwarenessFence putArg(String str, boolean z) {
        if (this.args == null) {
            this.args = new Bundle();
        }
        this.args.putBoolean(str, z);
        return this;
    }

    public AwarenessFence putArg(String str, boolean[] zArr) {
        if (this.args == null) {
            this.args = new Bundle();
        }
        this.args.putBooleanArray(str, zArr);
        return this;
    }

    public AwarenessFence putArg(String str, float f) {
        if (this.args == null) {
            this.args = new Bundle();
        }
        this.args.putFloat(str, f);
        return this;
    }

    public AwarenessFence putArg(String str, float[] fArr) {
        if (this.args == null) {
            this.args = new Bundle();
        }
        this.args.putFloatArray(str, fArr);
        return this;
    }

    public AwarenessFence putArg(String str, double d) {
        if (this.args == null) {
            this.args = new Bundle();
        }
        this.args.putDouble(str, d);
        return this;
    }

    public AwarenessFence putArg(String str, double[] dArr) {
        if (this.args == null) {
            this.args = new Bundle();
        }
        this.args.putDoubleArray(str, dArr);
        return this;
    }

    public AwarenessFence putArg(String str, String str2) {
        if (this.args == null) {
            this.args = new Bundle();
        }
        this.args.putString(str, str2);
        return this;
    }

    public AwarenessFence putArg(String str, String[] strArr) {
        if (this.args == null) {
            this.args = new Bundle();
        }
        this.args.putStringArray(str, strArr);
        return this;
    }

    public AwarenessFence putArg(String str, ArrayList<String> arrayList) {
        if (this.args == null) {
            this.args = new Bundle();
        }
        this.args.putStringArrayList(str, arrayList);
        return this;
    }

    public AwarenessFence putArg(String str, Parcelable... parcelableArr) {
        if (this.args == null) {
            this.args = new Bundle();
        }
        if (parcelableArr != null) {
            this.args.putParcelableArray(str, parcelableArr);
        }
        return this;
    }

    public AwarenessFence putArg(String str, AwarenessFence... awarenessFenceArr) {
        if (this.args == null) {
            this.args = new Bundle();
        }
        if (awarenessFenceArr != null) {
            this.args.putParcelableArray(str, awarenessFenceArr);
        }
        return this;
    }

    public AwarenessFence[] getAwarenessFenceArray(String str) {
        Bundle bundle = this.args;
        if (bundle != null && bundle.containsKey(str)) {
            Parcelable[] parcelableArray = this.args.getParcelableArray(str);
            if (parcelableArray instanceof AwarenessFence[]) {
                return (AwarenessFence[]) parcelableArray;
            }
        }
        return null;
    }

    public AwarenessEnvelope toEnvelope() {
        return AwarenessEnvelope.create(MESSAGE_TYPE).putArg(MESSAGE_TYPE, this);
    }

    public Intent toIntent() {
        Bundle bundle = new Bundle();
        bundle.putParcelable(MESSAGE_TYPE, this);
        return new Intent().putExtra(AwarenessEnvelope.MESSAGE_TYPE, bundle);
    }

    public static AwarenessFence parseFrom(Intent intent) {
        Bundle bundleExtra;
        if (intent == null || !intent.hasExtra(AwarenessEnvelope.MESSAGE_TYPE) || (bundleExtra = intent.getBundleExtra(AwarenessEnvelope.MESSAGE_TYPE)) == null) {
            return null;
        }
        bundleExtra.setClassLoader(AwarenessFence.class.getClassLoader());
        Parcelable parcelable = bundleExtra.getParcelable(MESSAGE_TYPE);
        if (parcelable instanceof AwarenessFence) {
            return (AwarenessFence) parcelable;
        }
        return null;
    }

    public static AwarenessFence parseFrom(AwarenessEnvelope awarenessEnvelope) {
        if (awarenessEnvelope != null && MESSAGE_TYPE.equals(awarenessEnvelope.getMessageType()) && awarenessEnvelope.getArgs() != null) {
            Bundle args = awarenessEnvelope.getArgs();
            args.setClassLoader(AwarenessFence.class.getClassLoader());
            Parcelable parcelable = args.getParcelable(MESSAGE_TYPE);
            if (parcelable instanceof AwarenessFence) {
                return (AwarenessFence) parcelable;
            }
        }
        return null;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.type);
        parcel.writeString(this.identifier);
        parcel.writeParcelable(this.state, i);
        parcel.writeBundle(this.args);
    }

    public String toString() {
        return String.format(Locale.ENGLISH, "AwarenessFence(type = %s,identifier = %s)", this.type, this.identifier);
    }
}
