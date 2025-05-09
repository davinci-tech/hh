package com.huawei.health.ecologydevice.ui.measure.fragment;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
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
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import androidx.fragment.app.FragmentActivity;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.health.R;
import com.huawei.health.device.api.HealthDeviceEntryApi;
import com.huawei.health.device.api.HonourDeviceConstantsApi;
import com.huawei.health.device.api.VersionVerifyUtilApi;
import com.huawei.health.device.callback.IDeviceEventHandler;
import com.huawei.health.device.connectivity.comm.MeasurableDevice;
import com.huawei.health.device.model.HealthDevice;
import com.huawei.health.device.open.data.model.HealthData;
import com.huawei.health.ecologydevice.callback.IdialogButtonClickCallback;
import com.huawei.health.ecologydevice.callback.NfcMeasureCallback;
import com.huawei.health.ecologydevice.clouddevice.DownloadDeviceInfoCallBack;
import com.huawei.health.ecologydevice.manager.ResourceManager;
import com.huawei.health.ecologydevice.open.data.model.CommonUiResponse;
import com.huawei.health.ecologydevice.ui.BaseFragment;
import com.huawei.health.ecologydevice.ui.measure.fragment.BloodSugarIntroductionFragment;
import com.huawei.health.ecologydevice.ui.util.PermissionDialogHelper;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.dictionary.constants.ProductMap;
import com.huawei.hihealth.dictionary.constants.ProductMapInfo;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.utils.PermissionUtil;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.OpAnalyticsConstants;
import com.huawei.operation.ble.BleConstants;
import com.huawei.ui.commonui.dialog.CustomViewDialog;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.progressbar.HealthProgressBar;
import com.huawei.ui.commonui.utils.CustomPermissionAction;
import defpackage.cei;
import defpackage.cez;
import defpackage.cpp;
import defpackage.cwt;
import defpackage.cxh;
import defpackage.dbe;
import defpackage.dcq;
import defpackage.dcx;
import defpackage.dcz;
import defpackage.deb;
import defpackage.dei;
import defpackage.dif;
import defpackage.dja;
import defpackage.dko;
import defpackage.dkq;
import defpackage.dks;
import defpackage.gnm;
import defpackage.msj;
import defpackage.msn;
import defpackage.msq;
import defpackage.msr;
import defpackage.nsj;
import defpackage.nsn;
import health.compact.a.CommonUtil;
import health.compact.a.LanguageUtil;
import health.compact.a.Services;
import health.compact.a.UnitUtil;
import java.io.UnsupportedEncodingException;
import java.lang.ref.WeakReference;
import java.math.BigDecimal;
import java.util.Iterator;

