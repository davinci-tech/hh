package com.huawei.appmarket.framework.coreservice;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* loaded from: classes3.dex */
public interface b extends IInterface {
    void a(DataHolder dataHolder, com.huawei.appmarket.framework.coreservice.a aVar) throws RemoteException;

    public static abstract class a extends Binder implements b {

        /* renamed from: com.huawei.appmarket.framework.coreservice.b$a$a, reason: collision with other inner class name */
        static class C0044a implements b {
            public static b e;
            private IBinder c;

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.c;
            }

            @Override // com.huawei.appmarket.framework.coreservice.b
            public void a(DataHolder dataHolder, com.huawei.appmarket.framework.coreservice.a aVar) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.huawei.appmarket.framework.coreservice.IAppGalleryServiceTransport");
                    if (dataHolder != null) {
                        obtain.writeInt(1);
                        dataHolder.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeStrongBinder(aVar != null ? aVar.asBinder() : null);
                    if (this.c.transact(1, obtain, obtain2, 0) || a.a() == null) {
                        obtain2.readException();
                    } else {
                        a.a().a(dataHolder, aVar);
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            C0044a(IBinder iBinder) {
                this.c = iBinder;
            }
        }

        public static b a(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.huawei.appmarket.framework.coreservice.IAppGalleryServiceTransport");
            return (queryLocalInterface == null || !(queryLocalInterface instanceof b)) ? new C0044a(iBinder) : (b) queryLocalInterface;
        }

        public static b a() {
            return C0044a.e;
        }
    }
}
