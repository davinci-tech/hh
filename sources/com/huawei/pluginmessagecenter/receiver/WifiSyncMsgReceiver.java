package com.huawei.pluginmessagecenter.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.text.TextUtils;
import androidx.core.app.NotificationManagerCompat;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.annotations.SerializedName;
import com.huawei.health.R;
import com.huawei.health.messagecenter.model.IpushBase;
import com.huawei.health.messagecenter.model.MessageConstant;
import com.huawei.health.messagecenter.model.MessageObject;
import com.huawei.hihealth.HiAggregateOption;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.HiSyncOption;
import com.huawei.hihealth.api.HiHealthManager;
import com.huawei.hihealth.api.HiHealthNativeApi;
import com.huawei.hihealth.data.listener.HiAggregateListener;
import com.huawei.hihealth.data.type.HiSyncType;
import com.huawei.hwcloudmodel.healthdatacloud.model.GetBindDeviceReq;
import com.huawei.hwcloudmodel.model.userprofile.DeviceInfo;
import com.huawei.hwcloudmodel.model.userprofile.GetBindDeviceRsp;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.ble.BleConstants;
import com.huawei.pluginmessagecenter.manager.MCNotificationManager;
import defpackage.cei;
import defpackage.jbs;
import defpackage.jdx;
import defpackage.jec;
import defpackage.koq;
import health.compact.a.BroadcastManagerUtil;
import health.compact.a.CommonUtil;
import health.compact.a.HiDateUtil;
import health.compact.a.LocalBroadcast;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.atomic.AtomicInteger;

/* loaded from: classes6.dex */
public class WifiSyncMsgReceiver implements IpushBase {
    private static final int AGGREGATE_COUNT_ONE = 1;
    private static final int CLOUD_ERROR_CODE_SUCCESS = 0;
    private static final int MAX_RETRY_TIMES = 2;
    private static final String MSG_ID_WIFI = "9111";
    private static final long ONE_DAY_MILLISECONDS = 86400000;
    private static final String PUSH_MESSAGE_CLASHES = "-1";
    private static final String PUSH_MESSAGE_MAIN = "0";
    private static final String PUSH_STATUS_REGISTER = "1";
    private static final String PUSH_STATUS_UNREGISTER = "-1";
    private static final String TAG = "Debug_WifiSyncMsgReceiver";
    private static final int WEIGHT_STICK = 1;
    private static final int WIFI_FLAG = 0;
    private static final String WIFI_MODULE = "WIFI_NOTIFICATION_MODULE";
    private static final int WIFI_NOTIFIED = 0;
    private static final int WIFI_SYNC_PUSH_TYPE = 9;
    private static boolean sIsConflict;
    private static BroadcastReceiver sPushDataDoneReceiver;
    private int mRetryTimes = 0;
    private static AtomicInteger sRegisterNum = new AtomicInteger(0);
    private static final Object sLock = new Object();

    public static boolean getLastConflictFlag() {
        return sIsConflict;
    }

