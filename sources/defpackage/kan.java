package defpackage;

import android.database.Cursor;
import android.text.TextUtils;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.CommonUtil;

/* loaded from: classes5.dex */
public class kan {
    public static void bMr_(Cursor cursor, kaa kaaVar) {
        if (cursor == null || kaaVar == null) {
            LogUtil.h("ContactSync", 1, "RawContactBeanUtils", "setContactFieldsWithDataCursor: cursor or rawContactBean is null.");
            return;
        }
        String string = cursor.getString(2);
        if (TextUtils.isEmpty(string)) {
            LogUtil.h("ContactSync", 1, "RawContactBeanUtils", "setContactFieldsWithDataCursor: mimeType: is null or empty.");
            return;
        }
        if ("vnd.android.cursor.item/phone_v2".equals(string)) {
            bMD_(cursor, kaaVar);
            return;
        }
        if ("vnd.android.cursor.item/name".equals(string)) {
            bMG_(cursor, kaaVar);
            return;
        }
        if ("vnd.android.cursor.item/nickname".equals(string)) {
            bMA_(cursor, kaaVar);
            return;
        }
        if ("vnd.android.cursor.item/email_v2".equals(string)) {
            bMw_(cursor, kaaVar);
            return;
        }
        if ("vnd.android.cursor.item/organization".equals(string)) {
            bMC_(cursor, kaaVar);
            return;
        }
        if ("vnd.android.cursor.item/note".equals(string)) {
            bMB_(cursor, kaaVar);
            return;
        }
        if ("vnd.android.cursor.item/contact_event".equals(string)) {
            bMx_(cursor, kaaVar);
            return;
        }
        if ("vnd.android.cursor.item/photo".equals(string)) {
            bME_(cursor, kaaVar);
            return;
        }
        if ("vnd.android.cursor.item/website".equals(string)) {
            bMH_(cursor, kaaVar);
            return;
        }
        if ("vnd.android.cursor.item/relation".equals(string)) {
            bMF_(cursor, kaaVar);
            return;
        }
        if ("vnd.android.cursor.item/postal-address_v2".equals(string)) {
            bMq_(cursor, kaaVar);
        } else if ("vnd.android.cursor.item/im".equals(string)) {
            bMz_(cursor, kaaVar);
        } else {
            LogUtil.h("ContactSync", 1, "RawContactBeanUtils", "setContactFieldsWithDataCursor else, mimeType: ", string);
        }
    }

    public static void bMt_(Cursor cursor, kaa kaaVar, boolean z) {
        if (cursor == null || kaaVar == null) {
            LogUtil.h("ContactSync", 1, "RawContactBeanUtils", "setContactHiCallFields: cursor or rawContactBean is null.");
        } else if ("vnd.huawei.cursor.item/hicall_device".equals(cursor.getString(2))) {
            bMy_(cursor, kaaVar, z);
        }
    }

    public static void bMs_(Cursor cursor, kaa kaaVar) {
        if (cursor == null || kaaVar == null) {
            LogUtil.h("ContactSync", 1, "RawContactBeanUtils", "setContactHiCallFields: cursor or rawContactBean is null.");
            return;
        }
        kaaVar.setVersion(cursor.getString(1));
        kaaVar.f(cursor.getString(2));
        kaaVar.m(cursor.getString(3));
        kaaVar.c(cursor.getString(4));
        kaaVar.d(cursor.getString(5));
        if (CommonUtil.ba()) {
            kaaVar.k(cursor.getString(6));
            kaaVar.h(cursor.getString(7));
        }
    }

    private static void bMA_(Cursor cursor, kaa kaaVar) {
        kaaVar.i(cursor.getString(3));
    }

    private static void bMw_(Cursor cursor, kaa kaaVar) {
        String string = cursor.getString(3);
        int i = cursor.getInt(4);
        if (c(string)) {
            LogUtil.h("ContactSync", 1, "RawContactBeanUtils", "email number is null");
            return;
        }
        if (i == 2) {
            kaaVar.a("vnd.android.cursor.item/email_v2", 2, string);
        } else if (i == 3) {
            kaaVar.a("vnd.android.cursor.item/email_v2", 3, string);
        } else {
            kaaVar.a("vnd.android.cursor.item/email_v2", 1, string);
        }
    }

    private static void bMB_(Cursor cursor, kaa kaaVar) {
        String string = cursor.getString(3);
        if (string == null) {
            string = "";
        }
        kaaVar.j(string);
    }

