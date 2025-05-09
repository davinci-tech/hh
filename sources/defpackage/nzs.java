package defpackage;

import android.text.TextUtils;
import android.util.Xml;
import com.huawei.hms.network.inner.api.NetworkService;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.device.declaration.xmlparser.DeclarationConstants;
import com.huawei.ui.device.declaration.xmlparser.parser.BaseDeclarationParser;
import health.compact.a.CommonUtil;
import java.io.IOException;
import java.io.InputStream;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

/* loaded from: classes6.dex */
public class nzs extends BaseDeclarationParser {
    private XmlPullParser d;
    private nzc e = null;

    @Override // com.huawei.ui.device.declaration.xmlparser.parser.XmlParser
    /* renamed from: b, reason: merged with bridge method [inline-methods] */
    public nzf parse(InputStream inputStream) throws XmlPullParserException, IOException {
        if (inputStream == null) {
            throw new NullPointerException("parameter 'xmlInputStream' is null.");
        }
        d(inputStream);
        try {
            try {
                return c();
            } finally {
                try {
                    inputStream.close();
                } catch (IOException unused) {
                    LogUtil.b("DeclarationParserImpl", "parse: parse xml occurred IOException.");
                }
            }
        } catch (IOException | XmlPullParserException e) {
            LogUtil.b("DeclarationParserImpl", "parse: parse xml occurred XmlPullParserException or IOException.");
            throw e;
        }
    }

    private nzf c() throws XmlPullParserException, IOException {
        nzf nzfVar = new nzf();
        int eventType = this.d.getEventType();
        while (eventType != 1) {
            String name = this.d.getName();
            if (eventType == 2) {
                c(nzfVar, name);
            } else {
                LogUtil.a("DeclarationParserImpl", "else case.");
            }
            eventType = this.d.next();
        }
        return nzfVar;
    }

