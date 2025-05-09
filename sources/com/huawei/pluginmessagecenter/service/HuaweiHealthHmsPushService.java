package com.huawei.pluginmessagecenter.service;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.huawei.haf.handler.HandlerCenter;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.BuildConfig;
import com.huawei.health.messagecenter.model.CommonUtil;
import com.huawei.health.messagecenter.model.IpushBase;
import com.huawei.health.messagecenter.model.IpushTokenCallback;
import com.huawei.hmf.tasks.OnFailureListener;
import com.huawei.hmf.tasks.OnSuccessListener;
import com.huawei.hms.aaid.HmsInstanceId;
import com.huawei.hms.aaid.entity.AAIDResult;
import com.huawei.hms.common.ApiException;
import com.huawei.hms.push.HmsMessageService;
import com.huawei.hms.push.RemoteMessage;
import com.huawei.hms.push.constant.RemoteMessageConst;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.pluginmessagecenter.provider.data.PushBeanBase;
import com.huawei.pluginmessagecenter.provider.data.PushMessage;
import defpackage.bzu;
import defpackage.koq;
import defpackage.mrh;
import health.compact.a.LocalBroadcast;
import health.compact.a.LogAnonymous;
import health.compact.a.Utils;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes6.dex */
public class HuaweiHealthHmsPushService extends HmsMessageService {
    private static final String ACTION_RECEIVE_PUSH_MSG = "com.huawei.wallet.push.action.PUSH_MSG";
    private static final String ACTION_RECEIVE_PUSH_TOKEN = "com.huawei.wallet.push.action.PUSH_TOKEN";
    private static final String ADVERTISE_TYPE = "4";
    private static final int ALIPAY_EXPIRED_CODE = 7;
    private static final String AUTHORIZATION_CLASS = "mrf";
    private static final String AUTHORIZATION_HANDLE_METHOD = "handleExpiration";
    private static final String DEFAULT_TOKEN_SCOPE = "HCM";
    private static final String EXTRA_PUSH_MSG = "com.huawei.wallet.push.extra.PUSH_MSG";
    private static final String EXTRA_PUSH_TOKEN = "com.huawei.wallet.push.extra.PUSH_TOKEN";
    private static final String GROUP_PUSH_MESSAGE = "GroupInvatationNotify";
    private static final String GUIDE_MAP_KEY = "guideVersion";
    private static final String GUIDE_PAGE_MESSAGE_NAME = "qbl";
    private static final String HEALTH_GROUP_PUSH_CLASS_NAME = "com.huawei.pluginmessagecenter.receiver.PushAgentReceiver";
    private static final String MESSAGE_CENTER = "messagecenter";
    private static final String MESSAGE_TYPE = "2";
    private static final String PUSH_ID = "pushId";
    private static final String PUSH_IS_RECONNECT = "reconnectAble";
    private static final String PUSH_TYPE = "pushType";
    private static final int QQ_EXPIRED_CODE = 8;
    private static final String RELEASE_TAG = "R_HuaweiHealthHmsPushService";
    private static final String TAG = "HuaweiHealthHmsPushService";
    private static final HashMap<String, IpushBase> sMsgHandlers;
    private static List<String> sWalletPushMsgList;
    private static final Object LOCK = new Object();
    private static Gson gson = new Gson();
    private static HashMap<String, IpushTokenCallback> sPushCallbackMap = new HashMap<>(10);

    static {
        ArrayList arrayList = new ArrayList(10);
        sWalletPushMsgList = arrayList;
        arrayList.add("personizedPush");
        sWalletPushMsgList.add("UnionPayPush");
        sWalletPushMsgList.add("reportloss");
        sWalletPushMsgList.add("consume");
        sWalletPushMsgList.add("clearese");
        sWalletPushMsgList.add("delaccount");
        sMsgHandlers = new HashMap<>();
    }

    public static void registerMessageHandler(IpushBase ipushBase) {
        List<String> pushType = ipushBase.getPushType();
        if (koq.b(pushType)) {
            return;
        }
        for (String str : pushType) {
            HashMap<String, IpushBase> hashMap = sMsgHandlers;
            synchronized (hashMap) {
                ReleaseLogUtil.e(TAG, "push type ", str, " register handlers");
                hashMap.put(str, ipushBase);
            }
        }
    }

    @Override // com.huawei.hms.push.HmsMessageService, android.app.Service
    public int onStartCommand(Intent intent, int i, int i2) {
        super.onStartCommand(intent, i, i2);
        stopSelf(i2);
        return 2;
    }

