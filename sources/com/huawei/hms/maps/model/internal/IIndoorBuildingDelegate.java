package com.huawei.hms.maps.model.internal;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import java.util.List;

/* loaded from: classes9.dex */
public interface IIndoorBuildingDelegate extends IInterface {
    boolean equalsRemote(IIndoorBuildingDelegate iIndoorBuildingDelegate);

    int getActiveLevelIndex();

    int getDefaultLevelIndex();

    List<IBinder> getLevels();

    int hashCodeRemote();

    boolean isUnderground();

    public static abstract class Stub extends Binder implements IIndoorBuildingDelegate {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        static class Proxy implements IIndoorBuildingDelegate {

            /* renamed from: a, reason: collision with root package name */
            private IBinder f5023a;

            @Override // com.huawei.hms.maps.model.internal.IIndoorBuildingDelegate
            public boolean isUnderground() {
                return mab.a(this.f5023a, "com.huawei.hms.maps.model.internal.IIndoorBuildingDelegate", 4);
            }

            @Override // com.huawei.hms.maps.model.internal.IIndoorBuildingDelegate
            public int hashCodeRemote() {
                return mab.b(this.f5023a, "com.huawei.hms.maps.model.internal.IIndoorBuildingDelegate", 6);
            }

            @Override // com.huawei.hms.maps.model.internal.IIndoorBuildingDelegate
            public List<IBinder> getLevels() {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.huawei.hms.maps.model.internal.IIndoorBuildingDelegate");
                    this.f5023a.transact(3, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.createBinderArrayList();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public String getInterfaceDescriptor() {
                return "com.huawei.hms.maps.model.internal.IIndoorBuildingDelegate";
            }

            @Override // com.huawei.hms.maps.model.internal.IIndoorBuildingDelegate
            public int getDefaultLevelIndex() {
                return mab.b(this.f5023a, "com.huawei.hms.maps.model.internal.IIndoorBuildingDelegate", 2);
            }

            @Override // com.huawei.hms.maps.model.internal.IIndoorBuildingDelegate
            public int getActiveLevelIndex() {
                return mab.b(this.f5023a, "com.huawei.hms.maps.model.internal.IIndoorBuildingDelegate", 1);
            }

            @Override // com.huawei.hms.maps.model.internal.IIndoorBuildingDelegate
            public boolean equalsRemote(IIndoorBuildingDelegate iIndoorBuildingDelegate) {
                return mab.a(iIndoorBuildingDelegate, this.f5023a, "com.huawei.hms.maps.model.internal.IIndoorBuildingDelegate", 5);
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.f5023a;
            }

            Proxy(IBinder iBinder) {
                this.f5023a = iBinder;
            }
        }

        public static IIndoorBuildingDelegate asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.huawei.hms.maps.model.internal.IIndoorBuildingDelegate");
            return (queryLocalInterface == null || !(queryLocalInterface instanceof IIndoorBuildingDelegate)) ? new Proxy(iBinder) : (IIndoorBuildingDelegate) queryLocalInterface;
        }

        public Stub() {
            attachInterface(this, "com.huawei.hms.maps.model.internal.IIndoorBuildingDelegate");
        }
    }
}
