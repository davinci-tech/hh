package com.huawei.ui.main.stories.privacy.template.view.showdata;

import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.util.SparseArray;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.R;
import com.huawei.hihealth.HiAppInfo;
import com.huawei.hihealth.HiDataReadOption;
import com.huawei.hihealth.HiDataSourceFetchOption;
import com.huawei.hihealth.HiDeviceInfo;
import com.huawei.hihealth.HiHealthClient;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.api.HiHealthNativeApi;
import com.huawei.hihealth.data.listener.HiDataClientListener;
import com.huawei.hihealth.data.listener.HiDataReadResultListener;
import com.huawei.hihealth.data.model.HiBloodPressureMetaData;
import com.huawei.hihealth.dictionary.utils.DicDataTypeUtil;
import com.huawei.hihealth.util.HiJsonUtil;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.ble.BleConstants;
import com.huawei.ui.commonui.recycleview.HealthRecycleView;
import com.huawei.ui.commonui.subheader.HealthSubHeader;
import com.huawei.ui.commonui.titlebar.CustomTitleBar;
import com.huawei.ui.main.R$string;
import com.huawei.ui.main.stories.health.util.BloodPressureUtil;
import com.huawei.ui.main.stories.privacy.template.model.adapter.BloodOxygenDetailAdapter;
import com.huawei.ui.main.stories.privacy.template.model.adapter.BloodPressureDetailAdapter;
import com.huawei.ui.main.stories.privacy.template.model.adapter.DataSourceViewAdapter;
import com.huawei.ui.main.stories.privacy.template.model.bean.PageModelArgs;
import com.huawei.ui.main.stories.privacy.template.model.bean.PrivacyDataModel;
import com.huawei.ui.main.stories.privacy.template.model.bean.SourceInfoBean;
import com.huawei.ui.main.stories.privacy.template.view.showdata.PrivacyDetailActivity;
import com.huawei.ui.main.stories.template.BaseActivity;
import com.huawei.uikit.hwcolumnsystem.widget.HwColumnSystem;
import defpackage.koq;
import defpackage.nsn;
import defpackage.nsy;
import defpackage.qkv;
import defpackage.rju;
import defpackage.rrb;
import defpackage.rre;
import defpackage.rrf;
import defpackage.rsr;
import defpackage.rto;
import defpackage.rud;
import defpackage.trg;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.utils.StringUtils;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/* loaded from: classes7.dex */
public class PrivacyDetailActivity extends BaseActivity {

    /* renamed from: a, reason: collision with root package name */
    private static final Map<String, String> f10435a;
    private HealthRecycleView b;
    private DataSourceViewAdapter d;
    private DataSourceViewAdapter e;
    private CustomTitleBar f;
    private LinearLayout h;
    private HealthRecycleView i;
    private boolean j;
    private int k;
    private PrivacyDataModel l;
    private long m;
    private PageModelArgs o;
    private String p;
    private int q;
    private LinearLayout r;
    private SourceInfoBean s;
    private int y;
    private String c = "";
    private String t = "";
    private List<PrivacyDataModel> g = new CopyOnWriteArrayList();
    private Handler n = new Handler(Looper.getMainLooper());

