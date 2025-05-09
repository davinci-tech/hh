package defpackage;

import android.app.Activity;
import android.app.Dialog;
import android.bluetooth.BluetoothAdapter;
import android.content.ActivityNotFoundException;
import android.content.BroadcastReceiver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.huawei.haf.handler.BaseHandler;
import com.huawei.health.R;
import com.huawei.health.device.callback.IHealthDeviceCallback;
import com.huawei.health.device.connectivity.comm.MeasurableDevice;
import com.huawei.health.device.fatscale.multiusers.MultiUsersManager;
import com.huawei.health.device.model.HealthDevice;
import com.huawei.health.device.open.MeasureController;
import com.huawei.health.device.open.MeasureKit;
import com.huawei.health.device.open.data.model.HealthData;
import com.huawei.health.device.ui.DeviceMainActivity;
import com.huawei.health.device.ui.measure.BaseInteractor;
import com.huawei.health.device.util.EventBus;
import com.huawei.health.ecologydevice.manager.ResourceManager;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hihealth.HiAggregateOption;
import com.huawei.hihealth.HiHealthClient;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.api.HiHealthManager;
import com.huawei.hihealth.api.HiHealthNativeApi;
import com.huawei.hihealth.data.listener.HiAggregateListener;
import com.huawei.hihealth.data.listener.HiSubscribeListener;
import com.huawei.hihealth.data.listener.HiUnSubscribeListener;
import com.huawei.hihealth.data.type.HiSubscribeType;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwbasemgr.WeightBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwcommonmodel.utils.PermissionUtil;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.ble.BleConstants;
import com.huawei.ui.commonui.R$string;
import com.huawei.ui.commonui.dialog.CustomTextAlertDialog;
import com.huawei.ui.commonui.dialog.CustomViewDialog;
import com.huawei.ui.commonui.dialog.NoTitleCustomAlertDialog;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.progressbar.HealthProgressBar;
import com.huawei.ui.commonui.utils.CustomPermissionAction;
import defpackage.cld;
import health.compact.a.CommonUtil;
import health.compact.a.LanguageUtil;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.UnitUtil;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.lang.ref.WeakReference;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

/* loaded from: classes3.dex */
public class cld extends BaseInteractor implements View.OnClickListener {

    /* renamed from: a, reason: collision with root package name */
    private static cld f777a;
    private static c b;
    private HealthTextView aa;
    private CustomTextAlertDialog ab;
    private HealthTextView ae;
    private HealthTextView af;
    private String ag;
    private LinearLayout ah;
    private volatile int ai;
    private HealthTextView aj;
    private HealthTextView ak;
    private List<Integer> an;
    private HealthTextView c;
    private a d;
    private HealthTextView g;
    private HealthTextView h;
    private LinearLayout i;
    private HealthTextView j;
    private HealthTextView k;
    private Handler m;
    private LinearLayout n;
    private cmv o;
    private ImageView r;
    private HealthTextView t;
    private d u;
    private CustomViewDialog v;
    private String w;
    private Activity x;
    private Dialog y;
    private HealthTextView z;
    private boolean s = false;
    private Timer l = null;
    private Timer f = null;
    private int e = Integer.MIN_VALUE;
    private boolean q = false;
    private boolean p = false;
    private int ac = 0;
    private IBaseResponseCallback ad = new IBaseResponseCallback() { // from class: cln
        @Override // com.huawei.hwbasemgr.IBaseResponseCallback
        /* renamed from: onResponse */
        public final void d(int i2, Object obj) {
            cld.this.b(i2, obj);
        }
    };

    /* synthetic */ void b(final int i2, final Object obj) {
        Activity activity = this.x;
        if (activity == null) {
            LogUtil.b("PluginDevice_WeightInteractor", "queryData get main activity is null");
        } else {
            activity.runOnUiThread(new Runnable() { // from class: clo
                @Override // java.lang.Runnable
                public final void run() {
                    cld.this.d(i2, obj);
                }
            });
        }
    }

    /* synthetic */ void d(int i2, Object obj) {
        if (i2 == 0 && (obj instanceof HiHealthData)) {
            Activity activity = this.x;
            if (activity instanceof DeviceMainActivity) {
                if (((DeviceMainActivity) activity).g()) {
                    e((HiHealthData) obj, this.af, this.z, this.aa);
                    return;
                } else {
                    a((HiHealthData) obj);
                    return;
                }
            }
            LogUtil.b("PluginDevice_WeightInteractor", "mMainActivity is not instanceof DeviceMainActivity");
            return;
        }
        LogUtil.h("PluginDevice_WeightInteractor", "queryData HiHealthData onResponse can not refresh");
    }

    @Deprecated
    private cld(Activity activity, String str, String str2) {
        this.x = activity;
        this.w = str;
        this.ag = str2;
    }

    private cld(String str, String str2) {
        this.w = str;
        this.ag = str2;
    }

    public static cld d(String str, String str2) {
        cld cldVar;
        synchronized (cld.class) {
            if (TextUtils.isEmpty(str2)) {
                LogUtil.h("PluginDevice_WeightInteractor", "getInstance uniqueId is null");
            }
            cld cldVar2 = f777a;
            if (cldVar2 != null && !TextUtils.equals(str2, cldVar2.ag)) {
                f777a.onDestroy();
                f777a = new cld(str, str2);
                b = new c();
            }
            if (f777a == null) {
                f777a = new cld(str, str2);
            }
            if (b == null) {
                b = new c();
            }
            f777a.q();
            f777a.c(str);
            cldVar = f777a;
        }
        return cldVar;
    }

    @Deprecated
    public static cld HJ_(Activity activity, String str, String str2) {
        cld cldVar;
        synchronized (cld.class) {
            if (TextUtils.isEmpty(str2)) {
                LogUtil.h("PluginDevice_WeightInteractor", "getInstance uniqueId is null");
            }
            cld cldVar2 = f777a;
            if (cldVar2 != null && !TextUtils.equals(str2, cldVar2.ag)) {
                f777a.onDestroy();
                f777a = new cld(activity, str, str2);
                b = new c();
            }
            if (f777a == null) {
                f777a = new cld(activity, str, str2);
            }
            if (b == null) {
                b = new c();
            }
            f777a.HS_(activity);
            f777a.q();
            f777a.c(str);
            f777a.h();
            cldVar = f777a;
        }
        return cldVar;
    }

    @Deprecated
    public static cld HI_(Activity activity, String str) {
        cld HJ_;
        synchronized (cld.class) {
            HJ_ = HJ_(activity, str, e(str));
        }
        return HJ_;
    }

    public void HS_(Activity activity) {
        this.x = activity;
    }

    public void b(cmv cmvVar) {
        this.o = cmvVar;
    }

    private void q() {
        this.ac = 0;
    }

    private void c(String str) {
        this.w = str;
    }

    public void HN_(Activity activity, View view) {
        if (activity == null) {
            LogUtil.h("PluginDevice_WeightInteractor", "initView mainActivity is null");
            return;
        }
        this.x = activity;
        h();
        this.k = (HealthTextView) nsy.cMd_(view, R.id.weight_device_connect_status_tv);
        this.ae = (HealthTextView) nsy.cMd_(view, R.id.weight_start_measure_tv);
        this.h = (HealthTextView) nsy.cMd_(view, R.id.connect_status_prompt_message);
        HealthTextView healthTextView = this.k;
        if (healthTextView != null) {
            healthTextView.setText(this.x.getString(R.string._2130841443_res_0x7f020f63));
        } else {
            LogUtil.h("PluginDevice_WeightInteractor", "mConnectStatusTextView is null please check the rootView");
        }
        HH_(view);
        c(0.0d, this.ak);
        k();
    }

