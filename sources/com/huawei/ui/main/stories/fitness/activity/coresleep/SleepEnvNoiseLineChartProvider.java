package com.huawei.ui.main.stories.fitness.activity.coresleep;

import android.content.Context;
import android.text.TextUtils;
import android.util.SparseArray;
import androidx.core.content.ContextCompat;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.design.pattern.Observer;
import com.huawei.haf.design.pattern.ObserverManagerUtil;
import com.huawei.health.R;
import com.huawei.health.knit.data.MinorProvider;
import com.huawei.health.knit.section.model.SectionBean;
import com.huawei.health.servicesui.R$string;
import com.huawei.health.sleep.sleepviewdata.SleepViewConstants;
import com.huawei.hihealth.HiDataReadOption;
import com.huawei.hihealth.HiDataReadProOption;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.api.HiHealthNativeApi;
import com.huawei.hihealth.data.listener.HiDataReadResultListener;
import com.huawei.hihealth.dictionary.utils.DicDataTypeUtil;
import com.huawei.hwlogsmodel.LogUtil;
import defpackage.bkz;
import defpackage.fdp;
import defpackage.jec;
import java.lang.ref.WeakReference;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes9.dex */
public class SleepEnvNoiseLineChartProvider extends MinorProvider<fdp> {

    /* renamed from: a, reason: collision with root package name */
    private Date f9815a;
    private Context b;
    private long c;
    private long d;
    private long e;
    private long f;
    private Observer g;
    private boolean h;
    private long i;
    private SectionBean j;
    private fdp l = new fdp(SleepViewConstants.ViewTypeEnum.DAY);

    @Override // com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.KnitDataProvider
    public boolean isActive(Context context) {
        this.b = context;
        if (this.l == null) {
            return false;
        }
        Object[] objArr = new Object[1];
        StringBuilder sb = new StringBuilder("isActive: ");
        fdp fdpVar = this.l;
        sb.append(fdpVar == null ? "false" : Boolean.valueOf(fdpVar.f().o()));
        objArr[0] = sb.toString();
        LogUtil.a("SleepEnvNoiseLineChartProvider", objArr);
        fdp fdpVar2 = this.l;
        return fdpVar2 != null && fdpVar2.f().o();
    }

    @Override // com.huawei.health.knit.data.MinorProvider, com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.KnitDataProvider
    public void loadData(Context context, SectionBean sectionBean) {
        LogUtil.a("SleepEnvNoiseLineChartProvider", "loadData");
        b();
        this.j = sectionBean;
    }

    @Override // com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.KnitDataProvider
    /* renamed from: d, reason: merged with bridge method [inline-methods] */
    public void parseParams(Context context, HashMap<String, Object> hashMap, fdp fdpVar) {
        LogUtil.a("SleepEnvNoiseLineChartProvider", "parseParams");
        this.l = fdpVar;
        c(context, hashMap, fdpVar);
    }

    private void c(Context context, HashMap<String, Object> hashMap, fdp fdpVar) {
        LogUtil.a("SleepEnvNoiseLineChartProvider", "start to set env noise data");
        hashMap.put("ENV_NOISE_TITLE", context.getResources().getString(R$string.IDS_env_noise_chart_title));
        hashMap.put("ENV_NOISE_UNIT", context.getResources().getString(R$string.IDS_env_noise_chart_unit));
        String string = context.getResources().getString(R$string.IDS_env_noise_region_definition);
        hashMap.put("MOON_IMAGE", ContextCompat.getDrawable(this.b, R.drawable._2131430616_res_0x7f0b0cd8));
        hashMap.put("SUN_IMAGE", ContextCompat.getDrawable(this.b, R.drawable._2131430645_res_0x7f0b0cf5));
        hashMap.put("ENV_NOISE_REGION_DEFINITION", string);
        hashMap.put("SLEEP_START_TIME", Long.valueOf(fdpVar.f().v()));
        hashMap.put("SLEEP_END_TIME", Long.valueOf(fdpVar.f().r()));
        String s = fdpVar.f().s();
        if (TextUtils.isEmpty(s)) {
            hashMap.put("ENVIRONMENTAL_NOISE_AVERAGEDB", 0);
        } else {
            try {
                hashMap.put("ENVIRONMENTAL_NOISE_AVERAGEDB", Integer.valueOf(new JSONObject(s).getInt("avgSnoreDb")));
            } catch (JSONException unused) {
                LogUtil.a("SleepEnvNoiseLineChartProvider", "sleep environmental noise MetaData get fail");
            }
        }
        hashMap.put("ENVIRONMENTAL_NOISE_DATA_LIST", fdpVar.f().x());
        LogUtil.a("SleepEnvNoiseLineChartProvider", "noise sequencedata: ", fdpVar.f().x());
    }

