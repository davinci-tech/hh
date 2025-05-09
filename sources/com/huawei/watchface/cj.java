package com.huawei.watchface;

import android.text.TextUtils;
import android.util.Xml;
import com.google.gson.JsonSyntaxException;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.huawei.hihealth.HiDataFilter;
import com.huawei.hihealth.model.SampleEvent;
import com.huawei.watchface.cj;
import com.huawei.watchface.utils.CommonUtils;
import com.huawei.watchface.utils.HwLog;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

/* loaded from: classes9.dex */
public class cj extends JsonReader {

    /* renamed from: a, reason: collision with root package name */
    private final d f10950a;
    private final XmlPullParser b;
    private final e<g> c;
    private final e<h> d;
    private final Stack<f> e;
    private final Stack<b> f;
    private final i g;
    private final a h;
    private g i;
    private g j;
    private h k;
    private h l;
    private JsonToken m;
    private boolean n;
    private boolean o;
    private boolean p;
    private JsonToken q;
    private int r;
    private boolean s;

    interface c<T> {
        T create();
    }

    public static class d {

        /* renamed from: a, reason: collision with root package name */
        boolean f10954a;
        boolean b;
        boolean c;
        boolean d;
        boolean e;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ g i() {
        return new g(null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ h h() {
        return new h(null);
    }

    cj(Reader reader, d dVar) {
        super(reader);
        this.c = new e<>(new c() { // from class: com.huawei.watchface.cj$$ExternalSyntheticLambda0
            @Override // com.huawei.watchface.cj.c
            public final Object create() {
                cj.g i2;
                i2 = cj.i();
                return i2;
            }
        });
        this.d = new e<>(new c() { // from class: com.huawei.watchface.cj$$ExternalSyntheticLambda1
            @Override // com.huawei.watchface.cj.c
            public final Object create() {
                cj.h h2;
                h2 = cj.h();
                return h2;
            }
        });
        this.e = new Stack<>();
        this.f = new Stack<>();
        i iVar = new i(null);
        this.g = iVar;
        this.h = new a(10);
        this.o = true;
        this.p = false;
        this.r = 0;
        XmlPullParser newPullParser = Xml.newPullParser();
        this.b = newPullParser;
        this.f10950a = dVar;
        iVar.f10959a = -1;
        try {
            newPullParser.setInput(reader);
            newPullParser.setFeature("http://xmlpull.org/v1/doc/features.html#process-namespaces", dVar.d);
        } catch (XmlPullParserException e2) {
            throw new IllegalStateException(e2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String b(String str, String str2, XmlPullParser xmlPullParser) throws XmlPullParserException {
        if (TextUtils.isEmpty(str2)) {
            return str;
        }
        if (xmlPullParser != null) {
            int namespaceCount = xmlPullParser.getNamespaceCount(xmlPullParser.getDepth());
            int i2 = 0;
            while (true) {
                if (i2 >= namespaceCount) {
                    break;
                }
                if (str2.equals(xmlPullParser.getNamespaceUri(i2))) {
                    str2 = xmlPullParser.getNamespacePrefix(i2);
                    break;
                }
                i2++;
            }
        }
        return HiDataFilter.DataFilterExpression.LESS_THAN + str2 + HiDataFilter.DataFilterExpression.BIGGER_THAN + str;
    }

    private JsonToken a() {
        g gVar = this.j;
        if (gVar != null) {
            return gVar.f10957a;
        }
        return null;
    }

    private JsonToken b() {
        g gVar = this.j;
        if (gVar == null) {
            return JsonToken.END_DOCUMENT;
        }
        this.j = gVar.b;
        if (gVar == this.i) {
            this.i = null;
        }
        this.c.a(gVar);
        return gVar.f10957a;
    }

    private h c() {
        h hVar = this.l;
        if (hVar == null) {
            throw new IllegalStateException("No value can be given");
        }
        if (hVar == this.k) {
            this.k = null;
        }
        this.d.a(hVar);
        this.l = hVar.b;
        return hVar;
    }

    private void a(JsonToken jsonToken) throws IOException {
        JsonToken peek = peek();
        this.q = null;
        if (peek != jsonToken) {
            throw new IllegalStateException("jsonToken expected, but met actual");
        }
    }

    @Override // com.google.gson.stream.JsonReader
    public void beginObject() throws IOException {
        JsonToken jsonToken = JsonToken.BEGIN_OBJECT;
        this.m = jsonToken;
        a(jsonToken);
    }

    @Override // com.google.gson.stream.JsonReader
    public void endObject() throws IOException {
        JsonToken jsonToken = JsonToken.END_OBJECT;
        this.m = jsonToken;
        a(jsonToken);
    }

    @Override // com.google.gson.stream.JsonReader
    public void beginArray() throws IOException {
        JsonToken jsonToken = JsonToken.BEGIN_ARRAY;
        this.m = jsonToken;
        a(jsonToken);
    }

    @Override // com.google.gson.stream.JsonReader
    public void endArray() throws IOException {
        JsonToken jsonToken = JsonToken.END_ARRAY;
        this.m = jsonToken;
        a(jsonToken);
    }

    @Override // com.google.gson.stream.JsonReader
    public boolean hasNext() throws IOException {
        peek();
        return (this.q == JsonToken.END_OBJECT || this.q == JsonToken.END_ARRAY) ? false : true;
    }

    @Override // com.google.gson.stream.JsonReader
    public void skipValue() throws IOException {
        this.s = true;
        int i2 = 0;
        do {
            try {
                JsonToken peek = peek();
                if (peek != JsonToken.BEGIN_ARRAY && peek != JsonToken.BEGIN_OBJECT) {
                    if (peek != JsonToken.END_ARRAY && peek != JsonToken.END_OBJECT) {
                        if (this.k != null) {
                            c();
                        } else {
                            this.q = null;
                        }
                        this.q = null;
                    }
                    i2--;
                    this.q = null;
                }
                i2++;
                this.q = null;
            } finally {
                this.s = false;
            }
        } while (i2 != 0);
    }

    private void d() throws XmlPullParserException, IOException {
        b();
        c();
        if (this.f10950a.f10954a && a() == null) {
            a(true);
        }
        this.e.pop();
        this.e.pop();
        this.e.pop();
        int size = this.e.size();
        if (this.f10950a.f10954a && a() == JsonToken.STRING) {
            this.e.push(f.INSIDE_PRIMITIVE_ARRAY);
            return;
        }
        this.e.push(f.INSIDE_ARRAY);
        int i2 = size + 1;
        if (this.e.size() <= i2 || this.e.get(i2) != f.INSIDE_OBJECT) {
            this.e.push(f.INSIDE_OBJECT);
        }
        if (a() != JsonToken.BEGIN_OBJECT) {
            c(JsonToken.BEGIN_OBJECT);
        }
    }

    private void e() throws XmlPullParserException, IOException {
        JsonToken jsonToken = this.q;
        JsonToken jsonToken2 = this.m;
        if (jsonToken != jsonToken2 && jsonToken2 == JsonToken.BEGIN_ARRAY) {
            int i2 = AnonymousClass1.f10951a[this.q.ordinal()];
            if (i2 == 1) {
                this.q = JsonToken.BEGIN_ARRAY;
                f peek = this.e.peek();
                if (a() == JsonToken.NAME) {
                    if (this.f10950a.c) {
                        this.e.pop();
                        c(JsonToken.BEGIN_OBJECT);
                        this.e.push(f.INSIDE_EMBEDDED_ARRAY);
                        this.e.push(f.INSIDE_OBJECT);
                        if (peek == f.NAME) {
                            this.e.push(f.NAME);
                            return;
                        }
                        return;
                    }
                    d();
                    return;
                }
                return;
            }
            if (i2 == 2) {
                this.q = JsonToken.BEGIN_ARRAY;
                if (this.f10950a.c) {
                    if (this.f10950a.f10954a) {
                        c(JsonToken.STRING);
                        this.e.push(f.INSIDE_PRIMITIVE_EMBEDDED_ARRAY);
                        return;
                    }
                    c(JsonToken.END_OBJECT);
                    c(JsonToken.STRING);
                    c(JsonToken.NAME);
                    c(JsonToken.BEGIN_OBJECT);
                    b(c().f10958a);
                    b(SampleEvent.SEPARATOR);
                    this.e.push(f.INSIDE_EMBEDDED_ARRAY);
                    return;
                }
                c(JsonToken.END_ARRAY);
                return;
            }
            HwLog.i("WatchFaceConfigReader", "mToken is not a rule, do nothing");
        }
    }

    @Override // com.google.gson.stream.JsonReader
    public JsonToken peek() throws IOException {
        if (this.m == null && this.o) {
            return JsonToken.BEGIN_OBJECT;
        }
        if (this.q != null) {
            try {
                e();
                this.m = null;
                return this.q;
            } catch (XmlPullParserException e2) {
                throw new JsonSyntaxException("XML parsing exception", e2);
            }
        }
        try {
            a(false);
            this.m = null;
            JsonToken b2 = b();
            this.q = b2;
            return b2;
        } catch (XmlPullParserException e3) {
            throw new JsonSyntaxException("XML parsing exception", e3);
        }
    }

    @Override // com.google.gson.stream.JsonReader
    public String nextString() throws IOException {
        a(JsonToken.STRING);
        return c().f10958a;
    }

    @Override // com.google.gson.stream.JsonReader
    public boolean nextBoolean() throws IOException {
        a(JsonToken.BOOLEAN);
        String str = c().f10958a;
        if ("true".equalsIgnoreCase(str) || "false".equalsIgnoreCase(str)) {
            return true;
        }
        throw new IOException("Cannot parse mValue to boolean");
    }

    @Override // com.google.gson.stream.JsonReader
    public double nextDouble() throws IOException {
        a(JsonToken.STRING);
        return Double.parseDouble(c().f10958a);
    }

    @Override // com.google.gson.stream.JsonReader
    public int nextInt() throws IOException {
        a(JsonToken.STRING);
        return CommonUtils.b(c().f10958a);
    }

    @Override // com.google.gson.stream.JsonReader
    public long nextLong() throws IOException {
        a(JsonToken.STRING);
        return Long.parseLong(c().f10958a);
    }

    @Override // com.google.gson.stream.JsonReader
    public String nextName() throws IOException {
        this.m = JsonToken.NAME;
        a(JsonToken.NAME);
        return c().f10958a;
    }

    private i f() throws IOException, XmlPullParserException {
        int next = this.b.next();
        i iVar = this.g;
        iVar.a();
        if (next == 1) {
            this.n = true;
        } else if (next == 2) {
            iVar.f10959a = 1;
            iVar.b = this.b.getName();
            iVar.d = this.b.getNamespace();
            if (this.b.getAttributeCount() > 0) {
                this.h.a(this.b);
                iVar.e = this.h;
            }
        } else if (next == 3) {
            iVar.f10959a = 2;
            iVar.b = this.b.getName();
            iVar.d = this.b.getNamespace();
        } else if (next == 4) {
            String trim = this.b.getText().trim();
            if (trim.length() == 0) {
                this.p = true;
                iVar.f10959a = -1;
                return iVar;
            }
            this.p = false;
            iVar.f10959a = 3;
            iVar.c = trim;
        } else {
            HwLog.i("WatchFaceConfigReader", "nextXmlInfo default");
            iVar.f10959a = -1;
        }
        return iVar;
    }

    private void b(JsonToken jsonToken) {
        g a2 = this.c.a();
        a2.f10957a = jsonToken;
        a2.b = null;
        g gVar = this.i;
        if (gVar == null) {
            this.i = a2;
            this.j = a2;
        } else {
            gVar.b = a2;
            this.i = a2;
        }
    }

    private void a(String str) {
        h a2 = this.d.a();
        a2.f10958a = str.trim();
        a2.b = null;
        h hVar = this.k;
        if (hVar == null) {
            this.k = a2;
            this.l = a2;
        } else {
            hVar.b = a2;
            this.k = a2;
        }
    }

    private void a(a aVar) throws XmlPullParserException {
        int size = aVar.f10952a.size();
        for (int i2 = 0; i2 < size; i2++) {
            b(JsonToken.NAME);
            a("@" + aVar.a(i2));
            b(JsonToken.STRING);
            a(aVar.b.get(i2));
        }
    }

    private void c(JsonToken jsonToken) {
        g a2 = this.c.a();
        a2.f10957a = jsonToken;
        a2.b = null;
        g gVar = this.j;
        if (gVar == null) {
            this.j = a2;
            this.i = a2;
        } else {
            a2.b = gVar;
            this.j = a2;
        }
    }

    private void b(String str) {
        h a2 = this.d.a();
        a2.f10958a = str;
        a2.b = null;
        h hVar = this.l;
        if (hVar == null) {
            this.k = a2;
            this.l = a2;
        } else {
            a2.b = hVar;
            this.l = a2;
        }
    }

    private void a(boolean z) throws IOException, XmlPullParserException {
        while (true) {
            if ((this.i != null || this.n) && !z) {
                return;
            }
            i f2 = f();
            if (this.n) {
                if (this.f10950a.b) {
                    return;
                }
                b(JsonToken.END_OBJECT);
                return;
            }
            if (f2.f10959a != -1) {
                int i2 = f2.f10959a;
                if (i2 != 1) {
                    if (i2 == 2) {
                        d(f2);
                    } else if (i2 == 3) {
                        z = c(f2);
                        if (z && this.s) {
                            return;
                        }
                    } else {
                        HwLog.i("WatchFaceConfigReader", "xml type is not a rule, do nothing");
                    }
                } else if (this.o) {
                    this.o = false;
                    a(f2);
                } else {
                    b(f2);
                }
                z = false;
                if (z) {
                    continue;
                } else {
                    return;
                }
            }
        }
    }

    private void a(i iVar) throws XmlPullParserException {
        if (!this.f10950a.b) {
            b(this.m);
            this.e.push(f.INSIDE_OBJECT);
            b(iVar);
            return;
        }
        if (iVar.e != null) {
            b(JsonToken.BEGIN_OBJECT);
            this.e.push(f.INSIDE_OBJECT);
            a(iVar.e);
            return;
        }
        int i2 = AnonymousClass1.f10951a[this.m.ordinal()];
        if (i2 == 1) {
            b(JsonToken.BEGIN_OBJECT);
            this.e.push(f.INSIDE_OBJECT);
        } else if (i2 == 3) {
            b(JsonToken.BEGIN_ARRAY);
            this.e.push(this.f10950a.e ? f.INSIDE_PRIMITIVE_ARRAY : f.INSIDE_ARRAY);
        } else {
            HwLog.i("WatchFaceConfigReader", "First expectedToken does not begin_object or begin_array");
        }
    }

    private void b(i iVar) throws XmlPullParserException {
        f peek = this.e.peek();
        if (this.f10950a.c && peek.mIsInsideArray && this.f.size() > 0) {
            b peek2 = this.f.peek();
            if (peek2.f10953a == this.b.getDepth()) {
                if (!(this.f10950a.d ? iVar.a(this.b) : iVar.b).equals(peek2.b)) {
                    b(JsonToken.END_ARRAY);
                    g();
                    peek = this.e.peek();
                }
            }
        }
        int i2 = AnonymousClass1.b[peek.ordinal()];
        if (i2 == 1 || i2 == 2) {
            this.e.push(f.PRIMITIVE_VALUE);
        } else if (i2 == 3 || i2 == 4) {
            b(JsonToken.BEGIN_OBJECT);
            this.e.push(f.INSIDE_OBJECT);
        } else {
            if (i2 == 5) {
                b(JsonToken.BEGIN_OBJECT);
                this.e.push(f.INSIDE_OBJECT);
            }
            this.e.push(f.NAME);
            b(JsonToken.NAME);
            a(iVar.a(this.b));
            this.p = true;
        }
        if (iVar.e != null) {
            f peek3 = this.e.peek();
            if (peek3 == f.PRIMITIVE_VALUE) {
                throw new IllegalStateException("Attributes data in primitive scope");
            }
            if (peek3 == f.NAME) {
                b(JsonToken.BEGIN_OBJECT);
                this.e.push(f.INSIDE_OBJECT);
            }
            a(iVar.e);
        }
    }

    /* renamed from: com.huawei.watchface.cj$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {

        /* renamed from: a, reason: collision with root package name */
        static final /* synthetic */ int[] f10951a;
        static final /* synthetic */ int[] b;

        static {
            int[] iArr = new int[f.values().length];
            b = iArr;
            try {
                iArr[f.INSIDE_PRIMITIVE_ARRAY.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                b[f.INSIDE_PRIMITIVE_EMBEDDED_ARRAY.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                b[f.INSIDE_EMBEDDED_ARRAY.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                b[f.INSIDE_ARRAY.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                b[f.NAME.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                b[f.PRIMITIVE_VALUE.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                b[f.INSIDE_OBJECT.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            int[] iArr2 = new int[JsonToken.values().length];
            f10951a = iArr2;
            try {
                iArr2[JsonToken.BEGIN_OBJECT.ordinal()] = 1;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                f10951a[JsonToken.STRING.ordinal()] = 2;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                f10951a[JsonToken.BEGIN_ARRAY.ordinal()] = 3;
            } catch (NoSuchFieldError unused10) {
            }
        }
    }

    private boolean c(i iVar) {
        int i2 = AnonymousClass1.b[this.e.peek().ordinal()];
        if (i2 == 5) {
            a(iVar.c, true);
            return true;
        }
        if (i2 == 6) {
            a(iVar.c, false);
            return false;
        }
        if (i2 == 7) {
            int i3 = this.r;
            String str = SampleEvent.SEPARATOR;
            if (i3 > 0) {
                str = SampleEvent.SEPARATOR + this.r;
            }
            this.r++;
            b(JsonToken.NAME);
            a(str);
            a(iVar.c, false);
            return false;
        }
        HwLog.i("WatchFaceConfigReader", "Cannot process text xmlValue inside scope");
        return true;
    }

    private void a(String str, boolean z) {
        g gVar;
        if (z && (gVar = this.i) != null && gVar.f10957a == JsonToken.STRING) {
            if (str.length() > 0) {
                StringBuilder sb = new StringBuilder();
                h hVar = this.k;
                sb.append(hVar.f10958a);
                sb.append("");
                sb.append(str);
                hVar.f10958a = sb.toString();
                return;
            }
            return;
        }
        b(JsonToken.STRING);
        a(str);
    }

    private void g() {
        this.e.pop();
        if (this.e.isEmpty() || this.e.peek() != f.NAME) {
            return;
        }
        this.e.pop();
    }

    private void d(i iVar) throws XmlPullParserException {
        switch (AnonymousClass1.b[this.e.peek().ordinal()]) {
            case 1:
            case 4:
                b(JsonToken.END_ARRAY);
                g();
                break;
            case 2:
            case 3:
                b(JsonToken.END_ARRAY);
                b(JsonToken.END_OBJECT);
                g();
                g();
                break;
            case 5:
                if (this.p) {
                    a("", true);
                }
                g();
                break;
            case 6:
                this.e.pop();
                break;
            case 7:
                b(JsonToken.END_OBJECT);
                this.r = 0;
                g();
                break;
            default:
                HwLog.i("WatchFaceConfigReader", "mScopeStack's top element status is not a rule, do nothing");
                break;
        }
        if (this.f10950a.c) {
            int depth = this.b.getDepth();
            String a2 = this.f10950a.d ? iVar.a(this.b) : iVar.b;
            while (this.f.size() > 0 && this.f.peek().f10953a > depth) {
                this.f.pop();
            }
            if (this.f.size() == 0 || this.f.peek().f10953a < depth) {
                this.f.push(new b(depth, a2));
            } else {
                this.f.peek().b = a2;
            }
        }
    }

    enum f {
        INSIDE_OBJECT(false),
        INSIDE_ARRAY(true),
        INSIDE_EMBEDDED_ARRAY(true),
        INSIDE_PRIMITIVE_EMBEDDED_ARRAY(true),
        INSIDE_PRIMITIVE_ARRAY(true),
        PRIMITIVE_VALUE(false),
        NAME(false);

        final boolean mIsInsideArray;

        f(boolean z) {
            this.mIsInsideArray = z;
        }
    }

    static final class g {

        /* renamed from: a, reason: collision with root package name */
        JsonToken f10957a;
        g b;

        private g() {
        }

        /* synthetic */ g(AnonymousClass1 anonymousClass1) {
            this();
        }

        public String toString() {
            return this.f10957a + ", " + this.b;
        }
    }

    static final class h {

        /* renamed from: a, reason: collision with root package name */
        String f10958a;
        h b;

        private h() {
        }

        /* synthetic */ h(AnonymousClass1 anonymousClass1) {
            this();
        }

        public String toString() {
            return this.f10958a + ", " + this.b;
        }
    }

    static final class i {

        /* renamed from: a, reason: collision with root package name */
        int f10959a;
        String b;
        String c;
        String d;
        a e;

        private i() {
        }

        /* synthetic */ i(AnonymousClass1 anonymousClass1) {
            this();
        }

        public void a() {
            this.f10959a = -1;
            this.b = null;
            this.c = null;
            this.d = null;
            this.e = null;
        }

        String a(XmlPullParser xmlPullParser) throws XmlPullParserException {
            return cj.b(this.b, this.d, xmlPullParser);
        }
    }

    static class b {

        /* renamed from: a, reason: collision with root package name */
        int f10953a;
        String b;

        b(int i, String str) {
            this.f10953a = i;
            this.b = str;
        }

        public String toString() {
            return "'" + this.b + "'/" + this.f10953a;
        }
    }

    static final class e<T> {

        /* renamed from: a, reason: collision with root package name */
        private final c<T> f10955a;
        private final List<T> b = new ArrayList(32);

        e(c<T> cVar) {
            this.f10955a = cVar;
        }

        public T a() {
            if (this.b.isEmpty()) {
                return this.f10955a.create();
            }
            return this.b.remove(this.b.size() - 1);
        }

        void a(T t) {
            this.b.add(t);
        }
    }

    class a {

        /* renamed from: a, reason: collision with root package name */
        List<String> f10952a;
        List<String> b;
        List<String> c;
        int d = 0;

        a(int i) {
            b(i);
        }

        private void b(int i) {
            this.f10952a = new ArrayList(i);
            this.b = new ArrayList(i);
            this.c = new ArrayList(i);
            this.d = i;
        }

        public void a(XmlPullParser xmlPullParser) {
            int attributeCount = xmlPullParser.getAttributeCount();
            if (attributeCount > this.d) {
                b(attributeCount);
            }
            this.f10952a.clear();
            this.b.clear();
            this.c.clear();
            for (int i = 0; i < attributeCount; i++) {
                this.f10952a.add(xmlPullParser.getAttributeName(i));
                if (cj.this.f10950a.d) {
                    this.c.add(xmlPullParser.getAttributePrefix(i));
                } else {
                    this.c.add("");
                }
                this.b.add(xmlPullParser.getAttributeValue(i));
            }
        }

        String a(int i) throws XmlPullParserException {
            List<String> list = this.f10952a;
            boolean z = list != null && i < list.size() && i >= 0;
            List<String> list2 = this.c;
            return (list2 == null || i >= list2.size() || i < 0 || !z) ? "" : cj.b(this.f10952a.get(i), this.c.get(i), null);
        }
    }
}
