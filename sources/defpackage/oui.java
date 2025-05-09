package defpackage;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.text.TextUtils;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.handler.HandlerExecutor;
import com.huawei.hihealth.HiHealthClient;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.HiSyncOption;
import com.huawei.hihealth.api.HiHealthManager;
import com.huawei.hihealth.api.HiHealthNativeApi;
import com.huawei.hihealth.data.listener.HiCommonListener;
import com.huawei.hihealth.data.listener.HiSubscribeListener;
import com.huawei.hihealth.data.listener.HiUnSubscribeListener;
import com.huawei.hihealth.util.HiBroadcastUtil;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes6.dex */
public class oui {

    /* renamed from: a, reason: collision with root package name */
    private IBaseResponseCallback f15957a;
    private List<Integer> b;
    private BroadcastReceiver e;
    private boolean d = false;
    private boolean c = true;

    public void a(IBaseResponseCallback iBaseResponseCallback) {
        this.e = new b(this);
        this.f15957a = iBaseResponseCallback;
        g();
        h();
        c(0);
    }

    private void h() {
        pit.a();
        ArrayList arrayList = new ArrayList(16);
        arrayList.add(103);
        HiHealthNativeApi.a(BaseApplication.e()).subscribeHiHealthData(arrayList, new e(this));
    }

    public void c() {
        j();
        if (this.b != null) {
            HiHealthNativeApi.a(BaseApplication.e()).unSubscribeHiHealthData(this.b, new HiUnSubscribeListener() { // from class: oul
                @Override // com.huawei.hihealth.data.listener.HiUnSubscribeListener
                public final void onResult(boolean z) {
                    ReleaseLogUtil.e("StepCardSyncHelper", "unSubscribeSportData isSuccess = ", Boolean.valueOf(z));
                }
            });
        }
    }

