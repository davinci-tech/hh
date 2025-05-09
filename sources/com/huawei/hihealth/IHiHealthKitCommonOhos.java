package com.huawei.hihealth;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import com.huawei.hihealth.ICommonCallback;
import com.huawei.hihealth.IDataOperateListener;
import com.huawei.hihealth.IDataOperateListenerOhos;
import com.huawei.hihealth.IDataReadResultListenerOhos;
import com.huawei.hihealth.ISportDataCallback;
import com.huawei.hihealthkit.context.OutOfBandData;
import java.util.List;

/* loaded from: classes.dex */
public interface IHiHealthKitCommonOhos extends IInterface {
    public static final String DESCRIPTOR = "com.huawei.hihealth.IHiHealthKitCommonOhos";

    public static class Default implements IHiHealthKitCommonOhos {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.huawei.hihealth.IHiHealthKitCommonOhos
        public void execQueryOhos(OutOfBandData outOfBandData, IDataReadResultListenerOhos iDataReadResultListenerOhos, int i, HiHealthDataQuery hiHealthDataQuery) throws RemoteException {
        }

        @Override // com.huawei.hihealth.IHiHealthKitCommonOhos
        public String getCategory(int i) throws RemoteException {
            return null;
        }

        @Override // com.huawei.hihealth.IHiHealthKitCommonOhos
        public int getServiceApiLevel(OutOfBandData outOfBandData) throws RemoteException {
            return 0;
        }

        @Override // com.huawei.hihealth.IHiHealthKitCommonOhos
        public void getUserInfo(OutOfBandData outOfBandData, List<String> list, ICommonCallback iCommonCallback) throws RemoteException {
        }

        @Override // com.huawei.hihealth.IHiHealthKitCommonOhos
        public void getUserPreference(OutOfBandData outOfBandData, List<String> list, IDataOperateListener iDataOperateListener) throws RemoteException {
        }

        @Override // com.huawei.hihealth.IHiHealthKitCommonOhos
        public void isAvailable(OutOfBandData outOfBandData, ICommonCallback iCommonCallback) throws RemoteException {
        }

        @Override // com.huawei.hihealth.IHiHealthKitCommonOhos
        public void queryDataOhos(OutOfBandData outOfBandData, HealthKitDictQuery healthKitDictQuery, IDataReadResultListenerOhos iDataReadResultListenerOhos) throws RemoteException {
        }

        @Override // com.huawei.hihealth.IHiHealthKitCommonOhos
        public void registerPackageName(String str) throws RemoteException {
        }

        @Override // com.huawei.hihealth.IHiHealthKitCommonOhos
        public void registerSportData(OutOfBandData outOfBandData, ISportDataCallback iSportDataCallback) throws RemoteException {
        }

        @Override // com.huawei.hihealth.IHiHealthKitCommonOhos
        public void saveSamplesOhos(OutOfBandData outOfBandData, List<HiHealthKitDataOhos> list, IDataOperateListenerOhos iDataOperateListenerOhos) throws RemoteException {
        }

        @Override // com.huawei.hihealth.IHiHealthKitCommonOhos
        public void setKitVersion(String str) throws RemoteException {
        }

        @Override // com.huawei.hihealth.IHiHealthKitCommonOhos
        public void setUserPreference(OutOfBandData outOfBandData, HiUserPreferenceData hiUserPreferenceData, boolean z, IDataOperateListener iDataOperateListener) throws RemoteException {
        }

        @Override // com.huawei.hihealth.IHiHealthKitCommonOhos
        public void startSportEnhance(OutOfBandData outOfBandData, StartSportParam startSportParam, ICommonCallback iCommonCallback) throws RemoteException {
        }

        @Override // com.huawei.hihealth.IHiHealthKitCommonOhos
        public void stopSport(OutOfBandData outOfBandData, ICommonCallback iCommonCallback) throws RemoteException {
        }

        @Override // com.huawei.hihealth.IHiHealthKitCommonOhos
        public void unregisterSportData(OutOfBandData outOfBandData, ICommonCallback iCommonCallback) throws RemoteException {
        }
    }

    void execQueryOhos(OutOfBandData outOfBandData, IDataReadResultListenerOhos iDataReadResultListenerOhos, int i, HiHealthDataQuery hiHealthDataQuery) throws RemoteException;

    String getCategory(int i) throws RemoteException;

