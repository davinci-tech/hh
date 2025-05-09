package com.huawei.hms.hatool;

import android.text.TextUtils;
import com.huawei.watchface.mvp.ui.activity.ScanImageActivity;
import java.io.UnsupportedEncodingException;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes4.dex */
public class l0 {

    /* renamed from: a, reason: collision with root package name */
    private String f4599a;
    private String b;
    private String c;
    private List<b1> d;
    private String e;

    public void a() {
        g d1Var;
        b0 c;
        String str;
        String a2 = a(this.f4599a, this.b);
        if (!TextUtils.isEmpty(a2) || "preins".equals(this.b)) {
            if (!"_hms_config_tag".equals(this.f4599a) && !"_openness_config_tag".equals(this.f4599a)) {
                b();
            }
            h1 d = d();
            if (d != null) {
                byte[] a3 = a(d);
                if (a3.length == 0) {
                    str = "request body is empty";
                } else {
                    d1Var = new f(a3, a2, this.f4599a, this.b, this.e, this.d);
                    c = b0.b();
                }
            } else {
                d1Var = new d1(this.d, this.f4599a, this.e, this.b);
                c = b0.c();
            }
            c.a(d1Var);
            return;
        }
        str = "collectUrl is empty";
        v.e("hmsSdk", str);
    }

    private h1 d() {
        return k.a(this.d, this.f4599a, this.b, this.e, this.c);
    }

    private JSONArray c() {
        JSONArray jSONArray = new JSONArray();
        Iterator<b1> it = this.d.iterator();
        while (it.hasNext()) {
            try {
                jSONArray.put(it.next().d());
            } catch (JSONException unused) {
                v.c("hmsSdk", "handleEvents: json error,Abandon this data");
            }
        }
        return jSONArray;
    }

    private void b() {
        if (c0.a(q0.i(), "backup_event", ScanImageActivity.SCAN_IMAGE_LENGTH_LIMIT)) {
            v.d("hmsSdk", "backup file reach max limited size, delete it");
            d.a(q0.i(), "backup_event", new String[0]);
            return;
        }
        JSONArray c = c();
        String a2 = n1.a(this.f4599a, this.b, this.e);
        v.c("hmsSdk", "Update data cached into backup,spKey: " + a2);
        d.b(q0.i(), "backup_event", a2, c.toString());
    }

    private byte[] a(h1 h1Var) {
        String str;
        try {
            JSONObject a2 = h1Var.a();
            if (a2 != null) {
                return k1.a(a2.toString().getBytes("UTF-8"));
            }
            v.e("hmsSdk", "uploadEvents is null");
            return new byte[0];
        } catch (UnsupportedEncodingException unused) {
            str = "sendData(): getBytes - Unsupported coding format!!";
            v.e("hmsSdk", str);
            return new byte[0];
        } catch (JSONException unused2) {
            str = "uploadEvents to json error";
            v.e("hmsSdk", str);
            return new byte[0];
        }
    }

    private String a(String str, String str2) {
        String str3;
        String f = a1.f(str, str2);
        if (TextUtils.isEmpty(f)) {
            v.a("hmsSdk", "No report address,TAG : %s,TYPE: %s ", str, str2);
            return "";
        }
        if ("oper".equals(str2)) {
            str3 = "{url}/common/hmshioperqrt";
        } else if ("maint".equals(str2)) {
            str3 = "{url}/common/hmshimaintqrt";
        } else {
            if (!"diffprivacy".equals(str2)) {
                return "";
            }
            str3 = "{url}/common/common2";
        }
        return str3.replace("{url}", f);
    }

    public l0(String str, String str2, String str3, List<b1> list, String str4) {
        this.f4599a = str;
        this.b = str2;
        this.c = str3;
        this.d = list;
        this.e = str4;
    }
}
