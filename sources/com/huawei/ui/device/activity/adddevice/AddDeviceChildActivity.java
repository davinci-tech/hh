package com.huawei.ui.device.activity.adddevice;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.health.R;
import com.huawei.health.device.manager.DeviceCloudSharePreferencesManager;
import com.huawei.health.device.util.EventBus;
import com.huawei.health.devicemgr.api.mainprocess.DownloadManagerApi;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.health.ecologydevice.manager.ResourceManager;
import com.huawei.health.ecologydevice.ui.measure.fragment.device.DeviceCategoryFragment;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hms.network.httpclient.util.PreConnectManager;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.hwversionmgr.manager.HwVersionManager;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.openalliance.ad.db.bean.ContentResource;
import com.huawei.openalliance.ad.db.bean.TemplateStyleRecord;
import com.huawei.operation.OpAnalyticsConstants;
import com.huawei.operation.OpAnalyticsUtil;
import com.huawei.pluginmgr.filedownload.PullListener;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.dialog.CommonDialog21;
import com.huawei.ui.commonui.dialog.CustomProgressDialog;
import com.huawei.ui.commonui.dialog.CustomTextAlertDialog;
import com.huawei.ui.commonui.dialog.NoTitleCustomAlertDialog;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.titlebar.CustomTitleBar;
import com.huawei.ui.device.activity.pairing.DevicePairGuideActivity;
import com.huawei.ui.device.views.adddevice.SelectDeviceListAdapter;
import com.huawei.up.utils.ErrorCode;
import defpackage.cfl;
import defpackage.cpa;
import defpackage.cpp;
import defpackage.cus;
import defpackage.cuw;
import defpackage.cux;
import defpackage.cvc;
import defpackage.cvj;
import defpackage.cvk;
import defpackage.cwc;
import defpackage.dcq;
import defpackage.dcz;
import defpackage.ixx;
import defpackage.jed;
import defpackage.jfq;
import defpackage.jfu;
import defpackage.jfy;
import defpackage.jgh;
import defpackage.jis;
import defpackage.jiu;
import defpackage.jph;
import defpackage.jpt;
import defpackage.koq;
import defpackage.msj;
import defpackage.msn;
import defpackage.mso;
import defpackage.msq;
import defpackage.nrh;
import defpackage.nsn;
import defpackage.nsy;
import defpackage.ntt;
import defpackage.nue;
import defpackage.oad;
import defpackage.oae;
import defpackage.obu;
import health.compact.a.CommonUtil;
import health.compact.a.EzPluginManager;
import health.compact.a.Services;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;
import health.compact.a.UnitUtil;
import health.compact.a.Utils;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.io.File;
import java.lang.ref.WeakReference;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes6.dex */
public class AddDeviceChildActivity extends BaseActivity {
    private boolean aa;
    private CommonDialog21 ae;
    private a ag;
    private ListView ah;
    private int aj;
    private String al;
    private Context d;
    private CustomProgressDialog f;
    private CustomTitleBar g;
    private CustomProgressDialog.Builder i;
    private oae j;
    private CustomTextAlertDialog k;
    private RelativeLayout l;
    private HealthTextView n;
    private CustomTextAlertDialog o;
    private static final Object e = new Object();
    private static final Object b = new Object();
    private static final Object c = new Object();
    private ArrayList<obu> ai = new ArrayList<>(16);
    private SelectDeviceListAdapter af = null;
    private String an = "";
    private boolean ad = false;
    private int ao = 0;
    private int ak = 0;
    private boolean v = false;
    private boolean w = false;
    private boolean u = false;
    private volatile List<cvk> aq = new ArrayList(16);
    private volatile List<cvk> r = new ArrayList(16);
    private volatile int ar = 0;
    private volatile int m = 0;
    private volatile int am = 0;
    private String h = "";
    private boolean s = false;
    private boolean x = false;
    private boolean y = false;
    private boolean ac = false;
    private ArrayList<obu> t = new ArrayList<>(16);
    private ArrayList<obu> q = new ArrayList<>(16);
    private int ab = 0;
    private long z = 0;

