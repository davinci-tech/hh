package defpackage;

import android.os.SystemClock;
import android.text.TextUtils;
import com.huawei.health.R;
import com.huawei.health.servicesui.R$string;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.pluginfitnessadvice.audio.PlayRecordInfo;
import com.huawei.pluginfitnessadvice.audio.SleepAudioGroup;
import com.huawei.pluginfitnessadvice.audio.SleepAudioInfo;
import com.huawei.pluginfitnessadvice.audio.SleepAudioPackage;
import com.huawei.pluginfitnessadvice.audio.SleepAudioSeries;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import health.compact.a.UnitUtil;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes4.dex */
public class ffv {
    private static int a(int i, int i2) {
        if (i2 >= i) {
            return 100;
        }
        return (int) (((i2 * 1.0f) / i) * 100.0f);
    }

    public static boolean e(int i) {
        return i == 2;
    }

    public static final String e(SleepAudioSeries sleepAudioSeries) {
        int isVip = sleepAudioSeries.getIsVip();
        if (isVip == 0) {
            return BaseApplication.getContext().getResources().getString(R$string.IDS_sleep_decompression_free);
        }
        if (isVip == 1) {
            return BaseApplication.getContext().getResources().getString(R$string.IDS_vip);
        }
        if (isVip != 2) {
            return "";
        }
        int purchasedStatus = sleepAudioSeries.getPurchasedStatus();
        if (purchasedStatus == 1) {
            return BaseApplication.getContext().getResources().getString(R$string.IDS_sleep_decompression_purchased);
        }
        return purchasedStatus == 2 ? BaseApplication.getContext().getResources().getString(R$string.IDS_sug_series_course_pay) : "";
    }

    public static final String d(int i, int i2) {
        if (i == 0) {
            return BaseApplication.getContext().getResources().getString(R$string.IDS_sleep_decompression_free);
        }
        if (i == 1) {
            return BaseApplication.getContext().getResources().getString(R$string.IDS_vip);
        }
        if (i != 2) {
            return "";
        }
        if (i2 == 1) {
            return BaseApplication.getContext().getResources().getString(R$string.IDS_sleep_decompression_purchased);
        }
        return BaseApplication.getContext().getResources().getString(R$string.IDS_sug_series_course_pay);
    }

    public static final String a(SleepAudioInfo sleepAudioInfo, SleepAudioSeries sleepAudioSeries) {
        int isVip = sleepAudioInfo.getIsVip();
        if (isVip == 0) {
            return BaseApplication.getContext().getResources().getString(R$string.IDS_sleep_decompression_free);
        }
        if (isVip != 2) {
            return "";
        }
        if (a(sleepAudioSeries)) {
            return BaseApplication.getContext().getResources().getString(R$string.IDS_sleep_decompression_purchased);
        }
        if (a(sleepAudioInfo)) {
            return BaseApplication.getContext().getResources().getString(R$string.IDS_sleep_decompression_try_listening);
        }
        return BaseApplication.getContext().getResources().getString(R$string.IDS_sug_series_course_pay);
    }

    public static final boolean b(SleepAudioSeries sleepAudioSeries) {
        return sleepAudioSeries.getIsVip() == 1;
    }

    public static final boolean d(SleepAudioInfo sleepAudioInfo) {
        return sleepAudioInfo.getIsVip() == 1;
    }

    public static final boolean c(SleepAudioSeries sleepAudioSeries) {
        return sleepAudioSeries.getIsVip() == 2;
    }

    public static final boolean a(SleepAudioInfo sleepAudioInfo) {
        return !TextUtils.isEmpty(sleepAudioInfo.getAuditionUrl());
    }

    public static final boolean a(SleepAudioSeries sleepAudioSeries) {
        return sleepAudioSeries.getPurchasedStatus() == 1;
    }

    public static String e(SleepAudioSeries sleepAudioSeries, long j) {
        StringBuilder sb = new StringBuilder();
        String b = b(sleepAudioSeries.getHeatCount());
        if (!TextUtils.isEmpty(b)) {
            sb.append(b);
            sb.append("  |  ");
        }
        int seriesAudioSize = sleepAudioSeries.getSeriesAudioSize();
        sb.append(nsf.a(R.plurals._2130903458_res_0x7f0301a2, seriesAudioSize, UnitUtil.e(seriesAudioSize, 1, 0)));
        sb.append("  |  ");
        sb.append(BaseApplication.getContext().getResources().getString(R$string.IDS_sleep_decompression_min, Long.valueOf(j / 60)));
        return sb.toString();
    }