    int getServiceApiLevel(OutOfBandData outOfBandData) throws RemoteException;

    void getUserInfo(OutOfBandData outOfBandData, List<String> list, ICommonCallback iCommonCallback) throws RemoteException;

    void getUserPreference(OutOfBandData outOfBandData, List<String> list, IDataOperateListener iDataOperateListener) throws RemoteException;

    void isAvailable(OutOfBandData outOfBandData, ICommonCallback iCommonCallback) throws RemoteException;

    void queryDataOhos(OutOfBandData outOfBandData, HealthKitDictQuery healthKitDictQuery, IDataReadResultListenerOhos iDataReadResultListenerOhos) throws RemoteException;

    void registerPackageName(String str) throws RemoteException;

    void registerSportData(OutOfBandData outOfBandData, ISportDataCallback iSportDataCallback) throws RemoteException;

    void saveSamplesOhos(OutOfBandData outOfBandData, List<HiHealthKitDataOhos> list, IDataOperateListenerOhos iDataOperateListenerOhos) throws RemoteException;

    void setKitVersion(String str) throws RemoteException;

    void setUserPreference(OutOfBandData outOfBandData, HiUserPreferenceData hiUserPreferenceData, boolean z, IDataOperateListener iDataOperateListener) throws RemoteException;

    void startSportEnhance(OutOfBandData outOfBandData, StartSportParam startSportParam, ICommonCallback iCommonCallback) throws RemoteException;

    void stopSport(OutOfBandData outOfBandData, ICommonCallback iCommonCallback) throws RemoteException;

    void unregisterSportData(OutOfBandData outOfBandData, ICommonCallback iCommonCallback) throws RemoteException;

