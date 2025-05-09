package com.huawei.pluginhealthzone.interactors;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.drawable.Icon;
import android.location.LocationManager;
import android.net.Uri;
import android.text.TextUtils;
import com.amap.api.services.geocoder.GeocodeSearch;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.annotations.SerializedName;
import com.huawei.android.airsharing.api.IEventListener;
import com.huawei.health.R;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.health.messagecenter.api.MessageCenterApi;
import com.huawei.health.messagecenter.model.IpushBase;
import com.huawei.health.messagecenter.model.MessageConstant;
import com.huawei.health.messagecenter.model.MessageObject;
import com.huawei.hihealth.util.HiJsonUtil;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.utils.PermissionUtil;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.pluginhealthzone.activity.FamilyHealthTempActivity;
import com.huawei.pluginhealthzone.cloud.CloudApi;
import com.huawei.pluginhealthzone.cloud.HttpDataCallback;
import com.huawei.pluginhealthzone.devicelink.callback.DeviceDataListener;
import com.huawei.pluginhealthzone.interactors.HealthZonePushReceiver;
import com.huawei.pluginhealthzone.jsmodule.callback.PushMessageListener;
import com.huawei.pluginhealthzone.receiver.HealthZonePushButtonReceiver;
import com.huawei.ui.commonui.R$drawable;
import com.huawei.ui.commonui.R$string;
import com.huawei.utils.FoundationCommonUtil;
import com.huawei.watchface.mvp.model.webview.JsNetwork;
import defpackage.jdh;
import defpackage.jpt;
import defpackage.mpq;
import defpackage.mpv;
import defpackage.mpx;
import defpackage.mpy;
import defpackage.mqc;
import defpackage.mqh;
import defpackage.mqj;
import defpackage.mqp;
import health.compact.a.BroadcastManagerUtil;
import health.compact.a.CommonUtil;
import health.compact.a.CommonUtils;
import health.compact.a.LocalBroadcast;
import health.compact.a.LogAnonymous;
import health.compact.a.Services;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class HealthZonePushReceiver implements IpushBase {
    public static final String BLOOD_OXYGEN_NOTIFY = "23";
    public static final String BLOOD_PRESSURE_NOTIFY = "24";
    public static final String BLOOD_SUGAR_DAWN_NOTIFY = "27";
    public static final String BODY_TEMPERATURE_HIGH_NOTIFY = "34";
    public static final String BODY_TEMPERATURE_LOW_NOTIFY = "35";
    public static final String COMMENT_FAILED_NOTIFY = "38";
    private static final int COMMENT_NOTIFICATION_ID_START = 20210615;
    public static final String CYCLE_BLOOD_OXYGEN_NOTIFY = "33";
    public static final String DATA_POST_COMMENTS_NOTIFY = "36";
    public static final String DEAUTH_EVENT_NOTIFY = "20";
    public static final String DETAIL_TYPE = "detailType";
    public static final String ECG_MEASUREMENT_ABNORMAL = "43";
    public static final String ECG_REMINDER_NOTIFY = "39";
    public static final String FALL_LOCATION_NOTIFY = "42";
    public static final String FAMILY_CARE_NOTIFY = "40";
    public static final String FOLLOW_ACCEPT_NOTIFY = "17";
    private static final int FOLLOW_NOTIFICATION_ID_START = 20000000;
    public static final String FOLLOW_REJECT_NOTIFY = "18";
    public static final String FOLLOW_REQUEST_NOTIFY = "16";
    public static final String FROM = "from";
    private static final String HEALTHZONE_DEEPLINK = "huaweischeme://healthapp/router/familyhealth?healthType=10";
    private static final String HEALTHZONE_URI = "messagecenter://healthZone?skipType=1&destination=com.huawei.pluginhealthzone.activity.FamilyHealthTempActivity";
    private static final int HEALTH_NOTIFICATION_ID_END = 20200213;
    private static final int HEALTH_NOTIFICATION_ID_START = 20200204;
    public static final String HIGH_HEART_RATE_NOTIFY = "21";
    private static final int HUID_INTERCEPTION_EIGHT_LENGTH = 8;
    private static final String HWSCHEMEBASICHEALTH_ACTIVITY_NAME = "com.huawei.health.browseraction.HwSchemeBasicHealthActivity";
    public static final String IS_HASH_HUID = "isHashHuid";
    private static final int LOCATION_NOTIFICATION_ID_PERMISSIONS = 20210510;
    private static final int LOCATION_NOTIFICATION_ID_START = 20210511;
    public static final String LOCATION_UPLOAD = "5001";
    public static final String LOW_HEART_RATE_NOTIFY = "22";
    public static final String MEMBER_HUID = "memberhuid";
    private static final String NUM_ONE = "1";
    private static final String PING_ACTIVITY = "1";
    private static final String PING_HEALTH_APP = "0";
    public static final String POSTPRANDIAL_BLOOD_GLUCOSE_NOTIFY = "26";
    public static final String PREPRANDIAL_BLOOD_GLUCOSE_NOTIFY = "25";
    public static final String PRESSURE_NOTIFY = "32";
    public static final String PROACTIVE_SHARING_NOTIFY = "37";
    private static final String PUSH_KEY_SPLIT = "-";
    public static final String PUSH_TYPE = "pushType";
    private static final String RESULT_CODE = "resultCode";
    public static final String SLEEP_SCORE_NOTIFY = "30";
    public static final String SLEEP_TIME_NOTIFY = "29";
    public static final String SOS_LOCATION_NOTIFY = "41";
    public static final String STEP_NOTIFY = "28";
    private static final int SUCCESS = 0;
    private static final String TAG = "HealthZonePushReceiver";
    private static final int TO_DETAIL = 2;
    private static final int TO_H5 = 0;
    private static final int TO_SET = 1;
    public static final String UNFOLLOW_EVENT_NOTIFY = "19";
    private static HealthZonePushButtonReceiver healthZonePushButtonReceiver = null;
    private static int sCommentNotificationId = 20210615;
    private static int sFollowNotificationId = 20000000;
    private static int sHealthNotificationId = 20200204;
    private static int sLocationNotificationId = 20210511;
    private static PushMessageListener sPushMessageListener;
    private boolean mIsFirstGenerateMsg = false;
    private static final Object LOCK = new Object();
    private static final List<String> PROACTIVE_NOTIFICATION_PUSH_LIST = new ArrayList<String>() { // from class: com.huawei.pluginhealthzone.interactors.HealthZonePushReceiver.2
        {
            add(HealthZonePushReceiver.PROACTIVE_SHARING_NOTIFY);
            add(HealthZonePushReceiver.COMMENT_FAILED_NOTIFY);
            add("39");
            add(HealthZonePushReceiver.ECG_MEASUREMENT_ABNORMAL);
            add(HealthZonePushReceiver.FAMILY_CARE_NOTIFY);
            add(HealthZonePushReceiver.SOS_LOCATION_NOTIFY);
            add("42");
        }
    };
    private static HashMap<Long, a> sLocationPushInfoMap = new HashMap<>(16);
    private static HashMap<String, Long> sNotifyMap = new HashMap<>(16);
    private static long NOTIFY_INTERVAL = 600000;

    @Override // com.huawei.health.messagecenter.model.IpushBase
    public List<String> getPushType() {
        ArrayList arrayList = new ArrayList(Arrays.asList(LOCATION_UPLOAD, "5002"));
        for (int i = 16; i <= 30; i++) {
            arrayList.add(String.valueOf(i));
        }
        return arrayList;
    }

    @Override // com.huawei.health.messagecenter.model.IpushBase
    public void processPushMsg(Context context, String str) {
        LogUtil.a(TAG, "processPushMsg enter:", str);
        if (str == null || TextUtils.isEmpty(str)) {
            LogUtil.h(TAG, "processPushMsg msg is null or empty");
            return;
        }
        try {
            Gson gson = new Gson();
            e eVar = (e) gson.fromJson(str, e.class);
            String d = eVar.d();
            if (d == null) {
                LogUtil.h(TAG, "processPushMsg pushType is null");
                return;
            }
            Notification.Builder xf_ = jdh.c().xf_();
            if ("com.huawei.bone".equals(BaseApplication.getAppPackage())) {
                xf_.setSmallIcon(R$drawable.ic_wear_notification);
            } else {
                xf_.setSmallIcon(R$drawable.healthlogo_ic_notification);
            }
            Intent intent = new Intent("android.intent.action.MAIN");
            if (isFollowNotification(d)) {
                pushListenerCallback(d, HiJsonUtil.e(eVar));
                setFollowNotification(context, eVar, (e.d) gson.fromJson(CommonUtil.z(eVar.b()), e.d.class), xf_, intent);
            } else if (isAboutLocation(d)) {
                getPositionPushInfo(context, eVar, xf_, intent);
            } else {
                setHealthNotification(context, eVar, xf_, intent);
            }
        } catch (JsonSyntaxException e2) {
            LogUtil.b(TAG, "processPushMsg exception", LogAnonymous.b((Throwable) e2));
        }
    }

    private void pushListenerCallback(String str, String str2) {
        if (sPushMessageListener != null) {
            LogUtil.a(TAG, "sPushMessageListener != null");
            sPushMessageListener.onChange(str, str2);
        }
    }

    private boolean isAboutLocation(String str) {
        return LOCATION_UPLOAD.equals(str);
    }

    private void getPositionPushInfo(final Context context, e eVar, final Notification.Builder builder, final Intent intent) {
        LogUtil.a(TAG, "getPositionPushInfo enter");
        try {
            mpq.d().getPositionPushInfo(eVar.b(), 1, new HttpDataCallback() { // from class: com.huawei.pluginhealthzone.interactors.HealthZonePushReceiver.1
                @Override // com.huawei.pluginhealthzone.cloud.HttpDataCallback
                public void onFailure(int i, String str) {
                    LogUtil.b(HealthZonePushReceiver.TAG, "getPositionPushInfo onFailure errorCode:", Integer.valueOf(i));
                }

                @Override // com.huawei.pluginhealthzone.cloud.HttpDataCallback
                public void onSuccess(JSONObject jSONObject) {
                    try {
                        if (jSONObject.has("resultCode") && jSONObject.getInt("resultCode") == 0) {
                            HealthZonePushReceiver.this.setAboutLocation(context, jSONObject, builder, intent);
                        }
                    } catch (JSONException e2) {
                        LogUtil.b(HealthZonePushReceiver.TAG, "getPositionPushInfo onSuccess:", LogAnonymous.b((Throwable) e2));
                    }
                }
            });
        } catch (NumberFormatException e2) {
            LogUtil.b(TAG, "getPositionPushInfo:", LogAnonymous.b((Throwable) e2));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setAboutLocation(Context context, JSONObject jSONObject, Notification.Builder builder, Intent intent) {
        LogUtil.a(TAG, "setAboutLocation = ", jSONObject.toString());
        try {
            a aVar = jSONObject.has("pushInfo") ? (a) HiJsonUtil.e(jSONObject.getJSONObject("pushInfo").toString(), a.class) : null;
            if (aVar == null) {
                return;
            }
            packageLocationInformation(context, aVar, builder, intent);
        } catch (JSONException e2) {
            LogUtil.b(TAG, "jsonException =", LogAnonymous.b((Throwable) e2));
        }
    }

    private void startPosition(Context context, a aVar, Notification.Builder builder, Intent intent) {
        if (aVar == null) {
            LogUtil.h(TAG, "pushInfo is null.");
            return;
        }
        int intValue = aVar.a().intValue();
        if (aVar.a().intValue() == 1) {
            LogUtil.a(TAG, "HuId = ", aVar.d(), "Info = ", HiJsonUtil.e(aVar));
            sLocationPushInfoMap.put(aVar.d(), aVar);
        }
        if (sLocationPushInfoMap.get(aVar.d()) != null) {
            aVar = sLocationPushInfoMap.get(aVar.d());
        }
        if (aVar != null) {
            aVar.c(intValue);
        }
        if (getLocationPermission(context, builder, intent, aVar)) {
            getCommonUsedDevices(context, builder, intent, aVar);
        }
    }

    private void packageLocationInformation(Context context, a aVar, Notification.Builder builder, Intent intent) {
        int i;
        LogUtil.a(TAG, "HiJsonUtil.toJson(pushInfo) = ", HiJsonUtil.e(aVar));
        sendMsgToDevice(aVar, false);
        switch (aVar.j().intValue()) {
            case 1:
                startPosition(context, aVar, builder, intent);
                return;
            case 2:
                mqh.b().e();
                getCommonUsedDevices(context, builder, intent, aVar);
                return;
            case 3:
                String string = context.getString(R.string._2130840394_res_0x7f020b4a, setUserNickName(aVar));
                intent.putExtra("pushType", String.valueOf(aVar.j()));
                intent.putExtra(MEMBER_HUID, String.valueOf(aVar.d()));
                if (PermissionUtil.e()) {
                    intent.setAction("health_zone_go_h5");
                    goSettingLocationByActivity(context, builder, intent, string, 0);
                    return;
                } else {
                    intent.setAction("health_zone_go_h5");
                    goSettingLocation(context, builder, intent, string, 0);
                    return;
                }
            case 4:
                i = R.string._2130840398_res_0x7f020b4e;
                break;
            case 5:
                i = R.string._2130840393_res_0x7f020b49;
                break;
            case 6:
                i = R.string._2130840397_res_0x7f020b4d;
                break;
            default:
                LogUtil.h(TAG, "packageLocationInformation pushType : ", aVar.j());
                return;
        }
        setLocationNotification(context, builder, intent, aVar, i);
    }

    private void getCommonUsedDevices(final Context context, final Notification.Builder builder, final Intent intent, final a aVar) {
        LogUtil.a(TAG, "getCommonUsedDevices enter");
        mpq.d().getCommonUsedDevices("FAMILY_SPACE", new HttpDataCallback() { // from class: com.huawei.pluginhealthzone.interactors.HealthZonePushReceiver.5
            @Override // com.huawei.pluginhealthzone.cloud.HttpDataCallback
            public void onFailure(int i, String str) {
                LogUtil.b(HealthZonePushReceiver.TAG, "getCommonUsedDevices onFailure errorCode:", Integer.valueOf(i));
                HealthZonePushReceiver.this.setLocationNotification(context, builder, intent, aVar, aVar.j().intValue() == 1 ? R.string._2130840395_res_0x7f020b4b : R.string._2130840396_res_0x7f020b4c);
            }

            @Override // com.huawei.pluginhealthzone.cloud.HttpDataCallback
            public void onSuccess(JSONObject jSONObject) {
                if (jSONObject == null) {
                    return;
                }
                LogUtil.a(HealthZonePushReceiver.TAG, "getCommonUsedDevices data =", jSONObject.toString());
                HealthZonePushReceiver.this.settingTheMessage(context, builder, intent, aVar, jSONObject);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void settingTheMessage(Context context, Notification.Builder builder, Intent intent, a aVar, JSONObject jSONObject) {
        int i;
        boolean isWatchOnCommonList = isWatchOnCommonList(jSONObject);
        if (aVar.j().intValue() == 1) {
            i = jSONObject.toString().contains(FoundationCommonUtil.getAndroidId(context)) ? R.string._2130840406_res_0x7f020b56 : R.string._2130840395_res_0x7f020b4b;
            uploadLocation(context, isWatchOnCommonList);
            handleGenerateMsg(context, aVar, context.getString(R.string._2130845892_res_0x7f0220c4, setUserNickName(aVar)), 1);
        } else {
            i = jSONObject.toString().contains(FoundationCommonUtil.getAndroidId(context)) ? R.string._2130840407_res_0x7f020b57 : R.string._2130840396_res_0x7f020b4c;
            handleGenerateMsg(context, aVar, context.getString(R.string._2130845893_res_0x7f0220c5, setUserNickName(aVar)), aVar.j().intValue());
        }
        int i2 = i;
        sendMsgToDevice(aVar, isWatchOnCommonList);
        if (aVar.j().intValue() == 1 && aVar.a().intValue() == 0) {
            LogUtil.a(TAG, "start position is not isStart begin");
        } else {
            setLocationNotification(context, builder, intent, aVar, i2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isWatchOnCommonList(JSONObject jSONObject) {
        DeviceInfo a2 = jpt.a(TAG);
        LogUtil.a(TAG, "currentDeviceInfo:", a2);
        String str = null;
        String securityUuid = (a2 == null || a2.getDeviceConnectState() != 2) ? null : a2.getSecurityUuid();
        if (securityUuid != null) {
            str = securityUuid + "#ANDROID21";
        }
        return securityUuid != null && (jSONObject.toString().contains(securityUuid) || jSONObject.toString().contains(str));
    }

    private void handleGenerateMsg(Context context, a aVar, String str, int i) {
        if (i == 1) {
            if (this.mIsFirstGenerateMsg) {
                return;
            }
            this.mIsFirstGenerateMsg = true;
            generateMessage(context, aVar, str);
            return;
        }
        if (this.mIsFirstGenerateMsg) {
            this.mIsFirstGenerateMsg = false;
            generateMessage(context, aVar, str);
        }
    }

    private void generateMessage(final Context context, final a aVar, final String str) {
        LogUtil.a(TAG, "enter generateViewLocationMsg");
        final MessageCenterApi messageCenterApi = (MessageCenterApi) Services.c("PluginMessageCenter", MessageCenterApi.class);
        messageCenterApi.getMessageId(String.valueOf(19), MessageConstant.VIEWING_OR_ENDED_LOCATION_MSG, new IBaseResponseCallback() { // from class: mqb
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public final void d(int i, Object obj) {
                HealthZonePushReceiver.lambda$generateMessage$0(str, context, aVar, messageCenterApi, i, obj);
            }
        });
    }

    public static /* synthetic */ void lambda$generateMessage$0(String str, Context context, a aVar, MessageCenterApi messageCenterApi, int i, Object obj) {
        if (i == 0 && (obj instanceof String)) {
            MessageObject messageObject = new MessageObject();
            messageObject.setMsgId((String) obj);
            messageObject.setModule(String.valueOf(19));
            messageObject.setType(MessageConstant.VIEWING_OR_ENDED_LOCATION_MSG);
            messageObject.setPosition(1);
            messageObject.setMsgPosition(11);
            messageObject.setReadFlag(0);
            messageObject.setMsgContent(str);
            messageObject.setMsgTitle(context.getResources().getString(R.string.IDS_hwh_family_health_zone));
            messageObject.setDetailUri("messagecenter://healthZone?skipType=1&destination=com.huawei.pluginhealthzone.activity.FamilyHealthTempActivity&pushType=4&memberhuid=" + aVar.d());
            messageObject.setCreateTime(System.currentTimeMillis());
            if (LoginInit.getInstance(BaseApplication.getContext()) != null) {
                messageObject.setHuid(LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1011));
            }
            messageCenterApi.insertMessage(messageObject);
        }
    }

    private boolean getLocationPermission(Context context, Notification.Builder builder, Intent intent, a aVar) {
        if (isGpsEnable()) {
            if (context.getApplicationContext().checkSelfPermission("android.permission.ACCESS_COARSE_LOCATION") == 0 || context.getApplicationContext().checkSelfPermission("android.permission.ACCESS_FINE_LOCATION") == 0) {
                return true;
            }
            intent.putExtra("pushType", String.valueOf(aVar.j()));
            intent.putExtra(MEMBER_HUID, String.valueOf(aVar.d()));
            if (PermissionUtil.e()) {
                intent.setAction("health_zone_go_details_setting");
                goSettingLocationByActivity(context, builder, intent, context.getResources().getString(R.string._2130840390_res_0x7f020b46, setUserNickName(aVar)), 2);
            } else {
                intent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
                intent.setData(Uri.fromParts("package", com.huawei.haf.application.BaseApplication.d(), null));
                goSettingLocation(context, builder, intent, context.getResources().getString(R.string._2130840390_res_0x7f020b46, setUserNickName(aVar)), 2);
            }
            return false;
        }
        String string = context.getResources().getString(R.string._2130840389_res_0x7f020b45, setUserNickName(aVar));
        intent.putExtra("pushType", String.valueOf(aVar.j()));
        intent.putExtra(MEMBER_HUID, String.valueOf(aVar.d()));
        if (PermissionUtil.e()) {
            intent.setAction("health_zone_location_source_setting");
            goSettingLocationByActivity(context, builder, intent, string, 1);
        } else {
            intent.setAction("android.settings.LOCATION_SOURCE_SETTINGS");
            goSettingLocation(context, builder, intent, string, 1);
        }
        return false;
    }

    private String setUserNickName(a aVar) {
        if (!TextUtils.isEmpty(aVar.f())) {
            return aVar.f();
        }
        if (TextUtils.isEmpty(aVar.g())) {
            return !TextUtils.isEmpty(aVar.e()) ? aVar.e() : "";
        }
        return CommonUtil.m(aVar.g());
    }

    private void goSettingLocation(Context context, Notification.Builder builder, Intent intent, String str, int i) {
        LogUtil.a(TAG, "goSettingLocation enter");
        setRegisterReceiver(context);
        setNotificationBuilder(context, builder, str);
        setBuilderAction(context, getPendingIntent(context, i, intent), builder);
        intent.setAction("android.intent.action.MAIN");
        intent.setAction("health_zone_push_cancel");
        builder.addAction(new Notification.Action.Builder((Icon) null, context.getResources().getString(R.string._2130840357_res_0x7f020b25), PendingIntent.getBroadcast(context, 0, intent, 201326592)).build());
        builder.setAutoCancel(true);
        builder.setCategory("HwFamily01");
        jdh.c().xh_(LOCATION_NOTIFICATION_ID_PERMISSIONS, builder.build());
    }

    private PendingIntent getPendingIntent(Context context, int i, Intent intent) {
        if (i == 0) {
            return PendingIntent.getBroadcast(context, 0, intent, 201326592);
        }
        return PendingIntent.getActivity(context, 0, intent, 201326592);
    }

    private void goSettingLocationByActivity(Context context, Notification.Builder builder, Intent intent, String str, int i) {
        LogUtil.a(TAG, "goSettingLocationByActivity enter");
        setNotificationBuilder(context, builder, str);
        intent.setClassName(BaseApplication.getAppPackage(), HWSCHEMEBASICHEALTH_ACTIVITY_NAME);
        intent.putExtra("healthZoneNotification", 1);
        setBuilderAction(context, getPendingIntentByActivity(context, i, intent), builder);
        intent.setAction("health_zone_push_cancel");
        builder.addAction(new Notification.Action.Builder((Icon) null, context.getResources().getString(R.string._2130840357_res_0x7f020b25), PendingIntent.getActivity(context, 0, intent, 201326592)).build());
        builder.setAutoCancel(true);
        builder.setCategory("HwFamily01");
        jdh.c().xh_(LOCATION_NOTIFICATION_ID_PERMISSIONS, builder.build());
    }

    private PendingIntent getPendingIntentByActivity(Context context, int i, Intent intent) {
        if (i == 0) {
            return PendingIntent.getActivity(context, 0, intent, 201326592);
        }
        return PendingIntent.getActivity(context, 0, intent, 201326592);
    }

    private void setNotificationBuilder(Context context, Notification.Builder builder, String str) {
        builder.setContentTitle(context.getResources().getString(R$string.IDS_hwh_family_health_zone)).setContentText(str).setStyle(new Notification.BigTextStyle().bigText(str));
    }

    private void setBuilderAction(Context context, PendingIntent pendingIntent, Notification.Builder builder) {
        builder.addAction(new Notification.Action.Builder((Icon) null, context.getResources().getString(R.string._2130843892_res_0x7f0218f4), pendingIntent).build());
        builder.setContentIntent(pendingIntent);
    }

    private void setRegisterReceiver(Context context) {
        LogUtil.a(TAG, "setRegisterReceiver eneter");
        healthZonePushButtonReceiver = new HealthZonePushButtonReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("health_zone_push_cancel");
        intentFilter.addAction("health_zone_go_h5");
        BroadcastManagerUtil.bFA_(context, healthZonePushButtonReceiver, intentFilter, LocalBroadcast.c, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setLocationNotification(Context context, Notification.Builder builder, Intent intent, a aVar, int i) {
        pushListenerCallback(String.valueOf(aVar.j()), HiJsonUtil.e(aVar));
        intent.putExtra("pushType", String.valueOf(aVar.j()));
        intent.putExtra(MEMBER_HUID, String.valueOf(aVar.d()));
        intent.setClass(context, FamilyHealthTempActivity.class);
        setNotificationBuilder(context, builder, context.getString(i, setUserNickName(aVar)));
        intent.setFlags(335544320);
        builder.setAutoCancel(true).setContentIntent(PendingIntent.getActivity(context, sLocationNotificationId, intent, 201326592));
        builder.setCategory("HwFamily01");
        jdh.c().xh_(sLocationNotificationId, builder.build());
    }

    private void sendMsgToDevice(a aVar, boolean z) {
        int i;
        LogUtil.a(TAG, "pushInfo getPushSubType() = ", Integer.valueOf(aVar.j().intValue()));
        mpv mpvVar = new mpv();
        int intValue = aVar.j().intValue();
        if (intValue != 1) {
            if (intValue != 2) {
                if (intValue != 3) {
                    if (intValue == 7) {
                        mpvVar.j("1");
                    } else if (intValue == 8) {
                        mpvVar.j("0");
                    } else if (intValue == 9) {
                        this.mIsFirstGenerateMsg = false;
                        i = 3003;
                    } else {
                        LogUtil.h(TAG, "sendMsgToDevice pushType : ", aVar.j());
                        return;
                    }
                    i = 3002;
                } else {
                    i = 3001;
                }
            } else if (!z) {
                return;
            } else {
                i = IEventListener.EVENT_ID_DEVICE_DISCONN_SUCC;
            }
        } else {
            if (!z) {
                return;
            }
            mpvVar.e(String.valueOf(isGpsEnable()));
            i = IEventListener.EVENT_ID_DEVICE_CONN_FAIL;
        }
        if (aVar.j().intValue() == 7 || aVar.j().intValue() == 9) {
            judgeCommonUsedDevices(mpvVar, aVar, i);
        } else {
            sendMsgBodyToDevice(mpvVar, aVar, i);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendMsgBodyToDevice(mpv mpvVar, a aVar, int i) {
        mpv commonParameter = setCommonParameter(mpvVar, aVar, i);
        mpx mpxVar = new mpx();
        mpxVar.a(commonParameter);
        LogUtil.a(TAG, "sendMsgToDevice ResponseMsgBody = ", HiJsonUtil.e(mpxVar));
        mpy.d().activeSendMsgToDevice(0, mpxVar, new DeviceDataListener() { // from class: mqa
            @Override // com.huawei.pluginhealthzone.devicelink.callback.DeviceDataListener
            public final void onResult(int i2, mpx mpxVar2) {
                LogUtil.a(HealthZonePushReceiver.TAG, "sendMsgToDevice resultCode = ", Integer.valueOf(i2), "ResponseMsgBody = ", HiJsonUtil.e(mpxVar2));
            }
        });
    }

    private void judgeCommonUsedDevices(final mpv mpvVar, final a aVar, final int i) {
        mpq.d().getCommonUsedDevices("FAMILY_SPACE", new HttpDataCallback() { // from class: com.huawei.pluginhealthzone.interactors.HealthZonePushReceiver.3
            @Override // com.huawei.pluginhealthzone.cloud.HttpDataCallback
            public void onFailure(int i2, String str) {
                LogUtil.b(HealthZonePushReceiver.TAG, "judgeCommonUsedDevices, onFailure errorCode: ", Integer.valueOf(i2), ", errorInfo: ", str);
            }

            @Override // com.huawei.pluginhealthzone.cloud.HttpDataCallback
            public void onSuccess(JSONObject jSONObject) {
                LogUtil.a(HealthZonePushReceiver.TAG, "judgeCommonUsedDevices, onSuccess");
                if (jSONObject != null) {
                    if (HealthZonePushReceiver.this.isWatchOnCommonList(jSONObject)) {
                        HealthZonePushReceiver.this.sendMsgBodyToDevice(mpvVar, aVar, i);
                        return;
                    }
                    return;
                }
                LogUtil.h(HealthZonePushReceiver.TAG, "judgeCommonUsedDevices, data is null");
            }
        });
    }

    private mpv setCommonParameter(mpv mpvVar, a aVar, int i) {
        mpvVar.c(i);
        mpvVar.a(aVar.d().longValue());
        mpvVar.d(aVar.f());
        mpvVar.b(aVar.c());
        mpvVar.c(aVar.e());
        mpvVar.a(aVar.g());
        mpvVar.h(aVar.h());
        mpvVar.b(aVar.b());
        return mpvVar;
    }

    private boolean isGpsEnable() {
        LocationManager xA_ = CommonUtils.xA_();
        return xA_ != null && xA_.isProviderEnabled(GeocodeSearch.GPS);
    }

    private void uploadLocation(final Context context, final boolean z) {
        if (isGpsEnable()) {
            mqh.b().a(new LocationMgrCallback() { // from class: mqd
                @Override // com.huawei.pluginhealthzone.interactors.LocationMgrCallback
                public final void onLocationChanged(mqp mqpVar) {
                    HealthZonePushReceiver.this.m771x529f3a1f(context, z, mqpVar);
                }
            });
        }
    }

    /* renamed from: lambda$uploadLocation$2$com-huawei-pluginhealthzone-interactors-HealthZonePushReceiver, reason: not valid java name */
    public /* synthetic */ void m771x529f3a1f(Context context, boolean z, mqp mqpVar) {
        DeviceInfo a2;
        mqpVar.a(1);
        mqpVar.c(Long.valueOf(System.currentTimeMillis()));
        String androidId = FoundationCommonUtil.getAndroidId(context);
        CloudApi d = mpq.d();
        d.uploadPosition(androidId, mqpVar, 0, new HttpDataCallback() { // from class: com.huawei.pluginhealthzone.interactors.HealthZonePushReceiver.4
            @Override // com.huawei.pluginhealthzone.cloud.HttpDataCallback
            public void onFailure(int i, String str) {
                LogUtil.h(HealthZonePushReceiver.TAG, "onFailure(phone), errorCode:", Integer.valueOf(i), "errorInfo:", str);
            }

            @Override // com.huawei.pluginhealthzone.cloud.HttpDataCallback
            public void onSuccess(JSONObject jSONObject) {
                LogUtil.a(HealthZonePushReceiver.TAG, "uploadPosition onSuccess: phone");
            }
        });
        if (z && (a2 = jpt.a(TAG)) != null && a2.getDeviceConnectState() == 2) {
            String str = a2.getSecurityUuid() + "#ANDROID21";
            LogUtil.a(TAG, "deviceUuid = ", str);
            d.uploadPosition(str, mqpVar, 0, new HttpDataCallback() { // from class: com.huawei.pluginhealthzone.interactors.HealthZonePushReceiver.8
                @Override // com.huawei.pluginhealthzone.cloud.HttpDataCallback
                public void onFailure(int i, String str2) {
                    LogUtil.h(HealthZonePushReceiver.TAG, "onFailure(watch), errorCode:", Integer.valueOf(i), "errorInfo:", str2);
                }

                @Override // com.huawei.pluginhealthzone.cloud.HttpDataCallback
                public void onSuccess(JSONObject jSONObject) {
                    LogUtil.a(HealthZonePushReceiver.TAG, "uploadPosition onSuccess: watch");
                }
            });
        }
    }

    private boolean isFollowNotification(String str) {
        return new ArrayList(Arrays.asList("16", "17", "18", "19", DEAUTH_EVENT_NOTIFY)).contains(str);
    }

    private void setFollowNotification(Context context, e eVar, e.d dVar, Notification.Builder builder, Intent intent) {
        if (!isSetNotifyContent(context, eVar, dVar, builder, intent)) {
            LogUtil.h(TAG, "Invalid notify");
            return;
        }
        intent.setFlags(335544320);
        builder.setAutoCancel(true).setContentIntent(PendingIntent.getActivity(context, sFollowNotificationId, intent, 201326592));
        synchronized (LOCK) {
            jdh.c().xh_(sFollowNotificationId, builder.build());
            sFollowNotificationId++;
        }
    }

    private boolean setFollowRequestNotifyContent(Context context, e.d dVar, Notification.Builder builder, Intent intent, String str) {
        String string;
        builder.setContentTitle(context.getResources().getString(R$string.IDS_hwh_family_health_zone));
        if (dVar.e().equals("1")) {
            string = context.getResources().getString(R.string._2130840386_res_0x7f020b42, str);
        } else {
            string = context.getResources().getString(R.string._2130840383_res_0x7f020b3f, str);
        }
        builder.setContentText(string);
        intent.setClass(context, FamilyHealthTempActivity.class);
        return true;
    }

    private boolean setFollowAcceptNotifyContent(Context context, e.d dVar, Notification.Builder builder, Intent intent, String str) {
        String string;
        builder.setContentTitle(context.getResources().getString(R$string.IDS_hwh_family_health_zone));
        if (dVar.e().equals("1")) {
            string = context.getResources().getString(R.string._2130840387_res_0x7f020b43, str);
        } else {
            string = context.getResources().getString(R.string._2130840384_res_0x7f020b40, str);
        }
        builder.setContentText(string);
        intent.setClass(context, FamilyHealthTempActivity.class);
        return true;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    private boolean isSetNotifyContent(Context context, e eVar, e.d dVar, Notification.Builder builder, Intent intent) {
        char c;
        String m = CommonUtil.m(dVar.b());
        intent.putExtra("pushType", eVar.d());
        String d = eVar.d();
        d.hashCode();
        int hashCode = d.hashCode();
        if (hashCode != 1598) {
            switch (hashCode) {
                case 1573:
                    if (d.equals("16")) {
                        c = 0;
                        break;
                    }
                    c = 65535;
                    break;
                case 1574:
                    if (d.equals("17")) {
                        c = 1;
                        break;
                    }
                    c = 65535;
                    break;
                case 1575:
                    if (d.equals("18")) {
                        c = 2;
                        break;
                    }
                    c = 65535;
                    break;
                case 1576:
                    if (d.equals("19")) {
                        c = 3;
                        break;
                    }
                    c = 65535;
                    break;
                default:
                    c = 65535;
                    break;
            }
        } else {
            if (d.equals(DEAUTH_EVENT_NOTIFY)) {
                c = 4;
            }
            c = 65535;
        }
        if (c == 0) {
            return setFollowRequestNotifyContent(context, dVar, builder, intent, m);
        }
        if (c == 1) {
            return setFollowAcceptNotifyContent(context, dVar, builder, intent, m);
        }
        if (c == 2) {
            builder.setContentTitle(context.getResources().getString(R$string.IDS_hwh_family_health_zone));
            builder.setContentText(context.getResources().getString(R.string._2130840385_res_0x7f020b41, m));
            intent.setClass(context, FamilyHealthTempActivity.class);
            return true;
        }
        if (c == 3) {
            builder.setContentTitle(context.getResources().getString(R$string.IDS_hwh_family_health_zone));
            builder.setContentText(context.getResources().getString(R.string._2130840361_res_0x7f020b29, m));
            intent.setClass(context, FamilyHealthTempActivity.class);
            return true;
        }
        if (c == 4) {
            builder.setContentTitle(context.getResources().getString(R$string.IDS_hwh_family_health_zone));
            builder.setContentText(context.getResources().getString(R.string._2130840362_res_0x7f020b2a, m));
            intent.setClass(context, FamilyHealthTempActivity.class);
            return true;
        }
        LogUtil.h(TAG, "Unexpected push type");
        return false;
    }

    private void setHealthNotification(Context context, e eVar, Notification.Builder builder, Intent intent) {
        String d;
        String b;
        intent.setClass(context, FamilyHealthTempActivity.class);
        if (eVar.d().equals("5002")) {
            e.b bVar = (e.b) new Gson().fromJson(CommonUtil.z(eVar.b()), e.b.class);
            d = bVar.b();
            b = bVar.e();
            if (isProactiveNotification(d)) {
                pushListenerCallback(d, HiJsonUtil.e(eVar));
                intent.putExtra("pushType", d);
                sendCommentFailureNotification(bVar, builder, context, intent);
                return;
            }
        } else {
            d = eVar.d();
            b = eVar.b();
        }
        String str = d;
        pushListenerCallback(str, HiJsonUtil.e(eVar));
        intent.putExtra("pushType", str);
        getPushInformationByNotifyTime(context, builder, intent, b, str);
    }

    private boolean isProactiveNotification(String str) {
        return PROACTIVE_NOTIFICATION_PUSH_LIST.contains(str);
    }

    private void getPushInformationByNotifyTime(final Context context, final Notification.Builder builder, final Intent intent, String str, String str2) {
        try {
            mpq.d().getPushInformationByNotifyTime(Long.parseLong(str), Integer.parseInt(str2), new HttpDataCallback() { // from class: com.huawei.pluginhealthzone.interactors.HealthZonePushReceiver.9
                @Override // com.huawei.pluginhealthzone.cloud.HttpDataCallback
                public void onFailure(int i, String str3) {
                    LogUtil.b(HealthZonePushReceiver.TAG, "getPushInformationByNotifyTime onFailure errorCode:", Integer.valueOf(i));
                }

                @Override // com.huawei.pluginhealthzone.cloud.HttpDataCallback
                public void onSuccess(JSONObject jSONObject) {
                    try {
                        if (jSONObject.has("resultCode") && jSONObject.getInt("resultCode") == 0) {
                            mqj.c(jSONObject, new AbnormalDataInformationCallback() { // from class: com.huawei.pluginhealthzone.interactors.HealthZonePushReceiver.9.5
                                @Override // com.huawei.pluginhealthzone.interactors.AbnormalDataInformationCallback
                                public void onFailure(int i, String str3) {
                                }

                                @Override // com.huawei.pluginhealthzone.interactors.AbnormalDataInformationCallback
                                public void onSuccess(List<mqc> list) {
                                    if (list == null) {
                                        LogUtil.h(HealthZonePushReceiver.TAG, "abnormalDataInformation is null");
                                        return;
                                    }
                                    builder.setContentTitle(context.getResources().getString(R$string.IDS_hwh_family_health_zone));
                                    Iterator<mqc> it = list.iterator();
                                    while (it.hasNext()) {
                                        HealthZonePushReceiver.this.sendNotificationForEachUser(it.next(), builder, context, intent);
                                    }
                                }
                            });
                        }
                    } catch (JSONException e2) {
                        LogUtil.b(HealthZonePushReceiver.TAG, "getPushInformationByNotifyTime onSuccess:", LogAnonymous.b((Throwable) e2));
                    }
                }
            });
        } catch (NumberFormatException e2) {
            LogUtil.b(TAG, "setHealthNotification:", e2.getMessage());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x0128, code lost:
    
        com.huawei.pluginhealthzone.interactors.HealthZonePushReceiver.sNotifyMap.put(r5, java.lang.Long.valueOf(r0));
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void sendNotificationForEachUser(defpackage.mqc r11, android.app.Notification.Builder r12, android.content.Context r13, android.content.Intent r14) {
        /*
            Method dump skipped, instructions count: 364
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.pluginhealthzone.interactors.HealthZonePushReceiver.sendNotificationForEachUser(mqc, android.app.Notification$Builder, android.content.Context, android.content.Intent):void");
    }

    private int getPushTypeFromRecords(mqc mqcVar, int i) {
        try {
            return mqcVar.cmW_().keyAt(i);
        } catch (ArrayIndexOutOfBoundsException | NullPointerException unused) {
            LogUtil.b(TAG, "get pushType exception");
            return 0;
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:3:0x0017, code lost:
    
        switch(r2) {
            case 1660: goto L18;
            case 1661: goto L14;
            case 1662: goto L10;
            case 1663: goto L6;
            default: goto L32;
        };
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void sendCommentFailureNotification(com.huawei.pluginhealthzone.interactors.HealthZonePushReceiver.e.b r6, android.app.Notification.Builder r7, android.content.Context r8, android.content.Intent r9) {
        /*
            Method dump skipped, instructions count: 378
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.pluginhealthzone.interactors.HealthZonePushReceiver.sendCommentFailureNotification(com.huawei.pluginhealthzone.interactors.HealthZonePushReceiver$e$b, android.app.Notification$Builder, android.content.Context, android.content.Intent):void");
    }

    public static void setPushMessageListener(PushMessageListener pushMessageListener) {
        sPushMessageListener = pushMessageListener;
    }

    public static PushMessageListener getPushMessageListener() {
        return sPushMessageListener;
    }

    public static HealthZonePushButtonReceiver getHealthZonePushButtonReceiver() {
        return healthZonePushButtonReceiver;
    }

    /* loaded from: classes6.dex */
    static class e {

        @SerializedName(com.huawei.health.messagecenter.model.CommonUtil.MSG_CONTENT)
        private String c;

        @SerializedName("pushType")
        private String d = "";

        @SerializedName("pushId")
        private String e = "";

        private e() {
        }

        static class d {

            @SerializedName("memberFlag")
            private String d = "";

            @SerializedName("userAccount")
            private String e = "";

            private d() {
            }

            public String b() {
                return this.e;
            }

            public String e() {
                return this.d;
            }
        }

        static class b {

            @SerializedName("postId")
            private String b;

            @SerializedName("postStatus")
            private String c;

            @SerializedName("postCreateTime")
            private long e;

            /* renamed from: a, reason: collision with root package name */
            @SerializedName("notifyTime")
            private String f8486a = "";

            @SerializedName(HealthZonePushReceiver.DETAIL_TYPE)
            private String d = "";

            @SerializedName("userAccount")
            private String i = "";

            private b() {
            }

            public String d() {
                return this.i;
            }

            public String e() {
                return this.f8486a;
            }

            public String b() {
                return this.d;
            }

            public long a() {
                return this.e;
            }

            public String c() {
                return this.c;
            }
        }

        public String d() {
            return this.d;
        }

        public String b() {
            return this.c;
        }

        public String toString() {
            return "HealthZoneMsgPushBean{, pushType ='" + this.d + "', pushId ='" + this.e + "'}";
        }
    }

    /* loaded from: classes6.dex */
    public static class a {

        /* renamed from: a, reason: collision with root package name */
        @SerializedName(HealthZonePushReceiver.MEMBER_HUID)
        private Long f8485a;

        @SerializedName("huid")
        private Long b;

        @SerializedName("email")
        private String c;

        @SerializedName("isStart")
        private Integer d;

        @SerializedName("headPictureURL")
        private String e;

        @SerializedName(JsNetwork.NetworkType.MOBILE)
        private String f;

        @SerializedName("pushId")
        private String g;

        @SerializedName("pushSubType")
        private Integer h;

        @SerializedName("pushType")
        private Integer i;

        @SerializedName("requestFlag")
        private String j;

        @SerializedName("userNickName")
        private String m;

        @SerializedName("traceId")
        private String o;

        private a() {
        }

        public Long d() {
            return this.b;
        }

        public Long b() {
            return this.f8485a;
        }

        public Integer j() {
            return this.h;
        }

        public Integer a() {
            return this.d;
        }

        public String f() {
            return this.m;
        }

        public String c() {
            return this.e;
        }

        public String g() {
            return this.f;
        }

        public String e() {
            return this.c;
        }

        public String h() {
            return this.j;
        }

        public void c(int i) {
            this.d = Integer.valueOf(i);
        }
    }
}