    private void g() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.huawei.hihealth.action_data_refresh");
        intentFilter.addAction("com.huawei.hihealth.action_sync_total_sport_data");
        intentFilter.addAction("com.huawei.hihealth.action_sync");
        intentFilter.addAction("android.intent.action.SCREEN_ON");
        intentFilter.addAction("com.huawei.plugin.account.logout");
        LocalBroadcastManager.getInstance(BaseApplication.e()).registerReceiver(this.e, intentFilter);
        this.d = true;
        LogUtil.c("StepCardSyncHelper", "mHiBroadcastReceiver register");
    }

    private void j() {
        if (this.e == null || !this.d) {
            return;
        }
        try {
            LocalBroadcastManager.getInstance(BaseApplication.e()).unregisterReceiver(this.e);
            LogUtil.c("StepCardSyncHelper", "mHiBroadcastReceiver unregister");
        } catch (IllegalArgumentException unused) {
            LogUtil.c("StepCardSyncHelper", "IllegalArgumentException mHiBroadcastReceiver unregister");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void a() {
        SharedPreferenceManager.e(Integer.toString(10000), "step_achieved_show_time", 0L);
        SharedPreferenceManager.e(Integer.toString(10000), "intensity_achieved_show_time", 0L);
        SharedPreferenceManager.e(Integer.toString(10000), "active_achieved_show_time", 0L);
        SharedPreferenceManager.e(Integer.toString(10000), "achieved_show_time", 0L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(int i) {
        if (i == 2 || i == 3) {
            this.c = false;
        }
        if (i == 2) {
            c(0);
            if (TextUtils.isEmpty(SharedPreferenceManager.b(BaseApplication.e(), Integer.toString(10000), "first_day_run_record"))) {
                d();
            }
        }
    }

    public void d() {
        HiBroadcastUtil.i(BaseApplication.e());
        HandlerExecutor.d(new Runnable() { // from class: oug
            @Override // java.lang.Runnable
            public final void run() {
                oui.this.b();
            }
        }, 3000L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b() {
        HiSyncOption hiSyncOption = new HiSyncOption();
        hiSyncOption.setSyncModel(2);
        hiSyncOption.setSyncAction(4);
        hiSyncOption.setSyncDataType(10004);
        hiSyncOption.setSyncScope(1);
        hiSyncOption.setSyncMethod(2);
        LogUtil.a("StepCardSyncHelper", "syncSportData !");
        HiHealthManager.d(BaseApplication.e()).synCloud(hiSyncOption, new HiCommonListener() { // from class: oui.3
            @Override // com.huawei.hihealth.data.listener.HiCommonListener
            public void onSuccess(int i, Object obj) {
                LogUtil.a("StepCardSyncHelper", "syncSportData onSuccess!");
            }

            @Override // com.huawei.hihealth.data.listener.HiCommonListener
            public void onFailure(int i, Object obj) {
                LogUtil.h("StepCardSyncHelper", "syncSportData onFailure!");
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(int i) {
        this.f15957a.d(i, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(int i, String str) {
        this.f15957a.d(i, str);
    }

    static class b extends BroadcastReceiver {
        private final oui b;

        b(oui ouiVar) {
            this.b = (oui) new WeakReference(ouiVar).get();
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (intent == null) {
                ReleaseLogUtil.d("StepCardSyncHelper", "StepCardData onReceive intent=null");
                return;
            }
            if (this.b == null) {
                ReleaseLogUtil.c("StepCardSyncHelper", "mCallback is null");
                return;
            }
            if ("com.huawei.hihealth.action_data_refresh".equals(intent.getAction())) {
                int intExtra = intent.getIntExtra("refresh_type", -1);
                ReleaseLogUtil.e("StepCardSyncHelper", "StepsCard onReceive HiRefreshType ", Integer.valueOf(intExtra));
                if (intExtra == 6) {
                    this.b.c(0);
                    return;
                }
                return;
            }
            if ("com.huawei.hihealth.action_sync_total_sport_data".equals(intent.getAction())) {
                ReleaseLogUtil.e("StepCardSyncHelper", "StepsCard onReceive ACTION_SYNC_TOTAL_SPORT_DATA");
                this.b.c(1);
                return;
            }
            if ("com.huawei.hihealth.action_sync".equals(intent.getAction())) {
                int intExtra2 = intent.getIntExtra("com.huawei.hihealth.action_sync_status", 6);
                ReleaseLogUtil.e("StepCardSyncHelper", "status is ", Integer.valueOf(intExtra2));
                this.b.d(intExtra2);
            } else if ("com.huawei.plugin.account.logout".equals(intent.getAction())) {
                ReleaseLogUtil.e("StepCardSyncHelper", "StepsCard onReceive logout");
                oui.a();
                this.b.c = true;
                this.b.c(2);
            }
        }
    }

    static class e implements HiSubscribeListener {

        /* renamed from: a, reason: collision with root package name */
        private WeakReference<oui> f15958a;

        e(oui ouiVar) {
            this.f15958a = new WeakReference<>(ouiVar);
        }

        @Override // com.huawei.hihealth.data.listener.HiSubscribeListener
        public void onResult(List<Integer> list, List<Integer> list2) {
            oui ouiVar = this.f15958a.get();
            if (ouiVar == null) {
                LogUtil.h("StepCardSyncHelper", "onResult logicalStepCounter == null");
            } else {
                if (koq.b(list)) {
                    return;
                }
                ReleaseLogUtil.e("StepCardSyncHelper", "SportDataCallback success");
                ouiVar.b = list;
            }
        }

        @Override // com.huawei.hihealth.data.listener.HiSubscribeListener
        public void onChange(int i, HiHealthClient hiHealthClient, String str, HiHealthData hiHealthData, long j) {
            Object[] objArr = new Object[10];
            objArr[0] = "SportIntensityCallback ";
            objArr[1] = hiHealthData == null ? null : hiHealthData.toString();
            objArr[2] = " type ";
            objArr[3] = Integer.valueOf(i);
            objArr[4] = " client ";
            objArr[5] = hiHealthClient != null ? hiHealthClient.getHuid() : null;
            objArr[6] = "changeKey ";
            objArr[7] = str;
            objArr[8] = " time ";
            objArr[9] = Long.valueOf(j);
            LogUtil.a("StepCardSyncHelper", objArr);
            if (i == 103) {
                oui ouiVar = this.f15958a.get();
                if (ouiVar == null) {
                    LogUtil.h("StepCardSyncHelper", "logicalStepCounter is null");
                } else if ("900200006".equals(str) || "900200008".equals(str) || "900200009".equals(str) || "900200007".equals(str)) {
                    ouiVar.d(3, str);
                }
            }
        }
    }
}
