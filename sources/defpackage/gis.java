package defpackage;

import android.content.Context;
import android.graphics.Point;
import android.os.Build;
import com.huawei.haf.application.BaseApplication;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.networkclient.IRequest;
import com.huawei.openalliance.ad.constant.Constants;
import health.compact.a.CommonUtil;
import health.compact.a.GRSManager;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes4.dex */
public class gis implements IRequest {
    public Map<String, String> e(Map<String, String> map) {
        if (map == null) {
            map = new HashMap<>();
        }
        Context e = BaseApplication.e();
        map.put("firmware", Build.VERSION.RELEASE);
        if (nsn.ae(e)) {
            map.put("screen", "1200*1920");
        } else {
            map.put("screen", nsn.n() + "*" + nsn.j());
        }
        Point cLb_ = nsn.cLb_();
        map.put("realscreen", cLb_.x + "*" + cLb_.y);
        map.put("locale", CommonUtil.u().replace(Constants.LINK, "_"));
        map.put("isoCode", LoginInit.getInstance(e).getAccountInfo(1010));
        map.put("mcc", CommonUtil.i(e));
        map.put("type", "5");
        return map;
    }

    @Override // com.huawei.networkclient.IRequest
    public String getUrl() {
        return GRSManager.a(BaseApplication.e()).getUrl("domainThemeCloud") + "/servicesupport/theme/tis/service/getFrontParam.do";
    }
}
