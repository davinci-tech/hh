package defpackage;

import android.util.Log;
import com.huawei.skinner.facade.template.ILogger;

/* loaded from: classes6.dex */
public class ndj implements ILogger {

    /* renamed from: a, reason: collision with root package name */
    private boolean f15265a = false;
    private boolean c = false;

    @Override // com.huawei.skinner.facade.template.ILogger
    public void showLog(boolean z) {
        this.f15265a = z;
    }

    @Override // com.huawei.skinner.facade.template.ILogger
    public void showDetailedStackTrace(boolean z) {
        this.c = z;
    }

    @Override // com.huawei.skinner.facade.template.ILogger
    public void debug(String str) {
        debug("HwSkinner", str);
    }

    @Override // com.huawei.skinner.facade.template.ILogger
    public void info(String str) {
        info("HwSkinner", str);
    }

    @Override // com.huawei.skinner.facade.template.ILogger
    public void warn(String str) {
        warn("HwSkinner", str);
    }

    @Override // com.huawei.skinner.facade.template.ILogger
    public void error(String str) {
        error("HwSkinner", str);
    }

    @Override // com.huawei.skinner.facade.template.ILogger
    public void error(String str, Throwable th) {
        error("HwSkinner", str, th);
    }

    @Override // com.huawei.skinner.facade.template.ILogger
    public void debug(String str, String str2) {
        if (this.f15265a) {
            Log.d("HwSkinner", b(str, str2));
        }
    }

    @Override // com.huawei.skinner.facade.template.ILogger
    public void info(String str, String str2) {
        Log.i("HwSkinner", b(str, str2));
    }

    @Override // com.huawei.skinner.facade.template.ILogger
    public void warn(String str, String str2) {
        if (this.f15265a) {
            Log.w("HwSkinner", b(str, str2));
        }
    }

    @Override // com.huawei.skinner.facade.template.ILogger
    public void error(String str, String str2) {
        Log.e("HwSkinner", b(str, str2));
    }

    @Override // com.huawei.skinner.facade.template.ILogger
    public void error(String str, String str2, Throwable th) {
        if (this.c) {
            Log.e("HwSkinner", b(str, str2), th);
            return;
        }
        if (th == null) {
            th = new NullPointerException("throwable is null!");
        }
        Throwable cause = th.getCause();
        error(str, "Error:" + (cause == null ? th.getMessage() : cause.getMessage()) + "; " + str2);
    }

    @Override // com.huawei.skinner.facade.template.ILogger
    public boolean isShowLog() {
        return this.f15265a;
    }

    @Override // com.huawei.skinner.facade.template.ILogger
    public boolean isShowDetailedStackTrace() {
        return this.c;
    }

    private String b(String str, String str2) {
        if ("HwSkinner".equals(str)) {
            return str2;
        }
        return str + "#" + str2;
    }
}
