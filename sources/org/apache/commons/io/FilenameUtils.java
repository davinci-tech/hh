package org.apache.commons.io;

import java.io.File;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

/* loaded from: classes10.dex */
public class FilenameUtils {
    private static final int BASE_16 = 16;
    private static final String EMPTY_STRING = "";
    private static final String[] EMPTY_STRING_ARRAY = new String[0];
    public static final char EXTENSION_SEPARATOR = '.';
    public static final String EXTENSION_SEPARATOR_STR = Character.toString(EXTENSION_SEPARATOR);
    private static final int IPV4_MAX_OCTET_VALUE = 255;
    private static final Pattern IPV4_PATTERN;
    private static final int IPV6_MAX_HEX_DIGITS_PER_GROUP = 4;
    private static final int IPV6_MAX_HEX_GROUPS = 8;
    private static final int MAX_UNSIGNED_SHORT = 65535;
    private static final int NOT_FOUND = -1;
    private static final char OTHER_SEPARATOR;
    private static final Pattern REG_NAME_PART_PATTERN;
    private static final char SYSTEM_NAME_SEPARATOR;
    private static final char UNIX_NAME_SEPARATOR = '/';
    private static final char WINDOWS_NAME_SEPARATOR = '\\';

    private static boolean isSeparator(char c) {
        return c == '/' || c == '\\';
    }

    private static char toSeparator(boolean z) {
        return z ? '/' : '\\';
    }

    static {
        char c = File.separatorChar;
        SYSTEM_NAME_SEPARATOR = c;
        OTHER_SEPARATOR = flipSeparator(c);
        IPV4_PATTERN = Pattern.compile("^(\\d{1,3})\\.(\\d{1,3})\\.(\\d{1,3})\\.(\\d{1,3})$");
        REG_NAME_PART_PATTERN = Pattern.compile("^[a-zA-Z0-9][a-zA-Z0-9-]*$");
    }

    public static String concat(String str, String str2) {
        int prefixLength = getPrefixLength(str2);
        if (prefixLength < 0) {
            return null;
        }
        if (prefixLength > 0) {
            return normalize(str2);
        }
        if (str == null) {
            return null;
        }
        int length = str.length();
        if (length == 0) {
            return normalize(str2);
        }
        if (isSeparator(str.charAt(length - 1))) {
            return normalize(str + str2);
        }
        return normalize(str + '/' + str2);
    }

    public static boolean directoryContains(String str, String str2) {
        if (isEmpty(str) || isEmpty(str2) || IOCase.SYSTEM.checkEquals(str, str2)) {
            return false;
        }
        char separator = toSeparator(str.charAt(0) == '/');
        if (str.charAt(str.length() - 1) != separator) {
            str = str + separator;
        }
        return IOCase.SYSTEM.checkStartsWith(str2, str);
    }

    private static String doGetFullPath(String str, boolean z) {
        int prefixLength;
        if (str == null || (prefixLength = getPrefixLength(str)) < 0) {
            return null;
        }
        if (prefixLength >= str.length()) {
            return z ? getPrefix(str) : str;
        }
        int indexOfLastSeparator = indexOfLastSeparator(str);
        if (indexOfLastSeparator < 0) {
            return str.substring(0, prefixLength);
        }
        int i = indexOfLastSeparator + (z ? 1 : 0);
        if (i == 0) {
            i++;
        }
        return str.substring(0, i);
    }

    private static String doGetPath(String str, int i) {
        int prefixLength;
        if (str == null || (prefixLength = getPrefixLength(str)) < 0) {
            return null;
        }
        int indexOfLastSeparator = indexOfLastSeparator(str);
        int i2 = i + indexOfLastSeparator;
        return (prefixLength >= str.length() || indexOfLastSeparator < 0 || prefixLength >= i2) ? "" : requireNonNullChars(str.substring(prefixLength, i2));
    }

