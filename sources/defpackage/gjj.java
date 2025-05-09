package defpackage;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.health.R;
import com.huawei.health.threeCircle.remind.model.DeviceEventData;
import com.huawei.health.threeCircle.remind.model.OnRemindHandle;
import com.huawei.health.threeCircle.remind.model.ThreeCircleRemindData;
import com.huawei.health.threeCircle.remind.receiver.SportRemindReceiver;
import com.huawei.hihealth.HiHealthClient;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.api.HiHealthNativeApi;
import com.huawei.hihealth.data.listener.HiSubscribeListener;
import com.huawei.hihealth.data.listener.HiUnSubscribeListener;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwbasemgr.ResponseCallback;
import com.huawei.hwcommonmodel.utils.DateFormatUtil;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.pluginachievement.manager.model.ParsedFieldTag;
import com.huawei.threecircle.ActiveTipStringUtils;
import com.huawei.threecircle.api.ThreeCircleApi;
import health.compact.a.CommonUtil;
import health.compact.a.Services;
import health.compact.a.SharedPerferenceUtils;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.UnitUtil;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.lang.ref.WeakReference;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes4.dex */
public class gjj implements OnRemindHandle {

    /* renamed from: a, reason: collision with root package name */
    private static final String[] f12824a = {"LotIntensity_Run", "LotIntensity_Walk"};
    private static final String[] d = {"LotCalorie_Run", "LotCalorie_Walk"};
    private Bundle b;
    private int c;
    private Map<String, String> e = new HashMap();
    private int f;
    private c g;
    private List<Integer> h;
    private int i;

    private ThreeCircleRemindData d(ThreeCircleRemindData threeCircleRemindData, ThreeCircleRemindData threeCircleRemindData2, ThreeCircleRemindData threeCircleRemindData3) {
        return threeCircleRemindData != null ? threeCircleRemindData : threeCircleRemindData2 != null ? threeCircleRemindData2 : threeCircleRemindData3;
    }

    @Override // com.huawei.health.threeCircle.remind.model.OnRemindHandle
    public void onTrigger(Map<String, Integer> map, gjr gjrVar) {
        if (gjrVar.d()) {
            b();
        }
        if (!SharedPreferenceManager.a("HiHealthService", "pullAllStatus", false)) {
            LogUtil.a("TC_TodayTgtHdlr", "data is not sync finish.");
            return;
        }
        Bundle bundle = this.b;
        if (bundle != null) {
            aNl_(bundle, map, gjrVar);
        } else {
            b(map, gjrVar);
        }
    }

    @Override // com.huawei.health.threeCircle.remind.model.OnRemindHandle
    public void onTrigger(Bundle bundle, Map<String, Integer> map, gjr gjrVar) {
        if (bundle == null) {
            return;
        }
        Bundle bundle2 = this.b;
        if (bundle2 == null || !bundle2.toString().equals(bundle.toString())) {
            this.b = bundle;
            if (!SharedPreferenceManager.a("HiHealthService", "pullAllStatus", false)) {
                LogUtil.a("TC_TodayTgtHdlr", "data is not sync finish.");
                return;
            }
            if (this.g == null) {
                this.g = new c(this);
                HiHealthNativeApi.a(BaseApplication.e()).subscribeHiHealthData(Collections.singletonList(103), this.g);
            }
            if (gjrVar.d()) {
                b();
            }
            aNl_(bundle, map, gjrVar);
        }
    }

    private void b() {
        SharedPreferenceManager.c("threeCircleSp", "intensityType", "");
        SharedPreferenceManager.c("threeCircleSp", "activeHourType", "");
        SharedPreferenceManager.c("threeCircleSp", "caloriesType", "");
    }

    private void aNl_(Bundle bundle, final Map<String, Integer> map, final gjr gjrVar) {
        final int i = bundle.getInt("activeHourType");
        final int i2 = bundle.getInt("intensityType");
        final int i3 = bundle.getInt("caloriesType");
        if (this.c == 0 || this.i == 0 || this.f == 0) {
            d(new IBaseResponseCallback() { // from class: gjn
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public final void d(int i4, Object obj) {
                    gjj.this.c(i, i2, i3, gjrVar, map, i4, obj);
                }
            });
        } else {
            b(i, i2, i3, gjrVar, map);
        }
    }

