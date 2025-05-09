package defpackage;

import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.pluginachievement.medal.model.TrackData;
import java.util.ArrayList;
import java.util.Iterator;

/* loaded from: classes9.dex */
public class lmu {
    private static final Object b = new Object();

    public static void e(ArrayList<TrackData> arrayList) {
        if (koq.b(arrayList)) {
            return;
        }
        synchronized (b) {
            LogUtil.a("AchieveMedalDataMgr", "enter dealAchieveTrackData():");
            bnp bnpVar = new bnp();
            bnpVar.b("A4").dealMedalGenerate(arrayList);
            Iterator<TrackData> it = arrayList.iterator();
            while (it.hasNext()) {
                TrackData next = it.next();
                bnpVar.b(mlb.d(next.acquireType())).dealMedalGenerate(next);
            }
        }
    }
}
