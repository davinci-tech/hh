package com.huawei.hwsmartinteractmgr.smarter;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.HiUserInfo;
import com.huawei.hihealth.api.HiHealthManager;
import com.huawei.hihealth.data.listener.HiCommonListener;
import com.huawei.hihealth.util.HiJsonUtil;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.hwsmartinteractmgr.data.ContentWeightWeekly;
import com.huawei.hwsmartinteractmgr.data.SmartMsgConstant;
import com.huawei.hwsmartinteractmgr.data.SmartMsgDbObject;
import com.huawei.hwsmartinteractmgr.smarthttpmodel.SmartHttpCallback;
import com.huawei.operation.OpAnalyticsUtil;
import com.huawei.operation.utils.Constants;
import com.huawei.ui.commonui.base.CommonUiBaseResponse;
import defpackage.kot;
import defpackage.kvs;
import defpackage.kvx;
import defpackage.kvz;
import defpackage.kwb;
import defpackage.kwh;
import defpackage.kwj;
import defpackage.kwn;
import health.compact.a.HiDateUtil;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/* loaded from: classes5.dex */
public class WeightSmarterHelper {
    private kvs d;
    private Context e;

    public WeightSmarterHelper(Context context) {
        this.e = context;
        this.d = kvs.b(context);
    }

