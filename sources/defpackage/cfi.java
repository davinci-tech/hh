package defpackage;

import android.graphics.Bitmap;
import android.text.TextUtils;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.hihealth.HiUserPreference;
import com.huawei.hihealth.api.HiHealthManager;
import com.huawei.hms.support.feature.result.CommonConstant;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.Base64;
import health.compact.a.HiDateUtil;
import health.compact.a.SharedPreferenceManager;
import java.io.Serializable;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class cfi implements Serializable {
    private static final long serialVersionUID = -7145013958496895419L;

    /* renamed from: a, reason: collision with root package name */
    private float f679a;
    private int b;
    private int c;
    private int d;
    private byte e;
    private int f;
    private String g;
    private String h;
    private boolean i;
    private String j;
    private int k;
    private float l;
    private String m;
    private float n;
    private String o;
    private float p;

    public cfi() {
        this(null);
    }

    public cfi(JSONObject jSONObject) {
        this.e = (byte) -100;
        this.p = -100.0f;
        this.g = "";
        this.b = -100;
        this.f = -1;
        if (jSONObject != null) {
            b(jSONObject);
        }
    }

    public String i() {
        return (String) cpt.d(this.m);
    }

    public void d(String str) {
        this.m = (String) cpt.d(str);
    }

    public String h() {
        return (String) cpt.d(this.j);
    }

    public void b(String str) {
        this.j = (String) cpt.d(str);
    }

    public int a() {
        return ((Integer) cpt.d(Integer.valueOf(this.d))).intValue();
    }

    public void a(int i) {
        this.d = ((Integer) cpt.d(Integer.valueOf(i))).intValue();
    }

    public byte c() {
        return ((Byte) cpt.d(Byte.valueOf(this.e))).byteValue();
    }

    public void a(byte b) {
        this.e = ((Byte) cpt.d(Byte.valueOf(b))).byteValue();
    }

    public int d() {
        return ((Integer) cpt.d(Integer.valueOf(this.c))).intValue();
    }

    public void d(int i) {
        this.c = ((Integer) cpt.d(Integer.valueOf(i))).intValue();
    }

    public float m() {
        return ((Float) cpt.d(Float.valueOf(this.p))).floatValue();
    }

    public void b(final float f) {
        this.p = ((Float) cpt.d(Float.valueOf(f))).floatValue();
        ThreadPoolManager.d().execute(new Runnable() { // from class: cfh
            @Override // java.lang.Runnable
            public final void run() {
                cfi.this.a(f);
            }
        });
    }

    /* synthetic */ void a(float f) {
        String b;
        HiUserPreference userPreference = HiHealthManager.d(BaseApplication.getContext()).getUserPreference("custom.weight_auto_update_status");
        if (userPreference != null) {
            b = userPreference.getValue();
        } else {
            b = SharedPreferenceManager.b(BaseApplication.getContext(), Integer.toString(10000), "health_weight_auto_update");
        }
        if ("0".equals(b)) {
            LogUtil.a("User", "weight userinfo update switch is close");
        } else {
            LogUtil.a("User", "weight userinfo update switch is on");
            e(f);
        }
    }

    public int n() {
        return ((Integer) cpt.d(Integer.valueOf(this.k))).intValue();
    }

    public void e(int i) {
        this.k = ((Integer) cpt.d(Integer.valueOf(i))).intValue();
    }

    public boolean r() {
        return ((Boolean) cpt.d(Boolean.valueOf(this.i))).booleanValue();
    }

    public void b(boolean z) {
        this.i = ((Boolean) cpt.d(Boolean.valueOf(z))).booleanValue();
    }

    public float k() {
        return ((Float) cpt.d(Float.valueOf(this.f679a))).floatValue();
    }

    public void d(float f) {
        this.f679a = ((Float) cpt.d(Float.valueOf(f))).floatValue();
    }

    public int g() {
        return ((Integer) cpt.d(Integer.valueOf(this.b))).intValue();
    }

    public void b(int i) {
        this.b = ((Integer) cpt.d(Integer.valueOf(i))).intValue();
        a(h(i));
    }

    public void e(String str) {
        this.g = (String) cpt.d(str);
    }

    public String e() {
        return (String) cpt.d(this.g);
    }

    public Bitmap Ex_() {
        return (Bitmap) cpt.d(Ev_(this.o));
    }

    public void Ey_(Bitmap bitmap) {
        this.o = Ew_(bitmap);
    }

    public String j() {
        return (String) cpt.d(this.h);
    }

    public void a(String str) {
        this.h = (String) cpt.d(str);
    }

    public float l() {
        return ((Float) cpt.d(Float.valueOf(this.n))).floatValue();
    }

    public void e(float f) {
        this.n = ((Float) cpt.d(Float.valueOf(f))).floatValue();
    }

    public float f() {
        return this.l;
    }

    public void c(float f) {
        this.l = f;
    }

    public int b() {
        return ((Integer) cpt.d(Integer.valueOf(this.f))).intValue();
    }

    public void c(int i) {
        this.f = ((Integer) cpt.d(Integer.valueOf(i))).intValue();
    }

    public boolean t() {
        byte b = this.e;
        return b == 1 || b == 0 || b == 2;
    }

    public boolean p() {
        return this.b > 0;
    }

    public boolean q() {
        return this.c > 0;
    }

    public boolean v() {
        return this.p > 0.0f;
    }

    public boolean s() {
        return q() && p() && t();
    }

    public String a(boolean z) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("UUID", this.m);
            jSONObject.put("name", this.j);
            jSONObject.put(CommonConstant.KEY_GENDER, (int) this.e);
            jSONObject.put("height", this.c);
            if (!Double.isNaN(this.p)) {
                jSONObject.put("weight", this.p);
            }
            jSONObject.put("type", this.k);
            jSONObject.put("isCurrent", this.i);
            if (z) {
                jSONObject.put("icon", this.o);
            } else {
                jSONObject.put("icon", "");
            }
            jSONObject.put("goalWeight", this.f679a);
            jSONObject.put("birthday", this.b);
            jSONObject.put("sid", this.h);
            if (!Double.isNaN(this.n)) {
                jSONObject.put("userInfoWeight", this.n);
            }
        } catch (JSONException e) {
            LogUtil.b("User", "error when create JSON: " + e.getMessage());
        }
        return jSONObject.toString();
    }

    private String Ew_(Bitmap bitmap) {
        return bitmap != null ? Base64.a(nrf.cHp_(bitmap, false)) : "";
    }

    private void b(JSONObject jSONObject) {
        this.m = jSONObject.optString("UUID");
        this.j = jSONObject.optString("name");
        try {
            this.e = Byte.parseByte(jSONObject.optString(CommonConstant.KEY_GENDER));
        } catch (NumberFormatException unused) {
            LogUtil.b("User", "User, parseJson: gender parse byte fail");
        }
        this.c = jSONObject.optInt("height");
        if (!Double.isNaN(jSONObject.optDouble("weight"))) {
            this.p = (float) jSONObject.optDouble("weight");
        } else {
            LogUtil.c("User", "@@save mutiuserinfo weight is null");
        }
        this.k = jSONObject.optInt("type");
        this.i = jSONObject.optBoolean("isCurrent");
        this.o = jSONObject.optString("icon");
        this.f679a = jSONObject.optInt("goalWeight");
        int optInt = jSONObject.optInt("birthday");
        this.b = optInt;
        this.d = h(optInt);
        this.h = jSONObject.optString("sid");
        this.n = (float) jSONObject.optDouble("userInfoWeight");
    }

    private int h(int i) {
        if (p()) {
            return (HiDateUtil.c(System.currentTimeMillis()) / 10000) - (i / 10000);
        }
        return 0;
    }

    private Bitmap Ev_(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        return nrf.cHM_(Base64.e(str));
    }

    public String toString() {
        return "uuid= " + this.m + ",name = " + this.j;
    }
}
