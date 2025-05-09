package com.huawei.health.device.wifi.interfaces;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;
import com.huawei.health.device.util.EventBus;
import com.huawei.health.messagecenter.model.CommonUtil;
import com.huawei.health.messagecenter.model.IpushBase;
import com.huawei.hwlogsmodel.LogUtil;
import defpackage.cpw;
import defpackage.csb;
import defpackage.csd;
import defpackage.jec;
import health.compact.a.CompileParameterUtil;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/* loaded from: classes3.dex */
public class WiFiMultiUserPushReceiver implements IpushBase {
    private static final String MAIN_USER_AGREE_AUTHORIZATION = "14";
    private static final String MAIN_USER_REJECT_AUTHORIZATION = "15";
    private static final String MAIN_USER_RELEASE_AUTHORIZATION = "11";
    private static final String MAIN_USER_SHARE_DEVICE = "113";
    private static final String SUB_USER_AUTHORIZED_SUCCESS = "10";
    private static final String SUB_USER_EXIT_AUTHORIZATION = "12";
    private static final String SUB_USER_REQUEST_AUTHORIZATION = "13";
    private static final String TAG = "WiFiMultiUserPushReceiver";

    @Override // com.huawei.health.messagecenter.model.IpushBase
    public List<String> getPushType() {
        return Arrays.asList(MAIN_USER_SHARE_DEVICE, "10", "11", "12", "13", "14", "15");
    }

    @Override // com.huawei.health.messagecenter.model.IpushBase
    public void processPushMsg(Context context, String str) {
        cpw.a(false, TAG, "get pushType msg time:", jec.c(new Date()));
        if (TextUtils.isEmpty(str)) {
            cpw.e(false, TAG, "processPushMsg Error, PushMsg is Empty");
            return;
        }
        if (CompileParameterUtil.a("SUPPORT_WIFI_WEIGHT_DEVICE", false)) {
            cpw.a(false, TAG, "WiFiMultiUserPushReceiver processPushMsg():msg =", str);
            try {
                Gson gson = new Gson();
                HiWiFiSyncMsgPushBean hiWiFiSyncMsgPushBean = (HiWiFiSyncMsgPushBean) gson.fromJson(str, new TypeToken<HiWiFiSyncMsgPushBean>() { // from class: com.huawei.health.device.wifi.interfaces.WiFiMultiUserPushReceiver.1
                }.getType());
                cpw.a(false, TAG, "processPushMsg, pushType = ", hiWiFiSyncMsgPushBean.getPushType());
                HiWiFiSyncMsgPushBean.UserAccount userAccount = (HiWiFiSyncMsgPushBean.UserAccount) gson.fromJson(hiWiFiSyncMsgPushBean.getMsgContext(), HiWiFiSyncMsgPushBean.UserAccount.class);
                cpw.a(false, TAG, "processPushMsg, NUA = ", userAccount.getUserAccount());
                processByType(userAccount, hiWiFiSyncMsgPushBean);
            } catch (JsonSyntaxException e) {
                cpw.e(false, TAG, "processPushMsg JsonSyntaxException:", e.getMessage());
            }
        }
    }

    private void processByType(HiWiFiSyncMsgPushBean.UserAccount userAccount, HiWiFiSyncMsgPushBean hiWiFiSyncMsgPushBean) {
        if ("10".equals(hiWiFiSyncMsgPushBean.getPushType())) {
            csd.e().c(userAccount.getUserAccount());
            return;
        }
        if ("11".equals(hiWiFiSyncMsgPushBean.getPushType())) {
            Bundle bundle = new Bundle();
            bundle.putString("pushType", "release_auth");
            bundle.putString("pushContent", userAccount.getUserAccount());
            EventBus.d(new EventBus.b("multi_user_auto_cancle_dialog", bundle));
            csb.a().g();
            return;
        }
        if ("12".equals(hiWiFiSyncMsgPushBean.getPushType())) {
            Bundle bundle2 = new Bundle();
            bundle2.putString("pushType", "sub_user_un_authorize");
            bundle2.putString("pushContent", userAccount.getUserAccount());
            EventBus.d(new EventBus.b("sub_user_unauthorize_notification", bundle2));
            csd.e().c();
            return;
        }
        if ("13".equals(hiWiFiSyncMsgPushBean.getPushType())) {
            LogUtil.a(TAG, "receive SUB_USER_REQUEST_AUTHORIZATION");
            pushWifiDeviceEvent(hiWiFiSyncMsgPushBean.getMsgContext(), "sub_user_req_authorize_notification");
            return;
        }
        if ("14".equals(hiWiFiSyncMsgPushBean.getPushType())) {
            LogUtil.a(TAG, "receive MAIN_USER_AGREE_AUTHORIZATION");
            csd.e().c(userAccount.getUserAccount());
            pushWifiDeviceEvent(hiWiFiSyncMsgPushBean.getMsgContext(), "authorization_agreed_notification");
        } else if ("15".equals(hiWiFiSyncMsgPushBean.getPushType())) {
            LogUtil.a(TAG, "receive MAIN_USER_REJECT_AUTHORIZATION");
            pushWifiDeviceEvent(hiWiFiSyncMsgPushBean.getMsgContext(), "authorization_rejected_notification");
        } else if (MAIN_USER_SHARE_DEVICE.equals(hiWiFiSyncMsgPushBean.getPushType())) {
            LogUtil.a(TAG, "receive MAIN_USER_SHARE_DEVICE");
            pushWifiDeviceEvent(hiWiFiSyncMsgPushBean.getMsgContext(), "device_main_share_to_sub_user");
        } else {
            cpw.d(false, TAG, "processPushMsg, pushType is other ");
        }
    }

    private void pushWifiDeviceEvent(String str, String str2) {
        LogUtil.a(TAG, "pushWifiDeviceEvent:", str2);
        Bundle bundle = new Bundle();
        bundle.putString("pushContent", str);
        EventBus.d(new EventBus.b(str2, bundle));
    }

    public static class MessageContent {
        private String userAccount = "";
        private String prodId = "";

        public String getUserAccount() {
            return this.userAccount;
        }

        public void setUserAccount(String str) {
            this.userAccount = str;
        }

        public String getProdId() {
            return this.prodId;
        }

        public void setProdId(String str) {
            this.prodId = str;
        }
    }

    static class HiWiFiSyncMsgPushBean {

        @SerializedName(CommonUtil.MSG_CONTENT)
        private String msgContext;

        @SerializedName("pushType")
        private String pushType = "";

        @SerializedName("pushId")
        private String pushId = "";

        private HiWiFiSyncMsgPushBean() {
        }

        static class UserAccount {

            @SerializedName("userAccount")
            private String userAccount = "";

            private UserAccount() {
            }

            public String getUserAccount() {
                return this.userAccount;
            }

            public void setUserAccount(String str) {
                this.userAccount = str;
            }
        }

        public String getPushType() {
            return this.pushType;
        }

        public void setPushType(String str) {
            this.pushType = str;
        }

        public String getPushId() {
            return this.pushId;
        }

        public void setPushId(String str) {
            this.pushId = str;
        }

        public String getMsgContext() {
            return this.msgContext;
        }

        public void setMsgContext(String str) {
            this.msgContext = str;
        }

        public String toString() {
            return "HiWiFiSyncMsgPushBean{, pushType ='" + this.pushType + "', pushId ='" + this.pushId + "'}";
        }
    }
}
