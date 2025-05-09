package com.huawei.healthcloud.plugintrack.wearengine.rqmanager;

import android.content.Context;
import com.google.gson.annotations.SerializedName;
import com.huawei.haf.application.BaseApplication;
import com.huawei.health.devicemgr.api.constant.GetDeviceInfoMode;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.health.messagecenter.model.CommonUtil;
import com.huawei.health.messagecenter.model.IpushBase;
import com.huawei.hihealth.HiUserPreference;
import com.huawei.hihealth.api.HiHealthManager;
import com.huawei.hwlogsmodel.LogUtil;
import defpackage.cun;
import defpackage.cwi;
import defpackage.ggl;
import defpackage.ghb;
import defpackage.hqa;
import defpackage.jdx;
import defpackage.jec;
import health.compact.a.ReleaseLogUtil;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/* loaded from: classes.dex */
public class RqDataPushReceiver implements IpushBase {
    private static final String PUSH_DATA_TYPE = "RQ";
    private static final String PUSH_EVENT = "RQ_CALCULATE_SUCCESS";
    private static final String SYNC_DATA_PUSH_TYPE = "301";
    private static final String TAG = "RQData";
    private boolean mIsCorrect = false;

    @Override // com.huawei.health.messagecenter.model.IpushBase
    public List<String> getPushType() {
        return Arrays.asList("301");
    }

    @Override // com.huawei.health.messagecenter.model.IpushBase
    public void processPushMsg(final Context context, String str) {
        LogUtil.a(TAG, "get push msg time:", jec.c(new Date()));
        if (str != null) {
            if (str.length() >= 1) {
                LogUtil.a(TAG, "PushRqData: processPushMsg():message=", str);
                a aVar = (a) ghb.a(str, a.class);
                c cVar = (c) ghb.a(aVar.e, c.class);
                boolean z = cVar.e.contains(PUSH_DATA_TYPE) && cVar.b.contains(PUSH_EVENT) && aVar.c.contains("301");
                this.mIsCorrect = z;
                if (!z) {
                    ReleaseLogUtil.c(TAG, "processPushMsg Error PushMsg is error");
                    return;
                } else {
                    jdx.b(new Runnable() { // from class: com.huawei.healthcloud.plugintrack.wearengine.rqmanager.RqDataPushReceiver.2
                        @Override // java.lang.Runnable
                        public void run() {
                            HiUserPreference hiUserPreference = new HiUserPreference();
                            hiUserPreference.setKey("track.rq_config");
                            HashMap hashMap = new HashMap();
                            hashMap.put("isSyncOrNot", Boolean.toString(false));
                            hiUserPreference.setValue(ghb.e(hashMap));
                            HiHealthManager.d(BaseApplication.e()).setUserPreference(hiUserPreference);
                            if (!RqDataPushReceiver.checkDeviceRqDataCapability()) {
                                ReleaseLogUtil.a(RqDataPushReceiver.TAG, "sendRqDataToDevice failed with device not support");
                            } else {
                                new hqa().b(context, ggl.a(new Date(System.currentTimeMillis())));
                            }
                        }
                    });
                    return;
                }
            }
        }
        LogUtil.b(TAG, "processPushMsg Error PushMsg is Empty");
    }

    /* loaded from: classes4.dex */
    class c {

        @SerializedName("pushEvent")
        private String b;

        @SerializedName("pushDataType")
        private String e;

        public String toString() {
            return "RQDataMsgPushBean{, pushDataType='" + this.e + "', pushEvent='" + this.b + "'}";
        }
    }

    /* loaded from: classes4.dex */
    class a {

        @SerializedName("pushType")
        private String c;

        @SerializedName("pushId")
        private String d;

        @SerializedName(CommonUtil.MSG_CONTENT)
        private String e;

        public String toString() {
            return "RqDataMsgPushBean1{msgContext='" + this.e + "', pushId='" + this.d + "', pushType='" + this.c + "'}";
        }
    }

    public static boolean checkDeviceRqDataCapability() {
        DeviceInfo deviceInfo = cun.c().getDeviceInfo(GetDeviceInfoMode.CONNECTED_MAIN_DEVICES_WITHOUT_AW70, null, TAG);
        if (deviceInfo == null) {
            LogUtil.b(TAG, "checkDeviceRqDataCapability : deviceInfo is null");
            return false;
        }
        return cwi.c(deviceInfo, 53);
    }
}
