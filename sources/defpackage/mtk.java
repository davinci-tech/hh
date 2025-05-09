package defpackage;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import com.huawei.haf.download.DownloadPluginCallback;
import com.huawei.haf.download.DownloadPluginUrl;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.pluginmgr.EzPluginConfigBase;
import com.huawei.pluginmgr.filedownload.PullListener;
import health.compact.a.LogUtil;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes.dex */
public class mtk {
    private static int b;
    private final mrx e;
    private final HashMap<Integer, e> d = new HashMap<>(8);
    private final HashMap<String, File> c = new HashMap<>(8);

    /* renamed from: a, reason: collision with root package name */
    private final Handler f15164a = new c(Looper.getMainLooper());

    /* JADX INFO: Access modifiers changed from: private */
    public static int c(int i) {
        if (i == -10) {
            return 1;
        }
        if (i != -5) {
            if (i == -4) {
                return 6;
            }
            if (i == -3) {
                return 2;
            }
            if (i != -1) {
                return (i == 0 || i == 1) ? 0 : 3;
            }
        }
        return 4;
    }

    public mtk(EzPluginConfigBase ezPluginConfigBase) {
        this.e = new mrx(ezPluginConfigBase);
    }

    public EzPluginConfigBase c() {
        return this.e.a();
    }

    public File c(String str) {
        File file;
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        synchronized (this.c) {
            file = this.c.get(str);
        }
        if (file == null) {
            return e(str);
        }
        if (file.exists()) {
            return file;
        }
        synchronized (this.c) {
            this.c.remove(str);
        }
        return null;
    }

    /* loaded from: classes6.dex */
    static class d {

        /* renamed from: a, reason: collision with root package name */
        int f15167a;
        e b;
        String d;
        msq e;

        private d() {
            this.b = null;
            this.d = null;
            this.e = null;
            this.f15167a = 0;
        }
    }

    /* loaded from: classes6.dex */
    static class e {

        /* renamed from: a, reason: collision with root package name */
        DownloadPluginCallback f15168a;
        boolean b;
        int c;
        int d;
        DownloadPluginUrl e;
        int f;
        mrx g;
        int h;
        int i;
        int j;
        int k;
        int l;
        int m;
        int n;
        List<String> o;

        private e() {
            this.o = null;
            this.f = 0;
            this.j = 0;
            this.b = false;
            this.i = 0;
            this.f15168a = null;
            this.e = null;
            this.g = null;
            this.m = 0;
            this.l = 0;
            this.n = 0;
            this.k = 0;
            this.c = 0;
            this.d = 0;
            this.h = -1;
        }
    }

    public int a(List<String> list, int i, DownloadPluginCallback downloadPluginCallback, DownloadPluginUrl downloadPluginUrl) {
        if (list == null || list.isEmpty()) {
            return -1;
        }
        return e(new ArrayList(list), i, downloadPluginCallback, downloadPluginUrl);
    }

    private int e(List<String> list, int i, DownloadPluginCallback downloadPluginCallback, DownloadPluginUrl downloadPluginUrl) {
        e eVar = new e();
        eVar.o = list;
        eVar.m = eVar.o.size();
        eVar.j = 1;
        eVar.b = i <= 0;
        if (i <= 0) {
            i = 10000;
        }
        eVar.i = i;
        eVar.f15168a = downloadPluginCallback;
        eVar.e = downloadPluginUrl;
        int c2 = c(eVar);
        coC_(this.f15164a, eVar, this.e);
        return c2;
    }