    private static void bMx_(Cursor cursor, kaa kaaVar) {
        int i = cursor.getInt(4);
        String string = cursor.getString(3);
        if (c(string)) {
            LogUtil.h("ContactSync", 1, "RawContactBeanUtils", "event value is null");
            return;
        }
        if (i == 0) {
            String string2 = cursor.getString(5);
            if (c(string2)) {
                kaaVar.a("vnd.android.cursor.item/contact_event", 1, string);
                return;
            } else {
                kaaVar.c("vnd.android.cursor.item/contact_event", string2, string);
                return;
            }
        }
        if (i == 1) {
            kaaVar.a("vnd.android.cursor.item/contact_event", 1, string);
            return;
        }
        if (i == 2) {
            kaaVar.a("vnd.android.cursor.item/contact_event", 2, string);
            return;
        }
        if (i == 3) {
            kaaVar.a("vnd.android.cursor.item/contact_event", 3, string);
        } else if (i == 4) {
            kaaVar.a("vnd.android.cursor.item/contact_event", 4, string);
        } else {
            LogUtil.h("ContactSync", 1, "RawContactBeanUtils", "setEventFields type: ", Integer.valueOf(i));
        }
    }

    private static void bME_(Cursor cursor, kaa kaaVar) {
        byte[] blob = cursor.getBlob(17);
        if (blob != null && blob.length > 0) {
            kaaVar.a(blob);
        }
        kaaVar.g(cursor.getString(12));
    }

    private static void bMC_(Cursor cursor, kaa kaaVar) {
        kaaVar.d("data1", cursor.getString(3));
        kaaVar.d("data4", cursor.getString(6));
    }

    private static void bMy_(Cursor cursor, kaa kaaVar, boolean z) {
        kaj kajVar = new kaj();
        String string = cursor.getString(3);
        if (!c(string)) {
            kajVar.o(string);
        }
        String string2 = cursor.getString(6);
        if (!c(string2)) {
            kajVar.c(string2);
        }
        kajVar.a(cursor.getInt(7));
        String string3 = cursor.getString(8);
        if (!c(string3)) {
            kajVar.c("true".equalsIgnoreCase(string3));
        }
        kajVar.d(cursor.getLong(9));
        String string4 = cursor.getString(10);
        if (!c(string4)) {
            kajVar.b("true".equalsIgnoreCase(string4));
        }
        kajVar.c(cursor.getInt(11));
        String string5 = cursor.getString(13);
        if (!c(string5)) {
            kajVar.a(string5);
        }
        String string6 = cursor.getString(15);
        if (!c(string6)) {
            kajVar.h(string6);
        }
        kajVar.a(cursor.getLong(18));
        kajVar.b(cursor.getLong(19));
        bMv_(cursor, z, kajVar);
        kaaVar.a(kajVar);
    }

    private static void bMz_(Cursor cursor, kaa kaaVar) {
        int i = cursor.getInt(7);
        String string = cursor.getString(3);
        if (TextUtils.isEmpty(string)) {
            LogUtil.h("ContactSync", 1, "RawContactBeanUtils", "setImFields: IM value is null or empty.");
            return;
        }
        if (b(i)) {
            kaaVar.a("vnd.android.cursor.item/im", i, string);
            return;
        }
        if (i == -1) {
            String string2 = cursor.getString(8);
            if (TextUtils.isEmpty(string2)) {
                kaaVar.a("vnd.android.cursor.item/im", 0, string);
                return;
            } else {
                kaaVar.c("vnd.android.cursor.item/im", string2, string);
                return;
            }
        }
        LogUtil.h("ContactSync", 1, "RawContactBeanUtils", "setImFields: other else branch: unsupported im type. ");
    }

    private static void bMv_(Cursor cursor, boolean z, kaj kajVar) {
        String string = cursor.getString(12);
        if (!c(string)) {
            if (z) {
                kajVar.d(string);
            } else {
                kajVar.j(string);
            }
        }
        String string2 = cursor.getString(14);
        if (!c(string2)) {
            if (z) {
                kajVar.i(string2);
            } else {
                kajVar.b(string2);
            }
        }
        String string3 = cursor.getString(16);
        if (z) {
            kajVar.f(string3);
        } else {
            kajVar.g(string3);
        }
        String string4 = cursor.getString(17);
        if (z) {
            return;
        }
        kajVar.e(string4);
    }

    private static void bMD_(Cursor cursor, kaa kaaVar) {
        String string = cursor.getString(3);
        int i = cursor.getInt(4);
        if (c(string)) {
            LogUtil.h("ContactSync", 1, "RawContactBeanUtils", "setPhoneFields: phone is null");
            return;
        }
        if (a(i)) {
            kaaVar.a("vnd.android.cursor.item/phone_v2", i, string);
        } else if (i == 0) {
            kaaVar.c("vnd.android.cursor.item/phone_v2", cursor.getString(5), string);
        } else {
            LogUtil.h("ContactSync", 1, "RawContactBeanUtils", "setPhoneFields: unknown phone type.");
        }
    }

