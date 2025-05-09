package defpackage;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.handler.ExtendHandler;
import com.huawei.haf.handler.HandlerCenter;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.login.ui.login.LoginInit;
import com.tencent.mm.opensdk.constants.ConstantsAPI;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import defpackage.gsl;
import health.compact.a.BroadcastManagerUtil;
import health.compact.a.HiBroadcastManager;
import health.compact.a.LogAnonymous;
import health.compact.a.LogUtil;
import health.compact.a.ReleaseLogUtil;
import health.compact.a.SharedPreferenceManager;
import java.lang.ref.WeakReference;

/* loaded from: classes4.dex */
public class gsj {
    private ExtendHandler b;
    private b d;
    private IWXAPI e;
    private a g;
    private e i;
    private gsk c = new gsk();

    /* renamed from: a, reason: collision with root package name */
    private gsk f12907a = new gsk();

    public void e() {
        ReleaseLogUtil.b("R_MmSportSyncManager", " init.");
        ThreadPoolManager.d().execute(new Runnable() { // from class: gsm
            @Override // java.lang.Runnable
            public final void run() {
                gsj.this.c();
            }
        });
    }

    /* synthetic */ void c() {
        try {
            if (!b()) {
                ReleaseLogUtil.b("R_MmSportSyncManager", "not bind wechat.");
                a();
                return;
            }
            this.b = HandlerCenter.yt_(new c(this), "MmSportSyncManager");
            d();
            i();
            h();
            j();
        } catch (Exception e2) {
            ReleaseLogUtil.c("R_MmSportSyncManager", "init exception:", LogAnonymous.b((Throwable) e2));
        }
    }

    public void a() {
        ReleaseLogUtil.b("R_MmSportSyncManager", " onDestroy.");
        ExtendHandler extendHandler = this.b;
        if (extendHandler != null) {
            extendHandler.quit(true);
        }
        if (this.g != null) {
            ReleaseLogUtil.b("R_MmSportSyncManager", "unregister WxSportRefreshReceiver.");
            BaseApplication.e().unregisterReceiver(this.g);
            this.g = null;
        }
        if (this.i != null) {
            ReleaseLogUtil.b("R_MmSportSyncManager", "unregister WxStartReceiver.");
            BaseApplication.e().unregisterReceiver(this.i);
            this.i = null;
        }
        if (this.d != null) {
            ReleaseLogUtil.b("R_MmSportSyncManager", "unregister mStepSyncCloudReceiver.");
            BaseApplication.e().unregisterReceiver(this.d);
            this.d = null;
        }
        IWXAPI iwxapi = this.e;
        if (iwxapi != null) {
            iwxapi.unregisterApp();
        }
    }

