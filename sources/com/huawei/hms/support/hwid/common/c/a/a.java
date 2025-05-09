package com.huawei.hms.support.hwid.common.c.a;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.hms.support.hwid.common.e.c;
import com.huawei.hms.support.hwid.common.e.g;
import com.huawei.hms.support.hwid.common.e.j;
import com.huawei.hms.support.hwid.common.e.k;
import com.huawei.phoneservice.faq.base.constants.FaqConstants;
import com.huawei.up.request.HttpRequestBase;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlSerializer;

/* loaded from: classes9.dex */
public class a extends com.huawei.hms.support.hwid.common.c.a {
    private String e;
    private String g;
    private String i;
    private String j;
    private int f = -1;
    private String h = "siteDomain";
    private int k = 0;
    private String l = "/AccountServer/IUserInfoMng/getResource";

    public a(Context context, String str) {
        this.i = str;
        this.j = a(context);
    }

    public String e() throws IllegalArgumentException, IllegalStateException, IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            XmlSerializer a2 = k.a(byteArrayOutputStream);
            a2.startDocument("UTF-8", true);
            a2.startTag(null, "GetResourceReq");
            k.a(a2, "version", "66300");
            if (!TextUtils.isEmpty(this.i)) {
                k.a(a2, "resourceID", this.i);
            } else {
                k.a(a2, "resourceID", this.h);
            }
            if (!TextUtils.isEmpty(this.j)) {
                k.a(a2, FaqConstants.FAQ_LANGUAGE, this.j);
            }
            k.a(a2, "reqClientType", String.valueOf(this.k));
            k.a(a2, "clientVersion", c.a());
            a2.endTag(null, "GetResourceReq");
            a2.endDocument();
            return byteArrayOutputStream.toString("UTF-8");
        } finally {
            try {
                byteArrayOutputStream.close();
            } catch (IOException e) {
                g.b("GetResourceRequest", e.getClass().getSimpleName());
            }
        }
    }

    public void a(String str) throws XmlPullParserException, IOException {
        XmlPullParser a2 = k.a(str.getBytes("UTF-8"));
        for (int eventType = a2.getEventType(); 1 != eventType; eventType = a2.next()) {
            String name = a2.getName();
            if (eventType == 2) {
                if ("result".equals(name)) {
                    this.b = j.a(a2.getAttributeValue(null, "resultCode"));
                }
                if (this.b == 0) {
                    if ("ResourceContent".equals(name)) {
                        this.e = a2.nextText();
                        g.a("GetResourceRequest", "resourceContent");
                    }
                } else if ("errorCode".equals(name)) {
                    this.f = j.a(a2.nextText());
                } else if (HttpRequestBase.TAG_ERROR_DESC.equals(name)) {
                    this.g = a2.nextText();
                }
            }
        }
    }

    @Override // com.huawei.hms.support.hwid.common.c.a
    public String a(Context context) {
        return c.d(context);
    }

    public String f() {
        return this.e;
    }

    public int g() {
        return this.f;
    }

    public String h() {
        return this.g;
    }

    @Override // com.huawei.hms.support.hwid.common.c.a
    public String a() {
        return this.l;
    }
}
