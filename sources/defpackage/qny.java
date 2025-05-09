package defpackage;

import android.text.SpannableStringBuilder;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.TypefaceSpan;
import android.util.Pair;
import com.huawei.health.R;
import com.huawei.hihealth.HiAggregateOption;
import com.huawei.hihealth.HiDataReadOption;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.api.HiHealthManager;
import com.huawei.hihealth.data.listener.HiAggregateListener;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.ui.commonui.linechart.common.DataInfos;
import com.huawei.ui.commonui.linechart.icommon.IStorageModel;
import com.huawei.ui.commonui.linechart.utils.ResponseCallback;
import health.compact.a.UnitUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes9.dex */
public class qny {

    /* renamed from: a, reason: collision with root package name */
    private static long f16513a;
    private long b;
    private boolean e;
    private qpg g;
    private long i;
    private String j = "TEMPERATURE_MIN_MAX";
    private final boolean d = UnitUtil.d();
    private final qpk c = qpk.d();

    public void c(long j) {
        this.i = j;
    }

    public void b(long j) {
        this.b = j;
    }

    public void a(DataInfos dataInfos, qpg qpgVar, ResponseCallback responseCallback) {
        LogUtil.a("TemperatureStorage", "requestData");
        this.g = qpgVar;
        if (dataInfos.isDayData()) {
            b();
            this.e = true;
        }
        a(responseCallback);
    }

    private void b() {
        if (!jdl.d(this.i, this.b)) {
            LogUtil.h("TemperatureStorage", "getDayBodyAlarmData requested data is not the same day");
            return;
        }
        if (jdl.d(this.i, f16513a) && nsn.a(500)) {
            return;
        }
        f16513a = this.i;
        if ("SKIN_TEMPERATURE_MIN_MAX".equals(this.j)) {
            return;
        }
        int[] iArr = {this.c.j(), this.c.h(), this.c.k()};
        this.c.a(iArr, c(iArr, this.i), new c(this.g));
    }

    private HiDataReadOption c(int[] iArr, long j) {
        long t = jdl.t(j);
        HiDataReadOption hiDataReadOption = new HiDataReadOption();
        hiDataReadOption.setCount(2);
        hiDataReadOption.setType(iArr);
        hiDataReadOption.setSortOrder(1);
        hiDataReadOption.setStartTime(t);
        hiDataReadOption.setEndTime(jdl.e(t));
        return hiDataReadOption;
    }

    static class c implements IBaseResponseCallback {
        private qpg e;

        private c(qpg qpgVar) {
            this.e = qpgVar;
        }

        @Override // com.huawei.hwbasemgr.IBaseResponseCallback
        /* renamed from: onResponse */
        public void d(int i, Object obj) {
            if (i != 0) {
                LogUtil.h("TemperatureStorage", "DayBodyAlarmDataResponse errorCode ", Integer.valueOf(i));
                this.e.e((List<HiHealthData>) null);
                return;
            }
            List<HiHealthData> list = obj instanceof List ? (List) obj : null;
            if (koq.b(list)) {
                LogUtil.h("TemperatureStorage", "DayBodyAlarmDataResponse list is empty");
                this.e.e((List<HiHealthData>) null);
            } else {
                LogUtil.a("TemperatureStorage", "DayBodyAlarmDataResponse list size ", Integer.valueOf(list.size()));
                this.e.e(list);
            }
        }
    }

    private void a(ResponseCallback responseCallback) {
        int[] iArr = {2104};
        this.c.a(iArr, b(iArr), new b(responseCallback));
    }

    private HiDataReadOption b(int[] iArr) {
        HiDataReadOption hiDataReadOption = new HiDataReadOption();
        hiDataReadOption.setType(iArr);
        hiDataReadOption.setSortOrder(1);
        hiDataReadOption.setStartTime(this.i);
        hiDataReadOption.setEndTime(this.b);
        return hiDataReadOption;
    }

    static class b implements IBaseResponseCallback {
        private final qny b;
        private final ResponseCallback<Map<Long, IStorageModel>> c;

