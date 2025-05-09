package com.huawei.hiai.awareness.client;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import java.util.Locale;
import java.util.Optional;
import java.util.function.Consumer;

/* loaded from: classes8.dex */
public class FenceState implements Parcelable {
    private static final String AWARENESS_FENCE_IDENTIFIER = "awareness_fence_identifier";
    public static final Parcelable.Creator<FenceState> CREATOR = new Parcelable.Creator<FenceState>() { // from class: com.huawei.hiai.awareness.client.FenceState.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public FenceState createFromParcel(Parcel parcel) {
            return new FenceState(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public FenceState[] newArray(int i) {
            return new FenceState[i];
        }
    };
    public static final String MESSAGE_TYPE = "awareness_fence_state";
    public static final int NOK = -1;
    public static final int OK = 1;
    public static final int UNKNOWN = 0;
    private int currentState;
    private Bundle extras;
    private String identifier;
    private long lastUpdateTimestamp;
    private int previousState;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public FenceState() {
        this.currentState = 0;
        this.previousState = 0;
        this.lastUpdateTimestamp = 0L;
    }

    private FenceState(Parcel parcel) {
        this.currentState = parcel.readInt();
        this.previousState = parcel.readInt();
        this.lastUpdateTimestamp = parcel.readLong();
        Bundle readBundle = parcel.readBundle();
        this.extras = readBundle;
        Optional.ofNullable(readBundle).ifPresent(new Consumer() { // from class: com.huawei.hiai.awareness.client.FenceState$$ExternalSyntheticLambda0
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                FenceState.this.m542lambda$new$0$comhuaweihiaiawarenessclientFenceState((Bundle) obj);
            }
        });
    }

    /* renamed from: lambda$new$0$com-huawei-hiai-awareness-client-FenceState, reason: not valid java name */
    /* synthetic */ void m542lambda$new$0$comhuaweihiaiawarenessclientFenceState(Bundle bundle) {
        this.identifier = this.extras.getString(AWARENESS_FENCE_IDENTIFIER);
    }

    public int getCurrentState() {
        return this.currentState;
    }

    public int getPreviousState() {
        return this.previousState;
    }

    public long getLastUpdateTimestamp() {
        return this.lastUpdateTimestamp;
    }

    @Deprecated
    public Bundle getExtras() {
        return this.extras;
    }

    public String getIdentifier() {
        return this.identifier;
    }

    public void setCurrentState(int i) {
        this.currentState = i;
    }

    public void setPreviousState(int i) {
        this.previousState = i;
    }

    public void setLastUpdateTimestamp(long j) {
        this.lastUpdateTimestamp = j;
    }

    public void flashLastUpdateTimestamp() {
        this.lastUpdateTimestamp = System.currentTimeMillis();
    }

    public void setExtras(Bundle bundle) {
        this.extras = bundle != null ? new Bundle(bundle) : null;
    }

    public void setIdentifier(String str) {
        this.identifier = str;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.currentState);
        parcel.writeInt(this.previousState);
        parcel.writeLong(this.lastUpdateTimestamp);
        if (!TextUtils.isEmpty(this.identifier)) {
            if (this.extras == null) {
                this.extras = new Bundle();
            }
            this.extras.putString(AWARENESS_FENCE_IDENTIFIER, this.identifier);
        }
        parcel.writeBundle(this.extras);
    }

    public int getInt(String str) {
        Bundle bundle = this.extras;
        if (bundle != null) {
            return bundle.getInt(str, 0);
        }
        return 0;
    }

    public long getLong(String str) {
        Bundle bundle = this.extras;
        if (bundle != null) {
            return bundle.getLong(str, 0L);
        }
        return 0L;
    }

    public boolean getBoolean(String str) {
        Bundle bundle = this.extras;
        if (bundle != null) {
            return bundle.getBoolean(str);
        }
        return false;
    }

    public double getDouble(String str) {
        Bundle bundle = this.extras;
        if (bundle != null) {
            return bundle.getDouble(str, 0.0d);
        }
        return 0.0d;
    }

    public String getString(String str) {
        Bundle bundle = this.extras;
        if (bundle != null) {
            return bundle.getString(str);
        }
        return null;
    }

    public int[] getIntArray(String str) {
        Bundle bundle = this.extras;
        if (bundle != null) {
            return bundle.getIntArray(str);
        }
        return null;
    }

    public double[] getDoubleArray(String str) {
        Bundle bundle = this.extras;
        if (bundle != null) {
            return bundle.getDoubleArray(str);
        }
        return null;
    }

    public long[] getLongArray(String str) {
        Bundle bundle = this.extras;
        if (bundle != null) {
            return bundle.getLongArray(str);
        }
        return null;
    }

    public String[] getStringArray(String str) {
        Bundle bundle = this.extras;
        if (bundle != null) {
            return bundle.getStringArray(str);
        }
        return null;
    }

    public Object getObject(String str) {
        Bundle bundle = this.extras;
        if (bundle != null) {
            return bundle.get(str);
        }
        return null;
    }

    public String toString() {
        return String.format(Locale.ENGLISH, "FenceState(%d)", Integer.valueOf(this.currentState));
    }
}
