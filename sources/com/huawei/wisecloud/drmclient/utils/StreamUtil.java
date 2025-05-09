package com.huawei.wisecloud.drmclient.utils;

import com.huawei.hms.ads.uiengineloader.aj;
import com.huawei.wisecloud.drmclient.exception.HwDrmException;
import com.huawei.wisecloud.drmclient.log.HwDrmLog;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/* loaded from: classes9.dex */
public class StreamUtil {
    public static byte[] toByteArray(InputStream inputStream) throws HwDrmException {
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            try {
                byte[] bArr = new byte[4096];
                while (true) {
                    int read = inputStream.read(bArr);
                    if (-1 != read) {
                        byteArrayOutputStream.write(bArr, 0, read);
                    } else {
                        byte[] byteArray = byteArrayOutputStream.toByteArray();
                        byteArrayOutputStream.close();
                        return byteArray;
                    }
                }
            } finally {
            }
        } catch (IOException e) {
            String str = "fail to convert InputStream, IOException: " + HwDrmLog.printException((Exception) e);
            HwDrmLog.e(aj.f4374a, str);
            throw new HwDrmException(str);
        }
    }
}
