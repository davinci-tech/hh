package com.huawei.ui.device.activity.smartalarm;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.huawei.datatype.SmartAlarmInfo;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.health.R;
import com.huawei.health.devicemgr.business.entity.DeviceCapability;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hms.network.httpclient.util.PreConnectManager;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.R$string;
import com.huawei.ui.commonui.adapter.CheckAdapter;
import com.huawei.ui.commonui.adapter.SingleCheckAdapter;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.dialog.CommonDialog21;
import com.huawei.ui.commonui.dialog.CustomAlertDialog;
import com.huawei.ui.commonui.dialog.NoTitleCustomAlertDialog;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.timepicker.HealthTimePicker;
import com.huawei.ui.commonui.titlebar.CustomTitleBar;
import com.huawei.ui.device.activity.smartalarm.SmartAlarmClockActivity;
import com.huawei.ui.device.interactors.DeviceSettingsInteractors;
import defpackage.cvs;
import defpackage.jgh;
import defpackage.nrh;
import defpackage.nsn;
import defpackage.nsy;
import defpackage.oae;
import defpackage.oal;
import health.compact.a.CommonUtil;
import health.compact.a.LanguageUtil;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.UnitUtil;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

/* loaded from: classes6.dex */
public class SmartAlarmClockActivity extends BaseActivity implements View.OnClickListener {