    private static String doNormalize(String str, char c, boolean z) {
        boolean z2;
        if (str == null) {
            return null;
        }
        requireNonNullChars(str);
        int length = str.length();
        if (length == 0) {
            return str;
        }
        int prefixLength = getPrefixLength(str);
        if (prefixLength < 0) {
            return null;
        }
        int i = length + 2;
        char[] cArr = new char[i];
        str.getChars(0, str.length(), cArr, 0);
        char flipSeparator = flipSeparator(c);
        for (int i2 = 0; i2 < i; i2++) {
            if (cArr[i2] == flipSeparator) {
                cArr[i2] = c;
            }
        }
        if (cArr[length - 1] != c) {
            cArr[length] = c;
            length++;
            z2 = false;
        } else {
            z2 = true;
        }
        int i3 = prefixLength != 0 ? prefixLength : 1;
        while (i3 < length) {
            if (cArr[i3] == c) {
                int i4 = i3 - 1;
                if (cArr[i4] == c) {
                    System.arraycopy(cArr, i3, cArr, i4, length - i3);
                    length--;
                    i3 = i4;
                }
            }
            i3++;
        }
        int i5 = prefixLength + 1;
        int i6 = i5;
        while (i6 < length) {
            if (cArr[i6] == c) {
                int i7 = i6 - 1;
                if (cArr[i7] == '.' && (i6 == i5 || cArr[i6 - 2] == c)) {
                    if (i6 == length - 1) {
                        z2 = true;
                    }
                    System.arraycopy(cArr, i6 + 1, cArr, i7, length - i6);
                    length -= 2;
                    i6 = i7;
                }
            }
            i6++;
        }
        int i8 = prefixLength + 2;
        int i9 = i8;
        while (i9 < length) {
            if (cArr[i9] == c && cArr[i9 - 1] == '.' && cArr[i9 - 2] == '.' && (i9 == i8 || cArr[i9 - 3] == c)) {
                if (i9 == i8) {
                    return null;
                }
                if (i9 == length - 1) {
                    z2 = true;
                }
                int i10 = i9 - 4;
                while (true) {
                    if (i10 >= prefixLength) {
                        if (cArr[i10] == c) {
                            int i11 = i10 + 1;
                            System.arraycopy(cArr, i9 + 1, cArr, i11, length - i9);
                            length -= i9 - i10;
                            i9 = i11;
                            break;
                        }
                        i10--;
                    } else {
                        int i12 = i9 + 1;
                        System.arraycopy(cArr, i12, cArr, prefixLength, length - i9);
                        length -= i12 - prefixLength;
                        i9 = i5;
                        break;
                    }
                }
            }
            i9++;
        }
        if (length <= 0) {
            return "";
        }
        if (length <= prefixLength) {
            return new String(cArr, 0, length);
        }
        if (z2 && z) {
            return new String(cArr, 0, length);
        }
        return new String(cArr, 0, length - 1);
    }

    public static boolean equals(String str, String str2) {
        return equals(str, str2, false, IOCase.SENSITIVE);
    }

    public static boolean equals(String str, String str2, boolean z, IOCase iOCase) {
        if (str == null || str2 == null) {
            return str == null && str2 == null;
        }
        if (z && ((str = normalize(str)) == null || (str2 = normalize(str2)) == null)) {
            return false;
        }
        return IOCase.value(iOCase, IOCase.SENSITIVE).checkEquals(str, str2);
    }

    public static boolean equalsNormalized(String str, String str2) {
        return equals(str, str2, true, IOCase.SENSITIVE);
    }

    public static boolean equalsNormalizedOnSystem(String str, String str2) {
        return equals(str, str2, true, IOCase.SYSTEM);
    }

    public static boolean equalsOnSystem(String str, String str2) {
        return equals(str, str2, false, IOCase.SYSTEM);
    }

    static char flipSeparator(char c) {
        if (c == '/') {
            return '\\';
        }
        if (c == '\\') {
            return '/';
        }
        throw new IllegalArgumentException(String.valueOf(c));
    }

    private static int getAdsCriticalOffset(String str) {
        int lastIndexOf = str.lastIndexOf(SYSTEM_NAME_SEPARATOR);
        int lastIndexOf2 = str.lastIndexOf(OTHER_SEPARATOR);
        if (lastIndexOf != -1) {
            return lastIndexOf2 == -1 ? lastIndexOf + 1 : Math.max(lastIndexOf, lastIndexOf2) + 1;
        }
        if (lastIndexOf2 == -1) {
            return 0;
        }
        return lastIndexOf2 + 1;
    }

