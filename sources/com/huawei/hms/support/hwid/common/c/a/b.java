package com.huawei.hms.support.hwid.common.c.a;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import com.huawei.hms.support.api.entity.pay.HwPayConstant;
import com.huawei.hms.support.feature.result.CommonConstant;
import com.huawei.hms.support.hwid.bean.CheckPasswordByUserIdReq;
import com.huawei.hms.support.hwid.common.e.c;
import com.huawei.hms.support.hwid.common.e.g;
import com.huawei.hms.support.hwid.common.e.j;
import com.huawei.hms.support.hwid.common.e.k;
import com.huawei.hwidauth.datatype.DeviceInfo;
import com.huawei.phoneservice.faq.base.constants.FaqConstants;
import com.huawei.up.request.HttpRequestBase;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlSerializer;

/* loaded from: classes9.dex */
public class b extends com.huawei.hms.support.hwid.common.c.a {
    private String e;
    private String f;
    private String g;
    private String h;
    private String i = "/IUserPwdMng/uidVerifyPasswordV3";
    private String j = "7";
    private String k;
    private String l;
    private String m;
    private String n;
    private String o;
    private String p;
    private String q;
    private String r;

    public b(Context context, CheckPasswordByUserIdReq checkPasswordByUserIdReq, String str, String str2, String str3) {
        this.e = checkPasswordByUserIdReq.getUserId();
        this.l = str3;
        this.g = checkPasswordByUserIdReq.getAppId();
        String guardianUserID = checkPasswordByUserIdReq.getGuardianUserID();
        this.m = guardianUserID;
        this.f = TextUtils.isEmpty(guardianUserID) ? "0" : "1";
        this.h = com.huawei.hms.support.hwid.common.b.b.a(checkPasswordByUserIdReq.getPassword(), str2);
        this.n = a(context);
        this.r = com.huawei.hms.support.hwid.common.a.a.a(context);
        this.o = checkPasswordByUserIdReq.getDeviceId();
        this.p = checkPasswordByUserIdReq.getDeviceId2();
        this.q = checkPasswordByUserIdReq.getDeviceType();
        a(str, checkPasswordByUserIdReq.getSiteId());
    }

    public String e() throws IllegalArgumentException, IllegalStateException, IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            XmlSerializer a2 = k.a(byteArrayOutputStream);
            a2.startDocument("UTF-8", true);
            a2.startTag(null, "UidVerifyPasswordV3Req");
            k.a(a2, "version", "66300");
            k.a(a2, HwPayConstant.KEY_USER_ID, this.e);
            k.a(a2, "sceneID", this.f);
            k.a(a2, "password", this.h);
            k.a(a2, "appID", this.g);
            k.a(a2, "supportAlg", "PS256");
            k.a(a2, "reqClientType", "7");
            k.a(a2, "guardianUserID", this.m);
            if (!TextUtils.isEmpty(this.n)) {
                k.a(a2, FaqConstants.FAQ_LANGUAGE, this.n);
            }
            a2.startTag(null, "deviceInfo");
            k.a(a2, DeviceInfo.TAG_DEVICE_ID, this.o);
            if (!TextUtils.isEmpty(this.p)) {
                k.a(a2, "deviceID2", this.p);
            }
            k.a(a2, "deviceType", this.q);
            k.a(a2, "uuid", this.r);
            k.a(a2, "terminalType", com.huawei.hms.support.hwid.common.a.a.a());
            a2.endTag(null, "deviceInfo");
            a2.endTag(null, "UidVerifyPasswordV3Req");
            a2.endDocument();
            return byteArrayOutputStream.toString("UTF-8");
        } finally {
            try {
                byteArrayOutputStream.close();
            } catch (IOException unused) {
                g.c("UIDVerifyPasswordV3Request", "GetAuthCodeSendListRequest pack error", true);
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
                    a(a2, name);
                } else if ("errorCode".equals(name)) {
                    this.c = j.a(a2.nextText());
                } else if (HttpRequestBase.TAG_ERROR_DESC.equals(name)) {
                    this.d = a2.nextText();
                }
            }
        }
    }

    private void a(XmlPullParser xmlPullParser, String str) {
        try {
            if (CommonConstant.KEY_ID_TOKEN.equals(str)) {
                this.k = xmlPullParser.nextText();
            } else {
                g.a("UIDVerifyPasswordV3Request", "do nothing for node: " + str, false);
            }
        } catch (Throwable th) {
            g.a("UIDVerifyPasswordV3Request", "getSendListReqResult error " + th.getClass().getSimpleName(), true);
        }
    }

    @Override // com.huawei.hms.support.hwid.common.c.a
    public String a() {
        return this.i;
    }

    @Override // com.huawei.hms.support.hwid.common.c.a
    public Bundle b() {
        Bundle b = super.b();
        b.putString(CommonConstant.KEY_ID_TOKEN, this.k);
        return b;
    }

    @Override // com.huawei.hms.support.hwid.common.c.a
    public String a(Context context) {
        return c.d(context);
    }
}
