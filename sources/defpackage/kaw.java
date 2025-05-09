package defpackage;

import android.text.TextUtils;
import com.huawei.hwdevice.phoneprocess.mgr.hwcontactsyncmgr.contacts.sync.utils.SyncHelper;
import com.huawei.hwlogsmodel.LogUtil;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class kaw {
    public static void e(StringBuffer stringBuffer, String str, String str2) {
        if (kat.b(str2)) {
            return;
        }
        stringBuffer.append(str).append(str2).append("\r\n");
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public static void a(String str, kaa kaaVar, StringBuffer stringBuffer) {
        char c;
        str.hashCode();
        switch (str.hashCode()) {
            case -1569536764:
                if (str.equals("vnd.android.cursor.item/email_v2")) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case -1328682538:
                if (str.equals("vnd.android.cursor.item/contact_event")) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case -601229436:
                if (str.equals("vnd.android.cursor.item/postal-address_v2")) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case 684173810:
                if (str.equals("vnd.android.cursor.item/phone_v2")) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            case 950831081:
                if (str.equals("vnd.android.cursor.item/im")) {
                    c = 4;
                    break;
                }
                c = 65535;
                break;
            case 1409846529:
                if (str.equals("vnd.android.cursor.item/relation")) {
                    c = 5;
                    break;
                }
                c = 65535;
                break;
            default:
                c = 65535;
                break;
        }
        if (c == 0) {
            a(kaaVar.b("vnd.android.cursor.item/email_v2"), stringBuffer, "EMAIL;X1-", SyncHelper.Encode.ENCODE);
            return;
        }
        if (c == 1) {
            e(kaaVar.b("vnd.android.cursor.item/contact_event"), stringBuffer, "X-CUSTOM-EVENT-0;X1-");
            return;
        }
        if (c == 2) {
            d(kaaVar.b(), stringBuffer);
            return;
        }
        if (c == 3) {
            e(kaaVar.b("vnd.android.cursor.item/phone_v2"), stringBuffer, "TEL;X1-");
        } else if (c == 4) {
            a(kaaVar.b("vnd.android.cursor.item/im"), stringBuffer, "X-CUSTOM-IM;X1-", SyncHelper.Encode.ENCODE);
        } else {
            if (c != 5) {
                return;
            }
            a(kaaVar.b("vnd.android.cursor.item/relation"), stringBuffer, "X-CUSTOM-RELATION-0;X1-", SyncHelper.Encode.ENCODE);
        }
    }

    private static void e(HashMap<String, List<String>> hashMap, StringBuffer stringBuffer, String str) {
        a(hashMap, stringBuffer, str, SyncHelper.Encode.NORMAL);
    }

    private static void a(HashMap<String, List<String>> hashMap, StringBuffer stringBuffer, String str, SyncHelper.Encode encode) {
        if (hashMap == null || hashMap.size() == 0) {
            return;
        }
        for (Map.Entry<String, List<String>> entry : hashMap.entrySet()) {
            String key = entry.getKey();
            List<String> value = entry.getValue();
            if (!TextUtils.isEmpty(key)) {
                String str2 = SyncHelper.Encode.ENCODE.equals(encode) ? str + kat.c(key) + ";ENCODING=QUOTED-PRINTABLE;CHARSET=UTF-8:" : str + kat.c(key) + ":";
                int size = value.size();
                for (int i = 0; i < size; i++) {
                    String str3 = value.get(i);
                    if (SyncHelper.Encode.ENCODE.equals(encode)) {
                        str3 = b(new String[]{str3}, SyncHelper.Transferred.NORMAL);
                    }
                    e(stringBuffer, str2, str3);
                }
            }
        }
    }

    private static void d(HashMap<String, List<String[]>> hashMap, StringBuffer stringBuffer) {
        if (hashMap == null || hashMap.size() == 0) {
            return;
        }
        for (Map.Entry<String, List<String[]>> entry : hashMap.entrySet()) {
            String key = entry.getKey();
            List<String[]> value = entry.getValue();
            if (!TextUtils.isEmpty(key)) {
                String str = "ADR;X1-" + kat.c(key) + ";ENCODING=QUOTED-PRINTABLE;CHARSET=UTF-8:";
                int size = value.size();
                for (int i = 0; i < size; i++) {
                    e(stringBuffer, str, b(value.get(i), SyncHelper.Transferred.TRANSFERRED));
                }
            }
        }
    }

    public static String b(String[] strArr, SyncHelper.Transferred transferred) {
        StringBuilder sb = new StringBuilder(256);
        int length = strArr.length;
        for (int i = 0; i < length; i++) {
            String str = strArr[i];
            if (SyncHelper.Transferred.TRANSFERRED.equals(transferred)) {
                str = kat.b(str) ? "" : kat.e(str);
            }
            sb.append(kat.b(str) ? "" : kar.d(str));
            if (i != length - 1) {
                sb.append(";");
            }
        }
        return sb.toString();
    }

    public static String b(String str) {
        return e(str, "");
    }

    public static String e(String str, String str2) {
        return kat.b(str) ? str2 : kar.d(kat.e(str));
    }

    public static void c(List<String> list, String str, StringBuffer stringBuffer) {
        a(list, str, stringBuffer, SyncHelper.Encode.NORMAL);
    }

    public static void a(List<String> list, String str, StringBuffer stringBuffer, SyncHelper.Encode encode) {
        if (list == null || list.size() <= 0) {
            return;
        }
        Collections.sort(list);
        int size = list.size();
        for (int i = 0; i < size; i++) {
            String str2 = list.get(i);
            if (SyncHelper.Encode.ENCODE.equals(encode)) {
                str2 = e(str2, "");
            }
            e(stringBuffer, str, str2);
        }
    }

    public static void b(List<String[]> list, String str, StringBuffer stringBuffer, SyncHelper.Encode encode) {
        String str2;
        if (list == null || list.isEmpty()) {
            return;
        }
        Collections.sort(list, new a());
        int size = list.size();
        for (int i = 0; i < size; i++) {
            String[] strArr = list.get(i);
            if (SyncHelper.Encode.ENCODE.equals(encode)) {
                str2 = b(strArr, SyncHelper.Transferred.TRANSFERRED);
            } else {
                LogUtil.a("ContactSync", 1, "VcardHelper", "do not use branch");
                str2 = "";
            }
            e(stringBuffer, str, str2);
        }
    }

    public static void e(JSONObject jSONObject, ArrayList<kaj> arrayList) throws JSONException {
        JSONArray jSONArray = new JSONArray();
        int size = arrayList.size();
        for (int i = 0; i < size; i++) {
            jSONArray.put(i, kaq.e(arrayList.get(i)));
        }
        jSONObject.put("hiCallDeviceList", jSONArray);
    }

    public static class a implements Comparator<String[]>, Serializable {
        private static final long serialVersionUID = 1;

        @Override // java.util.Comparator
        /* renamed from: d, reason: merged with bridge method [inline-methods] */
        public int compare(String[] strArr, String[] strArr2) {
            return kat.c(strArr, ",", "").compareToIgnoreCase(kat.c(strArr2, ",", ""));
        }
    }
}
