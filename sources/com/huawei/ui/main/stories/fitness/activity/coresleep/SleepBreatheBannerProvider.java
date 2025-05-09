package com.huawei.ui.main.stories.fitness.activity.coresleep;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.core.content.ContextCompat;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.handler.HandlerExecutor;
import com.huawei.health.R;
import com.huawei.health.knit.data.BaseKnitDataProvider;
import com.huawei.health.knit.section.model.SectionBean;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hihealth.HiAggregateOption;
import com.huawei.hihealth.HiHealthClient;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.api.HiHealthManager;
import com.huawei.hihealth.api.HiHealthNativeApi;
import com.huawei.hihealth.data.listener.HiAggregateListener;
import com.huawei.hihealth.data.listener.HiSubscribeListener;
import com.huawei.hihealth.data.listener.HiUnSubscribeListener;
import com.huawei.hihealth.dictionary.utils.DicDataTypeUtil;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.ui.commonui.seekbar.HealthSeekBarExtend;
import com.huawei.ui.main.R$string;
import com.huawei.ui.main.stories.fitness.activity.coresleep.SleepBreatheBannerProvider;
import defpackage.dqo;
import defpackage.drl;
import defpackage.hyk;
import defpackage.jdx;
import defpackage.koq;
import defpackage.nrz;
import health.compact.a.LanguageUtil;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes6.dex */
public class SleepBreatheBannerProvider extends BaseKnitDataProvider<HiHealthData> {

    /* renamed from: a, reason: collision with root package name */
    public static final Map<Integer, Integer> f9808a;
    public static final Map<Integer, Integer> e;
    private boolean b = false;
    private SectionBean c;
    private boolean d;
    private List<Integer> j;

    static {
        HashMap hashMap = new HashMap(4);
        f9808a = hashMap;
        hashMap.put(1, Integer.valueOf(R$string.IDS_sleep_breathe_normal));
        hashMap.put(2, Integer.valueOf(R$string.IDS_sleep_breathe_mild));
        hashMap.put(3, Integer.valueOf(R$string.IDS_sleep_breathe_moderate));
        hashMap.put(4, Integer.valueOf(R$string.IDS_sleep_breathe_severe));
        HashMap hashMap2 = new HashMap(4);
        e = hashMap2;
        hashMap2.put(1, Integer.valueOf(R$string.IDS_sleep_breathe_normal));
        hashMap2.put(2, Integer.valueOf(R$string.IDS_sleep_breathe_mild_sus));
        hashMap2.put(3, Integer.valueOf(R$string.IDS_sleep_breathe_moderate_sus));
        hashMap2.put(4, Integer.valueOf(R$string.IDS_sleep_breathe_severe_sus));
    }

    @Override // com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.KnitDataProvider
    public boolean isActive(Context context) {
        return drl.c("com.huawei.health_deviceFeature_config", "txt", "com.huawei.health.h5.sleep-apnea");
    }

    public static boolean b() {
        Map c = c();
        if (c == null) {
            ReleaseLogUtil.c("Sleep_SleepBreatheBannerProvider", "checkMedicalTypeNMPA extInfo is null!");
            return false;
        }
        Object obj = c.get("medical");
        ReleaseLogUtil.e("Sleep_SleepBreatheBannerProvider", "checkMedicalTypeNMPA medicalValue = ", obj);
        if (!(obj instanceof Boolean) || !((Boolean) obj).booleanValue()) {
            return false;
        }
        Object obj2 = c.get("medicalType");
        ReleaseLogUtil.e("Sleep_SleepBreatheBannerProvider", "checkMedicalTypeNMPA medicalTypeValue = ", obj2);
        if (obj2 instanceof String) {
            return "NMPA".equals((String) obj2);
        }
        return false;
    }

