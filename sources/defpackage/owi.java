package defpackage;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.health.health.utils.functionsetcard.manager.model.CardConfig;
import com.huawei.health.health.utils.functionsetcard.reader.FunctionSetSubCardData;
import com.huawei.health.messagecenter.model.CommonUtil;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwlogsmodel.LogUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/* loaded from: classes6.dex */
public class owi {
    private static boolean b = false;
    private static boolean c = false;

    public static void d(boolean z) {
        b = z;
    }

    public static void c(Context context, List<FunctionSetSubCardData> list, boolean z) {
        int i;
        int i2;
        if (context == null) {
            return;
        }
        if (c) {
            LogUtil.a("FunctionSetCardBiUtil", "already upload");
            return;
        }
        if (koq.b(list)) {
            i = 0;
            i2 = 0;
        } else {
            i = 0;
            i2 = 0;
            for (int i3 = 0; i3 < list.size(); i3++) {
                FunctionSetSubCardData functionSetSubCardData = list.get(i3);
                if (functionSetSubCardData != null) {
                    if (functionSetSubCardData.hasCardData()) {
                        i++;
                    } else {
                        i2++;
                    }
                }
            }
        }
        HashMap hashMap = new HashMap();
        hashMap.put("click", 1);
        hashMap.put("dataCardNumber", Integer.valueOf(i));
        hashMap.put("noDataCardNumber", Integer.valueOf(i2));
        hashMap.put("smartRecommend", Boolean.valueOf(z));
        LogUtil.a("FunctionSetCardBiUtil", "onDestroy, eventMap: ", hashMap.toString());
        ixx.d().d(context, AnalyticsValue.FUNCTION_CARD_2040168.value(), hashMap, 0);
        c = true;
    }

    public static void b(Context context, FunctionSetSubCardData functionSetSubCardData, boolean z, int i) {
        if (context == null || functionSetSubCardData == null) {
            return;
        }
        a(functionSetSubCardData, i, z);
        e(context, functionSetSubCardData);
    }

    public static void a(FunctionSetSubCardData functionSetSubCardData, int i, boolean z) {
        if (functionSetSubCardData == null) {
            LogUtil.a("FunctionSetCardBiUtil", "addOrDeleteCardBiEvent, cardReader is null");
            return;
        }
        HashMap hashMap = new HashMap();
        hashMap.put("click", 1);
        hashMap.put("cardName", functionSetSubCardData.getCardId());
        hashMap.put("smartRecommend", Boolean.valueOf(z));
        hashMap.put("changeType", Integer.valueOf(i));
        if (i == 1) {
            hashMap.put("noDataCardType", Integer.valueOf(functionSetSubCardData.getCurrentIsLargeCard() ? 1 : 2));
        } else if (i == 2) {
            hashMap.put("noHideCardType", Boolean.valueOf(functionSetSubCardData.hasCardData()));
        } else if (i == 3) {
            hashMap.put("hideCardType", Boolean.valueOf(functionSetSubCardData.hasCardData()));
        } else {
            LogUtil.a("FunctionSetCardBiUtil", "addOrDeleteCardBiEvent other changeType : ", Integer.valueOf(i));
        }
        LogUtil.a("FunctionSetCardBiUtil", "addOrDeleteCardBiEvent, eventMap: ", hashMap.toString());
        ixx.d().d(BaseApplication.getContext(), AnalyticsValue.FUNCTION_CARD_2040169.value(), hashMap, 0);
    }

    public static void e(Context context, FunctionSetSubCardData functionSetSubCardData) {
        if (functionSetSubCardData == null) {
            LogUtil.h("FunctionSetCardBiUtil", "reader is null");
            return;
        }
        LogUtil.a("FunctionSetCardBiUtil", "biEventCardShow FUNCTION_CARD_2040170");
        ixx.d().a(context, AnalyticsValue.FUNCTION_CARD_2040170.value(), functionSetSubCardData.getShowOrClickBiInfo(0), 0);
        a(context, functionSetSubCardData);
    }

    public static void c(Context context, FunctionSetSubCardData functionSetSubCardData) {
        if (functionSetSubCardData == null) {
            LogUtil.h("FunctionSetCardBiUtil", "reader is null");
        } else {
            ixx.d().a(context, "2010114", functionSetSubCardData.getShowOrClickBiInfo(1), 0);
        }
    }

    private static int d(String str, List<FunctionSetSubCardData> list, Context context) {
        CardConfig cardConfig;
        for (int i = 0; i < list.size(); i++) {
            FunctionSetSubCardData functionSetSubCardData = list.get(i);
            if (functionSetSubCardData != null && (cardConfig = functionSetSubCardData.getCardConfig()) != null) {
                String cardId = cardConfig.getCardId();
                if (!TextUtils.isEmpty(cardId) && cardId.equals(str)) {
                    return i;
                }
            }
        }
        return -1;
    }

