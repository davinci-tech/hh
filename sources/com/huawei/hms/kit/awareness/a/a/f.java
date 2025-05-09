package com.huawei.hms.kit.awareness.a.a;

import android.os.Parcel;
import android.os.Parcelable;
import java.lang.reflect.Field;

/* loaded from: classes4.dex */
public class f implements Parcelable {
    public static final Parcelable.Creator<f> CREATOR = new Parcelable.Creator<f>() { // from class: com.huawei.hms.kit.awareness.a.a.f.1
        @Override // android.os.Parcelable.Creator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public f[] newArray(int i) {
            return new f[i];
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public f createFromParcel(Parcel parcel) {
            int dataPosition = parcel.dataPosition();
            String readString = parcel.readString();
            parcel.setDataPosition(dataPosition);
            return (f) a(readString, parcel);
        }

        <T extends Parcelable> T a(String str, Parcel parcel) {
            try {
                Class<?> cls = Class.forName(str);
                if (!Parcelable.class.isAssignableFrom(cls)) {
                    return null;
                }
                Field field = cls.getField("CREATOR");
                if ((field.getModifiers() & 8) == 0 || !Parcelable.Creator.class.isAssignableFrom(field.getType())) {
                    return null;
                }
                Parcelable.Creator creator = (Parcelable.Creator) field.get(null);
                if (creator == null) {
                    return null;
                }
                return (T) creator.createFromParcel(parcel);
            } catch (ClassNotFoundException | IllegalAccessException | NoSuchFieldException unused) {
                return null;
            }
        }
    };

    /* renamed from: a, reason: collision with root package name */
    private static final String f4798a = "InternalResult";
    private int b;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(getClass().getName());
    }

    public int c() {
        return this.b;
    }

    public void a(int i) {
        this.b = i;
    }

    public static <T extends f> T a(f fVar, Class<T> cls) {
        if (fVar == null) {
            return null;
        }
        try {
            return cls.cast(fVar);
        } catch (ClassCastException unused) {
            com.huawei.hms.kit.awareness.b.a.c.d(f4798a, "result cast fail!", new Object[0]);
            return null;
        }
    }

    public f(Parcel parcel) {
        parcel.readString();
    }

    public f() {
        this.b = 10009;
    }
}
