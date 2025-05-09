package com.google.json;

import com.huawei.operation.utils.Constants;
import java.math.BigInteger;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;

/* loaded from: classes.dex */
public final class JsonSanitizer {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    public static final int DEFAULT_NESTING_DEPTH = 64;
    private static final int[] DIGITS_BY_BASE_THAT_FIT_IN_63B;
    private static final char[] HEX_DIGITS;
    public static final int MAXIMUM_NESTING_DEPTH = 4096;
    private static final boolean SUPER_VERBOSE_AND_SLOW_LOGGING = false;
    private static final UnbracketedComma UNBRACKETED_COMMA;
    private int bracketDepth;
    private int cleaned;
    private boolean[] isMap;
    private final String jsonish;
    private final int maximumNestingDepth;
    private StringBuilder sanitizedJson;

    enum State {
        START_ARRAY,
        BEFORE_ELEMENT,
        AFTER_ELEMENT,
        START_MAP,
        BEFORE_KEY,
        AFTER_KEY,
        BEFORE_VALUE,
        AFTER_VALUE
    }

    private static int hexVal(char c) {
        int i = c | ' ';
        return i - (i <= 57 ? 48 : 87);
    }

    private static boolean isHex(char c) {
        if ('0' <= c && c <= '9') {
            return true;
        }
        int i = c | ' ';
        return 97 <= i && i <= 102;
    }

    private static boolean isOct(char c) {
        return '0' <= c && c <= '7';
    }

    public static String sanitize(String str) {
        return sanitize(str, 64);
    }

    public static String sanitize(String str, int i) {
        JsonSanitizer jsonSanitizer = new JsonSanitizer(str, i);
        jsonSanitizer.sanitize();
        return jsonSanitizer.toString();
    }

    JsonSanitizer(String str) {
        this(str, 64);
    }

    JsonSanitizer(String str, int i) {
        this.maximumNestingDepth = Math.min(Math.max(1, i), 4096);
        this.jsonish = str == null ? Constants.NULL : str;
    }

    int getMaximumNestingDepth() {
        return this.maximumNestingDepth;
    }

