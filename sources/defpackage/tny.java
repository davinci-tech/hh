package defpackage;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.text.TextUtils;
import com.huawei.wearengine.AuthManager;
import com.huawei.wearengine.WearEngineBinderClient;
import com.huawei.wearengine.WearEngineClientInner;
import com.huawei.wearengine.auth.AuthListener;
import com.huawei.wearengine.auth.Permission;
import java.lang.ref.WeakReference;
import java.util.Arrays;

/* loaded from: classes9.dex */
public class tny implements WearEngineBinderClient {
    private static volatile tny b;
    private final Object d = new Object();

    /* renamed from: a, reason: collision with root package name */
    private final String[] f17272a = {Permission.MOTION_SENSOR.getName(), Permission.WEAR_USER_STATUS.getName(), Permission.DEVICE_SN.getName()};
    private IBinder.DeathRecipient c = new IBinder.DeathRecipient() { // from class: tny.5
        @Override // android.os.IBinder.DeathRecipient
        public void binderDied() {
            tov.b("AuthServiceProxy", "binderDied enter");
            if (tny.this.e != null) {
                tny.this.e.asBinder().unlinkToDeath(tny.this.c, 0);
                tny.this.e = null;
            }
        }
    };
    private volatile AuthManager e = null;

    private tny() {
        b();
    }

    private void e() throws RemoteException {
        synchronized (this.d) {
            if (this.e == null) {
                WearEngineClientInner.c().d();
                IBinder fcR_ = WearEngineClientInner.c().fcR_(5);
                if (fcR_ == null) {
                    tov.c("AuthServiceProxy", "binder == null");
                    throw new tnx(2);
                }
                this.e = AuthManager.Stub.asInterface(fcR_);
                this.e.asBinder().linkToDeath(this.c, 0);
            }
        }
    }

    public static tny c() {
        if (b == null) {
            synchronized (tny.class) {
                if (b == null) {
                    b = new tny();
                }
            }
        }
        return b;
    }

    public int d(AuthListener authListener, Permission[] permissionArr) {
        try {
            e();
            if (!d(permissionArr)) {
                throw new tnx(14);
            }
            if (tra.a("auth_pre_start_auth")) {
                return c(authListener, permissionArr);
            }
            if (this.e != null) {
                return this.e.requestPermission(authListener, permissionArr);
            }
            return 6;
        } catch (RemoteException unused) {
            tov.d("AuthServiceProxy", "requestPermission RemoteException");
            return 12;
        } catch (IllegalStateException e) {
            throw tnx.e(e);
        }
    }

    public boolean d(Permission permission) {
        try {
            e();
            if (!d(new Permission[]{permission})) {
                throw new tnx(14);
            }
            if (this.e != null) {
                return this.e.checkPermission(permission);
            }
            throw new tnx(6);
        } catch (RemoteException unused) {
            tov.d("AuthServiceProxy", "checkPermission RemoteException");
            throw new tnx(12);
        } catch (IllegalStateException e) {
            throw tnx.e(e);
        }
    }

    @Override // com.huawei.wearengine.WearEngineBinderClient
    public void clearBinderProxy() {
        this.e = null;
        tov.b("AuthServiceProxy", "clearBinderProxy");
    }

    private void b() {
        WearEngineClientInner.c().a(new tob(new WeakReference(this)));
    }

    private int c(AuthListener authListener, Permission[] permissionArr) {
        if (permissionArr == null || permissionArr.length == 0) {
            tov.c("AuthServiceProxy", "startAuth permissions is null or empty");
            return 5;
        }
        try {
            e();
            if (this.e != null) {
                String preStartAuth = this.e.preStartAuth(authListener);
                if (TextUtils.isEmpty(preStartAuth)) {
                    tov.c("AuthServiceProxy", "startAuth targetJson is empty");
                    return 12;
                }
                try {
                    Context c = trr.c();
                    Intent ffm_ = trz.ffm_(c, preStartAuth, permissionArr);
                    if (ffm_ == null) {
                        return 12;
                    }
                    tov.a("AuthServiceProxy", "startAuth: start ClientHubActivity");
                    c.startActivity(ffm_);
                    return 0;
                } catch (ActivityNotFoundException unused) {
                    tov.d("AuthServiceProxy", "startAuth ActivityNotFoundException");
                    return 12;
                }
            }
            tov.c("AuthServiceProxy", "startAuth mAuthManager is null");
            return 6;
        } catch (RemoteException unused2) {
            tov.d("AuthServiceProxy", "requestPermission RemoteException");
            return 12;
        } catch (IllegalStateException e) {
            throw tnx.e(e);
        }
    }

    private boolean d(Permission[] permissionArr) {
        top.a(permissionArr, "isSupportPermissions permissions can not be null.");
        for (Permission permission : permissionArr) {
            top.a(permission, "isSupportPermissions permission can not be null.");
            if (Arrays.asList(this.f17272a).contains(permission.getName()) && !tra.a(permission.getName())) {
                return false;
            }
        }
        return true;
    }
}
