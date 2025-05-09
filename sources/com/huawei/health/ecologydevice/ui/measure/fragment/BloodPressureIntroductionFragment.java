package com.huawei.health.ecologydevice.ui.measure.fragment;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothManager;
import android.bluetooth.le.BluetoothLeScanner;
import android.bluetooth.le.ScanCallback;
import android.bluetooth.le.ScanFilter;
import android.bluetooth.le.ScanResult;
import android.bluetooth.le.ScanSettings;
import android.content.ActivityNotFoundException;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.health.R;
import com.huawei.health.device.api.DeviceManagerApi;
import com.huawei.health.device.api.HealthDeviceEntryApi;
import com.huawei.health.device.callback.IDeviceEventHandler;
import com.huawei.health.device.model.HealthDevice;
import com.huawei.health.device.open.data.model.HealthData;
import com.huawei.health.ecologydevice.callback.IdialogButtonClickCallback;
import com.huawei.health.ecologydevice.callback.NfcMeasureCallback;
import com.huawei.health.ecologydevice.clouddevice.DownloadDeviceInfoCallBack;
import com.huawei.health.ecologydevice.manager.BloodPressureStartFromDeviceHelper;
import com.huawei.health.ecologydevice.manager.ResourceManager;
import com.huawei.health.ecologydevice.ui.BaseFragment;
import com.huawei.health.ecologydevice.ui.measure.fragment.BloodPressureIntroductionFragment;
import com.huawei.health.ecologydevice.ui.measure.fragment.device.DeviceSilentGuideFragment;
import com.huawei.health.ecologydevice.ui.util.PermissionDialogHelper;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hihealth.HiAggregateOption;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.api.HiHealthManager;
import com.huawei.hihealth.data.listener.HiAggregateListener;
import com.huawei.hihealth.dictionary.constants.ProductMap;
import com.huawei.hihealth.dictionary.constants.ProductMapInfo;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.utils.PermissionUtil;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.OpAnalyticsConstants;
import com.huawei.ui.commonui.dialog.CustomViewDialog;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.progressbar.HealthProgressBar;
import com.huawei.ui.commonui.utils.CustomPermissionAction;
import defpackage.cez;
import defpackage.cpp;
import defpackage.cwt;
import defpackage.cxh;
import defpackage.czu;
import defpackage.dcq;
import defpackage.dcx;
import defpackage.dcz;
import defpackage.deo;
import defpackage.dfd;
import defpackage.dif;
import defpackage.diy;
import defpackage.dja;
import defpackage.djz;
import defpackage.dko;
import defpackage.dkq;
import defpackage.dks;
import defpackage.dlb;
import defpackage.gnm;
import defpackage.koq;
import defpackage.msj;
import defpackage.msn;
import defpackage.msq;
import defpackage.nsj;
import defpackage.nsn;
import health.compact.a.CommonUtil;
import health.compact.a.Services;
import health.compact.a.UnitUtil;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes3.dex */
public class BloodPressureIntroductionFragment extends BaseFragment implements View.OnClickListener {
    private static final int AUTO_CONNECT_BLOOD_PRESSURE_DEVICE = 101;
    private static final int AUTO_CONNECT_DEVICE_DELAYED = 500;
    private static final int BLUETOOTH_TURN_OFF = 109;
    private static final int CONNECTED_FAILED = 106;
    private static final int CONVERT_RADIX_10 = 10;
    private static final int DOWNLOAD_RESOURCE_SUCCESS = 113;
    private static final int DOWNLOAD_TIME_OUT = 112;
    private static final int DOWNLOAD_TIME_OUT_DELAY_30S = 30000;
    private static final int MAX_MEASURE_TIME = 120000;
    private static final int MEASURE_FINSH = 105;
    private static final int REQUEST_ENABLE_BT = 2;
    private static final int SCAN_TIME_OUT = 116;
    private static final int SCAN_TIME_OUT_DELAY_9000MS = 9000;
    private static final int SHOW_FIRST_CONNECT_FAILED_TIPS = 107;
    private static final int SHOW_MEASURE_FAILED = 108;
    private static final int START_MEASURING = 114;
    private static final int START_SCAN = 115;
    private static final int START_SCAN_DELAY_500MS = 500;
    private static final int STATE_CHANGED = 102;
    private static final String TAG = "BloodPressureIntroductionFragment";
    private static final int TO_FRESH = 111;
    private static final int UPDATE_BLOOD_PRESSURE_DATA = 104;
    private static final int UPDATE_DATA_EMPTY = 103;
    private HealthTextView mBloodPressureDataTv;
    private RelativeLayout mBloodPressureFaqsCloseVoice;
    private LinearLayout mBloodPressureFaqsLayout;
    private RelativeLayout mBloodPressureFaqsSyncData;
    private czu mBloodPressureManager;
    private HealthTextView mBloodPressureMeasureValueTv;
    private HealthTextView mBloodPressureMeasureValueUnitTv;
    private HealthTextView mBloodPressureTv;
    private BluetoothAdapter mBluetoothAdapter;
    private HealthTextView mConnectStatusTv;
    private dfd mDataHandler;
    private ImageView mDefaultDeviceImage;
    private cxh mDevice;
    private ImageView mDeviceImage;
    private String mDeviceMacAddress;
    private ViewGroup mDeviceMeasureValue;
    private String mDeviceName;
    private String mDevicePid;
    private String mDeviceType;
    private String mEnterType;
    private HealthTextView mFirstConnectFailedTv;
    private String mProductId;
    private dcz mProductInfo;
    private HealthProgressBar mReconnectLoadingPb;
    private HealthTextView mReconnectTv;
    private long mStartConnectTime;
    private final HealthDevice.HealthDeviceKind mKind = HealthDevice.HealthDeviceKind.HDK_BLOOD_PRESSURE;
    private boolean mIsBtEnableShowing = false;
    private boolean mIsScanner = false;
    private CustomViewDialog mDialogForDisconnect = null;
    private boolean mIsConnectedBefore = false;
    private boolean mIsMeasuring = false;
    private boolean mIsHaveProductInfo = false;
    private int mMaxDownloadCount = 1;
    private BloodPressureHandler mBloodPressureHandler = new BloodPressureHandler();
    private NfcMeasureCallback mBloodPressureDeviceCallback = new NfcMeasureCallback() { // from class: com.huawei.health.ecologydevice.ui.measure.fragment.BloodPressureIntroductionFragment.1
        @Override // com.huawei.health.ecologydevice.callback.NfcMeasureCallback
        public void onDataChanged(HealthDevice healthDevice, HealthData healthData) {
            LogUtil.a(BloodPressureIntroductionFragment.TAG, "onDataChanged get HealthData");
            BloodPressureIntroductionFragment.this.saveBloodPressureData(healthDevice, healthData);
            if (BloodPressureIntroductionFragment.this.mBloodPressureHandler == null || !(healthData instanceof deo)) {
                return;
            }
            BloodPressureIntroductionFragment.this.mBloodPressureHandler.removeMessages(108);
            Message obtain = Message.obtain();
            obtain.what = 105;
            obtain.obj = healthData;
            BloodPressureIntroductionFragment.this.mBloodPressureHandler.sendMessage(obtain);
        }

        @Override // com.huawei.health.ecologydevice.callback.NfcMeasureCallback
        public void onStatusChanged(int i, int i2, int i3) {
            LogUtil.a(BloodPressureIntroductionFragment.TAG, "onStatusChanged and status=", Integer.valueOf(i));
            BloodPressureIntroductionFragment.this.sendEmptyMessageToHandler(102);
            if (i == 2) {
                BloodPressureIntroductionFragment.this.mIsConnectedBefore = true;
                djz.b();
                double c = dko.c(BloodPressureIntroductionFragment.this.mStartConnectTime);
                BloodPressureIntroductionFragment bloodPressureIntroductionFragment = BloodPressureIntroductionFragment.this;
                bloodPressureIntroductionFragment.tickBiNfcConnectBloodPressure(bloodPressureIntroductionFragment.getContext(), c, i2, i3);
                BloodPressureIntroductionFragment.this.monitorUploadDataToHota();
                LogUtil.a(BloodPressureIntroductionFragment.TAG, "TIME connected time:", Double.valueOf(c));
                return;
            }
            if (i != 3) {
                switch (i) {
                    case 10:
                        BloodPressureIntroductionFragment.this.sendEmptyMessageToHandler(107);
                        break;
                    case 11:
                        BloodPressureIntroductionFragment bloodPressureIntroductionFragment2 = BloodPressureIntroductionFragment.this;
                        bloodPressureIntroductionFragment2.tickBiNfcConnectBloodPressure(bloodPressureIntroductionFragment2.getContext(), 0.0d, i2, i3);
                        BloodPressureIntroductionFragment.this.mBloodPressureHandler.sendEmptyMessage(106);
                        break;
                    case 12:
                        BloodPressureIntroductionFragment.this.sendEmptyMessageToHandler(12);
                        LogUtil.a(BloodPressureIntroductionFragment.TAG, "BLUETOOTH is turn off");
                        break;
                    default:
                        LogUtil.h(BloodPressureIntroductionFragment.TAG, "not have this status");
                        break;
                }
                return;
            }
            if (BloodPressureIntroductionFragment.this.mIsMeasuring) {
                BloodPressureIntroductionFragment.this.removeMessage(108);
                BloodPressureIntroductionFragment.this.sendEmptyMessageToHandler(108);
            }
        }

        @Override // com.huawei.health.ecologydevice.callback.NfcMeasureCallback
        public void onStartMeasuring() {
            LogUtil.a(BloodPressureIntroductionFragment.TAG, "onStartMeasuring sent START_MEASURING");
            BloodPressureIntroductionFragment.this.mBloodPressureHandler.sendEmptyMessage(114);
        }
    };
    private ScanCallback mScanCallback = new ScanCallback() { // from class: com.huawei.health.ecologydevice.ui.measure.fragment.BloodPressureIntroductionFragment.2
        @Override // android.bluetooth.le.ScanCallback
        public void onScanResult(int i, ScanResult scanResult) {
            super.onScanResult(i, scanResult);
            LogUtil.a(BloodPressureIntroductionFragment.TAG, "onScanResult");
            if (scanResult == null || scanResult.getDevice() == null) {
                return;
            }
            LogUtil.a(BloodPressureIntroductionFragment.TAG, "ScanCallback result");
            BloodPressureIntroductionFragment.this.stopBleScanner();
            BloodPressureIntroductionFragment.this.initBloodPressureManager(scanResult.getDevice());
            BloodPressureIntroductionFragment.this.bindDevice();
            dko.b(BloodPressureIntroductionFragment.this.mProductId, BloodPressureIntroductionFragment.this.mDeviceMacAddress, BloodPressureIntroductionFragment.this.mDeviceMacAddress);
            BloodPressureIntroductionFragment.this.sendEmptyDelayMessage(101, 500L);
        }
    };

