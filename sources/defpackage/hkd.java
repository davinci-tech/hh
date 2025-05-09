package defpackage;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.SparseArray;
import android.view.View;
import android.widget.LinearLayout;
import com.huawei.basefitnessadvice.model.FitnessTrackRecord;
import com.huawei.haf.handler.BaseHandler;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.R;
import com.huawei.health.courseplanservice.api.RecordApi;
import com.huawei.health.devicemgr.api.constant.GetDeviceInfoMode;
import com.huawei.health.devicemgr.business.entity.DeviceCapability;
import com.huawei.health.h5pro.H5ProClient;
import com.huawei.health.h5pro.core.H5ProLaunchOption;
import com.huawei.health.h5pro.utils.EnvironmentHelper;
import com.huawei.health.weight.WeightApi;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hihealth.HiDataReadOption;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.api.HiHealthManager;
import com.huawei.hihealth.data.listener.HiDataReadResultListener;
import com.huawei.hms.support.api.entity.common.CommonConstant;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import defpackage.kwy;
import health.compact.a.Services;
import health.compact.a.UnitUtil;
import health.compact.a.Utils;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.CountDownLatch;

/* loaded from: classes4.dex */
public class hkd {

    /* renamed from: a, reason: collision with root package name */
    private long f13192a;
    private float b;
    private double c;
    private Context d;
    private long e;
    private hjw f;
    private HealthTextView g;
    private HealthTextView h;
    private LinearLayout i;
    private LinearLayout j;
    private HealthTextView k;
    private float l;
    private Handler m = new j(this);
    private HealthTextView n;
    private float o;
    private LinearLayout r;

    static class j extends BaseHandler<hkd> {
        j(hkd hkdVar) {
            super(hkdVar);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.huawei.haf.handler.BaseHandler
        /* renamed from: bhF_, reason: merged with bridge method [inline-methods] */
        public void handleMessageWhenReferenceNotNull(hkd hkdVar, Message message) {
            switch (message.what) {
                case 10001:
                    float f = hkdVar.l + hkdVar.b;
                    LogUtil.a("Track_WeightMeasureDataHelper", "sportTotalDuration：", Float.valueOf(f));
                    if (f > 2.5f) {
                        hkdVar.a(f);
                        break;
                    } else {
                        hkdVar.i();
                        break;
                    }
                case 10002:
                    if (hkd.b(hkdVar.e) || hkdVar.o <= 500.0f || hkdVar.c <= 24.0d) {
                        hkdVar.g();
                        break;
                    } else {
                        hkdVar.d(hkdVar.o);
                        break;
                    }
                    break;
                case 10003:
                    long longValue = ((Long) message.obj).longValue();
                    LogUtil.a("Track_WeightMeasureDataHelper", "lastBodyShapeTime：", Long.valueOf(longValue));
                    if (!hkd.c(longValue)) {
                        hkdVar.c();
                        break;
                    } else {
                        hkdVar.i();
                        break;
                    }
                case 10004:
                    hkdVar.i();
                    break;
                default:
                    LogUtil.h("Track_WeightMeasureDataHelper", "WeightMeasureHandler else msg");
                    break;
            }
        }
    }

    public hkd(View view, Context context) {
        this.d = context;
        bhE_(view);
    }

    private void bhE_(View view) {
        this.r = (LinearLayout) view.findViewById(R.id.layout_weight_measurement);
        this.k = (HealthTextView) view.findViewById(R.id.weight_measurement_tips);
        this.n = (HealthTextView) view.findViewById(R.id.weight_measurement_coaching);
        this.h = (HealthTextView) view.findViewById(R.id.track_detail_ECG_measurement);
        this.j = (LinearLayout) view.findViewById(R.id.layout_ECG_measurement);
        this.g = (HealthTextView) view.findViewById(R.id.track_detail_ECG_measurement_coaching);
        this.i = (LinearLayout) view.findViewById(R.id.layout_smart_coach_sport_data_interpretation);
    }

