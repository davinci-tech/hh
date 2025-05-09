package com.huawei.ui.device.activity.intelligenthome;

import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.SpannableString;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.huawei.callback.FitnessCloudCallBack;
import com.huawei.datatype.SmartAlarmInfo;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.health.R;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hms.support.api.entity.common.CommonConstant;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcloudmodel.model.intelligent.StartDeviceLinkageResponse;
import com.huawei.hwcloudmodel.model.intelligent.StopDeviceLinkageResponse;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.operation.OpAnalyticsConstants;
import com.huawei.operation.activity.WebViewActivity;
import com.huawei.operation.utils.Constants;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.dialog.CommonDialog21;
import com.huawei.ui.commonui.dialog.CustomTextAlertDialog;
import com.huawei.ui.commonui.dialog.NoTitleCustomAlertDialog;
import com.huawei.ui.commonui.divider.HealthDivider;
import com.huawei.ui.commonui.dotspageindicator.HealthDotsPageIndicator;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.scrollview.HealthScrollView;
import com.huawei.ui.commonui.switchbutton.HealthSwitchButton;
import com.huawei.ui.commonui.titlebar.CustomTitleBar;
import com.huawei.ui.commonui.viewpager.HealthPagerAdapter;
import com.huawei.ui.commonui.viewpager.HealthViewPager;
import com.huawei.ui.device.activity.alarm.AlarmActivity;
import com.huawei.ui.device.activity.intelligenthome.IntelligentHomeLinkageActivity;
import com.huawei.ui.device.interactors.DeviceSettingsInteractors;
import defpackage.cvs;
import defpackage.ixx;
import defpackage.jcf;
import defpackage.jdx;
import defpackage.jgh;
import defpackage.jgy;
import defpackage.jpt;
import defpackage.jqi;
import defpackage.nrh;
import defpackage.nsf;
import defpackage.nsn;
import defpackage.nsy;
import defpackage.oal;
import health.compact.a.CommonUtil;
import health.compact.a.GRSManager;
import health.compact.a.LanguageUtil;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;
import health.compact.a.Utils;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

/* loaded from: classes6.dex */
public class IntelligentHomeLinkageActivity extends BaseActivity {
    private static final Object b = new Object();
    private static String e = "7:00";

