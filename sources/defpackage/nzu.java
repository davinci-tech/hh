package defpackage;

import com.huawei.ui.device.declaration.xmlparser.parser.XmlParser;
import java.io.IOException;
import java.io.InputStream;
import org.xmlpull.v1.XmlPullParserException;

/* loaded from: classes6.dex */
public class nzu implements XmlParser<nze> {
    private XmlParser<nze> c = new nzv();

    @Override // com.huawei.ui.device.declaration.xmlparser.parser.XmlParser
    /* renamed from: b, reason: merged with bridge method [inline-methods] */
    public nze parse(InputStream inputStream) throws XmlPullParserException, IOException {
        if (inputStream == null) {
            return new nze();
        }
        return this.c.parse(inputStream);
    }
}
