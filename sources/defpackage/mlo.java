package defpackage;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;
import com.huawei.haf.application.BaseApplication;
import com.huawei.health.R;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.pluginachievement.report.bean.ReportClimbData;
import com.huawei.pluginachievement.report.bean.ReportRideData;
import com.huawei.pluginachievement.report.bean.ReportRunData;
import com.huawei.pluginachievement.report.bean.ReportSwimData;
import com.huawei.pluginachievement.report.bean.ReportWalkData;
import com.huawei.pluginhealthzone.interactors.HealthZonePushReceiver;
import health.compact.a.UnitUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/* loaded from: classes8.dex */
public class mlo {

    /* renamed from: a, reason: collision with root package name */
    private static final Integer[] f15053a = {Integer.valueOf(R.color._2131296401_res_0x7f090091), Integer.valueOf(R.color._2131296423_res_0x7f0900a7), Integer.valueOf(R.color._2131296411_res_0x7f09009b), Integer.valueOf(R.color._2131296403_res_0x7f090093), Integer.valueOf(R.color._2131296427_res_0x7f0900ab), Integer.valueOf(R.color._2131296421_res_0x7f0900a5), Integer.valueOf(R.color._2131296405_res_0x7f090095), Integer.valueOf(R.color._2131296399_res_0x7f09008f), Integer.valueOf(R.color._2131296407_res_0x7f090097), Integer.valueOf(R.color._2131296415_res_0x7f09009f), Integer.valueOf(R.color._2131296429_res_0x7f0900ad), Integer.valueOf(R.color._2131296417_res_0x7f0900a1), Integer.valueOf(R.color._2131296419_res_0x7f0900a3), Integer.valueOf(R.color._2131296413_res_0x7f09009d), Integer.valueOf(R.color._2131296409_res_0x7f090099), Integer.valueOf(R.color._2131296425_res_0x7f0900a9)};

    public static Drawable ckB_(Context context, int i, int i2) {
        Drawable drawable = ContextCompat.getDrawable(context, i);
        return drawable == null ? drawable : ckC_(drawable, i2);
    }

    public static Drawable ckC_(Drawable drawable, int i) {
        if (drawable == null) {
            return drawable;
        }
        Drawable wrap = DrawableCompat.wrap(drawable);
        DrawableCompat.setTint(wrap, i);
        return wrap;
    }

    public static void e(List<mkm> list, Map<String, Integer> map) {
        ArrayList arrayList = new ArrayList(map.entrySet());
        int intValue = f15053a[0].intValue();
        for (int i = 0; i < arrayList.size(); i++) {
            Integer[] numArr = f15053a;
            if (numArr.length > i) {
                intValue = numArr[i].intValue();
            }
            list.add(e(((String) ((Map.Entry) arrayList.get(i)).getKey()) + "x" + ((Map.Entry) arrayList.get(i)).getValue(), ((Integer) ((Map.Entry) arrayList.get(i)).getValue()).intValue(), 0, BaseApplication.e().getResources().getColor(intValue)));
        }
    }

    public static void b(Context context, List<Integer> list, List<String> list2, ArrayList<String> arrayList) {
        if (context == null || list == null || koq.b(arrayList)) {
            return;
        }
        if (arrayList.contains("1")) {
            list.add(Integer.valueOf(R.drawable._2131430710_res_0x7f0b0d36));
            list2.add(context.getResources().getString(R.string._2130840901_res_0x7f020d45));
        }
        if (arrayList.contains("2")) {
            list.add(Integer.valueOf(R.drawable._2131430702_res_0x7f0b0d2e));
            list2.add(context.getResources().getString(R.string._2130840902_res_0x7f020d46));
        }
        if (arrayList.contains("16")) {
            list.add(Integer.valueOf(R.drawable._2131430708_res_0x7f0b0d34));
            list2.add(context.getResources().getString(R.string._2130840903_res_0x7f020d47));
        }
        if (arrayList.contains("4")) {
            list.add(Integer.valueOf(R.drawable._2131430701_res_0x7f0b0d2d));
            list2.add(context.getResources().getString(R.string._2130840908_res_0x7f020d4c));
        }
        if (arrayList.contains("128")) {
            list.add(Integer.valueOf(R.drawable._2131430700_res_0x7f0b0d2c));
            list2.add(context.getResources().getString(R.string._2130840904_res_0x7f020d48));
        }
        if (arrayList.contains("256")) {
            list.add(Integer.valueOf(R.drawable._2131430709_res_0x7f0b0d35));
            list2.add(context.getResources().getString(R.string._2130840905_res_0x7f020d49));
        }
        if (arrayList.contains(HealthZonePushReceiver.PRESSURE_NOTIFY)) {
            list.add(Integer.valueOf(R.drawable._2131430711_res_0x7f0b0d37));
            list2.add(context.getResources().getString(R.string._2130840906_res_0x7f020d4a));
        }
        if (arrayList.contains("64")) {
            list2.add(context.getResources().getString(R.string._2130840907_res_0x7f020d4b));
            list.add(Integer.valueOf(R.drawable._2131430703_res_0x7f0b0d2f));
        }
        if (arrayList.contains("8")) {
            list.add(Integer.valueOf(R.drawable._2131430704_res_0x7f0b0d30));
            list2.add(context.getResources().getString(R.string._2130840909_res_0x7f020d4d));
        }
        if (arrayList.contains("512")) {
            list.add(Integer.valueOf(R.drawable._2131430705_res_0x7f0b0d31));
            list2.add(context.getResources().getString(R.string._2130840910_res_0x7f020d4e));
        }
    }