    public static String a(long j) {
        if (j == 0) {
            return "";
        }
        long j2 = j / 60;
        return j2 == 0 ? "" : BaseApplication.getContext().getResources().getString(R$string.IDS_hw_show_sport_timeline_mins_string, Long.valueOf(j2));
    }

    public static final String b(int i) {
        return i <= 0 ? "" : nrq.c(i);
    }

    public static String b(long j, PlayRecordInfo playRecordInfo) {
        if (playRecordInfo == null) {
            LogUtil.a("CourseTagUtils", "playRecord is null");
            return "";
        }
        if (playRecordInfo.getCompleteCount() > 0) {
            LogUtil.a("CourseTagUtils", "completeCount:", Integer.valueOf(playRecordInfo.getCompleteCount()));
            return BaseApplication.getContext().getResources().getString(R$string.IDS_sleep_decompression_played);
        }
        long duration = playRecordInfo.getDuration();
        if (duration == 0) {
            LogUtil.a("CourseTagUtils", "duration is 0");
            return "";
        }
        if (duration >= j) {
            return BaseApplication.getContext().getResources().getString(R$string.IDS_sleep_decompression_played);
        }
        long j2 = (long) (((duration * 1.0f) / j) * 100.0f);
        if (j2 == 0) {
            j2 = 1;
        }
        LogUtil.a("CourseTagUtils", "percent: ", Long.valueOf(j2));
        if (Long.compare(j2, 100L) >= 0) {
            return BaseApplication.getContext().getResources().getString(R$string.IDS_sleep_decompression_played);
        }
        return BaseApplication.getContext().getResources().getString(R$string.IDS_sleep_decompression_playing) + " " + Long.toString(j2) + "%";
    }

    private static boolean d(long j, PlayRecordInfo playRecordInfo) {
        if (playRecordInfo == null) {
            LogUtil.a("CourseTagUtils", "playRecord is null");
            return false;
        }
        if (playRecordInfo.getCompleteCount() > 0) {
            LogUtil.a("CourseTagUtils", "completeCount:", Integer.valueOf(playRecordInfo.getCompleteCount()));
            return true;
        }
        long duration = playRecordInfo.getDuration();
        if (duration != 0) {
            return duration >= j || Long.compare((long) (((((float) duration) * 1.0f) / ((float) j)) * 100.0f), 100L) >= 0;
        }
        LogUtil.a("CourseTagUtils", "duration is 0");
        return false;
    }

    public static long a(SleepAudioPackage sleepAudioPackage) {
        SleepAudioSeries sleepAudioSeries = sleepAudioPackage.getSleepAudioSeries();
        if (sleepAudioSeries == null) {
            LogUtil.a("CourseTagUtils", "series is null");
            return 0L;
        }
        if (sleepAudioSeries.getDisplayStyle() == 1) {
            return b(sleepAudioPackage.getSleepAudioInfoList());
        }
        return d(sleepAudioPackage.getSleepAudioGroupList());
    }

    private static long b(List<SleepAudioInfo> list) {
        int size = list.size();
        long j = 0;
        for (int i = 0; i < size; i++) {
            SleepAudioInfo sleepAudioInfo = list.get(i);
            if (sleepAudioInfo != null) {
                j += sleepAudioInfo.getAudioDuration();
            }
        }
        return j;
    }

    private static long d(List<SleepAudioGroup> list) {
        int size = list.size();
        long j = 0;
        for (int i = 0; i < size; i++) {
            List<SleepAudioInfo> sleepAudioInfoList = list.get(i).getSleepAudioInfoList();
            if (koq.b(sleepAudioInfoList)) {
                LogUtil.b("CourseTagUtils", "sleepAudioInfoList is null");
            } else {
                int size2 = sleepAudioInfoList.size();
                for (int i2 = 0; i2 < size2; i2++) {
                    SleepAudioInfo sleepAudioInfo = sleepAudioInfoList.get(i2);
                    if (sleepAudioInfo != null) {
                        j += sleepAudioInfo.getAudioDuration();
                    }
                }
            }
        }
        return j;
    }

    public static String b(SleepAudioPackage sleepAudioPackage, boolean z, int i) {
        SleepAudioSeries sleepAudioSeries;
        if (sleepAudioPackage == null || (sleepAudioSeries = sleepAudioPackage.getSleepAudioSeries()) == null) {
            return "";
        }
        int isVip = sleepAudioSeries.getIsVip();
        if (isVip == 0) {
            return e(b(sleepAudioPackage), a(sleepAudioPackage), false);
        }
        return isVip == 2 ? i == 1 ? e(b(sleepAudioPackage), a(sleepAudioPackage), true) : "" : (isVip == 1 && z) ? e(b(sleepAudioPackage), a(sleepAudioPackage), false) : "";
    }