    /* synthetic */ void c(int i, int i2, int i3, gjr gjrVar, Map map, int i4, Object obj) {
        b(i, i2, i3, gjrVar, map);
    }

    @Override // com.huawei.health.threeCircle.remind.model.OnRemindHandle
    public void registerAlarm() {
        long timeInMillis = gjz.b(Calendar.getInstance().get(11) >= 16 ? 1 : 0, 16, 17).getTimeInMillis();
        Intent intent = new Intent(BaseApplication.e(), (Class<?>) SportRemindReceiver.class);
        intent.putExtra("remindType", new String[]{"TodayAchievement"});
        try {
            if (Build.VERSION.SDK_INT > 30 && CommonUtil.bh()) {
                sqa.ekz_(101010, intent, 335544320, 0, timeInMillis);
            } else {
                sqa.ekx_(101010, intent, 335544320, 0, timeInMillis);
            }
            SharedPerferenceUtils.c(BaseApplication.e(), DateFormatUtil.b(System.currentTimeMillis()));
        } catch (IllegalStateException e) {
            SharedPerferenceUtils.c(BaseApplication.e(), -1);
            ReleaseLogUtil.c("TC_TodayTgtHdlr", "registerAlarm：", ExceptionUtils.d(e));
        }
    }

    @Override // com.huawei.health.threeCircle.remind.model.OnRemindHandle
    public boolean isActiveTrigger() {
        return gjz.b(16, 17);
    }

    @Override // com.huawei.health.threeCircle.remind.model.OnRemindHandle
    public void destroy() {
        if (koq.c(this.h)) {
            HiHealthNativeApi.a(BaseApplication.e()).unSubscribeHiHealthData(this.h, new HiUnSubscribeListener() { // from class: gji
                @Override // com.huawei.hihealth.data.listener.HiUnSubscribeListener
                public final void onResult(boolean z) {
                    LogUtil.a("TC_TodayTgtHdlr", "onDestroy unSubscribeHiHealthData isSuccess ", Boolean.valueOf(z));
                }
            });
            this.g = null;
        }
    }

    private void b(final Map<String, Integer> map, final gjr gjrVar) {
        if (gjrVar == null) {
            return;
        }
        long currentTimeMillis = System.currentTimeMillis();
        nix.c().c(jdl.t(currentTimeMillis), jdl.e(currentTimeMillis), new ResponseCallback() { // from class: gjl
            @Override // com.huawei.hwbasemgr.ResponseCallback
            public final void onResponse(int i, Object obj) {
                gjj.this.e(gjrVar, map, i, (List) obj);
            }
        });
    }

    /* synthetic */ void e(final gjr gjrVar, final Map map, int i, List list) {
        if (koq.b(list)) {
            LogUtil.b("TC_TodayTgtHdlr", "can not get today three circle data.");
            return;
        }
        HiHealthData hiHealthData = (HiHealthData) list.get(0);
        if (hiHealthData == null) {
            LogUtil.b("TC_TodayTgtHdlr", "get today three circle data is null.");
            return;
        }
        final int i2 = hiHealthData.getInt("durationUserValue");
        final int i3 = hiHealthData.getInt("activeUserValue");
        final int i4 = hiHealthData.getInt("calorieUserValue");
        ReleaseLogUtil.e("R_TC_TodayTgtHdlr", "intensity:", Integer.valueOf(i2), " intensityGoal:", Integer.valueOf(this.i), "activeHours:", Integer.valueOf(i3), " activeGoal:", Integer.valueOf(this.c), "calories:", Integer.valueOf(i4), " caloriesGoal:", Integer.valueOf(this.f));
        if (this.c == 0 || this.i == 0 || this.f == 0) {
            d(new IBaseResponseCallback() { // from class: gjh
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public final void d(int i5, Object obj) {
                    gjj.this.a(i3, i2, i4, gjrVar, map, i5, obj);
                }
            });
        } else {
            b(i3, i2, i4, gjrVar, map);
        }
    }

    /* synthetic */ void a(int i, int i2, int i3, gjr gjrVar, Map map, int i4, Object obj) {
        b(i, i2, i3, gjrVar, map);
    }

