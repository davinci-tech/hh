package com.huawei.hms.hihealth;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* loaded from: classes4.dex */
public interface aabh extends IInterface {

    public static abstract class aab extends Binder implements aabh {

        /* renamed from: com.huawei.hms.hihealth.aabh$aab$aab, reason: collision with other inner class name */
        static class C0103aab implements aabh {
            private IBinder aab;

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.aab;
            }

            public void aab(com.huawei.hms.hihealth.aab aabVar) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.huawei.hms.hihealth.IClientPoolAidlInterface");
                    obtain.writeStrongInterface(aabVar);
                    this.aab.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public IBinder aab(int i, String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.huawei.hms.hihealth.IClientPoolAidlInterface");
                    obtain.writeInt(i);
                    obtain.writeString(str);
                    this.aab.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readStrongBinder();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            C0103aab(IBinder iBinder) {
                this.aab = iBinder;
            }
        }

        public static aabh aab(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.huawei.hms.hihealth.IClientPoolAidlInterface");
            return (queryLocalInterface == null || !(queryLocalInterface instanceof aabh)) ? new C0103aab(iBinder) : (aabh) queryLocalInterface;
        }
    }
}
