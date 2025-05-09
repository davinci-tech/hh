package defpackage;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import androidx.core.content.ContextCompat;
import com.huawei.haf.application.BaseApplication;
import com.huawei.health.R;
import com.huawei.hihealth.util.HiJsonUtil;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.pluginachievement.manager.model.WeekAndMonthRecord;
import com.huawei.pluginachievement.report.bean.ReportClimbData;
import com.huawei.pluginachievement.report.bean.ReportCrossTrainerData;
import com.huawei.pluginachievement.report.bean.ReportDataBean;
import com.huawei.pluginachievement.report.bean.ReportFitnessData;
import com.huawei.pluginachievement.report.bean.ReportHikingData;
import com.huawei.pluginachievement.report.bean.ReportOtherData;
import com.huawei.pluginachievement.report.bean.ReportRideData;
import com.huawei.pluginachievement.report.bean.ReportRowData;
import com.huawei.pluginachievement.report.bean.ReportRunData;
import com.huawei.pluginachievement.report.bean.ReportSwimData;
import com.huawei.pluginachievement.report.bean.ReportTrailRunData;
import com.huawei.pluginachievement.report.bean.ReportWalkData;
import health.compact.a.LanguageUtil;
import health.compact.a.UnitUtil;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes8.dex */
public class mlm {
    private static final Integer[] c = {Integer.valueOf(R.color._2131296423_res_0x7f0900a7), Integer.valueOf(R.color._2131296407_res_0x7f090097), Integer.valueOf(R.color._2131296411_res_0x7f09009b), Integer.valueOf(R.color._2131296401_res_0x7f090091), Integer.valueOf(R.color._2131296427_res_0x7f0900ab), Integer.valueOf(R.color._2131296403_res_0x7f090093), Integer.valueOf(R.color._2131296421_res_0x7f0900a5), Integer.valueOf(R.color._2131296405_res_0x7f090095), Integer.valueOf(R.color._2131296399_res_0x7f09008f), Integer.valueOf(R.color._2131296415_res_0x7f09009f), Integer.valueOf(R.color._2131296429_res_0x7f0900ad), Integer.valueOf(R.color._2131296417_res_0x7f0900a1), Integer.valueOf(R.color._2131296419_res_0x7f0900a3), Integer.valueOf(R.color._2131296413_res_0x7f09009d), Integer.valueOf(R.color._2131296409_res_0x7f090099), Integer.valueOf(R.color._2131296425_res_0x7f0900a9)};
    private static final Integer[] e = {Integer.valueOf(R.color._2131296422_res_0x7f0900a6), Integer.valueOf(R.color._2131296406_res_0x7f090096), Integer.valueOf(R.color._2131296410_res_0x7f09009a), Integer.valueOf(R.color._2131296400_res_0x7f090090), Integer.valueOf(R.color._2131296426_res_0x7f0900aa), Integer.valueOf(R.color._2131296402_res_0x7f090092), Integer.valueOf(R.color._2131296420_res_0x7f0900a4), Integer.valueOf(R.color._2131296404_res_0x7f090094), Integer.valueOf(R.color._2131296398_res_0x7f09008e), Integer.valueOf(R.color._2131296414_res_0x7f09009e), Integer.valueOf(R.color._2131296428_res_0x7f0900ac), Integer.valueOf(R.color._2131296416_res_0x7f0900a0), Integer.valueOf(R.color._2131296418_res_0x7f0900a2), Integer.valueOf(R.color._2131296412_res_0x7f09009c), Integer.valueOf(R.color._2131296408_res_0x7f090098), Integer.valueOf(R.color._2131296424_res_0x7f0900a8)};
    private static Map<Integer, Class> b = new HashMap(16);

    public static Map<Integer, Class> c() {
        if (b.size() == 0) {
            b.put(2008, ReportRunData.class);
            b.put(2009, ReportSwimData.class);
            b.put(2010, ReportTrailRunData.class);
            b.put(2011, ReportWalkData.class);
            b.put(2001, ReportClimbData.class);
            b.put(2002, ReportCrossTrainerData.class);
            b.put(2003, ReportFitnessData.class);
            b.put(2004, ReportHikingData.class);
            b.put(2005, ReportOtherData.class);
            b.put(2006, ReportRideData.class);
            b.put(2007, ReportRowData.class);
        }
        return b;
    }

