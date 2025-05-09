package com.huawei.ui.device.activity.alarm;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.huawei.datatype.EventAlarmInfo;
import com.huawei.datatype.SmartAlarmInfo;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.R;
import com.huawei.health.devicemgr.business.entity.DeviceCapability;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.dialog.CustomTextAlertDialog;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.subheader.HealthSubHeader;
import com.huawei.ui.commonui.switchbutton.HealthSwitchButton;
import com.huawei.ui.commonui.toolbar.HealthToolBar;
import com.huawei.ui.device.activity.alarm.AlarmActivity;
import com.huawei.ui.device.activity.eventalarm.EventAlarmClockActivity;
import com.huawei.ui.device.activity.smartalarm.SmartAlarmClockActivity;
import com.huawei.ui.device.interactors.DeviceSettingsInteractors;
import com.huawei.ui.device.views.alarm.AlarmListAdapter;
import defpackage.cvs;
import defpackage.cvz;
import defpackage.cwi;
import defpackage.ixx;
import defpackage.iyl;
import defpackage.jcf;
import defpackage.jgh;
import defpackage.jpp;
import defpackage.jpt;
import defpackage.jqi;
import defpackage.knl;
import defpackage.nrh;
import defpackage.nsf;
import defpackage.nsn;
import defpackage.nsy;
import defpackage.oae;
import defpackage.oal;
import defpackage.obx;
import defpackage.sqo;
import health.compact.a.CommonUtil;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.UnitUtil;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

/* loaded from: classes6.dex */
public class AlarmActivity extends BaseActivity implements AdapterView.OnItemClickListener {

