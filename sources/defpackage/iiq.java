package defpackage;

import com.huawei.hwlogsmodel.LogUtil;

/* loaded from: classes7.dex */
public class iiq {
    private igx b;

    private iiq() {
        this.b = igx.d();
    }

    static class b {
        private static final iiq b = new iiq();
    }

    public static iiq d() {
        return b.b;
    }

    public boolean e(igr igrVar) {
        return c(igrVar.a(), igrVar.f(), igrVar.e()) ? a(igrVar) > 0 : c(igrVar) > 0;
    }

    private long c(igr igrVar) {
        LogUtil.c("Debug_AuthorizationManager", "insertAuthorization()");
        long currentTimeMillis = System.currentTimeMillis();
        igrVar.d(currentTimeMillis);
        igrVar.b(currentTimeMillis);
        return this.b.insert(iid.bzj_(igrVar));
    }

    private int a(igr igrVar) {
        igrVar.b(System.currentTimeMillis());
        return this.b.update(iid.bzj_(igrVar), e(), a(igrVar.a(), igrVar.f(), igrVar.e()));
    }

    private boolean c(int i, int i2, int i3) {
        return iih.bAv_(this.b.query(e(), a(i, i2, i3), null, null, null));
    }

    private String[] a(int i, int i2, int i3) {
        return new String[]{Integer.toString(i), Integer.toString(i2), Integer.toString(i3)};
    }

    private String e() {
        return "app_id =? and user_id =? and permission_id =? ";
    }
}
