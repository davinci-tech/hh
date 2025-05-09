package com.huawei.hms.maps.model.internal;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import com.huawei.hms.maps.model.Tile;

/* loaded from: classes9.dex */
public interface ITileProviderDelegate extends IInterface {
    Tile getTile(int i, int i2, int i3);

    public static abstract class Stub extends Binder implements ITileProviderDelegate {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        static class Proxy implements ITileProviderDelegate {

            /* renamed from: a, reason: collision with root package name */
            private IBinder f5029a;

            @Override // com.huawei.hms.maps.model.internal.ITileProviderDelegate
            public Tile getTile(int i, int i2, int i3) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.huawei.hms.maps.model.internal.ITileProviderDelegate");
                    obtain.writeInt(i);
                    obtain.writeInt(i2);
                    obtain.writeInt(i3);
                    this.f5029a.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0 ? Tile.CREATOR.createFromParcel(obtain2) : null;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public String getInterfaceDescriptor() {
                return "com.huawei.hms.maps.model.internal.ITileProviderDelegate";
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.f5029a;
            }

            Proxy(IBinder iBinder) {
                this.f5029a = iBinder;
            }
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) {
            if (i != 1) {
                if (i != 1598968902) {
                    return super.onTransact(i, parcel, parcel2, i2);
                }
                parcel2.writeString("com.huawei.hms.maps.model.internal.ITileProviderDelegate");
                return true;
            }
            parcel.enforceInterface("com.huawei.hms.maps.model.internal.ITileProviderDelegate");
            Tile tile = getTile(parcel.readInt(), parcel.readInt(), parcel.readInt());
            parcel2.writeNoException();
            if (tile != null) {
                parcel2.writeInt(1);
                tile.writeToParcel(parcel2, 1);
            } else {
                parcel2.writeInt(0);
            }
            return true;
        }

        public static ITileProviderDelegate asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.huawei.hms.maps.model.internal.ITileProviderDelegate");
            return (queryLocalInterface == null || !(queryLocalInterface instanceof ITileProviderDelegate)) ? new Proxy(iBinder) : (ITileProviderDelegate) queryLocalInterface;
        }

        public Stub() {
            attachInterface(this, "com.huawei.hms.maps.model.internal.ITileProviderDelegate");
        }
    }
}