    public static abstract class Stub extends Binder implements IHiHealthKitCommonOhos {
        static final int TRANSACTION_execQueryOhos = 1;
        static final int TRANSACTION_getCategory = 5;
        static final int TRANSACTION_getServiceApiLevel = 4;
        static final int TRANSACTION_getUserInfo = 8;
        static final int TRANSACTION_getUserPreference = 10;
        static final int TRANSACTION_isAvailable = 15;
        static final int TRANSACTION_queryDataOhos = 7;
        static final int TRANSACTION_registerPackageName = 3;
        static final int TRANSACTION_registerSportData = 13;
        static final int TRANSACTION_saveSamplesOhos = 6;
        static final int TRANSACTION_setKitVersion = 2;
        static final int TRANSACTION_setUserPreference = 9;
        static final int TRANSACTION_startSportEnhance = 11;
        static final int TRANSACTION_stopSport = 12;
        static final int TRANSACTION_unregisterSportData = 14;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, IHiHealthKitCommonOhos.DESCRIPTOR);
        }

        public static IHiHealthKitCommonOhos asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(IHiHealthKitCommonOhos.DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof IHiHealthKitCommonOhos)) {
                return (IHiHealthKitCommonOhos) queryLocalInterface;
            }
            return new Proxy(iBinder);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i >= 1 && i <= 16777215) {
                parcel.enforceInterface(IHiHealthKitCommonOhos.DESCRIPTOR);
            }
            if (i == 1598968902) {
                parcel2.writeString(IHiHealthKitCommonOhos.DESCRIPTOR);
                return true;
            }
            switch (i) {
                case 1:
                    execQueryOhos((OutOfBandData) _Parcel.bvu_(parcel, OutOfBandData.CREATOR), IDataReadResultListenerOhos.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt(), (HiHealthDataQuery) _Parcel.bvu_(parcel, HiHealthDataQuery.CREATOR));
                    parcel2.writeNoException();
                    return true;
                case 2:
                    setKitVersion(parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 3:
                    registerPackageName(parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 4:
                    int serviceApiLevel = getServiceApiLevel((OutOfBandData) _Parcel.bvu_(parcel, OutOfBandData.CREATOR));
                    parcel2.writeNoException();
                    parcel2.writeInt(serviceApiLevel);
                    return true;
                case 5:
                    String category = getCategory(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeString(category);
                    return true;
                case 6:
                    saveSamplesOhos((OutOfBandData) _Parcel.bvu_(parcel, OutOfBandData.CREATOR), parcel.createTypedArrayList(HiHealthKitDataOhos.CREATOR), IDataOperateListenerOhos.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 7:
                    queryDataOhos((OutOfBandData) _Parcel.bvu_(parcel, OutOfBandData.CREATOR), (HealthKitDictQuery) _Parcel.bvu_(parcel, HealthKitDictQuery.CREATOR), IDataReadResultListenerOhos.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 8:
                    getUserInfo((OutOfBandData) _Parcel.bvu_(parcel, OutOfBandData.CREATOR), parcel.createStringArrayList(), ICommonCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 9:
                    setUserPreference((OutOfBandData) _Parcel.bvu_(parcel, OutOfBandData.CREATOR), (HiUserPreferenceData) _Parcel.bvu_(parcel, HiUserPreferenceData.CREATOR), parcel.readInt() != 0, IDataOperateListener.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 10:
                    getUserPreference((OutOfBandData) _Parcel.bvu_(parcel, OutOfBandData.CREATOR), parcel.createStringArrayList(), IDataOperateListener.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 11:
                    startSportEnhance((OutOfBandData) _Parcel.bvu_(parcel, OutOfBandData.CREATOR), (StartSportParam) _Parcel.bvu_(parcel, StartSportParam.CREATOR), ICommonCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 12:
                    stopSport((OutOfBandData) _Parcel.bvu_(parcel, OutOfBandData.CREATOR), ICommonCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 13:
                    registerSportData((OutOfBandData) _Parcel.bvu_(parcel, OutOfBandData.CREATOR), ISportDataCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 14:
                    unregisterSportData((OutOfBandData) _Parcel.bvu_(parcel, OutOfBandData.CREATOR), ICommonCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 15:
                    isAvailable((OutOfBandData) _Parcel.bvu_(parcel, OutOfBandData.CREATOR), ICommonCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }

        static class Proxy implements IHiHealthKitCommonOhos {
            private IBinder d;

            Proxy(IBinder iBinder) {
                this.d = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.d;
            }

            @Override // com.huawei.hihealth.IHiHealthKitCommonOhos
            public void execQueryOhos(OutOfBandData outOfBandData, IDataReadResultListenerOhos iDataReadResultListenerOhos, int i, HiHealthDataQuery hiHealthDataQuery) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IHiHealthKitCommonOhos.DESCRIPTOR);
                    _Parcel.bvw_(obtain, outOfBandData, 0);
                    obtain.writeStrongInterface(iDataReadResultListenerOhos);
                    obtain.writeInt(i);
                    _Parcel.bvw_(obtain, hiHealthDataQuery, 0);
                    this.d.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hihealth.IHiHealthKitCommonOhos
            public void setKitVersion(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IHiHealthKitCommonOhos.DESCRIPTOR);
                    obtain.writeString(str);
                    this.d.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hihealth.IHiHealthKitCommonOhos
            public void registerPackageName(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IHiHealthKitCommonOhos.DESCRIPTOR);
                    obtain.writeString(str);
                    this.d.transact(3, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hihealth.IHiHealthKitCommonOhos
            public int getServiceApiLevel(OutOfBandData outOfBandData) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IHiHealthKitCommonOhos.DESCRIPTOR);
                    _Parcel.bvw_(obtain, outOfBandData, 0);
                    this.d.transact(4, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hihealth.IHiHealthKitCommonOhos
            public String getCategory(int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IHiHealthKitCommonOhos.DESCRIPTOR);
                    obtain.writeInt(i);
                    this.d.transact(5, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readString();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hihealth.IHiHealthKitCommonOhos
            public void saveSamplesOhos(OutOfBandData outOfBandData, List<HiHealthKitDataOhos> list, IDataOperateListenerOhos iDataOperateListenerOhos) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IHiHealthKitCommonOhos.DESCRIPTOR);
                    _Parcel.bvw_(obtain, outOfBandData, 0);
                    _Parcel.bvv_(obtain, list, 0);
                    obtain.writeStrongInterface(iDataOperateListenerOhos);
                    this.d.transact(6, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hihealth.IHiHealthKitCommonOhos
            public void queryDataOhos(OutOfBandData outOfBandData, HealthKitDictQuery healthKitDictQuery, IDataReadResultListenerOhos iDataReadResultListenerOhos) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IHiHealthKitCommonOhos.DESCRIPTOR);
                    _Parcel.bvw_(obtain, outOfBandData, 0);
                    _Parcel.bvw_(obtain, healthKitDictQuery, 0);
                    obtain.writeStrongInterface(iDataReadResultListenerOhos);
                    this.d.transact(7, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hihealth.IHiHealthKitCommonOhos
            public void getUserInfo(OutOfBandData outOfBandData, List<String> list, ICommonCallback iCommonCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IHiHealthKitCommonOhos.DESCRIPTOR);
                    _Parcel.bvw_(obtain, outOfBandData, 0);
                    obtain.writeStringList(list);
                    obtain.writeStrongInterface(iCommonCallback);
                    this.d.transact(8, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hihealth.IHiHealthKitCommonOhos
            public void setUserPreference(OutOfBandData outOfBandData, HiUserPreferenceData hiUserPreferenceData, boolean z, IDataOperateListener iDataOperateListener) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IHiHealthKitCommonOhos.DESCRIPTOR);
                    _Parcel.bvw_(obtain, outOfBandData, 0);
                    _Parcel.bvw_(obtain, hiUserPreferenceData, 0);
                    obtain.writeInt(z ? 1 : 0);
                    obtain.writeStrongInterface(iDataOperateListener);
                    this.d.transact(9, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hihealth.IHiHealthKitCommonOhos
            public void getUserPreference(OutOfBandData outOfBandData, List<String> list, IDataOperateListener iDataOperateListener) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IHiHealthKitCommonOhos.DESCRIPTOR);
                    _Parcel.bvw_(obtain, outOfBandData, 0);
                    obtain.writeStringList(list);
                    obtain.writeStrongInterface(iDataOperateListener);
                    this.d.transact(10, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hihealth.IHiHealthKitCommonOhos
            public void startSportEnhance(OutOfBandData outOfBandData, StartSportParam startSportParam, ICommonCallback iCommonCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IHiHealthKitCommonOhos.DESCRIPTOR);
                    _Parcel.bvw_(obtain, outOfBandData, 0);
                    _Parcel.bvw_(obtain, startSportParam, 0);
                    obtain.writeStrongInterface(iCommonCallback);
                    this.d.transact(11, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hihealth.IHiHealthKitCommonOhos
            public void stopSport(OutOfBandData outOfBandData, ICommonCallback iCommonCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IHiHealthKitCommonOhos.DESCRIPTOR);
                    _Parcel.bvw_(obtain, outOfBandData, 0);
                    obtain.writeStrongInterface(iCommonCallback);
                    this.d.transact(12, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hihealth.IHiHealthKitCommonOhos
            public void registerSportData(OutOfBandData outOfBandData, ISportDataCallback iSportDataCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IHiHealthKitCommonOhos.DESCRIPTOR);
                    _Parcel.bvw_(obtain, outOfBandData, 0);
                    obtain.writeStrongInterface(iSportDataCallback);
                    this.d.transact(13, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hihealth.IHiHealthKitCommonOhos
            public void unregisterSportData(OutOfBandData outOfBandData, ICommonCallback iCommonCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IHiHealthKitCommonOhos.DESCRIPTOR);
                    _Parcel.bvw_(obtain, outOfBandData, 0);
                    obtain.writeStrongInterface(iCommonCallback);
                    this.d.transact(14, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hihealth.IHiHealthKitCommonOhos
            public void isAvailable(OutOfBandData outOfBandData, ICommonCallback iCommonCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IHiHealthKitCommonOhos.DESCRIPTOR);
                    _Parcel.bvw_(obtain, outOfBandData, 0);
                    obtain.writeStrongInterface(iCommonCallback);
                    this.d.transact(15, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }
    }

    public static class _Parcel {
        /* JADX INFO: Access modifiers changed from: private */
        public static <T> T bvu_(Parcel parcel, Parcelable.Creator<T> creator) {
            if (parcel.readInt() != 0) {
                return creator.createFromParcel(parcel);
            }
            return null;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static <T extends Parcelable> void bvw_(Parcel parcel, T t, int i) {
            if (t != null) {
                parcel.writeInt(1);
                t.writeToParcel(parcel, i);
            } else {
                parcel.writeInt(0);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static <T extends Parcelable> void bvv_(Parcel parcel, List<T> list, int i) {
            if (list == null) {
                parcel.writeInt(-1);
                return;
            }
            int size = list.size();
            parcel.writeInt(size);
            for (int i2 = 0; i2 < size; i2++) {
                bvw_(parcel, list.get(i2), i);
            }
        }
    }
}
