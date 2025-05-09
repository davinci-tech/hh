package com.huawei.hihealth.dictionary.model;

import com.google.gson.Gson;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import java.lang.reflect.Type;

/* loaded from: classes.dex */
public class HiHealthDictionaryDeserializer implements JsonDeserializer<HiHealthDictionary> {
    @Override // com.google.gson.JsonDeserializer
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public HiHealthDictionary deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        if (jsonElement == null || !jsonElement.isJsonObject()) {
            return null;
        }
        HiHealthDictionary hiHealthDictionary = (HiHealthDictionary) new Gson().fromJson((JsonElement) jsonElement.getAsJsonObject(), HiHealthDictionary.class);
        hiHealthDictionary.d();
        return hiHealthDictionary;
    }
}
