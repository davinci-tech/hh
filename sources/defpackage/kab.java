package defpackage;

import android.text.TextUtils;
import android.util.Base64;
import com.huawei.hwdevice.phoneprocess.mgr.hwcontactsyncmgr.contacts.sync.utils.SyncHelper;
import com.huawei.hwlogsmodel.LogUtil;
import java.nio.charset.StandardCharsets;

/* loaded from: classes5.dex */
public class kab {
    public static String d(kaa kaaVar) {
        if (kaaVar == null) {
            LogUtil.h("ContactSync", 1, "ContactVcardBuilder", "[buildVCard] : bean is null");
            return "";
        }
        StringBuffer stringBuffer = new StringBuffer(1024);
        stringBuffer.append("BEGIN:VCARD\r\nVERSION:2.1\r\n");
        d(stringBuffer);
        g(kaaVar, stringBuffer);
        n(kaaVar, stringBuffer);
        m(kaaVar, stringBuffer);
        c(kaaVar, stringBuffer);
        e(kaaVar, stringBuffer);
        b(kaaVar, stringBuffer);
        p(kaaVar, stringBuffer);
        i(kaaVar, stringBuffer);
        o(kaaVar, stringBuffer);
        f(kaaVar, stringBuffer);
        r(kaaVar, stringBuffer);
        h(kaaVar, stringBuffer);
        l(kaaVar, stringBuffer);
        q(kaaVar, stringBuffer);
        t(kaaVar, stringBuffer);
        d(kaaVar, stringBuffer);
        s(kaaVar, stringBuffer);
        k(kaaVar, stringBuffer);
        a(kaaVar, stringBuffer);
        j(kaaVar, stringBuffer);
        stringBuffer.append("END:VCARD").append("\r\n");
        return stringBuffer.toString();
    }

    private static void d(StringBuffer stringBuffer) {
        stringBuffer.append("X-HW-VERSION:").append(90006300).append("\r\n");
    }

    private static void g(kaa kaaVar, StringBuffer stringBuffer) {
        String e = kaw.e(kaaVar.e("data3"), "");
        String e2 = kaw.e(kaaVar.e("data2"), "");
        String e3 = kaw.e(kaaVar.e("data5"), "");
        String e4 = kaw.e(kaaVar.e("data4"), "");
        String e5 = kaw.e(kaaVar.e("data6"), "");
        if (kat.d(e, e2, e3, e4, e5)) {
            stringBuffer.append("N;ENCODING=QUOTED-PRINTABLE;CHARSET=UTF-8:").append(e).append(";").append(e2).append(";").append(e3).append(";").append(e4).append(";").append(e5).append("\r\n");
            if (kat.b(e)) {
                kaw.e(stringBuffer, "FN;ENCODING=QUOTED-PRINTABLE;CHARSET=UTF-8:", e2);
                return;
            }
            if (kat.b(e2)) {
                kaw.e(stringBuffer, "FN;ENCODING=QUOTED-PRINTABLE;CHARSET=UTF-8:", e);
                return;
            }
            kaw.e(stringBuffer, "FN;ENCODING=QUOTED-PRINTABLE;CHARSET=UTF-8:", e2 + " " + e);
        }
    }

    private static void n(kaa kaaVar, StringBuffer stringBuffer) {
        String e = kaw.e(kaaVar.e("data7"), "");
        String e2 = kaw.e(kaaVar.e("data8"), "");
        String e3 = kaw.e(kaaVar.e("data9"), "");
        if (kat.d(e, e2, e3)) {
            stringBuffer.append("X-PHONETIC;ENCODING=QUOTED-PRINTABLE;CHARSET=UTF-8:").append(e3).append(";").append(e).append(";").append(e2).append("\r\n");
        }
    }

