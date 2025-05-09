package defpackage;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Handler;
import android.os.Message;
import com.huawei.hwlogsmodel.LogUtil;
import defpackage.kyl;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

/* loaded from: classes5.dex */
public class kym implements Runnable {
    private static String b = null;
    private static boolean d = false;
    private static String e;

    /* renamed from: a, reason: collision with root package name */
    private Context f14702a;
    private Handler c;
    private boolean f;
    private final boolean h;
    private kyl.c.e i;
    private boolean j;

    public kym(Context context, Handler handler, boolean z, boolean z2, boolean z3) {
        this.f14702a = context;
        this.c = handler;
        this.f = z;
        this.j = z2;
        this.h = z3;
    }

    public static void d(boolean z) {
        d = z;
    }

    public static void a(String str) {
        b = str;
    }

    public static void c(String str) {
        e = str;
    }

    @Override // java.lang.Runnable
    public void run() {
        kyl.c a2;
        if (this.f) {
            a2 = c();
        } else if (this.j) {
            a2 = d();
        } else if (this.h) {
            a2 = g();
        } else {
            a2 = a();
        }
        if (a2 == null) {
            c(0, null);
            return;
        }
        kyl.c.e a3 = a(a2);
        this.i = a3;
        List<kxm> a4 = a3 != null ? a(a3.f14701a) : null;
        if (a4 == null) {
            LogUtil.h("AppPullChangeLogThread", "run changelog is null");
            c(0, null);
        } else {
            LogUtil.c("AppPullChangeLogThread", "run changelog is not null");
            c(1, a4);
        }
    }

    private kyl.c c() {
        if (kxu.e().i() != null) {
            String str = kxu.e().i().split("full/")[0] + "full/changelog.xml";
            String b2 = b();
            String j = j();
            LogUtil.c("AppPullChangeLogThread", "getAppChangeLog APP: current system language=", b2, " newLanguage", j);
            return kyl.c(this.f14702a, str, b2, j);
        }
        LogUtil.h("AppPullChangeLogThread", "getAppChangeLog DOWNLOADURL is null");
        return null;
    }

    private kyl.c d() {
        if (kxu.d().i() != null) {
            String str = kxu.d().i().split("full/")[0] + "full/changelog.xml";
            String b2 = b();
            String j = j();
            LogUtil.a("AppPullChangeLogThread", "getAw70ChangeLog AW70 Band: current system language=", b2, " newLanguage", j);
            return kyl.c(this.f14702a, str, b2, j, b);
        }
        LogUtil.h("AppPullChangeLogThread", "getAw70ChangeLog AW70 DOWNLOADURL is null");
        return null;
    }

    private kyl.c g() {
        if (kxu.l().i() != null) {
            String str = kxu.l().i().split("full/")[0] + "full/changelog.xml";
            String b2 = b();
            String j = j();
            LogUtil.c("AppPullChangeLogThread", "getScaleChangeLog Scale Band: current system language=", b2, " newLanguage", j);
            return kyl.c(this.f14702a, str, b2, j);
        }
        LogUtil.h("AppPullChangeLogThread", "getScaleChangeLog Scale DOWNLOADURL is null");
        return null;
    }

    private kyl.c a() {
        String b2 = b();
        String j = j();
        LogUtil.a("AppPullChangeLogThread", "getBandChangeLog Band: current system language=", b2, " newLanguage", j);
        kxl b3 = kxu.b();
        if (b3 != null && b3.i() != null) {
            String[] split = b3.i().split("full/");
            LogUtil.a("AppPullChangeLogThread", "getBandChangeLog: ", b);
            return kyl.c(this.f14702a, split[0] + "full/changelog.xml", b2, j, b);
        }
        String str = e;
        if (str != null) {
            LogUtil.a("AppPullChangeLogThread", "getBandChangeLog mVersionUrl=", str);
            return kyl.c(this.f14702a, e, b2, j, b);
        }
        LogUtil.h("AppPullChangeLogThread", "getBandChangeLog Band DOWNLOADURL is null");
        return null;
    }

    private void c(int i, Object obj) {
        Handler handler = this.c;
        if (handler == null) {
            return;
        }
        Message obtainMessage = handler.obtainMessage();
        obtainMessage.what = i;
        obtainMessage.obj = obj;
        if (d) {
            return;
        }
        this.c.sendMessage(obtainMessage);
    }

    private String b() {
        Configuration configuration = this.f14702a.getResources().getConfiguration();
        return (configuration.locale.getLanguage() + '-' + configuration.locale.getCountry()).toLowerCase(Locale.ENGLISH);
    }

    private String j() {
        String e2 = mtj.e(null);
        return e2 == null ? "" : e2.toLowerCase(Locale.ENGLISH);
    }

    public kyl.c.e a(kyl.c cVar) {
        if (cVar == null) {
            LogUtil.h("AppPullChangeLogThread", "getFeatureWhenPullChangeLogSuccess msgObjOfCallBack==null");
            return null;
        }
        LogUtil.c("AppPullChangeLogThread", "getFeatureWhenPullChangeLogSuccess pull change log success", ", changeLogXml.mCurrentLanguage = ", Integer.valueOf(cVar.e));
        if (cVar.e != -1) {
            return cVar.d.get(cVar.e);
        }
        if (cVar.b != -1) {
            return cVar.d.get(cVar.b);
        }
        return null;
    }

    private List<kxm> a(List<kyl.c.C0322c> list) {
        ArrayList arrayList = new ArrayList();
        if (list != null) {
            for (kyl.c.C0322c c0322c : list) {
                kxm kxmVar = new kxm();
                StringBuffer stringBuffer = new StringBuffer("");
                if (c0322c != null) {
                    kxmVar.c(c0322c.b);
                    Iterator<String> it = c0322c.e.iterator();
                    while (it.hasNext()) {
                        stringBuffer.append(it.next());
                    }
                    kxmVar.b(stringBuffer.toString());
                    arrayList.add(kxmVar);
                } else {
                    kxmVar.c(null);
                }
            }
        }
        return arrayList;
    }

    public void e() {
        Thread thread = new Thread(this);
        thread.setName("Ver-PullLog");
        thread.start();
    }
}
