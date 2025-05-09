package com.huawei.hms.hihealth;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;

/* loaded from: classes4.dex */
public interface aabe extends IInterface {

    public static abstract class aab extends Binder implements aabe {

        /* renamed from: com.huawei.hms.hihealth.aabe$aab$aab, reason: collision with other inner class name */
        static class C0101aab implements aabe {
            private IBinder aab;

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.aab;
            }

            C0101aab(IBinder iBinder) {
                this.aab = iBinder;
            }
        }

        public static aabe aab(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.huawei.hms.hihealth.IAuthControllerManager");
            return (queryLocalInterface == null || !(queryLocalInterface instanceof aabe)) ? new C0101aab(iBinder) : (aabe) queryLocalInterface;
        }
    }
}
