package defpackage;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import com.huawei.haf.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.pluginachievement.impl.AchieveCallback;
import com.huawei.pluginachievement.manager.model.MedalConfigInfo;
import com.huawei.pluginachievement.manager.model.MedalLocation;
import com.huawei.pluginachievement.manager.model.TrackData;
import health.compact.a.HiDateUtil;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes6.dex */
public class mfe {
    private static meh d;

    public static void a(TrackData trackData, meh mehVar, Context context) {
        double acquireMedalLevel;
        if (a(mehVar, context)) {
            return;
        }
        if (TextUtils.isEmpty(mct.b(context, "personal"))) {
            LogUtil.a("PLGACHIEVE_AchieveSingleTrackMedalService", "flag is null");
            return;
        }
        if (trackData == null) {
            LogUtil.a("PLGACHIEVE_AchieveSingleTrackMedalService", "trackData is null");
            return;
        }
        double a2 = a(trackData);
        HashMap hashMap = new HashMap(2);
        ArrayList arrayList = new ArrayList(8);
        List<mcz> b = d.b(9, hashMap);
        ArrayList<String> l = d.l();
        if (b == null) {
            return;
        }
        LogUtil.a("PLGACHIEVE_AchieveSingleTrackMedalService", "medalConfigInfos size ", Integer.valueOf(b.size()));
        double d2 = 0.0d;
        String str = "";
        for (mcz mczVar : b) {
            if (mczVar instanceof MedalConfigInfo) {
                MedalConfigInfo medalConfigInfo = (MedalConfigInfo) mczVar;
                if (mlb.d(trackData.acquireType()).equals(medalConfigInfo.acquireMedalType())) {
                    if ("A3".equals(mlb.d(trackData.acquireType())) || "A5".equals(mlb.d(trackData.acquireType()))) {
                        if (trackData.acquireTrackTime() <= mht.a(medalConfigInfo.acquireTakeEffectTime())) {
                        }
                    }
                    String acquireMedalID = medalConfigInfo.acquireMedalID();
                    if ("3".equals(acquireMedalID)) {
                        acquireMedalLevel = 21.0975d;
                    } else {
                        acquireMedalLevel = "4".equals(acquireMedalID) ? 42.195d : medalConfigInfo.acquireMedalLevel() * 1.0d;
                    }
                    if (a2 >= acquireMedalLevel && !l.contains(acquireMedalID)) {
                        LogUtil.a("PLGACHIEVE_AchieveSingleTrackMedalService", "medalIdTmp ", acquireMedalID);
                        arrayList.add(medalConfigInfo);
                        if (acquireMedalLevel > d2) {
                            str = medalConfigInfo.acquireMedalID();
                            d2 = acquireMedalLevel;
                        }
                    }
                }
            }
        }
        e(context, (ArrayList<MedalConfigInfo>) arrayList, str, trackData);
    }

    private static double a(TrackData trackData) {
        if (mlb.e(trackData.acquireType())) {
            return trackData.acquireTrackNum();
        }
        if (mlb.b(trackData.acquireType())) {
            return mlg.e((long) trackData.acquireDistance(), false);
        }
        return mlg.d((long) trackData.acquireDistance());
    }

    public static boolean a(meh mehVar, Context context) {
        if (context == null) {
            return true;
        }
        if (mehVar == null) {
            d = meh.c(context.getApplicationContext());
            return false;
        }
        d = mehVar;
        return false;
    }

    public static void d(Context context, ArrayList<MedalConfigInfo> arrayList, String str) {
        LogUtil.a("PLGACHIEVE_AchieveSingleTrackMedalService", "dealStableMedal ContinuousStableMedal!");
        e(context, arrayList, str, (TrackData) null);
    }

    public static void e(Context context, ArrayList<MedalConfigInfo> arrayList, String str, TrackData trackData) {
        LogUtil.a("PLGACHIEVE_AchieveSingleTrackMedalService", "dealStableMedal Id == ", str);
        StringBuilder sb = new StringBuilder();
        Iterator<MedalConfigInfo> it = arrayList.iterator();
        while (it.hasNext()) {
            MedalConfigInfo next = it.next();
            String acquireMedalID = next.acquireMedalID();
            sb.append(acquireMedalID);
            sb.append(",");
            if (str.equals(acquireMedalID) && c(d(trackData))) {
                d.b(acquireMedalID, next.acquireMedalType(), next.acquireMedalLevel());
            } else {
                d(acquireMedalID, d(trackData));
            }
            Map<String, String> a2 = d.a(acquireMedalID, String.valueOf(next.acquireMedalLevel()));
            a2.put("countryCode", LoginInit.getInstance(context).getAccountInfo(1010));
            d.a(7, a2);
        }
        if (sb.length() > 1) {
            sb.deleteCharAt(sb.length() - 1);
            b(context, sb.toString());
            LogUtil.a("PLGACHIEVE_AchieveSingleTrackMedalService", "dealStableMedal stableMedalIdList == ", sb.toString());
        }
    }

