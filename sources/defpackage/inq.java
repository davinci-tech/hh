package defpackage;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.RemoteException;
import android.os.SystemClock;
import com.huawei.haf.application.BaseApplication;
import com.huawei.hihealth.HiSyncOption;
import com.huawei.hihealth.ICommonCallback;
import com.huawei.hihealth.api.HiHealthNativeApi;
import com.huawei.hihealth.util.HiJsonUtil;
import health.compact.a.BroadcastManagerUtil;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes7.dex */
public class inq {
    private static final AtomicBoolean d = new AtomicBoolean(false);
    private Map<Long, ICommonCallback> e = new HashMap();
    private BroadcastReceiver b = new BroadcastReceiver() { // from class: inq.4
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (intent == null) {
                ReleaseLogUtil.c("R_HiHealthKitSyncDataHelper", "SyncDataReceiver intent is null");
                return;
            }
            long longExtra = intent.getLongExtra("sync_data_result_id", 0L);
            if (inq.this.e.containsKey(Long.valueOf(longExtra))) {
                boolean booleanExtra = intent.getBooleanExtra("sync_data_result_success", false);
                ReleaseLogUtil.e("R_HiHealthKitSyncDataHelper", "receive syncId: ", Long.valueOf(longExtra), " sync result: ", Boolean.valueOf(booleanExtra));
                ICommonCallback iCommonCallback = (ICommonCallback) inq.this.e.get(Long.valueOf(longExtra));
                inq.this.e.remove(Long.valueOf(longExtra));
                try {
                    if (booleanExtra) {
                        iCommonCallback.onResult(0, ipd.b(0));
                    } else {
                        iCommonCallback.onResult(1022, ipd.b(1022));
                    }
                } catch (RemoteException e) {
                    ReleaseLogUtil.c("R_HiHealthKitSyncDataHelper", "SyncDataReceiver RemoteException: ", e.getMessage());
                }
            }
            if (inq.this.e.isEmpty() && inq.d.compareAndSet(true, false)) {
                inq.this.a();
            }
        }
    };

    static class d {
        private static final inq c = new inq();
    }

    public static inq b() {
        return d.c;
    }

    public void e(int[] iArr, ICommonCallback iCommonCallback) {
        ReleaseLogUtil.e("R_HiHealthKitSyncDataHelper", "enter syncDataTypes: ", HiJsonUtil.e(iArr));
        if (d.compareAndSet(false, true)) {
            c();
        }
        long elapsedRealtimeNanos = SystemClock.elapsedRealtimeNanos();
        this.e.put(Long.valueOf(elapsedRealtimeNanos), iCommonCallback);
        HiSyncOption hiSyncOption = new HiSyncOption();
        hiSyncOption.setSyncModel(2);
        hiSyncOption.setSyncAction(0);
        hiSyncOption.setSyncDataTypes(c(iArr));
        hiSyncOption.setSyncMethod(2);
        hiSyncOption.setForceSync(true);
        hiSyncOption.setSyncId(elapsedRealtimeNanos);
        HiHealthNativeApi.a(BaseApplication.e()).synCloud(hiSyncOption, null);
    }

    private void c() {
        ReleaseLogUtil.e("R_HiHealthKitSyncDataHelper", "register sync receiver");
        BroadcastManagerUtil.bFA_(BaseApplication.e(), this.b, new IntentFilter("com.huawei.hihealth.action_sync_data_result"), "com.huawei.hihealth.DEFAULT_PERMISSION", null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a() {
        ReleaseLogUtil.e("R_HiHealthKitSyncDataHelper", "unregister sync receiver");
        BaseApplication.e().unregisterReceiver(this.b);
    }

    private List<Integer> c(int[] iArr) {
        ArrayList arrayList = new ArrayList();
        for (int i : iArr) {
            arrayList.add(Integer.valueOf(i));
        }
        return arrayList;
    }
}
