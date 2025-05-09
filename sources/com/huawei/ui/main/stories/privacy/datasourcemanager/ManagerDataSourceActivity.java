package com.huawei.ui.main.stories.privacy.datasourcemanager;

import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import androidx.core.content.ContextCompat;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.huawei.haf.common.utils.NetworkUtil;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.health.R;
import com.huawei.health.ecologydevice.ui.measure.fragment.device.DeviceCategoryFragment;
import com.huawei.hihealth.HiSyncOption;
import com.huawei.hihealth.api.HiHealthNativeApi;
import com.huawei.hihealthservice.hihealthkit.cpcheck.OnRequestCallBack;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.nfc.PluginPayAdapter;
import com.huawei.operation.utils.Constants;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.recycleview.HealthRecycleView;
import com.huawei.ui.commonui.titlebar.CustomTitleBar;
import com.huawei.ui.main.R$string;
import com.huawei.ui.main.stories.privacy.datasourcemanager.adapter.DataSourceAdapter;
import com.huawei.ui.main.stories.privacy.datasourcemanager.bean.SourceInfo;
import com.huawei.ui.main.stories.privacy.datasourcemanager.listener.DataDownloadListener;
import com.huawei.ui.main.stories.privacy.datasourcemanager.listener.DataSourceItemClickListener;
import com.huawei.ui.main.stories.privacy.datasourcemanager.receiver.DataDownloadReceiver;
import defpackage.ipb;
import defpackage.koq;
import defpackage.mct;
import defpackage.nrh;
import defpackage.nrz;
import defpackage.rqt;
import defpackage.rri;
import defpackage.rro;
import defpackage.rrp;
import health.compact.a.LanguageUtil;
import health.compact.a.Utils;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/* loaded from: classes7.dex */
public class ManagerDataSourceActivity extends BaseActivity implements DataSourceItemClickListener, DataDownloadListener {

    /* renamed from: a, reason: collision with root package name */
    private int f10402a;
    private DataDownloadReceiver b;
    private boolean c = false;
    private CustomTitleBar d;
    private DataSourceAdapter e;
    private ImageView f;
    private List<SourceInfo> g;
    private HealthRecycleView h;
    private RelativeLayout i;
    private RelativeLayout j;
    private rqt n;

    @Override // com.huawei.ui.main.stories.privacy.datasourcemanager.listener.DataSourceItemClickListener
    public void onItemClickListener(int i) {
        if (koq.b(this.g)) {
            LogUtil.b("ManagerDataSourceActivity", "mDeviceList is null or mDeviceList.size() is 0");
            return;
        }
        SourceInfo sourceInfo = this.g.get(i);
        String deviceName = sourceInfo.getDeviceName();
        String clientId = sourceInfo.getClientId();
        if (this.f10402a == 2) {
            LogUtil.a("ManagerDataSourceActivity", "from health_kit");
            Intent intent = new Intent();
            intent.setClass(this, DeviceDetailRecordActivity.class);
            intent.putExtra(PluginPayAdapter.KEY_DEVICE_INFO_NAME, deviceName);
            intent.putExtra("client_id", clientId);
            startActivity(intent);
            return;
        }
        int sourceType = sourceInfo.getSourceType();
        String deviceUniqueCode = sourceInfo.getDeviceUniqueCode();
        Intent intent2 = new Intent();
        intent2.putExtra(DeviceCategoryFragment.DEVICE_TYPE, sourceInfo.getDeviceType());
        intent2.putExtra(PluginPayAdapter.KEY_DEVICE_INFO_NAME, deviceName);
        intent2.putExtra("deviceUniqueCode", deviceUniqueCode);
        intent2.putExtra("SOURCE_TYPE", sourceType);
        intent2.putExtra("is_data_loaded", this.c);
        intent2.setClass(this, DeviceDetailRecordActivity.class);
        if (sourceType == 2) {
            LogUtil.a("ManagerDataSourceActivity", "device type");
            intent2.putExtra("read_type", 2);
            intent2.putExtra("other_manufacaturer", sourceInfo.isOtherManufacturer());
            startActivity(intent2);
            return;
        }
        if (sourceType == 3) {
            LogUtil.a("ManagerDataSourceActivity", "manual type");
            intent2.putExtra("package_name", sourceInfo.getPackageName());
            intent2.putExtra("read_type", 3);
            startActivity(intent2);
            return;
        }
        if (sourceType == 1) {
            LogUtil.a("ManagerDataSourceActivity", "APP type");
            intent2.putExtra("read_type", 1);
            intent2.putExtra("package_name", sourceInfo.getPackageName());
            startActivity(intent2);
            return;
        }
        LogUtil.a("ManagerDataSourceActivity", "error type");
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        c();
        if (!NetworkUtil.i()) {
            nrh.b(this, R$string.IDS_connect_network);
            this.j.setVisibility(8);
            this.i.setVisibility(0);
            h();
        }
        Intent intent = getIntent();
        if (intent != null) {
            int intExtra = intent.getIntExtra("jump_key", 0);
            this.f10402a = intExtra;
            if (intExtra == 1) {
                LogUtil.a("ManagerDataSourceActivity", "from data source management");
                ReleaseLogUtil.e("R_ManagerDataSourceActivity", "from data source management release");
                this.d.setTitleText(getResources().getString(R$string.IDS_hwh_data_source_manager));
                a();
                j();
                return;
            }
            if (intExtra == 2) {
                LogUtil.a("ManagerDataSourceActivity", "from health_kit");
                this.d.setTitleText(getResources().getString(R$string.IDS_hwh_Health_Kit_data_show));
                d();
                return;
            }
            LogUtil.a("ManagerDataSourceActivity", "jump_key is error");
            return;
        }
        LogUtil.b("ManagerDataSourceActivity", "intent is null");
    }

