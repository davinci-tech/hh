package defpackage;

import android.content.Intent;
import android.text.TextUtils;
import com.huawei.appgallery.coreservice.api.ConnectConfig;

/* loaded from: classes2.dex */
public class aex {
    private static String e(ConnectConfig connectConfig) {
        return (connectConfig == null || TextUtils.isEmpty(connectConfig.getConnectServiceAction())) ? "com.huawei.appmarket.service.intent.ACTION_CORE_SERVICE" : connectConfig.getConnectServiceAction();
    }

    public static Intent gK_(String str, ConnectConfig connectConfig) {
        Intent intent = new Intent(e(connectConfig));
        intent.setPackage(str);
        return intent;
    }
}
