package com.huawei.watchface.mvp.model.webview;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.text.TextUtils;
import android.webkit.JavascriptInterface;
import android.webkit.ValueCallback;
import android.webkit.WebView;
import com.huawei.hms.support.api.entity.pay.internal.BaseReq;
import com.huawei.hms.support.api.pay.PayResultInfo;
import com.huawei.operation.h5pro.jsmodules.device.DevicePairUtils;
import com.huawei.operation.utils.Constants;
import com.huawei.pluginachievement.manager.model.KakaConstants;
import com.huawei.secure.android.common.util.EncodeUtil;
import com.huawei.watchface.ab;
import com.huawei.watchface.ac;
import com.huawei.watchface.an;
import com.huawei.watchface.ao;
import com.huawei.watchface.api.HwWatchFaceApi;
import com.huawei.watchface.api.HwWatchFaceBtManager;
import com.huawei.watchface.api.HwWatchFaceManager;
import com.huawei.watchface.api.WebViewActivity;
import com.huawei.watchface.az;
import com.huawei.watchface.ba;
import com.huawei.watchface.bf;
import com.huawei.watchface.bg;
import com.huawei.watchface.cv;
import com.huawei.watchface.de;
import com.huawei.watchface.dh;
import com.huawei.watchface.dj;
import com.huawei.watchface.dp;
import com.huawei.watchface.dx;
import com.huawei.watchface.dz;
import com.huawei.watchface.ec;
import com.huawei.watchface.ef;
import com.huawei.watchface.environment.Environment;
import com.huawei.watchface.eo;
import com.huawei.watchface.fa;
import com.huawei.watchface.fj;
import com.huawei.watchface.flavor.FlavorConfig;
import com.huawei.watchface.l;
import com.huawei.watchface.manager.WatchFacePhotoAlbumManager;
import com.huawei.watchface.mvp.model.crypt.PversionSdUtils;
import com.huawei.watchface.mvp.model.datatype.CommandJsonInfo;
import com.huawei.watchface.mvp.model.datatype.CouponsByproductBean;
import com.huawei.watchface.mvp.model.datatype.DownloadQueryBean;
import com.huawei.watchface.mvp.model.datatype.InstallWatchFaceBean;
import com.huawei.watchface.mvp.model.datatype.PortraitImgInfo;
import com.huawei.watchface.mvp.model.datatype.VipOrderBean;
import com.huawei.watchface.mvp.model.datatype.VipOrderBeanForH5;
import com.huawei.watchface.mvp.model.datatype.VipPackageBean;
import com.huawei.watchface.mvp.model.datatype.WatchFaceAarAbility;
import com.huawei.watchface.mvp.model.datatype.querysubinfodetail.QuerySubInfoDetailResp;
import com.huawei.watchface.mvp.model.helper.GsonHelper;
import com.huawei.watchface.mvp.model.helper.JsSafeUrlSystemParamManager;
import com.huawei.watchface.mvp.model.info.VipPayInfo;
import com.huawei.watchface.mvp.model.info.VipPayInfoCoupons;
import com.huawei.watchface.mvp.model.latona.LatonaWatchFaceProvider;
import com.huawei.watchface.mvp.model.pay.JsPayResult;
import com.huawei.watchface.mvp.model.pay.JsPayVipNewResult;
import com.huawei.watchface.mvp.ui.activity.ClipImageActivity;
import com.huawei.watchface.mvp.ui.activity.WatchfaceUrlActivityV1;
import com.huawei.watchface.n;
import com.huawei.watchface.s;
import com.huawei.watchface.t;
import com.huawei.watchface.utils.ArrayUtils;
import com.huawei.watchface.utils.BackgroundTaskUtils;
import com.huawei.watchface.utils.CommonUtils;
import com.huawei.watchface.utils.DensityUtil;
import com.huawei.watchface.utils.HwLog;
import com.huawei.watchface.utils.IntegerUtils;
import com.huawei.watchface.utils.LanguageUtils;
import com.huawei.watchface.utils.PowerThemeHelper;
import com.huawei.watchface.utils.WebViewUtils;
import com.huawei.watchface.utils.callback.CloseWebCallback;
import com.huawei.watchface.utils.callback.DelectUserDesignationWatchFaceCallback;
import com.huawei.watchface.utils.callback.IH5LoadingCallback;
import com.huawei.watchface.utils.callback.OnWebViewStatusCallback;
import com.huawei.watchface.utils.callback.OperateWatchFaceCallback;
import com.huawei.watchface.utils.callback.PluginOperationAdapter;
import com.huawei.watchface.utils.callback.SendCurrentUrlCallback;
import com.huawei.watchface.utils.callback.SendNoNetMsgCallback;
import com.huawei.watchface.utils.callback.SendServerErrorMsgCallback;
import com.huawei.watchface.utils.callback.ToastCallback;
import com.huawei.watchface.utils.callback.ViewOnClickListener;
import com.huawei.watchface.utils.constants.WatchFaceConstant;
import com.huawei.watchface.utils.constants.WebViewConstant;
import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes7.dex */
public class JSInterface {
    public static final String RESULT_FAILED = "1";
    public static final String RESULT_FAILED_JSON_EXCEPTION = "-2";
    private static final String RESULT_FAILED_NETWORK_UNCONNECTED = "-1";
    private static final String RESULT_FAILED_NO_JS_INTERFACE_PERMISSION = "-3";
    public static final String RESULT_SUCCESS = "0";
    private static final String TAG = "JSInterface";
    protected PluginOperationAdapter mAdapter;
    private CloseWebCallback mCloseWebCallback;
    protected Context mContext;
    private DelectUserDesignationWatchFaceCallback mDelectUserDesignationWatchFaceCallback;
    private long mLastTransmitWatchInfoChange = 0;
    private IH5LoadingCallback mLoadingCallback;
    private OnWebViewStatusCallback mOnWebViewStatusCallback;
    protected OperateWatchFaceCallback mOperateWatchFaceCallback;
    protected SendCurrentUrlCallback mSendCurrentUrlCallback;
    private SendNoNetMsgCallback mSendNoNetMsgCallback;
    private SendServerErrorMsgCallback mSendServerErrorMsgCallback;
    private ToastCallback mToastCallback;
    protected WebView mWebview;

    public JSInterface(Context context) {
        this.mContext = context;
        this.mAdapter = (PluginOperationAdapter) ba.a(context).getAdapter();
    }

    public void setTouchCallback(OnWebViewStatusCallback onWebViewStatusCallback) {
        this.mOnWebViewStatusCallback = onWebViewStatusCallback;
    }

    public void setWidgetCallback(ToastCallback toastCallback) {
        this.mToastCallback = toastCallback;
    }

    public void setH5LoadingCallback(IH5LoadingCallback iH5LoadingCallback) {
        this.mLoadingCallback = iH5LoadingCallback;
    }

    public void setSendCallback(SendServerErrorMsgCallback sendServerErrorMsgCallback, SendNoNetMsgCallback sendNoNetMsgCallback, SendCurrentUrlCallback sendCurrentUrlCallback) {
        this.mSendServerErrorMsgCallback = sendServerErrorMsgCallback;
        this.mSendNoNetMsgCallback = sendNoNetMsgCallback;
        this.mSendCurrentUrlCallback = sendCurrentUrlCallback;
    }

    public void setWebView(WebView webView) {
        this.mWebview = webView;
        JsInteractAddition.getInstance().a(webView);
    }

    public void setMagicWebView(WebView webView) {
        this.mWebview = webView;
        JsInteractAddition.getInstance().b(webView);
    }

    public void setStarAndEndCallback(CloseWebCallback closeWebCallback) {
        this.mCloseWebCallback = closeWebCallback;
    }

    public void setOperateWatchFaceCallback(OperateWatchFaceCallback operateWatchFaceCallback, DelectUserDesignationWatchFaceCallback delectUserDesignationWatchFaceCallback) {
        this.mOperateWatchFaceCallback = operateWatchFaceCallback;
        this.mDelectUserDesignationWatchFaceCallback = delectUserDesignationWatchFaceCallback;
    }

    @JavascriptInterface
    public void toast(String str, String str2) {
        ToastCallback toastCallback;
        HwLog.i(TAG, "toast content:" + str + ", time:" + str2);
        if (JsSafeUrlSystemParamManager.getInstance().isInURLWhiteList(this.mWebview) && (toastCallback = this.mToastCallback) != null) {
            toastCallback.onToast(str, str2);
        }
    }

    @JavascriptInterface
    public void closeWeb() {
        CloseWebCallback closeWebCallback;
        HwLog.i(TAG, "closeWeb");
        if (JsSafeUrlSystemParamManager.getInstance().isInURLWhiteList(this.mWebview) && (closeWebCallback = this.mCloseWebCallback) != null) {
            closeWebCallback.onCloseWebCallback();
        }
    }

    @JavascriptInterface
    public void sendServerErrorMsg() {
        String str = TAG;
        HwLog.i(str, "sendServerErrorMsg");
        if (JsSafeUrlSystemParamManager.getInstance().isInURLWhiteList(this.mWebview) && this.mSendServerErrorMsgCallback != null) {
            HwLog.i(str, "sendServerErrorMsg mSendServerErrorMsgCallback != null");
            this.mSendServerErrorMsgCallback.onSendServerErrorMsgCallback();
        }
    }

    @JavascriptInterface
    public void sendWatchFaceServerRetryMsg() {
        String str = TAG;
        HwLog.i(str, "sendWatchFaceServerRetryMsg");
        if (JsSafeUrlSystemParamManager.getInstance().isInURLWhiteList(this.mWebview) && this.mSendServerErrorMsgCallback != null) {
            HwLog.i(str, "sendServerErrorMsg mSendServerErrorMsgCallback != null");
            this.mSendServerErrorMsgCallback.onSendWatchFaceServerRetryCallback();
        }
    }

    @JavascriptInterface
    public void shareWatchface(String str) {
        HwLog.i(TAG, "shareLink AAR Module enter.");
        if (JsSafeUrlSystemParamManager.getInstance().isInURLWhiteList(this.mWebview)) {
            try {
                HwWatchFaceApi.getInstance(this.mContext).shareLink(str);
            } catch (Exception e) {
                HwLog.e(TAG, "shareWatchface -- JSONException" + HwLog.printException(e));
            }
        }
    }