    private void retrySyncCloud(Context context) {
        int i = this.mRetryTimes;
        if (i < 2) {
            triggerSyncCloud(context);
            int i2 = this.mRetryTimes + 1;
            this.mRetryTimes = i2;
            LogUtil.a(TAG, "sync cloud fail and retry, times: ", Integer.valueOf(i2), ", maxTimes: ", 2);
            return;
        }
        LogUtil.h(TAG, "max retry time reach, times: ", Integer.valueOf(i), ", maxTimes: ", 2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleAction(final Context context, Intent intent) {
        if ("com.huawei.health.action.ACTION_PUSH_DATA_DONE_ACTION".equals(intent.getAction())) {
            LogUtil.a(TAG, "after sync data, time: ", jec.c(new Date()));
            jdx.b(new Runnable() { // from class: com.huawei.pluginmessagecenter.receiver.WifiSyncMsgReceiver.1
                @Override // java.lang.Runnable
                public void run() {
                    WifiSyncMsgReceiver.this.getAllWifiWeightData(context);
                }
            });
            unRegisterPushDataSyncDone(context);
            this.mRetryTimes = 0;
            return;
        }
        if ("com.huawei.hihealth.action_download_data_result".equals(intent.getAction())) {
            int intExtra = intent.getIntExtra("com.huawei.hihealth.action_download_data_result_code", 0);
            if (intExtra == -1) {
                retrySyncCloud(context);
            }
            LogUtil.a(TAG, "download data retry sync cloud, result: ", Integer.valueOf(intExtra));
            return;
        }
        LogUtil.h(TAG, "Unknown action: ", intent.getAction());
    }

    private void registerPushDataSyncDone(final Context context) {
        synchronized (sLock) {
            if (sPushDataDoneReceiver == null) {
                sPushDataDoneReceiver = new BroadcastReceiver() { // from class: com.huawei.pluginmessagecenter.receiver.WifiSyncMsgReceiver.2
                    @Override // android.content.BroadcastReceiver
                    public void onReceive(Context context2, Intent intent) {
                        if (intent != null) {
                            WifiSyncMsgReceiver.this.handleAction(context, intent);
                        }
                    }
                };
                IntentFilter intentFilter = new IntentFilter("com.huawei.health.action.ACTION_PUSH_DATA_DONE_ACTION");
                intentFilter.addAction("com.huawei.health.action.ACTION_PUSH_DATA_DONE_ACTION");
                intentFilter.addAction("com.huawei.hihealth.action_download_data_result");
                BroadcastManagerUtil.bFA_(context, sPushDataDoneReceiver, intentFilter, LocalBroadcast.c, null);
                sRegisterNum.set(0);
            }
            SharedPreferenceManager.e(BaseApplication.getContext(), "wifi_weight_device", "wifi_push_regist_key", "1", (StorageParams) null);
            sRegisterNum.incrementAndGet();
            LogUtil.a(TAG, "registerPushDataSyncDone, mRegisterNum: ", Integer.valueOf(sRegisterNum.get()));
        }
    }

    private void unRegisterPushDataSyncDone(Context context) {
        synchronized (sLock) {
            if (sRegisterNum.decrementAndGet() <= 0) {
                try {
                    context.unregisterReceiver(sPushDataDoneReceiver);
                } catch (IllegalArgumentException unused) {
                    LogUtil.h(TAG, "unRegisterSyncDataDone occur IllegalArgumentException.");
                }
                sPushDataDoneReceiver = null;
                SharedPreferenceManager.e(BaseApplication.getContext(), "wifi_weight_device", "wifi_push_regist_key", "-1", (StorageParams) null);
            }
            LogUtil.a(TAG, "enter unRegisterPushDataSyncDone, mRegisterNum: ", Integer.valueOf(sRegisterNum.get()));
        }
    }

    private void pushMessageData(Context context, PushBean pushBean) {
        boolean equals = pushBean.mScaleUid.equals("-1");
        sIsConflict = equals;
        LogUtil.a(TAG, "start data notification isConflict: ", Boolean.valueOf(equals));
        BaseApplication.getContext().getSharedPreferences(Integer.toString(10000), 0).edit().putBoolean(MessageConstant.KEY_PUSH_CONFLICT_FLAG, sIsConflict).apply();
        if (sIsConflict) {
            return;
        }
        boolean areNotificationsEnabled = NotificationManagerCompat.from(BaseApplication.getContext()).areNotificationsEnabled();
        LogUtil.a(TAG, "pushMessageData enabledNotify:", Boolean.valueOf(areNotificationsEnabled));
        if (!CommonUtil.bh() && !areNotificationsEnabled) {
            LogUtil.h(TAG, "pushMessageData notification bar permission is not enabled.");
            return;
        }
        String string = context.getString(R.string.IDS_device_wifi_notification_weight_manager);
        String string2 = context.getString(R.string.IDS_device_wifi_notification_weight_isConflict);
        SharedPreferenceManager.e(BaseApplication.getContext(), "wifi_weight_device", "wifi_push_weight_SAVE_UUID", pushBean.mScaleUid, new StorageParams(1));
        if (!pushBean.mScaleUid.equals("0")) {
            String userName = cei.b().getUserName(pushBean.mScaleUid);
            if (!TextUtils.isEmpty(userName)) {
                string2 = String.format(Locale.ENGLISH, context.getString(R.string.IDS_device_wifi_notification_weight_nickname), userName);
            }
        }
        MessageObject messageObject = new MessageObject();
        messageObject.setFlag(0);
        messageObject.setExpireTime(System.currentTimeMillis() + 86400000);
        messageObject.setPosition(2);
        messageObject.setNotified(0);
        messageObject.setCreateTime(System.currentTimeMillis());
        messageObject.setMsgId(MSG_ID_WIFI);
        messageObject.setMsgTitle(string);
        messageObject.setMsgContent(string2);
        messageObject.setModule(WIFI_MODULE);
        String str = pushBean.mScaleUid;
        if (TextUtils.isEmpty(pushBean.mScaleUid) || pushBean.mScaleUid.equals("0")) {
            str = cei.b().getMainUserUuid();
        }
        StringBuilder sb = new StringBuilder(16);
        sb.append("messagecenter://userinfo_weight?key=");
        sb.append(str);
        messageObject.setDetailUri(sb.toString());
        messageObject.setWeight(1);
        LogUtil.a(TAG, "start data notification");
        MCNotificationManager.cancelNotification(CommonUtil.m(context, "111"));
        MCNotificationManager.showNotification(context, messageObject);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void getAllWifiWeightData(Context context) {
        ArrayList<Long> deviceCodeFromWiFiDevice = cei.b().getDeviceCodeFromWiFiDevice();
        if (deviceCodeFromWiFiDevice == null || deviceCodeFromWiFiDevice.size() <= 0) {
            LogUtil.h(TAG, "getAllWifiWeightData wifiDeviceList is null");
            return;
        }
        Iterator<Long> it = deviceCodeFromWiFiDevice.iterator();
        while (it.hasNext()) {
            getWifiWeightData(context, it.next().longValue());
        }
    }

    private void getWifiWeightData(Context context, long j) {
        LogUtil.c(TAG, "getWifiWeightData deviceCode is: ", Long.valueOf(j));
        if (context == null || j == 0) {
            LogUtil.h(TAG, "getWifiWeightData error, context: ", context, "|deviceCode: ", Long.valueOf(j));
            return;
        }
        GetBindDeviceReq getBindDeviceReq = new GetBindDeviceReq();
        getBindDeviceReq.setDeviceCode(Long.valueOf(j));
        GetBindDeviceRsp c = jbs.a(context).c(getBindDeviceReq);
        if (!checkCloudResponse(c)) {
            LogUtil.h(TAG, "getWifiWeightData checkCloudResponse error");
            return;
        }
        List<DeviceInfo> deviceInfos = c.getDeviceInfos();
        if (koq.b(deviceInfos)) {
            LogUtil.h(TAG, "getWifiWeightData deviceInfos is null");
            return;
        }
        String uniqueId = deviceInfos.get(0).getUniqueId();
        LogUtil.c(TAG, "getWifiWeightData deviceUUID is: ", uniqueId);
        if (uniqueId == null) {
            LogUtil.a(TAG, "getWifiWeightData error, deviceUuid is null");
            return;
        }
        SharedPreferenceManager.e(BaseApplication.getContext(), "wifi_weight_device", "wifi_push_weight_download", "0", (StorageParams) null);
        cei.b().initUserIfEmpty();
        queryHiHealthData(context, uniqueId);
    }

    private boolean checkCloudResponse(GetBindDeviceRsp getBindDeviceRsp) {
        if (getBindDeviceRsp == null) {
            LogUtil.h(TAG, "checkCloudResponse response is null");
            return false;
        }
        int intValue = getBindDeviceRsp.getResultCode().intValue();
        LogUtil.a(TAG, "checkCloudResponse response resultCode is,", Integer.valueOf(intValue));
        return intValue == 0;
    }

    private void queryHiHealthData(Context context, String str) {
        long currentTimeMillis = System.currentTimeMillis();
        LogUtil.a(TAG, "endTime: ", Long.valueOf(currentTimeMillis));
        String[] strArr = {BleConstants.WEIGHT_KEY};
        HiAggregateOption hiAggregateOption = new HiAggregateOption();
        hiAggregateOption.setTimeRange(HiDateUtil.t(0L), HiDateUtil.f(currentTimeMillis));
        hiAggregateOption.setTimeRange(0L, currentTimeMillis);
        hiAggregateOption.setType(new int[]{10006});
        hiAggregateOption.setGroupUnitType(0);
        hiAggregateOption.setAggregateType(1);
        hiAggregateOption.setSortOrder(1);
        hiAggregateOption.setFilter("");
        hiAggregateOption.setConstantsKey(strArr);
        hiAggregateOption.setCount(1);
        hiAggregateOption.setDeviceUuid(str);
        HiHealthManager.d(context).aggregateHiHealthData(hiAggregateOption, new HiAggregateListener() { // from class: com.huawei.pluginmessagecenter.receiver.WifiSyncMsgReceiver.3
            @Override // com.huawei.hihealth.data.listener.HiAggregateListener
            public void onResultIntent(int i, List<HiHealthData> list, int i2, int i3) {
            }

            @Override // com.huawei.hihealth.data.listener.HiAggregateListener
            public void onResult(List<HiHealthData> list, int i, int i2) {
                if (koq.b(list)) {
                    LogUtil.h(WifiSyncMsgReceiver.TAG, "datas is null");
                    return;
                }
                LogUtil.c(WifiSyncMsgReceiver.TAG, "data download complete");
                if (WifiSyncMsgReceiver.sIsConflict) {
                    cei.b().startSync();
                } else {
                    SharedPreferenceManager.e(BaseApplication.getContext(), "wifi_weight_device", "wifi_push_weight_download", "1", (StorageParams) null);
                    cei.b().sendEventToKaKa(list.get(0).getInt("trackdata_deviceType"));
                }
            }
        });
    }

    private void triggerSyncCloud(Context context) {
        HiSyncOption hiSyncOption = new HiSyncOption();
        hiSyncOption.setSyncModel(2);
        hiSyncOption.setSyncAction(2);
        hiSyncOption.setSyncDataType(HiSyncType.HiSyncDataType.c);
        hiSyncOption.setSyncScope(1);
        hiSyncOption.setSyncMethod(2);
        hiSyncOption.setPushAction(2);
        hiSyncOption.setForceSync(true);
        HiHealthNativeApi.a(context).synCloud(hiSyncOption, null);
    }

    @Override // com.huawei.health.messagecenter.model.IpushBase
    public List<String> getPushType() {
        return Arrays.asList("9");
    }

    @Override // com.huawei.health.messagecenter.model.IpushBase
    public void processPushMsg(Context context, String str) {
        PushBean pushBean;
        LogUtil.a(TAG, "get WIFI_SYNC_PUSH_TYPE push msg time:" + jec.c(new Date()));
        if (TextUtils.isEmpty(str) || context == null) {
            LogUtil.h(TAG, "processPushMsg Error PushMsg or context is Empty");
            return;
        }
        LogUtil.c(TAG, "processPushMsg() message: ", str);
        String b = SharedPreferenceManager.b(BaseApplication.getContext(), "wifi_weight_device", "weight_notify_key");
        if ("false".equals(b)) {
            LogUtil.a(TAG, "don't show weight measure notify...showNotify: ", b);
            return;
        }
        try {
            Gson gson = new Gson();
            HiWiFiSyncMsgPushBean hiWiFiSyncMsgPushBean = (HiWiFiSyncMsgPushBean) gson.fromJson(str, HiWiFiSyncMsgPushBean.class);
            if (hiWiFiSyncMsgPushBean.mMsgContext == null) {
                pushBean = new PushBean();
            } else {
                pushBean = (PushBean) gson.fromJson(hiWiFiSyncMsgPushBean.mMsgContext, PushBean.class);
            }
            if (hiWiFiSyncMsgPushBean.mPushType == 9) {
                LogUtil.a(TAG, "processPushMsg, startSync, time: ", jec.c(new Date()));
                registerPushDataSyncDone(context);
                this.mRetryTimes = 0;
                pushMessageData(context, pushBean);
                cei.b().confirmDevUserInfo();
                triggerSyncCloud(context);
            }
        } catch (JsonSyntaxException unused) {
            LogUtil.b(TAG, "processPushMsg JsonSyntaxException");
        }
    }

    class HiWiFiSyncMsgPushBean {

        @SerializedName(com.huawei.health.messagecenter.model.CommonUtil.MSG_CONTENT)
        private String mMsgContext;

        @SerializedName("pushId")
        private long mPushId = 0;

        @SerializedName("pushType")
        private int mPushType = 0;

        private HiWiFiSyncMsgPushBean() {
        }

        public String toString() {
            return "HiWiFiSyncMsgPushBean{, mPushType='" + this.mPushType + "', mPushId='" + this.mPushId + "'}";
        }
    }

    public class PushBean {

        @SerializedName("conflictFlag")
        private int mConflictFlag = 0;

        @SerializedName("dateTime")
        private long mDateTime = 0;

        @SerializedName("scaleUid")
        private String mScaleUid = "";

        public PushBean() {
        }

        public String toString() {
            return "PushBean{, mConflictFlag='" + this.mConflictFlag + "', mScaleUid='" + this.mScaleUid + "'}";
        }
    }
}
