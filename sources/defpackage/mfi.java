package defpackage;

import android.content.Context;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.pluginachievement.manager.model.AchieveInfo;
import com.huawei.pluginachievement.manager.model.KakaLineRecord;
import com.huawei.pluginachievement.manager.model.KakaRecord;
import com.huawei.pluginachievement.manager.model.RecentMonthRecord;
import com.huawei.pluginachievement.manager.model.RecentWeekRecord;
import com.huawei.pluginachievement.manager.model.SingleDayRecord;
import com.huawei.pluginachievement.manager.model.TotalRecord;
import java.io.IOException;
import java.io.InputStream;
import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

/* loaded from: classes6.dex */
public class mfi {
    public static String d(int i, String str) {
        if (i == 0) {
            return d("personal_preset_data.txt");
        }
        if (i == 8) {
            return d("get_medal_preset_data.txt");
        }
        if (i != 11) {
            return i != 13 ? str : d("level_preset_data.txt");
        }
        return d("kaka_task_preset_data.txt");
    }

    private static String d(String str) {
        String str2;
        InputStream e;
        LogUtil.b("AchievementMockUtil", "readPresetData start:", str);
        str2 = "";
        try {
            e = moh.e("achievement", str);
            try {
            } finally {
            }
        } catch (IOException unused) {
            LogUtil.b("AchievementMockUtil", "readPresetData failed :", str);
        }
        if (e == null) {
            LogUtil.h("AchievementMockUtil", "readPresetData inputStream is null");
            if (e != null) {
                e.close();
            }
            return "";
        }
        byte[] bArr = new byte[e.available()];
        str2 = e.read(bArr) > 0 ? new String(bArr, "UTF-8") : "";
        if (e != null) {
            e.close();
        }
        return str2;
    }

