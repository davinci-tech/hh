package com.huawei.hms.maps;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;

/* loaded from: classes9.dex */
public interface maf extends IInterface {

    public static abstract class maa extends Binder implements maf {

        /* renamed from: com.huawei.hms.maps.maf$maa$maa, reason: collision with other inner class name */
        static class C0133maa implements maf {

            /* renamed from: a, reason: collision with root package name */
            private IBinder f4977a;

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.f4977a;
            }

            C0133maa(IBinder iBinder) {
                this.f4977a = iBinder;
            }
        }

        public static maf a(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.huawei.hms.search.api.IPlaceDelegate");
            return (queryLocalInterface == null || !(queryLocalInterface instanceof maf)) ? new C0133maa(iBinder) : (maf) queryLocalInterface;
        }
    }
}
