package com.huawei.hms.maps.model.internal;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import com.huawei.hms.maps.model.CoordinateLatLng;
import com.huawei.hms.maps.model.LatLng;
import com.huawei.hms.maps.model.LatLngBounds;
import com.huawei.hms.maps.model.PatternItem;
import java.util.List;
import java.util.Map;

/* loaded from: classes4.dex */
public class mab {
    public static IBinder j(IBinder iBinder, String str, int i) {
        return a(iBinder, str, i, null, null, 0);
    }

    public static void i(IBinder iBinder, String str, int i) {
        Parcel obtain = Parcel.obtain();
        Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken(str);
            iBinder.transact(i, obtain, obtain2, 0);
            obtain2.readException();
        } finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }

    public static List<PatternItem> h(IBinder iBinder, String str, int i) {
        Parcel obtain = Parcel.obtain();
        Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken(str);
            iBinder.transact(i, obtain, obtain2, 0);
            obtain2.readException();
            return obtain2.createTypedArrayList(PatternItem.CREATOR);
        } finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }

    public static List<LatLng> g(IBinder iBinder, String str, int i) {
        Parcel obtain = Parcel.obtain();
        Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken(str);
            iBinder.transact(i, obtain, obtain2, 0);
            obtain2.readException();
            return obtain2.createTypedArrayList(LatLng.CREATOR);
        } finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }

    public static LatLngBounds f(IBinder iBinder, String str, int i) {
        Parcel obtain = Parcel.obtain();
        Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken(str);
            iBinder.transact(i, obtain, obtain2, 0);
            obtain2.readException();
            return obtain2.readInt() != 0 ? LatLngBounds.CREATOR.createFromParcel(obtain2) : null;
        } finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }

    public static LatLng e(IBinder iBinder, String str, int i) {
        Parcel obtain = Parcel.obtain();
        Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken(str);
            iBinder.transact(i, obtain, obtain2, 0);
            obtain2.readException();
            return obtain2.readInt() != 0 ? LatLng.CREATOR.createFromParcel(obtain2) : null;
        } finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }

    public static String d(IBinder iBinder, String str, int i) {
        Parcel obtain = Parcel.obtain();
        Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken(str);
            iBinder.transact(i, obtain, obtain2, 0);
            obtain2.readException();
            return obtain2.readString();
        } finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }

    public static float c(IBinder iBinder, String str, int i) {
        Parcel obtain = Parcel.obtain();
        Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken(str);
            iBinder.transact(i, obtain, obtain2, 0);
            obtain2.readException();
            return obtain2.readFloat();
        } finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }

    public static void b(List<PatternItem> list, IBinder iBinder, String str, int i) {
        Parcel obtain = Parcel.obtain();
        Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken(str);
            obtain.writeTypedList(list);
            iBinder.transact(i, obtain, obtain2, 0);
            obtain2.readException();
        } finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }

    public static int b(IBinder iBinder, String str, int i) {
        Parcel obtain = Parcel.obtain();
        Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken(str);
            iBinder.transact(i, obtain, obtain2, 0);
            obtain2.readException();
            return obtain2.readInt();
        } finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }

    public static CoordinateLatLng[] a(CoordinateLatLng[] coordinateLatLngArr, IBinder iBinder, String str, int i) {
        Parcel obtain = Parcel.obtain();
        Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken(str);
            obtain.writeTypedArray(coordinateLatLngArr, 0);
            iBinder.transact(i, obtain, obtain2, 0);
            obtain2.readException();
            return (CoordinateLatLng[]) obtain2.createTypedArray(CoordinateLatLng.CREATOR);
        } finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }

    public static boolean a(IInterface iInterface, IBinder iBinder, String str, int i) {
        Parcel obtain = Parcel.obtain();
        Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken(str);
            obtain.writeStrongBinder(iInterface != null ? iInterface.asBinder() : null);
            iBinder.transact(i, obtain, obtain2, 0);
            obtain2.readException();
            return obtain2.readInt() != 0;
        } finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }

    public static boolean a(IBinder iBinder, String str, int i, boolean z) {
        Parcel obtain = Parcel.obtain();
        Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken(str);
            obtain.writeInt(z ? 1 : 0);
            iBinder.transact(i, obtain, obtain2, 0);
            obtain2.readException();
            return obtain2.readInt() != 0;
        } finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }

    public static boolean a(IBinder iBinder, String str, int i) {
        return b(iBinder, str, i) != 0;
    }

    public static void a(boolean z, IBinder iBinder, String str, int i) {
        a(iBinder, str, i, Integer.valueOf(z ? 1 : 0));
    }

    public static void a(Map map, IBinder iBinder, String str, int i) {
        Parcel obtain = Parcel.obtain();
        Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken(str);
            obtain.writeMap(map);
            iBinder.transact(i, obtain, obtain2, 0);
            obtain2.readException();
        } finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }

    public static void a(List<LatLng> list, IBinder iBinder, String str, int i) {
        Parcel obtain = Parcel.obtain();
        Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken(str);
            obtain.writeTypedList(list);
            iBinder.transact(i, obtain, obtain2, 0);
            obtain2.readException();
        } finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }

    public static void a(String str, IBinder iBinder, String str2, int i) {
        Parcel obtain = Parcel.obtain();
        Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken(str2);
            obtain.writeString(str);
            iBinder.transact(i, obtain, obtain2, 0);
            obtain2.readException();
        } finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }

    private static void a(Parcel parcel, Number number) {
        if (number instanceof Integer) {
            parcel.writeInt(number.intValue());
            return;
        }
        if (number instanceof Float) {
            parcel.writeFloat(number.floatValue());
            return;
        }
        if (number instanceof Double) {
            parcel.writeDouble(number.doubleValue());
        } else {
            if (number instanceof Byte) {
                parcel.writeByte(number.byteValue());
                return;
            }
            Log.e("DelegateUtil", "setNumber with not support type." + number.toString());
        }
    }

    public static void a(IBinder iBinder, String str, int i, String... strArr) {
        Parcel obtain = Parcel.obtain();
        Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken(str);
            for (String str2 : strArr) {
                obtain.writeString(str2);
            }
            iBinder.transact(i, obtain, obtain2, 0);
            obtain2.readException();
        } finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }

    public static void a(IBinder iBinder, String str, int i, Number... numberArr) {
        Parcel obtain = Parcel.obtain();
        Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken(str);
            for (Number number : numberArr) {
                a(obtain, number);
            }
            iBinder.transact(i, obtain, obtain2, 0);
            obtain2.readException();
        } finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }

    public static void a(IBinder iBinder, String str, int i, Integer... numArr) {
        a(iBinder, str, i, (Number[]) numArr);
    }

    public static void a(IBinder iBinder, String str, int i, Float... fArr) {
        a(iBinder, str, i, (Number[]) fArr);
    }

    public static void a(IBinder iBinder, String str, int i, Parcelable... parcelableArr) {
        a(iBinder, str, i, (Number) null, parcelableArr);
    }

    public static void a(IBinder iBinder, String str, int i, IBinder... iBinderArr) {
        Parcel obtain = Parcel.obtain();
        Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken(str);
            for (IBinder iBinder2 : iBinderArr) {
                obtain.writeStrongBinder(iBinder2);
            }
            iBinder.transact(i, obtain, obtain2, 0);
            obtain2.readException();
        } finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }

    public static void a(IBinder iBinder, String str, int i, Number number, Parcelable... parcelableArr) {
        Parcel obtain = Parcel.obtain();
        Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken(str);
            for (Parcelable parcelable : parcelableArr) {
                if (parcelable != null) {
                    obtain.writeInt(1);
                    parcelable.writeToParcel(obtain, 0);
                } else {
                    obtain.writeInt(0);
                }
            }
            if (number != null) {
                a(obtain, number);
            }
            iBinder.transact(i, obtain, obtain2, 0);
            obtain2.readException();
        } finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }

    public static void a(IBinder iBinder, IBinder iBinder2, String str, int i) {
        Parcel obtain = Parcel.obtain();
        Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken(str);
            obtain.writeStrongBinder(iBinder);
            iBinder2.transact(i, obtain, obtain2, 0);
            obtain2.readException();
        } finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }

    public static Number a(IBinder iBinder, String str, int i, int i2, Parcelable... parcelableArr) {
        Parcel obtain = Parcel.obtain();
        Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken(str);
            for (Parcelable parcelable : parcelableArr) {
                if (parcelable != null) {
                    obtain.writeInt(1);
                    parcelable.writeToParcel(obtain, 0);
                } else {
                    obtain.writeInt(0);
                }
            }
            iBinder.transact(i, obtain, obtain2, 0);
            obtain2.readException();
            if (i2 == 0) {
                int readInt = obtain2.readInt();
                obtain2.recycle();
                obtain.recycle();
                return Integer.valueOf(readInt);
            }
            if (i2 == 1) {
                float readFloat = obtain2.readFloat();
                obtain2.recycle();
                obtain.recycle();
                return Float.valueOf(readFloat);
            }
            if (i2 == 2) {
                double readDouble = obtain2.readDouble();
                obtain2.recycle();
                obtain.recycle();
                return Double.valueOf(readDouble);
            }
            if (i2 == 3) {
                byte readByte = obtain2.readByte();
                obtain2.recycle();
                obtain.recycle();
                return Byte.valueOf(readByte);
            }
            Log.e("DelegateUtil", "read number from parcel error , with not support type." + i2);
            obtain2.recycle();
            obtain.recycle();
            return null;
        } catch (Throwable th) {
            obtain2.recycle();
            obtain.recycle();
            throw th;
        }
    }

    public static IBinder a(IBinder iBinder, String str, int i, Parcelable parcelable) {
        return a(iBinder, str, i, null, parcelable, 3);
    }

    private static IBinder a(IBinder iBinder, String str, int i, IBinder iBinder2, Parcelable parcelable, int i2) {
        Parcel obtain = Parcel.obtain();
        Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken(str);
            if (i2 == 1 || i2 == 2) {
                obtain.writeStrongBinder(iBinder2);
            }
            if (i2 == 2 || i2 == 3) {
                if (parcelable != null) {
                    obtain.writeInt(1);
                    parcelable.writeToParcel(obtain, 0);
                } else {
                    obtain.writeInt(0);
                }
            }
            iBinder.transact(i, obtain, obtain2, 0);
            obtain2.readException();
            return obtain2.readStrongBinder();
        } finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }

    public static IBinder a(IBinder iBinder, String str, int i, IBinder iBinder2, Parcelable parcelable) {
        return a(iBinder, str, i, iBinder2, parcelable, 2);
    }

    public static IBinder a(IBinder iBinder, String str, int i, IBinder iBinder2) {
        return a(iBinder, str, i, iBinder2, null, 1);
    }
}
