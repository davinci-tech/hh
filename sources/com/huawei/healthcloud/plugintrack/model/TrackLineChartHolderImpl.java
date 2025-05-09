package com.huawei.healthcloud.plugintrack.model;

import android.content.Context;
import com.huawei.hwfoundationmodel.trackmodel.HeartRateData;
import com.huawei.hwfoundationmodel.trackmodel.StepRateData;
import com.huawei.ui.commonui.view.trackview.TrackLineChartHolder;
import defpackage.ffn;
import defpackage.ffs;
import defpackage.ixt;
import defpackage.knw;
import defpackage.kny;
import defpackage.knz;
import defpackage.kob;
import defpackage.koc;
import defpackage.kof;
import defpackage.kog;
import defpackage.koh;
import defpackage.koi;
import defpackage.kol;
import defpackage.kom;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes4.dex */
public class TrackLineChartHolderImpl extends TrackLineChartHolder {

    /* renamed from: a, reason: collision with root package name */
    private int f3529a;
    private List<ffs> aa;
    private float ab;
    private ArrayList<HeartRateData> ac;
    private List<ffn> ad;
    private List<kob> ae;
    private ArrayList<StepRateData> af;
    private float ag;
    private List<kof> ah;
    private int ai;
    private int aj;
    private float ak;
    private int al;
    private List<kol> am;
    private int an;
    private List<kom> ao;
    private int b;
    private float c;
    private int d;
    private ArrayList<knz> e;
    private List<kny> f;
    private float g;
    private int h;
    private ArrayList<HeartRateData> i;
    private ArrayList<HeartRateData> j;
    private ArrayList<ixt> k;
    private ArrayList<HeartRateData> l;
    private int m;
    private int n;
    private int o;
    private List<kog> p;
    private List<koc> q;
    private int r;
    private List<knw> s;
    private int t;
    private List<koh> u;
    private float v;
    private ArrayList<koi> w;
    private float x;
    private int y;
    private int z;

    public TrackLineChartHolderImpl(Context context) {
        super(context);
        this.x = -1.0f;
        this.g = -1.0f;
        this.ab = -1.0f;
        this.v = -1.0f;
        this.c = -1.0f;
        this.ak = -1.0f;
    }

    @Override // com.huawei.ui.commonui.view.trackview.TrackLineChartHolder
    public ArrayList<HeartRateData> acquireHeartRateData() {
        return this.j;
    }

    public void d(ArrayList<HeartRateData> arrayList) {
        this.j = arrayList;
    }

    public void e(ArrayList<HeartRateData> arrayList) {
        this.i = arrayList;
    }

    public void c(ArrayList<HeartRateData> arrayList) {
        this.l = arrayList;
    }

    @Override // com.huawei.ui.commonui.view.trackview.TrackLineChartHolder
    public int acquireHeartRateDataInterval() {
        return this.h;
    }

    @Override // com.huawei.ui.commonui.view.trackview.TrackLineChartHolder
    public float acquireHeartRateDataSumTime() {
        return this.g;
    }

    @Override // com.huawei.ui.commonui.view.trackview.TrackLineChartHolder
    public float acquireRecoverHeartRateDataSumTime() {
        return this.x;
    }

    @Override // com.huawei.ui.commonui.view.trackview.TrackLineChartHolder
    public ArrayList<HeartRateData> acquireInvalidHeartRateData() {
        return this.i;
    }

    @Override // com.huawei.ui.commonui.view.trackview.TrackLineChartHolder
    public ArrayList<HeartRateData> acquireInvalidUnixHeartRateData() {
        return this.l;
    }

    public void b(int i) {
        this.h = i;
    }

    public void e(float f) {
        this.g = f;
    }

    public void c(float f) {
        this.x = f;
    }

    public void d(float f) {
        this.ab = f;
    }

    @Override // com.huawei.ui.commonui.view.trackview.TrackLineChartHolder
    public float acquireRealTimePaceDataSumTime() {
        return this.v;
    }

