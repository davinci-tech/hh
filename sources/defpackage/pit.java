package defpackage;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.application.BroadcastManager;
import com.huawei.hihealth.HiHealthClient;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.api.HiHealthNativeApi;
import com.huawei.hihealth.data.listener.HiSubscribeListener;
import com.huawei.hihealth.data.listener.HiUnSubscribeListener;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.BroadcastManagerUtil;
import health.compact.a.HiCommonUtil;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/* loaded from: classes6.dex */
public class pit {
    private static volatile pit b;
    private static final Object e = new Object();

    /* renamed from: a, reason: collision with root package name */
    private HashMap<String, Integer> f16147a;
    private BroadcastReceiver c;
    private List<Integer> g;
    private BroadcastReceiver h;
    private final CopyOnWriteArrayList<IBaseResponseCallback> i = new CopyOnWriteArrayList<>();
    private int d = 0;

    public static pit a() {
        if (b == null) {
            synchronized (e) {
                if (b == null) {
                    b = new pit();
                }
            }
        }
        return b;
    }

    public void c(IBaseResponseCallback iBaseResponseCallback) {
        HashMap<String, Integer> hashMap;
        HashMap<String, Integer> hashMap2;
        if (iBaseResponseCallback == null) {
            ReleaseLogUtil.c("SCUI_SportTargetSearchHelper", "callback is null in getActiveGoals");
            return;
        }
        if (this.d == 2 && (hashMap2 = this.f16147a) != null && hashMap2.size() == 4) {
            LogUtil.a("SCUI_SportTargetSearchHelper", "have searched");
            iBaseResponseCallback.d(0, this.f16147a);
            return;
        }
        this.i.add(iBaseResponseCallback);
        int i = this.d;
        if (i == 1) {
            return;
        }
        if (i == 0 || !((hashMap = this.f16147a) == null || hashMap.size() == 4)) {
            d();
        }
    }

