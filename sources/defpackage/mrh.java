package defpackage;

import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import com.huawei.haf.common.os.SystemInfo;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.devicemgr.api.constant.GetDeviceInfoMode;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.health.messagecenter.model.IpushBase;
import com.huawei.health.messagecenter.model.IpushTokenCallback;
import com.huawei.health.messagecenter.model.MessageObject;
import com.huawei.health.userprofilemgr.api.UserProfileMgrApi;
import com.huawei.hwcloudmodel.callback.GrsQueryCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.operation.utils.AppTypeUtils;
import com.huawei.operation.utils.PhoneInfoUtils;
import com.huawei.pluginmessagecenter.manager.MCNotificationManager;
import com.huawei.pluginmessagecenter.provider.data.MessageChangeEvent;
import com.huawei.pluginmessagecenter.service.MessageObserver;
import com.huawei.pluginmessagecenter.util.HttpResCallback;
import com.huawei.thirdloginbasemodule.ThirdLoginDataStorageUtil;
import com.huawei.up.model.UserInfomation;
import health.compact.a.AuthorizationUtils;
import health.compact.a.CommonUtil;
import health.compact.a.CompileParameterUtil;
import health.compact.a.GRSManager;
import health.compact.a.KeyValDbManager;
import health.compact.a.Services;
import health.compact.a.Utils;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes6.dex */
public class mrh implements IpushBase, IpushTokenCallback {
    private static final Object c = new Object();
    private static String d;
    private static String e;

    /* renamed from: a, reason: collision with root package name */
    private MessageObserver f15126a = new MessageObserver() { // from class: mrh.5
        @Override // com.huawei.pluginmessagecenter.service.MessageObserver
        public void onChange(int i, MessageChangeEvent messageChangeEvent) {
            LogUtil.a("UIDV_MessagePushReceiver", "MessageObserver onChange start");
            mrh.this.d();
        }
    };

    public static void b(String str) {
        LogUtil.c("UIDV_MessagePushReceiver", "onToken()  ==> saveToken token = ", str);
        String c2 = c(BaseApplication.getContext(), str);
        LogUtil.c("UIDV_MessagePushReceiver", "saveToken params ", c2);
        if (TextUtils.isEmpty(str)) {
            return;
        }
        LogUtil.a("UIDV_MessagePushReceiver", "saveToken==>doSaveToken!");
        d(BaseApplication.getContext(), str, c2, e, d);
    }

    public static void d(final Context context, final String str, final String str2, final String str3, final String str4) {
        ReleaseLogUtil.e("UIDV_MessagePushReceiver", "doSaveToken token is: ", CommonUtil.l(str), "deviceId is: ", CommonUtil.l(str4));
        if (!Utils.i()) {
            LogUtil.a("UIDV_MessagePushReceiver", "doSaveToken isNoCloudVersion");
        } else {
            GRSManager.a(context).e("messageCenterUrl", new GrsQueryCallback() { // from class: mrh.3
                @Override // com.huawei.hwcloudmodel.callback.GrsQueryCallback
                public void onCallBackSuccess(String str5) {
                    LogUtil.a("UIDV_MessagePushReceiver", "MESSAGE_CENTER_KEY SUCCESS");
                    mro.e(context, str5 + com.huawei.health.messagecenter.model.CommonUtil.MESSAGE_CENTER_SAVE_TOKEN, str2, new HttpResCallback() { // from class: mrh.3.5
                        @Override // com.huawei.pluginmessagecenter.util.HttpResCallback
                        public void onSucceed(String str6, String str7, String str8) {
                            LogUtil.a("UIDV_MessagePushReceiver", "doSaveToken onSucceed");
                            LogUtil.c("UIDV_MessagePushReceiver", "doSaveToken HttpUtils.doPostReq onSucceed ==> result =", str6);
                            mrc.e(context).i(str3);
                            mrc.e(context).g(str);
                            mrc.e(context).f(str4);
                            KeyValDbManager.b(context).e("key_push_register_status", "1");
                        }

                        @Override // com.huawei.pluginmessagecenter.util.HttpResCallback
                        public void onFailed(int i) {
                            LogUtil.b("UIDV_MessagePushReceiver", "doSaveToken HttpUtils.doPostReq onFailed ==> resCode =", Integer.valueOf(i));
                        }
                    });
                }

                @Override // com.huawei.hwcloudmodel.callback.GrsQueryCallback
                public void onCallBackFail(int i) {
                    LogUtil.b("UIDV_MessagePushReceiver", "onCallBackFail resCode = ", Integer.valueOf(i));
                }
            });
            LogUtil.a("UIDV_MessagePushReceiver", "Leave doSaveToken");
        }
    }

    public static String c(Context context, String str) {
        String a2 = CommonUtil.a(BaseApplication.getContext(), false);
        LogUtil.c("UIDV_MessagePushReceiver", "getBodyWithToken deviceId= ", a2);
        StringBuffer e2 = e(context, str, a2);
        e = LoginInit.getInstance(context).getAccountInfo(1011);
        d = a2;
        return e2.toString();
    }

