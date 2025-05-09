package defpackage;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.health.messagecenter.model.IpushBase;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.AuthorizationUtils;
import health.compact.a.Utils;
import java.util.List;

/* loaded from: classes.dex */
public class qbl implements IpushBase {
    @Override // com.huawei.health.messagecenter.model.IpushBase
    public List<String> getPushType() {
        return null;
    }

    @Override // com.huawei.health.messagecenter.model.IpushBase
    public void processPushMsg(Context context, String str) {
        LogUtil.a("GuidePageMessageReceiver", "message:", str);
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("GuidePageMessageReceiver", "message is emyty");
            return;
        }
        if (nsn.l() || nsn.ae(BaseApplication.getContext())) {
            LogUtil.h("GuidePageMessageReceiver", "isMateX");
            return;
        }
        if (!Utils.i()) {
            LogUtil.h("GuidePageMessageReceiver", "is not allow");
        } else if (!AuthorizationUtils.a(BaseApplication.getContext())) {
            LogUtil.h("GuidePageMessageReceiver", "not Available or not Authorize");
        } else {
            qbd.b(str);
        }
    }
}
