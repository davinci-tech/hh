package defpackage;

import com.huawei.openalliance.ad.constant.JsbMapKeyNames;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.security.Provider;
import java.security.SecureRandom;
import java.security.Security;
import org.eclipse.californium.elements.util.JceNames;
import org.slf4j.Logger;

/* loaded from: classes7.dex */
public class vbu {

    /* renamed from: a, reason: collision with root package name */
    private static final String[] f17653a;
    private static final String[][] b;
    private static volatile vbu c;
    private static final String[] d;
    private static final Logger e = vha.a((Class<?>) vbu.class);
    private final boolean f;
    private final String g;
    private final boolean h;
    private final boolean i;
    private final boolean j;
    private final boolean k;
    private final boolean n;
    private final boolean o;

    public static void c() {
    }

    static {
        String[] strArr = {JceNames.ED25519, "1.3.101.112", JceNames.OID_ED25519, JceNames.EDDSA, JceNames.ED25519v2};
        d = strArr;
        String[] strArr2 = {JceNames.ED448, "1.3.101.113", JceNames.OID_ED448, JceNames.EDDSA, JceNames.ED448v2};
        f17653a = strArr2;
        b = new String[][]{new String[]{JceNames.DH, "DiffieHellman"}, new String[]{JceNames.EC, JceNames.ECv2}, strArr, strArr2, new String[]{JceNames.X25519, JceNames.X25519v2, JceNames.OID_X25519}, new String[]{JceNames.X448, JceNames.X448v2, JceNames.OID_X448}};
        try {
            try {
                Class.forName(AccessController.class.getName());
                f();
            } catch (Throwable th) {
                e.error("JCE:", th);
            }
        } catch (ClassNotFoundException unused) {
            h();
        }
    }

    private static void f() {
        AccessController.doPrivileged(new PrivilegedAction<Void>() { // from class: vbu.5
            @Override // java.security.PrivilegedAction
            /* renamed from: e, reason: merged with bridge method [inline-methods] */
            public Void run() {
                try {
                    vbu.h();
                    return null;
                } catch (Throwable th) {
                    vbu.e.error("JCE:", th);
                    return null;
                }
            }
        });
    }

    private static boolean a(Provider provider) {
        return provider != null && provider.getName().equals(JceNames.JCE_PROVIDER_BOUNCY_CASTLE);
    }

    private static boolean e(Provider provider) {
        return provider != null && provider.getClass().getName().equals("net.i2p.crypto.eddsa.EdDSASecurityProvider");
    }

    private static void e(Provider provider, String str, String str2) {
        if (str2.equals(provider.getProperty(str))) {
            return;
        }
        provider.setProperty(str, str2);
    }

    private static Provider b(String str) {
        try {
            Provider provider = (Provider) Class.forName(str).getConstructor(new Class[0]).newInstance(new Object[0]);
            e.debug("Loaded {}", str);
            return provider;
        } catch (Throwable th) {
            Logger logger = e;
            if (logger.isTraceEnabled()) {
                logger.trace("Loading {} failed!", str, th);
                return null;
            }
            logger.debug("Loading {} failed!", str);
            return null;
        }
    }

    private static void j() {
        try {
            Class<?> cls = Class.forName("org.slf4j.bridge.SLF4JBridgeHandler");
            cls.getMethod("removeHandlersForRootLogger", new Class[0]).invoke(null, new Object[0]);
            cls.getMethod(JsbMapKeyNames.H5_TEXT_DOWNLOAD_INSTALL, new Class[0]).invoke(null, new Object[0]);
        } catch (ClassNotFoundException unused) {
            e.warn("Setup BC logging failed, missing logging bridge 'jul-to-slf4j'!");
        } catch (Throwable th) {
            e.warn("Setup BC logging failed!", th);
        }
    }