    public static void b(Context context, int i) {
        if (context == null) {
            return;
        }
        HashMap hashMap = new HashMap();
        hashMap.put("click", 1);
        hashMap.put(CommonUtil.PAGE_TYPE, Integer.valueOf(i));
        hashMap.put("smartRecommend", Boolean.valueOf(b));
        LogUtil.a("FunctionSetCardBiUtil", "uploadBiEvent, FUNCTION_CARD_2040171: ", hashMap.toString());
        ixx.d().d(context, AnalyticsValue.FUNCTION_CARD_2040171.value(), hashMap, 0);
    }

    public static void b(Context context, boolean z, int i) {
        if (context == null) {
            return;
        }
        HashMap hashMap = new HashMap();
        hashMap.put("click", 1);
        hashMap.put("event", Integer.valueOf(i));
        hashMap.put("smartRecommend", Boolean.valueOf(z));
        LogUtil.a("FunctionSetCardBiUtil", "uploadBiEventLongClick, map: ", hashMap.toString());
        ixx.d().d(context, "2010105", hashMap, 0);
    }

    public static void a(Context context, boolean z, int i, FunctionSetSubCardData functionSetSubCardData) {
        String cardId = functionSetSubCardData.getCardId();
        HashMap hashMap = new HashMap();
        hashMap.put("click", 1);
        hashMap.put("from", Integer.valueOf(i));
        hashMap.put("cardName", cardId);
        hashMap.put("smartRecommend", Boolean.valueOf(z));
        LogUtil.a("FunctionSetCardBiUtil", "uploadBiEventClickEditCard, map: ", hashMap.toString());
        ixx.d().d(context.getApplicationContext(), AnalyticsValue.HEALTH_HOME_MANAGERMENT_CARD_2010032.value(), hashMap, 0);
    }

    public static void d(Context context, int i, FunctionSetSubCardData functionSetSubCardData, List<FunctionSetSubCardData> list, boolean z, int i2) {
        String cardId = functionSetSubCardData.getCardId();
        int d = d(cardId, list, context);
        HashMap hashMap = new HashMap();
        hashMap.put("cardName", cardId);
        hashMap.put("curPosition", Integer.valueOf(d));
        hashMap.put("addResult", "");
        hashMap.put("deleteResult", cardId);
        hashMap.put("positionResult", a(context, d, list));
        hashMap.put("click", 1);
        hashMap.put("type", Integer.valueOf(i));
        hashMap.put("from", Integer.valueOf(i2));
        hashMap.put("smartRecommend", Boolean.valueOf(z));
        LogUtil.a("FunctionSetCardBiUtil", "uploadBiEventEditCard, map: ", hashMap.toString());
        ixx.d().d(context.getApplicationContext(), "2060074", hashMap, 0);
    }

    private static String a(Context context, int i, List<FunctionSetSubCardData> list) {
        if (i >= list.size() - 1) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        while (true) {
            int i2 = i + 1;
            if (i2 >= list.size()) {
                break;
            }
            if (list.get(i2) != null) {
                sb.append(list.get(i2).getCardId());
                sb.append(":");
                sb.append(i2);
                sb.append("->");
                sb.append(i);
                sb.append(";");
            }
            i = i2;
        }
        String sb2 = sb.toString();
        return TextUtils.isEmpty(sb2) ? sb2 : sb2.substring(0, sb2.length() - 1);
    }

    public static void c(Context context, boolean z, boolean z2, int i) {
        HashMap hashMap = new HashMap();
        hashMap.put("smartSwitch", Boolean.valueOf(z));
        hashMap.put("click", 1);
        LogUtil.a("FunctionSetCardBiUtil", "uploadBiEventSwitchStateChanged, map: ", hashMap.toString());
        ixx.d().d(context.getApplicationContext(), "2010115", hashMap, 0);
    }

    public static void e(Context context) {
        HashMap hashMap = new HashMap();
        hashMap.put("scroll", "1");
        hashMap.put("smartRecommend", Boolean.valueOf(b));
        LogUtil.a("FunctionSetCardBiUtil", "uploadBiEventHomeFragmentScrollToBottom, map: ", hashMap.toString());
        ixx.d().d(context.getApplicationContext(), "2010065", hashMap, 0);
    }

    public static void b(Context context, int i, boolean z, ArrayList<ojs> arrayList, int i2, List<ojs> list) {
        LogUtil.a("FunctionSetCardBiUtil", "lastCardList: ", arrayList);
        LogUtil.a("FunctionSetCardBiUtil", "curCardList: ", list);
        HashMap hashMap = new HashMap();
        hashMap.put("addResult", a(arrayList, list, i2));
        hashMap.put("deleteResult", e(arrayList, list, i2));
        hashMap.put("positionResult", d(arrayList, list, i2));
        hashMap.put("click", 1);
        hashMap.put("from", Integer.valueOf(i));
        hashMap.put("smartRecommend", Boolean.valueOf(z));
        LogUtil.a("FunctionSetCardBiUtil", "uploadBiEventCardPositionChanged, map: ", hashMap.toString());
        ixx.d().d(context.getApplicationContext(), "2060074", hashMap, 0);
    }

