package com.amap.api.col.p0003sl;

import android.content.Context;
import android.os.Handler;
import android.text.TextUtils;
import com.amap.api.col.p0003sl.iw;
import com.huawei.hms.framework.common.ExceptionCode;
import com.huawei.openalliance.ad.constant.VastAttribute;
import com.huawei.openalliance.ad.constant.VideoPlayFlag;
import com.huawei.operation.utils.CloudParamKeys;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes2.dex */
public final class ik {

    /* renamed from: a, reason: collision with root package name */
    private Context f1181a;
    private hz b;
    private boolean c = true;
    private boolean d = false;
    private boolean e = true;
    private boolean f = false;
    private List<String> g = new ArrayList();
    private im h = new im((byte) 0);
    private im i = new im();
    private iw.a j = new iw.a() { // from class: com.amap.api.col.3sl.ik.1
        @Override // com.amap.api.col.3sl.iw.a
        public final void a(int i) {
            if (i > 0 && ik.a(ik.this) != null) {
                ((il) ik.this.c().f).a(i);
                ik.a(ik.this, VastAttribute.ERROR, String.valueOf(((il) ik.this.c().f).b()));
                ik.a(ik.this).postDelayed(new Runnable() { // from class: com.amap.api.col.3sl.ik.1.1
                    @Override // java.lang.Runnable
                    public final void run() {
                        ik.this.c(false);
                    }
                }, 660000L);
            }
        }
    };
    private iw.a k = new iw.a() { // from class: com.amap.api.col.3sl.ik.2
        @Override // com.amap.api.col.3sl.iw.a
        public final void a(int i) {
            if (i <= 0) {
                return;
            }
            ((il) ik.this.e().f).a(i);
            ik.a(ik.this, CloudParamKeys.INFO, String.valueOf(((il) ik.this.e().f).b()));
            if (ik.a(ik.this) == null) {
                return;
            }
            ik.a(ik.this).postDelayed(new Runnable() { // from class: com.amap.api.col.3sl.ik.2.1
                @Override // java.lang.Runnable
                public final void run() {
                    ik.this.d(false);
                }
            }, 660000L);
        }
    };
    private Handler l = null;
    private kd m = null;
    private kd n = null;

    static final class a {

        /* renamed from: a, reason: collision with root package name */
        public static Map<String, ik> f1186a = new HashMap();
    }

    public static ik a(hz hzVar) {
        if (hzVar == null || TextUtils.isEmpty(hzVar.a())) {
            return null;
        }
        if (a.f1186a.get(hzVar.a()) == null) {
            a.f1186a.put(hzVar.a(), new ik(hzVar));
        }
        return a.f1186a.get(hzVar.a());
    }

    private ik(hz hzVar) {
        this.b = hzVar;
    }

    public final void a(Context context) {
        this.f1181a = context.getApplicationContext();
    }

    public final void a(boolean z, boolean z2, boolean z3, boolean z4, List<String> list) {
        this.c = z;
        this.d = z2;
        this.e = z3;
        this.f = z4;
        this.g = list;
        d();
        f();
    }

    public final void a(boolean z) {
        if (b()) {
            b(z);
        }
    }

    public final void a(ij ijVar) {
        if (b() && this.c && ij.a(ijVar) && ijVar != null) {
            List<String> list = this.g;
            if (list != null && list.size() != 0) {
                for (int i = 0; i < this.g.size(); i++) {
                    if (!TextUtils.isEmpty(this.g.get(i)) && ijVar.b().contains(this.g.get(i))) {
                        return;
                    }
                }
            }
            if (this.e || ijVar.a() != ij.f1180a) {
                im b = b(ijVar.a());
                if (b.a(ijVar.b())) {
                    String a2 = ij.a(b.a());
                    if (this.f1181a == null || TextUtils.isEmpty(a2) || "[]".equals(a2)) {
                        return;
                    }
                    iw.a(this.f1181a, this.b, ijVar.c(), c(ijVar.a()), a2);
                    b(false);
                    b.b();
                }
                b.a(ijVar);
            }
        }
    }

    public final void a() {
        if (b()) {
            a(ij.b);
            a(ij.f1180a);
        }
    }

    private void a(int i) {
        Context context;
        im b = b(i);
        String a2 = ij.a(b.a());
        if (TextUtils.isEmpty(a2) || "[]".equals(a2) || (context = this.f1181a) == null) {
            return;
        }
        iw.a(context, this.b, ij.a(i), c(i), a2);
        b.b();
    }