    @JavascriptInterface
    public void updateAllWifiCheck() {
        String str = TAG;
        HwLog.i(str, "updateAllWifiCheck AAR Module enter.");
        if (JsSafeUrlSystemParamManager.getInstance().isInURLWhiteList(this.mWebview)) {
            if (this.mOperateWatchFaceCallback != null) {
                if (CommonUtils.r() == 0) {
                    this.mOperateWatchFaceCallback.transmitDownloadWatchFaceResult(null, null, 1003);
                    return;
                } else if (CommonUtils.r() == 1) {
                    this.mOperateWatchFaceCallback.transmitDownloadWatchFaceResult(null, null, 1004);
                    return;
                } else {
                    HwLog.i(str, "showNetworkWarningPopup no Internet connection");
                    return;
                }
            }
            HwLog.i(str, "mOperateWatchFaceCallback null");
        }
    }

    @JavascriptInterface
    public void sendWatchFaceServerErrorMsg() {
        String str = TAG;
        HwLog.i(str, "sendWatchFaceServerErrorMsg");
        if (JsSafeUrlSystemParamManager.getInstance().isInURLWhiteList(this.mWebview) && this.mSendServerErrorMsgCallback != null) {
            HwLog.i(str, "sendServerErrorMsg mSendServerErrorMsgCallback != null");
            this.mSendServerErrorMsgCallback.onSendWatchFaceServerErrorCallback();
        }
    }

    @JavascriptInterface
    public void sendNoNetMsg() {
        String str = TAG;
        HwLog.i(str, "sendNoNetMsg");
        if (JsSafeUrlSystemParamManager.getInstance().isInURLWhiteList(this.mWebview)) {
            if (CommonUtils.t()) {
                HwLog.i(str, "sendNoNetMsg is backGround");
            } else if (this.mSendNoNetMsgCallback != null) {
                HwLog.i(str, "sendNoNetMsg mSendNoNetMsgCallback != null");
                this.mSendNoNetMsgCallback.onSendNoNetMsgCallback();
            }
        }
    }

    @JavascriptInterface
    public boolean isNetworkConnected() {
        HwLog.i(TAG, "isNetworkConnected");
        if (JsSafeUrlSystemParamManager.getInstance().isInURLWhiteList(this.mWebview)) {
            return CommonUtils.f();
        }
        return false;
    }

    @JavascriptInterface
    public boolean isBigCD() {
        if (JsSafeUrlSystemParamManager.getInstance().isInURLWhiteList(this.mWebview)) {
            return JsInteractAddition.getInstance().a(this.mSendCurrentUrlCallback, this.mAdapter);
        }
        return false;
    }

    @JavascriptInterface
    public String getWatchFaceInfo() {
        String str = TAG;
        HwLog.i(str, "getWatchFaceInfo enter.");
        if (!JsSafeUrlSystemParamManager.getInstance().isInURLWhiteList(this.mWebview)) {
            return "";
        }
        PluginOperationAdapter pluginOperationAdapter = this.mAdapter;
        String watchFaceInfo = pluginOperationAdapter != null ? pluginOperationAdapter.getWatchFaceInfo() : "";
        HwLog.iBetaLog(str, "getWatchFaceInfo() watchFaceInfo: " + watchFaceInfo);
        return watchFaceInfo;
    }

    @JavascriptInterface
    public String getCurrentWatchFaceInfo() {
        String str = TAG;
        HwLog.i(str, "getCurrentWatchFaceInfo enter.");
        if (!JsSafeUrlSystemParamManager.getInstance().isInURLWhiteList(this.mWebview)) {
            return "";
        }
        PluginOperationAdapter pluginOperationAdapter = this.mAdapter;
        String watchFaceInfo = pluginOperationAdapter != null ? pluginOperationAdapter.getWatchFaceInfo() : "";
        if (TextUtils.equals(watchFaceInfo, "1") || TextUtils.equals(watchFaceInfo, "0") || TextUtils.equals(watchFaceInfo, "")) {
            HwLog.d(str, "getCurrentWatchFaceInfo from sp");
            watchFaceInfo = ao.b(dp.a("watchface_info_key", "themename"), "storagePw");
        }
        String str2 = TextUtils.isEmpty(watchFaceInfo) ? "0" : watchFaceInfo;
        HwLog.i(str, "getCurrentWatchFaceInfo() watchFaceInfo: " + str2);
        return str2;
    }

    @JavascriptInterface
    public String getDisableWearInfo() {
        String str = TAG;
        HwLog.i(str, "getDisableWearInfo enter.");
        if (!JsSafeUrlSystemParamManager.getInstance().isInURLWhiteList(this.mWebview)) {
            HwLog.i(str, "getDisableWearInfo isInURLWhiteList");
            return "";
        }
        return HwWatchFaceApi.getInstance(this.mContext).getDisableWearInfo();
    }

    @JavascriptInterface
    public String getFirmware() {
        if (!JsSafeUrlSystemParamManager.getInstance().isInURLWhiteList(this.mWebview)) {
            return "";
        }
        PluginOperationAdapter pluginOperationAdapter = this.mAdapter;
        String firmware = pluginOperationAdapter != null ? pluginOperationAdapter.getFirmware() : "";
        HwLog.i(TAG, "getFirmware return:" + firmware);
        return firmware;
    }

    @JavascriptInterface
    public String getLocale() {
        if (!JsSafeUrlSystemParamManager.getInstance().isInURLWhiteList(this.mWebview)) {
            return "";
        }
        PluginOperationAdapter pluginOperationAdapter = this.mAdapter;
        String locale = pluginOperationAdapter != null ? pluginOperationAdapter.getLocale() : "";
        HwLog.i(TAG, "getLocale return:" + locale);
        return locale;
    }

    @JavascriptInterface
    public String getIsoCode() {
        if (!JsSafeUrlSystemParamManager.getInstance().isInURLWhiteList(this.mWebview)) {
            return "";
        }
        PluginOperationAdapter pluginOperationAdapter = this.mAdapter;
        String locale = pluginOperationAdapter != null ? pluginOperationAdapter.getLocale() : "";
        HwLog.i(TAG, "getLocale retStr: " + locale);
        return locale;
    }

    @JavascriptInterface
    public String getScreenSize() {
        if (!JsSafeUrlSystemParamManager.getInstance().isInURLWhiteList(this.mWebview)) {
            return "";
        }
        PluginOperationAdapter pluginOperationAdapter = this.mAdapter;
        String screenSize = pluginOperationAdapter != null ? pluginOperationAdapter.getScreenSize() : "";
        HwLog.i(TAG, "getScreenSize screenSize: " + screenSize);
        return screenSize;
    }

    @JavascriptInterface
    public String getPhoneWatchType() {
        if (!JsSafeUrlSystemParamManager.getInstance().isInURLWhiteList(this.mWebview)) {
            return "";
        }
        PluginOperationAdapter pluginOperationAdapter = this.mAdapter;
        String phoneWatchType = pluginOperationAdapter != null ? pluginOperationAdapter.getPhoneWatchType() : "";
        HwLog.i(TAG, "getPhoneWatchType retStr: " + phoneWatchType);
        return phoneWatchType;
    }

    @JavascriptInterface
    public String getBuildNumber() {
        if (!JsSafeUrlSystemParamManager.getInstance().isInURLWhiteList(this.mWebview)) {
            return "";
        }
        PluginOperationAdapter pluginOperationAdapter = this.mAdapter;
        String buildNumber = pluginOperationAdapter != null ? pluginOperationAdapter.getBuildNumber() : "";
        HwLog.i(TAG, "getBuildNumber return: " + buildNumber);
        return buildNumber;
    }

    @JavascriptInterface
    public String getWatchThemeVersion() {
        if (!JsSafeUrlSystemParamManager.getInstance().isInURLWhiteList(this.mWebview)) {
            return "";
        }
        PluginOperationAdapter pluginOperationAdapter = this.mAdapter;
        String watchThemeVersion = pluginOperationAdapter != null ? pluginOperationAdapter.getWatchThemeVersion() : "";
        HwLog.i(TAG, "getWatchThemeVersion retStr: " + watchThemeVersion);
        return watchThemeVersion;
    }

    @JavascriptInterface
    public String getWatchOtherThemeVersion() {
        if (!JsSafeUrlSystemParamManager.getInstance().isInURLWhiteList(this.mWebview)) {
            return "";
        }
        PluginOperationAdapter pluginOperationAdapter = this.mAdapter;
        String watchOtherThemeVersion = pluginOperationAdapter != null ? pluginOperationAdapter.getWatchOtherThemeVersion() : "";
        HwLog.i(TAG, "getWatchOtherThemeVersion otherRetStr: " + watchOtherThemeVersion);
        return watchOtherThemeVersion;
    }

    @JavascriptInterface
    public String getWatchFileType() {
        if (!JsSafeUrlSystemParamManager.getInstance().isInURLWhiteList(this.mWebview)) {
            return "";
        }
        PluginOperationAdapter pluginOperationAdapter = this.mAdapter;
        String watchFileType = pluginOperationAdapter != null ? pluginOperationAdapter.getWatchFileType() : "";
        HwLog.i(TAG, "getWatchFileType return:" + watchFileType);
        return watchFileType;
    }

    @JavascriptInterface
    public String getCurrentWatchFaceId() {
        if (!JsSafeUrlSystemParamManager.getInstance().isInURLWhiteList(this.mWebview)) {
            return "";
        }
        PluginOperationAdapter pluginOperationAdapter = this.mAdapter;
        String currentWatchFaceId = pluginOperationAdapter != null ? pluginOperationAdapter.getCurrentWatchFaceId() : "";
        HwLog.i(TAG, "getCurrentWatchFaceId retStr: " + currentWatchFaceId);
        return currentWatchFaceId;
    }

    @JavascriptInterface
    public String getPreWatchFaceIdStore() {
        if (!JsSafeUrlSystemParamManager.getInstance().isInURLWhiteList(this.mWebview)) {
            return "";
        }
        PluginOperationAdapter pluginOperationAdapter = this.mAdapter;
        String preWatchFaceIdStore = pluginOperationAdapter != null ? pluginOperationAdapter.getPreWatchFaceIdStore() : "";
        HwLog.i(TAG, "getPreWatchFaceIdStore retStr:" + preWatchFaceIdStore);
        return preWatchFaceIdStore;
    }