    public void c(Context context, SectionBean sectionBean) {
        LogUtil.a("SleepEnvNoiseLineChartProvider", "begin to request envnoise data!");
        HiDataReadOption hiDataReadOption = new HiDataReadOption();
        hiDataReadOption.setStartTime(this.f);
        hiDataReadOption.setEndTime(this.i);
        hiDataReadOption.setType(new int[]{DicDataTypeUtil.DataType.ENVIRONMENT_NOISE_TYPE.value()});
        hiDataReadOption.setSortOrder(1);
        hiDataReadOption.setCount(5);
        HiHealthNativeApi.a(BaseApplication.e()).readHiHealthDataPro(HiDataReadProOption.builder().e(hiDataReadOption).b(1).e(), new d(this));
    }

    static class d implements HiDataReadResultListener {
        private final WeakReference<SleepEnvNoiseLineChartProvider> c;

        @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
        public void onResultIntent(int i, Object obj, int i2, int i3) {
        }

        public d(SleepEnvNoiseLineChartProvider sleepEnvNoiseLineChartProvider) {
            this.c = new WeakReference<>(sleepEnvNoiseLineChartProvider);
        }

        @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
        public void onResult(Object obj, int i, int i2) {
            SleepEnvNoiseLineChartProvider sleepEnvNoiseLineChartProvider;
            if (i == 0 && (obj instanceof SparseArray) && (sleepEnvNoiseLineChartProvider = this.c.get()) != null) {
                Object obj2 = ((SparseArray) obj).get(DicDataTypeUtil.DataType.ENVIRONMENT_NOISE_TYPE.value());
                sleepEnvNoiseLineChartProvider.c((List<HiHealthData>) (obj2 instanceof List ? (List) obj2 : null));
            }
        }
    }

