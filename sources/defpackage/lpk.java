package defpackage;

import android.util.JsonReader;
import java.io.IOException;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

/* loaded from: classes5.dex */
public class lpk {
    private static final String c = "VersInfo";
    private int e = 0;
    private int b = 0;

    private void c(String str) {
        try {
            this.e = Integer.parseInt(str);
        } catch (NumberFormatException unused) {
        }
    }

    private void b(String str) {
        try {
            this.b = Integer.parseInt(str);
        } catch (NumberFormatException unused) {
        }
    }

    public void e(XmlPullParser xmlPullParser) throws IOException, XmlPullParserException {
        while (true) {
            xmlPullParser.next();
            if (lqa.b(xmlPullParser) || xmlPullParser.getEventType() == 1) {
                return;
            }
            if (xmlPullParser.getEventType() == 2 && "parm".equals(xmlPullParser.getName()) && xmlPullParser.getAttributeCount() >= 2) {
                String attributeValue = xmlPullParser.getAttributeValue(0);
                String attributeValue2 = xmlPullParser.getAttributeValue(1);
                if ("version".equals(attributeValue)) {
                    c(attributeValue2);
                } else if ("validity".equals(attributeValue)) {
                    b(attributeValue2);
                } else {
                    loq.d(c, "unexpected tag");
                }
            }
        }
    }

    public void bYX_(JsonReader jsonReader) throws IOException, IllegalStateException {
        if (jsonReader == null) {
            loq.b(c, "parserJsonItems reader == null");
            return;
        }
        jsonReader.beginObject();
        while (jsonReader.hasNext()) {
            String nextName = jsonReader.nextName();
            nextName.hashCode();
            if (nextName.equals("validity")) {
                b(String.valueOf(jsonReader.nextInt()));
            } else if (nextName.equals("version")) {
                c(String.valueOf(jsonReader.nextInt()));
            } else {
                loq.d(c, "unexpected tag");
                jsonReader.skipValue();
            }
        }
        jsonReader.endObject();
        loq.d(c, "vers finish");
    }
}