    public static String getBaseName(String str) {
        return removeExtension(getName(str));
    }

    public static String getExtension(String str) throws IllegalArgumentException {
        if (str == null) {
            return null;
        }
        int indexOfExtension = indexOfExtension(str);
        return indexOfExtension == -1 ? "" : str.substring(indexOfExtension + 1);
    }

    public static String getFullPath(String str) {
        return doGetFullPath(str, true);
    }

    public static String getFullPathNoEndSeparator(String str) {
        return doGetFullPath(str, false);
    }

    public static String getName(String str) {
        if (str == null) {
            return null;
        }
        return requireNonNullChars(str).substring(indexOfLastSeparator(str) + 1);
    }

    public static String getPath(String str) {
        return doGetPath(str, 1);
    }

    public static String getPathNoEndSeparator(String str) {
        return doGetPath(str, 0);
    }

    public static String getPrefix(String str) {
        int prefixLength;
        if (str == null || (prefixLength = getPrefixLength(str)) < 0) {
            return null;
        }
        if (prefixLength > str.length()) {
            requireNonNullChars(str);
            return str + '/';
        }
        return requireNonNullChars(str.substring(0, prefixLength));
    }

    public static int getPrefixLength(String str) {
        if (str == null) {
            return -1;
        }
        int length = str.length();
        if (length == 0) {
            return 0;
        }
        char charAt = str.charAt(0);
        if (charAt == ':') {
            return -1;
        }
        if (length == 1) {
            if (charAt == '~') {
                return 2;
            }
            return isSeparator(charAt) ? 1 : 0;
        }
        if (charAt == '~') {
            int indexOf = str.indexOf(47, 1);
            int indexOf2 = str.indexOf(92, 1);
            if (indexOf == -1 && indexOf2 == -1) {
                return length + 1;
            }
            if (indexOf == -1) {
                indexOf = indexOf2;
            }
            if (indexOf2 == -1) {
                indexOf2 = indexOf;
            }
            return Math.min(indexOf, indexOf2) + 1;
        }
        char charAt2 = str.charAt(1);
        if (charAt2 == ':') {
            char upperCase = Character.toUpperCase(charAt);
            if (upperCase < 'A' || upperCase > 'Z') {
                return upperCase == '/' ? 1 : -1;
            }
            if (length != 2 || FileSystem.getCurrent().supportsDriveLetter()) {
                return (length == 2 || !isSeparator(str.charAt(2))) ? 2 : 3;
            }
            return 0;
        }
        if (!isSeparator(charAt) || !isSeparator(charAt2)) {
            return isSeparator(charAt) ? 1 : 0;
        }
        int indexOf3 = str.indexOf(47, 2);
        int indexOf4 = str.indexOf(92, 2);
        if ((indexOf3 == -1 && indexOf4 == -1) || indexOf3 == 2 || indexOf4 == 2) {
            return -1;
        }
        if (indexOf3 == -1) {
            indexOf3 = indexOf4;
        }
        if (indexOf4 == -1) {
            indexOf4 = indexOf3;
        }
        int min = Math.min(indexOf3, indexOf4);
        int i = min + 1;
        if (isValidHostName(str.substring(2, min))) {
            return i;
        }
        return -1;
    }

    public static int indexOfExtension(String str) throws IllegalArgumentException {
        if (str == null) {
            return -1;
        }
        if (isSystemWindows() && str.indexOf(58, getAdsCriticalOffset(str)) != -1) {
            throw new IllegalArgumentException("NTFS ADS separator (':') in file name is forbidden.");
        }
        int lastIndexOf = str.lastIndexOf(46);
        if (indexOfLastSeparator(str) > lastIndexOf) {
            return -1;
        }
        return lastIndexOf;
    }

    public static int indexOfLastSeparator(String str) {
        if (str == null) {
            return -1;
        }
        return Math.max(str.lastIndexOf(47), str.lastIndexOf(92));
    }

    private static boolean isEmpty(String str) {
        return str == null || str.isEmpty();
    }

