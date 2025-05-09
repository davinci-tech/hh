package defpackage;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.text.TextUtils;
import com.huawei.health.messagecenter.api.MessageCenterApi;
import com.huawei.hms.push.constant.RemoteMessageConst;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.BroadcastManagerUtil;
import health.compact.a.LocalBroadcast;
import health.compact.a.LogAnonymous;
import health.compact.a.Services;

/* loaded from: classes3.dex */
public class ezt {
    private static e d;

    public static void c(Context context) {
        LogUtil.a("PushTokenReceiverHelper", "Enter registerReceivePushToken()");
        d = new e();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.huawei.health.action.ACTION_RECEIVE_PUSH_TOKEN");
        BroadcastManagerUtil.bFA_(context, d, intentFilter, LocalBroadcast.c, null);
    }

    public static void a(Context context) {
        LogUtil.a("PushTokenReceiverHelper", "Enter unregisterPushTokenChange");
        try {
            context.unregisterReceiver(d);
        } catch (IllegalArgumentException e2) {
            LogUtil.b("PushTokenReceiverHelper", "unregisterPushTokenChange illegalArgumentException ", LogAnonymous.b((Throwable) e2));
        } catch (RuntimeException e3) {
            LogUtil.b("PushTokenReceiverHelper", "unregisterPushTokenChange runtimeException", LogAnonymous.b((Throwable) e3));
        }
    }

    static class e extends BroadcastReceiver {
        private e() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            LogUtil.a("PushTokenReceiver", "GetPushTokenReceiver enter");
            if (context == null || intent == null) {
                LogUtil.h("PushTokenReceiver", "GetPushTokenReceiver context or intent is null.");
                return;
            }
            if (intent.getAction() != null && "com.huawei.health.action.ACTION_RECEIVE_PUSH_TOKEN".equals(intent.getAction())) {
                String stringExtra = intent.getStringExtra(RemoteMessageConst.DEVICE_TOKEN);
                LogUtil.a("PushTokenReceiver", "GetPushTokenReceiver LocalBroadcast.ACTION_RECEIVE_PUSH_TOKEN");
                if (!TextUtils.isEmpty(stringExtra)) {
                    ((MessageCenterApi) Services.c("PluginMessageCenter", MessageCenterApi.class)).saveToken(stringExtra);
                }
            }
            ezt.a(context);
        }
    }
}