    private boolean b() {
        return this.f1181a != null;
    }

    private im b(int i) {
        if (i == ij.b) {
            return this.i;
        }
        return this.h;
    }

    private void b(boolean z) {
        c(z);
        d(z);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(boolean z) {
        kd c = c(ij.b);
        if (z) {
            ((il) c.f).a(z);
        }
        Context context = this.f1181a;
        if (context == null) {
            return;
        }
        iw.a(context, c, this.j);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(boolean z) {
        kd c = c(ij.f1180a);
        if (z) {
            ((il) c.f).a(z);
        }
        Context context = this.f1181a;
        if (context == null) {
            return;
        }
        iw.a(context, c, this.k);
    }

    private kd c(int i) {
        if (i == ij.b) {
            if (this.n == null) {
                this.n = c();
            }
            return this.n;
        }
        if (this.m == null) {
            this.m = e();
        }
        return this.m;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public kd c() {
        kd kdVar = this.n;
        if (kdVar != null) {
            return kdVar;
        }
        d();
        return this.n;
    }

    private kd d() {
        if (this.f1181a == null) {
            return null;
        }
        kd kdVar = new kd();
        this.n = kdVar;
        kdVar.f1251a = h();
        this.n.b = 512000000L;
        this.n.d = 12500;
        this.n.c = "1";
        this.n.h = -1;
        this.n.i = "elkey";
        long a2 = a(VastAttribute.ERROR);
        this.n.f = new il(true, new ky(this.f1181a, this.d), a2, ExceptionCode.CRASH_EXCEPTION);
        this.n.g = null;
        return this.n;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public kd e() {
        kd kdVar = this.m;
        if (kdVar != null) {
            return kdVar;
        }
        f();
        return this.m;
    }

    private long a(String str) {
        try {
            String format = new SimpleDateFormat("yyyyMMdd").format(new Date());
            return Long.parseLong(in.a(this.b).a(this.f1181a, "", "", format + str));
        } catch (Throwable unused) {
            return 0L;
        }
    }

    private kd f() {
        if (this.f1181a == null) {
            return null;
        }
        kd kdVar = new kd();
        this.m = kdVar;
        kdVar.f1251a = g();
        this.m.b = 512000000L;
        this.m.d = 12500;
        this.m.c = "1";
        this.m.h = -1;
        this.m.i = "inlkey";
        long a2 = a(CloudParamKeys.INFO);
        this.m.f = new il(this.f, new ky(this.f1181a, this.d), a2, 30000000);
        this.m.g = null;
        return this.m;
    }

    private String g() {
        Context context = this.f1181a;
        if (context == null) {
            return null;
        }
        return a(context, "CAF9B6B99962BF5C2264824231D7A40C", this.b);
    }

    private String h() {
        Context context = this.f1181a;
        if (context == null) {
            return null;
        }
        return a(context, "CB5E100E5A9A3E7F6D1FD97512215282", this.b);
    }

    private static String a(Context context, String str, hz hzVar) {
        String b;
        if (context == null) {
            return null;
        }
        if (hzVar != null) {
            try {
                if (!TextUtils.isEmpty(hzVar.a())) {
                    b = hv.b(hzVar.a());
                    return context.getFilesDir().getAbsolutePath() + File.separator + "EBDEC84EF205FEA2DF0719DEB822869E" + File.separator + str + File.separator + b;
                }
            } catch (Throwable unused) {
                return null;
            }
        }
        b = VideoPlayFlag.PLAY_IN_ALL;
        return context.getFilesDir().getAbsolutePath() + File.separator + "EBDEC84EF205FEA2DF0719DEB822869E" + File.separator + str + File.separator + b;
    }

    static /* synthetic */ Handler a(ik ikVar) {
        Context context = ikVar.f1181a;
        if (context == null || context == null) {
            return null;
        }
        if (ikVar.l == null) {
            ikVar.l = new Handler(ikVar.f1181a.getMainLooper());
        }
        return ikVar.l;
    }

    static /* synthetic */ void a(ik ikVar, String str, String str2) {
        try {
            String format = new SimpleDateFormat("yyyyMMdd").format(new Date());
            in.a(ikVar.b).a(ikVar.f1181a, "", "", format + str, str2);
        } catch (Throwable unused) {
        }
    }
}
