package com.huawei.health.h5pro.utils;

import android.content.ContentValues;
import android.util.ArrayMap;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.util.Map;

/* loaded from: classes3.dex */
public final class ContentValuesTypeAdapterFactory implements TypeAdapterFactory {

    public static class ContentValuesTypeAdapter extends TypeAdapter<ContentValues> {
        public final TypeAdapter<Map> c;

        @Override // com.google.gson.TypeAdapter
        public void write(JsonWriter jsonWriter, ContentValues contentValues) {
            if (contentValues == null) {
                jsonWriter.nullValue();
                return;
            }
            ArrayMap arrayMap = new ArrayMap(contentValues.size());
            for (Map.Entry<String, Object> entry : contentValues.valueSet()) {
                arrayMap.put(entry.getKey(), entry.getValue());
            }
            jsonWriter.beginObject();
            jsonWriter.name("mMap");
            this.c.write(jsonWriter, arrayMap);
            jsonWriter.endObject();
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.google.gson.TypeAdapter
        /* renamed from: read */
        public ContentValues read2(JsonReader jsonReader) {
            jsonReader.beginObject();
            jsonReader.nextName();
            Map read2 = this.c.read2(jsonReader);
            jsonReader.endObject();
            if (read2 == null) {
                return null;
            }
            ContentValues contentValues = new ContentValues(read2.size());
            for (Map.Entry entry : read2.entrySet()) {
                XR_(contentValues, (String) entry.getKey(), entry.getValue());
            }
            return contentValues;
        }

        private void XR_(ContentValues contentValues, String str, Object obj) {
            if (obj == null) {
                contentValues.putNull(str);
                return;
            }
            if (obj instanceof String) {
                contentValues.put(str, (String) obj);
                return;
            }
            if (obj instanceof Byte) {
                contentValues.put(str, (Byte) obj);
                return;
            }
            if (obj instanceof Short) {
                contentValues.put(str, (Short) obj);
                return;
            }
            if (obj instanceof Integer) {
                contentValues.put(str, (Integer) obj);
                return;
            }
            if (obj instanceof Long) {
                contentValues.put(str, (Long) obj);
                return;
            }
            if (obj instanceof Float) {
                contentValues.put(str, (Float) obj);
                return;
            }
            if (obj instanceof Double) {
                contentValues.put(str, (Double) obj);
                return;
            }
            if (obj instanceof Boolean) {
                contentValues.put(str, (Boolean) obj);
            } else if (obj instanceof byte[]) {
                contentValues.put(str, (byte[]) obj);
            } else {
                throw new IllegalArgumentException("Unsupported type " + obj.getClass());
            }
        }

        public ContentValuesTypeAdapter(Gson gson) {
            this.c = gson.getAdapter(Map.class);
        }
    }

    @Override // com.google.gson.TypeAdapterFactory
    public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> typeToken) {
        if (typeToken.getRawType() == ContentValues.class) {
            return new ContentValuesTypeAdapter(gson);
        }
        return null;
    }
}