    private static Map c() {
        dqo a2 = drl.a("com.huawei.health_deviceFeature_config", "txt", "com.huawei.health.h5.sleep-apnea");
        if (a2 == null) {
            ReleaseLogUtil.d("Sleep_SleepBreatheBannerProvider", "getExtInfo featureConfig is null");
            return null;
        }
        return a2.a();
    }

    @Override // com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.KnitDataProvider
    public void loadDefaultData(SectionBean sectionBean) {
        this.b = b();
    }

    @Override // com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.KnitDataProvider
    public void loadData(Context context, SectionBean sectionBean) {
        if (!isActive(BaseApplication.e())) {
            LogUtil.h("Sleep_SleepBreatheBannerProvider", "loadData can not show SleepBreatheBanner section");
            return;
        }
        this.c = sectionBean;
        c(sectionBean);
        if (koq.b(this.j)) {
            LogUtil.a("Sleep_SleepBreatheBannerProvider", "loadData type:", Integer.valueOf(DicDataTypeUtil.DataType.OSA_SET.value()));
            HiHealthNativeApi.a(BaseApplication.e()).subscribeHiHealthData(DicDataTypeUtil.DataType.OSA_SET.value(), new e(this, null));
        }
    }

    @Override // com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.KnitDataProvider
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public void parseParams(Context context, HashMap<String, Object> hashMap, HiHealthData hiHealthData) {
        e(hashMap, hiHealthData);
    }

    private void e(HashMap<String, Object> hashMap, HiHealthData hiHealthData) {
        String string;
        String string2;
        String string3;
        LogUtil.a("Sleep_SleepBreatheBannerProvider", "setData");
        hashMap.clear();
        if (this.b) {
            string = BaseApplication.e().getResources().getString(R$string.IDS_sleep_breathe_banner_title_nmpa);
        } else {
            string = BaseApplication.e().getResources().getString(R$string.IDS_sleep_breathe_banner_title);
        }
        hashMap.put("TITLE", string);
        if (LanguageUtil.bc(BaseApplication.e())) {
            hashMap.put("LEFT_ICON_IMAGE", nrz.cKn_(BaseApplication.e(), R.drawable._2131430443_res_0x7f0b0c2b));
        } else {
            hashMap.put("LEFT_ICON_IMAGE", ContextCompat.getDrawable(BaseApplication.e(), R.drawable._2131430443_res_0x7f0b0c2b));
        }
        hashMap.put("CLICK_EVENT_LISTENER", new AnonymousClass3());
        if (hiHealthData == null || hiHealthData.getStartTime() == 0) {
            LogUtil.h("Sleep_SleepBreatheBannerProvider", "data is null");
            if (this.b) {
                string2 = BaseApplication.e().getResources().getString(R$string.IDS_sleep_breathe_no_data_nmpa);
            } else {
                string2 = BaseApplication.e().getResources().getString(R$string.IDS_sleep_breathe_no_data);
            }
            hashMap.put("SUBTITLE", string2);
            return;
        }
        int i = hiHealthData.getInt("osaLevel");
        if (i > 0 && i <= 4) {
            if (this.b) {
                string3 = BaseApplication.e().getResources().getString(e.get(Integer.valueOf(i)).intValue());
            } else {
                string3 = BaseApplication.e().getResources().getString(f9808a.get(Integer.valueOf(i)).intValue());
            }
            hashMap.put("SUBTITLE", string3);
        }
        hashMap.put("SECTION_CHART", dra_(i));
        hashMap.put("BAR_CHART_DATE_TEXT", Long.valueOf(hiHealthData.getStartTime()));
    }

    /* renamed from: com.huawei.ui.main.stories.fitness.activity.coresleep.SleepBreatheBannerProvider$3, reason: invalid class name */
    public class AnonymousClass3 implements View.OnClickListener {
        AnonymousClass3() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            LoginInit.getInstance(BaseApplication.e()).browsingToLogin(new IBaseResponseCallback() { // from class: pmn
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public final void d(int i, Object obj) {
                    SleepBreatheBannerProvider.AnonymousClass3.this.e(i, obj);
                }
            }, "");
            ViewClickInstrumentation.clickOnView(view);
        }

