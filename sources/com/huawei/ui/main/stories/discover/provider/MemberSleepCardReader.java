package com.huawei.ui.main.stories.discover.provider;

import android.content.Context;
import android.text.SpannableString;
import android.text.TextUtils;
import android.util.SparseArray;
import com.huawei.haf.design.pattern.Observer;
import com.huawei.haf.design.pattern.ObserverManagerUtil;
import com.huawei.health.R;
import com.huawei.health.marketing.datatype.RecommendResourceInfo;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.data.listener.HiDataReadResultListener;
import com.huawei.hihealth.dictionary.utils.DicDataTypeUtil;
import com.huawei.hms.network.embedded.k;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwcommonmodel.fitnessdatatype.SleepTotalData;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.openalliance.ad.constant.ConfigMapDefValues;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.ui.main.R$string;
import defpackage.jdl;
import defpackage.jec;
import defpackage.koq;
import defpackage.kpi;
import defpackage.npt;
import defpackage.nrq;
import defpackage.pob;
import defpackage.pqr;
import defpackage.pxb;
import defpackage.qnl;
import health.compact.a.UnitUtil;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.lang.ref.WeakReference;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.IllegalFormatConversionException;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes9.dex */
public class MemberSleepCardReader extends MemberCardReader {
    private static final int[] h = {DicDataTypeUtil.DataType.SLEEP_SCORE.value(), DicDataTypeUtil.DataType.EFFECTIVE_SLEEP_DURATION.value(), DicDataTypeUtil.DataType.BED_TIME.value()};
    private String f;
    private Observer g;
    private boolean j;
    private List<SleepTotalData> l;
    private SpannableString m;

    public MemberSleepCardReader(Context context) {
        super(context);
        this.j = false;
        this.g = new c(this);
        a(Arrays.asList(3), this);
        ObserverManagerUtil.d(this.g, "MonthProblemRefresh");
        c("vipPageSleep");
        a(AnalyticsValue.VIP_INTRO_RECOMMEND_SLEEP_CARD.value());
        j("VIPCard_MemberSleepCardReader");
    }

    @Override // com.huawei.ui.main.stories.discover.provider.MemberCardReader, com.huawei.ui.main.stories.discover.provider.MemberBaseCardReader
    public void initCardReader(List<RecommendResourceInfo> list) {
        super.initCardReader(list);
        c(2);
    }

    static class c implements Observer {
        private WeakReference<MemberSleepCardReader> c;

        c(MemberSleepCardReader memberSleepCardReader) {
            this.c = new WeakReference<>(memberSleepCardReader);
        }

        @Override // com.huawei.haf.design.pattern.Observer
        public void notify(String str, Object... objArr) {
            LogUtil.a("VIPCard_MemberSleepCardReader", "mMonthProblemObserver");
            MemberSleepCardReader memberSleepCardReader = this.c.get();
            if (memberSleepCardReader == null) {
                LogUtil.b("VIPCard_MemberSleepCardReader", "mMonthProblemObserver is null");
            } else if ("MonthProblemRefresh".equals(str)) {
                memberSleepCardReader.j = true;
            }
        }
    }

    private void q() {
        if (!l() && o() && !k()) {
            LogUtil.a("VIPCard_MemberSleepCardReader", "do not need to refresh card");
            return;
        }
        String str = d() + Constants.LINK + this.f;
        if (!b(str)) {
            LogUtil.h("VIPCard_MemberSleepCardReader", "no resources on cloud，try no data label");
            str = d() + "-addRecord";
            v();
        }
        LogUtil.a("VIPCard_MemberSleepCardReader", "constructRecommendCardBean");
        i(this.f);
        h(str);
        final RecommendResourceInfo d2 = d(h());
        if (d2 == null || d2.isInvalid()) {
            LogUtil.b("VIPCard_MemberSleepCardReader", "content is not complete");
            return;
        }
        int color = a().getColor(R.color._2131299327_res_0x7f090bff);
        doF_(d2, Integer.valueOf(color), this.m, Integer.valueOf(a().getColor(R.color._2131299326_res_0x7f090bfe)), new npt() { // from class: com.huawei.ui.main.stories.discover.provider.MemberSleepCardReader.1
            @Override // defpackage.npt, com.huawei.ui.commonui.listener.OnClickSectionListener
            public void onClick(int i) {
                MemberSleepCardReader.this.b(i, d2);
            }
        });
    }

