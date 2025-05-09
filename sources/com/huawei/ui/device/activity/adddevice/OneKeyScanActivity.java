package com.huawei.ui.device.activity.adddevice;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.ActivityNotFoundException;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LayoutAnimationController;
import android.view.animation.TranslateAnimation;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.IntentSenderRequest;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.SimpleItemAnimator;
import com.google.android.gms.wearable.PutDataRequest;
import com.huawei.devicesdk.entity.ScanMode;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.haf.design.pattern.ObserverManagerUtil;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.R;
import com.huawei.health.device.connectivity.comm.MeasurableDevice;
import com.huawei.health.device.model.HealthDevice;
import com.huawei.health.devicemgr.api.constant.HwGetDevicesMode;
import com.huawei.health.devicemgr.api.mainprocess.DownloadManagerApi;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.health.devicemgr.business.entity.HwGetDevicesParameter;
import com.huawei.health.devicemgr.business.entity.downloadresouce.DeviceDownloadSourceInfo;
import com.huawei.health.devicemgr.business.entity.downloadresouce.DownloadResultCallBack;
import com.huawei.health.ecologydevice.ui.util.PermissionDialogHelper;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwbtsdk.btdatatype.callback.BtSwitchStateCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwcommonmodel.utils.PermissionUtil;
import com.huawei.hwcommonmodel.utils.permission.PermissionsResultAction;
import com.huawei.hwdevice.phoneprocess.mgr.notification.SensitivePermissionStatus;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.nfc.PluginPayAdapter;
import com.huawei.operation.h5pro.jsmodules.device.BasePairManagerJsApi;
import com.huawei.pluginmgr.hwwear.bean.DeviceFittingsBean;
import com.huawei.ui.commonui.adapter.BaseRecyclerAdapter;
import com.huawei.ui.commonui.adapter.RecyclerHolder;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.button.HealthButton;
import com.huawei.ui.commonui.dialog.NoTitleCustomAlertDialog;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.progressbar.HealthProgressBar;
import com.huawei.ui.commonui.recycleview.HealthRecycleView;
import com.huawei.ui.commonui.scrollview.HealthScrollView;
import com.huawei.ui.commonui.titlebar.CustomTitleBar;
import com.huawei.ui.commonui.utils.CustomPermissionAction;
import com.huawei.ui.device.adapter.ScanDeviceAdapter;
import com.huawei.ui.device.utlis.BluetoothPermisionUtils;
import com.huawei.ui.device.views.adddevice.RadarImageView;
import defpackage.bgb;
import defpackage.bjf;
import defpackage.bkw;
import defpackage.bmw;
import defpackage.ceo;
import defpackage.cez;
import defpackage.cpa;
import defpackage.cpl;
import defpackage.cun;
import defpackage.cuy;
import defpackage.cva;
import defpackage.cve;
import defpackage.cvl;
import defpackage.cvm;
import defpackage.cvt;
import defpackage.cvy;
import defpackage.dds;
import defpackage.dfe;
import defpackage.dib;
import defpackage.dks;
import defpackage.fhw;
import defpackage.iyl;
import defpackage.jeg;
import defpackage.jfu;
import defpackage.jkj;
import defpackage.koq;
import defpackage.mst;
import defpackage.msx;
import defpackage.nrr;
import defpackage.nsn;
import defpackage.nsy;
import defpackage.ntt;
import defpackage.nuc;
import defpackage.nue;
import defpackage.nyr;
import defpackage.oae;
import defpackage.oau;
import defpackage.obb;
import defpackage.obs;
import defpackage.sqo;
import health.compact.a.BroadcastManagerUtil;
import health.compact.a.CloudUtils;
import health.compact.a.CommonUtil;
import health.compact.a.CompileParameterUtil;
import health.compact.a.LanguageUtil;
import health.compact.a.Services;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import health.compact.a.util.LogUtil;
import java.io.File;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/* loaded from: classes6.dex */
public class OneKeyScanActivity extends BaseActivity implements DownloadResultCallBack {

