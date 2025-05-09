package com.google.android.gms.common.server.response;

import android.util.Log;
import com.google.android.gms.common.server.response.FastJsonResponse;
import com.huawei.health.tradeservice.pay.PayActivity;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Map;
import java.util.Stack;
import okio.internal.Buffer;

/* loaded from: classes8.dex */
public class FastParser<T extends FastJsonResponse> {
    private static final char[] zaqg = {'u', 'l', 'l'};
    private static final char[] zaqh = {'r', 'u', 'e'};
    private static final char[] zaqi = {'r', 'u', 'e', '\"'};
    private static final char[] zaqj = {'a', 'l', 's', 'e'};
    private static final char[] zaqk = {'a', 'l', 's', 'e', '\"'};
    private static final char[] zaql = {'\n'};
    private static final zaa<Integer> zaqn = new com.google.android.gms.common.server.response.zaa();
    private static final zaa<Long> zaqo = new zab();
    private static final zaa<Float> zaqp = new zac();
    private static final zaa<Double> zaqq = new zad();
    private static final zaa<Boolean> zaqr = new zae();
    private static final zaa<String> zaqs = new zaf();
    private static final zaa<BigInteger> zaqt = new zag();
    private static final zaa<BigDecimal> zaqu = new zah();
    private final char[] zaqb = new char[1];
    private final char[] zaqc = new char[32];
    private final char[] zaqd = new char[1024];
    private final StringBuilder zaqe = new StringBuilder(32);
    private final StringBuilder zaqf = new StringBuilder(1024);
    private final Stack<Integer> zaqm = new Stack<>();

    interface zaa<O> {
        O zah(FastParser fastParser, BufferedReader bufferedReader) throws ParseException, IOException;
    }

    public static class ParseException extends Exception {
        public ParseException(String str) {
            super(str);
        }

        public ParseException(String str, Throwable th) {
            super(str, th);
        }

        public ParseException(Throwable th) {
            super(th);
        }
    }

