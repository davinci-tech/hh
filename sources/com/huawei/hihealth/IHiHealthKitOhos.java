package com.huawei.hihealth;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import com.huawei.hihealth.ClientToken;
import com.huawei.hihealth.ICommonCallback;
import com.huawei.hihealth.ICommonListenerOhos;
import com.huawei.hihealth.IDataOperateListenerOhos;
import com.huawei.hihealth.IDataReadResultListenerOhos;
import com.huawei.hihealth.IRealTimeDataCallback;
import com.huawei.hihealth.IRegisterRealTimeCallback;
import com.huawei.hihealth.ISportDataCallback;
import java.util.List;

/* loaded from: classes.dex */
public interface IHiHealthKitOhos extends IInterface {
    public static final String DESCRIPTOR = "com.huawei.hihealth.IHiHealthKitOhos";

    public static class Default implements IHiHealthKitOhos {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.huawei.hihealth.IHiHealthKitOhos
        public void bindDevice(String str, String str2, String str3, int i, ICommonCallback iCommonCallback) throws RemoteException {
        }

        @Override // com.huawei.hihealth.IHiHealthKitOhos
        public void deleteSampleOhos(int i, HiHealthKitDataOhos hiHealthKitDataOhos, IDataOperateListenerOhos iDataOperateListenerOhos) throws RemoteException {
        }

        @Override // com.huawei.hihealth.IHiHealthKitOhos
        public void deleteSamplesOhos(int i, List<HiHealthKitDataOhos> list, IDataOperateListenerOhos iDataOperateListenerOhos) throws RemoteException {
        }

        @Override // com.huawei.hihealth.IHiHealthKitOhos
        public void execQueryOhos(int i, IDataReadResultListenerOhos iDataReadResultListenerOhos, int i2, HiHealthDataQuery hiHealthDataQuery) throws RemoteException {
        }

        @Override // com.huawei.hihealth.IHiHealthKitOhos
        public void getBirthdayOhos(int i, ICommonListenerOhos iCommonListenerOhos) throws RemoteException {
        }

        @Override // com.huawei.hihealth.IHiHealthKitOhos
        public String getCategory(int i) throws RemoteException {
            return null;
        }

        @Override // com.huawei.hihealth.IHiHealthKitOhos
        public void getCountOhos(int i, IDataReadResultListenerOhos iDataReadResultListenerOhos, HiHealthDataQuery hiHealthDataQuery) throws RemoteException {
        }

        @Override // com.huawei.hihealth.IHiHealthKitOhos
        public void getGenderOhos(int i, ICommonListenerOhos iCommonListenerOhos) throws RemoteException {
        }

        @Override // com.huawei.hihealth.IHiHealthKitOhos
        public void getHeightOhos(int i, ICommonListenerOhos iCommonListenerOhos) throws RemoteException {
        }

        @Override // com.huawei.hihealth.IHiHealthKitOhos
        public int getServiceApiLevel() throws RemoteException {
            return 0;
        }

        @Override // com.huawei.hihealth.IHiHealthKitOhos
        public void getWeightOhos(int i, ICommonListenerOhos iCommonListenerOhos) throws RemoteException {
        }

        @Override // com.huawei.hihealth.IHiHealthKitOhos
        public void queryDataOhos(HealthKitDictQuery healthKitDictQuery, IDataReadResultListenerOhos iDataReadResultListenerOhos) throws RemoteException {
        }

        @Override // com.huawei.hihealth.IHiHealthKitOhos
        public void registerDataAutoReportOhos(DataReportModel dataReportModel, IRegisterRealTimeCallback iRegisterRealTimeCallback) throws RemoteException {
        }

        @Override // com.huawei.hihealth.IHiHealthKitOhos
        public void registerPackageName(String str) throws RemoteException {
        }

        @Override // com.huawei.hihealth.IHiHealthKitOhos
        public void registerSportDataOhos(ISportDataCallback iSportDataCallback) throws RemoteException {
        }

        @Override // com.huawei.hihealth.IHiHealthKitOhos
        public void registerToken(ClientToken clientToken) throws RemoteException {
        }

        @Override // com.huawei.hihealth.IHiHealthKitOhos
        public void saveDeviceInfo(String str) throws RemoteException {
        }

        @Override // com.huawei.hihealth.IHiHealthKitOhos
        public void saveSampleOhos(int i, HiHealthKitDataOhos hiHealthKitDataOhos, IDataOperateListenerOhos iDataOperateListenerOhos) throws RemoteException {
        }

