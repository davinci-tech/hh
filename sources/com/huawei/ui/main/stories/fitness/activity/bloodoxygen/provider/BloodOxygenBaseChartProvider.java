package com.huawei.ui.main.stories.fitness.activity.bloodoxygen.provider;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.SparseArray;
import android.view.View;
import com.huawei.haf.design.pattern.Observer;
import com.huawei.haf.design.pattern.ObserverManagerUtil;
import com.huawei.health.R;
import com.huawei.health.knit.data.MajorProvider;
import com.huawei.health.knit.section.listener.FullScreenCallback;
import com.huawei.health.knit.section.listener.OnDataChangedListener;
import com.huawei.health.knit.section.listener.OnMarkViewTextNotify;
import com.huawei.health.knit.section.listener.OnXRangeTextCallback;
import com.huawei.health.knit.section.model.SectionBean;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hihealth.HiDataReadOption;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.api.HiHealthNativeApi;
import com.huawei.hihealth.data.listener.HiDataReadResultListener;
import com.huawei.hihealth.dictionary.utils.DicDataTypeUtil;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.ui.commonui.base.CommonUiBaseResponse;
import com.huawei.ui.commonui.calendarview.HealthCalendar;
import com.huawei.ui.commonui.calendarview.HealthCalendarActivity;
import com.huawei.ui.commonui.calendarview.HealthDataMarkDateTrigger;
import com.huawei.ui.commonui.linechart.common.DataInfos;
import com.huawei.ui.commonui.listener.OnClickSectionListener;
import com.huawei.ui.main.stories.fitness.activity.bloodoxygen.KnitBloodOxygenDetailActivity;
import com.huawei.ui.main.stories.fitness.activity.bloodoxygen.detailactivity.HorizontalBloodOxygenDayActivity;
import com.huawei.ui.main.stories.fitness.activity.bloodoxygen.provider.BloodOxygenBaseChartProvider;
import com.huawei.ui.main.stories.fitness.views.base.chart.ObserveredClassifiedView;
import com.huawei.ui.main.stories.fitness.views.bloodoxygen.BloodOxygenModuleBarChartHolder;
import com.huawei.ui.main.stories.utils.LastTimeHealthDataReader;
import defpackage.efb;
import defpackage.gib;
import defpackage.koq;
import defpackage.nom;
import defpackage.pjh;
import defpackage.pkr;
import defpackage.qrp;
import defpackage.scg;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.UnitUtil;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/* loaded from: classes9.dex */
public abstract class BloodOxygenBaseChartProvider extends MajorProvider<pkr> {
    private static final int SET_DATA = 1;
    private static final String SPANNABLE_STRING_REGULAR = "[\\d]";
    private static final String TAG = "BloodOxygenBaseChartProvider";
    private pkr mBelowData;
    private HealthCalendar mCalendar;
    private CommonUiBaseResponse mCommonUiBaseResponse;
    private String mCursorTime;
    private String mCursorValue;
    private LastTimeHealthDataReader<KnitBloodOxygenDetailActivity> mLastTimeHealthDataReader;
    private Observer mObserver;
    private Long mRefleshTimestamp;
    private SectionBean mSectionBean;
    private int mStartTimestamp;
    private String mTextDate;
    private Date lastReqDate = new Date();
    private boolean isFirst = true;
    private b mHandler = new b(this);
    private BloodOxygenModuleBarChartHolder mBloodOxygenHolder = new BloodOxygenModuleBarChartHolder(BaseApplication.getContext());

    protected abstract DataInfos getDataInfo();

    protected abstract int getStartTimestamp(long j2);

    @Override // com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.KnitDataProvider
    public boolean isActive(Context context) {
        return true;
    }

    @Override // com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.KnitDataProvider
    public /* bridge */ /* synthetic */ void parseParams(Context context, HashMap hashMap, Object obj) {
        parseParams(context, (HashMap<String, Object>) hashMap, (pkr) obj);
    }

    static class b extends Handler {
        private final WeakReference<BloodOxygenBaseChartProvider> b;