    private void b() {
        LogUtil.a("ManagerDataSourceActivity", "registerBroadCast");
        this.b = new DataDownloadReceiver(this);
        LocalBroadcastManager.getInstance(this).registerReceiver(this.b, new IntentFilter("com.huawei.hihealth.action_sync"));
    }

    private void c() {
        setContentView(R.layout.activity_manager_data_source);
        this.j = (RelativeLayout) findViewById(R.id.data_source_loading);
        HealthRecycleView healthRecycleView = (HealthRecycleView) findViewById(R.id.recyclerView);
        this.h = healthRecycleView;
        healthRecycleView.a(false);
        this.h.d(false);
        this.i = (RelativeLayout) findViewById(R.id.no_data_source);
        this.f = (ImageView) findViewById(R.id.no_data_image);
        CustomTitleBar customTitleBar = (CustomTitleBar) findViewById(R.id.manager_data_source_title_layout);
        this.d = customTitleBar;
        customTitleBar.setTitleBarBackgroundColor(ContextCompat.getColor(this, R.color._2131297799_res_0x7f090607));
        this.g = new ArrayList(10);
        this.h.setLayoutManager(new LinearLayoutManager(this, 1, false));
        DataSourceAdapter dataSourceAdapter = new DataSourceAdapter(this, this);
        this.e = dataSourceAdapter;
        this.h.setAdapter(dataSourceAdapter);
    }

    private void j() {
        if (!TextUtils.isEmpty(mct.e(this, "data_downing_flag", ""))) {
            this.c = true;
            LogUtil.a("ManagerDataSourceActivity", "data has download");
            return;
        }
        if (!NetworkUtil.i()) {
            this.c = true;
            LogUtil.a("ManagerDataSourceActivity", "no network connected");
            return;
        }
        if (this.b == null) {
            b();
        }
        HiSyncOption hiSyncOption = new HiSyncOption();
        hiSyncOption.setSyncModel(2);
        hiSyncOption.setSyncAction(5);
        hiSyncOption.setSyncDataType(20000);
        hiSyncOption.setSyncScope(1);
        hiSyncOption.setSyncMethod(2);
        hiSyncOption.setSyncManual(1);
        hiSyncOption.setSyncDataArea(1);
        HiHealthNativeApi.a(this).synCloud(hiSyncOption, null);
        mct.b(this, "data_downing_flag", "dataStartDownLoad");
        LogUtil.a("ManagerDataSourceActivity", "startSyncData");
    }

    private void d() {
        LogUtil.a("ManagerDataSourceActivity", "getDataFromHealthKit isOversea=" + Utils.o() + ",ifAllowLogin=" + Utils.i());
        if (Utils.o() && !Utils.i()) {
            this.j.setVisibility(8);
            this.i.setVisibility(0);
            LogUtil.h("ManagerDataSourceActivity", "getDataFromHealthKit return for Oversea && Not AllowLogin");
            return;
        }
        e();
    }