/* loaded from: classes3.dex */
public class BloodSugarIntroductionFragment extends BaseFragment implements View.OnClickListener {
    private static final String BLOOD_SUGAR_UNIT_MMOL_L = "mmol/L";
    private static final int DELAY_500MS = 500;
    private static final int DOWNLOAD_RESOURCE_SUCCESS = 104;
    private static final int DOWNLOAD_TIME_OUT = 108;
    private static final int DOWNLOAD_TIME_OUT_DELAY_30S = 30000;
    private static final int REQUEST_ENABLE_BT = 2;
    private static final int REQUEST_GPS_LOCATION = 1;
    private static final int SCAN_DEVICE = 109;
    private static final int SHOW_INPUT_PIN = 107;
    private static final int STATE_CHANGED = 106;
    private static final String TAG = "BloodSugarIntroductionFragment";
    private static final int TO_FRESH = 105;
    private BluetoothAdapter mBluetoothAdapter;
    private BluetoothDevice mBluetoothDevice;
    private BroadcastReceiver mBroadcastReceiver;
    private int mCurrentStatus;
    private ImageView mDefaultDeviceImage;
    private cxh mDevice;
    private HealthTextView mDeviceBondStatus;
    private ImageView mDeviceImage;
    private String mDeviceMacAddress;
    private RelativeLayout mDeviceMeasureValueLayout;
    private String mDeviceName;
    private String mDevicePid;
    private String mDevicePin;
    private String mDeviceType;
    private String mEnterType;
    private HealthTextView mFirstConnectFailedTv;
    private HealthTextView mLastMeasure;
    private HealthTextView mLastMeasureDate;
    private LinearLayout mLastMeasureLayout;
    private LinearLayout mMeasureLevelLayout;
    private HealthTextView mMeasureValue;
    private HealthTextView mMeasureValueLevel;
    private HealthTextView mMeasureValueUnit;
    private String mProductId;
    private dcz mProductInfo;
    private HealthProgressBar mReBondLoadingPb;
    private HealthTextView mReBondTv;
    private int mReconnectCount;
    private ImageView mRightArrow;
    private dbe mRocheGlucoseManager;
    private long mStartConnectTime;
    private HealthTextView mTimePeriod;
    private final HealthDevice.HealthDeviceKind mKind = HealthDevice.HealthDeviceKind.HDK_BLOOD_SUGAR;
    private boolean mIsBtEnableShowing = false;
    private CustomViewDialog mDialogForConnectFail = null;
    private BloodSugarHandler mBloodSugarHandler = new BloodSugarHandler();
    private final NfcMeasureCallback mNfcMeasureCallback = new NfcMeasureCallback() { // from class: com.huawei.health.ecologydevice.ui.measure.fragment.BloodSugarIntroductionFragment.1
        @Override // com.huawei.health.ecologydevice.callback.NfcMeasureCallback
        public void onStartMeasuring() {
        }

        @Override // com.huawei.health.ecologydevice.callback.NfcMeasureCallback
        public void onDataChanged(HealthDevice healthDevice, HealthData healthData) {
            int parseInt;
            try {
                if (healthDevice instanceof cxh) {
                    BloodSugarIntroductionFragment.this.mDevice = (cxh) healthDevice;
                }
                int bondState = BloodSugarIntroductionFragment.this.mDevice.Rb_().getBondState();
                if (BloodSugarIntroductionFragment.this.mDevice.Rb_() == null || bondState != 12) {
                    return;
                }
                BloodSugarIntroductionFragment bloodSugarIntroductionFragment = BloodSugarIntroductionFragment.this;
                bloodSugarIntroductionFragment.mBluetoothDevice = bloodSugarIntroductionFragment.mDevice.Rb_();
                dcz d = BloodSugarIntroductionFragment.this.mProductId != null ? ResourceManager.e().d(BloodSugarIntroductionFragment.this.mProductId) : null;
                if (BloodSugarIntroductionFragment.this.mDeviceType != null) {
                    try {
                        parseInt = Integer.parseInt(BloodSugarIntroductionFragment.this.mDeviceType);
                    } catch (NumberFormatException e) {
                        LogUtil.a(BloodSugarIntroductionFragment.TAG, e.getMessage());
                    }
                    if (d == null && d.n() != null) {
                        String d2 = dcx.d(BloodSugarIntroductionFragment.this.mProductId, d.n().b());
                        BloodSugarIntroductionFragment bloodSugarIntroductionFragment2 = BloodSugarIntroductionFragment.this;
                        bloodSugarIntroductionFragment2.registerDeviceInfo(bloodSugarIntroductionFragment2.mDevice, d2, parseInt, BloodSugarIntroductionFragment.this.mProductId);
                    } else {
                        BloodSugarIntroductionFragment bloodSugarIntroductionFragment3 = BloodSugarIntroductionFragment.this;
                        bloodSugarIntroductionFragment3.registerDeviceInfo(bloodSugarIntroductionFragment3.mDevice, BloodSugarIntroductionFragment.this.getContext().getResources().getString(R.string.IDS_device_group_name_blood_glucose_scale), parseInt, BloodSugarIntroductionFragment.this.mProductId);
                    }
                    BloodSugarIntroductionFragment bloodSugarIntroductionFragment4 = BloodSugarIntroductionFragment.this;
                    bloodSugarIntroductionFragment4.tickBiNfcConnectBloodSugar(bloodSugarIntroductionFragment4.getContext(), dko.c(BloodSugarIntroductionFragment.this.mStartConnectTime), bondState, BloodSugarIntroductionFragment.this.mReconnectCount);
                }
                parseInt = 10001;
                if (d == null) {
                }
                BloodSugarIntroductionFragment bloodSugarIntroductionFragment32 = BloodSugarIntroductionFragment.this;
                bloodSugarIntroductionFragment32.registerDeviceInfo(bloodSugarIntroductionFragment32.mDevice, BloodSugarIntroductionFragment.this.getContext().getResources().getString(R.string.IDS_device_group_name_blood_glucose_scale), parseInt, BloodSugarIntroductionFragment.this.mProductId);
                BloodSugarIntroductionFragment bloodSugarIntroductionFragment42 = BloodSugarIntroductionFragment.this;
                bloodSugarIntroductionFragment42.tickBiNfcConnectBloodSugar(bloodSugarIntroductionFragment42.getContext(), dko.c(BloodSugarIntroductionFragment.this.mStartConnectTime), bondState, BloodSugarIntroductionFragment.this.mReconnectCount);
            } catch (SecurityException e2) {
                LogUtil.b(BloodSugarIntroductionFragment.TAG, "onDataChanged SecurityException:", ExceptionUtils.d(e2));
            }
        }

        @Override // com.huawei.health.ecologydevice.callback.NfcMeasureCallback
        public void onStatusChanged(int i, int i2, int i3) {
            LogUtil.a(BloodSugarIntroductionFragment.TAG, "onStatusChanged and status=", Integer.valueOf(i));
            BloodSugarIntroductionFragment.this.mReconnectCount = i3;
            BloodSugarIntroductionFragment.this.mCurrentStatus = i;
            BloodSugarIntroductionFragment.this.sendEmptyMessage(106);
            if (i == 13) {
                BloodSugarIntroductionFragment bloodSugarIntroductionFragment = BloodSugarIntroductionFragment.this;
                bloodSugarIntroductionFragment.tickBiNfcConnectBloodSugar(bloodSugarIntroductionFragment.getContext(), 0.0d, i2, i3);
            }
        }
    };

