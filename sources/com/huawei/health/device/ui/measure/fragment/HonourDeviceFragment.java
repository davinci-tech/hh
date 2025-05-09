package com.huawei.health.device.ui.measure.fragment;

import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import androidx.fragment.app.Fragment;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.R;
import com.huawei.health.device.connectivity.comm.MeasurableDevice;
import com.huawei.health.device.model.HealthDevice;
import com.huawei.health.device.open.MeasureController;
import com.huawei.health.device.open.MeasureKit;
import com.huawei.health.device.ui.DeviceMainActivity;
import com.huawei.health.device.ui.measure.BaseInteractor;
import com.huawei.health.device.wifi.lib.handler.StaticHandler;
import com.huawei.health.ecologydevice.manager.ResourceManager;
import com.huawei.health.ecologydevice.ui.BaseFragment;
import com.huawei.health.ecologydevice.ui.measure.activity.DeviceUsageDescriptionActivity;
import com.huawei.health.versionmgr.api.VersionMgrApi;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hihealth.device.open.HealthDevice;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.nfc.PluginPayAdapter;
import com.huawei.operation.utils.Constants;
import com.huawei.pluginachievement.manager.model.KakaConstants;
import com.huawei.ui.commonui.dialog.CommonDialog21;
import com.huawei.ui.commonui.dialog.NoTitleCustomAlertDialog;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.popupview.PopViewList;
import com.huawei.ui.commonui.webview.WebViewActivity;
import defpackage.ceo;
import defpackage.cfl;
import defpackage.cjx;
import defpackage.ckq;
import defpackage.cld;
import defpackage.cle;
import defpackage.cnt;
import defpackage.coa;
import defpackage.cpa;
import defpackage.cpe;
import defpackage.cpp;
import defpackage.cpw;
import defpackage.cpz;
import defpackage.ctk;
import defpackage.dcx;
import defpackage.dcz;
import defpackage.dfg;
import defpackage.dij;
import defpackage.dks;
import defpackage.jah;
import defpackage.nqc;
import defpackage.nsf;
import defpackage.nsn;
import defpackage.nsy;
import health.compact.a.CommonUtil;
import health.compact.a.CompileParameterUtil;
import health.compact.a.GRSManager;
import health.compact.a.LanguageUtil;
import health.compact.a.Services;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/* loaded from: classes3.dex */
public class HonourDeviceFragment extends BaseFragment implements View.OnClickListener {
    private static final String AM16_SETTINGS = "am16_settings";
    private static final String CONNECT_STATUS = "status";
    private static final String CONNECT_TYPE_LONG_TAG = "type";
    private static final int DELAY_TIME = 3000;
    private static final int DELETE_DEVICE = 0;
    private static final String DEVICE_KIND = "kind";
    private static final String DOWNLOAD_STATUS = "false";
    private static final String GO_BACK_TAG = "goback";
    private static final String HONOUR_DEVICE = "honour_device";
    private static final String HUAWEI_FIT = "HUAWEI FIT";
    private static final int MESSAGE_DOWNLOAD_FAIL = 2;
    private static final int MESSAGE_DOWNLOAD_NOFILE = 3;
    private static final int MESSAGE_DOWNLOAD_RESOURCE_IN_PROGRESS = 1;
    private static final int MESSAGE_DOWNLOAD_RESOURCE_SUCCESS = 0;
    private static final String METIS_PRODUCTID = "9323f6b7-b459-44f4-a698-988d1769832a";
    private static final String OPEN_SERVICE = "OpenService";
    private static final int POLLEN_CLUB = 2;
    private static final int SERVICE_HOT_LINE = 1;
    private static final String SERVICE_XINQING_INDEX_URL = "/heartIndex/index.html";
    private static final String TAG = "HonourDeviceFragment";
    private static final int TIME_LOST = 120000;
    private static final int UP_VIEW_TYPE = 3;
    private static final String VIEW_TYPE = "view";
    private static final String VIEW_TYPE_MEASURE = "measure";
    private HealthTextView mBodyFatScaleDeviceStartMeasure;
    private ImageView mCleanUserDataArrowImg;
    private RelativeLayout mCleanUserDataLayout;
    private String mClubHost;
    private ImageView mDeviceImg;
    private ImageView mFirmwareUpdateArrowImg;
    private RelativeLayout mFirmwareUpdateLayout;
    private String mGoto;
    private LinearLayout mHeadPhoneShowLayout;
    private RelativeLayout mHeartFineIndexNumberLayout;
    private ImageView mHeartRateMeasureStatusImage;
    private BaseInteractor mInteractor;
    private CommonDialog21 mLoadingDialog;
    private HealthTextView mOtherTextView;
    private String mProductId;
    private dcz mProductInfo;
    private coa mServiceControl;
    private List<cnt> mServiceList;
    private ImageView mShowMoreDataArrowImage;
    private LinearLayout mShowMoreDataLayout;
    private RelativeLayout mSleepPressureLayout;
    private RelativeLayout mTahitiUserGuideLayout;
    private String mUrl;
    private RelativeLayout mUserGuideLayout;
    private RelativeLayout mWeightDataFromSmartLifeLayout;
    private LinearLayout mWeightDeviceShowLayout;
    private LinearLayout mWeightLayoutContainer;
    private LinearLayout mWeightValueLayoutContainer;
    private String mUniqueId = null;
    private ContentValues mDeviceInfo = null;
    private Handler mHandler = new InnerStaticHandler(this);

