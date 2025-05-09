package defpackage;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.health.sport.utils.FitnessDataQueryDefine;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.data.model.HiStressMetaData;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.ui.commonui.base.CommonUiBaseResponse;
import com.huawei.ui.main.R$string;
import com.huawei.ui.main.stories.fitness.interactors.stressAdvice.PressureAdeviceRetrunValue;
import com.huawei.ui.main.stories.fitness.interactors.stressAdvice.PressureMeasureAdviceMgr;
import health.compact.a.UnitUtil;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes6.dex */
public class pwr {
    private static final Map<Integer, Integer> b = new HashMap(10);

    /* renamed from: a, reason: collision with root package name */
    private static final Map<Integer, Integer> f16312a = new HashMap(10);
    private static final Map<Integer, Integer> e = new HashMap(10);
    private pwm d = pwm.a();
    private String c = "";

    public static String e(int i) {
        String str;
        LogUtil.a("PressureMeasureDetailInteractor", "getAdviceFromMap = ", Integer.valueOf(i));
        Context context = BaseApplication.getContext();
        if (i >= 1002 && i <= 3002) {
            Map<Integer, Integer> map = b;
            if (map.size() <= 0) {
                sdo.a(sdo.b("pressure/dayAdvice.properties"), map);
            }
            str = context.getString(map.get(Integer.valueOf(i)).intValue());
        } else if (i >= 4002 && i <= 5108) {
            Map<Integer, Integer> map2 = f16312a;
            if (map2.size() <= 0) {
                sdo.a(sdo.b("pressure/weekAdvice.properties"), map2);
            }
            str = context.getString(map2.get(Integer.valueOf(i)).intValue());
        } else if (i >= 6002 && i <= 6072) {
            Map<Integer, Integer> map3 = e;
            if (map3.size() <= 0) {
                sdo.a(sdo.b("pressure/monthAdvice.properties"), map3);
            }
            str = context.getString(map3.get(Integer.valueOf(i)).intValue());
        } else {
            LogUtil.h("PressureMeasureDetailInteractor", "num error = ", Integer.valueOf(i));
            str = "";
        }
        LogUtil.a("PressureMeasureDetailInteractor", "getAdviceFromMap adviceContent = ", str);
        return str;
    }

    public String e() {
        return this.c;
    }

    public void d(Date date, int i, final CommonUiBaseResponse commonUiBaseResponse) {
        if (date == null || commonUiBaseResponse == null) {
            LogUtil.h("PressureMeasureDetailInteractor", "getPressureAdvice parameter error");
        } else {
            this.c = BaseApplication.getContext().getString(R$string.IDS_pressure_advice_4002);
            PressureMeasureAdviceMgr.a(date, i, new CommonUiBaseResponse() { // from class: pwv
                @Override // com.huawei.ui.commonui.base.CommonUiBaseResponse
                public final void onResponse(int i2, Object obj) {
                    pwr.this.b(commonUiBaseResponse, i2, obj);
                }
            });
        }
    }

    /* synthetic */ void b(CommonUiBaseResponse commonUiBaseResponse, int i, Object obj) {
        LogUtil.c("PressureMeasureDetailInteractor", "getPressureAdvice errorCode = ", Integer.valueOf(i));
        if (i == 0 && obj != null) {
            PressureAdeviceRetrunValue pressureAdeviceRetrunValue = (PressureAdeviceRetrunValue) obj;
            if (pressureAdeviceRetrunValue.getError_code() == 0) {
                LogUtil.a("PressureMeasureDetailInteractor", "getPressureAdvice from jni success");
                String e2 = e(pressureAdeviceRetrunValue.getAdvice_num_2());
                if (!TextUtils.isEmpty(e2)) {
                    this.c = e2;
                }
            }
        }
        commonUiBaseResponse.onResponse(i, obj);
    }