        @Override // com.huawei.hihealth.IHiHealthKitOhos
        public void saveSamplesOhos(int i, List<HiHealthKitDataOhos> list, IDataOperateListenerOhos iDataOperateListenerOhos) throws RemoteException {
        }

        @Override // com.huawei.hihealth.IHiHealthKitOhos
        public void setKitVersionOhos(String str) throws RemoteException {
        }

        @Override // com.huawei.hihealth.IHiHealthKitOhos
        public void startReadingHeartRateOhos(int i, IRealTimeDataCallback iRealTimeDataCallback) throws RemoteException {
        }

        @Override // com.huawei.hihealth.IHiHealthKitOhos
        public void startReadingRRIOhos(int i, IRealTimeDataCallback iRealTimeDataCallback) throws RemoteException {
        }

        @Override // com.huawei.hihealth.IHiHealthKitOhos
        public void startSportEnhanceOhos(StartSportParam startSportParam, ICommonCallback iCommonCallback) throws RemoteException {
        }

        @Override // com.huawei.hihealth.IHiHealthKitOhos
        public void startSportOhos(int i, ICommonCallback iCommonCallback) throws RemoteException {
        }

        @Override // com.huawei.hihealth.IHiHealthKitOhos
        public void stopReadingHeartRateOhos(int i, IRealTimeDataCallback iRealTimeDataCallback) throws RemoteException {
        }

        @Override // com.huawei.hihealth.IHiHealthKitOhos
        public void stopReadingRRIOhos(int i, IRealTimeDataCallback iRealTimeDataCallback) throws RemoteException {
        }

        @Override // com.huawei.hihealth.IHiHealthKitOhos
        public void stopSportOhos(ICommonCallback iCommonCallback) throws RemoteException {
        }

        @Override // com.huawei.hihealth.IHiHealthKitOhos
        public void unregisterDataAutoReportOhos(DataReportModel dataReportModel, IRegisterRealTimeCallback iRegisterRealTimeCallback) throws RemoteException {
        }

        @Override // com.huawei.hihealth.IHiHealthKitOhos
        public void unregisterSportDataOhos(ICommonCallback iCommonCallback) throws RemoteException {
        }
    }

    void bindDevice(String str, String str2, String str3, int i, ICommonCallback iCommonCallback) throws RemoteException;

    void deleteSampleOhos(int i, HiHealthKitDataOhos hiHealthKitDataOhos, IDataOperateListenerOhos iDataOperateListenerOhos) throws RemoteException;

    void deleteSamplesOhos(int i, List<HiHealthKitDataOhos> list, IDataOperateListenerOhos iDataOperateListenerOhos) throws RemoteException;

    void execQueryOhos(int i, IDataReadResultListenerOhos iDataReadResultListenerOhos, int i2, HiHealthDataQuery hiHealthDataQuery) throws RemoteException;

    void getBirthdayOhos(int i, ICommonListenerOhos iCommonListenerOhos) throws RemoteException;

    String getCategory(int i) throws RemoteException;

    void getCountOhos(int i, IDataReadResultListenerOhos iDataReadResultListenerOhos, HiHealthDataQuery hiHealthDataQuery) throws RemoteException;

    void getGenderOhos(int i, ICommonListenerOhos iCommonListenerOhos) throws RemoteException;

    void getHeightOhos(int i, ICommonListenerOhos iCommonListenerOhos) throws RemoteException;

    int getServiceApiLevel() throws RemoteException;

    void getWeightOhos(int i, ICommonListenerOhos iCommonListenerOhos) throws RemoteException;

    void queryDataOhos(HealthKitDictQuery healthKitDictQuery, IDataReadResultListenerOhos iDataReadResultListenerOhos) throws RemoteException;

    void registerDataAutoReportOhos(DataReportModel dataReportModel, IRegisterRealTimeCallback iRegisterRealTimeCallback) throws RemoteException;

    void registerPackageName(String str) throws RemoteException;

    void registerSportDataOhos(ISportDataCallback iSportDataCallback) throws RemoteException;

    void registerToken(ClientToken clientToken) throws RemoteException;

    void saveDeviceInfo(String str) throws RemoteException;

    void saveSampleOhos(int i, HiHealthKitDataOhos hiHealthKitDataOhos, IDataOperateListenerOhos iDataOperateListenerOhos) throws RemoteException;

    void saveSamplesOhos(int i, List<HiHealthKitDataOhos> list, IDataOperateListenerOhos iDataOperateListenerOhos) throws RemoteException;