    static /* synthetic */ void lambda$saveBloodPressureData$0(int i, Object obj) {
    }

    @Override // com.huawei.health.ecologydevice.ui.BaseFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Bundle arguments = getArguments();
        if (arguments == null) {
            LogUtil.a(TAG, "bundle is null");
        } else {
            initParam(arguments);
        }
    }

    @Override // com.huawei.health.ecologydevice.ui.BaseFragment, androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        LogUtil.a(TAG, "in onCreateView");
        ViewGroup viewGroup2 = super.onCreateView(layoutInflater, viewGroup, bundle) instanceof ViewGroup ? (ViewGroup) super.onCreateView(layoutInflater, viewGroup, bundle) : null;
        this.child = layoutInflater.inflate(R.layout.blood_pressure_introduction_layout, viewGroup, false);
        if (viewGroup2 != null) {
            viewGroup2.addView(this.child);
            initView(this.child);
        }
        initViewStatus();
        initTitleBar();
        initDeviceDataTv();
        if (!CommonUtil.aa(BaseApplication.getContext()) || TextUtils.isEmpty(this.mDevicePid)) {
            LogUtil.a(TAG, "not have net or mDevicePid is null, not download");
            this.mIsHaveProductInfo = false;
            initBloodPressureManager(BluetoothAdapter.getDefaultAdapter().getRemoteDevice(this.mDeviceMacAddress));
            this.mProductInfo = getProductInfo();
            registerDeviceInfo();
            if (this.mProductInfo == null && (BloodPressureStartFromDeviceHelper.b(this.mDevicePid) || BloodPressureStartFromDeviceHelper.c())) {
                showDownloadFail();
                return viewGroup2;
            }
            onDeviceStateChanged();
            sendEmptyDelayMessage(101, 500L);
        } else {
            refreshProductInfo();
            this.mIsHaveProductInfo = true;
            sendEmptyDelayMessage(115, 500L);
        }
        initFaqsView();
        return viewGroup2;
    }

    private void handleUpdateProductMap() {
        LogUtil.a(TAG, "begin update product map");
        showDownloadView();
        sendEmptyDelayMessage(112, OpAnalyticsConstants.H5_LOADING_DELAY);
        cwt.a().b(this.mKind.name(), this.mDevicePid, new DownloadDeviceInfoCallBack() { // from class: com.huawei.health.ecologydevice.ui.measure.fragment.BloodPressureIntroductionFragment.3
            @Override // com.huawei.health.ecologydevice.clouddevice.DownloadDeviceInfoCallBack
            public void onSuccess() {
                LogUtil.a(BloodPressureIntroductionFragment.TAG, "onSuccess");
                BloodPressureIntroductionFragment.this.refreshProductInfo();
                BloodPressureIntroductionFragment.this.removeMessage(112);
                BloodPressureIntroductionFragment.this.sendEmptyMessage(113);
            }

            @Override // com.huawei.health.ecologydevice.clouddevice.DownloadDeviceInfoCallBack
            public void onFailure(int i) {
                LogUtil.a(BloodPressureIntroductionFragment.TAG, "onFailure");
            }

            @Override // com.huawei.health.ecologydevice.clouddevice.DownloadDeviceInfoCallBack
            public void onNetworkError() {
                LogUtil.a(BloodPressureIntroductionFragment.TAG, "onNetworkError");
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void bindDevice() {
        if (((DeviceManagerApi) Services.c("PluginDevice", DeviceManagerApi.class)).isBondedDevice(this.mDeviceMacAddress)) {
            LogUtil.a(TAG, "device is bound");
        } else {
            ((HealthDeviceEntryApi) Services.c("PluginDevice", HealthDeviceEntryApi.class)).bindDevice(this.mProductId, this.mDevice, new IDeviceEventHandler() { // from class: com.huawei.health.ecologydevice.ui.measure.fragment.BloodPressureIntroductionFragment.4
                @Override // com.huawei.health.device.callback.IDeviceEventHandler
                public void onDeviceFound(HealthDevice healthDevice) {
                    LogUtil.c(BloodPressureIntroductionFragment.TAG, "onDeviceFound");
                }

                @Override // com.huawei.health.device.callback.IDeviceEventHandler
                public void onScanFailed(int i) {
                    LogUtil.c(BloodPressureIntroductionFragment.TAG, "onScanFailed code:", Integer.valueOf(i));
                }

                @Override // com.huawei.health.device.callback.IDeviceEventHandler
                public void onStateChanged(int i) {
                    LogUtil.a(BloodPressureIntroductionFragment.TAG, "onStateChanged code :", Integer.valueOf(i));
                }
            });
        }
    }

    private void handleCancel() {
        LogUtil.a(TAG, "enter handleCancel");
        Iterator<msq> it = msn.c().b().iterator();
        while (it.hasNext()) {
            msq next = it.next();
            if (dks.h(this.mProductId)) {
                msj.a().b(next);
            }
        }
    }

    private Bitmap getProductImage() {
        dcz dczVar = this.mProductInfo;
        String a2 = (dczVar == null || dczVar.e().size() <= 0) ? "" : dcq.b().a(this.mProductId, this.mProductInfo.e().get(0).e());
        if (TextUtils.isEmpty(a2)) {
            return null;
        }
        return BitmapFactory.decodeFile(a2);
    }

    private void setProductNameAndImage() {
        dcz dczVar = this.mProductInfo;
        if (dczVar != null && dczVar.n() != null) {
            super.setTitle(dcx.d(this.mProductId, this.mProductInfo.n().b()));
        }
        if (this.mDefaultDeviceImage == null || this.mDeviceImage == null) {
            return;
        }
        if (getProductImage() != null) {
            this.mDefaultDeviceImage.setVisibility(8);
            this.mDeviceImage.setVisibility(0);
            this.mDeviceImage.setImageBitmap(getProductImage());
        } else {
            this.mDefaultDeviceImage.setVisibility(0);
            this.mDeviceImage.setVisibility(8);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void refreshProductInfo() {
        dcz productInfo = getProductInfo();
        this.mProductInfo = productInfo;
        if (productInfo == null) {
            LogUtil.h(TAG, "handlerToRefresh productInfo is null");
        } else {
            BloodPressureStartFromDeviceHelper.e(productInfo);
            sendEmptyMessage(111);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendEmptyMessage(int i) {
        BloodPressureHandler bloodPressureHandler = this.mBloodPressureHandler;
        if (bloodPressureHandler != null) {
            bloodPressureHandler.sendEmptyMessage(i);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendEmptyDelayMessage(int i, long j) {
        BloodPressureHandler bloodPressureHandler = this.mBloodPressureHandler;
        if (bloodPressureHandler != null) {
            bloodPressureHandler.sendEmptyMessageDelayed(i, j);
        }
    }

    private void showDownloadView() {
        this.mConnectStatusTv.setText(R.string.IDS_device_download_resource);
        this.mReconnectLoadingPb.setVisibility(0);
        this.mReconnectTv.setVisibility(8);
    }

    private void showDownloadFail() {
        this.mConnectStatusTv.setText(R.string._2130841543_res_0x7f020fc7);
        this.mFirstConnectFailedTv.setVisibility(0);
        this.mFirstConnectFailedTv.setText(R.string.IDS_device_download_fail_tips);
        this.mReconnectLoadingPb.setVisibility(8);
        this.mReconnectTv.setVisibility(0);
        this.mReconnectTv.setText(R.string._2130850161_res_0x7f023171);
    }

    private void reDownloadResource() {
        if (CommonUtil.aa(BaseApplication.getContext())) {
            LogUtil.a(TAG, "reDownloadResource: re-download");
            this.mMaxDownloadCount--;
            showDownloadView();
            sendEmptyDelayMessage(112, OpAnalyticsConstants.H5_LOADING_DELAY);
            handleUpdateProductMap();
            return;
        }
        LogUtil.a(TAG, "reDownloadResource: judgingBluetoothOnOff");
        if (BloodPressureStartFromDeviceHelper.b(this.mDevicePid) || BloodPressureStartFromDeviceHelper.c()) {
            showDownloadFail();
        } else {
            sendEmptyDelayMessage(101, 500L);
        }
    }

    @Override // com.huawei.health.ecologydevice.ui.BaseFragment, androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        LogUtil.a(TAG, "in onResume");
        queryLastBloodPressureData();
    }

    @Override // com.huawei.health.ecologydevice.ui.BaseFragment, androidx.fragment.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        djz.d(TAG);
        stopBleScanner();
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroyView() {
        super.onDestroyView();
        BloodPressureHandler bloodPressureHandler = this.mBloodPressureHandler;
        if (bloodPressureHandler != null) {
            bloodPressureHandler.removeCallbacksAndMessages(null);
            this.mBloodPressureHandler = null;
        }
        czu czuVar = this.mBloodPressureManager;
        if (czuVar != null) {
            czuVar.releaseResource();
        }
        handleCancel();
    }

    @Override // com.huawei.health.ecologydevice.ui.BaseFragment
    public boolean onBackPressed() {
        finishFragment();
        return false;
    }

    @Override // androidx.fragment.app.Fragment
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        LogUtil.a(TAG, "onActivityResult requestCode =", Integer.valueOf(i2), "resultCode =", Integer.valueOf(i2));
        if (i == 2) {
            if (i2 == -1) {
                LogUtil.a(TAG, "onActivityResult BT is Enable");
                onPermissionGranted();
            } else {
                popupFragment(null);
            }
            this.mIsBtEnableShowing = false;
            return;
        }
        if (i == 1) {
            if (!dja.VG_(this.mainActivity)) {
                popupFragment(null);
            }
            if (!isBtEnabled()) {
                openBluetooth();
                return;
            } else {
                LogUtil.a(TAG, "onActivityResult GpsLocation is Enable");
                onPermissionGranted();
                return;
            }
        }
        LogUtil.a(TAG, "onActivityResult else is", Integer.valueOf(i2));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onPermissionGranted() {
        LogUtil.a(TAG, "onPermissionGranted ,mIsScanner =  ", Boolean.valueOf(this.mIsScanner));
        if (this.mIsScanner) {
            sendEmptyMessage(115);
        } else {
            sendEmptyMessage(101);
        }
    }

    private void initView(View view) {
        this.mDefaultDeviceImage = (ImageView) view.findViewById(R.id.device_default_image_iv);
        this.mDeviceImage = (ImageView) view.findViewById(R.id.device_image_iv);
        this.mConnectStatusTv = (HealthTextView) view.findViewById(R.id.device_connect_status);
        this.mFirstConnectFailedTv = (HealthTextView) view.findViewById(R.id.device_first_connect_failed_tips);
        this.mReconnectTv = (HealthTextView) view.findViewById(R.id.device_reconnect_tv);
        this.mReconnectLoadingPb = (HealthProgressBar) view.findViewById(R.id.device_reconnect_pb);
        this.mBloodPressureTv = (HealthTextView) view.findViewById(R.id.third_party_device_type);
        this.mBloodPressureDataTv = (HealthTextView) view.findViewById(R.id.device_measure_date);
        this.mBloodPressureMeasureValueTv = (HealthTextView) view.findViewById(R.id.device_measure_value);
        this.mBloodPressureMeasureValueUnitTv = (HealthTextView) view.findViewById(R.id.device_measure_value_unit);
        this.mDeviceMeasureValue = (ViewGroup) view.findViewById(R.id.device_measure_value_layout);
        this.mReconnectTv.setOnClickListener(this);
        this.mDeviceMeasureValue.setOnClickListener(this);
    }

    private void initViewStatus() {
        this.mConnectStatusTv.setText(R.string.IDS_device_rope_device_connecting);
        this.mReconnectTv.setVisibility(8);
        this.mReconnectLoadingPb.setVisibility(8);
        this.mFirstConnectFailedTv.setVisibility(8);
        this.mDeviceMeasureValue.setClickable(false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showConnectingStatus() {
        this.mConnectStatusTv.setText(R.string.IDS_device_rope_device_connecting);
        this.mReconnectTv.setVisibility(8);
        this.mReconnectLoadingPb.setVisibility(0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showMeasureFailed() {
        this.mIsMeasuring = false;
        this.mReconnectTv.setVisibility(0);
        this.mReconnectLoadingPb.setVisibility(8);
        this.mReconnectTv.setClickable(false);
        this.mReconnectTv.setText(R.string.IDS_device_measureactivity_guide_start_measure);
    }

    private void initDeviceDataTv() {
        Resources resources = getContext().getResources();
        if (this.mKind == HealthDevice.HealthDeviceKind.HDK_BLOOD_PRESSURE) {
            this.mBloodPressureTv.setText(resources.getString(R.string.IDS_hw_show_main_home_page_bloodpressure));
            this.mBloodPressureMeasureValueUnitTv.setText(resources.getString(R.string._2130843507_res_0x7f021773));
        }
    }

    private void initFaqsView() {
        this.mBloodPressureFaqsLayout = (LinearLayout) this.child.findViewById(R.id.blood_pressure_faqs);
        this.mBloodPressureFaqsCloseVoice = (RelativeLayout) this.child.findViewById(R.id.faqs_close_voice);
        this.mBloodPressureFaqsSyncData = (RelativeLayout) this.child.findViewById(R.id.faqs_sync_data);
        this.mBloodPressureFaqsCloseVoice.setOnClickListener(this);
        this.mBloodPressureFaqsSyncData.setOnClickListener(this);
        if ("O006".equals(this.mDevicePid) && !TextUtils.isEmpty(dcq.b().c(this.mProductId))) {
            this.mBloodPressureFaqsLayout.setVisibility(0);
        } else {
            this.mBloodPressureFaqsLayout.setVisibility(8);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onDeviceStateChanged() {
        int d = this.mBloodPressureManager.d();
        if (d == 1) {
            this.mConnectStatusTv.setText(R.string.IDS_device_rope_device_connecting);
            this.mReconnectLoadingPb.setVisibility(0);
            this.mReconnectTv.setVisibility(8);
            return;
        }
        if (d != 2) {
            if (d != 3) {
                return;
            }
            this.mConnectStatusTv.setText(R.string.IDS_device_rope_device_not_connected);
            this.mReconnectLoadingPb.setVisibility(8);
            if (this.mIsConnectedBefore) {
                this.mReconnectTv.setVisibility(0);
                this.mReconnectTv.setText(R.string.IDS_device_rope_device_reconnect);
                return;
            }
            return;
        }
        LogUtil.a(TAG, "mIsHaveProductInfo ", Boolean.valueOf(this.mIsHaveProductInfo));
        if (this.mIsHaveProductInfo) {
            setProductNameAndImage();
        }
        this.mConnectStatusTv.setText(R.string.IDS_device_rope_device_connected);
        this.mReconnectTv.setVisibility(0);
        this.mReconnectTv.setText(R.string.IDS_device_measureactivity_guide_start_measure);
        this.mReconnectLoadingPb.setVisibility(8);
        this.mFirstConnectFailedTv.setVisibility(8);
        if (this.mIsMeasuring) {
            this.mReconnectTv.setText(R.string.IDS_device_measureactivity_measuring);
            this.mReconnectLoadingPb.setVisibility(0);
        }
    }

    private void initTitleBar() {
        this.mCustomTitleBar.setRightButtonVisibility(8);
        Resources resources = getContext().getResources();
        if (resources != null) {
            this.mCustomTitleBar.setTitleBarBackgroundColor(resources.getColor(R.color._2131299340_res_0x7f090c0c));
            super.setTitle(resources.getString(R.string._2130837741_res_0x7f0200ed));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleViewAfterMeasured(deo deoVar) {
        this.mIsMeasuring = false;
        onDeviceStateChanged();
        setViewClickable(false);
        updateBloodPressureData(deoVar);
    }

    private void updateBloodPressureData(HealthData healthData) {
        if (this.mBloodPressureDataTv == null || this.mBloodPressureMeasureValueTv == null) {
            LogUtil.h(TAG, "view is null");
            return;
        }
        if (healthData instanceof deo) {
            deo deoVar = (deo) healthData;
            short systolic = deoVar.getSystolic();
            short diastolic = deoVar.getDiastolic();
            long startTime = healthData.getStartTime();
            this.mBloodPressureDataTv.setVisibility(0);
            String e = UnitUtil.e(systolic, 1, 0);
            String e2 = UnitUtil.e(diastolic, 1, 0);
            this.mBloodPressureMeasureValueTv.setText(e + "/" + e2);
            this.mBloodPressureDataTv.setText(nsj.a(startTime));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleViewAfterConnectFailed() {
        this.mConnectStatusTv.setText(R.string.IDS_device_common_err_connect_fail_tips);
        this.mFirstConnectFailedTv.setVisibility(8);
        this.mReconnectLoadingPb.setVisibility(8);
        this.mReconnectTv.setVisibility(0);
        this.mReconnectTv.setText(R.string.IDS_device_measureactivity_guide_start_measure);
        this.mReconnectTv.setTextColor(getResources().getColor(R.color._2131299241_res_0x7f090ba9));
        showDialog(getString(R.string.IDS_device_common_err_connect_fail_tips));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void saveBloodPressureData(HealthDevice healthDevice, HealthData healthData) {
        dfd dfdVar = new dfd(10002);
        this.mDataHandler = dfdVar;
        dfdVar.e(new IBaseResponseCallback() { // from class: com.huawei.health.ecologydevice.ui.measure.fragment.BloodPressureIntroductionFragment$$ExternalSyntheticLambda0
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public final void d(int i, Object obj) {
                BloodPressureIntroductionFragment.lambda$saveBloodPressureData$0(i, obj);
            }
        });
        this.mDataHandler.onDataChanged(healthDevice, healthData);
    }

    private void queryLastBloodPressureData() {
        HiAggregateOption createHiAggregateOption = createHiAggregateOption();
        createHiAggregateOption.setStartTime(0L);
        createHiAggregateOption.setEndTime(System.currentTimeMillis());
        HiHealthManager.d(this.mainActivity).aggregateHiHealthData(createHiAggregateOption, new HiAggregateListener() { // from class: com.huawei.health.ecologydevice.ui.measure.fragment.BloodPressureIntroductionFragment.5
            @Override // com.huawei.hihealth.data.listener.HiAggregateListener
            public void onResult(List<HiHealthData> list, int i, int i2) {
                LogUtil.a(BloodPressureIntroductionFragment.TAG, "queryBloodPressureData onResult called");
                if (koq.b(list)) {
                    LogUtil.h(BloodPressureIntroductionFragment.TAG, "queryBloodPressureData onResult blood pressure data is null");
                    Message obtain = Message.obtain();
                    obtain.what = 103;
                    BloodPressureIntroductionFragment.this.sendMessageToHandler(obtain);
                    return;
                }
                Message obtain2 = Message.obtain();
                obtain2.what = 104;
                obtain2.obj = list.get(0);
                BloodPressureIntroductionFragment.this.sendMessageToHandler(obtain2);
            }

            @Override // com.huawei.hihealth.data.listener.HiAggregateListener
            public void onResultIntent(int i, List<HiHealthData> list, int i2, int i3) {
                LogUtil.a(BloodPressureIntroductionFragment.TAG, "queryBloodPressureData onResultIntent errorCode = ", Integer.valueOf(i2));
            }
        });
    }

    private void initParam(Bundle bundle) {
        String string = bundle.getString("BLE_FROM_QRCODE");
        this.mDeviceMacAddress = string;
        if (TextUtils.isEmpty(string)) {
            LogUtil.h(TAG, "mDeviceMacAddress is empty");
            return;
        }
        this.mDeviceName = bundle.getString("BRAND_FROM_QRCODE");
        this.mDeviceType = bundle.getString("DEVICE_TYPE_INDEX");
        String string2 = bundle.getString("PID_FROM_QRCODE");
        this.mDevicePid = string2;
        LogUtil.c(TAG, "initParam: mDeviceName=", this.mDeviceName, " mDeviceType=", this.mDeviceType, " mDevicePid=", string2);
        if (!TextUtils.isEmpty(this.mDevicePid)) {
            czu.e().a(this.mDevicePid);
        }
        if (TextUtils.isEmpty(bundle.getString("PAYLOAD_FROM_NFC"))) {
            LogUtil.c(TAG, "QRCODE");
            this.mEnterType = "QRCODE";
        } else {
            LogUtil.c(TAG, "NFC");
            this.mEnterType = "NFC";
        }
        if (this.mBluetoothAdapter != null || getActivity() == null) {
            return;
        }
        BluetoothManager bluetoothManager = getActivity().getSystemService("bluetooth") instanceof BluetoothManager ? (BluetoothManager) getActivity().getSystemService("bluetooth") : null;
        if (bluetoothManager == null) {
            LogUtil.h(TAG, "no BT Manager in this phone");
        } else {
            this.mBluetoothAdapter = bluetoothManager.getAdapter();
        }
    }

    private dcz getProductInfo() {
        ProductMapInfo d;
        LogUtil.c(TAG, "mProductId = ", this.mProductId, " mDevicePid = ", this.mDevicePid);
        if (TextUtils.isEmpty(this.mProductId) && TextUtils.isEmpty(this.mDevicePid)) {
            return null;
        }
        if (TextUtils.isEmpty(this.mProductId) && (d = ProductMap.d(this.mDevicePid)) != null) {
            this.mProductId = d.h();
        }
        return ResourceManager.e().d(this.mProductId);
    }

    private void registerDeviceInfo() {
        int a2 = !TextUtils.isEmpty(this.mDeviceType) ? CommonUtil.a(this.mDeviceType, 10) : 10001;
        dcz dczVar = this.mProductInfo;
        if (dczVar != null && dczVar.n() != null) {
            this.mIsHaveProductInfo = true;
            registerDeviceInfo(this.mDevice, dcx.d(this.mProductId, this.mProductInfo.n().b()), a2, this.mProductId);
        } else {
            this.mIsHaveProductInfo = false;
            registerDeviceInfo(this.mDevice, getResources().getString(R.string._2130837741_res_0x7f0200ed), a2, this.mProductId);
        }
        BloodPressureStartFromDeviceHelper.e(this.mProductInfo);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void initBloodPressureManager(BluetoothDevice bluetoothDevice) {
        LogUtil.a(TAG, "initBloodPressureManager");
        this.mDevice = cxh.Ra_(bluetoothDevice);
        if (this.mBloodPressureManager == null) {
            this.mBloodPressureManager = czu.e();
        }
        this.mBloodPressureManager.init(this.mBloodPressureDeviceCallback);
        dkq.c().b(this.mKind.name());
        djz.c(TAG);
    }

    private void checkPermissions() {
        PermissionUtil.PermissionType d = PermissionDialogHelper.d();
        PermissionDialogHelper.VB_(getActivity(), d, new AnonymousClass6(this.mainActivity, d));
    }

    /* renamed from: com.huawei.health.ecologydevice.ui.measure.fragment.BloodPressureIntroductionFragment$6, reason: invalid class name */
    class AnonymousClass6 extends CustomPermissionAction {
        final /* synthetic */ PermissionUtil.PermissionType val$permissionType;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass6(Context context, PermissionUtil.PermissionType permissionType) {
            super(context);
            this.val$permissionType = permissionType;
        }

        @Override // com.huawei.hwcommonmodel.utils.permission.PermissionsResultAction
        public void onGranted() {
            if (this.val$permissionType != PermissionUtil.PermissionType.LOCATION || dja.VG_(BloodPressureIntroductionFragment.this.mainActivity)) {
                if (BloodPressureIntroductionFragment.this.isBtEnabled()) {
                    BloodPressureIntroductionFragment.this.onPermissionGranted();
                    return;
                } else {
                    LogUtil.a(BloodPressureIntroductionFragment.TAG, "Permission Bt is close");
                    BloodPressureIntroductionFragment.this.openBluetooth();
                    return;
                }
            }
            LogUtil.a(BloodPressureIntroductionFragment.TAG, "Permission GpsClose is close");
            BloodPressureIntroductionFragment.this.openGpsLocation();
        }

        @Override // com.huawei.ui.commonui.utils.CustomPermissionAction, com.huawei.hwcommonmodel.utils.permission.PermissionsResultAction
        public void onDenied(String str) {
            super.onDenied(str);
            BloodPressureIntroductionFragment.this.finishFragment();
        }

        @Override // com.huawei.ui.commonui.utils.CustomPermissionAction, com.huawei.hwcommonmodel.utils.permission.PermissionsResultAction
        public void onForeverDenied(PermissionUtil.PermissionType permissionType) {
            nsn.cLK_(BloodPressureIntroductionFragment.this.mainActivity, permissionType, null, new View.OnClickListener() { // from class: com.huawei.health.ecologydevice.ui.measure.fragment.BloodPressureIntroductionFragment$6$$ExternalSyntheticLambda0
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    BloodPressureIntroductionFragment.AnonymousClass6.this.m282x1a085f2c(view);
                }
            }, new View.OnClickListener() { // from class: com.huawei.health.ecologydevice.ui.measure.fragment.BloodPressureIntroductionFragment$6$$ExternalSyntheticLambda1
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    BloodPressureIntroductionFragment.AnonymousClass6.this.m283x310242d(view);
                }
            });
        }

        /* renamed from: lambda$onForeverDenied$0$com-huawei-health-ecologydevice-ui-measure-fragment-BloodPressureIntroductionFragment$6, reason: not valid java name */
        /* synthetic */ void m282x1a085f2c(View view) {
            BloodPressureIntroductionFragment.this.finishFragment();
            ViewClickInstrumentation.clickOnView(view);
        }

        /* renamed from: lambda$onForeverDenied$1$com-huawei-health-ecologydevice-ui-measure-fragment-BloodPressureIntroductionFragment$6, reason: not valid java name */
        /* synthetic */ void m283x310242d(View view) {
            BloodPressureIntroductionFragment.this.finishFragment();
            ViewClickInstrumentation.clickOnView(view);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void openGpsLocation() {
        LogUtil.a(TAG, "openGpsLocation jump to GPS Setting");
        dif.Vp_(this.mainActivity, isAdded(), new IdialogButtonClickCallback() { // from class: com.huawei.health.ecologydevice.ui.measure.fragment.BloodPressureIntroductionFragment.7
            @Override // com.huawei.health.ecologydevice.callback.IdialogButtonClickCallback
            public void onClick(View view) {
                BloodPressureIntroductionFragment.this.startActivityForResult(new Intent("android.settings.LOCATION_SOURCE_SETTINGS"), 1);
                ViewClickInstrumentation.clickOnView(view);
            }

            @Override // com.huawei.health.ecologydevice.callback.IdialogButtonClickCallback
            public void onCancelClick(View view) {
                LogUtil.a(BloodPressureIntroductionFragment.TAG, "openGpsLocation onCancelClick");
                BloodPressureIntroductionFragment.this.finishFragment();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startScanner() {
        this.mIsScanner = true;
        if (PermissionUtil.e(this.mainActivity, PermissionDialogHelper.d()) != PermissionUtil.PermissionResult.GRANTED) {
            LogUtil.a(TAG, "connectDevice checkPermissions");
            checkPermissions();
            return;
        }
        if (!dja.VG_(this.mainActivity) && Build.VERSION.SDK_INT < 30) {
            LogUtil.a(TAG, "connectDevice openGpsLocation");
            openGpsLocation();
            return;
        }
        if (!isBtEnabled()) {
            LogUtil.a(TAG, "connectDevice openBluetooth");
            openBluetooth();
            return;
        }
        LogUtil.a(TAG, "startScanner");
        ArrayList arrayList = new ArrayList(10);
        arrayList.add(new ScanFilter.Builder().setDeviceAddress(this.mDeviceMacAddress).build());
        try {
            BluetoothLeScanner bluetoothLeScanner = this.mBluetoothAdapter.getBluetoothLeScanner();
            if (bluetoothLeScanner == null) {
                LogUtil.h(TAG, "scanner is null");
                return;
            }
            bluetoothLeScanner.startScan(arrayList, new ScanSettings.Builder().setScanMode(1).build(), this.mScanCallback);
            showConnectingStatus();
            sendEmptyDelayMessage(116, 9000L);
        } catch (SecurityException e) {
            LogUtil.b(TAG, "startScanner SecurityException:", ExceptionUtils.d(e));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void stopBleScanner() {
        LogUtil.a(TAG, "stopBleScanner");
        BloodPressureHandler bloodPressureHandler = this.mBloodPressureHandler;
        if (bloodPressureHandler != null) {
            bloodPressureHandler.removeMessages(115);
            this.mBloodPressureHandler.removeMessages(116);
        }
        BluetoothAdapter bluetoothAdapter = this.mBluetoothAdapter;
        if (bluetoothAdapter == null) {
            LogUtil.h(TAG, "stopBleScanner mBluetoothAdapter is null");
            return;
        }
        try {
            BluetoothLeScanner bluetoothLeScanner = bluetoothAdapter.getBluetoothLeScanner();
            if (bluetoothLeScanner != null) {
                bluetoothLeScanner.stopScan(this.mScanCallback);
            }
        } catch (SecurityException e) {
            LogUtil.b(TAG, "stopBleScanner SecurityException:", ExceptionUtils.d(e));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void refreshBloodPressureData(HiHealthData hiHealthData) {
        if (this.mBloodPressureMeasureValueTv == null || this.mBloodPressureDataTv == null) {
            LogUtil.h(TAG, "refreshBloodPressureData view is null");
            return;
        }
        if (hiHealthData == null) {
            showEmptyView();
            return;
        }
        double d = hiHealthData.getDouble("BLOOD_PRESSURE_SYSTOLIC");
        double d2 = hiHealthData.getDouble("BLOOD_PRESSURE_DIASTOLIC");
        long startTime = hiHealthData.getStartTime();
        this.mBloodPressureDataTv.setVisibility(0);
        String e = UnitUtil.e(d, 1, 0);
        String e2 = UnitUtil.e(d2, 1, 0);
        this.mBloodPressureMeasureValueTv.setText(e + "/" + e2);
        this.mBloodPressureDataTv.setText(nsj.a(startTime));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showEmptyView() {
        HealthTextView healthTextView;
        if (this.mBloodPressureMeasureValueTv == null || (healthTextView = this.mBloodPressureDataTv) == null || this.mBloodPressureMeasureValueUnitTv == null) {
            LogUtil.h(TAG, "showEmptyView view is null");
            return;
        }
        healthTextView.setVisibility(8);
        if (this.mKind == HealthDevice.HealthDeviceKind.HDK_BLOOD_PRESSURE) {
            this.mBloodPressureMeasureValueTv.setText("--/--");
        }
    }

    private void setViewClickable(boolean z) {
        if (getContext() == null) {
            return;
        }
        this.mDeviceMeasureValue.setClickable(false);
        this.mReconnectTv.setClickable(!z);
        this.mReconnectTv.setTextColor(getResources().getColor(!z ? R.color._2131296570_res_0x7f09013a : R.color._2131299241_res_0x7f090ba9));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void connectDevice() {
        this.mIsScanner = false;
        if (!PermissionDialogHelper.Vy_(this.mainActivity)) {
            LogUtil.a(TAG, "connectDevice checkPermissions");
            checkPermissions();
        } else if (!isBtEnabled()) {
            LogUtil.a(TAG, "connectDevice openBluetooth");
            openBluetooth();
        } else if (this.mBloodPressureManager != null) {
            this.mStartConnectTime = System.currentTimeMillis();
            this.mBloodPressureManager.b(this.mDeviceMacAddress);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void openBluetooth() {
        if (this.mIsBtEnableShowing) {
            LogUtil.a(TAG, "openBlueTooth is mIsBtEnableShowing");
            return;
        }
        try {
            if (!isAdded()) {
                LogUtil.a(TAG, "openBlueTooth fragment is not attach");
            }
            this.mIsBtEnableShowing = true;
            startActivityForResult(new Intent("android.bluetooth.adapter.action.REQUEST_ENABLE"), 2);
        } catch (ActivityNotFoundException unused) {
            LogUtil.b(TAG, "openBlueTooth from fragment ActivityNotFoundException!");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isBtEnabled() {
        BluetoothAdapter bluetoothAdapter = this.mBluetoothAdapter;
        if (bluetoothAdapter == null) {
            LogUtil.h(TAG, "no BT in this phone");
            finishFragment();
            return false;
        }
        return bluetoothAdapter.isEnabled();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void finishFragment() {
        popupFragment(null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showDialog(String str) {
        if (getContext() == null) {
            return;
        }
        if (this.mDialogForDisconnect == null) {
            View inflate = LayoutInflater.from(cpp.a()).inflate(R.layout.blood_pressure_connect_timeout_dialog, (ViewGroup) null);
            CustomViewDialog.Builder builder = new CustomViewDialog.Builder(getContext());
            builder.a(str).czg_(inflate).cze_(R.string._2130841794_res_0x7f0210c2, new View.OnClickListener() { // from class: com.huawei.health.ecologydevice.ui.measure.fragment.BloodPressureIntroductionFragment.8
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    LogUtil.a(BloodPressureIntroductionFragment.TAG, "showConnectionNoteDialog onclick PositiveButton");
                    BloodPressureIntroductionFragment.this.finishFragment();
                    ViewClickInstrumentation.clickOnView(view);
                }
            });
            CustomViewDialog e = builder.e();
            this.mDialogForDisconnect = e;
            e.setCancelable(false);
        }
        this.mDialogForDisconnect.show();
    }

    private void showMeasuringView() {
        this.mReconnectTv.setVisibility(0);
        this.mReconnectLoadingPb.setVisibility(0);
        this.mReconnectTv.setText(R.string.IDS_device_measureactivity_measuring);
        setViewClickable(true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showFirstConnectFailedView() {
        this.mFirstConnectFailedTv.setVisibility(0);
        this.mFirstConnectFailedTv.setText(R.string.IDS_device_err_connect_timeout_tips_info1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showBluetoothOffView() {
        this.mFirstConnectFailedTv.setVisibility(8);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void removeMessage(int i) {
        BloodPressureHandler bloodPressureHandler = this.mBloodPressureHandler;
        if (bloodPressureHandler != null) {
            bloodPressureHandler.removeMessages(i);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendMessageToHandler(Message message) {
        BloodPressureHandler bloodPressureHandler = this.mBloodPressureHandler;
        if (bloodPressureHandler != null) {
            bloodPressureHandler.sendMessage(message);
        }
    }

    private void sendMessageToHandlerWithDelay(int i, long j) {
        BloodPressureHandler bloodPressureHandler = this.mBloodPressureHandler;
        if (bloodPressureHandler != null) {
            bloodPressureHandler.sendEmptyMessageDelayed(i, j);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendEmptyMessageToHandler(int i) {
        BloodPressureHandler bloodPressureHandler = this.mBloodPressureHandler;
        if (bloodPressureHandler != null) {
            bloodPressureHandler.sendEmptyMessage(i);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void tickBiNfcConnectBloodPressure(Context context, double d, int i, int i2) {
        new dko.c().e(d).c(this.mProductId).d(this.mDeviceType).b(this.mEnterType).e(i2).i(this.mProductId).b(i).b().b(context);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void monitorUploadDataToHota() {
        if (!"bc23383f-d3c8-48fe-977a-da6d200696d9".equals(this.mProductId) || this.mDevice == null) {
            return;
        }
        LogUtil.a(TAG, " monitorUploadDataToHota");
        dlb.a(this.mDevice.getDeviceName());
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
        if (view == this.mReconnectTv) {
            reConnectClick();
        } else if (view == this.mDeviceMeasureValue) {
            FragmentActivity activity = getActivity();
            if (activity == null) {
                LogUtil.h(TAG, "BloodPressureResultFragment saveBloodPressureData() onResponse() mainActivity == null");
                ViewClickInstrumentation.clickOnView(view);
                return;
            }
            Intent intent = new Intent();
            intent.setPackage(cez.w);
            intent.putExtra("healthdata", "MyHealthData_jump_mainActivity");
            intent.setClassName(cez.w, "com.huawei.ui.main.stories.health.activity.healthdata.KnitBloodPressureActivity");
            gnm.aPB_(activity, intent);
            finishFragment();
        } else if (view == this.mBloodPressureFaqsCloseVoice) {
            diy.e(getContext(), this.mProductId, "feature=voice");
        } else if (view == this.mBloodPressureFaqsSyncData) {
            diy.e(getContext(), this.mProductId, "feature=connect");
        } else {
            LogUtil.h(TAG, "not have this click");
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    private void reConnectClick() {
        if (this.mReconnectTv.getText().toString().equals(getResources().getString(R.string.IDS_device_measureactivity_guide_start_measure))) {
            LogUtil.a(TAG, "start measure");
            czu czuVar = this.mBloodPressureManager;
            if (czuVar == null || czuVar.d() != 2) {
                return;
            }
            startMeasure();
            return;
        }
        if (this.mReconnectTv.getText().toString().equals(getResources().getString(R.string._2130850161_res_0x7f023171))) {
            this.mFirstConnectFailedTv.setVisibility(8);
            reDownloadResource();
            return;
        }
        if (this.mReconnectTv.getText().toString().equals(getResources().getString(R.string.IDS_device_rope_device_reconnect))) {
            if (!isBtEnabled()) {
                LogUtil.a(TAG, "reConnectClick openBluetooth");
                openBluetooth();
                return;
            }
            LogUtil.a(TAG, "reconnect");
            czu czuVar2 = this.mBloodPressureManager;
            if (czuVar2 == null || czuVar2.d() != 3) {
                return;
            }
            this.mBloodPressureManager.b();
            sendEmptyDelayMessage(101, 500L);
            return;
        }
        LogUtil.h(TAG, "not have this click");
    }

    private void startMeasure() {
        this.mIsMeasuring = true;
        if (BloodPressureStartFromDeviceHelper.c()) {
            LogUtil.a(TAG, "NewMeasurementProcedure");
            if (BloodPressureStartFromDeviceHelper.b(this.mDevicePid) || BloodPressureStartFromDeviceHelper.c()) {
                enterDeviceSilentGuideFragment(this.mProductId, this.mDeviceMacAddress);
                return;
            }
            this.mBloodPressureManager.g();
        } else {
            this.mBloodPressureManager.c();
        }
        showMeasuringView();
        sendMessageToHandlerWithDelay(108, 120000L);
    }

    private void enterDeviceSilentGuideFragment(String str, String str2) {
        Bundle bundle = new Bundle();
        bundle.putString("view", "startMeasureFromDevice");
        ContentValues contentValues = new ContentValues();
        contentValues.put("uniqueId", str2);
        contentValues.put("productId", str);
        bundle.putParcelable("commonDeviceInfo", contentValues);
        Fragment deviceSilentGuideFragment = new DeviceSilentGuideFragment();
        deviceSilentGuideFragment.setArguments(bundle);
        addFragment(deviceSilentGuideFragment);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showStartMeasureAnimation() {
        czu czuVar = this.mBloodPressureManager;
        if (czuVar == null || czuVar.d() != 2) {
            return;
        }
        this.mIsMeasuring = true;
        showMeasuringView();
        sendMessageToHandlerWithDelay(108, 120000L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void downTimeOut() {
        this.mIsHaveProductInfo = false;
        handleCancel();
        if (this.mMaxDownloadCount <= 0) {
            if (BloodPressureStartFromDeviceHelper.b(this.mDevicePid) || BloodPressureStartFromDeviceHelper.c()) {
                showDownloadFail();
                return;
            } else {
                BloodPressureStartFromDeviceHelper.e((dcz) null);
                sendEmptyDelayMessage(101, 500L);
                return;
            }
        }
        showDownloadFail();
    }

    static class BloodPressureHandler extends Handler {
        private final WeakReference<BloodPressureIntroductionFragment> mFragment;

        private BloodPressureHandler(BloodPressureIntroductionFragment bloodPressureIntroductionFragment) {
            super(Looper.myLooper());
            this.mFragment = new WeakReference<>(bloodPressureIntroductionFragment);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            BloodPressureIntroductionFragment bloodPressureIntroductionFragment = this.mFragment.get();
            if (bloodPressureIntroductionFragment == null || !bloodPressureIntroductionFragment.isAdded() || bloodPressureIntroductionFragment.isRemoving() || bloodPressureIntroductionFragment.isDetached() || !preHandle(message, bloodPressureIntroductionFragment)) {
            }
            switch (message.what) {
                case 106:
                    LogUtil.c(BloodPressureIntroductionFragment.TAG, "CONNECTED_FAILED");
                    bloodPressureIntroductionFragment.handleViewAfterConnectFailed();
                    break;
                case 107:
                    LogUtil.c(BloodPressureIntroductionFragment.TAG, "SHOW_FIRST_CONNECT_FAILED_TIPS");
                    bloodPressureIntroductionFragment.showFirstConnectFailedView();
                    bloodPressureIntroductionFragment.showConnectingStatus();
                    break;
                case 108:
                    LogUtil.c(BloodPressureIntroductionFragment.TAG, "SHOW_MEASURE_FAILED");
                    bloodPressureIntroductionFragment.showMeasureFailed();
                    bloodPressureIntroductionFragment.showDialog(bloodPressureIntroductionFragment.getString(R.string.IDS_device_measure_fail));
                    break;
                case 109:
                    bloodPressureIntroductionFragment.showBluetoothOffView();
                    break;
                case 110:
                case 111:
                default:
                    LogUtil.h(BloodPressureIntroductionFragment.TAG, "not have this case and case =", Integer.valueOf(message.what));
                    break;
                case 112:
                    LogUtil.a(BloodPressureIntroductionFragment.TAG, "handle in DOWNLOAD_TIME_OUT");
                    bloodPressureIntroductionFragment.downTimeOut();
                    break;
                case 113:
                    LogUtil.c(BloodPressureIntroductionFragment.TAG, "DOWNLOAD_RESOURCE_SUCCESS");
                    bloodPressureIntroductionFragment.mIsHaveProductInfo = true;
                    removeMessages(112);
                    bloodPressureIntroductionFragment.startScanner();
                    break;
                case 114:
                    LogUtil.a(BloodPressureIntroductionFragment.TAG, "handle in START_MEASURING");
                    bloodPressureIntroductionFragment.showStartMeasureAnimation();
                    break;
            }
        }

        private boolean preHandle(Message message, BloodPressureIntroductionFragment bloodPressureIntroductionFragment) {
            int i = message.what;
            if (i == 115) {
                LogUtil.a(BloodPressureIntroductionFragment.TAG, "handle in START_SCAN");
                bloodPressureIntroductionFragment.startScanner();
            } else if (i != 116) {
                switch (i) {
                    case 101:
                        LogUtil.c(BloodPressureIntroductionFragment.TAG, "AUTO_CONNECT_BLOOD_PRESSURE_DEVICE");
                        bloodPressureIntroductionFragment.showConnectingStatus();
                        bloodPressureIntroductionFragment.connectDevice();
                        break;
                    case 102:
                        LogUtil.c(BloodPressureIntroductionFragment.TAG, "STATE_CHANGED");
                        bloodPressureIntroductionFragment.onDeviceStateChanged();
                        break;
                    case 103:
                        LogUtil.c(BloodPressureIntroductionFragment.TAG, "UPDATE_DATA_EMPTY");
                        bloodPressureIntroductionFragment.showEmptyView();
                        break;
                    case 104:
                        LogUtil.c(BloodPressureIntroductionFragment.TAG, "UPDATE_BLOOD_PRESSURE_DATA");
                        if (message.obj instanceof HiHealthData) {
                            bloodPressureIntroductionFragment.refreshBloodPressureData((HiHealthData) message.obj);
                            break;
                        }
                        break;
                    case 105:
                        LogUtil.c(BloodPressureIntroductionFragment.TAG, "MEASURE_FINSH");
                        if (message.obj instanceof deo) {
                            bloodPressureIntroductionFragment.handleViewAfterMeasured((deo) message.obj);
                            break;
                        }
                        break;
                    default:
                        return true;
                }
            } else {
                LogUtil.a(BloodPressureIntroductionFragment.TAG, "handle in STOP_SCAN");
                bloodPressureIntroductionFragment.stopBleScanner();
                bloodPressureIntroductionFragment.handleViewAfterConnectFailed();
            }
            return false;
        }
    }
}
