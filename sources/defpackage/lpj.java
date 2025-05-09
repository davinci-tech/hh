package defpackage;

import android.util.JsonReader;
import java.io.IOException;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

/* loaded from: classes5.dex */
public class lpj {
    private static final String e = "TokenInfo";
    private String c = null;
    private int b = 0;

    public String b() {
        return this.c;
    }

    private void a(String str) {
        this.c = str;
    }

    private void c(String str) {
        try {
            this.b = Integer.parseInt(str);
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
                if ("token".equals(attributeValue)) {
                    a(attributeValue2);
                } else if ("validity".equals(attributeValue)) {
                    c(attributeValue2);
                } else if (loq.b.booleanValue()) {
                    loq.d(e, "expected tag = " + attributeValue);
                }
            }
        }
    }

    public void bYW_(JsonReader jsonReader) throws IOException, IllegalStateException {
        if (jsonReader == null) {
            loq.b(e, "parserJsonItems reader == null");
            return;
        }
        jsonReader.beginObject();
        while (jsonReader.hasNext()) {
            String nextName = jsonReader.nextName();
            nextName.hashCode();
            if (nextName.equals("validity")) {
                c(String.valueOf(jsonReader.nextInt()));
            } else if (nextName.equals("token")) {
                a(jsonReader.nextString());
            } else {
                loq.d(e, "unexpected tag");
                jsonReader.skipValue();
            }
        }
        jsonReader.endObject();
        loq.d(e, "token finish");
    }
}
