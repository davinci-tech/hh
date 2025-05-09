package com.huawei.pluginmessagecenter.service;

import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import com.google.gson.JsonSyntaxException;
import com.huawei.health.devicemgr.api.constant.GetDeviceInfoMode;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.health.userprofilemgr.api.UserProfileMgrApi;
import com.huawei.hwcloudmodel.callback.GrsQueryCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.operation.utils.AppTypeUtils;
import com.huawei.operation.utils.OperationUtilsApi;
import com.huawei.operation.utils.PhoneInfoUtils;
import com.huawei.pluginmessagecenter.util.HttpResCallback;
import com.huawei.pluginmessagecenter.util.PullMessageCallback;
import com.huawei.thirdloginbasemodule.ThirdLoginDataStorageUtil;
import com.huawei.up.model.UserInfomation;
import defpackage.cun;
import defpackage.mro;
import health.compact.a.CommonUtil;
import health.compact.a.GRSManager;
import health.compact.a.Services;
import health.compact.a.Utils;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

/* loaded from: classes6.dex */
public class MessagePuller {
    private static final Byte[] LOCK = new Byte[1];
    private static final String PARAM_CONCAT_CHAR = "&";
    private static final String PARAM_EQUAL_CHAR = "=";
    private static final String TAG = "UIDV_MessagePuller";
    private static volatile MessagePuller sInstance;

    private MessagePuller() {
        LogUtil.a(TAG, "MessagePuller");
    }

