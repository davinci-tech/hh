package com.huawei.hms.hihealth;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;

/* loaded from: classes4.dex */
public interface aabn extends IInterface {

    public static abstract class aab extends Binder implements aabn {

        /* renamed from: com.huawei.hms.hihealth.aabn$aab$aab, reason: collision with other inner class name */
        static class C0107aab implements aabn {
            private IBinder aab;

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.aab;
            }

            C0107aab(IBinder iBinder) {
                this.aab = iBinder;
            }
        }

        public static aabn aab(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.huawei.hms.hihealth.IHealthRecordsControllerManager");
            return (queryLocalInterface == null || !(queryLocalInterface instanceof aabn)) ? new C0107aab(iBinder) : (aabn) queryLocalInterface;
        }
    }
}