    static {
        HashMap hashMap = new HashMap(16);
        f10435a = hashMap;
        hashMap.put("001", "HUAWEI");
        hashMap.put("002", "Honor");
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.privacy_data_detail_view);
        Intent intent = getIntent();
        if (intent != null) {
            this.o = (PageModelArgs) intent.getParcelableExtra("extra_page_model_args");
            this.l = (PrivacyDataModel) intent.getParcelableExtra("extra_privacy_data_model");
            this.s = (SourceInfoBean) intent.getParcelableExtra("extra_source_info");
        }
        PageModelArgs pageModelArgs = this.o;
        if (pageModelArgs == null || this.l == null) {
            LogUtil.b("PrivacyDetailActivity", "PageModelArgs or mPrePageModel or sourceInfoBean is null");
            return;
        }
        this.y = pageModelArgs.getDataSource();
        this.k = this.o.getPageType();
        o();
        k();
    }

    private void o() {
        this.r = (LinearLayout) findViewById(R.id.source_info_detail);
        this.h = (LinearLayout) findViewById(R.id.privacy_device_info);
        CustomTitleBar customTitleBar = (CustomTitleBar) findViewById(R.id.device_detail_title_bar);
        this.f = customTitleBar;
        customTitleBar.setTitleBarBackgroundColor(ContextCompat.getColor(this, R.color._2131297799_res_0x7f090607));
        this.b = (HealthRecycleView) findViewById(R.id.privacy_data_detail_recycle_view);
        this.i = (HealthRecycleView) findViewById(R.id.privacy_data_device_recycle_view);
        int i = this.k;
        if (i == 105 && this.y == 3) {
            this.d = new BloodOxygenDetailAdapter();
        } else if (i == 107 && this.y != 2) {
            this.d = new BloodPressureDetailAdapter();
        } else {
            this.d = new DataSourceViewAdapter();
        }
        this.e = new DataSourceViewAdapter();
        HealthSubHeader b = b(R.id.privacy_data_detail_title, R.id.item_title);
        b.setHeadTitleText(getResources().getString(R$string.IDS_privacy_device_info));
        b.setSplitterVisibility(4);
        b.setSubHeaderBackgroundColor(0);
        if (p() || t()) {
            a();
        }
    }

    private void k() {
        this.f.setTitleText(getResources().getString(R$string.IDS_privacy_data_detail));
        this.t = getResources().getString(this.y == 2 ? R$string.IDS_privacy_record_time : R$string.IDS_privacy_record_app_time);
        if (this.y == 2) {
            q();
        } else {
            ThreadPoolManager.d().execute(new Runnable() { // from class: com.huawei.ui.main.stories.privacy.template.view.showdata.PrivacyDetailActivity.2
                @Override // java.lang.Runnable
                public void run() {
                    rud.c();
                    PrivacyDetailActivity.this.s();
                    PrivacyDetailActivity privacyDetailActivity = PrivacyDetailActivity.this;
                    privacyDetailActivity.c(privacyDetailActivity.k, PrivacyDetailActivity.this.l.getStartTime(), PrivacyDetailActivity.this.l.getEndTime());
                }
            });
        }
    }

    private void a() {
        if (this.r == null || !nsn.ae(this)) {
            return;
        }
        HwColumnSystem hwColumnSystem = new HwColumnSystem(this);
        hwColumnSystem.e(0);
        if (hwColumnSystem.f() != 8) {
            return;
        }
        FrameLayout.LayoutParams layoutParams = this.r.getLayoutParams() instanceof FrameLayout.LayoutParams ? (FrameLayout.LayoutParams) this.r.getLayoutParams() : null;
        int round = Math.round(hwColumnSystem.g()) + hwColumnSystem.a();
        if (layoutParams != null) {
            layoutParams.setMarginStart(round);
            layoutParams.setMarginEnd(round);
        }
    }

    private boolean p() {
        int i = this.k;
        return i == 106 || i == 113;
    }

    private boolean t() {
        return this.k == 102;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void q() {
        this.r.setVisibility(0);
        e(this.s);
        e(this.g);
        if (!this.s.isDevice()) {
            this.h.setVisibility(8);
            return;
        }
        List<PrivacyDataModel> c = c(this.s);
        if (c.size() <= 1) {
            this.h.setVisibility(8);
        } else if ("UNKNOWN".equals(c.get(0).getDataDesc())) {
            this.h.setVisibility(8);
        } else {
            this.h.setVisibility(0);
            c(c);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void s() {
        LogUtil.a("PrivacyDetailActivity", "showDeviceInfo clientId :", Integer.valueOf(this.l.getClientId()));
        HiHealthNativeApi.a(this).fetchDataSource(HiDataSourceFetchOption.builder().a(1).c(new int[]{this.l.getClientId()}).e(), new a(this));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(List<HiHealthClient> list) {
        if (list == null || list.size() <= 0) {
            LogUtil.a("PrivacyDetailActivity", "handleClientSourceResult clientList is null or size is 0");
            r();
            return;
        }
        HiHealthClient hiHealthClient = list.get(0);
        HiDeviceInfo hiDeviceInfo = hiHealthClient.getHiDeviceInfo();
        HiAppInfo hiAppInfo = hiHealthClient.getHiAppInfo();
        if (hiDeviceInfo == null || hiAppInfo == null) {
            LogUtil.a("PrivacyDetailActivity", "deviceInfo or appInfo is null");
            r();
            return;
        }
        String packageName = hiAppInfo.getPackageName();
        LogUtil.a("PrivacyDetailActivity", "handleClientSourceResult packageName is ", packageName);
        String deviceName = rrf.d(hiDeviceInfo).getDeviceName();
        LogUtil.a("PrivacyDetailActivity", "handleClientSourceResult deviceName is ", deviceName);
        if (rrb.d(packageName)) {
            String appName = hiAppInfo.getAppName();
            this.p = appName;
            LogUtil.a("PrivacyDetailActivity", "handleClientSourceResult getAppName is ", appName);
            if ("未知APP".equals(this.p)) {
                this.p = deviceName;
            }
            if (TextUtils.isEmpty(this.p)) {
                this.p = getResources().getString(R$string.IDS_hwh_datasource_unknow_application);
            }
            this.s = new SourceInfoBean(false, this.p);
        } else if (rrb.c(packageName, hiDeviceInfo.getDeviceUniqueCode())) {
            this.p = getResources().getString(R$string.IDS_hw_health_show_healthdata_input);
            this.s = new SourceInfoBean(false, this.p);
        } else if (hiDeviceInfo.getDeviceType() == 32) {
            SourceInfoBean sourceInfoBean = new SourceInfoBean(true, deviceName);
            this.s = sourceInfoBean;
            sourceInfoBean.setDeviceName(deviceName);
            this.s.setSoftwareVersion(hiDeviceInfo.getSoftwareVersion());
            this.s.setManufacturer(hiDeviceInfo.getDeviceName());
        } else if (hiDeviceInfo.getDeviceType() == 30) {
            SourceInfoBean sourceInfoBean2 = new SourceInfoBean(true, deviceName);
            this.s = sourceInfoBean2;
            sourceInfoBean2.setDeviceName(deviceName);
            this.s.setSoftwareVersion(hiDeviceInfo.getSoftwareVersion());
            this.s.setManufacturer(hiDeviceInfo.getManufacturer());
        } else {
            SourceInfoBean sourceInfoBean3 = new SourceInfoBean(true, deviceName);
            this.s = sourceInfoBean3;
            sourceInfoBean3.setDeviceName(deviceName);
            this.s.setHardwareVersion(hiDeviceInfo.getHardwareVersion());
            this.s.setSoftwareVersion(hiDeviceInfo.getSoftwareVersion());
            this.s.setManufacturer(hiDeviceInfo.getManufacturer());
            this.s.setProduceType(hiDeviceInfo.getModel());
        }
        this.n.post(new rto(this));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(int i, long j, long j2) {
        if (i == 107) {
            this.q = 2006;
        } else if (i != 109) {
            return;
        } else {
            this.q = 2004;
        }
        HiDataReadOption hiDataReadOption = new HiDataReadOption();
        hiDataReadOption.setType(new int[]{this.q});
        if (i == 107) {
            hiDataReadOption.setType(new int[]{2006, DicDataTypeUtil.DataType.BLOOD_BEFORE_ACTIVITY.value()});
        }
        hiDataReadOption.setReadType(0);
        hiDataReadOption.setStartTime(j);
        hiDataReadOption.setEndTime(j2);
        hiDataReadOption.setSortOrder(1);
        HiHealthNativeApi.a(this).readHiHealthData(hiDataReadOption, new e(this));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dQN_(SparseArray<List<HiHealthData>> sparseArray) {
        if (sparseArray.size() <= 0) {
            LogUtil.h("PrivacyDetailActivity", "handleHiDataResult map.size() <= 0");
            return;
        }
        List<HiHealthData> list = sparseArray.get(this.q);
        if (!trg.d(list)) {
            long modifiedTime = list.get(0).getModifiedTime();
            this.m = modifiedTime;
            if (modifiedTime == 0) {
                this.m = list.get(0).getEndTime();
            }
            LogUtil.a("PrivacyDetailActivity", "handleHiDataResult mModifyTime is ", Long.valueOf(this.m));
        } else {
            LogUtil.h("PrivacyDetailActivity", "handleHiDataResult hiHealthDataList.size() <= 0");
        }
        if (this.k != 107) {
            this.n.post(new Runnable() { // from class: rtj
                @Override // java.lang.Runnable
                public final void run() {
                    PrivacyDetailActivity.this.y();
                }
            });
            return;
        }
        List<HiHealthData> list2 = sparseArray.get(DicDataTypeUtil.DataType.BLOOD_BEFORE_ACTIVITY.value());
        if (trg.d(list2)) {
            this.j = true;
            this.n.post(new Runnable() { // from class: rtj
                @Override // java.lang.Runnable
                public final void run() {
                    PrivacyDetailActivity.this.y();
                }
            });
            return;
        }
        HiHealthData hiHealthData = list2.get(0);
        List<qkv> b = BloodPressureUtil.b();
        int a2 = BloodPressureUtil.a(hiHealthData.getIntValue());
        BloodPressureUtil.d(a2, b);
        List list3 = (List) b.stream().filter(new Predicate() { // from class: rtp
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                boolean i;
                i = ((qkv) obj).i();
                return i;
            }
        }).map(new Function() { // from class: rts
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((qkv) obj).d();
            }
        }).collect(Collectors.toList());
        try {
            HiBloodPressureMetaData hiBloodPressureMetaData = (HiBloodPressureMetaData) HiJsonUtil.e(hiHealthData.getMetaData(), HiBloodPressureMetaData.class);
            if (hiBloodPressureMetaData != null && koq.c(hiBloodPressureMetaData.getActivityActions())) {
                list3.addAll(hiBloodPressureMetaData.getActivityActions());
            }
        } catch (Exception unused) {
            LogUtil.a("PrivacyDetailActivity", "turn json mHiHealthData.getMetaData() fail: ", "");
        }
        final StringBuilder sb = new StringBuilder();
        list3.forEach(new Consumer() { // from class: rtr
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                PrivacyDetailActivity.d(sb, (String) obj);
            }
        });
        this.c = a2 != 4096 ? sb.toString() : "";
        this.j = true;
        this.n.post(new Runnable() { // from class: rtj
            @Override // java.lang.Runnable
            public final void run() {
                PrivacyDetailActivity.this.y();
            }
        });
    }

    public static /* synthetic */ void d(StringBuilder sb, String str) {
        sb.append(sb.length() > 0 ? "、 " : "");
        sb.append(str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void y() {
        boolean z;
        if (koq.b(this.g)) {
            return;
        }
        Iterator<PrivacyDataModel> it = this.g.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            PrivacyDataModel next = it.next();
            if (this.t.equals(next.getDataTitle())) {
                next.setDataDesc(rsr.b(this.m));
                break;
            }
        }
        if (this.k != 107 || this.y == 2) {
            e(this.g);
            return;
        }
        String string = getResources().getString(R$string.IDS_blood_measure_action);
        Iterator<PrivacyDataModel> it2 = this.g.iterator();
        PrivacyDataModel privacyDataModel = null;
        while (true) {
            z = false;
            if (!it2.hasNext()) {
                break;
            }
            privacyDataModel = it2.next();
            if (string.equals(privacyDataModel.getDataTitle()) && !StringUtils.g(this.c)) {
                privacyDataModel.setDataDesc(this.c);
                break;
            } else if (string.equals(privacyDataModel.getDataTitle()) && StringUtils.g(this.c)) {
                z = true;
                break;
            }
        }
        if (z && privacyDataModel != null) {
            this.g.remove(privacyDataModel);
        }
        e(this.g);
    }

    private void r() {
        String deviceName = this.l.getDeviceName();
        this.p = deviceName;
        LogUtil.a("PrivacyDetailActivity", "setUnknownSourceData getDeviceName is ", deviceName);
        if (TextUtils.isEmpty(this.p)) {
            this.p = getResources().getString(R$string.IDS_hw_data_origin_unknow_device);
        }
        this.s = new SourceInfoBean(false, this.p);
        this.n.post(new rto(this));
    }

    private void e(SourceInfoBean sourceInfoBean) {
        int i = this.k;
        if (i == 103) {
            n();
            return;
        }
        if (i == 105) {
            l();
        } else if (i == 107) {
            i();
        } else {
            m();
        }
    }

    private void n() {
        if (this.l.getType() == 22107 || this.l.getType() == 22106) {
            d();
            f();
            j();
            return;
        }
        m();
    }

    private void l() {
        if (this.y == 3) {
            h();
            g();
            b();
            f();
            j();
            return;
        }
        m();
    }

    private void i() {
        if (this.y != 2) {
            PrivacyDataModel privacyDataModel = new PrivacyDataModel(rsr.d(this.k), rsr.c(this.k, this.l));
            privacyDataModel.putInt(BleConstants.BLOODPRESSURE_SYSTOLIC, this.l.getInt(BleConstants.BLOODPRESSURE_SYSTOLIC));
            privacyDataModel.putInt(BleConstants.BLOODPRESSURE_DIASTOLIC, this.l.getInt(BleConstants.BLOODPRESSURE_DIASTOLIC));
            this.g.add(privacyDataModel);
            this.g.add(new PrivacyDataModel(getResources().getString(R$string.IDS_hw_health_show_pulse_heart_bmp), rre.c(this.l.getInt(BleConstants.BLOODPRESSURE_SPHYGMUS))));
            e();
            g();
            b();
            f();
            j();
            int i = this.l.getInt("measureAbnormal");
            if (i == 2 || i == 3 || i == 4 || i == 5 || i == 9 || i == 10) {
                PrivacyDataModel privacyDataModel2 = new PrivacyDataModel(getResources().getString(R$string.IDS_privacy_measure_abnormal), rju.c.b(i));
                privacyDataModel2.putInt("measureAbnormal", i);
                this.g.add(privacyDataModel2);
                return;
            }
            return;
        }
        m();
    }

    private void m() {
        c();
        g();
        b();
        f();
        j();
    }

    private void c() {
        this.g.add(new PrivacyDataModel(rsr.d(this.k), this.y == 2 ? this.l.getDataTitle() : rsr.c(this.k, this.l)));
    }

    private void g() {
        this.g.add(new PrivacyDataModel(getResources().getString(R$string.IDS_settings_seat_long_starttime), rsr.b(this.l.getStartTime())));
    }

    private void b() {
        this.g.add(new PrivacyDataModel(getResources().getString(R$string.IDS_settings_seat_long_endtime), rsr.b(this.l.getEndTime())));
    }

    private void f() {
        String str = this.t;
        long j = this.m;
        if (j == 0) {
            j = this.l.getModifyTime();
        }
        if (j == 0) {
            j = this.l.getEndTime();
        }
        this.g.add(new PrivacyDataModel(str, rsr.b(j)));
    }

    private void j() {
        String string = getResources().getString(R$string.IDS_privacy_source);
        String source = this.s.getSource();
        if (TextUtils.isEmpty(source)) {
            source = getResources().getString(R$string.IDS_hw_data_origin_unknow_device);
        }
        this.g.add(new PrivacyDataModel(string, source));
    }

    private void e() {
        if (this.j && StringUtils.g(this.c)) {
            return;
        }
        this.g.add(new PrivacyDataModel(getResources().getString(R$string.IDS_blood_measure_action), this.c));
    }

    private void d() {
        this.g.add(new PrivacyDataModel(getResources().getString(R$string.IDS_manual_sleep_bed_time), this.l.getLong("bedStartTime") == Long.MIN_VALUE ? "--" : rrb.a(this.l.getLong("bedEndTime") - this.l.getLong("bedStartTime"), 0L)));
        this.g.add(new PrivacyDataModel(getResources().getString(R$string.IDS_manual_sleep_sleep_time), this.l.getLong("sleepStartTime") == Long.MIN_VALUE ? "--" : rrb.a(this.l.getLong("sleepEndTime") - this.l.getLong("sleepStartTime"), 0L)));
        String string = getResources().getString(R$string.IDS_get_in_bed_time_label);
        long j = this.l.getLong("bedStartTime");
        this.g.add(new PrivacyDataModel(string, j == Long.MIN_VALUE ? "--" : rsr.b(j)));
        String string2 = getResources().getString(R$string.IDS_fitness_core_sleep_go_to_sleep_time);
        long j2 = this.l.getLong("sleepStartTime");
        this.g.add(new PrivacyDataModel(string2, j2 == Long.MIN_VALUE ? "--" : rsr.b(j2)));
        String string3 = getResources().getString(R$string.IDS_manual_sleep_waking_time);
        long j3 = this.l.getLong("sleepEndTime");
        this.g.add(new PrivacyDataModel(string3, j3 == Long.MIN_VALUE ? "--" : rsr.b(j3)));
        String string4 = getResources().getString(R$string.IDS_getup_time_label);
        long j4 = this.l.getLong("bedEndTime");
        this.g.add(new PrivacyDataModel(string4, j4 != Long.MIN_VALUE ? rsr.b(j4) : "--"));
    }

    private void h() {
        int i = this.l.getInt("lakeLouiseScoreKey");
        if (i != Integer.MIN_VALUE) {
            this.g.add(new PrivacyDataModel(getString(R$string.IDS_privacy_highland_risk), String.valueOf(i)));
        }
        this.g.add(new PrivacyDataModel(getString(R$string.IDS_hw_show_main_permission_blood_oxygen), String.valueOf(this.l.getInt("bloodOxygenKey"))));
        if ("true".equals(SharedPreferenceManager.b(getApplicationContext(), String.valueOf(10000), "BLOOD_OXYGEN_SWITCH_IS_VISIBLE"))) {
            int i2 = this.l.getInt("altitudeKey");
            if (i2 == Integer.MIN_VALUE) {
                this.g.add(new PrivacyDataModel(getString(R$string.IDS_hw_health_blood_oxygen_elevation), "--"));
            } else {
                this.g.add(new PrivacyDataModel(getString(R$string.IDS_hw_health_blood_oxygen_elevation), rre.e(i2)));
            }
        }
    }

    private List<PrivacyDataModel> c(SourceInfoBean sourceInfoBean) {
        ArrayList arrayList = new ArrayList();
        Resources resources = getResources();
        String string = resources.getString(R$string.IDS_privacy_device_name);
        String deviceName = sourceInfoBean.getDeviceName();
        if (TextUtils.isEmpty(deviceName)) {
            deviceName = getResources().getString(R$string.IDS_hw_data_origin_unknow_device);
        }
        arrayList.add(new PrivacyDataModel(string, deviceName));
        d(arrayList, sourceInfoBean);
        String string2 = resources.getString(R$string.IDS_privacy_device_type);
        String produceType = sourceInfoBean.getProduceType();
        if (!TextUtils.isEmpty(produceType)) {
            arrayList.add(new PrivacyDataModel(string2, produceType));
        }
        String string3 = resources.getString(R$string.IDS_device_rope_info_hardware_version);
        String hardwareVersion = sourceInfoBean.getHardwareVersion();
        if (!TextUtils.isEmpty(hardwareVersion)) {
            arrayList.add(new PrivacyDataModel(string3, hardwareVersion));
        }
        String string4 = resources.getString(R$string.IDS_device_rope_info_software_version);
        String softwareVersion = sourceInfoBean.getSoftwareVersion();
        if (!TextUtils.isEmpty(softwareVersion)) {
            arrayList.add(new PrivacyDataModel(string4, softwareVersion));
        }
        return arrayList;
    }

    private void d(List<PrivacyDataModel> list, SourceInfoBean sourceInfoBean) {
        String string = getResources().getString(R$string.IDS_privacy_device_manufacturer);
        String manufacturer = sourceInfoBean.getManufacturer();
        if (manufacturer != null && Pattern.matches("[0-9]{3}", manufacturer)) {
            manufacturer = f10435a.get(manufacturer);
        }
        if (TextUtils.isEmpty(manufacturer)) {
            return;
        }
        list.add(new PrivacyDataModel(string, manufacturer));
    }

    private HealthSubHeader b(int i, int i2) {
        return (HealthSubHeader) nsy.cMc_(this, i).findViewById(i2);
    }

    private void e(List<PrivacyDataModel> list) {
        b(this.b, this.d, list);
    }

    private void c(List<PrivacyDataModel> list) {
        b(this.i, this.e, list);
    }

    private void b(HealthRecycleView healthRecycleView, DataSourceViewAdapter dataSourceViewAdapter, List<PrivacyDataModel> list) {
        healthRecycleView.setLayoutManager(new LinearLayoutManager(this) { // from class: com.huawei.ui.main.stories.privacy.template.view.showdata.PrivacyDetailActivity.1
            @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.LayoutManager
            public boolean canScrollVertically() {
                return false;
            }
        });
        healthRecycleView.setAdapter(dataSourceViewAdapter);
        dataSourceViewAdapter.b(list);
    }

    static class a implements HiDataClientListener {

        /* renamed from: a, reason: collision with root package name */
        WeakReference<PrivacyDetailActivity> f10436a;

        a(PrivacyDetailActivity privacyDetailActivity) {
            this.f10436a = new WeakReference<>(privacyDetailActivity);
        }

        @Override // com.huawei.hihealth.data.listener.HiDataClientListener
        public void onResult(List<HiHealthClient> list) {
            WeakReference<PrivacyDetailActivity> weakReference = this.f10436a;
            if (weakReference == null) {
                return;
            }
            PrivacyDetailActivity privacyDetailActivity = weakReference.get();
            if (privacyDetailActivity != null) {
                privacyDetailActivity.d(list);
            } else {
                LogUtil.a("PrivacyDetailActivity", "ReadDataSourceCallBack detailActivity is null");
            }
        }
    }

    static class e implements HiDataReadResultListener {
        WeakReference<PrivacyDetailActivity> c;

        @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
        public void onResultIntent(int i, Object obj, int i2, int i3) {
        }

        e(PrivacyDetailActivity privacyDetailActivity) {
            this.c = new WeakReference<>(privacyDetailActivity);
        }

        @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
        public void onResult(Object obj, int i, int i2) {
            WeakReference<PrivacyDetailActivity> weakReference = this.c;
            if (weakReference == null) {
                return;
            }
            if (!(obj instanceof SparseArray)) {
                LogUtil.h("PrivacyDetailActivity", "readHiHealthDataModifyTime data null");
                return;
            }
            SparseArray sparseArray = (SparseArray) obj;
            PrivacyDetailActivity privacyDetailActivity = weakReference.get();
            if (privacyDetailActivity != null) {
                privacyDetailActivity.dQN_(sparseArray);
            } else {
                LogUtil.a("PrivacyDetailActivity", "ReadHiDataCallBack detailActivity is null");
            }
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