    /* renamed from: a, reason: collision with root package name */
    private static String f9049a = "10";
    private static boolean b = false;
    private static String e = "7:00";
    private int ae;
    private int ai;
    private SmartAlarmInfo aj;
    private int ak;
    private int al;
    private int ao;
    private int aq;
    private HealthSwitchButton ar;
    private int as;
    private HealthToolBar av;
    private HealthSwitchButton aw;
    private oal d;
    private DeviceCapability i;
    private DeviceSettingsInteractors m;
    private DeviceSettingsInteractors n;
    private List<EventAlarmInfo> o;
    private jgh q;
    private jqi r;
    private Context g = null;
    private ListView ab = null;
    private LinearLayout am = null;
    private HealthTextView ap = null;
    private HealthTextView au = null;
    private HealthTextView af = null;
    private HealthTextView c = null;
    private HealthTextView j = null;
    private HealthTextView at = null;
    private boolean aa = false;
    private List<SmartAlarmInfo> an = new ArrayList(10);
    private List<EventAlarmInfo> k = new ArrayList(10);
    private List<obx> t = new ArrayList(10);
    private AlarmListAdapter h = null;
    private List<EventAlarmInfo> s = new ArrayList(10);
    private boolean x = false;
    private boolean z = false;
    private boolean ac = false;
    private boolean y = false;
    private boolean w = false;
    private boolean ad = false;
    private String f = "";
    private String l = "";
    private boolean v = false;
    private boolean u = false;
    private Handler p = new a(this);
    private Runnable ax = new Runnable() { // from class: com.huawei.ui.device.activity.alarm.AlarmActivity.5
        @Override // java.lang.Runnable
        public void run() {
            ReleaseLogUtil.e("R_AlarmActivity", "once set timer enter...curSecond is ", Integer.valueOf(Calendar.getInstance().get(13)));
            AlarmActivity.this.p.postDelayed(this, 60000 - ((r0 - 1) * 1000));
            AlarmActivity.this.e(false);
            AlarmActivity.this.a(false);
        }
    };
    private CompoundButton.OnCheckedChangeListener ag = new CompoundButton.OnCheckedChangeListener() { // from class: com.huawei.ui.device.activity.alarm.AlarmActivity.12
        @Override // android.widget.CompoundButton.OnCheckedChangeListener
        public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
            ReleaseLogUtil.e("R_AlarmActivity", "once onCheckedChanged isChecked = ", Boolean.valueOf(z));
            if (!AlarmActivity.this.h()) {
                nrh.b(AlarmActivity.this.g, R.string.IDS_device_not_connect);
                compoundButton.setChecked(!z);
                ViewClickInstrumentation.clickOnView(compoundButton);
                return;
            }
            boolean unused = AlarmActivity.b = z;
            boolean z2 = AlarmActivity.b;
            ArrayList arrayList = new ArrayList(10);
            if (AlarmActivity.this.aj != null) {
                AlarmActivity.this.aj.setSmartAlarmEnable(z2 ? 1 : 0);
                arrayList.add(AlarmActivity.this.aj);
                AlarmActivity.this.a(1);
                ReleaseLogUtil.e("R_AlarmActivity", "once onCheckedChanged mSmartAlarmInfo hour = ", Integer.valueOf(AlarmActivity.this.aj.getSmartAlarmStartTimeHour()), ", Mins is", Integer.valueOf(AlarmActivity.this.aj.getSmartAlarmStartTimeMins()));
                if (AlarmActivity.this.w) {
                    LogUtil.a("AlarmActivity", "mIsFromSmartEdit is", Boolean.valueOf(AlarmActivity.this.w));
                    ViewClickInstrumentation.clickOnView(compoundButton);
                    return;
                } else if (!AlarmActivity.this.ad) {
                    AlarmActivity.this.a(arrayList);
                    ViewClickInstrumentation.clickOnView(compoundButton);
                    return;
                } else {
                    ViewClickInstrumentation.clickOnView(compoundButton);
                    return;
                }
            }
            LogUtil.a("AlarmActivity", "null is mSmartAlarmInfo");
            ViewClickInstrumentation.clickOnView(compoundButton);
        }
    };
    private CompoundButton.OnCheckedChangeListener ah = new AnonymousClass20();

    /* renamed from: com.huawei.ui.device.activity.alarm.AlarmActivity$20, reason: invalid class name */
    public class AnonymousClass20 implements CompoundButton.OnCheckedChangeListener {
        AnonymousClass20() {
        }

        @Override // android.widget.CompoundButton.OnCheckedChangeListener
        public void onCheckedChanged(CompoundButton compoundButton, final boolean z) {
            ReleaseLogUtil.e("R_AlarmActivity", "onCheckedChanged syncRemind isChecked = ", Boolean.valueOf(z));
            if (AlarmActivity.this.h()) {
                AlarmActivity.this.aa = z;
                ThreadPoolManager.d().execute(new Runnable() { // from class: nup
                    @Override // java.lang.Runnable
                    public final void run() {
                        AlarmActivity.AnonymousClass20.this.b(z);
                    }
                });
                ViewClickInstrumentation.clickOnView(compoundButton);
            } else {
                ReleaseLogUtil.d("R_AlarmActivity", "onCheckedChanged no device connected");
                nrh.b(AlarmActivity.this.g, R.string.IDS_device_not_connect);
                compoundButton.setChecked(!z);
                ViewClickInstrumentation.clickOnView(compoundButton);
            }
        }

        public /* synthetic */ void b(boolean z) {
            cvz.b(AlarmActivity.this.l, z);
            AlarmActivity.this.q.d(AlarmActivity.this.g, z);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(List<SmartAlarmInfo> list) {
        if (this.z) {
            this.n.c(list);
            this.n.a(this.f, list, new IBaseResponseCallback() { // from class: com.huawei.ui.device.activity.alarm.AlarmActivity.18
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public void d(int i, Object obj) {
                    LogUtil.a("AlarmActivity", "setSmartAlarm mIsSupportChangeAlarm err_code is ", Integer.valueOf(i));
                }
            });
        } else {
            p();
            this.m.e(list, new IBaseResponseCallback() { // from class: com.huawei.ui.device.activity.alarm.AlarmActivity.17
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public void d(int i, Object obj) {
                    LogUtil.a("AlarmActivity", "setSmartAlarm() err_code is ", Integer.valueOf(i));
                }
            });
        }
    }

    class a extends Handler {

        /* renamed from: a, reason: collision with root package name */
        WeakReference<AlarmActivity> f9057a;

        a(AlarmActivity alarmActivity) {
            this.f9057a = new WeakReference<>(alarmActivity);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            if (this.f9057a.get() == null) {
                return;
            }
            int i = message.what;
            if (i == 100) {
                List<SmartAlarmInfo> list = (List) message.obj;
                LogUtil.a("AlarmActivity", "alarm message.arg1 is ", Integer.valueOf(message.arg1));
                if (message.arg1 == 1) {
                    AlarmActivity.this.q.a(list, (IBaseResponseCallback) null);
                }
                AlarmActivity.this.q();
                return;
            }
            if (i == 102) {
                AlarmActivity.this.cPg_(message);
                return;
            }
            if (i != 104) {
                if (i != 105) {
                    return;
                }
                ReleaseLogUtil.e("R_AlarmActivity", "MESSAGE_UPDATE_EVENT_DATA");
                AlarmActivity.this.n.b(AlarmActivity.this.k);
                nrh.e(AlarmActivity.this.g, R.string._2130842375_res_0x7f021307);
                return;
            }
            ReleaseLogUtil.e("R_AlarmActivity", "MESSAGE_UPDATE_EVENT_UI_COMMAND mEventAlarmDbList.size() is ", Integer.valueOf(AlarmActivity.this.k.size()));
            AlarmActivity.this.l();
            if (AlarmActivity.this.h == null) {
                return;
            }
            AlarmActivity.this.h.c(AlarmActivity.this.t);
            AlarmActivity.this.h.notifyDataSetChanged();
            boolean z = AlarmActivity.this.k.size() >= jgh.d(BaseApplication.getContext()).i();
            AlarmActivity.this.d(z);
            ReleaseLogUtil.e("AlarmActivity", "isListOverSize is: ", Boolean.valueOf(z));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void cPg_(Message message) {
        ReleaseLogUtil.e("R_AlarmActivity", "MESSAGE_UPDATE_EVENT_ALARM_UI mAlarmListAdapter is ", this.h, " mEventAlarmItemList is ", this.t);
        List<obx> arrayList = new ArrayList(10);
        if (message.obj instanceof List) {
            arrayList = (List) message.obj;
        }
        List<obx> list = this.t;
        if (list == null) {
            this.t = new ArrayList(10);
        } else {
            list.clear();
        }
        for (obx obxVar : arrayList) {
            if (this.t.size() < jgh.d(BaseApplication.getContext()).i()) {
                this.t.add(obxVar);
            }
        }
        ReleaseLogUtil.e("R_AlarmActivity", " mAlarmListAdapter is ", this.h, " mEventAlarmItemList.size()", Integer.valueOf(this.t.size()));
        AlarmListAdapter alarmListAdapter = this.h;
        if (alarmListAdapter != null) {
            alarmListAdapter.c(this.t);
            this.h.notifyDataSetChanged();
        } else {
            AlarmListAdapter alarmListAdapter2 = new AlarmListAdapter(this, this.t);
            this.h = alarmListAdapter2;
            this.ab.setAdapter((ListAdapter) alarmListAdapter2);
        }
        this.h.c(new AlarmListAdapter.OnAlarmSwitchClickListener() { // from class: com.huawei.ui.device.activity.alarm.AlarmActivity.19
            @Override // com.huawei.ui.device.views.alarm.AlarmListAdapter.OnAlarmSwitchClickListener
            public void onAlarmSwitchClick(View view) {
                AlarmActivity.this.onClickEventAlarmSwitch(view);
            }
        });
        ReleaseLogUtil.e("R_AlarmActivity", "mEventAlarmItemList.size() is ", Integer.valueOf(this.t.size()));
        d(this.t.size() >= jgh.d(BaseApplication.getContext()).i());
        LogUtil.a("AlarmActivity", "alarm message.arg1 is ", Integer.valueOf(message.arg1), "alarm mIsBackground is ", Boolean.valueOf(this.x));
        if (!this.x) {
            p();
        }
        if (message.arg1 == 1) {
            this.q.c(this.k, (IBaseResponseCallback) null);
        }
        if (this.u) {
            a(2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(boolean z) {
        if (z) {
            this.av.setEnabled(false);
            this.av.setIcon(2, R.mipmap._2131820811_res_0x7f11010b);
            this.av.setIconTitleColor(2, this.g.getResources().getString(R.string._2130841394_res_0x7f020f32), R.color._2131297376_res_0x7f090460);
        } else {
            this.av.setEnabled(true);
            this.av.setIcon(2, R.drawable._2131429693_res_0x7f0b093d);
            this.av.setIconTitleColor(2, this.g.getResources().getString(R.string._2130841394_res_0x7f020f32), R.color._2131299241_res_0x7f090ba9);
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.g = BaseApplication.getContext();
        ReleaseLogUtil.e("R_AlarmActivity", "onCreate()");
        setContentView(R.layout.activity_alarm_black);
        Intent intent = getIntent();
        if (intent != null) {
            String stringExtra = intent.getStringExtra("device_id");
            this.f = stringExtra;
            obx.b(stringExtra);
        } else if (bundle != null && !TextUtils.isEmpty(bundle.getString("deviceId", ""))) {
            String string = bundle.getString("deviceId");
            this.f = string;
            obx.b(string);
            ReleaseLogUtil.e("R_AlarmActivity", "onCreate getIntent() is null and reSave");
        } else {
            ReleaseLogUtil.d("R_AlarmActivity", "onCreate mCurrentDeviceId is empty!");
        }
        String d = knl.d(this.f);
        this.l = d;
        if (TextUtils.isEmpty(d)) {
            ReleaseLogUtil.d("R_AlarmActivity", "findSyncView mEncryDeviceId is null!");
        }
        this.m = DeviceSettingsInteractors.d((Context) null);
        this.d = oal.d(null);
        this.r = jqi.a();
        jgh d2 = jgh.d(this.g);
        this.q = d2;
        this.ai = d2.i();
        this.n = DeviceSettingsInteractors.d(this.g);
        ReleaseLogUtil.e("AlarmActivity", "mCurrentDeviceId is", iyl.d().e(this.f));
        DeviceCapability e2 = cvs.e(this.f);
        this.i = e2;
        if (e2 != null) {
            this.z = e2.isSupportChangeAlarm();
            this.ac = this.i.isSmartAlarm();
            this.y = this.i.isEventAlarm();
            ReleaseLogUtil.e("R_AlarmActivity", "onCreate isSmartAlarm is ", Boolean.valueOf(this.ac), ", isEventAlarm is ", Boolean.valueOf(this.y));
        } else {
            ReleaseLogUtil.d("R_AlarmActivity", "onCreate mDeviceCapability is null");
        }
        j();
        ReleaseLogUtil.e("R_AlarmActivity", "mIsSupportChangeAlarm is", Boolean.valueOf(this.z), "mMaxAlarmNumber is ", Integer.valueOf(this.ai));
        if (this.z) {
            o();
            n();
        } else {
            LogUtil.a("AlarmActivity", "once curSecond is ", Integer.valueOf(Calendar.getInstance().get(13)));
            this.p.postDelayed(this.ax, 60000 - ((r6 - 1) * 1000));
            LogUtil.a("AlarmActivity", "once send message alarm time");
        }
    }

    private void o() {
        this.n.d(this.f, new IBaseResponseCallback() { // from class: com.huawei.ui.device.activity.alarm.AlarmActivity.16
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i, Object obj) {
                ReleaseLogUtil.e("R_AlarmActivity", "errorCode is", Integer.valueOf(i));
                AlarmActivity alarmActivity = AlarmActivity.this;
                alarmActivity.k = alarmActivity.n.d(obj);
                ReleaseLogUtil.e("R_AlarmActivity", "eventAlarmList is", Integer.valueOf(AlarmActivity.this.k.size()));
                ArrayList arrayList = new ArrayList(10);
                AlarmActivity.this.s.clear();
                for (int i2 = 0; i2 < AlarmActivity.this.k.size(); i2++) {
                    EventAlarmInfo eventAlarmInfo = (EventAlarmInfo) AlarmActivity.this.k.get(i2);
                    if (TextUtils.isEmpty(eventAlarmInfo.getEventAlarmName())) {
                        eventAlarmInfo.setEventAlarmName(AlarmActivity.this.getString(R.string._2130841509_res_0x7f020fa5));
                    }
                    AlarmActivity.this.s.add(eventAlarmInfo);
                    obx obxVar = new obx();
                    arrayList.add(obxVar.b(obxVar, eventAlarmInfo, AlarmActivity.this.d, AlarmActivity.this.k, i2));
                }
                AlarmActivity.this.k.clear();
                AlarmActivity.this.k.addAll(AlarmActivity.this.s);
                AlarmActivity.this.n.b(AlarmActivity.this.k);
                Message obtainMessage = AlarmActivity.this.p.obtainMessage();
                AlarmActivity.this.h = null;
                obtainMessage.what = 102;
                obtainMessage.obj = arrayList;
                obtainMessage.arg1 = 0;
                AlarmActivity.this.p.sendMessage(obtainMessage);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(List<EventAlarmInfo> list) {
        LogUtil.a("AlarmActivity", "Enter refreshEventAlarmUI_spData");
        ArrayList arrayList = new ArrayList(10);
        this.s.clear();
        for (int i = 0; i < list.size(); i++) {
            EventAlarmInfo eventAlarmInfo = list.get(i);
            if (TextUtils.isEmpty(eventAlarmInfo.getEventAlarmName())) {
                eventAlarmInfo.setEventAlarmName(getString(R.string._2130841509_res_0x7f020fa5));
            }
            this.s.add(eventAlarmInfo);
            obx obxVar = new obx();
            arrayList.add(obxVar.b(obxVar, eventAlarmInfo, this.d, list, i));
        }
        list.clear();
        list.addAll(this.s);
        this.n.b(list);
        Message obtainMessage = this.p.obtainMessage();
        this.h = null;
        obtainMessage.what = 102;
        obtainMessage.obj = arrayList;
        obtainMessage.arg1 = 0;
        this.p.sendMessage(obtainMessage);
    }

    private void n() {
        List<SmartAlarmInfo> list = this.an;
        if (list == null || list.isEmpty()) {
            SmartAlarmInfo smartAlarmInfo = new SmartAlarmInfo();
            ArrayList arrayList = new ArrayList(10);
            this.an = arrayList;
            arrayList.add(smartAlarmInfo);
        }
        if (!this.an.isEmpty()) {
            SmartAlarmInfo smartAlarmInfo2 = this.an.get(0);
            this.aj = smartAlarmInfo2;
            this.ak = smartAlarmInfo2.getSmartAlarmIndex();
            this.al = this.aj.getSmartAlarmEnable();
            this.ao = this.aj.getSmartAlarmStartTimeHour();
            this.aq = this.aj.getSmartAlarmStartTimeMins();
            this.as = this.aj.getSmartAlarmRepeat();
            this.ae = this.aj.getSmartAlarmAheadTime();
            if (this.as == 0 && this.al == 1) {
                this.al = a(this.aj);
            }
            Message obtainMessage = this.p.obtainMessage();
            obtainMessage.what = 100;
            this.p.sendMessage(obtainMessage);
        } else {
            LogUtil.a("AlarmActivity", "refreshSmartAlarmUi() deviceSmartAlarmList is null or size is 0");
        }
        i();
    }

    private void i() {
        this.n.a(this.f, new IBaseResponseCallback() { // from class: com.huawei.ui.device.activity.alarm.AlarmActivity.22
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i, Object obj) {
                ReleaseLogUtil.e("R_AlarmActivity", "errorCode is ", Integer.valueOf(i));
                List<SmartAlarmInfo> a2 = AlarmActivity.this.n.a(obj);
                ReleaseLogUtil.e("R_AlarmActivity", "smartAlarmList ", a2, "mSmartAlarmList is ", AlarmActivity.this.an);
                if ((a2 == null || a2.isEmpty()) && AlarmActivity.this.ac) {
                    AlarmActivity.this.n.a(AlarmActivity.this.f, AlarmActivity.this.an, new IBaseResponseCallback() { // from class: com.huawei.ui.device.activity.alarm.AlarmActivity.22.2
                        @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                        /* renamed from: onResponse */
                        public void d(int i2, Object obj2) {
                            ReleaseLogUtil.e("R_AlarmActivity", "setSmartAlarm mIsSupportChangeAlarm errorCode is ", Integer.valueOf(i2));
                        }
                    });
                    return;
                }
                AlarmActivity.this.an = a2;
                if (!AlarmActivity.this.an.isEmpty()) {
                    AlarmActivity alarmActivity = AlarmActivity.this;
                    alarmActivity.aj = (SmartAlarmInfo) alarmActivity.an.get(0);
                    AlarmActivity alarmActivity2 = AlarmActivity.this;
                    alarmActivity2.ak = alarmActivity2.aj.getSmartAlarmIndex();
                    AlarmActivity alarmActivity3 = AlarmActivity.this;
                    alarmActivity3.al = alarmActivity3.aj.getSmartAlarmEnable();
                    AlarmActivity alarmActivity4 = AlarmActivity.this;
                    alarmActivity4.ao = alarmActivity4.aj.getSmartAlarmStartTimeHour();
                    AlarmActivity alarmActivity5 = AlarmActivity.this;
                    alarmActivity5.aq = alarmActivity5.aj.getSmartAlarmStartTimeMins();
                    AlarmActivity alarmActivity6 = AlarmActivity.this;
                    alarmActivity6.as = alarmActivity6.aj.getSmartAlarmRepeat();
                    if (AlarmActivity.this.aj.getSmartAlarmAheadTime() == 0) {
                        AlarmActivity.this.aj.setSmartAlarmAheadTime(5);
                    }
                    AlarmActivity alarmActivity7 = AlarmActivity.this;
                    alarmActivity7.ae = alarmActivity7.aj.getSmartAlarmAheadTime();
                    Message obtainMessage = AlarmActivity.this.p.obtainMessage();
                    obtainMessage.what = 100;
                    AlarmActivity.this.p.sendMessage(obtainMessage);
                    ReleaseLogUtil.e("R_AlarmActivity", "mSmartAlarmList.size() ", Integer.valueOf(AlarmActivity.this.an.size()), "mSmartAlarmEnable", Integer.valueOf(AlarmActivity.this.al));
                    return;
                }
                ReleaseLogUtil.e("R_AlarmActivity", "refreshSmartAlarmUi() deviceSmartAlarmList is null or size is 0");
            }
        });
    }

    public void onClickEventAlarmSwitch(View view) {
        if (view == null) {
            return;
        }
        HealthSwitchButton healthSwitchButton = view instanceof HealthSwitchButton ? (HealthSwitchButton) view : null;
        if (!h()) {
            nrh.b(this.g, R.string.IDS_device_not_connect);
            if (healthSwitchButton != null) {
                healthSwitchButton.setChecked(!healthSwitchButton.isChecked());
                return;
            }
            return;
        }
        int intValue = view.getTag() instanceof Integer ? ((Integer) view.getTag()).intValue() : 0;
        if (healthSwitchButton != null) {
            boolean isChecked = healthSwitchButton.isChecked();
            ReleaseLogUtil.e("R_AlarmActivity", "onClickEventAlarmSwitch() position is ", Integer.valueOf(intValue), ",isChecked is ", Boolean.valueOf(isChecked));
            d(intValue, isChecked);
        }
    }

    private void d(int i, boolean z) {
        if (i < 0 || i >= this.k.size()) {
            return;
        }
        LogUtil.a("AlarmActivity", "updateEventAlarm()");
        EventAlarmInfo eventAlarmInfo = this.k.get(i);
        if (z) {
            eventAlarmInfo.setEventAlarmEnable(1);
        } else {
            eventAlarmInfo.setEventAlarmEnable(0);
        }
        eventAlarmInfo.setModifiedTimestamp(System.currentTimeMillis());
        this.k.set(i, eventAlarmInfo);
        if (this.z) {
            this.n.b(this.k);
            this.n.c(this.f, this.k, new IBaseResponseCallback() { // from class: com.huawei.ui.device.activity.alarm.AlarmActivity.25
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public void d(int i2, Object obj) {
                    ReleaseLogUtil.e("R_AlarmActivity", "updateEventAlarm mIsSupportChangeAlarm errorCode is ", Integer.valueOf(i2));
                }
            });
        } else {
            p();
            this.m.b(this.k, new IBaseResponseCallback() { // from class: com.huawei.ui.device.activity.alarm.AlarmActivity.4
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public void d(int i2, Object obj) {
                    ReleaseLogUtil.e("R_AlarmActivity", "updateEventAlarm() errorCode is ", Integer.valueOf(i2));
                }
            });
        }
        if (jcf.c()) {
            k();
            return;
        }
        Message obtainMessage = this.p.obtainMessage();
        obtainMessage.what = 104;
        this.p.sendMessage(obtainMessage);
    }

    private void k() {
        l();
        AlarmListAdapter alarmListAdapter = this.h;
        if (alarmListAdapter == null) {
            return;
        }
        alarmListAdapter.c(this.t);
    }

    private void j() {
        f();
        d();
        this.j.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.ui.device.activity.alarm.AlarmActivity.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (!nsn.o()) {
                    AlarmActivity.this.w = false;
                    AlarmActivity.this.ad = false;
                    if (!AlarmActivity.this.ar.isChecked()) {
                        AlarmActivity.this.ar.setChecked(true);
                        AlarmActivity.this.b(true);
                    } else {
                        AlarmActivity.this.e();
                    }
                    ViewClickInstrumentation.clickOnView(view);
                    return;
                }
                LogUtil.a("AlarmActivity", "onClick() isFastClick");
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        this.at.setOnClickListener(new View.OnClickListener() { // from class: num
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                AlarmActivity.this.cPi_(view);
            }
        });
        this.am.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.ui.device.activity.alarm.AlarmActivity.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                AlarmActivity.this.m();
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        this.ab.setOnItemClickListener(this);
        this.av.setOnSingleTapListener(new HealthToolBar.OnSingleTapListener() { // from class: com.huawei.ui.device.activity.alarm.AlarmActivity.2
            @Override // com.huawei.ui.commonui.toolbar.HealthToolBar.OnSingleTapListener
            public void onSingleTap(int i) {
                if (i == 2) {
                    if (!AlarmActivity.this.h()) {
                        nrh.b(AlarmActivity.this.g, R.string.IDS_device_not_connect);
                    } else {
                        AlarmActivity.this.c();
                    }
                }
            }
        });
    }

    public /* synthetic */ void cPi_(View view) {
        if (nsn.a(500)) {
            LogUtil.a("AlarmActivity", "mSyncRemindCoverView onClick() isFastClick");
            ViewClickInstrumentation.clickOnView(view);
        } else {
            if (this.aa) {
                this.aw.setChecked(false);
            } else {
                this.aw.setChecked(true);
            }
            ViewClickInstrumentation.clickOnView(view);
        }
    }

    private void f() {
        this.av = (HealthToolBar) findViewById(R.id.buttomview);
        this.av.cHc_(View.inflate(this.g, R.layout.hw_toolbar_bottomview, null));
        this.av.setIconVisible(1, 4);
        this.av.setIconVisible(3, 4);
        this.av.setIcon(2, R.drawable._2131429693_res_0x7f0b093d);
        this.av.setIconTitle(2, this.g.getResources().getString(R.string._2130841394_res_0x7f020f32));
        this.av.cHf_(this);
        this.ap = (HealthTextView) nsy.cMc_(this, R.id.smart_alarm_time1);
        this.au = (HealthTextView) nsy.cMc_(this, R.id.smart_alarm_day);
        this.am = (LinearLayout) nsy.cMc_(this, R.id.smart_alarm_text);
        this.ar = (HealthSwitchButton) nsy.cMc_(this, R.id.smart_alarm_switch_button);
        this.j = (HealthTextView) nsy.cMc_(this, R.id.tv_switch_cover_button);
        this.ab = (ListView) nsy.cMc_(this, R.id.event_alarm_list);
        this.af = (HealthTextView) nsy.cMc_(this, R.id.smart_alarm_promt_description);
        View inflate = View.inflate(this.g, R.layout.activity_event_clock_footer, null);
        this.c = (HealthTextView) inflate.findViewById(R.id.alarm_bottom_tv);
        String e2 = UnitUtil.e(this.ai, 1, 0);
        Context context = this.g;
        String string = context.getString(R.string._2130842783_res_0x7f02149f, DeviceSettingsInteractors.d(context).a(this.f), e2);
        this.c.setText(string);
        jcf.bEz_(this.c, string);
        ReleaseLogUtil.e("R_AlarmActivity", "mAlarmBottomTextView is:", this.c);
        this.h = new AlarmListAdapter(this, this.t);
        this.ab.addFooterView(inflate);
        this.ab.setAdapter((ListAdapter) this.h);
        LinearLayout linearLayout = (LinearLayout) nsy.cMc_(this, R.id.settings_smart_alarm_layout);
        LinearLayout linearLayout2 = (LinearLayout) nsy.cMc_(this, R.id.smart_alarm_prompt_line);
        HealthSubHeader healthSubHeader = (HealthSubHeader) nsy.cMc_(this, R.id.settings_smart_alarm_text);
        HealthSubHeader healthSubHeader2 = (HealthSubHeader) nsy.cMc_(this, R.id.event_alarm_title);
        ReleaseLogUtil.e("R_AlarmActivity", "findView isSmartAlarm is ", Boolean.valueOf(this.ac), ", isEventAlarm is ", Boolean.valueOf(this.y));
        if (this.ac) {
            linearLayout.setVisibility(0);
            linearLayout2.setVisibility(0);
            healthSubHeader.setVisibility(0);
        }
        if (this.y) {
            this.ab.setVisibility(0);
            this.c.setVisibility(0);
            this.av.setVisibility(0);
        }
        if (this.ac && this.y) {
            healthSubHeader2.setVisibility(0);
        }
    }

    private void d() {
        this.at = (HealthTextView) nsy.cMc_(this, R.id.alarm_sync_remind_switch_view);
        this.aw = (HealthSwitchButton) nsy.cMc_(this, R.id.alarm_sync_remind_switch_button);
        View cMc_ = nsy.cMc_(this, R.id.alarm_sync_remind_splitter);
        LinearLayout linearLayout = (LinearLayout) nsy.cMc_(this, R.id.alarm_sync_remind_layout);
        LinearLayout linearLayout2 = (LinearLayout) nsy.cMc_(this, R.id.alarm_sync_remind_prompt_line);
        HealthTextView healthTextView = (HealthTextView) nsy.cMc_(this, R.id.alarm_sync_remind_healthTextView);
        DeviceInfo a2 = jpt.a("AlarmActivity");
        if (a2 == null) {
            ReleaseLogUtil.d("R_AlarmActivity", "findSyncView deviceInfo is null!");
            return;
        }
        if (jpp.e(a2)) {
            healthTextView.setText(R.string._2130846772_res_0x7f022434);
        } else {
            healthTextView.setText(R.string._2130846773_res_0x7f022435);
        }
        boolean c = cwi.c(a2, 200);
        boolean c2 = cvz.c();
        ReleaseLogUtil.e("R_AlarmActivity", "findSyncView isSupportSyncRemind is: ", Boolean.valueOf(c), " isSupportMiddleWear is:", Boolean.valueOf(c2));
        if (c2 && c && this.q.g()) {
            cMc_.setVisibility(0);
            linearLayout.setVisibility(0);
            linearLayout2.setVisibility(0);
            int a3 = cvz.a(this.l);
            ReleaseLogUtil.e("R_AlarmActivity", "findSyncView syncRemindStatus from db is ", Integer.valueOf(a3));
            this.aa = a3 != 0;
            this.aw.setOnCheckedChangeListener(null);
            this.aw.setChecked(this.aa);
            this.aw.setOnCheckedChangeListener(this.ah);
        }
    }

    public void e() {
        DeviceInfo a2 = jpt.a("AlarmActivity");
        if (a2 != null) {
            this.r.getSwitchSetting("intelligent_home_linkage", a2.getDeviceIdentify(), new IBaseResponseCallback() { // from class: com.huawei.ui.device.activity.alarm.AlarmActivity.9
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public void d(int i, Object obj) {
                    LogUtil.a("AlarmActivity", "checkIntelligentStatus errorCode = ", Integer.valueOf(i));
                    final boolean a3 = AlarmActivity.this.a(i, obj);
                    AlarmActivity.this.runOnUiThread(new Runnable() { // from class: com.huawei.ui.device.activity.alarm.AlarmActivity.9.2
                        @Override // java.lang.Runnable
                        public void run() {
                            if (!a3) {
                                AlarmActivity.this.ar.setChecked(false);
                                AlarmActivity.this.b(false);
                            } else {
                                AlarmActivity.this.b();
                            }
                        }
                    });
                }
            });
        } else {
            this.ar.setChecked(false);
            b(false);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean h() {
        return oae.c(this.g).e(this.f) == 2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean a(int i, Object obj) {
        if (i != 0 || !(obj instanceof String)) {
            return false;
        }
        String str = (String) obj;
        if (!str.contains("&&")) {
            return false;
        }
        String[] split = str.split("&&");
        LogUtil.a("AlarmActivity", "INTELLIGENT_HOME_LINKAGE splits is ", Integer.valueOf(split.length));
        return split.length == 5 && Boolean.parseBoolean(split[3]);
    }

    public void b() {
        CustomTextAlertDialog a2 = new CustomTextAlertDialog.Builder(this).b(R.string.IDS_service_area_notice_title).d(R.string.IDS_device_to_intelligent_home_linkage_alarm_notice).cyU_(R.string._2130841235_res_0x7f020e93, new View.OnClickListener() { // from class: com.huawei.ui.device.activity.alarm.AlarmActivity.10
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LogUtil.a("AlarmActivity", "DownloadInstelligentHomeDialog ok click");
                AlarmActivity.this.ar.setChecked(false);
                AlarmActivity.this.b(false);
                ViewClickInstrumentation.clickOnView(view);
            }
        }).cyR_(R.string._2130841939_res_0x7f021153, new View.OnClickListener() { // from class: com.huawei.ui.device.activity.alarm.AlarmActivity.7
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LogUtil.a("AlarmActivity", "DownloadInstelligentHomeDialog cancel click");
                ViewClickInstrumentation.clickOnView(view);
            }
        }).a();
        a2.setCancelable(false);
        a2.show();
    }

    private void g() {
        if (this.z) {
            return;
        }
        e(true);
        a(true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(boolean z) {
        if (jcf.c()) {
            String h = nsf.h(z ? R.string._2130847334_res_0x7f022666 : R.string._2130850611_res_0x7f023333);
            String h2 = nsf.h(R.string._2130847333_res_0x7f022665);
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append(h);
            stringBuffer.append(h2);
            jcf.bEz_(this.j, stringBuffer);
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        LogUtil.a("AlarmActivity", "onResume()");
        this.x = false;
        g();
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onPause() {
        super.onPause();
        LogUtil.a("AlarmActivity", "onPause()");
        this.x = true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(boolean z) {
        LogUtil.a("AlarmActivity", "getEventAlarm() isNeedBiEvent is ", Boolean.valueOf(z));
        this.u = z;
        this.q.d(new IBaseResponseCallback() { // from class: com.huawei.ui.device.activity.alarm.AlarmActivity.6
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i, Object obj) {
                LogUtil.a("AlarmActivity", "getEventAlarm errorCode is ", Integer.valueOf(i));
                ArrayList arrayList = new ArrayList(10);
                if (i == 0 && (obj instanceof List)) {
                    AlarmActivity.this.k = (List) obj;
                }
                LogUtil.a("AlarmActivity", "getEventAlarm() mEventAlarmDbList.size() is ", Integer.valueOf(AlarmActivity.this.k.size()));
                int i2 = 0;
                for (int i3 = 0; i3 < AlarmActivity.this.k.size(); i3++) {
                    EventAlarmInfo eventAlarmInfo = (EventAlarmInfo) AlarmActivity.this.k.get(i3);
                    obx obxVar = new obx();
                    obx b2 = obxVar.b(obxVar, eventAlarmInfo, AlarmActivity.this.d, AlarmActivity.this.k, i3);
                    if (i2 == 0) {
                        i2 = b2.i();
                    }
                    arrayList.add(b2);
                }
                Message obtainMessage = AlarmActivity.this.p.obtainMessage();
                obtainMessage.what = 102;
                obtainMessage.obj = arrayList;
                obtainMessage.arg1 = i2;
                AlarmActivity.this.p.sendMessage(obtainMessage);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(boolean z) {
        LogUtil.a("AlarmActivity", "getSmartAlarm() isNeedBiEvent is ", Boolean.valueOf(z));
        this.v = z;
        this.q.a(new IBaseResponseCallback() { // from class: com.huawei.ui.device.activity.alarm.AlarmActivity.8
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i, Object obj) {
                LogUtil.a("AlarmActivity", "getSmartAlarm errorCode is ", Integer.valueOf(i));
                if (obj instanceof List) {
                    AlarmActivity.this.an = (List) obj;
                }
                if (AlarmActivity.this.an == null || AlarmActivity.this.an.isEmpty()) {
                    SmartAlarmInfo smartAlarmInfo = new SmartAlarmInfo();
                    AlarmActivity.this.an = new ArrayList(10);
                    AlarmActivity.this.an.add(smartAlarmInfo);
                }
                LogUtil.a("AlarmActivity", "mSmartAlarmList.size() ", Integer.valueOf(AlarmActivity.this.an.size()));
                if (!AlarmActivity.this.an.isEmpty()) {
                    SmartAlarmInfo smartAlarmInfo2 = new SmartAlarmInfo();
                    AlarmActivity alarmActivity = AlarmActivity.this;
                    int i2 = 0;
                    alarmActivity.aj = (SmartAlarmInfo) alarmActivity.an.get(0);
                    AlarmActivity alarmActivity2 = AlarmActivity.this;
                    alarmActivity2.ak = alarmActivity2.aj.getSmartAlarmIndex();
                    smartAlarmInfo2.setSmartAlarmIndex(AlarmActivity.this.ak);
                    AlarmActivity alarmActivity3 = AlarmActivity.this;
                    alarmActivity3.al = alarmActivity3.aj.getSmartAlarmEnable();
                    smartAlarmInfo2.setSmartAlarmEnable(AlarmActivity.this.al);
                    AlarmActivity alarmActivity4 = AlarmActivity.this;
                    alarmActivity4.ao = alarmActivity4.aj.getSmartAlarmStartTimeHour();
                    smartAlarmInfo2.setSmartAlarmStartTimeHour(AlarmActivity.this.ao);
                    AlarmActivity alarmActivity5 = AlarmActivity.this;
                    alarmActivity5.aq = alarmActivity5.aj.getSmartAlarmStartTimeMins();
                    smartAlarmInfo2.setSmartAlarmStartTimeMins(AlarmActivity.this.aq);
                    AlarmActivity alarmActivity6 = AlarmActivity.this;
                    alarmActivity6.as = alarmActivity6.aj.getSmartAlarmRepeat();
                    smartAlarmInfo2.setSmartAlarmRepeat(AlarmActivity.this.as);
                    if (AlarmActivity.this.aj.getSmartAlarmAheadTime() == 0) {
                        AlarmActivity.this.aj.setSmartAlarmAheadTime(5);
                    }
                    AlarmActivity alarmActivity7 = AlarmActivity.this;
                    alarmActivity7.ae = alarmActivity7.aj.getSmartAlarmAheadTime();
                    smartAlarmInfo2.setSmartAlarmAheadTime(AlarmActivity.this.ae);
                    if (AlarmActivity.this.as == 0 && AlarmActivity.this.al == 1) {
                        AlarmActivity alarmActivity8 = AlarmActivity.this;
                        alarmActivity8.al = alarmActivity8.a(alarmActivity8.aj);
                        smartAlarmInfo2.setSmartAlarmEnable(AlarmActivity.this.al);
                        if (AlarmActivity.this.al == 0) {
                            i2 = 1;
                        }
                    }
                    ArrayList arrayList = new ArrayList(10);
                    arrayList.add(smartAlarmInfo2);
                    AlarmActivity.this.c(arrayList, i2);
                    return;
                }
                LogUtil.h("AlarmActivity", "getSmartAlarm() mSmartAlarmList is null or size is 0");
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(List<SmartAlarmInfo> list, int i) {
        Message obtainMessage = this.p.obtainMessage();
        obtainMessage.what = 100;
        obtainMessage.obj = list;
        obtainMessage.arg1 = i;
        this.p.sendMessage(obtainMessage);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int a(SmartAlarmInfo smartAlarmInfo) {
        int smartAlarmEnable = smartAlarmInfo.getSmartAlarmEnable();
        String b2 = SharedPreferenceManager.b(this.g, String.valueOf(10022), "ONCE_SMART_ALARM_INFO");
        LogUtil.a("AlarmActivity", "once onceSmartAlarmIsOver json is ", b2);
        if (TextUtils.isEmpty(b2)) {
            return smartAlarmEnable;
        }
        List<SmartAlarmInfo> list = (List) new Gson().fromJson(b2, new TypeToken<List<SmartAlarmInfo>>() { // from class: com.huawei.ui.device.activity.alarm.AlarmActivity.15
        }.getType());
        if (list != null && !list.isEmpty()) {
            for (SmartAlarmInfo smartAlarmInfo2 : list) {
                if (smartAlarmInfo2.getSmartAlarmIndex() == smartAlarmInfo.getSmartAlarmIndex() && System.currentTimeMillis() / 1000 >= smartAlarmInfo2.getSmartAlarmTime()) {
                    smartAlarmEnable = 0;
                }
            }
            LogUtil.a("AlarmActivity", "once onceSmartAlarmIsOver iRet is ", Integer.valueOf(smartAlarmEnable));
        }
        return smartAlarmEnable;
    }

    private void cPh_(Intent intent) {
        LogUtil.a("AlarmActivity", "updateSmartAlarmUi()");
        if (intent == null) {
            LogUtil.h("AlarmActivity", "updateSmartAlarmUi() intent is null");
            return;
        }
        int intExtra = intent.getIntExtra("smart_time", 0);
        int intExtra2 = intent.getIntExtra("week_day", 31);
        this.ap.setText(oal.b(this.g, intExtra));
        int a2 = CommonUtil.a(intent.getStringExtra("ahead_time"), 10);
        String e2 = UnitUtil.e(a2, 1, 0);
        this.au.setText(this.g.getResources().getQuantityString(R.plurals._2130903206_res_0x7f0300a6, CommonUtil.h(e2), this.d.b(intExtra2), e2));
        this.ao = intExtra / 100;
        this.aq = intExtra % 100;
        SmartAlarmInfo smartAlarmInfo = this.aj;
        if (smartAlarmInfo != null) {
            smartAlarmInfo.setSmartAlarmAheadTime(a2);
            this.aj.setSmartAlarmEnable(1);
            this.aj.setSmartAlarmRepeat(intExtra2);
            this.aj.setSmartAlarmStartTimeHour(this.ao);
            this.aj.setSmartAlarmStartTimeMins(this.aq);
            LogUtil.a("AlarmActivity", "updateSmartAlarmUi() update mSmartAlarmInfo finish!");
        }
        this.ar.setChecked(true);
        b(true);
        e(intExtra, a2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void q() {
        this.ad = true;
        LogUtil.a("AlarmActivity", "enter updateSmartAlarmUi()  ;", Integer.valueOf(this.al), ";mIsSmartButtonChecked=", Boolean.valueOf(b));
        if (this.al == 1) {
            b = true;
        } else {
            b = false;
        }
        this.ar.setChecked(b);
        b(b);
        this.ar.setOnCheckedChangeListener(this.ag);
        String b2 = oal.b(this.g, (this.ao * 100) + this.aq);
        e = b2;
        this.ap.setText(b2);
        f9049a = UnitUtil.e(this.ae, 1, 0);
        this.au.setText(this.g.getResources().getQuantityString(R.plurals._2130903206_res_0x7f0300a6, CommonUtil.h(f9049a), this.d.b(this.as), f9049a));
        LogUtil.a("AlarmActivity", "mWeekDayTextView is ", this.au.getText(), ";mSmartTimerTextView is ", this.ap.getText(), ";mSmartAlarmSwitch is ", Boolean.valueOf(this.ar.isChecked()));
        if (this.v) {
            a(1);
        }
        e((this.ao * 100) + this.aq, this.ae);
    }

    private void e(int i, int i2) {
        String b2 = oal.b(this.g, i);
        this.af.setText(String.format(Locale.ROOT, this.g.getString(R.string._2130842469_res_0x7f021365), oal.b(this.g, this.d.d(i / 100, i % 100, i2)), b2, this.m.a(this.f)));
    }

    private void t() {
        LogUtil.a("AlarmActivity", "updateEventAlarmUi()");
        this.q.d(new IBaseResponseCallback() { // from class: com.huawei.ui.device.activity.alarm.AlarmActivity.13
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i, Object obj) {
                LogUtil.a("AlarmActivity", "updateEventAlarmUi() getEventAlarm() err_code is ", Integer.valueOf(i));
                if (i == 0 && (obj instanceof List)) {
                    AlarmActivity.this.k = (List) obj;
                }
                LogUtil.a("AlarmActivity", "updateEventAlarmUi() mEventAlarmDbList.size is ", Integer.valueOf(AlarmActivity.this.k.size()));
                Message obtainMessage = AlarmActivity.this.p.obtainMessage();
                obtainMessage.what = 104;
                AlarmActivity.this.p.sendMessage(obtainMessage);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void l() {
        this.t.clear();
        for (EventAlarmInfo eventAlarmInfo : this.k) {
            obx obxVar = new obx();
            obx a2 = obxVar.a(obxVar, eventAlarmInfo, this.d, this.g);
            if (this.t.size() < this.ai) {
                this.t.add(a2);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void m() {
        if (nsn.o()) {
            LogUtil.a("AlarmActivity", "onSmartAlarmClick() isFastClick");
            return;
        }
        if (h()) {
            try {
                Intent intent = new Intent(this, (Class<?>) SmartAlarmClockActivity.class);
                intent.putExtra("key_to_smart_alarm_activity", (this.ao * 100) + this.aq);
                intent.putExtra("device_id", this.f);
                startActivityForResult(intent, 0);
                overridePendingTransition(R.anim._2130771980_res_0x7f01000c, R.anim._2130771980_res_0x7f01000c);
                return;
            } catch (ActivityNotFoundException unused) {
                LogUtil.b("AlarmActivity", "onClick Exception: ActivityNotFoundException");
                sqo.c("onClick Exception: ActivityNotFoundException");
                return;
            }
        }
        nrh.b(this.g, R.string.IDS_device_not_connect);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c() {
        LogUtil.a("AlarmActivity", "addButtonClick()");
        if (this.t == null) {
            LogUtil.b("AlarmActivity", "addButtonClick() mEventAlarmItemList is null");
            return;
        }
        if (!nsn.o()) {
            LogUtil.a("AlarmActivity", "addButtonClick() mEventAlarmItemList.size() is ", Integer.valueOf(this.t.size()));
            int size = this.t.size();
            int i = this.ai;
            if (size >= i) {
                LogUtil.h("AlarmActivity", "addButtonClick() Can't greater than, ", Integer.valueOf(i));
                return;
            }
            try {
                Intent intent = new Intent(this.g, (Class<?>) EventAlarmClockActivity.class);
                intent.putExtra("from_add_button", true);
                intent.putExtra("device_id", this.f);
                startActivityForResult(intent, 0);
                overridePendingTransition(R.anim._2130771980_res_0x7f01000c, R.anim._2130771980_res_0x7f01000c);
                return;
            } catch (ActivityNotFoundException unused) {
                LogUtil.b("AlarmActivity", "addButtonClick Exception: ActivityNotFoundException");
                sqo.c("addButtonClick Exception: ActivityNotFoundException");
                return;
            }
        }
        LogUtil.a("AlarmActivity", "addButtonClick() isFastClick");
    }

    @Override // android.widget.AdapterView.OnItemClickListener
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        LogUtil.a("AlarmActivity", "onItemClick: position is ", Integer.valueOf(i));
        List<obx> list = this.t;
        if (list == null || list.isEmpty()) {
            LogUtil.a("AlarmActivity", "mEventAlarmItemList.size() is 0 or mEventAlarmItemList is null");
            ViewClickInstrumentation.clickOnListView(adapterView, view, i);
            return;
        }
        if (nsn.o()) {
            LogUtil.a("AlarmActivity", "onClick() isFastClick");
            ViewClickInstrumentation.clickOnListView(adapterView, view, i);
            return;
        }
        if (!h()) {
            nrh.b(this.g, R.string.IDS_device_not_connect);
            ViewClickInstrumentation.clickOnListView(adapterView, view, i);
            return;
        }
        LogUtil.a("AlarmActivity", "onItemClick: mEventAlarmItemList.size() is ", Integer.valueOf(this.t.size()));
        if (i < this.t.size()) {
            LogUtil.a("AlarmActivity", "mEventAlarmItemList.get(position)", this.t.get(i));
            obx obxVar = this.t.get(i);
            try {
                Intent intent = new Intent(this.g, (Class<?>) EventAlarmClockActivity.class);
                intent.putExtra("device_id", this.f);
                Bundle bundle = new Bundle();
                bundle.putSerializable("from_list_item", obxVar);
                intent.putExtras(bundle);
                startActivityForResult(intent, 0);
                overridePendingTransition(R.anim._2130771980_res_0x7f01000c, R.anim._2130771980_res_0x7f01000c);
            } catch (ActivityNotFoundException unused) {
                LogUtil.b("AlarmActivity", "onItemClick Exception: ActivityNotFoundException");
                sqo.c("onItemClick Exception: ActivityNotFoundException");
            }
        } else {
            LogUtil.b("AlarmActivity", "position error");
        }
        ViewClickInstrumentation.clickOnListView(adapterView, view, i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(int i) {
        HashMap hashMap = new HashMap(16);
        if (i == 1) {
            hashMap.put("type", 1);
            hashMap.put("state", Integer.valueOf(b ? 1 : 0));
            hashMap.put("ahead_time", f9049a);
        } else if (i == 2) {
            hashMap.put("type", 2);
            hashMap.put("alarm_num", Integer.valueOf(this.t.size()));
        } else {
            LogUtil.a("AlarmActivity", "sendAlarmBiEvent type is error.", Integer.valueOf(i));
        }
        ixx.d().d(this.g, AnalyticsValue.SETTING_SMART_ALARM_1090030.value(), hashMap, 0);
    }

    private void p() {
        if (oae.c(this.g).e(this.f) != 2) {
            LogUtil.a("AlarmActivity", "showNoConnectedToast()");
            nrh.b(this.g, R.string.IDS_device_not_connect);
        }
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        ReleaseLogUtil.e("R_AlarmActivity", "onActivityResult requestCode is ", Integer.valueOf(i), ",resultCode is ", Integer.valueOf(i2));
        if (i2 == 10) {
            this.w = true;
            cPh_(intent);
            return;
        }
        if (i2 != 11) {
            return;
        }
        if (this.z) {
            r();
            String b2 = SharedPreferenceManager.b(this.g, String.valueOf(10022), "DEVICE_EVENT_ALARM_INFO");
            if (!TextUtils.isEmpty(b2)) {
                this.o = (List) new Gson().fromJson(b2, new TypeToken<List<EventAlarmInfo>>() { // from class: com.huawei.ui.device.activity.alarm.AlarmActivity.14
                }.getType());
            }
            List<EventAlarmInfo> list = this.o;
            if (list == null) {
                return;
            }
            ReleaseLogUtil.e("R_AlarmActivity", "eventAlarmDBList_change.size is ", Integer.valueOf(list.size()), ";onActivityResult json is  ", b2);
            this.n.c(this.f, this.o, new IBaseResponseCallback() { // from class: com.huawei.ui.device.activity.alarm.AlarmActivity.11
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public void d(int i3, Object obj) {
                    ReleaseLogUtil.e("R_AlarmActivity", "onActivityResult mIbaseResponseCallbach err_code is ", Integer.valueOf(i3), " ;objData=", obj);
                    if (i3 != 0) {
                        Message obtainMessage = AlarmActivity.this.p.obtainMessage();
                        obtainMessage.what = 105;
                        AlarmActivity.this.p.sendMessage(obtainMessage);
                    } else {
                        AlarmActivity alarmActivity = AlarmActivity.this;
                        alarmActivity.d((List<EventAlarmInfo>) alarmActivity.o);
                        AlarmActivity alarmActivity2 = AlarmActivity.this;
                        alarmActivity2.k = alarmActivity2.o;
                    }
                }
            });
            return;
        }
        t();
    }

    private void r() {
        if (!this.z || h()) {
            return;
        }
        nrh.b(this.g, R.string._2130843760_res_0x7f021870);
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onStop() {
        super.onStop();
        this.p.removeMessages(100);
        this.p.removeMessages(102);
        this.p.removeMessages(104);
        LogUtil.a("AlarmActivity", "once onStop");
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        CommonUtil.a(this.g);
        super.onDestroy();
        this.p.removeMessages(100);
        this.p.removeMessages(102);
        this.p.removeMessages(104);
        this.p.removeCallbacks(this.ax);
        LogUtil.a("AlarmActivity", "once onDestroy");
    }

    @Override // androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onSaveInstanceState(Bundle bundle) {
        LogUtil.a("AlarmActivity", "onSaveInstanceState mMaxAlarmNumber is ", Integer.valueOf(this.ai));
        bundle.putInt("alarmNumber", this.ai);
        bundle.putString("deviceId", this.f);
        super.onSaveInstanceState(bundle);
    }

    @Override // android.app.Activity
    protected void onRestoreInstanceState(Bundle bundle) {
        super.onRestoreInstanceState(bundle);
        this.ai = bundle.getInt("alarmNumber", 5);
        jgh.d(BaseApplication.getContext()).d(this.ai);
        LogUtil.a("AlarmActivity", "onRestoreInstanceState mMaxAlarmNumber is ", Integer.valueOf(this.ai));
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
