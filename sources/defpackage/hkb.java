package defpackage;

import com.huawei.healthcloud.plugintrack.model.SportDetailChartDataType;
import com.huawei.healthcloud.plugintrack.model.TrackLineChartHolderImpl;
import com.huawei.hwfoundationmodel.trackmodel.HeartRateData;
import com.huawei.hwfoundationmodel.trackmodel.MotionPath;
import com.huawei.hwfoundationmodel.trackmodel.MotionPathSimplify;
import com.huawei.hwfoundationmodel.trackmodel.StepRateData;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.UnitUtil;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

/* loaded from: classes4.dex */
public class hkb {
    private TrackLineChartHolderImpl b;
    private hjw c;
    private long e;

    public hkb(TrackLineChartHolderImpl trackLineChartHolderImpl, hjw hjwVar) {
        this.b = trackLineChartHolderImpl;
        this.c = hjwVar;
    }

    public void a(SportDetailChartDataType sportDetailChartDataType, MotionPathSimplify motionPathSimplify, MotionPath motionPath) {
        LogUtil.a("Track_TrackLineChartHolderFiller", "initData dataType:", sportDetailChartDataType);
        if (this.b == null || this.c == null) {
            LogUtil.h("Track_TrackLineChartHolderFiller", "initData mTrackLineChartHolder or mTrackDetailDataManager is null,return");
            return;
        }
        if (sportDetailChartDataType == null) {
            LogUtil.h("Track_TrackLineChartHolderFiller", "initData dataType null,return");
            return;
        }
        if (motionPath == null) {
            LogUtil.h("Track_TrackLineChartHolderFiller", "initData motionPath null,return");
        } else if (motionPathSimplify == null) {
            LogUtil.h("Track_TrackLineChartHolderFiller", "initData motionPathSimplify null,return");
        } else {
            a(sportDetailChartDataType, motionPath, motionPathSimplify.requestStartTime(), motionPathSimplify.requestTotalTime(), motionPathSimplify);
        }
    }