    public List<sdf> c(List<HiHealthData> list) {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < list.size(); i++) {
            HiHealthData hiHealthData = list.get(i);
            sdf sdfVar = new sdf();
            long startTime = hiHealthData.getStartTime();
            int intValue = hiHealthData.getIntValue();
            if (intValue <= 0 || intValue >= 100) {
                LogUtil.a("PressureMeasureDetailInteractor", "getDayStressData isStressData = false , stressScore = ", Integer.valueOf(intValue));
            } else {
                int a2 = sdh.a(intValue);
                sdfVar.d(startTime / 1000);
                sdfVar.c(a2);
                sdfVar.b(intValue);
                arrayList.add(sdfVar);
            }
        }
        return arrayList;
    }

    public List<sdf> a(Date date, int i, List<HiStressMetaData> list) {
        ArrayList arrayList = new ArrayList();
        for (int i2 = 0; i2 < i; i2++) {
            long l = jec.l(date);
            long k = jec.k(date);
            List<HiStressMetaData> a2 = a(l, k, list);
            int size = a2.size();
            if (a2.size() > 0) {
                int i3 = 0;
                for (int i4 = 0; i4 < size; i4++) {
                    i3 += a2.get(i4).fetchStressScore();
                }
                int i5 = (int) (i3 / size);
                sdf sdfVar = new sdf();
                sdfVar.d(k);
                sdfVar.b(i5);
                sdfVar.c(sdh.a(i5));
                arrayList.add(sdfVar);
            } else {
                sdf sdfVar2 = new sdf();
                sdfVar2.b(0);
                sdfVar2.c(0);
                sdfVar2.d(k);
                arrayList.add(sdfVar2);
            }
            date = jec.p(date);
        }
        return arrayList;
    }

    public List<sdf> e(Date date, List<HiStressMetaData> list) {
        ArrayList arrayList = new ArrayList();
        int i = 0;
        while (i < 12) {
            Date c = jec.c(date, 1);
            Date b2 = jec.b(jec.d(c, -1));
            long l = jec.l(date);
            long l2 = jec.l(b2);
            List<HiStressMetaData> a2 = a(l, l2, list);
            int size = a2.size();
            if (a2.size() > 0) {
                int i2 = 0;
                for (int i3 = 0; i3 < size; i3++) {
                    i2 += a2.get(i3).fetchStressScore();
                }
                int i4 = (int) (i2 / size);
                sdf sdfVar = new sdf();
                sdfVar.b(i4);
                sdfVar.c(sdh.a(i4));
                sdfVar.d(l2);
                arrayList.add(sdfVar);
            } else {
                sdf sdfVar2 = new sdf();
                sdfVar2.b(0);
                sdfVar2.c(0);
                sdfVar2.d(l2);
                arrayList.add(sdfVar2);
            }
            i++;
            date = c;
        }
        return arrayList;
    }

    private List<HiStressMetaData> a(long j, long j2, List<HiStressMetaData> list) {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < list.size(); i++) {
            long fetchStressStartTime = list.get(i).fetchStressStartTime() / 1000;
            if (fetchStressStartTime >= j && fetchStressStartTime <= j2) {
                arrayList.add(list.get(i));
            }
        }
        LogUtil.c("PressureMeasureDetailInteractor", "getYearPointList isHave = ", Integer.valueOf(arrayList.size()));
        return arrayList;
    }

    public void d(final HiStressMetaData hiStressMetaData, final CommonUiBaseResponse commonUiBaseResponse, final int i) {
        DeviceInfo a2;
        boolean b2 = keq.b(hiStressMetaData);
        LogUtil.a("PressureMeasureDetailInteractor", "setStressData,isAdjustStressDate = ", Boolean.valueOf(b2));
        if (b2 && (a2 = jpt.a("PressureMeasureDetailInteractor")) != null) {
            LogUtil.a("PressureMeasureDetailInteractor", "currentDeviceInfo ", a2.toString());
            jpp.i(a2);
            this.d.b(jpp.d(), hiStressMetaData, new CommonUiBaseResponse() { // from class: pwr.4
                @Override // com.huawei.ui.commonui.base.CommonUiBaseResponse
                public void onResponse(int i2, Object obj) {
                    CommonUiBaseResponse commonUiBaseResponse2 = commonUiBaseResponse;
                    if (commonUiBaseResponse2 != null) {
                        commonUiBaseResponse2.onResponse(i2, Integer.valueOf(i));
                        if (i2 == 0 && i == 0 && cvs.d().isSupportStressAppToDevice()) {
                            jld.b().d(hiStressMetaData);
                        }
                    }
                }
            });
        }
    }

    public static int e(List<sdf> list) {
        Iterator<sdf> it = list.iterator();
        int i = 0;
        while (it.hasNext()) {
            if (it.next().c() > 0) {
                i++;
            }
        }
        Iterator<sdf> it2 = list.iterator();
        int i2 = 0;
        while (it2.hasNext()) {
            i2 += it2.next().c();
        }
        LogUtil.c("PressureMeasureDetailInteractor", "sumPressureVlaue = ", i2 + " size = ", Integer.valueOf(i));
        if (i == 0) {
            LogUtil.c("PressureMeasureDetailInteractor", "size is zero");
            return 0;
        }
        int i3 = (int) (i2 / i);
        LogUtil.c("PressureMeasureDetailInteractor", "avgValue = ", Integer.valueOf(i3));
        return i3;
    }

    public static String b(List<sdf> list) {
        if (list == null || list.size() <= 0) {
            return "--";
        }
        int c = list.get(0).c();
        int c2 = list.get(0).c();
        for (sdf sdfVar : list) {
            if (sdfVar.c() < c2) {
                c2 = sdfVar.c();
            }
        }
        for (sdf sdfVar2 : list) {
            if (sdfVar2.c() > c) {
                c = sdfVar2.c();
            }
        }
        LogUtil.c("PressureMeasureDetailInteractor", "minValue = ", Integer.valueOf(c2), " maxValue = ", Integer.valueOf(c));
        return UnitUtil.e(c2, 1, 0) + Constants.LINK + UnitUtil.e(c, 1, 0);
    }

    public void e(final CommonUiBaseResponse commonUiBaseResponse) {
        eeo.a(new IBaseResponseCallback() { // from class: pws
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public final void d(int i, Object obj) {
                pwr.d(CommonUiBaseResponse.this, i, obj);
            }
        });
    }

    static /* synthetic */ void d(CommonUiBaseResponse commonUiBaseResponse, int i, Object obj) {
        LogUtil.a("PressureMeasureDetailInteractor", "isAlreadyDoPressureAdjust errorCode: ", Integer.valueOf(i), "objData: ", obj);
        commonUiBaseResponse.onResponse(i, obj);
    }

    public void a(Date date, int i, final CommonUiBaseResponse commonUiBaseResponse) {
        if (date == null || commonUiBaseResponse == null) {
            LogUtil.h("PressureMeasureDetailInteractor", "requestStressStatisticDatas date or callback is null");
            return;
        }
        LogUtil.a("PressureMeasureDetailInteractor", "requestStressDetailDatas paraUIDataType = ", Integer.valueOf(i));
        this.d.c(jec.l(date), d(i), new CommonUiBaseResponse() { // from class: pwu
            @Override // com.huawei.ui.commonui.base.CommonUiBaseResponse
            public final void onResponse(int i2, Object obj) {
                pwr.a(CommonUiBaseResponse.this, i2, obj);
            }
        });
    }

    static /* synthetic */ void a(CommonUiBaseResponse commonUiBaseResponse, int i, Object obj) {
        List arrayList = new ArrayList();
        if (i == 0 && (obj instanceof List)) {
            arrayList = (List) obj;
            LogUtil.a("PressureMeasureDetailInteractor", "sumListData.size() = ", Integer.valueOf(arrayList.size()));
        }
        commonUiBaseResponse.onResponse(i, arrayList);
    }

    public void e(long j, long j2, int i, final CommonUiBaseResponse commonUiBaseResponse) {
        LogUtil.a("PressureMeasureDetailInteractor", "requestStatisticData type = ", Integer.valueOf(i));
        if (commonUiBaseResponse == null) {
            LogUtil.h("PressureMeasureDetailInteractor", "requestStatisticData callback is null");
        } else {
            this.d.c(j, j2, d(i), new CommonUiBaseResponse() { // from class: pww
                @Override // com.huawei.ui.commonui.base.CommonUiBaseResponse
                public final void onResponse(int i2, Object obj) {
                    pwr.e(CommonUiBaseResponse.this, i2, obj);
                }
            });
        }
    }

    static /* synthetic */ void e(CommonUiBaseResponse commonUiBaseResponse, int i, Object obj) {
        Object arrayList = new ArrayList();
        if (i == 0 && (obj instanceof List)) {
            arrayList = (List) obj;
        }
        commonUiBaseResponse.onResponse(i, arrayList);
    }

    private FitnessDataQueryDefine.FitnessQueryId d(int i) {
        if (i == 1) {
            return FitnessDataQueryDefine.FitnessQueryId.FITNESS_TYPE_DAY_HISTOGRAM;
        }
        if (i == 2) {
            return FitnessDataQueryDefine.FitnessQueryId.FITNESS_TYPE_WEEK_HISTOGRAM;
        }
        if (i == 3) {
            return FitnessDataQueryDefine.FitnessQueryId.FITNESS_TYPE_MONTH_HISTOGRAM;
        }
        if (i == 4) {
            return FitnessDataQueryDefine.FitnessQueryId.FITNESS_TYPE_YEAR_HISTOGRAM;
        }
        return FitnessDataQueryDefine.FitnessQueryId.FITNESS_TYPE_DEFAULT_HISTOGRAM;
    }
}