    /* renamed from: a, reason: collision with root package name */
    private DownloadManagerApi f8995a = (DownloadManagerApi) Services.c("DownloadDeviceResource", DownloadManagerApi.class);
    private EventBus.ICallback p = new EventBus.ICallback() { // from class: com.huawei.ui.device.activity.adddevice.AddDeviceChildActivity.4
        @Override // com.huawei.health.device.util.EventBus.ICallback
        public void onEvent(EventBus.b bVar) {
            if (bVar == null) {
                LogUtil.a("PluginDevice_AddDeviceChildActivity", "event is null");
            } else if (!AddDeviceChildActivity.this.isFinishing()) {
                AddDeviceChildActivity.this.j(bVar.e());
            } else {
                LogUtil.h("PluginDevice_AddDeviceChildActivity", "activity is null");
            }
        }
    };

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        cancelAdaptRingRegion();
        this.d = BaseApplication.getContext();
        LogUtil.a("PluginDevice_AddDeviceChildActivity", "onCreate()");
        getWindow().setFlags(16777216, 16777216);
        EventBus.d(this.p, 2, "device_download_dialog", "single_device_download_dialog");
        this.w = false;
        this.u = false;
        this.ag = new a(this);
        h();
        o();
        Intent intent = getIntent();
        if (intent != null) {
            this.w = intent.getBooleanExtra("isFromWear", false);
            this.u = intent.getBooleanExtra("isFromWearR1", false);
            LogUtil.a("PluginDevice_AddDeviceChildActivity", "mIsFromWear: ", Boolean.valueOf(this.w), " mIsFromWearR1:", Boolean.valueOf(this.u));
            if (!this.u && cNs_(intent)) {
                return;
            }
        }
        r();
        int i = this.ao;
        if (i == 1 || i == 2) {
            jph.bIM_(this, -1);
        }
        if (intent != null && !TextUtils.isEmpty(intent.getStringExtra("pairGuideSelectAddress"))) {
            d(intent.getStringExtra("pairGuideSelectAddress"));
        } else {
            LogUtil.a("PluginDevice_AddDeviceChildActivity", "intent is null");
        }
    }

    private boolean cNs_(Intent intent) {
        if (!this.w) {
            return false;
        }
        int intExtra = intent.getIntExtra(DeviceCategoryFragment.DEVICE_TYPE, -10);
        if (intExtra == -10) {
            return true;
        }
        e(intExtra);
        return true;
    }

    private void o() {
        jfq.c();
        jiu.c();
        jgh.d(this.d);
        jfy.b();
        jis.b();
        if (this.d != null) {
            this.j = oae.c(BaseApplication.getContext());
        }
        Intent intent = getIntent();
        if (intent != null) {
            boolean booleanExtra = intent.getBooleanExtra("KEY_OUTSIDE_OPEN_ACTIVITY_FLAG", false);
            this.ad = booleanExtra;
            LogUtil.a("PluginDevice_AddDeviceChildActivity", "mIsOutsideOpenApp: ", Boolean.valueOf(booleanExtra));
        }
    }

    private void r() {
        setContentView(R.layout.activity_add_device_child);
        CustomTitleBar customTitleBar = (CustomTitleBar) nsy.cMc_(this, R.id.select_device_detail_title_bar);
        this.g = customTitleBar;
        customTitleBar.setVisibility(0);
        this.g.setLeftButtonOnClickListener(new View.OnClickListener() { // from class: com.huawei.ui.device.activity.adddevice.AddDeviceChildActivity.15
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Intent intent = AddDeviceChildActivity.this.getIntent();
                if (intent != null && "com.huawei.ui.device.activity.adddevice.AddDeviceChildActivity".equals(intent.getAction())) {
                    AddDeviceChildActivity.this.u();
                } else {
                    AddDeviceChildActivity.this.finish();
                }
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        this.ah = (ListView) nsy.cMc_(this, R.id.list_setup_device);
        RelativeLayout relativeLayout = (RelativeLayout) nsy.cMc_(this, R.id.addDevice_error_layout);
        this.l = relativeLayout;
        relativeLayout.setVisibility(8);
        this.l.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.ui.device.activity.adddevice.AddDeviceChildActivity.19
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                AddDeviceChildActivity.this.s = true;
                if (CommonUtil.aa(AddDeviceChildActivity.this.d)) {
                    AddDeviceChildActivity.this.y();
                    ViewClickInstrumentation.clickOnView(view);
                } else {
                    LogUtil.a("PluginDevice_AddDeviceChildActivity", "Network is not Connected ");
                    nrh.b(AddDeviceChildActivity.this.d, R.string._2130841392_res_0x7f020f30);
                    ViewClickInstrumentation.clickOnView(view);
                }
            }
        });
        this.n = (HealthTextView) nsy.cMc_(this, R.id.adddevice_error_text);
        a();
        if (this.ad) {
            p();
        } else {
            s();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void u() {
        Intent intent = new Intent();
        intent.setAction("com.huawei.commonui.CLEAN_ACTIVITY");
        if (LocalBroadcastManager.getInstance(this.d) != null) {
            LocalBroadcastManager.getInstance(this.d).sendBroadcast(intent);
        }
    }

    private void ab() {
        NoTitleCustomAlertDialog.Builder d = d();
        d.czC_(R.string.IDS_device_to_go_into_app_market, new View.OnClickListener() { // from class: com.huawei.ui.device.activity.adddevice.AddDeviceChildActivity.17
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ntt.cNE_(AddDeviceChildActivity.this.d, AddDeviceChildActivity.this);
                ViewClickInstrumentation.clickOnView(view);
            }
        }).czz_(R.string._2130841130_res_0x7f020e2a, new View.OnClickListener() { // from class: com.huawei.ui.device.activity.adddevice.AddDeviceChildActivity.20
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        NoTitleCustomAlertDialog e2 = d.e();
        e2.setCancelable(false);
        e2.show();
    }

    private void ac() {
        NoTitleCustomAlertDialog.Builder d = d();
        d.czC_(R.string._2130841554_res_0x7f020fd2, new View.OnClickListener() { // from class: com.huawei.ui.device.activity.adddevice.AddDeviceChildActivity.18
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LogUtil.a("PluginDevice_AddDeviceChildActivity", "click known button");
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        NoTitleCustomAlertDialog e2 = d.e();
        e2.setCancelable(false);
        e2.show();
    }

    private void aa() {
        NoTitleCustomAlertDialog.Builder d = d();
        d.czC_(R.string.IDS_device_to_go_into_app_market, new View.OnClickListener() { // from class: com.huawei.ui.device.activity.adddevice.AddDeviceChildActivity.24
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ntt.cND_(AddDeviceChildActivity.this.d, AddDeviceChildActivity.this);
                ViewClickInstrumentation.clickOnView(view);
            }
        }).czz_(R.string._2130845098_res_0x7f021daa, new View.OnClickListener() { // from class: com.huawei.ui.device.activity.adddevice.AddDeviceChildActivity.25
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        NoTitleCustomAlertDialog e2 = d.e();
        e2.setCancelable(false);
        e2.show();
    }

    private NoTitleCustomAlertDialog.Builder d() {
        LogUtil.a("PluginDevice_AddDeviceChildActivity", "enter buildNoTitleCustomAlertDialog");
        return new NoTitleCustomAlertDialog.Builder(this).e(this.d.getResources().getString(R.string.IDS_device_mgr_not_found_device_tips1) + System.lineSeparator() + this.d.getResources().getString(R.string.IDS_device_mgr_not_found_device_tips2, jed.b(1.0d, 1, 0)) + System.lineSeparator() + this.d.getResources().getString(R.string.IDS_device_mgr_not_found_device_tips3, jed.b(2.0d, 1, 0), nsn.a(BaseApplication.getContext()), BaseApplication.getContext().getString(R.string.IDS_select_device_b1_name), BaseApplication.getContext().getString(R.string.IDS_select_device_b2_name), BaseApplication.getContext().getString(R.string.IDS_app_display_name_w1)));
    }

    private void s() {
        Intent intent = getIntent();
        if (intent != null) {
            this.ao = intent.getIntExtra(TemplateStyleRecord.STYLE, 0);
            this.ak = intent.getIntExtra("isHeartRateDevice", 0);
            LogUtil.a("PluginDevice_AddDeviceChildActivity", "initListView() style : ", Integer.valueOf(this.ao));
            int i = this.ao;
            if (i == 1) {
                c(this.ak == 1);
                return;
            }
            if (i == 2) {
                b(this.ak == 1);
            } else if (i == 4) {
                t();
            } else {
                LogUtil.a("PluginDevice_AddDeviceChildActivity", "unknown style");
            }
        }
    }

    private void c(boolean z) {
        LogUtil.a("PluginDevice_AddDeviceChildActivity", "initViewForWatch");
        this.g.setTitleText(this.d.getString(R.string.IDS_add_device_smart_watch));
        if (!this.t.isEmpty()) {
            LogUtil.a("PluginDevice_AddDeviceChildActivity", "initViewForWatch() mHwLists.clear()");
            this.t.clear();
        }
        if (!this.q.isEmpty()) {
            LogUtil.a("PluginDevice_AddDeviceChildActivity", "initViewForWatch() mHonorLists.clear()");
            this.q.clear();
        }
        for (cuw cuwVar : oad.e(z)) {
            LogUtil.a("PluginDevice_AddDeviceChildActivity", "initViewForWatch() type:", Integer.valueOf(cuwVar.m()));
            d(cuwVar.m(), cuwVar.f(), cuwVar.h(), cuwVar.v(), cuwVar.p());
        }
        ad();
        b("SMART_WATCH", true);
        a("SMART_WATCH");
    }

    private void b(boolean z) {
        LogUtil.a("PluginDevice_AddDeviceChildActivity", "initViewForBand");
        this.g.setTitleText(this.d.getString(R.string.IDS_add_device_smart_band));
        if (!this.t.isEmpty()) {
            LogUtil.a("PluginDevice_AddDeviceChildActivity", "initViewForBand() mHwLists.clear()");
            this.t.clear();
        }
        if (!this.q.isEmpty()) {
            LogUtil.a("PluginDevice_AddDeviceChildActivity", "initViewForBand() mHonorLists.clear()");
            this.q.clear();
        }
        for (cuw cuwVar : oad.a(z)) {
            LogUtil.a("PluginDevice_AddDeviceChildActivity", "initViewForBand() type:", Integer.valueOf(cuwVar.m()));
            d(cuwVar.m(), cuwVar.f(), cuwVar.h(), cuwVar.v(), cuwVar.p());
        }
        ad();
        b("SMART_BAND", true);
        a("SMART_BAND");
    }

    private void a(String str) {
        Message obtain = Message.obtain();
        obtain.what = 90;
        obtain.obj = str;
        this.ag.sendMessage(obtain);
    }

    private void a() {
        View inflate = LayoutInflater.from(this.d).inflate(R.layout.activity_add_device_button, (ViewGroup) null);
        inflate.findViewById(R.id.not_found_device_tip).setOnClickListener(new View.OnClickListener() { // from class: com.huawei.ui.device.activity.adddevice.AddDeviceChildActivity.23
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                AddDeviceChildActivity.this.w();
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        this.ah.addFooterView(inflate);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void w() {
        boolean bh = CommonUtil.bh();
        boolean o = Utils.o();
        LogUtil.a("PluginDevice_AddDeviceChildActivity", "isHuaweiSystem ", Boolean.valueOf(bh), ",isOverSea ", Boolean.valueOf(o));
        if (bh && !o) {
            ab();
        } else if (o) {
            aa();
        } else {
            ac();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void x() {
        this.ai.clear();
        if (this.t.size() > 0) {
            obu obuVar = new obu(0);
            obuVar.d(this.d.getString(R.string.IDS_device_huawei_band));
            obuVar.d(false);
            this.ai.add(obuVar);
        }
        if (this.x) {
            this.ai.addAll(this.t);
        } else if (this.t.size() > 3) {
            this.ai.addAll(this.t.subList(0, 3));
            n();
        } else {
            this.ai.addAll(this.t);
        }
        if (this.q.size() > 0) {
            obu obuVar2 = new obu(0);
            obuVar2.d(this.d.getString(R.string.IDS_device_honor_band));
            obuVar2.d(true);
            this.ai.add(obuVar2);
        }
        if (this.y) {
            this.ai.addAll(this.q);
        } else if (this.q.size() > 3) {
            this.ai.addAll(this.q.subList(0, 3));
            obu obuVar3 = new obu(2);
            obuVar3.cUT_(new View.OnClickListener() { // from class: com.huawei.ui.device.activity.adddevice.AddDeviceChildActivity.3
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    AddDeviceChildActivity.this.y = true;
                    LogUtil.a("PluginDevice_AddDeviceChildActivity", " listNotifyDataSetChanged mIsClickRyDeviceMore ");
                    AddDeviceChildActivity.this.x();
                    ViewClickInstrumentation.clickOnView(view);
                }
            });
            this.ai.add(obuVar3);
        } else {
            this.ai.addAll(this.q);
        }
        this.af.a(this.ai);
        this.af.notifyDataSetChanged();
    }

    private void n() {
        obu obuVar = new obu(2);
        obuVar.cUT_(new View.OnClickListener() { // from class: com.huawei.ui.device.activity.adddevice.AddDeviceChildActivity.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                AddDeviceChildActivity.this.x = true;
                LogUtil.a("PluginDevice_AddDeviceChildActivity", " listNotifyDataSetChanged mIsClickHwDeviceMore ");
                AddDeviceChildActivity.this.x();
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        this.ai.add(obuVar);
    }

    private void ad() {
        if (this.t.size() > 0) {
            obu obuVar = new obu(0);
            obuVar.d(this.d.getString(R.string.IDS_device_huawei_band));
            obuVar.d(false);
            this.ai.add(obuVar);
        }
        if (this.t.size() > 3) {
            this.ai.addAll(this.t.subList(0, 3));
            obu obuVar2 = new obu(2);
            obuVar2.cUT_(new View.OnClickListener() { // from class: com.huawei.ui.device.activity.adddevice.AddDeviceChildActivity.2
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    LogUtil.a("PluginDevice_AddDeviceChildActivity", " listNotifyDataSetChanged mIsClickHwDeviceMore ");
                    AddDeviceChildActivity.this.x = true;
                    AddDeviceChildActivity.this.x();
                    ViewClickInstrumentation.clickOnView(view);
                }
            });
            this.ai.add(obuVar2);
        } else {
            this.ai.addAll(this.t);
        }
        if (this.q.size() > 0) {
            obu obuVar3 = new obu(0);
            obuVar3.d(this.d.getString(R.string.IDS_device_honor_band));
            obuVar3.d(true);
            this.ai.add(obuVar3);
        }
        if (this.q.size() > 3) {
            this.ai.addAll(this.q.subList(0, 3));
            obu obuVar4 = new obu(2);
            obuVar4.cUT_(new View.OnClickListener() { // from class: com.huawei.ui.device.activity.adddevice.AddDeviceChildActivity.5
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    LogUtil.a("PluginDevice_AddDeviceChildActivity", " listNotifyDataSetChanged mIsClickRyDeviceMore ");
                    AddDeviceChildActivity.this.y = true;
                    AddDeviceChildActivity.this.x();
                    ViewClickInstrumentation.clickOnView(view);
                }
            });
            this.ai.add(obuVar4);
        } else {
            this.ai.addAll(this.q);
        }
        SelectDeviceListAdapter selectDeviceListAdapter = new SelectDeviceListAdapter(this.ai);
        this.af = selectDeviceListAdapter;
        this.ah.setAdapter((ListAdapter) selectDeviceListAdapter);
    }

    private void g(String str) {
        this.h = str;
        EzPluginManager.a().b(new PullListener() { // from class: com.huawei.ui.device.activity.adddevice.AddDeviceChildActivity.7
            @Override // com.huawei.pluginmgr.filedownload.PullListener
            public void onPullingChange(msq msqVar, mso msoVar) {
                if (msoVar == null) {
                    LogUtil.h("PluginDevice_AddDeviceChildActivity", "onPullingChange result is null");
                    return;
                }
                LogUtil.a("PluginDevice_AddDeviceChildActivity", "Update the status of the index file:", Integer.valueOf(msoVar.i()));
                int i = msoVar.i();
                AddDeviceChildActivity.this.d(AnalyticsValue.DEVICE_INDEX_FILE_2180010.value(), i, "index");
                if (i == 1 || i == -11) {
                    EzPluginManager.a().e();
                    AddDeviceChildActivity addDeviceChildActivity = AddDeviceChildActivity.this;
                    addDeviceChildActivity.e(addDeviceChildActivity.h);
                    return;
                }
                if (i == -1) {
                    if (!koq.b(AddDeviceChildActivity.this.f8995a.getIndexList())) {
                        LogUtil.a("PluginDevice_AddDeviceChildActivity", "Updating the index fails and the UI is not updated");
                    }
                    if (AddDeviceChildActivity.this.ag != null) {
                        Message obtain = Message.obtain();
                        obtain.what = 50;
                        AddDeviceChildActivity.this.ag.sendMessage(obtain);
                        return;
                    }
                    return;
                }
                if (i == -5) {
                    AddDeviceChildActivity.this.al();
                    if (AddDeviceChildActivity.this.ag != null) {
                        AddDeviceChildActivity.this.ag.removeMessages(70);
                        Message obtain2 = Message.obtain();
                        obtain2.what = 80;
                        AddDeviceChildActivity.this.ag.sendMessage(obtain2);
                    }
                    LogUtil.a("PluginDevice_AddDeviceChildActivity", "The server does not find any resource.");
                    return;
                }
                LogUtil.a("PluginDevice_AddDeviceChildActivity", "unknown error");
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(String str) {
        g();
        List<cvk> indexList = this.f8995a.getIndexList();
        if (koq.b(indexList)) {
            LogUtil.a("PluginDevice_AddDeviceChildActivity", "have no index info");
            return;
        }
        for (cvk cvkVar : indexList) {
            if (cvkVar.g() != null && TextUtils.equals(cvkVar.g(), str)) {
                LogUtil.a("PluginDevice_AddDeviceChildActivity", "has band plugin info, uuid :" + cvkVar.e());
                LogUtil.a("PluginDevice_AddDeviceChildActivity", "publish mode :" + cvkVar.f());
                if (!c(cvkVar)) {
                    if (Utils.o()) {
                        if (TextUtils.equals(cvkVar.f(), "2") || TextUtils.equals(cvkVar.f(), "3")) {
                            d(cvkVar);
                        }
                    } else if (TextUtils.equals(cvkVar.f(), "1") || TextUtils.equals(cvkVar.f(), "3")) {
                        d(cvkVar);
                    }
                }
            }
        }
        if (l() == 0) {
            LogUtil.a("PluginDevice_AddDeviceChildActivity", "have no band plugin info");
            al();
            this.ag.removeMessages(70);
            return;
        }
        c(m());
    }

    private boolean c(cvk cvkVar) {
        if (!CommonUtil.as() || (!cvkVar.g().equals("SMART_WATCH") && !cvkVar.g().equals("SMART_BAND"))) {
            return false;
        }
        d(cvkVar);
        return true;
    }

    private void c(List<cvk> list) {
        LogUtil.a("PluginDevice_AddDeviceChildActivity", "enter checkIsNeedUpdate");
        for (final cvk cvkVar : list) {
            if (EzPluginManager.a().g(cvkVar.e())) {
                EzPluginManager.a().b(cvkVar.e(), new PullListener() { // from class: com.huawei.ui.device.activity.adddevice.AddDeviceChildActivity.8
                    @Override // com.huawei.pluginmgr.filedownload.PullListener
                    public void onPullingChange(msq msqVar, mso msoVar) {
                        if (msoVar == null) {
                            LogUtil.a("PluginDevice_AddDeviceChildActivity", "onPullingChange result is null");
                        } else if (msoVar.i() == 1) {
                            AddDeviceChildActivity.this.a(cvkVar);
                        }
                    }
                });
            }
        }
        if (m().size() > 0) {
            d(m());
        } else {
            al();
            this.ag.removeMessages(70);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(cvk cvkVar) {
        LogUtil.a("PluginDevice_AddDeviceChildActivity", "check is remove plugin index info");
        e(cvkVar);
    }

    private void d(List<cvk> list) {
        LogUtil.a("PluginDevice_AddDeviceChildActivity", "enter updateDescriptionForWear");
        al();
        this.ag.removeMessages(70);
        Message obtain = Message.obtain();
        obtain.what = 20;
        this.ag.sendMessage(obtain);
        this.ar = 0;
        this.m = 0;
        for (final cvk cvkVar : list) {
            EzPluginManager.a().c(cvkVar.e(), new PullListener() { // from class: com.huawei.ui.device.activity.adddevice.AddDeviceChildActivity.9
                @Override // com.huawei.pluginmgr.filedownload.PullListener
                public void onPullingChange(msq msqVar, mso msoVar) {
                    if (msqVar == null || msoVar == null) {
                        LogUtil.h("PluginDevice_AddDeviceChildActivity", "onPullingChange task or result is null");
                        return;
                    }
                    LogUtil.a("PluginDevice_AddDeviceChildActivity", "update Description mTotalSize is :", Integer.valueOf(msoVar.j()), "pullSize is :", Integer.valueOf(msoVar.b()), "uuid is :", msqVar.o());
                    AddDeviceChildActivity.this.d(AnalyticsValue.DEVICE_DESCRIPTION_FILE_2180011.value(), msoVar.i(), cvkVar.e());
                    if (msoVar.i() != 1) {
                        AddDeviceChildActivity.this.e(cvkVar);
                        LogUtil.a("PluginDevice_AddDeviceChildActivity", "Failed to update the description file,The UI is not updated.");
                        ReleaseLogUtil.d("HWWEAR_PluginDevice_AddDeviceChildActivity", "updateDescriptionForWear failed");
                        if (AddDeviceChildActivity.this.ag != null) {
                            Message obtain2 = Message.obtain();
                            obtain2.what = 50;
                            AddDeviceChildActivity.this.ag.sendMessage(obtain2);
                            return;
                        }
                        return;
                    }
                    LogUtil.a("PluginDevice_AddDeviceChildActivity", "The description file is updated successfully.");
                    synchronized (AddDeviceChildActivity.b) {
                        AddDeviceChildActivity.this.ar += msoVar.j();
                        AddDeviceChildActivity.this.m++;
                        if (AddDeviceChildActivity.this.m == AddDeviceChildActivity.this.l()) {
                            LogUtil.a("PluginDevice_AddDeviceChildActivity", "Updated successfully. The total size of the resource:", Integer.valueOf(AddDeviceChildActivity.this.ar));
                            AddDeviceChildActivity.this.ak();
                        }
                    }
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int l() {
        int size;
        synchronized (c) {
            size = this.aq.size();
        }
        return size;
    }

    private void g() {
        synchronized (c) {
            this.aq.clear();
        }
    }

    private void d(cvk cvkVar) {
        synchronized (c) {
            this.aq.add(cvkVar);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(cvk cvkVar) {
        synchronized (c) {
            if (this.aq.contains(cvkVar)) {
                this.aq.remove(cvkVar);
            }
        }
    }

    private List<cvk> m() {
        ArrayList arrayList = new ArrayList(16);
        synchronized (c) {
            Iterator<cvk> it = this.aq.iterator();
            while (it.hasNext()) {
                arrayList.add(it.next());
            }
        }
        return arrayList;
    }

    private void ag() {
        CustomProgressDialog customProgressDialog = this.f;
        if (customProgressDialog != null && customProgressDialog.isShowing()) {
            LogUtil.a("PluginDevice_AddDeviceChildActivity", "The progress bar already exists");
            return;
        }
        this.f = new CustomProgressDialog(this);
        CustomProgressDialog.Builder builder = new CustomProgressDialog.Builder(this);
        this.i = builder;
        builder.d(this.d.getString(R.string._2130841851_res_0x7f0210fb)).cyH_(new View.OnClickListener() { // from class: com.huawei.ui.device.activity.adddevice.AddDeviceChildActivity.10
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LogUtil.a("PluginDevice_AddDeviceChildActivity", "Click Cancel");
                AddDeviceChildActivity.this.k();
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        CustomProgressDialog e2 = this.i.e();
        this.f = e2;
        e2.setCanceledOnTouchOutside(false);
        if (!isFinishing()) {
            this.f.show();
            this.i.d(0);
            this.i.e(UnitUtil.e(0.0d, 2, 0));
        }
        LogUtil.a("PluginDevice_AddDeviceChildActivity", "mCustomProgressDialog.show()");
    }

    private void g(int i) {
        CustomProgressDialog customProgressDialog = this.f;
        if (customProgressDialog == null || !customProgressDialog.isShowing() || this.ar == 0) {
            return;
        }
        int i2 = ((this.am + i) * 100) / this.ar;
        if (i2 > 99) {
            i2 = 99;
        }
        if (this.ab != i2) {
            LogUtil.a("PluginDevice_AddDeviceChildActivity", "showDownloadProgress downloaded ：", Integer.valueOf(this.am + i), " total : ", Integer.valueOf(this.ar), " showDownloadProgress progress:", Integer.valueOf(i2));
            this.ab = i2;
        }
        String e2 = UnitUtil.e(i2, 2, 0);
        this.i.d(i2);
        this.i.e(e2);
    }

    private void f() {
        LogUtil.a("PluginDevice_AddDeviceChildActivity", "enter closeProgress");
        CustomProgressDialog customProgressDialog = this.f;
        if (customProgressDialog == null || !customProgressDialog.isShowing() || isFinishing()) {
            return;
        }
        this.f.cancel();
        LogUtil.a("PluginDevice_AddDeviceChildActivity", "enter closeProgress cancel");
    }

    private void i() {
        synchronized (e) {
            this.am = 0;
        }
        f();
        al();
        this.n.setText(this.d.getString(R.string.IDS_device_plugin_download_error));
        this.l.setVisibility(0);
        this.ag.removeMessages(70);
        for (cvk cvkVar : m()) {
            if (!this.r.contains(cvkVar)) {
                LogUtil.a("PluginDevice_AddDeviceChildActivity", "Delete the description file after the resource is not downloaded,uuid：" + cvkVar.e());
                EzPluginManager.a().a(cvkVar.e());
                b(cvkVar);
            }
        }
    }

    private void b(cvk cvkVar) {
        for (int i = 0; i < this.t.size(); i++) {
            if (this.t.get(i).e() == jfu.b(cvkVar.e())) {
                LogUtil.a("PluginDevice_AddDeviceChildActivity", "updateUIForWear already have this device，need remove");
                this.t.remove(i);
                x();
            }
        }
        for (int i2 = 0; i2 < this.t.size(); i2++) {
            if (this.t.get(i2).e() == jfu.b(cvkVar.e())) {
                LogUtil.a("PluginDevice_AddDeviceChildActivity", "updateUIForWear already have this device，need remove");
                this.t.remove(i2);
                x();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void y() {
        LogUtil.a("PluginDevice_AddDeviceChildActivity", "enter retryDownload");
        RelativeLayout relativeLayout = this.l;
        if (relativeLayout != null) {
            relativeLayout.setVisibility(8);
        }
        if (this.ag == null) {
            LogUtil.a("PluginDevice_AddDeviceChildActivity", "enter retryDownload mMyHandler is null");
            return;
        }
        ah();
        int i = this.ao;
        if (i == 1) {
            a("SMART_WATCH");
            return;
        }
        if (i == 2) {
            a("SMART_BAND");
        } else if (i == 4) {
            a("SMART_HEADPHONES");
        } else {
            LogUtil.a("PluginDevice_AddDeviceChildActivity", "retryDownload unknown style");
        }
    }

    private void ah() {
        CustomProgressDialog customProgressDialog = this.f;
        if (customProgressDialog != null && customProgressDialog.isShowing()) {
            LogUtil.a("PluginDevice_AddDeviceChildActivity", "The progress bar already exist");
            return;
        }
        if (this.ae == null) {
            new CommonDialog21(this, R.style.app_update_dialogActivity);
            this.ae = CommonDialog21.a(this);
        }
        this.ae.e(this.d.getString(R.string.IDS_device_plugin_download_loading));
        this.ae.a();
        if (!Utils.o()) {
            this.ag.sendEmptyMessageDelayed(70, 5000L);
        } else {
            this.ag.sendEmptyMessageDelayed(70, PreConnectManager.CONNECT_INTERNAL);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void al() {
        CommonDialog21 commonDialog21 = this.ae;
        if (commonDialog21 == null || !commonDialog21.isShowing() || isFinishing()) {
            return;
        }
        this.ae.dismiss();
        this.ae = null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void k() {
        LogUtil.a("PluginDevice_AddDeviceChildActivity", "enter handleCancel");
        h();
        i();
    }

    private void h() {
        Iterator<msq> it = msn.c().b().iterator();
        while (it.hasNext()) {
            msq next = it.next();
            if (next != null && b(next.c())) {
                EzPluginManager.a().a(next);
            }
        }
    }

    private boolean b(String str) {
        if (TextUtils.equals("plugin_index", str)) {
            return true;
        }
        List<cvk> indexList = this.f8995a.getIndexList();
        if (koq.b(indexList)) {
            return false;
        }
        for (cvk cvkVar : indexList) {
            if (cvkVar != null && TextUtils.equals(cvkVar.e(), str)) {
                return true;
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void ak() {
        int l = l();
        LogUtil.a("PluginDevice_AddDeviceChildActivity", "enter updatePluginForWear size：", Integer.valueOf(l));
        this.r.clear();
        if (l > 0) {
            b(0);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(final int i) {
        if (c(i)) {
            return;
        }
        final String d = d(i);
        LogUtil.a("PluginDevice_AddDeviceChildActivity", "enter downloadOnePlugin fetchFileId:", d);
        EzPluginManager.a().d(d, new PullListener() { // from class: com.huawei.ui.device.activity.adddevice.AddDeviceChildActivity.6
            @Override // com.huawei.pluginmgr.filedownload.PullListener
            public void onPullingChange(msq msqVar, mso msoVar) {
                if (msoVar != null && AddDeviceChildActivity.this.ag != null) {
                    AddDeviceChildActivity.this.d(AnalyticsValue.DEVICE_RESOURCE_FILE_2180012.value(), msoVar.i(), d);
                    if (msoVar.i() == 1) {
                        LogUtil.a("PluginDevice_AddDeviceChildActivity", "The resource file (single) is successfully downloaded");
                        if (AddDeviceChildActivity.this.c(i)) {
                            return;
                        }
                        AddDeviceChildActivity.this.a(i);
                        synchronized (AddDeviceChildActivity.e) {
                            AddDeviceChildActivity.this.am += msoVar.j();
                        }
                        int l = AddDeviceChildActivity.this.l();
                        int i2 = i + 1;
                        if (i2 < l) {
                            AddDeviceChildActivity.this.b(i2);
                            return;
                        } else {
                            LogUtil.a("PluginDevice_AddDeviceChildActivity", "Succeeded in downloading all the resource files");
                            AddDeviceChildActivity.this.j(40);
                            return;
                        }
                    }
                    if (msoVar.i() != 0) {
                        AddDeviceChildActivity.this.j(50);
                        LogUtil.h("PluginDevice_AddDeviceChildActivity", "downloadOnePlugin failed");
                        ReleaseLogUtil.d("HWWEAR_PluginDevice_AddDeviceChildActivity", "downloadOnePlugin failed");
                        return;
                    } else {
                        Message obtain = Message.obtain();
                        obtain.what = 30;
                        obtain.arg1 = msoVar.b();
                        AddDeviceChildActivity.this.ag.sendMessage(obtain);
                        return;
                    }
                }
                LogUtil.h("PluginDevice_AddDeviceChildActivity", "onPullingChange result or mMyHandler is null");
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(int i) {
        synchronized (c) {
            cvk cvkVar = this.aq.get(i);
            String str = this.aq.get(i).e() + "_version";
            String a2 = this.aq.get(i).a();
            StorageParams storageParams = new StorageParams();
            storageParams.d(0);
            SharedPreferenceManager.e(this.d, String.valueOf(1003), str, a2, storageParams);
            LogUtil.a("PluginDevice_AddDeviceChildActivity", "setKey is ", str, ",setVersion is ", a2);
            i(cvkVar);
            this.r.add(cvkVar);
        }
    }

    private String d(int i) {
        String e2;
        synchronized (c) {
            e2 = this.aq.get(i).e();
        }
        return e2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void j(int i) {
        Message obtain = Message.obtain();
        obtain.what = i;
        this.ag.sendMessage(obtain);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean c(int i) {
        int l = l();
        if (i >= 0 && i <= l - 1) {
            return false;
        }
        LogUtil.a("PluginDevice_AddDeviceChildActivity", "downloadOnePlugin index out of bounder.");
        Message obtain = Message.obtain();
        obtain.what = 50;
        this.ag.sendMessage(obtain);
        return true;
    }

    private void b(String str, boolean z) {
        List<cvk> indexList = this.f8995a.getIndexList();
        if (indexList == null || indexList.size() <= 0) {
            if (z) {
                LogUtil.a("PluginDevice_AddDeviceChildActivity", "indexInfoCaches.size equals 0");
                Message obtain = Message.obtain();
                obtain.what = 60;
                this.ag.sendMessage(obtain);
                return;
            }
            return;
        }
        LogUtil.a("PluginDevice_AddDeviceChildActivity", "indexInfoCaches.size greater 0");
        boolean z2 = false;
        for (cvk cvkVar : indexList) {
            if (cvkVar.g() != null && TextUtils.equals(cvkVar.g(), str)) {
                LogUtil.a("PluginDevice_AddDeviceChildActivity", "has band plugin info from cache, uuid :", cvkVar.e());
                if (EzPluginManager.a().g(cvkVar.e())) {
                    c(this.f8995a.getPluginInfoByUuid(cvkVar.e()), cwc.e(cvkVar.i()));
                    z2 = true;
                } else {
                    LogUtil.a("PluginDevice_AddDeviceChildActivity", "No resource file exists under the uuid");
                }
            }
        }
        if (z2 || !z) {
            return;
        }
        LogUtil.a("PluginDevice_AddDeviceChildActivity", "have no done file ");
        Message obtain2 = Message.obtain();
        obtain2.what = 60;
        this.ag.sendMessage(obtain2);
    }

    private void c(cvc cvcVar, boolean z) {
        if (z) {
            if (cvcVar == null) {
                Message obtain = Message.obtain();
                obtain.what = 50;
                this.ag.sendMessage(obtain);
            } else {
                if (cvcVar.f() != null && cvcVar.f().bt()) {
                    LogUtil.a("PluginDevice_AddDeviceChildActivity", "deviceType is ", Integer.valueOf(cvcVar.f().af()));
                    v();
                    d(cvcVar);
                    runOnUiThread(new Runnable() { // from class: com.huawei.ui.device.activity.adddevice.AddDeviceChildActivity.12
                        @Override // java.lang.Runnable
                        public void run() {
                            AddDeviceChildActivity.this.x();
                        }
                    });
                    return;
                }
                Message obtain2 = Message.obtain();
                obtain2.what = 10;
                obtain2.obj = cvcVar;
                this.ag.sendMessage(obtain2);
            }
        }
    }

    private void d(cvc cvcVar) {
        cuw d = oad.d();
        obu obuVar = new obu(d.m(), d.f(), d.h(), true, b(cvcVar));
        obuVar.a(1);
        obuVar.cUT_(new c(obuVar));
        this.t.add(0, obuVar);
        this.ac = true;
    }

    private void v() {
        for (int i = 0; i < this.t.size(); i++) {
            if (this.t.get(i).d().equals(BaseApplication.getContext().getString(R.string._2130849042_res_0x7f022d12))) {
                this.t.remove(i);
                return;
            }
        }
    }

    private void i(cvk cvkVar) {
        LogUtil.a("PluginDevice_AddDeviceChildActivity", "Name of the resource file downloaded successfully:", cvkVar.e());
        c(this.f8995a.getPluginInfoByUuid(cvkVar.e()), cwc.e(cvkVar.i()));
    }

    private void e(cvc cvcVar) {
        ArrayList<obu> arrayList = this.t;
        if (arrayList != null && !arrayList.isEmpty()) {
            for (int i = 0; i < this.t.size(); i++) {
                obu obuVar = this.t.get(i);
                if (obuVar.cUR_().getInt(OpAnalyticsConstants.OPERATION_ID) == jfu.b(cvcVar.e()) && jfu.b(cvcVar.e()) != -1) {
                    LogUtil.a("PluginDevice_AddDeviceChildActivity", "updateUIForWear already have this device");
                    this.t.remove(obuVar);
                    c(cvcVar);
                    x();
                    return;
                }
            }
        }
        ArrayList<obu> arrayList2 = this.q;
        if (arrayList2 != null && !arrayList2.isEmpty()) {
            for (int i2 = 0; i2 < this.q.size(); i2++) {
                obu obuVar2 = this.q.get(i2);
                if (obuVar2.cUR_().getInt(OpAnalyticsConstants.OPERATION_ID) == jfu.b(cvcVar.e()) && jfu.b(cvcVar.e()) != -1) {
                    LogUtil.a("PluginDevice_AddDeviceChildActivity", "updateUIForWear already have this device");
                    this.q.remove(obuVar2);
                    c(cvcVar);
                    x();
                    return;
                }
            }
        }
        if (cpa.z(cvcVar.e())) {
            e();
        }
        LogUtil.a("PluginDevice_AddDeviceChildActivity", "enter updateUIForWear");
        c(cvcVar);
        x();
    }

    private void c(cvc cvcVar) {
        int b2 = jfu.b(cvcVar.e());
        if (this.ak == 1) {
            boolean z = cux.e(b2) || cus.e().d(b2);
            LogUtil.a("PluginDevice_AddDeviceChildActivity", "isSupportHeartRate ", Boolean.valueOf(z), " ", Integer.valueOf(b2));
            if (!z) {
                return;
            }
        }
        if (cvcVar.f() == null) {
            return;
        }
        cvc cvcVar2 = new cvc();
        cvj cvjVar = new cvj();
        cvjVar.b(cvcVar.f().d());
        cvcVar2.c(cvjVar);
        if (ntt.d(cvcVar2, b2, CommonUtil.d(this.d))) {
            LogUtil.a("PluginDevice_AddDeviceChildActivity", "ezPluginInfo", cvcVar.toString());
            if ("".equals(cvcVar.f().ae())) {
                return;
            }
            b(b2, -1);
            obu obuVar = new obu(-1, cvcVar.f().ae(), cvcVar.f().i(), true, b(cvcVar));
            obuVar.a(1);
            obuVar.cUT_(new c(obuVar));
            if ("1".equals(cvcVar.f().f())) {
                if (this.ac) {
                    this.t.add(1, obuVar);
                    return;
                } else {
                    this.t.add(0, obuVar);
                    return;
                }
            }
            this.q.add(0, obuVar);
            return;
        }
        a(cvcVar, b2);
    }

    private void a(cvc cvcVar, int i) {
        cuw c2 = jfu.c(i);
        obu obuVar = new obu(i, c2.f(), c2.h(), true, b(cvcVar));
        obuVar.a(1);
        obuVar.cUT_(new c(obuVar));
        if (c2.p() == 1) {
            if (this.ac) {
                this.t.add(1, obuVar);
                return;
            } else {
                this.t.add(0, obuVar);
                return;
            }
        }
        this.q.add(0, obuVar);
    }

    private String b(cvc cvcVar) {
        return msj.a().d(cvcVar.e()) + File.separator + cvcVar.e() + File.separator + "img" + File.separator + cvcVar.f().ay() + ".png";
    }

    private void b(int i, int i2) {
        if (jfu.c(i) != null) {
            jfu.c(i).b(i2);
        }
    }

    private void t() {
        LogUtil.a("PluginDevice_AddDeviceChildActivity", "initViewForEar");
        this.g.setTitleText(this.d.getString(R.string.IDS_add_device_smart_headphones));
        if (!this.t.isEmpty()) {
            LogUtil.a("PluginDevice_AddDeviceChildActivity", "initViewForEar() mHwLists.clear()");
            this.t.clear();
        }
        if (!this.q.isEmpty()) {
            LogUtil.a("PluginDevice_AddDeviceChildActivity", "initViewForEar() mHonorLists.clear()");
            this.q.clear();
        }
        for (cuw cuwVar : oad.a()) {
            LogUtil.a("PluginDevice_AddDeviceChildActivity", "initViewForEar() type:", Integer.valueOf(cuwVar.m()));
            d(cuwVar.m(), cuwVar.f(), cuwVar.h(), cuwVar.v(), cuwVar.p());
        }
        e();
        ad();
        b("SMART_HEADPHONES", true);
        a("SMART_HEADPHONES");
    }

    private void e() {
        dcz d = ResourceManager.e().d("6d5416d9-2167-41df-ab10-c492e152b44f");
        if (d != null) {
            String t = d.t();
            Iterator<obu> it = this.ai.iterator();
            boolean z = false;
            while (it.hasNext()) {
                if (it.next().cUR_().getInt(OpAnalyticsConstants.OPERATION_ID) == 75) {
                    z = true;
                }
            }
            if (d.e().size() > 0) {
                File file = new File(dcq.b().a(d.t(), d.e().get(0).e()));
                if (!z && file.exists()) {
                    cfl.a().e(d.s(), ResourceManager.e().c(t) + File.separator + d.k());
                    cuw a2 = jfu.a();
                    d(a2.m(), a2.f(), a2.h(), a2.v(), a2.p());
                    return;
                }
                LogUtil.b("PluginDevice_AddDeviceChildActivity", "getProductInfo file is not exists ID:", t);
            }
        }
    }

    private void d(int i, String str, String str2, int i2, int i3) {
        obu d = oad.d(i, str, str2, i2);
        d.a(1);
        d.cUT_(new c(d));
        if (i3 == 1) {
            this.t.add(d);
        } else {
            this.q.add(d);
        }
    }

    private void c(int i, String str, String str2, int i2) {
        obu d = oad.d(i, str, str2, i2);
        d.cUT_(new b(d));
        this.ai.add(d);
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        LogUtil.a("PluginDevice_AddDeviceChildActivity", "onDestroy()");
        this.s = false;
        CustomProgressDialog.Builder builder = this.i;
        if (builder != null) {
            builder.cyH_(null);
        }
        h();
        ResourceManager.e().f();
        al();
        f();
        EventBus.a(this.p, "device_download_dialog", "single_device_download_dialog");
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        Intent intent = getIntent();
        if (intent != null && "com.huawei.ui.device.activity.adddevice.AddDeviceChildActivity".equals(intent.getAction())) {
            u();
        } else {
            super.onBackPressed();
            LogUtil.a("PluginDevice_AddDeviceChildActivity", " onBackPressed() ");
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onStart() {
        LogUtil.a("PluginDevice_AddDeviceChildActivity", "onStart()");
        super.onStart();
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        if (nsn.s() || nsn.r()) {
            LogUtil.a("PluginDevice_AddDeviceChildActivity", "Utils.isThreeFoldFonts:", Boolean.valueOf(nsn.s()), "; isLargeFontScaleMode:", Boolean.valueOf(nsn.r()));
        }
    }

    class c implements View.OnClickListener {
        obu b;

        c(obu obuVar) {
            this.b = obuVar;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            LogUtil.a("PluginDevice_AddDeviceChildActivity", "MyOnClickListener(): item.getID() ", Integer.valueOf(this.b.cUR_().getInt(OpAnalyticsConstants.OPERATION_ID)), "device name : ", this.b.cUR_().getString("mContent"));
            if (AddDeviceChildActivity.this.q()) {
                LogUtil.h("PluginDevice_AddDeviceChildActivity", "onClick() isFastClick");
                ViewClickInstrumentation.clickOnView(view);
                return;
            }
            HashMap hashMap = new HashMap(16);
            hashMap.put("click", "1");
            ixx.d().d(cpp.a(), AnalyticsValue.HEALTH_PLUGIN_DEVICE_SELECT_DEVICE_2060002.value(), hashMap, 0);
            if (this.b.e() == -1) {
                ntt.cNK_(AddDeviceChildActivity.this.d, AddDeviceChildActivity.this);
                ViewClickInstrumentation.clickOnView(view);
                return;
            }
            if (AddDeviceChildActivity.this.ac && this.b.d().equals(BaseApplication.getContext().getString(R.string._2130849042_res_0x7f022d12))) {
                AddDeviceChildActivity.this.af();
                ViewClickInstrumentation.clickOnView(view);
                return;
            }
            if (this.b.cUR_().getInt(OpAnalyticsConstants.OPERATION_ID) != 75) {
                AddDeviceChildActivity.this.a(this.b);
            } else {
                LogUtil.a("PluginDevice_AddDeviceChildActivity", "Honor Sunny Headset");
                DeviceInfo d = jpt.d("PluginDevice_AddDeviceChildActivity");
                if (d == null || !HwVersionManager.c(BaseApplication.getContext()).o(d.getDeviceIdentify())) {
                    AddDeviceChildActivity.this.ae();
                } else {
                    LogUtil.a("PluginDevice_AddDeviceChildActivity", "MyOnClickListener, wear device OTA is in progress");
                    AddDeviceChildActivity.this.ai();
                }
            }
            ViewClickInstrumentation.clickOnView(view);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void af() {
        startActivityForResult(new Intent(this, (Class<?>) AddDeviceChildSecondActivity.class), 1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void ae() {
        Intent intent = new Intent();
        Bundle bundle = new Bundle();
        intent.setAction("SWITCH_PLUGINDEVICE");
        bundle.putString("arg1", "DeviceIntroduction");
        bundle.putString("arg2", "6d5416d9-2167-41df-ab10-c492e152b44f");
        intent.setPackage(this.d.getPackageName());
        intent.setClassName(this.d.getPackageName(), "com.huawei.health.device.ui.DeviceMainActivity");
        intent.putExtras(bundle);
        startActivityForResult(intent, 102);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(obu obuVar) {
        Intent intent = new Intent();
        intent.setPackage(this.d.getPackageName());
        intent.setClassName(this.d, "com.huawei.ui.device.activity.adddevice.AddDeviceIntroActivity");
        intent.putExtra(DeviceCategoryFragment.DEVICE_TYPE, obuVar.cUR_().getInt(OpAnalyticsConstants.OPERATION_ID));
        if (obuVar.d() != null && obuVar.d().equals(BaseApplication.getContext().getString(R.string._2130849042_res_0x7f022d12))) {
            intent.putExtra("isPorc", true);
            intent.putExtra("IS_PROC", true);
            this.aa = true;
        } else {
            this.aa = false;
            if (obuVar.d() != null && obuVar.d().equals(BaseApplication.getContext().getString(R.string._2130849807_res_0x7f02300f))) {
                intent.putExtra("isR1pro", true);
                LogUtil.a("PluginDevice_AddDeviceChildActivity", "is R1 pro");
            }
        }
        intent.putExtra("dname", obuVar.d());
        intent.putExtra("viewStyle", this.ao);
        this.al = obuVar.h();
        startActivityForResult(intent, 99);
    }

    private void e(int i) {
        b(i, this.al);
    }

    private void b(int i, String str) {
        LogUtil.a("PluginDevice_AddDeviceChildActivity", "enter hasSelectDevice():The device to be connected is:", Integer.valueOf(i), " mIsClicked:", Boolean.valueOf(this.v));
        if (this.v) {
            return;
        }
        this.v = true;
        this.aj = i;
        cuw a2 = oad.a(i);
        if (i == -3) {
            c((String) null);
        } else if (i == 3 || i == 10) {
            j();
        } else {
            LogUtil.a("PluginDevice_AddDeviceChildActivity", "start enterDevicePairGuide");
            d(a2.m(), a2.f(), str);
        }
    }

    private void c(String str) {
        Intent intent = new Intent();
        intent.setClassName(this.d, "com.huawei.ui.homewear21.home.WearHomeActivity");
        intent.putExtra("isFromWear", this.w || this.u);
        intent.putExtra("device_id", str);
        startActivity(intent);
        finish();
    }

    private void d(int i, String str, String str2) {
        Intent intent = new Intent(this.d, (Class<?>) DevicePairGuideActivity.class);
        intent.putExtra("pairGuideProductType", i);
        intent.putExtra("pairGuideProductName", str);
        intent.putExtra("pairGuideSelectName", str2);
        intent.putExtra("pairGuideFromScanList", false);
        intent.putExtra("isHeartRateDevice", this.ak);
        intent.putExtra("pairGuideDeviceMode", ErrorCode.HWID_IS_STOPED);
        startActivityForResult(intent, 1);
    }

    private void d(String str) {
        Intent intent = new Intent(this.d, (Class<?>) DevicePairGuideActivity.class);
        intent.putExtra("pairGuideProductType", 32);
        intent.putExtra("pairGuideProductName", oae.c(BaseApplication.getContext()).b(32));
        intent.putExtra("pairGuideFromScanList", true);
        intent.putExtra("pairGuideSelectName", "CSN-Temp");
        intent.putExtra("pairGuideSelectAddress", str);
        intent.putExtra("pairGuideDeviceMode", ErrorCode.HWID_IS_STOPED);
        LogUtil.a("PluginDevice_AddDeviceChildActivity", "enterDevicePairGuideActivity start Activity.");
        this.aj = 32;
        startActivityForResult(intent, 1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void j() {
        Intent intent = new Intent(this.d, (Class<?>) DevicePairGuideActivity.class);
        ntt.cNM_(intent, this.aj, this.j);
        if (this.aj == 10) {
            intent.putExtra("pairGuideProductType", 10);
            LogUtil.a("PluginDevice_AddDeviceChildActivity", "start mIsProc:", Boolean.valueOf(this.aa));
            intent.putExtra("pairGuideProductName", this.d.getString(R.string.IDS_app_display_name_leo));
            intent.putExtra("IS_PROC", this.aa);
        } else {
            intent.putExtra("pairGuideProductType", 3);
            intent.putExtra("pairGuideProductName", this.d.getString(R.string.IDS_app_display_name_w1));
        }
        intent.putExtra("pairGuideFromScanList", false);
        intent.putExtra("pairGuideDeviceMode", ErrorCode.HWID_IS_STOPED);
        startActivityForResult(intent, 1);
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onActivityResult(int i, int i2, Intent intent) {
        LogUtil.a("PluginDevice_AddDeviceChildActivity", "Enter onActivityResult():mPersonalBasicInfoSettingFlag ", this.an, " resultCode:", Integer.valueOf(i2), " requestCode:", Integer.valueOf(i));
        if (this.w) {
            if (i2 == 0 && i == 1) {
                setResult(2);
                ntt.c();
                finish();
            }
            if (i == 1) {
                cNt_(i2, intent);
            }
        } else if (i == 1) {
            cNt_(i2, intent);
        } else if (i == 99) {
            int intExtra = (i2 != 101 || intent == null) ? -10 : intent.getIntExtra(DeviceCategoryFragment.DEVICE_TYPE, -10);
            if (intExtra != -10) {
                e(intExtra);
            }
        } else if (i2 == -1 && i == 102) {
            setResult(2);
            ntt.c();
            finish();
        } else {
            LogUtil.a("PluginDevice_AddDeviceChildActivity", "unknown resultCode");
        }
        this.v = false;
        super.onActivityResult(i, i2, intent);
    }

    private void cNt_(int i, Intent intent) {
        if (i == 2) {
            setResult(2);
            ntt.c();
            nue.cNU_(intent, this, nue.e(i, this.w || this.u, this.aj, true));
        }
    }

    private void p() {
        LogUtil.a("PluginDevice_AddDeviceChildActivity", "initSelectDeviceListAndroidWear");
        if (!this.ai.isEmpty()) {
            LogUtil.a("PluginDevice_AddDeviceChildActivity", "initSelectDeviceListAndroidWear() mList.clear()");
            this.ai.clear();
        }
        c(10, this.d.getString(R.string._2130849042_res_0x7f022d12), this.d.getString(R.string._2130842222_res_0x7f02126e), R.mipmap._2131821260_res_0x7f1102cc);
        c(10, this.d.getString(R.string.IDS_app_display_name_leo), this.d.getString(R.string._2130842210_res_0x7f021262), R.mipmap._2131821253_res_0x7f1102c5);
        SelectDeviceListAdapter selectDeviceListAdapter = new SelectDeviceListAdapter(this.ai);
        this.af = selectDeviceListAdapter;
        this.ah.setAdapter((ListAdapter) selectDeviceListAdapter);
    }

    class b implements View.OnClickListener {
        obu b;

        b(obu obuVar) {
            this.b = obuVar;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            LogUtil.a("PluginDevice_AddDeviceChildActivity", "MyOnClickListenerAndroidWear(): item.getId() ", Integer.valueOf(this.b.e()));
            AddDeviceChildActivity.this.aj = this.b.e();
            if (HwVersionManager.c(BaseApplication.getContext()).k("")) {
                AddDeviceChildActivity.this.ai();
                ViewClickInstrumentation.clickOnView(view);
            } else {
                AddDeviceChildActivity.this.j();
                ViewClickInstrumentation.clickOnView(view);
            }
        }
    }

    static class a extends Handler {

        /* renamed from: a, reason: collision with root package name */
        WeakReference<AddDeviceChildActivity> f9000a;

        a(AddDeviceChildActivity addDeviceChildActivity) {
            this.f9000a = new WeakReference<>(addDeviceChildActivity);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            AddDeviceChildActivity addDeviceChildActivity = this.f9000a.get();
            if (addDeviceChildActivity == null) {
                LogUtil.a("PluginDevice_AddDeviceChildActivity", "handleMessage mActivity is null");
                return;
            }
            if (message != null) {
                super.handleMessage(message);
                if (message.what < 55) {
                    addDeviceChildActivity.cNr_(message);
                } else {
                    addDeviceChildActivity.cNq_(message);
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void cNr_(Message message) {
        int i = message.what;
        if (i == 10) {
            if (message.obj instanceof cvc) {
                cvc cvcVar = (cvc) message.obj;
                LogUtil.a("PluginDevice_AddDeviceChildActivity", "wear info download success uuid:", cvcVar.e());
                e(cvcVar);
                return;
            }
            return;
        }
        if (i == 20) {
            ag();
            return;
        }
        if (i == 30) {
            synchronized (b) {
                g(message.arg1);
            }
            return;
        }
        if (i == 40) {
            LogUtil.a("PluginDevice_AddDeviceChildActivity", "WEAR_INFO_DOWNLOAD_FINISH ");
            f();
            synchronized (e) {
                this.am = 0;
            }
            return;
        }
        if (i == 50) {
            LogUtil.a("PluginDevice_AddDeviceChildActivity", "WEAR_INFO_DOWNLOAD_ERROR ");
            k();
            i();
            if (this.s) {
                z();
                return;
            }
            return;
        }
        LogUtil.a("PluginDevice_AddDeviceChildActivity", "handleWearInfo default");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void cNq_(Message message) {
        int i = message.what;
        if (i == 60) {
            LogUtil.a("PluginDevice_AddDeviceChildActivity", "WEAR_INFO_HAVE_NO_CACHE ");
            ah();
            return;
        }
        if (i == 70) {
            LogUtil.a("PluginDevice_AddDeviceChildActivity", "WEAR_INFO_LOADING_TIME_OUT ");
            al();
            k();
            if (this.s) {
                z();
                return;
            }
            return;
        }
        if (i == 80) {
            LogUtil.a("PluginDevice_AddDeviceChildActivity", "WEAR_INFO_NOT_PLACED ");
            k();
            if (this.s) {
                z();
                return;
            }
            return;
        }
        if (i == 90) {
            if (message.obj != null && (message.obj instanceof String)) {
                String str = (String) message.obj;
                LogUtil.a("PluginDevice_AddDeviceChildActivity", "deviceType : ", str);
                g(str);
            } else {
                LogUtil.a("PluginDevice_AddDeviceChildActivity", "wrong msg.obj");
            }
            LogUtil.a("PluginDevice_AddDeviceChildActivity", "WEAR_START_INDEX success.");
            return;
        }
        LogUtil.a("PluginDevice_AddDeviceChildActivity", "handleMoreWearInfo default");
    }

    private void z() {
        CustomTextAlertDialog customTextAlertDialog = this.o;
        if (customTextAlertDialog == null || !customTextAlertDialog.isShowing()) {
            CustomTextAlertDialog.Builder builder = new CustomTextAlertDialog.Builder(this);
            builder.b(R.string.IDS_service_area_notice_title).d(R.string.IDS_device_device_list_update_failed).cyU_(R.string._2130841794_res_0x7f0210c2, new View.OnClickListener() { // from class: com.huawei.ui.device.activity.adddevice.AddDeviceChildActivity.13
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    if (AddDeviceChildActivity.this.o != null) {
                        AddDeviceChildActivity.this.o.dismiss();
                        AddDeviceChildActivity.this.o = null;
                    }
                    ViewClickInstrumentation.clickOnView(view);
                }
            });
            CustomTextAlertDialog a2 = builder.a();
            this.o = a2;
            a2.setCancelable(false);
            this.o.show();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void j(final String str) {
        CustomTextAlertDialog customTextAlertDialog = this.k;
        if (customTextAlertDialog == null || !customTextAlertDialog.isShowing()) {
            CustomTextAlertDialog.Builder builder = new CustomTextAlertDialog.Builder(this);
            builder.b(R.string.IDS_service_area_notice_title).d(R.string.IDS_device_download_resoure_tip_content_message_new).cyU_(R.string._2130842122_res_0x7f02120a, new View.OnClickListener() { // from class: com.huawei.ui.device.activity.adddevice.AddDeviceChildActivity.11
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    DeviceCloudSharePreferencesManager deviceCloudSharePreferencesManager = new DeviceCloudSharePreferencesManager(AddDeviceChildActivity.this);
                    deviceCloudSharePreferencesManager.b("is_down_device", true);
                    deviceCloudSharePreferencesManager.a("is_download_device_time", new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date()));
                    if ("device_download_dialog".equals(str)) {
                        ResourceManager.e().i();
                    } else if ("single_device_download_dialog".equals(str)) {
                        ResourceManager.e().c((ArrayList<String>) null);
                    } else {
                        LogUtil.a("PluginDevice_AddDeviceChildActivity", "unknown action");
                    }
                    AddDeviceChildActivity.this.k.dismiss();
                    AddDeviceChildActivity.this.k = null;
                    ViewClickInstrumentation.clickOnView(view);
                }
            }).cyR_(R.string._2130841130_res_0x7f020e2a, new View.OnClickListener() { // from class: com.huawei.ui.device.activity.adddevice.AddDeviceChildActivity.14
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    DeviceCloudSharePreferencesManager deviceCloudSharePreferencesManager = new DeviceCloudSharePreferencesManager(AddDeviceChildActivity.this);
                    deviceCloudSharePreferencesManager.b("is_down_device", false);
                    deviceCloudSharePreferencesManager.a("is_download_device_time", new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date()));
                    AddDeviceChildActivity.this.k.dismiss();
                    AddDeviceChildActivity.this.k = null;
                    AddDeviceChildActivity.this.finish();
                    ViewClickInstrumentation.clickOnView(view);
                }
            });
            CustomTextAlertDialog a2 = builder.a();
            this.k = a2;
            a2.setCancelable(false);
            this.k.show();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void ai() {
        NoTitleCustomAlertDialog e2 = new NoTitleCustomAlertDialog.Builder(this).e(this.d.getResources().getString(R.string.IDS_device_ota_later_note)).czC_(R.string._2130841554_res_0x7f020fd2, new View.OnClickListener() { // from class: com.huawei.ui.device.activity.adddevice.AddDeviceChildActivity.16
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LogUtil.a("PluginDevice_AddDeviceChildActivity", "showTipDialog，click known button");
                ViewClickInstrumentation.clickOnView(view);
            }
        }).e();
        e2.setCancelable(false);
        e2.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean q() {
        long elapsedRealtime = SystemClock.elapsedRealtime();
        if (elapsedRealtime - this.z < 1000) {
            return true;
        }
        this.z = elapsedRealtime;
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(String str, int i, String str2) {
        HashMap hashMap = new HashMap(16);
        hashMap.put("deviceType", this.h);
        String[] split = str2.split(Constants.LINK);
        if (split.length > 0) {
            hashMap.put(ContentResource.FILE_NAME, split[split.length - 1]);
        } else {
            hashMap.put(ContentResource.FILE_NAME, str2);
        }
        hashMap.put("errorCode", Integer.valueOf(i));
        ixx.d().d(this.d, str, hashMap, 0);
        LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<>(16);
        for (Map.Entry entry : hashMap.entrySet()) {
            linkedHashMap.put((String) entry.getKey(), entry.getValue().toString());
        }
        OpAnalyticsUtil.getInstance().setEvent2nd(str, linkedHashMap);
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
