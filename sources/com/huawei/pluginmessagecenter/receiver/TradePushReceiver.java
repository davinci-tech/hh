package com.huawei.pluginmessagecenter.receiver;

import android.content.Context;
import android.content.res.Resources;
import android.text.TextUtils;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.huawei.health.R;
import com.huawei.health.health.utils.functionsetcard.reader.FunctionSetBeanReader;
import com.huawei.health.messagecenter.api.MessageCenterApi;
import com.huawei.health.messagecenter.model.IpushBase;
import com.huawei.health.messagecenter.model.MessageObject;
import com.huawei.health.messagecenter.model.MessagePushBean;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import defpackage.ixx;
import health.compact.a.LogUtil;
import health.compact.a.Services;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/* loaded from: classes6.dex */
public class TradePushReceiver implements IpushBase {
    private static final int REFUNDS_TYPE = 7001;
    private static final String TAG = "TradePushReceiver";

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
            } else if (messagePushBean.getPushType() == REFUNDS_TYPE) {
                LogUtil.c(TAG, "is trade message");
                doBiEvent();
                ((MessageCenterApi) Services.c("PluginMessageCenter", MessageCenterApi.class)).showNotification(BaseApplication.getContext(), builderMessage(messagePushBean.getMsgContext()));
            }
        } catch (JsonSyntaxException unused) {
            LogUtil.e(TAG, "processPushMsg JsonSyntaxException");
        }
    }

    private MessageObject builderMessage(String str) {
        Resources resources = BaseApplication.getContext().getResources();
        MessageObject messageObject = new MessageObject();
        messageObject.setDetailUri("messagecenter://trade?orderCode=" + str);
        messageObject.setPosition(2);
        messageObject.setReadFlag(0);
        messageObject.setExpireTime(0L);
        messageObject.setMsgId(String.valueOf(REFUNDS_TYPE));
        messageObject.setMsgTitle(resources.getString(R.string.IDS_app_name_health));
        messageObject.setMsgContent(resources.getString(R.string._2130844799_res_0x7f021c7f));
        messageObject.setCreateTime(System.currentTimeMillis());
        return messageObject;
    }

    private void doBiEvent() {
        HashMap hashMap = new HashMap();
        hashMap.put("click", 1);
        hashMap.put("type", 1);
        hashMap.put(FunctionSetBeanReader.BI_ELEMENT, 1);
        ixx.d().d(com.huawei.haf.application.BaseApplication.e(), AnalyticsValue.TRADE_REFUND_MESSAGE.value(), hashMap, 0);
    }
}
