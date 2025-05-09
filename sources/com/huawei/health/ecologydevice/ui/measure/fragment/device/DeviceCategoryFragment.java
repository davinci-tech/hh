package com.huawei.health.ecologydevice.ui.measure.fragment.device;

import android.bluetooth.BluetoothAdapter;
import android.content.ActivityNotFoundException;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import androidx.fragment.app.Fragment;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.R;
import com.huawei.health.device.api.DeviceInfoUtilsApi;
import com.huawei.health.device.api.DeviceMainActivityApi;
import com.huawei.health.device.api.HonourDeviceConstantsApi;
import com.huawei.health.device.constants.WeightConstants;
import com.huawei.health.device.model.HealthDevice;
import com.huawei.health.ecologydevice.manager.ResourceManager;
import com.huawei.health.ecologydevice.ui.BaseFragment;
import com.huawei.health.ecologydevice.ui.measure.adapter.ProductListAdapter;
import com.huawei.health.ecologydevice.ui.measure.adapter.WeightListAdapter;
import com.huawei.health.ecologydevice.ui.measure.fragment.ProductIntroductionFragment;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hms.support.api.entity.common.CommonConstant;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.util.SharedPreferenceUtil;
import com.huawei.ui.commonui.dialog.NoTitleCustomAlertDialog;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import defpackage.cjv;
import defpackage.cpp;
import defpackage.dcq;
import defpackage.dcz;
import defpackage.dka;
import defpackage.dkc;
import defpackage.dks;
import defpackage.gmz;
import defpackage.ixx;
import defpackage.jah;
import defpackage.jdm;
import defpackage.jdp;
import defpackage.nrh;
import health.compact.a.CommonUtil;
import health.compact.a.GRSManager;
import health.compact.a.Services;
import health.compact.a.Utils;
import health.compact.a.utils.StringUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;