    public void a(float f) {
        this.v = f;
    }

    @Override // com.huawei.ui.commonui.view.trackview.TrackLineChartHolder
    public float acquireRunningPostureDataSumTime() {
        return this.ab;
    }

    public void c() {
        this.g = -1.0f;
    }

    @Override // com.huawei.ui.commonui.view.trackview.TrackLineChartHolder
    public ArrayList<StepRateData> acquireStepRateData() {
        return this.af;
    }

    public void a(ArrayList<StepRateData> arrayList) {
        this.af = arrayList;
    }

    @Override // com.huawei.ui.commonui.view.trackview.TrackLineChartHolder
    public int acquireJumpDataInterval() {
        return this.n;
    }

    public void d(int i) {
        this.n = i;
    }

    public void b(ArrayList<ixt> arrayList) {
        this.k = arrayList;
    }

    @Override // com.huawei.ui.commonui.view.trackview.TrackLineChartHolder
    public ArrayList<ixt> acquireTrackJumpData() {
        return this.k;
    }

    @Override // com.huawei.ui.commonui.view.trackview.TrackLineChartHolder
    public int acquireStepRateDataInterval() {
        return this.aj;
    }

    public void i(int i) {
        this.aj = i;
    }

    @Override // com.huawei.ui.commonui.view.trackview.TrackLineChartHolder
    public float acquireStepRateSumTime() {
        return this.ak;
    }

    public void g(float f) {
        this.ak = f;
    }

    @Override // com.huawei.ui.commonui.view.trackview.TrackLineChartHolder
    public ArrayList<knz> acquireTrackAltitudeData() {
        return this.e;
    }

    public void f(ArrayList<knz> arrayList) {
        this.e = arrayList;
    }

    @Override // com.huawei.ui.commonui.view.trackview.TrackLineChartHolder
    public int acquireTrackAltitudeDataInterval() {
        return this.b;
    }

    public void k(int i) {
        this.b = i;
    }

    @Override // com.huawei.ui.commonui.view.trackview.TrackLineChartHolder
    public int acquireTrackAltitudeShowType() {
        return this.f3529a;
    }

    public void m(int i) {
        this.f3529a = i;
    }

    @Override // com.huawei.ui.commonui.view.trackview.TrackLineChartHolder
    public ArrayList<koi> acquireTrackRealTimeSpeedData() {
        return this.w;
    }

    public void h(ArrayList<koi> arrayList) {
        this.w = arrayList;
    }

