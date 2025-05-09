package defpackage;

import com.huawei.ui.device.declaration.xmlparser.parser.XmlParser;
import java.io.IOException;
import java.io.InputStream;
import org.xmlpull.v1.XmlPullParserException;

/* loaded from: classes6.dex */
public class nzq implements XmlParser<nzf> {
    private XmlParser<nzf> e = new nzs();

    @Override // com.huawei.ui.device.declaration.xmlparser.parser.XmlParser
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public nzf parse(InputStream inputStream) throws XmlPullParserException, IOException {
        if (inputStream == null) {
            return new nzf();
        }
        return this.e.parse(inputStream);
    }
}
