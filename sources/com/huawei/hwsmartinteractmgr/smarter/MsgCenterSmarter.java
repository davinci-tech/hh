package com.huawei.hwsmartinteractmgr.smarter;

import android.content.ContentValues;
import android.content.Context;
import androidx.exifinterface.media.ExifInterface;
import com.huawei.health.messagecenter.api.MessageCenterApi;
import com.huawei.health.messagecenter.model.CommonUtil;
import com.huawei.health.messagecenter.model.MessageObject;
import com.huawei.hihealth.util.HiJsonUtil;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.hwsmartinteractmgr.data.SmartMsgConstant;
import com.huawei.hwsmartinteractmgr.data.SmartMsgDbObject;
import com.huawei.hwsmartinteractmgr.data.msgcontent.NotificationMsgContent;
import com.huawei.pluginmessagecenter.provider.data.MessageChangeEvent;
import com.huawei.pluginmessagecenter.service.MessageObserver;
import defpackage.jdx;
import defpackage.koq;
import defpackage.kvs;
import health.compact.a.Services;
import java.util.List;

/* loaded from: classes5.dex */
public class MsgCenterSmarter extends BaseSmarter {
    private MessageCenterApi b;
    private kvs c;
    private MessageObserver d;

    public MsgCenterSmarter(Context context) {
        super(context);
        this.d = new MessageObserver() { // from class: com.huawei.hwsmartinteractmgr.smarter.MsgCenterSmarter.1
            @Override // com.huawei.pluginmessagecenter.service.MessageObserver
            public void onChange(int i, MessageChangeEvent messageChangeEvent) {
                LogUtil.a("SMART_MsgCenterSmarter", "MessageObserver onChange start");
                jdx.b(new Runnable() { // from class: com.huawei.hwsmartinteractmgr.smarter.MsgCenterSmarter.1.1
                    @Override // java.lang.Runnable
                    public void run() {
                        MsgCenterSmarter.this.c(50001);
                        MsgCenterSmarter.this.c(50000);
                    }
                });
            }
        };
        LogUtil.a("SMART_MsgCenterSmarter", "MsgCenterSmarter");
        this.e = context.getApplicationContext();
        this.b = (MessageCenterApi) Services.c("PluginMessageCenter", MessageCenterApi.class);
        this.c = kvs.b(this.e);
    }

    public void a() {
        c(50001);
        c(50000);
    }

    public void c(int i) {
        MessageObject a2 = a(i);
        if (a2 == null) {
            LogUtil.a("SMART_MsgCenterSmarter", "no message msgType = ", Integer.valueOf(i), " deleteResult=", Integer.valueOf(this.c.d(i)));
            return;
        }
        LogUtil.a("SMART_MsgCenterSmarter", "msgType = ", Integer.valueOf(i), " msgId = ", a2.getMsgId());
        SmartMsgDbObject a3 = this.c.a(i);
        if (a3 == null) {
            LogUtil.a("SMART_MsgCenterSmarter", "msgType = ", Integer.valueOf(i), " isInserted=", Boolean.valueOf(this.c.a(b(a2, i))));
            return;
        }
        String notificationId = ((NotificationMsgContent) HiJsonUtil.e(a3.getMsgContent(), NotificationMsgContent.class)).getNotificationId();
        if (notificationId != null && notificationId.equals(a2.getMsgId())) {
            if (a3.getStatus() == 2 || a3.getUpdateTime() != a2.getCreateTime()) {
                LogUtil.a("SMART_MsgCenterSmarter", "msgType = ", Integer.valueOf(i), " updateResult= ", Boolean.valueOf(c(a3.getId(), a2)));
            }
            LogUtil.a("SMART_MsgCenterSmarter", "msgType = ", Integer.valueOf(i), " oldId equals newId finish");
            return;
        }
        LogUtil.a("SMART_MsgCenterSmarter", "msgType = ", Integer.valueOf(i), " new msg delResult=", Integer.valueOf(this.c.d(i)), ", isInserted=", Boolean.valueOf(this.c.a(b(a2, i))));
    }

    private boolean c(int i, MessageObject messageObject) {
        NotificationMsgContent notificationMsgContent = new NotificationMsgContent();
        notificationMsgContent.setContent(messageObject.getMsgTitle());
        notificationMsgContent.setNotificationId(messageObject.getMsgId());
        notificationMsgContent.setType(messageObject.getType());
        notificationMsgContent.setUrl(messageObject.getDetailUri());
        ContentValues contentValues = new ContentValues();
        contentValues.put("msgContent", HiJsonUtil.d(notificationMsgContent, NotificationMsgContent.class));
        contentValues.put("expireTime", Long.valueOf(messageObject.getExpireTime()));
        return kvs.b(this.e).bSl_(i, contentValues);
    }

    private SmartMsgDbObject b(MessageObject messageObject, int i) {
        SmartMsgDbObject smartMsgDbObject = new SmartMsgDbObject();
        smartMsgDbObject.setMsgType(i);
        smartMsgDbObject.setShowMethod(SmartMsgConstant.SHOW_METHOD_SMART_CARD);
        smartMsgDbObject.setUpdateTime(messageObject.getCreateTime());
        smartMsgDbObject.setShowTime(SmartMsgConstant.DEFAULT_SHOW_TIME);
        smartMsgDbObject.setMsgContentType(4);
        smartMsgDbObject.setExpireTime(messageObject.getExpireTime());
        NotificationMsgContent notificationMsgContent = new NotificationMsgContent();
        notificationMsgContent.setContent(messageObject.getMsgTitle());
        notificationMsgContent.setNotificationId(messageObject.getMsgId());
        notificationMsgContent.setType(messageObject.getType());
        notificationMsgContent.setUrl(messageObject.getDetailUri());
        smartMsgDbObject.setMsgContent(HiJsonUtil.d(notificationMsgContent, NotificationMsgContent.class));
        smartMsgDbObject.setMsgSrc(5);
        smartMsgDbObject.setMessagePriority(1000);
        smartMsgDbObject.setStatus(1);
        return smartMsgDbObject;
    }

    private MessageObject a(int i) {
        List<MessageObject> messages = this.b.getMessages(11);
        if (koq.b(messages)) {
            LogUtil.h("SMART_MsgCenterSmarter", "messageList is empty");
            return null;
        }
        for (MessageObject messageObject : messages) {
            if ((i == 50001 && e(messageObject)) || (i == 50000 && !e(messageObject) && !a(messageObject))) {
                return messageObject;
            }
        }
        return null;
    }

    private boolean a(MessageObject messageObject) {
        if (messageObject == null) {
            return false;
        }
        return CommonUtil.WEEK_REPORT_MESSAGE.equals(messageObject.getType()) || "monthReportMessage".equals(messageObject.getType());
    }

    private boolean e(MessageObject messageObject) {
        if (messageObject == null) {
            return false;
        }
        return messageObject.getMsgId().startsWith("G") || messageObject.getMsgId().startsWith(ExifInterface.LATITUDE_SOUTH);
    }

    public void b() {
        this.b.registerMessageObserver(this.d);
    }

    public void e() {
        this.b.unregisterMessageObserver(this.d);
    }
}
