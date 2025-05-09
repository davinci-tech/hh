package com.huawei.health.ecologydevice.ui.measure.fragment;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothManager;
import android.content.ActivityNotFoundException;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import com.huawei.featureuserprofile.route.hwgpxmodel.Wpt;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.health.R;
import com.huawei.health.device.api.DeviceDataBaseHelperApi;
import com.huawei.health.device.api.HonourDeviceConstantsApi;
import com.huawei.health.device.api.MeasureKitManagerApi;
import com.huawei.health.device.api.VersionVerifyUtilApi;
import com.huawei.health.device.model.HealthDevice;
import com.huawei.health.device.open.BleMeasureController;
import com.huawei.health.device.open.MeasureController;
import com.huawei.health.device.open.MeasureKit;
import com.huawei.health.devicemgr.api.mainprocess.DownloadManagerApi;
import com.huawei.health.devicemgr.business.entity.downloadresouce.DownloadResultCallBack;
import com.huawei.health.ecologydevice.callback.IdialogButtonClickCallback;
import com.huawei.health.ecologydevice.callback.NemoDeviceCallback;
import com.huawei.health.ecologydevice.clouddevice.DownloadDeviceInfoCallBack;
import com.huawei.health.ecologydevice.manager.MassageGunConfig;
import com.huawei.health.ecologydevice.manager.ResourceManager;
import com.huawei.health.ecologydevice.open.HdpMeasureController;
import com.huawei.health.ecologydevice.ui.BaseFragment;
import com.huawei.health.ecologydevice.ui.measure.fragment.NfcOrQrCodeGuideFragment;
import com.huawei.health.ecologydevice.ui.measure.fragment.utils.BluetoothUtil;
import com.huawei.health.ecologydevice.ui.measure.fragment.utils.DeviceStateCallback;
import com.huawei.health.ecologydevice.ui.measure.fragment.utils.ProductCreateFactory;
import com.huawei.health.ecologydevice.ui.util.PermissionDialogHelper;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hihealth.dictionary.constants.ProductMap;
import com.huawei.hihealth.dictionary.constants.ProductMapInfo;
import com.huawei.hihealth.dictionary.utils.ProductMapParseUtil;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.utils.PermissionUtil;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.openalliance.ad.db.bean.EventMonitorRecord;
import com.huawei.operation.OpAnalyticsConstants;
import com.huawei.operation.ble.BleConstants;
import com.huawei.ui.commonui.dialog.CustomViewDialog;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.progressbar.HealthProgressBar;
import com.huawei.ui.commonui.utils.CustomPermissionAction;
import defpackage.cei;
import defpackage.cpp;
import defpackage.cve;
import defpackage.cwt;
import defpackage.cxh;
import defpackage.dcg;
import defpackage.dcp;
import defpackage.dcq;
import defpackage.dcx;
import defpackage.dcz;
import defpackage.dew;
import defpackage.dif;
import defpackage.dij;
import defpackage.dja;
import defpackage.dko;
import defpackage.dkq;
import defpackage.dks;
import defpackage.gnm;
import defpackage.koq;
import defpackage.msj;
import defpackage.msn;
import defpackage.msq;
import defpackage.msr;
import defpackage.mst;
import defpackage.msx;
import defpackage.nsn;
import health.compact.a.CommonUtil;
import health.compact.a.EzPluginManager;
import health.compact.a.Services;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.lang.ref.WeakReference;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/* loaded from: classes3.dex */
public class NfcOrQrCodeGuideFragment extends BaseFragment implements View.OnClickListener {
    private static final int DELAY_500MS = 500;
    private static final int DOWNLOAD_NO_RESOURCE = 112;
    private static final int DOWNLOAD_RESOURCE_SUCCESS = 104;
    private static final int DOWNLOAD_TIME_OUT = 108;
    private static final int DOWNLOAD_TIME_OUT_DELAY_30000MS = 30000;
    private static final int ENABLE_BT_GET_BLUETOOTH_NAME = 1;
    private static final int ENABLE_BT_SCAN_DEVICE = 2;
    private static final String FROM_SMART_LIFE_TYPE = "FROM_SMART_LIFE";
    private static final String IMAGE_NAME = "hw_meter_bind_2";
    private static final int REFRESHED_PRODUCT_INFO = 110;
    private static final int REQUEST_ENABLE_BT = 2;
    private static final int REQUEST_ENABLE_BT_TO_H5 = 4;
    private static final int REQUEST_GPS_LOCATION = 3;
    private static final int SCAN_DEVICE = 109;
    private static final int STATE_CHANGED = 106;
    private static final int SWITCH_FRAGMENT = 111;
    private static final String TAG = "NfcOrQrCodeGuideFragment";
    private static final int TO_FRESH = 105;
    private static final Set<String> TYPE_BLUETOOTH_CONNECT_NOT_NEED = new HashSet(10);
    private BluetoothDevice mBluetoothDevice;
    private BluetoothUtil mBluetoothUtil;
    private Bundle mBundle;
    private int mCurrentStatus;
    private ImageView mDefaultDeviceImage;
    private cxh mDevice;
    private HealthTextView mDeviceBondStatus;
    private int mDeviceId;
    private ImageView mDeviceImage;
    private String mDeviceMacAddress;
    private String mDeviceName;
    private String mDevicePin;
    private String mDeviceType;
    private ImageView mDialogImageView;
    private int mEnableBluetoothStatus;
    private String mEnterType;
    private HealthTextView mFirstConnectFailedTv;
    private HealthDevice mHealthDevic;
    private String mKind;
    private String mMeasureKit;
    private int mNewStatus;
    private String mPayload;
    private String mProductId;
    private dcz mProductInfo;
    private HealthProgressBar mReBondLoadingPb;
    private HealthTextView mReBondTv;
    private int mReconnectCount;
    private String mSmartProductId;
    private long mStartConnectTime;
    private boolean mIsBtEnableShowing = false;
    private CustomViewDialog mDialogForConnectFail = null;
    private CustomViewDialog mCustomViewDialog = null;
    private H5DeviceHandler mH5DeviceHandler = new H5DeviceHandler();
    private DeviceStateCallback deviceStateCallback = new DeviceStateCallback() { // from class: com.huawei.health.ecologydevice.ui.measure.fragment.NfcOrQrCodeGuideFragment.1
        @Override // com.huawei.health.ecologydevice.ui.measure.fragment.utils.DeviceStateCallback
        public void onNotify(int i, int i2, int i3, HealthDevice healthDevice) {
            LogUtil.a(NfcOrQrCodeGuideFragment.TAG, "onNotify: status = ", Integer.valueOf(i), "newStatus =", Integer.valueOf(i2), "reconnectCount =", Integer.valueOf(i3));
            NfcOrQrCodeGuideFragment.this.mCurrentStatus = i;
            NfcOrQrCodeGuideFragment.this.mReconnectCount = i3;
            NfcOrQrCodeGuideFragment.this.mNewStatus = i2;
            NfcOrQrCodeGuideFragment.this.mHealthDevic = healthDevice;
            NfcOrQrCodeGuideFragment.this.sendEmptyMessage(106);
        }
    };
    private DownloadResultCallBack mDownloadResultCallback = new DownloadResultCallBack() { // from class: com.huawei.health.ecologydevice.ui.measure.fragment.NfcOrQrCodeGuideFragment$$ExternalSyntheticLambda3
        @Override // com.huawei.health.devicemgr.business.entity.downloadresouce.DownloadResultCallBack
        public final void setDownloadStatus(int i, int i2) {
            NfcOrQrCodeGuideFragment.this.m305x29cd5262(i, i2);
        }
    };
    private final NemoDeviceCallback nemoDeviceCallback = new NemoDeviceCallback() { // from class: com.huawei.health.ecologydevice.ui.measure.fragment.NfcOrQrCodeGuideFragment.2
        @Override // com.huawei.health.ecologydevice.callback.NemoDeviceCallback
        public void bondSuccess(BluetoothDevice bluetoothDevice) {
            LogUtil.a(NfcOrQrCodeGuideFragment.TAG, "NomeDeviceManager bondSuccess");
            new dcp().d(NfcOrQrCodeGuideFragment.this.getActivity(), bluetoothDevice.getAddress());
            if (NfcOrQrCodeGuideFragment.this.getActivity() != null) {
                NfcOrQrCodeGuideFragment.this.getActivity().finish();
            }
        }

        @Override // com.huawei.health.ecologydevice.callback.NemoDeviceCallback
        public void bondError(int i) {
            if (i == 1) {
                LogUtil.h(NfcOrQrCodeGuideFragment.TAG, "NomeDeviceManager foundTimeout");
                NfcOrQrCodeGuideFragment.this.mCurrentStatus = 13;
                NfcOrQrCodeGuideFragment.this.sendEmptyMessage(106);
            } else {
                if (i == 2) {
                    LogUtil.h(NfcOrQrCodeGuideFragment.TAG, "NomeDeviceManager bondError");
                    NfcOrQrCodeGuideFragment.this.mCurrentStatus = 14;
                    NfcOrQrCodeGuideFragment.this.sendEmptyMessage(106);
                    return;
                }
                LogUtil.h(NfcOrQrCodeGuideFragment.TAG, "NomeDeviceManager other bindState:", Integer.valueOf(i));
            }
        }
    };

