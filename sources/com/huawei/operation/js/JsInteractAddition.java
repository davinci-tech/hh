package com.huawei.operation.js;

import android.content.Context;
import android.text.TextUtils;
import android.util.Pair;
import com.google.gson.Gson;
import com.huawei.hms.framework.network.restclient.hwhttp.RequestBody;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.operation.OpAnalyticsConstants;
import com.huawei.operation.OpAnalyticsUtil;
import com.huawei.operation.OperationKey;
import com.huawei.operation.adapter.PluginOperationAdapter;
import com.huawei.operation.adapter.SendCurrentUrlCallback;
import com.huawei.operation.adapter.SportData;
import com.huawei.operation.beans.JsSportData;
import com.huawei.operation.beans.OauthAtRsp;
import com.huawei.operation.beans.OauthInfoRsp;
import com.huawei.operation.ble.BleConstants;
import com.huawei.operation.h5pro.DataTransferInterceptorImpl;
import com.huawei.operation.h5pro.H5MiniProgramSecurityHelper;
import com.huawei.operation.jsoperation.JsUtil;
import com.huawei.operation.utils.Constants;
import com.huawei.operation.utils.Utils;
import com.huawei.phoneservice.faq.base.constants.FaqConstants;
import com.huawei.ui.commonui.base.BaseActivity;
import defpackage.lqi;
import defpackage.nsn;
import health.compact.a.CommonUtil;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes9.dex */
public class JsInteractAddition {
    public static final String BI_ERROR_CODE_INVALID_AT = "100001";
    private static final String GET_AT_HOST = "/oauth2/v3/token";
    private static final String GET_INFO_HOST = "/rest.php?nsp_svc=GOpen.User.getInfo";
    private static final String TAG = "PluginOperation_JsInteractAddition";

    private JsInteractAddition() {
    }

    public static JsInteractAddition getInstance() {
        return Instance.INSTANCE;
    }

    static class Instance {
        public static final JsInteractAddition INSTANCE = new JsInteractAddition();

        private Instance() {
        }
    }

    private String getAppInfoForWeb(Map<String, String> map, String str) {
        String str2 = map != null ? map.get(str) : "";
        LogUtil.c(TAG, "getAppInfo return:", str2);
        return str2;
    }

    public String getVersionForWeb(Map<String, String> map) {
        return getAppInfoForWeb(map, "grayVersion");
    }

    public String getAppIdForWeb(Map<String, String> map) {
        return getAppInfoForWeb(map, "appId");
    }

    public String getAppTypeForWeb(Map<String, String> map) {
        return getAppInfoForWeb(map, "appType");
    }

    public String getDeviceTypeForWeb(Map<String, String> map) {
        return getAppInfoForWeb(map, "deviceModel");
    }

    public String getDeviceId(Context context, SendCurrentUrlCallback sendCurrentUrlCallback, PluginOperationAdapter pluginOperationAdapter, Map<String, String> map) {
        if (!checkIsHuaweiWhiteUrl(context, sendCurrentUrlCallback, pluginOperationAdapter)) {
            return "";
        }
        String str = map != null ? map.get("deviceId") : "";
        LogUtil.c(TAG, "getDeviceId return:", str);
        return TextUtils.isEmpty(str) ? "clientnull" : str;
    }

    public String getSystemVersionForWeb(Map<String, String> map) {
        return getAppInfoForWeb(map, "sysVersion");
    }

    public String getBindDeviceTypeForWeb(Map<String, String> map) {
        return getAppInfoForWeb(map, "productType");
    }

    public String getPhoneTypeForWeb() {
        String str;
        if (CommonUtil.bh()) {
            LogUtil.a(TAG, "PhoneType is HW");
            str = "HW";
        } else {
            LogUtil.a(TAG, "PhoneType is 3RD");
            str = "3RD";
        }
        LogUtil.a(TAG, "getPhoneType return:", str);
        return str;
    }

