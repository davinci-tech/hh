package defpackage;

import android.graphics.Color;
import org.xmlpull.v1.XmlPullParser;

/* loaded from: classes8.dex */
public class aes {
    public static boolean a(XmlPullParser xmlPullParser, String str) {
        return xmlPullParser.getAttributeValue("", str) != null;
    }

    public static float d(XmlPullParser xmlPullParser, String str, float f) {
        String attributeValue = xmlPullParser.getAttributeValue("", str);
        return attributeValue == null ? f : Float.parseFloat(attributeValue);
    }

    public static boolean b(XmlPullParser xmlPullParser, String str, boolean z) {
        String attributeValue = xmlPullParser.getAttributeValue("", str);
        return attributeValue == null ? z : Boolean.parseBoolean(attributeValue);
    }

    public static int b(XmlPullParser xmlPullParser, String str, int i) {
        String attributeValue = xmlPullParser.getAttributeValue("", str);
        return attributeValue == null ? i : Integer.parseInt(attributeValue);
    }

    public static aek c(XmlPullParser xmlPullParser, String str, int i) {
        String attributeValue = xmlPullParser.getAttributeValue("", str);
        if (attributeValue != null) {
            return aek.b(Color.parseColor(attributeValue));
        }
        return aek.b(i);
    }

    public static String b(XmlPullParser xmlPullParser, String str) {
        String attributeValue = xmlPullParser.getAttributeValue("", str);
        if (attributeValue == null) {
            return null;
        }
        return attributeValue;
    }

    public static float a(XmlPullParser xmlPullParser, String str, float f) {
        String attributeValue = xmlPullParser.getAttributeValue("", str);
        if (attributeValue == null) {
            return f;
        }
        if (attributeValue.contains("px")) {
            attributeValue = attributeValue.substring(0, attributeValue.indexOf("px"));
        } else if (attributeValue.contains("dp")) {
            attributeValue = attributeValue.substring(0, attributeValue.indexOf("dp"));
        }
        return Float.parseFloat(attributeValue);
    }

    public static String d(XmlPullParser xmlPullParser) {
        return "XML file line #" + xmlPullParser.getLineNumber();
    }
}