    public static List<mjx> b(Context context, WeekAndMonthRecord weekAndMonthRecord, int i) {
        LogUtil.a("AchieveSportTypeViewHolder", "reportSportType getSportTypeBeanList enter, type == ", Integer.valueOf(i));
        ArrayList arrayList = new ArrayList(16);
        ArrayList<ReportDataBean> a2 = mli.a(weekAndMonthRecord, i);
        if (koq.b(a2)) {
            LogUtil.h("AchieveSportTypeViewHolder", "reportSportType reportDataDetailList is null.");
            return arrayList;
        }
        LogUtil.a("AchieveSportTypeViewHolder", "reportSportType reportDataDetailList size == ", Integer.valueOf(a2.size()));
        int size = a2.size();
        List<ReportDataBean> list = a2;
        if (size > 16) {
            list = a2.subList(0, 16);
        }
        if (koq.b(list)) {
            LogUtil.h("AchieveSportTypeViewHolder", "reportSportType reportDataList is null.");
            return arrayList;
        }
        ArrayList arrayList2 = new ArrayList(16);
        ArrayList arrayList3 = new ArrayList(16);
        ArrayList arrayList4 = new ArrayList(16);
        for (ReportDataBean reportDataBean : list) {
            if (reportDataBean instanceof ReportOtherData) {
                arrayList2.add((ReportOtherData) reportDataBean);
            } else {
                mjx b2 = b(reportDataBean, arrayList3, arrayList4);
                if (b2 != null) {
                    arrayList.add(b2);
                }
            }
        }
        ArrayList arrayList5 = new ArrayList(Arrays.asList(e));
        ArrayList arrayList6 = new ArrayList(Arrays.asList(c));
        int size2 = arrayList2.size();
        if (size2 > 0) {
            arrayList6.removeAll(arrayList3);
            arrayList5.removeAll(arrayList4);
            for (int i2 = 0; i2 < size2; i2++) {
                if (koq.d(arrayList6, i2) && koq.d(arrayList5, i2)) {
                    arrayList.add(c(context, (ReportOtherData) arrayList2.get(i2), ((Integer) arrayList6.get(i2)).intValue(), ((Integer) arrayList5.get(i2)).intValue()));
                }
            }
        }
        return arrayList;
    }

    private static mjx b(ReportDataBean reportDataBean, List<Integer> list, List<Integer> list2) {
        Context e2 = BaseApplication.e();
        if (reportDataBean instanceof ReportRunData) {
            return c(e2, (ReportRunData) reportDataBean, list, list2);
        }
        if (reportDataBean instanceof ReportWalkData) {
            return d(e2, (ReportWalkData) reportDataBean, list, list2);
        }
        if (reportDataBean instanceof ReportRideData) {
            return a(e2, (ReportRideData) reportDataBean, list, list2);
        }
        if (reportDataBean instanceof ReportSwimData) {
            return c(e2, (ReportSwimData) reportDataBean, list, list2);
        }
        if (reportDataBean instanceof ReportClimbData) {
            return c(e2, (ReportClimbData) reportDataBean, list, list2);
        }
        if (reportDataBean instanceof ReportCrossTrainerData) {
            return c(e2, (ReportCrossTrainerData) reportDataBean, list, list2);
        }
        if (reportDataBean instanceof ReportFitnessData) {
            return e(e2, (ReportFitnessData) reportDataBean, list, list2);
        }
        if (reportDataBean instanceof ReportHikingData) {
            return a(e2, (ReportHikingData) reportDataBean, list, list2);
        }
        if (reportDataBean instanceof ReportRowData) {
            return c(e2, (ReportRowData) reportDataBean, list, list2);
        }
        if (reportDataBean instanceof ReportTrailRunData) {
            return d(e2, (ReportTrailRunData) reportDataBean, list, list2);
        }
        return null;
    }

