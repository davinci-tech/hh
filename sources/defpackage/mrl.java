package defpackage;

import android.content.Context;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.huawei.haf.application.BaseApplication;
import com.huawei.health.messagecenter.model.IpushBase;
import com.huawei.health.messagecenter.model.MessageObject;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.pluginmessagecenter.manager.MCNotificationManager;
import health.compact.a.utils.StringUtils;
import java.util.Arrays;
import java.util.List;

/* loaded from: classes6.dex */
public class mrl implements IpushBase {
    @Override // com.huawei.health.messagecenter.model.IpushBase
    public List<String> getPushType() {
        return Arrays.asList("toWallet");
    }

    @Override // com.huawei.health.messagecenter.model.IpushBase
    public void processPushMsg(Context context, String str) {
        MessageObject d = d(str);
        if (d == null) {
            return;
        }
        LogUtil.a("WalletMarketMsgHandler", "send wallet market msg");
        MCNotificationManager.showNotification(BaseApplication.e(), d);
    }

    private MessageObject d(String str) {
        if (StringUtils.g(str)) {
            LogUtil.b("WalletMarketMsgHandler", "createNotifyMsg： msg cannot be null or empty.");
            return null;
        }
        try {
            mrn mrnVar = (mrn) new Gson().fromJson(str, mrn.class);
            if (!mrnVar.b()) {
                return null;
            }
            MessageObject messageObject = new MessageObject();
            try {
                messageObject.setType(mrnVar.d());
                messageObject.setMsgContent(mrnVar.e());
                messageObject.setDetailUri(mrnVar.a());
                messageObject.setMsgTitle(mrnVar.c());
                messageObject.setPosition(3);
                messageObject.setReadFlag(0);
                messageObject.setMsgId("123456");
                return messageObject;
            } catch (JsonSyntaxException unused) {
                return messageObject;
            }
        } catch (JsonSyntaxException unused2) {
            return null;
        }
    }
}
