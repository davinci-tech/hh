package com.huawei.openalliance.ad.net.http;

import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;
import com.huawei.openalliance.ad.fh;
import com.huawei.openalliance.ad.utils.cz;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes5.dex */
public class f {

    /* renamed from: a, reason: collision with root package name */
    final String f7307a;
    final String b;
    final int c;
    final List<String> d;
    final List<String> e;
    final String f;
    final boolean g;
    final String h;

    public static class a {

        /* renamed from: a, reason: collision with root package name */
        String f7308a;
        String b;
        int c;
        final List<String> d;
        List<String> e;
        String f;
        boolean g;
        String h;

        public f a() {
            return new f(this);
        }

        public a a(List<String> list) {
            if (list != null) {
                this.e.addAll(list);
            }
            return this;
        }

        public a a(Context context, String str) {
            if (TextUtils.isEmpty(str) || !cz.j(str)) {
                str = fh.b(context).a(context, str);
            }
            if (TextUtils.isEmpty(str)) {
                return this;
            }
            this.h = str;
            Uri parse = Uri.parse(str);
            this.f7308a = parse.getScheme();
            this.b = cz.k(str);
            this.c = parse.getPort();
            List<String> pathSegments = parse.getPathSegments();
            if (pathSegments != null) {
                this.d.addAll(pathSegments);
            }
            String encodedQuery = parse.getEncodedQuery();
            if (!TextUtils.isEmpty(encodedQuery)) {
                for (String str2 : encodedQuery.split("&")) {
                    this.e.add(str2);
                }
            }
            this.f = parse.getEncodedFragment();
            return this;
        }

        public a(boolean z) {
            this.d = new ArrayList();
            this.e = new ArrayList();
            this.g = z;
        }

        public a() {
            this.d = new ArrayList();
            this.e = new ArrayList();
            this.g = false;
        }
    }

    public String c() {
        int size;
        StringBuilder sb = new StringBuilder();
        sb.append(this.f7307a);
        sb.append("://");
        sb.append(this.b);
        if (this.c > 0) {
            sb.append(':');
            sb.append(this.c);
        }
        sb.append('/');
        List<String> list = this.d;
        if (list != null) {
            int size2 = list.size();
            for (int i = 0; i < size2; i++) {
                sb.append(this.d.get(i));
                sb.append('/');
            }
        }
        cz.a(sb, '/');
        List<String> list2 = this.e;
        if (list2 != null && (size = list2.size()) > 0) {
            sb.append('?');
            for (int i2 = 0; i2 < size; i2++) {
                sb.append(this.e.get(i2));
                sb.append('&');
            }
            cz.a(sb, '&');
        }
        if (!TextUtils.isEmpty(this.f)) {
            sb.append('#');
            sb.append(this.f);
        }
        return sb.toString();
    }

    public String b() {
        return this.h;
    }

    public boolean a() {
        return this.g;
    }

    f(a aVar) {
        this.f7307a = aVar.f7308a;
        this.b = aVar.b;
        this.c = aVar.c;
        this.d = aVar.d;
        this.e = aVar.e;
        this.f = aVar.f;
        this.g = aVar.g;
        this.h = aVar.h;
    }
}