    public String getLanguageForWeb(Map<String, String> map) {
        return getAppInfoForWeb(map, FaqConstants.FAQ_LANGUAGE);
    }

    public String getEnvironmentForWeb(Map<String, String> map) {
        return getAppInfoForWeb(map, "environment");
    }

    public String getNickName(Context context, SendCurrentUrlCallback sendCurrentUrlCallback, PluginOperationAdapter pluginOperationAdapter, Map<String, String> map) {
        return !checkIsHuaweiWhiteUrl(context, sendCurrentUrlCallback, pluginOperationAdapter) ? "" : getAppInfoForWeb(map, "name");
    }

    public String getUserPhoto(Context context, SendCurrentUrlCallback sendCurrentUrlCallback, PluginOperationAdapter pluginOperationAdapter, Map<String, String> map) {
        return !checkIsHuaweiWhiteUrl(context, sendCurrentUrlCallback, pluginOperationAdapter) ? "" : getAppInfoForWeb(map, "portraitUrl");
    }

    public boolean isTahitiForWeb(Context context, SendCurrentUrlCallback sendCurrentUrlCallback, PluginOperationAdapter pluginOperationAdapter) {
        if (!checkIsHuaweiWhiteUrl(context, sendCurrentUrlCallback, pluginOperationAdapter) && !Utils.isWhiteFileUrl(sendCurrentUrlCallback.getWebViewUrl())) {
            return false;
        }
        boolean ag = nsn.ag(context);
        LogUtil.a(TAG, "isTahiti return:", Boolean.valueOf(ag));
        return ag;
    }

    public boolean checkIsHuaweiWhiteUrl(Context context, SendCurrentUrlCallback sendCurrentUrlCallback, PluginOperationAdapter pluginOperationAdapter) {
        if (!Utils.needAuth(context)) {
            LogUtil.a(TAG, "checkIsHuaweiWhiteUrl do not need auth version!");
            return true;
        }
        if (pluginOperationAdapter == null || sendCurrentUrlCallback == null) {
            LogUtil.a(TAG, "(operationAdapter is null) || (currentUrlCallback is null)");
            return false;
        }
        if (H5MiniProgramSecurityHelper.isSuperTrustedMiniProgram(sendCurrentUrlCallback.getWebViewUrl())) {
            LogUtil.a(TAG, "checkUrlIsLegalNew is isSuperTrustedMiniProgram");
            return true;
        }
        return pluginOperationAdapter.checkWhiteUrl(sendCurrentUrlCallback.getWebViewUrl());
    }

