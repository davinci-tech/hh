package defpackage;

import android.text.TextUtils;
import com.huawei.ads.adsrec.db.table.AdCreativeContentRecord;
import com.huawei.ads.adsrec.db.table.AdSlotMapRecord;
import com.huawei.ads.adsrec.db.table.MaterialSummaryRecord;
import com.huawei.ads.fund.anno.NoDbField;
import com.huawei.ads.fund.util.StringUtils;
import com.huawei.openalliance.ad.constant.MapKeyNames;
import com.huawei.openplatform.abl.log.HiAdLog;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public class vb implements Cloneable {

    /* renamed from: a, reason: collision with root package name */
    private String f17641a;
    private int b;
    private String c;
    private String d;
    private String e;
    private double f;
    private String g;
    private String h;
    private double i;
    private String j;

    @NoDbField
    private String k;

    @NoDbField
    private Boolean l;
    private double m;
    private double n;
    private double o;
    private JSONObject p;
    private AdCreativeContentRecord q;
    private JSONObject r;
    private MaterialSummaryRecord s;
    private AdSlotMapRecord t;

    public Boolean y() {
        return this.l;
    }

    public String x() {
        return this.f17641a;
    }

    public int s() {
        return this.b;
    }

    public String toString() {
        return "Content{pkgName='" + this.c + "', slotId='" + this.f17641a + "', contentId='" + this.d + "', recallSource='" + this.b + '}';
    }

    public String r() {
        return this.c;
    }

    public double p() {
        return this.i;
    }

    public MaterialSummaryRecord q() {
        return this.s;
    }

    public String t() {
        return this.e;
    }

    public String k() {
        AdCreativeContentRecord adCreativeContentRecord = this.q;
        if (adCreativeContentRecord != null) {
            return adCreativeContentRecord.g();
        }
        return null;
    }

    public String m() {
        AdCreativeContentRecord adCreativeContentRecord = this.q;
        if (adCreativeContentRecord != null) {
            return adCreativeContentRecord.d();
        }
        return null;
    }

    public double o() {
        return this.f;
    }

    public String n() {
        if (StringUtils.isBlank(this.k)) {
            this.k = "1";
        }
        return this.k;
    }

    public JSONObject l() {
        vj.b(this.p, "recallSource", this.b);
        AdCreativeContentRecord adCreativeContentRecord = this.q;
        if (adCreativeContentRecord != null) {
            vj.b(this.p, "metaData", vj.a(adCreativeContentRecord.i()));
            vj.b(this.p, MapKeyNames.THIRD_MONITORS, vj.e(this.q.j()));
        }
        return this.p;
    }

    public String f() {
        return this.d;
    }

    public String g() {
        JSONObject jSONObject;
        if (this.j == null) {
            this.j = "";
            this.j = ("3".equals(this.p.optString("apiVer")) || (jSONObject = this.r) == null) ? uz.a(this.p) : jSONObject.optString("title");
        }
        return this.j;
    }

    public AdSlotMapRecord i() {
        return this.t;
    }

    public double h() {
        return this.o;
    }

    public double j() {
        return this.m;
    }

    public double a() {
        return this.n;
    }

    public String c() {
        JSONObject jSONObject;
        if (this.h == null && (jSONObject = this.r) != null) {
            this.h = jSONObject.optString("description");
        }
        return this.h;
    }

    public void d(double d) {
        this.f = d;
    }

    public AdCreativeContentRecord d() {
        return this.q;
    }

    public void c(double d) {
        this.o = d;
    }

    public String b() {
        JSONObject jSONObject;
        if (this.g == null) {
            this.g = "";
            this.g = ("3".equals(this.p.optString("apiVer")) || (jSONObject = this.r) == null) ? uz.d(this.p) : jSONObject.optString("label");
        }
        return this.g;
    }

    public void b(double d) {
        this.m = d;
    }

    public vb e() {
        try {
            vb vbVar = (vb) super.clone();
            AdCreativeContentRecord adCreativeContentRecord = this.q;
            if (adCreativeContentRecord != null) {
                vbVar.q = (AdCreativeContentRecord) adCreativeContentRecord.copy();
            }
            AdSlotMapRecord adSlotMapRecord = this.t;
            if (adSlotMapRecord != null) {
                vbVar.t = (AdSlotMapRecord) adSlotMapRecord.copy();
            }
            MaterialSummaryRecord materialSummaryRecord = this.s;
            if (materialSummaryRecord != null) {
                vbVar.s = (MaterialSummaryRecord) materialSummaryRecord.copy();
            }
            return vbVar;
        } catch (CloneNotSupportedException unused) {
            HiAdLog.w("Content", "copy failed");
            return null;
        }
    }

    public void a(boolean z) {
        this.l = Boolean.valueOf(z);
    }

    public void e(double d) {
        this.n = d;
    }

    private void v() {
        JSONObject jSONObject = this.p;
        if (jSONObject != null) {
            this.e = jSONObject.optString("interactiontype");
            this.k = this.p.optString("dspid");
        }
    }

    private void c(String str, JSONObject jSONObject) {
        if (jSONObject == null) {
            return;
        }
        this.d = jSONObject.optString("contentid");
        this.b = 0;
        this.q = new AdCreativeContentRecord(jSONObject);
        this.r = vj.a(jSONObject.optString("metaData"));
        v();
        this.s = new MaterialSummaryRecord(this.c, str, this.f17641a, this.d, jSONObject);
        jSONObject.remove("metaData");
        jSONObject.remove(MapKeyNames.THIRD_MONITORS);
        jSONObject.remove("deviceAiParam");
        this.t = new AdSlotMapRecord(this.c, this.f17641a, jSONObject);
    }

    private void w() {
        AdCreativeContentRecord adCreativeContentRecord;
        if (this.p == null) {
            this.p = a(this.t);
            v();
        }
        if (this.r != null || (adCreativeContentRecord = this.q) == null) {
            return;
        }
        this.r = vj.a(adCreativeContentRecord.i());
    }

    private JSONObject a(AdSlotMapRecord adSlotMapRecord) {
        if (adSlotMapRecord == null || TextUtils.isEmpty(adSlotMapRecord.e())) {
            return new JSONObject();
        }
        try {
            return new JSONObject(adSlotMapRecord.e());
        } catch (JSONException unused) {
            HiAdLog.w("Content", "create valued json obj err");
            return new JSONObject();
        }
    }

    public vb(String str, String str2, String str3, JSONObject jSONObject) {
        this.i = -100.0d;
        this.c = str;
        this.f17641a = str3;
        this.p = jSONObject;
        c(str2, jSONObject);
    }

    public vb(AdSlotMapRecord adSlotMapRecord, AdCreativeContentRecord adCreativeContentRecord) {
        this.i = -100.0d;
        this.b = 1;
        this.t = adSlotMapRecord;
        this.q = adCreativeContentRecord;
        if (adSlotMapRecord != null) {
            this.c = adSlotMapRecord.a();
            this.f17641a = adSlotMapRecord.c();
            this.d = adSlotMapRecord.b();
        }
        w();
    }
}
