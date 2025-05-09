package com.huawei.ui.device.activity.adddevice;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.widget.ImageView;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.health.R;
import com.huawei.health.devicemgr.api.mainprocess.DownloadManagerApi;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.health.ecologydevice.ui.measure.fragment.device.DeviceCategoryFragment;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwdevice.outofprocess.util.NotificationContentProviderUtil;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.hwversionmgr.manager.HwVersionManager;
import com.huawei.nfc.PluginPayAdapter;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.button.HealthButton;
import com.huawei.ui.commonui.dialog.CustomTextAlertDialog;
import com.huawei.ui.commonui.dialog.NoTitleCustomAlertDialog;
import com.huawei.ui.commonui.dotspageindicator.HealthDotsPageIndicator;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.titlebar.CustomTitleBar;
import com.huawei.ui.commonui.viewpager.HealthPagerAdapter;
import com.huawei.ui.commonui.viewpager.HealthViewPager;
import com.huawei.ui.device.interactors.CompatibilityInteractor;
import com.huawei.ui.main.R$string;
import defpackage.cpp;
import defpackage.cuw;
import defpackage.cvc;
import defpackage.cvt;
import defpackage.dwo;
import defpackage.ixx;
import defpackage.jfu;
import defpackage.jjb;
import defpackage.jph;
import defpackage.jpt;
import defpackage.nkx;
import defpackage.nrr;
import defpackage.nsn;
import defpackage.nsy;
import defpackage.oae;
import defpackage.oaf;
import health.compact.a.CommonUtil;
import health.compact.a.LanguageUtil;
import health.compact.a.Services;
import health.compact.a.Utils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

/* loaded from: classes6.dex */
public class AddDeviceIntroActivity extends BaseActivity implements View.OnClickListener {

    /* renamed from: a, reason: collision with root package name */
    private Context f9005a;
    private CustomTitleBar c;
    private Context d;
    private HealthButton e;
    private HealthDotsPageIndicator f;
    private b j;
    private HealthButton q;
    private HealthTextView u;
    private HealthViewPager v;
    private ArrayList<String> y = new ArrayList<>(16);
    private ArrayList<String> s = new ArrayList<>(16);
    private ArrayList<Integer> h = new ArrayList<>(16);
    private ArrayList<Integer> p = new ArrayList<>(16);
    private int g = -1;
    private int x = 0;
    private String i = "";
    private String b = "";
    private boolean k = false;
    private boolean l = false;
    private int n = 0;
    private boolean o = true;
    private cvc r = null;
    private ArrayList<Bitmap> t = new ArrayList<>(16);
    private ArrayList<Bitmap> w = new ArrayList<>(16);
    private HealthViewPager.OnPageChangeListener m = new HealthViewPager.OnPageChangeListener() { // from class: com.huawei.ui.device.activity.adddevice.AddDeviceIntroActivity.4
        @Override // com.huawei.uikit.hwviewpager.widget.HwViewPager.OnPageChangeListener
        public void onPageSelected(int i) {
            LogUtil.a("AddDeviceIntroActivity", "ProductIntroductionFragment onPageSelected() position ", Integer.valueOf(i));
            AddDeviceIntroActivity.this.a(i);
            AddDeviceIntroActivity.this.n = i;
        }

        @Override // com.huawei.uikit.hwviewpager.widget.HwViewPager.OnPageChangeListener
        public void onPageScrolled(int i, float f, int i2) {
            LogUtil.a("AddDeviceIntroActivity", "ProductIntroductionFragment onPageScrolled() position ", Integer.valueOf(i), " positionOffset ", Float.valueOf(f), " positionOffsetPixels ", Integer.valueOf(i2));
        }

        @Override // com.huawei.uikit.hwviewpager.widget.HwViewPager.OnPageChangeListener
        public void onPageScrollStateChanged(int i) {
            LogUtil.a("AddDeviceIntroActivity", "ProductIntroductionFragment onPageScrollStateChanged() state ", Integer.valueOf(i));
        }
    };

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.f9005a = BaseApplication.getContext();
        this.d = this;
        LogUtil.a("AddDeviceIntroActivity", "onCreate()");
        f();
        d();
        l();
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity
    public void initViewTahiti() {
        f();
        d();
        l();
    }

