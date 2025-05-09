package defpackage;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.os.Message;
import android.os.Process;
import android.os.RemoteException;
import android.text.TextUtils;
import com.huawei.haf.handler.ExtendHandler;
import com.huawei.haf.handler.HandlerCenter;
import com.huawei.health.IBaseCommonCallback;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.OpAnalyticsConstants;
import com.huawei.skinner.internal.OnRegisterSkinAttrsListener;
import com.huawei.syncmgr.SyncOption;
import health.compact.a.BroadcastManagerUtil;
import health.compact.a.CommonUtil;
import health.compact.a.CompileParameterUtil;
import java.util.Timer;
import java.util.TimerTask;

/* loaded from: classes.dex */
public class jtp {
    private static jtp d;
    private static final Object e = new Object();

    /* renamed from: a, reason: collision with root package name */
    private IBaseCommonCallback f14076a;
    private final BroadcastReceiver b = new BroadcastReceiver() { // from class: jtp.1
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            LogUtil.a("KitCoreSleepManager", "mNotifyRestartReceiver(): intent is ", intent.getAction());
            if (context != null) {
                if (jtp.this.f14076a != null) {
                    jtp.this.c.removeTasksAndMessages();
                    if ("com.huawei.health.action.ACTION_CORE_SLEEP_ERROR_CODE".equals(intent.getAction())) {
                        jtp.this.b(intent.getIntExtra("errorCode", -1), intent.getStringExtra("message"));
                        BaseApplication.getContext().unregisterReceiver(jtp.this.b);
                        return;
                    }
                    return;
                }
                LogUtil.h("KitCoreSleepManager", "no kit need message.");
                return;
            }
            LogUtil.h("KitCoreSleepManager", "mNotifyRestartReceiver context is null.");
        }
    };
    private ExtendHandler c;
    private Timer g;

    private jtp() {
        if (!CompileParameterUtil.a("IS_RELEASE_VERSION")) {
            String d2 = CommonUtil.d(Process.myPid());
            if (!TextUtils.isEmpty(d2) && !bfh.f349a.equals(d2)) {
                throw new RuntimeException("KitCoreSleepManager do not init in process process." + d2);
            }
        }
        this.c = HandlerCenter.yt_(new d(), "KitCoreSleepManager");
    }

    public static jtp a() {
        jtp jtpVar;
        synchronized (e) {
            if (d == null) {
                d = new jtp();
            }
            LogUtil.a("KitCoreSleepManager", "return KitCoreSleepManager");
            jtpVar = d;
        }
        return jtpVar;
    }

    public void b(IBaseCommonCallback iBaseCommonCallback) {
        LogUtil.a("KitCoreSleepManager", "enter KitCoreSleepManager startCoreSleep2");
        if (iBaseCommonCallback == null) {
            LogUtil.h("KitCoreSleepManager", "set startCoreSleep callback is null.");
            return;
        }
        this.c.removeTasksAndMessages();
        this.f14076a = iBaseCommonCallback;
        this.c.sendEmptyMessage(100, 1200000L);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.huawei.health.action.ACTION_CORE_SLEEP_ERROR_CODE");
        BroadcastManagerUtil.bFB_(BaseApplication.getContext(), this.b, intentFilter);
        LogUtil.a("KitCoreSleepManager", "kit triggle sleep sync");
        nhu.c().startSynCoreSleep(SyncOption.builder().c(), new IBaseResponseCallback() { // from class: jtp.2
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i, Object obj) {
                LogUtil.c("KitCoreSleepManager", "kit startCoreSleep : ", Integer.valueOf(i));
            }
        });
        d();
    }

    public void e(int i, String str) {
        if (this.f14076a == null) {
            LogUtil.h("KitCoreSleepManager", "no kit need message.");
            return;
        }
        this.c.removeTasksAndMessages();
        if (str == null) {
            LogUtil.h("KitCoreSleepManager", "message is null. errorCode : ", Integer.valueOf(i));
        } else {
            b(i, str);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(int i, String str) {
        IBaseCommonCallback iBaseCommonCallback = this.f14076a;
        if (iBaseCommonCallback != null) {
            LogUtil.a("KitCoreSleepManager", "errorCode : ", Integer.valueOf(i), "message : ", str);
            try {
                iBaseCommonCallback.onResponse(i, str);
            } catch (RemoteException unused) {
                LogUtil.b("KitCoreSleepManager", "toKitMessage remote exception.");
            }
            this.f14076a = null;
        } else {
            LogUtil.h("KitCoreSleepManager", "toKitMessage callback is null.");
        }
        Timer timer = this.g;
        if (timer != null) {
            timer.cancel();
            this.g = null;
        }
    }

    private void d() {
        Timer timer = this.g;
        if (timer != null) {
            timer.cancel();
        }
        Timer timer2 = new Timer("KitCoreSleepManager");
        this.g = timer2;
        timer2.schedule(new TimerTask() { // from class: jtp.4
            @Override // java.util.TimerTask, java.lang.Runnable
            public void run() {
                jtp.this.c(90, "");
            }
        }, OpAnalyticsConstants.H5_LOADING_DELAY, OpAnalyticsConstants.H5_LOADING_DELAY);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(int i, String str) {
        IBaseCommonCallback iBaseCommonCallback = this.f14076a;
        if (iBaseCommonCallback != null) {
            try {
                iBaseCommonCallback.onResponse(i, str);
            } catch (RemoteException unused) {
                LogUtil.b("KitCoreSleepManager", "toKitMessage remote exception.");
            }
        } else {
            Timer timer = this.g;
            if (timer != null) {
                timer.cancel();
                this.g = null;
            }
        }
    }

    /* loaded from: classes5.dex */
    class d implements Handler.Callback {
        private d() {
        }

        @Override // android.os.Handler.Callback
        public boolean handleMessage(Message message) {
            if (message.what == 100) {
                jtp.this.b(OnRegisterSkinAttrsListener.REGISTER_BY_SCAN, "timeout");
                return true;
            }
            LogUtil.h("KitCoreSleepManager", "no support what : ", Integer.valueOf(message.what));
            return false;
        }
    }
}
