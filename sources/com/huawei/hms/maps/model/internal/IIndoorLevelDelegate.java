package com.huawei.hms.maps.model.internal;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;

/* loaded from: classes9.dex */
public interface IIndoorLevelDelegate extends IInterface {
    void activate();

    boolean equalsRemote(IIndoorLevelDelegate iIndoorLevelDelegate);

    String getName();

    String getShortName();

    int hashCodeRemote();

    public static abstract class Stub extends Binder implements IIndoorLevelDelegate {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        static class Proxy implements IIndoorLevelDelegate {

            /* renamed from: a, reason: collision with root package name */
            private IBinder f5024a;

            @Override // com.huawei.hms.maps.model.internal.IIndoorLevelDelegate
            public int hashCodeRemote() {
                return mab.b(this.f5024a, "com.huawei.hms.maps.model.internal.IIndoorLevelDelegate", 5);
            }

            @Override // com.huawei.hms.maps.model.internal.IIndoorLevelDelegate
            public String getShortName() {
                return mab.d(this.f5024a, "com.huawei.hms.maps.model.internal.IIndoorLevelDelegate", 3);
            }

            @Override // com.huawei.hms.maps.model.internal.IIndoorLevelDelegate
            public String getName() {
                return mab.d(this.f5024a, "com.huawei.hms.maps.model.internal.IIndoorLevelDelegate", 2);
            }

            public String getInterfaceDescriptor() {
                return "com.huawei.hms.maps.model.internal.IIndoorLevelDelegate";
            }

            @Override // com.huawei.hms.maps.model.internal.IIndoorLevelDelegate
            public boolean equalsRemote(IIndoorLevelDelegate iIndoorLevelDelegate) {
                return mab.a(iIndoorLevelDelegate, this.f5024a, "com.huawei.hms.maps.model.internal.IIndoorLevelDelegate", 4);
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.f5024a;
            }

            @Override // com.huawei.hms.maps.model.internal.IIndoorLevelDelegate
            public void activate() {
                mab.i(this.f5024a, "com.huawei.hms.maps.model.internal.IIndoorLevelDelegate", 1);
            }

            Proxy(IBinder iBinder) {
                this.f5024a = iBinder;
            }
        }

        public static IIndoorLevelDelegate asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.huawei.hms.maps.model.internal.IIndoorLevelDelegate");
            return (queryLocalInterface == null || !(queryLocalInterface instanceof IIndoorLevelDelegate)) ? new Proxy(iBinder) : (IIndoorLevelDelegate) queryLocalInterface;
        }

        public Stub() {
            attachInterface(this, "com.huawei.hms.maps.model.internal.IIndoorLevelDelegate");
        }
    }
}
