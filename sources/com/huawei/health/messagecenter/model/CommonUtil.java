package com.huawei.health.messagecenter.model;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import com.huawei.health.devicemgr.api.mainprocess.DownloadManagerApi;
import com.huawei.hmf.tasks.OnCompleteListener;
import com.huawei.hmf.tasks.Task;
import com.huawei.hms.push.HmsMessaging;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.login.ui.login.LoginInit;
import defpackage.cvc;
import defpackage.cvj;
import defpackage.jdh;
import defpackage.nsn;
import health.compact.a.Services;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;
import health.compact.a.Utils;
import health.compact.a.util.LogUtil;
import java.util.HashMap;

/* loaded from: classes3.dex */
public class CommonUtil {
    public static final String BLOOD_PRESSURE_INFO = "IC5";
    public static final String BLOOD_SUGAR_INFO = "IC6";
    private static final String BROWSE_URL_SUFFIX = "Anon";
    public static final String CLOUD_SERVICE_MESSAGE_VALUE = "specialCloudServiceMessage";
    public static final String COUNTRY_CODE = "countryCode";
    public static final String CREATE_TIME = "createTime";
    public static final String DETAIL_URI = "detailUri";
    public static final String DETAIL_URI_EXTERNAL = "detailUriExt";
    public static final String EQUAL = " =? ";
    public static final String EQUAL_END = " =? and ";
    public static final String EQUAL_OR = " =? or ";
    public static final String EXPIRE_TIME = "expireTime";
    public static final String FLAG = "flag";
    public static final String HARMONY_IMAGE_SIZE = "harmonyImageSize";
    public static final String HARMONY_IMAGE_URL = "harmonyImageUrl";
    public static final String HEALTH_INFO = "IC1";
    public static final String HEAT_MAP_CITY = "heatMapCity";
    public static final String HUID = "huid";
    private static final String HW_A1 = "HW_A1_PLUS";
    private static final String HW_B0 = "HW_B0";
    private static final String HW_B2 = "HW_B2";
    private static final String HW_B3 = "HW_B3";
    private static final String HW_CRIUS = "HW_CRIUS";
    private static final String HW_ERIS = "HW_ERIS";
    private static final String HW_FORTUNA = "HW_FORTUNA";
    private static final String HW_GRUS = "HW_GRUS";
    private static final String HW_JANUS = "HW_JANUS";
    private static final String HW_K1 = "HW_K1";
    private static final String HW_NYX = "HW_NYX";
    private static final String HW_R1 = "HW_R1";
    private static final String HW_S1 = "HW_S1";
    private static final String HW_TALOS = "HW_TALOS";
    private static final String HW_TERRA = "HW_TERRA";
    private static final String HW_W1 = "HW_W1";
    private static final String HW_WATCH2 = "HW_WATCH2";
    public static final String ID = "id";
    public static final String IMAGE_TEXT_SEPARATE_SWITCH = "imageTextSeparateSwitch";
    public static final String IMEI = "imei";
    public static final String IMG_BIG_URI = "imgBigUri";
    public static final String IMG_URI = "imgURI";
    public static final String INFO_CLASSIFY = "infoClassify";
    public static final String INFO_RECOMMEND = "infoRecommend";
    public static final String KEY_CURRENT_DEVICE_ID = "currentDeviceId_key";
    public static final String KEY_LAST_HUID = "currentHuid_key";
    public static final String KEY_LAST_LANGUAGE = "lastLanguage_key";
    public static final String KEY_PUSH_HUID = "lastHuid_key";
    public static final String LAYOUT = "layout";
    private static final int MAP_INIT_SIZE = 17;
    public static final String MESSAGE_CENTER_GET_MESSAGE = "/messageCenter/getMessages";
    public static final String MESSAGE_CENTER_SAVE_TOKEN = "/messageCenter/savePushToken";
    public static final String MESSAGE_EXTERNAL_LIST = "messageExtList";
    public static final String MESSAGE_MODULE_AD = "2";
    public static final String METADATA = "metadata";
    public static final String MODULE = "module";
    public static final String MONTH_REPORT_MESSAGE = "monthReportMessage";
    public static final String MSG_CONTENT = "msgContext";
    public static final String MSG_FROM = "msgFrom";
    public static final String MSG_ID = "msgId";
    public static final String MSG_NOTIFY = "notified";
    public static final String MSG_POSITION = "msgPosition";
    public static final String MSG_TITLE = "msgTitle";
    public static final String MSG_TYPE = "msgType";
    public static final int MSG_TYPE_MSG_POSITION_ADVERTISEMENT_BANNER = 12;
    public static final int MSG_TYPE_MSG_POSITION_ADVERTISEMENT_BANNER_EMUI = 29;
    public static final int MSG_TYPE_MSG_POSITION_CONFIGURE_PAGE = 41;
    public static final int MSG_TYPE_MSG_POSITION_FAQ_MESSAGE = 31;
    public static final int MSG_TYPE_MSG_POSITION_HEALTH_DEVICE_BANNER = 24;
    public static final int MSG_TYPE_MSG_POSITION_HOME_DIALOG_MESSAGE = 26;
    public static final int MSG_TYPE_MSG_POSITION_HOME_PAGE = 21;
    public static final int MSG_TYPE_MSG_POSITION_INFORMATION_MESSAGE = 25;
    public static final int MSG_TYPE_MSG_POSITION_MESSAGE_LIST = 11;
    public static final int MSG_TYPE_MSG_POSITION_OPERATION_PROMOTION = 30;
    public static final int MSG_TYPE_MSG_POSITION_SPORT_BANNER_PAGE = 42;
    public static final int MSG_TYPE_MSG_POSITION_UNMISSABLE_TOPIC = 28;
    public static final int MSG_TYPE_MSG_POSITION_WONDERFUL_EVENT = 27;
    public static final int MSG_TYPE_READ_NOT_SHOW = 2;
    public static final int MSG_TYPE_READ_TOMORROW_NOT_SHOW = 3;
    public static final String MSG_USER_LABEL = "msgUserLable";
    private static final int NOTIFICATION_ID = 20220304;
    public static final int NOTIFICATION_MESSAGE_LIST_MESSAGE_POSITION = 3;
    public static final int NOTIFICATION_MESSAGE_POSITION = 2;
    public static final String PAGE_TYPE = "pageType";
    public static final String PARAM_APP_ID = "appId";
    public static final String PARAM_APP_TYPE = "appType";
    public static final String PARAM_BIND_DEVICE_TYPE = "bindDeviceType";
    public static final String PARAM_DEVICE_ID = "deviceId";
    public static final String PARAM_DEVICE_TYPE = "deviceType";
    public static final String PARAM_ENVIRONMENT = "environment";
    public static final String PARAM_HEALTH_TYPE = "healthType";
    public static final String PARAM_INTERFACE_VERSION = "iVersion";
    public static final String PARAM_LANGUAGE = "language";
    public static final String PARAM_PHONE_TYPE = "phoneType";
    public static final String PARAM_PUSH_TOKEN = "pushToken";
    public static final String PARAM_PUSH_TYPE = "pushType";
    public static final String PARAM_RECORD_NUMBER = "recordNumber";
    public static final String PARAM_SERVICE_TOKEN = "token";
    public static final String PARAM_SN = "sn";
    public static final String PARAM_SYSTEM_VERSION = "sysVersion";
    public static final String PARAM_TIMESTAMP = "timestamp";
    public static final String PARAM_TOKEN_TYPE = "tokenType";
    public static final String PARAM_TS = "ts";
    public static final String PARAM_UP_DEVICE_TYPE = "upDeviceType";
    public static final String PARAM_VERSION = "version";
    public static final String PARAM_WEAR_TYPE = "wearType";
    public static final String POSITION = "position";
    public static final String PUSH_TOKEN = "push_token_key";
    public static final String READ_FLAG = "readFlag";
    public static final String RECEIVE_TIME = "receiveTime";
    public static final String RESERVE_PARAM_ONE = "reserveParamOne";
    public static final String RESERVE_PARAM_THREE = "reserveParamThree";
    public static final String RESERVE_PARAM_TWO = "reserveParamTwo";
    public static final String RIDE_INFO = "IC3";
    public static final String RUN_INFO = "IC2";
    public static final String SITE_ID = "siteId";
    public static final String SLEEP_INFO = "IC7";
    public static final String STRESS_INFO = "IC8";
    public static final String TABLE_NAME = "message";
    public static final String TAG = "CommonUtil";
    public static final String TYPE = "type";
    public static final String USER_LABEL_STORAGE_KEY = "_userLabel_key";
    public static final String WALK_INFO = "IC10";
    public static final String WEEK_REPORT_MESSAGE = "weekReportMessage";
    public static final String WEIGHT = "weight";
    public static final String WEIGHT_INFO = "IC4";
    private static HashMap<String, String> wearTypes = new HashMap<>(17);

