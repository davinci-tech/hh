package defpackage;

import com.huawei.health.messagecenter.model.CommonUtil;
import com.huawei.hwlogsmodel.LogUtil;

/* loaded from: classes7.dex */
public class iju {
    private ihg d;

    private iju() {
        this.d = ihg.b();
    }

    static class d {

        /* renamed from: a, reason: collision with root package name */
        private static final iju f13400a = new iju();
    }

    public static iju c() {
        return d.f13400a;
    }

    public boolean c(igs igsVar) {
        return e(igsVar.a()) ? e(igsVar) > 0 : b(igsVar) > 0;
    }

    public int c(String str) {
        return iih.bAH_(this.d.query(a("permission"), b(str), null, null, null), "_id");
    }

    private long b(igs igsVar) {
        LogUtil.c("Debug_AuthorizationManager", "insertPermission()");
        return this.d.insert(iid.bzt_(igsVar));
    }

    private int e(igs igsVar) {
        return this.d.update(iid.bzt_(igsVar), a("permission"), b(igsVar.a()));
    }

    private boolean e(String str) {
        return iih.bAv_(this.d.query(a("permission"), b(str), null, null, null));
    }

    private String a(String str) {
        return str + CommonUtil.EQUAL;
    }

    private String[] b(String str) {
        return new String[]{str};
    }
}
