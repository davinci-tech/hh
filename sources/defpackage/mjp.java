package defpackage;

import android.os.SystemClock;
import android.text.TextUtils;
import android.util.Pair;
import com.huawei.haf.common.utils.CollectionUtils;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.R;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.operation.utils.Constants;
import com.huawei.pluginachievement.impl.AchieveCallback;
import com.huawei.pluginachievement.manager.model.MedalConfigInfo;
import com.huawei.pluginachievement.manager.model.MedalConstants;
import com.huawei.pluginachievement.manager.model.MedalInfo;
import com.huawei.pluginachievement.manager.model.MedalInfoDesc;
import com.huawei.pluginachievement.manager.model.MedalLocation;
import com.huawei.pluginachievement.manager.model.NewMedalTabDataBean;
import com.huawei.profile.profile.ProfileExtendConstants;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.ToLongFunction;

/* loaded from: classes6.dex */
public class mjp {

    /* renamed from: a, reason: collision with root package name */
    private static boolean f15023a = true;
    private static long b;
    private static NewMedalTabDataBean e;

    public static void e(final AchieveCallback achieveCallback) {
        ThreadPoolManager.d().execute(new Runnable() { // from class: mjp.1
            @Override // java.lang.Runnable
            public void run() {
                long j;
                meh c = meh.c(BaseApplication.getContext());
                Map<String, String> s = c.s();
                s.put("countryCode", LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1010));
                c.b(s);
                HashMap hashMap = new HashMap(5);
                List<mcz> b2 = c.b(8, hashMap);
                List<mcz> b3 = c.b(9, hashMap);
                LogUtil.h("PLGACHIEVE_AchieveMedalInitData", "getInitData configInfo getAllData");
                if (b2 == null || b3 == null || b3.isEmpty()) {
                    return;
                }
                ArrayList arrayList = new ArrayList(5);
                ArrayList arrayList2 = new ArrayList(5);
                ArrayList arrayList3 = new ArrayList(5);
                HashMap hashMap2 = new HashMap(5);
                ArrayList c2 = mjp.c(b3);
                List d = mjp.d(b3);
                LogUtil.a("PLGACHIEVE_AchieveMedalInitData", "historyPerfectMonthMedalList -> " + d);
                for (mcz mczVar : b2) {
                    if (mczVar instanceof MedalLocation) {
                        MedalLocation medalLocation = (MedalLocation) mczVar;
                        if (!c2.contains(medalLocation.acquireMedalID()) && !d.contains(medalLocation.acquireMedalID())) {
                            LogUtil.c("PLGACHIEVE_AchieveMedalInitData", medalLocation.toString());
                            if (medalLocation.acquireGainedCount() > 0) {
                                try {
                                    j = Long.parseLong(medalLocation.acquireMedalGainedTime());
                                } catch (NumberFormatException unused) {
                                    LogUtil.b("PLGACHIEVE_AchieveMedalInitData", "getData() NumberFormatException");
                                    j = 0;
                                }
                                arrayList2.add(medalLocation.acquireMedalID());
                                hashMap2.put(medalLocation.acquireMedalID(), Long.valueOf(j));
                            } else {
                                arrayList3.add(medalLocation.acquireMedalID());
                            }
                            arrayList.add(medalLocation);
                        }
                    }
                }
                mjp.a(b3, arrayList, mjm.d(arrayList2, hashMap2), arrayList3, AchieveCallback.this);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static List<String> d(List<mcz> list) {
        ArrayList arrayList = new ArrayList(5);
        if (CollectionUtils.d(list)) {
            LogUtil.h("PLGACHIEVE_AchieveMedalInitData", "getHistoryPerfectMonthMedal: medalConfigInfoList is empty");
            return arrayList;
        }
        HashMap hashMap = new HashMap(5);
        for (mcz mczVar : list) {
            if (mczVar instanceof MedalConfigInfo) {
                MedalConfigInfo medalConfigInfo = (MedalConfigInfo) mczVar;
                if (mlb.j(medalConfigInfo.acquireMedalType())) {
                    ((List) hashMap.computeIfAbsent(medalConfigInfo.acquireMedalType(), new Function() { // from class: mjo
                        @Override // java.util.function.Function
                        public final Object apply(Object obj) {
                            return mjp.b((String) obj);
                        }
                    })).add(new Pair(medalConfigInfo.acquireMedalID(), Long.valueOf(nsn.h(medalConfigInfo.acquireTakeEffectTime()))));
                }
            }
        }
        Iterator it = hashMap.entrySet().iterator();
        while (it.hasNext()) {
            List list2 = (List) ((Map.Entry) it.next()).getValue();
            if (list2.size() > 1) {
                list2.sort(Comparator.comparingLong(new ToLongFunction() { // from class: mjn
                    @Override // java.util.function.ToLongFunction
                    public final long applyAsLong(Object obj) {
                        long longValue;
                        longValue = ((Long) ((Pair) obj).second).longValue();
                        return longValue;
                    }
                }));
                for (int i = 0; i < list2.size() && i != list2.size() - 1; i++) {
                    arrayList.add((String) ((Pair) list2.get(i)).first);
                }
            }
        }
        return arrayList;
    }

    static /* synthetic */ List b(String str) {
        return new ArrayList();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void a(List<mcz> list, List<MedalLocation> list2, ArrayList<String> arrayList, ArrayList<String> arrayList2, AchieveCallback achieveCallback) {
        ArrayList arrayList3 = new ArrayList(5);
        ArrayList arrayList4 = new ArrayList(5);
        for (mcz mczVar : list) {
            if (mczVar instanceof MedalConfigInfo) {
                MedalConfigInfo medalConfigInfo = (MedalConfigInfo) mczVar;
                String acquireMedalType = medalConfigInfo.acquireMedalType();
                if (acquireMedalType != null && acquireMedalType.length() >= 3) {
                    arrayList4.add(medalConfigInfo.acquireMedalID());
                    if (mlb.g(acquireMedalType)) {
                    }
                }
                if (acquireMedalType != null) {
                    arrayList3.add(medalConfigInfo);
                }
            }
        }
        mko e2 = mjm.e(list2);
        Map<String, ArrayList<String>> b2 = e2.b();
        HashMap hashMap = new HashMap(5);
        HashMap hashMap2 = new HashMap(5);
        ArrayList<MedalInfoDesc> c = c(list2, arrayList, arrayList3);
        ArrayList<String> d = mjm.d(arrayList2, (ArrayList<String>) arrayList4, list2);
        hashMap.put(BaseApplication.getContext().getResources().getString(R.string._2130840758_res_0x7f020cb6), c);
        hashMap2.put(BaseApplication.getContext().getResources().getString(R.string._2130840759_res_0x7f020cb7), c(c(list2, d, arrayList3)));
        if (b2 != null) {
            for (Map.Entry<String, ArrayList<String>> entry : b2.entrySet()) {
                ArrayList<MedalInfoDesc> c2 = c(list2, entry.getValue(), arrayList3);
                hashMap.put(entry.getKey(), c2);
                c(hashMap2, c2);
            }
        }
        e(achieveCallback, c, e2, hashMap, hashMap2);
    }

    private static void e(AchieveCallback achieveCallback, ArrayList<MedalInfoDesc> arrayList, mko mkoVar, Map<String, ArrayList<MedalInfoDesc>> map, Map<String, ArrayList<MedalInfoDesc>> map2) {
        List<String> e2 = mkoVar.e();
        if (e2 != null) {
            NewMedalTabDataBean newMedalTabDataBean = new NewMedalTabDataBean();
            e = newMedalTabDataBean;
            newMedalTabDataBean.setMedalInfoDesc(arrayList);
            e.setTabNewList(e2);
            e.setFirstTabRelationship(mkoVar.d());
            e.setSecondTabRelationship(map);
            e.setSecondTabRelationshipForSport(map2);
            if (achieveCallback == null) {
                LogUtil.h("PLGACHIEVE_AchieveMedalInitData", "onMedalTabDataCallback: achieveCallback is null");
            } else {
                achieveCallback.onResponse(0, e);
            }
        }
    }

    private static ArrayList<MedalInfoDesc> c(ArrayList<MedalInfoDesc> arrayList) {
        ArrayList<MedalInfoDesc> arrayList2 = new ArrayList<>(10);
        Iterator<MedalInfoDesc> it = arrayList.iterator();
        while (it.hasNext()) {
            MedalInfoDesc next = it.next();
            if (next.acquireMedalType() != null && next.acquireMedalType().length() < 3) {
                arrayList2.add(next);
            }
        }
        return arrayList2;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:76:0x00f5, code lost:
    
        if (r1.equals("A") == false) goto L89;
     */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static void c(java.util.Map<java.lang.String, java.util.ArrayList<com.huawei.pluginachievement.manager.model.MedalInfoDesc>> r4, java.util.ArrayList<com.huawei.pluginachievement.manager.model.MedalInfoDesc> r5) {
        /*
            Method dump skipped, instructions count: 450
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.mjp.c(java.util.Map, java.util.ArrayList):void");
    }

    private static ArrayList<MedalInfoDesc> c(List<MedalLocation> list, ArrayList<String> arrayList, List<MedalConfigInfo> list2) {
        Object[] objArr = new Object[2];
        objArr[0] = "enter getMedalInfoByConfig list= ";
        objArr[1] = arrayList == null ? Constants.NULL : arrayList.toString();
        LogUtil.a("PLGACHIEVE_AchieveMedalInitData", objArr);
        ArrayList<MedalInfoDesc> arrayList2 = new ArrayList<>(5);
        Map<String, MedalInfo> c = mla.e().c(true);
        if (arrayList == null) {
            LogUtil.h("PLGACHIEVE_AchieveMedalInitData", "the list is null");
            return arrayList2;
        }
        e(list, arrayList, c, list2, arrayList2);
        LogUtil.c("PLGACHIEVE_AchieveMedalInitData", "medalInfoDesc size = ", Integer.valueOf(arrayList2.size()));
        return arrayList2;
    }

    private static void e(List<MedalLocation> list, ArrayList<String> arrayList, Map<String, MedalInfo> map, List<MedalConfigInfo> list2, ArrayList<MedalInfoDesc> arrayList2) {
        String str;
        int i;
        String str2;
        String str3;
        String str4;
        String b2 = mct.b(BaseApplication.getContext(), "_medalPngStatusDownload");
        Iterator<String> it = arrayList.iterator();
        int i2 = 0;
        while (it.hasNext()) {
            String next = it.next();
            MedalInfoDesc medalInfoDesc = new MedalInfoDesc(next);
            Iterator<MedalLocation> it2 = list.iterator();
            while (true) {
                if (!it2.hasNext()) {
                    break;
                }
                MedalLocation next2 = it2.next();
                if (next.equals(next2.acquireMedalID())) {
                    String acquireMedalGainedTime = next2.acquireMedalGainedTime();
                    int acquireGainedCount = next2.acquireGainedCount();
                    medalInfoDesc.saveGainCount(acquireGainedCount);
                    medalInfoDesc.saveGainTime(acquireMedalGainedTime);
                    i2 = acquireGainedCount;
                    break;
                }
            }
            Iterator<MedalConfigInfo> it3 = list2.iterator();
            while (true) {
                if (!it3.hasNext()) {
                    str = null;
                    i = 0;
                    str2 = null;
                    str3 = null;
                    str4 = null;
                    break;
                }
                MedalConfigInfo next3 = it3.next();
                if (next.equals(next3.acquireMedalID())) {
                    str = c(next3, i2, map, medalInfoDesc);
                    i = next3.acquireEventStatus();
                    str3 = next3.acquireEndTime();
                    str4 = next3.getClientTypes();
                    str2 = next3.getPhoneTypes();
                    break;
                }
            }
            if (mlb.o(str) && TextUtils.isEmpty(str3) && f15023a) {
                LogUtil.c("PLGACHIEVE_AchieveMedalInitData", "enter dealActivityMedal");
                f15023a = false;
                meh.c(BaseApplication.getContext()).e();
            }
            if (mlb.d(str3, i2) && mfg.b(str, next, BaseApplication.getContext(), b2) && d(i, next) && mlb.b(next, str4, str2, i2)) {
                arrayList2.add(medalInfoDesc);
            }
        }
    }

    private static boolean d(int i, String str) {
        if (i < 3) {
            return true;
        }
        LogUtil.a("PLGACHIEVE_AchieveMedalInitData", "Medal Is ExceptionStatus");
        MedalConfigInfo medalConfigInfo = new MedalConfigInfo();
        medalConfigInfo.saveMedalID(str);
        medalConfigInfo.saveIsNewConfig(0);
        medalConfigInfo.saveEventStatus(0);
        meh.c(BaseApplication.getContext()).e((mcz) medalConfigInfo);
        mfg.a(str, BaseApplication.getContext());
        return false;
    }

    private static String c(MedalConfigInfo medalConfigInfo, int i, Map<String, MedalInfo> map, MedalInfoDesc medalInfoDesc) {
        String acquireGrayPromotionName;
        String acquireGrayPromotionUrl;
        String acquireMedalName = medalConfigInfo.acquireMedalName();
        String acquireLightDescription = medalConfigInfo.acquireLightDescription();
        String acquireGrayDescription = medalConfigInfo.acquireGrayDescription();
        String acquireMessage = medalConfigInfo.acquireMessage();
        String acquireMedalType = medalConfigInfo.acquireMedalType();
        String d = mdn.d(acquireMedalType, String.valueOf(medalConfigInfo.acquireMedalLevel()));
        if (i > 0) {
            acquireGrayPromotionName = medalConfigInfo.acquireLightPromotionName();
            acquireGrayPromotionUrl = medalConfigInfo.acquireLightPromotionUrl();
        } else {
            acquireGrayPromotionName = medalConfigInfo.acquireGrayPromotionName();
            acquireGrayPromotionUrl = medalConfigInfo.acquireGrayPromotionUrl();
        }
        if (mcv.e()) {
            if (!c(medalInfoDesc, map.get(d))) {
                medalInfoDesc.saveText(acquireMedalName);
                medalInfoDesc.saveLightDescription(acquireLightDescription);
                medalInfoDesc.saveGrayDescription(acquireGrayDescription);
                medalInfoDesc.saveMessage(acquireMessage);
            }
        } else {
            medalInfoDesc.saveText(acquireMedalName);
            medalInfoDesc.saveLightDescription(acquireLightDescription);
            medalInfoDesc.saveGrayDescription(acquireGrayDescription);
            medalInfoDesc.saveMessage(acquireMessage);
        }
        int acquireReachStatus = medalConfigInfo.acquireReachStatus();
        medalInfoDesc.saveIsNewConfig(medalConfigInfo.acquireIsNewConfig());
        medalInfoDesc.saveReachStatus(acquireReachStatus);
        medalInfoDesc.saveMedalTypeLevel(d);
        medalInfoDesc.savePromotionName(acquireGrayPromotionName);
        medalInfoDesc.savePromotionURL(acquireGrayPromotionUrl);
        medalInfoDesc.saveMedalType(acquireMedalType);
        medalInfoDesc.saveMedalLabel(medalConfigInfo.acquireMedalLabel());
        return acquireMedalType;
    }

    private static boolean c(MedalInfoDesc medalInfoDesc, MedalInfo medalInfo) {
        if (medalInfo == null) {
            return false;
        }
        medalInfoDesc.saveText(medalInfo.getText());
        medalInfoDesc.saveLightDescription(medalInfo.getContent());
        medalInfoDesc.saveGrayDescription(medalInfo.getContent());
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static ArrayList<String> c(List<mcz> list) {
        ArrayList<String> arrayList = new ArrayList<>(5);
        if (list != null && !list.isEmpty() && mlb.e()) {
            for (mcz mczVar : list) {
                if (mczVar instanceof MedalConfigInfo) {
                    MedalConfigInfo medalConfigInfo = (MedalConfigInfo) mczVar;
                    if (MedalConstants.ACTION_MEDAL_TYPE.equals(medalConfigInfo.acquireMedalType())) {
                        arrayList.add(medalConfigInfo.acquireMedalID());
                    }
                }
            }
        }
        return arrayList;
    }

    public static boolean c() {
        long elapsedRealtime = SystemClock.elapsedRealtime();
        if (elapsedRealtime - b < ProfileExtendConstants.TIME_OUT) {
            return true;
        }
        b = elapsedRealtime;
        return false;
    }

    public static NewMedalTabDataBean b() {
        return e;
    }

    public static void e(NewMedalTabDataBean newMedalTabDataBean) {
        e = newMedalTabDataBean;
    }
}
