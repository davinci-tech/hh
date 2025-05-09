package com.huawei.hwdevice.mainprocess.mgr.hwotamanager;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import com.huawei.haf.application.BaseApplication;
import com.huawei.health.R;
import com.huawei.health.messagecenter.model.CommonUtil;
import com.huawei.health.messagecenter.model.MessageObject;
import com.huawei.hwlogsmodel.LogUtil;
import defpackage.jdh;
import health.compact.a.SharedPreferenceManager;

/* loaded from: classes5.dex */
public class NotificationManager {
    private MessageObject c;
    private Context d;

    private boolean e(int i) {
        return i == 1;
    }

    public NotificationManager(Context context) {
        this(context, null);
    }

    public NotificationManager(Context context, MessageObject messageObject) {
        this.d = context;
        this.c = messageObject;
    }

    public void e(long j) {
        LogUtil.a("NotificationManager", "start_showNotification");
        Context context = this.d;
        if (context == null || this.c == null) {
            LogUtil.h("NotificationManager", "showUpdateMessageNotification return");
            return;
        }
        String b = SharedPreferenceManager.b(context, Integer.toString(10000), "health_msg_switch_noticebar");
        LogUtil.a("NotificationManager", "showNotification() noticeRecommend", b);
        if ("0".equals(b) || !c() || e(this.c.getReadFlag()) || this.c.getNotified() == 1) {
            return;
        }
        try {
            Notification.Builder xf_ = jdh.c().xf_();
            LogUtil.a("NotificationManager", "builder =  ", xf_, "msgObj.getMsgTitle() ", this.c.getMsgTitle(), "msgObj.getMsgContent() ", this.c.getMsgContent(), "msgObj.getDetailUri() ", this.c.getDetailUri(), "msgObj.getMsgId() ", this.c.getMsgId(), "msgObj.getWeight() ", Integer.valueOf(this.c.getWeight()), "msgObj.getCreateTime() ", Long.valueOf(this.c.getCreateTime()));
            xf_.setContentTitle(this.c.getMsgTitle()).setContentText(this.c.getMsgContent()).setContentIntent(bHT_(this.c.getDetailUri(), this.c.getMsgId())).setTicker(this.c.getMsgTitle()).setNumber(this.c.getWeight()).setWhen(this.c.getCreateTime()).setStyle(new Notification.BigTextStyle().bigText(this.c.getMsgContent())).setShowWhen(true).setPriority(0).setAutoCancel(true).setOngoing(false).setDefaults(2);
            LogUtil.a("NotificationManager", "Builder_setMess");
            xf_.setSmallIcon(R.drawable.healthlogo_ic_notification);
            LogUtil.a("NotificationManager", "getMessageNotifyId  ", Long.valueOf(j));
            jdh.c().xh_((int) j, xf_.build());
            LogUtil.a("NotificationManager", "end showNotification");
        } catch (Resources.NotFoundException unused) {
            LogUtil.b("NotificationManager", "ResourcesNotFoundException");
        } catch (NumberFormatException e) {
            LogUtil.b("NotificationManager", e.getMessage());
        }
    }

    private boolean c() {
        return this.c.getPosition() == 2 || this.c.getPosition() == 3;
    }

    private PendingIntent bHT_(String str, String str2) {
        LogUtil.c("NotificationManager", "detailUri ", str);
        Intent intent = new Intent();
        intent.setClassName(BaseApplication.d(), "com.huawei.pluginmessagecenter.activity.DispatchSkipEventActivity");
        intent.putExtra(CommonUtil.DETAIL_URI, str);
        intent.putExtra("msgId", str2);
        intent.putExtra("notifiUri", str);
        if (str2.length() > 1) {
            health.compact.a.CommonUtil.a(str2.substring(1), 10);
        }
        return PendingIntent.getActivity(this.d, 0, intent, 201326592);
    }

    public void d(int i) {
        LogUtil.a("NotificationManager", "Enter cancelNotification");
        try {
            jdh.c().a(i);
        } catch (Exception unused) {
            LogUtil.b("NotificationManager", "error cancelNotification");
        }
    }
}