        public /* synthetic */ void e(int i, Object obj) {
            if (i == 0) {
                SleepBreatheBannerProvider.this.a();
            } else {
                LogUtil.h("Sleep_SleepBreatheBannerProvider", "onClick errorCode = ", Integer.valueOf(i));
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a() {
        hyk.b().startOsaH5(BaseApplication.e(), "Sleep_0001");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: b, reason: merged with bridge method [inline-methods] */
    public void c(final SectionBean sectionBean) {
        if (HandlerExecutor.c()) {
            jdx.b(new Runnable() { // from class: pmj
                @Override // java.lang.Runnable
                public final void run() {
                    SleepBreatheBannerProvider.this.c(sectionBean);
                }
            });
            return;
        }
        LogUtil.a("Sleep_SleepBreatheBannerProvider", "start queryLastData");
        HiAggregateOption e2 = e();
        e2.setCount(5);
        e2.setTimeRange(0L, System.currentTimeMillis());
        HiHealthManager.d(BaseApplication.e()).aggregateHiHealthData(e2, new d(this, sectionBean, null));
    }

    public static HiAggregateOption e() {
        HiAggregateOption hiAggregateOption = new HiAggregateOption();
        hiAggregateOption.setType(new int[]{DicDataTypeUtil.DataType.OSA_LEVEL.value()});
        hiAggregateOption.setConstantsKey(new String[]{"osaLevel"});
        hiAggregateOption.setGroupUnitType(0);
        hiAggregateOption.setSortOrder(1);
        return hiAggregateOption;
    }

    private View dra_(int i) {
        View inflate = LayoutInflater.from(BaseApplication.e()).inflate(R.layout.sleep_breathe_banner_chart, (ViewGroup) null);
        LogUtil.a("Sleep_SleepBreatheBannerProvider", "initSleepBreatheSeekBar inflated view: ", inflate);
        HealthSeekBarExtend healthSeekBarExtend = (HealthSeekBarExtend) inflate.findViewById(R.id.sleep_breathe_seek_bar);
        if (healthSeekBarExtend == null) {
            LogUtil.b("Sleep_SleepBreatheBannerProvider", "initSleepBreatheSeekBar mSeekBarExtend is null");
            return null;
        }
        healthSeekBarExtend.setMax(40);
        LogUtil.a("Sleep_SleepBreatheBannerProvider", "osaLevel = ", Integer.valueOf(i));
        if (i < 0) {
            i = 0;
        } else if (i > 4) {
            i = 4;
        } else {
            LogUtil.c("Sleep_SleepBreatheBannerProvider", "The progress is normal. No special handling is required.");
        }
        healthSeekBarExtend.setProgress((i * 10) - 5);
        return inflate;
    }

    @Override // com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.IKnitLifeCycle
    public void onResume() {
        super.onResume();
        SectionBean sectionBean = this.c;
        if (sectionBean == null) {
            LogUtil.h("Sleep_SleepBreatheBannerProvider", "onResume mSectionBean is null, return");
        } else if (this.d) {
            this.d = false;
            c(sectionBean);
        }
    }

    @Override // com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.IKnitLifeCycle
    public void onDestroy() {
        super.onDestroy();
        if (koq.b(this.j)) {
            LogUtil.h("Sleep_SleepBreatheBannerProvider", "onDestroy mSuccessList is empty");
        } else {
            HiHealthNativeApi.a(BaseApplication.e()).unSubscribeHiHealthData(this.j, new HiUnSubscribeListener() { // from class: pmi
                @Override // com.huawei.hihealth.data.listener.HiUnSubscribeListener
                public final void onResult(boolean z) {
                    LogUtil.a("Sleep_SleepBreatheBannerProvider", "unSubscribeData isSuccess = ", Boolean.valueOf(z));
                }
            });
        }
    }

    static class d implements HiAggregateListener {
        private final WeakReference<SleepBreatheBannerProvider> c;
        private final WeakReference<SectionBean> d;

        @Override // com.huawei.hihealth.data.listener.HiAggregateListener
        public void onResultIntent(int i, List<HiHealthData> list, int i2, int i3) {
        }

        /* synthetic */ d(SleepBreatheBannerProvider sleepBreatheBannerProvider, SectionBean sectionBean, AnonymousClass3 anonymousClass3) {
            this(sleepBreatheBannerProvider, sectionBean);
        }

        private d(SleepBreatheBannerProvider sleepBreatheBannerProvider, SectionBean sectionBean) {
            this.c = new WeakReference<>(sleepBreatheBannerProvider);
            this.d = new WeakReference<>(sectionBean);
        }

        @Override // com.huawei.hihealth.data.listener.HiAggregateListener
        public void onResult(List<HiHealthData> list, int i, int i2) {
            HiHealthData next;
            SleepBreatheBannerProvider sleepBreatheBannerProvider = this.c.get();
            SectionBean sectionBean = this.d.get();
            if (sleepBreatheBannerProvider == null || sectionBean == null) {
                LogUtil.h("Sleep_SleepBreatheBannerProvider", "SleepBreatheBannerProvider is null in InnerHiAggregateListener");
                return;
            }
            if (koq.b(list)) {
                LogUtil.h("Sleep_SleepBreatheBannerProvider", "queryLastData dataList is empty");
                sectionBean.e(new HiHealthData());
                return;
            }
            Iterator<HiHealthData> it = list.iterator();
            HiHealthData hiHealthData = null;
            while (it.hasNext() && (next = it.next()) != null) {
                if (hiHealthData == null || hiHealthData.getEndTime() < next.getEndTime()) {
                    hiHealthData = next;
                }
            }
            sectionBean.e(hiHealthData);
        }
    }

    static final class e implements HiSubscribeListener {
        private final WeakReference<SleepBreatheBannerProvider> e;

        /* synthetic */ e(SleepBreatheBannerProvider sleepBreatheBannerProvider, AnonymousClass3 anonymousClass3) {
            this(sleepBreatheBannerProvider);
        }

        private e(SleepBreatheBannerProvider sleepBreatheBannerProvider) {
            this.e = new WeakReference<>(sleepBreatheBannerProvider);
        }

        @Override // com.huawei.hihealth.data.listener.HiSubscribeListener
        public void onResult(List<Integer> list, List<Integer> list2) {
            SleepBreatheBannerProvider sleepBreatheBannerProvider = this.e.get();
            if (sleepBreatheBannerProvider != null) {
                sleepBreatheBannerProvider.j = list;
                LogUtil.a("Sleep_SleepBreatheBannerProvider", "subscribeData, onResult:" + list);
                return;
            }
            LogUtil.h("Sleep_SleepBreatheBannerProvider", "SleepBreatheBannerProvider is null in InnerHiAggregateListener");
        }

        @Override // com.huawei.hihealth.data.listener.HiSubscribeListener
        public void onChange(int i, HiHealthClient hiHealthClient, String str, HiHealthData hiHealthData, long j) {
            LogUtil.a("Sleep_SleepBreatheBannerProvider", "onChange, type=", i + "-client-" + hiHealthClient + "-changeKey-" + str + "-newValue-" + hiHealthData + "-time=" + j);
            SleepBreatheBannerProvider sleepBreatheBannerProvider = this.e.get();
            if (sleepBreatheBannerProvider != null) {
                sleepBreatheBannerProvider.d = true;
            } else {
                LogUtil.h("Sleep_SleepBreatheBannerProvider", "SleepBreatheBannerProvider is null in InnerHiAggregateListener");
            }
        }
    }
}
