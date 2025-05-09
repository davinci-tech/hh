package defpackage;

import android.content.Context;
import androidx.core.util.Pair;
import com.huawei.healthcloud.plugintrack.model.MotionData;
import com.huawei.healthcloud.plugintrack.model.SportDetailChartDataType;
import com.huawei.hwfoundationmodel.trackmodel.HeartRateData;
import com.huawei.hwfoundationmodel.trackmodel.MotionPath;
import com.huawei.hwfoundationmodel.trackmodel.MotionPathSimplify;
import com.huawei.hwfoundationmodel.trackmodel.StepRateData;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.CommonUtil;
import health.compact.a.UnitUtil;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes4.dex */
public class hjw implements Serializable {
    private static final long serialVersionUID = -4003181319353707660L;

    /* renamed from: a, reason: collision with root package name */
    private boolean f13191a;
    private Pair<float[], float[]> h;
    private MotionPathSimplify i = null;
    private MotionPath j = null;
    private MotionData g = null;
    private boolean e = false;
    private String f = null;
    private List<gya> o = new ArrayList(16);
    private List<hjw> n = new ArrayList(16);
    private boolean[] d = {false, false, false, false, false, false, false};
    private boolean c = true;
    private transient HashMap<SportDetailChartDataType, hix> b = new HashMap<>(20);

    public void e(MotionPathSimplify motionPathSimplify) {
        this.i = motionPathSimplify;
    }

    public void e(MotionPath motionPath) {
        this.j = motionPath;
    }

    public void c(List<gya> list) {
        this.o.clear();
        this.n.clear();
        if (koq.c(list)) {
            for (gya gyaVar : list) {
                if (gyaVar != null) {
                    hjw hjwVar = new hjw();
                    hjwVar.e(gyaVar.c());
                    hjwVar.e(gyaVar.b());
                    hjwVar.k();
                    this.o.add(gyaVar);
                    this.n.add(hjwVar);
                }
            }
        }
    }

    public MotionPath d() {
        return this.j;
    }

    public MotionPathSimplify e() {
        return this.i;
    }

    public int e(Context context) {
        gwg.b(context);
        int a2 = gwg.a();
        if (a2 != 0) {
            return a2;
        }
        if (t()) {
            for (hjw hjwVar : this.n) {
                if (hjwVar != null && hjwVar.d() != null) {
                    double[] dArr = new double[2];
                    if (e(dArr, hjwVar.d().requestLbsDataMap()) == 0) {
                        return gwg.e(context, dArr[0], dArr[1]);
                    }
                    a2 = -1;
                }
            }
            return a2;
        }
        MotionPath motionPath = this.j;
        if (motionPath == null) {
            return a2;
        }
        double[] dArr2 = new double[2];
        if (e(dArr2, motionPath.requestLbsDataMap()) == 0) {
            return gwg.e(context, dArr2[0], dArr2[1]);
        }
        return -1;
    }

    private int e(double[] dArr, Map<Long, double[]> map) {
        if (map == null) {
            LogUtil.b("Track_TrackDetailDataManager", "lbsDataMap is null");
            return -1;
        }
        for (Map.Entry<Long, double[]> entry : map.entrySet()) {
            if (entry != null && entry.getValue() != null && entry.getValue().length >= 2) {
                dArr[0] = entry.getValue()[0];
                dArr[1] = entry.getValue()[1];
                if (!gwe.c(new hjd(dArr[0], dArr[1]))) {
                    return 0;
                }
                LogUtil.a("Track_TrackDetailDataManager", "isPausePoint");
            }
        }
        return -1;
    }

    public MotionData j() {
        return this.g;
    }

    public Map<SportDetailChartDataType, hix> g() {
        return this.b;
    }

    public List<gya> o() {
        return this.o;
    }

    public List<hjw> i() {
        return this.n;
    }

    public List<MotionData> m() {
        if (koq.c(this.n)) {
            ArrayList arrayList = new ArrayList(this.n.size());
            for (hjw hjwVar : this.n) {
                MotionData j = hjwVar.j();
                if (j != null && !hjwVar.bp()) {
                    arrayList.add(j);
                }
            }
            return arrayList;
        }
        return Collections.EMPTY_LIST;
    }