    public void b() {
        ReleaseLogUtil.e("SCUI_SportTargetSearchHelper", "ActiveTargetSearchHelper destroy");
        e();
        BroadcastReceiver broadcastReceiver = this.h;
        if (broadcastReceiver != null) {
            BroadcastManager.wz_(broadcastReceiver);
            this.h = null;
        }
        if (this.c != null) {
            BroadcastManagerUtil.bFK_(BaseApplication.e(), this.c);
            this.c = null;
        }
        b = null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e() {
        if (this.g != null) {
            HiHealthNativeApi.a(BaseApplication.e()).unSubscribeHiHealthData(this.g, new HiUnSubscribeListener() { // from class: pir
                @Override // com.huawei.hihealth.data.listener.HiUnSubscribeListener
                public final void onResult(boolean z) {
                    ReleaseLogUtil.e("SCUI_SportTargetSearchHelper", "unSubscribeSportData isSuccess = ", Boolean.valueOf(z));
                }
            });
        }
    }

    private pit() {
        c();
        if (this.h == null) {
            this.h = new BroadcastReceiver() { // from class: pit.3
                @Override // android.content.BroadcastReceiver
                public void onReceive(Context context, Intent intent) {
                    String stringExtra = intent.getStringExtra(HiCommonUtil.b);
                    boolean booleanExtra = intent.getBooleanExtra(HiCommonUtil.d, false);
                    LogUtil.a("SCUI_SportTargetSearchHelper", "processName: ", stringExtra, " isRunning: ", Boolean.valueOf(booleanExtra), " time: ", Long.valueOf(intent.getLongExtra("time", 0L)));
                    if ("com.huawei.health:DaemonService".equals(stringExtra) && booleanExtra) {
                        ReleaseLogUtil.e("SCUI_SportTargetSearchHelper", "DaemonService restart, subscribe again");
                        pit.this.e();
                        pit.this.c();
                    }
                }
            };
        }
        BroadcastManager.wl_(this.h);
        IntentFilter intentFilter = new IntentFilter("com.huawei.hihealth.action_account_login_datas_switch_finish");
        if (this.c == null) {
            this.c = new BroadcastReceiver() { // from class: pit.5
                @Override // android.content.BroadcastReceiver
                public void onReceive(Context context, Intent intent) {
                    ReleaseLogUtil.e("SCUI_SportTargetSearchHelper", "ACTION_ACCOUNT_LOGIN_DATAS_SWITCH_FINISH");
                    pit.this.d();
                }
            };
        }
        BroadcastManagerUtil.bFE_(BaseApplication.e(), this.c, intentFilter);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c() {
        ArrayList arrayList = new ArrayList(16);
        arrayList.add(103);
        HiHealthNativeApi.a(BaseApplication.e()).subscribeHiHealthData(arrayList, new e(this));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(int i, Object obj) {
        d(i, obj);
        if (!(obj instanceof HashMap)) {
            LogUtil.h("SCUI_SportTargetSearchHelper", "arrive is not instanceof HashMap");
            return;
        }
        this.f16147a = (HashMap) obj;
        LogUtil.h("SCUI_SportTargetSearchHelper", "Searched over");
        this.d = 2;
    }

    private void d(int i, Object obj) {
        if (koq.c(this.i)) {
            Iterator<IBaseResponseCallback> it = this.i.iterator();
            while (it.hasNext()) {
                IBaseResponseCallback next = it.next();
                if (next == null) {
                    ReleaseLogUtil.c("SCUI_SportTargetSearchHelper", "callback is null in response");
                } else {
                    LogUtil.h("SCUI_SportTargetSearchHelper", "onResponse now");
                    next.d(i, obj);
                }
            }
            this.i.clear();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d() {
        LogUtil.a("SCUI_SportTargetSearchHelper", "start searching");
        this.d = 1;
        ArrayList arrayList = new ArrayList(4);
        arrayList.add("900200006");
        arrayList.add("900200008");
        arrayList.add("900200009");
        arrayList.add("900200007");
        nip.a(arrayList, new c());
    }

    static class c implements IBaseResponseCallback {
        private WeakReference<pit> d;

        private c(pit pitVar) {
            this.d = new WeakReference<>(pitVar);
        }

        @Override // com.huawei.hwbasemgr.IBaseResponseCallback
        /* renamed from: onResponse */
        public void d(int i, Object obj) {
            Object[] objArr = new Object[2];
            objArr[0] = "SportAchievementGoalDataCallback ";
            objArr[1] = obj == null ? null : obj.toString();
            LogUtil.a("SCUI_SportTargetSearchHelper", objArr);
            pit pitVar = this.d.get();
            if (pitVar != null) {
                pitVar.c(i, obj);
            } else {
                ReleaseLogUtil.d("SCUI_SportTargetSearchHelper", "SportAchievementGoalDataCallback cardData is null ");
            }
        }
    }

    static class e implements HiSubscribeListener {
        private WeakReference<pit> d;

        e(pit pitVar) {
            this.d = new WeakReference<>(pitVar);
        }

        @Override // com.huawei.hihealth.data.listener.HiSubscribeListener
        public void onResult(List<Integer> list, List<Integer> list2) {
            pit pitVar = this.d.get();
            if (pitVar == null) {
                LogUtil.h("SCUI_SportTargetSearchHelper", "onResult logicalStepCounter == null");
            } else {
                if (koq.b(list)) {
                    return;
                }
                ReleaseLogUtil.e("SCUI_SportTargetSearchHelper", "SportDataCallback success");
                pitVar.g = list;
            }
        }

        @Override // com.huawei.hihealth.data.listener.HiSubscribeListener
        public void onChange(int i, HiHealthClient hiHealthClient, String str, HiHealthData hiHealthData, long j) {
            Object[] objArr = new Object[10];
            objArr[0] = "ConfigGoalCallback ";
            objArr[1] = hiHealthData == null ? null : hiHealthData.toString();
            objArr[2] = " type ";
            objArr[3] = Integer.valueOf(i);
            objArr[4] = " client ";
            objArr[5] = hiHealthClient != null ? hiHealthClient.getHuid() : null;
            objArr[6] = "changeKey ";
            objArr[7] = str;
            objArr[8] = " time ";
            objArr[9] = Long.valueOf(j);
            LogUtil.a("SCUI_SportTargetSearchHelper", objArr);
            if (i == 103) {
                pit pitVar = this.d.get();
                if (pitVar == null) {
                    LogUtil.h("SCUI_SportTargetSearchHelper", "logicalStepCounter is null");
                } else if ("900200006".equals(str) || "900200008".equals(str) || "900200009".equals(str) || "900200007".equals(str)) {
                    pitVar.d();
                }
            }
        }
    }
}
