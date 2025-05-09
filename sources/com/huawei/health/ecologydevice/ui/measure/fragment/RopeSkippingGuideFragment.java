package com.huawei.health.ecologydevice.ui.measure.fragment;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothManager;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.health.R;
import com.huawei.health.device.api.DeviceMainActivityApi;
import com.huawei.health.device.api.VersionVerifyUtilApi;
import com.huawei.health.device.callback.VersionNoSupportCallback;
import com.huawei.health.device.connectivity.comm.MeasurableDevice;
import com.huawei.health.device.constants.WeightConstants;
import com.huawei.health.device.model.HealthDevice;
import com.huawei.health.devicemgr.api.mainprocess.DownloadManagerApi;
import com.huawei.health.devicemgr.business.entity.downloadresouce.DownloadResultCallBack;
import com.huawei.health.ecologydevice.callback.IdialogButtonClickCallback;
import com.huawei.health.ecologydevice.clouddevice.DownloadDeviceInfoCallBack;
import com.huawei.health.ecologydevice.fitness.ScannerController;
import com.huawei.health.ecologydevice.manager.ResourceManager;
import com.huawei.health.ecologydevice.manager.RopeCloudAuthManager;
import com.huawei.health.ecologydevice.ui.BaseFragment;
import com.huawei.health.ecologydevice.ui.measure.fragment.RopeSkippingGuideFragment;
import com.huawei.health.ecologydevice.ui.measure.fragment.device.DeviceBindFailedFragment;
import com.huawei.health.ecologydevice.ui.measure.fragment.device.DeviceConnectingFragment;
import com.huawei.health.ecologydevice.ui.measure.fragment.utils.ProductCreateFactory;
import com.huawei.health.ecologydevice.ui.util.PermissionDialogHelper;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hihealth.dictionary.constants.ProductMap;
import com.huawei.hihealth.dictionary.constants.ProductMapInfo;
import com.huawei.hihealth.dictionary.utils.ProductMapParseUtil;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.utils.PermissionUtil;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.openalliance.ad.constant.AdShowExtras;
import com.huawei.operation.OpAnalyticsConstants;
import com.huawei.ui.commonui.button.HealthButton;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.utils.CustomPermissionAction;
import defpackage.cei;
import defpackage.cpp;
import defpackage.cwt;
import defpackage.dcq;
import defpackage.dcx;
import defpackage.dcz;
import defpackage.ddw;
import defpackage.dfe;
import defpackage.dgw;
import defpackage.did;
import defpackage.dif;
import defpackage.dij;
import defpackage.dja;
import defpackage.dko;
import defpackage.dks;
import defpackage.dku;
import defpackage.koq;
import defpackage.msj;
import defpackage.msn;
import defpackage.msq;
import defpackage.msr;
import defpackage.mst;
import defpackage.nsn;
import health.compact.a.CommonUtil;
import health.compact.a.LanguageUtil;
import health.compact.a.Services;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;

