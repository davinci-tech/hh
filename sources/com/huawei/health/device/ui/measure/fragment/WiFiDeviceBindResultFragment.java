package com.huawei.health.device.ui.measure.fragment;

import android.animation.ValueAnimator;
import android.content.ActivityNotFoundException;
import android.content.ContentValues;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.drawable.AnimationDrawable;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Message;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import com.google.gson.Gson;
import com.huawei.health.R;
import com.huawei.health.device.callback.IDeviceEventHandler;
import com.huawei.health.device.model.HealthDevice;
import com.huawei.health.device.ui.DeviceMainActivity;
import com.huawei.health.device.util.EventBus;
import com.huawei.health.device.wifi.interfaces.DeviceCallBack;
import com.huawei.health.device.wifi.interfaces.ScanDeviceCallback;
import com.huawei.health.device.wifi.lib.handler.StaticHandler;
import com.huawei.health.device.wifi.manager.DeviceRegisterManager;
import com.huawei.health.ecologydevice.connectivity.ScanFilter;
import com.huawei.health.ecologydevice.manager.ResourceManager;
import com.huawei.health.ecologydevice.ui.BaseFragment;
import com.huawei.health.ecologydevice.ui.measure.fragment.ProductIntroductionFragment;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwcloudmodel.callback.ICloudOperationResult;
import com.huawei.hwcloudmodel.model.CloudCommonReponse;
import com.huawei.hwcloudmodel.model.unite.RegisterInfo;
import com.huawei.hwcloudmodel.model.unite.WifiDeviceUnbindReq;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.utils.Constants;
import com.huawei.ui.commonui.button.HealthButton;
import com.huawei.ui.commonui.dialog.NoTitleCustomAlertDialog;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import defpackage.ceo;
import defpackage.cji;
import defpackage.cjx;
import defpackage.cpa;
import defpackage.cpp;
import defpackage.cpw;
import defpackage.cpz;
import defpackage.csf;
import defpackage.cst;
import defpackage.csw;
import defpackage.ctk;
import defpackage.ctn;
import defpackage.ctv;
import defpackage.cue;
import defpackage.cuh;
import defpackage.cui;
import defpackage.dcz;
import defpackage.dks;
import defpackage.jbs;
import defpackage.nsn;
import health.compact.a.GRSManager;
import health.compact.a.LanguageUtil;
import health.compact.a.UnitUtil;
import java.io.Serializable;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

/* loaded from: classes3.dex */
public class WiFiDeviceBindResultFragment extends BaseFragment implements View.OnClickListener {
    private static final int ANIMATION_DURATION = 1000;
    private static final int ANIMATION_END_VALUE = 3;
    private static final int DEVICE_WORK_TIMEOUT = 180000;
    private static final int FAIL_REGISTER_FOUR = 4;
    private static final int FAIL_REGISTER_ONE = 1;
    private static final int FAIL_REGISTER_THREE = 3;
    private static final int FAIL_REGISTER_TWO = 2;
    private static final int FAIL_SOFTAP_ONE = 1;
    private static final int FAIL_SOFTAP_THREE = 3;
    private static final int FAIL_SOFTAP_TWO = 2;
    private static final int IMAGE_LAYOUT_OF_SCREEN = 3;
    private static final int INIT_MULTCAST_ONE = 1;
    private static final int INIT_MULTCAST_TWO = 2;
    private static final int INIT_SOFTAP_VALUE = 1;
    private static final int MSG_DEVICE_BLE_CONFIG_NETWORK = 16;
    private static final int MSG_DEVICE_START_CONFIG = 15;
    private static final int MSG_DEVICE_WORK_SET_UNIT = 12;
    private static final int MSG_DEVICE_WORK_STATUS_BINDING = 13;
    private static final int MSG_DEVICE_WORK_STATUS_FAIL = 5;
    private static final int MSG_DEVICE_WORK_STATUS_INIT = 14;
    private static final int MSG_DEVICE_WORK_STATUS_PAIR_STATUS = 7;
    private static final int MSG_DEVICE_WORK_STATUS_START = 4;
    private static final int MSG_DEVICE_WORK_STATUS_SUCCESS = 6;
    private static final int MSG_DEVICE_WORK_STATUS_TIMEOUT = 8;
    private static final int PAIR_REGISTER_VALUE = 2;
    private static final int REQUEST_CODE_FOR_UNBIND = 101;
    private static final int RESULT_CODE_FOR_UNBIND = 102;
    private static final int SCAN_COUNT = 2;
    private static final String TAG = "WiFiDeviceBindResultFragment";
    private static String mUniqueId;
    private ctn mAddDeviceInfo;
    private cui mAddDeviceManager;
    private AnimationDrawable mAnimation;
    private DeviceCallBack mBaseCallback;
    private LinearLayout mBindFailBtn;
    private LinearLayout mBindFailLayout;
    private LinearLayout mBindFailLayoutButton;
    private HealthTextView mBindFailText;
    private LinearLayout mBindRetryBtn;
    private LinearLayout mBindSuccessLayout;
    private LinearLayout mBindSuccessLayoutButton;
    private LinearLayout mBindingLayout;
    private WiFiDeviceBindResultFragment mContext;
    private String mDeviceId;
    private ContentValues mDeviceInfo;
    private LinearLayout mDevicePairResultImageLayout;
    private float mDeviceWeight;
    private NoTitleCustomAlertDialog mDialog;
    private ImageView mErrorImg;
    private HealthTextView mFailNext;
    private MyHandler mHandler;
    private boolean mIsHagrid;
    private String mOuthName;
    private String mOuthPwd;
    private HealthTextView mPairingStage;
    private HealthTextView mPairingStageAnim;
    private String mProductId;
    private dcz mProductInfo;
    private ImageView mProgressView;
    private ScanCallback mScanCallback;
    private cuh mScanManager;
    private String mSerialNumber;
    private String mTitle;
    private HealthButton mUserInfoBtn;
    private ValueAnimator mValueAnimator;
    private int mConfigMode = -1;
    private int mCurrentStatus = 14;
    private String[] mStageLoadingList = {".", "..", "..."};
    private boolean mIsBleScale = false;
    private boolean isHonourDevice = false;
    private boolean mIsGotoUserInfoActivity = false;

    static class MyHandler extends StaticHandler<WiFiDeviceBindResultFragment> {
        MyHandler(WiFiDeviceBindResultFragment wiFiDeviceBindResultFragment) {
            super(wiFiDeviceBindResultFragment);
        }