    private ThreeCircleRemindData d(ThreeCircleRemindData threeCircleRemindData, ThreeCircleRemindData threeCircleRemindData2) {
        if (threeCircleRemindData != null && "OverGoal".equals(threeCircleRemindData.getRemindType())) {
            return threeCircleRemindData;
        }
        if (threeCircleRemindData2 == null || !"OverGoal".equals(threeCircleRemindData2.getRemindType())) {
            return null;
        }
        return threeCircleRemindData2;
    }

    private ThreeCircleRemindData a(boolean z, boolean z2, boolean z3, Map<String, Integer> map) {
        if (b("DoneAll") > 0 || !z || !z2 || !z3) {
            return null;
        }
        Integer num = map.get("TodayAchievement");
        a("DoneAll", 1);
        return new ThreeCircleRemindData.c().b("TodayAchievement").c("DoneAll").a(a("TodayAchievement", "DoneAll", 1, "DoneAll")).e(num != null ? 4 + num.intValue() : 4).c(System.currentTimeMillis()).d();
    }

    private ThreeCircleRemindData e(int i, int i2, boolean z, String str, Map<String, Integer> map) {
        if (i2 <= 0) {
            return null;
        }
        if (!z) {
            return c(i, i2, str, map);
        }
        return b(i, i2, str, map);
    }

    private ThreeCircleRemindData b(int i, int i2, String str, Map<String, Integer> map) {
        int i3;
        int b = b(str);
        if (("activeHourType".equals(str) && b > 0) || (i3 = i / i2) <= b) {
            return null;
        }
        a(str, i3);
        ThreeCircleRemindData.c cVar = new ThreeCircleRemindData.c();
        if (i3 == 1 || "activeHourType".equals(str)) {
            ReleaseLogUtil.e("TC_TodayTgtHdlr", "has achieve .", str);
            cVar.b("TodayAchievement");
            String d2 = d(str);
            cVar.c(d2);
            cVar.a(a("TodayAchievement", d2, i3, str));
            cVar.e(e("TodayAchievement", str, map));
        } else {
            if (i3 > 4) {
                ReleaseLogUtil.d("R_TC_TodayTgtHdlr", "multiples is too high.", Integer.valueOf(i3), str);
                return null;
            }
            ReleaseLogUtil.e("TC_TodayTgtHdlr", "has over goal .", str, " multiples:", Integer.valueOf(i3));
            cVar.b("OverGoal");
            String b2 = b(i3);
            cVar.c(b2);
            cVar.e(e("OverGoal", str, map));
            cVar.a(a("OverGoal", b2, i3, str));
            cVar.a(e(str, i3));
        }
        return cVar.d();
    }

    private ThreeCircleRemindData c(int i, int i2, String str, Map<String, Integer> map) {
        int b = b("LagEncourage");
        if (!d() || b != 0) {
            return null;
        }
        ThreeCircleRemindData.c cVar = new ThreeCircleRemindData.c();
        String a2 = a(str);
        cVar.b("LagEncourage").c(a2).e(e("LagEncourage", str, map)).a(a("LagEncourage", a2, i2 - i, str));
        a("LagEncourage", 1);
        return cVar.d();
    }

    private String a(String str) {
        if ("intensityType".equals(str)) {
            String[] strArr = f12824a;
            return strArr[new SecureRandom().nextInt(strArr.length)];
        }
        if ("caloriesType".equals(str)) {
            String[] strArr2 = d;
            return strArr2[new SecureRandom().nextInt(strArr2.length)];
        }
        LogUtil.a("TC_TodayTgtHdlr", "getRandomSubRemindType is not intensity or calories");
        return "LotActiveHour";
    }

    private String a(String str, String str2, int i, String str3) {
        ThreeCircleApi threeCircleApi = (ThreeCircleApi) Services.c("DailyActivity", ThreeCircleApi.class);
        njg queryRules = threeCircleApi.queryRules(str, str2);
        if (queryRules == null) {
            return "";
        }
        HashMap hashMap = new HashMap();
        Context e = BaseApplication.e();
        for (String str4 : queryRules.e()) {
            if (ParsedFieldTag.GOAL.equals(str4)) {
                if ("caloriesType".equals(str3)) {
                    hashMap.put(str4, e.getString(R.string.IDS_active_caloric));
                }
                if ("intensityType".equals(str3)) {
                    hashMap.put(str4, e.getString(R.string.IDS_active_workout));
                }
            }
            if ("reachGoalRate".equals(str4)) {
                hashMap.put(str4, UnitUtil.e(i * 100, 2, 0));
            }
            if ("remainHour".equals(str4)) {
                hashMap.put(str4, e.getResources().getQuantityString(R.plurals.IDS_single_circle_active_target_desc, i, UnitUtil.e(i, 1, 0)));
            }
            if ("remainMinute".equals(str4)) {
                i = b(str2, i);
                if (i >= 3) {
                    hashMap.put(str4, e.getResources().getQuantityString(R.plurals.IDS_single_circle_intensity_target_desc, i, UnitUtil.e(i, 1, 0)));
                } else {
                    LogUtil.a("TC_TodayTgtHdlr", "multiples less than 3, multiples is ", Integer.valueOf(i));
                }
            }
        }
        LogUtil.a("TC_TodayTgtHdlr", "paramMap:", hashMap.toString());
        return threeCircleApi.getPromptMessage(queryRules, hashMap);
    }

