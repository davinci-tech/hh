package com.huawei.agconnect.apms.collect.type;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

/* loaded from: classes2.dex */
public class BaseCollectable implements Collectable {
    private int type;

    public BaseCollectable(int i) {
        this.type = i;
    }

    @Override // com.huawei.agconnect.apms.collect.type.Collectable
    public JsonElement asJson() {
        int i = this.type;
        if (i == 1) {
            return asJsonObject();
        }
        if (i == 2) {
            return asJsonArray();
        }
        if (i != 3) {
            return null;
        }
        return asJsonPrimitive();
    }

    @Override // com.huawei.agconnect.apms.collect.type.Collectable
    public JsonArray asJsonArray() {
        return null;
    }

    @Override // com.huawei.agconnect.apms.collect.type.Collectable
    public JsonObject asJsonObject() {
        return null;
    }

    @Override // com.huawei.agconnect.apms.collect.type.Collectable
    public JsonPrimitive asJsonPrimitive() {
        return null;
    }

    @Override // com.huawei.agconnect.apms.collect.type.Collectable
    public int getType() {
        return this.type;
    }

    @Override // com.huawei.agconnect.apms.collect.type.Collectable
    public String toJsonString() {
        JsonElement asJson = asJson();
        if (asJson == null) {
            return null;
        }
        return asJson.toString();
    }
}
