package com.huawei.operation.activity;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.BroadcastReceiver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.ViewStub;
import android.view.ViewTreeObserver;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;
import androidx.core.content.ContextCompat;
import androidx.webkit.WebSettingsCompat;
import androidx.webkit.WebViewFeature;
import com.huawei.haf.application.BroadcastManager;
import com.huawei.haf.handler.BaseHandler;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewScrollInstrumentation;
import com.huawei.hianalytics.visual.autocollect.instrument.WebViewInstrumentation;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcloudjs.JsClientApi;
import com.huawei.hwcloudjs.api.IAccessToken;
import com.huawei.hwcloudjs.api.TaskCallBack;
import com.huawei.hwcloudjs.service.hms.HmsCoreApi;
import com.huawei.hwcloudjs.service.hms.HmsLiteCoreApi;
import com.huawei.hwcloudjs.service.hms.bean.AccessTokenInfo;
import com.huawei.hwcloudjs.service.hms.bean.Status;
import com.huawei.hwcloudmodel.callback.GrsQueryCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.hwsmartinteractmgr.data.SmartMsgConstant;
import com.huawei.login.ui.login.AccountConstants;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.operation.BuildConfig;
import com.huawei.operation.OpAnalyticsConstants;
import com.huawei.operation.activity.WebViewActivity;
import com.huawei.operation.adapter.PluginOperationAdapter;
import com.huawei.operation.beans.TitleBean;
import com.huawei.operation.ble.BleConstants;
import com.huawei.operation.ble.BleOperatorImpl;
import com.huawei.operation.h5pro.adapter.H5ProJsAdapter;
import com.huawei.operation.js.JsInteraction;
import com.huawei.operation.jsoperation.JsCommand;
import com.huawei.operation.jsoperation.JsCommandOption;
import com.huawei.operation.jsoperation.JsOperationProducer;
import com.huawei.operation.jsoperation.JsRegisterFunctionResUtil;
import com.huawei.operation.operation.PluginOperation;
import com.huawei.operation.share.CaptureUtils;
import com.huawei.operation.share.ResultCallback;
import com.huawei.operation.utils.ActivityHtmlPathApi;
import com.huawei.operation.utils.AndroidBugWorkaround;
import com.huawei.operation.utils.Constants;
import com.huawei.operation.utils.OperationUtils;
import com.huawei.operation.utils.UriConstants;
import com.huawei.operation.utils.WebViewHelp;
import com.huawei.operation.utils.WebViewUtils;
import com.huawei.operation.view.ConfigConstants;
import com.huawei.operation.view.CustomWebView;
import com.huawei.operation.vmall.VmallLoginWebview;
import com.huawei.phoneservice.feedbackcommon.network.FeedbackWebConstants;
import com.huawei.pluginbase.PluginBaseAdapter;
import com.huawei.trade.TradeViewApi;
import com.huawei.trade.datatype.TradeViewInfo;
import com.huawei.ui.commonui.R$color;
import com.huawei.ui.commonui.R$drawable;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.button.HealthButton;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.popupview.PopViewList;
import com.huawei.ui.commonui.progressbar.HealthProgressBar;
import com.huawei.ui.commonui.titlebar.CustomTitleBar;
import defpackage.ezd;
import defpackage.fdu;
import defpackage.ixx;
import defpackage.jdi;
import defpackage.jdx;
import defpackage.jeg;
import defpackage.nkx;
import defpackage.nqc;
import defpackage.nrh;
import defpackage.nrt;
import defpackage.nrz;
import defpackage.nsf;
import defpackage.nsn;
import defpackage.nsy;
import health.compact.a.CommonUtil;
import health.compact.a.GRSManager;
import health.compact.a.LanguageUtil;
import health.compact.a.Services;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.Utils;
import java.lang.ref.WeakReference;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class WebViewActivity extends BaseActivity implements View.OnClickListener {
    private static final String ANNUAL_URL_CONTENT = "annualReport";
    private static final String APP_MARKET_H5 = "APPMARKET";
    private static final int APP_MARKET_REGISTER_RECEIVER = 9;
    private static final String BLE_INTRODUCTION_TYPE = "bleIntroductionType";
    private static final String FROM_WHERE = "from";
    private static final int GET_APP_MARKET_CALLBACK = 0;
    private static final String KEY_DEVICE_INFO = "commonDeviceInfo";
    private static final String KEY_OPERATE_CODE = "operateCode";
    private static final String KEY_PRODUCT_ID = "productId";
    private static final String OPERATE_DELETE = "Delete";
    private static final int SCROLL_TO_BOTTOM_VALUE = 3;
    private static final int STEP_FINISH_LOAD = 3;
    private static final int STEP_START_LOAD = 2;
    private static final String TAG = "PluginOperation_WebViewActivity";
    private static final String VMALL_API_PATH = "/rest.php";
    private static final int WEBVIEW_STATUS = 7;
    private static Activity sActivity;
    private static boolean sIsActiveFlag;
    private String mActivityId;
    private String mActivityShareType;
    private PluginOperationAdapter mAdapter;
    private String mAppMarketType;
    private String mBiId;
    private String mBiName;
    private String mBiSource;
    private String mBleIntroductionType;
    private BleOperatorImpl mBleOperator;
    private HealthButton mBtnNetSetting;
    private Context mContext;
    private CustomTitleBar mCustomTitleBar;
    private CustomWebView mCustomWebView;
    private String mDeviceId;
    private DoubleClickReceiver mDoubleClickReceiver;
    private FrameLayout mFrameLayout;
    private String mFromWhere;
    private H5ProJsAdapter mH5ProJsAdapter;
    private Handler mHandler;
    private String mHealthRecommendHost;
    private boolean mIsFromHeartRateBoard;
    private boolean mIsGuide;
    private String mJsApiId;
    private String mMiaoHealthHost;
    private ImageView mMiaoImageView;
    private HealthTextView mMiaoTextView;
    private String mMobileVmallHost;
    private RelativeLayout mNetworkLayout;
    private HealthTextView mNoNetworkTips;
    private nqc mPopView;
    private HealthProgressBar mProgressBar;
    private RelativeLayout mRelativeLayout;
    private RelativeLayout mReloadLayout;
    private String mShopUrlStart;
    private LinearLayout mShoppingLayOut;
    private String mShouldBiShowTime;
    private RelativeLayout mTabsRelativeLayout;
    private ImageView mTipsImageView;
    private String mTitle;
    private float mTouchX;
    private float mTouchY;
    private ImageView mTradeImageview;
    private HealthTextView mTradeTextview;
    private LinearLayout mTraderOrder;
    private String mType;
    private String mUrl;
    private ImageView mVmallImageView;
    private HealthTextView mVmallTextView;
    private WebView mWebView;
    private LinearLayout mWebViewLayout;
    private HealthTextView mWebViewTipsCheckDate;
    private HealthTextView mWebViewTipsCustomerService;
    private HealthTextView mWebViewTipsUpdate;
    private WebViewActivityExt webViewActivityExt;
    private static final String[] BLACK_PATH_LIST = {"/data/data/", "/data/user/"};
    private static boolean mIsDesigner = false;
    private static boolean mIsShowBtnAdd = false;
    private static String mCurrentProductId = null;
    private static boolean sIsOrderPrevious = false;
    private static ixx sBiAnalyticsUtil = ixx.d();
    private static IBaseResponseCallback mCallback = null;
    private View mShoppingView = null;
    private Map<String, String> mShareData = new HashMap();
    private boolean mIsNextPageLeftBtnArrowType = false;
    private boolean mIsSetting = true;
    private boolean mIsChange = false;
    private boolean mIsShowTitleRightDeleteDevice = false;
    private int mUiOptions = 0;
    private String mProductId = null;
    private ContentValues mDeviceInfo = null;
    private long mBiStartTime = 0;
    private long mBiPauseTime = 0;
    private long mBiTotalTime = 0;
    private boolean mIsMiaoChoose = false;
    private boolean mIsVmallChoose = false;
    private boolean mIsHealthChoose = false;
    private boolean mIsFirstShowTab = false;
    private boolean mIsOrderManagerPage = false;
    private boolean mIsVmallOrMiaoChoose = true;
    private boolean mIsShowDelete = false;
    private boolean mIsFirstShow = true;
    private boolean mIsFullScreen = false;
    private boolean mIsLoaded = false;

    static {
        JsClientApi.setGrsAppName(AccountConstants.GRS_APP_NAME);
        if (CommonUtil.z(BaseApplication.getContext())) {
            LogUtil.a(TAG, "registerJsApi HmsLiteCoreApi");
            JsClientApi.registerJsApi(HmsLiteCoreApi.class);
            JsClientApi.registerIAccessTokenClass(new IAccessToken() { // from class: com.huawei.operation.activity.WebViewActivity.1
                @Override // com.huawei.hwcloudjs.api.IAccessToken
                public void getAccessToken(TaskCallBack taskCallBack) {
                    String accountInfo = LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1008);
                    Status status = new Status(0, "success");
                    status.setSuccessFlag(true);
                    taskCallBack.onResult(new AccessTokenInfo(accountInfo, status));
                }
            });
        } else {
            JsClientApi.registerJsApi(HmsCoreApi.class);
            LogUtil.a(TAG, "registerJsApi HmsCoreApi");
        }
    }

    /* loaded from: classes5.dex */
    static class CheckUserWeightCallback implements IBaseResponseCallback {
        private WeakReference<WebViewActivity> mWebViewActivity;

        CheckUserWeightCallback(WebViewActivity webViewActivity) {
            this.mWebViewActivity = new WeakReference<>(webViewActivity);
        }

        @Override // com.huawei.hwbasemgr.IBaseResponseCallback
        /* renamed from: onResponse */
        public void d(int i, final Object obj) {
            final WebViewActivity webViewActivity = this.mWebViewActivity.get();
            if (webViewActivity == null || webViewActivity.mWebView == null || webViewActivity.mAdapter == null) {
                LogUtil.h(WebViewActivity.TAG, "activity or mWebView mAdapter is null");
            } else {
                webViewActivity.mWebView.post(new Runnable() { // from class: com.huawei.operation.activity.WebViewActivity$CheckUserWeightCallback$$ExternalSyntheticLambda0
                    @Override // java.lang.Runnable
                    public final void run() {
                        r0.mAdapter.checkUserWeightAndShowDialog(WebViewActivity.this, obj);
                    }
                });
            }
        }
    }

    public static void setActiveFlag(boolean z) {
        sIsActiveFlag = z;
    }

    public static boolean getActiveFlag() {
        return sIsActiveFlag;
    }

    public static void setIsDesigner(boolean z) {
        mIsDesigner = z;
    }

    public static boolean getIsDesigner() {
        return mIsDesigner;
    }

    public static boolean getIsShowBtnAdd() {
        return mIsShowBtnAdd;
    }

    public static String getProductId() {
        return mCurrentProductId;
    }

    public static void setProductId(String str) {
        mCurrentProductId = str;
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity
    public void loadApplicationTheme() {
        LogUtil.a(TAG, "loadApplicationTheme: setTheme");
        setTheme(R.style.OperationAppTheme);
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mContext = this;
        Intent intent = getIntent();
        if (intent != null) {
            this.mUrl = intent.getStringExtra("url");
            this.mFromWhere = intent.getStringExtra("from");
            this.mAppMarketType = intent.getStringExtra("app_type");
            this.mDeviceInfo = (ContentValues) intent.getParcelableExtra(KEY_DEVICE_INFO);
            String stringExtra = intent.getStringExtra("productId");
            this.mProductId = stringExtra;
            setProductId(stringExtra);
            this.mBleIntroductionType = intent.getStringExtra(BLE_INTRODUCTION_TYPE);
            this.mDeviceId = intent.getStringExtra("deviceId");
            if (!TextUtils.isEmpty(this.mUrl)) {
                String trim = this.mUrl.trim();
                this.mUrl = trim;
                intent.putExtra("url", trim);
            }
            ContentValues contentValues = this.mDeviceInfo;
            if (contentValues == null || contentValues.size() == 0) {
                ContentValues contentValues2 = new ContentValues();
                this.mDeviceInfo = contentValues2;
                contentValues2.put("uniqueId", intent.getStringExtra("uniqueId"));
                this.mDeviceInfo.put("name", intent.getStringExtra("name"));
                this.mDeviceInfo.put("deviceType", intent.getStringExtra("deviceType"));
                this.mDeviceInfo.put(Constants.KEY_BLE_SCAN_MODE, Integer.valueOf(intent.getIntExtra(Constants.KEY_BLE_SCAN_MODE, 0)));
            }
            if (OPERATE_DELETE.equals(intent.getStringExtra(KEY_OPERATE_CODE))) {
                this.mIsShowDelete = true;
            }
            if (!TextUtils.isEmpty(this.mDeviceId)) {
                JsInteraction.setDeviceId(this.mDeviceId);
            }
        }
        keepScreenOn();
        this.mIsFullScreen = WebViewUtils.isFullScreen(this.mUrl);
        this.webViewActivityExt = new WebViewActivityExt();
        putBiEventLoadPage(this.mUrl, 2);
        initGrsManager();
    }

    private void keepScreenOn() {
        GRSManager.a(this.mContext).e("messageCenterUrl", new GrsQueryCallback() { // from class: com.huawei.operation.activity.WebViewActivity.2
            @Override // com.huawei.hwcloudmodel.callback.GrsQueryCallback
            public void onCallBackSuccess(final String str) {
                LogUtil.c(WebViewActivity.TAG, "onCreate messageCenterHost = ", str);
                WebViewActivity.this.runOnUiThread(new Runnable() { // from class: com.huawei.operation.activity.WebViewActivity.2.1
                    @Override // java.lang.Runnable
                    public void run() {
                        if (TextUtils.isEmpty(WebViewActivity.this.mUrl)) {
                            return;
                        }
                        if (TextUtils.equals(str + BuildConfig.BREATHE_SERVICE_URL, WebViewActivity.this.mUrl)) {
                            WebViewActivity.this.getWindow().addFlags(128);
                        }
                    }
                });
            }

            @Override // com.huawei.hwcloudmodel.callback.GrsQueryCallback
            public void onCallBackFail(int i) {
                LogUtil.h(WebViewActivity.TAG, "onCallBackFail index = ", Integer.valueOf(i));
            }
        });
    }

    private void dealTitleBar() {
        if (TextUtils.isEmpty(this.mUrl)) {
            return;
        }
        if (this.mUrl.contains("vmall.com") || this.mUrl.contains(Constants.VMALL_TYPE_WITH_PREFIX)) {
            this.mCustomTitleBar.setAppBarVisible(false);
        }
        if (VmallLoginWebview.isAllowedAuth() && VmallLoginWebview.isUserChangeAccount()) {
            return;
        }
        CustomWebView.setLoginStatus(false);
    }

    private void initParam() {
        if (TextUtils.isEmpty(this.mUrl)) {
            return;
        }
        boolean contains = Uri.decode(this.mUrl).contains(Constants.INFORMATION_PLATFORM);
        CustomWebView customWebView = this.mCustomWebView;
        if (customWebView != null) {
            customWebView.setInformationFlag(contains);
        }
    }

    private void initGrsManager() {
        if (this.mHandler == null) {
            this.mHandler = new CommonWebViewActivityHandler(getMainLooper(), this);
        }
        jdx.b(new InitGrsManagerRunnable(this));
    }

    /* loaded from: classes5.dex */
    static class InitGrsManagerRunnable implements Runnable {
        private final WeakReference<WebViewActivity> weakActivity;

        InitGrsManagerRunnable(WebViewActivity webViewActivity) {
            this.weakActivity = new WeakReference<>(webViewActivity);
        }

        @Override // java.lang.Runnable
        public void run() {
            WebViewUtils.initMyTabGrsUrl(new ResultCallback() { // from class: com.huawei.operation.activity.WebViewActivity.InitGrsManagerRunnable.1
                @Override // com.huawei.operation.share.ResultCallback
                public void onResult(int i, Object obj) {
                    WebViewActivity webViewActivity = (WebViewActivity) InitGrsManagerRunnable.this.weakActivity.get();
                    if (webViewActivity != null) {
                        List list = (List) obj;
                        if (list.size() >= 4) {
                            webViewActivity.mMobileVmallHost = (String) list.get(0);
                            webViewActivity.mShopUrlStart = (String) list.get(1);
                            webViewActivity.mHealthRecommendHost = (String) list.get(2);
                            webViewActivity.mMiaoHealthHost = (String) list.get(3);
                            webViewActivity.mHandler.sendEmptyMessage(2015);
                            return;
                        }
                        LogUtil.h(WebViewActivity.TAG, "wrong size");
                    }
                }
            });
        }
    }

    private void initAppMarketUrl() {
        if (CommonUtil.as()) {
            String b = SharedPreferenceManager.b(BaseApplication.getContext(), "developeroptions", "developerswitch");
            LogUtil.a(TAG, "is beta version, developer:", b);
            if (b != null && b.equals("1")) {
                getAppMarketDevelopUrl();
                return;
            }
            this.mUrl += "/sandbox/cch5/health/appMarketBeta/dist/index.html";
            return;
        }
        if (CommonUtil.cc()) {
            LogUtil.a(TAG, "is test version");
            return;
        }
        LogUtil.a(TAG, "is release version");
        this.mUrl += "/cch5/health/appMarket/dist/index.html#/index";
    }

    private void getAppMarketDevelopUrl() {
        String contentCenterTest;
        String b = SharedPreferenceManager.b(this.mContext, "APP_MARKET", "app_market_test_url");
        String b2 = SharedPreferenceManager.b(this.mContext, "APP_MARKET", "app_market_site_key");
        if (TextUtils.isEmpty(b2)) {
            b2 = "appMarketBeta";
        }
        if ("app_market_test".equals(b)) {
            PluginOperationAdapter pluginOperationAdapter = this.mAdapter;
            if (pluginOperationAdapter == null) {
                PluginBaseAdapter adapter = PluginOperation.getInstance(this.mContext).getAdapter();
                if (adapter instanceof PluginOperationAdapter) {
                    this.mAdapter = (PluginOperationAdapter) adapter;
                }
                if (this.mAdapter == null) {
                    this.mAdapter = WebViewHelp.initAdapter();
                }
                PluginOperationAdapter pluginOperationAdapter2 = this.mAdapter;
                if (pluginOperationAdapter2 != null) {
                    contentCenterTest = pluginOperationAdapter2.getContentCenterTest();
                } else {
                    LogUtil.h(TAG, "getAppMarketDevelopUrl mAdapter is null");
                    contentCenterTest = "";
                }
            } else {
                contentCenterTest = pluginOperationAdapter.getContentCenterTest();
            }
            this.mUrl = contentCenterTest + Constants.H5_URL_BASE_PATH_TEST + b2 + "/dist/index.html";
            return;
        }
        this.mUrl += Constants.H5_URL_BASE_PATH_BETA + b2 + "/dist/index.html";
    }

    private void initWebView() {
        Intent intent = getIntent();
        if (intent != null) {
            String stringExtra = intent.getStringExtra("url");
            this.mUrl = stringExtra;
            if (TextUtils.isEmpty(stringExtra)) {
                this.mUrl = "";
            }
            enableSlowWholeDocumentDraw(this.mUrl);
        }
    }

    public void enableSlowWholeDocumentDraw(String str) {
        if (WebViewHelp.isAnnualReportUrl(str) || WebViewHelp.isGroupChallenge(str)) {
            LogUtil.a(TAG, "enableSlowWholeDocumentDraw");
            WebView.enableSlowWholeDocumentDraw();
        }
    }

    private void initErrorView() {
        LogUtil.a(TAG, "CommonUtil.isErrorWebView");
        ((HealthButton) findViewById(R.id.btn_go_setting)).setOnClickListener(new View.OnClickListener() { // from class: com.huawei.operation.activity.WebViewActivity.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LogUtil.a(WebViewActivity.TAG, "CommonUtil.uninstallApk");
                CommonUtil.p(WebViewActivity.this.mContext, "com.google.android.webview");
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        CustomTitleBar customTitleBar = (CustomTitleBar) nsy.cMc_(this, R.id.mini_shop__webview_titlebar);
        this.mCustomTitleBar = customTitleBar;
        customTitleBar.setTitleText(this.mContext.getString(R.string._2130841378_res_0x7f020f22));
    }

    private void initIntentData() {
        LogUtil.a(TAG, "initIntentData");
        Intent intent = getIntent();
        if (intent != null) {
            this.mIsGuide = intent.getBooleanExtra(Constants.IS_GUIDE, false);
            this.mType = intent.getStringExtra("type");
            this.mUrl = intent.getStringExtra("url");
            this.mIsFromHeartRateBoard = intent.getBooleanExtra(Constants.IS_START_FROM_HEART_RATE_BOARD, false);
            if (TextUtils.isEmpty(this.mUrl)) {
                this.mUrl = "";
            }
            LogUtil.a(TAG, "initIntentData mUrl");
            if ("appMarket".equals(this.mFromWhere)) {
                initAppMarketUrl();
                LogUtil.a(TAG, "appMarket mUrl :", CommonUtil.s(this.mUrl));
            }
            this.mTitle = intent.getStringExtra("title");
            this.mBiId = intent.getStringExtra("EXTRA_BI_ID");
            this.mBiName = intent.getStringExtra("EXTRA_BI_NAME");
            this.mBiSource = intent.getStringExtra("EXTRA_BI_SOURCE");
            this.mShouldBiShowTime = intent.getStringExtra("EXTRA_BI_SHOWTIME");
        }
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onNewIntent(Intent intent) {
        LogUtil.c(TAG, "onNewIntent");
        super.onNewIntent(intent);
        setIntent(intent);
        try {
            requestWindowFeature(1);
            if (this.mIsFullScreen) {
                WebViewHelp.setFullScreen(getWindow());
            }
        } catch (RuntimeException unused) {
            LogUtil.b(TAG, "Not support no title error.");
        }
        try {
            setContentView(R.layout.activity_common_web_view);
            if (!this.mIsFullScreen) {
                try {
                    getWindow().clearFlags(1024);
                } catch (RuntimeException unused2) {
                    LogUtil.b(TAG, "Not support fullscreen error.");
                }
            }
            this.mType = "";
            String stringExtra = intent.getStringExtra("url");
            this.mUrl = stringExtra;
            if (TextUtils.isEmpty(stringExtra)) {
                this.mUrl = "";
            }
            initView();
        } catch (RuntimeException unused3) {
            LogUtil.b(TAG, "onNewIntent webview error.");
            finish();
        }
    }

    private void initView() {
        LogUtil.a(TAG, "initView");
        initWebViewLayout();
        initLayout();
        initOnTouchListener();
        initLongClickImageShare();
        initData();
        initParam();
        showShoppingView();
    }

    private void initLayout() {
        this.mFrameLayout = (FrameLayout) nsy.cMc_(this, R.id.web_view_frame_layout);
        this.mRelativeLayout = (RelativeLayout) nsy.cMc_(this, R.id.web_view_relative_layout);
        CustomWebView customWebView = new CustomWebView(this.mContext, (HealthProgressBar) findViewById(R.id.load_url_progress), this.mWebView, this.mHandler, 3002);
        this.mCustomWebView = customWebView;
        customWebView.initFrameLayout(this.mContext, this.mFrameLayout);
        this.mCustomWebView.setFromWhere(this.mFromWhere);
        this.mCustomWebView.setAnnualUrl(ANNUAL_URL_CONTENT);
        this.mCustomWebView.setMobileVmallHostUrl(this.mMobileVmallHost);
        H5ProJsAdapter h5ProJsAdapter = new H5ProJsAdapter(this.mWebView, this.mRelativeLayout);
        this.mH5ProJsAdapter = h5ProJsAdapter;
        h5ProJsAdapter.mountH5ProInterfaces(this.mUrl);
        BleOperatorImpl bleOperatorImpl = new BleOperatorImpl();
        this.mBleOperator = bleOperatorImpl;
        String str = this.mProductId;
        if (str != null) {
            bleOperatorImpl.startBleState(this.mContext, str, this.mWebView, this.mHandler);
        }
        ContentValues contentValues = this.mDeviceInfo;
        if (contentValues != null) {
            this.mBleOperator.setDeviceInfo(contentValues);
        }
        this.mProgressBar = this.mCustomWebView.getProgressBar();
        setCustomTitleBar();
        this.mCustomWebView.setTextView(this.mCustomTitleBar.getViewTitle());
        if (!TextUtils.isEmpty(this.mTitle)) {
            this.mCustomWebView.setTitle(this.mTitle);
        }
        this.mReloadLayout = (RelativeLayout) nsy.cMc_(this, R.id.reload_layout);
        this.mNetworkLayout = (RelativeLayout) nsy.cMc_(this, R.id.net_work_layout);
        this.mBtnNetSetting = (HealthButton) nsy.cMc_(this, R.id.btn_no_net_work);
        this.mTipsImageView = (ImageView) nsy.cMc_(this, R.id.img_no_net_work);
        this.mNoNetworkTips = (HealthTextView) nsy.cMc_(this, R.id.tips_no_net_work);
        this.mWebViewTipsUpdate = (HealthTextView) nsy.cMc_(this, R.id.tips_web_view_update);
        this.mWebViewTipsCheckDate = (HealthTextView) nsy.cMc_(this, R.id.tips_web_view_check_date);
        this.mWebViewTipsCustomerService = (HealthTextView) nsy.cMc_(this, R.id.tips_web_view_customer_service);
        this.mShoppingLayOut = (LinearLayout) nsy.cMc_(this, R.id.shopping);
        this.mTraderOrder = (LinearLayout) nsy.cMc_(this, R.id.rl_order);
        initTitle();
    }

    private void initTitle() {
        this.mReloadLayout.setOnClickListener(this);
        this.mNetworkLayout.setOnClickListener(this);
        this.mBtnNetSetting.setOnClickListener(this);
        if (this.mIsFullScreen) {
            ((FrameLayout) nsy.cMc_(this, R.id.webview_framelayout)).setVisibility(8);
        }
        dealTitleBar();
        if (!this.mIsShowDelete || this.mAdapter == null || this.mDeviceInfo == null) {
            return;
        }
        if (this.webViewActivityExt == null) {
            this.webViewActivityExt = new WebViewActivityExt();
        }
        this.webViewActivityExt.showUnBindDeviceDialog(this.mContext, this.mDeviceInfo, this.mProductId, this.mAdapter);
    }

    private void initWebViewLayout() {
        this.mContext = this;
        setActivity(this);
        PluginBaseAdapter adapter = PluginOperation.getInstance(this.mContext).getAdapter();
        if (adapter instanceof PluginOperationAdapter) {
            this.mAdapter = (PluginOperationAdapter) adapter;
        } else {
            this.mAdapter = WebViewHelp.initAdapter();
        }
        if (this.mHandler == null) {
            this.mHandler = new CommonWebViewActivityHandler(getMainLooper(), this);
        }
        this.mWebViewLayout = (LinearLayout) nsy.cMc_(this, R.id.webview_layout);
        this.mWebView = (WebView) nsy.cMc_(this, R.id.web_view);
        if (WebViewHelp.isAnnualReportUrl(this.mUrl)) {
            this.mWebView.setLayerType(1, null);
        } else {
            this.mWebView.setLayerType(2, null);
        }
        LogUtil.a(TAG, "initView = ", Boolean.valueOf(this.mWebView.isHardwareAccelerated()));
        if (WebViewHelp.isAnnualReportUrl(this.mUrl) && Build.VERSION.SDK_INT >= 29) {
            this.mWebView.getSettings().setForceDark(0);
        } else {
            nrt.cKg_(this.mContext, this.mWebView);
        }
        if (nrt.a(this.mContext)) {
            WebSettings settings = this.mWebView.getSettings();
            if (WebViewFeature.isFeatureSupported(WebViewFeature.ALGORITHMIC_DARKENING)) {
                LogUtil.a(TAG, "initWebViewLayout: setAlgorithmicDarkeningAllowed");
                WebSettingsCompat.setAlgorithmicDarkeningAllowed(settings, true);
            }
            if (WebViewFeature.isFeatureSupported(WebViewFeature.FORCE_DARK)) {
                LogUtil.a(TAG, "initWebViewLayout: setForceDark");
                WebSettingsCompat.setForceDark(settings, 2);
            }
            if (WebViewFeature.isFeatureSupported(WebViewFeature.FORCE_DARK_STRATEGY)) {
                LogUtil.a(TAG, "initWebViewLayout: setForceDarkStrategy");
                WebSettingsCompat.setForceDarkStrategy(settings, 2);
            }
        }
    }

    private void showShoppingView() {
        View shoppingView = getShoppingView();
        this.mShoppingView = shoppingView;
        if (shoppingView == null) {
            LogUtil.h(TAG, "showShoppingView: Get shopping view failed !!");
            return;
        }
        LogUtil.a(TAG, "showShoppingView: Get shopping view success !!");
        if (this.mIsFullScreen) {
            this.mShoppingLayOut.setVisibility(8);
            LogUtil.a(TAG, "showShoppingView: mIsFullScreen is true");
        } else if (this.mWebView.getVisibility() != 0) {
            this.mShoppingLayOut.setVisibility(8);
            LogUtil.a(TAG, "showShoppingView: mWebView is NOT visible");
        } else {
            this.mShoppingLayOut.removeAllViews();
            this.mShoppingLayOut.addView(this.mShoppingView);
            this.mShoppingLayOut.setVisibility(0);
        }
    }

    private View getShoppingView() {
        if (this.mShoppingView != null) {
            cancelShoppingView();
        }
        if (TextUtils.isEmpty(this.mUrl)) {
            LogUtil.h(TAG, "getShoppingView: url is null");
            return this.mShoppingView;
        }
        Uri parse = Uri.parse(this.mUrl);
        try {
            if (!"Merchandise".equals(parse.getQueryParameter("pullCategory"))) {
                LogUtil.a(TAG, "getShoppingView: Not shopping page");
                return this.mShoppingView;
            }
            String queryParameter = parse.getQueryParameter("productId");
            String queryParameter2 = parse.getQueryParameter(WebViewHelp.BI_KEY_PULL_FROM);
            String queryParameter3 = parse.getQueryParameter("fromPageTitle");
            if (TextUtils.isEmpty(queryParameter)) {
                LogUtil.h(TAG, "getShoppingView: No product information.");
                return this.mShoppingView;
            }
            if (TextUtils.isEmpty(queryParameter)) {
                LogUtil.h(TAG, "getShoppingView：productId is null!");
                return this.mShoppingView;
            }
            TradeViewApi tradeViewApi = (TradeViewApi) Services.a("TradeService", TradeViewApi.class);
            if (tradeViewApi == null) {
                LogUtil.h(TAG, "getShoppingView：get trade feature failed !!!");
                return this.mShoppingView;
            }
            TradeViewInfo tradeViewInfo = new TradeViewInfo(queryParameter);
            tradeViewInfo.setPositionId(queryParameter2);
            tradeViewInfo.setFromPageTitle(queryParameter3);
            HashMap hashMap = new HashMap(16);
            hashMap.put(WebViewHelp.BI_KEY_PULL_FROM, parse.getQueryParameter(WebViewHelp.BI_KEY_PULL_FROM));
            hashMap.put("resourceId", parse.getQueryParameter("resourceId"));
            hashMap.put("resourceName", parse.getQueryParameter("resourceName"));
            hashMap.put("pullOrder", parse.getQueryParameter("pullOrder"));
            tradeViewInfo.setBiParams(hashMap);
            View tradeView = tradeViewApi.getTradeView(this.mContext, tradeViewInfo);
            this.mShoppingView = tradeView;
            return tradeView;
        } catch (UnsupportedOperationException unused) {
            LogUtil.b(TAG, "getShoppingView: Exception = UnsupportedOperationException");
            return this.mShoppingView;
        }
    }

    private void cancelShoppingView() {
        if (this.mShoppingView == null) {
            return;
        }
        TradeViewApi tradeViewApi = (TradeViewApi) Services.a("TradeService", TradeViewApi.class);
        if (tradeViewApi == null) {
            LogUtil.h(TAG, "cancelShoppingView：get trade feature failed !!!");
        } else {
            tradeViewApi.cancelView(this.mShoppingView);
            this.mShoppingView = null;
        }
    }

    private void initData() {
        if (!Constants.HEALTH_SHOP_TYPE.equals(this.mType) && !CommonUtil.aa(this.mContext) && !CommonUtil.bu() && !TextUtils.isEmpty(this.mUrl) && !this.mUrl.contains(ConfigConstants.BREATHE_URL)) {
            showNoNetworkPage();
        }
        boolean checkOrderManagerPage = WebViewHelp.checkOrderManagerPage(this.mUrl);
        this.mIsOrderManagerPage = checkOrderManagerPage;
        LogUtil.a(TAG, "initData mIsOrderManagerPage:", Boolean.valueOf(checkOrderManagerPage));
        if (this.mIsOrderManagerPage) {
            initTheTabsView();
        }
        initEvent();
        WebViewHelp.setBiSource(this.mUrl, this.mCustomWebView, this.mBiId, this.mBiName, this.mBiSource);
        WebViewHelp.initGlobalBiParam(this.mUrl);
        if (!Utils.o() || CommonUtil.cg()) {
            if (CommonUtil.cc()) {
                LogUtil.a(TAG, "initData test version register jssdk");
                this.mJsApiId = JsClientApi.createApi(this.mWebView, new JsClientApi.SdkOpt.Builder().setShowAuthDlg(false).build());
            } else if (!TextUtils.isEmpty(this.mUrl) && this.mUrl.startsWith("https://")) {
                LogUtil.a(TAG, "initData https protocol url register jssdk");
                this.mJsApiId = JsClientApi.createApi(this.mWebView, new JsClientApi.SdkOpt.Builder().setShowAuthDlg(false).build());
            } else if (isRightVersion()) {
                LogUtil.a(TAG, "initData TestThirdDevice or Release local security url register jssdk");
                this.mJsApiId = JsClientApi.createApi(this.mWebView, new JsClientApi.SdkOpt.Builder().setShowAuthDlg(false).build());
            } else {
                LogUtil.a(TAG, "initData not test or not third or not https protocol url register jssdk");
            }
            setJsUrl();
        }
        AndroidBugWorkaround.assistActivity(this);
        this.mUiOptions = getWindow().getDecorView().getSystemUiVisibility();
    }

    private void setJsUrl() {
        GRSManager.a(this).e("domainApiVmall", new GrsQueryCallback() { // from class: com.huawei.operation.activity.WebViewActivity.4
            @Override // com.huawei.hwcloudmodel.callback.GrsQueryCallback
            public void onCallBackSuccess(String str) {
                LogUtil.c(WebViewActivity.TAG, "initView vmallHostGrs = ", str);
                if (TextUtils.isEmpty(str)) {
                    LogUtil.b(WebViewActivity.TAG, "initData the vmallHostGrs is empty");
                    return;
                }
                JsClientApi.setJsUrl(str + WebViewActivity.VMALL_API_PATH);
            }

            @Override // com.huawei.hwcloudmodel.callback.GrsQueryCallback
            public void onCallBackFail(int i) {
                LogUtil.h(WebViewActivity.TAG, "onCallBackFail index = ", Integer.valueOf(i));
            }
        });
    }

    private boolean isRightVersion() {
        return (CommonUtil.cg() || CommonUtil.bv() || CommonUtil.as() || CommonUtil.aq()) && !this.mUrl.contains(FeedbackWebConstants.INVALID_FILE_NAME_PRE) && com.huawei.operation.utils.Utils.isMatchThirdDeviceH5UrlPrefix(this.mUrl);
    }

    private void initEvent() {
        if (this.mUrl.contains(Constants.WENJUANXING_URL_PREFIX) && !this.mUrl.contains(Constants.WENJUANXING_URL_PARAM)) {
            com.huawei.operation.utils.Utils.getWjxSurveyId(this.mUrl, this.mHandler);
        } else {
            this.mWebViewLayout.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() { // from class: com.huawei.operation.activity.WebViewActivity.5
                @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
                public void onGlobalLayout() {
                    LogUtil.a(WebViewActivity.TAG, "initEvent getViewTreeObserver():", Boolean.valueOf(WebViewActivity.this.mIsFirstShowTab));
                    if (WebViewActivity.this.mIsFirstShowTab) {
                        return;
                    }
                    WebViewActivity.this.mIsFirstShowTab = true;
                    if (WebViewActivity.this.mIsOrderManagerPage) {
                        WebViewActivity.this.mCustomTitleBar.setTitleText(WebViewActivity.this.mContext.getString(R.string._2130842077_res_0x7f0211dd));
                        WebViewActivity.this.customLeftBtnArrowType();
                        WebViewActivity.this.setTradeTabsChoose();
                    } else {
                        LogUtil.a(WebViewActivity.TAG, "initEvent onGlobalLayout() mIsFirstShowTab is true");
                        WebViewActivity.this.mCustomWebView.load(WebViewActivity.this.mUrl);
                    }
                }
            });
        }
        LogUtil.a(TAG, "initEvent mUrl");
        this.mWebView.setOnScrollChangeListener(new View.OnScrollChangeListener() { // from class: com.huawei.operation.activity.WebViewActivity.6
            @Override // android.view.View.OnScrollChangeListener
            public void onScrollChange(View view, int i, int i2, int i3, int i4) {
                if (WebViewActivity.this.mCustomWebView != null) {
                    String currentUrl = WebViewActivity.this.mCustomWebView.getCurrentUrl();
                    if (!TextUtils.isEmpty(currentUrl) && currentUrl.startsWith(WebViewActivity.this.mShopUrlStart)) {
                        if ((WebViewActivity.this.mWebView.getContentHeight() * WebViewActivity.this.mWebView.getScale()) - (WebViewActivity.this.mWebView.getHeight() + WebViewActivity.this.mWebView.getScrollY()) < 3.0f) {
                            HashMap hashMap = new HashMap(16);
                            hashMap.put("click", "1");
                            hashMap.put("title", WebViewActivity.this.mCustomTitleBar.getViewTitle().getText());
                            hashMap.put("url", WebViewActivity.this.mCustomWebView.getCurrentUrl());
                            ixx.d().d(WebViewActivity.this.mContext, AnalyticsValue.HEALTH_SHOP_BOTOOM_2120007.value(), hashMap, 0);
                        }
                    }
                    ViewScrollInstrumentation.scrollChangeOnView(view, i, i2, i3, i4);
                    return;
                }
                ViewScrollInstrumentation.scrollChangeOnView(view, i, i2, i3, i4);
            }
        });
    }

    private void initTheTabsView() {
        if (nsn.r()) {
            nsy.cMc_(this, R.id.tabs_rl_layout).setVisibility(8);
            ViewStub viewStub = (ViewStub) nsy.cMc_(this, R.id.scroll_view_view_sub);
            if (viewStub.getParent() != null) {
                View inflate = viewStub.inflate();
                this.mTabsRelativeLayout = (RelativeLayout) inflate.findViewById(R.id.tabs_rl_layout_Large);
                this.mVmallTextView = (HealthTextView) inflate.findViewById(R.id.vmall_text_view_Large);
                this.mVmallImageView = (ImageView) inflate.findViewById(R.id.vmall_image_Large);
                this.mMiaoImageView = (ImageView) inflate.findViewById(R.id.miao_image_large);
                this.mMiaoTextView = (HealthTextView) inflate.findViewById(R.id.miao_text_view_Large);
                this.mTradeTextview = (HealthTextView) inflate.findViewById(R.id.trade_text_view_Large);
                this.mTradeImageview = (ImageView) inflate.findViewById(R.id.trade_image_Large);
            }
        } else {
            RelativeLayout relativeLayout = (RelativeLayout) nsy.cMc_(this, R.id.tabs_rl_layout);
            this.mTabsRelativeLayout = relativeLayout;
            relativeLayout.setVisibility(0);
            this.mVmallTextView = (HealthTextView) nsy.cMc_(this, R.id.vmall_text_view);
            this.mVmallImageView = (ImageView) nsy.cMc_(this, R.id.vmall_image);
            this.mMiaoTextView = (HealthTextView) nsy.cMc_(this, R.id.miao_text_view);
            this.mMiaoImageView = (ImageView) nsy.cMc_(this, R.id.miao_image);
            this.mTradeTextview = (HealthTextView) nsy.cMc_(this, R.id.trade_text_view);
            this.mTradeImageview = (ImageView) nsy.cMc_(this, R.id.trade_image);
        }
        this.mVmallTextView.setOnClickListener(this);
        this.mMiaoTextView.setOnClickListener(this);
        this.mTradeTextview.setOnClickListener(this);
        addTradeView();
    }

    private void addTradeView() {
        LogUtil.a(TAG, "addTradeView");
        this.mTraderOrder.removeAllViews();
        TradeViewApi tradeViewApi = (TradeViewApi) Services.a("TradeService", TradeViewApi.class);
        if (tradeViewApi == null) {
            LogUtil.b(TAG, "getAppData : adapterHealthMgrApi is null.");
            return;
        }
        View orderVeiw = tradeViewApi.getOrderVeiw(this.mContext, 0);
        orderVeiw.setLayoutParams(new FrameLayout.LayoutParams(-1, -1));
        this.mTraderOrder.addView(orderVeiw);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setTradeTabsChoose() {
        setTheMiaoHealthChoose(false);
        setTheVmallChoose(false);
        setTheTradeText(true);
    }

    private void setTheTradeText(boolean z) {
        this.mIsHealthChoose = z;
        if (z) {
            this.mWebView.setVisibility(8);
            this.mCustomTitleBar.setTitleText(this.mContext.getString(R.string._2130842077_res_0x7f0211dd));
            this.mTradeTextview.setTextColor(getResources().getColor(R.color._2131296927_res_0x7f09029f));
            this.mTradeTextview.getPaint().setFakeBoldText(true);
            this.mTradeImageview.setVisibility(0);
            this.mTradeImageview.setBackgroundColor(getResources().getColor(R.color._2131296927_res_0x7f09029f));
            if (CommonUtil.aa(this.mContext)) {
                this.mTraderOrder.setVisibility(0);
                return;
            } else {
                LogUtil.h(TAG, "setTheTradeText net not ok");
                showNoNetworkPage();
                return;
            }
        }
        this.mWebView.setVisibility(0);
        this.mTraderOrder.setVisibility(8);
        this.mTradeTextview.setTextColor(getResources().getColor(R.color._2131299241_res_0x7f090ba9));
        this.mTradeTextview.getPaint().setFakeBoldText(false);
        this.mTradeImageview.setVisibility(4);
    }

    private void setTheTabsChoose(boolean z) {
        LogUtil.a(TAG, "setTheTabsChoose:", Boolean.valueOf(z));
        this.mIsVmallOrMiaoChoose = z;
        setTheVmallChoose(z);
        setTheMiaoHealthChoose(!z);
    }

    private void setTheVmallChoose(boolean z) {
        LogUtil.a(TAG, "setTheVmallChoose:", Boolean.valueOf(z));
        this.mIsVmallChoose = z;
        setTheVmallText(z);
        if (z) {
            setTheTradeText(false);
            String theVmallUrl = WebViewHelp.getTheVmallUrl(this.mHealthRecommendHost);
            LogUtil.a(TAG, "setTheVmallChoose get the vmall");
            if (TextUtils.isEmpty(theVmallUrl)) {
                return;
            }
            this.mCustomWebView.load(theVmallUrl);
        }
    }

    private void setTheVmallText(boolean z) {
        if (z) {
            this.mVmallTextView.setTextColor(getResources().getColor(R.color._2131296927_res_0x7f09029f));
            this.mVmallTextView.getPaint().setFakeBoldText(true);
            this.mVmallImageView.setVisibility(0);
            this.mVmallImageView.setBackgroundColor(getResources().getColor(R.color._2131296927_res_0x7f09029f));
        } else {
            this.mVmallTextView.setTextColor(getResources().getColor(R.color._2131299241_res_0x7f090ba9));
            this.mVmallTextView.getPaint().setFakeBoldText(false);
            this.mVmallImageView.setVisibility(4);
        }
        this.mVmallTextView.setClickable(!z);
    }

    private void setTheMiaoHealthChoose(boolean z) {
        LogUtil.a(TAG, "setTheMiaoHealthChoose:", Boolean.valueOf(z));
        this.mIsMiaoChoose = z;
        WebViewHelp.setTheMiaoText(z, this.mMiaoTextView, this.mMiaoImageView);
        if (z) {
            setTheTradeText(false);
            String miaoHealthUrlString = WebViewHelp.getMiaoHealthUrlString(this.mMiaoHealthHost);
            LogUtil.a(TAG, "setTheMiaoHealthChoose get the miao health");
            if (TextUtils.isEmpty(miaoHealthUrlString)) {
                return;
            }
            this.mCustomWebView.load(miaoHealthUrlString);
        }
    }

    private void setCustomTitleBar() {
        LogUtil.a(TAG, "setCustomTitleBar");
        CustomTitleBar customTitleBar = (CustomTitleBar) nsy.cMc_(this, R.id.mini_shop__webview_titlebar);
        this.mCustomTitleBar = customTitleBar;
        customTitleBar.setLeftButtonDrawable(BaseApplication.getContext().getResources().getDrawable(R.drawable._2131428439_res_0x7f0b0457), nsf.h(R.string._2130850611_res_0x7f023333));
        this.mCustomTitleBar.setLeftButtonOnClickListener(new View.OnClickListener() { // from class: com.huawei.operation.activity.WebViewActivity.7
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                WebViewActivity.this.handlePushNotificationIntent();
                LogUtil.a(WebViewActivity.TAG, "setCustomTitleBar in finish!");
                WebViewActivity.this.finish();
                ViewClickInstrumentation.clickOnView(view);
            }
        });
    }

    private void shareToThirdOrigin() {
        CustomWebView customWebView = this.mCustomWebView;
        if (customWebView == null) {
            LogUtil.b(TAG, "shareToThird mCustomWebView is null.");
            return;
        }
        String webViewTitles = customWebView.getWebViewTitles();
        if (WebViewUtils.isUnreasonableTitle(WebViewUtils.getTitleList(), webViewTitles)) {
            webViewTitles = this.mContext.getResources().getString(R.string._2130841926_res_0x7f021146);
        }
        LogUtil.a(TAG, "shareToThird title is", webViewTitles);
        String string = getString(R.string._2130842066_res_0x7f0211d2, new Object[]{webViewTitles});
        LogUtil.c(TAG, "getShareTitleContent():", webViewTitles, "getWebViewOriginalUrl():", string);
        this.mWebView.setDrawingCacheEnabled(false);
        this.mWebView.buildDrawingCache();
        Bitmap createScaledBitmap = Bitmap.createScaledBitmap(this.mWebView.getDrawingCache(), 64, 64, true);
        this.mWebView.destroyDrawingCache();
        if (createScaledBitmap == null || createScaledBitmap.getByteCount() > 32768) {
            LogUtil.a(TAG, "bitmap is null or more than max show default picture");
            createScaledBitmap = BitmapFactory.decodeResource(this.mContext.getResources(), R.mipmap._2131820756_res_0x7f1100d4);
        }
        if (!TextUtils.isEmpty(WebViewHelp.getCurrentPageUrl(this.mWebView)) && !"".equals(WebViewHelp.getCurrentPageUrl(this.mWebView))) {
            LogUtil.a(TAG, "Enter share information");
            HashMap hashMap = new HashMap(16);
            hashMap.put("id", this.mBiId);
            hashMap.put("name", this.mBiName);
            fdu fduVar = new fdu(2);
            fduVar.a(string);
            fduVar.awp_(createScaledBitmap);
            fduVar.f(WebViewHelp.getCurrentPageUrl(this.mWebView));
            fduVar.c(webViewTitles);
            fduVar.b(AnalyticsValue.HEALTH_SHARE_INFORMATION_SHARE_2100007.value());
            fduVar.b((Map<String, Object>) hashMap);
            OperationUtils.share(this.mContext, fduVar, false);
            return;
        }
        LogUtil.a(TAG, "getWebViewOriginalUrl() is null");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void shareToThird() {
        if (this.mCustomWebView == null) {
            LogUtil.b(TAG, "shareToThird mCustomWebView is null.");
            return;
        }
        if (this.mShareData.isEmpty()) {
            shareToThirdOrigin();
            return;
        }
        String str = this.mShareData.get(Constants.SHARE_TITLE);
        String str2 = this.mShareData.get(Constants.SHARE_CONTENT);
        String str3 = this.mShareData.get(Constants.SHARE_BITMAPURL);
        String currentPageUrl = WebViewHelp.getCurrentPageUrl(this.mWebView);
        if (TextUtils.isEmpty(str) && (!TextUtils.isEmpty(str2) || !TextUtils.isEmpty(str3))) {
            str = this.mCustomWebView.getWebViewTitles();
            if (WebViewUtils.isUnreasonableTitle(WebViewUtils.getTitleList(), str)) {
                str = this.mContext.getResources().getString(R.string._2130841926_res_0x7f021146);
            }
        }
        LogUtil.a(TAG, "shareToThird title is", str);
        if (!TextUtils.isEmpty(currentPageUrl) && !"".equals(currentPageUrl)) {
            LogUtil.a(TAG, "Enter share information");
            this.mCustomWebView.onShare(str3, str, str2, currentPageUrl);
        } else {
            LogUtil.a(TAG, "getCurrentPageUrl() is null");
        }
    }

    /* loaded from: classes5.dex */
    static class CommonWebViewActivityHandler extends BaseHandler<WebViewActivity> {
        CommonWebViewActivityHandler(Looper looper, WebViewActivity webViewActivity) {
            super(looper, webViewActivity);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.huawei.haf.handler.BaseHandler
        public void handleMessageWhenReferenceNotNull(WebViewActivity webViewActivity, Message message) {
            if (webViewActivity == null) {
                return;
            }
            int i = message.what;
            if (i == 2000) {
                LogUtil.a(WebViewActivity.TAG, "MSG_PROGRESSBAR_GONE");
                webViewActivity.mProgressBar.setVisibility(8);
                return;
            }
            if (i == 2005) {
                LogUtil.a(WebViewActivity.TAG, "MSG_POST_TOAST");
                Object obj = message.obj;
                if (obj instanceof String) {
                    Toast.makeText(webViewActivity.mContext, (String) obj, 0).show();
                    return;
                }
                return;
            }
            if (i != 2010) {
                if (i == 2099) {
                    LogUtil.a(WebViewActivity.TAG, "MSG_SHARE_FAIL_TOAST");
                    nrh.d(webViewActivity.mContext.getApplicationContext(), webViewActivity.mContext.getString(R.string._2130841762_res_0x7f0210a2));
                    return;
                } else if (i != 20010) {
                    webViewActivity.handleServerOrNetWorkPage(webViewActivity, message);
                    return;
                } else {
                    webViewActivity.mUrl = (String) message.obj;
                    webViewActivity.mCustomWebView.load(webViewActivity.mUrl);
                    return;
                }
            }
            LogUtil.a(WebViewActivity.TAG, "MSG_SHOW_DIALOG");
            Bundle data = message.getData();
            if (data != null) {
                WebViewHelp.showServiceTips(webViewActivity.mContext, webViewActivity.mWebView, data.getString("huid"), data.getString("serviceId"), data.getString("function"));
                return;
            }
            LogUtil.h(WebViewActivity.TAG, "handleMessageWhenReferenceNotNull MSG_SHOW_DIALOG bundle is null");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleServerOrNetWorkPage(WebViewActivity webViewActivity, Message message) {
        int i = message.what;
        if (i == 2001) {
            if (CommonUtil.aa(webViewActivity)) {
                webViewActivity.showNoServicePage();
                return;
            } else {
                webViewActivity.showNoNetworkPage();
                return;
            }
        }
        if (i == 2003) {
            webViewActivity.showNoNetworkPage();
            WebViewHelp.setRightButtonVisibility(8, this.mCustomTitleBar);
            return;
        }
        if (i != 4001) {
            switch (i) {
                case 2006:
                    LogUtil.a(TAG, "MSG_SERVER_ERROR");
                    String currentUrl = this.mCustomWebView.getCurrentUrl();
                    boolean z = WebViewUtils.isVmallMcpLoginUrl(currentUrl) || WebViewUtils.isUpLoginUrl(currentUrl);
                    if (CommonUtil.aa(webViewActivity.mContext) && z) {
                        LogUtil.a(TAG, "isVmallMcpLoginUrl or isUpLoginUrl.");
                        webViewActivity.mNetworkLayout.setVisibility(4);
                        break;
                    } else {
                        webViewActivity.showServerErrorPage();
                        break;
                    }
                    break;
                case 2007:
                    LogUtil.a(TAG, "MSG_CONNECT_TIMEOUT");
                    webViewActivity.showUnstableNetworkPage();
                    break;
                case 2008:
                    LogUtil.a(TAG, "MSG_SSL_HANDLE_ERROR");
                    webViewActivity.showSslErrorTipsPage();
                    break;
                default:
                    webViewActivity.handleTitle(webViewActivity, message);
                    break;
            }
            return;
        }
        LogUtil.a(TAG, "MAG_WEB_VIEW_LOAD");
        webViewActivity.showUnstableNetworkPage();
    }

    private void handleTitle(WebViewActivity webViewActivity, Message message) {
        int i = message.what;
        if (i == 2002) {
            LogUtil.a(TAG, "UPDATE_TITLE");
            Object obj = message.obj;
            if (obj instanceof String) {
                String str = (String) obj;
                LogUtil.a(TAG, "handleTitle title=", str);
                if (str.contains(Constants.ABOUT_BLANK) && !CommonUtil.aa(this.mContext)) {
                    str = getString(R.string._2130842061_res_0x7f0211cd);
                }
                if (Constants.NULL.equals(str)) {
                    str = "";
                }
                webViewActivity.mCustomTitleBar.setTitleText(str);
                return;
            }
            return;
        }
        if (i == 2012) {
            LogUtil.a(TAG, "MSG_HANDLE_WEB_VIEW_TITLE");
            webViewActivity.handleWebViewTitle((TitleBean) message.obj);
            return;
        }
        if (i == 2014) {
            LogUtil.a(TAG, "CustomWebView.MSG_QUIT_ACT");
            webViewActivity.customTitleBarRightQuitAct();
            return;
        }
        if (i == 2030) {
            LogUtil.a(TAG, "CustomWebView.MSG_SHOW_SHARE");
            webViewActivity.customTitleBarRightShare();
            if (message.getData() != null) {
                this.mActivityId = message.getData().getString("activityId");
                this.mActivityShareType = message.getData().getString(Constants.ACTIVITY_SHARE_TYPE);
                return;
            }
            return;
        }
        if (i == 2031) {
            LogUtil.a(TAG, "CustomWebView.MSG_SHOW_ACTIVITY_RESULT_NOTE");
            if (message.arg1 == 1) {
                webViewActivity.showNoteBtn();
                return;
            }
            return;
        }
        webViewActivity.handleTitleSec(webViewActivity, message);
    }

    private void handleTitleSec(WebViewActivity webViewActivity, Message message) {
        int i = message.what;
        if (i == 2009) {
            Object obj = message.obj;
            LogUtil.a(TAG, "MSG_SHOW_MY_ACTIVITY isShow = ", obj);
            if (obj instanceof Boolean) {
                boolean booleanValue = ((Boolean) obj).booleanValue();
                this.mIsShowTitleRightDeleteDevice = booleanValue;
                webViewActivity.showRightTextBtnMyActivity(booleanValue);
                return;
            }
            return;
        }
        if (i == 2017) {
            LogUtil.a(TAG, "CustomWebView.MSG_VMALL_COUPON");
            webViewActivity.customTitleBarRightVmallCoupon();
        } else if (i == 2020) {
            LogUtil.a(TAG, "CustomWebView.MSG_RIGHT_BTN_INVISIBLE");
            webViewActivity.setRightBtnInvisible();
        } else {
            webViewActivity.handleStartActivity(webViewActivity, message);
        }
    }

    private void handleSetLeftBtnArrowType(WebViewActivity webViewActivity, Message message) {
        int i = message.what;
        if (i == 20023) {
            LogUtil.a(TAG, "LEFT_BTN_ARROW_TYPE.");
            customLeftBtnArrowType();
        } else if (i == 20025) {
            LogUtil.a(TAG, "NEXT_H5_PAGE_LEFT_BTN_ARROW_TYPE.");
            this.mIsNextPageLeftBtnArrowType = true;
        } else {
            webViewActivity.handleSetShareConfigData(webViewActivity, message);
        }
    }

    private void handleSetShareConfigData(WebViewActivity webViewActivity, Message message) {
        if (message.what == 20024) {
            LogUtil.a(TAG, "SET_SHARE_DATA.");
            Bundle data = message.getData();
            if (data != null) {
                String string = data.getString(Constants.SHARE_BITMAPURL);
                String string2 = data.getString(Constants.SHARE_TITLE);
                String string3 = data.getString(Constants.SHARE_CONTENT);
                String string4 = data.getString(Constants.SHARE_URL);
                if (this.mShareData == null) {
                    this.mShareData = new HashMap();
                }
                if (!TextUtils.isEmpty(string) || !TextUtils.isEmpty(string2) || !TextUtils.isEmpty(string3)) {
                    this.mShareData.put(Constants.SHARE_BITMAPURL, string);
                    this.mShareData.put(Constants.SHARE_TITLE, string2);
                    this.mShareData.put(Constants.SHARE_CONTENT, string3);
                    this.mShareData.put(Constants.SHARE_URL, string4);
                    return;
                }
                this.mShareData.clear();
                return;
            }
            return;
        }
        WebViewHelp.handleSetFullscreen(this.mContext, this.mWebView, getWindow(), webViewActivity, message);
    }

    private void handleStartActivity(WebViewActivity webViewActivity, Message message) {
        int i = message.what;
        if (i == 2004) {
            LogUtil.a(TAG, "MSG_START_MINI_SHOP_WEB_PAGE");
            Bundle data = message.getData();
            if (data != null) {
                String string = data.getString("url");
                String string2 = data.getString("type");
                Intent intent = new Intent(webViewActivity.mContext, (Class<?>) WebViewActivity.class);
                intent.putExtra("url", string);
                intent.putExtra("type", string2);
                webViewActivity.mContext.startActivity(intent);
                return;
            }
            LogUtil.h(TAG, "handleStartActivity MSG_START_MINI_SHOP_WEB_PAGE bundle is null");
            return;
        }
        if (i != 2018) {
            if (i == 20253) {
                LogUtil.a(TAG, "Constants.comeback");
                runOnUiThread(new Runnable() { // from class: com.huawei.operation.activity.WebViewActivity.8
                    @Override // java.lang.Runnable
                    public void run() {
                        LogUtil.a(WebViewActivity.TAG, "Constants.comeback11");
                        WebViewActivity.this.mCustomWebView.goBack();
                    }
                });
                return;
            } else {
                webViewActivity.handleLoadPage(webViewActivity, message);
                return;
            }
        }
        if (webViewActivity.mContext != null) {
            try {
                Intent intent2 = new Intent();
                intent2.setPackage(Constants.APP_PACKAGE);
                intent2.setClassName(Constants.APP_PACKAGE, Constants.APP_SETTING_ACTIVITY);
                webViewActivity.mContext.startActivity(intent2);
            } catch (ActivityNotFoundException unused) {
                LogUtil.a(TAG, "activity not found exception.");
            }
        }
    }

    private void handleLoadPage(WebViewActivity webViewActivity, Message message) {
        int i = message.what;
        if (i == 1) {
            LogUtil.a(TAG, "RESET_ACTIVITY_LIST");
            String activityKeyUrlSp = WebViewUtils.getActivityKeyUrlSp(webViewActivity.mContext);
            ActivityHtmlPathApi activityHtmlPathApi = (ActivityHtmlPathApi) Services.a("PluginOperation", ActivityHtmlPathApi.class);
            if (activityHtmlPathApi != null) {
                webViewActivity.mCustomWebView.load(activityKeyUrlSp + activityHtmlPathApi.getActivityHtmlPath() + Constants.ACTIVITY_HTML);
                return;
            }
            return;
        }
        if (i == 2011) {
            LogUtil.a(TAG, "MSG_GET_SPORT_DATA");
            Bundle data = message.getData();
            if (data != null) {
                webViewActivity.callJsSportDataFunction(data.getString(Constants.JS_PARAM), data.getString(Constants.JS_FUNCTION_RES), data.getString(Constants.JS_FUNCTION_ERR), data.getBoolean(Constants.IS_LEGAL));
                return;
            } else {
                LogUtil.h(TAG, "handleStartActivity MSG_GET_SPORT_DATA bundle is null");
                return;
            }
        }
        if (i == 2015) {
            LogUtil.a(TAG, "CustomWebView.MSG_GRS_GET_URL");
            dealMsgGrsGetUrl(webViewActivity);
            return;
        }
        if (i == 2016) {
            LogUtil.a(TAG, "MSG_START_WEB_PAGE");
            Bundle data2 = message.getData();
            if (data2 != null) {
                webViewActivity.startLoadUrl(data2.getString("url"));
                return;
            } else {
                LogUtil.h(TAG, "handleStartActivity MSG_START_WEB_PAGE bundle is null");
                return;
            }
        }
        if (i != 10085) {
            if (i == 10086) {
                LogUtil.a(TAG, "ON_PAGE_FINISH");
                dealOnPageFinish(webViewActivity, message);
                return;
            } else {
                webViewActivity.handleJsService(webViewActivity, message);
                return;
            }
        }
        LogUtil.a(TAG, "ON_PAGE_STARTED");
        if (message.obj instanceof String) {
            String str = (String) message.obj;
            boolean z = (TextUtils.isEmpty(str) || webViewActivity.isResetToOrderManager(str) || !str.contains("vmall.com")) ? false : true;
            LogUtil.a(TAG, "ON_PAGE_STARTED: isVmallUrl -> " + z);
            if (z) {
                webViewActivity.setTheTabsView(false);
                webViewActivity.mCustomTitleBar.setAppBarVisible(false);
            }
        }
    }

    private void dealMsgGrsGetUrl(WebViewActivity webViewActivity) {
        webViewActivity.initWebView();
        if (CommonUtil.w(webViewActivity.mContext.getApplicationContext()) && !Utils.o()) {
            webViewActivity.setContentView(R.layout.fragment_web_view_error_1);
            webViewActivity.initErrorView();
            return;
        }
        try {
            requestWindowFeature(1);
            if (this.mIsFullScreen) {
                WebViewHelp.setFullScreen(getWindow());
            }
        } catch (RuntimeException unused) {
            LogUtil.b(TAG, "Not support no title error.");
        }
        try {
            setContentView(R.layout.activity_common_web_view);
            if (!this.mIsFullScreen) {
                try {
                    getWindow().clearFlags(1024);
                } catch (RuntimeException unused2) {
                    LogUtil.b(TAG, "Not support fullscreen error.");
                }
            }
            webViewActivity.initIntentData();
            webViewActivity.initView();
        } catch (RuntimeException unused3) {
            LogUtil.b(TAG, "handleMessage webview error.");
            finish();
        }
    }

    private void dealOnPageFinish(WebViewActivity webViewActivity, Message message) {
        putBiEventLoadPage(this.mUrl, 3);
        Object obj = message.obj;
        if (obj instanceof String) {
            String str = (String) obj;
            if (CommonUtil.aa(webViewActivity.mContext)) {
                if (WebViewUtils.isVmallMcpLoginUrl(str) || WebViewUtils.isUpLoginUrl(str)) {
                    webViewActivity.mWebView.setVisibility(4);
                    webViewActivity.mNetworkLayout.setVisibility(4);
                    return;
                } else if (this.mNetworkLayout.getVisibility() != 0) {
                    webViewActivity.mWebView.setVisibility(0);
                }
            }
            boolean isResetToOrderManager = webViewActivity.isResetToOrderManager(str);
            boolean z = true;
            boolean z2 = webViewActivity.isOrderSignPage(str) && sIsOrderPrevious;
            LogUtil.a(TAG, "isOrderArgSign:", Boolean.valueOf(z2));
            if (z2) {
                WebView webView = webViewActivity.mWebView;
                String url = WebViewUtils.getUrl("sessionStorage.clear()");
                webView.loadUrl(url);
                WebViewInstrumentation.loadUrl(webView, url);
            }
            if (!isResetToOrderManager && !z2) {
                z = false;
            }
            LogUtil.a(TAG, "isResetToOrderManager isOrder:", Boolean.valueOf(z));
            webViewActivity.setTheTabsView(z);
            sIsOrderPrevious = isResetToOrderManager;
        }
        if (this.mIsNextPageLeftBtnArrowType) {
            customLeftBtnArrowType();
            return;
        }
        this.mIsNextPageLeftBtnArrowType = false;
        if (BleConstants.BLE_THIRD_DEVICE_H5.equals(this.mBleIntroductionType)) {
            customLeftBtnArrowType();
            PluginOperationAdapter pluginOperationAdapter = this.mAdapter;
            if (pluginOperationAdapter != null && this.mIsFirstShow && pluginOperationAdapter.isRopeDevice(this.mProductId)) {
                this.mIsFirstShow = false;
                this.mAdapter.getUserInfo(new CheckUserWeightCallback(this));
            }
        }
    }

    private void handleJsService(WebViewActivity webViewActivity, Message message) {
        if (message.what == 2013) {
            Bundle data = message.getData();
            if (data != null) {
                JsCommandOption jsCommandOption = new JsCommandOption();
                jsCommandOption.setServiceType(data.getString(Constants.JS_SERVICE_TYPE));
                jsCommandOption.setFuncType(data.getString(Constants.JS_FUNC_TYPE));
                jsCommandOption.setParam(data.getString(Constants.JS_PARAM));
                jsCommandOption.setFunctionRes(data.getString(Constants.JS_FUNCTION_RES));
                jsCommandOption.setLegal(data.getBoolean(Constants.IS_LEGAL));
                JsCommand produceRequest = JsOperationProducer.produceRequest(jsCommandOption.getServiceType());
                if (produceRequest == null) {
                    LogUtil.b(TAG, "MSG_JS_SERVICE error serviceType,it is ", jsCommandOption.getServiceType());
                    return;
                } else {
                    produceRequest.execute(webViewActivity, webViewActivity.mAdapter, jsCommandOption);
                    return;
                }
            }
            LogUtil.h(TAG, "handleJsService MSG_JS_SERVICE bundle is null");
            return;
        }
        webViewActivity.handleSetLeftBtnArrowType(webViewActivity, message);
    }

    private boolean isResetToOrderManager(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        if (CommonUtil.cc()) {
            LogUtil.a(TAG, "isResetToOrderManager isTestVersion");
            if (!TextUtils.equals(this.mHealthRecommendHost + UriConstants.VMALL_URL_STRING, str)) {
                if (!TextUtils.equals(this.mMiaoHealthHost + UriConstants.MIAO_HEALTH_URL_TEST, str)) {
                    return false;
                }
            }
            return true;
        }
        if (CommonUtil.as()) {
            LogUtil.a(TAG, "isResetToOrderManager is beta version");
            if (!(this.mHealthRecommendHost + UriConstants.VMALL_URL_STRING).equals(str)) {
                if (!(this.mMiaoHealthHost + UriConstants.MIAO_HEALTH_URL_BETA).equals(str)) {
                    return false;
                }
            }
            return true;
        }
        LogUtil.a(TAG, "isResetToOrderManager is other version");
        if (!TextUtils.equals(this.mHealthRecommendHost + UriConstants.VMALL_URL_STRING, str)) {
            if (!TextUtils.equals(this.mMiaoHealthHost + UriConstants.MIAO_HEALTH_URL, str)) {
                return false;
            }
        }
        return true;
    }

    private boolean isOrderSignPage(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        if (CommonUtil.cc()) {
            return str.startsWith(this.mHealthRecommendHost + "/healthMallPlat/vmall/index.html#/agrSign");
        }
        return str.startsWith(this.mHealthRecommendHost + "/healthMallPlat/vmall/index.html#/agrSign");
    }

    private void setTheTabsView(boolean z) {
        if (this.mTabsRelativeLayout == null) {
            if (!z) {
                if (nsn.r()) {
                    ViewStub viewStub = (ViewStub) nsy.cMc_(this, R.id.scroll_view_view_sub);
                    if (viewStub.getParent() != null) {
                        this.mTabsRelativeLayout = (RelativeLayout) viewStub.inflate().findViewById(R.id.tabs_rl_layout_Large);
                    }
                } else {
                    this.mTabsRelativeLayout = (RelativeLayout) nsy.cMc_(this, R.id.tabs_rl_layout);
                }
            } else {
                initTheTabsView();
            }
        }
        LogUtil.a(TAG, "isResetToOrderManager isShowTab:", Boolean.valueOf(z));
        if (z) {
            this.mTabsRelativeLayout.setVisibility(0);
            setTheVmallText(this.mIsVmallOrMiaoChoose);
            WebViewHelp.setTheMiaoText(!this.mIsVmallOrMiaoChoose, this.mMiaoTextView, this.mMiaoImageView);
            return;
        }
        this.mTabsRelativeLayout.setVisibility(8);
    }

    private void handleWebViewTitle(TitleBean titleBean) {
        if (this.mIsFullScreen) {
            this.mCustomTitleBar.setAppBarVisible(false);
        } else if (titleBean == null) {
            LogUtil.c(TAG, "titleBean is null");
            if (this.mIsShowTitleRightDeleteDevice) {
                LogUtil.a(TAG, "mIsShowTitleRightDeleteDevice = true");
                return;
            }
            defaultHandleTitle();
        } else if (!titleBean.fetchIsShowAppBar()) {
            this.mCustomTitleBar.setAppBarVisible(false);
        } else {
            this.mCustomTitleBar.setAppBarVisible(true);
            handleCustomTitleBarLeftBtn(titleBean);
            handleCustomTitleBarRightBtn(titleBean);
        }
        if (TextUtils.equals("appMarket", this.mFromWhere)) {
            customLeftBtnArrowType();
        }
    }

    private void handleCustomTitleBarLeftBtn(TitleBean titleBean) {
        if (titleBean == null) {
            return;
        }
        if (TitleBean.LEFT_BTN_TYPE_ARROW.equals(titleBean.fetchGetLeftBtn())) {
            customLeftBtnArrowType();
        } else {
            customLeftBtnCloseType();
        }
    }

    private void handleCustomTitleBarRightBtn(TitleBean titleBean) {
        if (titleBean != null) {
            if (titleBean.fetchGetFeatureUrl() == null || !titleBean.fetchGetFeatureUrl().contains(ConfigConstants.HW_HEALTH_SHOP_URL)) {
                String fetchGetRightBtn = titleBean.fetchGetRightBtn();
                LogUtil.a(TAG, "rightBtnType = ", fetchGetRightBtn);
                if (TitleBean.RIGHT_BTN_TYPE_MORE.equals(fetchGetRightBtn)) {
                    customTitleBarRightMoreType(titleBean);
                    return;
                }
                if (TitleBean.RIGHT_BTN_TYPE_IS_MY_ACTIVITY.equals(fetchGetRightBtn)) {
                    showRightTextBtnMyActivity(true);
                    return;
                }
                if (TitleBean.RIGHT_BTN_TYPE_SHARE.equals(fetchGetRightBtn)) {
                    WebViewHelp.setRightButtonVisibility(0, this.mCustomTitleBar);
                    this.mCustomTitleBar.setRightButtonDrawable(BaseApplication.getContext().getResources().getDrawable(R.drawable._2131428444_res_0x7f0b045c), nsf.h(R.string._2130850657_res_0x7f023361));
                    View rightIconImage = this.mCustomTitleBar.getRightIconImage();
                    if (rightIconImage != null) {
                        View findViewById = rightIconImage.findViewById(R.id.hwappbarpattern_ok_icon);
                        if (findViewById != null) {
                            findViewById.setContentDescription(this.mContext.getString(R.string._2130851150_res_0x7f02354e));
                        }
                        rightIconImage.setVisibility(0);
                    }
                    this.mCustomTitleBar.setRightButtonOnClickListener(nkx.cwZ_(new View.OnClickListener() { // from class: com.huawei.operation.activity.WebViewActivity.9
                        @Override // android.view.View.OnClickListener
                        public void onClick(View view) {
                            LogUtil.a(WebViewActivity.TAG, "onClick share information");
                            WebViewActivity.this.shareToThird();
                            ViewClickInstrumentation.clickOnView(view);
                        }
                    }, (BaseActivity) this.mContext, true, ""));
                    return;
                }
                if (this.mIsSetting && "appMarket".equals(this.mFromWhere)) {
                    if (!TextUtils.isEmpty(this.mAppMarketType) && "smart_type".equals(this.mAppMarketType)) {
                        WebViewHelp.setRightButtonVisibility(8, this.mCustomTitleBar);
                        return;
                    } else {
                        WebViewHelp.setRightButtonVisibility(0, this.mCustomTitleBar);
                        return;
                    }
                }
                WebViewHelp.setRightButtonVisibility(8, this.mCustomTitleBar);
                this.mCustomTitleBar.setRightSoftkeyVisibility(8);
            }
        }
    }

    private void setShareRightDrawable() {
        View findViewById;
        if (LanguageUtil.bc(BaseApplication.getContext())) {
            this.mCustomTitleBar.setRightSoftkeyBackground(nrz.cKn_(BaseApplication.getContext(), R$drawable.ic_health_nav_share_black), nsf.h(R.string._2130850657_res_0x7f023361));
        } else {
            this.mCustomTitleBar.setRightSoftkeyBackground(ContextCompat.getDrawable(BaseApplication.getContext(), R$drawable.ic_health_nav_share_black), nsf.h(R.string._2130850657_res_0x7f023361));
        }
        View rightSoftKey = this.mCustomTitleBar.getRightSoftKey();
        if (rightSoftKey == null || (findViewById = rightSoftKey.findViewById(R.id.hwappbarpattern_menu_icon)) == null) {
            return;
        }
        findViewById.setContentDescription(this.mContext.getString(R.string._2130851150_res_0x7f02354e));
    }

    private void customTitleBarRightQuitAct() {
        LogUtil.a(TAG, "customTitleBarRightQuitAct");
        WebViewHelp.setRightButtonVisibility(0, this.mCustomTitleBar);
        this.mCustomTitleBar.setRightButtonDrawable(BaseApplication.getContext().getResources().getDrawable(R.drawable._2131428689_res_0x7f0b0551), nsf.h(R.string._2130850635_res_0x7f02334b));
        this.mCustomTitleBar.setRightButtonOnClickListener(new View.OnClickListener() { // from class: com.huawei.operation.activity.WebViewActivity.10
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LogUtil.a(WebViewActivity.TAG, "customTitleBarRightQuitAct onClick");
                WebViewActivity.this.handleMoreQuitActPopWindow();
                ViewClickInstrumentation.clickOnView(view);
            }
        });
    }

    private void customTitleBarRightShare() {
        LogUtil.a(TAG, "show customTitleBarRightShare");
        this.mCustomTitleBar.setRightSoftkeyVisibility(0);
        setShareRightDrawable();
        this.mCustomTitleBar.setRightSoftkeyOnClickListener(nkx.cwZ_(new View.OnClickListener() { // from class: com.huawei.operation.activity.WebViewActivity.11
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LogUtil.a(WebViewActivity.TAG, "onClick share information");
                if (TextUtils.isEmpty(WebViewActivity.this.mActivityId) || TextUtils.isEmpty(WebViewActivity.this.mActivityShareType)) {
                    if (WebViewActivity.this.mCustomWebView != null && (WebViewActivity.this.mContext instanceof WebViewActivity)) {
                        JsRegisterFunctionResUtil.callBackResStatus((WebViewActivity) WebViewActivity.this.mContext, WebViewActivity.this.mCustomWebView.getmRegisterActivityShareFunctionRes(), "");
                    } else {
                        LogUtil.h(WebViewActivity.TAG, "showShareDialog mCustomWebView is null");
                    }
                } else {
                    WebViewActivity.this.mCustomWebView.onShare(WebViewActivity.this.mActivityId, WebViewActivity.this.mActivityShareType);
                }
                ViewClickInstrumentation.clickOnView(view);
            }
        }, (BaseActivity) this.mContext, true, ""));
    }

    private void showNoteBtn() {
        LogUtil.a(TAG, "showNoteBtn");
        WebViewHelp.setRightButtonVisibility(0, this.mCustomTitleBar);
        this.mCustomTitleBar.setRightButtonDrawable(getResources().getDrawable(R.mipmap._2131821482_res_0x7f1103aa), nsf.h(R.string._2130845073_res_0x7f021d91));
        this.mCustomTitleBar.setRightTextButtonClickable(true);
        this.mCustomTitleBar.setRightButtonOnClickListener(new View.OnClickListener() { // from class: com.huawei.operation.activity.WebViewActivity.12
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LogUtil.a(WebViewActivity.TAG, "showNoteBtn onClick");
                if (WebViewActivity.this.webViewActivityExt == null) {
                    WebViewActivity.this.webViewActivityExt = new WebViewActivityExt();
                }
                WebViewActivity.this.webViewActivityExt.showActivityResultExplainDialog(WebViewActivity.this.mContext);
                ViewClickInstrumentation.clickOnView(view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleMoreQuitActPopWindow() {
        View inflate = View.inflate(this.mContext, R.layout.common_pop_menu_single, null);
        this.mPopView = new nqc(this.mContext, inflate);
        HealthTextView healthTextView = (HealthTextView) inflate.findViewById(R.id.text);
        healthTextView.setText(R.string._2130842283_res_0x7f0212ab);
        healthTextView.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.operation.activity.WebViewActivity.13
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                WebViewHelp.showQuitDialog(WebViewActivity.this.mContext, WebViewActivity.this.mCustomWebView);
                WebViewActivity.this.mPopView.b();
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        this.mPopView.cEh_(this.mCustomTitleBar, 17);
    }

    private void setRightBtnInvisible() {
        LogUtil.a(TAG, "setRightBtnInvisible");
        WebViewHelp.setRightButtonVisibility(8, this.mCustomTitleBar);
        this.mCustomTitleBar.setRightSoftkeyVisibility(8);
    }

    private void customTitleBarRightVmallCoupon() {
        LogUtil.a(TAG, "customTitleBarRightVmallCoupon");
        WebViewHelp.setRightButtonVisibility(0, this.mCustomTitleBar);
        this.mCustomTitleBar.setRightButtonDrawable(BaseApplication.getContext().getResources().getDrawable(R.drawable._2131428688_res_0x7f0b0550), nsf.h(R.string._2130845022_res_0x7f021d5e));
        this.mCustomTitleBar.setRightButtonOnClickListener(new View.OnClickListener() { // from class: com.huawei.operation.activity.WebViewActivity.14
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LogUtil.a(WebViewActivity.TAG, "customTitleBarRightVmallCoupon onClick");
                if (WebViewActivity.this.mCustomWebView != null && (WebViewActivity.this.mContext instanceof WebViewActivity)) {
                    JsRegisterFunctionResUtil.callBackResStatus((WebViewActivity) WebViewActivity.this.mContext, WebViewActivity.this.mCustomWebView.getRegisterVmallCouponFunctionRes(), "2");
                } else {
                    LogUtil.a(WebViewActivity.TAG, "customTitleBarRightVmallCoupon mCustomWebView is null");
                }
                ViewClickInstrumentation.clickOnView(view);
            }
        });
    }

    private void customTitleBarRightMoreType(final TitleBean titleBean) {
        if (TextUtils.isEmpty(titleBean.fetchGetFeatureUrl()) && TextUtils.isEmpty(titleBean.fetchGetShoppingCartUrl()) && TextUtils.isEmpty(titleBean.fetchGetOrderManagerUrl())) {
            WebViewHelp.setRightButtonVisibility(8, this.mCustomTitleBar);
        } else {
            WebViewHelp.setRightButtonVisibility(0, this.mCustomTitleBar);
        }
        this.mCustomTitleBar.setRightButtonDrawable(BaseApplication.getContext().getResources().getDrawable(R.drawable._2131428689_res_0x7f0b0551), nsf.h(R.string._2130850635_res_0x7f02334b));
        this.mCustomTitleBar.setRightButtonOnClickListener(new View.OnClickListener() { // from class: com.huawei.operation.activity.WebViewActivity.15
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                WebViewHelp.setClickBi(WebViewActivity.this.mContext, AnalyticsValue.HEALTH_SHOP_WEBVIEW_MORE_CLICK_2120016.value());
                WebViewActivity webViewActivity = WebViewActivity.this;
                webViewActivity.mPopView = WebViewHelp.handleMorePopWindow(webViewActivity.mContext, WebViewActivity.this.mCustomWebView, titleBean);
                WebViewActivity.this.mPopView.cEh_(WebViewActivity.this.mCustomTitleBar, 17);
                ViewClickInstrumentation.clickOnView(view);
            }
        });
    }

    private void defaultHandleTitle() {
        LogUtil.a(TAG, "defaultHandleTitle");
        customLeftBtnCloseType();
        WebViewHelp.setRightButtonVisibility(8, this.mCustomTitleBar);
    }

    private void customLeftBtnCloseType() {
        this.mCustomTitleBar.setLeftButtonDrawable(BaseApplication.getContext().getResources().getDrawable(R.drawable._2131428439_res_0x7f0b0457), nsf.h(R.string._2130850611_res_0x7f023333));
        this.mCustomTitleBar.setLeftButtonOnClickListener(new View.OnClickListener() { // from class: com.huawei.operation.activity.WebViewActivity.16
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (WebViewActivity.this.mCustomWebView != null && WebViewActivity.this.mWebView != null) {
                    LogUtil.c(WebViewActivity.TAG, "Constants.WEB_VIEW_STATUS_CLOSE");
                    WebViewActivity.this.mCustomWebView.callbackWebViewStatus(Constants.WEBVIEW_STATUS_CLOSE);
                }
                WebViewActivity.this.handlePushNotificationIntent();
                LogUtil.a(WebViewActivity.TAG, "setCustomTitleBar in finish");
                WebViewActivity.this.finish();
                ViewClickInstrumentation.clickOnView(view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void customLeftBtnArrowType() {
        if (LanguageUtil.bc(this.mContext)) {
            this.mCustomTitleBar.setLeftButtonDrawable(BaseApplication.getContext().getResources().getDrawable(R.drawable._2131428443_res_0x7f0b045b), nsf.h(R.string._2130850617_res_0x7f023339));
        } else {
            this.mCustomTitleBar.setLeftButtonDrawable(BaseApplication.getContext().getResources().getDrawable(R.drawable._2131428438_res_0x7f0b0456), nsf.h(R.string._2130850617_res_0x7f023339));
        }
        this.mCustomTitleBar.setLeftButtonOnClickListener(new View.OnClickListener() { // from class: com.huawei.operation.activity.WebViewActivity.17
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (WebViewActivity.this.mAdapter != null) {
                    if (WebViewActivity.this.mCustomWebView != null && WebViewActivity.this.mWebView != null) {
                        LogUtil.c(WebViewActivity.TAG, "Constants.WEB_VIEW_STATUS_LEFT_ARROW");
                        WebViewActivity.this.mCustomWebView.callbackWebViewStatus(Constants.WEBVIEW_STATUS_LEFT_ARROW);
                    }
                    WebViewActivity.this.handlePushNotificationIntent();
                    if (!WebViewActivity.this.mCustomWebView.goBack()) {
                        LogUtil.a(WebViewActivity.TAG, "setCustomTitleBar in finish");
                        WebViewActivity.this.finish();
                    }
                }
                ViewClickInstrumentation.clickOnView(view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handlePushNotificationIntent() {
        SharedPreferences sharedPreferences = getSharedPreferences("pushNotificationIntent", 0);
        if (sharedPreferences.getBoolean(Constants.KEY_IS_PUSH_NOTIFICATION, false)) {
            LogUtil.a(TAG, "setCustomTitleBar isPush in if");
            SharedPreferences.Editor edit = sharedPreferences.edit();
            edit.putBoolean(Constants.KEY_IS_PUSH_NOTIFICATION, false);
            edit.apply();
            WebViewHelp.jumpToMain(this.mContext, getPackageManager());
        } else {
            LogUtil.a(TAG, "setCustomTitleBar isPush in else");
            setResult(-1);
        }
        if (isTaskRoot()) {
            LogUtil.a(TAG, "setCustomTitleBar is TaskRoot,jump to main");
            WebViewHelp.jumpToMain(this.mContext, getPackageManager());
        }
    }

    private void callJsSportDataFunction(String str, String str2, String str3, boolean z) {
        if (!z) {
            WebView webView = this.mWebView;
            String url = WebViewUtils.getUrl(str3, String.valueOf(1003));
            webView.loadUrl(url);
            WebViewInstrumentation.loadUrl(webView, url);
            WebView webView2 = this.mWebView;
            String url2 = WebViewUtils.getUrl(str2, Constants.NULL);
            webView2.loadUrl(url2);
            WebViewInstrumentation.loadUrl(webView2, url2);
            return;
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            String string = jSONObject.getString(Constants.START_DATE);
            String string2 = jSONObject.getString("endDate");
            this.mAdapter.getSportData(string, string2, new SportDataResponseCallback(this, str2, str3));
        } catch (JSONException unused) {
            LogUtil.b(TAG, "parse param json fail!");
            WebView webView3 = this.mWebView;
            String url3 = WebViewUtils.getUrl(str3, String.valueOf(1001));
            webView3.loadUrl(url3);
            WebViewInstrumentation.loadUrl(webView3, url3);
            WebView webView4 = this.mWebView;
            String url4 = WebViewUtils.getUrl(str2, Constants.NULL);
            webView4.loadUrl(url4);
            WebViewInstrumentation.loadUrl(webView4, url4);
        }
    }

    /* loaded from: classes5.dex */
    static class SportDataResponseCallback implements IBaseResponseCallback {
        private String mFunctionErr;
        private String mFunctionRes;
        private WeakReference<WebViewActivity> mWebViewActivity;

        SportDataResponseCallback(WebViewActivity webViewActivity, String str, String str2) {
            this.mWebViewActivity = new WeakReference<>(webViewActivity);
            this.mFunctionRes = str;
            this.mFunctionErr = str2;
        }

        @Override // com.huawei.hwbasemgr.IBaseResponseCallback
        /* renamed from: onResponse */
        public void d(final int i, final Object obj) {
            WebViewActivity webViewActivity = this.mWebViewActivity.get();
            if (webViewActivity != null && webViewActivity.mWebView != null) {
                final WebView webView = webViewActivity.mWebView;
                if (i == 1001) {
                    String url = WebViewUtils.getUrl(this.mFunctionErr, String.valueOf(i));
                    webView.loadUrl(url);
                    WebViewInstrumentation.loadUrl(webView, url);
                    String url2 = WebViewUtils.getUrl(this.mFunctionRes, Constants.NULL);
                    webView.loadUrl(url2);
                    WebViewInstrumentation.loadUrl(webView, url2);
                    return;
                }
                if (i == 1999) {
                    String url3 = WebViewUtils.getUrl(this.mFunctionErr, String.valueOf(i));
                    webView.loadUrl(url3);
                    WebViewInstrumentation.loadUrl(webView, url3);
                    String url4 = WebViewUtils.getUrl(this.mFunctionRes, Constants.NULL);
                    webView.loadUrl(url4);
                    WebViewInstrumentation.loadUrl(webView, url4);
                    return;
                }
                LogUtil.c(WebViewActivity.TAG, "getSportData onResponse not illegal or not unknown error");
                webViewActivity.runOnUiThread(new Runnable() { // from class: com.huawei.operation.activity.WebViewActivity$SportDataResponseCallback$$ExternalSyntheticLambda0
                    @Override // java.lang.Runnable
                    public final void run() {
                        WebViewActivity.SportDataResponseCallback.this.m680x110df60f(webView, obj, i);
                    }
                });
                return;
            }
            LogUtil.h(WebViewActivity.TAG, "activity or mWebView is null");
        }

        /* renamed from: lambda$onResponse$0$com-huawei-operation-activity-WebViewActivity$SportDataResponseCallback, reason: not valid java name */
        /* synthetic */ void m680x110df60f(WebView webView, Object obj, int i) {
            String url = WebViewUtils.getUrl(this.mFunctionRes, obj.toString());
            webView.loadUrl(url);
            WebViewInstrumentation.loadUrl(webView, url);
            String url2 = WebViewUtils.getUrl(this.mFunctionErr, String.valueOf(i));
            webView.loadUrl(url2);
            WebViewInstrumentation.loadUrl(webView, url2);
        }
    }

    private void showRightTextBtnMyActivity(boolean z) {
        if (z) {
            WebViewHelp.setRightButtonVisibility(0, this.mCustomTitleBar);
            this.mCustomTitleBar.setRightButtonDrawable(BaseApplication.getContext().getResources().getDrawable(R.drawable._2131428689_res_0x7f0b0551), nsf.h(R.string._2130850635_res_0x7f02334b));
            this.mCustomTitleBar.setRightButtonOnClickListener(new View.OnClickListener() { // from class: com.huawei.operation.activity.WebViewActivity.18
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    LogUtil.a(WebViewActivity.TAG, "onClick myActivity");
                    if (CommonUtil.aa(WebViewActivity.this.getApplicationContext())) {
                        WebViewActivity.this.mNetworkLayout.setVisibility(4);
                        WebViewActivity.this.mWebView.setVisibility(0);
                        if (BleConstants.BLE_THIRD_DEVICE_H5.equals(WebViewActivity.this.mBleIntroductionType)) {
                            WebViewActivity.this.showUnbindDeviceMenu();
                        } else {
                            WebViewActivity.this.handleMoreMyActivityPopWindow();
                        }
                        ViewClickInstrumentation.clickOnView(view);
                        return;
                    }
                    LogUtil.a(WebViewActivity.TAG, "reload isNetworkConnected");
                    ViewClickInstrumentation.clickOnView(view);
                }
            });
            return;
        }
        WebViewHelp.setRightButtonVisibility(8, this.mCustomTitleBar);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleMoreMyActivityPopWindow() {
        View inflate = View.inflate(this.mContext, R.layout.common_pop_menu_single, null);
        this.mPopView = new nqc(this.mContext, inflate);
        HealthTextView healthTextView = (HealthTextView) inflate.findViewById(R.id.text);
        healthTextView.setText(R.string._2130841925_res_0x7f021145);
        healthTextView.setOnClickListener(nkx.cwZ_(new View.OnClickListener() { // from class: com.huawei.operation.activity.WebViewActivity.19
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                String activityKeyUrlSp = WebViewUtils.getActivityKeyUrlSp(WebViewActivity.this.mContext);
                WebViewHelp.setClickBi(WebViewActivity.this.mContext, AnalyticsValue.HEALTH_ACTIVITY_WEBVIEW_MY_ACTIVITY_1100035.value());
                ActivityHtmlPathApi activityHtmlPathApi = (ActivityHtmlPathApi) Services.a("PluginOperation", ActivityHtmlPathApi.class);
                if (activityHtmlPathApi != null) {
                    WebViewActivity.this.mCustomWebView.reload(activityKeyUrlSp + activityHtmlPathApi.getActivityHtmlPath() + Constants.MY_ACTIVITY_HTML);
                }
                WebViewActivity.this.mPopView.b();
                ViewClickInstrumentation.clickOnView(view);
            }
        }, (BaseActivity) this.mContext, true, AnalyticsValue.HEALTH_ACTIVITY_WEBVIEW_MY_ACTIVITY_1100035.value()));
        this.mPopView.cEh_(this.mCustomTitleBar, 17);
    }

    public void startLoadUrl(String str) {
        LogUtil.a(TAG, "startLoadUrl startLoadUrl the url");
        if (this.mCustomWebView == null) {
            LogUtil.c(TAG, "mCustomWebView is null startLoadUrl");
            return;
        }
        String replaceSpace = WebViewUtils.replaceSpace(str);
        if (!this.mCustomWebView.isWhiteUrl(replaceSpace)) {
            LogUtil.c(TAG, "isWhiteUrl is false url");
            this.mCustomWebView.setJsEnable(false);
        } else {
            this.mCustomWebView.setJsEnable(true);
            WebView webView = this.mWebView;
            webView.loadUrl(replaceSpace);
            WebViewInstrumentation.loadUrl(webView, replaceSpace);
        }
    }

    public void startLoadJs(String str) {
        LogUtil.a(TAG, "startLoadJs");
        if (this.mCustomWebView == null) {
            LogUtil.c(TAG, "mCustomWebView is null");
            return;
        }
        WebView webView = this.mWebView;
        webView.loadUrl(str);
        WebViewInstrumentation.loadUrl(webView, str);
    }

    private void showNoServicePage() {
        LogUtil.a(TAG, "showNoServicePage");
        WebViewHelp.setBiAnalysis("-2");
        this.mNetworkLayout.setVisibility(0);
        this.mWebView.setVisibility(4);
        this.mShoppingLayOut.setVisibility(8);
        this.mTraderOrder.setVisibility(8);
        setTxtTipsVisibility(8);
        this.mBtnNetSetting.setVisibility(8);
        this.mNoNetworkTips.setGravity(17);
        this.mNoNetworkTips.setText(getString(R.string._2130842060_res_0x7f0211cc));
        this.mTipsImageView.setBackgroundResource(R.drawable._2131430211_res_0x7f0b0b43);
    }

    private void showUnstableNetworkPage() {
        LogUtil.a(TAG, "showNoServicePage");
        WebViewHelp.setBiAnalysis("-1");
        this.mNetworkLayout.setVisibility(0);
        this.mWebView.setVisibility(4);
        this.mShoppingLayOut.setVisibility(8);
        this.mTraderOrder.setVisibility(8);
        setTxtTipsVisibility(8);
        this.mNoNetworkTips.setGravity(17);
        this.mProgressBar.setVisibility(8);
        this.mNoNetworkTips.setText(getString(R.string._2130842062_res_0x7f0211ce));
        this.mTipsImageView.setBackgroundResource(R.drawable._2131430211_res_0x7f0b0b43);
    }

    private void showNoNetworkPage() {
        if (com.huawei.operation.utils.Utils.isMatchThirdDeviceH5UrlPrefix(this.mUrl)) {
            LogUtil.c(TAG, "the url is thirdParty device, not need showNoNetworkPage");
            return;
        }
        LogUtil.a(TAG, "showNoNetworkPage");
        WebViewHelp.setBiAnalysis("-1");
        this.mNetworkLayout.setVisibility(0);
        this.mWebView.setVisibility(4);
        this.mShoppingLayOut.setVisibility(8);
        this.mTraderOrder.setVisibility(8);
        setTxtTipsVisibility(8);
        this.mNoNetworkTips.setGravity(17);
        this.mNoNetworkTips.setText(getString(R.string._2130842061_res_0x7f0211cd));
        this.mTipsImageView.setBackgroundResource(R.drawable._2131430211_res_0x7f0b0b43);
    }

    private void showServerErrorPage() {
        LogUtil.a(TAG, "showServerErrorPage");
        WebViewHelp.setBiAnalysis("-2");
        this.mNetworkLayout.setVisibility(0);
        this.mWebView.setVisibility(4);
        this.mShoppingLayOut.setVisibility(8);
        this.mTraderOrder.setVisibility(8);
        this.mBtnNetSetting.setVisibility(8);
        setTxtTipsVisibility(8);
        this.mNoNetworkTips.setGravity(17);
        this.mNoNetworkTips.setText(getString(R.string._2130842063_res_0x7f0211cf));
        this.mTipsImageView.setBackgroundResource(R.drawable._2131430211_res_0x7f0b0b43);
    }

    private void showSslErrorTipsPage() {
        LogUtil.a(TAG, "showSslErrorTipsPage");
        WebViewHelp.setBiAnalysis(OpAnalyticsConstants.SSL_FAIL_CODE);
        this.mNetworkLayout.setVisibility(0);
        this.mWebView.setVisibility(4);
        this.mShoppingLayOut.setVisibility(8);
        this.mTraderOrder.setVisibility(8);
        this.mNoNetworkTips.setGravity(0);
        setTxtTipsVisibility(0);
        this.mNoNetworkTips.setText(getString(R.string._2130842071_res_0x7f0211d7));
        this.mWebViewTipsCheckDate.setText(String.format(Locale.ROOT, getString(R.string._2130842072_res_0x7f0211d8), 2));
        this.mWebViewTipsCustomerService.setText(getString(R.string._2130842073_res_0x7f0211d9));
        this.mTipsImageView.setBackgroundResource(R.drawable._2131429687_res_0x7f0b0937);
    }

    private void setTxtTipsVisibility(int i) {
        this.mWebViewTipsUpdate.setVisibility(i);
        this.mWebViewTipsCheckDate.setVisibility(i);
        this.mWebViewTipsCustomerService.setVisibility(i);
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        DoubleClickReceiver doubleClickReceiver = new DoubleClickReceiver();
        this.mDoubleClickReceiver = doubleClickReceiver;
        BroadcastManager.wh_(doubleClickReceiver);
        super.onResume();
        LogUtil.a(TAG, "onResume");
        if (this.mCustomWebView != null && this.mWebView != null) {
            LogUtil.c(TAG, "Constants.WEB_VIEW_STATUS_ON_RESUME");
            this.mCustomWebView.callbackWebViewStatus(Constants.WEBVIEW_STATUS_ON_RESUME);
        }
        this.mBiTotalTime += this.mBiPauseTime - this.mBiStartTime;
        this.mBiStartTime = System.currentTimeMillis();
        CustomWebView customWebView = this.mCustomWebView;
        if (customWebView != null) {
            String biCurrentUrl = customWebView.getBiCurrentUrl();
            if (!TextUtils.isEmpty(biCurrentUrl)) {
                sBiAnalyticsUtil.e(this.mCustomWebView.getBiIdFromUrl(biCurrentUrl), this.mCustomWebView.getUrlLifeCycleBiMap(biCurrentUrl));
            }
            if (this.mCustomWebView.getCustomViewDialog() != null) {
                this.mCustomWebView.getCustomViewDialog().dismiss();
            }
            this.mCustomWebView.clearUploadMessage();
        }
        if (this.mIsFullScreen) {
            WebViewHelp.setFullScreen(getWindow());
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onPause() {
        super.onPause();
        LogUtil.a(TAG, "onPause");
        if (this.mCustomWebView != null && this.mWebView != null) {
            LogUtil.c(TAG, "Constants.WEB_VIEW_STATUS_ON_PAUSE");
            this.mCustomWebView.callbackWebViewStatus(Constants.WEBVIEW_STATUS_ON_PAUSE);
        }
        CustomWebView customWebView = this.mCustomWebView;
        if (customWebView != null) {
            String biCurrentUrl = customWebView.getBiCurrentUrl();
            if (!TextUtils.isEmpty(biCurrentUrl)) {
                sBiAnalyticsUtil.a(this.mCustomWebView.getBiIdFromUrl(biCurrentUrl), this.mCustomWebView.getUrlLifeCycleBiMap(biCurrentUrl));
            }
        }
        this.mBiPauseTime = System.currentTimeMillis();
        BroadcastManager.wv_(this.mDoubleClickReceiver);
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        LogUtil.a(TAG, "onDestroy");
        this.webViewActivityExt.startVmallLogout(this.mCustomWebView, this.mWebView);
        sIsActiveFlag = false;
        setActiveFlag(false);
        JsClientApi.destroyApi(this.mJsApiId);
        setActivity(null);
        destroyBi();
        destroyWebView();
        PluginOperationAdapter pluginOperationAdapter = this.mAdapter;
        if (pluginOperationAdapter != null) {
            pluginOperationAdapter.stressAbort(null, this.mIsFromHeartRateBoard);
            this.mAdapter.breatheControl(4, 0, -1, 0, null);
        }
        onDestroyClear();
        H5ProJsAdapter h5ProJsAdapter = this.mH5ProJsAdapter;
        if (h5ProJsAdapter != null) {
            h5ProJsAdapter.cleanH5ProInterfaces();
            this.mH5ProJsAdapter = null;
        }
        this.webViewActivityExt = null;
        CustomWebView customWebView = this.mCustomWebView;
        if (customWebView != null) {
            customWebView.onDestroy();
        }
        if (!TextUtils.isEmpty(this.mDeviceId)) {
            JsInteraction.setDeviceId(null);
        }
        super.onDestroy();
    }

    private void destroyBi() {
        this.mBiTotalTime += this.mBiPauseTime - this.mBiStartTime;
        if (Constants.OPEN_SERVICE_TYPE.equals(this.mType)) {
            LogUtil.a("onDestroy OPEN_SERVICE_TYPE", new Object[0]);
            HashMap hashMap = new HashMap(16);
            hashMap.put("id", this.mBiId);
            hashMap.put("name", this.mBiName);
            hashMap.put("time", Long.valueOf(this.mBiTotalTime));
            ixx.d().d(this.mContext, AnalyticsValue.OPEN_SERVICE_WEBVIEW_2010064.value(), hashMap, 0);
        }
        if ("SHOW_TIME_BI".equals(this.mShouldBiShowTime)) {
            LogUtil.a(TAG, "BI_SHOW_TIME");
            HashMap hashMap2 = new HashMap(16);
            hashMap2.put("id", this.mBiId);
            hashMap2.put("title", this.mBiName);
            hashMap2.put("source", this.mBiSource);
            hashMap2.put(SmartMsgConstant.MSG_SHOW_TIME, Long.valueOf(this.mBiTotalTime));
            ixx.d().d(this.mContext, AnalyticsValue.WEBVIEW_SHOW_1100020.value(), hashMap2, 0);
        }
        if (this.mIsGuide) {
            HashMap hashMap3 = new HashMap(16);
            hashMap3.put("startTime", Long.valueOf(this.mBiStartTime));
            hashMap3.put("totalTime", Long.valueOf(this.mBiTotalTime));
            ixx.d().d(this.mContext, AnalyticsValue.HEALTH_SLEEP_GUIDE_TIME_21300024.value(), hashMap3, 0);
        }
    }

    private void onDestroyClear() {
        BleOperatorImpl bleOperatorImpl;
        if (this.mProductId != null && (bleOperatorImpl = this.mBleOperator) != null) {
            bleOperatorImpl.stopBleState();
            this.mBleOperator = null;
        }
        cancelShoppingView();
        this.mDoubleClickReceiver = null;
        this.mAdapter = null;
    }

    private void destroyWebView() {
        LogUtil.a(TAG, "destroyWebView");
        if (this.mWebView != null) {
            LogUtil.a("onDestroy destroyWebView", new Object[0]);
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
        }
    }

    private void reload() {
        LogUtil.a(TAG, "reload");
        if (!CommonUtil.aa(getApplicationContext())) {
            LogUtil.a(TAG, "reload isNetworkConnected");
            return;
        }
        this.mNetworkLayout.setVisibility(4);
        this.mWebView.setVisibility(0);
        String webViewOriginalUrl = WebViewHelp.getWebViewOriginalUrl(this.mWebView);
        if (TextUtils.isEmpty(webViewOriginalUrl)) {
            LogUtil.a(TAG, "reloadUrl is empty");
            if (this.mIsHealthChoose) {
                this.mTraderOrder.setVisibility(0);
                addTradeView();
            } else if (this.mIsMiaoChoose) {
                LogUtil.a(TAG, "reloadUrl mIsVmallChoose is false");
                this.mCustomWebView.load(WebViewHelp.getMiaoHealthUrlString(this.mMiaoHealthHost));
            } else {
                this.mCustomWebView.load(this.mUrl);
            }
        } else if (WebViewHelp.checkOrderManagerPage(webViewOriginalUrl)) {
            LogUtil.a(TAG, "reloadUrl mIsVmallOrMiaoChoose is true");
            if (this.mIsHealthChoose) {
                this.mTraderOrder.setVisibility(0);
                addTradeView();
            } else if (this.mIsVmallChoose) {
                LogUtil.a(TAG, "reloadUrl mIsVmallChoose is true");
                this.mCustomWebView.load(WebViewHelp.getTheVmallUrl(this.mHealthRecommendHost));
            } else {
                LogUtil.a(TAG, "reloadUrl mIsVmallChoose is false");
                this.mCustomWebView.load(WebViewHelp.getMiaoHealthUrlString(this.mMiaoHealthHost));
            }
        } else {
            LogUtil.a(TAG, "reloadUrl mIsVmallOrMiaoChoose is false");
            this.mCustomWebView.load(webViewOriginalUrl);
        }
        showShoppingView();
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        LogUtil.a(TAG, "reload");
        if (view == this.mReloadLayout) {
            reload();
        } else if (view == this.mBtnNetSetting) {
            CommonUtil.q(this.mContext);
        } else if (view == this.mVmallTextView) {
            setTheTabsChoose(true);
            doBiEvent(1);
        } else if (view == this.mMiaoTextView) {
            setTheTabsChoose(false);
            doBiEvent(2);
        } else if (view == this.mTradeTextview) {
            setTradeTabsChoose();
        } else {
            LogUtil.a(TAG, "other position");
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    private void doBiEvent(int i) {
        HashMap hashMap = new HashMap();
        hashMap.put("click", 1);
        hashMap.put(com.huawei.health.messagecenter.model.CommonUtil.PAGE_TYPE, Integer.valueOf(i));
        ixx.d().d(com.huawei.haf.application.BaseApplication.e(), AnalyticsValue.REMIND_MY_ORDER_EVENT.value(), hashMap, 0);
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        LogUtil.a(TAG, "requestCode is ", Integer.valueOf(i), " resultCode is ", Integer.valueOf(i2));
        BleOperatorImpl bleOperatorImpl = this.mBleOperator;
        if (bleOperatorImpl != null) {
            bleOperatorImpl.onActivityResult(i, i2, intent);
        }
        if (i == 10086) {
            if (this.mCustomWebView.getUploadMessage() == null) {
                return;
            }
            Uri data = (intent == null || i2 != -1) ? null : intent.getData();
            if (!checkUriIsValid(data)) {
                data = null;
            }
            this.mCustomWebView.getUploadMessage().onReceiveValue(data);
            this.mCustomWebView.setUploadMessage(null);
            return;
        }
        if (i == 10087) {
            if (this.mCustomWebView.getUploadMessageForAndroid5() == null) {
                return;
            }
            Uri data2 = (intent == null || i2 != -1) ? null : intent.getData();
            if (data2 != null) {
                if (!checkUriIsValid(data2)) {
                    data2 = null;
                }
                this.mCustomWebView.getUploadMessageForAndroid5().onReceiveValue(new Uri[]{data2});
            } else {
                this.mCustomWebView.getUploadMessageForAndroid5().onReceiveValue(new Uri[0]);
            }
            this.mCustomWebView.setUploadMessageForAndroid5(null);
            return;
        }
        if (i == 20001) {
            WebViewHelp.takePhoto(i2, intent, this.mCustomWebView);
            return;
        }
        if (i != 20002) {
            if (i != 42001) {
                LogUtil.a(TAG, "onActivityResult in JsClientApi");
                JsClientApi.handleActivityResult(i, i2, intent);
                return;
            } else {
                if (i2 == 42002) {
                    finish();
                    return;
                }
                return;
            }
        }
        if (i2 != -1) {
            return;
        }
        int aub_ = ezd.aub_(intent);
        if (aub_ == 2) {
            nsn.cKS_(this, 20002);
            return;
        }
        BleOperatorImpl bleOperatorImpl2 = this.mBleOperator;
        if (bleOperatorImpl2 == null || aub_ != 0) {
            return;
        }
        bleOperatorImpl2.handleScanCodeResult(i2, intent);
    }

    private boolean checkUriIsValid(Uri uri) {
        if (uri == null || uri.getPath().contains(FeedbackWebConstants.INVALID_FILE_NAME_PRE)) {
            return false;
        }
        for (String str : BLACK_PATH_LIST) {
            if (uri.getPath().startsWith(str)) {
                return false;
            }
        }
        return true;
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        super.onRequestPermissionsResult(i, strArr, iArr);
        LogUtil.a(TAG, "onRequestPermissionsResult requestCode is ", Integer.valueOf(i));
        jeg.d().d(strArr, iArr);
        if (i == 9006) {
            JsClientApi.handleActivityPermissionResult(i, strArr, iArr);
            return;
        }
        if (i == 1) {
            if (jdi.c(this.mContext, strArr)) {
                LogUtil.a(TAG, "onRequestPermissionsResult : HasPermissions");
                this.mCustomWebView.takePhoto();
                return;
            } else {
                LogUtil.a(TAG, "onRequestPermissionsResult : finish");
                return;
            }
        }
        if (i == 3001) {
            LogUtil.a(TAG, "Constants.REQUEST_CODE_SCREEN");
            if (jdi.c(this.mContext, strArr)) {
                LogUtil.a(TAG, "onRequestPermissionsResult : HasPermissions");
                new CaptureUtils(this.mContext).capture(this.mWebView, this.mCustomWebView.getCaptureFunction());
                return;
            } else {
                new CaptureUtils(this.mContext).captureNoPermission(this.mWebView, this.mCustomWebView.getCaptureFunction());
                LogUtil.a(TAG, "onRequestPermissionsResult : finish");
                return;
            }
        }
        LogUtil.c(TAG, "onRequestPermissionsResult other");
    }

    private void resetActivityListPage() {
        LogUtil.a(TAG, "resetActivityListPage");
        this.mHandler.obtainMessage(1).sendToTarget();
    }

    public static void backToActivityListPage() {
        LogUtil.a(TAG, "backToActivityListPage");
        if (sActivity instanceof WebViewActivity) {
            LogUtil.a(TAG, "resetActivityListPage");
            ((WebViewActivity) sActivity).resetActivityListPage();
        }
    }

    public static void setActivity(Activity activity) {
        LogUtil.a(TAG, "setActivity");
        sActivity = activity;
    }

    public static Activity getActivity() {
        LogUtil.a(TAG, "getActivity");
        return sActivity;
    }

    /* loaded from: classes5.dex */
    public class DoubleClickReceiver extends BroadcastReceiver {
        public DoubleClickReceiver() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            LogUtil.a(WebViewActivity.TAG, "DoubleClickReceiver onReceive()");
            if (WebViewActivity.this.mWebView != null) {
                WebViewActivity.this.mWebView.setScrollY(0);
            }
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
        int i = configuration.orientation;
        if (i != 1) {
            if (i != 2) {
                return;
            }
            LogUtil.a(TAG, "ORIENTATION_LANDSCAPE");
            return;
        }
        LogUtil.a(TAG, "ORIENTATION_PORTRAIT");
        WebView webView = this.mWebView;
        if (webView == null) {
            return;
        }
        try {
            webView.getClass().getMethod("onPause", new Class[0]).invoke(this.mWebView, null);
        } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            LogUtil.a(TAG, e.getMessage());
        }
        CustomTitleBar customTitleBar = this.mCustomTitleBar;
        if (customTitleBar != null) {
            customTitleBar.setVisibility(0);
        }
        WebViewHelp.showBottomUiMenu(getWindow(), this.mUiOptions);
        try {
            this.mWebView.getClass().getMethod("onResume", new Class[0]).invoke(this.mWebView, null);
        } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException e2) {
            LogUtil.a(TAG, e2.getMessage());
        }
        loadApplicationTheme();
        initSystemBar();
        if (CommonUtil.bh()) {
            getWindow().setNavigationBarColor(getResources().getColor(R$color.common_color_white));
            LogUtil.a(TAG, "setNavigationBarColor common_color_white");
        }
        getWindow().getDecorView().refreshDrawableState();
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, android.app.Activity, android.view.Window.Callback
    public void onWindowFocusChanged(boolean z) {
        IBaseResponseCallback iBaseResponseCallback;
        super.onWindowFocusChanged(z);
        if (z && this.mIsFullScreen) {
            WebViewHelp.setFullScreen(getWindow());
        }
        if (!z || (iBaseResponseCallback = mCallback) == null) {
            return;
        }
        iBaseResponseCallback.d(0, "");
    }

    public String getUrl() {
        return this.mUrl;
    }

    public void setTitle(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        this.mCustomWebView.setTitle(str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showUnbindDeviceMenu() {
        if (this.mContext == null || this.mCustomTitleBar == null) {
            LogUtil.h(TAG, "showUnbindDeviceMenu mContext or mCustomTitleBar = null ");
        } else {
            new PopViewList(this.mContext, this.mCustomTitleBar, new ArrayList(Arrays.asList(getResources().getString(R.string.IDS_device_wear_home_delete_device)))).e(new PopViewList.PopViewClickListener() { // from class: com.huawei.operation.activity.WebViewActivity$$ExternalSyntheticLambda1
                @Override // com.huawei.ui.commonui.popupview.PopViewList.PopViewClickListener
                public final void setOnClick(int i) {
                    WebViewActivity.this.m679x5f3802f7(i);
                }
            });
        }
    }

    /* renamed from: lambda$showUnbindDeviceMenu$0$com-huawei-operation-activity-WebViewActivity, reason: not valid java name */
    /* synthetic */ void m679x5f3802f7(int i) {
        if (i == 0) {
            PluginBaseAdapter adapter = PluginOperation.getInstance(this.mContext).getAdapter();
            if (this.mAdapter == null && (adapter instanceof PluginOperationAdapter)) {
                this.mAdapter = (PluginOperationAdapter) adapter;
            }
            if (this.mAdapter == null) {
                LogUtil.h(TAG, "showUnbindDeviceMenu: adapter is null");
                return;
            }
            if (this.webViewActivityExt == null) {
                this.webViewActivityExt = new WebViewActivityExt();
            }
            this.webViewActivityExt.showUnBindDeviceDialog(this.mContext, this.mDeviceInfo, this.mProductId, this.mAdapter);
        }
    }

    private void initOnTouchListener() {
        WebView webView = this.mWebView;
        if (webView == null) {
            return;
        }
        webView.setOnTouchListener(new View.OnTouchListener() { // from class: com.huawei.operation.activity.WebViewActivity$$ExternalSyntheticLambda0
            @Override // android.view.View.OnTouchListener
            public final boolean onTouch(View view, MotionEvent motionEvent) {
                return WebViewActivity.this.m678xbca69d7a(view, motionEvent);
            }
        });
    }

    /* renamed from: lambda$initOnTouchListener$1$com-huawei-operation-activity-WebViewActivity, reason: not valid java name */
    /* synthetic */ boolean m678xbca69d7a(View view, MotionEvent motionEvent) {
        if ((motionEvent.getAction() & 255) != 0) {
            return false;
        }
        this.mTouchX = motionEvent.getX();
        this.mTouchY = motionEvent.getY();
        return false;
    }

    private void initLongClickImageShare() {
        WebView webView = this.mWebView;
        if (webView == null) {
            return;
        }
        webView.setOnLongClickListener(new View.OnLongClickListener() { // from class: com.huawei.operation.activity.WebViewActivity.20
            @Override // android.view.View.OnLongClickListener
            public boolean onLongClick(View view) {
                WebView.HitTestResult hitTestResult = WebViewActivity.this.mWebView.getHitTestResult();
                if (hitTestResult == null) {
                    return false;
                }
                int type = hitTestResult.getType();
                if (type != 5 && type != 8) {
                    return false;
                }
                final String extra = hitTestResult.getExtra();
                LogUtil.a(WebViewActivity.TAG, "image in webview is long clicked: ", extra);
                if (WebViewHelp.isSvgUrl(extra)) {
                    return false;
                }
                View view2 = new View(WebViewActivity.this.mContext);
                view2.setX(WebViewActivity.this.mTouchX);
                view2.setY(WebViewActivity.this.mTouchY);
                WebViewActivity.this.mFrameLayout.addView(view2);
                View inflate = View.inflate(WebViewActivity.this.mContext, R.layout.common_pop_menu_single, null);
                final nqc nqcVar = new nqc(WebViewActivity.this.mContext, inflate);
                HealthTextView healthTextView = (HealthTextView) inflate.findViewById(R.id.text);
                healthTextView.setText(R.string._2130844725_res_0x7f021c35);
                nqcVar.cEh_(view2, 18);
                healthTextView.setOnClickListener(nkx.cwZ_(new View.OnClickListener() { // from class: com.huawei.operation.activity.WebViewActivity.20.1
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view3) {
                        nqcVar.b();
                        WebViewHelp.shareWebImage(extra);
                        ViewClickInstrumentation.clickOnView(view3);
                    }
                }, (BaseActivity) WebViewActivity.this.mContext, true, ""));
                WebViewActivity.this.mFrameLayout.removeView(view2);
                return false;
            }
        });
    }

    private void putBiEventLoadPage(String str, int i) {
        HashMap hashMap = new HashMap(16);
        hashMap.put("click", 1);
        hashMap.put("Steps", Integer.valueOf(i));
        hashMap.put("H5package", str);
        ixx.d().d(BaseApplication.getContext(), AnalyticsValue.H5_RESOURCE_LOAD_EVENT_VALUE.value(), hashMap, 0);
    }
}