    private void c(nzf nzfVar, String str) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("DeclarationParserImpl", "name is null or empty.");
            return;
        }
        if ("declaration".equals(str)) {
            nzf parseDeclaration = parseDeclaration();
            nzfVar.d(parseDeclaration.j());
            nzfVar.b(parseDeclaration.f());
            nzfVar.a(parseDeclaration.a());
            nzfVar.a(parseDeclaration.g());
            nzfVar.c(parseDeclaration.k());
            nzfVar.e(parseDeclaration.c());
            this.e = nzfVar;
            return;
        }
        if ("title".equals(str)) {
            nzj parseTitle = parseTitle();
            nzfVar.a(parseTitle);
            this.e = parseTitle;
            return;
        }
        if ("content".equals(str)) {
            nzd parseContent = parseContent();
            nzfVar.e(parseContent);
            this.e = parseContent;
            return;
        }
        if ("part".equals(str)) {
            nzn parsePart = parsePart();
            nzfVar.b().b(parsePart);
            this.e = parsePart;
        } else {
            if ("string".equals(str)) {
                parseString(this.e);
                return;
            }
            if ("placeholder".equals(str)) {
                parsePlaceholder(this.e);
            } else if (NetworkService.Constants.CONFIG_SERVICE.equals(str)) {
                parseConfig(this.e);
            } else {
                LogUtil.h("DeclarationParserImpl", "name not contains.");
            }
        }
    }

    @Override // com.huawei.ui.device.declaration.xmlparser.parser.BaseDeclarationParser
    public nzf parseDeclaration() {
        nzf nzfVar = new nzf();
        nzfVar.d(c("product_type"));
        nzfVar.b(c("scope"));
        nzfVar.a(c("feature_id"));
        nzfVar.a(CommonUtil.e(c("index"), 0));
        nzfVar.c(c("xml_version"));
        nzfVar.e(c("content_version"));
        return nzfVar;
    }

    @Override // com.huawei.ui.device.declaration.xmlparser.parser.BaseDeclarationParser
    public nzj parseTitle() {
        nzj nzjVar = new nzj();
        String c = c("checkbox");
        if (DeclarationConstants.Position.START.name().equalsIgnoreCase(c)) {
            nzjVar.c(DeclarationConstants.Position.START);
        } else if (DeclarationConstants.Position.END.name().equalsIgnoreCase(c)) {
            nzjVar.c(DeclarationConstants.Position.END);
        } else {
            nzjVar.c(DeclarationConstants.Position.EMPTY);
        }
        nzjVar.d(Boolean.parseBoolean(c("checked")));
        return nzjVar;
    }

    @Override // com.huawei.ui.device.declaration.xmlparser.parser.BaseDeclarationParser
    public nzd parseContent() {
        nzd nzdVar = new nzd();
        nzdVar.d(CommonUtil.e(c("part_count"), 0));
        return nzdVar;
    }

    @Override // com.huawei.ui.device.declaration.xmlparser.parser.BaseDeclarationParser
    public nzn parsePart() {
        String c = c("type");
        if (DeclarationConstants.PartType.PARAGRAPH.name().equalsIgnoreCase(c)) {
            nzl nzlVar = new nzl();
            nzlVar.d(DeclarationConstants.PartType.PARAGRAPH);
            return nzlVar;
        }
        if (DeclarationConstants.PartType.SWITCHFACE.name().equalsIgnoreCase(c)) {
            nzo nzoVar = new nzo();
            nzoVar.d(DeclarationConstants.PartType.SWITCHFACE);
            return nzoVar;
        }
        if (DeclarationConstants.PartType.CHECKBOX.name().equalsIgnoreCase(c)) {
            nzk nzkVar = new nzk();
            nzkVar.d(DeclarationConstants.PartType.CHECKBOX);
            return nzkVar;
        }
        if (DeclarationConstants.PartType.TOGGLEBUTTON.name().equalsIgnoreCase(c)) {
            nzm nzmVar = new nzm();
            nzmVar.d(DeclarationConstants.PartType.TOGGLEBUTTON);
            return nzmVar;
        }
        LogUtil.a("DeclarationParserImpl", "not support part type.");
        return null;
    }

    @Override // com.huawei.ui.device.declaration.xmlparser.parser.BaseDeclarationParser
    public void parseString(nzc nzcVar) {
        if (nzcVar instanceof nzj) {
            ((nzj) nzcVar).d(c("value"));
            return;
        }
        if (nzcVar instanceof nzl) {
            String c = c("value");
            int e = CommonUtil.e(c("placeholder_count"), 0);
            nzl nzlVar = (nzl) nzcVar;
            nzlVar.c(c);
            nzlVar.a(e);
            return;
        }
        if (nzcVar instanceof nzo) {
            ((nzo) nzcVar).a(c("value"));
        } else {
            if (nzcVar instanceof nzk) {
                String c2 = c("value");
                String c3 = c("description");
                nzk nzkVar = (nzk) nzcVar;
                nzkVar.a(c2);
                nzkVar.d(c3);
                return;
            }
            LogUtil.a("DeclarationParserImpl", "not support element.");
        }
    }

    @Override // com.huawei.ui.device.declaration.xmlparser.parser.BaseDeclarationParser
    public void parsePlaceholder(nzc nzcVar) {
        nzr nzrVar;
        DeclarationConstants.PlaceholderType placeholderType;
        String c = c("value");
        String c2 = c("type");
        if (DeclarationConstants.PlaceholderType.STR.name().equalsIgnoreCase(c2)) {
            nzrVar = new nzr();
            placeholderType = DeclarationConstants.PlaceholderType.STR;
        } else if (DeclarationConstants.PlaceholderType.URL.name().equalsIgnoreCase(c2)) {
            nzrVar = b();
            placeholderType = DeclarationConstants.PlaceholderType.URL;
        } else if (DeclarationConstants.PlaceholderType.JSON.name().equalsIgnoreCase(c2)) {
            nzrVar = a();
            placeholderType = DeclarationConstants.PlaceholderType.JSON;
        } else {
            LogUtil.a("DeclarationParserImpl", "parsePlaceholder: not support placeholder type");
            nzrVar = null;
            placeholderType = null;
        }
        if (nzrVar == null) {
            LogUtil.h("DeclarationParserImpl", "parsePlaceholder: placeholder is null.");
            return;
        }
        nzrVar.e(c);
        nzrVar.d(placeholderType);
        if (nzcVar instanceof nzn) {
            ((nzn) nzcVar).a(nzrVar);
            LogUtil.a("DeclarationParserImpl", "currentElement instanceof Part: " + c2 + ", " + c);
            return;
        }
        LogUtil.a("DeclarationParserImpl", "parsePlaceholder: other type");
    }

    private nzr a() {
        return new nzp(c("json"));
    }

    private nzr b() {
        return new nzt(c("url"), c("flag"), c("branchId"));
    }

    @Override // com.huawei.ui.device.declaration.xmlparser.parser.BaseDeclarationParser
    public void parseConfig(nzc nzcVar) {
        if (nzcVar instanceof nzo) {
            ((nzo) nzcVar).d(d());
        } else if (nzcVar instanceof nzk) {
            ((nzk) nzcVar).e(e());
        } else {
            LogUtil.a("DeclarationParserImpl", "parsePlaceholder: not support element");
        }
    }

    private nzi e() {
        DeclarationConstants.Position position;
        nzi nziVar = new nzi();
        String c = c("pos");
        String c2 = c("checked");
        if (DeclarationConstants.Position.START.name().equalsIgnoreCase(c)) {
            position = DeclarationConstants.Position.START;
        } else if (DeclarationConstants.Position.END.name().equalsIgnoreCase(c)) {
            position = DeclarationConstants.Position.END;
        } else if (DeclarationConstants.Position.EMPTY.name().equalsIgnoreCase(c)) {
            position = DeclarationConstants.Position.EMPTY;
        } else {
            position = DeclarationConstants.Position.UNKNOWN;
            LogUtil.a("DeclarationParserImpl", "parsePlaceholder: not support checkbox position");
        }
        nziVar.d(position);
        nziVar.d(Boolean.parseBoolean(c2));
        return nziVar;
    }

    private nzg d() {
        nzg nzgVar = new nzg();
        String c = c("state");
        String c2 = c("action");
        String c3 = c("description");
        if (DeclarationConstants.SwitchFaceState.Enabled.name().equalsIgnoreCase(c)) {
            nzgVar.c(DeclarationConstants.SwitchFaceState.Enabled);
        } else if (DeclarationConstants.SwitchFaceState.Disabled.name().equalsIgnoreCase(c2)) {
            nzgVar.c(DeclarationConstants.SwitchFaceState.Disabled);
        } else {
            LogUtil.a("DeclarationParserImpl", "parsePlaceholder: not support switch face state");
        }
        nzgVar.c(c2);
        nzgVar.b(c3);
        return nzgVar;
    }

    private void d(InputStream inputStream) throws XmlPullParserException {
        XmlPullParser newPullParser = Xml.newPullParser();
        this.d = newPullParser;
        newPullParser.setFeature("http://xmlpull.org/v1/doc/features.html#process-namespaces", false);
        this.d.setInput(inputStream, "UTF-8");
    }

    private String c(String str) {
        if (this.d == null || TextUtils.isEmpty(str)) {
            LogUtil.h("DeclarationParserImpl", "getAttributeValueByName: mXmlPullParser is null or specifiedName is invalid.");
            return "";
        }
        int e = e(this.d, str);
        if (e == -1) {
            LogUtil.h("DeclarationParserImpl", "getAttributeValueByName: invalid index.");
            return "";
        }
        return this.d.getAttributeValue(e);
    }

    private int e(XmlPullParser xmlPullParser, String str) {
        if (xmlPullParser == null || TextUtils.isEmpty(str)) {
            LogUtil.h("DeclarationParserImpl", "getAttributeNameIndex: mXmlPullParser is null or specifiedName is invalid.");
            return -1;
        }
        int attributeCount = xmlPullParser.getAttributeCount();
        for (int i = 0; i < attributeCount; i++) {
            if (TextUtils.equals(this.d.getAttributeName(i), str)) {
                return i;
            }
        }
        return -1;
    }
}
