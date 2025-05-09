package com.huawei.basichealthmodel.receiver;

import android.content.Context;
import android.text.TextUtils;
import com.google.gson.Gson;
import com.huawei.health.messagecenter.model.IpushBase;
import com.huawei.hwdevice.phoneprocess.mgr.exercise.HwExerciseConstants;
import com.huawei.hwlogsmodel.LogUtil;
import defpackage.azi;
import defpackage.bby;
import defpackage.nsn;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

/* loaded from: classes3.dex */
public class HealthModelPushReceiver implements IpushBase {
    private static final int HEALTH_MODEL_PUSH_TYPE = 31;
    private static final int HEALTH_MODEL_SHEDDING_FOUR_DAY = 4;
    private static final int HEALTH_MODEL_SHEDDING_SEVEN_DAY = 7;
    private static final int HEALTH_MODEL_SHEDDING_TWO_DAY = 2;
    private static final String TAG = "HealthLife_HealthModelPushReceiver";

    @Override // com.huawei.health.messagecenter.model.IpushBase
    public List<String> getPushType() {
        return Arrays.asList("31");
    }

    @Override // com.huawei.health.messagecenter.model.IpushBase
    public void processPushMsg(Context context, String str) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.h(TAG, "processPushMsg Error PushMsg is Empty");
            return;
        }
        LogUtil.c(TAG, "health model get message", str);
        HealthMsgPushBean healthMsgPushBean = (HealthMsgPushBean) new Gson().fromJson(str, HealthMsgPushBean.class);
        if (healthMsgPushBean == null || healthMsgPushBean.pushType != 31) {
            LogUtil.h(TAG, "bean == null, pushType != SYNC_DATA");
            return;
        }
        int e = nsn.e(healthMsgPushBean.msgContext);
        int reminderType = getReminderType(e);
        if (reminderType == -1) {
            LogUtil.h(TAG, "sheddingDays", Integer.valueOf(e));
            return;
        }
        long reminderTime = getReminderTime();
        bby.e(reminderType, reminderTime, 1, 1);
        LogUtil.a(TAG, "registerHealthModelReminder reminderId", Integer.valueOf(reminderType), "reminderTime", Long.valueOf(reminderTime));
    }

    private int getReminderType(int i) {
        if (i == 2 || i == 4) {
            return 4000;
        }
        return (i % 7 != 0 || i == 0) ? -1 : 4001;
    }

    private long getReminderTime() {
        Calendar s = azi.s();
        s.add(12, HwExerciseConstants.NINE_MINUTES_PACE);
        if (System.currentTimeMillis() > s.getTimeInMillis()) {
            s.add(5, 1);
        }
        return s.getTimeInMillis();
    }

    static class HealthMsgPushBean {
        private int pushType = 0;
        private String msgContext = "";

        private HealthMsgPushBean() {
        }

        public String toString() {
            return "HiSyncMsgPushBean{, pushType='" + this.pushType + "', pushId='" + this.msgContext + "'}";
        }
    }
}
