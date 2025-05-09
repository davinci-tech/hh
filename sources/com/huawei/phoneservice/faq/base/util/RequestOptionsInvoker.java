package com.huawei.phoneservice.faq.base.util;

import android.graphics.drawable.Drawable;
import com.bumptech.glide.Priority;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.request.BaseRequestOptions;
import com.bumptech.glide.request.RequestOptions;
import com.huawei.openalliance.ad.constant.VastAttribute;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/* loaded from: classes9.dex */
public class RequestOptionsInvoker {
    static boolean isHigher48() {
        try {
            Class.forName("com.bumptech.glide.request.BaseRequestOptions");
            return true;
        } catch (ClassNotFoundException unused) {
            return false;
        }
    }

    public static void invoke(RequestBuilder requestBuilder, int i, int i2, Priority priority, int i3) throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        if (!isHigher48()) {
            requestBuilder.apply((BaseRequestOptions<?>) new RequestOptions().override(i, i2).priority(priority).error(i3).fitCenter());
            return;
        }
        Class<?> cls = Class.forName("com.bumptech.glide.request.RequestOptions");
        Object newInstance = cls.newInstance();
        Class cls2 = Integer.TYPE;
        Method method = cls.getMethod("override", cls2, cls2);
        Method method2 = cls.getMethod("priority", Priority.class);
        Method method3 = cls.getMethod(VastAttribute.ERROR, cls2);
        method.invoke(newInstance, Integer.valueOf(i), Integer.valueOf(i2));
        method2.invoke(newInstance, priority);
        method3.invoke(newInstance, Integer.valueOf(i3));
        cls.getMethod("fitCenter", new Class[0]).invoke(newInstance, new Object[0]);
        requestBuilder.getClass().getMethod("apply", Class.forName("com.bumptech.glide.request.BaseRequestOptions")).invoke(requestBuilder, newInstance);
    }

    public static void invoke(RequestBuilder requestBuilder, int i, int i2, Priority priority) throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        if (!isHigher48()) {
            requestBuilder.apply((BaseRequestOptions<?>) new RequestOptions().override(i, i2).priority(priority));
            return;
        }
        Class<?> cls = Class.forName("com.bumptech.glide.request.RequestOptions");
        Object newInstance = cls.newInstance();
        Method method = requestBuilder.getClass().getMethod("apply", Class.forName("com.bumptech.glide.request.BaseRequestOptions"));
        Class cls2 = Integer.TYPE;
        Method method2 = cls.getMethod("override", cls2, cls2);
        Method method3 = cls.getMethod("priority", Priority.class);
        method2.invoke(newInstance, Integer.valueOf(i), Integer.valueOf(i2));
        method3.invoke(newInstance, priority);
        method.invoke(requestBuilder, newInstance);
    }

    public static void invoke(RequestBuilder requestBuilder, int i, int i2, Drawable drawable, int i3) throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        if (!isHigher48()) {
            requestBuilder.apply((BaseRequestOptions<?>) new RequestOptions().override(i, i2).error(i3).placeholder(drawable).centerCrop());
            return;
        }
        Class<?> cls = Class.forName("com.bumptech.glide.request.RequestOptions");
        Object newInstance = cls.newInstance();
        Class cls2 = Integer.TYPE;
        Method method = cls.getMethod("override", cls2, cls2);
        Method method2 = cls.getMethod("placeholder", Drawable.class);
        Method method3 = cls.getMethod(VastAttribute.ERROR, cls2);
        method.invoke(newInstance, Integer.valueOf(i), Integer.valueOf(i2));
        method2.invoke(newInstance, drawable);
        method3.invoke(newInstance, Integer.valueOf(i3));
        cls.getMethod("centerCrop", new Class[0]).invoke(newInstance, new Object[0]);
        requestBuilder.getClass().getMethod("apply", Class.forName("com.bumptech.glide.request.BaseRequestOptions")).invoke(requestBuilder, newInstance);
    }

    public static void invoke(RequestBuilder requestBuilder, int i, int i2, Drawable drawable) throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        if (!isHigher48()) {
            requestBuilder.apply((BaseRequestOptions<?>) new RequestOptions().override(i, i2).placeholder(drawable).centerCrop());
            return;
        }
        Class<?> cls = Class.forName("com.bumptech.glide.request.RequestOptions");
        Object newInstance = cls.newInstance();
        Class cls2 = Integer.TYPE;
        Method method = cls.getMethod("override", cls2, cls2);
        Method method2 = cls.getMethod("placeholder", Drawable.class);
        Method method3 = cls.getMethod("centerCrop", new Class[0]);
        method.invoke(newInstance, Integer.valueOf(i), Integer.valueOf(i2));
        method2.invoke(newInstance, drawable);
        method3.invoke(newInstance, new Object[0]);
        requestBuilder.getClass().getMethod("apply", Class.forName("com.bumptech.glide.request.BaseRequestOptions")).invoke(requestBuilder, newInstance);
    }

    public static void invoke(RequestBuilder requestBuilder, int i, int i2, int i3) throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        if (!isHigher48()) {
            requestBuilder.apply((BaseRequestOptions<?>) new RequestOptions().override(i, i2).placeholder(i3).centerCrop());
            return;
        }
        Class<?> cls = Class.forName("com.bumptech.glide.request.RequestOptions");
        Object newInstance = cls.newInstance();
        Class cls2 = Integer.TYPE;
        Method method = cls.getMethod("override", cls2, cls2);
        Method method2 = cls.getMethod("placeholder", cls2);
        Method method3 = cls.getMethod("centerCrop", new Class[0]);
        method.invoke(newInstance, Integer.valueOf(i), Integer.valueOf(i2));
        method2.invoke(newInstance, Integer.valueOf(i3));
        method3.invoke(newInstance, new Object[0]);
        requestBuilder.getClass().getMethod("apply", Class.forName("com.bumptech.glide.request.BaseRequestOptions")).invoke(requestBuilder, newInstance);
    }

    public static void invoke(RequestBuilder requestBuilder, int i, int i2) throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        if (!isHigher48()) {
            requestBuilder.apply((BaseRequestOptions<?>) new RequestOptions().error(i2).placeholder(i).centerCrop());
            return;
        }
        Class<?> cls = Class.forName("com.bumptech.glide.request.RequestOptions");
        Object newInstance = cls.newInstance();
        Class cls2 = Integer.TYPE;
        Method method = cls.getMethod("placeholder", cls2);
        Method method2 = cls.getMethod(VastAttribute.ERROR, cls2);
        Method method3 = cls.getMethod("centerCrop", new Class[0]);
        method.invoke(newInstance, Integer.valueOf(i));
        method2.invoke(newInstance, Integer.valueOf(i2));
        method3.invoke(newInstance, new Object[0]);
        requestBuilder.getClass().getMethod("apply", Class.forName("com.bumptech.glide.request.BaseRequestOptions")).invoke(requestBuilder, newInstance);
    }
}
