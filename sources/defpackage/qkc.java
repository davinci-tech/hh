package defpackage;

import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import com.huawei.health.R;
import com.huawei.hihealth.HiDataReadOption;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.utils.DateFormatUtil;
import com.huawei.hwfoundationmodel.trackmodel.MotionPathSimplify;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.main.R$string;
import health.compact.a.UnitUtil;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

/* loaded from: classes6.dex */
public class qkc {
    public static String b = "sleep_notification";
    public static String c = "sport_notification";

    private static long d(long j, float f, long j2, float f2, float f3) {
        return ((long) (((f - f3) * (j2 - j)) / (f - f2))) + j;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int e(int i) {
        return (i == 262 || i == 281 || i == 264 || i == 265) ? 0 : 1;
    }

    public static void e(long j, long j2) {
        LogUtil.a("BloodSugarEventHandler", "handleSleepBloodSugarEvent");
        deb.b("sync_sleep_time_key", j + "," + j2);
    }

    public static void b() {
        LogUtil.a("BloodSugarEventHandler", "recordSportBloodSugarEvent");
        kor.a().b(new b());
    }

    public static void c() {
        LogUtil.a("BloodSugarEventHandler", "handleSleepBloodSugarEvent");
        String d2 = d("sync_sleep_time_key");
        if (TextUtils.isEmpty(d2)) {
            LogUtil.a("BloodSugarEventHandler", "timeString is null");
            return;
        }
        String[] split = d2.split(",");
        if (split.length < 2) {
            LogUtil.a("BloodSugarEventHandler", "size to small");
            return;
        }
        String str = split[0];
        String str2 = split[1];
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            LogUtil.a("BloodSugarEventHandler", "two time is null");
            return;
        }
        try {
            e(Long.parseLong(str), Long.parseLong(str2), 0, -1);
        } catch (NumberFormatException unused) {
            LogUtil.b("BloodSugarEventHandler", "String to long is wrong");
        }
    }

