package com.huawei.profile.utils;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.internal.LinkedTreeMap;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import com.huawei.profile.utils.logger.DsLog;
import java.io.IOException;
import java.util.ArrayList;

/* loaded from: classes6.dex */
public final class ProfileTypeAdapter extends TypeAdapter<Object> {
    private static final String DOT = ".";
    private static final String TAG = "ProfileTypeAdapter";
    private final Gson gson;

    ProfileTypeAdapter(Gson gson) {
        this.gson = gson;
    }

    @Override // com.google.gson.TypeAdapter
    /* renamed from: read */
    public Object read2(JsonReader jsonReader) throws IOException {
        JsonToken peek = jsonReader.peek();
        if (peek == JsonToken.BEGIN_ARRAY) {
            ArrayList arrayList = new ArrayList();
            jsonReader.beginArray();
            while (jsonReader.hasNext()) {
                arrayList.add(read2(jsonReader));
            }
            jsonReader.endArray();
            return arrayList;
        }
        if (peek == JsonToken.BEGIN_OBJECT) {
            LinkedTreeMap linkedTreeMap = new LinkedTreeMap();
            jsonReader.beginObject();
            while (jsonReader.hasNext()) {
                linkedTreeMap.put(jsonReader.nextName(), read2(jsonReader));
            }
            jsonReader.endObject();
            return linkedTreeMap;
        }
        if (peek == JsonToken.STRING) {
            return jsonReader.nextString();
        }
        if (peek == JsonToken.NUMBER) {
            String nextString = jsonReader.nextString();
            if (nextString.contains(".")) {
                Double parseDouble = NumberUtil.parseDouble(nextString);
                if (parseDouble != null) {
                    return parseDouble;
                }
                DsLog.et(TAG, "cant get double from string,error: NumberFormatException", new Object[0]);
                return null;
            }
            Long parseLong = NumberUtil.parseLong(nextString);
            if (parseLong == null) {
                DsLog.et(TAG, "cant get long from string,error: NumberFormatException", new Object[0]);
                return null;
            }
            long longValue = parseLong.longValue();
            if (longValue >= -2147483648L && longValue <= 2147483647L) {
                return Integer.valueOf((int) longValue);
            }
            return Long.valueOf(longValue);
        }
        if (peek == JsonToken.BOOLEAN) {
            return Boolean.valueOf(jsonReader.nextBoolean());
        }
        return null;
    }

    @Override // com.google.gson.TypeAdapter
    public void write(JsonWriter jsonWriter, Object obj) throws IOException {
        if (obj == null) {
            jsonWriter.nullValue();
            return;
        }
        Class cls = (Class) ClassUtil.cast(obj.getClass());
        if (cls != null) {
            TypeAdapter adapter = this.gson.getAdapter(cls);
            if (adapter instanceof ProfileTypeAdapter) {
                jsonWriter.beginObject();
                jsonWriter.endObject();
                return;
            } else {
                adapter.write(jsonWriter, obj);
                return;
            }
        }
        DsLog.et(TAG, "the clazz is null", new Object[0]);
    }
}
