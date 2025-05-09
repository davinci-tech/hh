package com.huawei.ui.main.stories.privacy.datasourcemanager;

import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.huawei.haf.common.utils.NetworkUtil;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.health.R;
import com.huawei.health.ecologydevice.ui.measure.fragment.device.DeviceCategoryFragment;
import com.huawei.hihealthservice.hihealthkit.cpcheck.OnRequestCallBack;
import com.huawei.nfc.PluginPayAdapter;
import com.huawei.operation.utils.Constants;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.recycleview.HealthRecycleView;
import com.huawei.ui.commonui.titlebar.CustomTitleBar;
import com.huawei.ui.main.R$string;
import com.huawei.ui.main.stories.privacy.datasourcemanager.adapter.DeviceRecordAdapter;
import com.huawei.ui.main.stories.privacy.datasourcemanager.listener.DataDownloadListener;
import com.huawei.ui.main.stories.privacy.datasourcemanager.listener.DataSourceItemClickListener;
import com.huawei.ui.main.stories.privacy.datasourcemanager.receiver.DataDownloadReceiver;
import com.huawei.ui.main.stories.privacy.template.model.bean.PageModelArgs;
import com.huawei.ui.main.stories.privacy.template.model.bean.SourceInfoBean;
import com.huawei.ui.main.stories.privacy.template.view.showdata.PrivacyDataModelActivity;
import defpackage.ipb;
import defpackage.koq;
import defpackage.nrh;
import defpackage.nrz;
import defpackage.rjz;
import defpackage.rkc;
import defpackage.rqt;
import defpackage.rri;
import defpackage.rro;
import defpackage.rrp;
import health.compact.a.LanguageUtil;
import health.compact.a.Utils;
import health.compact.a.util.LogUtil;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

/* loaded from: classes7.dex */
public class DeviceDetailRecordActivity extends BaseActivity implements DataSourceItemClickListener, DataDownloadListener {

