package defpackage;

import android.os.Parcel;
import android.text.TextUtils;
import android.util.SparseArray;
import com.huawei.appgallery.coreservice.internal.support.parcelable.AutoParcelable;
import com.huawei.appgallery.coreservice.internal.support.parcelable.EnableAutoParcel;
import com.huawei.appgallery.coreservice.internal.support.parcelable.b.a;
import com.huawei.appgallery.coreservice.internal.support.parcelable.b.c;
import com.huawei.openalliance.ad.constant.Constants;
import defpackage.afn;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.TypeVariable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes2.dex */
public final class afq {
    private static final Map<a, c> b;

    private static void hA_(AutoParcelable autoParcelable, Parcel parcel, Field field, int i, Map<String, String> map) {
        boolean isAccessible = field.isAccessible();
        field.setAccessible(true);
        try {
            try {
                c cVar = b.get(a.a(field));
                if (cVar == null) {
                    afp.d.e("SafeParcel", "can not find process to read:" + field.getName());
                } else {
                    cVar.a(autoParcelable, field, parcel, i, map);
                }
            } catch (IllegalAccessException unused) {
                afp.d.c("SafeParcel", "can not set field value");
            }
        } finally {
            field.setAccessible(isAccessible);
        }
    }

    private static void hz_(AutoParcelable autoParcelable, Parcel parcel, Field field, int i) {
        int value = ((EnableAutoParcel) field.getAnnotation(EnableAutoParcel.class)).value();
        boolean mayNull = ((EnableAutoParcel) field.getAnnotation(EnableAutoParcel.class)).mayNull();
        boolean isAccessible = field.isAccessible();
        field.setAccessible(true);
        c cVar = b.get(a.a(field));
        if (cVar == null) {
            afp.d.e("SafeParcel", "can not find process to write:" + field.getName());
        } else {
            cVar.a(parcel, field, value, field.get(autoParcelable), i, mayNull);
        }
        field.setAccessible(isAccessible);
    }

    private static void hy_(AutoParcelable autoParcelable, Parcel parcel, Class cls) {
        TypeVariable<Class<?>>[] typeParameters = autoParcelable.getClass().getTypeParameters();
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        for (TypeVariable<Class<?>> typeVariable : typeParameters) {
            if (!TextUtils.isEmpty(typeVariable.getName())) {
                arrayList2.add(typeVariable.getName());
            }
        }
        if (!arrayList2.isEmpty()) {
            Field[] declaredFields = cls.getDeclaredFields();
            int length = declaredFields.length;
            for (int i = 0; i < length; i++) {
                Field field = declaredFields[i];
                if (field.isAnnotationPresent(EnableAutoParcel.class) && arrayList2.contains(field.getGenericType().toString())) {
                    boolean isAccessible = field.isAccessible();
                    field.setAccessible(true);
                    String name = field.getName();
                    try {
                        try {
                            Object obj = field.get(autoParcelable);
                            if (obj != null) {
                                arrayList.add(name + "|" + obj.getClass().getCanonicalName());
                            }
                        } catch (IllegalAccessException unused) {
                            afp.d.e("SafeParcel", "can not get the value of the field:" + name);
                        }
                    } finally {
                        field.setAccessible(isAccessible);
                    }
                }
            }
        }
        new afl().a(parcel, null, 0, (String[]) arrayList.toArray(new String[typeParameters.length]), 0, false);
    }

    public static void hx_(AutoParcelable autoParcelable, Parcel parcel, int i) {
        Class<?> cls = autoParcelable.getClass();
        int hD_ = afo.hD_(parcel, 20293);
        hy_(autoParcelable, parcel, cls);
        while (cls != null) {
            for (Field field : cls.getDeclaredFields()) {
                if (field.isAnnotationPresent(EnableAutoParcel.class)) {
                    try {
                        hz_(autoParcelable, parcel, field, i);
                    } catch (Exception e) {
                        afp.d.c("SafeParcel", "Error writing field: " + e);
                    }
                }
            }
            cls = cls.getSuperclass();
        }
        afo.hB_(parcel, hD_);
    }

