package defpackage;

import com.google.flatbuffers.reflection.BaseType;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CoderResult;
import java.nio.charset.CodingErrorAction;
import java.security.PublicKey;
import java.security.cert.Certificate;
import java.util.regex.Pattern;
import org.eclipse.californium.elements.util.StandardCharsets;

/* loaded from: classes7.dex */
public class vcb {
    public static final String b;
    public static final boolean d;

    /* renamed from: a, reason: collision with root package name */
    private static final Pattern f17659a = Pattern.compile("^(([a-zA-Z0-9]|[a-zA-Z0-9][a-zA-Z0-9\\-]*[a-zA-Z0-9])\\.)*([A-Za-z0-9]|[A-Za-z0-9][A-Za-z0-9\\-]*[A-Za-z0-9])$");
    private static final Pattern j = Pattern.compile("^(\\[[0-9a-fA-F:]+(%\\w+)?\\]|[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3})$");
    public static final String c = System.getProperty("line.separator");
    private static final char[] e = "0123456789ABCDEF".toCharArray();
    private static final String[] g = new String[10];

    /* JADX WARN: Code restructure failed: missing block: B:12:0x0066, code lost:
    
        if ("0.0".equals(r0) != false) goto L13;
     */
    static {
        /*
            java.lang.String r0 = "^(([a-zA-Z0-9]|[a-zA-Z0-9][a-zA-Z0-9\\-]*[a-zA-Z0-9])\\.)*([A-Za-z0-9]|[A-Za-z0-9][A-Za-z0-9\\-]*[A-Za-z0-9])$"
            java.util.regex.Pattern r0 = java.util.regex.Pattern.compile(r0)
            defpackage.vcb.f17659a = r0
            java.lang.String r0 = "^(\\[[0-9a-fA-F:]+(%\\w+)?\\]|[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3})$"
            java.util.regex.Pattern r0 = java.util.regex.Pattern.compile(r0)
            defpackage.vcb.j = r0
            java.lang.String r0 = "line.separator"
            java.lang.String r0 = java.lang.System.getProperty(r0)
            defpackage.vcb.c = r0
            java.lang.String r0 = "0123456789ABCDEF"
            char[] r0 = r0.toCharArray()
            defpackage.vcb.e = r0
            r0 = 10
            java.lang.String[] r0 = new java.lang.String[r0]
            defpackage.vcb.g = r0
            java.lang.String r0 = ""
            r1 = 0
            r2 = r1
        L2a:
            java.lang.String[] r3 = defpackage.vcb.g
            int r4 = r3.length
            if (r2 >= r4) goto L45
            r3[r2] = r0
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            r3.append(r0)
            java.lang.String r0 = "\t"
            r3.append(r0)
            java.lang.String r0 = r3.toString()
            int r2 = r2 + 1
            goto L2a
        L45:
            java.lang.Class<java.net.InetSocketAddress> r0 = java.net.InetSocketAddress.class
            java.lang.String r2 = "getHostString"
            java.lang.Class[] r3 = new java.lang.Class[r1]     // Catch: java.lang.NoSuchMethodException -> L52
            java.lang.reflect.Method r0 = r0.getMethod(r2, r3)     // Catch: java.lang.NoSuchMethodException -> L52
            if (r0 == 0) goto L52
            r1 = 1
        L52:
            defpackage.vcb.d = r1
            java.lang.Class<vcb> r0 = defpackage.vcb.class
            java.lang.Package r0 = r0.getPackage()
            if (r0 == 0) goto L68
            java.lang.String r0 = r0.getImplementationVersion()
            java.lang.String r1 = "0.0"
            boolean r1 = r1.equals(r0)
            if (r1 == 0) goto L69
        L68:
            r0 = 0
        L69:
            defpackage.vcb.b = r0
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.vcb.<clinit>():void");
    }

    public static String b(InetSocketAddress inetSocketAddress) {
        if (d) {
            return inetSocketAddress.getHostString();
        }
        InetAddress address = inetSocketAddress.getAddress();
        if (address != null) {
            String inetAddress = address.toString();
            if (inetAddress.startsWith("/")) {
                return inetAddress.substring(1);
            }
            return address.getHostName();
        }
        return inetSocketAddress.getHostName();
    }

    public static String b(int i) {
        if (i < 0) {
            return "";
        }
        String[] strArr = g;
        if (i >= strArr.length) {
            return strArr[strArr.length - 1];
        }
        return strArr[i];
    }

    public static String a() {
        return c;
    }

    public static byte[] c(String str) {
        if (str == null) {
            return null;
        }
        int length = str.length();
        if ((length & 1) != 0) {
            throw new IllegalArgumentException("'" + str + "' has odd length!");
        }
        int i = length / 2;
        byte[] bArr = new byte[i];
        int i2 = 0;
        for (int i3 = 0; i3 < i; i3++) {
            int digit = Character.digit(str.charAt(i2), 16);
            if (digit < 0) {
                throw new IllegalArgumentException("'" + str + "' digit " + i2 + " is not hexadecimal!");
            }
            bArr[i3] = (byte) (digit << 4);
            int i4 = i2 + 1;
            int digit2 = Character.digit(str.charAt(i4), 16);
            if (digit2 < 0) {
                throw new IllegalArgumentException("'" + str + "' digit " + i4 + " is not hexadecimal!");
            }
            bArr[i3] = (byte) (bArr[i3] | ((byte) digit2));
            i2 += 2;
        }
        return bArr;
    }

    public static String e(byte[] bArr) {
        if (bArr == null) {
            return null;
        }
        return bArr.length == 0 ? "" : b(bArr, (char) 0, 0);
    }

    public static String b(byte[] bArr) {
        return b(bArr, (char) 0, 0);
    }

    public static String b(byte[] bArr, char c2, int i) {
        if (bArr == null || bArr.length == 0) {
            return "--";
        }
        if (i == 0 || i > bArr.length) {
            i = bArr.length;
        }
        StringBuilder sb = new StringBuilder((c2 == 0 ? 2 : 3) * i);
        for (int i2 = 0; i2 < i; i2++) {
            byte b2 = bArr[i2];
            char[] cArr = e;
            sb.append(cArr[(b2 & 255) >>> 4]);
            sb.append(cArr[b2 & BaseType.Obj]);
            if (c2 != 0 && i2 < i - 1) {
                sb.append(c2);
            }
        }
        return sb.toString();
    }

    public static String b(String str, int i) {
        return (str == null || i <= 0 || i >= str.length()) ? str : str.substring(0, i);
    }

    public static String b(byte[] bArr, int i) {
        if (bArr == null) {
            return "<no data>";
        }
        if (bArr.length == 0) {
            return "<empty data>";
        }
        if (bArr.length < i) {
            i = bArr.length;
        }
        int length = bArr.length;
        int i2 = 0;
        while (true) {
            if (i2 < length) {
                byte b2 = bArr[i2];
                if (32 > b2 && b2 != 9 && b2 != 10 && b2 != 13) {
                    break;
                }
                i2++;
            } else {
                CharsetDecoder newDecoder = StandardCharsets.UTF_8.newDecoder();
                newDecoder.onMalformedInput(CodingErrorAction.REPORT);
                newDecoder.onUnmappableCharacter(CodingErrorAction.REPORT);
                ByteBuffer wrap = ByteBuffer.wrap(bArr);
                CharBuffer allocate = CharBuffer.allocate(i);
                CoderResult decode = newDecoder.decode(wrap, allocate, true);
                newDecoder.flush(allocate);
                allocate.flip();
                if (CoderResult.OVERFLOW == decode) {
                    return "\"" + ((Object) allocate) + "\".. " + bArr.length + " bytes";
                }
                if (!decode.isError()) {
                    return "\"" + ((Object) allocate) + "\"";
                }
            }
        }
        String b3 = b(bArr, ' ', i);
        if (bArr.length <= i) {
            return b3;
        }
        return b3 + ".." + bArr.length + " bytes";
    }

    public static String c(InetAddress inetAddress) {
        if (inetAddress == null) {
            return null;
        }
        return inetAddress.getHostAddress();
    }

    public static String c(InetSocketAddress inetSocketAddress) {
        String c2;
        if (inetSocketAddress == null) {
            return null;
        }
        if (d) {
            c2 = b(inetSocketAddress);
        } else {
            InetAddress address = inetSocketAddress.getAddress();
            c2 = address != null ? c(address) : "<unresolved>";
        }
        if (inetSocketAddress.getAddress() instanceof Inet6Address) {
            return "[" + c2 + "]:" + inetSocketAddress.getPort();
        }
        return c2 + ":" + inetSocketAddress.getPort();
    }

    public static String c(SocketAddress socketAddress) {
        if (socketAddress == null) {
            return null;
        }
        if (socketAddress instanceof InetSocketAddress) {
            return c((InetSocketAddress) socketAddress);
        }
        return socketAddress.toString();
    }

    public static String a(InetSocketAddress inetSocketAddress) {
        if (inetSocketAddress == null) {
            return null;
        }
        InetAddress address = inetSocketAddress.getAddress();
        if (address != null && address.isAnyLocalAddress()) {
            return "port " + inetSocketAddress.getPort();
        }
        String str = "";
        String b2 = d ? b(inetSocketAddress) : "";
        String c2 = address != null ? c(address) : "<unresolved>";
        if (!b2.equals(c2)) {
            str = b2 + "/";
        }
        if (inetSocketAddress.getAddress() instanceof Inet6Address) {
            return str + "[" + c2 + "]:" + inetSocketAddress.getPort();
        }
        return str + c2 + ":" + inetSocketAddress.getPort();
    }

    public static Object b(final SocketAddress socketAddress) {
        if (socketAddress == null) {
            return null;
        }
        return new Object() { // from class: vcb.1
            public String toString() {
                SocketAddress socketAddress2 = socketAddress;
                if (socketAddress2 instanceof InetSocketAddress) {
                    return vcb.a((InetSocketAddress) socketAddress2);
                }
                return socketAddress2.toString();
            }
        };
    }

    public static boolean a(String str) {
        if (str == null) {
            return false;
        }
        return f17659a.matcher(str).matches();
    }

    public static boolean b(String str) {
        if (str == null) {
            return false;
        }
        return j.matcher(str).matches();
    }

    public static String d(InetAddress inetAddress) throws URISyntaxException {
        int indexOf;
        int i;
        if (inetAddress == null) {
            throw new NullPointerException("address must not be null!");
        }
        String hostAddress = inetAddress.getHostAddress();
        if (!(inetAddress instanceof Inet6Address)) {
            return hostAddress;
        }
        Inet6Address inet6Address = (Inet6Address) inetAddress;
        if ((inet6Address.getScopedInterface() == null && inet6Address.getScopeId() <= 0) || (indexOf = hostAddress.indexOf(37)) <= 0 || (i = indexOf + 1) >= hostAddress.length()) {
            return hostAddress;
        }
        String substring = hostAddress.substring(i);
        String substring2 = hostAddress.substring(0, indexOf);
        String str = substring2 + "%25" + substring;
        try {
            new URI(null, null, str, -1, null, null, null);
            return str;
        } catch (URISyntaxException e2) {
            String replaceAll = substring.replaceAll("[-._~]", "");
            if (replaceAll.isEmpty()) {
                return substring2;
            }
            String str2 = substring2 + "%25" + replaceAll;
            try {
                new URI(null, null, str2, -1, null, null, null);
                return str2;
            } catch (URISyntaxException unused) {
                throw e2;
            }
        }
    }

    public static String h(String str) {
        if (str == null) {
            return "";
        }
        if (str.isEmpty() || str.endsWith(" ")) {
            return str;
        }
        return str + " ";
    }

    public static String c(Certificate certificate) {
        String[] split = certificate.toString().split("\n");
        StringBuilder sb = new StringBuilder();
        int i = 0;
        for (String str : split) {
            String trim = str.trim();
            if (!trim.isEmpty()) {
                int i2 = i(trim);
                if (i2 < 0 && trim.length() == 1) {
                    i += i2;
                    i2 = 0;
                }
                sb.append(b(i));
                sb.append(trim);
                sb.append("\n");
                i += i2;
            } else {
                sb.append("\n");
            }
        }
        return sb.toString();
    }

    private static int i(String str) {
        int length = str.length();
        int i = 0;
        while (length > 0) {
            length--;
            char charAt = str.charAt(length);
            if (charAt == '[') {
                i++;
            } else if (charAt == ']') {
                i--;
            }
        }
        if (i == 0 || !str.matches("\\d+:\\s+.*")) {
            return i;
        }
        return 0;
    }

    public static String c(PublicKey publicKey) {
        return publicKey.toString().replaceAll("\n\\s+", "\n");
    }

    public static boolean d(String[] strArr, String str) {
        for (String str2 : strArr) {
            if (str2.equalsIgnoreCase(str)) {
                return true;
            }
        }
        return false;
    }

    public static String e(String str) {
        String property;
        String str2 = System.getenv(str);
        return ((str2 == null || str2.isEmpty()) && (property = System.getProperty(str)) != null) ? property : str2;
    }

    public static Boolean d(String str) {
        String e2 = e(str);
        if (e2 == null || e2.isEmpty()) {
            return null;
        }
        return Boolean.valueOf(e2);
    }
}
