package com.huawei.android.hms.ppskit;

import android.net.Uri;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import com.huawei.android.hms.ppskit.c;

/* loaded from: classes2.dex */
public interface b extends IInterface {
    void a(RemoteInstallReq remoteInstallReq, Uri uri, c cVar);

    public static abstract class a extends Binder implements b {

        /* renamed from: com.huawei.android.hms.ppskit.b$a$a, reason: collision with other inner class name */
        static class C0040a implements b {
            public static b c;
            private IBinder e;

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.e;
            }

            @Override // com.huawei.android.hms.ppskit.b
            public void a(RemoteInstallReq remoteInstallReq, Uri uri, c cVar) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.huawei.android.hms.ppskit.IPPSInstallationService");
                    if (remoteInstallReq != null) {
                        obtain.writeInt(1);
                        remoteInstallReq.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (uri != null) {
                        obtain.writeInt(1);
                        uri.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeStrongBinder(cVar != null ? cVar.asBinder() : null);
                    if (this.e.transact(1, obtain, obtain2, 0) || a.a() == null) {
                        obtain2.readException();
                    } else {
                        a.a().a(remoteInstallReq, uri, cVar);
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            C0040a(IBinder iBinder) {
                this.e = iBinder;
            }
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) {
            if (i == 1598968902) {
                parcel2.writeString("com.huawei.android.hms.ppskit.IPPSInstallationService");
                return true;
            }
            if (i != 1) {
                return super.onTransact(i, parcel, parcel2, i2);
            }
            parcel.enforceInterface("com.huawei.android.hms.ppskit.IPPSInstallationService");
            a(parcel.readInt() != 0 ? RemoteInstallReq.CREATOR.createFromParcel(parcel) : null, parcel.readInt() != 0 ? (Uri) Uri.CREATOR.createFromParcel(parcel) : null, c.a.a(parcel.readStrongBinder()));
            parcel2.writeNoException();
            return true;
        }

        public static b a(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.huawei.android.hms.ppskit.IPPSInstallationService");
            return (queryLocalInterface == null || !(queryLocalInterface instanceof b)) ? new C0040a(iBinder) : (b) queryLocalInterface;
        }

        public static b a() {
            return C0040a.c;
        }
    }
}
