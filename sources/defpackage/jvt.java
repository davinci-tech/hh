package defpackage;

import com.huawei.hwlogsmodel.LogUtil;
import java.util.Arrays;

/* loaded from: classes5.dex */
public class jvt {

    /* renamed from: a, reason: collision with root package name */
    private static jvt f14139a;
    private static final Object d = new Object();
    private kbc b = null;

    private jvt() {
    }

    public static jvt a() {
        jvt jvtVar;
        synchronized (d) {
            if (f14139a == null) {
                f14139a = new jvt();
            }
            jvtVar = f14139a;
        }
        return jvtVar;
    }

    public boolean b(kbc kbcVar) {
        synchronized (this) {
            if (kbcVar != null) {
                if (kbcVar.e() != null) {
                    kbc kbcVar2 = this.b;
                    if (kbcVar2 == null) {
                        this.b = kbcVar;
                        return true;
                    }
                    if (kbcVar2.d() == kbcVar.d() && Arrays.equals(kbcVar.e(), this.b.e())) {
                        LogUtil.h("CheckFileDataUtil", "report is repeat.");
                        this.b = null;
                        return false;
                    }
                    this.b = kbcVar;
                    return true;
                }
            }
            LogUtil.h("CheckFileDataUtil", "report is empty.");
            return false;
        }
    }
}