    /* renamed from: lambda$new$0$com-huawei-health-ecologydevice-ui-measure-fragment-NfcOrQrCodeGuideFragment, reason: not valid java name */
    /* synthetic */ void m305x29cd5262(int i, int i2) {
        if (i == 0) {
            return;
        }
        LogUtil.a(TAG, "setDownloadStatus status: ", Integer.valueOf(i));
        ((DownloadManagerApi) Services.c("DownloadDeviceResource", DownloadManagerApi.class)).onDownloadIndexAllDestroy();
        if (i == 1) {
            mst.a().b();
            checkDeviceType();
        } else {
            sendEmptyMessage(108);
        }
    }

    private void checkDeviceType() {
        if (TextUtils.isEmpty(this.mProductId) && ProductCreateFactory.getProductKind(HealthDevice.HealthDeviceKind.getHealthDeviceKind(this.mDeviceType)) == 1) {
            if (TextUtils.isEmpty(this.mDeviceName)) {
                H5DeviceHandler h5DeviceHandler = this.mH5DeviceHandler;
                if (h5DeviceHandler == null) {
                    LogUtil.h(TAG, "checkDeviceType mH5DeviceHandler is null:");
                    return;
                } else {
                    h5DeviceHandler.post(new Runnable() { // from class: com.huawei.health.ecologydevice.ui.measure.fragment.NfcOrQrCodeGuideFragment$$ExternalSyntheticLambda0
                        @Override // java.lang.Runnable
                        public final void run() {
                            NfcOrQrCodeGuideFragment.this.m304x8d91e549();
                        }
                    });
                    return;
                }
            }
            getProductIdByBluetoothName(this.mDeviceName);
            updateIndexAndProductMap();
            return;
        }
        LogUtil.a(TAG, "checkDeviceType DeviceType is not sport device:");
        updateIndexAndProductMap();
    }

    /* renamed from: lambda$checkDeviceType$1$com-huawei-health-ecologydevice-ui-measure-fragment-NfcOrQrCodeGuideFragment, reason: not valid java name */
    /* synthetic */ void m304x8d91e549() {
        judgingBluetoothOnOff(1);
    }

    private void updateIndexAndProductMap() {
        LogUtil.a(TAG, "updateIndexAndProductMap");
        if (TextUtils.isEmpty(this.mSmartProductId) && ProductCreateFactory.getProductKind(HealthDevice.HealthDeviceKind.getHealthDeviceKind(this.mDeviceType)) == 1) {
            startIndoorEquipRunningActByNfc();
        } else {
            if (jumpByIndoorEquipment()) {
                return;
            }
            handleUpdateProductMap();
        }
    }

    private boolean jumpByIndoorEquipment() {
        if (ProductCreateFactory.getProductKind(HealthDevice.HealthDeviceKind.getHealthDeviceKind(this.mDeviceType)) == 1) {
            List<msx> c = mst.a().c(this.mProductId);
            if (koq.b(c)) {
                return false;
            }
            LogUtil.a(TAG, "getJumpToProductPage", Integer.valueOf(c.get(0).g()));
            if (c.get(0).g() == 0) {
                startIndoorEquipRunningActByNfc();
                return true;
            }
        }
        return false;
    }

    private void startIndoorEquipRunningActByNfc() {
        FragmentActivity activity = getActivity();
        if (activity == null) {
            return;
        }
        Intent intent = new Intent();
        intent.putExtra("PID_FROM_QRCODE", this.mSmartProductId);
        intent.putExtra("PROTOCOL_FROM_QRCODE", dij.f(this.mPayload) ? "2" : "1");
        String str = this.mDeviceMacAddress;
        if (str != null) {
            intent.putExtra("BLE_FROM_QRCODE", str.replace(":", ""));
        }
        intent.putExtra("DEVICE_TYPE_INDEX", this.mDeviceType);
        intent.putExtra("BLENAME_FROM_QRCODE", this.mDeviceName);
        intent.setClassName(BaseApplication.getAppPackage(), "com.huawei.indoorequip.activity.IndoorEquipConnectedActivity");
        gnm.aPB_(activity, intent);
        activity.finish();
    }

