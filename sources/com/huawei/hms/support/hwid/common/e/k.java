package com.huawei.hms.support.hwid.common.e;

import android.util.Xml;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.OutputStream;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;
import org.xmlpull.v1.XmlSerializer;

/* loaded from: classes9.dex */
public class k {
    public static void a(XmlSerializer xmlSerializer, String str, String str2) throws IllegalArgumentException, IllegalStateException, IOException {
        if (str2 == null || xmlSerializer == null || str == null) {
            return;
        }
        xmlSerializer.startTag(null, str).text(str2).endTag(null, str);
    }

    public static XmlSerializer a(OutputStream outputStream) throws IllegalArgumentException, IllegalStateException, IOException {
        XmlSerializer newSerializer = Xml.newSerializer();
        newSerializer.setOutput(outputStream, "UTF-8");
        return newSerializer;
    }

    public static XmlPullParser a(byte[] bArr) throws XmlPullParserException {
        XmlPullParser newPullParser = XmlPullParserFactory.newInstance().newPullParser();
        newPullParser.setInput(new ByteArrayInputStream(bArr), "UTF-8");
        return newPullParser;
    }
}