    /* renamed from: hkb$3, reason: invalid class name */
    static /* synthetic */ class AnonymousClass3 {
        static final /* synthetic */ int[] c;

        static {
            int[] iArr = new int[SportDetailChartDataType.values().length];
            c = iArr;
            try {
                iArr[SportDetailChartDataType.HEART_RATE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                c[SportDetailChartDataType.STEP_RATE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                c[SportDetailChartDataType.ALTITUDE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                c[SportDetailChartDataType.SPEED_RATE.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                c[SportDetailChartDataType.SWOLF.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                c[SportDetailChartDataType.REALTIME_PACE.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                c[SportDetailChartDataType.PULL_FREQ.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                c[SportDetailChartDataType.GROUND_CONTACT_TIME.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                c[SportDetailChartDataType.HANG_TIME.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                c[SportDetailChartDataType.GROUND_HANG_TIME_RATE.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                c[SportDetailChartDataType.GROUND_IMPACT_ACCELERATION.ordinal()] = 11;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                c[SportDetailChartDataType.VERTICAL_OSCILLATION.ordinal()] = 12;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                c[SportDetailChartDataType.VERTICAL_RATIO.ordinal()] = 13;
            } catch (NoSuchFieldError unused13) {
            }
            try {
                c[SportDetailChartDataType.GC_TIME_BALANCE.ordinal()] = 14;
            } catch (NoSuchFieldError unused14) {
            }
            try {
                c[SportDetailChartDataType.ACTIVE_PEAK.ordinal()] = 15;
            } catch (NoSuchFieldError unused15) {
            }
            try {
                c[SportDetailChartDataType.JUMP_HEIGHT.ordinal()] = 16;
            } catch (NoSuchFieldError unused16) {
            }
            try {
                c[SportDetailChartDataType.JUMP_TIME.ordinal()] = 17;
            } catch (NoSuchFieldError unused17) {
            }
            try {
                c[SportDetailChartDataType.SPO2.ordinal()] = 18;
            } catch (NoSuchFieldError unused18) {
            }
            try {
                c[SportDetailChartDataType.CADENCE.ordinal()] = 19;
            } catch (NoSuchFieldError unused19) {
            }
            try {
                c[SportDetailChartDataType.PADDLE_FREQUENCY.ordinal()] = 20;
            } catch (NoSuchFieldError unused20) {
            }
            try {
                c[SportDetailChartDataType.POWER.ordinal()] = 21;
            } catch (NoSuchFieldError unused21) {
            }
            try {
                c[SportDetailChartDataType.SKIPPING_SPEED.ordinal()] = 22;
            } catch (NoSuchFieldError unused22) {
            }
            try {
                c[SportDetailChartDataType.HEART_RATE_RECOVERY.ordinal()] = 23;
            } catch (NoSuchFieldError unused23) {
            }
            try {
                c[SportDetailChartDataType.PEAK_WEIGHT.ordinal()] = 24;
            } catch (NoSuchFieldError unused24) {
            }
        }
    }

    private void a(SportDetailChartDataType sportDetailChartDataType, MotionPath motionPath, long j, long j2, MotionPathSimplify motionPathSimplify) {
        switch (AnonymousClass3.c[sportDetailChartDataType.ordinal()]) {
            case 1:
                a(motionPath, j);
                break;
            case 2:
                d(motionPath, j, j2);
                break;
            case 3:
                b(motionPath, j);
                break;
            case 4:
                c(motionPath, j);
                break;
            case 5:
                c(motionPath);
                break;
            case 6:
                a(motionPath, motionPathSimplify);
                break;
            case 7:
                e(motionPath);
                break;
            case 8:
            case 9:
            case 10:
            case 11:
            case 12:
            case 13:
            case 14:
            case 15:
                d(motionPath, motionPathSimplify);
                break;
            case 16:
            case 17:
                a(motionPath);
                break;
            case 18:
                c(motionPath, motionPathSimplify);
                break;
            case 19:
                a(motionPath, j, j2, motionPathSimplify);
                break;
            case 20:
                a(motionPath, j, j2);
                break;
            case 21:
                b(motionPath, j, j2);
                break;
            case 22:
                c(motionPath, j, j2);
                break;
            case 23:
                b(motionPath);
                break;
            case 24:
                e(motionPath, j, j2);
                break;
            default:
                LogUtil.h("Track_TrackLineChartHolderFiller", "fillData unknown dataType:", sportDetailChartDataType);
                break;
        }
    }

    private void b(MotionPath motionPath) {
        if (motionPath == null) {
            LogUtil.b("Track_TrackLineChartHolderFiller", "in initRecoveryHeartRateData motionPath is null");
            return;
        }
        if (motionPath.requestHeartRecoveryRateList() == null) {
            LogUtil.b("Track_TrackLineChartHolderFiller", "requestTrackList null,return");
            return;
        }
        if (motionPath.requestHeartRecoveryRateList().size() > 0) {
            ArrayList<HeartRateData> arrayList = new ArrayList<>(16);
            arrayList.addAll(motionPath.requestHeartRecoveryRateList());
            this.b.i(arrayList);
            this.b.b(5);
            this.b.c((r0.get(r0.size() - 1).acquireTime() - r0.get(0).acquireTime()) / 1000.0f);
        }
    }

    private void a(MotionPath motionPath) {
        if (motionPath.requestJumpDataList() == null) {
            LogUtil.h("Track_TrackLineChartHolderFiller", "requestTrackList null,return");
            return;
        }
        if (!this.c.w() || this.c.e().requestSportType() == 271) {
            ArrayList<ixt> arrayList = new ArrayList<>(16);
            arrayList.addAll(motionPath.requestJumpDataList());
            this.b.b(arrayList);
            this.b.d(5);
            this.b.c(((int) this.c.e().requestTotalTime()) / 1000);
        }
    }

    private void a(MotionPath motionPath, long j) {
        if (motionPath.requestHeartRateList() == null) {
            LogUtil.h("Track_TrackLineChartHolderFiller", "fillHeartRateData motionPath requestHeartRateList null,return");
            return;
        }
        if (!this.c.z() || this.c.e().requestSportType() == 512) {
            ArrayList<HeartRateData> arrayList = new ArrayList<>(16);
            List<kny> heartRateAreaList = motionPath.getHeartRateAreaList();
            if (koq.c(heartRateAreaList)) {
                this.b.n(heartRateAreaList);
            }
            this.b.d(arrayList);
            ArrayList<HeartRateData> requestHeartRateList = motionPath.requestHeartRateList();
            this.b.c(motionPath.requestInvalidHeartRateList());
            int d = sqb.d(requestHeartRateList, j);
            this.b.b(d);
            String extendDataString = this.c.e().getExtendDataString("isTrustHeartRate");
            LogUtil.a("Track_TrackLineChartHolderFiller", "initHeartRateData hasTrustHeartRate", extendDataString);
            ArrayList<HeartRateData> requestInvalidHeartRateList = motionPath.requestInvalidHeartRateList();
            if (hjh.b(requestHeartRateList, requestInvalidHeartRateList)) {
                hjh.d(requestHeartRateList, extendDataString, d, arrayList);
                if (requestHeartRateList.size() - 1 > 0) {
                    e(d, requestHeartRateList, extendDataString);
                    return;
                } else {
                    this.b.c();
                    return;
                }
            }
            this.b.e(hjh.c(requestHeartRateList, requestInvalidHeartRateList, arrayList, d, extendDataString));
            if ((requestHeartRateList.size() + requestInvalidHeartRateList.size()) - 1 > 0) {
                b(d, requestHeartRateList, requestInvalidHeartRateList, extendDataString);
            } else {
                this.b.c();
            }
        }
    }

    private void b(int i, List<HeartRateData> list, List<HeartRateData> list2, String str) {
        if (str == null) {
            this.b.e(i * ((list.size() + list2.size()) - 1));
            return;
        }
        if ("1".equals(str)) {
            long acquireTime = list.get(0).acquireTime();
            long acquireTime2 = list.get(list.size() - 1).acquireTime();
            long acquireTime3 = list2.get(0).acquireTime();
            long acquireTime4 = list2.get(list2.size() - 1).acquireTime();
            this.b.e((Math.max(acquireTime2, acquireTime4) - Math.min(acquireTime, acquireTime3)) / 1000);
        }
    }

    private void e(int i, List<HeartRateData> list, String str) {
        if (str == null) {
            this.b.e(i * (list.size() - 1));
        } else if ("1".equals(str)) {
            this.b.e((list.get(list.size() - 1).acquireTime() - list.get(0).acquireTime()) / 1000.0f);
        }
    }

    private void a(MotionPath motionPath, long j, long j2, MotionPathSimplify motionPathSimplify) {
        if (motionPath.requestRidePostureDataList() == null) {
            LogUtil.h("Track_TrackLineChartHolderFiller", "fillCadenceRateData motionPath requestRidePostureDataList null,return");
            return;
        }
        if (this.c.u()) {
            return;
        }
        ArrayList<ffn> arrayList = new ArrayList<>(16);
        this.b.d(arrayList);
        if (d(motionPath)) {
            LogUtil.a("Track_TrackLineChartHolderFiller", "Ride Accessories or Cross Trainer handleCadenceRateData start");
            a(motionPath, j, j2, arrayList, motionPathSimplify);
        } else {
            LogUtil.a("Track_TrackLineChartHolderFiller", "AW70 handleCadenceRateDataToBike start");
            e(motionPath, arrayList);
        }
        this.b.b(TimeUnit.MILLISECONDS.toSeconds(j2));
    }

    private boolean d(MotionPath motionPath) {
        List<ffn> requestRidePostureDataList = motionPath.requestRidePostureDataList();
        if (requestRidePostureDataList != null && requestRidePostureDataList.size() >= 2 && requestRidePostureDataList.get(0) != null && requestRidePostureDataList.get(1) != null && requestRidePostureDataList.get(0).acquireTime() > 1080000000 && requestRidePostureDataList.get(1).acquireTime() > 1080000000) {
            LogUtil.a("Track_TrackLineChartHolderFiller", "isAbsoluteTimestamp is true");
            return true;
        }
        LogUtil.a("Track_TrackLineChartHolderFiller", "isAbsoluteTimestamp is false");
        return false;
    }

    private void a(MotionPath motionPath, long j, long j2) {
        if (motionPath.requestPaddleFrequencyList() == null) {
            LogUtil.h("Track_TrackLineChartHolderFiller", "fillPaddleFrequencyData motionPath requestPaddleFrequencyList null,return");
        } else if (!this.c.aa() || this.c.e().requestSportType() == 274) {
            ArrayList arrayList = new ArrayList(16);
            this.b.e((List<knw>) arrayList);
            b(motionPath, j, j2, arrayList);
        }
    }

    private void e(MotionPath motionPath, long j, long j2) {
        if (motionPath.requestWeightList() == null) {
            LogUtil.h("Track_TrackLineChartHolderFiller", "initWeightData motionPath requestPowerList null,return");
            return;
        }
        int requestSportType = this.c.e().requestSportType();
        if (!this.c.ae() || requestSportType == 274) {
            ArrayList<kom> arrayList = new ArrayList<>(16);
            this.b.f(arrayList);
            e(motionPath, j, j2, arrayList);
        }
    }

    private void e(MotionPath motionPath, long j, long j2, ArrayList<kom> arrayList) {
        List<kom> requestWeightList = motionPath.requestWeightList();
        if (!motionPath.isValidWeightData()) {
            LogUtil.h("Track_TrackLineChartHolderFiller", "handleWeightData weightListTemp is null");
            return;
        }
        if (UnitUtil.h()) {
            Iterator<kom> it = requestWeightList.iterator();
            while (it.hasNext()) {
                it.next().d((int) Math.round(UnitUtil.h(r1.d())));
            }
        }
        int d = sqb.d(requestWeightList, j);
        this.b.o(d);
        kom komVar = requestWeightList.get(0);
        if (komVar == null) {
            LogUtil.h("Track_TrackLineChartHolderFiller", "handleWeightData data is null");
        } else {
            arrayList.add(new kom(0L, komVar.d()));
            d(j2, arrayList, d, requestWeightList);
        }
    }

    private void d(long j, ArrayList<kom> arrayList, int i, List<kom> list) {
        Iterator<kom> it = list.iterator();
        int i2 = 0;
        long j2 = 0;
        while (it.hasNext()) {
            kom next = it.next();
            long j3 = i;
            i2++;
            long j4 = i2 * j3;
            if (it.hasNext()) {
                arrayList.add(new kom(j4, next.d()));
            } else {
                long seconds = ((int) TimeUnit.MILLISECONDS.toSeconds(j)) - j2;
                if (seconds < 0) {
                    arrayList.add(new kom(j4, next.d()));
                } else {
                    long j5 = seconds - (seconds % 5);
                    if (j5 == 0) {
                        j2 += 5;
                        arrayList.add(new kom(j2, next.d()));
                    } else if (j5 > j3) {
                        arrayList.add(new kom(j4, next.d()));
                    } else {
                        j2 += j5;
                        arrayList.add(new kom(j2, next.d()));
                    }
                }
            }
            j2 = j4;
        }
    }

    private void b(MotionPath motionPath, long j, long j2) {
        if (motionPath.requestPowerList() == null) {
            LogUtil.h("Track_TrackLineChartHolderFiller", "fillPowerData motionPath requestPowerList null,return");
            return;
        }
        int requestSportType = this.c.e().requestSportType();
        if (!this.c.ai() || requestSportType == 274 || requestSportType == 273 || requestSportType == 265) {
            ArrayList arrayList = new ArrayList(16);
            this.b.c((List<koc>) arrayList);
            e(motionPath, j, j2, (List<koc>) arrayList);
        }
    }

    private void c(MotionPath motionPath, long j, long j2) {
        if (motionPath.requestSkippingSpeedList() == null) {
            LogUtil.h("Track_TrackLineChartHolderFiller", "fillPowerData motionPath requestPowerList null,return");
            return;
        }
        int requestSportType = this.c.e().requestSportType();
        if (!this.c.aj() || requestSportType == 283) {
            ArrayList arrayList = new ArrayList(16);
            a(motionPath, j, j2, arrayList);
            this.b.b((List<kob>) arrayList);
        }
    }

    private void d(MotionPath motionPath, long j, long j2) {
        if (motionPath.requestStepRateList() == null) {
            LogUtil.h("Track_TrackLineChartHolderFiller", "fillStepRateData motionPath requestStepRateList null,return");
            return;
        }
        if (!this.c.ar() || this.c.e().requestSportType() == 512) {
            LogUtil.a("Track_TrackLineChartHolderFiller", "initStepRateData");
            ArrayList<StepRateData> arrayList = new ArrayList<>(16);
            this.b.a(arrayList);
            this.b.i(sqb.d(motionPath.requestStepRateList(), j));
            hjh.a(motionPath.requestStepRateList(), j, j2, arrayList);
            this.b.g(TimeUnit.MILLISECONDS.toSeconds(j2));
        }
    }

    private void a(MotionPath motionPath, long j, long j2, List<kob> list) {
        List<kob> requestSkippingSpeedList = motionPath.requestSkippingSpeedList();
        if (!motionPath.isValidSkippingData()) {
            LogUtil.h("Track_TrackLineChartHolderFiller", "handlePowerData listTemp is null");
            return;
        }
        int d = sqb.d(requestSkippingSpeedList, j);
        this.b.g(d);
        int i = 0;
        kob kobVar = requestSkippingSpeedList.get(0);
        if (kobVar == null) {
            LogUtil.h("Track_TrackLineChartHolderFiller", "handlePowerData data is null");
            return;
        }
        list.add(new kob(0L, kobVar.c()));
        Iterator<kob> it = requestSkippingSpeedList.iterator();
        long j3 = 0;
        while (it.hasNext()) {
            kob next = it.next();
            long j4 = d;
            i++;
            long j5 = i * j4;
            if (it.hasNext()) {
                list.add(new kob(j5, next.c()));
                j3 = j5;
            } else {
                Iterator<kob> it2 = it;
                int i2 = d;
                long seconds = ((int) TimeUnit.MILLISECONDS.toSeconds(j2)) - j3;
                if (seconds < 0) {
                    list.add(new kob(j5, next.c()));
                } else {
                    long j6 = seconds - (seconds % 5);
                    if (j6 == 0) {
                        j3 += 5;
                        list.add(new kob(j3, next.c()));
                    } else if (j6 > j4) {
                        list.add(new kob(j5, next.c()));
                    } else {
                        j3 += j6;
                        list.add(new kob(j3, next.c()));
                    }
                    d = i2;
                    it = it2;
                }
                j3 = j5;
                d = i2;
                it = it2;
            }
        }
    }

    private void e(MotionPath motionPath, long j, long j2, List<koc> list) {
        List<koc> requestPowerList = motionPath.requestPowerList();
        if (!motionPath.isValidPowerData()) {
            LogUtil.h("Track_TrackLineChartHolderFiller", "handlePowerData powerDataListTemp is null");
            return;
        }
        int d = sqb.d(requestPowerList, j);
        this.b.j(d);
        int i = 0;
        koc kocVar = requestPowerList.get(0);
        if (kocVar == null) {
            LogUtil.h("Track_TrackLineChartHolderFiller", "handlePowerData data is null");
            return;
        }
        list.add(new koc(0L, kocVar.b()));
        Iterator<koc> it = requestPowerList.iterator();
        long j3 = 0;
        while (it.hasNext()) {
            koc next = it.next();
            long j4 = d;
            i++;
            long j5 = i * j4;
            if (it.hasNext()) {
                list.add(new koc(j5, next.b()));
                j3 = j5;
            } else {
                Iterator<koc> it2 = it;
                int i2 = d;
                long seconds = ((int) TimeUnit.MILLISECONDS.toSeconds(j2)) - j3;
                if (seconds < 0) {
                    list.add(new koc(j5, next.b()));
                } else {
                    long j6 = seconds - (seconds % 5);
                    if (j6 == 0) {
                        j3 += 5;
                        list.add(new koc(j3, next.b()));
                    } else if (j6 > j4) {
                        list.add(new koc(j5, next.b()));
                    } else {
                        j3 += j6;
                        list.add(new koc(j3, next.b()));
                    }
                    d = i2;
                    it = it2;
                }
                j3 = j5;
                d = i2;
                it = it2;
            }
        }
    }

    private void b(MotionPath motionPath, long j, long j2, List<knw> list) {
        List<knw> requestPaddleFrequencyList = motionPath.requestPaddleFrequencyList();
        if (!motionPath.isValidPaddleData()) {
            LogUtil.h("Track_TrackLineChartHolderFiller", "handlePaddleFreqData paddleListTemp is null");
            return;
        }
        int d = sqb.d(requestPaddleFrequencyList, j);
        this.b.a(d);
        int i = 0;
        knw knwVar = requestPaddleFrequencyList.get(0);
        if (knwVar == null) {
            LogUtil.h("Track_TrackLineChartHolderFiller", "handlePaddleFreqData data is null");
            return;
        }
        list.add(new knw(0L, knwVar.b()));
        Iterator<knw> it = requestPaddleFrequencyList.iterator();
        long j3 = 0;
        while (it.hasNext()) {
            knw next = it.next();
            long j4 = d;
            i++;
            long j5 = i * j4;
            if (it.hasNext()) {
                list.add(new knw(j5, next.b()));
                j3 = j5;
            } else {
                Iterator<knw> it2 = it;
                int i2 = d;
                long seconds = ((int) TimeUnit.MILLISECONDS.toSeconds(j2)) - j3;
                if (seconds < 0) {
                    list.add(new knw(j5, next.b()));
                } else {
                    long j6 = seconds - (seconds % 5);
                    if (j6 == 0) {
                        j3 += 5;
                        list.add(new knw(j3, next.b()));
                    } else if (j6 > j4) {
                        list.add(new knw(j5, next.b()));
                    } else {
                        j3 += j6;
                        list.add(new knw(j3, next.b()));
                    }
                    d = i2;
                    it = it2;
                }
                j3 = j5;
                d = i2;
                it = it2;
            }
        }
    }

    private void a(MotionPath motionPath, long j, long j2, List<ffn> list, MotionPathSimplify motionPathSimplify) {
        List<ffn> requestRidePostureDataList = motionPath.requestRidePostureDataList();
        if (!motionPath.isValidCadenceData()) {
            LogUtil.h("Track_TrackLineChartHolderFiller", "handleCadenceRateData cadenceListTemp is null");
            return;
        }
        int d = sqb.d(requestRidePostureDataList, j);
        this.b.e(d);
        if (hji.a(motionPathSimplify.requestSportType(), motionPathSimplify)) {
            e(requestRidePostureDataList, motionPathSimplify);
        }
        int i = 0;
        ffn ffnVar = requestRidePostureDataList.get(0);
        if (ffnVar == null) {
            LogUtil.h("Track_TrackLineChartHolderFiller", "handleCadenceRateData data is null");
            return;
        }
        list.add(new ffn(0L, ffnVar.e()));
        Iterator<ffn> it = requestRidePostureDataList.iterator();
        long j3 = 0;
        while (it.hasNext()) {
            ffn next = it.next();
            long j4 = d;
            i++;
            long j5 = i * j4;
            if (it.hasNext()) {
                list.add(new ffn(j5, next.e()));
                j3 = j5;
            } else {
                long seconds = ((int) TimeUnit.MILLISECONDS.toSeconds(j2)) - j3;
                if (seconds < 0) {
                    list.add(new ffn(j5, next.e()));
                } else {
                    long j6 = seconds - (seconds % 5);
                    if (j6 == 0) {
                        j3 += 5;
                        list.add(new ffn(j3, next.e()));
                    } else if (j6 > j4) {
                        list.add(new ffn(j5, next.e()));
                    } else {
                        j3 += j6;
                        list.add(new ffn(j3, next.e()));
                    }
                }
                j3 = j5;
            }
        }
    }

    private void e(List<ffn> list, MotionPathSimplify motionPathSimplify) {
        ArrayList arrayList = new ArrayList();
        if (koq.b(list) || motionPathSimplify == null) {
            LogUtil.h("Track_TrackLineChartHolderFiller", "motionPathSimplify is null");
            return;
        }
        if (motionPathSimplify.requestSportType() == 273) {
            for (ffn ffnVar : list) {
                if (ffnVar != null) {
                    ffn ffnVar2 = new ffn();
                    ffnVar2.c(ffnVar.acquireTime());
                    ffnVar2.e(ffnVar.e() / 2);
                    arrayList.add(ffnVar2);
                }
            }
            if (koq.c(arrayList)) {
                list.clear();
                list.addAll(arrayList);
            }
        }
    }

    private void e(MotionPath motionPath, ArrayList<ffn> arrayList) {
        List<ffn> requestRidePostureDataList = motionPath.requestRidePostureDataList();
        boolean c = c(requestRidePostureDataList);
        long j = -1;
        for (int i = 0; i < requestRidePostureDataList.size(); i++) {
            ffn ffnVar = requestRidePostureDataList.get(i);
            if (ffnVar != null) {
                long acquireTime = ffnVar.acquireTime();
                if (acquireTime > this.c.e().requestTotalTime()) {
                    return;
                }
                long d = d(e(c, acquireTime));
                this.e = d;
                long j2 = d - j;
                if (j != -1 && j2 > 5 && j2 < 20) {
                    e(arrayList, ffnVar, j2, j);
                }
                a(this.e, arrayList, ffnVar);
                j = this.e;
            }
        }
    }

    private void e(ArrayList<ffn> arrayList, ffn ffnVar, long j, long j2) {
        int i = ((int) j) / 5;
        for (int i2 = 1; i2 <= i; i2++) {
            long j3 = (i2 * 5) + j2;
            if (j3 >= this.e) {
                return;
            }
            a(j3, arrayList, ffnVar);
        }
    }

    private void a(long j, ArrayList<ffn> arrayList, ffn ffnVar) {
        arrayList.add(new ffn(j, ffnVar.e()));
    }

    private void b(MotionPath motionPath, long j) {
        if (motionPath.requestAltitudeList() == null) {
            LogUtil.h("Track_TrackLineChartHolderFiller", "fillAltitudeData motionPath requestAltitudeList null,return");
            return;
        }
        if (!this.c.r() || this.c.e().requestSportType() == 512) {
            ArrayList<knz> arrayList = new ArrayList<>(16);
            this.b.f(arrayList);
            ArrayList<knz> requestAltitudeList = motionPath.requestAltitudeList();
            int d = sqb.d(requestAltitudeList, j);
            this.b.k(d);
            this.b.m(1);
            for (int i = 0; i < requestAltitudeList.size(); i++) {
                arrayList.add(new knz(d * i, requestAltitudeList.get(i).c()));
            }
        }
    }

    private void c(MotionPath motionPath, long j) {
        if (koq.b(motionPath.requestRealTimeSpeedList())) {
            LogUtil.h("Track_TrackLineChartHolderFiller", "fillAltitudeData motionPath requestAltitudeList null,return");
            return;
        }
        if (!this.c.al() || this.c.e().requestSportType() == 512) {
            ArrayList<koi> arrayList = new ArrayList<>(16);
            this.b.h(arrayList);
            List<koi> requestRealTimeSpeedList = motionPath.requestRealTimeSpeedList();
            int d = sqb.d(requestRealTimeSpeedList, j);
            this.b.l(d);
            Iterator<koi> it = requestRealTimeSpeedList.iterator();
            while (it.hasNext()) {
                arrayList.add(it.next());
            }
            if (hji.a(this.c.e().requestSportType(), this.c.e().requestSportDataSource())) {
                arrayList.clear();
                for (int i = 0; i < requestRealTimeSpeedList.size(); i++) {
                    arrayList.add(new koi(i * d, requestRealTimeSpeedList.get(i).e()));
                }
            }
        }
    }

    private void c(MotionPath motionPath) {
        if (motionPath.requestSwolfList() == null) {
            LogUtil.h("Track_TrackLineChartHolderFiller", "requestSwolfList motionPath requestSwolfList null,return");
            return;
        }
        if (!this.c.as() || this.c.e().requestSportType() == 512) {
            ArrayList arrayList = new ArrayList(16);
            arrayList.addAll(motionPath.requestSwolfList());
            this.b.j(arrayList);
            this.b.n(5);
        }
    }

    private void e(MotionPath motionPath) {
        if (motionPath.requestPullFreqList() == null) {
            LogUtil.h("Track_TrackLineChartHolderFiller", "initPullFreqData null,return");
            return;
        }
        if (!this.c.af() || this.c.e().requestSportType() == 512) {
            ArrayList arrayList = new ArrayList(16);
            arrayList.addAll(motionPath.requestPullFreqList());
            this.b.h((List<kog>) arrayList);
            this.b.h(5);
        }
    }

    private void a(MotionPath motionPath, MotionPathSimplify motionPathSimplify) {
        if (motionPath.requestRealTimePaceList() == null) {
            LogUtil.h("Track_TrackLineChartHolderFiller", "requestSwolfList motionPath requestSwolfList null,return");
            return;
        }
        if (!this.c.ah() || this.c.e().requestSportType() == 512) {
            ArrayList<koh> arrayList = new ArrayList<>(16);
            this.b.i(arrayList);
            List<koh> requestRealTimePaceList = motionPath.requestRealTimePaceList();
            this.b.h(5);
            int requestSportType = motionPathSimplify.requestSportType();
            if (UnitUtil.h() && !hji.j(requestSportType)) {
                b(arrayList, requestRealTimePaceList, requestSportType);
            } else {
                c(arrayList, requestRealTimePaceList, requestSportType);
            }
            this.b.a((requestRealTimePaceList.size() - 1) * 5);
        }
    }

    private void c(ArrayList<koh> arrayList, List<koh> list, int i) {
        for (int i2 = 0; i2 < list.size(); i2++) {
            if (list.get(i2).a() != 0) {
                if (i == 266 || i == 262) {
                    arrayList.add(new koh(list.get(i2).acquireTime(), list.get(i2).a() / 10));
                } else if (i == 274 || hji.j(i)) {
                    arrayList.add(new koh(i2 * 5, (int) Math.round(list.get(i2).a() / 2.0d)));
                } else {
                    arrayList.add(new koh(list.get(i2).acquireTime(), list.get(i2).a()));
                }
            }
        }
    }

    private void b(ArrayList<koh> arrayList, List<koh> list, int i) {
        for (int i2 = 0; i2 < list.size(); i2++) {
            if (list.get(i2).a() != 0) {
                if (i == 266 || i == 262) {
                    arrayList.add(new koh(list.get(i2).acquireTime(), (int) UnitUtil.d(list.get(i2).a() / 10.0d, 2)));
                } else if (hji.a(i, this.c.e().requestSportDataSource())) {
                    arrayList.add(new koh(i2 * 5, (int) UnitUtil.d(list.get(i2).a() / 10.0d, 2)));
                } else {
                    arrayList.add(new koh(list.get(i2).acquireTime(), (int) UnitUtil.d(list.get(i2).a(), 3)));
                }
            }
        }
    }

    private void d(MotionPath motionPath, MotionPathSimplify motionPathSimplify) {
        if (motionPath == null || motionPath.requestRunningPostureList() == null) {
            LogUtil.h("Track_TrackLineChartHolderFiller", "fillRunningPostureList motionPath requestRunningPostureList null,return");
            return;
        }
        if (!this.c.an() || this.c.e().requestSportType() == 512) {
            ArrayList arrayList = new ArrayList(16);
            this.b.a((List<ffs>) arrayList);
            ArrayList<ffs> requestRunningPostureList = motionPath.requestRunningPostureList();
            this.b.f(5);
            hjh.c((ArrayList<ffs>) arrayList, requestRunningPostureList);
            LogUtil.a("Track_TrackLineChartHolderFiller", "initRunningPostureData");
            this.b.d(TimeUnit.MILLISECONDS.toSeconds(motionPathSimplify.requestTotalTime()));
        }
    }

    private long e(boolean z, long j) {
        return z ? TimeUnit.MILLISECONDS.toSeconds(j) : j;
    }

    private void c(MotionPath motionPath, MotionPathSimplify motionPathSimplify) {
        long requestTotalTime;
        if (koq.b(motionPath.requestSpo2List())) {
            LogUtil.h("Track_TrackLineChartHolderFiller", "motionPath spo2 null or empty, return");
            return;
        }
        int requestSportType = motionPathSimplify.requestSportType();
        if (this.c.ao()) {
            return;
        }
        this.b.g(motionPath.requestSpo2List());
        if (kxb.c(requestSportType)) {
            requestTotalTime = (long) (this.c.e().getExtendDataFloat("skiTotalTime") / 1000.0f);
        } else {
            requestTotalTime = this.c.e().requestTotalTime() / 1000;
        }
        this.b.j(requestTotalTime);
    }

    private long d(long j) {
        long j2 = j / 5;
        return j % 5 > 2 ? (j2 + 1) * 5 : j2 * 5;
    }

    private boolean c(List<ffn> list) {
        List<Long> e = e(list);
        if (e.size() < 3) {
            return list.size() > 0 && list.get(0).acquireTime() > 1000;
        }
        long longValue = e.get(0).longValue();
        long longValue2 = e.get(1).longValue();
        return longValue2 - longValue > 500 && e.get(2).longValue() - longValue2 > 500;
    }

    private List<Long> e(List<ffn> list) {
        ArrayList<Long> arrayList = new ArrayList(list.size());
        if (koq.b(list)) {
            return arrayList;
        }
        for (ffn ffnVar : list) {
            if (ffnVar != null) {
                arrayList.add(Long.valueOf(ffnVar.acquireTime()));
            }
        }
        HashSet hashSet = new HashSet(list.size());
        ArrayList arrayList2 = new ArrayList(list.size());
        for (Long l : arrayList) {
            if (hashSet.add(l)) {
                arrayList2.add(l);
            }
        }
        arrayList.clear();
        arrayList.addAll(arrayList2);
        return arrayList;
    }
}