    private int b(String str, int i) {
        if ("LotCalorie_Run".equals(str)) {
            return ActiveTipStringUtils.e(i, 258);
        }
        if ("LotCalorie_Walk".equals(str)) {
            return ActiveTipStringUtils.e(i, 257);
        }
        LogUtil.a("TC_TodayTgtHdlr", "do not need change, subRemindType is ", str);
        return i;
    }

    private DeviceEventData e(String str, int i) {
        char c2;
        str.hashCode();
        int hashCode = str.hashCode();
        int i2 = 0;
        if (hashCode == -647116275) {
            if (str.equals("intensityType")) {
                c2 = 0;
            }
            c2 = 65535;
        } else if (hashCode != 1686952800) {
            if (hashCode == 1847125092 && str.equals("activeHourType")) {
                c2 = 2;
            }
            c2 = 65535;
        } else {
            if (str.equals("caloriesType")) {
                c2 = 1;
            }
            c2 = 65535;
        }
        if (c2 == 0) {
            i2 = 3;
        } else if (c2 == 1) {
            i2 = 2;
        } else if (c2 == 2) {
            i2 = 4;
        }
        return new DeviceEventData.c().j(2).c(System.currentTimeMillis() / 1000).g(i2).c(i).d();
    }

    private String d(String str) {
        char c2;
        str.hashCode();
        int hashCode = str.hashCode();
        if (hashCode == -647116275) {
            if (str.equals("intensityType")) {
                c2 = 0;
            }
            c2 = 65535;
        } else if (hashCode != 1686952800) {
            if (hashCode == 1847125092 && str.equals("activeHourType")) {
                c2 = 2;
            }
            c2 = 65535;
        } else {
            if (str.equals("caloriesType")) {
                c2 = 1;
            }
            c2 = 65535;
        }
        return c2 != 0 ? c2 != 1 ? c2 != 2 ? "" : "DoneActiveH" : "DoneCalorie" : "DoneIntensity";
    }

    private int e(String str, String str2, Map<String, Integer> map) {
        Integer num = map != null ? map.get(str) : null;
        int intValue = num == null ? 0 : num.intValue();
        return "activeHourType".equals(str2) ? intValue + 3 : "intensityType".equals(str2) ? intValue + 2 : intValue + 1;
    }

    private boolean d() {
        Calendar calendar = Calendar.getInstance();
        int i = (calendar.get(11) * 100) + calendar.get(12);
        return i >= 1600 && i <= 1700;
    }

    private int b(String str) {
        String str2 = this.e.get(str);
        if (str2 == null) {
            String e = SharedPreferenceManager.e("threeCircleSp", str, "");
            if (e == null) {
                this.e.put(str, "");
            } else {
                this.e.put(str, e);
            }
            str2 = this.e.get(str);
        }
        LogUtil.c("R_TC_TodayTgtHdlr", "cacheValue:", str2, " dataType:", str);
        if (str2 != null && str2.length() == 9) {
            String substring = str2.substring(0, 8);
            int e2 = CommonUtil.e(str2.substring(8), 0);
            if (substring.equals(Integer.toString(DateFormatUtil.b(System.currentTimeMillis())))) {
                return e2;
            }
        }
        return 0;
    }

