package defpackage;

import android.text.TextUtils;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.pluginachievement.gltexture.util.FileUtil;
import com.huawei.pluginachievement.manager.model.MedalConfigInfo;
import com.huawei.pluginachievement.manager.model.MedalInfo;
import com.huawei.pluginachievement.manager.model.MedalLocation;
import defpackage.mfo;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes6.dex */
public class med {
    public static Map<String, mfo> a(List<mcz> list, List<mcz> list2) {
        return e(list2, b(list));
    }

    private static Map<String, MedalLocation> b(List<mcz> list) {
        HashMap hashMap = new HashMap();
        if (koq.b(list)) {
            return hashMap;
        }
        for (mcz mczVar : list) {
            if (mczVar instanceof MedalLocation) {
                MedalLocation medalLocation = (MedalLocation) mczVar;
                hashMap.put(medalLocation.acquireMedalID(), medalLocation);
            }
        }
        return hashMap;
    }

    public static ArrayList<mfs> d(List<mcz> list, List<mcz> list2) {
        return c(list2, b(list));
    }

    private static ArrayList<mfs> c(List<mcz> list, Map<String, MedalLocation> map) {
        ArrayList<mfs> arrayList = new ArrayList<>(8);
        if (koq.b(list)) {
            return arrayList;
        }
        for (mcz mczVar : list) {
            if (mczVar instanceof MedalConfigInfo) {
                MedalConfigInfo medalConfigInfo = (MedalConfigInfo) mczVar;
                String acquireMedalType = medalConfigInfo.acquireMedalType();
                if (!TextUtils.isEmpty(acquireMedalType) && (acquireMedalType.length() < 3 || mlb.n(acquireMedalType))) {
                    if (map.containsKey(medalConfigInfo.acquireMedalID())) {
                        MedalLocation medalLocation = map.get(medalConfigInfo.acquireMedalID());
                        mfs mfsVar = new mfs();
                        if (medalLocation != null && medalLocation.acquireGainedCount() > 0) {
                            mfsVar.d(medalLocation.acquireMedalID());
                            mfsVar.e(medalLocation.acquireGainedCount());
                            mfsVar.e(medalLocation.acquireMedalGainedTime());
                            mfsVar.b(medalLocation.acquireTimestamp());
                            mfsVar.b(acquireMedalType);
                            arrayList.add(mfsVar);
                        }
                    }
                }
            }
        }
        return arrayList;
    }

    private static Map<String, mfo> e(List<mcz> list, Map<String, MedalLocation> map) {
        HashMap hashMap = new HashMap();
        if (koq.b(list)) {
            return hashMap;
        }
        Map<String, MedalInfo> c = mla.e().c(true);
        for (mcz mczVar : list) {
            if (mczVar instanceof MedalConfigInfo) {
                MedalConfigInfo medalConfigInfo = (MedalConfigInfo) mczVar;
                mfo.b b = b(medalConfigInfo, c);
                if (map.containsKey(medalConfigInfo.acquireMedalID())) {
                    MedalLocation medalLocation = map.get(medalConfigInfo.acquireMedalID());
                    b.a(medalLocation.acquireMedalGainedTime()).e(medalLocation.acquireGainedCount()).a(medalLocation.acquireFirstTabPriority()).e(medalLocation.acquireFirstTabDesc()).g(medalLocation.acquireSecondTabPriority()).n(medalLocation.acquireSecondTabDesc()).j(medalLocation.acquireMedalWeight());
                }
                hashMap.put(medalConfigInfo.acquireMedalID(), b.b());
            }
        }
        return hashMap;
    }

    private static mfo.b b(MedalConfigInfo medalConfigInfo, Map<String, MedalInfo> map) {
        MedalInfo medalInfo;
        if (medalConfigInfo == null) {
            return null;
        }
        String acquireMedalName = medalConfigInfo.acquireMedalName();
        String acquireLightDescription = medalConfigInfo.acquireLightDescription();
        String acquireGrayDescription = medalConfigInfo.acquireGrayDescription();
        if (mcv.e() && mlb.c().contains(medalConfigInfo.acquireMedalID()) && (medalInfo = map.get(mdn.d(medalConfigInfo.acquireMedalType(), String.valueOf(medalConfigInfo.acquireMedalLevel())))) != null) {
            acquireMedalName = medalInfo.getText();
            acquireLightDescription = medalInfo.getContent();
            acquireGrayDescription = medalInfo.getContent();
        }
        return new mfo.b(medalConfigInfo.acquireMedalID()).c(medalConfigInfo.acquireActionType()).c(medalConfigInfo.acquireEndTime()).j(medalConfigInfo.acquireStartTime()).h(acquireGrayDescription).f(acquireLightDescription).d(medalConfigInfo.acquireMedalLabel()).i(medalConfigInfo.acquireMessage()).o(medalConfigInfo.acquireTakeEffectTime()).g(acquireMedalName).h(medalConfigInfo.acquireMedalUnit()).l(medalConfigInfo.acquireMedalType()).e(medalConfigInfo.acquireTimestamp()).b(medalConfigInfo.acquireGrayPromotionName()).d(medalConfigInfo.acquireLightPromotionName()).b(medalConfigInfo.acquireGoal()).i(medalConfigInfo.acquireMedalLevel()).f(medalConfigInfo.acquireRepeatable()).n(medalConfigInfo.acquireEventStatus());
    }

    public static void d() {
        ThreadPoolManager.d().execute(new Runnable() { // from class: med.4
            @Override // java.lang.Runnable
            public void run() {
                med.e();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void e() {
        meh c = meh.c(BaseApplication.e());
        List<mcz> b = c.b(9, new HashMap(2));
        ArrayList<String> l = c.l();
        if (b == null) {
            return;
        }
        for (mcz mczVar : b) {
            if (mczVar instanceof MedalConfigInfo) {
                MedalConfigInfo medalConfigInfo = (MedalConfigInfo) mczVar;
                if (!TextUtils.isEmpty(medalConfigInfo.acquireMedalType())) {
                    String acquireMedalID = medalConfigInfo.acquireMedalID();
                    String acquireEndTime = medalConfigInfo.acquireEndTime();
                    if (!mlb.d(acquireEndTime, l.contains(acquireMedalID) ? 1 : 0)) {
                        LogUtil.a("PLGACHIEVE_PLGACHIEVE_MedalConfigInteractor", "clearEventMedalGenerate medalId=", acquireMedalID, " type=", medalConfigInfo.acquireMedalType(), " name=", medalConfigInfo.acquireMedalName(), " endTime=", mfn.c(BaseApplication.e(), mht.a(acquireEndTime)));
                        File file = new File(mlb.c(mlb.b(acquireMedalID)));
                        boolean exists = file.exists();
                        if (exists) {
                            LogUtil.h("PLGACHIEVE_PLGACHIEVE_MedalConfigInteractor", "clearEventMedalGenerate isPngExists ", Boolean.valueOf(exists));
                            FileUtil.e(file);
                        }
                        File file2 = new File(meb.a(acquireMedalID));
                        boolean exists2 = file2.exists();
                        if (exists2) {
                            LogUtil.h("PLGACHIEVE_PLGACHIEVE_MedalConfigInteractor", "clearEventMedalGenerate is3dExists ", Boolean.valueOf(exists2));
                            FileUtil.e(file2);
                        }
                    }
                }
            }
        }
    }
}