    @Override // com.huawei.health.ecologydevice.ui.BaseFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    private boolean isNeedUpdateAm16Resource() {
        LogUtil.a(TAG, "init get AM16 resource");
        return getAm16ResourceStatus();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void saveAm16ResourceStatus() {
        SharedPreferenceManager.e(cpp.a(), "BondDevice", AM16_SETTINGS, DOWNLOAD_STATUS, new StorageParams());
    }

    private boolean getAm16ResourceStatus() {
        String b = SharedPreferenceManager.b(cpp.a(), "BondDevice", AM16_SETTINGS);
        LogUtil.a(TAG, "isDownloadFile is ", b);
        return !DOWNLOAD_STATUS.equals(b);
    }

    protected void showmLoadingDialog(int i) {
        if (this.mLoadingDialog == null) {
            new CommonDialog21(getActivity(), R.style.app_update_dialogActivity);
            CommonDialog21 a2 = CommonDialog21.a(getActivity());
            this.mLoadingDialog = a2;
            a2.e(getActivity().getString(i));
            this.mLoadingDialog.a();
            this.mLoadingDialog.setCancelable(false);
            return;
        }
        LogUtil.h(TAG, "mLoadingDialog is not null");
    }

    protected void destroymLoadingDialog() {
        CommonDialog21 commonDialog21 = this.mLoadingDialog;
        if (commonDialog21 != null) {
            commonDialog21.cancel();
            this.mLoadingDialog = null;
            LogUtil.a(TAG, "destroy mLoadingDialog");
        }
    }

    private void downloadAm16Resource() {
        if (isNeedUpdateAm16Resource() && cpa.z(this.mProductId)) {
            showmLoadingDialog(R.string._2130840672_res_0x7f020c60);
            this.mHandler.sendEmptyMessageDelayed(2, 120000L);
            cpe.e(this.mProductId, new IBaseResponseCallback() { // from class: com.huawei.health.device.ui.measure.fragment.HonourDeviceFragment$$ExternalSyntheticLambda7
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public final void d(int i, Object obj) {
                    HonourDeviceFragment.this.m205x411879ec(i, obj);
                }
            });
        }
    }

    /* renamed from: lambda$downloadAm16Resource$0$com-huawei-health-device-ui-measure-fragment-HonourDeviceFragment, reason: not valid java name */
    /* synthetic */ void m205x411879ec(int i, Object obj) {
        if (i == 1) {
            this.mHandler.sendEmptyMessage(0);
            return;
        }
        if (i == -11) {
            this.mHandler.sendEmptyMessage(3);
        } else if (i == 0) {
            this.mHandler.sendEmptyMessage(1);
        } else {
            this.mHandler.sendEmptyMessage(2);
            LogUtil.h(TAG, "MESSAGE_DOWNLOAD_FAIL response, errorCode: ", Integer.valueOf(i));
        }
    }

