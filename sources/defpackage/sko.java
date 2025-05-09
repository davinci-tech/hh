package defpackage;

import android.icu.text.AlphabeticIndex;
import android.text.TextUtils;
import android.util.Log;
import java.lang.Character;
import java.util.Collections;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

/* loaded from: classes9.dex */
public class sko {
    private static sko j;
    private final e i;
    private final Locale k;
    private String l;
    public static final Locale b = new Locale("ar");
    public static final Locale d = new Locale("fa");
    public static final Locale c = new Locale("el");
    public static final Locale e = new Locale("he");
    public static final Locale f = new Locale("th");
    public static final Locale g = new Locale("uk");

    /* renamed from: a, reason: collision with root package name */
    public static final Locale f17092a = new Locale("hi");
    private static final String h = Locale.JAPANESE.getLanguage();

    static class b extends e {
        b(Locale locale) {
            super(locale);
        }
    }

    private sko(Locale locale) {
        if (locale == null) {
            this.k = Locale.getDefault();
        } else {
            this.k = locale;
        }
        String language = this.k.getLanguage();
        this.l = language;
        if (language.equals(h)) {
            this.i = new d(this.k);
        } else if (this.k.equals(Locale.CHINA)) {
            this.i = new b(this.k);
        } else {
            this.i = new e(this.k);
        }
    }

    public static sko e() {
        sko skoVar;
        synchronized (sko.class) {
            sko skoVar2 = j;
            if (skoVar2 == null || !skoVar2.b(Locale.getDefault())) {
                j = new sko(null);
            }
            skoVar = j;
        }
        return skoVar;
    }

    public int a(String str) {
        return this.i.c(str);
    }

    public boolean b(Locale locale) {
        return this.k.equals(locale);
    }

    public String d(String str) {
        return this.i.a(str);
    }

    public String e(int i) {
        return this.i.c(i);
    }

    public String e(String str) {
        char charAt;
        char charAt2;
        if (!TextUtils.isEmpty(str)) {
            if ("TW".equals(this.k.getCountry()) && (charAt2 = d(str).charAt(0)) >= 12549 && charAt2 <= 12585) {
                return String.valueOf(charAt2);
            }
            if ("ar".equals(this.k.getLanguage()) && (charAt = d(str).charAt(0)) < 1574 && charAt > 1569) {
                return "آ";
            }
        }
        return e(a(d(str)));
    }

    static class d extends e {
        private static final Set<Character.UnicodeBlock> b;
        private final int c;

        static {
            HashSet hashSet = new HashSet();
            hashSet.add(Character.UnicodeBlock.HIRAGANA);
            hashSet.add(Character.UnicodeBlock.KATAKANA);
            hashSet.add(Character.UnicodeBlock.KATAKANA_PHONETIC_EXTENSIONS);
            hashSet.add(Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS);
            hashSet.add(Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS);
            hashSet.add(Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A);
            hashSet.add(Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_B);
            hashSet.add(Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION);
            hashSet.add(Character.UnicodeBlock.CJK_RADICALS_SUPPLEMENT);
            hashSet.add(Character.UnicodeBlock.CJK_COMPATIBILITY);
            hashSet.add(Character.UnicodeBlock.CJK_COMPATIBILITY_FORMS);
            hashSet.add(Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS);
            hashSet.add(Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS_SUPPLEMENT);
            b = Collections.unmodifiableSet(hashSet);
        }

        d(Locale locale) {
            super(locale);
            this.c = super.c("日");
        }

        private static boolean e(int i) {
            return b.contains(Character.UnicodeBlock.of(i));
        }

        @Override // sko.e
        public int c(String str) {
            int c = super.c(str);
            return ((c != this.c || e(Character.codePointAt(str, 0))) && c <= this.c) ? c : c + 1;
        }

        @Override // sko.e
        public int d() {
            return super.d() + 1;
        }

        @Override // sko.e
        public String c(int i) {
            int i2 = this.c;
            if (i == i2) {
                return "他";
            }
            if (i > i2) {
                i--;
            }
            return super.c(i);
        }
    }

    static class e {

        /* renamed from: a, reason: collision with root package name */
        protected AlphabeticIndex.ImmutableIndex f17093a;
        private int c;
        private final Locale d;
        private int e;

        e(Locale locale) {
            this.f17093a = null;
            this.e = 0;
            this.c = 0;
            this.d = locale;
            AlphabeticIndex.ImmutableIndex buildImmutableIndex = new AlphabeticIndex(locale).setMaxLabelCount(300).addLabels(Locale.ENGLISH).addLabels(Locale.JAPANESE).addLabels(Locale.KOREAN).addLabels(sko.f).addLabels("fa".equals(locale.getLanguage()) ? sko.d : sko.b).addLabels(sko.e).addLabels(sko.c).addLabels(sko.g).addLabels(sko.f17092a).buildImmutableIndex();
            this.f17093a = buildImmutableIndex;
            int bucketCount = buildImmutableIndex.getBucketCount();
            this.c = bucketCount;
            this.e = bucketCount - 1;
        }

        private boolean a(int i) {
            return (!Character.isSpaceChar(i) && i != 43) && (i != 40) && (i != 35) && ((i != 41 && i != 46) & (i != 45));
        }

        public String a(String str) {
            return str;
        }

        public int c(String str) {
            int i;
            if (str == null) {
                Log.w("HwSectionLocaleUtils", "getBucketIndex: displayName is null!");
                return -1;
            }
            int length = str.length();
            int i2 = 0;
            while (i2 < length) {
                int codePointAt = Character.codePointAt(str, i2);
                if (Character.isDigit(codePointAt) || a(codePointAt)) {
                    break;
                }
                i2 += Character.charCount(codePointAt);
            }
            int bucketIndex = this.f17093a.getBucketIndex(str);
            if (bucketIndex < 0) {
                return -1;
            }
            if (bucketIndex >= this.e) {
                return bucketIndex + 1;
            }
            if (!"TW".equals(this.d.getCountry()) || length <= i2) {
                return bucketIndex;
            }
            int codePointAt2 = Character.codePointAt(str, i2);
            if (codePointAt2 < 12549 || codePointAt2 > 12573) {
                if (codePointAt2 >= 12573 && codePointAt2 <= 12585) {
                    i = codePointAt2 - 12550;
                }
                return bucketIndex;
            }
            i = codePointAt2 - 12549;
            return i + 1;
        }

        public int d() {
            return this.c + 1;
        }

        public String c(int i) {
            if (i >= 0 && i < d()) {
                if (i == 0) {
                    return "#";
                }
                if (i > this.e) {
                    i--;
                }
                if (this.f17093a.getBucket(i) != null) {
                    return this.f17093a.getBucket(i).getLabel();
                }
            }
            return "";
        }
    }
}
