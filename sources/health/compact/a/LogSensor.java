package health.compact.a;

import android.os.Handler;
import android.os.Looper;
import android.os.Process;
import android.util.Log;
import com.huawei.hwlogsmodel.common.LogConfig;
import defpackage.ktv;
import java.io.File;

/* loaded from: classes.dex */
public class LogSensor {
    private static ktv b;

    /* renamed from: a, reason: collision with root package name */
    private static final boolean f13128a = BuildConfigProperties.e("IS_OUTPUT_LOG", false);
    private static final Object d = new Object();

    static {
        b = null;
        b = new ktv("LogSensor");
        com.huawei.hwlogsmodel.common.LogConfig.d(new LogConfig.Model() { // from class: health.compact.a.LogSensor.2
            @Override // com.huawei.hwlogsmodel.common.LogConfig.Model
            public void clearLogCache() {
                LogSensor.a();
            }
        });
    }

    private LogSensor() {
    }

    public static void a() {
        b.d();
        Log.i("LogSensor", "clearLogCache start in process:" + ProcessUtil.b() + " pid:" + Process.myPid());
        com.huawei.hwlogsmodel.common.LogConfig d2 = com.huawei.hwlogsmodel.LogUtil.d("sensor");
        if (d2 == null) {
            Log.w("LogSensor", "clearLogCache,logConfig null,return");
            return;
        }
        com.huawei.hwlogsmodel.common.LogConfig.d(new File(d2.l()));
        Log.i("LogSensor", "clearLogCache end in process:" + ProcessUtil.b() + " pid:" + Process.myPid());
    }

    public static void e(byte[] bArr) {
        if (bArr == null || bArr.length == 0) {
            Log.w("LogSensor", "LogSensor empty message return");
        } else {
            a(f13128a, bArr);
        }
    }

    private static void a(boolean z, byte[] bArr) {
        if (z) {
            b.b(bArr, false);
        }
    }

    public static void b() {
        synchronized (d) {
            b.c();
            b("LogSensor", "blockForAnalyze");
            b.b(false);
            new Handler(Looper.getMainLooper()).postDelayed(new Runnable() { // from class: health.compact.a.LogSensor.3
                @Override // java.lang.Runnable
                public void run() {
                    LogSensor.c();
                }
            }, 300000L);
        }
    }

    public static void c() {
        synchronized (d) {
            b("LogSensor", "unBlockForAnalyze");
            b.b(true);
        }
    }

    public static void b(String str, String str2) {
        Log.i(str, str2);
    }
}
