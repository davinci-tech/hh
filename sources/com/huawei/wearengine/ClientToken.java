package com.huawei.wearengine;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* loaded from: classes7.dex */
public interface ClientToken extends IInterface {
    public static final String DESCRIPTOR = "com.huawei.wearengine.ClientToken";

    public static abstract class Stub extends Binder implements ClientToken {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, ClientToken.DESCRIPTOR);
        }

        public static ClientToken asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(ClientToken.DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof ClientToken)) {
                return (ClientToken) queryLocalInterface;
            }
            return new c(iBinder);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i == 1598968902) {
                parcel2.writeString(ClientToken.DESCRIPTOR);
                return true;
            }
            return super.onTransact(i, parcel, parcel2, i2);
        }

        static class c implements ClientToken {
            private IBinder e;

            c(IBinder iBinder) {
                this.e = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.e;
            }
        }
    }
}
