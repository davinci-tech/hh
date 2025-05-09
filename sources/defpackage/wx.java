package defpackage;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* loaded from: classes8.dex */
public class wx implements IInterface {

    /* renamed from: a, reason: collision with root package name */
    private final String f17738a;
    private final IBinder d;

    protected wx(IBinder iBinder, String str) {
        this.d = iBinder;
        this.f17738a = str;
    }

    @Override // android.os.IInterface
    public IBinder asBinder() {
        return this.d;
    }

    protected final Parcel dY_() {
        Parcel obtain = Parcel.obtain();
        obtain.writeInterfaceToken(this.f17738a);
        return obtain;
    }

    protected final void dZ_(int i, Parcel parcel) throws RemoteException {
        Parcel obtain = Parcel.obtain();
        try {
            this.d.transact(i, parcel, obtain, 1);
            obtain.readException();
        } finally {
            obtain.recycle();
            parcel.recycle();
        }
    }
}
