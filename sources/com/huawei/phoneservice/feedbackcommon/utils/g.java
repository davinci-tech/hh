package com.huawei.phoneservice.feedbackcommon.utils;

import android.content.Context;
import android.content.res.XmlResourceParser;
import com.huawei.hms.framework.common.Logger;
import com.huawei.phoneservice.faq.base.util.FaqRefectUtils;
import java.io.IOException;
import org.xmlpull.v1.XmlPullParserException;

/* loaded from: classes6.dex */
public class g {
    public static String a(Context context, String str, String str2) {
        String str3;
        XmlResourceParser xml = context.getResources().getXml(c(context, FaqRefectUtils.ResType.RES_TYPE_XML, str2));
        try {
            if (xml == null) {
                return "";
            }
            try {
                for (int eventType = xml.getEventType(); eventType != 1; eventType = xml.next()) {
                    if (eventType == 2) {
                        String name = xml.getName();
                        if (str.equals(xml.getAttributeName(0)) && "string".equals(name)) {
                            return xml.getAttributeValue(0);
                        }
                    }
                }
            } catch (IOException unused) {
                str3 = "getValueByAttributeName IOException";
                Logger.e("XmlUtil", str3);
                return "";
            } catch (XmlPullParserException unused2) {
                str3 = "getValueByAttributeName XmlPullParserException ";
                Logger.e("XmlUtil", str3);
                return "";
            } catch (Exception unused3) {
                str3 = "getValueByAttributeName Exception";
                Logger.e("XmlUtil", str3);
                return "";
            }
            return "";
        } finally {
            xml.close();
        }
    }

    public static int c(Context context, FaqRefectUtils.ResType resType, String str) {
        try {
            return context.getResources().getIdentifier(str, resType.toString(), context.getPackageName());
        } catch (Exception e) {
            Logger.e("XmlUtil", e);
            return 0;
        }
    }
}
