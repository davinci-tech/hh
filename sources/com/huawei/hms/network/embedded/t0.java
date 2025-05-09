package com.huawei.hms.network.embedded;

import com.huawei.hms.network.exception.NetworkCanceledExceptionImpl;
import com.huawei.hms.network.exception.NetworkInternalExceptionImpl;
import com.huawei.hms.network.exception.NetworkTimeoutExceptionImpl;
import com.huawei.hms.network.exception.NetworkUnsupportedExceptionImpl;
import java.io.IOException;

/* loaded from: classes9.dex */
public class t0 {
    public static IOException d(String str, Throwable th) {
        return h1.apiAvailable(4) ? new NetworkUnsupportedExceptionImpl(str, th) : new IOException(str, th);
    }

    public static IOException d(String str) {
        return h1.apiAvailable(4) ? new NetworkUnsupportedExceptionImpl(str) : new IOException(str);
    }

    public static IOException c(String str, Throwable th) {
        return h1.apiAvailable(4) ? new NetworkTimeoutExceptionImpl(str, th) : new IOException(str, th);
    }

    public static IOException c(String str) {
        return h1.apiAvailable(4) ? new NetworkTimeoutExceptionImpl(str) : new IOException(str);
    }

    public static IOException b(String str, Throwable th) {
        return h1.apiAvailable(4) ? new NetworkInternalExceptionImpl(str, th) : new IOException(str, th);
    }

    public static IOException b(String str) {
        return h1.apiAvailable(4) ? new NetworkInternalExceptionImpl(str) : new IOException(str);
    }

    public static IOException a(String str, Throwable th) {
        return h1.apiAvailable(4) ? new NetworkCanceledExceptionImpl(str, th) : new IOException(str, th);
    }

    public static IOException a(String str) {
        return h1.apiAvailable(4) ? new NetworkCanceledExceptionImpl(str) : new IOException(str);
    }
}