    private CommonUtil() {
    }

    public static void initWearType() {
        HashMap<String, String> hashMap = new HashMap<>(17);
        wearTypes = hashMap;
        hashMap.clear();
        wearTypes.put(String.valueOf(5), HW_B0);
        wearTypes.put(String.valueOf(1), HW_B2);
        wearTypes.put(String.valueOf(7), HW_B3);
        wearTypes.put(String.valueOf(2), HW_K1);
        wearTypes.put(String.valueOf(3), HW_W1);
        wearTypes.put(String.valueOf(10), HW_WATCH2);
        wearTypes.put(String.valueOf(8), HW_S1);
        wearTypes.put(String.valueOf(13), HW_NYX);
        wearTypes.put(String.valueOf(12), HW_A1);
        wearTypes.put(String.valueOf(11), HW_R1);
        wearTypes.put(String.valueOf(14), HW_GRUS);
        wearTypes.put(String.valueOf(15), HW_ERIS);
        wearTypes.put(String.valueOf(16), HW_JANUS);
        wearTypes.put(String.valueOf(18), HW_CRIUS);
        wearTypes.put(String.valueOf(19), HW_TERRA);
        wearTypes.put(String.valueOf(20), HW_TALOS);
        wearTypes.put(String.valueOf(21), HW_FORTUNA);
    }

