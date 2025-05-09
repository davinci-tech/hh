package com.huawei.caassharea.caassharea;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import com.huawei.caassharea.caassharea.caassharea;

/* loaded from: classes8.dex */
public interface caasshareb extends IInterface {
    boolean caassharea();

    boolean caassharea(int i);

    boolean caassharea(Bundle bundle);

    boolean caassharea(com.huawei.caassharea.caassharea.caassharea caasshareaVar);

    boolean caasshareb(Bundle bundle);

    public static abstract class caassharea extends Binder implements caasshareb {

        static final class a implements caasshareb {
            public static caasshareb e;
            private IBinder d;

            @Override // com.huawei.caassharea.caassharea.caasshareb
            public final boolean caasshareb(Bundle bundle) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.huawei.caasservice.share.IHwCaasShareService");
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (!this.d.transact(4, obtain, obtain2, 0) && caassharea.caasshareb() != null) {
                        return caassharea.caasshareb().caasshareb(bundle);
                    }
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.caassharea.caassharea.caasshareb
            public final boolean caassharea(com.huawei.caassharea.caassharea.caassharea caasshareaVar) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.huawei.caasservice.share.IHwCaasShareService");
                    obtain.writeStrongBinder(caasshareaVar != null ? caasshareaVar.asBinder() : null);
                    if (!this.d.transact(1, obtain, obtain2, 0) && caassharea.caasshareb() != null) {
                        return caassharea.caasshareb().caassharea(caasshareaVar);
                    }
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.caassharea.caassharea.caasshareb
            public final boolean caassharea(Bundle bundle) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.huawei.caasservice.share.IHwCaasShareService");
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (!this.d.transact(3, obtain, obtain2, 0) && caassharea.caasshareb() != null) {
                        return caassharea.caasshareb().caassharea(bundle);
                    }
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.caassharea.caassharea.caasshareb
            public final boolean caassharea(int i) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.huawei.caasservice.share.IHwCaasShareService");
                    obtain.writeInt(i);
                    if (!this.d.transact(5, obtain, obtain2, 0) && caassharea.caasshareb() != null) {
                        return caassharea.caasshareb().caassharea(i);
                    }
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.caassharea.caassharea.caasshareb
            public final boolean caassharea() {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.huawei.caasservice.share.IHwCaasShareService");
                    if (!this.d.transact(2, obtain, obtain2, 0) && caassharea.caasshareb() != null) {
                        return caassharea.caasshareb().caassharea();
                    }
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // android.os.IInterface
            public final IBinder asBinder() {
                return this.d;
            }

            a(IBinder iBinder) {
                this.d = iBinder;
            }
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) {
            int i3;
            com.huawei.caassharea.caassharea.caassharea caasshareaVar = null;
            if (i == 1) {
                parcel.enforceInterface("com.huawei.caasservice.share.IHwCaasShareService");
                IBinder readStrongBinder = parcel.readStrongBinder();
                if (readStrongBinder != null) {
                    IInterface queryLocalInterface = readStrongBinder.queryLocalInterface("com.huawei.caasservice.share.IHwCaasShareCallback");
                    caasshareaVar = (queryLocalInterface == null || !(queryLocalInterface instanceof com.huawei.caassharea.caassharea.caassharea)) ? new caassharea.AbstractBinderC0046caassharea.b(readStrongBinder) : (com.huawei.caassharea.caassharea.caassharea) queryLocalInterface;
                }
                i3 = caassharea(caasshareaVar);
            } else if (i == 2) {
                parcel.enforceInterface("com.huawei.caasservice.share.IHwCaasShareService");
                i3 = caassharea();
            } else if (i == 3) {
                parcel.enforceInterface("com.huawei.caasservice.share.IHwCaasShareService");
                i3 = caassharea(parcel.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(parcel) : null);
            } else if (i == 4) {
                parcel.enforceInterface("com.huawei.caasservice.share.IHwCaasShareService");
                i3 = caasshareb(parcel.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(parcel) : null);
            } else {
                if (i != 5) {
                    if (i != 1598968902) {
                        return super.onTransact(i, parcel, parcel2, i2);
                    }
                    parcel2.writeString("com.huawei.caasservice.share.IHwCaasShareService");
                    return true;
                }
                parcel.enforceInterface("com.huawei.caasservice.share.IHwCaasShareService");
                i3 = caassharea(parcel.readInt());
            }
            parcel2.writeNoException();
            parcel2.writeInt(i3);
            return true;
        }

        public static caasshareb caasshareb() {
            return a.e;
        }

        public static caasshareb caassharea(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.huawei.caasservice.share.IHwCaasShareService");
            return (queryLocalInterface == null || !(queryLocalInterface instanceof caasshareb)) ? new a(iBinder) : (caasshareb) queryLocalInterface;
        }
    }
}