    public void b(int i) {
        this.c.e(i);
        this.c.d(System.currentTimeMillis());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d() {
        ReleaseLogUtil.b("R_MmSportSyncManager", "getIwxApi.");
        IWXAPI createWXAPI = WXAPIFactory.createWXAPI(BaseApplication.e(), null);
        this.e = createWXAPI;
        boolean registerApp = createWXAPI.registerApp("wx36bda3d35fbcfd06");
        gsl.c b2 = gsl.b(BaseApplication.e(), "wx36bda3d35fbcfd06");
        int b3 = gsl.b(BaseApplication.e());
        Object[] objArr = new Object[8];
        objArr[0] = "isRegister:";
        objArr[1] = Boolean.valueOf(registerApp);
        objArr[2] = "getSportConfig:";
        objArr[3] = Integer.valueOf(b2.b());
        objArr[4] = " ";
        objArr[5] = b2.c() instanceof gsl.b ? Long.valueOf(((gsl.b) b2.c()).d()) : "no data";
        objArr[6] = " apiLevel:";
        objArr[7] = Integer.valueOf(b3);
        LogUtil.c("MmSportSyncManager", objArr);
    }

    private void i() {
        if (this.i == null) {
            ReleaseLogUtil.b("R_MmSportSyncManager", "registerWeChartStart.");
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction(ConstantsAPI.ACTION_REFRESH_WXAPP);
            this.i = new e(this.b);
            BroadcastManagerUtil.bFB_(BaseApplication.e(), this.i, intentFilter);
        }
    }

    private void h() {
        if (this.g == null) {
            ReleaseLogUtil.b("R_MmSportSyncManager", "registerWeChartSport.");
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("com.tencent.mm.plugin.openapi.Intent.ACTION_SET_SPORT_STEP");
            this.g = new a(this.b);
            BroadcastManagerUtil.bFB_(BaseApplication.e(), this.g, intentFilter);
        }
    }

    private void j() {
        if (this.d == null) {
            ReleaseLogUtil.b("R_MmSportSyncManager", "registerStepSyncCloud.");
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("com.huawei.hihealth.action_sync_total_sport_data");
            this.d = new b(this.b);
            HiBroadcastManager.bwj_(BaseApplication.e(), this.d, intentFilter);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void f() {
        this.b.removeMessages(102);
        long currentTimeMillis = System.currentTimeMillis();
        if (!jdl.d(currentTimeMillis, this.c.e())) {
            ReleaseLogUtil.b("R_MmSportSyncManager", "sync step failed, step is not current day.");
            return;
        }
        if (jdl.d(this.c.e(), this.f12907a.e()) && this.c.d() == this.f12907a.d()) {
            ReleaseLogUtil.b("R_MmSportSyncManager", "step not changed, not need sync.");
        } else {
            if (currentTimeMillis - this.f12907a.e() < 300000) {
                long e2 = this.f12907a.e();
                this.b.sendEmptyMessage(102, 300000L);
                ReleaseLogUtil.b("R_MmSportSyncManager", "sync step is not pass 5 minute, interval is:", Long.valueOf(currentTimeMillis - e2));
                return;
            }
            d(gsl.b(BaseApplication.e(), "wx36bda3d35fbcfd06", this.c.e() / 1000, this.c.d()), this.c.d(), System.currentTimeMillis());
        }
    }

    private void d(int i, int i2, long j) {
        ReleaseLogUtil.b("R_MmSportSyncManager", "setSportStep:", Integer.valueOf(i), " syncStep:", Integer.valueOf(i2), " syncStepTimestamp:", Long.valueOf(j));
        if (i == 1) {
            this.f12907a.d(j);
            this.f12907a.e(i2);
            return;
        }
        if (i != 3 && i != 9) {
            switch (i) {
                case 3902:
                    ReleaseLogUtil.b("R_MmSportSyncManager", "set step failed, step switch is not open.");
                    break;
                case 3903:
                    ReleaseLogUtil.a("R_MmSportSyncManager", "set step failed, sync step too much.");
                    this.b.sendEmptyMessage(102, 300000L);
                    break;
                case 3904:
                    ReleaseLogUtil.a("R_MmSportSyncManager", "set step failed, current step count is too many.");
                    break;
            }
            return;
        }
        ReleaseLogUtil.a("R_MmSportSyncManager", "set step failed,not login.");
        this.b.sendEmptyMessage(102, 300000L);
    }

    static class c implements Handler.Callback {

        /* renamed from: a, reason: collision with root package name */
        private WeakReference<gsj> f12909a;

        c(gsj gsjVar) {
            this.f12909a = new WeakReference<>(gsjVar);
        }

        @Override // android.os.Handler.Callback
        public boolean handleMessage(Message message) {
            ReleaseLogUtil.b("R_MmSportSyncManager", "handle message.", Integer.valueOf(message.what));
            WeakReference<gsj> weakReference = this.f12909a;
            if (weakReference == null || weakReference.get() == null) {
                return false;
            }
            try {
                if (message.what == 101) {
                    this.f12909a.get().d();
                    return true;
                }
                if (message.what != 102) {
                    return false;
                }
                this.f12909a.get().f();
                return true;
            } catch (Exception e) {
                ReleaseLogUtil.c("R_MmSportSyncManager", "handle message.", Integer.valueOf(message.what), " exception:", LogAnonymous.b((Throwable) e));
                return false;
            }
        }
    }

    static class e extends BroadcastReceiver {
        private ExtendHandler e;

        e(ExtendHandler extendHandler) {
            this.e = extendHandler;
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (intent == null) {
                LogUtil.a("MmSportSyncManager", "WxStartReceiver intent == null");
                return;
            }
            ReleaseLogUtil.b("MmSportSyncManager", "receiver we chat start.", intent.getAction());
            if (ConstantsAPI.ACTION_REFRESH_WXAPP.equals(intent.getAction())) {
                this.e.sendEmptyMessage(101, 12000L);
            }
        }
    }

    static class a extends BroadcastReceiver {

        /* renamed from: a, reason: collision with root package name */
        private ExtendHandler f12908a;

        a(ExtendHandler extendHandler) {
            this.f12908a = extendHandler;
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (intent == null) {
                LogUtil.a("MmSportSyncManager", "WxSportRefreshReceiver intent == null");
                return;
            }
            ReleaseLogUtil.b("R_MmSportSyncManager", "receiver we chat refresh.", intent.getAction());
            if ("com.tencent.mm.plugin.openapi.Intent.ACTION_SET_SPORT_STEP".equals(intent.getAction())) {
                String stringExtra = intent.getStringExtra("EXTRA_EXT_OPEN_NOTIFY_TYPE");
                if (TextUtils.isEmpty(stringExtra)) {
                    ReleaseLogUtil.c("R_MmSportSyncManager", "wrong intent extra notifyType");
                    return;
                }
                ReleaseLogUtil.b("R_MmSportSyncManager", "we chat notify.", stringExtra);
                if ("SPORT_MESSAGE".equals(stringExtra)) {
                    this.f12908a.sendEmptyMessage(102);
                }
            }
        }
    }

    static class b extends BroadcastReceiver {
        private ExtendHandler d;

        b(ExtendHandler extendHandler) {
            this.d = extendHandler;
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (intent == null) {
                LogUtil.a("MmSportSyncManager", "StepSyncCloudReceiver intent == null");
                return;
            }
            ReleaseLogUtil.b("R_MmSportSyncManager", "receiver step sync cloud.", intent.getAction());
            if (this.d != null && "com.huawei.hihealth.action_sync_total_sport_data".equals(intent.getAction())) {
                this.d.sendEmptyMessage(102);
            }
        }
    }

    private boolean b() {
        String accountInfo = LoginInit.getInstance(BaseApplication.e()).getAccountInfo(1011);
        String str = "BIND_WECHAT_RANK";
        if (!TextUtils.isEmpty(accountInfo)) {
            str = "BIND_WECHAT_RANK" + bgv.e(accountInfo);
        }
        LogUtil.c("MmSportSyncManager", "isBindWeChat key:", str);
        String e2 = SharedPreferenceManager.e(Integer.toString(10000), str, "");
        ReleaseLogUtil.b("R_MmSportSyncManager", "isBindWeChat cache:", e2);
        if (TextUtils.isEmpty(e2) || !e2.contains("_")) {
            return false;
        }
        String[] split = e2.split("_");
        if (split.length != 2) {
            return false;
        }
        return Boolean.parseBoolean(split[1]);
    }
}
