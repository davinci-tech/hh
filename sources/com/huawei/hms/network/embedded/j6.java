package com.huawei.hms.network.embedded;

import com.google.flatbuffers.reflection.BaseType;
import com.huawei.hms.framework.common.Logger;
import com.huawei.hms.network.base.common.FormBody;
import com.huawei.hms.network.base.common.Headers;
import com.huawei.hms.network.base.common.RequestBodyProviders;
import com.huawei.hms.network.base.util.HttpUtils;
import com.huawei.hms.network.embedded.c1;
import com.huawei.hms.network.httpclient.HttpClient;
import com.huawei.hms.network.httpclient.Request;
import com.huawei.hms.network.httpclient.RequestBody;
import com.huawei.hms.network.httpclient.Submit;
import com.huawei.hms.network.httpclient.config.NetworkConfig;
import java.io.EOFException;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import javax.annotation.Nullable;
import org.slf4j.Marker;

/* loaded from: classes9.dex */
public final class j6 {
    public static final String l = "RequestBuilder";
    public static final Pattern m = Pattern.compile("(.*/)?(\\.|%2e|%2E){1,2}(/.*)?");
    public static final char[] n = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
    public static final char[] o = {'\t', '\n', '\f', '\r'};
    public static final String p = " \"'<>#&=";
    public static final String q = " !\"#$&'(),/:;<=>?@[]\\^`{|}~";
    public static Method r;

    /* renamed from: a, reason: collision with root package name */
    public final Request.Builder f5324a;
    public String b;
    public String c;
    public d3 d;
    public String e;
    public Map<String, List<String>> f;
    public boolean g;

    @Nullable
    public FormBody.Builder h;

    @Nullable
    public RequestBody i;
    public boolean j;
    public NetworkConfig k = new NetworkConfig(null);

    public void setNetworkParameters(String str) {
        this.k.setOption(str);
    }

    public void setMetricsData(String str) {
        this.k.setOption(str);
    }

    public boolean hasBody() {
        return this.g;
    }

    public d3 getRequestUrl() throws IOException {
        String str;
        StringBuilder sb = new StringBuilder();
        if (!HttpUtils.isHttpOrGrsUrl(this.e)) {
            d3 d3Var = this.d;
            if (d3Var == null) {
                throw new IOException("baseUrl == null", new NullPointerException("baseUrl == null"));
            }
            sb.append(d3Var.getUrl());
        }
        sb.append(this.e);
        String b = b();
        if (!b.isEmpty()) {
            int lastIndexOf = sb.lastIndexOf("?");
            int length = sb.length();
            if (lastIndexOf >= 0) {
                str = lastIndexOf != length + (-1) ? "&" : "?";
                sb.append(b);
            }
            sb.append(str);
            sb.append(b);
        }
        return new d3(sb.toString());
    }

    public String getRelativeUrl() {
        return this.e;
    }

    public String getMethod() {
        return this.c;
    }

    public void c(String str, String str2) {
        String str3 = this.e;
        if (str3 == null) {
            throw new AssertionError();
        }
        String replace = str3.replace("{" + str + "}", str2);
        if (!m.matcher(replace).matches()) {
            this.e = replace;
        } else {
            throw new IllegalArgumentException("@Path parameters shouldn't perform path traversal ('.' or '..'): " + str2);
        }
    }

    public void b(String str, String str2) {
        if ("Content-Type".equalsIgnoreCase(str)) {
            this.b = str2;
        } else {
            this.f5324a.addHeader(str, str2);
        }
    }

