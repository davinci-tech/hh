package com.huawei.ui.homewear21.home;

import android.content.ActivityNotFoundException;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.os.Parcelable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CompoundButton;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.health.R;
import com.huawei.health.devicemgr.business.entity.DeviceCapability;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.dialog.NoTitleCustomAlertDialog;
import com.huawei.ui.device.activity.music.MusicMainActivity;
import com.huawei.ui.device.activity.notification.NotificationTipActivity;
import com.huawei.ui.device.interactors.DeviceSettingsInteractors;
import com.huawei.ui.device.interactors.NotificationPushInteractor;
import com.huawei.ui.device.views.devicesettings.DeviceSettingFactoryBaseAdapter;
import com.huawei.ui.homewear21.home.MusicSecondaryMenuActivity;
import defpackage.jjd;
import defpackage.jjj;
import defpackage.jki;
import defpackage.nsn;
import defpackage.obz;
import defpackage.oxa;
import defpackage.oxd;
import defpackage.oyf;
import defpackage.ozf;
import defpackage.ozh;
import defpackage.ozj;
import health.compact.a.BroadcastManagerUtil;
import health.compact.a.LocalBroadcast;
import java.lang.ref.WeakReference;
import java.util.List;
import java.util.Vector;

/* loaded from: classes6.dex */
public class MusicSecondaryMenuActivity extends BaseActivity {

