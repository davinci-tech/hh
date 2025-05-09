package defpackage;

import com.huawei.health.R;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* loaded from: classes6.dex */
public class mji {
    public static ArrayList<mkg> c(List<mdf> list) {
        if (list == null) {
            LogUtil.a("PLGACHIEVE_AchieveKakaInitLayoutManager", "kakaTaskInfos is null");
            return new ArrayList<>();
        }
        ArrayList<mkg> arrayList = new ArrayList<>(8);
        Collections.sort(list);
        d(list, arrayList);
        return arrayList;
    }

    private static mkj d(mdf mdfVar) {
        mkj mkjVar = new mkj();
        if (mdfVar == null) {
            return mkjVar;
        }
        mkjVar.c(mdfVar.b());
        mkjVar.e(mdfVar.h());
        mkjVar.b(1);
        mkjVar.b(mdfVar.f());
        mkjVar.j(e(mdfVar));
        mkjVar.a(mdfVar.t());
        mkjVar.c(mdfVar.n());
        mkjVar.a(mdfVar.e());
        mkjVar.g(mdfVar.ag());
        mkjVar.e(mdfVar.x());
        mkjVar.d(mdfVar.ad());
        mkjVar.g(mdfVar.aa());
        mkjVar.i(mdfVar.z());
        mkjVar.d(mdfVar.a());
        mkjVar.f(mdfVar.ac());
        mkjVar.j(mdfVar.ae());
        return mkjVar;
    }

    private static String e(mdf mdfVar) {
        long a2 = mle.a(mct.b(BaseApplication.getContext(), "kakaSyncDate"));
        long currentTimeMillis = System.currentTimeMillis();
        long c = mdfVar.c();
        if (mdfVar.s() == 1 && mle.a(a2, currentTimeMillis) && mle.a(c, currentTimeMillis)) {
            String valueOf = String.valueOf(0);
            LogUtil.a("PLGACHIEVE_AchieveKakaInitLayoutManager", "getTaskRecord is ", mdfVar.h(), " lastSyncDate ", Long.valueOf(a2), " taskLastTimestamp ", Long.valueOf(c), " currentTime ", Long.valueOf(currentTimeMillis));
            return valueOf;
        }
        return mdfVar.p();
    }

    private static boolean c(int i, mdf mdfVar) {
        if (i == 0) {
            return mle.d(mdfVar.ag());
        }
        if (i != 1) {
            return false;
        }
        return !mle.d(mdfVar.ag());
    }

    private static ArrayList<mkg> d(List<mdf> list, int i) {
        ArrayList arrayList = new ArrayList(2);
        ArrayList arrayList2 = new ArrayList(2);
        ArrayList arrayList3 = new ArrayList(2);
        boolean z = true;
        for (mdf mdfVar : list) {
            if (mdfVar == null || !c(i, mdfVar) || !mle.k(String.valueOf(mdfVar.ag()))) {
                LogUtil.h("PLGACHIEVE_AchieveKakaInitLayoutManager", "getDataListByType kakaTaskInfo is null or invalid");
            } else {
                if (mle.a(mdfVar.ag()) && mdfVar.j() > 0) {
                    LogUtil.a("PLGACHIEVE_AchieveKakaInitLayoutManager", "acquireTaskFrequency()=", Integer.valueOf(mdfVar.j()));
                    mdfVar.i(String.valueOf(1));
                }
                mkg mkgVar = new mkg();
                mkgVar.e(1);
                mkgVar.c(mdfVar.h());
                mkj d = d(mdfVar);
                d.h(String.valueOf(i));
                mkgVar.e(d);
                int e = nsn.e(d.f());
                if (i == 0) {
                    if (!a(e, mdfVar)) {
                        d(e, arrayList, arrayList3, mkgVar);
                        z = false;
                    }
                } else if (e > 1) {
                    arrayList2.add(mkgVar);
                } else if (e == 1) {
                    arrayList.add(mkgVar);
                } else {
                    arrayList3.add(mkgVar);
                }
            }
        }
        ArrayList<mkg> arrayList4 = new ArrayList<>(list.size());
        arrayList4.addAll(arrayList);
        arrayList4.addAll(arrayList3);
        arrayList4.addAll(arrayList2);
        if (i == 0 && z) {
            arrayList4.clear();
        }
        return arrayList4;
    }

    private static void d(int i, List<mkg> list, List<mkg> list2, mkg mkgVar) {
        if (i == 1) {
            list.add(mkgVar);
        } else {
            list2.add(mkgVar);
        }
    }

    private static boolean a(int i, mdf mdfVar) {
        if (i > 1) {
            return true;
        }
        if (mdfVar.ag() == 10005) {
            return !mle.e(mdfVar);
        }
        return false;
    }

    private static void c(ArrayList<String> arrayList) {
        arrayList.add(e(R.string._2130840770_res_0x7f020cc2));
        arrayList.add(e(R.string._2130840769_res_0x7f020cc1));
    }

    private static String e(int i) {
        return BaseApplication.getContext().getResources().getString(i);
    }

    private static void d(List<mdf> list, ArrayList<mkg> arrayList) {
        LogUtil.a("PLGACHIEVE_AchieveKakaInitLayoutManager", "initLayoutData kakaTaskInfos size = ", Integer.valueOf(list.size()));
        ArrayList arrayList2 = new ArrayList(8);
        c((ArrayList<String>) arrayList2);
        for (int i = 0; i < arrayList2.size(); i++) {
            ArrayList<mkg> d = d(list, i);
            int size = d.size();
            if (size != 0) {
                arrayList.add(b((String) arrayList2.get(i)));
                for (int i2 = 0; i2 < size; i2++) {
                    mkg mkgVar = d.get(i2);
                    if (i2 == size - 1) {
                        mkgVar.b(true);
                    }
                    if (i2 == 0) {
                        mkgVar.d(true);
                    }
                    arrayList.add(mkgVar);
                }
            }
        }
        LogUtil.a("PLGACHIEVE_AchieveKakaInitLayoutManager", "initLayoutData relationShipDataList size = ", Integer.valueOf(arrayList.size()));
    }

    private static mkg b(String str) {
        mkg mkgVar = new mkg();
        mkgVar.e(0);
        mkgVar.a(str);
        mkgVar.c("");
        mkgVar.e((mkj) null);
        return mkgVar;
    }

    public static mkj c(mdf mdfVar) {
        return d(mdfVar);
    }
}