    private static String d(ArrayList<ojs> arrayList, List<ojs> list, int i) {
        int indexOf;
        StringBuilder sb = new StringBuilder();
        for (int i2 = 0; i2 < arrayList.size(); i2++) {
            if (arrayList.get(i2) != null && (indexOf = list.indexOf(arrayList.get(i2))) != -1 && indexOf != i2 && i2 < i && list.get(indexOf).c() == 1) {
                sb.append(arrayList.get(i2).d());
                sb.append(":");
                sb.append(i2);
                sb.append("->");
                sb.append(indexOf);
                sb.append(";");
            }
        }
        String sb2 = sb.toString();
        return TextUtils.isEmpty(sb2) ? sb2 : sb2.substring(0, sb2.length() - 1);
    }

    private static String e(ArrayList<ojs> arrayList, List<ojs> list, int i) {
        ArrayList arrayList2 = new ArrayList();
        for (int i2 = 0; i2 < arrayList.size(); i2++) {
            if (arrayList.get(i2) != null) {
                String d = arrayList.get(i2).d();
                if (i2 < i && a(list, d, false)) {
                    arrayList2.add(d);
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i3 = 0; i3 < arrayList2.size(); i3++) {
            sb.append((String) arrayList2.get(i3));
            if (i3 != arrayList2.size() - 1) {
                sb.append(";");
            }
        }
        return sb.toString();
    }

    private static String a(ArrayList<ojs> arrayList, List<ojs> list, int i) {
        ArrayList arrayList2 = new ArrayList();
        for (int i2 = 0; i2 < list.size(); i2++) {
            if (list.get(i2) != null) {
                String d = list.get(i2).d();
                if (list.get(i2).c() == 1 && b(arrayList, d, i)) {
                    arrayList2.add(d);
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i3 = 0; i3 < arrayList2.size(); i3++) {
            sb.append((String) arrayList2.get(i3));
            if (i3 != arrayList2.size() - 1) {
                sb.append(";");
            }
        }
        return sb.toString();
    }

    private static boolean b(ArrayList<ojs> arrayList, String str, int i) {
        for (int i2 = 0; i2 < arrayList.size(); i2++) {
            if (arrayList.get(i2) != null) {
                String d = arrayList.get(i2).d();
                if (!TextUtils.isEmpty(d) && d.equals(str) && i2 >= i) {
                    return true;
                }
            }
        }
        return false;
    }

    /* JADX WARN: Code restructure failed: missing block: B:22:0x003d, code lost:
    
        r4 = true;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static boolean a(java.util.List<defpackage.ojs> r6, java.lang.String r7, boolean r8) {
        /*
            r0 = 0
            r1 = r0
        L2:
            int r2 = r6.size()
            if (r1 >= r2) goto L4c
            java.lang.Object r2 = r6.get(r1)
            if (r2 != 0) goto Lf
            goto L49
        Lf:
            java.lang.Object r2 = r6.get(r1)
            ojs r2 = (defpackage.ojs) r2
            java.lang.String r2 = r2.d()
            boolean r3 = android.text.TextUtils.isEmpty(r2)
            if (r3 == 0) goto L20
            goto L49
        L20:
            r3 = 1
            if (r8 == 0) goto L30
            java.lang.Object r4 = r6.get(r1)
            ojs r4 = (defpackage.ojs) r4
            int r4 = r4.c()
            if (r4 != r3) goto L3f
            goto L3d
        L30:
            java.lang.Object r4 = r6.get(r1)
            ojs r4 = (defpackage.ojs) r4
            int r4 = r4.c()
            r5 = 2
            if (r4 != r5) goto L3f
        L3d:
            r4 = r3
            goto L40
        L3f:
            r4 = r0
        L40:
            boolean r2 = r2.equals(r7)
            if (r2 == 0) goto L49
            if (r4 == 0) goto L49
            return r3
        L49:
            int r1 = r1 + 1
            goto L2
        L4c:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.owi.a(java.util.List, java.lang.String, boolean):boolean");
    }

    private static void a(Context context, FunctionSetSubCardData functionSetSubCardData) {
        CardConfig cardConfig;
        if (functionSetSubCardData == null || (cardConfig = functionSetSubCardData.getCardConfig()) == null || !"PHYSIOLOGICAL_CYCLE_CARD_KEY_NEW".equals(cardConfig.getCardId())) {
            return;
        }
        int cardType = !functionSetSubCardData.hasCardData() ? 0 : functionSetSubCardData.getCardType();
        HashMap hashMap = new HashMap();
        hashMap.put("click", 1);
        hashMap.put("cardType", Integer.valueOf(cardType));
        ixx.d().d(context.getApplicationContext(), "2200013", hashMap, 0);
    }
}