    public static String getWearType(String str, String str2) {
        cvc pluginInfoByDeviceType;
        cvj f;
        LogUtil.d(TAG, "keyString:", str, "moduleName:", str2);
        if (str == null) {
            return "";
        }
        try {
            if (Integer.parseInt(str) >= 34 && !TextUtils.isEmpty(str2)) {
                return str2;
            }
        } catch (NumberFormatException unused) {
            LogUtil.e(TAG, "NumberFormatException");
        }
        HashMap<String, String> hashMap = wearTypes;
        String str3 = (hashMap == null || !hashMap.containsKey(str)) ? "" : wearTypes.get(str);
        try {
            return (!"".equals(str3) || (pluginInfoByDeviceType = ((DownloadManagerApi) Services.c("DownloadDeviceResource", DownloadManagerApi.class)).getPluginInfoByDeviceType(Integer.parseInt(str))) == null || (f = pluginInfoByDeviceType.f()) == null) ? str3 : f.bq();
        } catch (NumberFormatException e) {
            LogUtil.d(TAG, "exception: " + e.getMessage());
            return str3;
        }
    }

    public static boolean isRecommendSwitchOpen(Context context) {
        if (Utils.o()) {
            return true;
        }
        return "1".equals(SharedPreferenceManager.b(context, Integer.toString(10000), "health_product_recommend"));
    }

