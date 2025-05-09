package defpackage;

import com.huawei.hwlogsmodel.LogUtil;
import java.nio.ByteBuffer;

/* loaded from: classes3.dex */
public class cfr {
    public byte[] a(String str, String str2) {
        String c = cvx.c(str);
        String str3 = cvx.e(1) + cvx.e(c.length() / 2) + c;
        String c2 = cvx.c(str2);
        String str4 = str3 + (cvx.e(2) + cvx.e(c2.length() / 2) + c2);
        cfs cfsVar = new cfs();
        cfsVar.a(44);
        cfsVar.c(8);
        cfsVar.c(cvx.a(str4));
        cfsVar.d(cvx.a(str4).length);
        return d(cfsVar);
    }

    public byte[] b(int i, int i2) {
        String str = (cvx.e(1) + cvx.e(1) + cvx.e(i)) + (cvx.e(2) + cvx.e(1) + cvx.e(i2));
        cfs cfsVar = new cfs();
        cfsVar.a(44);
        cfsVar.c(2);
        cfsVar.c(cvx.a(str));
        cfsVar.d(cvx.a(str).length);
        return d(cfsVar);
    }

    public byte[] b(int i) {
        String str = (cvx.e(1) + cvx.e(1) + cvx.e(i)) + (cvx.e(2) + cvx.e(0)) + (cvx.e(3) + cvx.e(0)) + (cvx.e(4) + cvx.e(0)) + (cvx.e(5) + cvx.e(0));
        cfs cfsVar = new cfs();
        cfsVar.a(44);
        cfsVar.c(3);
        cfsVar.c(cvx.a(str));
        cfsVar.d(cvx.a(str).length);
        return d(cfsVar);
    }

    public byte[] d(int i, int i2, int i3) {
        String str = (cvx.e(1) + cvx.e(1) + cvx.e(i)) + (cvx.e(2) + cvx.e(4) + cvx.b(i2)) + (cvx.e(3) + cvx.e(4) + cvx.b(i3));
        cfs cfsVar = new cfs();
        cfsVar.a(44);
        cfsVar.c(4);
        cfsVar.c(cvx.a(str));
        cfsVar.d(cvx.a(str).length);
        return d(cfsVar);
    }

    public byte[] a(int i, int i2) {
        String str = (cvx.e(1) + cvx.e(1) + cvx.e(i)) + (cvx.e(2) + cvx.e(1) + cvx.e(i2));
        cfs cfsVar = new cfs();
        cfsVar.a(44);
        cfsVar.c(6);
        cfsVar.c(cvx.a(str));
        cfsVar.d(cvx.a(str).length);
        return d(cfsVar);
    }

    private static byte[] d(cfs cfsVar) {
        ByteBuffer allocate = ByteBuffer.allocate(cfsVar.a() + 2);
        allocate.put(cvx.a(cvx.e(cfsVar.e())));
        allocate.put(cvx.a(cvx.e(cfsVar.d())));
        LogUtil.a("WspFileDataParser", "getCommandBytes, deviceCommand:", cfsVar.toString());
        if (cfsVar.c() != null) {
            allocate.put(cfsVar.c());
            LogUtil.a("WspFileDataParser", "command data", cvx.d(cfsVar.c()));
        } else {
            LogUtil.h("WspFileDataParser", "command data is null");
        }
        allocate.flip();
        return allocate.array();
    }
}
