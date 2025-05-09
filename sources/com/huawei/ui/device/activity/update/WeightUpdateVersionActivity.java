package com.huawei.ui.device.activity.update;

import android.content.ActivityNotFoundException;
import android.content.BroadcastReceiver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import com.huawei.haf.handler.BaseHandler;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.health.R;
import com.huawei.health.device.callback.IHealthDeviceCallback;
import com.huawei.health.device.connectivity.comm.MeasurableDevice;
import com.huawei.health.device.model.HealthDevice;
import com.huawei.health.device.open.MeasureController;
import com.huawei.health.device.open.MeasureKit;
import com.huawei.health.device.open.data.model.HealthData;
import com.huawei.health.device.util.EventBus;
import com.huawei.health.ecologydevice.manager.ResourceManager;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.openalliance.ad.constant.ParamConstants;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.button.HealthButton;
import com.huawei.ui.commonui.dialog.CustomTextAlertDialog;
import com.huawei.ui.commonui.divider.HealthDivider;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.titlebar.CustomTitleBar;
import com.huawei.ui.device.views.device.RoundProgressImageView;
import defpackage.ceo;
import defpackage.cfl;
import defpackage.cgk;
import defpackage.cgt;
import defpackage.cjx;
import defpackage.cld;
import defpackage.cpa;
import defpackage.cpp;
import defpackage.dcq;
import defpackage.dcx;
import defpackage.dcz;
import defpackage.ixx;
import defpackage.kxp;
import defpackage.nsn;
import defpackage.nsy;
import defpackage.nyi;
import defpackage.oaj;
import health.compact.a.BroadcastManagerUtil;
import health.compact.a.CommonUtil;
import health.compact.a.LocalBroadcast;
import health.compact.a.UnitUtil;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

/* loaded from: classes6.dex */
public class WeightUpdateVersionActivity extends BaseActivity implements View.OnClickListener {