    private boolean fromWalletPushMsg(String str) {
        LogUtil.a(TAG, "fromWalletPushMsg responseStr = ", str);
        if (str == null) {
            return false;
        }
        try {
            String string = new JSONObject(str).getString("msg");
            LogUtil.a(TAG, "fromWalletPushMsg walletPushMsg = ", string);
            if (string != null && !"".equals(string)) {
                if (sWalletPushMsgList.contains(string)) {
                    return true;
                }
            }
            return false;
        } catch (JSONException e) {
            LogUtil.b(TAG, "fromWalletPushMsg jsonException = ", LogAnonymous.b((Throwable) e));
            return false;
        }
    }

    private void sendWalletMsg(String str) {
        LogUtil.a(TAG, "enter sendWalletMsg");
        Intent intent = new Intent();
        intent.setPackage(BaseApplication.getContext().getPackageName());
        intent.setAction(ACTION_RECEIVE_PUSH_MSG);
        intent.putExtra(EXTRA_PUSH_MSG, str);
        BaseApplication.getContext().sendBroadcast(intent, LocalBroadcast.c);
    }

    public static void sendWalletToken(String str) {
        LogUtil.a(TAG, "enter sendWalletToken");
        if (!bzu.b().isPluginAvaiable()) {
            LogUtil.a(TAG, "enter sendWalletToken not PluginAvaiable");
            return;
        }
        Intent intent = new Intent();
        intent.setPackage(BaseApplication.getContext().getPackageName());
        intent.setAction(ACTION_RECEIVE_PUSH_TOKEN);
        intent.putExtra(EXTRA_PUSH_TOKEN, str);
        BaseApplication.getContext().sendBroadcast(intent, LocalBroadcast.c);
    }

    public static void sendHealthGroupToken(String str) {
        LogUtil.a(TAG, "enter sendHealthGroupToken");
        try {
            Class<?> cls = Class.forName(HEALTH_GROUP_PUSH_CLASS_NAME);
            cls.getMethod("onToken", Context.class, String.class).invoke(cls.newInstance(), BaseApplication.getContext(), str);
        } catch (ClassNotFoundException e) {
            e = e;
            LogUtil.b(TAG, "sendHealthGroupToken ", LogAnonymous.b(e));
        } catch (IllegalAccessException e2) {
            e = e2;
            LogUtil.b(TAG, "sendHealthGroupToken ", LogAnonymous.b(e));
        } catch (InstantiationException e3) {
            e = e3;
            LogUtil.b(TAG, "sendHealthGroupToken ", LogAnonymous.b(e));
        } catch (NoSuchMethodException e4) {
            e = e4;
            LogUtil.b(TAG, "sendHealthGroupToken ", LogAnonymous.b(e));
        } catch (InvocationTargetException e5) {
            e = e5;
            LogUtil.b(TAG, "sendHealthGroupToken ", LogAnonymous.b(e));
        } catch (Exception unused) {
            LogUtil.b(TAG, "sendHealthGroupToken meet exception");
        }
    }

    @Override // com.huawei.hms.push.HmsMessageService
    public void onNewToken(String str) {
        LogUtil.a(TAG, "onNewToken getTokenMsg ");
        sendPushToken(str);
        sendWalletToken(str);
        sendHealthGroupToken(str);
    }

    @Override // com.huawei.hms.push.HmsMessageService
    public void onTokenError(Exception exc) {
        super.onTokenError(exc);
        LogUtil.h(TAG, "onTokenError:", exc.toString());
    }

    @Override // com.huawei.hms.push.HmsMessageService
    public void onMessageReceived(RemoteMessage remoteMessage) {
        if (remoteMessage == null || TextUtils.isEmpty(remoteMessage.getData())) {
            LogUtil.h(TAG, "onMessageReceived, message or data is null.");
            return;
        }
        String data = remoteMessage.getData();
        ReleaseLogUtil.e(RELEASE_TAG, "onMessageReceived, content = ", data);
        PushBeanBase pushBeanBase = new PushBeanBase();
        if (MESSAGE_CENTER.equals(data)) {
            pushBeanBase.pushType = "2";
        } else if (isAdvertiseMessage(data)) {
            pushBeanBase.pushType = "4";
        } else if (fromWalletPushMsg(data)) {
            if (bzu.b().isPluginAvaiable()) {
                sendWalletMsg(data);
                return;
            }
            return;
        } else {
            if (checkAuthorizationMsg(data) || isGroupMessage(data) || parseStringToMap(data) || isPushMessageToDevice(data)) {
                return;
            }
            ReleaseLogUtil.e(RELEASE_TAG, "deal message with PushBean class");
            try {
                pushBeanBase = (PushBeanBase) gson.fromJson(data, PushBeanBase.class);
            } catch (JsonSyntaxException unused) {
                LogUtil.b(TAG, "Parsing exception");
                return;
            }
        }
        if (pushBeanBase != null) {
            HashMap<String, IpushBase> hashMap = sMsgHandlers;
            synchronized (hashMap) {
                IpushBase ipushBase = hashMap.get(pushBeanBase.pushType);
                if (ipushBase == null) {
                    ReleaseLogUtil.e(TAG, "message ", pushBeanBase, " has no handlers");
                } else {
                    ipushBase.processPushMsg(BaseApplication.getContext(), data);
                }
            }
        }
    }