    private static mkm e(String str, int i, int i2, int i3) {
        mkm mkmVar = new mkm();
        mkmVar.b(str);
        mkmVar.b(i);
        mkmVar.e(i2);
        mkmVar.a(i3);
        mkmVar.c(R.drawable._2131428273_res_0x7f0b03b1);
        return mkmVar;
    }

    public static List<mkp> e(Context context, ReportRunData reportRunData) {
        ArrayList arrayList = new ArrayList(16);
        if (context != null && reportRunData != null) {
            if (reportRunData.getSumDistance() > 0) {
                arrayList.add(a(context.getResources().getString(R.string._2130841586_res_0x7f020ff2), e(reportRunData.getSumDistance()), c(), ""));
            }
            if (reportRunData.getSumTime() > 0) {
                arrayList.add(a(context.getResources().getString(R.string._2130842417_res_0x7f021331), c(reportRunData.getSumTime()), c(context, reportRunData.getSumTime()), ""));
            }
            if (reportRunData.getMaxDistance() > 0) {
                arrayList.add(a(context.getResources().getString(R.string._2130840961_res_0x7f020d81), e(reportRunData.getMaxDistance()), c(), ""));
            }
            if (reportRunData.getBestPace() > 0.0f && mlg.e(reportRunData.getBestPace(), 3.4028234663852886E38d) != 0) {
                arrayList.add(a(context.getResources().getString(R.string._2130840962_res_0x7f020d82), gvv.a(d(reportRunData)), "", ""));
            }
            a(context, arrayList, reportRunData);
        }
        return arrayList;
    }

    private static float d(ReportRunData reportRunData) {
        if (reportRunData == null) {
            return 0.0f;
        }
        if (UnitUtil.h()) {
            return reportRunData.getBritishBestPace();
        }
        return reportRunData.getBestPace();
    }

    public static String c(Context context, long j) {
        if (j > 3600) {
            return context.getResources().getString(R.string._2130841434_res_0x7f020f5a);
        }
        return context.getResources().getString(R.string._2130841436_res_0x7f020f5c);
    }

    public static String c(long j) {
        if (j > 3600) {
            return UnitUtil.e((j * 1.0d) / 3600.0d, 1, 1);
        }
        return UnitUtil.e((j * 1.0d) / 60.0d, 1, 2);
    }