    private static long d(TrackData trackData) {
        return trackData == null ? System.currentTimeMillis() : trackData.acquireTrackTime();
    }

    private static boolean c(long j) {
        boolean z = System.currentTimeMillis() - j <= 604800000;
        LogUtil.a("PLGACHIEVE_AchieveSingleTrackMedalService", "isSevenDay ", Boolean.valueOf(z), " trackTime ", Long.valueOf(j));
        return z;
    }

    public static void d(String str, long j) {
        MedalLocation medalLocation = new MedalLocation();
        medalLocation.saveGainedCount(1);
        long currentTimeMillis = System.currentTimeMillis();
        if (c(j)) {
            j = currentTimeMillis;
        }
        medalLocation.saveMedalGainedTime(String.valueOf(j));
        if (TextUtils.isEmpty(str)) {
            return;
        }
        medalLocation.saveMedalID(str);
        d.e(medalLocation);
    }

    private static void d(String str, long j, int i) {
        MedalLocation medalLocation = new MedalLocation();
        medalLocation.saveGainedCount(i + 1);
        medalLocation.saveMedalGainedTime(String.valueOf(j));
        if (TextUtils.isEmpty(str)) {
            return;
        }
        medalLocation.saveMedalID(str);
        meh.c(BaseApplication.e()).e(medalLocation);
    }

    private static void b(Context context, String str) {
        HashMap hashMap = new HashMap(2);
        hashMap.put("_uploadMedal", str);
        mct.b(context, hashMap);
    }

    public static void b(ArrayList<TrackData> arrayList, meh mehVar, Context context) {
        if (a(mehVar, context)) {
            return;
        }
        if (mehVar.t() == null || mehVar.t().getHuid() == null) {
            LogUtil.a("PLGACHIEVE_AchieveSingleTrackMedalService", "dealTrack userProfile or uid is null");
            mehVar.ad();
            if (mehVar.t() == null || mehVar.t().getHuid() == null) {
                return;
            }
        }
        int b = b(b(arrayList));
        LogUtil.a("PLGACHIEVE_AchieveSingleTrackMedalService", "medalLevel = ", Integer.valueOf(b));
        if (b == 0) {
            return;
        }
        HashMap hashMap = new HashMap(2);
        ArrayList arrayList2 = new ArrayList(8);
        List<mcz> b2 = d.b(9, hashMap);
        ArrayList<String> l = d.l();
        if (b2 == null) {
            return;
        }
        double d2 = 0.0d;
        String str = "";
        for (mcz mczVar : b2) {
            if (mczVar instanceof MedalConfigInfo) {
                MedalConfigInfo medalConfigInfo = (MedalConfigInfo) mczVar;
                if ("A4".equals(medalConfigInfo.acquireMedalType())) {
                    String acquireMedalID = medalConfigInfo.acquireMedalID();
                    double d3 = b;
                    if (d3 >= medalConfigInfo.acquireMedalLevel() * 1.0d && !l.contains(acquireMedalID)) {
                        arrayList2.add(medalConfigInfo);
                        if (d3 > d2) {
                            str = medalConfigInfo.acquireMedalID();
                            d2 = d3;
                        }
                    }
                }
            }
        }
        if (arrayList2.size() == 0 || "".equals(str)) {
            return;
        }
        LogUtil.a("PLGACHIEVE_AchieveSingleTrackMedalService", "medalID:", str);
        d(context, (ArrayList<MedalConfigInfo>) arrayList2, str);
    }