    void setKitVersionOhos(String str) throws RemoteException;

    void startReadingHeartRateOhos(int i, IRealTimeDataCallback iRealTimeDataCallback) throws RemoteException;

    void startReadingRRIOhos(int i, IRealTimeDataCallback iRealTimeDataCallback) throws RemoteException;

    void startSportEnhanceOhos(StartSportParam startSportParam, ICommonCallback iCommonCallback) throws RemoteException;

    void startSportOhos(int i, ICommonCallback iCommonCallback) throws RemoteException;

    void stopReadingHeartRateOhos(int i, IRealTimeDataCallback iRealTimeDataCallback) throws RemoteException;

    void stopReadingRRIOhos(int i, IRealTimeDataCallback iRealTimeDataCallback) throws RemoteException;

    void stopSportOhos(ICommonCallback iCommonCallback) throws RemoteException;

    void unregisterDataAutoReportOhos(DataReportModel dataReportModel, IRegisterRealTimeCallback iRegisterRealTimeCallback) throws RemoteException;

    void unregisterSportDataOhos(ICommonCallback iCommonCallback) throws RemoteException;

    public static abstract class Stub extends Binder implements IHiHealthKitOhos {
        static final int TRANSACTION_bindDevice = 26;
        static final int TRANSACTION_deleteSampleOhos = 14;
        static final int TRANSACTION_deleteSamplesOhos = 15;
        static final int TRANSACTION_execQueryOhos = 5;
        static final int TRANSACTION_getBirthdayOhos = 2;
        static final int TRANSACTION_getCategory = 23;
        static final int TRANSACTION_getCountOhos = 11;
        static final int TRANSACTION_getGenderOhos = 1;
        static final int TRANSACTION_getHeightOhos = 3;
        static final int TRANSACTION_getServiceApiLevel = 10;
        static final int TRANSACTION_getWeightOhos = 4;
        static final int TRANSACTION_queryDataOhos = 27;
        static final int TRANSACTION_registerDataAutoReportOhos = 24;
        static final int TRANSACTION_registerPackageName = 9;
        static final int TRANSACTION_registerSportDataOhos = 21;
        static final int TRANSACTION_registerToken = 18;
        static final int TRANSACTION_saveDeviceInfo = 28;
        static final int TRANSACTION_saveSampleOhos = 12;
        static final int TRANSACTION_saveSamplesOhos = 13;
        static final int TRANSACTION_setKitVersionOhos = 8;
        static final int TRANSACTION_startReadingHeartRateOhos = 6;
        static final int TRANSACTION_startReadingRRIOhos = 16;
        static final int TRANSACTION_startSportEnhanceOhos = 29;
        static final int TRANSACTION_startSportOhos = 19;
        static final int TRANSACTION_stopReadingHeartRateOhos = 7;
        static final int TRANSACTION_stopReadingRRIOhos = 17;
        static final int TRANSACTION_stopSportOhos = 20;
        static final int TRANSACTION_unregisterDataAutoReportOhos = 25;
        static final int TRANSACTION_unregisterSportDataOhos = 22;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, IHiHealthKitOhos.DESCRIPTOR);
        }

        public static IHiHealthKitOhos asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(IHiHealthKitOhos.DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof IHiHealthKitOhos)) {
                return (IHiHealthKitOhos) queryLocalInterface;
            }
            return new Proxy(iBinder);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i >= 1 && i <= 16777215) {
                parcel.enforceInterface(IHiHealthKitOhos.DESCRIPTOR);
            }
            if (i == 1598968902) {
                parcel2.writeString(IHiHealthKitOhos.DESCRIPTOR);
                return true;
            }
            switch (i) {
                case 1:
                    getGenderOhos(parcel.readInt(), ICommonListenerOhos.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 2:
                    getBirthdayOhos(parcel.readInt(), ICommonListenerOhos.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 3:
                    getHeightOhos(parcel.readInt(), ICommonListenerOhos.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 4:
                    getWeightOhos(parcel.readInt(), ICommonListenerOhos.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 5:
                    execQueryOhos(parcel.readInt(), IDataReadResultListenerOhos.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt(), (HiHealthDataQuery) _Parcel.bvE_(parcel, HiHealthDataQuery.CREATOR));
                    parcel2.writeNoException();
                    return true;
                case 6:
                    startReadingHeartRateOhos(parcel.readInt(), IRealTimeDataCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 7:
                    stopReadingHeartRateOhos(parcel.readInt(), IRealTimeDataCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 8:
                    setKitVersionOhos(parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 9:
                    registerPackageName(parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 10:
                    int serviceApiLevel = getServiceApiLevel();
                    parcel2.writeNoException();
                    parcel2.writeInt(serviceApiLevel);
                    return true;
                case 11:
                    getCountOhos(parcel.readInt(), IDataReadResultListenerOhos.Stub.asInterface(parcel.readStrongBinder()), (HiHealthDataQuery) _Parcel.bvE_(parcel, HiHealthDataQuery.CREATOR));
                    parcel2.writeNoException();
                    return true;
                case 12:
                    saveSampleOhos(parcel.readInt(), (HiHealthKitDataOhos) _Parcel.bvE_(parcel, HiHealthKitDataOhos.CREATOR), IDataOperateListenerOhos.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 13:
                    saveSamplesOhos(parcel.readInt(), parcel.createTypedArrayList(HiHealthKitDataOhos.CREATOR), IDataOperateListenerOhos.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 14:
                    deleteSampleOhos(parcel.readInt(), (HiHealthKitDataOhos) _Parcel.bvE_(parcel, HiHealthKitDataOhos.CREATOR), IDataOperateListenerOhos.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 15:
                    deleteSamplesOhos(parcel.readInt(), parcel.createTypedArrayList(HiHealthKitDataOhos.CREATOR), IDataOperateListenerOhos.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 16:
                    startReadingRRIOhos(parcel.readInt(), IRealTimeDataCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 17:
                    stopReadingRRIOhos(parcel.readInt(), IRealTimeDataCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 18:
                    registerToken(ClientToken.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 19:
                    startSportOhos(parcel.readInt(), ICommonCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 20:
                    stopSportOhos(ICommonCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 21:
                    registerSportDataOhos(ISportDataCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 22:
                    unregisterSportDataOhos(ICommonCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 23:
                    String category = getCategory(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeString(category);
                    return true;
                case 24:
                    registerDataAutoReportOhos((DataReportModel) _Parcel.bvE_(parcel, DataReportModel.CREATOR), IRegisterRealTimeCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 25:
                    unregisterDataAutoReportOhos((DataReportModel) _Parcel.bvE_(parcel, DataReportModel.CREATOR), IRegisterRealTimeCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 26:
                    bindDevice(parcel.readString(), parcel.readString(), parcel.readString(), parcel.readInt(), ICommonCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 27:
                    queryDataOhos((HealthKitDictQuery) _Parcel.bvE_(parcel, HealthKitDictQuery.CREATOR), IDataReadResultListenerOhos.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 28:
                    saveDeviceInfo(parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 29:
                    startSportEnhanceOhos((StartSportParam) _Parcel.bvE_(parcel, StartSportParam.CREATOR), ICommonCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }

        static class Proxy implements IHiHealthKitOhos {
            private IBinder b;

            Proxy(IBinder iBinder) {
                this.b = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.b;
            }

            @Override // com.huawei.hihealth.IHiHealthKitOhos
            public void getGenderOhos(int i, ICommonListenerOhos iCommonListenerOhos) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IHiHealthKitOhos.DESCRIPTOR);
                    obtain.writeInt(i);
                    obtain.writeStrongInterface(iCommonListenerOhos);
                    this.b.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hihealth.IHiHealthKitOhos
            public void getBirthdayOhos(int i, ICommonListenerOhos iCommonListenerOhos) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IHiHealthKitOhos.DESCRIPTOR);
                    obtain.writeInt(i);
                    obtain.writeStrongInterface(iCommonListenerOhos);
                    this.b.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hihealth.IHiHealthKitOhos
            public void getHeightOhos(int i, ICommonListenerOhos iCommonListenerOhos) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IHiHealthKitOhos.DESCRIPTOR);
                    obtain.writeInt(i);
                    obtain.writeStrongInterface(iCommonListenerOhos);
                    this.b.transact(3, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hihealth.IHiHealthKitOhos
            public void getWeightOhos(int i, ICommonListenerOhos iCommonListenerOhos) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IHiHealthKitOhos.DESCRIPTOR);
                    obtain.writeInt(i);
                    obtain.writeStrongInterface(iCommonListenerOhos);
                    this.b.transact(4, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hihealth.IHiHealthKitOhos
            public void execQueryOhos(int i, IDataReadResultListenerOhos iDataReadResultListenerOhos, int i2, HiHealthDataQuery hiHealthDataQuery) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IHiHealthKitOhos.DESCRIPTOR);
                    obtain.writeInt(i);
                    obtain.writeStrongInterface(iDataReadResultListenerOhos);
                    obtain.writeInt(i2);
                    _Parcel.bvG_(obtain, hiHealthDataQuery, 0);
                    this.b.transact(5, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hihealth.IHiHealthKitOhos
            public void startReadingHeartRateOhos(int i, IRealTimeDataCallback iRealTimeDataCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IHiHealthKitOhos.DESCRIPTOR);
                    obtain.writeInt(i);
                    obtain.writeStrongInterface(iRealTimeDataCallback);
                    this.b.transact(6, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hihealth.IHiHealthKitOhos
            public void stopReadingHeartRateOhos(int i, IRealTimeDataCallback iRealTimeDataCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IHiHealthKitOhos.DESCRIPTOR);
                    obtain.writeInt(i);
                    obtain.writeStrongInterface(iRealTimeDataCallback);
                    this.b.transact(7, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hihealth.IHiHealthKitOhos
            public void setKitVersionOhos(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IHiHealthKitOhos.DESCRIPTOR);
                    obtain.writeString(str);
                    this.b.transact(8, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hihealth.IHiHealthKitOhos
            public void registerPackageName(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IHiHealthKitOhos.DESCRIPTOR);
                    obtain.writeString(str);
                    this.b.transact(9, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hihealth.IHiHealthKitOhos
            public int getServiceApiLevel() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IHiHealthKitOhos.DESCRIPTOR);
                    this.b.transact(10, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hihealth.IHiHealthKitOhos
            public void getCountOhos(int i, IDataReadResultListenerOhos iDataReadResultListenerOhos, HiHealthDataQuery hiHealthDataQuery) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IHiHealthKitOhos.DESCRIPTOR);
                    obtain.writeInt(i);
                    obtain.writeStrongInterface(iDataReadResultListenerOhos);
                    _Parcel.bvG_(obtain, hiHealthDataQuery, 0);
                    this.b.transact(11, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hihealth.IHiHealthKitOhos
            public void saveSampleOhos(int i, HiHealthKitDataOhos hiHealthKitDataOhos, IDataOperateListenerOhos iDataOperateListenerOhos) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IHiHealthKitOhos.DESCRIPTOR);
                    obtain.writeInt(i);
                    _Parcel.bvG_(obtain, hiHealthKitDataOhos, 0);
                    obtain.writeStrongInterface(iDataOperateListenerOhos);
                    this.b.transact(12, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hihealth.IHiHealthKitOhos
            public void saveSamplesOhos(int i, List<HiHealthKitDataOhos> list, IDataOperateListenerOhos iDataOperateListenerOhos) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IHiHealthKitOhos.DESCRIPTOR);
                    obtain.writeInt(i);
                    _Parcel.bvF_(obtain, list, 0);
                    obtain.writeStrongInterface(iDataOperateListenerOhos);
                    this.b.transact(13, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hihealth.IHiHealthKitOhos
            public void deleteSampleOhos(int i, HiHealthKitDataOhos hiHealthKitDataOhos, IDataOperateListenerOhos iDataOperateListenerOhos) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IHiHealthKitOhos.DESCRIPTOR);
                    obtain.writeInt(i);
                    _Parcel.bvG_(obtain, hiHealthKitDataOhos, 0);
                    obtain.writeStrongInterface(iDataOperateListenerOhos);
                    this.b.transact(14, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hihealth.IHiHealthKitOhos
            public void deleteSamplesOhos(int i, List<HiHealthKitDataOhos> list, IDataOperateListenerOhos iDataOperateListenerOhos) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IHiHealthKitOhos.DESCRIPTOR);
                    obtain.writeInt(i);
                    _Parcel.bvF_(obtain, list, 0);
                    obtain.writeStrongInterface(iDataOperateListenerOhos);
                    this.b.transact(15, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hihealth.IHiHealthKitOhos
            public void startReadingRRIOhos(int i, IRealTimeDataCallback iRealTimeDataCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IHiHealthKitOhos.DESCRIPTOR);
                    obtain.writeInt(i);
                    obtain.writeStrongInterface(iRealTimeDataCallback);
                    this.b.transact(16, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hihealth.IHiHealthKitOhos
            public void stopReadingRRIOhos(int i, IRealTimeDataCallback iRealTimeDataCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IHiHealthKitOhos.DESCRIPTOR);
                    obtain.writeInt(i);
                    obtain.writeStrongInterface(iRealTimeDataCallback);
                    this.b.transact(17, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hihealth.IHiHealthKitOhos
            public void registerToken(ClientToken clientToken) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IHiHealthKitOhos.DESCRIPTOR);
                    obtain.writeStrongInterface(clientToken);
                    this.b.transact(18, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hihealth.IHiHealthKitOhos
            public void startSportOhos(int i, ICommonCallback iCommonCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IHiHealthKitOhos.DESCRIPTOR);
                    obtain.writeInt(i);
                    obtain.writeStrongInterface(iCommonCallback);
                    this.b.transact(19, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hihealth.IHiHealthKitOhos
            public void stopSportOhos(ICommonCallback iCommonCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IHiHealthKitOhos.DESCRIPTOR);
                    obtain.writeStrongInterface(iCommonCallback);
                    this.b.transact(20, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hihealth.IHiHealthKitOhos
            public void registerSportDataOhos(ISportDataCallback iSportDataCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IHiHealthKitOhos.DESCRIPTOR);
                    obtain.writeStrongInterface(iSportDataCallback);
                    this.b.transact(21, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hihealth.IHiHealthKitOhos
            public void unregisterSportDataOhos(ICommonCallback iCommonCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IHiHealthKitOhos.DESCRIPTOR);
                    obtain.writeStrongInterface(iCommonCallback);
                    this.b.transact(22, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hihealth.IHiHealthKitOhos
            public String getCategory(int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IHiHealthKitOhos.DESCRIPTOR);
                    obtain.writeInt(i);
                    this.b.transact(23, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readString();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hihealth.IHiHealthKitOhos
            public void registerDataAutoReportOhos(DataReportModel dataReportModel, IRegisterRealTimeCallback iRegisterRealTimeCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IHiHealthKitOhos.DESCRIPTOR);
                    _Parcel.bvG_(obtain, dataReportModel, 0);
                    obtain.writeStrongInterface(iRegisterRealTimeCallback);
                    this.b.transact(24, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hihealth.IHiHealthKitOhos
            public void unregisterDataAutoReportOhos(DataReportModel dataReportModel, IRegisterRealTimeCallback iRegisterRealTimeCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IHiHealthKitOhos.DESCRIPTOR);
                    _Parcel.bvG_(obtain, dataReportModel, 0);
                    obtain.writeStrongInterface(iRegisterRealTimeCallback);
                    this.b.transact(25, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hihealth.IHiHealthKitOhos
            public void bindDevice(String str, String str2, String str3, int i, ICommonCallback iCommonCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IHiHealthKitOhos.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    obtain.writeString(str3);
                    obtain.writeInt(i);
                    obtain.writeStrongInterface(iCommonCallback);
                    this.b.transact(26, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hihealth.IHiHealthKitOhos
            public void queryDataOhos(HealthKitDictQuery healthKitDictQuery, IDataReadResultListenerOhos iDataReadResultListenerOhos) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IHiHealthKitOhos.DESCRIPTOR);
                    _Parcel.bvG_(obtain, healthKitDictQuery, 0);
                    obtain.writeStrongInterface(iDataReadResultListenerOhos);
                    this.b.transact(27, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hihealth.IHiHealthKitOhos
            public void saveDeviceInfo(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IHiHealthKitOhos.DESCRIPTOR);
                    obtain.writeString(str);
                    this.b.transact(28, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hihealth.IHiHealthKitOhos
            public void startSportEnhanceOhos(StartSportParam startSportParam, ICommonCallback iCommonCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IHiHealthKitOhos.DESCRIPTOR);
                    _Parcel.bvG_(obtain, startSportParam, 0);
                    obtain.writeStrongInterface(iCommonCallback);
                    this.b.transact(29, obtain, obtain2, 0);
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
        public static <T> T bvE_(Parcel parcel, Parcelable.Creator<T> creator) {
            if (parcel.readInt() != 0) {
                return creator.createFromParcel(parcel);
            }
            return null;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static <T extends Parcelable> void bvG_(Parcel parcel, T t, int i) {
            if (t != null) {
                parcel.writeInt(1);
                t.writeToParcel(parcel, i);
            } else {
                parcel.writeInt(0);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static <T extends Parcelable> void bvF_(Parcel parcel, List<T> list, int i) {
            if (list == null) {
                parcel.writeInt(-1);
                return;
            }
            int size = list.size();
            parcel.writeInt(size);
            for (int i2 = 0; i2 < size; i2++) {
                bvG_(parcel, list.get(i2), i);
            }
        }
    }
}
