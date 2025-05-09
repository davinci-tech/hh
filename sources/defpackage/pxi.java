package defpackage;

import com.huawei.health.sport.utils.FitnessDataQueryDefine;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.fitnessdatatype.SleepTotalData;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.openalliance.ad.constant.ConfigMapDefValues;
import com.huawei.ui.commonui.base.CommonUiBaseResponse;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes6.dex */
public class pxi {
    private static final Object c = new Object();
    private static pxi d;

    /* renamed from: a, reason: collision with root package name */
    private pxj f16325a;

    private pxi() {
        this.f16325a = null;
        this.f16325a = pxj.b();
    }

    public static pxi e() {
        pxi pxiVar;
        synchronized (pxi.class) {
            if (d == null) {
                d = new pxi();
            }
            pxiVar = d;
        }
        return pxiVar;
    }

    public void a(long j, final FitnessDataQueryDefine.FitnessQueryId fitnessQueryId, final FitnessDataQueryDefine.FitnessSleepTotalDataId fitnessSleepTotalDataId, final CommonUiBaseResponse commonUiBaseResponse) {
        int i;
        LogUtil.a("SCUI_FitnessOldMgrInteractors", "Enter requestGetCoreSleepDetailData");
        synchronized (c) {
            int c2 = FitnessDataQueryDefine.c(fitnessQueryId);
            int a2 = FitnessDataQueryDefine.a(j, fitnessQueryId);
            int e = FitnessDataQueryDefine.e(fitnessQueryId);
            LogUtil.a("SCUI_FitnessOldMgrInteractors", "requestGetCoreSleepDetailData queryID = " + fitnessQueryId + "unitSize = " + a2 + "  unitCount = " + e + "  unitType = " + c2);
            if (c2 == 5 && 12 == e) {
                Date date = new Date(1000 * j);
                int i2 = 0;
                for (int i3 = 0; i3 < 12; i3++) {
                    i2 += FitnessDataQueryDefine.e(jec.n(jec.c(date, i3))) * ConfigMapDefValues.DEFAULT_APPLIST_INTERVAL;
                }
                i = i2;
            } else {
                i = a2;
            }
            pxj pxjVar = this.f16325a;
            if (pxjVar != null) {
                pxjVar.a(j, c2, i, e, new IBaseResponseCallback() { // from class: pxi.2
                    @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                    /* renamed from: onResponse */
                    public void d(int i4, Object obj) {
                        if (obj != null) {
                            LogUtil.a("SCUI_FitnessOldMgrInteractors", "getCoreSleepDetail onResponse objData != null");
                        } else {
                            LogUtil.a("SCUI_FitnessOldMgrInteractors", "getCoreSleepDetail onResponse errCode = " + i4);
                        }
                        switch (AnonymousClass3.f16326a[fitnessQueryId.ordinal()]) {
                            case 1:
                            case 2:
                            case 3:
                            case 4:
                            case 5:
                            case 6:
                            case 7:
                            case 8:
                                if (i4 == 0) {
                                    LogUtil.a("SCUI_FitnessOldMgrInteractors", "process getCoreSleepDetail");
                                    if (obj != null) {
                                        pxi.this.e(fitnessSleepTotalDataId, commonUiBaseResponse, obj);
                                        break;
                                    }
                                } else if (commonUiBaseResponse != null) {
                                    LogUtil.a("SCUI_FitnessOldMgrInteractors", "requestGetCoreSleepDetailData callback.");
                                    commonUiBaseResponse.onResponse(i4, obj);
                                    break;
                                }
                                break;
                            case 9:
                            case 10:
                            case 11:
                            case 12:
                            case 13:
                                CommonUiBaseResponse commonUiBaseResponse2 = commonUiBaseResponse;
                                if (commonUiBaseResponse2 != null) {
                                    commonUiBaseResponse2.onResponse(i4, obj);
                                    break;
                                }
                                break;
                        }
                    }
                });
            }
        }
        LogUtil.a("SCUI_FitnessOldMgrInteractors", "Leave requestGetCoreSleepDetailData");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(FitnessDataQueryDefine.FitnessSleepTotalDataId fitnessSleepTotalDataId, CommonUiBaseResponse commonUiBaseResponse, Object obj) {
        int i;
        LogUtil.a("SCUI_FitnessOldMgrInteractors", "Enter processFitnessSleepStatisticData");
        List list = (List) obj;
        int i2 = AnonymousClass3.e[fitnessSleepTotalDataId.ordinal()];
        if (i2 == 1) {
            Iterator it = list.iterator();
            i = 0;
            while (it.hasNext()) {
                i += ((SleepTotalData) it.next()).getTotalSleepTime();
            }
            LogUtil.a("SCUI_FitnessOldMgrInteractors", "FITNESS_TOTAL_SLEEP_TIME totalSleepTime = " + i);
        } else if (i2 == 2) {
            Iterator it2 = list.iterator();
            i = 0;
            while (it2.hasNext()) {
                i += ((SleepTotalData) it2.next()).getTotalSleepTime();
            }
        } else if (i2 == 3) {
            Iterator it3 = list.iterator();
            i = 0;
            while (it3.hasNext()) {
                i += ((SleepTotalData) it3.next()).getTotalSleepTime();
            }
        } else if (i2 != 4) {
            if (commonUiBaseResponse != null) {
                commonUiBaseResponse.onResponse(0, obj);
                return;
            }
            return;
        } else {
            Iterator it4 = list.iterator();
            i = 0;
            while (it4.hasNext()) {
                i += ((SleepTotalData) it4.next()).getTotalSleepTime();
            }
        }
        if (commonUiBaseResponse != null) {
            commonUiBaseResponse.onResponse(0, Integer.valueOf(i));
        }
        LogUtil.a("SCUI_FitnessOldMgrInteractors", "Leave processFitnessSleepStatisticData");
    }

    /* renamed from: pxi$3, reason: invalid class name */
    static /* synthetic */ class AnonymousClass3 {

        /* renamed from: a, reason: collision with root package name */
        static final /* synthetic */ int[] f16326a;
        static final /* synthetic */ int[] e;

        static {
            int[] iArr = new int[FitnessDataQueryDefine.FitnessSleepTotalDataId.values().length];
            e = iArr;
            try {
                iArr[FitnessDataQueryDefine.FitnessSleepTotalDataId.FITNESS_TOTAL_SLEEP_TIME.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                e[FitnessDataQueryDefine.FitnessSleepTotalDataId.FITNESS_DEEP_SLEEP_TIME.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                e[FitnessDataQueryDefine.FitnessSleepTotalDataId.FITNESS_SHALLOW_SLEEP_TIME.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                e[FitnessDataQueryDefine.FitnessSleepTotalDataId.FITNESS_WAKEUP_TIME.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                e[FitnessDataQueryDefine.FitnessSleepTotalDataId.FITNESS_UNSPECIFIC_SLEEP_TIME.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            int[] iArr2 = new int[FitnessDataQueryDefine.FitnessQueryId.values().length];
            f16326a = iArr2;
            try {
                iArr2[FitnessDataQueryDefine.FitnessQueryId.FITNESS_TYPE_SLEEP_DAY_STATISTIC.ordinal()] = 1;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                f16326a[FitnessDataQueryDefine.FitnessQueryId.FITNESS_TYPE_SLEEP_WEEK_STATISTIC.ordinal()] = 2;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                f16326a[FitnessDataQueryDefine.FitnessQueryId.FITNESS_TYPE_SLEEP_MONTH_STATISTIC.ordinal()] = 3;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                f16326a[FitnessDataQueryDefine.FitnessQueryId.FITNESS_TYPE_SLEEP_YEAR_STATISTIC.ordinal()] = 4;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                f16326a[FitnessDataQueryDefine.FitnessQueryId.FITNESS_TYPE_SLEEP_DAY_STATISTIC_DETAIL.ordinal()] = 5;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                f16326a[FitnessDataQueryDefine.FitnessQueryId.FITNESS_TYPE_SLEEP_WEEK_STATISTIC_DETAIL.ordinal()] = 6;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                f16326a[FitnessDataQueryDefine.FitnessQueryId.FITNESS_TYPE_SLEEP_MONTH_STATISTIC_DETAIL.ordinal()] = 7;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                f16326a[FitnessDataQueryDefine.FitnessQueryId.FITNESS_TYPE_SLEEP_YEAR_STATISTIC_DETAIL.ordinal()] = 8;
            } catch (NoSuchFieldError unused13) {
            }
            try {
                f16326a[FitnessDataQueryDefine.FitnessQueryId.FITNESS_TYPE_UP_SLEEP_DETAIL.ordinal()] = 9;
            } catch (NoSuchFieldError unused14) {
            }
            try {
                f16326a[FitnessDataQueryDefine.FitnessQueryId.FITNESS_TYPE_SLEEP_DAY_HISTOGRAM.ordinal()] = 10;
            } catch (NoSuchFieldError unused15) {
            }
            try {
                f16326a[FitnessDataQueryDefine.FitnessQueryId.FITNESS_TYPE_SLEEP_WEEK_HISTOGRAM.ordinal()] = 11;
            } catch (NoSuchFieldError unused16) {
            }
            try {
                f16326a[FitnessDataQueryDefine.FitnessQueryId.FITNESS_TYPE_SLEEP_MONTH_HISTOGRAM.ordinal()] = 12;
            } catch (NoSuchFieldError unused17) {
            }
            try {
                f16326a[FitnessDataQueryDefine.FitnessQueryId.FITNESS_TYPE_SLEEP_YEAR_HISTOGRAM.ordinal()] = 13;
            } catch (NoSuchFieldError unused18) {
            }
            try {
                f16326a[FitnessDataQueryDefine.FitnessQueryId.FITNESS_TYPE_DAY_HISTOGRAM.ordinal()] = 14;
            } catch (NoSuchFieldError unused19) {
            }
            try {
                f16326a[FitnessDataQueryDefine.FitnessQueryId.FITNESS_TYPE_WEEK_HISTOGRAM.ordinal()] = 15;
            } catch (NoSuchFieldError unused20) {
            }
            try {
                f16326a[FitnessDataQueryDefine.FitnessQueryId.FITNESS_TYPE_MONTH_HISTOGRAM.ordinal()] = 16;
            } catch (NoSuchFieldError unused21) {
            }
            try {
                f16326a[FitnessDataQueryDefine.FitnessQueryId.FITNESS_TYPE_YEAR_HISTOGRAM.ordinal()] = 17;
            } catch (NoSuchFieldError unused22) {
            }
        }
    }
}
