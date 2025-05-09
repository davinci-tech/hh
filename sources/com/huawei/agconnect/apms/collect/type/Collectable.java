package com.huawei.agconnect.apms.collect.type;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

/* loaded from: classes2.dex */
public interface Collectable {

    /* loaded from: classes8.dex */
    public interface Type {
        public static final int ARRAY = 2;
        public static final int OBJECT = 1;
        public static final int VALUE = 3;
    }

    JsonElement asJson();

    JsonArray asJsonArray();

    JsonObject asJsonObject();

    JsonPrimitive asJsonPrimitive();

    int getType();

    String toJsonString();
}
