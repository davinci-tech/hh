package defpackage;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.pluginachievement.manager.model.MedalConfigInfo;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/* loaded from: classes6.dex */
public class mek {
    public static void d(Context context, double d, meh mehVar, String str) {
        LogUtil.a("PLGACHIEVE_AchieveContinuousStableMedalService", "Enter dealMedalGenerate");
        if (mfe.a(mehVar, context)) {
            LogUtil.h("PLGACHIEVE_AchieveContinuousStableMedalService", "dealMedalGenerate isEmptyContextAndService!");
            return;
        }
        if (TextUtils.isEmpty(mct.b(context, "personal"))) {
            LogUtil.h("PLGACHIEVE_AchieveContinuousStableMedalService", "dealMedalGenerate flag is null");
            return;
        }
        HashMap hashMap = new HashMap(2);
        ArrayList arrayList = new ArrayList(8);
        List<mcz> b = mehVar.b(9, hashMap);
        ArrayList<String> l = mehVar.l();
        if (koq.b(b)) {
            LogUtil.h("PLGACHIEVE_AchieveContinuousStableMedalService", "medalConfigInfos isEmpty!");
            return;
        }
        LogUtil.a("PLGACHIEVE_AchieveContinuousStableMedalService", "medalConfigInfos size ", Integer.valueOf(b.size()), "lightUpMedalList  ", l.toString(), "value ", Double.valueOf(d));
        String str2 = "";
        int i = 0;
        for (mcz mczVar : b) {
            if (mczVar instanceof MedalConfigInfo) {
                MedalConfigInfo medalConfigInfo = (MedalConfigInfo) mczVar;
                if (str.equals(medalConfigInfo.acquireMedalType())) {
                    String acquireMedalID = medalConfigInfo.acquireMedalID();
                    int acquireMedalLevel = medalConfigInfo.acquireMedalLevel();
                    if (d >= acquireMedalLevel && !l.contains(acquireMedalID)) {
                        LogUtil.a("PLGACHIEVE_AchieveContinuousStableMedalService", "medalIdTmp ", acquireMedalID);
                        arrayList.add(medalConfigInfo);
                        if (acquireMedalLevel > i) {
                            str2 = medalConfigInfo.acquireMedalID();
                            i = acquireMedalLevel;
                        }
                    }
                }
            }
        }
        LogUtil.a("PLGACHIEVE_AchieveContinuousStableMedalService", "dealStableMedal MedalList  ", Integer.valueOf(arrayList.size()));
        mfe.d(context, (ArrayList<MedalConfigInfo>) arrayList, str2);
    }
}