    public void b(int i) {
        e g = g(i);
        if (g == null) {
            return;
        }
        Handler handler = this.f15164a;
        handler.sendMessage(Message.obtain(handler, 1102, g));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void coC_(final Handler handler, final e eVar, final mrx mrxVar) {
        boolean z = true;
        boolean z2 = mrxVar == eVar.g;
        if (eVar.e != null) {
            if (Looper.getMainLooper() == Looper.myLooper()) {
                ThreadPoolManager.d().c(handler.toString(), new Runnable() { // from class: mtk.3
                    @Override // java.lang.Runnable
                    public void run() {
                        mtk.coC_(handler, eVar, mrxVar);
                    }
                });
                return;
            }
            String downloadPluginUrl = eVar.e.getDownloadPluginUrl(null, true);
            String e2 = eVar.g.e(downloadPluginUrl, b(eVar.g.a(), downloadPluginUrl, eVar));
            String e3 = eVar.g.e();
            if (z2 || TextUtils.isEmpty(e3) || !e3.equals(mrxVar.e())) {
                z = z2;
            } else {
                eVar.g = mrxVar;
            }
            LogUtil.c("Login_DownloadPluginHelper", "updatePluginDownloadUrl success. queryStr=(", e2, "), default=", Boolean.valueOf(z2), ", reuse=", Boolean.valueOf(z));
        }
        handler.sendMessageDelayed(Message.obtain(handler, 1000, eVar), (z2 || mrxVar.c()) ? 0L : 500L);
    }

    private static String b(EzPluginConfigBase ezPluginConfigBase, String str, e eVar) {
        if (TextUtils.isEmpty(str) || !str.contains("/commonAbility/configCenter/")) {
            return ezPluginConfigBase.getPluginQueryString(eVar.o.isEmpty() ? null : eVar.o.get(0));
        }
        return "";
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean a(mrx mrxVar) {
        boolean z = true;
        if (!mrxVar.c()) {
            synchronized (mrxVar) {
                if (!mrxVar.c()) {
                    boolean i = mrxVar.i();
                    mrxVar.b(i);
                    z = i;
                }
            }
            LogUtil.c("Login_DownloadPluginHelper", "updateIndex file, updateIndexCache result=", Boolean.valueOf(z));
        }
        return z;
    }

    private static msa e(mrx mrxVar, String str) {
        a(mrxVar);
        return mrxVar.c(str);
    }

    private static List<String> d(mrx mrxVar) {
        if (!a(mrxVar)) {
            return Collections.EMPTY_LIST;
        }
        List<msa> b2 = mrxVar.b();
        if (b2 == null) {
            return Collections.EMPTY_LIST;
        }
        ArrayList arrayList = new ArrayList(b2.size());
        Iterator<msa> it = b2.iterator();
        while (it.hasNext()) {
            arrayList.add(it.next().b());
        }
        return arrayList;
    }

    private boolean a(e eVar, boolean z, int i) {
        eVar.o = d(eVar.g);
        eVar.m = eVar.o.size();
        if (!eVar.o.isEmpty()) {
            return true;
        }
        if (z) {
            i = 4;
        }
        b(eVar, i);
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void coF_(final Handler handler, final e eVar) {
        if (eVar.g.c()) {
            handler.sendMessage(Message.obtain(handler, 1001, eVar));
            return;
        }
        eVar.g.a(null);
        handler.sendMessageDelayed(Message.obtain(handler, 1101, eVar), eVar.i);
        eVar.g.e(new PullListener() { // from class: mtk.4
            /* JADX WARN: Removed duplicated region for block: B:10:? A[RETURN, SYNTHETIC] */
            /* JADX WARN: Removed duplicated region for block: B:7:0x0058  */
            @Override // com.huawei.pluginmgr.filedownload.PullListener
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public void onPullingChange(defpackage.msq r5, defpackage.mso r6) {
                /*
                    r4 = this;
                    int r0 = r6.i()
                    r1 = 0
                    if (r0 == 0) goto L33
                    r5 = 1
                    if (r0 == r5) goto L13
                    int r5 = r6.i()
                    int r5 = defpackage.mtk.d(r5)
                    goto L56
                L13:
                    mtk$e r5 = r2
                    mrx r5 = r5.g
                    defpackage.mtk.b(r5)
                    mtk$e r5 = r2
                    mrx r5 = r5.g
                    java.lang.String r0 = r6.a()
                    r5.a(r0)
                    android.os.Handler r5 = r3
                    r0 = 1001(0x3e9, float:1.403E-42)
                    mtk$e r2 = r2
                    android.os.Message r0 = android.os.Message.obtain(r5, r0, r2)
                    r5.sendMessage(r0)
                    goto L55
                L33:
                    mtk$d r0 = new mtk$d
                    r2 = 0
                    r0.<init>()
                    mtk$e r2 = r2
                    r0.b = r2
                    r0.e = r5
                    mtk$e r5 = r2
                    mrx r5 = r5.g
                    java.lang.String r2 = r6.a()
                    r5.a(r2)
                    android.os.Handler r5 = r3
                    r2 = 1003(0x3eb, float:1.406E-42)
                    android.os.Message r0 = android.os.Message.obtain(r5, r2, r0)
                    r5.sendMessage(r0)
                L55:
                    r5 = r1
                L56:
                    if (r5 == 0) goto L7f
                    int r6 = r6.i()
                    java.lang.String r6 = defpackage.mtk.e(r6)
                    java.lang.String r0 = "), reason="
                    java.lang.Integer r2 = java.lang.Integer.valueOf(r5)
                    java.lang.String r3 = "updateIndex file fail("
                    java.lang.Object[] r6 = new java.lang.Object[]{r3, r6, r0, r2}
                    java.lang.String r0 = "Login_DownloadPluginHelper"
                    health.compact.a.ReleaseLogUtil.b(r0, r6)
                    android.os.Handler r6 = r3
                    r0 = 1002(0x3ea, float:1.404E-42)
                    mtk$e r2 = r2
                    android.os.Message r5 = android.os.Message.obtain(r6, r0, r5, r1, r2)
                    r6.sendMessage(r5)
                L7f:
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: defpackage.mtk.AnonymousClass4.onPullingChange(msq, mso):void");
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void coD_(Handler handler, e eVar, boolean z, int i) {
        handler.removeMessages(1101);
        if (!eVar.o.isEmpty() || a(eVar, z, i)) {
            if (!z) {
                d(eVar, i);
                return;
            }
            for (String str : (String[]) eVar.o.toArray(new String[eVar.o.size()])) {
                d dVar = new d();
                dVar.b = eVar;
                dVar.d = str;
                msa e2 = e(eVar.g, dVar.d);
                if (e2 == null) {
                    LogUtil.c("Login_DownloadPluginHelper", "updateDescrption file fail(index not exist the plugin), mUuid=", dVar.d);
                    c(dVar, 4);
                } else {
                    msq c2 = msj.a().c(dVar.d);
                    if (c2 != null) {
                        LogUtil.c("Login_DownloadPluginHelper", "updateDescrption file(cancel existing tasks), mUuid=", dVar.d);
                        msj.a().b(c2);
                    }
                    if (eVar.g.b(dVar.d)) {
                        LogUtil.c("Login_DownloadPluginHelper", "updateDescrption file success(plugin is avaiable), mUuid=", dVar.d);
                        c(dVar, 0);
                    } else {
                        if (!b(eVar)) {
                            coB_(handler, eVar);
                        }
                        if (!eVar.g.a().needDescriptionFile()) {
                            dVar.f15167a = e2.e();
                            handler.sendMessageDelayed(Message.obtain(handler, 1004, dVar), c2 == null ? 0L : 500L);
                        } else {
                            coE_(handler, dVar);
                        }
                    }
                }
            }
            if (b(eVar) && eVar.o.isEmpty()) {
                coy_(handler, eVar, 0);
            }
        }
    }

    private void coE_(final Handler handler, final d dVar) {
        dVar.b.g.a(dVar.d, new PullListener() { // from class: mtk.1
            /* JADX WARN: Removed duplicated region for block: B:10:? A[RETURN, SYNTHETIC] */
            /* JADX WARN: Removed duplicated region for block: B:7:0x006c  */
            @Override // com.huawei.pluginmgr.filedownload.PullListener
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public void onPullingChange(defpackage.msq r11, defpackage.mso r12) {
                /*
                    r10 = this;
                    int r0 = r12.i()
                    r1 = 0
                    java.lang.String r2 = "Login_DownloadPluginHelper"
                    r3 = 0
                    if (r0 == 0) goto L4b
                    r11 = 1
                    if (r0 == r11) goto L16
                    int r11 = r12.i()
                    int r11 = defpackage.mtk.d(r11)
                    goto L6a
                L16:
                    mtk$d r11 = r2
                    java.lang.String r11 = r11.d
                    java.lang.String r0 = "updateDescrption file success, mUuid="
                    java.lang.Object[] r11 = new java.lang.Object[]{r0, r11}
                    health.compact.a.LogUtil.c(r2, r11)
                    mtk$d r11 = r2
                    r11.e = r1
                    mtk$d r11 = r2
                    int r0 = r12.j()
                    r11.f15167a = r0
                    mtk$d r11 = r2
                    mtk$e r11 = r11.b
                    mrx r11 = r11.g
                    java.lang.String r0 = r12.a()
                    r11.a(r0)
                    android.os.Handler r11 = r3
                    r0 = 1004(0x3ec, float:1.407E-42)
                    mtk$d r4 = r2
                    android.os.Message r0 = android.os.Message.obtain(r11, r0, r4)
                    r11.sendMessage(r0)
                    goto L69
                L4b:
                    mtk$d r0 = r2
                    r0.e = r11
                    mtk$d r11 = r2
                    mtk$e r11 = r11.b
                    mrx r11 = r11.g
                    java.lang.String r0 = r12.a()
                    r11.a(r0)
                    android.os.Handler r11 = r3
                    r0 = 1006(0x3ee, float:1.41E-42)
                    mtk$d r4 = r2
                    android.os.Message r0 = android.os.Message.obtain(r11, r0, r4)
                    r11.sendMessage(r0)
                L69:
                    r11 = r3
                L6a:
                    if (r11 == 0) goto L9b
                    java.lang.String r4 = "updateDescrption file fail("
                    int r12 = r12.i()
                    java.lang.String r5 = defpackage.mtk.e(r12)
                    java.lang.String r6 = "), mUuid="
                    mtk$d r12 = r2
                    java.lang.String r7 = r12.d
                    java.lang.String r8 = ", reason="
                    java.lang.Integer r9 = java.lang.Integer.valueOf(r11)
                    java.lang.Object[] r12 = new java.lang.Object[]{r4, r5, r6, r7, r8, r9}
                    health.compact.a.ReleaseLogUtil.b(r2, r12)
                    mtk$d r12 = r2
                    r12.e = r1
                    android.os.Handler r12 = r3
                    r0 = 1005(0x3ed, float:1.408E-42)
                    mtk$d r1 = r2
                    android.os.Message r11 = android.os.Message.obtain(r12, r0, r11, r3, r1)
                    r12.sendMessage(r11)
                L9b:
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: defpackage.mtk.AnonymousClass1.onPullingChange(msq, mso):void");
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void coz_(Handler handler, d dVar, boolean z, int i) {
        if (dVar.b.o.isEmpty()) {
            return;
        }
        if (!z) {
            c(dVar, i);
        } else {
            dVar.b.c++;
            dVar.b.k += dVar.f15167a;
        }
        if (dVar.b.c == dVar.b.o.size()) {
            LogUtil.c("Login_DownloadPluginHelper", "collectDescrptions success, mTotalSize=", Integer.valueOf(dVar.b.k));
            if (dVar.b.o.isEmpty()) {
                coy_(handler, dVar.b, 0);
                return;
            }
            dVar.d = dVar.b.o.get(0);
            dVar.f15167a = 0;
            coG_(handler, dVar);
        }
    }

    private void coG_(final Handler handler, final d dVar) {
        if (dVar.b.j == 2) {
            coy_(handler, dVar.b, 1);
            return;
        }
        handler.removeMessages(1101);
        handler.sendMessageDelayed(Message.obtain(handler, 1101, dVar.b), dVar.b.i);
        dVar.b.g.e(dVar.d, new PullListener() { // from class: mtk.2
            /* JADX WARN: Removed duplicated region for block: B:10:? A[RETURN, SYNTHETIC] */
            /* JADX WARN: Removed duplicated region for block: B:7:0x0074  */
            @Override // com.huawei.pluginmgr.filedownload.PullListener
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public void onPullingChange(defpackage.msq r11, defpackage.mso r12) {
                /*
                    r10 = this;
                    int r0 = r12.i()
                    r1 = 0
                    java.lang.String r2 = "Login_DownloadPluginHelper"
                    r3 = 0
                    if (r0 == 0) goto L4b
                    r11 = 1
                    if (r0 == r11) goto L16
                    int r11 = r12.i()
                    int r11 = defpackage.mtk.d(r11)
                    goto L72
                L16:
                    mtk$d r11 = r2
                    java.lang.String r11 = r11.d
                    java.lang.String r0 = "updatePlugin file success, mUuid="
                    java.lang.Object[] r11 = new java.lang.Object[]{r0, r11}
                    health.compact.a.LogUtil.c(r2, r11)
                    mtk$d r11 = r2
                    r11.e = r1
                    mtk$d r11 = r2
                    int r0 = r12.j()
                    r11.f15167a = r0
                    mtk$d r11 = r2
                    mtk$e r11 = r11.b
                    mrx r11 = r11.g
                    java.lang.String r0 = r12.a()
                    r11.a(r0)
                    android.os.Handler r11 = r3
                    r0 = 1007(0x3ef, float:1.411E-42)
                    mtk$d r4 = r2
                    android.os.Message r0 = android.os.Message.obtain(r11, r0, r4)
                    r11.sendMessage(r0)
                    goto L71
                L4b:
                    mtk$d r0 = r2
                    r0.e = r11
                    mtk$d r11 = r2
                    int r0 = r12.b()
                    r11.f15167a = r0
                    mtk$d r11 = r2
                    mtk$e r11 = r11.b
                    mrx r11 = r11.g
                    java.lang.String r0 = r12.a()
                    r11.a(r0)
                    android.os.Handler r11 = r3
                    r0 = 1009(0x3f1, float:1.414E-42)
                    mtk$d r4 = r2
                    android.os.Message r0 = android.os.Message.obtain(r11, r0, r4)
                    r11.sendMessage(r0)
                L71:
                    r11 = r3
                L72:
                    if (r11 == 0) goto La3
                    java.lang.String r4 = "updatePlugin file fail("
                    int r12 = r12.i()
                    java.lang.String r5 = defpackage.mtk.e(r12)
                    java.lang.String r6 = "), mUuid="
                    mtk$d r12 = r2
                    java.lang.String r7 = r12.d
                    java.lang.String r8 = ", reason="
                    java.lang.Integer r9 = java.lang.Integer.valueOf(r11)
                    java.lang.Object[] r12 = new java.lang.Object[]{r4, r5, r6, r7, r8, r9}
                    health.compact.a.ReleaseLogUtil.b(r2, r12)
                    mtk$d r12 = r2
                    r12.e = r1
                    android.os.Handler r12 = r3
                    r0 = 1008(0x3f0, float:1.413E-42)
                    mtk$d r1 = r2
                    android.os.Message r11 = android.os.Message.obtain(r12, r0, r11, r3, r1)
                    r12.sendMessage(r11)
                La3:
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: defpackage.mtk.AnonymousClass2.onPullingChange(msq, mso):void");
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void coA_(Handler handler, d dVar, boolean z, int i) {
        boolean c2 = c(dVar, i);
        if (!z && !c2) {
            coy_(handler, dVar.b, i);
            return;
        }
        dVar.b.d += dVar.f15167a;
        if (dVar.b.o.isEmpty()) {
            coy_(handler, dVar.b, 0);
            return;
        }
        dVar.d = dVar.b.o.get(0);
        dVar.f15167a = 0;
        coG_(handler, dVar);
    }

    private boolean b(e eVar) {
        return eVar.h != -1;
    }

    private void coB_(Handler handler, e eVar) {
        handler.sendMessageDelayed(Message.obtain(handler, 1101, eVar), eVar.i);
        eVar.h = 0;
        if (eVar.f15168a != null) {
            eVar.f15168a.startDownloadProgress(eVar.f);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(d dVar) {
        if (dVar.b.k <= 0) {
            LogUtil.e("Login_DownloadPluginHelper", "mTotalSize error, mTotalSize=", Integer.valueOf(dVar.b.k));
            return;
        }
        int i = dVar.f15167a + dVar.b.d;
        int i2 = (int) ((i * 100) / dVar.b.k);
        if (dVar.b.h >= i2) {
            return;
        }
        dVar.b.h = i2;
        if (dVar.b.f15168a != null) {
            dVar.b.f15168a.showDownloadProgress(dVar.b.f, i, i2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void coy_(Handler handler, e eVar, int i) {
        handler.removeMessages(1101);
        if (i == 2 && eVar.b) {
            eVar.n += eVar.i;
            LogUtil.c("Login_DownloadPluginHelper", "mTaskNo=", Integer.valueOf(eVar.f), ", mLastProgress=", Integer.valueOf(eVar.h), ", mTimeout=", Integer.valueOf(eVar.i), ", mTotalCheckTimeout=", Integer.valueOf(eVar.n));
            if (eVar.n < 120000) {
                handler.sendMessageDelayed(Message.obtain(handler, 1101, eVar), eVar.i);
                return;
            }
        }
        d(eVar, i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void d(e eVar) {
        if (eVar.j == 1) {
            eVar.j = 2;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void a(d dVar) {
        msq msqVar;
        if (dVar.b.j != 2 || (msqVar = dVar.e) == null) {
            return;
        }
        msj.a().b(msqVar);
    }

    private void d(e eVar, int i) {
        if (eVar.o.isEmpty()) {
            b(eVar, i);
            return;
        }
        d dVar = new d();
        for (String str : (String[]) eVar.o.toArray(new String[eVar.o.size()])) {
            dVar.b = eVar;
            dVar.d = str;
            c(dVar, i);
        }
    }

    private boolean c(d dVar, int i) {
        int i2 = (i != 0 || e(dVar.d) == null) ? -1 : 0;
        e eVar = dVar.b;
        if (eVar.o.remove(dVar.d)) {
            if (i2 == 0) {
                eVar.l++;
            }
            r0 = eVar.f15168a != null ? eVar.f15168a.onDownloadPluginResult(eVar.f, dVar.d, i2) : false;
            b(eVar, i);
        }
        return r0;
    }

    private void b(e eVar, int i) {
        if (eVar.o.isEmpty()) {
            g(eVar.f);
            eVar.j = 0;
            if (eVar.f15168a == null) {
                return;
            }
            if (eVar.m > 0 && eVar.m == eVar.l) {
                i = 0;
            } else if (i == 0 && eVar.l > 0) {
                i = 5;
            }
            eVar.f15168a.onDownloadResult(eVar.f, i);
            eVar.f15168a = null;
        }
    }

    private File e(String str) {
        String b2 = this.e.b(str, true);
        if (b2 == null) {
            return null;
        }
        File file = new File(b2);
        if (file.exists()) {
            synchronized (this.c) {
                this.c.put(str, file);
            }
            LogUtil.c("Login_DownloadPluginHelper", file.getName(), " is exists.");
            return file;
        }
        LogUtil.c("Login_DownloadPluginHelper", file.getName(), " is not exists.");
        return null;
    }

    /* loaded from: classes6.dex */
    class c extends Handler {
        c(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            if (message.obj instanceof d) {
                a((d) message.obj, message.what, message.arg1);
            } else if (message.obj instanceof e) {
                c((e) message.obj, message.what, message.arg1);
            }
        }

        private void a(d dVar, int i, int i2) {
            switch (i) {
                case 1003:
                    mtk.a(dVar);
                    break;
                case 1004:
                    mtk.this.coz_(this, dVar, true, 0);
                    break;
                case 1005:
                    mtk.this.coz_(this, dVar, false, i2);
                    break;
                case 1006:
                    mtk.a(dVar);
                    break;
                case 1007:
                    mtk.this.coA_(this, dVar, true, 0);
                    break;
                case 1008:
                    mtk.this.coA_(this, dVar, false, i2);
                    break;
                case 1009:
                    mtk.this.b(dVar);
                    mtk.a(dVar);
                    break;
                default:
                    LogUtil.a("Login_DownloadPluginHelper", "handleTaskInfoMessage, what=", Integer.valueOf(i), "reason=", Integer.valueOf(i2));
                    break;
            }
        }

        private void c(e eVar, int i, int i2) {
            if (i == 1101) {
                mtk.this.coy_(this, eVar, 2);
                return;
            }
            if (i != 1102) {
                switch (i) {
                    case 1000:
                        mtk.this.coF_(this, eVar);
                        break;
                    case 1001:
                        mtk.this.coD_(this, eVar, true, 0);
                        break;
                    case 1002:
                        mtk.this.coD_(this, eVar, false, i2);
                        break;
                    default:
                        LogUtil.a("Login_DownloadPluginHelper", "handleTaskInfosMessages, what=", Integer.valueOf(i), "reason=", Integer.valueOf(i2));
                        break;
                }
                return;
            }
            mtk.d(eVar);
        }
    }

    private int c(e eVar) {
        boolean isEmpty;
        eVar.f = b();
        synchronized (this.d) {
            isEmpty = this.d.isEmpty();
            this.d.put(Integer.valueOf(eVar.f), eVar);
        }
        if (isEmpty) {
            eVar.g = this.e;
            if (eVar.g.a().needDescriptionFile()) {
                eVar.g.h();
            }
        } else {
            eVar.g = new mrx(c());
        }
        return eVar.f;
    }

    private e g(int i) {
        e remove;
        synchronized (this.d) {
            remove = this.d.remove(Integer.valueOf(i));
        }
        return remove;
    }

    private static int b() {
        int i;
        synchronized (mtk.class) {
            i = b + 1;
            b = i;
        }
        return i;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String a(int i) {
        switch (i) {
            case -11:
                return "PULL_NOT_MODIFIED";
            case -10:
                return "PULL_CANCEL";
            case -9:
                return "PULL_STORAGE_NOT_ENOUGH";
            case -8:
                return "PULL_TASK_IS_PENDING";
            case -7:
                return "PULL_NO_SUCH_TASK";
            case -6:
                return "PULL_DECOMPRESS_FAIL";
            case -5:
                return "PULL_NO_SUCH_FILE";
            case -4:
                return "PULL_NETWORK_ERROR";
            case -3:
                return "PULL_TIMEOUT";
            case -2:
                return "PULL_VERIFICATION_FAILED";
            case -1:
                return "PULL_UNEXPECTED_INTERRUPTION";
            default:
                return "status:" + i;
        }
    }
}
