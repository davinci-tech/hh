package com.huawei.openalliance.ad;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;

/* loaded from: classes5.dex */
public interface cr extends IInterface {
    String a();

    boolean b();

    public static abstract class a {

        /* renamed from: com.huawei.openalliance.ad.cr$a$a, reason: collision with other inner class name */
        static class C0175a implements cr {

            /* renamed from: a, reason: collision with root package name */
            private IBinder f6685a;

            public String c() {
                return "com.uodis.opendevice.aidl.OpenDeviceIdentifierService";
            }

            @Override // com.huawei.openalliance.ad.cr
            public boolean b() {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(c());
                    this.f6685a.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.f6685a;
            }

            @Override // com.huawei.openalliance.ad.cr
            public String a() {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(c());
                    this.f6685a.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readString();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            C0175a(IBinder iBinder) {
                this.f6685a = iBinder;
            }
        }

        public static cr a(IBinder iBinder) {
            return new C0175a(iBinder);
        }
    }
}
