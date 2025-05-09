package com.huawei.pluginmessagecenter.receiver;

import android.content.Context;
import android.text.TextUtils;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.huawei.haf.application.BaseApplication;
import com.huawei.health.messagecenter.api.MessageCenterApi;
import com.huawei.health.messagecenter.model.IpushBase;
import com.huawei.health.messagecenter.model.MessageObject;
import com.huawei.health.messagecenter.model.MessagePushBean;
import com.huawei.health.vip.api.VipApi;
import com.huawei.health.vip.datatypes.MemberMessage;
import com.huawei.health.vip.datatypes.VipMessageInfo;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import defpackage.gpn;
import defpackage.ixx;
import defpackage.koq;
import health.compact.a.LogUtil;
import health.compact.a.Services;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/* loaded from: classes6.dex */
public class MemberPushReceiver implements IpushBase {
    private static final int FIRST_POSITION = 0;
    private static final int REFUNDS_TYPE = 7002;
    private static final String TAG = "MemberPushReciver";

    @Override // com.huawei.health.messagecenter.model.IpushBase
    public List<String> getPushType() {
        return Arrays.asList(String.valueOf(REFUNDS_TYPE));
    }

    @Override // com.huawei.health.messagecenter.model.IpushBase
    public void processPushMsg(Context context, String str) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.a(TAG, "processPushMsg Error PushMsg is Empty");
            return;
        }
        try {
            LogUtil.d(TAG, "handleMsg get message", str);
            MessagePushBean messagePushBean = (MessagePushBean) new Gson().fromJson(str, MessagePushBean.class);
            if (messagePushBean == null) {
                LogUtil.a(TAG, "processPushMsg handleMsg is null");
                return;
            }
            if (messagePushBean.getPushType() == REFUNDS_TYPE) {
                LogUtil.c(TAG, "is refunds message");
                String msgContext = messagePushBean.getMsgContext();
                if (TextUtils.isEmpty(msgContext)) {
                    LogUtil.c(TAG, "messageId is empty");
                } else {
                    getVipMessage(msgContext);
                }
            }
        } catch (JsonSyntaxException unused) {
            LogUtil.e(TAG, "processPushMsg JsonSyntaxException");
        }
    }

    private void getVipMessage(String str) {
        VipApi vipApi = (VipApi) Services.a("vip", VipApi.class);
        if (vipApi == null) {
            LogUtil.a(TAG, "getVipMessages VipApi is null");
        } else {
            vipApi.getVipMessage(str, new IBaseResponseCallback() { // from class: com.huawei.pluginmessagecenter.receiver.MemberPushReceiver.2
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public void d(int i, Object obj) {
                    if (i == 0 && (obj instanceof MemberMessage)) {
                        MemberPushReceiver.this.handleVipMessage((MemberMessage) obj);
                    } else {
                        LogUtil.a(MemberPushReceiver.TAG, "getVipMessage fail:", Integer.valueOf(i));
                    }
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleVipMessage(MemberMessage memberMessage) {
        LogUtil.c(TAG, "enter handleVipMessage");
        VipMessageInfo b = gpn.b(memberMessage.getInfoValue());
        if (b == null || TextUtils.isEmpty(gpn.c(b))) {
            LogUtil.a(TAG, "vipMessageInfo == null or operateType not support");
            return;
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add(memberMessage);
        List<MessageObject> e = gpn.e((List<MemberMessage>) arrayList, true);
        if (koq.c(e)) {
            MessageObject messageObject = e.get(0);
            messageObject.setMsgId("l" + new SecureRandom().nextInt(1000));
            ((MessageCenterApi) Services.c("PluginMessageCenter", MessageCenterApi.class)).showNotification(BaseApplication.e(), messageObject);
            doBiEvent(messageObject.getMsgTitle());
        }
    }

    private void doBiEvent(String str) {
        HashMap hashMap = new HashMap();
        hashMap.put("click", 1);
        hashMap.put("title", str);
        ixx.d().d(BaseApplication.e(), AnalyticsValue.VIP_MESSAGE.value(), hashMap, 0);
    }
}
