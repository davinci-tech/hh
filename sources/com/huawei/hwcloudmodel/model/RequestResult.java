package com.huawei.hwcloudmodel.model;

import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.openalliance.ad.constant.Constants;
import health.compact.a.IoUtils;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.util.zip.GZIPInputStream;

/* loaded from: classes5.dex */
public class RequestResult {
    private static final int BUFFER_FINISH_FLAG = -1;
    private static final int BUFFER_OFFSET = 0;
    private static final int BUFFER_SIZE = 1024;
    private static final String TAG = "RequestResult";
    private HttpURLConnection mConnection;
    private InputStream mInputStream;
    private int mStatusCode;

    public RequestResult(HttpURLConnection httpURLConnection) throws IOException {
        if (httpURLConnection == null) {
            return;
        }
        this.mConnection = httpURLConnection;
        this.mStatusCode = httpURLConnection.getResponseCode();
        InputStream errorStream = httpURLConnection.getErrorStream();
        this.mInputStream = errorStream;
        if (errorStream == null) {
            this.mInputStream = httpURLConnection.getInputStream();
        }
        if (httpURLConnection.getContentEncoding() == null || this.mInputStream == null || httpURLConnection.getContentEncoding() == null || !Constants.GZIP.equals(httpURLConnection.getContentEncoding())) {
            return;
        }
        this.mInputStream = new GZIPInputStream(this.mInputStream);
    }

    public int getStatusCode() {
        return this.mStatusCode;
    }

    public InputStream asStream() {
        return this.mInputStream;
    }

    public byte[] asByte() {
        byte[] bArr = new byte[1024];
        InputStream asStream = asStream();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        while (true) {
            try {
                try {
                    int read = asStream.read(bArr, 0, 1024);
                    if (read == -1) {
                        break;
                    }
                    byteArrayOutputStream.write(bArr, 0, read);
                } catch (IOException e) {
                    LogUtil.b(TAG, "asByte Exception e = ", e.getMessage());
                }
            } finally {
                IoUtils.e(asStream);
                this.mConnection.disconnect();
                byteArrayOutputStream.toByteArray();
                IoUtils.e(byteArrayOutputStream);
            }
        }
        return byteArrayOutputStream.toByteArray();
    }
}