    /* renamed from: a, reason: collision with root package name */
    private NoTitleCustomAlertDialog f9021a;
    private boolean aa;
    private boolean ab;
    private boolean ac;
    private String af;
    private nyr ag;
    private nyr ai;
    private HealthButton aj;
    private a al;
    private LinearLayout am;
    private LinearLayout ap;
    private obb aq;
    private int as;
    private LinearLayout at;
    private HealthTextView au;
    private HealthButton av;
    private RelativeLayout aw;
    private HealthRecycleView ax;
    private RadarImageView az;
    private int b;
    private HealthTextView ba;
    private RelativeLayout bb;
    private int bc;
    private HealthTextView bd;
    private int be;
    private BtSwitchStateCallback bf;
    private LinearLayout bg;
    private LinearLayout bh;
    private CustomTitleBar bi;
    private HealthTextView bj;
    private HealthTextView bk;
    private HealthTextView bl;
    private HealthTextView bm;
    private nuc bo;
    private int br;
    private LinearLayout c;
    private ScanDeviceAdapter d;
    private String e;
    private String g;
    private String h;
    private int i;
    private Context j;
    private ActivityResultLauncher<IntentSenderRequest> l;
    private String o;
    private int p;
    private String q;
    private HealthProgressBar r;
    private String s;
    private String t;
    private boolean z;
    private boolean w = false;
    private boolean v = false;
    private boolean ah = false;
    private int ar = 126;
    private PermissionsResultAction ao = null;
    private List<String> bq = new ArrayList(16);
    private List<String> n = new ArrayList(16);
    private Map<nyr, Integer> ay = new HashMap(16);
    private List<cve> f = new ArrayList(16);
    private int m = 0;
    private int k = 0;
    private boolean ae = false;
    private boolean x = false;
    private boolean y = false;
    private boolean ad = true;
    private boolean u = false;
    private BroadcastReceiver ak = new BroadcastReceiver() { // from class: com.huawei.ui.device.activity.adddevice.OneKeyScanActivity.2
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (intent == null || intent.getAction() == null) {
                LogUtil.c("OneKeyScanActivity", "mPairBroadcastReceiver intent is null");
                return;
            }
            if ("com.huawei.health.action.PAIR_DEVICE_SUCCESS".equals(intent.getAction())) {
                LogUtil.d("OneKeyScanActivity", "pair device success");
                if (OneKeyScanActivity.this.isFinishing()) {
                    LogUtil.d("OneKeyScanActivity", "this activity is finish");
                    return;
                }
                if (!intent.getBooleanExtra(BasePairManagerJsApi.FROM_H5_KEY, false)) {
                    if (!OneKeyScanActivity.this.u) {
                        OneKeyScanActivity.this.finish();
                    }
                    if (!TextUtils.isEmpty(OneKeyScanActivity.this.af) && (OneKeyScanActivity.this.af.startsWith(PutDataRequest.WEAR_URI_SCHEME) || OneKeyScanActivity.this.af.startsWith("SPORTS_GENIE"))) {
                        LogUtil.d("OneKeyScanActivity", "start connect device item onclick");
                        return;
                    } else {
                        OneKeyScanActivity.this.finish();
                        return;
                    }
                }
                OneKeyScanActivity.this.finish();
            }
        }
    };
    private BroadcastReceiver an = new BroadcastReceiver() { // from class: com.huawei.ui.device.activity.adddevice.OneKeyScanActivity.11
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (intent == null || intent.getAction() == null) {
                LogUtil.c("OneKeyScanActivity", "NetBroadcastReceiver intent is null");
                return;
            }
            try {
                Thread.sleep(500L);
            } catch (InterruptedException unused) {
                LogUtil.e("OneKeyScanActivity", "sleep Exception");
            }
            if ("android.net.conn.CONNECTIVITY_CHANGE".equals(intent.getAction())) {
                if (CommonUtil.aa(OneKeyScanActivity.this.j)) {
                    if (OneKeyScanActivity.this.ad && !OneKeyScanActivity.this.ae) {
                        OneKeyScanActivity.this.az.e();
                        OneKeyScanActivity.this.bk.setText(R.string._2130841767_res_0x7f0210a7);
                        OneKeyScanActivity.this.bl.setText(R.string.IDS_device_mgr_device_scaning_title);
                        OneKeyScanActivity.this.bl.setTextColor(OneKeyScanActivity.this.getResources().getColor(R.color._2131299241_res_0x7f090ba9));
                        OneKeyScanActivity.this.bl.setTextSize(R.dimen._2131365061_res_0x7f0a0cc5);
                        OneKeyScanActivity.this.bj.setVisibility(8);
                    }
                    OneKeyScanActivity.this.h();
                    try {
                        if (OneKeyScanActivity.this.an != null) {
                            OneKeyScanActivity oneKeyScanActivity = OneKeyScanActivity.this;
                            oneKeyScanActivity.unregisterReceiver(oneKeyScanActivity.an);
                            OneKeyScanActivity.this.an = null;
                            return;
                        }
                        return;
                    } catch (IllegalArgumentException unused2) {
                        LogUtil.e("OneKeyScanActivity", "unregisterBroadcastReceiver is error");
                        return;
                    }
                }
                LogUtil.c("OneKeyScanActivity", "net work is error");
            }
        }
    };

    @Override // com.huawei.ui.commonui.base.BaseActivity
    public void initViewTahiti() {
    }

    static /* synthetic */ int as(OneKeyScanActivity oneKeyScanActivity) {
        int i = oneKeyScanActivity.bc;
        oneKeyScanActivity.bc = i + 1;
        return i;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(nyr nyrVar) {
        cve cveVar;
        List<cve> deviceInfoByBluetooth = ((DownloadManagerApi) Services.c("DownloadDeviceResource", DownloadManagerApi.class)).getDeviceInfoByBluetooth(nyrVar.b());
        if (cez.c()) {
            deviceInfoByBluetooth = e(deviceInfoByBluetooth, nyrVar.b(), this.bq);
        }
        if (koq.b(deviceInfoByBluetooth) && !dfe.a().e() && !this.ab) {
            LogUtil.c("OneKeyScanActivity", "infoBeans is empty");
            return;
        }
        if (!koq.b(this.bq)) {
            cveVar = c(deviceInfoByBluetooth);
        } else if (this.ab) {
            List<DeviceFittingsBean> a2 = mst.a().a(this.q, nyrVar.b());
            if (koq.b(a2)) {
                LogUtil.c("OneKeyScanActivity", "fittingDeviceList is empty");
                return;
            }
            List<msx> c = mst.a().c(this.q);
            if (koq.b(c)) {
                LogUtil.c("OneKeyScanActivity", "devicePluginInfoBeanList is empty");
                return;
            }
            cveVar = c(a2.get(0), c.get(0));
        } else {
            cveVar = deviceInfoByBluetooth.get(0);
        }
        if (cveVar == null) {
            LogUtil.c("OneKeyScanActivity", "infoBean is empty");
            return;
        }
        if (e(cveVar, nyrVar) || "4bfc5a27-f2b9-4c41-bd9b-a4a2a18f752c".equals(cveVar.ac().get(0))) {
            if (dks.j(cveVar.ac().get(0))) {
                if (dks.b(dds.c().d(), cveVar.ac().get(0))) {
                    return;
                }
            } else {
                nuc nucVar = this.bo;
                if (nucVar == null || nucVar.c(cveVar.ac().get(0), nyrVar.h())) {
                    boolean c2 = cpl.c(nyrVar.h());
                    Object[] objArr = new Object[2];
                    objArr[0] = "mUtil is null:";
                    objArr[1] = Boolean.valueOf(this.bo == null);
                    LogUtil.c("OneKeyScanActivity", objArr);
                    LogUtil.c("OneKeyScanActivity", "device is bonded, productId: ", cveVar.ac().get(0), ", uniqueId: ", CommonUtil.l(nyrVar.h()), ", bluetooth valid: ", Boolean.valueOf(c2));
                    if (c2) {
                        return;
                    }
                }
            }
            if (koq.b(this.bq) && !dks.b(this.j)) {
                return;
            }
        }
        if (cpa.f() || !nyrVar.b().contains("HUAWEI Scale 3-")) {
            a(nyrVar, cveVar);
        }
    }

    private cve c(DeviceFittingsBean deviceFittingsBean, msx msxVar) {
        cve cveVar = new cve();
        cveVar.e(deviceFittingsBean.getBluetoothMatch());
        cveVar.a(deviceFittingsBean.getBluetoothNameList());
        cveVar.d(deviceFittingsBean.getUuid());
        cveVar.d(new DeviceDownloadSourceInfo(null).cloneOldDeviceDownloadSourceInfo(deviceFittingsBean.getDeviceDownloadSourceInfo()));
        cveVar.j(msxVar.f());
        cveVar.m(msxVar.o());
        return cveVar;
    }

    private boolean e(cve cveVar, nyr nyrVar) {
        return (!koq.b(cveVar.ac()) && !TextUtils.isEmpty(cveVar.r()) && cveVar.r().startsWith("HDK")) && (!this.aa || !nyrVar.h().equals(this.s));
    }

    private static List<cve> e(List<cve> list, String str, List<String> list2) {
        if (list == null) {
            list = new ArrayList<>(16);
        }
        if (!koq.b(list2) && !TextUtils.isEmpty(str)) {
            String b = SharedPreferenceManager.b(BaseApplication.getContext(), "ecology_device_module", "ecology_device_bluetooth_name");
            if (!TextUtils.isEmpty(b) && str.toUpperCase(Locale.ENGLISH).contains(b.toUpperCase(Locale.ENGLISH))) {
                list.addAll(((DownloadManagerApi) Services.c("DownloadDeviceResource", DownloadManagerApi.class)).getDeviceList());
            }
        }
        return list;
    }

    private cve c(List<cve> list) {
        for (cve cveVar : list) {
            if (cveVar != null && !koq.b(cveVar.ac())) {
                ArrayList arrayList = new ArrayList(cveVar.ac());
                arrayList.retainAll(this.bq);
                if (arrayList.size() != 0) {
                    return cveVar;
                }
            }
        }
        return null;
    }

    private void a(nyr nyrVar, cve cveVar) {
        if (this.ay.containsKey(nyrVar)) {
            LogUtil.d("OneKeyScanActivity", "scanDevice already has item: ", nyrVar.b());
            return;
        }
        nyrVar.b(cveVar.k());
        nyrVar.e(cveVar.r());
        if ("b81581e1-5c5a-4e30-b099-0a448ef56a24".equals(cveVar.ac().get(0)) && cveVar.aa() != null && cveVar.aa().get("b81581e1-5c5a-4e30-b099-0a448ef56a24") != null) {
            ArrayList arrayList = new ArrayList(16);
            arrayList.addAll(cveVar.aa().get("b81581e1-5c5a-4e30-b099-0a448ef56a24").f());
            nyrVar.a(arrayList);
        } else {
            nyrVar.a(cveVar.ac());
        }
        LogUtil.d("OneKeyScanActivity", "compareItem item getUuidList: ", nyrVar.k().toString());
        nyrVar.a(cveVar.y());
        nyrVar.d(d(cveVar.n()));
        nyrVar.a(cveVar.p());
        nyrVar.b(cveVar.m());
        nyrVar.d(cveVar.s());
        if (cveVar.l() != null && cveVar.l().size() > 0) {
            nyrVar.d(cveVar.l().get(0).d());
        } else {
            nyrVar.d(cveVar.s());
        }
        nyrVar.e(cveVar.x());
        this.ay.put(nyrVar, Integer.valueOf(nyrVar.o()));
        if (this.bo == null) {
            this.bo = new nuc();
        }
        ArrayList<nyr> c = this.bo.c(this.ay);
        this.d.e(c);
        if (c.size() <= 2 || !this.z) {
            return;
        }
        ah();
    }

    private void ah() {
        this.z = false;
        ab();
        d();
        b();
        ValueAnimator cOH_ = cOH_();
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this.az, "translationX", 0.0f, c());
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.setDuration(350L);
        animatorSet.setInterpolator(new obs(0.2f, 0.0f, 0.2f, 1.0f));
        animatorSet.play(cOH_).with(ofFloat);
        animatorSet.addListener(new Animator.AnimatorListener() { // from class: com.huawei.ui.device.activity.adddevice.OneKeyScanActivity.16
            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationStart(Animator animator) {
                LogUtil.d("OneKeyScanActivity", "onAnimationStart");
                OneKeyScanActivity.this.bd.setVisibility(8);
                OneKeyScanActivity.this.bm.setText(R.string._2130841767_res_0x7f0210a7);
                OneKeyScanActivity.this.d(true);
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                LogUtil.d("OneKeyScanActivity", "onAnimationEnd");
                OneKeyScanActivity.this.d(false);
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationCancel(Animator animator) {
                LogUtil.b("OneKeyScanActivity", "onAnimationCancel");
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationRepeat(Animator animator) {
                LogUtil.b("OneKeyScanActivity", "onAnimationRepeat");
            }
        });
        animatorSet.start();
    }

    private int c() {
        int e;
        if (this.br != 0) {
            if (LanguageUtil.bc(this.j)) {
                e = (this.br / 2) - nrr.e(this.j, 24.0f);
            } else {
                e = ((-this.br) / 2) + nrr.e(this.j, 24.0f);
            }
        } else if (LanguageUtil.bc(this.j)) {
            e = nrr.e(this.j, 144.0f);
        } else {
            e = 0 - nrr.e(this.j, 144.0f);
        }
        if (!(this.az.getLayoutParams() instanceof ViewGroup.MarginLayoutParams)) {
            return e;
        }
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) this.az.getLayoutParams();
        marginLayoutParams.leftMargin += ((Integer) BaseActivity.getSafeRegionWidth().first).intValue();
        return e + marginLayoutParams.leftMargin;
    }

    private void b() {
        ValueAnimator ofInt = ValueAnimator.ofInt(nrr.e(this.j, 115.0f), 0);
        ofInt.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.huawei.ui.device.activity.adddevice.OneKeyScanActivity.20
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                OneKeyScanActivity.this.bg.getLayoutParams().height = ((Integer) valueAnimator.getAnimatedValue()).intValue();
            }
        });
        ofInt.setDuration(350L);
        ofInt.setInterpolator(new obs(0.2f, 0.0f, 0.2f, 1.0f));
        ofInt.start();
    }

    private void d() {
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this.bk, "alpha", 1.0f, 0.0f);
        ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(this.bl, "alpha", 1.0f, 0.0f);
        ObjectAnimator ofFloat3 = ObjectAnimator.ofFloat(this.bj, "alpha", 1.0f, 0.0f);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.setDuration(100L);
        animatorSet.setInterpolator(new obs(0.2f, 0.0f, 0.2f, 1.0f));
        animatorSet.playTogether(ofFloat, ofFloat3, ofFloat2);
        animatorSet.start();
    }

    private void ab() {
        if (nsn.s()) {
            LogUtil.d("OneKeyScanActivity", "enter rightTextShow Utils.isThreeFoldFonts()");
            this.bm.setTextSize(1, 30.0f);
        }
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this.bh, "alpha", 0.0f, 1.0f);
        ofFloat.setStartDelay(250L);
        ofFloat.setDuration(100L);
        ofFloat.setInterpolator(new obs(0.2f, 0.0f, 0.2f, 1.0f));
        ofFloat.start();
    }

    private ValueAnimator cOH_() {
        ValueAnimator ofInt = ValueAnimator.ofInt(this.ar, 24);
        ofInt.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.huawei.ui.device.activity.adddevice.OneKeyScanActivity.17
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                int intValue = ((Integer) valueAnimator.getAnimatedValue()).intValue();
                OneKeyScanActivity.this.az.c(intValue);
                float f = intValue * 2;
                OneKeyScanActivity.this.az.getLayoutParams().height = nrr.e(OneKeyScanActivity.this.j, f);
                OneKeyScanActivity.this.az.getLayoutParams().width = nrr.e(OneKeyScanActivity.this.j, f);
                OneKeyScanActivity.this.az.requestLayout();
            }
        });
        return ofInt;
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        ReleaseLogUtil.e("R_OneKeyScanActivity", "onCreate to enter");
        cancelAdaptRingRegion();
        this.j = this;
        setContentView(R.layout.activity_scan_all);
        t();
        getWindow().setFlags(16777216, 16777216);
        clearBackgroundDrawable();
        x();
        r();
        overridePendingTransition(R.anim._2130771980_res_0x7f01000c, R.anim._2130771980_res_0x7f01000c);
    }

    private void t() {
        this.l = registerForActivityResult(new ActivityResultContracts.StartIntentSenderForResult(), new ActivityResultCallback() { // from class: com.huawei.ui.device.activity.adddevice.OneKeyScanActivity$$ExternalSyntheticLambda0
            @Override // androidx.activity.result.ActivityResultCallback
            public final void onActivityResult(Object obj) {
                OneKeyScanActivity.this.c((ActivityResult) obj);
            }
        });
    }

    /* synthetic */ void c(ActivityResult activityResult) {
        bmw.e(100112, this.g, String.valueOf(activityResult.getResultCode()), String.valueOf(100001));
        if (activityResult.getResultCode() == -1) {
            SharedPreferenceManager.c("SENSITIVE_PERMISSION_STATUS", "SENSITIVE_PERMISSION_STATUS", SensitivePermissionStatus.RESTART.getValue());
            ai();
        } else {
            f();
        }
    }

    private void f() {
        obb.d(new IBaseResponseCallback() { // from class: com.huawei.ui.device.activity.adddevice.OneKeyScanActivity.19
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i, Object obj) {
                if (i == -1) {
                    obb.c(OneKeyScanActivity.this.o, OneKeyScanActivity.this.j, OneKeyScanActivity.this.l, OneKeyScanActivity.this.b);
                } else {
                    OneKeyScanActivity.this.ai();
                }
            }
        }, this.j);
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onStart() {
        super.onStart();
        if (CommonUtil.ar() || CommonUtil.bf()) {
            adapterView();
        }
    }

    private void p() {
        this.al = new a(this);
        this.bo = new nuc();
        this.aq = new obb();
        if (getIntent() != null) {
            try {
                this.i = getIntent().getIntExtra("KEY_INTENT_COURSE_ENTRANCE", 0);
                this.h = getIntent().getStringExtra("KEY_INTENT_EQUIPMENT_TYPE");
                this.be = getIntent().getIntExtra("SPORT_TYPE", 0);
                this.bq = getIntent().getStringArrayListExtra("uuid_list");
                this.ah = getIntent().getBooleanExtra("is_scan_to_pair_guide", false);
                this.ac = getIntent().getBooleanExtra("is_go_rope_jump", false);
                this.aa = getIntent().getBooleanExtra("is_invalidation", false);
                this.t = getIntent().getStringExtra("uniqueId");
                this.ab = getIntent().getBooleanExtra("isJumpFromFittings", false);
                this.q = getIntent().getStringExtra("fittings_host_uuid");
                this.e = getIntent().getStringExtra("fittings_host_sn");
            } catch (ArrayIndexOutOfBoundsException unused) {
                LogUtil.e("OneKeyScanActivity", "ArrayIndexOutOfBoundsException get uuid list");
            }
        }
        ObserverManagerUtil.c("SKIP_PRODUCT_INTRODUCTION_FRAGMENT", Integer.valueOf(this.be));
        ObserverManagerUtil.c("SKIP_START_EXERCISE_UI", Integer.valueOf(this.be));
        if (this.ah) {
            this.c.setVisibility(8);
            h();
        } else if (koq.b(this.bq)) {
            if (this.ab) {
                this.c.setVisibility(8);
            }
            h();
            this.al.sendEmptyMessageDelayed(104, 2000L);
        } else if (koq.b(((DownloadManagerApi) Services.c("DownloadDeviceResource", DownloadManagerApi.class)).getDeviceList())) {
            LogUtil.d("OneKeyScanActivity", "initData download device source.");
            h();
            this.al.sendEmptyMessageDelayed(104, 2000L);
        } else {
            this.c.setVisibility(8);
            this.w = true;
            this.x = false;
            this.y = false;
        }
        this.bo.c(AnalyticsValue.ONE_KEY_SCAN_ENTER.value());
    }

    private void r() {
        if (TextUtils.isEmpty(this.t)) {
            LogUtil.c("OneKeyScanActivity", "initInvalidationDeviceInfo mInvalidationUniqueId is null.");
            return;
        }
        MeasurableDevice d = ceo.d().d(this.t, false);
        if (d == null || TextUtils.isEmpty(d.getAddress())) {
            LogUtil.c("OneKeyScanActivity", "initInvalidationDeviceInfo device or address is null.");
        } else if (cpa.x(d.getProductId())) {
            this.s = cpa.h(d.getSerialNumber());
        } else {
            this.s = d.getAddress();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void h() {
        LogUtil.d("OneKeyScanActivity", "downloadResource");
        ((DownloadManagerApi) Services.c("DownloadDeviceResource", DownloadManagerApi.class)).addDownloadIndexAllCallBack(this);
        ((DownloadManagerApi) Services.c("DownloadDeviceResource", DownloadManagerApi.class)).downloadIndexAll();
        this.v = true;
        this.am.setVisibility(8);
        this.ap.setVisibility(8);
    }

    private void ar() {
        LogUtil.d("OneKeyScanActivity", "updateData");
        try {
            this.f.clear();
            List<cve> m = m();
            if (koq.b(m)) {
                LogUtil.c("OneKeyScanActivity", "updateData,deviceSingleSeriesInfoBeans is empty");
                sqo.ak("deviceSingleSeriesInfoBeans is empty");
                this.al.sendEmptyMessage(3);
                return;
            }
            this.f.addAll(m);
            if (!cpa.f()) {
                ArrayList arrayList = new ArrayList(16);
                for (cve cveVar : this.f) {
                    List<String> ac = cveVar.ac();
                    if (!ac.contains("8358eb90-b40d-11e9-a2a3-2a2ae2dbcce4") && !ac.contains("b29df4e3-b1f7-4e40-960d-4cfb63ccca05")) {
                        arrayList.add(cveVar);
                    }
                    LogUtil.d("OneKeyScanActivity", "hagrid device");
                }
                this.f.clear();
                this.f.addAll(arrayList);
            }
            List<bjf> a2 = a();
            LogUtil.d("OneKeyScanActivity", "start scan");
            b(ScanMode.BR_BLE, a2);
        } catch (ConcurrentModificationException unused) {
            LogUtil.e("OneKeyScanActivity", "updateData is exception");
            sqo.ak("deviceSingleSeriesInfoBeans is ConcurrentModificationException");
            ae();
        }
    }

    private List<bjf> a() {
        boolean b = koq.b(this.bq);
        ArrayList arrayList = new ArrayList(16);
        for (cve cveVar : this.f) {
            if (b) {
                if (cveVar.ab() != 2 || cveVar.j() != 2) {
                    if (koq.b(cveVar.ac()) || !PermissionDialogHelper.b(cveVar.ac().get(0))) {
                        d(arrayList, cveVar);
                    }
                }
            } else {
                arrayList.addAll(e(cveVar));
            }
        }
        return arrayList;
    }

    private List<cve> m() {
        ArrayList arrayList = new ArrayList();
        List<cve> deviceList = ((DownloadManagerApi) Services.c("DownloadDeviceResource", DownloadManagerApi.class)).getDeviceList();
        if (!koq.b(this.bq)) {
            for (cve cveVar : deviceList) {
                List<String> ac = cveVar.ac();
                if (ac != null && !ac.isEmpty() && ac.contains(this.bq.get(0))) {
                    arrayList.add(cveVar);
                }
            }
            return arrayList;
        }
        if (dib.c().b(this.i) || fhw.e.containsKey(Integer.valueOf(this.be))) {
            cuy indexBean = ((DownloadManagerApi) Services.c("DownloadDeviceResource", DownloadManagerApi.class)).getIndexBean();
            if (indexBean == null) {
                LogUtil.d("OneKeyScanActivity", "indexAllBean is null");
                return arrayList;
            }
            List<cva> e = indexBean.e();
            if (koq.b(e)) {
                LogUtil.d("OneKeyScanActivity", "getDeviceKindBeans is empty");
                return arrayList;
            }
            LogUtil.d("OneKeyScanActivity", "getDeviceKindBeans size is " + e.size());
            for (cva cvaVar : e) {
                if ((!TextUtils.isEmpty(this.h) && this.h.equals(cvaVar.e())) || (fhw.e.containsKey(Integer.valueOf(this.be)) && ((HealthDevice.HealthDeviceKind) fhw.e.get(Integer.valueOf(this.be))).name().equals(cvaVar.e()))) {
                    arrayList.addAll(cvaVar.c());
                    return arrayList;
                }
            }
            return arrayList;
        }
        if (!this.ab) {
            return deviceList;
        }
        arrayList.addAll(n());
        return arrayList;
    }

    private List<cve> n() {
        ArrayList arrayList = new ArrayList(16);
        List<msx> c = mst.a().c(this.q);
        if (koq.b(c)) {
            return arrayList;
        }
        msx msxVar = c.get(0);
        List<DeviceFittingsBean> b = msxVar.b();
        if (koq.b(b)) {
            return arrayList;
        }
        for (DeviceFittingsBean deviceFittingsBean : b) {
            if (deviceFittingsBean != null) {
                arrayList.add(c(deviceFittingsBean, msxVar));
            }
        }
        return arrayList;
    }

    private void ae() {
        this.az.d();
        this.bl.setText(R.string._2130844156_res_0x7f0219fc);
        this.bl.setTextColor(getResources().getColor(R.color._2131296651_res_0x7f09018b));
        this.bl.setTextSize(R.dimen._2131365064_res_0x7f0a0cc8);
        this.bj.setVisibility(0);
        this.bk.setText(R.string._2130844157_res_0x7f0219fd);
        this.bm.setText(R.string._2130844157_res_0x7f0219fd);
        this.bd.setVisibility(0);
        af();
        ag();
    }

    private void af() {
        if (this.bl.hasOnClickListeners()) {
            return;
        }
        this.bl.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.ui.device.activity.adddevice.OneKeyScanActivity.18
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LogUtil.d("OneKeyScanActivity", "restart scan");
                if (OneKeyScanActivity.this.i()) {
                    OneKeyScanActivity.this.al();
                    ViewClickInstrumentation.clickOnView(view);
                } else {
                    OneKeyScanActivity.this.startActivityForResult(new Intent("android.bluetooth.adapter.action.REQUEST_ENABLE"), 102);
                    ViewClickInstrumentation.clickOnView(view);
                }
            }
        });
    }

    private void ag() {
        if (this.z) {
            this.ba.setVisibility(4);
        } else {
            this.ba.setVisibility(0);
        }
        if (this.ba.hasOnClickListeners()) {
            return;
        }
        this.ba.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.ui.device.activity.adddevice.OneKeyScanActivity.23
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (OneKeyScanActivity.this.i()) {
                    OneKeyScanActivity.this.al();
                    ViewClickInstrumentation.clickOnView(view);
                } else {
                    OneKeyScanActivity.this.startActivityForResult(new Intent("android.bluetooth.adapter.action.REQUEST_ENABLE"), 102);
                    ViewClickInstrumentation.clickOnView(view);
                }
            }
        });
    }

    private void d(List<bjf> list, cve cveVar) {
        if (CloudUtils.d()) {
            if (TextUtils.equals(cveVar.v(), "2") || TextUtils.equals(cveVar.v(), "3")) {
                list.addAll(e(cveVar));
                return;
            }
            return;
        }
        if (TextUtils.equals(cveVar.v(), "1") || TextUtils.equals(cveVar.v(), "3")) {
            list.addAll(e(cveVar));
        }
    }

    private void x() {
        LinearLayout linearLayout = (LinearLayout) nsy.cMc_(this, R.id.content);
        View inflate = LayoutInflater.from(this.j).inflate(R.layout.activity_scan_all_content, (ViewGroup) null);
        if (nsn.s()) {
            cOD_(linearLayout, inflate);
        } else {
            cOF_(linearLayout, inflate);
        }
        this.z = true;
        CustomTitleBar customTitleBar = (CustomTitleBar) nsy.cMc_(this, R.id.search_custom_title);
        this.bi = customTitleBar;
        customTitleBar.setTitleBarBackgroundColor(getResources().getColor(R.color._2131296690_res_0x7f0901b2));
        this.aj = (HealthButton) nsy.cMc_(this, R.id.scan_left_button);
        HealthProgressBar healthProgressBar = (HealthProgressBar) nsy.cMc_(this, R.id.download_progress);
        this.r = healthProgressBar;
        healthProgressBar.d(this, R.color._2131296657_res_0x7f090191, R.color.emui_accent);
        this.r.setProgress(0);
        this.av = (HealthButton) nsy.cMc_(this, R.id.scan_right_button);
        this.am = (LinearLayout) nsy.cMc_(this, R.id.device_error_bad_layout);
        this.ap = (LinearLayout) nsy.cMc_(this, R.id.device_download_bad_layout);
        this.au = (HealthTextView) nsy.cMc_(this, R.id.resource_error);
        initView(inflate);
        aa();
        y();
        if (nsn.s()) {
            this.av.setText("  " + this.av.getText().toString() + "  ");
        }
    }

    public void initView(View view) {
        HealthRecycleView healthRecycleView = (HealthRecycleView) nsy.cMd_(view, R.id.scan_device_list);
        this.ax = healthRecycleView;
        healthRecycleView.setNestedScrollingEnabled(false);
        this.c = (LinearLayout) nsy.cMc_(this, R.id.bottom_layout);
    }

    private boolean e(Context context) {
        return LanguageUtil.j(context) || LanguageUtil.p(this.j);
    }

    private void cOF_(LinearLayout linearLayout, View view) {
        linearLayout.addView(view);
    }

    private void cOD_(LinearLayout linearLayout, View view) {
        HealthScrollView healthScrollView = new HealthScrollView(this.j);
        healthScrollView.setVerticalScrollBarEnabled(false);
        healthScrollView.setScrollViewIntercepts(true);
        healthScrollView.addView(view);
        linearLayout.addView(healthScrollView);
    }

    private void aa() {
        this.aj.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.ui.device.activity.adddevice.OneKeyScanActivity.25
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                OneKeyScanActivity.this.am();
                OneKeyScanActivity.this.bo.c(AnalyticsValue.ONE_KEY_SCAN_MANUAL_ADD.value());
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        this.ao = new CustomPermissionAction(this) { // from class: com.huawei.ui.device.activity.adddevice.OneKeyScanActivity.5
            @Override // com.huawei.hwcommonmodel.utils.permission.PermissionsResultAction
            public void onGranted() {
                LogUtil.d("OneKeyScanActivity", "mQrCodeAction: onGranted");
                OneKeyScanActivity.this.k();
            }

            @Override // com.huawei.ui.commonui.utils.CustomPermissionAction, com.huawei.hwcommonmodel.utils.permission.PermissionsResultAction
            public void onDenied(String str) {
                LogUtil.d("OneKeyScanActivity", "mQrCodeAction: onDenied");
                OneKeyScanActivity.this.k();
            }

            @Override // com.huawei.ui.commonui.utils.CustomPermissionAction, com.huawei.hwcommonmodel.utils.permission.PermissionsResultAction
            public void onForeverDenied(PermissionUtil.PermissionType permissionType) {
                LogUtil.d("OneKeyScanActivity", "mQrCodeAction: onForeverDenied");
                OneKeyScanActivity.this.k();
            }
        };
        this.av.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.ui.device.activity.adddevice.OneKeyScanActivity.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                OneKeyScanActivity.this.bo.c(AnalyticsValue.ONE_KEY_SCAN_CLICK_SCAN.value());
                LoginInit.getInstance(BaseApplication.getContext()).browsingToLogin(new IBaseResponseCallback() { // from class: com.huawei.ui.device.activity.adddevice.OneKeyScanActivity.1.4
                    @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                    /* renamed from: onResponse */
                    public void d(int i, Object obj) {
                        if (i == 0) {
                            PermissionUtil.b(OneKeyScanActivity.this.j, PermissionUtil.PermissionType.CAMERA, OneKeyScanActivity.this.ao);
                        }
                    }
                }, "");
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        this.bi.setLeftButtonOnClickListener(new View.OnClickListener() { // from class: com.huawei.ui.device.activity.adddevice.OneKeyScanActivity.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                OneKeyScanActivity.this.onBackPressed();
                ViewClickInstrumentation.clickOnView(view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void k() {
        if (this.j == null) {
            LogUtil.c("OneKeyScanActivity", "gotoScan: mContext is null");
            return;
        }
        this.u = false;
        try {
            Intent intent = new Intent();
            intent.setClassName(this.j, "com.huawei.ui.homehealth.qrcode.activity.QrCodeScanningActivity");
            this.j.startActivity(intent);
        } catch (ActivityNotFoundException e) {
            LogUtil.e("OneKeyScanActivity", "ActivityNotFoundException e:", e.getMessage());
        }
        this.bo.cOu_(this.ak);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void am() {
        this.u = false;
        this.bo.cOu_(this.ak);
        if (nsn.o()) {
            LogUtil.c("OneKeyScanActivity", "startDeviceList fast click");
            return;
        }
        Intent intent = new Intent();
        intent.setClass(this.j, AllDeviceListActivity.class);
        if (dib.c().b(this.i)) {
            intent.putExtra("kind_id", this.h);
            intent.putExtra("KEY_INTENT_COURSE_ENTRANCE", this.i);
            intent.putExtra("KEY_INTENT_EQUIPMENT_TYPE", this.h);
        }
        if (fhw.e.containsKey(Integer.valueOf(this.be))) {
            intent.putExtra("SPORT_TYPE", this.be);
        }
        intent.putExtra("progressbar", this.as);
        startActivity(intent);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void al() {
        ReleaseLogUtil.e("R_OneKeyScanActivity", "enter startScan mIsScanning: ", Boolean.valueOf(this.ae));
        if (this.ae) {
            return;
        }
        if (this.al == null) {
            LogUtil.c("OneKeyScanActivity", "scanview is not init");
            return;
        }
        if (!BluetoothPermisionUtils.c(this)) {
            this.az.d();
            this.bk.setText(R.string._2130844146_res_0x7f0219f2);
            this.bj.setVisibility(8);
            this.ad = false;
            this.bo.b(this.j, this.al, this.bl);
            this.bo.b(this.j, this.al);
            LogUtil.d("OneKeyScanActivity", "startScan BluetoothPermission not granted");
            return;
        }
        if (!i()) {
            LogUtil.d("OneKeyScanActivity", "startScan bluetooth is not open");
            this.az.d();
            this.bk.setText(R.string._2130844146_res_0x7f0219f2);
            this.bj.setVisibility(8);
            this.ad = false;
            this.bo.c(this.j, this.bl);
            return;
        }
        if (Build.VERSION.SDK_INT <= 30 && !obb.b()) {
            this.az.d();
            this.bk.setText(R.string._2130844146_res_0x7f0219f2);
            this.bj.setVisibility(8);
            this.ad = false;
            LogUtil.d("OneKeyScanActivity", "startScan GPS service not enable");
            this.bo.c(this.j, this.al, this.bl);
            this.aq.cTX_(this);
            return;
        }
        if (!this.w) {
            this.ad = true;
            this.az.e();
            this.bk.setText(R.string._2130841767_res_0x7f0210a7);
            this.bj.setVisibility(8);
            this.bl.setText(R.string.IDS_device_mgr_device_scaning_title);
            this.bl.setTextColor(getResources().getColor(R.color._2131299241_res_0x7f090ba9));
            this.bl.setTextSize(R.dimen._2131365061_res_0x7f0a0cc5);
            if (this.v) {
                return;
            }
            if (this.y) {
                q();
                return;
            } else {
                if (this.x) {
                    j();
                    return;
                }
                return;
            }
        }
        this.ad = true;
        aj();
    }

    private void j() {
        this.bk.setText(R.string.IDS_downlod_device_error);
        this.bj.setVisibility(8);
        this.bl.setText("");
        int i = this.p + 1;
        this.p = i;
        if (i < 2) {
            this.az.d();
            s();
        } else {
            this.ap.setVisibility(8);
            this.am.setVisibility(8);
            this.r.setVisibility(8);
            g();
        }
    }

    private void g() {
        if (koq.b(((DownloadManagerApi) Services.c("DownloadDeviceResource", DownloadManagerApi.class)).getDeviceList())) {
            s();
            this.az.d();
        } else {
            aj();
        }
    }

    private void aj() {
        this.az.e();
        LogUtil.d("OneKeyScanActivity", "enter startScan");
        this.bk.setText(R.string._2130841767_res_0x7f0210a7);
        this.bl.setText(R.string.IDS_device_mgr_device_scaning_title);
        this.bl.setTextColor(getResources().getColor(R.color._2131299241_res_0x7f090ba9));
        this.bl.setTextSize(R.dimen._2131365061_res_0x7f0a0cc5);
        this.bj.setVisibility(8);
        if (!this.z) {
            this.ba.setVisibility(4);
            this.bm.setText(R.string._2130841767_res_0x7f0210a7);
            this.bd.setVisibility(8);
        }
        this.d.e((List<nyr>) null);
        if (!i()) {
            this.ae = true;
            this.al.sendEmptyMessageDelayed(3, 15000L);
        } else {
            ar();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean i() {
        BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
        return defaultAdapter != null && defaultAdapter.isEnabled();
    }

    private void s() {
        this.as = 0;
        this.ap.setVisibility(0);
        this.am.setVisibility(8);
        if (this.ap.hasOnClickListeners()) {
            return;
        }
        this.ap.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.ui.device.activity.adddevice.OneKeyScanActivity.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                OneKeyScanActivity.this.az.e();
                OneKeyScanActivity.this.bk.setText(R.string._2130841767_res_0x7f0210a7);
                OneKeyScanActivity.this.bl.setText(R.string.IDS_device_mgr_device_scaning_title);
                OneKeyScanActivity.this.bl.setTextColor(OneKeyScanActivity.this.getResources().getColor(R.color._2131299241_res_0x7f090ba9));
                OneKeyScanActivity.this.bl.setTextSize(R.dimen._2131365061_res_0x7f0a0cc5);
                OneKeyScanActivity.this.bj.setVisibility(8);
                OneKeyScanActivity.this.h();
                ViewClickInstrumentation.clickOnView(view);
            }
        });
    }

    private void q() {
        this.az.d();
        this.bk.setText(R.string.IDS_downlod_device_error);
        this.bl.setText(R.string._2130844160_res_0x7f021a00);
        this.bj.setVisibility(8);
        this.as = 0;
        this.ap.setVisibility(8);
        this.am.setVisibility(0);
        if (this.am.hasOnClickListeners()) {
            return;
        }
        this.am.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.ui.device.activity.adddevice.OneKeyScanActivity.7
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                OneKeyScanActivity.this.bo.a(OneKeyScanActivity.this);
                ViewClickInstrumentation.clickOnView(view);
            }
        });
    }

    private void b(final ScanMode scanMode, final List<bjf> list) {
        this.ay.clear();
        this.ae = true;
        this.al.sendEmptyMessageDelayed(3, 15000L);
        ThreadPoolManager.d().execute(new Runnable() { // from class: com.huawei.ui.device.activity.adddevice.OneKeyScanActivity.6
            @Override // java.lang.Runnable
            public void run() {
                OneKeyScanActivity.this.d((List<bjf>) list);
                OneKeyScanActivity.this.runOnUiThread(new Runnable() { // from class: com.huawei.ui.device.activity.adddevice.OneKeyScanActivity.6.5
                    @Override // java.lang.Runnable
                    public void run() {
                        if (dfe.a().e()) {
                            OneKeyScanActivity.this.bo.cOs_(OneKeyScanActivity.this.al, OneKeyScanActivity.this.bq, OneKeyScanActivity.this.f);
                        } else {
                            OneKeyScanActivity.this.bo.d(scanMode, list, OneKeyScanActivity.this.al);
                        }
                    }
                });
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(List<bjf> list) {
        List<BluetoothDevice> b = this.bo.b();
        if (!koq.b(b)) {
            e(list, b);
        }
        List<BluetoothDevice> e = this.bo.e();
        if (koq.b(e)) {
            return;
        }
        e(list, e);
    }

    private void e(List<bjf> list, List<BluetoothDevice> list2) {
        for (BluetoothDevice bluetoothDevice : list2) {
            Iterator<bjf> it = list.iterator();
            while (it.hasNext() && !cOE_(bluetoothDevice, it.next())) {
            }
        }
    }

    private boolean cOE_(BluetoothDevice bluetoothDevice, bjf bjfVar) {
        try {
            String name = bluetoothDevice.getName();
            String address = bluetoothDevice.getAddress();
            if (name != null && address != null && bjfVar != null && bjfVar.d() != null && name.toLowerCase(Locale.ENGLISH).contains(bjfVar.d().toLowerCase(Locale.ENGLISH))) {
                if (!e(name)) {
                    nyr nyrVar = new nyr();
                    nyrVar.c(name);
                    nyrVar.a(address);
                    if (bluetoothDevice.getType() == 3) {
                        nyrVar.d(1);
                    } else {
                        nyrVar.d(bluetoothDevice.getType());
                    }
                    nyrVar.e(0);
                    if (this.al != null) {
                        Message obtain = Message.obtain();
                        obtain.what = 1;
                        obtain.obj = nyrVar;
                        this.al.sendMessage(obtain);
                    }
                }
                return true;
            }
        } catch (SecurityException e) {
            LogUtil.e("OneKeyScanActivity", "handleCompare SecurityException:", ExceptionUtils.d(e));
            sqo.ak("get system bluetooth device exception ");
        }
        return false;
    }

    private boolean e(String str) {
        List<DeviceInfo> deviceList = cun.c().getDeviceList(HwGetDevicesMode.ACTIVE_DEVICES, null, "OneKeyScanActivity");
        if (koq.b(deviceList)) {
            return false;
        }
        Iterator<DeviceInfo> it = deviceList.iterator();
        while (it.hasNext()) {
            if (TextUtils.equals(str, it.next().getDeviceName())) {
                LogUtil.d("OneKeyScanActivity", "isDeviceConnected name:", str);
                return true;
            }
        }
        return false;
    }

    private void y() {
        LogUtil.d("OneKeyScanActivity", "enter initRecyclerView");
        this.ax.setLayoutManager(new LinearLayoutManager(this));
        if (this.ax.getItemAnimator() instanceof SimpleItemAnimator) {
            ((SimpleItemAnimator) this.ax.getItemAnimator()).setSupportsChangeAnimations(false);
        }
        if (nsn.s()) {
            this.d = new ScanDeviceAdapter(new BaseRecyclerAdapter.OnItemClickListener<nyr>() { // from class: com.huawei.ui.device.activity.adddevice.OneKeyScanActivity.10
                @Override // com.huawei.ui.commonui.adapter.BaseRecyclerAdapter.OnItemClickListener
                /* renamed from: b, reason: merged with bridge method [inline-methods] */
                public void onItemClicked(RecyclerHolder recyclerHolder, int i, nyr nyrVar) {
                    LogUtil.d("OneKeyScanActivity", "enter initRecyclerView isThreeFoldFonts and click position = ", Integer.valueOf(i));
                    OneKeyScanActivity.this.a(recyclerHolder, i, nyrVar);
                }
            }, true);
        } else {
            this.d = new ScanDeviceAdapter(new BaseRecyclerAdapter.OnItemClickListener<nyr>() { // from class: com.huawei.ui.device.activity.adddevice.OneKeyScanActivity.9
                @Override // com.huawei.ui.commonui.adapter.BaseRecyclerAdapter.OnItemClickListener
                /* renamed from: d, reason: merged with bridge method [inline-methods] */
                public void onItemClicked(RecyclerHolder recyclerHolder, int i, nyr nyrVar) {
                    LogUtil.d("OneKeyScanActivity", "enter initRecyclerView and click position = ", Integer.valueOf(i));
                    OneKeyScanActivity.this.b(nyrVar);
                    OneKeyScanActivity.this.a(recyclerHolder, i, nyrVar);
                }
            });
        }
        this.d.c(new BaseRecyclerAdapter.OnItemClickListener() { // from class: com.huawei.ui.device.activity.adddevice.OneKeyScanActivity.8
            @Override // com.huawei.ui.commonui.adapter.BaseRecyclerAdapter.OnItemClickListener
            public void onItemClicked(RecyclerHolder recyclerHolder, int i, Object obj) {
                if (i == 0) {
                    OneKeyScanActivity.this.e(recyclerHolder);
                } else if (obj instanceof nyr) {
                    nyr nyrVar = (nyr) obj;
                    LogUtil.d("OneKeyScanActivity", "mAdapter onItemClicked position: ", Integer.valueOf(i), " name: ", nyrVar.b());
                    OneKeyScanActivity.this.a(recyclerHolder, i, nyrVar);
                }
            }
        });
        this.ax.setAdapter(this.d);
        this.ax.a(false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(RecyclerHolder recyclerHolder) {
        if (nsn.s()) {
            this.ba = (HealthTextView) nsy.cMd_(recyclerHolder.itemView, R.id.re_scan_small_text_with_large_font_scale);
            LogUtil.d("OneKeyScanActivity", "mSmallScanAgainText Utils.isThreeFoldFonts()");
            if (!e(this.j)) {
                ((HealthTextView) nsy.cMd_(recyclerHolder.itemView, R.id.re_scan_small_french_text)).setVisibility(8);
            } else {
                ((HealthTextView) nsy.cMd_(recyclerHolder.itemView, R.id.re_scan_small_text)).setVisibility(8);
            }
        } else {
            this.aw = (RelativeLayout) nsy.cMd_(recyclerHolder.itemView, R.id.scan_normal_layout);
            this.at = (LinearLayout) nsy.cMd_(recyclerHolder.itemView, R.id.scan_french_layout);
            if (!e(this.j)) {
                this.aw.setVisibility(8);
                this.at.setVisibility(0);
                this.ba = (HealthTextView) nsy.cMd_(recyclerHolder.itemView, R.id.re_scan_small_french_text);
            } else {
                this.aw.setVisibility(0);
                this.at.setVisibility(8);
                this.ba = (HealthTextView) nsy.cMd_(recyclerHolder.itemView, R.id.re_scan_small_text);
            }
        }
        this.az = (RadarImageView) nsy.cMd_(recyclerHolder.itemView, R.id.scan_radar);
        RelativeLayout relativeLayout = (RelativeLayout) nsy.cMd_(recyclerHolder.itemView, R.id.scan_layout);
        this.bb = relativeLayout;
        relativeLayout.post(new Runnable() { // from class: com.huawei.ui.device.activity.adddevice.OneKeyScanActivity.15
            @Override // java.lang.Runnable
            public void run() {
                OneKeyScanActivity oneKeyScanActivity = OneKeyScanActivity.this;
                oneKeyScanActivity.br = oneKeyScanActivity.bb.getWidth();
            }
        });
        this.bg = (LinearLayout) nsy.cMd_(recyclerHolder.itemView, R.id.scan_text_layout);
        HealthTextView healthTextView = (HealthTextView) nsy.cMd_(recyclerHolder.itemView, R.id.scan_text_tip1);
        this.bk = healthTextView;
        healthTextView.setText(R.string._2130841767_res_0x7f0210a7);
        HealthTextView healthTextView2 = (HealthTextView) nsy.cMd_(recyclerHolder.itemView, R.id.scan_text_tip2);
        this.bl = healthTextView2;
        healthTextView2.setText(R.string.IDS_device_mgr_device_scaning_title);
        this.bj = (HealthTextView) nsy.cMd_(recyclerHolder.itemView, R.id.no_device_found_text_tip);
        if (!e(this.j)) {
            this.bd = (HealthTextView) nsy.cMd_(recyclerHolder.itemView, R.id.no_device_found_small_french_text);
            this.bm = (HealthTextView) nsy.cMd_(recyclerHolder.itemView, R.id.scan_small_french_text);
            this.bh = (LinearLayout) nsy.cMd_(recyclerHolder.itemView, R.id.scan_layout_french_small);
        } else {
            this.bd = (HealthTextView) nsy.cMd_(recyclerHolder.itemView, R.id.no_device_found_small_text);
            this.bm = (HealthTextView) nsy.cMd_(recyclerHolder.itemView, R.id.scan_small_text);
            this.bh = (LinearLayout) nsy.cMd_(recyclerHolder.itemView, R.id.scan_layout_small);
        }
        v();
        p();
        al();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(nyr nyrVar) {
        cvl cvlVar;
        if (nyrVar.f() == null || (cvlVar = nyrVar.f().get(nyrVar.k().get(0))) == null) {
            return;
        }
        LogUtil.d("OneKeyScanActivity", "deeplink uuid: ", cvlVar.e());
        LogUtil.d("OneKeyScanActivity", "deeplink uri: ", cvlVar.a());
        LogUtil.d("OneKeyScanActivity", "deeplink pair_format: ", cvlVar.d());
    }

    private void b(int i, nyr nyrVar) {
        LogUtil.d("OneKeyScanActivity", "ScanDeviceAdapter mAdapter onItemClicked position: ", Integer.valueOf(i), " name: ", nyrVar.b());
        String b = this.bo.b(nyrVar);
        if (CompileParameterUtil.a("IS_LUPIN_SUPPORT_PRIVACY", false) && "25c6df38-ca23-11e9-a32f-2a2ae2dbcce4".equals(b)) {
            this.ag = nyrVar;
            boolean e = SharedPreferenceManager.e(BaseApplication.getContext(), LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1011));
            if (dks.d(BaseApplication.getContext()) && !e) {
                this.bo.d(this.j, nyrVar, b);
                return;
            } else {
                d(nyrVar);
                return;
            }
        }
        d(nyrVar);
    }

    private void d(final nyr nyrVar) {
        this.ai = nyrVar;
        HashMap hashMap = new HashMap(16);
        hashMap.put(PluginPayAdapter.KEY_DEVICE_INFO_NAME, nyrVar.b());
        hashMap.put("bluetooth_name", nyrVar.n());
        this.bo.d(AnalyticsValue.ONE_KEY_SCAN_CLICK_CONNECT.value(), hashMap);
        LoginInit.getInstance(BaseApplication.getContext()).browsingToLogin(new IBaseResponseCallback() { // from class: com.huawei.ui.device.activity.adddevice.OneKeyScanActivity.12
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i, Object obj) {
                List<String> list;
                if (i == 0) {
                    if (OneKeyScanActivity.this.a(nyrVar)) {
                        OneKeyScanActivity.this.ag = nyrVar;
                        OneKeyScanActivity.this.bo.c(OneKeyScanActivity.this.aa);
                        OneKeyScanActivity.this.af = nyrVar.i();
                        OneKeyScanActivity.this.bo.c(OneKeyScanActivity.this.i, OneKeyScanActivity.this.h);
                        nuc nucVar = OneKeyScanActivity.this.bo;
                        Context context = OneKeyScanActivity.this.j;
                        nyr nyrVar2 = nyrVar;
                        nucVar.cOv_(context, nyrVar2, nyrVar2.k(), OneKeyScanActivity.this.ak, OneKeyScanActivity.this.ac);
                        return;
                    }
                    if (TextUtils.isEmpty(nyrVar.i()) || !nyrVar.i().startsWith("HDK")) {
                        OneKeyScanActivity.this.e(nyrVar);
                        return;
                    }
                    OneKeyScanActivity.this.ag = nyrVar;
                    OneKeyScanActivity.this.bo.c(OneKeyScanActivity.this.aa);
                    OneKeyScanActivity.this.af = nyrVar.i();
                    OneKeyScanActivity.this.bo.c(OneKeyScanActivity.this.i, OneKeyScanActivity.this.h);
                    if (!OneKeyScanActivity.this.ab) {
                        list = OneKeyScanActivity.this.bq;
                    } else {
                        list = nyrVar.k();
                        OneKeyScanActivity.this.bo.a(OneKeyScanActivity.this.ab);
                        OneKeyScanActivity.this.bo.e(OneKeyScanActivity.this.e);
                    }
                    OneKeyScanActivity.this.bo.cOv_(OneKeyScanActivity.this.j, nyrVar, list, OneKeyScanActivity.this.ak, OneKeyScanActivity.this.ac);
                }
            }
        }, "");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean a(nyr nyrVar) {
        return "SMART_HEADPHONES".equals(nyrVar.i()) && nyrVar.k() != null && nyrVar.k().size() > 0 && "4bfc5a27-f2b9-4c41-bd9b-a4a2a18f752c".equals(nyrVar.k().get(0));
    }

    private void v() {
        if (nsn.ae(BaseApplication.getContext())) {
            LogUtil.d("OneKeyScanActivity", "initRadarSize is pad");
            this.az.getLayoutParams().height = nrr.e(this.j, 272.0f);
            this.az.getLayoutParams().width = nrr.e(this.j, 272.0f);
            this.az.e();
            return;
        }
        if (nsn.j() - nsn.r(this.j) < nrr.e(this.j, 640.0f)) {
            LogUtil.d("OneKeyScanActivity", "initRadarSize low phone");
            this.az.getLayoutParams().height = nrr.e(this.j, 212.0f);
            this.az.getLayoutParams().width = nrr.e(this.j, 212.0f);
            this.ar = 106;
            this.az.e(106);
            return;
        }
        LogUtil.d("OneKeyScanActivity", "initRadarSize normal phone");
        this.az.e();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(boolean z) {
        if (z) {
            w();
        }
        this.d.b(z);
    }

    private void w() {
        TranslateAnimation translateAnimation = new TranslateAnimation(0.0f, 0.0f, nrr.e(this.j, -50.0f), 0.0f);
        translateAnimation.setDuration(350L);
        this.ax.setLayoutAnimation(new LayoutAnimationController(translateAnimation));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(nyr nyrVar) {
        if (TextUtils.isEmpty(nyrVar.i())) {
            LogUtil.c("OneKeyScanActivity", "device kind id is empty");
            return;
        }
        if (!nyrVar.i().startsWith(PutDataRequest.WEAR_URI_SCHEME) && !nyrVar.i().startsWith("SPORTS_GENIE") && !nyrVar.i().startsWith("SMART_HEADPHONES")) {
            LogUtil.c("OneKeyScanActivity", "device not support");
            return;
        }
        this.u = true;
        this.af = nyrVar.i();
        this.n.clear();
        this.m = nyrVar.a();
        this.k = nyrVar.d();
        this.n.addAll(nyrVar.k());
        this.g = nyrVar.b();
        this.o = nyrVar.h();
        this.b = nyrVar.e();
        an();
    }

    private void an() {
        if (isFinishing()) {
            LogUtil.d("OneKeyScanActivity", "this activity is finish");
            return;
        }
        if (!nsn.a(2000)) {
            if (jkj.d().j()) {
                LogUtil.d("OneKeyScanActivity", "wear device OTA is in progress");
                this.bo.e(this.j);
                return;
            } else if (z()) {
                LogUtil.c("OneKeyScanActivity", "current device is follow device");
                o();
                return;
            } else {
                l();
                return;
            }
        }
        LogUtil.d("OneKeyScanActivity", "click too fast");
    }

    private void l() {
        LogUtil.d("OneKeyScanActivity", "enter handleDialogByConnectedDevice");
        List<DeviceInfo> c = oae.c(BaseApplication.getContext()).c();
        if (c == null || c.isEmpty()) {
            LogUtil.c("OneKeyScanActivity", "onClick connectedDeviceInfo is null");
            o();
        } else if (c.size() == 1) {
            b(c);
        } else if (c.size() >= 2) {
            a(c);
        } else {
            LogUtil.d("OneKeyScanActivity", "more devices");
        }
    }

    private void a(List<DeviceInfo> list) {
        if (u()) {
            for (DeviceInfo deviceInfo : list) {
                if (cvt.c(deviceInfo.getProductType())) {
                    LogUtil.d("OneKeyScanActivity", "caseForTwoMoreConnectedDevices aw70 device");
                    this.bo.a(this.j, this.g, deviceInfo, this.al);
                    return;
                }
            }
            return;
        }
        for (DeviceInfo deviceInfo2 : list) {
            if (!cvt.a(deviceInfo2.getProductType(), deviceInfo2.getAutoDetectSwitchStatus())) {
                LogUtil.d("OneKeyScanActivity", "onClick user has connect other device, and also wants to connect other device");
                this.bo.a(this.j, this.g, deviceInfo2, this.al);
                return;
            }
        }
    }

    private void b(List<DeviceInfo> list) {
        LogUtil.d("OneKeyScanActivity", "onClick has one connected device");
        DeviceInfo deviceInfo = list.get(0);
        if (deviceInfo == null) {
            LogUtil.c("OneKeyScanActivity", "onClick connected is null");
            return;
        }
        if (cvt.c(deviceInfo.getProductType())) {
            a(deviceInfo);
            return;
        }
        if (u()) {
            LogUtil.d("OneKeyScanActivity", "onClick user has connect aw70, and also wants to connect aw70 device");
            o();
            return;
        }
        LogUtil.d("OneKeyScanActivity", "onClick user has connect other device, and also wants to connect other device");
        if ((this.m == 10 && deviceInfo.getDeviceIdentify().equalsIgnoreCase("AndroidWear")) || this.m == -3) {
            o();
        } else {
            this.bo.a(this.j, this.g, deviceInfo, this.al);
        }
    }

    private boolean u() {
        return this.m == 1;
    }

    private boolean z() {
        return this.k == 1;
    }

    private void a(DeviceInfo deviceInfo) {
        if (u()) {
            LogUtil.d("OneKeyScanActivity", "onClick user has connect aw70, and also wants to connect aw70 device");
            this.bo.a(this.j, this.g, deviceInfo, this.al);
        } else if (deviceInfo.getAutoDetectSwitchStatus() == 0) {
            LogUtil.d("OneKeyScanActivity", "onClick user has connect aw70 band mode, and also wants to connect other device");
            this.bo.a(this.j, this.g, deviceInfo, this.al);
        } else if (deviceInfo.getAutoDetectSwitchStatus() == 1) {
            LogUtil.d("OneKeyScanActivity", "onClick user has connect aw70 run mode, and also wants to connect other device");
            o();
        } else {
            LogUtil.d("OneKeyScanActivity", "onClick user has connect aw70 unknown mode, and also wants to connect other device");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(final RecyclerHolder recyclerHolder, final int i, final nyr nyrVar) {
        LogUtil.d("OneKeyScanActivity", "enter initRecyclerView and click position = ", Integer.valueOf(i));
        if (BluetoothAdapter.getDefaultAdapter() == null) {
            LogUtil.e("OneKeyScanActivity", "checkBluetoothEnabled BluetoothAdapter or getActivity is null");
            return;
        }
        if (!i()) {
            NoTitleCustomAlertDialog noTitleCustomAlertDialog = this.f9021a;
            if (noTitleCustomAlertDialog != null) {
                if (noTitleCustomAlertDialog.isShowing()) {
                    this.f9021a.dismiss();
                }
                this.f9021a = null;
            }
            String string = getResources().getString(R.string.IDS_app_name_health);
            String string2 = getResources().getString(R.string.IDS_device_bt_open_request_info);
            NoTitleCustomAlertDialog.Builder builder = new NoTitleCustomAlertDialog.Builder(this);
            builder.e(String.format(Locale.ENGLISH, string2, string));
            builder.czC_(R.string.IDS_device_bt_right_btn_info, new View.OnClickListener() { // from class: com.huawei.ui.device.activity.adddevice.OneKeyScanActivity.13
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    LogUtil.d("OneKeyScanActivity", "checkBluetoothEnabled enable bluetooth");
                    OneKeyScanActivity.this.bf = new BtSwitchStateCallback() { // from class: com.huawei.ui.device.activity.adddevice.OneKeyScanActivity.13.2
                        @Override // com.huawei.hwbtsdk.btdatatype.callback.BtSwitchStateCallback
                        public void onBtSwitchStateCallback(int i2) {
                            LogUtil.d("OneKeyScanActivity", "btSwitchState:", Integer.valueOf(i2));
                            OneKeyScanActivity.this.d(i2, recyclerHolder, i, nyrVar);
                        }
                    };
                    iyl.d().d(OneKeyScanActivity.this.bf);
                    ViewClickInstrumentation.clickOnView(view);
                }
            });
            builder.czz_(R.string.IDS_device_bt_left_btn_info, new View.OnClickListener() { // from class: com.huawei.ui.device.activity.adddevice.OneKeyScanActivity.14
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    LogUtil.d("OneKeyScanActivity", "checkBluetoothEnabled not to enable bluetooth");
                    ViewClickInstrumentation.clickOnView(view);
                }
            });
            NoTitleCustomAlertDialog e = builder.e();
            this.f9021a = e;
            e.setCancelable(false);
            this.f9021a.show();
            return;
        }
        LogUtil.d("OneKeyScanActivity", "Bluetooth Enabled");
        d(nyrVar);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(int i, RecyclerHolder recyclerHolder, int i2, nyr nyrVar) {
        if (i == 1) {
            LogUtil.d("OneKeyScanActivity", "checkBluetoothEnabled not to enable bluetooth");
            return;
        }
        if (i == 3) {
            LogUtil.d("OneKeyScanActivity", "checkBluetoothEnabled enable bluetooth", 3);
            iyl.d().e(this.bf);
            if (recyclerHolder != null && i2 != -1) {
                LogUtil.d("OneKeyScanActivity", "enable bluetooth onItemClickedAdapter and click position = ", Integer.valueOf(i2));
                b(i2, nyrVar);
                return;
            } else {
                LogUtil.d("OneKeyScanActivity", "enable bluetooth checkLoginDownload and click position = ", Integer.valueOf(i2));
                d(nyrVar);
                return;
            }
        }
        LogUtil.c("OneKeyScanActivity", "Bluetooth unknown state.");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void o() {
        if (obb.a(this.j, this.b) && this.ai.i().startsWith(PutDataRequest.WEAR_URI_SCHEME)) {
            new HwGetDevicesParameter().setDeviceIdentify(this.o);
            if (!koq.b(cun.c().getDeviceList(HwGetDevicesMode.IDENTIFY_DEVICES, null, "OneKeyScanActivity"))) {
                SharedPreferenceManager.e(Integer.toString(1000), "wearable_unpair_reconnection", true);
                ArrayList arrayList = new ArrayList(16);
                arrayList.add(this.o);
                oae.c(BaseApplication.getContext()).e(arrayList, true);
            } else {
                bkw.d().sK_(BluetoothAdapter.getDefaultAdapter().getRemoteDevice(this.o));
            }
            obb.c(this.o, this.j, this.l, this.b);
            return;
        }
        ai();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void ai() {
        DeviceInfo deviceInfo = new DeviceInfo();
        deviceInfo.setDeviceUdid(this.g);
        deviceInfo.setUuid(this.o);
        if (!TextUtils.isEmpty(this.af)) {
            deviceInfo.setDeviceModel(this.af.toLowerCase(Locale.ENGLISH));
        }
        if (bgb.d().isSupportH5Pair(this.g)) {
            this.bo.cOu_(this.ak);
        }
        this.bo.b(this.j, deviceInfo, this.n);
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        if (nsn.s() || nsn.r()) {
            LogUtil.d("OneKeyScanActivity", "Utils.isThreeFoldFonts:", Boolean.valueOf(nsn.s()), "; isLargeFontScaleMode:", Boolean.valueOf(nsn.r()));
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        ReleaseLogUtil.e("R_OneKeyScanActivity", "onDestroy to enter");
        super.onDestroy();
        ak();
        iyl.d().e(this.bf);
        ((DownloadManagerApi) Services.c("DownloadDeviceResource", DownloadManagerApi.class)).onDownloadIndexAllDestroy();
        a aVar = this.al;
        if (aVar != null) {
            aVar.removeCallbacksAndMessages(null);
        }
        if (this.bo != null) {
            HashMap hashMap = new HashMap(16);
            hashMap.put("device_number", Integer.valueOf(this.ay.size()));
            this.bo.d(AnalyticsValue.ONE_KEY_SCAN_DEVICE_NUMBER.value(), hashMap);
        }
        jeg.a();
        RadarImageView radarImageView = this.az;
        if (radarImageView != null) {
            radarImageView.d();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void ad() {
        try {
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
            BroadcastManagerUtil.bFB_(this, this.an, intentFilter);
        } catch (IllegalArgumentException unused) {
            LogUtil.e("OneKeyScanActivity", "handleDownloadNetError register receiver is error");
        }
    }

    private void ap() {
        try {
            if (this.an != null) {
                LogUtil.d("OneKeyScanActivity", "unregister mNetBroadcastReceiver");
                unregisterReceiver(this.an);
            }
        } catch (IllegalArgumentException unused) {
            LogUtil.e("OneKeyScanActivity", "unregisterNetBroadcast is error");
        }
    }

    private void aq() {
        try {
            if (this.ak != null) {
                LogUtil.d("OneKeyScanActivity", "mPairBroadcastReceiver mReceiver != null");
                LocalBroadcastManager.getInstance(BaseApplication.getContext()).unregisterReceiver(this.ak);
            }
        } catch (IllegalArgumentException unused) {
            LogUtil.e("OneKeyScanActivity", "unregisterPairBroadcast is error");
        }
    }

    private void ak() {
        ap();
        aq();
    }

    @Override // com.huawei.health.devicemgr.business.entity.downloadresouce.DownloadResultCallBack
    public void setDownloadStatus(int i, int i2) {
        a aVar = this.al;
        if (aVar == null) {
            LogUtil.c("OneKeyScanActivity", "mMyHandler is null");
            return;
        }
        if (i == 1) {
            aVar.sendEmptyMessage(100);
            LogUtil.d("OneKeyScanActivity", "setDownloadStatus , index_all download Success");
            return;
        }
        if (i == 0) {
            Message obtain = Message.obtain();
            obtain.what = 102;
            obtain.obj = Integer.valueOf(i2);
            a aVar2 = this.al;
            if (aVar2 != null) {
                aVar2.sendMessage(obtain);
                return;
            }
            return;
        }
        if (i == -2) {
            aVar.sendEmptyMessage(103);
            return;
        }
        Message obtain2 = Message.obtain();
        obtain2.what = 101;
        obtain2.obj = Integer.valueOf(i);
        this.al.sendMessage(obtain2);
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        LogUtil.d("OneKeyScanActivity", "onActivityResult, requestCode: ", Integer.valueOf(i), "resultCode: ", Integer.valueOf(i2));
        if (i == 3) {
            al();
            return;
        }
        if (i == 2) {
            if (obb.b(this)) {
                LogUtil.d("OneKeyScanActivity", "onActivityResult permission start scan");
                al();
                return;
            } else {
                LogUtil.c("OneKeyScanActivity", "gps permission is not open");
                return;
            }
        }
        if (i == 1) {
            cOG_(i2, intent);
        }
        if (i == 4 && i2 == 1) {
            finish();
        }
        if (i == 102 && i()) {
            al();
        }
        if (i == 1001 && i2 == 3) {
            if (intent == null) {
                LogUtil.c("OneKeyScanActivity", "data is null");
            } else {
                this.bo.cOt_(this.j, this.ag, this.bq, intent, this.ak);
            }
        }
    }

    private void cOG_(int i, Intent intent) {
        int i2;
        if (i != 2) {
            if (i == 3) {
                e();
                return;
            }
            return;
        }
        setResult(2);
        ntt.c();
        String str = this.g;
        if (str != null) {
            i2 = jfu.c(str);
            LogUtil.d("OneKeyScanActivity", "mDeviceName is ", this.g, " deviceType is ", Integer.valueOf(i2));
        } else {
            i2 = -1;
        }
        nue.cNU_(intent, this, nue.e(i, false, i2, true));
        e();
    }

    private void e() {
        if (isFinishing()) {
            return;
        }
        finish();
    }

    private List<bjf> e(cve cveVar) {
        ArrayList arrayList = new ArrayList(16);
        if (koq.b(cveVar.e())) {
            return arrayList;
        }
        for (String str : cveVar.e()) {
            bjf.a aVar = new bjf.a();
            int d = this.bo.d(cveVar.b());
            if (d != 0) {
                aVar.e(d);
                aVar.a(str);
                arrayList.add(aVar.a());
            }
        }
        if (cveVar.aa() != null && !koq.b(this.bq)) {
            LogUtil.d("OneKeyScanActivity", "bluetoothNameSecondDeal: ", this.bq.get(0));
            c(cveVar, arrayList);
        }
        if (cez.c()) {
            dks.c(arrayList, this.bo.d(cveVar.b()));
        }
        return arrayList;
    }

    private void c(cve cveVar, List<bjf> list) {
        cvm cvmVar = cveVar.aa().get(this.bq.get(0));
        if (cvmVar == null) {
            return;
        }
        List<String> b = cvmVar.b();
        String d = cvmVar.d();
        if (b == null || b.size() <= 0) {
            return;
        }
        list.clear();
        for (String str : b) {
            bjf.a aVar = new bjf.a();
            int d2 = this.bo.d(d);
            if (d2 != 0) {
                aVar.e(d2);
                aVar.a(str);
                list.add(aVar.a());
            }
        }
    }

    private String d(String str) {
        File c;
        cuy indexBean = ((DownloadManagerApi) Services.c("DownloadDeviceResource", DownloadManagerApi.class)).getIndexBean();
        if (indexBean == null) {
            return "";
        }
        String d = indexBean.d();
        if (TextUtils.isEmpty(d)) {
            return "";
        }
        String str2 = cvy.b() + d;
        File e = cvy.e(str2);
        if (e == null || (c = cvy.c(str2)) == null) {
            return "";
        }
        List<String> c2 = cvy.c(str, cvy.b(e), cvy.b(c));
        if (c2.size() > 0) {
            return c2.get(0);
        }
        LogUtil.c("OneKeyScanActivity", "getDeviceContext,value is empty");
        return "";
    }

    public static class a extends Handler {

        /* renamed from: a, reason: collision with root package name */
        WeakReference<OneKeyScanActivity> f9031a;

        a(OneKeyScanActivity oneKeyScanActivity) {
            this.f9031a = new WeakReference<>(oneKeyScanActivity);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            OneKeyScanActivity oneKeyScanActivity = this.f9031a.get();
            if (oneKeyScanActivity == null) {
                LogUtil.e("OneKeyScanActivity", "handleMessage mActivity is null");
            }
            if (message != null) {
                super.handleMessage(message);
                ReleaseLogUtil.e("R_OneKeyScanActivity", "handleMessage msg.what :", Integer.valueOf(message.what));
                int i = message.what;
                if (i == 1) {
                    if (message.obj instanceof nyr) {
                        oneKeyScanActivity.c((nyr) message.obj);
                        return;
                    }
                    return;
                }
                if (i == 2) {
                    oneKeyScanActivity.ae = false;
                    return;
                }
                if (i == 3) {
                    d(oneKeyScanActivity);
                    return;
                }
                if (i == 4) {
                    oneKeyScanActivity.al();
                    return;
                }
                if (i == 5) {
                    oneKeyScanActivity.o();
                    return;
                }
                switch (i) {
                    case 100:
                        e(oneKeyScanActivity);
                        break;
                    case 101:
                        sqo.ak("download error");
                        cOI_(message, oneKeyScanActivity);
                        break;
                    case 102:
                        cOJ_(message, oneKeyScanActivity);
                        break;
                    case 103:
                        a(oneKeyScanActivity);
                        break;
                    case 104:
                        b(oneKeyScanActivity);
                        break;
                    default:
                        LogUtil.c("OneKeyScanActivity", "handleMessage default");
                        break;
                }
            }
        }

        private void b(OneKeyScanActivity oneKeyScanActivity) {
            if (oneKeyScanActivity.v && oneKeyScanActivity.ad && new File(cvy.a()).exists() && !oneKeyScanActivity.w) {
                oneKeyScanActivity.w = true;
                oneKeyScanActivity.al();
            }
        }

        private void e(OneKeyScanActivity oneKeyScanActivity) {
            oneKeyScanActivity.v = false;
            oneKeyScanActivity.r.setVisibility(8);
            oneKeyScanActivity.ap.setVisibility(8);
            oneKeyScanActivity.am.setVisibility(8);
            oneKeyScanActivity.as = 100;
            oneKeyScanActivity.w = true;
            oneKeyScanActivity.y = false;
            oneKeyScanActivity.x = false;
            if (oneKeyScanActivity.bo != null) {
                HashMap hashMap = new HashMap(16);
                hashMap.put("download_result", "0");
                oneKeyScanActivity.bo.d(AnalyticsValue.ONE_KEY_SCAN_RESOURCE_DOWNLOAD_RESULT.value(), hashMap);
            }
            if (oneKeyScanActivity.ad) {
                oneKeyScanActivity.al();
            }
        }

        private void cOJ_(Message message, OneKeyScanActivity oneKeyScanActivity) {
            if (message.obj instanceof Integer) {
                oneKeyScanActivity.v = true;
                oneKeyScanActivity.r.setVisibility(0);
                oneKeyScanActivity.am.setVisibility(8);
                oneKeyScanActivity.am.setVisibility(8);
                int intValue = ((Integer) message.obj).intValue();
                oneKeyScanActivity.r.setProgress(intValue);
                oneKeyScanActivity.as = intValue;
            }
        }

        private void cOI_(Message message, OneKeyScanActivity oneKeyScanActivity) {
            oneKeyScanActivity.v = false;
            oneKeyScanActivity.r.setVisibility(8);
            oneKeyScanActivity.x = true;
            oneKeyScanActivity.y = false;
            if (message.obj instanceof Integer) {
                int intValue = ((Integer) message.obj).intValue();
                oneKeyScanActivity.as = 0;
                if (intValue == -3) {
                    oneKeyScanActivity.au.setText(R.string._2130844862_res_0x7f021cbe);
                } else {
                    oneKeyScanActivity.au.setText(R.string.IDS_no_device_res);
                }
            }
            if (oneKeyScanActivity.bo != null) {
                HashMap hashMap = new HashMap(16);
                hashMap.put("download_result", "1");
                oneKeyScanActivity.bo.d(AnalyticsValue.ONE_KEY_SCAN_RESOURCE_DOWNLOAD_RESULT.value(), hashMap);
                oau.c(100099, "");
            }
            if (oneKeyScanActivity.ad) {
                oneKeyScanActivity.al();
            }
        }

        private void a(OneKeyScanActivity oneKeyScanActivity) {
            oneKeyScanActivity.v = false;
            oneKeyScanActivity.ad();
            oneKeyScanActivity.r.setVisibility(8);
            oneKeyScanActivity.x = false;
            oneKeyScanActivity.y = true;
            oneKeyScanActivity.as = 0;
            if (oneKeyScanActivity.bo != null) {
                HashMap hashMap = new HashMap(16);
                hashMap.put("download_result", "2");
                oneKeyScanActivity.bo.d(AnalyticsValue.ONE_KEY_SCAN_RESOURCE_DOWNLOAD_RESULT.value(), hashMap);
            }
            if (oneKeyScanActivity.ad) {
                oneKeyScanActivity.al();
            }
        }

        private void d(OneKeyScanActivity oneKeyScanActivity) {
            oneKeyScanActivity.az.d();
            LogUtil.d("OneKeyScanActivity", "scan failed");
            oneKeyScanActivity.bl.setText(R.string._2130844156_res_0x7f0219fc);
            oneKeyScanActivity.bl.setTextColor(oneKeyScanActivity.getResources().getColor(R.color._2131296651_res_0x7f09018b));
            oneKeyScanActivity.bl.setTextSize(R.dimen._2131365064_res_0x7f0a0cc8);
            oneKeyScanActivity.bj.setVisibility(0);
            i(oneKeyScanActivity);
            c(oneKeyScanActivity);
            g(oneKeyScanActivity);
            oneKeyScanActivity.ae = false;
        }

        private void c(OneKeyScanActivity oneKeyScanActivity) {
            if (!oneKeyScanActivity.d.a().isEmpty() && oneKeyScanActivity.d.a().size() > 1) {
                oneKeyScanActivity.bk.setText(R.string._2130844157_res_0x7f0219fd);
                oneKeyScanActivity.bm.setText(R.string._2130844157_res_0x7f0219fd);
                oneKeyScanActivity.bj.setVisibility(0);
                oneKeyScanActivity.bd.setVisibility(0);
                oneKeyScanActivity.bc = 0;
                return;
            }
            OneKeyScanActivity.as(oneKeyScanActivity);
            oneKeyScanActivity.bk.setText(R.string.IDS_no_device_found);
            oneKeyScanActivity.bm.setText(R.string.IDS_no_device_found);
            oneKeyScanActivity.bd.setVisibility(8);
            oneKeyScanActivity.bj.setVisibility(8);
            if (!koq.b(oneKeyScanActivity.bq) || oneKeyScanActivity.bc < 2) {
                return;
            }
            oneKeyScanActivity.bo.b(oneKeyScanActivity.j, oneKeyScanActivity.bl);
        }

        private void i(final OneKeyScanActivity oneKeyScanActivity) {
            if (oneKeyScanActivity.bl.hasOnClickListeners()) {
                return;
            }
            oneKeyScanActivity.bl.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.ui.device.activity.adddevice.OneKeyScanActivity.a.4
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    LogUtil.d("OneKeyScanActivity", "restart scan");
                    if (oneKeyScanActivity.i()) {
                        if (!koq.b(oneKeyScanActivity.bq)) {
                            oneKeyScanActivity.al();
                            ViewClickInstrumentation.clickOnView(view);
                            return;
                        } else {
                            if (oneKeyScanActivity.bc < 2) {
                                oneKeyScanActivity.al();
                            }
                            ViewClickInstrumentation.clickOnView(view);
                            return;
                        }
                    }
                    oneKeyScanActivity.startActivityForResult(new Intent("android.bluetooth.adapter.action.REQUEST_ENABLE"), 102);
                    ViewClickInstrumentation.clickOnView(view);
                }
            });
        }

        private void g(final OneKeyScanActivity oneKeyScanActivity) {
            if (oneKeyScanActivity.z) {
                oneKeyScanActivity.ba.setVisibility(4);
            } else {
                oneKeyScanActivity.ba.setVisibility(0);
            }
            if (oneKeyScanActivity.ba.hasOnClickListeners()) {
                return;
            }
            oneKeyScanActivity.ba.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.ui.device.activity.adddevice.OneKeyScanActivity.a.2
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    if (oneKeyScanActivity.i()) {
                        oneKeyScanActivity.al();
                        ViewClickInstrumentation.clickOnView(view);
                    } else {
                        oneKeyScanActivity.startActivityForResult(new Intent("android.bluetooth.adapter.action.REQUEST_ENABLE"), 102);
                        ViewClickInstrumentation.clickOnView(view);
                    }
                }
            });
        }
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        super.onBackPressed();
        LogUtil.d("OneKeyScanActivity", "cancel to select device");
        ObserverManagerUtil.c("DEVICE_ASSOCIATION", 152);
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
