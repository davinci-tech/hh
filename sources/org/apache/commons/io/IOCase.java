package org.apache.commons.io;

import com.hihonor.assistant.cardmgrsdk.CardMgrSdkConst;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Stream;

/* loaded from: classes10.dex */
public enum IOCase {
    SENSITIVE("Sensitive", true),
    INSENSITIVE("Insensitive", false),
    SYSTEM(CardMgrSdkConst.MSGScene.SYSTEM, FileSystem.getCurrent().isCaseSensitive());

    private static final long serialVersionUID = -6343169151696340687L;
    private final String name;
    private final transient boolean sensitive;

    public static IOCase value(IOCase iOCase, IOCase iOCase2) {
        return iOCase != null ? iOCase : iOCase2;
    }

    public static IOCase forName(final String str) {
        return (IOCase) Stream.of((Object[]) values()).filter(new Predicate() { // from class: org.apache.commons.io.IOCase$$ExternalSyntheticLambda0
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                boolean equals;
                equals = ((IOCase) obj).getName().equals(str);
                return equals;
            }
        }).findFirst().orElseThrow(new Supplier() { // from class: org.apache.commons.io.IOCase$$ExternalSyntheticLambda1
            @Override // java.util.function.Supplier
            public final Object get() {
                return IOCase.lambda$forName$1(str);
            }
        });
    }

    static /* synthetic */ IllegalArgumentException lambda$forName$1(String str) {
        return new IllegalArgumentException("Illegal IOCase name: " + str);
    }

    public static boolean isCaseSensitive(IOCase iOCase) {
        return iOCase != null && iOCase.isCaseSensitive();
    }

    IOCase(String str, boolean z) {
        this.name = str;
        this.sensitive = z;
    }

    public int checkCompareTo(String str, String str2) {
        Objects.requireNonNull(str, "str1");
        Objects.requireNonNull(str2, "str2");
        return this.sensitive ? str.compareTo(str2) : str.compareToIgnoreCase(str2);
    }

    public boolean checkEndsWith(String str, String str2) {
        if (str == null || str2 == null) {
            return false;
        }
        int length = str2.length();
        return str.regionMatches(!this.sensitive, str.length() - length, str2, 0, length);
    }

    public boolean checkEquals(String str, String str2) {
        return str == str2 || (str != null && (!this.sensitive ? !str.equalsIgnoreCase(str2) : !str.equals(str2)));
    }

    public int checkIndexOf(String str, int i, String str2) {
        int length;
        if (str == null || str2 == null || (length = str.length() - str2.length()) < i) {
            return -1;
        }
        while (i <= length) {
            if (checkRegionMatches(str, i, str2)) {
                return i;
            }
            i++;
        }
        return -1;
    }

    public boolean checkRegionMatches(String str, int i, String str2) {
        return (str == null || str2 == null || !str.regionMatches(this.sensitive ^ true, i, str2, 0, str2.length())) ? false : true;
    }

    public boolean checkStartsWith(String str, String str2) {
        return (str == null || str2 == null || !str.regionMatches(this.sensitive ^ true, 0, str2, 0, str2.length())) ? false : true;
    }

    public String getName() {
        return this.name;
    }

    public boolean isCaseSensitive() {
        return this.sensitive;
    }

    private Object readResolve() {
        return forName(this.name);
    }

    @Override // java.lang.Enum
    public String toString() {
        return this.name;
    }
}