    private static void bMG_(Cursor cursor, kaa kaaVar) {
        String string = cursor.getString(5);
        if (string == null) {
            string = "";
        }
        kaaVar.b("data3", string);
        String string2 = cursor.getString(4);
        if (string2 == null) {
            string2 = "";
        }
        kaaVar.b("data2", string2);
        String string3 = cursor.getString(7);
        if (string3 == null) {
            string3 = "";
        }
        kaaVar.b("data5", string3);
        String string4 = cursor.getString(6);
        if (string4 == null) {
            string4 = "";
        }
        kaaVar.b("data4", string4);
        String string5 = cursor.getString(8);
        if (string5 == null) {
            string5 = "";
        }
        kaaVar.b("data6", string5);
        String string6 = cursor.getString(11);
        if (string6 == null) {
            string6 = "";
        }
        kaaVar.b("data9", string6);
        String string7 = cursor.getString(9);
        if (string7 == null) {
            string7 = "";
        }
        kaaVar.b("data7", string7);
        String string8 = cursor.getString(10);
        kaaVar.b("data8", string8 != null ? string8 : "");
    }

    private static void bMq_(Cursor cursor, kaa kaaVar) {
        int[] iArr = {7, 8, 6, 9, 10, 11, 12};
        int i = cursor.getInt(4);
        String[] strArr = new String[7];
        for (int i2 = 0; i2 < 7; i2++) {
            String string = cursor.getString(iArr[i2]);
            if (TextUtils.isEmpty(string)) {
                string = "";
            }
            strArr[i2] = string;
        }
        if (i == 0) {
            String string2 = cursor.getString(5);
            if (TextUtils.isEmpty(string2)) {
                kaaVar.b(1, strArr);
                return;
            } else {
                kaaVar.e(string2, strArr);
                return;
            }
        }
        if (i == 1 || i == 2 || i == 3) {
            kaaVar.b(i, strArr);
        } else {
            LogUtil.h("ContactSync", 1, "RawContactBeanUtils", "setAddressFields: else branch.");
        }
    }

    private static void bMH_(Cursor cursor, kaa kaaVar) {
        String string = cursor.getString(3);
        if (TextUtils.isEmpty(string)) {
            LogUtil.h("ContactSync", 1, "RawContactBeanUtils", "setWebSiteFields: value is null.");
        }
        switch (cursor.getInt(4)) {
            case 1:
            case 2:
            case 3:
            case 6:
            case 7:
                kaaVar.a("vnd.android.cursor.item/website", 7, string);
                break;
            case 4:
                kaaVar.a("vnd.android.cursor.item/website", 4, string);
                break;
            case 5:
                kaaVar.a("vnd.android.cursor.item/website", 5, string);
                break;
            default:
                LogUtil.h("ContactSync", 1, "RawContactBeanUtils", "setWebSiteFields: default branch.");
                break;
        }
    }

    private static void bMF_(Cursor cursor, kaa kaaVar) {
        String string = cursor.getString(3);
        if (TextUtils.isEmpty(string)) {
            LogUtil.h("ContactSync", 1, "RawContactBeanUtils", "setRelationFields: value is null.");
            return;
        }
        int i = cursor.getInt(4);
        if (c(i)) {
            bMu_(cursor, kaaVar, string, i);
        } else {
            kaaVar.a("vnd.android.cursor.item/relation", i, string);
        }
    }

    private static void bMu_(Cursor cursor, kaa kaaVar, String str, int i) {
        if (i != 0) {
            LogUtil.h("ContactSync", 1, "RawContactBeanUtils", "setRelationFields: unknown relation type.");
            return;
        }
        String string = cursor.getString(5);
        if (TextUtils.isEmpty(string)) {
            kaaVar.a("vnd.android.cursor.item/relation", 1, str);
        } else {
            kaaVar.c("vnd.android.cursor.item/relation", string, str);
        }
    }

    private static boolean c(int i) {
        switch (i) {
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
            case 12:
            case 13:
            case 14:
                return false;
            default:
                LogUtil.h("ContactSync", 1, "RawContactBeanUtils", "isCustomRelationType: not support custom relation type.");
                return true;
        }
    }

    private static boolean b(int i) {
        switch (i) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
                return true;
            default:
                LogUtil.h("ContactSync", 1, "RawContactBeanUtils", "isImType: not support im type.");
                return false;
        }
    }

    private static boolean a(int i) {
        if (i != 10 && i != 17 && i != 20 && i != 12 && i != 13) {
            switch (i) {
                case 1:
                case 2:
                case 3:
                case 4:
                case 5:
                case 6:
                case 7:
                    break;
                default:
                    LogUtil.h("ContactSync", 1, "RawContactBeanUtils", "isPhoneType: not support phone type.");
                    return false;
            }
        }
        return true;
    }

    private static boolean c(String str) {
        return TextUtils.isEmpty(str);
    }
}
