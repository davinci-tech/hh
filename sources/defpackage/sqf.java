package defpackage;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import com.huawei.framework.servicemgr.Consumer;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.health.ecologydevice.networkclient.HealthEngineRequestManager;
import com.huawei.health.messagecenter.api.MessageCenterApi;
import com.huawei.health.messagecenter.model.MessageConstant;
import com.huawei.health.messagecenter.model.MessageObject;
import com.huawei.health.socialshare.api.SocialShareCenterApi;
import com.huawei.health.sport.model.WorkoutRecord;
import com.huawei.health.suggestion.PluginSuggestion;
import com.huawei.health.userprofilemgr.api.UserProfileMgrApi;
import com.huawei.hms.support.feature.result.CommonConstant;
import com.huawei.hwadpaterhealthmgr.PluginAchieveAdapterImpl;
import com.huawei.hwadpaterhealthmgr.PluginOperationAdapterImpl;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcloudmodel.callback.GrsQueryCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.operation.activity.WebViewActivity;
import com.huawei.operation.adapter.PluginOperationAdapter;
import com.huawei.operation.beans.UrlBean;
import com.huawei.operation.beans.WebViewConfig;
import com.huawei.operation.h5pro.jsmodules.device.DevicePairUtils;
import com.huawei.operation.operation.PluginOperation;
import com.huawei.operation.utils.AppTypeUtils;
import com.huawei.operation.utils.OperationUtils;
import com.huawei.operation.utils.PhoneInfoUtils;
import com.huawei.phoneservice.faq.base.constants.FaqConstants;
import com.huawei.up.model.UserInfomation;
import com.huawei.up.utils.Constants;
import health.compact.a.AuthorizationUtils;
import health.compact.a.CommonUtil;
import health.compact.a.GRSManager;
import health.compact.a.Services;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes7.dex */
public class sqf {
    private static final String d = "HWAdapterHelper";

    private static Map<String, String> a() {
        HashMap hashMap = new HashMap();
        LoginInit loginInit = LoginInit.getInstance(BaseApplication.getContext());
        String accountInfo = loginInit.getAccountInfo(1008);
        String accountInfo2 = loginInit.getAccountInfo(1015);
        hashMap.put("huid", loginInit.getAccountInfo(1011));
        hashMap.put("severToken", accountInfo);
        hashMap.put("accessToken", accountInfo2);
        return hashMap;
    }

    private static Map<String, String> j() {
        HashMap hashMap = new HashMap();
        UserInfomation userInfo = ((UserProfileMgrApi) Services.c("HWUserProfileMgr", UserProfileMgrApi.class)).getUserInfo();
        if (userInfo != null) {
            hashMap.put("birthday", userInfo.getBirthday());
            hashMap.put(CommonConstant.KEY_GENDER, String.valueOf(userInfo.getGender()));
            hashMap.put("weight", String.valueOf(userInfo.getWeight()));
            hashMap.put("height", String.valueOf(userInfo.getHeight()));
            hashMap.put("name", userInfo.getName());
            hashMap.put(FaqConstants.FAQ_LANGUAGE, userInfo.getLanguageCode());
            hashMap.put("portraitUrl", userInfo.getPortraitUrl());
            hashMap.put("picPath", userInfo.getPicPath());
        }
        return hashMap;
    }

    private static Map<String, String> c() {
        HashMap hashMap = new HashMap();
        hashMap.put("deviceModel", PhoneInfoUtils.getDeviceModel());
        LoginInit loginInit = LoginInit.getInstance(BaseApplication.getContext());
        String deviceId = loginInit.getDeviceId();
        if ("".equals(deviceId)) {
            deviceId = "clientnull";
        }
        hashMap.put("deviceId", deviceId);
        hashMap.put("deviceType", PhoneInfoUtils.getPhoneType());
        if (AuthorizationUtils.a(BaseApplication.getContext())) {
            hashMap.put(HealthEngineRequestManager.PARAMS_DEVICE_SN, CommonUtil.g());
        } else {
            hashMap.put(HealthEngineRequestManager.PARAMS_DEVICE_SN, "unknown");
        }
        hashMap.put("upDeviceType", loginInit.getDeviceType());
        return hashMap;
    }

