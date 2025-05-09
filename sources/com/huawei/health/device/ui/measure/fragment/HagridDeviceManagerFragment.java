package com.huawei.health.device.ui.measure.fragment;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.content.ActivityNotFoundException;
import android.content.ContentValues;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Message;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import com.google.gson.Gson;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.R;
import com.huawei.health.device.callback.ScaleDialogCallback;
import com.huawei.health.device.connectivity.comm.MeasurableDevice;
import com.huawei.health.device.fatscale.multiusers.MultiUsersManager;
import com.huawei.health.device.model.HealthDevice;
import com.huawei.health.device.ui.DeviceMainActivity;
import com.huawei.health.device.ui.measure.adapter.HagridDeviceManagerAdapter;
import com.huawei.health.device.ui.measure.adapter.WeightResultConfirmAdapter;
import com.huawei.health.device.ui.privacy.HonorDevicePrivacyActivity;
import com.huawei.health.device.ui.util.DeviceManagerInfoHandler;
import com.huawei.health.device.util.EventBus;
import com.huawei.health.device.wifi.control.claim.ClaimWeightDataManager;
import com.huawei.health.device.wifi.interfaces.CommBaseCallback;
import com.huawei.health.devicemgr.api.mainprocess.DownloadManagerApi;
import com.huawei.health.devicemgr.business.entity.downloadresouce.DownloadResultCallBack;
import com.huawei.health.ecologydevice.manager.ResourceManager;
import com.huawei.health.ecologydevice.ui.BaseFragment;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hms.support.api.entity.pay.HwPayConstant;
import com.huawei.hwcloudmodel.callback.ICloudOperationResult;
import com.huawei.hwcloudmodel.model.CloudCommonReponse;
import com.huawei.hwcloudmodel.model.unite.WifiDeviceGetSubUserAuthMsgReq;
import com.huawei.hwcloudmodel.model.unite.WifiDeviceGetSubUserAuthMsgRsp;
import com.huawei.hwcloudmodel.model.unite.WifiDeviceShareMemberInfoBySubUserReq;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.utils.PermissionUtil;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.openalliance.ad.constant.ParamConstants;
import com.huawei.profile.profile.ProfileExtendConstants;
import com.huawei.ui.commonui.checkbox.HealthCheckBox;
import com.huawei.ui.commonui.dialog.CommonDialog21;
import com.huawei.ui.commonui.dialog.CustomViewDialog;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.popupview.PopViewList;
import com.huawei.ui.commonui.scrollview.HealthScrollView;
import com.huawei.ui.commonui.utils.CustomPermissionAction;
import defpackage.cek;
import defpackage.ceo;
import defpackage.cfi;
import defpackage.cgt;
import defpackage.ckq;
import defpackage.cld;
import defpackage.clf;
import defpackage.cmp;
import defpackage.cmu;
import defpackage.cmv;
import defpackage.cnb;
import defpackage.cnc;
import defpackage.cnn;
import defpackage.cnu;
import defpackage.cof;
import defpackage.coy;
import defpackage.coz;
import defpackage.cpa;
import defpackage.cpe;
import defpackage.cpp;
import defpackage.cpw;
import defpackage.cpy;
import defpackage.cpz;
import defpackage.cqa;
import defpackage.cqb;
import defpackage.cqh;
import defpackage.crw;
import defpackage.csc;
import defpackage.csf;
import defpackage.ctk;
import defpackage.ctq;
import defpackage.cub;
import defpackage.cud;
import defpackage.cxh;
import defpackage.dcq;
import defpackage.dcx;
import defpackage.dcz;
import defpackage.dfg;
import defpackage.dij;
import defpackage.dks;
import defpackage.ixj;
import defpackage.jbs;
import defpackage.njn;
import defpackage.nrh;
import defpackage.nsf;
import defpackage.nsn;
import defpackage.nsy;
import health.compact.a.CommonUtil;
import health.compact.a.CompileParameterUtil;
import health.compact.a.GRSManager;
import health.compact.a.LanguageUtil;
import health.compact.a.LocalBroadcast;
import health.compact.a.Services;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;
import health.compact.a.UnitUtil;
import health.compact.a.Utils;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes3.dex */
public class HagridDeviceManagerFragment extends BaseFragment implements View.OnClickListener, DownloadResultCallBack {
    private static final String[] EVENT_SUBSCRIBE_LIST = {"get_device_ssid_result", "manager_info_success", "manager_info_failed", "set_weight_unit_result", "device_reset_result", "get_weight_unit_result", "wifi_scale_auth_refresh", "sub_user_unauthorize_notification", "multi_user_auto_cancle_dialog", "event_bus_sub_user_exit_device", "event_bus_config_wifi"};
    private static final String TAG = "HagridDeviceManagerFragment";
    private static final String TAG_RELEASE = "R_Weight_HagridDeviceManagerFragment";
    private boolean hadCheckUpdate;
    private HagridDeviceManagerAdapter mAdapter;
    private HealthTextView mAddUserView;
    private clf mConditionBeforeRequestHandler;
    private HealthTextView mConnectStatusPromptMessage;
    private HealthTextView mConnectStatusTextView;
    private ContentValues mContentValues;
    private CustomViewDialog mCustomViewDialog;
    private String mDevId;
    private ImageView mDeviceImg;
    private cmu mEventManager;
    private String mGoto;
    private RelativeLayout mGuestMeasureLayout;
    private RelativeLayout mHagrideUserGuideLayout;
    private RelativeLayout mHagrideWeightDataClaimLayout;
    private RelativeLayout mHagrideWeightDataManagerLayout;
    private byte[] mHuid;
    private boolean mIsClickDeviceVersionUpdateItem;
    private boolean mIsNfcConnectScales;
    private boolean mIsWaitForAccountInfo;
    private ListView mItemListView;
    private HealthDevice.HealthDeviceKind mKind;
    private CommonDialog21 mLoadingDialog;
    private PopViewList mPopView;
    private String mProductId;
    private dcz mProductInfo;
    private HealthScrollView mScrollView;
    private ImageView mShowMoreDataArrowImage;
    private LinearLayout mShowMoreDataLayout;
    private HealthTextView mStartMeasureTextView;
    private String mUniqueId;
    private String mUrl;
    private WeightResultConfirmAdapter mUserAdapter;
    private LinearLayout mUserLayout;
    private ListView mUserListView;
    private ImageView mWeightDataClaimImg;
    private ImageView mWeightDataClaimRedPointImage;
    private HealthTextView mWeightDataClaimText;
    private RelativeLayout mWeightDataFromSmartLifeLayout;
    private HealthTextView mWeightDeviceConnectStatus;
    private cld mWeightInteractor;
    private LinearLayout mWeightLayoutContainer;
    private LinearLayout mWeightValueLayoutContainer;
    private ctk mWiFiDevice;
    private cmv myHandler;
    private String mAccountInfo = null;
    private CountDownTimer mLoadingConditionTimer = new CountDownTimer(6000, 1000) { // from class: com.huawei.health.device.ui.measure.fragment.HagridDeviceManagerFragment.1
        @Override // android.os.CountDownTimer
        public void onTick(long j) {
        }

        @Override // android.os.CountDownTimer
        public void onFinish() {
            LogUtil.h(HagridDeviceManagerFragment.TAG_RELEASE, "mLoadingConditionTimer: receive account info timeout");
            if (HagridDeviceManagerFragment.this.mConditionBeforeRequestHandler != null) {
                HagridDeviceManagerFragment.this.mConditionBeforeRequestHandler.sendEmptyMessage(10);
            }
        }
    };
    private boolean mIsMainUser = false;
    private int mGetHuidType = 0;
    private int mDownloadStatus = -1;
    private CommBaseCallback commBaseCallback = new CommBaseCallback<HagridDeviceManagerFragment>(this) { // from class: com.huawei.health.device.ui.measure.fragment.HagridDeviceManagerFragment.2
        @Override // com.huawei.health.device.wifi.interfaces.CommBaseCallback
        public void onResult(HagridDeviceManagerFragment hagridDeviceManagerFragment, int i, String str, Object obj) {
            boolean z = false;
            boolean z2 = (hagridDeviceManagerFragment == null || hagridDeviceManagerFragment.isDestory()) ? false : true;
            if (hagridDeviceManagerFragment != null) {
                if (hagridDeviceManagerFragment.isAdded() && hagridDeviceManagerFragment.myHandler != null) {
                    z = true;
                }
                if (z2 && z) {
                    hagridDeviceManagerFragment.myHandler.sendEmptyMessage(1);
                }
            }
        }
    };

    public boolean getIsClickDeviceVersionUpdateItem() {
        return this.mIsClickDeviceVersionUpdateItem;
    }

    public void setAccountInfo(String str) {
        this.mAccountInfo = str;
    }

    public CountDownTimer getLoadingConditionTimer() {
        return this.mLoadingConditionTimer;
    }

    public cmv getManagerHandler() {
        return this.myHandler;
    }

    @Override // com.huawei.health.devicemgr.business.entity.downloadresouce.DownloadResultCallBack
    public void setDownloadStatus(int i, int i2) {
        this.mDownloadStatus = i;
        if (i == -2) {
            nrh.c(BaseApplication.getContext(), R.string._2130844161_res_0x7f021a01);
            ((DownloadManagerApi) Services.c("DownloadDeviceResource", DownloadManagerApi.class)).onDownloadIndexAllDestroy();
        } else if (i != 0) {
            LogUtil.a(TAG, "setDownloadStatus mDownloadStatus: ", Integer.valueOf(i));
            ((DownloadManagerApi) Services.c("DownloadDeviceResource", DownloadManagerApi.class)).onDownloadIndexAllDestroy();
        }
    }

    public void switchToSubUserWifiPage() {
        LogUtil.a(TAG, "switchToSubUserWifiPage");
        if (this.mAdapter == null) {
            LogUtil.h(TAG_RELEASE, "switchToSubUserWifiPage mAdapter is null.");
            return;
        }
        MeasurableDevice d = ceo.d().d(this.mUniqueId, false);
        if (d != null) {
            if (d instanceof ctk) {
                this.mWiFiDevice = (ctk) d;
                this.mAdapter.d(14, createSettingItem(12, getViewString(R.string.IDS_device_user_manager), "", ""));
                ClaimWeightDataManager.INSTANCE.registerCallBack(getClass().getSimpleName(), this.commBaseCallback);
                return;
            }
            LogUtil.h(TAG, "switchToSubUserWifiPage, bond device isn't wifi device");
            return;
        }
        LogUtil.h(TAG, "switchToSubUserWifiPage, no bond device");
    }

    public void connectSuccess() {
        checkUpdateVersion();
        LogUtil.a(TAG, "connectSuccess");
        if (this.mConnectStatusTextView == null || this.mConnectStatusPromptMessage == null || this.mainActivity == null) {
            return;
        }
        LogUtil.a(TAG, "connectSuccess update text status");
        LogUtil.a(TAG, "connectSuccess mConnectStatusTextView is ", this.mConnectStatusTextView);
        this.mConnectStatusTextView.setText(this.mainActivity.getString(R.string._2130841442_res_0x7f020f62));
        this.mConnectStatusPromptMessage.setVisibility(8);
    }