    private static StringBuffer e(Context context, String str, String str2) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("tokenType=").append(String.valueOf(ThirdLoginDataStorageUtil.getTokenTypeValue()));
        stringBuffer.append("&appType=").append(String.valueOf(AppTypeUtils.getAppType()));
        stringBuffer.append(Constants.VERSION).append(CommonUtil.e(BaseApplication.getContext()));
        stringBuffer.append("&sysVersion=").append(Build.VERSION.RELEASE);
        stringBuffer.append("&iVersion=2");
        UserInfomation userInfo = ((UserProfileMgrApi) Services.c("HWUserProfileMgr", UserProfileMgrApi.class)).getUserInfo();
        if (userInfo != null) {
            stringBuffer.append(Constants.LANGUAGE).append(userInfo.getLanguageCode());
        }
        String d2 = com.huawei.haf.application.BaseApplication.d();
        stringBuffer.append("&token=").append(e());
        stringBuffer.append("&appId=").append(d2);
        stringBuffer.append("&deviceId=").append(str2);
        if (!TextUtils.isEmpty(str) && str.length() <= 256) {
            stringBuffer.append("&pushToken=").append(str);
        }
        a(context, stringBuffer);
        d(stringBuffer);
        stringBuffer.append("&").append("sn").append("=").append(AuthorizationUtils.a(BaseApplication.getContext()) ? CommonUtil.g() : "unknown");
        stringBuffer.append("&").append("ts").append("=").append(System.currentTimeMillis());
        c(stringBuffer);
        stringBuffer.append("&").append("environment").append("=").append(CommonUtil.l(BaseApplication.getContext()));
        stringBuffer.append("&").append("upDeviceType").append("=").append(LoginInit.getInstance(com.huawei.haf.application.BaseApplication.e()).getDeviceType());
        stringBuffer.append("&").append("siteId").append("=").append(LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1009));
        return stringBuffer;
    }

    private static void c(StringBuffer stringBuffer) {
        DeviceInfo deviceInfo = cun.c().getDeviceInfo(GetDeviceInfoMode.ACTIVE_MAIN_DEVICES_WITHOUT_AW70, null, "UIDV_MessagePushReceiver");
        if (deviceInfo != null) {
            stringBuffer.append("&").append("bindDeviceType").append("=").append(deviceInfo.getProductType());
            stringBuffer.append("&").append("deviceType").append("=").append(deviceInfo.getDeviceModel());
        } else {
            stringBuffer.append("&").append("deviceType").append("=").append(PhoneInfoUtils.getDeviceModel());
        }
    }

    private static void a(Context context, StringBuffer stringBuffer) {
        if (CommonUtil.z(context)) {
            stringBuffer.append("&").append("pushType").append("=").append("1");
        } else {
            stringBuffer.append("&").append("pushType").append("=").append("0");
        }
    }

    private static void d(StringBuffer stringBuffer) {
        if (CompileParameterUtil.a("GOOGLE_PLAY_OEM_BETTERME", false)) {
            if (SystemInfo.a()) {
                stringBuffer.append("&").append("phoneType").append("=").append("HW_GP");
                return;
            } else {
                stringBuffer.append("&").append("phoneType").append("=").append("3RD_GP");
                return;
            }
        }
        stringBuffer.append("&").append("phoneType").append("=").append(PhoneInfoUtils.getPhoneType());
    }

    @Override // com.huawei.health.messagecenter.model.IpushBase
    public List<String> getPushType() {
        return Arrays.asList("2");
    }

    @Override // com.huawei.health.messagecenter.model.IpushBase
    public void processPushMsg(Context context, String str) {
        if (str == null || "".equals(str) || str.length() < 1) {
            LogUtil.b("UIDV_MessagePushReceiver", "processPushMsg Error PushMsg is Empty");
        } else {
            LogUtil.a("UIDV_MessagePushReceiver", "msg = ", str);
            ThreadPoolManager.d().execute(new Runnable() { // from class: mrh.1
                @Override // java.lang.Runnable
                public void run() {
                    LogUtil.a("UIDV_MessagePushReceiver", "startMessageNotificationObserver doRefresh()");
                    if (mrh.this.f15126a != null) {
                        LogUtil.a("UIDV_MessagePushReceiver", "openMessageObserver registerMessageObserver");
                        mqw.b(BaseApplication.getContext()).c(mrh.this.f15126a);
                    }
                    mqw.b(BaseApplication.getContext()).d();
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d() {
        LogUtil.a("UIDV_MessagePushReceiver", "refreshNotificationMessage enter");
        synchronized (c) {
            ThreadPoolManager.d().execute(new Runnable() { // from class: mrh.4
                @Override // java.lang.Runnable
                public void run() {
                    List<MessageObject> b = mqw.b(BaseApplication.getContext()).b(2);
                    if (b != null && !b.isEmpty()) {
                        LogUtil.a("UIDV_MessagePushReceiver", "refreshNotificationMessage notificationMessageList show count = ", Integer.valueOf(b.size()));
                        Iterator<MessageObject> it = b.iterator();
                        while (it.hasNext()) {
                            mrh.this.e(it.next());
                        }
                        return;
                    }
                    LogUtil.a("UIDV_MessagePushReceiver", "refreshNotificationMessage notificationMessageList empty");
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(MessageObject messageObject) {
        LogUtil.a("UIDV_MessagePushReceiver", "showNotificationMsg");
        MCNotificationManager.showNotification(BaseApplication.getContext(), messageObject);
    }

    @Override // com.huawei.health.messagecenter.model.IpushTokenCallback
    public void pushTokenHandle(Context context, String str) {
        b(str);
    }

    public static String e() {
        String accountInfo = LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1008);
        if (TextUtils.isEmpty(accountInfo)) {
            return accountInfo;
        }
        try {
            return URLEncoder.encode(accountInfo, StandardCharsets.UTF_8.name());
        } catch (UnsupportedEncodingException unused) {
            LogUtil.b("UIDV_MessagePushReceiver", "token encode Exception UnsupportedEncodingException");
            return accountInfo;
        }
    }
}
