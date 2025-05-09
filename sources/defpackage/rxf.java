package defpackage;

import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import com.huawei.health.messagecenter.api.MessageCenterApi;
import com.huawei.hwcloudmodel.callback.GrsQueryCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.operation.utils.PhoneInfoUtils;
import com.huawei.pluginmessagecenter.util.HttpResCallback;
import com.huawei.thirdloginbasemodule.ThirdLoginDataStorageUtil;
import health.compact.a.GRSManager;
import health.compact.a.Services;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

/* loaded from: classes7.dex */
public class rxf {
    private static rxf e;

    /* renamed from: a, reason: collision with root package name */
    private final MessageCenterApi f16946a = (MessageCenterApi) Services.a("PluginMessageCenter", MessageCenterApi.class);
    private final Context d;

    private rxf(Context context) {
        this.d = context.getApplicationContext();
    }

    public static rxf e(Context context) {
        if (e == null) {
            e = new rxf(context);
        }
        return e;
    }

    public void e(final HttpResCallback httpResCallback) {
        LogUtil.a("InformationInteractors", "getInfoMoreUrl");
        final StringBuffer stringBuffer = new StringBuffer(256);
        String deviceId = LoginInit.getInstance(this.d).getDeviceId();
        if ("".equals(deviceId)) {
            deviceId = "clientnull";
        }
        stringBuffer.append("tokenType=").append(ThirdLoginDataStorageUtil.getTokenTypeValue());
        String accountInfo = LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1008);
        if (!TextUtils.isEmpty(accountInfo)) {
            try {
                stringBuffer.append("&").append("token").append("=").append(URLEncoder.encode(accountInfo, StandardCharsets.UTF_8.name()));
            } catch (UnsupportedEncodingException e2) {
                LogUtil.b("InformationInteractors", "token encode Exception", e2.toString());
            }
        }
        if (LoginInit.getInstance(this.d).isLoginedByWear()) {
            stringBuffer.append("&appId=com.huawei.bone");
        } else {
            stringBuffer.append("&appId=").append(BaseApplication.getAppPackage());
        }
        stringBuffer.append("&deviceId=").append(deviceId);
        stringBuffer.append("&deviceType=").append(PhoneInfoUtils.getPhoneType());
        stringBuffer.append("&sysVersion=").append(Build.VERSION.RELEASE);
        stringBuffer.append("&appType=1&iVersion=2&language=zh_CN&ts=");
        stringBuffer.append(System.currentTimeMillis());
        stringBuffer.append("&upDeviceType=").append(LoginInit.getInstance(this.d).getDeviceType());
        GRSManager.a(this.d).e("messageCenterUrl", new GrsQueryCallback() { // from class: rxf.2
            @Override // com.huawei.hwcloudmodel.callback.GrsQueryCallback
            public void onCallBackSuccess(String str) {
                LogUtil.c("InformationInteractors", "GRSManager onCallBackSuccess MESSAGE_CENTER_KEY url = ", str);
                rxf.this.f16946a.doMessagesPostReq(rxf.this.d, str + "/messageCenter/getMoreInfoUrl", stringBuffer.toString(), httpResCallback);
            }

            @Override // com.huawei.hwcloudmodel.callback.GrsQueryCallback
            public void onCallBackFail(int i) {
                LogUtil.c("InformationInteractors", "GRSManager onCallBackFail MESSAGE_CENTER_KEY code = ", Integer.valueOf(i));
            }
        });
    }
}
