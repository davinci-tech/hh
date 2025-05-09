package defpackage;

import com.autonavi.amap.mapcore.tools.GlMapUtil;
import com.huawei.hihealthservice.old.model.OldToNewMotionPath;
import com.huawei.hms.network.base.common.trans.FileBinary;
import com.huawei.openalliance.ad.constant.ErrorCode;
import com.huawei.openalliance.ad.constant.MetaCreativeType;
import com.huawei.openalliance.ad.constant.MimeType;
import com.huawei.pluginfitnessadvice.FitWorkout;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes7.dex */
public class uxm {
    private static final Map<Integer, b> e = new ConcurrentHashMap();

    static {
        b(0, "text/plain", "txt", true);
        a(16, "application/cose; cose-type=\"cose-encrypt0\"", "cbor");
        a(17, "application/cose; cose-type=\"cose-mac0\"", "cbor");
        a(18, "application/cose; cose-type=\"cose-sign1\"", "cbor");
        a(19, "application/ace+cbor", "cbor");
        a(21, MimeType.GIF, MetaCreativeType.GIF);
        a(22, MimeType.JPEG, "jpeg");
        a(23, "image/png", "png");
        b(40, "application/link-format", "wlnk", false);
        b(41, "application/xml", "xml", false);
        a(42, FileBinary.HEAD_VALUE_CONTENT_TYPE_OCTET_STREAM, "bin");
        b(46, "application/xmpp+xml", "xmpp", false);
        a(47, "application/exi", "exi");
        b(50, "application/json", "json", false);
        b(51, "application/json-patch+json", "json", false);
        b(52, "application/merge-patch+json", "json", false);
        a(60, "application/cbor", "cbor");
        a(61, "application/cwt", "cwt");
        a(62, "application/multipart-core", "part");
        a(63, "application/cbor-seq", "cbor");
        a(96, "application/cose; cose-type=\"cose-encrypt\"", "cbor");
        a(97, "application/cose; cose-type=\"cose-mac\"", "cbor");
        a(98, "application/cose; cose-type=\"cose-sign\"", "cbor");
        a(101, "application/cose-key", "cbor");
        a(102, "application/cose-key-set", "cbor");
        b(110, "application/senml+json", "json", false);
        b(111, "application/sensml+json", "json", false);
        a(112, "application/senml+cbor", "cbor");
        a(113, "application/sensml+cbor", "cbor");
        a(114, "application/senml+exi", "exi");
        a(115, "application/sensml+exi", "exi");
        b(256, "application/coap-group+json", "json", false);
        a(OldToNewMotionPath.SPORT_TYPE_BASKETBALL, "application/dots+cbor", "cbor");
        a(272, "application/missing-blocks+cbor-seq", "cbor");
        a(OldToNewMotionPath.SPORT_TYPE_CROSS_COUNTRY_RACE, "application/pkcs7-mime; smime-type=\"server-generated-key\"", "pkcs");
        a(281, "application/pkcs7-mime; smime-type=\"certs-only\"", "pkcs");
        a(FitWorkout.CATEGORY_REAL_SCENE, "application/pkcs8", "pkcs");
        a(285, "application/csattrs", "csattrs");
        a(286, "application/pkcs10", "pkcs");
        a(287, "application/pkix-cert", "pkix");
        b(310, "application/senml+xml", "xml", false);
        b(311, "application/sensml+xml", "xml", false);
        b(GlMapUtil.DEVICE_DISPLAY_DPI_HIGH, "application/senml-etch+json", "json", false);
        a(322, "application/senml-etch+cbor", "cbor");
        b(ErrorCode.ERROR_CODE_NO_PACKAGE, "application/td+json", "json", false);
        a(10000, "application/vnd.ocf+cbor", "cbor");
        a(10001, "application/oscore", "oscore");
        b(10002, "application/javascript", "js", false);
        a(11542, "application/vnd.oma.lwm2m+tlv", "tlv");
        b(11543, "application/vnd.oma.lwm2m+json", "json", false);
        a(11544, "application/vnd.oma.lwm2m+cbor", "cbor");
        b(20000, "text/css", "css", false);
        b(30000, "image/svg+xml", "xml", false);
    }

    public static boolean e(int i) {
        b bVar = e.get(Integer.valueOf(i));
        if (bVar != null) {
            return bVar.e();
        }
        return false;
    }

    public static String a(int i) {
        if (i == -1) {
            return "undefined";
        }
        b bVar = e.get(Integer.valueOf(i));
        if (bVar != null) {
            return bVar.b();
        }
        return "unknown/" + i;
    }

    private static void a(int i, String str, String str2) {
        c(new b(Integer.valueOf(i), str, str2));
    }

    private static void b(int i, String str, String str2, boolean z) {
        c(new b(Integer.valueOf(i), str, str2, z));
    }

    public static void c(b bVar) {
        e.put(bVar.c(), bVar);
    }

    public static class b {

        /* renamed from: a, reason: collision with root package name */
        private final boolean f17576a;
        private final String b;
        private final Integer c;
        private final String d;
        private final boolean e;

        public b(Integer num, String str, String str2) {
            if (num == null) {
                throw new NullPointerException("type must not be null!");
            }
            if (str == null) {
                throw new NullPointerException("mime must not be null!");
            }
            if (str2 == null) {
                throw new NullPointerException("file extension must not be null!");
            }
            this.c = num;
            this.d = str;
            this.b = str2;
            this.f17576a = false;
            this.e = false;
        }

        public b(Integer num, String str, String str2, boolean z) {
            if (num == null) {
                throw new NullPointerException("type must not be null!");
            }
            if (str == null) {
                throw new NullPointerException("mime must not be null!");
            }
            if (str2 == null) {
                throw new NullPointerException("file extension must not be null!");
            }
            this.c = num;
            this.d = str;
            this.b = str2;
            this.f17576a = true;
            this.e = z;
        }

        public Integer c() {
            return this.c;
        }

        public String b() {
            return this.d;
        }

        public boolean e() {
            return this.f17576a;
        }
    }
}
