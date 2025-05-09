package defpackage;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.text.TextUtils;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.huawei.health.messagecenter.model.IpushBase;
import com.huawei.health.messagecenter.model.MessagePushBean;
import com.huawei.hihealth.api.SyncApi;
import com.huawei.hihealth.util.HealthSyncUtil;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.ReleaseLogUtil;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;
import java.util.Arrays;
import java.util.List;

/* loaded from: classes.dex */
public class ezs implements IpushBase {

    /* renamed from: a, reason: collision with root package name */
    private d f12401a;
    private Context e;

    @Override // com.huawei.health.messagecenter.model.IpushBase
    public List<String> getPushType() {
        return Arrays.asList("8002");
    }

    @Override // com.huawei.health.messagecenter.model.IpushBase
    public void processPushMsg(Context context, String str) {
        MessagePushBean messagePushBean;
        LogUtil.a("SyncDataPushReceiver", "enter processPushMsg");
        if (context == null) {
            ReleaseLogUtil.a("HiH_SyncDataPushReceiver", "processPushMsg context is null");
            return;
        }
        if (TextUtils.isEmpty(str)) {
            ReleaseLogUtil.a("HiH_SyncDataPushReceiver", "processPushMsg push message is Empty");
            return;
        }
        ReleaseLogUtil.b("HiH_SyncDataPushReceiver", "processPushMsg message: ", str);
        try {
            messagePushBean = (MessagePushBean) new Gson().fromJson(str, MessagePushBean.class);
        } catch (JsonSyntaxException unused) {
            ReleaseLogUtil.c("HiH_SyncDataPushReceiver", "processPushMsg JsonSyntaxException");
        }
        if (messagePushBean == null) {
            LogUtil.h("SyncDataPushReceiver", "processPushMsg MessagePushBean is null");
            return;
        }
        if (messagePushBean.getPushType() != Integer.valueOf("8002").intValue()) {
            LogUtil.h("SyncDataPushReceiver", "processPushMsg pushType invaild");
            return;
        }
        this.e = context;
        c();
        d();
        b();
    }

    private void c() {
        ReleaseLogUtil.b("HiH_SyncDataPushReceiver", "enter registerSyncReceiver");
        if (this.f12401a == null) {
            this.f12401a = new d();
        }
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.huawei.hihealth.action_sync");
        LocalBroadcastManager.getInstance(this.e).registerReceiver(this.f12401a, intentFilter);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e() {
        ReleaseLogUtil.b("HiH_SyncDataPushReceiver", "enter unregisterSyncReceiver");
        if (this.f12401a != null) {
            LocalBroadcastManager.getInstance(this.e).unregisterReceiver(this.f12401a);
            this.f12401a = null;
        }
    }

    private void d() {
        long currentTimeMillis = System.currentTimeMillis();
        jfq.c().d(new IBaseResponseCallback() { // from class: ezs.3
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i, Object obj) {
                LogUtil.a("SyncDataPushReceiver", "work out sync errorCode: ", Integer.valueOf(i));
            }
        });
        LogUtil.a("SyncDataPushReceiver", "deviceDataSync finished, time cost: ", Long.valueOf(System.currentTimeMillis() - currentTimeMillis));
    }

    private void b() {
        long currentTimeMillis = System.currentTimeMillis();
        try {
            HealthSyncUtil.b(SyncApi.FITNESS);
        } catch (IllegalArgumentException unused) {
            ReleaseLogUtil.c("HiH_SyncDataPushReceiver", "FitnessAdvice syncData failed");
        }
        try {
            HealthSyncUtil.b(SyncApi.HEALTH_LIFE);
        } catch (IllegalArgumentException unused2) {
            ReleaseLogUtil.c("HiH_SyncDataPushReceiver", "HealthLifeModel syncData failed");
        }
        gou.c(0, 20000, 0);
        LogUtil.a("SyncDataPushReceiver", "cloudSync finished, time cost: ", Long.valueOf(System.currentTimeMillis() - currentTimeMillis));
    }

    /* loaded from: classes3.dex */
    class d extends BroadcastReceiver {
        d() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            LogUtil.c("SyncDataPushReceiver", "enter syncBroadcastReceiver");
            if (intent == null) {
                ReleaseLogUtil.a("HiH_SyncDataPushReceiver", "onReceive intent is null");
                return;
            }
            if ("com.huawei.hihealth.action_sync".equals(intent.getAction())) {
                int intExtra = intent.getIntExtra("com.huawei.hihealth.action_sync_status", 6);
                ReleaseLogUtil.b("HiH_SyncDataPushReceiver", "action sync status = ", Integer.valueOf(intExtra));
                if (intExtra == 2) {
                    long currentTimeMillis = System.currentTimeMillis();
                    gou.c(currentTimeMillis);
                    SharedPreferenceManager.e(context, Integer.toString(10000), "last_sync_time", String.valueOf(currentTimeMillis), new StorageParams());
                    ezs.this.e();
                }
            }
        }
    }
}