        @Override // com.huawei.health.device.wifi.lib.handler.StaticHandler
        public void handleMessage(WiFiDeviceBindResultFragment wiFiDeviceBindResultFragment, Message message) {
            if (message == null) {
                LogUtil.h(WiFiDeviceBindResultFragment.TAG, "msg is null.");
                return;
            }
            if (wiFiDeviceBindResultFragment == null) {
                LogUtil.h(WiFiDeviceBindResultFragment.TAG, "object is null.");
                return;
            }
            LogUtil.a(WiFiDeviceBindResultFragment.TAG, "DeviceBind MyHandler what:", Integer.valueOf(message.what));
            if (wiFiDeviceBindResultFragment.isDestroy()) {
                return;
            }
            int i = message.arg1;
            wiFiDeviceBindResultFragment.mConfigMode = i;
            LogUtil.a(WiFiDeviceBindResultFragment.TAG, "DeviceBind MyHandler what:", Integer.valueOf(message.what), ", configMode: ", Integer.valueOf(i));
            int i2 = message.what;
            if (i2 == 4) {
                LogUtil.c(WiFiDeviceBindResultFragment.TAG, "msg ", "DEVICE_WORK_STATUS_START");
                wiFiDeviceBindResultFragment.mScanManager.a();
                wiFiDeviceBindResultFragment.updateStatus(i, 14);
                wiFiDeviceBindResultFragment.startConfigNetWork(wiFiDeviceBindResultFragment.mOuthName, wiFiDeviceBindResultFragment.mOuthPwd, wiFiDeviceBindResultFragment.mAddDeviceInfo);
                return;
            }
            if (i2 != 5) {
                if (i2 == 6) {
                    removeMessages(8);
                    wiFiDeviceBindResultFragment.setBiWifiDeviceBindSuccessCount();
                    wiFiDeviceBindResultFragment.updateStatus(i, 6);
                    return;
                } else if (i2 != 8) {
                    processHandler(wiFiDeviceBindResultFragment, message, i);
                    return;
                }
            }
            removeMessages(8);
            LogUtil.a(WiFiDeviceBindResultFragment.TAG, "MSG_DEVICE_WORK_STATUS_FAIL", ",errorCode:", Integer.valueOf(message.arg2));
            wiFiDeviceBindResultFragment.updateStatus(i, 5);
            if (wiFiDeviceBindResultFragment.mAddDeviceManager != null) {
                wiFiDeviceBindResultFragment.mAddDeviceManager.c();
            }
            if (wiFiDeviceBindResultFragment.mScanManager != null) {
                wiFiDeviceBindResultFragment.mScanManager.a();
            }
        }

        private void processHandler(WiFiDeviceBindResultFragment wiFiDeviceBindResultFragment, Message message, int i) {
            int i2 = message.what;
            LogUtil.a(WiFiDeviceBindResultFragment.TAG, "processHandler what:", Integer.valueOf(i2));
            if (i2 == 7) {
                if (message.arg2 == 2212) {
                    i = 3;
                }
                wiFiDeviceBindResultFragment.updateStatus(i, 7);
                return;
            }
            if (i2 == 12) {
                csf.d(wiFiDeviceBindResultFragment.mainActivity);
                return;
            }
            if (i2 == 13) {
                wiFiDeviceBindResultFragment.processingLogicEvent(i, 13, message.obj);
                return;
            }
            if (i2 == 15) {
                wiFiDeviceBindResultFragment.startConfig();
                return;
            }
            if (i2 == 16) {
                if (message.obj == null) {
                    LogUtil.b(WiFiDeviceBindResultFragment.TAG, "obj is null or obj is not instance WifiDevice");
                    return;
                }
                if (message.obj instanceof RegisterInfo) {
                    RegisterInfo registerInfo = (RegisterInfo) message.obj;
                    if (cji.a(WiFiDeviceBindResultFragment.mUniqueId, 24)) {
                        wiFiDeviceBindResultFragment.sendConfigInfoForHag2021(registerInfo);
                        return;
                    } else {
                        wiFiDeviceBindResultFragment.sendConfigInfo(registerInfo);
                        return;
                    }
                }
                return;
            }
            LogUtil.h(WiFiDeviceBindResultFragment.TAG, "message default");
        }
    }

