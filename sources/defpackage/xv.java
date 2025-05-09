package defpackage;

import android.os.Bundle;
import com.huawei.android.appbundle.splitinstall.protocol.SplitInstallServiceCallback;
import com.huawei.haf.bundle.InstallException;
import com.huawei.hmf.tasks.TaskCompletionSource;
import java.util.List;

/* loaded from: classes8.dex */
class xv<T> extends SplitInstallServiceCallback {
    protected final TaskCompletionSource<T> b;
    private final xz e;

    xv(xz xzVar, TaskCompletionSource<T> taskCompletionSource) {
        this.e = xzVar;
        this.b = taskCompletionSource;
    }

    @Override // com.huawei.android.appbundle.splitinstall.protocol.SplitInstallServiceCallbackProxy
    public void onStartInstall(int i, Bundle bundle) {
        this.e.b.e();
        xz.e.d("onStartInstall(%d)", Integer.valueOf(i));
    }

    @Override // com.huawei.android.appbundle.splitinstall.protocol.SplitInstallServiceCallbackProxy
    public void onCompleteInstall(int i) {
        this.e.b.e();
        xz.e.d("onCompleteInstall(%d)", Integer.valueOf(i));
    }

    public void onCancelInstall(int i, Bundle bundle) {
        this.e.b.e();
        xz.e.d("onCancelInstall(%d)", Integer.valueOf(i));
    }

    public void onGetSession(int i, Bundle bundle) {
        this.e.b.e();
        xz.e.d("onGetSession(%d)", Integer.valueOf(i));
    }

    public void onDeferredUninstall(Bundle bundle) {
        this.e.b.e();
        xz.e.d("onDeferredUninstall", new Object[0]);
    }

    public void onDeferredInstall(Bundle bundle) {
        this.e.b.e();
        xz.e.d("onDeferredInstall", new Object[0]);
    }

    public void onGetSessionStates(List<Bundle> list) {
        this.e.b.e();
        xz.e.d("onGetSessionStates", new Object[0]);
    }

    @Override // com.huawei.android.appbundle.splitinstall.protocol.SplitInstallServiceCallbackProxy
    public final void onError(Bundle bundle) {
        this.e.b.e();
        int i = bundle.getInt("error_code");
        xz.e.d("onError(%d)", Integer.valueOf(i));
        this.b.setException(new InstallException(i));
    }
}
