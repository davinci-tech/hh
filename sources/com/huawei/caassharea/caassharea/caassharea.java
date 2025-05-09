package com.huawei.caassharea.caassharea;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;

/* loaded from: classes8.dex */
public interface caassharea extends IInterface {
    void caassharea(int i);

    void caassharea(int i, String str);

    /* renamed from: com.huawei.caassharea.caassharea.caassharea$caassharea, reason: collision with other inner class name */
    public static abstract class AbstractBinderC0046caassharea extends Binder implements caassharea {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        /* renamed from: com.huawei.caassharea.caassharea.caassharea$caassharea$b */
        static final class b implements caassharea {
            public static caassharea c;
            private IBinder d;

            @Override // com.huawei.caassharea.caassharea.caassharea
            public final void caassharea(int i, String str) {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.huawei.caasservice.share.IHwCaasShareCallback");
                    obtain.writeInt(i);
                    obtain.writeString(str);
                    if (this.d.transact(1, obtain, null, 1) || AbstractBinderC0046caassharea.caassharea() == null) {
                        return;
                    }
                    AbstractBinderC0046caassharea.caassharea().caassharea(i, str);
                } finally {
                    obtain.recycle();
                }
            }

            @Override // com.huawei.caassharea.caassharea.caassharea
            public final void caassharea(int i) {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.huawei.caasservice.share.IHwCaasShareCallback");
                    obtain.writeInt(i);
                    if (this.d.transact(2, obtain, null, 1) || AbstractBinderC0046caassharea.caassharea() == null) {
                        return;
                    }
                    AbstractBinderC0046caassharea.caassharea().caassharea(i);
                } finally {
                    obtain.recycle();
                }
            }

            @Override // android.os.IInterface
            public final IBinder asBinder() {
                return this.d;
            }

            b(IBinder iBinder) {
                this.d = iBinder;
            }
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) {
            if (i == 1) {
                parcel.enforceInterface("com.huawei.caasservice.share.IHwCaasShareCallback");
                caassharea(parcel.readInt(), parcel.readString());
                return true;
            }
            if (i == 2) {
                parcel.enforceInterface("com.huawei.caasservice.share.IHwCaasShareCallback");
                caassharea(parcel.readInt());
                return true;
            }
            if (i != 1598968902) {
                return super.onTransact(i, parcel, parcel2, i2);
            }
            parcel2.writeString("com.huawei.caasservice.share.IHwCaasShareCallback");
            return true;
        }

        public static caassharea caassharea() {
            return b.c;
        }

        public AbstractBinderC0046caassharea() {
            attachInterface(this, "com.huawei.caasservice.share.IHwCaasShareCallback");
        }
    }
}
