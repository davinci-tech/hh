package com.huawei.hms.network.restclient.converter.gson;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.huawei.hms.network.httpclient.ResponseBody;
import com.huawei.hms.network.restclient.Converter;
import java.io.IOException;

/* loaded from: classes9.dex */
final class GsonResponseBodyConverter<T> extends Converter<ResponseBody, T> {
    private final TypeAdapter<T> adapter;
    private final Gson gson;

    GsonResponseBodyConverter(Gson gson, TypeAdapter<T> typeAdapter) {
        this.adapter = typeAdapter;
        this.gson = gson;
    }

    @Override // com.huawei.hms.network.restclient.Converter
    public T convert(ResponseBody responseBody) throws IOException {
        JsonReader newJsonReader = this.gson.newJsonReader(responseBody.charStream());
        try {
            T read2 = this.adapter.read2(newJsonReader);
            if (JsonToken.END_DOCUMENT == newJsonReader.peek()) {
                return read2;
            }
            throw new JsonIOException("JSON document was not fully consumed.");
        } finally {
            responseBody.close();
        }
    }
}