    @Override // com.huawei.health.ecologydevice.ui.BaseFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        LogUtil.a(TAG, "onCreate");
        this.myHandler = new cmv(this);
        this.mConditionBeforeRequestHandler = new clf(this, this.mainActivity);
        cmu cmuVar = new cmu();
        this.mEventManager = cmuVar;
        EventBus.d(cmuVar, 0, EVENT_SUBSCRIBE_LIST);
        this.mEventManager.c(this.myHandler, this, this.mUniqueId, this.mProductId);
        downloadIndexAllResources();
    }

    @Override // com.huawei.health.ecologydevice.ui.BaseFragment, androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        LogUtil.a(TAG, "onCreateView");
        ViewGroup viewGroup2 = super.onCreateView(layoutInflater, viewGroup, bundle) instanceof ViewGroup ? (ViewGroup) super.onCreateView(layoutInflater, viewGroup, bundle) : null;
        this.child = layoutInflater.inflate(R.layout.hygride_product_manager_layout, viewGroup, false);
        if (viewGroup2 != null) {
            viewGroup2.addView(this.child);
        }
        initView();
        initData();
        initViewData();
        setListener();
        cld d = cld.d(this.mProductId, this.mUniqueId);
        this.mWeightInteractor = d;
        d.HN_(this.mainActivity, this.child);
        initManager();
        initUnitType();
        if (cqb.d().b(this.mUniqueId)) {
            LogUtil.a(TAG, "init WeightInteractor and init Handler");
            this.mWeightInteractor.a();
        } else {
            this.mWeightDeviceConnectStatus.setText(getViewString(R.string._2130840619_res_0x7f020c2b));
            this.mStartMeasureTextView.setText(getViewString(R.string.IDS_device_start_paring_title));
        }
        if (getActivity() instanceof DeviceMainActivity) {
            ((DeviceMainActivity) getActivity()).c(HagridDeviceManagerFragment.class);
        }
        Bundle arguments = getArguments();
        if (arguments != null && "Delete".equals(arguments.getString("operateCode"))) {
            cnc.b().Je_(this.mainActivity);
        }
        if (!TextUtils.isEmpty(this.mUniqueId)) {
            cpa.at(this.mUniqueId);
        }
        return viewGroup2;
    }

    private void checkUpdateVersion() {
        if (this.hadCheckUpdate) {
            return;
        }
        this.hadCheckUpdate = true;
        Intent intent = new Intent("action_start_check_scale");
        intent.putExtra("commonDeviceInfo", this.mContentValues);
        cpp.a().sendBroadcast(intent, LocalBroadcast.c);
    }

    private void initManager() {
        cnn.b().b(this.mUniqueId, this.mProductId, this.mWeightInteractor);
        cnn.b().c(this.myHandler);
        this.mEventManager.c(this.myHandler, this, this.mUniqueId, this.mProductId);
        cnc.b().IY_(this.mUniqueId, this.mProductId, this.mainActivity, this.myHandler, this);
        this.mWeightInteractor.b(this.myHandler);
    }

    private void downloadIndexAllResources() {
        if (cpe.c()) {
            ((DownloadManagerApi) Services.c("DownloadDeviceResource", DownloadManagerApi.class)).addDownloadIndexAllCallBack(this);
            ((DownloadManagerApi) Services.c("DownloadDeviceResource", DownloadManagerApi.class)).downloadIndexAll();
        } else {
            this.mDownloadStatus = 1;
        }
    }

    public void gotoHagridDeviceWlanUseGuideFragment() {
        LogUtil.a(TAG, "gotoHagridDeviceWlanUseGuideFragment");
        Bundle bundle = new Bundle();
        ContentValues contentValues = this.mContentValues;
        if (contentValues == null) {
            contentValues = new ContentValues();
        }
        if (!contentValues.containsKey("productId")) {
            contentValues.put("productId", this.mProductId);
            contentValues.put("uniqueId", this.mUniqueId);
        }
        bundle.putParcelable("commonDeviceInfo", contentValues);
        bundle.putString("productId", this.mProductId);
        bundle.putString("cloudDeviceId", this.mDevId);
        bundle.putParcelable("commonDeviceInfo", this.mContentValues);
        bundle.putByteArray("mainHuid", this.mHuid);
        bundle.putInt(ParamConstants.Param.VIEW_TYPE, 13);
        Fragment selectFragment = getSelectFragment(HagridDeviceWlanUseGuideFragment.class);
        Fragment fragment = selectFragment instanceof BaseFragment ? (BaseFragment) selectFragment : null;
        if (!(fragment instanceof HagridDeviceWlanUseGuideFragment)) {
            fragment = new HagridDeviceWlanUseGuideFragment();
        }
        if (this.mAccountInfo != null) {
            LogUtil.a(TAG, "gotoHagridDeviceWlanUseGuideFragment mAccountInfo is not null");
            bundle.putString("accountInfo", this.mAccountInfo);
        }
        fragment.setArguments(bundle);
        switchFragment(fragment);
    }

    private void clickRequestDeviceShareItem() {
        if (nsn.o()) {
            LogUtil.h(TAG, "clickRequestDeviceShareItem click too fast.");
            return;
        }
        if (!(ceo.d().d(this.mUniqueId, true) instanceof cxh)) {
            showSelectBindDeviceDialog();
            return;
        }
        if (isBluetoothConnect()) {
            this.mIsClickDeviceVersionUpdateItem = false;
            showLoadingDialog(getString(R.string._2130841415_res_0x7f020f47));
            LogUtil.a(TAG, "clickRequestDeviceShareItem mGetHuidType:", Integer.valueOf(this.mGetHuidType));
            if (this.mGetHuidType != 1) {
                startLoadingConditionTimer();
                return;
            }
            if (!this.mIsMainUser) {
                destroyLoadingDialog();
                byte[] bArr = this.mHuid;
                if (bArr == null || bArr.length == 0) {
                    LogUtil.a(TAG, "is only ble device goto config wifi");
                    configWifi();
                    return;
                } else {
                    LogUtil.a(TAG, "clickRequestDeviceShareItem: wifi sub user");
                    dealClickDeviceShare(new DeviceManagerInfoHandler());
                    return;
                }
            }
            LogUtil.h(TAG, "clickRequestDeviceShareItem: main user");
            destroyLoadingDialog();
            return;
        }
        LogUtil.a(TAG, "clickRequestDeviceShareItem click toast not connect");
        nrh.c(BaseApplication.getContext(), R.string.IDS_plugin_device_weight_device_not_connect);
    }

    private void dealClickDeviceShare(DeviceManagerInfoHandler deviceManagerInfoHandler) {
        byte[] bArr = this.mHuid;
        if (bArr == null || bArr.length == 0) {
            deviceManagerInfoHandler.e(new DeviceManagerInfoHandler.GetManagerAccountInfoListener() { // from class: com.huawei.health.device.ui.measure.fragment.HagridDeviceManagerFragment$$ExternalSyntheticLambda4
                @Override // com.huawei.health.device.ui.util.DeviceManagerInfoHandler.GetManagerAccountInfoListener
                public final void managerAccountInfo(String str) {
                    HagridDeviceManagerFragment.this.m154x5b22c281(str);
                }
            });
            deviceManagerInfoHandler.a();
        } else {
            this.myHandler.sendEmptyMessage(15);
        }
    }

    /* renamed from: lambda$dealClickDeviceShare$0$com-huawei-health-device-ui-measure-fragment-HagridDeviceManagerFragment, reason: not valid java name */
    /* synthetic */ void m154x5b22c281(String str) {
        LogUtil.a(TAG, "managerAccountInfo account: ", str);
        if (TextUtils.isEmpty(str)) {
            str = "";
        }
        this.mAccountInfo = str;
        cmv cmvVar = this.myHandler;
        if (cmvVar != null) {
            cmvVar.sendEmptyMessage(15);
        }
    }

    private void startLoadingConditionTimer() {
        CountDownTimer countDownTimer = this.mLoadingConditionTimer;
        if (countDownTimer != null && this.mGetHuidType == 3) {
            countDownTimer.cancel();
            this.mLoadingConditionTimer.start();
        } else {
            if (countDownTimer != null) {
                this.myHandler.sendEmptyMessage(5);
                this.mLoadingConditionTimer.cancel();
                this.mLoadingConditionTimer.start();
                return;
            }
            LogUtil.h(TAG, "startLoadingConditionTimer mGetHuidType is ", Integer.valueOf(this.mGetHuidType));
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
        LogUtil.a(TAG_RELEASE, "showLoadingDialog: mLoadingDialog is not null");
    }

    public void destroyLoadingDialog() {
        CommonDialog21 commonDialog21 = this.mLoadingDialog;
        if (commonDialog21 != null) {
            commonDialog21.cancel();
            this.mLoadingDialog = null;
        }
    }

    private boolean checkWiFiIsOpen() {
        Activity activity = BaseApplication.getActivity();
        if (this.mainActivity == null && (activity instanceof DeviceMainActivity)) {
            LogUtil.a(TAG, "mainActivity is null, get top activity");
            this.mainActivity = activity;
        }
        if (cub.d(this.mainActivity)) {
            return true;
        }
        LogUtil.a(TAG, "checkWiFiIsOpen checkWifiStatus false");
        coy.c(this.mainActivity);
        return false;
    }

    private void setListener() {
        this.mStartMeasureTextView.setOnClickListener(this);
        this.mHagrideUserGuideLayout.setOnClickListener(this);
        this.mHagrideWeightDataClaimLayout.setOnClickListener(this);
        this.mHagrideWeightDataManagerLayout.setOnClickListener(this);
        this.mGuestMeasureLayout.setOnClickListener(this);
        this.mShowMoreDataLayout.setOnClickListener(this);
        this.mItemListView.setOnItemClickListener(new AdapterView.OnItemClickListener() { // from class: com.huawei.health.device.ui.measure.fragment.HagridDeviceManagerFragment$$ExternalSyntheticLambda8
            @Override // android.widget.AdapterView.OnItemClickListener
            public final void onItemClick(AdapterView adapterView, View view, int i, long j) {
                HagridDeviceManagerFragment.this.m161xaeeed031(adapterView, view, i, j);
            }
        });
        setTouchListener();
    }

    /* renamed from: lambda$setListener$1$com-huawei-health-device-ui-measure-fragment-HagridDeviceManagerFragment, reason: not valid java name */
    /* synthetic */ void m161xaeeed031(AdapterView adapterView, View view, int i, long j) {
        HagridDeviceManagerAdapter hagridDeviceManagerAdapter = this.mAdapter;
        if (hagridDeviceManagerAdapter == null) {
            ViewClickInstrumentation.clickOnListView(adapterView, view, i);
            return;
        }
        cnu item = hagridDeviceManagerAdapter.getItem(i);
        if (item == null) {
            LogUtil.h(TAG, "setListener onItemClick item is null");
            ViewClickInstrumentation.clickOnListView(adapterView, view, i);
            return;
        }
        int e = item.e();
        LogUtil.a(TAG, "clickRequestDeviceShareItem click item is ", Integer.valueOf(e));
        if (cqh.c().KT_(this.mainActivity, this.mUniqueId)) {
            ViewClickInstrumentation.clickOnListView(adapterView, view, i);
        } else {
            onItemCLick(e);
            ViewClickInstrumentation.clickOnListView(adapterView, view, i);
        }
    }

    private void onItemCLick(int i) {
        if (i == 6) {
            otaUpdate();
            return;
        }
        if (i == 9) {
            cnc.b().d(this.mUniqueId, this.mWeightInteractor, this);
            return;
        }
        if (i == 12) {
            cnb.IT_(this.mainActivity, this.mUniqueId, this.mWiFiDevice, this.mContentValues, this);
            return;
        }
        if (i == 13) {
            clickWifiItem();
            return;
        }
        if (i == 1) {
            cnb.d(this.mainActivity, this.mUniqueId, this.mProductId, this.mUrl, this);
            return;
        }
        if (i == 15) {
            cnn.b().e(this);
            return;
        }
        if (i == 14) {
            if (CommonUtil.aa(this.mainActivity) || checkWiFiIsOpen()) {
                LogUtil.a(TAG, "onItemClick Check network is connected");
                clickRequestDeviceShareItem();
                return;
            } else {
                LogUtil.h(TAG, "onItemClick network is not connected");
                return;
            }
        }
        if (i == 18) {
            cmp.e().IK_(this.mWeightInteractor, this.mWiFiDevice, this.mainActivity);
            return;
        }
        if (i == 19) {
            betaFeedback();
            return;
        }
        if (i == 20) {
            if (nsn.o()) {
                ReleaseLogUtil.d(TAG_RELEASE, "help & customer click too fast.");
                return;
            }
            if (cek.e() && !ixj.b().a() && !ixj.b().h()) {
                ixj.b().bCO_(false, null);
            }
            GRSManager.a(this.mainActivity).e("helpCustomerUrl", new cek(this));
            return;
        }
        LogUtil.h(TAG_RELEASE, "onItemClick other.");
    }

    private void betaFeedback() {
        if (!(ceo.d().d(this.mUniqueId, true) instanceof cxh)) {
            LogUtil.a(TAG, "betaFeedback showSelectBindDeviceDialog");
            showSelectBindDeviceDialog();
        } else {
            cmp.e().a();
        }
    }

    private void setTouchListener() {
        this.mItemListView.setOnTouchListener(new View.OnTouchListener() { // from class: com.huawei.health.device.ui.measure.fragment.HagridDeviceManagerFragment$$ExternalSyntheticLambda2
            @Override // android.view.View.OnTouchListener
            public final boolean onTouch(View view, MotionEvent motionEvent) {
                return HagridDeviceManagerFragment.this.m162x601d8cfb(view, motionEvent);
            }
        });
    }

    /* renamed from: lambda$setTouchListener$2$com-huawei-health-device-ui-measure-fragment-HagridDeviceManagerFragment, reason: not valid java name */
    /* synthetic */ boolean m162x601d8cfb(View view, MotionEvent motionEvent) {
        this.mScrollView.requestDisallowInterceptTouchEvent(false);
        return false;
    }

    private boolean isBluetoothConnect() {
        cld cldVar = this.mWeightInteractor;
        if (cldVar != null) {
            return cldVar.b();
        }
        return false;
    }

    private void otaUpdate() {
        if (nsn.o()) {
            LogUtil.h(TAG, "otaUpdate click too fast.");
            return;
        }
        if (cpa.ah(this.mProductId)) {
            if (this.mWeightInteractor != null) {
                LogUtil.a(TAG, "otaUpdate mini OTA update");
                this.mWeightInteractor.controller(6, null);
                return;
            } else {
                LogUtil.h(TAG, "otaUpdate mWeightInteractor is null");
                return;
            }
        }
        if (!(ceo.d().d(this.mUniqueId, true) instanceof cxh)) {
            LogUtil.a(TAG, "otaUpdate showSelectBindDeviceDialog");
            showSelectBindDeviceDialog();
            return;
        }
        if (isBluetoothConnect()) {
            if (this.mWiFiDevice != null) {
                if (!CommonUtil.aa(this.mainActivity)) {
                    coy.c(this.mainActivity);
                    return;
                } else if (this.mWiFiDevice.b().k() == 1) {
                    upgradeForWiFi();
                    return;
                } else {
                    LogUtil.a(TAG, "Configured network but not main user.");
                    startVersionActivity(false);
                    return;
                }
            }
            upgradeForBluetooth();
            return;
        }
        LogUtil.a(TAG, "otaUpdate toast not connect");
        nrh.b(BaseApplication.getContext(), R.string.IDS_plugin_device_weight_device_not_connect);
    }

    private void upgradeForWiFi() {
        ReleaseLogUtil.e(TAG_RELEASE, "upgradeForWiFi in");
        Map<String, String> e = csc.d().e(this.mWiFiDevice.d());
        if (e != null && "2".equals(e.get("status"))) {
            if (csc.a(this.mWiFiDevice.d())) {
                e.put("status", "1");
                csc.d().d(this.mWiFiDevice.d(), e);
                LogUtil.h(TAG_RELEASE, "upgradeForWiFi Upgrade timed out");
            } else {
                LogUtil.a(TAG, "upgradeForWiFi during upgrade");
                Bundle bundle = new Bundle();
                bundle.putParcelable("commonDeviceInfo", this.mContentValues);
                bundle.putBoolean("fromProductView", true);
                bundle.putString("version", e.get("fwNewVer"));
                bundle.putString("cer_version", e.get("fwCurVer"));
                bundle.putString("update_nodes", e.get("releaseNote"));
                HagridWiFiOtaUpdateFragment hagridWiFiOtaUpdateFragment = new HagridWiFiOtaUpdateFragment();
                hagridWiFiOtaUpdateFragment.setArguments(bundle);
                switchFragment(hagridWiFiOtaUpdateFragment);
                return;
            }
        }
        csc.d().c(this.mWiFiDevice.d(), false);
        HagridWifiProductUpgradeFragment hagridWifiProductUpgradeFragment = new HagridWifiProductUpgradeFragment();
        Bundle bundle2 = new Bundle();
        bundle2.putParcelable("commonDeviceInfo", this.mContentValues);
        bundle2.putBoolean("fromProductView", true);
        hagridWifiProductUpgradeFragment.setArguments(bundle2);
        switchFragment(hagridWifiProductUpgradeFragment);
    }

    private void upgradeForBluetooth() {
        HagridDeviceManagerAdapter hagridDeviceManagerAdapter = this.mAdapter;
        if (hagridDeviceManagerAdapter == null) {
            LogUtil.h(TAG, "otaUpdate click updateItme mAdapter is null");
            return;
        }
        cnu a2 = hagridDeviceManagerAdapter.a(6);
        if (a2 == null || !a2.f()) {
            LogUtil.h(TAG, "otaUpdate click updateItem is not enable");
            return;
        }
        boolean z = true;
        this.mIsClickDeviceVersionUpdateItem = true;
        showLoadingDialog(getString(R.string._2130841415_res_0x7f020f47));
        LogUtil.a(TAG, "upgradeForBluetooth mGetHuidType:", Integer.valueOf(this.mGetHuidType), ",mIsWaitForAccountInfo:", Boolean.valueOf(this.mIsWaitForAccountInfo));
        if (this.mGetHuidType != 1 && this.mIsWaitForAccountInfo) {
            startLoadingConditionTimer();
            return;
        }
        destroyLoadingDialog();
        byte[] bArr = this.mHuid;
        boolean z2 = bArr == null || bArr.length == 0;
        if (!this.mIsMainUser && !z2) {
            z = false;
        }
        startVersionActivity(z);
    }

    public void clickWifiItem() {
        if (nsn.o()) {
            LogUtil.h(TAG, "clickWifiItem click too fast.");
            return;
        }
        if (!(ceo.d().d(this.mUniqueId, true) instanceof cxh)) {
            showSelectBindDeviceDialog();
            return;
        }
        if (isBluetoothConnect()) {
            HagridDeviceManagerAdapter hagridDeviceManagerAdapter = this.mAdapter;
            if (hagridDeviceManagerAdapter == null) {
                LogUtil.h(TAG, "clickWifiItem mAdapter is null");
                return;
            }
            cnu a2 = hagridDeviceManagerAdapter.a(13);
            if (a2 != null && a2.f()) {
                configWifi();
                return;
            } else {
                LogUtil.h(TAG_RELEASE, "clickWifiItem is not enable");
                return;
            }
        }
        LogUtil.a(TAG, "clickWifiItem click toast not connect.");
        nrh.d(BaseApplication.getContext(), getViewString(R.string.IDS_plugin_device_weight_device_not_connect));
    }

    public void configWifi() {
        Fragment wiFiInfoConfirmFragment;
        if (!checkWiFiIsOpen()) {
            LogUtil.h(TAG, "kWiFi is not Open");
            return;
        }
        LogUtil.a(TAG, "configWifi and wifi is Open");
        String b = SharedPreferenceManager.b(BaseApplication.getContext(), String.valueOf(10000), "8358eb90-b40d-11e9-a2a3-2a2ae2dbcce4");
        Bundle bundle = new Bundle();
        bundle.putParcelable("commonDeviceInfo", this.mContentValues);
        bundle.putString("deviceSsid", b);
        if (cpa.x(this.mProductId)) {
            wiFiInfoConfirmFragment = new HagridWiFiInfoConfirmFragment();
        } else {
            wiFiInfoConfirmFragment = new WiFiInfoConfirmFragment();
        }
        wiFiInfoConfirmFragment.setArguments(bundle);
        switchFragment(wiFiInfoConfirmFragment);
    }

    private void refreshUnreadMsgNum() {
        ctk ctkVar = this.mWiFiDevice;
        if (ctkVar == null || ctkVar.b().k() != 1) {
            LogUtil.a(TAG_RELEASE, "refreshUnreadMsgNum: not manager");
            return;
        }
        WifiDeviceGetSubUserAuthMsgReq wifiDeviceGetSubUserAuthMsgReq = new WifiDeviceGetSubUserAuthMsgReq();
        wifiDeviceGetSubUserAuthMsgReq.setDevId(this.mDevId);
        jbs.a(BaseApplication.getContext()).c(wifiDeviceGetSubUserAuthMsgReq, new ICloudOperationResult() { // from class: com.huawei.health.device.ui.measure.fragment.HagridDeviceManagerFragment$$ExternalSyntheticLambda6
            @Override // com.huawei.hwcloudmodel.callback.ICloudOperationResult
            public final void operationResult(Object obj, String str, boolean z) {
                HagridDeviceManagerFragment.this.m160x6b445fb6((CloudCommonReponse) obj, str, z);
            }
        });
    }

    /* renamed from: lambda$refreshUnreadMsgNum$3$com-huawei-health-device-ui-measure-fragment-HagridDeviceManagerFragment, reason: not valid java name */
    /* synthetic */ void m160x6b445fb6(CloudCommonReponse cloudCommonReponse, String str, boolean z) {
        LogUtil.a(TAG, "wifiDeviceGetSubUserAuthMsg request result: ", Boolean.valueOf(z));
        if (!z || TextUtils.isEmpty(str)) {
            return;
        }
        WifiDeviceGetSubUserAuthMsgRsp wifiDeviceGetSubUserAuthMsgRsp = (WifiDeviceGetSubUserAuthMsgRsp) new Gson().fromJson(str, WifiDeviceGetSubUserAuthMsgRsp.class);
        if (wifiDeviceGetSubUserAuthMsgRsp == null) {
            LogUtil.h(TAG_RELEASE, "wifiDeviceGetSubUserAuthMsg return fail: authMsgRsp is null");
            return;
        }
        Message obtain = Message.obtain();
        obtain.what = 7;
        obtain.arg1 = cud.a(wifiDeviceGetSubUserAuthMsgRsp.getAuthMsgs());
        if (this.myHandler == null) {
            this.myHandler = new cmv(this);
        }
        this.myHandler.sendMessage(obtain);
    }

    private void initView() {
        this.mScrollView = (HealthScrollView) nsy.cMd_(this.child, R.id.hygride_product_manager_scroll_view);
        ListView listView = (ListView) nsy.cMd_(this.child, R.id.list_device_setting);
        this.mItemListView = listView;
        if (listView != null) {
            listView.setSelector(new ColorDrawable(0));
        }
        this.mDeviceImg = (ImageView) nsy.cMd_(this.child, R.id.device_img);
        this.mWeightDeviceConnectStatus = (HealthTextView) nsy.cMd_(this.child, R.id.weight_device_connect_status_tv);
        this.mStartMeasureTextView = (HealthTextView) nsy.cMd_(this.child, R.id.weight_start_measure_tv);
        this.mHagrideUserGuideLayout = (RelativeLayout) nsy.cMd_(this.child, R.id.hygride_user_guide_layout);
        this.mHagrideWeightDataClaimLayout = (RelativeLayout) nsy.cMd_(this.child, R.id.hygride_weight_data_claim_layout);
        this.mWeightDataClaimText = (HealthTextView) nsy.cMd_(this.child, R.id.weight_data_claim_text);
        this.mWeightDataClaimImg = (ImageView) nsy.cMd_(this.child, R.id.weight_data_claim_img);
        this.mWeightDataClaimRedPointImage = (ImageView) nsy.cMd_(this.child, R.id.weight_data_claim_red_point_img);
        this.mHagrideWeightDataManagerLayout = (RelativeLayout) nsy.cMd_(this.child, R.id.hygride_weight_data_manager_layout);
        this.mGuestMeasureLayout = (RelativeLayout) nsy.cMd_(this.child, R.id.hygride_guest_weight_layout);
        this.mCustomTitleBar.setRightButtonVisibility(0);
        HealthTextView healthTextView = (HealthTextView) nsy.cMd_(this.child, R.id.weight_device_connect_status_tv);
        this.mConnectStatusTextView = healthTextView;
        LogUtil.a(TAG, "init mConnectStatusTextView is ", healthTextView);
        this.mConnectStatusPromptMessage = (HealthTextView) nsy.cMd_(this.child, R.id.connect_status_prompt_message);
        this.mWeightValueLayoutContainer = (LinearLayout) nsy.cMd_(this.child, R.id.weight_value_layout_container);
        this.mWeightLayoutContainer = (LinearLayout) nsy.cMd_(this.child, R.id.weight_layout_container);
        this.mWeightDataFromSmartLifeLayout = (RelativeLayout) nsy.cMd_(this.child, R.id.weight_data_from_smart_life_layout);
        this.mShowMoreDataLayout = (LinearLayout) nsy.cMd_(this.child, R.id.more_data_show_layout);
        this.mShowMoreDataArrowImage = (ImageView) nsy.cMd_(this.child, R.id.smart_life_img_arrow);
        if (LanguageUtil.bc(this.mainActivity)) {
            this.mShowMoreDataArrowImage.setBackground(getResources().getDrawable(R.drawable._2131427841_res_0x7f0b0201));
        } else {
            this.mShowMoreDataArrowImage.setBackground(getResources().getDrawable(R.drawable._2131427842_res_0x7f0b0202));
        }
        Resources resources = BaseApplication.getContext().getResources();
        if (resources != null) {
            this.mCustomTitleBar.setTitleBarBackgroundColor(resources.getColor(R.color._2131299340_res_0x7f090c0c));
            this.mCustomTitleBar.setRightButtonDrawable(resources.getDrawable(R.drawable._2131430194_res_0x7f0b0b32), nsf.h(R.string._2130850657_res_0x7f023361));
        }
        this.mCustomTitleBar.setRightButtonOnClickListener(new View.OnClickListener() { // from class: com.huawei.health.device.ui.measure.fragment.HagridDeviceManagerFragment$$ExternalSyntheticLambda7
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                HagridDeviceManagerFragment.this.m159xed61f7b5(view);
            }
        });
        PopViewList popViewList = this.mPopView;
        if (popViewList == null || !popViewList.a()) {
            return;
        }
        LogUtil.h(TAG, "initView mPopView dismiss");
        this.mPopView.b();
    }

    /* renamed from: lambda$initView$4$com-huawei-health-device-ui-measure-fragment-HagridDeviceManagerFragment, reason: not valid java name */
    /* synthetic */ void m159xed61f7b5(View view) {
        showUnbindDeviceMenu();
        ViewClickInstrumentation.clickOnView(view);
    }

    private void showUnbindDeviceMenu() {
        ArrayList arrayList = new ArrayList(Arrays.asList(getResources().getString(R.string.IDS_device_wear_home_delete_device)));
        boolean e = SharedPreferenceManager.e(BaseApplication.getContext(), LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1011));
        boolean d = dks.d(BaseApplication.getContext());
        if (CompileParameterUtil.a("IS_LUPIN_SUPPORT_PRIVACY", false) && d && "25c6df38-ca23-11e9-a32f-2a2ae2dbcce4".equals(this.mProductId) && e) {
            arrayList.add(getResources().getString(R.string._2130844137_res_0x7f0219e9));
        }
        PopViewList popViewList = new PopViewList(this.mainActivity, this.mCustomTitleBar, arrayList);
        this.mPopView = popViewList;
        popViewList.e(new PopViewList.PopViewClickListener() { // from class: com.huawei.health.device.ui.measure.fragment.HagridDeviceManagerFragment$$ExternalSyntheticLambda0
            @Override // com.huawei.ui.commonui.popupview.PopViewList.PopViewClickListener
            public final void setOnClick(int i) {
                HagridDeviceManagerFragment.this.m165x35622561(i);
            }
        });
    }

    /* renamed from: lambda$showUnbindDeviceMenu$5$com-huawei-health-device-ui-measure-fragment-HagridDeviceManagerFragment, reason: not valid java name */
    /* synthetic */ void m165x35622561(int i) {
        if (i != 0) {
            if (i == 1) {
                startActivityForResult(new Intent(BaseApplication.getContext(), (Class<?>) HonorDevicePrivacyActivity.class), 1001);
                return;
            } else {
                LogUtil.h(TAG, "unknown position : ", Integer.valueOf(i));
                return;
            }
        }
        cld cldVar = this.mWeightInteractor;
        if (cldVar != null) {
            cldVar.d();
        }
        cnc.b().Je_(this.mainActivity);
    }

    private void initData() {
        this.mProductId = getArguments().getString("productId");
        dfg.d().c(this.mProductId);
        this.mGoto = getArguments().getString("goto");
        ContentValues contentValues = (ContentValues) getArguments().getParcelable("commonDeviceInfo");
        this.mContentValues = contentValues;
        if (contentValues != null) {
            this.mProductId = contentValues.getAsString("productId");
            this.mUniqueId = this.mContentValues.getAsString("uniqueId");
        } else {
            this.mContentValues = new ContentValues();
            LogUtil.h(TAG, "initData mContentValues is null");
        }
        if (TextUtils.isEmpty(this.mProductId)) {
            LogUtil.h(TAG_RELEASE, "initData mProductId is null");
            this.mainActivity.onBackPressed();
            return;
        }
        if (TextUtils.isEmpty(this.mUrl)) {
            ThreadPoolManager.d().execute(new Runnable() { // from class: com.huawei.health.device.ui.measure.fragment.HagridDeviceManagerFragment$$ExternalSyntheticLambda1
                @Override // java.lang.Runnable
                public final void run() {
                    HagridDeviceManagerFragment.this.m158x3774f7ce();
                }
            });
        }
        initDeviceData();
        getBindByisNfc();
        ArrayList arrayList = new ArrayList(10);
        initItemList(arrayList);
        HagridDeviceManagerAdapter hagridDeviceManagerAdapter = new HagridDeviceManagerAdapter(this.mainActivity, this.mProductId);
        this.mAdapter = hagridDeviceManagerAdapter;
        hagridDeviceManagerAdapter.b(arrayList);
        this.mItemListView.setAdapter((ListAdapter) this.mAdapter);
        cmp.e().IH_(this.mainActivity, this.mProductId, this.mUniqueId, this);
        cnc.b().IY_(this.mUniqueId, this.mProductId, this.mainActivity, this.myHandler, this);
    }

    /* renamed from: lambda$initData$6$com-huawei-health-device-ui-measure-fragment-HagridDeviceManagerFragment, reason: not valid java name */
    /* synthetic */ void m158x3774f7ce() {
        this.mUrl = cpy.b(this.mProductId);
    }

    private void initDeviceData() {
        if (TextUtils.isEmpty(this.mUniqueId)) {
            LogUtil.h(TAG, "initData get uniqueId from device");
            MeasurableDevice c = ceo.d().c(this.mProductId);
            if (c != null) {
                String uniqueId = c.getUniqueId();
                this.mUniqueId = uniqueId;
                this.mContentValues.put("uniqueId", uniqueId);
                this.mContentValues.put("productId", this.mProductId);
            }
        }
        LogUtil.a(TAG, "initData unique ", cpw.d(this.mUniqueId));
        dcz d = ResourceManager.e().d(this.mProductId);
        this.mProductInfo = d;
        if (d != null) {
            this.mKind = d.l();
        } else {
            LogUtil.h(TAG, "initData mProductInfo is null");
        }
        MeasurableDevice d2 = ceo.d().d(this.mUniqueId, false);
        if (d2 != null && (d2 instanceof ctk)) {
            this.mWiFiDevice = (ctk) d2;
            LogUtil.a(TAG, "initData this device is change type");
            this.mDevId = this.mWiFiDevice.d();
            ClaimWeightDataManager.INSTANCE.registerCallBack(getClass().getSimpleName(), this.commBaseCallback);
            cnn.b().c(this.mWiFiDevice);
            coz.e(TAG_RELEASE, this.mainActivity, this.mWiFiDevice, this.mUniqueId);
        }
        cpz.e(this.mainActivity, d2, this.mProductId, this.mUniqueId);
    }

    private void getBindByisNfc() {
        if (cpa.v(this.mProductId) && !coy.JU_(this.mHuid, this.mainActivity, TAG)) {
            boolean z = getArguments().getBoolean("isNfcConnect");
            this.mIsNfcConnectScales = z;
            if (!z) {
                String b = SharedPreferenceManager.b(BaseApplication.getContext(), String.valueOf(10000), "isNfcConnect" + this.mUniqueId);
                LogUtil.a(TAG, "isNfcConnectResult = ", b);
                if ("true".equals(b)) {
                    this.mIsNfcConnectScales = true;
                    return;
                } else {
                    LogUtil.a(TAG, "getBooleanExtra mIsNfcCommect from sharePreference = ", Boolean.valueOf(this.mIsNfcConnectScales));
                    this.mIsNfcConnectScales = false;
                    return;
                }
            }
            SharedPreferenceManager.e(BaseApplication.getContext(), String.valueOf(10000), "isNfcConnect" + this.mUniqueId, this.mIsNfcConnectScales + "", (StorageParams) null);
            StringBuilder sb = new StringBuilder("isNfcConnect");
            sb.append(this.mUniqueId);
            LogUtil.a(TAG, "set mIsNfcConnectScales = ", sb.toString());
            return;
        }
        LogUtil.h(TAG, "current device manage is current user");
    }

    private void initUnitType() {
        setWeightUnitResult(cnn.b().e());
    }

    public void updateItemView(final String str) {
        if (getActivity() == null) {
            LogUtil.h(TAG, "updateItemView getActivity is null");
        } else {
            getActivity().runOnUiThread(new Runnable() { // from class: com.huawei.health.device.ui.measure.fragment.HagridDeviceManagerFragment$$ExternalSyntheticLambda3
                @Override // java.lang.Runnable
                public final void run() {
                    HagridDeviceManagerFragment.this.m168x874d60e6(str);
                }
            });
        }
    }

    /* renamed from: lambda$updateItemView$7$com-huawei-health-device-ui-measure-fragment-HagridDeviceManagerFragment, reason: not valid java name */
    /* synthetic */ void m168x874d60e6(String str) {
        cnu createSettingItem = createSettingItem(18, getViewString(R.string.IDS_device_rope_wifi_log), "", str);
        HagridDeviceManagerAdapter hagridDeviceManagerAdapter = this.mAdapter;
        if (hagridDeviceManagerAdapter != null) {
            hagridDeviceManagerAdapter.c(18, createSettingItem);
            this.mAdapter.a(createSettingItem);
        }
    }

    public void setWeightUnitResult(int i) {
        if (i == 1) {
            this.mAdapter.a(createSettingItem(15, getViewString(R.string.IDS_hw_device_body_scale_show_unit), "", getViewString(R.string._2130840558_res_0x7f020bee)));
        } else if (i == 3) {
            this.mAdapter.a(createSettingItem(15, getViewString(R.string.IDS_hw_device_body_scale_show_unit), "", getViewString(R.string.IDS_device_weight_value_g)));
        } else if (i == 2) {
            this.mAdapter.a(createSettingItem(15, getViewString(R.string.IDS_hw_device_body_scale_show_unit), "", getViewString(R.string.IDS_device_measure_weight_value_unit_eng)));
        } else {
            this.mAdapter.a(createSettingItem(15, getViewString(R.string.IDS_hw_device_body_scale_show_unit), "", ""));
        }
        LogUtil.a(TAG, "setWeightUnitResult mUnitType", String.valueOf(i));
    }

    private String getViewString(int i) {
        return BaseApplication.getContext().getResources().getString(i);
    }

    private void initItemList(List<cnu> list) {
        list.clear();
        showClaimDataView();
        list.add(createSettingItem(15, getViewString(R.string.IDS_hw_device_body_scale_show_unit), "", ""));
        this.mIsWaitForAccountInfo = false;
        if (cpa.ah(this.mProductId) || cpa.r(this.mProductId)) {
            list.add(createSettingItem(9, getViewString(R.string._2130841511_res_0x7f020fa7), "", ""));
            addMiniScaleItems(list);
        } else if (this.mWiFiDevice != null) {
            MeasurableDevice d = ceo.d().d(this.mUniqueId, true);
            if (this.mWiFiDevice.b().k() == 1) {
                LogUtil.a(TAG, "is manager addHagridWifiManagerItems");
                addHagridWifiManagerItems(list);
            } else {
                addNonManagerItems(d, list);
            }
        } else {
            LogUtil.a(TAG, "is only BLE device add updateItem");
            this.mIsWaitForAccountInfo = true;
            addHagridBleItems(list);
        }
        if (!Utils.o() && !CommonUtil.bv() && cpa.an(this.mProductId)) {
            list.add(createSettingItem(18, getViewString(R.string.IDS_device_rope_wifi_log), "", ""));
            list.add(createSettingItem(19, getViewString(R.string.IDS_device_rope_beta_upload), "", ""));
        }
        if (cpa.as(this.mProductId)) {
            addHelpCustomerServiceItem(list);
        }
    }

    private void addHelpCustomerServiceItem(List<cnu> list) {
        if (CommonUtil.as()) {
            LogUtil.h(TAG, "beta app not support");
            return;
        }
        if (CommonUtil.bu()) {
            ReleaseLogUtil.d(TAG_RELEASE, "store demo version not support");
            return;
        }
        if (!Utils.i()) {
            ReleaseLogUtil.d(TAG_RELEASE, "is not login status");
            return;
        }
        int m = CommonUtil.m(BaseApplication.getContext(), LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1009));
        ReleaseLogUtil.e(TAG_RELEASE, "siteId : ", Integer.valueOf(m));
        if (m != 1 || LanguageUtil.j(this.mainActivity) || LanguageUtil.p(this.mainActivity)) {
            list.add(createSettingItem(20, getViewString((njn.e(this.mainActivity) || com.huawei.operation.utils.Utils.isShowJapanCustomer(this.mainActivity)) ? R.string._2130843618_res_0x7f0217e2 : R.string._2130842101_res_0x7f0211f5), "", ""));
        }
    }

    private void addNonManagerItems(MeasurableDevice measurableDevice, List<cnu> list) {
        String format;
        if (measurableDevice == null) {
            LogUtil.h(TAG, "initItemList the device is not exist");
            return;
        }
        if (nsn.ae(BaseApplication.getContext())) {
            format = String.format(Locale.ENGLISH, getResources().getString(R.string.IDS_pad_device_auto_sync_data), new Object[0]);
        } else {
            format = String.format(Locale.ENGLISH, getResources().getString(R.string.IDS_device_request_wlan_auto_sync_data_sub_content), new Object[0]);
        }
        LogUtil.a(TAG, "initItemList getDeviceManagerStatus is", Boolean.valueOf(coy.e(this.mUniqueId)));
        if ((measurableDevice instanceof cxh) && !coy.e(this.mUniqueId)) {
            list.add(createSettingItem(13, getViewString(R.string.IDS_device_wifi_config_network), format, ""));
        } else {
            list.add(createSettingItem(12, getViewString(R.string.IDS_device_user_manager), "", ""));
        }
        LogUtil.a(TAG, "initItemList mIsNfcConnectScales is", Boolean.valueOf(this.mIsNfcConnectScales));
        if (!this.mIsNfcConnectScales) {
            list.add(createSettingItem(9, getViewString(R.string._2130841511_res_0x7f020fa7), "", ""));
        }
        if (measurableDevice instanceof ctk) {
            return;
        }
        list.add(getUpdateItem());
    }

    private void showWeightDataView() {
        if (!(getActivity() instanceof DeviceMainActivity)) {
            LogUtil.h(TAG, "getActivity() is not instantof DeviceMainActivity");
            return;
        }
        DeviceMainActivity deviceMainActivity = (DeviceMainActivity) getActivity();
        if (deviceMainActivity != null && deviceMainActivity.g()) {
            this.mWeightDataFromSmartLifeLayout.setVisibility(0);
            this.mWeightValueLayoutContainer.setVisibility(8);
            this.mWeightLayoutContainer.setVisibility(8);
        } else {
            this.mWeightDataFromSmartLifeLayout.setVisibility(8);
            this.mWeightValueLayoutContainer.setVisibility(0);
            this.mWeightLayoutContainer.setVisibility(0);
        }
    }

    public void showClaimDataView() {
        if (cpa.al(this.mProductId)) {
            this.mGuestMeasureLayout.setVisibility(8);
            this.mWeightDataClaimText.setText(getViewString(R.string.IDS_hw_device_weight_guest_measurement));
            this.mWeightDataClaimText.setTextColor(this.mainActivity.getResources().getColor(R.color._2131299236_res_0x7f090ba4));
            this.mWeightDataClaimImg.setImageResource(R.drawable._2131429976_res_0x7f0b0a58);
            this.mWeightDataClaimImg.setImageDrawable(this.mainActivity.getResources().getDrawable(R.drawable._2131429976_res_0x7f0b0a58));
            this.mWeightDataClaimRedPointImage.setVisibility(8);
            if (cpa.r(this.mProductId)) {
                this.mHagrideWeightDataManagerLayout.setVisibility(0);
                return;
            }
            return;
        }
        this.mHagrideWeightDataManagerLayout.setVisibility(8);
        this.mWeightDataClaimText.setText(getViewString(R.string.IDS_device_wifi_scale_manager));
        this.mWeightDataClaimText.setTextColor(this.mainActivity.getResources().getColor(R.color._2131299236_res_0x7f090ba4));
        this.mWeightDataClaimImg.setImageResource(R.drawable._2131430421_res_0x7f0b0c15);
        this.mWeightDataClaimImg.setImageDrawable(this.mainActivity.getResources().getDrawable(R.drawable._2131430421_res_0x7f0b0c15));
        this.mWeightDataClaimRedPointImage.setVisibility(8);
    }

    private void addHagridWifiManagerItems(List<cnu> list) {
        String format;
        list.add(createSettingItem(16, getViewString(R.string.IDS_device_hygride_manager_settings), "", ""));
        list.add(createSettingItem(12, getViewString(R.string.IDS_device_user_manager), "", ""));
        if (nsn.ae(BaseApplication.getContext())) {
            format = String.format(Locale.ENGLISH, getResources().getString(R.string.IDS_pad_device_auto_sync_data), new Object[0]);
        } else {
            format = String.format(Locale.ENGLISH, getResources().getString(R.string.IDS_device_request_wlan_auto_sync_data_sub_content), new Object[0]);
        }
        LogUtil.a(TAG, "addHagridWifiManagerItems show config network");
        list.add(createSettingItem(13, getViewString(R.string.IDS_device_wifi_config_network), format, ""));
        list.add(createSettingItem(9, getViewString(R.string._2130841511_res_0x7f020fa7), "", ""));
        list.add(getUpdateItem());
    }

    private void addHagridBleItems(List<cnu> list) {
        String format;
        if (!coy.e(this.mUniqueId)) {
            if (nsn.ae(BaseApplication.getContext())) {
                format = String.format(Locale.ENGLISH, getResources().getString(R.string.IDS_pad_device_auto_sync_data), new Object[0]);
            } else {
                format = String.format(Locale.ENGLISH, getResources().getString(R.string.IDS_device_request_wlan_auto_sync_data_sub_content), new Object[0]);
            }
            LogUtil.a(TAG, "addHagridBleItems show config network");
            list.add(createSettingItem(13, getViewString(R.string.IDS_device_wifi_config_network), format, ""));
        } else {
            LogUtil.a(TAG, "addHagridBleItems show device share! ");
            list.add(createSettingItem(14, getViewString(R.string.IDS_device_user_manager), "", ""));
        }
        LogUtil.a(TAG, "addHagridBleItems mIsNfcConnectScales is", Boolean.valueOf(this.mIsNfcConnectScales));
        if (!this.mIsNfcConnectScales) {
            list.add(createSettingItem(9, getViewString(R.string._2130841511_res_0x7f020fa7), "", ""));
        }
        list.add(getUpdateItem());
    }

    private void addMiniScaleItems(List<cnu> list) {
        list.add(getUpdateItem());
    }

    private cnu getUpdateItem() {
        String viewString;
        if (cpa.ae(this.mProductId)) {
            viewString = getViewString(R.string.IDS_device_scale_device_firmware_version);
        } else {
            viewString = getViewString(R.string.IDS_device_wear_home_device_ota_upgrade);
        }
        return createSettingItem(6, viewString, "", "");
    }

    private String getSubUserNum() {
        String viewString = getViewString(R.string.IDS_device_wifi_scale_sub_user_num);
        crw a2 = ctq.a(this.mWiFiDevice.b().a());
        String format = String.format(Locale.ENGLISH, viewString, UnitUtil.e(((a2 == null || a2.d()) ? 0 : a2.b().size()) + 1, 1, 0), UnitUtil.e((cpa.w(this.mProductId) || "b29df4e3-b1f7-4e40-960d-4cfb63ccca05".equals(this.mProductId)) ? 10 : 5, 1, 0));
        csf.c(this.mWiFiDevice.d());
        return format;
    }

    public cnu createSettingItem(int i, String str, String str2, String str3) {
        cnu cnuVar = new cnu();
        cnuVar.b(i);
        cnuVar.d(str);
        cnuVar.c(str2);
        cnuVar.b(true);
        cnuVar.a(str3);
        return cnuVar;
    }

    private void initViewData() {
        if ("8358eb90-b40d-11e9-a2a3-2a2ae2dbcce4".equals(this.mProductId)) {
            this.mDeviceImg.setImageResource(R.drawable.pic_huawei_hagrid_img);
        } else if ("25c6df38-ca23-11e9-a32f-2a2ae2dbcce4".equals(this.mProductId)) {
            this.mDeviceImg.setImageResource(R.drawable.pic_honor_mini_img);
        } else {
            dcz d = ResourceManager.e().d(this.mProductId);
            if (d != null) {
                String c = d.c("device_manager_img");
                if (TextUtils.isEmpty(c)) {
                    LogUtil.h(TAG, "initViewData img = null");
                    return;
                }
                String a2 = dcq.b().a(this.mProductId, c);
                if (TextUtils.isEmpty(a2)) {
                    return;
                }
                Bitmap TK_ = dcx.TK_(a2);
                if (TK_ != null) {
                    LogUtil.a(TAG, "initViewData bitmap != null");
                    this.mDeviceImg.setImageBitmap(TK_);
                } else {
                    LogUtil.h(TAG, "initViewData bitmap = null");
                }
            }
        }
        if (!TextUtils.isEmpty(this.mProductId) && this.mProductInfo != null) {
            String f = cpa.f(this.mUniqueId);
            if (TextUtils.isEmpty(cpa.f(this.mUniqueId))) {
                setTitle(dcx.d(this.mProductId, this.mProductInfo.n().b()));
            } else {
                setTitle(dcx.d(this.mProductId, this.mProductInfo.n().b()) + " - " + f);
            }
        }
        HagridDeviceManagerAdapter hagridDeviceManagerAdapter = this.mAdapter;
        if (hagridDeviceManagerAdapter == null || hagridDeviceManagerAdapter.a(6) == null) {
            return;
        }
        firmwareVersionRedDot();
    }

    private void firmwareVersionRedDot() {
        if (cpa.v(this.mProductId)) {
            boolean b = coz.b(this.mainActivity, this.mUniqueId);
            if (this.mWiFiDevice == null && !coy.e(this.mUniqueId) && b) {
                LogUtil.a(TAG, " unconfigured network && no device manager &&  have new version");
                this.mAdapter.a(6).a(true);
                this.mAdapter.notifyDataSetChanged();
                return;
            }
            ctk ctkVar = this.mWiFiDevice;
            if (ctkVar != null && ctkVar.b().k() == 1 && b) {
                LogUtil.a(TAG, " configured network && is main user && have new version");
                this.mAdapter.a(6).a(true);
                this.mAdapter.notifyDataSetChanged();
                return;
            } else {
                this.mAdapter.a(6).a(false);
                this.mAdapter.notifyDataSetChanged();
                return;
            }
        }
        csc d = csc.d();
        ctk ctkVar2 = this.mWiFiDevice;
        if (d.g(ctkVar2 != null ? ctkVar2.d() : "") && this.mWiFiDevice.b().k() == 1) {
            this.mAdapter.a(6).a(true);
            this.mAdapter.notifyDataSetChanged();
        } else {
            this.mAdapter.a(6).a(false);
            this.mAdapter.notifyDataSetChanged();
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view == this.mStartMeasureTextView) {
            checkBluetoothPermissions();
        } else if (view == this.mHagrideUserGuideLayout) {
            dealClickUserGuide();
        } else if (view == this.mHagrideWeightDataClaimLayout) {
            LogUtil.a(TAG, "click mHagrideWeightDataClaimLayout");
            if (this.mWeightDataClaimText.getText().toString().equals(getResources().getString(R.string.IDS_device_wifi_scale_manager))) {
                LogUtil.a(TAG, "mHagrideWeightDataClaimLayout scale data manage");
                dealDataManager();
            } else if (this.mWeightDataClaimText.getText().toString().equals(getResources().getString(R.string.IDS_hw_device_weight_guest_measurement))) {
                LogUtil.a(TAG, "mHagrideWeightDataClaimLayout guest measure");
                dealClickWeightDataClaim();
            } else {
                Intent intent = new Intent();
                intent.putExtra("productId", this.mProductId);
                intent.setClassName(this.mainActivity, "com.huawei.ui.main.stories.health.activity.healthdata.ClaimMeasureDataActivity");
                try {
                    this.mainActivity.startActivity(intent);
                } catch (ActivityNotFoundException unused) {
                    LogUtil.b(TAG, "onClick startActivity ActivityNotFoundException occur.");
                }
            }
        } else if (view == this.mHagrideWeightDataManagerLayout) {
            dealDataManager();
        } else if (view == this.mShowMoreDataLayout) {
            dij.UZ_(this.mainActivity);
        } else if (view == this.mGuestMeasureLayout) {
            LogUtil.a(TAG_RELEASE, "click mGuestMeasureLayout.");
            dealClickWeightDataClaim();
        } else {
            LogUtil.h(TAG_RELEASE, "Click invalid.");
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    private void dealClickUserGuide() {
        if (cqh.c().KT_(this.mainActivity, this.mUniqueId)) {
            LogUtil.h(TAG, "onclick mHagrideUserGuideLayout pair invalid and bluetooth switch is off");
        } else {
            cnb.d(this.mainActivity, this.mUniqueId, this.mProductId, this.mUrl, this);
        }
    }

    private void dealClickWeightDataClaim() {
        if (cqh.c().KT_(this.mainActivity, this.mUniqueId)) {
            LogUtil.h(TAG, "onclick mWeightDataClaimText or mGuestMeasureLayout pair invalid and bluetooth switch is off");
        } else {
            gotoGuestUserInfoGuidFragment();
        }
    }

    private void checkBluetoothPermissions() {
        if (Build.VERSION.SDK_INT > 30 && PermissionUtil.e(this.mainActivity, PermissionUtil.PermissionType.SCAN) != PermissionUtil.PermissionResult.GRANTED) {
            PermissionUtil.b(this.mainActivity, PermissionUtil.PermissionType.SCAN, new CustomPermissionAction(this.mainActivity) { // from class: com.huawei.health.device.ui.measure.fragment.HagridDeviceManagerFragment.3
                @Override // com.huawei.hwcommonmodel.utils.permission.PermissionsResultAction
                public void onGranted() {
                    LogUtil.a(HagridDeviceManagerFragment.TAG, "startMeasure doActionWithPermissions onGranted");
                    HagridDeviceManagerFragment.this.startMeasure();
                }
            });
        } else {
            startMeasure();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startMeasure() {
        LogUtil.a(TAG, "enter checkBluetoothEnabled");
        if (BluetoothAdapter.getDefaultAdapter() == null || getActivity() == null) {
            LogUtil.b(TAG, "checkBluetoothEnabled BluetoothAdapter or getActivity is null");
            return;
        }
        if (BluetoothAdapter.getDefaultAdapter().isEnabled()) {
            LogUtil.a(TAG, "click mStartMeasureTextView");
            MeasurableDevice d = ceo.d().d(this.mUniqueId, true);
            if (d == null) {
                LogUtil.h(TAG, "device is null");
                return;
            } else if (!(d instanceof cxh)) {
                LogUtil.a(TAG, "device is not bound, jump to bond page");
                startPaireGuide();
                return;
            } else {
                startBleMeasure();
                return;
            }
        }
        cqh.c().Lf_(this.mainActivity, 2);
    }

    private void startPaireGuide() {
        if (cqa.a().d(this.mDownloadStatus)) {
            if (cpe.c()) {
                downloadIndexAllResources();
            } else {
                cnb.a(getActivity(), this.mProductId, this.mUniqueId);
            }
        }
    }

    private void gotoGuestUserInfoGuidFragment() {
        if (!(ceo.d().d(this.mUniqueId, true) instanceof cxh)) {
            LogUtil.h(TAG, "gotoGuestUserInfoGuidFragment is not BleDevice");
            showSelectBindDeviceDialog();
            return;
        }
        LogUtil.c(TAG, "gotoGuestUserInfoGuidFragment className: ", GuestUserInfoGuideFragment.class.getName());
        BaseFragment c = ckq.c(GuestUserInfoGuideFragment.class.getName());
        if (c != null) {
            Bundle bundle = new Bundle();
            bundle.putString("productId", this.mProductId);
            bundle.putParcelable("commonDeviceInfo", this.mContentValues);
            bundle.putBoolean("deviceMgrMeasure", true);
            c.setArguments(bundle);
            switchFragment(c);
            return;
        }
        LogUtil.h(TAG, "gotoGuestUserInfoGuidFragment guestMeasureFragment is null");
    }

    private void startBleMeasure() {
        List<cfi> allUser = MultiUsersManager.INSTANCE.getAllUser();
        LogUtil.a(TAG, "startBleMeasure allUser number:", Integer.valueOf(allUser.size()));
        if (allUser.size() <= 1) {
            jumpToMeasureFragment();
            return;
        }
        CustomViewDialog initCustomViewDialog = initCustomViewDialog();
        this.mCustomViewDialog = initCustomViewDialog;
        if (initCustomViewDialog != null && !initCustomViewDialog.isShowing()) {
            initUserData(allUser);
            ViewGroup.LayoutParams layoutParams = this.mUserLayout.getLayoutParams();
            layoutParams.height = cqb.d().Kt_(this.mUserListView, this.mainActivity);
            layoutParams.width = -1;
            this.mUserLayout.setLayoutParams(layoutParams);
            this.mCustomViewDialog.show();
            return;
        }
        LogUtil.h(TAG, "showDialog: mCustomViewDialog is null | isShowing.");
    }

    private void initUserData(List<cfi> list) {
        WeightResultConfirmAdapter weightResultConfirmAdapter = this.mUserAdapter;
        if (weightResultConfirmAdapter == null) {
            LogUtil.h(TAG, "initUserData: mUserAdapter is null.");
            return;
        }
        weightResultConfirmAdapter.b(list);
        HealthTextView healthTextView = this.mAddUserView;
        if (healthTextView != null) {
            healthTextView.setVisibility(this.mUserAdapter.getCount() >= 10 ? 8 : 0);
        }
        String i = MultiUsersManager.INSTANCE.getCurrentUser().i();
        if (TextUtils.isEmpty(i)) {
            LogUtil.h(TAG, "initUserData: uuid is empty");
            return;
        }
        for (int i2 = 0; i2 < list.size(); i2++) {
            cfi cfiVar = list.get(i2);
            if (cfiVar == null) {
                LogUtil.h(TAG, "initUserData: tmpUser is null");
                return;
            } else {
                if (i.equals(cfiVar.i())) {
                    this.mUserAdapter.a(i2);
                    showSelectionUser(i2);
                    return;
                }
            }
        }
    }

    private void showSelectionUser(int i) {
        ListView listView = this.mUserListView;
        if (listView != null) {
            listView.setSelection(i);
        }
    }

    private CustomViewDialog initCustomViewDialog() {
        CustomViewDialog customViewDialog = this.mCustomViewDialog;
        if (customViewDialog != null) {
            return customViewDialog;
        }
        if (getActivity() != null) {
            CustomViewDialog.Builder builder = new CustomViewDialog.Builder(getActivity());
            builder.a(getActivity().getString(R.string.IDS_hw_device_hygride_select_current_measure_user)).czg_(getDialogView()).cze_(R.string._2130841395_res_0x7f020f33, new View.OnClickListener() { // from class: com.huawei.health.device.ui.measure.fragment.HagridDeviceManagerFragment$$ExternalSyntheticLambda9
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    HagridDeviceManagerFragment.this.m156x6316218(view);
                }
            }).czc_(R.string._2130841939_res_0x7f021153, new View.OnClickListener() { // from class: com.huawei.health.device.ui.measure.fragment.HagridDeviceManagerFragment$$ExternalSyntheticLambda10
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    HagridDeviceManagerFragment.this.m157x20a25b37(view);
                }
            });
            CustomViewDialog e = builder.e();
            this.mCustomViewDialog = e;
            e.setCancelable(false);
        }
        return this.mCustomViewDialog;
    }

    /* renamed from: lambda$initCustomViewDialog$8$com-huawei-health-device-ui-measure-fragment-HagridDeviceManagerFragment, reason: not valid java name */
    /* synthetic */ void m156x6316218(View view) {
        this.mCustomViewDialog.dismiss();
        int d = this.mUserAdapter.d();
        Object item = this.mUserAdapter.getItem(d);
        if (item instanceof cfi) {
            MultiUsersManager.INSTANCE.setCurrentUser((cfi) item);
            jumpToMeasureFragment();
        } else {
            LogUtil.b(TAG, "change failed :", Integer.valueOf(d));
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    /* renamed from: lambda$initCustomViewDialog$9$com-huawei-health-device-ui-measure-fragment-HagridDeviceManagerFragment, reason: not valid java name */
    /* synthetic */ void m157x20a25b37(View view) {
        this.mCustomViewDialog.dismiss();
        ViewClickInstrumentation.clickOnView(view);
    }

    private View getDialogView() {
        if (getActivity() == null) {
            return null;
        }
        View inflate = LayoutInflater.from(getActivity()).inflate(R.layout.weight_measure_result_confirm_dialog_view, (ViewGroup) null, false);
        this.mUserLayout = (LinearLayout) inflate.findViewById(R.id.weight_measure_result_confrim_list_layout);
        this.mUserListView = (ListView) inflate.findViewById(R.id.weight_measure_result_confrim_user_list);
        WeightResultConfirmAdapter weightResultConfirmAdapter = new WeightResultConfirmAdapter(getActivity(), null);
        this.mUserAdapter = weightResultConfirmAdapter;
        this.mUserListView.setAdapter((ListAdapter) weightResultConfirmAdapter);
        HealthTextView healthTextView = (HealthTextView) inflate.findViewById(R.id.weight_measure_result_confrim_add_new_user_text);
        this.mAddUserView = healthTextView;
        healthTextView.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.health.device.ui.measure.fragment.HagridDeviceManagerFragment$$ExternalSyntheticLambda14
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                HagridDeviceManagerFragment.this.m155x54f409a(view);
            }
        });
        return inflate;
    }

    /* renamed from: lambda$getDialogView$10$com-huawei-health-device-ui-measure-fragment-HagridDeviceManagerFragment, reason: not valid java name */
    /* synthetic */ void m155x54f409a(View view) {
        enterAddNewUser();
        ViewClickInstrumentation.clickOnView(view);
    }

    private void enterAddNewUser() {
        try {
            Intent intent = new Intent();
            intent.setPackage(BaseApplication.getAppPackage());
            intent.setClassName(BaseApplication.getAppPackage(), "com.huawei.ui.main.stories.health.activity.healthdata.AddOrEditWeightUserActivity");
            intent.putExtra("weight_user_id_key", "");
            intent.putExtra("claimWeightData", true);
            startActivityForResult(intent, 0);
        } catch (IllegalStateException e) {
            LogUtil.b(TAG, "enterAddNewUser occur IllegalStateException: ", ExceptionUtils.d(e));
        }
    }

    private void jumpToMeasureFragment() {
        BaseFragment a2 = ckq.a(this.mKind.name());
        if (a2 != null) {
            Bundle bundle = new Bundle();
            bundle.putString("view", "measure");
            bundle.putString("kind", this.mKind.name());
            bundle.putParcelable("commonDeviceInfo", this.mContentValues);
            bundle.putString("goback", "hygride");
            bundle.putBoolean("status", isBluetoothConnect());
            bundle.putBoolean("deviceMgrMeasure", true);
            bundle.putInt("type", -1);
            a2.setArguments(bundle);
            switchFragment(a2);
        }
    }

    @Override // com.huawei.health.ecologydevice.ui.BaseFragment, androidx.fragment.app.Fragment
    public void onResume() {
        LogUtil.a(TAG, "onResume");
        super.onResume();
        cnn.b().d();
        getBindByisNfc();
        csf.a(this.mainActivity, this.mWiFiDevice, this.mProductInfo);
        refreshSubUserNum();
        refreshWifiName();
        if (ceo.d().d(this.mUniqueId, true) != null && this.mWeightInteractor != null && cqb.d().b(this.mUniqueId)) {
            LogUtil.h(TAG, "mWeightInteractor onResume");
            this.mWeightInteractor.HS_(this.mainActivity);
            this.mWeightInteractor.onResume();
        }
        if (!cpa.ah(this.mProductId) && !cpa.r(this.mProductId) && !this.myHandler.hasMessages(3)) {
            this.myHandler.sendEmptyMessageDelayed(3, ProfileExtendConstants.TIME_OUT);
        }
        initManager();
        this.mEventManager.b();
        refreshUnreadMsgNum();
        ClaimWeightDataManager.INSTANCE.queryBluetoothClaimData();
        showClaimDataView();
        showWeightDataView();
        showConfigureAutoUpgrade();
    }

    private void showConfigureAutoUpgrade() {
        if (this.mainActivity == null) {
            LogUtil.h(TAG, "showConfigureAutoUpgradeDialog mainActivity is null");
            return;
        }
        if (!cpa.v(this.mProductId)) {
            LogUtil.h(TAG, "showConfigureAutoUpgradeDialog is not new hag");
            return;
        }
        if (cqb.d().c("hagridConfigureAutoUpgrade", 1, this.mUniqueId) < 1) {
            LogUtil.h(TAG, "showConfigureAutoUpgradeDialog day less than one");
            return;
        }
        ctk ctkVar = this.mWiFiDevice;
        if (ctkVar == null) {
            LogUtil.h(TAG, "showConfigureAutoUpgradeDialog is not WiFiDevice");
            return;
        }
        if (ctkVar.b().k() != 1) {
            LogUtil.h(TAG, "showConfigureAutoUpgradeDialog is not main user");
            return;
        }
        if (!coz.b(this.mainActivity, this.mUniqueId)) {
            LogUtil.h(TAG, "showConfigureAutoUpgradeDialog is not new version");
            return;
        }
        String b = csc.b(this.mWiFiDevice.d());
        LogUtil.a(TAG, "showConfigureAutoUpgradeDialog autoUpgradeStatus:", b);
        if ("0".equals(b)) {
            showConfigureAutoUpgradeDialog();
        } else {
            LogUtil.h(TAG, "showConfigureAutoUpgradeDialog auto upgrade status open");
        }
    }

    private void showConfigureAutoUpgradeDialog() {
        View inflate = getActivity().getLayoutInflater().inflate(R.layout.configure_atuo_upgrade_dialog, (ViewGroup) null);
        final HealthCheckBox healthCheckBox = (HealthCheckBox) inflate.findViewById(R.id.id_device_auto_upgrade_switch);
        cqh.c().Lb_(this.mainActivity, inflate, new ScaleDialogCallback() { // from class: com.huawei.health.device.ui.measure.fragment.HagridDeviceManagerFragment$$ExternalSyntheticLambda13
            @Override // com.huawei.health.device.callback.ScaleDialogCallback
            public final void operationResult(int i) {
                HagridDeviceManagerFragment.this.m163x2d017b7c(healthCheckBox, i);
            }
        });
    }

    /* renamed from: lambda$showConfigureAutoUpgradeDialog$11$com-huawei-health-device-ui-measure-fragment-HagridDeviceManagerFragment, reason: not valid java name */
    /* synthetic */ void m163x2d017b7c(HealthCheckBox healthCheckBox, int i) {
        if (i == 0) {
            LogUtil.a(TAG, "showConfigureAutoUpgradeDialog to upgrade");
            SharedPreferenceManager.e(BaseApplication.getContext(), String.valueOf(10000), "hagridConfigureAutoUpgrade" + this.mUniqueId, String.valueOf(System.currentTimeMillis()), (StorageParams) null);
            cqb.d().c(healthCheckBox, this.mWiFiDevice);
            upgradeForWiFi();
            return;
        }
        LogUtil.a(TAG, "showConfigureAutoUpgradeDialog is cancel");
        SharedPreferenceManager.e(BaseApplication.getContext(), String.valueOf(10000), "hagridConfigureAutoUpgrade" + this.mUniqueId, String.valueOf(System.currentTimeMillis()), (StorageParams) null);
        cqb.d().c(healthCheckBox, this.mWiFiDevice);
    }

    public void refreshSubUserNum() {
        ctk ctkVar = this.mWiFiDevice;
        if (ctkVar == null || this.mAdapter == null) {
            return;
        }
        if (ctkVar.b().k() == 1) {
            this.mAdapter.a(createSettingItem(12, getViewString(R.string.IDS_device_user_manager), "", getSubUserNum()));
        } else {
            subUserRefreshNum();
        }
    }

    private void subUserRefreshNum() {
        WifiDeviceShareMemberInfoBySubUserReq wifiDeviceShareMemberInfoBySubUserReq = new WifiDeviceShareMemberInfoBySubUserReq();
        wifiDeviceShareMemberInfoBySubUserReq.setDevId(this.mDevId);
        jbs.a(this.mainActivity).c(wifiDeviceShareMemberInfoBySubUserReq, new ICloudOperationResult() { // from class: com.huawei.health.device.ui.measure.fragment.HagridDeviceManagerFragment$$ExternalSyntheticLambda12
            @Override // com.huawei.hwcloudmodel.callback.ICloudOperationResult
            public final void operationResult(Object obj, String str, boolean z) {
                HagridDeviceManagerFragment.this.m167xbb16452a((CloudCommonReponse) obj, str, z);
            }
        });
    }

    /* renamed from: lambda$subUserRefreshNum$13$com-huawei-health-device-ui-measure-fragment-HagridDeviceManagerFragment, reason: not valid java name */
    /* synthetic */ void m167xbb16452a(CloudCommonReponse cloudCommonReponse, String str, boolean z) {
        if (this.mainActivity != null && z) {
            try {
                JSONArray jSONArray = new JSONObject(str).getJSONArray("members");
                if (jSONArray != null && jSONArray.length() > 0) {
                    final int length = jSONArray.length();
                    this.mainActivity.runOnUiThread(new Runnable() { // from class: com.huawei.health.device.ui.measure.fragment.HagridDeviceManagerFragment$$ExternalSyntheticLambda11
                        @Override // java.lang.Runnable
                        public final void run() {
                            HagridDeviceManagerFragment.this.m166xa0a54c0b(length);
                        }
                    });
                    return;
                }
                LogUtil.h(TAG, "AuthorizeSubUser members is zero");
                return;
            } catch (JSONException unused) {
                LogUtil.b(TAG, "AuthorizeSubUser members is JSONException");
                return;
            }
        }
        LogUtil.h(TAG, "getMemberInfoBySubUser fail");
    }

    /* renamed from: lambda$subUserRefreshNum$12$com-huawei-health-device-ui-measure-fragment-HagridDeviceManagerFragment, reason: not valid java name */
    /* synthetic */ void m166xa0a54c0b(int i) {
        this.mAdapter.a(createSettingItem(12, getViewString(R.string.IDS_device_user_manager), "", String.format(Locale.ENGLISH, getViewString(R.string.IDS_device_wifi_scale_sub_user_num), UnitUtil.e(i + 1, 1, 0), UnitUtil.e((cpa.w(this.mProductId) || "b29df4e3-b1f7-4e40-960d-4cfb63ccca05".equals(this.mProductId)) ? 10 : 5, 1, 0))));
    }

    private void refreshWifiName() {
        String format;
        ctk ctkVar = this.mWiFiDevice;
        if (ctkVar == null || this.mAdapter == null) {
            return;
        }
        boolean z = ctkVar.b().k() == 1;
        LogUtil.a(TAG, "enter refreshWifiName isMainUser is", Boolean.valueOf(z));
        String b = SharedPreferenceManager.b(BaseApplication.getContext(), String.valueOf(10000), "hagrid_wifi_ssid" + this.mUniqueId);
        if (TextUtils.isEmpty(b)) {
            b = SharedPreferenceManager.b(BaseApplication.getContext(), String.valueOf(10000), "hagrid_wifi_ssid" + this.mProductId);
            if (TextUtils.isEmpty(b)) {
                b = SharedPreferenceManager.b(BaseApplication.getContext(), String.valueOf(10000), "hagrid_wifi_ssid");
            }
        }
        if (!z) {
            b = "";
        }
        if (nsn.ae(BaseApplication.getContext())) {
            format = String.format(Locale.ENGLISH, getResources().getString(R.string.IDS_pad_device_auto_sync_data), new Object[0]);
        } else {
            format = String.format(Locale.ENGLISH, getResources().getString(R.string.IDS_device_request_wlan_auto_sync_data_sub_content), new Object[0]);
        }
        LogUtil.a(TAG, "refreshWifiName mWiFiDevice and nAdapter are not null and show config network ");
        this.mAdapter.a(createSettingItem(13, getViewString(R.string.IDS_device_wifi_config_network), format, b));
    }

    @Override // com.huawei.health.ecologydevice.ui.BaseFragment
    public boolean onBackPressed() {
        cgt.e().b();
        MeasurableDevice d = ceo.d().d(this.mUniqueId, true);
        if (d != null && !(d instanceof ctk) && this.mWeightInteractor != null && !cpa.ak(this.mProductId)) {
            this.mWeightInteractor.e(false);
            this.mWeightInteractor.onDestroy();
        }
        if (!"devicebind".equals(this.mGoto)) {
            return true;
        }
        this.mainActivity.setResult(-1);
        this.mainActivity.finish();
        return false;
    }

    public boolean isDestory() {
        FragmentActivity activity = getActivity();
        if (activity == null) {
            LogUtil.h(TAG_RELEASE, "DeviceMainActivity is null");
            return true;
        }
        if (!activity.isFinishing() && !activity.isDestroyed()) {
            return false;
        }
        LogUtil.h(TAG_RELEASE, "DeviceMainActivity is Destroyed");
        return true;
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroyView() {
        super.onDestroyView();
        if (this.mWiFiDevice != null) {
            ClaimWeightDataManager.INSTANCE.unRegisterCallBack(getClass().getSimpleName());
        }
        PopViewList popViewList = this.mPopView;
        if (popViewList != null && popViewList.a()) {
            LogUtil.a(TAG, "onDestroyView popview dismiss");
            this.mPopView.b();
        }
        LogUtil.a(TAG, "onDestroyView");
    }

    @Override // com.huawei.health.ecologydevice.ui.BaseFragment, androidx.fragment.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        LogUtil.a(TAG, "onDestroy start");
        EventBus.a(this.mEventManager, EVENT_SUBSCRIBE_LIST);
        cmv cmvVar = this.myHandler;
        if (cmvVar != null) {
            cmvVar.removeCallbacksAndMessages(null);
            this.myHandler.c();
            this.myHandler = null;
        }
        CountDownTimer countDownTimer = this.mLoadingConditionTimer;
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
        destroyLoadingDialog();
        clf clfVar = this.mConditionBeforeRequestHandler;
        if (clfVar != null) {
            clfVar.removeCallbacksAndMessages(null);
            this.mConditionBeforeRequestHandler.e();
            this.mConditionBeforeRequestHandler = null;
        }
    }

    private void dealDataManager() {
        if (!cpa.e(this.mProductId, this.mUniqueId)) {
            if (isBluetoothConnect()) {
                gotoDataMangerFragment();
                return;
            } else {
                nrh.b(BaseApplication.getContext(), R.string.IDS_plugin_device_weight_device_not_connect);
                return;
            }
        }
        if (cqh.c().KT_(this.mainActivity, this.mUniqueId)) {
            LogUtil.h(TAG, "dealDataManager return");
        } else if (!(ceo.d().d(this.mUniqueId, true) instanceof cxh)) {
            LogUtil.a(TAG, "otaUpdate showSelectBindDeviceDialog");
            showSelectBindDeviceDialog();
        } else {
            gotoDataMangerFragment();
        }
    }

    private void gotoDataMangerFragment() {
        LogUtil.a(TAG, "gotoDataMangerFragment");
        Bundle bundle = new Bundle();
        bundle.putParcelable("commonDeviceInfo", this.mContentValues);
        ctk ctkVar = this.mWiFiDevice;
        if (ctkVar != null) {
            bundle.putString("deviceId", ctkVar.d());
        }
        WiFiBodyFatScaleDataManagerFragment wiFiBodyFatScaleDataManagerFragment = new WiFiBodyFatScaleDataManagerFragment();
        wiFiBodyFatScaleDataManagerFragment.setArguments(bundle);
        switchFragment(wiFiBodyFatScaleDataManagerFragment);
    }

    @Override // androidx.fragment.app.Fragment
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i2 == -1 && i == 1001) {
            configWifi();
        }
        if (i == 1001 && i2 == 10005) {
            LogUtil.h(TAG, "privacy result enter");
            if (intent == null) {
                LogUtil.h(TAG, "onActivityResult data is null");
                return;
            } else if (intent.getBooleanExtra("revoke_honor_privacy", false)) {
                cof.d(false);
                cnc.b().a(ceo.d().d(this.mUniqueId, false));
            }
        }
        if (intent == null) {
            LogUtil.h(TAG, "onActivityResult data is null");
            return;
        }
        if (i2 == 1) {
            String stringExtra = intent.getStringExtra(HwPayConstant.KEY_USER_ID);
            if (TextUtils.isEmpty(stringExtra)) {
                LogUtil.h(TAG, "onActivityResult userId is null");
                return;
            }
            cfi singleUserById = MultiUsersManager.INSTANCE.getSingleUserById(stringExtra);
            if (singleUserById == null) {
                LogUtil.h(TAG, "onActivityResult newUser is null");
            } else {
                MultiUsersManager.INSTANCE.setCurrentUser(singleUserById);
                initUserData(MultiUsersManager.INSTANCE.getAllUser());
            }
        }
    }

    public void startVersionActivity(boolean z) {
        if (this.mWeightInteractor.b()) {
            Intent intent = new Intent();
            intent.setPackage(this.mainActivity.getPackageName());
            intent.setClassName(this.mainActivity.getPackageName(), "com.huawei.ui.device.activity.update.WeightUpdateVersionActivity");
            intent.putExtra("productId", this.mProductId);
            intent.putExtra("commonDeviceInfo", this.mContentValues);
            if (cpa.c(this.mProductId, this.mUniqueId)) {
                intent.putExtra("isUpdateDialog", true);
            } else {
                intent.putExtra("fromsetting", true);
            }
            intent.putExtra("user_type", z);
            startActivityForResult(intent, 1001);
            return;
        }
        nrh.c(BaseApplication.getContext(), R.string.IDS_plugin_device_weight_device_not_connect);
    }

    public void dealWithLoadingResult(boolean z) {
        CommonDialog21 commonDialog21;
        clf clfVar;
        if (getActivity() == null || getActivity().isFinishing() || (commonDialog21 = this.mLoadingDialog) == null || !commonDialog21.isShowing()) {
            LogUtil.h(TAG_RELEASE, "dealLoadingResult: deal with loading result fail");
            return;
        }
        if (z && (clfVar = this.mConditionBeforeRequestHandler) != null) {
            clfVar.sendEmptyMessage(11);
            return;
        }
        clf clfVar2 = this.mConditionBeforeRequestHandler;
        if (clfVar2 != null) {
            clfVar2.sendEmptyMessage(12);
        }
    }

    public void showSelectBindDeviceDialog() {
        if (cqa.a().d(this.mDownloadStatus)) {
            cqh.c().Lc_(this.mainActivity, new ScaleDialogCallback() { // from class: com.huawei.health.device.ui.measure.fragment.HagridDeviceManagerFragment$$ExternalSyntheticLambda5
                @Override // com.huawei.health.device.callback.ScaleDialogCallback
                public final void operationResult(int i) {
                    HagridDeviceManagerFragment.this.m164x8c97241(i);
                }
            });
        }
    }

    /* renamed from: lambda$showSelectBindDeviceDialog$14$com-huawei-health-device-ui-measure-fragment-HagridDeviceManagerFragment, reason: not valid java name */
    /* synthetic */ void m164x8c97241(int i) {
        if (i == 0) {
            if (cpe.c()) {
                downloadIndexAllResources();
                return;
            } else {
                cnb.a(this.mainActivity, this.mProductId, this.mUniqueId);
                return;
            }
        }
        LogUtil.h(TAG, "showSelectBindDeviceDialog negative");
    }

    public void setHuidType(int i) {
        this.mGetHuidType = i;
    }

    public int getHuidType() {
        return this.mGetHuidType;
    }

    public void setHuid(byte[] bArr) {
        this.mHuid = bArr == null ? null : (byte[]) bArr.clone();
    }

    public byte[] getHuid() {
        byte[] bArr = this.mHuid;
        if (bArr != null) {
            return (byte[]) bArr.clone();
        }
        return null;
    }

    public void setMainUser(boolean z) {
        this.mIsMainUser = z;
    }

    public boolean isMainUser() {
        return this.mIsMainUser;
    }

    public void setDevId(String str) {
        this.mDevId = str;
    }

    public ContentValues getContentValues() {
        return this.mContentValues;
    }

    public ctk getWiFiDevice() {
        return this.mWiFiDevice;
    }

    public void setWiFiDevice(ctk ctkVar) {
        this.mWiFiDevice = ctkVar;
    }

    public dcz getProductInfo() {
        return this.mProductInfo;
    }

    public cld getWeightInteractor() {
        return this.mWeightInteractor;
    }

    public String getProductId() {
        return this.mProductId;
    }

    public HagridDeviceManagerAdapter getAdapter() {
        return this.mAdapter;
    }

    public Activity getMainActivity() {
        return this.mainActivity;
    }

    public boolean isClickDeviceVersionUpdateItem() {
        return this.mIsClickDeviceVersionUpdateItem;
    }

    public String getUniqueId() {
        return this.mUniqueId;
    }
}