    @JavascriptInterface
    public String getWatchFaceIdStore() {
        if (!JsSafeUrlSystemParamManager.getInstance().isInURLWhiteList(this.mWebview)) {
            return "";
        }
        PluginOperationAdapter pluginOperationAdapter = this.mAdapter;
        String watchFaceIdStore = pluginOperationAdapter != null ? pluginOperationAdapter.getWatchFaceIdStore() : "";
        HwLog.i(TAG, "getWatchFaceIdStore retStr: " + watchFaceIdStore);
        return watchFaceIdStore;
    }

    @JavascriptInterface
    public String sendStartDownload(String str) {
        String str2 = TAG;
        HwLog.w(str2, "sendStartDownload enter");
        if (!JsSafeUrlSystemParamManager.getInstance().isInSensitiveURLWhiteList(this.mWebview, "sendStartDownload")) {
            return "-3";
        }
        long currentTimeMillis = System.currentTimeMillis();
        DownloadQueryBean downloadQueryBean = (DownloadQueryBean) GsonHelper.fromJson(str, DownloadQueryBean.class);
        if (downloadQueryBean != null) {
            if (downloadQueryBean.isUpdate() || Math.abs(currentTimeMillis - this.mLastTransmitWatchInfoChange) >= 500) {
                this.mLastTransmitWatchInfoChange = currentTimeMillis;
                if (downloadQueryBean.isVipFreeWatchFace()) {
                    HwLog.i(str2, "sendStartDownload--downloadInfoJson== isVipFreeWatchFace");
                    ac.a().a(CommonUtils.B());
                }
                if (this.mAdapter != null) {
                    return JsInteractAddition.getInstance().a(this.mAdapter, downloadQueryBean);
                }
            } else {
                HwLog.w(str2, "sendStartDownload error:Short time interval");
                return "1";
            }
        }
        return "1";
    }

    @JavascriptInterface
    public void gotoPlugin(final String str, final String str2, String str3) {
        HwLog.i(TAG, "gotoPlugin...1" + str + str3);
        ((Activity) this.mContext).runOnUiThread(new Runnable() { // from class: com.huawei.watchface.mvp.model.webview.JSInterface.1
            @Override // java.lang.Runnable
            public void run() {
                HwLog.i(JSInterface.TAG, "gotoPlugin...2");
                HwWatchFaceApi.getInstance(JSInterface.this.mContext).openPlugin((Activity) JSInterface.this.mContext, str, str2);
            }
        });
    }

    @JavascriptInterface
    public String isPluginSupported() {
        HwLog.i(TAG, "isPluginSupported");
        return "1";
    }

    @JavascriptInterface
    public String isCloudConfigSupportedFreeTab() {
        HwLog.i(TAG, "isCloudConfigSupportedFreeTab() -- enter");
        return CommonUtils.C();
    }

    @JavascriptInterface
    public void setDefaultWatchFace(String str, String str2) {
        setDefaultWatchFace(str, str2, "");
    }

    @JavascriptInterface
    public void setDefaultWatchFace(String str, String str2, String str3) {
        if (JsSafeUrlSystemParamManager.getInstance().isInSensitiveURLWhiteList(this.mWebview, "setDefaultWatchFace")) {
            HwLog.i(TAG, "setDefaultWatchFace hiTopId: " + str + " version: " + str2 + ", watchScreen: " + str3);
            if (this.mAdapter != null) {
                dz.b(String.valueOf(System.currentTimeMillis()));
                this.mAdapter.setDefaultWatchFace(str, str2, str3);
            }
        }
    }

    @JavascriptInterface
    public void cancelInstallWatchFace(String str, String str2) {
        HwLog.i(TAG, "cancelInstallWatchFace");
        JsInteractAddition.getInstance().c(this.mContext, this.mSendCurrentUrlCallback, this.mAdapter, str, str2);
    }

    @JavascriptInterface
    public void deleteWatchFace(String str, String str2) {
        JsInteractAddition.getInstance().b(this.mContext, this.mSendCurrentUrlCallback, this.mAdapter, str, str2);
    }

    @JavascriptInterface
    public void setWatchFaceDeleteButton(String str, String str2) {
        JsInteractAddition.getInstance().a(this.mContext, this.mSendCurrentUrlCallback, this.mAdapter, str, str2);
    }

    @JavascriptInterface
    public String getWatchFaceUrlBase() {
        return JsInteractAddition.getInstance().d(this.mContext, this.mSendCurrentUrlCallback, this.mAdapter);
    }

    @JavascriptInterface
    public void getWatchFaceNames(String str) {
        HwLog.i(TAG, "getWatchFaceNames");
        JsInteractAddition.getInstance().a(this.mContext, this.mSendCurrentUrlCallback, this.mAdapter, str);
    }

    @JavascriptInterface
    public int getWatchFaceInstallState() {
        HwLog.i(TAG, "getWatchFaceNames");
        return JsInteractAddition.getInstance().c(this.mContext, this.mSendCurrentUrlCallback, this.mAdapter);
    }

    @JavascriptInterface
    public void chooseWearPicToClip() {
        HwLog.i(TAG, WebViewConstant.InterfaceName.INTERFACE_CHOOSE_WEAR_TO_CLIP);
        JsInteractAddition.getInstance().a(this.mContext, this.mSendCurrentUrlCallback, this.mAdapter);
    }

    @JavascriptInterface
    public void getWatchFaceThemeWearAlbumInfo(String str, String str2, String str3, String str4) {
        if (str == null || str2 == null || str3 == null) {
            return;
        }
        HashMap<String, String> hashMap = new HashMap<>(32);
        hashMap.put(JsInteractAddition.HI_TOP_ID, str);
        hashMap.put("version", str2);
        hashMap.put("url", str3);
        hashMap.put(WatchFaceConstant.HASHCODE, str4);
        JsInteractAddition.getInstance().a(this.mContext, this.mSendCurrentUrlCallback, this.mAdapter, hashMap);
    }

    @JavascriptInterface
    public void saveWatchFaceThemeWearAlbumInfo(String str) {
        JsInteractAddition.getInstance().b(this.mContext, this.mSendCurrentUrlCallback, this.mAdapter, str);
    }

    @JavascriptInterface
    public String getAlbumPackageName() {
        return JsInteractAddition.getInstance().b(this.mContext, this.mSendCurrentUrlCallback, this.mAdapter);
    }

    @JavascriptInterface
    public int getSoftKeyboardHeight() {
        OperateWatchFaceCallback operateWatchFaceCallback;
        HwLog.i(TAG, "getSoftKeyboardHeight");
        if (JsSafeUrlSystemParamManager.getInstance().isInURLWhiteList(this.mWebview) && (operateWatchFaceCallback = this.mOperateWatchFaceCallback) != null) {
            return operateWatchFaceCallback.getSoftKeyboardHeight();
        }
        return 0;
    }

    @JavascriptInterface
    public void getWatchFacePayInfo(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8) {
        HwLog.i(TAG, "getWatchFacePayInfo enter .");
        getWatchFacePayInfo(str, str2, str3, str4, str5, str6, str7, str8, null, false);
    }

    @JavascriptInterface
    public void getWatchFacePayInfo(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, boolean z) {
        HwLog.i(TAG, "getWatchFacePayInfo() hiTopId: " + str2 + ", version: " + str3 + ", price: " + str4 + ", symbolType: " + str6);
        if (JsSafeUrlSystemParamManager.getInstance().isInSensitiveURLWhiteList(this.mWebview, "getWatchFacePayInfo") && this.mAdapter != null) {
            this.mAdapter.getWatchFacePayInfo(new InstallWatchFaceBean(str2, str3, str, str4, str5, str6, str7, str9, z, 2));
        }
    }

    @JavascriptInterface
    public void reportVipPackageInfo(String str) {
        String str2 = TAG;
        HwLog.i(str2, "reportVipPackageInfo enter");
        if (!JsSafeUrlSystemParamManager.getInstance().isInSensitiveURLWhiteList(this.mWebview, "payForVip")) {
            HwLog.e(str2, "reportVipPackageInfo() !isInSensitiveURLWhiteList.");
            return;
        }
        VipPayInfoCoupons vipPayInfoCoupons = (VipPayInfoCoupons) GsonHelper.fromJson(str, VipPayInfoCoupons.class);
        if (vipPayInfoCoupons == null) {
            HwLog.i(str2, "vipPayInfoCoupons is null");
        } else {
            s.a(this.mContext).a(vipPayInfoCoupons);
        }
    }

    @JavascriptInterface
    public void payForVip(String str, final String str2) {
        String str3 = TAG;
        HwLog.i(str3, "payForVip enter ");
        if (!JsSafeUrlSystemParamManager.getInstance().isInSensitiveURLWhiteList(this.mWebview, "payForVip")) {
            HwLog.e(str3, "payForVip() !isInSensitiveURLWhiteList.");
            return;
        }
        if (ViewOnClickListener.isSeriesClick()) {
            HwLog.i(str3, "payForVip isSeriesClick");
            return;
        }
        final JsPayResult jsPayResult = new JsPayResult();
        if (TextUtils.isEmpty(str)) {
            HwLog.i(str3, "payForVip payReqJson is null");
            jsPayResult.setResultCode(-1);
            callH5PayResult(jsPayResult, str2);
            return;
        }
        VipPayInfo vipPayInfo = (VipPayInfo) GsonHelper.fromJson(str, VipPayInfo.class);
        if (vipPayInfo == null) {
            HwLog.i(str3, "pay payReq is null");
            jsPayResult.setResultCode(-1);
            callH5PayResult(jsPayResult, str2);
        } else {
            if (CommonUtils.i(vipPayInfo.getAmount())) {
                t.a().c();
                return;
            }
            BaseReq withholdRequest = an.b(vipPayInfo.getTradeType()) ^ true ? vipPayInfo.getWithholdRequest(vipPayInfo) : vipPayInfo.getPayInfoReq(vipPayInfo);
            PluginOperationAdapter pluginOperationAdapter = this.mAdapter;
            if (pluginOperationAdapter != null) {
                pluginOperationAdapter.payForVip(withholdRequest, new bf() { // from class: com.huawei.watchface.mvp.model.webview.JSInterface.2
                    @Override // com.huawei.watchface.bf
                    public void onResult(int i, PayResultInfo payResultInfo) {
                        HwLog.d(JSInterface.TAG, "payForVip  resultCode = " + i);
                        jsPayResult.setResultCode(i);
                        jsPayResult.setPayResultInfo(payResultInfo);
                        HwLog.d(JSInterface.TAG, "mAdapter payForVip --before callH5PayResult");
                        JSInterface.this.callH5PayResult(jsPayResult, str2);
                    }
                });
            }
        }
    }