    private static void a(AutoParcelable autoParcelable, Parcel parcel) {
        SparseArray sparseArray = new SparseArray();
        for (Class<?> cls = autoParcelable.getClass(); cls != null; cls = cls.getSuperclass()) {
            for (Field field : cls.getDeclaredFields()) {
                if (field.isAnnotationPresent(EnableAutoParcel.class)) {
                    int value = ((EnableAutoParcel) field.getAnnotation(EnableAutoParcel.class)).value();
                    if (sparseArray.get(value) != null) {
                        throw new d("Field number " + value + " is used twice in " + cls.getName() + " for fields " + field.getName() + " and " + ((Field) sparseArray.get(value)).getName());
                    }
                    sparseArray.put(value, field);
                }
            }
        }
        Class<?> cls2 = autoParcelable.getClass();
        int a2 = afn.a(parcel);
        Map<String, String> hw_ = hw_(parcel);
        while (parcel.dataPosition() < a2) {
            int readInt = parcel.readInt();
            int e = afn.e(readInt);
            Field field2 = (Field) sparseArray.get(e);
            if (field2 == null) {
                afp.d.a("SafeParcel", "Unknown field num " + e + " in " + cls2.getName() + ", skipping.");
            } else {
                try {
                    hA_(autoParcelable, parcel, field2, readInt, hw_);
                } catch (afn.c e2) {
                    afn.b(parcel, readInt);
                    afp.d.c("SafeParcel", "Error reading field: " + e + " in " + cls2.getName() + ", skipping." + e2.getMessage());
                }
            }
            afn.hv_(parcel, readInt);
        }
        if (parcel.dataPosition() <= a2) {
            return;
        }
        throw new d("Overread allowed size end=" + a2 + Constants.LINK + parcel.dataPosition());
    }

    private static Map<String, String> hw_(Parcel parcel) {
        HashMap hashMap = new HashMap();
        String[] ho_ = afl.ho_(parcel, parcel.readInt());
        if (ho_ != null) {
            for (String str : ho_) {
                if (!TextUtils.isEmpty(str)) {
                    String[] split = str.split("\\|");
                    if (split.length == 2) {
                        hashMap.put(split[0], split[1]);
                    }
                }
            }
        }
        return hashMap;
    }

    static final class d extends RuntimeException {
        public d(String str, Throwable th) {
            super(str, th);
        }

        public d(String str) {
            super(str);
        }
    }

    public static ClassLoader c(Class cls) {
        return cls == null ? ClassLoader.getSystemClassLoader() : cls.getClassLoader();
    }

    public static <T extends AutoParcelable> T a(Class<T> cls, Parcel parcel) {
        try {
            Constructor<T> declaredConstructor = cls.getDeclaredConstructor(new Class[0]);
            boolean isAccessible = declaredConstructor.isAccessible();
            declaredConstructor.setAccessible(true);
            try {
                try {
                    T newInstance = declaredConstructor.newInstance(new Object[0]);
                    try {
                        a(newInstance, parcel);
                    } catch (Exception e) {
                        afp.d.c("SafeParcel", "can not readObject", e);
                    }
                    return newInstance;
                } finally {
                    declaredConstructor.setAccessible(isAccessible);
                }
            } catch (IllegalAccessException e2) {
                throw new d("newInstance failed", e2);
            } catch (InstantiationException e3) {
                throw new d("newInstance failed", e3);
            } catch (InvocationTargetException e4) {
                throw new d("newInstance failed", e4);
            }
        } catch (NoSuchMethodException e5) {
            throw new d("createObject() requires a default constructor", e5);
        } catch (SecurityException e6) {
            throw new d("createObject() requires a public constructor", e6);
        }
    }

    static {
        HashMap hashMap = new HashMap();
        b = hashMap;
        hashMap.put(a.BINDER, new aev());
        hashMap.put(a.BOOLEAN, new aet());
        hashMap.put(a.BUNDLE, new aey());
        hashMap.put(a.BYTE_ARRAY, new aez());
        hashMap.put(a.DOUBLE, new afb());
        hashMap.put(a.FLOAT, new afc());
        hashMap.put(a.HASH_MAP, new afa());
        hashMap.put(a.INT_ARRAY, new afh());
        hashMap.put(a.INTEGER, new afg());
        hashMap.put(a.INTERFACE, new afd());
        hashMap.put(a.LIST, new afe());
        hashMap.put(a.LONG, new aff());
        hashMap.put(a.PARCELABLE_ARRAY, new afi());
        hashMap.put(a.PARCELABLE, new afm());
        hashMap.put(a.STRING_ARRAY, new afl());
        hashMap.put(a.STRING_LIST, new afk());
        hashMap.put(a.STRING, new afj());
    }
}
