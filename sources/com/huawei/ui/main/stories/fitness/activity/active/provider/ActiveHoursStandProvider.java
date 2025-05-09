package com.huawei.ui.main.stories.fitness.activity.active.provider;

import android.content.Context;
import android.content.Intent;
import android.text.SpannableString;
import android.text.TextUtils;
import android.util.SparseIntArray;
import android.view.View;
import com.huawei.haf.common.utils.CollectionUtils;
import com.huawei.haf.design.pattern.ObserverManagerUtil;
import com.huawei.health.R;
import com.huawei.health.knit.data.MinorProvider;
import com.huawei.health.knit.section.model.SectionBean;
import com.huawei.health.manager.util.TimeUtil;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hihealth.HiDataInsertOption;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.api.HiHealthManager;
import com.huawei.hihealth.data.listener.HiDataOperateListener;
import com.huawei.hihealth.dictionary.utils.DicDataTypeUtil;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwbasemgr.ResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwcommonmodel.utils.DateFormatUtil;
import com.huawei.ui.main.R$string;
import com.huawei.ui.main.stories.fitness.activity.active.ActiveTargetActivity;
import defpackage.ggl;
import defpackage.ixx;
import defpackage.jdl;
import defpackage.nip;
import defpackage.nix;
import defpackage.nrh;
import defpackage.nsf;
import defpackage.nsi;
import defpackage.nsn;
import defpackage.phw;
import health.compact.a.BroadcastManagerUtil;
import health.compact.a.UnitUtil;
import health.compact.a.util.LogUtil;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.TimeZone;

/* loaded from: classes9.dex */
public class ActiveHoursStandProvider extends MinorProvider<phw> {

    /* renamed from: a, reason: collision with root package name */
    private phw f9726a;
    private SectionBean b;
    private boolean c;
    private String e;
    private int f;
    private final SparseIntArray d = new SparseIntArray();
    private int i = 0;

    @Override // com.huawei.health.knit.data.MinorProvider, com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.KnitDataProvider
    public void loadDefaultData(SectionBean sectionBean) {
        super.loadDefaultData(sectionBean);
        this.b = sectionBean;
        phw phwVar = new phw();
        this.f9726a = phwVar;
        this.b.e(phwVar);
    }

    @Override // com.huawei.health.knit.data.MinorProvider, com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.KnitDataProvider
    /* renamed from: loadData */
    public void e(Context context, SectionBean sectionBean) {
        LogUtil.d("SCUI_ActiveHoursStandProvider", "enter loadData");
        c();
    }

