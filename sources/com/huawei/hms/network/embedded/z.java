package com.huawei.hms.network.embedded;

import com.huawei.hms.framework.common.ExceptionCode;
import com.huawei.hms.framework.common.Logger;
import com.huawei.hms.network.exception.NetworkCanceledException;
import com.huawei.hms.network.exception.NetworkInternalException;
import com.huawei.hms.network.exception.NetworkTimeoutException;
import com.huawei.hms.network.exception.NetworkUnsupportedException;
import java.io.IOException;
import java.net.ConnectException;
import java.net.HttpRetryException;
import java.net.NoRouteToHostException;
import java.net.PortUnreachableException;
import java.net.ProtocolException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.net.UnknownServiceException;
import javax.net.ssl.SSLException;
import javax.net.ssl.SSLHandshakeException;
import javax.net.ssl.SSLKeyException;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.SSLProtocolException;

/* loaded from: classes9.dex */
public class z {

    /* renamed from: a, reason: collision with root package name */
    public static final String f5586a = "ErrorCodeUtil";
    public static final int b = 0;
    public static final int c = -1;
    public static final int d = 2;
    public static final int e = 3;
    public static final int f = 4;
    public static final int g = 5;
    public static final int h = 6;
    public static final int i = 7;
    public static final int j = 8;
    public static final int k = 9;
    public static final int l = 10;
    public static final int m = 11;
    public static final int n = 12;
    public static final int o = 13;
    public static final int p = 14;

    public static int a(int i2) {
        return i2 == 404 ? 8 : 0;
    }

    public static int a(Exception exc) {
        return exc instanceof NetworkCanceledException ? ExceptionCode.CANCEL : exc instanceof NetworkTimeoutException ? ExceptionCode.NETWORK_TIMEOUT : exc instanceof NetworkUnsupportedException ? ExceptionCode.NETWORK_UNSUPPORTED : exc instanceof NetworkInternalException ? ExceptionCode.CRASH_EXCEPTION : ExceptionCode.getErrorCodeFromException(exc);
    }

    public static int a(IOException iOException) {
        if (iOException instanceof PortUnreachableException) {
            return 2;
        }
        if ((iOException instanceof ConnectException) || (iOException instanceof HttpRetryException)) {
            return 3;
        }
        if (iOException instanceof SocketTimeoutException) {
            return 4;
        }
        if (iOException instanceof UnknownHostException) {
            return 5;
        }
        if (iOException instanceof NoRouteToHostException) {
            return 6;
        }
        if (iOException instanceof UnknownServiceException) {
            return 7;
        }
        if (iOException instanceof ProtocolException) {
            return 9;
        }
        if (iOException instanceof SSLKeyException) {
            return 11;
        }
        if (iOException instanceof SSLPeerUnverifiedException) {
            return 12;
        }
        if (iOException instanceof SSLProtocolException) {
            return 13;
        }
        if (iOException instanceof SSLHandshakeException) {
            return 14;
        }
        if (iOException instanceof SSLException) {
            return 10;
        }
        Logger.w("ErrorCodeUtil", "Request failed with %s", iOException.getClass().getName());
        return -1;
    }
}