    public void e(hjw hjwVar) {
        this.f = hjwVar;
        if (hjwVar != null) {
            this.f13192a = hjwVar.e().requestStartTime();
        } else {
            this.f13192a = 0L;
        }
        if (e(this.f13192a)) {
            e();
        } else {
            i();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(float f) {
        double d2 = f;
        String format = String.format(Locale.ENGLISH, this.d.getResources().getString(R.string._2130840095_res_0x7f020a1f), this.d.getResources().getQuantityString(R.plurals._2130903138_res_0x7f030062, UnitUtil.e(d2), UnitUtil.e(d2, 1, 1)));
        this.j.setVisibility(8);
        this.r.setVisibility(0);
        this.k.setText(format);
        this.n.setOnClickListener(new View.OnClickListener() { // from class: hkd.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LogUtil.a("Track_WeightMeasureDataHelper", "showBodyShapeTips click");
                Intent intent = new Intent(CommonConstant.ACTION.HWID_SCHEME_URL, Uri.parse("huaweihealth://huaweihealth.app/openwith?address=com.huawei.health.h5.ai-body-shape?h5pro=true&isNeedLogin=true&urlType=4&pName=com.huawei.health&from=1"));
                intent.addCategory("android.intent.category.DEFAULT").addFlags(268435456);
                gnm.aPB_(hkd.this.d, intent);
                ViewClickInstrumentation.clickOnView(view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(float f) {
        double d2 = f;
        String format = String.format(Locale.ENGLISH, this.d.getResources().getString(R.string._2130840094_res_0x7f020a1e), this.d.getResources().getQuantityString(R.plurals._2130903139_res_0x7f030063, UnitUtil.e(d2), UnitUtil.e(d2, 1, 0)));
        this.j.setVisibility(8);
        this.r.setVisibility(0);
        this.k.setText(format);
        this.n.setOnClickListener(new View.OnClickListener() { // from class: hkd.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LogUtil.a("Track_WeightMeasureDataHelper", "showWeightTips click");
                grz.aSU_("", new Bundle());
                ViewClickInstrumentation.clickOnView(view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void g() {
        LogUtil.a("Track_WeightMeasureDataHelper", "setEcgMeasurementText");
        this.r.setVisibility(8);
        if (d()) {
            this.j.setVisibility(0);
            this.h.setText(this.d.getResources().getString(R.string._2130840001_res_0x7f0209c1, 30));
            if (this.h.getLayoutParams() instanceof LinearLayout.LayoutParams) {
                LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) this.h.getLayoutParams();
                int a2 = (int) nme.a(this.d.getResources().getDimension(R.dimen._2131365058_res_0x7f0a0cc2));
                if (this.i.getVisibility() == 0) {
                    layoutParams.topMargin = a2;
                } else {
                    layoutParams.topMargin = 0;
                }
                this.h.setLayoutParams(layoutParams);
            }
            h();
            return;
        }
        this.j.setVisibility(8);
    }

    private boolean d() {
        if (this.f == null) {
            LogUtil.a("Track_WeightMeasureDataHelper", "trackDetailDataManager == null");
            return false;
        }
        if (Utils.o()) {
            LogUtil.a("Track_WeightMeasureDataHelper", "setEcgMeasurementText isOversea");
            return false;
        }
        if (cun.c().getDeviceInfo(GetDeviceInfoMode.CONNECTED_MAIN_DEVICES_WITHOUT_AW70, null, "Track_WeightMeasureDataHelper") == null) {
            LogUtil.a("Track_WeightMeasureDataHelper", "setEcgMeasurementText deviceInfo is null or deviceInfo != DEVICE_CONNECTED");
            return false;
        }
        DeviceCapability d2 = cvs.d();
        int requestAvgHeartRate = this.f.e().requestAvgHeartRate();
        if (d2 != null) {
            return d2.isSupportEcgAuth() && requestAvgHeartRate >= 120;
        }
        LogUtil.b("Track_WeightMeasureDataHelper", "get deviceInfo is null. please check.");
        return false;
    }

    private void h() {
        this.g.setOnClickListener(new View.OnClickListener() { // from class: hkd.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                HashMap hashMap = new HashMap();
                hashMap.put("click", 1);
                ixx.d().d(BaseApplication.getContext(), AnalyticsValue.SPORT_DETAILS_ECG_MEASUREMENT_2090014.value(), hashMap, 0);
                bzs.e().initH5Pro();
                H5ProLaunchOption build = new H5ProLaunchOption.Builder().addCustomizeJsModule("innerapi", bzs.e().getCommonJsModule("innerapi")).showStatusBar().setStatusBarTextBlack(true).setForceDarkMode(0).build();
                String str = cwo.b() ? "0" : "1";
                H5ProClient.startH5LightApp(hkd.this.d, EnvironmentHelper.getInstance().getUrl() + "weixinScan/dist/index.html#/measureGuide?support=" + str, build);
                ViewClickInstrumentation.clickOnView(view);
            }
        });
    }

    private void e() {
        LogUtil.a("Track_WeightMeasureDataHelper", "getRecentLastBodyShapeTime");
        kot.a().b(this.d, new e(this.m));
    }

    static class e implements IBaseResponseCallback {
        private WeakReference<Handler> c;

        e(Handler handler) {
            this.c = new WeakReference<>(handler);
        }

        @Override // com.huawei.hwbasemgr.IBaseResponseCallback
        /* renamed from: onResponse */
        public void d(int i, Object obj) {
            Handler handler = this.c.get();
            if (handler == null) {
                LogUtil.h("Track_WeightMeasureDataHelper", "handler == null");
                return;
            }
            if (i != 0 || !(obj instanceof Long)) {
                LogUtil.h("Track_WeightMeasureDataHelper", "LastBodyShapeRecordCallBack datas is error");
                handler.sendEmptyMessage(10004);
                return;
            }
            LogUtil.a("Track_WeightMeasureDataHelper", "LastBodyShapeRecordCallBack:", Long.valueOf(((Long) obj).longValue()));
            Message obtainMessage = handler.obtainMessage();
            obtainMessage.what = 10004;
            obtainMessage.obj = obj;
            handler.sendMessage(obtainMessage);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c() {
        LogUtil.a("Track_WeightMeasureDataHelper", "getMonthDataSportDuration");
        final CountDownLatch countDownLatch = new CountDownLatch(2);
        final c cVar = new c(this, countDownLatch);
        final a aVar = new a(this, countDownLatch);
        ThreadPoolManager.d().execute(new Runnable() { // from class: hkd.2
            @Override // java.lang.Runnable
            public void run() {
                long s = jdl.s(System.currentTimeMillis());
                long a2 = jdl.a(System.currentTimeMillis());
                gts.b(hkd.this.d).e(s, a2, 5, 0, cVar);
                ((RecordApi) Services.a("CoursePlanService", RecordApi.class)).acquireSummaryFitnessRecord(new kwy.a().a(s).e(a2).c(5).d(), aVar);
                try {
                    countDownLatch.await();
                } catch (InterruptedException unused) {
                    LogUtil.b("Track_WeightMeasureDataHelper", "interrupted while waiting for getMonthDataSportDuration");
                }
                if (hkd.this.m != null) {
                    Message obtainMessage = hkd.this.m.obtainMessage();
                    obtainMessage.what = 10001;
                    hkd.this.m.sendMessage(obtainMessage);
                }
            }
        });
    }

    static class c implements IBaseResponseCallback {
        private WeakReference<hkd> b;
        private WeakReference<CountDownLatch> c;

        c(hkd hkdVar, CountDownLatch countDownLatch) {
            this.b = new WeakReference<>(hkdVar);
            this.c = new WeakReference<>(countDownLatch);
        }

        @Override // com.huawei.hwbasemgr.IBaseResponseCallback
        /* renamed from: onResponse */
        public void d(int i, Object obj) {
            hkd hkdVar = this.b.get();
            CountDownLatch countDownLatch = this.c.get();
            if (countDownLatch == null || hkdVar == null) {
                LogUtil.h("Track_WeightMeasureDataHelper", "CurrentMonthTrackSportTimeCallBack param is null");
                countDownLatch.countDown();
                return;
            }
            if (!koq.e(obj, HiHealthData.class)) {
                LogUtil.h("Track_WeightMeasureDataHelper", "not match listType");
                countDownLatch.countDown();
                return;
            }
            List<HiHealthData> list = (List) obj;
            if (koq.b(list)) {
                LogUtil.h("Track_WeightMeasureDataHelper", "sumMounthDataList isEmpty");
                countDownLatch.countDown();
                return;
            }
            long j = 0;
            for (HiHealthData hiHealthData : list) {
                if (hiHealthData == null) {
                    LogUtil.h("Track_WeightMeasureDataHelper", "hiHealthData == null");
                } else {
                    j += hiHealthData.getLong("Track_Duration_Sum");
                }
            }
            LogUtil.a("Track_WeightMeasureDataHelper", "trackTimeDuration :", Long.valueOf(j));
            float f = j / 3600000.0f;
            LogUtil.a("Track_WeightMeasureDataHelper", "trackTimeHours :", Float.valueOf(f));
            hkdVar.l = f;
            countDownLatch.countDown();
        }
    }

    static class a implements IBaseResponseCallback {

        /* renamed from: a, reason: collision with root package name */
        private WeakReference<hkd> f13195a;
        private WeakReference<CountDownLatch> c;

        a(hkd hkdVar, CountDownLatch countDownLatch) {
            this.f13195a = new WeakReference<>(hkdVar);
            this.c = new WeakReference<>(countDownLatch);
        }

        @Override // com.huawei.hwbasemgr.IBaseResponseCallback
        /* renamed from: onResponse */
        public void d(int i, Object obj) {
            hkd hkdVar = this.f13195a.get();
            CountDownLatch countDownLatch = this.c.get();
            if (hkdVar == null || countDownLatch == null) {
                LogUtil.h("Track_WeightMeasureDataHelper", "CurrentMonthFitnessSportTimeCallBack param is null");
                countDownLatch.countDown();
                return;
            }
            if (!koq.e(obj, FitnessTrackRecord.class)) {
                LogUtil.h("Track_WeightMeasureDataHelper", "not match listType");
                countDownLatch.countDown();
                return;
            }
            List list = (List) obj;
            if (koq.b(list)) {
                LogUtil.h("Track_WeightMeasureDataHelper", "fitnessTrackRecordList isEmpty");
                countDownLatch.countDown();
                return;
            }
            LogUtil.a("Track_WeightMeasureDataHelper", "mFitnessTimeDuration :", Long.valueOf(((FitnessTrackRecord) list.get(0)).acquireSumExerciseTime()));
            float acquireSumExerciseTime = r6.acquireSumExerciseTime() / 3600000.0f;
            LogUtil.a("Track_WeightMeasureDataHelper", "timeDurationHour :", Float.valueOf(acquireSumExerciseTime));
            hkdVar.b = acquireSumExerciseTime;
            countDownLatch.countDown();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void i() {
        if (f(this.f13192a)) {
            a();
        } else {
            g();
        }
    }

    private void a() {
        LogUtil.a("Track_WeightMeasureDataHelper", "getWeightData");
        final CountDownLatch countDownLatch = new CountDownLatch(2);
        final d dVar = new d(this, countDownLatch);
        final b bVar = new b(this, countDownLatch);
        ThreadPoolManager.d().execute(new Runnable() { // from class: hkd.4
            @Override // java.lang.Runnable
            public void run() {
                kot.a().b(hkd.this.d, 0L, System.currentTimeMillis(), 1, dVar);
                hkd.e(hkd.this.d, (IBaseResponseCallback) bVar);
                try {
                    countDownLatch.await();
                } catch (InterruptedException unused) {
                    LogUtil.b("Track_WeightMeasureDataHelper", "interrupted while waiting for getWeightData");
                }
                if (hkd.this.m != null) {
                    Message obtainMessage = hkd.this.m.obtainMessage();
                    obtainMessage.what = 10002;
                    hkd.this.m.sendMessage(obtainMessage);
                }
            }
        });
    }

    static class d implements IBaseResponseCallback {
        private WeakReference<CountDownLatch> b;
        private WeakReference<hkd> e;

        d(hkd hkdVar, CountDownLatch countDownLatch) {
            this.e = new WeakReference<>(hkdVar);
            this.b = new WeakReference<>(countDownLatch);
        }

        @Override // com.huawei.hwbasemgr.IBaseResponseCallback
        /* renamed from: onResponse */
        public void d(int i, Object obj) {
            hkd hkdVar = this.e.get();
            CountDownLatch countDownLatch = this.b.get();
            if (hkdVar == null || countDownLatch == null) {
                LogUtil.h("Track_WeightMeasureDataHelper", "LastWeightCallBack param is null");
                countDownLatch.countDown();
                return;
            }
            if (i != 0 || !(obj instanceof List)) {
                LogUtil.h("Track_WeightMeasureDataHelper", "LastWeightCallBack datas is error");
                countDownLatch.countDown();
                return;
            }
            List list = (List) obj;
            if (koq.b(list)) {
                LogUtil.h("Track_WeightMeasureDataHelper", "LastWeightCallBack datas is empty");
                countDownLatch.countDown();
                return;
            }
            WeightApi weightApi = (WeightApi) Services.a("Main", WeightApi.class);
            HiHealthData hiHealthData = (HiHealthData) list.get(0);
            hkdVar.c = weightApi.getBmiByHiHealthData(hiHealthData);
            hkdVar.e = hiHealthData.getStartTime();
            LogUtil.a("Track_WeightMeasureDataHelper", "LastWeightCallBack mBmi = ", Double.valueOf(hkdVar.c));
            LogUtil.a("Track_WeightMeasureDataHelper", "LastWeightCallBack mLastWeightTime = ", Long.valueOf(hkdVar.e));
            countDownLatch.countDown();
        }
    }

    static class b implements IBaseResponseCallback {
        private WeakReference<CountDownLatch> b;
        private WeakReference<hkd> e;

        b(hkd hkdVar, CountDownLatch countDownLatch) {
            this.e = new WeakReference<>(hkdVar);
            this.b = new WeakReference<>(countDownLatch);
        }

        @Override // com.huawei.hwbasemgr.IBaseResponseCallback
        /* renamed from: onResponse */
        public void d(int i, Object obj) {
            hkd hkdVar = this.e.get();
            CountDownLatch countDownLatch = this.b.get();
            if (hkdVar == null || countDownLatch == null) {
                LogUtil.h("Track_WeightMeasureDataHelper", "helper == null || countDownLatch == null");
                countDownLatch.countDown();
            } else if (i == 0 && (obj instanceof Float)) {
                hkdVar.o = ((Float) obj).floatValue();
                LogUtil.a("Track_WeightMeasureDataHelper", "caloriesValueSum：", obj);
                countDownLatch.countDown();
            } else {
                LogUtil.h("Track_WeightMeasureDataHelper", "CaloriesDataCallback datas is error");
                countDownLatch.countDown();
            }
        }
    }

    public static void e(Context context, final IBaseResponseCallback iBaseResponseCallback) {
        HiDataReadOption hiDataReadOption = new HiDataReadOption();
        hiDataReadOption.setTimeInterval(jdl.c(System.currentTimeMillis(), 2, 0), jdl.e(System.currentTimeMillis()));
        hiDataReadOption.setType(new int[]{40003});
        HiHealthManager.d(context).readHiHealthData(hiDataReadOption, new HiDataReadResultListener() { // from class: hkd.6
            @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
            public void onResultIntent(int i, Object obj, int i2, int i3) {
            }

            @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
            public void onResult(Object obj, int i, int i2) {
                hkd.e(obj, IBaseResponseCallback.this);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void e(Object obj, IBaseResponseCallback iBaseResponseCallback) {
        if (obj == null || !(obj instanceof SparseArray)) {
            LogUtil.h("Track_WeightMeasureDataHelper", "data fail");
            iBaseResponseCallback.d(-1, obj);
            return;
        }
        SparseArray sparseArray = (SparseArray) obj;
        if (sparseArray.size() == 0) {
            LogUtil.h("Track_WeightMeasureDataHelper", "map.size() == 0");
            iBaseResponseCallback.d(-1, obj);
            return;
        }
        Object obj2 = sparseArray.get(40003);
        if (!koq.e(obj2, HiHealthData.class)) {
            LogUtil.h("Track_WeightMeasureDataHelper", "acquireCaloriesDataResult onResult data not instanceof List");
            iBaseResponseCallback.d(-1, null);
            return;
        }
        List<HiHealthData> list = (List) obj2;
        if (koq.b(list)) {
            LogUtil.h("Track_WeightMeasureDataHelper", "healthDataList is empty");
            iBaseResponseCallback.d(-1, obj);
            return;
        }
        int i = 0;
        for (HiHealthData hiHealthData : list) {
            if (hiHealthData == null) {
                LogUtil.h("Track_WeightMeasureDataHelper", "healthData == null");
            } else {
                i += hiHealthData.getIntValue();
            }
        }
        iBaseResponseCallback.d(0, Float.valueOf(i / 1000.0f));
    }

    public void b() {
        Handler handler = this.m;
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
        }
    }

    private static boolean j(long j2) {
        return jdl.n(j2) >= 4 && jdl.n(j2) <= 6;
    }

    private static boolean i(long j2) {
        long a2 = jdl.a(j2);
        return j2 >= jdl.d(a2, -2) && j2 <= a2;
    }

    public static boolean a(long j2) {
        return jdl.f(System.currentTimeMillis(), j2);
    }

    public static boolean d(long j2) {
        return jdl.i(System.currentTimeMillis(), j2);
    }

    public static boolean c(long j2) {
        return d(j2) && i(j2);
    }

    public static boolean b(long j2) {
        return a(j2) && j(j2);
    }

    public static boolean e(long j2) {
        return d(j2) && c(System.currentTimeMillis());
    }

    public static boolean f(long j2) {
        return a(j2) && b(System.currentTimeMillis());
    }
}
