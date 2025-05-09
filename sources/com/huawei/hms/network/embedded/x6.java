package com.huawei.hms.network.embedded;

import com.huawei.hihealthservice.old.model.OldToNewMotionPath;
import com.huawei.indoorequip.datastruct.MachineControlPointResponse;
import com.huawei.up.model.UserInfomation;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes9.dex */
public final class x6 {

    /* renamed from: a, reason: collision with root package name */
    public final String f5569a;
    public static final Comparator<String> b = new Comparator() { // from class: com.huawei.hms.network.embedded.x6$$ExternalSyntheticLambda0
        @Override // java.util.Comparator
        public final int compare(Object obj, Object obj2) {
            return x6.a((String) obj, (String) obj2);
        }
    };
    public static final Map<String, x6> c = new LinkedHashMap();
    public static final x6 d = a("SSL_RSA_WITH_NULL_MD5", 1);
    public static final x6 e = a("SSL_RSA_WITH_NULL_SHA", 2);
    public static final x6 f = a("SSL_RSA_EXPORT_WITH_RC4_40_MD5", 3);
    public static final x6 g = a("SSL_RSA_WITH_RC4_128_MD5", 4);
    public static final x6 h = a("SSL_RSA_WITH_RC4_128_SHA", 5);
    public static final x6 i = a("SSL_RSA_EXPORT_WITH_DES40_CBC_SHA", 8);
    public static final x6 j = a("SSL_RSA_WITH_DES_CBC_SHA", 9);
    public static final x6 k = a("SSL_RSA_WITH_3DES_EDE_CBC_SHA", 10);
    public static final x6 l = a("SSL_DHE_DSS_EXPORT_WITH_DES40_CBC_SHA", 17);
    public static final x6 m = a("SSL_DHE_DSS_WITH_DES_CBC_SHA", 18);
    public static final x6 n = a("SSL_DHE_DSS_WITH_3DES_EDE_CBC_SHA", 19);
    public static final x6 o = a("SSL_DHE_RSA_EXPORT_WITH_DES40_CBC_SHA", 20);
    public static final x6 p = a("SSL_DHE_RSA_WITH_DES_CBC_SHA", 21);
    public static final x6 q = a("SSL_DHE_RSA_WITH_3DES_EDE_CBC_SHA", 22);
    public static final x6 r = a("SSL_DH_anon_EXPORT_WITH_RC4_40_MD5", 23);
    public static final x6 s = a("SSL_DH_anon_WITH_RC4_128_MD5", 24);
    public static final x6 t = a("SSL_DH_anon_EXPORT_WITH_DES40_CBC_SHA", 25);
    public static final x6 u = a("SSL_DH_anon_WITH_DES_CBC_SHA", 26);
    public static final x6 v = a("SSL_DH_anon_WITH_3DES_EDE_CBC_SHA", 27);
    public static final x6 w = a("TLS_KRB5_WITH_DES_CBC_SHA", 30);
    public static final x6 x = a("TLS_KRB5_WITH_3DES_EDE_CBC_SHA", 31);
    public static final x6 y = a("TLS_KRB5_WITH_RC4_128_SHA", 32);
    public static final x6 z = a("TLS_KRB5_WITH_DES_CBC_MD5", 34);
    public static final x6 A = a("TLS_KRB5_WITH_3DES_EDE_CBC_MD5", 35);
    public static final x6 B = a("TLS_KRB5_WITH_RC4_128_MD5", 36);
    public static final x6 C = a("TLS_KRB5_EXPORT_WITH_DES_CBC_40_SHA", 38);
    public static final x6 D = a("TLS_KRB5_EXPORT_WITH_RC4_40_SHA", 40);
    public static final x6 E = a("TLS_KRB5_EXPORT_WITH_DES_CBC_40_MD5", 41);
    public static final x6 F = a("TLS_KRB5_EXPORT_WITH_RC4_40_MD5", 43);
    public static final x6 G = a("TLS_RSA_WITH_AES_128_CBC_SHA", 47);
    public static final x6 H = a("TLS_DHE_DSS_WITH_AES_128_CBC_SHA", 50);
    public static final x6 I = a("TLS_DHE_RSA_WITH_AES_128_CBC_SHA", 51);
    public static final x6 J = a("TLS_DH_anon_WITH_AES_128_CBC_SHA", 52);
    public static final x6 K = a("TLS_RSA_WITH_AES_256_CBC_SHA", 53);
    public static final x6 L = a("TLS_DHE_DSS_WITH_AES_256_CBC_SHA", 56);
    public static final x6 M = a("TLS_DHE_RSA_WITH_AES_256_CBC_SHA", 57);
    public static final x6 N = a("TLS_DH_anon_WITH_AES_256_CBC_SHA", 58);
    public static final x6 O = a("TLS_RSA_WITH_NULL_SHA256", 59);
    public static final x6 P = a("TLS_RSA_WITH_AES_128_CBC_SHA256", 60);
    public static final x6 Q = a("TLS_RSA_WITH_AES_256_CBC_SHA256", 61);
    public static final x6 R = a("TLS_DHE_DSS_WITH_AES_128_CBC_SHA256", 64);
    public static final x6 S = a("TLS_RSA_WITH_CAMELLIA_128_CBC_SHA", 65);
    public static final x6 T = a("TLS_DHE_DSS_WITH_CAMELLIA_128_CBC_SHA", 68);
    public static final x6 U = a("TLS_DHE_RSA_WITH_CAMELLIA_128_CBC_SHA", 69);
    public static final x6 V = a("TLS_DHE_RSA_WITH_AES_128_CBC_SHA256", 103);
    public static final x6 W = a("TLS_DHE_DSS_WITH_AES_256_CBC_SHA256", 106);
    public static final x6 X = a("TLS_DHE_RSA_WITH_AES_256_CBC_SHA256", 107);
    public static final x6 Y = a("TLS_DH_anon_WITH_AES_128_CBC_SHA256", 108);
    public static final x6 Z = a("TLS_DH_anon_WITH_AES_256_CBC_SHA256", 109);
    public static final x6 a0 = a("TLS_RSA_WITH_CAMELLIA_256_CBC_SHA", UserInfomation.WEIGHT_DEFAULT_ENGLISH);
    public static final x6 b0 = a("TLS_DHE_DSS_WITH_CAMELLIA_256_CBC_SHA", 135);
    public static final x6 c0 = a("TLS_DHE_RSA_WITH_CAMELLIA_256_CBC_SHA", 136);
    public static final x6 d0 = a("TLS_PSK_WITH_RC4_128_SHA", OldToNewMotionPath.SPORT_TYPE_PILATES);
    public static final x6 e0 = a("TLS_PSK_WITH_3DES_EDE_CBC_SHA", OldToNewMotionPath.SPORT_TYPE_AEROBICS);
    public static final x6 f0 = a("TLS_PSK_WITH_AES_128_CBC_SHA", 140);
    public static final x6 g0 = a("TLS_PSK_WITH_AES_256_CBC_SHA", 141);
    public static final x6 h0 = a("TLS_RSA_WITH_SEED_CBC_SHA", 150);
    public static final x6 i0 = a("TLS_RSA_WITH_AES_128_GCM_SHA256", 156);
    public static final x6 j0 = a("TLS_RSA_WITH_AES_256_GCM_SHA384", 157);
    public static final x6 k0 = a("TLS_DHE_RSA_WITH_AES_128_GCM_SHA256", 158);
    public static final x6 l0 = a("TLS_DHE_RSA_WITH_AES_256_GCM_SHA384", 159);
    public static final x6 m0 = a("TLS_DHE_DSS_WITH_AES_128_GCM_SHA256", MachineControlPointResponse.OP_CODE_EXTENSION_SET_DYNAMIC_ENERGY);
    public static final x6 n0 = a("TLS_DHE_DSS_WITH_AES_256_GCM_SHA384", MachineControlPointResponse.OP_CODE_EXTENSION_SET_STEP_COUNT);
    public static final x6 o0 = a("TLS_DH_anon_WITH_AES_128_GCM_SHA256", MachineControlPointResponse.OP_CODE_EXTENSION_SET_SUPPRESS_AUTO_PAUSE);
    public static final x6 p0 = a("TLS_DH_anon_WITH_AES_256_GCM_SHA384", MachineControlPointResponse.OP_CODE_EXTENSION_SET_TARGETED_EXPENDED_ENERGY);
    public static final x6 q0 = a("TLS_EMPTY_RENEGOTIATION_INFO_SCSV", 255);
    public static final x6 r0 = a("TLS_FALLBACK_SCSV", 22016);
    public static final x6 s0 = a("TLS_ECDH_ECDSA_WITH_NULL_SHA", 49153);
    public static final x6 t0 = a("TLS_ECDH_ECDSA_WITH_RC4_128_SHA", 49154);
    public static final x6 u0 = a("TLS_ECDH_ECDSA_WITH_3DES_EDE_CBC_SHA", 49155);
    public static final x6 v0 = a("TLS_ECDH_ECDSA_WITH_AES_128_CBC_SHA", 49156);
    public static final x6 w0 = a("TLS_ECDH_ECDSA_WITH_AES_256_CBC_SHA", 49157);
    public static final x6 x0 = a("TLS_ECDHE_ECDSA_WITH_NULL_SHA", 49158);
    public static final x6 y0 = a("TLS_ECDHE_ECDSA_WITH_RC4_128_SHA", 49159);
    public static final x6 z0 = a("TLS_ECDHE_ECDSA_WITH_3DES_EDE_CBC_SHA", 49160);
    public static final x6 A0 = a("TLS_ECDHE_ECDSA_WITH_AES_128_CBC_SHA", 49161);
    public static final x6 B0 = a("TLS_ECDHE_ECDSA_WITH_AES_256_CBC_SHA", 49162);
    public static final x6 C0 = a("TLS_ECDH_RSA_WITH_NULL_SHA", 49163);
    public static final x6 D0 = a("TLS_ECDH_RSA_WITH_RC4_128_SHA", 49164);
    public static final x6 E0 = a("TLS_ECDH_RSA_WITH_3DES_EDE_CBC_SHA", 49165);
    public static final x6 F0 = a("TLS_ECDH_RSA_WITH_AES_128_CBC_SHA", 49166);
    public static final x6 G0 = a("TLS_ECDH_RSA_WITH_AES_256_CBC_SHA", 49167);
    public static final x6 H0 = a("TLS_ECDHE_RSA_WITH_NULL_SHA", 49168);
    public static final x6 I0 = a("TLS_ECDHE_RSA_WITH_RC4_128_SHA", 49169);
    public static final x6 J0 = a("TLS_ECDHE_RSA_WITH_3DES_EDE_CBC_SHA", 49170);
    public static final x6 K0 = a("TLS_ECDHE_RSA_WITH_AES_128_CBC_SHA", 49171);
    public static final x6 L0 = a("TLS_ECDHE_RSA_WITH_AES_256_CBC_SHA", 49172);
    public static final x6 M0 = a("TLS_ECDH_anon_WITH_NULL_SHA", 49173);
    public static final x6 N0 = a("TLS_ECDH_anon_WITH_RC4_128_SHA", 49174);
    public static final x6 O0 = a("TLS_ECDH_anon_WITH_3DES_EDE_CBC_SHA", 49175);
    public static final x6 P0 = a("TLS_ECDH_anon_WITH_AES_128_CBC_SHA", 49176);
    public static final x6 Q0 = a("TLS_ECDH_anon_WITH_AES_256_CBC_SHA", 49177);
    public static final x6 R0 = a("TLS_ECDHE_ECDSA_WITH_AES_128_CBC_SHA256", 49187);
    public static final x6 S0 = a("TLS_ECDHE_ECDSA_WITH_AES_256_CBC_SHA384", 49188);
    public static final x6 T0 = a("TLS_ECDH_ECDSA_WITH_AES_128_CBC_SHA256", 49189);
    public static final x6 U0 = a("TLS_ECDH_ECDSA_WITH_AES_256_CBC_SHA384", 49190);
    public static final x6 V0 = a("TLS_ECDHE_RSA_WITH_AES_128_CBC_SHA256", 49191);
    public static final x6 W0 = a("TLS_ECDHE_RSA_WITH_AES_256_CBC_SHA384", 49192);
    public static final x6 X0 = a("TLS_ECDH_RSA_WITH_AES_128_CBC_SHA256", 49193);
    public static final x6 Y0 = a("TLS_ECDH_RSA_WITH_AES_256_CBC_SHA384", 49194);
    public static final x6 Z0 = a("TLS_ECDHE_ECDSA_WITH_AES_128_GCM_SHA256", 49195);
    public static final x6 a1 = a("TLS_ECDHE_ECDSA_WITH_AES_256_GCM_SHA384", 49196);
    public static final x6 b1 = a("TLS_ECDH_ECDSA_WITH_AES_128_GCM_SHA256", 49197);
    public static final x6 c1 = a("TLS_ECDH_ECDSA_WITH_AES_256_GCM_SHA384", 49198);
    public static final x6 d1 = a("TLS_ECDHE_RSA_WITH_AES_128_GCM_SHA256", 49199);
    public static final x6 e1 = a("TLS_ECDHE_RSA_WITH_AES_256_GCM_SHA384", 49200);
    public static final x6 f1 = a("TLS_ECDH_RSA_WITH_AES_128_GCM_SHA256", 49201);
    public static final x6 g1 = a("TLS_ECDH_RSA_WITH_AES_256_GCM_SHA384", 49202);
    public static final x6 h1 = a("TLS_ECDHE_PSK_WITH_AES_128_CBC_SHA", 49205);
    public static final x6 i1 = a("TLS_ECDHE_PSK_WITH_AES_256_CBC_SHA", 49206);
    public static final x6 j1 = a("TLS_ECDHE_RSA_WITH_CHACHA20_POLY1305_SHA256", 52392);
    public static final x6 k1 = a("TLS_ECDHE_ECDSA_WITH_CHACHA20_POLY1305_SHA256", 52393);
    public static final x6 l1 = a("TLS_DHE_RSA_WITH_CHACHA20_POLY1305_SHA256", 52394);
    public static final x6 m1 = a("TLS_ECDHE_PSK_WITH_CHACHA20_POLY1305_SHA256", 52396);
    public static final x6 n1 = a("TLS_AES_128_GCM_SHA256", 4865);
    public static final x6 o1 = a("TLS_AES_256_GCM_SHA384", 4866);
    public static final x6 p1 = a("TLS_CHACHA20_POLY1305_SHA256", 4867);
    public static final x6 q1 = a("TLS_AES_128_CCM_SHA256", 4868);
    public static final x6 r1 = a("TLS_AES_128_CCM_8_SHA256", 4869);

