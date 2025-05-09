package com.huawei.agconnect.credential.obs;

import android.os.Parcel;
import android.os.Parcelable;
import com.huawei.agconnect.common.api.Logger;
import com.huawei.secure.android.common.util.SafeBase64;

/* loaded from: classes8.dex */
class s {

    /* renamed from: a, reason: collision with root package name */
    private static final String f1778a = "ParcelableSerializer";

    private static <T> byte[] b(T t) {
        if (t == null || !Parcelable.class.isAssignableFrom(t.getClass())) {
            return null;
        }
        Parcel obtain = Parcel.obtain();
        ((Parcelable) t).writeToParcel(obtain, 0);
        byte[] marshall = obtain.marshall();
        obtain.recycle();
        return marshall;
    }

    static <T> String a(T t) {
        return SafeBase64.encodeToString(b(t), 0);
    }

    private static <T> T a(byte[] bArr, Class<T> cls) {
        StringBuilder sb;
        String message;
        if (bArr == null || bArr.length == 0 || !Parcelable.class.isAssignableFrom(cls)) {
            return null;
        }
        try {
            Parcelable.Creator creator = (Parcelable.Creator) cls.getField("CREATOR").get(null);
            if (creator != null) {
                Parcel obtain = Parcel.obtain();
                obtain.unmarshall(bArr, 0, bArr.length);
                obtain.setDataPosition(0);
                T t = (T) creator.createFromParcel(obtain);
                obtain.recycle();
                return t;
            }
        } catch (IllegalAccessException e) {
            sb = new StringBuilder("illegal access exception : ");
            message = e.getMessage();
            sb.append(message);
            Logger.e(f1778a, sb.toString());
            return null;
        } catch (NoSuchFieldException e2) {
            sb = new StringBuilder("no such field exception : ");
            message = e2.getMessage();
            sb.append(message);
            Logger.e(f1778a, sb.toString());
            return null;
        }
        return null;
    }

    static <T> T a(String str, Class<T> cls) {
        return (T) a(SafeBase64.decode(str, 0), cls);
    }

    s() {
    }
}