    @JavascriptInterface
    public void payForVipNew(String str, final String str2, final String str3) {
        String str4 = TAG;
        HwLog.i(str4, "payForVipNew enter ");
        if (!JsSafeUrlSystemParamManager.getInstance().isInSensitiveURLWhiteList(this.mWebview, "payForVip")) {
            HwLog.e(str4, "payForVipNew() !isInSensitiveURLWhiteList.");
            return;
        }
        if (ViewOnClickListener.isSeriesClick()) {
            HwLog.i(str4, "payForVipNew isSeriesClick");
            return;
        }
        final JsPayVipNewResult jsPayVipNewResult = new JsPayVipNewResult();
        if (TextUtils.isEmpty(str)) {
            HwLog.i(str4, "payForVipNew payReqJson is null");
            jsPayVipNewResult.setResultCode(-1);
            callH5PayResult(jsPayVipNewResult, str3);
            return;
        }
        VipPayInfoCoupons vipPayInfoCoupons = (VipPayInfoCoupons) GsonHelper.fromJson(str, VipPayInfoCoupons.class);
        if (vipPayInfoCoupons == null) {
            HwLog.i(str4, "payForVipNew vipPayJson is null");
            jsPayVipNewResult.setResultCode(-1);
            callH5PayResult(jsPayVipNewResult, str3);
        } else {
            jsPayVipNewResult.setVipPayInfoCoupons(vipPayInfoCoupons);
            s.a(this.mContext).b(vipPayInfoCoupons.getProductCode());
            PluginOperationAdapter pluginOperationAdapter = this.mAdapter;
            if (pluginOperationAdapter != null) {
                pluginOperationAdapter.payForVipNew(vipPayInfoCoupons, new bf() { // from class: com.huawei.watchface.mvp.model.webview.JSInterface$$ExternalSyntheticLambda3
                    @Override // com.huawei.watchface.bf
                    public final void onResult(int i, PayResultInfo payResultInfo) {
                        JSInterface.this.m913x30e2c1ee(jsPayVipNewResult, str3, i, payResultInfo);
                    }
                }, new bg() { // from class: com.huawei.watchface.mvp.model.webview.JSInterface$$ExternalSyntheticLambda4
                    @Override // com.huawei.watchface.bg
                    public final void onResult(VipOrderBean vipOrderBean, VipPackageBean vipPackageBean) {
                        JSInterface.this.m914x5ebb5c4d(str2, vipOrderBean, vipPackageBean);
                    }
                });
            }
        }
    }

    /* renamed from: lambda$payForVipNew$0$com-huawei-watchface-mvp-model-webview-JSInterface, reason: not valid java name */
    /* synthetic */ void m913x30e2c1ee(JsPayVipNewResult jsPayVipNewResult, String str, int i, PayResultInfo payResultInfo) {
        HwLog.i(TAG, "payForVipNew resultCode:" + i);
        if (i != 0) {
            s.a(Environment.getApplicationContext()).a();
        }
        jsPayVipNewResult.setResultCode(i);
        if (payResultInfo == null) {
            jsPayVipNewResult.setReturnCode(i);
        } else {
            jsPayVipNewResult.setPayResultInfo(payResultInfo);
        }
        callH5PayResult(jsPayVipNewResult, str);
    }

    /* renamed from: lambda$payForVipNew$1$com-huawei-watchface-mvp-model-webview-JSInterface, reason: not valid java name */
    /* synthetic */ void m914x5ebb5c4d(String str, VipOrderBean vipOrderBean, VipPackageBean vipPackageBean) {
        HwLog.i(TAG, "payForVipNew vipOrderResult callback");
        callH5VipOrderResult(vipOrderBean, vipPackageBean, str);
    }

    @JavascriptInterface
    public void payForVipByCoupons(String str) {
        String str2 = TAG;
        HwLog.i(str2, "payForVipByCoupons enter, couponStr");
        if (!JsSafeUrlSystemParamManager.getInstance().isInSensitiveURLWhiteList(this.mWebview, "payForVip")) {
            HwLog.e(str2, "payForVipByCoupons() !isInSensitiveURLWhiteList.");
        } else if (ViewOnClickListener.isSeriesClick()) {
            HwLog.i(str2, "payForVipByCoupons isSeriesClick");
        } else {
            final CouponsByproductBean.CouponAndPrice couponAndPrice = !TextUtils.isEmpty(str) ? (CouponsByproductBean.CouponAndPrice) GsonHelper.fromJson(str, CouponsByproductBean.CouponAndPrice.class) : null;
            BackgroundTaskUtils.submit(new Runnable() { // from class: com.huawei.watchface.mvp.model.webview.JSInterface$$ExternalSyntheticLambda6
                @Override // java.lang.Runnable
                public final void run() {
                    JSInterface.this.m912x96f5c616(couponAndPrice);
                }
            });
        }
    }

    /* renamed from: lambda$payForVipByCoupons$2$com-huawei-watchface-mvp-model-webview-JSInterface, reason: not valid java name */
    /* synthetic */ void m912x96f5c616(CouponsByproductBean.CouponAndPrice couponAndPrice) {
        s.a(this.mContext).a(couponAndPrice);
    }

