package defpackage;

import android.content.Context;
import android.net.Uri;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.utils.WebViewHelp;
import com.huawei.ui.commonui.utils.versioncontrol.VersionControlUtil;
import com.huawei.ui.main.stories.service.SleepServiceImpl;
import java.lang.ref.WeakReference;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/* loaded from: classes6.dex */
public class pwo {
    private final Uri f;
    private final Context h;
    private final String b = "from";
    private final String d = WebViewHelp.BI_KEY_PULL_FROM;

    /* renamed from: a, reason: collision with root package name */
    private final String f16310a = "resourceName";
    private final String c = "resourceId";
    private final String e = "pullOrder";
    private volatile Boolean i = false;
    private final long j = 120;
    private final WeakReference<pwo> g = new WeakReference<>(this);

    public pwo(Context context, Uri uri) {
        this.h = context;
        this.f = uri;
    }

    public void b() {
        if (VersionControlUtil.isShowSmartSleepImprovement()) {
            this.i = pob.a((Boolean) null);
            ThreadPoolManager.d().execute(new Runnable() { // from class: pwp
                @Override // java.lang.Runnable
                public final void run() {
                    pwo.this.e();
                }
            });
        } else {
            SleepServiceImpl.dRw_(this.h, this.f);
        }
    }

    /* synthetic */ void e() {
        if (this.i == null) {
            CountDownLatch countDownLatch = new CountDownLatch(1);
            pob.d(new c(this.h, this.g, countDownLatch));
            try {
                countDownLatch.await(120L, TimeUnit.MILLISECONDS);
            } catch (InterruptedException unused) {
                LogUtil.b("IntelSleepJumpManager", "open status InterruptedException occurs");
            }
        }
        LogUtil.a("IntelSleepJumpManager", "get open status :", this.i);
        duH_(this.h, this.f);
    }

    private void duH_(Context context, Uri uri) {
        if (Boolean.TRUE.equals(this.i)) {
            SleepServiceImpl.dRw_(context, uri);
        } else {
            SleepServiceImpl.a(e("#/distribute", 2));
        }
    }

    private String e(String str, int i) {
        return String.format("%s?%s=%s&%s=%s&%s=%s&%s=%s&%s=%s", str, "from", Integer.valueOf(i), WebViewHelp.BI_KEY_PULL_FROM, this.f.getQueryParameter(WebViewHelp.BI_KEY_PULL_FROM), "resourceName", this.f.getQueryParameter("resourceName"), "resourceId", this.f.getQueryParameter("resourceId"), "pullOrder", this.f.getQueryParameter("pullOrder"));
    }

    static class c implements IBaseResponseCallback {
        private final CountDownLatch b;
        private final WeakReference<Context> c;
        private final WeakReference<pwo> e;

        public c(Context context, WeakReference<pwo> weakReference, CountDownLatch countDownLatch) {
            this.c = new WeakReference<>(context);
            this.e = weakReference;
            this.b = countDownLatch;
        }

        @Override // com.huawei.hwbasemgr.IBaseResponseCallback
        /* renamed from: onResponse */
        public void d(int i, Object obj) {
            pwo pwoVar = this.e.get();
            Context context = this.c.get();
            if (pwoVar == null || context == null) {
                LogUtil.b("IntelSleepJumpManager", "KnitSleepDetailActivity is null or context is null");
                CountDownLatch countDownLatch = this.b;
                if (countDownLatch != null) {
                    countDownLatch.countDown();
                    return;
                }
                return;
            }
            if (obj instanceof Boolean) {
                pwoVar.i = Boolean.valueOf(((Boolean) obj).booleanValue());
            } else if (i == 23030) {
                pwoVar.i = false;
            }
            LogUtil.a("IntelSleepJumpManager", "errorCode: ", Integer.valueOf(i), " isOpen: ", pwoVar.i);
            CountDownLatch countDownLatch2 = this.b;
            if (countDownLatch2 != null) {
                countDownLatch2.countDown();
            }
        }
    }
}