    public static void a(final Context context) {
        LogUtil.a("AchievementMockUtil", "insertStoreDemoAchieveData");
        ThreadPoolManager.d().execute(new Runnable() { // from class: mfi.1
            @Override // java.lang.Runnable
            public void run() {
                if (mfi.c(context)) {
                    return;
                }
                LogUtil.a("AchievementMockUtil", "insert storedemo achievement: try again");
                mfi.c(context);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean c(Context context) {
        e(context);
        if (!d(context)) {
            LogUtil.h("AchievementMockUtil", "insert storedemo insertKakaRecord failed");
            return false;
        }
        if (!i(context)) {
            LogUtil.h("AchievementMockUtil", "insert storedemo insertKakaRecord failed");
            return false;
        }
        if (!f(context)) {
            LogUtil.h("AchievementMockUtil", "insert storedemo insertKakaRecord failed");
            return false;
        }
        if (!h(context)) {
            LogUtil.h("AchievementMockUtil", "insert storedemo insertSingleDayRecord failed");
            return false;
        }
        if (!j(context)) {
            LogUtil.h("AchievementMockUtil", "insert storedemo insertRecentWeekRecord failed");
            return false;
        }
        if (!g(context)) {
            LogUtil.h("AchievementMockUtil", "insert storedemo insertRecentMonthRecord failed");
            return false;
        }
        LogUtil.a("AchievementMockUtil", "insert storedemo achievement pass");
        return true;
    }

    private static boolean d(Context context) {
        AchieveInfo achieveInfo = new AchieveInfo();
        achieveInfo.setUserLevel(2);
        achieveInfo.setUserPoint(60);
        achieveInfo.setUserReachStandardDays(10.0d);
        achieveInfo.setSyncTimestamp(System.currentTimeMillis());
        achieveInfo.saveMedals("");
        return mee.b(context).b(achieveInfo);
    }

    private static boolean i(Context context) {
        KakaLineRecord kakaLineRecord = new KakaLineRecord();
        ArrayList arrayList = new ArrayList(30);
        long currentTimeMillis = System.currentTimeMillis();
        for (int i = 0; i < 30; i++) {
            SecureRandom secureRandom = new SecureRandom();
            int nextInt = secureRandom.nextInt(9) + 1;
            int nextInt2 = (secureRandom.nextInt(5) + 1) * 5;
            long nextInt3 = secureRandom.nextInt(5) + (i * 20);
            int i2 = nextInt != 6 ? nextInt : 5;
            if (i2 == 3 || i2 == 9) {
                nextInt2 = -nextInt2;
            }
            arrayList.add(new KakaRecord(currentTimeMillis, i2, nextInt2, currentTimeMillis - (nextInt3 * 3481000)));
        }
        kakaLineRecord.setKakaLineRecords(arrayList);
        kakaLineRecord.setTotalNum(10);
        return mee.b(context).b(kakaLineRecord);
    }

    private static boolean f(Context context) {
        TotalRecord totalRecord = new TotalRecord();
        totalRecord.setStartDate(System.currentTimeMillis() - 2505600000L);
        totalRecord.setEndDate(System.currentTimeMillis());
        totalRecord.setDays(30);
        totalRecord.setDistance(204.0d);
        totalRecord.setSteps(277780.0d);
        totalRecord.saveCalorie(6779000.0d);
        totalRecord.setStepsRanking(1.35d);
        return mee.b(context).b(totalRecord);
    }

    private static boolean h(Context context) {
        SingleDayRecord singleDayRecord = new SingleDayRecord();
        singleDayRecord.saveBestStepDay(String.format(Locale.ROOT, "[{\"recordDay\":%s,\"value\":25160}]", new SimpleDateFormat("yyyyMMdd", Locale.ENGLISH).format(Long.valueOf(a(19)))));
        singleDayRecord.saveBestWalkDistance(e(a(5), 2120.0d));
        singleDayRecord.saveBestRunDistance(e(a(15), 22320.0d));
        singleDayRecord.saveBestRunPace(e(a(4), 341.0d));
        singleDayRecord.saveBestRun3KMPace(e(a(13), 1294.0d));
        singleDayRecord.saveBestRun5KMPace(e(a(14), 1873.0d));
        singleDayRecord.saveBestRunHMPace(e(a(15), 8179.0d));
        singleDayRecord.saveBestCycleDistance(e(a(27), 7890.0d));
        singleDayRecord.saveBestCycleSpeed(e(a(11), 281.25d));
        return mee.b(context).b(singleDayRecord);
    }

    private static long a(int i) {
        return System.currentTimeMillis() - (i * 86400000);
    }

    private static String e(long j, double d) {
        return String.format(Locale.ROOT, "[{\"startTime\":%d,\"deviceCode\":231378400,\"endTime\":%d,\"source\":1,\"value\":%f}]", Long.valueOf(j), Long.valueOf(j), Double.valueOf(d));
    }

    private static boolean j(Context context) {
        RecentWeekRecord recentWeekRecord = new RecentWeekRecord();
        recentWeekRecord.setSteps(69460.0d);
        recentWeekRecord.setDistance(12.4d);
        recentWeekRecord.setDistanceRanking(45.93d);
        recentWeekRecord.setReportNo(101);
        recentWeekRecord.setStepsRanking(53.41d);
        recentWeekRecord.setKakaNum(45);
        recentWeekRecord.setPrice(30);
        recentWeekRecord.setUserMedals("");
        recentWeekRecord.saveFirstDate(System.currentTimeMillis() - 518400000);
        recentWeekRecord.saveEndDate(System.currentTimeMillis());
        recentWeekRecord.setMinReportNo(0);
        recentWeekRecord.setUserMedals("1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19");
        return mee.b(context).b(recentWeekRecord);
    }

    private static boolean g(Context context) {
        RecentMonthRecord recentMonthRecord = new RecentMonthRecord();
        recentMonthRecord.setSteps(277780.0d);
        recentMonthRecord.setDistance(71.74d);
        recentMonthRecord.setReportNo(200);
        recentMonthRecord.setDistanceRanking(61.23d);
        recentMonthRecord.setStepsRanking(55.66d);
        recentMonthRecord.setKakaNum(60);
        recentMonthRecord.setPrice(30);
        recentMonthRecord.setMedals("");
        recentMonthRecord.saveFirstDate(System.currentTimeMillis() - 2505600000L);
        recentMonthRecord.saveEndDate(System.currentTimeMillis());
        recentMonthRecord.setMinReportNo(100);
        recentMonthRecord.setMedals("1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19");
        return mee.b(context).b(recentMonthRecord);
    }

    private static void e(Context context) {
        mee b = mee.b(context);
        b.d(new AchieveInfo());
        b.d(new KakaLineRecord());
        b.d(new SingleDayRecord());
        b.d(new RecentMonthRecord());
        b.d(new RecentWeekRecord());
    }
}
