package defpackage;

import android.text.TextUtils;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.pluginachievement.manager.model.MedalConfigInfo;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes6.dex */
public class mdw {
    public static ArrayList<String> a(List<mcz> list, ArrayList<String> arrayList) {
        ArrayList<String> arrayList2 = new ArrayList<>(0);
        ArrayList arrayList3 = new ArrayList(0);
        if (koq.b(list)) {
            LogUtil.h("PLGACHIEVE_AchieveMedalDownRule", "findFirstMedalLevelIdList medalConfigInfoList is empty.");
            return arrayList2;
        }
        for (mcz mczVar : list) {
            if (mczVar instanceof MedalConfigInfo) {
                MedalConfigInfo medalConfigInfo = (MedalConfigInfo) mczVar;
                if (medalConfigInfo.acquireMedalType().length() < 3) {
                    String acquireMedalType = medalConfigInfo.acquireMedalType();
                    if (!arrayList3.contains(acquireMedalType)) {
                        arrayList3.add(acquireMedalType);
                    }
                }
            }
        }
        LogUtil.a("PLGACHIEVE_AchieveMedalDownRule", "findFirstMedalLevelIdList typeList ", arrayList3.toString());
        Iterator it = arrayList3.iterator();
        while (it.hasNext()) {
            String str = (String) it.next();
            String str2 = "";
            int i = 0;
            for (mcz mczVar2 : list) {
                if (mczVar2 instanceof MedalConfigInfo) {
                    MedalConfigInfo medalConfigInfo2 = (MedalConfigInfo) mczVar2;
                    if (medalConfigInfo2.acquireMedalType().equals(str) && !arrayList.contains(medalConfigInfo2.acquireMedalID()) && medalConfigInfo2.acquireMedalType().length() < 3) {
                        String acquireMedalID = medalConfigInfo2.acquireMedalID();
                        int acquireMedalLevel = medalConfigInfo2.acquireMedalLevel();
                        if (i == 0 || i >= acquireMedalLevel) {
                            i = acquireMedalLevel;
                            str2 = acquireMedalID;
                        }
                    }
                }
            }
            if (!TextUtils.isEmpty(str2)) {
                arrayList2.add(str2);
            }
        }
        LogUtil.a("PLGACHIEVE_AchieveMedalDownRule", "findFirstMedalLevelIdList list ", arrayList2.toString());
        return arrayList2;
    }
}