    private static long b(SleepAudioPackage sleepAudioPackage) {
        SleepAudioSeries sleepAudioSeries = sleepAudioPackage.getSleepAudioSeries();
        if (sleepAudioSeries == null) {
            LogUtil.a("CourseTagUtils", "series is null");
            return 0L;
        }
        if (sleepAudioSeries.getDisplayStyle() == 1) {
            return i(sleepAudioPackage.getSleepAudioInfoList());
        }
        return e(sleepAudioPackage.getSleepAudioGroupList());
    }

    private static long i(List<SleepAudioInfo> list) {
        PlayRecordInfo playRecord;
        long duration;
        long j = 0;
        for (SleepAudioInfo sleepAudioInfo : list) {
            if (sleepAudioInfo != null && (playRecord = sleepAudioInfo.getPlayRecord()) != null) {
                if (playRecord.getCompleteCount() > 0) {
                    duration = sleepAudioInfo.getAudioDuration();
                } else {
                    duration = playRecord.getDuration();
                }
                j += duration;
            }
        }
        return j;
    }

    private static long e(List<SleepAudioGroup> list) {
        PlayRecordInfo playRecord;
        long duration;
        long j = 0;
        for (SleepAudioGroup sleepAudioGroup : list) {
            if (sleepAudioGroup != null) {
                for (SleepAudioInfo sleepAudioInfo : sleepAudioGroup.getSleepAudioInfoList()) {
                    if (sleepAudioInfo != null && (playRecord = sleepAudioInfo.getPlayRecord()) != null) {
                        if (playRecord.getCompleteCount() > 0) {
                            duration = sleepAudioInfo.getAudioDuration();
                        } else {
                            duration = playRecord.getDuration();
                        }
                        j += duration;
                    }
                }
            }
        }
        return j;
    }

    public static String c(List<SleepAudioInfo> list) {
        long duration;
        int size = list.size();
        long j = 0;
        long j2 = 0;
        for (int i = 0; i < size; i++) {
            j2 += list.get(i).getAudioDuration();
            if (list.get(i).getPlayRecord() != null) {
                if (list.get(i).getPlayRecord().getCompleteCount() > 0) {
                    duration = list.get(i).getAudioDuration();
                } else {
                    duration = list.get(i).getPlayRecord().getDuration();
                }
                j += duration;
            }
        }
        return e(j, j2, true);
    }

    public static String a(List<SleepAudioGroup> list) {
        long duration;
        int size = list.size();
        long j = 0;
        long j2 = 0;
        for (int i = 0; i < size; i++) {
            List<SleepAudioInfo> sleepAudioInfoList = list.get(i).getSleepAudioInfoList();
            if (koq.b(sleepAudioInfoList)) {
                LogUtil.b("CourseTagUtils", "sleepAudioInfoList is null");
            } else {
                int size2 = sleepAudioInfoList.size();
                for (int i2 = 0; i2 < size2; i2++) {
                    j2 += sleepAudioInfoList.get(i2).getAudioDuration();
                    if (sleepAudioInfoList.get(i2).getPlayRecord() != null) {
                        if (sleepAudioInfoList.get(i2).getPlayRecord().getCompleteCount() > 0) {
                            duration = sleepAudioInfoList.get(i2).getAudioDuration();
                        } else {
                            duration = sleepAudioInfoList.get(i2).getPlayRecord().getDuration();
                        }
                        j += duration;
                    }
                }
            }
        }
        return e(j, j2, true);
    }

    public static String e(long j, long j2, boolean z) {
        if (j == 0) {
            return z ? BaseApplication.getContext().getResources().getString(R$string.IDS_sleep_decompression_not_played) : "";
        }
        if (j >= j2) {
            return BaseApplication.getContext().getResources().getString(R$string.IDS_sleep_decompression_played);
        }
        long j3 = (long) (((j * 1.0f) / j2) * 100.0f);
        if (j3 == 0) {
            j3 = 1;
        }
        if (j3 == 100) {
            return BaseApplication.getContext().getResources().getString(R$string.IDS_sleep_decompression_played);
        }
        return BaseApplication.getContext().getResources().getString(R$string.IDS_sleep_decompression_playing) + " " + Long.toString(j3) + "%";
    }

