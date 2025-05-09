package com.huawei.ui.device.activity.eventalarm;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.InputFilter;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.huawei.datatype.EventAlarmInfo;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.health.R;
import com.huawei.health.devicemgr.business.entity.DeviceCapability;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.adapter.CheckAdapter;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.button.HealthButton;
import com.huawei.ui.commonui.dialog.CustomAlertDialog;
import com.huawei.ui.commonui.dialog.NoTitleCustomAlertDialog;
import com.huawei.ui.commonui.divider.HealthDivider;
import com.huawei.ui.commonui.edittext.HealthEditText;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.switchbutton.HealthSwitchButton;
import com.huawei.ui.commonui.timepicker.HealthTimePicker;
import com.huawei.ui.commonui.titlebar.CustomTitleBar;
import com.huawei.ui.device.activity.eventalarm.EventAlarmClockActivity;
import com.huawei.ui.device.interactors.DeviceSettingsInteractors;
import defpackage.cvs;
import defpackage.cvx;
import defpackage.cwi;
import defpackage.jgh;
import defpackage.jpt;
import defpackage.nrh;
import defpackage.nsn;
import defpackage.nsy;
import defpackage.oae;
import defpackage.oal;
import defpackage.obx;
import health.compact.a.CommonUtil;
import health.compact.a.LanguageUtil;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.regex.Pattern;

/* loaded from: classes6.dex */
public class EventAlarmClockActivity extends BaseActivity implements View.OnClickListener {