    public static void a() {
        LogUtil.a("BloodSugarEventHandler", "handleSportBloodSugarEvent");
        String d2 = d("sync_sport_time_key");
        if (TextUtils.isEmpty(d2)) {
            LogUtil.a("BloodSugarEventHandler", "timeString is null");
            return;
        }
        String[] split = d2.split(",");
        if (split.length < 3) {
            LogUtil.a("BloodSugarEventHandler", "size to small");
            return;
        }
        String str = split[0];
        String str2 = split[1];
        String str3 = split[2];
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2) || TextUtils.isEmpty(str3)) {
            LogUtil.a("BloodSugarEventHandler", "two time is null");
            return;
        }
        try {
            e(Long.parseLong(str), Long.parseLong(str2), 1, Integer.parseInt(str3));
        } catch (NumberFormatException unused) {
            LogUtil.b("BloodSugarEventHandler", "String parse is wrong");
        }
    }

    private static String d(String str) {
        String d2 = deb.d(str);
        deb.b(str, "");
        return d2;
    }

    private static void e(long j, long j2, int i, int i2) {
        LogUtil.a("BloodSugarEventHandler", "type=", Integer.valueOf(i), ", queryBloodSugarContinueData");
        HiDataReadOption hiDataReadOption = new HiDataReadOption();
        hiDataReadOption.setStartTime(j);
        hiDataReadOption.setEndTime(j2);
        hiDataReadOption.setType(new int[]{2108});
        hiDataReadOption.setReadType(0);
        hiDataReadOption.setSortOrder(0);
        Bundle bundle = new Bundle();
        bundle.putLong("key_start_time", j);
        bundle.putLong("key_end_time", j2);
        if (i2 != -1) {
            bundle.putInt("key_sport_type", i2);
        }
        kor.a().e(BaseApplication.getContext(), hiDataReadOption, new d(i, bundle));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void c(long j, long j2, List<HiHealthData> list) {
        LogUtil.a("BloodSugarEventHandler", "handleSleepBloodSugarData");
        ArrayList arrayList = new ArrayList(4);
        float f = deb.b()[1];
        int i = 0;
        long j3 = 0;
        HiHealthData hiHealthData = null;
        HiHealthData hiHealthData2 = null;
        for (HiHealthData hiHealthData3 : list) {
            if (hiHealthData != null && hiHealthData3.getEndTime() - hiHealthData.getEndTime() > 2100000 && !arrayList.isEmpty()) {
                arrayList.add(hiHealthData);
                arrayList.add(hiHealthData);
                i++;
                j3 += c(arrayList, f);
                arrayList.clear();
            }
            if (hiHealthData3.getFloatValue() <= f) {
                if (arrayList.isEmpty()) {
                    if (hiHealthData == null) {
                        hiHealthData = hiHealthData3;
                    }
                    arrayList.add(hiHealthData);
                    arrayList.add(hiHealthData3);
                }
                hiHealthData2 = hiHealthData3;
            } else if (!arrayList.isEmpty()) {
                arrayList.add(hiHealthData);
                arrayList.add(hiHealthData3);
                i++;
                j3 += c(arrayList, f);
                arrayList.clear();
            }
            hiHealthData = hiHealthData3;
        }
        if (!arrayList.isEmpty()) {
            arrayList.add(hiHealthData);
            arrayList.add(hiHealthData);
            i++;
            j3 += c(arrayList, f);
        }
        b(j3, i, hiHealthData2, j, j2);
    }

    private static void b(long j, int i, HiHealthData hiHealthData, long j2, long j3) {
        if (i == 0) {
            return;
        }
        LogUtil.a("BloodSugarEventHandler", "time=", Long.valueOf(j), ", count=", Integer.valueOf(i));
        int round = Math.round(((j / 1000.0f) / 60.0f) / i);
        long endTime = hiHealthData.getEndTime();
        Resources resources = BaseApplication.getContext().getResources();
        d(endTime, resources.getString(R$string.IDS_hypoglycaemia_notify_title), resources.getString(R$string.IDS_hypoglycaemia_notify_desc, DateFormatUtil.d(j2, DateFormatUtil.DateFormatType.DATE_FORMAT_HOUR_MINUTE), DateFormatUtil.d(j3, DateFormatUtil.DateFormatType.DATE_FORMAT_HOUR_MINUTE), resources.getQuantityString(R.plurals._2130903078_res_0x7f030026, i, Integer.valueOf(i)), resources.getQuantityString(R.plurals._2130903041_res_0x7f030001, round, Integer.valueOf(round))), b);
    }

    private static long c(List<HiHealthData> list, float f) {
        if (list.size() != 4) {
            LogUtil.h("BloodSugarEventHandler", "getIntervalTime error");
            return 0L;
        }
        HiHealthData hiHealthData = list.get(0);
        HiHealthData hiHealthData2 = list.get(1);
        long d2 = d(hiHealthData.getEndTime(), hiHealthData.getFloatValue(), hiHealthData2.getEndTime(), hiHealthData2.getFloatValue(), f);
        HiHealthData hiHealthData3 = list.get(2);
        HiHealthData hiHealthData4 = list.get(3);
        return d(hiHealthData3.getEndTime(), hiHealthData3.getFloatValue(), hiHealthData4.getEndTime(), hiHealthData4.getFloatValue(), f) - d2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void e(long j, List<HiHealthData> list, int i) {
        String string;
        LogUtil.a("BloodSugarEventHandler", "handleSleepBloodSugarData");
        long j2 = 0;
        int i2 = 0;
        for (HiHealthData hiHealthData : list) {
            long endTime = hiHealthData.getEndTime();
            if (d(hiHealthData) == 1003) {
                i2++;
            }
            j2 = endTime;
        }
        String e = UnitUtil.e(new BigDecimal(i2).divide(new BigDecimal(list.size()), 2, 4).multiply(BigDecimal.valueOf(100L)).doubleValue(), 2, 0);
        LogUtil.a("BloodSugarEventHandler", "proportion : ", e);
        Resources resources = BaseApplication.getContext().getResources();
        String string2 = resources.getString(R$string.IDS_hw_show_healthdata_monitoring);
        if (i == 0) {
            string = resources.getString(R$string.IDS_blood_sugar_notification2);
        } else {
            string = resources.getString(R$string.IDS_blood_sugar_notification);
        }
        d(j2, string2, String.format(Locale.ENGLISH, string, DateFormatUtil.d(j, DateFormatUtil.DateFormatType.DATE_FORMAT_HOUR_MINUTE), e), c);
    }

    private static int d(HiHealthData hiHealthData) {
        try {
            return Integer.parseInt(qjv.a(BaseApplication.getContext(), hiHealthData.getType(), hiHealthData.getFloatValue()).get("HEALTH_BLOOD_SUGAR_LEVEL_KEY"));
        } catch (NumberFormatException unused) {
            LogUtil.h("BloodSugarEventHandler", "levelValue is String, parse to Integer error");
            return 1000;
        }
    }

    private static void d(final long j, final String str, final String str2, final String str3) {
        LogUtil.a("BloodSugarEventHandler", "sendNotification, ", str3);
        new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: qka
            @Override // java.lang.Runnable
            public final void run() {
                qkc.a(j, str3, str, str2);
            }
        });
    }

    static /* synthetic */ void a(long j, String str, String str2, String str3) {
        HashMap hashMap = new HashMap();
        hashMap.put("extra_service_id", "BloodSugarCardConstructor");
        hashMap.put("extra_page_type", String.valueOf(8));
        hashMap.put("extra_time_stamp", String.valueOf(j));
        hashMap.put("notification_type", str);
        jrn.b(BaseApplication.getContext()).d(jrn.b("healthDetail", hashMap, str2, str3), jdh.c());
    }

    static class d implements IBaseResponseCallback {
        private final Bundle d;
        private final int e;

        d(int i, Bundle bundle) {
            this.e = i;
            this.d = bundle;
        }

        @Override // com.huawei.hwbasemgr.IBaseResponseCallback
        /* renamed from: onResponse */
        public void d(int i, Object obj) {
            if (!(obj instanceof List)) {
                LogUtil.a("BloodSugarEventHandler", "type=", Integer.valueOf(this.e), ", no bloodsugar data");
                return;
            }
            List list = (List) obj;
            if (koq.b(list)) {
                LogUtil.a("BloodSugarEventHandler", "type=", Integer.valueOf(this.e), ", HiHealthData List is Empty!");
                return;
            }
            LogUtil.a("BloodSugarEventHandler", "type=", Integer.valueOf(this.e), ", handleBloodSugarData");
            int i2 = this.e;
            if (i2 == 0) {
                if (this.d.containsKey("key_start_time") && this.d.containsKey("key_end_time")) {
                    qkc.c(this.d.getLong("key_start_time"), this.d.getLong("key_end_time"), list);
                    return;
                }
                return;
            }
            if (i2 == 1) {
                if (this.d.containsKey("key_start_time") && this.d.containsKey("key_sport_type")) {
                    qkc.e(this.d.getLong("key_start_time"), list, this.d.getInt("key_sport_type"));
                    return;
                }
                return;
            }
            LogUtil.a("BloodSugarEventHandler", "not know type = ", Integer.valueOf(i2));
        }
    }

    static class b implements IBaseResponseCallback {
        private b() {
        }

        @Override // com.huawei.hwbasemgr.IBaseResponseCallback
        /* renamed from: onResponse */
        public void d(int i, Object obj) {
            if (!(obj instanceof MotionPathSimplify)) {
                LogUtil.a("BloodSugarEventHandler", "no sport data");
                return;
            }
            MotionPathSimplify motionPathSimplify = (MotionPathSimplify) obj;
            LogUtil.a("BloodSugarEventHandler", "save sport data to sp, ", motionPathSimplify.requestStartTime() + "," + motionPathSimplify.requestEndTime() + "," + qkc.e(motionPathSimplify.requestSportType()));
            deb.b("sync_sport_time_key", motionPathSimplify.requestStartTime() + "," + motionPathSimplify.requestEndTime() + "," + qkc.e(motionPathSimplify.requestSportType()));
        }
    }
}
