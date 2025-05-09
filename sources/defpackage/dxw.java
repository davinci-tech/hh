package defpackage;

import android.content.Context;
import android.os.Looper;
import android.text.TextUtils;
import com.huawei.haf.application.BaseApplication;
import com.huawei.health.userlabelmgr.model.UpdateUserLabel;
import com.huawei.hms.support.feature.result.CommonConstant;
import com.huawei.hwlogsmodel.LogUtil;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/* loaded from: classes3.dex */
public class dxw {

    /* renamed from: a, reason: collision with root package name */
    private static Context f11901a;
    private List<String> f;
    private List<UpdateUserLabel> g;
    private volatile long h;
    private List<String> j;
    private static final Object e = new Object();
    private static final Object d = new Object();
    private static final Set<String> b = new HashSet<String>() { // from class: dxw.1
        {
            add("weight");
            add("health_sport_weight_size");
            add("health_sport_sleep_care");
            add("health_sport_sleep_quality");
            add("health_sport_weight_care");
            add("health_sport_heart_rate");
            add("health_sport_blood_oxygen");
            add("health_blood_sugar_group_up");
            add("health_blood_pressure_group_up");
            add("health_sport_health_mode_frequency");
            add("health_sport_health_mode_user");
            add("health_sport_health_mode_active");
        }
    };
    private static final Set<String> c = new HashSet<String>() { // from class: dxw.5
        {
            add("health_sleep_rhythm_type");
            add("health_monthly_sleep_problem");
            add("health_daily_sleep_problem");
            add("health_daily_sleep_target_problem");
        }
    };

    static class e {
        public static final dxw c = new dxw();
    }

    private dxw() {
        this.g = new ArrayList(10);
        this.j = new ArrayList(10);
        this.f = new ArrayList(10);
        f11901a = BaseApplication.e();
        this.j.add(CommonConstant.KEY_GENDER);
        this.j.add("weight");
        this.j.add("health_sport_weight_size");
        this.j.add("age");
        this.j.add("health_blood_pressure_group_up");
        this.j.add("health_sport_grade_up");
        this.j.add("health_sport_freq_up");
        this.j.add("health_sport_bike_up");
        this.j.add("health_blood_sugar_group_up");
        this.j.add("health_sport_last_ride");
        this.j.add("health_sport_strength_up");
        this.j.add("health_sport_last_run");
        this.j.add("health_sport_user_active");
        this.j.add("health_sport_last_active");
        this.j.add("health_sport_last_login");
        this.j.add("health_sport_ride_up");
        this.j.add("health_sport_fitness_newuser");
        this.j.add("health_sport_fitness_silence");
        this.j.add("health_sport_fitness_frequency");
        this.j.add("health_sport_fitness_intensity");
        this.j.add("health_sport_sleep_care");
        this.j.add("health_sport_sleep_quality");
        this.j.add("health_sport_weight_care");
        this.j.add("health_sport_heart_rate");
        this.j.add("health_sport_blood_oxygen");
        this.j.add("health_sport_user_app");
        this.j.add("health_sport_last_fitness");
        this.j.add("health_sport_device_type");
        this.j.add("health_sport_device_up");
        this.j.add("health_sport_device_time");
        this.j.add("health_sport_max_vo2");
        this.j.add("health_sport_stress_average");
        this.j.add("health_sport_sleep_average");
        this.j.add("health_sport_health_mode_user");
        this.j.add("health_sport_average_pace");
        this.j.add("health_sport_step_average");
        this.j.add("health_sport_exercise_intensity_average");
        this.j.add("health_sport_walk_weekday");
        this.j.add("health_sport_run_weekday");
        this.j.add("health_sport_ride_weekday");
        this.j.add("health_sport_member_flag");
        this.j.add("health_sport_member_stage");
        this.j.add("health_sport_member_type");
        this.j.addAll(c);
        a();
        d();
    }

    private void d() {
        this.f.add("health_sport_device_type_smart_wear");
        this.f.add("health_sport_device_up_smart_wear");
        this.f.add("health_sport_device_time_smart_wear");
        this.f.add("health_sport_device_type_third_party");
        this.f.add("health_sport_device_up_third_party");
        this.f.add("health_sport_device_time_third_party");
        this.f.add("health_sport_is_new_user");
    }