    /* renamed from: a, reason: collision with root package name */
    private Context f9227a;
    private RelativeLayout ab;
    private CustomAlertDialog ad;
    private CustomAlertDialog af;
    private HealthTextView ai;
    private RelativeLayout b;
    private oal c;
    private HealthTextView e;
    private CustomTitleBar f;
    private View g;
    private DeviceSettingsInteractors i;
    private NoTitleCustomAlertDialog k;
    private CommonDialog21 m;
    private jgh o;
    private int p;
    private int q;
    private HealthTextView r;
    private int s;
    private int t;
    private int u;
    private String[] w;
    private int x;
    private int y;
    private RelativeLayout z;
    private List<SmartAlarmInfo> v = new ArrayList(16);
    private String[] ag = new String[7];
    private int ah = 31;
    private boolean[] aa = {false, true, false, false};
    private int ac = 1;
    private Handler h = new c(this);
    private String d = "";
    private boolean n = false;
    private HealthTimePicker j = null;
    private AdapterView.OnItemClickListener l = new AdapterView.OnItemClickListener() { // from class: com.huawei.ui.device.activity.smartalarm.SmartAlarmClockActivity.3
        @Override // android.widget.AdapterView.OnItemClickListener
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
            SmartAlarmClockActivity.this.ac = i;
            SmartAlarmClockActivity.this.d();
            String str = SmartAlarmClockActivity.this.w[SmartAlarmClockActivity.this.ac];
            LogUtil.a("SmartAlarmClockActivity", "mSmartWakeArrayPosition:", Integer.valueOf(SmartAlarmClockActivity.this.ac), " aheadTime:", str);
            SmartAlarmClockActivity smartAlarmClockActivity = SmartAlarmClockActivity.this;
            smartAlarmClockActivity.b(smartAlarmClockActivity.ac);
            SmartAlarmClockActivity.this.e.setText(str);
            LogUtil.a("SmartAlarmClockActivity", "dialogAheadTime onClick after replace aheadTime:", str);
            SmartAlarmClockActivity.this.n();
            ViewClickInstrumentation.clickOnListView(adapterView, view, i);
        }
    };

    class c extends Handler {
        WeakReference<SmartAlarmClockActivity> b;

        c(SmartAlarmClockActivity smartAlarmClockActivity) {
            this.b = new WeakReference<>(smartAlarmClockActivity);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            if (this.b.get() == null) {
                LogUtil.h("SmartAlarmClockActivity", "activity is null");
                return;
            }
            int i = message.what;
            if (i == 1) {
                SmartAlarmClockActivity.this.v();
                return;
            }
            if (i == 3) {
                SmartAlarmClockActivity.this.k();
                return;
            }
            if (i == 4) {
                LogUtil.h("SmartAlarmClockActivity", "save alarm timeout");
            } else if (i == 5) {
                SmartAlarmClockActivity.this.o();
            } else {
                LogUtil.a("SmartAlarmClockActivity", "handleMessage unknow message what");
            }
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_alarm_smart_clock_black);
        this.f9227a = BaseApplication.getContext();
        Intent intent = getIntent();
        if (intent != null) {
            this.d = intent.getStringExtra("device_id");
        }
        this.o = jgh.d(this.f9227a);
        this.i = DeviceSettingsInteractors.d((Context) null);
        this.c = oal.d(null);
        this.w = new String[]{UnitUtil.e(5.0d, 1, 0), UnitUtil.e(10.0d, 1, 0), UnitUtil.e(20.0d, 1, 0), UnitUtil.e(30.0d, 1, 0)};
        DeviceCapability e = cvs.e(this.d);
        if (e != null) {
            this.n = e.isSupportChangeAlarm();
        }
        f();
        this.h.sendEmptyMessageDelayed(3, 1000L);
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        LogUtil.a("SmartAlarmClockActivity", "mIsSupportChangeAlarm() ", Boolean.valueOf(this.n));
        if (this.n) {
            i();
        } else {
            h();
        }
        Intent intent = getIntent();
        if (intent != null) {
            int intExtra = intent.getIntExtra("key_to_smart_alarm_activity", 0);
            d(intExtra / 100, intExtra % 100);
        }
        e();
    }

    private void e() {
        ((ViewGroup) findViewById(R.id.smart_alarm_clock_black)).startAnimation(AnimationUtils.loadAnimation(this, R.anim._2130772059_res_0x7f01005b));
    }

    private void h() {
        LogUtil.a("SmartAlarmClockActivity", "getSmartAlarm()");
        this.o.a(new IBaseResponseCallback() { // from class: com.huawei.ui.device.activity.smartalarm.SmartAlarmClockActivity.4
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i, Object obj) {
                ReleaseLogUtil.e("R_SmartAlarmClockActivity", "getSmartAlarm() errorCode = ", Integer.valueOf(i));
                if (obj instanceof List) {
                    SmartAlarmClockActivity.this.v = (List) obj;
                }
                SmartAlarmClockActivity.this.r();
            }
        });
    }

    private void i() {
        this.i.a(this.d, new IBaseResponseCallback() { // from class: com.huawei.ui.device.activity.smartalarm.SmartAlarmClockActivity.10
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i, Object obj) {
                ReleaseLogUtil.e("R_SmartAlarmClockActivity", "getDeviceSmartAlarm() errorCode =", Integer.valueOf(i), ",objData = ", obj);
                SmartAlarmClockActivity smartAlarmClockActivity = SmartAlarmClockActivity.this;
                smartAlarmClockActivity.v = smartAlarmClockActivity.i.a(obj);
                SmartAlarmClockActivity.this.r();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void r() {
        List<SmartAlarmInfo> list = this.v;
        if (list == null || list.isEmpty()) {
            SmartAlarmInfo smartAlarmInfo = new SmartAlarmInfo();
            ArrayList arrayList = new ArrayList(16);
            this.v = arrayList;
            arrayList.add(smartAlarmInfo);
        }
        SmartAlarmInfo smartAlarmInfo2 = this.v.get(0);
        ReleaseLogUtil.e("R_SmartAlarmClockActivity", "initView mSmartAlarmList.size()", Integer.valueOf(this.v.size()));
        this.p = smartAlarmInfo2.getSmartAlarmIndex();
        this.t = smartAlarmInfo2.getSmartAlarmEnable();
        this.x = smartAlarmInfo2.getSmartAlarmStartTimeHour();
        this.u = smartAlarmInfo2.getSmartAlarmStartTimeMins();
        this.y = smartAlarmInfo2.getSmartAlarmRepeat();
        if (smartAlarmInfo2.getSmartAlarmAheadTime() == 0) {
            smartAlarmInfo2.setSmartAlarmAheadTime(5);
        }
        this.s = smartAlarmInfo2.getSmartAlarmAheadTime();
        ReleaseLogUtil.e("R_SmartAlarmClockActivity", "initData mSmartAlarmIndex =", Integer.valueOf(this.p), "initData mSmartAlarmEnable =", Integer.valueOf(this.t), "SmartAlarmClockActivity", "mSmartAlarmStartHourTime = ", Integer.valueOf(this.x), "mSmartAlarmStartMinTime =", Integer.valueOf(this.u), "mSmartAlarmRepeat =", Integer.valueOf(this.y), "mSmartAlarmAheadTime =", Integer.valueOf(this.s));
        e(this.s);
        this.ah = this.y;
        this.h.sendEmptyMessage(1);
    }

    private void f() {
        LogUtil.a("SmartAlarmClockActivity", "initView enters");
        this.f = (CustomTitleBar) nsy.cMc_(this, R.id.smart_alarm_title_bar);
        b();
        if (LanguageUtil.bc(BaseApplication.getContext())) {
            ((ImageView) nsy.cMc_(this, R.id.arrow)).setBackgroundResource(R.drawable._2131427841_res_0x7f0b0201);
            ((ImageView) nsy.cMc_(this, R.id.settings_switch)).setBackgroundResource(R.drawable._2131427841_res_0x7f0b0201);
        }
        g();
        if (nsn.ag(this.f9227a)) {
            this.z.setVisibility(8);
            this.ab.setVisibility(0);
            if (this.g.getLayoutParams() instanceof RelativeLayout.LayoutParams) {
                ((RelativeLayout.LayoutParams) this.g.getLayoutParams()).addRule(3, R.id.linear_time_wheel_bigcd);
                return;
            }
            return;
        }
        this.z.setVisibility(0);
        this.ab.setVisibility(8);
    }

    private void b() {
        this.f.setRightButtonOnClickListener(new View.OnClickListener() { // from class: com.huawei.ui.device.activity.smartalarm.SmartAlarmClockActivity.7
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (!nsn.o()) {
                    if (SmartAlarmClockActivity.this.l()) {
                        SmartAlarmClockActivity.this.m();
                    } else if (SmartAlarmClockActivity.this.t == 0) {
                        SmartAlarmClockActivity.this.t = 1;
                        SmartAlarmClockActivity.this.m();
                    } else {
                        oae c2 = oae.c(SmartAlarmClockActivity.this.f9227a);
                        if (SmartAlarmClockActivity.this.n && c2.e(SmartAlarmClockActivity.this.d) != 2) {
                            nrh.b(SmartAlarmClockActivity.this.f9227a, R.string._2130843760_res_0x7f021870);
                        }
                        ReleaseLogUtil.e("SmartAlarmClockActivity", "addTitleBarListener device is not connect.");
                        SmartAlarmClockActivity.this.finish();
                    }
                    ViewClickInstrumentation.clickOnView(view);
                    return;
                }
                LogUtil.a("SmartAlarmClockActivity", "onClick() isFastClick");
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        this.f.setLeftButtonOnClickListener(new View.OnClickListener() { // from class: com.huawei.ui.device.activity.smartalarm.SmartAlarmClockActivity.9
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (SmartAlarmClockActivity.this.l()) {
                    SmartAlarmClockActivity smartAlarmClockActivity = SmartAlarmClockActivity.this;
                    smartAlarmClockActivity.d((Context) smartAlarmClockActivity);
                } else {
                    SmartAlarmClockActivity.this.finish();
                }
                ViewClickInstrumentation.clickOnView(view);
            }
        });
    }

    private void g() {
        this.ai = (HealthTextView) nsy.cMc_(this, R.id.smart_alarm__prompt_description);
        this.e = (HealthTextView) nsy.cMc_(this, R.id.smart_alarm_ahead_time);
        this.r = (HealthTextView) nsy.cMc_(this, R.id.event_alarm_repeat);
        RelativeLayout relativeLayout = (RelativeLayout) nsy.cMc_(this, R.id.smart_alarm_ahead_time_ll);
        this.b = relativeLayout;
        relativeLayout.setOnClickListener(this);
        ((RelativeLayout) nsy.cMc_(this, R.id.smart_alarm_repeat_ll)).setOnClickListener(this);
        this.z = (RelativeLayout) nsy.cMc_(this, R.id.linear_time_wheel);
        this.ab = (RelativeLayout) nsy.cMc_(this, R.id.linear_time_wheel_bigcd);
        this.g = nsy.cMc_(this, R.id.event_alarm_clock_devide_image);
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void p() {
        Bundle bundle = new Bundle();
        bundle.putInt("smart_time", (this.j.getHour() * 100) + this.j.getMinute());
        bundle.putString("ahead_time", this.e.getText().toString());
        bundle.putInt("week_day", this.ah);
        Intent intent = new Intent();
        intent.putExtras(bundle);
        setResult(10, intent);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        LogUtil.a("SmartAlarmClockActivity", "onClick()");
        if (nsn.a(500)) {
            LogUtil.h("SmartAlarmClockActivity", "onClick() view click too fast");
            ViewClickInstrumentation.clickOnView(view);
            return;
        }
        int id = view.getId();
        if (id == R.id.smart_alarm_ahead_time_ll) {
            LogUtil.a("SmartAlarmClockActivity", "onClick() id = smart_alarm_ahead_time_ll");
            y();
        } else if (id != R.id.smart_alarm_repeat_ll) {
            LogUtil.a("SmartAlarmClockActivity", "onClick id = ", Integer.valueOf(id));
        } else {
            LogUtil.a("SmartAlarmClockActivity", "onClick() id = smart_alarm_repeat_ll");
            q();
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean l() {
        int hour = this.j.getHour();
        int minute = this.j.getMinute();
        int j = j();
        ReleaseLogUtil.e("R_SmartAlarmClockActivity", "isSmartClockChanged() aheadTime=", Integer.valueOf(j));
        List<SmartAlarmInfo> list = this.v;
        if (list == null || list.isEmpty()) {
            return false;
        }
        SmartAlarmInfo smartAlarmInfo = this.v.get(0);
        if ((hour * 100) + minute == (smartAlarmInfo.getSmartAlarmStartTimeHour() * 100) + smartAlarmInfo.getSmartAlarmStartTimeMins() && this.ah == smartAlarmInfo.getSmartAlarmRepeat() && j == smartAlarmInfo.getSmartAlarmAheadTime()) {
            ReleaseLogUtil.e("R_SmartAlarmClockActivity", "saveUiData() return with nothing changed!!!!");
            return false;
        }
        this.t = 1;
        return true;
    }

    private void q() {
        String c2 = this.c.c(Integer.toBinaryString(this.ah), 7);
        int length = c2.length();
        boolean[] zArr = new boolean[length];
        int i = 1;
        while (true) {
            boolean z = false;
            if (i >= length) {
                break;
            }
            if (c2.charAt(length - i) == '1') {
                z = true;
            }
            zArr[i] = z;
            i++;
        }
        zArr[0] = c2.charAt(0) == '1';
        this.ag = new String[]{this.f9227a.getString(R.string._2130841537_res_0x7f020fc1), this.f9227a.getString(R.string._2130841437_res_0x7f020f5d), this.f9227a.getString(R.string._2130841539_res_0x7f020fc3), this.f9227a.getString(R.string._2130841558_res_0x7f020fd6), this.f9227a.getString(R.string._2130841538_res_0x7f020fc2), this.f9227a.getString(R.string._2130841414_res_0x7f020f46), this.f9227a.getString(R.string._2130841468_res_0x7f020f7c)};
        final CheckAdapter checkAdapter = new CheckAdapter(this.f9227a, this.ag, zArr);
        ListView listView = new ListView(this.f9227a);
        listView.setDivider(null);
        listView.setAdapter((ListAdapter) checkAdapter);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2);
        layoutParams.setMargins(0, 0, 0, 0);
        listView.setLayoutParams(layoutParams);
        listView.setOnItemClickListener(new CheckAdapter.OnMultiItemClick());
        CustomAlertDialog c3 = new CustomAlertDialog.Builder(this).e(R.string._2130841510_res_0x7f020fa6).cyp_(listView).cyo_(R.string._2130841131_res_0x7f020e2b, new DialogInterface.OnClickListener() { // from class: nyg
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i2) {
                SmartAlarmClockActivity.this.cSz_(checkAdapter, dialogInterface, i2);
            }
        }).cyn_(R.string._2130841130_res_0x7f020e2a, new DialogInterface.OnClickListener() { // from class: nyh
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i2) {
                ViewClickInstrumentation.clickOnDialog(dialogInterface, i2);
            }
        }).c();
        this.af = c3;
        c3.show();
    }

    public /* synthetic */ void cSz_(CheckAdapter checkAdapter, DialogInterface dialogInterface, int i) {
        boolean[] e = checkAdapter.e();
        if (e == null) {
            LogUtil.a("SmartAlarmClockActivity", "checkedItems is null");
            ViewClickInstrumentation.clickOnDialog(dialogInterface, i);
            return;
        }
        this.ah = this.c.c(e);
        HealthTextView healthTextView = this.r;
        oal oalVar = this.c;
        healthTextView.setText(oalVar.b(oalVar.c(e)));
        ViewClickInstrumentation.clickOnDialog(dialogInterface, i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(Context context) {
        ReleaseLogUtil.e("R_SmartAlarmClockActivity", "showPromptSaveDialog()");
        NoTitleCustomAlertDialog e = new NoTitleCustomAlertDialog.Builder(context).e(R.string._2130842170_res_0x7f02123a).czC_(R.string._2130841751_res_0x7f021097, new View.OnClickListener() { // from class: com.huawei.ui.device.activity.smartalarm.SmartAlarmClockActivity.8
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ReleaseLogUtil.e("R_SmartAlarmClockActivity", "showPromptSaveDialog() Yes");
                if (SmartAlarmClockActivity.this.t == 1) {
                    SmartAlarmClockActivity.this.m();
                }
                SmartAlarmClockActivity.this.k.cancel();
                ViewClickInstrumentation.clickOnView(view);
            }
        }).czz_(R.string._2130841389_res_0x7f020f2d, new View.OnClickListener() { // from class: com.huawei.ui.device.activity.smartalarm.SmartAlarmClockActivity.6
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ReleaseLogUtil.c("R_SmartAlarmClockActivity", "showPromptSaveDialog() No");
                SmartAlarmClockActivity.this.finish();
                SmartAlarmClockActivity.this.k.cancel();
                ViewClickInstrumentation.clickOnView(view);
            }
        }).e();
        this.k = e;
        e.setCancelable(false);
        this.k.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void v() {
        ReleaseLogUtil.e("R_SmartAlarmClockActivity", "updateSmartAlarmUi() mSmartAlarmList.size()", Integer.valueOf(this.v.size()));
        if (this.v.isEmpty()) {
            LogUtil.h("SmartAlarmClockActivity", "mSmartAlarmList is null");
            return;
        }
        SmartAlarmInfo smartAlarmInfo = this.v.get(0);
        int smartAlarmStartTimeHour = smartAlarmInfo.getSmartAlarmStartTimeHour();
        int smartAlarmStartTimeMins = smartAlarmInfo.getSmartAlarmStartTimeMins();
        int smartAlarmStartTimeHour2 = smartAlarmInfo.getSmartAlarmStartTimeHour();
        int smartAlarmStartTimeMins2 = smartAlarmInfo.getSmartAlarmStartTimeMins();
        String b = oal.b(this.f9227a, (smartAlarmStartTimeHour * 100) + smartAlarmStartTimeMins);
        this.e.setText(UnitUtil.e(this.s, 1, 0));
        String b2 = oal.b(this.f9227a, this.c.d(smartAlarmStartTimeHour2, smartAlarmStartTimeMins2, smartAlarmInfo.getSmartAlarmAheadTime()));
        this.r.setText(this.c.b(this.ah));
        this.ai.setText(String.format(Locale.ENGLISH, this.f9227a.getString(R.string._2130842469_res_0x7f021365), b2, b, this.i.a(this.d)));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void m() {
        ReleaseLogUtil.e("R_SmartAlarmClockActivity", "saveUiData()");
        if (this.e == null) {
            return;
        }
        if (this.n) {
            c();
        } else {
            this.o.a(new IBaseResponseCallback() { // from class: com.huawei.ui.device.activity.smartalarm.SmartAlarmClockActivity.14
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public void d(int i, Object obj) {
                    List arrayList = new ArrayList(16);
                    if (i == 0 && (obj instanceof List)) {
                        arrayList = (List) obj;
                    }
                    SmartAlarmClockActivity.this.c((List<SmartAlarmInfo>) arrayList);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(List<SmartAlarmInfo> list) {
        this.v = list;
        SmartAlarmInfo t = t();
        ArrayList arrayList = new ArrayList(16);
        arrayList.add(t);
        if (oae.c(this.f9227a).e(this.d) != 2) {
            runOnUiThread(new Runnable() { // from class: com.huawei.ui.device.activity.smartalarm.SmartAlarmClockActivity.13
                @Override // java.lang.Runnable
                public void run() {
                    ReleaseLogUtil.e("R_SmartAlarmClockActivity", "showNoConnectedToast()");
                    nrh.b(SmartAlarmClockActivity.this.f9227a, R.string.IDS_device_not_connect);
                }
            });
        }
        this.i.e(arrayList, new IBaseResponseCallback() { // from class: com.huawei.ui.device.activity.smartalarm.SmartAlarmClockActivity.1
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i, Object obj) {
                ReleaseLogUtil.e("R_SmartAlarmClockActivity", "saveUiData() errorCode = ", Integer.valueOf(i), "objData=", obj);
                SmartAlarmClockActivity.this.p();
                SmartAlarmClockActivity.this.finish();
            }
        });
        ReleaseLogUtil.e("R_SmartAlarmClockActivity", "saveUiData() setDBAlarmClock()=", t.toString());
    }

    public void c() {
        String b = SharedPreferenceManager.b(this.f9227a, String.valueOf(10022), "DEVICE_EVENT_ALARM_INFO");
        if (!TextUtils.isEmpty(b)) {
            this.v = (List) new Gson().fromJson(b, new TypeToken<List<SmartAlarmInfo>>() { // from class: com.huawei.ui.device.activity.smartalarm.SmartAlarmClockActivity.5
            }.getType());
        }
        SmartAlarmInfo t = t();
        ArrayList arrayList = new ArrayList(16);
        arrayList.add(t);
        DeviceSettingsInteractors.d(this.f9227a).c(arrayList);
        if (oae.c(this.f9227a).e(this.d) != 2) {
            nrh.b(this.f9227a, R.string._2130843760_res_0x7f021870);
            return;
        }
        s();
        Handler handler = this.h;
        if (handler != null) {
            handler.sendEmptyMessageDelayed(4, PreConnectManager.CONNECT_INTERNAL);
        }
        DeviceSettingsInteractors.d(this.f9227a).a(this.d, arrayList, new IBaseResponseCallback() { // from class: com.huawei.ui.device.activity.smartalarm.SmartAlarmClockActivity.2
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i, Object obj) {
                ReleaseLogUtil.e("R_SmartAlarmClockActivity", "saveDeviceSamrtAlarm() errorCode = ", Integer.valueOf(i), "objData=", obj);
                if (SmartAlarmClockActivity.this.h != null) {
                    SmartAlarmClockActivity.this.h.sendEmptyMessage(5);
                }
            }
        });
        LogUtil.a("SmartAlarmClockActivity", "saveUiData() setDBAlarmClock()=", t.toString());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void o() {
        LogUtil.a("SmartAlarmClockActivity", "saveFinish Entering");
        Handler handler = this.h;
        if (handler != null && handler.hasMessages(4)) {
            this.h.removeMessages(4);
        }
        a();
        p();
        finish();
    }

    private SmartAlarmInfo t() {
        LogUtil.a("SmartAlarmClockActivity", "settingAlarm Entering");
        List<SmartAlarmInfo> list = this.v;
        if (list == null || list.isEmpty()) {
            SmartAlarmInfo smartAlarmInfo = new SmartAlarmInfo();
            ArrayList arrayList = new ArrayList(16);
            this.v = arrayList;
            arrayList.add(smartAlarmInfo);
        }
        int hour = (this.j.getHour() * 100) + this.j.getMinute();
        SmartAlarmInfo smartAlarmInfo2 = this.v.get(0);
        smartAlarmInfo2.setSmartAlarmStartTimeHour(hour / 100);
        smartAlarmInfo2.setSmartAlarmStartTimeMins(hour % 100);
        smartAlarmInfo2.setSmartAlarmRepeat(this.ah);
        smartAlarmInfo2.setSmartAlarmAheadTime(j());
        smartAlarmInfo2.setSmartAlarmEnable(1);
        return smartAlarmInfo2;
    }

    private int j() {
        String string = this.f9227a.getString(R.string._2130841134_res_0x7f020e2e);
        String obj = this.e.getText().toString();
        LogUtil.a("SmartAlarmClockActivity", "getAheadTime() strAheadText=", obj);
        if (TextUtils.isEmpty(obj) || string.equals(obj)) {
            return 0;
        }
        return CommonUtil.m(this.f9227a, obj);
    }

    private void d(int i, int i2) {
        LogUtil.a("SmartAlarmClockActivity", "initAlarmPicker= ", Integer.valueOf(i), Integer.valueOf(i2));
        this.j = (HealthTimePicker) findViewById(R.id.hw_health_timepicker);
        if (nsn.ag(this.f9227a)) {
            this.j = (HealthTimePicker) findViewById(R.id.hw_health_timepicker_bigcd);
        }
        Calendar calendar = Calendar.getInstance();
        calendar.set(11, i);
        calendar.set(12, i2);
        this.j.setTime(i, i2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void n() {
        int j = j();
        LogUtil.a("SmartAlarmClockActivity", "modifyPrompt interval=", Integer.valueOf(j));
        int hour = (this.j.getHour() * 100) + this.j.getMinute();
        String b = oal.b(this.f9227a, hour);
        this.ai.setText(String.format(Locale.ENGLISH, this.f9227a.getString(R.string._2130842469_res_0x7f021365), oal.b(this.f9227a, this.c.d(hour / 100, hour % 100, j)), b, this.i.a(this.d)));
    }

    private void y() {
        if (isFinishing()) {
            return;
        }
        SingleCheckAdapter singleCheckAdapter = new SingleCheckAdapter(this.f9227a, new String[]{getResources().getString(R.string._2130837641_res_0x7f020089, UnitUtil.e(5.0d, 1, 0)), getResources().getString(R.string._2130837641_res_0x7f020089, UnitUtil.e(10.0d, 1, 0)), getResources().getString(R.string._2130837641_res_0x7f020089, UnitUtil.e(20.0d, 1, 0)), getResources().getString(R.string._2130837641_res_0x7f020089, UnitUtil.e(30.0d, 1, 0))}, this.aa);
        ListView listView = new ListView(this.f9227a);
        listView.setDivider(null);
        listView.setAdapter((ListAdapter) singleCheckAdapter);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2);
        layoutParams.setMargins(0, 0, 0, 0);
        listView.setLayoutParams(layoutParams);
        listView.setOnItemClickListener(this.l);
        CustomAlertDialog c2 = new CustomAlertDialog.Builder(this).e(R.string._2130841482_res_0x7f020f8a).cyp_(listView).cyn_(R.string._2130841130_res_0x7f020e2a, new DialogInterface.OnClickListener() { // from class: nyc
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                SmartAlarmClockActivity.this.cSA_(dialogInterface, i);
            }
        }).c();
        this.ad = c2;
        c2.show();
        LogUtil.c("SmartAlarmClockActivity", "showSamrtWakeDialog()");
    }

    public /* synthetic */ void cSA_(DialogInterface dialogInterface, int i) {
        d();
        ViewClickInstrumentation.clickOnDialog(dialogInterface, i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d() {
        CustomAlertDialog customAlertDialog;
        if (isFinishing() || (customAlertDialog = this.ad) == null) {
            return;
        }
        customAlertDialog.cancel();
        this.ad = null;
    }

    private void e(int i) {
        ReleaseLogUtil.e("R_SmartAlarmClockActivity", "initAheadTime aheadTime = ", Integer.valueOf(i));
        if (i == 5) {
            b(0);
            return;
        }
        if (i == 10) {
            b(1);
            return;
        }
        if (i == 20) {
            b(2);
        } else if (i == 30) {
            b(3);
        } else {
            b(1);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(int i) {
        boolean[] zArr;
        int i2 = 0;
        while (true) {
            zArr = this.aa;
            if (i2 >= zArr.length) {
                break;
            }
            zArr[i2] = false;
            i2++;
        }
        if (zArr.length > i) {
            zArr[i] = true;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void k() {
        LogUtil.a("SmartAlarmClockActivity", "refreshPromt Entering");
        this.h.removeMessages(3);
        int hour = (this.j.getHour() * 100) + this.j.getMinute();
        if (this.q != hour) {
            this.q = hour;
            n();
        }
        this.h.sendEmptyMessageDelayed(3, 1000L);
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onPause() {
        super.onPause();
        LogUtil.a("SmartAlarmClockActivity", "onPause()");
    }

    @Override // android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i == 4) {
            if (l()) {
                d((Context) this);
                return false;
            }
            finish();
            return false;
        }
        return super.onKeyDown(i, keyEvent);
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        CommonUtil.a(this.f9227a);
        super.onDestroy();
        a();
        Handler handler = this.h;
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
        }
    }

    private void s() {
        LogUtil.a("showLoadingDialog", new Object[0]);
        if (this.m == null) {
            this.m = CommonDialog21.a(this);
        }
        this.m.e(this.f9227a.getResources().getString(R$string.IDS_sns_saveing));
        this.m.a();
    }

    private void a() {
        LogUtil.a("SmartAlarmClockActivity", "dismissDialog Entering");
        CommonDialog21 commonDialog21 = this.m;
        if (commonDialog21 == null || !commonDialog21.isShowing()) {
            return;
        }
        this.m.cancel();
        this.m = null;
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
