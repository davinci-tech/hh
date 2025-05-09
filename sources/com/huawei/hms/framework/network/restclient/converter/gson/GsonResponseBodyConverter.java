package com.huawei.hms.framework.network.restclient.converter.gson;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.huawei.hms.framework.network.restclient.Converter;
import com.huawei.hms.framework.network.restclient.hwhttp.ResponseBody;
import java.io.IOException;

@Deprecated
/* loaded from: classes9.dex */
final class GsonResponseBodyConverter<T> implements Converter<ResponseBody, T> {
    private final TypeAdapter<T> adapter;
    private final Gson gson;

    GsonResponseBodyConverter(Gson gson, TypeAdapter<T> typeAdapter) {
        this.adapter = typeAdapter;
        this.gson = gson;
    }

    @Override // com.huawei.hms.framework.network.restclient.Converter
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
