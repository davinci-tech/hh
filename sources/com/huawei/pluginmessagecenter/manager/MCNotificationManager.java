package com.huawei.pluginmessagecenter.manager;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.text.TextUtils;
import androidx.core.app.NotificationManagerCompat;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.common.dfx.DfxUtils;
import com.huawei.health.R;
import com.huawei.health.messagecenter.model.MessageObject;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.OpAnalyticsUtil;
import com.huawei.pluginmessagecenter.activity.DispatchSkipEventActivity;
import defpackage.jdh;
import defpackage.lrg;
import defpackage.mrc;
import defpackage.mrk;
import health.compact.a.CommonUtil;
import health.compact.a.SharedPreferenceManager;
import java.text.SimpleDateFormat;
import java.util.Date;

/* loaded from: classes6.dex */
public class MCNotificationManager {
    private static final int DEVICE_UPDATE_NOTIFY_ID = 20171027;
    private static final int HEALTH_BLOOD_PRESSURE = 20240710;
    private static final int HEALTH_PROCESSOR_MEDICAL_EXAM_REPORT_NOTIFY_ID = 20230221;
    private static final int HEALTH_PROCESSOR_PPG_NOTIFY_ID = 20221130;
    private static final String TAG = "UIDV_MCNotificationManager";
    private static final String WIFI_AUTH = "WIFI_NOTIFICATION_MODULE_AUTH";

    private static boolean isRead(int i) {
        return i == 1;
    }

    private MCNotificationManager() {
    }

    public static void showNotification(Context context, MessageObject messageObject) {
        LogUtil.a(TAG, "showNotification()");
        if (context == null || messageObject == null) {
            LogUtil.a(TAG, "messageObject == null");
            return;
        }
        if (!NotificationManagerCompat.from(context).areNotificationsEnabled()) {
            LogUtil.h(TAG, "notification unable");
            return;
        }
        String b = SharedPreferenceManager.b(BaseApplication.e(), Integer.toString(10000), "health_msg_switch_noticebar");
        LogUtil.a(TAG, "showNotification() noticebarRecommend", b);
        if ("0".equals(b) || !isNotificationMsg(messageObject) || isRead(messageObject.getReadFlag()) || checkExpireTime(messageObject) || messageObject.getNotified() == 1) {
            return;
        }
        notificationMsg(context, messageObject);
    }

    private static void notificationMsg(Context context, MessageObject messageObject) {
        try {
            Notification.Builder xf_ = jdh.c().xf_();
            if (!TextUtils.isEmpty(messageObject.getMsgTitle())) {
                xf_.setContentTitle(messageObject.getMsgTitle());
            }
            xf_.setContentIntent(getDefaultIntent(context, messageObject)).setStyle(new Notification.BigTextStyle().bigText(messageObject.getMsgContent())).setTicker(messageObject.getMsgTitle()).setNumber(messageObject.getWeight()).setWhen(messageObject.getCreateTime()).setShowWhen(true).setPriority(0).setAutoCancel(true).setOngoing(false).setGroupSummary(messageObject.isGroupSummary()).setDefaults(2).setExtras(lrg.bZn_(new Bundle(), messageObject.getDeviceLinkageList()));
            if (!TextUtils.isEmpty(messageObject.getMsgContent())) {
                xf_.setContentText(messageObject.getMsgContent());
            }
            LogUtil.a(TAG, "showNotification() title = ", messageObject.getMsgTitle());
            xf_.setSmallIcon(CommonUtil.as() ? BaseApplication.e().getResources().getIdentifier("healthlogo_hw_app_beta", "mipmap", com.huawei.hwcommonmodel.application.BaseApplication.getAppPackage()) : R.drawable.healthlogo_ic_notification);
            if (messageObject.getModule() != null && WIFI_AUTH.equals(messageObject.getModule())) {
                xf_.setSubText(new SimpleDateFormat("a hh:mm").format(new Date()));
                xf_.setShowWhen(false);
            }
            if (messageObject.getType().equals("device_ota")) {
                jdh.c().xh_(DEVICE_UPDATE_NOTIFY_ID, xf_.build());
            } else if (messageObject.getType().equals("device_ota1")) {
                jdh.c().xh_(20180808, xf_.build());
            } else if (messageObject.getType().equals("device_scale_ota")) {
                jdh.c().xh_(20180920, xf_.build());
            } else if (messageObject.getType().equals("HiHealthProcessor_PPG")) {
                jdh.c().xh_(HEALTH_PROCESSOR_PPG_NOTIFY_ID, xf_.build());
            } else if (messageObject.getType().equals("HiHealthProcessor_MedicalExamReport")) {
                jdh.c().xh_(HEALTH_PROCESSOR_MEDICAL_EXAM_REPORT_NOTIFY_ID, xf_.build());
            } else if (messageObject.getType().equals("BloodPressure")) {
                jdh.c().xh_(HEALTH_BLOOD_PRESSURE, xf_.build());
            } else {
                jdh.c().xh_(Integer.parseInt(messageObject.getMsgId().substring(1)), xf_.build());
            }
            mrc.e(BaseApplication.e()).h(messageObject.getMsgId());
        } catch (Resources.NotFoundException unused) {
            LogUtil.b(TAG, "ResourcesNotFoundException");
        } catch (NumberFormatException e) {
            LogUtil.b(TAG, e.getMessage());
        } catch (StringIndexOutOfBoundsException e2) {
            handleExceptionMessage("notificationMsg", messageObject, e2);
        }
    }

