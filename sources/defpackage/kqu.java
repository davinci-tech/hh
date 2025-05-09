package defpackage;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.hms.network.inner.api.NetworkService;
import com.huawei.hwidauth.c.k;
import com.huawei.up.request.HttpRequestBase;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlSerializer;

/* loaded from: classes5.dex */
public class kqu extends k {
    private String e = "/AccountServer/IDM/getClientConfiguration?";
    private String f = "";
    private Context g;
    private String h;
    private String i;
    private String j;

    public kqu(String str, String str2, String str3, Context context) {
        this.h = str;
        this.j = str2;
        this.i = str3;
        this.g = context;
    }

    @Override // com.huawei.hwidauth.c.k
    public String a() throws IllegalArgumentException, IllegalStateException, IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            XmlSerializer e = kri.e(byteArrayOutputStream);
            e.startDocument("UTF-8", true);
            e.startTag(null, "GetClientConfigurationReq");
            kri.e(e, "version", "66300");
            if (!TextUtils.isEmpty(this.h)) {
                kri.e(e, "country", this.h);
            } else {
                kri.e(e, "country", "");
            }
            if (!TextUtils.isEmpty(this.j)) {
                kri.e(e, NetworkService.Constants.CONFIG_SERVICE, this.j);
            } else {
                kri.e(e, NetworkService.Constants.CONFIG_SERVICE, "");
            }
            ksy.b("GetClientConfigurationRequest", "Request dataVer = " + this.i, true);
            if (!TextUtils.isEmpty(this.i)) {
                kri.e(e, "dataVer", this.i);
            } else {
                kri.e(e, "dataVer", "");
            }
            e.endTag(null, "GetClientConfigurationReq");
            e.endDocument();
            String byteArrayOutputStream2 = byteArrayOutputStream.toString("UTF-8");
            ksy.b("GetClientConfigurationRequest", "Config request info:" + byteArrayOutputStream2, false);
            return byteArrayOutputStream2;
        } finally {
            try {
                byteArrayOutputStream.close();
            } catch (IOException e2) {
                ksy.c("GetClientConfigurationRequest", e2.getClass().getSimpleName(), true);
            }
        }
    }

    public void d(String str) throws XmlPullParserException, IOException {
        XmlPullParser a2 = kri.a(str.getBytes("UTF-8"));
        ksm.e(this.g).e();
        for (int eventType = a2.getEventType(); 1 != eventType; eventType = a2.next()) {
            String name = a2.getName();
            if (eventType == 2) {
                if ("result".equals(name)) {
                    this.f6363a = krg.a(a2.getAttributeValue(null, "resultCode"));
                } else if (this.f6363a == 0 && "CountryList".equalsIgnoreCase(name)) {
                    ksy.b("GetClientConfigurationRequest", "mResultCode is success.", true);
                    this.f = str;
                    ksm.e(this.g).d(a2, str);
                } else if ("errorCode".equals(name)) {
                    this.b = krg.a(a2.nextText());
                } else if (HttpRequestBase.TAG_ERROR_DESC.equals(name)) {
                    this.c = a2.nextText();
                }
            }
        }
    }

    @Override // com.huawei.hwidauth.c.k
    public String c() {
        return this.e + "cVersion=HwID_6.12.0.302&Version=66300";
    }

    public String d() {
        return this.f;
    }

    @Override // com.huawei.hwidauth.c.k
    public String b() {
        return "";
    }
}
