package com.google.json;

import com.huawei.openalliance.ad.constant.VideoPlayFlag;
import com.huawei.operation.OpAnalyticsConstants;
import com.huawei.operation.utils.Constants;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* loaded from: classes.dex */
public final class EvalMinifier {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final int BOILERPLATE_COST = 22;
    private static final String ENVELOPE_P1 = "(function(";
    private static final String ENVELOPE_P2 = "){return";
    private static final String ENVELOPE_P3 = "}(";
    private static final String ENVELOPE_P4 = "))";
    private static final int MARGINAL_VAR_COST = 2;
    private static final String[][] RESERVED_KEYWORDS = {new String[0], new String[0], new String[]{"as", "do", "if", "in", "of"}, new String[]{"for", "get", "let", "new", "set", "try", "var"}, new String[]{"case", "else", "enum", "eval", "from", Constants.NULL, "this", "true", "void", "with"}, new String[]{"async", "await", "break", "catch", "class", "const", "false", "super", "throw", "while", "yield"}, new String[]{"delete", "export", "import", "public", "return", "switch", "static", "target", "typeof"}, new String[]{"default", "extends", "finally", "package", "private"}, new String[]{"continue", "debugger", "function"}, new String[]{"arguments", OpAnalyticsConstants.REQUEST_INTERFACE, "protected"}, new String[]{"implements", "instanceof"}};
    private static final int SAVINGS_THRESHOLD = 32;

    private static boolean isLetterOrNumberChar(char c) {
        if ('0' <= c && c <= '9') {
            return true;
        }
        char c2 = (char) (c | ' ');
        return ('a' <= c2 && c2 <= 'z') || c == '_' || c == '$' || c == '-' || c == '.';
    }

    static int nextIdentChar(char c, boolean z) {
        if (c == 'z') {
            return 65;
        }
        if (c == 'Z') {
            return 95;
        }
        if (c == '_') {
            return 36;
        }
        if (c == '$') {
            return z ? 48 : -1;
        }
        if (c == '9') {
            return -1;
        }
        return (char) (c + 1);
    }

    public static String minify(String str) {
        JsonSanitizer jsonSanitizer = new JsonSanitizer(str);
        jsonSanitizer.sanitize();
        return minify(jsonSanitizer.toCharSequence()).toString();
    }

    public static String minify(String str, int i) {
        JsonSanitizer jsonSanitizer = new JsonSanitizer(str, i);
        jsonSanitizer.sanitize();
        return minify(jsonSanitizer.toCharSequence()).toString();
    }