    private void a() {
        this.j.add("pengine_daily_work_hour");
        this.j.add("pengine_sport_frequency");
        this.j.add("pengine_sport_type_preference");
        this.j.add("pengine_work_day");
        this.j.add("pengine_commuting_mode");
        this.j.add("pengine_leave_home_time");
        this.j.add("pengine_arrive_home_time");
        this.j.add("pengine_arrive_company_time");
        this.j.add("pengine_leave_company_time");
        this.j.add("pengine_commuting_duration");
    }

    public static dxw a(Context context) {
        LogUtil.a("BIUserLabelMgr", "BIUserLabelHelper getInstance");
        if (context != null) {
            f11901a = context.getApplicationContext();
        }
        return e.c;
    }

    public void b(UpdateUserLabel updateUserLabel) {
        if (updateUserLabel == null) {
            LogUtil.h("BIUserLabelMgr", "registerCallback null");
            return;
        }
        synchronized (d) {
            if (!this.g.contains(updateUserLabel)) {
                this.g.add(updateUserLabel);
            }
        }
    }

    public void e(UpdateUserLabel updateUserLabel) {
        if (updateUserLabel == null) {
            LogUtil.h("BIUserLabelMgr", "unRegisterCallback null");
            return;
        }
        synchronized (d) {
            this.g.remove(updateUserLabel);
        }
    }