    /* renamed from: a, reason: collision with root package name */
    private String f10400a;
    private int b;
    private CustomTitleBar d;
    private DataDownloadReceiver e;
    private String f;
    private String g;
    private int h;
    private DeviceRecordAdapter i;
    private RelativeLayout k;
    private boolean l;
    private ImageView m;
    private boolean n;
    private String p;
    private HealthRecycleView q;
    private List<rkc> s;
    private RelativeLayout t;
    private List<rkc> c = new ArrayList(10);
    private List<rkc> o = new ArrayList(10);
    private List<rkc> j = new ArrayList(10);

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_device_detail_record);
        a();
        e();
    }

    private void e() {
        Intent intent = getIntent();
        if (intent == null) {
            LogUtil.e("DeviceDetailRecordActivity", "intent is null");
            return;
        }
        this.f = intent.getStringExtra(PluginPayAdapter.KEY_DEVICE_INFO_NAME);
        this.f10400a = intent.getStringExtra("client_id");
        if (!TextUtils.isEmpty(this.f)) {
            this.d.setTitleText(String.format(Locale.ROOT, getString(R$string.IDS_hwh_info_record), this.f));
        } else {
            LogUtil.c("DeviceDetailRecordActivity", "mDeviceName is empty");
        }
        this.s = new ArrayList(10);
        if (!TextUtils.isEmpty(this.f10400a)) {
            LogUtil.d("DeviceDetailRecordActivity", "from healthKit");
            d();
            return;
        }
        boolean booleanExtra = intent.getBooleanExtra("is_data_loaded", false);
        this.n = booleanExtra;
        if (!booleanExtra) {
            c();
        }
        ((TextView) findViewById(R.id.tv_loading_text)).setText(R$string.IDS_hw_health_loading);
        this.g = intent.getStringExtra("deviceUniqueCode");
        this.p = intent.getStringExtra("package_name");
        if (TextUtils.isEmpty(this.g) && TextUtils.isEmpty(this.p)) {
            this.k.setVisibility(8);
            this.t.setVisibility(0);
            LogUtil.e("DeviceDetailRecordActivity", "mDeviceUniqueCode or mPackageName is empty");
        } else {
            this.b = intent.getIntExtra("read_type", 0);
            this.h = intent.getIntExtra(DeviceCategoryFragment.DEVICE_TYPE, Integer.MIN_VALUE);
            LogUtil.d("DeviceDetailRecordActivity", "mClassType is ", Integer.valueOf(this.b), "deviceType is", Integer.valueOf(this.h));
            d(this.b, this.g, this.p, this.h);
        }
    }

    private void a() {
        this.k = (RelativeLayout) findViewById(R.id.device_detail_loading);
        this.t = (RelativeLayout) findViewById(R.id.no_data_source);
        this.d = (CustomTitleBar) findViewById(R.id.device_detail_record_title_layout);
        this.m = (ImageView) findViewById(R.id.no_data_image);
        this.d.setTitleBarBackgroundColor(getResources().getColor(R.color._2131297799_res_0x7f090607));
        HealthRecycleView healthRecycleView = (HealthRecycleView) findViewById(R.id.recyclerView);
        this.q = healthRecycleView;
        healthRecycleView.a(false);
        this.q.d(false);
        this.q.setLayoutManager(new LinearLayoutManager(this, 1, false));
        DeviceRecordAdapter deviceRecordAdapter = new DeviceRecordAdapter(this, this);
        this.i = deviceRecordAdapter;
        this.q.setAdapter(deviceRecordAdapter);
    }

    private void d(int i, String str, String str2, int i2) {
        this.t.setVisibility(8);
        new rqt().c(i, str, str2, i2, new c(this));
    }

    private void d() {
        String language = getResources().getConfiguration().locale.getLanguage();
        LogUtil.d("DeviceDetailRecordActivity", "getDataFromHealthKit");
        ipb.d().c(this, new rro(false, this.f10400a, language).getRequestParamsBuilder().b(new OnRequestCallBack<rri>() { // from class: com.huawei.ui.main.stories.privacy.datasourcemanager.DeviceDetailRecordActivity.4
            @Override // com.huawei.hihealthservice.hihealthkit.cpcheck.OnRequestCallBack
            /* renamed from: d, reason: merged with bridge method [inline-methods] */
            public void onSuccess(rri rriVar) {
                DeviceDetailRecordActivity.this.k.setVisibility(8);
                if (rriVar == null || rriVar.getWriteRecords() == null) {
                    return;
                }
                DeviceDetailRecordActivity.this.c(rriVar);
            }

            @Override // com.huawei.hihealthservice.hihealthkit.cpcheck.OnRequestCallBack
            public void onFail(int i, Throwable th) {
                DeviceDetailRecordActivity.this.k.setVisibility(8);
                Object[] objArr = new Object[3];
                objArr[0] = Integer.valueOf(i);
                objArr[1] = " accessRecordModel ";
                objArr[2] = th == null ? Constants.NULL : th.getMessage();
                LogUtil.e("DeviceDetailRecordActivity", objArr);
            }
        }).a());
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public void c(rri rriVar) {
        char c2;
        rkc rkcVar = new rkc();
        rkcVar.d(2);
        rkcVar.b(true);
        this.s.add(rkcVar);
        Iterator<rrp> it = rriVar.getWriteRecords().iterator();
        while (it.hasNext()) {
            String dataType = it.next().getDataType();
            dataType.hashCode();
            switch (dataType.hashCode()) {
                case -1857182118:
                    if (dataType.equals("com.huawei.activityrecord")) {
                        c2 = 0;
                        break;
                    }
                    c2 = 65535;
                    break;
                case -577505844:
                    if (dataType.equals("com.huawei.continuous.sleep.fragment")) {
                        c2 = 1;
                        break;
                    }
                    c2 = 65535;
                    break;
                case -452904221:
                    if (dataType.equals("com.huawei.instantaneous.body_weight")) {
                        c2 = 2;
                        break;
                    }
                    c2 = 65535;
                    break;
                case -343678452:
                    if (dataType.equals("com.huawei.continuous.steps.delta")) {
                        c2 = 3;
                        break;
                    }
                    c2 = 65535;
                    break;
                case 90592855:
                    if (dataType.equals("com.huawei.instantaneous.blood_glucose")) {
                        c2 = 4;
                        break;
                    }
                    c2 = 65535;
                    break;
                case 1900688636:
                    if (dataType.equals("com.huawei.instantaneous.blood_pressure")) {
                        c2 = 5;
                        break;
                    }
                    c2 = 65535;
                    break;
                default:
                    c2 = 65535;
                    break;
            }
            if (c2 == 0) {
                l();
            } else if (c2 == 1) {
                b(true);
            } else if (c2 == 2) {
                e(true);
            } else if (c2 == 3) {
                d(true);
            } else if (c2 == 4) {
                c(true);
            } else if (c2 == 5) {
                a(true);
            }
        }
        this.i.d(this.j.size());
        this.s.addAll(this.j);
        this.i.b(this.s);
    }

    private void j() {
        rkc rkcVar = new rkc();
        rkcVar.d(3);
        rkcVar.e(101);
        rkcVar.a(getResources().getString(R$string.IDS_sport_distance));
        rkcVar.e(true);
        rkcVar.b(R.mipmap._2131821018_res_0x7f1101da);
        this.c.add(rkcVar);
    }

    private void i() {
        rkc rkcVar = new rkc();
        rkcVar.d(3);
        rkcVar.e(110);
        rkcVar.a(getResources().getString(R$string.IDS_motiontrack_climb_stairs_tip));
        rkcVar.e(true);
        rkcVar.b(R.mipmap._2131821017_res_0x7f1101d9);
        this.c.add(rkcVar);
    }

    private void h() {
        rkc rkcVar = new rkc();
        rkcVar.d(3);
        rkcVar.e(111);
        rkcVar.a(getResources().getString(R$string.IDS_active_caloric));
        rkcVar.e(true);
        rkcVar.b(R.mipmap._2131821020_res_0x7f1101dc);
        this.c.add(rkcVar);
    }

    private void g() {
        rkc rkcVar = new rkc();
        rkcVar.d(3);
        rkcVar.a(getResources().getString(R$string.IDS_hw_health_show_healthdata_heart_bmp));
        rkcVar.c(true);
        rkcVar.e(102);
        rkcVar.b(R.mipmap._2131821019_res_0x7f1101db);
        this.o.add(rkcVar);
    }

    private void e(boolean z) {
        rkc rkcVar = new rkc();
        rkcVar.d(3);
        rkcVar.e(109);
        rkcVar.a(getResources().getString(R$string.IDS_hw_show_set_weight));
        rkcVar.b(R.mipmap._2131821027_res_0x7f1101e3);
        if (z) {
            rkcVar.b(true);
            this.j.add(rkcVar);
        } else {
            rkcVar.c(true);
            this.o.add(rkcVar);
        }
    }

    private void b(boolean z) {
        rkc rkcVar = new rkc();
        rkcVar.d(3);
        rkcVar.e(103);
        rkcVar.a(getResources().getString(R$string.IDS_hw_show_main_home_page_sleep));
        rkcVar.b(R.mipmap._2131821024_res_0x7f1101e0);
        if (z) {
            rkcVar.b(true);
            this.j.add(rkcVar);
        } else {
            rkcVar.c(true);
            this.o.add(rkcVar);
        }
    }

    private void d(boolean z) {
        rkc rkcVar = new rkc();
        rkcVar.d(3);
        rkcVar.e(100);
        rkcVar.a(getResources().getString(R$string.IDS_hw_show_main_permission_steps));
        rkcVar.b(R.mipmap._2131821025_res_0x7f1101e1);
        if (z) {
            rkcVar.b(true);
            this.j.add(rkcVar);
        } else {
            rkcVar.e(true);
            this.c.add(rkcVar);
        }
    }

    private void l() {
        rkc rkcVar = new rkc();
        rkcVar.d(3);
        rkcVar.e(112);
        rkcVar.a(getResources().getString(R$string.IDS_privacy_activity_records));
        rkcVar.b(R.mipmap._2131821013_res_0x7f1101d5);
        rkcVar.b(true);
        this.j.add(rkcVar);
    }

    private void k() {
        rkc rkcVar = new rkc();
        rkcVar.d(3);
        rkcVar.e(104);
        rkcVar.c(true);
        rkcVar.a(getResources().getString(R$string.IDS_settings_one_level_menu_settings_item_text_id14));
        rkcVar.b(R.mipmap._2131821023_res_0x7f1101df);
        this.o.add(rkcVar);
    }

    private void f() {
        rkc rkcVar = new rkc();
        rkcVar.d(3);
        rkcVar.e(105);
        rkcVar.c(true);
        rkcVar.a(getResources().getString(R$string.IDS_hw_health_blood_oxygen));
        rkcVar.b(R.mipmap._2131821014_res_0x7f1101d6);
        this.o.add(rkcVar);
    }

    private void a(boolean z) {
        rkc rkcVar = new rkc();
        rkcVar.d(3);
        rkcVar.e(107);
        rkcVar.a(getResources().getString(R$string.IDS_hw_show_main_home_page_bloodpressure));
        rkcVar.b(R.mipmap._2131821015_res_0x7f1101d7);
        if (z) {
            rkcVar.b(true);
            this.j.add(rkcVar);
        } else {
            rkcVar.c(true);
            this.o.add(rkcVar);
        }
    }

    private void c(boolean z) {
        rkc rkcVar = new rkc();
        rkcVar.d(3);
        rkcVar.e(108);
        rkcVar.a(getResources().getString(R$string.IDS_hw_show_main_home_page_bloodsugar));
        rkcVar.b(R.mipmap._2131821016_res_0x7f1101d8);
        if (z) {
            rkcVar.b(true);
            this.j.add(rkcVar);
        } else {
            rkcVar.c(true);
            this.o.add(rkcVar);
        }
    }

    private void n() {
        rkc rkcVar = new rkc();
        rkcVar.d(3);
        rkcVar.c(true);
        if (Utils.o()) {
            rkcVar.e(113);
            rkcVar.a(getResources().getString(R$string.IDS_health_skin_temperature));
        } else {
            rkcVar.e(106);
            rkcVar.a(getResources().getString(R$string.IDS_settings_health_temperature));
        }
        rkcVar.b(R.mipmap._2131821026_res_0x7f1101e2);
        this.o.add(rkcVar);
    }

    @Override // com.huawei.ui.main.stories.privacy.datasourcemanager.listener.DataSourceItemClickListener
    public void onItemClickListener(int i) {
        if (koq.b(this.s)) {
            LogUtil.e("DeviceDetailRecordActivity", "mRecordList is null or mRecordList.size() is 0");
            return;
        }
        rkc rkcVar = this.s.get(i);
        if (rkcVar.e() == 3) {
            int a2 = rkcVar.a();
            if (!TextUtils.isEmpty(this.f10400a)) {
                LogUtil.d("DeviceDetailRecordActivity", "HealthKit click");
                PrivacyDataModelActivity.dQK_(this, new PageModelArgs(a2, "PrivacyDataConstructor", this.f10400a, 2, 1), new SourceInfoBean(false, this.f));
                return;
            }
            PageModelArgs pageModelArgs = new PageModelArgs(a2, "PrivacyDataConstructor", this.g, 1, 1);
            pageModelArgs.setClassType(this.b);
            pageModelArgs.setPackageName(this.p);
            if (getIntent() != null) {
                this.l = getIntent().getBooleanExtra("other_manufacaturer", false);
            }
            pageModelArgs.setOtherManufacturer(this.l);
            LogUtil.d("DeviceDetailRecordActivity", "DataSource click dataType is ", Integer.valueOf(a2));
            PrivacyDataModelActivity.b(this, pageModelArgs);
        }
    }

    private void d(rjz rjzVar) {
        LogUtil.d("DeviceDetailRecordActivity", "into updateList");
        this.s.clear();
        if (rjzVar.h() || rjzVar.j() || rjzVar.a() || rjzVar.d()) {
            b(rjzVar);
        }
        boolean z = rjzVar.g() || rjzVar.k() || rjzVar.f() || rjzVar.i();
        boolean z2 = rjzVar.b() || rjzVar.e() || rjzVar.c() || rjzVar.n();
        if (z || z2) {
            rkc rkcVar = new rkc();
            rkcVar.d(2);
            rkcVar.e(getResources().getString(R$string.IDS_settings_health_condition));
            this.s.add(rkcVar);
            c(rjzVar);
        } else {
            LogUtil.d("DeviceDetailRecordActivity", "ActiveStatistic and HealthPower no data");
        }
        this.i.b(this.s);
        if (this.s.size() > 0) {
            LogUtil.d("DeviceDetailRecordActivity", Integer.valueOf(this.s.size()));
        } else {
            LogUtil.e("DeviceDetailRecordActivity", "mRecordList size is 0");
        }
        if (this.n || !NetworkUtil.i()) {
            return;
        }
        nrh.b(this, R$string.IDS_hwh_data_loading);
    }

    private void b(rjz rjzVar) {
        LogUtil.d("DeviceDetailRecordActivity", "setActivityStatistic");
        this.c.clear();
        rkc rkcVar = new rkc();
        rkcVar.d(2);
        rkcVar.e(getResources().getString(R$string.IDS_settings_active_statistic));
        this.s.add(rkcVar);
        if (rjzVar.h()) {
            d(false);
        }
        if (rjzVar.j()) {
            j();
        }
        if (rjzVar.a()) {
            i();
        }
        if (rjzVar.d()) {
            h();
        }
        this.s.addAll(this.c);
        this.i.b(this.c.size());
    }

    private void c(rjz rjzVar) {
        LogUtil.d("DeviceDetailRecordActivity", "setShowHealthCondition");
        this.o.clear();
        if (rjzVar.g()) {
            g();
        }
        if (rjzVar.k()) {
            e(false);
        }
        if (rjzVar.f()) {
            b(false);
        }
        if (rjzVar.i()) {
            k();
        }
        if (rjzVar.b()) {
            f();
        }
        if (rjzVar.e()) {
            a(false);
        }
        if (rjzVar.c()) {
            c(false);
        }
        if (rjzVar.n()) {
            n();
        }
        this.s.addAll(this.o);
        rkc rkcVar = new rkc();
        rkcVar.d(1);
        this.s.add(rkcVar);
        this.i.e(this.o.size());
    }

    private void c() {
        LogUtil.d("DeviceDetailRecordActivity", "registerBroadCast");
        this.e = new DataDownloadReceiver(this);
        LocalBroadcastManager.getInstance(this).registerReceiver(this.e, new IntentFilter("com.huawei.hihealth.action_sync"));
    }

    private void o() {
        if (this.e != null) {
            LocalBroadcastManager.getInstance(this).unregisterReceiver(this.e);
            this.e = null;
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        ipb.d().e(this);
        o();
        super.onDestroy();
    }

    private void b() {
        Toast.makeText(this, R$string.IDS_hwh_data_is_loaded, 0).show();
        d(this.b, this.g, this.p, this.h);
    }

    @Override // com.huawei.ui.main.stories.privacy.datasourcemanager.listener.DataDownloadListener
    public void dataDownloadListener(int i) {
        LogUtil.d("DeviceDetailRecordActivity", "actionSyncStatus is", Integer.valueOf(i));
        if (i == 2) {
            this.n = true;
            b();
            o();
        } else if (i == 3) {
            this.n = false;
        }
    }

    static class c implements DataSourceCallback<rjz> {

        /* renamed from: a, reason: collision with root package name */
        WeakReference<DeviceDetailRecordActivity> f10401a;

        c(DeviceDetailRecordActivity deviceDetailRecordActivity) {
            this.f10401a = new WeakReference<>(deviceDetailRecordActivity);
        }

        @Override // com.huawei.ui.main.stories.privacy.datasourcemanager.DataSourceCallback
        /* renamed from: d, reason: merged with bridge method [inline-methods] */
        public void onResponse(int i, rjz rjzVar) {
            LogUtil.d("DeviceDetailRecordActivity", "DataTypeCallBack resultCode = ", Integer.valueOf(i));
            WeakReference<DeviceDetailRecordActivity> weakReference = this.f10401a;
            if (weakReference == null) {
                LogUtil.e("DeviceDetailRecordActivity", "mWeakReference is null");
                return;
            }
            DeviceDetailRecordActivity deviceDetailRecordActivity = weakReference.get();
            if (deviceDetailRecordActivity != null) {
                deviceDetailRecordActivity.d(i, rjzVar);
            } else {
                LogUtil.e("DeviceDetailRecordActivity", "DataTypeCallBack deviceDetailRecordActivity is null");
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(int i, rjz rjzVar) {
        LogUtil.d("DeviceDetailRecordActivity", "resultCode = ", Integer.valueOf(i));
        if (i != 0 || rjzVar == null) {
            this.k.setVisibility(8);
            this.t.setVisibility(0);
            m();
        } else {
            this.k.setVisibility(8);
            d(rjzVar);
        }
    }

    private void m() {
        if (LanguageUtil.bc(this)) {
            this.m.setBackground(nrz.cKn_(this, R.mipmap._2131821228_res_0x7f1102ac));
        } else {
            this.m.setBackgroundResource(R.mipmap._2131821228_res_0x7f1102ac);
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
