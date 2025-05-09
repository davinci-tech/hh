package defpackage;

import com.huawei.hmf.tasks.Task;
import com.huawei.hmf.tasks.Tasks;
import com.huawei.wearengine.auth.AuthCallback;
import com.huawei.wearengine.auth.AuthListener;
import com.huawei.wearengine.auth.Permission;
import java.util.concurrent.Callable;

/* loaded from: classes9.dex */
public class toa {
    private static volatile toa d;
    private tny c = tny.c();

    private toa() {
    }

    public static toa d() {
        if (d == null) {
            synchronized (toa.class) {
                if (d == null) {
                    d = new toa();
                }
            }
        }
        return d;
    }

    public Task<Void> a(final AuthCallback authCallback, final Permission... permissionArr) {
        final AuthListener.Stub stub = new AuthListener.Stub() { // from class: toa.4
            @Override // com.huawei.wearengine.auth.AuthListener
            public void onOk(Permission[] permissionArr2) {
                authCallback.onOk(permissionArr2);
            }

            @Override // com.huawei.wearengine.auth.AuthListener
            public void onCancel() {
                authCallback.onCancel();
            }
        };
        return Tasks.callInBackground(new Callable<Void>() { // from class: toa.5
            @Override // java.util.concurrent.Callable
            /* renamed from: e, reason: merged with bridge method [inline-methods] */
            public Void call() {
                top.a(authCallback, "AuthCallback can not be null!");
                top.a(permissionArr, "Permissions can not be null!");
                if (permissionArr.length <= 6) {
                    int d2 = toa.this.c.d(stub, permissionArr);
                    if (d2 == 0) {
                        return null;
                    }
                    throw new tnx(d2);
                }
                throw new tnx(5);
            }
        });
    }

    public Task<Boolean> a(final Permission permission) {
        return Tasks.callInBackground(new Callable<Boolean>() { // from class: toa.2
            @Override // java.util.concurrent.Callable
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public Boolean call() {
                top.a(permission, "Permission can not be null!");
                return Boolean.valueOf(toa.this.c.d(permission));
            }
        });
    }
}