    private static void a(Context context, List<mkp> list, ReportRunData reportRunData) {
        if (reportRunData.getFiveKmMinTime() > 0.0d && mlg.e(reportRunData.getFiveKmMinTime(), Double.MAX_VALUE) != 0) {
            list.add(a(String.format(Locale.ROOT, context.getString(R.string._2130840806_res_0x7f020ce6), 5), mlg.d((int) (reportRunData.getFiveKmMinTime() + 0.5d)), "", ""));
        }
        if (reportRunData.getTenKmMinTime() > 0.0d && mlg.e(reportRunData.getTenKmMinTime(), Double.MAX_VALUE) != 0) {
            list.add(a(String.format(Locale.ROOT, context.getString(R.string._2130840806_res_0x7f020ce6), 10), mlg.d((int) (reportRunData.getTenKmMinTime() + 0.5d)), "", ""));
        }
        if (reportRunData.getHalfMarathonCount() > 0 && reportRunData.getHalfMarathonMinTime() > 0.0d && mlg.e(reportRunData.getHalfMarathonMinTime(), Double.MAX_VALUE) != 0) {
            list.add(a(context.getResources().getString(R.string._2130841792_res_0x7f0210c0), UnitUtil.e(reportRunData.getHalfMarathonCount(), 1, 0), context.getResources().getString(R.string._2130841409_res_0x7f020f41), context.getResources().getString(R.string._2130840963_res_0x7f020d83, mlg.d((int) (reportRunData.getHalfMarathonMinTime() + 0.5d)))));
        }
        if (reportRunData.getFullMarathonCount() <= 0 || reportRunData.getFullMarathonMinTime() <= 0.0d || mlg.e(reportRunData.getFullMarathonMinTime(), Double.MAX_VALUE) == 0) {
            return;
        }
        list.add(a(context.getResources().getString(R.string._2130841793_res_0x7f0210c1), UnitUtil.e(reportRunData.getFullMarathonCount(), 1, 0), context.getResources().getString(R.string._2130841409_res_0x7f020f41), context.getResources().getString(R.string._2130840963_res_0x7f020d83, mlg.d((int) (reportRunData.getFullMarathonMinTime() + 0.5d)))));
    }

    public static String e(int i) {
        double d = mlg.d(i);
        return UnitUtil.h() ? UnitUtil.e(UnitUtil.e(d, 3), 1, 2) : UnitUtil.e(d, 1, 2);
    }

    public static String b(double d) {
        return UnitUtil.h() ? UnitUtil.e(UnitUtil.e(d, 3), 1, 2) : UnitUtil.e(d, 1, 2);
    }

    public static String c() {
        if (UnitUtil.h()) {
            return BaseApplication.e().getString(R.string._2130844081_res_0x7f0219b1);
        }
        return BaseApplication.e().getString(R.string._2130844082_res_0x7f0219b2);
    }

    public static mkp a(String str, String str2, String str3, String str4) {
        mkp mkpVar = new mkp();
        if (!TextUtils.isEmpty(str)) {
            mkpVar.a(str);
        }
        if (!TextUtils.isEmpty(str2)) {
            mkpVar.b(str2);
        }
        if (!TextUtils.isEmpty(str3)) {
            mkpVar.c(str3);
        }
        if (!TextUtils.isEmpty(str4)) {
            mkpVar.d(str4);
        }
        return mkpVar;
    }

    public static List<mkp> c(Context context, ReportRideData reportRideData) {
        ArrayList arrayList = new ArrayList(16);
        if (context == null || reportRideData == null) {
            LogUtil.h("AchieveSportTypeDataHelp", "reportSportType reportRideData is null");
            return arrayList;
        }
        if (reportRideData.getSumDistance() > 0) {
            arrayList.add(a(context.getResources().getString(R.string._2130841586_res_0x7f020ff2), e(reportRideData.getSumDistance()), c(), ""));
        }
        if (reportRideData.getSumTime() > 0) {
            arrayList.add(a(context.getResources().getString(R.string._2130842417_res_0x7f021331), c(reportRideData.getSumTime()), c(context, reportRideData.getSumTime()), ""));
        }
        if (reportRideData.getSumCreepingWave() > 0) {
            arrayList.add(a(context.getResources().getString(R.string._2130842325_res_0x7f0212d5), UnitUtil.e(reportRideData.getSumCreepingWave() / 10.0d, 1, 2), context.getResources().getString(R.string._2130841568_res_0x7f020fe0), ""));
        }
        if (reportRideData.getSumDescent() > 0.0f) {
            arrayList.add(a(context.getResources().getString(R.string._2130842545_res_0x7f0213b1), UnitUtil.e(reportRideData.getSumDescent() / 10.0d, 1, 2), context.getResources().getString(R.string._2130841568_res_0x7f020fe0), ""));
        }
        if (reportRideData.getMaxDistance() > 0) {
            arrayList.add(a(context.getResources().getString(R.string._2130840961_res_0x7f020d81), e(reportRideData.getMaxDistance()), c(), ""));
        }
        c(context, arrayList, reportRideData);
        return arrayList;
    }

    private static void c(Context context, List<mkp> list, ReportRideData reportRideData) {
        if (list == null || reportRideData == null || reportRideData.getBestPace() <= 0.0d) {
            return;
        }
        String string = context.getString(R.string._2130844078_res_0x7f0219ae);
        String a2 = mlg.a(reportRideData.getBestPace());
        if (UnitUtil.h()) {
            a2 = UnitUtil.e(UnitUtil.e(mef.d(a2), 3), 1, 2);
            string = context.getString(R.string._2130844079_res_0x7f0219af);
        }
        list.add(a(context.getResources().getString(R.string._2130840805_res_0x7f020ce5), a2, string, ""));
        LogUtil.a("AchieveSportTypeDataHelp", "setRideBestPace  contentValue = ", a2);
    }

