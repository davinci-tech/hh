package defpackage;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import com.huawei.haf.download.DownloadPluginCallback;
import com.huawei.haf.language.LanguageManager;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hms.network.httpclient.util.PreConnectManager;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.OpAnalyticsUtil;
import com.huawei.operation.OperationKey;
import com.huawei.ui.commonui.dialog.CustomTextAlertDialog;
import com.huawei.up.request.HttpRequestBase;
import health.compact.a.CommonUtil;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/* loaded from: classes4.dex */
public final class gok {
    private static volatile gok b;
    private int c;
    private c d;

    private gok() {
        if (LanguageManager.d()) {
            LanguageManager.b(true);
        }
    }

    public static gok b() {
        if (b == null) {
            synchronized (gok.class) {
                if (b == null) {
                    b = new gok();
                }
            }
        }
        return b;
    }

    public void a(Context context, boolean z) {
        if (LanguageManager.d() && !LanguageManager.b() && this.d == null) {
            if (!CommonUtil.aa(context)) {
                ReleaseLogUtil.d("Login_LanguageUpdate", "isNetworkConnected=false");
                this.c = 6;
                return;
            }
            this.c = 0;
            c cVar = new c(z);
            this.d = cVar;
            int a2 = LanguageManager.a(10000, cVar);
            ReleaseLogUtil.e("Login_LanguageUpdate", "start updateLanguagePackage taskNo=", Integer.valueOf(a2));
            if (a2 <= 0) {
                this.d = null;
                this.c = 3;
            }
        }
    }

    public boolean d() {
        if (LanguageManager.d()) {
            return LanguageManager.b();
        }
        return true;
    }

    public void e(int i) {
        InterruptedException e;
        boolean z;
        if (LanguageManager.d()) {
            c cVar = this.d;
            if (cVar == null) {
                if (this.c == 0) {
                    this.c = e(false, false, 3);
                    return;
                }
                return;
            }
            if (Looper.getMainLooper() == Looper.myLooper()) {
                LogUtil.h("Login_LanguageUpdate", "this is mainThread, waitUpdate thread error.");
                throw new IllegalThreadStateException("waitUpdate not allow InMainThread, please use this in new thread!");
            }
            try {
                try {
                    CountDownLatch countDownLatch = cVar.e;
                    if (LanguageManager.b() || countDownLatch == null) {
                        z = false;
                    } else {
                        z = !countDownLatch.await(i < 0 ? PreConnectManager.CONNECT_INTERNAL : i, TimeUnit.MILLISECONDS);
                    }
                } catch (InterruptedException e2) {
                    e = e2;
                    z = false;
                }
                try {
                    ReleaseLogUtil.e("Login_LanguageUpdate", "wait language package update complete. isTimeout=", Boolean.valueOf(z));
                } catch (InterruptedException e3) {
                    e = e3;
                    LogUtil.b("Login_LanguageUpdate", e.toString());
                    this.d = null;
                    this.c = e(z, cVar.c, cVar.b);
                }
                this.d = null;
                this.c = e(z, cVar.c, cVar.b);
            } catch (Throwable th) {
                this.d = null;
                throw th;
            }
        }
    }

    private int e(boolean z, boolean z2, int i) {
        if (LanguageManager.b()) {
            return 0;
        }
        if (z) {
            return z2 ? 8 : 4;
        }
        if (i < 0) {
            return 3;
        }
        return i;
    }

    public void aQC_(Context context, final Handler handler) {
        if (!LanguageManager.d() || context == null || handler == null) {
            return;
        }
        int i = this.c;
        this.c = 0;
        if (i == 0) {
            return;
        }
        ReleaseLogUtil.e("Login_LanguageUpdate", "checkUpdateStatus=", Integer.valueOf(i));
        CustomTextAlertDialog a2 = new CustomTextAlertDialog.Builder(context).b(R.string.IDS_service_area_notice_title).e(context.getString(R.string._2130841207_res_0x7f020e77, context.getString(R.string.IDS_device_upgrade_file_size_kb, Integer.toString(300)))).cyU_(R.string._2130841206_res_0x7f020e76, new View.OnClickListener() { // from class: gok.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ReleaseLogUtil.e("Login_LanguageUpdate", "finish MainActivity for cause: DownloadLanguage reStart");
                handler.sendEmptyMessage(12);
                ViewClickInstrumentation.clickOnView(view);
            }
        }).cyR_(R.string._2130841132_res_0x7f020e2c, new View.OnClickListener() { // from class: gok.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ViewClickInstrumentation.clickOnView(view);
            }
        }).a();
        a2.setCancelable(false);
        a2.show();
        e(i + 100, 0, 0L);
    }

    public void a() {
        LanguageManager.e();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void e(int i, int i2, long j) {
        StringBuilder sb = new StringBuilder(64);
        sb.append("locale=");
        sb.append(Locale.getDefault());
        sb.append(", lang=");
        sb.append(LanguageManager.d((Locale) null, true));
        if (i2 > 0) {
            sb.append(", taskNo=");
            sb.append(i2);
            sb.append(", costTime=");
            sb.append(j);
        }
        LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<>(5);
        linkedHashMap.put("errorCode", Integer.toString(i));
        linkedHashMap.put(HttpRequestBase.TAG_ERROR_DESC, sb.toString());
        OpAnalyticsUtil.getInstance().setKeyProcessEvent(OperationKey.HEALTH_LANGUAGE_DOWNLOAD_85070031.value(), linkedHashMap);
    }

    static class c implements DownloadPluginCallback {

        /* renamed from: a, reason: collision with root package name */
        private final long f12886a;
        private int b = -1;
        private boolean c;
        private final CountDownLatch e;

        c(boolean z) {
            this.e = z ? new CountDownLatch(1) : null;
            this.f12886a = System.currentTimeMillis();
        }

        @Override // com.huawei.haf.download.DownloadPluginCallback
        public boolean onDownloadPluginResult(int i, String str, int i2) {
            ReleaseLogUtil.e("Login_LanguageUpdate", "onDownloadPluginResult taskNo=", Integer.valueOf(i), ", fileId=", str, ", result=", Integer.valueOf(i2));
            LanguageManager.b(str);
            CountDownLatch countDownLatch = this.e;
            if (countDownLatch == null) {
                return false;
            }
            countDownLatch.countDown();
            return false;
        }

        @Override // com.huawei.haf.download.DownloadPluginCallback
        public void startDownloadProgress(int i) {
            this.c = true;
            ReleaseLogUtil.e("Login_LanguageUpdate", "startDownloadProgress taskNo=", Integer.valueOf(i));
        }

        @Override // com.huawei.haf.download.DownloadPluginCallback
        public void showDownloadProgress(int i, int i2, int i3) {
            ReleaseLogUtil.e("Login_LanguageUpdate", "showDownloadProgress taskNo=", Integer.valueOf(i), ", progress=", Integer.valueOf(i3));
        }

        @Override // com.huawei.haf.download.DownloadPluginCallback
        public void onDownloadResult(int i, int i2) {
            boolean b = LanguageManager.b();
            if (b) {
                i2 = 0;
            }
            this.b = i2;
            ReleaseLogUtil.e("Login_LanguageUpdate", "onDownloadResult taskNo=", Integer.valueOf(i), ", reason=", Integer.valueOf(this.b), ", result=", Boolean.valueOf(b));
            gok.e(this.b, i, System.currentTimeMillis() - this.f12886a);
        }
    }
}
