package com.huawei.ui.openservice.ui;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.RelativeLayout;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.operation.activity.WebViewActivity;
import com.huawei.operation.utils.Constants;
import com.huawei.operation.utils.Utils;
import com.huawei.pluginachievement.manager.model.KakaConstants;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.button.HealthButton;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.openservice.OpenServiceUtil;
import com.huawei.ui.openservice.db.control.OpenServiceControl;
import com.huawei.ui.openservice.db.model.OpenService;
import com.huawei.ui.openservice.db.model.UserServiceAuth;
import com.huawei.ui.openservice.ui.adapter.OpenServiceGridAdapter;
import com.huawei.ui.openservice.ui.dialog.ServiceAuthDialog;
import defpackage.ixx;
import defpackage.koq;
import defpackage.mxv;
import defpackage.nrr;
import defpackage.nsn;
import health.compact.a.CommonUtil;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.RejectedExecutionException;

/* loaded from: classes2.dex */
public class OpenServiceActivity extends BaseActivity {
    private static final int DEFAULT_DELAY_TIME = 100;
    private static final int GRID_NUM_NORMAL = 2;
    private static final int GRID_NUM_TAHITI = 4;
    private static final String LOG_TAG = "Opera_OpenServiceActivity";
    private static final int MSG_INIT_DATA = 1;
    private static final int MSG_NOT_AUTH = 4;
    private static final int MSG_NO_DATA_AND_NO_NET = 5;
    private static final int MSG_NO_DATA_AND_SERVICE_ERROR = 6;
    private static final String OPEN_SERVICE = "OpenService";
    private static final String SERVICE_TYPE_APPLICATION_SCHEME = "SCHEME";
    private static final String SERVICE_TYPE_QUICK_APPLICATION = "K";
    private static final String SERVICE_TYPE_URL = "A";
    private static final String TAG = "OpenServiceActivity";
    private static final int THREAD_POOL_SIZE = 1;
    private OpenServiceGridAdapter adapter;
    private ScrollGridView gridView;
    private String huid;
    private List<OpenService> items;
    private HealthTextView loadTextView;
    private Context mContext;
    private RelativeLayout mExceptionLayout;
    private OpenServiceHandler mHandler;
    private RelativeLayout mLoadingLayout;
    private HealthButton mNetworkSettingButton;
    private ThreadPoolManager mThreadPool;
    private RelativeLayout mainIncludeLayout;
    private OpenServiceControl serviceControl;

