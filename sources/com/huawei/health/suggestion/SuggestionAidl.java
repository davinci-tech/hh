package com.huawei.health.suggestion;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import com.huawei.health.sport.model.WorkoutRecord;

/* loaded from: classes4.dex */
public interface SuggestionAidl extends IInterface {
    public static final String DESCRIPTOR = "com.huawei.health.suggestion.SuggestionAidl";

    boolean postFitnessRecord(WorkoutRecord workoutRecord) throws RemoteException;

    public static abstract class Stub extends Binder implements SuggestionAidl {
        static final int TRANSACTION_postFitnessRecord = 1;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, SuggestionAidl.DESCRIPTOR);
        }

        public static SuggestionAidl asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(SuggestionAidl.DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof SuggestionAidl)) {
                return (SuggestionAidl) queryLocalInterface;
            }
            return new b(iBinder);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i >= 1 && i <= 16777215) {
                parcel.enforceInterface(SuggestionAidl.DESCRIPTOR);
            }
            if (i == 1598968902) {
                parcel2.writeString(SuggestionAidl.DESCRIPTOR);
                return true;
            }
            if (i == 1) {
                boolean postFitnessRecord = postFitnessRecord((WorkoutRecord) b.axR_(parcel, WorkoutRecord.CREATOR));
                parcel2.writeNoException();
                parcel2.writeInt(postFitnessRecord ? 1 : 0);
                return true;
            }
            return super.onTransact(i, parcel, parcel2, i2);
        }

        static class b implements SuggestionAidl {

            /* renamed from: a, reason: collision with root package name */
            private IBinder f3010a;

            b(IBinder iBinder) {
                this.f3010a = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.f3010a;
            }

            @Override // com.huawei.health.suggestion.SuggestionAidl
            public boolean postFitnessRecord(WorkoutRecord workoutRecord) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(SuggestionAidl.DESCRIPTOR);
                    b.axS_(obtain, workoutRecord, 0);
                    this.f3010a.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }
    }

    public static class b {
        /* JADX INFO: Access modifiers changed from: private */
        public static <T> T axR_(Parcel parcel, Parcelable.Creator<T> creator) {
            if (parcel.readInt() != 0) {
                return creator.createFromParcel(parcel);
            }
            return null;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static <T extends Parcelable> void axS_(Parcel parcel, T t, int i) {
            if (t != null) {
                parcel.writeInt(1);
                t.writeToParcel(parcel, i);
            } else {
                parcel.writeInt(0);
            }
        }
    }
}