    public void parse(InputStream inputStream, T t) throws ParseException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream), 1024);
        try {
            try {
                this.zaqm.push(0);
                char zaj = zaj(bufferedReader);
                if (zaj == 0) {
                    throw new ParseException("No data to parse");
                }
                if (zaj == '[') {
                    this.zaqm.push(5);
                    Map<String, FastJsonResponse.Field<?, ?>> fieldMappings = t.getFieldMappings();
                    if (fieldMappings.size() != 1) {
                        throw new ParseException("Object array response class must have a single Field");
                    }
                    FastJsonResponse.Field<?, ?> value = fieldMappings.entrySet().iterator().next().getValue();
                    t.addConcreteTypeArrayInternal(value, value.zapv, zaa(bufferedReader, value));
                } else if (zaj == '{') {
                    this.zaqm.push(1);
                    zaa(bufferedReader, t);
                } else {
                    StringBuilder sb = new StringBuilder(19);
                    sb.append("Unexpected token: ");
                    sb.append(zaj);
                    throw new ParseException(sb.toString());
                }
                zak(0);
            } catch (IOException e) {
                throw new ParseException(e);
            }
        } finally {
            try {
                bufferedReader.close();
            } catch (IOException unused) {
                Log.w("FastParser", "Failed to close reader while parsing.");
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:20:0x0298 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:24:0x027a A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private final boolean zaa(java.io.BufferedReader r17, com.google.android.gms.common.server.response.FastJsonResponse r18) throws com.google.android.gms.common.server.response.FastParser.ParseException, java.io.IOException {
        /*
            Method dump skipped, instructions count: 702
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.common.server.response.FastParser.zaa(java.io.BufferedReader, com.google.android.gms.common.server.response.FastJsonResponse):boolean");
    }

    private final String zaa(BufferedReader bufferedReader) throws ParseException, IOException {
        this.zaqm.push(2);
        char zaj = zaj(bufferedReader);
        if (zaj == '\"') {
            this.zaqm.push(3);
            String zab = zab(bufferedReader, this.zaqc, this.zaqe, null);
            zak(3);
            if (zaj(bufferedReader) == ':') {
                return zab;
            }
            throw new ParseException("Expected key/value separator");
        }
        if (zaj == ']') {
            zak(2);
            zak(1);
            zak(5);
            return null;
        }
        if (zaj == '}') {
            zak(2);
            return null;
        }
        StringBuilder sb = new StringBuilder(19);
        sb.append("Unexpected token: ");
        sb.append(zaj);
        throw new ParseException(sb.toString());
    }

    private final String zab(BufferedReader bufferedReader) throws ParseException, IOException {
        bufferedReader.mark(1024);
        char zaj = zaj(bufferedReader);
        if (zaj == '\"') {
            if (bufferedReader.read(this.zaqb) == -1) {
                throw new ParseException("Unexpected EOF while parsing string");
            }
            char c = this.zaqb[0];
            boolean z = false;
            do {
                if (c != '\"' || z) {
                    z = c == '\\' ? !z : false;
                    if (bufferedReader.read(this.zaqb) == -1) {
                        throw new ParseException("Unexpected EOF while parsing string");
                    }
                    c = this.zaqb[0];
                }
            } while (!Character.isISOControl(c));
            throw new ParseException("Unexpected control character while reading string");
        }
        if (zaj == ',') {
            throw new ParseException("Missing value");
        }
        int i = 1;
        if (zaj == '[') {
            this.zaqm.push(5);
            bufferedReader.mark(32);
            if (zaj(bufferedReader) == ']') {
                zak(5);
            } else {
                bufferedReader.reset();
                boolean z2 = false;
                boolean z3 = false;
                while (i > 0) {
                    char zaj2 = zaj(bufferedReader);
                    if (zaj2 == 0) {
                        throw new ParseException("Unexpected EOF while parsing array");
                    }
                    if (Character.isISOControl(zaj2)) {
                        throw new ParseException("Unexpected control character while reading array");
                    }
                    if (zaj2 == '\"' && !z3) {
                        z2 = !z2;
                    }
                    if (zaj2 == '[' && !z2) {
                        i++;
                    }
                    if (zaj2 == ']' && !z2) {
                        i--;
                    }
                    z3 = (zaj2 == '\\' && z2) ? !z3 : false;
                }
                zak(5);
            }
        } else if (zaj == '{') {
            this.zaqm.push(1);
            bufferedReader.mark(32);
            char zaj3 = zaj(bufferedReader);
            if (zaj3 == '}') {
                zak(1);
            } else if (zaj3 == '\"') {
                bufferedReader.reset();
                zaa(bufferedReader);
                while (zab(bufferedReader) != null) {
                }
                zak(1);
            } else {
                StringBuilder sb = new StringBuilder(18);
                sb.append("Unexpected token ");
                sb.append(zaj3);
                throw new ParseException(sb.toString());
            }
        } else {
            bufferedReader.reset();
            zaa(bufferedReader, this.zaqd);
        }
        char zaj4 = zaj(bufferedReader);
        if (zaj4 == ',') {
            zak(2);
            return zaa(bufferedReader);
        }
        if (zaj4 == '}') {
            zak(2);
            return null;
        }
        StringBuilder sb2 = new StringBuilder(18);
        sb2.append("Unexpected token ");
        sb2.append(zaj4);
        throw new ParseException(sb2.toString());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final String zac(BufferedReader bufferedReader) throws ParseException, IOException {
        return zaa(bufferedReader, this.zaqc, this.zaqe, null);
    }

    private final <O> ArrayList<O> zaa(BufferedReader bufferedReader, zaa<O> zaaVar) throws ParseException, IOException {
        char zaj = zaj(bufferedReader);
        if (zaj == 'n') {
            zab(bufferedReader, zaqg);
            return null;
        }
        if (zaj != '[') {
            throw new ParseException("Expected start of array");
        }
        this.zaqm.push(5);
        ArrayList<O> arrayList = new ArrayList<>();
        while (true) {
            bufferedReader.mark(1024);
            char zaj2 = zaj(bufferedReader);
            if (zaj2 == 0) {
                throw new ParseException("Unexpected EOF");
            }
            if (zaj2 != ',') {
                if (zaj2 == ']') {
                    zak(5);
                    return arrayList;
                }
                bufferedReader.reset();
                arrayList.add(zaaVar.zah(this, bufferedReader));
            }
        }
    }

    private final String zaa(BufferedReader bufferedReader, char[] cArr, StringBuilder sb, char[] cArr2) throws ParseException, IOException {
        char zaj = zaj(bufferedReader);
        if (zaj == '\"') {
            return zab(bufferedReader, cArr, sb, cArr2);
        }
        if (zaj == 'n') {
            zab(bufferedReader, zaqg);
            return null;
        }
        throw new ParseException("Expected string");
    }

    /* JADX WARN: Code restructure failed: missing block: B:18:0x0031, code lost:
    
        throw new com.google.android.gms.common.server.response.FastParser.ParseException("Unexpected control character while reading string");
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static java.lang.String zab(java.io.BufferedReader r8, char[] r9, java.lang.StringBuilder r10, char[] r11) throws com.google.android.gms.common.server.response.FastParser.ParseException, java.io.IOException {
        /*
            r0 = 0
            r10.setLength(r0)
            int r1 = r9.length
            r8.mark(r1)
            r1 = r0
            r2 = r1
        La:
            int r3 = r8.read(r9)
            r4 = -1
            if (r3 == r4) goto L69
            r4 = r0
        L12:
            if (r4 >= r3) goto L61
            char r5 = r9[r4]
            boolean r6 = java.lang.Character.isISOControl(r5)
            if (r6 == 0) goto L32
            if (r11 == 0) goto L2a
            r6 = r0
        L1f:
            int r7 = r11.length
            if (r6 >= r7) goto L2a
            char r7 = r11[r6]
            if (r7 != r5) goto L27
            goto L32
        L27:
            int r6 = r6 + 1
            goto L1f
        L2a:
            com.google.android.gms.common.server.response.FastParser$ParseException r8 = new com.google.android.gms.common.server.response.FastParser$ParseException
            java.lang.String r9 = "Unexpected control character while reading string"
            r8.<init>(r9)
            throw r8
        L32:
            r6 = 34
            r7 = 1
            if (r5 != r6) goto L54
            if (r2 != 0) goto L54
            r10.append(r9, r0, r4)
            r8.reset()
            int r4 = r4 + r7
            long r2 = (long) r4
            r8.skip(r2)
            if (r1 == 0) goto L4f
            java.lang.String r8 = r10.toString()
            java.lang.String r8 = com.google.android.gms.common.util.JsonUtils.unescapeString(r8)
            return r8
        L4f:
            java.lang.String r8 = r10.toString()
            return r8
        L54:
            r6 = 92
            if (r5 != r6) goto L5d
            r1 = r2 ^ 1
            r2 = r1
            r1 = r7
            goto L5e
        L5d:
            r2 = r0
        L5e:
            int r4 = r4 + 1
            goto L12
        L61:
            r10.append(r9, r0, r3)
            int r3 = r9.length
            r8.mark(r3)
            goto La
        L69:
            com.google.android.gms.common.server.response.FastParser$ParseException r8 = new com.google.android.gms.common.server.response.FastParser$ParseException
            java.lang.String r9 = "Unexpected EOF while parsing string"
            r8.<init>(r9)
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.common.server.response.FastParser.zab(java.io.BufferedReader, char[], java.lang.StringBuilder, char[]):java.lang.String");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final int zad(BufferedReader bufferedReader) throws ParseException, IOException {
        int i;
        int i2;
        int i3;
        int zaa2 = zaa(bufferedReader, this.zaqd);
        int i4 = 0;
        if (zaa2 == 0) {
            return 0;
        }
        char[] cArr = this.zaqd;
        if (zaa2 > 0) {
            if (cArr[0] == '-') {
                i = Integer.MIN_VALUE;
                i2 = 1;
            } else {
                i = -2147483647;
                i2 = 0;
            }
            if (i2 < zaa2) {
                int digit = Character.digit(cArr[i2], 10);
                if (digit < 0) {
                    throw new ParseException("Unexpected non-digit character");
                }
                i4 = -digit;
                i3 = i2 + 1;
            } else {
                i3 = i2;
            }
            while (i3 < zaa2) {
                int digit2 = Character.digit(cArr[i3], 10);
                if (digit2 < 0) {
                    throw new ParseException("Unexpected non-digit character");
                }
                if (i4 < -214748364) {
                    throw new ParseException("Number too large");
                }
                int i5 = i4 * 10;
                if (i5 < i + digit2) {
                    throw new ParseException("Number too large");
                }
                i4 = i5 - digit2;
                i3++;
            }
            if (i2 == 0) {
                return -i4;
            }
            if (i3 > 1) {
                return i4;
            }
            throw new ParseException("No digits to parse");
        }
        throw new ParseException("No number to parse");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final long zae(BufferedReader bufferedReader) throws ParseException, IOException {
        long j;
        int i;
        int zaa2 = zaa(bufferedReader, this.zaqd);
        long j2 = 0;
        if (zaa2 == 0) {
            return 0L;
        }
        char[] cArr = this.zaqd;
        if (zaa2 > 0) {
            int i2 = 0;
            if (cArr[0] == '-') {
                j = Long.MIN_VALUE;
                i2 = 1;
            } else {
                j = -9223372036854775807L;
            }
            if (i2 < zaa2) {
                int digit = Character.digit(cArr[i2], 10);
                if (digit < 0) {
                    throw new ParseException("Unexpected non-digit character");
                }
                j2 = -digit;
                i = i2 + 1;
            } else {
                i = i2;
            }
            while (i < zaa2) {
                int digit2 = Character.digit(cArr[i], 10);
                if (digit2 < 0) {
                    throw new ParseException("Unexpected non-digit character");
                }
                if (j2 < Buffer.OVERFLOW_ZONE) {
                    throw new ParseException("Number too large");
                }
                long j3 = j2 * 10;
                long j4 = digit2;
                if (j3 < j + j4) {
                    throw new ParseException("Number too large");
                }
                j2 = j3 - j4;
                i++;
            }
            if (i2 == 0) {
                return -j2;
            }
            if (i > 1) {
                return j2;
            }
            throw new ParseException("No digits to parse");
        }
        throw new ParseException("No number to parse");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final BigInteger zaf(BufferedReader bufferedReader) throws ParseException, IOException {
        int zaa2 = zaa(bufferedReader, this.zaqd);
        if (zaa2 == 0) {
            return null;
        }
        return new BigInteger(new String(this.zaqd, 0, zaa2));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean zaa(BufferedReader bufferedReader, boolean z) throws ParseException, IOException {
        while (true) {
            char zaj = zaj(bufferedReader);
            if (zaj != '\"') {
                if (zaj == 'f') {
                    zab(bufferedReader, z ? zaqk : zaqj);
                    return false;
                }
                if (zaj == 'n') {
                    zab(bufferedReader, zaqg);
                    return false;
                }
                if (zaj == 't') {
                    zab(bufferedReader, z ? zaqi : zaqh);
                    return true;
                }
                StringBuilder sb = new StringBuilder(19);
                sb.append("Unexpected token: ");
                sb.append(zaj);
                throw new ParseException(sb.toString());
            }
            if (z) {
                throw new ParseException("No boolean value found in string");
            }
            z = true;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final float zag(BufferedReader bufferedReader) throws ParseException, IOException {
        int zaa2 = zaa(bufferedReader, this.zaqd);
        if (zaa2 == 0) {
            return 0.0f;
        }
        return Float.parseFloat(new String(this.zaqd, 0, zaa2));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final double zah(BufferedReader bufferedReader) throws ParseException, IOException {
        int zaa2 = zaa(bufferedReader, this.zaqd);
        if (zaa2 == 0) {
            return 0.0d;
        }
        return Double.parseDouble(new String(this.zaqd, 0, zaa2));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final BigDecimal zai(BufferedReader bufferedReader) throws ParseException, IOException {
        int zaa2 = zaa(bufferedReader, this.zaqd);
        if (zaa2 == 0) {
            return null;
        }
        return new BigDecimal(new String(this.zaqd, 0, zaa2));
    }

    private final <T extends FastJsonResponse> ArrayList<T> zaa(BufferedReader bufferedReader, FastJsonResponse.Field<?, ?> field) throws ParseException, IOException {
        PayActivity.AnonymousClass6 anonymousClass6 = (ArrayList<T>) new ArrayList();
        char zaj = zaj(bufferedReader);
        if (zaj == ']') {
            zak(5);
            return anonymousClass6;
        }
        if (zaj == 'n') {
            zab(bufferedReader, zaqg);
            zak(5);
            return null;
        }
        if (zaj == '{') {
            this.zaqm.push(1);
            while (true) {
                try {
                    FastJsonResponse zacp = field.zacp();
                    if (!zaa(bufferedReader, zacp)) {
                        return anonymousClass6;
                    }
                    anonymousClass6.add(zacp);
                    char zaj2 = zaj(bufferedReader);
                    if (zaj2 != ',') {
                        if (zaj2 == ']') {
                            zak(5);
                            return anonymousClass6;
                        }
                        StringBuilder sb = new StringBuilder(19);
                        sb.append("Unexpected token: ");
                        sb.append(zaj2);
                        throw new ParseException(sb.toString());
                    }
                    if (zaj(bufferedReader) != '{') {
                        throw new ParseException("Expected start of next object in array");
                    }
                    this.zaqm.push(1);
                } catch (IllegalAccessException e) {
                    throw new ParseException("Error instantiating inner object", e);
                } catch (InstantiationException e2) {
                    throw new ParseException("Error instantiating inner object", e2);
                }
            }
        } else {
            StringBuilder sb2 = new StringBuilder(19);
            sb2.append("Unexpected token: ");
            sb2.append(zaj);
            throw new ParseException(sb2.toString());
        }
    }

    private final char zaj(BufferedReader bufferedReader) throws ParseException, IOException {
        if (bufferedReader.read(this.zaqb) == -1) {
            return (char) 0;
        }
        while (Character.isWhitespace(this.zaqb[0])) {
            if (bufferedReader.read(this.zaqb) == -1) {
                return (char) 0;
            }
        }
        return this.zaqb[0];
    }

    private final int zaa(BufferedReader bufferedReader, char[] cArr) throws ParseException, IOException {
        int i;
        char zaj = zaj(bufferedReader);
        if (zaj == 0) {
            throw new ParseException("Unexpected EOF");
        }
        if (zaj == ',') {
            throw new ParseException("Missing value");
        }
        if (zaj == 'n') {
            zab(bufferedReader, zaqg);
            return 0;
        }
        bufferedReader.mark(1024);
        if (zaj == '\"') {
            i = 0;
            boolean z = false;
            while (i < cArr.length && bufferedReader.read(cArr, i, 1) != -1) {
                char c = cArr[i];
                if (Character.isISOControl(c)) {
                    throw new ParseException("Unexpected control character while reading string");
                }
                if (c == '\"' && !z) {
                    bufferedReader.reset();
                    bufferedReader.skip(i + 1);
                    return i;
                }
                z = c == '\\' ? !z : false;
                i++;
            }
        } else {
            cArr[0] = zaj;
            i = 1;
            while (i < cArr.length && bufferedReader.read(cArr, i, 1) != -1) {
                char c2 = cArr[i];
                if (c2 == '}' || c2 == ',' || Character.isWhitespace(c2) || cArr[i] == ']') {
                    bufferedReader.reset();
                    bufferedReader.skip(i - 1);
                    cArr[i] = 0;
                    return i;
                }
                i++;
            }
        }
        if (i == cArr.length) {
            throw new ParseException("Absurdly long value");
        }
        throw new ParseException("Unexpected EOF");
    }

    private final void zab(BufferedReader bufferedReader, char[] cArr) throws ParseException, IOException {
        int i = 0;
        while (i < cArr.length) {
            int read = bufferedReader.read(this.zaqc, 0, cArr.length - i);
            if (read == -1) {
                throw new ParseException("Unexpected EOF");
            }
            for (int i2 = 0; i2 < read; i2++) {
                if (cArr[i2 + i] != this.zaqc[i2]) {
                    throw new ParseException("Unexpected character");
                }
            }
            i += read;
        }
    }

    private final void zak(int i) throws ParseException {
        if (this.zaqm.isEmpty()) {
            StringBuilder sb = new StringBuilder(46);
            sb.append("Expected state ");
            sb.append(i);
            sb.append(" but had empty stack");
            throw new ParseException(sb.toString());
        }
        int intValue = this.zaqm.pop().intValue();
        if (intValue == i) {
            return;
        }
        StringBuilder sb2 = new StringBuilder(46);
        sb2.append("Expected state ");
        sb2.append(i);
        sb2.append(" but had ");
        sb2.append(intValue);
        throw new ParseException(sb2.toString());
    }
}
