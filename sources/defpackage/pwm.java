package defpackage;

import com.huawei.haf.application.BaseApplication;
import com.huawei.health.sport.utils.FitnessDataQueryDefine;
import com.huawei.health.userprofilemgr.model.BaseResponseCallback;
import com.huawei.hihealth.HiHealthClient;
import com.huawei.hihealth.HiTimeInterval;
import com.huawei.hihealth.api.HiHealthManager;
import com.huawei.hihealth.data.listener.HiMultiDataClientListener;
import com.huawei.hihealth.data.model.HiStressMetaData;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.base.CommonUiBaseResponse;
import health.compact.a.HiDateUtil;
import health.compact.a.LogAnonymous;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes6.dex */
public class pwm {
    private static pwm b;
    private static final Object c = new Object();

    /* renamed from: a, reason: collision with root package name */
    private kor f16303a;

    private pwm() {
        this.f16303a = null;
        this.f16303a = kor.a();
    }

    public static pwm a() {
        pwm pwmVar;
        synchronized (pwm.class) {
            if (b == null) {
                b = new pwm();
            }
            pwmVar = b;
        }
        return pwmVar;
    }

    public void a(final CommonUiBaseResponse commonUiBaseResponse) {
        synchronized (c) {
            kor korVar = this.f16303a;
            if (korVar != null) {
                korVar.d(new IBaseResponseCallback() { // from class: pwm.2
                    @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                    /* renamed from: onResponse */
                    public void d(int i, Object obj) {
                        if (commonUiBaseResponse != null) {
                            LogUtil.a("SCUI_FitnessMgrInteractors", "requsetGetGoogleFitnessDataPonitDetailByDataType");
                            commonUiBaseResponse.onResponse(i, obj);
                        }
                    }
                });
            }
        }
    }

    public void d(final CommonUiBaseResponse commonUiBaseResponse) {
        synchronized (c) {
            kor korVar = this.f16303a;
            if (korVar != null) {
                korVar.a(new IBaseResponseCallback() { // from class: pwm.5
                    @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                    /* renamed from: onResponse */
                    public void d(int i, Object obj) {
                        if (commonUiBaseResponse != null) {
                            LogUtil.a("SCUI_FitnessMgrInteractors", "requsetGetGoogleFitnessDataPonitSegentByDataType");
                            commonUiBaseResponse.onResponse(i, obj);
                        }
                    }
                });
            }
        }
    }

