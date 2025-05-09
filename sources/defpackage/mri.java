package defpackage;

import android.content.Context;
import android.text.TextUtils;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.annotations.SerializedName;
import com.huawei.health.messagecenter.api.MessageCenterApi;
import com.huawei.health.messagecenter.model.CommonUtil;
import com.huawei.health.messagecenter.model.IpushBase;
import com.huawei.health.messagecenter.model.MessageConstant;
import com.huawei.health.messagecenter.model.MessageObject;
import com.huawei.health.messagecenter.model.MessagePushBean;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.pluginachievement.manager.model.ParsedFieldTag;
import com.huawei.pluginhealthzone.interactors.HealthZonePushReceiver;
import com.huawei.ui.commonui.R$string;
import health.compact.a.LogUtil;
import health.compact.a.Services;
import java.util.Arrays;
import java.util.List;

/* loaded from: classes6.dex */
public class mri implements IpushBase {
    @Override // com.huawei.health.messagecenter.model.IpushBase
    public List<String> getPushType() {
        return Arrays.asList(String.valueOf(32));
    }

    @Override // com.huawei.health.messagecenter.model.IpushBase
    public void processPushMsg(Context context, String str) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.a("AchievementPushReceiver", "processPushMsg, message is empty");
            return;
        }
        try {
            LogUtil.c("AchievementPushReceiver", "processPushMsg, message = ", str);
            Gson gson = new Gson();
            MessagePushBean messagePushBean = (MessagePushBean) gson.fromJson(str, MessagePushBean.class);
            if (messagePushBean != null && !TextUtils.isEmpty(messagePushBean.getMsgContext())) {
                e eVar = (e) gson.fromJson(messagePushBean.getMsgContext(), e.class);
                if (eVar == null) {
                    LogUtil.a("AchievementPushReceiver", "msgContext is null");
                    return;
                } else {
                    if (messagePushBean.getPushType() == 32 && eVar.f15129a == 101) {
                        d(eVar.b, eVar.e, context);
                        return;
                    }
                    return;
                }
            }
            LogUtil.a("AchievementPushReceiver", "bean is null or msgContext is empty");
        } catch (JsonSyntaxException unused) {
            LogUtil.e("AchievementPushReceiver", "processPushMsg JsonSyntaxException");
        }
    }

    private void d(String str, int i, final Context context) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.a("AchievementPushReceiver", "resultId is empty");
            return;
        }
        final String str2 = "huaweihealth://huaweihealth.app/openwith?address=com.huawei.health.h5.physical-fitness-test?h5pro=true&path=certificateDetail&teamType=" + i + "&resultId=" + str;
        CommonUtil.setNotification(context, context.getResources().getString(R$string.IDS_national_sports_certificate), context.getResources().getString(R$string.IDS_your_certificate_has_issued), str2 + "&from=3");
        final MessageCenterApi messageCenterApi = (MessageCenterApi) Services.c("PluginMessageCenter", MessageCenterApi.class);
        messageCenterApi.getMessageId(MessageConstant.CERTIFICATE, MessageConstant.CERTIFICATE_TYPE, new IBaseResponseCallback() { // from class: mre
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public final void d(int i2, Object obj) {
                mri.b(context, str2, messageCenterApi, i2, obj);
            }
        });
    }

    static /* synthetic */ void b(Context context, String str, MessageCenterApi messageCenterApi, int i, Object obj) {
        LogUtil.c("AchievementPushReceiver", "errorCode = ", Integer.valueOf(i));
        if (i == 0 && (obj instanceof String)) {
            MessageObject messageObject = new MessageObject();
            messageObject.setMsgId((String) obj);
            messageObject.setModule(MessageConstant.CERTIFICATE);
            messageObject.setType(MessageConstant.CERTIFICATE_TYPE);
            messageObject.setPosition(1);
            messageObject.setMsgPosition(11);
            messageObject.setReadFlag(0);
            messageObject.setMsgContent(context.getResources().getString(R$string.IDS_congratulations_national_sports_certificates_issued));
            messageObject.setMsgTitle(context.getResources().getString(R$string.IDS_national_sports_certificate));
            messageObject.setDetailUri(str + "&from=2");
            messageObject.setCreateTime(System.currentTimeMillis());
            if (LoginInit.getInstance(BaseApplication.getContext()) != null) {
                messageObject.setHuid(LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1011));
            }
            messageCenterApi.insertMessage(messageObject);
        }
    }

    static class e {

        /* renamed from: a, reason: collision with root package name */
        @SerializedName(HealthZonePushReceiver.DETAIL_TYPE)
        private int f15129a;

        @SerializedName(ParsedFieldTag.NPES_RESULT_ID)
        private String b;

        @SerializedName(ParsedFieldTag.NPES_TEAM_TYPE)
        private int e;

        private e() {
        }

        public String toString() {
            return "MsgContext{detailType=" + this.f15129a + ", resultId='" + this.b + "', teamType=" + this.e + '}';
        }
    }
}
