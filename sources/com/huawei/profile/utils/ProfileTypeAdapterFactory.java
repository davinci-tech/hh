package com.huawei.profile.utils;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;

/* loaded from: classes6.dex */
public final class ProfileTypeAdapterFactory implements TypeAdapterFactory {
    @Override // com.google.gson.TypeAdapterFactory
    public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> typeToken) {
        if (typeToken == null || typeToken.getRawType() != Object.class) {
            return null;
        }
        return (TypeAdapter) ClassUtil.cast(new ProfileTypeAdapter(gson));
    }
}