        private b(qny qnyVar, ResponseCallback<Map<Long, IStorageModel>> responseCallback) {
            this.b = qnyVar;
            this.c = responseCallback;
        }

        @Override // com.huawei.hwbasemgr.IBaseResponseCallback
        /* renamed from: onResponse */
        public void d(int i, Object obj) {
            qny qnyVar = this.b;
            if (qnyVar == null) {
                LogUtil.h("TemperatureStorage", "OldTypeMaxMinResponse helper is null");
                return;
            }
            ResponseCallback<Map<Long, IStorageModel>> responseCallback = this.c;
            if (responseCallback != null) {
                qnyVar.b(responseCallback, (List<HiHealthData>) (obj instanceof List ? (List) obj : null));
            } else {
                LogUtil.h("TemperatureStorage", "OldTypeMaxMinResponse callback is null");
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(ResponseCallback responseCallback, List<HiHealthData> list) {
        HiAggregateOption d = d();
        d.setType(new int[]{this.c.f(), this.c.i(), this.c.l(), this.c.n(), this.c.q(), this.c.r()});
        d.setConstantsKey(new String[]{"body_min_key", "body_max_key", "skin_min_key", "skin_max_key", "suspected_temperature_high_min_key", "suspected_temperature_high_max_key"});
        HiHealthManager.d(BaseApplication.getContext()).aggregateHiHealthData(d, new e(this.g, responseCallback, list));
    }

    private HiAggregateOption d() {
        HiAggregateOption hiAggregateOption = new HiAggregateOption();
        hiAggregateOption.setStartTime(this.i);
        hiAggregateOption.setEndTime(this.b);
        hiAggregateOption.setGroupUnitSize(1);
        hiAggregateOption.setAggregateType(1);
        hiAggregateOption.setGroupUnitType(3);
        hiAggregateOption.setReadType(0);
        hiAggregateOption.setSortOrder(1);
        return hiAggregateOption;
    }

    static class e implements HiAggregateListener {

        /* renamed from: a, reason: collision with root package name */
        private final qpg f16514a;
        private ResponseCallback<Map<Long, IStorageModel>> b;
        private final List<HiHealthData> c;
        private final qny e;

        private e(qny qnyVar, qpg qpgVar, ResponseCallback<Map<Long, IStorageModel>> responseCallback, List<HiHealthData> list) {
            this.e = qnyVar;
            this.b = responseCallback;
            this.c = list;
            this.f16514a = qpgVar;
        }

        @Override // com.huawei.hihealth.data.listener.HiAggregateListener
        public void onResult(List<HiHealthData> list, int i, int i2) {
            if (this.b == null) {
                LogUtil.h("TemperatureStorage", "MaxMinDataReadListener callback is null");
                return;
            }
            qny qnyVar = this.e;
            if (qnyVar != null) {
                List a2 = qnyVar.a(list, this.c);
                if (koq.b(a2)) {
                    LogUtil.h("TemperatureStorage", "MaxMinDataReadListener data is null!");
                    if (!this.e.e) {
                        this.b.onResult(0, new HashMap());
                    }
                    this.f16514a.e(false);
                    this.e.c((List<HiHealthData>) a2, this.b);
                    return;
                }
                LogUtil.a("TemperatureStorage", "MaxMinDataReadListener data size ", Integer.valueOf(a2.size()));
                this.f16514a.e(true);
                this.e.c((List<HiHealthData>) a2, this.b);
                this.b = null;
                return;
            }
            LogUtil.h("TemperatureStorage", "MaxMinDataReadListener helper is null");
        }

        @Override // com.huawei.hihealth.data.listener.HiAggregateListener
        public void onResultIntent(int i, List<HiHealthData> list, int i2, int i3) {
            LogUtil.c("TemperatureStorage", "DataReadListener onResultIntent errorCode = ", Integer.valueOf(i2));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public List<HiHealthData> a(List<HiHealthData> list, List<HiHealthData> list2) {
        ArrayList arrayList = new ArrayList(16);
        if (trg.d(list2)) {
            LogUtil.h("TemperatureStorage", "addOldData oldList is null!");
            return list;
        }
        HashMap hashMap = new HashMap(16);
        for (HiHealthData hiHealthData : list2) {
            if (hiHealthData != null) {
                long d = jec.d(hiHealthData.getStartTime());
                float floatValue = hiHealthData.getFloatValue();
                HiHealthData hiHealthData2 = hashMap.get(Long.valueOf(d));
                if (hiHealthData2 == null) {
                    hiHealthData2 = new HiHealthData();
                    hiHealthData2.setTimeInterval(d, d);
                    hiHealthData2.putFloat("body_min_key", floatValue);
                    hiHealthData2.putFloat("body_max_key", floatValue);
                } else {
                    float f = hiHealthData2.getFloat("body_max_key");
                    float f2 = hiHealthData2.getFloat("body_min_key");
                    float max = Math.max(floatValue, f);
                    hiHealthData2.putFloat("body_min_key", Math.min(floatValue, f2));
                    hiHealthData2.putFloat("body_max_key", max);
                }
                hashMap.put(Long.valueOf(d), hiHealthData2);
            }
        }
        if (trg.d(list)) {
            LogUtil.h("TemperatureStorage", "addOldData list is null");
            b(arrayList, hashMap);
            return arrayList;
        }
        d(list, hashMap, arrayList);
        return arrayList;
    }

    private void d(List<HiHealthData> list, Map<Long, HiHealthData> map, List<HiHealthData> list2) {
        for (HiHealthData hiHealthData : list) {
            if (hiHealthData != null) {
                HiHealthData hiHealthData2 = map.get(Long.valueOf(hiHealthData.getStartTime()));
                if (hiHealthData2 == null) {
                    list2.add(hiHealthData);
                } else {
                    float f = hiHealthData2.getFloat("body_min_key");
                    float f2 = hiHealthData2.getFloat("body_max_key");
                    float min = Math.min(hiHealthData.getFloat("body_min_key"), f);
                    float max = Math.max(hiHealthData.getFloat("body_max_key"), f2);
                    hiHealthData2.putFloat("body_min_key", min);
                    hiHealthData2.putFloat("body_max_key", max);
                    hiHealthData2.putFloat("skin_min_key", hiHealthData.getFloat("skin_min_key"));
                    hiHealthData2.putFloat("skin_max_key", hiHealthData.getFloat("skin_max_key"));
                    list2.add(hiHealthData2);
                }
            }
        }
    }

    private void b(List<HiHealthData> list, Map<Long, HiHealthData> map) {
        Iterator<Map.Entry<Long, HiHealthData>> it = map.entrySet().iterator();
        while (it.hasNext()) {
            list.add(it.next().getValue());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(List<HiHealthData> list, ResponseCallback<Map<Long, IStorageModel>> responseCallback) {
        Map<String, SpannableStringBuilder> d;
        new HashMap();
        if (!this.e) {
            LogUtil.a("TemperatureStorage", "is not day fragment");
            ArrayList arrayList = new ArrayList(16);
            qpr.d(b(list), arrayList);
            d = d(arrayList);
        } else {
            d = d(list);
        }
        this.g.e(d);
        responseCallback.onResult(0, null);
    }

    private List<HiHealthData> b(List<HiHealthData> list) {
        if (!koq.c(list)) {
            return null;
        }
        ArrayList arrayList = new ArrayList(list.size());
        for (int i = 0; i < list.size(); i++) {
            HiHealthData hiHealthData = list.get(i);
            if (hiHealthData.getStartTime() >= this.i && hiHealthData.getEndTime() < this.b) {
                arrayList.add(hiHealthData);
            }
        }
        LogUtil.c("TemperatureStorage", "results list ", Integer.valueOf(arrayList.size()));
        return arrayList;
    }

    private Map<String, SpannableStringBuilder> d(List<HiHealthData> list) {
        HashMap hashMap = new HashMap();
        if (koq.b(list)) {
            LogUtil.h("TemperatureStorage", "dealMaxAndMin data list is isEmpty");
            SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder("--");
            hashMap.put("TEMPERATURE_MIN_MAX", spannableStringBuilder);
            hashMap.put("SKIN_TEMPERATURE_MIN_MAX", spannableStringBuilder);
            return hashMap;
        }
        HiHealthData hiHealthData = list.get(0);
        float c2 = c(hiHealthData.getFloat("body_min_key"));
        float c3 = c(hiHealthData.getFloat("body_max_key"));
        float c4 = c(hiHealthData.getFloat("skin_min_key"));
        float c5 = c(hiHealthData.getFloat("skin_max_key"));
        Pair<String, String> dHg_ = qpr.dHg_(c3, c2, c(hiHealthData.getFloat("suspected_temperature_high_max_key")), c(hiHealthData.getFloat("suspected_temperature_high_min_key")));
        Pair<String, String> dHg_2 = qpr.dHg_(c5, c4, 0.0f, 0.0f);
        SpannableStringBuilder spannableStringBuilder2 = new SpannableStringBuilder((CharSequence) dHg_2.first);
        dGx_(spannableStringBuilder2, (String) dHg_2.first, (String) dHg_2.second);
        SpannableStringBuilder spannableStringBuilder3 = new SpannableStringBuilder((CharSequence) dHg_.first);
        dGx_(spannableStringBuilder3, (String) dHg_.first, (String) dHg_.second);
        LogUtil.c("TemperatureStorage", "bodyMinMax ", dHg_.first, "skinMinMax ", dHg_2.first);
        hashMap.put("TEMPERATURE_MIN_MAX", spannableStringBuilder3);
        hashMap.put("SKIN_TEMPERATURE_MIN_MAX", spannableStringBuilder2);
        return hashMap;
    }

    private void dGx_(SpannableStringBuilder spannableStringBuilder, String str, String str2) {
        int i;
        int indexOf = str.indexOf(str2);
        int length = str2.length();
        if (indexOf < 0 || (i = length + indexOf) > spannableStringBuilder.length()) {
            LogUtil.b("TemperatureStorage", "TemperatureStorage spannableString overflow");
            return;
        }
        spannableStringBuilder.setSpan(new AbsoluteSizeSpan(BaseApplication.getContext().getResources().getDimensionPixelSize(R.dimen._2131362886_res_0x7f0a0446)), 0, str.length(), 17);
        spannableStringBuilder.setSpan(new AbsoluteSizeSpan(BaseApplication.getContext().getResources().getDimensionPixelSize(R.dimen._2131362954_res_0x7f0a048a)), indexOf, i, 17);
        spannableStringBuilder.setSpan(new TypefaceSpan(Constants.FONT), indexOf, i, 17);
    }

    private float c(float f) {
        if (f == 0.0f) {
            return 0.0f;
        }
        return qpr.d(f);
    }

    private Map<String, SpannableStringBuilder> a() {
        HashMap hashMap = new HashMap(2);
        for (int i = 0; i < 2; i++) {
            SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder("--");
            if (i == 0) {
                hashMap.put("TEMPERATURE_MIN_MAX", spannableStringBuilder);
            } else {
                hashMap.put("SKIN_TEMPERATURE_MIN_MAX", spannableStringBuilder);
            }
        }
        return hashMap;
    }

    public Map<String, eei> d(Map<String, SpannableStringBuilder> map, String str, String str2) {
        if (map == null || map.isEmpty()) {
            LogUtil.h("TemperatureStorage", "getTemperatureCardContent temperatureList is null");
            map = a();
        }
        HashMap hashMap = new HashMap(16);
        synchronized (map) {
            for (Map.Entry<String, SpannableStringBuilder> entry : map.entrySet()) {
                eei eeiVar = new eei();
                eeiVar.agT_(entry.getValue());
                if ("TEMPERATURE_MIN_MAX".equals(entry.getKey())) {
                    eeiVar.d(str);
                } else if ("SKIN_TEMPERATURE_MIN_MAX".equals(entry.getKey())) {
                    eeiVar.d(str2);
                } else {
                    LogUtil.a("TemperatureStorage", "other card");
                }
                eeiVar.a(qpr.c());
                hashMap.put(entry.getKey(), eeiVar);
            }
        }
        return hashMap;
    }
}