    @JavascriptInterface
    public String getCouponsBeanStr() {
        String str = TAG;
        HwLog.i(str, "getCouponsStr enter ");
        if (!JsSafeUrlSystemParamManager.getInstance().isInURLWhiteList(this.mWebview)) {
            HwLog.i(str, "getCouponsStr is not InURLWhiteList ");
            return "";
        }
        return s.a(this.mContext).h();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void callH5PayResult(JsPayResult jsPayResult, String str) {
        try {
            if (this.mWebview == null || TextUtils.isEmpty(str)) {
                return;
            }
            final String str2 = Constants.JAVA_SCRIPT + EncodeUtil.encodeForJavaScript(str) + Constants.LEFT_BRACKET_ONLY + dx.a().a(jsPayResult) + Constants.RIGHT_BRACKET_ONLY;
            FlavorConfig.safeHwLog(TAG, str2);
            this.mWebview.post(new Runnable() { // from class: com.huawei.watchface.mvp.model.webview.JSInterface$$ExternalSyntheticLambda5
                @Override // java.lang.Runnable
                public final void run() {
                    JSInterface.this.m910xafdac8ce(str2);
                }
            });
        } catch (Exception e) {
            HwLog.i(TAG, "callH5PayResult Exception: " + HwLog.printException(e));
        }
    }

    /* renamed from: lambda$callH5PayResult$4$com-huawei-watchface-mvp-model-webview-JSInterface, reason: not valid java name */
    /* synthetic */ void m910xafdac8ce(String str) {
        WebView webView = this.mWebview;
        if (webView != null) {
            webView.evaluateJavascript(str, new ValueCallback() { // from class: com.huawei.watchface.mvp.model.webview.JSInterface$$ExternalSyntheticLambda7
                @Override // android.webkit.ValueCallback
                public final void onReceiveValue(Object obj) {
                    HwLog.i(JSInterface.TAG, "callH5PayResult start");
                }
            });
        }
    }

    private void callH5VipOrderResult(VipOrderBean vipOrderBean, VipPackageBean vipPackageBean, String str) {
        try {
            if (this.mWebview == null || TextUtils.isEmpty(str)) {
                return;
            }
            String encodeForJavaScript = EncodeUtil.encodeForJavaScript(str);
            VipOrderBeanForH5 vipOrderBeanForH5 = new VipOrderBeanForH5();
            vipOrderBeanForH5.setVipOrderBean(vipOrderBean);
            vipOrderBeanForH5.setVipPackageBean(vipPackageBean);
            final String str2 = Constants.JAVA_SCRIPT + encodeForJavaScript + Constants.LEFT_BRACKET_ONLY + dx.a().a(vipOrderBeanForH5) + Constants.RIGHT_BRACKET_ONLY;
            FlavorConfig.safeHwLog(TAG, str2);
            this.mWebview.post(new Runnable() { // from class: com.huawei.watchface.mvp.model.webview.JSInterface$$ExternalSyntheticLambda2
                @Override // java.lang.Runnable
                public final void run() {
                    JSInterface.this.m911x14858193(str2);
                }
            });
        } catch (Exception e) {
            HwLog.i(TAG, "callH5VipOrderResult Exception: " + HwLog.printException(e));
        }
    }

    /* renamed from: lambda$callH5VipOrderResult$6$com-huawei-watchface-mvp-model-webview-JSInterface, reason: not valid java name */
    /* synthetic */ void m911x14858193(String str) {
        WebView webView = this.mWebview;
        if (webView != null) {
            webView.evaluateJavascript(str, new ValueCallback() { // from class: com.huawei.watchface.mvp.model.webview.JSInterface$$ExternalSyntheticLambda1
                @Override // android.webkit.ValueCallback
                public final void onReceiveValue(Object obj) {
                    HwLog.i(JSInterface.TAG, "callH5VipOrderResult start");
                }
            });
        }
    }

    @JavascriptInterface
    public void getWatchFacePayInfo(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9) {
        getWatchFacePayInfo(str, str2, str3, str4, str5, str6, str7, str8, str9, false);
    }

    @JavascriptInterface
    public void getWatchFacePayInfo(String str, String str2, String str3, String str4, String str5, String str6, String str7) {
        getWatchFacePayInfo(str, str2, str3, str4, str5, str6, str7, "");
    }

    @JavascriptInterface
    public void getWatchFacePayInfo(String str, String str2, String str3, String str4, String str5, String str6) {
        getWatchFacePayInfo(str, str2, str3, str4, str5, str6, "", "");
    }

    @JavascriptInterface
    public String getTokenAndDeviceType() {
        return JsInteractAddition.getInstance().e(this.mContext, this.mSendCurrentUrlCallback, this.mAdapter);
    }

    @JavascriptInterface
    public String getWatchFaceData() {
        HwLog.i(TAG, "getWatchFaceData");
        return JsInteractAddition.getInstance().d(this.mAdapter);
    }

    @JavascriptInterface
    public String getWatchFaceSort() {
        HwLog.i(TAG, "getWatchFaceSort");
        return JsInteractAddition.getInstance().b(this.mAdapter);
    }

    @JavascriptInterface
    public String getWatchFaceSortList() {
        HwLog.i(TAG, "getWatchFaceSortList");
        return JsInteractAddition.getInstance().c(this.mAdapter);
    }

    @JavascriptInterface
    public void setWatchFaceSortList(String str) {
        HwLog.i(TAG, "setWatchFaceSortList=" + str);
        JsInteractAddition.getInstance().a(str, this.mAdapter);
    }

    @JavascriptInterface
    public void setWatchFaceConfirmButton() {
        OperateWatchFaceCallback operateWatchFaceCallback;
        HwLog.i(TAG, "setWatchFaceConfirmButton");
        if (JsSafeUrlSystemParamManager.getInstance().isInURLWhiteList(this.mWebview) && (operateWatchFaceCallback = this.mOperateWatchFaceCallback) != null) {
            operateWatchFaceCallback.setWatchFaceConfirmButton();
        }
    }

    @JavascriptInterface
    public void deldetUserSelectWatchFace(String str, String str2) {
        PluginOperationAdapter pluginOperationAdapter;
        HwLog.i(TAG, "deldetUserSelectWatchFace hiTopId: " + str + ", version: " + str2);
        if (JsSafeUrlSystemParamManager.getInstance().isInSensitiveURLWhiteList(this.mWebview, "deldetUserSelectWatchFace") && (pluginOperationAdapter = this.mAdapter) != null) {
            pluginOperationAdapter.deleteDesignationUserWatchFace(str, str2, this.mDelectUserDesignationWatchFaceCallback);
        }
    }

    @JavascriptInterface
    public void deleteSelectWatchFace(String str, String str2) {
        PluginOperationAdapter pluginOperationAdapter;
        HwLog.i(TAG, "deleteSelectWatchFace hiTopId: " + str + ", version: " + str2);
        if (JsSafeUrlSystemParamManager.getInstance().isInSensitiveURLWhiteList(this.mWebview, "deldetUserSelectWatchFace") && (pluginOperationAdapter = this.mAdapter) != null) {
            pluginOperationAdapter.deleteDesignationWatchFace(str, str2);
        }
    }

    @JavascriptInterface
    public String getWatchFaceSignBean() {
        if (!JsSafeUrlSystemParamManager.getInstance().isInSensitiveURLWhiteList(this.mWebview, "getWatchFaceSignBean")) {
            return "";
        }
        HwLog.i(TAG, "getWatchFaceSignBean");
        PluginOperationAdapter pluginOperationAdapter = this.mAdapter;
        return pluginOperationAdapter != null ? pluginOperationAdapter.getWatchFaceSignBean() : "";
    }

    @JavascriptInterface
    public void setEnterWatchFaceMarket(boolean z) {
        HwLog.i(TAG, "setEnterWatchFaceMarket");
        if (JsSafeUrlSystemParamManager.getInstance().isInURLWhiteList(this.mWebview)) {
            if (z) {
                fa.a();
            }
            PluginOperationAdapter pluginOperationAdapter = this.mAdapter;
            if (pluginOperationAdapter != null) {
                pluginOperationAdapter.setEnterWatchFaceMarket(z);
            }
        }
    }

    @JavascriptInterface
    public String getWatchFaceDownloadData() {
        String str = TAG;
        HwLog.i(str, "getWatchFaceDownloadData() enter.");
        if (!JsSafeUrlSystemParamManager.getInstance().isInURLWhiteList(this.mWebview)) {
            return "";
        }
        PluginOperationAdapter pluginOperationAdapter = this.mAdapter;
        String watchFaceDownloadData = pluginOperationAdapter != null ? pluginOperationAdapter.getWatchFaceDownloadData() : "";
        HwLog.i(str, "getWatchFaceDownloadData() watchFaceDownloadData: " + watchFaceDownloadData);
        return watchFaceDownloadData;
    }

    @JavascriptInterface
    public int getWatchFaceDownloadItemNum() {
        PluginOperationAdapter pluginOperationAdapter;
        HwLog.i(TAG, "getWatchFaceDownloadItemNum");
        if (JsSafeUrlSystemParamManager.getInstance().isInURLWhiteList(this.mWebview) && (pluginOperationAdapter = this.mAdapter) != null) {
            return pluginOperationAdapter.getWatchFaceDownloadItemNum();
        }
        return 0;
    }

    @JavascriptInterface
    public String sendContinueDownloadWatchFace(int i, String str) {
        String str2 = TAG;
        HwLog.i(str2, "sendContinueDownloadWatchFace--operationCode==" + i + " ,continueDownloadInfo==" + str);
        if (!JsSafeUrlSystemParamManager.getInstance().isInSensitiveURLWhiteList(this.mWebview, "sendContinueDownloadWatchFace")) {
            return "-3";
        }
        DownloadQueryBean downloadQueryBean = (DownloadQueryBean) GsonHelper.fromJson(str, DownloadQueryBean.class);
        if (downloadQueryBean == null) {
            HwLog.w(str2, "sendContinueDownloadWatchFace -- continueDownloadInfo parse failed");
            return "-2";
        }
        downloadQueryBean.setVipFreeWatchFace(HwWatchFaceManager.getInstance(this.mContext).isVipFreeWatchFaceContain(downloadQueryBean.getHitopId()));
        PluginOperationAdapter pluginOperationAdapter = this.mAdapter;
        return pluginOperationAdapter != null ? pluginOperationAdapter.sendContinueDownloadWatchFace(i, downloadQueryBean) : "1";
    }

    @JavascriptInterface
    public String registerWebViewStatus(String str) {
        HwLog.i(TAG, "registerWebViewStatus functionRes : " + str);
        if (!JsSafeUrlSystemParamManager.getInstance().isInURLWhiteList(this.mWebview)) {
            return String.valueOf(Constants.CODE_UNKNOWN_ERROR);
        }
        OnWebViewStatusCallback onWebViewStatusCallback = this.mOnWebViewStatusCallback;
        if (onWebViewStatusCallback != null) {
            onWebViewStatusCallback.onWebViewStatusCallback(str);
            return String.valueOf(0);
        }
        return String.valueOf(Constants.CODE_UNKNOWN_ERROR);
    }

    @JavascriptInterface
    public void finishWatchFaceWebViewActivity() {
        String str = TAG;
        HwLog.i(str, "finishWatchFaceWebViewActivity");
        if (JsSafeUrlSystemParamManager.getInstance().isInURLWhiteList(this.mWebview)) {
            if (!contextIsValidWebViewActivity()) {
                HwLog.i(str, "finishWatchFaceWebViewActivity -- not valid WebViewActivity");
            } else {
                ((WebViewActivity) this.mContext).finish();
            }
        }
    }

    @JavascriptInterface
    public boolean getCurrentUserIsDesignerState() {
        HwLog.i(TAG, "getCurrentUserIsDesignerState");
        if (JsSafeUrlSystemParamManager.getInstance().isInURLWhiteList(this.mWebview)) {
            return dp.a("current_user_is_designer", "goal_steps_perference", false);
        }
        return false;
    }

    @JavascriptInterface
    public void jumpHwWatchFaceDesigner() {
        String str = TAG;
        HwLog.i(str, "jumpHwWatchFaceDesigner");
        if (JsSafeUrlSystemParamManager.getInstance().isInSensitiveURLWhiteList(this.mWebview, "jumpHwWatchFaceDesigner")) {
            if (!contextIsValidWebViewActivity()) {
                HwLog.i(str, "jumpHwWatchFaceDesigner -- not valid WebViewActivity");
            } else {
                ((WebViewActivity) this.mContext).verifyStoragePermissions();
            }
        }
    }

    @JavascriptInterface
    public void jumpHwWatchFacePrivacy() {
        String str = TAG;
        HwLog.i(str, "jumpHwWatchFacePrivacy");
        if (JsSafeUrlSystemParamManager.getInstance().isInURLWhiteList(this.mWebview)) {
            if (!contextIsValidWebViewActivity()) {
                HwLog.i(str, "jumpHwWatchFacePrivacy -- not valid WebViewActivity");
            } else {
                ((WebViewActivity) this.mContext).gotoPrivacyStatementActivity();
            }
        }
    }

    private boolean contextIsValidWebViewActivity() {
        if (!CommonUtils.l(this.mContext)) {
            HwLog.i(TAG, "contextIsValidWebViewActivity -- not valid activity");
            return false;
        }
        if (this.mContext instanceof WebViewActivity) {
            return true;
        }
        HwLog.i(TAG, "contextIsValidWebViewActivity -- not instanceof WebViewActivity");
        return false;
    }

    @JavascriptInterface
    public String getNativeParams() {
        HwLog.i(TAG, "getNativeParams() enter.");
        if (!JsSafeUrlSystemParamManager.getInstance().isInURLWhiteList(this.mWebview)) {
            return "";
        }
        HwWatchFaceApi hwWatchFaceApi = HwWatchFaceApi.getInstance(Environment.getApplicationContext());
        ab a2 = ab.a();
        JSONObject jSONObject = new JSONObject();
        if (a2 != null) {
            try {
                QuerySubInfoDetailResp b = a2.b();
                if (b != null) {
                    jSONObject.put("memberStatus", b.getMemberStatus() != null ? b.getMemberStatus() : "0");
                    if (b.getSubInfo() != null) {
                        jSONObject.put("memberExpireDate", b.getSubInfo().getValidDate());
                    }
                }
            } catch (JSONException e) {
                HwLog.e(TAG, "getNativeParams -- JSONException" + HwLog.printException((Exception) e));
            }
        }
        jSONObject.put("statusbarHeight", DensityUtil.a(CommonUtils.h(Environment.getApplicationContext())));
        jSONObject.put("isBlackTheme", PowerThemeHelper.isDarkMode(this.mContext));
        jSONObject.put("isSupportedH5TitleBar", hwWatchFaceApi.isSupportedH5TitleBar());
        jSONObject.put("deviceId", hwWatchFaceApi.getDeviceIdAsTheme(true));
        jSONObject.put("uid", hwWatchFaceApi.getUserId());
        jSONObject.put("x-brandChannel", String.valueOf(cv.e()));
        jSONObject.put("x-accountBrand", hwWatchFaceApi.getAccountBrand());
        Application applicationContext = Environment.getApplicationContext();
        if (applicationContext != null) {
            jSONObject.put("x-packagename", applicationContext.getPackageName());
        }
        jSONObject.put("x-appBrand", String.valueOf(cv.c()));
        jSONObject.put("sdk_int", Build.VERSION.SDK_INT);
        jSONObject.put("rom_version", Build.VERSION.RELEASE);
        jSONObject.put("phoneId", cv.h());
        jSONObject.put("watchDeviceId", cv.h());
        jSONObject.put("phoneBrand", cv.k());
        jSONObject.put("isHuaweiPhone", CommonUtils.i());
        jSONObject.put("appVersion", cv.a());
        jSONObject.put(DevicePairUtils.DEVICE_CONNECT_STATE, hwWatchFaceApi.getDeviceConnectState());
        jSONObject.put("locale", LanguageUtils.a(true));
        jSONObject.put("aarVersion", cv.j());
        jSONObject.put("aarAbilityVersion", "1019100");
        jSONObject.put("isoCode", hwWatchFaceApi.getCommonCountryCode());
        jSONObject.put("abilityList", GsonHelper.toJson(HwWatchFaceManager.getWatchAbility()));
        jSONObject.put("isKidAccount", n.a(this.mContext).a());
        jSONObject.put("healthModelStatus", az.a().c());
        jSONObject.put("lan", LanguageUtils.d());
        IH5LoadingCallback iH5LoadingCallback = this.mLoadingCallback;
        if (iH5LoadingCallback != null) {
            iH5LoadingCallback.onLoadSuccess();
        }
        return jSONObject.toString();
    }

    @JavascriptInterface
    public void reportOmData(String str, String str2) {
        String str3 = TAG;
        HwLog.i(str3, "reportOmData");
        if (JsSafeUrlSystemParamManager.getInstance().isInURLWhiteList(this.mWebview)) {
            if (str.equals("2180001")) {
                HwLog.i(str3, "reportOmData json:" + str2);
                LinkedHashMap<String, String> parseOmData = parseOmData(str2);
                int a2 = IntegerUtils.a(parseOmData.get("showSuccess"));
                long a3 = dh.a(parseOmData.get("startts"), 0L);
                ef efVar = new ef();
                efVar.a(a3);
                efVar.b("2");
                if (a2 == 1) {
                    efVar.a(a2, "MSG_ON_LOAD_SUCCESS");
                } else {
                    efVar.a(a2, "MSG_ON_LOAD_FAILED");
                }
                eo.a(efVar, efVar.g());
                return;
            }
            LinkedHashMap<String, String> parseOmData2 = parseOmData(str2);
            if (parseOmData2.size() == 0) {
                return;
            }
            new ec(str, parseOmData2).a_();
        }
    }

    private LinkedHashMap<String, String> parseOmData(String str) {
        LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<>();
        try {
            JSONObject jSONObject = new JSONObject(str.trim());
            Iterator<String> keys = jSONObject.keys();
            while (keys.hasNext()) {
                String next = keys.next();
                linkedHashMap.put(next, jSONObject.optString(next));
            }
        } catch (JSONException unused) {
            HwLog.e(TAG, "reportOmData param json error");
        }
        return linkedHashMap;
    }

    @JavascriptInterface
    public String getSmartPicture(String str, String str2, String str3) {
        String str4 = TAG;
        HwLog.i(str4, "getSmartPicture");
        if (!JsSafeUrlSystemParamManager.getInstance().isInURLWhiteList(this.mWebview)) {
            HwLog.w(str4, "getSmartPicture -- url not in white list");
            return "";
        }
        return WatchFacePhotoAlbumManager.getInstance(this.mContext).a(str, str2, str3, (String) null, -1)[0];
    }

    @JavascriptInterface
    public String getSmartPictureList(String str) {
        String str2 = TAG;
        HwLog.i(str2, "getSmartPictureList");
        if (!JsSafeUrlSystemParamManager.getInstance().isInURLWhiteList(this.mWebview)) {
            HwLog.w(str2, "getSmartPictureList -- url not in white list");
            return "";
        }
        return WatchFacePhotoAlbumManager.getInstance(this.mContext).b(str);
    }

    @JavascriptInterface
    public String getWatchFaceWithFilterInfo(String str) {
        String str2 = TAG;
        HwLog.i(str2, "getWatchFaceWithFilterInfo");
        if (!JsSafeUrlSystemParamManager.getInstance().isInURLWhiteList(this.mWebview)) {
            HwLog.w(str2, "getWatchFaceWithFilterInfo -- url not in white list");
            return "";
        }
        return WatchFacePhotoAlbumManager.getInstance(this.mContext).c(str);
    }

    @JavascriptInterface
    public boolean hasSmartPick(boolean z) {
        if (!JsSafeUrlSystemParamManager.getInstance().isInURLWhiteList(this.mWebview)) {
            HwLog.w(TAG, "hasSmartPick -- url not in white list");
            return false;
        }
        HwLog.i(TAG, "hasSmartPick -- isSupport:" + z);
        return z;
    }

    @JavascriptInterface
    public void sendStartTryoutDownload(String str) {
        JsInteractAddition.getInstance().a(this.mAdapter, str);
    }

    @JavascriptInterface
    public void cancelTryOutWatchFace(String str, String str2) {
        if (!JsSafeUrlSystemParamManager.getInstance().isInSensitiveURLWhiteList(this.mWebview, "cancelTryOutWatchFace")) {
            HwLog.w(TAG, "cancelTryOutWatchFace -- not in white list");
            return;
        }
        String str3 = TAG;
        HwLog.i(str3, "cancelTryOutWatchFace hiTopId: " + str + " version:" + str2);
        PluginOperationAdapter pluginOperationAdapter = this.mAdapter;
        if (pluginOperationAdapter != null) {
            pluginOperationAdapter.cancelTryOutWatchFace(str, str2);
        } else {
            HwLog.w(str3, "cancelTryOutWatchFace error adapter is null");
        }
    }

    @JavascriptInterface
    public String getTryOutWatchFacePackageName() {
        return JsInteractAddition.getInstance().e(this.mAdapter);
    }

    @JavascriptInterface
    public String getKaleidoscopePackageName() {
        return JsInteractAddition.getInstance().f(this.mAdapter);
    }

    @JavascriptInterface
    public boolean createShortcuts() {
        String str = TAG;
        HwLog.i(str, "createShortcuts() enter.");
        if (!JsSafeUrlSystemParamManager.getInstance().isInURLWhiteList(this.mWebview)) {
            HwLog.i(str, "createShortcuts() isInURLWhiteList");
            return false;
        }
        return HwWatchFaceApi.getInstance(this.mContext).createShortcuts();
    }

    @JavascriptInterface
    public boolean isCreateShortcuts() {
        String str = TAG;
        HwLog.i(str, "isCreateShortcuts() enter.");
        if (!JsSafeUrlSystemParamManager.getInstance().isInURLWhiteList(this.mWebview)) {
            HwLog.i(str, "isCreateShortcuts() isInURLWhiteList");
            return false;
        }
        return HwWatchFaceApi.getInstance(this.mContext).isExistShortCut("watchface_supermarket");
    }

    @JavascriptInterface
    public void jumpOtherUrlActivity(String str) {
        String str2 = TAG;
        HwLog.i(str2, "jumpOtherUrlActivity() enter.");
        if (!JsSafeUrlSystemParamManager.getInstance().isInURLWhiteList(this.mWebview)) {
            HwLog.i(str2, "jumpOtherUrlActivity isInURLWhiteList");
        } else {
            HwWatchFaceApi.getInstance(this.mContext).jumpOtherUrlActivity(this.mContext, str);
        }
    }

    @JavascriptInterface
    public void jumpMarketActivity(String str) {
        String str2 = TAG;
        HwLog.i(str2, "jumpMarketActivity() enter. appId:" + str);
        if (!JsSafeUrlSystemParamManager.getInstance().isInURLWhiteList(this.mWebview)) {
            HwLog.i(str2, "jumpMarketActivity isInURLWhiteList");
        } else {
            WebViewUtils.b(this.mContext, str);
        }
    }

    @JavascriptInterface
    public void toCallCustomerService(String str) {
        if (!JsSafeUrlSystemParamManager.getInstance().isInURLWhiteList(this.mWebview)) {
            HwLog.w(TAG, "toCallCustomerService -- url not in white list");
            return;
        }
        if (ViewOnClickListener.isSeriesClick(1000)) {
            return;
        }
        Intent intent = new Intent(new Intent("android.intent.action.DIAL", Uri.parse(KakaConstants.SCHEME_TEL + str)));
        if (CommonUtils.b(this.mContext, intent)) {
            CommonUtils.a(this.mContext, intent);
        }
    }

    @JavascriptInterface
    public void toOpenSourceLicense() {
        if (!JsSafeUrlSystemParamManager.getInstance().isInURLWhiteList(this.mWebview)) {
            HwLog.w(TAG, "toOpenSourceLicense -- url not in white list");
        } else {
            if (ViewOnClickListener.isSeriesClick(1000)) {
                return;
            }
            Intent intent = new Intent(this.mContext, (Class<?>) WatchfaceUrlActivityV1.class);
            intent.putExtra("WebType", 101);
            CommonUtils.a(this.mContext, intent);
        }
    }

    @JavascriptInterface
    public void toPrivacyInformation(String str) {
        String str2;
        String str3 = TAG;
        HwLog.i(str3, "toPrivacyInformation enter");
        if (!JsSafeUrlSystemParamManager.getInstance().isInURLWhiteList(this.mWebview)) {
            HwLog.w(str3, "toPrivacyInformation -- url not in white list");
            return;
        }
        try {
            str2 = new JSONObject(str).optString("url");
        } catch (JSONException e) {
            HwLog.e(TAG, "JSONException : " + HwLog.printException((Exception) e));
            str2 = "";
        }
        if (ViewOnClickListener.isSeriesClick(1000)) {
            return;
        }
        Intent intent = new Intent(this.mContext, (Class<?>) WatchfaceUrlActivityV1.class);
        intent.putExtra("WebType", 102);
        intent.putExtra("WebSource", str2);
        CommonUtils.a(this.mContext, intent);
    }

    @JavascriptInterface
    public int getVersionCode() {
        if (!JsSafeUrlSystemParamManager.getInstance().isInURLWhiteList(this.mWebview)) {
            HwLog.w(TAG, "getVersionCode -- url not in white list");
            return 0;
        }
        return CommonUtils.getVersionCode();
    }

    @JavascriptInterface
    public String getAarVersion() {
        if (JsSafeUrlSystemParamManager.getInstance().isInURLWhiteList(this.mWebview)) {
            return "10005151";
        }
        HwLog.w(TAG, "getWatchfaceAarVersion -- url not in white list");
        return "";
    }

    @JavascriptInterface
    public void setEnteringWatchFaceAlbum(boolean z) {
        PluginOperationAdapter pluginOperationAdapter;
        HwLog.i(TAG, "setEnteringWatchFaceAlbum");
        if (JsSafeUrlSystemParamManager.getInstance().isInURLWhiteList(this.mWebview) && (pluginOperationAdapter = this.mAdapter) != null) {
            pluginOperationAdapter.setEnteringWatchFaceAlbum(z);
        }
    }

    @JavascriptInterface
    public String getVideoAlbumPackageName() {
        return JsInteractAddition.getInstance().a(this.mAdapter);
    }

    @JavascriptInterface
    public void startSeparationPortrait(String str) {
        HwLog.i(TAG, "startSeparationPortrait : " + str);
        JsInteractAddition.getInstance().a(str);
    }

    @JavascriptInterface
    public void goBack() {
        String str = TAG;
        HwLog.i(str, "goBack");
        if (!JsSafeUrlSystemParamManager.getInstance().isInURLWhiteList(this.mWebview)) {
            HwLog.i(str, "goBack url not in white list");
            return;
        }
        try {
            Context context = this.mContext;
            if (context == null || !(context instanceof Activity)) {
                return;
            }
            HwLog.i(str, "goBack finish");
            ((Activity) this.mContext).finish();
        } catch (Exception e) {
            HwLog.e(TAG, "goBack " + e.toString());
        }
    }

    @JavascriptInterface
    public void choosePicToClip() {
        HwLog.i(TAG, WebViewConstant.InterfaceName.INTERFACE_CHOOSEPICTOCLIP);
        JsInteractAddition.getInstance().f(this.mContext, this.mSendCurrentUrlCallback, this.mAdapter);
    }

    @JavascriptInterface
    public void choosePicToH5Clip(String str) {
        HwLog.i(TAG, "choosePicToH5Clip");
        JsInteractAddition.getInstance().g(this.mContext, this.mSendCurrentUrlCallback, this.mAdapter);
    }

    @JavascriptInterface
    public void choosePicToH5Search(String str) {
        String str2 = TAG;
        HwLog.i(str2, "choosePicToH5Search jumpIndex:" + str);
        if (!JsSafeUrlSystemParamManager.getInstance().isInURLWhiteList(this.mWebview)) {
            HwLog.i(str2, "choosePicToH5Search() isInURLWhiteList");
        } else {
            l.a().b(str);
        }
    }

    @JavascriptInterface
    public String getImgUrlForSearch() {
        String str = TAG;
        HwLog.i(str, "getImgUrlForSearch enter");
        if (!JsSafeUrlSystemParamManager.getInstance().isInURLWhiteList(this.mWebview)) {
            HwLog.i(str, "getImgUrlForSearch() isInURLWhiteList");
            return "";
        }
        return l.a().b();
    }

    @JavascriptInterface
    public void cancelSearchImage() {
        String str = TAG;
        HwLog.i(str, "cancelSearchImage enter");
        if (!JsSafeUrlSystemParamManager.getInstance().isInURLWhiteList(this.mWebview)) {
            HwLog.i(str, "cancelSearchImage() isInURLWhiteList");
        } else {
            l.a().a(true, false);
        }
    }

    @JavascriptInterface
    public void getWatchFaceThemeAlbumInfo(String str, String str2, String str3) {
        getWatchFaceThemeAlbumInfo(str, str2, str3, "");
    }

    @JavascriptInterface
    public void getWatchFaceThemeAlbumInfo(String str, String str2, String str3, String str4) {
        if (str == null || str2 == null || str3 == null) {
            return;
        }
        HashMap<String, String> hashMap = new HashMap<>(32);
        hashMap.put(JsInteractAddition.HI_TOP_ID, str);
        String str5 = TAG;
        HwLog.i(str5, JsInteractAddition.HI_TOP_ID + str);
        hashMap.put("version", str2);
        HwLog.i(str5, "version" + str2);
        hashMap.put("url", str3);
        hashMap.put(WatchFaceConstant.HASHCODE, str4);
        JsInteractAddition.getInstance().b(this.mContext, this.mSendCurrentUrlCallback, this.mAdapter, hashMap);
    }

    @JavascriptInterface
    public void syncWatchFaceThemeAlbumInfo(String str) {
        if (TextUtils.isEmpty(str)) {
            HwLog.d(TAG, "syncWatchFaceThemeAlbumInfo msg is null");
        } else {
            JsInteractAddition.getInstance().b(this.mAdapter, str);
        }
    }

    @JavascriptInterface
    public void saveWatchFaceThemeAlbumInfo(String str) {
        JsInteractAddition.getInstance().c(this.mContext, this.mSendCurrentUrlCallback, this.mAdapter, str);
    }

    @JavascriptInterface
    public String getWearAlbumPackageName() {
        return JsInteractAddition.getInstance().a(this.mWebview, this.mAdapter);
    }

    @JavascriptInterface
    public void getWatchFaceKaleidoscopeInfo(String str, String str2, String str3, String str4) {
        if (str == null || str2 == null || str3 == null) {
            return;
        }
        HashMap<String, String> hashMap = new HashMap<>(32);
        hashMap.put(JsInteractAddition.HI_TOP_ID, str);
        hashMap.put("version", str2);
        hashMap.put("url", str3);
        hashMap.put(WatchFaceConstant.HASHCODE, str4);
        JsInteractAddition.getInstance().a(hashMap, this.mWebview);
    }

    @JavascriptInterface
    public void reselectionWearTransmit() {
        String str = TAG;
        HwLog.i(str, "reselectionWearTransmit() -- enter.");
        if (!JsSafeUrlSystemParamManager.getInstance().isInSensitiveURLWhiteList(this.mWebview, "reselection")) {
            HwLog.i(str, "reselectionWearTransmit() -- is not in safe url.");
            return;
        }
        HwLog.i(str, "reselectionWearTransmit() -- executing adapter.");
        if (this.mAdapter != null) {
            HwLog.i(str, "reselectionWearTransmit() -- adapter is not null.");
            this.mAdapter.reselectionWearTransmit();
        }
    }

    @JavascriptInterface
    public void updatePersonalizedStatus(String str) {
        String str2 = TAG;
        HwLog.i(str2, "updatePersonalizedStatus() enter. result ：" + str);
        if (!JsSafeUrlSystemParamManager.getInstance().isInURLWhiteList(this.mWebview)) {
            HwLog.i(str2, "updatePersonalizedStatus() isInURLWhiteList");
            return;
        }
        try {
            boolean optBoolean = new JSONObject(str).optBoolean("status");
            boolean g = dp.g(this.mContext);
            HwLog.i(str2, "updatePersonalizedStatus() personalizedStatus is personalizedStatus=" + optBoolean + ",  originStatus=" + g);
            if (g == optBoolean) {
                dp.b(this.mContext, false);
            } else {
                dp.b(this.mContext, true);
            }
        } catch (JSONException e) {
            HwLog.e(TAG, "updatePersonalizedStatus：" + HwLog.printException((Exception) e));
        }
    }

    @JavascriptInterface
    public boolean checkFile(String str) {
        String str2 = TAG;
        HwLog.i(str2, "checkFile() enter");
        if (!JsSafeUrlSystemParamManager.getInstance().isInURLWhiteList(this.mWebview)) {
            HwLog.e(str2, "checkFile() no whiteurl ");
            return false;
        }
        File file = PversionSdUtils.getFile(str);
        return file != null && file.exists();
    }

    @JavascriptInterface
    public String saveImagePath(String str, String str2, String str3) {
        String str4 = TAG;
        HwLog.i(str4, "saveImagePath name = " + str3 + ",type =" + str2);
        if (!JsSafeUrlSystemParamManager.getInstance().isInURLWhiteList(this.mWebview)) {
            HwLog.e(str4, "saveImagePath() isInURLWhiteList");
            return "-1";
        }
        if (TextUtils.isEmpty(str)) {
            return "-1";
        }
        PortraitImgInfo portraitImgInfo = (PortraitImgInfo) GsonHelper.fromJson(str, PortraitImgInfo.class);
        if (portraitImgInfo == null) {
            HwLog.e(str4, "portraitImgInfo is null");
            return "-1";
        }
        String str5 = (String) ArrayUtils.a(str3.split("_"), 0);
        if (TextUtils.isEmpty(portraitImgInfo.getFgImgUrl()) || TextUtils.isEmpty(portraitImgInfo.getBgImgUrl())) {
            if (!PversionSdUtils.a(this.mContext, portraitImgInfo.getImgUrl(), str5 + ".png", str2)) {
                return "-1";
            }
        } else {
            boolean a2 = PversionSdUtils.a(this.mContext, portraitImgInfo.getFgImgUrl(), str5 + "_fg.png", str2);
            if (!PversionSdUtils.a(this.mContext, portraitImgInfo.getBgImgUrl(), str5 + "_bg.png", str2) || !a2) {
                return "-1";
            }
        }
        return "0";
    }

    @JavascriptInterface
    public void setStatusBarFontColors(String str) {
        String str2 = TAG;
        HwLog.i(str2, "setStatusBarFontColors() enter");
        if (!JsSafeUrlSystemParamManager.getInstance().isInURLWhiteList(this.mWebview)) {
            HwLog.i(str2, "setStatusBarFontColors() isInURLWhiteList");
            return;
        }
        if (TextUtils.isEmpty(str)) {
            HwLog.i(str2, "setStatusBarFontColors() json is isEmpty");
        }
        try {
            if (!dj.a()) {
                HwLog.i(str2, "setStatusBarFontColors() isNetworkAvailable false");
                return;
            }
            final String optString = new JSONObject(str).optString("statusColor");
            if (TextUtils.isEmpty(optString)) {
                return;
            }
            if (BackgroundTaskUtils.a()) {
                CommonUtils.a(optString, (Activity) this.mContext);
            } else {
                BackgroundTaskUtils.postInMainThread(new Runnable() { // from class: com.huawei.watchface.mvp.model.webview.JSInterface$$ExternalSyntheticLambda0
                    @Override // java.lang.Runnable
                    public final void run() {
                        JSInterface.this.m915x1196412d(optString);
                    }
                });
            }
        } catch (JSONException e) {
            HwLog.e(TAG, "setStatusBarFontColors() JSONException " + HwLog.printException((Exception) e));
        }
    }

    /* renamed from: lambda$setStatusBarFontColors$7$com-huawei-watchface-mvp-model-webview-JSInterface, reason: not valid java name */
    /* synthetic */ void m915x1196412d(String str) {
        CommonUtils.a(str, (Activity) this.mContext);
    }

    @JavascriptInterface
    public void sendBluetoothCommand(String str) {
        String str2 = TAG;
        HwLog.i(str2, "sendBluetoothCommand() enter");
        if (!JsSafeUrlSystemParamManager.getInstance().isInURLWhiteList(this.mWebview)) {
            HwLog.i(str2, "sendBluetoothCommand() isInURLWhiteList");
            return;
        }
        if (TextUtils.isEmpty(str)) {
            HwLog.i(str2, "sendBluetoothCommand() json is isEmpty");
            return;
        }
        CommandJsonInfo commandJsonInfo = (CommandJsonInfo) GsonHelper.fromJson(str, CommandJsonInfo.class);
        if (commandJsonInfo == null) {
            HwLog.i(str2, "sendBluetoothCommand() commandJsonInfo is isEmpty");
        } else {
            HwWatchFaceBtManager.getInstance(Environment.getApplicationContext()).h5ToSendDeviceCommand(commandJsonInfo.getBluetoothTlvName(), commandJsonInfo.getServiceId(), commandJsonInfo.getCommandId(), commandJsonInfo.getCommandType(), commandJsonInfo.getDeviceCommand());
        }
    }

    @JavascriptInterface
    public void register(String str) {
        String str2 = TAG;
        HwLog.i(str2, "register() enter");
        if (!JsSafeUrlSystemParamManager.getInstance().isInURLWhiteList(this.mWebview)) {
            HwLog.i(str2, "register() isInURLWhiteList");
        } else {
            HwWatchFaceBtManager.getInstance(Environment.getApplicationContext()).setRegisterH5(str);
        }
    }

    @JavascriptInterface
    public void applyWatchFaceThemeAlbumInfo(String str) {
        String str2 = TAG;
        HwLog.i(str2, "applyWatchFaceThemeAlbumInfo() enter");
        if (!JsSafeUrlSystemParamManager.getInstance().isInURLWhiteList(this.mWebview)) {
            HwLog.i(str2, "applyWatchFaceThemeAlbumInfo() isInURLWhiteList");
        } else {
            JsInteractAddition.getInstance().d(this.mContext, this.mSendCurrentUrlCallback, this.mAdapter, str);
        }
    }

    @JavascriptInterface
    public void transmitWatchFiles(int i, int i2) {
        String str = TAG;
        HwLog.i(str, "transmitWatchFiles() enter:" + i2);
        if (!JsSafeUrlSystemParamManager.getInstance().isInURLWhiteList(this.mWebview)) {
            HwLog.i(str, "transmitWatchFiles() isInURLWhiteList");
        } else {
            WatchFacePhotoAlbumManager.getInstance(this.mContext).a(i, i2);
        }
    }

    @JavascriptInterface
    public boolean getIsSupportTouch() {
        String str = TAG;
        HwLog.i(str, "getIsSupportTouch() enter:");
        if (!JsSafeUrlSystemParamManager.getInstance().isInURLWhiteList(this.mWebview)) {
            HwLog.i(str, "getIsSupportTouch() isInURLWhiteList");
            return false;
        }
        return HwWatchFaceBtManager.getInstance(this.mContext).isSupportOneTouchAbility();
    }

    @JavascriptInterface
    public String getAlbumPreviewPath() {
        String str = TAG;
        HwLog.i(str, "getAlbumPreviewPath() enter:");
        if (!JsSafeUrlSystemParamManager.getInstance().isInURLWhiteList(this.mWebview)) {
            HwLog.i(str, "getAlbumPreviewPath() isInURLWhiteList");
            return "";
        }
        return WatchFacePhotoAlbumManager.getInstance(this.mContext).f();
    }

    @JavascriptInterface
    public void saveBase64ToFile(String str, String str2) {
        String str3 = TAG;
        HwLog.i(str3, "saveBase64ToFile() enter:");
        if (!JsSafeUrlSystemParamManager.getInstance().isInURLWhiteList(this.mWebview)) {
            HwLog.i(str3, "saveBase64ToFile() isInURLWhiteList");
            return;
        }
        String a2 = WatchFacePhotoAlbumManager.getInstance(this.mContext).a(str2, str, WatchFacePhotoAlbumManager.getInstance(this.mContext).n(), false);
        if (TextUtils.isEmpty(a2)) {
            HwLog.d(str3, "saveBase64ToFile failed");
        } else {
            dp.a(cv.i(), a2, "album_background");
            WatchFacePhotoAlbumManager.getInstance(this.mContext).d(str);
        }
    }

    @JavascriptInterface
    public boolean customClipPic(String str, boolean z) {
        if (!JsSafeUrlSystemParamManager.getInstance().isInURLWhiteList(this.mWebview)) {
            HwLog.i(TAG, "customClipPic() isInURLWhiteList");
            return false;
        }
        HashMap<String, String> e = de.e(str);
        if (e == null) {
            HwLog.d(TAG, "customClipPic orgFilePath is empty");
            return false;
        }
        Intent intent = new Intent(this.mContext, (Class<?>) ClipImageActivity.class);
        intent.putExtra("sourceImagePath", e.get(ClipImageActivity.ORG_IMAGE_PATH));
        intent.putExtra("sourceImageUri", e.get(ClipImageActivity.ORG_IMAGE_PATH));
        intent.putExtra("scaleX", e.get("scaleX"));
        intent.putExtra("scaleY", e.get("scaleY"));
        intent.putExtra(ClipImageActivity.SOURCE_IMAGE_TRANS_X, e.get(ClipImageActivity.SOURCE_IMAGE_TRANS_X));
        intent.putExtra(ClipImageActivity.SOURCE_IMAGE_TRANS_Y, e.get(ClipImageActivity.SOURCE_IMAGE_TRANS_Y));
        intent.putExtra(ClipImageActivity.ORG_WINDOWS_HEIGHT, e.get(ClipImageActivity.ORG_WINDOWS_HEIGHT));
        intent.putExtra(ClipImageActivity.ORG_WINDOWS_WIDTH, e.get(ClipImageActivity.ORG_WINDOWS_WIDTH));
        intent.putExtra("isSecondEdit", true);
        int watchFaceWidth = LatonaWatchFaceProvider.getInstance(this.mContext).getWatchFaceWidth();
        int watchFaceHeight = LatonaWatchFaceProvider.getInstance(this.mContext).getWatchFaceHeight();
        if (watchFaceWidth > 0 && watchFaceHeight > 0) {
            intent.putExtra(ClipImageActivity.CLIP_TARGET_WIDTH, watchFaceWidth);
            intent.putExtra(ClipImageActivity.CLIP_TARGET_HEIGHT, watchFaceHeight);
        }
        intent.putExtra(ClipImageActivity.CLIP_TARGET_SHAPE, z);
        CommonUtils.startActivityForResult((Activity) this.mContext, intent, 3);
        return true;
    }

    @JavascriptInterface
    public void dismissPayVipDialog() {
        String str = TAG;
        HwLog.i(str, "closePayVipDialog: enter");
        if (!JsSafeUrlSystemParamManager.getInstance().isInURLWhiteList(this.mWebview)) {
            HwLog.e(str, "closePayVipDialog() !isInURLWhiteList.");
            return;
        }
        try {
            s.a(this.mContext).b(this.mContext);
        } catch (Exception e) {
            HwLog.e(TAG, "closePayVipDialog error:" + HwLog.printException(e));
        }
    }

    @JavascriptInterface
    public void setAarAbility(String str) {
        String str2 = TAG;
        HwLog.i(str2, "setAarAbility: enter");
        if (!JsSafeUrlSystemParamManager.getInstance().isInURLWhiteList(this.mWebview)) {
            HwLog.e(str2, "setAarAbility() !isInURLWhiteList.");
            return;
        }
        try {
            WatchFaceAarAbility watchFaceAarAbility = (WatchFaceAarAbility) GsonHelper.fromJson(str, WatchFaceAarAbility.class);
            if (watchFaceAarAbility == null) {
                HwLog.d(str2, "watchFaceAarAbility is null");
                return;
            }
            fj.a().a(watchFaceAarAbility.getEnableAnrCollect());
            JsSafeUrlSystemParamManager.getInstance().a(watchFaceAarAbility.isEnableWebViewUrlCache());
            if (!contextIsValidWebViewActivity()) {
                HwLog.i(str2, "jumpHwWatchFacePrivacy -- not valid WebViewActivity");
            } else {
                ((WebViewActivity) this.mContext).setReloadConfigChange(watchFaceAarAbility.isEnableReloadOnConfigChange());
            }
        } catch (Exception e) {
            HwLog.e(TAG, "setAarAbility error:" + HwLog.printException(e));
        }
    }

    @JavascriptInterface
    public String getCommonParam() {
        String str = TAG;
        HwLog.i(str, "getCommonParam: enter");
        if (!JsSafeUrlSystemParamManager.getInstance().isInURLWhiteList(this.mWebview)) {
            HwLog.e(str, "getCommonParam() !isInURLWhiteList.");
            return "";
        }
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("initTime", HwWatchFaceApi.getInstance(Environment.getApplicationContext()).getInitTime());
            return jSONObject.toString();
        } catch (Exception e) {
            HwLog.e(TAG, "setAarAbility error:" + HwLog.printException(e));
            return "";
        }
    }
}
