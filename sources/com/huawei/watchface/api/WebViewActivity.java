package com.huawei.watchface.api;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.webkit.ValueCallback;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.PermissionChecker;
import com.google.gson.Gson;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hianalytics.visual.autocollect.instrument.WebViewInstrumentation;
import com.huawei.hms.support.api.pay.HuaweiPay;
import com.huawei.hms.support.api.pay.PayResultInfo;
import com.huawei.hms.support.api.paytask.Pay;
import com.huawei.operation.ble.BleConstants;
import com.huawei.operation.utils.Constants;
import com.huawei.pluginachievement.manager.model.ResultCode;
import com.huawei.secure.android.common.intent.SafeBroadcastReceiver;
import com.huawei.secure.android.common.intent.SafeIntent;
import com.huawei.secure.android.common.util.SafeString;
import com.huawei.secure.android.common.webview.SafeWebView;
import com.huawei.uikit.hwbutton.widget.HwButton;
import com.huawei.uikit.hwprogressbar.widget.HwProgressBar;
import com.huawei.uikit.phone.hwtextview.widget.HwTextView;
import com.huawei.watchface.R$drawable;
import com.huawei.watchface.R$id;
import com.huawei.watchface.R$layout;
import com.huawei.watchface.R$string;
import com.huawei.watchface.ac;
import com.huawei.watchface.as;
import com.huawei.watchface.at;
import com.huawei.watchface.au;
import com.huawei.watchface.az;
import com.huawei.watchface.ba;
import com.huawei.watchface.cm;
import com.huawei.watchface.cv;
import com.huawei.watchface.d;
import com.huawei.watchface.db;
import com.huawei.watchface.dc;
import com.huawei.watchface.dh;
import com.huawei.watchface.dj;
import com.huawei.watchface.dp;
import com.huawei.watchface.ds;
import com.huawei.watchface.dt;
import com.huawei.watchface.dw;
import com.huawei.watchface.ef;
import com.huawei.watchface.ej;
import com.huawei.watchface.environment.Environment;
import com.huawei.watchface.eo;
import com.huawei.watchface.eq;
import com.huawei.watchface.ew;
import com.huawei.watchface.ey;
import com.huawei.watchface.fb;
import com.huawei.watchface.fc;
import com.huawei.watchface.fi;
import com.huawei.watchface.fj;
import com.huawei.watchface.flavor.FlavorConfig;
import com.huawei.watchface.manager.WatchFaceKaleidoscopeManager;
import com.huawei.watchface.manager.WatchFacePhotoAlbumManager;
import com.huawei.watchface.manager.WatchFaceWearManager;
import com.huawei.watchface.mvp.base.BaseActivity;
import com.huawei.watchface.mvp.model.crypt.PversionSdUtils;
import com.huawei.watchface.mvp.model.datatype.TryoutBean;
import com.huawei.watchface.mvp.model.datatype.WatchFaceSupportInfo;
import com.huawei.watchface.mvp.model.datatype.WatchResourcesInfo;
import com.huawei.watchface.mvp.model.filedownload.threadpool.ThreadPoolManager;
import com.huawei.watchface.mvp.model.helper.JsSafeUrlSystemParamManager;
import com.huawei.watchface.mvp.model.kaleidoscope.basic.KaleidoscopeDraw;
import com.huawei.watchface.mvp.model.latona.BackgroundOptionalConfig;
import com.huawei.watchface.mvp.model.latona.LatonaWatchFaceProvider;
import com.huawei.watchface.mvp.model.webview.JSInterface;
import com.huawei.watchface.mvp.model.webview.JsNetwork;
import com.huawei.watchface.mvp.ui.activity.ClipImageActivity;
import com.huawei.watchface.mvp.ui.view.CustomWebView;
import com.huawei.watchface.n;
import com.huawei.watchface.p;
import com.huawei.watchface.s;
import com.huawei.watchface.t;
import com.huawei.watchface.u;
import com.huawei.watchface.utils.BackgroundTaskUtils;
import com.huawei.watchface.utils.ChoosePicUtil;
import com.huawei.watchface.utils.CommonUtils;
import com.huawei.watchface.utils.DensityUtil;
import com.huawei.watchface.utils.HwLog;
import com.huawei.watchface.utils.HwSfpPolicyManagerHelper;
import com.huawei.watchface.utils.MobileInfoHelper;
import com.huawei.watchface.utils.PermissionUtils;
import com.huawei.watchface.utils.SafeHandler;
import com.huawei.watchface.utils.WatchFaceBitmapUtil;
import com.huawei.watchface.utils.WatchFaceHttpUtil;
import com.huawei.watchface.utils.WebViewUtils;
import com.huawei.watchface.utils.callback.IBaseResponseCallback;
import com.huawei.watchface.utils.callback.PluginOperationAdapter;
import com.huawei.watchface.utils.permission.PermissionUtil;
import defpackage.mcf;
import java.io.File;
import java.lang.ref.WeakReference;
import java.util.Objects;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes7.dex */
public class WebViewActivity extends BaseActivity implements View.OnClickListener, ActivityCompat.OnRequestPermissionsResultCallback {
    private static final String ACTION_REMOVE_ACCOUNT = "com.huawei.hwid.ACTION_REMOVE_ACCOUNT";
    private static final String ACTIVITY_URL = "huaweischeme://healthapp/devicemanagement?ProductID=&DeviceType=06D&SubMAC=";
    private static final String DESIGNER_STATE_COMMON = "1";
    public static final String HW_SIGNATURE_OR_SYSTEM = "huawei.android.permission.HW_SIGNATURE_OR_SYSTEM";
    private static final String PRIVACY_PATH = "com.huawei.ui.device.activity.watchface.PrivacyStatementActivity";
    private static final int REQUEST_EXTERNAL_STORAGE = 10010;
    private static final String TAG = "WebViewActivity";
    private static Activity sActivity;
    private WeakReference<Context> context;
    private PluginOperationAdapter mAdapter;
    private ef mAnalyticsH5Event;
    private ImageView mBackImageView;
    private HwButton mBtnNetSetting;
    protected ChoosePicUtil mChoosePicUtil;
    private CommonWebViewBroadcastReceiver mCommonWebViewBroadcastReceiver;
    protected Context mContext;
    public CustomWebView mCustomWebView;
    private DoubleClickReceiver mDoubleClickReceiver;
    protected SafeHandler mHandler;
    private Intent mIntent;
    private String mLastChooseClipResourceStyle;
    private String mLastLoadUrl;
    private RelativeLayout mNetworkLayout;
    private HwTextView mNoNetworkTips;
    private HwProgressBar mProgressBar;
    private RelativeLayout mReloadLayout;
    private ImageView mTipsImageView;
    private String mUrl;
    protected SafeWebView mWebView;
    private LinearLayout mWebViewLayout;
    private HwTextView mWebViewTipsCheckDate;
    private HwTextView mWebViewTipsCustomerService;
    private HwTextView mWebViewTipsUpdate;
    private String[] permissions = {"android.permission.WRITE_EXTERNAL_STORAGE"};
    private int mKaleidoscopeCurrentMaxIndex = -1;
    private boolean isReloadConfigChange = true;
    private IBaseResponseCallback mIBaseResponseCallback = new IBaseResponseCallback() { // from class: com.huawei.watchface.api.WebViewActivity.1
        @Override // com.huawei.watchface.utils.callback.IBaseResponseCallback
        public void onResponse(int i, Object obj) {
            HwLog.d(WebViewActivity.TAG, "mIBaseResponseCallback() onResponse responseCode: " + i);
            if (WebViewActivity.this.mHandler == null) {
                return;
            }
            if (WebViewActivity.this.mHandler.hasMessages(2015)) {
                WebViewActivity.this.mHandler.removeMessages(2015);
            }
            WebViewActivity.this.mHandler.sendEmptyMessage(2015);
        }
    };

    protected void generateWearInfo(String str) {
    }

    protected void reselectionWearTransmit() {
    }

    protected void setPortraitManeger() {
    }

