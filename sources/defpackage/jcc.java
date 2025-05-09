package defpackage;

import android.text.TextUtils;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.hwlogsmodel.common.LogConfig;
import health.compact.a.CommonUtil;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/* loaded from: classes5.dex */
public class jcc {

    /* renamed from: a, reason: collision with root package name */
    private static final Object f13733a = new Object();
    private static final String c = LogConfig.o() + "app_config_value.txt";
    private static jcc d;
    private ExecutorService b = Executors.newSingleThreadExecutor();

    private jcc() {
    }

    public static jcc d() {
        jcc jccVar;
        synchronized (f13733a) {
            if (d == null) {
                d = new jcc();
            }
            jccVar = d;
        }
        return jccVar;
    }

    public void b(final String str, final String str2) {
        if (this.b == null) {
            this.b = Executors.newSingleThreadExecutor();
        }
        this.b.execute(new Runnable() { // from class: jcc.5
            @Override // java.lang.Runnable
            public void run() {
                FileOutputStream fileOutputStream;
                LogUtil.a("Util_ConfigUserSetValue", " button status key : ", str, "button status values :", str2);
                FileOutputStream fileOutputStream2 = null;
                try {
                    try {
                        String c2 = CommonUtil.c(jcc.c);
                        if (TextUtils.isEmpty(c2)) {
                            LogUtil.a("Util_ConfigUserSetValue", "configStatus safePath is empty");
                            return;
                        }
                        File file = new File(c2);
                        if (!file.getParentFile().exists()) {
                            LogUtil.a("Util_ConfigUserSetValue", "is mkdir parent file : " + file.getParentFile().mkdirs());
                        }
                        if (!file.isFile()) {
                            LogUtil.a("Util_ConfigUserSetValue", "is create new file : " + file.createNewFile());
                        }
                        fileOutputStream = new FileOutputStream(file, true);
                        try {
                            String str3 = str + " : " + str2 + ";" + System.lineSeparator();
                            fileOutputStream.write(str3.getBytes("UTF-8"), 0, str3.length());
                            try {
                                fileOutputStream.close();
                            } catch (IOException unused) {
                                LogUtil.b("Util_ConfigUserSetValue", "close io exception");
                            }
                        } catch (IOException unused2) {
                            fileOutputStream2 = fileOutputStream;
                            LogUtil.a("Util_ConfigUserSetValue", "create file io execption ");
                            if (fileOutputStream2 != null) {
                                try {
                                    fileOutputStream2.close();
                                } catch (IOException unused3) {
                                    LogUtil.b("Util_ConfigUserSetValue", "close io exception");
                                }
                            }
                        } catch (Throwable th) {
                            th = th;
                            if (fileOutputStream != null) {
                                try {
                                    fileOutputStream.close();
                                } catch (IOException unused4) {
                                    LogUtil.b("Util_ConfigUserSetValue", "close io exception");
                                }
                            }
                            throw th;
                        }
                    } catch (IOException unused5) {
                    }
                } catch (Throwable th2) {
                    th = th2;
                    fileOutputStream = null;
                }
            }
        });
    }

    public void c() {
        File file = new File(c);
        if (file.exists()) {
            LogUtil.a("Util_ConfigUserSetValue", "is delete file : " + file.delete());
        }
    }
}
