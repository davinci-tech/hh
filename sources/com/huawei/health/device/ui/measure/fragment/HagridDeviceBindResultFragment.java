package com.huawei.health.device.ui.measure.fragment;

import android.content.ActivityNotFoundException;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.drawable.AnimationDrawable;
import android.location.LocationManager;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.huawei.health.R;
import com.huawei.health.device.callback.IDeviceEventHandler;
import com.huawei.health.device.callback.ScaleDialogCallback;
import com.huawei.health.device.connectivity.comm.MeasurableDevice;
import com.huawei.health.device.manager.DeviceCloudSharePreferencesManager;
import com.huawei.health.device.model.HealthDevice;
import com.huawei.health.device.ui.DeviceMainActivity;
import com.huawei.health.device.ui.util.DeviceManagerInfoHandler;
import com.huawei.health.device.util.EventBus;
import com.huawei.health.device.wifi.interfaces.BaseCallbackInterface;
import com.huawei.health.device.wifi.lib.handler.StaticHandler;
import com.huawei.health.device.wifi.manager.DeviceRegisterManager;
import com.huawei.health.ecologydevice.manager.ResourceManager;
import com.huawei.health.ecologydevice.ui.BaseFragment;
import com.huawei.health.ecologydevice.ui.measure.fragment.ProductIntroductionFragment;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcloudmodel.callback.ICloudOperationResult;
import com.huawei.hwcloudmodel.model.CloudCommonReponse;
import com.huawei.hwcloudmodel.model.unite.RegisterInfo;
import com.huawei.hwcloudmodel.model.unite.WifiDeviceGetDeviceRegistration;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.openalliance.ad.constant.ParamConstants;
import com.huawei.operation.utils.Constants;
import com.huawei.ui.commonui.R$string;
import com.huawei.ui.commonui.button.HealthButton;
import com.huawei.ui.commonui.checkbox.HealthCheckBox;
import com.huawei.ui.commonui.dialog.CommonDialog21;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.titlebar.CustomTitleBar;
import com.huawei.up.api.UpApi;
import defpackage.ceo;
import defpackage.cgt;
import defpackage.cji;
import defpackage.cjx;
import defpackage.ckm;
import defpackage.cld;
import defpackage.coy;
import defpackage.cpa;
import defpackage.cpn;
import defpackage.cpp;
import defpackage.cpw;
import defpackage.cpz;
import defpackage.cqa;
import defpackage.cqh;
import defpackage.csf;
import defpackage.cst;
import defpackage.csw;
import defpackage.ctk;
import defpackage.ctn;
import defpackage.ctq;
import defpackage.ctv;
import defpackage.cub;
import defpackage.cug;
import defpackage.dcz;
import defpackage.dif;
import defpackage.dja;
import defpackage.gmz;
import defpackage.jbs;
import defpackage.knx;
import defpackage.nrh;
import defpackage.nsn;
import defpackage.nsy;
import health.compact.a.CommonUtil;
import health.compact.a.LanguageUtil;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;
import health.compact.a.UnitUtil;
import health.compact.a.Utils;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

