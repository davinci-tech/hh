package defpackage;

import android.text.TextUtils;
import android.util.JsonReader;
import java.io.IOException;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

/* loaded from: classes5.dex */
public class lpf {
    private static final String c = "ApplicationInfo";

    /* renamed from: a, reason: collision with root package name */
    private String f14826a = null;
    private String j = null;
    private int d = -1;
    private String g = null;
    private String f = null;
    private String e = null;
    private int i = -1;
    private int b = -1;
    private int h = -1;

    public void d(String str) {
        this.f14826a = str;
    }

    public void e(String str) {
        this.j = str;
    }

    private void b(String str) {
        try {
            this.d = Integer.parseInt(str);
        } catch (NumberFormatException unused) {
        }
    }

    private void i(String str) {
        this.g = str;
    }

    private void h(String str) {
        this.f = str;
    }

    private void c(String str) {
        this.e = str;
    }

    private void g(String str) {
        try {
            this.i = Integer.parseInt(str);
        } catch (NumberFormatException unused) {
        }
    }

    private void a(String str) {
        if (TextUtils.isEmpty(str)) {
            loq.d(c, "empty addrstatus, set to 2");
            this.b = 2;
        } else {
            try {
                this.b = Integer.parseInt(str);
            } catch (NumberFormatException unused) {
            }
        }
    }

    private void f(String str) {
        try {
            this.h = Integer.parseInt(str);
        } catch (NumberFormatException unused) {
        }
    }

    public void c(XmlPullParser xmlPullParser) throws IOException, XmlPullParserException {
        while (true) {
            xmlPullParser.next();
            if (lqa.b(xmlPullParser) || xmlPullParser.getEventType() == 1) {
                return;
            }
            if (xmlPullParser.getEventType() == 2 && "parm".equals(xmlPullParser.getName()) && xmlPullParser.getAttributeCount() >= 2) {
                String attributeValue = xmlPullParser.getAttributeValue(0);
                String attributeValue2 = xmlPullParser.getAttributeValue(1);
                if (loq.b.booleanValue()) {
                    loq.d(c, "name =" + attributeValue + " value =" + attributeValue2);
                }
                if ("AppID".equals(attributeValue)) {
                    d(attributeValue2);
                } else if ("Name".equals(attributeValue)) {
                    e(attributeValue2);
                } else if ("EntitlementStatus".equals(attributeValue)) {
                    b(attributeValue2);
                } else if ("AddrUpdate_URL".equals(attributeValue) || "ServiceFlow_URL".equals(attributeValue)) {
                    i(attributeValue2);
                } else if ("AddrUpdate_POST_Data".equals(attributeValue) || "ServiceFlow_POST_Data".equals(attributeValue)) {
                    h(attributeValue2);
                } else if ("AddrStatus".equals(attributeValue)) {
                    a(attributeValue2);
                } else if ("TC_Status".equals(attributeValue)) {
                    g(attributeValue2);
                } else if ("ProvStatus".equals(attributeValue)) {
                    f(attributeValue2);
                } else if ("MessageForIncompatible".equals(attributeValue)) {
                    c(attributeValue2);
                } else {
                    loq.d(c, "unexpected type = " + attributeValue);
                }
            }
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public void bYV_(JsonReader jsonReader) throws IOException, IllegalStateException {
        char c2;
        if (jsonReader == null) {
            loq.b(c, "parserJsonItems reader == null");
            return;
        }
        jsonReader.beginObject();
        while (jsonReader.hasNext()) {
            String nextName = jsonReader.nextName();
            nextName.hashCode();
            switch (nextName.hashCode()) {
                case -2031513485:
                    if (nextName.equals("ServiceFlow_URL")) {
                        c2 = 0;
                        break;
                    }
                    c2 = 65535;
                    break;
                case -1475363997:
                    if (nextName.equals("AddrStatus")) {
                        c2 = 1;
                        break;
                    }
                    c2 = 65535;
                    break;
                case -1406300604:
                    if (nextName.equals("AddrUpdate_POST_Data")) {
                        c2 = 2;
                        break;
                    }
                    c2 = 65535;
                    break;
                case -940502177:
                    if (nextName.equals("EntitlementStatus")) {
                        c2 = 3;
                        break;
                    }
                    c2 = 65535;
                    break;
                case 2420395:
                    if (nextName.equals("Name")) {
                        c2 = 4;
                        break;
                    }
                    c2 = 65535;
                    break;
                case 201712610:
                    if (nextName.equals("TC_Status")) {
                        c2 = 5;
                        break;
                    }
                    c2 = 65535;
                    break;
                case 586042413:
                    if (nextName.equals("ServiceFlow_POST_Data")) {
                        c2 = 6;
                        break;
                    }
                    c2 = 65535;
                    break;
                case 955117514:
                    if (nextName.equals("AddrUpdate_URL")) {
                        c2 = 7;
                        break;
                    }
                    c2 = 65535;
                    break;
                case 1544803905:
                    if (nextName.equals("default")) {
                        c2 = '\b';
                        break;
                    }
                    c2 = 65535;
                    break;
                case 1583433947:
                    if (nextName.equals("ProvStatus")) {
                        c2 = '\t';
                        break;
                    }
                    c2 = 65535;
                    break;
                case 2133913147:
                    if (nextName.equals("MessageForIncompatible")) {
                        c2 = '\n';
                        break;
                    }
                    c2 = 65535;
                    break;
                default:
                    c2 = 65535;
                    break;
            }
            switch (c2) {
                case 0:
                case 7:
                    i(jsonReader.nextString());
                    break;
                case 1:
                    a(String.valueOf(jsonReader.nextInt()));
                    break;
                case 2:
                case 6:
                    h(jsonReader.nextString());
                    break;
                case 3:
                    b(jsonReader.nextString());
                    break;
                case 4:
                    e(String.valueOf(jsonReader.nextInt()));
                    break;
                case 5:
                    g(String.valueOf(jsonReader.nextInt()));
                    break;
                case '\b':
                    bYU_(jsonReader);
                    break;
                case '\t':
                    f(String.valueOf(jsonReader.nextInt()));
                    break;
                case '\n':
                    c(String.valueOf(jsonReader.nextInt()));
                    break;
                default:
                    loq.d(c, "unexpected tag");
                    jsonReader.skipValue();
                    break;
            }
        }
        jsonReader.endObject();
        loq.d(c, "token finish");
    }

    private void bYU_(JsonReader jsonReader) throws IOException, IllegalStateException {
        if (jsonReader == null) {
            loq.b(c, "parserJsonDefaultItems reader == null");
            return;
        }
        jsonReader.beginObject();
        while (jsonReader.hasNext()) {
            String nextName = jsonReader.nextName();
            nextName.hashCode();
            if (nextName.equals("appId")) {
                jsonReader.beginArray();
                while (jsonReader.hasNext()) {
                    d(jsonReader.nextString());
                }
                jsonReader.endArray();
            } else {
                loq.d(c, "unexpected tag");
                jsonReader.skipValue();
            }
        }
        jsonReader.endObject();
    }
}