    public static void showUpdateMessageNotification(Context context, MessageObject messageObject, long j) {
        LogUtil.a(TAG, "start_showNotification");
        if (messageObject == null) {
            LogUtil.a(TAG, "return");
            return;
        }
        String b = SharedPreferenceManager.b(BaseApplication.e(), Integer.toString(10000), "health_msg_switch_noticebar");
        LogUtil.a(TAG, "showNotification() noticebarRecommend", b);
        if ("0".equals(b) || !isNotificationMsg(messageObject) || isRead(messageObject.getReadFlag()) || messageObject.getNotified() == 1) {
            return;
        }
        try {
            LogUtil.a(TAG, "msgObj.getMsgTitle() ", messageObject.getMsgTitle());
            LogUtil.a(TAG, "msgObj.getMsgContent() ", messageObject.getMsgContent());
            LogUtil.a(TAG, "msgObj.getDetailUri() ", messageObject.getDetailUri());
            LogUtil.a(TAG, "msgObj.getMsgId() ", messageObject.getMsgId());
            LogUtil.a(TAG, "msgObj.getWeight() ", Integer.valueOf(messageObject.getWeight()));
            LogUtil.a(TAG, "msgObj.getCreateTime() ", Long.valueOf(messageObject.getCreateTime()));
            Notification.Builder xf_ = jdh.c().xf_();
            xf_.setContentTitle(messageObject.getMsgTitle()).setContentText(messageObject.getMsgContent()).setContentIntent(getDefaultIntent(context, messageObject)).setTicker(messageObject.getMsgTitle()).setNumber(messageObject.getWeight()).setWhen(messageObject.getCreateTime()).setShowWhen(true).setPriority(0).setAutoCancel(true).setOngoing(false).setDefaults(2).setExtras(lrg.bZn_(new Bundle(), messageObject.getDeviceLinkageList()));
            LogUtil.a(TAG, "Builder_setMess");
            xf_.setSmallIcon(R.drawable.healthlogo_ic_notification);
            LogUtil.a(TAG, "getMessageNotifyId ==", Long.valueOf(j));
            jdh.c().xh_((int) j, xf_.build());
            LogUtil.a(TAG, "end_showNotification");
        } catch (Resources.NotFoundException unused) {
            LogUtil.b(TAG, "ResourcesNotFoundException");
        } catch (NumberFormatException e) {
            LogUtil.b(TAG, e.getMessage());
        }
    }

    private static boolean isNotificationMsg(MessageObject messageObject) {
        return messageObject.getPosition() == 2 || messageObject.getPosition() == 3;
    }

    private static boolean checkExpireTime(MessageObject messageObject) {
        if (messageObject.getExpireTime() == 0) {
            return false;
        }
        long b = mrk.b(System.currentTimeMillis());
        long expireTime = messageObject.getExpireTime();
        return expireTime < b && expireTime > mrk.b(0L);
    }

    private static PendingIntent getDefaultIntent(Context context, MessageObject messageObject) {
        int i;
        LogUtil.c(TAG, "detailUri=====>", messageObject.getDetailUri());
        Intent intent = new Intent();
        intent.setClass(context, DispatchSkipEventActivity.class);
        intent.setFlags(335544320);
        intent.putExtra(com.huawei.health.messagecenter.model.CommonUtil.DETAIL_URI, messageObject.getDetailUri());
        intent.putExtra("msgId", messageObject.getMsgId());
        intent.putExtra("from", 2);
        intent.putExtra("module", messageObject.getModule());
        intent.putExtra("notifiUri", messageObject.getDetailUri());
        intent.putExtra("messageContent", messageObject.getMsgContent());
        try {
            i = Integer.parseInt(messageObject.getMsgId().substring(1));
        } catch (NumberFormatException e) {
            LogUtil.b(TAG, e.getMessage());
            i = 0;
            return PendingIntent.getActivity(context, i, intent, 201326592);
        } catch (StringIndexOutOfBoundsException e2) {
            handleExceptionMessage("getDefaultIntent", messageObject, e2);
            i = 0;
            return PendingIntent.getActivity(context, i, intent, 201326592);
        }
        return PendingIntent.getActivity(context, i, intent, 201326592);
    }

    private static void handleExceptionMessage(String str, MessageObject messageObject, StringIndexOutOfBoundsException stringIndexOutOfBoundsException) {
        String str2;
        if (messageObject != null) {
            str2 = "msgId = " + messageObject.getMsgId() + ", module = " + messageObject.getModule() + ", type = " + messageObject.getType() + ", msgType = " + messageObject.getMsgType() + ", msgFrom = " + messageObject.getMsgFrom() + ", huid = " + messageObject.getHuid();
        } else {
            str2 = "";
        }
        LogUtil.b(TAG, str + " StringIndexOutOfBoundsException, msg: ", str2);
        String d = DfxUtils.d(Thread.currentThread(), stringIndexOutOfBoundsException);
        OpAnalyticsUtil.getInstance().setProbabilityProblemEvent(str + " StringIndexOutOfBoundsException", "errMessage: " + d + " - msg: " + str2);
    }

    public static void cancelNotification(int i) {
        LogUtil.a(TAG, "Enter cancelNotification");
        try {
            jdh.c().a(i);
        } catch (Exception unused) {
            LogUtil.a(TAG, "error cancelNotification");
        }
    }
}
