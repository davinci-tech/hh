package com.huawei.agconnect.core;

import com.huawei.agconnect.annotation.AutoCreated;
import com.huawei.agconnect.annotation.SharedInstance;
import com.huawei.agconnect.annotation.Singleton;
import java.lang.reflect.Modifier;

/* loaded from: classes2.dex */
public class Service {

    /* renamed from: a, reason: collision with root package name */
    private final Class<?> f1714a;
    private final Class<?> b;
    private final Object c;
    private boolean d;
    private boolean e;
    private boolean f;

    public boolean isSingleton() {
        return this.d;
    }

    public boolean isSharedInstance() {
        return this.e;
    }

    public boolean isAutoCreated() {
        return this.f;
    }

    public Class<?> getType() {
        return this.b;
    }

    public Class<?> getInterface() {
        return this.f1714a;
    }

    public Object getInstance() {
        return this.c;
    }

    /* loaded from: classes8.dex */
    public static class Builder {

        /* renamed from: a, reason: collision with root package name */
        Class<?> f1715a;
        Class<?> b;
        Object c;
        private boolean d;
        private boolean e;
        private boolean f;

        public Builder setObject(Object obj) {
            this.c = obj;
            return this;
        }

        public Builder setInterface(Class<?> cls) {
            this.f1715a = cls;
            return this;
        }

        public Builder setClass(Class<?> cls) {
            this.b = cls;
            return this;
        }

        public Builder isSingleton(boolean z) {
            this.d = z;
            return this;
        }

        public Builder isSharedInstance(boolean z) {
            this.e = z;
            return this;
        }

        public Builder isAutoCreated(boolean z) {
            this.f = z;
            return this;
        }

        public Service build() {
            if (this.f1715a == null) {
                throw new IllegalArgumentException("the interface parameter cannot be NULL");
            }
            Class<?> cls = this.b;
            if (cls == null) {
                if (this.c == null) {
                    throw new IllegalArgumentException("the clazz or object parameter must set one");
                }
                Service service = new Service(this.f1715a, this.c);
                service.d = this.d;
                return service;
            }
            if (cls.isInterface() || !Modifier.isPublic(this.b.getModifiers())) {
                throw new IllegalArgumentException("the clazz parameter cant be interface type or not public");
            }
            Service service2 = new Service((Class) this.f1715a, (Class) this.b);
            service2.d = this.d;
            service2.e = this.e;
            service2.f = this.f;
            return service2;
        }
    }

    public static Builder builder(Class<?> cls, Object obj) {
        return new Builder().setInterface(cls).setObject(obj).isSingleton(true).isSharedInstance(cls.isAnnotationPresent(SharedInstance.class)).isAutoCreated(cls.isAnnotationPresent(AutoCreated.class));
    }

    public static Builder builder(Class<?> cls, Class<?> cls2) {
        return new Builder().setInterface(cls).setClass(cls2).isSingleton(cls2.isAnnotationPresent(Singleton.class)).isSharedInstance(cls2.isAnnotationPresent(SharedInstance.class)).isAutoCreated(cls2.isAnnotationPresent(AutoCreated.class));
    }

    public static Builder builder(Class<?> cls) {
        return new Builder().setInterface(cls).setClass(cls).isSingleton(cls.isAnnotationPresent(Singleton.class)).isSharedInstance(cls.isAnnotationPresent(SharedInstance.class)).isAutoCreated(cls.isAnnotationPresent(AutoCreated.class));
    }

    private Service(Class<?> cls, Object obj) {
        this.f1714a = cls;
        this.b = null;
        this.c = obj;
    }

    private Service(Class<?> cls, Class<?> cls2) {
        this.f1714a = cls;
        this.b = cls2;
        this.c = null;
    }
}
