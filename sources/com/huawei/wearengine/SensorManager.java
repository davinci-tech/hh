package com.huawei.wearengine;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import com.huawei.wearengine.device.Device;
import com.huawei.wearengine.sensor.AsyncReadCallback;
import com.huawei.wearengine.sensor.AsyncStopCallback;
import com.huawei.wearengine.sensor.Sensor;
import java.util.List;

/* loaded from: classes9.dex */
public interface SensorManager extends IInterface {
    public static final String DESCRIPTOR = "com.huawei.wearengine.SensorManager";

    int asyncRead(Device device, Sensor sensor, AsyncReadCallback asyncReadCallback) throws RemoteException;

    int asyncReadSensors(Device device, List<Sensor> list, AsyncReadCallback asyncReadCallback) throws RemoteException;

    List<Sensor> getSensorList(Device device) throws RemoteException;

    int stopAsyncRead(Device device, Sensor sensor, AsyncStopCallback asyncStopCallback) throws RemoteException;

    int stopAsyncReadSensors(Device device, List<Sensor> list, AsyncStopCallback asyncStopCallback) throws RemoteException;

    public static abstract class Stub extends Binder implements SensorManager {
        static final int TRANSACTION_asyncRead = 2;
        static final int TRANSACTION_asyncReadSensors = 3;
        static final int TRANSACTION_getSensorList = 1;
        static final int TRANSACTION_stopAsyncRead = 4;
        static final int TRANSACTION_stopAsyncReadSensors = 5;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, SensorManager.DESCRIPTOR);
        }

        public static SensorManager asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(SensorManager.DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof SensorManager)) {
                return (SensorManager) queryLocalInterface;
            }
            return new e(iBinder);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i >= 1 && i <= 16777215) {
                parcel.enforceInterface(SensorManager.DESCRIPTOR);
            }
            if (i == 1598968902) {
                parcel2.writeString(SensorManager.DESCRIPTOR);
                return true;
            }
            if (i == 1) {
                List<Sensor> sensorList = getSensorList((Device) c.fcM_(parcel, Device.CREATOR));
                parcel2.writeNoException();
                c.fcN_(parcel2, sensorList, 1);
            } else if (i == 2) {
                int asyncRead = asyncRead((Device) c.fcM_(parcel, Device.CREATOR), (Sensor) c.fcM_(parcel, Sensor.CREATOR), AsyncReadCallback.Stub.asInterface(parcel.readStrongBinder()));
                parcel2.writeNoException();
                parcel2.writeInt(asyncRead);
            } else if (i == 3) {
                int asyncReadSensors = asyncReadSensors((Device) c.fcM_(parcel, Device.CREATOR), parcel.createTypedArrayList(Sensor.CREATOR), AsyncReadCallback.Stub.asInterface(parcel.readStrongBinder()));
                parcel2.writeNoException();
                parcel2.writeInt(asyncReadSensors);
            } else if (i == 4) {
                int stopAsyncRead = stopAsyncRead((Device) c.fcM_(parcel, Device.CREATOR), (Sensor) c.fcM_(parcel, Sensor.CREATOR), AsyncStopCallback.Stub.asInterface(parcel.readStrongBinder()));
                parcel2.writeNoException();
                parcel2.writeInt(stopAsyncRead);
            } else if (i == 5) {
                int stopAsyncReadSensors = stopAsyncReadSensors((Device) c.fcM_(parcel, Device.CREATOR), parcel.createTypedArrayList(Sensor.CREATOR), AsyncStopCallback.Stub.asInterface(parcel.readStrongBinder()));
                parcel2.writeNoException();
                parcel2.writeInt(stopAsyncReadSensors);
            } else {
                return super.onTransact(i, parcel, parcel2, i2);
            }
            return true;
        }

        static class e implements SensorManager {
            private IBinder d;

            e(IBinder iBinder) {
                this.d = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.d;
            }

            @Override // com.huawei.wearengine.SensorManager
            public List<Sensor> getSensorList(Device device) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(SensorManager.DESCRIPTOR);
                    c.fcO_(obtain, device, 0);
                    this.d.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.createTypedArrayList(Sensor.CREATOR);
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.wearengine.SensorManager
            public int asyncRead(Device device, Sensor sensor, AsyncReadCallback asyncReadCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(SensorManager.DESCRIPTOR);
                    c.fcO_(obtain, device, 0);
                    c.fcO_(obtain, sensor, 0);
                    obtain.writeStrongInterface(asyncReadCallback);
                    this.d.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.wearengine.SensorManager
            public int asyncReadSensors(Device device, List<Sensor> list, AsyncReadCallback asyncReadCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(SensorManager.DESCRIPTOR);
                    c.fcO_(obtain, device, 0);
                    c.fcN_(obtain, list, 0);
                    obtain.writeStrongInterface(asyncReadCallback);
                    this.d.transact(3, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.wearengine.SensorManager
            public int stopAsyncRead(Device device, Sensor sensor, AsyncStopCallback asyncStopCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(SensorManager.DESCRIPTOR);
                    c.fcO_(obtain, device, 0);
                    c.fcO_(obtain, sensor, 0);
                    obtain.writeStrongInterface(asyncStopCallback);
                    this.d.transact(4, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.wearengine.SensorManager
            public int stopAsyncReadSensors(Device device, List<Sensor> list, AsyncStopCallback asyncStopCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(SensorManager.DESCRIPTOR);
                    c.fcO_(obtain, device, 0);
                    c.fcN_(obtain, list, 0);
                    obtain.writeStrongInterface(asyncStopCallback);
                    this.d.transact(5, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }
    }

    public static class c {
        /* JADX INFO: Access modifiers changed from: private */
        public static <T> T fcM_(Parcel parcel, Parcelable.Creator<T> creator) {
            if (parcel.readInt() != 0) {
                return creator.createFromParcel(parcel);
            }
            return null;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static <T extends Parcelable> void fcO_(Parcel parcel, T t, int i) {
            if (t != null) {
                parcel.writeInt(1);
                t.writeToParcel(parcel, i);
            } else {
                parcel.writeInt(0);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static <T extends Parcelable> void fcN_(Parcel parcel, List<T> list, int i) {
            if (list == null) {
                parcel.writeInt(-1);
                return;
            }
            int size = list.size();
            parcel.writeInt(size);
            for (int i2 = 0; i2 < size; i2++) {
                fcO_(parcel, list.get(i2), i);
            }
        }
    }
}
