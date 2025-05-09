package defpackage;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import androidx.core.graphics.drawable.DrawableCompat;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.huawei.haf.bundle.extension.ComponentInfo;
import com.huawei.healthcloud.plugintrack.ui.sporttypeconfig.bean.HwSportTypeInfo;
import com.huawei.hihealthservice.old.model.OldToNewMotionPath;
import com.huawei.hms.kit.awareness.barrier.internal.e.a;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.LanguageUtil;
import health.compact.a.SharedPreferenceManager;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes7.dex */
public class rds {
    private static final int b = Color.argb(153, 0, 0, 0);
    private static final int c = Color.argb(153, 255, 255, 255);

    public static String c(rdy rdyVar) {
        if (rdyVar != null) {
            return rdyVar.c() != 0 ? BaseApplication.getContext().getResources().getString(rdyVar.c()) : "";
        }
        LogUtil.h("SportMotionStringUtils", "itemData is null ");
        return "";
    }

    public static int e(String str) {
        int b2 = !TextUtils.isEmpty(str) ? gxz.b(str, BaseApplication.getContext()) : 0;
        LogUtil.a("SportMotionStringUtils", "sportTypeRes:", str, "sportTypeNameId:", Integer.valueOf(b2));
        return b2;
    }

    public static Drawable dMx_(Drawable drawable, int i) {
        if (LanguageUtil.bc(BaseApplication.getContext())) {
            return nrf.cJH_(nrz.cKm_(BaseApplication.getContext(), drawable), i);
        }
        return nrf.cJH_(drawable, i);
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x008b  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0039  */
    /* JADX WARN: Removed duplicated region for block: B:9:0x0049  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static void a(int r6) {
        /*
            java.lang.String r0 = "recentlyAdded"
            java.lang.String r1 = "SportMotionStringUtils"
            r2 = 20002(0x4e22, float:2.8029E-41)
            com.google.gson.Gson r3 = new com.google.gson.Gson     // Catch: com.google.gson.JsonSyntaxException -> L2b
            r3.<init>()     // Catch: com.google.gson.JsonSyntaxException -> L2b
            android.content.Context r4 = com.huawei.hwcommonmodel.application.BaseApplication.getContext()     // Catch: com.google.gson.JsonSyntaxException -> L2b
            java.lang.String r5 = java.lang.String.valueOf(r2)     // Catch: com.google.gson.JsonSyntaxException -> L2b
            java.lang.String r4 = health.compact.a.SharedPreferenceManager.b(r4, r5, r0)     // Catch: com.google.gson.JsonSyntaxException -> L2b
            rds$3 r5 = new rds$3     // Catch: com.google.gson.JsonSyntaxException -> L2b
            r5.<init>()     // Catch: com.google.gson.JsonSyntaxException -> L2b
            java.lang.reflect.Type r5 = r5.getType()     // Catch: com.google.gson.JsonSyntaxException -> L2b
            java.lang.Object r3 = r3.fromJson(r4, r5)     // Catch: com.google.gson.JsonSyntaxException -> L2b
            java.util.List r3 = (java.util.List) r3     // Catch: com.google.gson.JsonSyntaxException -> L2b
            boolean r4 = e(r3, r6)     // Catch: com.google.gson.JsonSyntaxException -> L2c
            goto L37
        L2b:
            r3 = 0
        L2c:
            java.lang.String r4 = "updateSportTypeId jsonSyntaxException "
            java.lang.Object[] r4 = new java.lang.Object[]{r4}
            com.huawei.hwlogsmodel.LogUtil.b(r1, r4)
            r4 = 1
        L37:
            if (r3 != 0) goto L47
            java.lang.String r3 = "sportTypeIdList is null"
            java.lang.Object[] r3 = new java.lang.Object[]{r3}
            com.huawei.hwlogsmodel.LogUtil.h(r1, r3)
            java.util.ArrayList r3 = new java.util.ArrayList
            r3.<init>()
        L47:
            if (r4 == 0) goto L8b
            java.lang.String r4 = "add sportTypeId:"
            java.lang.Integer r5 = java.lang.Integer.valueOf(r6)
            java.lang.Object[] r4 = new java.lang.Object[]{r4, r5}
            com.huawei.hwlogsmodel.LogUtil.a(r1, r4)
            r4 = 0
            java.lang.Integer r6 = java.lang.Integer.valueOf(r6)
            r3.add(r4, r6)
            int r6 = r3.size()
            r4 = 6
            if (r6 <= r4) goto L71
            java.lang.String r6 = "sportTypeId enter remove"
            java.lang.Object[] r6 = new java.lang.Object[]{r6}
            com.huawei.hwlogsmodel.LogUtil.a(r1, r6)
            r3.remove(r4)
        L71:
            android.content.Context r6 = com.huawei.hwcommonmodel.application.BaseApplication.getContext()
            com.google.gson.Gson r1 = new com.google.gson.Gson
            r1.<init>()
            java.lang.String r1 = r1.toJson(r3)
            health.compact.a.StorageParams r3 = new health.compact.a.StorageParams
            r3.<init>()
            java.lang.String r2 = java.lang.String.valueOf(r2)
            health.compact.a.SharedPreferenceManager.e(r6, r2, r0, r1, r3)
            return
        L8b:
            java.lang.String r6 = "sportTypeId is already added"
            java.lang.Object[] r6 = new java.lang.Object[]{r6}
            com.huawei.hwlogsmodel.LogUtil.a(r1, r6)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.rds.a(int):void");
    }

    private static boolean e(List<Integer> list, int i) {
        if (bkz.e(list)) {
            LogUtil.h("SportMotionStringUtils", "handleSportTypeIdList sportTypeIdList is null");
            return true;
        }
        Iterator<Integer> it = list.iterator();
        while (it.hasNext()) {
            if (i == it.next().intValue()) {
                return false;
            }
        }
        return true;
    }

    public static List<rdy> b(int i, String str) {
        List list;
        ArrayList arrayList = new ArrayList();
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("SportMotionStringUtils", "sportGroupTypeId is null");
            return arrayList;
        }
        try {
            list = (List) new Gson().fromJson(SharedPreferenceManager.b(BaseApplication.getContext(), String.valueOf(20002), "recentlyAdded"), new TypeToken<List<Integer>>() { // from class: rds.5
            }.getType());
        } catch (JsonSyntaxException unused) {
            LogUtil.b("SportMotionStringUtils", "getSportTypeIdList jsonSyntaxException");
            list = null;
        }
        if (bkz.e(list)) {
            LogUtil.h("SportMotionStringUtils", "sportTypeIds is null");
            return arrayList;
        }
        for (int i2 = 0; i2 < list.size(); i2++) {
            HwSportTypeInfo d = hln.c(BaseApplication.getContext()).d(((Integer) list.get(i2)).intValue());
            rdy rdyVar = new rdy();
            rdyVar.c("recentlyAdded");
            rdyVar.a(d.getSportTypeId());
            rdyVar.b(d.getMet());
            rdyVar.d(d.getSportTypeId() == i && str.equals("recentlyAdded"));
            rdyVar.c(e(d.getSportTypeRes()));
            rdyVar.a(d.getHistoryList().getItemImg());
            rdyVar.dMB_(d.getHistoryList().getItemDrawable());
            rdyVar.b(hkc.d(d.getSportTypeId()));
            arrayList.add(rdyVar);
        }
        return arrayList;
    }

    public static int d() {
        if (nrt.a(BaseApplication.getContext())) {
            return c;
        }
        return b;
    }

    public static Drawable dMw_(Drawable drawable, int i) {
        Drawable wrap = DrawableCompat.wrap(drawable);
        DrawableCompat.setTint(wrap, i);
        return wrap;
    }

    public static boolean d(int i) {
        return Arrays.asList(263, 286, 287, 288, Integer.valueOf(ComponentInfo.PluginPay_A_N), 283, 512, 0, Integer.valueOf(a.z), Integer.valueOf(OldToNewMotionPath.SPORT_TYPE_OTHER_SPORT), 10001, 290, 291, 222).contains(Integer.valueOf(i));
    }
}
