package com.huawei.harmonyos.interwork.base.utils;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import com.google.flatbuffers.reflection.BaseType;
import com.huawei.harmonyos.interwork.base.utils.Uri;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CodingErrorAction;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.regex.Pattern;

/* loaded from: classes3.dex */
public abstract class Uri implements Parcelable, Comparable<Uri> {
    private static final int DEFAULT_PORT = -1;
    private static final int FIRST_POS = 0;
    private static final char FRAGMENT_SEPARATOR = '#';
    private static final int HEXADECIMAL = 16;
    private static final int HEXVALUE_BEGIN = 10;
    private static final int HIERARCHICAL_URI = 2;
    private static final int HIGH_MASK = 240;
    private static final String INVALID_INPUT_CHARACTER = "�";
    private static final char LOWER_CASE_BEGIN = 'a';
    private static final char LOWER_CASE_F = 'f';
    private static final int LOW_MASK = 15;
    private static final int NOT_FOUND = -1;
    private static final char NUMBER_BEGIN = '0';
    private static final char NUMBER_END = '9';
    private static final int OPAQUE_URI = 1;
    private static final String PATH_ALLOW = "/";
    private static final char PERCENT_SIGN = '%';
    private static final int POS_INC = 1;
    private static final int POS_INC_MORE = 2;
    private static final char QUERY_FLAG = '?';
    private static final char RIGHT_SEPARATOR = '\\';
    private static final char SCHEME_FRAGMENT = '#';
    private static final char SCHEME_SEPARATOR = ':';
    private static final int SECOND_POS = 1;
    private static final char SLASH_SEPARATOR = '/';
    private static final char UPPER_CASE_BEGIN = 'A';
    private static final char UPPER_CASE_F = 'F';
    String scheme;
    public static final Parcelable.Creator<Uri> CREATOR = new Parcelable.Creator<Uri>() { // from class: com.huawei.harmonyos.interwork.base.utils.Uri.4
        @Override // android.os.Parcelable.Creator
        /* renamed from: Ak_, reason: merged with bridge method [inline-methods] */
        public final Uri createFromParcel(Parcel parcel) {
            int readInt = parcel.readInt();
            if (readInt == 1) {
                return OpaqueUri.makeFromParcel(parcel);
            }
            if (readInt == 2) {
                return HierarchicalUri.makeFromParcel(parcel);
            }
            throw new IllegalArgumentException("unsupported URI type.");
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public final Uri[] newArray(int i) {
            return new Uri[i];
        }
    };
    private static final Pattern URI_SCHEME_PATTERN = Pattern.compile("^[a-zA-Z][a-zA-Z0-9|\\+|\\-|\\.]*$");
    private static final char[] HEXADECIMAL_DIGITS = "0123456789ABCDEF".toCharArray();

    private static int hexCharToValue(char c) {
        if (c >= '0' && c <= '9') {
            return c - NUMBER_BEGIN;
        }
        int i = 97;
        if (c < 'a' || c > 'f') {
            i = 65;
            if (c < 'A' || c > 'F') {
                return -1;
            }
        }
        return (c + '\n') - i;
    }

    public abstract e buildUpon();

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public abstract String getDecodedAuthority();

    public abstract String getDecodedFragment();

    public abstract String getDecodedHost();

    public abstract String getDecodedPath();

    public abstract List<String> getDecodedPathList();

    public abstract String getDecodedQuery();

    public abstract Map<String, List<String>> getDecodedQueryParams();

    public abstract String getDecodedSchemeSpecificPart();

    public abstract String getDecodedUserInfo();

    public abstract String getEncodedAuthority();

    public abstract String getEncodedFragment();

    public abstract String getEncodedHost();

    public abstract String getEncodedPath();

    public abstract String getEncodedQuery();

    public abstract String getEncodedSchemeSpecificPart();

    public abstract String getEncodedUserInfo();

    public abstract int getPort();

    public abstract boolean isHierarchical();

    public abstract String toString();

    private Uri(String str) {
        this.scheme = str;
    }

    public boolean isOpaque() {
        return !isHierarchical();
    }

    public boolean isRelative() {
        return this.scheme == null;
    }

    public boolean isAbsolute() {
        return !isRelative();
    }

    public String getScheme() {
        return this.scheme;
    }

    public static final class e {

        /* renamed from: a, reason: collision with root package name */
        private a f2166a;
        private a b;
        private String c;
        private a d;
        private a e;
        private a i;

        public final e i(String str) {
            this.c = str;
            return this;
        }

        final e e(a aVar) {
            this.i = aVar;
            return this;
        }

        public final e b(String str) {
            return e(a.c(null, str));
        }

        final e d(a aVar) {
            this.e = aVar;
            return this;
        }

        public final e d(String str) {
            return d(a.c(null, str));
        }

        final e c(a aVar) {
            this.f2166a = aVar;
            return this;
        }

        public final e e(String str) {
            return c(a.c(null, str));
        }

        public final e c(String str) {
            a aVar = this.f2166a;
            if (aVar == null) {
                return e("/".concat(String.valueOf(str)));
            }
            String d = aVar.d("/");
            if (!d.endsWith("/")) {
                d = d + "/";
            }
            return e(d + str);
        }

        final e b(a aVar) {
            this.d = aVar;
            return this;
        }

        public final e f(String str) {
            return b(a.c(null, str));
        }

        final e a(a aVar) {
            this.b = aVar;
            return this;
        }

        public final e a(String str) {
            return a(a.c(null, str));
        }

        public final Uri a() {
            a aVar;
            if (this.i != null) {
                if (this.c == null) {
                    throw new UnsupportedOperationException("The scheme part can't be null.");
                }
                return new OpaqueUri(this.c, this.i, this.b);
            }
            if ((this.c != null || this.e != null) && (aVar = this.f2166a) != null) {
                aVar.d();
            }
            return new HierarchicalUri(this.c, this.e, this.f2166a, this.d, this.b);
        }
    }

    public static class HierarchicalUri extends Uri {
        private a authority;
        private a fragment;
        private a host;
        private final Object lock;
        private a path;
        private volatile List<String> pathList;
        private Integer port;
        private a query;
        private volatile Map<String, List<String>> queryParamMap;
        private a ssp;
        private String uriStrCache;
        private a userInfo;

        @Override // com.huawei.harmonyos.interwork.base.utils.Uri
        public boolean isHierarchical() {
            return true;
        }

        @Override // com.huawei.harmonyos.interwork.base.utils.Uri, java.lang.Comparable
        public /* bridge */ /* synthetic */ int compareTo(Uri uri) {
            return super.compareTo(uri);
        }

        private HierarchicalUri(String str, a aVar, a aVar2, a aVar3, a aVar4) {
            super(str);
            this.lock = new Object();
            this.authority = aVar;
            this.path = aVar2;
            this.query = aVar3;
            this.fragment = aVar4;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            if (parcel == null) {
                return;
            }
            parcel.writeInt(2);
            parcel.writeString(this.scheme);
            a.Am_(this.authority, parcel, null);
            a.Am_(this.path, parcel, "/");
            a.Am_(this.query, parcel, null);
            a.Am_(this.fragment, parcel, null);
        }

        public static HierarchicalUri makeFromParcel(Parcel parcel) {
            return new HierarchicalUri(parcel.readString(), a.Al_(parcel), a.Al_(parcel), a.Al_(parcel), a.Al_(parcel));
        }

        @Override // com.huawei.harmonyos.interwork.base.utils.Uri
        public String getDecodedSchemeSpecificPart() {
            return a.a(generateSsp().orElse(null)).orElse(null);
        }

        @Override // com.huawei.harmonyos.interwork.base.utils.Uri
        public String getEncodedSchemeSpecificPart() {
            return a.b(generateSsp().orElse(null), "/").orElse(null);
        }

        private Optional<a> generateSsp() {
            a aVar = this.ssp;
            if (aVar != null) {
                return Optional.ofNullable(aVar);
            }
            final StringBuilder sb = new StringBuilder();
            Optional.ofNullable(this.authority).ifPresent(new Consumer() { // from class: bwr
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    Uri.HierarchicalUri.lambda$generateSsp$0(sb, (Uri.a) obj);
                }
            });
            Optional.ofNullable(this.path).ifPresent(new Consumer() { // from class: bww
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    Uri.HierarchicalUri.lambda$generateSsp$1(sb, (Uri.a) obj);
                }
            });
            Optional.ofNullable(this.query).ifPresent(new Consumer() { // from class: bwy
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    Uri.HierarchicalUri.lambda$generateSsp$2(sb, (Uri.a) obj);
                }
            });
            if (sb.length() == 0) {
                return Optional.empty();
            }
            a c = a.c(null, sb.toString());
            this.ssp = c;
            return Optional.ofNullable(c);
        }

        public static /* synthetic */ void lambda$generateSsp$0(StringBuilder sb, a aVar) {
            String d = aVar.d(null);
            if (TextUtils.isEmpty(d)) {
                return;
            }
            sb.append("//");
            sb.append(d);
        }

        public static /* synthetic */ void lambda$generateSsp$1(StringBuilder sb, a aVar) {
            String d = aVar.d("/");
            if (TextUtils.isEmpty(d)) {
                return;
            }
            sb.append(d);
        }

        public static /* synthetic */ void lambda$generateSsp$2(StringBuilder sb, a aVar) {
            String d = aVar.d(null);
            if (TextUtils.isEmpty(d)) {
                return;
            }
            sb.append("?");
            sb.append(d);
        }

        @Override // com.huawei.harmonyos.interwork.base.utils.Uri
        public String getDecodedAuthority() {
            return a.a(this.authority).orElse(null);
        }

        @Override // com.huawei.harmonyos.interwork.base.utils.Uri
        public String getEncodedAuthority() {
            return a.b(this.authority, null).orElse(null);
        }

        @Override // com.huawei.harmonyos.interwork.base.utils.Uri
        public String getDecodedUserInfo() {
            return a.a(generateUserInfo(false)).orElse(null);
        }

        @Override // com.huawei.harmonyos.interwork.base.utils.Uri
        public String getEncodedUserInfo() {
            return a.b(generateUserInfo(true), null).orElse(null);
        }

        private a generateUserInfo(boolean z) {
            if (this.userInfo == null) {
                generateAuthority();
            }
            return this.userInfo;
        }

        @Override // com.huawei.harmonyos.interwork.base.utils.Uri
        public String getDecodedHost() {
            return a.a(generateHost()).orElse(null);
        }

        @Override // com.huawei.harmonyos.interwork.base.utils.Uri
        public String getEncodedHost() {
            return a.b(generateHost(), null).orElse(null);
        }

        private a generateHost() {
            if (this.host == null) {
                generateAuthority();
            }
            return this.host;
        }

        @Override // com.huawei.harmonyos.interwork.base.utils.Uri
        public int getPort() {
            return generatePort().intValue();
        }

        private Integer generatePort() {
            if (this.port == null) {
                generateAuthority();
            }
            return this.port;
        }

        private void generateAuthority() {
            String substring;
            String encodedAuthority = getEncodedAuthority();
            if (encodedAuthority == null) {
                this.port = -1;
                return;
            }
            int lastIndexOf = encodedAuthority.lastIndexOf(64);
            this.userInfo = lastIndexOf == -1 ? null : a.c(null, encodedAuthority.substring(0, lastIndexOf));
            int findPortIndex = findPortIndex(encodedAuthority);
            int i = lastIndexOf + 1;
            if (findPortIndex == -1) {
                substring = encodedAuthority.substring(i);
            } else {
                substring = encodedAuthority.substring(i, findPortIndex);
            }
            this.host = a.c(null, substring);
            if (findPortIndex == -1) {
                this.port = -1;
            }
            try {
                this.port = Integer.valueOf(Integer.parseInt(decode(encodedAuthority.substring(findPortIndex + 1))));
            } catch (NumberFormatException unused) {
                this.port = -1;
            }
        }

        private int findPortIndex(String str) {
            if (str == null || str.isEmpty()) {
                return -1;
            }
            for (int length = str.length() - 1; length >= 0; length--) {
                char charAt = str.charAt(length);
                if (charAt == ':') {
                    return length;
                }
                if (charAt < '0' || charAt > '9') {
                    return -1;
                }
            }
            return -1;
        }

        @Override // com.huawei.harmonyos.interwork.base.utils.Uri
        public String getDecodedPath() {
            return a.a(this.path).orElse(null);
        }

        @Override // com.huawei.harmonyos.interwork.base.utils.Uri
        public String getEncodedPath() {
            return a.b(this.path, "/").orElse(null);
        }

        @Override // com.huawei.harmonyos.interwork.base.utils.Uri
        public String getDecodedQuery() {
            return a.a(this.query).orElse(null);
        }

        @Override // com.huawei.harmonyos.interwork.base.utils.Uri
        public String getEncodedQuery() {
            return a.b(this.query, null).orElse(null);
        }

        @Override // com.huawei.harmonyos.interwork.base.utils.Uri
        public List<String> getDecodedPathList() {
            if (this.pathList != null) {
                return this.pathList;
            }
            String encodedPath = getEncodedPath();
            int i = 0;
            if (encodedPath == null) {
                ArrayList arrayList = new ArrayList(0);
                this.pathList = arrayList;
                return arrayList;
            }
            synchronized (this.lock) {
                if (this.pathList != null) {
                    return this.pathList;
                }
                ArrayList arrayList2 = new ArrayList();
                while (true) {
                    int indexOf = encodedPath.indexOf(47, i);
                    if (indexOf < 0) {
                        break;
                    }
                    if (i < indexOf) {
                        arrayList2.add(decode(encodedPath.substring(i, indexOf)));
                    }
                    i = indexOf + 1;
                }
                if (i < encodedPath.length()) {
                    arrayList2.add(decode(encodedPath.substring(i)));
                }
                this.pathList = arrayList2;
                return arrayList2;
            }
        }

        @Override // com.huawei.harmonyos.interwork.base.utils.Uri
        public Map<String, List<String>> getDecodedQueryParams() {
            if (this.queryParamMap != null) {
                return this.queryParamMap;
            }
            String encodedQuery = getEncodedQuery();
            if (encodedQuery == null) {
                HashMap hashMap = new HashMap();
                this.queryParamMap = hashMap;
                return hashMap;
            }
            synchronized (this.lock) {
                if (this.queryParamMap != null) {
                    return this.queryParamMap;
                }
                HashMap hashMap2 = new HashMap();
                int i = 0;
                while (true) {
                    int indexOf = encodedQuery.indexOf(38, i);
                    int length = indexOf != -1 ? indexOf : encodedQuery.length();
                    int indexOf2 = encodedQuery.indexOf(61, i);
                    if (indexOf2 > length || indexOf2 == -1) {
                        indexOf2 = length;
                    }
                    String decode = decode(encodedQuery.substring(i, indexOf2));
                    String decode2 = indexOf2 != length ? decode(encodedQuery.substring(indexOf2 + 1, length)) : "";
                    if (!hashMap2.containsKey(decode)) {
                        hashMap2.put(decode, new ArrayList());
                    }
                    ((List) hashMap2.get(decode)).add(decode2);
                    if (indexOf == -1) {
                        this.queryParamMap = hashMap2;
                        return hashMap2;
                    }
                    i = indexOf + 1;
                }
            }
        }

        @Override // com.huawei.harmonyos.interwork.base.utils.Uri
        public String getDecodedFragment() {
            return a.a(this.fragment).orElse(null);
        }

        @Override // com.huawei.harmonyos.interwork.base.utils.Uri
        public String getEncodedFragment() {
            return a.b(this.fragment, null).orElse(null);
        }

        @Override // com.huawei.harmonyos.interwork.base.utils.Uri
        public e buildUpon() {
            return new e().i(this.scheme).d(this.authority).c(this.path).b(this.query).a(this.fragment);
        }

        @Override // com.huawei.harmonyos.interwork.base.utils.Uri
        public String toString() {
            return (String) Optional.ofNullable(this.uriStrCache).orElseGet(new Supplier() { // from class: bwt
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Uri.HierarchicalUri.this.m129x5b6d6924();
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: getUriStr, reason: merged with bridge method [inline-methods] */
        public String m129x5b6d6924() {
            StringBuilder sb = new StringBuilder();
            if (this.scheme != null) {
                sb.append(this.scheme);
                sb.append(Uri.SCHEME_SEPARATOR);
            }
            if (this.ssp == null) {
                generateSsp();
            }
            if (this.ssp != null) {
                String encodedSchemeSpecificPart = getEncodedSchemeSpecificPart();
                if (!TextUtils.isEmpty(encodedSchemeSpecificPart)) {
                    sb.append(encodedSchemeSpecificPart);
                }
            }
            if (this.fragment != null) {
                String encodedFragment = getEncodedFragment();
                if (!TextUtils.isEmpty(encodedFragment)) {
                    sb.append('#');
                    sb.append(encodedFragment);
                }
            }
            String sb2 = sb.toString();
            this.uriStrCache = sb2;
            return sb2;
        }
    }

    public static class OpaqueUri extends Uri {
        private a fragment;
        private a opaqueSsp;
        private String uriStrCache;

        @Override // com.huawei.harmonyos.interwork.base.utils.Uri
        public int getPort() {
            return -1;
        }

        @Override // com.huawei.harmonyos.interwork.base.utils.Uri
        public boolean isHierarchical() {
            return false;
        }

        @Override // com.huawei.harmonyos.interwork.base.utils.Uri, java.lang.Comparable
        public /* bridge */ /* synthetic */ int compareTo(Uri uri) {
            return super.compareTo(uri);
        }

        private OpaqueUri(String str, a aVar, a aVar2) {
            super(str);
            this.opaqueSsp = (a) Optional.ofNullable(aVar).orElseThrow(new Supplier() { // from class: bwx
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Uri.OpaqueUri.lambda$new$0();
                }
            });
            this.fragment = aVar2;
        }

        public static /* synthetic */ UnsupportedOperationException lambda$new$0() {
            return new UnsupportedOperationException("The ssp part can't be null.");
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            if (parcel == null) {
                return;
            }
            parcel.writeInt(1);
            parcel.writeString(this.scheme);
            a.Am_(this.opaqueSsp, parcel, null);
            a.Am_(this.fragment, parcel, null);
        }

        public static OpaqueUri makeFromParcel(Parcel parcel) {
            return new OpaqueUri(parcel.readString(), a.Al_(parcel), a.Al_(parcel));
        }

        @Override // com.huawei.harmonyos.interwork.base.utils.Uri
        public String getDecodedSchemeSpecificPart() {
            return this.opaqueSsp.b();
        }

        @Override // com.huawei.harmonyos.interwork.base.utils.Uri
        public String getEncodedSchemeSpecificPart() {
            return this.opaqueSsp.d(null);
        }

        @Override // com.huawei.harmonyos.interwork.base.utils.Uri
        public List<String> getDecodedPathList() {
            return new ArrayList(0);
        }

        @Override // com.huawei.harmonyos.interwork.base.utils.Uri
        public Map<String, List<String>> getDecodedQueryParams() {
            return new HashMap();
        }

        @Override // com.huawei.harmonyos.interwork.base.utils.Uri
        public String getDecodedFragment() {
            return a.a(this.fragment).orElse(null);
        }

        @Override // com.huawei.harmonyos.interwork.base.utils.Uri
        public String getEncodedFragment() {
            return a.b(this.fragment, null).orElse(null);
        }

        @Override // com.huawei.harmonyos.interwork.base.utils.Uri
        public e buildUpon() {
            return new e().i(this.scheme).e(this.opaqueSsp).a(this.fragment);
        }

        @Override // com.huawei.harmonyos.interwork.base.utils.Uri
        public String toString() {
            return (String) Optional.ofNullable(this.uriStrCache).orElseGet(new Supplier() { // from class: bwz
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Uri.OpaqueUri.this.m130xfc9bc04a();
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: getUriStr, reason: merged with bridge method [inline-methods] */
        public String m130xfc9bc04a() {
            StringBuilder sb = new StringBuilder();
            sb.append(this.scheme);
            sb.append(Uri.SCHEME_SEPARATOR);
            sb.append(getEncodedSchemeSpecificPart());
            if (this.fragment != null) {
                sb.append('#');
                sb.append(getEncodedFragment());
            }
            String sb2 = sb.toString();
            this.uriStrCache = sb2;
            return sb2;
        }

        @Override // com.huawei.harmonyos.interwork.base.utils.Uri
        public String getEncodedUserInfo() {
            return "";
        }

        @Override // com.huawei.harmonyos.interwork.base.utils.Uri
        public String getEncodedQuery() {
            return "";
        }

        @Override // com.huawei.harmonyos.interwork.base.utils.Uri
        public String getEncodedPath() {
            return "";
        }

        @Override // com.huawei.harmonyos.interwork.base.utils.Uri
        public String getEncodedHost() {
            return "";
        }

        @Override // com.huawei.harmonyos.interwork.base.utils.Uri
        public String getEncodedAuthority() {
            return "";
        }

        @Override // com.huawei.harmonyos.interwork.base.utils.Uri
        public String getDecodedUserInfo() {
            return "";
        }

        @Override // com.huawei.harmonyos.interwork.base.utils.Uri
        public String getDecodedQuery() {
            return "";
        }

        @Override // com.huawei.harmonyos.interwork.base.utils.Uri
        public String getDecodedPath() {
            return "";
        }

        @Override // com.huawei.harmonyos.interwork.base.utils.Uri
        public String getDecodedHost() {
            return "";
        }

        @Override // com.huawei.harmonyos.interwork.base.utils.Uri
        public String getDecodedAuthority() {
            return "";
        }
    }

    public static class a {

        /* renamed from: a, reason: collision with root package name */
        private volatile String f2165a;
        private volatile String d;

        private a(String str, String str2) {
            this.f2165a = str;
            this.d = str2;
        }

        static a c(String str, String str2) {
            if (str == null && str2 == null) {
                return null;
            }
            return new a(str, str2);
        }

        static a Al_(Parcel parcel) {
            return c(parcel.readString(), parcel.readString());
        }

        static void Am_(a aVar, Parcel parcel, String str) {
            if (aVar != null) {
                parcel.writeString(aVar.b());
                parcel.writeString(aVar.d(str));
            } else {
                parcel.writeString(null);
                parcel.writeString(null);
            }
        }

        static Optional<String> a(a aVar) {
            if (aVar != null) {
                return Optional.ofNullable(aVar.b());
            }
            return Optional.empty();
        }

        static Optional<String> b(a aVar, String str) {
            if (aVar != null) {
                return Optional.ofNullable(aVar.d(str));
            }
            return Optional.empty();
        }

        String b() {
            return (String) Optional.ofNullable(this.f2165a).orElseGet(new Supplier() { // from class: bxe
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Uri.a.this.e();
                }
            });
        }

        public /* synthetic */ String e() {
            String decode = Uri.decode(this.d);
            this.f2165a = decode;
            return decode;
        }

        public /* synthetic */ String b(String str) {
            String orElse = Uri.encode(this.f2165a, str).orElse(null);
            this.d = orElse;
            return orElse;
        }

        String d(final String str) {
            return (String) Optional.ofNullable(this.d).orElseGet(new Supplier() { // from class: bxd
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Uri.a.this.b(str);
                }
            });
        }

        void d() {
            Optional.ofNullable(this.f2165a).ifPresent(new Consumer() { // from class: bwv
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    Uri.a.this.e((String) obj);
                }
            });
            Optional.ofNullable(this.d).ifPresent(new Consumer() { // from class: bxc
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    Uri.a.this.a((String) obj);
                }
            });
        }

        public /* synthetic */ void e(String str) {
            if (str.startsWith("/")) {
                return;
            }
            this.f2165a = "/".concat(String.valueOf(str));
        }

        public /* synthetic */ void a(String str) {
            if (str.startsWith("/")) {
                return;
            }
            this.d = "/".concat(String.valueOf(str));
        }
    }

    public static String encode(String str) {
        return encode(str, null).orElse(null);
    }

    public static Optional<String> encode(String str, String str2) {
        if (str == null) {
            return Optional.empty();
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            char charAt = str.charAt(i);
            if (isAllowed(charAt, str2)) {
                sb.append(charAt);
            } else {
                for (byte b : String.valueOf(charAt).getBytes(StandardCharsets.UTF_8)) {
                    sb.append(PERCENT_SIGN);
                    char[] cArr = HEXADECIMAL_DIGITS;
                    sb.append(cArr[(b & 240) >> 4]);
                    sb.append(cArr[b & BaseType.Obj]);
                }
            }
        }
        return Optional.ofNullable(sb.toString());
    }

    private static boolean isAllowed(char c, String str) {
        if (c >= 'A' && c <= 'Z') {
            return true;
        }
        if (c >= 'a' && c <= 'z') {
            return true;
        }
        if ((c < '0' || c > '9') && "_-!.~'()*".indexOf(c) == -1) {
            return (str == null || str.indexOf(c) == -1) ? false : true;
        }
        return true;
    }

    public static String decode(String str) {
        if (str == null || str.isEmpty() || str.indexOf(37) < 0) {
            return str;
        }
        StringBuilder sb = new StringBuilder();
        CharsetDecoder onUnmappableCharacter = StandardCharsets.UTF_8.newDecoder().onMalformedInput(CodingErrorAction.REPLACE).replaceWith(INVALID_INPUT_CHARACTER).onUnmappableCharacter(CodingErrorAction.REPORT);
        ByteBuffer allocate = ByteBuffer.allocate(str.length());
        int i = 0;
        while (i < str.length()) {
            char charAt = str.charAt(i);
            i++;
            if (charAt == '%') {
                int i2 = 0;
                byte b = 0;
                while (true) {
                    if (i2 >= 2) {
                        break;
                    }
                    if (i >= str.length()) {
                        return sb.toString();
                    }
                    char charAt2 = str.charAt(i);
                    i++;
                    int hexCharToValue = hexCharToValue(charAt2);
                    if (hexCharToValue < 0) {
                        flushDecodingByteAccumulator(sb, onUnmappableCharacter, allocate);
                        sb.append(INVALID_INPUT_CHARACTER);
                        break;
                    }
                    b = (byte) ((b * BaseType.Union) + hexCharToValue);
                    i2++;
                }
                allocate.put(b);
            } else {
                flushDecodingByteAccumulator(sb, onUnmappableCharacter, allocate);
                sb.append(charAt);
            }
        }
        flushDecodingByteAccumulator(sb, onUnmappableCharacter, allocate);
        return sb.toString();
    }

    private static void flushDecodingByteAccumulator(StringBuilder sb, CharsetDecoder charsetDecoder, ByteBuffer byteBuffer) {
        if (byteBuffer.position() == 0) {
            return;
        }
        byteBuffer.flip();
        try {
            try {
                sb.append((CharSequence) charsetDecoder.decode(byteBuffer));
            } catch (CharacterCodingException unused) {
                sb.append(INVALID_INPUT_CHARACTER);
            }
        } finally {
            byteBuffer.flip();
            byteBuffer.limit(byteBuffer.capacity());
        }
    }

    public static Uri getUriFromParts(String str, String str2, String str3) {
        if (str == null) {
            throw new NullPointerException("scheme can't be null");
        }
        if (str2 == null) {
            throw new NullPointerException("ssp can't be null");
        }
        return new OpaqueUri(str, a.c(str2, null), a.c(str3, null));
    }

    public static Uri getUriFromFileCanonicalPath(File file) throws IOException {
        if (file == null) {
            throw new NullPointerException("file can't be null");
        }
        return new HierarchicalUri("file", a.c("", null), a.c(file.getCanonicalPath(), null), null, null);
    }

    public static Uri appendEncodedPathToUri(Uri uri, String str) {
        if (uri.isOpaque()) {
            throw new UnsupportedOperationException("opaque uri can't append path");
        }
        return uri.buildUpon().c(str).a();
    }

    public static Uri parse(String str) {
        if (str == null || str.isEmpty()) {
            throw new NullPointerException("uriStr is null or empty.");
        }
        e eVar = new e();
        String orElse = parseScheme(str).orElse(null);
        eVar.i(orElse);
        eVar.a(parseFragment(str).orElse(null));
        String orElse2 = parseSchemeSpecificPart(str).orElse(null);
        if (orElse != null && orElse2 != null && !orElse2.startsWith("/")) {
            eVar.b(orElse2);
            return eVar.a();
        }
        eVar.d(parseAuthority(orElse2).orElse(null));
        eVar.e(parsePath(str).orElse(null));
        eVar.f(parseQuery(orElse2).orElse(null));
        return eVar.a();
    }

    private static Optional<String> parseScheme(String str) {
        if (TextUtils.isEmpty(str)) {
            return Optional.empty();
        }
        int indexOf = str.indexOf(58);
        if (indexOf > 0) {
            String substring = str.substring(0, indexOf);
            if (!URI_SCHEME_PATTERN.matcher(substring).matches()) {
                throw new IllegalArgumentException("scheme is not illegal.");
            }
            return Optional.ofNullable(substring);
        }
        return Optional.empty();
    }

    private static Optional<String> parseSchemeSpecificPart(String str) {
        if (TextUtils.isEmpty(str)) {
            return Optional.empty();
        }
        int indexOf = str.indexOf(58);
        int indexOf2 = str.indexOf(35);
        if (indexOf2 == -1) {
            return Optional.ofNullable(str.substring(indexOf + 1));
        }
        return Optional.ofNullable(str.substring(indexOf + 1, indexOf2));
    }

    private static Optional<String> parseFragment(String str) {
        if (TextUtils.isEmpty(str)) {
            return Optional.empty();
        }
        int indexOf = str.indexOf(35);
        if (indexOf != -1) {
            return Optional.ofNullable(str.substring(indexOf + 1));
        }
        return Optional.empty();
    }

    private static Optional<String> parseAuthority(String str) {
        if (TextUtils.isEmpty(str)) {
            return Optional.empty();
        }
        int length = str.length();
        if (length > 2 && str.charAt(0) == '/' && str.charAt(1) == '/') {
            int i = 2;
            while (i < length) {
                char charAt = str.charAt(i);
                if (charAt == '/' || charAt == '\\' || charAt == '?' || charAt == '#') {
                    break;
                }
                i++;
            }
            return Optional.ofNullable(str.substring(2, i));
        }
        return Optional.empty();
    }

    private static Optional<String> parsePath(String str) {
        int indexOf = str.indexOf(58);
        int length = str.length();
        int i = indexOf == -1 ? 0 : indexOf + 1;
        int i2 = i + 1;
        if (length > i2 && str.charAt(i) == '/' && str.charAt(i2) == '/') {
            i += 2;
            while (i < length) {
                char charAt = str.charAt(i);
                if (charAt != '?' && charAt != '#') {
                    if (charAt == '/' || charAt == '\\') {
                        break;
                    }
                    i++;
                } else {
                    return Optional.empty();
                }
            }
        }
        int i3 = i;
        while (i3 < length) {
            char charAt2 = str.charAt(i3);
            if (charAt2 == '?' || charAt2 == '#') {
                break;
            }
            i3++;
        }
        if (i3 == i) {
            return Optional.empty();
        }
        return Optional.ofNullable(str.substring(i, i3));
    }

    private static Optional<String> parseQuery(String str) {
        if (TextUtils.isEmpty(str)) {
            return Optional.empty();
        }
        int indexOf = str.indexOf(63);
        if (indexOf == -1) {
            return Optional.empty();
        }
        return Optional.ofNullable(str.substring(indexOf + 1));
    }

    public Optional<String> getLastPath() {
        List<String> decodedPathList = getDecodedPathList();
        if (decodedPathList.isEmpty()) {
            return Optional.empty();
        }
        return Optional.ofNullable(decodedPathList.get(decodedPathList.size() - 1));
    }

    public List<String> getQueryParamsByKey(String str) {
        return getDecodedQueryParams().getOrDefault(str, new ArrayList(0));
    }

    public String getFirstQueryParamByKey(String str) {
        List<String> queryParamsByKey = getQueryParamsByKey(str);
        return queryParamsByKey.isEmpty() ? "" : queryParamsByKey.get(0);
    }

    public Set<String> getQueryParamNames() {
        return getDecodedQueryParams().keySet();
    }

    public boolean getBooleanQueryParam(String str, boolean z) {
        String firstQueryParamByKey = getFirstQueryParamByKey(str);
        if (firstQueryParamByKey == null) {
            return z;
        }
        String lowerCase = firstQueryParamByKey.toLowerCase(Locale.ROOT);
        return ("false".equals(lowerCase) || "0".equals(lowerCase)) ? false : true;
    }

    public Uri getLowerCaseScheme() {
        String scheme = getScheme();
        if (scheme == null) {
            return this;
        }
        String lowerCase = scheme.toLowerCase(Locale.ROOT);
        return scheme.equals(lowerCase) ? this : buildUpon().i(lowerCase).a();
    }

    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof Uri)) {
            return false;
        }
        return ((Uri) obj).toString().equals(toString());
    }

    public int hashCode() {
        return Objects.hashCode(toString());
    }

    @Override // java.lang.Comparable
    public int compareTo(Uri uri) {
        return toString().compareTo(uri.toString());
    }
}
