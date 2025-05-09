package com.huawei.hmf.orb.tbis;

import java.lang.reflect.Type;

/* loaded from: classes9.dex */
public interface TextCodec {
    int getInt(String str);

    <T> T getObject(String str, Class<T> cls);

    String getString(String str);

    void put(String str, Object obj);

    <T> T toObject(String str, Class<T> cls);

    <T> T toObject(String str, Type type);

    String toString();

    String toString(Object obj);
}
