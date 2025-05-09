package defpackage;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.NetworkInfo;
import android.os.Handler;
import android.os.Looper;
import android.os.Parcelable;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.CommonUtil;
import health.compact.a.Utils;

/* loaded from: classes3.dex */
public class ezy {
    private static e c;

    public static void b(Context context) {
        if (!CommonUtil.ce() || Utils.o()) {
            return;
        }
        if (c == null) {
            c = new e();
        }
        LogUtil.a("WifiChangeReceiverHelper", "Enter registerWifiBroadcast()");
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.net.wifi.STATE_CHANGE");
        context.registerReceiver(c, intentFilter);
    }

    public static void a(Context context) {
        if (c != null) {
            LogUtil.a("WifiChangeReceiverHelper", "Enter unregisterWifiBroadcast()");
            try {
                context.unregisterReceiver(c);
            } catch (IllegalArgumentException e2) {
                LogUtil.h("WifiChangeReceiverHelper", "unregisterWifiBroadcast，IllegalArgumentException e = ", e2.getMessage());
            } catch (RuntimeException e3) {
                LogUtil.h("WifiChangeReceiverHelper", "unregisterWifiBroadcast，RuntimeException e = ", e3.getMessage());
            }
            c = null;
        }
    }

    static class e extends BroadcastReceiver {
        private boolean e;

        private e() {
            this.e = false;
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (intent == null || intent.getAction() == null) {
                return;
            }
            LogUtil.c("WifiBroadcastReceiver", "mWifiBroadcastReceiver----onReceive intent = ", intent.getAction());
            if ("android.net.wifi.STATE_CHANGE".equals(intent.getAction())) {
                Parcelable parcelableExtra = intent.getParcelableExtra("networkInfo");
                if (parcelableExtra instanceof NetworkInfo) {
                    boolean z = ((NetworkInfo) parcelableExtra).getState() == NetworkInfo.State.CONNECTED;
                    LogUtil.c("WifiBroadcastReceiver", "mWifiBroadcastReceiver----isConnected = ", Boolean.valueOf(z), ", BuildConfig.RELEASE_EVENT_LOG_UPLOAD : ", true, ", oldConnected = ", Boolean.valueOf(this.e));
                    if (z == this.e) {
                        return;
                    }
                    this.e = z;
                    if (z) {
                        d(BaseApplication.getContext());
                    }
                }
            }
        }

        private void d(final Context context) {
            LogUtil.a("WifiBroadcastReceiver", "isConnected, uploadLog.");
            new Handler(Looper.getMainLooper()).postDelayed(new Runnable() { // from class: ezy.e.3
                @Override // java.lang.Runnable
                public void run() {
                    ThreadPoolManager.d().execute(new Runnable() { // from class: ezy.e.3.3
                        @Override // java.lang.Runnable
                        public void run() {
                        }
                    });
                }
            }, 2000L);
        }
    }
}