    @Override // com.huawei.health.ecologydevice.ui.BaseFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        LogUtil.a(TAG, "onCreate");
        this.mHandler = new MyHandler(this);
        this.mContext = this;
    }

    @Override // com.huawei.health.ecologydevice.ui.BaseFragment, androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        LogUtil.a(TAG, "onCreateView");
        ViewGroup viewGroup2 = super.onCreateView(layoutInflater, viewGroup, bundle) instanceof ViewGroup ? (ViewGroup) super.onCreateView(layoutInflater, viewGroup, bundle) : null;
        this.child = layoutInflater.inflate(R.layout.device_wifi_bind_result_layout, viewGroup, false);
        if (viewGroup2 != null) {
            viewGroup2.addView(this.child);
        }
        initView();
        initData();
        initListener();
        if (this.mIsBleScale) {
            this.mHandler.removeCallbacksAndMessages(null);
            updateStatus(this.mConfigMode, 6);
        }
        return viewGroup2;
    }

    private void initView() {
        this.mBindingLayout = (LinearLayout) this.child.findViewById(R.id.id_device_wifi_binding_layout);
        this.mBindSuccessLayout = (LinearLayout) this.child.findViewById(R.id.id_device_bind_success_layout);
        this.mBindFailLayout = (LinearLayout) this.child.findViewById(R.id.id_device_bind_fail_layout);
        this.mBindSuccessLayoutButton = (LinearLayout) this.child.findViewById(R.id.id_device_bind_success_button);
        this.mBindFailLayoutButton = (LinearLayout) this.child.findViewById(R.id.id_device_bind_fail_button);
        this.mUserInfoBtn = (HealthButton) this.child.findViewById(R.id.id_btn_consum_userinfo);
        this.mFailNext = (HealthTextView) this.child.findViewById(R.id.wifi_device_bind_fail_next);
        this.mBindRetryBtn = (LinearLayout) this.child.findViewById(R.id.id_btn_retry);
        this.mBindFailBtn = (LinearLayout) this.child.findViewById(R.id.id_btn_bind_fail);
        this.mBindFailText = (HealthTextView) this.child.findViewById(R.id.id_tv_bind_fail);
        this.mPairingStage = (HealthTextView) this.child.findViewById(R.id.IDS_device_wifi_binding_promt_tv);
        this.mPairingStageAnim = (HealthTextView) this.child.findViewById(R.id.IDS_device_wifi_binding_promt_anim);
        this.mErrorImg = (ImageView) this.child.findViewById(R.id.pair_result_device_show_img);
        this.mDevicePairResultImageLayout = (LinearLayout) this.child.findViewById(R.id.pair_result_device_image_layout);
        ImageView imageView = (ImageView) this.child.findViewById(R.id.device_pair_guide_progress_anim);
        this.mProgressView = imageView;
        if (imageView.getDrawable() instanceof AnimationDrawable) {
            this.mAnimation = (AnimationDrawable) this.mProgressView.getDrawable();
        }
        this.mAnimation.start();
        this.mErrorImg.setVisibility(8);
        setTitle(getResources().getString(R.string.IDS_device_mgr_device_pair_guide_note));
        boolean bc = LanguageUtil.bc(BaseApplication.getContext());
        ImageView imageView2 = (ImageView) this.child.findViewById(R.id.wifi_binde_device_next_img);
        ImageView imageView3 = (ImageView) this.child.findViewById(R.id.wifi_device_bind_retry_img);
        int i = R.drawable.wifi_device_arrow_left;
        imageView2.setBackgroundResource(bc ? R.drawable.wifi_device_arrow_right : R.drawable.wifi_device_arrow_left);
        if (!bc) {
            i = R.drawable.wifi_device_arrow_right;
        }
        imageView3.setBackgroundResource(i);
        refreshDevicePairResultLayout();
    }

    private void initListener() {
        this.mUserInfoBtn.setOnClickListener(this);
        this.mBindRetryBtn.setOnClickListener(this);
        this.mBindFailBtn.setOnClickListener(this);
    }

    private void initData() {
        if (getArguments() != null) {
            ContentValues contentValues = (ContentValues) getArguments().getParcelable("commonDeviceInfo");
            this.mDeviceInfo = contentValues;
            if (contentValues != null) {
                this.mProductId = contentValues.getAsString("productId");
                mUniqueId = this.mDeviceInfo.getAsString("uniqueId");
            }
            if (TextUtils.isEmpty(this.mProductId) || TextUtils.isEmpty(mUniqueId)) {
                LogUtil.h(TAG, "initData mProductId or mUniqueId is empty");
            }
            this.mOuthName = getArguments().getString("outhName");
            this.mOuthPwd = getArguments().getString("outhPd");
            this.mIsBleScale = getArguments().getBoolean("isBleScale");
            this.mDeviceWeight = getArguments().getFloat("deviceWeight");
            this.mTitle = getArguments().getString("title");
            this.isHonourDevice = getArguments().getBoolean("isHonourDevice");
            this.mIsHagrid = getArguments().getBoolean("isHygirde");
            Serializable serializable = getArguments().getSerializable("add_device_info");
            if (serializable instanceof ctn) {
                this.mAddDeviceInfo = (ctn) serializable;
            }
            this.mConfigMode = getArguments().getInt("config_mode", 1);
        }
        this.mProductInfo = ResourceManager.e().d(this.mProductId);
        this.mAddDeviceManager = cui.b(cpp.a());
        this.mBaseCallback = new NetWorkCallback(this);
        this.mScanManager = cuh.e(this.mainActivity);
        this.mScanCallback = new ScanCallback(this);
        Message obtain = Message.obtain();
        obtain.what = 8;
        obtain.arg1 = this.mConfigMode;
        this.mHandler.sendMessageDelayed(obtain, 180000L);
    }

    private void startScanDevice() {
        if (checkHavePermission()) {
            dcz dczVar = this.mProductInfo;
            if (dczVar != null) {
                this.mScanManager.d(dczVar, 2, this.mScanCallback);
            } else {
                LogUtil.h(TAG, "startScan Get device information is fail");
            }
        }
    }

    private boolean checkHavePermission() {
        Object systemService = this.mainActivity.getSystemService("location");
        if (!(systemService instanceof LocationManager)) {
            LogUtil.h(TAG, "checkHavePermission objLocationManager is not instanceof LocationManager");
            return false;
        }
        if (ctv.Mg_(this.mainActivity, (LocationManager) systemService)) {
            return true;
        }
        NoTitleCustomAlertDialog noTitleCustomAlertDialog = this.mDialog;
        if (noTitleCustomAlertDialog != null && noTitleCustomAlertDialog.isShowing()) {
            return false;
        }
        String string = getResources().getString(R.string.IDS_device_wifi_gps_service_prompt_msg);
        NoTitleCustomAlertDialog.Builder builder = new NoTitleCustomAlertDialog.Builder(this.mainActivity);
        builder.e(string);
        builder.czC_(R.string._2130842176_res_0x7f021240, new View.OnClickListener() { // from class: com.huawei.health.device.ui.measure.fragment.WiFiDeviceBindResultFragment.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LogUtil.a(WiFiDeviceBindResultFragment.TAG, "showGpsDialog():click setting button");
                Intent intent = new Intent();
                intent.setAction("android.settings.LOCATION_SOURCE_SETTINGS");
                try {
                    ctv.Mj_(WiFiDeviceBindResultFragment.this.mainActivity, intent);
                } catch (ActivityNotFoundException unused) {
                    intent.setAction("android.settings.SETTINGS");
                    try {
                        ctv.Mj_(WiFiDeviceBindResultFragment.this.mainActivity, intent);
                    } catch (Exception unused2) {
                        LogUtil.h(WiFiDeviceBindResultFragment.TAG, "checkHavePermission occurred startActivity exception");
                    }
                }
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        builder.czz_(R.string._2130841130_res_0x7f020e2a, new View.OnClickListener() { // from class: com.huawei.health.device.ui.measure.fragment.WiFiDeviceBindResultFragment.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                WiFiDeviceBindResultFragment.this.release();
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        NoTitleCustomAlertDialog e = builder.e();
        this.mDialog = e;
        e.setCancelable(false);
        this.mDialog.show();
        return false;
    }

    @Override // androidx.fragment.app.Fragment
    public void onStart() {
        LogUtil.a(TAG, "onStart: ", Integer.valueOf(this.mCurrentStatus));
        super.onStart();
        Message obtain = Message.obtain();
        obtain.what = 15;
        obtain.arg1 = this.mConfigMode;
        this.mHandler.sendMessage(obtain);
    }

    private void unBindDevice(String str) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.h(TAG, "unBindDevice deviceid is empty");
            return;
        }
        WifiDeviceUnbindReq wifiDeviceUnbindReq = new WifiDeviceUnbindReq();
        wifiDeviceUnbindReq.setDevId(str);
        LogUtil.a(TAG, "unBindDevice :", cpw.d(str));
        jbs.a(cpp.a()).c(wifiDeviceUnbindReq, new ICloudOperationResult<CloudCommonReponse>() { // from class: com.huawei.health.device.ui.measure.fragment.WiFiDeviceBindResultFragment.3
            @Override // com.huawei.hwcloudmodel.callback.ICloudOperationResult
            public void operationResult(CloudCommonReponse cloudCommonReponse, String str2, boolean z) {
                int i;
                String str3;
                LogUtil.a(WiFiDeviceBindResultFragment.TAG, "unBindDevice :", Boolean.valueOf(z));
                if (z) {
                    return;
                }
                if (cloudCommonReponse != null) {
                    i = cloudCommonReponse.getResultCode().intValue();
                    str3 = cloudCommonReponse.getResultDesc();
                } else {
                    i = Constants.CODE_UNKNOWN_ERROR;
                    str3 = "unknown error";
                }
                if (i != 112000000) {
                    Message obtain = Message.obtain();
                    obtain.what = 5;
                    obtain.arg1 = WiFiDeviceBindResultFragment.this.mConfigMode;
                    WiFiDeviceBindResultFragment.this.mHandler.sendMessage(obtain);
                }
                LogUtil.a(WiFiDeviceBindResultFragment.TAG, " unBindDevice error:", Integer.valueOf(i), ",resultDesc:", str3);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startConfig() {
        if (this.mCurrentStatus == 14) {
            int i = this.mConfigMode;
            if (i == 1) {
                updateStatus(i, 14);
                startScanDevice();
            } else {
                if (i == 2 || i == 5) {
                    Message obtain = Message.obtain();
                    obtain.what = 4;
                    obtain.arg1 = this.mConfigMode;
                    this.mHandler.sendMessage(obtain);
                    return;
                }
                LogUtil.h(TAG, " startConfig configMode: ", Integer.valueOf(i));
            }
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        LogUtil.a(TAG, "onclick ID", Integer.valueOf(view.getId()));
        if (view.getId() == R.id.id_btn_consum_userinfo) {
            this.mIsGotoUserInfoActivity = true;
            if (this.mIsBleScale) {
                onClickBleScaleUserInfo();
            } else {
                onClickUserInfo();
            }
        } else if (view.getId() == R.id.id_btn_retry) {
            onClickRetry();
        } else if (view.getId() == R.id.id_btn_bind_fail) {
            onBackPressed();
        } else {
            LogUtil.h(TAG, "onclick else");
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    @Override // com.huawei.health.ecologydevice.ui.BaseFragment, androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        if (this.mIsGotoUserInfoActivity) {
            if (this.mIsBleScale) {
                if (this.mainActivity instanceof DeviceMainActivity) {
                    ((DeviceMainActivity) this.mainActivity).c(true);
                }
                jumpToHonourDeviceFragment();
            } else {
                if (cue.a(this.mProductId) && cpa.ad(this.mProductId)) {
                    if (this.mainActivity instanceof DeviceMainActivity) {
                        ((DeviceMainActivity) this.mainActivity).c(true);
                    }
                    toDeviceManageFragment();
                    return;
                }
                popupFragment(WiFiProductIntroductionFragment.class);
            }
        }
    }

    private void onClickBleScaleUserInfo() {
        LogUtil.a(TAG, "onClickBleScaleUserInfo");
        if (this.mContext != null) {
            Intent intent = new Intent();
            intent.putExtra("device", "wifi");
            intent.putExtra("isBleScale", this.mIsBleScale);
            intent.putExtra("productId", this.mProductId);
            intent.putExtra("deviceWeight", this.mDeviceWeight);
            ContentValues contentValues = new ContentValues();
            contentValues.put("productId", this.mProductId);
            if ("7a1063dd-0e0f-4a72-9939-461476ff0259".equals(this.mProductId) || "34fa0346-d46c-439d-9cb0-2f696618846b".equals(this.mProductId) || "33123f39-7fc1-420b-9882-a4b0d6c61100".equals(this.mProductId) || "ccd1f0f8-8c57-4bd7-a884-0ef38482f15f".equals(this.mProductId)) {
                contentValues.put("uniqueId", mUniqueId);
            } else {
                contentValues.put("uniqueId", this.mDeviceId);
            }
            intent.putExtra("commonDeviceInfo", contentValues);
            intent.setClassName(BaseApplication.getAppPackage(), "com.huawei.ui.main.stories.me.activity.BindUserInfoActivity");
            this.mContext.startActivityForResult(intent, 101);
        }
    }

    private void onClickUserInfo() {
        LogUtil.a(TAG, "onClickUserInfo");
        if (this.mContext != null) {
            Intent intent = new Intent();
            intent.putExtra("device", "wifi");
            intent.putExtra("productId", this.mProductId);
            intent.putExtra("auth_device_id", this.mDeviceId);
            intent.putExtra("sn", this.mSerialNumber);
            LogUtil.a(TAG, "onClickUserInfo mSerialNumber:", cpw.d(this.mSerialNumber));
            ContentValues contentValues = new ContentValues();
            contentValues.put("productId", this.mProductId);
            contentValues.put("uniqueId", this.mDeviceId);
            intent.putExtra("commonDeviceInfo", contentValues);
            intent.setClassName(BaseApplication.getAppPackage(), "com.huawei.ui.main.stories.me.activity.BindUserInfoActivity");
            this.mContext.startActivity(intent);
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == 101 && i2 == 102) {
            popupFragment(null);
        }
    }

    private void onClickRetry() {
        LogUtil.a(TAG, "onClickRetry");
        popupFragment(WiFiInfoConfirmFragment.class);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateStatus(int i, int i2) {
        this.mCurrentStatus = i2;
        LogUtil.a(TAG, "updateStatus：", Integer.valueOf(i2), ", configMode:", Integer.valueOf(i));
        if (i2 == 7) {
            setTitle(getResources().getString(R.string.IDS_device_mgr_device_pair_guide_note));
            this.mBindingLayout.setVisibility(0);
            this.mBindSuccessLayout.setVisibility(8);
            this.mBindFailLayout.setVisibility(8);
            if (i == 3) {
                showStage(getResources().getString(R.string.IDS_device_wifi_binding_stage3, UnitUtil.e(2.0d, 1, 0), UnitUtil.e(2.0d, 1, 0)));
                return;
            }
            return;
        }
        if (i2 == 5) {
            setTitle(getResources().getString(R.string.IDS_device_wifi_bind_fail));
            this.mBindingLayout.setVisibility(8);
            this.mBindSuccessLayout.setVisibility(8);
            this.mBindFailLayout.setVisibility(0);
            this.mBindFailLayoutButton.setVisibility(0);
            this.mErrorImg.setVisibility(0);
            this.mErrorImg.setImageResource(R.drawable._2131429993_res_0x7f0b0a69);
            this.mFailNext.setText(R.string._2130841130_res_0x7f020e2a);
            if (i == 1 || i == 3) {
                handleMultcastOrRegisterMode();
            } else if (i == 2) {
                handleSoftApMode();
            } else {
                LogUtil.h(TAG, "updateStatus MSG_DEVICE_WORK_STATUS_FAIL default");
            }
            ValueAnimator valueAnimator = this.mValueAnimator;
            if (valueAnimator != null) {
                valueAnimator.end();
            }
            AnimationDrawable animationDrawable = this.mAnimation;
            if (animationDrawable != null) {
                animationDrawable.stop();
            }
            this.mProgressView.setImageResource(R.mipmap._2131820611_res_0x7f110043);
            this.mProgressView.setVisibility(0);
            return;
        }
        processOtherBindType(i, i2);
    }

    private void processOtherBindType(int i, int i2) {
        if (i2 != 6) {
            if (i2 != 14) {
                LogUtil.h(TAG, "updateStatus default");
                return;
            }
            if (i == 1) {
                showStage(getResources().getString(R.string.IDS_device_wifi_binding_stage2, UnitUtil.e(1.0d, 1, 0), UnitUtil.e(2.0d, 1, 0)));
                return;
            } else if (i == 2) {
                this.mPairingStage.setText(getResources().getString(R.string.IDS_device_wifi_binding_stage2, UnitUtil.e(1.0d, 1, 0), UnitUtil.e(1.0d, 1, 0)));
                return;
            } else {
                LogUtil.h(TAG, "updateStatus MSG_DEVICE_WORK_STATUS_INIT default");
                return;
            }
        }
        setTitle(getResources().getString(R.string._2130841385_res_0x7f020f29));
        this.mBindingLayout.setVisibility(8);
        this.mBindSuccessLayout.setVisibility(0);
        this.mBindSuccessLayoutButton.setVisibility(0);
        this.mBindFailLayout.setVisibility(8);
        this.mErrorImg.setVisibility(0);
        this.mErrorImg.setImageResource(R.drawable.device_jabra_bind_success_icon);
        ValueAnimator valueAnimator = this.mValueAnimator;
        if (valueAnimator != null) {
            valueAnimator.end();
        }
        AnimationDrawable animationDrawable = this.mAnimation;
        if (animationDrawable != null) {
            animationDrawable.stop();
        }
        this.mProgressView.setImageResource(R.mipmap._2131820611_res_0x7f110043);
        this.mProgressView.setVisibility(0);
    }

    private void handleSoftApMode() {
        this.mBindFailText.setText(new SpannableStringBuilder(getResources().getString(R.string.IDS_device_wifi_bind_fail_title) + System.lineSeparator() + String.format(Locale.ENGLISH, getResources().getString(R.string.IDS_device_wifi_bind_manual_fail_prompt_1), UnitUtil.e(1.0d, 1, 0)) + System.lineSeparator() + String.format(Locale.ENGLISH, nsn.d(this.mainActivity, R.string.IDS_device_wifi_bind_auto_fail_prompt_2), UnitUtil.e(2.0d, 1, 0)) + System.lineSeparator() + getResources().getString(R.string.IDS_device_wifi_bound_device_inconsistent, 3)));
        this.mFailNext.setText(R.string.IDS_device_wifi_bind_auto);
    }

    private void handleMultcastOrRegisterMode() {
        this.mBindFailText.setText(new SpannableStringBuilder(getResources().getString(R.string.IDS_device_wifi_bind_fail_title) + System.lineSeparator() + String.format(Locale.ENGLISH, getResources().getString(R.string.IDS_device_wifi_pair_fail_1), UnitUtil.e(1.0d, 1, 0)) + System.lineSeparator() + String.format(Locale.ENGLISH, nsn.d(this.mainActivity, R.string.IDS_device_wifi_pair_fail_2), UnitUtil.e(2.0d, 1, 0)) + System.lineSeparator() + String.format(Locale.ENGLISH, getResources().getString(R.string.IDS_device_wifi_pair_fail_3), UnitUtil.e(3.0d, 1, 0)) + System.lineSeparator() + getResources().getString(R.string.IDS_device_wifi_bound_device_inconsistent, 4)));
        this.mFailNext.setText(R.string._2130841130_res_0x7f020e2a);
    }

    private boolean compareDevice(ScanFilter scanFilter, String str) {
        if (scanFilter == null || TextUtils.isEmpty(str)) {
            LogUtil.h(TAG, "compareDevice scanFilter is null || TextUtils.isEmpty(productId)");
            return false;
        }
        if (scanFilter.a(str)) {
            return true;
        }
        LogUtil.h(TAG, "compareDevice productId error: ", str);
        return false;
    }

    private boolean isBindSuccess(ctk ctkVar) {
        if (this.mAddDeviceInfo != null) {
            return true;
        }
        dcz dczVar = this.mProductInfo;
        if (dczVar == null || ctkVar == null) {
            Object[] objArr = new Object[2];
            objArr[0] = "isBindSuccess proid is null or mDevice is null: ";
            objArr[1] = Boolean.valueOf(ctkVar != null);
            LogUtil.h(TAG, objArr);
            return false;
        }
        return compareDevice(dczVar.w(), ctkVar.b().f());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendConfigInfoForHag2021(RegisterInfo registerInfo) {
        this.mAddDeviceInfo.e(registerInfo.getDevId());
        HashMap hashMap = new HashMap(16);
        hashMap.put("ssid", this.mOuthName);
        hashMap.put("password", this.mOuthPwd);
        hashMap.put("devId", registerInfo.getDevId());
        hashMap.put("psk", registerInfo.getPsk());
        hashMap.put("code", registerInfo.getVerifyCode());
        String url = GRSManager.a(BaseApplication.getContext()).getUrl("hilinkDevicePrimaryCloudUrl");
        hashMap.put("cloudPrimaryUrl", url);
        String url2 = GRSManager.a(BaseApplication.getContext()).getUrl("hilinkDeviceStandbyCloudUrl");
        hashMap.put("cloudStandbyUrl", url2);
        String url3 = GRSManager.a(BaseApplication.getContext()).getUrl("healthAPPToDeviceUrl");
        hashMap.put("cloudUrl", url3);
        LogUtil.a(TAG, "sendConfigInfo ssid=", cpw.d(this.mOuthName), ", ssidPwd=", cpw.d(this.mOuthPwd), ", cloudPrimaryUrl=", url, ", cloudStandbyUrl=", url2, ", cloudUrl=", url3);
        String json = new Gson().toJson(hashMap);
        Bundle bundle = new Bundle();
        bundle.putString("configjson", json);
        EventBus.d(new EventBus.b("send_config_info_hag_2021", bundle));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendConfigInfo(RegisterInfo registerInfo) {
        csw cswVar = new csw();
        cswVar.a(registerInfo.getDevId());
        this.mAddDeviceInfo.e(registerInfo.getDevId());
        cswVar.b(registerInfo.getVerifyCode());
        cswVar.d(GRSManager.a(BaseApplication.getContext()).getUrl("healthAPPToDeviceUrl"));
        cswVar.e(registerInfo.getPsk());
        LogUtil.c(TAG, " softApRegisterInfo = ", cswVar);
        String json = new Gson().toJson(cswVar, csw.class);
        Bundle bundle = new Bundle();
        bundle.putString("ssid", this.mOuthName);
        bundle.putString("pwd", this.mOuthPwd);
        bundle.putInt("encryptMode", this.mAddDeviceInfo.a());
        bundle.putString("deviceSsid", this.mAddDeviceInfo.f());
        bundle.putString("registerMsg", json);
        EventBus.d(new EventBus.b("send_config_info", bundle));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void processingLogicEvent(int i, int i2, Object obj) {
        if (obj == null || !(obj instanceof ctk)) {
            LogUtil.h(TAG, "processingLogicEvent obj is null or obj is not instance WifiDevice");
            return;
        }
        if (i2 == 13) {
            ctk ctkVar = (ctk) obj;
            if (this.mProductId != null) {
                if (!isBindSuccess(ctkVar)) {
                    LogUtil.a(TAG, "processingLogicEvent bind device is not need device :", ctkVar.b().toString());
                    Message obtain = Message.obtain();
                    obtain.what = 5;
                    obtain.arg1 = i;
                    this.mHandler.sendMessage(obtain);
                    unBindDevice(ctkVar.d());
                    return;
                }
                ctkVar.setProductId(this.mProductId);
                ctkVar.setKind(HealthDevice.HealthDeviceKind.HDK_WEIGHT);
                ctkVar.b().d(1);
                this.mDeviceId = ctkVar.b().a();
                LogUtil.a(TAG, "processingLogicEvent deviceDetailInfo:", ctkVar.toString());
                mUniqueId = ctkVar.getUniqueId();
                this.mSerialNumber = ctkVar.b().m();
                boolean z = ceo.d().c(ctkVar.getSerialNumber(), false) != null;
                dcz dczVar = this.mProductInfo;
                String s = dczVar != null ? dczVar.s() : "";
                if (z) {
                    updateWifiDevice(i, this.mProductId, s, ctkVar);
                } else {
                    saveWifiDevice(i, this.mProductId, s, ctkVar);
                }
            }
        }
    }

    private void updateWifiDevice(final int i, String str, String str2, ctk ctkVar) {
        cjx.e().a(str, str2, ctkVar, new IDeviceEventHandler() { // from class: com.huawei.health.device.ui.measure.fragment.WiFiDeviceBindResultFragment.4
            @Override // com.huawei.health.device.callback.IDeviceEventHandler
            public void onDeviceFound(HealthDevice healthDevice) {
            }

            @Override // com.huawei.health.device.callback.IDeviceEventHandler
            public void onScanFailed(int i2) {
            }

            @Override // com.huawei.health.device.callback.IDeviceEventHandler
            public void onStateChanged(int i2) {
                LogUtil.a(WiFiDeviceBindResultFragment.TAG, "updateWifiDevice onStateChanged code:", Integer.valueOf(i2));
                WiFiDeviceBindResultFragment.this.wifiDeviceStateChanged(i, i2);
            }
        });
    }

    private void saveWifiDevice(final int i, String str, String str2, ctk ctkVar) {
        cjx.e().b(str, str2, ctkVar, new IDeviceEventHandler() { // from class: com.huawei.health.device.ui.measure.fragment.WiFiDeviceBindResultFragment.5
            @Override // com.huawei.health.device.callback.IDeviceEventHandler
            public void onDeviceFound(HealthDevice healthDevice) {
            }

            @Override // com.huawei.health.device.callback.IDeviceEventHandler
            public void onScanFailed(int i2) {
            }

            @Override // com.huawei.health.device.callback.IDeviceEventHandler
            public void onStateChanged(int i2) {
                LogUtil.a(WiFiDeviceBindResultFragment.TAG, "saveWifiDevice onStateChanged code:", Integer.valueOf(i2));
                WiFiDeviceBindResultFragment.this.wifiDeviceStateChanged(i, i2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void wifiDeviceStateChanged(int i, int i2) {
        if (i2 == 7) {
            Message obtain = Message.obtain();
            obtain.what = 6;
            obtain.arg1 = i;
            this.mHandler.sendMessage(obtain);
            return;
        }
        if (i2 == 8) {
            cjx.e().o(mUniqueId);
            Message obtain2 = Message.obtain();
            obtain2.what = 5;
            obtain2.arg1 = i;
            this.mHandler.sendMessage(obtain2);
            return;
        }
        LogUtil.h(TAG, "wifiDevice onStateChanged not passed or failed");
    }

    class NetWorkCallback implements DeviceCallBack {
        private WeakReference<WiFiDeviceBindResultFragment> mActivity;

        NetWorkCallback(WiFiDeviceBindResultFragment wiFiDeviceBindResultFragment) {
            this.mActivity = new WeakReference<>(wiFiDeviceBindResultFragment);
        }

        @Override // com.huawei.health.device.wifi.interfaces.DeviceCallBack
        public void onSuccess(int i, Object obj) {
            if (i == 4) {
                i = 1;
            }
            int i2 = i == 5 ? 16 : 13;
            if (isDestroy()) {
                Message obtain = Message.obtain();
                obtain.what = i2;
                obtain.arg1 = i;
                obtain.obj = obj;
                WiFiDeviceBindResultFragment.this.mHandler.sendMessage(obtain);
                WiFiDeviceBindResultFragment.this.mHandler.sendEmptyMessage(12);
            }
        }

        @Override // com.huawei.health.device.wifi.interfaces.DeviceCallBack
        public void onFailure(int i, int i2) {
            if (i == 4) {
                i = 1;
            }
            if (isDestroy()) {
                Message obtain = Message.obtain();
                obtain.what = 5;
                obtain.arg1 = i;
                obtain.arg2 = i2;
                WiFiDeviceBindResultFragment.this.mHandler.sendMessage(obtain);
            }
        }

        @Override // com.huawei.health.device.wifi.interfaces.DeviceCallBack
        public void onStatus(int i, int i2) {
            if (i == 4) {
                i = 1;
            }
            if (isDestroy()) {
                Message obtain = Message.obtain();
                obtain.what = 7;
                obtain.arg1 = i;
                obtain.arg2 = i2;
                WiFiDeviceBindResultFragment.this.mHandler.sendMessage(obtain);
            }
        }

        private boolean isDestroy() {
            WeakReference<WiFiDeviceBindResultFragment> weakReference = this.mActivity;
            if (weakReference == null) {
                return false;
            }
            WiFiDeviceBindResultFragment wiFiDeviceBindResultFragment = weakReference.get();
            WiFiDeviceBindResultFragment wiFiDeviceBindResultFragment2 = wiFiDeviceBindResultFragment instanceof WiFiDeviceBindResultFragment ? wiFiDeviceBindResultFragment : null;
            return (wiFiDeviceBindResultFragment2 == null || wiFiDeviceBindResultFragment2.isDestroy()) ? false : true;
        }
    }

    public void startConfigNetWork(String str, String str2, ctn ctnVar) {
        int i = this.mConfigMode;
        if (i == 2) {
            LogUtil.a(TAG, "startConfigNetWork SOFTAP_MODE");
            this.mAddDeviceManager.a(this.mConfigMode, str, str2, ctnVar, this.mBaseCallback);
            return;
        }
        if (i != 1) {
            if (i == 3) {
                LogUtil.a(TAG, "startConfigNetWork REGISTER_MODE:", Integer.valueOf(i));
                this.mAddDeviceInfo = ctnVar;
                this.mAddDeviceManager.d(ctnVar, this.mBaseCallback);
                return;
            } else {
                if (i == 5) {
                    LogUtil.a(TAG, "startConfigNetWork REGISTER_MODE:", Integer.valueOf(i));
                    this.mAddDeviceInfo = ctnVar;
                    this.mAddDeviceManager.b(DeviceRegisterManager.RegisterMode.REGISTER_BLE, this.mAddDeviceInfo, this.mBaseCallback);
                    return;
                }
                LogUtil.a(TAG, "startConfigNetWork config mode is: ", Integer.valueOf(i));
                return;
            }
        }
        if (ctnVar == null) {
            if (TextUtils.isEmpty(this.mProductId)) {
                LogUtil.h(TAG, "startConfigNetWork mProductId is null");
                return;
            } else if (this.mProductInfo == null) {
                LogUtil.h(TAG, "startConfigNetWork mProductinfo is null");
                return;
            } else {
                LogUtil.a(TAG, "startConfigNetWork HAND_ADD_WIFIAP_MODE");
                this.mAddDeviceManager.d(str, str2, this.mProductInfo.w(), this.mProductId, this.mBaseCallback);
                return;
            }
        }
        LogUtil.a(TAG, "startConfigNetWork MULTCAST_MODE");
        this.mAddDeviceManager.a(this.mConfigMode, str, str2, ctnVar, this.mBaseCallback);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isDestroy() {
        FragmentActivity activity = getActivity();
        if (activity == null || activity.isFinishing() || activity.isDestroyed()) {
            LogUtil.b(TAG, "DeviceMainActivity is Destroyed");
            return true;
        }
        if (isAdded()) {
            return false;
        }
        LogUtil.b(TAG, "MyHandler mFragment is not add");
        return false;
    }

    @Override // com.huawei.health.ecologydevice.ui.BaseFragment
    public boolean onBackPressed() {
        if (this.mCurrentStatus == 6) {
            showConfirmInfoAndDisplay();
            return false;
        }
        popupFragment(WiFiProductIntroductionFragment.class);
        return false;
    }

    @Override // com.huawei.health.ecologydevice.ui.BaseFragment, androidx.fragment.app.Fragment
    public void onDestroy() {
        cst.c(this.mainActivity);
        super.onDestroy();
        cui cuiVar = this.mAddDeviceManager;
        if (cuiVar != null) {
            cuiVar.c();
        }
        MyHandler myHandler = this.mHandler;
        if (myHandler != null) {
            myHandler.removeCallbacksAndMessages(null);
        }
        cuh cuhVar = this.mScanManager;
        if (cuhVar != null) {
            cuhVar.a();
        }
        ValueAnimator valueAnimator = this.mValueAnimator;
        if (valueAnimator != null) {
            valueAnimator.cancel();
        }
        AnimationDrawable animationDrawable = this.mAnimation;
        if (animationDrawable != null) {
            animationDrawable.stop();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setBiWifiDeviceBindSuccessCount() {
        cpz.e(mUniqueId, this.mProductInfo);
    }

    private void showConfirmInfoAndDisplay() {
        LogUtil.a(TAG, "showConfirmInfoAndDisplay() enter");
        NoTitleCustomAlertDialog.Builder builder = new NoTitleCustomAlertDialog.Builder(this.mainActivity);
        builder.e(R.string.IDS_device_wifi_confirm_info_and_display).czz_(R.string._2130841130_res_0x7f020e2a, new View.OnClickListener() { // from class: com.huawei.health.device.ui.measure.fragment.WiFiDeviceBindResultFragment.7
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ViewClickInstrumentation.clickOnView(view);
            }
        }).czC_(R.string._2130841378_res_0x7f020f22, new View.OnClickListener() { // from class: com.huawei.health.device.ui.measure.fragment.WiFiDeviceBindResultFragment.6
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LogUtil.a(WiFiDeviceBindResultFragment.TAG, "showConfirmInfoAndDisplay mIsBleScale:", Boolean.valueOf(WiFiDeviceBindResultFragment.this.mIsBleScale));
                if (WiFiDeviceBindResultFragment.this.mIsBleScale) {
                    WiFiDeviceBindResultFragment.this.jumpToHonourDeviceFragment();
                } else {
                    if (!TextUtils.isEmpty(WiFiDeviceBindResultFragment.this.mDeviceId)) {
                        csf.c(WiFiDeviceBindResultFragment.this.mDeviceId, false);
                    } else {
                        LogUtil.h(WiFiDeviceBindResultFragment.TAG, "showConfirmInfoAndDisplay() deviceid is null");
                    }
                    if (cue.a(WiFiDeviceBindResultFragment.this.mProductId) && cpa.ad(WiFiDeviceBindResultFragment.this.mProductId)) {
                        WiFiDeviceBindResultFragment.this.toDeviceManageFragment();
                    } else {
                        WiFiDeviceBindResultFragment.this.popupFragment(WiFiProductIntroductionFragment.class);
                    }
                }
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        builder.e().show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void toDeviceManageFragment() {
        dks.e();
        Bundle bundle = new Bundle();
        ContentValues contentValues = new ContentValues();
        contentValues.put("uniqueId", mUniqueId);
        contentValues.put("productId", this.mProductId);
        bundle.putParcelable("commonDeviceInfo", contentValues);
        bundle.putString("productId", this.mProductId);
        Fragment wiFiProductIntroductionFragment = new WiFiProductIntroductionFragment();
        wiFiProductIntroductionFragment.setArguments(bundle);
        switchFragment((Fragment) null, wiFiProductIntroductionFragment);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void jumpToHonourDeviceFragment() {
        if (this.isHonourDevice) {
            if (!cpa.q(this.mProductId)) {
                popupFragment(ProductIntroductionFragment.class);
                LogUtil.a(TAG, "jumpToHonourDeviceFragment switchFragment mProductId=", this.mProductId);
                Bundle bundle = new Bundle();
                bundle.putString("productId", this.mProductId);
                bundle.putBoolean("isBindSuccess", true);
                bundle.putBoolean("isCorrectProductId", false);
                bundle.putString("title", this.mTitle);
                ContentValues contentValues = new ContentValues();
                contentValues.put("productId", this.mProductId);
                contentValues.put("uniqueId", this.mDeviceId);
                bundle.putParcelable("commonDeviceInfo", contentValues);
                Fragment productIntroductionFragment = new ProductIntroductionFragment();
                productIntroductionFragment.setArguments(bundle);
                switchFragment(productIntroductionFragment);
                return;
            }
            LogUtil.a(TAG, "jumpToHonourDeviceFragment popupFragment mProductId=", this.mProductId);
            popupFragment(ProductIntroductionFragment.class);
            return;
        }
        if (this.mIsHagrid) {
            Bundle bundle2 = new Bundle();
            bundle2.putString("productId", this.mProductId);
            ContentValues contentValues2 = new ContentValues();
            contentValues2.put("productId", this.mProductId);
            contentValues2.put("uniqueId", this.mDeviceId);
            bundle2.putParcelable("commonDeviceInfo", contentValues2);
            bundle2.putString("goto", "devicebind");
            bundle2.putString("title", this.mTitle);
            Fragment hagridDeviceManagerFragment = new HagridDeviceManagerFragment();
            hagridDeviceManagerFragment.setArguments(bundle2);
            switchFragment(hagridDeviceManagerFragment);
            return;
        }
        Bundle bundle3 = new Bundle();
        bundle3.putString("productId", this.mProductId);
        ContentValues contentValues3 = new ContentValues();
        contentValues3.put("productId", this.mProductId);
        contentValues3.put("uniqueId", this.mDeviceId);
        bundle3.putParcelable("commonDeviceInfo", contentValues3);
        bundle3.putString("goto", "devicebind");
        bundle3.putString("title", this.mTitle);
        Fragment honourDeviceFragment = new HonourDeviceFragment();
        honourDeviceFragment.setArguments(bundle3);
        switchFragment(honourDeviceFragment);
    }

    private void showStage(String str) {
        this.mPairingStage.setText(str);
        ValueAnimator valueAnimator = this.mValueAnimator;
        if (valueAnimator == null) {
            ValueAnimator duration = ValueAnimator.ofInt(0, 3).setDuration(1000L);
            this.mValueAnimator = duration;
            duration.setRepeatCount(-1);
            this.mValueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.huawei.health.device.ui.measure.fragment.WiFiDeviceBindResultFragment.8
                @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                public void onAnimationUpdate(ValueAnimator valueAnimator2) {
                    WiFiDeviceBindResultFragment.this.mPairingStageAnim.setText(WiFiDeviceBindResultFragment.this.mStageLoadingList[((Integer) valueAnimator2.getAnimatedValue()).intValue() % WiFiDeviceBindResultFragment.this.mStageLoadingList.length]);
                }
            });
            if (this.mValueAnimator.isRunning()) {
                return;
            }
            this.mValueAnimator.start();
            return;
        }
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.huawei.health.device.ui.measure.fragment.WiFiDeviceBindResultFragment.9
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator valueAnimator2) {
                WiFiDeviceBindResultFragment.this.mPairingStageAnim.setText(WiFiDeviceBindResultFragment.this.mStageLoadingList[((Integer) valueAnimator2.getAnimatedValue()).intValue() % WiFiDeviceBindResultFragment.this.mStageLoadingList.length]);
            }
        });
        if (this.mValueAnimator.isRunning()) {
            return;
        }
        this.mValueAnimator.start();
    }

    static class ScanCallback extends ScanDeviceCallback<WiFiDeviceBindResultFragment> {
        @Override // com.huawei.health.device.wifi.interfaces.ScanDeviceCallback
        public /* bridge */ /* synthetic */ void onDeviceDiscovered(WiFiDeviceBindResultFragment wiFiDeviceBindResultFragment, List list) {
            onDeviceDiscovered2(wiFiDeviceBindResultFragment, (List<ctn>) list);
        }

        ScanCallback(WiFiDeviceBindResultFragment wiFiDeviceBindResultFragment) {
            super(wiFiDeviceBindResultFragment);
        }

        /* renamed from: onDeviceDiscovered, reason: avoid collision after fix types in other method */
        public void onDeviceDiscovered2(WiFiDeviceBindResultFragment wiFiDeviceBindResultFragment, List<ctn> list) {
            ArrayList<ctn> removeRepeatDevice = wiFiDeviceBindResultFragment.removeRepeatDevice(list);
            LogUtil.a(WiFiDeviceBindResultFragment.TAG, "onDeviceDiscovered deviceInfo ", Integer.valueOf(list.size()), " mDevices:", Integer.valueOf(removeRepeatDevice.size()));
            if (removeRepeatDevice.size() > 0) {
                wiFiDeviceBindResultFragment.mScanManager.a();
                wiFiDeviceBindResultFragment.mAddDeviceInfo = removeRepeatDevice.get(0);
                Message obtain = Message.obtain();
                obtain.what = 4;
                obtain.arg1 = 1;
                wiFiDeviceBindResultFragment.mHandler.sendMessage(obtain);
            }
        }

        @Override // com.huawei.health.device.wifi.interfaces.ScanDeviceCallback
        public void onDeviceDiscoveryFinished(WiFiDeviceBindResultFragment wiFiDeviceBindResultFragment) {
            LogUtil.a(WiFiDeviceBindResultFragment.TAG, "onDeviceDiscoveryFinished ");
            if (wiFiDeviceBindResultFragment.mHandler != null) {
                Message obtain = Message.obtain();
                obtain.what = 4;
                obtain.arg1 = 1;
                wiFiDeviceBindResultFragment.mHandler.sendMessage(obtain);
            }
        }

        @Override // com.huawei.health.device.wifi.interfaces.ScanDeviceCallback
        public void onFailure(WiFiDeviceBindResultFragment wiFiDeviceBindResultFragment, Object obj) {
            LogUtil.a(WiFiDeviceBindResultFragment.TAG, "onFailure scan");
            if (wiFiDeviceBindResultFragment.mHandler != null) {
                Message obtain = Message.obtain();
                obtain.what = 4;
                obtain.arg1 = 1;
                wiFiDeviceBindResultFragment.mHandler.sendMessage(obtain);
            }
        }
    }

    public ArrayList<ctn> removeRepeatDevice(List<ctn> list) {
        ArrayList<ctn> arrayList = new ArrayList<>(16);
        for (ctn ctnVar : list) {
            String f = ctnVar.f();
            String g = ctnVar.g();
            String i = ctnVar.i();
            LogUtil.a(TAG, "removeRepeatDevice ssid: ", f, ", proId: ", g, ", type: ", i);
            if (TextUtils.isEmpty(f) || TextUtils.isEmpty(g) || TextUtils.isEmpty(i)) {
                LogUtil.h(TAG, "removeRepeatDevice ssid,productId,type is null");
            } else if (!"wifiap".equals(ctnVar.i())) {
                LogUtil.h(TAG, "removeRepeatDevice source type is other ");
            } else {
                if (arrayList.size() > 0) {
                    Iterator<ctn> it = arrayList.iterator();
                    while (it.hasNext()) {
                        ctn next = it.next();
                        if (next == null || !f.equals(next.f()) || !next.i().equals(i)) {
                        }
                    }
                }
                arrayList.add(ctnVar);
            }
        }
        LogUtil.a(TAG, "removeRepeatDevice all add info is ", arrayList);
        return arrayList;
    }

    @Override // com.huawei.health.ecologydevice.ui.BaseFragment, androidx.fragment.app.Fragment, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LogUtil.a(TAG, "onConfigurationChanged, refresh page");
        refreshDevicePairResultLayout();
        super.onConfigurationChanged(configuration);
    }

    private void refreshDevicePairResultLayout() {
        LinearLayout linearLayout = this.mDevicePairResultImageLayout;
        if (linearLayout != null) {
            nsn.cLA_(linearLayout, 3);
        }
    }
}