    @Override // com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.KnitDataProvider
    /* renamed from: b, reason: merged with bridge method [inline-methods] */
    public void parseParams(final Context context, HashMap hashMap, phw phwVar) {
        if (TextUtils.isEmpty(phwVar.c()) || TextUtils.isEmpty(phwVar.a())) {
            LogUtil.c("SCUI_ActiveHoursStandProvider", "ActiveHoursData data is error");
            return;
        }
        long e2 = phwVar.e();
        g(e2);
        if (!TextUtils.isEmpty(this.e) && this.e.equals(phwVar.a())) {
            d(context, this.c);
        } else {
            d(context, phwVar.j());
            this.f9726a.d(phwVar.c());
        }
        this.f9726a.b(phwVar.a());
        this.f9726a.b(e2);
        LogUtil.d("SCUI_ActiveHoursStandProvider", "parseParams mActiveHoursData is ", this.f9726a);
        hashMap.put("STAND_TODAY_DATA", Boolean.valueOf(TimeUtil.b(e2, System.currentTimeMillis()) || TimeUtil.b(e2, TimeUtil.e(System.currentTimeMillis()))));
        int min = Math.min(phwVar.d() / 4, 24);
        this.i = min;
        this.f9726a.b(min * 4);
        hashMap.put("STAND_PROGRESS", Integer.valueOf(b(e2)));
        hashMap.put("STAND_TIME", (this.f9726a.j() || !i(e2)) ? this.f9726a.a() : "");
        hashMap.put("STAND_PROGRESS_TEXT", dpL_(e2));
        if (!TimeUtil.b(e2, System.currentTimeMillis())) {
            hashMap.put("TITLE", null);
            return;
        }
        hashMap.put("STAND_TIP", d());
        hashMap.put("STAND_CONTENT", this.f9726a.c());
        hashMap.put("RIGHT_ICON_CLICK_EVENT", dpK_(context));
        hashMap.put("CLICK_EVENT_LISTENER", new View.OnClickListener() { // from class: com.huawei.ui.main.stories.fitness.activity.active.provider.ActiveHoursStandProvider.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LogUtil.d("SCUI_ActiveHoursStandProvider", "OnClickListener() onClick mark stand");
                if (!ActiveHoursStandProvider.this.f9726a.j()) {
                    ActiveHoursStandProvider.this.d(context);
                    long e3 = ActiveHoursStandProvider.this.f9726a.e();
                    ActiveHoursStandProvider.this.c(context, e3);
                    ActiveHoursStandProvider.this.e(e3);
                    ViewClickInstrumentation.clickOnView(view);
                    return;
                }
                LogUtil.c("SCUI_ActiveHoursStandProvider", "mark stand card is marked");
                ViewClickInstrumentation.clickOnView(view);
            }
        });
    }

    private void g(long j) {
        if ((j <= 0 || !jdl.ac(j)) && this.d.get(DateFormatUtil.b(j)) <= 0) {
            nix.b(j, "900200009", new c(this));
        }
    }

    private boolean a(long j) {
        return j >= ggl.a(System.currentTimeMillis()) && j <= ggl.d(System.currentTimeMillis());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(long j) {
        HashMap hashMap = new HashMap(3);
        hashMap.put("click", "1");
        hashMap.put("from", Integer.valueOf(getExtra().getInt("from")));
        hashMap.put("type", Integer.valueOf(a(j) ? 1 : 2));
        ixx.d().d(BaseApplication.getContext(), AnalyticsValue.CLICK_THE_MARKER_TO_STAND_1040095.value(), hashMap, 0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(Context context, long j) {
        HiHealthData hiHealthData = new HiHealthData(DicDataTypeUtil.DataType.ACTIVE_HOUR_IS_ACTIVE.value());
        if (!i(j)) {
            LogUtil.c("SCUI_ActiveHoursStandProvider", "the current time cannot be marked");
            return;
        }
        hiHealthData.setTimeInterval(ggl.a(j), ggl.d(j));
        hiHealthData.setDeviceUuid("-1");
        hiHealthData.setValue(1);
        ArrayList arrayList = new ArrayList(1);
        arrayList.add(hiHealthData);
        HiDataInsertOption hiDataInsertOption = new HiDataInsertOption();
        hiDataInsertOption.setDatas(arrayList);
        HiHealthManager.d(context).insertHiHealthData(hiDataInsertOption, new e(this, context, j));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(Context context) {
        Intent intent = new Intent();
        intent.setAction("ENTER_HOUR_LAYOUT");
        BroadcastManagerUtil.bFG_(context, intent);
        LogUtil.d("SCUI_ActiveHoursStandProvider", "send ENTER_HOUR_LAYOUT end");
    }

    private boolean i(long j) {
        boolean b2 = TimeUtil.b(j, System.currentTimeMillis());
        boolean z = ggl.a(j) <= ggl.a(System.currentTimeMillis());
        LogUtil.d("SCUI_ActiveHoursStandProvider", "isMarkableByTime isSameDay is ", Boolean.valueOf(b2), " isMarkable is ", Boolean.valueOf(z));
        return b2 && z;
    }

    private void d(Context context, boolean z) {
        if (this.f9726a == null) {
            LogUtil.c("SCUI_ActiveHoursStandProvider", "setMarkStandStatus mActiveHoursData is null");
            return;
        }
        String h = nsf.h(z ? R$string.IDS_active_standing : R$string.IDS_stand_now);
        SpannableString spannableString = new SpannableString(h);
        nsi.cKI_(spannableString, h, z ? R.color._2131299241_res_0x7f090ba9 : R.color._2131296445_res_0x7f0900bd);
        this.f9726a.d(spannableString);
        this.f9726a.c(z);
    }

    private void c() {
        if (this.f9726a.e() != 0 && !jdl.ac(this.f9726a.e())) {
            LogUtil.d("SCUI_ActiveHoursStandProvider", "queryCountData is not today return ", Long.valueOf(this.f9726a.e()));
        } else {
            nip.d("900200009", new b());
        }
    }

    private View.OnClickListener dpK_(final Context context) {
        return new View.OnClickListener() { // from class: com.huawei.ui.main.stories.fitness.activity.active.provider.ActiveHoursStandProvider$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActiveHoursStandProvider.dpM_(context, view);
            }
        };
    }

    static /* synthetic */ void dpM_(Context context, View view) {
        if (nsn.a(500)) {
            LogUtil.c("SCUI_ActiveHoursStandProvider", "click too fast");
            ViewClickInstrumentation.clickOnView(view);
        } else {
            ActiveTargetActivity.e(context, 4, 3);
            ViewClickInstrumentation.clickOnView(view);
        }
    }

    private int d(long j) {
        if (j > 0 && jdl.ac(j)) {
            return this.f;
        }
        int i = this.d.get(DateFormatUtil.c(j, TimeZone.getDefault()));
        return i <= 0 ? this.f : i;
    }

    private int b(long j) {
        if (d(j) > 0) {
            return (int) (UnitUtil.a(this.i / r3, 2) * 100.0d);
        }
        return 0;
    }

    private SpannableString dpL_(long j) {
        int d = d(j);
        String e2 = UnitUtil.e(this.i, 1, 0);
        SpannableString spannableString = new SpannableString(nsf.b(R$string.IDS_current_total_time, e2, nsf.a(R.plurals._2130903199_res_0x7f03009f, d, UnitUtil.e(d, 1, 0))));
        nsi.cKI_(spannableString, e2, R.color._2131299236_res_0x7f090ba4);
        nsi.cKJ_(spannableString, e2, nsf.b(R.dimen._2131362955_res_0x7f0a048b));
        nsi.cKL_(spannableString, e2, R$string.textFontFamilyMedium);
        return spannableString;
    }

    private String d() {
        int i = this.f;
        if (i <= 0) {
            return "";
        }
        int i2 = this.i;
        if (i2 >= i) {
            return nsf.h(R$string.IDS_hour_target_tip_2);
        }
        int i3 = i - i2;
        return nsf.b(R$string.IDS_hour_target_tip_1, nsf.a(R.plurals._2130903199_res_0x7f03009f, i3, UnitUtil.e(i3, 1, 0)));
    }

    static class c implements ResponseCallback<List<HiHealthData>> {
        private final WeakReference<ActiveHoursStandProvider> d;

        c(ActiveHoursStandProvider activeHoursStandProvider) {
            this.d = new WeakReference<>(activeHoursStandProvider);
        }

        @Override // com.huawei.hwbasemgr.ResponseCallback
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public void onResponse(int i, List<HiHealthData> list) {
            HiHealthData hiHealthData;
            ActiveHoursStandProvider activeHoursStandProvider;
            if (CollectionUtils.d(list) || (hiHealthData = list.get(0)) == null || (activeHoursStandProvider = this.d.get()) == null) {
                return;
            }
            activeHoursStandProvider.d.append(DateFormatUtil.c(hiHealthData.getStartTime(), TimeZone.getDefault()), hiHealthData.getInt("activeGoalValue"));
            if (activeHoursStandProvider.b != null) {
                activeHoursStandProvider.b.e(activeHoursStandProvider.f9726a);
            }
        }
    }

    static class b implements IBaseResponseCallback {
        private WeakReference<ActiveHoursStandProvider> d;

        private b(ActiveHoursStandProvider activeHoursStandProvider) {
            this.d = new WeakReference<>(activeHoursStandProvider);
        }

        @Override // com.huawei.hwbasemgr.IBaseResponseCallback
        /* renamed from: onResponse */
        public void d(int i, Object obj) {
            Object[] objArr = new Object[4];
            objArr[0] = "onResponse: errorCode = ";
            objArr[1] = Integer.valueOf(i);
            objArr[2] = " objData = ";
            objArr[3] = obj == null ? null : obj.toString();
            LogUtil.d("SCUI_ActiveHoursStandProvider", objArr);
            ActiveHoursStandProvider activeHoursStandProvider = this.d.get();
            if (activeHoursStandProvider != null) {
                activeHoursStandProvider.f = 12;
                if (obj instanceof Integer) {
                    activeHoursStandProvider.f = ((Integer) obj).intValue();
                } else {
                    LogUtil.c("SCUI_ActiveHoursStandProvider", "onResponse: objData is not instanceof ActiveGoalModel");
                }
                if (activeHoursStandProvider.b != null) {
                    activeHoursStandProvider.b.e(activeHoursStandProvider.f9726a);
                    return;
                }
                return;
            }
            LogUtil.c("SCUI_ActiveHoursStandProvider", "onResponse: provider is null");
        }
    }

    static class e implements HiDataOperateListener {
        private WeakReference<ActiveHoursStandProvider> b;
        private long c;
        private Context d;

        public e(ActiveHoursStandProvider activeHoursStandProvider, Context context, long j) {
            this.b = new WeakReference<>(activeHoursStandProvider);
            this.d = context;
            this.c = j;
        }

        @Override // com.huawei.hihealth.data.listener.HiDataOperateListener
        public void onResult(int i, Object obj) {
            ActiveHoursStandProvider activeHoursStandProvider = this.b.get();
            if (activeHoursStandProvider == null) {
                LogUtil.c("SCUI_ActiveHoursStandProvider", "onResult: provider is null");
                return;
            }
            LogUtil.d("SCUI_ActiveHoursStandProvider", "onResult: insert insertHiHealthData errorCode is ", Integer.valueOf(i));
            if (i == 0) {
                activeHoursStandProvider.b(this.d, this.c);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(Context context, long j) {
        d(context, true);
        this.b.e(this.f9726a);
        this.c = true;
        this.e = this.f9726a.a();
        ObserverManagerUtil.c("observer_label_mark_stand", Long.valueOf(ggl.a(j) + 1800000));
        nrh.d(context, context.getResources().getString(R$string.IDS_active_insert_success));
    }
}
