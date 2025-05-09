package org.eclipse.californium.elements.util;

import defpackage.vcb;
import defpackage.vha;
import java.net.Inet4Address;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketAddress;
import java.net.SocketException;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;
import org.slf4j.Logger;

/* loaded from: classes7.dex */
public class NetworkInterfacesUtil {

    /* renamed from: a, reason: collision with root package name */
    private static boolean f15883a;
    private static boolean d;
    private static int f;
    private static int h;
    private static Inet4Address i;
    private static int j;
    private static NetworkInterface l;
    private static Inet4Address n;
    private static Inet6Address o;
    private static final Logger b = vha.a((Class<?>) NetworkInterfacesUtil.class);
    private static final Pattern c = Pattern.compile("(vxlan\\.calico|cali[0123456789abcdef]{10,}|cilium_\\w+|lxc[0123456789abcdef]{12,}|virbr\\d+|docker\\d+)");
    private static final Pattern e = Pattern.compile("^([0-9a-fA-F:]+)(%\\w+)?$");
    private static final Set<InetAddress> g = new HashSet();
    private static final Set<String> m = new HashSet();

    /* loaded from: classes10.dex */
    public interface InetAddressFilter {
        boolean matches(InetAddress inetAddress);
    }

    static class e implements Enumeration<NetworkInterface> {

        /* renamed from: a, reason: collision with root package name */
        private final Pattern f15884a;
        private final Enumeration<NetworkInterface> b;
        private NetworkInterface c;
        private final Pattern d;

        private e(Enumeration<NetworkInterface> enumeration) {
            Pattern pattern;
            this.b = enumeration;
            String e = vcb.e("COAP_NETWORK_INTERFACES");
            String e2 = vcb.e("COAP_NETWORK_INTERFACES_EXCLUDE");
            Pattern pattern2 = null;
            if (e != null && !e.isEmpty()) {
                pattern = Pattern.compile(e);
            } else if (e2 == null || e2.isEmpty()) {
                pattern2 = NetworkInterfacesUtil.c;
                pattern = null;
            } else {
                pattern = null;
            }
            if (e2 != null && !e2.isEmpty()) {
                pattern2 = Pattern.compile(e2);
            }
            this.f15884a = pattern;
            this.d = pattern2;
            e();
        }

        @Override // java.util.Enumeration
        public boolean hasMoreElements() {
            return this.c != null;
        }

        @Override // java.util.Enumeration
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public NetworkInterface nextElement() {
            NetworkInterface networkInterface = this.c;
            e();
            return networkInterface;
        }

