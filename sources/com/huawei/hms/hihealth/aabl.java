package com.huawei.hms.hihealth;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;

/* loaded from: classes4.dex */
public interface aabl extends IInterface {

    public static abstract class aab extends Binder implements aabl {

        /* renamed from: com.huawei.hms.hihealth.aabl$aab$aab, reason: collision with other inner class name */
        static class C0106aab implements aabl {
            private IBinder aab;

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.aab;
            }

            C0106aab(IBinder iBinder) {
                this.aab = iBinder;
            }
        }

        public static aabl aab(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.huawei.hms.hihealth.IDataControllerManager");
            return (queryLocalInterface == null || !(queryLocalInterface instanceof aabl)) ? new C0106aab(iBinder) : (aabl) queryLocalInterface;
        }
    }
}
