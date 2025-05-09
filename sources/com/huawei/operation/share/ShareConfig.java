package com.huawei.operation.share;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Handler;
import android.text.TextUtils;
import com.huawei.haf.handler.HandlerExecutor;
import com.huawei.health.R;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.utils.httputils.pluginoperation.HttpResCallback;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.operation.adapter.PluginOperationAdapter;
import com.huawei.operation.operation.PluginOperation;
import com.huawei.operation.share.ShareConfig;
import com.huawei.operation.utils.CloudParamKeys;
import com.huawei.operation.utils.OperationUtils;
import com.huawei.operation.utils.WebViewUtils;
import com.huawei.phoneservice.faq.base.constants.FaqConstants;
import com.huawei.pluginbase.PluginBaseAdapter;
import com.huawei.ui.commonui.dialog.CommonDialog21;
import com.huawei.up.utils.Constants;
import defpackage.eil;
import defpackage.jei;
import health.compact.a.GRSManager;
import health.compact.a.Utils;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class ShareConfig {
    private static final String COUNTRY_CODE = "countryCode";
    private static final int DELAY_TIME = 3000;
    public static final int MSG_SHARE_FAIL_TOAST = 2099;
    private static final String PARAM_ACTIVITY_ID = "activityId";
    public static final String PARAM_ENVIRONMENT = "environment";
    private static final String PARAM_SHARE_TYPE = "shareType";
    private static final String SHARE_CONTENT = "shareContent";
    private static final String SHARE_IMG_URL = "shareImg";
    private static final String SHARE_TITLE = "shareTitle";
    private static final String SITE_ID = "siteId";
    private static final String TAG = "PluginOperation_[Operation Version 1.2]ShareConfig";
    private static long sToastTime;
    private Bitmap mBitmap;
    private Context mContext;
    private Handler mHandler;
    private ShareConfigCallback mObserver;
    private CommonDialog21 mLoadDataDialog = null;
    private String mShareImgUrl = "";
    private String mShareUrl = "";
    private String mShareTitle = "";
    private String mShareContent = "";

    public ShareConfig(Context context, Handler handler) {
        this.mContext = context;
        this.mHandler = handler;
    }

    public void obtainShareConfig(String str, String str2, ShareConfigCallback shareConfigCallback) {
        PluginBaseAdapter adapter = PluginOperation.getInstance(this.mContext).getAdapter();
        if (!(adapter instanceof PluginOperationAdapter)) {
            LogUtil.a(TAG, "obtainShareConfig adapter is null");
            return;
        }
        this.mObserver = shareConfigCallback;
        Map<String, String> info = ((PluginOperationAdapter) adapter).getInfo(new String[]{"getAppInfo", "getDeviceInfo", "getLoginInfo", Constants.METHOD_GET_USER_INFO, "getPhoneInfo"});
        String token = getToken(info);
        HashMap<String, String> hashMap = new HashMap<>(16);
        hashMap.put("token", token);
        hashMap.put("deviceType", info.get("deviceModel"));
        hashMap.put("appType", info.get("appType"));
        hashMap.put("ts", String.valueOf(System.currentTimeMillis()));
        hashMap.put("tokenType", OperationUtils.getTokenType());
        hashMap.put("appId", info.get("appId"));
        String str3 = info.get("deviceId");
        if (TextUtils.isEmpty(str3)) {
            str3 = "clientnull";
        }
        String str4 = info.get("iversion");
        String str5 = info.get("environment");
        hashMap.put("deviceId", str3);
        hashMap.put("sysVersion", info.get("sysVersion"));
        hashMap.put("bindDeviceType", info.get("productType"));
        hashMap.put("iVersion", str4);
        hashMap.put("language", info.get(FaqConstants.FAQ_LANGUAGE));
        hashMap.put("environment", String.valueOf(str5));
        hashMap.put("activityId", str);
        hashMap.put("shareType", str2);
        String commonCountryCode = GRSManager.a(this.mContext).getCommonCountryCode();
        hashMap.put("countryCode", commonCountryCode);
        String accountInfo = LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1009);
        if (Utils.o() && OperationUtils.isOperation(commonCountryCode)) {
            hashMap.put("siteId", accountInfo);
        }
        hashMap.put(CloudParamKeys.CLIENT_TYPE, eil.a());
        m758xa2bb106c();
        String str6 = info.get("huid");
        String str7 = info.get("grayVersion");
        HashMap<String, String> hashMap2 = new HashMap<>(2);
        hashMap2.put("x-huid", str6);
        hashMap2.put("x-version", str7);
        doPost(WebViewUtils.getActivityPortalUrlSp(this.mContext) + "/activity/getShareConfig", hashMap, hashMap2);
    }

    public void obtainShareConfig(String str, String str2, String str3, String str4, ShareConfigCallback shareConfigCallback) {
        LogUtil.a(TAG, "obtainShareConfig enter !");
        this.mObserver = shareConfigCallback;
        HandlerExecutor.e(new Runnable() { // from class: com.huawei.operation.share.ShareConfig$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                ShareConfig.this.m758xa2bb106c();
            }
        });
        this.mShareImgUrl = str;
        this.mShareTitle = str2;
        this.mShareContent = str3;
        this.mShareUrl = str4;
        new DownImgAsyncTask(this, null).execute(new String[0]);
    }

    /* renamed from: com.huawei.operation.share.ShareConfig$1, reason: invalid class name */
    class AnonymousClass1 implements HttpResCallback {
        AnonymousClass1() {
        }

        @Override // com.huawei.hwcommonmodel.utils.httputils.pluginoperation.HttpResCallback
        public void onFinished(int i, String str) {
            LogUtil.a(ShareConfig.TAG, "obtainShareConfig resCode = ", Integer.valueOf(i));
            if (i == 200) {
                LogUtil.c(ShareConfig.TAG, "obtainShareConfig success result = ", str);
                try {
                    JSONObject jSONObject = new JSONObject(str);
                    ShareConfig.this.mShareImgUrl = jSONObject.getString(ShareConfig.SHARE_IMG_URL);
                    ShareConfig.this.mShareTitle = jSONObject.getString("shareTitle");
                    ShareConfig.this.mShareContent = jSONObject.getString("shareContent");
                    ShareConfig.this.mShareUrl = jSONObject.getString("url");
                    new DownImgAsyncTask(ShareConfig.this, null).execute(new String[0]);
                    return;
                } catch (JSONException e) {
                    LogUtil.b(ShareConfig.TAG, e.getMessage());
                    return;
                }
            }
            if (i == -1 && Math.abs(System.currentTimeMillis() - ShareConfig.getToastTime()) > 3000) {
                ShareConfig.setToastTime(System.currentTimeMillis());
                LogUtil.a(ShareConfig.TAG, "onFinished MSG_SHARE_FAIL_TOAST");
                ShareConfig.this.mHandler.sendEmptyMessage(ShareConfig.MSG_SHARE_FAIL_TOAST);
            }
            HandlerExecutor.e(new Runnable() { // from class: com.huawei.operation.share.ShareConfig$1$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    ShareConfig.AnonymousClass1.this.m759lambda$onFinished$0$comhuaweioperationshareShareConfig$1();
                }
            });
        }

        /* renamed from: lambda$onFinished$0$com-huawei-operation-share-ShareConfig$1, reason: not valid java name */
        /* synthetic */ void m759lambda$onFinished$0$comhuaweioperationshareShareConfig$1() {
            ShareConfig.this.dismissWaitingDialog();
        }
    }

    private void doPost(String str, HashMap<String, String> hashMap, HashMap<String, String> hashMap2) {
        jei.d(str, hashMap, hashMap2, new AnonymousClass1());
    }

    class DownImgAsyncTask extends AsyncTask<String, Void, Bitmap> {
        private DownImgAsyncTask() {
        }

        /* synthetic */ DownImgAsyncTask(ShareConfig shareConfig, AnonymousClass1 anonymousClass1) {
            this();
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public Bitmap doInBackground(String... strArr) {
            Bitmap bGs_ = jei.bGs_(ShareConfig.this.mShareImgUrl);
            LogUtil.a(ShareConfig.TAG, "DownImgAsyncTask:optionBitmap bitmap = ", bGs_);
            return bGs_;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public void onPostExecute(Bitmap bitmap) {
            super.onPostExecute((DownImgAsyncTask) bitmap);
            LogUtil.a(ShareConfig.TAG, "bitmap = ", bitmap);
            ShareConfig.this.mBitmap = bitmap;
            if (ShareConfig.this.mBitmap == null) {
                ShareConfig shareConfig = ShareConfig.this;
                shareConfig.mBitmap = shareConfig.getAppIcon();
            }
            ShareConfig.this.dismissWaitingDialog();
            ShareConfig.this.callBackResult();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dismissWaitingDialog() {
        LogUtil.a(TAG, "closeLoadDataDialog: mLoadDataDialog = ", this.mLoadDataDialog);
        CommonDialog21 commonDialog21 = this.mLoadDataDialog;
        if (commonDialog21 != null) {
            commonDialog21.dismiss();
            this.mLoadDataDialog = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: showWaitingDialog, reason: merged with bridge method [inline-methods] */
    public void m758xa2bb106c() {
        LogUtil.a(TAG, "showWaitingDialog: mLoadDataDialog = ", this.mLoadDataDialog);
        if (this.mLoadDataDialog != null) {
            return;
        }
        new CommonDialog21(this.mContext, R.style.common_dialog21);
        CommonDialog21 a2 = CommonDialog21.a(this.mContext);
        this.mLoadDataDialog = a2;
        a2.e(this.mContext.getString(R.string._2130841415_res_0x7f020f47));
        this.mLoadDataDialog.setCancelable(false);
        this.mLoadDataDialog.a();
        this.mLoadDataDialog.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void callBackResult() {
        LogUtil.a(TAG, "callBackResult enter !");
        ShareConfigCallback shareConfigCallback = this.mObserver;
        if (shareConfigCallback != null) {
            shareConfigCallback.onShareConfig(this.mShareTitle, this.mShareContent, this.mBitmap, this.mShareUrl);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Bitmap getAppIcon() {
        Drawable loadIcon = this.mContext.getApplicationInfo().loadIcon(this.mContext.getPackageManager());
        if (loadIcon instanceof BitmapDrawable) {
            return ((BitmapDrawable) loadIcon).getBitmap();
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static long getToastTime() {
        return sToastTime;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void setToastTime(long j) {
        sToastTime = j;
    }

    private String getToken(Map<String, String> map) {
        if (!TextUtils.isEmpty(map.get("severToken"))) {
            try {
                return URLEncoder.encode(map.get("severToken"), StandardCharsets.UTF_8.name());
            } catch (UnsupportedEncodingException e) {
                LogUtil.b(TAG, "token encode Exception ", e.toString());
            }
        }
        return map.get("severToken");
    }
}