    public boolean checkAuthorizationMsg(String str) {
        JSONException e;
        int i;
        String str2;
        LogUtil.a(TAG, "enter checkAuthorizationMsg content = ", str);
        try {
            JSONObject jSONObject = new JSONObject(str);
            i = jSONObject.getInt("pushType");
            try {
                str2 = jSONObject.getString(PUSH_ID);
            } catch (JSONException e2) {
                e = e2;
                LogUtil.b(TAG, "checkAuthorizationMsg jsonException = ", LogAnonymous.b((Throwable) e));
                str2 = "";
                if (i == 7) {
                }
                try {
                    Class<?> cls = Class.forName(AUTHORIZATION_CLASS);
                    cls.getMethod(AUTHORIZATION_HANDLE_METHOD, Integer.class, String.class, Context.class).invoke(cls.newInstance(), Integer.valueOf(i), str2, BaseApplication.getContext());
                    return true;
                } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | NoSuchMethodException | InvocationTargetException e3) {
                    LogUtil.b(TAG, "checkAuthorizationMsg ", LogAnonymous.b(e3));
                    return false;
                }
            }
        } catch (JSONException e4) {
            e = e4;
            i = 0;
        }
        if (i == 7 && i != 8) {
            return false;
        }
        Class<?> cls2 = Class.forName(AUTHORIZATION_CLASS);
        cls2.getMethod(AUTHORIZATION_HANDLE_METHOD, Integer.class, String.class, Context.class).invoke(cls2.newInstance(), Integer.valueOf(i), str2, BaseApplication.getContext());
        return true;
    }

    private boolean isPushMessageToDevice(String str) {
        LogUtil.c(TAG, "enter checkAuthorizationMsg content : ", str);
        try {
            String string = new JSONObject(str).getString(CommonUtil.MSG_CONTENT);
            JSONObject jSONObject = new JSONObject(string);
            final int i = string.contains(PUSH_IS_RECONNECT) ? jSONObject.getInt(PUSH_IS_RECONNECT) : 0;
            final String string2 = jSONObject.getString("reqId");
            final String string3 = jSONObject.getString("devType");
            final String string4 = jSONObject.getString("targetDevice");
            final int i2 = jSONObject.getInt("timeout");
            LogUtil.c(TAG, "get param from content, requestId  ", string2, " deviceType", string3, "timeout :", Integer.valueOf(i2), " targetDevice ", string4);
            ThreadPoolManager.d().d(TAG, new Runnable() { // from class: com.huawei.pluginmessagecenter.service.HuaweiHealthHmsPushService.1
                @Override // java.lang.Runnable
                public void run() {
                    LogUtil.a(HuaweiHealthHmsPushService.TAG, "ready push info to device.");
                    Intent intent = new Intent();
                    intent.setClassName(BaseApplication.getContext(), "com.huawei.hwdevice.phoneprocess.service.HandleIntentService");
                    intent.setPackage(BaseApplication.getContext().getPackageName());
                    intent.setAction("com.huawei.health.ACTION_NOTIFY_PUSH_MULTI_LINK");
                    intent.putExtra("reqId", string2);
                    intent.putExtra("devType", string3);
                    intent.putExtra("timeout", i2);
                    intent.putExtra("targetDevice", string4);
                    intent.putExtra(HuaweiHealthHmsPushService.PUSH_IS_RECONNECT, i);
                    BaseApplication.getContext().startService(intent);
                }
            });
            return true;
        } catch (JSONException e) {
            LogUtil.b(TAG, "isPushMessageToDevice Exception : ", LogAnonymous.b((Throwable) e));
            return false;
        }
    }

    private boolean isAdvertiseMessage(String str) {
        JSONObject jSONObject;
        try {
            jSONObject = new JSONObject(str);
        } catch (JSONException e) {
            LogUtil.b(TAG, "isAdvertiseMessage jsonException = ", LogAnonymous.b((Throwable) e));
            jSONObject = null;
        }
        PushMessage pushMessage = new PushMessage();
        if (jSONObject == null) {
            return false;
        }
        boolean fillMessage = pushMessage.fillMessage(jSONObject);
        LogUtil.c(TAG, "pushMessage");
        return fillMessage;
    }

    private boolean isGroupMessage(String str) {
        try {
            if (!new JSONObject(str).has(GROUP_PUSH_MESSAGE)) {
                return false;
            }
            try {
                Class<?> cls = Class.forName(HEALTH_GROUP_PUSH_CLASS_NAME);
                cls.getMethod("onPushMessage", Context.class, String.class).invoke(cls.newInstance(), BaseApplication.getContext(), str);
                return true;
            } catch (ClassNotFoundException e) {
                e = e;
                LogUtil.b(TAG, "isGroupMessage ", LogAnonymous.b(e));
                return false;
            } catch (IllegalAccessException e2) {
                e = e2;
                LogUtil.b(TAG, "isGroupMessage ", LogAnonymous.b(e));
                return false;
            } catch (InstantiationException e3) {
                e = e3;
                LogUtil.b(TAG, "isGroupMessage ", LogAnonymous.b(e));
                return false;
            } catch (NoSuchMethodException e4) {
                e = e4;
                LogUtil.b(TAG, "isGroupMessage ", LogAnonymous.b(e));
                return false;
            } catch (InvocationTargetException e5) {
                e = e5;
                LogUtil.b(TAG, "isGroupMessage ", LogAnonymous.b(e));
                return false;
            } catch (Exception unused) {
                LogUtil.b(TAG, "isGroupMessage Exception with no match type");
                return false;
            }
        } catch (JSONException e6) {
            LogUtil.b(TAG, "isGroupMessage jsonException = ", LogAnonymous.b((Throwable) e6));
            return false;
        }
    }

    private void sendPushToken(String str) {
        LogUtil.a(TAG, "enter sendPushToken");
        Intent intent = new Intent();
        intent.setPackage(BaseApplication.getContext().getPackageName());
        intent.setAction("com.huawei.health.action.ACTION_RECEIVE_PUSH_TOKEN");
        intent.putExtra(RemoteMessageConst.DEVICE_TOKEN, str);
        BaseApplication.getContext().sendBroadcast(intent, LocalBroadcast.c);
    }

    public static void setCallBack(String str, IpushTokenCallback ipushTokenCallback) {
        LogUtil.a(TAG, "enter setCallBack");
        if (Utils.i()) {
            synchronized (LOCK) {
                sPushCallbackMap.put(str, ipushTokenCallback);
            }
            return;
        }
        ReleaseLogUtil.d(RELEASE_TAG, "do not setCallBack, is support_push", true);
    }

    public static void pushTokenToCloud(String str) {
        synchronized (LOCK) {
            for (IpushTokenCallback ipushTokenCallback : sPushCallbackMap.values()) {
                if (ipushTokenCallback != null) {
                    ipushTokenCallback.pushTokenHandle(BaseApplication.getContext(), str);
                }
            }
        }
    }

    private boolean parseStringToMap(String str) {
        LogUtil.a(TAG, "content:", str);
        try {
            Map map = (Map) new Gson().fromJson(str, (Class) new HashMap().getClass());
            if (map == null) {
                LogUtil.b(TAG, "map = null");
                return false;
            }
            String str2 = (String) map.get(GUIDE_MAP_KEY);
            if (TextUtils.isEmpty(str2)) {
                LogUtil.h(TAG, "version is Empty");
                return false;
            }
            try {
                Class<?> cls = Class.forName(GUIDE_PAGE_MESSAGE_NAME);
                cls.getMethod(IpushBase.class.getMethods()[0].getName(), Context.class, String.class).invoke(cls.newInstance(), BaseApplication.getContext(), str2);
                return true;
            } catch (ClassNotFoundException e) {
                e = e;
                LogUtil.b(TAG, "parseStringToMap ", LogAnonymous.b(e));
                return false;
            } catch (IllegalAccessException e2) {
                e = e2;
                LogUtil.b(TAG, "parseStringToMap ", LogAnonymous.b(e));
                return false;
            } catch (InstantiationException e3) {
                e = e3;
                LogUtil.b(TAG, "parseStringToMap ", LogAnonymous.b(e));
                return false;
            } catch (NoSuchMethodException e4) {
                e = e4;
                LogUtil.b(TAG, "parseStringToMap ", LogAnonymous.b(e));
                return false;
            } catch (InvocationTargetException e5) {
                e = e5;
                LogUtil.b(TAG, "parseStringToMap ", LogAnonymous.b(e));
                return false;
            } catch (Exception unused) {
                LogUtil.b(TAG, "parseStringToMap Exception with no match type");
                return false;
            }
        } catch (JsonSyntaxException unused2) {
            LogUtil.b(TAG, "JsonSyntaxException");
            return false;
        }
    }

    public static void initHms() {
        LogUtil.a(TAG, "initHMS enter: ");
        HmsInstanceId.getInstance(BaseApplication.getContext()).getAAID().addOnSuccessListener(new OnSuccessListener<AAIDResult>() { // from class: com.huawei.pluginmessagecenter.service.HuaweiHealthHmsPushService.3
            @Override // com.huawei.hmf.tasks.OnSuccessListener
            public void onSuccess(AAIDResult aAIDResult) {
                ReleaseLogUtil.e(HuaweiHealthHmsPushService.RELEASE_TAG, "initHms getAAID success");
                if (Utils.i()) {
                    HuaweiHealthHmsPushService.setCallBack("MessagePushToken", new mrh());
                } else {
                    LogUtil.a(HuaweiHealthHmsPushService.TAG, "initHms getAAID success,but is not cloud country");
                }
                HuaweiHealthHmsPushService.getToken();
            }
        }).addOnFailureListener(new OnFailureListener() { // from class: com.huawei.pluginmessagecenter.service.HuaweiHealthHmsPushService.2
            @Override // com.huawei.hmf.tasks.OnFailureListener
            public void onFailure(Exception exc) {
                ReleaseLogUtil.c(HuaweiHealthHmsPushService.RELEASE_TAG, "initHMS exception : ", LogAnonymous.b((Throwable) exc));
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void getToken() {
        ThreadPoolManager.d().execute(new Runnable() { // from class: com.huawei.pluginmessagecenter.service.HuaweiHealthHmsPushService.4
            @Override // java.lang.Runnable
            public void run() {
                try {
                    String token = HmsInstanceId.getInstance(BaseApplication.getContext()).getToken(String.valueOf(BuildConfig.HMS_APPLICATION_ID), "HCM");
                    ReleaseLogUtil.e(HuaweiHealthHmsPushService.RELEASE_TAG, "getToken done = ", health.compact.a.CommonUtil.l(token));
                    if (TextUtils.isEmpty(token)) {
                        return;
                    }
                    HuaweiHealthHmsPushService.pushTokenToCloud(token);
                    HuaweiHealthHmsPushService.sendWalletToken(token);
                    HuaweiHealthHmsPushService.sendHealthGroupToken(token);
                } catch (Exception e) {
                    LogUtil.b(HuaweiHealthHmsPushService.TAG, "getToken exception = ", LogAnonymous.b((Throwable) e));
                }
            }
        });
    }

    public static void deleteToken() {
        ThreadPoolManager.d().execute(new Runnable() { // from class: com.huawei.pluginmessagecenter.service.HuaweiHealthHmsPushService$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                HuaweiHealthHmsPushService.lambda$deleteToken$0();
            }
        });
    }

    static /* synthetic */ void lambda$deleteToken$0() {
        try {
            HmsInstanceId.getInstance(BaseApplication.getContext()).deleteToken(String.valueOf(BuildConfig.HMS_APPLICATION_ID), "HCM");
            ReleaseLogUtil.e(RELEASE_TAG, "token deleted successfully");
        } catch (ApiException e) {
            LogUtil.b(TAG, "deleteToken exception = " + LogAnonymous.b((Throwable) e));
            retryDeleteToken();
        }
    }

    private static void retryDeleteToken() {
        HandlerCenter.e(TAG).postTask(new Runnable() { // from class: com.huawei.pluginmessagecenter.service.HuaweiHealthHmsPushService$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                HuaweiHealthHmsPushService.lambda$retryDeleteToken$1();
            }
        }, 500L);
    }

    static /* synthetic */ void lambda$retryDeleteToken$1() {
        try {
            HmsInstanceId.getInstance(BaseApplication.getContext()).deleteToken(String.valueOf(BuildConfig.HMS_APPLICATION_ID), "HCM");
            ReleaseLogUtil.e(RELEASE_TAG, "retry token deleted successfully");
        } catch (ApiException e) {
            LogUtil.b(TAG, "retry deleteToken exception = " + LogAnonymous.b((Throwable) e));
        }
    }
}
