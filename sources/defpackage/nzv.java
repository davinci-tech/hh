package defpackage;

import android.text.TextUtils;
import android.util.Xml;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.device.declaration.xmlparser.parser.XmlParser;
import java.io.IOException;
import java.io.InputStream;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

/* loaded from: classes6.dex */
public class nzv implements XmlParser<nze> {
    private XmlPullParser c;

    @Override // com.huawei.ui.device.declaration.xmlparser.parser.XmlParser
    /* renamed from: d, reason: merged with bridge method [inline-methods] */
    public nze parse(InputStream inputStream) throws XmlPullParserException, IOException {
        if (inputStream == null) {
            throw new NullPointerException("parameter 'xmlInputStream' is null.");
        }
        a(inputStream);
        nze c = c();
        inputStream.close();
        return c;
    }

    private nze c() throws XmlPullParserException, IOException {
        nze nzeVar = new nze();
        int eventType = this.c.getEventType();
        String str = "";
        String str2 = "";
        while (eventType != 1) {
            String name = this.c.getName();
            if (eventType == 0) {
                LogUtil.a("ResourceParserImpl", "parse xml begin.");
            } else if (eventType != 2) {
                if (eventType == 3) {
                    LogUtil.a("ResourceParserImpl", "parse xml end.");
                    str = null;
                    str2 = null;
                } else if (eventType == 4) {
                    boolean z = !TextUtils.isEmpty(str2);
                    if (z) {
                        str = this.c.getText();
                    }
                    boolean isEmpty = TextUtils.isEmpty(str);
                    if (z && (!isEmpty)) {
                        nzeVar.b(str2, str);
                    }
                } else {
                    LogUtil.a("ResourceParserImpl", "default case.");
                }
            } else if ("string".equals(name)) {
                str2 = this.c.getAttributeValue(0);
            }
            eventType = this.c.next();
        }
        return nzeVar;
    }

    private void a(InputStream inputStream) throws XmlPullParserException {
        XmlPullParser newPullParser = Xml.newPullParser();
        this.c = newPullParser;
        newPullParser.setFeature("http://xmlpull.org/v1/doc/features.html#process-namespaces", false);
        this.c.setInput(inputStream, "UTF-8");
    }
}