    public static boolean isSystemBarNoticeSwitchOnOrDefault(Context context) {
        String b = SharedPreferenceManager.b(context, Integer.toString(10000), "health_msg_switch_noticebar");
        if (TextUtils.isEmpty(b)) {
            boolean z = !Utils.o();
            com.huawei.hwlogsmodel.LogUtil.a(TAG, "isSystemBarNoticeSwitchOnOrDefault default:", Boolean.valueOf(z));
            setPushSwitch(context, z);
            return z;
        }
        return "1".equals(b);
    }

    public static void setPushSwitch(Context context, boolean z) {
        String str = z ? "1" : "0";
        com.huawei.hwlogsmodel.LogUtil.a(TAG, "setPushSwitch:", str);
        SharedPreferenceManager.e(context, Integer.toString(10000), "health_msg_switch_noticebar", str, new StorageParams());
        updatePushWithHmsIfDeeded(context, z);
    }

    private static void updatePushWithHmsIfDeeded(Context context, boolean z) {
        if (!health.compact.a.CommonUtil.cj() || health.compact.a.CommonUtil.cc()) {
            return;
        }
        com.huawei.hwlogsmodel.LogUtil.a(TAG, "updatePushWithHmsIfDeeded");
        switchPushWithHms(context, z, new OnCompleteListener<Void>() { // from class: com.huawei.health.messagecenter.model.CommonUtil.1
            @Override // com.huawei.hmf.tasks.OnCompleteListener
            public void onComplete(Task<Void> task) {
                if (task.isSuccessful()) {
                    com.huawei.hwlogsmodel.LogUtil.a(CommonUtil.TAG, "updatePushWithHmsIfDeeded success");
                } else {
                    com.huawei.hwlogsmodel.LogUtil.b(CommonUtil.TAG, "updatePushWithHmsIfDeeded fail:", task.getException().getMessage());
                }
            }
        });
    }

    private static void switchPushWithHms(Context context, boolean z, OnCompleteListener<Void> onCompleteListener) {
        if (z) {
            HmsMessaging.getInstance(context).turnOnPush().addOnCompleteListener(onCompleteListener);
        } else {
            HmsMessaging.getInstance(context).turnOffPush().addOnCompleteListener(onCompleteListener);
        }
    }

    public static String getUrlSuffix() {
        return LoginInit.getInstance(BaseApplication.getContext()).isBrowseMode() ? BROWSE_URL_SUFFIX : "";
    }

    public static String newPathConcat(String str, String str2, String str3) {
        if (TextUtils.isEmpty(str)) {
            com.huawei.hwlogsmodel.LogUtil.h(TAG, "newPathConcat oldUrl is empty");
            return str;
        }
        StringBuilder sb = new StringBuilder(str);
        if (str.contains("?")) {
            sb.append("&");
        } else {
            sb.append("?");
        }
        sb.append(str2);
        sb.append("=");
        sb.append(str3);
        com.huawei.hwlogsmodel.LogUtil.c(TAG, "newPathConcat newUrl is ", sb.toString());
        return sb.toString();
    }

    public static void setNotification(Context context, String str, String str2, String str3) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2) || TextUtils.isEmpty(str3)) {
            LogUtil.c(TAG, "title or content or uriStr is empty");
            return;
        }
        jdh.c().a(NOTIFICATION_ID);
        Notification.Builder xf_ = jdh.c().xf_();
        nsn.cLB_(xf_);
        xf_.setContentTitle(str).setContentText(str2).setStyle(new Notification.BigTextStyle().bigText(str2)).setAutoCancel(true).setGroupSummary(true).setContentIntent(getDefaultIntent(context, str3)).setOnlyAlertOnce(true);
        jdh.c().xh_(NOTIFICATION_ID, xf_.build());
    }

    private static PendingIntent getDefaultIntent(Context context, String str) {
        Intent intent = new Intent();
        intent.setClassName(context, "com.huawei.health.browseraction.HwHealthBrowserActionActivity");
        intent.setFlags(335544320);
        intent.setData(Uri.parse(str));
        return PendingIntent.getActivity(BaseApplication.getContext(), NOTIFICATION_ID, intent, 201326592);
    }
}
