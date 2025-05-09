package defpackage;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

/* loaded from: classes5.dex */
public class lpt {
    private lph b = null;

    /* renamed from: a, reason: collision with root package name */
    private lpk f14829a = null;
    private lpj c = null;
    private lpf e = null;

    public void e(lph lphVar) {
        this.b = lphVar;
    }

    public void c(lpk lpkVar) {
        this.f14829a = lpkVar;
    }

    public lpj a() {
        return this.c;
    }

    public void d(lpj lpjVar) {
        this.c = lpjVar;
    }

    public void b(lpf lpfVar) {
        this.e = lpfVar;
    }

    public void d(String str) {
        try {
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(str.getBytes("UTF-8"));
            try {
                XmlPullParser newPullParser = XmlPullParserFactory.newInstance().newPullParser();
                newPullParser.setInput(byteArrayInputStream, "UTF-8");
                for (int eventType = newPullParser.getEventType(); eventType != 1; eventType = newPullParser.next()) {
                    if (eventType == 2 && "characteristic".equals(newPullParser.getName())) {
                        String attributeValue = newPullParser.getAttributeValue(0);
                        if ("EAP-AKA".equals(attributeValue)) {
                            lph lphVar = new lph();
                            lphVar.a(newPullParser);
                            e(lphVar);
                        } else if ("VERS".equals(attributeValue)) {
                            lpk lpkVar = new lpk();
                            lpkVar.e(newPullParser);
                            c(lpkVar);
                        } else if ("TOKEN".equals(attributeValue)) {
                            lpj lpjVar = new lpj();
                            lpjVar.c(newPullParser);
                            d(lpjVar);
                        } else if (!"APPLICATION".equals(attributeValue)) {
                            loq.d("EntitleResponse", "unexpected tag type =" + attributeValue);
                        } else {
                            lpf lpfVar = new lpf();
                            lpfVar.c(newPullParser);
                            b(lpfVar);
                        }
                    }
                }
                byteArrayInputStream.close();
            } catch (Throwable th) {
                try {
                    byteArrayInputStream.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
                throw th;
            }
        } catch (IOException | XmlPullParserException unused) {
            loq.b("EntitleResponse", "pullParserXml->XmlPullParserException error");
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x006e A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:19:0x004e A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void e(java.lang.String r6) {
        /*
            r5 = this;
            android.util.JsonReader r0 = new android.util.JsonReader
            java.io.StringReader r1 = new java.io.StringReader
            r1.<init>(r6)
            r0.<init>(r1)
            r0.beginObject()     // Catch: java.lang.Throwable -> L81
        Ld:
            boolean r6 = r0.hasNext()     // Catch: java.lang.Throwable -> L81
            if (r6 == 0) goto L7a
            java.lang.String r6 = r0.nextName()     // Catch: java.lang.Throwable -> L81
            int r1 = r6.hashCode()     // Catch: java.lang.Throwable -> L81
            r2 = 2662736(0x28a150, float:3.731288E-39)
            r3 = 2
            r4 = 1
            if (r1 == r2) goto L41
            r2 = 80988633(0x4d3c9d9, float:4.979123E-36)
            if (r1 == r2) goto L37
            r2 = 1597464377(0x5f375f39, float:1.3213343E19)
            if (r1 == r2) goto L2d
            goto L4b
        L2d:
            java.lang.String r1 = "AccessControl"
            boolean r6 = r6.equals(r1)     // Catch: java.lang.Throwable -> L81
            if (r6 == 0) goto L4b
            r6 = r3
            goto L4c
        L37:
            java.lang.String r1 = "Token"
            boolean r6 = r6.equals(r1)     // Catch: java.lang.Throwable -> L81
            if (r6 == 0) goto L4b
            r6 = r4
            goto L4c
        L41:
            java.lang.String r1 = "Vers"
            boolean r6 = r6.equals(r1)     // Catch: java.lang.Throwable -> L81
            if (r6 == 0) goto L4b
            r6 = 0
            goto L4c
        L4b:
            r6 = -1
        L4c:
            if (r6 == 0) goto L6e
            if (r6 == r4) goto L62
            if (r6 == r3) goto L56
            r0.skipValue()     // Catch: java.lang.Throwable -> L81
            goto Ld
        L56:
            lpf r6 = new lpf     // Catch: java.lang.Throwable -> L81
            r6.<init>()     // Catch: java.lang.Throwable -> L81
            r6.bYV_(r0)     // Catch: java.lang.Throwable -> L81
            r5.b(r6)     // Catch: java.lang.Throwable -> L81
            goto Ld
        L62:
            lpj r6 = new lpj     // Catch: java.lang.Throwable -> L81
            r6.<init>()     // Catch: java.lang.Throwable -> L81
            r6.bYW_(r0)     // Catch: java.lang.Throwable -> L81
            r5.d(r6)     // Catch: java.lang.Throwable -> L81
            goto Ld
        L6e:
            lpk r6 = new lpk     // Catch: java.lang.Throwable -> L81
            r6.<init>()     // Catch: java.lang.Throwable -> L81
            r6.bYX_(r0)     // Catch: java.lang.Throwable -> L81
            r5.c(r6)     // Catch: java.lang.Throwable -> L81
            goto Ld
        L7a:
            r0.endObject()     // Catch: java.lang.Throwable -> L81
            r0.close()     // Catch: java.lang.Throwable -> L81
            goto L88
        L81:
            java.lang.String r6 = "EntitleResponse"
            java.lang.String r0 = "pullParserJson error"
            defpackage.loq.b(r6, r0)
        L88:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.lpt.e(java.lang.String):void");
    }
}
