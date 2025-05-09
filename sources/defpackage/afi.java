package defpackage;

import android.os.Parcel;
import android.os.Parcelable;
import com.huawei.appgallery.coreservice.internal.support.parcelable.AutoParcelable;
import com.huawei.appgallery.coreservice.internal.support.parcelable.b.c;
import java.lang.reflect.Field;
import java.util.Map;

/* loaded from: classes2.dex */
public class afi implements c<Parcelable[]> {
    @Override // com.huawei.appgallery.coreservice.internal.support.parcelable.b.c
    public void a(AutoParcelable autoParcelable, Field field, Parcel parcel, int i, Map<String, String> map) {
        field.set(autoParcelable, a(parcel, i, field));
    }

    @Override // com.huawei.appgallery.coreservice.internal.support.parcelable.b.c
    public void a(Parcel parcel, Field field, int i, Parcelable[] parcelableArr, int i2, boolean z) {
        if (parcelableArr == null) {
            if (z) {
                afo.hC_(parcel, i, 0);
                return;
            }
            return;
        }
        int hD_ = afo.hD_(parcel, i);
        parcel.writeInt(parcelableArr.length);
        for (Parcelable parcelable : parcelableArr) {
            if (parcelable == null) {
                parcel.writeInt(0);
            } else {
                hl_(parcel, parcelable, i2);
            }
        }
        afo.hB_(parcel, hD_);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r8v6, types: [android.os.Parcelable[]] */
    private <T extends Parcelable> T[] a(Parcel parcel, int i, Field field) {
        int hu_ = afn.hu_(parcel, i);
        int dataPosition = parcel.dataPosition();
        T[] tArr = null;
        if (hu_ == 0) {
            return null;
        }
        try {
            Parcelable.Creator hk_ = hk_(field, null);
            if (hk_ != null) {
                tArr = (Parcelable[]) parcel.createTypedArray(hk_);
            }
        } catch (Exception e2) {
            afp.d.c("ParcelableArrayTypeProcess", "error readParcelableArray:" + e2.getMessage());
        }
        parcel.setDataPosition(dataPosition + hu_);
        return tArr;
    }

    static <T extends Parcelable> void hl_(Parcel parcel, T t, int i) {
        int dataPosition = parcel.dataPosition();
        parcel.writeInt(1);
        int dataPosition2 = parcel.dataPosition();
        t.writeToParcel(parcel, i);
        int dataPosition3 = parcel.dataPosition();
        parcel.setDataPosition(dataPosition);
        parcel.writeInt(dataPosition3 - dataPosition2);
        parcel.setDataPosition(dataPosition3);
    }

    static Parcelable.Creator hk_(Field field, Map<String, String> map) {
        String str;
        Class<?> type = field.getType();
        if (type.isArray()) {
            type = type.getComponentType();
        }
        if (type == null || !Parcelable.class.isAssignableFrom(type)) {
            return null;
        }
        String name = field.getName();
        if (map != null && map.get(name) != null && (str = map.get(name)) != null) {
            try {
                type = Class.forName(str);
            } catch (ClassNotFoundException unused) {
                afp.d.e("ParcelableArrayTypeProcess", "error clazz:" + str);
                return null;
            }
        }
        return hj_(type);
    }

    public static class e extends RuntimeException {
        public e(String str) {
            super(str);
        }
    }

    static Parcelable.Creator hj_(Class cls) {
        try {
            return (Parcelable.Creator) cls.getDeclaredField("CREATOR").get(null);
        } catch (IllegalAccessException unused) {
            throw new e(cls + " IllegalAccessException");
        } catch (IllegalArgumentException unused2) {
            throw new e(cls + " IllegalArgumentException");
        } catch (NoSuchFieldException unused3) {
            throw new e(cls + " is an Parcelable without CREATOR");
        }
    }
}
