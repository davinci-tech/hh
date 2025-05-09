package com.huawei.hms.maps.internal;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import com.huawei.hms.maps.model.MapAuthInfo;

/* loaded from: classes9.dex */
public interface IMapAuthToProvider extends IInterface {
    void notifyProvierAuthInfo(MapAuthInfo mapAuthInfo);

    public static abstract class Stub extends Binder implements IMapAuthToProvider {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        static class Proxy implements IMapAuthToProvider {

            /* renamed from: a, reason: collision with root package name */
            private IBinder f4956a;

            @Override // com.huawei.hms.maps.internal.IMapAuthToProvider
            public void notifyProvierAuthInfo(MapAuthInfo mapAuthInfo) {
                com.huawei.hms.maps.model.internal.mab.a(this.f4956a, "com.huawei.hms.maps.internal.IMapAuthToProvider", 1, mapAuthInfo);
            }

            public String getInterfaceDescriptor() {
                return "com.huawei.hms.maps.internal.IMapAuthToProvider";
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.f4956a;
            }

            Proxy(IBinder iBinder) {
                this.f4956a = iBinder;
            }
        }

        public static IMapAuthToProvider asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.huawei.hms.maps.internal.IMapAuthToProvider");
            return (queryLocalInterface == null || !(queryLocalInterface instanceof IMapAuthToProvider)) ? new Proxy(iBinder) : (IMapAuthToProvider) queryLocalInterface;
        }
    }
}