    public List<MotionData> b() {
        ArrayList arrayList = new ArrayList(this.n.size() + 1);
        if (this.g != null && !bp()) {
            arrayList.add(this.g);
        }
        arrayList.addAll(m());
        return arrayList;
    }

    public boolean n() {
        return koq.c(this.o);
    }

    public Map<Integer, Float> e(int i) {
        if (d(i)) {
            return this.i.localePaceMap();
        }
        MotionPath motionPath = this.j;
        if (motionPath != null) {
            return motionPath.localePaceMap();
        }
        return null;
    }

    private boolean d(int i) {
        MotionPathSimplify motionPathSimplify;
        return i == 264 && (motionPathSimplify = this.i) != null && motionPathSimplify.localePaceMap() != null && this.i.localePaceMap().size() > 0;
    }

    public boolean b(int i) {
        switch (i) {
            case 0:
                return this.d[0];
            case 1:
                return this.d[1];
            case 2:
                return this.d[2];
            case 3:
                return this.d[3];
            case 4:
                return this.d[4];
            case 5:
                return this.d[5];
            case 6:
                return this.d[6];
            default:
                return false;
        }
    }

    public boolean t() {
        MotionPathSimplify motionPathSimplify = this.i;
        return motionPathSimplify != null && motionPathSimplify.requestSportType() == 512;
    }

    public void k() {
        if (t()) {
            ba();
        } else {
            bf();
        }
    }

    private void bf() {
        MotionPathSimplify motionPathSimplify;
        this.d[4] = true;
        if (bp()) {
            this.d[0] = true;
        }
        if (bi() && ap()) {
            this.d[1] = true;
        }
        MotionPath motionPath = this.j;
        if (motionPath != null) {
            this.c = motionPath.isValidSpeedList();
        } else {
            this.c = false;
        }
        bd();
        boolean z = this.b.get(SportDetailChartDataType.GROUND_CONTACT_TIME).i() && this.b.get(SportDetailChartDataType.GROUND_IMPACT_ACCELERATION).i() && this.b.get(SportDetailChartDataType.HANG_TIME).i() && this.b.get(SportDetailChartDataType.GROUND_HANG_TIME_RATE).i();
        boolean i = this.b.get(SportDetailChartDataType.ALTITUDE).i();
        boolean z2 = br() || bn() || this.b.get(SportDetailChartDataType.HEART_RATE).i();
        boolean i2 = this.b.get(SportDetailChartDataType.SPEED_RATE).i();
        boolean i3 = this.b.get(SportDetailChartDataType.PULL_FREQ).i();
        boolean i4 = this.b.get(SportDetailChartDataType.SWOLF).i();
        boolean i5 = this.b.get(SportDetailChartDataType.STEP_RATE).i();
        boolean i6 = this.b.get(SportDetailChartDataType.REALTIME_PACE).i();
        boolean i7 = this.b.get(SportDetailChartDataType.CADENCE).i();
        boolean i8 = this.b.get(SportDetailChartDataType.PADDLE_FREQUENCY).i();
        boolean i9 = this.b.get(SportDetailChartDataType.POWER).i();
        boolean i10 = this.b.get(SportDetailChartDataType.PEAK_WEIGHT).i();
        boolean z3 = br() || bn() || this.b.get(SportDetailChartDataType.HEART_RATE_RECOVERY).i();
        if (i && z2 && i2 && i3 && i4 && i5 && i6 && z && w() && i7 && i8 && i9 && z3 && i10) {
            this.d[2] = true;
        }
        this.d[5] = !bt();
        this.d[6] = !av();
        LogUtil.a("Track_TrackDetailDataManager", "hideRadioView() hideRadioList is ", Arrays.toString(this.d));
        MotionPath motionPath2 = this.j;
        if (motionPath2 == null || (motionPathSimplify = this.i) == null) {
            return;
        }
        this.g = gvz.c(motionPath2, motionPathSimplify);
    }

