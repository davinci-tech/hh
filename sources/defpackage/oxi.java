package defpackage;

import android.app.Activity;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.OpAnalyticsUtil;
import java.util.Arrays;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/* loaded from: classes6.dex */
public class oxi {

    /* renamed from: a, reason: collision with root package name */
    private boolean f16000a;
    private Activity b;
    private String c;
    private static final String[] e = {"value"};
    private static final String[] d = {"watchface_enable"};

    public oxi(Activity activity, String str) {
        this.b = activity;
        this.c = str;
        LogUtil.h("PluginOperation_ShowPrivacyDialogHelper", "ShowPrivacyDialogHelper mFrom:", str);
    }

    public void e(int i) {
        if (this.b == null) {
            LogUtil.h("PluginOperation_ShowPrivacyDialogHelper", "gotoWatchFace mActivity is null");
        } else {
            d(i);
        }
    }

    public void b(int i, boolean z) {
        LogUtil.h("PluginOperation_ShowPrivacyDialogHelper", "gotoWatchFace isNeedFinish ", Boolean.valueOf(z));
        this.f16000a = z;
        e(i);
    }

    public void d(int i) {
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        final String arrays = Arrays.toString(Thread.currentThread().getStackTrace());
        ThreadPoolManager d2 = ThreadPoolManager.d();
        Runnable runnable = new Runnable() { // from class: oxi.2
            @Override // java.lang.Runnable
            public void run() {
                LogUtil.b("PluginOperation_ShowPrivacyDialogHelper", "execute init adapter");
                jls.e(BaseApplication.getContext());
                countDownLatch.countDown();
                OpAnalyticsUtil.getInstance().setProbabilityProblemEvent("watchFaceInitAar", "stackTraceMessage :" + arrays);
            }
        };
        d2.d("PluginOperation_ShowPrivacyDialogHelper", runnable);
        try {
            if (!countDownLatch.await(3000L, TimeUnit.MILLISECONDS)) {
            }
        } catch (InterruptedException unused) {
            LogUtil.b("PluginOperation_ShowPrivacyDialogHelper", "init adapter is interrupted");
        } finally {
            LogUtil.b("PluginOperation_ShowPrivacyDialogHelper", "init adapter failed, time is out");
        }
        jls.bHW_(this.b, i);
        if (!this.f16000a || this.b.isFinishing()) {
            return;
        }
        LogUtil.a("PluginOperation_ShowPrivacyDialogHelper", "gotoDetailOrMarket finish");
        this.b.finish();
    }
}
