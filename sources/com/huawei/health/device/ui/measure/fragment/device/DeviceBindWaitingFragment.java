package com.huawei.health.device.ui.measure.fragment.device;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import com.huawei.devicesdk.callback.DeviceStatusChangeCallback;
import com.huawei.devicesdk.entity.DeviceInfo;
import com.huawei.featureuserprofile.route.hwgpxmodel.Wpt;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.haf.design.pattern.Observer;
import com.huawei.haf.design.pattern.ObserverManagerUtil;
import com.huawei.haf.handler.BaseHandler;
import com.huawei.haf.router.AppRouterExtras;
import com.huawei.health.R;
import com.huawei.health.device.api.DeviceMainActivityApi;
import com.huawei.health.device.callback.BindHealthDeviceCallback;
import com.huawei.health.device.callback.IDeviceEventHandler;
import com.huawei.health.device.callback.IHealthDeviceCallback;
import com.huawei.health.device.callback.VersionNoSupportCallback;
import com.huawei.health.device.connectivity.comm.BleDeviceHelper;
import com.huawei.health.device.connectivity.comm.MeasurableDevice;
import com.huawei.health.device.fatscale.multiusers.WeightDataManager;
import com.huawei.health.device.model.HealthDevice;
import com.huawei.health.device.open.data.model.HealthData;
import com.huawei.health.device.ui.measure.fragment.HagridDeviceBindResultFragment;
import com.huawei.health.device.ui.measure.fragment.HagridDeviceManagerFragment;
import com.huawei.health.device.ui.measure.fragment.WiFiDeviceBindResultFragment;
import com.huawei.health.device.ui.measure.fragment.device.DeviceBindWaitingFragment;
import com.huawei.health.device.util.EventBus;
import com.huawei.health.ecologydevice.callback.NemoDeviceCallback;
import com.huawei.health.ecologydevice.manager.ResourceManager;
import com.huawei.health.ecologydevice.manager.RopeCloudAuthManager;
import com.huawei.health.ecologydevice.networkclient.HealthEngineRequestManager;
import com.huawei.health.ecologydevice.ui.BaseFragment;
import com.huawei.health.ecologydevice.ui.measure.fragment.HeartRateDeviceRunGuide;
import com.huawei.health.ecologydevice.ui.measure.fragment.ProductIntroductionFragment;
import com.huawei.health.ecologydevice.ui.measure.fragment.RopeSkippingGuideFragment;
import com.huawei.health.ecologydevice.ui.measure.fragment.SportDeviceIntroductionFragment;
import com.huawei.health.ecologydevice.ui.measure.fragment.device.DeviceBindFailedFragment;
import com.huawei.health.ecologydevice.ui.measure.fragment.device.DeviceBindSuccessFragment;
import com.huawei.health.ecologydevice.ui.measure.fragment.device.DeviceSilentGuideFragment;
import com.huawei.health.ecologydevice.ui.measure.fragment.utils.ProductCreateFactory;
import com.huawei.health.userprofilemgr.api.UserProfileMgrApi;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hihealth.HiHealthClient;
import com.huawei.hihealth.data.listener.HiDataClientListener;
import com.huawei.hms.support.hianalytics.HiAnalyticsConstant;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcloudmodel.callback.ICloudOperationResult;
import com.huawei.hwcloudmodel.model.CloudCommonReponse;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.utils.PermissionUtil;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.openalliance.ad.constant.AdShowExtras;
import com.huawei.operation.OpAnalyticsConstants;
import com.huawei.operation.ble.BleConstants;
import com.huawei.pluginmgr.hwwear.bean.DeviceDownloadSourceInfo;
import com.huawei.ui.commonui.dialog.CustomAlertDialog;
import com.huawei.ui.commonui.dialog.NoTitleCustomAlertDialog;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.progressbar.HealthProgressBar;
import com.huawei.ui.commonui.utils.CustomPermissionAction;
import defpackage.ceo;
import defpackage.ceu;
import defpackage.cev;
import defpackage.cfa;
import defpackage.cgt;
import defpackage.cjx;
import defpackage.ckm;
import defpackage.cld;
import defpackage.cop;
import defpackage.coy;
import defpackage.cpa;
import defpackage.cpl;
import defpackage.cpp;
import defpackage.cpw;
import defpackage.cpy;
import defpackage.cpz;
import defpackage.cqa;
import defpackage.cqh;
import defpackage.crj;
import defpackage.ctk;
import defpackage.ctq;
import defpackage.cxh;
import defpackage.dcp;
import defpackage.dcq;
import defpackage.dcz;
import defpackage.dds;
import defpackage.ddw;
import defpackage.dei;
import defpackage.dfe;
import defpackage.dgw;
import defpackage.dhc;
import defpackage.dij;
import defpackage.djr;
import defpackage.dkc;
import defpackage.dkd;
import defpackage.dko;
import defpackage.dkq;
import defpackage.dks;
import defpackage.fhw;
import defpackage.gmz;
import defpackage.gnb;
import defpackage.koq;
import defpackage.msr;
import defpackage.nsn;
import health.compact.a.CommonUtil;
import health.compact.a.EzPluginManager;
import health.compact.a.Services;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;
import health.compact.a.Utils;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.lang.ref.WeakReference;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/* loaded from: classes3.dex */
public class DeviceBindWaitingFragment extends BaseFragment {
    private static final int BIND_ANIMATION_STOP_INDEX = 3;
    private static final String BUNDLE_KEY_AUTO_DEVICE_SSID = "auth_device_id";
    private static final String BUNDLE_KEY_BIND_START = "is_bind_started";
    private static final String BUNDLE_KEY_DEVICE_WEIGHT = "deviceWeight";
    private static final String BUNDLE_KEY_HAS_MANAGER = "hasManager";
    private static final String BUNDLE_KEY_IS_BIND_SUCCESS = "isBindSuccess";
    private static final String BUNDLE_KEY_MAIN_HUID = "mainHuid";
    private static final String BUNDLE_KEY_PRODUCT_ID = "productId";
    private static final String BUNDLE_KEY_RESIS_FAT = "deviceResis";
    private static final String BUNDLE_KEY_VIEW = "view";
    private static final String BUNDLE_VALUE_BIND_RESULT_CONFIRM = "bindResultConfirm";
    private static final int CONNECT_TIMEOUT = 1;
    private static final int DELAY_TIME = 1000;
    private static String DEVICE_ASSOCIATION = "DEVICE_ASSOCIATION";
    private static final String DOBBY_SCALE = "Dobby";
    private static final int ERROR_NUMBER = 907127004;
    private static final String HEALTH_APP_MAIN_ACTIVITY = "com.huawei.health.MainActivity";
    private static final String HOME_TAB_DEVICE = "HOME";
    private static final String HOME_TAB_NAME = "homeTabName";
    private static final String HUAWEI_SCALE_3BLE = "HUAWEI Scale 3 BLE-";
    private static final String IS_GO_ROPE_JUMP = "is_go_rope_jump";
    private static final int MSG_BIND_FAIL = 2;
    private static final int MSG_DEVICE_CHECK_FAIL = 3;
    private static final String OVERSEA_HAGRID_B29_SCALE = "Hagrid-B29";
    private static final String OVERSEA_HAG_SCALE = "HAG";
    private static final String PAIR_YES = "yes";
    private static final String RELEASE_TAG = "R_Weight_PluginDevice_DeviceBindWaitingFragment";
    private static final int REQUEST_GPS_LOCATION = 3;
    private static final int SCAN_TIMEOUT = 30000;
    private static String SKIP_PRODUCT_INTRODUCTION_FRAGMENT = "SKIP_PRODUCT_INTRODUCTION_FRAGMENT";
    private static final String TAG = "PluginDevice_DeviceBindWaitingFragment";
    private static final int UPDATE_DATA_BASE_FAIL_VALUE = 0;
    private static final int WEIGHT = -1;
    public static final Observer sSkipProductIntroductionObserver = new Observer() { // from class: com.huawei.health.device.ui.measure.fragment.device.DeviceBindWaitingFragment.1
        @Override // com.huawei.haf.design.pattern.Observer
        public void notify(String str, Object... objArr) {
            Object obj = objArr[0];
            if (obj instanceof Integer) {
                int unused = DeviceBindWaitingFragment.sSportType = ((Integer) obj).intValue();
            }
            if (DeviceBindWaitingFragment.sSkipProductIntroductionObserver != null) {
                ObserverManagerUtil.e(DeviceBindWaitingFragment.sSkipProductIntroductionObserver, DeviceBindWaitingFragment.SKIP_PRODUCT_INTRODUCTION_FRAGMENT);
            }
        }
    };
    private static int sSportType = 0;
    private cop gpslUtil;
    private AnimationDrawable mAnimationDrawable;
    private CustomAlertDialog mCustomAlertDialog;
    private HealthTextView mDeviceBindingTextView;
    private HealthTextView mDeviceBindingUnderTextView;
    private int mDeviceBluetoothType;
    private String mDeviceIconPath;
    private ImageView mDeviceImageView;
    private ContentValues mDeviceInfo;
    private dgw mDeviceManagerModel;
    private dkd mDeviceResourceTool;
    private String mDeviceSn;
    private String mDeviceStateListenId;
    private String mDeviceType;
    private DeviceDownloadSourceInfo mDownloadSource;
    private Handler mHandler;
    private HealthDevice mHealthDevice;
    private gmz mInteractor;
    private boolean mIsGoRopeJump;
    private boolean mIsInvalidation;
    private boolean mIsNfcCommect;
    private boolean mIsOnceSaveDevice;
    private boolean mIsUniformDeviceManagementFlag;
    private MeasurableDevice mMeasurableDevice;
    private LinearLayout mNetWorkErrorLayout;
    private ImageView mPairGuideProgressAnim;
    private int mPosition;
    private String mProductId;
    private dcz mProductInfo;
    private HealthProgressBar mProgressBar;
    private LinearLayout mResourceErrorLayout;
    private String mSsid;
    private TimerTask mTask;
    private dhc mThirdPartyUniteDevice;
    private Timer mTimer;
    private String mTitle;
    private String mUniqueId;
    private float mWeight;
    private ckm mWeightAndFatRateData;
    private BleDeviceHelper mBleDeviceHelper = null;
    private String mMeasureKit = "74e12d04-cf14-4ce8-9e42-7a085f79b708";
    private boolean mIsHealthDataStatus = false;
    private boolean mIsBindStarted = false;
    private boolean mIsBingTimeout = false;
    private boolean mIsJump = false;
    private Timer mConnectTimer = null;
    private NoTitleCustomAlertDialog mDialog = null;
    private boolean isFromScanFragment = false;
    private boolean mIsHasManaer = false;
    private boolean mIsHasBondedProduct = false;
    private boolean isGotoUserInfoActivity = false;
    private String mDeviceIdFromDevice = "";
    private boolean mIsDownloadResourceFailed = false;
    private byte[] mMainHuid = null;
    private ArrayList<String> mUuidList = new ArrayList<>(16);
    private int mErrorCode = Integer.MIN_VALUE;
    private boolean mIsJumpToSetting = false;
    private IHealthDeviceCallback iHealthDeviceCallback = new IHealthDeviceCallback() { // from class: com.huawei.health.device.ui.measure.fragment.device.DeviceBindWaitingFragment.2
        @Override // com.huawei.health.device.callback.IHealthDeviceCallback
        public void onDataChanged(HealthDevice healthDevice, HealthData healthData) {
        }

        @Override // com.huawei.health.device.callback.IHealthDeviceCallback
        public void onDataChanged(HealthDevice healthDevice, List<HealthData> list) {
        }

        @Override // com.huawei.health.device.callback.IHealthDeviceCallback
        public void onProgressChanged(HealthDevice healthDevice, HealthData healthData) {
        }

        @Override // com.huawei.health.device.callback.IHealthDeviceCallback
        public void onStatusChanged(HealthDevice healthDevice, int i) {
            LogUtil.a(DeviceBindWaitingFragment.TAG, "iHealthDeviceCallback onStatusChanged > status: " + i + ", mIsBingTimeout: " + DeviceBindWaitingFragment.this.mIsBingTimeout);
            if (i != -2 || DeviceBindWaitingFragment.this.mIsBingTimeout) {
                return;
            }
            LogUtil.a(DeviceBindWaitingFragment.TAG, "send user auth command- user authentication is completed, the binding of success");
            DeviceBindWaitingFragment.this.cancelTimer();
            DeviceBindWaitingFragment.this.jumpToCompUserInfo(false);
            ceo.d().c(DeviceBindWaitingFragment.this.mProductId, DeviceBindWaitingFragment.this.mProductInfo.s(), DeviceBindWaitingFragment.this.mMeasurableDevice, new IDeviceEventHandler() { // from class: com.huawei.health.device.ui.measure.fragment.device.DeviceBindWaitingFragment.2.1
                @Override // com.huawei.health.device.callback.IDeviceEventHandler
                public void onDeviceFound(HealthDevice healthDevice2) {
                }

                @Override // com.huawei.health.device.callback.IDeviceEventHandler
                public void onScanFailed(int i2) {
                }

                @Override // com.huawei.health.device.callback.IDeviceEventHandler
                public void onStateChanged(int i2) {
                }
            });
        }

        @Override // com.huawei.health.device.callback.IHealthDeviceCallback
        public void onFailed(HealthDevice healthDevice, int i) {
            LogUtil.a(DeviceBindWaitingFragment.TAG, " send user auth command- user authentication is completed- the binding of fail");
        }
    };
    private BindHealthDeviceCallback mBindCallback = new BindHealthDeviceCallback() { // from class: com.huawei.health.device.ui.measure.fragment.device.DeviceBindWaitingFragment.3
        @Override // com.huawei.health.device.callback.IHealthDeviceCallback
        public void onDataChanged(HealthDevice healthDevice, HealthData healthData) {
        }

        @Override // com.huawei.health.device.callback.IHealthDeviceCallback
        public void onDataChanged(HealthDevice healthDevice, List<HealthData> list) {
        }

        @Override // com.huawei.health.device.callback.IHealthDeviceCallback
        public void onProgressChanged(HealthDevice healthDevice, HealthData healthData) {
        }

        @Override // com.huawei.health.device.callback.IHealthDeviceCallback
        public void onStatusChanged(HealthDevice healthDevice, int i) {
        }

        @Override // com.huawei.health.device.callback.BindHealthDeviceCallback
        public void onStatusChanged(HealthDevice healthDevice, int i, Bundle bundle) {
            ReleaseLogUtil.e(DeviceBindWaitingFragment.RELEASE_TAG, "mBindCallback onStatusChanged > status: ", Integer.valueOf(i), ", mIsBingTimeout: ", Boolean.valueOf(DeviceBindWaitingFragment.this.mIsBingTimeout));
            DeviceBindWaitingFragment.this.processCallbackResult(i, bundle);
        }

        @Override // com.huawei.health.device.callback.IHealthDeviceCallback
        public void onFailed(HealthDevice healthDevice, int i) {
            if (i == 3 && DeviceBindWaitingFragment.this.mHandler != null) {
                DeviceBindWaitingFragment.this.mHandler.sendEmptyMessage(2);
            }
            LogUtil.a(DeviceBindWaitingFragment.TAG, " send user auth command, user authentication is completed, the binding of fail");
        }
    };
    IDeviceEventHandler mBindingStatusCallback = new IDeviceEventHandler() { // from class: com.huawei.health.device.ui.measure.fragment.device.DeviceBindWaitingFragment.4
        @Override // com.huawei.health.device.callback.IDeviceEventHandler
        public void onDeviceFound(HealthDevice healthDevice) {
            LogUtil.c(DeviceBindWaitingFragment.TAG, "onDeviceFound");
        }

        @Override // com.huawei.health.device.callback.IDeviceEventHandler
        public void onScanFailed(int i) {
            LogUtil.c(DeviceBindWaitingFragment.TAG, "onScanFailed code:", Integer.valueOf(i));
        }

        @Override // com.huawei.health.device.callback.IDeviceEventHandler
        public void onStateChanged(int i) {
            ReleaseLogUtil.e(DeviceBindWaitingFragment.RELEASE_TAG, "onStateChanged code :", Integer.valueOf(i));
            if (i == 7) {
                DeviceBindWaitingFragment.this.passDevicePair();
                if (cpa.ac(DeviceBindWaitingFragment.this.mProductId) || cpa.ae(DeviceBindWaitingFragment.this.mProductId)) {
                    LogUtil.h(DeviceBindWaitingFragment.TAG, "is own fat scale");
                    return;
                } else {
                    dks.e();
                    return;
                }
            }
            if (i == 8) {
                DeviceBindWaitingFragment.this.pairDeviceFailed();
                return;
            }
            if (i == 10) {
                cjx.e().b(DeviceBindWaitingFragment.this.mProductId, DeviceBindWaitingFragment.this.mProductInfo.s(), DeviceBindWaitingFragment.this.mHealthDevice, this);
            } else if (i == -1) {
                DeviceBindWaitingFragment.this.mErrorCode = -1;
                DeviceBindWaitingFragment.this.pairDeviceFailed();
            } else {
                LogUtil.c(DeviceBindWaitingFragment.TAG, "in else branch");
            }
        }
    };
    private DeviceStatusChangeCallback mDeviceStatusChangeCallback = new DeviceStatusChangeCallback() { // from class: com.huawei.health.device.ui.measure.fragment.device.DeviceBindWaitingFragment$$ExternalSyntheticLambda2
        @Override // com.huawei.devicesdk.callback.DeviceStatusChangeCallback
        public final void onConnectStatusChanged(DeviceInfo deviceInfo, int i, int i2) {
            DeviceBindWaitingFragment.this.m266x3790e493(deviceInfo, i, i2);
        }
    };
    private BroadcastReceiver mNetBroadcastReceiver = new BroadcastReceiver() { // from class: com.huawei.health.device.ui.measure.fragment.device.DeviceBindWaitingFragment.5
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if ("android.net.conn.CONNECTIVITY_CHANGE".equals(intent.getAction())) {
                if (CommonUtil.aa(context)) {
                    DeviceBindWaitingFragment.this.startDownloadThirdPartyDevicePlugin();
                    DeviceBindWaitingFragment.this.unregisterNetworkBroadcastReceiver();
                } else {
                    ReleaseLogUtil.d(DeviceBindWaitingFragment.TAG, "onReceive, net work is error");
                }
            }
        }
    };

    /* renamed from: lambda$new$0$com-huawei-health-device-ui-measure-fragment-device-DeviceBindWaitingFragment, reason: not valid java name */
    /* synthetic */ void m266x3790e493(DeviceInfo deviceInfo, int i, int i2) {
        LogUtil.a(TAG, "new device pair -> deviceInfo: ", deviceInfo.getDeviceName(), "status: ", Integer.valueOf(i), "errCode: ", Integer.valueOf(i2));
        if (i == 30) {
            LogUtil.a(TAG, "bonded_device_success");
            ddw.c().d(this.mDeviceStateListenId);
            cjx.e().b(this.mProductId, this.mProductInfo.s(), this.mThirdPartyUniteDevice, this.mBindingStatusCallback);
        } else if (i == 31) {
            LogUtil.a(TAG, "bonded_device_ing");
        } else {
            if (i != 32) {
                LogUtil.a(TAG, "unknown status", Integer.valueOf(i));
                return;
            }
            LogUtil.a(TAG, "bonded_device_failed");
            ddw.c().d(this.mDeviceStateListenId);
            pairDeviceFailed();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void passDevicePair() {
        String a2;
        ReleaseLogUtil.e(RELEASE_TAG, "DeviceBindWaitingFragment PAIRING_PASSED");
        if (isSwitchPage()) {
            a2 = this.mUniqueId;
        } else {
            a2 = this.mDeviceManagerModel.a(this.mPosition);
        }
        dko.b(this.mProductId, a2, this.mUniqueId);
        if (getDataStatus() && Utils.i() && (!Utils.o() || !isShowAuthorizeDialog())) {
            showAuthorizeAlertDialog();
        } else {
            jumpToActivity();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void pairDeviceFailed() {
        LogUtil.a(TAG, "DeviceBindWaitingFragment PAIRING_FAILED mDeviceBluetoothType:", Integer.valueOf(this.mDeviceBluetoothType));
        if (isSwitchPage() && getActivity() != null) {
            getActivity().setResult(1);
        }
        Bundle bundle = new Bundle();
        bundle.putString("productId", this.mProductId);
        bundle.putString("title", this.mTitle);
        if (this.mErrorCode == -1) {
            bundle.putInt("errorCode", -1);
            this.mErrorCode = Integer.MIN_VALUE;
        }
        if ("9bf158ba-49b0-46aa-9fdf-ed75da1569cf".equals(this.mProductId) || cpa.ad(this.mProductId)) {
            LogUtil.a(TAG, "dnurse or scale");
            bundle.putBoolean(DeviceBindFailedFragment.IS_FROM_BIND, false);
        } else {
            bundle.putBoolean(DeviceBindFailedFragment.IS_FROM_BIND, true);
        }
        LogUtil.bRh_(ERROR_NUMBER, "PluginDevice_PluginDevice", bundle, false, "Fail to bind device ", bundle);
        DeviceBindFailedFragment deviceBindFailedFragment = new DeviceBindFailedFragment();
        if (dks.j(this.mProductId)) {
            ceo.d().a(dds.c().d(), this.mUniqueId);
        } else {
            ceo.d().n(this.mUniqueId);
        }
        deviceBindFailedFragment.setArguments(bundle);
        switchFragment(deviceBindFailedFragment);
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroyView() {
        LogUtil.c(TAG, "DeviceBindWaitingFragment onDestroyView");
        BleDeviceHelper bleDeviceHelper = this.mBleDeviceHelper;
        if (bleDeviceHelper != null) {
            bleDeviceHelper.a(null);
        }
        super.onDestroyView();
    }

    @Override // com.huawei.health.ecologydevice.ui.BaseFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        this.mInteractor = gmz.d();
        ReleaseLogUtil.e(RELEASE_TAG, "DeviceBindWaitingFragment onCreate");
        super.onCreate(bundle);
        if (bundle != null) {
            this.mIsBindStarted = bundle.getBoolean(BUNDLE_KEY_BIND_START, false);
        }
        this.mHandler = new ConnectHandler(this);
        this.mIsJump = false;
        this.mDeviceManagerModel = ddw.c().d();
        Bundle arguments = getArguments();
        initVariables(arguments);
        LogUtil.a(TAG, "DeviceBindWaitingFragment productId is ", this.mProductId, ",mDeviceType = ", this.mDeviceType);
        LogUtil.a(TAG, "HealthUtils.isSwitchPage(getContext()):", Boolean.valueOf(isSwitchPage()));
        if (isEcologyHeadphones(this.mProductId)) {
            return;
        }
        if (!isSwitchPage()) {
            if (this.mDeviceManagerModel == null) {
                LogUtil.a(TAG, "mDeviceManagerModel == null");
                return;
            }
            if (initDeviceData()) {
                dcz d = ResourceManager.e().d(this.mProductId);
                this.mProductInfo = d;
                if (d == null) {
                    resetProductInfo(arguments);
                }
                insertDeviceData();
                return;
            }
            return;
        }
        stop();
    }

    private boolean isEcologyHeadphones(String str) {
        return "4bfc5a27-f2b9-4c41-bd9b-a4a2a18f752c".equals(str);
    }

    private void initVariables(Bundle bundle) {
        if (bundle != null) {
            try {
                this.mUuidList = bundle.getStringArrayList("uuid_list");
                this.mProductId = bundle.getString("productId");
                this.mIsGoRopeJump = bundle.getBoolean(IS_GO_ROPE_JUMP, false);
                this.mDeviceInfo = (ContentValues) bundle.getParcelable("commonDeviceInfo");
                this.mDownloadSource = (DeviceDownloadSourceInfo) bundle.getParcelable(AdShowExtras.DOWNLOAD_SOURCE);
                boolean z = bundle.getBoolean("isNfcConnect", false);
                this.mIsNfcCommect = z;
                LogUtil.a(TAG, "onCreate getBoolean mIsNfcCommect = ", Boolean.valueOf(z));
                ContentValues contentValues = this.mDeviceInfo;
                if (contentValues != null) {
                    this.mProductId = contentValues.getAsString("productId");
                    this.mUniqueId = this.mDeviceInfo.getAsString("uniqueId");
                }
                if (koq.c(this.mUuidList)) {
                    this.mProductId = this.mUuidList.get(0);
                }
                this.mPosition = bundle.getInt("position");
                this.mTitle = bundle.getString("title");
                this.mDeviceType = bundle.getString("DeviceType");
                this.mDeviceIconPath = bundle.getString("DeviceIconPath");
                this.mIsInvalidation = bundle.getBoolean("is_invalidation", false);
                this.isFromScanFragment = bundle.getBoolean("from_scan_fragment", false);
            } catch (Exception unused) {
                LogUtil.b(TAG, "DeviceBindWaitingFragment onCreate Exception");
            }
        }
    }

    private boolean initDeviceData() {
        if (isSwitchPage()) {
            return isHealthDeviceNull();
        }
        ArrayList<dhc> arrayList = new ArrayList<>(10);
        ArrayList<HealthDevice> arrayList2 = new ArrayList<>(10);
        if (this.mDeviceManagerModel == null) {
            LogUtil.a(TAG, "mDeviceManagerModel == null");
            return false;
        }
        boolean d = dkq.c().d();
        this.mIsUniformDeviceManagementFlag = d;
        if (d) {
            arrayList = this.mDeviceManagerModel.d();
        } else {
            arrayList2 = this.mDeviceManagerModel.e();
        }
        if (koq.b(arrayList) && koq.b(arrayList2) && getActivity() != null) {
            LogUtil.a(TAG, "DeviceBindWaitingFragment destroy activity");
            getActivity().finish();
            return false;
        }
        if (!koq.d(arrayList, this.mPosition) && !koq.d(arrayList2, this.mPosition)) {
            return false;
        }
        if (this.mIsUniformDeviceManagementFlag) {
            this.mThirdPartyUniteDevice = arrayList.get(this.mPosition);
            return true;
        }
        this.mHealthDevice = arrayList2.get(this.mPosition);
        return true;
    }

    private boolean isHealthDeviceNull() {
        if ("9bf158ba-49b0-46aa-9fdf-ed75da1569cf".equals(this.mProductId)) {
            cfa cfaVar = new cfa();
            this.mHealthDevice = cfaVar;
            this.mUniqueId = cfaVar.getUniqueId();
        } else {
            if (dfe.a().e()) {
                HealthDevice b = dfe.a().b(this.mProductId);
                this.mHealthDevice = b;
                return b != null;
            }
            BluetoothDevice remoteDevice = BluetoothAdapter.getDefaultAdapter().getRemoteDevice(this.mUniqueId);
            int i = this.mDeviceBluetoothType;
            if (i != 1) {
                if (i == 2) {
                    this.mHealthDevice = ceu.Ej_(remoteDevice);
                }
                return false;
            }
            this.mHealthDevice = cxh.Ra_(remoteDevice);
        }
        if (this.mHealthDevice != null) {
            return true;
        }
        return false;
    }

    private void resetProductInfo(Bundle bundle) {
        HealthDevice healthDevice = this.mHealthDevice;
        if (healthDevice != null && !(healthDevice instanceof MeasurableDevice)) {
            LogUtil.h(TAG, "resetProductInfo mHealthDevice not MeasurableDevice");
            return;
        }
        String a2 = this.mDeviceManagerModel.a(this.mPosition);
        String c = this.mDeviceManagerModel.c(this.mPosition);
        dcz dczVar = new dcz();
        this.mProductInfo = dczVar;
        dczVar.n(ResourceManager.e().d(a2, "SHA-256"));
        this.mProductInfo.a("ic_heartrate_devices", c, a2);
        this.mProductInfo.b("1");
        cev.b bVar = new cev.b();
        bVar.a(1);
        bVar.c(10L, TimeUnit.SECONDS);
        this.mProductInfo.b(bVar.c());
        this.mProductInfo.g(this.mMeasureKit);
        if (bundle != null) {
            try {
                this.mProductInfo.b(HealthDevice.HealthDeviceKind.valueOf(bundle.getString("scan_kind")));
            } catch (Exception unused) {
                LogUtil.b(TAG, "DeviceBindWaitingFragment onCreate Exception");
            }
        }
    }

    private void insertDeviceData() {
        dcz dczVar = this.mProductInfo;
        if (dczVar == null || dczVar.x() == null) {
            LogUtil.h(TAG, "insertDeviceData mProductInfo = null");
            return;
        }
        String a2 = this.mProductInfo.x().a();
        ReleaseLogUtil.e(RELEASE_TAG, "insertDeviceData pair = ", a2);
        if (cpa.ad(this.mProductId) && !crj.c(this.mProductId, "HDK_WEIGHT")) {
            crj.Lv_(getContext(), getActivity(), new VersionNoSupportCallback() { // from class: com.huawei.health.device.ui.measure.fragment.device.DeviceBindWaitingFragment$$ExternalSyntheticLambda8
                @Override // com.huawei.health.device.callback.VersionNoSupportCallback
                public final void onDialogClose() {
                    DeviceBindWaitingFragment.this.m264x2958787();
                }
            });
            cancelTimer();
            return;
        }
        if (needUpdateAppVersion()) {
            Object[] objArr = new Object[2];
            objArr[0] = "current device needs to update the app version, mProductId = ";
            objArr[1] = CommonUtil.bv() ? cpw.d(this.mProductId) : this.mProductId;
            ReleaseLogUtil.e(RELEASE_TAG, objArr);
            crj.Lv_(getContext(), getActivity(), new VersionNoSupportCallback() { // from class: com.huawei.health.device.ui.measure.fragment.device.DeviceBindWaitingFragment$$ExternalSyntheticLambda9
                @Override // com.huawei.health.device.callback.VersionNoSupportCallback
                public final void onDialogClose() {
                    DeviceBindWaitingFragment.this.m265x9be69c8();
                }
            });
            cancelTimer();
            return;
        }
        queryDeviceByUniqueId();
        if (cpa.ae(this.mProductId)) {
            cld.c(this.mProductId, this.mUniqueId);
        }
        if (this.mIsUniformDeviceManagementFlag) {
            bindDeviceByUdsProcess(a2);
            return;
        }
        if (dks.j(this.mProductId)) {
            if (!(this.mHealthDevice instanceof MeasurableDevice)) {
                LogUtil.h(TAG, "attachUniqueId is null or mHealthDevice no instanceof MeasurableDevice");
                this.mBindingStatusCallback.onStateChanged(-1);
                return;
            } else {
                cjx.e().a(dds.c().d(), this.mProductId, this.mProductInfo.s(), (MeasurableDevice) this.mHealthDevice, this.mBindingStatusCallback);
                return;
            }
        }
        bindDeviceByOldProcess(a2);
    }

    /* renamed from: lambda$insertDeviceData$1$com-huawei-health-device-ui-measure-fragment-device-DeviceBindWaitingFragment, reason: not valid java name */
    /* synthetic */ void m264x2958787() {
        popupFragment(null);
    }

    /* renamed from: lambda$insertDeviceData$2$com-huawei-health-device-ui-measure-fragment-device-DeviceBindWaitingFragment, reason: not valid java name */
    /* synthetic */ void m265x9be69c8() {
        popupFragment(null);
    }

    private void queryDeviceByUniqueId() {
        if (!"2GYA".equals(dij.e(this.mProductId))) {
            LogUtil.a(TAG, "is not new HEAD repo device");
        } else {
            dei.c().a(0, this.mUniqueId, new HiDataClientListener() { // from class: com.huawei.health.device.ui.measure.fragment.device.DeviceBindWaitingFragment$$ExternalSyntheticLambda3
                @Override // com.huawei.hihealth.data.listener.HiDataClientListener
                public final void onResult(List list) {
                    DeviceBindWaitingFragment.this.m267x9c3a1717(list);
                }
            });
        }
    }

    /* renamed from: lambda$queryDeviceByUniqueId$3$com-huawei-health-device-ui-measure-fragment-device-DeviceBindWaitingFragment, reason: not valid java name */
    /* synthetic */ void m267x9c3a1717(List list) {
        djr.c().d(isJumpVibrantLifePage(list));
    }

    private boolean isJumpVibrantLifePage(List<HiHealthClient> list) {
        if (koq.b(list)) {
            LogUtil.a(TAG, "isJumpVibrantLifePage clientList is empty");
            return true;
        }
        LogUtil.a(TAG, "isJumpVibrantLifePage clientList size", Integer.valueOf(list.size()));
        Iterator<HiHealthClient> it = list.iterator();
        while (it.hasNext()) {
            if (it.next().getHiDeviceInfo() != null) {
                LogUtil.a(TAG, "isJumpVibrantLifePage hiHealthClient.getHiDeviceInfo() != null");
                return false;
            }
        }
        return true;
    }

    private boolean needUpdateAppVersion() {
        String name = this.mProductInfo.l().name();
        LogUtil.a(TAG, "mProductInfo.getKind() = ", name);
        return msr.c.containsKey(name) && !crj.c(this.mProductId, name);
    }

    private void bindDeviceByUdsProcess(String str) {
        ReleaseLogUtil.e(RELEASE_TAG, "enter bindDeviceByUdsProcess");
        dhc dhcVar = this.mThirdPartyUniteDevice;
        if (dhcVar == null) {
            LogUtil.a(TAG, "mThirdPartyUniteDevice == null ");
            return;
        }
        if (dhcVar.a() != null) {
            dhc dhcVar2 = this.mThirdPartyUniteDevice;
            dhcVar2.a(dhcVar2.a().getDeviceMac());
        }
        if (PAIR_YES.equals(str)) {
            if (dfe.a().e()) {
                cjx.e().b(this.mProductId, this.mProductInfo.s(), this.mThirdPartyUniteDevice, this.mBindingStatusCallback);
                return;
            }
            if (BluetoothAdapter.getDefaultAdapter().getRemoteDevice(this.mThirdPartyUniteDevice.getAddress()).getBondState() != 12) {
                ReleaseLogUtil.e(RELEASE_TAG, "create system bond");
                this.mDeviceStateListenId = UUID.randomUUID().toString();
                ddw.c().a(this.mDeviceStateListenId, this.mDeviceStatusChangeCallback);
                ddw.c().c(this.mThirdPartyUniteDevice);
                return;
            }
            cjx.e().b(this.mProductId, this.mProductInfo.s(), this.mThirdPartyUniteDevice, this.mBindingStatusCallback);
            return;
        }
        cjx.e().b(this.mProductId, this.mProductInfo.s(), this.mThirdPartyUniteDevice, this.mBindingStatusCallback);
    }

    private void bindDeviceByOldProcess(String str) {
        ReleaseLogUtil.e(RELEASE_TAG, "enter bindDeviceByOldProcess");
        if (PAIR_YES.equals(str)) {
            if (dfe.a().e()) {
                cjx.e().b(this.mProductId, this.mProductInfo.s(), this.mHealthDevice, this.mBindingStatusCallback);
                return;
            }
            if (this.mHealthDevice == null) {
                return;
            }
            BluetoothDevice remoteDevice = BluetoothAdapter.getDefaultAdapter().getRemoteDevice(this.mHealthDevice.getAddress());
            try {
                if (remoteDevice.getBondState() != 12) {
                    LogUtil.c(TAG, "NOT BOND_BONDED");
                    createBond(remoteDevice.getClass(), remoteDevice);
                    IntentFilter intentFilter = new IntentFilter();
                    intentFilter.addAction("android.bluetooth.device.action.BOND_STATE_CHANGED");
                    BleDeviceHelper bleDeviceHelper = new BleDeviceHelper(remoteDevice);
                    this.mBleDeviceHelper = bleDeviceHelper;
                    bleDeviceHelper.a(this.mBindingStatusCallback);
                    cpp.a().registerReceiver(bleDeviceHelper, intentFilter);
                } else {
                    cjx.e().b(this.mProductId, this.mProductInfo.s(), this.mHealthDevice, this.mBindingStatusCallback);
                }
                return;
            } catch (IllegalAccessException | IllegalArgumentException | NoSuchMethodException | SecurityException | InvocationTargetException e) {
                LogUtil.b(TAG, "bindDeviceByOldProcess Exception:", ExceptionUtils.d(e));
                return;
            }
        }
        cjx.e().b(this.mProductId, this.mProductInfo.s(), this.mHealthDevice, this.mBindingStatusCallback);
    }

    private void bindWeightDevice() {
        HealthDevice healthDevice = this.mHealthDevice;
        if (healthDevice instanceof MeasurableDevice) {
            MeasurableDevice measurableDevice = (MeasurableDevice) healthDevice;
            this.mMeasurableDevice = measurableDevice;
            measurableDevice.setMeasureKitUuid(this.mProductInfo.s());
            Bundle bundle = new Bundle();
            bundle.putInt("type", -2);
            bundle.putString("productId", this.mProductId);
            if (cpa.ae(this.mProductId)) {
                cjx.e().Gs_(this.mProductId, this.mUniqueId, this.mBindCallback, bundle, this.mMeasurableDevice);
            } else {
                cjx.e().Gs_(this.mProductId, this.mUniqueId, this.iHealthDeviceCallback, bundle, this.mMeasurableDevice);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void jumpToCompUserInfo(boolean z) {
        if (this.mIsJump) {
            return;
        }
        dks.e();
        Bundle bundle = new Bundle();
        bundle.putString("productId", this.mProductId);
        bundle.putString("goto", "devicebind");
        bundle.putString("title", this.mTitle);
        bundle.putBoolean("isBleScale", true);
        bundle.putBoolean("isHonourDevice", z);
        bundle.putBoolean("isNfcConnect", this.mIsNfcCommect);
        LogUtil.a(TAG, "jumpToCompUserInfo put isNfcConnect is true");
        ContentValues contentValues = new ContentValues();
        contentValues.put("uniqueId", this.mUniqueId);
        contentValues.put("productId", this.mProductId);
        bundle.putParcelable("commonDeviceInfo", contentValues);
        Fragment wiFiDeviceBindResultFragment = new WiFiDeviceBindResultFragment();
        wiFiDeviceBindResultFragment.setArguments(bundle);
        switchFragment(wiFiDeviceBindResultFragment);
        WeightDataManager.INSTANCE.setInitFlag(true);
        this.mIsJump = true;
    }

    public boolean createBond(Class cls, BluetoothDevice bluetoothDevice) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException {
        if (cls == null || bluetoothDevice == null) {
            LogUtil.a(TAG, "DeviceBindWaitingFragment createBond param is null");
            return false;
        }
        Boolean bool = (Boolean) cls.getMethod("createBond", new Class[0]).invoke(bluetoothDevice, new Object[0]);
        if (bool == null) {
            return false;
        }
        return bool.booleanValue();
    }

    @Override // com.huawei.health.ecologydevice.ui.BaseFragment, androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        if (layoutInflater == null) {
            LogUtil.a(TAG, "DeviceBindWaitingFragment onCreateView inflater is null");
            FragmentActivity activity = getActivity();
            if (activity != null) {
                activity.finish();
            }
            return null;
        }
        ReleaseLogUtil.e(RELEASE_TAG, "DeviceBindWaitingFragment onCreateView");
        ViewGroup viewGroup2 = (ViewGroup) super.onCreateView(layoutInflater, viewGroup, bundle);
        this.child = layoutInflater.inflate(isSwitchPage() ? R.layout.device_measure_bind_device_layout : R.layout.device_measure_bind_device, viewGroup, false);
        if (viewGroup2 != null) {
            viewGroup2.addView(this.child);
        }
        RopeCloudAuthManager.d(this.mProductId);
        if (isSwitchPage()) {
            initView();
            startBindingDevice();
            super.setTitle(getActivity().getString(R.string.IDS_device_bind_connect));
        } else {
            HealthTextView healthTextView = (HealthTextView) this.child.findViewById(R.id.device_measure_search_prompt);
            ((HealthProgressBar) this.child.findViewById(R.id.hw_device_bind_pb)).setLayerType(1, null);
            if (cpa.ab(this.mProductId)) {
                healthTextView.setText(BaseApplication.getContext().getString(R.string.IDS_plugin_device_weight_device_pair_new_tip, 3));
                startTimer();
            } else {
                healthTextView.setText(R.string.IDS_device_selection_waiting_for_binding);
                if (dks.g(this.mProductId) || dks.k(this.mProductId)) {
                    startTimer();
                }
            }
            healthTextView.setVisibility(0);
            super.setTitle(this.mTitle);
        }
        return viewGroup2;
    }

    private boolean isSwitchPage() {
        return dks.b(getContext()) && !this.isFromScanFragment;
    }

    private void showBindingAnimation() {
        AnimationDrawable animationDrawable = this.mAnimationDrawable;
        if (animationDrawable == null || animationDrawable.isRunning()) {
            return;
        }
        this.mAnimationDrawable.start();
        this.mPairGuideProgressAnim.setBackground(null);
    }

    private void stopBindingAnimation() {
        AnimationDrawable animationDrawable = this.mAnimationDrawable;
        if (animationDrawable == null || !animationDrawable.isRunning()) {
            return;
        }
        this.mAnimationDrawable.stop();
        Drawable frame = this.mAnimationDrawable.getFrame(3);
        if (frame != null) {
            this.mPairGuideProgressAnim.setBackground(frame);
        }
    }

    private void initView() {
        ImageView imageView = (ImageView) this.child.findViewById(R.id.device_pair_guide_progress_anim);
        this.mPairGuideProgressAnim = imageView;
        if (imageView.getDrawable() instanceof AnimationDrawable) {
            this.mAnimationDrawable = (AnimationDrawable) this.mPairGuideProgressAnim.getDrawable();
        }
        this.mDeviceImageView = (ImageView) this.child.findViewById(R.id.device_show_image);
        this.mDeviceBindingTextView = (HealthTextView) this.child.findViewById(R.id.device_binding_text);
        this.mDeviceBindingUnderTextView = (HealthTextView) this.child.findViewById(R.id.device_binding_under_text);
        this.mNetWorkErrorLayout = (LinearLayout) this.child.findViewById(R.id.device_error_bad_layout);
        this.mResourceErrorLayout = (LinearLayout) this.child.findViewById(R.id.device_download_bad_layout);
        this.mProgressBar = (HealthProgressBar) this.child.findViewById(R.id.download_progress);
        LinearLayout linearLayout = (LinearLayout) this.child.findViewById(R.id.device_image_layout);
        if (nsn.ae(this.mainActivity)) {
            LogUtil.a(TAG, "isPad enter");
            cqa.a().Kq_(this.mainActivity, linearLayout, 0.8d, false);
        }
        refreshDeviceImage();
        showBindingAnimation();
    }

    private void refreshDeviceImage() {
        if (cpa.ad(this.mProductId)) {
            getProductId();
            dcz d = ResourceManager.e().d(this.mProductId);
            this.mProductInfo = d;
            if (d != null) {
                this.mDeviceIconPath = dcq.b().a(this.mProductInfo.t(), this.mProductInfo.n().d());
            } else {
                this.mDeviceImageView.setImageResource(R.drawable._2131430602_res_0x7f0b0cca);
                return;
            }
        }
        if (TextUtils.isEmpty(this.mDeviceIconPath)) {
            LogUtil.h(TAG, "refreshDeviceImage mDeviceIconPath is null");
            return;
        }
        Bitmap coo_ = EzPluginManager.a().coo_(this.mDeviceIconPath);
        if (coo_ != null) {
            this.mDeviceImageView.setImageBitmap(coo_);
        } else {
            LogUtil.h(TAG, "refreshDeviceImage error");
        }
    }

    private void setBindingText() {
        int i;
        this.mDeviceBindingTextView.setText(R.string._2130841387_res_0x7f020f2b);
        boolean ae = nsn.ae(BaseApplication.getContext());
        if ("9bf158ba-49b0-46aa-9fdf-ed75da1569cf".equals(this.mProductId)) {
            i = ae ? R.string.IDS_device_bind_audio_hint_pad : R.string.IDS_device_bind_audio_hint;
        } else {
            i = "HDK_WEIGHT".equals(this.mDeviceType) ? R.string.IDS_device_paring_tips : ae ? R.string.IDS_device_binding_hint_pad : R.string.IDS_device_binding_tips;
        }
        this.mDeviceBindingUnderTextView.setText(BaseApplication.getContext().getString(i));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setDownloadSuccess() {
        dcz d = ResourceManager.e().d(this.mProductId);
        this.mProductInfo = d;
        if (d == null) {
            LogUtil.b(TAG, "setDownloadSuccess mProductInfo is null");
            pairDeviceFailed();
            return;
        }
        this.mDeviceBluetoothType = d.x().c();
        this.mProgressBar.setVisibility(8);
        this.mResourceErrorLayout.setVisibility(8);
        this.mNetWorkErrorLayout.setVisibility(8);
        setBindingText();
        showBindingAnimation();
        refreshDeviceImage();
        if (Build.VERSION.SDK_INT > 30) {
            checkScanPermission();
        } else {
            checkGpsEnabledAndOpen();
            checkBluetoothEnabled();
        }
    }

    private void checkGpsEnabledAndOpen() {
        LogUtil.a(TAG, "checkGpsEnabledAndOpen comming");
        if (this.gpslUtil == null) {
            this.gpslUtil = new cop();
        }
        if (!this.gpslUtil.d(getActivity())) {
            LogUtil.a(TAG, "location permission not granted");
            this.gpslUtil.c(getActivity());
        } else {
            LogUtil.h(TAG, "location permission granted");
        }
        if (!this.gpslUtil.b(getActivity())) {
            LogUtil.a(TAG, "GPS service not enable");
            checkPermission(getActivity());
            cqh.c().KZ_(getActivity());
            return;
        }
        LogUtil.h(TAG, "GPS service enable");
    }

    public void checkScanPermission() {
        FragmentActivity activity = getActivity();
        if (activity == null) {
            LogUtil.b(TAG, "checkScanPermission context is null");
        } else {
            LogUtil.a(TAG, "checkScanPermission comming");
            PermissionUtil.b(activity, PermissionUtil.PermissionType.SCAN, new AnonymousClass6(activity));
        }
    }

    /* renamed from: com.huawei.health.device.ui.measure.fragment.device.DeviceBindWaitingFragment$6, reason: invalid class name */
    class AnonymousClass6 extends CustomPermissionAction {
        AnonymousClass6(Context context) {
            super(context);
        }

        @Override // com.huawei.hwcommonmodel.utils.permission.PermissionsResultAction
        public void onGranted() {
            LogUtil.a(DeviceBindWaitingFragment.TAG, "Scan Permission onGranted");
            DeviceBindWaitingFragment.this.checkBluetoothEnabled();
        }

        @Override // com.huawei.ui.commonui.utils.CustomPermissionAction, com.huawei.hwcommonmodel.utils.permission.PermissionsResultAction
        public void onDenied(String str) {
            super.onDenied(str);
            LogUtil.a(DeviceBindWaitingFragment.TAG, "Scan Permission onDenied");
            DeviceBindWaitingFragment.this.enterToMainActivity();
        }

        @Override // com.huawei.ui.commonui.utils.CustomPermissionAction, com.huawei.hwcommonmodel.utils.permission.PermissionsResultAction
        public void onForeverDenied(PermissionUtil.PermissionType permissionType) {
            DeviceBindWaitingFragment.this.mIsJumpToSetting = true;
            LogUtil.a(DeviceBindWaitingFragment.TAG, "Scan Permission onForeverDenied mIsJumpToSetting", Boolean.valueOf(DeviceBindWaitingFragment.this.mIsJumpToSetting));
            onForeverDenied(permissionType, null, new View.OnClickListener() { // from class: com.huawei.health.device.ui.measure.fragment.device.DeviceBindWaitingFragment$6$$ExternalSyntheticLambda0
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    DeviceBindWaitingFragment.AnonymousClass6.this.m273xe4f563c8(view);
                }
            });
        }

        /* renamed from: lambda$onForeverDenied$0$com-huawei-health-device-ui-measure-fragment-device-DeviceBindWaitingFragment$6, reason: not valid java name */
        /* synthetic */ void m273xe4f563c8(View view) {
            DeviceBindWaitingFragment.this.enterToMainActivity();
            ViewClickInstrumentation.clickOnView(view);
        }
    }

    public void checkPermission(Context context) {
        if (context == null) {
            LogUtil.h(TAG, "checkPermission, error ");
            return;
        }
        if (this.gpslUtil == null) {
            this.gpslUtil = new cop();
        }
        boolean d = this.gpslUtil.d(context);
        LogUtil.a(TAG, "checkPermission, isCheckPermission: ", Boolean.valueOf(d));
        if (d) {
            return;
        }
        this.gpslUtil.c(getActivity());
    }

    private void startPairingDevice() {
        ReleaseLogUtil.e(RELEASE_TAG, "startPairingDevice");
        if (isEcologyHeadphones(this.mProductId)) {
            if (needUpdateAppVersion()) {
                LogUtil.a(TAG, "current device needs to update the app version, mProductId = ", this.mProductId);
                crj.Lv_(getContext(), getActivity(), new VersionNoSupportCallback() { // from class: com.huawei.health.device.ui.measure.fragment.device.DeviceBindWaitingFragment$$ExternalSyntheticLambda1
                    @Override // com.huawei.health.device.callback.VersionNoSupportCallback
                    public final void onDialogClose() {
                        DeviceBindWaitingFragment.this.m272xda83a04d();
                    }
                });
                return;
            } else {
                new dcp().d(this.mUniqueId, new NemoDeviceCallback() { // from class: com.huawei.health.device.ui.measure.fragment.device.DeviceBindWaitingFragment.7
                    @Override // com.huawei.health.ecologydevice.callback.NemoDeviceCallback
                    public void bondSuccess(BluetoothDevice bluetoothDevice) {
                        LogUtil.a(DeviceBindWaitingFragment.TAG, "startPairingDevice--bondSuccess");
                        new dcp().d(DeviceBindWaitingFragment.this.getActivity(), DeviceBindWaitingFragment.this.mUniqueId);
                        dks.e();
                        FragmentActivity activity = DeviceBindWaitingFragment.this.getActivity();
                        if (activity != null) {
                            activity.finish();
                        }
                    }

                    @Override // com.huawei.health.ecologydevice.callback.NemoDeviceCallback
                    public void bondError(int i) {
                        LogUtil.h(DeviceBindWaitingFragment.TAG, "startPairingDevice,bindState:", Integer.valueOf(i));
                        DeviceBindWaitingFragment.this.pairDeviceFailed();
                    }
                });
                return;
            }
        }
        if (initDeviceData()) {
            if (cpa.ab(this.mProductId)) {
                startTimer();
            } else if (dks.g(this.mProductId) || dks.k(this.mProductId)) {
                startTimer();
            }
            insertDeviceData();
            return;
        }
        LogUtil.b(TAG, "setDownloadSuccess initDeviceData failed");
        pairDeviceFailed();
    }

    /* renamed from: lambda$startPairingDevice$4$com-huawei-health-device-ui-measure-fragment-device-DeviceBindWaitingFragment, reason: not valid java name */
    /* synthetic */ void m272xda83a04d() {
        popupFragment(null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setFailedLayout() {
        if (!CommonUtil.aa(BaseApplication.getContext())) {
            setNetWorkErrorLayout();
            return;
        }
        stopBindingAnimation();
        this.mDeviceBindingTextView.setText(R.string.IDS_downlod_device_error);
        this.mDeviceBindingUnderTextView.setText(R.string._2130844160_res_0x7f021a00);
        this.mProgressBar.setVisibility(8);
        this.mNetWorkErrorLayout.setVisibility(8);
        this.mResourceErrorLayout.setVisibility(0);
        this.mResourceErrorLayout.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.health.device.ui.measure.fragment.device.DeviceBindWaitingFragment$$ExternalSyntheticLambda12
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                DeviceBindWaitingFragment.this.m268xdef53701(view);
            }
        });
    }

    /* renamed from: lambda$setFailedLayout$5$com-huawei-health-device-ui-measure-fragment-device-DeviceBindWaitingFragment, reason: not valid java name */
    /* synthetic */ void m268xdef53701(View view) {
        startDownloadThirdPartyDevicePlugin();
        ViewClickInstrumentation.clickOnView(view);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setProgressBarLayout(int i) {
        if (i > 0) {
            this.mProgressBar.setProgress(i);
        }
    }

    private void setNetWorkErrorLayout() {
        BroadcastReceiver broadcastReceiver;
        try {
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
            if (getActivity() != null && (broadcastReceiver = this.mNetBroadcastReceiver) != null) {
                getActivity().registerReceiver(broadcastReceiver, intentFilter);
            }
        } catch (IllegalArgumentException unused) {
            LogUtil.b(TAG, "handleDownloadNetError register receiver is error");
        }
        stopBindingAnimation();
        this.mDeviceBindingTextView.setText(R.string.IDS_downlod_device_error);
        this.mDeviceBindingUnderTextView.setText(R.string._2130844160_res_0x7f021a00);
        this.mProgressBar.setVisibility(8);
        this.mNetWorkErrorLayout.setVisibility(0);
        this.mResourceErrorLayout.setVisibility(8);
        this.mNetWorkErrorLayout.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.health.device.ui.measure.fragment.device.DeviceBindWaitingFragment$$ExternalSyntheticLambda11
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                DeviceBindWaitingFragment.this.m269x94437ddf(view);
            }
        });
    }

    /* renamed from: lambda$setNetWorkErrorLayout$6$com-huawei-health-device-ui-measure-fragment-device-DeviceBindWaitingFragment, reason: not valid java name */
    /* synthetic */ void m269x94437ddf(View view) {
        startActivity(new Intent("android.settings.SETTINGS"));
        ViewClickInstrumentation.clickOnView(view);
    }

    @Override // androidx.fragment.app.Fragment
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putBoolean(BUNDLE_KEY_BIND_START, this.mIsBindStarted);
    }

    private void startBindingDevice() {
        if (this.mIsBindStarted) {
            LogUtil.h(TAG, "startBindingDevice failed: DeviceBindWaitingFragment is not visible");
            return;
        }
        if (getActivity() == null) {
            LogUtil.h(TAG, "startDownloadThirdPartyDevicePlugin activity is null");
            pairDeviceFailed();
            return;
        }
        ReleaseLogUtil.e(RELEASE_TAG, "startBindingDevice productId: ", cpw.d(this.mProductId), ", uniqueId: ", cpw.d(this.mUniqueId));
        if (dks.j(this.mProductId)) {
            if (dks.b(dds.c().d(), this.mUniqueId)) {
                pairDeviceFailed();
                return;
            }
        } else if (dks.d(this.mProductId, this.mUniqueId)) {
            if (isEcologyHeadphones(this.mProductId)) {
                FragmentActivity activity = getActivity();
                if (activity != null) {
                    new dcp().d(activity, this.mUniqueId);
                    activity.finish();
                }
                LogUtil.h(TAG, "activity is null");
                return;
            }
            LogUtil.b(TAG, "startDownloadThirdPartyDevicePlugin device is bond");
            if (this.mIsInvalidation && (cpa.ac(this.mProductId) || cpa.ae(this.mProductId))) {
                LogUtil.h(TAG, "is own scale device");
            } else {
                pairDeviceFailed();
                return;
            }
        }
        ReleaseLogUtil.e(RELEASE_TAG, "isPluginAvaiable:", Boolean.valueOf(EzPluginManager.a().g(this.mProductId)));
        if (!CommonUtil.aa(getContext())) {
            if (EzPluginManager.a().g(this.mProductId)) {
                setDownloadSuccess();
                return;
            } else {
                setNetWorkErrorLayout();
                return;
            }
        }
        if (TextUtils.isEmpty(this.mDeviceType)) {
            LogUtil.b(TAG, "startDownloadThirdPartyDevicePlugin mDeviceType is null");
            pairDeviceFailed();
        } else {
            startDownloadThirdPartyDevicePlugin();
            this.mIsBindStarted = true;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startDownloadThirdPartyDevicePlugin() {
        List singletonList;
        LogUtil.a(TAG, "startDownloadThirdPartyDevicePlugin");
        this.mProgressBar.setVisibility(0);
        this.mNetWorkErrorLayout.setVisibility(8);
        this.mResourceErrorLayout.setVisibility(8);
        setBindingText();
        showBindingAnimation();
        dkd dkdVar = this.mDeviceResourceTool;
        if (dkdVar != null) {
            dkdVar.e();
        }
        if (koq.c(this.mUuidList)) {
            singletonList = this.mUuidList;
        } else {
            singletonList = Collections.singletonList(this.mProductId);
        }
        dkd dkdVar2 = new dkd(getActivity(), this.mDeviceType, 1, singletonList, new dkc() { // from class: com.huawei.health.device.ui.measure.fragment.device.DeviceBindWaitingFragment.8
            @Override // defpackage.dkb
            public void onSuccess() {
                LogUtil.a(DeviceBindWaitingFragment.TAG, "startDownloadThirdPartyDevicePlugin onSuccess");
                DeviceBindWaitingFragment.this.mIsDownloadResourceFailed = false;
                DeviceBindWaitingFragment.this.getProductId();
                DeviceBindWaitingFragment.this.setDownloadSuccess();
            }

            @Override // defpackage.dkb
            public void onDownload(int i) {
                super.onDownload(i);
                DeviceBindWaitingFragment.this.setProgressBarLayout(i);
            }

            @Override // defpackage.dkb
            public void onFailure(int i) {
                DeviceBindWaitingFragment.this.mIsDownloadResourceFailed = true;
                DeviceBindWaitingFragment.this.setFailedLayout();
                ReleaseLogUtil.d(DeviceBindWaitingFragment.TAG, DeviceBindWaitingFragment.this.mDeviceType, " download failure, errorCode is ", Integer.valueOf(i));
            }
        });
        this.mDeviceResourceTool = dkdVar2;
        dkdVar2.a(this.mDownloadSource);
        this.mDeviceResourceTool.b();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void unregisterNetworkBroadcastReceiver() {
        try {
            if (this.mNetBroadcastReceiver == null || getActivity() == null) {
                return;
            }
            LogUtil.a(TAG, "unregister mNetBroadcastReceiver");
            getActivity().unregisterReceiver(this.mNetBroadcastReceiver);
            this.mNetBroadcastReceiver = null;
        } catch (IllegalArgumentException unused) {
            LogUtil.b(TAG, "unregisterBroadcastReceiver is error");
        }
    }

    @Override // com.huawei.health.ecologydevice.ui.BaseFragment, androidx.fragment.app.Fragment
    public void onDestroy() {
        LogUtil.a(TAG, "onDestroy");
        dcz dczVar = this.mProductInfo;
        if (dczVar != null && dczVar.x().c() == 2) {
            cjx.e().b();
        }
        if (this.mBleDeviceHelper != null) {
            cpp.a().unregisterReceiver(this.mBleDeviceHelper);
            this.mBleDeviceHelper = null;
        }
        dkd dkdVar = this.mDeviceResourceTool;
        if (dkdVar != null) {
            dkdVar.e();
            this.mDeviceResourceTool = null;
        }
        stopBindingAnimation();
        unregisterNetworkBroadcastReceiver();
        cgt.e().b(this.mBindCallback);
        super.onDestroy();
    }

    private void saveDeviceKind(HealthDevice.HealthDeviceKind healthDeviceKind) {
        LogUtil.a(TAG, "saveDeviceKind");
        StorageParams storageParams = new StorageParams();
        if (HealthDevice.HealthDeviceKind.HDK_WEIGHT.equals(healthDeviceKind)) {
            setSharedPreference("BIND_WEIGHT", storageParams);
            return;
        }
        if (HealthDevice.HealthDeviceKind.HDK_BLOOD_PRESSURE.equals(healthDeviceKind)) {
            setSharedPreference("BIND_BLOOD_PRESSURE", storageParams);
            return;
        }
        if (HealthDevice.HealthDeviceKind.HDK_BLOOD_SUGAR.equals(healthDeviceKind)) {
            setSharedPreference("BIND_BLOOD_SUGAR", storageParams);
            return;
        }
        if (HealthDevice.HealthDeviceKind.HDK_HEART_RATE.equals(healthDeviceKind)) {
            setSharedPreference("BIND_HEART_RATE", storageParams);
            return;
        }
        if (HealthDevice.HealthDeviceKind.HDK_BODY_TEMPERATURE.equals(healthDeviceKind)) {
            setSharedPreference("BIND_BODY_TEMPERATURE", storageParams);
            return;
        }
        if (HealthDevice.HealthDeviceKind.HDK_BLOOD_OXYGEN.equals(healthDeviceKind)) {
            setSharedPreference("BIND_BLOOD_OXYGEN", storageParams);
            return;
        }
        if (HealthDevice.HealthDeviceKind.HDK_ROPE_SKIPPING.equals(healthDeviceKind)) {
            setSharedPreference("BIND_ROPE_SKIPPING", storageParams);
            return;
        }
        if (HealthDevice.HealthDeviceKind.HDK_TREADMILL.equals(healthDeviceKind)) {
            setSharedPreference("BIND_TREADMILL", storageParams);
            return;
        }
        if (HealthDevice.HealthDeviceKind.HDK_EXERCISE_BIKE.equals(healthDeviceKind)) {
            setSharedPreference("BIND_EXERCISE_BIKE", storageParams);
            return;
        }
        if (HealthDevice.HealthDeviceKind.HDK_ROWING_MACHINE.equals(healthDeviceKind)) {
            setSharedPreference("BIND_ROWING_MACHINE", storageParams);
            return;
        }
        if (HealthDevice.HealthDeviceKind.HDK_ELLIPTICAL_MACHINE.equals(healthDeviceKind)) {
            setSharedPreference("BIND_ELLIPTICAL_MACHINE", storageParams);
        } else if (HealthDevice.HealthDeviceKind.HDK_WALKING_MACHINE.equals(healthDeviceKind)) {
            setSharedPreference("BIND_WALKING_MACHINE", storageParams);
        } else {
            LogUtil.c(TAG, "in else branch");
        }
    }

    private void setSharedPreference(String str, StorageParams storageParams) {
        SharedPreferenceManager.e(cpp.a(), String.valueOf(10000), str, "1", storageParams);
    }

    private boolean getDataStatus() {
        if ("true".equals(this.mInteractor.c(7))) {
            this.mIsHealthDataStatus = true;
        } else {
            this.mIsHealthDataStatus = false;
        }
        return !this.mIsHealthDataStatus;
    }

    private void jumpToActivity() {
        saveDeviceKind(this.mProductInfo.l());
        cjx.e().b();
        boolean z = false;
        MeasurableDevice d = ceo.d().d(this.mUniqueId, false);
        setHourDeviceBind();
        ReleaseLogUtil.e(RELEASE_TAG, "sSportType is " + sSportType);
        if (fhw.e.containsKey(Integer.valueOf(sSportType))) {
            if (!((HealthDevice.HealthDeviceKind) fhw.e.get(Integer.valueOf(sSportType))).equals(this.mProductInfo.l())) {
                LogUtil.a(TAG, "wrong device");
                ObserverManagerUtil.c(DEVICE_ASSOCIATION, new Object[0]);
            } else {
                LogUtil.a(TAG, "already binded, return to cp page");
                ObserverManagerUtil.c(DEVICE_ASSOCIATION, this.mUniqueId, fhw.f12519a.get(Integer.valueOf(sSportType)));
            }
            sSportType = 0;
            popupFragment(null);
            return;
        }
        boolean equals = HiAnalyticsConstant.KeyAndValue.NUMBER_01.equals(this.mProductInfo.p());
        if (d != null && d.isAutoDevice()) {
            z = true;
        }
        if ("1".equals(this.mProductInfo.j())) {
            if (dks.j(this.mProductId)) {
                dks.c(getContext(), this.mProductInfo, this.mProductId, this.mUniqueId, getArguments() == null ? "" : getArguments().getString("fittings_host_sn"));
                ((DeviceMainActivityApi) Services.c("PluginDevice", DeviceMainActivityApi.class)).onBackPressed(this.mainActivity);
                return;
            } else {
                popupFragment(null);
                dks.d(getContext(), this.mProductInfo, this.mProductId, this.mUniqueId);
                return;
            }
        }
        if (CommonUtil.cg() && BleConstants.BLE_THIRD_DEVICE_H5.equals(this.mProductInfo.m().d())) {
            switchToSuccessOrH5Intro();
            return;
        }
        if (z && equals) {
            switchToSuccessOrSilent(new Bundle());
            return;
        }
        if (d != null && this.mProductInfo.l().equals(HealthDevice.HealthDeviceKind.HDK_HEART_RATE)) {
            switchToSuccessOrHeartRate(new Bundle());
        } else if (d != null && (this.mProductInfo.l().equals(HealthDevice.HealthDeviceKind.HDK_ROPE_SKIPPING) || dks.k(this.mProductId))) {
            enterRopeSkippingOrSport();
        } else {
            defaultJump();
        }
    }

    private void defaultJump() {
        ReleaseLogUtil.e(RELEASE_TAG, "------------ is honour new device> " + cpa.ac(this.mProductId));
        if (cpa.ac(this.mProductId) || cpa.ae(this.mProductId)) {
            bindWeightDevice();
        } else if (cpa.aa(this.mProductId)) {
            jumpToCompUserInfo(true);
        } else {
            this.mHandler.postDelayed(new Runnable() { // from class: com.huawei.health.device.ui.measure.fragment.device.DeviceBindWaitingFragment$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    DeviceBindWaitingFragment.this.m263x6ad97c9();
                }
            }, 1000L);
        }
    }

    /* renamed from: lambda$defaultJump$7$com-huawei-health-device-ui-measure-fragment-device-DeviceBindWaitingFragment, reason: not valid java name */
    /* synthetic */ void m263x6ad97c9() {
        Bundle bundle = new Bundle();
        bundle.putString("productId", this.mProductId);
        ContentValues contentValues = new ContentValues();
        contentValues.put("uniqueId", this.mUniqueId);
        contentValues.put("productId", this.mProductId);
        bundle.putParcelable("commonDeviceInfo", contentValues);
        bundle.putBoolean(BUNDLE_KEY_IS_BIND_SUCCESS, true);
        bundle.putBoolean(IS_GO_ROPE_JUMP, this.mIsGoRopeJump);
        bundle.putBoolean("isNfcConnect", this.mIsNfcCommect);
        LogUtil.a(TAG, "jumpToActivity putBoolean mIsNfcCommect = ", Boolean.valueOf(this.mIsNfcCommect));
        setCourseEquipmentParameters(bundle);
        switchToSuccessOrIntroduction(bundle);
    }

    private void setCourseEquipmentParameters(Bundle bundle) {
        Bundle arguments = getArguments();
        if (arguments == null) {
            LogUtil.a(TAG, "argumentsBundle is null");
            return;
        }
        int i = arguments.getInt("KEY_INTENT_COURSE_ENTRANCE", 0);
        String string = arguments.getString("KEY_INTENT_EQUIPMENT_TYPE");
        bundle.putInt("KEY_INTENT_COURSE_ENTRANCE", i);
        bundle.putString("KEY_INTENT_EQUIPMENT_TYPE", string);
        LogUtil.a(TAG, "setCourseEquipmentParameters courseEntryType = " + i + ", courseEquipmentType = " + string);
    }

    private void switchToSuccessOrH5Intro() {
        Bundle bundle = new Bundle();
        putData(bundle);
        if (isSwitchPage()) {
            DeviceBindSuccessFragment deviceBindSuccessFragment = new DeviceBindSuccessFragment();
            deviceBindSuccessFragment.setArguments(bundle);
            switchFragment(deviceBindSuccessFragment);
        } else {
            Intent Wx_ = dks.Wx_(this.mProductInfo, this.mProductId, this.mUniqueId);
            if (Wx_ != null) {
                startActivity(Wx_);
                popupFragment(null);
            }
        }
    }

    private void switchToSuccessOrIntroduction(Bundle bundle) {
        if (isSwitchPage()) {
            DeviceBindSuccessFragment deviceBindSuccessFragment = new DeviceBindSuccessFragment();
            deviceBindSuccessFragment.setArguments(bundle);
            switchFragment(deviceBindSuccessFragment);
        } else {
            ProductIntroductionFragment productIntroductionFragment = new ProductIntroductionFragment();
            productIntroductionFragment.setArguments(bundle);
            switchFragment(productIntroductionFragment);
        }
    }

    private void switchToSuccessOrHeartRate(Bundle bundle) {
        Fragment heartRateDeviceRunGuide;
        putData(bundle);
        if (isSwitchPage()) {
            heartRateDeviceRunGuide = new DeviceBindSuccessFragment();
        } else {
            heartRateDeviceRunGuide = new HeartRateDeviceRunGuide();
        }
        heartRateDeviceRunGuide.setArguments(bundle);
        switchFragment(heartRateDeviceRunGuide);
    }

    private void switchToSuccessOrSilent(Bundle bundle) {
        Fragment deviceSilentGuideFragment;
        bundle.putString(BUNDLE_KEY_VIEW, "bond");
        putData(bundle);
        if (isSwitchPage()) {
            deviceSilentGuideFragment = new DeviceBindSuccessFragment();
        } else {
            deviceSilentGuideFragment = new DeviceSilentGuideFragment();
        }
        deviceSilentGuideFragment.setArguments(bundle);
        switchFragment(deviceSilentGuideFragment);
    }

    private void putData(Bundle bundle) {
        bundle.putString("productId", this.mProductId);
        bundle.putString("title", this.mTitle);
        bundle.putBoolean(BUNDLE_KEY_IS_BIND_SUCCESS, true);
        ContentValues contentValues = new ContentValues();
        contentValues.put("uniqueId", this.mUniqueId);
        contentValues.put("productId", this.mProductId);
        bundle.putParcelable("commonDeviceInfo", contentValues);
    }

    private void enterRopeSkippingOrSport() {
        Fragment createProductFragment;
        LogUtil.a(TAG, "enterRopeSkippingOrSport");
        cancelTimer();
        Bundle bundle = new Bundle();
        bundle.putString("productId", this.mProductId);
        bundle.putString("title", this.mTitle);
        bundle.putBoolean(BUNDLE_KEY_IS_BIND_SUCCESS, true);
        bundle.putBoolean(IS_GO_ROPE_JUMP, this.mIsGoRopeJump);
        ContentValues contentValues = new ContentValues();
        contentValues.put("uniqueId", this.mUniqueId);
        contentValues.put("productId", this.mProductId);
        bundle.putParcelable("commonDeviceInfo", contentValues);
        setCourseEquipmentParameters(bundle);
        if (isSwitchPage()) {
            createProductFragment = new DeviceBindSuccessFragment();
        } else if (this.mProductInfo.l().equals(HealthDevice.HealthDeviceKind.HDK_ROPE_SKIPPING)) {
            createProductFragment = new RopeSkippingGuideFragment();
        } else if (dks.k(this.mProductId)) {
            createProductFragment = new SportDeviceIntroductionFragment();
        } else {
            createProductFragment = ProductCreateFactory.createProductFragment(this.mProductId);
        }
        createProductFragment.setArguments(bundle);
        switchFragment(createProductFragment);
    }

    private void setHourDeviceBind() {
        if ("7a1063dd-0e0f-4a72-9939-461476ff0259".equalsIgnoreCase(this.mProductId) && cpa.d()) {
            cpa.o();
            Bundle bundle = new Bundle();
            bundle.putInt("type", -2);
            cjx.e().Gr_(this.mProductId, this.mUniqueId, new IHealthDeviceCallback() { // from class: com.huawei.health.device.ui.measure.fragment.device.DeviceBindWaitingFragment.9
                @Override // com.huawei.health.device.callback.IHealthDeviceCallback
                public void onDataChanged(HealthDevice healthDevice, HealthData healthData) {
                }

                @Override // com.huawei.health.device.callback.IHealthDeviceCallback
                public void onDataChanged(HealthDevice healthDevice, List<HealthData> list) {
                }

                @Override // com.huawei.health.device.callback.IHealthDeviceCallback
                public void onFailed(HealthDevice healthDevice, int i) {
                }

                @Override // com.huawei.health.device.callback.IHealthDeviceCallback
                public void onProgressChanged(HealthDevice healthDevice, HealthData healthData) {
                }

                @Override // com.huawei.health.device.callback.IHealthDeviceCallback
                public void onStatusChanged(HealthDevice healthDevice, int i) {
                }
            }, bundle);
        }
    }

    private boolean isShowAuthorizeDialog() {
        return "true".equals(SharedPreferenceManager.b(getActivity(), Integer.toString(10025), "health_manual_record_sync_agree"));
    }

    private void showAuthorizeAlertDialog() {
        CustomAlertDialog customAlertDialog = this.mCustomAlertDialog;
        if (customAlertDialog != null && customAlertDialog.isShowing()) {
            LogUtil.a(TAG, "CustomAlertDialog, is showing.");
            return;
        }
        UserProfileMgrApi userProfileMgrApi = (UserProfileMgrApi) Services.c("HWUserProfileMgr", UserProfileMgrApi.class);
        gnb popOutWindowInfo = userProfileMgrApi.getPopOutWindowInfo(getActivity(), "privacy_health_data_");
        int a2 = popOutWindowInfo.a();
        long c = popOutWindowInfo.c();
        long currentTimeMillis = System.currentTimeMillis();
        if (a2 >= 3 || currentTimeMillis - c <= 86400000) {
            jumpToActivity();
            return;
        }
        userProfileMgrApi.setPopOutWindowInfo(getActivity(), "privacy_health_data_");
        View inflate = getActivity().getLayoutInflater().inflate(R.layout.commonui_dialog_health_tip, (ViewGroup) null);
        ((LinearLayout) inflate.findViewById(R.id.remind_layout)).setVisibility(8);
        ((HealthTextView) inflate.findViewById(R.id.hw_health_service_item_one)).setText(R.string._2130841190_res_0x7f020e66);
        judgeHealthLayout(inflate);
        CustomAlertDialog.Builder builder = new CustomAlertDialog.Builder(getActivity());
        builder.cyp_(inflate);
        builder.e(R.string.IDS_service_area_notice_title);
        builder.cyo_(R.string._2130841555_res_0x7f020fd3, new DialogInterface.OnClickListener() { // from class: com.huawei.health.device.ui.measure.fragment.device.DeviceBindWaitingFragment$$ExternalSyntheticLambda6
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                DeviceBindWaitingFragment.this.m270x3a58924d(dialogInterface, i);
            }
        });
        builder.cyn_(R.string._2130841129_res_0x7f020e29, new DialogInterface.OnClickListener() { // from class: com.huawei.health.device.ui.measure.fragment.device.DeviceBindWaitingFragment$$ExternalSyntheticLambda7
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                DeviceBindWaitingFragment.this.m271x4181748e(dialogInterface, i);
            }
        });
        CustomAlertDialog c2 = builder.c();
        this.mCustomAlertDialog = c2;
        c2.setCancelable(false);
        this.mCustomAlertDialog.show();
    }

    /* renamed from: lambda$showAuthorizeAlertDialog$8$com-huawei-health-device-ui-measure-fragment-device-DeviceBindWaitingFragment, reason: not valid java name */
    /* synthetic */ void m270x3a58924d(DialogInterface dialogInterface, int i) {
        if (!this.mIsHealthDataStatus) {
            this.mInteractor.c(7, true, "DeviceBindWaitingFragment", (IBaseResponseCallback) null);
        }
        jumpToActivity();
        ViewClickInstrumentation.clickOnDialog(dialogInterface, i);
    }

    /* renamed from: lambda$showAuthorizeAlertDialog$9$com-huawei-health-device-ui-measure-fragment-device-DeviceBindWaitingFragment, reason: not valid java name */
    /* synthetic */ void m271x4181748e(DialogInterface dialogInterface, int i) {
        jumpToActivity();
        ViewClickInstrumentation.clickOnDialog(dialogInterface, i);
    }

    private void judgeHealthLayout(View view) {
        if (this.mIsHealthDataStatus) {
            return;
        }
        LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.health_status_layout);
        HealthTextView healthTextView = (HealthTextView) view.findViewById(R.id.health_tip);
        linearLayout.setVisibility(0);
        if (!Utils.o()) {
            healthTextView.setText(R.string._2130841143_res_0x7f020e37);
        } else {
            healthTextView.setText(R.string._2130841238_res_0x7f020e96);
        }
    }

    @Override // com.huawei.health.ecologydevice.ui.BaseFragment
    public boolean onBackPressed() {
        if (dks.g(this.mProductId) || dks.k(this.mProductId)) {
            cancelTimer();
        }
        if (this.mIsDownloadResourceFailed) {
            return true;
        }
        return !cpa.aw(this.mProductId);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void cancelTimer() {
        synchronized (this) {
            Timer timer = this.mConnectTimer;
            if (timer != null) {
                timer.cancel();
                this.mConnectTimer = null;
                LogUtil.a(TAG, "Cancel the timer connected devices");
            } else {
                LogUtil.a(TAG, "Connection timer has been canceled");
            }
        }
    }

    private void startTimer() {
        synchronized (this) {
            if (this.mConnectTimer == null) {
                this.mConnectTimer = new Timer(TAG);
                LogUtil.a(TAG, "Start the timer connected devices");
                this.mConnectTimer.schedule(new MyTimerTask(this), OpAnalyticsConstants.H5_LOADING_DELAY);
            }
        }
    }

    @Override // com.huawei.health.ecologydevice.ui.BaseFragment, androidx.fragment.app.Fragment
    public void onResume() {
        LogUtil.a(TAG, "onResume mIsJumpToSetting ", Boolean.valueOf(this.mIsJumpToSetting));
        if (this.mIsJumpToSetting) {
            this.mIsJumpToSetting = false;
            checkScanPermission();
        }
        if (this.isGotoUserInfoActivity) {
            Bundle bundle = new Bundle();
            bundle.putString("productId", this.mProductId);
            bundle.putParcelable("commonDeviceInfo", this.mDeviceInfo);
            bundle.putString("goto", "devicebind");
            bundle.putBoolean("isNfcConnect", this.mIsNfcCommect);
            LogUtil.a(TAG, "onResume putBoolean mIsNfcCommect = ", Boolean.valueOf(this.mIsNfcCommect));
            HagridDeviceManagerFragment hagridDeviceManagerFragment = new HagridDeviceManagerFragment();
            hagridDeviceManagerFragment.setArguments(bundle);
            switchFragment(hagridDeviceManagerFragment);
        }
        super.onResume();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void stop() {
        if (this.mProductId != null) {
            Bundle bundle = new Bundle();
            bundle.putInt("type", -6);
            bundle.putString("productId", this.mProductId);
            HealthDevice c = cjx.e().c(this.mUniqueId);
            if (c != null) {
                cgt.e().prepare(c, null, bundle);
            } else {
                cgt.e().prepare(null, null, bundle);
            }
        }
        cjx.e().e(this.mProductId, this.mUniqueId, -6);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void processCallbackResult(int i, Bundle bundle) {
        if (bundle == null) {
            LogUtil.h(TAG, "processCallbackResult bundle is null");
            return;
        }
        MeasurableDevice measurableDevice = (MeasurableDevice) this.mHealthDevice;
        LogUtil.a(TAG, "processCallbackResult status = ", Integer.valueOf(i), "measurableDevice.getUniqueId:", measurableDevice.getUniqueId());
        boolean z = false;
        if (i == -7) {
            try {
                byte[] byteArray = bundle.getByteArray("huid");
                this.mMainHuid = byteArray;
                LogUtil.a(TAG, "processCallbackResult mMainHuid = ", byteArray);
            } catch (ArrayIndexOutOfBoundsException unused) {
                LogUtil.b(TAG, "huid getByteArray exception");
            }
            byte[] bArr = this.mMainHuid;
            if (bArr != null && bArr.length != 0) {
                z = true;
            }
            this.mIsHasManaer = z;
            LogUtil.a(TAG, "mIsHasManager = ", Boolean.valueOf(z));
            processBindedSameWiFi(bundle);
            return;
        }
        if (i == -8) {
            this.mIsHasManaer = false;
            if (!this.mIsHasBondedProduct || this.mHandler == null) {
                return;
            }
            LogUtil.a(TAG, "try to bond different scales of same product");
            this.mHandler.sendEmptyMessage(3);
            return;
        }
        if (i == -12) {
            if (bundle.getInt("ret") != 0) {
                LogUtil.h(TAG, " send config info failed");
                return;
            } else {
                LogUtil.a(TAG, " send config info success.");
                return;
            }
        }
        processCallbackResultPro(i, bundle, measurableDevice);
    }

    private void processCallbackResultPro(int i, Bundle bundle, MeasurableDevice measurableDevice) {
        if (i == -13) {
            if (bundle.getInt("ret") != 0) {
                LogUtil.h(TAG, " set manager info failed");
                return;
            } else {
                LogUtil.a(TAG, " set manager info success.");
                return;
            }
        }
        if (i == -16) {
            String string = bundle.getString(HealthEngineRequestManager.PARAMS_DEVICE_SN);
            this.mDeviceSn = string;
            LogUtil.a(TAG, " save device info, deviceSn:", cpw.d(string), "measurableDevice.getUniqueId:", measurableDevice.getUniqueId());
            measurableDevice.setSerialNumber(this.mDeviceSn);
            checkAndUpdateLocalDevice(measurableDevice, this.mDeviceSn);
            return;
        }
        if (i == -9) {
            this.mSsid = bundle.getString("deviceSsid");
            cpa.d(this.mDeviceSn, measurableDevice.getUniqueId());
            cpa.a(measurableDevice.getUniqueId(), this.mDeviceSn);
            setDeviceInfo(measurableDevice, i);
            gotoBluetoothPairSuccessPage(this.mIsHasManaer);
            cancelTimer();
            return;
        }
        if (i == -2) {
            processBindType(bundle);
            return;
        }
        if (i == -14) {
            saveDevice(measurableDevice, "");
            setDeviceInfo(measurableDevice, i);
            gotoBluetoothPairSuccessPage(false);
            cancelTimer();
            return;
        }
        LogUtil.h(TAG, " status others");
    }

    private void setDeviceInfo(MeasurableDevice measurableDevice, int i) {
        if (this.mDeviceInfo == null) {
            this.mDeviceInfo = new ContentValues();
        }
        if (i == -9) {
            this.mUniqueId = measurableDevice.getUniqueId();
        }
        this.mDeviceInfo.put("uniqueId", measurableDevice.getUniqueId());
        this.mDeviceInfo.put("productId", this.mProductId);
    }

    private void gotoBluetoothPairSuccessPage(boolean z) {
        LogUtil.a(TAG, "gotoBluetoothPairSuccessPage = ", Boolean.valueOf(z));
        dks.e();
        if (this.mIsJump) {
            return;
        }
        cpl.a(this.mUniqueId);
        cpz.b(this.mProductId);
        this.mIsJump = true;
        if (getContext() != null) {
            Bundle bundle = new Bundle();
            if (z) {
                HagridDeviceBindResultFragment.setBindStatus(9);
            } else {
                HagridDeviceBindResultFragment.setBindStatus(10);
            }
            bundle.putString(BUNDLE_KEY_VIEW, BUNDLE_VALUE_BIND_RESULT_CONFIRM);
            bundle.putBoolean(BUNDLE_KEY_IS_BIND_SUCCESS, true);
            bundle.putString("productId", this.mProductId);
            this.isGotoUserInfoActivity = true;
            bundle.putFloat(BUNDLE_KEY_DEVICE_WEIGHT, this.mWeight);
            bundle.putString("productId", this.mProductId);
            bundle.putBoolean(BUNDLE_KEY_HAS_MANAGER, z);
            bundle.putByteArray(BUNDLE_KEY_MAIN_HUID, this.mMainHuid);
            bundle.putParcelable("commonDeviceInfo", this.mDeviceInfo);
            if (!cpa.ah(this.mProductId) && !cpa.r(this.mProductId)) {
                bundle.putString("deviceSsid", this.mSsid);
                bundle.putString(BUNDLE_KEY_AUTO_DEVICE_SSID, this.mProductId);
                bundle.putString("cloudDeviceId", this.mDeviceIdFromDevice);
            }
            ckm ckmVar = this.mWeightAndFatRateData;
            if (ckmVar != null) {
                bundle.putSerializable(BUNDLE_KEY_RESIS_FAT, ckmVar);
            }
            bundle.putBoolean("isNfcConnect", this.mIsNfcCommect);
            LogUtil.a(TAG, "gotoBluetoothPairSuccessPage putBoolean mIsNfcCommect = ", Boolean.valueOf(this.mIsNfcCommect));
            HagridDeviceBindResultFragment hagridDeviceBindResultFragment = new HagridDeviceBindResultFragment();
            hagridDeviceBindResultFragment.setArguments(bundle);
            switchFragment(this, hagridDeviceBindResultFragment);
        }
    }

    private void processBindType(Bundle bundle) {
        if (bundle == null) {
            LogUtil.h(TAG, "processBindType bundle is null");
            return;
        }
        byte b = bundle.getByte("ret");
        if (b == 2) {
            LogUtil.b(TAG, "binding failed.");
            cancelTimer();
            Handler handler = this.mHandler;
            if (handler != null) {
                handler.sendEmptyMessage(2);
                return;
            }
            return;
        }
        if (b == 1) {
            if (cpa.ae(this.mProductId)) {
                EventBus.d(new EventBus.b("reset_wifi"));
            }
            LogUtil.a(TAG, "processBindType ret = 1 set hasManager = true");
            this.mIsHasManaer = true;
        } else {
            LogUtil.a(TAG, "other ret");
        }
        this.mWeight = bundle.getFloat("weight_data", -1.0f);
        if (bundle.getSerializable("resis_data") instanceof ckm) {
            this.mWeightAndFatRateData = (ckm) bundle.getSerializable("resis_data");
        }
        if (!cpy.a(this.mProductId, this.mWeight)) {
            cancelTimer();
            Handler handler2 = this.mHandler;
            if (handler2 != null) {
                handler2.sendEmptyMessage(2);
                return;
            }
            return;
        }
        cancelTimer();
        startTimer();
        LogUtil.a(TAG, "bind is completed, ret is ", Byte.valueOf(b));
    }

    private void checkAndUpdateLocalDevice(MeasurableDevice measurableDevice, final String str) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.h(TAG, "serialNumber is empty");
            return;
        }
        boolean z = false;
        MeasurableDevice c = ceo.d().c(str, false);
        if (c == null) {
            LogUtil.h(TAG, "device is null , save current device as new one");
            this.mIsOnceSaveDevice = true;
            saveDevice(measurableDevice, str);
            return;
        }
        if (!this.mIsOnceSaveDevice) {
            ReleaseLogUtil.d(RELEASE_TAG, "checkAndUpdateLocalDevice registerDeviceInfo");
            this.mIsOnceSaveDevice = true;
            ceo.d().d(this.mProductId, c, str);
        }
        if (c instanceof ctk) {
            ctk ctkVar = (ctk) c;
            if (!TextUtils.equals(this.mDeviceIdFromDevice, ctkVar.d())) {
                LogUtil.h(TAG, "device rebind, unbind local device");
                new coy().d(ctkVar.d(), new ICloudOperationResult() { // from class: com.huawei.health.device.ui.measure.fragment.device.DeviceBindWaitingFragment$$ExternalSyntheticLambda10
                    @Override // com.huawei.hwcloudmodel.callback.ICloudOperationResult
                    public final void operationResult(Object obj, String str2, boolean z2) {
                        DeviceBindWaitingFragment.lambda$checkAndUpdateLocalDevice$10(str, (CloudCommonReponse) obj, str2, z2);
                    }
                });
                z = true;
            }
        }
        upDateDeviceUniqueId(measurableDevice, z);
    }

    static /* synthetic */ void lambda$checkAndUpdateLocalDevice$10(String str, CloudCommonReponse cloudCommonReponse, String str2, boolean z) {
        LogUtil.a(TAG, "operationResult isSuccess ", Boolean.valueOf(z));
        ctq.b(str);
    }

    private void upDateDeviceUniqueId(MeasurableDevice measurableDevice, boolean z) {
        Handler handler;
        ContentValues contentValues = new ContentValues();
        contentValues.put("uniqueId", measurableDevice.getUniqueId());
        if (z) {
            contentValues.put(Wpt.MODE, (Integer) 1);
        }
        int Eg_ = ceo.d().Eg_(contentValues, "sn=?", new String[]{measurableDevice.getSerialNumber()});
        LogUtil.a(TAG, "upDateDeviceUniqueId affected rows:", Integer.valueOf(Eg_));
        if (Eg_ > 0 || (handler = this.mHandler) == null) {
            return;
        }
        handler.sendEmptyMessage(2);
    }

    private void saveDevice(MeasurableDevice measurableDevice, String str) {
        ceo.d().b(this.mProductId, this.mProductInfo.s(), measurableDevice, new IDeviceEventHandler() { // from class: com.huawei.health.device.ui.measure.fragment.device.DeviceBindWaitingFragment.10
            @Override // com.huawei.health.device.callback.IDeviceEventHandler
            public void onDeviceFound(HealthDevice healthDevice) {
            }

            @Override // com.huawei.health.device.callback.IDeviceEventHandler
            public void onScanFailed(int i) {
            }

            @Override // com.huawei.health.device.callback.IDeviceEventHandler
            public void onStateChanged(int i) {
            }
        }, str);
        LogUtil.a(TAG, "band sendWeightDetailSyncSuccessBroadcast");
        cpa.c(getContext(), this.mProductId, this.mUniqueId);
    }

    private void processBindedSameWiFi(Bundle bundle) {
        if (bundle == null) {
            LogUtil.h(TAG, "processBindedSameWiFi bundle is null");
        } else if (bundle.getString("cloudDeviceID") != null) {
            this.mDeviceIdFromDevice = bundle.getString("cloudDeviceID");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void getProductId() {
        String judgeProductIdByDeviceName = judgeProductIdByDeviceName(this.mTitle);
        if (!TextUtils.isEmpty(judgeProductIdByDeviceName)) {
            this.mProductId = judgeProductIdByDeviceName;
            return;
        }
        if (koq.c(this.mUuidList)) {
            Iterator<String> it = this.mUuidList.iterator();
            while (it.hasNext()) {
                String next = it.next();
                dcz d = ResourceManager.e().d(next);
                if (d != null) {
                    boolean a2 = d.w().a(this.mTitle);
                    LogUtil.h(TAG, "getProductId deviceNameMatched:", Boolean.valueOf(a2));
                    if (a2) {
                        this.mProductId = next;
                        LogUtil.h(TAG, "getProductId productId:", next);
                        stop();
                    }
                } else {
                    LogUtil.h(TAG, "productInfo is null");
                }
            }
        }
    }

    private String judgeProductIdByDeviceName(String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        String str2 = (str.contains(DOBBY_SCALE) || str.contains(HUAWEI_SCALE_3BLE)) ? "c943c933-442e-4c34-bcd0-66597f24aaed" : "";
        if (str.contains(OVERSEA_HAGRID_B29_SCALE)) {
            str2 = "b29df4e3-b1f7-4e40-960d-4cfb63ccca05";
        } else if (str.contains(OVERSEA_HAG_SCALE)) {
            str2 = "8358eb90-b40d-11e9-a2a3-2a2ae2dbcce4";
        } else {
            LogUtil.h(TAG, "other hagr device");
        }
        if (str.contains("CH100")) {
            str2 = "33123f39-7fc1-420b-9882-a4b0d6c61100";
        }
        if (str.contains("CH18")) {
            str2 = "34fa0346-d46c-439d-9cb0-2f696618846b";
        }
        if (str.contains("CH100S")) {
            str2 = "ccd1f0f8-8c57-4bd7-a884-0ef38482f15f";
        }
        return str.contains("AH100") ? "7a1063dd-0e0f-4a72-9939-461476ff0259" : str2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void checkBluetoothEnabled() {
        ReleaseLogUtil.e(RELEASE_TAG, "enter checkBluetoothEnabled");
        if (BluetoothAdapter.getDefaultAdapter() == null || getActivity() == null) {
            LogUtil.b(TAG, "checkBluetoothEnabled BluetoothAdapter or getActivity is null");
            enterToMainActivity();
            return;
        }
        if (BluetoothAdapter.getDefaultAdapter().isEnabled()) {
            startPairingDevice();
            return;
        }
        String string = getActivity().getResources().getString(R.string.IDS_app_name_health);
        String string2 = getActivity().getResources().getString(R.string.IDS_device_bt_open_request_info);
        NoTitleCustomAlertDialog.Builder builder = new NoTitleCustomAlertDialog.Builder(getActivity());
        builder.e(String.format(Locale.ENGLISH, string2, string));
        builder.czC_(R.string.IDS_device_bt_right_btn_info, new View.OnClickListener() { // from class: com.huawei.health.device.ui.measure.fragment.device.DeviceBindWaitingFragment$$ExternalSyntheticLambda4
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                DeviceBindWaitingFragment.this.m261xc900810a(view);
            }
        });
        builder.czz_(R.string.IDS_device_bt_left_btn_info, new View.OnClickListener() { // from class: com.huawei.health.device.ui.measure.fragment.device.DeviceBindWaitingFragment$$ExternalSyntheticLambda5
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                DeviceBindWaitingFragment.this.m262xd029634b(view);
            }
        });
        NoTitleCustomAlertDialog e = builder.e();
        e.setCancelable(false);
        e.show();
    }

    /* renamed from: lambda$checkBluetoothEnabled$11$com-huawei-health-device-ui-measure-fragment-device-DeviceBindWaitingFragment, reason: not valid java name */
    /* synthetic */ void m261xc900810a(View view) {
        LogUtil.a(TAG, "checkBluetoothEnabled enable bluetooth");
        startActivityForResult(new Intent("android.bluetooth.adapter.action.REQUEST_ENABLE"), 1);
        ViewClickInstrumentation.clickOnView(view);
    }

    /* renamed from: lambda$checkBluetoothEnabled$12$com-huawei-health-device-ui-measure-fragment-device-DeviceBindWaitingFragment, reason: not valid java name */
    /* synthetic */ void m262xd029634b(View view) {
        LogUtil.a(TAG, "checkBluetoothEnabled not to enable bluetooth");
        enterToMainActivity();
        ViewClickInstrumentation.clickOnView(view);
    }

    @Override // androidx.fragment.app.Fragment
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        LogUtil.a(TAG, "onActivityResult");
        if (i != 1 || i2 == 0) {
            return;
        }
        startPairingDevice();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void enterToMainActivity() {
        LogUtil.a(TAG, "enterToMainActivity");
        Intent intent = new Intent();
        intent.setPackage(BaseApplication.getAppPackage());
        intent.setClassName(BaseApplication.getAppPackage(), "com.huawei.health.MainActivity");
        intent.setFlags(AppRouterExtras.COLDSTART);
        intent.putExtra("homeTabName", "HOME");
        startActivity(intent);
        FragmentActivity activity = getActivity();
        if (activity != null) {
            LogUtil.a(TAG, "finish");
            activity.finish();
        }
    }

    static class MyTimerTask extends TimerTask {
        private WeakReference<DeviceBindWaitingFragment> mWeakRef;

        MyTimerTask(DeviceBindWaitingFragment deviceBindWaitingFragment) {
            this.mWeakRef = new WeakReference<>(deviceBindWaitingFragment);
        }

        @Override // java.util.TimerTask, java.lang.Runnable
        public void run() {
            DeviceBindWaitingFragment deviceBindWaitingFragment = this.mWeakRef.get();
            if (deviceBindWaitingFragment == null) {
                return;
            }
            LogUtil.a(DeviceBindWaitingFragment.TAG, "connect timeout...");
            deviceBindWaitingFragment.mIsBingTimeout = true;
            if (deviceBindWaitingFragment.mProductInfo != null && deviceBindWaitingFragment.mProductInfo.x().c() == 2) {
                cjx.e().b();
            }
            if (deviceBindWaitingFragment.mBleDeviceHelper != null) {
                cpp.a().unregisterReceiver(deviceBindWaitingFragment.mBleDeviceHelper);
                deviceBindWaitingFragment.mBleDeviceHelper = null;
            }
            cgt.e().b(deviceBindWaitingFragment.mBindCallback);
            deviceBindWaitingFragment.mHandler.sendEmptyMessage(1);
        }
    }

    static class ConnectHandler extends BaseHandler<DeviceBindWaitingFragment> {
        ConnectHandler(DeviceBindWaitingFragment deviceBindWaitingFragment) {
            super(deviceBindWaitingFragment);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.huawei.haf.handler.BaseHandler
        public void handleMessageWhenReferenceNotNull(final DeviceBindWaitingFragment deviceBindWaitingFragment, Message message) {
            int i = message.what;
            if (i != 1) {
                if (i != 2) {
                    if (i != 3) {
                        return;
                    }
                    switchFragment(deviceBindWaitingFragment, 11);
                    return;
                } else {
                    deviceBindWaitingFragment.cancelTimer();
                    LogUtil.c(DeviceBindWaitingFragment.TAG, "DeviceBindWaitingFragment bind fail...");
                    setConnectFail(deviceBindWaitingFragment, message);
                    return;
                }
            }
            deviceBindWaitingFragment.cancelTimer();
            LogUtil.a(DeviceBindWaitingFragment.TAG, "DeviceBindWaitingFragment connect timeout...");
            NoTitleCustomAlertDialog.Builder builder = new NoTitleCustomAlertDialog.Builder(deviceBindWaitingFragment.mainActivity);
            if (dks.g(deviceBindWaitingFragment.mProductId) || dks.k(deviceBindWaitingFragment.mProductId)) {
                builder.e(deviceBindWaitingFragment.mainActivity.getApplication().getString(R.string.IDS_device_rope_skipping_pair_timeout));
            } else {
                builder.e(deviceBindWaitingFragment.mainActivity.getApplication().getString(R.string.IDS_device_scale_pair_timeout, new Object[]{String.valueOf(3)}));
            }
            builder.czC_(R.string.IDS_device_permisson, new View.OnClickListener() { // from class: com.huawei.health.device.ui.measure.fragment.device.DeviceBindWaitingFragment$ConnectHandler$$ExternalSyntheticLambda0
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    DeviceBindWaitingFragment.ConnectHandler.lambda$handleMessageWhenReferenceNotNull$0(DeviceBindWaitingFragment.this, view);
                }
            });
            deviceBindWaitingFragment.mDialog = builder.e();
            deviceBindWaitingFragment.mDialog.setCancelable(false);
            deviceBindWaitingFragment.mDialog.show();
        }

        static /* synthetic */ void lambda$handleMessageWhenReferenceNotNull$0(DeviceBindWaitingFragment deviceBindWaitingFragment, View view) {
            deviceBindWaitingFragment.mDialog.dismiss();
            cjx.e().e(deviceBindWaitingFragment.mProductId, deviceBindWaitingFragment.mUniqueId, -6);
            cjx.e().o(deviceBindWaitingFragment.mUniqueId);
            deviceBindWaitingFragment.popupFragment(ProductIntroductionFragment.class);
            ViewClickInstrumentation.clickOnView(view);
        }

        private void setConnectFail(DeviceBindWaitingFragment deviceBindWaitingFragment, Message message) {
            deviceBindWaitingFragment.cancelTimer();
            LogUtil.h(DeviceBindWaitingFragment.TAG, "DeviceBindWaitingFragment connect fail...");
            switchFragment(deviceBindWaitingFragment, message.obj != null ? ((Integer) message.obj).intValue() : 3);
        }

        private void switchFragment(DeviceBindWaitingFragment deviceBindWaitingFragment, int i) {
            HagridDeviceBindResultFragment.setBindStatus(i);
            Bundle bundle = new Bundle();
            bundle.putString("productId", deviceBindWaitingFragment.mProductId);
            ContentValues contentValues = new ContentValues();
            contentValues.put("uniqueId", deviceBindWaitingFragment.mUniqueId);
            contentValues.put("productId", deviceBindWaitingFragment.mProductId);
            bundle.putParcelable("commonDeviceInfo", contentValues);
            HagridDeviceBindResultFragment hagridDeviceBindResultFragment = new HagridDeviceBindResultFragment();
            hagridDeviceBindResultFragment.setArguments(bundle);
            deviceBindWaitingFragment.switchFragment(hagridDeviceBindResultFragment);
            deviceBindWaitingFragment.stop();
        }
    }
}