    @Override // com.huawei.health.ecologydevice.ui.BaseFragment, androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.child = layoutInflater.inflate(R.layout.fragment_honour_device_setting, viewGroup, false);
        View onCreateView = super.onCreateView(layoutInflater, viewGroup, bundle);
        init();
        initListener();
        if (onCreateView instanceof ViewGroup) {
            ((ViewGroup) onCreateView).addView(this.child);
        }
        return onCreateView;
    }

    private void initListener() {
        this.mBodyFatScaleDeviceStartMeasure.setOnClickListener(this);
        this.mHeartRateMeasureStatusImage.setOnClickListener(this);
        this.mUserGuideLayout.setOnClickListener(this);
        this.mTahitiUserGuideLayout.setOnClickListener(this);
        this.mHeartFineIndexNumberLayout.setOnClickListener(this);
        this.mSleepPressureLayout.setOnClickListener(this);
        this.mCleanUserDataLayout.setOnClickListener(this);
        this.mFirmwareUpdateLayout.setOnClickListener(this);
        this.mShowMoreDataLayout.setOnClickListener(this);
    }

    private void openAppHelpActivity(String str) {
        LogUtil.c(TAG, "openAppHelpActivity useUrl: ", str);
        cpz.a();
        Intent intent = new Intent(this.mainActivity, (Class<?>) WebViewActivity.class);
        intent.putExtra("WebViewActivity.REQUEST_URL_KEY", str);
        intent.putExtra(Constants.JUMP_MODE_KEY, 8);
        try {
            this.mainActivity.startActivity(intent);
        } catch (ActivityNotFoundException unused) {
            LogUtil.b(TAG, "openAppHelpActivity activity exception");
        }
    }

    private void openHeartIndexActivity() {
        LogUtil.a(TAG, "openHeartIndexActivity to enter");
        String url = GRSManager.a(BaseApplication.getContext()).getUrl("healthRecommendUrl");
        LogUtil.c(TAG, "openHeartIndexActivity recommendHost: ", url);
        if (this.mServiceList.size() > 1) {
            startService(this.mServiceList.get(1));
            return;
        }
        if (TextUtils.isEmpty(url)) {
            LogUtil.h(TAG, "openHeartIndexActivity recommendHost is empty");
            return;
        }
        LogUtil.a(TAG, "xinqing service is null, create OpenService");
        cnt cntVar = new cnt();
        cntVar.q(url + SERVICE_XINQING_INDEX_URL);
        cntVar.k("xinqing");
        cntVar.f(this.mainActivity.getString(R.string._2130842596_res_0x7f0213e4));
        startService(cntVar);
    }

    private void openSleepDecompression() {
        LogUtil.a(TAG, "openSleepDecompression to enter");
        if (this.mServiceList.size() > 1) {
            startService(this.mServiceList.get(0));
            return;
        }
        String e = jah.c().e("domain_www_heart_ide");
        if (CommonUtil.cc()) {
            e = jah.c().e("domain_web_debug_psy");
        }
        if (TextUtils.isEmpty(e)) {
            LogUtil.h(TAG, "openSleepDecompression relaxHost is empty");
            return;
        }
        LogUtil.a(TAG, "shumian service is null , create OpenService");
        cnt cntVar = new cnt();
        cntVar.q(e + CompileParameterUtil.c("SERVICE_SHUMIAN_RELAX_URL", ""));
        cntVar.k(KakaConstants.DECOMPRESSI0N);
        cntVar.f(this.mainActivity.getString(R.string._2130842597_res_0x7f0213e5));
        startService(cntVar);
    }

    private void init() {
        ContentValues contentValues = (ContentValues) getArguments().getParcelable("commonDeviceInfo");
        this.mDeviceInfo = contentValues;
        if (contentValues != null) {
            this.mProductId = contentValues.getAsString("productId");
            this.mUniqueId = this.mDeviceInfo.getAsString("uniqueId");
        } else {
            this.mProductId = getArguments().getString("productId");
        }
        if (!TextUtils.isEmpty(this.mProductId) && TextUtils.isEmpty(this.mUniqueId)) {
            HealthDevice d = ceo.d().d(this.mProductId);
            if (d != null) {
                this.mUniqueId = d.getUniqueId();
            } else {
                LogUtil.h(TAG, "init device is null, productId: ", cpw.d(this.mProductId));
                this.mainActivity.onBackPressed();
            }
        }
        dfg.d().c(this.mProductId);
        LogUtil.a(TAG, "HonourDeviceFragment init is ", this.mProductId, ", isHonorWeightScale: ", Boolean.valueOf(dfg.d().b()));
        this.mGoto = getArguments().getString("goto");
        intUrl();
        if (TextUtils.isEmpty(this.mClubHost)) {
            ThreadPoolManager.d().execute(new Runnable() { // from class: com.huawei.health.device.ui.measure.fragment.HonourDeviceFragment$$ExternalSyntheticLambda1
                @Override // java.lang.Runnable
                public final void run() {
                    HonourDeviceFragment.this.m206x80c90116();
                }
            });
        }
        dcz d2 = ResourceManager.e().d(this.mProductId);
        this.mProductInfo = d2;
        if (d2 != null) {
            cfl.a().e(this.mProductInfo.s(), ResourceManager.e().c(this.mProductId) + File.separator + this.mProductInfo.k());
        }
        initView();
        initData();
        cfl.a().c("54C9739F-CA5C-4347-9F00-75B9DDF2C649");
        initOpenService();
        downloadAm16Resource();
    }

    /* renamed from: lambda$init$1$com-huawei-health-device-ui-measure-fragment-HonourDeviceFragment, reason: not valid java name */
    /* synthetic */ void m206x80c90116() {
        this.mClubHost = GRSManager.a(BaseApplication.getContext()).getNoCheckUrl("domainHuafenUrl", "");
    }

    private void intUrl() {
        if (TextUtils.isEmpty(this.mUrl)) {
            ThreadPoolManager.d().execute(new Runnable() { // from class: com.huawei.health.device.ui.measure.fragment.HonourDeviceFragment$$ExternalSyntheticLambda4
                @Override // java.lang.Runnable
                public final void run() {
                    HonourDeviceFragment.this.m208x507aff87();
                }
            });
        }
    }

    /* renamed from: lambda$intUrl$2$com-huawei-health-device-ui-measure-fragment-HonourDeviceFragment, reason: not valid java name */
    /* synthetic */ void m208x507aff87() {
        String accountInfo = LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1010);
        if (TextUtils.isEmpty(accountInfo)) {
            accountInfo = "SG";
        }
        this.mUrl = GRSManager.a(BaseApplication.getContext()).getNoCheckUrl("domainTipsResDbankcdn", accountInfo);
    }

    private void initData() {
        initHeadPhoneAndWeightDeviceView();
        if (cpa.z(this.mProductId)) {
            this.mInteractor = new cle(this.mainActivity, this.child, this.mProductId, this.mUniqueId);
            return;
        }
        if (cpa.ab(this.mProductId)) {
            if (this.mInteractor == null) {
                this.mInteractor = cld.d(this.mProductId, this.mUniqueId);
            }
            BaseInteractor baseInteractor = this.mInteractor;
            if (baseInteractor instanceof cld) {
                ((cld) baseInteractor).HN_(this.mainActivity, this.child);
                ((cld) this.mInteractor).a();
            }
            if (getActivity() instanceof DeviceMainActivity) {
                ((DeviceMainActivity) getActivity()).c(HonourDeviceFragment.class);
            }
            if ("33123f39-7fc1-420b-9882-a4b0d6c61100".equals(this.mProductId)) {
                this.mDeviceImg.setImageResource(R.drawable.pic_huawei_device_manager_img);
                return;
            } else if ("ccd1f0f8-8c57-4bd7-a884-0ef38482f15f".equals(this.mProductId)) {
                this.mDeviceImg.setImageResource(R.drawable.pic_honor_device_manager_img);
                return;
            } else {
                this.mDeviceImg.setImageResource(R.drawable.pic_huawei_home_device_img);
                return;
            }
        }
        LogUtil.h(TAG, "initData() is other Device");
    }

    private void initHeadPhoneAndWeightDeviceView() {
        if (cpa.z(this.mProductId)) {
            this.mHeadPhoneShowLayout.setVisibility(0);
            this.mWeightDeviceShowLayout.setVisibility(8);
            this.mOtherTextView.setVisibility(8);
            this.mFirmwareUpdateLayout.setVisibility(8);
            this.mCleanUserDataLayout.setVisibility(8);
            fitTahiti();
            return;
        }
        this.mHeadPhoneShowLayout.setVisibility(8);
        this.mWeightDeviceShowLayout.setVisibility(0);
        this.mOtherTextView.setVisibility(0);
        this.mFirmwareUpdateLayout.setVisibility(0);
        this.mCleanUserDataLayout.setVisibility(0);
    }

    private void fitTahiti() {
        if (nsn.ag(this.mainActivity)) {
            this.mTahitiUserGuideLayout.setVisibility(0);
            this.mUserGuideLayout.setVisibility(8);
        } else {
            this.mTahitiUserGuideLayout.setVisibility(8);
            this.mUserGuideLayout.setVisibility(0);
        }
    }

    @Override // com.huawei.health.ecologydevice.ui.BaseFragment
    public void initViewTahiti() {
        super.initViewTahiti();
        LogUtil.a(TAG, "initViewTahiti() to enter");
        if (cpa.z(this.mProductId)) {
            fitTahiti();
        }
    }

    private void initView() {
        this.mDeviceImg = (ImageView) nsy.cMd_(this.child, R.id.device_img);
        this.mHeadPhoneShowLayout = (LinearLayout) nsy.cMd_(this.child, R.id.head_phone_show_layout);
        this.mTahitiUserGuideLayout = (RelativeLayout) nsy.cMd_(this.child, R.id.tahiti_user_guide_layout);
        this.mWeightDeviceShowLayout = (LinearLayout) nsy.cMd_(this.child, R.id.weight_device_show_layout);
        this.mHeartRateMeasureStatusImage = (ImageView) nsy.cMd_(this.child, R.id.heart_rate_measuring_status_img);
        this.mHeartFineIndexNumberLayout = (RelativeLayout) nsy.cMd_(this.child, R.id.heart_fine_index_number_layout);
        this.mSleepPressureLayout = (RelativeLayout) nsy.cMd_(this.child, R.id.sleep_pressure_layout);
        this.mBodyFatScaleDeviceStartMeasure = (HealthTextView) nsy.cMd_(this.child, R.id.weight_start_measure_tv);
        this.mOtherTextView = (HealthTextView) nsy.cMd_(this.child, R.id.other_text_view);
        this.mCleanUserDataLayout = (RelativeLayout) nsy.cMd_(this.child, R.id.clean_user_data_layout);
        this.mFirmwareUpdateLayout = (RelativeLayout) nsy.cMd_(this.child, R.id.firmware_update_layout);
        this.mCleanUserDataArrowImg = (ImageView) nsy.cMd_(this.child, R.id.clean_user_data_arrow_img);
        this.mFirmwareUpdateArrowImg = (ImageView) nsy.cMd_(this.child, R.id.firmware_update_arrow_img);
        this.mUserGuideLayout = (RelativeLayout) nsy.cMd_(this.child, R.id.user_guide_layout);
        this.mWeightValueLayoutContainer = (LinearLayout) nsy.cMd_(this.child, R.id.weight_value_layout_container);
        this.mWeightLayoutContainer = (LinearLayout) nsy.cMd_(this.child, R.id.weight_layout_container);
        this.mWeightDataFromSmartLifeLayout = (RelativeLayout) nsy.cMd_(this.child, R.id.weight_data_from_smart_life_layout);
        this.mShowMoreDataLayout = (LinearLayout) nsy.cMd_(this.child, R.id.more_data_show_layout);
        this.mShowMoreDataArrowImage = (ImageView) nsy.cMd_(this.child, R.id.smart_life_img_arrow);
        if (LanguageUtil.bc(this.mainActivity)) {
            this.mCleanUserDataArrowImg.setBackground(getResources().getDrawable(R.drawable._2131427841_res_0x7f0b0201));
            this.mFirmwareUpdateArrowImg.setBackground(getResources().getDrawable(R.drawable._2131427841_res_0x7f0b0201));
            this.mShowMoreDataArrowImage.setBackground(getResources().getDrawable(R.drawable._2131427841_res_0x7f0b0201));
        } else {
            this.mCleanUserDataArrowImg.setBackground(getResources().getDrawable(R.drawable._2131427842_res_0x7f0b0202));
            this.mFirmwareUpdateArrowImg.setBackground(getResources().getDrawable(R.drawable._2131427842_res_0x7f0b0202));
            this.mShowMoreDataArrowImage.setBackground(getResources().getDrawable(R.drawable._2131427842_res_0x7f0b0202));
        }
        setTitle(this.mProductId, this.mProductInfo);
        this.mDeviceImg.setImageResource(R.mipmap._2131821431_res_0x7f110377);
        Resources resources = BaseApplication.getContext().getResources();
        if (resources != null) {
            this.mCustomTitleBar.setTitleBarBackgroundColor(resources.getColor(R.color._2131299340_res_0x7f090c0c));
            this.mCustomTitleBar.setRightButtonDrawable(resources.getDrawable(R.drawable._2131430194_res_0x7f0b0b32), nsf.h(R.string._2130850635_res_0x7f02334b));
        }
        this.mCustomTitleBar.setRightButtonVisibility(0);
        this.mCustomTitleBar.setRightButtonOnClickListener(new View.OnClickListener() { // from class: com.huawei.health.device.ui.measure.fragment.HonourDeviceFragment$$ExternalSyntheticLambda3
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                HonourDeviceFragment.this.m207x582fa65d(view);
            }
        });
    }

    /* renamed from: lambda$initView$3$com-huawei-health-device-ui-measure-fragment-HonourDeviceFragment, reason: not valid java name */
    /* synthetic */ void m207x582fa65d(View view) {
        showUnbindDeviceMenu();
        ViewClickInstrumentation.clickOnView(view);
    }

    private void showUnbindDeviceMenu() {
        ArrayList arrayList;
        if (this.mainActivity == null || this.mCustomTitleBar == null) {
            LogUtil.h(TAG, "mainActivity or mCustomTitleBar is null");
            return;
        }
        if (cpa.z(this.mProductId)) {
            arrayList = new ArrayList(Arrays.asList(getResources().getString(R.string.IDS_device_wear_home_delete_device), getResources().getString(R.string._2130841514_res_0x7f020faa), getResources().getString(R.string._2130847825_res_0x7f022851)));
        } else {
            arrayList = new ArrayList(Arrays.asList(getResources().getString(R.string.IDS_device_wear_home_delete_device)));
        }
        new PopViewList(this.mainActivity, this.mCustomTitleBar, arrayList).e(new PopViewList.PopViewClickListener() { // from class: com.huawei.health.device.ui.measure.fragment.HonourDeviceFragment$$ExternalSyntheticLambda0
            @Override // com.huawei.ui.commonui.popupview.PopViewList.PopViewClickListener
            public final void setOnClick(int i) {
                HonourDeviceFragment.this.m209x3c544231(i);
            }
        });
    }

    /* renamed from: lambda$showUnbindDeviceMenu$4$com-huawei-health-device-ui-measure-fragment-HonourDeviceFragment, reason: not valid java name */
    /* synthetic */ void m209x3c544231(int i) {
        if (i == 0) {
            deleteDevice();
            return;
        }
        if (i == 1) {
            callServiceHotLine();
        } else if (i == 2) {
            enterPollenClub();
        } else {
            LogUtil.h(TAG, "unknown position : ", Integer.valueOf(i));
        }
    }

    private void deleteDevice() {
        HashMap hashMap = new HashMap(16);
        hashMap.put("click", "1");
        hashMap.put(PluginPayAdapter.KEY_DEVICE_INFO_NAME, dks.e(this.mProductId, this.mProductInfo.n().b()));
        unbindDevice(hashMap);
    }

    private void callServiceHotLine() {
        ResolveInfo resolveActivity;
        LogUtil.a(TAG, "callServiceHotLine to enter");
        cpz.e();
        Intent intent = new Intent("android.intent.action.DIAL", Uri.parse(KakaConstants.SCHEME_TEL + BaseApplication.getContext().getString(R.string._2130846301_res_0x7f02225d)));
        PackageManager packageManager = this.mainActivity.getPackageManager();
        if (packageManager == null || (resolveActivity = packageManager.resolveActivity(intent, 1048576)) == null) {
            return;
        }
        intent.setComponent(new ComponentName(resolveActivity.activityInfo.packageName, resolveActivity.activityInfo.name));
        try {
            this.mainActivity.startActivity(intent);
        } catch (ActivityNotFoundException unused) {
            LogUtil.b(TAG, "callServiceHotLine exception");
        }
    }

    private void enterPollenClub() {
        cpz.c();
        if (TextUtils.isEmpty(this.mClubHost)) {
            this.mClubHost = "https:/";
            LogUtil.h(TAG, "enterPollenClub mClubHost is empty");
        }
        String str = this.mClubHost + "/mhw/consumer/cn/community/mhwnews/allcircle/id_10000008";
        LogUtil.c(TAG, "enterPollenClub() -> pollenClubUrl: ", str, ", jumpModeKey: ", 1);
        Intent intent = new Intent(this.mainActivity, (Class<?>) com.huawei.operation.activity.WebViewActivity.class);
        intent.putExtra("url", str);
        intent.putExtra(Constants.JUMP_MODE_KEY, 1);
        try {
            this.mainActivity.startActivity(intent);
        } catch (ActivityNotFoundException unused) {
            LogUtil.b(TAG, "enterPollenClub exception");
        }
    }

    private void setTitle(String str, dcz dczVar) {
        if (dczVar == null) {
            LogUtil.h(TAG, "setTitle error productInfo is null");
            return;
        }
        if (dczVar.n() != null) {
            String d = dcx.d(str, dczVar.n().b());
            if (METIS_PRODUCTID.equals(str) && !LanguageUtil.ba(cpp.a()) && !LanguageUtil.ab(cpp.a()) && !LanguageUtil.m(cpp.a())) {
                d = HUAWEI_FIT;
            }
            String f = cpa.f(this.mUniqueId);
            if (!TextUtils.isEmpty(f)) {
                d = d + com.huawei.openalliance.ad.constant.Constants.LINK + f;
            }
            setTitle(d);
            return;
        }
        LogUtil.h(TAG, "the productInfo.getManifest() is null");
    }

    private void initOpenService() {
        this.mServiceList = new ArrayList(10);
        coa a2 = coa.a(this.mainActivity);
        this.mServiceControl = a2;
        List<cnt> d = a2.d();
        if (d != null && d.size() > 0) {
            LogUtil.a(TAG, "mServiceList size is ", Integer.valueOf(d.size()));
            for (cnt cntVar : d) {
                if (cntVar.e().contains(KakaConstants.DECOMPRESSI0N)) {
                    this.mServiceList.add(cntVar);
                } else if (cntVar.e().contains("xinqing")) {
                    this.mServiceList.add(cntVar);
                } else {
                    LogUtil.a(TAG, "initOpenService() serviceID: ", cntVar.e());
                }
            }
        }
        cnt.a(this.mServiceList);
        LogUtil.a(TAG, "mServiceList selected size is ", Integer.valueOf(this.mServiceList.size()));
    }

    private void unbindDevice(final Map<String, Object> map) {
        if (isNoneBondedDevice()) {
            NoTitleCustomAlertDialog.Builder builder = new NoTitleCustomAlertDialog.Builder(getActivity());
            builder.e(R.string.IDS_device_selection_cancel_unbind_device).czC_(R.string.IDS_device_ui_dialog_yes, new View.OnClickListener() { // from class: com.huawei.health.device.ui.measure.fragment.HonourDeviceFragment$$ExternalSyntheticLambda5
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    HonourDeviceFragment.this.m210x6e88d056(map, view);
                }
            }).czz_(R.string.IDS_device_ui_dialog_no, new View.OnClickListener() { // from class: com.huawei.health.device.ui.measure.fragment.HonourDeviceFragment$$ExternalSyntheticLambda6
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    HonourDeviceFragment.lambda$unbindDevice$6(view);
                }
            });
            NoTitleCustomAlertDialog e = builder.e();
            e.setCancelable(false);
            e.show();
        }
    }

    /* renamed from: lambda$unbindDevice$5$com-huawei-health-device-ui-measure-fragment-HonourDeviceFragment, reason: not valid java name */
    /* synthetic */ void m210x6e88d056(Map map, View view) {
        switchDeviceUnbind(map);
        ViewClickInstrumentation.clickOnView(view);
    }

    static /* synthetic */ void lambda$unbindDevice$6(View view) {
        LogUtil.a(TAG, "unbindDevice NegativeButton");
        ViewClickInstrumentation.clickOnView(view);
    }

    private void switchDeviceUnbind(Map<String, Object> map) {
        LogUtil.a(TAG, "switchDeviceUnbind");
        VersionMgrApi versionMgrApi = (VersionMgrApi) Services.c("HWVersionMgr", VersionMgrApi.class);
        if (cjx.e().h(this.mProductId)) {
            if (cjx.e().h(this.mProductId, this.mUniqueId)) {
                cjx.e().e(this.mProductId, this.mUniqueId);
                cjx.e().b(this.mProductId, this.mUniqueId);
                if (cpa.ab(this.mProductId)) {
                    cpa.j(this.mUniqueId, "");
                    versionMgrApi.resetBandUpdate(this.mProductId, this.mUniqueId);
                }
                versionMgrApi.resetBandUpdate(this.mProductId, this.mUniqueId);
                cpz.c(map);
                getActivity().finish();
                return;
            }
            return;
        }
        String str = this.mProductId;
        if (str != null && cpa.ab(str)) {
            prepare();
        }
        if (ceo.d().n(this.mUniqueId)) {
            cjx.e().e(this.mProductId, this.mUniqueId, -5);
            if (cpa.ab(this.mProductId)) {
                cpa.j(this.mUniqueId, "");
                versionMgrApi.resetBandUpdate(this.mProductId, this.mUniqueId);
            }
            cpz.c(map);
            getActivity().finish();
        }
    }

    private void prepare() {
        com.huawei.health.device.model.HealthDevice a2 = cjx.e().a(this.mProductId);
        dcz d = ResourceManager.e().d(this.mProductId);
        if (d != null) {
            MeasureKit g = cjx.e().g(d.s());
            if (g == null) {
                LogUtil.h(TAG, "kit is null");
                return;
            }
            MeasureController measureController = g.getMeasureController();
            Bundle bundle = new Bundle();
            bundle.putInt("type", -5);
            bundle.putString("productId", this.mProductId);
            if (measureController != null) {
                measureController.prepare(a2, null, bundle);
                return;
            }
            return;
        }
        LogUtil.h(TAG, "switchDeviceUnbind productInfo is null");
    }

    private void startService(cnt cntVar) {
        BaseInteractor baseInteractor = this.mInteractor;
        if (baseInteractor instanceof cle) {
            ((cle) baseInteractor).d();
        }
        Intent intent = new Intent(this.mainActivity, (Class<?>) com.huawei.operation.activity.WebViewActivity.class);
        intent.putExtra("url", cntVar.b());
        intent.putExtra("EXTRA_BI_ID", cntVar.e());
        intent.putExtra("EXTRA_BI_NAME", cntVar.c());
        intent.putExtra("EXTRA_BI_SOURCE", "OpenService");
        intent.putExtra(Constants.IS_START_FROM_HEART_RATE_BOARD, true);
        intent.putExtra("type", Constants.OPEN_SERVICE_TYPE);
        try {
            this.mainActivity.startActivity(intent);
        } catch (ActivityNotFoundException unused) {
            LogUtil.b(TAG, "startService exception.");
        }
        cpz.d(cntVar.e(), cntVar.c());
    }

    @Override // com.huawei.health.ecologydevice.ui.BaseFragment, androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        BaseInteractor baseInteractor = this.mInteractor;
        if (baseInteractor != null) {
            baseInteractor.onResume();
        }
        showWeightDataView();
    }

    @Override // com.huawei.health.ecologydevice.ui.BaseFragment, androidx.fragment.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        BaseInteractor baseInteractor = this.mInteractor;
        if (baseInteractor instanceof cld) {
            cld cldVar = (cld) baseInteractor;
            cldVar.e();
            cldVar.c();
            cldVar.e(false);
            MeasurableDevice d = ceo.d().d(this.mUniqueId, true);
            if (d == null || (d instanceof ctk)) {
                return;
            }
            d.disconnect();
        }
    }

    private boolean isNoneBondedDevice() {
        return cjx.e().h(this.mProductId) ? cjx.e().d(this.mProductId) != null : ceo.d().d(this.mUniqueId, false) != null;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view == this.mBodyFatScaleDeviceStartMeasure) {
            startMeasure();
        } else if (view == this.mUserGuideLayout) {
            openUserGuideActivity();
        } else if (view == this.mTahitiUserGuideLayout) {
            openUserGuideActivity();
        } else {
            RelativeLayout relativeLayout = this.mHeartFineIndexNumberLayout;
            if (view == relativeLayout) {
                if (relativeLayout.isEnabled()) {
                    openHeartIndexActivity();
                }
            } else {
                RelativeLayout relativeLayout2 = this.mSleepPressureLayout;
                if (view == relativeLayout2) {
                    if (relativeLayout2.isEnabled()) {
                        openSleepDecompression();
                    }
                } else if (view == this.mCleanUserDataLayout) {
                    this.mInteractor.controller(5, null);
                } else if (view == this.mShowMoreDataLayout) {
                    dij.UZ_(this.mainActivity);
                } else if (view == this.mFirmwareUpdateLayout) {
                    this.mInteractor.controller(6, null);
                } else if (view == this.mHeartRateMeasureStatusImage) {
                    pleasePluginHeadsets();
                } else {
                    LogUtil.h(TAG, "onClick view not exist");
                }
            }
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    private void openUserGuideActivity() {
        String e = cpa.e(this.mProductId);
        if (TextUtils.isEmpty(e) || TextUtils.isEmpty(this.mUrl)) {
            LogUtil.h(TAG, "openUserGuideActivity url is empty");
            Intent intent = new Intent(this.mainActivity, (Class<?>) DeviceUsageDescriptionActivity.class);
            intent.putExtra("productId", this.mProductId);
            try {
                startActivity(intent);
                return;
            } catch (ActivityNotFoundException unused) {
                LogUtil.b(TAG, "openUserGuideActivity exception");
                return;
            }
        }
        LogUtil.c(TAG, "openUserGuideActivity url: ", this.mUrl, e);
        openAppHelpActivity(this.mUrl + e);
    }

    private void startMeasure() {
        HealthDevice.HealthDeviceKind l = this.mProductInfo.l();
        Fragment a2 = ckq.a(l.name());
        if (a2 != null) {
            BaseInteractor baseInteractor = this.mInteractor;
            if (baseInteractor != null) {
                baseInteractor.setClean();
            }
            Bundle bundle = new Bundle();
            bundle.putString(VIEW_TYPE, VIEW_TYPE_MEASURE);
            bundle.putString(DEVICE_KIND, l.name());
            bundle.putString("productId", this.mProductId);
            bundle.putString(GO_BACK_TAG, HONOUR_DEVICE);
            BaseInteractor baseInteractor2 = this.mInteractor;
            if (baseInteractor2 instanceof cld) {
                bundle.putBoolean("status", ((cld) baseInteractor2).b());
            }
            bundle.putInt("type", -1);
            bundle.putBoolean("activeMeasure", true);
            ContentValues contentValues = new ContentValues();
            contentValues.put("productId", this.mProductId);
            contentValues.put("uniqueId", this.mUniqueId);
            bundle.putParcelable("commonDeviceInfo", contentValues);
            a2.setArguments(bundle);
            switchFragment(a2);
        }
    }

    private void pleasePluginHeadsets() {
        View inflate = View.inflate(this.mainActivity, R.layout.honor_ear_phone_connect_notice_pop_view, null);
        ((HealthTextView) inflate.findViewById(R.id.heart_rate_ear_phone_notice)).setText(String.format(Locale.ENGLISH, getResources().getString(R.string.IDS_pad_hw_device_earp_insert), nsn.i(BaseApplication.getContext())));
        final nqc nqcVar = new nqc(this.mainActivity, inflate);
        nqcVar.cEh_(this.mHeartRateMeasureStatusImage, 3);
        new Handler().postDelayed(new Runnable() { // from class: com.huawei.health.device.ui.measure.fragment.HonourDeviceFragment$$ExternalSyntheticLambda2
            @Override // java.lang.Runnable
            public final void run() {
                nqc.this.b();
            }
        }, 3000L);
    }

    @Override // com.huawei.health.ecologydevice.ui.BaseFragment
    public boolean onBackPressed() {
        BaseInteractor baseInteractor = this.mInteractor;
        if (baseInteractor != null) {
            baseInteractor.onDestroy();
            BaseInteractor baseInteractor2 = this.mInteractor;
            if (baseInteractor2 instanceof cld) {
                cld cldVar = (cld) baseInteractor2;
                cldVar.e();
                cldVar.e(false);
                MeasurableDevice d = ceo.d().d(this.mUniqueId, true);
                if (d != null && !(d instanceof ctk)) {
                    d.disconnect();
                }
            }
        }
        if (!"devicebind".equals(this.mGoto)) {
            return true;
        }
        this.mainActivity.setResult(-1);
        this.mainActivity.finish();
        return false;
    }

    private void showWeightDataView() {
        if (cpa.z(this.mProductId)) {
            return;
        }
        DeviceMainActivity deviceMainActivity = getActivity() instanceof DeviceMainActivity ? (DeviceMainActivity) getActivity() : null;
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

    static class InnerStaticHandler extends StaticHandler<HonourDeviceFragment> {
        InnerStaticHandler(HonourDeviceFragment honourDeviceFragment) {
            super(honourDeviceFragment);
        }

        @Override // com.huawei.health.device.wifi.lib.handler.StaticHandler
        public void handleMessage(HonourDeviceFragment honourDeviceFragment, Message message) {
            if (honourDeviceFragment == null || !honourDeviceFragment.isAdded() || honourDeviceFragment.isDetached()) {
                return;
            }
            if (message == null) {
                LogUtil.h(HonourDeviceFragment.TAG, "can not handle null message");
                return;
            }
            int i = message.what;
            if (i == 0) {
                honourDeviceFragment.saveAm16ResourceStatus();
                honourDeviceFragment.destroymLoadingDialog();
                cfl.a().d("54C9739F-CA5C-4347-9F00-75B9DDF2C649");
                if (honourDeviceFragment.mInteractor != null) {
                    honourDeviceFragment.mInteractor.onResume();
                    return;
                }
                return;
            }
            if (i == 2) {
                honourDeviceFragment.destroymLoadingDialog();
            } else if (i == 3) {
                honourDeviceFragment.saveAm16ResourceStatus();
                honourDeviceFragment.destroymLoadingDialog();
            } else {
                LogUtil.h(HonourDeviceFragment.TAG, "fall through default case");
            }
        }
    }
}
