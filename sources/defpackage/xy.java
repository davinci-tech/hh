package defpackage;

import android.os.IBinder;
import com.huawei.android.appbundle.remote.RemoteCall;
import com.huawei.android.appbundle.splitinstall.protocol.SplitInstallServiceHolder;
import com.huawei.android.appbundle.splitinstall.protocol.SplitInstallServiceProxy;

/* loaded from: classes8.dex */
public class xy implements RemoteCall<SplitInstallServiceProxy> {

    /* renamed from: a, reason: collision with root package name */
    static final RemoteCall f17751a = new xy();

    private xy() {
    }

    @Override // com.huawei.android.appbundle.remote.RemoteCall
    /* renamed from: ep_, reason: merged with bridge method [inline-methods] */
    public SplitInstallServiceProxy asInterface(IBinder iBinder) {
        return SplitInstallServiceHolder.queryLocalInterface(iBinder);
    }
}
