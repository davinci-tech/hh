package com.huawei.hms.hihealth;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.huawei.hms.hihealth.data.DataType;
import com.huawei.hms.hihealth.options.DataTypeAddOptions;

/* loaded from: classes4.dex */
public interface aabp extends IInterface {
    DataType aab(DataTypeAddOptions dataTypeAddOptions) throws RemoteException;

    boolean aab() throws RemoteException;

    DataType aaba(String str) throws RemoteException;

    void aaba() throws RemoteException;

    boolean aaba(boolean z) throws RemoteException;

    String aabc() throws RemoteException;

    boolean aabc(String str) throws RemoteException;

    void aabd() throws RemoteException;

    boolean aabf() throws RemoteException;

    boolean aabg() throws RemoteException;

    com.huawei.hms.hihealth.data.aabc aabh() throws RemoteException;

    public static abstract class aab extends Binder implements aabp {

        /* renamed from: com.huawei.hms.hihealth.aabp$aab$aab, reason: collision with other inner class name */
        static class C0108aab implements aabp {
            private IBinder aab;

            @Override // com.huawei.hms.hihealth.aabp
            public DataType aab(DataTypeAddOptions dataTypeAddOptions) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.huawei.hms.hihealth.ISettingControllerManager");
                    if (dataTypeAddOptions != null) {
                        obtain.writeInt(1);
                        dataTypeAddOptions.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.aab.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0 ? DataType.CREATOR.createFromParcel(obtain2) : null;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hms.hihealth.aabp
            public DataType aaba(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.huawei.hms.hihealth.ISettingControllerManager");
                    obtain.writeString(str);
                    this.aab.transact(3, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0 ? DataType.CREATOR.createFromParcel(obtain2) : null;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hms.hihealth.aabp
            public com.huawei.hms.hihealth.data.aabc aabh() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.huawei.hms.hihealth.ISettingControllerManager");
                    this.aab.transact(11, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0 ? com.huawei.hms.hihealth.data.aabc.CREATOR.createFromParcel(obtain2) : null;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.aab;
            }

            @Override // com.huawei.hms.hihealth.aabp
            public boolean aabg() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.huawei.hms.hihealth.ISettingControllerManager");
                    this.aab.transact(7, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hms.hihealth.aabp
            public boolean aabf() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.huawei.hms.hihealth.ISettingControllerManager");
                    this.aab.transact(5, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hms.hihealth.aabp
            public void aabd() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.huawei.hms.hihealth.ISettingControllerManager");
                    this.aab.transact(4, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hms.hihealth.aabp
            public boolean aabc(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.huawei.hms.hihealth.ISettingControllerManager");
                    obtain.writeString(str);
                    this.aab.transact(9, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hms.hihealth.aabp
            public String aabc() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.huawei.hms.hihealth.ISettingControllerManager");
                    this.aab.transact(6, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readString();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hms.hihealth.aabp
            public boolean aaba(boolean z) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.huawei.hms.hihealth.ISettingControllerManager");
                    obtain.writeInt(z ? 1 : 0);
                    this.aab.transact(8, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hms.hihealth.aabp
            public void aaba() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.huawei.hms.hihealth.ISettingControllerManager");
                    this.aab.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hms.hihealth.aabp
            public boolean aab() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.huawei.hms.hihealth.ISettingControllerManager");
                    this.aab.transact(10, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            C0108aab(IBinder iBinder) {
                this.aab = iBinder;
            }
        }

        public static aabp aab(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.huawei.hms.hihealth.ISettingControllerManager");
            return (queryLocalInterface == null || !(queryLocalInterface instanceof aabp)) ? new C0108aab(iBinder) : (aabp) queryLocalInterface;
        }
    }
}
