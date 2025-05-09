package com.amap.api.col.p0003sl;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.UnknownHostException;

/* loaded from: classes2.dex */
public final class ms {
    public static void a(Throwable th) {
        if (mh.f1335a) {
            b(th);
        }
    }

    private static String b(Throwable th) {
        if (th == null) {
            return "";
        }
        for (Throwable th2 = th; th2 != null; th2 = th2.getCause()) {
            if (th2 instanceof UnknownHostException) {
                return "";
            }
        }
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        th.printStackTrace(printWriter);
        printWriter.flush();
        return stringWriter.toString();
    }
}