    @Override // com.huawei.ui.commonui.view.trackview.TrackLineChartHolder
    public int acquireTrackRealTimeSpeedDataInterval() {
        return this.y;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.huawei.ui.commonui.view.trackview.TrackLineChartHolder
    /* renamed from: b, reason: merged with bridge method [inline-methods] */
    public ArrayList<HeartRateData> acquireTrackHeartRateData() {
        return this.ac;
    }

    public void i(ArrayList<HeartRateData> arrayList) {
        this.ac = arrayList;
    }

    public void l(int i) {
        this.y = i;
    }

    @Override // com.huawei.ui.commonui.view.trackview.TrackLineChartHolder
    public List<kol> acquireSwolfData() {
        return this.am;
    }

    public void j(List<kol> list) {
        this.am = list;
    }

    @Override // com.huawei.ui.commonui.view.trackview.TrackLineChartHolder
    public int acquireSwolfDataInterval() {
        return this.al;
    }

    public void n(int i) {
        this.al = i;
    }

    public void d(List<ffn> list) {
        this.ad = list;
    }

    @Override // com.huawei.ui.commonui.view.trackview.TrackLineChartHolder
    public List<knw> acquirePaddleFrequencyData() {
        return this.s;
    }

    @Override // com.huawei.ui.commonui.view.trackview.TrackLineChartHolder
    public int acquirePaddleFreqInterval() {
        return this.m;
    }

    public void a(int i) {
        this.m = i;
    }

    public void e(List<knw> list) {
        this.s = list;
    }

    @Override // com.huawei.ui.commonui.view.trackview.TrackLineChartHolder
    public List<ffn> acquireRidePostureData() {
        return this.ad;
    }

    @Override // com.huawei.ui.commonui.view.trackview.TrackLineChartHolder
    public int acquireCadenceInterval() {
        return this.d;
    }

    @Override // com.huawei.ui.commonui.view.trackview.TrackLineChartHolder
    public float acquireCadenceSumTime() {
        return this.c;
    }

    public void b(float f) {
        this.c = f;
    }

    public void e(int i) {
        this.d = i;
    }

    @Override // com.huawei.ui.commonui.view.trackview.TrackLineChartHolder
    public List<koc> acquirePoweData() {
        return this.q;
    }

    @Override // com.huawei.ui.commonui.view.trackview.TrackLineChartHolder
    public List<kom> acquireWeightData() {
        return this.ao;
    }

    @Override // com.huawei.ui.commonui.view.trackview.TrackLineChartHolder
    public List<kob> acquireSkippingSpeedData() {
        return this.ae;
    }

    @Override // com.huawei.ui.commonui.view.trackview.TrackLineChartHolder
    public int acquireSkippingSpeedInterval() {
        return this.ai;
    }

    @Override // com.huawei.ui.commonui.view.trackview.TrackLineChartHolder
    public int acquirePowerDataInterval() {
        return this.t;
    }

    public void j(int i) {
        this.t = i;
    }

    @Override // com.huawei.ui.commonui.view.trackview.TrackLineChartHolder
    public int acquireWeightDataInterval() {
        return this.an;
    }

    public void o(int i) {
        this.an = i;
    }

    public void g(int i) {
        this.ai = i;
    }

    public void c(List<koc> list) {
        this.q = list;
    }

    public void f(List<kom> list) {
        this.ao = list;
    }

    public void b(List<kob> list) {
        this.ae = list;
    }

    @Override // com.huawei.ui.commonui.view.trackview.TrackLineChartHolder
    public List<kog> acquirePullFreqData() {
        return this.p;
    }

    public void h(List<kog> list) {
        this.p = list;
    }

    @Override // com.huawei.ui.commonui.view.trackview.TrackLineChartHolder
    public int acquirePullFreqDataInterval() {
        return this.r;
    }

    @Override // com.huawei.ui.commonui.view.trackview.TrackLineChartHolder
    public int acquireJumpTotalTime() {
        return this.o;
    }

    public void c(int i) {
        this.o = i;
    }

    public void h(int i) {
        this.r = i;
    }

    @Override // com.huawei.ui.commonui.view.trackview.TrackLineChartHolder
    public List<koh> acquireTrackRealTimePaceData() {
        return this.u;
    }

    public void i(List<koh> list) {
        this.u = list;
    }

    @Override // com.huawei.ui.commonui.view.trackview.TrackLineChartHolder
    public List<ffs> acquireRunningPostureData() {
        return this.aa;
    }

    public void a(List<ffs> list) {
        this.aa = list;
    }

    @Override // com.huawei.ui.commonui.view.trackview.TrackLineChartHolder
    public int acquireRunningPostureDataInterval() {
        return this.z;
    }

    public void f(int i) {
        this.z = i;
    }

    @Override // com.huawei.ui.commonui.view.trackview.TrackLineChartHolder
    public List<kof> acquireTrackSpo2Data() {
        return this.ah;
    }

    public void g(List<kof> list) {
        this.ah = list;
    }

    @Override // com.huawei.ui.commonui.view.trackview.TrackLineChartHolder
    public float acquireSpo2SumTime() {
        return this.ag;
    }

    public void j(float f) {
        this.ag = f;
    }

    public void n(List<kny> list) {
        this.f = list;
    }

    @Override // com.huawei.ui.commonui.view.trackview.TrackLineChartHolder
    public List<kny> getHeartRateAreaList() {
        return this.f;
    }
}
