package com.huawei.hms.framework.network.grs.h;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.hms.framework.common.IoUtils;
import com.huawei.hms.framework.common.Logger;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/* loaded from: classes4.dex */
public class c {

    /* renamed from: a, reason: collision with root package name */
    private static final String f4552a = "c";

    public static String a(String str, Context context) {
        Logger.d(f4552a, "Io getConfigContent, fileName: " + str);
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        InputStream inputStream = null;
        try {
            try {
            } catch (IOException unused) {
                Logger.w(f4552a, "local config file is not exist.filename is {%s}", str);
            }
            if (new File(str).isDirectory()) {
                return "";
            }
            inputStream = context.getAssets().open(str);
            byte[] bArr = new byte[8192];
            while (true) {
                int read = inputStream.read(bArr);
                if (read == -1) {
                    byteArrayOutputStream.flush();
                    return byteArrayOutputStream.toString("UTF-8");
                }
                byteArrayOutputStream.write(bArr, 0, read);
            }
        } finally {
            IoUtils.closeSecure((OutputStream) byteArrayOutputStream);
            IoUtils.closeSecure(inputStream);
        }
    }
}