    private static String g() {
        String property = Security.getProperty("securerandom.strongAlgorithms");
        if (property != null) {
            if (property.contains("NativePRNGBlocking")) {
                Security.setProperty("securerandom.strongAlgorithms", property.replaceAll("NativePRNGBlocking", "NativePRNGNonBlocking"));
            } else {
                SecureRandom secureRandom = new SecureRandom();
                String str = secureRandom.getAlgorithm() + ":";
                if (!property.contains(str)) {
                    Security.setProperty("securerandom.strongAlgorithms", str + secureRandom.getProvider().getName() + "," + property);
                } else {
                    e.info("Random: {} already in {}", str, property);
                }
            }
        }
        return property;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Can't wrap try/catch for region: R(37:0|1|(1:169)(37:5|(1:7)(2:158|(33:160|9|10|11|12|(3:151|152|153)(1:14)|(2:17|(1:19)(6:20|(1:22)(1:49)|23|(7:37|38|39|40|(1:42)|43|44)|(1:26)|(3:32|33|34)))|(2:52|(1:54)(2:55|(5:57|58|59|60|61)))|66|67|68|(1:70)(2:144|145)|71|72|73|74|75|76|77|78|79|(1:139)(5:85|86|87|(1:89)(1:136)|90)|91|(1:93)|(1:95)(1:135)|96|(1:134)(9:99|(1:101)(2:131|(1:133))|102|103|104|105|106|107|108)|109|(1:111)(1:126)|112|(1:114)|115|(5:117|(2:120|118)|121|122|123)(1:125))(2:161|(34:163|10|11|12|(0)(0)|(2:17|(0)(0))|(2:52|(0)(0))|66|67|68|(0)(0)|71|72|73|74|75|76|77|78|79|(2:81|83)|139|91|(0)|(0)(0)|96|(0)|134|109|(0)(0)|112|(0)|115|(0)(0))(2:164|(1:166)(1:168))))|8|9|10|11|12|(0)(0)|(0)|(0)|66|67|68|(0)(0)|71|72|73|74|75|76|77|78|79|(0)|139|91|(0)|(0)(0)|96|(0)|134|109|(0)(0)|112|(0)|115|(0)(0))|167|10|11|12|(0)(0)|(0)|(0)|66|67|68|(0)(0)|71|72|73|74|75|76|77|78|79|(0)|139|91|(0)|(0)(0)|96|(0)|134|109|(0)(0)|112|(0)|115|(0)(0)) */
    /* JADX WARN: Code restructure failed: missing block: B:141:0x016c, code lost:
    
        r13 = false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:143:0x015b, code lost:
    
        r12 = false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:150:0x014a, code lost:
    
        r5 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:157:0x0071, code lost:
    
        r2 = false;
        r9 = null;
     */
    /* JADX WARN: Removed duplicated region for block: B:111:0x024d  */
    /* JADX WARN: Removed duplicated region for block: B:114:0x0260  */
    /* JADX WARN: Removed duplicated region for block: B:117:0x026f  */
    /* JADX WARN: Removed duplicated region for block: B:125:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:126:0x0250  */
    /* JADX WARN: Removed duplicated region for block: B:135:0x01e9  */
    /* JADX WARN: Removed duplicated region for block: B:144:0x0136 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:14:0x006f  */
    /* JADX WARN: Removed duplicated region for block: B:151:0x0062 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:16:0x0075 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:19:0x007d  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x0087  */
    /* JADX WARN: Removed duplicated region for block: B:51:0x00f7 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:54:0x0101  */
    /* JADX WARN: Removed duplicated region for block: B:55:0x010a  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x0133  */
    /* JADX WARN: Removed duplicated region for block: B:81:0x017a  */
    /* JADX WARN: Removed duplicated region for block: B:93:0x01d3  */
    /* JADX WARN: Removed duplicated region for block: B:95:0x01e6  */
    /* JADX WARN: Removed duplicated region for block: B:98:0x01f5 A[ADDED_TO_REGION] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static void h() {
        /*
            Method dump skipped, instructions count: 661
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.vbu.h():void");
    }

    public static boolean a() {
        return c.o;
    }

    public static boolean b() {
        return c.f;
    }

    public static boolean c(String str) {
        if (JceNames.EC.equalsIgnoreCase(str)) {
            return c.j;
        }
        if (JceNames.RSA.equalsIgnoreCase(str)) {
            return c.k;
        }
        String a2 = a(str, null);
        if (JceNames.OID_ED25519.equals(a2)) {
            return c.h;
        }
        if (JceNames.OID_ED448.equals(a2)) {
            return c.i;
        }
        return JceNames.EDDSA.equalsIgnoreCase(str) && (c.h || c.i);
    }

    public static String a(String str, String str2) {
        if (JceNames.EDDSA.equalsIgnoreCase(str)) {
            return JceNames.EDDSA;
        }
        return vcb.d(d, str) ? JceNames.OID_ED25519 : vcb.d(f17653a, str) ? JceNames.OID_ED448 : str2;
    }

    public static boolean c(String str, String str2) {
        if (str != null && str.equals(str2)) {
            return true;
        }
        for (String[] strArr : b) {
            if (vcb.d(strArr, str) && vcb.d(strArr, str2)) {
                return true;
            }
        }
        return false;
    }

    private vbu(boolean z, boolean z2, boolean z3, boolean z4, boolean z5, boolean z6, boolean z7, String str) {
        this.o = z;
        this.k = z2;
        this.j = z3;
        this.h = z4;
        this.i = z5;
        this.n = z6;
        this.f = z7;
        this.g = str;
    }

    public int hashCode() {
        int i = this.h ? 41 : 37;
        int i2 = this.i ? 41 : 37;
        int i3 = this.n ? 41 : 37;
        int i4 = this.j ? 41 : 37;
        return ((((((((((((i + 31) * 31) + i2) * 31) + i3) * 31) + i4) * 31) + (this.k ? 41 : 37)) * 31) + (this.o ? 41 : 37)) * 31) + this.g.hashCode();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        vbu vbuVar = (vbu) obj;
        return this.h == vbuVar.h && this.i == vbuVar.i && this.n == vbuVar.n && this.j == vbuVar.j && this.k == vbuVar.k && this.o == vbuVar.o && this.g.equals(vbuVar.g);
    }
}
