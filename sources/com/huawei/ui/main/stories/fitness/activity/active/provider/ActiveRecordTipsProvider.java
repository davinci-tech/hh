package com.huawei.ui.main.stories.fitness.activity.active.provider;

import android.app.Activity;
import android.content.Context;
import android.util.SparseArray;
import com.huawei.haf.design.pattern.Observer;
import com.huawei.haf.design.pattern.ObserverManagerUtil;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.knit.data.BaseKnitDataProvider;
import com.huawei.health.knit.data.MinorProvider;
import com.huawei.health.knit.section.model.SectionBean;
import com.huawei.hihealth.HiDataInsertOption;
import com.huawei.hihealth.HiDataReadOption;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.api.HiHealthManager;
import com.huawei.hihealth.data.listener.HiDataOperateListener;
import com.huawei.hihealth.data.listener.HiDataReadResultListener;
import com.huawei.hihealth.dictionary.utils.DicDataTypeUtil;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.ble.BleConstants;
import com.huawei.threecircle.ActiveTipStringUtils;
import com.huawei.ui.main.R$string;
import com.huawei.ui.main.stories.fitness.activity.active.provider.ActiveRecordTipsProvider;
import defpackage.edr;
import defpackage.ggl;
import defpackage.gso;
import defpackage.ixx;
import defpackage.jdl;
import defpackage.koq;
import defpackage.nrh;
import defpackage.nru;
import defpackage.nsn;
import defpackage.phn;
import defpackage.phs;
import defpackage.pxm;
import defpackage.pxp;
import health.compact.a.CommonUtils;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/* loaded from: classes9.dex */
public class ActiveRecordTipsProvider extends MinorProvider<edr> {

    /* renamed from: a, reason: collision with root package name */
    private boolean f9732a;
    private edr c;
    private SectionBean f;
    private phs g;
    private int h;
    private int e = -1;
    private boolean b = false;
    private boolean d = false;
    private ActiveTipStringUtils.TipType k = ActiveTipStringUtils.TipType.STAND_HOUR;
    private HashMap<String, Object> j = new HashMap<>();
    private final c i = new c(this);

    @Override // com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.KnitDataProvider
    public boolean isActive(Context context) {
        if (!this.d) {
            return false;
        }
        if (a()) {
            return true;
        }
        if (this.f9732a) {
            return CommonUtils.h(nru.b(this.j, "sport_diff_time_key", "0")) >= 3 || (this.e > 0 && !this.b);
        }
        return false;
    }