/* loaded from: classes3.dex */
public class HagridDeviceBindResultFragment extends BaseFragment implements View.OnClickListener {
    private static final int CLOUD_DEVICE_NOT_FIND_CODE = 112000050;
    private static final String TAG = "HagridDeviceBindResultFragment";
    private static final String TAG_RELEASE = "R_Weight_HagridDeviceBindResultFragment";
    private static int mBindStatus;
    private String deviceAddress;
    private AnimationDrawable mAnimation;
    private ImageView mAnimationView;
    private LinearLayout mAutoUpgradeLayout;
    private HealthCheckBox mAutoUpgradeSwitch;
    private ImageView mBackgroundImg;
    private LinearLayout mBindCancel;
    private HealthButton mBindCancelButton;
    private HealthTextView mBindCancelText;
    private LinearLayout mBindDeviceProgressLayout;
    private HealthButton mBindFinishButton;
    private HealthTextView mBindGoText;
    private LinearLayout mBindGuideBottom;
    private LinearLayout mBindGuideLayout;
    private LinearLayout mBindNextBottom;
    private HealthTextView mBindPercentSign;
    private HealthTextView mBindProgressText;
    private LinearLayout mBindResultDeviceLayout;
    private HealthButton mBindSuccessButton;
    private LinearLayout mBindSuccessLayout;
    private LinearLayout mBindSuccessLayoutBottom;
    private HealthTextView mBindSuccessText;
    private HealthTextView mBindSucessDes;
    private HealthTextView mBindWifiGuideDes;
    private ImageView mCancelImage;
    private Timer mCheckTimer;
    private String mCloudDeviceId;
    private ContentValues mContentValues;
    private Context mContext;
    private String mDeviceId;
    private String mDeviceIdentify;
    private DeviceRegisterManager mDeviceManager;
    private DeviceManagerInfoHandler mDeviceManagerInfoHandler;
    private String mDeviceSsid;
    private ImageView mGuideImg;
    private FrameLayout mGuideSuccessImg;
    private gmz mInteractors;
    private boolean mIsNfcConnect;
    private CommonDialog21 mLoadingDialog;
    private MyHandler mMyHandler;
    private ImageView mNextImage;
    private HealthTextView mPairingWifiNameText;
    private String mProductId;
    private dcz mProductInfo;
    private LinearLayout mReChooseLayout;
    private ctk mRegisterDevice;
    private String mSsid;
    private String mSsidPwd;
    private long mStartBindTime;
    private ctk mWifiDevice;
    private boolean mIsModifyWlanInfo = false;
    private boolean mIsHealthDataStatus = false;
    private boolean mIsPersonalInfoStatus = false;
    private String mAccountInfo = "";
    private boolean mIsBinding = false;
    private boolean mIsCreateSave = false;
    private boolean mIsJumpFromWifiInfoConfirmFragment = false;
    private coy mFragmentHelper = new coy();
    private BaseCallbackInterface mRegisterCallback = new BaseCallbackInterface() { // from class: com.huawei.health.device.ui.measure.fragment.HagridDeviceBindResultFragment.1
        @Override // com.huawei.health.device.wifi.interfaces.BaseCallbackInterface
        public void onSuccess(Object obj) {
            if (obj != null) {
                if (obj instanceof Bundle) {
                    LogUtil.a(HagridDeviceBindResultFragment.TAG, "onSuccess startEstimatingBindProgress...");
                    if (cji.a(HagridDeviceBindResultFragment.this.mDeviceIdentify, 24)) {
                        HagridDeviceBindResultFragment.this.sendConfigInfoForNew((Bundle) obj);
                        return;
                    } else {
                        HagridDeviceBindResultFragment.this.getVerifyCodeSuccess((Bundle) obj);
                        return;
                    }
                }
                if (obj instanceof ctk) {
                    LogUtil.a(HagridDeviceBindResultFragment.TAG, "onSuccess sendSetManagerInfo");
                    HagridDeviceBindResultFragment.this.mRegisterDevice = (ctk) obj;
                    HagridDeviceBindResultFragment.this.sendSetManagerInfo();
                    return;
                }
                LogUtil.h(HagridDeviceBindResultFragment.TAG_RELEASE, "onSuccess else");
            }
        }

        @Override // com.huawei.health.device.wifi.interfaces.BaseCallbackInterface
        public void onFailure(int i) {
            HagridDeviceBindResultFragment.this.mMainHandler.removeMessages(1006);
            if (i == 3103) {
                HagridDeviceBindResultFragment.this.mMyHandler.sendEmptyMessage(1004);
            } else if (i == 3101) {
                HagridDeviceBindResultFragment.this.mMyHandler.sendEmptyMessage(1005);
            } else {
                LogUtil.h(HagridDeviceBindResultFragment.TAG_RELEASE, "onFailure else");
            }
        }

        @Override // com.huawei.health.device.wifi.interfaces.BaseCallbackInterface
        public void onStatus(int i) {
            LogUtil.a(HagridDeviceBindResultFragment.TAG, "update status: ", Integer.valueOf(i));
            if (i == 0) {
                HagridDeviceBindResultFragment.this.mMyHandler.sendEmptyMessageDelayed(1003, 2000L);
            }
        }
    };
    private EventBus.ICallback mEventCallback = new EventBus.ICallback() { // from class: com.huawei.health.device.ui.measure.fragment.HagridDeviceBindResultFragment$$ExternalSyntheticLambda5
        @Override // com.huawei.health.device.util.EventBus.ICallback
        public final void onEvent(EventBus.b bVar) {
            HagridDeviceBindResultFragment.this.m152x65e72cff(bVar);
        }
    };
    private Handler mMainHandler = new Handler(Looper.getMainLooper()) { // from class: com.huawei.health.device.ui.measure.fragment.HagridDeviceBindResultFragment.2
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            if (message.what == 1006) {
                HagridDeviceBindResultFragment.this.updateEstimatingBindProgress(message.obj instanceof Boolean ? ((Boolean) message.obj).booleanValue() : false);
            } else {
                LogUtil.h(HagridDeviceBindResultFragment.TAG, "mMainHandler other status");
            }
        }
    };

    /* renamed from: lambda$new$0$com-huawei-health-device-ui-measure-fragment-HagridDeviceBindResultFragment, reason: not valid java name */
    /* synthetic */ void m152x65e72cff(EventBus.b bVar) {
        if (bVar == null) {
            LogUtil.h(TAG_RELEASE, "event is null");
            return;
        }
        Bundle Kl_ = bVar.Kl_();
        if (Kl_ == null) {
            LogUtil.h(TAG_RELEASE, "bundle is null");
            return;
        }
        LogUtil.a(TAG, "event start: ", bVar.e());
        if ("send_wifi_password_result".equals(bVar.e())) {
            onReceiveWifiPasswordResult(Kl_);
            return;
        }
        if ("update_wifi_device_cloud_status".equals(bVar.e())) {
            onReceiveWifiDeviceCloudStatus(Kl_);
        } else if ("set_manager_info_result".equals(bVar.e())) {
            onReceiveSetManagerInfoResult(Kl_);
        } else {
            LogUtil.h(TAG_RELEASE, "event is else");
        }
    }

    private void onReceiveWifiPasswordResult(Bundle bundle) {
        int i = bundle.getInt("result");
        LogUtil.a(TAG, "send wifi password result: ", Integer.valueOf(i));
        if (i == 0) {
            cancelTimer();
            startTimer();
        } else {
            this.mMyHandler.sendEmptyMessage(1004);
        }
    }

    private void onReceiveWifiDeviceCloudStatus(Bundle bundle) {
        int i = bundle.getInt("status");
        LogUtil.a(TAG, "update wifi password cloud status: ", Integer.valueOf(i));
        if (i != 2 || !isManagerUser()) {
            if (i == 3) {
                this.mMyHandler.removeMessages(1003);
                cancelTimer();
                this.mMyHandler.sendEmptyMessage(1004);
                return;
            }
            LogUtil.h(TAG, "update wifi is not SUCCESS or FAILED");
            return;
        }
        this.mMyHandler.removeMessages(1004);
        cancelTimer();
        Message obtain = Message.obtain();
        obtain.what = 1006;
        obtain.obj = true;
        this.mMainHandler.sendMessage(obtain);
        this.mMyHandler.sendEmptyMessageDelayed(1003, 2000L);
    }

    private void onReceiveSetManagerInfoResult(Bundle bundle) {
        if (bundle != null) {
            int i = bundle.getInt("ret");
            this.mMyHandler.removeMessages(1007);
            if (i == 0) {
                registerDeviceSuccess(this.mRegisterDevice);
            } else {
                this.mMyHandler.sendEmptyMessage(1007);
            }
        }
    }

    static class MyHandler extends StaticHandler<HagridDeviceBindResultFragment> {
        MyHandler(HagridDeviceBindResultFragment hagridDeviceBindResultFragment) {
            super(hagridDeviceBindResultFragment);
        }

        @Override // com.huawei.health.device.wifi.lib.handler.StaticHandler
        public void handleMessage(HagridDeviceBindResultFragment hagridDeviceBindResultFragment, Message message) {
            LogUtil.a(HagridDeviceBindResultFragment.TAG, "HagridDeviceBindResultFragment MyHandler what: ", Integer.valueOf(message.what));
            if (hagridDeviceBindResultFragment.isDestory()) {
                return;
            }
            LogUtil.a(HagridDeviceBindResultFragment.TAG, "HagridDeviceBindResultFragment MyHandler what: ", Integer.valueOf(message.what));
            int i = message.what;
            if (i == 1000) {
                hagridDeviceBindResultFragment.gotoDeviceManagerPage();
            }
            if (i == 1007) {
                int unused = HagridDeviceBindResultFragment.mBindStatus = 6;
                hagridDeviceBindResultFragment.updateStatus(6);
                if (hagridDeviceBindResultFragment.mRegisterDevice != null) {
                    hagridDeviceBindResultFragment.mRegisterDevice.b().d(2);
                }
                hagridDeviceBindResultFragment.bindUpdateDevice();
                return;
            }
            switch (i) {
                case 1003:
                    removeMessages(1007);
                    int unused2 = HagridDeviceBindResultFragment.mBindStatus = 5;
                    hagridDeviceBindResultFragment.updateStatus(5);
                    break;
                case 1004:
                    removeMessages(1007);
                    int unused3 = HagridDeviceBindResultFragment.mBindStatus = 6;
                    hagridDeviceBindResultFragment.updateStatus(6);
                    break;
                case 1005:
                    removeMessages(1007);
                    int unused4 = HagridDeviceBindResultFragment.mBindStatus = 6;
                    hagridDeviceBindResultFragment.updateStatus(6);
                    break;
                default:
                    LogUtil.h(HagridDeviceBindResultFragment.TAG, "HagridDeviceBindResultFragment MyHandler what else");
                    break;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void bindUpdateDevice() {
        if (this.mRegisterDevice == null) {
            LogUtil.h(TAG, "enter bindUpdateDevice, mRegisterDevice is null");
        } else {
            LogUtil.a(TAG, "enter bindUpdateDevice");
            ceo.d().b(this.mProductId, this.mProductInfo.s(), this.mRegisterDevice, new IDeviceEventHandler() { // from class: com.huawei.health.device.ui.measure.fragment.HagridDeviceBindResultFragment.3
                @Override // com.huawei.health.device.callback.IDeviceEventHandler
                public void onDeviceFound(HealthDevice healthDevice) {
                    LogUtil.a(HagridDeviceBindResultFragment.TAG, "bindUpdateDevice onDeviceFound");
                }

                @Override // com.huawei.health.device.callback.IDeviceEventHandler
                public void onScanFailed(int i) {
                    LogUtil.a(HagridDeviceBindResultFragment.TAG, "bindUpdateDevice onScanFailed: ", Integer.valueOf(i));
                }

                @Override // com.huawei.health.device.callback.IDeviceEventHandler
                public void onStateChanged(int i) {
                    LogUtil.a(HagridDeviceBindResultFragment.TAG, "bindUpdateDevice success");
                    HagridDeviceBindResultFragment.this.unbindWifiDevice();
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void unbindWifiDevice() {
        ctk c;
        ctk ctkVar = this.mRegisterDevice;
        if (ctkVar == null) {
            LogUtil.h(TAG, "enter unbindWifiDevice, mRegisterDevice is null");
            return;
        }
        String d = ctkVar.d();
        if (d != null && (c = ctq.c(d)) != null) {
            c.doRemoveBond();
        }
        LogUtil.a(TAG, "enter unbindWifiDevice");
        new coy().d(this.mRegisterDevice.d(), new ICloudOperationResult() { // from class: com.huawei.health.device.ui.measure.fragment.HagridDeviceBindResultFragment$$ExternalSyntheticLambda9
            @Override // com.huawei.hwcloudmodel.callback.ICloudOperationResult
            public final void operationResult(Object obj, String str, boolean z) {
                LogUtil.a(HagridDeviceBindResultFragment.TAG, "operationResult isSuccess ", Boolean.valueOf(z));
            }
        });
    }

    public static void setBindStatus(int i) {
        mBindStatus = i;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isDestory() {
        FragmentActivity activity = getActivity();
        if (activity == null || activity.isFinishing() || activity.isDestroyed()) {
            LogUtil.h(TAG_RELEASE, "DeviceMainActivity is Destroyed");
            return true;
        }
        if (isAdded()) {
            return false;
        }
        LogUtil.h(TAG_RELEASE, "MyHandler mFragment is not add");
        return false;
    }

    @Override // com.huawei.health.ecologydevice.ui.BaseFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        LogUtil.a(TAG, "onCreate");
        this.mMyHandler = new MyHandler(this);
        this.mInteractors = gmz.d();
        this.mDeviceManager = DeviceRegisterManager.a(BaseApplication.getContext());
    }

    @Override // com.huawei.health.ecologydevice.ui.BaseFragment, androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        LogUtil.a(TAG, "onCreateView");
        ViewGroup viewGroup2 = super.onCreateView(layoutInflater, viewGroup, bundle) instanceof ViewGroup ? (ViewGroup) super.onCreateView(layoutInflater, viewGroup, bundle) : null;
        this.child = layoutInflater.inflate(R.layout.hygride_device_wifi_bind_result_layout, viewGroup, false);
        if (viewGroup2 != null) {
            viewGroup2.removeView(this.mCustomTitleBar);
            viewGroup2.addView(this.child);
        }
        initView();
        initData();
        initListener();
        updateStatus(mBindStatus);
        return viewGroup2;
    }

    @Override // androidx.fragment.app.Fragment
    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        FragmentActivity activity = getActivity();
        this.mContext = activity;
        if (activity == null) {
            LogUtil.h(TAG_RELEASE, "onActivityCreated mContext== null");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendConfigInfoForNew(Bundle bundle) {
        if (bundle == null) {
            LogUtil.h(TAG_RELEASE, "onActivityCreated varBundle == null");
            return;
        }
        RegisterInfo registerInfo = (RegisterInfo) bundle.getParcelable("device_register_verifycode");
        if (registerInfo == null) {
            LogUtil.h(TAG_RELEASE, "onActivityCreated entity is null");
            return;
        }
        this.mDeviceId = registerInfo.getDevId();
        HashMap hashMap = new HashMap(16);
        hashMap.put("ssid", this.mSsid);
        hashMap.put("password", this.mSsidPwd);
        hashMap.put("devId", registerInfo.getDevId());
        hashMap.put("psk", registerInfo.getPsk());
        hashMap.put("code", registerInfo.getVerifyCode());
        String e = cpn.e(true);
        String e2 = cpn.e(false);
        String c = cpn.c();
        hashMap.put("cloudPrimaryUrl", e);
        hashMap.put("cloudStandbyUrl", e2);
        hashMap.put("cloudUrl", c);
        LogUtil.a(TAG, "sendConfigInfo ssid=", cpw.d(this.mSsid), ", ssidPwd=", cpw.d(this.mSsidPwd), ", cloudPrimaryUrl=", e, ", cloudStandbyUrl=", e2, ", cloudUrl=", c);
        String json = new Gson().toJson(hashMap);
        Bundle bundle2 = new Bundle();
        bundle2.putString("configjson", json);
        EventBus.d(new EventBus.b("send_config_info_hag_2021", bundle2));
        startEstimatingBindProgress();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void getVerifyCodeSuccess(Bundle bundle) {
        if (bundle == null) {
            LogUtil.h(TAG_RELEASE, "onActivityCreated varBundle == null");
            return;
        }
        RegisterInfo registerInfo = (RegisterInfo) bundle.getParcelable("device_register_verifycode");
        if (registerInfo == null) {
            LogUtil.h(TAG_RELEASE, "onActivityCreated entity is null");
            return;
        }
        csw cswVar = new csw();
        cswVar.a(registerInfo.getDevId());
        cswVar.b(registerInfo.getVerifyCode());
        cswVar.d(cpn.c());
        cswVar.e(registerInfo.getPsk());
        this.mDeviceId = cswVar.e();
        Bundle bundle2 = new Bundle();
        if (TextUtils.isEmpty(this.mSsid)) {
            this.mSsid = getWlanInfoByKey("outhName");
        }
        if (TextUtils.isEmpty(this.mSsidPwd)) {
            this.mSsidPwd = getWlanInfoByKey("outhPd");
        }
        bundle2.putString("ssid", this.mSsid);
        bundle2.putString("pwd", this.mSsidPwd);
        bundle2.putInt("encryptMode", 100);
        bundle2.putString("deviceSsid", this.mDeviceSsid);
        bundle2.putString("registerMsg", new Gson().toJson(cswVar, csw.class));
        LogUtil.a(TAG, "getVerifyCodeSuccess publish EVEBUS_SEND_CONFIG_INFO");
        EventBus.d(new EventBus.b("send_config_info", bundle2));
        startEstimatingBindProgress();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendSetManagerInfo() {
        Bundle bundle = new Bundle();
        bundle.putString("huid", LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1011));
        bundle.putString("deviceId", this.mDeviceId);
        UpApi upApi = new UpApi(this.mContext);
        if (cpa.w(this.mProductId)) {
            bundle.putString("accountInfo", upApi.getCurrentUserAccount());
        }
        LogUtil.a(TAG, "sendSetManagerInfo publish EVEBUS_SET_MANAGER_INFO");
        EventBus.d(new EventBus.b("set_manager_info", bundle));
        this.mRegisterDevice.setProductId(this.mProductId);
        MeasurableDevice d = ceo.d().d(this.mDeviceIdentify, true);
        if (d == null) {
            LogUtil.h(TAG_RELEASE, "healthBondedDevice null.");
            this.mMyHandler.sendEmptyMessage(1004);
            return;
        }
        String uniqueId = d.getUniqueId();
        this.deviceAddress = uniqueId;
        this.mRegisterDevice.a(uniqueId);
        this.mRegisterDevice.setKind(HealthDevice.HealthDeviceKind.HDK_WEIGHT);
        LogUtil.a(TAG, "sendSetManagerInfo setSource CURRENT_USER_DEVICE");
        this.mRegisterDevice.b().d(1);
    }

    private void registerDeviceSuccess(ctk ctkVar) {
        LogUtil.a(TAG, "registerDeviceSuccess enter");
        if (ctkVar == null) {
            LogUtil.h(TAG, "registerDeviceSuccess wifiDevice is null");
            return;
        }
        Message obtain = Message.obtain();
        obtain.what = 1006;
        obtain.obj = true;
        this.mMainHandler.sendMessage(obtain);
        updateWifiDevice(ctkVar);
    }

    private void updateWifiDevice(ctk ctkVar) {
        ceo.d().b(this.mProductId, this.mProductInfo.s(), ctkVar, new IDeviceEventHandler() { // from class: com.huawei.health.device.ui.measure.fragment.HagridDeviceBindResultFragment.4
            @Override // com.huawei.health.device.callback.IDeviceEventHandler
            public void onDeviceFound(HealthDevice healthDevice) {
                LogUtil.a(HagridDeviceBindResultFragment.TAG, "updateWifiDevice onDeviceFound");
            }

            @Override // com.huawei.health.device.callback.IDeviceEventHandler
            public void onScanFailed(int i) {
                LogUtil.a(HagridDeviceBindResultFragment.TAG, "updateWifiDevice onScanFailed code: ", Integer.valueOf(i));
            }

            @Override // com.huawei.health.device.callback.IDeviceEventHandler
            public void onStateChanged(int i) {
                LogUtil.a(HagridDeviceBindResultFragment.TAG, "bindDevice onStateChanged code: ", Integer.valueOf(i));
                if (i == 7) {
                    HagridDeviceBindResultFragment.this.mMyHandler.sendEmptyMessageDelayed(1003, 2000L);
                } else if (i == 8) {
                    HagridDeviceBindResultFragment.this.mMyHandler.sendEmptyMessage(1004);
                } else {
                    LogUtil.h(HagridDeviceBindResultFragment.TAG_RELEASE, "bindDevice onStateChanged not passed or failed");
                }
            }
        });
    }

    private void initView() {
        this.mGuideImg = (ImageView) nsy.cMd_(this.child, R.id.pair_result_guide_img);
        this.mGuideSuccessImg = (FrameLayout) nsy.cMd_(this.child, R.id.pair_result_device_progress_img);
        this.mBindGuideLayout = (LinearLayout) nsy.cMd_(this.child, R.id.id_device_bind_guide_layout);
        this.mBindWifiGuideDes = (HealthTextView) nsy.cMd_(this.child, R.id.id_device_bind_guide_des);
        this.mBindGuideBottom = (LinearLayout) nsy.cMd_(this.child, R.id.id_device_bind_guide_bottom);
        this.mBindSuccessLayout = (LinearLayout) nsy.cMd_(this.child, R.id.id_device_bind_success_layout);
        this.mBindSuccessText = (HealthTextView) nsy.cMd_(this.child, R.id.id_device_bind_success_txt);
        this.mBindProgressText = (HealthTextView) nsy.cMd_(this.child, R.id.id_device_bind_progress);
        this.mBindSucessDes = (HealthTextView) nsy.cMd_(this.child, R.id.id_device_bind_success_des);
        this.mBindPercentSign = (HealthTextView) nsy.cMd_(this.child, R.id.id_device_bind_percent_sign);
        this.mBindSuccessLayoutBottom = (LinearLayout) nsy.cMd_(this.child, R.id.id_device_bind_success_ll);
        this.mBindDeviceProgressLayout = (LinearLayout) nsy.cMd_(this.child, R.id.id_device_bind_progress_ll);
        this.mBindSuccessButton = (HealthButton) nsy.cMd_(this.child, R.id.id_device_bind_success_button);
        this.mBindFinishButton = (HealthButton) nsy.cMd_(this.child, R.id.id_device_bind_finish_button);
        this.mBindCancelButton = (HealthButton) nsy.cMd_(this.child, R.id.id_device_not_config_wifi_interim_button);
        this.mBindNextBottom = (LinearLayout) nsy.cMd_(this.child, R.id.id_btn_go_next_ll);
        this.mBindGoText = (HealthTextView) nsy.cMd_(this.child, R.id.wifi_device_bind_next_text);
        this.mBindCancel = (LinearLayout) nsy.cMd_(this.child, R.id.id_btn_bind_cancel_ll);
        this.mBindCancelText = (HealthTextView) nsy.cMd_(this.child, R.id.wifi_device_bind_fail_next_text);
        this.mNextImage = (ImageView) nsy.cMd_(this.child, R.id.wifi_device_bind_retry_img);
        this.mCancelImage = (ImageView) nsy.cMd_(this.child, R.id.wifi_binde_device_next_img);
        ImageView imageView = (ImageView) nsy.cMd_(this.child, R.id.device_pair_guide_progress_anim);
        this.mAnimationView = imageView;
        if (imageView.getDrawable() instanceof AnimationDrawable) {
            this.mAnimation = (AnimationDrawable) this.mAnimationView.getDrawable();
        } else {
            LogUtil.h(TAG, "mAnimationView.getDrawable() not instanceof AnimationDrawable");
        }
        this.mBackgroundImg = (ImageView) nsy.cMd_(this.child, R.id.pair_result_device_img);
        this.mBindResultDeviceLayout = (LinearLayout) nsy.cMd_(this.child, R.id.pair_result_device_img_layout);
        this.mCustomTitleBar = (CustomTitleBar) this.child.findViewById(R.id.device_pair_guidance_title);
        this.mCustomTitleBar.setLeftButtonVisibility(8);
        this.mAutoUpgradeLayout = (LinearLayout) nsy.cMd_(this.child, R.id.id_device_auto_upgrade_switch_layout);
        this.mAutoUpgradeSwitch = (HealthCheckBox) nsy.cMd_(this.child, R.id.id_device_auto_upgrade_switch);
        this.mPairingWifiNameText = (HealthTextView) nsy.cMd_(this.child, R.id.IDS_device_wifi_binding_wifi_name_tv);
        this.mReChooseLayout = (LinearLayout) this.child.findViewById(R.id.id_device_bind_rechoose_layout);
        this.mAutoUpgradeLayout.setVisibility(8);
        showCustomBarTitle(mBindStatus);
        if (LanguageUtil.bc(BaseApplication.getContext())) {
            this.mNextImage.setBackgroundResource(R.drawable.wifi_device_arrow_left);
            this.mCancelImage.setBackgroundResource(R.drawable.wifi_device_arrow_right);
        }
    }

    private void initData() {
        if (getArguments() != null) {
            boolean z = getArguments().getBoolean("isNfcConnect", false);
            this.mIsNfcConnect = z;
            LogUtil.a(TAG, "initData getBoolean mIsNfcConnect: ", Boolean.valueOf(z));
            ContentValues contentValues = (ContentValues) getArguments().getParcelable("commonDeviceInfo");
            this.mContentValues = contentValues;
            LogUtil.c(TAG, "HagridDeviceBindResultFragment initData deviceInfo: ", contentValues);
            ContentValues contentValues2 = this.mContentValues;
            if (contentValues2 != null) {
                this.mProductId = contentValues2.getAsString("productId");
                String asString = this.mContentValues.getAsString("uniqueId");
                this.mDeviceIdentify = asString;
                if (TextUtils.isEmpty(asString) || TextUtils.isEmpty(this.mProductId)) {
                    LogUtil.h(TAG, "initData : deviceIdentify or mProductId is empty");
                }
            }
            initBasicInfo();
        } else {
            LogUtil.h(TAG, "getArguments() is null");
        }
        this.mProductInfo = ResourceManager.e().d(this.mProductId);
        MeasurableDevice d = ceo.d().d(this.mDeviceIdentify, false);
        if (d == null) {
            LogUtil.h(TAG, "initData device is null");
        }
        if (d instanceof ctk) {
            ctk ctkVar = (ctk) d;
            this.mWifiDevice = ctkVar;
            LogUtil.a(TAG, "initData mWifiDevice: ", CommonUtil.l(ctkVar.toString()));
        }
    }

    private void initBasicInfo() {
        if (TextUtils.isEmpty(this.mDeviceId)) {
            String wlanInfoByKey = getWlanInfoByKey("mDeviceId");
            this.mDeviceId = wlanInfoByKey;
            if (!TextUtils.isEmpty(wlanInfoByKey)) {
                this.mIsCreateSave = !this.mIsJumpFromWifiInfoConfirmFragment;
                setWlanInfoByKey("mDeviceId", "");
            }
            LogUtil.a(TAG, "initData : mDeviceId: ", CommonUtil.l(this.mDeviceId), ", isJumpFromWifiInfoConfirmFragment: ", Boolean.valueOf(this.mIsJumpFromWifiInfoConfirmFragment));
        }
        String string = getArguments().getString("outhName");
        this.mSsid = string;
        if (TextUtils.isEmpty(string)) {
            this.mSsid = getWlanInfoByKey("outhName");
        }
        String string2 = getArguments().getString("outhPd");
        this.mSsidPwd = string2;
        if (TextUtils.isEmpty(string2)) {
            this.mSsidPwd = getWlanInfoByKey("outhPd");
        }
        String string3 = getArguments().getString("deviceSsid");
        this.mDeviceSsid = string3;
        if (TextUtils.isEmpty(string3)) {
            this.mDeviceSsid = getWlanInfoByKey("deviceSsid");
        }
        String string4 = getArguments().getString("cloudDeviceId");
        this.mCloudDeviceId = string4;
        if (TextUtils.isEmpty(string4)) {
            this.mCloudDeviceId = getWlanInfoByKey("cloudDeviceId");
        }
        if ((cpa.ah(this.mProductId) || cpa.r(this.mProductId)) && mBindStatus == 8) {
            mBindStatus = 10;
        }
        LogUtil.a(TAG, "initData mSsid: ", CommonUtil.l(this.mSsid), " mSsidPwd: ", CommonUtil.l(this.mSsidPwd), " mDeviceSsid: ", CommonUtil.l(this.mDeviceSsid), " mCloudDeviceId: ", CommonUtil.l(this.mCloudDeviceId), " mBindStatus: ", Integer.valueOf(mBindStatus), " mDeviceIdentify: ", CommonUtil.l(this.mDeviceIdentify), " mProductId: ", CommonUtil.l(this.mProductId));
    }

    private void saveWlanInfo() {
        LogUtil.a(TAG, "enter saveWlanInfo");
        DeviceCloudSharePreferencesManager deviceCloudSharePreferencesManager = new DeviceCloudSharePreferencesManager(cpp.a());
        ContentValues contentValues = (ContentValues) getArguments().getParcelable("commonDeviceInfo");
        if (contentValues != null) {
            deviceCloudSharePreferencesManager.d("productId", contentValues.getAsString("productId"));
            deviceCloudSharePreferencesManager.d("uniqueId", contentValues.getAsString("uniqueId"));
        }
        LogUtil.a(TAG, "saveWlanInfo mDeviceSsid = ", this.mDeviceSsid);
        LogUtil.a(TAG, "saveWlanInfo getArguments().getString(HonourDeviceConstants.DEVICE_SSID) = ", getArguments().getString("deviceSsid"));
        deviceCloudSharePreferencesManager.d("outhName", getArguments().getString("outhName"));
        deviceCloudSharePreferencesManager.d("outhPd", getArguments().getString("outhPd"));
        deviceCloudSharePreferencesManager.d("deviceSsid", getArguments().getString("deviceSsid"));
        deviceCloudSharePreferencesManager.d("cloudDeviceId", getArguments().getString("cloudDeviceId"));
        deviceCloudSharePreferencesManager.d("mDeviceId", this.mDeviceId);
    }

    private String getWlanInfoByKey(String str) {
        DeviceCloudSharePreferencesManager deviceCloudSharePreferencesManager = new DeviceCloudSharePreferencesManager(cpp.a());
        LogUtil.a(TAG, "enter getWlanInfoByKey spFieldName: ", str, " spFieldValue: ", deviceCloudSharePreferencesManager.c(str));
        return deviceCloudSharePreferencesManager.c(str);
    }

    private void setWlanInfoByKey(String str, String str2) {
        LogUtil.a(TAG, "enter setWlanInfoByKey spFieldName is", str, " spFieldValue: ", str2);
        new DeviceCloudSharePreferencesManager(cpp.a()).d(str, str2);
    }

    @Override // androidx.fragment.app.Fragment
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        LogUtil.a(TAG, "onSaveInstanceState");
        if (getArguments() != null) {
            LogUtil.a(TAG, "onSaveInstanceState getArguments() is not null");
            saveWlanInfo();
            if (this.mainActivity instanceof DeviceMainActivity) {
                ((DeviceMainActivity) this.mainActivity).d(false);
            }
        }
    }

    public void setJumpFromWifiInfoFragment(boolean z) {
        this.mIsJumpFromWifiInfoConfirmFragment = z;
    }

    private void showCustomBarTitle(int i) {
        LogUtil.a(TAG, "showCustomBarTitle bindStatus: ", Integer.valueOf(i));
        switch (i) {
            case 1:
            case 2:
            case 3:
                if (cpa.w(this.mProductId)) {
                    this.mCustomTitleBar.setTitleText(getResources().getString(R.string.IDS_device_wifi_config_network));
                    break;
                } else {
                    this.mCustomTitleBar.setTitleText(getResources().getString(R.string.IDS_device_mgr_device_pair_guide_note));
                    break;
                }
            case 4:
            default:
                LogUtil.h(TAG_RELEASE, "showCustomBarTitle viewType unknown");
                break;
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
                this.mCustomTitleBar.setTitleText(getResources().getString(R.string.IDS_device_wifi_config_network));
                break;
        }
    }

    private void initListener() {
        this.mBindSuccessButton.setOnClickListener(this);
        this.mBindNextBottom.setOnClickListener(this);
        this.mBindCancel.setOnClickListener(this);
        this.mBindCancelButton.setOnClickListener(this);
        this.mBindFinishButton.setOnClickListener(this);
    }

    private void startEstimatingBindProgress() {
        LogUtil.a(TAG, "Into startEstimatingBindProgress()");
        this.mStartBindTime = System.currentTimeMillis();
        Message obtain = Message.obtain();
        obtain.what = 1006;
        obtain.obj = false;
        this.mMainHandler.sendMessageDelayed(obtain, 3000L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateEstimatingBindProgress(boolean z) {
        int currentTimeMillis = (int) ((((System.currentTimeMillis() - this.mStartBindTime) - 3000) * 100) / 45000);
        if (currentTimeMillis < 0) {
            currentTimeMillis = 0;
        }
        if (z) {
            this.mMainHandler.removeMessages(1006);
            this.mBindProgressText.setText(UnitUtil.e(100.0d, 1, 0));
        } else {
            if (currentTimeMillis < 100) {
                this.mBindProgressText.setText(UnitUtil.e(currentTimeMillis, 1, 0));
                Message obtain = Message.obtain();
                obtain.what = 1006;
                obtain.obj = false;
                this.mMainHandler.sendMessageDelayed(obtain, 500L);
                return;
            }
            LogUtil.a(TAG_RELEASE, "updateEstimatingBindProgress waiting for completion");
        }
    }

    private void gotoWiFiInfoDevice() {
        LogUtil.a(TAG, "gotoWiFiInfoDevice mProductId: ", this.mProductId);
        cug.c().e();
        if (Build.VERSION.SDK_INT < 28) {
            cub.l(this.mainActivity);
        }
        Bundle bundle = new Bundle();
        bundle.putParcelable("commonDeviceInfo", this.mContentValues);
        bundle.putString("productId", this.mProductId);
        bundle.putString("deviceSsid", this.mDeviceSsid);
        bundle.putBoolean(HagridWiFiInfoConfirmFragment.KEY_SHOW_GUIDE, true);
        bundle.putParcelable("commonDeviceInfo", getContentValues());
        HagridWiFiInfoConfirmFragment hagridWiFiInfoConfirmFragment = new HagridWiFiInfoConfirmFragment();
        hagridWiFiInfoConfirmFragment.setArguments(bundle);
        switchFragment(hagridWiFiInfoConfirmFragment);
    }

    private boolean checkWiFiIsOpen() {
        if (cub.d(this.mainActivity)) {
            return true;
        }
        LogUtil.a(TAG_RELEASE, "checkWiFiIsOpen checkWifiStatus false");
        cqh.c().b(this.mainActivity, new ScaleDialogCallback() { // from class: com.huawei.health.device.ui.measure.fragment.HagridDeviceBindResultFragment$$ExternalSyntheticLambda0
            @Override // com.huawei.health.device.callback.ScaleDialogCallback
            public final void operationResult(int i) {
                HagridDeviceBindResultFragment.this.m147x2ce7e772(i);
            }
        });
        return false;
    }

    /* renamed from: lambda$checkWiFiIsOpen$2$com-huawei-health-device-ui-measure-fragment-HagridDeviceBindResultFragment, reason: not valid java name */
    /* synthetic */ void m147x2ce7e772(int i) {
        LogUtil.a(TAG_RELEASE, "showWifiSwitchEnableDialog result: ", Integer.valueOf(i));
        if (i == 0) {
            if (((WifiManager) this.mainActivity.getSystemService("wifi")).setWifiEnabled(true)) {
                LogUtil.a(TAG, "showWifiSwitchEnableDialog gotoWiFiInfoDevice");
                if (checkGpsIsOpen()) {
                    gotoWiFiInfoDevice();
                    return;
                }
                return;
            }
            cub.k(this.mainActivity);
            return;
        }
        LogUtil.a(TAG, "showWifiSwitchEnableDialog do nothing");
    }

    private boolean checkGpsIsOpen() {
        if (ctv.Mg_(this.mainActivity, (LocationManager) this.mainActivity.getSystemService("location"))) {
            return true;
        }
        LogUtil.a(TAG_RELEASE, "checkGpsIsOpen: gps service is not open");
        cqh.c().La_(this.mainActivity);
        return false;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        int id = view.getId();
        LogUtil.a(TAG, "onclick ID: ", Integer.valueOf(id), " bind status: ", Integer.valueOf(mBindStatus));
        if (id == R.id.id_device_bind_success_button) {
            gotoDeviceManagerPage();
        } else if (id == R.id.id_btn_go_next_ll) {
            LogUtil.a(TAG, "onclick retry");
            goNext();
        } else if (id == R.id.id_device_bind_finish_button) {
            LogUtil.a(TAG, "onclick finish");
            goNext();
        } else if (id == R.id.id_btn_bind_cancel_ll) {
            LogUtil.a(TAG, "onclick cancel");
            goCancel();
        } else if (id == R.id.id_device_not_config_wifi_interim_button) {
            goCancel();
        } else {
            LogUtil.h(TAG_RELEASE, "onClick click id unknown");
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    private void goNext() {
        LogUtil.a(TAG, "goNext mBindStatus: ", Integer.valueOf(mBindStatus));
        switch (mBindStatus) {
            case 1:
            case 2:
            case 3:
                BaseFragment baseFragment = getSelectFragment(HagridDeviceBindGuidFragment.class) instanceof BaseFragment ? (BaseFragment) getSelectFragment(HagridDeviceBindGuidFragment.class) : null;
                if (baseFragment == null) {
                    baseFragment = new HagridDeviceBindGuidFragment();
                }
                Bundle bundle = new Bundle();
                bundle.putString("productId", this.mProductId);
                bundle.putParcelable("commonDeviceInfo", getContentValues());
                baseFragment.setArguments(bundle);
                popupFragment(HagridDeviceBindGuidFragment.class);
                switchFragment((Fragment) null, baseFragment);
                break;
            case 4:
            default:
                LogUtil.h(TAG, "goNext else");
                break;
            case 5:
                chooseShowOpenNoticeBarMsgSwitchDialog();
                break;
            case 6:
                if (cqa.a().a(this.mContext, cld.HI_(getActivity(), this.mProductId))) {
                    gotoWiFiInfoDevice();
                    break;
                } else {
                    LogUtil.h(TAG, "onClickBind go retry, device is disconnect");
                    break;
                }
            case 7:
                LogUtil.a(TAG, "onClickBind goNext, HonourDeviceConstants.VIEW_TYPE_WLAN_WAITING_HYGRIDE");
                break;
            case 8:
                showOuthDialog();
                break;
            case 9:
            case 10:
                jumpToCompUserInfo();
                break;
            case 11:
                gotoDeivePairFragment();
                break;
        }
    }

    private void chooseShowOpenNoticeBarMsgSwitchDialog() {
        this.mFragmentHelper.a(this.mContext, new IBaseResponseCallback() { // from class: com.huawei.health.device.ui.measure.fragment.HagridDeviceBindResultFragment$$ExternalSyntheticLambda8
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public final void d(int i, Object obj) {
                HagridDeviceBindResultFragment.this.m148x8a4f4b9c(i, obj);
            }
        });
    }

    /* renamed from: lambda$chooseShowOpenNoticeBarMsgSwitchDialog$3$com-huawei-health-device-ui-measure-fragment-HagridDeviceBindResultFragment, reason: not valid java name */
    /* synthetic */ void m148x8a4f4b9c(int i, Object obj) {
        LogUtil.a(TAG, "chooseShowOpenNoticeBarMsgSwitchDialog: onResponse errCode: ", Integer.valueOf(i));
        gotoMainUserDeviceWlanUseGuideFragment();
    }

    private void gotoDeivePairFragment() {
        ICloudOperationResult<CloudCommonReponse> iCloudOperationResult = new ICloudOperationResult() { // from class: com.huawei.health.device.ui.measure.fragment.HagridDeviceBindResultFragment$$ExternalSyntheticLambda1
            @Override // com.huawei.hwcloudmodel.callback.ICloudOperationResult
            public final void operationResult(Object obj, String str, boolean z) {
                HagridDeviceBindResultFragment.lambda$gotoDeivePairFragment$4((CloudCommonReponse) obj, str, z);
            }
        };
        MeasurableDevice d = ceo.d().d(this.mDeviceIdentify, false);
        if (d instanceof ctk) {
            this.mDeviceId = ((ctk) d).d();
        } else {
            LogUtil.h(TAG_RELEASE, "HagridDeviceBindResultFragment unbind wifi device error");
        }
        this.mFragmentHelper.d(this.mDeviceId, iCloudOperationResult);
        this.mFragmentHelper.JX_(this.mContentValues, (ctk) d, this.mProductInfo);
        this.mMyHandler.postDelayed(new Runnable() { // from class: com.huawei.health.device.ui.measure.fragment.HagridDeviceBindResultFragment$$ExternalSyntheticLambda2
            @Override // java.lang.Runnable
            public final void run() {
                HagridDeviceBindResultFragment.this.m151xbb012722();
            }
        }, 200L);
        Bundle bundle = new Bundle();
        bundle.putString("productId", this.mProductId);
        bundle.putParcelable("commonDeviceInfo", this.mContentValues);
        bundle.putParcelable("commonDeviceInfo", getContentValues());
        ProductIntroductionFragment productIntroductionFragment = new ProductIntroductionFragment();
        productIntroductionFragment.setArguments(bundle);
        switchFragment(productIntroductionFragment);
    }

    static /* synthetic */ void lambda$gotoDeivePairFragment$4(CloudCommonReponse cloudCommonReponse, String str, boolean z) {
        int i;
        String str2;
        LogUtil.a(TAG, "onClickUnBind: ", Boolean.valueOf(z));
        if (z) {
            LogUtil.h(TAG, "HagrideDeviceBindResultFragment unbind wifi device success");
            return;
        }
        if (cloudCommonReponse != null) {
            i = cloudCommonReponse.getResultCode().intValue();
            str2 = cloudCommonReponse.getResultDesc();
        } else {
            i = Constants.CODE_UNKNOWN_ERROR;
            str2 = "unknown error";
        }
        LogUtil.h(TAG, " onClickUnBind error: ", Integer.valueOf(i), " resultDesc: ", str2);
    }

    /* renamed from: lambda$gotoDeivePairFragment$5$com-huawei-health-device-ui-measure-fragment-HagridDeviceBindResultFragment, reason: not valid java name */
    /* synthetic */ void m151xbb012722() {
        nrh.b(this.mainActivity, R.string.IDS_device_scale_unpair_success);
    }

    private void goCancel() {
        LogUtil.a(TAG, "goCancel mBindStatus: ", Integer.valueOf(mBindStatus));
        int i = mBindStatus;
        if (i == 3 || i == 2 || i == 1) {
            coy.b(this.mProductId, this.mDeviceIdentify);
            BaseFragment baseFragment = getSelectFragment(HagridDeviceBindGuidFragment.class) instanceof BaseFragment ? (BaseFragment) getSelectFragment(ProductIntroductionFragment.class) : null;
            if (baseFragment == null) {
                baseFragment = new ProductIntroductionFragment();
            }
            Bundle bundle = new Bundle();
            bundle.putParcelable("commonDeviceInfo", getContentValues());
            baseFragment.setArguments(bundle);
            popupFragment(ProductIntroductionFragment.class);
            switchFragment((Fragment) null, baseFragment);
            return;
        }
        if (i == 8) {
            Message obtain = Message.obtain();
            obtain.what = 1000;
            this.mMyHandler.sendMessage(obtain);
            return;
        }
        if (i == 6) {
            gotoDeviceManagerPage();
            return;
        }
        if (i == 7) {
            onBackPressed();
            return;
        }
        if (i == 11) {
            Bundle bundle2 = new Bundle();
            bundle2.putString("productId", this.mProductId);
            bundle2.putParcelable("commonDeviceInfo", this.mContentValues);
            bundle2.putBoolean("isNfcConnect", this.mIsNfcConnect);
            LogUtil.a(TAG, "goCancel bind fail putBoolean mIsNfcConnect: ", Boolean.valueOf(this.mIsNfcConnect));
            HagridDeviceManagerFragment hagridDeviceManagerFragment = new HagridDeviceManagerFragment();
            hagridDeviceManagerFragment.setArguments(bundle2);
            switchFragment((Fragment) null, hagridDeviceManagerFragment);
            return;
        }
        LogUtil.h(TAG, "goCancel mBindStatus else");
    }

    private ContentValues getContentValues() {
        ContentValues contentValues = new ContentValues();
        contentValues.put("uniqueId", this.mDeviceIdentify);
        contentValues.put("productId", this.mProductId);
        return contentValues;
    }

    private void jumpToCompUserInfo() {
        if (!CommonUtil.aa(this.mContext)) {
            nrh.b(this.mContext, R.string._2130841884_res_0x7f02111c);
        } else if (cpa.x(this.mProductId)) {
            showLoadingDialog(getString(R.string._2130843058_res_0x7f0215b2));
            getDeviceRegistration();
        } else {
            gotoUserInfo(true);
        }
    }

    private void showLoadingDialog(String str) {
        if (this.mLoadingDialog == null) {
            new CommonDialog21(getActivity(), R.style.app_update_dialogActivity);
            CommonDialog21 a2 = CommonDialog21.a(getActivity());
            this.mLoadingDialog = a2;
            a2.e(str);
            this.mLoadingDialog.a();
            this.mLoadingDialog.setCancelable(false);
            return;
        }
        LogUtil.a(TAG, "showLoadingDialog: mLoadingDialog is not null");
    }

    private void getDeviceRegistration() {
        WifiDeviceGetDeviceRegistration wifiDeviceGetDeviceRegistration = new WifiDeviceGetDeviceRegistration();
        wifiDeviceGetDeviceRegistration.setProductId(cpa.f.get(this.mProductId).intValue());
        wifiDeviceGetDeviceRegistration.setUniqueId(cpa.l(this.mDeviceIdentify));
        jbs.a(this.mContext).a(wifiDeviceGetDeviceRegistration, new ICloudOperationResult() { // from class: com.huawei.health.device.ui.measure.fragment.HagridDeviceBindResultFragment$$ExternalSyntheticLambda7
            @Override // com.huawei.hwcloudmodel.callback.ICloudOperationResult
            public final void operationResult(Object obj, String str, boolean z) {
                HagridDeviceBindResultFragment.this.m150xbba73f0a((CloudCommonReponse) obj, str, z);
            }
        });
    }

    /* renamed from: lambda$getDeviceRegistration$6$com-huawei-health-device-ui-measure-fragment-HagridDeviceBindResultFragment, reason: not valid java name */
    /* synthetic */ void m150xbba73f0a(CloudCommonReponse cloudCommonReponse, String str, boolean z) {
        if (z) {
            if (((Integer) ((Map) new Gson().fromJson(str, new TypeToken<Map<String, Integer>>() { // from class: com.huawei.health.device.ui.measure.fragment.HagridDeviceBindResultFragment.5
            }.getType())).get("status")).intValue() != 1) {
                LogUtil.a(TAG, "device not registration,goto subuser userinfo");
                gotoUserInfo(false);
                return;
            } else {
                LogUtil.a(TAG, "status is registration,goto user info");
                gotoUserInfo(true);
                return;
            }
        }
        if (cloudCommonReponse != null && cloudCommonReponse.getResultCode().intValue() == CLOUD_DEVICE_NOT_FIND_CODE) {
            LogUtil.a(TAG, "getDeviceRegistration device cloud not find this device");
            gotoUserInfo(false);
        } else {
            gotoUserInfo(true);
            LogUtil.a(TAG, "getDeviceRegistration fail,goto sub user user info");
        }
    }

    private void destroyLoadingDialog() {
        CommonDialog21 commonDialog21 = this.mLoadingDialog;
        if (commonDialog21 == null || !commonDialog21.isShowing()) {
            return;
        }
        this.mLoadingDialog.dismiss();
        this.mLoadingDialog = null;
    }

    private void gotoUserInfo(boolean z) {
        destroyLoadingDialog();
        Bundle arguments = getArguments();
        if (arguments == null) {
            return;
        }
        if (z) {
            z = arguments.getBoolean("hasManager", true);
        }
        Intent intentData = getIntentData(arguments, z);
        saveDeviceManagerStatus(z);
        if (!isAdded() || isDetached()) {
            LogUtil.h(TAG, "gotoUserInfo, !isAdded() || isDetached()");
            return;
        }
        try {
            getContext().startActivity(intentData);
        } catch (ActivityNotFoundException unused) {
            LogUtil.b(TAG, "gotoUserInfo startActivity ActivityNotFoundException occur.");
        }
        if (this.mainActivity == null || this.mainActivity.isFinishing() || this.mainActivity.isDestroyed()) {
            LogUtil.h(TAG, "gotoUserInfo, mainActivity is null or isFinishing or isDestroyed");
            return;
        }
        if (this.mainActivity instanceof DeviceMainActivity) {
            ((DeviceMainActivity) this.mainActivity).d(false);
        }
        this.mainActivity.finish();
    }

    private Intent getIntentData(Bundle bundle, boolean z) {
        Intent intent = new Intent();
        float f = bundle.getFloat("deviceWeight");
        String str = bundle.get("deviceSsid") instanceof String ? (String) bundle.get("deviceSsid") : null;
        try {
            intent.putExtra("mainHuid", bundle.getByteArray("mainHuid"));
        } catch (ArrayIndexOutOfBoundsException unused) {
            LogUtil.b(TAG, "jumpToCompUserInfo ArrayIndexOutOfBoundsException");
        }
        intent.putExtra("device", "wifi");
        intent.putExtra("deviceWeight", f);
        intent.putExtra("productId", this.mProductId);
        intent.putExtra("commonDeviceInfo", this.mContentValues);
        intent.putExtra("isBleScale", true);
        if (cpa.ah(this.mProductId) || cpa.r(this.mProductId)) {
            intent.putExtra("goto", "devicebind");
        } else {
            if (!TextUtils.isEmpty(str)) {
                intent.putExtra("deviceSsid", str);
            }
            intent.putExtra("auth_device_id", this.mProductId);
            intent.putExtra("hasManager", z);
            intent.putExtra("cloudDeviceId", this.mCloudDeviceId);
        }
        intent.putExtra("config_mode", 5);
        if (bundle.get("deviceResis") instanceof ckm) {
            intent.putExtra("deviceResis", (ckm) bundle.get("deviceResis"));
        }
        intent.putExtra("accountInfo", this.mAccountInfo);
        intent.putExtra("isNfcConnect", this.mIsNfcConnect);
        LogUtil.a(TAG, "gotoUserInfo put isNfcConnect is ", Boolean.valueOf(this.mIsNfcConnect));
        intent.setClassName(BaseApplication.getAppPackage(), "com.huawei.ui.main.stories.me.activity.BindUserInfoActivity");
        return intent;
    }

    private void saveDeviceManagerStatus(boolean z) {
        DeviceCloudSharePreferencesManager deviceCloudSharePreferencesManager = new DeviceCloudSharePreferencesManager(cpp.a());
        StringBuffer stringBuffer = new StringBuffer(16);
        stringBuffer.append("device_has_manager");
        stringBuffer.append(this.mDeviceIdentify);
        deviceCloudSharePreferencesManager.b(stringBuffer.toString(), z);
    }

    private void showOuthDialog() {
        View inflate = View.inflate(this.mContext, R.layout.dialog_user_auth_message, null);
        cpn.Kg_(inflate, this.mContext, isShowOuthDialog(), isShowPersonalDialog());
        dif.Vq_(this.mContext, inflate, new IBaseResponseCallback() { // from class: com.huawei.health.device.ui.measure.fragment.HagridDeviceBindResultFragment$$ExternalSyntheticLambda3
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public final void d(int i, Object obj) {
                HagridDeviceBindResultFragment.this.m153x1a24799f(i, obj);
            }
        });
    }

    /* renamed from: lambda$showOuthDialog$7$com-huawei-health-device-ui-measure-fragment-HagridDeviceBindResultFragment, reason: not valid java name */
    /* synthetic */ void m153x1a24799f(int i, Object obj) {
        LogUtil.a(TAG, "showOuthDialog, onResponse errCode=", Integer.valueOf(i));
        if (i == 0) {
            if (!this.mIsHealthDataStatus) {
                this.mInteractors.c(7, true, TAG, (IBaseResponseCallback) null);
            }
            if (!this.mIsPersonalInfoStatus) {
                this.mInteractors.c(2, true, TAG, (IBaseResponseCallback) null);
            }
            if (checkWiFiIsOpen()) {
                if (checkGpsIsOpen()) {
                    LogUtil.a(TAG, "showOuthDialog() goto wifi config page");
                    gotoWiFiInfoDevice();
                    return;
                }
                return;
            }
            LogUtil.h(TAG, "showOuthDialog wlan is not open");
        }
    }

    private boolean isShowOuthDialog() {
        return getDataStatus() && Utils.i();
    }

    private boolean isShowPersonalDialog() {
        return gePersonalInfoStatus() && Utils.i();
    }

    private boolean gePersonalInfoStatus() {
        if ("true".equals(this.mInteractors.c(2))) {
            this.mIsPersonalInfoStatus = true;
        } else {
            this.mIsPersonalInfoStatus = false;
        }
        return !this.mIsPersonalInfoStatus;
    }

    private boolean getDataStatus() {
        if ("true".equals(this.mInteractors.c(7))) {
            this.mIsHealthDataStatus = true;
        } else {
            this.mIsHealthDataStatus = false;
        }
        return !this.mIsHealthDataStatus;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void gotoDeviceManagerPage() {
        Bundle bundle = new Bundle();
        bundle.putParcelable("commonDeviceInfo", this.mContentValues);
        bundle.putString("productId", this.mProductId);
        bundle.putString("goto", "devicebind");
        bundle.putBoolean("isNfcConnect", this.mIsNfcConnect);
        LogUtil.a(TAG, "gotoDeviceManagerPage putBoolean mIsNfcConnect: ", Boolean.valueOf(this.mIsNfcConnect));
        coy.b(this.mProductId, this.mDeviceIdentify);
        boolean a2 = cji.a(this.mDeviceIdentify, 39);
        LogUtil.a(TAG, "support prompt handle measure : ", Boolean.valueOf(a2));
        if (a2) {
            HagridDeviceWlanUseGuideFragment hagridDeviceWlanUseGuideFragment = new HagridDeviceWlanUseGuideFragment();
            bundle.putString("uniqueId", this.mDeviceIdentify);
            bundle.putInt(ParamConstants.Param.VIEW_TYPE, 14);
            switchFragment((Fragment) null, hagridDeviceWlanUseGuideFragment);
            hagridDeviceWlanUseGuideFragment.setArguments(bundle);
        } else {
            if (getSelectFragment(HagridDeviceManagerFragment.class) != null) {
                popupFragment(HagridDeviceManagerFragment.class);
            }
            HagridDeviceManagerFragment hagridDeviceManagerFragment = new HagridDeviceManagerFragment();
            switchFragment((Fragment) null, hagridDeviceManagerFragment);
            hagridDeviceManagerFragment.setArguments(bundle);
        }
        this.mMyHandler.removeMessages(1007);
    }

    private void gotoMainUserDeviceWlanUseGuideFragment() {
        LogUtil.a(TAG, "gotoMainUserDeviceWlanUseGuideFragment");
        Bundle bundle = new Bundle();
        bundle.putParcelable("commonDeviceInfo", this.mContentValues);
        bundle.putInt(ParamConstants.Param.VIEW_TYPE, 15);
        bundle.putBoolean("isNfcConnect", this.mIsNfcConnect);
        LogUtil.a(TAG, "gotoMainUserDeviceWlanUseGuideFragment put isNfcConnect is ", Boolean.valueOf(this.mIsNfcConnect));
        Fragment selectFragment = getSelectFragment(HagridDeviceWlanUseGuideFragment.class);
        BaseFragment baseFragment = selectFragment instanceof BaseFragment ? (BaseFragment) selectFragment : null;
        if (!(baseFragment instanceof HagridDeviceWlanUseGuideFragment)) {
            baseFragment = new HagridDeviceWlanUseGuideFragment();
        }
        baseFragment.setArguments(bundle);
        switchFragment(baseFragment);
    }

    @Override // com.huawei.health.ecologydevice.ui.BaseFragment, androidx.fragment.app.Fragment
    public void onResume() {
        LogUtil.a(TAG, "onResume");
        super.onResume();
    }

    private String spliceZipText(ArrayList<dcz.d> arrayList, int i, int i2) {
        StringBuilder sb = new StringBuilder(16);
        while (i < i2) {
            String c = dja.c(arrayList, this.mProductId, i);
            if (!TextUtils.isEmpty(c)) {
                sb.append(c);
                if (i != i2 - 1) {
                    sb.append(System.lineSeparator());
                }
            }
            i++;
        }
        return sb.toString();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateStatus(int i) {
        this.mAnimationView.setVisibility(8);
        this.mBindDeviceProgressLayout.setVisibility(8);
        this.mAutoUpgradeLayout.setVisibility(8);
        AnimationDrawable animationDrawable = this.mAnimation;
        if (animationDrawable != null) {
            animationDrawable.stop();
        }
        LogUtil.a(TAG, "updateStatus viewType:", Integer.valueOf(i));
        switch (i) {
            case 1:
            case 3:
                showBleFailView();
                break;
            case 2:
                showScanFailView();
                break;
            case 5:
                this.mReChooseLayout.setVisibility(8);
                showWlanSuccessView();
                break;
            case 6:
                this.mReChooseLayout.setVisibility(8);
                showWlanFailView();
                break;
            case 7:
                this.mReChooseLayout.setVisibility(0);
                showWlanWaitingView();
                break;
            case 8:
                showWlanGuideView();
                break;
            case 9:
                showPairSuccessView(true);
                getDeviceManagerInfo();
                break;
            case 10:
                showPairSuccessView(false);
                break;
            case 11:
                this.mReChooseLayout.setVisibility(8);
                showDeviceCheckFailView();
                break;
        }
    }

    private void showWlanGuideView() {
        LogUtil.a(TAG, "showWlanGuideView enter");
        this.mBindGuideBottom.setVisibility(0);
        this.mBindSuccessLayout.setVisibility(8);
        this.mBindGuideLayout.setVisibility(0);
        this.mBindCancel.setVisibility(4);
        this.mBindCancelButton.setVisibility(0);
        dcz dczVar = this.mProductInfo;
        if (dczVar != null) {
            setBindImageBitmap(this.mGuideImg, dczVar.ab(), 0);
            String c = dja.c(this.mProductInfo.ab(), this.mProductId, 1);
            if (LanguageUtil.y(BaseApplication.getContext())) {
                this.mBindWifiGuideDes.setText("\u200f" + c);
                return;
            }
            this.mBindWifiGuideDes.setText(c);
            return;
        }
        LogUtil.h(TAG, "updateStatus mProductInfo is null");
    }

    private void getDeviceManagerInfo() {
        if (this.mDeviceManagerInfoHandler == null) {
            this.mDeviceManagerInfoHandler = new DeviceManagerInfoHandler();
        }
        this.mDeviceManagerInfoHandler.e(new DeviceManagerInfoHandler.GetManagerAccountInfoListener() { // from class: com.huawei.health.device.ui.measure.fragment.HagridDeviceBindResultFragment$$ExternalSyntheticLambda6
            @Override // com.huawei.health.device.ui.util.DeviceManagerInfoHandler.GetManagerAccountInfoListener
            public final void managerAccountInfo(String str) {
                HagridDeviceBindResultFragment.this.m149x3620e3d0(str);
            }
        });
        this.mDeviceManagerInfoHandler.a();
    }

    /* renamed from: lambda$getDeviceManagerInfo$8$com-huawei-health-device-ui-measure-fragment-HagridDeviceBindResultFragment, reason: not valid java name */
    /* synthetic */ void m149x3620e3d0(String str) {
        if (TextUtils.isEmpty(str)) {
            str = "";
        }
        this.mAccountInfo = str;
    }

    private void showPairSuccessView(boolean z) {
        if (z) {
            setTitle(getResources().getString(R.string._2130841385_res_0x7f020f29));
        } else {
            setTitle(getResources().getString(R.string.IDS_device_mgr_device_pair_guide_note));
            this.mBindSuccessButton.setText(getResources().getString(R.string.IDS_device_hygride_wlan_done));
        }
        this.mGuideImg.setVisibility(8);
        this.mBindGuideLayout.setVisibility(8);
        this.mBindGuideBottom.setVisibility(8);
        this.mGuideSuccessImg.setVisibility(0);
        this.mBindSuccessLayout.setVisibility(0);
        if (cpa.ae(this.mProductId)) {
            this.mBindGoText.setText(R.string.IDS_device_show_next);
            this.mBindCancel.setVisibility(8);
            this.mBindGuideBottom.setVisibility(0);
        } else {
            this.mBindSuccessLayoutBottom.setVisibility(0);
        }
        dcz dczVar = this.mProductInfo;
        if (dczVar != null) {
            String c = dja.c(dczVar.d(), this.mProductId, 4);
            if (LanguageUtil.ac(BaseApplication.getContext())) {
                this.mBindSuccessText.setText("\u200f" + c);
            } else {
                this.mBindSuccessText.setText(c);
            }
            setBindImageBitmap(this.mBackgroundImg, this.mProductInfo.d(), 4);
            return;
        }
        LogUtil.h(TAG, "showPairSuccessView mProductInfo is null");
    }

    private void showScanFailView() {
        resultFailView();
        setTitle(getResources().getString(R.string.IDS_device_mgr_device_pair_guide_note));
        dcz dczVar = this.mProductInfo;
        if (dczVar != null) {
            setBindImageBitmap(this.mBackgroundImg, dczVar.d(), 10);
            this.mBindSuccessText.setText(dja.c(this.mProductInfo.d(), this.mProductId, 10));
            this.mBindSucessDes.setText(spliceZipText(this.mProductInfo.d(), 11, this.mProductInfo.d().size()));
            this.mBindSucessDes.setVisibility(0);
            return;
        }
        LogUtil.h(TAG, "showScanFailView mProductInfo is null");
    }

    private void showBleFailView() {
        resultFailView();
        setTitle(getResources().getString(R.string.IDS_device_mgr_device_pair_guide_note));
        dcz dczVar = this.mProductInfo;
        if (dczVar != null) {
            setBindImageBitmap(this.mBackgroundImg, dczVar.d(), 5);
            this.mBindSuccessText.setText(dja.c(this.mProductInfo.d(), this.mProductId, 5));
            this.mBindSucessDes.setText(spliceZipText(this.mProductInfo.d(), 6, 10));
            this.mBindSucessDes.setVisibility(0);
            return;
        }
        LogUtil.h(TAG, "showDeviceCheckFailView mProductInfo is null");
    }

    private void showDeviceCheckFailView() {
        resultFailView();
        this.mBindGoText.setText(R$string.IDS_hw_health_wear_connect_device_unpair_button);
        setTitle(getResources().getString(R.string.IDS_device_mgr_device_pair_guide_note));
        this.mBindSuccessText.setText(getResources().getString(R.string.IDS_device_wifi_bind_fail));
        dcz dczVar = this.mProductInfo;
        if (dczVar != null) {
            setBindImageBitmap(this.mBackgroundImg, dczVar.d(), 5);
        } else {
            LogUtil.h(TAG, "showDeviceCheckFailView mProductInfo is null");
        }
        this.mBindSucessDes.setVisibility(0);
        this.mBindSucessDes.setText(R.string.IDS_device_scale_pair_fail_unbind_tip);
    }

    private void showWlanSuccessView() {
        this.mDeviceManager.d();
        setTitle(getResources().getString(R.string.IDS_device_wifi_config_network));
        this.mBindGuideLayout.setVisibility(8);
        this.mBindSuccessLayout.setVisibility(0);
        if (this.mProductInfo != null) {
            this.mBindSuccessText.setVisibility(0);
            this.mBindSucessDes.setVisibility(0);
            setBindImageBitmap(this.mBackgroundImg, this.mProductInfo.ab(), 3);
            this.mBindSuccessText.setText(dja.c(this.mProductInfo.ab(), this.mProductId, 3));
            this.mBindSucessDes.setText(dja.c(this.mProductInfo.ab(), this.mProductId, 4));
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) this.mBindSucessDes.getLayoutParams();
            layoutParams.gravity = 1;
            layoutParams.topMargin = nsn.c(BaseApplication.getContext(), 8.0f);
            this.mBindSucessDes.setLayoutParams(layoutParams);
        } else {
            LogUtil.h(TAG, "showWlanSuccessView mProductInfo is null");
        }
        this.mBindSuccessLayoutBottom.setVisibility(8);
        this.mBindGuideBottom.setVisibility(0);
        this.mBindCancel.setVisibility(8);
        this.mGuideSuccessImg.setVisibility(0);
        this.mGuideImg.setVisibility(8);
        LogUtil.a(TAG, "showWlanSuccessView mIsModifyWlanInfo:", Boolean.valueOf(this.mIsModifyWlanInfo));
        if (!this.mIsModifyWlanInfo) {
            this.mAutoUpgradeLayout.setVisibility(0);
            if (Utils.o()) {
                this.mAutoUpgradeSwitch.setChecked(false);
            }
        }
        this.mBindNextBottom.setVisibility(8);
        this.mBindFinishButton.setVisibility(0);
        SharedPreferenceManager.e(BaseApplication.getContext(), String.valueOf(10000), "hagrid_wifi_ssid" + this.mDeviceIdentify, this.mSsid, (StorageParams) null);
        sendMultiUserInfo();
        this.mIsBinding = false;
        setUserCollectionSwitch();
        cpz.f(this.deviceAddress);
        if (TextUtils.isEmpty(this.deviceAddress)) {
            return;
        }
        this.deviceAddress = "";
    }

    private void setUserCollectionSwitch() {
        MyHandler myHandler = this.mMyHandler;
        if (myHandler != null) {
            myHandler.postDelayed(new Runnable() { // from class: com.huawei.health.device.ui.measure.fragment.HagridDeviceBindResultFragment$$ExternalSyntheticLambda4
                @Override // java.lang.Runnable
                public final void run() {
                    HagridDeviceBindResultFragment.lambda$setUserCollectionSwitch$9();
                }
            }, 3000L);
        }
    }

    static /* synthetic */ void lambda$setUserCollectionSwitch$9() {
        boolean e = knx.e();
        LogUtil.a(TAG, "setUserCollectionSwitch isAnalyticsOpen:", Boolean.valueOf(e));
        if (e) {
            csf.e(e);
        }
    }

    private void showWlanFailView() {
        this.mDeviceManager.d();
        resultFailView();
        this.mBindCancel.setVisibility(0);
        this.mBindFinishButton.setVisibility(8);
        if (this.mProductInfo != null) {
            this.mBindSuccessText.setVisibility(0);
            this.mBindSucessDes.setVisibility(0);
            setBindImageBitmap(this.mBackgroundImg, this.mProductInfo.ab(), 5);
            String c = dja.c(this.mProductInfo.ab(), this.mProductId, 5);
            if (LanguageUtil.bp(BaseApplication.getContext())) {
                this.mBindSuccessText.setText("\u200f" + c);
            } else {
                this.mBindSuccessText.setText(c);
            }
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) this.mBindSucessDes.getLayoutParams();
            layoutParams.gravity = GravityCompat.START;
            layoutParams.topMargin = nsn.c(BaseApplication.getContext(), 8.0f);
            this.mBindSucessDes.setLayoutParams(layoutParams);
            this.mBindSucessDes.setText(spliceZipText(this.mProductInfo.ab(), 6, this.mProductInfo.ab().size()));
        } else {
            LogUtil.a(TAG, "showWlanFailView mProductInfo is null");
        }
        this.mBindNextBottom.setVisibility(0);
        isClickNextStep(true);
        this.mBindCancelText.setText(getResources().getString(R.string.IDS_device_wlan_not_config_device));
        this.mBindGoText.setText(getResources().getString(R.string._2130841467_res_0x7f020f7b));
        this.mIsBinding = false;
        this.mBindSuccessButton.setVisibility(8);
    }

    private void showWlanWaitingView() {
        LogUtil.a(TAG, "showWlanWaitingView starting");
        this.mBindDeviceProgressLayout.setVisibility(0);
        String e = UnitUtil.e(0.0d, 1, 0);
        this.mBindPercentSign.setText(UnitUtil.e(0.0d, 2, 0).replace(e, ""));
        this.mBindProgressText.setText(e);
        resultFailView();
        dcz dczVar = this.mProductInfo;
        if (dczVar != null) {
            setBindImageBitmap(this.mBackgroundImg, dczVar.ab(), 0);
            this.mBindSucessDes.setText(dja.c(this.mProductInfo.ab(), this.mProductId, 2));
            this.mBindSucessDes.setVisibility(0);
            if (this.mBindSucessDes.getLayoutParams() instanceof LinearLayout.LayoutParams) {
                LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) this.mBindSucessDes.getLayoutParams();
                layoutParams.gravity = 1;
                layoutParams.topMargin = nsn.c(BaseApplication.getContext(), 24.0f);
                this.mBindSucessDes.setLayoutParams(layoutParams);
            } else {
                LogUtil.h(TAG, "mBindSucessDes.getLayoutParams() not instanceof LinearLayout.LayoutParams");
            }
        } else {
            LogUtil.h(TAG, "showWlanWaitingView mProductInfo is null");
        }
        this.mBindSuccessText.setVisibility(8);
        this.mBindCancel.setVisibility(8);
        this.mBindNextBottom.setVisibility(8);
        configWlanInfo();
    }

    private void configWlanInfo() {
        EventBus.d(this.mEventCallback, 0, "send_wifi_password_result", "set_manager_info_result", "update_wifi_device_cloud_status");
        if (TextUtils.isEmpty(this.mSsid)) {
            String wlanInfoByKey = getWlanInfoByKey("outhName");
            this.mSsid = wlanInfoByKey;
            LogUtil.a(TAG, "configWlanInfo outhName: ", wlanInfoByKey);
        }
        if (TextUtils.isEmpty(this.mSsidPwd)) {
            String wlanInfoByKey2 = getWlanInfoByKey("outhPd");
            this.mSsidPwd = wlanInfoByKey2;
            LogUtil.a(TAG, "configWlanInfo mSsidPwd: ", wlanInfoByKey2);
        }
        this.mPairingWifiNameText.setText(String.format(Locale.ENGLISH, getString(R.string.IDS_device_wifi_current_config), this.mSsid));
        if (isManagerUser()) {
            EventBus.d(this.mEventCallback, 0, "update_wifi_device_cloud_status");
            this.mIsModifyWlanInfo = true;
            Bundle bundle = new Bundle();
            bundle.putString("wifiSsid", this.mSsid);
            bundle.putString("wifiPwd", this.mSsidPwd);
            EventBus.d(new EventBus.b("send_ssid", bundle));
            LogUtil.a(TAG, "start update wifi ssid and password");
            startEstimatingBindProgress();
        } else {
            LogUtil.a(TAG, "configWlanInfo mIsCreateSave: ", Boolean.valueOf(this.mIsCreateSave));
            LogUtil.a(TAG, "configWlanInfo not manager usr to config wlan mIsBinding: ", Boolean.valueOf(this.mIsBinding));
            this.mIsModifyWlanInfo = false;
            if (!this.mIsBinding) {
                ctn ctnVar = new ctn();
                ctnVar.f(this.mDeviceSsid);
                if (this.mIsCreateSave) {
                    ctnVar.e(this.mDeviceId);
                    this.mIsCreateSave = false;
                    startEstimatingBindProgress();
                }
                this.mDeviceManager.a(ctnVar, DeviceRegisterManager.RegisterMode.REGISTER_BLE, this.mRegisterCallback);
                this.mMyHandler.sendEmptyMessageDelayed(1007, 95000L);
                this.mIsBinding = true;
            }
        }
        LogUtil.a(TAG, "showWlanWaitingView mIsModifyWlanInfo: ", Boolean.valueOf(this.mIsModifyWlanInfo));
    }

    private void setBindImageBitmap(ImageView imageView, ArrayList<dcz.d> arrayList, int i) {
        if (imageView == null) {
            LogUtil.h(TAG, "setImageBitmap ImageView is null");
        } else {
            refreshBindResultLayout();
            imageView.setImageBitmap(dja.VF_(arrayList, this.mProductId, i));
        }
    }

    private void resultFailView() {
        this.mGuideImg.setVisibility(8);
        this.mBindGuideLayout.setVisibility(8);
        this.mGuideSuccessImg.setVisibility(0);
        this.mBindSuccessLayout.setVisibility(0);
        this.mBindSucessDes.setVisibility(8);
        this.mBindSuccessLayoutBottom.setVisibility(8);
        this.mBindGuideBottom.setVisibility(0);
        this.mBindCancelText.setText(getResources().getString(R.string._2130841130_res_0x7f020e2a));
        this.mBindGoText.setText(getResources().getString(R.string._2130841467_res_0x7f020f7b));
    }

    private boolean isClickNextStep(boolean z) {
        if (this.mainActivity == null) {
            return false;
        }
        if (z) {
            this.mBindNextBottom.setEnabled(true);
            this.mBindGoText.setTextColor(BaseApplication.getContext().getResources().getColor(R.color._2131299236_res_0x7f090ba4));
            if (LanguageUtil.bc(BaseApplication.getContext())) {
                this.mNextImage.setBackgroundResource(R.drawable.wifi_device_arrow_left);
            } else {
                this.mNextImage.setBackgroundResource(R.drawable.wifi_device_arrow_right);
            }
            return true;
        }
        this.mBindNextBottom.setEnabled(false);
        this.mBindGoText.setTextColor(BaseApplication.getContext().getResources().getColor(R.color._2131299241_res_0x7f090ba9));
        if (LanguageUtil.bc(BaseApplication.getContext())) {
            this.mNextImage.setBackgroundResource(R.drawable.wifi_device_arrow_disable_left);
        } else {
            this.mNextImage.setBackgroundResource(R.drawable.wifi_device_arrow_disable_right);
        }
        return false;
    }

    @Override // com.huawei.health.ecologydevice.ui.BaseFragment
    public boolean onBackPressed() {
        LogUtil.a(TAG, "onBackPressed");
        int i = mBindStatus;
        if (i == 5 || i == 9 || i == 10 || i == 8) {
            gotoDeviceManagerPage();
        } else {
            if (i == 7) {
                return false;
            }
            coy.b(this.mProductId, this.mDeviceIdentify);
            int i2 = mBindStatus;
            if (i2 == 1 || i2 == 3) {
                stop();
            }
            this.mMyHandler.removeMessages(1007);
            popupFragment(HagridWiFiInfoConfirmFragment.class);
        }
        return false;
    }

    private void stop() {
        LogUtil.a(TAG, "stop enter");
        if (this.mProductId != null) {
            Bundle bundle = new Bundle();
            bundle.putInt("type", -6);
            bundle.putString("productId", this.mProductId);
            MeasurableDevice d = ceo.d().d(this.mDeviceIdentify, true);
            if (d != null) {
                cgt.e().prepare(d, null, bundle);
            } else {
                cgt.e().prepare(null, null, bundle);
            }
        }
        cjx.e().e(this.mProductId, this.mDeviceIdentify, -6);
    }

    @Override // com.huawei.health.ecologydevice.ui.BaseFragment, androidx.fragment.app.Fragment
    public void onDestroy() {
        LogUtil.a(TAG, "onDestroy");
        cst.c(this.mainActivity);
        AnimationDrawable animationDrawable = this.mAnimation;
        if (animationDrawable != null) {
            animationDrawable.stop();
        }
        this.mIsBinding = false;
        this.mIsJumpFromWifiInfoConfirmFragment = false;
        this.mDeviceManager.d();
        this.mMyHandler.removeMessages(1007);
        EventBus.a(this.mEventCallback, "send_wifi_password_result", "update_wifi_device_cloud_status", "set_manager_info_result");
        super.onDestroy();
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroyView() {
        sendAutoUpgrade();
        super.onDestroyView();
        LogUtil.a(TAG, "onDestroyView");
    }

    private void sendAutoUpgrade() {
        if (this.mAutoUpgradeLayout.getVisibility() == 0) {
            String str = this.mAutoUpgradeSwitch.isChecked() ? "1" : "0";
            LogUtil.a(TAG, "sendAutoUpgrade autoUpgradeStatus: ", str);
            csf.d(this.mDeviceId, str, knx.e());
        }
    }

    private void sendMultiUserInfo() {
        if (TextUtils.isEmpty(this.mProductId)) {
            LogUtil.h(TAG_RELEASE, "sendMultiUserInfoToScale fail: mProductId is null");
            return;
        }
        ctk ctkVar = ceo.d().d(this.mDeviceIdentify, false) instanceof ctk ? (ctk) ceo.d().d(this.mDeviceIdentify, false) : null;
        if (ctkVar == null) {
            LogUtil.h(TAG_RELEASE, "sendMultiUserInfoToScale fail: wifiDevice is null");
            return;
        }
        String d = ctkVar.d();
        if (TextUtils.isEmpty(d)) {
            return;
        }
        csf.c(d, true);
    }

    private boolean isManagerUser() {
        ctk ctkVar = this.mWifiDevice;
        return (ctkVar == null || ctkVar.b() == null || this.mWifiDevice.b().k() != 1) ? false : true;
    }

    private void startTimer() {
        if (this.mCheckTimer == null) {
            this.mCheckTimer = new Timer(TAG);
            LogUtil.a(TAG, "Start the timer check device status");
            this.mCheckTimer.schedule(new MyTimerTask(this), 60000L);
        }
    }

    private void cancelTimer() {
        Timer timer = this.mCheckTimer;
        if (timer != null) {
            timer.cancel();
            this.mCheckTimer = null;
            LogUtil.a(TAG, "Cancel the timer connected device");
            return;
        }
        LogUtil.h(TAG, "checkTimer has been canceled");
    }

    static class MyTimerTask extends TimerTask {
        private WeakReference<HagridDeviceBindResultFragment> mWeakRefc;

        MyTimerTask(HagridDeviceBindResultFragment hagridDeviceBindResultFragment) {
            this.mWeakRefc = new WeakReference<>(hagridDeviceBindResultFragment);
        }

        @Override // java.util.TimerTask, java.lang.Runnable
        public void run() {
            WeakReference<HagridDeviceBindResultFragment> weakReference = this.mWeakRefc;
            if (weakReference == null) {
                LogUtil.h(HagridDeviceBindResultFragment.TAG_RELEASE, "MyTimerTask run mWeakRefc is null");
                return;
            }
            HagridDeviceBindResultFragment hagridDeviceBindResultFragment = weakReference.get();
            if (hagridDeviceBindResultFragment == null) {
                LogUtil.h(HagridDeviceBindResultFragment.TAG_RELEASE, "MyTimerTask run fragment is null");
            } else {
                LogUtil.a(HagridDeviceBindResultFragment.TAG_RELEASE, "check wifiDevice cloud status timeout");
                hagridDeviceBindResultFragment.mMyHandler.sendEmptyMessage(1004);
            }
        }
    }

    @Override // com.huawei.health.ecologydevice.ui.BaseFragment, androidx.fragment.app.Fragment, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LogUtil.a(TAG, "onConfigurationChanged, refresh page");
        refreshBindResultLayout();
        super.onConfigurationChanged(configuration);
    }

    private void refreshBindResultLayout() {
        LinearLayout linearLayout = this.mBindResultDeviceLayout;
        if (linearLayout != null) {
            nsn.cLA_(linearLayout, 3);
        }
    }
}
