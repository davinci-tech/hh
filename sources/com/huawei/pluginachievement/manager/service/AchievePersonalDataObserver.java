package com.huawei.pluginachievement.manager.service;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import com.huawei.health.R;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.pluginachievement.impl.AchieveObserver;
import com.huawei.pluginachievement.impl.PersonalDataCallback;
import com.huawei.pluginachievement.manager.model.AchieveInfo;
import com.huawei.pluginachievement.manager.model.AchieveUserLevelInfo;
import com.huawei.pluginachievement.manager.model.PersonalData;
import com.huawei.pluginachievement.manager.model.RecentWeekRecord;
import com.huawei.pluginachievement.manager.model.ResultCode;
import com.huawei.pluginachievement.manager.model.SingleDayRecord;
import com.huawei.pluginachievement.manager.model.TotalRecord;
import com.huawei.pluginachievement.manager.model.UserAchieveWrapper;
import defpackage.mct;
import defpackage.mcz;
import defpackage.mee;
import defpackage.meh;
import defpackage.mlc;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes6.dex */
public class AchievePersonalDataObserver implements AchieveObserver {

    /* renamed from: a, reason: collision with root package name */
    private Context f8373a;
    private meh b;
    private Map<Integer, String> c = new HashMap(8);
    private PersonalDataCallback d;
    private PersonalData e;

    public AchievePersonalDataObserver(Context context) {
        this.f8373a = context;
        b();
        e(this.c, context);
    }

    public void c(PersonalDataCallback personalDataCallback) {
        this.d = personalDataCallback;
    }

    private boolean d(String str) {
        return !"0".equals(str);
    }

    @Override // com.huawei.pluginachievement.impl.AchieveObserver
    public void onDataChanged(int i, UserAchieveWrapper userAchieveWrapper) {
        LogUtil.a("PLGACHIEVE_AchievePersonalDataObserver", "AchievePersonalDataObserver|onDataChanged  error= ", Integer.valueOf(i));
        if (i == -1) {
            b(this);
            return;
        }
        if (userAchieveWrapper == null) {
            return;
        }
        int contentType = userAchieveWrapper.getContentType();
        LogUtil.a("PLGACHIEVE_AchievePersonalDataObserver", "AchievePersonalDataObserver|onDataChanged contentType = ", Integer.valueOf(contentType));
        if (contentType == 0) {
            d(userAchieveWrapper);
        }
    }

    private void d(UserAchieveWrapper userAchieveWrapper) {
        LogUtil.a("PLGACHIEVE_AchievePersonalDataObserver", "processPersonalInfo enter");
        if (userAchieveWrapper == null) {
            b(this);
            return;
        }
        if (d(userAchieveWrapper.getResultCode())) {
            if (ResultCode.CODE_NEVER_SYNC.equals(userAchieveWrapper.getResultCode())) {
                mct.b(this.f8373a, "NEWBIE", "done");
            }
            PersonalData d = d();
            this.e = d;
            PersonalDataCallback personalDataCallback = this.d;
            if (personalDataCallback != null) {
                personalDataCallback.onPersonalDataChange(d);
                this.d = null;
            }
            b(this);
            return;
        }
        SingleDayRecord acquireSingleDayRecord = userAchieveWrapper.acquireSingleDayRecord();
        TotalRecord acquireTotalRecord = userAchieveWrapper.acquireTotalRecord();
        if (TextUtils.isEmpty(mct.b(this.f8373a, "NEWBIE")) && acquireTotalRecord != null) {
            if (acquireTotalRecord.getDays() <= 10) {
                LogUtil.c("AchievePersonalDataObserver", " set the new user");
                mct.b(this.f8373a, "NEWBIE", "done");
            } else {
                LogUtil.c("AchievePersonalDataObserver", " set the old user");
                mct.b(this.f8373a, "NEWBIE", "doing");
            }
        }
        PersonalData d2 = d(acquireSingleDayRecord, acquireTotalRecord, userAchieveWrapper.getAchieveInfo());
        this.e = d2;
        PersonalDataCallback personalDataCallback2 = this.d;
        if (personalDataCallback2 != null) {
            personalDataCallback2.onPersonalDataChange(d2);
            this.d = null;
        }
        b(this);
    }

    private PersonalData d() {
        HashMap hashMap = new HashMap(2);
        mcz d = this.b.d(14, hashMap);
        int i = 1;
        mcz d2 = this.b.d(1, hashMap);
        mcz d3 = this.b.d(5, hashMap);
        SingleDayRecord singleDayRecord = (SingleDayRecord) this.b.d(2, hashMap);
        boolean z = d3 instanceof AchieveInfo;
        String acquireMedals = z ? ((AchieveInfo) d3).acquireMedals() : "";
        if (d instanceof AchieveUserLevelInfo) {
            i = ((AchieveUserLevelInfo) d).acquireUserLevel();
        } else if (z) {
            i = mlc.c(((AchieveInfo) d3).getUserReachStandardDays());
        }
        int userPoint = z ? ((AchieveInfo) d3).getUserPoint() : 0;
        boolean z2 = d2 instanceof TotalRecord;
        int days = z2 ? ((TotalRecord) d2).getDays() : 0;
        return PersonalData.builder().withMedals(acquireMedals).withPersonalLevel(i).withPersonalLevelDesc(this.c.get(Integer.valueOf(i))).withSumKakaNum(userPoint).withSumDays(days).withSumSteps(z2 ? ((TotalRecord) d2).getSteps() : 0.0d).withBestDaySteps(singleDayRecord != null ? singleDayRecord.getSteps() : 0).build();
    }

