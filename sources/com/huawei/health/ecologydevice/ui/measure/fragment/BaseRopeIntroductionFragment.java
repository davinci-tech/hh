package com.huawei.health.ecologydevice.ui.measure.fragment;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.LruCache;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import androidx.fragment.app.FragmentActivity;
import com.google.gson.Gson;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.health.R;
import com.huawei.health.device.api.DeviceInfoUtilsApi;
import com.huawei.health.device.api.HealthDeviceEntryApi;
import com.huawei.health.device.api.HonourDeviceConstantsApi;
import com.huawei.health.device.manager.ResourceFileListener;
import com.huawei.health.device.model.HealthDevice;
import com.huawei.health.ecologydevice.callback.IdialogButtonClickCallback;
import com.huawei.health.ecologydevice.callback.RopeDeviceDataListener;
import com.huawei.health.ecologydevice.clouddevice.DownloadDeviceInfoCallBack;
import com.huawei.health.ecologydevice.fitness.datastruct.DeviceInformation;
import com.huawei.health.ecologydevice.fitness.datastruct.SwitchStatusData;
import com.huawei.health.ecologydevice.manager.ResourceManager;
import com.huawei.health.ecologydevice.ui.measure.fragment.ropefragment.DeviceInfoFragment;
import com.huawei.health.marketing.api.MarketingApi;
import com.huawei.health.marketing.datatype.ResourceResultInfo;
import com.huawei.health.suggestion.model.FitRunPlayAudio;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hmf.tasks.OnFailureListener;
import com.huawei.hmf.tasks.OnSuccessListener;
import com.huawei.hmf.tasks.Task;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.nfc.PluginPayAdapter;
import com.huawei.ui.commonui.dialog.CustomViewDialog;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.popupview.PopViewList;
import com.huawei.ui.commonui.progressbar.HealthProgressBar;
import defpackage.cpp;
import defpackage.cwt;
import defpackage.czb;
import defpackage.czd;
import defpackage.dcq;
import defpackage.dcx;
import defpackage.dcz;
import defpackage.ddk;
import defpackage.ddr;
import defpackage.dds;
import defpackage.dif;
import defpackage.dij;
import defpackage.djt;
import defpackage.dko;
import defpackage.dks;
import defpackage.dku;
import defpackage.ixx;
import defpackage.knl;
import defpackage.koq;
import defpackage.nrh;
import defpackage.nrz;
import defpackage.nsf;
import defpackage.nsn;
import health.compact.a.LanguageUtil;
import health.compact.a.Services;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;
import health.compact.a.UnitUtil;
import java.io.File;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;

/* loaded from: classes3.dex */
public abstract class BaseRopeIntroductionFragment extends ProductFragment implements RopeDeviceDataListener, ResourceFileListener {
    private static final int AUTO_CONNECT_DEVICE_DELAYED = 500;
    private static final int AUTO_CONNECT_ROPE_DEVICE = 105;
    private static final String BATTERY_NORMAL_KEY = "battery_normal_key";
    private static final String HUAWEI_FIT = "HUAWEI FIT";
    public static final String KEY_FROM_SCAN = "IsFromScan";
    private static final int LRU_CACHE_SIZE = 1048576;
    private static final int MAX_CONNECTION_FAILURES = 3;
    private static final String METIS_PRODUCTID = "9323f6b7-b459-44f4-a698-988d1769832a";
    private static final String NFC = "NFC";
    private static final String OPCODE_DELETE = "Delete";
    private static final String QRCODE = "QRCODE";
    private static final int REMOVE = 2;
    private static final String ROPE_DEVICE_INFO = "rope_device_info";
    private static final String SCAN = "SCAN";
    private static final int SELECTING_AUTHORIZATION_FUNCTION = 116;
    private static final int SHOW_CONNECTION_NOTE_DIALOG = 104;
    private static final String SP_BATTERY_NUM = "sp_battery_num";
    private static final String SP_DEVICE_INFO = "RopeDeviceIntroductionSp";
    private static final String TAG = "BaseRopeIntroductionFragment";
    private static final int UPDATE_ROPE_SKIPPING_BATTERY_STATUS = 107;
    private static final int UPDATE_ROPE_SKIPPING_CONNECTION_STATUS = 109;
    private static final int UPDATE_ROPE_SKIPPING_DATA = 106;
    private static final int UPDATE_ROPE_SKIPPING_DEVICE_INFO = 108;
    private static final int UPDATE_ROPE_SKIPPING_RESOURCE_FILE = 110;
    private static final int UPDATE_ROPE_SKIPPING_REVERSE_CONTROL = 114;
    private static final int UPDATE_ROPE_SKIPPING_ROPE_CONFIG_SETTING = 115;
    private static final int UPDATE_ROPE_SKIPPING_ROPE_CONFIG_SWITCH = 117;
    private static final int UPDATE_ROPE_SKIPPING_SET_DEFAULT_VALUE = 111;
    private static final int UPDATE_ROPE_SKIPPING_SET_MONTH_DATA = 112;
    private static final int UPDATE_ROPE_SKIPPING_SET_ROPE_MODE = 113;
    private ImageView mBatteryIconIv;
    private String mBatteryKey;
    private LinearLayout mBatteryLayout;
    private int mBatteryValue;
    private HealthTextView mBatteryValueTv;
    private HealthTextView mConnectStatusTv;
    private CustomViewDialog mConnectionNoteDialog;
    private LinearLayout mContainerLayout;
    private ImageView mDeviceImageIv;
    protected DeviceInformation mDeviceInformation;
    private String mEnterType;
    private boolean mIsNeedProgressBarVisiable;
    protected String mMacAddress;
    private LinearLayout mMarketingLayout;
    private RelativeLayout mPerformanceLayout;
    private LinearLayout mPersonalPerformanceLayout;
    private PopViewList mPopViewList;
    private HealthProgressBar mReconnectLoadingPb;
    private HealthTextView mReconnectTv;
    protected ddk mRopeDeviceDataManager;
    protected ViewGroup mRopeHistoryLayout;
    private long mStartConnectTime;
    protected String mTitle;
    protected ArrayList<String> mMoreMenuList = new ArrayList<>();
    private HealthDevice.HealthDeviceKind mKind = HealthDevice.HealthDeviceKind.HDK_ROPE_SKIPPING;
    private RopeDeviceHandler mRopeDeviceHandler = new RopeDeviceHandler();
    private int mReconnectionTime = 0;
    private LruCache<String, Bitmap> mBitmapLruCache = new LruCache<>(1048576);
    private ArrayList<String> mImagePathList = new ArrayList<>(10);
    private boolean mIsOtaUpdate = false;