    public static void b(HealthTextView healthTextView, String str) {
        if (str == null || str.length() == 0) {
            LogUtil.b("CourseTagUtils", "text is null");
            return;
        }
        if (str.equals(BaseApplication.getContext().getResources().getString(R$string.IDS_sleep_decompression_free))) {
            if (nrt.a(BaseApplication.getContext())) {
                healthTextView.setTextColor(BaseApplication.getContext().getResources().getColor(R.color._2131296998_res_0x7f0902e6));
                healthTextView.setBackground(BaseApplication.getContext().getResources().getDrawable(R.drawable.pressure_price_tag_background_dark));
            } else {
                healthTextView.setTextColor(BaseApplication.getContext().getResources().getColor(R.color._2131298158_res_0x7f09076e));
                healthTextView.setBackground(BaseApplication.getContext().getResources().getDrawable(R.drawable.pressure_price_tag_background));
            }
        } else if (str.equals(BaseApplication.getContext().getResources().getString(R$string.IDS_sleep_decompression_purchased)) || str.equals(BaseApplication.getContext().getResources().getString(R$string.IDS_sug_series_course_pay))) {
            healthTextView.setTextColor(BaseApplication.getContext().getResources().getColor(R.color._2131296805_res_0x7f090225));
            healthTextView.setBackground(BaseApplication.getContext().getResources().getDrawable(R.drawable.sleep_course_purchased_tag_background));
        } else if (str.equals(BaseApplication.getContext().getResources().getString(R$string.IDS_vip))) {
            healthTextView.setTextColor(BaseApplication.getContext().getResources().getColor(R.color._2131299094_res_0x7f090b16));
            healthTextView.setBackground(BaseApplication.getContext().getResources().getDrawable(R.drawable.sleep_course_membership_tag_background));
        } else {
            healthTextView.setTextColor(BaseApplication.getContext().getResources().getColor(R.color._2131299098_res_0x7f090b1a));
            healthTextView.setBackground(BaseApplication.getContext().getResources().getDrawable(R.drawable.sleep_course_video_tag_background));
        }
        int c = nsn.c(BaseApplication.getContext(), 4.0f);
        int c2 = nsn.c(BaseApplication.getContext(), 1.0f);
        healthTextView.setText(str);
        healthTextView.setTextSize(1, 10.0f);
        healthTextView.setPadding(c, c2, c, c2);
        healthTextView.setVisibility(0);
    }

    public static void a(HealthTextView healthTextView, String str) {
        if (str == null || str.length() == 0) {
            LogUtil.b("CourseTagUtils", "text is null");
            return;
        }
        healthTextView.setTextColor(BaseApplication.getContext().getResources().getColor(R.color._2131299238_res_0x7f090ba6));
        healthTextView.setBackground(BaseApplication.getContext().getResources().getDrawable(R.drawable.sleep_course_white_tag_background));
        int c = nsn.c(BaseApplication.getContext(), 4.0f);
        int c2 = nsn.c(BaseApplication.getContext(), 1.0f);
        healthTextView.setText(str);
        healthTextView.setTextSize(1, 10.0f);
        healthTextView.setPadding(c, c2, c, c2);
        healthTextView.setVisibility(0);
    }

    public static boolean b(SleepAudioInfo sleepAudioInfo, SleepAudioSeries sleepAudioSeries) {
        int isVip = sleepAudioInfo.getIsVip();
        return (isVip == 0 || isVip != 2 || a(sleepAudioSeries) || a(sleepAudioInfo)) ? false : true;
    }

    public static String a(String str) {
        return str.replaceAll(" ", "");
    }

    public static void c(SleepAudioPackage sleepAudioPackage, long j, int i, int i2) {
        if (sleepAudioPackage == null) {
            return;
        }
        HashMap hashMap = new HashMap();
        SleepAudioSeries sleepAudioSeries = sleepAudioPackage.getSleepAudioSeries();
        if (sleepAudioSeries != null) {
            hashMap.put("click", 1);
            hashMap.put("resourceType", Integer.valueOf(sleepAudioSeries.getAudioType()));
            hashMap.put("resourceMode", Integer.valueOf(sleepAudioSeries.getIsVip()));
            hashMap.put("ResourceName", sleepAudioSeries.getName());
            hashMap.put("id", Integer.valueOf(sleepAudioSeries.getId()));
            hashMap.put("payType", Integer.valueOf(sleepAudioSeries.getPurchasedStatus()));
            hashMap.put("mixedMode", 1);
            hashMap.put("event", Integer.valueOf(i2));
            hashMap.put("finishRate", Integer.valueOf(a(sleepAudioSeries.getSeriesAudioSize(), c(sleepAudioPackage))));
        }
        hashMap.put("duration", Long.valueOf((SystemClock.elapsedRealtime() - j) / 1000));
        hashMap.put("from", Integer.valueOf(i));
        LogUtil.a("CourseTagUtils", "biEventEnterSeriesDetail, map: ", hashMap.toString());
        ixx.d().d(BaseApplication.getContext(), AnalyticsValue.SERIES_ENTER_2030099.value(), hashMap, 0);
    }

