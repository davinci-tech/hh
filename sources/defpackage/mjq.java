package defpackage;

import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.pluginachievement.manager.model.AchieveLevelTotalInfo;
import com.huawei.pluginachievement.manager.model.AchieveUserLevelInfo;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes6.dex */
public class mjq {
    public static mkk e(double d) {
        mkk mkkVar = new mkk();
        mkkVar.b(b(d));
        ArrayList<mki> arrayList = new ArrayList<>(20);
        for (int i = 1; i <= 20; i++) {
            arrayList.add(c(i));
        }
        mkkVar.b(arrayList);
        return mkkVar;
    }

    private static mki c(int i) {
        mki mkiVar = new mki();
        mkiVar.b(mlc.c(i));
        mkiVar.e(i);
        mkiVar.d(0.0d);
        return mkiVar;
    }

    public static mkk a(mkh mkhVar) {
        if (mkhVar == null) {
            return null;
        }
        mcz d = mkhVar.d();
        AchieveUserLevelInfo achieveUserLevelInfo = d instanceof AchieveUserLevelInfo ? (AchieveUserLevelInfo) d : null;
        List<mcz> a2 = mkhVar.a();
        if (a2 == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList(a2.size());
        for (mcz mczVar : a2) {
            if (mczVar instanceof AchieveLevelTotalInfo) {
                arrayList.add((AchieveLevelTotalInfo) mczVar);
            }
        }
        if (achieveUserLevelInfo == null) {
            return null;
        }
        int size = arrayList.size();
        int acquireUserLevel = achieveUserLevelInfo.acquireUserLevel();
        mkk mkkVar = new mkk();
        ArrayList<mki> arrayList2 = new ArrayList<>(20);
        int i = 1;
        int i2 = 0;
        int i3 = 0;
        while (i2 < size) {
            AchieveLevelTotalInfo achieveLevelTotalInfo = (AchieveLevelTotalInfo) arrayList.get(i2);
            i3 += achieveLevelTotalInfo.acquireUserNumber();
            i2++;
            if (i2 == acquireUserLevel) {
                i = achieveLevelTotalInfo.acquireUserNumber();
            }
        }
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            arrayList2.add(a((AchieveLevelTotalInfo) it.next(), i3));
        }
        mkkVar.b(d(achieveUserLevelInfo, i, i3));
        mkkVar.b(arrayList2);
        LogUtil.a("levelLocationRelationShipData", mkkVar.toString());
        mkkVar.d(achieveUserLevelInfo);
        return mkkVar;
    }

    private static mku b(double d) {
        mku mkuVar = new mku();
        int c = mlc.c(d);
        mkuVar.d(c);
        mkuVar.c(0.0d);
        int c2 = mlc.c(c + 1);
        int c3 = mlc.c(c);
        int e = mlc.e(d);
        mkuVar.a(c2 - c3);
        mkuVar.e(e - c3);
        return mkuVar;
    }

    private static mku d(AchieveUserLevelInfo achieveUserLevelInfo, int i, int i2) {
        mku mkuVar = new mku();
        int acquireUserExperience = achieveUserLevelInfo.acquireUserExperience();
        int e = mlc.e(acquireUserExperience);
        mkuVar.d(e);
        mkuVar.c(mlc.a(i, i2));
        int c = mlc.c(e + 1);
        int c2 = mlc.c(e);
        mkuVar.a(c - c2);
        mkuVar.e(acquireUserExperience - c2);
        return mkuVar;
    }

    public static mki a(AchieveLevelTotalInfo achieveLevelTotalInfo, int i) {
        mki mkiVar = new mki();
        int acquireLevel = achieveLevelTotalInfo.acquireLevel();
        double a2 = mlc.a(achieveLevelTotalInfo.acquireUserNumber(), i);
        mkiVar.b(mlc.c(acquireLevel));
        mkiVar.e(acquireLevel);
        mkiVar.d(a2);
        mkiVar.a(achieveLevelTotalInfo.acquireUserNumber());
        return mkiVar;
    }
}