        public b(BloodOxygenBaseChartProvider bloodOxygenBaseChartProvider) {
            this.b = new WeakReference<>(bloodOxygenBaseChartProvider);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            if (message == null) {
                return;
            }
            super.handleMessage(message);
            BloodOxygenBaseChartProvider bloodOxygenBaseChartProvider = this.b.get();
            if (message.what != 1) {
                return;
            }
            bloodOxygenBaseChartProvider.mSectionBean.e(bloodOxygenBaseChartProvider.mSectionBean.e());
        }
    }

    public BloodOxygenBaseChartProvider() {
        this.mStartTimestamp = 0;
        this.mStartTimestamp = getStartTimestamp(System.currentTimeMillis());
        if (BaseApplication.getActivity() instanceof KnitBloodOxygenDetailActivity) {
            if (this.mLastTimeHealthDataReader == null) {
                this.mLastTimeHealthDataReader = new LastTimeHealthDataReader<>((KnitBloodOxygenDetailActivity) BaseApplication.getActivity(), new a());
            }
            this.mLastTimeHealthDataReader.b(LastTimeHealthDataReader.CardData.BLOOD_OXYGEN);
        }
    }

    @Override // com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.KnitDataProvider
    public void loadDefaultData(SectionBean sectionBean) {
        this.mSectionBean = sectionBean;
        this.mBelowData = new pkr();
    }

    @Override // com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.KnitDataProvider
    public void loadData(Context context, SectionBean sectionBean) {
        LogUtil.b(TAG, "loadData");
        Observer observer = new Observer() { // from class: com.huawei.ui.main.stories.fitness.activity.bloodoxygen.provider.BloodOxygenBaseChartProvider.2
            @Override // com.huawei.haf.design.pattern.Observer
            public void notify(String str, Object... objArr) {
                if (koq.e(objArr, 0)) {
                    Object obj = objArr[0];
                    if (obj instanceof Boolean) {
                        BloodOxygenBaseChartProvider.this.mBelowData.e(((Boolean) obj).booleanValue());
                        BloodOxygenBaseChartProvider bloodOxygenBaseChartProvider = BloodOxygenBaseChartProvider.this;
                        bloodOxygenBaseChartProvider.notifyMinorProviders(bloodOxygenBaseChartProvider.mBelowData);
                    }
                }
            }
        };
        this.mObserver = observer;
        ObserverManagerUtil.d(observer, "ALTITUDE_SWITCH_OPEN_CLOSE");
    }

    public void parseParams(Context context, HashMap<String, Object> hashMap, pkr pkrVar) {
        LogUtil.b(TAG, "parseParams");
        hashMap.clear();
        hashMap.put("BAR_DATA_INFOS", getDataInfo());
        hashMap.put("BAR_CHART_DATE_TEXT", this.mTextDate);
        hashMap.put("BAR_CHART_PERIOD_STRING", this.mCursorTime);
        hashMap.put("BAR_CHART_VALUE", this.mCursorValue);
        hashMap.put("FULL_SCREEN_IMAGE", Integer.valueOf(R.drawable._2131429959_res_0x7f0b0a47));
        hashMap.put("BAR_COMMON_START_TIME", Integer.valueOf(this.mStartTimestamp));
        hashMap.put("BAR_COMMON_REFLESH_TIME", this.mRefleshTimestamp);
        hashMap.put("BAR_COMMON_CHART_HOLDER", this.mBloodOxygenHolder);
        setOnXRangeSetCallback(hashMap);
        setOnDataChangedListener(hashMap);
        setOnMarkViewTextNotify(hashMap);
        setClickListener(hashMap);
        this.mRefleshTimestamp = null;
    }

    @Override // com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.IKnitLifeCycle
    public void onDestroy() {
        Observer observer = this.mObserver;
        if (observer != null) {
            ObserverManagerUtil.e(observer, "ALTITUDE_SWITCH_OPEN_CLOSE");
        }
    }

    private void setOnXRangeSetCallback(HashMap<String, Object> hashMap) {
        hashMap.put("BAR_COMMON_MARK_CHANGE_CALL_BACK", new OnXRangeTextCallback() { // from class: pko
            @Override // com.huawei.health.knit.section.listener.OnXRangeTextCallback
            public final void onRangeShow(int i, int i2, String str) {
                BloodOxygenBaseChartProvider.this.m807xc13b5729(i, i2, str);
            }
        });
    }

