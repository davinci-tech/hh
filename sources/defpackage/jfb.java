package defpackage;

import android.content.Context;
import com.huawei.hwbasemgr.HwBaseManager;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import java.util.ArrayList;

/* loaded from: classes5.dex */
public class jfb extends HwBaseManager {
    private static jfb d;

    private jfb(Context context) {
        super(context);
        new jey().b(this);
    }

    public static jfb e() {
        jfb jfbVar;
        synchronized (jfb.class) {
            LogUtil.c("MigrateDbManager", "Enter getInstance()");
            if (d == null) {
                d = new jfb(BaseApplication.getContext());
            }
            jfbVar = d;
        }
        return jfbVar;
    }

    public long e(jfd jfdVar) {
        jey jeyVar = new jey();
        if (jfdVar == null || jeyVar.d(this, jfdVar.d(), jfdVar.b())) {
            return 0L;
        }
        return jeyVar.c(this, jfdVar);
    }

    public int a() {
        return new jey().c(this);
    }

    public ArrayList<jfd> d(String str) {
        return new jey().b(this, str);
    }

    public int d(String str, String str2) {
        return new jey().e(this, str, str2);
    }

    public int a(String str, String str2) {
        return new jey().b(this, str, str2);
    }

    public int e(String str, String str2) {
        return new jey().a(this, str, str2);
    }

    public int b(String str, String str2) {
        return new jey().c(this, str, str2);
    }

    public int c(String str, String str2) {
        return new jey().j(this, str, str2);
    }

    @Override // com.huawei.hwbasemgr.HwBaseManager
    public Integer getModuleId() {
        return 20008;
    }
}
