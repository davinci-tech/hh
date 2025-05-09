package defpackage;

import android.location.Location;
import android.os.Bundle;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlocationmgr.model.IGpsStatusCallback;
import com.huawei.hwlocationmgr.model.ILoactionCallback;
import com.huawei.hwlocationmgr.model.IOriginalGpsStatusListener;
import java.util.List;

/* loaded from: classes5.dex */
public class ktg {

    /* renamed from: a, reason: collision with root package name */
    private static ktg f14583a;
    private static final Object e = new Object();
    private kto b;

    private ktg() {
        this.b = null;
        this.b = kto.a(BaseApplication.getContext());
    }

    public static ktg c() {
        ktg ktgVar;
        synchronized (e) {
            ktg ktgVar2 = f14583a;
            if (ktgVar2 == null) {
                f14583a = new ktg();
            } else {
                ktgVar2.b.b();
            }
            ktgVar = f14583a;
        }
        return ktgVar;
    }

    private void g() {
        if (this.b == null) {
            this.b = kto.a(BaseApplication.getContext());
        }
    }

    public boolean e() {
        g();
        return this.b.e();
    }

    public void b() {
        g();
        this.b.d();
    }

    public Location bQQ_(List<String> list) {
        if (this.b == null) {
            this.b = kto.a(BaseApplication.getContext());
        }
        return this.b.bRe_(list);
    }

    public Location bQR_() {
        if (this.b == null) {
            this.b = kto.a(BaseApplication.getContext());
        }
        return this.b.bRf_();
    }

    public void e(ILoactionCallback iLoactionCallback, String str) {
        g();
        this.b.a(iLoactionCallback, str);
    }

    public void a(String str) {
        g();
        this.b.d(str);
    }

    public void a(IGpsStatusCallback iGpsStatusCallback, String str) {
        g();
        this.b.c(iGpsStatusCallback, str);
    }

    public void d(String str) {
        g();
        this.b.e(str);
    }

    public void e(IOriginalGpsStatusListener iOriginalGpsStatusListener, String str) {
        g();
        this.b.a(iOriginalGpsStatusListener, str);
    }

    public void c(String str) {
        g();
        this.b.c(str);
    }

    public void b(boolean z) {
        g();
        this.b.c(z);
    }

    public int a() {
        g();
        return this.b.c();
    }

    public boolean bQS_(String str, String str2, Bundle bundle) {
        g();
        return this.b.bRg_(str, str2, bundle);
    }
}
