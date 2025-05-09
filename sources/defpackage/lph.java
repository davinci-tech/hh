package defpackage;

import java.io.IOException;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

/* loaded from: classes5.dex */
public class lph {
    private String c = null;

    public void e(String str) {
        this.c = str;
    }

    public void a(XmlPullParser xmlPullParser) throws IOException, XmlPullParserException {
        while (true) {
            xmlPullParser.next();
            if (lqa.b(xmlPullParser) || xmlPullParser.getEventType() == 1) {
                return;
            }
            if (xmlPullParser.getEventType() == 2 && "parm".equals(xmlPullParser.getName()) && xmlPullParser.getAttributeCount() >= 2) {
                String attributeValue = xmlPullParser.getAttributeValue(0);
                String attributeValue2 = xmlPullParser.getAttributeValue(1);
                if ("EAP-AKA Challenge".equals(attributeValue)) {
                    e(attributeValue2);
                }
            }
        }
    }
}
