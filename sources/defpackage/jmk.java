package defpackage;

import com.huawei.hwdevice.mainprocess.mgr.hwwatchfacemgr.touchtransfer.initese.oma.SmartCardCallback;
import com.huawei.hwlogsmodel.LogUtil;

/* loaded from: classes5.dex */
public class jmk {
    private jml b;
    private jmm c;
    private SmartCardCallback d;
    private int e = -1;

    public void e(int i, jml jmlVar) {
        this.e = i;
        if (jmlVar == null) {
            c(i, "SmartCardBean must not allow to null.");
        } else if (jmlVar.d() == null) {
            c(this.e, "the aid must not allow to null");
        } else {
            this.b = jmlVar;
            a();
        }
    }

    private void a() {
        if (this.c == null) {
            LogUtil.h("SmartCard", "new SmartCardRequest");
            this.c = new jmm();
        }
        this.c.a(this.b);
        this.c.e(this.d);
        this.c.a(this.e);
        this.c.run();
    }

    public jmk d(SmartCardCallback smartCardCallback) {
        this.d = smartCardCallback;
        return this;
    }

    public void e() {
        if (this.c != null) {
            jnk.a().d();
            this.c = null;
        }
        this.d = null;
        this.b = null;
    }

    private void c(int i, String str) {
        if (this.d != null) {
            this.d.onOperatorFailure(i, new Error(str));
        }
    }
}
