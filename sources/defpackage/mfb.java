package defpackage;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.pluginachievement.manager.model.MedalConfigInfo;
import com.huawei.pluginachievement.manager.model.TrackData;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes6.dex */
public class mfb {
    public static void c(TrackData trackData, meh mehVar, Context context) {
        LogUtil.a("PLGACHIEVE_AchieveTrackMedalService", "Enter dealEventMedalGenerate");
        if (context == null) {
            return;
        }
        HashMap hashMap = new HashMap(2);
        if (mehVar == null) {
            mehVar = meh.c(context.getApplicationContext());
        }
        List<mcz> b = mehVar.b(9, hashMap);
        ArrayList<String> l = mehVar.l();
        LogUtil.a("PLGACHIEVE_AchieveTrackMedalService", "getMedalLightList size:", Integer.valueOf(l.size()));
        if (b != null) {
            for (mcz mczVar : b) {
                if (mczVar instanceof MedalConfigInfo) {
                    MedalConfigInfo medalConfigInfo = (MedalConfigInfo) mczVar;
                    String acquireMedalType = medalConfigInfo.acquireMedalType();
                    if (!TextUtils.isEmpty(acquireMedalType) && !mlb.k(acquireMedalType) && acquireMedalType.length() >= 3 && !l.contains(medalConfigInfo.acquireMedalID())) {
                        c(medalConfigInfo, trackData, mehVar);
                    }
                }
            }
        }
    }

    private static void c(MedalConfigInfo medalConfigInfo, TrackData trackData, meh mehVar) {
        if (!b(medalConfigInfo, trackData)) {
            LogUtil.a("PLGACHIEVE_AchieveTrackMedalService", "judgeTimeIsValid:invalid.");
            return;
        }
        int acquireActionType = medalConfigInfo.acquireActionType();
        LogUtil.a("PLGACHIEVE_AchieveTrackMedalService", "medalActionType=", Integer.valueOf(acquireActionType));
        String acquireMedalID = medalConfigInfo.acquireMedalID();
        String acquireMedalType = medalConfigInfo.acquireMedalType();
        int acquireMedalLevel = medalConfigInfo.acquireMedalLevel();
        double d = mlg.d((long) trackData.acquireDistance());
        LogUtil.a("PLGACHIEVE_AchieveTrackMedalService", "medalLevel=", Integer.valueOf(acquireMedalLevel), " medalType=", acquireMedalType, " medalId=", acquireMedalID);
        if (3 == acquireActionType || 11 == acquireActionType) {
            if (!mfg.b(trackData.acquireType()) || d < acquireMedalLevel) {
                return;
            }
            mehVar.b(acquireMedalID, acquireMedalType, acquireMedalLevel);
            a(mehVar, acquireMedalID, acquireMedalLevel);
            return;
        }
        if (4 == acquireActionType || 12 == acquireActionType) {
            if (259 != trackData.acquireType() || d < acquireMedalLevel) {
                return;
            }
            mehVar.b(acquireMedalID, acquireMedalType, acquireMedalLevel);
            a(mehVar, acquireMedalID, acquireMedalLevel);
            return;
        }
        LogUtil.h("PLGACHIEVE_AchieveTrackMedalService", "dealTrackData type error:", Integer.valueOf(acquireActionType));
    }

    private static void a(meh mehVar, String str, int i) {
        if (mehVar == null || TextUtils.isEmpty(str)) {
            return;
        }
        LogUtil.a("PLGACHIEVE_AchieveTrackMedalService", "doRefreshMedal medalId ", str, " medalLevel ", String.valueOf(i));
        Map<String, String> a2 = mehVar.a(str, String.valueOf(i));
        a2.put("countryCode", LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1010));
        mehVar.a(7, a2);
    }

    private static boolean b(MedalConfigInfo medalConfigInfo, TrackData trackData) {
        long j;
        if (!mfg.e(medalConfigInfo.acquireStartTime()) || !mfg.e(medalConfigInfo.acquireEndTime())) {
            return false;
        }
        long j2 = 0;
        try {
            j = Long.parseLong(medalConfigInfo.acquireStartTime());
        } catch (NumberFormatException unused) {
            j = 0;
        }
        try {
            j2 = Long.parseLong(medalConfigInfo.acquireEndTime());
        } catch (NumberFormatException unused2) {
            LogUtil.b("PLGACHIEVE_AchieveTrackMedalService", "requestSportData NumberFormatException");
            return j2 < trackData.acquireTrackTime() ? false : false;
        }
        if (j2 < trackData.acquireTrackTime() && trackData.acquireTrackTime() >= j) {
            return true;
        }
    }
}
