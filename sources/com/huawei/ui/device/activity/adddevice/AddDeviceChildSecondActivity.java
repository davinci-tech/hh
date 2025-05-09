package com.huawei.ui.device.activity.adddevice;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.health.R;
import com.huawei.health.devicemgr.api.mainprocess.DownloadManagerApi;
import com.huawei.health.ecologydevice.ui.measure.fragment.device.DeviceCategoryFragment;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.OpAnalyticsConstants;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.titlebar.CustomTitleBar;
import com.huawei.ui.device.activity.pairing.DevicePairGuideActivity;
import com.huawei.ui.device.views.adddevice.SelectDeviceListAdapter;
import com.huawei.up.utils.ErrorCode;
import defpackage.cpp;
import defpackage.cuw;
import defpackage.cvc;
import defpackage.cvj;
import defpackage.cvk;
import defpackage.ixx;
import defpackage.jfu;
import defpackage.jph;
import defpackage.msj;
import defpackage.nsy;
import defpackage.ntt;
import defpackage.oad;
import defpackage.oae;
import defpackage.obu;
import health.compact.a.CommonUtil;
import health.compact.a.Services;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/* loaded from: classes6.dex */
public class AddDeviceChildSecondActivity extends BaseActivity {
    private Context b;
    private CustomTitleBar d;
    private oae e;
    private int g;
    private String h;
    private ListView i;
    private ArrayList<obu> j = new ArrayList<>(16);
    private SelectDeviceListAdapter f = null;

