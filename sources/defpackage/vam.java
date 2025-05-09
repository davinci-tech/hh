package defpackage;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.List;
import org.eclipse.californium.elements.UDPConnector;
import org.slf4j.Logger;

/* loaded from: classes7.dex */
public class vam extends UDPConnector {
    public static final Logger i = vha.a((Class<?>) vam.class);
    private boolean f;
    private InetAddress h;
    private List<e> j;
    private NetworkInterface k;

    public boolean b() {
        return this.f15868a;
    }

    /* JADX WARN: Removed duplicated region for block: B:20:0x0082 A[Catch: all -> 0x0164, TRY_LEAVE, TryCatch #0 {, blocks: (B:3:0x0001, B:8:0x0007, B:10:0x0018, B:11:0x0036, B:13:0x003a, B:16:0x0040, B:17:0x0075, B:18:0x007b, B:20:0x0082, B:22:0x0088, B:32:0x008e, B:24:0x00b5, B:27:0x00bb, B:37:0x00d3, B:39:0x00de, B:41:0x00e4, B:43:0x00ee, B:44:0x00f5, B:45:0x00ea, B:47:0x0116, B:48:0x00f6, B:50:0x00fe, B:52:0x0104, B:54:0x010e, B:55:0x0115, B:56:0x010a, B:59:0x0117, B:61:0x011e, B:63:0x0127, B:68:0x0052, B:69:0x005a, B:72:0x005e, B:75:0x006e, B:78:0x0141, B:79:0x0151, B:81:0x0153, B:82:0x0163), top: B:2:0x0001, inners: #1, #3, #4, #5, #6 }] */
    @Override // org.eclipse.californium.elements.UDPConnector, org.eclipse.californium.elements.Connector
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void start() throws java.io.IOException {
        /*
            Method dump skipped, instructions count: 359
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.vam.start():void");
    }

    static class e {
        private final NetworkInterface d;
        private final InetAddress e;
    }
}
