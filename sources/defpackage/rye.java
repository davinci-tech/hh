package defpackage;

import android.text.TextUtils;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.IoUtils;
import health.compact.a.LogAnonymous;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

/* loaded from: classes7.dex */
public class rye {
    public static int a(String str, String str2) {
        return SharedPreferenceManager.e(BaseApplication.getContext(), String.valueOf(20014), str, str2, (StorageParams) null);
    }

    public static String b(String str) {
        return SharedPreferenceManager.b(BaseApplication.getContext(), String.valueOf(20014), str);
    }

    public static boolean e(String str) {
        if (TextUtils.isEmpty(str)) {
            return true;
        }
        File file = new File(str);
        if (!file.exists()) {
            return true;
        }
        if (!file.isDirectory()) {
            return file.length() <= 0;
        }
        File[] listFiles = file.listFiles();
        return listFiles == null || listFiles.length <= 0;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r3v4 */
    public static String d(String str) {
        Throwable th;
        Closeable closeable;
        IOException e;
        FileInputStream fileInputStream;
        Closeable closeable2;
        Throwable th2;
        FileInputStream fileInputStream2;
        FileInputStream fileInputStream3;
        String str2 = "";
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("Util", "readJson path is empty");
            return "";
        }
        FileInputStream fileInputStream4 = null;
        try {
            File file = new File(str);
            if (file.exists()) {
                fileInputStream = new FileInputStream(file);
                try {
                    InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, StandardCharsets.UTF_8);
                    try {
                        StringBuilder sb = new StringBuilder(16);
                        while (true) {
                            int read = inputStreamReader.read();
                            if (read == -1) {
                                break;
                            }
                            sb.append((char) read);
                        }
                        str2 = sb.toString();
                        fileInputStream4 = fileInputStream;
                        fileInputStream3 = inputStreamReader;
                    } catch (IOException e2) {
                        e = e2;
                        fileInputStream4 = inputStreamReader;
                        try {
                            LogUtil.b("Util", "readJson ioException is ", LogAnonymous.b((Throwable) e));
                            IoUtils.e(fileInputStream);
                            fileInputStream2 = fileInputStream4;
                            IoUtils.e(fileInputStream2);
                            return str2;
                        } catch (Throwable th3) {
                            inputStreamReader = fileInputStream4;
                            th2 = th3;
                            th = th2;
                            closeable2 = inputStreamReader;
                            fileInputStream4 = fileInputStream;
                            closeable = closeable2;
                            IoUtils.e(fileInputStream4);
                            IoUtils.e(closeable);
                            throw th;
                        }
                    } catch (Throwable th4) {
                        th2 = th4;
                        th = th2;
                        closeable2 = inputStreamReader;
                        fileInputStream4 = fileInputStream;
                        closeable = closeable2;
                        IoUtils.e(fileInputStream4);
                        IoUtils.e(closeable);
                        throw th;
                    }
                } catch (IOException e3) {
                    e = e3;
                } catch (Throwable th5) {
                    th = th5;
                    closeable2 = null;
                    fileInputStream4 = fileInputStream;
                    closeable = closeable2;
                    IoUtils.e(fileInputStream4);
                    IoUtils.e(closeable);
                    throw th;
                }
            } else {
                fileInputStream3 = null;
            }
            IoUtils.e(fileInputStream4);
            fileInputStream2 = fileInputStream3;
        } catch (IOException e4) {
            e = e4;
            fileInputStream = null;
        } catch (Throwable th6) {
            th = th6;
            closeable = null;
            IoUtils.e(fileInputStream4);
            IoUtils.e(closeable);
            throw th;
        }
        IoUtils.e(fileInputStream2);
        return str2;
    }
}