    public void HO_(Activity activity, View view) {
        if (activity == null) {
            LogUtil.h("PluginDevice_WeightInteractor", "initWifiView mainActivity is null");
            return;
        }
        this.x = activity;
        h();
        HH_(view);
        k();
    }

    private void HH_(View view) {
        this.ah = (LinearLayout) nsy.cMd_(view, R.id.weight_value_layout);
        this.aj = (HealthTextView) nsy.cMd_(view, R.id.weight_value);
        this.ak = (HealthTextView) nsy.cMd_(view, R.id.weight_unit);
        this.c = (HealthTextView) nsy.cMd_(view, R.id.bmi_value);
        this.i = (LinearLayout) nsy.cMd_(view, R.id.body_fat_rate_layout);
        this.j = (HealthTextView) nsy.cMd_(view, R.id.body_fat_rate_value);
        this.g = (HealthTextView) nsy.cMd_(view, R.id.body_fat_rate_value_unit);
        LinearLayout linearLayout = (LinearLayout) nsy.cMd_(view, R.id.scale_device_go_details);
        this.n = linearLayout;
        linearLayout.setOnClickListener(this);
        this.t = (HealthTextView) nsy.cMd_(view, R.id.scale_device_last_time);
        this.r = (ImageView) nsy.cMd_(view, R.id.icon);
        this.af = (HealthTextView) nsy.cMd_(view, R.id.smart_life_measure_value);
        this.z = (HealthTextView) nsy.cMd_(view, R.id.smart_life_measure_time);
        this.aa = (HealthTextView) nsy.cMd_(view, R.id.smart_life_measure_unit);
    }

