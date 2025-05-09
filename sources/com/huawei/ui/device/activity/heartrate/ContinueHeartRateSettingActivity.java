package com.huawei.ui.device.activity.heartrate;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.health.R;
import com.huawei.health.devicemgr.business.entity.DeviceCapability;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hihealthservice.old.model.OldToNewMotionPath;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.dialog.CustomAlertDialog;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.radiobutton.HealthRadioButton;
import com.huawei.ui.commonui.subheader.HealthSubHeader;
import com.huawei.ui.commonui.switchbutton.HealthSwitchButton;
import com.huawei.ui.device.interactors.DeviceSettingsInteractors;
import com.huawei.ui.main.R$string;
import defpackage.cvs;
import defpackage.jfu;
import defpackage.jlk;
import defpackage.jqi;
import defpackage.nsn;
import defpackage.nsy;
import defpackage.nvj;
import defpackage.nyn;
import defpackage.obd;
import health.compact.a.CommonUtil;
import health.compact.a.LanguageUtil;
import health.compact.a.UnitUtil;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Semaphore;

/* loaded from: classes.dex */
public class ContinueHeartRateSettingActivity extends BaseActivity {

    /* renamed from: a, reason: collision with root package name */
    private ImageView f9095a;
    private LinearLayout aa;
    private ImageView ab;
    private List<String> ad;
    private RelativeLayout ae;
    private LinearLayout ag;
    private RelativeLayout ah;
    private HealthTextView ai;
    private RelativeLayout ak;
    private RelativeLayout am;
    private HealthTextView ao;
    private HealthTextView ap;
    private HealthTextView aq;
    private HealthTextView at;
    private HealthTextView au;
    private HealthTextView aw;
    private HealthTextView ax;
    private HealthTextView ay;
    private HealthTextView az;
    private HealthTextView b;
    private HealthTextView bb;
    private int c;
    private int d;
    private HealthTextView e;
    private ImageView g;
    private HealthSwitchButton h;
    private HealthTextView i;
    private HealthTextView j;
    private DeviceSettingsInteractors k;
    private ImageView m;
    private HealthTextView n;
    private int p;
    private HealthSubHeader q;
    private LinearLayout r;
    private HealthTextView s;
    private obd t;
    private ImageView u;
    private jqi v;
    private HealthTextView w;
    private int y;
    private Map<Integer, RelativeLayout> an = new HashMap(7);
    private Map<Integer, HealthRadioButton> x = new HashMap(7);
    private Map<Integer, RelativeLayout> aj = new HashMap(7);
    private Map<Integer, HealthRadioButton> z = new HashMap(7);
    private Map<Integer, RelativeLayout> al = new HashMap(7);
    private Map<Integer, ImageView> ac = new HashMap(7);
    private Semaphore av = new Semaphore(0);
    private View.OnTouchListener f = new View.OnTouchListener() { // from class: com.huawei.ui.device.activity.heartrate.ContinueHeartRateSettingActivity.1
        @Override // android.view.View.OnTouchListener
        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (view.getId() != ContinueHeartRateSettingActivity.this.h.getId() || motionEvent.getAction() != 0) {
                return false;
            }
            boolean z = !ContinueHeartRateSettingActivity.this.h.isChecked();
            ReleaseLogUtil.e("DEVMGR_ContinueHeartRateSettingActivity", "mCustomSwitchButton isChecked:", Boolean.valueOf(z));
            if (z) {
                ContinueHeartRateSettingActivity.this.a(true);
                return true;
            }
            jlk.a().a(ContinueHeartRateSettingActivity.this, new IBaseResponseCallback() { // from class: com.huawei.ui.device.activity.heartrate.ContinueHeartRateSettingActivity.1.3
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public void d(int i, Object obj) {
                    if (i == 0) {
                        ContinueHeartRateSettingActivity.this.a(false);
                    }
                }
            });
            return true;
        }
    };
    private View.OnClickListener as = new View.OnClickListener() { // from class: com.huawei.ui.device.activity.heartrate.ContinueHeartRateSettingActivity.5
        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (!nsn.o()) {
                ContinueHeartRateSettingActivity.this.b();
                ViewClickInstrumentation.clickOnView(view);
            } else {
                LogUtil.h("ContinueHeartRateSettingActivity", "click the raise button is fast click");
                ViewClickInstrumentation.clickOnView(view);
            }
        }
    };
    private View.OnClickListener ar = new View.OnClickListener() { // from class: com.huawei.ui.device.activity.heartrate.ContinueHeartRateSettingActivity.9
        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (!nsn.o()) {
                ContinueHeartRateSettingActivity.this.g();
                ViewClickInstrumentation.clickOnView(view);
            } else {
                LogUtil.h("ContinueHeartRateSettingActivity", "click the down button is fast click");
                ViewClickInstrumentation.clickOnView(view);
            }
        }
    };
    private View.OnClickListener l = new View.OnClickListener() { // from class: com.huawei.ui.device.activity.heartrate.ContinueHeartRateSettingActivity.7
        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            for (Map.Entry entry : ContinueHeartRateSettingActivity.this.an.entrySet()) {
                if (entry.getValue() == view) {
                    ContinueHeartRateSettingActivity.this.d = ((Integer) entry.getKey()).intValue();
                    ContinueHeartRateSettingActivity continueHeartRateSettingActivity = ContinueHeartRateSettingActivity.this;
                    continueHeartRateSettingActivity.e(continueHeartRateSettingActivity.d);
                    ViewClickInstrumentation.clickOnView(view);
                    return;
                }
            }
            ViewClickInstrumentation.clickOnView(view);
        }
    };
    private View.OnClickListener o = new View.OnClickListener() { // from class: com.huawei.ui.device.activity.heartrate.ContinueHeartRateSettingActivity.10
        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            for (Map.Entry entry : ContinueHeartRateSettingActivity.this.aj.entrySet()) {
                if (entry.getValue() == view) {
                    ContinueHeartRateSettingActivity.this.c = ((Integer) entry.getKey()).intValue();
                    ContinueHeartRateSettingActivity continueHeartRateSettingActivity = ContinueHeartRateSettingActivity.this;
                    continueHeartRateSettingActivity.b(continueHeartRateSettingActivity.c);
                    ViewClickInstrumentation.clickOnView(view);
                    return;
                }
            }
            ViewClickInstrumentation.clickOnView(view);
        }
    };
    private View.OnClickListener af = new View.OnClickListener() { // from class: com.huawei.ui.device.activity.heartrate.ContinueHeartRateSettingActivity.6
        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            LogUtil.a("ContinueHeartRateSettingActivity", "click the radio button");
            Iterator it = ContinueHeartRateSettingActivity.this.al.entrySet().iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                Map.Entry entry = (Map.Entry) it.next();
                if (entry.getValue() == view) {
                    LogUtil.a("ContinueHeartRateSettingActivity", "get the button");
                    int intValue = ((Integer) entry.getKey()).intValue();
                    ContinueHeartRateSettingActivity.this.d(intValue);
                    ReleaseLogUtil.e("DEVMGR_ContinueHeartRateSettingActivity", "send heart rate key: ", Integer.valueOf(intValue));
                    if (intValue != 0) {
                        if (intValue == 1) {
                            if (ContinueHeartRateSettingActivity.this.k != null) {
                                ContinueHeartRateSettingActivity.this.k.c(1);
                                ContinueHeartRateSettingActivity.this.t.a("biContinueHeartRateModeSwitch", 1);
                            } else {
                                LogUtil.h("ContinueHeartRateSettingActivity", "send cycle heart rate mDeviceInteractors is null");
                            }
                        } else {
                            LogUtil.c("ContinueHeartRateSettingActivity", "click not get heart rate choice");
                        }
                    } else if (ContinueHeartRateSettingActivity.this.k != null) {
                        ContinueHeartRateSettingActivity.this.k.a(1);
                        ContinueHeartRateSettingActivity.this.t.a("biContinueHeartRateModeSwitch", 0);
                    } else {
                        LogUtil.h("ContinueHeartRateSettingActivity", "send continue heart rate mDeviceInteractors is null");
                    }
                    LogUtil.a("ContinueHeartRateSettingActivity", "write sharedpreference");
                    ContinueHeartRateSettingActivity.this.a("heartRateChoiceKey", intValue);
                    LogUtil.a("ContinueHeartRateSettingActivity", "finish writting sharedpreference");
                }
            }
            ViewClickInstrumentation.clickOnView(view);
        }
    };

    /* JADX INFO: Access modifiers changed from: private */
    public void a(boolean z) {
        DeviceSettingsInteractors deviceSettingsInteractors;
        DeviceSettingsInteractors deviceSettingsInteractors2;
        this.h.setChecked(z);
        ReleaseLogUtil.e("DEVMGR_ContinueHeartRateSettingActivity", "continue heart rate onCheckedChanged: ", Boolean.valueOf(z));
        int b = b("heartRateChoiceKey");
        ReleaseLogUtil.e("DEVMGR_ContinueHeartRateSettingActivity", "support key is ", Integer.valueOf(b));
        if (b == 0 && (deviceSettingsInteractors2 = this.k) != null) {
            deviceSettingsInteractors2.a(z ? 1 : 0);
        }
        if (b == 1 && (deviceSettingsInteractors = this.k) != null) {
            deviceSettingsInteractors.c(z ? 1 : 0);
        }
        f(z ? 1 : 0);
        e(z, b);
        c(z);
        this.t.a("biContinueHeartRateSwitch", z ? 1 : 0);
    }

    private void c(final boolean z) {
        if (z) {
            ArrayList arrayList = new ArrayList(16);
            arrayList.add("custom.heart_rate_raise_remind");
            arrayList.add("custom.heart_rate_down_remind");
            this.v.getSwitchSetting(arrayList, new IBaseResponseCallback() { // from class: com.huawei.ui.device.activity.heartrate.ContinueHeartRateSettingActivity.8
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public void d(int i, Object obj) {
                    LogUtil.a("ContinueHeartRateSettingActivity", "refreshHeartRateNumber errorCode is:", Integer.valueOf(i), ", objectData:", obj);
                    if (obj == null) {
                        ContinueHeartRateSettingActivity.this.y = 120;
                        ContinueHeartRateSettingActivity.this.p = 40;
                    } else {
                        List list = (List) obj;
                        String a2 = nvj.a(list, "custom.heart_rate_raise_remind");
                        LogUtil.a("ContinueHeartRateSettingActivity", "refreshHeartRateNumber raiseNumber is:", a2);
                        if (a2 == null) {
                            ContinueHeartRateSettingActivity.this.y = 120;
                        }
                        String a3 = nvj.a(list, "custom.heart_rate_down_remind");
                        LogUtil.a("ContinueHeartRateSettingActivity", "refreshHeartRateNumber downNumber is:", a2);
                        if (a3 == null) {
                            ContinueHeartRateSettingActivity.this.p = 40;
                        }
                    }
                    ContinueHeartRateSettingActivity.this.runOnUiThread(new Runnable() { // from class: com.huawei.ui.device.activity.heartrate.ContinueHeartRateSettingActivity.8.2
                        @Override // java.lang.Runnable
                        public void run() {
                            ContinueHeartRateSettingActivity.this.d(z);
                        }
                    });
                }
            });
            return;
        }
        d(z);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(boolean z) {
        LogUtil.a("ContinueHeartRateSettingActivity", "refreshHeartRateNumber mHeartRateRaiseNumber: ", Integer.valueOf(this.y), " mHeartRateDownNumber: ", Integer.valueOf(this.p));
        a(true, z, false);
        b(true, z, false);
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_device_continue_measure_heart_rate);
        this.v = jqi.a();
        this.k = DeviceSettingsInteractors.d(BaseApplication.getContext());
        this.t = new obd();
        c();
    }

    private void c() {
        this.h = (HealthSwitchButton) nsy.cMc_(this, R.id.heart_rate_switch_button);
        this.am = (RelativeLayout) nsy.cMc_(this, R.id.settings_heart_rate_raise_remind_explain_layout);
        this.ak = (RelativeLayout) nsy.cMc_(this, R.id.settings_heart_rate_down_remind_explain_layout);
        this.at = (HealthTextView) nsy.cMc_(this, R.id.raise_remind_number);
        this.ap = (HealthTextView) nsy.cMc_(this, R.id.down_remind_number);
        this.u = (ImageView) nsy.cMc_(this, R.id.settings_heart_rate_imageView);
        this.i = (HealthTextView) nsy.cMc_(this, R.id.heart_rate_content_tv);
        this.au = (HealthTextView) nsy.cMc_(this, R.id.heart_rate_tip1_tv);
        this.bb = (HealthTextView) nsy.cMc_(this, R.id.heart_rate_tip2_tv);
        this.ay = (HealthTextView) nsy.cMc_(this, R.id.heart_rate_tip3_tv);
        this.az = (HealthTextView) nsy.cMc_(this, R.id.heart_rate_tip4_tv);
        this.aw = (HealthTextView) nsy.cMc_(this, R.id.heart_rate_up_remind_explain);
        this.ax = (HealthTextView) nsy.cMc_(this, R.id.heart_rate_down_remind_explain);
        this.ao = (HealthTextView) nsy.cMc_(this, R.id.heart_rate_up_remind_content_explain);
        this.ag = (LinearLayout) nsy.cMc_(this, R.id.layout_raise_remind_number);
        this.r = (LinearLayout) nsy.cMc_(this, R.id.layout_down_remind_number);
        this.ai = (HealthTextView) nsy.cMc_(this, R.id.raise_remind_value);
        this.s = (HealthTextView) nsy.cMc_(this, R.id.down_remind_value);
        this.m = (ImageView) nsy.cMc_(this, R.id.image_right_down);
        this.ab = (ImageView) nsy.cMc_(this, R.id.image_right_raise);
        setViewSafeRegion(false, this.ao);
        this.aq = (HealthTextView) nsy.cMc_(this, R.id.heart_rate_down_remind_content_explain);
        d();
        DeviceCapability d = cvs.d();
        if (d != null) {
            if (!d.isSupportHeartRateRaiseAlarm()) {
                this.am.setVisibility(8);
            }
            if (!d.isSupportHeartRateDownAlarm()) {
                this.ak.setVisibility(8);
                this.aq.setVisibility(8);
            }
            LogUtil.a("ContinueHeartRateSettingActivity", "get device capablity");
            b(d);
        }
        this.am.setOnClickListener(this.as);
        this.ak.setOnClickListener(this.ar);
        if (LanguageUtil.ai(BaseApplication.getContext())) {
            this.ag.setLayoutDirection(1);
            this.r.setLayoutDirection(1);
        }
    }

    private void b(DeviceCapability deviceCapability) {
        int i;
        if (deviceCapability.isSupportContinueHeartRate()) {
            if (deviceCapability.isSupportHeartRateEnable()) {
                i = 3;
            }
            i = 0;
        } else {
            if (deviceCapability.isSupportHeartRateEnable()) {
                i = 1;
            }
            i = 0;
        }
        LogUtil.a("ContinueHeartRateSettingActivity", "get choice key " + i);
        a("heartRateSupportChoiceKey", i);
        c(i);
        a();
    }

    private void a() {
        ArrayList arrayList = new ArrayList(16);
        this.ad = arrayList;
        arrayList.add("continue_heart_rate");
        this.ad.add("custom.heart_rate_raise_remind");
        this.ad.add("custom.heart_rate_down_remind");
        this.ad.add("heart_rate_mode");
        this.v.getSwitchSetting(this.ad, new IBaseResponseCallback() { // from class: com.huawei.ui.device.activity.heartrate.ContinueHeartRateSettingActivity.13
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i, Object obj) {
                LogUtil.a("ContinueHeartRateSettingActivity", "initData list errCode is:", Integer.valueOf(i));
                if (i == 0 && (obj instanceof List)) {
                    List list = (List) obj;
                    final int i2 = 0;
                    final int b = CommonUtil.b(BaseApplication.getContext(), nvj.a(list, "continue_heart_rate"), 0);
                    LogUtil.a("ContinueHeartRateSettingActivity", "CONTINUE_HEART_RATE status :", Integer.valueOf(b));
                    String a2 = nvj.a(list, "custom.heart_rate_raise_remind");
                    ContinueHeartRateSettingActivity.this.y = CommonUtil.h(a2);
                    String a3 = nvj.a(list, "custom.heart_rate_down_remind");
                    ContinueHeartRateSettingActivity.this.p = CommonUtil.h(a3);
                    DeviceCapability d = cvs.d();
                    if (d.isSupportContinueHeartRate() && d.isSupportHeartRateEnable()) {
                        i2 = CommonUtil.h(nvj.a(list, "heart_rate_mode"));
                        LogUtil.a("ContinueHeartRateSettingActivity", "initItemSelect HEART_RATE_MODE support : ", Integer.valueOf(i2));
                    } else if (!d.isSupportContinueHeartRate() || d.isSupportHeartRateEnable()) {
                        i2 = 1;
                        LogUtil.a("ContinueHeartRateSettingActivity", "initData supportValue:", 1);
                    }
                    ContinueHeartRateSettingActivity.this.runOnUiThread(new Runnable() { // from class: com.huawei.ui.device.activity.heartrate.ContinueHeartRateSettingActivity.13.4
                        @Override // java.lang.Runnable
                        public void run() {
                            ContinueHeartRateSettingActivity.this.d(b, i2);
                        }
                    });
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(int i, int i2) {
        boolean z = i != 0;
        this.ah.setEnabled(z);
        this.ae.setEnabled(z);
        LogUtil.a("ContinueHeartRateSettingActivity", "switch checked is ", Boolean.valueOf(z));
        this.h.setChecked(z);
        this.h.setOnTouchListener(this.f);
        f(i);
        a(false, z, false);
        b(false, z, false);
        a("heartRateChoiceKey", i2);
        b(i2, z);
        this.av.release();
    }

    private void f(int i) {
        LogUtil.a("ContinueHeartRateSettingActivity", "updateViewBySwitchStatus: ", Integer.valueOf(i));
        if (i == 0) {
            this.aw.setTextColor(getResources().getColor(R.color._2131299241_res_0x7f090ba9));
            this.at.setTextColor(getResources().getColor(R.color._2131296698_res_0x7f0901ba));
            this.ao.setTextColor(getResources().getColor(R.color._2131296698_res_0x7f0901ba));
            this.am.setEnabled(false);
            this.ax.setTextColor(getResources().getColor(R.color._2131297822_res_0x7f09061e));
            this.ap.setTextColor(getResources().getColor(R.color._2131296698_res_0x7f0901ba));
            this.aq.setTextColor(getResources().getColor(R.color._2131296698_res_0x7f0901ba));
            this.e.setTextColor(getResources().getColor(R.color._2131296698_res_0x7f0901ba));
            this.n.setTextColor(getResources().getColor(R.color._2131296698_res_0x7f0901ba));
            this.j.setTextColor(getResources().getColor(R.color._2131296698_res_0x7f0901ba));
            this.b.setTextColor(getResources().getColor(R.color._2131296698_res_0x7f0901ba));
            this.ak.setEnabled(false);
            return;
        }
        if (i == 1) {
            this.aw.setTextColor(getResources().getColor(R.color._2131297823_res_0x7f09061f));
            this.at.setTextColor(getResources().getColor(R.color._2131299241_res_0x7f090ba9));
            this.ao.setTextColor(getResources().getColor(R.color._2131299241_res_0x7f090ba9));
            this.am.setEnabled(true);
            this.ax.setTextColor(getResources().getColor(R.color._2131297823_res_0x7f09061f));
            this.ap.setTextColor(getResources().getColor(R.color._2131299241_res_0x7f090ba9));
            this.aq.setTextColor(getResources().getColor(R.color._2131299241_res_0x7f090ba9));
            this.ak.setEnabled(true);
            this.e.setTextColor(getResources().getColor(R.color._2131297823_res_0x7f09061f));
            this.n.setTextColor(getResources().getColor(R.color._2131297823_res_0x7f09061f));
            this.j.setTextColor(getResources().getColor(R.color._2131299241_res_0x7f090ba9));
            this.b.setTextColor(getResources().getColor(R.color._2131299241_res_0x7f090ba9));
            return;
        }
        LogUtil.h("ContinueHeartRateSettingActivity", "no this status");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(boolean z, boolean z2, boolean z3) {
        int i;
        ReleaseLogUtil.e("DEVMGR_ContinueHeartRateSettingActivity", "updateViewByHeartRateNumber isSendCommand:", Boolean.valueOf(z), ", isContinueHeartRateIsChecked:", Boolean.valueOf(z2), ", isMigrateHeartRateRaiseRemind:", Boolean.valueOf(z3));
        int i2 = this.y;
        this.d = i2;
        if (z2 && i2 != 0) {
            this.ao.setText(getResources().getString(R.string._2130842696_res_0x7f021448, getResources().getString(R.string._2130837641_res_0x7f020089, UnitUtil.e(10.0d, 1, 0)), UnitUtil.e(this.y, 1, 0)));
            String c = nvj.c(this.y);
            this.ai.setText(c);
            b(this.at, CommonUtil.m(BaseApplication.getContext(), c));
            if (z) {
                c(1, this.y, true);
            }
        } else {
            try {
                i = Integer.parseInt(UnitUtil.e(10.0d, 1, 0));
            } catch (NumberFormatException unused) {
                LogUtil.b("ContinueHeartRateSettingActivity", "updateViewByHeartRateNumber NumberFormatException");
                i = 10;
            }
            this.ao.setText(getResources().getString(R.string._2130843450_res_0x7f02173a, Integer.valueOf(i)));
            this.at.setText(R.string._2130841259_res_0x7f020eab);
            this.ai.setText("");
            if (z) {
                c(0, 0, z3);
            } else if (z3) {
                this.v.setSwitchSetting("custom.heart_rate_raise_remind", this.y + "", null);
            }
        }
        if (LanguageUtil.bc(BaseApplication.getContext())) {
            this.ab.setBackgroundResource(R.drawable._2131427841_res_0x7f0b0201);
        } else {
            this.ab.setBackgroundResource(R.drawable._2131427842_res_0x7f0b0202);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(boolean z, boolean z2, boolean z3) {
        DeviceCapability d;
        ReleaseLogUtil.e("DEVMGR_ContinueHeartRateSettingActivity", "updateViewByHeartRateNumberDownRate isSendCommand:", Boolean.valueOf(z), ", isContinueHeartRateChecked:", Boolean.valueOf(z2), ", isMigrateHeartRateRaiseRemind:", Boolean.valueOf(z3));
        int i = this.p;
        this.c = i;
        if (z2 && i != 0) {
            HealthTextView healthTextView = this.aq;
            Resources resources = getResources();
            String quantityString = getResources().getQuantityString(R.plurals._2130903333_res_0x7f030125, 10, 10);
            Resources resources2 = getResources();
            int i2 = this.p;
            healthTextView.setText(resources.getString(R.string._2130846566_res_0x7f022366, quantityString, resources2.getQuantityString(R.plurals._2130903203_res_0x7f0300a3, i2, Integer.valueOf(i2))));
            String c = nvj.c(this.p);
            this.s.setText(c);
            b(this.ap, CommonUtil.m(BaseApplication.getContext(), c));
            if (z) {
                e(1, this.p, true);
            }
        } else {
            this.aq.setText(getResources().getString(R.string._2130846567_res_0x7f022367, getResources().getString(R.string._2130837641_res_0x7f020089, UnitUtil.e(10.0d, 1, 0))));
            this.ap.setText(R.string._2130841259_res_0x7f020eab);
            this.s.setText("");
            if (z) {
                e(0, 0, z3);
            } else if (z3 && (d = cvs.d()) != null && d.isSupportHeartRateDownAlarm()) {
                this.v.setSwitchSetting("custom.heart_rate_down_remind", this.p + "", null);
                LogUtil.a("ContinueHeartRateSettingActivity", "update view by heart rate down");
            }
        }
        if (LanguageUtil.bc(BaseApplication.getContext())) {
            this.m.setBackgroundResource(R.drawable._2131427841_res_0x7f0b0201);
        } else {
            this.m.setBackgroundResource(R.drawable._2131427842_res_0x7f0b0202);
        }
    }

    private void c(int i, int i2, boolean z) {
        LogUtil.a("ContinueHeartRateSettingActivity", "openOrCloseHeartRateRaiseRemindEnable openOrClose: ", Integer.valueOf(i), " number: ", Integer.valueOf(i2));
        DeviceSettingsInteractors deviceSettingsInteractors = this.k;
        if (deviceSettingsInteractors == null) {
            LogUtil.h("ContinueHeartRateSettingActivity", "openOrCloseHeartRateRaiseRemindEnable mDeviceInteractors is null");
        } else {
            deviceSettingsInteractors.d(i, i2, z);
        }
    }

    private void e(int i, int i2, boolean z) {
        LogUtil.a("ContinueHeartRateSettingActivity", "openOrCloseHeartRateDownRemindEnable openOrClose: ", Integer.valueOf(i), " number: ", Integer.valueOf(i2));
        DeviceSettingsInteractors deviceSettingsInteractors = this.k;
        if (deviceSettingsInteractors == null) {
            LogUtil.h("ContinueHeartRateSettingActivity", "openOrCloseHeartRateDownRemindEnable mDeviceInteractors is null");
        } else {
            deviceSettingsInteractors.a(i, i2, z);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b() {
        this.an.clear();
        this.x.clear();
        ReleaseLogUtil.e("DEVMGR_ContinueHeartRateSettingActivity", "showDialog()");
        LayoutInflater layoutInflater = getSystemService("layout_inflater") instanceof LayoutInflater ? (LayoutInflater) getSystemService("layout_inflater") : null;
        if (layoutInflater != null) {
            View inflate = layoutInflater.inflate(R.layout.hw_show_setting_heart_reate_rasise_remind, (ViewGroup) null);
            cPT_(inflate);
            CustomAlertDialog.Builder builder = new CustomAlertDialog.Builder(this);
            builder.a(getString(R$string.IDS_heartrate_raise_remind)).cyp_(inflate).cyo_(R$string.IDS_hw_common_ui_dialog_confirm, new DialogInterface.OnClickListener() { // from class: com.huawei.ui.device.activity.heartrate.ContinueHeartRateSettingActivity.3
                @Override // android.content.DialogInterface.OnClickListener
                public void onClick(DialogInterface dialogInterface, int i) {
                    LogUtil.a("ContinueHeartRateSettingActivity", "dialog set positive");
                    ContinueHeartRateSettingActivity continueHeartRateSettingActivity = ContinueHeartRateSettingActivity.this;
                    continueHeartRateSettingActivity.y = continueHeartRateSettingActivity.d;
                    ContinueHeartRateSettingActivity.this.a(true, true, true);
                    ViewClickInstrumentation.clickOnDialog(dialogInterface, i);
                }
            }).cyn_(R$string.IDS_hw_show_cancel, new DialogInterface.OnClickListener() { // from class: com.huawei.ui.device.activity.heartrate.ContinueHeartRateSettingActivity.15
                @Override // android.content.DialogInterface.OnClickListener
                public void onClick(DialogInterface dialogInterface, int i) {
                    LogUtil.a("ContinueHeartRateSettingActivity", "dialog set negative");
                    ViewClickInstrumentation.clickOnDialog(dialogInterface, i);
                }
            });
            CustomAlertDialog c = builder.c();
            c.setCancelable(false);
            c.show();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void g() {
        this.aj.clear();
        this.z.clear();
        ReleaseLogUtil.e("DEVMGR_ContinueHeartRateSettingActivity", "showDialogDownRate()");
        LayoutInflater layoutInflater = getSystemService("layout_inflater") instanceof LayoutInflater ? (LayoutInflater) getSystemService("layout_inflater") : null;
        if (layoutInflater != null) {
            View inflate = layoutInflater.inflate(R.layout.hw_show_setting_heart_reate_down_remind, (ViewGroup) null);
            cPU_(inflate);
            CustomAlertDialog.Builder builder = new CustomAlertDialog.Builder(this);
            builder.a(getString(R$string.IDS_heartrate_down_remind)).cyp_(inflate).cyo_(R$string.IDS_hw_common_ui_dialog_confirm, new DialogInterface.OnClickListener() { // from class: com.huawei.ui.device.activity.heartrate.ContinueHeartRateSettingActivity.4
                @Override // android.content.DialogInterface.OnClickListener
                public void onClick(DialogInterface dialogInterface, int i) {
                    LogUtil.a("ContinueHeartRateSettingActivity", "dialog set positive");
                    ContinueHeartRateSettingActivity continueHeartRateSettingActivity = ContinueHeartRateSettingActivity.this;
                    continueHeartRateSettingActivity.p = continueHeartRateSettingActivity.c;
                    ContinueHeartRateSettingActivity.this.b(true, true, true);
                    ViewClickInstrumentation.clickOnDialog(dialogInterface, i);
                }
            }).cyn_(R$string.IDS_hw_show_cancel, new DialogInterface.OnClickListener() { // from class: com.huawei.ui.device.activity.heartrate.ContinueHeartRateSettingActivity.2
                @Override // android.content.DialogInterface.OnClickListener
                public void onClick(DialogInterface dialogInterface, int i) {
                    LogUtil.a("ContinueHeartRateSettingActivity", "dialog set negative");
                    ViewClickInstrumentation.clickOnDialog(dialogInterface, i);
                }
            });
            CustomAlertDialog c = builder.c();
            c.setCancelable(false);
            c.show();
        }
    }

    private void b(HealthTextView healthTextView, int i) {
        healthTextView.setText(getResources().getQuantityString(R.plurals._2130903203_res_0x7f0300a3, i, Integer.valueOf(i)));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(int i) {
        ReleaseLogUtil.e("DEVMGR_ContinueHeartRateSettingActivity", "Enter updateContinueHighItemDrawable");
        for (Map.Entry<Integer, HealthRadioButton> entry : this.x.entrySet()) {
            entry.getValue().setChecked(i == entry.getKey().intValue());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(int i) {
        ReleaseLogUtil.e("DEVMGR_ContinueHeartRateSettingActivity", "Enter updateItemSelectButtonDrawableDownRate");
        for (Map.Entry<Integer, HealthRadioButton> entry : this.z.entrySet()) {
            entry.getValue().setChecked(i == entry.getKey().intValue());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(int i) {
        for (Map.Entry<Integer, ImageView> entry : this.ac.entrySet()) {
            int intValue = entry.getKey().intValue();
            ImageView value = entry.getValue();
            if (i == intValue) {
                value.setImageResource(R.drawable._2131427695_res_0x7f0b016f);
            } else {
                value.setImageResource(R.drawable._2131427694_res_0x7f0b016e);
            }
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        this.x.clear();
        this.an.clear();
        this.al.clear();
        this.ac.clear();
        CommonUtil.a(this);
    }

    private void cPT_(View view) {
        b((HealthTextView) nsy.cMd_(view, R.id.tv_100), 100);
        b((HealthTextView) nsy.cMd_(view, R.id.tv_110), 110);
        b((HealthTextView) nsy.cMd_(view, R.id.tv_120), 120);
        HealthTextView healthTextView = (HealthTextView) nsy.cMd_(view, R.id.tv_130);
        Integer valueOf = Integer.valueOf(OldToNewMotionPath.SPORT_TYPE_TENNIS);
        b(healthTextView, OldToNewMotionPath.SPORT_TYPE_TENNIS);
        b((HealthTextView) nsy.cMd_(view, R.id.tv_140), 140);
        b((HealthTextView) nsy.cMd_(view, R.id.tv_150), 150);
        RelativeLayout relativeLayout = (RelativeLayout) nsy.cMd_(view, R.id.rl_close);
        this.an.put(0, relativeLayout);
        this.x.put(0, (HealthRadioButton) nsy.cMd_(view, R.id.iv_close));
        RelativeLayout relativeLayout2 = (RelativeLayout) nsy.cMd_(view, R.id.rl_100);
        this.an.put(100, relativeLayout2);
        this.x.put(100, (HealthRadioButton) nsy.cMd_(view, R.id.iv_100));
        RelativeLayout relativeLayout3 = (RelativeLayout) nsy.cMd_(view, R.id.rl_110);
        this.an.put(110, relativeLayout3);
        this.x.put(110, (HealthRadioButton) nsy.cMd_(view, R.id.iv_110));
        RelativeLayout relativeLayout4 = (RelativeLayout) nsy.cMd_(view, R.id.rl_120);
        this.an.put(120, relativeLayout4);
        this.x.put(120, (HealthRadioButton) nsy.cMd_(view, R.id.iv_120));
        RelativeLayout relativeLayout5 = (RelativeLayout) nsy.cMd_(view, R.id.rl_130);
        this.an.put(valueOf, relativeLayout5);
        this.x.put(valueOf, (HealthRadioButton) nsy.cMd_(view, R.id.iv_130));
        RelativeLayout relativeLayout6 = (RelativeLayout) nsy.cMd_(view, R.id.rl_140);
        this.an.put(140, relativeLayout6);
        this.x.put(140, (HealthRadioButton) nsy.cMd_(view, R.id.iv_140));
        RelativeLayout relativeLayout7 = (RelativeLayout) nsy.cMd_(view, R.id.rl_150);
        this.an.put(150, relativeLayout7);
        this.x.put(150, (HealthRadioButton) nsy.cMd_(view, R.id.iv_150));
        relativeLayout.setOnClickListener(this.l);
        relativeLayout2.setOnClickListener(this.l);
        relativeLayout3.setOnClickListener(this.l);
        relativeLayout4.setOnClickListener(this.l);
        relativeLayout5.setOnClickListener(this.l);
        relativeLayout6.setOnClickListener(this.l);
        relativeLayout7.setOnClickListener(this.l);
        e(this.y);
    }

    private void cPU_(View view) {
        b((HealthTextView) nsy.cMd_(view, R.id.tv_50), 50);
        b((HealthTextView) nsy.cMd_(view, R.id.tv_45), 45);
        b((HealthTextView) nsy.cMd_(view, R.id.tv_40), 40);
        RelativeLayout relativeLayout = (RelativeLayout) nsy.cMd_(view, R.id.rl_close);
        this.aj.put(0, relativeLayout);
        this.z.put(0, (HealthRadioButton) nsy.cMd_(view, R.id.iv_close));
        RelativeLayout relativeLayout2 = (RelativeLayout) nsy.cMd_(view, R.id.rl_50);
        this.aj.put(50, relativeLayout2);
        this.z.put(50, (HealthRadioButton) nsy.cMd_(view, R.id.iv_50));
        RelativeLayout relativeLayout3 = (RelativeLayout) nsy.cMd_(view, R.id.rl_45);
        this.aj.put(45, relativeLayout3);
        this.z.put(45, (HealthRadioButton) nsy.cMd_(view, R.id.iv_45));
        RelativeLayout relativeLayout4 = (RelativeLayout) nsy.cMd_(view, R.id.rl_40);
        this.aj.put(40, relativeLayout4);
        this.z.put(40, (HealthRadioButton) nsy.cMd_(view, R.id.iv_40));
        relativeLayout.setOnClickListener(this.o);
        relativeLayout2.setOnClickListener(this.o);
        relativeLayout3.setOnClickListener(this.o);
        relativeLayout4.setOnClickListener(this.o);
        b(this.p);
    }

    private void c(int i) {
        LogUtil.a("ContinueHeartRateSettingActivity", "heart rate lqm init choice choice key is ", Integer.valueOf(i));
        this.ah = (RelativeLayout) nsy.cMc_(this, R.id.radio_heart_rate_auto_measurement);
        this.e = (HealthTextView) nsy.cMc_(this, R.id.tv_heart_rate_auto_measurement);
        this.f9095a = (ImageView) nsy.cMc_(this, R.id.iv_heart_rate_auto_measurement);
        this.b = (HealthTextView) nsy.cMc_(this, R.id.tv_heart_rate_auto_measurement_detail);
        this.ae = (RelativeLayout) nsy.cMc_(this, R.id.radio_heart_rate_cycle_measurement);
        this.n = (HealthTextView) nsy.cMc_(this, R.id.tv_heart_rate_cycle_measurement);
        this.g = (ImageView) nsy.cMc_(this, R.id.iv_heart_rate_cycle_measurement);
        this.j = (HealthTextView) nsy.cMc_(this, R.id.tv_heart_rate_cycle_measurement_detail);
        this.q = (HealthSubHeader) nsy.cMc_(this, R.id.subheader_heart_rate_measurement);
        this.aa = (LinearLayout) nsy.cMc_(this, R.id.settings_heart_rate_layout);
        this.w = (HealthTextView) nsy.cMc_(this, R.id.heart_rate_tv_sub);
        ViewGroup.LayoutParams layoutParams = this.aa.getLayoutParams();
        if (i == 3) {
            this.aa.setLayoutParams(layoutParams);
            e();
        } else {
            this.aa.setLayoutParams(layoutParams);
            a(i);
        }
    }

    private int b(String str) {
        return BaseApplication.getContext().getSharedPreferences("heartRateChoiceSP", 0).getInt(str, -1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(String str, int i) {
        SharedPreferences.Editor edit = BaseApplication.getContext().getSharedPreferences("heartRateChoiceSP", 0).edit();
        edit.putInt(str, i);
        edit.apply();
    }

    private void d(int i, boolean z) {
        if (z) {
            if (i == 0) {
                this.f9095a.setImageResource(R.drawable._2131427695_res_0x7f0b016f);
                this.g.setImageResource(R.drawable._2131427694_res_0x7f0b016e);
                return;
            } else if (i == 1) {
                this.f9095a.setImageResource(R.drawable._2131427694_res_0x7f0b016e);
                this.g.setImageResource(R.drawable._2131427695_res_0x7f0b016f);
                return;
            } else {
                LogUtil.c("ContinueHeartRateSettingActivity", "isHeartRateSwitchChecked not get heart rate choice");
                return;
            }
        }
        if (i == 0) {
            this.f9095a.setImageResource(R.drawable._2131427713_res_0x7f0b0181);
            this.g.setImageResource(R.drawable._2131427708_res_0x7f0b017c);
        } else if (i == 1) {
            this.f9095a.setImageResource(R.drawable._2131427708_res_0x7f0b017c);
            this.g.setImageResource(R.drawable._2131427713_res_0x7f0b0181);
        } else {
            LogUtil.c("ContinueHeartRateSettingActivity", "!isHeartRateSwitchChecked not get heart rate choice");
        }
    }

    private void b(int i, boolean z) {
        if (this.ah.getVisibility() == 0) {
            if (i == -1 || i == 0) {
                LogUtil.a("ContinueHeartRateSettingActivity", "init click continue click");
                d(0, z);
            } else if (i == 1) {
                LogUtil.a("ContinueHeartRateSettingActivity", "init cycle continue click");
                d(1, z);
            } else {
                LogUtil.c("ContinueHeartRateSettingActivity", "updateSwitchStatus not get heart rate choice");
            }
        }
    }

    private void b(boolean z) {
        if (!z) {
            this.ae.setOnClickListener(null);
            this.ah.setOnClickListener(null);
            this.ae.setEnabled(false);
            this.ah.setEnabled(false);
            return;
        }
        this.ah.setOnClickListener(this.af);
        this.ae.setOnClickListener(this.af);
        this.ae.setEnabled(true);
        this.ah.setEnabled(true);
    }

    private void d(boolean z, nyn nynVar) {
        LogUtil.a("ContinueHeartRateSettingActivity", "sendHearRateMeasureCommand onCheckedChanged: ", Boolean.valueOf(z));
        this.ah.setEnabled(z);
        this.ae.setEnabled(z);
        boolean b = nynVar.b();
        int e = nynVar.e();
        if (e == 0) {
            LogUtil.a("ContinueHeartRateSettingActivity", "send continue heart rate ", Integer.valueOf(b ? 1 : 0));
            DeviceSettingsInteractors deviceSettingsInteractors = this.k;
            if (deviceSettingsInteractors != null) {
                deviceSettingsInteractors.a(b ? 1 : 0);
            } else {
                LogUtil.h("ContinueHeartRateSettingActivity", "sendHearRateMeasureCommand support continue, mDeviceInteractors is null");
            }
            f(b ? 1 : 0);
            return;
        }
        if (e == 1) {
            LogUtil.a("ContinueHeartRateSettingActivity", "send cycle heart rate ", Integer.valueOf(b ? 1 : 0));
            DeviceSettingsInteractors deviceSettingsInteractors2 = this.k;
            if (deviceSettingsInteractors2 != null) {
                deviceSettingsInteractors2.c(b ? 1 : 0);
            } else {
                LogUtil.h("ContinueHeartRateSettingActivity", "sendHearRateMeasureCommand support cycle, mDeviceInteractors is null");
            }
            f(b ? 1 : 0);
            return;
        }
        LogUtil.c("ContinueHeartRateSettingActivity", "sendHearRateMeasureCommand not get heart rate choice");
    }

    private void e() {
        this.ah.setVisibility(0);
        this.e.setVisibility(0);
        this.f9095a.setVisibility(0);
        this.b.setVisibility(0);
        this.ae.setVisibility(0);
        this.n.setVisibility(0);
        this.g.setVisibility(0);
        this.j.setVisibility(0);
        this.al.put(0, this.ah);
        this.al.put(1, this.ae);
        this.ac.put(0, this.f9095a);
        this.ac.put(1, this.g);
        this.ah.setOnClickListener(this.af);
        this.ae.setOnClickListener(this.af);
        this.w.setVisibility(8);
        this.q.setVisibility(0);
        String string = getResources().getString(R.string._2130843131_res_0x7f0215fb);
        String string2 = getResources().getString(R.string._2130843133_res_0x7f0215fd);
        String c = nvj.c(24);
        this.b.setText(String.format(string2, Integer.valueOf(CommonUtil.m(BaseApplication.getContext(), c))));
        this.j.setText(String.format(string, Integer.valueOf(CommonUtil.m(BaseApplication.getContext(), c))));
    }

    private void a(int i) {
        this.ah.setVisibility(8);
        this.ah.setEnabled(false);
        this.e.setVisibility(8);
        this.f9095a.setVisibility(8);
        this.b.setVisibility(8);
        this.ae.setVisibility(8);
        this.ae.setEnabled(false);
        this.n.setVisibility(8);
        this.g.setVisibility(8);
        this.j.setVisibility(8);
        this.q.setVisibility(8);
        this.w.setVisibility(0);
        if (i == 0) {
            this.w.setText(String.format(getResources().getString(R.string._2130843137_res_0x7f021601), 24));
        } else if (i == 1) {
            this.w.setText(String.format(getResources().getString(R.string._2130843138_res_0x7f021602), 24));
        } else {
            this.w.setVisibility(8);
            this.w.setText("");
        }
    }

    private void d() {
        String string;
        String string2;
        String string3;
        DeviceSettingsInteractors deviceSettingsInteractors = this.k;
        if (deviceSettingsInteractors == null) {
            LogUtil.h("ContinueHeartRateSettingActivity", "initTipText mDeviceInteractors is null");
            return;
        }
        DeviceInfo a2 = deviceSettingsInteractors.a();
        if (a2 == null) {
            LogUtil.h("ContinueHeartRateSettingActivity", "refresh dialog Support deviceInfo is null , return");
            return;
        }
        if (!jfu.h(a2.getProductType())) {
            LogUtil.a("ContinueHeartRateSettingActivity", "WATCH PRODUCT");
            string = getResources().getString(R.string._2130842698_res_0x7f02144a);
            string2 = getResources().getString(R.string._2130842232_res_0x7f021278);
            string3 = getResources().getString(R.string.IDS_hwh_motiontrack_device_using_tips);
            this.u.setImageResource(R.drawable._2131428622_res_0x7f0b050e);
        } else {
            string = getResources().getString(R.string._2130842697_res_0x7f021449);
            string2 = getResources().getString(R.string._2130842229_res_0x7f021275);
            string3 = getResources().getString(R.string._2130843194_res_0x7f02163a);
            this.u.setImageResource(R.drawable._2131428621_res_0x7f0b050d);
        }
        String string4 = getResources().getString(R.string._2130842699_res_0x7f02144b);
        String string5 = getResources().getString(R.string._2130843136_res_0x7f021600);
        this.i.setText(String.format(string, 24));
        this.au.setText(String.format(string2, 1));
        this.bb.setText(String.format(string4, 2));
        this.ay.setText(String.format(string3, 3));
        this.az.setText(String.format(string5, 4));
    }

    private void e(boolean z, int i) {
        nyn nynVar = new nyn();
        nynVar.b(z);
        nynVar.b(i);
        boolean b = nynVar.b();
        d(z, nynVar);
        LogUtil.a("ContinueHeartRateSettingActivity", "isOpenHeartRateMeasure is ", Boolean.valueOf(b));
        b(b);
        int e = nynVar.e();
        if (this.ah.getVisibility() == 0) {
            d(e, b);
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
