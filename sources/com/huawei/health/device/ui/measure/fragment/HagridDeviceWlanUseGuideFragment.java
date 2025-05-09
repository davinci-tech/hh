package com.huawei.health.device.ui.measure.fragment;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.AbsoluteSizeSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import androidx.fragment.app.Fragment;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.R;
import com.huawei.health.device.callback.ScaleDialogCallback;
import com.huawei.health.device.fatscale.multiusers.MultiUsersManager;
import com.huawei.health.device.ui.measure.view.HagridUsageGuideBanner;
import com.huawei.health.device.ui.measure.view.holder.HealthViewPagerHolder;
import com.huawei.health.device.ui.measure.view.holder.HealthViewPagerHolderCreator;
import com.huawei.health.ecologydevice.manager.ResourceManager;
import com.huawei.health.ecologydevice.ui.BaseFragment;
import com.huawei.health.userprofilemgr.api.UserProfileMgrApi;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcloudmodel.callback.ICloudOperationResult;
import com.huawei.hwcloudmodel.model.CloudCommonReponse;
import com.huawei.hwcloudmodel.model.unite.WifiDeviceAddAuthMsgBySubUserReq;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.openalliance.ad.constant.ParamConstants;
import com.huawei.operation.utils.Constants;
import com.huawei.ui.commonui.button.HealthButton;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.titlebar.CustomTitleBar;
import com.huawei.ui.commonui.webview.WebViewActivity;
import com.huawei.up.api.UpApi;
import com.huawei.up.model.UserInfomation;
import defpackage.cfi;
import defpackage.cji;
import defpackage.cnq;
import defpackage.coc;
import defpackage.coy;
import defpackage.cpa;
import defpackage.cpp;
import defpackage.cpy;
import defpackage.cpz;
import defpackage.cqh;
import defpackage.csb;
import defpackage.cvx;
import defpackage.dcq;
import defpackage.dcx;
import defpackage.dcz;
import defpackage.dif;
import defpackage.gmz;
import defpackage.jbs;
import defpackage.koq;
import defpackage.nrf;
import defpackage.nrh;
import defpackage.nsi;
import defpackage.nsn;
import health.compact.a.CommonUtil;
import health.compact.a.LanguageUtil;
import health.compact.a.Services;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;
import health.compact.a.UnitUtil;
import health.compact.a.Utils;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* loaded from: classes3.dex */
public class HagridDeviceWlanUseGuideFragment extends BaseFragment implements View.OnClickListener {
    private static final int COLLECTION_DEFAULT_SIZE = 16;
    private static final int ERROR_CODE_NOT_BELONG = 112000000;
    private static final int ERROR_CODE_REQUEST_SENDING = 112000090;
    private static final int ERROR_CODE_REQUEST_TOO_FAST = 112000080;
    private static final String KEY_ACCOUNT_INFO = "accountInfo";
    private static final int LEFT_PADDING = 4;
    private static final String REACH_MAX_QUOTA_LIMIT = "reach max quota limit";
    private static final int REQUEST_CODE_READ_BECOME_ADMIN_HELP = 1000;
    private static final int REQUEST_CODE_READ_HAGRID_DEVICES_HELP = 1001;
    private static final String TAG = "HagridDeviceWlanUseGuideFragment";
    private static final int TEXT_SIZE = 13;
    private static int mViewType;
    private HealthTextView mBecomeAdminHelpTextView;
    private RelativeLayout mCfgWlanAfterCompleteUserInfoBottomBtnLayout;
    private LinearLayout mCfgWlanBottomLayout;
    private String mCloudDeviceId;
    private Context mContext;
    private ContentValues mDeviceInfo;
    private HealthTextView mDeviceWlanUseGuideTextView;
    private HagridUsageGuideBanner mHagridUsageGuideBanner;
    private boolean mIsNfcCommect;
    private HealthButton mKnowDetailBtn;
    private List<cnq> mList;
    private byte[] mMainHuid;
    private HealthTextView mNotNowKnowDetailTextView;
    private ImageView mNotNowRequestDeviceShareImageView;
    private LinearLayout mNotRequestDeviceShareLayout;
    private String mProductId;
    private HealthButton mRequestCfgWlanButton;
    private ImageView mRequestDeviceShareImageView;
    private LinearLayout mRequestDeviceShareLayout;
    private HealthTextView mTitleHintTextView;
    private HealthTextView mTopBecomeAdminHelpTextView;
    private String mUniqueId;
    private String mUrl;
    private LinearLayout mUseGuideBottomBtnLayout;
    private ImageView mUseGuideImage;
    private LinearLayout mUseGuideImageLayout;
    private gmz mUserProfileMgrUserPrivacy;
    private static final int[] USAGE_GUIDE_TITLES = {R.string.hagrid_guide_handle_title, R.string.hagrid_guide_implant_title, R.string.hagrid_guide_flat_title, R.string.hagrid_guide_bricks_title};
    private static final int[] HAGB29_USAGE_GUIDE_CONTENTS = {R.string.IDS_device_hagrid_b29_meausre_new, R.string.hagrid_guide_implant_content, R.string.hagrid_guide_flat_content, R.string.hagrid_guide_bricks_content};
    private static final int[] HAGB19_USAGE_GUIDE_CONTENTS = {R.string.IDS_device_hagrid_b19_meausre_new, R.string.hagrid_guide_implant_content, R.string.hagrid_guide_flat_content, R.string.hagrid_guide_bricks_content};
    private static final Pattern PATTERN = Pattern.compile("%[0-9]+\\$[s].");
    private String mUserName = "";
    private boolean mIsUploaded = false;
    private coy mFragmentHelper = new coy();
    private int mWidth = 0;
    private CountDownTimer mNetworkResponseMsgGetTimer = new CountDownTimer(5000, 1000) { // from class: com.huawei.health.device.ui.measure.fragment.HagridDeviceWlanUseGuideFragment.1
        @Override // android.os.CountDownTimer
        public void onTick(long j) {
        }

        @Override // android.os.CountDownTimer
        public void onFinish() {
            LogUtil.h(HagridDeviceWlanUseGuideFragment.TAG, "mNetworkResponseMsgGetTimer: Network response timeout.");
            nrh.c(HagridDeviceWlanUseGuideFragment.this.mContext, R.string._2130841392_res_0x7f020f30);
        }
    };