    private static String e(String str) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("PluginDevice_WeightInteractor", "getDeviceUniqueId productId is null");
            return "";
        }
        MeasurableDevice c2 = ceo.d().c(str);
        if (c2 == null) {
            LogUtil.h("PluginDevice_WeightInteractor", "measurebleDevice is null, productId:", cpw.d(str));
            return "";
        }
        return c2.getUniqueId();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void k() {
        LogUtil.a("PluginDevice_WeightInteractor", "WeightInteractor queryData to enter");
        b(this.ad);
    }

    public void a() {
        if (this.m == null) {
            this.m = new i(this);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void g() {
        if (this.ai == 1) {
            LogUtil.h("PluginDevice_WeightInteractor", "connectFail mStatus is connecting");
            return;
        }
        Object[] objArr = new Object[2];
        objArr[0] = "HealthDataImporter Fail to connect, callback is null:";
        objArr[1] = Boolean.valueOf(b == null);
        LogUtil.h("PluginDevice_WeightInteractor", objArr);
        this.p = false;
        s();
        p();
        int i2 = this.ac;
        if (i2 < 100 && b != null) {
            LogUtil.a("PluginDevice_WeightInteractor", "onResume startTimer, ", Integer.valueOf(i2));
            i();
        } else {
            d();
            onDestroy();
        }
    }

    private void s() {
        Activity activity = this.x;
        if (activity != null) {
            HealthTextView healthTextView = this.k;
            if (healthTextView != null) {
                healthTextView.setText(activity.getString(R.string._2130841443_res_0x7f020f63));
            }
            HealthTextView healthTextView2 = this.h;
            if (healthTextView2 != null) {
                healthTextView2.setVisibility(0);
            }
        }
    }

    private void t() {
        Activity activity = this.x;
        if (activity != null) {
            HealthTextView healthTextView = this.k;
            if (healthTextView != null) {
                healthTextView.setText(activity.getString(R.string._2130840619_res_0x7f020c2b));
            }
            HealthTextView healthTextView2 = this.h;
            if (healthTextView2 != null) {
                healthTextView2.setVisibility(8);
            }
            HealthTextView healthTextView3 = this.ae;
            if (healthTextView3 != null) {
                healthTextView3.setVisibility(8);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void n() {
        LogUtil.a("PluginDevice_WeightInteractor", "WeightInteractor connected");
        Activity activity = this.x;
        if (activity != null) {
            HealthTextView healthTextView = this.k;
            if (healthTextView != null) {
                healthTextView.setText(activity.getString(R.string._2130841442_res_0x7f020f62));
            }
            HealthTextView healthTextView2 = this.h;
            if (healthTextView2 != null) {
                healthTextView2.setVisibility(8);
            }
        }
        if (d("hagridConfigureTheNetwork", 7) >= 7 && "b29df4e3-b1f7-4e40-960d-4cfb63ccca05".equals(this.w)) {
            LogUtil.a("PluginDevice_WeightInteractor", "EventBus.publish");
            EventBus.d(new EventBus.b("event_bus_config_wifi", new Bundle()));
        }
        this.ac = 0;
        d();
    }

    private int d(String str, int i2) {
        String b2 = SharedPreferenceManager.b(BaseApplication.getContext(), String.valueOf(10000), str + this.ag);
        if (TextUtils.isEmpty(b2)) {
            LogUtil.h("PluginDevice_WeightInteractor", "differentDaysByMillisecond is first");
            return i2;
        }
        int currentTimeMillis = (int) ((System.currentTimeMillis() - CommonUtil.g(b2)) / 86400000);
        LogUtil.a("PluginDevice_WeightInteractor", "differentDaysByMillisecond get config days is = ", Integer.valueOf(currentTimeMillis));
        return currentTimeMillis;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void o() {
        LogUtil.a("PluginDevice_WeightInteractor", "WeightInteractor connecting");
        Activity activity = this.x;
        if (activity != null) {
            HealthTextView healthTextView = this.k;
            if (healthTextView != null) {
                healthTextView.setText(activity.getString(R.string.IDS_hw_health_wear_connect_device_connect_text));
            }
            HealthTextView healthTextView2 = this.h;
            if (healthTextView2 != null) {
                healthTextView2.setVisibility(8);
            }
        }
    }

    @Override // com.huawei.health.device.ui.measure.BaseInteractor
    public void controller(int i2, Object obj) {
        Activity activity = this.x;
        if (activity == null) {
            LogUtil.b("PluginDevice_WeightInteractor", "WeightInteractor controller mMainActivity is null");
            return;
        }
        if (i2 == 5) {
            if (l()) {
                if (this.p) {
                    v();
                    return;
                } else {
                    nrh.b(this.x, R.string.IDS_plugin_device_weight_device_not_connect);
                    return;
                }
            }
            b(1);
            return;
        }
        if (i2 == 6) {
            if (this.p) {
                Intent intent = new Intent();
                ContentValues contentValues = new ContentValues();
                contentValues.put("productId", this.w);
                contentValues.put("uniqueId", this.ag);
                intent.putExtra("commonDeviceInfo", contentValues);
                if (obj instanceof Bundle) {
                    Bundle bundle = (Bundle) obj;
                    intent.putExtra("deviceSsid", bundle.getString("deviceSsid"));
                    intent.putExtra("user_type", bundle.getBoolean("user_type"));
                }
                intent.setPackage(BaseApplication.getAppPackage());
                intent.putExtra("fromsetting", true);
                intent.setClassName(BaseApplication.getAppPackage(), "com.huawei.ui.device.activity.update.WeightUpdateVersionActivity");
                try {
                    this.x.startActivity(intent);
                    return;
                } catch (ActivityNotFoundException unused) {
                    LogUtil.b("PluginDevice_WeightInteractor", "controller ActivityNotFoundException");
                    return;
                }
            }
            nrh.b(activity, R.string.IDS_plugin_device_weight_device_not_connect);
            return;
        }
        LogUtil.a("PluginDevice_WeightInteractor", "WeightInteractor controller().");
    }

    private void v() {
        Activity activity = this.x;
        if (activity != null && !activity.isFinishing()) {
            CustomTextAlertDialog.Builder builder = new CustomTextAlertDialog.Builder(this.x);
            builder.b(R.string.IDS_plugin_device_clear_user_data).d(R.string.IDS_plugin_device_weight_device_clear_dialog_tip).cyU_(R.string.IDS_device_ui_dialog_yes, new View.OnClickListener() { // from class: clh
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    cld.this.HP_(view);
                }
            }).cyR_(R.string.IDS_device_ui_dialog_no, new View.OnClickListener() { // from class: clg
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    cld.HK_(view);
                }
            });
            CustomTextAlertDialog a2 = builder.a();
            a2.setCancelable(false);
            a2.show();
            return;
        }
        LogUtil.a("PluginDevice_WeightInteractor", "showClearTipDialog() mMainActivity is finish.");
    }

    /* synthetic */ void HP_(View view) {
        if (this.p) {
            r();
            f();
        } else {
            nrh.b(this.x, R.string.IDS_plugin_device_weight_device_not_connect);
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    static /* synthetic */ void HK_(View view) {
        LogUtil.a("PluginDevice_WeightInteractor", "showClearTipDialog NegativeButton");
        ViewClickInstrumentation.clickOnView(view);
    }

    private void f() {
        LogUtil.a("PluginDevice_WeightInteractor", "send clear user data command");
        a(true, true);
        w();
        EventBus.d(new EventBus.b("weight_device_clear_user_data"));
    }

    private void w() {
        Activity activity = this.x;
        if (activity == null || activity.isFinishing()) {
            LogUtil.b("PluginDevice_WeightInteractor", "WeightInteractor showClearProgressDialog mMainActivity is null or finish");
            return;
        }
        CustomViewDialog customViewDialog = this.v;
        if (customViewDialog == null) {
            CustomViewDialog.Builder builder = new CustomViewDialog.Builder(this.x);
            View inflate = LayoutInflater.from(this.x).inflate(R.layout.dialog_weight_device_clear_user_data_progress, (ViewGroup) null);
            ((HealthProgressBar) nsy.cMd_(inflate, R.id.iv_clear_user_data_anim)).setVisibility(0);
            builder.czg_(inflate);
            CustomViewDialog e2 = builder.e();
            this.v = e2;
            e2.setCancelable(false);
            this.v.show();
        } else if (!customViewDialog.isShowing()) {
            this.v.show();
        } else {
            LogUtil.a("PluginDevice_WeightInteractor", "WeightInteractor showClearProgressDialog().");
        }
        b(true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(boolean z) {
        synchronized (this) {
            LogUtil.a("PluginDevice_WeightInteractor", "cancel user info task. is start: ", Boolean.valueOf(z));
            Timer timer = this.f;
            if (timer != null) {
                timer.cancel();
                this.f = null;
            }
            if (z) {
                Timer timer2 = new Timer("PluginDevice_WeightInteractor");
                this.f = timer2;
                timer2.schedule(new TimerTask() { // from class: cld.3
                    @Override // java.util.TimerTask, java.lang.Runnable
                    public void run() {
                        cld.this.p();
                    }
                }, 15000L);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void p() {
        synchronized (this) {
            LogUtil.a("PluginDevice_WeightInteractor", "Body fat scales to remove user data timeout");
            b(false);
            if (a(false, false)) {
                a(false, true);
                this.m.sendEmptyMessage(4);
            }
        }
    }

    private boolean a(boolean z, boolean z2) {
        boolean z3;
        synchronized (this) {
            if (z2) {
                this.q = z;
            }
            z3 = this.q;
        }
        return z3;
    }

    @Override // com.huawei.health.device.ui.measure.BaseInteractor
    public void onResume() {
        if (f777a.ai != 1) {
            this.ai = 3;
        } else {
            o();
        }
        LogUtil.a("PluginDevice_WeightInteractor", "onResume mStatus: ", Integer.valueOf(this.ai));
        this.p = false;
        d();
        Handler handler = this.m;
        if (handler == null) {
            ReleaseLogUtil.d("R_Weight_PluginDevice_WeightInteractor", "mHandler == null ");
            return;
        }
        handler.removeCallbacksAndMessages(null);
        this.ac = 0;
        if (!this.s) {
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("android.bluetooth.adapter.action.STATE_CHANGED");
            this.u = new d(this);
            BaseApplication.getContext().registerReceiver(this.u, intentFilter);
            this.s = true;
        }
        if (l()) {
            d(false);
            return;
        }
        LogUtil.a("PluginDevice_WeightInteractor", "onResume startTimer");
        i();
        b(1);
    }

    private boolean l() {
        BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
        if (defaultAdapter != null) {
            if (defaultAdapter.isEnabled()) {
                LogUtil.c("PluginDevice_WeightInteractor", "bluetooth is open");
                return true;
            }
            LogUtil.c("PluginDevice_WeightInteractor", "bluetooth is close");
            return false;
        }
        LogUtil.c("PluginDevice_WeightInteractor", "bluetooth is null");
        return false;
    }

    @Override // com.huawei.health.device.ui.measure.BaseInteractor
    public void onDestroy() {
        LogUtil.a("PluginDevice_WeightInteractor", "WeightInteractor destroy.");
        this.ai = 3;
        if (this.w != null) {
            Bundle bundle = new Bundle();
            bundle.putInt("type", -6);
            bundle.putString("productId", this.w);
            MeasurableDevice d2 = ceo.d().d(this.ag, false);
            if (cpa.ae(this.w)) {
                LogUtil.a("PluginDevice_WeightInteractor", "onDestroy prepare wsp controller");
                cgt.e().b(b);
                cgt.e().prepare(d2, null, bundle);
            } else {
                cgk.d().e(b);
                cgk.d().prepare(d2, null, bundle);
            }
            b = null;
        }
        cjx.e().e(this.w, this.ag, -6);
        e();
        c();
    }

    public void c() {
        Handler handler = this.m;
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
        }
        this.x = null;
        this.k = null;
        this.h = null;
        this.ah = null;
        this.i = null;
        this.aj = null;
        this.ak = null;
        this.c = null;
        this.j = null;
        this.g = null;
        this.af = null;
        this.z = null;
        this.aa = null;
        this.ae = null;
        this.n = null;
        this.t = null;
        this.r = null;
        this.y = null;
    }

    public void e() {
        j();
        setClean();
        this.v = null;
    }

    @Override // com.huawei.health.device.ui.measure.BaseInteractor
    public void setClean() {
        try {
            LogUtil.a("PluginDevice_WeightInteractor", "Enter unRegisterUnbindDeviceBroadcast()");
            if (this.s) {
                BaseApplication.getContext().unregisterReceiver(this.u);
                this.s = false;
            }
        } catch (IllegalArgumentException e2) {
            LogUtil.b("PluginDevice_WeightInteractor", e2.getMessage());
        }
        d();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(final boolean z) {
        c(this.x, new CustomPermissionAction(this.x) { // from class: cld.5
            @Override // com.huawei.hwcommonmodel.utils.permission.PermissionsResultAction
            public void onGranted() {
                cld.this.a(z);
            }
        });
    }

    private void c(Context context, CustomPermissionAction customPermissionAction) {
        if (context == null) {
            LogUtil.h("PluginDevice_WeightInteractor", "checkRequestScanPermissions: context is null");
        } else if (Build.VERSION.SDK_INT > 30) {
            PermissionUtil.b(context, PermissionUtil.PermissionType.SCAN, new AnonymousClass4(context, customPermissionAction, context));
        } else {
            customPermissionAction.onGranted();
        }
    }

    /* renamed from: cld$4, reason: invalid class name */
    public class AnonymousClass4 extends CustomPermissionAction {
        final /* synthetic */ Context c;
        final /* synthetic */ CustomPermissionAction e;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass4(Context context, CustomPermissionAction customPermissionAction, Context context2) {
            super(context);
            this.e = customPermissionAction;
            this.c = context2;
        }

        @Override // com.huawei.hwcommonmodel.utils.permission.PermissionsResultAction
        public void onGranted() {
            LogUtil.a("PluginDevice_WeightInteractor", "checkRequestScanPermissions onGranted");
            a();
            this.e.onGranted();
        }

        @Override // com.huawei.ui.commonui.utils.CustomPermissionAction, com.huawei.hwcommonmodel.utils.permission.PermissionsResultAction
        public void onForeverDenied(PermissionUtil.PermissionType permissionType) {
            a();
            String string = this.c.getResources().getString(nsn.d(PermissionUtil.PermissionType.SCAN));
            cld cldVar = cld.this;
            CustomTextAlertDialog.Builder cyR_ = new CustomTextAlertDialog.Builder(this.c).b(R$string.IDS_hwh_home_other_permissions_title).e(string).cyR_(R$string.IDS_settings_button_cancal, new View.OnClickListener() { // from class: clp
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    cld.AnonymousClass4.HT_(view);
                }
            });
            int i = R$string.IDS_hwh_motiontrack_permission_guide_go_set;
            final Context context = this.c;
            cldVar.ab = cyR_.cyU_(i, new View.OnClickListener() { // from class: com.huawei.health.device.ui.measure.WeightInteractor$3$$ExternalSyntheticLambda1
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    cld.AnonymousClass4.HU_(context, view);
                }
            }).a();
            cld.this.ab.setCancelable(false);
            cld.this.ab.show();
        }

        static /* synthetic */ void HT_(View view) {
            LogUtil.a("PluginDevice_WeightInteractor", "showScanPermissionSettingGuide cancel");
            ViewClickInstrumentation.clickOnView(view);
        }

        public static /* synthetic */ void HU_(Context context, View view) {
            LogUtil.a("PluginDevice_WeightInteractor", "showScanPermissionSettingGuide setting");
            nsn.ak(context);
            ViewClickInstrumentation.clickOnView(view);
        }

        private void a() {
            if (cld.this.ab == null || !cld.this.ab.isShowing()) {
                return;
            }
            cld.this.ab.dismiss();
            LogUtil.a("PluginDevice_WeightInteractor", "showScanPermissionSettingGuide: dismiss dialog");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(boolean z) {
        MeasurableDevice d2 = ceo.d().d(this.ag, true);
        if (d2 == null || !(d2 instanceof cxh)) {
            LogUtil.a("PluginDevice_WeightInteractor", "WeightInteractor BleDevice not bonded :", this.w);
            if (cpa.x(this.w)) {
                d();
                onDestroy();
                s();
                return;
            }
            return;
        }
        if (l()) {
            Bundle bundle = new Bundle();
            bundle.putInt("type", -1);
            bundle.putString("productId", this.w);
            bundle.putString("uniqueId", this.ag);
            bundle.putBoolean("clearData", true);
            bundle.putBoolean("isConnectAction", true);
            if (z) {
                cgt.e().e(-6);
                cjx.e().e(this.w, this.ag, -6);
                cjx.e().Gt_(this.w, this.ag, b, bundle, true);
                LogUtil.a("PluginDevice_WeightInteractor", "reconnect...");
                return;
            }
            dcz d3 = ResourceManager.e().d(this.w);
            if (d3 == null) {
                LogUtil.h("PluginDevice_WeightInteractor", "productInfo == null");
                return;
            }
            MeasureKit g = cjx.e().g(d3.s());
            if (g == null) {
                LogUtil.h("PluginDevice_WeightInteractor", "kit == null");
                return;
            }
            MeasureController measureController = g.getMeasureController();
            if (measureController == null) {
                LogUtil.h("PluginDevice_WeightInteractor", "controller == null");
                return;
            } else {
                measureController.prepare(d2, b, bundle);
                return;
            }
        }
        LogUtil.a("PluginDevice_WeightInteractor", "bluetooth is off");
    }

    public void d() {
        synchronized (this) {
            Timer timer = this.l;
            if (timer != null) {
                timer.cancel();
                this.l = null;
                LogUtil.a("PluginDevice_WeightInteractor", "Cancel the timer connected devices");
            } else {
                LogUtil.h("PluginDevice_WeightInteractor", "Connection timer has been canceled");
            }
            a aVar = this.d;
            if (aVar != null) {
                boolean cancel = aVar.cancel();
                this.d = null;
                LogUtil.a("PluginDevice_WeightInteractor", "bluetooth reconnection cancel result: ", Boolean.valueOf(cancel));
            } else {
                LogUtil.h("PluginDevice_WeightInteractor", "mBluetoothReconnectionTimer is null");
            }
        }
    }

    public void i() {
        synchronized (this) {
            this.ac++;
            if (this.l == null) {
                this.l = new Timer("PluginDevice_WeightInteractor");
                LogUtil.a("PluginDevice_WeightInteractor", "Start the timer connected devices");
                if (this.d == null) {
                    this.d = new a(this);
                }
                this.l.schedule(this.d, 1000L, 32005L);
            }
        }
    }

    public void b(final int i2) {
        LogUtil.a("PluginDevice_WeightInteractor", "show open bluetooth dialog...");
        Activity activity = this.x;
        if (activity == null || activity.isFinishing() || this.x.isDestroyed()) {
            LogUtil.h("PluginDevice_WeightInteractor", "showOpenSystemBluetoothDialog: mMainActivity is null | isFinishing | isDestroyed");
        } else {
            c(this.x, new CustomPermissionAction(this.x) { // from class: cld.1
                @Override // com.huawei.hwcommonmodel.utils.permission.PermissionsResultAction
                public void onGranted() {
                    cld.this.c(i2);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(int i2) {
        Dialog dialog = this.y;
        if (dialog != null && dialog.isShowing()) {
            this.y.dismiss();
            LogUtil.a("PluginDevice_WeightInteractor", "showOpenSystemBluetoothDialog: dismiss old dialog");
        }
        if (i2 == 1) {
            CustomTextAlertDialog.Builder builder = new CustomTextAlertDialog.Builder(this.x);
            builder.b(R.string.IDS_device_bluetooth_open).d(R.string.IDS_device_bluetooth_open_request).cyR_(R.string.IDS_device_ui_dialog_no, new View.OnClickListener() { // from class: clb
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    cld.HL_(view);
                }
            }).cyU_(R.string.IDS_device_ui_dialog_yes, new View.OnClickListener() { // from class: cli
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    cld.this.HQ_(view);
                }
            });
            this.y = builder.a();
        } else if (i2 == 2) {
            String string = this.x.getResources().getString(R.string.IDS_app_name_health);
            NoTitleCustomAlertDialog.Builder e2 = new NoTitleCustomAlertDialog.Builder(this.x).e(String.format(Locale.ENGLISH, this.x.getResources().getString(R.string.IDS_device_bt_open_request_info), string));
            e2.czz_(R.string.IDS_device_bt_left_btn_info, new View.OnClickListener() { // from class: clj
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    cld.HM_(view);
                }
            }).czC_(R.string.IDS_device_bt_right_btn_info, new View.OnClickListener() { // from class: clk
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    cld.this.HR_(view);
                }
            });
            this.y = e2.e();
        } else {
            LogUtil.h("PluginDevice_WeightInteractor", "WeightInteractor showOpenSystemBluetoothDialog()");
        }
        Dialog dialog2 = this.y;
        if (dialog2 != null) {
            dialog2.setCancelable(false);
            this.y.show();
        }
    }

    static /* synthetic */ void HL_(View view) {
        LogUtil.a("PluginDevice_WeightInteractor", "showOpenSystemBluetoothDialog NegativeButton");
        ViewClickInstrumentation.clickOnView(view);
    }

    /* synthetic */ void HQ_(View view) {
        cqh.c().KY_(this.x);
        ViewClickInstrumentation.clickOnView(view);
    }

    static /* synthetic */ void HM_(View view) {
        LogUtil.a("PluginDevice_WeightInteractor", "showOpenSystemBluetoothDialog NegativeButton OPEN_BLUETOOTH_DIALOG");
        ViewClickInstrumentation.clickOnView(view);
    }

    /* synthetic */ void HR_(View view) {
        cqh.c().KY_(this.x);
        ViewClickInstrumentation.clickOnView(view);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void y() {
        LogUtil.a("PluginDevice_WeightInteractor", "WeightInteractor showSelectBindDeviceDialog");
        Activity activity = this.x;
        if (activity != null && (activity instanceof DeviceMainActivity)) {
            ((DeviceMainActivity) activity).a(this.w);
            d();
            t();
            onDestroy();
            return;
        }
        LogUtil.h("PluginDevice_WeightInteractor", "WeightInteractor showSelectBindDeviceDialog mainActivity is null");
    }

    private void r() {
        HashMap hashMap = new HashMap(16);
        hashMap.put("click", "1");
        ixx.d().d(cpp.a(), AnalyticsValue.HEALTH_PLUGIN_DEVICE_WEIGHT_BODYFATSCALE_CLEAR_USER_DATA_2060031.value(), hashMap, 0);
    }

    public boolean b() {
        return this.p;
    }

    public void e(boolean z) {
        this.p = z;
    }

    public static void HG_(Activity activity) {
        cld cldVar = f777a;
        if (cldVar == null || cldVar.x != activity) {
            LogUtil.a("PluginDevice_WeightInteractor", "weightInteractor is null or earlier activity");
            return;
        }
        LogUtil.a("PluginDevice_WeightInteractor", "destroyWithoutDisconnect productId: ", cldVar.w, ", uniqueId: ", CommonUtil.l(cldVar.ag));
        cgt.e().b(b);
        cldVar.e();
        cldVar.c();
    }

    public static void c(String str, String str2) {
        cld cldVar = f777a;
        if (cldVar == null) {
            LogUtil.a("PluginDevice_WeightInteractor", "weightInteractor is null");
            return;
        }
        LogUtil.a("PluginDevice_WeightInteractor", "uniqueId: ", CommonUtil.l(str2), ", mUniqueId: ", CommonUtil.l(cldVar.ag));
        if (TextUtils.equals(str2, cldVar.ag)) {
            return;
        }
        cldVar.onDestroy();
        cldVar.w = str;
        cldVar.ag = str2;
    }

    static class i extends BaseHandler<cld> {
        i(cld cldVar) {
            super(cldVar);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.huawei.haf.handler.BaseHandler
        /* renamed from: HV_, reason: merged with bridge method [inline-methods] */
        public void handleMessageWhenReferenceNotNull(cld cldVar, Message message) {
            if (cldVar == null) {
                LogUtil.h("PluginDevice_WeightInteractor", "handleMessageWhenReferenceNotNull() weightInteractor is null.");
            }
            if (message == null) {
                LogUtil.h("PluginDevice_WeightInteractor", "handleMessageWhenReferenceNotNull() msg is null.");
                return;
            }
            ReleaseLogUtil.d("R_Weight_PluginDevice_WeightInteractor", "handleMessageWhenReferenceNotNull() msg is ", Integer.valueOf(message.what));
            switch (message.what) {
                case 1:
                    cldVar.g();
                    break;
                case 2:
                    cldVar.n();
                    if (cldVar.o != null) {
                        cldVar.o.sendEmptyMessage(16);
                        break;
                    }
                    break;
                case 3:
                    e(cldVar);
                    break;
                case 4:
                    if (cldVar.v != null && cldVar.v.isShowing()) {
                        if (cldVar.x != null) {
                            nrh.b(cldVar.x, R.string.IDS_plugin_device_weight_device_clear_fail);
                        }
                        cldVar.v.dismiss();
                    }
                    LogUtil.h("PluginDevice_WeightInteractor", "clear user data fail");
                    break;
                case 5:
                    b(cldVar);
                    break;
                case 6:
                    cldVar.y();
                    break;
                case 7:
                    cldVar.o();
                    break;
            }
        }

        private void e(cld cldVar) {
            if (cldVar.v != null && cldVar.v.isShowing()) {
                if (cldVar.x != null) {
                    nrh.b(cldVar.x, R.string.IDS_plugin_device_weight_device_clear_success);
                }
                cldVar.v.dismiss();
            }
            cldVar.b(false);
            LogUtil.a("PluginDevice_WeightInteractor", "clear user data success");
        }

        private boolean a(cld cldVar) {
            return cldVar.ai == 1 || cgt.e().k() == 2 || cgt.e().k() == 1;
        }

        private void b(cld cldVar) {
            if (ceo.d().d(cldVar.ag, true) != null) {
                if (cjx.e().i(cldVar.ag)) {
                    ReleaseLogUtil.e("R_Weight_PluginDevice_WeightInteractor", cpw.d(cldVar.ag), " is measuring");
                    return;
                }
                if (cldVar.e == 12 && !cldVar.p) {
                    LogUtil.h("PluginDevice_WeightInteractor", "device is not connect, try to reconnect, status = ", Integer.valueOf(cldVar.ai));
                    if (cpa.ae(cldVar.w) && a(cldVar)) {
                        cldVar.d();
                        return;
                    } else {
                        ReleaseLogUtil.d("R_Weight_PluginDevice_WeightInteractor", "device goto connect...");
                        cldVar.d(true);
                        return;
                    }
                }
                LogUtil.a("PluginDevice_WeightInteractor", "bluetooth status is close...");
                if (cldVar.p) {
                    return;
                }
                cldVar.m.sendEmptyMessage(1);
                return;
            }
            cldVar.d();
        }
    }

    static class d extends BroadcastReceiver {
        private WeakReference<cld> b;

        d(cld cldVar) {
            this.b = null;
            this.b = new WeakReference<>(cldVar);
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            cld cldVar = this.b.get();
            if (cldVar != null && !(ceo.d().d(cldVar.ag, true) instanceof cxh)) {
                LogUtil.h("PluginDevice_WeightInteractor", "current device is not ble device");
                return;
            }
            if (cldVar == null || !"android.bluetooth.adapter.action.STATE_CHANGED".equals(intent.getAction())) {
                return;
            }
            cldVar.e = intent.getIntExtra("android.bluetooth.adapter.extra.STATE", Integer.MIN_VALUE);
            if (cldVar.e != 12) {
                if (cldVar.e == 10) {
                    ReleaseLogUtil.e("R_Weight_PluginDevice_WeightInteractor", "BleDevice onConnectionStateChange :status:0,newState:0");
                    if (cldVar.p) {
                        cldVar.p = false;
                        cldVar.m.sendEmptyMessage(1);
                    }
                    cldVar.d();
                    return;
                }
                LogUtil.h("PluginDevice_WeightInteractor", "BluetoothStateBroadcastReceiver onReceive(). mBluetoothStatus: ", Integer.valueOf(cldVar.e));
                int k = cgt.e().k();
                if (k == 1 || k == 2) {
                    ReleaseLogUtil.d("R_Weight_PluginDevice_WeightInteractor", "invalid connect status: ", Integer.valueOf(k));
                    cgt.e().c();
                    return;
                }
                return;
            }
            ReleaseLogUtil.e("R_Weight_PluginDevice_WeightInteractor", "Open the bluetooth");
            cldVar.ac = 0;
            cldVar.d();
            cldVar.i();
            if (cldVar.y == null || !cldVar.y.isShowing()) {
                return;
            }
            cldVar.y.dismiss();
        }
    }

    static class a extends TimerTask {
        private WeakReference<cld> b;

        a(cld cldVar) {
            this.b = new WeakReference<>(cldVar);
        }

        @Override // java.util.TimerTask, java.lang.Runnable
        public void run() {
            cld cldVar = this.b.get();
            if (cldVar != null && cldVar.m != null) {
                cldVar.e = BluetoothAdapter.getDefaultAdapter().getState();
                LogUtil.a("PluginDevice_WeightInteractor", "weight BleDevice onConnectionStateChange Run autoReconnect");
                cldVar.m.removeMessages(5);
                cldVar.m.sendEmptyMessageDelayed(5, 1000L);
                return;
            }
            LogUtil.h("PluginDevice_WeightInteractor", "BluetoothReconnectionTimer run, weightInteractor or mHandler is null");
        }
    }

    static class c implements IHealthDeviceCallback {
        @Override // com.huawei.health.device.callback.IHealthDeviceCallback
        public void onDataChanged(HealthDevice healthDevice, HealthData healthData) {
        }

        @Override // com.huawei.health.device.callback.IHealthDeviceCallback
        public void onDataChanged(HealthDevice healthDevice, List<HealthData> list) {
        }

        @Override // com.huawei.health.device.callback.IHealthDeviceCallback
        public void onFailed(HealthDevice healthDevice, int i) {
        }

        @Override // com.huawei.health.device.callback.IHealthDeviceCallback
        public void onProgressChanged(HealthDevice healthDevice, HealthData healthData) {
        }

        private c() {
        }

        @Override // com.huawei.health.device.callback.IHealthDeviceCallback
        public void onStatusChanged(HealthDevice healthDevice, int i) {
            cld.b(healthDevice, i);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void b(HealthDevice healthDevice, int i2) {
        ReleaseLogUtil.e("R_Weight_PluginDevice_WeightInteractor", "Interactor onStatusChanged ", Integer.valueOf(System.identityHashCode(healthDevice)), ", status: ", Integer.valueOf(i2));
        cld cldVar = f777a;
        if (cldVar == null) {
            LogUtil.h("PluginDevice_WeightInteractor", "mInteractor is null");
            return;
        }
        if (i2 == 3) {
            int k = cgt.e().k();
            LogUtil.a("PluginDevice_WeightInteractor", "onStatusChange measureConnectStatus: ", Integer.valueOf(k));
            if (k == 0) {
                f777a.ai = i2;
            }
            LogUtil.a("PluginDevice_WeightInteractor", "onStatusChange mStatus: ", Integer.valueOf(f777a.ai));
            if (f777a.ai != i2) {
                f777a.ai = i2;
            }
            if (f777a.ai != 1) {
                c(1, 1, 1000);
                f777a.p = false;
                return;
            }
            return;
        }
        if (i2 == 2) {
            cldVar.ai = i2;
            c(1, 2, 0);
            f777a.p = true;
            return;
        }
        if (i2 == -3) {
            cldVar.a(false, true);
            d(3, 0);
            LogUtil.a("PluginDevice_WeightInteractor", "Clear the user data is complete");
            return;
        }
        if (i2 == 15) {
            d(6, 0);
            return;
        }
        if (i2 == 4) {
            cldVar.ai = i2;
            f777a.d();
            c(1, 1, 1000);
        } else if (i2 == 1) {
            cldVar.ai = i2;
            d(7, 0);
        } else if (i2 == 5) {
            cldVar.ai = 3;
        } else {
            LogUtil.h("PluginDevice_WeightInteractor", "WeightInteractor unknown status.");
        }
    }

    private static void c(int i2, int i3, int i4) {
        Handler handler;
        cld cldVar = f777a;
        if (cldVar == null || (handler = cldVar.m) == null) {
            return;
        }
        handler.removeMessages(i2);
        f777a.m.sendEmptyMessageDelayed(i3, i4);
    }

    private static void d(int i2, int i3) {
        Handler handler;
        cld cldVar = f777a;
        if (cldVar == null || (handler = cldVar.m) == null) {
            return;
        }
        handler.sendEmptyMessageDelayed(i2, i3);
    }

    public void h() {
        LogUtil.a("PluginDevice_WeightInteractor", "WeightInteractor subscribeWeightData to enter");
        HiHealthNativeApi.a(this.x).subscribeHiHealthData(Arrays.asList(Integer.valueOf(HiSubscribeType.c)), new e(this));
    }

    public void j() {
        LogUtil.a("PluginDevice_WeightInteractor", "WeightInteractor unSubscribeHiHealthWeightData to enter");
        if (this.x == null) {
            LogUtil.b("PluginDevice_WeightInteractor", "WeightInteractor unSubscribeHiHealthWeightData mMainActivity is null");
        } else if (!koq.b(this.an)) {
            HiHealthNativeApi.a(this.x).unSubscribeHiHealthData(this.an, new h("unSubscribeWeightData, isSuccess:"));
        } else {
            LogUtil.h("PluginDevice_WeightInteractor", "unSubscribe HiHealth WeightData list is empty or null");
        }
    }

    private void b(IBaseResponseCallback iBaseResponseCallback) {
        HiAggregateOption a2 = cff.a();
        a2.setFilter("0");
        HiHealthManager.d(BaseApplication.getContext()).aggregateHiHealthData(a2, new b(iBaseResponseCallback));
    }

    public static class e implements HiSubscribeListener {
        private WeakReference<cld> c;

        public e(cld cldVar) {
            this.c = new WeakReference<>(cldVar);
        }

        @Override // com.huawei.hihealth.data.listener.HiSubscribeListener
        public void onResult(List<Integer> list, List<Integer> list2) {
            if (this.c == null) {
                LogUtil.h("PluginDevice_WeightInteractor", "WeightInteractor InnerHiSubscribeListener onResult mInteractor is null");
                return;
            }
            if (koq.b(list)) {
                LogUtil.h("PluginDevice_WeightInteractor", "WeightInteractor subscribe HiHealthData successList is null or empty");
                return;
            }
            cld cldVar = this.c.get();
            if (cldVar != null) {
                cldVar.an = list;
            } else {
                LogUtil.h("PluginDevice_WeightInteractor", "WeightInteractor InnerHiSubscribeListener onResult interactor is null");
            }
            LogUtil.a("PluginDevice_WeightInteractor", "WeightInteractor subscribe HiHealthData successList.size() = ", Integer.valueOf(list.size()));
        }

        @Override // com.huawei.hihealth.data.listener.HiSubscribeListener
        public void onChange(int i, HiHealthClient hiHealthClient, String str, HiHealthData hiHealthData, long j) {
            if (this.c == null) {
                LogUtil.h("PluginDevice_WeightInteractor", "WeightInteractor InnerHiSubscribeListener onResult mInteractor is null");
                return;
            }
            LogUtil.a("PluginDevice_WeightInteractor", "WeightInteractor InnerHiSubscribeListener onChange");
            cld cldVar = this.c.get();
            if (cldVar == null) {
                LogUtil.h("PluginDevice_WeightInteractor", "WeightInteractor InnerHiSubscribeListener onResult interactor is null");
            } else if (i == HiSubscribeType.c) {
                cldVar.k();
            } else {
                LogUtil.h("PluginDevice_WeightInteractor", "WeightInteractor InnerHiSubscribeListener onChange not weight data type");
            }
        }
    }

    private void a(HiHealthData hiHealthData) {
        LogUtil.a("PluginDevice_WeightInteractor", "WeightInteractor refreshWeightDetailData enter");
        if (this.aj == null || this.ak == null || this.c == null || this.j == null) {
            LogUtil.h("PluginDevice_WeightInteractor", "WeightInteractor refreshWeightDetailData view = null");
            return;
        }
        if (hiHealthData == null) {
            LogUtil.h("PluginDevice_WeightInteractor", "WeightInteractor refreshWeightDetailData healtData = null");
            this.aj.setText("--");
            c(0.0d, this.ak);
            this.c.setText("--");
            this.j.setText("--");
            this.g.setVisibility(8);
            return;
        }
        LogUtil.a("PluginDevice_WeightInteractor", "refreshWeightDetailData healthData ", hiHealthData);
        e(hiHealthData);
        d(hiHealthData);
        b(hiHealthData);
        c(hiHealthData);
    }

    private void c(HiHealthData hiHealthData) {
        if (hiHealthData == null) {
            LogUtil.h("PluginDevice_WeightInteractor", "setBodyFatValue healthData = null");
            return;
        }
        double d2 = hiHealthData.getDouble(BleConstants.BODY_FAT_RATE);
        if (Double.compare(d2, 0.0d) > 0.0d) {
            LogUtil.c("PluginDevice_WeightInteractor", "refreshWeightDetailData bodyFat = ", Double.valueOf(d2));
            if (LanguageUtil.ai(this.x)) {
                this.i.setLayoutDirection(1);
            }
            if (LanguageUtil.j(this.x) || LanguageUtil.p(this.x)) {
                this.j.setText(UnitUtil.e(d2, 1, 1));
                this.g.setVisibility(0);
                return;
            } else {
                this.j.setText(UnitUtil.e(d2, 2, 1));
                this.g.setVisibility(8);
                return;
            }
        }
        LogUtil.h("PluginDevice_WeightInteractor", "refreshWeightDetailData bodyFat is valid");
        this.j.setText("--");
        this.g.setVisibility(8);
    }

    private void b(HiHealthData hiHealthData) {
        if (hiHealthData == null) {
            LogUtil.h("PluginDevice_WeightInteractor", "setBmiValue healthData = null");
            return;
        }
        double d2 = hiHealthData.getDouble(BleConstants.BMI);
        if (d2 >= 10.0d && d2 <= 60.0d) {
            LogUtil.c("PluginDevice_WeightInteractor", "refreshWeightDetailData BMI = ", Double.valueOf(d2));
            this.c.setText(UnitUtil.e(d2, 1, 1));
            return;
        }
        double d3 = hiHealthData.getDouble("bodyWeight");
        if (d3 <= 0.0d) {
            this.c.setText("--");
            return;
        }
        int i2 = hiHealthData.getInt("height");
        if (i2 <= 0) {
            b(d3);
        } else {
            c(d3, i2);
        }
    }

    private void b(double d2) {
        MultiUsersManager.INSTANCE.getCurrentUser(new f(this, d2));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(double d2, int i2) {
        if (this.c == null) {
            LogUtil.b("PluginDevice_WeightInteractor", "setBmiValueByHeight mBmiValue is null");
            return;
        }
        double a2 = dks.a(d2, i2);
        String e2 = ((double) Double.compare(a2, 0.0d)) > 0.0d ? UnitUtil.e(a2, 1, 1) : "--";
        LogUtil.c("PluginDevice_WeightInteractor", "refreshWeightDetailData bmi from weight and height, ", e2, "double ", Double.valueOf(a2));
        this.c.setText(e2);
    }

    private void d(HiHealthData hiHealthData) {
        if (hiHealthData == null) {
            LogUtil.h("PluginDevice_WeightInteractor", "setWeightValue healthData = null");
            return;
        }
        double d2 = hiHealthData.getDouble("bodyWeight");
        int e2 = cff.e(d2, hiHealthData.getInt("trackdata_deviceType"));
        if (Double.compare(d2, 0.0d) > 0.0d) {
            LogUtil.c("PluginDevice_WeightInteractor", "refreshWeightDetailData weight = ", Double.valueOf(d2));
            if (LanguageUtil.ai(this.x)) {
                this.ah.setLayoutDirection(1);
            }
            b(d2, e2, this.aj);
            c(d2, this.ak);
            return;
        }
        LogUtil.h("PluginDevice_WeightInteractor", "refreshWeightDetailData weight is valid");
        this.aj.setText("--");
        c(0.0d, this.ak);
    }

    private void e(HiHealthData hiHealthData, HealthTextView healthTextView, HealthTextView healthTextView2, HealthTextView healthTextView3) {
        LogUtil.a("PluginDevice_WeightInteractor", "WeightInteractor refreshWeightData to enter");
        if (healthTextView == null || healthTextView2 == null || healthTextView3 == null) {
            LogUtil.h("PluginDevice_WeightInteractor", "WeightInteractor refreshWeightData view is null");
            return;
        }
        if (hiHealthData == null) {
            c(healthTextView, healthTextView2, healthTextView3);
            return;
        }
        double d2 = hiHealthData.getDouble("bodyWeight");
        int i2 = hiHealthData.getInt("trackdata_deviceType");
        long startTime = hiHealthData.getStartTime();
        int e2 = cff.e(d2, i2);
        if (d2 > 0.0d) {
            b(d2, e2, healthTextView);
            c(d2, healthTextView3);
            healthTextView2.setText(nsj.a(startTime));
            healthTextView2.setVisibility(0);
            return;
        }
        c(healthTextView, healthTextView2, healthTextView3);
    }

    private void b(double d2, int i2, HealthTextView healthTextView) {
        if (d2 <= 0.0d) {
            healthTextView.setText("--");
        } else if (UnitUtil.h()) {
            healthTextView.setText(UnitUtil.e(UnitUtil.h(d2), 1, i2));
        } else {
            healthTextView.setText(UnitUtil.e(d2, 1, i2));
        }
    }

    private void c(double d2, HealthTextView healthTextView) {
        if (healthTextView == null) {
            LogUtil.h("PluginDevice_WeightInteractor", "setWeightDataMeasureValueUnit unitView is null");
        } else if (UnitUtil.h()) {
            healthTextView.setText(BaseApplication.getContext().getResources().getQuantityString(R.plurals._2130903216_res_0x7f0300b0, dks.c(UnitUtil.h(d2)), ""));
        } else {
            healthTextView.setText(BaseApplication.getContext().getResources().getQuantityString(R.plurals._2130903215_res_0x7f0300af, dks.c(d2), ""));
        }
    }

    private void c(HealthTextView healthTextView, HealthTextView healthTextView2, HealthTextView healthTextView3) {
        if (healthTextView == null || healthTextView2 == null || healthTextView3 == null) {
            LogUtil.h("PluginDevice_WeightInteractor", "showEmptyView view is null");
            return;
        }
        b(0.0d, 0, healthTextView);
        c(0.0d, healthTextView3);
        healthTextView2.setVisibility(8);
    }

    public static class h implements HiUnSubscribeListener {
        private String d;

        public h(String str) {
            this.d = str;
        }

        @Override // com.huawei.hihealth.data.listener.HiUnSubscribeListener
        public void onResult(boolean z) {
            LogUtil.a("PluginDevice_WeightInteractor", this.d, Boolean.valueOf(z));
        }
    }

    static class f implements WeightBaseResponseCallback<cfi> {
        private WeakReference<cld> b;
        private double d;

        f(cld cldVar, double d) {
            this.b = new WeakReference<>(cldVar);
            this.d = d;
        }

        @Override // com.huawei.hwbasemgr.WeightBaseResponseCallback
        /* renamed from: c, reason: merged with bridge method [inline-methods] */
        public void onResponse(final int i, final cfi cfiVar) {
            final cld cldVar = this.b.get();
            if (cldVar != null) {
                Activity activity = cldVar.x;
                if (activity == null) {
                    LogUtil.h("PluginDevice_WeightInteractor", "InnerWeightBaseResponseCallback onResponse mainActivity is null");
                    return;
                } else {
                    activity.runOnUiThread(new Runnable() { // from class: clm
                        @Override // java.lang.Runnable
                        public final void run() {
                            cld.f.this.a(cfiVar, i, cldVar);
                        }
                    });
                    return;
                }
            }
            LogUtil.h("PluginDevice_WeightInteractor", "InnerWeightBaseResponseCallback onResponse weightInteractor is null");
        }

        /* synthetic */ void a(cfi cfiVar, int i, cld cldVar) {
            if (cfiVar != null && i == 0) {
                cldVar.c(this.d, cfiVar.d());
            } else {
                LogUtil.h("PluginDevice_WeightInteractor", "InnerWeightBaseResponseCallback setBmiValue getCurrentUser fail");
            }
        }
    }

    static class b implements HiAggregateListener {
        private WeakReference<IBaseResponseCallback> d;

        b(IBaseResponseCallback iBaseResponseCallback) {
            this.d = new WeakReference<>(iBaseResponseCallback);
        }

        @Override // com.huawei.hihealth.data.listener.HiAggregateListener
        public void onResult(List<HiHealthData> list, int i, int i2) {
            IBaseResponseCallback iBaseResponseCallback = this.d.get();
            if (iBaseResponseCallback == null) {
                LogUtil.h("PluginDevice_WeightInteractor", "InnerHiAggregateListener onResult responseCallback is null");
            } else if (koq.b(list)) {
                iBaseResponseCallback.d(-1, null);
            } else {
                iBaseResponseCallback.d(0, list.get(0));
            }
        }

        @Override // com.huawei.hihealth.data.listener.HiAggregateListener
        public void onResultIntent(int i, List<HiHealthData> list, int i2, int i3) {
            LogUtil.a("PluginDevice_WeightInteractor", "InnerHiAggregateListener queryWeightData onResultIntent errorCode = ", Integer.valueOf(i2));
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (nsn.o()) {
            LogUtil.a("PluginDevice_WeightInteractor", "click too fast");
            ViewClickInstrumentation.clickOnView(view);
        } else {
            if (view == this.n) {
                ReleaseLogUtil.d("R_Weight_PluginDevice_WeightInteractor", "enterToWeightHistory");
                m();
            }
            ViewClickInstrumentation.clickOnView(view);
        }
    }

    private void m() {
        if (this.x == null) {
            LogUtil.h("PluginDevice_WeightInteractor", "enterToWeightHistory mMainActivity is null");
            return;
        }
        MultiUsersManager.INSTANCE.setCurrentUser(MultiUsersManager.INSTANCE.getMainUser());
        Intent intent = new Intent();
        intent.addFlags(268435456);
        intent.setPackage(BaseApplication.getAppPackage());
        intent.setClassName(BaseApplication.getAppPackage(), "com.huawei.ui.main.stories.health.activity.healthdata.HealthDataHistoryActivity");
        try {
            ReleaseLogUtil.c("R_Weight_PluginDevice_WeightInteractor", "enter to Weight History Activity");
            this.x.startActivity(intent);
        } catch (ActivityNotFoundException unused) {
            LogUtil.b("PluginDevice_WeightInteractor", "enterToWeightHistory ActivityNotFoundException");
        }
    }

    private void e(HiHealthData hiHealthData) {
        Activity activity = this.x;
        if (activity == null || this.r == null || this.t == null) {
            LogUtil.h("PluginDevice_WeightInteractor", "setLastMeasureTime mMainActivity is null");
            return;
        }
        if (LanguageUtil.bc(activity)) {
            this.r.setBackground(this.x.getResources().getDrawable(R.drawable._2131427841_res_0x7f0b0201));
        } else {
            this.r.setBackground(this.x.getResources().getDrawable(R.drawable._2131427842_res_0x7f0b0202));
        }
        if (hiHealthData == null) {
            LogUtil.h("PluginDevice_WeightInteractor", "setLastMeasureTime healthData is null");
        } else {
            this.t.setText(a(hiHealthData.getStartTime(), "yyyy/MM/dd HH:mm:ss"));
        }
    }

    private String a(long j, String str) {
        try {
            Date date = new Date();
            date.setTime(j);
            return new SimpleDateFormat(DateFormat.getBestDateTimePattern(Locale.getDefault(), str)).format(date);
        } catch (IllegalArgumentException unused) {
            LogUtil.b("PluginDevice_WeightInteractor", "getFormat error");
            return "";
        }
    }
}
