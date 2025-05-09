package com.huawei.openalliance.ad.hsf;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import com.huawei.openalliance.ad.ho;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes5.dex */
public interface b extends IInterface {
    int a();

    int a(String str, List<PPSHsfService> list);

    public static abstract class a extends Binder implements b {

        /* renamed from: com.huawei.openalliance.ad.hsf.b$a$a, reason: collision with other inner class name */
        static class C0189a implements b {

            /* renamed from: a, reason: collision with root package name */
            private IBinder f6924a;

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.f6924a;
            }

            @Override // com.huawei.openalliance.ad.hsf.b
            public int a(String str, List<PPSHsfService> list) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.huawei.hsf.internal.ICoreService");
                    obtain.writeString(str);
                    this.f6924a.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                    int readInt = obtain2.readInt();
                    obtain2.readTypedList(list, PPSHsfService.CREATOR);
                    return readInt;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.openalliance.ad.hsf.b
            public int a() {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.huawei.hsf.internal.ICoreService");
                    this.f6924a.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            C0189a(IBinder iBinder) {
                this.f6924a = iBinder;
            }
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) {
            if (i == 1) {
                parcel.enforceInterface("com.huawei.hsf.internal.ICoreService");
                int a2 = a();
                parcel2.writeNoException();
                parcel2.writeInt(a2);
                return true;
            }
            if (i != 2) {
                if (i != 1598968902) {
                    return super.onTransact(i, parcel, parcel2, i2);
                }
                parcel2.writeString("com.huawei.hsf.internal.ICoreService");
                return true;
            }
            parcel.enforceInterface("com.huawei.hsf.internal.ICoreService");
            String readString = parcel.readString();
            ArrayList arrayList = new ArrayList();
            int a3 = a(readString, arrayList);
            parcel2.writeNoException();
            parcel2.writeInt(a3);
            parcel2.writeTypedList(arrayList);
            return true;
        }

        public static b a(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            try {
                IInterface queryLocalInterface = iBinder.queryLocalInterface("com.huawei.hsf.internal.ICoreService");
                return (queryLocalInterface == null || !(queryLocalInterface instanceof b)) ? new C0189a(iBinder) : (b) queryLocalInterface;
            } catch (Throwable th) {
                ho.c("ICoreService", "ICoreService err: " + th.getClass().getSimpleName());
                return null;
            }
        }
    }
}
