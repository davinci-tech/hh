package com.huawei.hms.hihealth;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* loaded from: classes4.dex */
public interface aabi extends IInterface {
    String aab(String str, com.huawei.hms.hihealth.options.aaba aabaVar) throws RemoteException;

    String aabb(String str) throws RemoteException;

    public static abstract class aab extends Binder implements aabi {

        /* renamed from: com.huawei.hms.hihealth.aabi$aab$aab, reason: collision with other inner class name */
        static class C0104aab implements aabi {
            private IBinder aab;

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.aab;
            }

            @Override // com.huawei.hms.hihealth.aabi
            public String aabb(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.huawei.hms.hihealth.ICommonApiCall");
                    obtain.writeString(str);
                    this.aab.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readString();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hms.hihealth.aabi
            public String aab(String str, com.huawei.hms.hihealth.options.aaba aabaVar) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.huawei.hms.hihealth.ICommonApiCall");
                    obtain.writeString(str);
                    obtain.writeStrongInterface(aabaVar);
                    this.aab.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readString();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            C0104aab(IBinder iBinder) {
                this.aab = iBinder;
            }
        }

        public static aabi aab(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.huawei.hms.hihealth.ICommonApiCall");
            return (queryLocalInterface == null || !(queryLocalInterface instanceof aabi)) ? new C0104aab(iBinder) : (aabi) queryLocalInterface;
        }
    }
}
