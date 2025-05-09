package com.huawei.health;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;

/* loaded from: classes.dex */
public interface ITrackDataReport extends IInterface {
    public static final String DESCRIPTOR = "com.huawei.health.ITrackDataReport";

    void report(Bundle bundle) throws RemoteException;

    public static abstract class Stub extends Binder implements ITrackDataReport {
        static final int TRANSACTION_report = 1;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, ITrackDataReport.DESCRIPTOR);
        }

        public static ITrackDataReport asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(ITrackDataReport.DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof ITrackDataReport)) {
                return (ITrackDataReport) queryLocalInterface;
            }
            return new c(iBinder);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i >= 1 && i <= 16777215) {
                parcel.enforceInterface(ITrackDataReport.DESCRIPTOR);
            }
            if (i == 1598968902) {
                parcel2.writeString(ITrackDataReport.DESCRIPTOR);
                return true;
            }
            if (i != 1) {
                return super.onTransact(i, parcel, parcel2, i2);
            }
            Bundle bundle = (Bundle) e.AT_(parcel, Bundle.CREATOR);
            report(bundle);
            parcel2.writeNoException();
            e.AU_(parcel2, bundle, 1);
            return true;
        }

        /* loaded from: classes3.dex */
        static class c implements ITrackDataReport {

            /* renamed from: a, reason: collision with root package name */
            private IBinder f2174a;

            c(IBinder iBinder) {
                this.f2174a = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.f2174a;
            }

            @Override // com.huawei.health.ITrackDataReport
            public void report(Bundle bundle) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(ITrackDataReport.DESCRIPTOR);
                    e.AU_(obtain, bundle, 0);
                    this.f2174a.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                    if (obtain2.readInt() != 0) {
                        bundle.readFromParcel(obtain2);
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }
    }

    /* loaded from: classes3.dex */
    public static class e {
        /* JADX INFO: Access modifiers changed from: private */
        public static <T> T AT_(Parcel parcel, Parcelable.Creator<T> creator) {
            if (parcel.readInt() != 0) {
                return creator.createFromParcel(parcel);
            }
            return null;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static <T extends Parcelable> void AU_(Parcel parcel, T t, int i) {
            if (t != null) {
                parcel.writeInt(1);
                t.writeToParcel(parcel, i);
            } else {
                parcel.writeInt(0);
            }
        }
    }
}