    @Override // com.huawei.ui.main.stories.discover.provider.MemberCardReader, com.huawei.ui.main.stories.discover.provider.MemberBaseCardReader
    public void unRegisterListener() {
        LogUtil.a("VIPCard_MemberSleepCardReader", "unRegisterListener");
        super.unRegisterListener();
        super.t();
        ObserverManagerUtil.e(this.g, "MonthProblemRefresh");
        y();
    }

    private long e(int i, long j, boolean z) {
        Date time;
        Calendar calendar = Calendar.getInstance();
        if (j == 0) {
            calendar.set(calendar.get(1), calendar.get(2), calendar.get(5) + i, 0, 0, 0);
            time = calendar.getTime();
        } else {
            calendar.setTime(new Date(j));
            calendar.add(5, i);
            time = calendar.getTime();
        }
        if (z) {
            return jec.n(time);
        }
        return jec.k(time);
    }

    @Override // com.huawei.ui.main.stories.discover.provider.MemberCardReader, com.huawei.ui.main.stories.discover.provider.MemberBaseCardReader
    public void queryLabel() {
        d(e() && e("sleep_dmp"));
        if (b() == 0) {
            if (k()) {
                v();
                c(2);
            } else {
                LogUtil.a("VIPCard_MemberSleepCardReader", "privacy status is not changed");
            }
            LogUtil.a("VIPCard_MemberSleepCardReader", "sleep card reader:no privacy");
            return;
        }
        if (!l() && m() && !k() && !this.j) {
            LogUtil.a("VIPCard_MemberSleepCardReader", "no need to label");
            return;
        }
        y();
        LogUtil.a("VIPCard_MemberSleepCardReader", "start search");
        x();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void u() {
        kpi.a().c(e(-6, 0L, true), 3, ConfigMapDefValues.DEFAULT_APPLIST_INTERVAL, 7, new d());
    }

    static class a implements HiDataReadResultListener {
        private WeakReference<MemberSleepCardReader> c;

        a(MemberSleepCardReader memberSleepCardReader) {
            this.c = new WeakReference<>(memberSleepCardReader);
        }

        /* JADX WARN: Removed duplicated region for block: B:11:0x0096  */
        /* JADX WARN: Removed duplicated region for block: B:13:0x009a  */
        @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public void onResult(java.lang.Object r5, int r6, int r7) {
            /*
                r4 = this;
                java.lang.String r7 = "queryMonthResult onResult, data: "
                java.lang.Object[] r7 = new java.lang.Object[]{r7, r5}
                java.lang.String r0 = "VIPCard_MemberSleepCardReader"
                com.huawei.hwlogsmodel.LogUtil.a(r0, r7)
                java.lang.ref.WeakReference<com.huawei.ui.main.stories.discover.provider.MemberSleepCardReader> r7 = r4.c
                java.lang.Object r7 = r7.get()
                com.huawei.ui.main.stories.discover.provider.MemberSleepCardReader r7 = (com.huawei.ui.main.stories.discover.provider.MemberSleepCardReader) r7
                if (r7 != 0) goto L1f
                java.lang.String r5 = "memberSleepCardReader is null"
                java.lang.Object[] r5 = new java.lang.Object[]{r5}
                com.huawei.hwlogsmodel.LogUtil.b(r0, r5)
                return
            L1f:
                r1 = -1
                if (r6 == 0) goto L30
                java.lang.String r5 = "errorCode: "
                java.lang.Integer r6 = java.lang.Integer.valueOf(r6)
                java.lang.Object[] r5 = new java.lang.Object[]{r5, r6}
                com.huawei.hwlogsmodel.LogUtil.h(r0, r5)
                goto L93
            L30:
                boolean r6 = r5 instanceof android.util.SparseArray
                if (r6 != 0) goto L3e
                java.lang.String r5 = "data is not SparseArray"
                java.lang.Object[] r5 = new java.lang.Object[]{r5}
                com.huawei.hwlogsmodel.LogUtil.h(r0, r5)
                goto L93
            L3e:
                android.util.SparseArray r5 = (android.util.SparseArray) r5
                int r6 = r5.size()
                if (r6 > 0) goto L4f
                java.lang.String r6 = "map.size() <= 0"
                java.lang.Object[] r6 = new java.lang.Object[]{r6}
                com.huawei.hwlogsmodel.LogUtil.h(r0, r6)
            L4f:
                java.lang.String r6 = "queryMonthProcessResult success"
                java.lang.Object[] r6 = new java.lang.Object[]{r6}
                com.huawei.hwlogsmodel.LogUtil.a(r0, r6)
                long r2 = java.lang.System.currentTimeMillis()
                long r2 = defpackage.jdl.f(r2)
                r6 = 1
                java.util.Map r5 = defpackage.pob.drk_(r5, r2, r6)
                boolean r6 = r5.isEmpty()
                if (r6 == 0) goto L75
                java.lang.String r5 = "resMap is null"
                java.lang.Object[] r5 = new java.lang.Object[]{r5}
                com.huawei.hwlogsmodel.LogUtil.h(r0, r5)
                goto L93
            L75:
                java.lang.String r6 = "monthlySleepProblem"
                java.lang.Object r2 = r5.get(r6)
                boolean r2 = r2 instanceof java.lang.Integer
                if (r2 == 0) goto L8a
                java.lang.Object r5 = r5.get(r6)
                java.lang.Integer r5 = (java.lang.Integer) r5
                int r5 = r5.intValue()
                goto L94
            L8a:
                java.lang.String r5 = "resMap has no MONTHLY_SLEEP_PROBLEM"
                java.lang.Object[] r5 = new java.lang.Object[]{r5}
                com.huawei.hwlogsmodel.LogUtil.h(r0, r5)
            L93:
                r5 = r1
            L94:
                if (r5 != r1) goto L9a
                com.huawei.ui.main.stories.discover.provider.MemberSleepCardReader.e(r7)
                goto La1
            L9a:
                com.huawei.ui.main.stories.discover.provider.MemberSleepCardReader.e(r7, r5)
                r5 = 0
                com.huawei.ui.main.stories.discover.provider.MemberSleepCardReader.d(r7, r5)
            La1:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.huawei.ui.main.stories.discover.provider.MemberSleepCardReader.a.onResult(java.lang.Object, int, int):void");
        }

        @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
        public void onResultIntent(int i, Object obj, int i2, int i3) {
            LogUtil.a("VIPCard_MemberSleepCardReader", "queryMonthProcessResult intentType ", Integer.valueOf(i), " errorCode ", Integer.valueOf(i2));
        }
    }

    private void x() {
        pob.d((HiDataReadResultListener) new a(this), jdl.f(System.currentTimeMillis()), true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(int i) {
        LogUtil.a("VIPCard_MemberSleepCardReader", "whenAfterEvaluation");
        this.f = "startPlan";
        c(b(i), "[\\s\\S]*");
    }

    private String b(int i) {
        LogUtil.a("VIPCard_MemberSleepCardReader", "getMonthStr:", Integer.valueOf(i));
        if (i == 0) {
            return a().getString(R$string.IDS_sleep_no_problem);
        }
        if (i == 1) {
            return a().getString(R$string.IDS_sleep_lowquality_problem);
        }
        if (i == 2) {
            return a().getString(R$string.IDS_sleep_difficulty_fall_asleep);
        }
        if (i == 3) {
            return a().getString(R$string.IDS_sleep_insuf_sleep_duration);
        }
        if (i != 4) {
            return i != 5 ? "" : a().getString(R$string.IDS_sleep_difficulty_sleep_maintain);
        }
        return a().getString(R$string.IDS_sleep_irregular_sleep_schedule);
    }

    @Override // com.huawei.ui.main.stories.discover.provider.MemberCardReader, com.huawei.ui.main.stories.discover.provider.MemberBaseCardReader
    public void setDataChangeTrue() {
        super.setDataChangeTrue();
        this.j = true;
    }

    private void y() {
        LogUtil.a("VIPCard_MemberSleepCardReader", "resetParams");
        super.r();
        this.f = null;
        this.m = null;
        this.j = false;
        List<SleepTotalData> list = this.l;
        if (list != null) {
            list.clear();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void s() {
        LogUtil.a("VIPCard_MemberSleepCardReader", "fliterScenario");
        if (koq.b(this.l)) {
            LogUtil.a("VIPCard_MemberSleepCardReader", "no data on sleep card");
            v();
            c(0);
            return;
        }
        ArrayList arrayList = new ArrayList();
        for (SleepTotalData sleepTotalData : this.l) {
            if (nrq.b(sleepTotalData.getDeivceCategory()) || sleepTotalData.getDeivceCategory() == Integer.parseInt("054", 16)) {
                LogUtil.a("VIPCard_MemberSleepCardReader", "phone or pillow sleep, but now no pillo online");
                arrayList.add(Long.valueOf(sleepTotalData.getSleepDayTime()));
            } else if (sleepTotalData.getDeivceCategory() == Integer.parseInt("001", 16)) {
                LogUtil.a("VIPCard_MemberSleepCardReader", "manual sleep, need to calculate score");
                int intValue = new BigDecimal((pxb.a(sleepTotalData.getManualOnTime()) * 0.5f) + (pxb.a(sleepTotalData.getManualBedTime() == 0 ? 0.0f : sleepTotalData.getManualOnTime() / sleepTotalData.getManualBedTime()) * 0.3f) + (pxb.d(sleepTotalData.getManualFallTime()) * 0.2f)).intValue();
                sleepTotalData.setScore(intValue);
                sleepTotalData.setSleepLevel(pqr.f(intValue));
            } else {
                LogUtil.a("VIPCard_MemberSleepCardReader", "other sleep");
            }
        }
        if (koq.b(arrayList)) {
            c(0);
        } else {
            e(arrayList);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(int i) {
        if (i != 0) {
            if (i == 2) {
                LogUtil.a("VIPCard_MemberSleepCardReader", "init resources already");
                if (TextUtils.isEmpty(this.f) || !n()) {
                    return;
                }
                LogUtil.a("VIPCard_MemberSleepCardReader", "notify listener");
                q();
                return;
            }
            LogUtil.a("VIPCard_MemberSleepCardReader", "other message,labelName ", this.f);
            return;
        }
        LogUtil.a("VIPCard_MemberSleepCardReader", "wear scentific sleep");
        if (!TextUtils.isEmpty(this.f) && this.f.equals("startPlan")) {
            LogUtil.a("VIPCard_MemberSleepCardReader", "month problem get");
        } else if (koq.c(this.l)) {
            p();
        } else {
            LogUtil.b("VIPCard_MemberSleepCardReader", "mSleepTotalDataList is empty");
        }
        if (n()) {
            LogUtil.a("VIPCard_MemberSleepCardReader", "notify listener");
            q();
        }
    }

    private void e(List<Long> list) {
        LogUtil.a("VIPCard_MemberSleepCardReader", "requestExtraSleep size:", list.toString());
        ArrayList arrayList = new ArrayList(list.size());
        Iterator<Long> it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(qnl.d(it.next().longValue(), 1));
        }
        qnl.e(qnl.a(arrayList), new e(0, this));
    }

    private void e(SleepTotalData sleepTotalData, List<Integer> list) {
        if (c(sleepTotalData)) {
            list.add(Integer.valueOf(sleepTotalData.getManualFallTime()));
        } else if (sleepTotalData.getFallTime() == 0) {
            list.add(Integer.valueOf(sleepTotalData.getCommonFallTime()));
        } else {
            list.add(Integer.valueOf(sleepTotalData.getFallTime()));
        }
    }

    private void p() {
        LogUtil.a("VIPCard_MemberSleepCardReader", "calculateAvgToGetLabel");
        int size = this.l.size();
        ArrayList arrayList = new ArrayList(size);
        ArrayList arrayList2 = new ArrayList(size);
        ArrayList arrayList3 = new ArrayList(size);
        ArrayList arrayList4 = new ArrayList(size);
        ArrayList arrayList5 = new ArrayList(size);
        int i = 0;
        int i2 = 0;
        int i3 = 0;
        for (SleepTotalData sleepTotalData : this.l) {
            if (sleepTotalData.getTotalSleepTime() != 0) {
                arrayList.add(Integer.valueOf(sleepTotalData.getTotalSleepTime()));
                e(sleepTotalData, arrayList4);
                if (sleepTotalData.getSleepLevel() != null) {
                    i++;
                }
                if (a().getString(R$string.IDS_excellent).equals(sleepTotalData.getSleepLevel()) || a().getString(R$string.IDS_good).equals(sleepTotalData.getSleepLevel())) {
                    i2++;
                }
                if (a().getString(R$string.IDS_general).equals(sleepTotalData.getSleepLevel()) || a().getString(R$string.IDS_need_improving).equals(sleepTotalData.getSleepLevel())) {
                    i3++;
                }
                if (e(sleepTotalData)) {
                    arrayList2.add(Integer.valueOf(sleepTotalData.getDeepSleepTimePercent()));
                    arrayList3.add(Integer.valueOf(sleepTotalData.getShallowSleepTimePercent()));
                    LogUtil.a("VIPCard_MemberSleepCardReader", "this sleep scored ", Boolean.valueOf(sleepTotalData.getType() != 30 ? arrayList5.add(Integer.valueOf(sleepTotalData.getScore())) : false));
                }
            }
        }
        HashMap<String, Integer> hashMap = new HashMap<>(9);
        hashMap.put("shortSleepers", Integer.valueOf(d(arrayList)));
        hashMap.put("inadequateDeepSleep", Integer.valueOf(d(arrayList2)));
        hashMap.put("tooMuchLightSleep", Integer.valueOf(d(arrayList3)));
        hashMap.put("sleepLate", Integer.valueOf(d(arrayList4)));
        hashMap.put("sleepScore", Integer.valueOf(d(arrayList5)));
        hashMap.put("sleepScores", Integer.valueOf(arrayList5.size()));
        hashMap.put("sleepLevel", Integer.valueOf(i));
        hashMap.put("goodSleep", Integer.valueOf(i2));
        hashMap.put("generalSleep", Integer.valueOf(i3));
        LogUtil.a("VIPCard_MemberSleepCardReader", "metric is ", hashMap.entrySet().toString());
        a(hashMap);
    }

    private boolean e(SleepTotalData sleepTotalData) {
        return sleepTotalData.getDeivceCategory() == Integer.parseInt("06D", 16) || sleepTotalData.getDeivceCategory() == Integer.parseInt("06E", 16);
    }

    private boolean c(SleepTotalData sleepTotalData) {
        return sleepTotalData.getDeivceCategory() == Integer.parseInt("001", 16);
    }

    private int d(List<Integer> list) {
        int i = 0;
        if (koq.b(list)) {
            return 0;
        }
        Iterator<Integer> it = list.iterator();
        while (it.hasNext()) {
            i += it.next().intValue();
        }
        return Math.round(i / list.size());
    }

    private void a(HashMap<String, Integer> hashMap) {
        if (hashMap.get("shortSleepers").intValue() <= 360 && hashMap.get("shortSleepers").intValue() != 0) {
            aa();
            return;
        }
        if (hashMap.get("inadequateDeepSleep").intValue() != 0 && (hashMap.get("inadequateDeepSleep").intValue() > 60 || hashMap.get("inadequateDeepSleep").intValue() < 20)) {
            w();
            return;
        }
        if (hashMap.get("sleepLate").intValue() >= 240) {
            ac();
            return;
        }
        if (hashMap.get("tooMuchLightSleep").intValue() != 0 && hashMap.get("tooMuchLightSleep").intValue() >= 55) {
            z();
        } else if (koq.c(this.l)) {
            b(hashMap);
        } else {
            v();
        }
    }

    private void aa() {
        LogUtil.a("VIPCard_MemberSleepCardReader", "whenShortSleepers");
        this.f = "shortSleepers";
        c(a().getResources().getString(R$string.IDS_member_sleep_recommend_new, a().getResources().getString(R$string.IDS_member_sleep_add_record, UnitUtil.e(6.0d, 1, 0), UnitUtil.e(10.0d, 1, 0))), (String) null);
    }

    private void w() {
        LogUtil.a("VIPCard_MemberSleepCardReader", "whenInadequateDeepSleep");
        this.f = "inadequateDeepSleep";
        c(a().getResources().getString(R$string.IDS_member_sleep_inadequate_deep_sleep_new, UnitUtil.e(20.0d, 2, 0), UnitUtil.e(60.0d, 2, 0)), (String) null);
    }

    private void ac() {
        LogUtil.a("VIPCard_MemberSleepCardReader", "whenSleepLate");
        this.f = "sleepLate";
        c(a().getResources().getString(R$string.IDS_member_sleep_sleep_late_new, UnitUtil.e(12.0d, 1, 0)), (String) null);
    }

    private void z() {
        LogUtil.a("VIPCard_MemberSleepCardReader", "whenTooMuchLightSleep");
        this.f = "tooMuchLightSleep";
        String string = a().getResources().getString(R$string.IDS_member_sleep_too_much_light_sleep_new, UnitUtil.e(55.0d, 2, 0));
        c(a().getResources().getString(R$string.IDS_member_sleep_recommend_new, string), string);
    }

    private void b(HashMap<String, Integer> hashMap) {
        if ((hashMap.get("sleepScores").intValue() >= hashMap.get("sleepLevel").intValue() && hashMap.get("sleepScore").intValue() > 80) || (hashMap.get("sleepScores").intValue() < hashMap.get("sleepLevel").intValue() && hashMap.get("goodSleep").intValue() > hashMap.get("generalSleep").intValue())) {
            LogUtil.a("VIPCard_MemberSleepCardReader", "whenGreatSleepPoints");
            this.f = "greatSleepPoints";
            this.m = null;
            return;
        }
        v();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void v() {
        String str;
        LogUtil.a("VIPCard_MemberSleepCardReader", "whenOtherSleep");
        this.f = "addRecord";
        try {
            str = a().getResources().getString(R$string.IDS_member_sleep_add_record, UnitUtil.e(6.0d, 1, 0), UnitUtil.e(10.0d, 1, 0));
        } catch (IllegalFormatConversionException unused) {
            ReleaseLogUtil.c("VIPCard_MemberSleepCardReader", "whenOtherSleep string format exception");
            str = "";
        }
        c(str, (String) null);
    }

    private void c(String str, String str2) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("VIPCard_MemberSleepCardReader", "str is empty");
        } else if (TextUtils.isEmpty(str2)) {
            this.m = UnitUtil.bCR_(a(), "[\\d:\\-%]", str, R.style.member_card_sleep_subtitle_number, R.style.weight_management_goal_normal);
        } else {
            this.m = UnitUtil.bCR_(a(), str2, str, R.style.member_card_sleep_subtitle_number, R.style.weight_management_goal_normal);
        }
    }

    @Override // com.huawei.ui.main.stories.discover.provider.MemberCardReader, com.huawei.ui.main.stories.discover.provider.MemberBaseCardReader
    public void setOrder(int i) {
        super.setOrder(i);
    }

    static class e implements HiDataReadResultListener {
        private int c;
        private WeakReference<MemberSleepCardReader> d;

        @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
        public void onResultIntent(int i, Object obj, int i2, int i3) {
        }

        public e(int i, MemberSleepCardReader memberSleepCardReader) {
            this.c = i;
            this.d = new WeakReference<>(memberSleepCardReader);
        }

        @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
        public void onResult(Object obj, int i, int i2) {
            MemberSleepCardReader memberSleepCardReader = this.d.get();
            if (memberSleepCardReader == null) {
                LogUtil.h("VIPCard_MemberSleepCardReader", "PhonePillowDataChangeListener activity is null");
                return;
            }
            LogUtil.a("VIPCard_MemberSleepCardReader", "new device summary respone!");
            List<SleepTotalData> list = memberSleepCardReader.l;
            if (list == null) {
                LogUtil.b("VIPCard_MemberSleepCardReader", "sleepTotalDatas is null");
                memberSleepCardReader.c(0);
                return;
            }
            if (!(obj instanceof SparseArray)) {
                LogUtil.b("VIPCard_MemberSleepCardReader", "data is not instanceof SparseArray");
                memberSleepCardReader.c(0);
                return;
            }
            if (koq.b(list)) {
                LogUtil.b("VIPCard_MemberSleepCardReader", "mWeakReference.get() is empty");
                memberSleepCardReader.c(0);
                return;
            }
            ArrayList arrayList = new ArrayList(list.size());
            for (SleepTotalData sleepTotalData : list) {
                if (nrq.b(sleepTotalData.getDeivceCategory()) || sleepTotalData.getDeivceCategory() == Integer.parseInt("054", 16)) {
                    arrayList.add(sleepTotalData);
                }
            }
            if (koq.b(arrayList)) {
                LogUtil.a("VIPCard_MemberSleepCardReader", "sleepTotalData is null");
                memberSleepCardReader.c(0);
                return;
            }
            if (this.c == 0) {
                memberSleepCardReader.d(obj, arrayList);
            }
            if (this.c == 1) {
                memberSleepCardReader.c(obj, arrayList);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(Object obj, List<SleepTotalData> list) {
        LogUtil.a("VIPCard_MemberSleepCardReader", "processQueryDeviceId");
        ArrayList arrayList = new ArrayList(list.size());
        Iterator<SleepTotalData> it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(Long.valueOf(it.next().getSleepDayTime()));
        }
        if (obj == null || !(obj instanceof SparseArray)) {
            return;
        }
        List list2 = (List) ((SparseArray) obj).get(22100);
        ArrayList arrayList2 = new ArrayList(arrayList.size());
        int i = 0;
        for (Object obj2 : list2) {
            if (obj2 instanceof HiHealthData) {
                LogUtil.a("VIPCard_MemberSleepCardReader", "construct phone sleep options with deviceId,phoneTimes=", arrayList.toString());
                long longValue = ((Long) arrayList.get(i)).longValue();
                long e2 = e(-1, longValue, true) * 1000;
                long e3 = e(1, longValue, false) * 1000;
                LogUtil.a("VIPCard_MemberSleepCardReader", "yesterday=", Long.valueOf(e2), " today=", Long.valueOf(longValue), " tomorrow=", Long.valueOf(e3));
                arrayList2.add(qnl.b(((HiHealthData) obj2).getString("device_uniquecode"), -1, e2, e3, h));
            } else {
                LogUtil.a("VIPCard_MemberSleepCardReader", "obj is not instanceof HiHealthData");
            }
            i++;
        }
        if (koq.c(arrayList2)) {
            qnl.e(qnl.a(arrayList2), new e(1, this));
        } else {
            c(0);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(Object obj, List<SleepTotalData> list) {
        LogUtil.a("VIPCard_MemberSleepCardReader", "processQueryPhoneSleep");
        SparseArray sparseArray = (SparseArray) obj;
        for (SleepTotalData sleepTotalData : list) {
            long e2 = jec.e(new Date(sleepTotalData.getSleepDayTime())) * 1000;
            long j = ((e2 / 1000) + k.b.m) * 1000;
            int dGn_ = qnl.dGn_(sparseArray, DicDataTypeUtil.DataType.SLEEP_SCORE.value(), e2, j);
            int dGn_2 = qnl.dGn_(sparseArray, DicDataTypeUtil.DataType.EFFECTIVE_SLEEP_DURATION.value(), e2, j);
            long dGo_ = qnl.dGo_(sparseArray, DicDataTypeUtil.DataType.BED_TIME.value(), e2, j);
            String dGq_ = qnl.dGq_(sparseArray, DicDataTypeUtil.DataType.BED_TIME.value());
            sleepTotalData.setScore(dGn_);
            sleepTotalData.setSleepLevel(pqr.f(dGn_));
            sleepTotalData.setTotalSleepTime(dGn_2 / 60);
            sleepTotalData.setFallTime(nrq.e(dGo_, dGq_, 1));
        }
        LogUtil.a("VIPCard_MemberSleepCardReader", "after query phone sleep ", this.l.toString());
        c(0);
    }

    static class d implements IBaseResponseCallback {
        private WeakReference<MemberSleepCardReader> d;

        private d(MemberSleepCardReader memberSleepCardReader) {
            this.d = new WeakReference<>(memberSleepCardReader);
        }

        @Override // com.huawei.hwbasemgr.IBaseResponseCallback
        /* renamed from: onResponse */
        public void d(int i, Object obj) {
            WeakReference<MemberSleepCardReader> weakReference = this.d;
            if (weakReference == null) {
                LogUtil.h("VIPCard_MemberSleepCardReader", "CoreSleepDataCallback weakReference is null");
                return;
            }
            MemberSleepCardReader memberSleepCardReader = weakReference.get();
            if (memberSleepCardReader == null) {
                LogUtil.h("VIPCard_MemberSleepCardReader", "CoreSleepDataCallback memberSleepCardReader is null");
                return;
            }
            Object[] objArr = new Object[4];
            objArr[0] = "errorcode is ";
            objArr[1] = Integer.valueOf(i);
            objArr[2] = "sleep data is ";
            objArr[3] = obj == null ? null : obj.toString();
            LogUtil.a("VIPCard_MemberSleepCardReader", objArr);
            if (i == 0 && koq.e(obj, SleepTotalData.class)) {
                memberSleepCardReader.l = (List) obj;
                memberSleepCardReader.s();
            } else {
                LogUtil.h("VIPCard_MemberSleepCardReader", "this user do not have sleep data");
                memberSleepCardReader.v();
                memberSleepCardReader.c(2);
            }
        }
    }
}
