package defpackage;

import android.content.Context;
import com.huawei.health.device.ui.measure.service.ServiceForDatabaseManager;
import com.huawei.hwlogsmodel.LogUtil;
import java.util.List;

/* loaded from: classes3.dex */
public class coa {

    /* renamed from: a, reason: collision with root package name */
    private static Context f808a;
    private ServiceForDatabaseManager b;

    static class e {
        public static final coa e = new coa();
    }

    private coa() {
        this.b = new ServiceForDatabaseManager(f808a);
    }

    public static coa a(Context context) {
        LogUtil.a("OpenServiceController", "getInstance");
        f808a = context.getApplicationContext();
        return e.e;
    }

    public List<cnt> d() {
        return this.b.b();
    }
}
