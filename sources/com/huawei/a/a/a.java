package com.huawei.a.a;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.huawei.harmonyos.interwork.DeviceInfo;
import com.huawei.harmonyos.interwork.b;
import com.huawei.harmonyos.interwork.base.bundle.AbilityInfo;
import com.huawei.harmonyos.interwork.base.content.Intent;
import com.huawei.harmonyos.interwork.c;
import com.huawei.harmonyos.interwork.d;
import com.huawei.harmonyos.interwork.e;
import java.util.List;

/* loaded from: classes2.dex */
public interface a extends IInterface {
    int a(IBinder iBinder, int i, int i2) throws RemoteException;

    int a(Intent intent, int i, int i2) throws RemoteException;

    int a(Intent intent, IBinder iBinder, int i, int i2) throws RemoteException;

    int a(Intent intent, b bVar, int i, int i2) throws RemoteException;

    DeviceInfo a(String str) throws RemoteException;

    String a(String str, String str2) throws RemoteException;

    List<DeviceInfo> a(int i) throws RemoteException;

    void a() throws RemoteException;

    void a(IBinder iBinder) throws RemoteException;

    void a(String str, e eVar, int i, int i2) throws RemoteException;

    boolean a(IBinder iBinder, c cVar, int i, int i2) throws RemoteException;

    boolean a(d dVar) throws RemoteException;

    IBinder b(String str, String str2) throws RemoteException;

    List<AbilityInfo> b(Intent intent, int i, int i2) throws RemoteException;

    void b() throws RemoteException;

    void b(String str, e eVar, int i, int i2) throws RemoteException;

    boolean b(IBinder iBinder, c cVar, int i, int i2) throws RemoteException;

    boolean b(d dVar) throws RemoteException;

    boolean b(String str) throws RemoteException;