    public void bc() {
        MotionPath motionPath = this.j;
        if (motionPath != null) {
            this.g.setLbsDataMap(motionPath.requestLbsDataMap());
        }
    }

    private void ba() {
        if (bp()) {
            this.d[0] = true;
        }
        boolean[] zArr = this.d;
        zArr[1] = true;
        zArr[2] = true;
        zArr[3] = true;
        zArr[5] = true;
        if (n()) {
            this.d[4] = false;
        }
        this.d[6] = true;
        ay();
    }

    private void bd() {
        for (SportDetailChartDataType sportDetailChartDataType : SportDetailChartDataType.values()) {
            hix hixVar = new hix();
            switch (AnonymousClass1.e[sportDetailChartDataType.ordinal()]) {
                case 1:
                    hixVar.b(be());
                    this.b.put(sportDetailChartDataType, hixVar);
                    break;
                case 2:
                    hixVar.b(bo());
                    this.b.put(sportDetailChartDataType, hixVar);
                    break;
                case 3:
                    hixVar.b(ak());
                    this.b.put(sportDetailChartDataType, hixVar);
                    break;
                case 4:
                    hixVar.b(q());
                    this.b.put(sportDetailChartDataType, hixVar);
                    break;
                case 5:
                    hixVar.b(bg());
                    this.b.put(sportDetailChartDataType, hixVar);
                    break;
                case 6:
                    hixVar.b(bj());
                    this.b.put(sportDetailChartDataType, hixVar);
                    break;
                case 7:
                    hixVar.b(bl());
                    this.b.put(sportDetailChartDataType, hixVar);
                    break;
                case 8:
                    hixVar.b(bm());
                    this.b.put(sportDetailChartDataType, hixVar);
                    break;
                case 9:
                    hixVar.b(bq());
                    this.b.put(sportDetailChartDataType, hixVar);
                    break;
                case 10:
                    hixVar.b(ao());
                    this.b.put(sportDetailChartDataType, hixVar);
                    break;
                case 11:
                    hixVar.b(y());
                    this.b.put(sportDetailChartDataType, hixVar);
                    break;
                case 12:
                    hixVar.b(ab());
                    this.b.put(sportDetailChartDataType, hixVar);
                    break;
                case 13:
                    hixVar.b(x());
                    this.b.put(sportDetailChartDataType, hixVar);
                    break;
                case 14:
                    hixVar.b(ad());
                    this.b.put(sportDetailChartDataType, hixVar);
                    break;
                case 15:
                    hixVar.b(au());
                    this.b.put(sportDetailChartDataType, hixVar);
                    break;
                case 16:
                    hixVar.b(v());
                    this.b.put(sportDetailChartDataType, hixVar);
                    break;
                case 17:
                    hixVar.b(aq());
                    this.b.put(sportDetailChartDataType, hixVar);
                    break;
                case 18:
                    hixVar.b(ac());
                    this.b.put(sportDetailChartDataType, hixVar);
                    break;
                case 19:
                    hixVar.b(w());
                    this.b.put(sportDetailChartDataType, hixVar);
                    break;
                case 20:
                    hixVar.b(w());
                    this.b.put(sportDetailChartDataType, hixVar);
                    break;
                case 21:
                    hixVar.b(bk());
                    this.b.put(sportDetailChartDataType, hixVar);
                    break;
                case 22:
                    hixVar.b(ag());
                    this.b.put(sportDetailChartDataType, hixVar);
                    break;
                case 23:
                    hixVar.b(ae());
                    this.b.put(sportDetailChartDataType, hixVar);
                    break;
            }
        }
    }