    public void a(long j, final CommonUiBaseResponse commonUiBaseResponse) {
        synchronized (c) {
            this.f16303a.d(new IBaseResponseCallback() { // from class: pwm.8
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public void d(int i, Object obj) {
                    List list = (List) obj;
                    ArrayList arrayList = new ArrayList();
                    if (list != null) {
                        pwm.this.c((List<HiHealthClient>) list, (ArrayList<pwb>) arrayList);
                    }
                    if (arrayList.size() == 1) {
                        arrayList.clear();
                        LogUtil.a("SCUI_FitnessMgrInteractors", "Step data origin is only phone.");
                    }
                    commonUiBaseResponse.onResponse(i, arrayList);
                }
            }, j);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(List<HiHealthClient> list, ArrayList<pwb> arrayList) {
        for (HiHealthClient hiHealthClient : list) {
            if (hiHealthClient.getHiDeviceInfo() != null) {
                ReleaseLogUtil.e("SCUI_FitnessMgrInteractors", "handleRequestResult deviceName: ", LogAnonymous.e(hiHealthClient.getHiDeviceInfo().getDeviceName()), ", model: ", hiHealthClient.getHiDeviceInfo().getModel(), ", deviceType ", Integer.valueOf(hiHealthClient.getHiDeviceInfo().getDeviceType()));
                LogUtil.c("SCUI_FitnessMgrInteractors", "handleRequestResult deviceUniqueCode:", hiHealthClient.getHiDeviceInfo().getDeviceUniqueCode());
            }
            pwb pwbVar = new pwb();
            if (hiHealthClient.getHiDeviceInfo() != null) {
                int deviceType = hiHealthClient.getHiDeviceInfo().getDeviceType();
                String deviceName = hiHealthClient.getHiDeviceInfo().getDeviceName();
                String prodId = hiHealthClient.getHiDeviceInfo().getProdId();
                String model = hiHealthClient.getHiDeviceInfo().getModel();
                String deviceUniqueCode = hiHealthClient.getHiDeviceInfo().getDeviceUniqueCode();
                if (deviceType == 30) {
                    deviceType = 32;
                }
                if (deviceType != 32 || (model != null && !"".equals(model))) {
                    if (deviceType == 51) {
                        deviceType = 23;
                    }
                    Iterator<pwb> it = arrayList.iterator();
                    while (true) {
                        if (it.hasNext()) {
                            pwb next = it.next();
                            if (next.c() != deviceType || !a(next.e(), deviceUniqueCode)) {
                            }
                        } else if (pwbVar.c() < 10000) {
                            pwbVar.a(deviceType);
                            pwbVar.c(prodId);
                            pwbVar.b(deviceName);
                            pwbVar.d(model);
                            pwbVar.a(deviceUniqueCode);
                            arrayList.add(pwbVar);
                        }
                    }
                }
            }
        }
    }

    private boolean a(String str, String str2) {
        return str.length() < str2.length() ? str2.contains(str) : str.contains(str2);
    }

    /* renamed from: pwm$3, reason: invalid class name */
    static /* synthetic */ class AnonymousClass3 {

        /* renamed from: a, reason: collision with root package name */
        static final /* synthetic */ int[] f16305a;

        static {
            int[] iArr = new int[FitnessDataQueryDefine.FitnessQueryId.values().length];
            f16305a = iArr;
            try {
                iArr[FitnessDataQueryDefine.FitnessQueryId.FITNESS_TYPE_DAY_HISTOGRAM.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f16305a[FitnessDataQueryDefine.FitnessQueryId.FITNESS_TYPE_WEEK_HISTOGRAM.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f16305a[FitnessDataQueryDefine.FitnessQueryId.FITNESS_TYPE_MONTH_HISTOGRAM.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f16305a[FitnessDataQueryDefine.FitnessQueryId.FITNESS_TYPE_YEAR_HISTOGRAM.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    public static long d(long j, FitnessDataQueryDefine.FitnessQueryId fitnessQueryId) {
        Date date = new Date(j * 1000);
        int i = AnonymousClass3.f16305a[fitnessQueryId.ordinal()];
        if (i == 1) {
            return jec.k(date);
        }
        if (i == 2) {
            return jec.l(jec.v(date));
        }
        if (i == 3) {
            return jec.l(jec.m(date));
        }
        if (i != 4) {
            return 1L;
        }
        return jec.l(jec.y(date));
    }

    public void c(long j, final FitnessDataQueryDefine.FitnessQueryId fitnessQueryId, final CommonUiBaseResponse commonUiBaseResponse) {
        LogUtil.a("SCUI_FitnessMgrInteractors", "Enter getStressDatasForDiagram!");
        synchronized (c) {
            long d = d(j, fitnessQueryId);
            LogUtil.a("SCUI_FitnessMgrInteractors", "getStressDatasForDiagram 0000 startTime = " + j + "   endTime = " + d);
            LogUtil.a("SCUI_FitnessMgrInteractors", "getStressDatasForDiagram startTime = " + new Date(j * 1000) + "   endTime = " + new Date(1000 * d));
            this.f16303a.c(j, d, new IBaseResponseCallback() { // from class: pwm.7
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public void d(int i, Object obj) {
                    int i2 = -1;
                    Object obj2 = -1;
                    if (i == 0) {
                        int i3 = AnonymousClass3.f16305a[fitnessQueryId.ordinal()];
                        if (i3 == 1 || i3 == 2 || i3 == 3 || i3 == 4) {
                            LogUtil.a("SCUI_FitnessMgrInteractors", "getStressDatasForDiagram err_code 0");
                            if (obj != null) {
                                obj2 = (List) obj;
                            }
                        } else {
                            obj2 = 1;
                        }
                        i2 = 0;
                    }
                    if (commonUiBaseResponse != null) {
                        LogUtil.a("SCUI_FitnessMgrInteractors", "getStressDatasForDiagram callbackResponse.onResponse sucess resultCode = " + i2 + " resultData = " + obj2);
                        commonUiBaseResponse.onResponse(i2, obj2);
                    }
                }
            });
        }
        LogUtil.a("SCUI_FitnessMgrInteractors", "Leave getStressDatasForDiagram!");
    }

    public void c(long j, long j2, final FitnessDataQueryDefine.FitnessQueryId fitnessQueryId, final CommonUiBaseResponse commonUiBaseResponse) {
        LogUtil.a("SCUI_FitnessMgrInteractors", "Enter requestPressureMeasureStatistic!");
        synchronized (c) {
            this.f16303a.a(j, j2 - 1000, new IBaseResponseCallback() { // from class: pwm.6
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public void d(int i, Object obj) {
                    int i2 = -1;
                    Object obj2 = -1;
                    if (i == 0) {
                        int i3 = AnonymousClass3.f16305a[fitnessQueryId.ordinal()];
                        if (i3 == 1 || i3 == 2 || i3 == 3 || i3 == 4) {
                            LogUtil.a("SCUI_FitnessMgrInteractors", "requestPressureMeasureDetail err_code 1");
                            if (obj != null) {
                                obj2 = (List) obj;
                            }
                        } else {
                            obj2 = 1;
                        }
                        i2 = 0;
                    }
                    if (commonUiBaseResponse != null) {
                        LogUtil.a("SCUI_FitnessMgrInteractors", "requestPressureMeasureStatistic callbackResponse.onResponse sucess resultCode1 = " + i2 + " resultData1 = " + obj2);
                        commonUiBaseResponse.onResponse(i2, obj2);
                    }
                }
            });
        }
    }

    public void b(String str, HiStressMetaData hiStressMetaData, final CommonUiBaseResponse commonUiBaseResponse) {
        LogUtil.a("SCUI_FitnessMgrInteractors", "Enter setStressData!");
        synchronized (c) {
            this.f16303a.d(str, hiStressMetaData, new IBaseResponseCallback() { // from class: pwm.10
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public void d(int i, Object obj) {
                    CommonUiBaseResponse commonUiBaseResponse2 = commonUiBaseResponse;
                    if (commonUiBaseResponse2 != null) {
                        commonUiBaseResponse2.onResponse(i, obj);
                    }
                }
            });
        }
        LogUtil.a("SCUI_FitnessMgrInteractors", "Leave setStressData!");
    }

    public void c(long j, final CommonUiBaseResponse commonUiBaseResponse) {
        this.f16303a.d(new IBaseResponseCallback() { // from class: pwm.4
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i, Object obj) {
                if (!(obj instanceof List)) {
                    LogUtil.h("SCUI_FitnessMgrInteractors", "getFitnessStepDataOrigin objData is not instanceof List");
                    commonUiBaseResponse.onResponse(i, null);
                } else {
                    ArrayList arrayList = new ArrayList(10);
                    pwm.this.c((List<HiHealthClient>) obj, (ArrayList<pwb>) arrayList);
                    commonUiBaseResponse.onResponse(i, arrayList);
                }
            }
        }, j);
    }

    public void d(List<Integer> list, long j, final BaseResponseCallback<List<pwb>> baseResponseCallback) {
        if (baseResponseCallback == null) {
            LogUtil.h("SCUI_FitnessMgrInteractors", "getDataOriginByTypes: callback is null");
            return;
        }
        if (koq.b(list)) {
            LogUtil.h("SCUI_FitnessMgrInteractors", "getDataOriginByTypes: query types is empty");
            baseResponseCallback.onResponse(-1, null);
            return;
        }
        LogUtil.a("SCUI_FitnessMgrInteractors", "getDataOriginByTypesMergeResult: request types ", list);
        HiTimeInterval hiTimeInterval = new HiTimeInterval();
        hiTimeInterval.setStartTime(HiDateUtil.t(j));
        hiTimeInterval.setEndTime(HiDateUtil.f(j));
        synchronized (c) {
            HiHealthManager.d(BaseApplication.e()).fetchDataSourceByTypes(list, hiTimeInterval, true, new HiMultiDataClientListener() { // from class: pwm.1
                @Override // com.huawei.hihealth.data.listener.HiMultiDataClientListener
                public void onMultiTypeResult(Map<Integer, List<HiHealthClient>> map) {
                }

                @Override // com.huawei.hihealth.data.listener.HiMultiDataClientListener
                public void onMergeTypeResult(List<HiHealthClient> list2) {
                    if (koq.b(list2)) {
                        LogUtil.h("SCUI_FitnessMgrInteractors", "onMergeTypeResult: response result list is empty");
                        baseResponseCallback.onResponse(-1, null);
                        return;
                    }
                    ArrayList arrayList = new ArrayList(10);
                    pwm.this.c(list2, (ArrayList<pwb>) arrayList);
                    pwm.this.a((ArrayList<pwb>) arrayList);
                    if (arrayList.size() == 1) {
                        arrayList.clear();
                        LogUtil.a("SCUI_FitnessMgrInteractors", "onMergeTypeResult Step data origin is only phone.");
                    }
                    LogUtil.a("SCUI_FitnessMgrInteractors", "onMergeTypeResult: ", Integer.valueOf(arrayList.size()));
                    baseResponseCallback.onResponse(0, arrayList);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(ArrayList<pwb> arrayList) {
        Iterator<pwb> it = arrayList.iterator();
        while (it.hasNext()) {
            if ("-1".equals(it.next().e())) {
                LogUtil.a("SCUI_FitnessMgrInteractors", "filterInputDataType: DeviceUniqueCode is -1");
                it.remove();
            }
        }
    }
}
