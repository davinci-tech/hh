package com.huawei.hmf.orb.aidl.communicate;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

/* loaded from: classes9.dex */
public class ObjectWrapper<T> extends Binder implements IInterface {
    private static final String DESCRIPTOR = "com.huawei.hmf.orb.aidl.communicate.ObjectWrapper";
    private final T mObject;

    @Override // android.os.IInterface
    public IBinder asBinder() {
        return this;
    }

    private ObjectWrapper(T t) {
        attachInterface(this, DESCRIPTOR);
        this.mObject = t;
    }

    public static <T> ObjectWrapper wrap(T t) {
        return new ObjectWrapper(t);
    }

    public static <T> T unwrap(Object obj) {
        if (!(obj instanceof Binder)) {
            return null;
        }
        Binder binder = (Binder) obj;
        if (DESCRIPTOR.equals(binder.getInterfaceDescriptor())) {
            return (T) unwrap(binder);
        }
        return null;
    }

    private static <T> T unwrap(Binder binder) {
        Field[] declaredFields = binder.getClass().getDeclaredFields();
        int i = 0;
        for (Field field : declaredFields) {
            if (!field.isSynthetic() && !Modifier.isStatic(field.getModifiers())) {
                i++;
            }
        }
        if (i == 1) {
            if (!declaredFields[0].isAccessible()) {
                declaredFields[0].setAccessible(true);
                try {
                    return (T) declaredFields[0].get(binder);
                } catch (IllegalAccessException unused) {
                    throw new IllegalArgumentException("Could not access the field in remoteBinder.");
                } catch (NullPointerException unused2) {
                    throw new IllegalArgumentException("Binder object is null.");
                }
            }
            throw new IllegalArgumentException("IObjectWrapper declared field not private!");
        }
        throw new IllegalArgumentException("Unexpected number of IObjectWrapper declared fields: " + declaredFields.length);
    }
}
