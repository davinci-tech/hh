package defpackage;

import com.amap.api.col.p0003sl.it;
import com.huawei.health.suggestion.h5pro.AudioConstant;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.CommonUtil;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class jku {

    /* renamed from: a, reason: collision with root package name */
    private static cwl f13921a = new cwl();

    public static String a(byte[] bArr) {
        String d = cvx.d(bArr);
        LogUtil.c("HwPayUtil", "getAllCardList, messageHex=", d);
        if (d == null || d.length() <= 4) {
            return null;
        }
        try {
            cwe a2 = f13921a.a(d.substring(4, d.length()));
            int i = 0;
            int i2 = 0;
            int i3 = 0;
            for (cwd cwdVar : a2.e()) {
                int a3 = CommonUtil.a(cwdVar.e(), 16);
                if (a3 == 1) {
                    i = CommonUtil.a(cwdVar.c(), 16);
                } else if (a3 == 2) {
                    i2 = CommonUtil.a(cwdVar.c(), 16);
                } else if (a3 == 3) {
                    i3 = CommonUtil.a(cwdVar.c(), 16);
                } else {
                    LogUtil.b("not support tlv Type!", new Object[0]);
                }
            }
            LogUtil.a("HwPayUtil", "cardNum =", Integer.valueOf(i), "CARD_INFO_START =", Integer.valueOf(i2), "CARD_INFO_END =", Integer.valueOf(i3));
            return e(a(a2.a()), i, i2, i3);
        } catch (cwg e) {
            LogUtil.b("HwPayUtil", "getCardInfo Tlv error e = ", e.getMessage());
            return null;
        } catch (NumberFormatException e2) {
            LogUtil.b("HwPayUtil", "getCardInfo error e = ", e2.getMessage());
            return null;
        }
    }

    public static boolean a(cwd cwdVar) {
        String c;
        if (cwdVar != null && (c = cwdVar.c()) != null && c.length() >= 40) {
            String substring = c.substring(0, 40);
            if (substring.replace(AudioConstant.WOMAN, "").isEmpty() || substring.replace(it.i, "").isEmpty()) {
                return true;
            }
        }
        return false;
    }

    private static List<String> a(List<cwe> list) throws NumberFormatException, cwg {
        ArrayList arrayList = new ArrayList(16);
        if (list != null && !list.isEmpty()) {
            Iterator<cwe> it = list.iterator();
            while (it.hasNext()) {
                Iterator<cwe> it2 = it.next().a().iterator();
                while (it2.hasNext()) {
                    List<cwd> e = it2.next().e();
                    LogUtil.c("HwPayUtil", "List, child tlv=", Integer.valueOf(e.size()));
                    arrayList.add(c(e).p());
                }
            }
        }
        return arrayList;
    }

    private static jlf c(List<cwd> list) throws NumberFormatException, cwg {
        jlf jlfVar = new jlf();
        for (cwd cwdVar : list) {
            LogUtil.c("HwPayUtil", "List, getTag = ", cwdVar.e(), "List, getValue = ", cwdVar.c());
            switch (CommonUtil.a(cwdVar.e(), 16)) {
                case 6:
                    jlfVar.b(cvx.e(cwdVar.c()));
                    break;
                case 7:
                    jlfVar.g(cvx.e(cwdVar.c()));
                    break;
                case 8:
                    jlfVar.f(cvx.e(cwdVar.c()));
                    break;
                case 9:
                    jlfVar.h(cvx.e(cwdVar.c()));
                    break;
                case 10:
                    jlfVar.b(CommonUtil.a(cwdVar.c(), 16));
                    if (jlfVar.a() == 0) {
                        jlfVar.b(2);
                        break;
                    } else {
                        break;
                    }
                case 11:
                    b(jlfVar, cwdVar);
                    break;
                default:
                    c(jlfVar, cwdVar);
                    break;
            }
        }
        return jlfVar;
    }

    private static void c(jlf jlfVar, cwd cwdVar) throws NumberFormatException, cwg {
        if (cwdVar == null || jlfVar == null) {
            return;
        }
        switch (CommonUtil.a(cwdVar.e(), 16)) {
            case 12:
                jlfVar.c(cvx.e(cwdVar.c()));
                break;
            case 13:
                jlfVar.i(cvx.e(cwdVar.c()));
                break;
            case 14:
                jlfVar.a(cvx.e(cwdVar.c()));
                break;
            case 15:
                jlfVar.e(cvx.e(cwdVar.c()));
                break;
            case 16:
                jlfVar.d(CommonUtil.a(cwdVar.c(), 16));
                break;
            case 17:
                jlfVar.b(CommonUtil.y(cwdVar.c()));
                break;
            case 18:
                jlfVar.a(CommonUtil.a(cwdVar.c(), 16));
                break;
            case 19:
                jlfVar.d(cvx.e(cwdVar.c()));
                break;
            case 20:
                jlfVar.a(CommonUtil.y(cwdVar.c()));
                break;
            case 21:
                jlfVar.j(cvx.e(cwdVar.c()));
                break;
            case 22:
                jlfVar.c(CommonUtil.y(cwdVar.c()));
                break;
            case 23:
                if (!a(cwdVar)) {
                    jlfVar.l(cvx.e(cwdVar.c()));
                    break;
                }
                break;
            default:
                LogUtil.h("HwPayUtil", "dont supprot the tlv type = ", Integer.valueOf(CommonUtil.a(cwdVar.e(), 16)));
                break;
        }
    }

    private static void b(jlf jlfVar, cwd cwdVar) throws NumberFormatException {
        if (cwdVar == null) {
            return;
        }
        if (CommonUtil.a(cwdVar.c(), 16) == 1) {
            jlfVar.d(true);
        } else {
            jlfVar.d(false);
        }
    }

    private static String e(List<String> list, int i, int i2, int i3) {
        JSONArray jSONArray;
        JSONObject jSONObject = new JSONObject();
        if (list != null) {
            jSONArray = new JSONArray();
            Iterator<String> it = list.iterator();
            while (it.hasNext()) {
                jSONArray.put(it.next());
            }
        } else {
            jSONArray = null;
        }
        try {
            jSONObject.put("cardInfo", jSONArray);
            jSONObject.put("cardNum", i);
            jSONObject.put("cardInfoStart", i2);
            jSONObject.put("cardInfoEnd", i3);
        } catch (JSONException e) {
            LogUtil.b("HwPayUtil", "generateCardInfo happen jsonException", e.getMessage());
        }
        return jSONObject.toString();
    }

    public static Object c(byte[] bArr) {
        String d = cvx.d(bArr);
        if (d == null || d.length() < 4) {
            LogUtil.h("HwPayUtil", "parseAccessConfig messageHex is error");
            return null;
        }
        LogUtil.a("HwPayUtil", "parseAccessConfig messageHex", d);
        try {
            List<cwd> e = f13921a.a(d.substring(4, d.length())).e();
            if (e == null) {
                LogUtil.h("HwPayUtil", "parseAccessConfig tlvs is null");
                return null;
            }
            jkt jktVar = new jkt();
            for (cwd cwdVar : e) {
                int a2 = CommonUtil.a(cwdVar.e(), 16);
                if (a2 == 1) {
                    jktVar.e(CommonUtil.a(cwdVar.c(), 16));
                } else if (a2 == 2) {
                    jktVar.a(CommonUtil.a(cwdVar.c(), 16));
                } else if (a2 == 3) {
                    jktVar.c(CommonUtil.a(cwdVar.c(), 16));
                } else if (a2 == 127) {
                    jktVar.d(CommonUtil.a(cwdVar.c(), 16));
                } else {
                    LogUtil.h("HwPayUtil", "parseAccessConfig is default");
                }
            }
            return jktVar.a();
        } catch (cwg unused) {
            LogUtil.b("HwPayUtil", "parseAccessConfig TlvException");
            return null;
        }
    }
}