    public static boolean isExtension(String str, Collection<String> collection) {
        if (str == null) {
            return false;
        }
        requireNonNullChars(str);
        if (collection == null || collection.isEmpty()) {
            return indexOfExtension(str) == -1;
        }
        return collection.contains(getExtension(str));
    }

    public static boolean isExtension(String str, String str2) {
        if (str == null) {
            return false;
        }
        requireNonNullChars(str);
        if (isEmpty(str2)) {
            return indexOfExtension(str) == -1;
        }
        return getExtension(str).equals(str2);
    }

    public static boolean isExtension(String str, String... strArr) {
        if (str == null) {
            return false;
        }
        requireNonNullChars(str);
        if (strArr == null || strArr.length == 0) {
            return indexOfExtension(str) == -1;
        }
        final String extension = getExtension(str);
        Stream of = Stream.of((Object[]) strArr);
        Objects.requireNonNull(extension);
        return of.anyMatch(new Predicate() { // from class: org.apache.commons.io.FilenameUtils$$ExternalSyntheticLambda0
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                boolean equals;
                equals = extension.equals((String) obj);
                return equals;
            }
        });
    }

    private static boolean isIPv4Address(String str) {
        Matcher matcher = IPV4_PATTERN.matcher(str);
        if (!matcher.matches() || matcher.groupCount() != 4) {
            return false;
        }
        for (int i = 1; i <= 4; i++) {
            String group = matcher.group(i);
            if (Integer.parseInt(group) > 255) {
                return false;
            }
            if (group.length() > 1 && group.startsWith("0")) {
                return false;
            }
        }
        return true;
    }

    private static boolean isIPv6Address(String str) {
        boolean contains = str.contains("::");
        if (contains && str.indexOf("::") != str.lastIndexOf("::")) {
            return false;
        }
        if ((str.startsWith(":") && !str.startsWith("::")) || (str.endsWith(":") && !str.endsWith("::"))) {
            return false;
        }
        String[] split = str.split(":");
        if (contains) {
            ArrayList arrayList = new ArrayList(Arrays.asList(split));
            if (str.endsWith("::")) {
                arrayList.add("");
            } else if (str.startsWith("::") && !arrayList.isEmpty()) {
                arrayList.remove(0);
            }
            split = (String[]) arrayList.toArray(EMPTY_STRING_ARRAY);
        }
        if (split.length > 8) {
            return false;
        }
        int i = 0;
        int i2 = 0;
        for (int i3 = 0; i3 < split.length; i3++) {
            String str2 = split[i3];
            if (str2.isEmpty()) {
                i2++;
                if (i2 > 1) {
                    return false;
                }
            } else if (i3 == split.length - 1 && str2.contains(".")) {
                if (!isIPv4Address(str2)) {
                    return false;
                }
                i += 2;
                i2 = 0;
            } else {
                if (str2.length() > 4) {
                    return false;
                }
                try {
                    int parseInt = Integer.parseInt(str2, 16);
                    if (parseInt >= 0 && parseInt <= 65535) {
                        i2 = 0;
                    }
                } catch (NumberFormatException unused) {
                }
                return false;
            }
            i++;
        }
        if (i <= 8) {
            return i >= 8 || contains;
        }
        return false;
    }

    private static boolean isRFC3986HostName(String str) {
        String[] split = str.split("\\.", -1);
        int i = 0;
        while (i < split.length) {
            if (split[i].isEmpty()) {
                return i == split.length - 1;
            }
            if (!REG_NAME_PART_PATTERN.matcher(split[i]).matches()) {
                return false;
            }
            i++;
        }
        return true;
    }

    static boolean isSystemWindows() {
        return SYSTEM_NAME_SEPARATOR == '\\';
    }

    private static boolean isValidHostName(String str) {
        return isIPv6Address(str) || isRFC3986HostName(str);
    }

    public static String normalize(String str) {
        return doNormalize(str, SYSTEM_NAME_SEPARATOR, true);
    }

    public static String normalize(String str, boolean z) {
        return doNormalize(str, toSeparator(z), true);
    }

    public static String normalizeNoEndSeparator(String str) {
        return doNormalize(str, SYSTEM_NAME_SEPARATOR, false);
    }

    public static String normalizeNoEndSeparator(String str, boolean z) {
        return doNormalize(str, toSeparator(z), false);
    }

    public static String removeExtension(String str) {
        if (str == null) {
            return null;
        }
        requireNonNullChars(str);
        int indexOfExtension = indexOfExtension(str);
        return indexOfExtension == -1 ? str : str.substring(0, indexOfExtension);
    }

    private static String requireNonNullChars(String str) {
        if (str.indexOf(0) < 0) {
            return str;
        }
        throw new IllegalArgumentException("Null character present in file/path name. There are no known legitimate use cases for such data, but several injection attacks may use it");
    }

    public static String separatorsToSystem(String str) {
        return FileSystem.getCurrent().normalizeSeparators(str);
    }

    public static String separatorsToUnix(String str) {
        return FileSystem.LINUX.normalizeSeparators(str);
    }

    public static String separatorsToWindows(String str) {
        return FileSystem.WINDOWS.normalizeSeparators(str);
    }

    static String[] splitOnTokens(String str) {
        if (str.indexOf(63) == -1 && str.indexOf(42) == -1) {
            return new String[]{str};
        }
        char[] charArray = str.toCharArray();
        ArrayList arrayList = new ArrayList();
        StringBuilder sb = new StringBuilder();
        int length = charArray.length;
        int i = 0;
        char c = 0;
        while (i < length) {
            char c2 = charArray[i];
            if (c2 == '?' || c2 == '*') {
                if (sb.length() != 0) {
                    arrayList.add(sb.toString());
                    sb.setLength(0);
                }
                if (c2 == '?') {
                    arrayList.add("?");
                } else if (c != '*') {
                    arrayList.add("*");
                }
            } else {
                sb.append(c2);
            }
            i++;
            c = c2;
        }
        if (sb.length() != 0) {
            arrayList.add(sb.toString());
        }
        return (String[]) arrayList.toArray(EMPTY_STRING_ARRAY);
    }

    public static boolean wildcardMatch(String str, String str2) {
        return wildcardMatch(str, str2, IOCase.SENSITIVE);
    }

    public static boolean wildcardMatch(String str, String str2, IOCase iOCase) {
        if (str == null && str2 == null) {
            return true;
        }
        if (str != null && str2 != null) {
            IOCase value = IOCase.value(iOCase, IOCase.SENSITIVE);
            String[] splitOnTokens = splitOnTokens(str2);
            ArrayDeque arrayDeque = new ArrayDeque(splitOnTokens.length);
            int i = 0;
            int i2 = 0;
            boolean z = false;
            do {
                if (!arrayDeque.isEmpty()) {
                    int[] iArr = (int[]) arrayDeque.pop();
                    i2 = iArr[0];
                    i = iArr[1];
                    z = true;
                }
                while (i2 < splitOnTokens.length) {
                    if (splitOnTokens[i2].equals("?")) {
                        i++;
                        if (i > str.length()) {
                            break;
                        }
                        z = false;
                        i2++;
                    } else if (splitOnTokens[i2].equals("*")) {
                        if (i2 == splitOnTokens.length - 1) {
                            i = str.length();
                        }
                        z = true;
                        i2++;
                    } else {
                        if (z) {
                            i = value.checkIndexOf(str, i, splitOnTokens[i2]);
                            if (i == -1) {
                                break;
                            }
                            int checkIndexOf = value.checkIndexOf(str, i + 1, splitOnTokens[i2]);
                            if (checkIndexOf >= 0) {
                                arrayDeque.push(new int[]{i2, checkIndexOf});
                            }
                            i += splitOnTokens[i2].length();
                            z = false;
                        } else {
                            if (!value.checkRegionMatches(str, i, splitOnTokens[i2])) {
                                break;
                            }
                            i += splitOnTokens[i2].length();
                            z = false;
                        }
                        i2++;
                    }
                }
                if (i2 == splitOnTokens.length && i == str.length()) {
                    return true;
                }
            } while (!arrayDeque.isEmpty());
        }
        return false;
    }

    public static boolean wildcardMatchOnSystem(String str, String str2) {
        return wildcardMatch(str, str2, IOCase.SYSTEM);
    }

    @Deprecated
    public FilenameUtils() {
    }
}
