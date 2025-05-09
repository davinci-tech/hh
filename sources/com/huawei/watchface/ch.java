package com.huawei.watchface;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import com.google.gson.internal.Primitives;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.MalformedJsonException;
import com.huawei.watchface.cj;
import com.huawei.watchface.utils.HwLog;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.lang.reflect.Type;

/* loaded from: classes9.dex */
public class ch {

    /* renamed from: a, reason: collision with root package name */
    private final Gson f10948a;
    private final cj.d b;

    ch(Gson gson, cj.d dVar) {
        this.f10948a = gson;
        this.b = dVar;
    }

    private static void a(Object obj, JsonReader jsonReader) {
        if (obj != null) {
            try {
                if (jsonReader.peek() == JsonToken.END_DOCUMENT) {
                } else {
                    throw new JsonIOException("JSON document was not fully consumed.");
                }
            } catch (MalformedJsonException e) {
                HwLog.e("WatchFaceConfigParser", "assertFullConsumption MalformedJsonException: " + HwLog.printException((Exception) e));
            } catch (IOException e2) {
                HwLog.e("WatchFaceConfigParser", "assertFullConsumption JsonIOException: " + HwLog.printException((Exception) e2));
            }
        }
    }

    public <T> T a(Class<T> cls, Reader reader) throws JsonSyntaxException, JsonIOException {
        cj cjVar = new cj(reader, this.b);
        Object a2 = a((Type) cls, cjVar);
        a(a2, cjVar);
        return (T) Primitives.wrap(cls).cast(a2);
    }

    private <T> T a(Type type, cj cjVar) throws JsonIOException, JsonSyntaxException {
        return (T) this.f10948a.fromJson(cjVar, type);
    }

    public void a(Writer writer, Object obj) throws JsonIOException, JsonSyntaxException {
        try {
            this.f10948a.toJson(obj, obj.getClass(), new ck(obj.getClass().getSimpleName(), writer));
        } catch (JsonIOException e) {
            HwLog.e("WatchFaceConfigParser", "toXml JsonIOException: " + HwLog.printException((Exception) e));
        } catch (IOException e2) {
            HwLog.e("WatchFaceConfigParser", "toXml IOException: " + HwLog.printException((Exception) e2));
        }
    }

    public String toString() {
        return this.f10948a.toString();
    }
}