/* loaded from: classes3.dex */
public class DeviceCategoryFragment extends BaseFragment {
    private static final String AM16_PRODUCTID = "6d5416d9-2167-41df-ab10-c492e152b44f";
    private static final String APP_GALLERY_HEALTH_URL = "/uowap/index.html#/detailApp/C10414141";
    private static final String BIND_ON_H5_PAGE_FLAG = "H5";
    private static final String CLASS_NAME_HEALTH_KIT_DETAIL = "com.huawei.ui.thirdpartservice.activity.healthkit.HealthKitActivity";
    private static final int DELAY_TIME = 500;
    public static final String DEVICE_KIND = "device_kind";
    private static final String DEVICE_LIST_TO_HEALTH_KIT_AUTHORIZATION = "device_list_to_health_kit_authorization";
    public static final String DEVICE_TYPE = "device_type";
    private static final String GOOGLE_CHINA_HEALTH_URL = "/app/C10414141?appId=C10414141&channel=4026633";
    private static final String GOOGLE_HEALTH_URL = "/store/apps/details?id=com.huawei.health";
    private static final String HEART_RATE_UNKNOWN_PRODUCT_ID = "aa:bb:cc:dd";
    private static final String HONOR_DEVICE = "honor";
    private static final String HUAWEI_DEVICE = "huawei";
    private static final String METIS_PRODUCTID = "9323f6b7-b459-44f4-a698-988d1769832a";
    private static final String TAG = "PluginDevice_DeviceCategoryFragment";
    public static final String WIFI_UPDATE_BACK_FINISH = "back_to_finish";
    private Bundle mBundle;
    private Context mContext;
    private ListView mDeviceListView;
    private String mDeviceType;
    private dka mDownloadDeviceResourceTool;
    private RelativeLayout mErrorRyt;
    private HealthTextView mErrorText;
    private View mMainView;
    private String mProductId;
    private ProductListAdapter mProductListAdapter;
    private String mUniqueId;
    private WeightListAdapter mWeightListAdapter;
    protected ArrayList<dcz> mProductInfos = new ArrayList<>(10);
    private boolean mIsBackToFinish = false;
    private boolean mIsRebind = false;
    private boolean mIsAutoJump = false;
    private ArrayList<dcz> mHuawei = new ArrayList<>(10);
    private ArrayList<dcz> mHonor = new ArrayList<>(10);
    private ArrayList<dcz> mOther = new ArrayList<>(10);
    private AdapterView.OnItemClickListener mOnItemClickListener = new AdapterView.OnItemClickListener() { // from class: com.huawei.health.ecologydevice.ui.measure.fragment.device.DeviceCategoryFragment.1
        @Override // android.widget.AdapterView.OnItemClickListener
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
            DeviceCategoryFragment deviceCategoryFragment = DeviceCategoryFragment.this;
            deviceCategoryFragment.handleClickEvent(i, deviceCategoryFragment.mProductListAdapter);
            ViewClickInstrumentation.clickOnListView(adapterView, view, i);
        }
    };

    @Override // com.huawei.health.ecologydevice.ui.BaseFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mBundle = getArguments();
        this.mContext = BaseApplication.getContext();
    }

    @Override // com.huawei.health.ecologydevice.ui.BaseFragment, androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        ViewGroup viewGroup2 = super.onCreateView(layoutInflater, viewGroup, bundle) instanceof ViewGroup ? (ViewGroup) super.onCreateView(layoutInflater, viewGroup, bundle) : null;
        if (layoutInflater == null) {
            LogUtil.a(TAG, "onCreateView inflater is null");
            return viewGroup2;
        }
        View inflate = layoutInflater.inflate(R.layout.device_category_fragment, viewGroup, false);
        this.mMainView = inflate;
        if (viewGroup2 != null) {
            viewGroup2.addView(inflate);
        }
        bindView();
        new Handler().postDelayed(new Runnable() { // from class: com.huawei.health.ecologydevice.ui.measure.fragment.device.DeviceCategoryFragment.2
            @Override // java.lang.Runnable
            public void run() {
                DeviceCategoryFragment.this.downloadIndexFile();
            }
        }, 500L);
        return viewGroup2;
    }

    private void bindView() {
        if (this.mainActivity == null) {
            LogUtil.h(TAG, "bindView mainActivity == null");
            return;
        }
        Bundle bundle = this.mBundle;
        if (bundle == null) {
            LogUtil.h(TAG, "bindView mBundle == null");
            return;
        }
        setTitle(bundle.getString(DEVICE_KIND));
        this.mDeviceListView = (ListView) this.mMainView.findViewById(R.id.device_list_view);
        this.mDeviceType = this.mBundle.getString(DEVICE_TYPE);
        this.mIsBackToFinish = this.mBundle.getBoolean(WIFI_UPDATE_BACK_FINISH);
        LogUtil.a(TAG, "bindView mDeviceType = ", this.mDeviceType);
        RelativeLayout relativeLayout = (RelativeLayout) this.mMainView.findViewById(R.id.addDevice_error_layout);
        this.mErrorRyt = relativeLayout;
        relativeLayout.setVisibility(8);
        this.mErrorRyt.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.health.ecologydevice.ui.measure.fragment.device.DeviceCategoryFragment$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                DeviceCategoryFragment.this.m363xae42a265(view);
            }
        });
        this.mErrorText = (HealthTextView) this.mMainView.findViewById(R.id.adddevice_error_text);
        this.mIsRebind = this.mBundle.getBoolean("isRebind", true);
        ContentValues contentValues = (ContentValues) this.mBundle.getParcelable("commonDeviceInfo");
        if (contentValues != null) {
            this.mUniqueId = contentValues.getAsString("uniqueId");
            this.mProductId = contentValues.getAsString("productId");
        }
        initListView();
        showLocalDeviceResource();
    }

    /* renamed from: lambda$bindView$0$com-huawei-health-ecologydevice-ui-measure-fragment-device-DeviceCategoryFragment, reason: not valid java name */
    /* synthetic */ void m363xae42a265(View view) {
        if (!CommonUtil.aa(BaseApplication.getContext())) {
            LogUtil.a(TAG, "Network is not Connected");
            nrh.b(this.mainActivity, R.string._2130841392_res_0x7f020f30);
            ViewClickInstrumentation.clickOnView(view);
        } else {
            this.mErrorRyt.setVisibility(8);
            downloadIndexFile();
            ViewClickInstrumentation.clickOnView(view);
        }
    }

    private void initListView() {
        if (HealthDevice.HealthDeviceKind.HDK_WEIGHT.name().equals(this.mDeviceType)) {
            WeightListAdapter weightListAdapter = new WeightListAdapter(this.mainActivity, this.mProductInfos);
            this.mWeightListAdapter = weightListAdapter;
            this.mDeviceListView.setAdapter((ListAdapter) weightListAdapter);
            this.mDeviceListView.setOnItemClickListener(new AdapterView.OnItemClickListener() { // from class: com.huawei.health.ecologydevice.ui.measure.fragment.device.DeviceCategoryFragment$$ExternalSyntheticLambda2
                @Override // android.widget.AdapterView.OnItemClickListener
                public final void onItemClick(AdapterView adapterView, View view, int i, long j) {
                    DeviceCategoryFragment.this.m364x63fc4837(adapterView, view, i, j);
                }
            });
        } else {
            ProductListAdapter productListAdapter = new ProductListAdapter(this.mProductInfos);
            this.mProductListAdapter = productListAdapter;
            this.mDeviceListView.setAdapter((ListAdapter) productListAdapter);
            this.mDeviceListView.setOnItemClickListener(this.mOnItemClickListener);
        }
        addFootView();
    }

    /* renamed from: lambda$initListView$1$com-huawei-health-ecologydevice-ui-measure-fragment-device-DeviceCategoryFragment, reason: not valid java name */
    /* synthetic */ void m364x63fc4837(AdapterView adapterView, View view, int i, long j) {
        HashMap hashMap = new HashMap(16);
        hashMap.put("click", "1");
        ixx.d().d(cpp.a(), AnalyticsValue.HEALTH_PLUGIN_DEVICE_SELECT_DEVICE_2060002.value(), hashMap, 0);
        dcz item = this.mWeightListAdapter.getItem(i);
        if (item != null) {
            if (isDeviceResourceCanUsing(item)) {
                jumpToIntroductionFragment(item.n(), item);
            } else {
                showAppVersionNotSupportDeviceDialog();
            }
        }
        ViewClickInstrumentation.clickOnListView(adapterView, view, i);
    }

    private void addFootView() {
        LayoutInflater layoutInflater = this.mainActivity.getSystemService("layout_inflater") instanceof LayoutInflater ? (LayoutInflater) this.mainActivity.getSystemService("layout_inflater") : null;
        if (layoutInflater == null) {
            LogUtil.h(TAG, "fuc addFootView inflater is null");
            return;
        }
        View inflate = layoutInflater.inflate(R.layout.device_no_find_button_layout, (ViewGroup) null);
        HealthTextView healthTextView = (HealthTextView) inflate.findViewById(R.id.not_found_device);
        this.mDeviceListView.addFooterView(inflate);
        inflate.setVisibility(0);
        healthTextView.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.health.ecologydevice.ui.measure.fragment.device.DeviceCategoryFragment$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                DeviceCategoryFragment.this.m362x42e3d7bf(view);
            }
        });
    }

    /* renamed from: lambda$addFootView$2$com-huawei-health-ecologydevice-ui-measure-fragment-device-DeviceCategoryFragment, reason: not valid java name */
    /* synthetic */ void m362x42e3d7bf(View view) {
        clickNotFoundDeviceButton(this.mDeviceType);
        ViewClickInstrumentation.clickOnView(view);
    }

    private void jumpToIntroductionFragment(dcz.b bVar, dcz dczVar) {
        if (bVar != null) {
            Fragment productIntroductionFragment = new ProductIntroductionFragment();
            HonourDeviceConstantsApi honourDeviceConstantsApi = (HonourDeviceConstantsApi) Services.c("PluginDevice", HonourDeviceConstantsApi.class);
            if (((DeviceInfoUtilsApi) Services.c("PluginDevice", DeviceInfoUtilsApi.class)).isWiFiDevice(dczVar.t())) {
                productIntroductionFragment = getPluginDeviceFragment(WeightConstants.FragmentType.WIFI_PRODUCT_INTRODUCTION);
            } else if (honourDeviceConstantsApi.isNoneHuaWeiWeightBondedDevice(dczVar.t()) && !this.mIsRebind && !honourDeviceConstantsApi.isHuaweiWspScaleProduct(dczVar.t())) {
                productIntroductionFragment = getNextFragment(dczVar.t());
            } else {
                LogUtil.h(TAG, "jumpToIntroductionFragment else");
            }
            if (productIntroductionFragment == null) {
                LogUtil.h(TAG, "jumpToIntroductionFragment nextFragment is null");
                return;
            }
            Bundle bundle = new Bundle();
            bundle.putString("productId", dczVar.t());
            bundle.putBoolean("isRebind", this.mIsRebind);
            ContentValues contentValues = new ContentValues();
            contentValues.put("uniqueId", this.mUniqueId);
            contentValues.put("productId", dczVar.t());
            bundle.putParcelable("commonDeviceInfo", contentValues);
            bundle.putBoolean("isAdd", true);
            productIntroductionFragment.setArguments(bundle);
            switchFragment(productIntroductionFragment);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleClickEvent(int i, ProductListAdapter productListAdapter) {
        HashMap hashMap = new HashMap(16);
        hashMap.put("click", "1");
        ixx.d().d(cpp.a(), AnalyticsValue.HEALTH_PLUGIN_DEVICE_SELECT_DEVICE_2060002.value(), hashMap, 0);
        if (i >= productListAdapter.getCount()) {
            LogUtil.h(TAG, "handleClickEvent outofbounds exception ");
            return;
        }
        cjv cjvVar = productListAdapter.getItem(i) instanceof cjv ? (cjv) productListAdapter.getItem(i) : null;
        if (cjvVar == null || cjvVar.a() != 0) {
            return;
        }
        dealWithItemClickEvent(cjvVar);
    }

    private void dealWithItemClickEvent(cjv cjvVar) {
        dcz dczVar = cjvVar.i() instanceof dcz ? (dcz) cjvVar.i() : null;
        boolean equals = "true".equals(gmz.d().c(402));
        if (dczVar != null) {
            if (CommonUtil.cg() && "PRO_GPRS".equals(dczVar.y())) {
                dks.d(getActivity(), dczVar, dczVar.t(), dczVar.x().a());
                if (getActivity() != null) {
                    getActivity().finish();
                    return;
                }
                return;
            }
            if (isDeviceResourceCanUsing(dczVar)) {
                Fragment productIntroductionFragment = new ProductIntroductionFragment();
                HonourDeviceConstantsApi honourDeviceConstantsApi = (HonourDeviceConstantsApi) Services.c("PluginDevice", HonourDeviceConstantsApi.class);
                if (((DeviceInfoUtilsApi) Services.c("PluginDevice", DeviceInfoUtilsApi.class)).isWiFiDevice(dczVar.t())) {
                    productIntroductionFragment = getPluginDeviceFragment(WeightConstants.FragmentType.WIFI_PRODUCT_INTRODUCTION);
                } else if (honourDeviceConstantsApi.isNoneHuaWeiWeightBondedDevice(dczVar.t()) && !this.mIsRebind && !honourDeviceConstantsApi.isHuaweiWspScaleProduct(dczVar.t())) {
                    productIntroductionFragment = getNextFragment(dczVar.t());
                } else if (BIND_ON_H5_PAGE_FLAG.equals(dczVar.b()) && equals) {
                    startWebViewActivity(dczVar.t());
                    return;
                } else {
                    if (BIND_ON_H5_PAGE_FLAG.equals(dczVar.b()) && !equals) {
                        startHealthKitActivity(dczVar.t());
                        return;
                    }
                    LogUtil.h(TAG, "handleClickEvent else");
                }
                if (productIntroductionFragment == null) {
                    LogUtil.h(TAG, "dealWithItemClickEvent nextFragment is null");
                    return;
                }
                Bundle bundle = new Bundle();
                bundle.putString("productId", dczVar.t());
                bundle.putBoolean("isRebind", this.mIsRebind);
                ContentValues contentValues = new ContentValues();
                contentValues.put("uniqueId", this.mUniqueId);
                contentValues.put("productId", dczVar.t());
                bundle.putParcelable("commonDeviceInfo", contentValues);
                productIntroductionFragment.setArguments(bundle);
                switchFragment(productIntroductionFragment);
                return;
            }
            showAppVersionNotSupportDeviceDialog();
        }
    }

    private Fragment getPluginDeviceFragment(WeightConstants.FragmentType fragmentType) {
        return ((DeviceMainActivityApi) Services.c("PluginDevice", DeviceMainActivityApi.class)).getPluginDeviceFragment(fragmentType);
    }

    private void startHealthKitActivity(String str) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.b(TAG, "startHealthKitActivity productId is null");
            return;
        }
        Intent intent = new Intent();
        intent.setPackage(this.mContext.getPackageName());
        intent.setClassName(this.mContext, CLASS_NAME_HEALTH_KIT_DETAIL);
        intent.putExtra("productId", str);
        intent.putExtra(DEVICE_LIST_TO_HEALTH_KIT_AUTHORIZATION, true);
        dcz d = ResourceManager.e().d(str);
        ContentValues contentValues = new ContentValues();
        if (d == null) {
            LogUtil.b(TAG, "startHealthKitActivity deviceProductInfo is null");
            return;
        }
        contentValues.put("name", d.n().b());
        contentValues.put("deviceType", d.l().name());
        intent.putExtra("commonDeviceInfo", contentValues);
        startActivity(intent);
    }

    private void startWebViewActivity(String str) {
        Intent intent = new Intent();
        intent.setPackage(this.mContext.getPackageName());
        intent.setClassName(this.mContext, "com.huawei.operation.activity.WebViewActivity");
        intent.putExtra("url", dcq.b().c(str) + "#/type=4/uuidpre=" + dks.b(str));
        intent.putExtra("productId", str);
        dcz d = ResourceManager.e().d(str);
        ContentValues contentValues = new ContentValues();
        if (d == null) {
            return;
        }
        contentValues.put("name", dks.e(str, d.n().b()));
        contentValues.put("deviceType", d.l().name());
        intent.putExtra("commonDeviceInfo", contentValues);
        LogUtil.a(TAG, "sannuo_type=4_device list page to H5 interface,Productid=", str);
        startActivity(intent);
    }

    /* JADX WARN: Code restructure failed: missing block: B:18:0x0043, code lost:
    
        if (r0 >= r2) goto L15;
     */
    /* JADX WARN: Code restructure failed: missing block: B:8:0x0033, code lost:
    
        if (r0 >= java.lang.Long.parseLong(r1)) goto L9;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private boolean isDeviceResourceCanUsing(defpackage.dcz r8) {
        /*
            r7 = this;
            android.content.Context r0 = com.huawei.hwcommonmodel.application.BaseApplication.getContext()
            int r0 = health.compact.a.CommonUtil.d(r0)
            java.lang.String r1 = "isDeviceResourceCanUsing currentVersion ="
            java.lang.Integer r2 = java.lang.Integer.valueOf(r0)
            java.lang.Object[] r1 = new java.lang.Object[]{r1, r2}
            java.lang.String r2 = "PluginDevice_DeviceCategoryFragment"
            com.huawei.hwlogsmodel.LogUtil.a(r2, r1)
            java.lang.String r1 = r8.a()
            java.lang.String r8 = r8.c()
            boolean r3 = health.compact.a.Utils.o()     // Catch: java.lang.NumberFormatException -> L46
            r4 = 1
            if (r3 != 0) goto L36
            boolean r8 = android.text.TextUtils.isEmpty(r1)     // Catch: java.lang.NumberFormatException -> L46
            if (r8 != 0) goto L35
            long r5 = (long) r0     // Catch: java.lang.NumberFormatException -> L46
            long r0 = java.lang.Long.parseLong(r1)     // Catch: java.lang.NumberFormatException -> L46
            int r8 = (r5 > r0 ? 1 : (r5 == r0 ? 0 : -1))
            if (r8 < 0) goto L4f
        L35:
            return r4
        L36:
            boolean r1 = android.text.TextUtils.isEmpty(r8)     // Catch: java.lang.NumberFormatException -> L46
            if (r1 != 0) goto L45
            long r0 = (long) r0     // Catch: java.lang.NumberFormatException -> L46
            long r2 = java.lang.Long.parseLong(r8)     // Catch: java.lang.NumberFormatException -> L46
            int r8 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r8 < 0) goto L4f
        L45:
            return r4
        L46:
            java.lang.String r8 = "isDeviceResourceCanUsing NumberFormatException"
            java.lang.Object[] r8 = new java.lang.Object[]{r8}
            com.huawei.hwlogsmodel.LogUtil.b(r2, r8)
        L4f:
            r8 = 0
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.health.ecologydevice.ui.measure.fragment.device.DeviceCategoryFragment.isDeviceResourceCanUsing(dcz):boolean");
    }

    private Fragment getNextFragment(String str) {
        if (((HonourDeviceConstantsApi) Services.c("PluginDevice", HonourDeviceConstantsApi.class)).isHuaweiWspScaleProduct(str)) {
            return getPluginDeviceFragment(WeightConstants.FragmentType.HAGRID_DEVICE_MANAGER);
        }
        return getPluginDeviceFragment(WeightConstants.FragmentType.HONOUR_DEVICE);
    }

    private void showLocalDeviceResource() {
        createDownLoadFileTool();
        this.mDownloadDeviceResourceTool.b();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void downloadIndexFile() {
        createDownLoadFileTool();
        this.mDownloadDeviceResourceTool.e();
    }

    private void createDownLoadFileTool() {
        if (this.mDownloadDeviceResourceTool == null) {
            dka dkaVar = new dka(this.mainActivity, this.mDeviceType, 1, new dkc() { // from class: com.huawei.health.ecologydevice.ui.measure.fragment.device.DeviceCategoryFragment.3
                @Override // defpackage.dkb
                public void onRefresh(Message message) {
                    DeviceCategoryFragment.this.refreshList(message);
                }

                @Override // defpackage.dkb
                public void onShowErrorLayout() {
                    if (DeviceCategoryFragment.this.isAdded()) {
                        DeviceCategoryFragment.this.showErrorLayout();
                    }
                }
            });
            this.mDownloadDeviceResourceTool = dkaVar;
            dkaVar.b(this.mPresetDeviceInChinaMap, this.mPresetDeviceInOverseaMap);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showErrorLayout() {
        this.mErrorText.setText(getString(R.string.IDS_device_plugin_download_error));
        this.mErrorRyt.setVisibility(0);
    }

    @Override // com.huawei.health.ecologydevice.ui.BaseFragment, androidx.fragment.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        LogUtil.a(TAG, "onDestroy() enter");
        dka dkaVar = this.mDownloadDeviceResourceTool;
        if (dkaVar != null) {
            dkaVar.d();
            this.mDownloadDeviceResourceTool = null;
        }
        this.mainActivity = null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void refreshList(Message message) {
        dcz dczVar = message.obj instanceof dcz ? (dcz) message.obj : null;
        if (dczVar == null || dczVar.l() == null) {
            return;
        }
        int c = dczVar.x().c();
        if (dczVar.l().name() != null && this.mDeviceType.equals(dczVar.l().name())) {
            if (TextUtils.equals("true", dczVar.c("is_dual_mode")) && !((HonourDeviceConstantsApi) Services.c("PluginDevice", HonourDeviceConstantsApi.class)).isSupportWifiDevice()) {
                LogUtil.h(TAG, "buildNeedUpdateDownLoadDeviceList has no cloud and is hagride device");
                return;
            } else {
                refreshDeviceList(dczVar);
                return;
            }
        }
        LogUtil.a(TAG, "productInfo is null scanMode = ", Integer.valueOf(c));
    }

    private void refreshDeviceList(dcz dczVar) {
        if (METIS_PRODUCTID.equals(dczVar.t()) || AM16_PRODUCTID.equals(dczVar.t()) || !((HonourDeviceConstantsApi) Services.c("PluginDevice", HonourDeviceConstantsApi.class)).isShowWiFiDevice(dczVar.t())) {
            LogUtil.a(TAG, "refreshDeviceList: productInfo", dczVar);
            return;
        }
        if (this.mDeviceType.equals(HealthDevice.HealthDeviceKind.HDK_WEIGHT.name())) {
            String a2 = dczVar.n().a();
            LogUtil.a(TAG, "handleMessage company", a2);
            if (isAdded()) {
                sort(this.mProductInfos, dczVar, a2);
            }
            try {
                autoJumpPairedPage();
                this.mWeightListAdapter.d(this.mProductInfos);
                this.mWeightListAdapter.notifyDataSetChanged();
                return;
            } catch (IllegalStateException e) {
                LogUtil.a(TAG, " handleMessage TO_REFRESH HDK_WEIGHT isMain", Boolean.valueOf(Looper.myLooper() == Looper.getMainLooper()));
                LogUtil.a(TAG, " handleMessage TO_REFRESH HDK_WEIGHT e", e.getMessage());
                return;
            }
        }
        this.mProductListAdapter.c(dczVar);
        try {
            this.mProductListAdapter.notifyDataSetChanged();
        } catch (IllegalStateException e2) {
            LogUtil.a(TAG, " handleMessage TO_REFRESH isMain", Boolean.valueOf(Looper.myLooper() == Looper.getMainLooper()));
            LogUtil.a(TAG, " handleMessage TO_REFRESH e", e2.getMessage());
        }
    }

    private void autoJumpPairedPage() {
        if (TextUtils.isEmpty(this.mProductId) || this.mIsAutoJump) {
            return;
        }
        int i = 0;
        while (true) {
            if (i >= this.mProductInfos.size()) {
                i = -1;
                break;
            } else if (this.mProductId.equals(this.mProductInfos.get(i).t())) {
                break;
            } else {
                i++;
            }
        }
        if (i < 0) {
            LogUtil.h(TAG, "jumpToIntroductionFragment position < 0");
            return;
        }
        dcz dczVar = this.mProductInfos.get(i);
        if (dczVar == null) {
            LogUtil.h(TAG, "jumpToIntroductionFragment productInfo is null");
            return;
        }
        this.mIsAutoJump = true;
        if (isDeviceResourceCanUsing(dczVar)) {
            jumpToIntroductionFragment(dczVar.n(), dczVar);
        } else {
            showAppVersionNotSupportDeviceDialog();
        }
    }

    private void sort(ArrayList<dcz> arrayList, dcz dczVar, String str) {
        if (arrayList.isEmpty()) {
            dcz dczVar2 = new dcz(1);
            if (HUAWEI_DEVICE.equalsIgnoreCase(str)) {
                dczVar2.f(getString(R.string.IDS_device_huawei_band));
                dczVar2.d(true);
                dczVar.e(false);
            } else if (HONOR_DEVICE.equalsIgnoreCase(str)) {
                dczVar2.f(getString(R.string.IDS_device_honor_band));
                dczVar2.d(false);
                dczVar.e(false);
            } else {
                dczVar2.f(getString(R.string._2130841847_res_0x7f0210f7).toUpperCase(Locale.getDefault()));
                dczVar2.d(false);
            }
            arrayList.add(dczVar2);
            arrayList.add(dczVar);
            return;
        }
        productInfosSort(arrayList);
        int query = query(arrayList, dczVar);
        if (HUAWEI_DEVICE.equalsIgnoreCase(str)) {
            judgeHuaweiDevice(dczVar, query, arrayList);
            return;
        }
        if (HONOR_DEVICE.equalsIgnoreCase(str)) {
            judgeHonorDevice(dczVar, query, arrayList);
            return;
        }
        if (this.mOther.size() <= 0) {
            dcz dczVar3 = new dcz(1);
            dczVar3.f(getString(R.string._2130841847_res_0x7f0210f7).toUpperCase(Locale.getDefault()));
            dczVar3.d(false);
            arrayList.add(arrayList.size(), dczVar3);
        }
        if (query == -1) {
            arrayList.add(arrayList.size(), dczVar);
        } else {
            arrayList.set(query, dczVar);
        }
    }

    private void judgeHuaweiDevice(dcz dczVar, int i, ArrayList<dcz> arrayList) {
        if (this.mHuawei.size() <= 0) {
            dcz dczVar2 = new dcz(1);
            if (isAdded()) {
                dczVar2.f(getString(R.string.IDS_device_huawei_band));
            } else {
                LogUtil.a(TAG, "not attached to a context");
            }
            dczVar2.d(true);
            arrayList.add(0, dczVar2);
            dczVar.e(false);
        }
        if (i == -1) {
            arrayList.add(1, dczVar);
        } else {
            arrayList.set(i, dczVar);
        }
    }

    private void judgeHonorDevice(dcz dczVar, int i, ArrayList<dcz> arrayList) {
        if (this.mHonor.size() <= 0) {
            dcz dczVar2 = new dcz(1);
            dczVar2.f(getString(R.string.IDS_device_honor_band));
            dczVar2.d(false);
            if (this.mHuawei.size() <= 0) {
                arrayList.add(this.mHuawei.size(), dczVar2);
            } else {
                arrayList.add(this.mHuawei.size() + 1, dczVar2);
            }
            dczVar.e(false);
        }
        if (i == -1) {
            if (this.mHuawei.size() <= 0) {
                arrayList.add(this.mHuawei.size() + 1, dczVar);
                return;
            } else {
                arrayList.add(this.mHuawei.size() + 2, dczVar);
                return;
            }
        }
        arrayList.set(i, dczVar);
    }

    private void productInfosSort(ArrayList<dcz> arrayList) {
        this.mHuawei.clear();
        this.mHonor.clear();
        this.mOther.clear();
        Iterator<dcz> it = arrayList.iterator();
        while (it.hasNext()) {
            dcz next = it.next();
            dcz.b n = next.n();
            if (n != null) {
                String a2 = n.a();
                if (a2 != null) {
                    if (a2.equalsIgnoreCase(HUAWEI_DEVICE)) {
                        this.mHuawei.add(next);
                    } else if (a2.equalsIgnoreCase(HONOR_DEVICE)) {
                        this.mHonor.add(next);
                    } else {
                        LogUtil.a(TAG, "productInfosSort is other info");
                    }
                } else {
                    this.mOther.add(next);
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void switchDeviceScanningFragment() {
        if (isAdded()) {
            setTitle(getResources().getString(R.string.IDS_device_search_title));
            Bundle bundle = new Bundle();
            bundle.putString("productId", HEART_RATE_UNKNOWN_PRODUCT_ID);
            bundle.putString("scan_kind", HealthDevice.HealthDeviceKind.HDK_HEART_RATE.name());
            bundle.putString("title", getString(R.string.IDS_device_search_title));
            Fragment deviceScanningFragment = new DeviceScanningFragment();
            ContentValues contentValues = new ContentValues();
            contentValues.put("uniqueId", this.mUniqueId);
            contentValues.put("productId", HEART_RATE_UNKNOWN_PRODUCT_ID);
            bundle.putParcelable("commonDeviceInfo", contentValues);
            deviceScanningFragment.setArguments(bundle);
            switchFragment(deviceScanningFragment);
        }
    }

    private void clickNotFoundDeviceButton(String str) {
        if (HealthDevice.HealthDeviceKind.HDK_HEART_RATE.name().equals(str)) {
            HashMap hashMap = new HashMap(16);
            hashMap.put("click", "1");
            ixx.d().d(cpp.a(), AnalyticsValue.HEALTH_PLUGIN_DEVICE_MORE_DEVICE_SEARCH_2060017.value(), hashMap, 0);
            if (BluetoothAdapter.getDefaultAdapter().getState() != 12) {
                NoTitleCustomAlertDialog.Builder builder = new NoTitleCustomAlertDialog.Builder(this.mainActivity);
                builder.e(R.string.IDS_device_bluetooth_open_request);
                builder.czC_(R.string.IDS_device_ui_dialog_yes, new View.OnClickListener() { // from class: com.huawei.health.ecologydevice.ui.measure.fragment.device.DeviceCategoryFragment.4
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        DeviceCategoryFragment.this.switchDeviceScanningFragment();
                        ViewClickInstrumentation.clickOnView(view);
                    }
                });
                builder.czz_(R.string.IDS_device_ui_dialog_no, new View.OnClickListener() { // from class: com.huawei.health.ecologydevice.ui.measure.fragment.device.DeviceCategoryFragment.5
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        ViewClickInstrumentation.clickOnView(view);
                    }
                });
                NoTitleCustomAlertDialog e = builder.e();
                e.setCancelable(false);
                e.show();
                return;
            }
            switchDeviceScanningFragment();
            return;
        }
        showNoFoundDeviceDialog();
    }

    private void showNoFoundDeviceDialog() {
        boolean o = Utils.o();
        LogUtil.a(TAG, "isOverSea = ", Boolean.valueOf(o));
        if (o && !CommonUtil.bf()) {
            showOverSeaDownloadDialog();
        } else if (jdm.b(this.mContext, "com.huawei.appmarket") && !CommonUtil.bf()) {
            showHuaweiMarketDownloadAppDialog();
        } else {
            showOtherMarketDownloadAppDialog();
        }
    }

    private void showAppVersionNotSupportDeviceDialog() {
        if (Utils.o()) {
            showOverSeaDownloadDialog();
        } else if (jdm.b(this.mContext, "com.huawei.appmarket")) {
            showHuaweiMarketDownloadAppDialog();
        } else {
            showHuaweiVmallDownloadByBrowserDialog();
        }
    }

    private void showOverSeaDownloadDialog() {
        if (jdm.b(this.mContext, "com.android.vending") && jdp.c(this.mContext)) {
            showGooglePlayDownloadAppDialog();
        } else if (jdm.b(this.mContext, "com.huawei.appmarket")) {
            showHuaweiMarketDownloadAppDialog();
        } else {
            showAppGalleryDownloadByBrowserDialog();
        }
    }

    private void showAppGalleryDownloadByBrowserDialog() {
        showNoTitleCustomAlertDialog(new NoTitleCustomAlertDialog.Builder(this.mainActivity).e(getDownloadAppDialogText(this.mContext.getResources().getString(R.string.IDS_device_mgr_no_support_device_content))).czC_(R.string.IDS_device_to_go_into_app_market, new View.OnClickListener() { // from class: com.huawei.health.ecologydevice.ui.measure.fragment.device.DeviceCategoryFragment.7
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                DeviceCategoryFragment.this.gotoAppGalleryDownloadByBrowser();
                ViewClickInstrumentation.clickOnView(view);
            }
        }).czz_(R.string._2130841130_res_0x7f020e2a, new View.OnClickListener() { // from class: com.huawei.health.ecologydevice.ui.measure.fragment.device.DeviceCategoryFragment.6
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ViewClickInstrumentation.clickOnView(view);
            }
        }));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void openDownloadByBrowserActivity(final String str) {
        if (StringUtils.g(str)) {
            LogUtil.h(TAG, "openDownloadByBrowserActivity: downloadPageUrl is invalid");
        } else if (this.mainActivity == null) {
            LogUtil.h(TAG, "openDownloadByBrowserActivity: mainActivity is null");
        } else {
            this.mainActivity.runOnUiThread(new Runnable() { // from class: com.huawei.health.ecologydevice.ui.measure.fragment.device.DeviceCategoryFragment.8
                @Override // java.lang.Runnable
                public void run() {
                    try {
                        DeviceCategoryFragment.this.startActivity(new Intent(CommonConstant.ACTION.HWID_SCHEME_URL, Uri.parse(str)));
                    } catch (ActivityNotFoundException unused) {
                        LogUtil.b(DeviceCategoryFragment.TAG, "openDownloadByBrowserActivity: startActivity ActivityNotFoundException");
                    }
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void gotoAppGalleryDownloadByBrowser() {
        ThreadPoolManager.d().d("appGalleryDownloadByBrowser", new Runnable() { // from class: com.huawei.health.ecologydevice.ui.measure.fragment.device.DeviceCategoryFragment.9
            @Override // java.lang.Runnable
            public void run() {
                String countryCode = SharedPreferenceUtil.getInstance(DeviceCategoryFragment.this.mContext).getCountryCode();
                String noCheckUrl = GRSManager.a(DeviceCategoryFragment.this.mContext).getNoCheckUrl("domainAppgalleryCloudHuawei", countryCode);
                if (StringUtils.g(noCheckUrl)) {
                    LogUtil.h(DeviceCategoryFragment.TAG, "gotoAppGalleryDownloadByBrowser: appGalleryUrl is invalid");
                    return;
                }
                LogUtil.a(DeviceCategoryFragment.TAG, "gotoAppGalleryDownloadByBrowser: countryCode:", countryCode);
                DeviceCategoryFragment.this.openDownloadByBrowserActivity(noCheckUrl + DeviceCategoryFragment.APP_GALLERY_HEALTH_URL);
            }
        });
    }

    private void showHuaweiVmallDownloadByBrowserDialog() {
        showNoTitleCustomAlertDialog(new NoTitleCustomAlertDialog.Builder(this.mainActivity).e(getDownloadAppDialogText(this.mContext.getResources().getString(R.string.IDS_device_mgr_no_support_device_content))).czC_(R.string.IDS_device_to_go_into_app_market, new View.OnClickListener() { // from class: com.huawei.health.ecologydevice.ui.measure.fragment.device.DeviceCategoryFragment.11
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                DeviceCategoryFragment.this.gotoHuaweiVmallDownloadByBrowser();
                ViewClickInstrumentation.clickOnView(view);
            }
        }).czz_(R.string._2130841130_res_0x7f020e2a, new View.OnClickListener() { // from class: com.huawei.health.ecologydevice.ui.measure.fragment.device.DeviceCategoryFragment.10
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ViewClickInstrumentation.clickOnView(view);
            }
        }));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void gotoHuaweiVmallDownloadByBrowser() {
        ThreadPoolManager.d().d("huaweiVmallDownloadBrowser", new Runnable() { // from class: com.huawei.health.ecologydevice.ui.measure.fragment.device.DeviceCategoryFragment.12
            @Override // java.lang.Runnable
            public void run() {
                String url = GRSManager.a(BaseApplication.getContext()).getUrl("domainAVmall");
                if (StringUtils.g(url)) {
                    LogUtil.h(DeviceCategoryFragment.TAG, "gotoHuaweiVmallDownloadByBrowser: googlePlayUrl is invalid");
                    return;
                }
                DeviceCategoryFragment.this.openDownloadByBrowserActivity(url + DeviceCategoryFragment.GOOGLE_CHINA_HEALTH_URL);
            }
        });
    }

    private void showGooglePlayDownloadByBrowserDialog() {
        showNoTitleCustomAlertDialog(new NoTitleCustomAlertDialog.Builder(this.mainActivity).e(getDownloadAppDialogText(this.mContext.getResources().getString(R.string.IDS_device_mgr_no_support_device_content_over_sea))).czC_(R.string.IDS_device_to_go_into_app_market, new View.OnClickListener() { // from class: com.huawei.health.ecologydevice.ui.measure.fragment.device.DeviceCategoryFragment.14
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                DeviceCategoryFragment.this.gotoGooglePlayDownloadByBrowser();
                ViewClickInstrumentation.clickOnView(view);
            }
        }).czz_(R.string._2130841130_res_0x7f020e2a, new View.OnClickListener() { // from class: com.huawei.health.ecologydevice.ui.measure.fragment.device.DeviceCategoryFragment.13
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ViewClickInstrumentation.clickOnView(view);
            }
        }));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void gotoGooglePlayDownloadByBrowser() {
        ThreadPoolManager.d().d("googlePlayDownloadByBrowser", new Runnable() { // from class: com.huawei.health.ecologydevice.ui.measure.fragment.device.DeviceCategoryFragment.15
            @Override // java.lang.Runnable
            public void run() {
                String e = jah.c().e("domain_play_google");
                if (StringUtils.g(e)) {
                    LogUtil.h(DeviceCategoryFragment.TAG, "gotoGooglePlayDownloadByBrowser: googlePlayUrl is invalid");
                    return;
                }
                DeviceCategoryFragment.this.openDownloadByBrowserActivity(e + DeviceCategoryFragment.GOOGLE_HEALTH_URL);
            }
        });
    }

    private void showHuaweiMarketDownloadAppDialog() {
        showNoTitleCustomAlertDialog(new NoTitleCustomAlertDialog.Builder(this.mainActivity).e(getDownloadAppDialogText(this.mContext.getResources().getString(R.string.IDS_device_mgr_no_support_device_content))).czC_(R.string.IDS_device_to_go_into_app_market, new View.OnClickListener() { // from class: com.huawei.health.ecologydevice.ui.measure.fragment.device.DeviceCategoryFragment.17
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                CommonUtil.d(DeviceCategoryFragment.this.mainActivity, "com.huawei.health");
                ViewClickInstrumentation.clickOnView(view);
            }
        }).czz_(R.string._2130841130_res_0x7f020e2a, new View.OnClickListener() { // from class: com.huawei.health.ecologydevice.ui.measure.fragment.device.DeviceCategoryFragment.16
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ViewClickInstrumentation.clickOnView(view);
            }
        }));
    }

    private void showGooglePlayDownloadAppDialog() {
        showNoTitleCustomAlertDialog(new NoTitleCustomAlertDialog.Builder(this.mainActivity).e(getDownloadAppDialogText(this.mContext.getResources().getString(R.string.IDS_device_mgr_no_support_device_content_over_sea))).czC_(R.string.IDS_device_to_go_into_app_market, new View.OnClickListener() { // from class: com.huawei.health.ecologydevice.ui.measure.fragment.device.DeviceCategoryFragment.19
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                CommonUtil.c(DeviceCategoryFragment.this.mainActivity, "com.huawei.health");
                ViewClickInstrumentation.clickOnView(view);
            }
        }).czz_(R.string._2130841130_res_0x7f020e2a, new View.OnClickListener() { // from class: com.huawei.health.ecologydevice.ui.measure.fragment.device.DeviceCategoryFragment.18
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ViewClickInstrumentation.clickOnView(view);
            }
        }));
    }

    private void showOtherMarketDownloadAppDialog() {
        showNoTitleCustomAlertDialog(new NoTitleCustomAlertDialog.Builder(this.mainActivity).e(getDownloadAppDialogText(this.mContext.getResources().getString(R.string.IDS_device_mgr_no_support_device_common_content))).czC_(R.string._2130841554_res_0x7f020fd2, new View.OnClickListener() { // from class: com.huawei.health.ecologydevice.ui.measure.fragment.device.DeviceCategoryFragment.20
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LogUtil.a(DeviceCategoryFragment.TAG, "click known button");
                ViewClickInstrumentation.clickOnView(view);
            }
        }));
    }

    private void showNoTitleCustomAlertDialog(NoTitleCustomAlertDialog.Builder builder) {
        if (builder == null) {
            return;
        }
        NoTitleCustomAlertDialog e = builder.e();
        e.setCancelable(false);
        e.show();
    }

    private String getDownloadAppDialogText(String str) {
        return this.mContext.getResources().getString(R.string.IDS_device_mgr_not_found_device_tips1) + System.lineSeparator() + str;
    }

    @Override // com.huawei.health.ecologydevice.ui.BaseFragment
    public boolean onBackPressed() {
        if (this.mIsBackToFinish && this.mainActivity != null) {
            this.mainActivity.finish();
            return false;
        }
        return super.onBackPressed();
    }
}