    private static void m(kaa kaaVar, StringBuffer stringBuffer) {
        kaw.a("vnd.android.cursor.item/phone_v2", kaaVar, stringBuffer);
        kaw.c(kaaVar.b("vnd.android.cursor.item/phone_v2", 2), "TEL;CELL:", stringBuffer);
        kaw.c(kaaVar.b("vnd.android.cursor.item/phone_v2", 17), "TEL;CELL;WORK:", stringBuffer);
        kaw.c(kaaVar.b("vnd.android.cursor.item/phone_v2", 1), "TEL;HOME:", stringBuffer);
        kaw.c(kaaVar.b("vnd.android.cursor.item/phone_v2", 3), "TEL;WORK:", stringBuffer);
        kaw.c(kaaVar.b("vnd.android.cursor.item/phone_v2", 12), "TEL;PREF:", stringBuffer);
        kaw.c(kaaVar.b("vnd.android.cursor.item/phone_v2", 7), "TEL;VOICE:", stringBuffer);
        kaw.c(kaaVar.b("vnd.android.cursor.item/phone_v2", 10), "TEL;WORK;PREF:", stringBuffer);
        kaw.c(kaaVar.b("vnd.android.cursor.item/phone_v2", 20), "TEL;MSG:", stringBuffer);
        kaw.c(kaaVar.b("vnd.android.cursor.item/phone_v2", 13), "TEL;FAX:", stringBuffer);
        kaw.c(kaaVar.b("vnd.android.cursor.item/phone_v2", 5), "TEL;FAX;HOME:", stringBuffer);
        kaw.c(kaaVar.b("vnd.android.cursor.item/phone_v2", 6), "TEL;PAGER:", stringBuffer);
        kaw.c(kaaVar.b("vnd.android.cursor.item/phone_v2", 4), "TEL;FAX;WORK:", stringBuffer);
    }

    private static void c(kaa kaaVar, StringBuffer stringBuffer) {
        kaw.a(kaaVar.b("vnd.android.cursor.item/email_v2", 3), "EMAIL;ENCODING=QUOTED-PRINTABLE;CHARSET=UTF-8:", stringBuffer, SyncHelper.Encode.ENCODE);
        kaw.a(kaaVar.b("vnd.android.cursor.item/email_v2", 1), "EMAIL;HOME;ENCODING=QUOTED-PRINTABLE;CHARSET=UTF-8:", stringBuffer, SyncHelper.Encode.ENCODE);
        kaw.a(kaaVar.b("vnd.android.cursor.item/email_v2", 2), "EMAIL;WORK;ENCODING=QUOTED-PRINTABLE;CHARSET=UTF-8:", stringBuffer, SyncHelper.Encode.ENCODE);
        kaw.a("vnd.android.cursor.item/email_v2", kaaVar, stringBuffer);
    }

    private static void e(kaa kaaVar, StringBuffer stringBuffer) {
        kaw.b(kaaVar.a(1), "ADR;HOME;ENCODING=QUOTED-PRINTABLE;CHARSET=UTF-8:", stringBuffer, SyncHelper.Encode.ENCODE);
        kaw.b(kaaVar.a(2), "ADR;WORK;ENCODING=QUOTED-PRINTABLE;CHARSET=UTF-8:", stringBuffer, SyncHelper.Encode.ENCODE);
        kaw.b(kaaVar.a(3), "ADR;ENCODING=QUOTED-PRINTABLE;CHARSET=UTF-8:", stringBuffer, SyncHelper.Encode.ENCODE);
        kaw.a("vnd.android.cursor.item/postal-address_v2", kaaVar, stringBuffer);
    }

    private static void b(kaa kaaVar, StringBuffer stringBuffer) {
        kaw.e(stringBuffer, "ORG;ENCODING=QUOTED-PRINTABLE;CHARSET=UTF-8:", kaw.b(kaaVar.a("data1")));
    }

    private static void p(kaa kaaVar, StringBuffer stringBuffer) {
        kaw.e(stringBuffer, "TITLE;ENCODING=QUOTED-PRINTABLE;CHARSET=UTF-8:", kaw.b(kaaVar.a("data4")));
    }

    private static void i(kaa kaaVar, StringBuffer stringBuffer) {
        kaw.a(kaaVar.b("vnd.android.cursor.item/im", 0), "X-AIM;ENCODING=QUOTED-PRINTABLE;CHARSET=UTF-8:", stringBuffer, SyncHelper.Encode.ENCODE);
        kaw.a(kaaVar.b("vnd.android.cursor.item/im", 6), "X-ICQ;ENCODING=QUOTED-PRINTABLE;CHARSET=UTF-8:", stringBuffer, SyncHelper.Encode.ENCODE);
        kaw.a(kaaVar.b("vnd.android.cursor.item/im", 7), "X-JABBER;ENCODING=QUOTED-PRINTABLE;CHARSET=UTF-8:", stringBuffer, SyncHelper.Encode.ENCODE);
        kaw.a(kaaVar.b("vnd.android.cursor.item/im", 1), "X-MSN;ENCODING=QUOTED-PRINTABLE;CHARSET=UTF-8:", stringBuffer, SyncHelper.Encode.ENCODE);
        kaw.a(kaaVar.b("vnd.android.cursor.item/im", 2), "X-YAHOO;ENCODING=QUOTED-PRINTABLE;CHARSET=UTF-8:", stringBuffer, SyncHelper.Encode.ENCODE);
        kaw.a(kaaVar.b("vnd.android.cursor.item/im", 4), "X-QQ;ENCODING=QUOTED-PRINTABLE;CHARSET=UTF-8:", stringBuffer, SyncHelper.Encode.ENCODE);
        kaw.a(kaaVar.b("vnd.android.cursor.item/im", 3), "X-SKYPE-USERNAME;ENCODING=QUOTED-PRINTABLE;CHARSET=UTF-8:", stringBuffer, SyncHelper.Encode.ENCODE);
        kaw.a(kaaVar.b("vnd.android.cursor.item/im", 5), "X-GOOGLE-TALK;ENCODING=QUOTED-PRINTABLE;CHARSET=UTF-8:", stringBuffer, SyncHelper.Encode.ENCODE);
        kaw.a("vnd.android.cursor.item/im", kaaVar, stringBuffer);
    }

