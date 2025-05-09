package defpackage;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import com.huawei.haf.application.BaseApplication;
import com.huawei.health.messagecenter.model.IpushBase;
import com.huawei.health.messagecenter.model.MessagePushBean;
import com.huawei.pluginhealthzone.interactors.HealthZonePushReceiver;
import health.compact.a.BroadcastManagerUtil;
import health.compact.a.LogUtil;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.Collections;
import java.util.List;

/* loaded from: classes6.dex */
public class mrm implements IpushBase {
    @Override // com.huawei.health.messagecenter.model.IpushBase
    public List<String> getPushType() {
        return Collections.singletonList(String.valueOf(9001));
    }

    @Override // com.huawei.health.messagecenter.model.IpushBase
    public void processPushMsg(Context context, String str) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.a("TC_PushReceiver", "processPushMsg, message is empty");
            return;
        }
        ReleaseLogUtil.e("TC_PushReceiver", "processPushMsg, message = ", str);
        Gson gson = new Gson();
        MessagePushBean messagePushBean = (MessagePushBean) gson.fromJson(str, MessagePushBean.class);
        if (messagePushBean == null) {
            LogUtil.a("TC_PushReceiver", "processPushMsg handleMsg is null");
            return;
        }
        b bVar = (b) gson.fromJson(messagePushBean.getMsgContext(), b.class);
        if (bVar == null) {
            LogUtil.a("TC_PushReceiver", "msgContext is null");
            return;
        }
        ReleaseLogUtil.e("TC_PushReceiver", "messagePushBean:", messagePushBean.getMsgContext(), " msgContext:", Integer.valueOf(bVar.c), Integer.valueOf(bVar.f15130a));
        String d = d(bVar.c);
        if (TextUtils.isEmpty(d)) {
            return;
        }
        Intent intent = new Intent();
        intent.setClassName(BaseApplication.d(), "com.huawei.health.threeCircle.remind.receiver.SportRemindReceiver");
        intent.putExtra("remindType", d);
        intent.putExtra("reportDate", bVar.f15130a);
        BroadcastManagerUtil.bFG_(BaseApplication.e(), intent);
    }

    private String d(int i) {
        if (i == 201) {
            return "WeekSummary";
        }
        if (i == 202) {
            return "MonthSummary";
        }
        if (i == 301) {
            return "ActiveWeek";
        }
        if (i == 302) {
            return "PerfectMonth";
        }
        LogUtil.a("TC_PushReceiver", "changePushTypeToRequireType other remindType", Integer.valueOf(i));
        return "";
    }

    public static class b {

        /* renamed from: a, reason: collision with root package name */
        @SerializedName("reportDate")
        private int f15130a;

        @SerializedName(HealthZonePushReceiver.DETAIL_TYPE)
        private int c;

        public String toString() {
            return "MsgContext{detailType=" + this.c + ", reportDate='" + this.f15130a + '}';
        }
    }
}