    public static MessagePuller getInstance() {
        if (sInstance == null) {
            synchronized (LOCK) {
                if (sInstance == null) {
                    sInstance = new MessagePuller();
                }
            }
        }
        return sInstance;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String getBody(long j) {
        LogUtil.a(TAG, "Enter getBody");
        StringBuffer stringBuffer = new StringBuffer(16);
        setMapData(j, stringBuffer);
        return stringBuffer.toString();
    }

    private boolean setMapData(long j, StringBuffer stringBuffer) {
        stringBuffer.append("token=").append(getToken());
        appendTypes(stringBuffer);
        stringBuffer.append(Constants.VERSION).append(CommonUtil.e(BaseApplication.getContext()));
        String deviceId = LoginInit.getInstance(BaseApplication.getContext()).getDeviceId();
        if ("".equals(deviceId)) {
            deviceId = "clientnull";
        }
        stringBuffer.append("&deviceId=").append(deviceId);
        stringBuffer.append("&timestamp=").append(j);
        stringBuffer.append("&ts=").append(String.valueOf(System.currentTimeMillis()));
        stringBuffer.append("&tokenType=").append(String.valueOf(ThirdLoginDataStorageUtil.getTokenTypeValue()));
        stringBuffer.append("&sysVersion=").append(Build.VERSION.RELEASE);
        stringBuffer.append("&iVersion=").append(2);
        UserInfomation userInfo = ((UserProfileMgrApi) Services.c("HWUserProfileMgr", UserProfileMgrApi.class)).getUserInfo();
        if (userInfo != null) {
            stringBuffer.append(Constants.LANGUAGE).append(userInfo.getLanguageCode());
        }
        stringBuffer.append("&appId=").append(com.huawei.haf.application.BaseApplication.d());
        stringBuffer.append("&environment=").append(String.valueOf(CommonUtil.l(BaseApplication.getContext())));
        stringBuffer.append("&upDeviceType=").append(LoginInit.getInstance(com.huawei.haf.application.BaseApplication.e()).getDeviceType());
        String accountInfo = LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1010);
        if (!Utils.i()) {
            return false;
        }
        stringBuffer.append("&countryCode=").append(accountInfo);
        stringBuffer.append("&siteId=").append(LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1009));
        return false;
    }

    private void appendTypes(StringBuffer stringBuffer) {
        String str;
        String str2;
        DeviceInfo deviceInfo = cun.c().getDeviceInfo(GetDeviceInfoMode.ACTIVE_MAIN_DEVICES_WITHOUT_AW70, null, TAG);
        if (deviceInfo != null) {
            str2 = String.valueOf(deviceInfo.getProductType());
            stringBuffer.append("&bindDeviceType=").append(deviceInfo.getProductType());
            str = deviceInfo.getDeviceModel();
        } else {
            str = "";
            str2 = str;
        }
        stringBuffer.append("&wearType=").append(com.huawei.health.messagecenter.model.CommonUtil.getWearType(str2, str) != null ? com.huawei.health.messagecenter.model.CommonUtil.getWearType(str2, str) : "");
        stringBuffer.append("&healthType=").append("");
        StringBuffer append = stringBuffer.append("&deviceType=");
        if (TextUtils.isEmpty(str)) {
            str = PhoneInfoUtils.getDeviceModel();
        }
        append.append(str);
        stringBuffer.append("&recordNumber=").append(String.valueOf(Integer.MAX_VALUE));
        stringBuffer.append("&appType=").append(String.valueOf(AppTypeUtils.getAppType()));
        stringBuffer.append("&phoneType=").append(PhoneInfoUtils.getPhoneType());
    }

    public void pullMessage(final Context context, final long j, final PullMessageCallback pullMessageCallback) {
        LogUtil.a(TAG, "pullMessage() time = ", Long.valueOf(j));
        if (judgeParameter(context, j, pullMessageCallback)) {
            return;
        }
        GRSManager.a(context).e("messageCenterUrl", new GrsQueryCallback() { // from class: com.huawei.pluginmessagecenter.service.MessagePuller.1
            @Override // com.huawei.hwcloudmodel.callback.GrsQueryCallback
            public void onCallBackSuccess(String str) {
                LogUtil.a(MessagePuller.TAG, "MESSAGE_CENTER_KEY SUCCESS");
                mro.e(context, str + com.huawei.health.messagecenter.model.CommonUtil.MESSAGE_CENTER_GET_MESSAGE + com.huawei.health.messagecenter.model.CommonUtil.getUrlSuffix(), MessagePuller.this.getBody(j), new HttpResCallback() { // from class: com.huawei.pluginmessagecenter.service.MessagePuller.1.1
                    @Override // com.huawei.pluginmessagecenter.util.HttpResCallback
                    public void onSucceed(String str2, String str3, String str4) {
                        LogUtil.c(MessagePuller.TAG, "pullMessage() doPostReq onSucceed ==> result == ", str2);
                        try {
                            pullMessageCallback.pullMessageResult(200, MessageParser.parseMessageArray(context, str2, str4), MessageParser.parseRevokeIdArray(str3));
                        } catch (JsonSyntaxException e) {
                            LogUtil.b(MessagePuller.TAG, "pullMessage() doPostReq JsonSyntaxException:", e.getMessage());
                            pullMessageCallback.pullMessageResult(-1, new ArrayList(16), new ArrayList(16));
                        }
                    }

                    @Override // com.huawei.pluginmessagecenter.util.HttpResCallback
                    public void onFailed(int i) {
                        LogUtil.a(MessagePuller.TAG, "pullMessage() doPostReq onFailed ==> resCode == ", Integer.valueOf(i));
                        pullMessageCallback.pullMessageResult(i, new ArrayList(16), new ArrayList(16));
                    }
                });
            }

            @Override // com.huawei.hwcloudmodel.callback.GrsQueryCallback
            public void onCallBackFail(int i) {
                LogUtil.a(MessagePuller.TAG, "onCallBackFail errorCode = ", Integer.valueOf(i));
            }
        });
    }

    private boolean judgeParameter(Context context, long j, PullMessageCallback pullMessageCallback) {
        if (context == null) {
            LogUtil.a(TAG, "pullMessage() context = null");
            return true;
        }
        if (pullMessageCallback == null) {
            LogUtil.a(TAG, "pullMessage() PullMessageCallback = null");
            return true;
        }
        if (j < 0) {
            LogUtil.a(TAG, "pullMessage() time < 0 time = ", Long.valueOf(j));
            pullMessageCallback.pullMessageResult(-1, null, null);
            return true;
        }
        if (LoginInit.getInstance(BaseApplication.getContext()).isKidAccount()) {
            LogUtil.a(TAG, "isKidAccount");
            pullMessageCallback.pullMessageResult(-1, null, null);
            return true;
        }
        if (!Utils.o() || isOverseaCountry(context)) {
            return false;
        }
        LogUtil.a(TAG, "isNoCloudVersion");
        pullMessageCallback.pullMessageResult(-1, null, null);
        return true;
    }

    private boolean isOverseaCountry(Context context) {
        return ((OperationUtilsApi) Services.c("PluginOperation", OperationUtilsApi.class)).isOperation(LoginInit.getInstance(context).getAccountInfo(1010));
    }

    private String getToken() {
        String accountInfo = LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1008);
        if (TextUtils.isEmpty(accountInfo)) {
            return accountInfo;
        }
        try {
            return URLEncoder.encode(accountInfo, StandardCharsets.UTF_8.name());
        } catch (UnsupportedEncodingException unused) {
            LogUtil.b(TAG, "token encode Exception UnsupportedEncodingException");
            return accountInfo;
        }
    }
}
