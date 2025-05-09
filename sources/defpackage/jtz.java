package defpackage;

import health.compact.a.util.LogUtil;
import java.nio.ByteBuffer;

/* loaded from: classes5.dex */
public class jtz {
    public static byte[] b(byte[] bArr, jug jugVar) {
        byte[] h = jugVar.h();
        if (h == null || h.length == 0) {
            LogUtil.c("BtProxyCommandUtil", "responseData is null");
            return bArr;
        }
        byte[] f = cvx.f(9);
        byte[] c = cvx.c(h.length);
        ByteBuffer allocate = ByteBuffer.allocate(bArr.length + f.length + c.length + h.length);
        allocate.put(bArr).put(f).put(c).put(h);
        return allocate.array();
    }
}
