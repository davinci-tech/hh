package com.autonavi.aps.amapapi.restruct;

import com.amap.api.maps.model.MyLocationStyle;
import java.util.Objects;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public final class f {

    /* renamed from: a, reason: collision with root package name */
    public int f1631a = 0;
    public double b = 0.0d;
    public double c = 0.0d;
    public long d = 0;
    public int e = 0;
    public int f = 0;
    public int g = 63;
    public int h = 0;

    public final String a() {
        JSONObject jSONObject;
        try {
            jSONObject = new JSONObject();
            jSONObject.put("time", this.d);
            jSONObject.put("lon", this.c);
            jSONObject.put("lat", this.b);
            jSONObject.put("radius", this.e);
            jSONObject.put(MyLocationStyle.LOCATION_TYPE, this.f1631a);
            jSONObject.put("reType", this.g);
            jSONObject.put("reSubType", this.h);
        } catch (Throwable unused) {
            jSONObject = null;
        }
        return jSONObject == null ? "" : jSONObject.toString();
    }

    public final void a(JSONObject jSONObject) {
        try {
            this.b = jSONObject.optDouble("lat", this.b);
            this.c = jSONObject.optDouble("lon", this.c);
            this.f1631a = jSONObject.optInt(MyLocationStyle.LOCATION_TYPE, this.f1631a);
            this.g = jSONObject.optInt("reType", this.g);
            this.h = jSONObject.optInt("reSubType", this.h);
            this.e = jSONObject.optInt("radius", this.e);
            this.d = jSONObject.optLong("time", this.d);
        } catch (Throwable th) {
            com.autonavi.aps.amapapi.utils.b.a(th, "CoreUtil", "transformLocation");
        }
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        f fVar = (f) obj;
        return this.f1631a == fVar.f1631a && Double.compare(fVar.b, this.b) == 0 && Double.compare(fVar.c, this.c) == 0 && this.d == fVar.d && this.e == fVar.e && this.f == fVar.f && this.g == fVar.g && this.h == fVar.h;
    }

    public final int hashCode() {
        return Objects.hash(Integer.valueOf(this.f1631a), Double.valueOf(this.b), Double.valueOf(this.c), Long.valueOf(this.d), Integer.valueOf(this.e), Integer.valueOf(this.f), Integer.valueOf(this.g), Integer.valueOf(this.h));
    }
}