    private static mkl c(Context context, ReportRunData reportRunData, List<Integer> list, List<Integer> list2) {
        list.add(Integer.valueOf(R.color._2131296423_res_0x7f0900a7));
        list2.add(Integer.valueOf(R.color._2131296422_res_0x7f0900a6));
        mkl mklVar = new mkl();
        mklVar.c(c(context.getResources().getString(R.string._2130842432_res_0x7f021340), a(reportRunData.getSportCount())));
        mklVar.a(reportRunData.getSportType());
        mklVar.d(reportRunData.getSportCount());
        mklVar.cjB_(ckE_(context, R.drawable._2131427747_res_0x7f0b01a3));
        mklVar.e(context.getResources().getColor(R.color._2131296423_res_0x7f0900a7));
        mklVar.b(context.getResources().getColor(R.color._2131296422_res_0x7f0900a6));
        mklVar.b(context.getResources().getString(R.string._2130842432_res_0x7f021340));
        e(mklVar, R.drawable._2131431781_res_0x7f0b1165, R.drawable._2131427785_res_0x7f0b01c9);
        mklVar.c(mlo.e(context, reportRunData));
        return mklVar;
    }

    private static String c(String str, String str2) {
        if (LanguageUtil.h(BaseApplication.e())) {
            return str + "x" + str2;
        }
        if (LanguageUtil.bc(BaseApplication.e())) {
            return str + " " + str2;
        }
        return str + " x" + str2;
    }

    private static Drawable ckE_(Context context, int i) {
        if (LanguageUtil.bc(context)) {
            return nrz.cKn_(context, i);
        }
        return context.getResources().getDrawable(i);
    }

    private static mkl d(Context context, ReportWalkData reportWalkData, List<Integer> list, List<Integer> list2) {
        list.add(Integer.valueOf(R.color._2131296429_res_0x7f0900ad));
        list2.add(Integer.valueOf(R.color._2131296428_res_0x7f0900ac));
        mkl mklVar = new mkl();
        mklVar.c(c(context.getResources().getString(R.string._2130841427_res_0x7f020f53), a(reportWalkData.getSportCount())));
        mklVar.a(reportWalkData.getSportType());
        mklVar.d(reportWalkData.getSportCount());
        mklVar.cjB_(ckE_(context, R.drawable._2131427749_res_0x7f0b01a5));
        mklVar.e(context.getResources().getColor(R.color._2131296429_res_0x7f0900ad));
        mklVar.b(context.getResources().getColor(R.color._2131296428_res_0x7f0900ac));
        mklVar.b(context.getResources().getString(R.string._2130841427_res_0x7f020f53));
        e(mklVar, R.drawable._2131431783_res_0x7f0b1167, R.drawable._2131427787_res_0x7f0b01cb);
        mklVar.c(mlo.e(context, reportWalkData));
        return mklVar;
    }

    private static mkl a(Context context, ReportRideData reportRideData, List<Integer> list, List<Integer> list2) {
        list.add(Integer.valueOf(R.color._2131296399_res_0x7f09008f));
        list2.add(Integer.valueOf(R.color._2131296398_res_0x7f09008e));
        mkl mklVar = new mkl();
        mklVar.c(c(context.getResources().getString(R.string._2130842145_res_0x7f021221), a(reportRideData.getSportCount())));
        mklVar.a(reportRideData.getSportType());
        mklVar.d(reportRideData.getSportCount());
        mklVar.cjB_(ckE_(context, R.drawable._2131427742_res_0x7f0b019e));
        mklVar.e(context.getResources().getColor(R.color._2131296399_res_0x7f09008f));
        mklVar.b(context.getResources().getColor(R.color._2131296398_res_0x7f09008e));
        mklVar.b(context.getResources().getString(R.string._2130842145_res_0x7f021221));
        e(mklVar, R.drawable._2131431775_res_0x7f0b115f, R.drawable._2131427779_res_0x7f0b01c3);
        mklVar.c(mlo.c(context, reportRideData));
        return mklVar;
    }

