package com.huawei.hihealthservice.sync.util;

import android.content.Context;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.annotations.SerializedName;
import com.huawei.health.messagecenter.model.IpushBase;
import com.huawei.hihealth.HiSyncOption;
import com.huawei.hihealth.api.HiHealthNativeApi;
import com.huawei.hwlogsmodel.LogUtil;
import defpackage.jec;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.Date;
import java.util.List;

/* loaded from: classes.dex */
public class HiWiFiSyncMsgReceiver implements IpushBase {
    private static final String RELEASE_LOG_TAG = "HiH_HiWiFiSyncMsgReceiver";
    private static final String TAG = "Debug_HiWiFiSyncMsgReceiver";
    private static final String WIFI_SYNC_PUSH_TYPE = "9";

    @Override // com.huawei.health.messagecenter.model.IpushBase
    public List<String> getPushType() {
        return null;
    }

    @Override // com.huawei.health.messagecenter.model.IpushBase
    public void processPushMsg(Context context, String str) {
        LogUtil.a(TAG, "get WIFI_SYNC_PUSH_TYPE push msg time:" + jec.c(new Date()));
        if (str == null || str.length() < 1) {
            LogUtil.h(TAG, "processPushMsg  Error PushMsg is Empty");
            return;
        }
        LogUtil.c(TAG, "processPushMsg():msg=" + str);
        try {
            if ("9".equals(((HiWiFiSyncMsgPushBean) new Gson().fromJson(str, HiWiFiSyncMsgPushBean.class)).mPushType)) {
                LogUtil.a(TAG, "processPushMsg, mPushType = WIFI_SYNC_PUSH_TYPE");
                HiSyncOption hiSyncOption = new HiSyncOption();
                hiSyncOption.setSyncModel(2);
                hiSyncOption.setSyncAction(2);
                hiSyncOption.setSyncDataType(20000);
                hiSyncOption.setSyncScope(1);
                hiSyncOption.setSyncMethod(2);
                hiSyncOption.setPushAction(2);
                LogUtil.a(TAG, "processPushMsg, startSync,time:" + jec.c(new Date()));
                if (context == null) {
                    return;
                }
                HiHealthNativeApi.a(context).synCloud(hiSyncOption, null);
            }
        } catch (JsonSyntaxException e) {
            ReleaseLogUtil.c(RELEASE_LOG_TAG, "processPushMsg JsonSyntaxException:" + e.getMessage());
        }
    }

    /* loaded from: classes4.dex */
    class HiWiFiSyncMsgPushBean {

        @SerializedName("pushType")
        private String mPushType = "";

        @SerializedName("pushId")
        private String mPushId = "";

        private HiWiFiSyncMsgPushBean() {
        }

        public String toString() {
            return "HiWiFiSyncMsgPushBean{, mPushType='" + this.mPushType + "', mPushId='" + this.mPushId + "'}";
        }
    }
}
