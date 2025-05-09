package defpackage;

import android.content.Context;
import com.huawei.factory.MedalTypeAccessible;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.pluginachievement.manager.model.MedalConfigInfo;
import com.huawei.pluginachievement.medal.model.TrackData;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes8.dex */
public class bnw implements MedalTypeAccessible {
    @Override // com.huawei.factory.MedalTypeAccessible
    public void dealMedalGenerate(TrackData trackData) {
    }

    public static void d(ArrayList<TrackData> arrayList) {
        Context context = BaseApplication.getContext();
        meh c = meh.c(context);
        if (bnr.e(c, context)) {
            return;
        }
        int d = d(c(arrayList));
        LogUtil.a("PLGACHIEVE_TriathlonStableMedalCalculate", "medalLevel:", Integer.valueOf(d));
        if (d == 0) {
            return;
        }
        HashMap hashMap = new HashMap(2);
        ArrayList arrayList2 = new ArrayList(8);
        List<mcz> b = c.b(9, hashMap);
        ArrayList<String> l = c.l();
        if (b == null) {
            return;
        }
        double d2 = 0.0d;
        String str = "";
        for (mcz mczVar : b) {
            if (!(mczVar instanceof MedalConfigInfo)) {
                return;
            }
            MedalConfigInfo medalConfigInfo = (MedalConfigInfo) mczVar;
            if ("A4".equals(medalConfigInfo.acquireMedalType())) {
                String acquireMedalID = medalConfigInfo.acquireMedalID();
                double d3 = d;
                if (d3 >= medalConfigInfo.acquireMedalLevel() * 1.0d && !l.contains(acquireMedalID)) {
                    arrayList2.add(medalConfigInfo);
                    if (d3 > d2) {
                        str = medalConfigInfo.acquireMedalID();
                        d2 = d3;
                    }
                }
            }
        }
        if (arrayList2.size() == 0 || "".equals(str)) {
            return;
        }
        LogUtil.a("PLGACHIEVE_TriathlonStableMedalCalculate", "medalId:", str);
        bnr.a(context, arrayList2, str);
    }

    private static int d(Map<String, ArrayList<TrackData>> map) {
        int i = 0;
        for (Map.Entry<String, ArrayList<TrackData>> entry : map.entrySet()) {
            ArrayList<TrackData> value = entry.getValue();
            LogUtil.a("PLGACHIEVE_TriathlonStableMedalCalculate", "trackDataMap key:", entry.getKey());
            if (value.size() != 0) {
                Iterator<TrackData> it = value.iterator();
                double d = 0.0d;
                double d2 = 0.0d;
                double d3 = 0.0d;
                while (it.hasNext()) {
                    TrackData next = it.next();
                    double d4 = mlg.d((long) next.acquireDistance());
                    LogUtil.a("PLGACHIEVE_TriathlonStableMedalCalculate", "trackDataMap value:", Long.valueOf(next.acquireTrackTime()));
                    if (mlb.b(next.acquireType()) && d4 > d) {
                        LogUtil.a("PLGACHIEVE_TriathlonStableMedalCalculate", " runMaxDistance ", Double.valueOf(d4));
                        d = d4;
                    }
                    if (mlb.c(next.acquireType()) && d4 > d2) {
                        LogUtil.a("PLGACHIEVE_TriathlonStableMedalCalculate", " bikeMaxDistance ", Double.valueOf(d4));
                        d2 = d4;
                    }
                    if (mlb.a(next.acquireType()) && d4 > d3) {
                        LogUtil.a("PLGACHIEVE_TriathlonStableMedalCalculate", " swimMaxDistance ", Double.valueOf(d4));
                        d3 = d4;
                    }
                }
                if (d >= 10.0d && d2 >= 40.0d && d3 >= 1.5d) {
                    return 2;
                }
                if (d < 5.0d || d2 < 20.0d || d3 < 0.75d) {
                    LogUtil.a("PLGACHIEVE_TriathlonStableMedalCalculate", "No achieve triathlon!");
                } else {
                    i = 1;
                }
            }
        }
        return i;
    }

    private static Map<String, ArrayList<TrackData>> c(ArrayList<TrackData> arrayList) {
        HashMap hashMap = new HashMap(8);
        Iterator<TrackData> it = arrayList.iterator();
        while (it.hasNext()) {
            TrackData next = it.next();
            String b = bnr.b(next.acquireTrackTime());
            if (hashMap.containsKey(b)) {
                ((ArrayList) hashMap.get(b)).add(next);
            } else {
                ArrayList arrayList2 = new ArrayList(8);
                arrayList2.add(next);
                hashMap.put(b, arrayList2);
            }
        }
        return hashMap;
    }

    @Override // com.huawei.factory.MedalTypeAccessible
    public void dealMedalGenerate(final ArrayList<TrackData> arrayList) {
        ThreadPoolManager.d().execute(new Runnable() { // from class: bnw.5
            @Override // java.lang.Runnable
            public void run() {
                bnw.d((ArrayList<TrackData>) arrayList);
            }
        });
    }
}