    private static mkl c(Context context, ReportSwimData reportSwimData, List<Integer> list, List<Integer> list2) {
        list.add(Integer.valueOf(R.color._2131296427_res_0x7f0900ab));
        list2.add(Integer.valueOf(R.color._2131296426_res_0x7f0900aa));
        mkl mklVar = new mkl();
        mklVar.c(c(context.getResources().getString(R.string._2130843186_res_0x7f021632), a(reportSwimData.getSportCount())));
        mklVar.a(reportSwimData.getSportType());
        mklVar.d(reportSwimData.getSportCount());
        mklVar.cjB_(ckE_(context, R.drawable._2131427748_res_0x7f0b01a4));
        mklVar.e(context.getResources().getColor(R.color._2131296427_res_0x7f0900ab));
        mklVar.b(context.getResources().getColor(R.color._2131296426_res_0x7f0900aa));
        mklVar.b(context.getResources().getString(R.string._2130843186_res_0x7f021632));
        e(mklVar, R.drawable._2131431782_res_0x7f0b1166, R.drawable._2131427786_res_0x7f0b01ca);
        mklVar.c(mlo.b(context, reportSwimData));
        return mklVar;
    }

    private static mkl c(Context context, ReportClimbData reportClimbData, List<Integer> list, List<Integer> list2) {
        list.add(Integer.valueOf(R.color._2131296401_res_0x7f090091));
        list2.add(Integer.valueOf(R.color._2131296400_res_0x7f090090));
        mkl mklVar = new mkl();
        mklVar.c(c(context.getResources().getString(R.string.IDS_motiontrack_climb_hill_tip), a(reportClimbData.getSportCount())));
        mklVar.a(reportClimbData.getSportType());
        mklVar.d(reportClimbData.getSportCount());
        mklVar.cjB_(ckE_(context, R.drawable._2131427740_res_0x7f0b019c));
        mklVar.e(context.getResources().getColor(R.color._2131296401_res_0x7f090091));
        mklVar.b(context.getResources().getColor(R.color._2131296400_res_0x7f090090));
        mklVar.b(context.getResources().getString(R.string.IDS_motiontrack_climb_hill_tip));
        e(mklVar, R.drawable._2131431778_res_0x7f0b1162, R.drawable._2131427783_res_0x7f0b01c7);
        mklVar.c(mlo.e(context, reportClimbData));
        return mklVar;
    }

    private static mkl c(Context context, ReportCrossTrainerData reportCrossTrainerData, List<Integer> list, List<Integer> list2) {
        list.add(Integer.valueOf(R.color._2131296405_res_0x7f090095));
        list2.add(Integer.valueOf(R.color._2131296404_res_0x7f090094));
        mkl mklVar = new mkl();
        mklVar.c(c(context.getResources().getString(R.string.IDS_hwh_sport_type_cross_trainer), a(reportCrossTrainerData.getSportCount())));
        mklVar.a(reportCrossTrainerData.getSportType());
        mklVar.d(reportCrossTrainerData.getSportCount());
        mklVar.cjB_(ckE_(context, R.drawable._2131427743_res_0x7f0b019f));
        mklVar.e(context.getResources().getColor(R.color._2131296405_res_0x7f090095));
        mklVar.b(context.getResources().getColor(R.color._2131296404_res_0x7f090094));
        mklVar.b(context.getResources().getString(R.string.IDS_hwh_sport_type_cross_trainer));
        e(mklVar, R.drawable._2131431776_res_0x7f0b1160, R.drawable._2131427780_res_0x7f0b01c4);
        ArrayList arrayList = new ArrayList(16);
        arrayList.add(mlo.a(context.getResources().getString(R.string._2130842417_res_0x7f021331), mlo.c(reportCrossTrainerData.getSumTime()), mlo.c(context, reportCrossTrainerData.getSumTime()), ""));
        if (reportCrossTrainerData.getMaxTime() > 0) {
            arrayList.add(mlo.a(context.getResources().getString(R.string._2130840965_res_0x7f020d85), String.valueOf(mlg.d((int) reportCrossTrainerData.getMaxTime())), "", ""));
        }
        mklVar.c(arrayList);
        return mklVar;
    }

