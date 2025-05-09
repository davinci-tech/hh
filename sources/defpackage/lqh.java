package defpackage;

import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.networkclient.ProgressListener;
import health.compact.a.LogUtil;

/* loaded from: classes5.dex */
public class lqh implements ProgressListener {
    @Override // com.huawei.networkclient.ProgressListener
    public void onFinish(Object obj) {
    }

    @Override // com.huawei.networkclient.ProgressListener
    public void onProgress(long j, long j2, boolean z) {
    }

    @Override // com.huawei.networkclient.ProgressListener
    public void onFail(Throwable th) {
        LogUtil.e("NullProgressListener", "onFail", ExceptionUtils.d(th));
    }
}