    private void l() {
        int i = this.x;
        if (i == 1 || i == 2) {
            jph.bIM_(this, -1);
        }
    }

    private void f() {
        setContentView(R.layout.activity_device_introduction_layout);
        this.c = (CustomTitleBar) nsy.cMc_(this, R.id.device_introduction_detail_title_bar);
        this.v = (HealthViewPager) nsy.cMc_(this, R.id.device_introduction_device_img);
        if (nsn.ag(this.f9005a)) {
            ViewGroup.LayoutParams layoutParams = this.v.getLayoutParams();
            layoutParams.width = nsn.c(this.f9005a, 320.0f);
            this.v.setLayoutParams(layoutParams);
        }
        this.u = (HealthTextView) nsy.cMc_(this, R.id.device_introduction_prompt);
        this.f = (HealthDotsPageIndicator) nsy.cMc_(this, R.id.indicator);
        this.e = (HealthButton) nsy.cMc_(this, R.id.device_introduction_buy_device);
        HealthButton healthButton = (HealthButton) nsy.cMc_(this, R.id.device_introduction_guide_next);
        this.q = healthButton;
        healthButton.setText(getString(R$string.IDS_device_start_paring_title).toUpperCase(Locale.getDefault()));
        c(this.q);
        c(this.e);
        Context context = this.d;
        BaseActivity baseActivity = context instanceof BaseActivity ? (BaseActivity) context : null;
        if (baseActivity != null) {
            this.e.setOnClickListener(nkx.cwZ_(this, baseActivity, true, ""));
            this.q.setOnClickListener(nkx.cwZ_(this, baseActivity, true, ""));
        }
    }