    private static Map<String, String> e() {
        HashMap hashMap = new HashMap();
        hashMap.put("version", CommonUtil.e(BaseApplication.getContext()));
        hashMap.put("grayVersion", CommonUtil.c(BaseApplication.getContext()));
        hashMap.put("appId", OperationUtils.getAppId(BaseApplication.getContext()));
        hashMap.put("sysVersion", OperationUtils.getSysVersion());
        hashMap.put("environment", String.valueOf(CommonUtil.l(BaseApplication.getContext())));
        hashMap.put("appType", String.valueOf(AppTypeUtils.getAppType()));
        hashMap.put("iversion", String.valueOf(OperationUtils.getInterfaceVersion()));
        hashMap.put("utc", String.valueOf(OperationUtils.getUtc()));
        return hashMap;
    }

    public static void c(Context context, fdu fduVar, IBaseResponseCallback iBaseResponseCallback, boolean z) {
        fduVar.c(z);
        ((SocialShareCenterApi) Services.c("PluginSocialShare", SocialShareCenterApi.class)).exeShare(fduVar, context);
    }

    public static Map<String, String> e(String[] strArr) {
        HashMap hashMap = new HashMap();
        for (String str : strArr) {
            if (str.equals("getLoginInfo")) {
                hashMap.putAll(a());
            } else if (str.equals(Constants.METHOD_GET_USER_INFO)) {
                hashMap.putAll(j());
            } else if (str.equals("getDeviceInfo")) {
                hashMap.putAll(d());
            } else if (str.equals("getPhoneInfo")) {
                hashMap.putAll(c());
            } else if (str.equals("getAppInfo")) {
                hashMap.putAll(e());
            } else {
                LogUtil.h(d, "not match");
            }
        }
        return hashMap;
    }

    public static void c(Context context, String str) {
        PluginOperation.getInstance(context).setAdapter(PluginOperationAdapterImpl.getInstance(context));
        PluginOperation.getInstance(context).startOperationWebPage(str);
    }

    public static String d(Context context) {
        UrlBean urlBean;
        WebViewConfig queryWebViewConfig = PluginOperationAdapterImpl.getInstance(context).queryWebViewConfig();
        if (queryWebViewConfig == null || (urlBean = queryWebViewConfig.getUrlBean()) == null) {
            return null;
        }
        return urlBean.getKakaExchangeUrl();
    }

    public static String e(MessageObject messageObject) {
        if (messageObject == null) {
            LogUtil.c(d, "messageObject == null");
            return "";
        }
        MessageCenterApi messageCenterApi = (MessageCenterApi) Services.c("PluginMessageCenter", MessageCenterApi.class);
        String msgId = messageObject.getMsgId();
        String module = messageObject.getModule();
        String type = messageObject.getType();
        if (TextUtils.isEmpty(msgId)) {
            LogUtil.c(d, "requestMessageId ");
            msgId = messageCenterApi.getMessageId(module, type);
            messageObject.setMsgId(msgId);
        }
        messageCenterApi.insertMessage(messageObject);
        if (MessageConstant.ACQUIRE_MEDAL_TYPE.equals(messageObject.getType()) || MessageConstant.NEW_MEDAL_TYPE.equals(messageObject.getType()) || "monthReportMessage".equals(messageObject.getType())) {
            c(messageObject);
        }
        LogUtil.a(d, "generateMessage obj=" + messageObject);
        return msgId;
    }

    private static void c(MessageObject messageObject) {
        messageObject.setPosition(3);
        ((MessageCenterApi) Services.c("PluginMessageCenter", MessageCenterApi.class)).showNotification(BaseApplication.getContext(), messageObject);
    }

    public static void b(Context context, int i, int i2, float f) {
        LogUtil.a(d, "startGPSMotionTrack in Manager Module with sportType=", Integer.valueOf(i), "", ",targetType=", Integer.valueOf(i2), ",targetValue=", Float.valueOf(f));
        gso.e().c(0, i, i2, f, null, BaseApplication.getContext());
        PluginOperation.getInstance(context).backToActivityListPage();
    }

    public static void b(Context context, String str, String str2) {
        LogUtil.c(d, "startFitnessPage in Manager Module with[id=", str, ",version=", str2, "]");
        PluginSuggestion pluginSuggestion = (PluginSuggestion) Services.a("PluginFitnessAdvice", PluginSuggestion.class);
        if (pluginSuggestion == null || !pluginSuggestion.isInitComplete()) {
            return;
        }
        gge.d((String) null);
        WorkoutRecord workoutRecord = new WorkoutRecord();
        workoutRecord.saveVersion(str2);
        workoutRecord.saveExerciseTime(new Date().getTime());
        workoutRecord.saveWorkoutId(str);
        workoutRecord.savePlanId("");
        ArrayList arrayList = new ArrayList(1);
        arrayList.add(workoutRecord);
        mmp mmpVar = new mmp(str);
        mmpVar.r(str2);
        mod.c(context, mmpVar, arrayList);
        PluginOperation.getInstance(context).backToActivityListPage();
    }

