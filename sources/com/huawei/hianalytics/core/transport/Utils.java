package com.huawei.hianalytics.core.transport;

import com.huawei.hianalytics.core.log.HiLog;
import com.huawei.hianalytics.core.transport.net.Response;
import java.io.IOException;
import java.net.ConnectException;
import java.net.UnknownHostException;
import javax.net.ssl.SSLHandshakeException;
import javax.net.ssl.SSLPeerUnverifiedException;

/* loaded from: classes4.dex */
public class Utils {
    public static final String TAG = "Utils";

    public static long parseStringToLong(String str) {
        try {
            return Long.parseLong(str);
        } catch (NumberFormatException unused) {
            return 0L;
        }
    }

    public static Response handlerException(Exception exc) {
        String str;
        if (exc instanceof SecurityException) {
            HiLog.e(TAG, HiLog.ErrorCode.NE003);
            return new Response(-101, "");
        }
        if (exc instanceof SSLPeerUnverifiedException) {
            HiLog.e(TAG, HiLog.ErrorCode.NE002);
            return new Response(Response.Code.SSL_VALIDATION_ERROR, "");
        }
        if (exc instanceof SSLHandshakeException) {
            HiLog.e(TAG, HiLog.ErrorCode.NE002);
            return new Response(Response.Code.SSL_VALIDATION_ERROR, "");
        }
        if (exc instanceof ConnectException) {
            HiLog.e(TAG, HiLog.ErrorCode.NE005);
            return new Response(Response.Code.CONNECTION_ERROR, "");
        }
        if (exc instanceof UnknownHostException) {
            HiLog.e(TAG, HiLog.ErrorCode.NE006);
            return new Response(Response.Code.HOST_ERROR, "");
        }
        if (exc instanceof IOException) {
            str = HiLog.ErrorCode.NE004;
        } else {
            str = "other Exception:" + exc.getMessage();
        }
        HiLog.e(TAG, str);
        return new Response(-102, "");
    }
}