    /* renamed from: a, reason: collision with root package name */
    private Context f9641a;
    private Context b;
    private DeviceSettingsInteractors g;
    private DeviceSettingFactoryBaseAdapter i;
    private ListView m;
    private RelativeLayout x;
    private NotificationPushInteractor y;
    private final Object e = new Object();
    private volatile List<obz> l = new Vector(0);
    private String h = "";
    private DeviceCapability j = null;
    private volatile boolean o = true;
    private DeviceInfo c = null;
    private oxd u = null;
    private c w = null;
    private HandlerThread n = null;
    private boolean s = false;
    private boolean t = false;
    private Handler p = new b(this);
    private final AdapterView.OnItemClickListener f = new AdapterView.OnItemClickListener() { // from class: com.huawei.ui.homewear21.home.MusicSecondaryMenuActivity.1
        @Override // android.widget.AdapterView.OnItemClickListener
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
            LogUtil.a("MusicSecondaryMenu", 0, "MusicSecondaryMenuActivity", "Genera onItemClick: parent is ", adapterView, ",view is ", view, ",position is ", Integer.valueOf(i), "idLong is ", Long.valueOf(j));
            if (!nsn.a(500)) {
                if (i >= MusicSecondaryMenuActivity.this.c().size() || i < 0) {
                    ViewClickInstrumentation.clickOnListView(adapterView, view, i);
                    return;
                }
                int e2 = ((obz) MusicSecondaryMenuActivity.this.c().get(i)).e();
                LogUtil.a("MusicSecondaryMenu", 1, "MusicSecondaryMenuActivity", "onItemClick: id is ", Integer.valueOf(e2));
                if (e2 == 110) {
                    if (jjj.g()) {
                        MusicSecondaryMenuActivity.this.x.setVisibility(0);
                        MusicSecondaryMenuActivity.this.m.setVisibility(8);
                        jjd b2 = jjd.b(BaseApplication.getContext());
                        MusicSecondaryMenuActivity musicSecondaryMenuActivity = MusicSecondaryMenuActivity.this;
                        b2.e(musicSecondaryMenuActivity.new e(musicSecondaryMenuActivity));
                    } else {
                        Intent intent = new Intent();
                        intent.putExtra("device_id", MusicSecondaryMenuActivity.this.h);
                        intent.setClass(view.getContext(), MusicMainActivity.class);
                        MusicSecondaryMenuActivity.this.startActivity(intent);
                    }
                } else {
                    LogUtil.h("MusicSecondaryMenu", 0, "MusicSecondaryMenuActivity", "Enter default");
                }
                ViewClickInstrumentation.clickOnListView(adapterView, view, i);
                return;
            }
            LogUtil.a("MusicSecondaryMenuActivity", "isFastDoubleClick");
            ViewClickInstrumentation.clickOnListView(adapterView, view, i);
        }
    };
    private final CompoundButton.OnCheckedChangeListener q = new CompoundButton.OnCheckedChangeListener() { // from class: com.huawei.ui.homewear21.home.MusicSecondaryMenuActivity.3
        @Override // android.widget.CompoundButton.OnCheckedChangeListener
        public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
            LogUtil.a("MusicSecondaryMenuActivity", "MusicControl is onCheckedChanged isChecked is ", Boolean.valueOf(z));
            if (z && !MusicSecondaryMenuActivity.this.y.e() && MusicSecondaryMenuActivity.this.p != null) {
                MusicSecondaryMenuActivity.this.p.sendEmptyMessageDelayed(1, 100L);
                MusicSecondaryMenuActivity.this.startActivity(new Intent("android.settings.ACTION_NOTIFICATION_LISTENER_SETTINGS"));
            }
            MusicSecondaryMenuActivity musicSecondaryMenuActivity = MusicSecondaryMenuActivity.this;
            musicSecondaryMenuActivity.c(musicSecondaryMenuActivity.c(), 34).e(z);
            jjd.b(MusicSecondaryMenuActivity.this.f9641a).c(z, true);
            ViewClickInstrumentation.clickOnView(compoundButton);
        }
    };
    private final IBaseResponseCallback r = new IBaseResponseCallback() { // from class: oxe
        @Override // com.huawei.hwbasemgr.IBaseResponseCallback
        /* renamed from: onResponse */
        public final void d(int i, Object obj) {
            MusicSecondaryMenuActivity.this.d(i, obj);
        }
    };
    private d k = new d(this) { // from class: com.huawei.ui.homewear21.home.MusicSecondaryMenuActivity.4
        @Override // com.huawei.ui.homewear21.home.MusicSecondaryMenuActivity.d, java.lang.Runnable
        public void run() {
            MusicSecondaryMenuActivity e2 = e();
            if (e2 != null) {
                e2.s();
            }
        }
    };
    private BroadcastReceiver d = new BroadcastReceiver() { // from class: com.huawei.ui.homewear21.home.MusicSecondaryMenuActivity.2
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (intent == null) {
                LogUtil.h("MusicSecondaryMenuActivity", "intent is null!");
                return;
            }
            Parcelable parcelableExtra = intent.getParcelableExtra("deviceinfo");
            DeviceInfo deviceInfo = parcelableExtra instanceof DeviceInfo ? (DeviceInfo) parcelableExtra : null;
            if (deviceInfo == null) {
                LogUtil.h("MusicSecondaryMenuActivity", "deviceInfo is null!");
                return;
            }
            if (!jjj.e(deviceInfo)) {
                LogUtil.h("MusicSecondaryMenuActivity", "This device does not have the correspond capability.");
                return;
            }
            if (deviceInfo.getDeviceConnectState() == 2) {
                MusicSecondaryMenuActivity musicSecondaryMenuActivity = MusicSecondaryMenuActivity.this;
                musicSecondaryMenuActivity.c(musicSecondaryMenuActivity.c(), 110).d(true);
                MusicSecondaryMenuActivity.this.i.notifyDataSetChanged();
            } else {
                MusicSecondaryMenuActivity musicSecondaryMenuActivity2 = MusicSecondaryMenuActivity.this;
                musicSecondaryMenuActivity2.c(musicSecondaryMenuActivity2.c(), 110).d(false);
                MusicSecondaryMenuActivity.this.i.notifyDataSetChanged();
            }
        }
    };

    static class b extends Handler {
        private final WeakReference<MusicSecondaryMenuActivity> e;

        b(MusicSecondaryMenuActivity musicSecondaryMenuActivity) {
            this.e = new WeakReference<>(musicSecondaryMenuActivity);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            MusicSecondaryMenuActivity musicSecondaryMenuActivity = this.e.get();
            if (musicSecondaryMenuActivity == null) {
                return;
            }
            if (musicSecondaryMenuActivity.isFinishing()) {
                LogUtil.a("MusicSecondaryMenu", 0, "MusicSecondaryMenuActivity", "Activity is finishing or null.");
                return;
            }
            int i = message.what;
            if (i == 1) {
                LogUtil.a("MusicSecondaryMenuActivity", "handleMessage，SHOW_TIP");
                Intent intent = new Intent();
                intent.setClass(musicSecondaryMenuActivity.b, NotificationTipActivity.class);
                try {
                    musicSecondaryMenuActivity.b.startActivity(intent);
                    return;
                } catch (ActivityNotFoundException unused) {
                    LogUtil.b("MusicSecondaryMenuActivity", "ActivityNotFoundException");
                    return;
                }
            }
            if (i != 1016) {
                if (i == 1017) {
                    LogUtil.a("MusicSecondaryMenu", 0, "MusicSecondaryMenuActivity", "HandleMessage.");
                    musicSecondaryMenuActivity.f();
                    musicSecondaryMenuActivity.l();
                    musicSecondaryMenuActivity.i();
                    return;
                }
                LogUtil.h("MusicSecondaryMenu", 0, "MusicSecondaryMenuActivity", "Enter default");
            }
        }
    }

    public class e implements IBaseResponseCallback {
        private final WeakReference<MusicSecondaryMenuActivity> c;

        e(MusicSecondaryMenuActivity musicSecondaryMenuActivity) {
            this.c = new WeakReference<>(musicSecondaryMenuActivity);
        }

        @Override // com.huawei.hwbasemgr.IBaseResponseCallback
        /* renamed from: onResponse */
        public void d(int i, Object obj) {
            LogUtil.a("MusicSecondaryMenuActivity", "getAuthorizationStatus");
            if (this.c.get() == null) {
                return;
            }
            if (obj instanceof Integer) {
                if (((Integer) obj).intValue() == 100) {
                    MusicSecondaryMenuActivity.this.runOnUiThread(new Runnable() { // from class: oxk
                        @Override // java.lang.Runnable
                        public final void run() {
                            MusicSecondaryMenuActivity.e.this.b();
                        }
                    });
                } else {
                    MusicSecondaryMenuActivity.this.runOnUiThread(new Runnable() { // from class: oxj
                        @Override // java.lang.Runnable
                        public final void run() {
                            MusicSecondaryMenuActivity.e.this.a();
                        }
                    });
                }
            }
            jjd.b(BaseApplication.getContext()).b();
        }

        public /* synthetic */ void b() {
            MusicSecondaryMenuActivity.this.x.setVisibility(8);
            MusicSecondaryMenuActivity.this.m.setVisibility(0);
            Intent intent = new Intent();
            intent.putExtra("device_id", MusicSecondaryMenuActivity.this.h);
            intent.setClass(MusicSecondaryMenuActivity.this, MusicMainActivity.class);
            MusicSecondaryMenuActivity.this.startActivity(intent);
        }

        public /* synthetic */ void a() {
            MusicSecondaryMenuActivity.this.x.setVisibility(8);
            MusicSecondaryMenuActivity.this.m.setVisibility(0);
            MusicSecondaryMenuActivity.this.h();
        }
    }

    public /* synthetic */ void d(int i, Object obj) {
        if (i == 0 && (obj instanceof List)) {
            LogUtil.a("MusicSecondaryMenu", 0, "MusicSecondaryMenuActivity", "startMessageNotificationObserver count ", Integer.valueOf(((List) obj).size()));
            djy_().sendEmptyMessage(1016);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public List<obz> c() {
        return this.l;
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_music_secondary_menu);
        oyf.e(true);
        this.b = this;
        Context context = BaseApplication.getContext();
        this.f9641a = context;
        this.g = DeviceSettingsInteractors.d(context);
        this.y = new NotificationPushInteractor(this.f9641a);
        if (jki.d() != null) {
            LogUtil.a("MusicSecondaryMenuActivity", "getHwMusicAccountSendToWear");
            jki.c(this.f9641a, false);
        }
        Intent intent = getIntent();
        if (intent != null) {
            this.h = intent.getStringExtra("device_id");
            this.s = intent.getBooleanExtra("isFromDeepLink", false);
            this.t = intent.getBooleanExtra("musicAuthorizationStatus", false);
        }
        if (this.h != null && oxa.a().a(this.h) != null) {
            this.c = oxa.a().a(this.h);
        }
        if (this.s && !this.t) {
            h();
        }
        n();
        b();
        LogUtil.a("MusicSecondaryMenu", 0, "MusicSecondaryMenuActivity", "UI setContentView");
        HandlerThread handlerThread = new HandlerThread("MusicSecondaryMenuActivity");
        this.n = handlerThread;
        handlerThread.start();
        c cVar = new c(this.n.getLooper(), this);
        this.w = cVar;
        cVar.post(this.k);
        m();
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        r();
        i();
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        LogUtil.a("MusicSecondaryMenuActivity", "onDestroy");
        djB_(this.d);
        e();
    }

    private void e() {
        HandlerThread handlerThread = this.n;
        if (handlerThread != null) {
            handlerThread.quit();
            this.n = null;
        }
        Handler handler = this.p;
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
            this.p = null;
        }
    }

    private void m() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.huawei.bone.action.CONNECTION_STATE_CHANGED");
        BroadcastManagerUtil.bFC_(this.b, this.d, intentFilter, LocalBroadcast.c, null);
    }

    private void djB_(BroadcastReceiver broadcastReceiver) {
        this.b.unregisterReceiver(broadcastReceiver);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void l() {
        LogUtil.a("MusicSecondaryMenu", 0, "MusicSecondaryMenuActivity", "refreshSettingView() mGeneralList , ", c());
        ListView listView = (ListView) findViewById(R.id.list_general_settings);
        this.m = listView;
        BaseActivity.cancelLayoutById(listView);
        this.m.setLayerType(2, null);
        DeviceSettingFactoryBaseAdapter deviceSettingFactoryBaseAdapter = new DeviceSettingFactoryBaseAdapter(this, c());
        this.i = deviceSettingFactoryBaseAdapter;
        this.m.setAdapter((ListAdapter) deviceSettingFactoryBaseAdapter);
        djA_(this.m, this.i);
        this.m.setOnItemClickListener(this.f);
        this.x = (RelativeLayout) findViewById(R.id.progress_rlt);
    }

    private void r() {
        DeviceCapability e2 = this.g.e(this.h);
        this.j = e2;
        if (e2 == null || !e2.isSupportMusicControl()) {
            return;
        }
        LogUtil.a("MusicSecondaryMenuActivity", "Enter updateMusicState:", Boolean.valueOf(this.y.e()));
        if (!this.y.e()) {
            c(c(), 34).e(false);
            jjd.b(this.f9641a).c(false, true);
            LogUtil.a("MusicSecondaryMenuActivity", "end updateMusicState");
        } else {
            boolean c2 = jjd.b(this.f9641a).c();
            c(c(), 34).e(c2);
            jjd.b(this.f9641a).c(c2, false);
        }
    }

    private void b() {
        DeviceInfo deviceInfo = this.c;
        if (deviceInfo != null) {
            this.o = deviceInfo.getDeviceConnectState() == 2;
            LogUtil.a("MusicSecondaryMenu", 0, "MusicSecondaryMenuActivity", "Enter onCreateView mIsConnected:", Boolean.valueOf(this.o), " state:", Integer.valueOf(this.c.getDeviceConnectState()));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void i() {
        LogUtil.a("MusicSecondaryMenu", 0, "MusicSecondaryMenuActivity", "notifyUpdateSettingList() mGeneralAdapter is ", c());
        DeviceSettingFactoryBaseAdapter deviceSettingFactoryBaseAdapter = this.i;
        if (deviceSettingFactoryBaseAdapter != null) {
            deviceSettingFactoryBaseAdapter.notifyDataSetChanged();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void j() {
        this.u.b(this.r);
    }

    private Handler djy_() {
        return this.p;
    }

    static class c extends Handler {
        private final WeakReference<MusicSecondaryMenuActivity> b;

        c(Looper looper, MusicSecondaryMenuActivity musicSecondaryMenuActivity) {
            super(looper);
            this.b = new WeakReference<>(musicSecondaryMenuActivity);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            MusicSecondaryMenuActivity musicSecondaryMenuActivity = this.b.get();
            if (musicSecondaryMenuActivity != null) {
                LogUtil.a("MusicSecondaryMenu", 0, "MusicSecondaryMenuActivity", "WeakHandler msg.what:", Integer.valueOf(message.what));
                if (message.what == 1019) {
                    musicSecondaryMenuActivity.j();
                    return;
                } else {
                    LogUtil.h("MusicSecondaryMenu", 0, "MusicSecondaryMenuActivity", "WeakHandler default");
                    return;
                }
            }
            LogUtil.h("MusicSecondaryMenu", 0, "MusicSecondaryMenuActivity", "WeakHandler null");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void s() {
        LogUtil.a("MusicSecondaryMenu", 0, "MusicSecondaryMenuActivity", "Enter weakRunnableRun");
        a();
    }

    private void a() {
        k();
        Handler handler = this.p;
        if (handler != null) {
            handler.sendEmptyMessage(1017);
        }
    }

    private void k() {
        LogUtil.a("MusicSecondaryMenu", 0, "MusicSecondaryMenuActivity", "Enter startMessageNotificationObserver!");
        oxd a2 = oxd.a();
        this.u = a2;
        if (a2 != null) {
            a2.djp_(this.w);
            this.u.b(this.r);
            LogUtil.a("MusicSecondaryMenu", 0, "MusicSecondaryMenuActivity", "startMessageNotificationObserver getMessageTotalList");
            this.u.b();
        } else {
            LogUtil.a("MusicSecondaryMenu", 0, "MusicSecondaryMenuActivity", "startMessageNotificationObserver enter null");
        }
        LogUtil.a("MusicSecondaryMenu", 0, "MusicSecondaryMenuActivity", "Leave startMessageNotificationObserver!");
    }

    static class d implements Runnable {
        private final WeakReference<MusicSecondaryMenuActivity> e;

        @Override // java.lang.Runnable
        public void run() {
        }

        d(MusicSecondaryMenuActivity musicSecondaryMenuActivity) {
            this.e = new WeakReference<>(musicSecondaryMenuActivity);
        }

        public MusicSecondaryMenuActivity e() {
            return this.e.get();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void f() {
        LogUtil.a("MusicSecondaryMenu", 0, "MusicSecondaryMenuActivity", "Enter refreshSettingData() mIsConnected:", Boolean.valueOf(this.o));
        if (g()) {
            LogUtil.a("MusicSecondaryMenuActivity", "mDeviceCapability is null, refreshSettingData return!");
        } else {
            o();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public obz c(List<obz> list, int i) {
        obz obzVar = new obz();
        for (obz obzVar2 : list) {
            if (obzVar2.e() == i) {
                LogUtil.a("MusicSecondaryMenu", 0, "MusicSecondaryMenuActivity", "getItem getId is ", Integer.valueOf(obzVar2.e()));
                obzVar = obzVar2;
            }
        }
        return obzVar;
    }

    private void o() {
        if (this.j.isSupportMusicInfoList()) {
            djx_(this.o, new ozj(0, 110, 8), new ozh(getString(R.string._2130843344_res_0x7f0216d0), getString(R.string._2130843345_res_0x7f0216d1), ""), new CompoundButton.OnCheckedChangeListener[0]);
        }
        if (this.j.isSupportMusicControl()) {
            String string = getString(R.string._2130843367_res_0x7f0216e7);
            if (nsn.ae(BaseApplication.getContext())) {
                string = getString(R.string._2130844401_res_0x7f021af1);
            }
            djx_(this.o, new ozj(0, 34, 9), new ozh(string, "", ""), this.q);
            c(c(), 34).e(jjd.b(this.f9641a).c());
        }
    }

    private void djx_(boolean z, ozj ozjVar, ozh ozhVar, CompoundButton.OnCheckedChangeListener... onCheckedChangeListenerArr) {
        synchronized (this.e) {
            c().add(ozf.djU_(z, ozjVar, ozhVar, onCheckedChangeListenerArr));
        }
    }

    private boolean g() {
        this.j = this.g.e(this.h);
        c().clear();
        djA_(this.m, this.i);
        i();
        if (this.j == null) {
            LogUtil.a("MusicSecondaryMenuActivity", "refreshSettingInit mDeviceCapability is null");
            return true;
        }
        this.c = oxa.a().a(this.h);
        n();
        return false;
    }

    private void djA_(ListView listView, DeviceSettingFactoryBaseAdapter deviceSettingFactoryBaseAdapter) {
        if (listView == null || deviceSettingFactoryBaseAdapter == null) {
            return;
        }
        int i = 0;
        for (int i2 = 0; i2 < deviceSettingFactoryBaseAdapter.getCount(); i2++) {
            View view = deviceSettingFactoryBaseAdapter.getView(i2, null, listView);
            if (view != null) {
                view.measure(0, 0);
                i += view.getMeasuredHeight();
            }
        }
        ViewGroup.LayoutParams layoutParams = listView.getLayoutParams();
        layoutParams.height = i + (listView.getDividerHeight() * (deviceSettingFactoryBaseAdapter.getCount() - 1));
        listView.setLayoutParams(layoutParams);
    }

    private void n() {
        DeviceInfo deviceInfo = this.c;
        if (deviceInfo != null) {
            this.h = deviceInfo.getDeviceIdentify();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void h() {
        LogUtil.a("MusicSecondaryMenuActivity", "enter noPermissionDialog");
        NoTitleCustomAlertDialog.Builder builder = new NoTitleCustomAlertDialog.Builder(this);
        builder.e(getString(R.string._2130845003_res_0x7f021d4b)).czC_(R.string._2130841794_res_0x7f0210c2, new View.OnClickListener() { // from class: oxl
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        builder.e().show();
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
