package com.huawei.hms.hihealth.options;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.huawei.hms.hihealth.data.SamplePoint;
import java.util.List;

/* loaded from: classes9.dex */
public interface aab extends IInterface {
    void onReceive(List<SamplePoint> list) throws RemoteException;

    void onStatusChange(int i) throws RemoteException;

    /* renamed from: com.huawei.hms.hihealth.options.aab$aab, reason: collision with other inner class name */
    public static abstract class AbstractBinderC0109aab extends Binder implements aab {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i >= 1 && i <= 16777215) {
                parcel.enforceInterface("com.huawei.hms.hihealth.options.OnActivityRecordRemoteListener");
            }
            if (i == 1598968902) {
                parcel2.writeString("com.huawei.hms.hihealth.options.OnActivityRecordRemoteListener");
                return true;
            }
            if (i == 1) {
                onReceive(parcel.createTypedArrayList(SamplePoint.CREATOR));
            } else {
                if (i != 2) {
                    return super.onTransact(i, parcel, parcel2, i2);
                }
                onStatusChange(parcel.readInt());
            }
            parcel2.writeNoException();
            return true;
        }

        public AbstractBinderC0109aab() {
            attachInterface(this, "com.huawei.hms.hihealth.options.OnActivityRecordRemoteListener");
        }
    }
}
