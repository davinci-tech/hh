package defpackage;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.health.suggestion.model.FitRunPlayAudio;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes7.dex */
public final class kr {
    public static kr e;
    public JSONObject b;
    public int i = 10000;
    public boolean f = false;
    public String g = "https://h5.m.taobao.com/mlapp/olist.html";
    public int j = 10;
    public boolean h = false;
    public boolean k = true;
    public boolean l = false;
    public boolean o = false;
    public boolean n = false;
    public boolean m = true;
    public boolean s = true;
    public String p = "";
    public boolean r = false;
    public boolean t = false;
    public boolean q = false;
    public boolean x = false;
    public boolean w = true;
    public String u = "";
    public String v = "";
    public boolean y = false;
    public boolean aa = false;
    public boolean z = false;
    public boolean ab = false;
    public boolean ad = false;
    public int ac = 1000;
    public boolean af = false;
    public boolean c = true;
    public List<e> d = null;

    /* renamed from: a, reason: collision with root package name */
    public int f14554a = -1;

    public class a implements Runnable {

        /* renamed from: a, reason: collision with root package name */
        public final /* synthetic */ Context f14555a;
        public final /* synthetic */ boolean b;
        public final /* synthetic */ lv c;
        public final /* synthetic */ int d;

        public a(lv lvVar, Context context, boolean z, int i) {
            this.c = lvVar;
            this.f14555a = context;
            this.b = z;
            this.d = i;
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                lh a2 = new lp().a(this.c, this.f14555a);
                if (a2 != null) {
                    kr.this.a(this.c, a2.d());
                    kr.this.d(lv.c());
                    kl.b(this.c, "biz", "offcfg|" + this.b + "|" + this.d);
                }
            } catch (Throwable th) {
                ma.c(th);
            }
        }
    }

    public static kr a() {
        if (e == null) {
            kr krVar = new kr();
            e = krVar;
            krVar.u();
        }
        return e;
    }

    private int ad() {
        return this.ac;
    }

    private JSONObject ae() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("timeout", n());
        jSONObject.put("h5_port_degrade", ac());
        jSONObject.put("tbreturl", v());
        jSONObject.put("configQueryInterval", j());
        jSONObject.put("launchAppSwitch", e.e(k()));
        jSONObject.put("scheme_pay_2", l());
        jSONObject.put("intercept_batch", m());
        jSONObject.put("deg_log_mcgw", g());
        jSONObject.put("deg_start_srv_first", h());
        jSONObject.put("prev_jump_dual", s());
        jSONObject.put("use_sc_only", f());
        jSONObject.put("bind_use_imp", c());
        jSONObject.put("retry_bnd_once", q());
        jSONObject.put("skip_trans", t());
        jSONObject.put("start_trans", d());
        jSONObject.put("up_before_pay", y());
        jSONObject.put("use_sc_lck_a", p());
        jSONObject.put("lck_k", o());
        jSONObject.put("bind_with_startActivity", i());
        jSONObject.put("startActivity_InsteadOf_Scheme", r());
        jSONObject.put("retry_aidl_activity_not_start", b());
        jSONObject.put("cfg_max_time", ad());
        jSONObject.put("get_oa_id", aa());
        jSONObject.put("notifyFailApp", x());
        jSONObject.put("enableStartActivityFallback", ab());
        jSONObject.put("enableBindExFallback", w());
        jSONObject.put("startactivity_in_ui_thread", z());
        jSONObject.put("ap_args", e());
        return jSONObject;
    }

    public boolean aa() {
        return this.c;
    }

    public boolean ab() {
        return this.aa;
    }

    public boolean ac() {
        return this.f;
    }

    public boolean b() {
        return this.ad;
    }

    public boolean c() {
        return this.r;
    }

    public boolean d() {
        return this.x;
    }

    public String f() {
        return this.p;
    }

    public boolean g() {
        return this.n;
    }

    public boolean h() {
        return this.m;
    }

    public String i() {
        return this.v;
    }

    public int j() {
        return this.j;
    }

    public List<e> k() {
        return this.d;
    }

    public boolean l() {
        return this.h;
    }

    public boolean m() {
        return this.k;
    }

    public int n() {
        int i = this.i;
        if (i < 1000 || i > 20000) {
            ma.a("DynCon", "time(def) = 10000");
            return 10000;
        }
        ma.a("DynCon", "time = " + this.i);
        return this.i;
    }

    public String o() {
        return this.u;
    }

    public boolean p() {
        return this.ab;
    }

    public boolean q() {
        return this.t;
    }

    public boolean r() {
        return this.y;
    }

    public boolean s() {
        return this.s;
    }

    public boolean t() {
        return this.q;
    }

    public void u() {
        Context d = lw.c().d();
        String d2 = mb.d(lv.c(), d, "alipay_cashier_dynamic_config", null);
        try {
            this.f14554a = Integer.parseInt(mb.d(lv.c(), d, "utdid_factor", "-1"));
        } catch (Exception unused) {
        }
        b(d2);
    }

    public String v() {
        return this.g;
    }

    public boolean w() {
        return this.z;
    }

    public boolean x() {
        return this.af;
    }

    public boolean y() {
        return this.w;
    }

    public boolean z() {
        return this.l;
    }

    public static final class e {
        public final String b;
        public final int c;
        public final String d;

        public e(String str, int i, String str2) {
            this.d = str;
            this.c = i;
            this.b = str2;
        }

        public static e a(JSONObject jSONObject) {
            if (jSONObject == null) {
                return null;
            }
            return new e(jSONObject.optString("pn"), jSONObject.optInt(FitRunPlayAudio.PLAY_TYPE_V, 0), jSONObject.optString("pk"));
        }

        public String toString() {
            return String.valueOf(d(this));
        }

        public static List<e> c(JSONArray jSONArray) {
            if (jSONArray == null) {
                return null;
            }
            ArrayList arrayList = new ArrayList();
            int length = jSONArray.length();
            for (int i = 0; i < length; i++) {
                e a2 = a(jSONArray.optJSONObject(i));
                if (a2 != null) {
                    arrayList.add(a2);
                }
            }
            return arrayList;
        }

        public static JSONObject d(e eVar) {
            if (eVar == null) {
                return null;
            }
            try {
                return new JSONObject().put("pn", eVar.d).put(FitRunPlayAudio.PLAY_TYPE_V, eVar.c).put("pk", eVar.b);
            } catch (JSONException e) {
                ma.c(e);
                return null;
            }
        }

        public static JSONArray e(List<e> list) {
            if (list == null) {
                return null;
            }
            JSONArray jSONArray = new JSONArray();
            Iterator<e> it = list.iterator();
            while (it.hasNext()) {
                jSONArray.put(d(it.next()));
            }
            return jSONArray;
        }
    }

    public JSONObject e() {
        return this.b;
    }

    private void b(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        try {
            c(new JSONObject(str));
        } catch (Throwable th) {
            ma.c(th);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(lv lvVar) {
        try {
            mb.c(lvVar, lw.c().d(), "alipay_cashier_dynamic_config", ae().toString());
        } catch (Exception e2) {
            ma.c(e2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(lv lvVar, String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            JSONObject optJSONObject = jSONObject.optJSONObject("st_sdk_config");
            lt.b(lvVar, optJSONObject, lt.e(lvVar, jSONObject));
            if (optJSONObject != null) {
                c(optJSONObject);
            } else {
                ma.c("DynCon", "empty config");
            }
        } catch (Throwable th) {
            ma.c(th);
        }
    }

    private void c(JSONObject jSONObject) {
        this.i = jSONObject.optInt("timeout", 10000);
        this.f = jSONObject.optBoolean("h5_port_degrade", false);
        this.g = jSONObject.optString("tbreturl", "https://h5.m.taobao.com/mlapp/olist.html").trim();
        this.j = jSONObject.optInt("configQueryInterval", 10);
        this.d = e.c(jSONObject.optJSONArray("launchAppSwitch"));
        this.h = jSONObject.optBoolean("scheme_pay_2", false);
        this.k = jSONObject.optBoolean("intercept_batch", true);
        this.n = jSONObject.optBoolean("deg_log_mcgw", false);
        this.m = jSONObject.optBoolean("deg_start_srv_first", true);
        this.s = jSONObject.optBoolean("prev_jump_dual", true);
        this.p = jSONObject.optString("use_sc_only", "");
        this.r = jSONObject.optBoolean("bind_use_imp", false);
        this.t = jSONObject.optBoolean("retry_bnd_once", false);
        this.q = jSONObject.optBoolean("skip_trans", false);
        this.x = jSONObject.optBoolean("start_trans", false);
        this.w = jSONObject.optBoolean("up_before_pay", true);
        this.u = jSONObject.optString("lck_k", "");
        this.ab = jSONObject.optBoolean("use_sc_lck_a", false);
        this.ad = jSONObject.optBoolean("retry_aidl_activity_not_start", false);
        this.af = jSONObject.optBoolean("notifyFailApp", false);
        this.v = jSONObject.optString("bind_with_startActivity", "");
        this.y = jSONObject.optBoolean("startActivity_InsteadOf_Scheme", false);
        this.ac = jSONObject.optInt("cfg_max_time", 1000);
        this.c = jSONObject.optBoolean("get_oa_id", true);
        this.aa = jSONObject.optBoolean("enableStartActivityFallback", false);
        this.z = jSONObject.optBoolean("enableBindExFallback", false);
        this.l = jSONObject.optBoolean("startactivity_in_ui_thread", false);
        this.b = jSONObject.optJSONObject("ap_args");
    }

    public void a(lv lvVar, Context context, boolean z, int i) {
        kl.b(lvVar, "biz", "oncfg|" + z + "|" + i);
        a aVar = new a(lvVar, context, z, i);
        if (z && !md.g()) {
            int ad = ad();
            if (md.b(ad, aVar, "AlipayDCPBlok")) {
                return;
            }
            kl.c(lvVar, "biz", "LogAppFetchConfigTimeout", "" + ad);
            return;
        }
        Thread thread = new Thread(aVar);
        thread.setName("AlipayDCP");
        thread.start();
    }

    public boolean b(Context context, int i) {
        if (this.f14554a == -1) {
            this.f14554a = md.d();
            mb.c(lv.c(), context, "utdid_factor", String.valueOf(this.f14554a));
        }
        return this.f14554a < i;
    }
}