    public void a(String str, String str2, boolean z) {
        if (this.f == null) {
            this.f = new HashMap();
        }
        String str3 = z ? " \"'<>#&=" : " !\"#$&'(),/:;<=>?@[]\\^`{|}~";
        String b = b(str, str3, z);
        String b2 = str2 != null ? b(str2, str3, z) : null;
        if (this.f.containsKey(b)) {
            this.f.get(b).add(b2);
            return;
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add(b2);
        this.f.put(b, arrayList);
    }

    public void a(String str, String str2) {
        FormBody.Builder builder = this.h;
        if (builder != null) {
            builder.add(str, str2);
        }
    }

    public void a(String str) {
        this.e = str;
    }

    public void a(RequestBody requestBody) {
        this.i = requestBody;
    }

    public Request a() throws IOException {
        RequestBody requestBody = this.i;
        if (requestBody == null) {
            FormBody.Builder builder = this.h;
            if (builder != null) {
                requestBody = builder.build();
            } else if (hasBody()) {
                requestBody = RequestBodyProviders.create((String) null, new byte[0]);
            }
        }
        String str = this.b;
        if (str != null) {
            if (requestBody != null) {
                requestBody = new a(requestBody, str);
            } else {
                this.f5324a.addHeader("Content-Type", str);
            }
        }
        this.f5324a.url(getRequestUrl().getUrl()).method(this.c).requestBody(requestBody);
        if (this.j) {
            this.k.setValue("core_connect_empty_body", true);
        }
        this.f5324a.options(this.k.toString());
        return this.f5324a.build();
    }

    private String b(String str, String str2, boolean z) {
        try {
            return a(str, 0, str.length(), str2, z);
        } catch (EOFException e) {
            Logger.w(l, "EOFException queryParamEncode err", e);
            return str;
        }
    }

    private String b() {
        StringBuilder sb = new StringBuilder();
        Map<String, List<String>> map = this.f;
        if (map != null && !map.isEmpty()) {
            Iterator<Map.Entry<String, List<String>>> it = this.f.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry<String, List<String>> next = it.next();
                List<String> value = next.getValue();
                for (int i = 0; i < value.size(); i++) {
                    sb.append(next.getKey());
                    sb.append("=");
                    sb.append(value.get(i));
                    if (i != value.size() - 1) {
                        sb.append("&");
                    }
                }
                if (it.hasNext()) {
                    sb.append("&");
                }
            }
        }
        return sb.toString();
    }

    private boolean a(int i, String str, boolean z) {
        if (i < 32 || i == 127 || i >= 128 || str.indexOf(i) != -1) {
            return true;
        }
        return i == 37 && !z;
    }

    public static class a extends RequestBody {

        /* renamed from: a, reason: collision with root package name */
        public final RequestBody f5325a;
        public final String b;

        @Override // com.huawei.hms.network.httpclient.RequestBody
        public void writeTo(OutputStream outputStream) throws IOException {
            this.f5325a.writeTo(outputStream);
        }

        @Override // com.huawei.hms.network.httpclient.RequestBody
        public String contentType() {
            return this.b;
        }

        @Override // com.huawei.hms.network.httpclient.RequestBody
        public long contentLength() throws IOException {
            return this.f5325a.contentLength();
        }

        public a(RequestBody requestBody, String str) {
            this.f5325a = requestBody;
            this.b = str;
        }
    }

    private boolean a(int i) {
        for (char c : o) {
            if (i == c) {
                return true;
            }
        }
        return false;
    }

    private void a(bb bbVar, String str, int i, int i2, String str2, boolean z) throws EOFException {
        bb bbVar2 = null;
        while (i < i2) {
            int codePointAt = str.codePointAt(i);
            if (!z || !a(codePointAt)) {
                if (codePointAt == 43) {
                    bbVar.a(z ? Marker.ANY_NON_NULL_MARKER : "%2B");
                } else if (a(codePointAt, str2, z)) {
                    if (bbVar2 == null) {
                        bbVar2 = new bb();
                    }
                    bbVar2.c(codePointAt);
                    while (!bbVar2.i()) {
                        byte readByte = bbVar2.readByte();
                        bbVar.writeByte(37);
                        char[] cArr = n;
                        bbVar.writeByte((int) cArr[((readByte & 255) >> 4) & 15]);
                        bbVar.writeByte((int) cArr[readByte & BaseType.Obj]);
                    }
                } else {
                    bbVar.c(codePointAt);
                }
            }
            i += Character.charCount(codePointAt);
        }
    }

    private String a(String str, int i, int i2, String str2, boolean z) throws EOFException {
        int i3 = i;
        while (i3 < i2) {
            int codePointAt = str.codePointAt(i3);
            if (a(codePointAt, str2, z) || codePointAt == 43) {
                bb bbVar = new bb();
                bbVar.a(str, i, i3);
                a(bbVar, str, i3, i2, str2, z);
                return bbVar.o();
            }
            i3 += Character.charCount(codePointAt);
        }
        return str.substring(i, i2);
    }

    public j6(Submit.Factory factory, String str, d3 d3Var, @Nullable String str2, @Nullable Headers headers, @Nullable String str3, boolean z, boolean z2, boolean z3) {
        this.c = str;
        this.d = d3Var;
        this.e = str2;
        this.b = str3;
        this.g = z;
        this.j = z3;
        this.f5324a = factory instanceof HttpClient ? ((HttpClient) factory).newRequest() : new c1.b();
        if (headers != null) {
            for (int i = 0; i < headers.size(); i++) {
                this.f5324a.addHeader(headers.name(i), headers.value(i));
            }
        }
        if (z2) {
            this.h = new FormBody.Builder();
        }
    }
}
