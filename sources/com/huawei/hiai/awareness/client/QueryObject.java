package com.huawei.hiai.awareness.client;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.ArrayMap;
import java.util.Map;

/* loaded from: classes8.dex */
public class QueryObject implements Parcelable {
    public static final Parcelable.Creator<QueryObject> CREATOR = new Parcelable.Creator<QueryObject>() { // from class: com.huawei.hiai.awareness.client.QueryObject.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public QueryObject createFromParcel(Parcel parcel) {
            return new QueryObject(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public QueryObject[] newArray(int i) {
            return new QueryObject[i];
        }
    };
    private Bundle args;
    private String type;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    private QueryObject(String str) {
        this.type = str;
    }

    private QueryObject(Parcel parcel) {
        this.type = parcel.readString();
        this.args = parcel.readBundle();
    }

    public static QueryObject create(String str) {
        return new QueryObject(str);
    }

    public String getType() {
        return this.type;
    }

    public Bundle getArgs() {
        return this.args != null ? new Bundle(this.args) : new Bundle();
    }

    public QueryObject putArg(String str, int i) {
        if (this.args == null) {
            this.args = new Bundle();
        }
        this.args.putInt(str, i);
        return this;
    }

    public QueryObject putArg(String str, long j) {
        if (this.args == null) {
            this.args = new Bundle();
        }
        this.args.putLong(str, j);
        return this;
    }

    public QueryObject putArg(String str, boolean z) {
        if (this.args == null) {
            this.args = new Bundle();
        }
        this.args.putBoolean(str, z);
        return this;
    }

    public QueryObject putArg(String str, String str2) {
        if (this.args == null) {
            this.args = new Bundle();
        }
        this.args.putString(str, str2);
        return this;
    }

    public QueryObject putArg(String str, float f) {
        if (this.args == null) {
            this.args = new Bundle();
        }
        this.args.putFloat(str, f);
        return this;
    }

    public QueryObject putArg(String str, double d) {
        if (this.args == null) {
            this.args = new Bundle();
        }
        this.args.putDouble(str, d);
        return this;
    }

    public QueryObject putArg(String str, String[] strArr) {
        if (this.args == null) {
            this.args = new Bundle();
        }
        this.args.putStringArray(str, strArr);
        return this;
    }

    public QueryObject putArg(String str, byte[] bArr) {
        if (this.args == null) {
            this.args = new Bundle();
        }
        this.args.putByteArray(str, bArr);
        return this;
    }

    public QueryObject putArg(String str, byte b) {
        if (this.args == null) {
            this.args = new Bundle();
        }
        this.args.putByte(str, b);
        return this;
    }

    public Map<String, Object> getParams() {
        ArrayMap arrayMap = new ArrayMap();
        Bundle bundle = this.args;
        if (bundle == null) {
            return arrayMap;
        }
        for (String str : bundle.keySet()) {
            Object obj = this.args.get(str);
            if (obj != null) {
                arrayMap.put(str, obj);
            }
        }
        return arrayMap;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.type);
        parcel.writeBundle(this.args);
    }
}