    private static String getUserName(Context context) {
        cfi mainUser = MultiUsersManager.INSTANCE.getMainUser();
        if (mainUser != null && !TextUtils.isEmpty(mainUser.h())) {
            LogUtil.a(TAG, "getUserName mainUser is not null");
            return mainUser.h();
        }
        UserInfomation userInfo = ((UserProfileMgrApi) Services.c("HWUserProfileMgr", UserProfileMgrApi.class)).getUserInfo();
        if (userInfo != null && !TextUtils.isEmpty(userInfo.getName())) {
            LogUtil.a(TAG, "getUserName userInfomation is not null");
            return userInfo.getName();
        }
        return new UpApi(context).getLegalAccountName();
    }

    @Override // com.huawei.health.ecologydevice.ui.BaseFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        LogUtil.a(TAG, "enter onCreate");
        this.mUserProfileMgrUserPrivacy = gmz.d();
    }

    @Override // com.huawei.health.ecologydevice.ui.BaseFragment, androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        LogUtil.a(TAG, "enter onCreateView");
        View onCreateView = super.onCreateView(layoutInflater, viewGroup, bundle);
        if (!(onCreateView instanceof ViewGroup)) {
            return onCreateView;
        }
        ViewGroup viewGroup2 = (ViewGroup) onCreateView;
        this.child = layoutInflater.inflate(R.layout.hagrid_device_wlan_use_guide_layout, viewGroup, false);
        this.mContext = BaseApplication.getContext();
        viewGroup2.removeView(this.mCustomTitleBar);
        viewGroup2.addView(this.child);
        initView();
        initData();
        initListener();
        updateView(mViewType);
        this.mWidth = nsn.h(this.mainActivity);
        return viewGroup2;
    }

    private void initView() {
        this.mUseGuideImage = (ImageView) this.child.findViewById(R.id.hagrid_device_wlan_use_guide_image);
        this.mNotNowRequestDeviceShareImageView = (ImageView) this.child.findViewById(R.id.hagrid_ble_device_not_now_request_device_share_img);
        this.mRequestDeviceShareImageView = (ImageView) this.child.findViewById(R.id.hagrid_ble_device_request_device_share_img);
        this.mUseGuideImageLayout = (LinearLayout) this.child.findViewById(R.id.hagrid_device_wlan_use_guide_image_layout);
        this.mCustomTitleBar = (CustomTitleBar) this.child.findViewById(R.id.hagrid_device_wlan_use_guide_title);
        this.mRequestCfgWlanButton = (HealthButton) this.child.findViewById(R.id.hagrid_ble_device_request_cfg_wlan);
        this.mBecomeAdminHelpTextView = (HealthTextView) this.child.findViewById(R.id.hagrid_ble_device_become_admin_help);
        this.mTopBecomeAdminHelpTextView = (HealthTextView) this.child.findViewById(R.id.hagrid_ble_top_device_become_admin_help);
        this.mRequestDeviceShareLayout = (LinearLayout) this.child.findViewById(R.id.hagrid_ble_device_btn_request_device_share_ll);
        this.mNotRequestDeviceShareLayout = (LinearLayout) this.child.findViewById(R.id.hagrid_ble_device_btn_not_now_request_device_share_ll);
        this.mCfgWlanBottomLayout = (LinearLayout) this.child.findViewById(R.id.hagrid_ble_device_cfg_wlan_bottom_btn_layout);
        this.mCfgWlanAfterCompleteUserInfoBottomBtnLayout = (RelativeLayout) this.child.findViewById(R.id.ble_device_cfg_wlan_after_complete_user_info_bottom_btn_layout);
        this.mKnowDetailBtn = (HealthButton) this.child.findViewById(R.id.hagrid_device_wlan_use_guide_know_detail);
        this.mNotNowKnowDetailTextView = (HealthTextView) this.child.findViewById(R.id.hagrid_device_wlan_use_guide_not_now_know_detail);
        this.mUseGuideBottomBtnLayout = (LinearLayout) this.child.findViewById(R.id.hagrid_device_wlan_use_guide_bottom_btn_layout);
        this.mDeviceWlanUseGuideTextView = (HealthTextView) this.child.findViewById(R.id.hagrid_devic_wlan_use_guide_text);
        HealthTextView healthTextView = (HealthTextView) this.child.findViewById(R.id.tv_device_device_introduction_prompt);
        this.mTitleHintTextView = healthTextView;
        healthTextView.setVisibility(8);
        HagridUsageGuideBanner hagridUsageGuideBanner = (HagridUsageGuideBanner) this.child.findViewById(R.id.hagrid_usage_guide_banner_view);
        this.mHagridUsageGuideBanner = hagridUsageGuideBanner;
        hagridUsageGuideBanner.setVisibility(8);
        refreshUseGuideImageLayout();
        this.mUseGuideImage.getLayoutParams().height = this.mUseGuideImageLayout.getLayoutParams().height;
        this.mUseGuideImage.getLayoutParams().width = this.mUseGuideImageLayout.getLayoutParams().width;
        Bitmap decodeResource = BitmapFactory.decodeResource(this.mContext.getResources(), R.drawable.hagrid_ble_device_config_wlan_img);
        ImageView imageView = this.mUseGuideImage;
        imageView.setImageBitmap(nrf.cJK_(decodeResource, imageView));
        if (LanguageUtil.bc(this.mContext)) {
            this.mNotNowRequestDeviceShareImageView.setBackgroundResource(R.drawable.wifi_device_arrow_right);
            this.mRequestDeviceShareImageView.setBackgroundResource(R.drawable.wifi_device_arrow_left);
        }
    }

    private void initData() {
        LogUtil.a(TAG, "enter initData");
        Bundle arguments = getArguments();
        if (arguments != null) {
            try {
                ContentValues contentValues = (ContentValues) arguments.getParcelable("commonDeviceInfo");
                this.mDeviceInfo = contentValues;
                if (contentValues != null) {
                    this.mProductId = contentValues.getAsString("productId");
                    this.mUniqueId = this.mDeviceInfo.getAsString("uniqueId");
                }
                if (TextUtils.isEmpty(this.mProductId) || TextUtils.isEmpty(this.mUniqueId)) {
                    LogUtil.h(TAG, "initData mProductId or mUniqueId is empty");
                }
                boolean z = arguments.getBoolean("isNfcConnect", false);
                this.mIsNfcCommect = z;
                LogUtil.a(TAG, "initData getBoolean mIsNfcCommect: ", Boolean.valueOf(z));
                this.mCloudDeviceId = arguments.getString("cloudDeviceId");
                this.mMainHuid = arguments.getByteArray("mainHuid");
                mViewType = arguments.getInt(ParamConstants.Param.VIEW_TYPE);
                ThreadPoolManager.d().execute(new Runnable() { // from class: com.huawei.health.device.ui.measure.fragment.HagridDeviceWlanUseGuideFragment$$ExternalSyntheticLambda7
                    @Override // java.lang.Runnable
                    public final void run() {
                        HagridDeviceWlanUseGuideFragment.this.m169xea7a7770();
                    }
                });
            } catch (ArrayIndexOutOfBoundsException unused) {
                LogUtil.b(TAG, "initData getByteArray index out of bounds");
            }
        } else {
            LogUtil.h(TAG, "initData bundle is null");
        }
        updateUserName();
    }

    /* renamed from: lambda$initData$0$com-huawei-health-device-ui-measure-fragment-HagridDeviceWlanUseGuideFragment, reason: not valid java name */
    /* synthetic */ void m169xea7a7770() {
        this.mUrl = cpy.b(this.mProductId);
    }

    private void initListener() {
        this.mRequestCfgWlanButton.setOnClickListener(this);
        this.mBecomeAdminHelpTextView.setOnClickListener(this);
        this.mTopBecomeAdminHelpTextView.setOnClickListener(this);
        this.mRequestDeviceShareLayout.setOnClickListener(this);
        this.mNotRequestDeviceShareLayout.setOnClickListener(this);
        this.mUseGuideBottomBtnLayout.setOnClickListener(this);
        this.mKnowDetailBtn.setOnClickListener(this);
        this.mNotNowKnowDetailTextView.setOnClickListener(this);
        this.mCustomTitleBar.setLeftButtonOnClickListener(new View.OnClickListener() { // from class: com.huawei.health.device.ui.measure.fragment.HagridDeviceWlanUseGuideFragment$$ExternalSyntheticLambda2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                HagridDeviceWlanUseGuideFragment.this.m170x8da10c3b(view);
            }
        });
    }

    /* renamed from: lambda$initListener$1$com-huawei-health-device-ui-measure-fragment-HagridDeviceWlanUseGuideFragment, reason: not valid java name */
    /* synthetic */ void m170x8da10c3b(View view) {
        this.mainActivity.onBackPressed();
        ViewClickInstrumentation.clickOnView(view);
    }

    private void updateView(int i) {
        LogUtil.a(TAG, "enter updateView viewType: ", Integer.valueOf(i));
        mViewType = i;
        switch (i) {
            case 12:
                uploadDeviceToCloud();
                showBleDeviceCfgWlanAfterCompleteUserInfoView();
                break;
            case 13:
                showBleDeviceCfgWlanEnterFromDeviceManagerView();
                break;
            case 14:
            case 15:
                showHagridDeviceUseGuideView();
                break;
            default:
                LogUtil.h(TAG, "updateView default");
                break;
        }
    }

    @Override // com.huawei.health.ecologydevice.ui.BaseFragment
    public boolean onBackPressed() {
        int i = mViewType;
        if ((i == 15) || (i == 14)) {
            gotoDeviceManagerFragment();
            return false;
        }
        if (i == 12) {
            updateView(14);
            return false;
        }
        return super.onBackPressed();
    }

    private boolean isShowAuthDialog() {
        if (!Utils.i() || !"true".equals(this.mUserProfileMgrUserPrivacy.c(7))) {
            return true;
        }
        LogUtil.h(TAG, "isShowAuthDialog false");
        return false;
    }

    private boolean isShowPersonalDialog() {
        if (!Utils.i() || !"true".equals(this.mUserProfileMgrUserPrivacy.c(2))) {
            return true;
        }
        LogUtil.h(TAG, "isShowPersonalDialog false");
        return false;
    }

    private void showUserPrivacyAuthDialog() {
        View inflate = View.inflate(this.mContext, R.layout.dialog_user_auth_message, null);
        initAuthDialogContent(inflate);
        dif.Vq_(this.mainActivity, inflate, new IBaseResponseCallback() { // from class: com.huawei.health.device.ui.measure.fragment.HagridDeviceWlanUseGuideFragment$$ExternalSyntheticLambda6
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public final void d(int i, Object obj) {
                HagridDeviceWlanUseGuideFragment.this.m175xbf581b6e(i, obj);
            }
        });
    }

    /* renamed from: lambda$showUserPrivacyAuthDialog$2$com-huawei-health-device-ui-measure-fragment-HagridDeviceWlanUseGuideFragment, reason: not valid java name */
    /* synthetic */ void m175xbf581b6e(int i, Object obj) {
        LogUtil.a(TAG, "showUserPrivacyAuthDialog, onResponse errorCode: ", Integer.valueOf(i));
        if (i == 0) {
            this.mUserProfileMgrUserPrivacy.c(7, true, TAG, (IBaseResponseCallback) null);
            this.mUserProfileMgrUserPrivacy.c(2, true, TAG, (IBaseResponseCallback) null);
            requestDeviceShare();
            setUserPrivacyAuthDialogSp("false");
            if (mViewType == 12) {
                updateView(14);
            }
        }
    }

    private void requestDeviceShare() {
        if (TextUtils.isEmpty(this.mCloudDeviceId)) {
            LogUtil.h(TAG, "requestDeviceShare get device id fail");
            nrh.c(this.mainActivity, R.string._2130842410_res_0x7f02132a);
            return;
        }
        byte[] bArr = this.mMainHuid;
        if (bArr == null || bArr.length == 0) {
            LogUtil.h(TAG, "requestDeviceShare get device huid fail");
            nrh.c(this.mainActivity, R.string._2130842410_res_0x7f02132a);
            return;
        }
        updateUserName();
        if (CommonUtil.aa(this.mContext)) {
            startNetworkResponseMsgGetCountDown();
        } else {
            nrh.c(this.mContext, R.string._2130841392_res_0x7f020f30);
        }
        final WifiDeviceAddAuthMsgBySubUserReq wifiDeviceAddAuthMsgBySubUserReq = new WifiDeviceAddAuthMsgBySubUserReq();
        wifiDeviceAddAuthMsgBySubUserReq.setDevId(this.mCloudDeviceId);
        wifiDeviceAddAuthMsgBySubUserReq.setMainHuid(getMainHuid());
        wifiDeviceAddAuthMsgBySubUserReq.setNickName(this.mUserName);
        LogUtil.a(TAG, "requestDeviceShare: ", wifiDeviceAddAuthMsgBySubUserReq.toString());
        csb.a().b(this.mProductId, this.mUniqueId, new IBaseResponseCallback() { // from class: com.huawei.health.device.ui.measure.fragment.HagridDeviceWlanUseGuideFragment$$ExternalSyntheticLambda3
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public final void d(int i, Object obj) {
                HagridDeviceWlanUseGuideFragment.this.m173xb6837474(wifiDeviceAddAuthMsgBySubUserReq, i, obj);
            }
        });
    }

    /* renamed from: lambda$requestDeviceShare$4$com-huawei-health-device-ui-measure-fragment-HagridDeviceWlanUseGuideFragment, reason: not valid java name */
    /* synthetic */ void m173xb6837474(WifiDeviceAddAuthMsgBySubUserReq wifiDeviceAddAuthMsgBySubUserReq, int i, Object obj) {
        if (i == 0) {
            stopNetworkResponseMsgGetCountDown();
            LogUtil.a(TAG, "requestDeviceShare cloud have device");
            nrh.c(this.mainActivity, R.string._2130842410_res_0x7f02132a);
            return;
        }
        jbs.a(this.mainActivity).a(wifiDeviceAddAuthMsgBySubUserReq, new ICloudOperationResult() { // from class: com.huawei.health.device.ui.measure.fragment.HagridDeviceWlanUseGuideFragment$$ExternalSyntheticLambda0
            @Override // com.huawei.hwcloudmodel.callback.ICloudOperationResult
            public final void operationResult(Object obj2, String str, boolean z) {
                HagridDeviceWlanUseGuideFragment.this.m172xaf5a9233((CloudCommonReponse) obj2, str, z);
            }
        });
    }

    /* renamed from: lambda$requestDeviceShare$3$com-huawei-health-device-ui-measure-fragment-HagridDeviceWlanUseGuideFragment, reason: not valid java name */
    /* synthetic */ void m172xaf5a9233(CloudCommonReponse cloudCommonReponse, String str, boolean z) {
        LogUtil.a(TAG, "requestDeviceShare request result: ", Boolean.valueOf(z));
        stopNetworkResponseMsgGetCountDown();
        dealRequestResult(cloudCommonReponse, str, z);
    }

    private void stopNetworkResponseMsgGetCountDown() {
        LogUtil.a(TAG, "stopNetworkResponseMsgGetCountDown.");
        CountDownTimer countDownTimer = this.mNetworkResponseMsgGetTimer;
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
    }

    private void startNetworkResponseMsgGetCountDown() {
        LogUtil.a(TAG, "startNetworkResponseMsgGetCountDown.");
        CountDownTimer countDownTimer = this.mNetworkResponseMsgGetTimer;
        if (countDownTimer != null) {
            countDownTimer.cancel();
            this.mNetworkResponseMsgGetTimer.start();
        }
    }

    private void showDeviceNotBelongUserCauseDeviceShareFailDialog() {
        cqh.c().Ld_(this.mainActivity, new ScaleDialogCallback() { // from class: com.huawei.health.device.ui.measure.fragment.HagridDeviceWlanUseGuideFragment$$ExternalSyntheticLambda4
            @Override // com.huawei.health.device.callback.ScaleDialogCallback
            public final void operationResult(int i) {
                HagridDeviceWlanUseGuideFragment.this.m174xdf92deb1(i);
            }
        });
    }

    /* renamed from: lambda$showDeviceNotBelongUserCauseDeviceShareFailDialog$5$com-huawei-health-device-ui-measure-fragment-HagridDeviceWlanUseGuideFragment, reason: not valid java name */
    /* synthetic */ void m174xdf92deb1(int i) {
        if (i == 0) {
            openDeviceHelp(1000);
        } else {
            LogUtil.a(TAG, "showDeviceNotBelongUserCauseDeviceShareFailDialog NegativeButton");
        }
    }

    private void openDeviceHelp(int i) {
        boolean o = Utils.o();
        boolean u = CommonUtil.u(this.mContext);
        StringBuilder sb = new StringBuilder();
        sb.append(this.mUrl);
        if (i == 1001) {
            sb.append(cpa.e(this.mProductId));
        } else if (o) {
            sb.append(String.format(Locale.ENGLISH, "/handbook/Jumppage/EMUI8.0/C001B001/en-US/index.html?lang=%1$s&devicetype=Scale-manager", CommonUtil.u()));
        } else if (u) {
            sb.append("/SmartWear/Scale-manager/EMUI8.0/C001B001/zh-CN/index.html");
        } else {
            sb.append("/SmartWear/Scale-manager/EMUI8.0/C001B001/en-US/index.html");
        }
        openHagridDeviceAppHelpActivity(sb.toString(), i);
    }

    private void openHagridDeviceAppHelpActivity(String str, int i) {
        int i2;
        this.mainActivity = BaseApplication.getActivity();
        if (this.mainActivity == null || this.mainActivity.isFinishing() || this.mainActivity.isDestroyed()) {
            LogUtil.h(TAG, "mainActivity is invalid");
            return;
        }
        LogUtil.a(TAG, "openHagridDeviceAppHelpActivity enter");
        if ((getSelectFragment(HagridDeviceManagerFragment.class) instanceof BaseFragment ? (BaseFragment) getSelectFragment(HagridDeviceManagerFragment.class) : null) == null || (i2 = mViewType) == 14 || i2 == 15) {
            LogUtil.h(TAG, "openHagridDeviceAppHelpActivity is null or mViewType = 14 or 15");
            gotoDeviceManagerFragment();
        }
        Intent intent = new Intent(this.mainActivity, (Class<?>) WebViewActivity.class);
        intent.putExtra("WebViewActivity.REQUEST_URL_KEY", str);
        intent.putExtra(Constants.JUMP_MODE_KEY, 8);
        this.mainActivity.startActivityForResult(intent, i);
    }

    private void dealRequestResult(CloudCommonReponse cloudCommonReponse, String str, boolean z) {
        if (z) {
            nrh.c(this.mainActivity, R.string.IDS_hw_device_wifi_subuser_request_authorize_send_success);
            if (mViewType == 13) {
                gotoDeviceManagerFragment();
                return;
            }
            return;
        }
        if (cloudCommonReponse == null) {
            LogUtil.h(TAG, "dealRequestResult rsp is null");
            return;
        }
        int intValue = cloudCommonReponse.getResultCode().intValue();
        if (intValue == ERROR_CODE_NOT_BELONG) {
            showDeviceNotBelongUserCauseDeviceShareFailDialog();
            return;
        }
        int i = R.string._2130842410_res_0x7f02132a;
        if (intValue == ERROR_CODE_REQUEST_TOO_FAST) {
            if (str.contains(REACH_MAX_QUOTA_LIMIT)) {
                i = R.string.IDS_device_wifi_request_limit;
            }
            nrh.c(this.mainActivity, i);
        } else if (intValue == ERROR_CODE_REQUEST_SENDING) {
            nrh.c(this.mainActivity, R.string.IDS_hw_device_wifi_subuser_request_authorize_sending);
        } else {
            LogUtil.b(TAG, "request fail: ", str);
            nrh.c(this.mainActivity, R.string._2130842410_res_0x7f02132a);
        }
    }

    private String getMainHuid() {
        byte[] bArr = new byte[19];
        System.arraycopy(this.mMainHuid, 0, bArr, 0, 19);
        String e = cvx.e(cvx.d(bArr));
        return !TextUtils.isEmpty(e) ? e.trim() : e;
    }

    private void updateUserName() {
        String userName = getUserName(this.mainActivity);
        if (!TextUtils.isEmpty(userName)) {
            this.mUserName = userName;
        }
        LogUtil.a(TAG, "updateUserName");
    }

    private void setUserPrivacyAuthDialogSp(String str) {
        SharedPreferenceManager.e(cpp.a(), String.valueOf(10000), "request_sync_data_dialog", str, new StorageParams());
    }

    private void initAuthDialogContent(View view) {
        if (view == null) {
            LogUtil.h(TAG, "initAuthDialogContent, dialogView is null");
            return;
        }
        StringBuffer stringBuffer = new StringBuffer(16);
        if (isShowAuthDialog()) {
            if (Utils.o()) {
                stringBuffer.append(System.lineSeparator()).append(getResources().getString(R.string._2130840570_res_0x7f020bfa));
            } else {
                stringBuffer.append(System.lineSeparator()).append(getResources().getString(R.string.IDS_device_haige_user_permission_message_health_item));
            }
        }
        if (isShowPersonalDialog()) {
            stringBuffer.append(System.lineSeparator()).append(getResources().getString(R.string.IDS_device_wifi_user_permission_dialog_personal_infomation_message));
        }
        SpannableString spannableString = new SpannableString(getResources().getString(R.string.IDS_device_haige_user_permission_message_sub, (isShowPersonalDialog() || isShowAuthDialog()) ? getResources().getString(R.string.IDS_device_haige_user_permission_message_other, stringBuffer) : ""));
        nsi.cKH_(spannableString, stringBuffer.toString(), new AbsoluteSizeSpan(13, true));
        ((HealthTextView) view.findViewById(R.id.dialog_text_alert_message)).setText(spannableString);
    }

    private boolean isShowUserPrivacyAuthDialog() {
        String b = SharedPreferenceManager.b(this.mContext, Integer.toString(10000), "request_sync_data_dialog");
        return "true".equals(b) || TextUtils.isEmpty(b);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.hagrid_ble_device_request_cfg_wlan || id == R.id.hagrid_ble_device_btn_request_device_share_ll) {
            this.mFragmentHelper.a(this.mainActivity, new IBaseResponseCallback() { // from class: com.huawei.health.device.ui.measure.fragment.HagridDeviceWlanUseGuideFragment$$ExternalSyntheticLambda5
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public final void d(int i, Object obj) {
                    HagridDeviceWlanUseGuideFragment.this.m171x14a040eb(i, obj);
                }
            });
        } else if (id == R.id.hagrid_ble_device_become_admin_help || id == R.id.hagrid_ble_top_device_become_admin_help) {
            openDeviceHelp(1000);
        } else if (id == R.id.hagrid_ble_device_btn_not_now_request_device_share_ll) {
            updateView(14);
        } else if (id == R.id.hagrid_device_wlan_use_guide_know_detail) {
            String string = this.mContext.getString(R.string._2130840648_res_0x7f020c48);
            if (!TextUtils.isEmpty(string) && string.equals(this.mKnowDetailBtn.getText().toString())) {
                gotoDeviceManagerFragment();
                ViewClickInstrumentation.clickOnView(view);
                return;
            }
            openDeviceHelp(1001);
        } else if (id == R.id.hagrid_device_wlan_use_guide_not_now_know_detail) {
            String string2 = this.mContext.getString(R.string._2130840649_res_0x7f020c49);
            if (!TextUtils.isEmpty(string2) && string2.equals(this.mNotNowKnowDetailTextView.getText().toString())) {
                openDeviceHelp(1001);
                ViewClickInstrumentation.clickOnView(view);
                return;
            }
            gotoDeviceManagerFragment();
        } else {
            LogUtil.h(TAG, "onClick: You click view unknown.");
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    /* renamed from: lambda$onClick$6$com-huawei-health-device-ui-measure-fragment-HagridDeviceWlanUseGuideFragment, reason: not valid java name */
    /* synthetic */ void m171x14a040eb(int i, Object obj) {
        LogUtil.a(TAG, "onClick: onResponse errCode: ", Integer.valueOf(i));
        cpz.a(this.mUniqueId, this.mProductId);
        clickBleDeviceRequestDeviceShareButton();
    }

    private void gotoDeviceManagerFragment() {
        if (getSelectFragment(HagridDeviceManagerFragment.class) != null) {
            popupFragment(HagridDeviceManagerFragment.class);
        }
        Bundle bundle = new Bundle();
        bundle.putString("productId", this.mProductId);
        bundle.putString("goto", "devicebind");
        bundle.putParcelable("commonDeviceInfo", this.mDeviceInfo);
        bundle.putBoolean("isNfcConnect", this.mIsNfcCommect);
        LogUtil.a(TAG, "gotoDeviceManagerFragment put isNfcConnect is true");
        HagridDeviceManagerFragment hagridDeviceManagerFragment = new HagridDeviceManagerFragment();
        hagridDeviceManagerFragment.setArguments(bundle);
        uploadDeviceToCloud();
        switchFragment((Fragment) null, hagridDeviceManagerFragment);
    }

    private void uploadDeviceToCloud() {
        if (this.mIsUploaded) {
            return;
        }
        coy.b(this.mProductId, this.mUniqueId);
        this.mIsUploaded = true;
    }

    private void clickBleDeviceRequestDeviceShareButton() {
        if (isShowUserPrivacyAuthDialog()) {
            showUserPrivacyAuthDialog();
            return;
        }
        requestDeviceShare();
        if (mViewType == 12) {
            updateView(14);
        }
    }

    private void showHagridDeviceUseGuideView() {
        int dimensionPixelSize = this.mContext.getResources().getDimensionPixelSize(R.dimen._2131364635_res_0x7f0a0b1b) - nsn.c(BaseApplication.getActivity(), 4.0f);
        this.mCustomTitleBar.setPadding(dimensionPixelSize, 0, dimensionPixelSize, 0);
        this.mCustomTitleBar.setTitleText(this.mContext.getString(R.string.IDS_device_hygride_use_guide_note));
        this.mCustomTitleBar.setLeftButtonVisibility(8);
        this.mCfgWlanAfterCompleteUserInfoBottomBtnLayout.setVisibility(8);
        this.mTopBecomeAdminHelpTextView.setVisibility(8);
        this.mCfgWlanBottomLayout.setVisibility(8);
        this.mUseGuideBottomBtnLayout.setVisibility(0);
        Bitmap decodeResource = BitmapFactory.decodeResource(this.mContext.getResources(), R.drawable.hagrid_device_wlan_use_guide_img);
        boolean a2 = cji.a(this.mUniqueId, 39);
        LogUtil.a(TAG, "support prompt handle measure: ", Boolean.valueOf(a2));
        if (a2 || cpa.ao(this.mProductId)) {
            this.mTitleHintTextView.setVisibility(0);
            this.mDeviceWlanUseGuideTextView.setText(this.mContext.getString(R.string.IDS_device_hagrid_instructions));
            dcz d = ResourceManager.e().d(this.mProductId);
            if (d == null) {
                LogUtil.h(TAG, "ProductInfo is null");
                ImageView imageView = this.mUseGuideImage;
                imageView.setImageBitmap(nrf.cJK_(decodeResource, imageView));
                return;
            }
            ArrayList<dcz.d> ad = d.ad();
            if (koq.b(ad)) {
                LogUtil.h(TAG, "getMode useGuides is null or size is zero");
                ImageView imageView2 = this.mUseGuideImage;
                imageView2.setImageBitmap(nrf.cJK_(decodeResource, imageView2));
                return;
            }
            ArrayList<Object> useGuideImage = getUseGuideImage(ad);
            if (koq.b(useGuideImage)) {
                LogUtil.h(TAG, "getUseGuideImage useGuidesImgArray is null or size is zero");
                return;
            }
            ArrayList<String> useGuideText = getUseGuideText(ad);
            if (koq.b(useGuideText)) {
                LogUtil.h(TAG, "getUseGuideText useGuidesTextArray is null or size is zero");
                return;
            }
            ArrayList<String> useGuideTitle = getUseGuideTitle(ad);
            if (koq.b(useGuideTitle)) {
                LogUtil.h(TAG, "getUseGuideText useGuidesTitleArray is null or size is zero");
                return;
            }
            this.mTitleHintTextView.setVisibility(8);
            this.mDeviceWlanUseGuideTextView.setVisibility(8);
            this.mUseGuideImage.setVisibility(8);
            setHagridUsageGuideBannerData(useGuideImage, useGuideText, useGuideTitle);
            return;
        }
        this.mTitleHintTextView.setVisibility(8);
        ImageView imageView3 = this.mUseGuideImage;
        imageView3.setImageBitmap(nrf.cJK_(decodeResource, imageView3));
        this.mDeviceWlanUseGuideTextView.setText(this.mContext.getString(R.string.IDS_device_hagrid_scale_use_guide_description_1));
    }

    private void setHagridUsageGuideBannerData(ArrayList<Object> arrayList, ArrayList<String> arrayList2, ArrayList<String> arrayList3) {
        this.mHagridUsageGuideBanner.setVisibility(0);
        this.mKnowDetailBtn.setText(this.mContext.getString(R.string._2130840648_res_0x7f020c48));
        this.mNotNowKnowDetailTextView.setText(this.mContext.getString(R.string._2130840649_res_0x7f020c49));
        this.mList = new ArrayList(10);
        for (int i = 0; i < arrayList.size(); i++) {
            cnq cnqVar = new cnq();
            cnqVar.c(arrayList.get(i));
            cnqVar.b(arrayList3.get(i));
            String str = arrayList2.get(i);
            String[] strArr = new String[10];
            Matcher matcher = PATTERN.matcher(str);
            int i2 = 0;
            while (matcher.find()) {
                int i3 = i2 + 1;
                strArr[i2] = UnitUtil.e(i3, 1, 0);
                i2 = i3;
            }
            String format = String.format(Locale.ROOT, str, strArr);
            LogUtil.a(TAG, "counter is :", Integer.valueOf(i2), ", content is ", format);
            cnqVar.a(format);
            this.mList.add(cnqVar);
        }
        this.mHagridUsageGuideBanner.setViewPagerData(this.mList, new HealthViewPagerHolderCreator() { // from class: com.huawei.health.device.ui.measure.fragment.HagridDeviceWlanUseGuideFragment$$ExternalSyntheticLambda1
            @Override // com.huawei.health.device.ui.measure.view.holder.HealthViewPagerHolderCreator
            public final HealthViewPagerHolder createViewHolder() {
                return HagridDeviceWlanUseGuideFragment.lambda$setHagridUsageGuideBannerData$7();
            }
        });
    }

    static /* synthetic */ HealthViewPagerHolder lambda$setHagridUsageGuideBannerData$7() {
        return new coc();
    }

    @Override // com.huawei.health.ecologydevice.ui.BaseFragment, androidx.fragment.app.Fragment
    public void onResume() {
        HagridUsageGuideBanner hagridUsageGuideBanner;
        super.onResume();
        LogUtil.a(TAG, "enter onResume");
        if (cji.a(this.mUniqueId, 39) && (hagridUsageGuideBanner = this.mHagridUsageGuideBanner) != null && !hagridUsageGuideBanner.e()) {
            LogUtil.a(TAG, "onResume HagridUsageGuideBanner startLoop");
            this.mHagridUsageGuideBanner.b();
            this.mHagridUsageGuideBanner.d();
        } else {
            LogUtil.a(TAG, "onResume HagridUsageGuideBanner startLoop else");
            HagridUsageGuideBanner hagridUsageGuideBanner2 = this.mHagridUsageGuideBanner;
            if (hagridUsageGuideBanner2 != null) {
                hagridUsageGuideBanner2.b();
                this.mHagridUsageGuideBanner.d();
            }
        }
    }

    @Override // com.huawei.health.ecologydevice.ui.BaseFragment, androidx.fragment.app.Fragment
    public void onPause() {
        super.onPause();
        LogUtil.a(TAG, "enter onPause");
        if (!cji.a(this.mUniqueId, 39) || this.mHagridUsageGuideBanner == null) {
            return;
        }
        LogUtil.a(TAG, "onPause HagridUsageGuideBanner stopLoop");
        this.mHagridUsageGuideBanner.b();
    }

    private void showBleDeviceCfgWlanAfterCompleteUserInfoView() {
        int dimensionPixelSize = this.mContext.getResources().getDimensionPixelSize(R.dimen._2131364635_res_0x7f0a0b1b) - nsn.c(BaseApplication.getActivity(), 4.0f);
        this.mCustomTitleBar.setPadding(dimensionPixelSize, 0, dimensionPixelSize, 0);
        this.mCustomTitleBar.setTitleText(getString(R.string.IDS_device_wifi_config_network));
        this.mCustomTitleBar.setLeftButtonVisibility(8);
        Bitmap decodeResource = BitmapFactory.decodeResource(this.mContext.getResources(), R.drawable.hagrid_ble_device_config_wlan_img);
        ImageView imageView = this.mUseGuideImage;
        imageView.setImageBitmap(nrf.cJK_(decodeResource, imageView));
        this.mDeviceWlanUseGuideTextView.setText(getWlanUseGuideText(getArguments(), true));
        this.mCfgWlanAfterCompleteUserInfoBottomBtnLayout.setVisibility(0);
        this.mTopBecomeAdminHelpTextView.setVisibility(0);
        this.mUseGuideBottomBtnLayout.setVisibility(8);
        this.mCfgWlanBottomLayout.setVisibility(8);
    }

    private void showBleDeviceCfgWlanEnterFromDeviceManagerView() {
        this.mCustomTitleBar.setPadding(0, 0, 0, 0);
        this.mCustomTitleBar.setTitleText(getString(R.string.IDS_device_user_manager));
        this.mCustomTitleBar.setLeftButtonVisibility(0);
        Bitmap decodeResource = BitmapFactory.decodeResource(this.mContext.getResources(), R.drawable.hagrid_ble_device_config_wlan_img);
        ImageView imageView = this.mUseGuideImage;
        imageView.setImageBitmap(nrf.cJK_(decodeResource, imageView));
        this.mDeviceWlanUseGuideTextView.setText(getWlanUseGuideText(getArguments(), false));
        this.mCfgWlanAfterCompleteUserInfoBottomBtnLayout.setVisibility(8);
        this.mTopBecomeAdminHelpTextView.setVisibility(8);
        this.mUseGuideBottomBtnLayout.setVisibility(8);
        this.mCfgWlanBottomLayout.setVisibility(0);
    }

    @Override // com.huawei.health.ecologydevice.ui.BaseFragment, androidx.fragment.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        stopNetworkResponseMsgGetCountDown();
    }

    @Override // androidx.fragment.app.Fragment
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == 1001) {
            gotoDeviceManagerFragment();
        }
    }

    private String getWlanUseGuideText(Bundle bundle, boolean z) {
        String string;
        String string2 = this.mContext.getString(R.string.IDS_device_hygride_bound_by_another);
        if (bundle == null || TextUtils.isEmpty(bundle.getString(KEY_ACCOUNT_INFO))) {
            LogUtil.h(TAG, "bundle or accountInfo is null.");
            return string2;
        }
        String string3 = bundle.getString(KEY_ACCOUNT_INFO);
        if (z) {
            string = this.mContext.getString(R.string.IDS_device_show_next);
        } else {
            string = this.mContext.getString(R.string.IDS_device_wifi_config_send_authority);
        }
        return String.format(Locale.ROOT, this.mContext.getString(R.string.IDS_hw_device_apply_wifiuser_new), string3, string);
    }

    @Override // com.huawei.health.ecologydevice.ui.BaseFragment, androidx.fragment.app.Fragment, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LogUtil.a(TAG, "onConfigurationChanged, refresh page");
        refreshUseGuideImageLayout();
        super.onConfigurationChanged(configuration);
        int h = nsn.h(this.mainActivity);
        if (h <= 0) {
            LogUtil.h(TAG, "invalid width");
            return;
        }
        int i = this.mWidth;
        if (h != i) {
            LogUtil.a(TAG, "lastWindowWidth: ", Integer.valueOf(i), "getCurrentWindowWidth: ", Integer.valueOf(h));
            this.mWidth = h;
            HagridUsageGuideBanner hagridUsageGuideBanner = this.mHagridUsageGuideBanner;
            if (hagridUsageGuideBanner != null) {
                hagridUsageGuideBanner.getLayoutParams().width = h;
                this.mHagridUsageGuideBanner.b();
            }
        }
    }

    private void refreshUseGuideImageLayout() {
        LinearLayout linearLayout = this.mUseGuideImageLayout;
        if (linearLayout != null) {
            nsn.cLA_(linearLayout, 2);
        }
    }

    private ArrayList<Object> getUseGuideImage(ArrayList<dcz.d> arrayList) {
        LogUtil.a(TAG, "WeightMeasureGuideFragment getUseGuideImage()");
        ArrayList<Object> arrayList2 = new ArrayList<>(10);
        Iterator<dcz.d> it = arrayList.iterator();
        while (it.hasNext()) {
            arrayList2.add(dcq.b().a(this.mProductId, it.next().e()));
        }
        LogUtil.a(TAG, "WeightMeasureGuideFragment imgList: ", Integer.valueOf(arrayList2.size()));
        return arrayList2;
    }

    private ArrayList<String> getUseGuideText(ArrayList<dcz.d> arrayList) {
        LogUtil.a(TAG, "WeightMeasureGuideFragment getUseGuideText()");
        ArrayList<String> arrayList2 = new ArrayList<>(10);
        for (int i = 0; i < arrayList.size(); i++) {
            dcz.d dVar = arrayList.get(i);
            String d = dcx.d(this.mProductId, dVar.c());
            if (TextUtils.isEmpty(d)) {
                if (TextUtils.isEmpty(dVar.c())) {
                    int[] iArr = HAGB19_USAGE_GUIDE_CONTENTS;
                    int length = iArr.length;
                    int[] iArr2 = HAGB29_USAGE_GUIDE_CONTENTS;
                    if (i < Math.min(length, iArr2.length)) {
                        if (this.mProductId.equals("8358eb90-b40d-11e9-a2a3-2a2ae2dbcce4")) {
                            d = this.mContext.getString(iArr[i]);
                        } else {
                            d = this.mContext.getString(iArr2[i]);
                        }
                    }
                }
                d = this.mContext.getResources().getString(dcx.e(dVar.c()));
            }
            LogUtil.a(TAG, "getUseGuideText text is ", dVar.c(), ", value is ", d);
            arrayList2.add(d);
        }
        LogUtil.a(TAG, "WeightMeasureGuideFragment TextList: ", Integer.valueOf(arrayList2.size()));
        return arrayList2;
    }

    private ArrayList<String> getUseGuideTitle(ArrayList<dcz.d> arrayList) {
        LogUtil.a(TAG, "WeightMeasureGuideFragment getUseGuideTitle()");
        ArrayList<String> arrayList2 = new ArrayList<>(10);
        for (int i = 0; i < arrayList.size(); i++) {
            dcz.d dVar = arrayList.get(i);
            String d = dcx.d(this.mProductId, dVar.b());
            if (TextUtils.isEmpty(d)) {
                if (TextUtils.isEmpty(dVar.b())) {
                    int[] iArr = USAGE_GUIDE_TITLES;
                    if (i < iArr.length) {
                        d = this.mContext.getString(iArr[i]);
                    }
                }
                d = this.mContext.getResources().getString(dcx.e(dVar.b()));
            }
            LogUtil.a(TAG, "getUseGuideText title is ", dVar.b(), ", value is ", d);
            arrayList2.add(d);
        }
        LogUtil.a(TAG, "WeightMeasureGuideFragment TitleList: ", Integer.valueOf(arrayList2.size()));
        return arrayList2;
    }
}