    private static int b(Map<String, ArrayList<TrackData>> map) {
        int i = 0;
        for (Map.Entry<String, ArrayList<TrackData>> entry : map.entrySet()) {
            ArrayList<TrackData> value = entry.getValue();
            LogUtil.a("PLGACHIEVE_AchieveSingleTrackMedalService", "trackDataMap key = ", entry.getKey());
            if (value.size() != 0) {
                Iterator<TrackData> it = value.iterator();
                double d2 = 0.0d;
                double d3 = 0.0d;
                double d4 = 0.0d;
                while (it.hasNext()) {
                    TrackData next = it.next();
                    double d5 = mlg.d((long) next.acquireDistance());
                    LogUtil.a("PLGACHIEVE_AchieveSingleTrackMedalService", "trackDataMap  value:", Long.valueOf(next.acquireTrackTime()));
                    if (mlb.b(next.acquireType()) && d5 > d2) {
                        LogUtil.a("PLGACHIEVE_AchieveSingleTrackMedalService", " runMaxDistance ", Double.valueOf(d5));
                        d2 = d5;
                    }
                    if (mlb.c(next.acquireType()) && d5 > d3) {
                        LogUtil.a("PLGACHIEVE_AchieveSingleTrackMedalService", " bikeMaxDistance ", Double.valueOf(d5));
                        d3 = d5;
                    }
                    if (mlb.a(next.acquireType()) && d5 > d4) {
                        LogUtil.a("PLGACHIEVE_AchieveSingleTrackMedalService", " swimMaxDistance ", Double.valueOf(d5));
                        d4 = d5;
                    }
                }
                if (d2 >= 10.0d && d3 >= 40.0d && d4 >= 1.5d) {
                    return 2;
                }
                if (d2 < 5.0d || d3 < 20.0d || d4 < 0.75d) {
                    LogUtil.a("PLGACHIEVE_AchieveSingleTrackMedalService", "No achieve triathlon!");
                } else {
                    i = 1;
                }
            }
        }
        return i;
    }

