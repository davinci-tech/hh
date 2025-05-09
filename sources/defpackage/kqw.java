package defpackage;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.hwidauth.c.k;
import com.huawei.phoneservice.faq.base.constants.FaqConstants;
import com.huawei.up.request.HttpRequestBase;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlSerializer;

/* loaded from: classes5.dex */
public class kqw extends k {
    private String e;
    private String f;
    private String h;
    private String j;
    private String o;
    private int g = -1;
    private String i = "";
    private int k = 0;
    private String n = "/AccountServer/IUserInfoMng/getResource?";

    public kqw(Context context, String str) {
        this.f = str;
        this.o = ksi.e(context);
    }

    @Override // com.huawei.hwidauth.c.k
    public String a() throws IllegalArgumentException, IllegalStateException, IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            XmlSerializer e = kri.e(byteArrayOutputStream);
            e.startDocument("UTF-8", true);
            e.startTag(null, "GetResourceReq");
            kri.e(e, "version", "66300");
            if (!TextUtils.isEmpty(this.f)) {
                kri.e(e, "resourceID", this.f);
            } else {
                kri.e(e, "resourceID", this.j);
            }
            if (!TextUtils.isEmpty(this.i)) {
                kri.e(e, "ResourceOldVer", this.i);
            }
            if (!TextUtils.isEmpty(this.o)) {
                kri.e(e, FaqConstants.FAQ_LANGUAGE, this.o);
            }
            kri.e(e, "reqClientType", String.valueOf(this.k));
            kri.e(e, "clientVersion", "HwID_6.12.0.302");
            e.endTag(null, "GetResourceReq");
            e.endDocument();
            return byteArrayOutputStream.toString("UTF-8");
        } finally {
            try {
                byteArrayOutputStream.close();
            } catch (IOException e2) {
                ksy.c("GetResourceRequest", e2.getClass().getSimpleName(), true);
            }
        }
    }

    public void c(String str) throws XmlPullParserException, IOException {
        XmlPullParser a2 = kri.a(str.getBytes("UTF-8"));
        for (int eventType = a2.getEventType(); 1 != eventType; eventType = a2.next()) {
            String name = a2.getName();
            if (eventType == 2) {
                if ("result".equals(name)) {
                    this.f6363a = krg.a(a2.getAttributeValue(null, "resultCode"));
                }
                if (this.f6363a == 0) {
                    if ("ResourceContent".equals(name)) {
                        this.d = a2.nextText();
                        ksy.b("GetResourceRequest", "resourceContent", true);
                    } else if ("ResourceVer".equals(name)) {
                        this.e = a2.nextText();
                    }
                } else if ("errorCode".equals(name)) {
                    this.g = krg.a(a2.nextText());
                } else if (HttpRequestBase.TAG_ERROR_DESC.equals(name)) {
                    this.h = a2.nextText();
                }
            }
        }
    }

    @Override // com.huawei.hwidauth.c.k
    public String c() {
        return this.n + "cVersion=HwID_6.12.0.302&Version=66300";
    }

    @Override // com.huawei.hwidauth.c.k
    public String b() {
        return "";
    }
}
