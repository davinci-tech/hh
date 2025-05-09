package defpackage;

import android.os.Handler;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import android.util.Log;
import com.huawei.harmonyos.interwork.a;
import com.huawei.harmonyos.interwork.base.ability.IAbilityConnection;
import com.huawei.harmonyos.interwork.base.bundle.ElementName;
import java.util.HashSet;
import java.util.Set;

/* loaded from: classes3.dex */
final class bwj extends a.AbstractBinderC0048a {

    /* renamed from: a, reason: collision with root package name */
    private static Handler f538a;
    private static final Set<bwj> d = new HashSet();
    private final IAbilityConnection e;

    public static void a(Handler handler) {
        f538a = handler;
    }

    @Override // com.huawei.harmonyos.interwork.a.AbstractBinderC0048a, android.os.Binder
    public final boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        if (i == 1) {
            parcel.enforceInterface("harmonyos.abilityshell.DistributedConnection");
            a(parcel.readInt() != 0 ? ElementName.CREATOR.createFromParcel(parcel) : null, parcel.readStrongBinder(), parcel.readInt());
            return true;
        }
        if (i == 2) {
            parcel.enforceInterface("harmonyos.abilityshell.DistributedConnection");
            a(parcel.readInt() != 0 ? ElementName.CREATOR.createFromParcel(parcel) : null, parcel.readInt());
            return true;
        }
        if (i == 1598968902) {
            parcel2.writeString("harmonyos.abilityshell.DistributedConnection");
            return true;
        }
        return super.onTransact(i, parcel, parcel2, i2);
    }

    @Override // com.huawei.harmonyos.interwork.a
    public final void a(final ElementName elementName, final IBinder iBinder, final int i) throws RemoteException {
        Handler handler = f538a;
        if (handler == null) {
            Log.e("DefKitLib_Connection", "invalid handler when connect done.");
        } else {
            handler.post(new Runnable() { // from class: bwm
                @Override // java.lang.Runnable
                public final void run() {
                    bwj.this.Ad_(elementName, iBinder, i);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void Ad_(ElementName elementName, IBinder iBinder, int i) {
        this.e.onAbilityConnectDone(elementName, iBinder, i);
    }

    @Override // com.huawei.harmonyos.interwork.a
    public final void a(final ElementName elementName, final int i) throws RemoteException {
        Handler handler = f538a;
        if (handler == null) {
            Log.e("DefKitLib_Connection", "invalid handler when disconnect.");
        } else {
            handler.post(new Runnable() { // from class: bwg
                @Override // java.lang.Runnable
                public final void run() {
                    bwj.this.e(elementName, i);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void e(ElementName elementName, int i) {
        this.e.onAbilityDisconnectDone(elementName, i);
    }
}
