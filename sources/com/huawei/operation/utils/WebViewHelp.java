package com.huawei.operation.utils;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Message;
import android.text.TextUtils;
import android.util.Base64;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import com.apprichtap.haptic.base.PrebakedEffectId;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.handler.HandlerExecutor;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hianalytics.visual.autocollect.instrument.WebViewInstrumentation;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcloudmodel.callback.GrsQueryCallback;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.OpAnalyticsUtil;
import com.huawei.operation.OperationKey;
import com.huawei.operation.activity.WebViewActivity;
import com.huawei.operation.adapter.PluginOperationAdapter;
import com.huawei.operation.beans.TitleBean;
import com.huawei.operation.ble.BleConstants;
import com.huawei.operation.jsoperation.JsRegisterFunctionResUtil;
import com.huawei.operation.jsoperation.JsUtil;
import com.huawei.operation.operation.PluginOperation;
import com.huawei.operation.view.CustomWebView;
import com.huawei.pluginbase.PluginBaseAdapter;
import com.huawei.secure.android.common.util.UrlUtil;
import com.huawei.ui.commonui.dialog.NoTitleCustomAlertDialog;
import com.huawei.ui.commonui.divider.HealthDivider;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.titlebar.CustomTitleBar;
import com.huawei.watchface.mvp.model.latona.provider.WatchFaceProvider;
import defpackage.fdu;
import defpackage.ixx;
import defpackage.jah;
import defpackage.jei;
import defpackage.nqc;
import health.compact.a.CommonUtil;
import health.compact.a.GRSManager;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.regex.Pattern;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class WebViewHelp {
    private static final String ANNUAL_URL_CONTENT = "annualReport";
    private static final int DEFAULT_PADDING = 0;
    private static final String GROUP_CHALLENGE_URL_CONTENT = "groupChallenge";
    private static final int RESULT_OK = -1;
    private static final String TAG = "PluginOperation_WebViewHelp";
    private static HashMap<String, String> mGlobalBiParams;
    public static final String BI_KEY_PULL_FROM = "pullfrom";
    private static final String[] GLOBAL_BI_PARAM_KEYS = {BI_KEY_PULL_FROM};

    private WebViewHelp() {
    }

    public static nqc handleMorePopWindow(Context context, CustomWebView customWebView, TitleBean titleBean) {
        View inflate = View.inflate(context, R.layout.pop_custom_view_health_dialog, null);
        LinearLayout linearLayout = (LinearLayout) inflate.findViewById(R.id.line_feature_home_page);
        LinearLayout linearLayout2 = (LinearLayout) inflate.findViewById(R.id.line_shopping_cart);
        LinearLayout linearLayout3 = (LinearLayout) inflate.findViewById(R.id.line_order_manager);
        HealthDivider healthDivider = (HealthDivider) inflate.findViewById(R.id.divison_bar_shop_car);
        HealthDivider healthDivider2 = (HealthDivider) inflate.findViewById(R.id.divison_bar_feature_home_page);
        if (TextUtils.isEmpty(titleBean.fetchGetFeatureUrl())) {
            linearLayout.setVisibility(8);
        } else {
            linearLayout.setVisibility(0);
        }
        if (TextUtils.isEmpty(titleBean.fetchGetShoppingCartUrl())) {
            linearLayout2.setVisibility(8);
        } else {
            linearLayout2.setVisibility(0);
        }
        if (TextUtils.isEmpty(titleBean.fetchGetOrderManagerUrl())) {
            linearLayout3.setVisibility(8);
        } else {
            linearLayout3.setVisibility(0);
        }
        int visibility = linearLayout3.getVisibility();
        if (linearLayout2.getVisibility() != 0 && visibility != 0) {
            healthDivider2.setVisibility(8);
        } else {
            healthDivider2.setVisibility(0);
        }
        if (visibility != 0) {
            healthDivider.setVisibility(8);
        } else {
            healthDivider.setVisibility(0);
        }
        nqc nqcVar = new nqc(context, inflate);
        featureHomePageOnClickListener(context, nqcVar, linearLayout, customWebView);
        shoppingCartOnClickListener(context, nqcVar, customWebView, linearLayout2, titleBean);
        orderManagerOnClickListener(context, nqcVar, customWebView, linearLayout3, titleBean);
        return nqcVar;
    }

    private static void featureHomePageOnClickListener(final Context context, final nqc nqcVar, LinearLayout linearLayout, final CustomWebView customWebView) {
        linearLayout.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.operation.utils.WebViewHelp.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                HashMap hashMap = new HashMap(16);
                hashMap.put("click", "1");
                hashMap.put(Constants.ITEM_NAME, 0);
                ixx.d().d(context, AnalyticsValue.HEALTH_SHOP_WEBVIEW_MORE_ITEM_2120015.value(), hashMap, 0);
                if (customWebView != null) {
                    LogUtil.a(WebViewHelp.TAG, "mCustomWebView.load(HEALTH_SHPO_URL)");
                    GRSManager.a(context).e("healthRecommendUrl", new GrsQueryCallback() { // from class: com.huawei.operation.utils.WebViewHelp.1.1
                        @Override // com.huawei.hwcloudmodel.callback.GrsQueryCallback
                        public void onCallBackSuccess(String str) {
                            LogUtil.c(WebViewHelp.TAG, "onCallBackSuccess HEALTH_RECOMMEND url");
                            Intent intent = new Intent(context, (Class<?>) WebViewActivity.class);
                            intent.putExtra("url", str + "/miniShop/html/homePage.html");
                            intent.setFlags(32768);
                            context.startActivity(intent);
                        }

                        @Override // com.huawei.hwcloudmodel.callback.GrsQueryCallback
                        public void onCallBackFail(int i) {
                            LogUtil.c(WebViewHelp.TAG, "onCallBackFail HEALTH_RECOMMEND errorCode = ", Integer.valueOf(i));
                        }
                    });
                }
                nqcVar.b();
                ViewClickInstrumentation.clickOnView(view);
            }
        });
    }

    private static void shoppingCartOnClickListener(final Context context, final nqc nqcVar, final CustomWebView customWebView, LinearLayout linearLayout, final TitleBean titleBean) {
        linearLayout.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.operation.utils.WebViewHelp.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                HashMap hashMap = new HashMap(16);
                hashMap.put("click", "1");
                hashMap.put(Constants.ITEM_NAME, "1");
                ixx.d().d(context, AnalyticsValue.HEALTH_SHOP_WEBVIEW_MORE_ITEM_2120015.value(), hashMap, 0);
                customWebView.reload(titleBean.fetchGetShoppingCartUrl());
                nqcVar.b();
                ViewClickInstrumentation.clickOnView(view);
            }
        });
    }

    private static void orderManagerOnClickListener(final Context context, final nqc nqcVar, final CustomWebView customWebView, LinearLayout linearLayout, final TitleBean titleBean) {
        linearLayout.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.operation.utils.WebViewHelp.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                HashMap hashMap = new HashMap(16);
                hashMap.put("click", "1");
                hashMap.put(Constants.ITEM_NAME, 2);
                ixx.d().d(context, AnalyticsValue.HEALTH_SHOP_WEBVIEW_MORE_ITEM_2120015.value(), hashMap, 0);
                customWebView.reload(titleBean.fetchGetOrderManagerUrl());
                nqcVar.b();
                ViewClickInstrumentation.clickOnView(view);
            }
        });
    }

    public static void showQuitDialog(final Context context, final CustomWebView customWebView) {
        NoTitleCustomAlertDialog.Builder builder = new NoTitleCustomAlertDialog.Builder(context);
        builder.e(context.getResources().getString(R.string._2130842284_res_0x7f0212ac)).czz_(R.string._2130841130_res_0x7f020e2a, new View.OnClickListener() { // from class: com.huawei.operation.utils.WebViewHelp.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ViewClickInstrumentation.clickOnView(view);
            }
        }).czB_(R.string._2130841492_res_0x7f020f94, R.color._2131297879_res_0x7f090657, new View.OnClickListener() { // from class: com.huawei.operation.utils.WebViewHelp.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                CustomWebView customWebView2 = CustomWebView.this;
                if (customWebView2 != null) {
                    Context context2 = context;
                    if (context2 instanceof WebViewActivity) {
                        JsRegisterFunctionResUtil.callBackResStatus((WebViewActivity) context2, customWebView2.getRegisterActivityQuitFunctionRes(), "1");
                        ViewClickInstrumentation.clickOnView(view);
                    }
                }
                LogUtil.a(WebViewHelp.TAG, "showQuitDialog customWebView is null");
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        builder.e().show();
    }

    public static void setClickBi(Context context, String str) {
        HashMap hashMap = new HashMap(16);
        hashMap.put("click", "1");
        ixx.d().d(context, str, hashMap, 0);
    }

    public static void handleSetFullscreen(Context context, WebView webView, Window window, WebViewActivity webViewActivity, Message message) {
        if (message.what == 20022) {
            LogUtil.a(TAG, "SET_FULLSCREEN");
            Object obj = message.obj;
            if (obj instanceof String) {
                String str = (String) obj;
                if (str.equals("0")) {
                    webViewActivity.getWindow().addFlags(1024);
                    webViewActivity.getWindow().getDecorView().setSystemUiVisibility(4);
                    LogUtil.c(TAG, "SET_FULLSCREEN invisible.");
                    return;
                } else {
                    if (str.equals("1")) {
                        window.clearFlags(1024);
                        webViewActivity.getWindow().getDecorView().setSystemUiVisibility(0);
                        transStatusBarColor(window);
                        LogUtil.c(TAG, "SET_FULLSCREEN visible.");
                        return;
                    }
                    LogUtil.c(TAG, "SET_FULLSCREEN empty param.");
                    return;
                }
            }
            return;
        }
        handleBleService(context, webViewActivity, message, webView);
    }

    public static void handleBleService(Context context, WebViewActivity webViewActivity, Message message, WebView webView) {
        Bundle data;
        if (webViewActivity == null || message == null || (data = message.getData()) == null) {
        }
        switch (message.what) {
            case 10002:
                callJsOnConnectionStateChange(data, webView);
                break;
            case 10003:
                callJsBleServicesDiscovered(data, webView);
                break;
            case 10004:
                callJsBleCharacteristicValueChange(data, webView);
                break;
            case 10005:
                callJsBleCharacteristicWrite(data, webView);
                break;
            case 10006:
                callJsBleCharacteristicRead(data, webView);
                break;
            case 10007:
                callJsGetBleAdapterState(data, webView);
                break;
            case 10008:
            case 10012:
            case PrebakedEffectId.RT_CALENDAR_DATE /* 10014 */:
            default:
                LogUtil.c(TAG, "handleCustomTitleBar.");
                handleCustomTitleBar(context, webViewActivity, message);
                break;
            case 10009:
                callJsSaveResult(data, webView);
                break;
            case 10010:
                callJsGetResult(data, webView);
                break;
            case 10011:
                callJsGetUserInfoResult(data, webView);
                break;
            case 10013:
                callJsSaveMultipleResult(data, webView);
                break;
            case 10015:
                LogUtil.a(TAG, "DELETE_DATA_RESULT_MSG");
                callJsDeleteResult(data, webView);
                break;
        }
    }

    private static void handleCustomTitleBar(Context context, WebViewActivity webViewActivity, Message message) {
        if (message.what == 20021) {
            LogUtil.a(TAG, "UPDATE_CUSTOM_TITLE_BAR_VISIBILITY");
            Object obj = message.obj;
            if (obj instanceof String) {
                LogUtil.c(TAG, "UPDATE_CUSTOM_TITLE_BAR_VISIBILITY string type");
                String str = (String) obj;
                View findViewById = webViewActivity.findViewById(R.id.app_titlebar);
                if (findViewById instanceof RelativeLayout) {
                    setCustomTitleBarVisibility((RelativeLayout) findViewById, str);
                    return;
                }
                return;
            }
            return;
        }
        handleStartToSportPage(context, webViewActivity, message);
    }

    private static void setCustomTitleBarVisibility(RelativeLayout relativeLayout, String str) {
        if (str.equals("0")) {
            LogUtil.c(TAG, "UPDATE_CUSTOM_TITLE_BAR_VISIBILITY hide titlebar");
            relativeLayout.setVisibility(8);
        } else if (str.equals("1")) {
            LogUtil.c(TAG, "UPDATE_CUSTOM_TITLE_BAR_VISIBILITY diaplay titlebar");
            relativeLayout.setVisibility(0);
        } else {
            LogUtil.c(TAG, "UPDATE_CUSTOM_TITLE_BAR_VISIBILITY empty param.");
        }
    }

    public static void handleStartToSportPage(Context context, WebViewActivity webViewActivity, Message message) {
        switch (message.what) {
            case Constants.START_TO_MAIN_ACTIVITY /* 2032 */:
                LogUtil.a(TAG, "START_TO_MAIN_ACTIVITY.");
                startToMainActivity(context);
                webViewActivity.finish();
                break;
            case 2033:
                LogUtil.a(TAG, "START_TO_SLEEP_CARD_DETAIL.");
                startToSleepCardDetail(context);
                break;
            case 2034:
                LogUtil.a(TAG, "START_TO_RIDE.");
                startToSportTab(259, context);
                webViewActivity.finish();
                break;
            case 2035:
                LogUtil.a(TAG, "START_TO_SPORT_RECORD_SWIMMING.");
                startToSportRecordSwimming(context);
                break;
            case 2036:
            default:
                LogUtil.c(TAG, "handleStartToSportPage default.");
                break;
            case 2037:
                LogUtil.a(TAG, "START_TO_FITNESS.");
                startToSportTab(10001, context);
                webViewActivity.finish();
                break;
            case 2038:
                LogUtil.a(TAG, "START_TO_CALORIES.");
                startToCalories(context);
                break;
            case 2039:
                LogUtil.a(TAG, "START_TO_OUTDOOR_RUNNING.");
                startToSportTab(258, context);
                webViewActivity.finish();
                break;
            case Constants.START_TO_INDOOR_RUNNING /* 2040 */:
                LogUtil.a(TAG, "START_TO_INDOOR_RUNNING.");
                startToSportTab(264, context);
                webViewActivity.finish();
                break;
        }
    }

    public static void startToSportTab(int i, Context context) {
        Intent intent = new Intent();
        intent.setClassName(context, "com.huawei.health.MainActivity");
        intent.setFlags(131072);
        intent.putExtra("mLaunchSource", 7);
        intent.putExtra(BleConstants.SPORT_TYPE, i);
        intent.putExtra("isToSportTab", true);
        context.startActivity(intent);
    }

    public static void startToSportRecordSwimming(Context context) {
        Intent intent = new Intent();
        intent.setPackage(Constants.APP_PACKAGE);
        intent.setClassName(Constants.APP_PACKAGE, Constants.SPORT_HISTORY);
        intent.putExtra(BleConstants.SPORT_TYPE, 262);
        context.startActivity(intent);
    }

    public static void startToCalories(Context context) {
        Intent intent = new Intent();
        intent.setPackage(Constants.APP_PACKAGE);
        intent.setClassName(Constants.APP_PACKAGE, Constants.FITNESS_CALORIES);
        context.startActivity(intent);
    }

    public static void startToSleepCardDetail(Context context) {
        Intent intent = new Intent();
        LogUtil.a(TAG, "goto KNIT_SLEEP_DETAIL_ACTIVITY");
        intent.setPackage(Constants.APP_PACKAGE);
        intent.setClassName(Constants.APP_PACKAGE, Constants.KNIT_SLEEP_DETAIL_ACTIVITY);
        intent.putExtra(Constants.CORE_SLEEP_TODAY_HAS_DATA, false);
        intent.putExtra(Constants.SLEEP_TYPE_KEY, false);
        context.startActivity(intent);
    }

    public static void startToMainActivity(Context context) {
        Intent intent = new Intent();
        intent.setClassName(context, "com.huawei.health.MainActivity");
        intent.setFlags(131072);
        intent.putExtra("mLaunchSource", 7);
        intent.putExtra(BleConstants.SPORT_TYPE, 258);
        intent.putExtra("isToSportTab", false);
        intent.putExtra(Constants.HOME_TAB_NAME, Constants.HOME);
        context.startActivity(intent);
    }

    public static void callJsDeleteResult(Bundle bundle, WebView webView) {
        if (bundle == null) {
            LogUtil.b(TAG, "callJsDeleteResult bundle is null");
            return;
        }
        String string = bundle.getString("function");
        String string2 = bundle.getString("errCode");
        if (string == null || string2 == null) {
            LogUtil.b(TAG, "callJsDeleteResult bundle get.* is null");
            return;
        }
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("errCode", string2);
            String jSONObject2 = jSONObject.toString();
            String url = WebViewUtils.getUrl(string, jSONObject2);
            webView.loadUrl(url);
            WebViewInstrumentation.loadUrl(webView, url);
            LogUtil.c(TAG, "callJsDeleteResult ", jSONObject2);
        } catch (JSONException e) {
            LogUtil.b(TAG, "callJsDeleteResult fail exception = ", e.getMessage());
        }
    }

    public static void callJsGetUserInfoResult(Bundle bundle, WebView webView) {
        if (bundle == null) {
            LogUtil.b(TAG, "callJsGetUserInfoResult bundle is null");
            return;
        }
        String string = bundle.getString("function");
        String string2 = bundle.getString("errCode");
        String string3 = bundle.getString("data");
        if (string == null || string2 == null) {
            LogUtil.b(TAG, "callJsGetUserInfoResult bundle get.* is null");
            return;
        }
        String url = WebViewUtils.getUrl(string, string3);
        webView.loadUrl(url);
        WebViewInstrumentation.loadUrl(webView, url);
        LogUtil.c(TAG, "callJsGetUserInfoResult data = ", string3);
    }

    public static void callJsGetResult(Bundle bundle, WebView webView) {
        if (bundle == null) {
            LogUtil.b(TAG, "callJsGetResult bundle is null");
            return;
        }
        String string = bundle.getString("function");
        String string2 = bundle.getString("errCode");
        String string3 = bundle.getString("data");
        if (string == null || string2 == null) {
            LogUtil.b(TAG, "callJsGetResult bundle get.* is null");
            return;
        }
        String url = WebViewUtils.getUrl(string, string3);
        webView.loadUrl(url);
        WebViewInstrumentation.loadUrl(webView, url);
        LogUtil.c(TAG, "callJsGetResult data = ", string3);
    }

    public static void callJsSaveMultipleResult(Bundle bundle, WebView webView) {
        if (bundle == null) {
            LogUtil.h(TAG, "callJsSaveMultipleResult bundle is null");
            return;
        }
        String string = bundle.getString("function");
        String string2 = bundle.getString("errCode");
        LogUtil.a(TAG, "function = ", string, ", errCodeNum = ", string2);
        if (string == null || string2 == null) {
            LogUtil.b(TAG, "callJsSaveMultipleResult bundle get.* is null");
            return;
        }
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("errCode", string2);
            String url = WebViewUtils.getUrl(string, jSONObject.toString());
            webView.loadUrl(url);
            WebViewInstrumentation.loadUrl(webView, url);
        } catch (JSONException e) {
            LogUtil.b(TAG, "callJsSaveMultipleResult fail exception =", e.getMessage());
        }
    }

    public static void callJsSaveResult(Bundle bundle, WebView webView) {
        if (bundle == null) {
            LogUtil.b(TAG, "callJsSaveResult bundle is null");
            return;
        }
        String string = bundle.getString("function");
        String string2 = bundle.getString("errCode");
        String string3 = bundle.getString("data");
        if (string == null || string2 == null) {
            LogUtil.b(TAG, "callJsSaveResult bundle get.* is null");
            return;
        }
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("errCode", string2);
            if (string3 != null) {
                jSONObject.put("data", string3);
            }
            String jSONObject2 = jSONObject.toString();
            String url = WebViewUtils.getUrl(string, jSONObject2);
            webView.loadUrl(url);
            WebViewInstrumentation.loadUrl(webView, url);
            LogUtil.c(TAG, "callJsSaveResult ", jSONObject2);
        } catch (JSONException e) {
            LogUtil.b(TAG, "callJsSaveResult fail exception = ", e.getMessage());
        }
    }

    public static void callJsGetBleAdapterState(Bundle bundle, WebView webView) {
        if (bundle == null) {
            LogUtil.b(TAG, "callJsGetBleAdapterState bundle is null");
            return;
        }
        String string = bundle.getString("function");
        boolean z = bundle.getBoolean(BleConstants.KEY_DISCOVERING);
        boolean z2 = bundle.getBoolean(BleConstants.KEY_AVAILABLE);
        String string2 = bundle.getString("errCode");
        if (string == null || string2 == null) {
            LogUtil.b(TAG, "callJsGetBleAdapterState bundle get.* is null");
            return;
        }
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(BleConstants.KEY_DISCOVERING, z);
            jSONObject.put(BleConstants.KEY_AVAILABLE, z2);
            jSONObject.put("errCode", string2);
            String jSONObject2 = jSONObject.toString();
            String url = WebViewUtils.getUrl(string, jSONObject2);
            webView.loadUrl(url);
            WebViewInstrumentation.loadUrl(webView, url);
            LogUtil.c(TAG, "callJsGetBleAdapterState ", jSONObject2);
        } catch (JSONException e) {
            LogUtil.b(TAG, "callJsGetBleAdapterState fail exception = ", e.getMessage());
        }
    }

    public static void callJsBleCharacteristicRead(Bundle bundle, WebView webView) {
        if (bundle == null) {
            LogUtil.b(TAG, "callJsBleCharacteristicRead bundle is null");
            return;
        }
        String string = bundle.getString("function");
        String string2 = bundle.getString("errCode");
        String string3 = bundle.getString("data");
        if (string == null || string2 == null || string3 == null) {
            LogUtil.b(TAG, "callJsBleCharacteristicRead bundle get.* is null");
            return;
        }
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("data", string3);
            jSONObject.put("errCode", string2);
            String jSONObject2 = jSONObject.toString();
            String url = WebViewUtils.getUrl(string, jSONObject2);
            webView.loadUrl(url);
            WebViewInstrumentation.loadUrl(webView, url);
            LogUtil.c(TAG, "callJsBleCharacteristicRead ", jSONObject2);
        } catch (JSONException e) {
            LogUtil.b(TAG, "callJsBleCharacteristicRead fail exception = ", e.getMessage());
        }
    }

    public static void callJsBleCharacteristicWrite(Bundle bundle, WebView webView) {
        if (bundle == null) {
            LogUtil.b(TAG, "callJsBleCharacteristicWrite bundle is null");
            return;
        }
        String string = bundle.getString("function");
        String string2 = bundle.getString("errCode");
        if (string == null || string2 == null) {
            LogUtil.b(TAG, "callJsBleCharacteristicWrite bundle get.* is null");
            return;
        }
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("errCode", string2);
            String jSONObject2 = jSONObject.toString();
            String url = WebViewUtils.getUrl(string, jSONObject2);
            webView.loadUrl(url);
            WebViewInstrumentation.loadUrl(webView, url);
            LogUtil.c(TAG, "callJsBleCharacteristicWrite ", jSONObject2);
        } catch (JSONException e) {
            LogUtil.b(TAG, "callJsBleCharacteristicWrite fail exception = ", e.getMessage());
        }
    }

    public static void callJsBleCharacteristicValueChange(Bundle bundle, WebView webView) {
        if (bundle == null) {
            LogUtil.b(TAG, "callJsBleCharacteristicValueChange bundle is null");
            return;
        }
        String string = bundle.getString("function");
        String string2 = bundle.getString("deviceId");
        String string3 = bundle.getString(BleConstants.KEY_CHARACTERISTICID);
        String string4 = bundle.getString("data");
        if (string == null || string2 == null || string3 == null || string4 == null) {
            LogUtil.b(TAG, "callJsBleCharacteristicValueChange bundle get.* is null");
            return;
        }
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("deviceId", string2);
            jSONObject.put("serviceId", "");
            jSONObject.put(BleConstants.KEY_CHARACTERISTICID, string3);
            jSONObject.put("data", string4);
            String jSONObject2 = jSONObject.toString();
            String url = WebViewUtils.getUrl(string, jSONObject2);
            webView.loadUrl(url);
            WebViewInstrumentation.loadUrl(webView, url);
            LogUtil.c(TAG, "callJsBleCharacteristicValueChange ", jSONObject2);
        } catch (JSONException e) {
            LogUtil.b(TAG, "callJsBleCharacteristicValueChange fail exception = ", e.getMessage());
        }
    }

    public static void callJsBleServicesDiscovered(Bundle bundle, WebView webView) {
        if (bundle == null) {
            LogUtil.b(TAG, "callJsBleServicesDiscovered bundle is null");
            return;
        }
        String string = bundle.getString("errCode");
        String string2 = bundle.getString("function");
        if (string == null || string2 == null) {
            LogUtil.b(TAG, "callJsBleServicesDiscovered bundle get.* is null");
            return;
        }
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("errCode", string);
            String jSONObject2 = jSONObject.toString();
            String url = WebViewUtils.getUrl(string2, jSONObject2);
            webView.loadUrl(url);
            WebViewInstrumentation.loadUrl(webView, url);
            LogUtil.c(TAG, "callJsBleServicesDiscovered ", jSONObject2);
        } catch (JSONException e) {
            LogUtil.b(TAG, "callJsBleServicesDiscovered fail exception = ", e.getMessage());
        }
    }

    public static void callJsOnConnectionStateChange(Bundle bundle, WebView webView) {
        if (bundle == null) {
            LogUtil.b(TAG, "callJsOnConnectionStateChange bundle is null");
            return;
        }
        String string = bundle.getString("deviceId");
        boolean z = bundle.getBoolean("connected");
        String string2 = bundle.getString("function");
        if (string == null || string2 == null) {
            LogUtil.b(TAG, "callJsOnConnectionStateChange bundle get.* is null");
            return;
        }
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("deviceId", string);
            jSONObject.put("connected", z);
            jSONObject.put(BleConstants.KEY_CONNECTSTATE, z);
            String jSONObject2 = jSONObject.toString();
            String url = WebViewUtils.getUrl(string2, jSONObject2);
            webView.loadUrl(url);
            WebViewInstrumentation.loadUrl(webView, url);
            LogUtil.c(TAG, "callJsOnConnectionStateChange ", jSONObject2);
        } catch (JSONException e) {
            LogUtil.b(TAG, "callJsOnConnectionStateChange fail exception = ", e.getMessage());
        }
    }

    public static void showServiceTips(final Context context, final WebView webView, String str, String str2, final String str3) {
        PluginBaseAdapter adapter = PluginOperation.getInstance(context).getAdapter();
        PluginOperationAdapter initAdapter = adapter instanceof PluginOperationAdapter ? (PluginOperationAdapter) adapter : initAdapter();
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2) || TextUtils.isEmpty(str3)) {
            LogUtil.a(TAG, "serviceId is null or huid is null or function is null");
            String url = WebViewUtils.getUrl(str3, String.valueOf(1002));
            webView.loadUrl(url);
            WebViewInstrumentation.loadUrl(webView, url);
            return;
        }
        if (initAdapter == null) {
            LogUtil.h(TAG, "pluginOperationAdapter is null");
            return;
        }
        int authType = initAdapter.getAuthType(str, str2);
        LogUtil.a(TAG, "authType = ", Integer.valueOf(authType));
        if (authType == 1) {
            LogUtil.a(TAG, "authType == 1");
            String url2 = WebViewUtils.getUrl(str3, "0");
            webView.loadUrl(url2);
            WebViewInstrumentation.loadUrl(webView, url2);
            return;
        }
        if (authType == 0) {
            LogUtil.a(TAG, "authType == 0");
            if (str2.contains("*")) {
                String[] split = str2.split("\\*");
                if (split.length <= 1) {
                    return;
                }
                String str4 = split[0];
                String str5 = split[1];
                LogUtil.a(TAG, "dialogType = ", str4, ",serviceName = ", str5);
                if (!Constants.HMS.equals(str4) && !Constants.NO_HMS.equals(str4)) {
                    LogUtil.a(TAG, "serviceId is not Legal!");
                    String url3 = WebViewUtils.getUrl(str3, Constants.ERROR_SERVICE_ID_STR);
                    webView.loadUrl(url3);
                    WebViewInstrumentation.loadUrl(webView, url3);
                    return;
                }
                initAdapter.showServiceTips(context, initShowTipsParams(str, str2, str5, str4), new IBaseResponseCallback() { // from class: com.huawei.operation.utils.WebViewHelp.6
                    @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                    /* renamed from: onResponse */
                    public void d(int i, Object obj) {
                        if (obj instanceof String) {
                            WebViewHelp.handleServiceTipsCallback(context, webView, (String) obj, str3);
                        }
                    }
                });
            }
        }
    }

    private static HashMap<String, String> initShowTipsParams(String str, String str2, String str3, String str4) {
        HashMap<String, String> hashMap = new HashMap<>(4);
        hashMap.put("huid", str);
        hashMap.put("serviceId", str2);
        hashMap.put(JsUtil.SERVICE_NAME, str3);
        hashMap.put("dialogType", str4);
        return hashMap;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void handleServiceTipsCallback(Context context, WebView webView, String str, String str2) {
        if ("1".equals(str)) {
            LogUtil.a(TAG, "Not agree service auth");
            String url = WebViewUtils.getUrl(str2, "1");
            webView.loadUrl(url);
            WebViewInstrumentation.loadUrl(webView, url);
            ((Activity) context).finish();
            return;
        }
        if ("0".equals(str)) {
            LogUtil.a(TAG, "agree service auth");
            String url2 = WebViewUtils.getUrl(str2, "0");
            webView.loadUrl(url2);
            WebViewInstrumentation.loadUrl(webView, url2);
            return;
        }
        LogUtil.c(TAG, "showServiceTips authStatus is not 0 and 1");
    }

    public static void jumpToMain(Context context, PackageManager packageManager) {
        ComponentName component;
        LogUtil.a(TAG, "jumpToMain");
        Intent intent = new Intent();
        Intent launchIntentForPackage = packageManager.getLaunchIntentForPackage(Constants.APP_PACKAGE);
        if (launchIntentForPackage == null || (component = launchIntentForPackage.getComponent()) == null) {
            return;
        }
        String className = component.getClassName();
        if (TextUtils.isEmpty(className)) {
            return;
        }
        try {
            intent.setComponent(new ComponentName(Constants.APP_PACKAGE, className));
            intent.setAction("android.intent.action.MAIN");
            intent.addCategory("android.intent.category.LAUNCHER");
            intent.setFlags(268435456);
            LogUtil.c(TAG, "intent = ", intent);
            context.startActivity(intent);
        } catch (ActivityNotFoundException unused) {
            LogUtil.b(TAG, "activity not found exception.");
        } catch (IllegalStateException e) {
            LogUtil.b(TAG, "getHealthAPPIntent()", e.getMessage());
        }
    }

    public static String getCurrentPageUrl(WebView webView) {
        LogUtil.a(TAG, "getCurrentPageUrl");
        return webView != null ? webView.getUrl() : "";
    }

    public static String getWebViewOriginalUrl(WebView webView) {
        LogUtil.a(TAG, "getWebViewOriginalUrl");
        return webView != null ? webView.getOriginalUrl() : "";
    }

    public static void setBiAnalysis(String str) {
        LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<>(2);
        linkedHashMap.put("flag", str);
        OpAnalyticsUtil.getInstance().setEvent2nd(OperationKey.HEALTH_APP_WEBVIEW_LOADING_SUCCESS_RATE_80060001.value(), linkedHashMap);
    }

    public static void takePhoto(int i, Intent intent, CustomWebView customWebView) {
        if (customWebView == null) {
            LogUtil.h(TAG, "takePhoto: webView is null");
            return;
        }
        if (customWebView.getUploadMessageForAndroid5() == null) {
            LogUtil.a(TAG, "onActivityResult takePhoto REQ_CODE_TAKE_PHOTO getUploadMessageForAndroid5 null");
            return;
        }
        if (i == 0) {
            LogUtil.a(TAG, "onActivityResult takePhoto REQ_CODE_TAKE_PHOTO resultCode == 0");
            customWebView.getUploadMessageForAndroid5().onReceiveValue(new Uri[0]);
            customWebView.setUploadMessageForAndroid5(null);
            return;
        }
        LogUtil.a(TAG, "onActivityResult takePhoto other");
        Uri imageUri = (intent == null || i != -1) ? customWebView.getImageUri() : intent.getData();
        if (imageUri != null) {
            LogUtil.a(TAG, "onActivityResult takePhoto REQ_CODE_TAKE_PHOTO result != null");
            customWebView.getUploadMessageForAndroid5().onReceiveValue(new Uri[]{imageUri});
        } else {
            LogUtil.a(TAG, "onActivityResult takePhoto REQ_CODE_TAKE_PHOTO result is null");
            customWebView.getUploadMessageForAndroid5().onReceiveValue(new Uri[0]);
        }
        customWebView.setUploadMessageForAndroid5(null);
    }

    public static void showBottomUiMenu(Window window, int i) {
        window.getDecorView().setSystemUiVisibility(i);
        LogUtil.a(TAG, "after showBottomUiMenu uiOptions = ", Integer.valueOf(i));
    }

    public static void transStatusBarColor(Window window) {
        window.getDecorView().setSystemUiVisibility(9216);
        window.setStatusBarColor(0);
    }

    public static void setBiSource(String str, CustomWebView customWebView, String str2, String str3, String str4) {
        String str5;
        if (!TextUtils.isEmpty(str)) {
            str5 = customWebView.getUrlBiType(str);
            if (TextUtils.isEmpty(str5)) {
                LogUtil.a(TAG, "type is empty");
            }
        } else {
            LogUtil.a(TAG, "url is empty");
            str5 = "";
        }
        LogUtil.c(TAG, "id = ", str2, " name = ", str3, " source = ", str4, " type = ", str5);
        HashMap hashMap = new HashMap(16);
        hashMap.put("click", "1");
        hashMap.put("id", str2);
        hashMap.put("name", str3);
        hashMap.put("source", str4);
        hashMap.put("url", str);
        hashMap.put("type", str5);
        ixx.d().d(BaseApplication.e(), AnalyticsValue.HEALTH_SHOP_SOURCE_2120006.value(), hashMap, 0);
    }

    public static void setTheMiaoText(boolean z, HealthTextView healthTextView, ImageView imageView) {
        if (z) {
            healthTextView.setTextColor(BaseApplication.e().getResources().getColor(R.color._2131296927_res_0x7f09029f));
            healthTextView.getPaint().setFakeBoldText(true);
            imageView.setVisibility(0);
            imageView.setBackgroundColor(BaseApplication.e().getResources().getColor(R.color._2131296927_res_0x7f09029f));
        } else {
            healthTextView.setTextColor(BaseApplication.e().getResources().getColor(R.color._2131299241_res_0x7f090ba9));
            healthTextView.getPaint().setFakeBoldText(false);
            imageView.setVisibility(4);
        }
        healthTextView.setClickable(!z);
    }

    public static String getTheVmallUrl(String str) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.b(TAG, "getTheVmallUrl mHealthRecommendHost is empty");
            return "";
        }
        return str + UriConstants.VMALL_URL_STRING;
    }

    public static String getMiaoHealthUrlString(String str) {
        if (CommonUtil.cc()) {
            return str + UriConstants.MIAO_HEALTH_URL_TEST;
        }
        if (CommonUtil.as()) {
            return str + UriConstants.MIAO_HEALTH_URL_BETA;
        }
        return str + UriConstants.MIAO_HEALTH_URL;
    }

    public static boolean checkOrderManagerPage(String str) {
        LogUtil.a(TAG, "checkOrderManagerPage url");
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        String url = GRSManager.a(com.huawei.hwcommonmodel.application.BaseApplication.getContext()).getUrl("healthRecommendUrl");
        String e = jah.c().e("domain_mlhwm_miaohealth_net");
        String e2 = jah.c().e("domain_hwmltest_miaomore");
        if (CommonUtil.cc()) {
            if (!TextUtils.equals(url + UriConstants.VMALL_URL_STRING, str)) {
                if (!TextUtils.equals(e2 + UriConstants.MIAO_HEALTH_URL_CHECK, str)) {
                    return false;
                }
            }
            return true;
        }
        if (!TextUtils.equals(url + UriConstants.VMALL_URL_STRING, str)) {
            if (!TextUtils.equals(e + UriConstants.MIAO_HEALTH_URL, str)) {
                return false;
            }
        }
        return true;
    }

    public static String filterSpecialStr(String str) {
        String trim = Pattern.compile(Constants.SPECIAL_STRING).matcher(str).replaceAll("").trim();
        LogUtil.a(TAG, "filterSpecialStr replaceStr=", trim);
        return trim;
    }

    public static PluginOperationAdapter initAdapter() {
        PluginOperationAdapter pluginOperationAdapter;
        Exception e;
        try {
            Object invoke = Class.forName("com.huawei.hwadpaterhealthmgr.PluginOperationAdapterImpl").getMethod("getInstance", Context.class).invoke(null, BaseApplication.e());
            if (!(invoke instanceof PluginOperationAdapter)) {
                LogUtil.b(TAG, "initAdapter fail: get Instance fail");
                return null;
            }
            pluginOperationAdapter = (PluginOperationAdapter) invoke;
            try {
                LogUtil.a(TAG, "initAdapter success:", pluginOperationAdapter);
                PluginOperation.getInstance(BaseApplication.e()).setAdapter(pluginOperationAdapter);
                return pluginOperationAdapter;
            } catch (ClassNotFoundException e2) {
                e = e2;
                LogUtil.b(TAG, "initAdapter fail:", e.getMessage());
                return pluginOperationAdapter;
            } catch (IllegalAccessException e3) {
                e = e3;
                LogUtil.b(TAG, "initAdapter fail:", e.getMessage());
                return pluginOperationAdapter;
            } catch (IllegalArgumentException e4) {
                e = e4;
                LogUtil.b(TAG, "initAdapter fail:", e.getMessage());
                return pluginOperationAdapter;
            } catch (NoSuchMethodException e5) {
                e = e5;
                LogUtil.b(TAG, "initAdapter fail:", e.getMessage());
                return pluginOperationAdapter;
            } catch (InvocationTargetException e6) {
                e = e6;
                LogUtil.b(TAG, "initAdapter fail:", e.getMessage());
                return pluginOperationAdapter;
            }
        } catch (ClassNotFoundException | IllegalAccessException | IllegalArgumentException | NoSuchMethodException | InvocationTargetException e7) {
            pluginOperationAdapter = null;
            e = e7;
        }
    }

    public static void setFullScreen(Window window) {
        LogUtil.a(TAG, "setFullScreen()");
        window.getDecorView().setSystemUiVisibility(5382);
        if (Build.VERSION.SDK_INT >= 28) {
            WindowManager.LayoutParams attributes = window.getAttributes();
            attributes.layoutInDisplayCutoutMode = 1;
            window.setAttributes(attributes);
        }
    }

    public static boolean isAnnualReportUrl(String str) {
        return !TextUtils.isEmpty(str) && str.contains(ANNUAL_URL_CONTENT);
    }

    public static boolean isGroupChallenge(String str) {
        return !TextUtils.isEmpty(str) && str.contains(GROUP_CHALLENGE_URL_CONTENT);
    }

    public static void setRightButtonVisibility(int i, CustomTitleBar customTitleBar) {
        customTitleBar.setRightButtonVisibility(i);
        if (i == 0) {
            customTitleBar.setPaddingRelative(0, 0, 0, 0);
        } else {
            customTitleBar.setPaddingRelative(0, 0, (int) BaseApplication.e().getResources().getDimension(R.dimen._2131362365_res_0x7f0a023d), 0);
        }
    }

    public static boolean isSvgUrl(String str) {
        return !TextUtils.isEmpty(str) && str.endsWith(WatchFaceProvider.SVG_SUFFIX);
    }

    public static void shareWebImage(final String str) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.h(TAG, "shareWebImage: data is empty");
        } else {
            ThreadPoolManager.d().execute(new Runnable() { // from class: com.huawei.operation.utils.WebViewHelp.7
                @Override // java.lang.Runnable
                public void run() {
                    Bitmap bitmap;
                    if (UrlUtil.isNetworkUrl(str)) {
                        bitmap = jei.bGs_(str);
                    } else {
                        try {
                            String[] split = str.split(",");
                            byte[] decode = Base64.decode(split.length == 1 ? split[0] : split[1], 2);
                            bitmap = BitmapFactory.decodeByteArray(decode, 0, decode.length);
                        } catch (IllegalArgumentException unused) {
                            LogUtil.b(WebViewHelp.TAG, "decryptFromBase64 UnsupportedEncoding, data=", str);
                            bitmap = null;
                        }
                    }
                    if (bitmap == null) {
                        LogUtil.h(WebViewHelp.TAG, "shareWebImage: bitmap  is null");
                        return;
                    }
                    final fdu fduVar = new fdu(1);
                    fduVar.awp_(bitmap);
                    fduVar.b(3);
                    HandlerExecutor.e(new Runnable() { // from class: com.huawei.operation.utils.WebViewHelp.7.1
                        @Override // java.lang.Runnable
                        public void run() {
                            OperationUtils.share(BaseApplication.e(), fduVar, false);
                        }
                    });
                }
            });
        }
    }

    public static void initGlobalBiParam(String str) {
        synchronized (WebViewHelp.class) {
            if (TextUtils.isEmpty(str)) {
                return;
            }
            mGlobalBiParams = new HashMap<>();
            Uri parse = Uri.parse(str);
            if (parse.isOpaque()) {
                return;
            }
            for (String str2 : GLOBAL_BI_PARAM_KEYS) {
                String queryParameter = parse.getQueryParameter(str2);
                if (!TextUtils.isEmpty(queryParameter)) {
                    mGlobalBiParams.put(str2, queryParameter);
                }
            }
        }
    }

    public static void addGlobalBiValue(String str, String str2) {
        synchronized (WebViewHelp.class) {
            if (mGlobalBiParams != null) {
                mGlobalBiParams = new HashMap<>();
            }
            mGlobalBiParams.put(str, str2);
        }
    }

    private static String getGlobalBiValue(String str) {
        synchronized (WebViewHelp.class) {
            HashMap<String, String> hashMap = mGlobalBiParams;
            if (hashMap == null) {
                return "";
            }
            return hashMap.get(str);
        }
    }

    public static String getBiPullFrom() {
        String globalBiValue;
        synchronized (WebViewHelp.class) {
            globalBiValue = getGlobalBiValue(BI_KEY_PULL_FROM);
        }
        return globalBiValue;
    }
}
