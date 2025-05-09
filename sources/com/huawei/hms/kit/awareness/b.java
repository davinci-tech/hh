package com.huawei.hms.kit.awareness;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import com.huawei.hms.kit.awareness.a.a.f;

/* loaded from: classes4.dex */
public interface b extends IInterface {
    public static final String c = "com.huawei.hms.kit.awareness.IRemoteWakeup";

    /* renamed from: com.huawei.hms.kit.awareness.b$b, reason: collision with other inner class name */
    /* loaded from: classes9.dex */
    public static class C0126b implements b {
        @Override // com.huawei.hms.kit.awareness.b
        public void a(f fVar) {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    void a(f fVar);

    public static abstract class a extends Binder implements b {

        /* renamed from: a, reason: collision with root package name */
        static final int f4820a = 1;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) {
            if (i == 1598968902) {
                parcel2.writeString(b.c);
                return true;
            }
            if (i != 1) {
                return super.onTransact(i, parcel, parcel2, i2);
            }
            parcel.enforceInterface(b.c);
            a(parcel.readInt() != 0 ? f.CREATOR.createFromParcel(parcel) : null);
            parcel2.writeNoException();
            return true;
        }

        /* renamed from: com.huawei.hms.kit.awareness.b$a$a, reason: collision with other inner class name */
        static class C0124a implements b {
            public static b b;

            /* renamed from: a, reason: collision with root package name */
            private IBinder f4821a;

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.f4821a;
            }

            @Override // com.huawei.hms.kit.awareness.b
            public void a(f fVar) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(b.c);
                    if (fVar != null) {
                        obtain.writeInt(1);
                        fVar.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (this.f4821a.transact(1, obtain, obtain2, 0) || a.a() == null) {
                        obtain2.readException();
                    } else {
                        a.a().a(fVar);
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public String a() {
                return b.c;
            }

            C0124a(IBinder iBinder) {
                this.f4821a = iBinder;
            }
        }

        public static boolean a(b bVar) {
            if (C0124a.b != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (bVar == null) {
                return false;
            }
            C0124a.b = bVar;
            return true;
        }

        public static b a(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(b.c);
            return (queryLocalInterface == null || !(queryLocalInterface instanceof b)) ? new C0124a(iBinder) : (b) queryLocalInterface;
        }

        public static b a() {
            return C0124a.b;
        }

        public a() {
            attachInterface(this, b.c);
        }
    }
}