    /* renamed from: com.huawei.a.a.a$a, reason: collision with other inner class name */
    public static abstract class AbstractBinderC0031a extends Binder implements a {
        public static a b(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.huawei.systemserver.servicebus.DefAccessInterface");
            if (queryLocalInterface != null && (queryLocalInterface instanceof a)) {
                return (a) queryLocalInterface;
            }
            return new b(iBinder);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i == 1598968902) {
                parcel2.writeString("com.huawei.systemserver.servicebus.DefAccessInterface");
                return true;
            }
            com.huawei.harmonyos.interwork.b bVar = null;
            switch (i) {
                case 1:
                    parcel.enforceInterface("com.huawei.systemserver.servicebus.DefAccessInterface");
                    a(parcel.readStrongBinder());
                    parcel2.writeNoException();
                    return true;
                case 2:
                    parcel.enforceInterface("com.huawei.systemserver.servicebus.DefAccessInterface");
                    a();
                    parcel2.writeNoException();
                    return true;
                case 3:
                    parcel.enforceInterface("com.huawei.systemserver.servicebus.DefAccessInterface");
                    int a2 = a(parcel.readInt() != 0 ? Intent.CREATOR.createFromParcel(parcel) : null, parcel.readStrongBinder(), parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(a2);
                    return true;
                case 4:
                    parcel.enforceInterface("com.huawei.systemserver.servicebus.DefAccessInterface");
                    int a3 = a(parcel.readStrongBinder(), parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(a3);
                    return true;
                case 5:
                    parcel.enforceInterface("com.huawei.systemserver.servicebus.DefAccessInterface");
                    int a4 = a(parcel.readInt() != 0 ? Intent.CREATOR.createFromParcel(parcel) : null, parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(a4);
                    return true;
                case 6:
                    parcel.enforceInterface("com.huawei.systemserver.servicebus.DefAccessInterface");
                    List<AbilityInfo> b2 = b(parcel.readInt() != 0 ? Intent.CREATOR.createFromParcel(parcel) : null, parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeTypedList(b2);
                    return true;
                default:
                    switch (i) {
                        case 8:
                            parcel.enforceInterface("com.huawei.systemserver.servicebus.DefAccessInterface");
                            boolean a5 = a(d.a.a(parcel.readStrongBinder()));
                            parcel2.writeNoException();
                            parcel2.writeInt(a5 ? 1 : 0);
                            return true;
                        case 9:
                            parcel.enforceInterface("com.huawei.systemserver.servicebus.DefAccessInterface");
                            boolean b3 = b(d.a.a(parcel.readStrongBinder()));
                            parcel2.writeNoException();
                            parcel2.writeInt(b3 ? 1 : 0);
                            return true;
                        case 10:
                            parcel.enforceInterface("com.huawei.systemserver.servicebus.DefAccessInterface");
                            List<DeviceInfo> a6 = a(parcel.readInt());
                            parcel2.writeNoException();
                            parcel2.writeTypedList(a6);
                            return true;
                        case 11:
                            parcel.enforceInterface("com.huawei.systemserver.servicebus.DefAccessInterface");
                            DeviceInfo a7 = a(parcel.readString());
                            parcel2.writeNoException();
                            if (a7 != null) {
                                parcel2.writeInt(1);
                                a7.writeToParcel(parcel2, 1);
                            } else {
                                parcel2.writeInt(0);
                            }
                            return true;
                        case 12:
                            parcel.enforceInterface("com.huawei.systemserver.servicebus.DefAccessInterface");
                            String a8 = a(parcel.readString(), parcel.readString());
                            parcel2.writeNoException();
                            parcel2.writeString(a8);
                            return true;
                        default:
                            switch (i) {
                                case 15:
                                    parcel.enforceInterface("com.huawei.systemserver.servicebus.DefAccessInterface");
                                    a(parcel.readString(), e.a.a(parcel.readStrongBinder()), parcel.readInt(), parcel.readInt());
                                    parcel2.writeNoException();
                                    return true;
                                case 16:
                                    parcel.enforceInterface("com.huawei.systemserver.servicebus.DefAccessInterface");
                                    b(parcel.readString(), e.a.a(parcel.readStrongBinder()), parcel.readInt(), parcel.readInt());
                                    parcel2.writeNoException();
                                    return true;
                                case 17:
                                    parcel.enforceInterface("com.huawei.systemserver.servicebus.DefAccessInterface");
                                    boolean b4 = b(parcel.readString());
                                    parcel2.writeNoException();
                                    parcel2.writeInt(b4 ? 1 : 0);
                                    return true;
                                case 18:
                                    parcel.enforceInterface("com.huawei.systemserver.servicebus.DefAccessInterface");
                                    b();
                                    parcel2.writeNoException();
                                    return true;
                                case 19:
                                    parcel.enforceInterface("com.huawei.systemserver.servicebus.DefAccessInterface");
                                    boolean a9 = a(parcel.readStrongBinder(), c.a.a(parcel.readStrongBinder()), parcel.readInt(), parcel.readInt());
                                    parcel2.writeNoException();
                                    parcel2.writeInt(a9 ? 1 : 0);
                                    return true;
                                case 20:
                                    parcel.enforceInterface("com.huawei.systemserver.servicebus.DefAccessInterface");
                                    boolean b5 = b(parcel.readStrongBinder(), c.a.a(parcel.readStrongBinder()), parcel.readInt(), parcel.readInt());
                                    parcel2.writeNoException();
                                    parcel2.writeInt(b5 ? 1 : 0);
                                    return true;
                                case 21:
                                    parcel.enforceInterface("com.huawei.systemserver.servicebus.DefAccessInterface");
                                    IBinder b6 = b(parcel.readString(), parcel.readString());
                                    parcel2.writeNoException();
                                    parcel2.writeStrongBinder(b6);
                                    return true;
                                case 22:
                                    parcel.enforceInterface("com.huawei.systemserver.servicebus.DefAccessInterface");
                                    Intent createFromParcel = parcel.readInt() != 0 ? Intent.CREATOR.createFromParcel(parcel) : null;
                                    IBinder readStrongBinder = parcel.readStrongBinder();
                                    if (readStrongBinder != null) {
                                        IInterface queryLocalInterface = readStrongBinder.queryLocalInterface("com.huawei.harmonyos.interwork.IAbilityStartCallback");
                                        if (queryLocalInterface != null && (queryLocalInterface instanceof com.huawei.harmonyos.interwork.b)) {
                                            bVar = (com.huawei.harmonyos.interwork.b) queryLocalInterface;
                                        } else {
                                            bVar = new b.a.C0049a(readStrongBinder);
                                        }
                                    }
                                    int a10 = a(createFromParcel, bVar, parcel.readInt(), parcel.readInt());
                                    parcel2.writeNoException();
                                    parcel2.writeInt(a10);
                                    return true;
                                default:
                                    return super.onTransact(i, parcel, parcel2, i2);
                            }
                    }
            }
        }

        /* renamed from: com.huawei.a.a.a$a$b */
        static final class b implements a {
            public static a b;

            /* renamed from: a, reason: collision with root package name */
            private IBinder f1668a;

            b(IBinder iBinder) {
                this.f1668a = iBinder;
            }

            @Override // android.os.IInterface
            public final IBinder asBinder() {
                return this.f1668a;
            }

            @Override // com.huawei.a.a.a
            public final void a(IBinder iBinder) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.huawei.systemserver.servicebus.DefAccessInterface");
                    obtain.writeStrongBinder(iBinder);
                    if (!this.f1668a.transact(1, obtain, obtain2, 0) && AbstractBinderC0031a.c() != null) {
                        AbstractBinderC0031a.c().a(iBinder);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.a.a.a
            public final void a() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.huawei.systemserver.servicebus.DefAccessInterface");
                    if (!this.f1668a.transact(2, obtain, obtain2, 0) && AbstractBinderC0031a.c() != null) {
                        AbstractBinderC0031a.c().a();
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.a.a.a
            public final int a(Intent intent, IBinder iBinder, int i, int i2) throws RemoteException {
                int readInt;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.huawei.systemserver.servicebus.DefAccessInterface");
                    if (intent != null) {
                        obtain.writeInt(1);
                        intent.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeStrongBinder(iBinder);
                    obtain.writeInt(i);
                    obtain.writeInt(i2);
                    if (!this.f1668a.transact(3, obtain, obtain2, 0) && AbstractBinderC0031a.c() != null) {
                        readInt = AbstractBinderC0031a.c().a(intent, iBinder, i, i2);
                    } else {
                        obtain2.readException();
                        readInt = obtain2.readInt();
                    }
                    return readInt;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.a.a.a
            public final int a(IBinder iBinder, int i, int i2) throws RemoteException {
                int readInt;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.huawei.systemserver.servicebus.DefAccessInterface");
                    obtain.writeStrongBinder(iBinder);
                    obtain.writeInt(i);
                    obtain.writeInt(i2);
                    if (!this.f1668a.transact(4, obtain, obtain2, 0) && AbstractBinderC0031a.c() != null) {
                        readInt = AbstractBinderC0031a.c().a(iBinder, i, i2);
                    } else {
                        obtain2.readException();
                        readInt = obtain2.readInt();
                    }
                    return readInt;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.a.a.a
            public final int a(Intent intent, int i, int i2) throws RemoteException {
                int readInt;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.huawei.systemserver.servicebus.DefAccessInterface");
                    if (intent != null) {
                        obtain.writeInt(1);
                        intent.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeInt(i);
                    obtain.writeInt(i2);
                    if (!this.f1668a.transact(5, obtain, obtain2, 0) && AbstractBinderC0031a.c() != null) {
                        readInt = AbstractBinderC0031a.c().a(intent, i, i2);
                    } else {
                        obtain2.readException();
                        readInt = obtain2.readInt();
                    }
                    return readInt;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.a.a.a
            public final List<AbilityInfo> b(Intent intent, int i, int i2) throws RemoteException {
                List<AbilityInfo> createTypedArrayList;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.huawei.systemserver.servicebus.DefAccessInterface");
                    if (intent != null) {
                        obtain.writeInt(1);
                        intent.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeInt(i);
                    obtain.writeInt(i2);
                    if (!this.f1668a.transact(6, obtain, obtain2, 0) && AbstractBinderC0031a.c() != null) {
                        createTypedArrayList = AbstractBinderC0031a.c().b(intent, i, i2);
                    } else {
                        obtain2.readException();
                        createTypedArrayList = obtain2.createTypedArrayList(AbilityInfo.CREATOR);
                    }
                    return createTypedArrayList;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.a.a.a
            public final boolean a(d dVar) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.huawei.systemserver.servicebus.DefAccessInterface");
                    obtain.writeStrongBinder(dVar != null ? dVar.asBinder() : null);
                    if (!this.f1668a.transact(8, obtain, obtain2, 0) && AbstractBinderC0031a.c() != null) {
                        return AbstractBinderC0031a.c().a(dVar);
                    }
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.a.a.a
            public final boolean b(d dVar) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.huawei.systemserver.servicebus.DefAccessInterface");
                    obtain.writeStrongBinder(dVar != null ? dVar.asBinder() : null);
                    if (!this.f1668a.transact(9, obtain, obtain2, 0) && AbstractBinderC0031a.c() != null) {
                        return AbstractBinderC0031a.c().b(dVar);
                    }
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.a.a.a
            public final List<DeviceInfo> a(int i) throws RemoteException {
                List<DeviceInfo> createTypedArrayList;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.huawei.systemserver.servicebus.DefAccessInterface");
                    obtain.writeInt(i);
                    if (!this.f1668a.transact(10, obtain, obtain2, 0) && AbstractBinderC0031a.c() != null) {
                        createTypedArrayList = AbstractBinderC0031a.c().a(i);
                    } else {
                        obtain2.readException();
                        createTypedArrayList = obtain2.createTypedArrayList(DeviceInfo.CREATOR);
                    }
                    return createTypedArrayList;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.a.a.a
            public final DeviceInfo a(String str) throws RemoteException {
                DeviceInfo createFromParcel;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.huawei.systemserver.servicebus.DefAccessInterface");
                    obtain.writeString(str);
                    if (!this.f1668a.transact(11, obtain, obtain2, 0) && AbstractBinderC0031a.c() != null) {
                        createFromParcel = AbstractBinderC0031a.c().a(str);
                    } else {
                        obtain2.readException();
                        createFromParcel = obtain2.readInt() != 0 ? DeviceInfo.CREATOR.createFromParcel(obtain2) : null;
                    }
                    return createFromParcel;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.a.a.a
            public final String a(String str, String str2) throws RemoteException {
                String readString;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.huawei.systemserver.servicebus.DefAccessInterface");
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    if (!this.f1668a.transact(12, obtain, obtain2, 0) && AbstractBinderC0031a.c() != null) {
                        readString = AbstractBinderC0031a.c().a(str, str2);
                    } else {
                        obtain2.readException();
                        readString = obtain2.readString();
                    }
                    return readString;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.a.a.a
            public final void a(String str, e eVar, int i, int i2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.huawei.systemserver.servicebus.DefAccessInterface");
                    obtain.writeString(str);
                    obtain.writeStrongBinder(eVar != null ? eVar.asBinder() : null);
                    obtain.writeInt(i);
                    obtain.writeInt(i2);
                    if (!this.f1668a.transact(15, obtain, obtain2, 0) && AbstractBinderC0031a.c() != null) {
                        AbstractBinderC0031a.c().a(str, eVar, i, i2);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.a.a.a
            public final void b(String str, e eVar, int i, int i2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.huawei.systemserver.servicebus.DefAccessInterface");
                    obtain.writeString(str);
                    obtain.writeStrongBinder(eVar != null ? eVar.asBinder() : null);
                    obtain.writeInt(i);
                    obtain.writeInt(i2);
                    if (!this.f1668a.transact(16, obtain, obtain2, 0) && AbstractBinderC0031a.c() != null) {
                        AbstractBinderC0031a.c().b(str, eVar, i, i2);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.a.a.a
            public final boolean b(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.huawei.systemserver.servicebus.DefAccessInterface");
                    obtain.writeString(str);
                    if (!this.f1668a.transact(17, obtain, obtain2, 0) && AbstractBinderC0031a.c() != null) {
                        return AbstractBinderC0031a.c().b(str);
                    }
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.a.a.a
            public final void b() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.huawei.systemserver.servicebus.DefAccessInterface");
                    if (!this.f1668a.transact(18, obtain, obtain2, 0) && AbstractBinderC0031a.c() != null) {
                        AbstractBinderC0031a.c().b();
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.a.a.a
            public final boolean a(IBinder iBinder, c cVar, int i, int i2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.huawei.systemserver.servicebus.DefAccessInterface");
                    obtain.writeStrongBinder(iBinder);
                    obtain.writeStrongBinder(cVar != null ? cVar.asBinder() : null);
                    obtain.writeInt(i);
                    obtain.writeInt(i2);
                    if (!this.f1668a.transact(19, obtain, obtain2, 0) && AbstractBinderC0031a.c() != null) {
                        return AbstractBinderC0031a.c().a(iBinder, cVar, i, i2);
                    }
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.a.a.a
            public final boolean b(IBinder iBinder, c cVar, int i, int i2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.huawei.systemserver.servicebus.DefAccessInterface");
                    obtain.writeStrongBinder(iBinder);
                    obtain.writeStrongBinder(cVar != null ? cVar.asBinder() : null);
                    obtain.writeInt(i);
                    obtain.writeInt(i2);
                    if (!this.f1668a.transact(20, obtain, obtain2, 0) && AbstractBinderC0031a.c() != null) {
                        return AbstractBinderC0031a.c().b(iBinder, cVar, i, i2);
                    }
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.a.a.a
            public final IBinder b(String str, String str2) throws RemoteException {
                IBinder readStrongBinder;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.huawei.systemserver.servicebus.DefAccessInterface");
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    if (!this.f1668a.transact(21, obtain, obtain2, 0) && AbstractBinderC0031a.c() != null) {
                        readStrongBinder = AbstractBinderC0031a.c().b(str, str2);
                    } else {
                        obtain2.readException();
                        readStrongBinder = obtain2.readStrongBinder();
                    }
                    return readStrongBinder;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.a.a.a
            public final int a(Intent intent, com.huawei.harmonyos.interwork.b bVar, int i, int i2) throws RemoteException {
                int readInt;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.huawei.systemserver.servicebus.DefAccessInterface");
                    if (intent != null) {
                        obtain.writeInt(1);
                        intent.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeStrongBinder(bVar != null ? bVar.asBinder() : null);
                    obtain.writeInt(i);
                    obtain.writeInt(i2);
                    if (!this.f1668a.transact(22, obtain, obtain2, 0) && AbstractBinderC0031a.c() != null) {
                        readInt = AbstractBinderC0031a.c().a(intent, bVar, i, i2);
                    } else {
                        obtain2.readException();
                        readInt = obtain2.readInt();
                    }
                    return readInt;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public static a c() {
            return b.b;
        }
    }
}
