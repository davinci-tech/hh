package com.huawei.hms.maps.internal;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;

/* loaded from: classes4.dex */
public interface IMapClientIdentity extends IInterface {
    void regestToProvierIdentity(com.huawei.hms.maps.model.maa maaVar);

    public static abstract class Stub extends Binder implements IMapClientIdentity {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        static class Proxy implements IMapClientIdentity {

            /* renamed from: a, reason: collision with root package name */
            private IBinder f4957a;

            @Override // com.huawei.hms.maps.internal.IMapClientIdentity
            public void regestToProvierIdentity(com.huawei.hms.maps.model.maa maaVar) {
                com.huawei.hms.maps.model.internal.mab.a(this.f4957a, "com.huawei.hms.maps.internal.IMapClientIdentity", 1, maaVar);
            }

            public String getInterfaceDescriptor() {
                return "com.huawei.hms.maps.internal.IMapClientIdentity";
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.f4957a;
            }

            Proxy(IBinder iBinder) {
                this.f4957a = iBinder;
            }
        }

        public static IMapClientIdentity asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.huawei.hms.maps.internal.IMapClientIdentity");
            return (queryLocalInterface == null || !(queryLocalInterface instanceof IMapClientIdentity)) ? new Proxy(iBinder) : (IMapClientIdentity) queryLocalInterface;
        }
    }
}
