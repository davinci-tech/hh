package com.huawei.health.receiver;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.text.TextUtils;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.R;
import com.huawei.health.browseraction.HwSchemeBasicHealthActivity;
import com.huawei.health.messagecenter.api.MessageCenterApi;
import com.huawei.health.messagecenter.model.IpushBase;
import com.huawei.health.messagecenter.model.MessageConstant;
import com.huawei.health.messagecenter.model.MessageObject;
import com.huawei.health.pluginhealthzone.FamilyHealthZoneApi;
import com.huawei.health.pluginhealthzone.UserInfoCallback;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcloudmodel.model.ecgservice.EcgGiftCardRequest;
import com.huawei.hwcloudmodel.model.ecgservice.EcgGiftCardResponse;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.utils.Constants;
import defpackage.exh;
import defpackage.ezw;
import defpackage.jbs;
import defpackage.jdh;
import defpackage.mxv;
import defpackage.nsn;
import health.compact.a.Services;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

/* loaded from: classes.dex */
public class EcgPushReceiver implements IpushBase {
    private static final String CONNECTION_SYMBOL = "-";
    private static final int ECG_BAD_INTERPRETATION_REPORT_PUSH_TYPE = 201;
    private static final String ECG_DETAIL_URI = "messagecenter://ecgDetail?ecgPageType=";
    private static final int ECG_INTERPRETATION_COMPLETED = 502;
    private static final int ECG_INTERPRETATION_PROFESSOR = 503;
    private static final int ECG_INTERPRETATION_REPORT_PUSH_TYPE = 200;
    private static final int ECG_INTERPRETING_STATUS_PUSH_TYPE = 202;
    private static final int ECG_NOTIFICATION_ID = 20201111;
    private static final int ECG_SERVICE_CARD_PUSH_TYPE = 203;
    private static final int ECG_STRAP_REPORT = 401;
    private static final String PAGE_DONATE = "donate&donateId=";
    private static final String PAGE_RETURN_CARD = "returnCard";
    private static final String TAG = "Ecg_PushReceiver";
    private static final int TYPE_ACTIVATED = 101;
    private static final int TYPE_ACTIVATED_RETURN = 103;
    private static final int TYPE_CANCEL_GIFT = 304;
    private static final int TYPE_CARD_RETURN = 201;
    private static final int TYPE_GET_CARD = 301;
    private static final int TYPE_GET_CARD_EXPIRED = 305;
    private static final int TYPE_GIFTING_CARD = 303;
    private static final int TYPE_TO_GIFT_CARD = 302;
    private static final int TYPE_TRIAL_RETURN = 102;

    @Override // com.huawei.health.messagecenter.model.IpushBase
    public List<String> getPushType() {
        return Arrays.asList("200", Constants.ERROR_SERVICE_ID_STR, Constants.ERROR_UNKNOWN, "203");
    }

