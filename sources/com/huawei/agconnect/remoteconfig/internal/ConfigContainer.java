package com.huawei.agconnect.remoteconfig.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.huawei.agconnect.common.api.Logger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public class ConfigContainer implements Parcelable {
    public static final Parcelable.Creator<ConfigContainer> CREATOR = new Parcelable.Creator<ConfigContainer>() { // from class: com.huawei.agconnect.remoteconfig.internal.ConfigContainer.1
        @Override // android.os.Parcelable.Creator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public ConfigContainer[] newArray(int i) {
            return new ConfigContainer[i];
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public ConfigContainer createFromParcel(Parcel parcel) {
            return new ConfigContainer(parcel);
        }
    };

    /* renamed from: a, reason: collision with root package name */
    private JSONObject f1810a;
    private List<Map<String, String>> b;
    private String c;
    private long d;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        JSONObject jSONObject = this.f1810a;
        if (jSONObject == null) {
            jSONObject = new JSONObject();
        }
        parcel.writeString(jSONObject.toString());
        parcel.writeString(this.c);
        parcel.writeLong(this.d);
        parcel.writeString((this.b != null ? new JSONArray((Collection) this.b) : new JSONArray()).toString());
    }

    public String c() {
        return this.c;
    }

    public boolean b(long j) {
        return System.currentTimeMillis() - this.d > j * 1000;
    }

    public List<Map<String, String>> b() {
        return this.b;
    }

    public void a(long j) {
        this.d = j;
    }

    public JSONObject a() {
        return this.f1810a;
    }

    public ConfigContainer(JSONObject jSONObject) {
        this.f1810a = jSONObject == null ? new JSONObject() : jSONObject;
    }

    public ConfigContainer(List<com.huawei.agconnect.remoteconfig.internal.a.b> list, List<Map<String, String>> list2, String str) {
        this.f1810a = com.huawei.agconnect.remoteconfig.internal.c.b.a(list);
        this.b = list2;
        this.c = str;
        this.d = System.currentTimeMillis();
    }

    private ConfigContainer(Parcel parcel) {
        Logger.v("RemoteConfig", "read config container from cache");
        try {
            String readString = parcel.readString();
            if (readString == null || readString.length() <= 0) {
                this.f1810a = new JSONObject();
            } else {
                this.f1810a = new JSONObject(readString);
            }
        } catch (JSONException e) {
            Logger.e("RemoteConfig", "parcel json value format error", e);
            this.f1810a = new JSONObject();
        }
        this.c = parcel.readString();
        this.d = parcel.readLong();
        try {
            String readString2 = parcel.readString();
            this.b = (readString2 == null || readString2.length() <= 0) ? new ArrayList<>() : com.huawei.agconnect.remoteconfig.internal.c.b.a(new JSONArray(readString2));
        } catch (JSONException e2) {
            Logger.e("RemoteConfig", "parcel json value format error", e2);
            this.b = new ArrayList();
        }
    }
}
