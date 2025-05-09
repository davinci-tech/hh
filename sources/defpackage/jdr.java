package defpackage;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.Base64;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.hwlogsmodel.LogUtil;
import java.nio.charset.StandardCharsets;
import java.util.List;

/* loaded from: classes5.dex */
public class jdr {
    private static final byte[] d = new byte[0];

    private static boolean e(String str, String str2) {
        return (str == null && str2 == null) || (str != null && str.equals(str2));
    }

    public static byte[] bFR_(Parcelable parcelable, String str) {
        if (parcelable == null) {
            LogUtil.h("ParcelableUtil", "marshall, parcelable == null");
            return d;
        }
        Parcel obtain = Parcel.obtain();
        obtain.writeString(str);
        obtain.writeString(parcelable.getClass().getName());
        parcelable.writeToParcel(obtain, 0);
        byte[] marshall = obtain.marshall();
        obtain.recycle();
        return marshall != null ? marshall : d;
    }

    public static <T> T bFU_(byte[] bArr, Parcelable.Creator<T> creator, String str) {
        if (bArr == null || bArr.length == 0) {
            LogUtil.h("ParcelableUtil", "unmarshall, data is empty");
            return null;
        }
        Parcel obtain = Parcel.obtain();
        try {
            try {
                obtain.unmarshall(bArr, 0, bArr.length);
                obtain.setDataPosition(0);
                if (e(str, obtain.readString())) {
                    String readString = obtain.readString();
                    T createFromParcel = creator.createFromParcel(obtain);
                    if (createFromParcel != null) {
                        if (createFromParcel.getClass().getName().equals(readString)) {
                            return createFromParcel;
                        }
                    }
                }
            } catch (Exception e) {
                LogUtil.h("ParcelableUtil", "unmarshall fail, e=", ExceptionUtils.d(e));
            }
            obtain.recycle();
            LogUtil.h("ParcelableUtil", "unmarshall fail, version or name or data error");
            return null;
        } finally {
            obtain.recycle();
        }
    }

    public static byte[] bFS_(Parcelable parcelable, String str) {
        return Base64.encode(bFR_(parcelable, str), 0);
    }

    public static <T> T bFV_(byte[] bArr, Parcelable.Creator<T> creator, String str) {
        if (bArr == null || bArr.length == 0) {
            LogUtil.h("ParcelableUtil", "unmarshallFromBase64, data is empty");
            return null;
        }
        try {
            return (T) bFU_(Base64.decode(bArr, 0), creator, str);
        } catch (IllegalArgumentException e) {
            LogUtil.b("ParcelableUtil", "unmarshallFromBase64, ex=", ExceptionUtils.d(e));
            return null;
        }
    }

    public static String bFT_(Parcelable parcelable, String str) {
        return new String(bFS_(parcelable, str), StandardCharsets.UTF_8);
    }

    public static <T> T bFW_(String str, Parcelable.Creator<T> creator, String str2) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("ParcelableUtil", "unmarshallFromBase64String, data is empty");
            return null;
        }
        return (T) bFV_(str.getBytes(StandardCharsets.UTF_8), creator, str2);
    }

    public static byte[] d(List list, String str) {
        if (list == null || list.isEmpty()) {
            LogUtil.h("ParcelableUtil", "marshallList, lists is empty");
            return d;
        }
        Parcel obtain = Parcel.obtain();
        obtain.writeString(str);
        obtain.writeList(list);
        byte[] marshall = obtain.marshall();
        obtain.recycle();
        return marshall != null ? marshall : d;
    }

    public static void b(byte[] bArr, ClassLoader classLoader, String str, List list) {
        if (bArr == null || bArr.length == 0) {
            LogUtil.h("ParcelableUtil", "unmarshallList, data is empty");
            return;
        }
        Parcel obtain = Parcel.obtain();
        try {
            try {
                obtain.unmarshall(bArr, 0, bArr.length);
                obtain.setDataPosition(0);
                if (e(str, obtain.readString())) {
                    obtain.readList(list, classLoader);
                    return;
                }
            } catch (Exception e) {
                LogUtil.h("ParcelableUtil", "unmarshallList fail, e=", ExceptionUtils.d(e));
            }
            obtain.recycle();
            LogUtil.h("ParcelableUtil", "unmarshallList fail, version or data error");
        } finally {
            obtain.recycle();
        }
    }

    public static byte[] c(List list, String str) {
        return Base64.encode(d(list, str), 0);
    }

    public static void c(byte[] bArr, ClassLoader classLoader, String str, List list) {
        if (bArr == null || bArr.length == 0) {
            LogUtil.h("ParcelableUtil", "unmarshallListFormBase64, data is empty");
            return;
        }
        try {
            b(Base64.decode(bArr, 0), classLoader, str, list);
        } catch (IllegalArgumentException e) {
            LogUtil.b("ParcelableUtil", "unmarshallListFormBase64, ex=", ExceptionUtils.d(e));
        }
    }

    public static String b(List list, String str) {
        return new String(c(list, str), StandardCharsets.UTF_8);
    }

    public static void d(String str, ClassLoader classLoader, String str2, List list) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("ParcelableUtil", "unmarshallListFromBase64String, data is empty");
        } else {
            c(str.getBytes(StandardCharsets.UTF_8), classLoader, str2, list);
        }
    }
}
