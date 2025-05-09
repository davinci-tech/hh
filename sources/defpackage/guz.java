package defpackage;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.text.TextUtils;
import com.huawei.haf.handler.HandlerExecutor;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.hihealth.HiSyncOption;
import com.huawei.hihealth.HiUserPreference;
import com.huawei.hihealth.api.HiHealthManager;
import com.huawei.hihealth.api.HiHealthNativeApi;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.BroadcastManagerUtil;
import health.compact.a.CommonUtil;
import health.compact.a.CommonUtils;
import health.compact.a.LogAnonymous;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;

/* loaded from: classes4.dex */
public class guz {
    private int c = 2;
    private boolean e = false;
    private int j = 40;
    private int f = 180;
    private boolean d = false;
    private float g = 500.0f;

    /* renamed from: a, reason: collision with root package name */
    private boolean f12951a = true;
    private boolean b = false;
    private final BroadcastReceiver i = new BroadcastReceiver() { // from class: guz.1
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (intent == null) {
                LogUtil.b("Track_AutoTrackConfig", "mSyncAllReceiver intent is null");
            } else if ("com.huawei.hihealth.action_sync_all_data".equals(intent.getAction())) {
                guz.this.d(context);
                ReleaseLogUtil.e("Track_AutoTrackConfig", "mSyncAllReceiver mAutoTrackState ", Integer.valueOf(guz.this.c));
                guz.this.h();
            }
        }
    };

    public void d(Context context) {
        if (context == null) {
            return;
        }
        SharedPreferences sharedPreferences = context.getSharedPreferences("AutoTrack", 0);
        if (sharedPreferences != null) {
            this.e = sharedPreferences.getBoolean("BtnShow", false);
            aUs_(sharedPreferences);
            this.j = sharedPreferences.getInt("StartDelay", 40);
            this.f = sharedPreferences.getInt("StopDelay", 180);
            this.d = sharedPreferences.getBoolean("ShowDebug", false);
            this.g = sharedPreferences.getFloat("MinDistance", 500.0f);
            this.f12951a = sharedPreferences.getBoolean("showRedPoint", true);
            if (CommonUtil.aw() || CommonUtil.bf()) {
                this.e = true;
            }
        }
        this.b = mwx.d();
        LogUtil.a("Track_AutoTrackConfig", "show ", Boolean.valueOf(this.e), "  enable ", Integer.valueOf(this.c), " start ", Integer.valueOf(this.j), " stop ", Integer.valueOf(this.f));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: aUr_, reason: merged with bridge method [inline-methods] */
    public void aUs_(final SharedPreferences sharedPreferences) {
        if (sharedPreferences.contains("TrackEnable")) {
            this.c = sharedPreferences.getInt("TrackEnable", 2);
        }
        if (HandlerExecutor.c()) {
            ThreadPoolManager.d().execute(new Runnable() { // from class: gux
                @Override // java.lang.Runnable
                public final void run() {
                    guz.this.aUs_(sharedPreferences);
                }
            });
            return;
        }
        HiUserPreference userPreference = HiHealthManager.d(BaseApplication.getContext()).getUserPreference("custom.auto_track_status");
        if (userPreference == null && !SharedPreferenceManager.a("HiHealthService", "pullAllStatus", false)) {
            ReleaseLogUtil.e("Track_AutoTrackConfig", "initAutoTrackState sync not over");
            i();
            return;
        }
        if (userPreference != null && !TextUtils.isEmpty(userPreference.getValue())) {
            this.c = CommonUtils.h(userPreference.getValue());
            if (this.c != sharedPreferences.getInt("TrackEnable", 2)) {
                b(e());
                ReleaseLogUtil.e("Track_AutoTrackConfig", "updateAutoTrackStateToDaemonService mAutoTrackState = ", Integer.valueOf(this.c));
                SharedPreferences.Editor aUq_ = aUq_(BaseApplication.getContext());
                if (aUq_ != null) {
                    aUq_.putInt("TrackEnable", this.c);
                    aUq_.apply();
                } else {
                    ReleaseLogUtil.d("Track_AutoTrackConfig", "editor is null");
                    return;
                }
            }
            ReleaseLogUtil.e("Track_AutoTrackConfig", "sp value to mAutoTrackState", Integer.valueOf(this.c));
            return;
        }
        if (sharedPreferences.contains("TrackEnable")) {
            n();
            ReleaseLogUtil.e("Track_AutoTrackConfig", "setAutoTrackStateUserPreference");
        }
    }

    private void b(boolean z) {
        try {
            guz guzVar = new guz();
            Intent intent = new Intent("com.huawei.health.track.config");
            intent.setPackage("com.huawei.health");
            intent.setClassName("com.huawei.health", "com.huawei.health.manager.DaemonService");
            intent.putExtra("autotrack_enable", z);
            intent.putExtra("stop_delay", guzVar.a());
            intent.putExtra("start_delay", guzVar.d());
            BaseApplication.getContext().startService(intent);
        } catch (IllegalStateException | NoSuchMethodError | SecurityException e) {
            ReleaseLogUtil.c("Track_AutoTrackConfig", "sendDaemonServiceBroadcast Exception", LogAnonymous.b(e));
        }
    }

    public void a(Context context) {
        SharedPreferences.Editor aUq_;
        if (context == null || (aUq_ = aUq_(context)) == null) {
            return;
        }
        aUq_.putBoolean("BtnShow", this.e);
        aUq_.putInt("TrackEnable", this.c);
        n();
        aUq_.putBoolean("ShowDebug", this.d);
        aUq_.putInt("StartDelay", this.j);
        aUq_.putInt("StopDelay", this.f);
        aUq_.putFloat("MinDistance", c());
        aUq_.apply();
        LogUtil.a("Track_AutoTrackConfig", "saveConfigToFile success");
    }

    private SharedPreferences.Editor aUq_(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("AutoTrack", 0);
        if (sharedPreferences == null) {
            return null;
        }
        return sharedPreferences.edit();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void n() {
        if (HandlerExecutor.c()) {
            ThreadPoolManager.d().execute(new Runnable() { // from class: guy
                @Override // java.lang.Runnable
                public final void run() {
                    guz.this.n();
                }
            });
            return;
        }
        HiUserPreference hiUserPreference = new HiUserPreference();
        hiUserPreference.setKey("custom.auto_track_status");
        hiUserPreference.setValue(String.valueOf(this.c));
        HiHealthManager.d(BaseApplication.getContext()).setUserPreference(hiUserPreference);
        HiSyncOption hiSyncOption = new HiSyncOption();
        hiSyncOption.setSyncModel(2);
        hiSyncOption.setSyncAction(0);
        hiSyncOption.setSyncDataType(20000);
        hiSyncOption.setSyncScope(1);
        hiSyncOption.setSyncMethod(2);
        HiHealthNativeApi.a(BaseApplication.getContext()).synCloud(hiSyncOption, null);
        ReleaseLogUtil.e("Track_AutoTrackConfig", "setAutoTrackStateUserPreference mAutoTrackState:", Integer.valueOf(this.c));
    }

    public boolean b() {
        return this.e;
    }

    public void e(Context context, boolean z) {
        if (z) {
            this.c = 1;
            e(context);
        } else {
            this.c = 2;
        }
    }

    private void e(Context context) {
        if (context == null) {
            LogUtil.h("Track_AutoTrackConfig", "setAutoIdentifyRecordsStartTime context is null");
        } else if (TextUtils.isEmpty(SharedPreferenceManager.b(context, String.valueOf(20002), "first_open_auto_identify_record_switch_time"))) {
            SharedPreferenceManager.e(context, String.valueOf(20002), "first_open_auto_identify_record_switch_time", String.valueOf(System.currentTimeMillis()), (StorageParams) null);
        }
    }

    public void i() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.huawei.hihealth.action_sync_all_data");
        BroadcastManagerUtil.bFD_(BaseApplication.getContext(), this.i, intentFilter);
    }

    public void h() {
        try {
            BaseApplication.getContext().unregisterReceiver(this.i);
        } catch (IllegalArgumentException unused) {
            LogUtil.b("Track_AutoTrackConfig", "unregister broadcast sync all data IllegalArgumentException.");
        }
    }

    public boolean e() {
        return this.c == 1;
    }

    public int d() {
        return this.j;
    }

    public int a() {
        return this.f;
    }

    public boolean j() {
        return this.d;
    }

    public float c() {
        if (this.b) {
            return 500.0f;
        }
        return this.g;
    }

    public boolean g() {
        return this.f12951a;
    }

    public void a(boolean z) {
        this.f12951a = z;
        e("showRedPoint", z);
    }

    public void e(String str, boolean z) {
        SharedPreferences.Editor aUq_ = aUq_(BaseApplication.getContext());
        if (aUq_ == null) {
            ReleaseLogUtil.d("Track_AutoTrackConfig", "saveSp editor is null");
        } else {
            aUq_.putBoolean(str, z);
            aUq_.apply();
        }
    }

    public boolean f() {
        return this.b;
    }
}