    private static void o(kaa kaaVar, StringBuffer stringBuffer) {
        kaw.e(stringBuffer, "NOTE;ENCODING=QUOTED-PRINTABLE;CHARSET=UTF-8:", kaw.b(kaaVar.j()));
    }

    private static void f(kaa kaaVar, StringBuffer stringBuffer) {
        kaw.e(stringBuffer, "X-NICKNAME;ENCODING=QUOTED-PRINTABLE;CHARSET=UTF-8:", kaw.b(kaaVar.i()));
    }

    private static void r(kaa kaaVar, StringBuffer stringBuffer) {
        kaw.a(kaaVar.b("vnd.android.cursor.item/website", 7), "URL;ENCODING=QUOTED-PRINTABLE;CHARSET=UTF-8:", stringBuffer, SyncHelper.Encode.ENCODE);
        kaw.a(kaaVar.b("vnd.android.cursor.item/website", 4), "URL;HOME;ENCODING=QUOTED-PRINTABLE;CHARSET=UTF-8:", stringBuffer, SyncHelper.Encode.ENCODE);
        kaw.a(kaaVar.b("vnd.android.cursor.item/website", 5), "URL;WORK;ENCODING=QUOTED-PRINTABLE;CHARSET=UTF-8:", stringBuffer, SyncHelper.Encode.ENCODE);
    }

    private static void h(kaa kaaVar, StringBuffer stringBuffer) {
        kaw.c(kaaVar.b("vnd.android.cursor.item/contact_event", 3), "BDAY:", stringBuffer);
        kaw.c(kaaVar.b("vnd.android.cursor.item/contact_event", 4), "X-BDAY-LUNAR:", stringBuffer);
        kaw.c(kaaVar.b("vnd.android.cursor.item/contact_event", 1), "X-CUSTOM-EVENT-1:", stringBuffer);
        kaw.c(kaaVar.b("vnd.android.cursor.item/contact_event", 2), "X-CUSTOM-EVENT-2:", stringBuffer);
        kaw.a("vnd.android.cursor.item/contact_event", kaaVar, stringBuffer);
    }