    private void b(fdp fdpVar) {
        Date date = new Date(fdpVar.f().f());
        this.f9815a = date;
        this.e = jec.n(date) * 1000;
        long e = jec.e(this.f9815a) * 1000;
        this.f = e - 28800000;
        this.i = e - (-86400000);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.huawei.health.knit.data.MinorProvider
    /* renamed from: d, reason: merged with bridge method [inline-methods] */
    public void onSetSectionBeanData(SectionBean sectionBean, fdp fdpVar) {
        this.l = fdpVar;
        this.j = sectionBean;
        b(fdpVar);
        c(this.b, sectionBean);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(List<HiHealthData> list) {
        if (bkz.e(list)) {
            LogUtil.b(getLogTag(), "no env noise data!");
            return;
        }
        boolean e = e(list);
        HiHealthData hiHealthData = list.get(0);
        LogUtil.a("SleepEnvNoiseLineChartProvider", "lastHihealthData.getStartTime(): ", Long.valueOf(hiHealthData.getStartTime()));
        LogUtil.a("SleepEnvNoiseLineChartProvider", "lastHihealthData.getEndTime(): ", Long.valueOf(hiHealthData.getEndTime()));
        if (!e) {
            boolean z = this.e < hiHealthData.getEndTime();
            LogUtil.a("SleepEnvNoiseLineChartProvider", "isValidEnvNoise: ", Boolean.valueOf(z));
            if (z) {
                this.h = false;
                this.d = hiHealthData.getStartTime();
                this.c = hiHealthData.getEndTime();
            } else {
                this.h = true;
            }
        }
        a(this.d, this.c);
    }

    private boolean e(List<HiHealthData> list) {
        if (bkz.e(list)) {
            LogUtil.b(getLogTag(), "no env noise data!");
            return false;
        }
        for (int i = 0; i < list.size(); i++) {
            HiHealthData hiHealthData = list.get(i);
            LogUtil.a("SleepEnvNoiseLineChartProvider", "hiHealthData start time:", Long.valueOf(hiHealthData.getStartTime()));
            if (this.e < hiHealthData.getEndTime() && hiHealthData.getEndTime() - hiHealthData.getStartTime() > 10800000) {
                LogUtil.a("SleepEnvNoiseLineChartProvider", "coresleep start time:", Long.valueOf(hiHealthData.getStartTime()));
                LogUtil.a("SleepEnvNoiseLineChartProvider", "coresleep end time:", Long.valueOf(hiHealthData.getEndTime()));
                LogUtil.a("SleepEnvNoiseLineChartProvider", "coresleep meta data:", hiHealthData.getMetaData());
                this.h = false;
                this.d = hiHealthData.getStartTime();
                this.c = hiHealthData.getEndTime();
                return true;
            }
        }
        return false;
    }

    private void a(long j, long j2) {
        LogUtil.a("SleepEnvNoiseLineChartProvider", "begin to request envnoise sequence data!");
        HiDataReadOption hiDataReadOption = new HiDataReadOption();
        hiDataReadOption.setStartTime(j);
        hiDataReadOption.setEndTime(j2);
        hiDataReadOption.setType(new int[]{DicDataTypeUtil.DataType.ENVIRONMENT_NOISE_TYPE.value()});
        HiHealthNativeApi.a(this.b).readHiHealthData(hiDataReadOption, new b(this));
    }

    static class b implements HiDataReadResultListener {
        private final WeakReference<SleepEnvNoiseLineChartProvider> e;

        @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
        public void onResultIntent(int i, Object obj, int i2, int i3) {
        }

        public b(SleepEnvNoiseLineChartProvider sleepEnvNoiseLineChartProvider) {
            this.e = new WeakReference<>(sleepEnvNoiseLineChartProvider);
        }

        @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
        public void onResult(Object obj, int i, int i2) {
            SleepEnvNoiseLineChartProvider sleepEnvNoiseLineChartProvider;
            if (i == 0 && (obj instanceof SparseArray) && (sleepEnvNoiseLineChartProvider = this.e.get()) != null) {
                Object obj2 = ((SparseArray) obj).get(DicDataTypeUtil.DataType.ENVIRONMENT_NOISE_TYPE.value());
                List list = obj2 instanceof List ? (List) obj2 : null;
                if (bkz.e(list)) {
                    LogUtil.b(sleepEnvNoiseLineChartProvider.getLogTag(), "no env noise data!");
                    return;
                }
                HiHealthData hiHealthData = (HiHealthData) list.get(0);
                sleepEnvNoiseLineChartProvider.l.f().g(hiHealthData.getStartTime());
                sleepEnvNoiseLineChartProvider.l.f().i(hiHealthData.getEndTime());
                sleepEnvNoiseLineChartProvider.l.f().c(hiHealthData.getMetaData());
                sleepEnvNoiseLineChartProvider.l.f().b(hiHealthData.getSequenceData());
                sleepEnvNoiseLineChartProvider.a(sleepEnvNoiseLineChartProvider.b, sleepEnvNoiseLineChartProvider.j, sleepEnvNoiseLineChartProvider.h);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(Context context, SectionBean sectionBean, boolean z) {
        LogUtil.a(getLogTag(), "mIsEnvNoiseSeqEmpty:", Boolean.valueOf(this.h));
        if (this.h) {
            this.j.e(this, null);
        } else {
            this.j.e(this, this.l);
        }
    }

    private void b() {
        if (this.g == null) {
            Observer observer = new Observer() { // from class: com.huawei.ui.main.stories.fitness.activity.coresleep.SleepEnvNoiseLineChartProvider.2
                @Override // com.huawei.haf.design.pattern.Observer
                public void notify(String str, Object... objArr) {
                    SleepEnvNoiseLineChartProvider sleepEnvNoiseLineChartProvider = SleepEnvNoiseLineChartProvider.this;
                    sleepEnvNoiseLineChartProvider.c(sleepEnvNoiseLineChartProvider.b, SleepEnvNoiseLineChartProvider.this.j);
                }
            };
            this.g = observer;
            ObserverManagerUtil.d(observer, "ENVIRONMENT_NOISE_SAVED_TIME");
        }
    }

    @Override // com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.IKnitLifeCycle
    public void onDestroy() {
        LogUtil.a("SleepEnvNoiseLineChartProvider", "clear envnoise section");
        ObserverManagerUtil.e(this.g, "ENVIRONMENT_NOISE_SAVED_TIME");
    }

    @Override // com.huawei.health.knit.data.BaseKnitDataProvider
    public String getLogTag() {
        return "SleepEnvNoiseLineChartProvider";
    }
}