    private void c(final HealthButton healthButton) {
        final int[] iArr = {15};
        for (int i = 0; i < 6; i++) {
            healthButton.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() { // from class: com.huawei.ui.device.activity.adddevice.AddDeviceIntroActivity.3
                @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
                public void onGlobalLayout() {
                    HealthButton healthButton2 = healthButton;
                    if (healthButton2 == null) {
                        LogUtil.a("AddDeviceIntroActivity", "onGlobalLayout button is null");
                        return;
                    }
                    if (healthButton2.getLineCount() > 1) {
                        WindowManager windowManager = AddDeviceIntroActivity.this.getSystemService(Constants.NATIVE_WINDOW_SUB_DIR) instanceof WindowManager ? (WindowManager) AddDeviceIntroActivity.this.getSystemService(Constants.NATIVE_WINDOW_SUB_DIR) : null;
                        if (windowManager != null) {
                            int width = windowManager.getDefaultDisplay().getWidth();
                            ViewGroup.LayoutParams layoutParams = healthButton.getLayoutParams();
                            if (width == layoutParams.width) {
                                int[] iArr2 = iArr;
                                int i2 = iArr2[0] - 1;
                                iArr2[0] = i2;
                                healthButton.setTextSize(1, i2);
                            } else {
                                layoutParams.width = width - nrr.e(AddDeviceIntroActivity.this, 64.0f);
                                healthButton.setLayoutParams(layoutParams);
                            }
                        }
                    } else {
                        healthButton.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                    }
                    LogUtil.a("AddDeviceIntroActivity", "globalLayout");
                }
            });
        }
    }

    private void d() {
        cuw c;
        Intent intent = getIntent();
        if (intent != null) {
            try {
                this.k = intent.getBooleanExtra("isFromWear", false);
                this.g = intent.getIntExtra(DeviceCategoryFragment.DEVICE_TYPE, -1);
                if (intent.getBooleanExtra("isPorc", false)) {
                    LogUtil.a("AddDeviceIntroActivity", "initData() device is porc!");
                    c = jfu.h();
                    this.l = true;
                } else if (intent.getBooleanExtra("isR1pro", false)) {
                    c = jfu.j();
                    this.i = c.f();
                } else {
                    c = jfu.c(this.g);
                    this.i = intent.getStringExtra("dname");
                }
                this.x = intent.getIntExtra("viewStyle", 1);
                LogUtil.a("AddDeviceIntroActivity", "initData() mDeviceType :", Integer.valueOf(this.g), "mDeviceName：", this.i);
                this.c.setTitleText(c.f());
                this.h = c.k();
                this.s = c.j();
                this.b = c.b();
            } catch (Exception unused) {
                LogUtil.b("AddDeviceIntroActivity", "AddDeviceIntroActivity encounteredClassNotFoundException");
            }
        }
        LogUtil.a("AddDeviceIntroActivity", "initData() mBuyUrl :", this.b);
        h();
        b();
    }

    private void h() {
        if (jfu.m(this.g)) {
            LogUtil.a("AddDeviceIntroActivity", "initData() device is plugin downloaded!");
            this.r = ((DownloadManagerApi) Services.c("DownloadDeviceResource", DownloadManagerApi.class)).getPluginInfoByUuid(jfu.j(this.g));
            this.t.clear();
            this.w.clear();
            cvc cvcVar = this.r;
            if (cvcVar != null && cvcVar.f() != null && this.r.f().aa() != null) {
                for (int i = 0; i < this.r.f().aa().size(); i++) {
                    DownloadManagerApi downloadManagerApi = (DownloadManagerApi) Services.c("DownloadDeviceResource", DownloadManagerApi.class);
                    cvc cvcVar2 = this.r;
                    this.t.add(downloadManagerApi.loadImageByImageName(cvcVar2, cvcVar2.f().aa().get(i)));
                }
                LogUtil.a("AddDeviceIntroActivity", "initData() device is plugin downloaded! image size:", Integer.valueOf(this.t.size()));
                String g = this.r.f().g();
                this.b = g;
                LogUtil.a("AddDeviceIntroActivity", "initData() device is plugin downloaded! mBuyUrl:", g);
            }
            if (LanguageUtil.bc(this)) {
                for (int size = this.t.size() - 1; size >= 0; size--) {
                    this.w.add(this.t.get(size));
                }
            } else {
                this.w.addAll(this.t);
            }
            this.j = new b(this.w, true);
            return;
        }
        this.p.clear();
        if (LanguageUtil.bc(this)) {
            for (int size2 = this.h.size() - 1; size2 >= 0; size2--) {
                this.p.add(this.h.get(size2));
            }
        } else {
            this.p.addAll(this.h);
        }
        this.j = new b(this.p);
    }

    private void b() {
        this.v.setAdapter(this.j);
        this.f.setRtlEnable(true);
        this.f.setViewPager(this.v);
        this.v.setOnPageChangeListener(this.m);
        this.y.clear();
        if (LanguageUtil.bc(this)) {
            for (int size = this.s.size() - 1; size >= 0; size--) {
                this.y.add(this.s.get(size));
            }
        } else {
            this.y.addAll(this.s);
        }
        LogUtil.a("AddDeviceIntroActivity", "initData() mTextList :", Integer.valueOf(this.y.size()));
        if (this.y.size() > 0) {
            if (this.o) {
                if (LanguageUtil.bc(this)) {
                    this.n = this.y.size() - 1;
                } else {
                    this.n = 0;
                }
                this.o = false;
            }
            if (this.n < this.y.size()) {
                this.u.setText(this.y.get(this.n));
            }
        }
        j();
    }

    private void j() {
        if (Utils.o() || CommonUtil.bf()) {
            this.e.setVisibility(8);
            return;
        }
        int i = this.g;
        if (i == 23 || i == 24) {
            LogUtil.h("AddDeviceIntroActivity", "setBuyButton HONOR_AW70, HUAWEI_AW70");
            this.e.setVisibility(8);
        } else {
            c(this.b);
        }
    }

    private void c(String str) {
        this.b = str;
        if (TextUtils.isEmpty(str)) {
            this.e.setVisibility(8);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(int i) {
        if (i >= 0) {
            try {
                if (i < this.y.size()) {
                    this.u.setText(this.y.get(i));
                    return;
                }
            } catch (IndexOutOfBoundsException unused) {
                LogUtil.a("AddDeviceIntroActivity", "IndexOutOfBoundsException arg0 ", Integer.valueOf(i), ",mTextList.size() ", Integer.valueOf(this.y.size()));
                return;
            }
        }
        LogUtil.a("AddDeviceIntroActivity", "error index ", Integer.valueOf(i));
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view == null) {
            LogUtil.a("AddDeviceIntroActivity", "onClick view is null");
            ViewClickInstrumentation.clickOnView(view);
            return;
        }
        if (R.id.device_introduction_guide_next == view.getId()) {
            if (!nsn.o()) {
                DeviceInfo d = jpt.d("AddDeviceIntroActivity");
                if (d != null && HwVersionManager.c(BaseApplication.getContext()).o(d.getDeviceIdentify())) {
                    LogUtil.a("AddDeviceIntroActivity", "wear device OTA is in progress");
                    i();
                } else {
                    e();
                }
            } else {
                LogUtil.a("AddDeviceIntroActivity", "click too fast");
            }
        } else if (R.id.device_introduction_buy_device == view.getId()) {
            if (!TextUtils.isEmpty(this.b)) {
                HashMap hashMap = new HashMap(16);
                hashMap.put("click", "1");
                hashMap.put(PluginPayAdapter.KEY_DEVICE_INFO_NAME, jfu.c(this.g, this.i, this.l));
                hashMap.put(DeviceCategoryFragment.DEVICE_TYPE, "HDK_WEAR");
                ixx.d().d(cpp.a(), AnalyticsValue.HEALTH_PLUGIN_DEVICE_BUY_PRODUCT_2060003.value(), hashMap, 0);
                Intent intent = new Intent();
                intent.setPackage(this.f9005a.getPackageName());
                intent.setClassName(this.f9005a.getPackageName(), "com.huawei.operation.activity.WebViewActivity");
                intent.putExtra("url", this.b);
                intent.putExtra("EXTRA_BI_ID", "");
                intent.putExtra("EXTRA_BI_NAME", "");
                intent.putExtra("EXTRA_BI_SOURCE", "Device");
                startActivity(intent);
            }
        } else {
            LogUtil.a("AddDeviceIntroActivity", "unknown id");
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    private void e() {
        LogUtil.a("AddDeviceIntroActivity", "enter handleDialogByConnectedDevice");
        List<DeviceInfo> c = oae.c(BaseApplication.getContext()).c();
        if (c == null || c.isEmpty()) {
            LogUtil.a("AddDeviceIntroActivity", "onClick connectedDeviceInfo is null");
            a();
        } else if (c.size() == 1) {
            e(c);
        } else if (c.size() >= 2) {
            a(c);
        } else {
            LogUtil.a("AddDeviceIntroActivity", "more devices");
        }
    }

    private void a(List<DeviceInfo> list) {
        if (cvt.c(this.g)) {
            for (DeviceInfo deviceInfo : list) {
                if (cvt.c(deviceInfo.getProductType())) {
                    LogUtil.a("AddDeviceIntroActivity", "caseForTwoMoreConnectedDevices aw70 device");
                    c(this.g, this.i, deviceInfo);
                    return;
                }
            }
            return;
        }
        for (DeviceInfo deviceInfo2 : list) {
            if (!cvt.a(deviceInfo2.getProductType(), deviceInfo2.getAutoDetectSwitchStatus())) {
                LogUtil.a("AddDeviceIntroActivity", "onClick user has connect other device, and also wants to connect other device");
                c(this.g, this.i, deviceInfo2);
                return;
            }
        }
    }

    private void e(List<DeviceInfo> list) {
        int i;
        LogUtil.a("AddDeviceIntroActivity", "onClick has one connected device");
        DeviceInfo deviceInfo = list.get(0);
        if (deviceInfo == null) {
            LogUtil.b("AddDeviceIntroActivity", "onClick connected is null");
            return;
        }
        if (cvt.c(deviceInfo.getProductType())) {
            if (cvt.c(this.g)) {
                LogUtil.a("AddDeviceIntroActivity", "onClick user has connect aw70, and also wants to connect aw70 device");
                c(this.g, this.i, deviceInfo);
                return;
            } else if (deviceInfo.getAutoDetectSwitchStatus() == 0) {
                LogUtil.a("AddDeviceIntroActivity", "onClick user has connect aw70 band mode, and also wants to connect other device");
                c(this.g, this.i, deviceInfo);
                return;
            } else if (deviceInfo.getAutoDetectSwitchStatus() == 1) {
                LogUtil.a("AddDeviceIntroActivity", "onClick user has connect aw70 run mode, and also wants to connect other device");
                a();
                return;
            } else {
                LogUtil.a("AddDeviceIntroActivity", "onClick user has connect aw70 unknown mode, and also wants to connect other device");
                return;
            }
        }
        if (cvt.c(this.g)) {
            LogUtil.a("AddDeviceIntroActivity", "onClick user has connect other device, and also wants to connect aw70 device");
            a();
            return;
        }
        LogUtil.a("AddDeviceIntroActivity", "onClick user has connect other device, and also wants to connect other device");
        int i2 = this.g;
        if (((i2 == 3 || i2 == 10) && deviceInfo.getDeviceIdentify().equalsIgnoreCase("AndroidWear")) || (i = this.g) == 2 || i == 9 || i == -3) {
            a();
        } else {
            c(i, this.i, deviceInfo);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void g() {
        LogUtil.a("AddDeviceIntroActivity", "makeResult mIsFromWear ", Boolean.valueOf(this.k));
        if (this.k) {
            try {
                Intent intent = new Intent();
                intent.putExtra(DeviceCategoryFragment.DEVICE_TYPE, this.g);
                intent.putExtra("isFromWear", this.k);
                LogUtil.a("AddDeviceIntroActivity", "onClick device_type:", Integer.valueOf(this.g));
                intent.setClass(this.d, AddDeviceChildActivity.class);
                this.d.startActivity(intent);
            } catch (ActivityNotFoundException e) {
                LogUtil.b("AddDeviceIntroActivity", "ActivityNotFoundException e:", e.getMessage());
            }
            finish();
            return;
        }
        Intent intent2 = new Intent();
        intent2.putExtra(DeviceCategoryFragment.DEVICE_TYPE, this.g);
        LogUtil.a("AddDeviceIntroActivity", "onClick device_type:", Integer.valueOf(this.g));
        setResult(101, intent2);
        finish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a() {
        if (!NotificationContentProviderUtil.e()) {
            LogUtil.a("AddDeviceIntroActivity", "gotoConnect NOTIFICATION_INITIALIZE");
            if (jjb.b().c()) {
                NotificationContentProviderUtil.a(1);
            } else {
                NotificationContentProviderUtil.a(0);
            }
        }
        c();
    }

    private void c() {
        CompatibilityInteractor compatibilityInteractor = new CompatibilityInteractor();
        if (compatibilityInteractor.a(BaseApplication.getContext())) {
            g();
            return;
        }
        List<DeviceInfo> h = dwo.d().h();
        if (h == null) {
            g();
            return;
        }
        if (h.size() > 0 && CompatibilityInteractor.c(BaseApplication.getContext())) {
            if (compatibilityInteractor.d(h)) {
                d(compatibilityInteractor, h);
                return;
            } else {
                g();
                return;
            }
        }
        g();
    }

    private void d(final CompatibilityInteractor compatibilityInteractor, List<DeviceInfo> list) {
        if (compatibilityInteractor.a(list) == null) {
            g();
        } else {
            compatibilityInteractor.a(this.d, (DeviceInfo) null, new IBaseResponseCallback() { // from class: com.huawei.ui.device.activity.adddevice.AddDeviceIntroActivity.5
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public void d(int i, Object obj) {
                    if (i != 0) {
                        AddDeviceIntroActivity.this.g();
                    } else {
                        if (obj == null || !(obj instanceof String) || compatibilityInteractor == null || !"SURE".equals((String) obj)) {
                            return;
                        }
                        CommonUtil.ak(BaseApplication.getContext());
                    }
                }
            });
        }
    }

    private void a(final String str, String str2) {
        CustomTextAlertDialog a2 = new CustomTextAlertDialog.Builder(this).b(R.string.IDS_device_replace_dialog_title_notification).e(str2).cyR_(R.string._2130841130_res_0x7f020e2a, new View.OnClickListener() { // from class: com.huawei.ui.device.activity.adddevice.AddDeviceIntroActivity.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LogUtil.a("AddDeviceIntroActivity", "showReplaceDeviceDialog():Click not to switch the device");
                ViewClickInstrumentation.clickOnView(view);
            }
        }).cyU_(R.string._2130841131_res_0x7f020e2b, new View.OnClickListener() { // from class: com.huawei.ui.device.activity.adddevice.AddDeviceIntroActivity.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LogUtil.a("AddDeviceIntroActivity", "showReplaceDeviceDialog():Click to agree to switch the device");
                AddDeviceIntroActivity.this.a();
                oaf.b(AddDeviceIntroActivity.this.f9005a).h(str);
                LogUtil.a("AddDeviceIntroActivity", "Clear the upgrade inter data");
                ViewClickInstrumentation.clickOnView(view);
            }
        }).a();
        a2.setCancelable(false);
        a2.show();
    }

    private void c(int i, String str, DeviceInfo deviceInfo) {
        oae c = oae.c(BaseApplication.getContext());
        int productType = deviceInfo.getProductType();
        String deviceName = deviceInfo.getDeviceName();
        String b2 = c.b(productType);
        if (i == 11 && "HUAWEI CM-R1P".equals(deviceName)) {
            b2 = this.f9005a.getString(R.string._2130849807_res_0x7f02300f);
        }
        String c2 = c.c(i);
        LogUtil.a("AddDeviceIntroActivity", "new device ", c2, ", old device ", b2);
        if (i == 11 && ("HUAWEI CM-R1P".equals(str) || this.f9005a.getString(R.string._2130849807_res_0x7f02300f).equals(str) || this.f9005a.getString(R.string.IDS_device_r1_pro_name_title).equals(str))) {
            c2 = this.f9005a.getString(R.string._2130849807_res_0x7f02300f);
        }
        if (deviceName == null || !TextUtils.isEmpty(b2) || !CommonUtil.bu()) {
            deviceName = b2;
        }
        a(deviceInfo.getDeviceIdentify(), String.format(Locale.ENGLISH, getResources().getString(R.string.IDS_replace_device_dialog_content), deviceName, c2));
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        LogUtil.a("AddDeviceIntroActivity", "onDestroy");
    }

    static class b extends HealthPagerAdapter {
        private List<Integer> b;
        private List<Bitmap> d;
        private boolean e;

        @Override // com.huawei.uikit.hwviewpager.widget.HwPagerAdapter
        public boolean isViewFromObject(View view, Object obj) {
            return view == obj;
        }

        b(List<Integer> list) {
            this.e = false;
            this.b = list;
        }

        b(List<Bitmap> list, boolean z) {
            this.d = list;
            this.e = z;
        }

        @Override // com.huawei.uikit.hwviewpager.widget.HwPagerAdapter
        public int getCount() {
            if (this.e) {
                List<Bitmap> list = this.d;
                if (list != null) {
                    return list.size();
                }
                return 0;
            }
            List<Integer> list2 = this.b;
            if (list2 != null) {
                return list2.size();
            }
            return 0;
        }

        @Override // com.huawei.uikit.hwviewpager.widget.HwPagerAdapter
        public Object instantiateItem(ViewGroup viewGroup, int i) {
            ImageView imageView = new ImageView(BaseApplication.getContext());
            if (this.e) {
                List<Bitmap> list = this.d;
                if (list != null) {
                    imageView.setImageBitmap(list.get(i));
                }
            } else {
                List<Integer> list2 = this.b;
                if (list2 != null) {
                    imageView.setImageResource(list2.get(i).intValue());
                }
            }
            imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
            if (viewGroup instanceof HealthViewPager) {
                ((HealthViewPager) viewGroup).addView(imageView, 0);
            }
            return imageView;
        }

        @Override // com.huawei.uikit.hwviewpager.widget.HwPagerAdapter
        public void destroyItem(ViewGroup viewGroup, int i, Object obj) {
            if (obj != null && (obj instanceof ImageView)) {
                ImageView imageView = (ImageView) obj;
                if (viewGroup == null || imageView.getDrawable() == null) {
                    return;
                }
                viewGroup.removeView(imageView);
            }
        }
    }

    private void i() {
        NoTitleCustomAlertDialog e = new NoTitleCustomAlertDialog.Builder(this).e(this.f9005a.getResources().getString(R.string.IDS_main_device_ota_error_message)).czC_(R.string._2130841554_res_0x7f020fd2, new View.OnClickListener() { // from class: com.huawei.ui.device.activity.adddevice.AddDeviceIntroActivity.6
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LogUtil.a("AddDeviceIntroActivity", "showTipDialog,click known button");
                ViewClickInstrumentation.clickOnView(view);
            }
        }).e();
        e.setCancelable(false);
        e.show();
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