    @Override // com.huawei.health.ecologydevice.ui.BaseFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        dbe dbeVar;
        super.onCreate(bundle);
        Bundle arguments = getArguments();
        if (arguments == null) {
            LogUtil.a(TAG, "bundle is null");
        } else if (initParam(arguments) && (dbeVar = this.mRocheGlucoseManager) != null) {
            dbeVar.d(this.mNfcMeasureCallback);
        }
    }

    @Override // com.huawei.health.ecologydevice.ui.BaseFragment, androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        if (layoutInflater == null) {
            LogUtil.a(TAG, "BloodSugarIntroductionFragment onCreateView inflater is null");
            FragmentActivity activity = getActivity();
            if (activity != null) {
                activity.finish();
            }
            return null;
        }
        ViewGroup viewGroup2 = super.onCreateView(layoutInflater, viewGroup, bundle) instanceof ViewGroup ? (ViewGroup) super.onCreateView(layoutInflater, viewGroup, bundle) : null;
        this.child = layoutInflater.inflate(R.layout.blood_sugar_introduction_layout, viewGroup, false);
        if (viewGroup2 != null) {
            viewGroup2.addView(this.child);
            initView(this.child);
        }
        initTitleBar();
        handleUpdateProductMap();
        return viewGroup2;
    }

    private void initView(View view) {
        this.mDefaultDeviceImage = (ImageView) view.findViewById(R.id.device_default_image_iv);
        this.mDeviceImage = (ImageView) view.findViewById(R.id.device_image_iv);
        this.mDeviceBondStatus = (HealthTextView) view.findViewById(R.id.device_connect_status);
        this.mFirstConnectFailedTv = (HealthTextView) view.findViewById(R.id.device_first_connect_failed_tips);
        this.mReBondTv = (HealthTextView) view.findViewById(R.id.device_reconnect_tv);
        this.mReBondLoadingPb = (HealthProgressBar) view.findViewById(R.id.device_reconnect_pb);
        this.mDeviceMeasureValueLayout = (RelativeLayout) view.findViewById(R.id.device_measure_value_layout);
        if ("NFC".equals(this.mEnterType) || "QRCODE".equals(this.mEnterType)) {
            this.mDeviceMeasureValueLayout.setVisibility(8);
        }
        this.mMeasureValue = (HealthTextView) view.findViewById(R.id.third_party_measure_value);
        this.mMeasureValueUnit = (HealthTextView) view.findViewById(R.id.third_party_device_type_unit);
        this.mMeasureValueLevel = (HealthTextView) view.findViewById(R.id.third_party_measure_value_level);
        this.mLastMeasure = (HealthTextView) view.findViewById(R.id.device_last_measure);
        this.mLastMeasureDate = (HealthTextView) view.findViewById(R.id.device_measure_date);
        this.mRightArrow = (ImageView) view.findViewById(R.id.iv_right_arrow);
        this.mTimePeriod = (HealthTextView) view.findViewById(R.id.device_measure_time_period);
        this.mLastMeasureLayout = (LinearLayout) view.findViewById(R.id.device_last_sugar_layout);
        this.mMeasureLevelLayout = (LinearLayout) view.findViewById(R.id.device_measure_level_layout);
        this.mFirstConnectFailedTv.setVisibility(8);
        this.mMeasureValueUnit.setVisibility(0);
        this.mMeasureValueUnit.setText(BLOOD_SUGAR_UNIT_MMOL_L);
        this.mReBondTv.setOnClickListener(this);
        this.mLastMeasureLayout.setOnClickListener(this);
        this.mLastMeasureLayout.setClickable(false);
    }

    private void initTitleBar() {
        this.mCustomTitleBar.setRightButtonVisibility(8);
        Resources resources = getContext().getResources();
        if (resources != null) {
            this.mCustomTitleBar.setTitleBarBackgroundColor(resources.getColor(R.color._2131299340_res_0x7f090c0c));
            super.setTitle(resources.getString(R.string.IDS_device_group_name_blood_glucose_scale));
        }
    }

    private boolean initParam(Bundle bundle) {
        if (bundle == null) {
            return false;
        }
        this.mDeviceMacAddress = bundle.getString("BLE_FROM_QRCODE");
        this.mDeviceName = bundle.getString("BLENAME_FROM_QRCODE");
        if (TextUtils.isEmpty(this.mDeviceMacAddress) && TextUtils.isEmpty(this.mDeviceName)) {
            return false;
        }
        this.mDeviceType = bundle.getString("DEVICE_TYPE_INDEX");
        this.mDevicePid = bundle.getString("PID_FROM_QRCODE");
        this.mDevicePin = bundle.getString("PIN_FROM_QRCODE");
        if (TextUtils.isEmpty(bundle.getString("PAYLOAD_FROM_NFC"))) {
            LogUtil.c(TAG, "QRCODE");
            this.mEnterType = "QRCODE";
        } else {
            LogUtil.c(TAG, "NFC");
            this.mEnterType = "NFC";
        }
        if (!checkInputIsValid()) {
            return false;
        }
        if (this.mBluetoothAdapter == null && getActivity() != null) {
            BluetoothManager bluetoothManager = getActivity().getSystemService("bluetooth") instanceof BluetoothManager ? (BluetoothManager) getActivity().getSystemService("bluetooth") : null;
            if (bluetoothManager == null) {
                LogUtil.h(TAG, "no BT Manager in this phone");
                return false;
            }
            this.mBluetoothAdapter = bluetoothManager.getAdapter();
        }
        if (this.mBluetoothAdapter == null) {
            return false;
        }
        if (this.mRocheGlucoseManager == null) {
            this.mRocheGlucoseManager = dbe.e();
        }
        this.mRocheGlucoseManager.d(this.mDeviceName, this.mDeviceMacAddress);
        dkq.c().b(this.mKind.name());
        this.mRocheGlucoseManager.e(dkq.c().d());
        return true;
    }

    private boolean checkInputIsValid() {
        if (TextUtils.isEmpty(this.mDevicePid) || TextUtils.isEmpty(this.mDeviceType)) {
            LogUtil.h(TAG, "mDevicePid || mDeviceType is null");
            return false;
        }
        if (TextUtils.isEmpty(this.mEnterType)) {
            LogUtil.h(TAG, "mEnterType is null");
            return false;
        }
        LogUtil.c(TAG, "mDevicePid=", this.mDevicePid, " mEnterType=", this.mEnterType, " mDeviceType=", this.mDeviceType, " mDevicePin=", this.mDevicePin);
        return true;
    }

    @Override // com.huawei.health.ecologydevice.ui.BaseFragment, androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        LogUtil.a(TAG, "onResume");
        dei.c().d(new CommonUiResponse() { // from class: com.huawei.health.ecologydevice.ui.measure.fragment.BloodSugarIntroductionFragment$$ExternalSyntheticLambda0
            @Override // com.huawei.health.ecologydevice.open.data.model.CommonUiResponse
            public final void onResponse(int i, Object obj) {
                BloodSugarIntroductionFragment.this.handleLastBloodSugarData(i, (HiHealthData) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public <T> void handleLastBloodSugarData(int i, T t) {
        if (i != 0) {
            LogUtil.h(TAG, "Failed to handleLastBloodSugarData, errorCode=", Integer.valueOf(i));
            dks.Wz_(this.mBloodSugarHandler, 103, null);
        } else {
            dks.Wz_(this.mBloodSugarHandler, 102, t);
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroyView() {
        super.onDestroyView();
        BloodSugarHandler bloodSugarHandler = this.mBloodSugarHandler;
        if (bloodSugarHandler != null) {
            bloodSugarHandler.removeCallbacksAndMessages(null);
            this.mBloodSugarHandler = null;
        }
        dbe dbeVar = this.mRocheGlucoseManager;
        if (dbeVar != null) {
            dbeVar.b();
        }
        if (this.mBroadcastReceiver != null) {
            getContext().unregisterReceiver(this.mBroadcastReceiver);
        }
        handleCancel();
    }

    @Override // com.huawei.health.ecologydevice.ui.BaseFragment, androidx.fragment.app.Fragment
    public void onDestroy() {
        super.onDestroy();
    }

    @Override // androidx.fragment.app.Fragment
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
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
        if (i == 1) {
            checkPermissions();
            return;
        }
        LogUtil.a(TAG, "onActivityResult else is" + i2);
    }

    private void handleUpdateProductMap() {
        LogUtil.a(TAG, "begin update product map");
        showDownloadView();
        sendEmptyDelayMessage(108, OpAnalyticsConstants.H5_LOADING_DELAY);
        cwt.a().b(this.mKind.name(), this.mDevicePid, new DownloadDeviceInfoCallBack() { // from class: com.huawei.health.ecologydevice.ui.measure.fragment.BloodSugarIntroductionFragment.2
            @Override // com.huawei.health.ecologydevice.clouddevice.DownloadDeviceInfoCallBack
            public void onSuccess() {
                LogUtil.a(BloodSugarIntroductionFragment.TAG, "onSuccess");
                BloodSugarIntroductionFragment.this.refreshProductInfo();
                BloodSugarIntroductionFragment.this.removeMessage(108);
                BloodSugarIntroductionFragment.this.sendEmptyMessage(104);
            }

            @Override // com.huawei.health.ecologydevice.clouddevice.DownloadDeviceInfoCallBack
            public void onFailure(int i) {
                LogUtil.a(BloodSugarIntroductionFragment.TAG, "onFailure");
            }

            @Override // com.huawei.health.ecologydevice.clouddevice.DownloadDeviceInfoCallBack
            public void onNetworkError() {
                LogUtil.a(BloodSugarIntroductionFragment.TAG, "onNetworkError");
            }
        });
    }

    private void showDownloadView() {
        this.mDeviceBondStatus.setText(R.string.IDS_device_download_resource);
        this.mReBondLoadingPb.setVisibility(0);
        this.mReBondTv.setVisibility(8);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showDownloadFail() {
        this.mDeviceBondStatus.setText(R.string._2130841543_res_0x7f020fc7);
        this.mFirstConnectFailedTv.setVisibility(0);
        this.mFirstConnectFailedTv.setText(R.string.IDS_device_download_fail_tips);
        this.mReBondLoadingPb.setVisibility(8);
        this.mReBondTv.setVisibility(0);
        this.mReBondTv.setText(R.string._2130850161_res_0x7f023171);
        new dko.c().d(108).i(this.mProductId).b().b(getContext());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void judgingBluetoothOnOff() {
        BluetoothAdapter bluetoothAdapter = this.mBluetoothAdapter;
        if (bluetoothAdapter == null) {
            LogUtil.h(TAG, "no BT in this phone");
            finishFragment();
        } else if (!bluetoothAdapter.isEnabled() && !this.mIsBtEnableShowing) {
            this.mIsBtEnableShowing = true;
            dif.c(getContext(), new IdialogButtonClickCallback() { // from class: com.huawei.health.ecologydevice.ui.measure.fragment.BloodSugarIntroductionFragment.3
                @Override // com.huawei.health.ecologydevice.callback.IdialogButtonClickCallback
                public void onClick(View view) {
                    PermissionDialogHelper.e(BloodSugarIntroductionFragment.this, 2);
                    ViewClickInstrumentation.clickOnView(view);
                }

                @Override // com.huawei.health.ecologydevice.callback.IdialogButtonClickCallback
                public void onCancelClick(View view) {
                    BloodSugarIntroductionFragment.this.finishFragment();
                }
            });
        } else {
            checkPermissions();
        }
    }

    private void checkPermissions() {
        PermissionUtil.PermissionType d = PermissionDialogHelper.d();
        PermissionDialogHelper.VB_(getActivity(), d, new AnonymousClass4(this.mainActivity, d));
    }

    /* renamed from: com.huawei.health.ecologydevice.ui.measure.fragment.BloodSugarIntroductionFragment$4, reason: invalid class name */
    class AnonymousClass4 extends CustomPermissionAction {
        final /* synthetic */ PermissionUtil.PermissionType val$permissionType;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass4(Context context, PermissionUtil.PermissionType permissionType) {
            super(context);
            this.val$permissionType = permissionType;
        }

        @Override // com.huawei.hwcommonmodel.utils.permission.PermissionsResultAction
        public void onGranted() {
            if (this.val$permissionType != PermissionUtil.PermissionType.LOCATION || dja.VG_(BloodSugarIntroductionFragment.this.mainActivity)) {
                BloodSugarIntroductionFragment.this.sendEmptyDelayMessage(109, 500L);
            } else {
                LogUtil.a(BloodSugarIntroductionFragment.TAG, "Permission GpsClose is close");
                BloodSugarIntroductionFragment.this.openGpsLocation();
            }
        }

        @Override // com.huawei.ui.commonui.utils.CustomPermissionAction, com.huawei.hwcommonmodel.utils.permission.PermissionsResultAction
        public void onDenied(String str) {
            super.onDenied(str);
            BloodSugarIntroductionFragment.this.finishFragment();
        }

        @Override // com.huawei.ui.commonui.utils.CustomPermissionAction, com.huawei.hwcommonmodel.utils.permission.PermissionsResultAction
        public void onForeverDenied(PermissionUtil.PermissionType permissionType) {
            super.onForeverDenied(permissionType);
            nsn.cLK_(BloodSugarIntroductionFragment.this.mainActivity, permissionType, null, new View.OnClickListener() { // from class: com.huawei.health.ecologydevice.ui.measure.fragment.BloodSugarIntroductionFragment$4$$ExternalSyntheticLambda0
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    BloodSugarIntroductionFragment.AnonymousClass4.this.m290xa2d9b65(view);
                }
            }, new View.OnClickListener() { // from class: com.huawei.health.ecologydevice.ui.measure.fragment.BloodSugarIntroductionFragment$4$$ExternalSyntheticLambda1
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    BloodSugarIntroductionFragment.AnonymousClass4.this.m291x4fcede04(view);
                }
            });
        }

        /* renamed from: lambda$onForeverDenied$0$com-huawei-health-ecologydevice-ui-measure-fragment-BloodSugarIntroductionFragment$4, reason: not valid java name */
        /* synthetic */ void m290xa2d9b65(View view) {
            BloodSugarIntroductionFragment.this.finishFragment();
            ViewClickInstrumentation.clickOnView(view);
        }

        /* renamed from: lambda$onForeverDenied$1$com-huawei-health-ecologydevice-ui-measure-fragment-BloodSugarIntroductionFragment$4, reason: not valid java name */
        /* synthetic */ void m291x4fcede04(View view) {
            BloodSugarIntroductionFragment.this.finishFragment();
            ViewClickInstrumentation.clickOnView(view);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void openGpsLocation() {
        LogUtil.a(TAG, "openGpsLocation jump to GPS Setting");
        dif.Vp_(this.mainActivity, isAdded(), new IdialogButtonClickCallback() { // from class: com.huawei.health.ecologydevice.ui.measure.fragment.BloodSugarIntroductionFragment.5
            @Override // com.huawei.health.ecologydevice.callback.IdialogButtonClickCallback
            public void onClick(View view) {
                BloodSugarIntroductionFragment bloodSugarIntroductionFragment = BloodSugarIntroductionFragment.this;
                dja.d(bloodSugarIntroductionFragment, bloodSugarIntroductionFragment.isAdded(), 1);
                ViewClickInstrumentation.clickOnView(view);
            }

            @Override // com.huawei.health.ecologydevice.callback.IdialogButtonClickCallback
            public void onCancelClick(View view) {
                LogUtil.a(BloodSugarIntroductionFragment.TAG, "openGpsLocation onCancelClick");
                BloodSugarIntroductionFragment.this.finishFragment();
            }
        });
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
            return;
        }
        this.mProductInfo = d2;
        if (((HonourDeviceConstantsApi) Services.c("PluginDevice", HonourDeviceConstantsApi.class)).isCorrectProductId(d2.t())) {
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

    /* JADX INFO: Access modifiers changed from: private */
    public void removeMessage(int i) {
        BloodSugarHandler bloodSugarHandler = this.mBloodSugarHandler;
        if (bloodSugarHandler != null) {
            bloodSugarHandler.removeMessages(i);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendEmptyMessage(int i) {
        BloodSugarHandler bloodSugarHandler = this.mBloodSugarHandler;
        if (bloodSugarHandler != null) {
            bloodSugarHandler.sendEmptyMessage(i);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendEmptyDelayMessage(int i, long j) {
        BloodSugarHandler bloodSugarHandler = this.mBloodSugarHandler;
        if (bloodSugarHandler != null) {
            bloodSugarHandler.sendEmptyMessageDelayed(i, j);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showLateBloodSugarData(HiHealthData hiHealthData) {
        LinearLayout linearLayout = this.mLastMeasureLayout;
        if (linearLayout == null || this.mMeasureLevelLayout == null) {
            LogUtil.h(TAG, "refreshBloodPressureData view is null");
            return;
        }
        if (hiHealthData == null) {
            showEmptyView();
            return;
        }
        linearLayout.setVisibility(0);
        this.mMeasureLevelLayout.setVisibility(0);
        this.mRightArrow.setVisibility(0);
        this.mTimePeriod.setVisibility(0);
        this.mLastMeasure.setText(R.string.IDS_hw_device_last_measurement);
        if (LanguageUtil.bc(this.mainActivity)) {
            this.mRightArrow.setImageResource(R.drawable._2131427841_res_0x7f0b0201);
        } else {
            this.mRightArrow.setImageResource(R.drawable._2131427842_res_0x7f0b0202);
        }
        setBloodSugarLevelTextAndColor(deb.c(BaseApplication.getContext(), hiHealthData.getType(), (float) hiHealthData.getDouble("point_value")));
        this.mLastMeasureDate.setText(nsj.f(hiHealthData.getStartTime()));
        this.mMeasureValue.setText(UnitUtil.e(hiHealthData.getDouble("point_value"), 1, 1));
        this.mTimePeriod.setText(deb.a(BigDecimal.valueOf(hiHealthData.getType()).intValue()));
    }

    private void setBloodSugarLevelTextAndColor(int i) {
        Resources resources = BaseApplication.getContext().getResources();
        switch (i) {
            case 1001:
                this.mMeasureValueLevel.setText(resources.getString(R.string._2130843962_res_0x7f02193a));
                this.mMeasureValueLevel.setTextColor(resources.getColor(R.color._2131296797_res_0x7f09021d));
                break;
            case 1002:
                this.mMeasureValueLevel.setText(resources.getString(R.string._2130843960_res_0x7f021938));
                this.mMeasureValueLevel.setTextColor(resources.getColor(R.color._2131296797_res_0x7f09021d));
                break;
            case 1003:
                this.mMeasureValueLevel.setText(resources.getString(R.string._2130843959_res_0x7f021937));
                this.mMeasureValueLevel.setTextColor(resources.getColor(R.color._2131296799_res_0x7f09021f));
                break;
            case 1004:
                this.mMeasureValueLevel.setText(resources.getString(R.string._2130843961_res_0x7f021939));
                this.mMeasureValueLevel.setTextColor(resources.getColor(R.color._2131296795_res_0x7f09021b));
                break;
            case 1005:
                this.mMeasureValueLevel.setText(resources.getString(R.string._2130843966_res_0x7f02193e));
                this.mMeasureValueLevel.setTextColor(resources.getColor(R.color._2131296795_res_0x7f09021b));
                break;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showEmptyView() {
        LinearLayout linearLayout = this.mLastMeasureLayout;
        if (linearLayout == null || this.mMeasureLevelLayout == null) {
            LogUtil.h(TAG, "refreshBloodPressureData view is null");
            return;
        }
        linearLayout.setVisibility(8);
        this.mMeasureLevelLayout.setVisibility(8);
        if (this.mKind == HealthDevice.HealthDeviceKind.HDK_BLOOD_SUGAR) {
            this.mMeasureValue.setText("- -");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void finishFragment() {
        popupFragment(null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showBondingView() {
        this.mDeviceBondStatus.setText(R.string._2130841387_res_0x7f020f2b);
        this.mFirstConnectFailedTv.setVisibility(8);
        this.mReBondTv.setVisibility(8);
        this.mReBondLoadingPb.setVisibility(0);
        setProductNameAndImage();
    }

    private void showBondedView() {
        if (isNeedLoadH5Page()) {
            bindDeviceAndLoadH5Page();
            return;
        }
        setProductNameAndImage();
        this.mDeviceMeasureValueLayout.setVisibility(0);
        this.mLastMeasureLayout.setClickable(true);
        this.mDeviceBondStatus.setText(R.string._2130841578_res_0x7f020fea);
        this.mReBondTv.setVisibility(0);
        this.mReBondLoadingPb.setVisibility(8);
        this.mFirstConnectFailedTv.setVisibility(8);
        this.mReBondTv.setText(R.string.IDS_device_measureactivity_guide_start_measure);
        this.mReBondTv.setTextColor(getResources().getColor(R.color._2131296570_res_0x7f09013a));
    }

    private boolean isNeedLoadH5Page() {
        ProductMapInfo d = ProductMap.d(this.mDevicePid);
        if (d == null) {
            return false;
        }
        dcz d2 = ResourceManager.e().d(d.h());
        this.mProductInfo = d2;
        return BleConstants.BLE_THIRD_DEVICE_H5.equals(d2.m().d());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void loadH5Page() {
        if (TextUtils.isEmpty(this.mDeviceMacAddress)) {
            MeasurableDevice bondedDeviceByBroadcastName = cei.b().getBondedDeviceByBroadcastName(this.mProductId, this.mDeviceName);
            if (bondedDeviceByBroadcastName == null) {
                LogUtil.a(TAG, "measurableDevice is null");
                if (getActivity() != null) {
                    getActivity().finish();
                    return;
                }
                return;
            }
            this.mDeviceMacAddress = bondedDeviceByBroadcastName.getUniqueId();
        }
        if ("1".equals(this.mProductInfo.j())) {
            dks.d(getContext(), this.mProductInfo, this.mProductId, this.mDeviceMacAddress);
        } else {
            Intent Wx_ = dks.Wx_(this.mProductInfo, this.mProductId, this.mDeviceMacAddress);
            if (Wx_ != null && getContext() != null) {
                Wx_.putExtra("url", dcq.b().c(this.mProductId) + "?enterType=" + this.mEnterType);
                gnm.aPB_(getContext(), Wx_);
            }
        }
        if (getActivity() != null) {
            getActivity().finish();
        }
    }

    private void bindDeviceAndLoadH5Page() {
        ((HealthDeviceEntryApi) Services.c("PluginDevice", HealthDeviceEntryApi.class)).bindDevice(this.mProductId, this.mProductInfo.s(), this.mDevice, new IDeviceEventHandler() { // from class: com.huawei.health.ecologydevice.ui.measure.fragment.BloodSugarIntroductionFragment.6
            @Override // com.huawei.health.device.callback.IDeviceEventHandler
            public void onDeviceFound(HealthDevice healthDevice) {
            }

            @Override // com.huawei.health.device.callback.IDeviceEventHandler
            public void onScanFailed(int i) {
            }

            @Override // com.huawei.health.device.callback.IDeviceEventHandler
            public void onStateChanged(int i) {
                if (i == 7) {
                    LogUtil.a(BloodSugarIntroductionFragment.TAG, "PAIRING PASSED");
                    BloodSugarIntroductionFragment.this.loadH5Page();
                    dko.b(BloodSugarIntroductionFragment.this.mProductId, BloodSugarIntroductionFragment.this.mDeviceMacAddress, BloodSugarIntroductionFragment.this.mDeviceMacAddress);
                }
            }
        });
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
                showInputPinTipsDialog();
                break;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showInputPinTipsDialog() {
        try {
            BluetoothDevice bluetoothDevice = this.mBluetoothDevice;
            if (bluetoothDevice != null && bluetoothDevice.getBondState() == 12) {
                showBondedView();
                return;
            }
            if (!TextUtils.isEmpty(this.mDevicePin)) {
                this.mBroadcastReceiver = new BroadcastReceiver() { // from class: com.huawei.health.ecologydevice.ui.measure.fragment.BloodSugarIntroductionFragment.7
                    @Override // android.content.BroadcastReceiver
                    public void onReceive(Context context, Intent intent) {
                        BluetoothDevice bluetoothDevice2 = (BluetoothDevice) intent.getParcelableExtra("android.bluetooth.device.extra.DEVICE");
                        abortBroadcast();
                        if (bluetoothDevice2 != null) {
                            try {
                                bluetoothDevice2.setPin(BloodSugarIntroductionFragment.this.mDevicePin.getBytes("UTF-8"));
                            } catch (UnsupportedEncodingException unused) {
                                LogUtil.b(BloodSugarIntroductionFragment.TAG, "encode UnsupportedEncodingException");
                            }
                        }
                    }
                };
                getContext().registerReceiver(this.mBroadcastReceiver, new IntentFilter("android.bluetooth.device.action.PAIRING_REQUEST"));
            }
            dbe dbeVar = this.mRocheGlucoseManager;
            if (dbeVar != null) {
                dbeVar.c();
            }
        } catch (SecurityException e) {
            LogUtil.b(TAG, "showInputPinTipsDialog SecurityException:", ExceptionUtils.d(e));
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
            builder.d(R.string.IDS_device_wifi_bind_fail).czg_(inflate).cze_(R.string._2130841794_res_0x7f0210c2, new View.OnClickListener() { // from class: com.huawei.health.ecologydevice.ui.measure.fragment.BloodSugarIntroductionFragment$$ExternalSyntheticLambda1
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    BloodSugarIntroductionFragment.this.m289x6fc3f385(view);
                }
            });
            CustomViewDialog e = builder.e();
            this.mDialogForConnectFail = e;
            e.setCancelable(false);
        }
        this.mDialogForConnectFail.show();
    }

    /* renamed from: lambda$showConnectFailDialog$0$com-huawei-health-ecologydevice-ui-measure-fragment-BloodSugarIntroductionFragment, reason: not valid java name */
    /* synthetic */ void m289x6fc3f385(View view) {
        LogUtil.a(TAG, "showConnectionNoteDialog onclick PositiveButton");
        finishFragment();
        ViewClickInstrumentation.clickOnView(view);
    }

    private void reDownloadResource() {
        if (CommonUtil.aa(BaseApplication.getContext())) {
            showDownloadView();
            sendEmptyDelayMessage(108, OpAnalyticsConstants.H5_LOADING_DELAY);
            handleUpdateProductMap();
            return;
        }
        showDownloadFail();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void scanDevice() {
        if (this.mRocheGlucoseManager != null) {
            this.mStartConnectTime = System.currentTimeMillis();
            this.mRocheGlucoseManager.d();
        }
    }

    private Bitmap getProductImage() {
        dcz d = ResourceManager.e().d(this.mProductId);
        return BitmapFactory.decodeFile((d == null || d.e().size() <= 0) ? "" : dcq.b().a(this.mProductId, d.e().get(0).e()));
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
        this.mDeviceImage.setImageBitmap(getProductImage());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void tickBiNfcConnectBloodSugar(Context context, double d, int i, int i2) {
        new dko.c().e(d).c(this.mProductId).d(this.mDeviceType).b(this.mEnterType).e(i2).i(this.mProductId).b(i).b().b(context);
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
                judgingBluetoothOnOff();
            } else if (this.mReBondTv.getText().toString().equals(getResources().getString(R.string._2130850161_res_0x7f023171))) {
                this.mFirstConnectFailedTv.setVisibility(8);
                reDownloadResource();
            } else {
                LogUtil.h(TAG, "not have this view");
            }
        } else if (view == this.mLastMeasureLayout) {
            Intent intent = new Intent();
            intent.setPackage(cez.w);
            intent.setClassName(cez.w, "com.huawei.ui.main.stories.health.activity.healthdata.BloodSugarHistoryActivity");
            startActivity(intent);
        } else {
            LogUtil.h(TAG, "click is other view");
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    static class BloodSugarHandler extends Handler {
        private final WeakReference<BloodSugarIntroductionFragment> mWeakReference;

        private BloodSugarHandler(BloodSugarIntroductionFragment bloodSugarIntroductionFragment) {
            super(Looper.myLooper());
            this.mWeakReference = new WeakReference<>(bloodSugarIntroductionFragment);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            BloodSugarIntroductionFragment bloodSugarIntroductionFragment = this.mWeakReference.get();
            if (bloodSugarIntroductionFragment == null || !bloodSugarIntroductionFragment.isAdded() || bloodSugarIntroductionFragment.isRemoving() || bloodSugarIntroductionFragment.isDetached()) {
                return;
            }
            switch (message.what) {
                case 102:
                    LogUtil.c(BloodSugarIntroductionFragment.TAG, "UPDATE_BLOOD_SUGAR_DATA");
                    if (message.obj instanceof HiHealthData) {
                        bloodSugarIntroductionFragment.showLateBloodSugarData((HiHealthData) message.obj);
                        break;
                    }
                    break;
                case 103:
                    LogUtil.c(BloodSugarIntroductionFragment.TAG, "UPDATE_DATA_EMPTY");
                    bloodSugarIntroductionFragment.showEmptyView();
                    break;
                case 104:
                    LogUtil.c(BloodSugarIntroductionFragment.TAG, "DOWNLOAD_RESOURCE_SUCCESS");
                    removeMessages(108);
                    if (!bloodSugarIntroductionFragment.needUpdateAppVersion()) {
                        bloodSugarIntroductionFragment.showBondingView();
                        bloodSugarIntroductionFragment.judgingBluetoothOnOff();
                        break;
                    }
                    break;
                case 105:
                default:
                    LogUtil.h(BloodSugarIntroductionFragment.TAG, "not have this case: ", Integer.valueOf(message.what));
                    break;
                case 106:
                    LogUtil.c(BloodSugarIntroductionFragment.TAG, "STATE_CHANGED");
                    bloodSugarIntroductionFragment.showDeviceCurrentState();
                    break;
                case 107:
                    bloodSugarIntroductionFragment.showInputPinTipsDialog();
                    break;
                case 108:
                    bloodSugarIntroductionFragment.handleCancel();
                    bloodSugarIntroductionFragment.showDownloadFail();
                    break;
                case 109:
                    bloodSugarIntroductionFragment.scanDevice();
                    break;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean needUpdateAppVersion() {
        VersionVerifyUtilApi versionVerifyUtilApi = (VersionVerifyUtilApi) Services.c("PluginDevice", VersionVerifyUtilApi.class);
        if (!msr.c.containsKey(this.mKind.name()) || versionVerifyUtilApi.isPublishVersion(this.mProductId, this.mKind.name())) {
            return false;
        }
        LogUtil.a(TAG, "current device needs to update the app version, mProductId = ", this.mProductId);
        versionVerifyUtilApi.noSupportDevice(getContext(), getActivity());
        return true;
    }
}
