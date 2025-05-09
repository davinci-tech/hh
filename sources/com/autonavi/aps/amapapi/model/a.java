package com.autonavi.aps.amapapi.model;

import android.text.TextUtils;
import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClientOption;
import com.autonavi.aps.amapapi.utils.b;
import com.autonavi.aps.amapapi.utils.i;
import com.huawei.hms.network.embedded.u3;
import com.tencent.open.SocialConstants;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public final class a extends AMapLocation {
    protected String d;
    boolean e;
    String f;
    private String g;
    private String h;
    private int i;
    private String j;
    private int k;
    private String l;
    private JSONObject m;
    private String n;
    private String o;
    private String p;

    public a(String str) {
        super(str);
        this.d = "";
        this.g = null;
        this.h = "";
        this.j = "";
        this.k = 0;
        this.l = "new";
        this.m = null;
        this.n = "";
        this.e = true;
        this.f = String.valueOf(AMapLocationClientOption.GeoLanguage.DEFAULT);
        this.o = "";
        this.p = null;
    }

    public final String a() {
        return this.g;
    }

    public final void a(String str) {
        this.g = str;
    }

    public final String b() {
        return this.h;
    }

    public final void b(String str) {
        this.h = str;
    }

    public final int c() {
        return this.i;
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x002b  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0025  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void c(java.lang.String r2) {
        /*
            r1 = this;
            boolean r0 = android.text.TextUtils.isEmpty(r2)
            if (r0 != 0) goto L1e
            java.lang.String r0 = "0"
            boolean r0 = r2.equals(r0)
            if (r0 == 0) goto L12
            r2 = 0
            r1.i = r2
            goto L21
        L12:
            java.lang.String r0 = "1"
            boolean r2 = r2.equals(r0)
            if (r2 == 0) goto L1e
            r2 = 1
            r1.i = r2
            goto L21
        L1e:
            r2 = -1
            r1.i = r2
        L21:
            int r2 = r1.i
            if (r2 != 0) goto L2b
            java.lang.String r2 = "WGS84"
            super.setCoordType(r2)
            return
        L2b:
            java.lang.String r2 = "GCJ02"
            super.setCoordType(r2)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.autonavi.aps.amapapi.model.a.c(java.lang.String):void");
    }

    public final String d() {
        return this.j;
    }

    public final void d(String str) {
        this.j = str;
    }

    public final String e() {
        return this.l;
    }

    public final void e(String str) {
        this.l = str;
    }

    public final JSONObject f() {
        return this.m;
    }

    public final void a(JSONObject jSONObject) {
        this.m = jSONObject;
    }

    public final String g() {
        return this.n;
    }

    private void i(String str) {
        this.n = str;
    }

    public final a h() {
        String g = g();
        if (TextUtils.isEmpty(g)) {
            return null;
        }
        String[] split = g.split(",");
        if (split.length != 3) {
            return null;
        }
        a aVar = new a("");
        aVar.setProvider(getProvider());
        aVar.setLongitude(i.c(split[0]));
        aVar.setLatitude(i.c(split[1]));
        aVar.setAccuracy(i.d(split[2]));
        aVar.setCityCode(getCityCode());
        aVar.setAdCode(getAdCode());
        aVar.setCountry(getCountry());
        aVar.setProvince(getProvince());
        aVar.setCity(getCity());
        aVar.setTime(getTime());
        aVar.e(e());
        aVar.c(String.valueOf(c()));
        if (i.a(aVar)) {
            return aVar;
        }
        return null;
    }

    public final boolean i() {
        return this.e;
    }

    public final void a(boolean z) {
        this.e = z;
    }

    public final String j() {
        return this.f;
    }

    public final void f(String str) {
        this.f = str;
    }

    private void j(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        String[] split = str.split("\\*");
        int length = split.length;
        int i = 0;
        while (true) {
            if (i >= length) {
                break;
            }
            String str2 = split[i];
            if (!TextUtils.isEmpty(str2)) {
                String[] split2 = str2.split(",");
                setLongitude(i.c(split2[0]));
                setLatitude(i.c(split2[1]));
                setAccuracy(i.e(split2[2]));
                break;
            }
            i++;
        }
        this.o = str;
    }

    @Override // com.amap.api.location.AMapLocation
    public final JSONObject toJson(int i) {
        try {
            JSONObject json = super.toJson(i);
            if (i == 1) {
                json.put("retype", this.j);
                json.put("cens", this.o);
                json.put("coord", this.i);
                json.put("mcell", this.n);
                json.put(SocialConstants.PARAM_APP_DESC, this.d);
                json.put("address", getAddress());
                if (this.m != null && i.a(json, "offpct")) {
                    json.put("offpct", this.m.getString("offpct"));
                }
            } else if (i != 2 && i != 3) {
                return json;
            }
            json.put("type", this.l);
            json.put("isReversegeo", this.e);
            json.put("geoLanguage", this.f);
            return json;
        } catch (Throwable th) {
            b.a(th, "AmapLoc", "toStr");
            return null;
        }
    }

    public final void b(JSONObject jSONObject) {
        try {
            b.a(this, jSONObject);
            e(jSONObject.optString("type", this.l));
            d(jSONObject.optString("retype", this.j));
            j(jSONObject.optString("cens", this.o));
            g(jSONObject.optString(SocialConstants.PARAM_APP_DESC, this.d));
            c(jSONObject.optString("coord", String.valueOf(this.i)));
            i(jSONObject.optString("mcell", this.n));
            a(jSONObject.optBoolean("isReversegeo", this.e));
            f(jSONObject.optString("geoLanguage", this.f));
            if (i.a(jSONObject, "poiid")) {
                setBuildingId(jSONObject.optString("poiid"));
            }
            if (i.a(jSONObject, u3.m)) {
                setBuildingId(jSONObject.optString(u3.m));
            }
            if (i.a(jSONObject, "floor")) {
                setFloor(jSONObject.optString("floor"));
            }
            if (i.a(jSONObject, "flr")) {
                setFloor(jSONObject.optString("flr"));
            }
        } catch (Throwable th) {
            b.a(th, "AmapLoc", "AmapLoc");
        }
    }

    public final void g(String str) {
        this.d = str;
    }

    public final String k() {
        return this.p;
    }

    public final void h(String str) {
        this.p = str;
    }

    public final int l() {
        return this.k;
    }

    public final void a(int i) {
        this.k = i;
    }

    @Override // com.amap.api.location.AMapLocation
    public final String toStr() {
        return toStr(1);
    }

    @Override // com.amap.api.location.AMapLocation
    public final String toStr(int i) {
        JSONObject jSONObject;
        try {
            jSONObject = toJson(i);
            jSONObject.put("nb", this.p);
        } catch (Throwable th) {
            b.a(th, "AMapLocation", "toStr part2");
            jSONObject = null;
        }
        if (jSONObject == null) {
            return null;
        }
        return jSONObject.toString();
    }
}
