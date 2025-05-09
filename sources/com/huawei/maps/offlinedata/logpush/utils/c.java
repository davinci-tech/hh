package com.huawei.maps.offlinedata.logpush.utils;

import com.huawei.maps.offlinedata.utils.g;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

/* loaded from: classes5.dex */
public class c {
    public static String a(Throwable th) {
        if (th == null) {
            return "";
        }
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        try {
            th.printStackTrace(printWriter);
            for (Throwable cause = th.getCause(); cause != null; cause = cause.getCause()) {
                cause.printStackTrace(printWriter);
            }
            String obj = stringWriter.toString();
            byte[] bytes = obj.getBytes(Charset.defaultCharset());
            int length = bytes.length;
            g.b("LogPushUtils", "logLength is " + length);
            if (length > 10240) {
                obj = new String(Arrays.copyOfRange(bytes, 0, 10240), StandardCharsets.UTF_8);
            }
            return obj;
        } finally {
            printWriter.close();
            try {
                stringWriter.close();
            } catch (IOException unused) {
                g.d("LogPushUtils", "close stringWriter IOException");
            }
        }
    }
}