        private void e() {
            Pattern pattern;
            Pattern pattern2;
            this.c = null;
            while (this.b.hasMoreElements()) {
                NetworkInterface nextElement = this.b.nextElement();
                String name = nextElement.getName();
                try {
                    if (nextElement.isUp() && (((pattern = this.f15884a) == null || pattern.matcher(name).matches()) && ((pattern2 = this.d) == null || !pattern2.matcher(name).matches()))) {
                        this.c = nextElement;
                        return;
                    }
                } catch (SocketException unused) {
                }
                NetworkInterfacesUtil.b.debug("skip {}", name);
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:173:0x01a7 A[Catch: all -> 0x01c7, TryCatch #1 {, blocks: (B:4:0x0003, B:6:0x0007, B:9:0x000b, B:11:0x0011, B:14:0x0020, B:16:0x0026, B:18:0x0032, B:22:0x003b, B:24:0x0041, B:25:0x0045, B:27:0x004b, B:30:0x0055, B:33:0x005d, B:39:0x0067, B:41:0x006d, B:43:0x0071, B:45:0x0075, B:47:0x0079, B:51:0x0082, B:52:0x0084, B:54:0x0088, B:55:0x008a, B:56:0x0092, B:58:0x0098, B:60:0x00a4, B:65:0x00ad, B:67:0x00b3, B:71:0x00b9, B:73:0x00bf, B:78:0x00c3, B:80:0x00c7, B:85:0x00d3, B:90:0x00dd, B:99:0x00e6, B:100:0x00ef, B:102:0x00f5, B:104:0x0101, B:106:0x0107, B:108:0x010d, B:110:0x0113, B:112:0x0127, B:124:0x0141, B:127:0x0148, B:130:0x014e, B:135:0x013d, B:136:0x0137, B:138:0x0151, B:139:0x0155, B:141:0x015b, B:159:0x0165, B:144:0x016e, B:147:0x0172, B:171:0x019f, B:173:0x01a7, B:180:0x01bb, B:181:0x01bf, B:184:0x0193, B:185:0x0181, B:186:0x0188), top: B:3:0x0003 }] */
    /* JADX WARN: Removed duplicated region for block: B:176:0x01b3  */
    /* JADX WARN: Removed duplicated region for block: B:178:0x01b7  */
    /* JADX WARN: Removed duplicated region for block: B:180:0x01bb A[Catch: all -> 0x01c7, TryCatch #1 {, blocks: (B:4:0x0003, B:6:0x0007, B:9:0x000b, B:11:0x0011, B:14:0x0020, B:16:0x0026, B:18:0x0032, B:22:0x003b, B:24:0x0041, B:25:0x0045, B:27:0x004b, B:30:0x0055, B:33:0x005d, B:39:0x0067, B:41:0x006d, B:43:0x0071, B:45:0x0075, B:47:0x0079, B:51:0x0082, B:52:0x0084, B:54:0x0088, B:55:0x008a, B:56:0x0092, B:58:0x0098, B:60:0x00a4, B:65:0x00ad, B:67:0x00b3, B:71:0x00b9, B:73:0x00bf, B:78:0x00c3, B:80:0x00c7, B:85:0x00d3, B:90:0x00dd, B:99:0x00e6, B:100:0x00ef, B:102:0x00f5, B:104:0x0101, B:106:0x0107, B:108:0x010d, B:110:0x0113, B:112:0x0127, B:124:0x0141, B:127:0x0148, B:130:0x014e, B:135:0x013d, B:136:0x0137, B:138:0x0151, B:139:0x0155, B:141:0x015b, B:159:0x0165, B:144:0x016e, B:147:0x0172, B:171:0x019f, B:173:0x01a7, B:180:0x01bb, B:181:0x01bf, B:184:0x0193, B:185:0x0181, B:186:0x0188), top: B:3:0x0003 }] */
    /* JADX WARN: Type inference failed for: r12v1 */
    /* JADX WARN: Type inference failed for: r12v10 */
    /* JADX WARN: Type inference failed for: r12v4 */
    /* JADX WARN: Type inference failed for: r12v6, types: [java.net.Inet4Address] */
    /* JADX WARN: Type inference failed for: r12v9 */
    /* JADX WARN: Type inference failed for: r13v1 */
    /* JADX WARN: Type inference failed for: r13v2 */
    /* JADX WARN: Type inference failed for: r13v4, types: [java.net.Inet4Address] */
    /* JADX WARN: Type inference failed for: r13v5 */
    /* JADX WARN: Type inference failed for: r13v6 */
    /* JADX WARN: Type inference failed for: r14v0 */
    /* JADX WARN: Type inference failed for: r14v1 */
    /* JADX WARN: Type inference failed for: r14v2 */
    /* JADX WARN: Type inference failed for: r14v3, types: [java.net.Inet6Address] */
    /* JADX WARN: Type inference failed for: r14v4 */
    /* JADX WARN: Type inference failed for: r14v5 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static void h() {
        /*
            Method dump skipped, instructions count: 458
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: org.eclipse.californium.elements.util.NetworkInterfacesUtil.h():void");
    }

    public static void a() {
        synchronized (NetworkInterfacesUtil.class) {
            f = 0;
            h = 0;
            j = 0;
            f15883a = false;
            d = false;
            m.clear();
            g.clear();
            i = null;
            n = null;
            o = null;
            l = null;
        }
    }

    public static int b() {
        h();
        return h;
    }

    public static int e() {
        h();
        return j;
    }

    public static boolean i() {
        h();
        return f15883a;
    }

    public static boolean g() {
        h();
        return d;
    }

    public static boolean c(InetAddress inetAddress) {
        h();
        return g.contains(inetAddress);
    }

    public static boolean b(InetAddress inetAddress) {
        h();
        return inetAddress != null && (inetAddress.isMulticastAddress() || g.contains(inetAddress));
    }

    public static boolean c(SocketAddress socketAddress, SocketAddress socketAddress2) {
        return socketAddress == socketAddress2 || (socketAddress != null && socketAddress.equals(socketAddress2));
    }
}