    private static int c(SleepAudioPackage sleepAudioPackage) {
        int i = 0;
        if (sleepAudioPackage.getSleepAudioSeries().getDisplayStyle() == 1) {
            List<SleepAudioInfo> sleepAudioInfoList = sleepAudioPackage.getSleepAudioInfoList();
            if (koq.b(sleepAudioInfoList)) {
                return 0;
            }
            for (SleepAudioInfo sleepAudioInfo : sleepAudioInfoList) {
                if (sleepAudioInfo != null && d(sleepAudioInfo.getAudioDuration(), sleepAudioInfo.getPlayRecord())) {
                    i++;
                }
            }
        } else {
            List<SleepAudioGroup> sleepAudioGroupList = sleepAudioPackage.getSleepAudioGroupList();
            if (koq.b(sleepAudioGroupList)) {
                return 0;
            }
            for (SleepAudioGroup sleepAudioGroup : sleepAudioGroupList) {
                if (sleepAudioGroup != null) {
                    List<SleepAudioInfo> sleepAudioInfoList2 = sleepAudioGroup.getSleepAudioInfoList();
                    if (!koq.b(sleepAudioInfoList2)) {
                        for (SleepAudioInfo sleepAudioInfo2 : sleepAudioInfoList2) {
                            if (sleepAudioInfo2 != null && d(sleepAudioInfo2.getAudioDuration(), sleepAudioInfo2.getPlayRecord())) {
                                i++;
                            }
                        }
                    }
                }
            }
        }
        return i;
    }

    public static void b(SleepAudioPackage sleepAudioPackage, boolean z, boolean z2, String str, int i) {
        if (sleepAudioPackage == null) {
            return;
        }
        HashMap hashMap = new HashMap();
        SleepAudioSeries sleepAudioSeries = sleepAudioPackage.getSleepAudioSeries();
        if (sleepAudioSeries != null) {
            hashMap.put("click", 1);
            hashMap.put("tabName", a(z, z2, str, i));
            hashMap.put("resourceType", Integer.valueOf(sleepAudioSeries.getAudioType()));
            hashMap.put("resourceMode", Integer.valueOf(sleepAudioSeries.getIsVip()));
            hashMap.put("ResourceName", sleepAudioSeries.getName());
            hashMap.put("id", Integer.valueOf(sleepAudioSeries.getId()));
        }
        LogUtil.a("CourseTagUtils", "biEventChangeTab, map: ", hashMap.toString());
        ixx.d().d(BaseApplication.getContext(), AnalyticsValue.SERIES_CHANGE_TAB_2030101.value(), hashMap, 0);
    }

    public static void b(Map<String, Object> map) {
        LogUtil.a("CourseTagUtils", "biEventRecommendShow, map: ", map.toString());
        ixx.d().d(BaseApplication.getContext(), AnalyticsValue.MARKETING_RESOURCE.value(), map, 0);
    }

    private static String a(boolean z, boolean z2, String str, int i) {
        if (z && z2) {
            if (i == 0) {
                return BaseApplication.getContext().getResources().getString(com.huawei.ui.commonui.R$string.IDS_sleep_decompression_tab_title_summary);
            }
            if (i == 1) {
                return BaseApplication.getContext().getResources().getString(com.huawei.ui.commonui.R$string.IDS_sleep_decompression_tab_title_chapter);
            }
            if (i == 2) {
                return str;
            }
        }
        if (z) {
            if (i == 0) {
                return BaseApplication.getContext().getResources().getString(com.huawei.ui.commonui.R$string.IDS_sleep_decompression_tab_title_summary);
            }
            if (i == 1) {
                return BaseApplication.getContext().getResources().getString(com.huawei.ui.commonui.R$string.IDS_sleep_decompression_tab_title_chapter);
            }
        }
        if (z2) {
            if (i == 0) {
                return BaseApplication.getContext().getResources().getString(com.huawei.ui.commonui.R$string.IDS_sleep_decompression_tab_title_chapter);
            }
            if (i == 1) {
                return str;
            }
        }
        return BaseApplication.getContext().getResources().getString(com.huawei.ui.commonui.R$string.IDS_sleep_decompression_tab_title_chapter);
    }
}