    private void e() {
        ipb.d().c(this, new rro(false, "", getResources().getConfiguration().locale.getLanguage()).getRequestParamsBuilder().b(new OnRequestCallBack<rri>() { // from class: com.huawei.ui.main.stories.privacy.datasourcemanager.ManagerDataSourceActivity.3
            @Override // com.huawei.hihealthservice.hihealthkit.cpcheck.OnRequestCallBack
            /* renamed from: d, reason: merged with bridge method [inline-methods] */
            public void onSuccess(rri rriVar) {
                ManagerDataSourceActivity.this.j.setVisibility(8);
                if (rriVar == null || koq.b(rriVar.getWriteRecords())) {
                    ManagerDataSourceActivity.this.i.setVisibility(0);
                    ManagerDataSourceActivity.this.h();
                    return;
                }
                ManagerDataSourceActivity.this.i.setVisibility(8);
                HashMap hashMap = new HashMap(16);
                for (rrp rrpVar : rriVar.getWriteRecords()) {
                    SourceInfo sourceInfo = new SourceInfo();
                    sourceInfo.setSourceType(1);
                    sourceInfo.setDeviceName(rrpVar.getAppName());
                    sourceInfo.setClientId(rrpVar.getAppId());
                    sourceInfo.setDeviceIcon(rrpVar.getAppIconPath());
                    sourceInfo.setItemType(3);
                    hashMap.put(rrpVar.getAppId(), sourceInfo);
                }
                SourceInfo sourceInfo2 = new SourceInfo();
                sourceInfo2.setHealthKit(true);
                sourceInfo2.setItemType(2);
                ManagerDataSourceActivity.this.g.add(sourceInfo2);
                ManagerDataSourceActivity.this.g.addAll(hashMap.values());
                SourceInfo sourceInfo3 = new SourceInfo();
                sourceInfo3.setItemType(1);
                ManagerDataSourceActivity.this.g.add(sourceInfo3);
                ManagerDataSourceActivity.this.e.c(hashMap.size());
                ManagerDataSourceActivity.this.e.d(ManagerDataSourceActivity.this.g);
            }

            @Override // com.huawei.hihealthservice.hihealthkit.cpcheck.OnRequestCallBack
            public void onFail(int i, Throwable th) {
                ManagerDataSourceActivity.this.j.setVisibility(8);
                ManagerDataSourceActivity.this.i.setVisibility(0);
                ManagerDataSourceActivity.this.h();
                Object[] objArr = new Object[3];
                objArr[0] = Integer.valueOf(i);
                objArr[1] = " accessRecordModel ";
                objArr[2] = th == null ? Constants.NULL : th.getMessage();
                LogUtil.b("ManagerDataSourceActivity", objArr);
            }
        }).a());
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        ipb.d().e(this);
        f();
        if (this.n != null) {
            this.n = null;
        }
    }

    private void f() {
        if (this.b != null) {
            LocalBroadcastManager.getInstance(this).unregisterReceiver(this.b);
            this.b = null;
        }
    }

    private void a() {
        LogUtil.a("ManagerDataSourceActivity", "getDeviceList");
        ReleaseLogUtil.e("R_ManagerDataSourceActivity", "start getdeviceList");
        rqt rqtVar = new rqt();
        this.n = rqtVar;
        rqtVar.b(new e(this));
    }

    @Override // com.huawei.ui.main.stories.privacy.datasourcemanager.listener.DataDownloadListener
    public void dataDownloadListener(int i) {
        LogUtil.a("ManagerDataSourceActivity", "dataDownloadStatus is", Integer.valueOf(i));
        if (i == 2) {
            this.c = true;
            f();
        } else if (i == 3) {
            mct.b(this, "data_downing_flag", "");
        }
    }

    static class e implements DataSourceCallback<List<SourceInfo>> {
        WeakReference<ManagerDataSourceActivity> e;

        e(ManagerDataSourceActivity managerDataSourceActivity) {
            this.e = new WeakReference<>(managerDataSourceActivity);
        }

        @Override // com.huawei.ui.main.stories.privacy.datasourcemanager.DataSourceCallback
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public void onResponse(int i, List<SourceInfo> list) {
            LogUtil.a("ManagerDataSourceActivity", "DeviceListCallBack errorCode is ", Integer.valueOf(i));
            ReleaseLogUtil.e("R_ManagerDataSourceActivity", "release DeviceListCallBack errorCode is ", Integer.valueOf(i));
            WeakReference<ManagerDataSourceActivity> weakReference = this.e;
            if (weakReference == null) {
                LogUtil.b("ManagerDataSourceActivity", "mWeakReference is null");
                return;
            }
            ManagerDataSourceActivity managerDataSourceActivity = weakReference.get();
            if (managerDataSourceActivity != null) {
                managerDataSourceActivity.e(list);
            } else {
                LogUtil.b("ManagerDataSourceActivity", "DataTypeCallBack managerDataSourceActivity is null");
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(List<SourceInfo> list) {
        this.j.setVisibility(8);
        if (koq.b(list)) {
            ReleaseLogUtil.e("R_ManagerDataSourceActivity", "release list is empty");
            this.i.setVisibility(0);
            h();
            LogUtil.b("ManagerDataSourceActivity", "list is empty");
            return;
        }
        this.i.setVisibility(8);
        ReleaseLogUtil.e("R_ManagerDataSourceActivity", "release list size is", Integer.valueOf(list.size()));
        LogUtil.a("ManagerDataSourceActivity", "list size is", Integer.valueOf(list.size()));
        this.g.addAll(list);
        rqt rqtVar = this.n;
        if (rqtVar != null) {
            this.e.a(rqtVar.c());
            this.e.c(this.n.a());
        }
        this.e.d(this.g);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void h() {
        if (LanguageUtil.bc(this)) {
            this.f.setBackground(nrz.cKn_(this, R.mipmap._2131821228_res_0x7f1102ac));
        } else {
            this.f.setBackgroundResource(R.mipmap._2131821228_res_0x7f1102ac);
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
