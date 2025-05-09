package defpackage;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.hwidauth.c.k;
import com.huawei.hwidauth.datatype.DeviceInfo;
import com.huawei.phoneservice.faq.base.constants.FaqConstants;
import com.huawei.up.request.HttpRequestBase;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlSerializer;

/* loaded from: classes5.dex */
public class kqy extends k {
    private Context e;
    private String f;
    private String g;
    private String h;
    private String i;
    private String j = "/AccountServer/IUserDeviceMng/getDevAuthCode?";
    private DeviceInfo m;
    private String n;
    private String o;

    public String d() {
        return this.i;
    }

    public String e() {
        return this.n;
    }

    public String f() {
        return this.o;
    }

    public kqy(Context context, String str, String str2, String str3) {
        this.e = context;
        this.g = str;
        this.h = str2;
        this.f = TextUtils.isEmpty(str3) ? "" : str3;
    }

    public void c(DeviceInfo deviceInfo) {
        this.m = deviceInfo;
    }

    @Override // com.huawei.hwidauth.c.k
    public String a() throws IllegalArgumentException, IllegalStateException, IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            XmlSerializer e = kri.e(byteArrayOutputStream);
            e.startDocument("UTF-8", true);
            e.startTag(null, "GetDevAuthCodeReq");
            kri.e(e, "version", "66300");
            kri.e(e, "uuid", kti.b(this.e));
            if (this.m != null) {
                e.startTag(null, "deviceInfo");
                DeviceInfo.a(e, this.m);
                e.endTag(null, "deviceInfo");
            }
            kri.e(e, "oprType", this.g);
            kri.e(e, "loginStatus", this.h);
            kri.e(e, "serviceToken", this.f);
            kri.e(e, FaqConstants.FAQ_LANGUAGE, ksi.e(this.e));
            kri.e(e, "appID", "com.huawei.hwid");
            e.endTag(null, "GetDevAuthCodeReq");
            e.endDocument();
            return byteArrayOutputStream.toString("UTF-8");
        } finally {
            try {
                byteArrayOutputStream.close();
            } catch (IOException unused) {
                ksy.c("GetDevAuthCodeRequest", "GetDevAuthCodeRequest IOException", true);
            }
        }
    }

    @Override // com.huawei.hwidauth.c.k
    public int g() {
        return this.b;
    }

    @Override // com.huawei.hwidauth.c.k
    public String h() {
        return this.c;
    }

    public void c(String str) throws XmlPullParserException, IOException {
        XmlPullParser a2 = kri.a(str.getBytes("UTF-8"));
        for (int eventType = a2.getEventType(); 1 != eventType; eventType = a2.next()) {
            String name = a2.getName();
            if (eventType == 2) {
                if ("result".equals(name)) {
                    this.f6363a = ksi.e(a2.getAttributeValue(null, "resultCode"));
                }
                if (this.f6363a == 0) {
                    if ("devAuthCode".equals(name)) {
                        this.i = a2.nextText();
                    } else if ("encryptKey".equals(name)) {
                        this.n = a2.nextText();
                    } else if ("randomID".equals(name)) {
                        this.o = a2.nextText();
                    }
                } else if ("errorCode".equals(name)) {
                    this.b = ksi.e(a2.nextText());
                } else if (HttpRequestBase.TAG_ERROR_DESC.equals(name)) {
                    this.c = a2.nextText();
                }
            }
        }
    }

    @Override // com.huawei.hwidauth.c.k
    public String c() {
        return this.j + "cVersion=HwID_6.12.0.302&Version=66300&ctrID=" + System.currentTimeMillis() + ksi.t();
    }

    @Override // com.huawei.hwidauth.c.k
    public String b() {
        return "";
    }
}