    /* JADX WARN: Code restructure failed: missing block: B:26:0x00a0, code lost:
    
        if (r7.get(6) == r12.get(6)) goto L15;
     */
    /* JADX WARN: Code restructure failed: missing block: B:6:0x007a, code lost:
    
        if (r7.get(2) == r12.get(2)) goto L15;
     */
    /* renamed from: lambda$setOnXRangeSetCallback$0$com-huawei-ui-main-stories-fitness-activity-bloodoxygen-provider-BloodOxygenBaseChartProvider, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public /* synthetic */ void m807xc13b5729(int r17, int r18, java.lang.String r19) {
        /*
            r16 = this;
            r0 = r16
            r1 = r17
            r2 = r19
            java.lang.String r3 = "OnXRangeTextCallback startx is"
            java.lang.Integer r4 = java.lang.Integer.valueOf(r17)
            java.lang.Object[] r3 = new java.lang.Object[]{r3, r4}
            java.lang.String r4 = "BloodOxygenBaseChartProvider"
            com.huawei.hwlogsmodel.LogUtil.a(r4, r3)
            java.lang.String r3 = "OnXRangeTextCallback endx is"
            java.lang.Integer r5 = java.lang.Integer.valueOf(r18)
            java.lang.Object[] r3 = new java.lang.Object[]{r3, r5}
            com.huawei.hwlogsmodel.LogUtil.a(r4, r3)
            java.util.concurrent.TimeUnit r3 = java.util.concurrent.TimeUnit.MINUTES
            long r5 = (long) r1
            long r7 = r3.toMillis(r5)
            java.util.Date r3 = new java.util.Date
            r3.<init>(r7)
            java.util.concurrent.TimeUnit r7 = java.util.concurrent.TimeUnit.MINUTES
            r8 = r18
            long r8 = (long) r8
            long r10 = r7.toMillis(r8)
            java.util.Date r7 = new java.util.Date
            r7.<init>(r10)
            java.lang.String r12 = "OnXRangeTextCallback startxDate is"
            java.lang.Object[] r12 = new java.lang.Object[]{r12, r3}
            com.huawei.hwlogsmodel.LogUtil.a(r4, r12)
            java.lang.String r12 = "OnXRangeTextCallback endxDate is"
            java.lang.Object[] r7 = new java.lang.Object[]{r12, r7}
            com.huawei.hwlogsmodel.LogUtil.a(r4, r7)
            com.huawei.ui.commonui.linechart.common.DataInfos r7 = r16.getDataInfo()
            com.huawei.ui.commonui.linechart.common.DataInfos r12 = com.huawei.ui.commonui.linechart.common.DataInfos.BloodOxygenYearDetail
            r14 = 1
            if (r7 != r12) goto L7d
            java.util.Calendar r7 = java.util.Calendar.getInstance()
            r7.setTime(r3)
            java.util.Calendar r12 = java.util.Calendar.getInstance()
            java.util.Date r15 = r0.lastReqDate
            r12.setTime(r15)
            int r15 = r7.get(r14)
            int r13 = r12.get(r14)
            if (r15 != r13) goto La3
            r13 = 2
            int r7 = r7.get(r13)
            int r12 = r12.get(r13)
            if (r7 != r12) goto La3
            goto La4
        L7d:
            java.util.Calendar r7 = java.util.Calendar.getInstance()
            java.util.Calendar r12 = java.util.Calendar.getInstance()
            r7.setTime(r3)
            java.util.Date r13 = r0.lastReqDate
            r12.setTime(r13)
            int r13 = r7.get(r14)
            int r15 = r12.get(r14)
            if (r13 != r15) goto La3
            r13 = 6
            int r7 = r7.get(r13)
            int r12 = r12.get(r13)
            if (r7 != r12) goto La3
            goto La4
        La3:
            r14 = 0
        La4:
            boolean r7 = r0.isInBegin(r3)
            if (r7 == 0) goto Lac
            if (r14 == 0) goto Lb0
        Lac:
            boolean r7 = r0.isFirst
            if (r7 == 0) goto Lcb
        Lb0:
            r0.lastReqDate = r3
            java.util.concurrent.TimeUnit r3 = java.util.concurrent.TimeUnit.MINUTES
            long r12 = r3.toMillis(r5)
            java.lang.String r3 = "OnXRangeTextCallback startTime is"
            java.lang.Long r7 = java.lang.Long.valueOf(r12)
            java.lang.Object[] r3 = new java.lang.Object[]{r3, r7}
            com.huawei.hwlogsmodel.LogUtil.a(r4, r3)
            r0.requestDayOxygenData(r12, r10)
            r3 = 0
            r0.isFirst = r3
        Lcb:
            java.lang.String r3 = r0.mTextDate
            boolean r3 = r2.equals(r3)
            if (r3 == 0) goto Ld4
            return
        Ld4:
            r0.mTextDate = r2
            java.util.concurrent.TimeUnit r2 = java.util.concurrent.TimeUnit.MINUTES
            long r2 = r2.toMillis(r5)
            java.util.concurrent.TimeUnit r4 = java.util.concurrent.TimeUnit.MINUTES
            long r4 = r4.toMillis(r8)
            boolean r2 = defpackage.nsj.a(r2, r4)
            if (r2 == 0) goto Lf0
            com.huawei.ui.commonui.calendarview.HealthCalendar r2 = r0.mCalendar
            com.huawei.ui.commonui.calendarview.HealthCalendar r1 = defpackage.qrp.a(r2, r1)
            r0.mCalendar = r1
        Lf0:
            r16.sendSetDataMessage()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.ui.main.stories.fitness.activity.bloodoxygen.provider.BloodOxygenBaseChartProvider.m807xc13b5729(int, int, java.lang.String):void");
    }

    private boolean isInBegin(Date date) {
        boolean z = date.getHours() == 0 && date.getMinutes() == 0 && date.getSeconds() == 0;
        if (getDataInfo() != DataInfos.BloodOxygenYearDetail) {
            return z;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(5) == 1 && z;
    }

    private void requestDayOxygenData(long j2, long j3) {
        LogUtil.a(TAG, "requestDayOxygenData");
        HiHealthNativeApi.a(BaseApplication.getContext()).readHiHealthData(getReadOption(j2, j3), new j(this, j2, j3));
    }

    static class j implements HiDataReadResultListener {

        /* renamed from: a, reason: collision with root package name */
        private final WeakReference<BloodOxygenBaseChartProvider> f9761a;
        private final long b;
        private final long d;

        @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
        public void onResultIntent(int i, Object obj, int i2, int i3) {
        }

        public j(BloodOxygenBaseChartProvider bloodOxygenBaseChartProvider, long j, long j2) {
            this.f9761a = new WeakReference<>(bloodOxygenBaseChartProvider);
            this.d = j;
            this.b = j2;
        }

        @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
        public void onResult(Object obj, int i, int i2) {
            if (!(obj instanceof SparseArray)) {
                LogUtil.b(BloodOxygenBaseChartProvider.TAG, "data can not convert");
                return;
            }
            SparseArray sparseArray = (SparseArray) obj;
            BloodOxygenBaseChartProvider bloodOxygenBaseChartProvider = this.f9761a.get();
            if (bloodOxygenBaseChartProvider == null) {
                return;
            }
            bloodOxygenBaseChartProvider.mBelowData = new pkr();
            List arrayList = new ArrayList(16);
            if (!efb.c()) {
                Object obj2 = sparseArray.get(2107);
                if (obj2 instanceof List) {
                    arrayList = (List) obj2;
                    LogUtil.a(BloodOxygenBaseChartProvider.TAG, "oxygenRemindValueList.size :", Integer.valueOf(arrayList.size()));
                }
            }
            pjh.dqd_(sparseArray, bloodOxygenBaseChartProvider.mBelowData, true);
            pjh.a(this.d, this.b - 1, bloodOxygenBaseChartProvider.mBelowData, bloodOxygenBaseChartProvider.getDataInfo(), new c(bloodOxygenBaseChartProvider, arrayList));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public HiDataReadOption getLatestOption(long j2, long j3, int i) {
        HiDataReadOption hiDataReadOption = new HiDataReadOption();
        hiDataReadOption.setCount(i);
        hiDataReadOption.setStartTime(j2);
        hiDataReadOption.setEndTime(j3);
        hiDataReadOption.setSortOrder(1);
        return hiDataReadOption;
    }

    private HiDataReadOption getReadOption(long j2, long j3) {
        HiDataReadOption hiDataReadOption = new HiDataReadOption();
        long b2 = gib.b(j2);
        hiDataReadOption.setTimeInterval(b2, j3 - 1);
        LogUtil.a(TAG, "getReadOption startTime:", Long.valueOf(b2), " endTime:", Long.valueOf(j3));
        hiDataReadOption.setSortOrder(1);
        hiDataReadOption.setCount(5);
        hiDataReadOption.setType(new int[]{2107, DicDataTypeUtil.DataType.LAKELOUISE_SCORE.value()});
        return hiDataReadOption;
    }

    private void setOnDataChangedListener(HashMap<String, Object> hashMap) {
        hashMap.put("BAR_COMMON_RANGE_SHOW_CALL_BACK", new OnDataChangedListener() { // from class: pkn
            @Override // com.huawei.health.knit.section.listener.OnDataChangedListener
            public final void onDataChangedListener(float f, float f2) {
                BloodOxygenBaseChartProvider.this.m805x506fdd06(f, f2);
            }
        });
    }

    /* renamed from: lambda$setOnDataChangedListener$1$com-huawei-ui-main-stories-fitness-activity-bloodoxygen-provider-BloodOxygenBaseChartProvider, reason: not valid java name */
    public /* synthetic */ void m805x506fdd06(float f, float f2) {
        String str;
        if (((int) f) != 0 && ((int) f2) != 0) {
            String e2 = UnitUtil.e(f, 2, 0);
            String e3 = UnitUtil.e(f2, 2, 0);
            str = ((Object) UnitUtil.bCR_(BaseApplication.getContext(), SPANNABLE_STRING_REGULAR, e2 + " ", R.style.health_text_chart_emphasize, R.style.health_text_chart_emphasize_small)) + Constants.LINK + ((Object) UnitUtil.bCR_(BaseApplication.getContext(), SPANNABLE_STRING_REGULAR, " " + e3, R.style.health_text_chart_emphasize, R.style.health_text_chart_emphasize_small));
        } else {
            LogUtil.h(TAG, "initViews min = ", Float.valueOf(f), " max = ", Float.valueOf(f2));
            str = "--";
        }
        if (str.equals(this.mCursorValue)) {
            return;
        }
        this.mCursorValue = str;
        sendSetDataMessage();
    }

    private void setOnMarkViewTextNotify(HashMap<String, Object> hashMap) {
        hashMap.put("BAR_COMMON_MARK_TEXT_CALL_BACK", new OnMarkViewTextNotify() { // from class: pkq
            @Override // com.huawei.health.knit.section.listener.OnMarkViewTextNotify
            public final void onTextChanged(String str, List list, float f) {
                BloodOxygenBaseChartProvider.this.m806xbd1d1d99(str, list, f);
            }
        });
    }

    /* renamed from: lambda$setOnMarkViewTextNotify$2$com-huawei-ui-main-stories-fitness-activity-bloodoxygen-provider-BloodOxygenBaseChartProvider, reason: not valid java name */
    public /* synthetic */ void m806xbd1d1d99(String str, List list, float f) {
        if (str.equals(this.mCursorTime)) {
            return;
        }
        this.mCursorTime = str;
        this.mCalendar = qrp.a(this.mCalendar, nom.h((int) f));
        sendSetDataMessage();
    }

    private void setClickListener(HashMap<String, Object> hashMap) {
        final d dVar = new d(this);
        hashMap.put("FULL_SCREEN_CALLBACK", new FullScreenCallback() { // from class: pkl
            @Override // com.huawei.health.knit.section.listener.FullScreenCallback
            public final void click(long j2) {
                BloodOxygenBaseChartProvider.lambda$setClickListener$3(j2);
            }
        });
        hashMap.put("BAR_CHART_CLICK_EVENT", new OnClickSectionListener() { // from class: com.huawei.ui.main.stories.fitness.activity.bloodoxygen.provider.BloodOxygenBaseChartProvider.5
            @Override // com.huawei.ui.commonui.listener.OnClickSectionListener
            public void onClick(int i) {
            }

            @Override // com.huawei.ui.commonui.listener.OnClickSectionListener
            public void onClick(int i, int i2) {
            }

            @Override // com.huawei.ui.commonui.listener.OnClickSectionListener
            public void onClick(int i, String str) {
            }

            @Override // com.huawei.ui.commonui.listener.OnClickSectionListener
            public void onClick(String str) {
                if ("BAR_CHART_CALENDAR_CLICK_EVENT".equals(str)) {
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("calendar", BloodOxygenBaseChartProvider.this.mCalendar);
                    bundle.putParcelable("markDateTrigger", new HealthDataMarkDateTrigger(47204, new int[]{2107}));
                    HealthCalendarActivity.cxj_(BaseApplication.getActivity(), bundle, dVar);
                }
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ViewClickInstrumentation.clickOnView(view);
            }
        });
    }

    public static /* synthetic */ void lambda$setClickListener$3(long j2) {
        Intent intent = new Intent(BaseApplication.getContext(), (Class<?>) HorizontalBloodOxygenDayActivity.class);
        intent.putExtra(ObserveredClassifiedView.JUMP_TIME_ID, nom.l(j2));
        try {
            BaseApplication.getActivity().startActivity(intent);
        } catch (ActivityNotFoundException e2) {
            LogUtil.b(TAG, "ActivityNotFoundException", e2.getMessage());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendSetDataMessage() {
        Message obtain = Message.obtain();
        obtain.what = 1;
        this.mHandler.sendMessage(obtain);
    }

    static class a implements IBaseResponseCallback {

        /* renamed from: a, reason: collision with root package name */
        private final WeakReference<BloodOxygenBaseChartProvider> f9758a;

        private a(BloodOxygenBaseChartProvider bloodOxygenBaseChartProvider) {
            this.f9758a = new WeakReference<>(bloodOxygenBaseChartProvider);
        }

        @Override // com.huawei.hwbasemgr.IBaseResponseCallback
        /* renamed from: onResponse */
        public void d(int i, Object obj) {
            BloodOxygenBaseChartProvider bloodOxygenBaseChartProvider = this.f9758a.get();
            if (bloodOxygenBaseChartProvider == null) {
                LogUtil.h(BloodOxygenBaseChartProvider.TAG, "LastTimeCallback activity is null");
                return;
            }
            if (obj instanceof HiHealthData) {
                HiHealthData hiHealthData = (HiHealthData) obj;
                if (hiHealthData.getStartTime() > 0) {
                    int startTimestamp = bloodOxygenBaseChartProvider.getStartTimestamp(hiHealthData.getStartTime());
                    bloodOxygenBaseChartProvider.mStartTimestamp = startTimestamp;
                    bloodOxygenBaseChartProvider.mCalendar = qrp.a(bloodOxygenBaseChartProvider.mCalendar, startTimestamp);
                }
                LogUtil.a(BloodOxygenBaseChartProvider.TAG, "read last data time from database,mLastTimestamp=", Integer.valueOf(bloodOxygenBaseChartProvider.mStartTimestamp));
            }
            bloodOxygenBaseChartProvider.mSectionBean.e(new pkr());
        }
    }

    static class d implements CommonUiBaseResponse {
        private final WeakReference<BloodOxygenBaseChartProvider> c;

        public d(BloodOxygenBaseChartProvider bloodOxygenBaseChartProvider) {
            this.c = new WeakReference<>(bloodOxygenBaseChartProvider);
        }

        @Override // com.huawei.ui.commonui.base.CommonUiBaseResponse
        public void onResponse(int i, Object obj) {
            BloodOxygenBaseChartProvider bloodOxygenBaseChartProvider = this.c.get();
            if (bloodOxygenBaseChartProvider == null) {
                LogUtil.h(BloodOxygenBaseChartProvider.TAG, "LastTimeCallback activity is null");
                return;
            }
            if (i == 1 && (obj instanceof HealthCalendar)) {
                bloodOxygenBaseChartProvider.mCalendar = (HealthCalendar) obj;
                bloodOxygenBaseChartProvider.mStartTimestamp = nom.f(nom.a(bloodOxygenBaseChartProvider.mCalendar.transformCalendar().getTimeInMillis()));
                bloodOxygenBaseChartProvider.mRefleshTimestamp = Long.valueOf(bloodOxygenBaseChartProvider.mCalendar.transformCalendar().getTimeInMillis());
                bloodOxygenBaseChartProvider.sendSetDataMessage();
            }
        }
    }

    static class c implements IBaseResponseCallback {

        /* renamed from: a, reason: collision with root package name */
        private List<HiHealthData> f9759a;
        private WeakReference<BloodOxygenBaseChartProvider> c;

        public c(BloodOxygenBaseChartProvider bloodOxygenBaseChartProvider, List<HiHealthData> list) {
            this.c = new WeakReference<>(bloodOxygenBaseChartProvider);
            this.f9759a = new ArrayList(list);
        }

        @Override // com.huawei.hwbasemgr.IBaseResponseCallback
        /* renamed from: onResponse */
        public void d(int i, Object obj) {
            BloodOxygenBaseChartProvider bloodOxygenBaseChartProvider = this.c.get();
            if (bloodOxygenBaseChartProvider == null) {
                LogUtil.a(BloodOxygenBaseChartProvider.TAG, "provider is null");
                return;
            }
            String b = SharedPreferenceManager.b(BaseApplication.getContext(), String.valueOf(10000), "BLOOD_OXYGEN_CURVE_STATUS");
            HiDataReadOption latestOption = bloodOxygenBaseChartProvider.getLatestOption(0L, System.currentTimeMillis(), 1);
            latestOption.setType(new int[]{2103});
            HiHealthNativeApi.a(BaseApplication.getContext()).readHiHealthData(latestOption, new e(bloodOxygenBaseChartProvider, this.f9759a, b));
        }
    }

    static class e implements HiDataReadResultListener {

        /* renamed from: a, reason: collision with root package name */
        private List<HiHealthData> f9760a;
        private String b;
        private WeakReference<BloodOxygenBaseChartProvider> e;

        @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
        public void onResultIntent(int i, Object obj, int i2, int i3) {
        }

        public e(BloodOxygenBaseChartProvider bloodOxygenBaseChartProvider, List<HiHealthData> list, String str) {
            this.e = new WeakReference<>(bloodOxygenBaseChartProvider);
            this.f9760a = new ArrayList(list);
            this.b = str;
        }

        @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
        public void onResult(Object obj, int i, int i2) {
            BloodOxygenBaseChartProvider bloodOxygenBaseChartProvider = this.e.get();
            if (bloodOxygenBaseChartProvider == null) {
                LogUtil.a(BloodOxygenBaseChartProvider.TAG, "provider is null");
                return;
            }
            Object obj2 = ((SparseArray) obj).get(2103);
            if (!(obj2 instanceof List)) {
                LogUtil.h(BloodOxygenBaseChartProvider.TAG, "bloodOxygenData not instanceof List");
                return;
            }
            List list = (List) obj2;
            if (list.isEmpty()) {
                LogUtil.h(BloodOxygenBaseChartProvider.TAG, "bloodOxygenData isEmpty");
                return;
            }
            HiHealthData hiHealthData = (HiHealthData) list.get(0);
            int intValue = hiHealthData.getIntValue();
            String e = scg.e(BaseApplication.getContext(), hiHealthData.getLong("start_time"));
            String e2 = UnitUtil.e(intValue, 2, 0);
            bloodOxygenBaseChartProvider.mBelowData.b(e);
            bloodOxygenBaseChartProvider.mBelowData.d(e2);
            bloodOxygenBaseChartProvider.mBelowData.e("true".equals(this.b));
            if (!efb.c()) {
                bloodOxygenBaseChartProvider.mBelowData.a(this.f9760a);
                bloodOxygenBaseChartProvider.mBelowData.e(false);
            }
            bloodOxygenBaseChartProvider.notifyMinorProviders(bloodOxygenBaseChartProvider.mBelowData);
        }
    }
}
