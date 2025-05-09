package defpackage;

import android.text.TextUtils;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.pluginachievement.manager.model.MedalConstants;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

/* loaded from: classes6.dex */
public class ncc {
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
    public void e(com.huawei.hwbasemgr.IBaseResponseCallback r12) {
        /*
            Method dump skipped, instructions count: 210
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.ncc.e(com.huawei.hwbasemgr.IBaseResponseCallback):void");
    }

    private void c(InputStream inputStream) {
        if (inputStream != null) {
            try {
                inputStream.close();
            } catch (IOException unused) {
                LogUtil.b("SimInfoXmlParse", "closeStream inputStream close IOException");
            }
        }
    }

    private void e(XmlPullParser xmlPullParser, nch nchVar) {
        if (nchVar == null) {
            LogUtil.h("SimInfoXmlParse", "processXmlTag simRuleInfo is null");
            return;
        }
        try {
            if ("mcc_mnc".equals(xmlPullParser.getAttributeValue(null, MedalConstants.EVENT_KEY))) {
                String nextText = xmlPullParser.nextText();
                if (!TextUtils.isEmpty(nextText)) {
                    nchVar.a(nextText);
                }
            } else if ("operator_key".equals(xmlPullParser.getAttributeValue(null, MedalConstants.EVENT_KEY))) {
                String nextText2 = xmlPullParser.nextText();
                if (!TextUtils.isEmpty(nextText2)) {
                    nchVar.f(nextText2);
                }
            } else if ("operator_name".equals(xmlPullParser.getAttributeValue(null, MedalConstants.EVENT_KEY))) {
                String nextText3 = xmlPullParser.nextText();
                if (!TextUtils.isEmpty(nextText3)) {
                    nchVar.h(nextText3);
                }
            } else if ("rule_id".equals(xmlPullParser.getAttributeValue(null, MedalConstants.EVENT_KEY))) {
                String nextText4 = xmlPullParser.nextText();
                if (!TextUtils.isEmpty(nextText4)) {
                    nchVar.a(e(nextText4));
                }
            } else {
                d(xmlPullParser, nchVar);
            }
        } catch (IOException unused) {
            LogUtil.b("SimInfoXmlParse", "processXmlTag IOException");
        } catch (XmlPullParserException unused2) {
            LogUtil.b("SimInfoXmlParse", "processXmlTag XmlPullParserException");
        }
    }

    private void d(XmlPullParser xmlPullParser, nch nchVar) {
        try {
            if ("gid1".equals(xmlPullParser.getAttributeValue(null, MedalConstants.EVENT_KEY))) {
                String nextText = xmlPullParser.nextText();
                if (!TextUtils.isEmpty(nextText)) {
                    nchVar.c(nextText);
                }
            } else if ("gid1_mask".equals(xmlPullParser.getAttributeValue(null, MedalConstants.EVENT_KEY))) {
                String nextText2 = xmlPullParser.nextText();
                if (!TextUtils.isEmpty(nextText2)) {
                    nchVar.b(nextText2);
                }
            } else if ("spn".equals(xmlPullParser.getAttributeValue(null, MedalConstants.EVENT_KEY))) {
                String nextText3 = xmlPullParser.nextText();
                if (!TextUtils.isEmpty(nextText3)) {
                    nchVar.g(nextText3);
                }
            } else if ("imsi".equals(xmlPullParser.getAttributeValue(null, MedalConstants.EVENT_KEY))) {
                String nextText4 = xmlPullParser.nextText();
                if (!TextUtils.isEmpty(nextText4)) {
                    nchVar.e(nextText4);
                }
            } else if ("iccid".equals(xmlPullParser.getAttributeValue(null, MedalConstants.EVENT_KEY))) {
                String nextText5 = xmlPullParser.nextText();
                if (!TextUtils.isEmpty(nextText5)) {
                    nchVar.d(nextText5);
                }
            } else {
                LogUtil.c("SimInfoXmlParse", "processXmlOtherTag else branch");
            }
        } catch (IOException unused) {
            LogUtil.b("SimInfoXmlParse", "processXmlOtherTag IOException");
        } catch (XmlPullParserException unused2) {
            LogUtil.b("SimInfoXmlParse", "processXmlOtherTag XmlPullParserException");
        }
    }

    private int e(String str) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("SimInfoXmlParse", "hexStringToInt hexString is empty");
            return 0;
        }
        if (str.startsWith("0X") || str.startsWith("0x")) {
            str = str.substring(2);
        }
        if (!str.matches("[0-9a-fA-F]+")) {
            LogUtil.h("SimInfoXmlParse", "hexStringToInt hexString is not matches");
            return 0;
        }
        try {
            return new BigInteger(str, 16).intValue();
        } catch (NumberFormatException unused) {
            LogUtil.b("SimInfoXmlParse", "hexStringToInt NumberFormatException");
            return 0;
        }
    }
}