    private static Map<String, String> d() {
        String str = d;
        LogUtil.a(str, "getDeviceInfo");
        HashMap hashMap = new HashMap();
        if (jpp.a()) {
            DeviceInfo a2 = jpt.a(str);
            if (a2 != null) {
                LogUtil.a(str, "getDeviceInfo ", a2.getDeviceName(), "deviceTyp=", String.valueOf(a2.getProductType()));
                hashMap.put("deviceName", a2.getDeviceName());
                hashMap.put("productType", String.valueOf(a2.getProductType()));
                hashMap.put(DevicePairUtils.DEVICE_CONNECT_STATE, String.valueOf(a2.getDeviceConnectState()));
                hashMap.put("deviceModel", a2.getDeviceModel());
                hashMap.put("deviceModelName", a2.getDeviceModel());
            } else {
                LogUtil.a(str, "getDeviceInfo enter else");
            }
        }
        return hashMap;
    }

    public static void b(final Context context) {
        if (context == null) {
            LogUtil.h(d, "skipKaka context = null");
            return;
        }
        if (((PluginSuggestion) Services.a("PluginFitnessAdvice", PluginSuggestion.class)) == null) {
            Services.a("PluginFitnessAdvice", PluginSuggestion.class, com.huawei.haf.application.BaseApplication.e(), new Consumer<PluginSuggestion>() { // from class: sqf.1
                @Override // com.huawei.framework.servicemgr.Consumer
                /* renamed from: a, reason: merged with bridge method [inline-methods] */
                public void accept(PluginSuggestion pluginSuggestion) {
                    if (pluginSuggestion != null) {
                        pluginSuggestion.init(BaseApplication.getContext());
                    }
                    sqf.b(context);
                }
            }, true);
            return;
        }
        mcv d2 = mcv.d(context);
        if (d2.getAdapter() == null) {
            mcv.d(context).setAdapter(new PluginAchieveAdapterImpl());
        }
        d2.a(context);
        d2.t();
        d2.b(context);
    }

    public static void e(final Context context) {
        if (context == null) {
            LogUtil.h(d, "jumpWebview context = null");
        } else {
            c(context);
            GRSManager.a(context).e("healthRecommendUrl", new GrsQueryCallback() { // from class: sqf.5
                @Override // com.huawei.hwcloudmodel.callback.GrsQueryCallback
                public void onCallBackSuccess(String str) {
                    if (TextUtils.isEmpty(str)) {
                        LogUtil.h(sqf.d, "geturl is empty");
                        return;
                    }
                    try {
                        Intent intent = new Intent(context, (Class<?>) WebViewActivity.class);
                        intent.putExtra("url", str + "/miniShop/html/homePage.html");
                        intent.putExtra("type", 3001);
                        context.startActivity(intent);
                    } catch (ActivityNotFoundException e) {
                        LogUtil.b(sqf.d, "onCallBackSuccess ActivityNotFoundException e:", e.getMessage());
                    }
                }

                @Override // com.huawei.hwcloudmodel.callback.GrsQueryCallback
                public void onCallBackFail(int i) {
                    LogUtil.h(sqf.d, "GRSManager onCallBackFail HEALTH_RECOMMEND errorCode = ", Integer.valueOf(i));
                }
            });
        }
    }

    private static void c(Context context) {
        if (mcv.d(context).getAdapter() == null) {
            LogUtil.h(d, "PluginAchieveAdapter == null");
            mcv.d(context).setAdapter(new PluginAchieveAdapterImpl());
        }
        PluginOperation pluginOperation = PluginOperation.getInstance(context);
        if ((pluginOperation.getAdapter() instanceof PluginOperationAdapter ? (PluginOperationAdapter) pluginOperation.getAdapter() : null) == null) {
            LogUtil.h(d, "pluginOperationAdapter == null");
            a(context);
        }
    }

    private static void a(Context context) {
        PluginOperation pluginOperation = PluginOperation.getInstance(context);
        pluginOperation.setAdapter(PluginOperationAdapterImpl.getInstance(context));
        pluginOperation.init(context);
    }
}
