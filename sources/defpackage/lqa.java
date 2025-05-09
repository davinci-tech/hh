package defpackage;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

/* loaded from: classes5.dex */
public class lqa {
    private static final String e = "XmlUtils";

    public static boolean b(XmlPullParser xmlPullParser) {
        try {
            if (xmlPullParser.getEventType() == 3) {
                return "characteristic".equals(xmlPullParser.getName());
            }
            return false;
        } catch (XmlPullParserException unused) {
            loq.b(e, "parse xml error");
            return false;
        }
    }
}
