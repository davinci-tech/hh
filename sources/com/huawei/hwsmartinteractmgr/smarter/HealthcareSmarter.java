package com.huawei.hwsmartinteractmgr.smarter;

import android.content.Context;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.hwsmartinteractmgr.data.ActivitySimple;
import com.huawei.hwsmartinteractmgr.interactors.HealthcareInteractors;
import com.huawei.hwsmartinteractmgr.smarthttpmodel.SmartHttpCallback;
import com.huawei.hwsmartinteractmgr.userlabel.LabelObserver;
import com.huawei.operation.utils.PhoneInfoUtils;
import com.huawei.pluginachievement.manager.db.IAchieveDBMgr;
import com.huawei.ui.thirdpartservice.syncdata.constants.SyncDataConstant;
import defpackage.kvz;
import defpackage.kwb;
import defpackage.kwi;
import defpackage.kwn;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/* loaded from: classes5.dex */
public class HealthcareSmarter extends BaseSmarter implements LabelObserver {

    /* renamed from: a, reason: collision with root package name */
    private ExecutorService f6404a;
    private final kwb c;
    private HealthcareInteractors d;

    public HealthcareSmarter(Context context) {
        super(context);
        this.f6404a = Executors.newCachedThreadPool();
        this.c = kwb.d();
        this.d = new HealthcareInteractors(this.e);
    }

    @Override // com.huawei.hwsmartinteractmgr.smarter.BaseSmarter
    public void d() {
        super.d();
        LogUtil.a("SMART_HealthcareSmarter", "startTimerCheck");
        if (!kwn.b(this.e, "last_checktime_healthcare_by_timer", 86400000L)) {
            LogUtil.a("SMART_HealthcareSmarter", "startTimerCheck, checkInterval fail, return");
        } else {
            kwn.a(this.e, "last_checktime_healthcare_by_timer", System.currentTimeMillis());
            a();
        }
    }

    public void b() {
        LogUtil.a("SMART_HealthcareSmarter", "startRuleByUser");
        if (!kwn.b(this.e, "last_checktime_healthcare_by_user", 300000L)) {
            LogUtil.a("SMART_HealthcareSmarter", "startRuleByUser, checkInterval fail, return");
        } else {
            kwn.a(this.e, "last_checktime_healthcare_by_user", System.currentTimeMillis());
            a();
        }
    }

    private void a() {
        this.f6404a.execute(new Runnable() { // from class: com.huawei.hwsmartinteractmgr.smarter.HealthcareSmarter.3
            @Override // java.lang.Runnable
            public void run() {
                HealthcareSmarter.this.e();
                HealthcareSmarter.this.c();
            }
        });
    }