    @Override // com.huawei.watchface.mvp.base.BaseActivity, com.huawei.secure.android.common.activity.SafeFragmentActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        Environment.setApplication((Application) getApplicationContext());
        super.onCreate(bundle);
        setLastLoadUrl(bundle);
        d.a().d();
        this.context = new WeakReference<>(this);
        getWindow().setSoftInputMode(48);
        fi.a().b();
        if (HwWatchFaceApi.getInstance(this).getAdapter() == null) {
            finish();
        }
        this.mContext = this;
        FlavorConfig.irrupt(this);
        String str = TAG;
        HwLog.i(str, "onCreate() enter. watchface version:" + cv.j());
        initAnalyticsH5Event();
        if (isRootActivity()) {
            n.a(this.mContext).k();
            getDesignerInfo();
            BackgroundTaskUtils.submit(new Runnable() { // from class: com.huawei.watchface.api.WebViewActivity$$ExternalSyntheticLambda7
                @Override // java.lang.Runnable
                public final void run() {
                    WebViewActivity.lambda$onCreate$0();
                }
            });
            fj.a().a(true);
            fj.a().b();
        }
        initGrsManager();
        BackgroundTaskUtils.submit(new Runnable() { // from class: com.huawei.watchface.api.WebViewActivity$$ExternalSyntheticLambda8
            @Override // java.lang.Runnable
            public final void run() {
                WebViewActivity.this.m890lambda$onCreate$1$comhuaweiwatchfaceapiWebViewActivity();
            }
        });
        setPortraitManeger();
        registerWebviewReceiver();
        registerHealthBalanceObserver();
        HwLog.i(str, "init time onCreate end:" + (System.currentTimeMillis() - HwWatchFaceApi.getInstance(this).getInitTime()));
    }

    static /* synthetic */ void lambda$onCreate$0() {
        if (HwWatchFaceApi.getInstance(Environment.getApplicationContext()).getDeviceConnectState() == 1) {
            dp.c("cache_device_id", db.a(HwWatchFaceApi.getInstance(Environment.getApplicationContext()).getDeviceIdentify()));
        }
        HwSfpPolicyManagerHelper.initDefaultCeLabelInDir();
    }

    /* renamed from: lambda$onCreate$1$com-huawei-watchface-api-WebViewActivity, reason: not valid java name */
    /* synthetic */ void m890lambda$onCreate$1$comhuaweiwatchfaceapiWebViewActivity() {
        fc.a(Environment.getApplicationContext());
        fb.a((Context) Environment.getApplicationContext(), true);
        dp.e(this.mContext);
        dp.f(this.mContext);
    }

    private void setLastLoadUrl(Bundle bundle) {
        if (bundle == null) {
            return;
        }
        this.mLastLoadUrl = bundle.getString("lastLoadUrl");
        this.mLastChooseClipResourceStyle = bundle.getString("lastChooseClipResourceStyle");
        String str = TAG;
        HwLog.i(str, "setLastLoadUrl mLastChooseClipResourceStyle:" + this.mLastChooseClipResourceStyle);
        FlavorConfig.safeHwLog(str, "mLastLoadUrl:" + this.mLastLoadUrl);
    }

    private void setLastChooseClipResourceStyle() {
        if (this.mCustomWebView == null) {
            HwLog.i(TAG, "setLastChooseClipResourceStyle mCustomWebView is null");
        } else if (TextUtils.isEmpty(this.mLastChooseClipResourceStyle)) {
            HwLog.i(TAG, "setLastChooseClipResourceStyle mLastChooseClipResourceStyle is null");
        } else {
            this.mCustomWebView.a(dh.a(this.mLastChooseClipResourceStyle, 0));
        }
    }

    @Override // com.huawei.secure.android.common.activity.SafeFragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        HwLog.d(TAG, "onNewIntent() enter.");
        setIntent(intent);
        initGrsManager();
    }

    private void transmitSetDefaultWatchFaceResult() {
        if (this.mCustomWebView == null) {
            HwLog.d(TAG, "updateUiForDefaultWatchFace() mCustomWebView is null");
            return;
        }
        WatchResourcesInfo currentWatchFace = HwWatchFaceBtManager.getInstance(this.mContext).getCurrentWatchFace();
        if (currentWatchFace == null) {
            HwLog.d(TAG, "updateUiForDefaultWatchFace() watchResourcesInfo is null");
            return;
        }
        HwLog.d(TAG, "updateUiForDefaultWatchFace() enter.");
        this.mCustomWebView.transmitSetDefaultWatchFaceResult(currentWatchFace.getWatchInfoId(), currentWatchFace.getWatchInfoVersion(), 1);
    }

    private void registerHealthBalanceObserver() {
        HwLog.i(TAG, "registerHealthBalanceObserver");
        az.a().b();
    }

    private void initGrsManager() {
        as.b().c();
        if (!Environment.sIsAarInTheme) {
            JsSafeUrlSystemParamManager.getInstance().b();
        }
        if (this.mHandler == null) {
            this.mHandler = new CommonWebViewActivityHandler(this);
        }
        if (HwWatchFaceBtManager.getInstance(this).getWatchFaceSupportInfo() == null) {
            HwLog.d(TAG, "initGrsManager() getWatchFaceSupportInfo is null.");
            HwWatchFaceBtManager.getInstance(this).getDeviceInfoForUi(this.mIBaseResponseCallback);
        }
        handleLoadPage((WebViewActivity) this.mContext);
    }

    private void initErrorView() {
        HwLog.i(TAG, "CommonUtil.isErrorWebView");
        ((HwButton) findViewById(R$id.btn_go_setting)).setOnClickListener(new View.OnClickListener() { // from class: com.huawei.watchface.api.WebViewActivity$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                WebViewActivity.this.m888lambda$initErrorView$2$comhuaweiwatchfaceapiWebViewActivity(view);
            }
        });
    }

    /* renamed from: lambda$initErrorView$2$com-huawei-watchface-api-WebViewActivity, reason: not valid java name */
    /* synthetic */ void m888lambda$initErrorView$2$comhuaweiwatchfaceapiWebViewActivity(View view) {
        HwLog.i(TAG, "CommonUtil.uninstallApk");
        CommonUtils.b(this.mContext, "com.google.android.webview");
        ViewClickInstrumentation.clickOnView(view);
    }

    public void gotoPrivacyStatementActivity() {
        Intent intent = new Intent();
        intent.setClassName(Environment.b(), PRIVACY_PATH);
        mcf.cfK_(this, intent, 102);
    }

    private void initIntentData() {
        String str = TAG;
        HwLog.i(str, "initIntentData() enter.");
        Intent intent = getIntent();
        this.mIntent = intent;
        if (intent != null) {
            this.mUrl = intent.getStringExtra("url");
            FlavorConfig.safeHwLog(str, "initIntentData mUrl:" + this.mUrl);
            if (!TextUtils.isEmpty(this.mLastLoadUrl)) {
                if (this.mLastLoadUrl.contains("&operateType=")) {
                    this.mLastLoadUrl = SafeString.replace(this.mLastLoadUrl, "&operateType=", "&operateTypeOrg=");
                }
                this.mUrl = this.mLastLoadUrl;
                this.mLastLoadUrl = null;
                this.mIntent.setAction(null);
                FlavorConfig.safeHwLog(str, "initIntentData mLastLoadUrl:" + this.mUrl);
            }
            TryoutBean tryoutBean = (TryoutBean) this.mIntent.getParcelableExtra("key_try_out_bean");
            if (TextUtils.isEmpty(this.mUrl) && tryoutBean != null) {
                String action = this.mIntent.getAction();
                if ("1".equals(action)) {
                    HwLog.i(str, "initIntentData - to buy watch face");
                    this.mUrl = t.a().d(tryoutBean.getDetailUrl(), "1");
                } else if ("2".equals(action)) {
                    HwLog.i(str, "initIntentData - finish try out");
                    t.a().b(tryoutBean);
                    this.mUrl = t.a().d(tryoutBean.getDetailUrl(), "2");
                } else if ("3".equals(action)) {
                    HwLog.i(str, "initIntentData - open vip");
                    this.mUrl = t.a().d(tryoutBean.getDetailUrl(), "4");
                }
            }
            if (TextUtils.isEmpty(this.mUrl)) {
                this.mUrl = "";
            }
            verifyExterUrl();
        }
    }

    @Override // androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        String str = TAG;
        FlavorConfig.safeHwLog(str, "onSaveInstanceState mUrl:" + this.mUrl);
        bundle.putString("lastLoadUrl", this.mUrl);
        CustomWebView customWebView = this.mCustomWebView;
        if (customWebView != null) {
            int chooseToClipResourceStyle = customWebView.getChooseToClipResourceStyle();
            HwLog.i(str, "onSaveInstanceState mChooseToClipResourceStyle:" + chooseToClipResourceStyle);
            bundle.putString("lastChooseClipResourceStyle", String.valueOf(chooseToClipResourceStyle));
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x005d A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:11:0x005e A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:15:0x00ae  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x00f5  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x0163  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x016b  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x0147  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x0152  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void verifyExterUrl() {
        /*
            Method dump skipped, instructions count: 414
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.watchface.api.WebViewActivity.verifyExterUrl():void");
    }

    private void dealUrlConfigParam() {
        if (Environment.getApplicationContext() == null) {
            HwLog.i(TAG, "dealUrlConfigParam getApplicationContext  is null");
            return;
        }
        String c = JsSafeUrlSystemParamManager.getInstance().c();
        if (TextUtils.isEmpty(c)) {
            HwLog.i(TAG, "dealUrlConfigParam releaseUrlConfig  is empty");
            return;
        }
        String str = this.mUrl;
        if (str != null && str.contains(c)) {
            HwLog.i(TAG, "dealUrlConfigParam mUrl  is contains releaseUrlConfig");
            return;
        }
        HwLog.i(TAG, "dealUrlConfigParam releaseUrlConfig:" + c);
        StringBuilder sb = new StringBuilder();
        sb.append(this.mUrl);
        sb.append(this.mUrl.contains("?") ? "&" : "?");
        sb.append(c);
        this.mUrl = sb.toString();
    }

    private void dealGreyUrl() {
        if (Environment.getApplicationContext() == null) {
            HwLog.i(TAG, " getApplicationContext  is null");
            return;
        }
        if (HwWatchFaceApi.getInstance(Environment.getApplicationContext()).isOversea() || !TextUtils.equals("CN", HwWatchFaceApi.getInstance(Environment.getApplicationContext()).getCommonCountryCode())) {
            HwLog.i(TAG, "dealGreyUrl() isOversea.");
        } else if (HwWatchFaceApi.getInstance(this.mContext) != null && HwWatchFaceApi.getInstance(this.mContext).isBetaVersion()) {
            HwLog.i(TAG, "dealGreyUrl() current is beta app.");
        } else {
            this.mUrl = HwWatchFaceApi.getInstance(Environment.getApplicationContext()).getWatchfaceReleaseSite(this.mUrl);
        }
    }

    private void initView() {
        String str = TAG;
        HwLog.i(str, "initView() enter.");
        this.mContext = this;
        setActivity(this);
        PluginOperationAdapter pluginOperationAdapter = (PluginOperationAdapter) ba.a(this.mContext).getAdapter();
        this.mAdapter = pluginOperationAdapter;
        if (pluginOperationAdapter == null) {
            initOperationAdapter(this.mContext);
            this.mAdapter = (PluginOperationAdapter) ba.a(this.mContext).getAdapter();
        }
        if (this.mHandler == null) {
            this.mHandler = new CommonWebViewActivityHandler(this);
        }
        this.mWebViewLayout = (LinearLayout) dw.a(this, R$id.webview_layout);
        SafeWebView safeWebView = (SafeWebView) dw.a(this, R$id.web_view);
        this.mWebView = safeWebView;
        safeWebView.setLayerType(2, null);
        HwLog.i(str, "initView() isHardwareAccelerated: " + this.mWebView.isHardwareAccelerated());
        dc.a(this.mContext, this.mWebView);
        if (dc.a(getApplicationContext())) {
            HwLog.i(str, "initView setBackgroundColor #000000");
            this.mWebView.setBackgroundColor(Color.parseColor("#000000"));
        } else {
            HwLog.i(str, "initView setBackgroundColor #FFFFFF");
            this.mWebView.setBackgroundColor(Color.parseColor("#FFFFFF"));
        }
        this.mCustomWebView = getCustomWebView();
        setLastChooseClipResourceStyle();
        setWatchfaceCallback();
        HwProgressBar f = this.mCustomWebView.f();
        this.mProgressBar = f;
        dw.c(f, CommonUtils.a((Context) this));
        this.mReloadLayout = (RelativeLayout) dw.a(this, R$id.reload_layout);
        this.mNetworkLayout = (RelativeLayout) dw.a(this, R$id.net_work_layout);
        this.mBtnNetSetting = (HwButton) dw.a(this, R$id.btn_no_net_work);
        this.mTipsImageView = (ImageView) dw.a(this, R$id.img_no_net_work);
        this.mBackImageView = (ImageView) dw.a(this, R$id.img_back);
        this.mNoNetworkTips = (HwTextView) dw.a(this, R$id.tips_no_net_work);
        this.mWebViewTipsUpdate = (HwTextView) dw.a(this, R$id.tips_web_view_update);
        this.mWebViewTipsCheckDate = (HwTextView) dw.a(this, R$id.tips_web_view_check_date);
        this.mWebViewTipsCustomerService = (HwTextView) dw.a(this, R$id.tips_web_view_customer_service);
        dw.a(this.mReloadLayout, this);
        dw.a(this.mBtnNetSetting, this);
        dw.a(this.mBackImageView, new View.OnClickListener() { // from class: com.huawei.watchface.api.WebViewActivity$$ExternalSyntheticLambda9
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                WebViewActivity.this.m889lambda$initView$3$comhuaweiwatchfaceapiWebViewActivity(view);
            }
        });
        HwLog.i(str, "init time initView:" + (System.currentTimeMillis() - HwWatchFaceApi.getInstance(this.mContext).getInitTime()));
        initData();
    }

    /* renamed from: lambda$initView$3$com-huawei-watchface-api-WebViewActivity, reason: not valid java name */
    /* synthetic */ void m889lambda$initView$3$comhuaweiwatchfaceapiWebViewActivity(View view) {
        goBack();
        ViewClickInstrumentation.clickOnView(view);
    }

    private void goBack() {
        String str = TAG;
        HwLog.i(str, "goBack");
        try {
            Context context = this.mContext;
            if (context == null || !(context instanceof Activity)) {
                return;
            }
            HwLog.i(str, "goBack finish");
            ((Activity) this.mContext).finish();
        } catch (Exception e) {
            HwLog.e(TAG, "goBack " + HwLog.printException(e));
        }
    }

    protected CustomWebView getCustomWebView() {
        return new CustomWebView(getJSInterface(), this.mContext, (HwProgressBar) findViewById(R$id.load_url_progress), this.mWebView, this.mHandler, 3002);
    }

    protected JSInterface getJSInterface() {
        return new JSInterface(this.mContext);
    }

    public void setWatchfaceCallback() {
        PluginOperationAdapter pluginOperationAdapter = this.mAdapter;
        if (pluginOperationAdapter != null) {
            pluginOperationAdapter.setWatchFaceCallback(this.mCustomWebView, isRootActivity());
            this.mAdapter.setEnteringWatchFaceAlbum(false);
            HwLog.i(TAG, "initView() setWatchFaceCallback.");
        }
    }

    protected void updateWatchfaceCallback() {
        PluginOperationAdapter pluginOperationAdapter = this.mAdapter;
        if (pluginOperationAdapter != null) {
            pluginOperationAdapter.updateWatchFaceCallback(this.mCustomWebView, isRootActivity());
            this.mAdapter.setEnteringWatchFaceAlbum(false);
            HwLog.i(TAG, "updateWatchfaceCallback() setWatchFaceCallback.");
        }
    }

    private void initOperationAdapter(Context context) {
        String str = TAG;
        HwLog.i(str, "initOperationAdapter() enter.");
        if (((PluginOperationAdapter) ba.a(context).getAdapter()) == null) {
            HwLog.i(str, "initOperationAdapter() adapter is null.");
            ba a2 = ba.a(context);
            a2.setAdapter(au.a(context));
            a2.init(context);
        }
    }

    private void initData() {
        HwLog.i(TAG, "initData() enter.");
        if (!CommonUtils.f()) {
            showNoNetworkPage();
        }
        initEvent();
    }

    private void initEvent() {
        String str = TAG;
        HwLog.i(str, "initEvent() enter.");
        if (this.mCustomWebView != null) {
            HwLog.e(str, "init time onGlobalLayout:" + (System.currentTimeMillis() - HwWatchFaceApi.getInstance(this.mContext).getInitTime()));
            FlavorConfig.safeHwLog(str, "loadSafeUrl mUrl:" + this.mUrl);
            this.mCustomWebView.loadSafeUrl(this.mUrl, false);
        }
    }

    static class CommonWebViewActivityHandler extends SafeHandler<WebViewActivity> {
        public CommonWebViewActivityHandler(WebViewActivity webViewActivity) {
            super(webViewActivity);
        }

        @Override // com.huawei.watchface.utils.SafeHandler
        public void handlerMessageAction(Message message) {
            if (getObj() == null) {
                return;
            }
            int i = message.what;
            if (i == 2000) {
                HwLog.i(WebViewActivity.TAG, "MSG_PROGRESSBAR_GONE");
                getObj().mProgressBar.setVisibility(8);
            } else if (i == 2005) {
                HwLog.i(WebViewActivity.TAG, "MSG_POST_TOAST");
                Object obj = message.obj;
                if (obj instanceof String) {
                    Toast.makeText(getObj().mContext, (String) obj, 0).show();
                }
            } else if (i == 2030) {
                HwLog.i(WebViewActivity.TAG, "MSG_DEVICE_MODE_CHANGE");
                Object obj2 = message.obj;
                if (obj2 instanceof String) {
                    getObj().notifyWatchfaceRefresh((String) obj2);
                }
            } else if (i == 2099) {
                HwLog.i(WebViewActivity.TAG, "MSG_SHARE_FAIL_TOAST");
                ds.a(DensityUtil.getStringById(R$string.IDS_submit_error_message_1));
            } else if (i == 20196) {
                String str = (String) message.obj;
                HwLog.i(WebViewActivity.TAG, "handleMessage newText=" + str);
                getObj().loadSafeUrl("javascript:window.transmitSearch('" + str + Constants.RIGHT_BRACKET);
            } else if (i == 20991) {
                HwLog.i(WebViewActivity.TAG, "MSG_WEAR_RESELECTION");
                getObj().reselectionWearTransmit();
            } else if (i == 2023) {
                HwLog.i(WebViewActivity.TAG, "MSG_KALEIDOSCOPE_RESET_INDEX");
                getObj().resetKaleidoscopeCurrentMaxIndex();
            } else if (i != 2024) {
                getObj().handleServerOrNetWorkPage(getObj(), message);
            } else {
                HwLog.i(WebViewActivity.TAG, "MSG_DEVICE_MODE_CHANGE");
                Object obj3 = message.obj;
                if (obj3 instanceof Integer) {
                    getObj().notifyWatchfaceModeChange(((Integer) obj3).intValue());
                }
            }
            getObj().uploadH5LoadUrl();
        }
    }

    protected void notifyWatchfaceModeChange(int i) {
        HwLog.i(TAG, "notifyWatchfaceModeChange currentMode：" + i);
        reload();
    }

    protected void notifyWatchfaceRefresh(String str) {
        HwLog.i(TAG, "notifyWatchfaceRefresh url is empty：" + TextUtils.isEmpty(str));
        if (TextUtils.isEmpty(str)) {
            reload();
        } else {
            loadSafeUrl(str);
        }
    }

    @Override // com.huawei.watchface.mvp.base.BaseActivity
    public void configChangeReload() {
        String str = TAG;
        HwLog.i(str, "configChangeReload() enter.");
        CustomWebView customWebView = this.mCustomWebView;
        if (customWebView == null) {
            HwLog.i(str, "configChangeReload mCustomWebView is null");
            return;
        }
        customWebView.i();
        if (!this.isReloadConfigChange) {
            HwLog.i(str, "configChangeReload isReloadConfigChange false");
            return;
        }
        this.mCustomWebView.a(true);
        dw.b(this.mWebView, 4);
        SafeWebView safeWebView = this.mWebView;
        if (safeWebView != null) {
            safeWebView.reload();
        }
    }

    public void setReloadConfigChange(boolean z) {
        HwLog.i(TAG, "setReloadConfigChange reloadConfigChange:" + z);
        this.isReloadConfigChange = z;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleServerOrNetWorkPage(WebViewActivity webViewActivity, Message message) {
        int i = message.what;
        if (i == 2001) {
            if (CommonUtils.f()) {
                HwLog.i(TAG, "MSG_ON_RECEIVED_ERROR showNoServicePage");
                webViewActivity.showNoServicePage();
                this.mAnalyticsH5Event.a(message.what, "MSG_ON_RECEIVED_ERROR showNoServicePage");
                return;
            } else {
                HwLog.i(TAG, "MSG_ON_RECEIVED_ERROR showNoNetworkPage");
                webViewActivity.showNoNetworkPage();
                this.mAnalyticsH5Event.a(message.what, "MSG_ON_RECEIVED_ERROR showNoNetworkPage");
                return;
            }
        }
        if (i == 2003) {
            HwLog.i(TAG, "MSG_ON_NET_WORK");
            webViewActivity.showNoNetworkPage();
            this.mAnalyticsH5Event.a(message.what, "MSG_ON_NET_WORK noNetWork");
            return;
        }
        if (i == 4001) {
            HwLog.i(TAG, "MAG_WEB_VIEW_LOAD");
            webViewActivity.showUnstableNetworkPage();
            this.mAnalyticsH5Event.a(message.what, "MAG_WEB_VIEW_LOAD");
            return;
        }
        if (i == 2006) {
            HwLog.i(TAG, "MSG_SERVER_ERROR");
            webViewActivity.showServerErrorPage();
            this.mAnalyticsH5Event.a(message.what, "MSG_SERVER_ERROR");
            return;
        }
        if (i == 2007) {
            HwLog.i(TAG, "MSG_CONNECT_TIMEOUT");
            webViewActivity.showUnstableNetworkPage();
            this.mAnalyticsH5Event.a(message.what, "MSG_CONNECT_TIMEOUT");
        } else if (i == 2021) {
            HwLog.i(TAG, "MSG_WATCH_FACE_SERVER_ERROR");
            webViewActivity.showWatchFaceServerErrorPage();
            this.mAnalyticsH5Event.a(message.what, "MSG_WATCH_FACE_SERVER_ERROR");
        } else {
            if (i == 2022) {
                HwLog.i(TAG, "MSG_WATCH_FACE_SERVER_RETRY");
                webViewActivity.showWatchFaceServerRetryPage();
                this.mAnalyticsH5Event.a(message.what, "MSG_WATCH_FACE_SERVER_RETRY");
                return;
            }
            webViewActivity.handleTitle(webViewActivity, message);
        }
    }

    private void handleTitle(WebViewActivity webViewActivity, Message message) {
        int i = message.what;
        if (i == 2025) {
            HwLog.i(TAG, "handleTitle() MSG_SHOW_WATCH_FACE_INSTALL_PROGRESS");
            webViewActivity.showInstallProgress(message.arg1);
        } else if (i == 20253) {
            HwLog.i(TAG, "handleTitle() UPLOAD_SUCCESS");
            BackgroundTaskUtils.postInMainThread(new Runnable() { // from class: com.huawei.watchface.api.WebViewActivity.2
                @Override // java.lang.Runnable
                public void run() {
                    HwLog.i(WebViewActivity.TAG, "handleTitle() goBack");
                    if (WebViewActivity.this.mCustomWebView != null) {
                        WebViewActivity.this.mCustomWebView.e();
                    }
                }
            });
        } else {
            webViewActivity.handleStartActivity(webViewActivity, message);
        }
    }

    private void handleStartActivity(WebViewActivity webViewActivity, Message message) {
        int i = message.what;
        if (i != 2004) {
            if (i == 2016) {
                String str = TAG;
                HwLog.i(str, "MSG_START_WEB_PAGE");
                Bundle data = message.getData();
                if (data != null) {
                    webViewActivity.startLoadUrl(data.getString("url"));
                    return;
                } else {
                    HwLog.w(str, "handleStartActivity MSG_START_WEB_PAGE bundle is null");
                    return;
                }
            }
            webViewActivity.handleLoadPage(webViewActivity, message);
            return;
        }
        String str2 = TAG;
        HwLog.i(str2, "MSG_START_MINI_SHOP_WEB_PAGE");
        Bundle data2 = message.getData();
        if (data2 != null) {
            String string = data2.getString("url");
            String string2 = data2.getString("type");
            Intent intent = new Intent(webViewActivity.mContext, (Class<?>) WebViewActivity.class);
            intent.putExtra("url", string);
            intent.putExtra("type", string2);
            mcf.cfJ_(webViewActivity.mContext, intent);
            return;
        }
        HwLog.w(str2, "handleStartActivity MSG_START_MINI_SHOP_WEB_PAGE bundle is null");
    }

    private void handleLoadPage(WebViewActivity webViewActivity, Message message) {
        int i = message.what;
        if (i == 2015) {
            HwLog.i(TAG, "handleLoadPage() MSG_GRS_GET_URL");
            handleLoadPage(webViewActivity);
        } else {
            if (i == 10086) {
                HwLog.i(TAG, "handleLoadPage() ON_PAGE_FINISH");
                Object obj = message.obj;
                if (this.mCustomWebView != null && message.arg1 == 11) {
                    this.mCustomWebView.a(false);
                }
                dealOnPageFinish(webViewActivity, obj);
                return;
            }
            webViewActivity.handleJsService(webViewActivity, message);
        }
    }

    private void handleLoadPage(WebViewActivity webViewActivity) {
        HwLog.e(TAG, "init time handleLoadPage:" + (System.currentTimeMillis() - HwWatchFaceApi.getInstance(this.mContext).getInitTime()));
        if (CommonUtils.e(Environment.getApplicationContext()) && !HwWatchFaceApi.getInstance(Environment.getApplicationContext()).isOversea()) {
            webViewActivity.setContentView(R$layout.watchface_fragment_web_view_error_1);
            webViewActivity.initErrorView();
            return;
        }
        try {
            setContentView(R$layout.watchface_activity_common_web_view);
            webViewActivity.initIntentData();
            webViewActivity.initView();
        } catch (RuntimeException unused) {
            HwLog.e(TAG, "handleLoadPage() webview error.");
            finish();
        }
    }

    private void dealOnPageFinish(WebViewActivity webViewActivity, Object obj) {
        if (obj instanceof String) {
            if (this.mIntent != null) {
                this.mUrl = (String) obj;
            }
            if (!CommonUtils.f() || this.mNetworkLayout.getVisibility() == 0) {
                return;
            }
            dw.b(webViewActivity.mWebView, 0);
        }
    }

    private void showInstallProgress(int i) {
        PluginOperationAdapter pluginOperationAdapter = this.mAdapter;
        if (pluginOperationAdapter != null) {
            pluginOperationAdapter.showInstallProgress(i, this);
        }
    }

    private void handleJsService(WebViewActivity webViewActivity, Message message) {
        int i = message.what;
        HwLog.d(TAG, "handleBleService.");
        webViewActivity.handleBleService(webViewActivity, message);
    }

    private void handleBleService(WebViewActivity webViewActivity, Message message) {
        Bundle data;
        if (webViewActivity == null || message == null || (data = message.getData()) == null) {
        }
        switch (message.what) {
            case 10002:
                HwLog.i(TAG, "BLE_CONNECTION_STATE_CHANGE");
                webViewActivity.callJsOnConnectionStateChange(data);
                break;
            case 10003:
                HwLog.i(TAG, "BLE_SERVICES_DISCOVERED");
                webViewActivity.callJsBleServicesDiscovered(data);
                break;
            case 10004:
                HwLog.i(TAG, "BLE_CHARACTERISTIC_VALUE_CHANGE");
                webViewActivity.callJsBleCharacteristicValueChange(data);
                break;
            case 10005:
                HwLog.i(TAG, "BLE_CHARACTERISTIC_WRITE");
                webViewActivity.callJsBleCharacteristicWrite(data);
                break;
            case 10006:
                HwLog.i(TAG, "BLE_CHARACTERISTIC_READ");
                webViewActivity.callJsBleCharacteristicRead(data);
                break;
            case 10007:
                HwLog.i(TAG, "GET_BLU_ADAPTER_STATE");
                webViewActivity.callJsGetBleAdapterState(data);
                break;
            case 10008:
            default:
                HwLog.d(TAG, "handleBleService default.");
                break;
            case 10009:
                HwLog.i(TAG, "SAVE_DATA_RESULT_MSG");
                webViewActivity.callJsSaveResult(data);
                break;
        }
    }

    private void callJsOnConnectionStateChange(Bundle bundle) {
        if (bundle == null) {
            HwLog.e(TAG, "callJsOnConnectionStateChange bundle is null");
            return;
        }
        String string = bundle.getString("deviceId");
        Boolean valueOf = Boolean.valueOf(bundle.getBoolean(BleConstants.KEY_CONNECTSTATE));
        String string2 = bundle.getString("function");
        if (string == null || valueOf == null || string2 == null) {
            HwLog.e(TAG, "callJsOnConnectionStateChange bundle get.* is null");
            return;
        }
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("deviceId", string);
            jSONObject.put(BleConstants.KEY_CONNECTSTATE, valueOf);
            String jSONObject2 = jSONObject.toString();
            loadSafeUrl(WebViewUtils.getUrl(string2, jSONObject2));
            HwLog.d(TAG, "callJsOnConnectionStateChange " + jSONObject2);
        } catch (JSONException e) {
            HwLog.e(TAG, "callJsOnConnectionStateChange fail exception = " + HwLog.printException((Exception) e));
        }
    }

    private void callJsBleServicesDiscovered(Bundle bundle) {
        if (bundle == null) {
            HwLog.e(TAG, "callJsBleServicesDiscovered bundle is null");
            return;
        }
        String string = bundle.getString("errCode");
        String string2 = bundle.getString("function");
        if (string == null || string2 == null) {
            HwLog.e(TAG, "callJsBleServicesDiscovered bundle get.* is null");
            return;
        }
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("errCode", string);
            String jSONObject2 = jSONObject.toString();
            loadSafeUrl(WebViewUtils.getUrl(string2, jSONObject2));
            HwLog.d(TAG, "callJsBleServicesDiscovered " + jSONObject2);
        } catch (JSONException e) {
            HwLog.e(TAG, "callJsBleServicesDiscovered fail exception = " + HwLog.printException((Exception) e));
        }
    }

    private void callJsBleCharacteristicValueChange(Bundle bundle) {
        if (bundle == null) {
            HwLog.e(TAG, "callJsBleCharacteristicValueChange bundle is null");
            return;
        }
        String string = bundle.getString("function");
        String string2 = bundle.getString("deviceId");
        String string3 = bundle.getString(BleConstants.KEY_CHARACTERISTICID);
        String string4 = bundle.getString("data");
        if (string == null || string2 == null || string3 == null || string4 == null) {
            HwLog.e(TAG, "callJsBleCharacteristicValueChange bundle get.* is null");
            return;
        }
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("deviceId", string2);
            jSONObject.put("serviceId", "");
            jSONObject.put(BleConstants.KEY_CHARACTERISTICID, string3);
            jSONObject.put("data", string4);
            String jSONObject2 = jSONObject.toString();
            loadSafeUrl(WebViewUtils.getUrl(string, jSONObject2));
            FlavorConfig.safeHwLog(TAG, "callJsBleCharacteristicValueChange " + jSONObject2);
        } catch (JSONException e) {
            HwLog.e(TAG, "callJsBleCharacteristicValueChange fail exception = " + HwLog.printException((Exception) e));
        }
    }

    private void callJsBleCharacteristicWrite(Bundle bundle) {
        if (bundle == null) {
            HwLog.e(TAG, "callJsBleCharacteristicWrite bundle is null");
            return;
        }
        String string = bundle.getString("function");
        String string2 = bundle.getString("errCode");
        if (string == null || string2 == null) {
            HwLog.e(TAG, "callJsBleCharacteristicWrite bundle get.* is null");
            return;
        }
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("errCode", string2);
            String jSONObject2 = jSONObject.toString();
            loadSafeUrl(WebViewUtils.getUrl(string, jSONObject2));
            HwLog.d(TAG, "callJsBleCharacteristicWrite " + jSONObject2);
        } catch (JSONException e) {
            HwLog.e(TAG, "callJsBleCharacteristicWrite fail exception = " + HwLog.printException((Exception) e));
        }
    }

    private void callJsBleCharacteristicRead(Bundle bundle) {
        if (bundle == null) {
            HwLog.e(TAG, "callJsBleCharacteristicRead bundle is null");
            return;
        }
        String string = bundle.getString("function");
        String string2 = bundle.getString("errCode");
        String string3 = bundle.getString("data");
        if (string == null || string2 == null || string3 == null) {
            HwLog.e(TAG, "callJsBleCharacteristicRead bundle get.* is null");
            return;
        }
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("data", string3);
            jSONObject.put("errCode", string2);
            String jSONObject2 = jSONObject.toString();
            loadSafeUrl(WebViewUtils.getUrl(string, jSONObject2));
            HwLog.d(TAG, "callJsBleCharacteristicRead " + jSONObject2);
        } catch (JSONException e) {
            HwLog.e(TAG, "callJsBleCharacteristicRead fail exception = " + HwLog.printException((Exception) e));
        }
    }

    private void callJsGetBleAdapterState(Bundle bundle) {
        if (bundle == null) {
            HwLog.e(TAG, "callJsGetBleAdapterState bundle is null");
            return;
        }
        String string = bundle.getString("function");
        Boolean valueOf = Boolean.valueOf(bundle.getBoolean(BleConstants.KEY_DISCOVERING));
        Boolean valueOf2 = Boolean.valueOf(bundle.getBoolean(BleConstants.KEY_AVAILABLE));
        String string2 = bundle.getString("errCode");
        if (string == null || valueOf == null || valueOf2 == null || string2 == null) {
            HwLog.e(TAG, "callJsGetBleAdapterState bundle get.* is null");
            return;
        }
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(BleConstants.KEY_DISCOVERING, valueOf);
            jSONObject.put(BleConstants.KEY_AVAILABLE, valueOf2);
            jSONObject.put("errCode", string2);
            String jSONObject2 = jSONObject.toString();
            loadSafeUrl(WebViewUtils.getUrl(string, jSONObject2));
            HwLog.d(TAG, "callJsGetBleAdapterState " + jSONObject2);
        } catch (JSONException e) {
            HwLog.e(TAG, "callJsGetBleAdapterState fail exception = " + HwLog.printException((Exception) e));
        }
    }

    private void callJsSaveResult(Bundle bundle) {
        if (bundle == null) {
            HwLog.e(TAG, "callJsSaveResult bundle is null");
            return;
        }
        String string = bundle.getString("function");
        Integer valueOf = Integer.valueOf(bundle.getInt("errCode"));
        if (string == null || valueOf == null) {
            HwLog.e(TAG, "callJsSaveResult bundle get.* is null");
            return;
        }
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("errCode", valueOf);
            String jSONObject2 = jSONObject.toString();
            loadSafeUrl(WebViewUtils.getUrl(string, jSONObject2));
            HwLog.d(TAG, "callJsSaveResult " + jSONObject2);
        } catch (JSONException e) {
            HwLog.e(TAG, "callJsSaveResult fail exception = " + HwLog.printException((Exception) e));
        }
    }

    public void notifyNotInstallation() {
        CustomWebView customWebView = this.mCustomWebView;
        if (customWebView != null) {
            customWebView.j();
        }
    }

    public void notifyH5ClickSelectPhoto() {
        CustomWebView customWebView = this.mCustomWebView;
        if (customWebView != null) {
            customWebView.k();
        }
    }

    public void verifyStoragePermissions() {
        CustomWebView customWebView;
        boolean isAndroid13 = CommonUtils.isAndroid13();
        String str = BleConstants.CODE_AUTHORIZED_FAIL;
        if (isAndroid13) {
            if (android.os.Environment.isExternalStorageManager()) {
                PluginOperationAdapter pluginOperationAdapter = this.mAdapter;
                if (pluginOperationAdapter != null) {
                    pluginOperationAdapter.openSystemFileManager(this);
                    return;
                }
                return;
            }
            HwWatchFaceApi.getInstance(this).showHealthAppSettingGuide(this, PermissionUtil.PermissionType.MANAGE_EXTERNAL_STORAGE.getPermission(), new View.OnClickListener() { // from class: com.huawei.watchface.api.WebViewActivity$$ExternalSyntheticLambda2
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    WebViewActivity.this.m893xda86cf(view);
                }
            }, new View.OnClickListener() { // from class: com.huawei.watchface.api.WebViewActivity$$ExternalSyntheticLambda3
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    WebViewActivity.this.m894x83fbbee(view);
                }
            });
            CustomWebView customWebView2 = this.mCustomWebView;
            if (customWebView2 != null) {
                customWebView2.a(BleConstants.CODE_AUTHORIZED_FAIL);
                return;
            }
            return;
        }
        if ((CommonUtils.isAndroid13() ? ContextCompat.checkSelfPermission(this, this.permissions[0]) : PermissionChecker.checkSelfPermission(this, this.permissions[0])) != 0) {
            ActivityCompat.requestPermissions(this, this.permissions, 10010);
            if (PermissionUtils.a(this, "android.permission.WRITE_EXTERNAL_STORAGE") || (customWebView = this.mCustomWebView) == null) {
                return;
            }
            String[] strArr = new String[1];
            if (CommonUtils.isAndroid11()) {
                str = ResultCode.ERROR_TS_TIMEOUT;
            }
            strArr[0] = str;
            customWebView.a(strArr);
            return;
        }
        PluginOperationAdapter pluginOperationAdapter2 = this.mAdapter;
        if (pluginOperationAdapter2 != null) {
            pluginOperationAdapter2.openSystemFileManager(this);
        }
    }

    /* renamed from: lambda$verifyStoragePermissions$4$com-huawei-watchface-api-WebViewActivity, reason: not valid java name */
    /* synthetic */ void m893xda86cf(View view) {
        HwLog.d(TAG, "positiveListener click");
        CustomWebView customWebView = this.mCustomWebView;
        if (customWebView != null) {
            customWebView.a("0");
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    /* renamed from: lambda$verifyStoragePermissions$5$com-huawei-watchface-api-WebViewActivity, reason: not valid java name */
    /* synthetic */ void m894x83fbbee(View view) {
        HwLog.d(TAG, "negativeListener click");
        CustomWebView customWebView = this.mCustomWebView;
        if (customWebView != null) {
            customWebView.a("0");
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    public void startLoadUrl(String str) {
        String str2 = TAG;
        HwLog.i(str2, "startLoadUrl startLoadUrl the url");
        if (this.mCustomWebView == null) {
            HwLog.d(str2, "mCustomWebView is null startLoadUrl");
            return;
        }
        String b = WebViewUtils.b(str);
        if (!this.mCustomWebView.b(b)) {
            HwLog.d(str2, "isWhiteUrl is false url");
            this.mCustomWebView.b(false);
        } else {
            this.mCustomWebView.b(true);
            loadSafeUrl(b);
        }
    }

    private void showNoServicePage() {
        HwLog.i(TAG, "showNoServicePage");
        showToolsBar();
        this.mNetworkLayout.setVisibility(0);
        dw.b(this.mWebView, 4);
        setTxtTipsVisibility(8);
        this.mBtnNetSetting.setVisibility(8);
        this.mNoNetworkTips.setGravity(17);
        this.mNoNetworkTips.setText(getString(R$string.IDS_hwh_home_healthshop_unable_connect_server_tips));
        this.mTipsImageView.setBackgroundResource(R$drawable.watchface_ic_network);
    }

    private void showUnstableNetworkPage() {
        HwLog.i(TAG, "showUnstableNetworkPage");
        showToolsBar();
        this.mNetworkLayout.setVisibility(0);
        dw.b(this.mWebView, 4);
        setTxtTipsVisibility(8);
        this.mNoNetworkTips.setGravity(17);
        this.mProgressBar.setVisibility(8);
        this.mNoNetworkTips.setText(getString(R$string.network_unstable_notice2));
        this.mTipsImageView.setBackgroundResource(R$drawable.watchface_ic_network);
    }

    public void showNoNetworkPage() {
        HwLog.i(TAG, "showNoNetworkPage");
        showToolsBar();
        this.mNetworkLayout.setVisibility(0);
        dw.b(this.mWebView, 4);
        setTxtTipsVisibility(8);
        this.mNoNetworkTips.setGravity(17);
        this.mNoNetworkTips.setText(getString(R$string.IDS_hwh_home_healthshop_no_net_work_pls_click_again));
        this.mTipsImageView.setBackgroundResource(R$drawable.watchface_ic_network);
    }

    private void showToolsBar() {
        try {
            CommonUtils.a(getWindow(), 0, !dc.a(getApplicationContext()));
            dw.c(this.mNetworkLayout, CommonUtils.a((Context) this));
        } catch (Exception unused) {
            HwLog.e(TAG, "showToolsBar Exception");
        }
    }

    private void showServerErrorPage() {
        HwLog.i(TAG, "showServerErrorPage");
        showToolsBar();
        this.mNetworkLayout.setVisibility(0);
        dw.b(this.mWebView, 4);
        this.mBtnNetSetting.setVisibility(8);
        setTxtTipsVisibility(8);
        this.mNoNetworkTips.setGravity(17);
        this.mNoNetworkTips.setText(getString(R$string.IDS_hwh_home_healthshop_servers_error));
        this.mTipsImageView.setBackgroundResource(R$drawable.watchface_ic_network);
    }

    private void showWatchFaceServerRetryPage() {
        HwLog.i(TAG, "showWatchFaceServerRetryPage");
        showToolsBar();
        dw.b(this.mNetworkLayout, 0);
        dw.b(this.mWebView, 4);
        dw.b(this.mBtnNetSetting, 4);
        setTxtTipsVisibility(8);
        this.mNoNetworkTips.setGravity(17);
        this.mNoNetworkTips.setText(getString(R$string.IDS_watchface_server_retry));
        this.mTipsImageView.setBackgroundResource(R$drawable.watchface_ic_network);
    }

    public void showWatchFaceNetworkRetryPage() {
        HwLog.i(TAG, "showWatchFaceServerRetryPage");
        showToolsBar();
        dw.b(this.mNetworkLayout, 0);
        dw.b(this.mWebView, 4);
        dw.b(this.mBtnNetSetting, 0);
        setTxtTipsVisibility(8);
        this.mNoNetworkTips.setGravity(17);
        this.mNoNetworkTips.setText(getString(R$string.IDS_submit_error_message_1));
        this.mTipsImageView.setBackgroundResource(R$drawable.watchface_ic_network);
    }

    private void showWatchFaceServerErrorPage() {
        HwLog.i(TAG, "showWatchFaceServerErrorPage");
        showToolsBar();
        this.mNetworkLayout.setVisibility(0);
        dw.b(this.mWebView, 4);
        this.mBtnNetSetting.setVisibility(8);
        setTxtTipsVisibility(8);
        this.mNoNetworkTips.setGravity(17);
        this.mNoNetworkTips.setText(getString(R$string.IDS_watchface_server_error));
        this.mTipsImageView.setBackgroundResource(R$drawable.watchface_ic_network);
    }

    private void setTxtTipsVisibility(int i) {
        this.mWebViewTipsUpdate.setVisibility(i);
        this.mWebViewTipsCheckDate.setVisibility(i);
        this.mWebViewTipsCustomerService.setVisibility(i);
    }

    @Override // com.huawei.watchface.mvp.base.BaseActivity, com.huawei.secure.android.common.activity.SafeFragmentActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        String str = TAG;
        HwLog.i(str, "onResume");
        this.mDoubleClickReceiver = new DoubleClickReceiver();
        IntentFilter intentFilter = new IntentFilter(Constants.CLICK_STATUS_BAR_ACTION);
        if (CommonUtils.isAndroid13()) {
            this.mContext.registerReceiver(this.mDoubleClickReceiver, intentFilter, Constants.SYSTEM_UI_PERMISSION, null, 4);
        } else {
            this.mContext.registerReceiver(this.mDoubleClickReceiver, intentFilter, Constants.SYSTEM_UI_PERMISSION, null);
        }
        if (this.mCustomWebView != null && this.mWebView != null) {
            HwLog.d(str, "WatchFaceConstant.WEB_VIEW_STATUS_ON_RESUME");
            this.mCustomWebView.c(Constants.WEBVIEW_STATUS_ON_RESUME);
        }
        CustomWebView customWebView = this.mCustomWebView;
        if (customWebView != null) {
            if (customWebView.b() != null) {
                this.mCustomWebView.b().dismiss();
            }
            this.mCustomWebView.g();
            PluginOperationAdapter pluginOperationAdapter = this.mAdapter;
            if (pluginOperationAdapter != null) {
                pluginOperationAdapter.updateWatchFaceCallback(this.mCustomWebView, isRootActivity());
            }
            this.mCustomWebView.addJavascriptInterface(getJSInterface());
        }
        if (isRootActivity() && dt.a()) {
            transmitSetDefaultWatchFaceResult();
            dt.a(false);
        }
        super.onResume();
    }

    @Override // com.huawei.watchface.mvp.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onPause() {
        super.onPause();
        String str = TAG;
        HwLog.i(str, "onPause");
        if (this.mCustomWebView != null && this.mWebView != null) {
            HwLog.d(str, "WatchFaceConstant.WEB_VIEW_STATUS_ON_PAUSE");
            this.mCustomWebView.c(Constants.WEBVIEW_STATUS_ON_PAUSE);
        }
        eo.a().c();
        eq.c().b();
        unregisterReceiver(this.mDoubleClickReceiver);
    }

    @Override // com.huawei.watchface.mvp.base.BaseActivity, com.huawei.secure.android.common.activity.SafeFragmentActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        String str = TAG;
        HwLog.i(str, "onDestroy() enter.");
        d.a().d();
        HwWatchFaceBtManager.getInstance(this).clearRegisterH5();
        WatchFaceWearManager.getInstance(this.mContext).a();
        if (isRootActivity()) {
            HwLog.d(str, "isRootActivity setIsSyncAndDestroyWatchFace");
            WatchFacePhotoAlbumManager.getInstance(this.mContext).a(false, true);
            WatchFaceWearManager.getInstance(this.mContext).a(false, true);
            WatchFaceKaleidoscopeManager.getInstance(this.mContext).a(false, true);
            fj.a().a(false);
            fj.a().c();
        }
        unregisterWebviewReceiver();
        Intent intent = this.mIntent;
        if (intent != null) {
            intent.putExtra("url", this.mUrl);
            setIntent(this.mIntent);
        }
        if (this.mHandler != null) {
            HwLog.i(str, "onDestroy() mHandler remove.");
            this.mHandler.removeCallbacksAndMessages(null);
            this.mHandler = null;
        }
        if (isRootActivity()) {
            this.mIBaseResponseCallback = null;
            destroyWebView();
            PluginOperationAdapter pluginOperationAdapter = this.mAdapter;
            if (pluginOperationAdapter != null) {
                pluginOperationAdapter.setWatchFaceCallback(null, isRootActivity());
                this.mAdapter.releaseWatchFaceLoginHelper();
                HwLog.i(str, "onDestroy() clearWatchFaceCallback");
            }
            WatchFaceHttpUtil.n();
            HwWatchFaceApi.getInstance(this).clearCountryCode();
            HwWatchFaceApi.getInstance(this).setDisableWearInfoList(null);
            HwWatchFaceApi.getInstance(this).setConnectStatus(null);
            HwWatchFaceApi.getInstance(this).setDeviceInfo(null);
            HwWatchFaceApi.getInstance(this).setAccountInfoMap(null);
            dj.a((Integer) null);
            t.a().h();
            fb.b(this);
            setActivity(null);
            as.b().e();
        } else {
            ChoosePicUtil.a();
        }
        super.onDestroy();
    }

    private void destroyWebView() {
        String str = TAG;
        HwLog.i(str, "destroyWebView");
        CustomWebView customWebView = this.mCustomWebView;
        if (customWebView != null) {
            customWebView.p();
            this.mCustomWebView = null;
        }
        if (this.mWebView != null) {
            HwLog.i(str, "onDestroy destroyWebView");
            this.mWebView.setTag("0");
            ViewParent parent = this.mWebView.getParent();
            if (parent instanceof ViewGroup) {
                ((ViewGroup) parent).removeView(this.mWebView);
            }
            this.mWebView.stopLoading();
            this.mWebView.getSettings().setJavaScriptEnabled(false);
            this.mWebView.clearHistory();
            this.mWebView.clearView();
            this.mWebView.removeAllViews();
            this.mWebView.destroy();
            this.mWebView = null;
        }
    }

    protected void reload() {
        String str = TAG;
        HwLog.i(str, "reload() enter.");
        if (!CommonUtils.f()) {
            HwLog.i(str, "reload() isNetworkConnected.");
            return;
        }
        dw.b(this.mNetworkLayout, 4);
        dw.b(this.mWebView, 0);
        String a2 = WebViewUtils.a((WebView) this.mWebView);
        if (this.mCustomWebView != null) {
            if (TextUtils.isEmpty(a2)) {
                HwLog.i(str, "reload() reloadUrl is empty.");
                this.mCustomWebView.loadSafeUrl(this.mUrl, false);
            } else {
                this.mCustomWebView.loadSafeUrl(a2, true);
            }
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        HwLog.i(TAG, "reload");
        if (view == this.mReloadLayout) {
            reload();
        } else if (view == this.mBtnNetSetting) {
            CommonUtils.f(this.mContext);
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    @Override // com.huawei.secure.android.common.activity.SafeFragmentActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (intent == null) {
            HwLog.i(TAG, "onActivityResult() data is null.");
        }
        String str = TAG;
        HwLog.i(str, "onActivityResult() requestCode is " + i + " resultCode is " + i2);
        SafeIntent safeIntent = new SafeIntent(intent);
        if (i == 10086) {
            if (this.mCustomWebView.a() == null) {
                HwLog.i(str, "onActivityResult FILE_CHOOSER_RESULT_CODE return");
                return;
            } else {
                this.mCustomWebView.a().onReceiveValue(i2 != -1 ? null : safeIntent.getData());
                this.mCustomWebView.a((ValueCallback<Uri>) null);
                return;
            }
        }
        if (i != 10087) {
            if (i == 20001) {
                takePhoto(i2, safeIntent);
                return;
            } else {
                onActivityResultTwo(i, i2, safeIntent);
                return;
            }
        }
        if (this.mCustomWebView.c() == null) {
            HwLog.i(str, "onActivityResult FILE_CHOOSER_RESULT_CODE_FOR_ANDROID_5 return");
            return;
        }
        Uri data = i2 != -1 ? null : safeIntent.getData();
        if (data != null) {
            HwLog.i(str, "onActivityResult FILE_CHOOSER_RESULT_CODE_FOR_ANDROID_5 result not null");
            this.mCustomWebView.c().onReceiveValue(new Uri[]{data});
        } else {
            HwLog.i(str, "onActivityResult FILE_CHOOSER_RESULT_CODE_FOR_ANDROID_5 result null");
            this.mCustomWebView.c().onReceiveValue(new Uri[0]);
        }
        this.mCustomWebView.b((ValueCallback<Uri[]>) null);
    }

    public void onActivityResultTwo(int i, int i2, SafeIntent safeIntent) {
        CustomWebView customWebView;
        if (i == 0) {
            String str = TAG;
            HwLog.i(str, "onActivityResultTwo CHOOSE_PHOTO_REQUEST_CODE");
            if (this.mCustomWebView == null) {
                HwLog.i(str, "onActivityResultTwo mCustomWebView is null.");
                return;
            } else if (!isChooseVideoBack()) {
                clipPic(safeIntent, true);
            }
        } else if (i == 1) {
            HwLog.i(TAG, "onActivityResultTwo GO_CAMERA_REQUEST_CODE");
            if (!isChooseVideoBack()) {
                clipPic(safeIntent, false);
            }
        } else if (i == 3) {
            transmitClippedPath(safeIntent);
        } else if (i == 10088) {
            transferWatchFaceFile(i2, safeIntent);
        } else if (i == 101) {
            handlePayResult(safeIntent, i);
        } else if (i == 103) {
            handlePayResult(safeIntent, i);
        } else if (i == 104) {
            handleSubscriptionPayResult(safeIntent, i);
        } else if (i == 105) {
            handleSubscriptionPayResult(safeIntent, i);
        } else if (i == 102) {
            disableWatchfaceService(i2);
        } else if (i != 42001) {
            HwLog.i(TAG, "onActivityResultTwo invalid requestCode.");
        } else if (i2 == 42002) {
            finish();
        }
        if (i2 != 0 || (customWebView = this.mCustomWebView) == null) {
            return;
        }
        customWebView.h();
    }

    private boolean isChooseVideoBack() {
        CustomWebView customWebView = this.mCustomWebView;
        return customWebView != null && customWebView.getChooseToClipResourceStyle() == 1;
    }

    private boolean isChooseSearchBack() {
        CustomWebView customWebView = this.mCustomWebView;
        return customWebView != null && customWebView.getChooseToClipResourceStyle() == 4;
    }

    protected boolean isChoosePhotoBack() {
        CustomWebView customWebView = this.mCustomWebView;
        return customWebView != null && customWebView.getChooseToClipResourceStyle() == 0;
    }

    private void handleSubscriptionPayResult(Intent intent, int i) {
        PayResultInfo payResultInfo;
        String str = TAG;
        HwLog.i(str, "handleSubscriptionPayResult --start");
        if (i == 104) {
            HwLog.i(str, "handleSubscriptionPayResult -- in SUB_HANDLER_EVENT_PAY_RESULT");
            payResultInfo = HuaweiPay.HuaweiPayApi.getPayResultInfoFromIntent(intent);
        } else if (i == 105) {
            HwLog.i(str, "handleSubscriptionPayResult -- in SUB_IAP_EVENT_PAY_RESULT");
            payResultInfo = Pay.getPayClient((Activity) this).getPayResultInfoFromIntent(intent);
        } else {
            payResultInfo = null;
        }
        handleAfterMembershipPaymentResult(intent, i, payResultInfo);
        if (payResultInfo != null && this.mAdapter != null) {
            HwLog.i(str, "handleSubscriptionPayResult -- payResultInfo : " + payResultInfo.getReturnCode());
            this.mAdapter.uploadSubscriptionPayResult(payResultInfo);
            return;
        }
        if (payResultInfo == null) {
            HwLog.e(str, "handleSubscriptionPayResult -- payResultInfo is null");
        }
        if (this.mAdapter == null) {
            HwLog.e(str, "handleSubscriptionPayResult -- mAdapter is null");
        }
    }

    private void handlePayResult(Intent intent, int i) {
        PayResultInfo payResultInfoFromIntent;
        String str = TAG;
        HwLog.i(str, "handlePayResult() start resolve huawei pay");
        CustomWebView customWebView = this.mCustomWebView;
        if (customWebView != null) {
            customWebView.transmitFinishPay("");
        }
        String b = s.a(Environment.getApplicationContext()).b();
        if (i == 101) {
            payResultInfoFromIntent = HuaweiPay.HuaweiPayApi.getPayResultInfoFromIntent(intent);
        } else {
            payResultInfoFromIntent = i == 103 ? Pay.getPayClient((Activity) this).getPayResultInfoFromIntent(intent) : null;
        }
        if (payResultInfoFromIntent != null && this.mAdapter != null) {
            HwLog.i(str, "handlePayResult() onActivityResult returnCode : " + payResultInfoFromIntent.getReturnCode());
            ej.a(b).c(payResultInfoFromIntent.getOrderID()).a(BleConstants.KEY_PATH, this.mUrl).a("reqCode", String.valueOf(i)).a("ErrMsg", payResultInfoFromIntent.getErrMsg()).a("Time", payResultInfoFromIntent.getTime()).a("WithholdID", payResultInfoFromIntent.getWithholdID()).a("ReturnCode", String.valueOf(payResultInfoFromIntent.getReturnCode())).a("RequestId", payResultInfoFromIntent.getRequestId());
            if (payResultInfoFromIntent.getReturnCode() == 0) {
                HwLog.i(str, "handlePayResult() Payment result success");
            } else if (payResultInfoFromIntent.getReturnCode() == 30000) {
                HwLog.i(str, "handlePayResult() Payment result user cancel");
                s.a(this.mContext).a(payResultInfoFromIntent);
            } else if (payResultInfoFromIntent.getReturnCode() == 30099) {
                HwLog.i(str, "handlePayResult() Payment result error");
                s.a(this.mContext).a(payResultInfoFromIntent);
            } else if (payResultInfoFromIntent.getReturnCode() == -1) {
                HwLog.i(str, "handlePayResult() Payment result failed");
                s.a(this.mContext).a(payResultInfoFromIntent);
            } else {
                HwLog.i(str, "handlePayResult() Payment result unknow");
            }
            this.mAdapter.uploadPayResult(String.valueOf(payResultInfoFromIntent.getReturnCode()), payResultInfoFromIntent.getErrMsg());
        } else if (!CommonUtils.i()) {
            showPayFailDialog();
        }
        ej.a(b, payResultInfoFromIntent != null ? payResultInfoFromIntent.getReturnCode() : com.huawei.openalliance.ad.constant.Constants.COORDINATE_FAIL_DEF, null);
    }

    private void handleAfterMembershipPaymentResult(Intent intent, int i, PayResultInfo payResultInfo) {
        String str = TAG;
        HwLog.i(str, "handleAfterPaymentResult() start resolve huawei pay");
        CustomWebView customWebView = this.mCustomWebView;
        if (customWebView != null) {
            customWebView.transmitFinishPay("");
        }
        String c = s.a(Environment.getApplicationContext()).c();
        if (payResultInfo != null && this.mAdapter != null) {
            HwLog.i(str, "handlePayResult() onActivityResult returnCode : " + payResultInfo.getReturnCode());
            if (c == null) {
                c = "VIP" + payResultInfo.getRequestId();
            }
            ej.a(c).c(payResultInfo.getOrderID()).a(BleConstants.KEY_PATH, this.mUrl).a("reqCode", String.valueOf(i)).a("ErrMsg", payResultInfo.getErrMsg()).a("Time", payResultInfo.getTime()).a("WithholdID", payResultInfo.getWithholdID()).a("ReturnCode", String.valueOf(payResultInfo.getReturnCode())).a("RequestId", payResultInfo.getRequestId());
            if (payResultInfo.getReturnCode() == 0) {
                HwLog.i(str, "handlePayResult() Payment result success");
                t.a().c();
            } else if (payResultInfo.getReturnCode() == 30000) {
                HwLog.i(str, "handlePayResult() Payment result user cancel");
                s.a(this.mContext).a(payResultInfo);
            } else if (payResultInfo.getReturnCode() == 30099) {
                HwLog.i(str, "handlePayResult() Payment result error");
                s.a(this.mContext).a(payResultInfo);
            } else if (payResultInfo.getReturnCode() == -1) {
                HwLog.i(str, "handlePayResult() Payment result failed");
                s.a(this.mContext).a(payResultInfo);
            } else {
                HwLog.i(str, "handlePayResult() Payment result unknow");
            }
        } else if (!CommonUtils.i()) {
            showPayFailDialog();
        }
        ej.a(c, payResultInfo != null ? payResultInfo.getReturnCode() : com.huawei.openalliance.ad.constant.Constants.COORDINATE_FAIL_DEF, null);
    }

    private void showPayFailDialog() {
        new cm.a(this).a(DensityUtil.getStringById(R$string.IDS_pay_failed)).b(DensityUtil.getStringById(R$string.IDS_watchface_watch_permission_pay_fail_content)).a(R$string.IDS_user_permission_know, new View.OnClickListener() { // from class: com.huawei.watchface.api.WebViewActivity$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ViewClickInstrumentation.clickOnView(view);
            }
        }).a().show();
    }

    private void disableWatchfaceService(int i) {
        if (i == -1) {
            finish();
        }
    }

    private void transferWatchFaceFile(int i, Intent intent) {
        if (i != -1) {
            HwLog.i(TAG, "no select watch face file");
            return;
        }
        if (intent == null) {
            HwLog.w(TAG, "transferWatchFaceFile, intent is null.");
            return;
        }
        Uri data = intent.getData();
        if (data == null) {
            HwLog.i(TAG, "uri is null");
            return;
        }
        PluginOperationAdapter pluginOperationAdapter = this.mAdapter;
        if (pluginOperationAdapter != null) {
            pluginOperationAdapter.transferWatchFaceFile(data, this);
        }
    }

    private void takePhoto(int i, Intent intent) {
        String str = TAG;
        HwLog.i(str, "onActivityResult takePhoto REQ_CODE_TAKE_PHOTO SDK_INT >= 21");
        if (this.mCustomWebView.c() == null) {
            HwLog.i(str, "onActivityResult takePhoto REQ_CODE_TAKE_PHOTO getUploadMessageForAndroid5 null");
            return;
        }
        if (i == 0) {
            HwLog.i(str, "onActivityResult takePhoto REQ_CODE_TAKE_PHOTO resultCode == 0");
            this.mCustomWebView.c().onReceiveValue(new Uri[0]);
            this.mCustomWebView.b((ValueCallback<Uri[]>) null);
            return;
        }
        HwLog.i(str, "onActivityResult takePhoto other");
        Uri d = (intent == null || i != -1) ? this.mCustomWebView.d() : intent.getData();
        if (d != null) {
            HwLog.i(str, "onActivityResult takePhoto REQ_CODE_TAKE_PHOTO result != null");
            this.mCustomWebView.c().onReceiveValue(new Uri[]{d});
        } else {
            HwLog.i(str, "onActivityResult takePhoto REQ_CODE_TAKE_PHOTO result is null");
            this.mCustomWebView.c().onReceiveValue(new Uri[0]);
        }
        this.mCustomWebView.b((ValueCallback<Uri[]>) null);
    }

    private void transmitClippedPath(Intent intent) {
        String str;
        if (intent != null) {
            try {
                str = intent.getStringExtra(ClipImageActivity.CLIP_RESULT_PATH);
            } catch (ClassCastException unused) {
                str = null;
            }
            String filterFilePath = CommonUtils.filterFilePath(str);
            if (TextUtils.isEmpty(filterFilePath)) {
                HwLog.e(TAG, "transmitClippedPath() clippedPath is empty");
                transmitClippedImageType(intent);
                return;
            }
            if (this.mCustomWebView != null) {
                String str2 = TAG;
                HwLog.e(str2, "transmitClippedPath() --  mCustomWebView not null");
                LatonaWatchFaceProvider.getInstance(this.mContext).setTempImagePaths(filterFilePath);
                if (isChooseKaleidoscopeBack()) {
                    HwLog.i(str2, "generateKaleidoscopeInfo");
                    generateKaleidoscopeInfo(filterFilePath);
                } else if (isChooseWearBack()) {
                    HwLog.i(str2, "generateWearInfo");
                    generateWearInfo(filterFilePath);
                } else {
                    HwLog.i(str2, "checkForPortrait");
                    checkForPortrait(filterFilePath, false);
                }
            }
        }
    }

    private void transmitClippedImageType(Intent intent) {
        String str = "";
        int i = 0;
        try {
            str = intent.getStringExtra(ClipImageActivity.IMAGE_SUFFIX);
            i = intent.getIntExtra(ClipImageActivity.IMAGE_SUFFIX_IS_NOT_SUPPORT, 0);
        } catch (ClassCastException unused) {
            HwLog.e(TAG, "transmitClippedImageType ClassCastException");
        } catch (Exception unused2) {
            HwLog.e(TAG, "transmitClippedImageType Exception");
        }
        HwLog.i(TAG, "transmitClippedImageType suffix:" + str + ",suffixSupportType:" + i);
        if (i == 1) {
            transmitClippedImageTypeIsNotSupport(str);
        }
    }

    private void transmitClippedImageTypeIsNotSupport(String str) {
        if (this.mCustomWebView != null) {
            HwLog.i(TAG, "transmitClippedImageTypeIsNotSupport suffix:" + str);
            this.mCustomWebView.d(str);
        }
    }

    protected void checkForPortrait(String str, boolean z) {
        this.mCustomWebView.transmitClippedPath(str);
    }

    private void clipPic(Intent intent, boolean z) {
        String fileSavedPath;
        Uri uri;
        String str = TAG;
        HwLog.i(str, "clipPic() isFromGallery: " + z);
        if (this.mChoosePicUtil == null) {
            HwLog.e(str, "clipPic() mChoosePicUtil is null");
            this.mChoosePicUtil = new ChoosePicUtil(this.mContext);
        }
        if (!z) {
            fileSavedPath = this.mChoosePicUtil.getFileSavedPath();
            uri = null;
        } else {
            if (intent == null) {
                HwLog.e(str, "clipPic() originData is null");
                return;
            }
            uri = intent.getData();
            if (uri == null) {
                HwLog.e(str, "clipPic() originalUri is null");
                return;
            }
            fileSavedPath = this.mChoosePicUtil.getPicPathByUri(this.mContext, uri);
        }
        if (TextUtils.isEmpty(fileSavedPath)) {
            HwLog.e(str, "clipPic() filePath is empty.");
            return;
        }
        if (isChooseWearBack()) {
            HwLog.i(str, "clipPic() -- isChooseWearBack true");
            WatchFaceWearManager.getInstance(this.mContext).a(fileSavedPath, uri);
        }
        saveClipPic(intent, fileSavedPath, uri);
    }

    private void saveClipPic(Intent intent, String str, Uri uri) {
        if (isChooseSearchBack()) {
            customScanClipPic(str, uri);
        } else {
            customClipPic(intent, str, uri, isH5Clip());
        }
    }

    public void customClipPic(Intent intent, String str, Uri uri, boolean z) {
        this.mChoosePicUtil.a((Activity) this.mContext, str, uri, isSquareBackground());
    }

    protected void customScanClipPic(String str, Uri uri) {
        this.mChoosePicUtil.b((Activity) this.mContext, str, uri, isSquareBackground());
    }

    protected boolean isChooseWearBack() {
        CustomWebView customWebView = this.mCustomWebView;
        return customWebView != null && customWebView.getChooseToClipResourceStyle() == 3;
    }

    protected boolean isSquareBackground() {
        WatchFaceSupportInfo watchFaceSupportInfo = HwWatchFaceBtManager.getInstance(this.mContext).getWatchFaceSupportInfo();
        return isChooseKaleidoscopeBack() || isChooseWearBack() || (watchFaceSupportInfo != null && watchFaceSupportInfo.isPortraitModeSupported());
    }

    protected boolean isChooseKaleidoscopeBack() {
        CustomWebView customWebView = this.mCustomWebView;
        return customWebView != null && customWebView.getChooseToClipResourceStyle() == 2;
    }

    public boolean isH5Clip() {
        CustomWebView customWebView = this.mCustomWebView;
        return customWebView != null && customWebView.isH5Clip;
    }

    @Override // com.huawei.watchface.mvp.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        super.onRequestPermissionsResult(i, strArr, iArr);
        String str = TAG;
        HwLog.i(str, "onRequestPermissionsResult requestCode is " + i);
        ey.a().a(strArr, iArr);
        if (i == 10010) {
            dp.a("android.permission.WRITE_EXTERNAL_STORAGE", false, "sp_permission_file");
            if (ew.a(this.mContext, strArr)) {
                PluginOperationAdapter pluginOperationAdapter = this.mAdapter;
                if (pluginOperationAdapter != null) {
                    pluginOperationAdapter.openSystemFileManager(this);
                }
            } else {
                HwWatchFaceApi.getInstance(this).showHealthAppSettingGuide(this, PermissionUtil.PermissionType.STORAGE.getPermission(), new View.OnClickListener() { // from class: com.huawei.watchface.api.WebViewActivity$$ExternalSyntheticLambda4
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        WebViewActivity.this.m891x5a75d57d(view);
                    }
                }, new View.OnClickListener() { // from class: com.huawei.watchface.api.WebViewActivity$$ExternalSyntheticLambda5
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        WebViewActivity.this.m892x61db0a9c(view);
                    }
                });
            }
        } else {
            HwLog.d(str, "onRequestPermissionsResult other");
        }
        boolean verifyPermissions = PermissionUtils.verifyPermissions(iArr);
        CustomWebView customWebView = this.mCustomWebView;
        if (customWebView != null) {
            customWebView.a(i, verifyPermissions);
            this.mCustomWebView.b(strArr);
        }
    }

    /* renamed from: lambda$onRequestPermissionsResult$7$com-huawei-watchface-api-WebViewActivity, reason: not valid java name */
    /* synthetic */ void m891x5a75d57d(View view) {
        HwLog.d(TAG, "positiveListener click");
        CustomWebView customWebView = this.mCustomWebView;
        if (customWebView != null) {
            customWebView.a("0");
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    /* renamed from: lambda$onRequestPermissionsResult$8$com-huawei-watchface-api-WebViewActivity, reason: not valid java name */
    /* synthetic */ void m892x61db0a9c(View view) {
        HwLog.d(TAG, "negativeListener click");
        CustomWebView customWebView = this.mCustomWebView;
        if (customWebView != null) {
            customWebView.a("0");
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    public void notifyH5ApplyPermission(String str) {
        HwLog.i(TAG, "notifyH5ApplyPermission");
        CustomWebView customWebView = this.mCustomWebView;
        if (customWebView != null) {
            customWebView.a(str);
        }
    }

    public static void setActivity(Activity activity) {
        HwLog.i(TAG, "setActivity");
        sActivity = activity;
    }

    public static Activity getActivity() {
        HwLog.i(TAG, "getActivity");
        return sActivity;
    }

    public class DoubleClickReceiver extends SafeBroadcastReceiver {
        public DoubleClickReceiver() {
        }

        @Override // com.huawei.secure.android.common.intent.SafeBroadcastReceiver
        public void onReceiveMsg(Context context, Intent intent) {
            HwLog.i(WebViewActivity.TAG, "DoubleClickReceiver onReceive()");
            if (WebViewActivity.this.mWebView != null) {
                WebViewActivity.this.mWebView.setScrollY(0);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void loadSafeUrl(final String str) {
        this.mAnalyticsH5Event.c(str);
        JsSafeUrlSystemParamManager.getInstance().a(new JsSafeUrlSystemParamManager.a() { // from class: com.huawei.watchface.api.WebViewActivity.3
            @Override // com.huawei.watchface.mvp.model.helper.JsSafeUrlSystemParamManager.a
            public void onGetFinished() {
                at a2 = as.b().a();
                if (a2 == null || !a2.e(str)) {
                    HwLog.i(WebViewActivity.TAG, "loadSafeUrl - loadPage: SupportType is null.");
                    WebViewActivity.this.loadSafeUrlAfterloadWhite(str);
                } else if (WebViewActivity.this.mWebView != null) {
                    SafeWebView safeWebView = WebViewActivity.this.mWebView;
                    String str2 = str;
                    safeWebView.loadUrl(str2);
                    WebViewInstrumentation.loadUrl(safeWebView, str2);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void loadSafeUrlAfterloadWhite(final String str) {
        as.b().a(new IBaseResponseCallback() { // from class: com.huawei.watchface.api.WebViewActivity.4
            @Override // com.huawei.watchface.utils.callback.IBaseResponseCallback
            public void onResponse(int i, Object obj) {
                if (obj instanceof at) {
                    boolean e = ((at) obj).e(str);
                    HwLog.i(WebViewActivity.TAG, "reload whitelist isWhiteUrl : " + e);
                    if (!e) {
                        HwLog.e(WebViewActivity.TAG, "reload isWhiteUrl false.");
                        return;
                    }
                    SafeWebView safeWebView = WebViewActivity.this.mWebView;
                    String str2 = str;
                    safeWebView.loadUrl(str2);
                    WebViewInstrumentation.loadUrl(safeWebView, str2);
                }
            }
        });
        as.b().c();
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        CustomWebView customWebView = this.mCustomWebView;
        if (customWebView == null || (!customWebView.f11111a && !this.mCustomWebView.b)) {
            super.onBackPressed();
        } else {
            HwLog.i(TAG, "onBackPressed -- transmitAppGoBack()");
            this.mCustomWebView.transmitAppGoBack();
        }
    }

    private void registerWebviewReceiver() {
        if (this.mCommonWebViewBroadcastReceiver == null) {
            this.mCommonWebViewBroadcastReceiver = new CommonWebViewBroadcastReceiver();
        }
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        if (MobileInfoHelper.isHuaweiDevice()) {
            intentFilter.addAction("com.huawei.hwid.ACTION_REMOVE_ACCOUNT");
        }
        WeakReference<Context> weakReference = this.context;
        if (weakReference == null || weakReference.get() == null) {
            return;
        }
        if (CommonUtils.isAndroid13()) {
            this.context.get().registerReceiver(this.mCommonWebViewBroadcastReceiver, intentFilter, HW_SIGNATURE_OR_SYSTEM, null, 4);
        } else {
            this.context.get().registerReceiver(this.mCommonWebViewBroadcastReceiver, intentFilter, HW_SIGNATURE_OR_SYSTEM, null);
        }
    }

    private void unregisterWebviewReceiver() {
        try {
            WeakReference<Context> weakReference = this.context;
            if (weakReference == null || weakReference.get() == null) {
                return;
            }
            this.context.get().unregisterReceiver(this.mCommonWebViewBroadcastReceiver);
        } catch (Exception e) {
            HwLog.i(TAG, "unregisterWebviewReceiver error=" + HwLog.printException(e));
        }
    }

    class CommonWebViewBroadcastReceiver extends SafeBroadcastReceiver {
        private CommonWebViewBroadcastReceiver() {
        }

        @Override // com.huawei.secure.android.common.intent.SafeBroadcastReceiver
        public void onReceiveMsg(Context context, Intent intent) {
            HwLog.i(WebViewActivity.TAG, "CommonWebViewBroadcastReceiver onReceiveMsg");
            if (intent == null || TextUtils.isEmpty(intent.getAction())) {
                HwLog.i(WebViewActivity.TAG, "CommonWebViewBroadcastReceiver intent or action is null.");
                return;
            }
            if (MobileInfoHelper.isHuaweiDevice() && "com.huawei.hwid.ACTION_REMOVE_ACCOUNT".equals(intent.getAction())) {
                WebViewActivity.this.finish();
            }
            if ("android.net.conn.CONNECTIVITY_CHANGE".equals(Objects.requireNonNull(intent.getAction()))) {
                if (WebViewActivity.this.mCustomWebView == null) {
                    HwLog.i(WebViewActivity.TAG, "CommonWebViewBroadcastReceiver mCustomWebView is null.");
                    return;
                }
                if (!CommonUtils.f()) {
                    if (p.a(context).f()) {
                        ds.b(DensityUtil.getStringById(R$string.network_is_error));
                    }
                } else {
                    ac.a().b(CommonUtils.B());
                }
                WebViewActivity.this.mCustomWebView.e(WebViewActivity.this.getNetworkInfo());
                dj.a((Integer) null);
            }
        }
    }

    public String getNetworkInfo() {
        int b = dj.b();
        return new Gson().toJson(new JsNetwork(b != 1 ? (b == 2 || b == 3 || b == 4 || b == 5) ? JsNetwork.NetworkType.MOBILE : "none" : "wifi", dj.a(Environment.getApplicationContext())));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void uploadH5LoadUrl() {
        if (this.mAnalyticsH5Event.d() == null) {
            return;
        }
        ef efVar = this.mAnalyticsH5Event;
        eo.a(efVar, efVar.g());
    }

    private void getDesignerInfo() {
        HwLog.i(TAG, "getDesignerInfo() enter.");
        try {
            ThreadPoolManager.getInstance().execute(new u(new IBaseResponseCallback() { // from class: com.huawei.watchface.api.WebViewActivity$$ExternalSyntheticLambda6
                @Override // com.huawei.watchface.utils.callback.IBaseResponseCallback
                public final void onResponse(int i, Object obj) {
                    WebViewActivity.lambda$getDesignerInfo$9(i, obj);
                }
            }));
        } catch (Exception e) {
            HwLog.i(TAG, "getDesignerInfo() error: " + HwLog.printException(e));
        }
    }

    static /* synthetic */ void lambda$getDesignerInfo$9(int i, Object obj) {
        String str = TAG;
        HwLog.i(str, "getDesignerInfo() errorCode: " + i + ", object:" + obj);
        if (i == 0 && (obj instanceof String)) {
            boolean equals = "1".equals((String) obj);
            HwLog.i(str, "getDesignerInfo() onSuccess, isDesigner: " + equals);
            dp.a("current_user_is_designer", equals, "goal_steps_perference");
        }
    }

    private void initAnalyticsH5Event() {
        if (this.mAnalyticsH5Event == null) {
            this.mAnalyticsH5Event = new ef();
        }
        this.mAnalyticsH5Event.a(System.currentTimeMillis());
        this.mAnalyticsH5Event.b("2");
    }

    public void resetKaleidoscopeCurrentMaxIndex() {
        this.mKaleidoscopeCurrentMaxIndex = -1;
    }

    private void generateKaleidoscopeInfo(String str) {
        String str2 = SafeString.substring(str, 0, str.lastIndexOf(".")) + File.separator;
        generateKaleidoscopeBg(KaleidoscopeDraw.a.HONEYCOMB.toString(), str, str2, "honeycomb.png");
        generateKaleidoscopeBg(KaleidoscopeDraw.a.RADIAL.toString(), str, str2, "radia.png");
        BackgroundOptionalConfig backgroundOptionalConfig = new BackgroundOptionalConfig();
        backgroundOptionalConfig.setResPreview(str);
        if (this.mKaleidoscopeCurrentMaxIndex == -1) {
            this.mKaleidoscopeCurrentMaxIndex = WatchFaceKaleidoscopeManager.getInstance(this).e();
        }
        HwLog.i(TAG, "generateKaleidoscopeInfo() mKaleidoscopeCurrentMaxIndex: " + this.mKaleidoscopeCurrentMaxIndex);
        int i = this.mKaleidoscopeCurrentMaxIndex + 1;
        this.mKaleidoscopeCurrentMaxIndex = i;
        backgroundOptionalConfig.setIndex(String.valueOf(i));
        backgroundOptionalConfig.setHoneycombBgPath(str2 + "honeycomb.png");
        backgroundOptionalConfig.setRadiationBgPath(str2 + "radia.png");
        this.mCustomWebView.a(backgroundOptionalConfig);
    }

    private void generateKaleidoscopeBg(String str, String str2, String str3, String str4) {
        if (PversionSdUtils.getFile(str3 + str4).exists()) {
            HwLog.i(TAG, "generateKaleidoscopeBg() already exist: " + str4);
            return;
        }
        WatchFaceBitmapUtil.saveBitmapToFile(new KaleidoscopeDraw(this, str2, str).a(), Bitmap.CompressFormat.PNG, str3, str4);
    }

    public void hideLoadingDialog() {
        this.mCustomWebView.hideLoadingDialog();
    }

    protected void showLoadingDialog() {
        this.mCustomWebView.showLoadingDialog(DensityUtil.getStringById(R$string.saving));
    }

    @Override // com.huawei.watchface.mvp.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
