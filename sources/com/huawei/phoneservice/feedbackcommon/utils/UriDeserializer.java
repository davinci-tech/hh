package com.huawei.phoneservice.feedbackcommon.utils;

import android.net.Uri;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import java.lang.reflect.Type;

/* loaded from: classes6.dex */
public class UriDeserializer implements JsonDeserializer<Uri> {
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.google.gson.JsonDeserializer
    public Uri deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        return Uri.parse(jsonElement.getAsString());
    }
}