    private static Map<String, ArrayList<TrackData>> b(ArrayList<TrackData> arrayList) {
        HashMap hashMap = new HashMap(8);
        Iterator<TrackData> it = arrayList.iterator();
        while (it.hasNext()) {
            TrackData next = it.next();
            String b = b(next.acquireTrackTime());
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

    private static String b(long j) {
        return new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH).format(new Date(j));
    }

    public static void cgq_(Bundle bundle) {
        if (TextUtils.isEmpty(mct.b(BaseApplication.e(), "personal"))) {
            LogUtil.h("PLGACHIEVE_AchieveSingleTrackMedalService", "flag is null");
            return;
        }
        if (bundle == null) {
            LogUtil.h("PLGACHIEVE_AchieveSingleTrackMedalService", "bundle is null");
            return;
        }
        String string = bundle.getString("dataType");
        int i = bundle.getInt("dataLevel") * 100;
        long j = bundle.getLong("timestamp");
        LogUtil.a("PLGACHIEVE_AchieveSingleTrackMedalService", "dealThreeCircleMedalGenerate type=", string, " dataLevel=", Integer.valueOf(i), " timestamp=", Long.valueOf(j));
        HashMap hashMap = new HashMap(2);
        ArrayList arrayList = new ArrayList(8);
        List<mcz> b = meh.c(BaseApplication.e()).b(9, hashMap);
        if (b == null) {
            return;
        }
        LogUtil.a("PLGACHIEVE_AchieveSingleTrackMedalService", "medalConfigInfos size ", Integer.valueOf(b.size()));
        double d2 = 0.0d;
        String str = "";
        for (mcz mczVar : b) {
            if (mczVar instanceof MedalConfigInfo) {
                MedalConfigInfo medalConfigInfo = (MedalConfigInfo) mczVar;
                if (string.equals(medalConfigInfo.acquireMedalType()) && j > mht.a(medalConfigInfo.acquireTakeEffectTime())) {
                    int acquireMedalLevel = medalConfigInfo.acquireMedalLevel();
                    String acquireMedalID = medalConfigInfo.acquireMedalID();
                    if (i >= acquireMedalLevel) {
                        LogUtil.a("PLGACHIEVE_AchieveSingleTrackMedalService", "dealThreeCircleMedalGenerate medalIdTmp ", acquireMedalID);
                        arrayList.add(medalConfigInfo);
                        double d3 = acquireMedalLevel;
                        if (d3 > d2) {
                            str = medalConfigInfo.acquireMedalID();
                            d2 = d3;
                        }
                    }
                }
            }
        }
        LogUtil.a("PLGACHIEVE_AchieveSingleTrackMedalService", "dealThreeCircleMedalGenerate list ", arrayList.toString(), " medalIdTmp ", str);
        c(arrayList, str, j);
    }

    public static void c(ArrayList<MedalConfigInfo> arrayList, String str, long j) {
        List<mcz> list;
        LogUtil.a("PLGACHIEVE_AchieveSingleTrackMedalService", "dealThreeCircleMedal maxMedalId == ", str);
        if (a(d, BaseApplication.e()) || koq.b(arrayList)) {
            return;
        }
        List<mcz> b = meh.c(BaseApplication.e()).b(8, new HashMap(2));
        Iterator<MedalConfigInfo> it = arrayList.iterator();
        while (it.hasNext()) {
            MedalConfigInfo next = it.next();
            if (koq.c(b)) {
                String acquireMedalID = next.acquireMedalID();
                for (mcz mczVar : b) {
                    if (mczVar instanceof MedalLocation) {
                        MedalLocation medalLocation = (MedalLocation) mczVar;
                        if (acquireMedalID.equals(medalLocation.acquireMedalID())) {
                            int acquireGainedCount = medalLocation.acquireGainedCount();
                            String acquireMedalName = next.acquireMedalName();
                            String acquireMedalGainedTime = medalLocation.acquireMedalGainedTime();
                            list = b;
                            LogUtil.a("PLGACHIEVE_AchieveSingleTrackMedalService", "dealThreeCircleMedal Id= ", acquireMedalID, " name= ", acquireMedalName, " lastGainedTime=", acquireMedalGainedTime, " timestamp=", Long.valueOf(j));
                            if (c(j, acquireMedalGainedTime)) {
                                LogUtil.h("PLGACHIEVE_AchieveSingleTrackMedalService", "dealThreeCircleMedal isTheSameDay.");
                            } else {
                                d(next, str, j, acquireGainedCount);
                            }
                        }
                    }
                }
            } else {
                list = b;
                LogUtil.a("PLGACHIEVE_AchieveSingleTrackMedalService", "parseTakeMedal data isEmpty.");
                d.a(7, b(next, j));
            }
            b = list;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean c(String str) {
        try {
        } catch (JSONException e) {
            LogUtil.b("PLGACHIEVE_AchieveSingleTrackMedalService", "parseTakeMedal Exception:", e.getMessage());
        }
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        JSONObject jSONObject = new JSONObject(str);
        String optString = jSONObject.optString("resultCode");
        String optString2 = jSONObject.optString("resultDesc");
        if (mdn.e(optString)) {
            if (!"take medal failed.".equals(optString2)) {
                return true;
            }
        }
        return false;
    }

    private static Map<String, String> b(MedalConfigInfo medalConfigInfo, long j) {
        Map<String, String> c = d.c(medalConfigInfo.acquireMedalID(), String.valueOf(medalConfigInfo.acquireMedalLevel()), j);
        c.put("countryCode", LoginInit.getInstance(BaseApplication.e()).getAccountInfo(1010));
        return c;
    }

    private static void d(final MedalConfigInfo medalConfigInfo, final String str, final long j, final int i) {
        d.b(7, b(medalConfigInfo, j), new AchieveCallback() { // from class: mfe.1
            @Override // com.huawei.pluginachievement.impl.AchieveCallback
            public void onResponse(int i2, Object obj) {
                LogUtil.a("PLGACHIEVE_AchieveSingleTrackMedalService", "parseTakeMedal resCode:", Integer.valueOf(i2));
                if (i2 == 200 && (obj instanceof String)) {
                    String str2 = (String) obj;
                    LogUtil.a("PLGACHIEVE_AchieveSingleTrackMedalService", "parseTakeMedal medalId:", MedalConfigInfo.this.acquireMedalID(), " result:", str2);
                    if (mfe.c(str2)) {
                        mfe.b(MedalConfigInfo.this, str, j, i);
                    } else {
                        LogUtil.h("PLGACHIEVE_AchieveSingleTrackMedalService", "parseTakeMedal takeMedal fail!");
                    }
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void b(MedalConfigInfo medalConfigInfo, String str, long j, int i) {
        String acquireMedalID = medalConfigInfo.acquireMedalID();
        if (str.equals(acquireMedalID) && !mle.a(j, System.currentTimeMillis())) {
            d.d(acquireMedalID, medalConfigInfo.acquireMedalType(), medalConfigInfo.acquireMedalLevel(), i);
        } else {
            d(acquireMedalID, j, i);
        }
    }

    private static boolean c(long j, String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        return HiDateUtil.t(j) == HiDateUtil.t(mle.a(str));
    }
}