    @Override // com.huawei.health.messagecenter.model.IpushBase
    public void processPushMsg(Context context, String str) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.h(TAG, "processPushMsg Error PushMsg is Empty");
            return;
        }
        try {
            LogUtil.c(TAG, "Ecg_PushReceiver get message", str);
            EcgMsgPushBean ecgMsgPushBean = (EcgMsgPushBean) new Gson().fromJson(str, EcgMsgPushBean.class);
            if (ecgMsgPushBean == null) {
                LogUtil.h(TAG, "processPushMsg EcgMsgPushBean is null");
                return;
            }
            LogUtil.a(TAG, "bean: ", ecgMsgPushBean.toString());
            int i = ecgMsgPushBean.pushType;
            if (i != 200 && i != 201) {
                if (i == 203) {
                    setServiceCardNotify(context, ecgMsgPushBean.msgContext);
                    ezw.b(ecgMsgPushBean.msgContext);
                    return;
                } else if (i == 202) {
                    LogUtil.h(TAG, "Notification bar not displayed, pushType =", Integer.valueOf(i));
                    return;
                } else {
                    LogUtil.h(TAG, "pushType is error, pushType =", Integer.valueOf(i));
                    return;
                }
            }
            LogUtil.a(TAG, "processPushMsg, pushType is interpretation report");
            sendEcgReportNotify(context, i);
            mxv.a(20000);
        } catch (JsonSyntaxException unused) {
            LogUtil.b(TAG, "processPushMsg JsonSyntaxException");
        }
    }

    private void sendEcgReportNotify(Context context, int i) {
        String str;
        Resources resources = BaseApplication.getContext().getResources();
        if (i == 200) {
            str = resources.getString(R.string._2130844073_res_0x7f0219a9);
        } else if (i == 201) {
            str = resources.getString(R.string._2130844071_res_0x7f0219a7, 2);
        } else {
            LogUtil.h(TAG, "the ecgReportType is error");
            str = "";
        }
        if (TextUtils.isEmpty(str)) {
            LogUtil.h(TAG, "setNotify contentText is null");
        } else {
            setNotify(context, resources.getString(R.string._2130844070_res_0x7f0219a6), str, false);
        }
    }

    private void setNotify(Context context, String str, String str2, boolean z) {
        Intent intent = new Intent(BaseApplication.getContext(), (Class<?>) HwSchemeBasicHealthActivity.class);
        intent.putExtra("ecgNotification", 1);
        if (z) {
            intent.putExtra("ecgPageType", "ecgStrap");
        } else {
            intent.putExtra("ecgPageType", "collection");
        }
        PendingIntent activity = PendingIntent.getActivity(context, 0, intent, 201326592);
        Notification.Builder xf_ = jdh.c().xf_();
        xf_.setSmallIcon(R.drawable.healthlogo_ic_notification);
        xf_.setAutoCancel(true);
        xf_.setContentTitle(str);
        xf_.setContentText(str2);
        xf_.setContentIntent(activity);
        xf_.setStyle(new Notification.BigTextStyle().bigText(str2));
        xf_.setPriority(0);
        jdh.c().xh_(ECG_NOTIFICATION_ID, xf_.build());
    }

    private void setServiceCardNotify(final Context context, String str) {
        String string;
        String string2;
        String str2;
        String str3;
        String str4;
        String string3;
        MsgContext msgContext = (MsgContext) new Gson().fromJson(str, MsgContext.class);
        LogUtil.a(TAG, "setServiceCardNotify msgContext: ", msgContext);
        if (msgContext == null) {
            LogUtil.h(TAG, "msgContext is null.");
            return;
        }
        final int i = msgContext.detailType;
        LogUtil.a(TAG, "setServiceCardNotify detailType: ", Integer.valueOf(i));
        if (i == 401) {
            sendEcgStrapNotify(context);
            return;
        }
        final long j = msgContext.notifyTime;
        if (i >= 301 && i <= 305) {
            ThreadPoolManager.d().execute(new Runnable() { // from class: com.huawei.health.receiver.EcgPushReceiver$$ExternalSyntheticLambda1
                @Override // java.lang.Runnable
                public final void run() {
                    EcgPushReceiver.this.m429xbff283ba(context, i, j);
                }
            });
            return;
        }
        String string4 = context.getString(R.string._2130844486_res_0x7f021b46);
        if (i == 101) {
            string3 = context.getString(R.string._2130844487_res_0x7f021b47);
        } else if (i == 102) {
            string3 = context.getString(R.string._2130844489_res_0x7f021b49);
        } else if (i == 103) {
            string3 = context.getString(R.string._2130844488_res_0x7f021b48);
        } else if (i == 201) {
            string3 = context.getString(R.string._2130844715_res_0x7f021c2b);
        } else {
            if (i == 502) {
                string = context.getString(R.string._2130844073_res_0x7f0219a9);
                string2 = context.getString(R.string.IDS_quick_app_ecg_analysis);
            } else if (i != 503) {
                LogUtil.h(TAG, "parsesContext detailType undefined: ", Integer.valueOf(i));
                return;
            } else {
                string = context.getString(R.string._2130844071_res_0x7f0219a7, 2);
                string2 = context.getString(R.string.IDS_quick_app_ecg_analysis);
            }
            str2 = string2;
            str3 = "electrocardiogram";
            str4 = string;
            sendServiceCardNotify(context, str4, i + "-" + j, str3, str2);
        }
        str2 = string4;
        str3 = PAGE_RETURN_CARD;
        str4 = string3;
        sendServiceCardNotify(context, str4, i + "-" + j, str3, str2);
    }

    /* renamed from: lambda$setServiceCardNotify$0$com-huawei-health-receiver-EcgPushReceiver, reason: not valid java name */
    /* synthetic */ void m429xbff283ba(Context context, int i, long j) {
        setAllMessageRead();
        getGiftCardMessage(context, i, j);
    }

    private void sendEcgStrapNotify(Context context) {
        setNotify(context, context.getString(R.string._2130838566_res_0x7f020426), context.getString(R.string._2130844073_res_0x7f0219a9), true);
    }

    private void sendServiceCardNotify(final Context context, final String str, final String str2, final String str3, final String str4) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.h(TAG, "sendServiceCardNotify content is null");
            return;
        }
        LogUtil.a(TAG, "sendServiceCardNotify start");
        final MessageCenterApi messageCenterApi = (MessageCenterApi) Services.c("PluginMessageCenter", MessageCenterApi.class);
        messageCenterApi.getMessageId(String.valueOf(19), MessageConstant.ECG_MEDAL_TYPE, new IBaseResponseCallback() { // from class: com.huawei.health.receiver.EcgPushReceiver$$ExternalSyntheticLambda0
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public final void d(int i, Object obj) {
                EcgPushReceiver.this.m428x83f8d959(str4, str, str2, str3, messageCenterApi, context, i, obj);
            }
        });
    }

    /* renamed from: lambda$sendServiceCardNotify$1$com-huawei-health-receiver-EcgPushReceiver, reason: not valid java name */
    /* synthetic */ void m428x83f8d959(String str, String str2, String str3, String str4, MessageCenterApi messageCenterApi, Context context, int i, Object obj) {
        if (i != 0 || obj == null) {
            LogUtil.h(TAG, "requestMessageId fail");
        } else if (obj instanceof String) {
            MessageObject builderMessage = builderMessage((String) obj, str, str2, str3, str4);
            messageCenterApi.insertMessage(builderMessage);
            messageCenterApi.showNotification(context, builderMessage);
        }
    }

    private MessageObject builderMessage(String str, String str2, String str3, String str4, String str5) {
        MessageObject messageObject = new MessageObject();
        messageObject.setMsgType(2);
        messageObject.setPosition(3);
        messageObject.setMsgPosition(11);
        messageObject.setDetailUri(ECG_DETAIL_URI + str5);
        messageObject.setExpireTime(0L);
        messageObject.setReadFlag(0);
        messageObject.setMsgId(str);
        messageObject.setModule(String.valueOf(19));
        messageObject.setType(MessageConstant.ECG_MEDAL_TYPE);
        messageObject.setMsgTitle(str2);
        messageObject.setMsgContent(str3);
        messageObject.setMsgFrom(str4);
        messageObject.setCreateTime(System.currentTimeMillis());
        return messageObject;
    }

    private void getGiftCardMessage(Context context, int i, long j) {
        LogUtil.a(TAG, "getGiftCardMessage start detailType ", Integer.valueOf(i));
        EcgGiftCardResponse d = jbs.a(context).d(new EcgGiftCardRequest(j, i));
        if (d == null || TextUtils.isEmpty(d.getPushInfo())) {
            LogUtil.h(TAG, "getGiftCardMessage response is null or donateId is empty");
            return;
        }
        LogUtil.c(TAG, "getGiftCardMessage response ", d.toString());
        String pushInfo = d.getPushInfo();
        if (i == 302 || i == 303) {
            setNotifyContent(i, pushInfo, "", 0L);
        } else {
            getUserNicknameFromSocial(i, pushInfo, d.getDonorId(), d.getExpireTime());
        }
    }

    private void getUserNicknameFromSocial(final int i, final String str, long j, final long j2) {
        ((FamilyHealthZoneApi) Services.a("PluginHealthZone", FamilyHealthZoneApi.class)).requestFindUserInfo(2, String.valueOf(j), new UserInfoCallback<exh.b>() { // from class: com.huawei.health.receiver.EcgPushReceiver.1
            @Override // com.huawei.health.pluginhealthzone.UserInfoCallback
            public void infoCallback(exh.b bVar) {
                LogUtil.h(EcgPushReceiver.TAG, "getUserNicknameFromSocial infoCallback");
                EcgPushReceiver.this.setNotifyContent(i, str, bVar.d(), j2);
            }

            @Override // com.huawei.health.pluginhealthzone.UserInfoCallback
            public void errorCallback(int i2) {
                LogUtil.h(EcgPushReceiver.TAG, "getUserNicknameFromSocial errorCallback");
            }
        });
    }

    public void setNotifyContent(int i, String str, String str2, long j) {
        String string;
        Context context = BaseApplication.getContext();
        String str3 = i + "-" + str + "-" + str2;
        if (i == 301) {
            if (TextUtils.isEmpty(str2)) {
                string = context.getString(R.string._2130845087_res_0x7f021d9f);
            } else {
                string = context.getString(R.string._2130844716_res_0x7f021c2c, str2);
            }
        } else if (i == 302) {
            string = context.getString(R.string._2130844720_res_0x7f021c30);
        } else if (i == 303) {
            string = context.getString(R.string._2130844721_res_0x7f021c31);
        } else if (i == 304) {
            if (TextUtils.isEmpty(str2)) {
                string = context.getString(R.string._2130845090_res_0x7f021da2);
            } else {
                string = context.getString(R.string._2130844724_res_0x7f021c34, str2);
            }
        } else {
            String format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH).format(Long.valueOf(j));
            if (TextUtils.isEmpty(str2)) {
                string = context.getString(R.string._2130845089_res_0x7f021da1, format);
            } else {
                string = context.getString(R.string._2130844723_res_0x7f021c33, str2, format);
            }
        }
        sendServiceCardNotify(context, string, str3, PAGE_DONATE + str, context.getString(R.string._2130844486_res_0x7f021b46));
    }

    private void setAllMessageRead() {
        int e;
        MessageCenterApi messageCenterApi = (MessageCenterApi) Services.c("PluginMessageCenter", MessageCenterApi.class);
        List<MessageObject> messages = messageCenterApi.getMessages(String.valueOf(19), MessageConstant.ECG_MEDAL_TYPE);
        for (int i = 0; i < messages.size(); i++) {
            MessageObject messageObject = messages.get(i);
            if (messageObject != null && messageObject.getMsgFrom() != null && messageObject.getReadFlag() != 1 && (e = nsn.e(messageObject.getMsgFrom().split("-")[0])) >= 301 && e <= 305) {
                messageCenterApi.setMessageRead(messageObject.getMsgId());
            }
        }
    }

    /* loaded from: classes3.dex */
    static class EcgMsgPushBean {
        private int pushType = 0;
        private String msgContext = "";

        private EcgMsgPushBean() {
        }

        public String toString() {
            return "EcgMsgPushBean{, pushType='" + this.pushType + "', msgContext='" + this.msgContext + "'}";
        }
    }

    /* loaded from: classes3.dex */
    static class MsgContext {
        private int detailType;
        private long notifyTime;

        private MsgContext() {
        }
    }
}
