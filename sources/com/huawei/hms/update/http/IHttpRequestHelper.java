package com.huawei.hms.update.http;

import android.content.Context;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/* loaded from: classes9.dex */
public interface IHttpRequestHelper {
    public static final int HTTP_OK = 200;
    public static final int HTTP_PARTIAL = 206;

    void cancel();

    void close();

    int get(String str, OutputStream outputStream, int i, int i2, Context context) throws IOException, CanceledException;

    int get(String str, OutputStream outputStream, Context context) throws IOException, CanceledException;

    int post(String str, InputStream inputStream, OutputStream outputStream, Context context) throws IOException, CanceledException;
}