    public void a(double d) {
        LogUtil.a("SMART_WeightSmarterHelper", "saveDataToUserInfo");
        final float f = (float) d;
        kwn.a(this.e, new IBaseResponseCallback() { // from class: com.huawei.hwsmartinteractmgr.smarter.WeightSmarterHelper.1
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i, Object obj) {
                if (i != 0) {
                    return;
                }
                HiHealthManager.d(BaseApplication.getContext()).fetchUserData(new HiCommonListener() { // from class: com.huawei.hwsmartinteractmgr.smarter.WeightSmarterHelper.1.4
                    @Override // com.huawei.hihealth.data.listener.HiCommonListener
                    public void onSuccess(int i2, Object obj2) {
                        LogUtil.a("SMART_WeightSmarterHelper", "fetchUserData Success ");
                        if (obj2 instanceof List) {
                            List list = (List) obj2;
                            if (list.isEmpty()) {
                                return;
                            }
                            LogUtil.a("SMART_WeightSmarterHelper", "fetchUserData Success not null ");
                            HiUserInfo hiUserInfo = (HiUserInfo) list.get(0);
                            hiUserInfo.setWeight(f);
                            OpAnalyticsUtil.getInstance().setProbabilityProblemEvent("callSetUserdata", "WeightSmartHelper");
                            HiHealthManager.d(WeightSmarterHelper.this.e).setUserData(hiUserInfo, new HiCommonListener() { // from class: com.huawei.hwsmartinteractmgr.smarter.WeightSmarterHelper.1.4.4
                                @Override // com.huawei.hihealth.data.listener.HiCommonListener
                                public void onSuccess(int i3, Object obj3) {
                                    LogUtil.a("SMART_WeightSmarterHelper", "save setUserData isSuccess ");
                                }

                                @Override // com.huawei.hihealth.data.listener.HiCommonListener
                                public void onFailure(int i3, Object obj3) {
                                    LogUtil.a("SMART_WeightSmarterHelper", "save setUserData failure ");
                                }
                            });
                        }
                    }

                    @Override // com.huawei.hihealth.data.listener.HiCommonListener
                    public void onFailure(int i2, Object obj2) {
                        LogUtil.a("SMART_WeightSmarterHelper", "fetchUserData Failure ");
                    }
                });
            }
        });
    }

    private void c(Context context, int i, final CommonUiBaseResponse commonUiBaseResponse) {
        long currentTimeMillis = System.currentTimeMillis();
        kot.a().b(context, currentTimeMillis - (i * 86400000), currentTimeMillis, 0, new IBaseResponseCallback() { // from class: com.huawei.hwsmartinteractmgr.smarter.WeightSmarterHelper.5
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i2, Object obj) {
                if (!(obj instanceof List)) {
                    commonUiBaseResponse.onResponse(100001, null);
                } else if (((List) obj).isEmpty()) {
                    commonUiBaseResponse.onResponse(100001, null);
                } else {
                    commonUiBaseResponse.onResponse(0, null);
                }
            }
        });
    }

    /* JADX WARN: Code restructure failed: missing block: B:9:0x0043, code lost:
    
        if (java.lang.Long.parseLong(r0) < health.compact.a.HiDateUtil.p(java.lang.System.currentTimeMillis())[0]) goto L10;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void e(final double r7, long r9) {
        /*
            r6 = this;
            android.content.Context r0 = r6.e
            r1 = 10006(0x2716, float:1.4021E-41)
            java.lang.String r1 = java.lang.Integer.toString(r1)
            java.lang.String r2 = "health_last_up_cloud_weight_goal_time"
            java.lang.String r0 = health.compact.a.SharedPreferenceManager.b(r0, r1, r2)
            long r1 = java.lang.System.currentTimeMillis()
            long r1 = r1 - r9
            java.lang.Long r9 = java.lang.Long.valueOf(r1)
            java.lang.String r10 = "lastUpCloudGoalTimeStr = "
            java.lang.String r3 = "judgeUploadAchieveWeight timeInterval = "
            java.lang.Object[] r9 = new java.lang.Object[]{r3, r9, r10, r0}
            java.lang.String r10 = "SMART_WeightSmarterHelper"
            com.huawei.hwlogsmodel.LogUtil.a(r10, r9)
            r3 = 604800000(0x240c8400, double:2.988109026E-315)
            int r9 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
            r1 = 0
            if (r9 >= 0) goto L55
            boolean r9 = android.text.TextUtils.isEmpty(r0)
            if (r9 == 0) goto L33
            goto L45
        L33:
            long r2 = java.lang.Long.parseLong(r0)     // Catch: java.lang.NumberFormatException -> L47
            long r4 = java.lang.System.currentTimeMillis()     // Catch: java.lang.NumberFormatException -> L47
            long[] r9 = health.compact.a.HiDateUtil.p(r4)     // Catch: java.lang.NumberFormatException -> L47
            r4 = r9[r1]     // Catch: java.lang.NumberFormatException -> L47
            int r9 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r9 >= 0) goto L55
        L45:
            r1 = 1
            goto L55
        L47:
            r9 = move-exception
            java.lang.String r0 = "judgeUploadAchieveWeight NumberFormatException = "
            java.lang.String r9 = r9.getMessage()
            java.lang.Object[] r9 = new java.lang.Object[]{r0, r9}
            com.huawei.hwlogsmodel.LogUtil.b(r10, r9)
        L55:
            java.lang.String r9 = "judgeUploadAchieveWeight isUpTimeResultOk = "
            java.lang.Boolean r0 = java.lang.Boolean.valueOf(r1)
            java.lang.Object[] r9 = new java.lang.Object[]{r9, r0}
            com.huawei.hwlogsmodel.LogUtil.a(r10, r9)
            kor r9 = defpackage.kor.a()
            android.content.Context r10 = r6.e
            com.huawei.hwsmartinteractmgr.smarter.WeightSmarterHelper$3 r0 = new com.huawei.hwsmartinteractmgr.smarter.WeightSmarterHelper$3
            r0.<init>()
            r9.b(r10, r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.hwsmartinteractmgr.smarter.WeightSmarterHelper.e(double, long):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d() {
        LogUtil.a("SMART_WeightSmarterHelper", "upLoadWeightGoalToCloud ");
        HashMap hashMap = new HashMap(3);
        hashMap.put("scenarioId", "101");
        hashMap.put("date", HiDateUtil.y(System.currentTimeMillis()));
        kwb d = kwb.d();
        d.d(2, new kwj(2));
        d.c(2, hashMap, new c());
    }

    class c extends SmartHttpCallback {
        @Override // com.huawei.hwsmartinteractmgr.smarthttpmodel.SmartHttpCallback
        public boolean shouldTrigger(Object obj) {
            return obj != null;
        }

        private c() {
        }

        @Override // com.huawei.hwsmartinteractmgr.smarthttpmodel.SmartHttpCallback
        public boolean isHandleResponse(int i, Object obj) {
            LogUtil.a("SMART_WeightSmarterHelper", "upLoadWeightGoalToCloud errCode = ", Integer.valueOf(i));
            boolean z = i == 0;
            if (z) {
                SharedPreferenceManager.e(WeightSmarterHelper.this.e, Integer.toString(10006), "health_last_up_cloud_weight_goal_time", String.valueOf(System.currentTimeMillis()), new StorageParams());
            }
            return z;
        }

        @Override // com.huawei.hwsmartinteractmgr.smarthttpmodel.SmartHttpCallback
        public void goTrigger(Object obj) {
            LogUtil.a("SMART_WeightSmarterHelper", "upLoadWeightGoalToCloud goTrigger");
        }
    }

    public void e(long[] jArr, final CommonUiBaseResponse commonUiBaseResponse) {
        LogUtil.a("SMART_WeightSmarterHelper", "getPeriodWeightData");
        final double[] dArr = new double[2];
        if (jArr == null || jArr.length != 2) {
            return;
        }
        kot.a().b(this.e, jArr[0], jArr[1], 0, new IBaseResponseCallback() { // from class: com.huawei.hwsmartinteractmgr.smarter.WeightSmarterHelper.2
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i, Object obj) {
                CommonUiBaseResponse commonUiBaseResponse2 = commonUiBaseResponse;
                if (commonUiBaseResponse2 == null) {
                    return;
                }
                if (obj instanceof List) {
                    List list = (List) obj;
                    LogUtil.c("SMART_WeightSmarterHelper", "getPeriodWeightData data = ", list);
                    if (list.isEmpty()) {
                        commonUiBaseResponse.onResponse(100001, null);
                        return;
                    }
                    double d = ((HiHealthData) list.get(list.size() - 1)).getDouble("bodyWeight");
                    double d2 = ((HiHealthData) list.get(0)).getDouble("bodyWeight");
                    double[] dArr2 = dArr;
                    dArr2[0] = d;
                    dArr2[1] = d2;
                    commonUiBaseResponse.onResponse(0, dArr2);
                    return;
                }
                commonUiBaseResponse2.onResponse(100001, null);
            }
        });
    }

    private void c(long[] jArr, final CommonUiBaseResponse commonUiBaseResponse) {
        LogUtil.a("SMART_WeightSmarterHelper", "getAchieveGoalFromCloud ");
        HashMap hashMap = new HashMap(3);
        hashMap.put("scenarioId", "101");
        if (jArr == null || jArr.length != 2) {
            return;
        }
        hashMap.put(Constants.START_DATE, HiDateUtil.y(jArr[0]));
        hashMap.put("endDate", HiDateUtil.y(jArr[1]));
        kwb d = kwb.d();
        d.d(3, new kwh(3));
        d.c(3, hashMap, new SmartHttpCallback<Long>() { // from class: com.huawei.hwsmartinteractmgr.smarter.WeightSmarterHelper.4
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.huawei.hwsmartinteractmgr.smarthttpmodel.SmartHttpCallback
            /* renamed from: c, reason: merged with bridge method [inline-methods] */
            public boolean isHandleResponse(int i, Long l) {
                LogUtil.a("SMART_WeightSmarterHelper", "getAchieveGoalFromCloud errCode = ", Integer.valueOf(i));
                return i == 0;
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.huawei.hwsmartinteractmgr.smarthttpmodel.SmartHttpCallback
            /* renamed from: d, reason: merged with bridge method [inline-methods] */
            public boolean shouldTrigger(Long l) {
                LogUtil.a("SMART_WeightSmarterHelper", "getAchieveGoalFromCloud integer = ", l);
                return l != null;
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.huawei.hwsmartinteractmgr.smarthttpmodel.SmartHttpCallback
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public void goTrigger(Long l) {
                CommonUiBaseResponse commonUiBaseResponse2 = commonUiBaseResponse;
                if (commonUiBaseResponse2 != null) {
                    commonUiBaseResponse2.onResponse(0, l);
                }
            }
        });
    }

    public void b(final long[] jArr, double[] dArr, double d) {
        double abs;
        LogUtil.a("SMART_WeightSmarterHelper", "generateWeightWeeklyMsg");
        if (jArr == null || dArr == null || dArr.length <= 1) {
            return;
        }
        LogUtil.c("SMART_WeightSmarterHelper", "generateWeightWeeklyMsg datas = ", Arrays.toString(dArr));
        int i = 0;
        if (Math.abs(dArr[0] - dArr[1]) < 0.05d) {
            abs = 0.0d;
        } else {
            double d2 = dArr[0];
            double d3 = dArr[1];
            if (d2 > d3) {
                abs = Math.abs(d2 - d3);
                i = 1;
            } else {
                abs = Math.abs(d2 - d3);
                i = 2;
            }
        }
        final double d4 = abs;
        if (dArr[1] <= d) {
            i = 3;
        }
        final int i2 = i;
        LogUtil.a("SMART_WeightSmarterHelper", "generateWeightWeeklyMsg finalWeekUpDown = ", Integer.valueOf(i2));
        c(jArr, new CommonUiBaseResponse() { // from class: com.huawei.hwsmartinteractmgr.smarter.WeightSmarterHelper.6
            @Override // com.huawei.ui.commonui.base.CommonUiBaseResponse
            public void onResponse(int i3, Object obj) {
                if (i3 != 0 || obj == null) {
                    return;
                }
                long longValue = ((Long) obj).longValue();
                LogUtil.a("SMART_WeightSmarterHelper", "save result = ", Integer.valueOf(WeightSmarterHelper.this.c("health_weight_weekly_start_time_key", String.valueOf(jArr[0]))), Integer.valueOf(WeightSmarterHelper.this.c("health_weight_weekly_end_time_key", String.valueOf(jArr[1]))), Integer.valueOf(WeightSmarterHelper.this.c("health_weight_weekly_data_change_key", String.valueOf(d4))), Integer.valueOf(WeightSmarterHelper.this.c("health_weight_weekly_up_down_key", String.valueOf(i2))), Integer.valueOf(WeightSmarterHelper.this.c("health_weight_weekly_reach_num_key", String.valueOf(longValue))));
                WeightSmarterHelper.this.b(i2, d4, longValue, jArr);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int c(String str, String str2) {
        return SharedPreferenceManager.e(this.e, Integer.toString(10006), str, str2, new StorageParams(1));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(int i, double d, long j, long[] jArr) {
        String b = SharedPreferenceManager.b(this.e, Integer.toString(10021), "recommend_weight_weekly_report");
        LogUtil.a("SMART_WeightSmarterHelper", "generateWeightWeeklyMsg weightWeeklyReport = ", b);
        if ("1".equals(b)) {
            return;
        }
        SmartMsgDbObject smartMsgDbObject = new SmartMsgDbObject();
        smartMsgDbObject.setMsgType(20007);
        smartMsgDbObject.setMsgSrc(2);
        smartMsgDbObject.setMsgContentType(5);
        ContentWeightWeekly contentWeightWeekly = new ContentWeightWeekly(i, d, j);
        contentWeightWeekly.setTimePeriod(0, jArr[0]);
        contentWeightWeekly.setTimePeriod(1, jArr[1]);
        String d2 = HiJsonUtil.d(contentWeightWeekly, ContentWeightWeekly.class);
        smartMsgDbObject.setMsgContent(d2);
        smartMsgDbObject.setShowMethod(SmartMsgConstant.SHOW_METHOD_SMART_CARD_HI_BOARD);
        smartMsgDbObject.setShowTime(SmartMsgConstant.DEFAULT_SHOW_TIME);
        smartMsgDbObject.setStatus(1);
        smartMsgDbObject.setMessagePriority(kwn.d(30001, "ai-weight-007"));
        SmartMsgDbObject a2 = this.d.a(20007);
        if (a2 == null) {
            this.d.a(smartMsgDbObject);
            return;
        }
        long updateTime = a2.getUpdateTime();
        LogUtil.a("SMART_WeightSmarterHelper", "generateWeightWeeklyMsg createTime = ", new Date(updateTime));
        if (System.currentTimeMillis() - updateTime >= 604800000) {
            this.d.d(20007);
            this.d.a(smartMsgDbObject);
        } else {
            if (a2.getMsgContent().equals(d2)) {
                return;
            }
            LogUtil.a("SMART_WeightSmarterHelper", "generateWeightWeeklyMsg updateMessage");
            a2.setMsgContent(d2);
            this.d.e(a2.getId(), a2);
        }
    }

    public static boolean[] e(List<String> list, List<String> list2) {
        boolean z;
        boolean z2;
        boolean[] zArr = new boolean[2];
        if (list == null || list.isEmpty()) {
            z = false;
            z2 = false;
        } else {
            if (list.contains("bmi_l3") || list.contains("bmi_l4") || list.contains("bmi_l5")) {
                z2 = true;
            } else {
                LogUtil.a("SMART_WeightSmarterHelper", "checkOnLabelChange isBmiLabelChanged = false");
                z2 = false;
            }
            z = list.contains("SportFitness_0") || list.contains("SportFitness_1");
        }
        if (list2 != null && !list2.isEmpty()) {
            if (list2.contains("bmi_l3") || list2.contains("bmi_l4") || list2.contains("bmi_l5")) {
                z2 = true;
            } else {
                LogUtil.a("SMART_WeightSmarterHelper", "checkOnLabelChange isBmiLabelChanged = false");
            }
            if (list2.contains("SportFitness_0") || list2.contains("SportFitness_1")) {
                z = true;
            }
        }
        zArr[0] = z2;
        zArr[1] = z;
        return zArr;
    }

    public static boolean b(Context context) {
        return context != null && kwn.b(context, "HDK_WEIGHT") > 0;
    }

    public void d(CommonUiBaseResponse commonUiBaseResponse) {
        LogUtil.c("SMART_WeightSmarterHelper", "judgeSuggestWeightVideo isExerciseTimes = ", Boolean.valueOf(kvz.e()));
        LogUtil.a("SMART_WeightSmarterHelper", "judgeSuggestWeightVideo reduceFatVideo = ", SharedPreferenceManager.b(this.e, Integer.toString(10021), "recommend_reduce_fat_video"), "isRuleOpen = ", Boolean.valueOf(kwn.c(30001, "ai-weight-006")));
        if (commonUiBaseResponse != null) {
            commonUiBaseResponse.onResponse(100001, null);
        }
    }

    public void e(String str, final CommonUiBaseResponse commonUiBaseResponse) {
        if (TextUtils.isEmpty(str) || commonUiBaseResponse == null) {
            LogUtil.a("SMART_WeightSmarterHelper", "checkWeightCommon ruleConstants=", str, " or callback == null");
            return;
        }
        LogUtil.a("SMART_WeightSmarterHelper", "checkWeightCommon ruleConstants=", str);
        boolean b = kvx.c(this.e).b(4);
        LogUtil.c("SMART_WeightSmarterHelper", "checkWeightCommon isFollowWeight=", Boolean.valueOf(b));
        if (b) {
            commonUiBaseResponse.onResponse(0, null);
            return;
        }
        boolean h = kvz.h();
        LogUtil.c("SMART_WeightSmarterHelper", "checkWeightCommon isOutBmi = ", Boolean.valueOf(h));
        if (h) {
            commonUiBaseResponse.onResponse(0, null);
            return;
        }
        try {
            int parseInt = Integer.parseInt(kwn.b(30001, str, "recently_num_days_have_data"));
            LogUtil.a("SMART_WeightSmarterHelper", "checkWeightCommon days = ", Integer.valueOf(parseInt));
            c(this.e, parseInt, new CommonUiBaseResponse() { // from class: com.huawei.hwsmartinteractmgr.smarter.WeightSmarterHelper.10
                @Override // com.huawei.ui.commonui.base.CommonUiBaseResponse
                public void onResponse(int i, Object obj) {
                    LogUtil.a("SMART_WeightSmarterHelper", "checkWeightCommon hasWeightData errCode = ", Integer.valueOf(i));
                    if (i == 0) {
                        commonUiBaseResponse.onResponse(0, null);
                    } else {
                        commonUiBaseResponse.onResponse(100001, null);
                    }
                }
            });
        } catch (NumberFormatException e) {
            LogUtil.b("SMART_WeightSmarterHelper", "checkWeightCommon NumberFormatException = ", e.getMessage());
        }
    }
}
