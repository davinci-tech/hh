package com.huawei.hianalytics;

import com.huawei.hianalytics.core.common.EnvUtils;
import com.huawei.hianalytics.core.log.HiLog;
import com.huawei.secure.android.common.encrypt.utils.EncryptUtil;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/* loaded from: classes4.dex */
public class o0 {

    /* renamed from: a, reason: collision with root package name */
    public String f3889a;

    public final String b(String str) {
        FileInputStream fileInputStream;
        byte[] bArr;
        int i;
        byte[] bArr2;
        File file = new File(a(str), "hianalytics_" + str);
        if (!a(file)) {
            String generateSecureRandomStr = EncryptUtil.generateSecureRandomStr(128);
            g1.a(file, generateSecureRandomStr);
            return generateSecureRandomStr;
        }
        FileInputStream fileInputStream2 = null;
        try {
            try {
                fileInputStream = new FileInputStream(file);
            } catch (Exception unused) {
            }
        } catch (Throwable th) {
            th = th;
        }
        try {
            bArr = new byte[1024];
            byte[] bArr3 = new byte[1024];
            i = 0;
            while (true) {
                int read = fileInputStream.read(bArr3);
                if (read == -1) {
                    break;
                }
                if (read > 0) {
                    if (bArr.length - i >= read) {
                        System.arraycopy(bArr3, 0, bArr, i, read);
                    } else {
                        byte[] bArr4 = new byte[(bArr.length + read) << 1];
                        System.arraycopy(bArr, 0, bArr4, 0, i);
                        System.arraycopy(bArr3, 0, bArr4, i, read);
                        bArr = bArr4;
                    }
                    i += read;
                }
            }
        } catch (Exception unused2) {
            fileInputStream2 = fileInputStream;
            HiLog.e("HASU", "read file error");
            g1.a(fileInputStream2);
            return "";
        } catch (Throwable th2) {
            th = th2;
            fileInputStream2 = fileInputStream;
            g1.a(fileInputStream2);
            throw th;
        }
        if (i == 0) {
            g1.a(fileInputStream);
            return "";
        }
        if (i <= 0) {
            bArr2 = new byte[0];
        } else {
            bArr2 = new byte[i];
            System.arraycopy(bArr, 0, bArr2, 0, i);
        }
        String str2 = new String(bArr2, StandardCharsets.UTF_8);
        g1.a(fileInputStream);
        return str2;
    }

    public final boolean a(File file) {
        if (file.exists()) {
            return true;
        }
        try {
            return file.createNewFile();
        } catch (IOException unused) {
            HiLog.e("ComponentManager", "create new file error");
            return false;
        }
    }

    public final void a(String str, String str2) {
        File file = new File(a(str));
        File file2 = new File(a(str), "hianalytics_" + str);
        if (!file.exists() && file.mkdirs()) {
            HiLog.i("ComponentManager", "file directory is mkdirs");
        }
        if (a(file2)) {
            g1.a(file2, str2);
        } else {
            HiLog.w("ComponentManager", "file is not found, file is create failed");
        }
    }

    public final String a(String str) {
        return this.f3889a + "/hianalytics_/component/".replace("component", str);
    }

    public static boolean b(File file) {
        File[] listFiles;
        if (file == null || !file.exists() || !file.isDirectory() || (listFiles = file.listFiles()) == null || listFiles.length == 0) {
            return false;
        }
        for (File file2 : listFiles) {
            if (file2.isFile()) {
                if (!file2.delete()) {
                    HiLog.i("ComponentManager", "delete file failed: " + file2.getName());
                }
            } else if (file2.isDirectory()) {
                b(file2);
            }
        }
        return file.delete();
    }

    public static boolean a() {
        File file;
        try {
            file = new File(EnvUtils.getAppContext().getFilesDir().getCanonicalPath() + File.separator + "/hianalytics_");
        } catch (IOException unused) {
            HiLog.e("ComponentManager", "delete component file error");
            file = null;
        }
        return b(file);
    }

    public o0() {
        try {
            this.f3889a = EnvUtils.getAppContext().getFilesDir().getCanonicalPath() + File.separator;
        } catch (IOException unused) {
            HiLog.e("ComponentManager", "get fileRootDirectory error");
        }
    }
}