    public String getFitnessDataForWeb(Context context, SendCurrentUrlCallback sendCurrentUrlCallback, PluginOperationAdapter pluginOperationAdapter, String str, String str2) {
        if (!checkIsHuaweiWhiteUrl(context, sendCurrentUrlCallback, pluginOperationAdapter)) {
            LogUtil.a(TAG, "the current Url is not Huawei super WhiteUrl");
            return BleConstants.CODE_AUTHORIZED_FAIL;
        }
        if (!JsUtil.checkParamIsLegal(str, str2)) {
            LogUtil.a(TAG, "getFitnessData params is not legal");
            return "1001";
        }
        List<Map<String, Object>> recordsByDateRange = pluginOperationAdapter.getRecordsByDateRange(str, str2);
        if (recordsByDateRange == null || recordsByDateRange.isEmpty()) {
            LogUtil.b(TAG, "sportListData is null!");
            return "";
        }
        LogUtil.a(TAG, "getFitnessData sportListData.size = ", Integer.valueOf(recordsByDateRange.size()));
        JSONArray jSONArray = new JSONArray();
        for (Map<String, Object> map : recordsByDateRange) {
            if (CommonUtil.aq()) {
                LogUtil.c(TAG, "getFitnessData sportData.get(date)=", map.get("date"), ",sportData.get(totalCalorie)= ", map.get(JsUtil.SUGGESTION_TOTAL_CALORIE), ",sportData.get(totalDuration)=", map.get("totalduration"));
            }
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("date", map.get("date"));
                jSONObject.put(JsUtil.SUGGESTION_TOTAL_CALORIE, map.get(JsUtil.SUGGESTION_TOTAL_CALORIE));
                jSONObject.put("totalduration", map.get("totalduration"));
                jSONArray.put(jSONObject);
            } catch (JSONException unused) {
                LogUtil.a(TAG, "getFitnessData JSONException");
            }
        }
        return jSONArray.toString();
    }

    public void getLogForWeb(Context context, SendCurrentUrlCallback sendCurrentUrlCallback, PluginOperationAdapter pluginOperationAdapter, String str, String str2) {
        if (!checkIsHuaweiWhiteUrl(context, sendCurrentUrlCallback, pluginOperationAdapter) && !Utils.isWhiteFileUrl(sendCurrentUrlCallback.getWebViewUrl())) {
            LogUtil.a(TAG, "the current Url is not Huawei super White Url");
            return;
        }
        if (Constants.LOG_DEBUG.equals(str)) {
            LogUtil.c(TAG, "H5 log content:", str2);
            return;
        }
        if ("INFO".equals(str)) {
            LogUtil.a(TAG, "H5 log content:", str2);
            return;
        }
        if (Constants.LOG_ERROR.equals(str)) {
            LogUtil.b(TAG, "H5 log content:", str2);
        } else if (Constants.LOG_WARN.equals(str)) {
            LogUtil.h(TAG, "H5 log content:", str2);
        } else {
            LogUtil.c(TAG, "log level:", str, ", content:", str2);
        }
    }

    public String getServerTokenForWeb(Context context, SendCurrentUrlCallback sendCurrentUrlCallback, PluginOperationAdapter pluginOperationAdapter) {
        if (!checkIsHuaweiWhiteUrl(context, sendCurrentUrlCallback, pluginOperationAdapter) || CommonUtil.z(context) || !TextUtils.isEmpty(LoginInit.getInstance(context).getAccountInfo(1015))) {
            return "";
        }
        LogUtil.a(TAG, "getServerTokenForWeb: at is empty");
        reportErrorCode(OperationKey.HEALTH_APP_H5_ERROR_CODE_80060003.value(), BI_ERROR_CODE_INVALID_AT);
        return DataTransferInterceptorImpl.TOKEN_PLACEHOLDER;
    }

    public String getSportDataForWeb(Context context, SendCurrentUrlCallback sendCurrentUrlCallback, PluginOperationAdapter pluginOperationAdapter) {
        if (!checkIsHuaweiWhiteUrl(context, sendCurrentUrlCallback, pluginOperationAdapter)) {
            return "";
        }
        String format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH).format(new Date(System.currentTimeMillis()));
        JsSportData jsSportData = new JsSportData();
        jsSportData.setVersion("1.0.1");
        jsSportData.setCurrentTime(format);
        SportData sportData = pluginOperationAdapter.getSportData();
        if (sportData != null) {
            jsSportData.configData(sportData.getData());
        }
        return new Gson().toJson(jsSportData);
    }

    public String getSafeRegionWidth(Context context, SendCurrentUrlCallback sendCurrentUrlCallback, PluginOperationAdapter pluginOperationAdapter) {
        LogUtil.a(TAG, "getSafeRegionWidth");
        if (!checkIsHuaweiWhiteUrl(context, sendCurrentUrlCallback, pluginOperationAdapter)) {
            return "";
        }
        JSONObject jSONObject = new JSONObject();
        Pair<Integer, Integer> safeRegionWidth = BaseActivity.getSafeRegionWidth();
        try {
            jSONObject.put("paddingStart", safeRegionWidth.first);
            jSONObject.put("paddingEnd", safeRegionWidth.second);
        } catch (JSONException unused) {
            LogUtil.a(TAG, "getSafeRegionWidth JSONException");
        }
        return jSONObject.toString();
    }

    public boolean isSupportStepCounter(Context context, SendCurrentUrlCallback sendCurrentUrlCallback, PluginOperationAdapter pluginOperationAdapter) {
        if (checkIsHuaweiWhiteUrl(context, sendCurrentUrlCallback, pluginOperationAdapter) && pluginOperationAdapter != null) {
            return pluginOperationAdapter.isSupportStepCounter(context);
        }
        return false;
    }

    public boolean isSupportHarvard() {
        return CommonUtil.bz();
    }

    public String getAtFromUp(String str, String str2, String str3, String str4, String str5) {
        LogUtil.a(TAG, "getAtFromUp appId=", str2);
        HashMap hashMap = new HashMap();
        hashMap.put("Content-Type", RequestBody.HEAD_VALUE_CONTENT_TYPE_URLENCODED);
        StringBuilder sb = new StringBuilder("grant_type=authorization_code&client_id=");
        sb.append(str2);
        sb.append("&client_secret=");
        try {
            sb.append(URLEncoder.encode(str3, StandardCharsets.UTF_8.name()));
            sb.append("&code=");
            sb.append(URLEncoder.encode(str4, StandardCharsets.UTF_8.name()));
            sb.append("&redirect_uri=");
            if (TextUtils.isEmpty(str5)) {
                str5 = "hms://redirect_uri";
            }
            sb.append(URLEncoder.encode(str5, StandardCharsets.UTF_8.name()));
            OauthAtRsp oauthAtRsp = (OauthAtRsp) lqi.d().d(str + GET_AT_HOST, hashMap, sb.toString(), OauthAtRsp.class);
            if (oauthAtRsp == null) {
                LogUtil.b(TAG, "getAtFromUp oauthAtRsp is null");
                return "";
            }
            return oauthAtRsp.getAccessToken();
        } catch (UnsupportedEncodingException unused) {
            LogUtil.b(TAG, "getUserInfoFromUp encode Exception UnsupportedEncodingException");
            return "";
        }
    }

    public JSONObject getUserInfoFromUp(String str, String str2) {
        LogUtil.a(TAG, "getUserInfoFromUp enter");
        HashMap hashMap = new HashMap();
        hashMap.put("Content-Type", RequestBody.HEAD_VALUE_CONTENT_TYPE_URLENCODED);
        StringBuilder sb = new StringBuilder("access_token=");
        try {
            sb.append(URLEncoder.encode(str, StandardCharsets.UTF_8.name()));
            sb.append("&getNickName=1");
            OauthInfoRsp oauthInfoRsp = (OauthInfoRsp) lqi.d().d(str2 + GET_INFO_HOST, hashMap, sb.toString(), OauthInfoRsp.class);
            if (oauthInfoRsp != null && !TextUtils.isEmpty(oauthInfoRsp.getOpenID())) {
                LogUtil.a(TAG, "getUserInfoFromUp oauthInfoRsp openid=", oauthInfoRsp.getOpenID());
                return oauthInfoRsp.getJsonResult(str);
            }
            LogUtil.c(TAG, "getUserInfoFromUp oauthInfoRsp or openid is null");
            return null;
        } catch (UnsupportedEncodingException unused) {
            LogUtil.b(TAG, "getUserInfoFromUp encode Exception UnsupportedEncodingException");
            return null;
        }
    }

    public void reportErrorCode(String str, String str2) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.h(TAG, "reportErrorCode: eventId is empty");
        } else {
            if (TextUtils.isEmpty(str2)) {
                LogUtil.h(TAG, "reportErrorCode: errorCode is empty");
                return;
            }
            LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<>(2);
            linkedHashMap.put(OpAnalyticsConstants.ERROR_CODE, str2);
            OpAnalyticsUtil.getInstance().setEvent2nd(str, linkedHashMap);
        }
    }
}