    @Override // com.huawei.hwsmartinteractmgr.userlabel.LabelObserver
    public void onChange(final Map<Integer, List<String>> map) {
        if (map == null) {
            return;
        }
        this.f6404a.execute(new Runnable() { // from class: com.huawei.hwsmartinteractmgr.smarter.HealthcareSmarter.5
            @Override // java.lang.Runnable
            public void run() {
                List list = (List) map.get(1);
                List list2 = (List) map.get(2);
                boolean z = list != null && list.contains("SportStep_1");
                boolean z2 = list2 != null && list2.contains("SportStep_1");
                if (z || z2) {
                    HealthcareSmarter.this.e();
                    HealthcareSmarter.this.c();
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e() {
        LogUtil.a("SMART_HealthcareSmarter", "recommendReachGoalActivity");
        if (!b("recommend_standard_activity", 30000, "ai-walk-001")) {
            kwn.c(this.e, 10000, 3);
            LogUtil.a("SMART_HealthcareSmarter", "not recommend, return");
        } else if (!kvz.j()) {
            kwn.c(this.e, 10000, 3);
            LogUtil.a("SMART_HealthcareSmarter", "no sportStepLittle label, return");
        } else {
            a("0", new c());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c() {
        LogUtil.a("SMART_HealthcareSmarter", "remindReachStepsGoal");
        if (!b("standard_activity_steps_target", 30000, "ai-walk-002")) {
            kwn.c(this.e, 10001, 3);
            LogUtil.a("SMART_HealthcareSmarter", "not recommend, return");
        } else {
            a("1", new e());
        }
    }

    private void a(String str, SmartHttpCallback<List<ActivitySimple>> smartHttpCallback) {
        LogUtil.a("SMART_HealthcareSmarter", "requestActivities, joinStatus=", str);
        HashMap hashMap = new HashMap(8);
        hashMap.put("joinStatus", str);
        hashMap.put("finishFlag", "1");
        hashMap.put(SyncDataConstant.BI_KEY_ACTIVITY_TYPE, "11");
        hashMap.put("pageNo", "0");
        hashMap.put(IAchieveDBMgr.PARAM_PAGE_SIZE, "50");
        hashMap.put("phoneType", PhoneInfoUtils.getPhoneType());
        this.c.d(0, new kwi(0));
        this.c.c(0, hashMap, smartHttpCallback);
    }

    class c extends SmartHttpCallback<List<ActivitySimple>> {
        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.huawei.hwsmartinteractmgr.smarthttpmodel.SmartHttpCallback
        /* renamed from: d, reason: merged with bridge method [inline-methods] */
        public boolean shouldTrigger(List<ActivitySimple> list) {
            return true;
        }

        private c() {
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.huawei.hwsmartinteractmgr.smarthttpmodel.SmartHttpCallback
        /* renamed from: c, reason: merged with bridge method [inline-methods] */
        public boolean isHandleResponse(int i, List<ActivitySimple> list) {
            return HealthcareSmarter.this.a(i, list);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.huawei.hwsmartinteractmgr.smarthttpmodel.SmartHttpCallback
        /* renamed from: e, reason: merged with bridge method [inline-methods] */
        public void goTrigger(List<ActivitySimple> list) {
            if (list != null) {
                LogUtil.a("SMART_HealthcareSmarter", "RequestStepsGoalActivityCallback.goTrigger, activities.size()=", Integer.valueOf(list.size()));
                if (list.isEmpty()) {
                    kwn.c(HealthcareSmarter.this.e, 10000, 3);
                } else {
                    HealthcareSmarter.this.d.d(list.get(0));
                }
            }
        }
    }

    class e extends SmartHttpCallback<List<ActivitySimple>> {
        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.huawei.hwsmartinteractmgr.smarthttpmodel.SmartHttpCallback
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public boolean shouldTrigger(List<ActivitySimple> list) {
            return true;
        }

        private e() {
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.huawei.hwsmartinteractmgr.smarthttpmodel.SmartHttpCallback
        /* renamed from: e, reason: merged with bridge method [inline-methods] */
        public boolean isHandleResponse(int i, List<ActivitySimple> list) {
            return HealthcareSmarter.this.a(i, list);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.huawei.hwsmartinteractmgr.smarthttpmodel.SmartHttpCallback
        /* renamed from: c, reason: merged with bridge method [inline-methods] */
        public void goTrigger(List<ActivitySimple> list) {
            if (list != null) {
                LogUtil.a("SMART_HealthcareSmarter", "RequestJoinActivityCallback, goTrigger, activities.size()=", Integer.valueOf(list.size()));
                if (list.isEmpty()) {
                    kwn.c(HealthcareSmarter.this.e, 10001, 3);
                    return;
                }
                for (ActivitySimple activitySimple : list) {
                    if (activitySimple.getActivityStatus() == 0) {
                        LogUtil.c("SMART_HealthcareSmarter", "not completed activity");
                        HealthcareSmarter.this.d.b(activitySimple);
                        return;
                    }
                }
                kwn.c(HealthcareSmarter.this.e, 10001, 3);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean a(int i, Object obj) {
        boolean z = obj == null;
        if (i != 0 && i != 8 && i != -1001) {
            z = true;
        }
        LogUtil.a("SMART_HealthcareSmarter", "errCode = ", Integer.valueOf(i), " isError is ", Boolean.valueOf(z));
        return !z;
    }
}
