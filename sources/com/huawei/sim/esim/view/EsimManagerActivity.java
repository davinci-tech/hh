package com.huawei.sim.esim.view;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Message;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.RelativeLayout;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import com.huawei.haf.handler.BaseHandler;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hms.support.hianalytics.HiAnalyticsConstant;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.utils.PermissionUtil;
import com.huawei.hwcommonmodel.utils.permission.PermissionsResultAction;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.indoorequip.datastruct.MachineControlPointResponse;
import com.huawei.multisimservice.model.MultiSimDeviceInfo;
import com.huawei.multisimservice.model.SimInfo;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.profile.profile.ProfileExtendConstants;
import com.huawei.sim.esim.qrcode.QrCodeActivity;
import com.huawei.sim.multisim.activity.EsimSelectCardActivity;
import com.huawei.sim.multisim.adapter.EsimFaqAdapter;
import com.huawei.ui.commonui.button.HealthButton;
import com.huawei.ui.commonui.dialog.CommonDialog21;
import com.huawei.ui.commonui.dialog.CustomTextAlertDialog;
import com.huawei.ui.commonui.dialog.NoTitleCustomAlertDialog;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.listviewforscroll.ListViewForScroll;
import com.huawei.ui.commonui.popupview.PopViewList;
import com.huawei.ui.commonui.subheader.HealthSubHeader;
import com.huawei.ui.commonui.titlebar.CustomTitleBar;
import com.huawei.ui.commonui.utils.CustomPermissionAction;
import defpackage.cun;
import defpackage.cwi;
import defpackage.jdi;
import defpackage.jpt;
import defpackage.koq;
import defpackage.ktx;
import defpackage.nbq;
import defpackage.nca;
import defpackage.ncb;
import defpackage.ncc;
import defpackage.ncd;
import defpackage.nce;
import defpackage.ncf;
import defpackage.nch;
import defpackage.nrh;
import defpackage.nrs;
import defpackage.nrt;
import defpackage.nsf;
import defpackage.nsn;
import defpackage.sqo;
import health.compact.a.CommonUtil;
import health.compact.a.GRSManager;
import health.compact.a.LanguageUtil;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.UnitUtil;
import health.compact.a.Utils;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/* loaded from: classes6.dex */
public class EsimManagerActivity extends BaseOpenSimCardActivity implements View.OnClickListener {
    private HealthTextView aa;
    private ImageView ac;
    private int ad;
    private RelativeLayout ae;
    private ImageView af;
    private CommonDialog21 ai;
    private View aj;
    private HealthButton ak;
    private MultiSimDeviceInfo al;
    private HealthButton an;
    private HealthButton ao;
    private String ap;
    private HealthButton aq;
    private LinearLayout ar;
    private HealthButton as;
    private HealthTextView at;
    private LinearLayout au;
    private CustomTitleBar av;
    private HealthTextView aw;
    private HealthTextView ax;
    private RelativeLayout ay;
    private HealthTextView bb;
    private LinearLayout k;
    private HealthButton m;
    private LinearLayout n;
    private int o;
    private HealthTextView p;
    private NoTitleCustomAlertDialog q;
    private ImageView r;
    private RelativeLayout s;
    private Context t;
    private EsimFaqAdapter u;
    private ListViewForScroll v;
    private LinearLayout w;
    private HealthTextView x;
    private RelativeLayout y;
    private int z;
    private String ab = "";
    private int l = 30;
    private boolean ah = false;
    private boolean ag = false;
    private EsimFaqAdapter.OnItemClickListener am = new EsimFaqAdapter.OnItemClickListener() { // from class: com.huawei.sim.esim.view.EsimManagerActivity.3
        @Override // com.huawei.sim.multisim.adapter.EsimFaqAdapter.OnItemClickListener
        public void onClick(String str, String str2) {
            LogUtil.a("EsimManagerActivity", "mOnItemClickListener url = ", str2, ",title = ", str);
            EsimManagerActivity.this.b(str, str2);
        }
    };

    static /* synthetic */ int w(EsimManagerActivity esimManagerActivity) {
        int i = esimManagerActivity.ad;
        esimManagerActivity.ad = i + 1;
        return i;
    }