    public String toString() {
        return this.f5569a;
    }

    public String a() {
        return this.f5569a;
    }

    public static String b(String str) {
        StringBuilder sb;
        if (str.startsWith("TLS_")) {
            sb = new StringBuilder("SSL_");
            sb.append(str.substring(4));
        } else {
            if (!str.startsWith("SSL_")) {
                return str;
            }
            StringBuilder sb2 = new StringBuilder("TLS_");
            sb2.append(str.substring(4));
            sb = sb2;
        }
        return sb.toString();
    }

    public static List<x6> a(String... strArr) {
        ArrayList arrayList = new ArrayList(strArr.length);
        for (String str : strArr) {
            arrayList.add(a(str));
        }
        return Collections.unmodifiableList(arrayList);
    }

    public static x6 a(String str, int i2) {
        x6 x6Var = new x6(str);
        c.put(str, x6Var);
        return x6Var;
    }

    public static x6 a(String str) {
        x6 x6Var;
        synchronized (x6.class) {
            Map<String, x6> map = c;
            x6Var = map.get(str);
            if (x6Var == null) {
                x6Var = map.get(b(str));
                if (x6Var == null) {
                    x6Var = new x6(str);
                }
                map.put(str, x6Var);
            }
        }
        return x6Var;
    }

    public static /* synthetic */ int a(String str, String str2) {
        int min = Math.min(str.length(), str2.length());
        for (int i2 = 4; i2 < min; i2++) {
            char charAt = str.charAt(i2);
            char charAt2 = str2.charAt(i2);
            if (charAt != charAt2) {
                return charAt < charAt2 ? -1 : 1;
            }
        }
        int length = str.length();
        int length2 = str2.length();
        if (length != length2) {
            return length < length2 ? -1 : 1;
        }
        return 0;
    }

    public x6(String str) {
        str.getClass();
        this.f5569a = str;
    }
}