    private static void l(kaa kaaVar, StringBuffer stringBuffer) {
        kaw.a(kaaVar.b("vnd.android.cursor.item/relation", 1), "X-CUSTOM-RELATION-1;ENCODING=QUOTED-PRINTABLE;CHARSET=UTF-8:", stringBuffer, SyncHelper.Encode.ENCODE);
        kaw.a(kaaVar.b("vnd.android.cursor.item/relation", 2), "X-CUSTOM-RELATION-2;ENCODING=QUOTED-PRINTABLE;CHARSET=UTF-8:", stringBuffer, SyncHelper.Encode.ENCODE);
        kaw.a(kaaVar.b("vnd.android.cursor.item/relation", 3), "X-CUSTOM-RELATION-3;ENCODING=QUOTED-PRINTABLE;CHARSET=UTF-8:", stringBuffer, SyncHelper.Encode.ENCODE);
        kaw.a(kaaVar.b("vnd.android.cursor.item/relation", 4), "X-CUSTOM-RELATION-4;ENCODING=QUOTED-PRINTABLE;CHARSET=UTF-8:", stringBuffer, SyncHelper.Encode.ENCODE);
        kaw.a(kaaVar.b("vnd.android.cursor.item/relation", 5), "X-CUSTOM-RELATION-5;ENCODING=QUOTED-PRINTABLE;CHARSET=UTF-8:", stringBuffer, SyncHelper.Encode.ENCODE);
        kaw.a(kaaVar.b("vnd.android.cursor.item/relation", 6), "X-CUSTOM-RELATION-6;ENCODING=QUOTED-PRINTABLE;CHARSET=UTF-8:", stringBuffer, SyncHelper.Encode.ENCODE);
        kaw.a(kaaVar.b("vnd.android.cursor.item/relation", 7), "X-CUSTOM-RELATION-7;ENCODING=QUOTED-PRINTABLE;CHARSET=UTF-8:", stringBuffer, SyncHelper.Encode.ENCODE);
        kaw.a(kaaVar.b("vnd.android.cursor.item/relation", 8), "X-CUSTOM-RELATION-8;ENCODING=QUOTED-PRINTABLE;CHARSET=UTF-8:", stringBuffer, SyncHelper.Encode.ENCODE);
        kaw.a(kaaVar.b("vnd.android.cursor.item/relation", 9), "X-CUSTOM-RELATION-9;ENCODING=QUOTED-PRINTABLE;CHARSET=UTF-8:", stringBuffer, SyncHelper.Encode.ENCODE);
        kaw.a(kaaVar.b("vnd.android.cursor.item/relation", 10), "X-CUSTOM-RELATION-10;ENCODING=QUOTED-PRINTABLE;CHARSET=UTF-8:", stringBuffer, SyncHelper.Encode.ENCODE);
        kaw.a(kaaVar.b("vnd.android.cursor.item/relation", 11), "X-CUSTOM-RELATION-11;ENCODING=QUOTED-PRINTABLE;CHARSET=UTF-8:", stringBuffer, SyncHelper.Encode.ENCODE);
        kaw.a(kaaVar.b("vnd.android.cursor.item/relation", 12), "X-CUSTOM-RELATION-12;ENCODING=QUOTED-PRINTABLE;CHARSET=UTF-8:", stringBuffer, SyncHelper.Encode.ENCODE);
        kaw.a(kaaVar.b("vnd.android.cursor.item/relation", 13), "X-CUSTOM-RELATION-13;ENCODING=QUOTED-PRINTABLE;CHARSET=UTF-8:", stringBuffer, SyncHelper.Encode.ENCODE);
        kaw.a(kaaVar.b("vnd.android.cursor.item/relation", 14), "X-CUSTOM-RELATION-14;ENCODING=QUOTED-PRINTABLE;CHARSET=UTF-8:", stringBuffer, SyncHelper.Encode.ENCODE);
        kaw.a("vnd.android.cursor.item/relation", kaaVar, stringBuffer);
    }

    private static void q(kaa kaaVar, StringBuffer stringBuffer) {
        kaw.e(stringBuffer, "X-HW-STARED-ORDER:", kaaVar.k());
    }

    private static void t(kaa kaaVar, StringBuffer stringBuffer) {
        kaw.e(stringBuffer, "X-HW-TIMES-CONTACTED:", kaaVar.n());
    }

    private static void d(kaa kaaVar, StringBuffer stringBuffer) {
        kaw.e(stringBuffer, "X-CUSTOM-PARAM:", kaaVar.l() + ";" + kaaVar.h());
    }

    private static void s(kaa kaaVar, StringBuffer stringBuffer) {
        kaw.e(stringBuffer, "UID:", kaaVar.getUid());
    }

    private static void k(kaa kaaVar, StringBuffer stringBuffer) {
        if (kaaVar.f() != null) {
            kaw.e(stringBuffer, "X-HW-PHOTO:", kaaVar.f());
        }
        kaw.e(stringBuffer, "PHOTO;ENCODING=BASE64:", new String(Base64.encode(kaaVar.g(), 2), StandardCharsets.UTF_8));
        if (kaaVar.a() != null) {
            kaw.e(stringBuffer, "X-CARD:", kaaVar.a());
        }
    }

    private static void a(kaa kaaVar, StringBuffer stringBuffer) {
        if (!kat.b(kaaVar.c())) {
            kaw.e(stringBuffer, "X-HW-ACCOUNT-TYPE:", kaaVar.c());
        }
        if (kat.b(kaaVar.d())) {
            return;
        }
        kaw.e(stringBuffer, "X-HW-ACCOUNT-NAME:", kaaVar.d());
    }

    private static void j(kaa kaaVar, StringBuffer stringBuffer) {
        if (kac.b().equals(kaaVar.c())) {
            String e = kaaVar.e();
            if (TextUtils.isEmpty(e)) {
                return;
            }
            kaw.e(stringBuffer, "X-HW-HICALL-DATA:", e);
        }
    }
}
