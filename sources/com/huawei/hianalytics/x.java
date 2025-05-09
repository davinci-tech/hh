package com.huawei.hianalytics;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.json.JSONObject;

/* loaded from: classes4.dex */
public class x {

    /* renamed from: a, reason: collision with root package name */
    public int f3960a;

    /* renamed from: a, reason: collision with other field name */
    public long f101a;

    /* renamed from: a, reason: collision with other field name */
    public String f102a;

    /* renamed from: a, reason: collision with other field name */
    public final List<a> f103a;
    public long b;

    /* renamed from: b, reason: collision with other field name */
    public String f104b;
    public long c;

    /* renamed from: c, reason: collision with other field name */
    public String f105c;
    public String d;
    public String e;
    public String f;
    public String g;

    public int hashCode() {
        return Objects.hash(this.f104b);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || x.class != obj.getClass()) {
            return false;
        }
        return Objects.equals(this.f104b, ((x) obj).f104b);
    }

    public JSONObject a() {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("item_id", this.f104b);
        jSONObject.put("mc_title", this.f105c);
        jSONObject.put("mc_artist", this.d);
        jSONObject.put("mc_album", this.f);
        jSONObject.put("mc_duration", this.e);
        jSONObject.put("play_start_time", this.f101a);
        jSONObject.put("play_duration", this.b);
        jSONObject.put("favorite_state", this.f3960a);
        jSONObject.put("package_name", this.g);
        return jSONObject;
    }

    /* loaded from: classes8.dex */
    public static class a {

        /* renamed from: a, reason: collision with root package name */
        public final long f3961a;

        /* renamed from: a, reason: collision with other field name */
        public final boolean f106a;

        public a(boolean z, long j) {
            this.f106a = z;
            this.f3961a = j;
        }
    }

    public x(String str) {
        JSONObject jSONObject = new JSONObject(str);
        this.f102a = jSONObject.optString("_id", "");
        this.f104b = jSONObject.optString("item_id", "");
        this.f105c = jSONObject.optString("mc_title", "");
        this.d = jSONObject.optString("mc_artist", "");
        this.f = jSONObject.optString("mc_album", "");
        this.e = jSONObject.optString("mc_duration", "");
        this.f101a = jSONObject.optLong("play_start_time", 0L);
        this.b = jSONObject.optLong("play_duration", 0L);
        this.f3960a = jSONObject.optInt("favorite_state", 0);
        this.g = jSONObject.optString("package_name", "");
        this.c = jSONObject.optLong("event_time", 0L);
        this.f103a = new ArrayList();
    }

    public x() {
        this.f102a = "";
        this.f104b = "";
        this.f105c = "";
        this.d = "";
        this.e = "";
        this.f = "";
        this.f101a = 0L;
        this.b = 0L;
        this.f3960a = 0;
        this.g = "";
        this.c = 0L;
        this.f103a = new ArrayList();
    }
}