    /* renamed from: a, reason: collision with root package name */
    private oal f9106a;
    private String aa;
    private String ab;
    private List<ImageView> ad;
    private LinearLayout ae;
    private RelativeLayout ag;
    private String ah;
    private CustomTitleBar ai;
    private boolean ak;
    private ImageView al;
    private RelativeLayout am;
    private CommonDialog21 ao;
    private HealthTextView ap;
    private ImageView aq;
    private String ar;
    private ImageView as;
    private LinearLayout at;
    private CustomTextAlertDialog au;
    private LinearLayout av;
    private ImageView aw;
    private String ay;
    private int az;
    private int ba;
    private HealthTextView bb;
    private CustomTextAlertDialog bc;
    private int bd;
    private int be;
    private SmartAlarmInfo bf;
    private RelativeLayout bg;
    private List<SmartAlarmInfo> bh;
    private HealthTextView bi;
    private HealthTextView bj;
    private int bk;
    private int bl;
    private HealthSwitchButton bm;
    private HealthTextView bn;
    private RelativeLayout bo;
    private RelativeLayout bp;
    private HealthTextView bq;
    private HealthViewPager bt;
    private HealthTextView bu;
    private LinearLayout bv;
    private String bw;
    private RelativeLayout bx;
    private HealthDivider by;
    private NoTitleCustomAlertDialog c;
    private HealthTextView cb;
    private String d;
    private Context f;
    private String g;
    private CustomTextAlertDialog h;
    private DeviceInfo i;
    private HealthScrollView l;
    private HealthTextView m;
    private HealthTextView n;
    private DeviceSettingsInteractors o;
    private jgy p;
    private NoTitleCustomAlertDialog q;
    private ImageView r;
    private String s;
    private HealthDotsPageIndicator t;
    private HealthTextView u;
    private String w;
    private RelativeLayout x;
    private jgh y;
    private jqi z;
    private HealthTextView bz = null;
    private int[] ac = {R.mipmap._2131820854_res_0x7f110136, R.mipmap._2131821445_res_0x7f110385};
    private int[] bs = {R.string.IDS_device_to_intelligent_home_linkage_after_sleep, R.string.IDS_device_to_intelligent_home_linkage_after_wake_up};
    private String[] br = null;
    private Handler v = new c(this);
    private boolean an = false;
    private String k = "";
    private boolean aj = false;
    private String j = "";
    private HealthViewPager.OnPageChangeListener ax = new HealthViewPager.OnPageChangeListener() { // from class: com.huawei.ui.device.activity.intelligenthome.IntelligentHomeLinkageActivity.2
        @Override // com.huawei.uikit.hwviewpager.widget.HwViewPager.OnPageChangeListener
        public void onPageSelected(int i) {
            LogUtil.a("IntelligentHomeLinkageActivity", "ProductIntroductionFragment onPageSelected() position = ", Integer.valueOf(i));
            IntelligentHomeLinkageActivity.this.e(i);
        }

        @Override // com.huawei.uikit.hwviewpager.widget.HwViewPager.OnPageChangeListener
        public void onPageScrolled(int i, float f, int i2) {
            LogUtil.a("IntelligentHomeLinkageActivity", "ProductIntroductionFragment onPageScrolled() position = ", Integer.valueOf(i), " positionOffset = ", Float.valueOf(f), " positionOffsetPixels = ", Integer.valueOf(i2));
        }

        @Override // com.huawei.uikit.hwviewpager.widget.HwViewPager.OnPageChangeListener
        public void onPageScrollStateChanged(int i) {
            LogUtil.a("IntelligentHomeLinkageActivity", "ProductIntroductionFragment onPageScrollStateChanged() state = ", Integer.valueOf(i));
        }
    };
    private CompoundButton.OnCheckedChangeListener af = new CompoundButton.OnCheckedChangeListener() { // from class: com.huawei.ui.device.activity.intelligenthome.IntelligentHomeLinkageActivity.3
        @Override // android.widget.CompoundButton.OnCheckedChangeListener
        public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
            LogUtil.a("03", 1, "IntelligentHomeLinkageActivity", "INTELLIGENT_HOME_LINKAGE is onCheckedChanged isChecked = ", Boolean.valueOf(z));
            Intent intent = new Intent();
            if (z) {
                DeviceSettingsInteractors unused = IntelligentHomeLinkageActivity.this.o;
                intent.putExtra("status", "1");
            } else {
                DeviceSettingsInteractors unused2 = IntelligentHomeLinkageActivity.this.o;
                intent.putExtra("status", "0");
            }
            IntelligentHomeLinkageActivity.this.setResult(-1, intent);
            IntelligentHomeLinkageActivity.this.d(AnalyticsValue.SETTING_1090014.value(), true, true, z);
            ViewClickInstrumentation.clickOnView(compoundButton);
        }
    };

    private void g() {
        jdx.b(new Runnable() { // from class: com.huawei.ui.device.activity.intelligenthome.IntelligentHomeLinkageActivity.1
            @Override // java.lang.Runnable
            public void run() {
                IntelligentHomeLinkageActivity.this.d = GRSManager.a(BaseApplication.getContext()).getUrl("domainResourcephsVmall");
                IntelligentHomeLinkageActivity.this.g = GRSManager.a(BaseApplication.getContext()).getUrl("domainSmarthomeHicloud");
            }
        });
    }

    class c extends Handler {
        WeakReference<IntelligentHomeLinkageActivity> c;

        c(IntelligentHomeLinkageActivity intelligentHomeLinkageActivity) {
            this.c = new WeakReference<>(intelligentHomeLinkageActivity);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            if (this.c.get() == null) {
            }
            LogUtil.a("IntelligentHomeLinkageActivity", "Enter handleMessage():", Integer.valueOf(message.what));
            switch (message.what) {
                case 1:
                    IntelligentHomeLinkageActivity.this.bl = ((Integer) message.obj).intValue();
                    LogUtil.a("IntelligentHomeLinkageActivity", "Enter handleMessage() mSmartEnable :", Integer.valueOf(IntelligentHomeLinkageActivity.this.bl));
                    IntelligentHomeLinkageActivity.this.aa();
                    break;
                case 2:
                    IntelligentHomeLinkageActivity.this.an = ((Boolean) message.obj).booleanValue();
                    LogUtil.a("IntelligentHomeLinkageActivity", "Enter handleMessage() mIsSwitchChecked :", Boolean.valueOf(IntelligentHomeLinkageActivity.this.an));
                    IntelligentHomeLinkageActivity.this.t();
                    IntelligentHomeLinkageActivity.this.aa();
                    IntelligentHomeLinkageActivity.this.bm.setChecked(IntelligentHomeLinkageActivity.this.an);
                    IntelligentHomeLinkageActivity.this.bm.setOnCheckedChangeListener(IntelligentHomeLinkageActivity.this.af);
                    break;
                case 3:
                    IntelligentHomeLinkageActivity.this.cQe_(message);
                    break;
                case 4:
                    IntelligentHomeLinkageActivity.this.cQg_(message);
                    break;
                case 5:
                    if (IntelligentHomeLinkageActivity.this.ao != null) {
                        IntelligentHomeLinkageActivity.this.ao.dismiss();
                        IntelligentHomeLinkageActivity.this.ao = null;
                    }
                    IntelligentHomeLinkageActivity.this.cQf_(message);
                    break;
                case 6:
                    LogUtil.a("IntelligentHomeLinkageActivity", "msg is INTELLIGENT_HOME_TIME_OUT");
                    if (IntelligentHomeLinkageActivity.this.v != null) {
                        IntelligentHomeLinkageActivity.this.v.removeMessages(6);
                    }
                    if (IntelligentHomeLinkageActivity.this.ao != null) {
                        IntelligentHomeLinkageActivity.this.ao.dismiss();
                        IntelligentHomeLinkageActivity.this.ao = null;
                    }
                    nrh.d(IntelligentHomeLinkageActivity.this.f, IntelligentHomeLinkageActivity.this.f.getString(R.string._2130841392_res_0x7f020f30));
                    break;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void cQe_(Message message) {
        Handler handler = this.v;
        if (handler != null) {
            handler.removeMessages(6);
        }
        CommonDialog21 commonDialog21 = this.ao;
        if (commonDialog21 != null) {
            commonDialog21.dismiss();
            this.ao = null;
        }
        StartDeviceLinkageResponse startDeviceLinkageResponse = message.obj instanceof StartDeviceLinkageResponse ? (StartDeviceLinkageResponse) message.obj : null;
        if (startDeviceLinkageResponse != null) {
            if (startDeviceLinkageResponse.getResultCode().intValue() == 0) {
                this.an = !this.ak;
                StringBuffer stringBuffer = new StringBuffer(16);
                stringBuffer.append(startDeviceLinkageResponse.getDevId()).append("&&").append(startDeviceLinkageResponse.getExpiresIn()).append("&&").append(startDeviceLinkageResponse.getDevice_prodId()).append("&&").append(this.an).append("&&false");
                if (this.i != null) {
                    this.z.setSwitchSetting("intelligent_home_linkage", stringBuffer.toString(), this.i.getDeviceIdentify(), null);
                }
                this.bm.setChecked(!this.ak);
                LogUtil.a("IntelligentHomeLinkageActivity", "startDeviceLinkage is success startDeviceLinkageRsq ", startDeviceLinkageResponse);
            } else {
                LogUtil.a("IntelligentHomeLinkageActivity", "startDeviceLinkage is fail startDeviceLinkageRsq resultCode ", startDeviceLinkageResponse.getResultCode(), "resultDesc ", startDeviceLinkageResponse.getResultDesc());
                Context context = this.f;
                nrh.d(context, context.getString(R.string._2130842544_res_0x7f0213b0));
            }
        } else {
            Context context2 = this.f;
            nrh.d(context2, context2.getString(R.string._2130841392_res_0x7f020f30));
        }
        t();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void cQg_(Message message) {
        CommonDialog21 commonDialog21 = this.ao;
        if (commonDialog21 != null) {
            commonDialog21.dismiss();
            this.ao = null;
        }
        StopDeviceLinkageResponse stopDeviceLinkageResponse = message.obj instanceof StopDeviceLinkageResponse ? (StopDeviceLinkageResponse) message.obj : null;
        if (stopDeviceLinkageResponse != null) {
            if (stopDeviceLinkageResponse.getResultCode().intValue() == 0) {
                boolean z = !this.ak;
                this.an = z;
                this.bm.setChecked(z);
                DeviceInfo deviceInfo = this.i;
                if (deviceInfo != null) {
                    this.z.setSwitchSetting("intelligent_home_linkage", null, deviceInfo.getDeviceIdentify(), null);
                }
                LogUtil.a("IntelligentHomeLinkageActivity", "stopDeviceLinkage is success stopDeviceLinkageRsq ", stopDeviceLinkageResponse);
            } else {
                LogUtil.a("IntelligentHomeLinkageActivity", "stopDeviceLinkage is fail stopDeviceLinkageRsq resultCode ", stopDeviceLinkageResponse.getResultCode(), " resultDesc ", stopDeviceLinkageResponse.getResultDesc());
                Context context = this.f;
                nrh.d(context, context.getString(R.string._2130842544_res_0x7f0213b0));
            }
        } else {
            LogUtil.a("IntelligentHomeLinkageActivity", "stopDeviceLinkage is fail");
            Context context2 = this.f;
            nrh.d(context2, context2.getString(R.string._2130841392_res_0x7f020f30));
        }
        t();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void cQf_(Message message) {
        LogUtil.a("IntelligentHomeLinkageActivity", "startDeviceLinkage is devId ", this.k, " proId ", this.ay);
        LogUtil.a("IntelligentHomeLinkageActivity", "startDeviceLinkage is msg.agr1 ", Integer.valueOf(message.arg1));
        StartDeviceLinkageResponse startDeviceLinkageResponse = (StartDeviceLinkageResponse) message.obj;
        int i = message.arg1;
        if (startDeviceLinkageResponse != null) {
            if (startDeviceLinkageResponse.getResultCode().intValue() == 0) {
                e(i, startDeviceLinkageResponse.getDevId(), startDeviceLinkageResponse.getDevice_prodId(), String.valueOf(startDeviceLinkageResponse.getExpiresIn()));
                LogUtil.a("IntelligentHomeLinkageActivity", "startDeviceLinkage is success startDeviceLinkageRsq ", startDeviceLinkageResponse);
                return;
            } else {
                if (!TextUtils.isEmpty(this.k)) {
                    e(i, this.k, this.ay, this.s);
                }
                LogUtil.a("IntelligentHomeLinkageActivity", "startDeviceLinkage is fail startDeviceLinkageRsq resultCode ", startDeviceLinkageResponse.getResultCode(), "resultDesc ", startDeviceLinkageResponse.getResultDesc());
                return;
            }
        }
        if (TextUtils.isEmpty(this.k)) {
            return;
        }
        e(i, this.k, this.ay, this.s);
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.f = this;
        setContentView(R.layout.activity_intelligent_home_linkage);
        this.y = jgh.d(this.f);
        this.z = jqi.a();
        this.o = DeviceSettingsInteractors.d(this.f);
        this.f9106a = oal.d(null);
        Intent intent = getIntent();
        if (intent != null) {
            this.j = intent.getStringExtra("device_id");
        }
        if (cvs.e(this.j) != null) {
            boolean isSupportChangeAlarm = cvs.e(this.j).isSupportChangeAlarm();
            this.aj = isSupportChangeAlarm;
            LogUtil.a("IntelligentHomeLinkageActivity", "mIsSupportChangeAlarm ", Boolean.valueOf(isSupportChangeAlarm));
        }
        this.p = jgy.e(BaseApplication.getContext());
        g();
        i();
        n();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void aa() {
        if (this.ba == 1) {
            this.bz.setVisibility(0);
            this.bz.setText(this.f9106a.b(this.bd));
            String b2 = oal.b(this.f, (this.be * 100) + this.bk);
            e = b2;
            this.bj.setText(b2);
            if (this.an) {
                this.bx.setClickable(true);
                this.bx.setAlpha(1.0f);
                return;
            } else {
                this.bx.setClickable(false);
                this.bx.setAlpha(0.5f);
                return;
            }
        }
        this.bj.setText(getString(R.string._2130841535_res_0x7f020fbf));
        this.bz.setVisibility(8);
        this.bx.setClickable(false);
        this.bx.setAlpha(0.5f);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void t() {
        if (this.an) {
            this.x.setClickable(true);
            this.x.setAlpha(1.0f);
            this.bg.setClickable(true);
            this.bg.setAlpha(1.0f);
            if (this.bl == 1) {
                this.bx.setClickable(true);
                this.bx.setAlpha(1.0f);
                return;
            } else {
                this.bx.setClickable(false);
                this.bx.setAlpha(0.5f);
                return;
            }
        }
        this.x.setClickable(false);
        this.x.setAlpha(0.5f);
        this.bg.setClickable(false);
        this.bg.setAlpha(0.5f);
        this.bx.setClickable(false);
        this.bx.setAlpha(0.5f);
    }

    private void i() {
        this.aa = getString(R.string.IDS_device_intelligent_home);
        this.ah = getString(R.string._2130843458_res_0x7f021742);
        this.ab = String.format(Locale.ENGLISH, getString(R.string.IDS_device_to_intelligent_home_linkage_under), this.aa);
        this.ar = String.format(Locale.ENGLISH, getString(R.string.IDS_device_to_intelligent_home_linkage_market_downloads), this.aa);
        this.w = String.format(Locale.ENGLISH, getString(R.string.IDS_device_to_intelligent_home_linkage_monitoring_after_sleep_new), this.ah, getString(R.string.IDS_device_to_intelligent_home_linkage_go_sleeping), 20);
        String format = String.format(Locale.ENGLISH, getString(R.string.IDS_device_to_intelligent_home_linkage_morning_after_wake_up_new), this.ah, getString(R.string._2130841515_res_0x7f020fab));
        this.bw = format;
        this.br = new String[]{this.w, format};
    }

    private void n() {
        HealthScrollView healthScrollView = (HealthScrollView) nsy.cMc_(this, R.id.sv_device_setting);
        this.l = healthScrollView;
        healthScrollView.setScrollViewVerticalDirectionEvent(true);
        this.l.setLayerType(2, null);
        this.am = (RelativeLayout) nsy.cMc_(this, R.id.rl_intelligent_home_linkage_unintall);
        this.bn = (HealthTextView) nsy.cMc_(this, R.id.switch_intelligent_home_title);
        this.bq = (HealthTextView) nsy.cMc_(this, R.id.switch_intelligent_home_describe);
        this.ag = (RelativeLayout) nsy.cMc_(this, R.id.rl_intelligent_home_linkage_unintent);
        this.bo = (RelativeLayout) nsy.cMc_(this, R.id.rl_switch_intelligent_home);
        this.bi = (HealthTextView) nsy.cMc_(this, R.id.tv_switch_intelligent_button);
        this.bp = (RelativeLayout) nsy.cMc_(this, R.id.rl_switch_intfelligent_button);
        this.cb = (HealthTextView) nsy.cMc_(this, R.id.tv_wake_up);
        this.av = (LinearLayout) nsy.cMc_(this, R.id.ll_unintent);
        this.ap = (HealthTextView) nsy.cMc_(this, R.id.tv_market_downloads);
        this.r = (ImageView) nsy.cMc_(this, R.id.img_download);
        this.bn.setText(this.ah);
        this.bq.setText(this.ab);
        this.ap.setText(this.ar);
        this.bu = (HealthTextView) nsy.cMc_(this, R.id.tv_to_intelligent_home);
        this.at = (LinearLayout) nsy.cMc_(this, R.id.ll_not_downloaded);
        h();
    }

    private void h() {
        this.ae = (LinearLayout) nsy.cMc_(this, R.id.device_intelligent_home_layout);
        this.bt = (HealthViewPager) nsy.cMc_(this, R.id.device_intelligent_home_linkage_img);
        LinearLayout linearLayout = (LinearLayout) nsy.cMc_(this, R.id.device_intelligent_home_linkage_img_layout);
        this.bv = linearLayout;
        nsn.cLA_(linearLayout, 2);
        this.t = (HealthDotsPageIndicator) nsy.cMc_(this, R.id.indicator);
        this.bm = (HealthSwitchButton) nsy.cMc_(this, R.id.switch_intelligent_button);
        this.n = (HealthTextView) nsy.cMc_(this, R.id.device_intelligent_after_sleep);
        this.m = (HealthTextView) nsy.cMc_(this, R.id.device_intelligent_monitoring_after_sleep);
        this.ai = (CustomTitleBar) nsy.cMc_(this, R.id.intelligent_home_title_bar);
        this.u = (HealthTextView) nsy.cMc_(this, R.id.tv_go_sleeping_minutes);
        this.bj = (HealthTextView) nsy.cMc_(this, R.id.tv_smart_alarm);
        this.bg = (RelativeLayout) nsy.cMc_(this, R.id.rl_smart_alarm);
        this.x = (RelativeLayout) nsy.cMc_(this, R.id.rl_go_sleeping);
        this.bx = (RelativeLayout) nsy.cMc_(this, R.id.rl_wake_up);
        this.bz = (HealthTextView) nsy.cMc_(this, R.id.smart_alarm_day);
        this.al = (ImageView) nsy.cMc_(this, R.id.iv_go_sleep);
        this.as = (ImageView) nsy.cMc_(this, R.id.iv_smart_alarm);
        this.aq = (ImageView) nsy.cMc_(this, R.id.iv_wake_up);
        this.aw = (ImageView) nsy.cMc_(this, R.id.iv_unintent);
        this.bb = (HealthTextView) nsy.cMc_(this, R.id.tv_to_intelligent_home_outsleep);
        this.by = (HealthDivider) nsy.cMc_(this, R.id.wake_up_divider);
        q();
        r();
        l();
    }

    private void l() {
        this.u.setText(String.format(Locale.ENGLISH, getString(R.string.IDS_device_to_intelligent_home_linkage_go_sleeping_minutes), 20));
        this.cb.setText(String.format(Locale.ENGLISH, getString(R.string.IDS_device_to_intelligent_home_linkage_reminder_enabled), getString(R.string._2130841515_res_0x7f020fab)));
        this.ai.setTitleText(this.ah);
        if (Utils.o()) {
            this.bu.setVisibility(8);
        } else {
            this.bu.setVisibility(0);
        }
        t();
        b();
        c();
        j();
        f();
    }

    private void q() {
        if (!k()) {
            this.bb.setVisibility(8);
            this.bg.setVisibility(8);
            this.bx.setVisibility(8);
            this.ai.setRightButtonVisibility(8);
            this.by.setVisibility(8);
            this.bs = new int[]{R.string.IDS_device_to_intelligent_home_linkage_after_sleep};
            this.br = new String[]{this.w};
            this.ac = new int[]{R.mipmap._2131820854_res_0x7f110136};
            return;
        }
        LogUtil.a("IntelligentHomeLinkageActivity", "setNotSupportAlarmCapabilityView is support alarm.");
    }

    private void r() {
        if (LanguageUtil.bc(this.f)) {
            this.al.setImageResource(R.drawable._2131427841_res_0x7f0b0201);
            this.as.setImageResource(R.drawable._2131427841_res_0x7f0b0201);
            this.aq.setImageResource(R.drawable._2131427841_res_0x7f0b0201);
            this.aw.setImageResource(R.drawable._2131427841_res_0x7f0b0201);
            this.r.setImageResource(R.drawable._2131427841_res_0x7f0b0201);
            return;
        }
        this.al.setImageResource(R.drawable._2131427842_res_0x7f0b0202);
        this.as.setImageResource(R.drawable._2131427842_res_0x7f0b0202);
        this.aq.setImageResource(R.drawable._2131427842_res_0x7f0b0202);
        this.aw.setImageResource(R.drawable._2131427842_res_0x7f0b0202);
        this.r.setImageResource(R.drawable._2131427842_res_0x7f0b0202);
    }

    private void c() {
        if (this.aj) {
            synchronized (b) {
                List<SmartAlarmInfo> list = this.bh;
                if (list == null || list.size() == 0) {
                    SmartAlarmInfo smartAlarmInfo = new SmartAlarmInfo();
                    ArrayList arrayList = new ArrayList(16);
                    this.bh = arrayList;
                    arrayList.add(smartAlarmInfo);
                }
                if (this.bh.size() != 0) {
                    this.bf = this.bh.get(0);
                    y();
                } else {
                    LogUtil.h("IntelligentHomeLinkageActivity", "getSmartAlarm mSmartAlarmLists is empty");
                }
            }
            s();
            return;
        }
        p();
    }

    private void s() {
        this.o.a(this.j, new IBaseResponseCallback() { // from class: nvs
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public final void d(int i, Object obj) {
                IntelligentHomeLinkageActivity.this.e(i, obj);
            }
        });
    }

    public /* synthetic */ void e(int i, Object obj) {
        LogUtil.a("IntelligentHomeLinkageActivity", "proceessSupportChange errorCode = ", Integer.valueOf(i));
        synchronized (b) {
            List<SmartAlarmInfo> a2 = this.o.a(obj);
            this.bh = a2;
            this.o.c(a2);
            List<SmartAlarmInfo> list = this.bh;
            if (list == null || list.size() == 0) {
                SmartAlarmInfo smartAlarmInfo = new SmartAlarmInfo();
                ArrayList arrayList = new ArrayList(16);
                this.bh = arrayList;
                arrayList.add(smartAlarmInfo);
            }
            LogUtil.a("IntelligentHomeLinkageActivity", "proceessSupportChange mSmartAlarmLists size = ", Integer.valueOf(this.bh.size()));
            if (this.bh.size() != 0) {
                this.bf = this.bh.get(0);
                y();
            } else {
                LogUtil.h("IntelligentHomeLinkageActivity", "proceessSupportChange mSmartAlarmInfo is empty");
            }
        }
    }

    private void p() {
        this.y.a(new IBaseResponseCallback() { // from class: nvv
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public final void d(int i, Object obj) {
                IntelligentHomeLinkageActivity.this.c(i, obj);
            }
        });
    }

    public /* synthetic */ void c(int i, Object obj) {
        synchronized (b) {
            if (obj instanceof List) {
                this.bh = (List) obj;
            }
            List<SmartAlarmInfo> list = this.bh;
            if (list == null || list.size() == 0) {
                SmartAlarmInfo smartAlarmInfo = new SmartAlarmInfo();
                ArrayList arrayList = new ArrayList(16);
                this.bh = arrayList;
                arrayList.add(smartAlarmInfo);
            }
            LogUtil.a("IntelligentHomeLinkageActivity", "processUnSupportChange mSmartAlarmLists size = ", Integer.valueOf(this.bh.size()));
            if (this.bh.size() != 0) {
                this.bf = this.bh.get(0);
                y();
            } else {
                LogUtil.h("IntelligentHomeLinkageActivity", "processUnSupportChange mSmartAlarmLists is empty");
            }
        }
    }

    private void y() {
        this.ba = this.bf.getSmartAlarmEnable();
        this.be = this.bf.getSmartAlarmStartTimeHour();
        this.bk = this.bf.getSmartAlarmStartTimeMins();
        this.bd = this.bf.getSmartAlarmRepeat();
        this.az = this.bf.getSmartAlarmAheadTime();
        if (this.bd == 0 && this.ba == 1) {
            this.ba = e(this.bf);
        }
        int i = this.be;
        LogUtil.a("IntelligentHomeLinkageActivity", "mSmartAlarmEnable =", Integer.valueOf(this.ba), "smartAlarmStartTime = ", Integer.valueOf((i * 100) + this.bk), "mSmartAlarmRepeat =", Integer.valueOf(this.bd), "mSmartAlarmAheadTime =", Integer.valueOf(this.az));
        Message obtain = Message.obtain();
        obtain.what = 1;
        obtain.obj = Integer.valueOf(this.ba);
        this.v.sendMessage(obtain);
    }

    private int e(SmartAlarmInfo smartAlarmInfo) {
        int smartAlarmEnable = smartAlarmInfo.getSmartAlarmEnable();
        String b2 = SharedPreferenceManager.b(this.f, String.valueOf(10022), "ONCE_SMART_ALARM_INFO");
        LogUtil.a("IntelligentHomeLinkageActivity", " onceSmartAlarmIsOver json = ", b2);
        if (TextUtils.isEmpty(b2)) {
            LogUtil.h("IntelligentHomeLinkageActivity", " onceSmartAlarmIsOver json is null");
            return smartAlarmEnable;
        }
        List list = (List) new Gson().fromJson(b2, new TypeToken<List<SmartAlarmInfo>>() { // from class: com.huawei.ui.device.activity.intelligenthome.IntelligentHomeLinkageActivity.4
        }.getType());
        if (list == null || list.size() == 0) {
            LogUtil.h("IntelligentHomeLinkageActivity", " onceSmartAlarmIsOver smartAlarmInfoList is null or list is empty");
            return smartAlarmEnable;
        }
        for (int i = 0; i < list.size(); i++) {
            SmartAlarmInfo smartAlarmInfo2 = (SmartAlarmInfo) list.get(i);
            if (smartAlarmInfo2.getSmartAlarmIndex() == smartAlarmInfo.getSmartAlarmIndex()) {
                long currentTimeMillis = System.currentTimeMillis() / 1000;
                LogUtil.a("IntelligentHomeLinkageActivity", "onceSmartAlarmIsOver currentTime ", Long.valueOf(currentTimeMillis));
                if (currentTimeMillis >= smartAlarmInfo2.getSmartAlarmTime()) {
                    smartAlarmEnable = 0;
                }
            }
        }
        LogUtil.a("IntelligentHomeLinkageActivity", "onceSmartAlarmIsOver reset = ", Integer.valueOf(smartAlarmEnable));
        return smartAlarmEnable;
    }

    private void b() {
        DeviceInfo a2 = jpt.a("IntelligentHomeLinkageActivity");
        this.i = a2;
        if (a2 != null) {
            this.z.getSwitchSetting("intelligent_home_linkage", a2.getDeviceIdentify(), new IBaseResponseCallback() { // from class: nvw
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public final void d(int i, Object obj) {
                    IntelligentHomeLinkageActivity.this.d(i, obj);
                }
            });
        }
    }

    public /* synthetic */ void d(int i, Object obj) {
        boolean b2 = b(i, obj);
        LogUtil.a("IntelligentHomeLinkageActivity", "getLinkageStatus isEnableStatus = ", Boolean.valueOf(b2));
        Message obtain = Message.obtain();
        obtain.what = 2;
        obtain.obj = Boolean.valueOf(b2);
        this.v.sendMessage(obtain);
    }

    private boolean b(int i, Object obj) {
        if (i != 0 || obj == null) {
            LogUtil.h("IntelligentHomeLinkageActivity", "isEnableStatus errorCode is not success or objectData is null");
            return false;
        }
        String str = obj instanceof String ? (String) obj : null;
        if (str == null) {
            LogUtil.h("IntelligentHomeLinkageActivity", "isEnableStatus info is null");
            return false;
        }
        if (str.contains("&&")) {
            String[] split = str.split("&&");
            LogUtil.a("IntelligentHomeLinkageActivity", "isEnableStatus length = ", Integer.valueOf(split.length));
            if (split.length == 5) {
                return Boolean.parseBoolean(split[3]);
            }
        }
        return false;
    }

    private void j() {
        this.ai.setLeftButtonOnClickListener(new View.OnClickListener() { // from class: nvk
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                IntelligentHomeLinkageActivity.this.cQi_(view);
            }
        });
        Drawable drawable = getResources().getDrawable(R.mipmap._2131821036_res_0x7f1101ec);
        if (drawable != null) {
            this.ai.setRightButtonDrawable(drawable, nsf.h(R.string._2130847331_res_0x7f022663));
        }
        this.ai.setRightButtonOnClickListener(new View.OnClickListener() { // from class: nvq
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                IntelligentHomeLinkageActivity.this.cQj_(view);
            }
        });
        this.av.setOnClickListener(new View.OnClickListener() { // from class: nvr
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                IntelligentHomeLinkageActivity.this.cQk_(view);
            }
        });
        this.bi.setOnClickListener(new View.OnClickListener() { // from class: nvt
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                IntelligentHomeLinkageActivity.this.cQl_(view);
            }
        });
        this.bp.setOnClickListener(new View.OnClickListener() { // from class: nvp
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                IntelligentHomeLinkageActivity.this.cQm_(view);
            }
        });
        m();
        e();
        ah();
    }

    public /* synthetic */ void cQi_(View view) {
        d();
        ViewClickInstrumentation.clickOnView(view);
    }

    public /* synthetic */ void cQj_(View view) {
        if (TextUtils.isEmpty(this.d)) {
            LogUtil.h("IntelligentHomeLinkageActivity", "mIntelligentHomeTitleBar Click mBaseResourceUrl is empty");
            ViewClickInstrumentation.clickOnView(view);
            return;
        }
        String str = this.d + "/Sleep/EMUI8.0/C001B001/zh-CN/index.html";
        Intent intent = new Intent(this.f, (Class<?>) WebViewActivity.class);
        intent.putExtra("url", str);
        intent.putExtra(Constants.JUMP_MODE_KEY, 0);
        startActivity(intent);
        ViewClickInstrumentation.clickOnView(view);
    }

    public /* synthetic */ void cQk_(View view) {
        CommonUtil.q(this.f);
        ViewClickInstrumentation.clickOnView(view);
    }

    public /* synthetic */ void cQl_(View view) {
        ac();
        ViewClickInstrumentation.clickOnView(view);
    }

    public /* synthetic */ void cQm_(View view) {
        ac();
        ViewClickInstrumentation.clickOnView(view);
    }

    private void ac() {
        synchronized (this) {
            boolean isChecked = this.bm.isChecked();
            LogUtil.a("IntelligentHomeLinkageActivity", "switch button isChecked = ", Boolean.valueOf(isChecked));
            this.ak = isChecked;
            if (!isChecked) {
                ad();
            } else {
                u();
            }
        }
    }

    private void e() {
        this.x.setOnClickListener(new View.OnClickListener() { // from class: nwc
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                IntelligentHomeLinkageActivity.this.cQh_(view);
            }
        });
    }

    public /* synthetic */ void cQh_(View view) {
        if (!CommonUtil.e(this.f, "com.huawei.smarthome")) {
            v();
        } else if (d(this.f, "com.huawei.smarthome") >= 1900072000) {
            c(0);
            d(AnalyticsValue.SETTING_1090016.value(), true, false, false);
        } else {
            x();
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    private void ah() {
        this.bg.setOnClickListener(new View.OnClickListener() { // from class: nwb
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                IntelligentHomeLinkageActivity.this.cQz_(view);
            }
        });
        this.bx.setOnClickListener(new View.OnClickListener() { // from class: nwa
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                IntelligentHomeLinkageActivity.this.cQy_(view);
            }
        });
    }

    public /* synthetic */ void cQz_(View view) {
        try {
            Intent intent = new Intent();
            intent.setClass(this.f, AlarmActivity.class);
            intent.putExtra("device_id", this.j);
            this.f.startActivity(intent);
        } catch (ActivityNotFoundException e2) {
            LogUtil.b("IntelligentHomeLinkageActivity", "wakeUpClick e:", ExceptionUtils.d(e2));
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    public /* synthetic */ void cQy_(View view) {
        if (!CommonUtil.e(this.f, "com.huawei.smarthome")) {
            v();
        } else if (d(this.f, "com.huawei.smarthome") >= 1900072000) {
            c(1);
            StorageParams storageParams = new StorageParams();
            SharedPreferenceManager.e(this.f, "intell_wake_up", "once_intell_wake_up" + this.i.getDeviceIdentify(), "go_wake_up", storageParams);
            d(AnalyticsValue.SETTING_1090017.value(), true, false, false);
        } else {
            x();
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    private boolean k() {
        if (cvs.e(this.j) == null) {
            return false;
        }
        boolean isSmartAlarm = cvs.e(this.j).isSmartAlarm();
        LogUtil.a("IntelligentHomeLinkageActivity", "isSupportAlarmCapability isSmartAlarm = ", Boolean.valueOf(isSmartAlarm));
        return isSmartAlarm;
    }

    private void d() {
        if (this.an && this.bl == 1) {
            if (this.i != null) {
                String b2 = SharedPreferenceManager.b(this.f, "intell_wake_up", "once_intell_wake_up" + this.i.getDeviceIdentify());
                LogUtil.a("IntelligentHomeLinkageActivity", "setLeftButtonOnClickListener result = ", b2);
                if (!"go_wake_up".equals(b2) && k()) {
                    w();
                    return;
                } else {
                    finish();
                    return;
                }
            }
            finish();
            return;
        }
        finish();
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        LogUtil.a("IntelligentHomeLinkageActivity", "onBackPressed");
        d();
    }

    private void w() {
        NoTitleCustomAlertDialog e2 = new NoTitleCustomAlertDialog.Builder(this.f).e(String.format(Locale.ENGLISH, this.f.getString(R.string.IDS_device_to_intelligent_home_linkage_alarm_wake_up), this.f.getString(R.string.IDS_device_to_intelligent_home_linkage_wake_up), this.aa)).czz_(R.string._2130841130_res_0x7f020e2a, new View.OnClickListener() { // from class: nvm
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                IntelligentHomeLinkageActivity.this.cQo_(view);
            }
        }).czC_(R.string._2130842357_res_0x7f0212f5, new View.OnClickListener() { // from class: nvo
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                IntelligentHomeLinkageActivity.this.cQp_(view);
            }
        }).e();
        this.c = e2;
        e2.setCancelable(false);
        this.c.show();
    }

    public /* synthetic */ void cQo_(View view) {
        LogUtil.a("IntelligentHomeLinkageActivity", "showDownloadNewAppDialog cancel click");
        this.c.dismiss();
        this.c = null;
        finish();
        ViewClickInstrumentation.clickOnView(view);
    }

    public /* synthetic */ void cQp_(View view) {
        LogUtil.a("IntelligentHomeLinkageActivity", "showDownloadNewAppDialog ok click");
        this.c.dismiss();
        this.c = null;
        if (!CommonUtil.e(this.f, "com.huawei.smarthome")) {
            v();
        } else if (d(this.f, "com.huawei.smarthome") >= 1900072000) {
            c(1);
            StorageParams storageParams = new StorageParams();
            SharedPreferenceManager.e(this.f, "intell_wake_up", "once_intell_wake_up" + this.i.getDeviceIdentify(), "go_wake_up", storageParams);
            d(AnalyticsValue.SETTING_1090017.value(), true, false, false);
        } else {
            x();
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    private void c(int i) {
        d(this.f.getString(R.string._2130841415_res_0x7f020f47));
        Message obtainMessage = this.v.obtainMessage();
        obtainMessage.what = 6;
        this.v.sendMessageDelayed(obtainMessage, OpAnalyticsConstants.H5_LOADING_DELAY);
        if (this.i != null) {
            a(i);
        }
    }

    private void a(final int i) {
        this.p.d(new FitnessCloudCallBack() { // from class: nvx
            @Override // com.huawei.callback.FitnessCloudCallBack
            public final void onResponce(Object obj) {
                IntelligentHomeLinkageActivity.this.a(i, obj);
            }
        });
    }

    public /* synthetic */ void a(int i, Object obj) {
        Message obtain = Message.obtain();
        obtain.obj = obj;
        obtain.arg1 = i;
        obtain.what = 5;
        this.v.sendMessage(obtain);
    }

    private void e(int i, String str, String str2, String str3) {
        Handler handler = this.v;
        if (handler != null) {
            handler.removeMessages(6);
        }
        StringBuffer stringBuffer = new StringBuffer(16);
        stringBuffer.append(str).append("&&").append(str3).append("&&").append(str2).append("&&").append(this.an).append("&&true");
        this.z.setSwitchSetting("intelligent_home_linkage", stringBuffer.toString(), this.i.getDeviceIdentify(), null);
        LogUtil.a("IntelligentHomeLinkageActivity", "go to sleep intelligent home");
        String accountInfo = LoginInit.getInstance(this.f).getAccountInfo(1011);
        LogUtil.a("IntelligentHomeLinkageActivity", " goIntelligentHome huid = ", accountInfo);
        String str4 = i == 0 ? "gotosleep" : "wakeup";
        CommonDialog21 commonDialog21 = this.ao;
        if (commonDialog21 != null) {
            commonDialog21.dismiss();
            this.ao = null;
        }
        String str5 = "hilink://smarthome.huawei.com?type=iftttrule&action=hwsmartwear&para=userId&userId=" + accountInfo + "&para=devId&devId=" + str + "&para=prodId&prodId=" + str2 + "&para=condId&condId=" + str4;
        LogUtil.a("IntelligentHomeLinkageActivity", " goToSleepIntelligentHome sction ", str5);
        c(str5);
    }

    private void v() {
        NoTitleCustomAlertDialog e2 = new NoTitleCustomAlertDialog.Builder(this.f).e(this.ar).czz_(R.string._2130841130_res_0x7f020e2a, new View.OnClickListener() { // from class: nvl
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                IntelligentHomeLinkageActivity.this.cQs_(view);
            }
        }).czC_(R.string._2130841733_res_0x7f021085, new View.OnClickListener() { // from class: nvu
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                IntelligentHomeLinkageActivity.this.cQt_(view);
            }
        }).e();
        this.q = e2;
        e2.setCancelable(false);
        this.q.show();
    }

    public /* synthetic */ void cQs_(View view) {
        LogUtil.a("IntelligentHomeLinkageActivity", "showDownloadIntelligentDialog cancel click");
        NoTitleCustomAlertDialog noTitleCustomAlertDialog = this.q;
        if (noTitleCustomAlertDialog != null) {
            noTitleCustomAlertDialog.dismiss();
            this.q = null;
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    public /* synthetic */ void cQt_(View view) {
        LogUtil.a("IntelligentHomeLinkageActivity", "showDownloadIntelligentDialog ok click");
        NoTitleCustomAlertDialog noTitleCustomAlertDialog = this.q;
        if (noTitleCustomAlertDialog != null) {
            noTitleCustomAlertDialog.dismiss();
            this.q = null;
        }
        b(false);
        ViewClickInstrumentation.clickOnView(view);
    }

    private int d(Context context, String str) {
        try {
            return context.getPackageManager().getPackageInfo(str, 0).versionCode;
        } catch (PackageManager.NameNotFoundException unused) {
            return -1;
        }
    }

    private void x() {
        CustomTextAlertDialog a2 = new CustomTextAlertDialog.Builder(this.f).b(R.string._2130843263_res_0x7f02167f).e(String.format(Locale.ENGLISH, this.f.getString(R.string.IDS_device_to_intelligent_home_linkage_update_retry), this.aa)).cyU_(R.string.IDS_device_to_intelligent_home_linkage_go_to_download, new View.OnClickListener() { // from class: nvz
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                IntelligentHomeLinkageActivity.this.cQu_(view);
            }
        }).cyR_(R.string._2130841130_res_0x7f020e2a, new View.OnClickListener() { // from class: nwd
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                IntelligentHomeLinkageActivity.this.cQv_(view);
            }
        }).a();
        this.au = a2;
        a2.setCancelable(false);
        this.au.show();
    }

    public /* synthetic */ void cQu_(View view) {
        LogUtil.a("IntelligentHomeLinkageActivity", "showDownloadNewAppDialog ok click");
        this.au.dismiss();
        this.au = null;
        b(true);
        ViewClickInstrumentation.clickOnView(view);
    }

    public /* synthetic */ void cQv_(View view) {
        LogUtil.a("IntelligentHomeLinkageActivity", "showDownloadNewAppDialog cancel click");
        this.au.dismiss();
        this.au = null;
        ViewClickInstrumentation.clickOnView(view);
    }

    private void ad() {
        String format = String.format(Locale.ENGLISH, this.f.getString(R.string.IDS_device_to_intelligent_home_linkage_open_after), this.f.getString(R.string.IDS_device_intelligent_home));
        if (!k()) {
            format = String.format(Locale.ENGLISH, this.f.getString(R.string._2130844858_res_0x7f021cba), this.f.getString(R.string.IDS_device_intelligent_home));
        }
        CustomTextAlertDialog a2 = new CustomTextAlertDialog.Builder(this.f).b(String.format(Locale.ENGLISH, this.f.getString(R.string.IDS_device_to_intelligent_home_linkage_open), this.ah)).e(format).cyU_(R.string._2130842176_res_0x7f021240, new View.OnClickListener() { // from class: nwe
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                IntelligentHomeLinkageActivity.this.cQw_(view);
            }
        }).cyR_(R.string._2130841130_res_0x7f020e2a, new View.OnClickListener() { // from class: nvn
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                IntelligentHomeLinkageActivity.this.cQx_(view);
            }
        }).a();
        this.bc = a2;
        a2.setCancelable(false);
        this.bc.show();
    }

    public /* synthetic */ void cQw_(View view) {
        LogUtil.a("IntelligentHomeLinkageActivity", "showOpenIntelligentHomeDialog ok click");
        CustomTextAlertDialog customTextAlertDialog = this.bc;
        if (customTextAlertDialog != null) {
            customTextAlertDialog.dismiss();
            this.bc = null;
            d(this.f.getString(R.string.IDS_device_to_intelligent_home_linkage_is_opening));
            Message obtainMessage = this.v.obtainMessage();
            obtainMessage.what = 6;
            this.v.sendMessageDelayed(obtainMessage, OpAnalyticsConstants.H5_LOADING_DELAY);
            z();
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    public /* synthetic */ void cQx_(View view) {
        LogUtil.a("IntelligentHomeLinkageActivity", "showOpenIntelligentHomeDialog cancel click");
        CustomTextAlertDialog customTextAlertDialog = this.bc;
        if (customTextAlertDialog != null) {
            customTextAlertDialog.dismiss();
            this.bc = null;
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    private void z() {
        this.p.d(new FitnessCloudCallBack() { // from class: nwf
            @Override // com.huawei.callback.FitnessCloudCallBack
            public final void onResponce(Object obj) {
                IntelligentHomeLinkageActivity.this.e(obj);
            }
        });
    }

    public /* synthetic */ void e(Object obj) {
        Message obtain = Message.obtain();
        obtain.what = 3;
        obtain.obj = obj;
        this.v.sendMessage(obtain);
    }

    private void u() {
        String format = String.format(Locale.ENGLISH, this.f.getString(R.string.IDS_device_to_intelligent_home_linkage_close_after), this.f.getString(R.string.IDS_device_intelligent_home));
        if (!k()) {
            format = String.format(Locale.ENGLISH, this.f.getString(R.string._2130844859_res_0x7f021cbb), this.f.getString(R.string.IDS_device_intelligent_home));
        }
        CustomTextAlertDialog a2 = new CustomTextAlertDialog.Builder(this.f).b(String.format(Locale.ENGLISH, this.f.getString(R.string.IDS_device_to_intelligent_home_linkage_close), this.ah)).e(format).cyU_(R.string._2130841132_res_0x7f020e2c, new View.OnClickListener() { // from class: nwg
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                IntelligentHomeLinkageActivity.this.cQq_(view);
            }
        }).cyR_(R.string._2130841130_res_0x7f020e2a, new View.OnClickListener() { // from class: nwi
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                IntelligentHomeLinkageActivity.this.cQr_(view);
            }
        }).a();
        this.h = a2;
        a2.setCancelable(false);
        this.h.show();
    }

    public /* synthetic */ void cQq_(View view) {
        LogUtil.a("IntelligentHomeLinkageActivity", "showCloseIntelligentHomeDialog ok click");
        CustomTextAlertDialog customTextAlertDialog = this.h;
        if (customTextAlertDialog != null) {
            customTextAlertDialog.dismiss();
            this.h = null;
            d(this.f.getString(R.string.IDS_device_to_intelligent_home_linkage_is_closing));
            ab();
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    public /* synthetic */ void cQr_(View view) {
        LogUtil.a("IntelligentHomeLinkageActivity", "showCloseIntelligentHomeDialog cancel click");
        CustomTextAlertDialog customTextAlertDialog = this.h;
        if (customTextAlertDialog != null) {
            customTextAlertDialog.dismiss();
            this.h = null;
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    private void ab() {
        this.p.a(new FitnessCloudCallBack() { // from class: nwh
            @Override // com.huawei.callback.FitnessCloudCallBack
            public final void onResponce(Object obj) {
                IntelligentHomeLinkageActivity.this.d(obj);
            }
        });
    }

    public /* synthetic */ void d(Object obj) {
        Message obtain = Message.obtain();
        obtain.obj = obj;
        obtain.what = 4;
        this.v.sendMessage(obtain);
    }

    private void d(String str) {
        LogUtil.a("IntelligentHomeLinkageActivity", "showLoadingDialog()");
        if (isFinishing()) {
            return;
        }
        CommonDialog21 commonDialog21 = this.ao;
        if (commonDialog21 == null) {
            new CommonDialog21(this.f, R.style.app_update_dialogActivity);
            CommonDialog21 a2 = CommonDialog21.a(this.f);
            this.ao = a2;
            a2.e(str);
            this.ao.setCancelable(false);
            this.ao.a();
            LogUtil.a("IntelligentHomeLinkageActivity", "mLoadingUserInformationDialog.show()");
            return;
        }
        commonDialog21.e(str);
        this.ao.a();
        LogUtil.a("IntelligentHomeLinkageActivity", "mLoadingUserInformationDialog.show()");
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        o();
        c();
        if (CommonUtil.aa(this)) {
            if (!CommonUtil.e(this, "com.huawei.smarthome")) {
                this.am.setVisibility(0);
            } else {
                this.am.setVisibility(8);
            }
            this.ag.setVisibility(8);
            this.bo.setAlpha(1.0f);
            this.bo.setClickable(true);
            this.bi.setClickable(true);
            this.bp.setClickable(true);
            t();
            return;
        }
        this.am.setVisibility(8);
        this.ag.setVisibility(0);
        this.bo.setAlpha(0.5f);
        this.x.setAlpha(0.5f);
        this.bo.setClickable(false);
        this.x.setClickable(false);
        this.bi.setClickable(false);
        this.bp.setClickable(false);
        this.bg.setClickable(false);
        this.bg.setAlpha(0.5f);
        this.bx.setClickable(false);
        this.bx.setAlpha(0.5f);
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        Handler handler = this.v;
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
        }
    }

    private void f() {
        this.ad = new ArrayList(16);
        d(this.ac);
    }

    private void d(int[] iArr) {
        for (int i : iArr) {
            ImageView imageView = new ImageView(this);
            imageView.setImageResource(i);
            this.ad.add(imageView);
        }
    }

    private void m() {
        if (CommonUtil.bf()) {
            LogUtil.a("IntelligentHomeLinkageActivity", "initToIntelligentHome isHonor");
            this.bu.setText(String.format(Locale.ENGLISH, getString(R.string._2130847834_res_0x7f02285a), this.aa));
            return;
        }
        Locale locale = Locale.ENGLISH;
        String string = getString(R.string.IDS_device_to_intelligent_home_linkage_details_and_purchase);
        String str = this.aa;
        String format = String.format(locale, string, str, str);
        int[] iArr = new int[1];
        try {
            if (format.lastIndexOf(this.aa) != -1) {
                iArr[0] = format.lastIndexOf(this.aa);
            }
        } catch (IndexOutOfBoundsException e2) {
            LogUtil.a("IntelligentHomeLinkageActivity", e2.getMessage());
        }
        SpannableString spannableString = new SpannableString(format);
        ClickableSpan clickableSpan = new ClickableSpan() { // from class: com.huawei.ui.device.activity.intelligenthome.IntelligentHomeLinkageActivity.5
            @Override // android.text.style.ClickableSpan
            public void onClick(View view) {
                IntelligentHomeLinkageActivity.this.a();
                ViewClickInstrumentation.clickOnView(view);
            }

            @Override // android.text.style.ClickableSpan, android.text.style.CharacterStyle
            public void updateDrawState(TextPaint textPaint) {
                super.updateDrawState(textPaint);
                textPaint.setColor(Color.parseColor(Constants.CHOOSE_TEXT_COLOR));
                textPaint.setUnderlineText(false);
            }
        };
        int i = iArr[0];
        spannableString.setSpan(clickableSpan, i, this.aa.length() + i, 33);
        this.bu.setText(spannableString);
        this.bu.setMovementMethod(LinkMovementMethod.getInstance());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a() {
        if (!CommonUtil.e(this.f, "com.huawei.smarthome")) {
            v();
            return;
        }
        if (d(this.f, "com.huawei.smarthome") >= 1900072000) {
            try {
                Intent intent = new Intent();
                intent.setAction(CommonConstant.ACTION.HWID_SCHEME_URL);
                Uri parse = Uri.parse("hilink://smarthome.huawei.com?type=vmall&action=hwsmartwear");
                LogUtil.a("IntelligentHomeLinkageActivity", "contentAUri:", parse);
                intent.setData(parse);
                cQd_(intent, this.aa);
            } catch (ActivityNotFoundException e2) {
                LogUtil.b("IntelligentHomeLinkageActivity", "checkSmartHomeStatus e:", ExceptionUtils.d(e2));
            }
            d(AnalyticsValue.SETTING_1090015.value(), true, false, false);
            return;
        }
        x();
    }

    private void c(String str) {
        Intent intent = new Intent();
        intent.setAction(CommonConstant.ACTION.HWID_SCHEME_URL);
        intent.setData(Uri.parse(str));
        intent.setFlags(268468224);
        cQd_(intent, this.aa);
    }

    private void o() {
        this.at.setOnClickListener(new View.OnClickListener() { // from class: nvy
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                IntelligentHomeLinkageActivity.this.cQn_(view);
            }
        });
        this.bt.setAdapter(new HealthPagerAdapter() { // from class: com.huawei.ui.device.activity.intelligenthome.IntelligentHomeLinkageActivity.8
            @Override // com.huawei.uikit.hwviewpager.widget.HwPagerAdapter
            public boolean isViewFromObject(View view, Object obj) {
                return view == obj;
            }

            @Override // com.huawei.uikit.hwviewpager.widget.HwPagerAdapter
            public int getCount() {
                return IntelligentHomeLinkageActivity.this.ad.size();
            }

            @Override // com.huawei.uikit.hwviewpager.widget.HwPagerAdapter
            public void destroyItem(ViewGroup viewGroup, int i, Object obj) {
                if (i < 0 || i >= IntelligentHomeLinkageActivity.this.ad.size()) {
                    return;
                }
                viewGroup.removeView((View) IntelligentHomeLinkageActivity.this.ad.get(i));
            }

            @Override // com.huawei.uikit.hwviewpager.widget.HwPagerAdapter
            public Object instantiateItem(ViewGroup viewGroup, int i) {
                if (i < 0 || i >= IntelligentHomeLinkageActivity.this.ad.size()) {
                    return null;
                }
                viewGroup.addView((View) IntelligentHomeLinkageActivity.this.ad.get(i));
                return IntelligentHomeLinkageActivity.this.ad.get(i);
            }
        });
        if (LanguageUtil.bc(this.f)) {
            int size = this.ad.size() - 1;
            this.bt.setCurrentItem(size);
            e(size);
        } else {
            this.bt.setCurrentItem(0);
            e(0);
        }
        HealthDotsPageIndicator healthDotsPageIndicator = this.t;
        if (healthDotsPageIndicator != null) {
            healthDotsPageIndicator.setRtlEnable(false);
            this.t.setViewPager(this.bt);
        }
        this.bt.setOnPageChangeListener(this.ax);
    }

    public /* synthetic */ void cQn_(View view) {
        b(false);
        ViewClickInstrumentation.clickOnView(view);
    }

    private void b(boolean z) {
        if (!CommonUtil.e(this.f, "com.huawei.appmarket")) {
            LogUtil.a("IntelligentHomeLinkageActivity", "is not install appmarket enter");
            try {
                if (TextUtils.isEmpty(this.g)) {
                    LogUtil.h("IntelligentHomeLinkageActivity", "downLoadIntelligentHome mBaseSmartHomeUrl is empty");
                    return;
                }
                String str = this.g + "/d/?v2";
                Intent intent = new Intent();
                intent.setAction(CommonConstant.ACTION.HWID_SCHEME_URL);
                intent.setData(Uri.parse(str));
                cQd_(intent, getString(R.string._2130847432_res_0x7f0226c8));
            } catch (ActivityNotFoundException e2) {
                LogUtil.b("IntelligentHomeLinkageActivity", "downLoadIntelligentHome intent app market e:", ExceptionUtils.d(e2));
            }
        } else {
            LogUtil.a("IntelligentHomeLinkageActivity", "is install appmarket enter");
            try {
                Intent intent2 = new Intent("com.huawei.appmarket.intent.action.AppDetail");
                intent2.setPackage("com.huawei.appmarket");
                intent2.addFlags(268468224);
                intent2.putExtra("APP_PACKAGENAME", "com.huawei.smarthome");
                if (z) {
                    nsn.cLM_(intent2, "com.huawei.appmarket", this, getString(R.string.IDS_device_fragment_application_market));
                } else {
                    startActivity(intent2);
                }
            } catch (ActivityNotFoundException e3) {
                LogUtil.b("IntelligentHomeLinkageActivity", "downLoadIntelligentHome intent browser e:", ExceptionUtils.d(e3));
            }
        }
        d(AnalyticsValue.SETTING_1090020.value(), false, false, false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(int i) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(nsf.h(R.string._2130847332_res_0x7f022664));
        HealthTextView healthTextView = this.n;
        if (healthTextView != null) {
            int[] iArr = this.bs;
            if (i < iArr.length) {
                healthTextView.setText(iArr[i]);
                stringBuffer.append(nsf.h(this.bs[i]));
            }
        }
        HealthTextView healthTextView2 = this.m;
        if (healthTextView2 != null) {
            String[] strArr = this.br;
            if (i < strArr.length) {
                healthTextView2.setText(strArr[i]);
                stringBuffer.append(this.br[i]);
            }
        }
        if (jcf.c()) {
            jcf.bEz_(this.ae, stringBuffer);
        }
    }

    private void cQd_(Intent intent, String str) {
        ResolveInfo resolveActivity;
        PackageManager packageManager = getPackageManager();
        if (packageManager == null || (resolveActivity = packageManager.resolveActivity(intent, 65536)) == null) {
            return;
        }
        ComponentName componentName = new ComponentName(resolveActivity.activityInfo.packageName, resolveActivity.activityInfo.name);
        intent.setComponent(componentName);
        nsn.cLM_(intent, componentName.getPackageName(), this, str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(String str, boolean z, boolean z2, boolean z3) {
        HashMap hashMap = new HashMap(16);
        if (z) {
            hashMap.put("click", "1");
        }
        if (z2) {
            hashMap.put("status", z3 ? "1" : "0");
        }
        ixx.d().d(BaseApplication.getContext(), str, hashMap, 0);
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
