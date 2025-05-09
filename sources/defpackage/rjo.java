package defpackage;

import android.text.TextUtils;
import com.huawei.haf.application.BaseApplication;
import com.huawei.health.servicesui.R$string;
import health.compact.a.util.LogUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/* loaded from: classes9.dex */
public class rjo {

    /* renamed from: a, reason: collision with root package name */
    private String f16789a;
    private List<rjq> e = new ArrayList();
    private List<eak> d = new ArrayList();

    public void d(String str) {
        this.f16789a = str;
    }

    public List<rjq> c() {
        return this.e;
    }

    public void a(List<rjq> list) {
        this.e = list;
    }

    public List<eak> e() {
        return b();
    }

    private List<eak> b() {
        rje e;
        rji rjiVar;
        this.d.clear();
        for (rjq rjqVar : this.e) {
            eak eakVar = new eak();
            this.d.add(eakVar);
            eakVar.f(rjqVar.j());
            eakVar.h(rjqVar.i());
            eakVar.b(rjqVar.h());
            if (rjqVar.g() != null && !TextUtils.isEmpty(rjqVar.g().c())) {
                LogUtil.b("GoodThingsData", "soldNum: ", rjqVar.g().c());
                eakVar.d(rjqVar.g().c());
            }
            rji[] e2 = rjqVar.e();
            eakVar.c((e2 == null || e2.length <= 0 || (rjiVar = e2[0]) == null || rjiVar.d() == 0.0d) ? null : e2[0].a());
            String a2 = rjqVar.a();
            if (!TextUtils.isEmpty(a2)) {
                eakVar.i(b(rjqVar.h(), a2));
            }
            String d = rjqVar.d();
            if (!TextUtils.isEmpty(d) && !d.equals(a2)) {
                eakVar.j(b(rjqVar.h(), d));
            }
            eakVar.e(this.f16789a);
            eakVar.g(e(rjqVar));
            rjh c = rjqVar.c();
            if (c != null && (e = c.e()) != null) {
                eakVar.a(e.e());
            }
        }
        return this.d;
    }

    private String b(String str, String str2) {
        float f;
        try {
            f = Float.parseFloat(str2);
        } catch (NumberFormatException unused) {
            LogUtil.e("GoodThingsData", "getMoneyString NumberFormatException");
            f = 0.0f;
        }
        if (Math.round(f) == f) {
            return String.format(Locale.ROOT, BaseApplication.e().getString(R$string.IDS_currency_money), str, String.valueOf((int) f));
        }
        return String.format(Locale.ROOT, BaseApplication.e().getString(R$string.IDS_currency_money), str, String.valueOf(f));
    }

    private String e(rjq rjqVar) {
        List<String> f = rjqVar.f();
        StringBuilder sb = new StringBuilder();
        if (koq.b(f)) {
            return null;
        }
        for (String str : f) {
            if (!TextUtils.isEmpty(str)) {
                sb.append(str);
                sb.append(" ");
            }
        }
        return sb.toString().trim();
    }
}