    /* JADX WARN: Removed duplicated region for block: B:80:0x00e5 A[Catch: UnbracketedComma -> 0x0236, TryCatch #0 {UnbracketedComma -> 0x0236, blocks: (B:5:0x001f, B:26:0x0051, B:30:0x006c, B:35:0x0094, B:53:0x009f, B:57:0x00a6, B:62:0x00ba, B:68:0x00c9, B:70:0x00d0, B:74:0x00d5, B:76:0x00df, B:78:0x00e1, B:80:0x00e5, B:82:0x00ec, B:83:0x0107, B:84:0x00f3, B:86:0x00f9, B:88:0x00ff, B:101:0x0056, B:103:0x021d, B:153:0x010b, B:182:0x010f, B:155:0x011a, B:164:0x0139, B:168:0x014b, B:169:0x0150, B:171:0x0154, B:174:0x015d, B:176:0x0160, B:178:0x012e, B:179:0x0132, B:180:0x0136, B:183:0x0166, B:185:0x016f, B:188:0x017a, B:190:0x0186, B:191:0x018a, B:193:0x018e, B:195:0x0193, B:196:0x0197, B:198:0x019e, B:200:0x01a3, B:204:0x01b0, B:206:0x01b4, B:214:0x01cb, B:217:0x01e7, B:223:0x01ce, B:225:0x01d2, B:226:0x01d4, B:228:0x01dd, B:233:0x01ea, B:235:0x01f0, B:237:0x01f5, B:238:0x01fd, B:241:0x0201, B:242:0x0204, B:243:0x0207, B:245:0x020d, B:246:0x0213, B:248:0x0219, B:249:0x021b), top: B:4:0x001f }] */
    /* JADX WARN: Removed duplicated region for block: B:85:0x00f7  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    void sanitize() {
        /*
            Method dump skipped, instructions count: 722
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.json.JsonSanitizer.sanitize():void");
    }

    /* renamed from: com.google.json.JsonSanitizer$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$google$json$JsonSanitizer$State;

        static {
            int[] iArr = new int[State.values().length];
            $SwitchMap$com$google$json$JsonSanitizer$State = iArr;
            try {
                iArr[State.BEFORE_VALUE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$google$json$JsonSanitizer$State[State.BEFORE_ELEMENT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$google$json$JsonSanitizer$State[State.BEFORE_KEY.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$google$json$JsonSanitizer$State[State.AFTER_KEY.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$google$json$JsonSanitizer$State[State.START_MAP.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$google$json$JsonSanitizer$State[State.START_ARRAY.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$google$json$JsonSanitizer$State[State.AFTER_ELEMENT.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$com$google$json$JsonSanitizer$State[State.AFTER_VALUE.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:35:0x0068, code lost:
    
        if (java.lang.Character.isLowSurrogate(r17.jsonish.charAt(r6)) != false) goto L39;
     */
    /* JADX WARN: Code restructure failed: missing block: B:36:0x006a, code lost:
    
        r4 = r6;
     */
    /* JADX WARN: Code restructure failed: missing block: B:92:0x015c, code lost:
    
        if (isHexAt(r6) != false) goto L39;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void sanitizeString(int r18, int r19) {
        /*
            Method dump skipped, instructions count: 634
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.json.JsonSanitizer.sanitizeString(int, int):void");
    }

    private State requireValueState(int i, State state, boolean z) throws UnbracketedComma {
        switch (AnonymousClass1.$SwitchMap$com$google$json$JsonSanitizer$State[state.ordinal()]) {
            case 1:
                return State.AFTER_VALUE;
            case 2:
            case 6:
                return State.AFTER_ELEMENT;
            case 3:
            case 5:
                if (z) {
                    return State.AFTER_KEY;
                }
                insert(i, "\"\":");
                return State.AFTER_VALUE;
            case 4:
                insert(i, ':');
                return State.AFTER_VALUE;
            case 7:
                if (this.bracketDepth == 0) {
                    throw UNBRACKETED_COMMA;
                }
                insert(i, ',');
                return State.AFTER_ELEMENT;
            case 8:
                if (z) {
                    insert(i, ',');
                    return State.AFTER_KEY;
                }
                insert(i, ",\"\":");
                return State.AFTER_VALUE;
            default:
                throw new AssertionError();
        }
    }

    private void insert(int i, char c) {
        replace(i, i, c);
    }

    private void insert(int i, String str) {
        replace(i, i, str);
    }

    private void elide(int i, int i2) {
        if (this.sanitizedJson == null) {
            this.sanitizedJson = new StringBuilder(this.jsonish.length() + 16);
        }
        this.sanitizedJson.append((CharSequence) this.jsonish, this.cleaned, i);
        this.cleaned = i2;
    }

    private void replace(int i, int i2, char c) {
        elide(i, i2);
        this.sanitizedJson.append(c);
    }

    private void replace(int i, int i2, String str) {
        elide(i, i2);
        this.sanitizedJson.append(str);
    }

    private static int endOfQuotedString(String str, int i) {
        int i2;
        char charAt = str.charAt(i);
        int i3 = i;
        do {
            i3 = str.indexOf(charAt, i3 + 1);
            if (i3 >= 0) {
                i2 = i3;
                while (i2 > i && str.charAt(i2 - 1) == '\\') {
                    i2--;
                }
            } else {
                return str.length();
            }
        } while (((i3 - i2) & 1) != 0);
        return i3 + 1;
    }

    private void elideTrailingComma(int i) {
        while (true) {
            int i2 = i - 1;
            if (i2 >= this.cleaned) {
                char charAt = this.jsonish.charAt(i2);
                if (charAt != '\t' && charAt != '\n' && charAt != '\r' && charAt != ' ') {
                    if (charAt == ',') {
                        elide(i2, i);
                        return;
                    } else {
                        throw new AssertionError("" + this.jsonish.charAt(i2));
                    }
                }
                i = i2;
            } else {
                int length = this.sanitizedJson.length();
                while (true) {
                    length--;
                    if (length >= 0) {
                        char charAt2 = this.sanitizedJson.charAt(length);
                        if (charAt2 != '\t' && charAt2 != '\n' && charAt2 != '\r' && charAt2 != ' ') {
                            if (charAt2 == ',') {
                                this.sanitizedJson.setLength(length);
                                return;
                            } else {
                                throw new AssertionError("" + this.sanitizedJson.charAt(length));
                            }
                        }
                    } else {
                        throw new AssertionError("Trailing comma not found in " + this.jsonish + " or " + ((Object) this.sanitizedJson));
                    }
                }
            }
        }
    }

    private void normalizeNumber(int i, int i2) {
        int i3;
        int i4;
        int i5;
        int i6;
        char charAt;
        if (i < i2) {
            char charAt2 = this.jsonish.charAt(i);
            if (charAt2 == '+') {
                int i7 = i + 1;
                elide(i, i7);
                i = i7;
            } else if (charAt2 == '-') {
                i++;
            }
        }
        int endOfDigitRun = endOfDigitRun(i, i2);
        if (i == endOfDigitRun) {
            insert(i, '0');
        } else if ('0' == this.jsonish.charAt(i)) {
            int i8 = endOfDigitRun - i;
            int i9 = 16;
            int i10 = 0;
            boolean z = true;
            if (i8 == 1 && endOfDigitRun < i2 && 120 == (this.jsonish.charAt(endOfDigitRun) | ' ')) {
                int i11 = endOfDigitRun + 1;
                int i12 = i11;
                while (i12 < i2) {
                    char charAt3 = this.jsonish.charAt(i12);
                    if ('0' > charAt3 || charAt3 > '9') {
                        char c = (char) (charAt3 | ' ');
                        if ('a' > c || c > 'f') {
                            break;
                        } else {
                            i6 = c - 'W';
                        }
                    } else {
                        i6 = charAt3 - '0';
                    }
                    i10 = Math.max(i6, i10);
                    i12++;
                }
                i4 = i10;
                i5 = 16;
                int i13 = i12;
                i3 = i11;
                endOfDigitRun = i13;
            } else if (i8 > 1) {
                for (int i14 = i; i14 < endOfDigitRun; i14++) {
                    int charAt4 = this.jsonish.charAt(i14) - '0';
                    if (charAt4 < 0) {
                        break;
                    }
                    i10 = Math.max(charAt4, i10);
                }
                i4 = i10;
                i5 = 8;
                i3 = i;
            } else {
                i3 = -1;
                z = false;
                i4 = 0;
                i5 = 10;
            }
            if (z) {
                elide(i, endOfDigitRun);
                String substring = this.jsonish.substring(i3, endOfDigitRun);
                int length = substring.length();
                if (i5 > i4) {
                    i9 = i5;
                } else if (i4 <= 10) {
                    i9 = 10;
                }
                if (length == 0) {
                    this.sanitizedJson.append('0');
                } else if (DIGITS_BY_BASE_THAT_FIT_IN_63B[i9] >= length) {
                    this.sanitizedJson.append(Long.parseLong(substring, i9));
                } else {
                    this.sanitizedJson.append(new BigInteger(substring, i9));
                }
            }
        }
        if (endOfDigitRun < i2 && this.jsonish.charAt(endOfDigitRun) == '.') {
            int i15 = endOfDigitRun + 1;
            int endOfDigitRun2 = endOfDigitRun(i15, i2);
            if (endOfDigitRun2 == i15) {
                insert(i15, '0');
            }
            endOfDigitRun = endOfDigitRun2;
        }
        if (endOfDigitRun < i2 && 101 == (this.jsonish.charAt(endOfDigitRun) | ' ')) {
            int i16 = endOfDigitRun + 1;
            if (i16 < i2 && ((charAt = this.jsonish.charAt(i16)) == '+' || charAt == '-')) {
                i16 = endOfDigitRun + 2;
            }
            endOfDigitRun = endOfDigitRun(i16, i2);
            if (endOfDigitRun == i16) {
                insert(i16, '0');
            }
        }
        if (endOfDigitRun != i2) {
            elide(endOfDigitRun, i2);
        }
    }

    private boolean canonicalizeNumber(int i, int i2) {
        elide(i, i);
        int length = this.sanitizedJson.length();
        normalizeNumber(i, i2);
        elide(i2, i2);
        return canonicalizeNumber(this.sanitizedJson, length, this.sanitizedJson.length());
    }

    private static boolean canonicalizeNumber(StringBuilder sb, int i, int i2) {
        int i3;
        int parseInt;
        char c;
        char charAt;
        char charAt2;
        int i4 = i + (sb.charAt(i) == '-' ? 1 : 0);
        int i5 = i4;
        while (i5 < i2 && '0' <= (charAt2 = sb.charAt(i5)) && charAt2 <= '9') {
            i5++;
        }
        if (i5 != i2 && '.' == sb.charAt(i5)) {
            do {
                i5++;
                if (i5 >= i2 || '0' > (charAt = sb.charAt(i5))) {
                    break;
                }
            } while (charAt <= '9');
        }
        if (i5 == i2) {
            i3 = i2;
        } else {
            i3 = i5 + 1;
            if (sb.charAt(i3) == '+') {
                i3 = i5 + 2;
            }
        }
        if (i2 == i3) {
            parseInt = 0;
        } else {
            try {
                parseInt = Integer.parseInt(sb.substring(i3, i2), 10);
            } catch (NumberFormatException unused) {
                return false;
            }
        }
        int i6 = i4;
        int i7 = i6;
        boolean z = false;
        int i8 = 0;
        boolean z2 = true;
        while (i6 < i5) {
            char charAt3 = sb.charAt(i6);
            if (charAt3 == '.') {
                if (z2) {
                    i8 = 0;
                }
                z = true;
            } else {
                if ((!z2 || charAt3 != '0') && !z) {
                    parseInt++;
                }
                if (charAt3 == '0') {
                    i8++;
                } else {
                    if (z2) {
                        if (z) {
                            parseInt -= i8;
                        }
                        i8 = 0;
                    }
                    while (true) {
                        if (i8 == 0 && charAt3 == 0) {
                            break;
                        }
                        if (i8 == 0) {
                            c = 0;
                        } else {
                            i8--;
                            c = charAt3;
                            charAt3 = '0';
                        }
                        sb.setCharAt(i7, charAt3);
                        i7++;
                        charAt3 = c;
                    }
                    z2 = false;
                }
            }
            i6++;
        }
        sb.setLength(i7);
        int i9 = i7 - i4;
        if (z2) {
            sb.setLength(i);
            sb.append('0');
            return true;
        }
        if (i9 <= parseInt && parseInt <= 21) {
            while (i9 < parseInt) {
                sb.append('0');
                i9++;
            }
        } else if (parseInt > 0 && parseInt <= 21) {
            sb.insert(i4 + parseInt, FilenameUtils.EXTENSION_SEPARATOR);
        } else if (-6 < parseInt && parseInt <= 0) {
            sb.insert(i4, "0.000000".substring(0, 2 - parseInt));
        } else {
            if (i9 != 1) {
                sb.insert(i4 + 1, FilenameUtils.EXTENSION_SEPARATOR);
            }
            int i10 = parseInt - 1;
            sb.append('e');
            sb.append(i10 >= 0 ? '+' : '-');
            sb.append(Math.abs(i10));
        }
        return true;
    }

    private boolean isKeyword(int i, int i2) {
        int i3 = i2 - i;
        if (i3 == 5) {
            return "false".regionMatches(0, this.jsonish, i, i3);
        }
        if (i3 == 4) {
            return Constants.NULL.regionMatches(0, this.jsonish, i, i3) || "true".regionMatches(0, this.jsonish, i, i3);
        }
        return false;
    }

    private boolean isOctAt(int i) {
        return isOct(this.jsonish.charAt(i));
    }

    private boolean isHexAt(int i) {
        return isHex(this.jsonish.charAt(i));
    }

    private boolean isJsonSpecialChar(int i) {
        char charAt = this.jsonish.charAt(i);
        return charAt <= ' ' || charAt == '\"' || charAt == ',' || charAt == ':' || charAt == '[' || charAt == ']' || charAt == '{' || charAt == '}';
    }

    private void appendHex(int i, int i2) {
        while (true) {
            i2--;
            if (i2 < 0) {
                return;
            }
            int i3 = (i >>> (i2 * 4)) & 15;
            this.sanitizedJson.append((char) (i3 + (i3 < 10 ? 48 : 87)));
        }
    }

    static final class UnbracketedComma extends Exception {
        private static final long serialVersionUID = 783239978717247850L;

        private UnbracketedComma() {
        }

        /* synthetic */ UnbracketedComma(AnonymousClass1 anonymousClass1) {
            this();
        }
    }

    private int endOfDigitRun(int i, int i2) {
        while (i < i2) {
            char charAt = this.jsonish.charAt(i);
            if ('0' > charAt || charAt > '9') {
                return i;
            }
            i++;
        }
        return i2;
    }

    static {
        UnbracketedComma unbracketedComma = new UnbracketedComma(null);
        UNBRACKETED_COMMA = unbracketedComma;
        unbracketedComma.setStackTrace(new StackTraceElement[0]);
        HEX_DIGITS = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
        DIGITS_BY_BASE_THAT_FIT_IN_63B = new int[]{-1, -1, 63, 39, 31, 27, 24, 22, 21, 19, 18, 18, 17, 17, 16, 16, 15};
    }

    CharSequence toCharSequence() {
        CharSequence charSequence = this.sanitizedJson;
        if (charSequence == null) {
            charSequence = this.jsonish;
        }
        return charSequence;
    }

    public String toString() {
        StringBuilder sb = this.sanitizedJson;
        return sb != null ? sb.toString() : this.jsonish;
    }

    private static int unescapedChar(String str, int i) {
        int length = str.length();
        int i2 = 0;
        if (i >= length) {
            return 0;
        }
        char charAt = str.charAt(i);
        if (charAt != '\\') {
            return charAt | IOUtils.DIR_SEPARATOR;
        }
        int i3 = i + 1;
        if (i3 == length) {
            return 65536;
        }
        char charAt2 = str.charAt(i3);
        if (charAt2 == 'b') {
            return 131080;
        }
        if (charAt2 == 'f') {
            return 131084;
        }
        if (charAt2 == 'n') {
            return 131082;
        }
        if (charAt2 == 'r') {
            return 131085;
        }
        if (charAt2 != 'x') {
            switch (charAt2) {
                case '0':
                case '1':
                case '2':
                case '3':
                case '4':
                case '5':
                case '6':
                case '7':
                    int i4 = i + 2;
                    if (i4 < length && isOct(str.charAt(i4))) {
                        i4 = i + 3;
                        if (charAt2 <= '3' && i4 < length && isOct(str.charAt(i4))) {
                            i4 = i + 4;
                        }
                    }
                    while (i3 < i4) {
                        i2 = (str.charAt(i3) - '0') | (i2 << 3);
                        i3++;
                    }
                    return ((i4 - i) << 16) | i2;
                default:
                    switch (charAt2) {
                        case 't':
                            return 131081;
                        case 'u':
                            int i5 = i + 5;
                            if (i5 < length) {
                                char charAt3 = str.charAt(i + 2);
                                char charAt4 = str.charAt(i + 3);
                                char charAt5 = str.charAt(i + 4);
                                char charAt6 = str.charAt(i5);
                                if (isHex(charAt3) && isHex(charAt4) && isHex(charAt5) && isHex(charAt6)) {
                                    return hexVal(charAt6) | (hexVal(charAt5) << 4) | (hexVal(charAt3) << 12) | 393216 | (hexVal(charAt4) << 8);
                                }
                            }
                            break;
                        case 'v':
                            return 131080;
                    }
            }
        } else {
            int i6 = i + 3;
            if (i6 < length) {
                char charAt7 = str.charAt(i + 2);
                char charAt8 = str.charAt(i6);
                if (isHex(charAt7) && isHex(charAt8)) {
                    return hexVal(charAt8) | (hexVal(charAt7) << 4) | 262144;
                }
            }
        }
        return 0 | charAt2;
    }

    private static int unescapedCharRev(String str, int i) {
        int i2;
        if (i < 0) {
            return 0;
        }
        int i3 = 1;
        while (true) {
            if (i3 >= 6 || (i2 = i - i3) < 0) {
                break;
            }
            if (str.charAt(i2) == '\\') {
                int i4 = 1;
                while (true) {
                    int i5 = i2 - i4;
                    if (i5 < 0 || str.charAt(i5) != '\\') {
                        break;
                    }
                    i4++;
                }
                if ((i4 & 1) == 1) {
                    int unescapedChar = unescapedChar(str, i2);
                    if ((unescapedChar >>> 16) - 1 == i3) {
                        return unescapedChar;
                    }
                }
            } else {
                i3++;
            }
        }
        return str.charAt(i) | IOUtils.DIR_SEPARATOR;
    }

    private static int runSlashPreceding(String str, int i) {
        int i2 = i;
        while (i2 >= 0 && str.charAt(i2) == '\\') {
            i2--;
        }
        return i - i2;
    }
}
