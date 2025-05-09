package com.huawei.secure.android.common.xml;

import android.util.Xml;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

/* loaded from: classes9.dex */
public class XmlNewPullParserSecurity {
    public static XmlPullParser getInstance() throws XmlPullParserException {
        XmlPullParser newPullParser = Xml.newPullParser();
        newPullParser.setFeature("http://xmlpull.org/v1/doc/features.html#process-docdecl", false);
        newPullParser.setFeature("http://xmlpull.org/v1/doc/features.html#process-namespaces", false);
        return newPullParser;
    }
}