    private void showNoDataLayout() {
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mContext = this;
        this.mHandler = new OpenServiceHandler(this);
        this.mThreadPool = ThreadPoolManager.e(1, 1, TAG);
        this.items = new ArrayList();
        this.serviceControl = OpenServiceControl.getInstance(this.mContext);
        setContentView(R.layout.activity_open_service);
        this.huid = LoginInit.getInstance(this.mContext).getAccountInfo(1011);
        init();
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity
    public void initViewTahiti() {
        if (this.gridView == null) {
            return;
        }
        if (nsn.ag(this.mContext)) {
            this.gridView.setNumColumns(4);
        } else {
            this.gridView.setNumColumns(2);
        }
    }

    private void init() {
        this.mainIncludeLayout = (RelativeLayout) findViewById(R.id.open_service_main);
        this.mLoadingLayout = (RelativeLayout) findViewById(R.id.open_service_load);
        this.mNetworkSettingButton = (HealthButton) findViewById(R.id.net_setting_btn);
        this.gridView = (ScrollGridView) findViewById(R.id.open_service_grid_view);
        if (nsn.ag(this.mContext)) {
            this.gridView.setNumColumns(4);
        }
        this.gridView.setHorizontalSpacing(nrr.b(this.mContext));
        this.gridView.setSelector(new ColorDrawable(0));
        this.mNetworkSettingButton.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.ui.openservice.ui.OpenServiceActivity.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                CommonUtil.q(OpenServiceActivity.this.mContext);
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        this.loadTextView = (HealthTextView) findViewById(R.id.center_text);
        this.mExceptionLayout = (RelativeLayout) findViewById(R.id.open_service_ex_layout);
        initItems();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showMainLayout() {
        this.mLoadingLayout.setVisibility(8);
        this.mainIncludeLayout.setVisibility(0);
        initAdapter();
        showNoDataLayout();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showNoDataAndNoNetLayout() {
        this.mLoadingLayout.setVisibility(8);
        this.mExceptionLayout.setVisibility(0);
        this.loadTextView.setText(this.mContext.getResources().getString(R.string._2130842128_res_0x7f021210));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showNoDataAnNoServiceLayout() {
        this.mLoadingLayout.setVisibility(8);
        this.mExceptionLayout.setVisibility(0);
        this.loadTextView.setText(this.mContext.getResources().getString(R.string._2130842129_res_0x7f021211));
    }

    private void initAdapter() {
        if (koq.b(this.items)) {
            LogUtil.h(LOG_TAG, "initAdapter() items is empty.");
            return;
        }
        final ArrayList arrayList = new ArrayList(10);
        for (OpenService openService : this.items) {
            if (openService == null) {
                LogUtil.h(LOG_TAG, "initAdapter() openService is null.");
            } else {
                String serviceVersion = openService.getServiceVersion();
                LogUtil.a(LOG_TAG, "initAdapter() serviceVersion = ", serviceVersion);
                if (!TextUtils.isEmpty(serviceVersion)) {
                    if ("0".equals(serviceVersion) || (CommonUtil.as() && "1".equals(serviceVersion))) {
                        setServiceShow(arrayList, openService);
                    }
                } else {
                    setServiceShow(arrayList, openService);
                }
            }
        }
        OpenServiceGridAdapter openServiceGridAdapter = new OpenServiceGridAdapter(arrayList, this.mContext, new View.OnClickListener() { // from class: com.huawei.ui.openservice.ui.OpenServiceActivity.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Object tag = view.getTag();
                if (tag != null && (tag instanceof Integer)) {
                    OpenServiceActivity.this.dealItemClick((OpenService) arrayList.get(((Integer) tag).intValue()));
                    ViewClickInstrumentation.clickOnView(view);
                } else {
                    ViewClickInstrumentation.clickOnView(view);
                }
            }
        });
        this.adapter = openServiceGridAdapter;
        this.gridView.setAdapter((ListAdapter) openServiceGridAdapter);
    }

    private void addOpenService(List<OpenService> list, OpenService openService) {
        if (list == null || openService == null) {
            LogUtil.h(LOG_TAG, "addOpenService openService is null.");
            return;
        }
        if (CommonUtil.bh()) {
            list.add(openService);
            return;
        }
        if (CommonUtil.bf()) {
            String serviceID = openService.getServiceID();
            if ("SCHEME_heartratestudy".equals(serviceID) || "SCHEME_heartstudy".equals(serviceID) || "SCHEME_vascular".equals(serviceID) || KakaConstants.DECOMPRESSI0N.equals(serviceID)) {
                return;
            }
            list.add(openService);
            return;
        }
        String serviceID2 = openService.getServiceID();
        if ("SCHEME_heartratestudy".equals(serviceID2) || "SCHEME_heartstudy".equals(serviceID2) || "SCHEME_vascular".equals(serviceID2)) {
            return;
        }
        list.add(openService);
    }

    private void setServiceShow(List<OpenService> list, OpenService openService) {
        if (list == null || openService == null) {
            LogUtil.h(LOG_TAG, "setServiceShow openService is null.");
            return;
        }
        List<String> showLabels = openService.getShowLabels();
        LogUtil.a(LOG_TAG, "showLabels:", showLabels, " currentVersion:", Integer.valueOf(BaseApplication.c()));
        if (koq.b(showLabels)) {
            addOpenService(list, openService);
            LogUtil.a(LOG_TAG, "showLabels is empty");
            return;
        }
        int showVersion = openService.getShowVersion();
        if (showVersion != 0 && showVersion > BaseApplication.c()) {
            LogUtil.h(LOG_TAG, Integer.valueOf(showVersion), " currentVersion:", Integer.valueOf(BaseApplication.c()));
            return;
        }
        LogUtil.a(LOG_TAG, openService.toString());
        if (koq.b(showLabels)) {
            LogUtil.h(LOG_TAG, "labels is empty");
            return;
        }
        if (CommonUtil.bh()) {
            addData(showLabels, "1", list, openService);
        } else if (CommonUtil.bf()) {
            addData(showLabels, "2", list, openService);
        } else {
            addData(showLabels, "3", list, openService);
        }
    }

    private void addData(List<String> list, String str, List<OpenService> list2, OpenService openService) {
        if (list.contains(str)) {
            list2.add(openService);
        } else {
            LogUtil.h(LOG_TAG, list.toString(), " lable:", str);
        }
    }

    private void initItems() {
        this.mThreadPool.execute(new Runnable() { // from class: com.huawei.ui.openservice.ui.OpenServiceActivity.3
            @Override // java.lang.Runnable
            public void run() {
                OpenServiceActivity.this.initData();
                if (OpenServiceActivity.this.isDataExist()) {
                    OpenServiceActivity.this.sendMsg(1);
                } else if (CommonUtil.aa(OpenServiceActivity.this.mContext)) {
                    OpenServiceActivity.this.sendMsg(6);
                } else {
                    OpenServiceActivity.this.sendMsg(5);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void initData() {
        if (TextUtils.isEmpty(this.huid)) {
            String huidOrDefault = LoginInit.getInstance(this.mContext).getHuidOrDefault();
            if (TextUtils.isEmpty(huidOrDefault) || "0".equals(huidOrDefault)) {
                return;
            }
        }
        List<OpenService> queryAllService = this.serviceControl.queryAllService();
        this.items = queryAllService;
        OpenService.orderOpenService(queryAllService);
    }

    private void doBi(String str, String str2, String str3, String str4) {
        HashMap hashMap = new HashMap();
        hashMap.put("click", "1");
        hashMap.put("id", str2);
        hashMap.put("name", str3);
        hashMap.put("entrance", str4);
        ixx.d().d(this.mContext, str, hashMap, 0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendMsg(int i) {
        Message obtainMessage = this.mHandler.obtainMessage();
        obtainMessage.what = i;
        this.mHandler.sendMessageDelayed(obtainMessage, 100L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendMsg(int i, Object obj) {
        Message obtainMessage = this.mHandler.obtainMessage();
        obtainMessage.what = i;
        obtainMessage.obj = obj;
        this.mHandler.sendMessageDelayed(obtainMessage, 100L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isDataExist() {
        return !OpenService.isEmpty(this.items);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startService(OpenService openService) {
        char c;
        if (openService == null) {
            LogUtil.h(LOG_TAG, "startService() service is null.");
            return;
        }
        String serviceTypeID = openService.getServiceTypeID();
        if (TextUtils.isEmpty(serviceTypeID)) {
            LogUtil.h(LOG_TAG, "startService() serviceTypeId is empty.");
            jumpToWebView(openService);
            return;
        }
        String upperCase = serviceTypeID.toUpperCase(Locale.ENGLISH);
        LogUtil.a(LOG_TAG, "startService() serviceTypeId = ", upperCase);
        upperCase.hashCode();
        int hashCode = upperCase.hashCode();
        if (hashCode == -1854658139) {
            if (upperCase.equals(SERVICE_TYPE_APPLICATION_SCHEME)) {
                c = 0;
            }
            c = 65535;
        } else if (hashCode != 65) {
            if (hashCode == 75 && upperCase.equals("K")) {
                c = 2;
            }
            c = 65535;
        } else {
            if (upperCase.equals("A")) {
                c = 1;
            }
            c = 65535;
        }
        if (c != 0) {
            if (c == 1) {
                jumpToWebView(openService);
            } else if (c != 2) {
                jumpToWebView(openService);
            }
            doBi(AnalyticsValue.OPEN_SERVICE_ENTER_20100063.value(), openService.getServiceID(), openService.getProductName(), "OpenService");
        }
        loginJumpToApk(openService);
        doBi(AnalyticsValue.OPEN_SERVICE_ENTER_20100063.value(), openService.getServiceID(), openService.getProductName(), "OpenService");
    }

    private void jumpToWebView(OpenService openService) {
        if (openService == null) {
            LogUtil.h(LOG_TAG, "jumpToWebView() service is null.");
            return;
        }
        String serviceUrl = openService.getServiceUrl();
        if (TextUtils.isEmpty(serviceUrl)) {
            serviceUrl = serviceUrl.trim();
        }
        if (Utils.isNotSupportBrowseUrl(serviceUrl)) {
            LoginInit.getInstance(this.mContext).browsingToLogin(new WebViewLoginCallback(this, serviceUrl, openService), AnalyticsValue.OPEN_SERVICE_ENTER_20100063.value());
        } else {
            startWebViewActivity(openService, serviceUrl);
        }
    }

    private void loginJumpToApk(OpenService openService) {
        if (openService == null) {
            LogUtil.h(LOG_TAG, "loginJumpToApk() service is null.");
            return;
        }
        String serviceTypeID = openService.getServiceTypeID();
        if (TextUtils.isEmpty(serviceTypeID)) {
            LogUtil.h(LOG_TAG, "loginJumpToApk() serviceTypeId is empty.");
        } else {
            LoginInit.getInstance(this.mContext).browsingToLogin(new ResponseCallback(this, serviceTypeID, openService), AnalyticsValue.OPEN_SERVICE_ENTER_20100063.value());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startWebViewActivity(OpenService openService, String str) {
        Intent intent = new Intent(this.mContext, (Class<?>) WebViewActivity.class);
        intent.putExtra("url", str);
        intent.putExtra("EXTRA_BI_ID", openService.getServiceID());
        intent.putExtra("EXTRA_BI_NAME", openService.getProductName());
        intent.putExtra("EXTRA_BI_SOURCE", "OpenService");
        intent.putExtra("type", Constants.OPEN_SERVICE_TYPE);
        this.mContext.startActivity(intent);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dealItemClick(final OpenService openService) {
        doBi(AnalyticsValue.OPEN_SERVICE_CLICK_SERVICE_2010069.value(), openService.getServiceID(), openService.getProductName(), "OpenService");
        this.mThreadPool.execute(new Runnable() { // from class: com.huawei.ui.openservice.ui.OpenServiceActivity.4
            @Override // java.lang.Runnable
            public void run() {
                UserServiceAuth queryServiceAuth = OpenServiceActivity.this.serviceControl.queryServiceAuth(OpenServiceActivity.this.huid, openService.getServiceID());
                if (queryServiceAuth == null || queryServiceAuth.fetchAuthType() == 0) {
                    OpenServiceActivity.this.sendMsg(4, openService);
                } else {
                    OpenServiceActivity.this.startService(openService);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void saveAuthStatus(final String str) {
        try {
            this.mThreadPool.execute(new Runnable() { // from class: com.huawei.ui.openservice.ui.OpenServiceActivity.5
                @Override // java.lang.Runnable
                public void run() {
                    UserServiceAuth userServiceAuth = new UserServiceAuth();
                    userServiceAuth.configHuid(OpenServiceActivity.this.huid);
                    userServiceAuth.configAuthType(1);
                    userServiceAuth.configServiceID(str);
                    OpenServiceActivity.this.serviceControl.insertOrUpdateUserAuth(userServiceAuth);
                }
            });
        } catch (RejectedExecutionException e) {
            LogUtil.b(LOG_TAG, "openServiceActivity saveAuthStatus() RejectedExecutionException =", e);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showDialog(OpenService openService) {
        LogUtil.a(LOG_TAG, "openServiceActivity showDialog");
        if (openService.getHmsAuth() == 2) {
            LogUtil.a(LOG_TAG, "this is health");
            showServiceAuthDialog(openService);
        } else {
            LogUtil.a(LOG_TAG, "this is HMS");
            startService(openService);
            saveAuthStatus(openService.getServiceID());
        }
    }

    private void showServiceAuthDialog(final OpenService openService) {
        ServiceAuthDialog.Builder builder = new ServiceAuthDialog.Builder(this.mContext);
        builder.setService(openService).setPositiveButton(new View.OnClickListener() { // from class: com.huawei.ui.openservice.ui.OpenServiceActivity.6
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                OpenServiceActivity.this.startService(openService);
                OpenServiceActivity.this.saveAuthStatus(openService.getServiceID());
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        ServiceAuthDialog create = builder.create();
        create.setCancelable(true);
        if (isDestroyed() || isFinishing()) {
            return;
        }
        create.show();
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        ThreadPoolManager threadPoolManager = this.mThreadPool;
        if (threadPoolManager != null) {
            threadPoolManager.shutdown();
        }
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        super.onBackPressed();
    }

    /* loaded from: classes7.dex */
    static class OpenServiceHandler extends Handler {
        private final WeakReference<OpenServiceActivity> mActivity;

        OpenServiceHandler(OpenServiceActivity openServiceActivity) {
            this.mActivity = new WeakReference<>(openServiceActivity);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            OpenServiceActivity openServiceActivity = this.mActivity.get();
            if (openServiceActivity == null) {
                super.handleMessage(message);
                return;
            }
            LogUtil.a(OpenServiceActivity.LOG_TAG, "handleMessage msg is ", Integer.valueOf(message.what));
            int i = message.what;
            if (i == 1) {
                openServiceActivity.showMainLayout();
                return;
            }
            if (i != 4) {
                if (i == 5) {
                    openServiceActivity.showNoDataAndNoNetLayout();
                    return;
                } else {
                    if (i != 6) {
                        return;
                    }
                    openServiceActivity.showNoDataAnNoServiceLayout();
                    return;
                }
            }
            Object obj = message.obj;
            if (obj == null || !(obj instanceof OpenService)) {
                return;
            }
            LogUtil.a(OpenServiceActivity.LOG_TAG, "Enter showDialog ");
            openServiceActivity.showDialog((OpenService) obj);
        }
    }

    /* loaded from: classes7.dex */
    static class ResponseCallback implements IBaseResponseCallback {
        private WeakReference<OpenServiceActivity> mWeakReference;
        private OpenService service;
        private String serviceTypeId;

        ResponseCallback(OpenServiceActivity openServiceActivity, String str, OpenService openService) {
            this.mWeakReference = new WeakReference<>(openServiceActivity);
            this.serviceTypeId = str;
            this.service = openService;
        }

        @Override // com.huawei.hwbasemgr.IBaseResponseCallback
        /* renamed from: onResponse */
        public void d(int i, Object obj) {
            OpenServiceActivity openServiceActivity = this.mWeakReference.get();
            if (openServiceActivity == null) {
                LogUtil.h(OpenServiceActivity.LOG_TAG, "activity = null");
                return;
            }
            if (i == 0) {
                if ("K".equals(this.serviceTypeId)) {
                    mxv.b(openServiceActivity, this.service.getServiceUrl(), 4, null);
                } else if (OpenServiceActivity.SERVICE_TYPE_APPLICATION_SCHEME.equals(this.serviceTypeId)) {
                    OpenServiceUtil.jumpToApk(openServiceActivity, this.service);
                } else {
                    LogUtil.a(OpenServiceActivity.LOG_TAG, "loginJumpToApk serviceTypeId = ", this.serviceTypeId);
                }
            }
        }
    }

    /* loaded from: classes7.dex */
    static class WebViewLoginCallback implements IBaseResponseCallback {
        private WeakReference<OpenServiceActivity> mWeakReference;
        private OpenService service;
        private String trimServiceUrl;

        WebViewLoginCallback(OpenServiceActivity openServiceActivity, String str, OpenService openService) {
            this.mWeakReference = new WeakReference<>(openServiceActivity);
            this.trimServiceUrl = str;
            this.service = openService;
        }

        @Override // com.huawei.hwbasemgr.IBaseResponseCallback
        /* renamed from: onResponse */
        public void d(int i, Object obj) {
            OpenServiceActivity openServiceActivity = this.mWeakReference.get();
            if (openServiceActivity == null) {
                LogUtil.h(OpenServiceActivity.LOG_TAG, "activity = null");
            } else if (i == 0) {
                openServiceActivity.startWebViewActivity(this.service, this.trimServiceUrl);
            }
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