    /* renamed from: a, reason: collision with root package name */
    private boolean f9002a = false;
    private DownloadManagerApi c = (DownloadManagerApi) Services.c("DownloadDeviceResource", DownloadManagerApi.class);

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        cancelAdaptRingRegion();
        Context context = BaseApplication.getContext();
        this.b = context;
        if (context != null) {
            this.e = oae.c(BaseApplication.getContext());
        }
        LogUtil.a("AddDeviceChildSecondActivity", "onCreate()");
        d();
    }

    private void d() {
        setContentView(R.layout.activity_add_device_child);
        CustomTitleBar customTitleBar = (CustomTitleBar) nsy.cMc_(this, R.id.select_device_detail_title_bar);
        this.d = customTitleBar;
        customTitleBar.setVisibility(0);
        this.d.setLeftButtonOnClickListener(new View.OnClickListener() { // from class: com.huawei.ui.device.activity.adddevice.AddDeviceChildSecondActivity.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                AddDeviceChildSecondActivity.this.finish();
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        this.i = (ListView) nsy.cMc_(this, R.id.list_setup_device);
        e();
        jph.bIM_(this, -1);
    }

    private void e() {
        LogUtil.a("AddDeviceChildSecondActivity", "initListView");
        this.d.setTitleText(this.b.getString(R.string._2130849042_res_0x7f022d12));
        for (cuw cuwVar : oad.e()) {
            LogUtil.a("AddDeviceChildSecondActivity", "initViewForWatch() type: ", Integer.valueOf(cuwVar.m()));
            b(cuwVar.m(), cuwVar.f(), cuwVar.h(), cuwVar.v());
        }
        b();
        d("SMART_WATCH");
    }

    private void d(String str) {
        List<cvk> indexList = this.c.getIndexList();
        if (indexList == null || indexList.size() <= 0) {
            LogUtil.h("AddDeviceChildSecondActivity", "indexInfoCaches.size equals 0");
            return;
        }
        LogUtil.a("AddDeviceChildSecondActivity", "indexInfoCaches.size greater 0");
        for (cvk cvkVar : indexList) {
            if (cvkVar.g() != null && TextUtils.equals(cvkVar.g(), str)) {
                LogUtil.a("AddDeviceChildSecondActivity", "has band plugin info from cache, uuid: ", cvkVar.e());
                if (this.c.isResourcesAvailable(cvkVar.e())) {
                    d(this.c.getPluginInfoByUuid(cvkVar.e()));
                } else {
                    LogUtil.h("AddDeviceChildSecondActivity", "No resource file exists under the uuid");
                }
            }
        }
    }

    private void d(cvc cvcVar) {
        if (cvcVar == null || cvcVar.f() == null) {
            LogUtil.h("AddDeviceChildSecondActivity", "updateUiForWear is null");
            return;
        }
        LogUtil.a("AddDeviceChildSecondActivity", "deviceType is ", Integer.valueOf(cvcVar.f().af()));
        if (cvcVar.f().bt()) {
            a(cvcVar);
            a();
        }
    }

    private void a(cvc cvcVar) {
        int b = jfu.b(cvcVar.e());
        if (cvcVar.f() == null) {
            return;
        }
        cvc cvcVar2 = new cvc();
        cvj cvjVar = new cvj();
        cvjVar.b(cvcVar.f().d());
        cvcVar2.c(cvjVar);
        if (ntt.d(cvcVar2, b, CommonUtil.d(this.b))) {
            LogUtil.a("AddDeviceChildSecondActivity", "ezPluginInfo: ", cvcVar.toString());
            if ("".equals(cvcVar.f().ae())) {
                return;
            }
            b(b, -1);
            obu obuVar = new obu(-1, cvcVar.f().ae(), cvcVar.f().i(), true, b(cvcVar));
            obuVar.a(1);
            obuVar.cUT_(new d(obuVar));
            this.j.add(0, obuVar);
            return;
        }
        cuw c = jfu.c(b);
        obu obuVar2 = new obu(b, c.f(), c.h(), true, b(cvcVar));
        obuVar2.a(1);
        obuVar2.cUT_(new d(obuVar2));
        this.j.add(0, obuVar2);
    }

    private void b(int i, int i2) {
        if (jfu.c(i) != null) {
            jfu.c(i).b(i2);
        }
    }

    private void a() {
        LogUtil.a("AddDeviceChildSecondActivity", "listNotifyDataSetChanged mlist size is ", Integer.valueOf(this.j.size()));
        this.f.a(this.j);
        this.f.notifyDataSetChanged();
    }

    private void b() {
        LogUtil.a("AddDeviceChildSecondActivity", "setListAdapter mlist size is ", Integer.valueOf(this.j.size()));
        SelectDeviceListAdapter selectDeviceListAdapter = new SelectDeviceListAdapter(this.j);
        this.f = selectDeviceListAdapter;
        this.i.setAdapter((ListAdapter) selectDeviceListAdapter);
    }

    private String b(cvc cvcVar) {
        return msj.a().d(cvcVar.e()) + File.separator + cvcVar.e() + File.separator + "img" + File.separator + cvcVar.f().ay() + ".png";
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        LogUtil.a("AddDeviceChildSecondActivity", "onDestroy()");
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onStart() {
        LogUtil.a("AddDeviceChildSecondActivity", "onStart()");
        super.onStart();
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
    }

    class d implements View.OnClickListener {
        obu b;

        d(obu obuVar) {
            this.b = obuVar;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            LogUtil.a("AddDeviceChildSecondActivity", "MyOnClickListener(): item.getID() ", Integer.valueOf(this.b.cUR_().getInt(OpAnalyticsConstants.OPERATION_ID)), " device name: ", this.b.cUR_().getString("mContent"));
            HashMap hashMap = new HashMap(16);
            hashMap.put("click", "1");
            ixx.d().d(cpp.a(), AnalyticsValue.HEALTH_PLUGIN_DEVICE_SELECT_DEVICE_2060002.value(), hashMap, 0);
            if (this.b.e() == -1) {
                ntt.cNK_(AddDeviceChildSecondActivity.this.b, AddDeviceChildSecondActivity.this);
                ViewClickInstrumentation.clickOnView(view);
            } else {
                this.b.cUR_().getInt(OpAnalyticsConstants.OPERATION_ID);
                AddDeviceChildSecondActivity.this.e(this.b);
                ViewClickInstrumentation.clickOnView(view);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(obu obuVar) {
        Intent intent = new Intent(this, (Class<?>) AddDeviceIntroActivity.class);
        intent.putExtra(DeviceCategoryFragment.DEVICE_TYPE, obuVar.cUR_().getInt(OpAnalyticsConstants.OPERATION_ID));
        if (obuVar.e() == 10) {
            intent.putExtra("isPorc", true);
            intent.putExtra("IS_PROC", true);
        }
        intent.putExtra("dname", obuVar.d());
        this.h = obuVar.h();
        startActivityForResult(intent, 99);
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onActivityResult(int i, int i2, Intent intent) {
        LogUtil.a("AddDeviceChildSecondActivity", "Enter onActivityResult(): ", " resultCode: ", Integer.valueOf(i2), " requestCode: ", Integer.valueOf(i));
        if (i == 1) {
            if (i2 == 0) {
                return;
            }
            setResult(2, intent);
            finish();
        } else if (i == 99) {
            int intExtra = (i2 != 101 || intent == null) ? -10 : intent.getIntExtra(DeviceCategoryFragment.DEVICE_TYPE, -10);
            if (intExtra != -10) {
                a(intExtra);
            }
        } else {
            LogUtil.a("AddDeviceChildSecondActivity", "unknown resultCode");
        }
        this.f9002a = false;
        super.onActivityResult(i, i2, intent);
    }

    private void a(int i) {
        d(i, this.h);
    }

    private void d(int i, String str) {
        LogUtil.a("AddDeviceChildSecondActivity", "enter enterDevicePairGuide, The device to be connected is:", Integer.valueOf(i), " mIsClicked:", Boolean.valueOf(this.f9002a));
        if (this.f9002a) {
            return;
        }
        this.f9002a = true;
        this.g = i;
        cuw a2 = oad.a(i);
        if (i == 10) {
            c();
        } else {
            LogUtil.a("AddDeviceChildSecondActivity", "start enterDevicePairGuide");
            c(a2.m(), a2.f(), str);
        }
    }

    private void c(int i, String str, String str2) {
        Intent intent = new Intent(this.b, (Class<?>) DevicePairGuideActivity.class);
        intent.putExtra("pairGuideProductType", i);
        intent.putExtra("pairGuideProductName", str);
        intent.putExtra("pairGuideSelectName", str2);
        intent.putExtra("pairGuideFromScanList", false);
        intent.putExtra("isHeartRateDevice", false);
        intent.putExtra("pairGuideDeviceMode", ErrorCode.HWID_IS_STOPED);
        startActivityForResult(intent, 1);
    }

    private void c() {
        LogUtil.a("AddDeviceChildSecondActivity", "enterW1PairGuide");
        Intent intent = new Intent(this.b, (Class<?>) DevicePairGuideActivity.class);
        ntt.cNM_(intent, this.g, this.e);
        intent.putExtra("pairGuideProductType", 10);
        intent.putExtra("pairGuideProductName", this.b.getString(R.string.IDS_app_display_name_leo));
        intent.putExtra("IS_PROC", true);
        intent.putExtra("pairGuideFromScanList", false);
        intent.putExtra("pairGuideDeviceMode", ErrorCode.HWID_IS_STOPED);
        startActivityForResult(intent, 1);
    }

    private void b(int i, String str, String str2, int i2) {
        obu d2 = oad.d(i, str, str2, i2);
        d2.a(1);
        d2.cUT_(new d(d2));
        this.j.add(d2);
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
