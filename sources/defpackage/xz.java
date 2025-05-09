package defpackage;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.huawei.android.appbundle.remote.OnBinderDiedListener;
import com.huawei.android.appbundle.splitinstall.protocol.SplitInstallServiceProxy;
import com.huawei.haf.bundle.InstallSessionState;
import com.huawei.haf.common.security.SecurityConstant;
import com.huawei.hmf.tasks.Task;
import com.huawei.hmf.tasks.TaskCompletionSource;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/* loaded from: classes8.dex */
final class xz implements OnBinderDiedListener {
    static final xh e = new xh("SplitInstallService");

    /* renamed from: a, reason: collision with root package name */
    final String f17752a;
    final xb<SplitInstallServiceProxy> b;
    private final Context c;

    xz(Context context) {
        this(context, context.getPackageName());
    }

    private xz(Context context, String str) {
        this.c = context;
        this.f17752a = str;
        Intent intent = new Intent("com.huawei.android.appbundle.splitinstall.BIND_SPLIT_INSTALL_SERVICE");
        intent.setPackage(str);
        xb<SplitInstallServiceProxy> xbVar = new xb<>(context.getApplicationContext(), e, "SplitInstallService", intent, xy.f17751a);
        this.b = xbVar;
        xbVar.d(this);
    }

    Task<Integer> c(List<String> list) {
        e.d("startInstall(%s)", list);
        TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        this.b.a(new ya(this, taskCompletionSource, list, taskCompletionSource));
        return taskCompletionSource.getTask();
    }

    Task<Void> a(List<String> list) {
        e.d("deferredInstall(%s)", list);
        TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        this.b.a(new xp(this, taskCompletionSource, list, taskCompletionSource));
        return taskCompletionSource.getTask();
    }

    Task<Void> e(List<String> list) {
        e.d("deferredUninstall(%s)", list);
        TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        this.b.a(new xo(this, taskCompletionSource, list, taskCompletionSource));
        return taskCompletionSource.getTask();
    }

    Task<InstallSessionState> d(int i) {
        e.d("getSessionState(%d)", Integer.valueOf(i));
        TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        this.b.a(new xl(this, taskCompletionSource, i, taskCompletionSource));
        return taskCompletionSource.getTask();
    }

    Task<List<InstallSessionState>> e() {
        e.d("getSessionStates", new Object[0]);
        TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        this.b.a(new xm(this, taskCompletionSource, taskCompletionSource));
        return taskCompletionSource.getTask();
    }

    Task<Void> b(int i) {
        e.d("cancelInstall(%d)", Integer.valueOf(i));
        TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        this.b.a(new xn(this, taskCompletionSource, i, taskCompletionSource));
        return taskCompletionSource.getTask();
    }

    static List<Bundle> b(Collection<String> collection) {
        ArrayList arrayList = new ArrayList(collection.size());
        for (String str : collection) {
            Bundle bundle = new Bundle();
            bundle.putString("module_name", str);
            arrayList.add(bundle);
        }
        return arrayList;
    }

    static Bundle en_() {
        Bundle bundle = new Bundle();
        bundle.putInt("playcore_version_code", 10010);
        return bundle;
    }

    @Override // com.huawei.android.appbundle.remote.OnBinderDiedListener
    public void onBinderDied() {
        e.d("onBinderDied", new Object[0]);
        Intent intent = new Intent();
        intent.setPackage(this.f17752a);
        intent.setAction("com.huawei.android.appbundle.splitinstall.receiver.SplitInstallUpdateIntentService");
        intent.putExtra("session_state", em_());
        intent.addFlags(1073741824);
        intent.addFlags(2097152);
        this.c.sendBroadcast(intent, SecurityConstant.d);
    }

    private Bundle em_() {
        ArrayList<String> arrayList = new ArrayList<>();
        Bundle bundle = new Bundle();
        bundle.putInt("session_id", -1);
        bundle.putInt("status", 6);
        bundle.putInt("error_code", -9);
        bundle.putStringArrayList("module_names", arrayList);
        bundle.putStringArrayList("download_module_names", arrayList);
        bundle.putLong("total_bytes_to_download", 0L);
        bundle.putLong("bytes_downloaded", 0L);
        return bundle;
    }
}