    @Override // com.huawei.sim.esim.view.BaseOpenSimCardActivity, com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        MultiSimDeviceInfo multiSimDeviceInfo;
        List<SimInfo> simInfoList;
        super.onCreate(bundle);
        setContentView(R.layout.activity_esim_manager);
        Intent intent = getIntent();
        if (intent != null) {
            this.o = intent.getIntExtra("esim_card_type_key", 1);
            this.ag = intent.getBooleanExtra("esim_is_open", false);
            this.al = (MultiSimDeviceInfo) intent.getParcelableExtra("esiim_multi_sim_deviceinfo");
        }
        this.t = this;
        this.e = new b(this);
        LogUtil.a("EsimManagerActivity", "onCreate()");
        ktx.e().a(false);
        ncf.g(this.t);
        l();
        nbq.e(this).c(true);
        if (this.ag && (multiSimDeviceInfo = this.al) != null && !TextUtils.isEmpty(multiSimDeviceInfo.getEID()) && (simInfoList = this.al.getSimInfoList()) != null && simInfoList.size() == 1) {
            ReleaseLogUtil.e("DEVMGR_EsimManagerActivity", "esim is already open");
            e(simInfoList.get(0));
            w();
            if (!Utils.o()) {
                return;
            }
        }
        if (this.o == 1) {
            ad();
        } else {
            b();
        }
    }

    private void ad() {
        String[] strArr;
        if (Build.VERSION.SDK_INT > 29) {
            strArr = new String[]{"android.permission.READ_PHONE_NUMBERS"};
        } else {
            strArr = new String[]{"android.permission.READ_PHONE_STATE"};
        }
        if (!jdi.c(this.t, strArr)) {
            LogUtil.h("EsimManagerActivity", "no permission, need to request");
            sqo.o("no permission, need to request.");
            jdi.bFL_(this, strArr, new PermissionsResultAction() { // from class: com.huawei.sim.esim.view.EsimManagerActivity.14
                @Override // com.huawei.hwcommonmodel.utils.permission.PermissionsResultAction
                public void onGranted() {
                    LogUtil.a("EsimManagerActivity", "PermissionsResultAction onGranted");
                    EsimManagerActivity.this.b();
                }

                @Override // com.huawei.hwcommonmodel.utils.permission.PermissionsResultAction
                public void onDenied(String str) {
                    LogUtil.a("EsimManagerActivity", "PermissionsResultAction onDenied");
                    EsimManagerActivity.this.b();
                }
            });
        } else {
            LogUtil.a("EsimManagerActivity", "enter else have Permissions ");
            b();
        }
    }

    private void l() {
        CustomTitleBar customTitleBar = (CustomTitleBar) findViewById(R.id.sim_manager_title_bar);
        this.av = customTitleBar;
        customTitleBar.setTitleBarBackgroundColor(getResources().getColor(R.color._2131296690_res_0x7f0901b2));
        this.y = (RelativeLayout) findViewById(R.id.rl_esim_error_info);
        this.x = (HealthTextView) findViewById(R.id.tv_esim_error_info);
        this.k = (LinearLayout) findViewById(R.id.managerContent);
        this.ae = (RelativeLayout) findViewById(R.id.rel_icon_parent);
        this.ay = (RelativeLayout) findViewById(R.id.multi_sim_waiting_content);
        this.n = (LinearLayout) findViewById(R.id.btn_layout);
        this.w = (LinearLayout) findViewById(R.id.esim_faq_layout);
        this.ac = (ImageView) findViewById(R.id.img_esim_open_icon);
        this.aw = (HealthTextView) findViewById(R.id.tv_esim_open_status);
        this.ax = (HealthTextView) findViewById(R.id.tv_esim_open_tips);
        HealthTextView healthTextView = (HealthTextView) findViewById(R.id.tv_esim_user_guide);
        this.at = healthTextView;
        healthTextView.setText(ncf.e(this.t.getString(R.string._2130848023_res_0x7f022917)));
        this.at.setOnClickListener(this);
        this.at.setVisibility(8);
        this.w.setVisibility(8);
        this.an = (HealthButton) findViewById(R.id.btn_esim_enable);
        this.ak = (HealthButton) findViewById(R.id.btn_esim_enable_now);
        this.an.setOnClickListener(this);
        this.ak.setOnClickListener(this);
        this.an.setVisibility(8);
        this.ak.setVisibility(8);
        this.m = (HealthButton) findViewById(R.id.btn_esim_account_manage);
        HealthTextView healthTextView2 = (HealthTextView) findViewById(R.id.multi_sim_waiting_info);
        this.bb = healthTextView2;
        healthTextView2.setVisibility(4);
        this.ao = (HealthButton) findViewById(R.id.btn_esim_retry);
        this.ar = (LinearLayout) findViewById(R.id.open_failed_button_layout);
        this.as = (HealthButton) findViewById(R.id.btn_esim_reactivate);
        this.ao.setOnClickListener(this);
        this.as.setOnClickListener(this);
        this.ay.setVisibility(0);
        this.v = (ListViewForScroll) findViewById(R.id.esim_faq_listview);
        this.s = (RelativeLayout) findViewById(R.id.download_fail_layout);
        this.r = (ImageView) findViewById(R.id.download_fail_image);
        this.p = (HealthTextView) findViewById(R.id.download_fail_text);
        this.au = (LinearLayout) findViewById(R.id.setting_layout);
        HealthButton healthButton = (HealthButton) findViewById(R.id.setting_btn);
        this.aq = healthButton;
        healthButton.setOnClickListener(this);
        this.af = (ImageView) findViewById(R.id.iv_arrow);
        this.aa = (HealthTextView) findViewById(R.id.tv_go_setting);
        if (nrt.a(this.t)) {
            this.y.setBackground(getResources().getDrawable(R.drawable.bg_error_layout_dark_mode_background));
            this.aa.setTextColor(this.t.getResources().getColor(R.color._2131296309_res_0x7f090035));
            this.af.setImageDrawable(getResources().getDrawable(R.drawable._2131427550_res_0x7f0b00de));
        } else {
            this.y.setBackground(getResources().getDrawable(R.drawable.bg_error_layout_background));
            this.aa.setTextColor(this.t.getResources().getColor(R.color._2131299241_res_0x7f090ba9));
            this.af.setImageDrawable(getResources().getDrawable(R.drawable._2131431483_res_0x7f0b103b));
        }
        this.af.setOnClickListener(this);
        this.aa.setOnClickListener(this);
        HealthSubHeader healthSubHeader = (HealthSubHeader) findViewById(R.id.esim_faq_subheader_title);
        healthSubHeader.setSplitterVisibility(4);
        healthSubHeader.setSubHeaderBackgroundColor(this.t.getResources().getColor(R.color._2131296690_res_0x7f0901b2));
    }

    private void t() {
        ThreadPoolManager.d().d("EsimManagerActivity", new Runnable() { // from class: com.huawei.sim.esim.view.EsimManagerActivity.19
            @Override // java.lang.Runnable
            public void run() {
                LogUtil.a("EsimManagerActivity", "parse sim info xml file start");
                new ncc().e(new IBaseResponseCallback() { // from class: com.huawei.sim.esim.view.EsimManagerActivity.19.1
                    @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                    /* renamed from: onResponse */
                    public void d(int i, Object obj) {
                        if (i == 0 && (obj instanceof List)) {
                            nce.e((List<nch>) obj);
                        }
                        EsimManagerActivity.this.ac();
                    }
                });
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void ac() {
        LogUtil.a("EsimManagerActivity", "parse esim open method xml file start");
        new ncb().b(new IBaseResponseCallback() { // from class: com.huawei.sim.esim.view.EsimManagerActivity.17
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i, Object obj) {
                if (i == 0 && (obj instanceof List)) {
                    nce.c((List<nca>) obj);
                }
                EsimManagerActivity.this.e.sendMessage(EsimManagerActivity.this.e.obtainMessage(10004));
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void p() {
        int b2 = ncd.b(this.t);
        this.z = b2;
        if (b2 == 0 || m()) {
            u();
        } else {
            w();
        }
    }

    private boolean m() {
        int i;
        return this.o == 2 && ((i = this.z) == 4 || i == 13 || i == 14 || i == 3);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void u() {
        ktx.e().b(new IBaseResponseCallback() { // from class: com.huawei.sim.esim.view.EsimManagerActivity.18
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i, Object obj) {
                ReleaseLogUtil.e("DEVMGR_EsimManagerActivity", "getLocalDeviceInfo onResponse errorCode :", Integer.valueOf(i));
                ncf.c(i, false);
                if (i == 1 && EsimManagerActivity.this.ad < 5) {
                    EsimManagerActivity.w(EsimManagerActivity.this);
                    LogUtil.h("EsimManagerActivity", "power down, retry ");
                    EsimManagerActivity.this.e.sendEmptyMessageDelayed(10003, 1000L);
                } else {
                    if (i != 0 || !(obj instanceof MultiSimDeviceInfo)) {
                        EsimManagerActivity.this.z = 7;
                        LogUtil.h("EsimManagerActivity", "refreshDeviceStatus() mEsimStatus :", Integer.valueOf(EsimManagerActivity.this.z));
                        sqo.o("getMultiSimDevicInfo fail errorCode." + i);
                        EsimManagerActivity.this.g();
                        return;
                    }
                    EsimManagerActivity.this.al = (MultiSimDeviceInfo) obj;
                    LogUtil.a("EsimManagerActivity", "getLocalDeviceInfo mMultiSimDeviceInfo :", EsimManagerActivity.this.al);
                    EsimManagerActivity.this.r();
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void r() {
        MultiSimDeviceInfo multiSimDeviceInfo = this.al;
        if (multiSimDeviceInfo != null && !TextUtils.isEmpty(multiSimDeviceInfo.getEID())) {
            List<SimInfo> simInfoList = this.al.getSimInfoList();
            if (simInfoList == null || simInfoList.isEmpty()) {
                e(false);
                return;
            } else if (simInfoList.size() == 1) {
                e(simInfoList.get(0));
            } else {
                this.z = 8;
                LogUtil.h("EsimManagerActivity", "There are multiple profile files");
                sqo.o("There are multiple profile files");
            }
        } else {
            int i = this.ad;
            if (i < 5) {
                this.ad = i + 1;
                LogUtil.a("EsimManagerActivity", "retry refreshDeviceStatus");
                this.e.sendEmptyMessageDelayed(10003, 1000L);
                return;
            } else {
                this.z = 7;
                LogUtil.h("EsimManagerActivity", "processDeviceInfo() mEsimStatus :", 7);
                sqo.o("getMultiSimDevicInfo fail");
            }
        }
        g();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(final boolean z) {
        ktx.e().d(new IBaseResponseCallback() { // from class: com.huawei.sim.esim.view.EsimManagerActivity.16
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i, Object obj) {
                ReleaseLogUtil.e("DEVMGR_EsimManagerActivity", "getMultiSimBatteryThreshold onResponse errorCode :", Integer.valueOf(i));
                if (i == 0) {
                    if (!z) {
                        EsimManagerActivity.this.z = 9;
                    } else {
                        EsimManagerActivity.this.e.sendMessage(EsimManagerActivity.this.e.obtainMessage(10008));
                        return;
                    }
                } else if (i == 129001) {
                    EsimManagerActivity.this.z = 10;
                } else if (i == 129002) {
                    EsimManagerActivity.this.z = 12;
                } else {
                    EsimManagerActivity.this.z = 11;
                }
                if (obj instanceof Integer) {
                    int intValue = ((Integer) obj).intValue();
                    if (intValue <= 0 || intValue > 100) {
                        EsimManagerActivity.this.z = 11;
                    } else {
                        EsimManagerActivity.this.l = intValue;
                    }
                } else {
                    LogUtil.h("EsimManagerActivity", "getMultiSimBatteryThreshold onResponse objectData is not Integer");
                }
                LogUtil.h("EsimManagerActivity", "refreshBatteryStatus() mEsimStatus :", Integer.valueOf(EsimManagerActivity.this.z));
                if (!z) {
                    EsimManagerActivity.this.g();
                } else {
                    EsimManagerActivity.this.e.sendMessage(EsimManagerActivity.this.e.obtainMessage(10009));
                }
            }
        });
    }

    private void e(SimInfo simInfo) {
        String imsi = simInfo.getIMSI();
        this.ab = imsi;
        this.ap = ncf.b(this.t, imsi);
        this.z = 100;
        ReleaseLogUtil.e("DEVMGR_EsimManagerActivity", "refreshHasBindStatus() mEsimStatus is:", 100);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view.getId() == R.id.tv_esim_user_guide) {
            b(this.t.getString(R.string._2130848023_res_0x7f022917), ncf.d(this.t));
        } else if (view.getId() == R.id.btn_esim_enable || view.getId() == R.id.btn_esim_reactivate || view.getId() == R.id.btn_esim_enable_now) {
            this.aj = view;
            this.an.setEnabled(false);
            this.ak.setEnabled(false);
            this.ay.setVisibility(0);
            int b2 = ncd.b(this.t);
            this.z = b2;
            if (b2 == 0 || m()) {
                e(false);
            } else {
                w();
            }
        } else if (view.getId() == R.id.iv_arrow) {
            LogUtil.a("EsimManagerActivity", "enter onclick arrow mEsimStatus:", Integer.valueOf(this.z));
            int i = this.z;
            if (i == 2) {
                ncf.csN_((Activity) this.t, 10001);
            } else if (i == 3) {
                ncf.csQ_((Activity) this.t, 10001);
            } else {
                LogUtil.a("EsimManagerActivity", "else branch");
            }
        } else if (view.getId() == R.id.tv_go_setting) {
            LogUtil.a("EsimManagerActivity", "enter onclick stext mEsimStatus:", Integer.valueOf(this.z));
            ncf.csQ_((Activity) this.t, 10001);
        } else if (view.getId() == R.id.setting_btn) {
            CommonUtil.q(this.t);
        } else {
            String b3 = SharedPreferenceManager.b(BaseApplication.getContext(), "share_preference_esim_accode", "KEY_ACCODE");
            LogUtil.a("EsimManagerActivity", "onClick acCode: ", b3);
            if (TextUtils.isEmpty(b3)) {
                ViewClickInstrumentation.clickOnView(view);
                return;
            }
            this.ay.setVisibility(0);
            this.k.setVisibility(8);
            this.bb.setText(this.t.getResources().getString(R.string._2130848014_res_0x7f02290e));
            this.e.sendEmptyMessageDelayed(10005, 120000L);
            a(b3);
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void w() {
        ReleaseLogUtil.e("DEVMGR_EsimManagerActivity", "refreshViewStatus mEsimStatus :", Integer.valueOf(this.z));
        if (this.z == 0) {
            LogUtil.h("EsimManagerActivity", "Esim Status is default,so not refresh view");
            return;
        }
        this.ay.setVisibility(8);
        this.k.setVisibility(0);
        this.bb.setText(this.t.getResources().getString(R.string._2130847997_res_0x7f0228fd));
        if (this.z == 9 && this.aj != null) {
            this.an.setEnabled(true);
            this.ak.setEnabled(true);
            csc_(this.aj);
            return;
        }
        this.y.setVisibility(0);
        this.ac.setVisibility(0);
        this.aw.setVisibility(0);
        this.ax.setVisibility(0);
        this.ar.setVisibility(8);
        int d = nrs.d(this.t);
        ViewGroup.LayoutParams layoutParams = this.ae.getLayoutParams();
        layoutParams.height = d / 3;
        this.ae.setLayoutParams(layoutParams);
        if (this.z < 100) {
            x();
        } else {
            q();
        }
        if (this.z == 9 || this.aj == null) {
            return;
        }
        nrh.d(this.t, this.x.getText().toString());
        this.aj = null;
    }

    private void csc_(View view) {
        if (view.getId() == R.id.btn_esim_enable || view.getId() == R.id.btn_esim_reactivate || view.getId() == R.id.btn_esim_enable_now) {
            if (nce.d()) {
                k();
            } else if (!Utils.o()) {
                s();
            } else {
                PermissionUtil.b(this.t, PermissionUtil.PermissionType.CAMERA, new CustomPermissionAction(this.t) { // from class: com.huawei.sim.esim.view.EsimManagerActivity.20
                    @Override // com.huawei.hwcommonmodel.utils.permission.PermissionsResultAction
                    public void onGranted() {
                        LogUtil.b("EsimManagerActivity", "qrCodeAction onGranted");
                        EsimManagerActivity.this.f();
                    }

                    @Override // com.huawei.ui.commonui.utils.CustomPermissionAction, com.huawei.hwcommonmodel.utils.permission.PermissionsResultAction
                    public void onDenied(String str) {
                        LogUtil.b("EsimManagerActivity", "qrCodeAction onDenied");
                        EsimManagerActivity.this.f();
                    }

                    @Override // com.huawei.ui.commonui.utils.CustomPermissionAction, com.huawei.hwcommonmodel.utils.permission.PermissionsResultAction
                    public void onForeverDenied(PermissionUtil.PermissionType permissionType) {
                        LogUtil.b("EsimManagerActivity", "qrCodeAction onForeverDenied");
                        EsimManagerActivity.this.f();
                    }
                });
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void f() {
        ncf.b(HiAnalyticsConstant.KeyAndValue.NUMBER_01, true);
        try {
            Intent intent = new Intent(this.t, (Class<?>) QrCodeActivity.class);
            intent.putExtra("esim_new_original_key", 1);
            this.t.startActivity(intent);
        } catch (ActivityNotFoundException unused) {
            LogUtil.b("EsimManagerActivity", "onGranted ActivityNotFoundException");
        }
    }

    private void k() {
        if (Utils.o() && this.o == 2) {
            b(this.al);
        } else {
            s();
        }
    }

    private void x() {
        this.n.setVisibility(0);
        if (this.o == 2) {
            this.ac.setImageDrawable(this.t.getResources().getDrawable(R.drawable._2131431573_res_0x7f0b1095));
            this.aw.setText(this.t.getResources().getString(R.string._2130848080_res_0x7f022950));
        } else {
            c(R.drawable.pic_esim_not_open);
            this.aw.setText(this.t.getResources().getString(R.string._2130848022_res_0x7f022916));
        }
        if (!Utils.o()) {
            if (this.o == 2) {
                this.ax.setText(this.t.getResources().getString(R.string._2130848101_res_0x7f022965));
            } else {
                this.ax.setText(this.t.getResources().getString(R.string._2130848143_res_0x7f02298f));
            }
        } else if (this.o == 2) {
            this.ax.setText(this.t.getResources().getString(R.string._2130848101_res_0x7f022965) + this.t.getResources().getString(R.string._2130848102_res_0x7f022966));
        } else {
            this.ax.setText(this.t.getResources().getString(R.string._2130848100_res_0x7f022964) + this.t.getResources().getString(R.string._2130848102_res_0x7f022966));
        }
        this.an.setBackgroundResource(R.drawable._2131427591_res_0x7f0b0107);
        this.an.setEnabled(false);
        this.ak.setBackgroundResource(R.drawable._2131427591_res_0x7f0b0107);
        this.ak.setEnabled(false);
        if (Utils.o()) {
            this.at.setVisibility(0);
            this.an.setVisibility(0);
            this.w.setVisibility(8);
            this.ak.setVisibility(8);
            if (nce.d()) {
                this.an.setText(this.t.getString(R.string._2130847986_res_0x7f0228f2));
            } else {
                this.an.setText(this.t.getString(R.string._2130847983_res_0x7f0228ef));
            }
        } else {
            this.at.setVisibility(8);
            this.an.setVisibility(8);
            this.w.setVisibility(0);
            EsimFaqAdapter esimFaqAdapter = new EsimFaqAdapter(this.t, this.ab);
            this.u = esimFaqAdapter;
            esimFaqAdapter.a(this.am);
            this.v.setAdapter((ListAdapter) this.u);
            this.ak.setVisibility(0);
            this.ak.setText(this.t.getString(R.string._2130847986_res_0x7f0228f2));
        }
        v();
    }

    private void c(int i) {
        Drawable drawable = getResources().getDrawable(R.drawable.pic_open_esim_bg);
        drawable.setAutoMirrored(true);
        this.ac.setImageDrawable(new LayerDrawable(new Drawable[]{drawable, getResources().getDrawable(i)}));
    }

    private void csb_(String[] strArr, SpannableStringBuilder spannableStringBuilder, SpannableString spannableString) {
        spannableStringBuilder.append((CharSequence) spannableString);
        if (strArr.length > 1) {
            spannableStringBuilder.append((CharSequence) strArr[strArr.length - 1]);
        }
        this.ax.setText(spannableStringBuilder);
        this.ax.setMovementMethod(LinkMovementMethod.getInstance());
        this.ax.setHighlightColor(ContextCompat.getColor(this, R.color._2131296323_res_0x7f090043));
    }

    private void v() {
        int i = this.z;
        if (i == 9) {
            ag();
            this.y.setVisibility(8);
            this.an.setBackgroundResource(R.drawable._2131430895_res_0x7f0b0def);
            this.an.setEnabled(true);
            this.ak.setBackgroundResource(R.drawable._2131430895_res_0x7f0b0def);
            this.ak.setEnabled(true);
            return;
        }
        if (i == 10) {
            this.x.setText(String.format(Locale.ENGLISH, this.t.getString(R.string.IDS_plugin_multi_esim_device_low_power), UnitUtil.e(this.l, 2, 0)));
            return;
        }
        if (m()) {
            this.y.setVisibility(8);
            this.an.setBackgroundResource(R.drawable._2131430895_res_0x7f0b0def);
            this.an.setEnabled(true);
            this.ak.setBackgroundResource(R.drawable._2131430895_res_0x7f0b0def);
            this.ak.setEnabled(true);
            return;
        }
        this.x.setText(ncd.a(this.t, this.z));
        int i2 = this.z;
        if (i2 == 2) {
            this.af.setVisibility(0);
            this.aa.setVisibility(8);
        } else if (i2 == 3) {
            this.af.setVisibility(0);
            this.aa.setVisibility(0);
        } else {
            this.af.setVisibility(8);
            this.aa.setVisibility(8);
        }
    }

    private void q() {
        String format;
        if (this.o == 2) {
            this.ac.setImageDrawable(this.t.getResources().getDrawable(R.drawable._2131431574_res_0x7f0b1096));
            boolean c = cwi.c(jpt.a("EsimManagerActivity"), MachineControlPointResponse.OP_CODE_EXTENSION_SET_TARGETED_EXPENDED_ENERGY);
            ReleaseLogUtil.e("DEVMGR_EsimManagerActivity", "is support delete esim:", Boolean.valueOf(c));
            if (c) {
                ai();
            }
        } else {
            c(R.drawable.pic_esim_open_success);
        }
        if (TextUtils.isEmpty(this.ap)) {
            if (this.o == 2) {
                format = this.t.getString(R.string._2130848091_res_0x7f02295b);
            } else {
                format = this.t.getString(R.string._2130848097_res_0x7f022961);
            }
        } else if (this.o == 2) {
            format = String.format(Locale.ENGLISH, this.t.getString(R.string._2130848090_res_0x7f02295a), this.ap);
        } else {
            format = String.format(Locale.ENGLISH, this.t.getString(R.string._2130848098_res_0x7f022962), this.ap);
        }
        this.aw.setText(format);
        this.ak.setVisibility(8);
        if (!Utils.o()) {
            this.ax.setText(this.t.getString(R.string._2130848050_res_0x7f022932));
            this.w.setVisibility(0);
            EsimFaqAdapter esimFaqAdapter = new EsimFaqAdapter(this.t, this.ab);
            this.u = esimFaqAdapter;
            esimFaqAdapter.a(this.am);
            this.v.setAdapter((ListAdapter) this.u);
            this.n.setVisibility(8);
        } else {
            o();
            n();
            this.w.setVisibility(8);
        }
        this.ar.setVisibility(8);
        this.y.setVisibility(8);
    }

    private void ai() {
        this.av.setRightButtonDrawable(getResources().getDrawable(R.drawable._2131430193_res_0x7f0b0b31), nsf.h(R.string._2130850635_res_0x7f02334b));
        this.av.setRightButtonClickable(true);
        this.av.setRightButtonOnClickListener(new View.OnClickListener() { // from class: com.huawei.sim.esim.view.EsimManagerActivity.22
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                EsimManagerActivity.this.af();
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        this.av.setRightButtonVisibility(0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void af() {
        new PopViewList(this.t, this.av, new ArrayList(Collections.singletonList(getResources().getString(R.string._2130848111_res_0x7f02296f)))).e(new PopViewList.PopViewClickListener() { // from class: com.huawei.sim.esim.view.EsimManagerActivity.21
            @Override // com.huawei.ui.commonui.popupview.PopViewList.PopViewClickListener
            public void setOnClick(int i) {
                LogUtil.a("EsimManagerActivity", "enter showPopUpView");
                if (i == 0) {
                    if (!ktx.e().g()) {
                        nrh.b(EsimManagerActivity.this.t, R.string._2130848117_res_0x7f022975);
                    } else {
                        EsimManagerActivity.this.z();
                    }
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void z() {
        LogUtil.a("EsimManagerActivity", "enter showDelSimDialog()");
        String string = this.t.getResources().getString(R.string._2130848113_res_0x7f022971);
        String string2 = this.t.getResources().getString(R.string._2130847923_res_0x7f0228b3);
        CustomTextAlertDialog a2 = new CustomTextAlertDialog.Builder(this.t).b(R.string._2130848112_res_0x7f022970).e(string).cyS_(string2, new View.OnClickListener() { // from class: com.huawei.sim.esim.view.EsimManagerActivity.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LogUtil.h("EsimManagerActivity", "press negative button");
                ViewClickInstrumentation.clickOnView(view);
            }
        }).cyV_(this.t.getResources().getString(R.string._2130848002_res_0x7f022902), new View.OnClickListener() { // from class: com.huawei.sim.esim.view.EsimManagerActivity.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                EsimManagerActivity.this.ah();
                EsimManagerActivity.this.ab();
                ViewClickInstrumentation.clickOnView(view);
            }
        }).a();
        a2.setCancelable(false);
        a2.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void ab() {
        LogUtil.a("EsimManagerActivity", "enter sendRemoveSimProfileCmd");
        ktx.e().a(this.al.getSimInfoList(), new IBaseResponseCallback() { // from class: com.huawei.sim.esim.view.EsimManagerActivity.5
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i, Object obj) {
                ReleaseLogUtil.e("DEVMGR_EsimManagerActivity", "sendRemoveSimProfileCmd onResponse errorCode : ", Integer.valueOf(i));
                Message obtainMessage = EsimManagerActivity.this.e.obtainMessage(10010);
                obtainMessage.arg1 = i;
                EsimManagerActivity.this.e.sendMessage(obtainMessage);
            }
        });
    }

    private void aa() {
        LogUtil.a("EsimManagerActivity", "enter showDelSimDialog()");
        String string = this.t.getResources().getString(R.string._2130848118_res_0x7f022976);
        CustomTextAlertDialog a2 = new CustomTextAlertDialog.Builder(this.t).b(R.string._2130848114_res_0x7f022972).e(string).cyV_(this.t.getResources().getString(R.string._2130847966_res_0x7f0228de), new View.OnClickListener() { // from class: com.huawei.sim.esim.view.EsimManagerActivity.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LogUtil.h("EsimManagerActivity", "showDelSimFailedDialog setPositiveButton");
                ViewClickInstrumentation.clickOnView(view);
            }
        }).a();
        a2.setCancelable(false);
        a2.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void ah() {
        LogUtil.a("EsimManagerActivity", "showDelLoadingDialog: mLoadDataDialog ");
        CommonDialog21 a2 = CommonDialog21.a(this);
        this.ai = a2;
        a2.e(getString(R.string._2130848115_res_0x7f022973));
        this.ai.setCancelable(false);
        this.ai.a();
        this.ai.show();
    }

    private void o() {
        List<Map<String, Object>> c = ncf.c(this.t);
        if (this.o == 2 || koq.b(c)) {
            LogUtil.h("EsimManagerActivity", "mCardType is standalone or supportAccountManageSimCardList is empty");
            this.n.setVisibility(8);
            return;
        }
        this.n.setVisibility(0);
        this.an.setVisibility(8);
        this.at.setVisibility(8);
        this.m.setVisibility(0);
        this.m.setAllCaps(false);
        this.m.setText(ncf.e(this.t.getString(R.string._2130848071_res_0x7f022947)));
        this.m.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.sim.esim.view.EsimManagerActivity.9
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LogUtil.a("EsimManagerActivity", "eSIM account management button is clicked");
                EsimManagerActivity.this.m.setEnabled(false);
                EsimManagerActivity.this.ay.setVisibility(0);
                EsimManagerActivity esimManagerActivity = EsimManagerActivity.this;
                esimManagerActivity.z = ncd.b(esimManagerActivity.t);
                if (EsimManagerActivity.this.z == 0) {
                    EsimManagerActivity.this.e(true);
                } else {
                    EsimManagerActivity.this.y();
                }
                ViewClickInstrumentation.clickOnView(view);
            }
        });
    }

    private void n() {
        String string = this.t.getString(R.string._2130847992_res_0x7f0228f8);
        String string2 = getString(R.string._2130848023_res_0x7f022917);
        String[] split = String.format(Locale.ENGLISH, string, string2).split(string2);
        if (split.length == 0) {
            LogUtil.h("EsimManagerActivity", "initBindTipsView() splits is empty");
            return;
        }
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
        SpannableString spannableString = new SpannableString(split[0]);
        spannableString.setSpan(new ForegroundColorSpan(ContextCompat.getColor(this, R.color._2131299241_res_0x7f090ba9)), 0, spannableString.length(), 33);
        spannableStringBuilder.append((CharSequence) spannableString);
        SpannableString spannableString2 = new SpannableString(string2);
        spannableString2.setSpan(new ClickableSpan() { // from class: com.huawei.sim.esim.view.EsimManagerActivity.6
            @Override // android.text.style.ClickableSpan, android.text.style.CharacterStyle
            public void updateDrawState(TextPaint textPaint) {
                super.updateDrawState(textPaint);
                textPaint.setColor(EsimManagerActivity.this.getResources().getColor(R.color._2131296319_res_0x7f09003f));
                textPaint.setUnderlineText(false);
            }

            @Override // android.text.style.ClickableSpan
            public void onClick(View view) {
                EsimManagerActivity esimManagerActivity = EsimManagerActivity.this;
                esimManagerActivity.b(esimManagerActivity.t.getString(R.string._2130848023_res_0x7f022917), ncf.d(EsimManagerActivity.this.t));
                ViewClickInstrumentation.clickOnView(view);
            }
        }, 0, spannableString2.length(), 33);
        csb_(split, spannableStringBuilder, spannableString2);
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onStop() {
        super.onStop();
        if (this.aj != null) {
            this.aj = null;
        }
    }

    @Override // com.huawei.sim.esim.view.BaseOpenSimCardActivity, com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        if (this.e != null) {
            this.e.removeMessages(10002);
            this.e.removeMessages(10003);
            this.e.removeMessages(10004);
            this.e.removeMessages(10005);
            this.e.removeMessages(10006);
            this.e.removeMessages(10007);
            this.e.removeMessages(10008);
            this.e.removeMessages(10009);
        }
        nbq.e(this).c(false);
        cun.c().cancelDownloadDeviceResources();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void g() {
        this.e.sendMessage(this.e.obtainMessage(10002));
    }

    static class b extends BaseHandler<EsimManagerActivity> {
        b(EsimManagerActivity esimManagerActivity) {
            super(esimManagerActivity);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.huawei.haf.handler.BaseHandler
        /* renamed from: csd_, reason: merged with bridge method [inline-methods] */
        public void handleMessageWhenReferenceNotNull(EsimManagerActivity esimManagerActivity, Message message) {
            if (message != null) {
                switch (message.what) {
                    case 10001:
                        esimManagerActivity.crZ_(message);
                        break;
                    case 10002:
                        esimManagerActivity.w();
                        break;
                    case 10003:
                        esimManagerActivity.u();
                        break;
                    case 10004:
                        esimManagerActivity.p();
                        break;
                    case 10005:
                        LogUtil.a("EsimManagerActivity", "redownload timeout");
                        esimManagerActivity.p();
                        break;
                    case 10006:
                        if (message.arg1 == 0) {
                            esimManagerActivity.e();
                            ktx.e().a(true);
                        } else {
                            sqo.o("MSG_SIM_MSG_DOWNLOAD_PROFILE_COMPLETE not is SUCCESS");
                            ktx.e().a(false);
                        }
                        esimManagerActivity.p();
                        break;
                    case 10007:
                        esimManagerActivity.i();
                        break;
                    case 10008:
                        esimManagerActivity.ae();
                        break;
                    case 10009:
                        sqo.o("MSG_REFRESH_BATTERY_THRESHOLD_ERROR_VIEW");
                        esimManagerActivity.y();
                        break;
                    case 10010:
                        esimManagerActivity.csa_(message);
                        break;
                    default:
                        LogUtil.h("EsimManagerActivity", "handleMessageWhenReferenceNotNull default");
                        break;
                }
            }
            LogUtil.a("EsimManagerActivity", "handleMessageWhenReferenceNotNull msg is null");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void csa_(Message message) {
        CommonDialog21 commonDialog21 = this.ai;
        if (commonDialog21 != null && commonDialog21.isShowing()) {
            LogUtil.a("EsimManagerActivity", "enter mLoadingDialog dismiss");
            this.ai.dismiss();
            this.ai = null;
        }
        if (message == null) {
            LogUtil.h("EsimManagerActivity", "processDeleteEsimResult msg is null");
            return;
        }
        int i = message.arg1;
        if (i == 0) {
            nrh.b(this.t, R.string._2130848119_res_0x7f022977);
            finish();
            return;
        }
        if (i == 1) {
            sqo.o("DELETE_SIM_FAILED");
            nrh.b(this.t, R.string._2130848114_res_0x7f022972);
            return;
        }
        if (i != 127) {
            if (i == 223 || i == 276) {
                sqo.o("DELETE_SIM_CARRIER_RESTRICTIONS");
                aa();
                return;
            } else if (i != 100009) {
                LogUtil.h("EsimManagerActivity", "dealReportResult, default");
                return;
            }
        }
        sqo.o("DeleteEsimResult ERR_TIME_OUT or DELETE_SIM_SYSTEM_ERROR");
        nrh.b(this.t, R.string._2130848116_res_0x7f022974);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void ae() {
        this.ay.setVisibility(8);
        this.m.setEnabled(true);
        try {
            this.t.startActivity(new Intent(this.t, (Class<?>) EsimSelectCardActivity.class));
        } catch (ActivityNotFoundException unused) {
            LogUtil.b("EsimManagerActivity", "startAccountManageProcess ActivityNotFoundException");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void y() {
        this.ay.setVisibility(8);
        this.y.setVisibility(0);
        v();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void i() {
        LogUtil.a("EsimManagerActivity", "handleNetworkingDownloadResources network is connected, download resources again");
        this.s.setVisibility(8);
        this.ay.setVisibility(0);
        b();
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        RelativeLayout relativeLayout;
        super.onResume();
        boolean j = ktx.e().j();
        LogUtil.a("EsimManagerActivity", "onResume isOperatorOpenSuccess is ", Boolean.valueOf(j));
        if (!j || (relativeLayout = this.ay) == null) {
            return;
        }
        relativeLayout.setVisibility(0);
        boolean c = cwi.c(jpt.a("EsimManagerActivity"), 148);
        LogUtil.a("EsimManagerActivity", "onResume isSupportStandaloneNum = ", Boolean.valueOf(c));
        if (!c) {
            ktx.e().a(false);
        }
        p();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(final String str, final String str2) {
        if (nsn.o()) {
            return;
        }
        LogUtil.a("EsimManagerActivity", "startWebView enter");
        ThreadPoolManager.d().d("EsimManagerActivity", new Runnable() { // from class: com.huawei.sim.esim.view.EsimManagerActivity.10
            @Override // java.lang.Runnable
            public void run() {
                final String url = GRSManager.a(BaseApplication.getContext()).getUrl("domainTipsResDbankcdn");
                if (TextUtils.isEmpty(url)) {
                    return;
                }
                EsimManagerActivity.this.runOnUiThread(new Runnable() { // from class: com.huawei.sim.esim.view.EsimManagerActivity.10.5
                    @Override // java.lang.Runnable
                    public void run() {
                        ncf.e(EsimManagerActivity.this.t, str, url + str2);
                    }
                });
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(int i) {
        if (this.e == null || !this.e.hasMessages(i)) {
            return;
        }
        this.e.removeMessages(i);
    }

    private void b(String str) {
        NoTitleCustomAlertDialog.Builder builder = new NoTitleCustomAlertDialog.Builder(this.t);
        builder.e(str);
        builder.czE_(this.t.getString(R.string._2130847966_res_0x7f0228de), new View.OnClickListener() { // from class: com.huawei.sim.esim.view.EsimManagerActivity.8
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LogUtil.a("EsimManagerActivity", "user choose cancel");
                if (EsimManagerActivity.this.q != null) {
                    EsimManagerActivity.this.q.dismiss();
                }
                Intent intent = new Intent(EsimManagerActivity.this.t, (Class<?>) MultiSimCardActivity.class);
                intent.putExtra("BatteryThreshold", EsimManagerActivity.this.l);
                intent.putExtra("esim_card_type_key", EsimManagerActivity.this.o);
                intent.putExtra("multi_sim_device_info", EsimManagerActivity.this.al);
                EsimManagerActivity.this.startActivity(intent);
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        NoTitleCustomAlertDialog e = builder.e();
        this.q = e;
        e.setCancelable(false);
        if (this.q.isShowing()) {
            return;
        }
        this.q.show();
    }

    private void s() {
        String b2 = SharedPreferenceManager.b(BaseApplication.getContext(), "share_preference_esim_accode", "KEY_ACCODE");
        LogUtil.a("EsimManagerActivity", "jumpToMultiSimCardActivity acCode: ", b2);
        if (!TextUtils.isEmpty(b2) && !Utils.o() && ncf.f(this.t) && this.o == 1) {
            b(getString(R.string._2130848034_res_0x7f022922));
            return;
        }
        Intent intent = new Intent(this.t, (Class<?>) MultiSimCardActivity.class);
        intent.putExtra("BatteryThreshold", this.l);
        intent.putExtra("esim_card_type_key", this.o);
        intent.putExtra("multi_sim_device_info", this.al);
        startActivity(intent);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e() {
        if (Utils.o()) {
            return;
        }
        ncf.a("", "0");
    }

    private void a(String str) {
        ktx.e().e(str, new IBaseResponseCallback() { // from class: com.huawei.sim.esim.view.EsimManagerActivity.7
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i, Object obj) {
                LogUtil.a("EsimManagerActivity", "openEsimWithoutConfirm first errorCode =", Integer.valueOf(i));
            }
        }, new IBaseResponseCallback() { // from class: com.huawei.sim.esim.view.EsimManagerActivity.15
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i, Object obj) {
                LogUtil.a("EsimManagerActivity", "openEsimWithoutConfirm second errorCode =", Integer.valueOf(i));
                EsimManagerActivity.this.d(10005);
                Message obtainMessage = EsimManagerActivity.this.e.obtainMessage(10006);
                obtainMessage.arg1 = i;
                if (i == 0) {
                    EsimManagerActivity.this.e.sendMessageDelayed(obtainMessage, 5000L);
                } else {
                    EsimManagerActivity.this.e.sendMessage(obtainMessage);
                }
            }
        });
    }

    private void ag() {
        String b2 = SharedPreferenceManager.b(BaseApplication.getContext(), "share_preference_esim_accode", "KEY_ACCODE");
        String b3 = SharedPreferenceManager.b(BaseApplication.getContext(), "share_preference_esim_accode", "KEY_ACCODE_SAVE_TIME");
        String str = "";
        if (TextUtils.isEmpty(b3)) {
            e();
        } else {
            try {
                long currentTimeMillis = System.currentTimeMillis() - Long.parseLong(b3);
                LogUtil.a("EsimManagerActivity", "updateViewWhenHaveAccode currentTime:", Long.valueOf(System.currentTimeMillis()), "updateViewWhenHaveAccode savedTime:", b3, "updateViewWhenHaveAccode timeInterval:", Long.valueOf(currentTimeMillis));
                if (currentTimeMillis > Constants.ANALYSIS_EVENT_KEEP_TIME) {
                    e();
                    b2 = "";
                }
                str = b2;
            } catch (NumberFormatException e) {
                LogUtil.b("EsimManagerActivity", "updateViewWhenHaveAccode NumberFormatException");
                sqo.o("updateViewWhenHaveAccode NumberFormatException" + e.getMessage());
                e();
            }
        }
        LogUtil.a("EsimManagerActivity", "updateViewWhenHaveAccode acCode: ", str);
        if (!TextUtils.isEmpty(str) && !Utils.o() && ncf.f(this.t) && this.o != 2) {
            c(R.drawable.pic_esim_open_failed);
            this.aw.setText(this.t.getResources().getString(R.string._2130848035_res_0x7f022923));
            this.ax.setText(this.t.getResources().getString(R.string._2130848033_res_0x7f022921));
            this.ar.setVisibility(0);
            this.ak.setVisibility(8);
            this.w.setVisibility(8);
            this.n.setVisibility(8);
            this.ao.setBackgroundResource(R.drawable._2131430895_res_0x7f0b0def);
            this.ao.setEnabled(true);
            this.as.setBackgroundResource(R.drawable._2131430895_res_0x7f0b0def);
            this.as.setEnabled(true);
            return;
        }
        this.ar.setVisibility(8);
        this.n.setVisibility(0);
        this.aw.setVisibility(0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void crZ_(Message message) {
        CommonDialog21 commonDialog21 = this.ai;
        if (commonDialog21 != null && commonDialog21.isShowing()) {
            LogUtil.a("EsimManagerActivity", "enter mLoadingDialog dismiss");
            this.ai.dismiss();
            this.ai = null;
        }
        String str = message.obj instanceof String ? (String) message.obj : "";
        if (!this.ah && "android.net.conn.CONNECTIVITY_CHANGE".equals(str) && ncd.a(this.t)) {
            this.e.sendEmptyMessageDelayed(10007, ProfileExtendConstants.TIME_OUT);
        } else {
            p();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b() {
        if (!ncd.a(this.t)) {
            LogUtil.h("EsimManagerActivity", "downloadResources network is not connected");
            b(this.t.getString(R.string._2130848067_res_0x7f022943), R.drawable._2131428076_res_0x7f0b02ec, true);
            return;
        }
        ArrayList arrayList = new ArrayList(16);
        if (ncf.d() == 85) {
            arrayList.add("c7d28238-fec2-4985-b2a4-f29c96637ac4");
        } else {
            arrayList.add("69a968a4-5db8-4d1e-a390-762cb8039784");
        }
        cun.c().downloadDeviceResources(arrayList, new IBaseResponseCallback() { // from class: com.huawei.sim.esim.view.EsimManagerActivity.13
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i, Object obj) {
                if (i == 0) {
                    EsimManagerActivity.this.h();
                    return;
                }
                if (i != 20000 || !(obj instanceof Integer)) {
                    EsimManagerActivity.this.j();
                    return;
                }
                int intValue = ((Integer) obj).intValue();
                EsimManagerActivity.this.bb.setVisibility(0);
                EsimManagerActivity.this.e(intValue);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(int i) {
        String e = UnitUtil.e(i, 2, 0);
        if (LanguageUtil.bp(this.t) || LanguageUtil.y(this.t)) {
            e = "\u200f" + e;
        }
        this.bb.setText(String.format(Locale.ENGLISH, this.t.getString(R.string._2130848069_res_0x7f022945), e));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void h() {
        this.ah = true;
        this.bb.setVisibility(0);
        this.bb.setText(this.t.getResources().getString(R.string._2130847997_res_0x7f0228fd));
        t();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void j() {
        b(this.t.getString(R.string._2130848068_res_0x7f022944), R.drawable._2131428077_res_0x7f0b02ed, false);
        this.s.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.sim.esim.view.EsimManagerActivity.12
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LogUtil.a("EsimManagerActivity", "handleDownloadFail download resources again");
                EsimManagerActivity.this.s.setVisibility(8);
                EsimManagerActivity.this.ay.setVisibility(0);
                EsimManagerActivity.this.b();
                ViewClickInstrumentation.clickOnView(view);
            }
        });
    }

    private void b(String str, int i, boolean z) {
        this.s.setVisibility(0);
        this.k.setVisibility(8);
        this.ay.setVisibility(8);
        if (z) {
            this.au.setVisibility(0);
        } else {
            this.au.setVisibility(8);
        }
        HealthTextView healthTextView = this.p;
        if (healthTextView != null) {
            healthTextView.setText(str);
            this.p.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() { // from class: com.huawei.sim.esim.view.EsimManagerActivity.11
                @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
                public void onGlobalLayout() {
                    EsimManagerActivity.this.p.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                    if (EsimManagerActivity.this.p.getLineCount() >= 2) {
                        EsimManagerActivity.this.p.setGravity(GravityCompat.START);
                    }
                }
            });
        }
        ImageView imageView = this.r;
        if (imageView != null) {
            Context context = this.t;
            ncf.csP_(context, imageView, nsn.c(context, 120.0f));
            Drawable drawable = getResources().getDrawable(i);
            drawable.setAutoMirrored(true);
            this.r.setImageDrawable(drawable);
        }
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        LogUtil.a("EsimManagerActivity", "onActivityResult requestCode:", Integer.valueOf(i));
        if (i == 10001) {
            p();
        }
    }

    @Override // com.huawei.sim.esim.view.BaseOpenSimCardActivity, com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