    private static CharSequence minify(CharSequence charSequence) {
        char c;
        int i;
        Token token;
        int i2;
        int i3;
        HashMap hashMap = new HashMap();
        int length = charSequence.length();
        int i4 = 0;
        while (true) {
            c = '\n';
            if (i4 >= length) {
                break;
            }
            char charAt = charSequence.charAt(i4);
            if (charAt == '\"') {
                i3 = i4 + 1;
                while (true) {
                    if (i3 >= length) {
                        break;
                    }
                    char charAt2 = charSequence.charAt(i3);
                    if (charAt2 != '\\') {
                        if (charAt2 == '\"') {
                            i3++;
                            break;
                        }
                    } else {
                        i3++;
                    }
                    i3++;
                }
            } else if (isLetterOrNumberChar(charAt)) {
                i3 = i4 + 1;
                while (i3 < length && isLetterOrNumberChar(charSequence.charAt(i3))) {
                    i3++;
                }
            } else {
                i4++;
            }
            int i5 = i3;
            while (i5 < length) {
                char charAt3 = charSequence.charAt(i5);
                if (charAt3 != '\t' && charAt3 != '\n' && charAt3 != '\r' && charAt3 != ' ') {
                    break;
                }
                i5++;
            }
            if (i5 == length || (':' != charSequence.charAt(i5) && i3 - i4 >= 4)) {
                Token token2 = new Token(i4, i3, charSequence);
                Token token3 = (Token) hashMap.put(token2, token2);
                if (token3 != null) {
                    token2.prev = token3;
                }
            }
            i4 = i5 - 1;
            i4++;
        }
        ArrayList arrayList = new ArrayList();
        Iterator it = hashMap.values().iterator();
        int i6 = 0;
        while (it.hasNext()) {
            Token token4 = (Token) it.next();
            if (token4.prev == null) {
                it.remove();
            } else {
                int i7 = 0;
                for (Token token5 = token4; token5 != null; token5 = token5.prev) {
                    i7++;
                }
                int i8 = ((i7 - 1) * (token4.end - token4.start)) - MARGINAL_VAR_COST;
                if (i8 > 0) {
                    i6 += i8;
                    while (token4 != null) {
                        arrayList.add(token4);
                        token4 = token4.prev;
                    }
                }
            }
        }
        if (i6 <= BOILERPLATE_COST + 32) {
            return charSequence;
        }
        Collections.sort(arrayList);
        int size = arrayList.size();
        StringBuilder sb = new StringBuilder(length);
        sb.append(ENVELOPE_P1);
        NameGenerator nameGenerator = new NameGenerator();
        boolean z = true;
        for (Token token6 : hashMap.values()) {
            String next = nameGenerator.next();
            for (Token token7 = token6; token7 != null; token7 = token7.prev) {
                token7.name = next;
            }
            if (z) {
                z = false;
            } else {
                sb.append(',');
            }
            sb.append(next);
        }
        sb.append(ENVELOPE_P2);
        int length2 = sb.length();
        int i9 = 0;
        int i10 = 0;
        while (true) {
            if (i9 < size) {
                i = i9 + 1;
                token = (Token) arrayList.get(i9);
            } else {
                i = i9;
                token = null;
            }
            int i11 = token != null ? token.start : length;
            int i12 = i10;
            boolean z2 = false;
            while (i10 < i11) {
                char charAt4 = charSequence.charAt(i10);
                if (!z2) {
                    if (charAt4 == '\t' || charAt4 == c || charAt4 == '\r' || charAt4 == ' ') {
                        int i13 = i12;
                        if (i13 != i10) {
                            sb.append(charSequence, i13, i10);
                        }
                        i12 = i10 + 1;
                    } else if (charAt4 == '\"') {
                        i2 = 1;
                        z2 = true;
                    }
                    i2 = 1;
                } else if (charAt4 == '\"') {
                    i2 = 1;
                    z2 = false;
                } else {
                    if (charAt4 == '\\') {
                        i10++;
                    }
                    i2 = 1;
                }
                i10 += i2;
                c = '\n';
            }
            int i14 = i12;
            if (i14 != i11) {
                sb.append(charSequence, i14, i11);
            }
            if (token == null) {
                break;
            }
            sb.append(token.name);
            i10 = token.end;
            i9 = i;
            c = '\n';
        }
        char charAt5 = sb.charAt(length2);
        if (charAt5 != '{' && charAt5 != '[' && charAt5 != '\"') {
            sb.insert(length2, ' ');
        }
        sb.append(ENVELOPE_P3);
        boolean z3 = true;
        for (Token token8 : hashMap.values()) {
            if (z3) {
                z3 = false;
            } else {
                sb.append(',');
            }
            sb.append(token8.seq, token8.start, token8.end);
        }
        sb.append(ENVELOPE_P4);
        return sb;
    }

    static final class Token implements Comparable<Token> {
        private final int end;
        private final int hashCode;

        @Nullable
        String name;

        @Nullable
        Token prev;

        @Nonnull
        private final CharSequence seq;
        private final int start;

        Token(int i, int i2, CharSequence charSequence) {
            this.start = i;
            this.end = i2;
            this.seq = charSequence;
            int i3 = 0;
            while (i < i2) {
                i3 = (i3 * 31) + charSequence.charAt(i);
                i++;
            }
            this.hashCode = i3;
        }

        public boolean equals(@Nullable Object obj) {
            if (!(obj instanceof Token)) {
                return false;
            }
            Token token = (Token) obj;
            if (this.hashCode != token.hashCode) {
                return false;
            }
            return EvalMinifier.regionMatches(this.seq, this.start, this.end, token.seq, token.start, token.end);
        }

        public int hashCode() {
            return this.hashCode;
        }

        @Override // java.lang.Comparable
        public int compareTo(Token token) {
            return this.start - token.start;
        }
    }

    static boolean regionMatches(CharSequence charSequence, int i, int i2, CharSequence charSequence2, int i3, int i4) {
        if (i4 - i3 != i2 - i) {
            return false;
        }
        while (i < i2) {
            if (charSequence.charAt(i) != charSequence2.charAt(i3)) {
                return false;
            }
            i++;
            i3++;
        }
        return true;
    }

    /* loaded from: classes8.dex */
    static final class NameGenerator {
        private final StringBuilder sb = new StringBuilder(VideoPlayFlag.PLAY_IN_ALL);

        NameGenerator() {
        }

        public String next() {
            String sb;
            int length;
            do {
                sb = this.sb.toString();
                int length2 = this.sb.length();
                while (true) {
                    length2--;
                    if (length2 < 0) {
                        break;
                    }
                    int nextIdentChar = EvalMinifier.nextIdentChar(this.sb.charAt(length2), length2 != 0);
                    if (nextIdentChar < 0) {
                        this.sb.setCharAt(length2, 'a');
                        if (length2 == 0) {
                            this.sb.append('a');
                        }
                    } else {
                        this.sb.setCharAt(length2, (char) nextIdentChar);
                        break;
                    }
                }
                length = sb.length();
                if (length >= EvalMinifier.RESERVED_KEYWORDS.length) {
                    break;
                }
            } while (Arrays.binarySearch(EvalMinifier.RESERVED_KEYWORDS[length], sb) >= 0);
            return sb;
        }
    }
}