    @Override // com.huawei.health.knit.data.MinorProvider, com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.KnitDataProvider
    public void loadDefaultData(SectionBean sectionBean) {
        LogUtil.a("SCUI_ActiveRecordTipsProvider", "enter loadDefaultData");
        super.loadDefaultData(sectionBean);
        ObserverManagerUtil.d(this.i, "observer_sport_list_show_tip");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.huawei.health.knit.data.MinorProvider
    /* renamed from: b, reason: merged with bridge method [inline-methods] */
    public void onSetSectionBeanData(final SectionBean sectionBean, final edr edrVar) {
        LogUtil.a("SCUI_ActiveRecordTipsProvider", "onSetSectionBeanData");
        this.f = sectionBean;
        this.c = edrVar;
        boolean d = d(edrVar);
        this.d = d;
        if (!d) {
            sectionBean.e(edrVar);
        } else {
            ThreadPoolManager.d().execute(new Runnable() { // from class: pin
                @Override // java.lang.Runnable
                public final void run() {
                    ActiveRecordTipsProvider.this.e(edrVar, sectionBean);
                }
            });
        }
    }

    public /* synthetic */ void e(edr edrVar, SectionBean sectionBean) {
        this.j.clear();
        b(edrVar);
        sectionBean.e(edrVar);
    }

    @Override // com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.KnitDataProvider
    /* renamed from: b, reason: merged with bridge method [inline-methods] */
    public void parseParams(Context context, HashMap hashMap, edr edrVar) {
        LogUtil.a("SCUI_ActiveRecordTipsProvider", "parseParams");
        hashMap.put("ITEM_TITLE", this.j.get("title_key"));
        hashMap.put("ITEM_SUBTITLE", this.j.get("description_key"));
        hashMap.put("BACKGROUND_IMAGE", this.j.get("icon_key"));
        hashMap.put("RIGHT_BUTTON_TEXT", this.j.get("button_key"));
        if (h()) {
            hashMap.put("LEFT_BUTTON_VISIBILITY", new BaseKnitDataProvider.d() { // from class: com.huawei.ui.main.stories.fitness.activity.active.provider.ActiveRecordTipsProvider.5
                @Override // com.huawei.health.knit.data.BaseKnitDataProvider.d, com.huawei.ui.commonui.listener.OnClickSectionListener
                public void onClick(int i) {
                    super.onClick(i);
                    if (ActiveRecordTipsProvider.this.e()) {
                        ReleaseLogUtil.c("SCUI_ActiveRecordTipsProvider", "click with InvalidResponse");
                    } else {
                        pxm.duY_(BaseApplication.getActivity(), ActiveRecordTipsProvider.this.g.f(), ActiveRecordTipsProvider.this.g.j());
                        pxp.e(ActiveRecordTipsProvider.this.k, 4);
                    }
                }
            });
        } else {
            hashMap.put("LEFT_BUTTON_VISIBILITY", null);
        }
        d(hashMap);
    }

    private void b(edr edrVar) {
        if (!pxm.c()) {
            c(edrVar);
            return;
        }
        this.g = phn.d();
        if (e()) {
            ReleaseLogUtil.d("SCUI_ActiveRecordTipsProvider", "Invalid Response");
            c(edrVar);
            return;
        }
        int c2 = this.g.c();
        int f = this.g.f();
        int i = this.g.i();
        int e2 = this.g.e();
        int g = this.g.g();
        int j = this.g.j();
        ReleaseLogUtil.e("SCUI_ActiveRecordTipsProvider", "period is ", Integer.valueOf(g), " subscription", Integer.valueOf(i));
        if (g == 3) {
            d(ActiveTipStringUtils.TipType.TASK_BONUS_GET, c2, e2, f, j);
            return;
        }
        if (g == 4) {
            d(ActiveTipStringUtils.TipType.TASK_BONUS_USE, c2, this.g.d(), f, j);
            return;
        }
        if (g != 1) {
            c(edrVar);
            return;
        }
        if (i == 2) {
            c(edrVar);
            return;
        }
        if (i == 1) {
            d(edrVar, true, c2, e2, f, j);
        } else if (pxm.e(this.g.h())) {
            c(edrVar);
        } else {
            d(ActiveTipStringUtils.TipType.TASK_SUBSCRIBE, c2, e2, f, j);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean e() {
        phs phsVar = this.g;
        return phsVar == null || phsVar.n();
    }

    private void d(ActiveTipStringUtils.TipType tipType, int i, int i2, int i3, int i4) {
        this.k = tipType;
        this.j = ActiveTipStringUtils.c(0, tipType, i, i2, i3, i4);
        pxp.e(tipType, 0);
    }

    private void c(edr edrVar) {
        d(edrVar, false, 0, 0, 0, 0);
    }

    private void d(edr edrVar, boolean z, int i, int i2, int i3, int i4) {
        int i5;
        int i6;
        int c2 = edrVar.c() - edrVar.e();
        int f = edrVar.f() - edrVar.i();
        int q = edrVar.q() - edrVar.s();
        this.e = q;
        if (q > 0) {
            CountDownLatch countDownLatch = new CountDownLatch(1);
            e(countDownLatch);
            try {
                if (!countDownLatch.await(3000L, TimeUnit.MILLISECONDS)) {
                    LogUtil.h("SCUI_ActiveRecordTipsProvider", "RequestData wait timeout, set IsCurrentHourStand");
                    this.b = true;
                    return;
                }
            } catch (InterruptedException unused) {
                ReleaseLogUtil.c("SCUI_ActiveRecordTipsProvider", "interrupted while waiting for requestData");
            }
        }
        if (this.e > 0 && !this.b) {
            this.k = z ? ActiveTipStringUtils.TipType.TASK_STAND_HOUR : ActiveTipStringUtils.TipType.STAND_HOUR;
            i5 = this.e;
        } else {
            if (f > 0) {
                this.k = z ? ActiveTipStringUtils.TipType.TASK_WORKOUT : ActiveTipStringUtils.TipType.WORKOUT;
                i6 = f;
                HashMap<String, Object> c3 = ActiveTipStringUtils.c(i6, this.k, i, i2, i3, i4);
                this.j = c3;
                int h = CommonUtils.h(nru.b(c3, "sport_type_key", "0"));
                this.h = h;
                pxp.b(0, 0, h, this.k);
                LogUtil.a("SCUI_ActiveRecordTipsProvider", "mDiffCalories: ", Integer.valueOf(c2), " mDiffIntensity: ", Integer.valueOf(f), " mDiffHour: ", Integer.valueOf(this.e), " mIsCurrentHourStand: ", Boolean.valueOf(this.b), " mTipsType:", this.k);
            }
            if (c2 > 0) {
                this.k = z ? ActiveTipStringUtils.TipType.TASK_CALORIES : ActiveTipStringUtils.TipType.CALORIES;
                i5 = c2 * 1000;
            } else {
                LogUtil.h("SCUI_ActiveRecordTipsProvider", "No need to show tips.");
                this.k = ActiveTipStringUtils.TipType.STAND_HOUR;
                this.j.clear();
                return;
            }
        }
        i6 = i5;
        HashMap<String, Object> c32 = ActiveTipStringUtils.c(i6, this.k, i, i2, i3, i4);
        this.j = c32;
        int h2 = CommonUtils.h(nru.b(c32, "sport_type_key", "0"));
        this.h = h2;
        pxp.b(0, 0, h2, this.k);
        LogUtil.a("SCUI_ActiveRecordTipsProvider", "mDiffCalories: ", Integer.valueOf(c2), " mDiffIntensity: ", Integer.valueOf(f), " mDiffHour: ", Integer.valueOf(this.e), " mIsCurrentHourStand: ", Boolean.valueOf(this.b), " mTipsType:", this.k);
    }

    private void e(CountDownLatch countDownLatch) {
        LogUtil.a("SCUI_ActiveRecordTipsProvider", "enter requestCurrentHourData");
        HiDataReadOption hiDataReadOption = new HiDataReadOption();
        long currentTimeMillis = System.currentTimeMillis();
        hiDataReadOption.setStartTime(ggl.a(currentTimeMillis));
        hiDataReadOption.setEndTime(ggl.d(currentTimeMillis));
        hiDataReadOption.setType(new int[]{DicDataTypeUtil.DataType.ACTIVE_HOUR_IS_ACTIVE.value()});
        HiHealthManager.d(BaseApplication.getContext()).readHiHealthData(hiDataReadOption, new e(countDownLatch));
    }

    private boolean d(edr edrVar) {
        if (edrVar == null) {
            LogUtil.h("SCUI_ActiveRecordTipsProvider", "ActiveRecordData is null");
            return false;
        }
        return jdl.ac(edrVar.j());
    }

    private void d(HashMap hashMap) {
        hashMap.put("CLICK_EVENT_LISTENER", new BaseKnitDataProvider.d() { // from class: com.huawei.ui.main.stories.fitness.activity.active.provider.ActiveRecordTipsProvider.2
            @Override // com.huawei.health.knit.data.BaseKnitDataProvider.d, com.huawei.ui.commonui.listener.OnClickSectionListener
            public void onClick(int i) {
                super.onClick(i);
                if (!nsn.a(500)) {
                    ActiveRecordTipsProvider.this.b();
                } else {
                    LogUtil.h("SCUI_ActiveRecordTipsProvider", "onClick isFastClick");
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b() {
        Context context = BaseApplication.getContext();
        LogUtil.a("SCUI_ActiveRecordTipsProvider", "mTipsType is ", this.k);
        switch (AnonymousClass1.b[this.k.ordinal()]) {
            case 1:
            case 2:
                e(context);
                break;
            case 3:
            case 4:
            case 5:
            case 6:
                b(context);
                break;
            case 7:
                pxm.dva_(BaseApplication.getActivity(), this.g);
                break;
            case 8:
                c();
                break;
            case 9:
                pxm.duZ_(BaseApplication.getActivity(), this.g.h());
                break;
            default:
                LogUtil.b("SCUI_ActiveRecordTipsProvider", "tip is error");
                break;
        }
        if (a()) {
            pxp.e(this.k);
        } else {
            pxp.b(1, 0, this.h, this.k);
        }
    }

    /* renamed from: com.huawei.ui.main.stories.fitness.activity.active.provider.ActiveRecordTipsProvider$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] b;

        static {
            int[] iArr = new int[ActiveTipStringUtils.TipType.values().length];
            b = iArr;
            try {
                iArr[ActiveTipStringUtils.TipType.STAND_HOUR.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                b[ActiveTipStringUtils.TipType.TASK_STAND_HOUR.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                b[ActiveTipStringUtils.TipType.WORKOUT.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                b[ActiveTipStringUtils.TipType.TASK_WORKOUT.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                b[ActiveTipStringUtils.TipType.CALORIES.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                b[ActiveTipStringUtils.TipType.TASK_CALORIES.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                b[ActiveTipStringUtils.TipType.TASK_BONUS_GET.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                b[ActiveTipStringUtils.TipType.TASK_BONUS_USE.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                b[ActiveTipStringUtils.TipType.TASK_SUBSCRIBE.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
        }
    }

    private void c() {
        pxp.c();
        pxm.a();
        ObserverManagerUtil.c("observer_refresh_active_record_provider", "");
    }

    private boolean h() {
        return this.k == ActiveTipStringUtils.TipType.TASK_STAND_HOUR || this.k == ActiveTipStringUtils.TipType.TASK_WORKOUT || this.k == ActiveTipStringUtils.TipType.TASK_CALORIES || a();
    }

    private boolean a() {
        return this.k == ActiveTipStringUtils.TipType.TASK_BONUS_GET || this.k == ActiveTipStringUtils.TipType.TASK_SUBSCRIBE || this.k == ActiveTipStringUtils.TipType.TASK_BONUS_USE;
    }

    private void b(Context context) {
        if (gso.e().aTs_(0, gso.e().aTn_(this.h, -1, -1.0f, -1, 2), null, context, null) == 0) {
            a(context);
            if (context instanceof Activity) {
                ((Activity) context).finish();
            }
        }
    }

    private void a(Context context) {
        LogUtil.a("SCUI_ActiveRecordTipsProvider", "doBiType with 1040027");
        HashMap hashMap = new HashMap(3);
        hashMap.put("click", 1);
        hashMap.put(BleConstants.SPORT_TYPE, Integer.valueOf(this.h));
        hashMap.put("from", 2);
        ixx.d().d(context, AnalyticsValue.MOTION_TRACK_1040027.value(), hashMap, 0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(CountDownLatch countDownLatch, Object obj) {
        if (!(obj instanceof SparseArray)) {
            ReleaseLogUtil.d("SCUI_ActiveRecordTipsProvider", "handleQueryActiveData onResult data not instanceof SparseArray.");
            countDownLatch.countDown();
            return;
        }
        SparseArray sparseArray = (SparseArray) obj;
        if (sparseArray.size() <= 0) {
            ReleaseLogUtil.d("SCUI_ActiveRecordTipsProvider", "handleQueryActiveData onResult map is empty.");
            countDownLatch.countDown();
            return;
        }
        Object obj2 = sparseArray.get(DicDataTypeUtil.DataType.ACTIVE_HOUR_IS_ACTIVE.value());
        if (obj2 instanceof List) {
            LogUtil.a("SCUI_ActiveRecordTipsProvider", "handleQueryActiveData onResult dataList size is ", Integer.valueOf(((List) obj2).size()));
            this.b = !koq.b(r5);
        }
        countDownLatch.countDown();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(boolean z) {
        this.f9732a = z;
        this.f.e(this.c);
    }

    private void e(final Context context) {
        HiHealthData hiHealthData = new HiHealthData(DicDataTypeUtil.DataType.ACTIVE_HOUR_IS_ACTIVE.value());
        long currentTimeMillis = System.currentTimeMillis();
        hiHealthData.setTimeInterval(ggl.a(currentTimeMillis), ggl.d(currentTimeMillis));
        hiHealthData.setDeviceUuid("-1");
        hiHealthData.setValue(1);
        ArrayList arrayList = new ArrayList(1);
        arrayList.add(hiHealthData);
        HiDataInsertOption hiDataInsertOption = new HiDataInsertOption();
        hiDataInsertOption.setDatas(arrayList);
        HiHealthManager.d(context).insertHiHealthData(hiDataInsertOption, new HiDataOperateListener() { // from class: com.huawei.ui.main.stories.fitness.activity.active.provider.ActiveRecordTipsProvider.3
            @Override // com.huawei.hihealth.data.listener.HiDataOperateListener
            public void onResult(int i, Object obj) {
                LogUtil.a("SCUI_ActiveRecordTipsProvider", "onResult: insert insertHiHealthData errorCode is ", Integer.valueOf(i));
                if (i == 0) {
                    ObserverManagerUtil.c("observer_refresh_active_record_provider", "");
                    Context context2 = context;
                    nrh.d(context2, context2.getResources().getString(R$string.IDS_active_insert_success));
                    ActiveRecordTipsProvider.this.d();
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d() {
        LogUtil.a("SCUI_ActiveRecordTipsProvider", "doBiType with 1040095");
        HashMap hashMap = new HashMap(2);
        hashMap.put("click", "1");
        hashMap.put("from", 3);
        ixx.d().d(BaseApplication.getContext(), AnalyticsValue.CLICK_THE_MARKER_TO_STAND_1040095.value(), hashMap, 0);
    }

    @Override // com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.IKnitLifeCycle
    public void onDestroy() {
        LogUtil.a("SCUI_ActiveRecordTipsProvider", "enter onDestroy");
        super.onDestroy();
        ObserverManagerUtil.e("observer_sport_list_show_tip");
    }

    static class e implements HiDataReadResultListener {
        private final WeakReference<ActiveRecordTipsProvider> c;
        private final CountDownLatch d;

        @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
        public void onResultIntent(int i, Object obj, int i2, int i3) {
        }

        private e(ActiveRecordTipsProvider activeRecordTipsProvider, CountDownLatch countDownLatch) {
            this.c = new WeakReference<>(activeRecordTipsProvider);
            this.d = countDownLatch;
        }

        @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
        public void onResult(Object obj, int i, int i2) {
            LogUtil.a("SCUI_ActiveRecordTipsProvider", "onResult: requestCurrentHourData data is ", obj);
            ActiveRecordTipsProvider activeRecordTipsProvider = this.c.get();
            if (activeRecordTipsProvider != null) {
                activeRecordTipsProvider.d(this.d, obj);
            } else {
                ReleaseLogUtil.d("SCUI_ActiveRecordTipsProvider", "onResult: provider is null");
            }
        }
    }

    public static class c implements Observer {
        private final WeakReference<ActiveRecordTipsProvider> c;

        c(ActiveRecordTipsProvider activeRecordTipsProvider) {
            this.c = new WeakReference<>(activeRecordTipsProvider);
        }

        @Override // com.huawei.haf.design.pattern.Observer
        public void notify(String str, Object... objArr) {
            if ("observer_sport_list_show_tip".equals(str)) {
                ActiveRecordTipsProvider activeRecordTipsProvider = this.c.get();
                if (activeRecordTipsProvider == null) {
                    ReleaseLogUtil.d("SCUI_ActiveRecordTipsProvider", "activeRecordTipsProvider is null");
                    return;
                }
                if (koq.e(objArr, 0)) {
                    Object obj = objArr[0];
                    if (obj instanceof Boolean) {
                        boolean booleanValue = ((Boolean) obj).booleanValue();
                        LogUtil.a("SCUI_ActiveRecordTipsProvider", "is show tip", Boolean.valueOf(booleanValue));
                        activeRecordTipsProvider.d(booleanValue);
                        return;
                    }
                }
                ReleaseLogUtil.c("SCUI_ActiveRecordTipsProvider", "error param.");
            }
        }
    }
}
