package defpackage;

import android.content.Context;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.hwcloudmodel.callback.IHttpOperationResult;
import com.huawei.hwcloudmodel.utils.NspClient;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.up.utils.NSPException;
import java.util.Map;

/* loaded from: classes5.dex */
public class jcb {
    private Context d;

    jcb(Context context) {
        this.d = context;
    }

    public void a(String str, Map<String, Object> map, IHttpOperationResult iHttpOperationResult) {
        d(str, map, 30, 30, iHttpOperationResult);
    }

    private void d(final String str, final Map<String, Object> map, final int i, final int i2, final IHttpOperationResult iHttpOperationResult) {
        LogUtil.a("HiH_HwDataRequest", "call(),service=", str);
        final NspClient nspClient = new NspClient(this.d);
        ThreadPoolManager.d().c("HiH_HwDataRequest", new Runnable() { // from class: jcb.2
            @Override // java.lang.Runnable
            public void run() {
                try {
                    iHttpOperationResult.operationResult(nspClient.call(str, map, i, i2, 1));
                } catch (NSPException e) {
                    LogUtil.b("HiH_HwDataRequest", "NSPException", Integer.valueOf(e.getCode()), e.getMessage());
                    iHttpOperationResult.exception(e.getCode(), e);
                }
            }
        });
    }
}