/* loaded from: classes3.dex */
public class RopeSkippingGuideFragment extends BaseFragment {
    private static final int DELAY_500MS = 500;
    private static final int DELAY_TIME = 500;
    private static final int DEVICE_SCAN_REFRESH = 0;
    private static final int DEVICE_SCAN_TIMEOUT = 1;
    private static final int DOWNLOAD_RESOURCE_SUCCESS = 104;
    private static final int DOWNLOAD_TIME_OUT = 108;
    private static final int DOWNLOAD_TIME_OUT_DELAY_30000MS = 30000;
    private static final String HUAWEI_FIT = "HUAWEI FIT";
    private static final String ISBIND_SUCCESS = "isBindSuccess";
    private static final String PRODUCT_ID = "productId";
    private static final String RELEASE_TAG = "EcologyDevice_RopeSkippingGuideFragment";
    private static final String ROPE_DEVICE_CONNECTING = "ropeDeviceConnecting";
    private static final int SCAN_DEVICE = 109;
    private static final String TAG = "PDROPE_RopeSkippingGuideFragment";
    private ActivityResultLauncher mActivityResultLauncherBt;
    private ActivityResultLauncher mActivityResultLauncherLocation;
    private String mDeviceBleName;
    private String mDeviceMac;
    private String mDevicePid;
    private String mEnterType;
    private HealthButton mFinishButton;
    private boolean mIsGoRopeJump;
    private HealthTextView mPairSuccess;
    private HealthTextView mPairing;
    private String mProductId;
    private dcz mProductInfo;
    private ScannerController mScannerController;
    private String mTitle;
    private ImageView mWaitingImg;
    private HealthDevice.HealthDeviceKind mKind = HealthDevice.HealthDeviceKind.HDK_ROPE_SKIPPING;
    private RopeHandler mHandler = new RopeHandler();
    private BluetoothAdapter mBluetoothAdapter = null;
    private boolean mIsBtEnableShowing = false;
    private boolean mIsDownloadFail = false;
    private ArrayList<HealthDevice> mListHealthDevice = new ArrayList<>();
    private String mMainTitleStr = HUAWEI_FIT;
    private ScannerController.ScanResultCallback mResultCallback = new ScannerController.ScanResultCallback() { // from class: com.huawei.health.ecologydevice.ui.measure.fragment.RopeSkippingGuideFragment.1
        @Override // com.huawei.health.ecologydevice.fitness.ScannerController.ScanResultCallback
        public void onResult(int i, HealthDevice healthDevice) {
            if (i == 1) {
                Message obtain = Message.obtain();
                obtain.what = 0;
                obtain.obj = healthDevice;
                RopeSkippingGuideFragment.this.mHandler.sendMessage(obtain);
                RopeSkippingGuideFragment ropeSkippingGuideFragment = RopeSkippingGuideFragment.this;
                ropeSkippingGuideFragment.tickBiNfcConnectRope(ropeSkippingGuideFragment.mainActivity, 2);
                return;
            }
            if (i == 2) {
                Message obtain2 = Message.obtain();
                obtain2.what = 1;
                RopeSkippingGuideFragment.this.mHandler.sendMessage(obtain2);
                RopeSkippingGuideFragment ropeSkippingGuideFragment2 = RopeSkippingGuideFragment.this;
                ropeSkippingGuideFragment2.tickBiNfcConnectRope(ropeSkippingGuideFragment2.mainActivity, 0);
                return;
            }
            if (i == 3) {
                boolean e = dfe.a().e();
                boolean j = dfe.a().j(RopeSkippingGuideFragment.this.mProductId);
                if (e && j) {
                    HealthDevice b = dfe.a().b(RopeSkippingGuideFragment.this.mProductId);
                    Message obtain3 = Message.obtain();
                    obtain3.what = 0;
                    obtain3.obj = b;
                    RopeSkippingGuideFragment.this.mHandler.sendMessage(obtain3);
                    RopeSkippingGuideFragment ropeSkippingGuideFragment3 = RopeSkippingGuideFragment.this;
                    ropeSkippingGuideFragment3.tickBiNfcConnectRope(ropeSkippingGuideFragment3.mainActivity, 0);
                    return;
                }
                return;
            }
            LogUtil.c("other branch", new Object[0]);
        }
    };
    private DownloadResultCallBack mDownloadResultCallback = new DownloadResultCallBack() { // from class: com.huawei.health.ecologydevice.ui.measure.fragment.RopeSkippingGuideFragment.2
        @Override // com.huawei.health.devicemgr.business.entity.downloadresouce.DownloadResultCallBack
        public void setDownloadStatus(int i, int i2) {
            if (i == 0) {
                return;
            }
            LogUtil.a(RopeSkippingGuideFragment.TAG, "setDownloadStatus status: ", Integer.valueOf(i));
            if (i != 1) {
                RopeSkippingGuideFragment.this.sendEmptyMessage(108);
            } else {
                mst.a().b();
                RopeSkippingGuideFragment.this.handleUpdateProductMap();
            }
            ((DownloadManagerApi) Services.c("DownloadDeviceResource", DownloadManagerApi.class)).onDownloadIndexAllDestroy();
        }
    };

    private void initParam(Bundle bundle) {
        if (bundle == null) {
            LogUtil.a(TAG, "bundle is null");
        }
        if (!TextUtils.isEmpty(bundle.getString("PAYLOAD_FROM_NFC"))) {
            this.mIsGoRopeJump = true;
        }
        if (bundle.getBoolean("KEY_TO_GET_START_FROM_QRCODE")) {
            this.mIsGoRopeJump = bundle.getBoolean("KEY_TO_GET_START_FROM_QRCODE");
        }
        this.mDevicePid = bundle.getString("PID_FROM_QRCODE");
        this.mDeviceMac = bundle.getString("BLE_FROM_QRCODE");
        String string = bundle.getString("BLENAME_FROM_QRCODE");
        this.mDeviceBleName = string;
        LogUtil.a(TAG, "RopeSkippingGuideFragment", " mDevicePid=", this.mDevicePid, " mDeviceBleName=", string);
    }

