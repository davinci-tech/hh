package defpackage;

import android.text.TextUtils;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.pluginachievement.manager.model.MedalConstants;
import java.io.IOException;
import java.io.InputStream;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

/* loaded from: classes6.dex */
public class ncb {
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:25:0x00bf  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x00c3  */
    /* JADX WARN: Type inference failed for: r7v4 */
    /* JADX WARN: Type inference failed for: r7v5, types: [java.util.List] */
    /* JADX WARN: Type inference failed for: r7v6 */
    /* JADX WARN: Type inference failed for: r7v7 */
    /* JADX WARN: Type inference failed for: r7v8 */
    /* JADX WARN: Type inference failed for: r7v9 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void b(com.huawei.hwbasemgr.IBaseResponseCallback r12) {
        /*
            Method dump skipped, instructions count: 210
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.ncb.b(com.huawei.hwbasemgr.IBaseResponseCallback):void");
    }

    private void c(InputStream inputStream) {
        if (inputStream != null) {
            try {
                inputStream.close();
            } catch (IOException unused) {
                LogUtil.b("EsimOpenMethodXmlParse", "closeInputStream inputStream close IOException");
            }
        }
    }

    private void b(XmlPullParser xmlPullParser, nca ncaVar) {
        if (ncaVar == null) {
            LogUtil.h("EsimOpenMethodXmlParse", "processXmlTag esimOpenMethod is null");
            return;
        }
        try {
            if ("open_method".equals(xmlPullParser.getAttributeValue(null, MedalConstants.EVENT_KEY))) {
                String nextText = xmlPullParser.nextText();
                if (!TextUtils.isEmpty(nextText)) {
                    ncaVar.b(nextText);
                }
            } else if ("operator_key".equals(xmlPullParser.getAttributeValue(null, MedalConstants.EVENT_KEY))) {
                String nextText2 = xmlPullParser.nextText();
                if (!TextUtils.isEmpty(nextText2)) {
                    ncaVar.d(nextText2);
                }
            } else if ("operator_name".equals(xmlPullParser.getAttributeValue(null, MedalConstants.EVENT_KEY))) {
                String nextText3 = xmlPullParser.nextText();
                if (!TextUtils.isEmpty(nextText3)) {
                    ncaVar.e(nextText3);
                }
            } else if ("open_url".equals(xmlPullParser.getAttributeValue(null, MedalConstants.EVENT_KEY))) {
                String nextText4 = xmlPullParser.nextText();
                if (!TextUtils.isEmpty(nextText4)) {
                    ncaVar.c(nextText4);
                }
            } else if ("query_url".equals(xmlPullParser.getAttributeValue(null, MedalConstants.EVENT_KEY))) {
                String nextText5 = xmlPullParser.nextText();
                if (!TextUtils.isEmpty(nextText5)) {
                    ncaVar.h(nextText5);
                }
            } else {
                d(xmlPullParser, ncaVar);
            }
        } catch (IOException unused) {
            LogUtil.b("EsimOpenMethodXmlParse", "processXmlTag IOException");
        } catch (XmlPullParserException unused2) {
            LogUtil.b("EsimOpenMethodXmlParse", "processXmlTag XmlPullParserException");
        }
    }

    private void d(XmlPullParser xmlPullParser, nca ncaVar) {
        try {
            if ("default_url".equals(xmlPullParser.getAttributeValue(null, MedalConstants.EVENT_KEY))) {
                String nextText = xmlPullParser.nextText();
                if (!TextUtils.isEmpty(nextText)) {
                    ncaVar.a(nextText);
                }
            } else if ("auth_method".equals(xmlPullParser.getAttributeValue(null, MedalConstants.EVENT_KEY))) {
                String nextText2 = xmlPullParser.nextText();
                if (!TextUtils.isEmpty(nextText2)) {
                    ncaVar.b(Integer.parseInt(nextText2));
                }
            } else {
                LogUtil.c("EsimOpenMethodXmlParse", "processOtherXmlTag else branch");
            }
        } catch (IOException unused) {
            LogUtil.b("EsimOpenMethodXmlParse", "processOtherXmlTag IOException");
        } catch (NumberFormatException unused2) {
            LogUtil.b("EsimOpenMethodXmlParse", "processOtherXmlTag NumberFormatException");
        } catch (XmlPullParserException unused3) {
            LogUtil.b("EsimOpenMethodXmlParse", "processOtherXmlTag XmlPullParserException");
        }
    }
}