    public static Drawable ckA_(int i) {
        return nrz.cKn_(BaseApplication.e(), i);
    }

    public static Drawable ckz_(int i) {
        hln c = hln.c(BaseApplication.e());
        Drawable drawable = BaseApplication.e().getDrawable(R.drawable.ic_health_list_outdoor_running);
        if (c.d(i) == null || c.d(i).getHistoryList() == null) {
            LogUtil.h("AchieveSportTypeDataHelp", "getSportTypeInfoById =null sportType= ", Integer.valueOf(i));
            return drawable;
        }
        return c.d(i).getHistoryList().getItemDrawable();
    }

    public static List<mkp> b(Context context, ReportSwimData reportSwimData) {
        ArrayList arrayList = new ArrayList(16);
        if (context != null && reportSwimData != null) {
            if (reportSwimData.getSumDistance() > 0) {
                arrayList.add(a(context.getResources().getString(R.string._2130841586_res_0x7f020ff2), e(reportSwimData.getSumDistance()), c(), ""));
            }
            if (reportSwimData.getSumTime() > 0) {
                arrayList.add(a(context.getResources().getString(R.string._2130842417_res_0x7f021331), c(reportSwimData.getSumTime()), c(context, reportSwimData.getSumTime()), ""));
            }
            if (reportSwimData.getMaxDistance() > 0) {
                arrayList.add(a(context.getResources().getString(R.string._2130840961_res_0x7f020d81), e(reportSwimData.getMaxDistance()), c(), ""));
            }
            e(context, arrayList, reportSwimData);
        }
        return arrayList;
    }

    private static void e(Context context, List<mkp> list, ReportSwimData reportSwimData) {
        if (context == null || reportSwimData == null || list == null || reportSwimData.getMaxStroke() == 0) {
            return;
        }
        list.add(a(context.getResources().getString(R.string._2130839812_res_0x7f020904), gwg.aUO_(context.getResources(), reportSwimData.getMaxStroke()), "", ""));
    }

    public static List<mkp> e(Context context, ReportWalkData reportWalkData) {
        ArrayList arrayList = new ArrayList(16);
        if (context != null && reportWalkData != null) {
            if (reportWalkData.getSumDistance() > 0) {
                arrayList.add(a(context.getResources().getString(R.string._2130841586_res_0x7f020ff2), e(reportWalkData.getSumDistance()), c(), ""));
            }
            if (reportWalkData.getSumTime() > 0) {
                arrayList.add(a(context.getResources().getString(R.string._2130842417_res_0x7f021331), c(reportWalkData.getSumTime()), c(context, reportWalkData.getSumTime()), ""));
            }
            if (reportWalkData.getMaxDistance() > 0) {
                arrayList.add(a(context.getResources().getString(R.string._2130840961_res_0x7f020d81), e(reportWalkData.getMaxDistance()), c(), ""));
            }
            if (reportWalkData.getMaxStepRate() > 0) {
                arrayList.add(a(context.getResources().getString(R.string._2130840968_res_0x7f020d88), UnitUtil.e(reportWalkData.getMaxStepRate(), 1, 0), context.getResources().getString(R.string._2130839766_res_0x7f0208d6), ""));
            }
        }
        return arrayList;
    }

    public static List<mkp> e(Context context, ReportClimbData reportClimbData) {
        ArrayList arrayList = new ArrayList(16);
        if (context != null && reportClimbData != null) {
            if (reportClimbData.getSumDistance() > 0) {
                arrayList.add(a(context.getResources().getString(R.string._2130841586_res_0x7f020ff2), e(reportClimbData.getSumDistance()), c(), ""));
            }
            if (reportClimbData.getSumTime() > 0) {
                arrayList.add(a(context.getResources().getString(R.string._2130842417_res_0x7f021331), c(reportClimbData.getSumTime()), c(context, reportClimbData.getSumTime()), ""));
            }
            if (reportClimbData.getMaxHeight() > 0.0f) {
                arrayList.add(a(context.getResources().getString(R.string._2130840964_res_0x7f020d84), b(reportClimbData.getMaxHeight()), context.getResources().getString(R.string._2130841568_res_0x7f020fe0), ""));
            }
        }
        return arrayList;
    }
}