    private void a(String str, int i) {
        String str2;
        ReleaseLogUtil.e("R_TC_TodayTgtHdlr", "dateType", str, "state:", Integer.valueOf(i));
        if ("activeHourType".equals(str) && i > 0) {
            str2 = DateFormatUtil.b(System.currentTimeMillis()) + Integer.toString(1);
        } else {
            str2 = DateFormatUtil.b(System.currentTimeMillis()) + Integer.toString(i);
        }
        SharedPreferenceManager.c("threeCircleSp", str, str2);
        this.e.put(str, str2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(final IBaseResponseCallback iBaseResponseCallback) {
        ArrayList arrayList = new ArrayList(3);
        arrayList.add("900200009");
        arrayList.add("900200008");
        arrayList.add("900200007");
        nip.a(arrayList, new IBaseResponseCallback() { // from class: gjm
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public final void d(int i, Object obj) {
                gjj.this.b(iBaseResponseCallback, i, obj);
            }
        });
    }

    /* synthetic */ void b(IBaseResponseCallback iBaseResponseCallback, int i, Object obj) {
        if (!(obj instanceof HashMap)) {
            LogUtil.h("TC_TodayTgtHdlr", "getActiveGoals objData is not instanceof HashMap");
            return;
        }
        HashMap hashMap = (HashMap) obj;
        this.i = nip.e(hashMap, "900200008", 25);
        this.c = nip.e(hashMap, "900200009", 12);
        this.f = nip.e(hashMap, "900200007", 270000);
        ReleaseLogUtil.e("R_TC_TodayTgtHdlr", "getActiveGoals intensityValue is ", Integer.valueOf(this.i), " standValue is ", Integer.valueOf(this.c), " calories goal:", Integer.valueOf(this.f));
        if (iBaseResponseCallback != null) {
            iBaseResponseCallback.d(0, obj);
        }
    }

    private void b(int i, int i2, int i3, gjr gjrVar, Map<String, Integer> map) {
        LogUtil.a("TC_TodayTgtHdlr", "checkIsAchieveGoal activeHour:", Integer.valueOf(i), "intensity:", Integer.valueOf(i2), "calories:", Integer.valueOf(i3));
        int i4 = this.c;
        boolean z = i >= i4;
        ThreeCircleRemindData e = e(i, i4, z, "activeHourType", map);
        int i5 = this.i;
        boolean z2 = i2 >= i5;
        ThreeCircleRemindData e2 = e(i2, i5, z2, "intensityType", map);
        int i6 = this.f;
        boolean z3 = i3 >= i6;
        ThreeCircleRemindData e3 = e(i3, i6, z3, "caloriesType", map);
        ThreeCircleRemindData d2 = d(e2, e3);
        if (d2 != null) {
            gjrVar.onResponse(0, d2);
            return;
        }
        ThreeCircleRemindData a2 = a(z2, z, z3, map);
        if (a2 != null) {
            gjrVar.onResponse(0, a2);
            return;
        }
        ThreeCircleRemindData d3 = d(e, e2, e3);
        if (d3 != null) {
            gjrVar.onResponse(0, d3);
        }
    }

    static class c implements HiSubscribeListener {

        /* renamed from: a, reason: collision with root package name */
        private WeakReference<gjj> f12825a;

        c(gjj gjjVar) {
            this.f12825a = new WeakReference<>(gjjVar);
        }

        @Override // com.huawei.hihealth.data.listener.HiSubscribeListener
        public void onResult(List<Integer> list, List<Integer> list2) {
            if (list != null && this.f12825a.get() != null) {
                this.f12825a.get().h = list;
                LogUtil.a("TC_TodayTgtHdlr", "onResult:", Integer.valueOf(list.size()));
            } else {
                LogUtil.h("TC_TodayTgtHdlr", "onResult list null.");
            }
        }

        @Override // com.huawei.hihealth.data.listener.HiSubscribeListener
        public void onChange(int i, HiHealthClient hiHealthClient, String str, HiHealthData hiHealthData, long j) {
            gjj gjjVar = this.f12825a.get();
            if (gjjVar != null && i == 103) {
                LogUtil.a("TC_TodayTgtHdlr", "onChange changeKey.", str);
                if ("900200007".equals(str) || "900200008".equals(str) || "900200009".equals(str)) {
                    gjjVar.d((IBaseResponseCallback) null);
                }
            }
        }
    }

    private String b(int i) {
        if (i == 2) {
            return "Double";
        }
        return i == 3 ? "Thrice" : i == 4 ? "Fourtimes" : "";
    }
}
