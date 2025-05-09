package com.huawei.ads.fund.util;

import android.util.Log;
import com.huawei.hms.ads.uiengineloader.aj;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.InputStream;
import java.net.HttpURLConnection;

/* loaded from: classes8.dex */
public abstract class StreamUtil {
    public static String readStream(InputStream inputStream) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] bArr = new byte[2048];
        while (true) {
            int read = inputStream.read(bArr);
            if (-1 == read) {
                return byteArrayOutputStream.toString("UTF-8");
            }
            byteArrayOutputStream.write(bArr, 0, read);
        }
    }

    public static void close(HttpURLConnection httpURLConnection) {
        if (httpURLConnection != null) {
            try {
                httpURLConnection.disconnect();
            } catch (Throwable th) {
                Log.w(aj.f4374a, "close HttpURLConnection Exception:" + th.getClass().getSimpleName());
            }
        }
    }

    public static void close(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (Throwable th) {
                Log.w(aj.f4374a, "close Throwable:" + th.getClass().getSimpleName());
            }
        }
    }
}