    /* renamed from: a, reason: collision with root package name */
    private HealthButton f9268a;
    private boolean aa;
    private boolean ab;
    private boolean ac;
    private boolean ad;
    private RoundProgressImageView ae;
    private HealthTextView af;
    private LinearLayout ag;
    private HealthTextView ah;
    private HealthTextView ai;
    private LinearLayout aj;
    private String ak;
    private HealthTextView al;
    private String am;
    private HealthTextView an;
    private oaj ar;
    private cld as;
    private ImageView e;
    private CustomTitleBar f;
    private HealthTextView j;
    private RelativeLayout k;
    private ContentValues l;
    private HealthTextView m;
    private HealthTextView n;
    private HealthTextView o;
    private LinearLayout p;
    private RelativeLayout q;
    private HealthTextView r;
    private c t;
    private HealthDivider u;
    private HealthTextView z;
    private String h = "";
    private Context g = null;
    private boolean w = false;
    private int x = 0;
    private boolean y = false;
    private boolean v = false;
    private CustomTextAlertDialog s = null;
    private CustomTextAlertDialog aq = null;
    private CustomTextAlertDialog c = null;
    private final ThreadLocal<Boolean> i = new ThreadLocal<>();
    private BroadcastReceiver d = new BroadcastReceiver() { // from class: com.huawei.ui.device.activity.update.WeightUpdateVersionActivity.3
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            LogUtil.a("WeightUpdateVersionActivity", "onReceive: action = " + action);
            if ("action_app_check_new_version_state".equals(action)) {
                WeightUpdateVersionActivity.this.cTc_(intent);
            }
        }
    };
    private IHealthDeviceCallback b = new IHealthDeviceCallback() { // from class: com.huawei.ui.device.activity.update.WeightUpdateVersionActivity.1
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

        @Override // com.huawei.health.device.callback.IHealthDeviceCallback
        public void onStatusChanged(HealthDevice healthDevice, int i) {
            if (healthDevice != null) {
                LogUtil.a("WeightUpdateVersionActivity", "mCallback onStatusChanged device name is ", healthDevice.getDeviceName());
            }
            LogUtil.a("WeightUpdateVersionActivity", "mCallback onStatusChanged status is ", Integer.valueOf(i));
            if (i == 1 || i == 2) {
                WeightUpdateVersionActivity.this.t.removeMessages(1);
            }
            if (i == 3) {
                if (Boolean.TRUE.equals(WeightUpdateVersionActivity.this.i.get())) {
                    WeightUpdateVersionActivity.this.i.set(false);
                    LogUtil.a("WeightUpdateVersionActivity", "mClickState HWChMeasureController#prepare>onStatusChanged");
                    return;
                } else {
                    WeightUpdateVersionActivity.this.v = false;
                    WeightUpdateVersionActivity.this.t.sendEmptyMessageDelayed(1, 500L);
                    return;
                }
            }
            if (i == 2) {
                WeightUpdateVersionActivity.this.v = true;
                if (!WeightUpdateVersionActivity.this.y) {
                    if (WeightUpdateVersionActivity.this.ar != null) {
                        WeightUpdateVersionActivity.this.ar.c(WeightUpdateVersionActivity.this.ab, WeightUpdateVersionActivity.this.ak);
                        WeightUpdateVersionActivity.this.t.sendEmptyMessage(4);
                        WeightUpdateVersionActivity.this.v();
                        return;
                    }
                    return;
                }
                LogUtil.a("WeightUpdateVersionActivity", "is already failed, do nothing.");
                return;
            }
            LogUtil.a("WeightUpdateVersionActivity", "onStatusChanged: status = ", Integer.valueOf(i));
        }
    };

    /* JADX INFO: Access modifiers changed from: private */
    public void cTc_(Intent intent) {
        int intExtra = intent.getIntExtra("state", -1);
        int intExtra2 = intent.getIntExtra("result", -1);
        String stringExtra = intent.getStringExtra("content");
        LogUtil.a("WeightUpdateVersionActivity", "updateAppState: state = " + intExtra + ", result = " + intExtra2);
        cTd_(intExtra, intExtra2, stringExtra, intent);
        a(intExtra, stringExtra);
        d(intExtra, intExtra2);
    }

    private void cTd_(int i, int i2, String str, Intent intent) {
        switch (i) {
            case 48:
                LogUtil.a("WeightUpdateVersionActivity", "STATE_CHECK_NEW_VERSION_START:mUpdateStatus = " + this.ar.s());
                break;
            case 49:
                LogUtil.a("WeightUpdateVersionActivity", "STATE_CHECK_NEW_VERSION_FAILED:mUpdateStatus = " + this.ar.s());
                if (this.ar.s() == 1) {
                    if (i2 == 0) {
                        LogUtil.a("WeightUpdateVersionActivity", "No New Version");
                        m();
                        this.ar.b();
                        break;
                    } else {
                        c(i2);
                        break;
                    }
                }
                break;
            case 50:
                LogUtil.a("WeightUpdateVersionActivity", "STATE_CHECK_NEW_VERSION_SUCCESS:mUpdateStatus = " + this.ar.s());
                this.ar.e(1);
                this.ar.a((Boolean) true);
                this.ar.c(i2);
                this.ar.j(nsn.b(this.g, i2));
                this.h = this.o.getText().toString();
                this.ar.c(str);
                LogUtil.a("WeightUpdateVersionActivity", "mCurrentVersion=", this.h, ", mBandNewVersion=", this.ar.h());
                this.w = intent.getBooleanExtra("isForced", false);
                this.x = intent.getIntExtra("minAppCode", 0);
                LogUtil.a("WeightUpdateVersionActivity", "check success! mIsForced:" + this.w + " minCode: " + this.x);
                break;
            default:
                LogUtil.a("WeightUpdateVersionActivity", "updateAppStateOne default case " + i);
                break;
        }
    }

    private void a(int i, String str) {
        if (i != 20) {
            switch (i) {
                case 51:
                    LogUtil.a("WeightUpdateVersionActivity", "STATE_FETCH_CHANGELOG_START:mUpdateStatus = " + this.ar.s());
                    int s = this.ar.s();
                    oaj oajVar = this.ar;
                    if (s == 1) {
                        oajVar.e(2);
                        break;
                    }
                    break;
                case 52:
                    LogUtil.a("WeightUpdateVersionActivity", "STATE_FETCH_CHANGELOG_FAILED: mUpdateStatus = " + this.ar.s());
                    if (this.ar.s() == 2) {
                        a(this.g.getString(R.string._2130841545_res_0x7f020fc9));
                        break;
                    }
                    break;
                case 53:
                    LogUtil.a("WeightUpdateVersionActivity", "STATE_FETCH_CHANGELOG_SUCCESS: mUpdateStatus = " + this.ar.s());
                    int s2 = this.ar.s();
                    oaj oajVar2 = this.ar;
                    if (s2 == 2) {
                        oajVar2.d(oajVar2.h(str));
                        LogUtil.a("WeightUpdateVersionActivity", "STATE_CHECK_NEW_VERSION_SUCCESS: mBandNewFeatureContent = " + this.ar.f());
                        r();
                        break;
                    }
                    break;
                default:
                    LogUtil.a("WeightUpdateVersionActivity", "default case " + i);
                    break;
            }
        }
        LogUtil.a("WeightUpdateVersionActivity", "STATE_DOWNLOAD_APP_START:mUpdateStatus = " + this.ar.s());
        this.ar.e(3);
    }

    private void d(int i, int i2) {
        String str;
        switch (i) {
            case 21:
                LogUtil.a("WeightUpdateVersionActivity", "STATE_DOWNLOAD_APP_PROGRESS: mUpdateStatus = " + this.ar.s());
                this.ar.e(3);
                b(i2);
                break;
            case 22:
                LogUtil.a("WeightUpdateVersionActivity", "STATE_DOWNLOAD_APP_FAILED: mWeightUpdateInteractors.mUpdateStatus = " + this.ar.s());
                if (this.ar.s() == 3) {
                    LogUtil.a("WeightUpdateVersionActivity", "STATE_DOWNLOAD_APP_FAILED: result = " + i2);
                    str = nyi.a(this.g, i2);
                } else {
                    str = "";
                }
                a(str);
                break;
            case 23:
                LogUtil.a("WeightUpdateVersionActivity", "STATE_DOWNLOAD_APP_SUCCESS: ");
                this.ar.a(true);
                p();
                i();
                break;
            default:
                LogUtil.a("WeightUpdateVersionActivity", "updateAppStateThree default case " + i);
                break;
        }
    }

    private void c(int i) {
        String string;
        if (i == 1) {
            string = this.g.getResources().getString(R.string._2130841548_res_0x7f020fcc);
            b(string);
        } else if (i == 2) {
            string = this.g.getResources().getString(R.string._2130841552_res_0x7f020fd0);
        } else if (i == 4) {
            string = this.g.getString(R.string._2130841495_res_0x7f020f97);
        } else {
            string = this.g.getResources().getString(R.string._2130841553_res_0x7f020fd1);
        }
        a(string);
    }

    private void i() {
        this.t.removeCallbacksAndMessages(null);
        if (!this.v) {
            LogUtil.h("WeightUpdateVersionActivity", "enterDeviceOtaActivity(): connected false");
            return;
        }
        cgk.d().e(this.b);
        cgt.e().b(this.b);
        if (!this.ac) {
            this.as.setClean();
        }
        LogUtil.a("WeightUpdateVersionActivity", " enterDeviceOtaActivity():");
        t();
        Intent intent = new Intent();
        intent.putExtra("productId", this.am);
        intent.putExtra("commonDeviceInfo", this.l);
        intent.putExtra("isUpdateDialog", true);
        intent.putExtra("fromsetting", this.ac);
        intent.setClass(this.g, WeightDeviceOtaActivity.class);
        try {
            this.g.startActivity(intent);
        } catch (ActivityNotFoundException unused) {
            LogUtil.b("WeightUpdateVersionActivity", "enterDeviceOtaActivity exception");
        }
        finish();
    }

    private void t() {
        HashMap hashMap = new HashMap(16);
        hashMap.put("click", "1");
        if (cpa.ae(this.am)) {
            hashMap.put("type", cpa.c.get(this.am));
            if (this.ar == null) {
                LogUtil.h("WeightUpdateVersionActivity", "setBiWifiDeviceOtaUpgrade mWeightUpdateInteractors is null");
                return;
            }
            String k = cpa.k(this.ak);
            String h = this.ar.h();
            hashMap.put("oldVersion", k);
            hashMap.put("newVersion", h);
            hashMap.put("deviceId", Integer.valueOf(cpa.j(this.am)));
            ixx.d().d(cpp.a(), AnalyticsValue.HEALTH_PLUGIN_DEVICE_HAGRID_DEVICE_UP_OTA_SUCCESS_2060041.value(), hashMap, 0);
            LogUtil.a("WeightUpdateVersionActivity", "setBi ID = ", AnalyticsValue.HEALTH_PLUGIN_DEVICE_HAGRID_DEVICE_UP_OTA_SUCCESS_2060041);
            return;
        }
        LogUtil.h("WeightUpdateVersionActivity", "setBiMiniOtaUpgrade not huawei wsp scale");
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.g = this;
        Intent intent = getIntent();
        if (intent == null) {
            LogUtil.h("WeightUpdateVersionActivity", "onCreate it is null.");
            finish();
            return;
        }
        this.aa = intent.getBooleanExtra("isUpdateDialog", false);
        this.am = intent.getStringExtra("productId");
        ContentValues contentValues = (ContentValues) intent.getParcelableExtra("commonDeviceInfo");
        this.l = contentValues;
        if (contentValues != null) {
            this.ak = contentValues.getAsString("uniqueId");
            if (TextUtils.isEmpty(this.am)) {
                this.am = this.l.getAsString("productId");
            }
        } else {
            this.ak = intent.getStringExtra("uniqueId");
        }
        this.ac = intent.getBooleanExtra("fromsetting", false);
        this.ab = intent.getBooleanExtra("user_type", false);
        this.ad = intent.getBooleanExtra("isFromNotify", false);
        LogUtil.a("WeightUpdateVersionActivity", "onCreate mIsUpdateDialog=", Boolean.valueOf(this.aa), ", mProductId=", this.am, ", mIsSetting=", Boolean.valueOf(this.ac), ", mIsMainUser=", Boolean.valueOf(this.ab), ", mIsFromNotify=", Boolean.valueOf(this.ad));
        if (TextUtils.isEmpty(this.am)) {
            finish();
            return;
        }
        if (!this.ac) {
            if (!TextUtils.isEmpty(this.ak)) {
                this.as = cld.HJ_(this, this.am, this.ak);
            } else {
                this.as = cld.HI_(this, this.am);
            }
            this.as.a();
        }
        this.t = new c(this);
        oaj a2 = oaj.a();
        this.ar = a2;
        a2.g(this.am);
        this.ar.cTG_(this.t);
        setContentView(R.layout.activity_update_version);
        n();
        g();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("action_app_check_new_version_state");
        BroadcastManagerUtil.bFC_(this.g, this.d, intentFilter, LocalBroadcast.c, null);
    }

    private void g() {
        LogUtil.a("WeightUpdateVersionActivity", "enter initUpdate()  ");
        this.ar.e(0);
        this.ar.a(true);
        EventBus.d(new EventBus.b("send_wake_up"));
        s();
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        LogUtil.a("WeightUpdateVersionActivity", "onResume()");
        super.onResume();
        if (!this.ac) {
            if (cpa.ae(this.am) && cgt.e().k() == 2) {
                this.ac = true;
            }
            cld cldVar = this.as;
            if (cldVar != null) {
                cldVar.onResume();
                this.t.removeMessages(1);
            }
        }
        if (this.ar.s() != 2) {
            EventBus.d(new EventBus.b("get_scale_version_code"));
        }
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        LogUtil.a("WeightUpdateVersionActivity", "onBackPressed()  ");
        oaj oajVar = this.ar;
        if (oajVar != null) {
            int s = oajVar.s();
            LogUtil.a("WeightUpdateVersionActivity", "onBackPressed() status = " + s);
            LogUtil.a("WeightUpdateVersionActivity", "onBackPressed() isForcedUpdate = " + this.w);
            if (s == 0) {
                super.onBackPressed();
            } else if (this.w) {
                d(this.ar.t());
                super.onBackPressed();
            } else if (s == 3) {
                x();
            } else {
                super.onBackPressed();
            }
        }
        cgk.d().e(this.b);
        cgt.e().b(this.b);
        if (!this.ac) {
            this.as.onDestroy();
        }
        if (this.am == null || !this.aa) {
            return;
        }
        MeasurableDevice d = ceo.d().d(this.ak, false);
        if (d != null) {
            dcz d2 = ResourceManager.e().d(this.am);
            MeasureKit g = cjx.e().g(d2 != null ? d2.s() : "");
            MeasureController measureController = g != null ? g.getMeasureController() : null;
            Bundle bundle = new Bundle();
            bundle.putInt("type", -1);
            bundle.putString("productId", this.am);
            bundle.putParcelable("commonDeviceInfo", this.l);
            if (measureController != null) {
                measureController.prepare(d, null, bundle);
            }
        }
        cjx.e().e(this.am, this.ak, -1);
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        cld cldVar;
        c cVar = this.t;
        if (cVar != null) {
            cVar.removeCallbacksAndMessages(null);
        }
        if (!this.ac && (cldVar = this.as) != null) {
            cldVar.setClean();
        }
        CommonUtil.a(this.g);
        super.onDestroy();
        ad();
        d();
        if (this.ar != null) {
            LogUtil.a("WeightUpdateVersionActivity", "ondestroy updateInteractor release");
            this.ar.u();
            this.ar = null;
        }
        this.g = null;
        this.i.remove();
        LogUtil.a("WeightUpdateVersionActivity", "onDestroy()");
        cgk.d().e(this.b);
        cgt.e().b(this.b);
    }

    private void d() {
        CustomTextAlertDialog customTextAlertDialog = this.s;
        if (customTextAlertDialog != null) {
            customTextAlertDialog.dismiss();
            this.s = null;
        }
        CustomTextAlertDialog customTextAlertDialog2 = this.c;
        if (customTextAlertDialog2 != null) {
            customTextAlertDialog2.dismiss();
            this.c = null;
        }
        CustomTextAlertDialog customTextAlertDialog3 = this.aq;
        if (customTextAlertDialog3 != null) {
            customTextAlertDialog3.dismiss();
            this.aq = null;
        }
    }

    private void n() {
        LogUtil.a("WeightUpdateVersionActivity", "Enter initView!");
        RoundProgressImageView roundProgressImageView = (RoundProgressImageView) nsy.cMc_(this, R.id.center_ota_download);
        this.ae = roundProgressImageView;
        roundProgressImageView.setVisibility(0);
        this.f = (CustomTitleBar) nsy.cMc_(this, R.id.update_title);
        this.e = (ImageView) nsy.cMc_(this, R.id.image_check_logo);
        RelativeLayout relativeLayout = (RelativeLayout) nsy.cMc_(this, R.id.rele_circle_download);
        this.q = relativeLayout;
        relativeLayout.setVisibility(8);
        this.af = (HealthTextView) nsy.cMc_(this, R.id.text_percent);
        HealthTextView healthTextView = (HealthTextView) nsy.cMc_(this, R.id.text_per_sign);
        this.ai = healthTextView;
        healthTextView.setText("%");
        this.ai.setVisibility(8);
        this.j = (HealthTextView) nsy.cMc_(this, R.id.text_circle_tip);
        this.p = (LinearLayout) nsy.cMc_(this, R.id.rela_failed);
        HealthButton healthButton = (HealthButton) nsy.cMc_(this, R.id.button);
        this.f9268a = healthButton;
        healthButton.setOnClickListener(this);
        this.f9268a.setClickable(true);
        this.f9268a.setBackground(getResources().getDrawable(R.drawable.button_background_emphasize));
        this.ah = (HealthTextView) nsy.cMc_(this, R.id.text_new_version_tip);
        LinearLayout linearLayout = (LinearLayout) nsy.cMc_(this, R.id.lin_tip);
        this.aj = linearLayout;
        linearLayout.setVisibility(8);
        this.an = (HealthTextView) nsy.cMc_(this, R.id.text_tip);
        this.al = (HealthTextView) nsy.cMc_(this, R.id.text_tip_content);
        this.z = (HealthTextView) nsy.cMc_(this, R.id.text_new_feature_content);
        LinearLayout linearLayout2 = (LinearLayout) nsy.cMc_(this, R.id.lin_new_feature);
        this.ag = linearLayout2;
        linearLayout2.setVisibility(8);
        HealthDivider healthDivider = (HealthDivider) nsy.cMc_(this, R.id.imageview_line);
        this.u = healthDivider;
        healthDivider.setVisibility(8);
        this.m = (HealthTextView) nsy.cMc_(this, R.id.failed_message);
        if (cpa.ae(this.am)) {
            this.f.setTitleText(this.g.getResources().getString(R.string.IDS_device_scale_device_firmware_version));
        } else {
            this.f.setTitleText(this.g.getResources().getString(R.string.IDS_device_wear_home_device_ota_upgrade));
        }
        this.f.setLeftButtonOnClickListener(new View.OnClickListener() { // from class: com.huawei.ui.device.activity.update.WeightUpdateVersionActivity.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                WeightUpdateVersionActivity.this.onBackPressed();
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        k();
        u();
        if (!cpa.x(this.am) || this.ab) {
            return;
        }
        this.f9268a.setVisibility(8);
    }

    private void k() {
        this.k = (RelativeLayout) nsy.cMc_(this, R.id.rela_device_version);
        this.r = (HealthTextView) nsy.cMc_(this, R.id.text_device_version);
        this.o = (HealthTextView) nsy.cMc_(this, R.id.text_device_version_num);
        this.n = (HealthTextView) nsy.cMc_(this, R.id.text_device_version_size);
        this.k.setVisibility(0);
        String n = this.ar.n();
        this.h = n;
        if (TextUtils.isEmpty(n)) {
            this.h = cpa.k(this.ak);
        }
        if (TextUtils.isEmpty(this.h) || "0".equals(this.h)) {
            this.h = cpa.k(this.am);
        }
        if (TextUtils.isEmpty(this.h) || "0".equals(this.h)) {
            return;
        }
        this.o.setText(this.h);
    }

    private void u() {
        LogUtil.a("WeightUpdateVersionActivity", "Enter showDeviceType() deviceType " + this.ar.k());
        dcz b = this.ar.b(this.am);
        if (b != null) {
            if (b.e().size() <= 0) {
                this.e.setImageResource(dcx.a(b.n().d()));
            } else {
                this.e.setImageBitmap(dcx.TK_(dcq.b().a(b.t(), b.n().d())));
            }
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        int id = view.getId();
        LogUtil.a("WeightUpdateVersionActivity", "onclick " + this.ar.s());
        if (id == R.id.button) {
            if (this.ar.s() == 0) {
                LogUtil.a("WeightUpdateVersionActivity", "mWeightUpdateInteractors.STATUS_INITIAL ");
                IntentFilter intentFilter = new IntentFilter();
                intentFilter.addAction("action_app_check_new_version_state");
                BroadcastManagerUtil.bFC_(this.g, this.d, intentFilter, LocalBroadcast.c, null);
                s();
                if (!this.v) {
                    v();
                }
                q();
                ViewClickInstrumentation.clickOnView(view);
                return;
            }
            boolean z = cpa.w(this.am) && CommonUtil.d(this.h, "1.0.1.117") < 0;
            if (cpa.x(this.am) && (!cpa.c(this.am, this.ak) || z)) {
                j();
                ViewClickInstrumentation.clickOnView(view);
                return;
            } else if (this.ar.s() == 2) {
                LogUtil.a("WeightUpdateVersionActivity", "mWeightUpdateInteractors.STATUS_PULL_CHANGE_LOG");
                EventBus.d(new EventBus.b("send_wake_up"));
                f();
                this.t.sendEmptyMessageDelayed(5, 45000L);
            }
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void v() {
        MeasurableDevice c2;
        if (this.ad && this.v) {
            this.ar.f(this.h);
            if (!TextUtils.isEmpty(this.ak)) {
                c2 = ceo.d().d(this.ak, false);
            } else {
                c2 = ceo.d().c(this.am);
            }
            if (c2 != null) {
                this.ar.i(c2.getSerialNumber());
            }
            this.ar.v();
            return;
        }
        EventBus.d(new EventBus.b("get_scale_version_code"));
    }

    private void j() {
        setResult(-1);
        finish();
    }

    protected static class c extends BaseHandler<WeightUpdateVersionActivity> {
        c(WeightUpdateVersionActivity weightUpdateVersionActivity) {
            super(weightUpdateVersionActivity);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.huawei.haf.handler.BaseHandler
        /* renamed from: cTe_, reason: merged with bridge method [inline-methods] */
        public void handleMessageWhenReferenceNotNull(WeightUpdateVersionActivity weightUpdateVersionActivity, Message message) {
            if (weightUpdateVersionActivity == null) {
            }
            switch (message.what) {
                case 1:
                    weightUpdateVersionActivity.a(weightUpdateVersionActivity.g.getString(R.string._2130841439_res_0x7f020f5f));
                    break;
                case 2:
                    break;
                case 3:
                    if (message.obj instanceof String) {
                        weightUpdateVersionActivity.c((String) message.obj);
                    }
                    weightUpdateVersionActivity.q();
                    break;
                case 4:
                    weightUpdateVersionActivity.l();
                    break;
                case 5:
                    EventBus.d(new EventBus.b("send_wake_up"));
                    sendEmptyMessageDelayed(5, 45000L);
                    break;
                case 6:
                    weightUpdateVersionActivity.y();
                    break;
                default:
                    LogUtil.a("WeightUpdateVersionActivity", "handleMessageWhenReferenceNotNull default.");
                    break;
            }
        }
    }

    private void s() {
        this.y = false;
        l();
        c();
        HashMap hashMap = new HashMap(0);
        hashMap.put("state", "device");
        ixx.d().d(BaseApplication.getContext(), AnalyticsValue.HOME_1010032.value(), hashMap, 0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void q() {
        HashMap hashMap = new HashMap(16);
        hashMap.put("type", cpa.c.get(this.am));
        HealthTextView healthTextView = this.o;
        hashMap.put("deviceVersion", (healthTextView == null || healthTextView.getText() == null) ? "" : this.o.getText().toString());
        hashMap.put("deviceId", Integer.valueOf(cpa.j(this.am)));
        ixx.d().d(cpp.a(), AnalyticsValue.HEALTH_PLUGIN_DEVICE_HAGRID_DEVICE_CHECK_UP_OTA_SUCCESS_2060040.value(), hashMap, 0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void l() {
        LogUtil.a("WeightUpdateVersionActivity", "Enter initViewForCheck! ");
        if (cpa.ae(this.am)) {
            this.f.setTitleText(this.g.getResources().getString(R.string.IDS_device_scale_device_firmware_version));
        } else {
            this.f.setTitleText(this.g.getResources().getString(R.string.IDS_device_wear_home_device_ota_upgrade));
        }
        this.k.setVisibility(0);
        if (this.ar.n() != null) {
            String n = this.ar.n();
            this.h = n;
            c(n);
        }
        u();
        this.e.setVisibility(0);
        if (!this.ae.getIsRunning()) {
            this.ae.d();
        }
        this.q.setVisibility(8);
        this.p.setVisibility(8);
        this.aj.setVisibility(8);
        this.u.setVisibility(8);
        this.f9268a.setBackground(getResources().getDrawable(R.drawable.button_background_emphasize_disable));
        this.f9268a.setClickable(false);
        this.f9268a.setText(R.string._2130841461_res_0x7f020f75);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void y() {
        this.ae.e();
    }

    private void m() {
        LogUtil.a("WeightUpdateVersionActivity", "Enter initViewForNoVersion! ");
        this.y = true;
        this.f9268a.setBackground(getResources().getDrawable(R.drawable.button_background_emphasize));
        this.f9268a.setClickable(true);
        this.f9268a.setText(R.string._2130841456_res_0x7f020f70);
        this.ah.setText(R.string._2130841462_res_0x7f020f76);
        this.ah.setVisibility(0);
        this.ae.e();
        kxp.c().c(this.am, this.ak);
        this.ar.e(0);
    }

    private void b(String str) {
        LogUtil.a("WeightUpdateVersionActivity", "Enter initViewForNoNetwork! ");
        this.f9268a.setBackground(getResources().getDrawable(R.drawable.button_background_emphasize));
        this.f9268a.setClickable(true);
        this.f9268a.setText(R.string._2130841467_res_0x7f020f7b);
        this.aj.setVisibility(0);
        this.an.setText(this.g.getString(R.string.IDS_service_area_notice_title));
        this.al.setText(str);
        this.u.setVisibility(0);
        this.ae.e();
    }

    private void r() {
        LogUtil.a("WeightUpdateVersionActivity", "Enter showAppNewVersion");
        if (cpa.x(this.am) && !this.ab) {
            LogUtil.a("WeightUpdateVersionActivity", "The new version is not displayed for sub-users");
            this.ae.e();
            return;
        }
        this.f9268a.setBackground(getResources().getDrawable(R.drawable.button_background_emphasize));
        this.f9268a.setClickable(true);
        boolean z = cpa.w(this.am) && CommonUtil.d(this.h, "1.0.1.117") < 0;
        if (cpa.x(this.am)) {
            if (!cpa.c(this.am, this.ak) || z) {
                this.f9268a.setText(R.string.IDS_wlan_config_device);
            } else {
                this.f9268a.setText(R.string.IDS_device_manager_update_health);
            }
        } else {
            this.f9268a.setText(R.string.IDS_device_manager_update_health);
        }
        this.ah.setText(R.string._2130841458_res_0x7f020f72);
        this.ah.setVisibility(8);
        this.ag.setVisibility(0);
        this.k.setVisibility(0);
        this.r.setText(R.string._2130841458_res_0x7f020f72);
        this.o.setText(this.ar.h());
        this.n.setText(this.ar.o());
        this.n.setVisibility(0);
        this.z.setText(this.ar.f());
        this.ae.e();
    }

    private void o() {
        LogUtil.a("WeightUpdateVersionActivity", "Enter initViewForDownload!");
        this.e.setVisibility(8);
        this.p.setVisibility(8);
        this.aj.setVisibility(8);
        this.q.setVisibility(0);
        b(0);
        this.u.setVisibility(8);
        this.f9268a.setBackground(getResources().getDrawable(R.drawable.button_background_emphasize_disable));
        this.f9268a.setClickable(false);
        this.ah.setVisibility(4);
    }

    private void b(int i) {
        LogUtil.a("WeightUpdateVersionActivity", "Enter showAppDownloadProgress progress = " + i);
        this.j.setText(R.string._2130841544_res_0x7f020fc8);
        String e = UnitUtil.e((double) i, 2, 0);
        LogUtil.a("WeightUpdateVersionActivity", "percentNum = " + e + "text:" + ((Object) UnitUtil.bCR_(this.g, "[\\d]", e, R.style.percent_number_style_num, R.style.percent_number_style_sign)));
        this.af.setText(UnitUtil.bCR_(this.g, "[\\d]", e, R.style.percent_number_style_num, R.style.percent_number_style_sign));
        this.ae.a((float) i);
    }

    private void p() {
        this.y = false;
        this.j.setText(R.string._2130841497_res_0x7f020f99);
        this.j.setVisibility(0);
        b(100);
        this.f9268a.setBackground(getResources().getDrawable(R.drawable.button_background_emphasize_disable));
        this.f9268a.setClickable(false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(String str) {
        if (this.g == null || this.y) {
            LogUtil.b("WeightUpdateVersionActivity", "showErrorMsg error, tipText=", str, " mIsFailed=", Boolean.valueOf(this.y));
            return;
        }
        this.y = true;
        LogUtil.a("WeightUpdateVersionActivity", "showErrorMsg(): tipText = " + str);
        this.ae.e();
        this.e.setVisibility(8);
        b(0);
        this.q.setVisibility(8);
        this.p.setVisibility(0);
        this.f9268a.setBackground(getResources().getDrawable(R.drawable.button_background_emphasize));
        this.f9268a.setClickable(true);
        this.f9268a.setText(this.g.getText(R.string._2130841467_res_0x7f020f7b));
        this.aj.setVisibility(0);
        this.an.setText(this.g.getString(R.string._2130841512_res_0x7f020fa8));
        if (str.equals(this.g.getResources().getString(R.string._2130841439_res_0x7f020f5f))) {
            this.al.setText(str + System.lineSeparator() + this.g.getResources().getString(R.string.IDS_device_scale_connect_tip));
        } else {
            this.al.setText(str);
        }
        this.u.setVisibility(0);
        int s = this.ar.s();
        oaj oajVar = this.ar;
        if (s != 1) {
            int s2 = oajVar.s();
            oaj oajVar2 = this.ar;
            if (s2 != 2) {
                if (oajVar2.s() == 3) {
                    this.m.setText(R.string._2130841543_res_0x7f020fc7);
                } else {
                    this.m.setText("");
                }
                LogUtil.a("WeightUpdateVersionActivity", "showErrorMsg() mUpdateStatus :" + this.ar.s());
                this.ar.e(0);
            }
        }
        this.m.setText(R.string._2130841829_res_0x7f0210e5);
        LogUtil.a("WeightUpdateVersionActivity", "showErrorMsg() mUpdateStatus :" + this.ar.s());
        this.ar.e(0);
    }

    private void c() {
        LogUtil.a("WeightUpdateVersionActivity", "doCheckAppNewVersion: mWeightUpdateInteractors.mUpdateStatus = " + this.ar.s() + "mIsConnected:" + this.v);
        int s = this.ar.s();
        oaj oajVar = this.ar;
        if (s == 0) {
            oajVar.e(1);
        }
        h();
    }

    private void f() {
        if (this.x > 0) {
            int d = CommonUtil.d(this.g);
            LogUtil.a("WeightUpdateVersionActivity", "curversioncode :" + d);
            if (this.x > d) {
                a();
                return;
            }
        }
        boolean b = this.ar.b(r0.j());
        LogUtil.a("WeightUpdateVersionActivity", "handleAppNewVersionOk: checkMemory = " + b);
        if (!b) {
            a(this.g.getString(R.string._2130841547_res_0x7f020fcb));
            return;
        }
        boolean y = this.ar.y();
        LogUtil.a("WeightUpdateVersionActivity", "handleAppNewVersionOk: wifiConnected = " + y);
        if (!y) {
            if (this.ar.p()) {
                w();
                return;
            } else {
                c(1);
                return;
            }
        }
        b();
    }

    private void w() {
        Context context = this.g;
        if (context != null && this.aq == null && c(context)) {
            CustomTextAlertDialog a2 = new CustomTextAlertDialog.Builder(this.g).b(R.string.IDS_service_area_notice_title).d(R.string._2130841457_res_0x7f020f71).cyU_(R.string._2130841549_res_0x7f020fcd, new View.OnClickListener() { // from class: com.huawei.ui.device.activity.update.WeightUpdateVersionActivity.4
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    LogUtil.c("WeightUpdateVersionActivity", "start wifiDialog, user click Negative button! ");
                    WeightUpdateVersionActivity.this.aq.dismiss();
                    WeightUpdateVersionActivity.this.aq = null;
                    WeightUpdateVersionActivity.this.b();
                    ViewClickInstrumentation.clickOnView(view);
                }
            }).cyR_(R.string._2130841130_res_0x7f020e2a, new View.OnClickListener() { // from class: com.huawei.ui.device.activity.update.WeightUpdateVersionActivity.2
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    LogUtil.c("WeightUpdateVersionActivity", "start wifiDialog, user click Positive button!");
                    WeightUpdateVersionActivity.this.aq.dismiss();
                    WeightUpdateVersionActivity.this.aq = null;
                    ViewClickInstrumentation.clickOnView(view);
                }
            }).a();
            this.aq = a2;
            a2.setCancelable(true);
            this.aq.show();
        }
    }

    private void x() {
        Context context = this.g;
        if (context != null && this.s == null && c(context)) {
            CustomTextAlertDialog a2 = new CustomTextAlertDialog.Builder(this.g).b(R.string.IDS_service_area_notice_title).d(R.string._2130841491_res_0x7f020f93).cyU_(R.string._2130841131_res_0x7f020e2b, new View.OnClickListener() { // from class: com.huawei.ui.device.activity.update.WeightUpdateVersionActivity.6
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    LogUtil.c("WeightUpdateVersionActivity", "start showExitUpdateDialog, user click Negative button! ");
                    int s = WeightUpdateVersionActivity.this.ar.s();
                    oaj unused = WeightUpdateVersionActivity.this.ar;
                    if (s == 3) {
                        WeightUpdateVersionActivity.this.ar.e();
                        oaj oajVar = WeightUpdateVersionActivity.this.ar;
                        oaj unused2 = WeightUpdateVersionActivity.this.ar;
                        oajVar.e(0);
                        LogUtil.a("WeightUpdateVersionActivity", "start showExitUpdateDialog,cancle downloading file!");
                        if (WeightUpdateVersionActivity.this.w) {
                            WeightUpdateVersionActivity weightUpdateVersionActivity = WeightUpdateVersionActivity.this;
                            weightUpdateVersionActivity.d(weightUpdateVersionActivity.ar.t());
                        }
                    }
                    WeightUpdateVersionActivity.this.s.dismiss();
                    WeightUpdateVersionActivity.this.s = null;
                    WeightUpdateVersionActivity.this.finish();
                    ViewClickInstrumentation.clickOnView(view);
                }
            }).cyR_(R.string._2130841130_res_0x7f020e2a, new View.OnClickListener() { // from class: com.huawei.ui.device.activity.update.WeightUpdateVersionActivity.9
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    LogUtil.c("WeightUpdateVersionActivity", "showExitUpdateDialog ok click");
                    WeightUpdateVersionActivity.this.s.dismiss();
                    WeightUpdateVersionActivity.this.s = null;
                    ViewClickInstrumentation.clickOnView(view);
                }
            }).a();
            this.s = a2;
            a2.setCancelable(true);
            this.s.show();
            return;
        }
        Context context2 = this.g;
        if (context2 != null && this.s != null && c(context2)) {
            this.s.show();
        } else {
            LogUtil.h("WeightUpdateVersionActivity", "mContext is null");
        }
    }

    private void h() {
        MeasurableDevice c2;
        MeasureController measureController;
        Bundle bundle = new Bundle();
        bundle.putInt("type", -1);
        bundle.putString("productId", this.am);
        bundle.putParcelable("commonDeviceInfo", this.l);
        if (!TextUtils.isEmpty(this.ak)) {
            c2 = ceo.d().d(this.ak, false);
        } else {
            c2 = ceo.d().c(this.am);
        }
        if (c2 != null) {
            this.ar.i(c2.getAddress());
            dcz d = ResourceManager.e().d(this.am);
            MeasureKit e = d != null ? cfl.a().e(d.s()) : null;
            if (e == null || (measureController = e.getMeasureController()) == null) {
                return;
            }
            this.i.set(true);
            measureController.prepare(c2, this.b, bundle);
            this.i.set(false);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b() {
        LogUtil.a("WeightUpdateVersionActivity", "doDownloadAppFile: mUpdateStatus = " + this.ar.s());
        o();
        this.ar.e();
        this.ar.i();
    }

    private void ad() {
        BroadcastReceiver broadcastReceiver = this.d;
        if (broadcastReceiver == null) {
            return;
        }
        try {
            Context context = this.g;
            if (context != null) {
                context.unregisterReceiver(broadcastReceiver);
            }
            this.d = null;
        } catch (IllegalArgumentException e) {
            LogUtil.a("WeightUpdateVersionActivity", e.getMessage());
        }
    }

    public void a() {
        LogUtil.a("WeightUpdateVersionActivity", "Enter showAppVersionIsLow");
        Context context = this.g;
        if (context != null && this.c == null && c(context)) {
            CustomTextAlertDialog a2 = new CustomTextAlertDialog.Builder(this.g).b(String.format(Locale.ENGLISH, this.g.getString(R.string._2130841541_res_0x7f020fc5), this.ar.t())).d(R.string._2130841540_res_0x7f020fc4).cyU_(R.string._2130841550_res_0x7f020fce, new View.OnClickListener() { // from class: com.huawei.ui.device.activity.update.WeightUpdateVersionActivity.7
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    LogUtil.c("WeightUpdateVersionActivity", "start showAppIsLowdialog, user click Negative button! ");
                    WeightUpdateVersionActivity.this.c.dismiss();
                    WeightUpdateVersionActivity.this.c = null;
                    WeightUpdateVersionActivity.this.e();
                    ViewClickInstrumentation.clickOnView(view);
                }
            }).cyR_(R.string._2130841130_res_0x7f020e2a, new View.OnClickListener() { // from class: com.huawei.ui.device.activity.update.WeightUpdateVersionActivity.10
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    LogUtil.c("WeightUpdateVersionActivity", "start showAppIsLowdialog, user click Positive button!");
                    WeightUpdateVersionActivity.this.c.dismiss();
                    WeightUpdateVersionActivity.this.c = null;
                    WeightUpdateVersionActivity.this.finish();
                    ViewClickInstrumentation.clickOnView(view);
                }
            }).a();
            this.c = a2;
            a2.setCancelable(true);
            this.c.show();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e() {
        LogUtil.a("WeightUpdateVersionActivity", " enterUpdateActivity():");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(String str) {
        LogUtil.a("WeightUpdateVersionActivity", "showForcedUpdateDialog deviceName:" + str);
        Intent intent = new Intent();
        intent.putExtra("deviceName", str);
        intent.putExtra("isForced", true);
        intent.putExtra(ParamConstants.CallbackMethod.ON_SHOW, false);
        intent.putExtra("isScale", true);
        intent.putExtra("productId", this.am);
        intent.putExtra("user_type", this.ab);
        intent.putExtra("commonDeviceInfo", this.l);
        intent.setClass(this.g, BandUpdateDialogActivity.class);
        try {
            this.g.startActivity(intent);
        } catch (ActivityNotFoundException unused) {
            LogUtil.b("WeightUpdateVersionActivity", "showForcedUpdateManualDialog exception");
        }
    }

    private boolean c(Context context) {
        if (context instanceof BaseActivity) {
            BaseActivity baseActivity = (BaseActivity) context;
            if (!baseActivity.isFinishing() && !baseActivity.isDestroyed()) {
                return true;
            }
            LogUtil.h("WeightUpdateVersionActivity", "isLiving: activity is isFinishing | isDestroyed.");
            return false;
        }
        LogUtil.h("WeightUpdateVersionActivity", "isLiving: context isn't BaseActivity.");
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(String str) {
        if (this.r == null || this.o == null) {
            LogUtil.h("WeightUpdateVersionActivity", "mDeviceVersion view is null");
        } else if (getResources().getString(R.string._2130841459_res_0x7f020f73).equals(this.r.getText().toString())) {
            this.o.setText(str);
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
