package defpackage;

import com.huawei.hwcloudmodel.callback.GrsQueryCallback;
import health.compact.a.GRSManager;
import health.compact.a.Utils;
import health.compact.a.util.LogUtil;

/* loaded from: classes4.dex */
public class fev {

    public static class c {
        public static boolean b() {
            return Utils.o();
        }
    }

    public static class a {
        public static void a(String str, final GrsQueryCallback grsQueryCallback) {
            if (grsQueryCallback == null) {
                return;
            }
            GRSManager.a(arx.b()).e(str, new GrsQueryCallback() { // from class: fev.a.2
                @Override // com.huawei.hwcloudmodel.callback.GrsQueryCallback
                public void onCallBackSuccess(String str2) {
                    LogUtil.b("Suggestion_CommonUtilSug", "GRSManagerSug onCallBackSuccess url = ", str2);
                    if (str2 == null) {
                        LogUtil.e("Suggestion_CommonUtilSug", "GRSManagerSug onCallBackSuccess url == null");
                        GrsQueryCallback.this.onCallBackFail(-1);
                    } else if (str2.startsWith("http://") || str2.startsWith("https://")) {
                        GrsQueryCallback.this.onCallBackSuccess(str2);
                    } else {
                        LogUtil.e("Suggestion_CommonUtilSug", "GRSManagerSug onCallBackSuccess url not http:// or https://");
                        GrsQueryCallback.this.onCallBackFail(-99);
                    }
                }

                @Override // com.huawei.hwcloudmodel.callback.GrsQueryCallback
                public void onCallBackFail(int i) {
                    LogUtil.e("Suggestion_CommonUtilSug", "GRSManagerSug onCallBackFail errorCode = ", Integer.valueOf(i));
                    GrsQueryCallback.this.onCallBackFail(i);
                }
            });
        }
    }
}