    @Override // com.huawei.health.ecologydevice.ui.BaseFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        BluetoothUtil bluetoothUtil;
        super.onCreate(bundle);
        initNoNeedConnectDevice();
        Bundle arguments = getArguments();
        this.mBundle = arguments;
        if (arguments == null) {
            LogUtil.a(TAG, "bundle is null");
        } else if (initParam(arguments) && (bluetoothUtil = this.mBluetoothUtil) != null) {
            bluetoothUtil.init(this.deviceStateCallback);
        }
    }

    private void initNoNeedConnectDevice() {
        Set<String> set = TYPE_BLUETOOTH_CONNECT_NOT_NEED;
        set.add("272");
        set.add("271");
        set.add("31");
        set.add("260");
        set.add("261");
        set.add(BleConstants.SPORT_TYPE_BIKE);
    }

    @Override // com.huawei.health.ecologydevice.ui.BaseFragment, androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        ProductMapParseUtil.b(BaseApplication.getContext());
        ProductMapInfo d = ProductMap.d(this.mSmartProductId);
        if (d != null) {
            this.mProductId = d.h();
            this.mProductInfo = ResourceManager.e().d(this.mProductId);
        }
        String str = this.mProductId;
        if (str != null && this.mProductInfo != null && isBondedDevice(str)) {
            LogUtil.a(TAG, "Device had bonded.");
            if (BleConstants.BLE_THIRD_DEVICE_H5.equals(this.mProductInfo.m().d())) {
                PermissionDialogHelper.e(this, 4);
                return super.onCreateView(layoutInflater, viewGroup, bundle);
            }
            if (jumpByIndoorEquipment()) {
                return super.onCreateView(layoutInflater, viewGroup, bundle);
            }
            switchNativeMode();
        }
        View onCreateView = super.onCreateView(layoutInflater, viewGroup, bundle);
        ViewGroup viewGroup2 = onCreateView instanceof ViewGroup ? (ViewGroup) onCreateView : null;
        if (viewGroup2 != null) {
            this.child = layoutInflater.inflate(R.layout.nfc_guide_introduction_layout, viewGroup, false);
            viewGroup2.addView(this.child);
            initView(this.child);
        }
        initDefaultImage();
        initTitleBar();
        if (BleConstants.TYPE_FASCIA_GUN_INDEX.equals(this.mDeviceType)) {
            handMassageGunMap();
        }
        reDownloadResource();
        return viewGroup2;
    }

    private void gotoH5Pro() {
        switchH5ProIntro();
        if (getActivity() != null) {
            getActivity().finish();
        }
    }

    private void initView(View view) {
        this.mDefaultDeviceImage = (ImageView) view.findViewById(R.id.device_default_image_iv);
        this.mDeviceImage = (ImageView) view.findViewById(R.id.device_image_iv);
        this.mDeviceBondStatus = (HealthTextView) view.findViewById(R.id.device_connect_status);
        this.mFirstConnectFailedTv = (HealthTextView) view.findViewById(R.id.device_first_connect_failed_tips);
        this.mReBondTv = (HealthTextView) view.findViewById(R.id.device_reconnect_tv);
        this.mReBondLoadingPb = (HealthProgressBar) view.findViewById(R.id.device_reconnect_pb);
        this.mFirstConnectFailedTv.setVisibility(8);
        this.mReBondTv.setOnClickListener(this);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    private void initDefaultImage() {
        char c;
        Resources resources = getContext().getResources();
        if (resources == null || TextUtils.isEmpty(this.mDeviceType)) {
            return;
        }
        LogUtil.c(TAG, "initDefaultImage:", this.mDeviceType);
        String str = this.mDeviceType;
        str.hashCode();
        switch (str.hashCode()) {
            case 1630:
                if (str.equals("31")) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case 1695:
                if (str.equals("54")) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case 1730:
                if (str.equals("68")) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case 47914:
                if (str.equals("082")) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            case 49750:
                if (str.equals(BleConstants.SPORT_TYPE_BIKE)) {
                    c = 4;
                    break;
                }
                c = 65535;
                break;
            case 49772:
                if (str.equals("260")) {
                    c = 5;
                    break;
                }
                c = 65535;
                break;
            case 49773:
                if (str.equals("261")) {
                    c = 6;
                    break;
                }
                c = 65535;
                break;
            case 49778:
                if (str.equals("266")) {
                    c = 7;
                    break;
                }
                c = 65535;
                break;
            case 49780:
                if (str.equals(BleConstants.TYPE_FASCIA_GUN_INDEX)) {
                    c = '\b';
                    break;
                }
                c = 65535;
                break;
            case 49803:
                if (str.equals("270")) {
                    c = '\t';
                    break;
                }
                c = 65535;
                break;
            case 49804:
                if (str.equals("271")) {
                    c = '\n';
                    break;
                }
                c = 65535;
                break;
            case 49805:
                if (str.equals("272")) {
                    c = 11;
                    break;
                }
                c = 65535;
                break;
            case 49806:
                if (str.equals("273")) {
                    c = '\f';
                    break;
                }
                c = 65535;
                break;
            default:
                c = 65535;
                break;
        }
        switch (c) {
            case 0:
                this.mDefaultDeviceImage.setImageDrawable(resources.getDrawable(R.drawable._2131431984_res_0x7f0b1230));
                break;
            case 1:
                this.mDefaultDeviceImage.setImageDrawable(resources.getDrawable(R.drawable._2131427665_res_0x7f0b0151));
                break;
            case 2:
                this.mDefaultDeviceImage.setImageDrawable(resources.getDrawable(R.drawable._2131427627_res_0x7f0b012b));
                break;
            case 3:
                this.mDefaultDeviceImage.setImageDrawable(resources.getDrawable(R.drawable._2131431532_res_0x7f0b106c));
                break;
            case 4:
                this.mDefaultDeviceImage.setImageDrawable(resources.getDrawable(R.drawable._2131430713_res_0x7f0b0d39));
                break;
            case 5:
                this.mDefaultDeviceImage.setImageDrawable(resources.getDrawable(R.drawable._2131427905_res_0x7f0b0241));
                break;
            case 6:
                this.mDefaultDeviceImage.setImageDrawable(resources.getDrawable(R.drawable._2131431313_res_0x7f0b0f91));
                break;
            case 7:
                this.mDefaultDeviceImage.setImageDrawable(resources.getDrawable(R.drawable._2131431533_res_0x7f0b106d));
                break;
            case '\b':
                this.mDefaultDeviceImage.setImageDrawable(resources.getDrawable(R.drawable._2131428145_res_0x7f0b0331));
                break;
            case '\t':
                this.mDefaultDeviceImage.setImageDrawable(resources.getDrawable(R.drawable.heart_rate_monitors_universal));
                break;
            case '\n':
                this.mDefaultDeviceImage.setImageDrawable(resources.getDrawable(R.drawable._2131428052_res_0x7f0b02d4));
                break;
            case 11:
                this.mDefaultDeviceImage.setImageDrawable(resources.getDrawable(R.drawable._2131431761_res_0x7f0b1151));
                break;
            case '\f':
                this.mDefaultDeviceImage.setImageDrawable(resources.getDrawable(R.drawable._2131431156_res_0x7f0b0ef4));
                break;
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:49:0x0071, code lost:
    
        if (r2.equals(com.huawei.operation.ble.BleConstants.TYPE_FASCIA_GUN_INDEX) == false) goto L60;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void initTitleBar() {
        /*
            Method dump skipped, instructions count: 442
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.health.ecologydevice.ui.measure.fragment.NfcOrQrCodeGuideFragment.initTitleBar():void");
    }

    private void startDownloadResource() {
        DownloadManagerApi downloadManagerApi = (DownloadManagerApi) Services.c("DownloadDeviceResource", DownloadManagerApi.class);
        if (!CommonUtil.aa(this.mainActivity)) {
            LogUtil.h(TAG, "startDownloadResource net is error");
            sendEmptyMessage(108);
        } else {
            downloadManagerApi.addDownloadIndexAllCallBack(this.mDownloadResultCallback);
            ReleaseLogUtil.e(TAG, "downLoad index_all");
            downloadManagerApi.downloadIndexAll();
        }
    }

    private boolean initParam(Bundle bundle) {
        if (bundle == null) {
            return false;
        }
        this.mPayload = bundle.getString("PAYLOAD_FROM_NFC");
        this.mDeviceMacAddress = bundle.getString("BLE_FROM_QRCODE");
        this.mDeviceName = bundle.getString("BLENAME_FROM_QRCODE");
        this.mDeviceType = bundle.getString("DEVICE_TYPE_INDEX");
        this.mSmartProductId = bundle.getString("PID_FROM_QRCODE");
        this.mDevicePin = bundle.getString("PIN_FROM_QRCODE");
        this.mKind = bundle.getString("DeviceType");
        String string = bundle.getString("ENTER_TYPE");
        this.mEnterType = string;
        LogUtil.a(TAG, "mEnterType:", string);
        if (TextUtils.isEmpty(this.mEnterType)) {
            this.mEnterType = Constants.LINK;
        }
        if (bundle.getBoolean(FROM_SMART_LIFE_TYPE)) {
            this.mEnterType = "";
        }
        if (!checkInputIsValid() || "082".equals(this.mDeviceType)) {
            return false;
        }
        if (BluetoothAdapter.getDefaultAdapter() == null && getActivity() != null) {
            if ((getActivity().getSystemService("bluetooth") instanceof BluetoothManager ? (BluetoothManager) getActivity().getSystemService("bluetooth") : null) == null) {
                LogUtil.h(TAG, "no BT Manager in this phone");
                return false;
            }
        }
        if (BluetoothAdapter.getDefaultAdapter() == null) {
            return false;
        }
        if (this.mBluetoothUtil == null) {
            this.mBluetoothUtil = BluetoothUtil.getInstance();
        }
        this.mBluetoothUtil.setDeviceNameAndMac(this.mDeviceName, this.mDeviceMacAddress);
        dkq.c().b(this.mKind);
        this.mBluetoothUtil.setIsUniformDeviceManagementFlag(dkq.c().d());
        return true;
    }

    private void saveBrDevice(BluetoothDevice bluetoothDevice) {
        dcz d = ResourceManager.e().d(this.mProductId);
        MeasureKit healthDeviceKit = ((MeasureKitManagerApi) Services.c("PluginDevice", MeasureKitManagerApi.class)).getHealthDeviceKit(this.mMeasureKit);
        ContentValues contentValues = new ContentValues();
        contentValues.put("kind", getDeviceKindName(d, healthDeviceKit));
        contentValues.put(Wpt.MODE, Integer.valueOf(getDeviceMode(d, healthDeviceKit)));
        contentValues.put("auto", Integer.valueOf(isAutoMeasureDevcie(d, healthDeviceKit) ? 1 : 0));
        try {
            contentValues.put("uniqueId", bluetoothDevice.getAddress());
            contentValues.put("name", bluetoothDevice.getName());
            contentValues.put("sn", "");
            contentValues.put("name", bluetoothDevice.getName());
            contentValues.put("productId", this.mProductId);
            contentValues.put(EventMonitorRecord.ADD_TIME, Long.valueOf(System.currentTimeMillis()));
            contentValues.put("kitUuid", this.mMeasureKit);
            if (((DeviceDataBaseHelperApi) Services.c("PluginDevice", DeviceDataBaseHelperApi.class)).insert(contentValues) == -1) {
                LogUtil.a(TAG, "fail to insert device data base");
            }
        } catch (SecurityException e) {
            LogUtil.b(TAG, "saveBrDevice SecurityException:", ExceptionUtils.d(e));
        }
    }

    private boolean checkInputIsValid() {
        LogUtil.a(TAG, "mDeviceMacAddress = ", this.mDeviceMacAddress, " mDeviceName", this.mDeviceName, " mDeviceType = ", this.mDeviceType, " mDevicePid = ", this.mSmartProductId, " mEnterType = ", this.mEnterType, " mDevicePin = ", this.mDevicePin);
        if (TextUtils.isEmpty(this.mDeviceMacAddress) && TextUtils.isEmpty(this.mDeviceName)) {
            return false;
        }
        return !TextUtils.isEmpty(this.mEnterType);
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroyView() {
        super.onDestroyView();
        H5DeviceHandler h5DeviceHandler = this.mH5DeviceHandler;
        if (h5DeviceHandler != null) {
            h5DeviceHandler.removeCallbacksAndMessages(null);
        }
        BluetoothUtil bluetoothUtil = this.mBluetoothUtil;
        if (bluetoothUtil != null) {
            bluetoothUtil.stopScanDevice();
            this.mBluetoothUtil.releaseSource();
        }
        if (this.mHealthDevic != null) {
            this.mHealthDevic = null;
        }
        this.mReconnectCount = 0;
        handleCancel();
    }

    @Override // androidx.fragment.app.Fragment
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        LogUtil.a(TAG, "onActivityResult is : ", Integer.valueOf(i), "; resultCode is : ", Integer.valueOf(i2));
        if (i == 2) {
            this.mIsBtEnableShowing = false;
            if (i2 == 0) {
                finishFragment();
                return;
            } else {
                checkPermissions();
                return;
            }
        }
        if (i == 3) {
            checkPermissions();
            return;
        }
        if (i != 4) {
            LogUtil.a(TAG, "onActivityResult else is" + i2);
        } else if (i2 == -1) {
            gotoH5Pro();
        } else {
            finishFragment();
        }
    }

    private void handMassageGunMap() {
        ResourceManager.e().TL_(new Handler(Looper.getMainLooper()), new MassageGunConfig.MassageGunCallback() { // from class: com.huawei.health.ecologydevice.ui.measure.fragment.NfcOrQrCodeGuideFragment$$ExternalSyntheticLambda2
            @Override // com.huawei.health.ecologydevice.manager.MassageGunConfig.MassageGunCallback
            public final void onMassageGunCallback(String str) {
                LogUtil.a(NfcOrQrCodeGuideFragment.TAG, "handMassageGunMap result ", Boolean.valueOf(dcg.a(str)));
            }
        });
    }

    private void handleUpdateProductMap() {
        LogUtil.a(TAG, "start handleUpdateProductMap");
        sendEmptyDelayMessage(108, OpAnalyticsConstants.H5_LOADING_DELAY);
        cwt.a().c(true);
        cwt.a().b(this.mKind, this.mSmartProductId, new DownloadDeviceInfoCallBack() { // from class: com.huawei.health.ecologydevice.ui.measure.fragment.NfcOrQrCodeGuideFragment.3
            @Override // com.huawei.health.ecologydevice.clouddevice.DownloadDeviceInfoCallBack
            public void onSuccess() {
                LogUtil.a(NfcOrQrCodeGuideFragment.TAG, "onSuccess");
                NfcOrQrCodeGuideFragment.this.resourceDownloaded();
            }

            @Override // com.huawei.health.ecologydevice.clouddevice.DownloadDeviceInfoCallBack
            public void onFailure(int i) {
                LogUtil.a(NfcOrQrCodeGuideFragment.TAG, "onFailure");
                if (i == 11) {
                    NfcOrQrCodeGuideFragment.this.sendEmptyMessage(112);
                } else {
                    NfcOrQrCodeGuideFragment.this.sendEmptyMessage(108);
                }
            }

            @Override // com.huawei.health.ecologydevice.clouddevice.DownloadDeviceInfoCallBack
            public void onNetworkError() {
                LogUtil.a(NfcOrQrCodeGuideFragment.TAG, "onNetworkError");
                NfcOrQrCodeGuideFragment.this.sendEmptyMessage(108);
            }
        });
    }

    private void showDownloadView() {
        LogUtil.a(TAG, "start showDownloadView");
        this.mDeviceBondStatus.setText(R.string.IDS_device_download_resource);
        this.mReBondLoadingPb.setVisibility(0);
        this.mReBondTv.setVisibility(8);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showDownloadFail(int i) {
        this.mDeviceBondStatus.setText(R.string._2130841543_res_0x7f020fc7);
        this.mFirstConnectFailedTv.setVisibility(0);
        this.mFirstConnectFailedTv.setText(i == 112 ? R.string.IDS_device_mgr_no_support_new_verion : R.string.IDS_device_download_fail_tips);
        this.mReBondLoadingPb.setVisibility(8);
        this.mReBondTv.setVisibility(0);
        this.mReBondTv.setText(R.string._2130850161_res_0x7f023171);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void judgingBluetoothOnOff(int i) {
        this.mEnableBluetoothStatus = i;
        if (BluetoothAdapter.getDefaultAdapter() == null) {
            LogUtil.h(TAG, "no BT in this phone");
            finishFragment();
        } else {
            if (!BluetoothAdapter.getDefaultAdapter().isEnabled() && !this.mIsBtEnableShowing) {
                LogUtil.a(TAG, "judgingBluetoothOnOff BluetoothAdapter.getDefaultAdapter().isEnabled() is : ", Boolean.valueOf(BluetoothAdapter.getDefaultAdapter().isEnabled()));
                this.mIsBtEnableShowing = true;
                openBlueTooth();
                return;
            }
            checkPermissions();
        }
    }

    private void openBlueTooth() {
        LogUtil.a(TAG, "openBlueTooth");
        PermissionDialogHelper.Vx_(this.mainActivity, new PermissionDialogHelper.OpenBlueToothAction() { // from class: com.huawei.health.ecologydevice.ui.measure.fragment.NfcOrQrCodeGuideFragment.4
            @Override // com.huawei.health.ecologydevice.ui.util.PermissionDialogHelper.OpenBlueToothAction
            public void onPermissionGranted() {
                try {
                    NfcOrQrCodeGuideFragment.this.startActivityForResult(new Intent("android.bluetooth.adapter.action.REQUEST_ENABLE"), 2);
                } catch (ActivityNotFoundException unused) {
                    LogUtil.b(NfcOrQrCodeGuideFragment.TAG, "openBlueTooth from activity ActivityNotFoundException!");
                }
            }

            @Override // com.huawei.health.ecologydevice.ui.util.PermissionDialogHelper.OpenBlueToothAction
            public void onPermissionDenied() {
                LogUtil.a(NfcOrQrCodeGuideFragment.TAG, "openBlueTooth finishFragment");
                NfcOrQrCodeGuideFragment.this.finishFragment();
            }
        });
    }

    private void doAfterPaired() {
        HealthDevice healthDevice = this.mHealthDevic;
        if (healthDevice instanceof cxh) {
            this.mDevice = (cxh) healthDevice;
        }
        try {
            cxh cxhVar = this.mDevice;
            if (cxhVar != null && cxhVar.Rb_() != null) {
                int bondState = this.mDevice.Rb_().getBondState();
                LogUtil.a(TAG, "bondState = ", Integer.valueOf(bondState));
                this.mBluetoothDevice = this.mDevice.Rb_();
                this.mDeviceMacAddress = this.mDevice.getUniqueId();
                if (this.mProductId != null) {
                    dcz d = ResourceManager.e().d(this.mProductId);
                    this.mProductInfo = d;
                    this.mMeasureKit = d != null ? d.s() : "";
                    saveDevice(this.mDevice);
                    if (ProductCreateFactory.getProductKind(HealthDevice.HealthDeviceKind.getHealthDeviceKind(this.mDeviceType)) == 1) {
                        dew.c(this.mProductId, this.mDeviceMacAddress, "");
                    }
                }
                cxh cxhVar2 = this.mDevice;
                dcz dczVar = this.mProductInfo;
                registerDeviceInfo(cxhVar2, (dczVar == null || dczVar.n() == null) ? getDeviceNames() : dcx.d(this.mProductId, this.mProductInfo.n().b()), this.mDeviceId, this.mProductId);
                sendEmptyMessage(111);
                String str = this.mProductId;
                String str2 = this.mDeviceMacAddress;
                dko.b(str, str2, str2);
                tickBiNfcOrQrCodeConnect(getContext(), dko.c(this.mStartConnectTime), bondState, this.mReconnectCount);
                return;
            }
            LogUtil.b(TAG, "onNotify() mDevice is null or BluetoothDevice is null");
        } catch (SecurityException e) {
            LogUtil.b(TAG, "doAfterPaired SecurityException:", ExceptionUtils.d(e));
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    private String getDeviceNames() {
        char c;
        Resources resources = getContext().getResources();
        if (resources == null || TextUtils.isEmpty(this.mDeviceType)) {
            return "";
        }
        String str = this.mDeviceType;
        str.hashCode();
        switch (str.hashCode()) {
            case 1695:
                if (str.equals("54")) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case 1730:
                if (str.equals("68")) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case 47914:
                if (str.equals("082")) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case 49773:
                if (str.equals("261")) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            case 49778:
                if (str.equals("266")) {
                    c = 4;
                    break;
                }
                c = 65535;
                break;
            case 49780:
                if (str.equals(BleConstants.TYPE_FASCIA_GUN_INDEX)) {
                    c = 5;
                    break;
                }
                c = 65535;
                break;
            case 49803:
                if (str.equals("270")) {
                    c = 6;
                    break;
                }
                c = 65535;
                break;
            case 49804:
                if (str.equals("271")) {
                    c = 7;
                    break;
                }
                c = 65535;
                break;
            case 49805:
                if (str.equals("272")) {
                    c = '\b';
                    break;
                }
                c = 65535;
                break;
            case 49806:
                if (str.equals("273")) {
                    c = '\t';
                    break;
                }
                c = 65535;
                break;
            default:
                c = 65535;
                break;
        }
        switch (c) {
            case 0:
                return resources.getString(R.string.IDS_device_group_name_body_fat_scale);
            case 1:
                return resources.getString(R.string.IDS_device_group_name_blood_pressure_scale);
            case 2:
                return resources.getString(R.string.IDS_add_device_smart_headphones);
            case 3:
                return resources.getString(R.string.IDS_device_group_name_rowing_machine_scale);
            case 4:
                return resources.getString(R.string.IDS_device_group_name_smart_pillow);
            case 5:
                return resources.getString(R.string.IDS_device_group_name_fascia_gun);
            case 6:
                return resources.getString(R.string.IDS_device_group_name_pulse_scale);
            case 7:
                return resources.getString(R.string.IDS_device_group_name_ecg_scale);
            case '\b':
                return resources.getString(R.string.IDS_device_group_name_temperature_scale);
            case '\t':
                return resources.getString(R.string.IDS_device_group_name_blood_oxygen_scale);
            default:
                return "";
        }
    }

    private void checkPermissions() {
        PermissionUtil.PermissionType d = PermissionDialogHelper.d();
        PermissionDialogHelper.VB_(getActivity(), d, new AnonymousClass5(this.mainActivity, d));
    }

    /* renamed from: com.huawei.health.ecologydevice.ui.measure.fragment.NfcOrQrCodeGuideFragment$5, reason: invalid class name */
    class AnonymousClass5 extends CustomPermissionAction {
        final /* synthetic */ PermissionUtil.PermissionType val$permissionType;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass5(Context context, PermissionUtil.PermissionType permissionType) {
            super(context);
            this.val$permissionType = permissionType;
        }

        @Override // com.huawei.hwcommonmodel.utils.permission.PermissionsResultAction
        public void onGranted() {
            if (this.val$permissionType != PermissionUtil.PermissionType.LOCATION || dja.VG_(NfcOrQrCodeGuideFragment.this.mainActivity)) {
                NfcOrQrCodeGuideFragment.this.bondDevice();
            } else {
                LogUtil.a(NfcOrQrCodeGuideFragment.TAG, "Permission GpsClose is close");
                NfcOrQrCodeGuideFragment.this.checkGpsLocation();
            }
        }

        @Override // com.huawei.ui.commonui.utils.CustomPermissionAction, com.huawei.hwcommonmodel.utils.permission.PermissionsResultAction
        public void onDenied(String str) {
            super.onDenied(str);
            NfcOrQrCodeGuideFragment.this.finishFragment();
        }

        @Override // com.huawei.ui.commonui.utils.CustomPermissionAction, com.huawei.hwcommonmodel.utils.permission.PermissionsResultAction
        public void onForeverDenied(PermissionUtil.PermissionType permissionType) {
            super.onForeverDenied(permissionType);
            nsn.cLK_(NfcOrQrCodeGuideFragment.this.mainActivity, permissionType, null, new View.OnClickListener() { // from class: com.huawei.health.ecologydevice.ui.measure.fragment.NfcOrQrCodeGuideFragment$5$$ExternalSyntheticLambda0
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    NfcOrQrCodeGuideFragment.AnonymousClass5.this.m309x491d7ab0(view);
                }
            }, new View.OnClickListener() { // from class: com.huawei.health.ecologydevice.ui.measure.fragment.NfcOrQrCodeGuideFragment$5$$ExternalSyntheticLambda1
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    NfcOrQrCodeGuideFragment.AnonymousClass5.this.m310x2710e08f(view);
                }
            });
        }

        /* renamed from: lambda$onForeverDenied$0$com-huawei-health-ecologydevice-ui-measure-fragment-NfcOrQrCodeGuideFragment$5, reason: not valid java name */
        /* synthetic */ void m309x491d7ab0(View view) {
            NfcOrQrCodeGuideFragment.this.finishFragment();
            ViewClickInstrumentation.clickOnView(view);
        }

        /* renamed from: lambda$onForeverDenied$1$com-huawei-health-ecologydevice-ui-measure-fragment-NfcOrQrCodeGuideFragment$5, reason: not valid java name */
        /* synthetic */ void m310x2710e08f(View view) {
            NfcOrQrCodeGuideFragment.this.finishFragment();
            ViewClickInstrumentation.clickOnView(view);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void bondDevice() {
        if (this.mEnableBluetoothStatus == 2) {
            if ("082".equals(this.mDeviceType)) {
                new dcp().d(this.mDeviceMacAddress, this.nemoDeviceCallback);
                return;
            } else {
                sendEmptyDelayMessage(109, 500L);
                return;
            }
        }
        jumpIndoorEquipOrDownloadResource();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void checkGpsLocation() {
        LogUtil.a(TAG, "openGpsLocation jump to GPS Setting");
        dif.Vp_(this.mainActivity, isAdded(), new IdialogButtonClickCallback() { // from class: com.huawei.health.ecologydevice.ui.measure.fragment.NfcOrQrCodeGuideFragment.6
            @Override // com.huawei.health.ecologydevice.callback.IdialogButtonClickCallback
            public void onClick(View view) {
                LogUtil.a(NfcOrQrCodeGuideFragment.TAG, "jump to GpsSetting");
                NfcOrQrCodeGuideFragment nfcOrQrCodeGuideFragment = NfcOrQrCodeGuideFragment.this;
                dja.d(nfcOrQrCodeGuideFragment, nfcOrQrCodeGuideFragment.isAdded(), 3);
                ViewClickInstrumentation.clickOnView(view);
            }

            @Override // com.huawei.health.ecologydevice.callback.IdialogButtonClickCallback
            public void onCancelClick(View view) {
                LogUtil.a(NfcOrQrCodeGuideFragment.TAG, "openGpsLocation onCancelClick");
                NfcOrQrCodeGuideFragment.this.finishFragment();
            }
        });
    }

    private void jumpIndoorEquipOrDownloadResource() {
        LogUtil.a(TAG, "jumpIndoorEquipOrDownloadResource");
        try {
            BluetoothDevice remoteDevice = (TextUtils.isEmpty(this.mDeviceMacAddress) || !BluetoothAdapter.checkBluetoothAddress(this.mDeviceMacAddress)) ? null : BluetoothAdapter.getDefaultAdapter().getRemoteDevice(this.mDeviceMacAddress);
            if (remoteDevice != null && !TextUtils.isEmpty(remoteDevice.getName())) {
                getProductIdByBluetoothName(remoteDevice.getName());
                updateIndexAndProductMap();
                return;
            }
            LogUtil.a(TAG, "bluetoothDevice  is null or bluetoothDevice.getName() is empty");
            startIndoorEquipRunningActByNfc();
        } catch (SecurityException e) {
            LogUtil.b(TAG, "jumpIndoorEquipOrDownloadResource SecurityException:", ExceptionUtils.d(e));
        }
    }

    private void getProductIdByBluetoothName(String str) {
        this.mProductId = getProductIdByBluetooth(str);
        String e = TextUtils.isEmpty(this.mSmartProductId) ? dij.e(this.mProductId) : this.mSmartProductId;
        this.mSmartProductId = e;
        LogUtil.a(TAG, "getProductIdByBluetoothName bluetoothName is ", str, ", mProductId is ", this.mProductId, ", smartProductId is ", e);
    }

    private String getProductIdByBluetooth(String str) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.a(TAG, "broadcastName is empty");
            return "";
        }
        List<cve> deviceInfoByBluetooth = ((DownloadManagerApi) Services.c("DownloadDeviceResource", DownloadManagerApi.class)).getDeviceInfoByBluetooth(str);
        if (!koq.c(deviceInfoByBluetooth)) {
            return "";
        }
        List<String> ac = deviceInfoByBluetooth.get(0).ac();
        return koq.c(ac) ? ac.get(0) : "";
    }

    private void handlerToRefresh(dcz dczVar) {
        if (dczVar == null) {
            LogUtil.a(TAG, "handlerToRefresh productInfo is null");
            return;
        }
        String t = dczVar.t();
        ResourceManager.e().h(t);
        if (((HonourDeviceConstantsApi) Services.c("PluginDevice", HonourDeviceConstantsApi.class)).isCorrectProductId(t)) {
            sendEmptyMessage(105);
        }
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

    private void removeMessage(int i) {
        H5DeviceHandler h5DeviceHandler = this.mH5DeviceHandler;
        if (h5DeviceHandler != null) {
            h5DeviceHandler.removeMessages(i);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendEmptyMessage(int i) {
        H5DeviceHandler h5DeviceHandler = this.mH5DeviceHandler;
        if (h5DeviceHandler != null) {
            h5DeviceHandler.sendEmptyMessage(i);
        }
    }

    private void sendEmptyDelayMessage(int i, long j) {
        H5DeviceHandler h5DeviceHandler = this.mH5DeviceHandler;
        if (h5DeviceHandler != null) {
            h5DeviceHandler.sendEmptyMessageDelayed(i, j);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void finishFragment() {
        popupFragment(null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void refreshProductInfo() {
        if ("082".equals(this.mDeviceType)) {
            sendEmptyMessage(110);
            return;
        }
        ProductMapInfo d = ProductMap.d(this.mSmartProductId);
        if (d == null) {
            LogUtil.a(TAG, "refreshProductInfo : mProductId is null");
            sendEmptyMessage(110);
            return;
        }
        this.mProductId = d.h();
        this.mProductInfo = ResourceManager.e().d(this.mProductId);
        this.mDeviceId = d.c();
        if (needUpdateAppVersion()) {
            return;
        }
        dcz dczVar = this.mProductInfo;
        if (BleConstants.BLE_THIRD_DEVICE_H5.equals(dczVar != null ? dczVar.m().d() : "")) {
            if (!TextUtils.isEmpty(this.mProductId) && isBondedDevice(this.mProductId)) {
                LogUtil.a(TAG, "Device has bonded: mProductId = ", this.mProductId);
                switchH5ProIntro();
            }
        } else if ("68".equals(this.mDeviceType)) {
            switchBloodPressureIntroductionFragment();
            return;
        } else if (!TextUtils.isEmpty(this.mProductId) && isBondedDevice(this.mProductId)) {
            LogUtil.a(TAG, "Native Device has bonded: mProductId = ", this.mProductId);
            switchNativeMode();
            return;
        }
        sendEmptyMessage(110);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void switchNativeMode() {
        LogUtil.a(TAG, "enter switchNativeMode");
        if (this.mProductInfo == null) {
            LogUtil.h(TAG, "switchNativeMode: mProductInfo is null");
            return;
        }
        if ("54".equals(this.mDeviceType) || "272".equals(this.mDeviceType)) {
            LogUtil.a(TAG, "goto ProductIntroductionFragment");
            switchProductIntroductionFragment();
        } else if (needJumpToSportPage()) {
            switchSportIntroductionFragment();
        } else if ("68".equals(this.mDeviceType)) {
            switchBloodPressureIntroductionFragment();
        } else {
            switchH5ProIntro();
        }
    }

    private boolean needJumpToSportPage() {
        return BleConstants.SPORT_TYPE_BIKE.equals(this.mDeviceType) || "261".equals(this.mDeviceType) || "260".equals(this.mDeviceType) || "31".equals(this.mDeviceType);
    }

    private void switchSportIntroductionFragment() {
        ContentValues contentValues = new ContentValues();
        contentValues.put("productId", this.mProductId);
        contentValues.put("uniqueId", this.mDeviceMacAddress);
        contentValues.put("name", this.mDeviceName);
        this.mBundle.putParcelable("commonDeviceInfo", contentValues);
        ProductFragment createProductFragment = ProductCreateFactory.createProductFragment(this.mProductId);
        createProductFragment.setArguments(this.mBundle);
        switchFragment(this, createProductFragment);
    }

    private void switchBloodPressureIntroductionFragment() {
        BloodPressureIntroductionFragment bloodPressureIntroductionFragment = new BloodPressureIntroductionFragment();
        bloodPressureIntroductionFragment.setArguments(this.mBundle);
        switchFragment(this, bloodPressureIntroductionFragment);
    }

    private void switchProductIntroductionFragment() {
        if (TextUtils.isEmpty(this.mBundle.getString("PID_FROM_QRCODE"))) {
            LogUtil.h(TAG, "switchProductIntroductionFragment pid is null");
            return;
        }
        Bundle bundle = new Bundle();
        bundle.putString("productId", this.mProductId);
        ContentValues contentValues = new ContentValues();
        contentValues.put("uniqueId", this.mBundle.getString("BLE_FROM_QRCODE"));
        contentValues.put("productId", this.mProductId);
        bundle.putParcelable("commonDeviceInfo", contentValues);
        bundle.putBoolean("isBindSuccess", true);
        Fragment productIntroductionFragment = new ProductIntroductionFragment();
        productIntroductionFragment.setArguments(bundle);
        switchFragment(this, productIntroductionFragment);
    }

    private boolean needUpdateAppVersion() {
        VersionVerifyUtilApi versionVerifyUtilApi = (VersionVerifyUtilApi) Services.c("PluginDevice", VersionVerifyUtilApi.class);
        if (!msr.c.containsKey(this.mKind) || versionVerifyUtilApi.isPublishVersion(this.mProductId, this.mKind)) {
            return false;
        }
        LogUtil.a(TAG, "current device needs to update the app version, mProductId = ", this.mProductId);
        versionVerifyUtilApi.noSupportDevice(getContext(), getActivity());
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showBondingView() {
        setProductNameAndImage();
        this.mDeviceBondStatus.setText(R.string._2130841387_res_0x7f020f2b);
        this.mFirstConnectFailedTv.setVisibility(8);
        this.mReBondTv.setVisibility(8);
        this.mReBondLoadingPb.setVisibility(0);
    }

    private void showBondedView() {
        this.mDeviceBondStatus.setText(R.string._2130841578_res_0x7f020fea);
        this.mReBondLoadingPb.setVisibility(8);
        this.mFirstConnectFailedTv.setVisibility(8);
    }

    private void showFirstBindFail() {
        this.mFirstConnectFailedTv.setVisibility(0);
        this.mFirstConnectFailedTv.setText(R.string.IDS_device_err_connect_timeout_tips_info1);
        this.mDeviceBondStatus.setText(R.string._2130841387_res_0x7f020f2b);
        this.mReBondTv.setVisibility(8);
        this.mReBondLoadingPb.setVisibility(0);
    }

    private void showBondFail() {
        this.mFirstConnectFailedTv.setVisibility(8);
        this.mReBondLoadingPb.setVisibility(8);
        this.mReBondTv.setVisibility(0);
        this.mReBondTv.setText(R.string._2130843255_res_0x7f021677);
        this.mDeviceBondStatus.setText(R.string.IDS_device_bind_fail);
        this.mReBondTv.setTextColor(getResources().getColor(R.color._2131296570_res_0x7f09013a));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showDeviceCurrentState() {
        LogUtil.h(TAG, "showDeviceCurrentState mCurrentStatus：", Integer.valueOf(this.mCurrentStatus));
        switch (this.mCurrentStatus) {
            case 10:
                showFirstBindFail();
                break;
            case 11:
            default:
                LogUtil.h(TAG, "not have thi case");
                break;
            case 12:
                break;
            case 13:
                showConnectFailDialog();
                if (!"082".equals(this.mDeviceType)) {
                    tickBiNfcOrQrCodeConnect(getContext(), 0.0d, this.mNewStatus, this.mReconnectCount);
                    break;
                }
                break;
            case 14:
                showBondFail();
                break;
            case 15:
                showBondingView();
                break;
            case 16:
                showBondedView();
                break;
            case 17:
                if (TYPE_BLUETOOTH_CONNECT_NOT_NEED.contains(this.mDeviceType)) {
                    doAfterPaired();
                    break;
                } else {
                    showInputPinTipsDialog();
                    break;
                }
            case 18:
                doAfterPaired();
                break;
        }
    }

    private void showInputPinTipsDialog() {
        LogUtil.c(TAG, "enter showInputPinTipsDialog()");
        HealthDevice healthDevice = this.mHealthDevic;
        if (healthDevice instanceof cxh) {
            this.mDevice = (cxh) healthDevice;
        }
        try {
            cxh cxhVar = this.mDevice;
            if (cxhVar != null && cxhVar.Rb_() != null) {
                LogUtil.a(TAG, "bondState:", Integer.valueOf(this.mDevice.Rb_().getBondState()));
                BluetoothDevice Rb_ = this.mDevice.Rb_();
                this.mBluetoothDevice = Rb_;
                if (Rb_ != null && Rb_.getBondState() == 12) {
                    LogUtil.c(TAG, "showInputPinTipsDialog(): BluetoothDevice.BOND_BONDED");
                    doAfterPaired();
                }
                if (!TextUtils.isEmpty(this.mDevicePin)) {
                    showPinCodeDialog();
                    return;
                } else {
                    if (this.mBluetoothUtil == null) {
                        return;
                    }
                    LogUtil.c(TAG, "showInputPinTipsDialog(): mBluetoothUtil.createBond()");
                    this.mBluetoothUtil.createBond();
                    return;
                }
            }
            LogUtil.b(TAG, "onNotify() mDevice is null or BluetoothDevice is null");
        } catch (SecurityException e) {
            LogUtil.b(TAG, "showInputPinTipsDialog SecurityException:", ExceptionUtils.d(e));
        }
    }

    private void showPinCodeDialog() {
        View inflate = View.inflate(getContext(), R.layout.input_pincode_dialog, null);
        getViewForDialog(inflate);
        if (this.mCustomViewDialog == null) {
            CustomViewDialog e = new CustomViewDialog.Builder(getContext()).czg_(inflate).czd_(getContext().getString(R.string.IDS_device_hygride_button_cancel), new View.OnClickListener() { // from class: com.huawei.health.ecologydevice.ui.measure.fragment.NfcOrQrCodeGuideFragment$$ExternalSyntheticLambda4
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    NfcOrQrCodeGuideFragment.this.m307x59995172(view);
                }
            }).czf_(getContext().getString(R.string.IDS_device_input), new View.OnClickListener() { // from class: com.huawei.health.ecologydevice.ui.measure.fragment.NfcOrQrCodeGuideFragment$$ExternalSyntheticLambda5
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    NfcOrQrCodeGuideFragment.this.m308x729aa311(view);
                }
            }).e();
            this.mCustomViewDialog = e;
            e.setCancelable(false);
        }
        this.mCustomViewDialog.show();
    }

    /* renamed from: lambda$showPinCodeDialog$3$com-huawei-health-ecologydevice-ui-measure-fragment-NfcOrQrCodeGuideFragment, reason: not valid java name */
    /* synthetic */ void m307x59995172(View view) {
        finishFragment();
        ViewClickInstrumentation.clickOnView(view);
    }

    /* renamed from: lambda$showPinCodeDialog$4$com-huawei-health-ecologydevice-ui-measure-fragment-NfcOrQrCodeGuideFragment, reason: not valid java name */
    /* synthetic */ void m308x729aa311(View view) {
        BluetoothUtil bluetoothUtil = this.mBluetoothUtil;
        if (bluetoothUtil != null) {
            bluetoothUtil.createBond();
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    private void getViewForDialog(View view) {
        this.mDialogImageView = (ImageView) view.findViewById(R.id.iv_device_image);
        Bitmap productImage = getProductImage(IMAGE_NAME);
        if (productImage != null) {
            this.mDialogImageView.setImageBitmap(productImage);
        }
    }

    private void showConnectFailDialog() {
        this.mFirstConnectFailedTv.setVisibility(8);
        this.mReBondLoadingPb.setVisibility(8);
        this.mReBondTv.setVisibility(0);
        this.mReBondTv.setText(R.string._2130843255_res_0x7f021677);
        this.mDeviceBondStatus.setText(R.string.IDS_device_bind_fail);
        this.mReBondTv.setTextColor(getResources().getColor(R.color._2131299241_res_0x7f090ba9));
        if (getContext() == null) {
            return;
        }
        if (this.mDialogForConnectFail == null) {
            View inflate = LayoutInflater.from(cpp.a()).inflate(R.layout.blood_pressure_connect_timeout_dialog, (ViewGroup) null);
            CustomViewDialog.Builder builder = new CustomViewDialog.Builder(getContext());
            builder.d(R.string.IDS_device_wifi_bind_fail).czg_(inflate).cze_(R.string._2130841794_res_0x7f0210c2, new View.OnClickListener() { // from class: com.huawei.health.ecologydevice.ui.measure.fragment.NfcOrQrCodeGuideFragment$$ExternalSyntheticLambda1
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    NfcOrQrCodeGuideFragment.this.m306x29df096a(view);
                }
            });
            CustomViewDialog e = builder.e();
            this.mDialogForConnectFail = e;
            e.setCancelable(false);
        }
        this.mDialogForConnectFail.show();
    }

    /* renamed from: lambda$showConnectFailDialog$5$com-huawei-health-ecologydevice-ui-measure-fragment-NfcOrQrCodeGuideFragment, reason: not valid java name */
    /* synthetic */ void m306x29df096a(View view) {
        LogUtil.a(TAG, "showConnectionNoteDialog onclick PositiveButton");
        finishFragment();
        ViewClickInstrumentation.clickOnView(view);
    }

    private void reDownloadResource() {
        if (CommonUtil.aa(BaseApplication.getContext())) {
            showDownloadView();
            sendEmptyDelayMessage(108, OpAnalyticsConstants.H5_LOADING_DELAY);
            startDownloadResource();
            return;
        }
        sendEmptyMessage(108);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void resourceDownloaded() {
        handlerToRefresh(ResourceManager.e().d(this.mProductId));
        removeMessage(108);
        sendEmptyMessage(104);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void scanDevice() {
        if (this.mBluetoothUtil != null) {
            LogUtil.a(TAG, "enter scanDevice");
            this.mStartConnectTime = System.currentTimeMillis();
            this.mBluetoothUtil.scanDevice();
        }
    }

    private Bitmap getProductImage(String str) {
        String str2;
        dcz d = ResourceManager.e().d(this.mProductId);
        if (d == null || d.e().size() <= 0) {
            str2 = "";
        } else if (IMAGE_NAME.equals(str)) {
            str2 = dcq.b().a(this.mProductId, str);
        } else {
            str2 = dcq.b().a(this.mProductId, d.e().get(0).e());
        }
        return BitmapFactory.decodeFile(str2);
    }

    private void setProductNameAndImage() {
        dcz d = ResourceManager.e().d(this.mProductId);
        if (d != null) {
            super.setTitle(dcx.d(this.mProductId, d.n().b()));
        }
        ImageView imageView = this.mDefaultDeviceImage;
        if (imageView == null || this.mDeviceImage == null) {
            return;
        }
        imageView.setVisibility(8);
        this.mDeviceImage.setVisibility(0);
        this.mDeviceImage.setImageBitmap(getProductImage(null));
    }

    private void tickBiNfcOrQrCodeConnect(Context context, double d, int i, int i2) {
        new dko.c().e(d).c(this.mProductId).d(this.mDeviceType).b(this.mEnterType).e(i2).b(i).i(this.mProductId).e(this.mDeviceMacAddress).b().b(context);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (getActivity() == null) {
            LogUtil.h(TAG, "onclick view is null");
            ViewClickInstrumentation.clickOnView(view);
            return;
        }
        if (nsn.o()) {
            LogUtil.a(TAG, "click too fast");
            ViewClickInstrumentation.clickOnView(view);
            return;
        }
        HealthTextView healthTextView = this.mReBondTv;
        if (view == healthTextView) {
            if (healthTextView.getText().toString().equals(getResources().getString(R.string.IDS_device_measureactivity_guide_start_measure))) {
                dks.c(getContext(), this.mProductId, this.mDevice.getUniqueId());
            } else if (this.mReBondTv.getText().toString().equals(getResources().getString(R.string._2130843255_res_0x7f021677))) {
                showBondingView();
                judgingBluetoothOnOff(2);
            } else if (this.mReBondTv.getText().toString().equals(getResources().getString(R.string._2130850161_res_0x7f023171))) {
                this.mFirstConnectFailedTv.setVisibility(8);
                reDownloadResource();
            } else {
                LogUtil.h(TAG, "not have this view");
            }
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    static class H5DeviceHandler extends Handler {
        private WeakReference<NfcOrQrCodeGuideFragment> mWeakReference;

        private H5DeviceHandler(NfcOrQrCodeGuideFragment nfcOrQrCodeGuideFragment) {
            this.mWeakReference = new WeakReference<>(nfcOrQrCodeGuideFragment);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            NfcOrQrCodeGuideFragment nfcOrQrCodeGuideFragment = this.mWeakReference.get();
            if (nfcOrQrCodeGuideFragment == null || !nfcOrQrCodeGuideFragment.isAdded() || nfcOrQrCodeGuideFragment.isRemoving() || nfcOrQrCodeGuideFragment.isDetached()) {
                return;
            }
            switch (message.what) {
                case 104:
                    LogUtil.c(NfcOrQrCodeGuideFragment.TAG, "DOWNLOAD_RESOURCE_SUCCESS");
                    removeMessages(108);
                    nfcOrQrCodeGuideFragment.refreshProductInfo();
                    break;
                case 105:
                case 107:
                default:
                    LogUtil.h(NfcOrQrCodeGuideFragment.TAG, "not have this case", Integer.valueOf(message.what));
                    break;
                case 106:
                    LogUtil.c(NfcOrQrCodeGuideFragment.TAG, "STATE_CHANGED");
                    nfcOrQrCodeGuideFragment.showDeviceCurrentState();
                    break;
                case 108:
                case 112:
                    nfcOrQrCodeGuideFragment.handleCancel();
                    LogUtil.h(NfcOrQrCodeGuideFragment.TAG, "DOWNLOAD fail mProductId:", nfcOrQrCodeGuideFragment.mProductId);
                    if (EzPluginManager.a().g(nfcOrQrCodeGuideFragment.mProductId)) {
                        nfcOrQrCodeGuideFragment.resourceDownloaded();
                        break;
                    } else {
                        nfcOrQrCodeGuideFragment.showDownloadFail(message.what);
                        break;
                    }
                case 109:
                    nfcOrQrCodeGuideFragment.scanDevice();
                    break;
                case 110:
                    nfcOrQrCodeGuideFragment.showBondingView();
                    nfcOrQrCodeGuideFragment.judgingBluetoothOnOff(2);
                    break;
                case 111:
                    LogUtil.c(NfcOrQrCodeGuideFragment.TAG, "enter SWITCH_FRAGMENT case");
                    if (BleConstants.BLE_THIRD_DEVICE_H5.equals(nfcOrQrCodeGuideFragment.mProductInfo != null ? nfcOrQrCodeGuideFragment.mProductInfo.m().d() : "")) {
                        nfcOrQrCodeGuideFragment.switchH5ProIntro();
                        break;
                    } else {
                        nfcOrQrCodeGuideFragment.switchNativeMode();
                        break;
                    }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void switchH5ProIntro() {
        LogUtil.c(TAG, "enter switchH5ProIntro");
        dcz dczVar = this.mProductInfo;
        if (dczVar == null) {
            LogUtil.h(TAG, "switchH5ProIntro: mProductInfo is null");
        } else if ("1".equals(dczVar.j())) {
            dks.d(getActivity(), this.mProductInfo, this.mProductId, this.mDeviceMacAddress);
            popupFragment(null);
        }
    }

    private void saveDevice(cxh cxhVar) {
        LogUtil.c(TAG, "saveDevice");
        dcz d = ResourceManager.e().d(this.mProductId);
        MeasureKit healthDeviceKit = ((MeasureKitManagerApi) Services.c("PluginDevice", MeasureKitManagerApi.class)).getHealthDeviceKit(this.mMeasureKit);
        ContentValues contentValues = new ContentValues();
        contentValues.put("kind", getDeviceKindName(d, healthDeviceKit));
        contentValues.put(Wpt.MODE, Integer.valueOf(getDeviceMode(d, healthDeviceKit)));
        contentValues.put("auto", Integer.valueOf(isAutoMeasureDevcie(d, healthDeviceKit) ? 1 : 0));
        contentValues.put("uniqueId", cxhVar.getUniqueId());
        contentValues.put("sn", "");
        contentValues.put("name", cxhVar.getDeviceName());
        contentValues.put("productId", this.mProductId);
        contentValues.put(EventMonitorRecord.ADD_TIME, Long.valueOf(System.currentTimeMillis()));
        contentValues.put("kitUuid", this.mMeasureKit);
        if (((DeviceDataBaseHelperApi) Services.c("PluginDevice", DeviceDataBaseHelperApi.class)).insert(contentValues) == -1) {
            LogUtil.a(TAG, "fail to insert device data base");
        }
    }

    private int getDeviceMode(dcz dczVar, MeasureKit measureKit) {
        if (measureKit != null) {
            return getConnectionModeByController(measureKit);
        }
        if (dczVar != null) {
            return dczVar.x().c();
        }
        LogUtil.h(TAG, "can not determine connect mode, set default connect mode");
        return 0;
    }

    private int getConnectionModeByController(MeasureKit measureKit) {
        MeasureController backgroundController = measureKit.getBackgroundController();
        if (backgroundController == null) {
            backgroundController = measureKit.getMeasureController();
        }
        if (backgroundController == null) {
            return 0;
        }
        if (backgroundController instanceof BleMeasureController) {
            return 1;
        }
        return backgroundController instanceof HdpMeasureController ? 2 : 4;
    }

    private String getDeviceKindName(dcz dczVar, MeasureKit measureKit) {
        String name = HealthDevice.HealthDeviceKind.HDK_UNKNOWN.name();
        if (measureKit != null && measureKit.getHealthDataKind() != null) {
            return measureKit.getHealthDataKind().name();
        }
        if (dczVar != null && dczVar.l() != null) {
            return dczVar.l().name();
        }
        LogUtil.h(TAG, "can not determine device kind, set defualt");
        return name;
    }

    private boolean isAutoMeasureDevcie(dcz dczVar, MeasureKit measureKit) {
        return ((dczVar != null && "10".equals(dczVar.p())) || measureKit == null || measureKit.getBackgroundController() == null) ? false : true;
    }

    private boolean isBondedDevice(String str) {
        Iterator<ContentValues> it = cei.b().getBondedDeviceByProductId(str).iterator();
        while (it.hasNext()) {
            ContentValues next = it.next();
            String asString = next.getAsString("uniqueId");
            String asString2 = next.getAsString("name");
            if ((!TextUtils.isEmpty(this.mDeviceMacAddress) && this.mDeviceMacAddress.equals(asString)) || (!TextUtils.isEmpty(this.mDeviceName) && this.mDeviceName.equals(asString2))) {
                if (!TextUtils.isEmpty(this.mDeviceMacAddress)) {
                    asString = this.mDeviceMacAddress;
                }
                this.mDeviceMacAddress = asString;
                return true;
            }
        }
        return false;
    }
}