    protected abstract int getLayoutId();

    protected abstract void initChildView();

    protected void initDefaultRopeView() {
    }

    @Override // com.huawei.health.ecologydevice.callback.RopeDeviceDataListener
    public void onQuaryTrackDetailDataSuccess() {
    }

    protected void refreshLastRopeDataUi() {
    }

    protected void refreshMonthDataUi() {
    }

    protected void selectingAuthorizationFunction() {
    }

    protected void startReverseControl() {
    }

    protected void updataRopeConfigSetting() {
    }

    protected void updateRopeMode() {
    }

    @Override // com.huawei.health.ecologydevice.ui.BaseFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        LogUtil.a(TAG, "in onCreate");
        this.mNeedOpenBlueTooth = true;
        ResourceManager.e().e(this);
        if (getArguments() != null) {
            ContentValues contentValues = (ContentValues) getArguments().getParcelable("commonDeviceInfo");
            if (contentValues != null) {
                this.mProductId = contentValues.getAsString("productId");
                this.mUniqueId = contentValues.getAsString("uniqueId");
            }
            LogUtil.c(TAG, "Rope device product id:", this.mProductId, ",mUniqueId:", dku.b(this.mUniqueId));
            ddk ddkVar = new ddk(this.mProductId, this.mUniqueId, this);
            this.mRopeDeviceDataManager = ddkVar;
            this.mProductInfo = ddkVar.j();
            String bondedDeviceAddress = ((DeviceInfoUtilsApi) Services.c("PluginDevice", DeviceInfoUtilsApi.class)).getBondedDeviceAddress(this.mProductId, this.mUniqueId);
            this.mMacAddress = bondedDeviceAddress;
            if (!TextUtils.isEmpty(bondedDeviceAddress)) {
                this.mBatteryKey = knl.d(this.mMacAddress);
            } else {
                this.mBatteryKey = BATTERY_NORMAL_KEY;
            }
            int a2 = SharedPreferenceManager.a(SP_BATTERY_NUM, this.mBatteryKey, 0);
            this.mBatteryValue = a2;
            this.mIsNeedProgressBarVisiable = a2 == 0;
        }
        if (this.mMacAddress != null && getContext() != null) {
            String e = SharedPreferenceManager.e(SP_DEVICE_INFO, this.mMacAddress, "");
            if (!TextUtils.isEmpty(e)) {
                this.mDeviceInformation = (DeviceInformation) new Gson().fromJson(e, DeviceInformation.class);
            }
        }
        handleUpdateProductMap();
        setSportTypeToSharePreference(this.mainActivity, 283);
    }

    @Override // com.huawei.health.ecologydevice.ui.BaseFragment, androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        if (layoutInflater == null) {
            LogUtil.h(TAG, "RopeDeviceIntroductionFragment onCreateView inflater is null");
            FragmentActivity activity = getActivity();
            if (activity != null) {
                activity.finish();
            }
            return null;
        }
        LogUtil.a(TAG, "in onCreateView");
        ViewGroup viewGroup2 = super.onCreateView(layoutInflater, viewGroup, bundle) instanceof ViewGroup ? (ViewGroup) super.onCreateView(layoutInflater, viewGroup, bundle) : null;
        View inflate = layoutInflater.inflate(R.layout.base_rope_device_introduction_layout, viewGroup, false);
        if (viewGroup2 != null) {
            viewGroup2.addView(inflate);
            initView(inflate);
            this.child = layoutInflater.inflate(getLayoutId(), viewGroup, false);
            this.mContainerLayout.addView(this.child);
            initChildView();
            initMarketing();
            initUxConfig(new czb(this.mProductId).c());
        }
        initDeviceInfo();
        if (!TextUtils.isEmpty(this.mUniqueId)) {
            ((HonourDeviceConstantsApi) Services.c("PluginDevice", HonourDeviceConstantsApi.class)).setDevicesUseTime(this.mUniqueId);
        }
        return viewGroup2;
    }

    @Override // com.huawei.health.ecologydevice.ui.BaseFragment, androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        ddr.d().c();
    }

    protected void initUxConfig(czd czdVar) {
        if (czdVar != null) {
            this.mMarketingLayout.setVisibility(czdVar.f() ? 0 : 8);
        }
        initPerformanceView(czdVar);
    }

    private void initPerformanceView(czd czdVar) {
        if (czdVar != null) {
            if (czdVar.b() == null || !czdVar.b().c()) {
                return;
            }
        } else if (!"true".equals(dij.c("isSupportScore", this.mProductId))) {
            return;
        }
        this.mPersonalPerformanceLayout.setVisibility(0);
        this.mPerformanceLayout.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.health.ecologydevice.ui.measure.fragment.BaseRopeIntroductionFragment$$ExternalSyntheticLambda3
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                BaseRopeIntroductionFragment.this.m277x39b4411a(view);
            }
        });
    }

    /* renamed from: lambda$initPerformanceView$0$com-huawei-health-ecologydevice-ui-measure-fragment-BaseRopeIntroductionFragment, reason: not valid java name */
    /* synthetic */ void m277x39b4411a(View view) {
        addFragment(new RopePerformanceHistoryFragment());
        tickBiRopeLevel2PageBrowsing(this.mainActivity, "rope_performance_history");
        ViewClickInstrumentation.clickOnView(view);
    }

    private void initMarketing() {
        int parseInt;
        if (dij.i(this.mProductId)) {
            parseInt = 4037;
        } else {
            try {
                parseInt = Integer.parseInt(dij.c("marketing_position_id", this.mProductId));
            } catch (NumberFormatException unused) {
                LogUtil.b(TAG, "error Marketing PositionId");
                return;
            }
        }
        final MarketingApi marketingApi = (MarketingApi) Services.c("FeatureMarketing", MarketingApi.class);
        Task<Map<Integer, ResourceResultInfo>> resourceResultInfo = marketingApi.getResourceResultInfo(parseInt);
        resourceResultInfo.addOnSuccessListener(new OnSuccessListener() { // from class: com.huawei.health.ecologydevice.ui.measure.fragment.BaseRopeIntroductionFragment$$ExternalSyntheticLambda4
            @Override // com.huawei.hmf.tasks.OnSuccessListener
            public final void onSuccess(Object obj) {
                BaseRopeIntroductionFragment.this.m276x6f067e28(marketingApi, (Map) obj);
            }
        });
        resourceResultInfo.addOnFailureListener(new OnFailureListener() { // from class: com.huawei.health.ecologydevice.ui.measure.fragment.BaseRopeIntroductionFragment$$ExternalSyntheticLambda5
            @Override // com.huawei.hmf.tasks.OnFailureListener
            public final void onFailure(Exception exc) {
                BaseRopeIntroductionFragment.lambda$initMarketing$2(exc);
            }
        });
    }

    /* renamed from: lambda$initMarketing$1$com-huawei-health-ecologydevice-ui-measure-fragment-BaseRopeIntroductionFragment, reason: not valid java name */
    /* synthetic */ void m276x6f067e28(MarketingApi marketingApi, Map map) {
        if (this.mMarketingLayout == null) {
            LogUtil.h(TAG, "initMarketing mMarketingLayout is null");
            return;
        }
        Iterator<View> it = marketingApi.getMarketingViewList(getContext(), marketingApi.filterMarketingRules((Map<Integer, ResourceResultInfo>) map)).iterator();
        while (it.hasNext()) {
            this.mMarketingLayout.addView(it.next());
        }
    }

    static /* synthetic */ void lambda$initMarketing$2(Exception exc) {
        Object[] objArr = new Object[2];
        objArr[0] = "initMarketing onFailure ";
        objArr[1] = exc == null ? "" : exc.getMessage();
        LogUtil.b(TAG, objArr);
    }

    private void initDeviceInfo() {
        Bundle arguments = getArguments();
        if (arguments == null) {
            LogUtil.h(TAG, "getArguments is null");
            return;
        }
        if (arguments.getBoolean(KEY_FROM_SCAN)) {
            this.mEnterType = SCAN;
        } else if (TextUtils.isEmpty(arguments.getString("PAYLOAD_FROM_NFC"))) {
            this.mEnterType = QRCODE;
        } else {
            this.mEnterType = NFC;
        }
        LogUtil.c(TAG, "mEnterType=", this.mEnterType);
    }

    private void initView(View view) {
        this.mCustomTitleBar.setRightButtonVisibility(0);
        Resources resources = BaseApplication.getContext().getResources();
        if (resources != null) {
            this.mCustomTitleBar.setTitleBarBackgroundColor(resources.getColor(R.color._2131299340_res_0x7f090c0c));
        }
        this.mCustomTitleBar.setRightButtonDrawable(this.mainActivity.getResources().getDrawable(R.drawable._2131430194_res_0x7f0b0b32), nsf.h(R.string._2130850635_res_0x7f02334b));
        this.mCustomTitleBar.setRightButtonOnClickListener(new View.OnClickListener() { // from class: com.huawei.health.ecologydevice.ui.measure.fragment.BaseRopeIntroductionFragment$$ExternalSyntheticLambda6
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                BaseRopeIntroductionFragment.this.m278xcd878e8d(view2);
            }
        });
        this.mContainerLayout = (LinearLayout) view.findViewById(R.id.container_layout);
        this.mDeviceImageIv = (ImageView) view.findViewById(R.id.rope_device_image_iv);
        this.mConnectStatusTv = (HealthTextView) view.findViewById(R.id.rope_device_connect_status);
        HealthTextView healthTextView = (HealthTextView) view.findViewById(R.id.rope_device_reconnect_tv);
        this.mReconnectTv = healthTextView;
        healthTextView.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.health.ecologydevice.ui.measure.fragment.BaseRopeIntroductionFragment$$ExternalSyntheticLambda7
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                BaseRopeIntroductionFragment.this.m279xfc38f8ac(view2);
            }
        });
        this.mReconnectLoadingPb = (HealthProgressBar) view.findViewById(R.id.rope_device_reconnect_pb);
        this.mBatteryLayout = (LinearLayout) view.findViewById(R.id.rope_device_battery_layout);
        this.mBatteryIconIv = (ImageView) view.findViewById(R.id.rope_device_battery_iv);
        this.mBatteryValueTv = (HealthTextView) view.findViewById(R.id.rope_device_battery_tv);
        this.mPersonalPerformanceLayout = (LinearLayout) view.findViewById(R.id.personal_performance_layout);
        this.mPerformanceLayout = (RelativeLayout) view.findViewById(R.id.performance_layout);
        this.mMarketingLayout = (LinearLayout) view.findViewById(R.id.rope_skip_introduction_marketing);
    }

    /* renamed from: lambda$initView$3$com-huawei-health-ecologydevice-ui-measure-fragment-BaseRopeIntroductionFragment, reason: not valid java name */
    /* synthetic */ void m278xcd878e8d(View view) {
        showUnbindDeviceMenu();
        ViewClickInstrumentation.clickOnView(view);
    }

    /* renamed from: lambda$initView$4$com-huawei-health-ecologydevice-ui-measure-fragment-BaseRopeIntroductionFragment, reason: not valid java name */
    /* synthetic */ void m279xfc38f8ac(View view) {
        connectToDevice();
        ViewClickInstrumentation.clickOnView(view);
    }

    private void handleUpdateProductMap() {
        if (this.mainActivity == null) {
            LogUtil.h(TAG, "handleUpdateProductMap mainActivity is null");
            return;
        }
        final String e = dij.e(this.mProductId);
        if (cwt.a().c(this.mainActivity, e)) {
            cwt.a().c(true);
            cwt.a().b(this.mKind.name(), e, new DownloadDeviceInfoCallBack() { // from class: com.huawei.health.ecologydevice.ui.measure.fragment.BaseRopeIntroductionFragment.1
                @Override // com.huawei.health.ecologydevice.clouddevice.DownloadDeviceInfoCallBack
                public void onSuccess() {
                    LogUtil.a(BaseRopeIntroductionFragment.TAG, "onSuccess");
                    cwt.a().d(BaseRopeIntroductionFragment.this.mainActivity, e);
                }

                @Override // com.huawei.health.ecologydevice.clouddevice.DownloadDeviceInfoCallBack
                public void onFailure(int i) {
                    LogUtil.a(BaseRopeIntroductionFragment.TAG, "onFailure");
                }

                @Override // com.huawei.health.ecologydevice.clouddevice.DownloadDeviceInfoCallBack
                public void onNetworkError() {
                    LogUtil.a(BaseRopeIntroductionFragment.TAG, "onNetworkError");
                }
            });
        }
    }

    @Override // com.huawei.health.ecologydevice.ui.measure.fragment.ProductFragment, androidx.fragment.app.Fragment
    public void onStart() {
        super.onStart();
        LogUtil.a(TAG, "in onStart");
        onDeviceStateChanged();
        loadResourceFile();
        if (this.mRopeDeviceDataManager == null) {
            LogUtil.h(TAG, "onStart mRopeDeviceDataManager is null");
            return;
        }
        if (this.mRopeHistoryLayout != null) {
            LogUtil.h(TAG, "onStart mRopeHistoryLayout is not null");
            this.mRopeDeviceDataManager.l();
        }
        if (this.mRopeDeviceDataManager.e() != 2 || this.mRopeDeviceHandler == null) {
            return;
        }
        LogUtil.c(TAG, "onStart STATE_CONNECTED");
        this.mRopeDeviceHandler.post(new Runnable() { // from class: com.huawei.health.ecologydevice.ui.measure.fragment.BaseRopeIntroductionFragment$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                BaseRopeIntroductionFragment.this.m280x36270877();
            }
        });
    }

    /* renamed from: lambda$onStart$5$com-huawei-health-ecologydevice-ui-measure-fragment-BaseRopeIntroductionFragment, reason: not valid java name */
    /* synthetic */ void m280x36270877() {
        ddk ddkVar = this.mRopeDeviceDataManager;
        if (ddkVar != null) {
            ddkVar.q();
        }
    }

    @Override // com.huawei.health.ecologydevice.ui.measure.fragment.ProductFragment
    protected void autoConnectDevice() {
        if (this.mRopeDeviceDataManager == null) {
            LogUtil.h(TAG, "mRopeDeviceDataManager is null");
            return;
        }
        this.mMacAddress = ((DeviceInfoUtilsApi) Services.c("PluginDevice", DeviceInfoUtilsApi.class)).getBondedDeviceAddress(this.mProductId, this.mUniqueId);
        this.mRopeDeviceDataManager.c(this.mProductId, this.mUniqueId);
        if (this.mRopeDeviceDataManager.e() == 2) {
            LogUtil.a(TAG, "getBleDeviceState STATE_CONNECTED");
            return;
        }
        CustomViewDialog customViewDialog = this.mConnectionNoteDialog;
        if (customViewDialog != null && customViewDialog.isShowing()) {
            LogUtil.a(TAG, "mConnectionNoteDialog isShowing");
        } else {
            this.mRopeDeviceHandler.sendEmptyMessageDelayed(105, 500L);
        }
    }

    @Override // com.huawei.health.ecologydevice.ui.measure.fragment.ProductFragment, com.huawei.health.ecologydevice.ui.BaseFragment, androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        if (getArguments() == null || !OPCODE_DELETE.equals(getArguments().getString("operateCode", ""))) {
            return;
        }
        LogUtil.a(TAG, "in onResume");
        unbindHaveBindingDevice();
        getArguments().remove("operateCode");
    }

    private void loadResourceFile() {
        RopeDeviceHandler ropeDeviceHandler = this.mRopeDeviceHandler;
        if (ropeDeviceHandler != null) {
            Message obtainMessage = ropeDeviceHandler.obtainMessage();
            obtainMessage.what = 110;
            this.mRopeDeviceHandler.sendMessage(obtainMessage);
        }
    }

    private boolean checkOtaVersion() {
        DeviceInformation deviceInformation;
        if (this.mProductInfo == null || (deviceInformation = this.mDeviceInformation) == null) {
            return false;
        }
        int otaVersion = getOtaVersion(deviceInformation.getSoftwareVersion());
        String c = this.mProductInfo.c("otaVersion");
        if (TextUtils.isEmpty(c)) {
            return false;
        }
        LogUtil.a(TAG, "softwareVersion = ", Integer.valueOf(otaVersion), " productInfoOtaVersion = ", c);
        return otaVersion < getOtaVersion(c);
    }

    private int getOtaVersion(String str) {
        return nsn.e(str.toLowerCase(Locale.ENGLISH).replace(FitRunPlayAudio.PLAY_TYPE_V, "").replace(".", ""));
    }

    public void setOtaRedPoint(boolean z) {
        if (koq.b(this.mMoreMenuList)) {
            LogUtil.b(TAG, "setOtaRedPoint mMoreMenuList is empty");
        } else if (checkOtaVersion() && !this.mMoreMenuList.contains(File.separator) && z) {
            this.mMoreMenuList.add(File.separator);
            this.mMoreMenuList.add(getResources().getString(R.string.IDS_device_wear_home_device_ota_upgrade));
        }
    }

    private void showUnbindDeviceMenu() {
        if (this.mainActivity == null || this.mCustomTitleBar == null) {
            LogUtil.h(TAG, "mainActivity or mCustomTitleBar is null");
            return;
        }
        if (koq.b(this.mMoreMenuList)) {
            LogUtil.b(TAG, "showUnbindDeviceMenu mMoreMenuList is empty");
            return;
        }
        PopViewList.PopViewClickListener popViewClickListener = new PopViewList.PopViewClickListener() { // from class: com.huawei.health.ecologydevice.ui.measure.fragment.BaseRopeIntroductionFragment$$ExternalSyntheticLambda0
            @Override // com.huawei.ui.commonui.popupview.PopViewList.PopViewClickListener
            public final void setOnClick(int i) {
                BaseRopeIntroductionFragment.this.m281x18682477(i);
            }
        };
        this.mPopViewList = new PopViewList(this.mainActivity, this.mCustomTitleBar, this.mMoreMenuList);
        if (!checkOtaVersion()) {
            this.mPopViewList.e(getResources().getString(R.string.IDS_device_wear_home_device_ota_upgrade), 2);
        }
        this.mPopViewList.e(popViewClickListener);
    }

    /* renamed from: lambda$showUnbindDeviceMenu$6$com-huawei-health-ecologydevice-ui-measure-fragment-BaseRopeIntroductionFragment, reason: not valid java name */
    /* synthetic */ void m281x18682477(int i) {
        String str = this.mMoreMenuList.get(i);
        if (TextUtils.equals(str, getResources().getString(R.string.IDS_device_wear_home_device_ota_upgrade))) {
            if (dij.g(this.mProductId)) {
                highRopeOta();
            } else {
                dks.d(this.mainActivity, this.mProductInfo, this.mProductId, this.mUniqueId);
            }
            tickBiRopeLevel2PageBrowsing(this.mainActivity, "OTA");
            return;
        }
        if (TextUtils.equals(str, getResources().getString(R.string.IDS_device_wear_home_delete_device))) {
            unbindHaveBindingDevice();
            return;
        }
        if (TextUtils.equals(str, getResources().getString(R.string.IDS_device_rope_device_info))) {
            goToDeviceInfoFragment();
            return;
        }
        if (TextUtils.equals(str, getResources().getString(R.string._2130845807_res_0x7f02206f))) {
            LogUtil.a(TAG, "collect log");
            this.mRopeDeviceDataManager.i();
        } else {
            if (TextUtils.equals(str, getResources().getString(R.string.IDS_home_rope_device_authorization))) {
                RopeDeviceHandler ropeDeviceHandler = this.mRopeDeviceHandler;
                if (ropeDeviceHandler != null) {
                    ropeDeviceHandler.sendEmptyMessage(116);
                    return;
                }
                return;
            }
            LogUtil.a(TAG, "ProductIntroductionFragment showUnbindDeviceMenu else");
        }
    }

    private void highRopeOta() {
        if (this.mRopeDeviceDataManager == null || this.mainActivity == null) {
            LogUtil.h(TAG, "highRopeOta mRopeDeviceDataManager or mainActivity is null");
        } else if (this.mRopeDeviceDataManager.e() != 2) {
            LogUtil.h(TAG, "highRopeOta disconnected");
            nrh.d(this.mainActivity, getResources().getString(R.string._2130844950_res_0x7f021d16));
        } else {
            this.mRopeDeviceDataManager.p();
            this.mIsOtaUpdate = true;
        }
    }

    private void unbindHaveBindingDevice() {
        LogUtil.a(TAG, "unBindHaveBindDevice to enter");
        final HashMap hashMap = new HashMap(16);
        hashMap.put("click", "1");
        hashMap.put(PluginPayAdapter.KEY_DEVICE_INFO_NAME, dks.e(this.mProductId, this.mRopeDeviceDataManager.f()));
        dif.e(this.mainActivity, new IdialogButtonClickCallback() { // from class: com.huawei.health.ecologydevice.ui.measure.fragment.BaseRopeIntroductionFragment.2
            @Override // com.huawei.health.ecologydevice.callback.IdialogButtonClickCallback
            public void onClick(View view) {
                dds.c().h();
                BaseRopeIntroductionFragment.this.unBindDevice(hashMap);
                ViewClickInstrumentation.clickOnView(view);
            }

            @Override // com.huawei.health.ecologydevice.callback.IdialogButtonClickCallback
            public void onCancelClick(View view) {
                LogUtil.a(BaseRopeIntroductionFragment.TAG, "unbindHaveBindingDevice cancle");
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void unBindDevice(Map<String, Object> map) {
        if (unBindDeviceUniversal(this.mProductId, this.mUniqueId)) {
            ((HealthDeviceEntryApi) Services.c("PluginDevice", HealthDeviceEntryApi.class)).stopMeasure(this.mProductId, this.mUniqueId);
            ixx.d().d(cpp.a(), AnalyticsValue.HEALTH_PLUGIN_DEVICE_UNBIND_SUCCEED_2060014.value(), map, 0);
            ResourceManager.e().f();
            popupFragment(null);
        }
    }

    @Override // com.huawei.health.ecologydevice.ui.measure.fragment.ProductFragment
    public void updateUIDisconnect() {
        LogUtil.a(TAG, "upDateUIDisconnect");
        this.mConnectStatusTv.setText(R.string.IDS_device_rope_device_not_connected);
        this.mReconnectLoadingPb.setVisibility(8);
        this.mBatteryLayout.setVisibility(8);
        this.mReconnectTv.setVisibility(0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void connectToDevice() {
        if (this.mRopeDeviceDataManager == null) {
            LogUtil.h(TAG, "mRopeDeviceDataManager is null");
            return;
        }
        try {
            LogUtil.a(TAG, "connectToDevice enter");
            int d = djt.e().d(this.mainActivity);
            if (d == 0) {
                ResourceManager.e().f();
                popupFragment(null);
                return;
            }
            if (this.mIsBtEnableShowing) {
                LogUtil.a(TAG, "connectToDevice cancle mIsBtEnableShowing =", Boolean.valueOf(this.mIsBtEnableShowing));
                return;
            }
            if (!checkPermission()) {
                LogUtil.a(TAG, "checkPermission cancle mIsBtEnableShowing =", Boolean.valueOf(this.mIsBtEnableShowing));
                return;
            }
            if (d != 1) {
                LogUtil.a(TAG, "judgeBlueTooth cancle mIsBtEnableShowing =", Boolean.valueOf(this.mIsBtEnableShowing));
                openBlueTooth(101);
            } else if (getActivity() != null) {
                LogUtil.a(TAG, "mRopeDeviceDataManager connectToDevice");
                this.mRopeDeviceDataManager.b();
                this.mReconnectionTime++;
                this.mStartConnectTime = System.currentTimeMillis();
            }
        } catch (SecurityException e) {
            LogUtil.b(TAG, "connectToDevice SecurityException:", ExceptionUtils.d(e));
        }
    }

    protected void goToDeviceInfoFragment() {
        Bundle bundle = new Bundle();
        bundle.putParcelable(ROPE_DEVICE_INFO, this.mDeviceInformation);
        bundle.putString("productId", this.mProductId);
        bundle.putString("uniqueId", this.mUniqueId);
        DeviceInfoFragment deviceInfoFragment = new DeviceInfoFragment();
        deviceInfoFragment.setArguments(bundle);
        addFragment(deviceInfoFragment);
    }

    @Override // com.huawei.health.ecologydevice.ui.measure.fragment.ProductFragment, androidx.fragment.app.Fragment
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        LogUtil.a(TAG, "onActivityResult requestCode", Integer.valueOf(i), " resultCode", Integer.valueOf(i2));
        if (i == 101) {
            this.mIsBtEnableShowing = false;
            if (i2 == -1) {
                LogUtil.a(TAG, "REQUEST_ENABLE_BT User access the bt");
                autoConnectDevice();
            } else {
                LogUtil.a(TAG, "REQUEST_ENABLE_BT User dined the bt");
            }
        }
    }

    @Override // com.huawei.health.ecologydevice.callback.RopeDeviceDataListener
    public void onNewDeviceState() {
        RopeDeviceHandler ropeDeviceHandler = this.mRopeDeviceHandler;
        if (ropeDeviceHandler == null || this.mRopeDeviceDataManager == null) {
            return;
        }
        ropeDeviceHandler.sendEmptyMessage(109);
        if (this.mReconnectionTime < 3 || this.mRopeDeviceDataManager.e() != 0) {
            return;
        }
        LogUtil.a(TAG, "mReconnectionTime:", Integer.valueOf(this.mReconnectionTime));
        this.mRopeDeviceHandler.sendEmptyMessage(104);
    }

    @Override // com.huawei.health.ecologydevice.callback.RopeDeviceDataListener
    public void onNewBatteryState() {
        RopeDeviceHandler ropeDeviceHandler = this.mRopeDeviceHandler;
        if (ropeDeviceHandler == null || this.mRopeDeviceDataManager == null) {
            return;
        }
        Message obtainMessage = ropeDeviceHandler.obtainMessage();
        obtainMessage.what = 107;
        obtainMessage.arg1 = this.mRopeDeviceDataManager.a();
        this.mRopeDeviceHandler.sendMessage(obtainMessage);
    }

    @Override // com.huawei.health.ecologydevice.callback.RopeDeviceDataListener
    public void onNewDeviceInfo() {
        ddk ddkVar = this.mRopeDeviceDataManager;
        if (ddkVar != null) {
            this.mDeviceInformation = ddkVar.d();
            String bondedDeviceAddress = ((DeviceInfoUtilsApi) Services.c("PluginDevice", DeviceInfoUtilsApi.class)).getBondedDeviceAddress(this.mProductId, this.mUniqueId);
            this.mMacAddress = bondedDeviceAddress;
            if (TextUtils.isEmpty(bondedDeviceAddress) || getContext() == null) {
                return;
            }
            SharedPreferenceManager.c(SP_DEVICE_INFO, this.mMacAddress, new Gson().toJson(this.mDeviceInformation));
        }
    }

    @Override // com.huawei.health.ecologydevice.callback.RopeDeviceDataListener
    public void onNewLastRope() {
        RopeDeviceHandler ropeDeviceHandler = this.mRopeDeviceHandler;
        if (ropeDeviceHandler != null) {
            ropeDeviceHandler.sendEmptyMessage(106);
        }
    }

    @Override // com.huawei.health.ecologydevice.callback.RopeDeviceDataListener
    public void onRopeMonthDataSuccess() {
        RopeDeviceHandler ropeDeviceHandler = this.mRopeDeviceHandler;
        if (ropeDeviceHandler != null) {
            ropeDeviceHandler.sendEmptyMessage(112);
        }
    }

    @Override // com.huawei.health.ecologydevice.callback.RopeDeviceDataListener
    public void onSetDefaultValue() {
        RopeDeviceHandler ropeDeviceHandler = this.mRopeDeviceHandler;
        if (ropeDeviceHandler != null) {
            Message obtainMessage = ropeDeviceHandler.obtainMessage();
            obtainMessage.what = 111;
            this.mRopeDeviceHandler.sendMessage(obtainMessage);
        }
    }

    @Override // com.huawei.health.ecologydevice.callback.RopeDeviceDataListener
    public void onSetRopeMode() {
        RopeDeviceHandler ropeDeviceHandler = this.mRopeDeviceHandler;
        if (ropeDeviceHandler != null) {
            ropeDeviceHandler.sendEmptyMessage(113);
        }
    }

    @Override // com.huawei.health.ecologydevice.callback.RopeDeviceDataListener
    public void onReverseControl() {
        RopeDeviceHandler ropeDeviceHandler = this.mRopeDeviceHandler;
        if (ropeDeviceHandler != null) {
            ropeDeviceHandler.sendEmptyMessage(114);
        }
    }

    @Override // com.huawei.health.ecologydevice.callback.RopeDeviceDataListener
    public void onRopeConfigSetting() {
        RopeDeviceHandler ropeDeviceHandler = this.mRopeDeviceHandler;
        if (ropeDeviceHandler != null) {
            ropeDeviceHandler.sendEmptyMessage(115);
        }
    }

    @Override // com.huawei.health.ecologydevice.callback.RopeDeviceDataListener
    public void onRopeConfigSwitch() {
        RopeDeviceHandler ropeDeviceHandler = this.mRopeDeviceHandler;
        if (ropeDeviceHandler != null) {
            ropeDeviceHandler.sendEmptyMessage(117);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showConnectionNoteDialog() {
        if (getContext() == null) {
            return;
        }
        if (this.mConnectionNoteDialog == null) {
            View inflate = LayoutInflater.from(cpp.a()).inflate(R.layout.rope_device_connection_note_dialog, (ViewGroup) null);
            ((HealthTextView) inflate.findViewById(R.id.dialog_content)).setText(nsn.ae(getContext()) ? R.string.IDS_device_reopen_bt_pad : R.string.IDS_device_mgr_pair_note_open_bluetooth);
            CustomViewDialog.Builder builder = new CustomViewDialog.Builder(getContext());
            builder.a(getString(R.string.IDS_device_mgr_pair_note_can_not_connect)).czg_(inflate).cze_(R.string._2130841794_res_0x7f0210c2, new View.OnClickListener() { // from class: com.huawei.health.ecologydevice.ui.measure.fragment.BaseRopeIntroductionFragment$$ExternalSyntheticLambda2
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    BaseRopeIntroductionFragment.lambda$showConnectionNoteDialog$7(view);
                }
            });
            CustomViewDialog e = builder.e();
            this.mConnectionNoteDialog = e;
            e.setCancelable(false);
        }
        this.mConnectionNoteDialog.show();
    }

    static /* synthetic */ void lambda$showConnectionNoteDialog$7(View view) {
        LogUtil.a(TAG, "showConnectionNoteDialog onclick PositiveButton");
        ViewClickInstrumentation.clickOnView(view);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onLoadResourceFileSuccess() {
        parseResourceFile();
        parseDeviceImage();
        String title = getTitle(this.mProductId, this.mProductInfo);
        this.mTitle = title;
        setTitle(title);
    }

    private void parseResourceFile() {
        if (this.mProductInfo.e().size() == 0) {
            LogUtil.h(TAG, "productInfo.descriptions.size() == 0");
            return;
        }
        LogUtil.c(TAG, "ResourceFile DeviceId:", this.mProductInfo.g());
        int size = this.mProductInfo.e().size();
        for (int i = 0; i < size; i++) {
            this.mImagePathList.add(dcq.b().a(this.mProductId, this.mProductInfo.e().get(i).e()));
        }
    }

    private void parseDeviceImage() {
        Bitmap bitmap;
        this.mDeviceImageIv.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        if (koq.b(this.mImagePathList)) {
            LogUtil.h(TAG, "showUnbindPairAndCancelBindView mImgPathList is null");
            this.mDeviceImageIv.setImageDrawable(this.mainActivity.getResources().getDrawable(R.drawable._2131430689_res_0x7f0b0d21));
            return;
        }
        String str = this.mImagePathList.get(0);
        if (this.mBitmapLruCache.get(str) == null) {
            bitmap = dcx.TK_(str);
            if (new File(str).exists() && bitmap != null) {
                LogUtil.a(TAG, "showUnbindPairAndCancelBindView cache Image");
                this.mBitmapLruCache.put(str, bitmap);
            }
        } else {
            LogUtil.h(TAG, "showUnbindPairAndCancelBindView load exists Image");
            bitmap = this.mBitmapLruCache.get(str);
        }
        if (bitmap == null) {
            LogUtil.h(TAG, "showUnbindPairAndCancelBindView bitmap is null");
        } else {
            this.mDeviceImageIv.setImageBitmap(bitmap);
        }
    }

    private void setSportTypeToSharePreference(Context context, int i) {
        SharedPreferenceManager.e(context, Integer.toString(20002), "map_tracking_sport_type", Integer.toString(i), new StorageParams());
    }

    private String getTitle(String str, dcz dczVar) {
        String d = dcx.d(str, dczVar.n().b());
        return (!METIS_PRODUCTID.equals(str) || LanguageUtil.ba(cpp.a()) || LanguageUtil.ab(cpp.a()) || LanguageUtil.m(cpp.a())) ? d : HUAWEI_FIT;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onNewBatteryValue(int i) {
        if (checkMacAddress()) {
            return;
        }
        this.mBatteryIconIv.setImageDrawable(getBetteryDrawable(i));
        this.mBatteryValueTv.setText(UnitUtil.e(i, 2, 0));
        this.mBatteryValue = i;
        this.mReconnectLoadingPb.setVisibility(8);
        this.mBatteryLayout.setVisibility(0);
        SharedPreferenceManager.b(SP_BATTERY_NUM, this.mBatteryKey, i);
    }

    protected void onDeviceStateChanged() {
        if (this.mRopeDeviceDataManager == null) {
            return;
        }
        if (checkMacAddress()) {
            LogUtil.a(TAG, "is not the MAC address of the device being connected");
            return;
        }
        int e = this.mRopeDeviceDataManager.e();
        if (e != 0) {
            if (e == 1) {
                changeConnectingUiByStatus();
                return;
            }
            if (e == 2) {
                this.mConnectStatusTv.setText(R.string.IDS_device_rope_device_connected);
                this.mReconnectTv.setVisibility(8);
                if (this.mBatteryValue == 0) {
                    this.mReconnectLoadingPb.setVisibility(0);
                    this.mBatteryLayout.setVisibility(8);
                } else {
                    this.mReconnectLoadingPb.setVisibility(8);
                    this.mBatteryLayout.setVisibility(0);
                    this.mBatteryIconIv.setImageDrawable(getBetteryDrawable(this.mBatteryValue));
                    this.mBatteryValueTv.setText(UnitUtil.e(this.mBatteryValue, 2, 0));
                }
                tickBiNfcConnectRope(getContext(), dko.c(this.mStartConnectTime), 2, this.mReconnectionTime);
                this.mReconnectionTime = 0;
                return;
            }
            if (e != 3) {
                return;
            }
        }
        this.mConnectStatusTv.setText(R.string.IDS_device_rope_device_not_connected);
        this.mReconnectLoadingPb.setVisibility(8);
        this.mBatteryLayout.setVisibility(8);
        this.mReconnectTv.setVisibility(0);
        if (this.mRopeDeviceDataManager.e() != 0 || this.mReconnectionTime <= 0) {
            return;
        }
        tickBiNfcConnectRope(getContext(), dko.c(this.mStartConnectTime), 3, this.mReconnectionTime);
    }

    protected boolean checkMacAddress() {
        if (this.mRopeDeviceDataManager == null) {
            return true;
        }
        return !this.mMacAddress.equals(dds.c().b()) && this.mRopeDeviceDataManager.e() == 2;
    }

    private void changeConnectingUiByStatus() {
        if (this.mIsNeedProgressBarVisiable) {
            this.mConnectStatusTv.setText(R.string.IDS_device_rope_device_connecting);
            this.mReconnectLoadingPb.setVisibility(0);
            this.mReconnectTv.setVisibility(8);
            this.mBatteryLayout.setVisibility(8);
            return;
        }
        this.mConnectStatusTv.setText(R.string.IDS_device_rope_device_connecting);
        this.mReconnectTv.setVisibility(8);
        this.mReconnectLoadingPb.setVisibility(8);
        this.mBatteryLayout.setVisibility(0);
        this.mBatteryIconIv.setImageDrawable(getBetteryDrawable(this.mBatteryValue));
        this.mBatteryValueTv.setText(UnitUtil.e(this.mBatteryValue, 2, 0));
    }

    private Drawable getBetteryDrawable(int i) {
        Drawable cLd_ = nsn.cLd_(i);
        return LanguageUtil.bc(this.mainActivity) ? nrz.cKm_(this.mainActivity, cLd_) : cLd_;
    }

    protected void updataRopeConfigSwitch() {
        SwitchStatusData o = this.mRopeDeviceDataManager.o();
        if (dij.g(this.mProductId) && this.mIsOtaUpdate) {
            if (o != null && o.isOpenBr() && !TextUtils.isEmpty(o.getBrMacAddress())) {
                dij.a(this.mainActivity, o.getBrMacAddress(), "");
            }
            this.mIsOtaUpdate = false;
        }
    }

    private void tickBiNfcConnectRope(Context context, double d, int i, int i2) {
        if (!SCAN.equals(this.mEnterType)) {
            LogUtil.a(TAG, "mEnterType = ", this.mEnterType);
        } else {
            new dko.c().e(d).c(this.mProductId).d("262").b(this.mEnterType).e(i2).i(this.mProductId).e(this.mUniqueId).b(i).b().b(context);
        }
    }

    @Override // com.huawei.health.ecologydevice.ui.BaseFragment
    public boolean onBackPressed() {
        removeCallBack();
        popupFragment(null);
        return false;
    }

    @Override // com.huawei.health.ecologydevice.ui.BaseFragment, androidx.fragment.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        removeCallBack();
        ResourceManager.e().f();
        djt.e().d();
        LogUtil.a(TAG, "BaseRopeIntroductionFragment onDestroy");
    }

    protected void removeCallBack() {
        LogUtil.a(TAG, "removeCallBack");
        RopeDeviceHandler ropeDeviceHandler = this.mRopeDeviceHandler;
        if (ropeDeviceHandler != null) {
            ropeDeviceHandler.removeCallbacksAndMessages(null);
            this.mRopeDeviceHandler = null;
        }
        ddk ddkVar = this.mRopeDeviceDataManager;
        if (ddkVar != null) {
            ddkVar.s();
            this.mRopeDeviceDataManager = null;
        }
    }

    @Override // com.huawei.health.device.manager.ResourceFileListener
    public void onResult(int i, String str) {
        LogUtil.c(TAG, "resultCode = ", Integer.valueOf(i), " resultValue = ", str);
        if (TextUtils.isEmpty(str) || i == -1) {
            LogUtil.h(TAG, "ProResourceFile load failure");
            return;
        }
        if (i == 200) {
            LogUtil.a(TAG, "ProResourceFile load success");
        } else if (i == 300) {
            LogUtil.a(TAG, "GET FILE SIZE SUCCESS");
        } else {
            LogUtil.h(TAG, "unhandled resultCode = ", Integer.valueOf(i));
        }
    }

    protected void tickBiRopeLevel2PageBrowsing(Context context, String str) {
        new dko.c().i(this.mProductId).e(this.mUniqueId).a(str).b().e(context);
    }

    static class RopeDeviceHandler extends Handler {
        private WeakReference<BaseRopeIntroductionFragment> mFragment;

        private RopeDeviceHandler(BaseRopeIntroductionFragment baseRopeIntroductionFragment) {
            this.mFragment = new WeakReference<>(baseRopeIntroductionFragment);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            BaseRopeIntroductionFragment baseRopeIntroductionFragment = this.mFragment.get();
            if (baseRopeIntroductionFragment == null || !baseRopeIntroductionFragment.isAdded() || baseRopeIntroductionFragment.isRemoving() || baseRopeIntroductionFragment.isDetached()) {
                return;
            }
            handMessage(message, baseRopeIntroductionFragment);
        }

        private void handMessage(Message message, BaseRopeIntroductionFragment baseRopeIntroductionFragment) {
            switch (message.what) {
                case 104:
                    baseRopeIntroductionFragment.showConnectionNoteDialog();
                    break;
                case 105:
                    if (baseRopeIntroductionFragment.mRopeDeviceDataManager.e() == 0) {
                        baseRopeIntroductionFragment.connectToDevice();
                        break;
                    }
                    break;
                case 106:
                    baseRopeIntroductionFragment.refreshLastRopeDataUi();
                    break;
                case 107:
                    baseRopeIntroductionFragment.onNewBatteryValue(message.arg1);
                    break;
                case 109:
                    baseRopeIntroductionFragment.onDeviceStateChanged();
                    break;
                case 110:
                    baseRopeIntroductionFragment.onLoadResourceFileSuccess();
                    break;
                case 111:
                    baseRopeIntroductionFragment.initDefaultRopeView();
                    break;
                case 112:
                    baseRopeIntroductionFragment.refreshMonthDataUi();
                    break;
                case 113:
                    baseRopeIntroductionFragment.updateRopeMode();
                    break;
                case 114:
                    baseRopeIntroductionFragment.startReverseControl();
                    break;
                case 115:
                    baseRopeIntroductionFragment.updataRopeConfigSetting();
                    break;
                case 116:
                    baseRopeIntroductionFragment.selectingAuthorizationFunction();
                    break;
                case 117:
                    baseRopeIntroductionFragment.updataRopeConfigSwitch();
                    break;
            }
        }
    }
}