    private PersonalData d(SingleDayRecord singleDayRecord, TotalRecord totalRecord, AchieveInfo achieveInfo) {
        int i;
        String str;
        HashMap hashMap = new HashMap(2);
        mcz d = this.b.d(14, hashMap);
        int i2 = 1;
        mcz d2 = this.b.d(1, hashMap);
        mcz d3 = this.b.d(5, hashMap);
        mcz d4 = this.b.d(2, hashMap);
        HashMap hashMap2 = new HashMap(2);
        hashMap2.put("reportNo", mct.e(this.f8373a, "_weekReportNo", "0"));
        mcz d5 = this.b.d(3, hashMap2);
        String acquireMedals = achieveInfo == null ? "" : achieveInfo.acquireMedals();
        boolean z = d3 instanceof AchieveInfo;
        String acquireMedals2 = z ? ((AchieveInfo) d3).acquireMedals() : "";
        if (d instanceof AchieveUserLevelInfo) {
            i = ((AchieveUserLevelInfo) d).acquireUserLevel();
        } else {
            if (z) {
                i2 = mlc.c(achieveInfo != null ? achieveInfo.getUserReachStandardDays() : 0.0d);
            }
            i = i2;
        }
        int userPoint = z ? ((AchieveInfo) d3).getUserPoint() : 0;
        boolean z2 = d2 instanceof TotalRecord;
        int days = z2 ? ((TotalRecord) d2).getDays() : 0;
        double steps = z2 ? ((TotalRecord) d2).getSteps() : 0.0d;
        int steps2 = d4 instanceof SingleDayRecord ? ((SingleDayRecord) d4).getSteps() : 0;
        String b = mct.b(this.f8373a, "_uploadMedal");
        PersonalData.Builder builder = PersonalData.builder();
        if (achieveInfo == null || !TextUtils.isEmpty(b)) {
            acquireMedals = acquireMedals2;
        }
        PersonalData.Builder withStepRanking = builder.withMedals(acquireMedals).withPersonalLevel(i).withStepRanking(d5 instanceof RecentWeekRecord ? ((RecentWeekRecord) d5).acquireStepsRanking() : 0.0d);
        if (achieveInfo != null) {
            str = this.c.get(Integer.valueOf(achieveInfo.getUserLevel()));
        } else {
            str = this.c.get(Integer.valueOf(i));
        }
        PersonalData.Builder withPersonalLevelDesc = withStepRanking.withPersonalLevelDesc(str);
        if (achieveInfo != null) {
            userPoint = achieveInfo.getUserPoint();
        }
        PersonalData.Builder withSumKakaNum = withPersonalLevelDesc.withSumKakaNum(userPoint);
        if (totalRecord != null) {
            days = totalRecord.getDays();
        }
        PersonalData.Builder withSumDays = withSumKakaNum.withSumDays(days);
        if (totalRecord != null) {
            steps = totalRecord.getSteps();
        }
        PersonalData.Builder withSumSteps = withSumDays.withSumSteps(steps);
        if (singleDayRecord != null) {
            steps2 = singleDayRecord.getSteps();
        }
        return withSumSteps.withBestDaySteps(steps2).build();
    }

    public void b(AchievePersonalDataObserver achievePersonalDataObserver) {
        if (achievePersonalDataObserver == null) {
            LogUtil.a("PLGACHIEVE_AchievePersonalDataObserver", "unregisterPersonalDataObserver object maybe not create.");
        } else {
            mee.b(this.f8373a).a(achievePersonalDataObserver);
        }
    }

    private void b() {
        if (this.b == null) {
            this.b = meh.c(BaseApplication.getContext());
        }
    }

    private void e(Map<Integer, String> map, Context context) {
        if (map == null) {
            return;
        }
        map.put(1, context.getResources().getString(R.string._2130840690_res_0x7f020c72));
        map.put(2, context.getResources().getString(R.string._2130840691_res_0x7f020c73));
        map.put(3, context.getResources().getString(R.string._2130840692_res_0x7f020c74));
        map.put(4, context.getResources().getString(R.string._2130840693_res_0x7f020c75));
        map.put(5, context.getResources().getString(R.string._2130840694_res_0x7f020c76));
    }

    public static void a(Context context, String str, String str2) {
        if (context == null || TextUtils.isEmpty(str)) {
            return;
        }
        SharedPreferences.Editor edit = context.getSharedPreferences("nps_config", 0).edit();
        LogUtil.a("AchievePersonalDataObserver", "setUserType: ", str2);
        edit.putString(str, str2).apply();
    }

    public static void a(int i) {
        LogUtil.a("PLGACHIEVE_AchievePersonalDataObserver", "setOldAndNewUserStatus: days -> " + i);
        Context context = BaseApplication.getContext();
        if (context == null) {
            LogUtil.h("PLGACHIEVE_AchievePersonalDataObserver", " setOldAndNewUserStatus: context is null");
            return;
        }
        String accountInfo = LoginInit.getInstance(context).getAccountInfo(1011);
        if (TextUtils.isEmpty(accountInfo)) {
            LogUtil.h("PLGACHIEVE_AchievePersonalDataObserver", " setOldAndNewUserStatus: userId is empty");
            return;
        }
        if (i <= 10) {
            LogUtil.a("PLGACHIEVE_AchievePersonalDataObserver", " setOldAndNewUserStatus: set the new user");
            a(context, "NEWBIE" + accountInfo, "done");
            return;
        }
        LogUtil.a("PLGACHIEVE_AchievePersonalDataObserver", " setOldAndNewUserStatus: set the old user");
        a(context, "NEWBIE" + accountInfo, "doing");
    }
}
