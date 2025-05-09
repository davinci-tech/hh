package defpackage;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.pluginachievement.manager.model.MedalConfigInfo;
import com.huawei.pluginachievement.manager.model.MedalLocation;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;

/* loaded from: classes8.dex */
public class bnr {
    public static boolean e(meh mehVar, Context context) {
        if (context == null || mehVar == null) {
            return true;
        }
        if (mehVar.t() == null || mehVar.t().getHuid() == null) {
            mehVar.ad();
            if (mehVar.t() == null || mehVar.t().getHuid() == null) {
                LogUtil.h("PLGACHIEVE_MedalRule", "dealTrack userProfile or uid is null");
                return true;
            }
        }
        if (!TextUtils.isEmpty(mct.b(context, "personal"))) {
            return false;
        }
        LogUtil.h("PLGACHIEVE_MedalRule", "flag is null");
        return true;
    }

    public static void a(Context context, ArrayList<MedalConfigInfo> arrayList, String str) {
        LogUtil.a("PLGACHIEVE_MedalRule", "dealStableMedal stableMedalId == ", str);
        meh c = meh.c(context);
        StringBuilder sb = new StringBuilder();
        Iterator<MedalConfigInfo> it = arrayList.iterator();
        while (it.hasNext()) {
            MedalConfigInfo next = it.next();
            String acquireMedalID = next.acquireMedalID();
            sb.append(acquireMedalID);
            sb.append(",");
            if (str.equals(acquireMedalID)) {
                c.b(acquireMedalID, next.acquireMedalType(), next.acquireMedalLevel());
            } else {
                d(acquireMedalID);
            }
            Map<String, String> a2 = c.a(acquireMedalID, String.valueOf(next.acquireMedalLevel()));
            a2.put("countryCode", LoginInit.getInstance(context).getCountryCode(null));
            c.a(7, a2);
        }
        if (sb.length() > 1) {
            sb.deleteCharAt(sb.length() - 1);
            a(context, sb.toString());
            LogUtil.a("PLGACHIEVE_MedalRule", "dealStableMedal stableMedalIdList == ", sb.toString());
        }
    }

    public static void d(String str) {
        MedalLocation medalLocation = new MedalLocation();
        medalLocation.saveGainedCount(1);
        medalLocation.saveMedalGainedTime(String.valueOf(System.currentTimeMillis()));
        if (TextUtils.isEmpty(str)) {
            return;
        }
        medalLocation.saveMedalID(str);
        meh.c(BaseApplication.getContext()).e(medalLocation);
    }

    private static void a(Context context, String str) {
        HashMap hashMap = new HashMap(2);
        hashMap.put("_uploadMedal", str);
        mct.b(context, hashMap);
    }

    public static String b(long j) {
        return new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH).format(new Date(j));
    }
}