    public void b(String str, String str2, String str3) {
        LogUtil.a("BIUserLabelMgr", "initLabel() in");
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str3) || str2 == null) {
            return;
        }
        dxh c2 = dxm.b().c(str, str3);
        if (c2 != null) {
            c2.b(str2);
            LogUtil.a("BIUserLabelMgr", "initLabel updateResult = ", Long.valueOf(dxm.b().a(c2)), " key = ", str);
        } else {
            c2 = new dxh();
            c2.d(str);
            c2.b(str2);
            c2.c(str3);
            LogUtil.a("BIUserLabelMgr", "initLabel insertResult = ", Long.valueOf(dxm.b().b(c2)), " key = ", str);
        }
        if (this.f.contains(str)) {
            LogUtil.c("BIUserLabelMgr", "initLabel update device label, label = ", str);
            c(c2);
        }
    }

    public List<String> a(String str) {
        if (Looper.getMainLooper() == Looper.myLooper()) {
            LogUtil.h("BIUserLabelMgr", "this is mainthread, getAllLabels error.");
            throw new IllegalThreadStateException("getAllLabels not allow InMainThread, please use this in new thread!");
        }
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("BIUserLabelMgr", "getAllLabels, huid is null");
            return new ArrayList(0);
        }
        List<String> d2 = dyd.d(f11901a).d(str);
        List<String> a2 = dxz.d().a(this.j);
        List<String> c2 = c(this.j, str);
        c2.addAll(d2);
        c2.addAll(a2);
        return c2;
    }

    /* JADX WARN: Code restructure failed: missing block: B:17:0x005d, code lost:
    
        if ((r4 - java.lang.Long.parseLong(r6.e())) > 86400000) goto L19;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.util.List<java.lang.String> c(java.util.List<java.lang.String> r12, java.lang.String r13) {
        /*
            r11 = this;
            boolean r0 = defpackage.koq.b(r12)
            java.lang.String r1 = "BIUserLabelMgr"
            if (r0 != 0) goto Lbe
            boolean r0 = android.text.TextUtils.isEmpty(r13)
            if (r0 == 0) goto L10
            goto Lbe
        L10:
            java.util.List r0 = r11.e(r12)
            java.util.ArrayList r2 = new java.util.ArrayList
            r3 = 10
            r2.<init>(r3)
            dxm r3 = defpackage.dxm.b()
            java.lang.String r4 = "getLabels enter getLabel"
            java.lang.Object[] r4 = new java.lang.Object[]{r4}
            com.huawei.hwlogsmodel.LogUtil.a(r1, r4)
            long r4 = java.lang.System.currentTimeMillis()
            java.util.List r12 = r11.c(r12)
            java.util.List r12 = r3.e(r12, r13)
            boolean r13 = defpackage.koq.b(r12)
            r3 = 1
            if (r13 != 0) goto L80
            java.util.Iterator r12 = r12.iterator()
            r13 = 0
        L40:
            boolean r6 = r12.hasNext()
            if (r6 == 0) goto L7f
            java.lang.Object r6 = r12.next()
            dxh r6 = (defpackage.dxh) r6
            if (r6 == 0) goto L6a
            java.lang.String r7 = r6.e()     // Catch: java.lang.NumberFormatException -> L60
            long r7 = java.lang.Long.parseLong(r7)     // Catch: java.lang.NumberFormatException -> L60
            long r7 = r4 - r7
            r9 = 86400000(0x5265c00, double:4.2687272E-316)
            int r7 = (r7 > r9 ? 1 : (r7 == r9 ? 0 : -1))
            if (r7 <= 0) goto L6b
            goto L6a
        L60:
            java.lang.String r6 = "getLabels, getTimeStamp Exception."
            java.lang.Object[] r6 = new java.lang.Object[]{r6}
            com.huawei.hwlogsmodel.LogUtil.b(r1, r6)
            goto L40
        L6a:
            r13 = r3
        L6b:
            if (r6 == 0) goto L40
            java.lang.String r7 = r6.c()
            boolean r7 = android.text.TextUtils.isEmpty(r7)
            if (r7 != 0) goto L40
            java.lang.String r6 = r6.c()
            r2.add(r6)
            goto L40
        L7f:
            r3 = r13
        L80:
            boolean r12 = defpackage.koq.b(r0)
            if (r12 != 0) goto L97
            java.lang.String r12 = "valueListMember is not null. valueListMember = "
            java.lang.String r13 = r0.toString()
            java.lang.Object[] r12 = new java.lang.Object[]{r12, r13}
            com.huawei.hwlogsmodel.LogUtil.a(r1, r12)
            r2.addAll(r0)
        L97:
            boolean r12 = r11.a(r3, r4)
            java.lang.String r4 = "getLabels isUpdate = "
            java.lang.Boolean r5 = java.lang.Boolean.valueOf(r3)
            java.lang.String r6 = " isUpdateFinally = "
            java.lang.Boolean r7 = java.lang.Boolean.valueOf(r12)
            java.lang.String r8 = " size = "
            int r13 = r2.size()
            java.lang.Integer r9 = java.lang.Integer.valueOf(r13)
            java.lang.Object[] r13 = new java.lang.Object[]{r4, r5, r6, r7, r8, r9}
            com.huawei.hwlogsmodel.LogUtil.c(r1, r13)
            if (r12 == 0) goto Lbd
            r11.c()
        Lbd:
            return r2
        Lbe:
            java.lang.String r12 = "getLabels null"
            java.lang.Object[] r12 = new java.lang.Object[]{r12}
            com.huawei.hwlogsmodel.LogUtil.h(r1, r12)
            java.util.ArrayList r12 = new java.util.ArrayList
            r12.<init>()
            return r12
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.dxw.c(java.util.List, java.lang.String):java.util.List");
    }

    private List<String> c(List<String> list) {
        ArrayList arrayList = new ArrayList();
        for (String str : list) {
            if (str != null) {
                if (e(str)) {
                    LogUtil.a("BIUserLabelMgr", "labelKey is vip flag. labelKey: ", str);
                } else {
                    arrayList.add(str);
                }
            }
        }
        return arrayList;
    }

    private List<String> e(List<String> list) {
        return a(list) ? dxz.d().a(list) : new ArrayList();
    }

    private boolean e(String str) {
        return TextUtils.equals(str, "health_sport_member_flag") || TextUtils.equals(str, "health_sport_member_stage") || TextUtils.equals(str, "health_sport_member_type");
    }

    private boolean a(List<String> list) {
        return list.contains("health_sport_member_flag") || list.contains("health_sport_member_stage") || list.contains("health_sport_member_type");
    }

    private void c() {
        UpdateUserLabel[] updateUserLabelArr;
        LogUtil.a("BIUserLabelMgr", "updateAllLabels enter");
        synchronized (d) {
            List<UpdateUserLabel> list = this.g;
            updateUserLabelArr = (UpdateUserLabel[]) list.toArray(new UpdateUserLabel[list.size()]);
        }
        for (UpdateUserLabel updateUserLabel : updateUserLabelArr) {
            if (updateUserLabel != null) {
                updateUserLabel.onUpdate();
            }
        }
    }

    private boolean a(boolean z, long j) {
        if (!z || j - this.h <= 180000) {
            return false;
        }
        synchronized (e) {
            if (j - this.h <= 180000) {
                return false;
            }
            this.h = j;
            return true;
        }
    }

    private void c(dxh dxhVar) {
        String d2 = dxhVar.d();
        if ("health_sport_device_type_smart_wear".equals(d2) || "health_sport_device_type_third_party".equals(d2)) {
            b(dxhVar);
        } else if ("health_sport_device_up_smart_wear".equals(d2) || "health_sport_device_up_third_party".equals(d2) || "health_sport_device_time_smart_wear".equals(d2) || "health_sport_device_time_third_party".equals(d2)) {
            a(dxhVar);
        }
    }

    private void b(dxh dxhVar) {
        String d2 = dxhVar.d();
        String a2 = dxhVar.a();
        if ("health_sport_device_type_smart_wear".equals(d2)) {
            b(dxhVar.c(), dxm.b().e("health_sport_device_type_third_party", a2), dxhVar);
        } else {
            b(dxm.b().e("health_sport_device_type_smart_wear", a2), dxhVar.c(), dxhVar);
        }
    }

    private void b(String str, String str2, dxh dxhVar) {
        if ("SportDeviceModel_1".equals(str2) || "SportDeviceModel_1".equals(str)) {
            dxhVar.d("health_sport_device_type");
            dxhVar.b("SportDeviceModel_1");
            dxm.b().e(dxhVar);
        } else {
            dxhVar.d("health_sport_device_type");
            dxhVar.b("SportDeviceModel_0");
            dxm.b().e(dxhVar);
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    private void a(dxh dxhVar) {
        char c2;
        String a2 = dxhVar.a();
        String c3 = dxhVar.c();
        String d2 = dxhVar.d();
        d2.hashCode();
        switch (d2.hashCode()) {
            case -2126036514:
                if (d2.equals("health_sport_device_up_smart_wear")) {
                    c2 = 0;
                    break;
                }
                c2 = 65535;
                break;
            case -1303235593:
                if (d2.equals("health_sport_device_time_third_party")) {
                    c2 = 1;
                    break;
                }
                c2 = 65535;
                break;
            case -1169261268:
                if (d2.equals("health_sport_device_time_smart_wear")) {
                    c2 = 2;
                    break;
                }
                c2 = 65535;
                break;
            case -898497147:
                if (d2.equals("health_sport_device_up_third_party")) {
                    c2 = 3;
                    break;
                }
                c2 = 65535;
                break;
            default:
                c2 = 65535;
                break;
        }
        if (c2 == 0) {
            a(c3, dxm.b().e("health_sport_device_up_third_party", a2), "health_sport_device_up", a2);
            return;
        }
        if (c2 == 1) {
            a(dxm.b().e("health_sport_device_time_smart_wear", a2), c3, "health_sport_device_time", a2);
            return;
        }
        if (c2 == 2) {
            a(c3, dxm.b().e("health_sport_device_time_third_party", a2), "health_sport_device_time", a2);
        } else if (c2 == 3) {
            a(dxm.b().e("health_sport_device_up_smart_wear", a2), c3, "health_sport_device_up", a2);
        } else {
            LogUtil.a("BIUserLabelMgr", "updateDevice default branch");
        }
    }

    private void a(String str, String str2, String str3, String str4) {
        LogUtil.c("BIUserLabelMgr", "compareTwoDeviceLabel label = ", str3);
        StringBuilder sb = new StringBuilder(16);
        if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2)) {
            sb.append(str);
            sb.append(",");
            sb.append(str2);
            b(str3, sb.toString(), str4);
            return;
        }
        if (TextUtils.isEmpty(str) && TextUtils.isEmpty(str2)) {
            b(str3, sb.toString(), str4);
            return;
        }
        if (TextUtils.isEmpty(str)) {
            sb.append(str2);
            b(str3, sb.toString(), str4);
        } else if (TextUtils.isEmpty(str2)) {
            sb.append(str);
            b(str3, sb.toString(), str4);
        }
    }

    public boolean b(List<String> list) {
        if (koq.b(list)) {
            LogUtil.h("BIUserLabelMgr", "labelKeys is null");
            return false;
        }
        Iterator<String> it = list.iterator();
        while (it.hasNext()) {
            if (b.contains(it.next())) {
                return true;
            }
        }
        return false;
    }

    public boolean d(List<String> list) {
        if (koq.b(list)) {
            LogUtil.h("BIUserLabelMgr", "labelKeys is null");
            return false;
        }
        for (String str : list) {
            if (!b.contains(str) && (this.j.contains(str) || this.f.contains(str))) {
                return true;
            }
        }
        return false;
    }
}