    private static mkf e(Context context, ReportFitnessData reportFitnessData, List<Integer> list, List<Integer> list2) {
        list.add(Integer.valueOf(R.color._2131296407_res_0x7f090097));
        list2.add(Integer.valueOf(R.color._2131296406_res_0x7f090096));
        mkf mkfVar = new mkf();
        mkfVar.c(c(context.getResources().getString(R.string._2130842862_res_0x7f0214ee), a(reportFitnessData.getSportCount())));
        mkfVar.a(-1);
        mkfVar.d(reportFitnessData.getSportCount());
        mkfVar.cjB_(ckE_(context, R.drawable._2131427744_res_0x7f0b01a0));
        mkfVar.e(context.getResources().getColor(R.color._2131296407_res_0x7f090097));
        mkfVar.b(context.getResources().getColor(R.color._2131296406_res_0x7f090096));
        mkfVar.b(context.getResources().getString(R.string._2130842862_res_0x7f0214ee));
        if (LanguageUtil.bc(context)) {
            mkfVar.cjz_(mlo.ckA_(R.drawable._2131431777_res_0x7f0b1161));
            mkfVar.cjA_(mlo.ckA_(R.drawable._2131427781_res_0x7f0b01c5));
        } else {
            mkfVar.cjz_(ContextCompat.getDrawable(context, R.drawable._2131431777_res_0x7f0b1161));
            mkfVar.cjA_(ContextCompat.getDrawable(context, R.drawable._2131427781_res_0x7f0b01c5));
        }
        ArrayList arrayList = new ArrayList(16);
        mlo.e(arrayList, reportFitnessData.getFirstLevelMap());
        mkfVar.d(arrayList);
        mkfVar.a(reportFitnessData.getMaxFirstLevel());
        mkfVar.d(reportFitnessData.getMaxCourse());
        mkfVar.g(UnitUtil.e(reportFitnessData.getSumTime() / 60.0f, 1, 2));
        mkfVar.e(mlg.d((int) reportFitnessData.getMaxTime()));
        ArrayList arrayList2 = new ArrayList(16);
        ArrayList arrayList3 = new ArrayList(16);
        mlo.b(context, arrayList2, arrayList3, reportFitnessData.getMaxTrainingPoints());
        mkfVar.c(arrayList2);
        mkfVar.b(arrayList3);
        return mkfVar;
    }

    private static mkl a(Context context, ReportHikingData reportHikingData, List<Integer> list, List<Integer> list2) {
        list.add(Integer.valueOf(R.color._2131296411_res_0x7f09009b));
        list2.add(Integer.valueOf(R.color._2131296410_res_0x7f09009a));
        mkl mklVar = new mkl();
        mklVar.c(c(context.getResources().getString(R.string.IDS_hwh_sport_type_hiking), a(reportHikingData.getSportCount())));
        mklVar.a(reportHikingData.getSportType());
        mklVar.d(reportHikingData.getSportCount());
        mklVar.cjB_(ckE_(context, R.drawable._2131427745_res_0x7f0b01a1));
        mklVar.e(context.getResources().getColor(R.color._2131296411_res_0x7f09009b));
        mklVar.b(context.getResources().getColor(R.color._2131296410_res_0x7f09009a));
        mklVar.b(context.getResources().getString(R.string.IDS_hwh_sport_type_hiking));
        e(mklVar, R.drawable._2131431779_res_0x7f0b1163, R.drawable._2131427782_res_0x7f0b01c6);
        ArrayList arrayList = new ArrayList(16);
        arrayList.add(mlo.a(context.getResources().getString(R.string._2130841586_res_0x7f020ff2), mlo.e(reportHikingData.getSumDistance()), mlo.c(), ""));
        arrayList.add(mlo.a(context.getResources().getString(R.string._2130842417_res_0x7f021331), mlo.c(reportHikingData.getSumTime()), mlo.c(context, reportHikingData.getSumTime()), ""));
        arrayList.add(mlo.a(context.getResources().getString(R.string._2130840961_res_0x7f020d81), mlo.e(reportHikingData.getMaxDistance()), mlo.c(), ""));
        mklVar.c(arrayList);
        return mklVar;
    }