    /* renamed from: a, reason: collision with root package name */
    private static String f9085a = null;
    private static String b = "|[[\\u2070-\\u27FF]\\uFE0F]|[\\u2070-\\u27FF]";
    private static String c = "[\\uD800\\uDC00-\\uDBFF\\uDFFF]";
    private static String e = "|[[\\u2900-\\u2BFF]\\uFE0F]|[\\u2900-\\u2BFF]";
    private int ac;
    private RelativeLayout ad;
    private int ae;
    private HealthTextView ag;
    private NoTitleCustomAlertDialog ah;
    private NoTitleCustomAlertDialog ai;
    private CustomAlertDialog aj;
    private HealthSwitchButton ak;
    private HealthTextView an;
    private oal g;
    private HealthButton h;
    private RelativeLayout i;
    private LinearLayout j;
    private DeviceSettingsInteractors l;
    private CustomTitleBar m;
    private DeviceSettingsInteractors p;
    private HealthDivider s;
    private HealthEditText t;
    private jgh u;
    private boolean v;
    private RelativeLayout z;
    private List<EventAlarmInfo> x = new ArrayList(16);
    private List<EventAlarmInfo> aa = new ArrayList(16);
    private obx r = null;
    private Context n = null;
    private int al = 0;
    private String[] am = new String[7];
    private String w = "";
    private String k = "";
    private boolean ab = false;
    private HealthTimePicker o = null;
    private boolean d = false;
    private boolean f = false;
    private Handler y = new Handler() { // from class: com.huawei.ui.device.activity.eventalarm.EventAlarmClockActivity.5
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            if (message.what == 2000 && EventAlarmClockActivity.this.m != null) {
                EventAlarmClockActivity.this.m.setRightButtonClickable(true);
            }
        }
    };
    private CompoundButton.OnCheckedChangeListener af = new CompoundButton.OnCheckedChangeListener() { // from class: com.huawei.ui.device.activity.eventalarm.EventAlarmClockActivity.7
        @Override // android.widget.CompoundButton.OnCheckedChangeListener
        public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
            if (compoundButton.isPressed()) {
                EventAlarmClockActivity.this.d = true;
                if (z) {
                    EventAlarmClockActivity.this.ag.setText("");
                    ReleaseLogUtil.e("R_EventAlarmClockActivity", "user change switch.");
                    EventAlarmClockActivity.this.ac = 1;
                    EventAlarmClockActivity.this.al = 31;
                } else {
                    EventAlarmClockActivity.this.ag.setText(R.string._2130841879_res_0x7f021117);
                    EventAlarmClockActivity.this.ac = 0;
                    EventAlarmClockActivity.this.al = 0;
                }
            }
            ViewClickInstrumentation.clickOnView(compoundButton);
        }
    };
    private InputFilter q = new InputFilter() { // from class: com.huawei.ui.device.activity.eventalarm.EventAlarmClockActivity.15
        Pattern e = Pattern.compile(EventAlarmClockActivity.c + EventAlarmClockActivity.b + EventAlarmClockActivity.e, 64);

        @Override // android.text.InputFilter
        public CharSequence filter(CharSequence charSequence, int i, int i2, Spanned spanned, int i3, int i4) {
            if (!this.e.matcher(charSequence).find() || EventAlarmClockActivity.this.f) {
                return null;
            }
            return "";
        }
    };

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        nsn.cLz_(this);
        this.n = BaseApplication.getContext();
        LogUtil.a("EventAlarmClockActivity", "onCreate()");
        setContentView(R.layout.activity_event_alarm_clock);
        Intent intent = getIntent();
        if (intent != null) {
            this.k = intent.getStringExtra("device_id");
        }
        this.u = jgh.d(this.n);
        this.l = DeviceSettingsInteractors.d((Context) null);
        this.g = oal.d(null);
        this.p = DeviceSettingsInteractors.d(this.n);
        DeviceCapability e2 = cvs.e(this.k);
        if (e2 != null) {
            this.ab = e2.isSupportChangeAlarm();
        }
        m();
    }

    private void m() {
        LogUtil.a("EventAlarmClockActivity", "initView()");
        this.m = (CustomTitleBar) nsy.cMc_(this, R.id.setting_event_alarm_title_bar);
        i();
        HealthButton healthButton = (HealthButton) nsy.cMc_(this, R.id.clock_btn_delete);
        this.h = healthButton;
        healthButton.setText(getString(R.string._2130841503_res_0x7f020f9f).toUpperCase(Locale.ENGLISH));
        this.h.setOnClickListener(this);
        this.j = (LinearLayout) nsy.cMc_(this, R.id.button_wrap);
        this.an = (HealthTextView) nsy.cMc_(this, R.id.smart_alarm_info);
        RelativeLayout relativeLayout = (RelativeLayout) nsy.cMc_(this, R.id.smart_alarm_clock_ll);
        this.i = relativeLayout;
        relativeLayout.setOnClickListener(this);
        this.ag = (HealthTextView) nsy.cMc_(this, R.id.event_alarm_repeat);
        HealthSwitchButton healthSwitchButton = (HealthSwitchButton) nsy.cMc_(this, R.id.skip_holiday_switch_btn);
        this.ak = healthSwitchButton;
        healthSwitchButton.setOnCheckedChangeListener(this.af);
        RelativeLayout relativeLayout2 = (RelativeLayout) nsy.cMc_(this, R.id.smart_alarm_holiday_ll);
        ImageView imageView = (ImageView) nsy.cMc_(this, R.id.imgv_second_setting_clock_line);
        DeviceInfo a2 = jpt.a("EventAlarmClockActivity");
        boolean c2 = cwi.c(a2, 140);
        ReleaseLogUtil.e("R_EventAlarmClockActivity", "SupportLegalHolidays is: ", Boolean.valueOf(c2));
        if (!LanguageUtil.m(this.n) || !c2) {
            imageView.setVisibility(8);
            relativeLayout2.setVisibility(8);
        }
        boolean c3 = cwi.c(a2, 227);
        this.f = c3;
        ReleaseLogUtil.e("R_EventAlarmClockActivity", "isSupportInputEmoji is: ", Boolean.valueOf(c3));
        ((RelativeLayout) nsy.cMc_(this, R.id.smart_alarm_repeat_ll)).setOnClickListener(this);
        if (LanguageUtil.bc(BaseApplication.getContext())) {
            nsy.cMc_(this, R.id.settings_switch).setBackgroundResource(R.drawable._2131427841_res_0x7f0b0201);
            nsy.cMc_(this, R.id.settings_alarm_name).setBackgroundResource(R.drawable._2131427841_res_0x7f0b0201);
        }
        this.z = (RelativeLayout) nsy.cMc_(this, R.id.linear_time_wheel);
        this.ad = (RelativeLayout) nsy.cMc_(this, R.id.linear_time_wheel_bigcd);
        this.s = (HealthDivider) nsy.cMc_(this, R.id.smart_alarm_clock_devide_image);
        if (nsn.ag(this.n)) {
            this.z.setVisibility(8);
            this.ad.setVisibility(0);
            ((RelativeLayout.LayoutParams) this.s.getLayoutParams()).addRule(3, R.id.linear_time_wheel_bigcd);
        } else {
            this.z.setVisibility(0);
            this.ad.setVisibility(8);
        }
    }

    private void i() {
        this.m.setRightButtonOnClickListener(new View.OnClickListener() { // from class: com.huawei.ui.device.activity.eventalarm.EventAlarmClockActivity.11
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ReleaseLogUtil.e("R_EventAlarmClockActivity", "onClick() id = clock_btn_save_sure");
                if (!nsn.o()) {
                    try {
                        EventAlarmClockActivity.this.m.setRightButtonClickable(false);
                        EventAlarmClockActivity.this.k();
                    } catch (UnsupportedEncodingException e2) {
                        LogUtil.b("EventAlarmClockActivity", "UnsupportedEncodingException is ", e2.getMessage());
                    }
                    ViewClickInstrumentation.clickOnView(view);
                    return;
                }
                LogUtil.a("EventAlarmClockActivity", "showPromptSaveDialog() onClick() isFastClick");
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        this.m.setLeftButtonOnClickListener(new View.OnClickListener() { // from class: com.huawei.ui.device.activity.eventalarm.EventAlarmClockActivity.14
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ReleaseLogUtil.e("R_EventAlarmClockActivity", "onClick() id = clock_btn_save_cancel");
                EventAlarmClockActivity.this.h();
                ViewClickInstrumentation.clickOnView(view);
            }
        });
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        Intent intent = super.getIntent();
        if (intent == null) {
            return;
        }
        boolean booleanExtra = intent.getBooleanExtra("from_add_button", false);
        this.v = booleanExtra;
        if (booleanExtra) {
            a((obx) null);
        } else {
            if (intent.getSerializableExtra("from_list_item") instanceof obx) {
                this.r = (obx) intent.getSerializableExtra("from_list_item");
            }
            obx obxVar = this.r;
            if (obxVar != null) {
                this.al = obxVar.h();
                a(this.r);
            } else {
                LogUtil.a("EventAlarmClockActivity", "mEventAlarmItem is null!");
            }
        }
        t();
        j();
        LogUtil.a("EventAlarmClockActivity", "onResume()");
    }

    private void j() {
        ((ViewGroup) findViewById(R.id.event_alarm_clock)).startAnimation(AnimationUtils.loadAnimation(this, R.anim._2130772059_res_0x7f01005b));
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        super.onBackPressed();
        if (this.v) {
            r();
        }
        finish();
    }

    private void t() {
        if (this.j.getLayoutParams() instanceof RelativeLayout.LayoutParams) {
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) this.j.getLayoutParams();
            LogUtil.a("EventAlarmClockActivity", "buttonDeleteLayout changed");
            if ((isInMultiWindowMode() && nsn.f(this) < nsn.j() / 2) || getResources().getConfiguration().orientation == 2) {
                layoutParams.removeRule(12);
                layoutParams.addRule(3, R.id.smart_alarm_clock_ll);
            } else {
                layoutParams.removeRule(3);
                layoutParams.addRule(12, -1);
            }
            this.j.setLayoutParams(layoutParams);
        }
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onMultiWindowModeChanged(boolean z, Configuration configuration) {
        super.onMultiWindowModeChanged(z, configuration);
        t();
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
        t();
    }

    private void a(obx obxVar) {
        LogUtil.a("EventAlarmClockActivity", "initUiData()");
        Calendar calendar = Calendar.getInstance();
        if (obxVar == null) {
            LogUtil.a("EventAlarmClockActivity", "null = clock");
            d(calendar.get(11), calendar.get(12));
            this.an.setText(R.string._2130841509_res_0x7f020fa5);
            f9085a = this.an.getText().toString();
            this.h.setVisibility(8);
            this.m.setTitleText(getResources().getString(R.string._2130841502_res_0x7f020f9e));
            this.ag.setText(this.g.b(this.al));
            this.ak.setChecked(false);
            return;
        }
        int d = obxVar.d();
        d(d / 100, d % 100);
        if (TextUtils.isEmpty(obxVar.a())) {
            this.an.setText(R.string._2130841509_res_0x7f020fa5);
            f9085a = this.an.getText().toString();
        } else {
            this.an.setText(obxVar.a());
            f9085a = obxVar.a();
        }
        obx obxVar2 = this.r;
        if (obxVar2 == null) {
            LogUtil.h("EventAlarmClockActivity", "mEventAlarmItem is null");
            return;
        }
        int c2 = obxVar2.c();
        this.ac = c2;
        LogUtil.a("EventAlarmClockActivity", "mHolidaySwitchStatus,", Integer.valueOf(c2));
        if (this.ac == 1) {
            if (!LanguageUtil.m(this.n)) {
                this.ac = 0;
                this.al = 31;
                this.ag.setText(R.string._2130841735_res_0x7f021087);
            } else {
                this.ak.setChecked(true);
                this.ag.setText("");
            }
        } else {
            this.ag.setText(this.r.b());
            this.ak.setChecked(false);
        }
        this.m.setTitleText(getResources().getString(R.string._2130841504_res_0x7f020fa0));
    }

    private void cPH_(EditText editText) {
        ArrayList arrayList = new ArrayList(Arrays.asList(editText.getFilters()));
        arrayList.add(this.q);
        editText.setFilters((InputFilter[]) arrayList.toArray(new InputFilter[arrayList.size()]));
    }

    private void d(int i, int i2) {
        this.o = (HealthTimePicker) findViewById(R.id.hw_health_timepicker);
        if (nsn.ag(this.n)) {
            this.o = (HealthTimePicker) findViewById(R.id.hw_health_timepicker_bigcd);
        }
        Calendar calendar = Calendar.getInstance();
        calendar.set(11, i);
        calendar.set(12, i2);
        this.o.setTime(i, i2);
        this.ae = (this.o.getHour() * 100) + this.o.getMinute();
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        CommonUtil.a(this.n);
        super.onDestroy();
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        LogUtil.a("EventAlarmClockActivity", "onClick()");
        if (nsn.a(500)) {
            LogUtil.h("EventAlarmClockActivity", "onClick() view click too fast");
            ViewClickInstrumentation.clickOnView(view);
            return;
        }
        int id = view.getId();
        if (id == R.id.clock_btn_delete) {
            ReleaseLogUtil.e("R_EventAlarmClockActivity", "onClick() id = clock_btn_delete");
            s();
        } else if (id == R.id.smart_alarm_repeat_ll) {
            this.d = true;
            ReleaseLogUtil.e("EventAlarmClockActivity", "onClick() id = smart_alarm_repeat_ll");
            w();
        } else if (id != R.id.smart_alarm_clock_ll) {
            LogUtil.a("EventAlarmClockActivity", "onClick() id = ", Integer.valueOf(id));
        } else {
            this.d = true;
            ReleaseLogUtil.e("R_EventAlarmClockActivity", "onClick() id = smart_alarm_clock_ll");
            o();
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    private void w() {
        String c2 = this.g.c(Integer.toBinaryString(this.al), 7);
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
        this.am = new String[]{this.n.getString(R.string._2130841537_res_0x7f020fc1), this.n.getString(R.string._2130841437_res_0x7f020f5d), this.n.getString(R.string._2130841539_res_0x7f020fc3), this.n.getString(R.string._2130841558_res_0x7f020fd6), this.n.getString(R.string._2130841538_res_0x7f020fc2), this.n.getString(R.string._2130841414_res_0x7f020f46), this.n.getString(R.string._2130841468_res_0x7f020f7c)};
        final CheckAdapter checkAdapter = new CheckAdapter(this.n, this.am, zArr);
        ListView listView = new ListView(this.n);
        listView.setDivider(null);
        listView.setAdapter((ListAdapter) checkAdapter);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2);
        layoutParams.setMargins(0, 0, 0, 0);
        listView.setLayoutParams(layoutParams);
        listView.setOnItemClickListener(new CheckAdapter.OnMultiItemClick());
        CustomAlertDialog c3 = new CustomAlertDialog.Builder(this).e(R.string._2130841510_res_0x7f020fa6).cyp_(listView).cyn_(R.string._2130841130_res_0x7f020e2a, new DialogInterface.OnClickListener() { // from class: nvh
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i2) {
                EventAlarmClockActivity.cPJ_(dialogInterface, i2);
            }
        }).cyo_(R.string._2130837556_res_0x7f020034, new DialogInterface.OnClickListener() { // from class: nvg
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i2) {
                EventAlarmClockActivity.this.cPN_(checkAdapter, dialogInterface, i2);
            }
        }).c();
        this.aj = c3;
        c3.show();
    }

    public static /* synthetic */ void cPJ_(DialogInterface dialogInterface, int i) {
        ReleaseLogUtil.e("R_EventAlarmClockActivity", "showRepeatDialog cancel");
        ViewClickInstrumentation.clickOnDialog(dialogInterface, i);
    }

    public /* synthetic */ void cPN_(CheckAdapter checkAdapter, DialogInterface dialogInterface, int i) {
        ReleaseLogUtil.e("R_EventAlarmClockActivity", "showRepeatDialog ok");
        boolean[] e2 = checkAdapter.e();
        if (e2 == null) {
            LogUtil.a("EventAlarmClockActivity", "checkedItems is null");
            ViewClickInstrumentation.clickOnDialog(dialogInterface, i);
            return;
        }
        this.al = this.g.c(e2);
        HealthTextView healthTextView = this.ag;
        oal oalVar = this.g;
        healthTextView.setText(oalVar.b(oalVar.c(e2)));
        this.ac = 0;
        this.ak.setChecked(false);
        ViewClickInstrumentation.clickOnDialog(dialogInterface, i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void r() {
        setResult(11, new Intent());
        ReleaseLogUtil.e("R_EventAlarmClockActivity", "setResultData()");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void f() {
        this.u.d(new IBaseResponseCallback() { // from class: com.huawei.ui.device.activity.eventalarm.EventAlarmClockActivity.13
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i, Object obj) {
                ReleaseLogUtil.e("R_EventAlarmClockActivity", "deleteClock()", Integer.valueOf(EventAlarmClockActivity.this.r.j()));
                if (i == 0 && (obj instanceof List)) {
                    EventAlarmClockActivity.this.x = (List) obj;
                }
                if (!EventAlarmClockActivity.this.x.isEmpty() && EventAlarmClockActivity.this.x.size() > EventAlarmClockActivity.this.r.j() - 1) {
                    EventAlarmClockActivity.this.x.remove(EventAlarmClockActivity.this.r.j() - 1);
                    int i2 = 0;
                    while (i2 < EventAlarmClockActivity.this.x.size()) {
                        EventAlarmInfo eventAlarmInfo = (EventAlarmInfo) EventAlarmClockActivity.this.x.get(i2);
                        i2++;
                        eventAlarmInfo.setEventAlarmIndex(i2);
                        ReleaseLogUtil.e("R_EventAlarmClockActivity", "deleteClock mEventAlarmList = ", eventAlarmInfo.toString());
                    }
                } else {
                    ReleaseLogUtil.e("R_EventAlarmClockActivity", "deleteClock() error");
                }
                EventAlarmClockActivity.this.q();
                EventAlarmClockActivity.this.l.b(EventAlarmClockActivity.this.x, new IBaseResponseCallback() { // from class: com.huawei.ui.device.activity.eventalarm.EventAlarmClockActivity.13.3
                    @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                    /* renamed from: onResponse */
                    public void d(int i3, Object obj2) {
                        if (i3 == 0) {
                            ReleaseLogUtil.e("R_EventAlarmClockActivity", "deleteClock() success");
                            EventAlarmClockActivity.this.r();
                            EventAlarmClockActivity.this.finish();
                            return;
                        }
                        ReleaseLogUtil.e("R_EventAlarmClockActivity", "deleteClock() failed, errorCode is ", Integer.valueOf(i3));
                    }
                });
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void n() {
        String b2 = SharedPreferenceManager.b(this.n, String.valueOf(10022), "DEVICE_EVENT_ALARM_INFO");
        if (!TextUtils.isEmpty(b2)) {
            this.x = (List) new Gson().fromJson(b2, new TypeToken<List<EventAlarmInfo>>() { // from class: com.huawei.ui.device.activity.eventalarm.EventAlarmClockActivity.12
            }.getType());
        }
        ReleaseLogUtil.e("R_EventAlarmClockActivity", "deleteDeviceClock() mEventAlarmList is ", Integer.valueOf(this.x.size()), ",mEventAlarmList = ", this.x.toString());
        if (!this.x.isEmpty() && this.x.size() > this.r.j() - 1) {
            this.x.remove(this.r.j() - 1);
            int i = 0;
            while (i < this.x.size()) {
                EventAlarmInfo eventAlarmInfo = this.x.get(i);
                i++;
                eventAlarmInfo.setEventAlarmIndex(i);
                ReleaseLogUtil.e("R_EventAlarmClockActivity", "deleteDeviceClock mEventAlarmList is ", eventAlarmInfo.toString());
            }
        } else {
            ReleaseLogUtil.e("R_EventAlarmClockActivity", "deleteDeviceClock() error");
        }
        this.p.b(this.x);
        r();
        finish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void q() {
        if (oae.c(this.n).e(this.k) != 2) {
            runOnUiThread(new Runnable() { // from class: com.huawei.ui.device.activity.eventalarm.EventAlarmClockActivity.17
                @Override // java.lang.Runnable
                public void run() {
                    ReleaseLogUtil.e("R_EventAlarmClockActivity", "showNoConnectedToast()");
                    nrh.b(EventAlarmClockActivity.this.n, R.string.IDS_device_not_connect);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void k() throws UnsupportedEncodingException {
        ReleaseLogUtil.e("R_EventAlarmClockActivity", "saveClock()");
        if (TextUtils.isEmpty(this.an.getText().toString().trim())) {
            ReleaseLogUtil.e("R_EventAlarmClockActivity", "info is null");
            this.an.setText(R.string._2130841509_res_0x7f020fa5);
            f9085a = this.an.getText().toString();
        }
        if (this.v) {
            if (this.ab) {
                g();
                return;
            } else {
                d();
                return;
            }
        }
        if (this.ab) {
            y();
        } else {
            x();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void h() {
        LogUtil.a("EventAlarmClockActivity", "clickCancelBtn()");
        p();
    }

    private void d() {
        LogUtil.a("EventAlarmClockActivity", "addAlarm()");
        this.aa.clear();
        this.u.d(new IBaseResponseCallback() { // from class: com.huawei.ui.device.activity.eventalarm.EventAlarmClockActivity.20
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i, Object obj) {
                LogUtil.a("EventAlarmClockActivity", "addAlarm() getEventAlarm() errorCode is ", Integer.valueOf(i));
                if (i == 0 && (obj instanceof List)) {
                    EventAlarmClockActivity.this.x = (List) obj;
                }
                for (int i2 = 0; i2 < EventAlarmClockActivity.this.x.size(); i2++) {
                    EventAlarmInfo eventAlarmInfo = (EventAlarmInfo) EventAlarmClockActivity.this.x.get(i2);
                    eventAlarmInfo.setEventAlarmIndex(i2 + 2);
                    EventAlarmClockActivity.this.aa.add(eventAlarmInfo);
                }
                int hour = (EventAlarmClockActivity.this.o.getHour() * 100) + EventAlarmClockActivity.this.o.getMinute();
                EventAlarmInfo eventAlarmInfo2 = new EventAlarmInfo();
                eventAlarmInfo2.setEventAlarmStartTimeHour(hour / 100);
                eventAlarmInfo2.setEventAlarmStartTimeMins(hour % 100);
                eventAlarmInfo2.setEventAlarmRepeat(EventAlarmClockActivity.this.al);
                eventAlarmInfo2.setEventAlarmName(EventAlarmClockActivity.this.an.getText().toString());
                eventAlarmInfo2.setEventAlarmLegalHolidayEnable(EventAlarmClockActivity.this.ac);
                eventAlarmInfo2.setCreateTimestamp(System.currentTimeMillis());
                eventAlarmInfo2.setModifiedTimestamp(System.currentTimeMillis());
                eventAlarmInfo2.setEventAlarmEnable(1);
                eventAlarmInfo2.setEventAlarmIndex(1);
                EventAlarmClockActivity.this.aa.add(0, eventAlarmInfo2);
                EventAlarmClockActivity.this.q();
                EventAlarmClockActivity.this.l.b(EventAlarmClockActivity.this.aa, new IBaseResponseCallback() { // from class: com.huawei.ui.device.activity.eventalarm.EventAlarmClockActivity.20.5
                    @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                    /* renamed from: onResponse */
                    public void d(int i3, Object obj2) {
                        LogUtil.a("EventAlarmClockActivity", "addAlarm() errorCode is ", Integer.valueOf(i3));
                        EventAlarmClockActivity.this.r();
                        EventAlarmClockActivity.this.y.sendEmptyMessage(2000);
                        EventAlarmClockActivity.this.finish();
                    }
                });
                LogUtil.a("EventAlarmClockActivity", "addAlarm() mNewEventAlarmList", EventAlarmClockActivity.this.aa);
            }
        });
    }

    private void g() {
        LogUtil.a("EventAlarmClockActivity", "addDeviceAlarm()");
        this.aa.clear();
        String b2 = SharedPreferenceManager.b(this.n, String.valueOf(10022), "DEVICE_EVENT_ALARM_INFO");
        if (!TextUtils.isEmpty(b2)) {
            this.x = (List) new Gson().fromJson(b2, new TypeToken<List<EventAlarmInfo>>() { // from class: com.huawei.ui.device.activity.eventalarm.EventAlarmClockActivity.3
            }.getType());
        }
        LogUtil.a("EventAlarmClockActivity", "addDeviceAlarm() + mEventAlarmList.size()", Integer.valueOf(this.x.size()));
        for (int i = 0; i < this.x.size(); i++) {
            EventAlarmInfo eventAlarmInfo = this.x.get(i);
            eventAlarmInfo.setEventAlarmIndex(i + 2);
            if (TextUtils.isEmpty(eventAlarmInfo.getEventAlarmName())) {
                eventAlarmInfo.setEventAlarmName(this.an.getText().toString());
            }
            this.aa.add(eventAlarmInfo);
        }
        int hour = (this.o.getHour() * 100) + this.o.getMinute();
        EventAlarmInfo eventAlarmInfo2 = new EventAlarmInfo();
        eventAlarmInfo2.setEventAlarmStartTimeHour(hour / 100);
        eventAlarmInfo2.setEventAlarmStartTimeMins(hour % 100);
        eventAlarmInfo2.setEventAlarmRepeat(this.al);
        eventAlarmInfo2.setEventAlarmName(this.an.getText().toString());
        eventAlarmInfo2.setEventAlarmLegalHolidayEnable(this.ac);
        eventAlarmInfo2.setCreateTimestamp(System.currentTimeMillis());
        eventAlarmInfo2.setModifiedTimestamp(System.currentTimeMillis());
        LogUtil.a("EventAlarmClockActivity", "addDeviceAlarm() mTextViewInfo.getText().toString() is ", this.an.getText().toString());
        eventAlarmInfo2.setEventAlarmEnable(1);
        eventAlarmInfo2.setEventAlarmIndex(1);
        if (this.aa.size() <= jgh.d(BaseApplication.getContext()).i()) {
            this.aa.add(0, eventAlarmInfo2);
        }
        q();
        this.p.b(this.aa);
        r();
        this.y.sendEmptyMessage(2000);
        finish();
        ReleaseLogUtil.e("R_EventAlarmClockActivity", "addDeviceAlarm() mNewEventAlarmList", this.aa);
    }

    private void x() {
        ReleaseLogUtil.e("R_EventAlarmClockActivity", "updateEventAlarm()");
        this.aa.clear();
        int hour = this.o.getHour();
        final int minute = (hour * 100) + this.o.getMinute();
        this.u.d(new IBaseResponseCallback() { // from class: com.huawei.ui.device.activity.eventalarm.EventAlarmClockActivity.4
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i, Object obj) {
                if (i == 0 && (obj instanceof List)) {
                    EventAlarmClockActivity.this.x = (List) obj;
                }
                EventAlarmClockActivity.this.d(minute);
                ReleaseLogUtil.e("R_EventAlarmClockActivity", "updateEventAlarm() mNewEventAlarmList", EventAlarmClockActivity.this.aa);
                EventAlarmClockActivity.this.q();
                EventAlarmClockActivity.this.l.b(EventAlarmClockActivity.this.aa, new IBaseResponseCallback() { // from class: com.huawei.ui.device.activity.eventalarm.EventAlarmClockActivity.4.2
                    @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                    /* renamed from: onResponse */
                    public void d(int i2, Object obj2) {
                        EventAlarmClockActivity.this.r();
                        EventAlarmClockActivity.this.y.sendEmptyMessage(2000);
                        EventAlarmClockActivity.this.finish();
                    }
                });
            }
        });
    }

    private void y() {
        ReleaseLogUtil.e("R_EventAlarmClockActivity", "updateEventAlarm()");
        this.aa.clear();
        int hour = this.o.getHour();
        int minute = this.o.getMinute();
        String b2 = SharedPreferenceManager.b(this.n, String.valueOf(10022), "DEVICE_EVENT_ALARM_INFO");
        if (!TextUtils.isEmpty(b2)) {
            this.x = (List) new Gson().fromJson(b2, new TypeToken<List<EventAlarmInfo>>() { // from class: com.huawei.ui.device.activity.eventalarm.EventAlarmClockActivity.1
            }.getType());
        }
        d((hour * 100) + minute);
        ReleaseLogUtil.e("R_EventAlarmClockActivity", "updateEventAlarm() mNewEventAlarmList", this.aa);
        this.p.b(this.aa);
        this.y.sendEmptyMessage(2000);
        r();
        finish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(int i) {
        long j;
        if (!this.x.isEmpty() && this.x.size() > this.r.j() - 1) {
            j = this.x.get(this.r.j() - 1).getCreateTimestamp();
            this.x.remove(this.r.j() - 1);
        } else {
            LogUtil.a("EventAlarmClockActivity", "updateEventAlarm() error");
            j = 0;
        }
        for (int i2 = 0; i2 < this.x.size(); i2++) {
            EventAlarmInfo eventAlarmInfo = this.x.get(i2);
            eventAlarmInfo.setEventAlarmIndex(i2 + 2);
            this.aa.add(eventAlarmInfo);
        }
        EventAlarmInfo eventAlarmInfo2 = new EventAlarmInfo();
        eventAlarmInfo2.setEventAlarmStartTimeHour(i / 100);
        eventAlarmInfo2.setEventAlarmStartTimeMins(i % 100);
        eventAlarmInfo2.setEventAlarmRepeat(this.al);
        eventAlarmInfo2.setEventAlarmName(this.an.getText().toString());
        eventAlarmInfo2.setEventAlarmLegalHolidayEnable(this.ac);
        eventAlarmInfo2.setCreateTimestamp(j);
        eventAlarmInfo2.setModifiedTimestamp(System.currentTimeMillis());
        eventAlarmInfo2.setEventAlarmEnable(1);
        eventAlarmInfo2.setEventAlarmIndex(1);
        this.aa.add(0, eventAlarmInfo2);
    }

    private boolean l() {
        return this.ae != (this.o.getHour() * 100) + this.o.getMinute();
    }

    private void p() {
        if (!this.d && !l()) {
            LogUtil.a("EventAlarmClockActivity", "AlarmHasNoChanges");
            finish();
            return;
        }
        LogUtil.a("EventAlarmClockActivity", "showPromptSaveDialog()");
        NoTitleCustomAlertDialog.Builder builder = new NoTitleCustomAlertDialog.Builder(this);
        builder.e(R.string._2130842170_res_0x7f02123a).czE_(getResources().getString(R.string._2130841751_res_0x7f021097).toUpperCase(Locale.ENGLISH), new View.OnClickListener() { // from class: com.huawei.ui.device.activity.eventalarm.EventAlarmClockActivity.10
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ReleaseLogUtil.e("R_EventAlarmClockActivity", "showPromptSaveDialog() Yes ...");
                if (!nsn.o()) {
                    try {
                        EventAlarmClockActivity.this.k();
                    } catch (UnsupportedEncodingException e2) {
                        LogUtil.b("EventAlarmClockActivity", "UnsupportedEncodingException is ", e2.getMessage());
                    }
                    EventAlarmClockActivity.this.ai.cancel();
                    ViewClickInstrumentation.clickOnView(view);
                    return;
                }
                LogUtil.a("EventAlarmClockActivity", "showPromptSaveDialog() onClick() isFastClick");
                ViewClickInstrumentation.clickOnView(view);
            }
        }).czA_(getResources().getString(R.string._2130841389_res_0x7f020f2d).toUpperCase(Locale.ENGLISH), new View.OnClickListener() { // from class: com.huawei.ui.device.activity.eventalarm.EventAlarmClockActivity.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ReleaseLogUtil.c("R_EventAlarmClockActivity", "showPromptSaveDialog() No ...");
                EventAlarmClockActivity.this.r();
                EventAlarmClockActivity.this.finish();
                EventAlarmClockActivity.this.ai.cancel();
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        builder.e(true);
        NoTitleCustomAlertDialog e2 = builder.e();
        this.ai = e2;
        e2.setCancelable(false);
        this.ai.show();
    }

    private void s() {
        ReleaseLogUtil.e("R_EventAlarmClockActivity", "showPromptSaveDialog()");
        NoTitleCustomAlertDialog.Builder builder = new NoTitleCustomAlertDialog.Builder(this);
        builder.e(R.string._2130842169_res_0x7f021239).czE_(getResources().getString(R.string._2130841438_res_0x7f020f5e).toUpperCase(Locale.ENGLISH), new View.OnClickListener() { // from class: com.huawei.ui.device.activity.eventalarm.EventAlarmClockActivity.9
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ReleaseLogUtil.c("R_EventAlarmClockActivity", "showPromptDeleteDialog() yes ...");
                if (EventAlarmClockActivity.this.ab) {
                    EventAlarmClockActivity.this.n();
                } else {
                    EventAlarmClockActivity.this.f();
                }
                EventAlarmClockActivity.this.ah.cancel();
                ViewClickInstrumentation.clickOnView(view);
            }
        }).czA_(getResources().getString(R.string._2130841130_res_0x7f020e2a).toUpperCase(Locale.ENGLISH), new View.OnClickListener() { // from class: com.huawei.ui.device.activity.eventalarm.EventAlarmClockActivity.6
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ReleaseLogUtil.e("R_EventAlarmClockActivity", "showPromptDeleteDialog() no ...");
                EventAlarmClockActivity.this.ah.cancel();
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        builder.e(true);
        NoTitleCustomAlertDialog e2 = builder.e();
        this.ah = e2;
        e2.setCancelable(false);
        this.ah.show();
    }

    private void c(final HealthButton healthButton) {
        this.t.addTextChangedListener(new TextWatcher() { // from class: com.huawei.ui.device.activity.eventalarm.EventAlarmClockActivity.8
            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                EventAlarmClockActivity eventAlarmClockActivity = EventAlarmClockActivity.this;
                String d = eventAlarmClockActivity.d(eventAlarmClockActivity.t.getText().toString());
                if (cvx.c(d).length() / 2 > 24) {
                    EventAlarmClockActivity eventAlarmClockActivity2 = EventAlarmClockActivity.this;
                    eventAlarmClockActivity2.w = eventAlarmClockActivity2.d(d, d.length() - 1);
                    EventAlarmClockActivity.this.t.setText(EventAlarmClockActivity.this.w);
                    EventAlarmClockActivity.this.t.setSelection(EventAlarmClockActivity.this.w.length());
                }
            }

            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable editable) {
                if (" ".equals(editable.toString().trim())) {
                    healthButton.setEnabled(true);
                }
                healthButton.setEnabled(!TextUtils.isEmpty(editable));
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String d(String str, int i) {
        int i2 = i - 1;
        if (Character.isHighSurrogate(str.charAt(i2))) {
            ReleaseLogUtil.e("R_EventAlarmClockActivity", "isHighSurrogate : " + str.charAt(i2));
            i = i2;
        }
        return str.substring(0, i);
    }

    private void o() {
        View inflate = LayoutInflater.from(this).inflate(R.layout.commonui_dialog_single_edit, (ViewGroup) null);
        HealthEditText healthEditText = (HealthEditText) inflate.findViewById(R.id.edit);
        this.t = healthEditText;
        cPH_(healthEditText);
        if (f9085a == null) {
            f9085a = getString(R.string._2130841509_res_0x7f020fa5);
        }
        CustomAlertDialog.Builder builder = new CustomAlertDialog.Builder(this);
        c(builder.b());
        this.t.setText(f9085a);
        HealthEditText healthEditText2 = this.t;
        healthEditText2.setSelection(healthEditText2.getText().length());
        builder.e(R.string._2130846327_res_0x7f022277).cyp_(inflate).cyn_(R.string._2130841130_res_0x7f020e2a, new DialogInterface.OnClickListener() { // from class: nve
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                ViewClickInstrumentation.clickOnDialog(dialogInterface, i);
            }
        }).cyo_(R.string._2130841131_res_0x7f020e2b, new DialogInterface.OnClickListener() { // from class: nvc
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                EventAlarmClockActivity.this.cPK_(dialogInterface, i);
            }
        });
        CustomAlertDialog c2 = builder.c();
        c2.setOnDismissListener(new DialogInterface.OnDismissListener() { // from class: nvd
            @Override // android.content.DialogInterface.OnDismissListener
            public final void onDismiss(DialogInterface dialogInterface) {
                EventAlarmClockActivity.this.cPL_(dialogInterface);
            }
        });
        c2.setOnShowListener(new DialogInterface.OnShowListener() { // from class: nvf
            @Override // android.content.DialogInterface.OnShowListener
            public final void onShow(DialogInterface dialogInterface) {
                EventAlarmClockActivity.this.cPM_(dialogInterface);
            }
        });
        c2.show();
    }

    public /* synthetic */ void cPK_(DialogInterface dialogInterface, int i) {
        String d = d(this.t.getText().toString());
        f9085a = d;
        this.an.setText(d);
        ViewClickInstrumentation.clickOnDialog(dialogInterface, i);
    }

    public /* synthetic */ void cPL_(DialogInterface dialogInterface) {
        ((InputMethodManager) this.n.getSystemService(InputMethodManager.class)).hideSoftInputFromWindow(this.t.getWindowToken(), 2);
        this.t.clearFocus();
    }

    public /* synthetic */ void cPM_(DialogInterface dialogInterface) {
        this.t.postDelayed(new Runnable() { // from class: nvi
            @Override // java.lang.Runnable
            public final void run() {
                EventAlarmClockActivity.this.e();
            }
        }, 100L);
    }

    public /* synthetic */ void e() {
        this.t.requestFocus();
        ((InputMethodManager) this.n.getSystemService(InputMethodManager.class)).showSoftInput(this.t, 0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String d(String str) {
        return str.replaceAll("(\r\n|\r|\n|\n\r)", " ");
    }

    @Override // android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i == 4) {
            h();
            return false;
        }
        return super.onKeyDown(i, keyEvent);
    }
}
