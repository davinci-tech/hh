package defpackage;

import com.huawei.haf.handler.ExtendHandler;
import com.huawei.haf.handler.HandlerCenter;
import com.huawei.hwcloudmodel.callback.WifiRequestCallBack;
import java.util.Map;

/* loaded from: classes5.dex */
public class jcd {
    private ExtendHandler d = null;

    public void d() {
        this.d = HandlerCenter.e("wifi_HwCloudManager");
    }

    public void b(final String str, final String str2, final Map<String, String> map, final WifiRequestCallBack wifiRequestCallBack) {
        this.d.postTask(new Runnable() { // from class: jcd.3
            @Override // java.lang.Runnable
            public void run() {
                wifiRequestCallBack.operationResult((String) lqi.d().d(str, map, str2, String.class));
            }
        });
    }
}