    /* renamed from: hjw$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] e;

        static {
            int[] iArr = new int[SportDetailChartDataType.values().length];
            e = iArr;
            try {
                iArr[SportDetailChartDataType.HEART_RATE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                e[SportDetailChartDataType.STEP_RATE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                e[SportDetailChartDataType.SPEED_RATE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                e[SportDetailChartDataType.ALTITUDE.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                e[SportDetailChartDataType.CADENCE.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                e[SportDetailChartDataType.PADDLE_FREQUENCY.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                e[SportDetailChartDataType.REALTIME_PACE.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                e[SportDetailChartDataType.PULL_FREQ.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                e[SportDetailChartDataType.SWOLF.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                e[SportDetailChartDataType.SPO2.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                e[SportDetailChartDataType.GROUND_CONTACT_TIME.ordinal()] = 11;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                e[SportDetailChartDataType.HANG_TIME.ordinal()] = 12;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                e[SportDetailChartDataType.GROUND_HANG_TIME_RATE.ordinal()] = 13;
            } catch (NoSuchFieldError unused13) {
            }
            try {
                e[SportDetailChartDataType.GROUND_IMPACT_ACCELERATION.ordinal()] = 14;
            } catch (NoSuchFieldError unused14) {
            }
            try {
                e[SportDetailChartDataType.VERTICAL_RATIO.ordinal()] = 15;
            } catch (NoSuchFieldError unused15) {
            }
            try {
                e[SportDetailChartDataType.GC_TIME_BALANCE.ordinal()] = 16;
            } catch (NoSuchFieldError unused16) {
            }
            try {
                e[SportDetailChartDataType.VERTICAL_OSCILLATION.ordinal()] = 17;
            } catch (NoSuchFieldError unused17) {
            }
            try {
                e[SportDetailChartDataType.ACTIVE_PEAK.ordinal()] = 18;
            } catch (NoSuchFieldError unused18) {
            }
            try {
                e[SportDetailChartDataType.JUMP_HEIGHT.ordinal()] = 19;
            } catch (NoSuchFieldError unused19) {
            }
            try {
                e[SportDetailChartDataType.JUMP_TIME.ordinal()] = 20;
            } catch (NoSuchFieldError unused20) {
            }
            try {
                e[SportDetailChartDataType.POWER.ordinal()] = 21;
            } catch (NoSuchFieldError unused21) {
            }
            try {
                e[SportDetailChartDataType.HEART_RATE_RECOVERY.ordinal()] = 22;
            } catch (NoSuchFieldError unused22) {
            }
            try {
                e[SportDetailChartDataType.PEAK_WEIGHT.ordinal()] = 23;
            } catch (NoSuchFieldError unused23) {
            }
        }
    }

    private boolean bn() {
        MotionPathSimplify motionPathSimplify = this.i;
        return motionPathSimplify != null && motionPathSimplify.requestSportType() == 290;
    }

    private boolean br() {
        MotionPathSimplify motionPathSimplify = this.i;
        return motionPathSimplify != null && motionPathSimplify.requestSportType() == 283;
    }

    private void ay() {
        for (SportDetailChartDataType sportDetailChartDataType : SportDetailChartDataType.values()) {
            hix hixVar = new hix();
            switch (AnonymousClass1.e[sportDetailChartDataType.ordinal()]) {
                case 1:
                case 2:
                case 3:
                case 4:
                case 7:
                case 8:
                case 9:
                case 11:
                case 12:
                case 13:
                case 14:
                    hixVar.b(false);
                    this.b.put(sportDetailChartDataType, hixVar);
                    break;
            }
        }
    }

    public int f() {
        int i = 0;
        while (true) {
            boolean[] zArr = this.d;
            if (i >= zArr.length) {
                return 0;
            }
            if (!zArr[i]) {
                return i;
            }
            i++;
        }
    }

    private boolean bp() {
        if (koq.c(this.n)) {
            Iterator<hjw> it = this.n.iterator();
            while (it.hasNext()) {
                if (!it.next().bp()) {
                    return false;
                }
            }
        }
        if (bh()) {
            return true;
        }
        return this.i.requestSportType() == 222 ? koq.b(this.j.requestMarkPointList()) || this.j.requestMarkPointList().size() < 2 : !this.i.hasTrackPoint() || this.j.requestLbsDataMap().size() < 3;
    }

    private boolean bt() {
        if (bh()) {
            return false;
        }
        return koq.c(this.j.requestSegmentList());
    }

    public boolean av() {
        MotionPathSimplify motionPathSimplify = this.i;
        return (motionPathSimplify == null || motionPathSimplify.requestSportType() != 283 || this.i.getExtendDataFloat("enduranceAbilityRank") == -1.0f) ? false : true;
    }

    private boolean bm() {
        if (bh()) {
            return true;
        }
        return true ^ this.j.isValidPullFreqList();
    }

    private boolean bq() {
        if (bh()) {
            return true;
        }
        return true ^ this.j.isValidSwolfList();
    }

    private boolean bl() {
        return bh() || this.i.requestSportType() == 265 || this.i.requestSportType() == 279 || this.i.requestSportType() == 271 || !this.c || aw();
    }

    private boolean bi() {
        Map<Integer, Float> a2;
        return bh() || (a2 = gvv.a(e(e().requestSportType()))) == null || a2.size() == 0;
    }

    private boolean bo() {
        if (bh()) {
            LogUtil.a("Track_TrackDetailDataManager", "isNoStepRateDetail,isInvalidMotionPathSimplifyAndMotionPath");
            return true;
        }
        int requestSportType = this.i.requestSportType();
        if (requestSportType == 260 || requestSportType == 274) {
            LogUtil.a("Track_TrackDetailDataManager", "isNoStepRateDetail,SportType:", Integer.valueOf(requestSportType));
            return true;
        }
        if (hji.a(requestSportType, this.i)) {
            LogUtil.a("Track_TrackDetailDataManager", "isNoStepRateDetail sportType and is old  ", Integer.valueOf(requestSportType));
            return true;
        }
        if (!this.j.isValidStepRateList()) {
            LogUtil.a("Track_TrackDetailDataManager", "isNoStepRateDetail,isValidStepRateList");
            return true;
        }
        if (this.j.requestStepRateList().size() > 0) {
            int e = ffw.e((StepRateData) Collections.max(this.j.requestStepRateList(), new Comparator<StepRateData>() { // from class: hjw.4
                @Override // java.util.Comparator
                /* renamed from: e, reason: merged with bridge method [inline-methods] */
                public int compare(StepRateData stepRateData, StepRateData stepRateData2) {
                    return ffw.e(stepRateData) - ffw.e(stepRateData2);
                }
            }));
            int requestAvgStepRate = this.i.requestAvgStepRate();
            if (e == 0 || e > 300) {
                LogUtil.a("Track_TrackDetailDataManager", "stepRatemax is zero or too SportsRecordingCardReaderbig");
            } else {
                LogUtil.a("Track_TrackDetailDataManager", "stepRate avg is bigger than max ");
                return requestAvgStepRate == 0 || requestAvgStepRate > e;
            }
        }
        return true;
    }

    private boolean bj() {
        if (bh()) {
            LogUtil.a("Track_TrackDetailDataManager", "isNoPaddleFreqDetail,isInvalidMotionPathSimplifyAndMotionPath");
            return true;
        }
        if (this.i.requestSportType() != 274) {
            LogUtil.a("Track_TrackDetailDataManager", "isNoPaddleFreqDetail,SportType:", Integer.valueOf(this.i.requestSportType()));
            return true;
        }
        int c = hji.c(this.i);
        if (c <= 0) {
            LogUtil.b("Track_TrackDetailDataManager", "isNoPaddleFreqDetail,paddle times is 0:", Integer.valueOf(c));
            return true;
        }
        if (this.j.isValidPaddleData()) {
            return false;
        }
        LogUtil.a("Track_TrackDetailDataManager", "isNoPaddleFreqDetail,isValidPaddleFreqList");
        return true;
    }

    private boolean bg() {
        int i;
        if (bh()) {
            LogUtil.a("Track_TrackDetailDataManager", "isNoCadenceRateDetail,isInvalidMotionPathSimplifyAndMotionPath");
            return true;
        }
        if (this.i.requestSportType() != 273 && this.i.requestSportType() != 265 && this.i.requestSportType() != 259) {
            LogUtil.a("Track_TrackDetailDataManager", "isNoCadenceRateDetail,SportType:", Integer.valueOf(this.i.requestSportType()));
            return true;
        }
        if (!this.j.isValidCadenceData()) {
            LogUtil.a("Track_TrackDetailDataManager", "isNoCadenceRateDetail,isValidCadenceRateList");
            return true;
        }
        List<ffn> requestRidePostureDataList = this.j.requestRidePostureDataList();
        if (koq.c(requestRidePostureDataList)) {
            i = ((ffn) Collections.max(requestRidePostureDataList, new Comparator() { // from class: hjz
                @Override // java.util.Comparator
                public final int compare(Object obj, Object obj2) {
                    return hjw.d((ffn) obj, (ffn) obj2);
                }
            })).e();
        } else {
            LogUtil.b("Track_TrackDetailDataManager", "maxCadenceRate  is  zero");
            i = 0;
        }
        int c = hji.c(this.i, this.j);
        if (c > 0 && c <= i) {
            return false;
        }
        LogUtil.b("Track_TrackDetailDataManager", "isNoPaddleFreqDetail,paddle times is 0: or candaceRate avg is bigger than max", Integer.valueOf(c), "max =", Integer.valueOf(i));
        return true;
    }

    static /* synthetic */ int d(ffn ffnVar, ffn ffnVar2) {
        return ffnVar.e() - ffnVar2.e();
    }

    private boolean bk() {
        if (bh()) {
            LogUtil.a("Track_TrackDetailDataManager", "isNoPowerDetail,isInvalidMotionPathSimplifyAndMotionPath");
            return true;
        }
        if (this.i.requestSportType() != 273 && this.i.requestSportType() != 274 && this.i.requestSportType() != 265 && this.i.requestSportType() != 259) {
            LogUtil.a("Track_TrackDetailDataManager", "isNoPowerDetail,SportType() == ", Integer.valueOf(this.i.requestSportType()));
            return true;
        }
        if (!this.j.isValidPowerData()) {
            LogUtil.a("Track_TrackDetailDataManager", "isNoPowerDetail,isValidPowerData");
            return true;
        }
        LogUtil.a("Track_TrackDetailDataManager", "isNoPowerDetail return false");
        return false;
    }

    private boolean be() {
        if (bh()) {
            LogUtil.a("Track_TrackDetailDataManager", "motionPath is null");
            return true;
        }
        if (!this.j.isValidHeartRateList()) {
            LogUtil.a("Track_TrackDetailDataManager", "motionPath is null");
            return true;
        }
        if (this.j.requestHeartRateList().size() <= 1) {
            return true;
        }
        int b = ffw.b((List<HeartRateData>) this.j.requestHeartRateList());
        int requestAvgHeartRate = this.i.requestAvgHeartRate();
        LogUtil.a("Track_TrackDetailDataManager", "heartRateMax ", Integer.valueOf(b), " avgHeartRate ", Integer.valueOf(requestAvgHeartRate));
        return b == 0 || requestAvgHeartRate == 0 || requestAvgHeartRate > b;
    }

    public boolean ao() {
        return (!bh() && this.j.isValidSpo2List() && bv()) ? false : true;
    }

    private boolean bv() {
        int requestMinSpo2 = this.i.requestMinSpo2();
        int requestMaxSpo2 = this.i.requestMaxSpo2();
        return requestMinSpo2 > 0 && requestMinSpo2 <= 100 && requestMaxSpo2 > 0 && requestMaxSpo2 <= 100;
    }

    public boolean q() {
        return bh() || !this.j.isValidAltitudeList();
    }

    public boolean ak() {
        if (!this.c || !aw()) {
            return true;
        }
        if (this.i.requestSportType() == 273 && !hji.a(this.i.requestSportDataSource())) {
            LogUtil.a("Track_TrackDetailDataManager", "isHideSpeedView,SportType:", Integer.valueOf(this.i.requestSportType()));
            return true;
        }
        if (Math.abs((float) hji.b(this.i.requestAvgPace())) < 1.0E-4f) {
            LogUtil.a("Track_TrackDetailDataManager", "avgSpeed is zero");
            return true;
        }
        LogUtil.a("Track_TrackDetailDataManager", "isHideSpeedView return false");
        return false;
    }

    public boolean ab() {
        if (bh()) {
            return true;
        }
        return !fgf.f(this.j.requestRunningPostureList());
    }

    public boolean v() {
        if (bh()) {
            return true;
        }
        return !fgf.c(this.j.requestRunningPostureList());
    }

    public boolean aq() {
        if (bh()) {
            return true;
        }
        return !fgf.i(this.j.requestRunningPostureList());
    }

    public boolean ac() {
        if (bh()) {
            return true;
        }
        return !fgf.b(this.j.requestRunningPostureList());
    }

    public boolean ad() {
        if (bh() || am()) {
            return true;
        }
        return !fgf.d(this.j.requestRunningPostureList());
    }

    public boolean au() {
        if (!bh() && this.i.getExtendDataInt("avg_v_s_r") > 0) {
            return !fgf.h(this.j.requestRunningPostureList());
        }
        return true;
    }

    public boolean am() {
        if (bh()) {
            return true;
        }
        return !fgf.g(this.j.requestRunningPostureList());
    }

    public boolean y() {
        if (bh()) {
            return true;
        }
        return !fgf.a(this.j.requestRunningPostureList());
    }

    public boolean x() {
        if (bh() || am() || this.i.requestGroundHangTimeRate() <= 0.0f) {
            return true;
        }
        ArrayList<ffs> requestRunningPostureList = this.j.requestRunningPostureList();
        Iterator<ffs> it = requestRunningPostureList.iterator();
        while (it.hasNext()) {
            if (it.next().o() > 0) {
                return false;
            }
        }
        Iterator<ffs> it2 = requestRunningPostureList.iterator();
        while (it2.hasNext()) {
            if (it2.next().l() > 0) {
                return false;
            }
        }
        return true;
    }

    private boolean bh() {
        return this.i == null || this.j == null;
    }

    public Map<Integer, Float> bb() {
        return gvv.a(e(e().requestSportType()));
    }

    public Float[] e(Map<Integer, Float> map) {
        if (map == null || map.size() <= 1) {
            return null;
        }
        return gvv.e(map);
    }

    public boolean az() {
        return (bh() || this.i.requestAbnormalTrack() == 0) ? false : true;
    }

    public boolean s() {
        return !bh() && this.i.hasTrackPoint() && this.i.requestSportDataSource() == 6;
    }

    public boolean p() {
        return !bh() && this.i.requestDuplicated() == 1;
    }

    public boolean z() {
        return this.b.get(SportDetailChartDataType.HEART_RATE).i();
    }

    public boolean ar() {
        return this.b.get(SportDetailChartDataType.STEP_RATE).i();
    }

    public boolean r() {
        return this.b.get(SportDetailChartDataType.ALTITUDE).i();
    }

    public boolean al() {
        return this.b.get(SportDetailChartDataType.SPEED_RATE).i();
    }

    public boolean u() {
        return this.b.get(SportDetailChartDataType.CADENCE).i();
    }

    public boolean aa() {
        return this.b.get(SportDetailChartDataType.PADDLE_FREQUENCY).i();
    }

    public boolean ai() {
        return this.b.get(SportDetailChartDataType.POWER).i();
    }

    public boolean aw() {
        MotionPathSimplify motionPathSimplify = this.i;
        return motionPathSimplify != null && hji.g(motionPathSimplify.requestSportType());
    }

    public boolean ap() {
        if (bh()) {
            return true;
        }
        return UnitUtil.h() ? this.i.requestBritishSwimSegments() == null || this.i.requestBritishSwimSegments().size() <= 0 : this.i.requestSwimSegments() == null || this.i.requestSwimSegments().size() <= 0;
    }

    public boolean w() {
        return bh() || this.j.requestJumpDataList() == null || this.j.requestJumpDataList().size() <= 0;
    }

    public boolean as() {
        return this.b.get(SportDetailChartDataType.SWOLF).i();
    }

    public boolean af() {
        return this.b.get(SportDetailChartDataType.PULL_FREQ).i();
    }

    public boolean ah() {
        return this.b.get(SportDetailChartDataType.REALTIME_PACE).i();
    }

    public boolean aj() {
        return bh() || this.j.requestSkippingSpeedList() == null || this.j.requestSkippingSpeedList().size() <= 1 || !b(this.j.requestSkippingSpeedList());
    }

    private boolean b(List<kob> list) {
        for (kob kobVar : list) {
            if (kobVar != null && kobVar.c() > 0) {
                return true;
            }
        }
        return false;
    }

    public Pair<float[], float[]> c() {
        MotionPathSimplify motionPathSimplify = this.i;
        if (motionPathSimplify == null) {
            LogUtil.a("Track_TrackDetailDataManager", "acquireSkipPerformanceScore, mMotionPathSimplify is null");
            return null;
        }
        float extendDataFloat = motionPathSimplify.getExtendDataFloat("maxSkipSpeed");
        float extendDataFloat2 = this.i.getExtendDataFloat("maxSkipSpeedRank");
        float extendDataFloat3 = this.i.getExtendDataFloat("enduranceTimeAbility") / 60.0f;
        float extendDataFloat4 = this.i.getExtendDataFloat("enduranceTimeAbilityRank");
        float extendDataFloat5 = this.i.getExtendDataFloat("maxSkippingTimes");
        float extendDataFloat6 = this.i.getExtendDataFloat("maxSkippingTimesAbilityRank");
        return new Pair<>(new float[]{extendDataFloat, extendDataFloat3, extendDataFloat5, this.i.getExtendDataFloat("skipNum"), this.i.getExtendDataFloat("enduranceAbility")}, new float[]{extendDataFloat2, extendDataFloat4, extendDataFloat6, this.i.getExtendDataFloat("skipNumAbilityRank"), this.i.getExtendDataFloat("enduranceAbilityRank")});
    }

    public void d(Pair<float[], float[]> pair) {
        this.h = pair;
    }

    public Pair<float[], float[]> a() {
        return this.h;
    }

    public boolean ae() {
        return bh() || this.j.requestWeightList() == null || this.j.requestWeightList().size() <= 1;
    }

    public boolean ag() {
        return bh() || this.j.requestHeartRecoveryRateList() == null || this.j.requestHeartRecoveryRateList().size() <= 1;
    }

    public boolean an() {
        return this.b.get(SportDetailChartDataType.GROUND_CONTACT_TIME).i() && this.b.get(SportDetailChartDataType.GROUND_IMPACT_ACCELERATION).i() && this.b.get(SportDetailChartDataType.HANG_TIME).i() && this.b.get(SportDetailChartDataType.GROUND_HANG_TIME_RATE).i() && this.b.get(SportDetailChartDataType.GC_TIME_BALANCE).i() && this.b.get(SportDetailChartDataType.VERTICAL_OSCILLATION).i() && this.b.get(SportDetailChartDataType.VERTICAL_RATIO).i() && this.b.get(SportDetailChartDataType.ACTIVE_PEAK).i();
    }

    private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        objectInputStream.defaultReadObject();
        this.b = new HashMap<>(20);
    }

    public boolean l() {
        MotionPath motionPath = this.j;
        if (motionPath != null && motionPath.requestRealTimePaceList() != null && this.j.requestRunningPostureList() != null && this.j.requestRunningPostureList().size() >= 3) {
            return false;
        }
        LogUtil.h("Track_TrackDetailDataManager", "motionPath is invalid for posture");
        return true;
    }

    public boolean at() {
        return this.f13191a;
    }

    public void a(boolean z) {
        this.f13191a = z;
    }

    public boolean ax() {
        return this.e;
    }

    public void d(boolean z) {
        this.e = z;
    }

    public String h() {
        return this.f;
    }

    public void c(String str) {
        this.f = str;
    }

    public boolean a(Context context) {
        if (this.i == null) {
            return false;
        }
        int e = e(context);
        if (bw()) {
            return e == 1 || (e == 3 && !CommonUtil.bv());
        }
        return false;
    }

    private boolean bw() {
        int requestSportType;
        MotionPathSimplify motionPathSimplify = this.i;
        return (motionPathSimplify == null || (requestSportType = motionPathSimplify.requestSportType()) == 266 || requestSportType == 262 || requestSportType == 222) ? false : true;
    }
}