    private static String a(int i) {
        return UnitUtil.e(i, 1, 0);
    }

    private static mkl c(Context context, ReportRowData reportRowData, List<Integer> list, List<Integer> list2) {
        list.add(Integer.valueOf(R.color._2131296421_res_0x7f0900a5));
        list2.add(Integer.valueOf(R.color._2131296420_res_0x7f0900a4));
        mkl mklVar = new mkl();
        mklVar.c(c(context.getResources().getString(R.string.IDS_hwh_sport_type_row_machine), a(reportRowData.getSportCount())));
        mklVar.a(reportRowData.getSportType());
        mklVar.d(reportRowData.getSportCount());
        mklVar.cjB_(ckE_(context, R.drawable._2131427746_res_0x7f0b01a2));
        mklVar.e(context.getResources().getColor(R.color._2131296421_res_0x7f0900a5));
        mklVar.b(context.getResources().getColor(R.color._2131296420_res_0x7f0900a4));
        mklVar.b(context.getResources().getString(R.string.IDS_hwh_sport_type_row_machine));
        e(mklVar, R.drawable._2131431780_res_0x7f0b1164, R.drawable._2131427784_res_0x7f0b01c8);
        ArrayList arrayList = new ArrayList(16);
        arrayList.add(mlo.a(context.getResources().getString(R.string._2130842417_res_0x7f021331), mlo.c(reportRowData.getSumTime()), mlo.c(context, reportRowData.getSumTime()), ""));
        arrayList.add(mlo.a(context.getResources().getString(R.string._2130840965_res_0x7f020d85), mlg.d((int) reportRowData.getMaxTime()), "", ""));
        if (reportRowData.getMaxPaddle() > 0.0f) {
            arrayList.add(mlo.a(context.getResources().getString(R.string._2130840966_res_0x7f020d86), a((int) reportRowData.getMaxPaddle()), context.getResources().getString(R.string._2130843497_res_0x7f021769), ""));
        }
        if (reportRowData.getMaxPaddleTimes() > 0.0f) {
            arrayList.add(mlo.a(context.getResources().getString(R.string._2130840967_res_0x7f020d87), a((int) reportRowData.getMaxPaddleTimes()), context.getResources().getString(R.string._2130841409_res_0x7f020f41), ""));
        }
        mklVar.c(arrayList);
        return mklVar;
    }

    private static void e(mkl mklVar, int i, int i2) {
        if (LanguageUtil.bc(BaseApplication.e())) {
            mklVar.cjz_(mlo.ckA_(i));
            mklVar.cjA_(mlo.ckA_(i2));
        } else {
            mklVar.cjz_(ContextCompat.getDrawable(BaseApplication.e(), i));
            mklVar.cjA_(ContextCompat.getDrawable(BaseApplication.e(), i2));
        }
    }

    public static ReportDataBean e(String str, int i) {
        Object e2 = HiJsonUtil.e(str, c().get(Integer.valueOf(i)));
        ReportDataBean reportDataBean = e2 instanceof ReportDataBean ? (ReportDataBean) e2 : null;
        LogUtil.c("AchieveSportTypeViewHolder", "reportSportType getReport ", reportDataBean);
        return reportDataBean;
    }

