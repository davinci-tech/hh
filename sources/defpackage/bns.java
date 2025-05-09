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
import java.util.List;

/* loaded from: classes8.dex */
public class bns implements MedalTypeAccessible {
    @Override // com.huawei.factory.MedalTypeAccessible
    public void dealMedalGenerate(ArrayList<TrackData> arrayList) {
    }

    public void c(TrackData trackData) {
        Context context = BaseApplication.getContext();
        meh c = meh.c(context);
        if (bnr.e(c, context) || trackData == null) {
            return;
        }
        double e = mlg.e((long) trackData.acquireDistance(), !mlb.b(trackData.acquireType()));
        HashMap hashMap = new HashMap(2);
        ArrayList arrayList = new ArrayList(8);
        List<mcz> b = c.b(9, hashMap);
        ArrayList<String> l = c.l();
        if (b == null) {
            return;
        }
        LogUtil.a("PLGACHIEVE_SingleStableMedalCalculate", "medalConfigInfos size ", Integer.valueOf(b.size()));
        double d = 0.0d;
        String str = "";
        for (mcz mczVar : b) {
            if (mczVar instanceof MedalConfigInfo) {
                MedalConfigInfo medalConfigInfo = (MedalConfigInfo) mczVar;
                if (mlb.d(trackData.acquireType()).equals(medalConfigInfo.acquireMedalType())) {
                    if ("A3".equals(mlb.d(trackData.acquireType()))) {
                        if (trackData.acquireTrackTime() <= mht.a(medalConfigInfo.acquireTakeEffectTime())) {
                        }
                    }
                    String acquireMedalID = medalConfigInfo.acquireMedalID();
                    double a2 = a(medalConfigInfo);
                    if (e >= a2 && !l.contains(acquireMedalID)) {
                        LogUtil.a("PLGACHIEVE_SingleStableMedalCalculate", "medalIdTmp ", acquireMedalID);
                        arrayList.add(medalConfigInfo);
                        if (a2 > d) {
                            str = medalConfigInfo.acquireMedalID();
                            d = a2;
                        }
                    }
                }
            }
        }
        bnr.a(context, arrayList, str);
    }

    private double a(MedalConfigInfo medalConfigInfo) {
        if (medalConfigInfo == null) {
            return 0.0d;
        }
        String acquireMedalID = medalConfigInfo.acquireMedalID();
        if ("3".equals(acquireMedalID)) {
            return 21.0975d;
        }
        if ("4".equals(acquireMedalID)) {
            return 42.195d;
        }
        return medalConfigInfo.acquireMedalLevel() * 1.0d;
    }

    @Override // com.huawei.factory.MedalTypeAccessible
    public void dealMedalGenerate(final TrackData trackData) {
        ThreadPoolManager.d().execute(new Runnable() { // from class: bns.4
            @Override // java.lang.Runnable
            public void run() {
                bns.this.c(trackData);
            }
        });
    }
}
