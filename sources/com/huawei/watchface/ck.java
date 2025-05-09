package com.huawei.watchface;

import android.text.TextUtils;
import android.util.Pair;
import android.util.Xml;
import com.google.gson.stream.JsonWriter;
import com.huawei.secure.android.common.util.SafeString;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;
import org.xmlpull.v1.XmlSerializer;

/* loaded from: classes9.dex */
public class ck extends JsonWriter {

    /* renamed from: a, reason: collision with root package name */
    private XmlSerializer f10960a;
    private String b;
    private String c;
    private Stack<Integer> d;
    private Stack<a> e;

    class a {
        private String b;
        private List<Pair<String, String>> c = new ArrayList(20);
        private List<a> d = new ArrayList(10);
        private List<Pair<String, String>> e = new ArrayList(20);
        private String f;

        a(String str) {
            this.b = str;
        }

        void a(String str, String str2) {
            this.c.add(new Pair<>(str, str2));
        }

        void b(String str, String str2) {
            this.e.add(new Pair<>(str, str2));
        }

        void a(a aVar) {
            this.d.add(aVar);
        }

        void a(String str) {
            this.f = str;
        }

        void a(XmlSerializer xmlSerializer, String str) throws IOException {
            xmlSerializer.text(System.lineSeparator());
            xmlSerializer.text(str);
            xmlSerializer.startTag(null, this.b);
            for (Pair<String, String> pair : this.c) {
                ck.this.f10960a.attribute(null, (String) pair.first, (String) pair.second);
            }
            for (Pair<String, String> pair2 : this.e) {
                xmlSerializer.text(System.lineSeparator());
                xmlSerializer.text(str + "    ");
                xmlSerializer.startTag(null, (String) pair2.first);
                xmlSerializer.text((String) pair2.second);
                xmlSerializer.endTag(null, (String) pair2.first);
            }
            Iterator<a> it = this.d.iterator();
            while (it.hasNext()) {
                it.next().a(xmlSerializer, str + "    ");
            }
            xmlSerializer.text(System.lineSeparator());
            xmlSerializer.text(str);
            xmlSerializer.endTag(null, this.b);
        }
    }

    ck(String str, Writer writer) throws IOException {
        super(writer);
        this.d = new Stack<>();
        this.e = new Stack<>();
        this.d.push(1);
        XmlSerializer newSerializer = Xml.newSerializer();
        this.f10960a = newSerializer;
        newSerializer.setOutput(writer);
        this.b = str;
    }

    @Override // com.google.gson.stream.JsonWriter
    public JsonWriter beginObject() throws IOException {
        int intValue = this.d.peek().intValue();
        if (intValue == 1) {
            this.f10960a.startDocument("utf-8", true);
            this.d.pop();
            this.d.push(2);
            this.e.push(new a(this.b));
        }
        if (intValue == 2) {
            if (!this.e.isEmpty()) {
                a peek = this.e.peek();
                a aVar = new a(this.c);
                peek.a(aVar);
                this.e.push(aVar);
            }
            this.d.push(2);
        }
        if (intValue == 3) {
            if (!this.e.isEmpty()) {
                a peek2 = this.e.peek();
                a aVar2 = new a(peek2.f);
                peek2.a(aVar2);
                this.e.push(aVar2);
            }
            this.d.push(2);
        }
        return this;
    }

    @Override // com.google.gson.stream.JsonWriter
    public JsonWriter endObject() throws IOException {
        a aVar = null;
        if (this.d.peek().intValue() == 2) {
            if (!this.e.isEmpty()) {
                a peek = this.e.peek();
                this.e.pop();
                aVar = peek;
            }
            this.d.pop();
        }
        if (this.d.isEmpty() && this.e.isEmpty()) {
            if (aVar != null) {
                aVar.a(this.f10960a, "");
            }
            this.f10960a.endDocument();
            this.f10960a.text(System.lineSeparator());
            this.f10960a.flush();
        }
        return this;
    }

    @Override // com.google.gson.stream.JsonWriter
    public JsonWriter beginArray() {
        this.d.push(3);
        if (!this.e.isEmpty()) {
            this.e.peek().a(this.c);
        }
        return this;
    }

    @Override // com.google.gson.stream.JsonWriter
    public JsonWriter endArray() {
        this.d.pop();
        return this;
    }

    @Override // com.google.gson.stream.JsonWriter
    public JsonWriter name(String str) {
        if (str == null) {
            throw new NullPointerException("mName == null");
        }
        this.c = str;
        return this;
    }

    @Override // com.google.gson.stream.JsonWriter
    public JsonWriter value(String str) {
        if (str == null) {
            return nullValue();
        }
        a(str);
        return this;
    }

    @Override // com.google.gson.stream.JsonWriter
    public JsonWriter value(boolean z) {
        a(z ? "true" : "false");
        return this;
    }

    @Override // com.google.gson.stream.JsonWriter
    public JsonWriter value(Boolean bool) {
        if (bool == null) {
            return nullValue();
        }
        a(bool.booleanValue() ? "true" : "false");
        return this;
    }

    @Override // com.google.gson.stream.JsonWriter
    public JsonWriter value(double d) {
        if (Double.isNaN(d) || Double.isInfinite(d)) {
            throw new IllegalArgumentException("Numeric values must be finite, but was " + d);
        }
        a(Double.toString(d));
        return this;
    }

    @Override // com.google.gson.stream.JsonWriter
    public JsonWriter value(long j) {
        a(Long.toString(j));
        return this;
    }

    @Override // com.google.gson.stream.JsonWriter
    public JsonWriter value(Number number) {
        if (number == null) {
            return nullValue();
        }
        String valueOf = String.valueOf(number);
        if (valueOf.equals("-Infinity") || valueOf.equals("Infinity") || valueOf.equals("NaN")) {
            throw new IllegalArgumentException("Numeric values must be finite");
        }
        a(valueOf);
        return this;
    }

    @Override // com.google.gson.stream.JsonWriter
    public JsonWriter jsonValue(String str) throws IOException {
        this.f10960a.text(str);
        this.f10960a.flush();
        return this;
    }

    @Override // com.google.gson.stream.JsonWriter
    public JsonWriter nullValue() {
        a((String) null);
        return this;
    }

    private void a(String str) {
        if (this.d.peek().intValue() != 2 || TextUtils.isEmpty(this.c) || this.e.isEmpty()) {
            return;
        }
        a peek = this.e.peek();
        String str2 = this.c;
        if (TextUtils.isEmpty(str2) || TextUtils.isEmpty(str)) {
            return;
        }
        if (str2.startsWith("@")) {
            peek.a(SafeString.substring(str2, 1, str2.length()), str);
        } else {
            peek.b(str2, str);
        }
    }
}
