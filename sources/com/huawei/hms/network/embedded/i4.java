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
public class i4 {
    public static final int CODE_EXCEPTION_404 = 8;
    public static final int CODE_EXCEPTION_IP_UNREACHABLE = 3;
    public static final int CODE_EXCEPTION_NO_ROUTE_TO_HOST = 6;
    public static final int CODE_EXCEPTION_PORT_UNREACHABLE = 2;
    public static final int CODE_EXCEPTION_PROTOCOL = 9;
    public static final int CODE_EXCEPTION_SOCKET_TIMEOUT = 4;
    public static final int CODE_EXCEPTION_SSL = 10;
    public static final int CODE_EXCEPTION_SSL_HANDSHAKE = 14;
    public static final int CODE_EXCEPTION_SSL_KEY = 11;
    public static final int CODE_EXCEPTION_SSL_PEER_UNVERIFIED = 12;
    public static final int CODE_EXCEPTION_SSL_PROTOCOL = 13;
    public static final int CODE_EXCEPTION_UNKNOWN_HOST = 5;
    public static final int CODE_EXCEPTION_UNKNOWN_SERVICE = 7;
    public static final int CODE_OK = 0;

    /* renamed from: a, reason: collision with root package name */
    public static final String f5303a = "ErrorCodeUtil";

    public static int getErrorCode(int i) {
        return i == 404 ? 8 : 0;
    }

    public static int getErrorCodeFromException(Exception exc) {
        try {
            if (exc instanceof q9) {
                return ExceptionCode.SHUTDOWN_EXCEPTION;
            }
        } catch (NoClassDefFoundError | NoSuchMethodError e) {
            Logger.w("ErrorCodeUtil", "class connectionShutdownException is not found", e.getClass().getSimpleName());
        }
        if (h1.apiAvailable(4)) {
            if (exc instanceof NetworkCanceledException) {
                return ExceptionCode.CANCEL;
            }
            if (exc instanceof NetworkTimeoutException) {
                return ExceptionCode.NETWORK_TIMEOUT;
            }
            if (exc instanceof NetworkUnsupportedException) {
                return ExceptionCode.NETWORK_UNSUPPORTED;
            }
            if (exc instanceof NetworkInternalException) {
                return ExceptionCode.CRASH_EXCEPTION;
            }
        }
        return ExceptionCode.getErrorCodeFromException(exc);
    }

    public static int getErrorCode(IOException iOException) {
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
        return iOException instanceof SSLException ? 10 : 0;
    }
}