    private static mkl d(Context context, ReportTrailRunData reportTrailRunData, List<Integer> list, List<Integer> list2) {
        list.add(Integer.valueOf(R.color._2131296403_res_0x7f090093));
        list2.add(Integer.valueOf(R.color._2131296402_res_0x7f090092));
        mkl mklVar = new mkl();
        mklVar.c(c(context.getResources().getString(R.string.IDS_motiontrack_cross_country_race), a(reportTrailRunData.getSportCount())));
        mklVar.a(reportTrailRunData.getSportType());
        mklVar.d(reportTrailRunData.getSportCount());
        mklVar.cjB_(ckE_(context, R.drawable._2131427741_res_0x7f0b019d));
        mklVar.e(context.getResources().getColor(R.color._2131296403_res_0x7f090093));
        mklVar.b(context.getResources().getColor(R.color._2131296402_res_0x7f090092));
        mklVar.b(context.getResources().getString(R.string.IDS_motiontrack_cross_country_race));
        e(mklVar, R.drawable._2131431774_res_0x7f0b115e, R.drawable._2131427778_res_0x7f0b01c2);
        ArrayList arrayList = new ArrayList(16);
        arrayList.add(mlo.a(context.getResources().getString(R.string._2130841586_res_0x7f020ff2), mlo.e(reportTrailRunData.getSumDistance()), mlo.c(), ""));
        arrayList.add(mlo.a(context.getResources().getString(R.string._2130842417_res_0x7f021331), mlo.c(reportTrailRunData.getSumTime()), mlo.c(context, reportTrailRunData.getSumTime()), ""));
        arrayList.add(mlo.a(context.getResources().getString(R.string._2130840961_res_0x7f020d81), mlo.e(reportTrailRunData.getMaxDistance()), mlo.c(), ""));
        mklVar.c(arrayList);
        return mklVar;
    }

    private static mkl c(Context context, ReportOtherData reportOtherData, int i, int i2) {
        mkl mklVar = new mkl();
        String e2 = gwg.e(context, reportOtherData.getSportType());
        mklVar.c(c(e2, a(reportOtherData.getSportCount())));
        mklVar.a(reportOtherData.getSportType());
        mklVar.d(reportOtherData.getSportCount());
        Drawable ckz_ = mlo.ckz_(reportOtherData.getSportType());
        if (LanguageUtil.bc(context)) {
            mklVar.cjB_(mlo.ckC_(nrz.cKm_(context, ckz_), context.getResources().getColor(i)));
        } else {
            mklVar.cjB_(mlo.ckC_(ckz_, context.getResources().getColor(i)));
        }
        mklVar.e(context.getResources().getColor(i));
        mklVar.b(context.getResources().getColor(i2));
        mklVar.b(e2);
        if (LanguageUtil.bc(context)) {
            mklVar.cjz_(mlo.ckC_(mlo.ckA_(R.drawable._2131431781_res_0x7f0b1165), context.getResources().getColor(i)));
        } else {
            mklVar.cjz_(mlo.ckB_(context, R.drawable._2131431781_res_0x7f0b1165, context.getResources().getColor(i)));
        }
        mklVar.cjy_(ckD_(reportOtherData));
        mklVar.c(context.getResources().getColor(i));
        ArrayList arrayList = new ArrayList(16);
        arrayList.add(mlo.a(context.getResources().getString(R.string._2130842417_res_0x7f021331), mlo.c(reportOtherData.getSumTime()), mlo.c(context, reportOtherData.getSumTime()), ""));
        arrayList.add(mlo.a(context.getResources().getString(R.string._2130840965_res_0x7f020d85), mlg.d((int) reportOtherData.getMaxTime()), "", ""));
        mklVar.c(arrayList);
        return mklVar;
    }

    private static Drawable ckD_(ReportOtherData reportOtherData) {
        Drawable ckz_ = mlo.ckz_(reportOtherData.getSportType());
        BitmapDrawable cKm_ = nrz.cKm_(BaseApplication.e(), ckz_);
        int color = BaseApplication.e().getResources().getColor(R.color._2131296384_res_0x7f090080);
        if (cKm_ != null) {
            return mlo.ckC_(cKm_, color);
        }
        return mlo.ckC_(ckz_, color);
    }

    public static mkp b(Context context, List<mjx> list) {
        mkp mkpVar = new mkp();
        if (context == null || koq.b(list)) {
            LogUtil.h("AchieveSportTypeViewHolder", "getSportTypeItem reportTypeBeanList is null.");
            return mkpVar;
        }
        Iterator<mjx> it = list.iterator();
        int i = 0;
        while (it.hasNext()) {
            i += it.next().i();
        }
        mkpVar.b(a(i));
        mkpVar.c(context.getString(R.string._2130841409_res_0x7f020f41));
        mkpVar.d(System.lineSeparator() + context.getString(R.string._2130842421_res_0x7f021335));
        return mkpVar;
    }
}