    @Override // com.huawei.health.ecologydevice.ui.BaseFragment, androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        Bundle arguments = getArguments();
        initParam(arguments);
        if (!isNeedScanAndBind()) {
            return super.onCreateView(layoutInflater, viewGroup, bundle);
        }
        if (layoutInflater == null) {
            LogUtil.c(TAG, "onCreateView inflater is null");
            FragmentActivity activity = getActivity();
            if (activity != null) {
                activity.finish();
            }
            return null;
        }
        ViewGroup viewGroup2 = super.onCreateView(layoutInflater, viewGroup, bundle) instanceof ViewGroup ? (ViewGroup) super.onCreateView(layoutInflater, viewGroup, bundle) : null;
        this.mCustomTitleBar.setCustomTitleBarTypeNormal();
        View inflate = layoutInflater.inflate(R.layout.device_rope_skipping_guid, (ViewGroup) null);
        if (viewGroup2 != null) {
            viewGroup2.addView(inflate);
        }
        LogUtil.c(TAG, "onCreateView");
        initView(inflate);
        this.mTitle = getArguments().getString("title");
        this.mCustomTitleBar.setTitleText(this.mTitle);
        registerActivityResultLocation();
        registerActivityResultBt();
        if (arguments != null && arguments.getBoolean(ISBIND_SUCCESS)) {
            new Handler().postDelayed(new Runnable() { // from class: com.huawei.health.ecologydevice.ui.measure.fragment.RopeSkippingGuideFragment$$ExternalSyntheticLambda3
                @Override // java.lang.Runnable
                public final void run() {
                    RopeSkippingGuideFragment.this.m330x7cf8f91();
                }
            }, 500L);
        } else if (arguments != null && arguments.getBoolean("KEY_TO_GET_START_FROM_QRCODE")) {
            initDownLoad();
        } else {
            this.mWaitingImg.setBackgroundResource(R.drawable._2131429881_res_0x7f0b09f9);
        }
        initViewClickListener();
        initDeviceInfo();
        return viewGroup2;
    }

    /* renamed from: lambda$onCreateView$0$com-huawei-health-ecologydevice-ui-measure-fragment-RopeSkippingGuideFragment, reason: not valid java name */
    /* synthetic */ void m330x7cf8f91() {
        this.mPairing.setVisibility(8);
        this.mFinishButton.setVisibility(0);
        this.mPairSuccess.setVisibility(0);
        this.mWaitingImg.setBackgroundResource(R.drawable._2131429882_res_0x7f0b09fa);
    }

    private void initDeviceInfo() {
        Bundle arguments = getArguments();
        if (arguments == null) {
            LogUtil.h(TAG, "getArguments is null");
            return;
        }
        if (TextUtils.isEmpty(arguments.getString("PAYLOAD_FROM_NFC"))) {
            this.mEnterType = "QRCODE";
        } else {
            this.mEnterType = "NFC";
        }
        LogUtil.c(TAG, "mEnterType=", this.mEnterType);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void tickBiNfcConnectRope(Context context, int i) {
        new dko.c().e(0.0d).c(this.mProductId).d("262").b(this.mEnterType).e(0).i(this.mProductId).e(this.mDeviceMac).b(i).b().b(context);
    }

    private boolean isNeedScanAndBind() {
        ProductMapParseUtil.b(BaseApplication.getContext());
        ProductMapInfo d = ProductMap.d(this.mDevicePid);
        if (d != null) {
            this.mProductId = d.h();
        }
        MeasurableDevice bondedDevice = getBondedDevice(this.mProductId);
        if (this.mProductId == null || bondedDevice == null) {
            return true;
        }
        did.c().e(this.mProductId, this.mDeviceMac);
        goRopeDeviceIntroductionFragment(this.mProductId, bondedDevice.getUniqueId(), bondedDevice.getDeviceName());
        return false;
    }

    public Intent createIntent(String str, String str2) {
        LogUtil.a(TAG, "enter createIntent");
        dcz d = ResourceManager.e().d(str);
        if (this.mainActivity == null || d == null) {
            LogUtil.b(TAG, "createIntent: context or productInfo is null");
            return null;
        }
        String d2 = dcx.d(d.t(), d.n().b());
        Intent intent = new Intent();
        intent.setFlags(268435456);
        intent.setAction("SWITCH_PLUGINDEVICE");
        intent.setPackage(BaseApplication.getAppPackage());
        intent.setClassName(BaseApplication.getContext(), "com.huawei.health.device.ui.DeviceMainActivity");
        intent.putExtra("productId", str);
        intent.putExtra("arg1", ROPE_DEVICE_CONNECTING);
        intent.putExtra("DeviceType", d.l().name());
        intent.putExtra("DeviceName", d2);
        intent.putExtra("DeviceIconPath", d.n().d());
        ContentValues contentValues = new ContentValues();
        contentValues.put("uniqueId", str2);
        contentValues.put("productId", str);
        intent.putExtra("commonDeviceInfo", contentValues);
        return intent;
    }

    public void startRopeDeviceConnectingPage(String str, String str2) {
        LogUtil.a(TAG, "startRopeDeviceConnectingPage: productId=", str, ", uniqueId=", dku.b(str2));
        Intent createIntent = createIntent(str, str2);
        if (createIntent == null) {
            LogUtil.b(TAG, "startRopeDeviceConnectingPage: intent is null");
            popupFragment(null);
        } else {
            DeviceConnectingFragment deviceConnectingFragment = new DeviceConnectingFragment();
            deviceConnectingFragment.setArguments(createIntent.getExtras());
            switchFragment((Fragment) null, deviceConnectingFragment);
        }
    }

    private void initDownLoad() {
        if (this.mBluetoothAdapter == null) {
            BluetoothManager bluetoothManager = this.mainActivity.getSystemService("bluetooth") instanceof BluetoothManager ? (BluetoothManager) this.mainActivity.getSystemService("bluetooth") : null;
            if (bluetoothManager == null) {
                LogUtil.h(TAG, "no BT Manager in this phone");
                return;
            }
            this.mBluetoothAdapter = bluetoothManager.getAdapter();
        }
        startDownloadResource();
    }

    private void initView(View view) {
        this.mFinishButton = (HealthButton) view.findViewById(R.id.hw_device_rope_skipping_ok);
        ImageView imageView = (ImageView) view.findViewById(R.id.hw_device_rope_skipping_img_1);
        this.mWaitingImg = imageView;
        imageView.setBackgroundResource(R.drawable._2131429884_res_0x7f0b09fc);
        this.mPairing = (HealthTextView) view.findViewById(R.id.hw_device_rope_skipping_pairing);
        this.mPairSuccess = (HealthTextView) view.findViewById(R.id.hw_device_rope_skipping_paired);
    }

    private void initViewClickListener() {
        this.mFinishButton.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.health.ecologydevice.ui.measure.fragment.RopeSkippingGuideFragment$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                RopeSkippingGuideFragment.this.m328x883bac7f(view);
            }
        });
    }

    /* renamed from: lambda$initViewClickListener$1$com-huawei-health-ecologydevice-ui-measure-fragment-RopeSkippingGuideFragment, reason: not valid java name */
    /* synthetic */ void m328x883bac7f(View view) {
        if (this.mFinishButton.getText().toString().equals(getResources().getString(R.string.IDS_device_show_complete))) {
            jumpToProductIntroductionFragment();
        } else if (this.mFinishButton.getText().toString().equals(getResources().getString(R.string._2130850161_res_0x7f023171))) {
            this.mFinishButton.setVisibility(8);
            reDownloadResource();
        } else {
            LogUtil.a(TAG, "RopeSkippingGuideFragment mFinishButton Text Mismatch");
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroyView() {
        super.onDestroyView();
        if (koq.b(this.mListHealthDevice)) {
            return;
        }
        this.mListHealthDevice.clear();
    }

    @Override // com.huawei.health.ecologydevice.ui.BaseFragment, androidx.fragment.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        this.mHandler.removeCallbacksAndMessages(null);
    }

    @Override // com.huawei.health.ecologydevice.ui.BaseFragment
    public boolean onBackPressed() {
        if (getActivity() == null || !this.mIsDownloadFail) {
            return false;
        }
        getActivity().finish();
        return false;
    }

    private void jumpToProductIntroductionFragment() {
        Bundle arguments = getArguments();
        if (arguments != null) {
            try {
                if (arguments.getBoolean(ISBIND_SUCCESS)) {
                    ContentValues contentValues = (ContentValues) arguments.getParcelable("commonDeviceInfo");
                    Bundle bundle = new Bundle();
                    bundle.putString("productId", arguments.getString("productId"));
                    bundle.putBoolean(ISBIND_SUCCESS, true);
                    bundle.putBoolean(BaseRopeIntroductionFragment.KEY_FROM_SCAN, false);
                    bundle.putParcelable("commonDeviceInfo", contentValues);
                    ProductFragment createProductFragment = ProductCreateFactory.createProductFragment(arguments.getString("productId"));
                    createProductFragment.setArguments(bundle);
                    switchFragment(createProductFragment);
                    return;
                }
            } catch (Exception unused) {
                LogUtil.b(TAG, "jumpToProductIntroductionFragment Exception");
            }
        }
        popupFragment(ProductIntroductionFragment.class);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void goDeviceBindWaitingFragment(HealthDevice healthDevice) {
        if (!isTargetDevice(healthDevice)) {
            LogUtil.h(TAG, "this scan devcie is not target device");
            return;
        }
        this.mScannerController.a();
        if (getActivity() == null) {
            return;
        }
        this.mListHealthDevice.add(healthDevice);
        dgw dgwVar = new dgw();
        dgwVar.a(this.mListHealthDevice);
        ddw.c().b(dgwVar);
        Bundle bundle = new Bundle();
        bundle.putString("productId", this.mProductId);
        bundle.putInt("position", 0);
        bundle.putString("title", this.mTitle);
        bundle.putBoolean("is_go_rope_jump", this.mIsGoRopeJump);
        bundle.putString("DeviceName", healthDevice.getDeviceName());
        bundle.putString("DeviceIconPath", getProductImagePath(this.mProductId));
        bundle.putString("DeviceType", "HDK_ROPE_SKIPPING");
        bundle.putParcelable(AdShowExtras.DOWNLOAD_SOURCE, cwt.a().b());
        ContentValues contentValues = new ContentValues();
        contentValues.put("uniqueId", healthDevice.getUniqueId());
        contentValues.put("productId", this.mProductId);
        bundle.putParcelable("commonDeviceInfo", contentValues);
        Fragment pluginDeviceFragment = ((DeviceMainActivityApi) Services.c("PluginDevice", DeviceMainActivityApi.class)).getPluginDeviceFragment(WeightConstants.FragmentType.DEVICE_BIND_WAITING);
        if (pluginDeviceFragment == null) {
            LogUtil.h(TAG, "nextFragment is null");
        } else {
            pluginDeviceFragment.setArguments(bundle);
            switchFragment(pluginDeviceFragment);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void goDeviceBindFailedFragment() {
        Bundle bundle = new Bundle();
        bundle.putString("productId", this.mProductId);
        bundle.putString("title", this.mTitle);
        bundle.putBoolean(DeviceBindFailedFragment.IS_FROM_BIND, true);
        DeviceBindFailedFragment deviceBindFailedFragment = new DeviceBindFailedFragment();
        deviceBindFailedFragment.setArguments(bundle);
        switchFragment(deviceBindFailedFragment);
        LogUtil.c(TAG, "goDeviceBindFailedFragment Fail to bind device ", bundle);
    }

    private void goRopeDeviceIntroductionFragment(String str, String str2, String str3) {
        if (this.mIsGoRopeJump && !"1".equals(dij.c("pageVersion", this.mProductId))) {
            if (!PermissionDialogHelper.Vy_(this.mainActivity) || !dja.c()) {
                LogUtil.c(TAG, "no bt or permission jump to deviceConnecting page");
                startRopeDeviceConnectingPage(str, str2);
                return;
            } else {
                goToMainRopeTab(str, str2);
                return;
            }
        }
        String string = getArguments() != null ? getArguments().getString("PAYLOAD_FROM_NFC") : "";
        Bundle bundle = new Bundle();
        ContentValues contentValues = new ContentValues();
        contentValues.put("uniqueId", str2);
        contentValues.put("productId", str);
        bundle.putParcelable("commonDeviceInfo", contentValues);
        bundle.putBoolean(ISBIND_SUCCESS, true);
        bundle.putString("PAYLOAD_FROM_NFC", string);
        bundle.putString("BLENAME_FROM_QRCODE", str3);
        Fragment createProductFragment = ProductCreateFactory.createProductFragment(str);
        createProductFragment.setArguments(bundle);
        switchFragment(createProductFragment);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleUpdateProductMap() {
        this.mIsDownloadFail = false;
        showDownloadView();
        sendEmptyDelayMessage(108, OpAnalyticsConstants.H5_LOADING_DELAY);
        cwt a2 = cwt.a();
        cwt.a().c(true);
        a2.b(this.mKind.name(), this.mDevicePid, new DownloadDeviceInfoCallBack() { // from class: com.huawei.health.ecologydevice.ui.measure.fragment.RopeSkippingGuideFragment.3
            @Override // com.huawei.health.ecologydevice.clouddevice.DownloadDeviceInfoCallBack
            public void onSuccess() {
                LogUtil.a(RopeSkippingGuideFragment.TAG, "onSuccess");
                RopeSkippingGuideFragment.this.refreshProductInfo();
                RopeSkippingGuideFragment.this.removeMessage(108);
                RopeSkippingGuideFragment.this.sendEmptyMessage(104);
            }

            @Override // com.huawei.health.ecologydevice.clouddevice.DownloadDeviceInfoCallBack
            public void onFailure(int i) {
                LogUtil.a(RopeSkippingGuideFragment.TAG, "onFailure");
                RopeSkippingGuideFragment.this.sendEmptyMessage(108);
            }

            @Override // com.huawei.health.ecologydevice.clouddevice.DownloadDeviceInfoCallBack
            public void onNetworkError() {
                LogUtil.a(RopeSkippingGuideFragment.TAG, "onNetworkError");
                RopeSkippingGuideFragment.this.sendEmptyMessage(108);
            }
        });
    }

    private void startDownloadResource() {
        setFirstDownloadFlag();
        DownloadManagerApi downloadManagerApi = (DownloadManagerApi) Services.c("DownloadDeviceResource", DownloadManagerApi.class);
        if (!CommonUtil.aa(this.mainActivity)) {
            LogUtil.h(TAG, "startDownloadResource net is error");
            sendEmptyMessage(108);
        } else {
            downloadManagerApi.addDownloadIndexAllCallBack(this.mDownloadResultCallback);
            ReleaseLogUtil.e(RELEASE_TAG, "downLoad index_all");
            downloadManagerApi.downloadIndexAll();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void refreshProductInfo() {
        ProductMapInfo d = ProductMap.d(this.mDevicePid);
        if (d != null) {
            this.mProductId = d.h();
        }
        dcz d2 = ResourceManager.e().d(this.mProductId);
        if (d2 == null) {
            LogUtil.h(TAG, "handlerToRefresh productInfo is null");
        } else {
            this.mProductInfo = d2;
        }
    }

    private void reDownloadResource() {
        if (CommonUtil.aa(BaseApplication.getContext())) {
            this.mIsDownloadFail = false;
            showDownloadView();
            sendEmptyDelayMessage(108, OpAnalyticsConstants.H5_LOADING_DELAY);
            startDownloadResource();
            return;
        }
        sendEmptyMessage(108);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showBondingView() {
        this.mPairing.setText(R.string._2130841387_res_0x7f020f2b);
        this.mTitle = getTitle();
        this.mCustomTitleBar.setTitleText(this.mTitle);
        this.mFinishButton.setVisibility(8);
        this.mFinishButton.setText(R.string.IDS_device_show_complete);
        LogUtil.a(TAG, "showBondingView mTitle ", this.mTitle);
    }

    private void showDownloadView() {
        this.mPairing.setText(R.string.IDS_device_download_resource);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showDownloadFail() {
        this.mIsDownloadFail = true;
        this.mPairing.setText(R.string._2130841543_res_0x7f020fc7);
        this.mFinishButton.setVisibility(0);
        this.mFinishButton.setText(R.string._2130850161_res_0x7f023171);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleCancel() {
        LogUtil.a(TAG, "enter handleCancel");
        Iterator<msq> it = msn.c().b().iterator();
        while (it.hasNext()) {
            msq next = it.next();
            if (dks.h(this.mProductId)) {
                msj.a().b(next);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendEmptyMessage(int i) {
        RopeHandler ropeHandler = this.mHandler;
        if (ropeHandler != null) {
            ropeHandler.sendEmptyMessage(i);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendEmptyDelayMessage(int i, long j) {
        RopeHandler ropeHandler = this.mHandler;
        if (ropeHandler != null) {
            ropeHandler.sendEmptyMessageDelayed(i, j);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void removeMessage(int i) {
        RopeHandler ropeHandler = this.mHandler;
        if (ropeHandler != null) {
            ropeHandler.removeMessages(i);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void finishFragment() {
        popupFragment(null);
    }

    private void setFirstDownloadFlag() {
        RopeCloudAuthManager.d(this.mProductId);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void judgingBluetoothOnOff() {
        if (this.mBluetoothAdapter == null) {
            LogUtil.h(TAG, "no BT in this phone");
            finishFragment();
            return;
        }
        this.mScannerController = new ScannerController(this.mProductInfo, this.mKind.name());
        try {
            boolean z = this.mIsBtEnableShowing;
            if (z) {
                LogUtil.a(TAG, "judgingBluetoothOnOff mIsBtEnableShowing", Boolean.valueOf(z));
            } else {
                if (this.mBluetoothAdapter.isEnabled()) {
                    return;
                }
                LogUtil.a(TAG, "judgingBluetoothOnOff enableBluetooth");
                this.mIsBtEnableShowing = true;
                dja.c(this.mActivityResultLauncherBt, "android.bluetooth.adapter.action.REQUEST_ENABLE");
            }
        } catch (SecurityException e) {
            LogUtil.b(TAG, "judgingBluetoothOnOff SecurityException:", ExceptionUtils.d(e));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void openGpsLocation() {
        LogUtil.a(TAG, "openGpsLocation jump to GPS Setting");
        dif.Vp_(this.mainActivity, isAdded(), new IdialogButtonClickCallback() { // from class: com.huawei.health.ecologydevice.ui.measure.fragment.RopeSkippingGuideFragment.4
            @Override // com.huawei.health.ecologydevice.callback.IdialogButtonClickCallback
            public void onClick(View view) {
                dja.c(RopeSkippingGuideFragment.this.mActivityResultLauncherLocation, "android.settings.LOCATION_SOURCE_SETTINGS");
                ViewClickInstrumentation.clickOnView(view);
            }

            @Override // com.huawei.health.ecologydevice.callback.IdialogButtonClickCallback
            public void onCancelClick(View view) {
                LogUtil.a(RopeSkippingGuideFragment.TAG, "openGpsLocation onCancelClick");
                RopeSkippingGuideFragment.this.finishFragment();
            }
        });
    }

    private void registerActivityResultLocation() {
        this.mActivityResultLauncherLocation = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback() { // from class: com.huawei.health.ecologydevice.ui.measure.fragment.RopeSkippingGuideFragment$$ExternalSyntheticLambda2
            @Override // androidx.activity.result.ActivityResultCallback
            public final void onActivityResult(Object obj) {
                RopeSkippingGuideFragment.this.m332xae728c37((ActivityResult) obj);
            }
        });
    }

    /* renamed from: lambda$registerActivityResultLocation$2$com-huawei-health-ecologydevice-ui-measure-fragment-RopeSkippingGuideFragment, reason: not valid java name */
    /* synthetic */ void m332xae728c37(ActivityResult activityResult) {
        judgingBluetoothOnOff();
        if (this.mIsBtEnableShowing) {
            return;
        }
        LogUtil.a(TAG, "registerActivityResultLocation start SCAN_DEVICE");
        sendEmptyDelayMessage(109, 500L);
    }

    private void registerActivityResultBt() {
        this.mActivityResultLauncherBt = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback() { // from class: com.huawei.health.ecologydevice.ui.measure.fragment.RopeSkippingGuideFragment$$ExternalSyntheticLambda0
            @Override // androidx.activity.result.ActivityResultCallback
            public final void onActivityResult(Object obj) {
                RopeSkippingGuideFragment.this.m331xac706a15((ActivityResult) obj);
            }
        });
    }

    /* renamed from: lambda$registerActivityResultBt$3$com-huawei-health-ecologydevice-ui-measure-fragment-RopeSkippingGuideFragment, reason: not valid java name */
    /* synthetic */ void m331xac706a15(ActivityResult activityResult) {
        LogUtil.a(TAG, "registerForActivityResult result=", activityResult);
        this.mIsBtEnableShowing = false;
        if (activityResult.getResultCode() == 0) {
            finishFragment();
        } else {
            LogUtil.a(TAG, "registerActivityResultBt start SCAN_DEVICE");
            sendEmptyDelayMessage(109, 500L);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void checkPermission() {
        PermissionUtil.PermissionType d = PermissionDialogHelper.d();
        PermissionDialogHelper.VB_(getActivity(), d, new AnonymousClass5(this.mainActivity, d));
    }

    /* renamed from: com.huawei.health.ecologydevice.ui.measure.fragment.RopeSkippingGuideFragment$5, reason: invalid class name */
    class AnonymousClass5 extends CustomPermissionAction {
        final /* synthetic */ PermissionUtil.PermissionType val$permissionType;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass5(Context context, PermissionUtil.PermissionType permissionType) {
            super(context);
            this.val$permissionType = permissionType;
        }

        @Override // com.huawei.hwcommonmodel.utils.permission.PermissionsResultAction
        public void onGranted() {
            if (this.val$permissionType == PermissionUtil.PermissionType.LOCATION && !dja.VG_(RopeSkippingGuideFragment.this.mainActivity)) {
                LogUtil.a(RopeSkippingGuideFragment.TAG, "Permission GpsClose is close");
                RopeSkippingGuideFragment.this.openGpsLocation();
                return;
            }
            LogUtil.a(RopeSkippingGuideFragment.TAG, "checkPermission judgingBluetoothOnOff");
            RopeSkippingGuideFragment.this.judgingBluetoothOnOff();
            if (RopeSkippingGuideFragment.this.mIsBtEnableShowing) {
                return;
            }
            LogUtil.a(RopeSkippingGuideFragment.TAG, "checkPermission start SCAN_DEVICE");
            RopeSkippingGuideFragment.this.sendEmptyDelayMessage(109, 500L);
        }

        @Override // com.huawei.ui.commonui.utils.CustomPermissionAction, com.huawei.hwcommonmodel.utils.permission.PermissionsResultAction
        public void onDenied(String str) {
            super.onDenied(str);
            RopeSkippingGuideFragment.this.finishFragment();
        }

        @Override // com.huawei.ui.commonui.utils.CustomPermissionAction, com.huawei.hwcommonmodel.utils.permission.PermissionsResultAction
        public void onForeverDenied(PermissionUtil.PermissionType permissionType) {
            LogUtil.h(RopeSkippingGuideFragment.TAG, "permission onForeverDenied");
            nsn.cLK_(RopeSkippingGuideFragment.this.mainActivity, permissionType, null, new View.OnClickListener() { // from class: com.huawei.health.ecologydevice.ui.measure.fragment.RopeSkippingGuideFragment$5$$ExternalSyntheticLambda0
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    RopeSkippingGuideFragment.AnonymousClass5.this.m333x255b5e4b(view);
                }
            }, new View.OnClickListener() { // from class: com.huawei.health.ecologydevice.ui.measure.fragment.RopeSkippingGuideFragment$5$$ExternalSyntheticLambda1
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    RopeSkippingGuideFragment.AnonymousClass5.this.m334x5d4b44c(view);
                }
            });
        }

        /* renamed from: lambda$onForeverDenied$0$com-huawei-health-ecologydevice-ui-measure-fragment-RopeSkippingGuideFragment$5, reason: not valid java name */
        /* synthetic */ void m333x255b5e4b(View view) {
            RopeSkippingGuideFragment.this.finishFragment();
            ViewClickInstrumentation.clickOnView(view);
        }

        /* renamed from: lambda$onForeverDenied$1$com-huawei-health-ecologydevice-ui-measure-fragment-RopeSkippingGuideFragment$5, reason: not valid java name */
        /* synthetic */ void m334x5d4b44c(View view) {
            RopeSkippingGuideFragment.this.finishFragment();
            ViewClickInstrumentation.clickOnView(view);
        }
    }

    private MeasurableDevice getBondedDevice(String str) {
        ArrayList<MeasurableDevice> bondedDevices = cei.b().getBondedDevices(str, false);
        if (koq.b(bondedDevices)) {
            return null;
        }
        Iterator<MeasurableDevice> it = bondedDevices.iterator();
        while (it.hasNext()) {
            MeasurableDevice next = it.next();
            if (isTargetDevice(next)) {
                return next;
            }
        }
        return null;
    }

    private boolean isTargetDevice(HealthDevice healthDevice) {
        return (!TextUtils.isEmpty(this.mDeviceMac) && this.mDeviceMac.equals(healthDevice.getAddress())) || (!TextUtils.isEmpty(this.mDeviceBleName) && this.mDeviceBleName.equals(healthDevice.getDeviceName()));
    }

    private String getTitle() {
        dcz dczVar = this.mProductInfo;
        if (dczVar != null) {
            this.mMainTitleStr = dcx.d(this.mProductId, dczVar.n().b());
            if (!LanguageUtil.ba(cpp.a()) && !LanguageUtil.ab(cpp.a()) && !LanguageUtil.m(cpp.a())) {
                this.mMainTitleStr = HUAWEI_FIT;
            }
        }
        return this.mMainTitleStr;
    }

    private String getProductImagePath(String str) {
        dcz d = ResourceManager.e().d(str);
        if (d == null || d.e() == null || d.e().get(0) == null) {
            LogUtil.a(TAG, "productInfo is null");
            return "";
        }
        return dcq.b().a(str, d.e().get(0).e());
    }

    static class RopeHandler extends Handler {
        private WeakReference<RopeSkippingGuideFragment> mWeakReference;

        private RopeHandler(RopeSkippingGuideFragment ropeSkippingGuideFragment) {
            this.mWeakReference = new WeakReference<>(ropeSkippingGuideFragment);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            RopeSkippingGuideFragment ropeSkippingGuideFragment = this.mWeakReference.get();
            if (ropeSkippingGuideFragment == null || !ropeSkippingGuideFragment.isAdded() || ropeSkippingGuideFragment.isRemoving() || ropeSkippingGuideFragment.isDetached()) {
                LogUtil.c(RopeSkippingGuideFragment.TAG, "fragment isAdded");
                return;
            }
            int i = message.what;
            if (i == 0) {
                if (message.obj instanceof HealthDevice) {
                    ropeSkippingGuideFragment.goDeviceBindWaitingFragment((HealthDevice) message.obj);
                    return;
                }
                return;
            }
            if (i == 1) {
                ReleaseLogUtil.e(RopeSkippingGuideFragment.RELEASE_TAG, "DEVICE_SCAN_TIMEOUT mList isEmpty ", Boolean.valueOf(ropeSkippingGuideFragment.mListHealthDevice.isEmpty()));
                if (ropeSkippingGuideFragment.mListHealthDevice.isEmpty()) {
                    ropeSkippingGuideFragment.goDeviceBindFailedFragment();
                    return;
                }
                return;
            }
            if (i == 104) {
                ReleaseLogUtil.e(RopeSkippingGuideFragment.RELEASE_TAG, "DOWNLOAD_RESOURCE_SUCCESS");
                if (ropeSkippingGuideFragment.needUpdateAppVersion()) {
                    return;
                }
                ropeSkippingGuideFragment.showBondingView();
                ropeSkippingGuideFragment.checkPermission();
                return;
            }
            if (i == 108) {
                ReleaseLogUtil.d(RopeSkippingGuideFragment.RELEASE_TAG, "DOWNLOAD_TIME_OUT");
                ropeSkippingGuideFragment.handleCancel();
                ropeSkippingGuideFragment.showDownloadFail();
            } else if (i == 109) {
                ReleaseLogUtil.e(RopeSkippingGuideFragment.TAG, "SCAN_DEVICE");
                ropeSkippingGuideFragment.startBinding();
            } else {
                LogUtil.h(RopeSkippingGuideFragment.TAG, "not have this case");
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startBinding() {
        ScannerController scannerController = this.mScannerController;
        if (scannerController == null) {
            return;
        }
        scannerController.a(this.mResultCallback);
        this.mScannerController.c();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean needUpdateAppVersion() {
        VersionVerifyUtilApi versionVerifyUtilApi = (VersionVerifyUtilApi) Services.c("PluginDevice", VersionVerifyUtilApi.class);
        if (!msr.c.containsKey(this.mKind.name()) || versionVerifyUtilApi.isPublishVersion(this.mProductId, this.mKind.name())) {
            return false;
        }
        LogUtil.a(TAG, "current device needs to update the app version, mProductId = ", this.mProductId);
        versionVerifyUtilApi.noSupportDevice(getContext(), getActivity(), new VersionNoSupportCallback() { // from class: com.huawei.health.ecologydevice.ui.measure.fragment.RopeSkippingGuideFragment$$ExternalSyntheticLambda4
            @Override // com.huawei.health.device.callback.VersionNoSupportCallback
            public final void onDialogClose() {
                RopeSkippingGuideFragment.this.m329xec6f306b();
            }
        });
        return true;
    }

    /* renamed from: lambda$needUpdateAppVersion$4$com-huawei-health-ecologydevice-ui-measure-fragment-RopeSkippingGuideFragment, reason: not valid java name */
    /* synthetic */ void m329xec6f306b() {
        popupFragment(null);
    }
}
