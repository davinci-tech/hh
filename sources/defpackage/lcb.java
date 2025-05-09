package defpackage;

import android.content.Context;
import android.util.Pair;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import com.github.mikephil.charting.data.Entry;
import com.huawei.health.R;
import com.huawei.hwfoundationmodel.trackmodel.HeartRateData;
import com.huawei.hwfoundationmodel.trackmodel.MotionPath;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.indoorequip.datastruct.SupportDataRange;
import com.huawei.indoorequip.ui.hrcontrol.HeartRateControlSportChart;
import com.huawei.indoorequip.ui.view.SportEquipItemDrawer;
import com.huawei.indoorequip.viewmodel.IndoorEquipViewModel;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import health.compact.a.SharedPreferenceManager;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes5.dex */
public class lcb {

    /* renamed from: a, reason: collision with root package name */
    private HeartRateControlSportChart f14758a;
    private HealthTextView aa;
    private HealthTextView ab;
    private List<String> ac;
    private int ad;
    private HealthTextView ae;
    private HealthTextView af;
    private HealthTextView ag;
    private HealthTextView ah;
    private LinearLayout ai;
    private HealthTextView aj;
    private HealthTextView ak;
    private HealthTextView al;
    private HealthTextView an;
    private IndoorEquipViewModel ar;
    private lbs b;
    private LinearLayout c;
    private lca d;
    private lbu e;
    private boolean f;
    private View g;
    private boolean h;
    private Context j;
    private SportEquipItemDrawer k;
    private SportEquipItemDrawer l;
    private SportEquipItemDrawer n;
    private LinearLayout o;
    private LinearLayout p;
    private View r;
    private LinearLayout t;
    private String u;
    private int v;
    private List<Pair<Integer, Integer>> w;
    private List<Pair<Integer, Integer>> x;
    private List<Integer> y;
    private HealthTextView z;
    private LinearLayout.LayoutParams m = new LinearLayout.LayoutParams(-2, -2);
    private LinearLayout.LayoutParams s = new LinearLayout.LayoutParams(-2, -2);
    private LinearLayout.LayoutParams am = new LinearLayout.LayoutParams(-2, -2);
    private int i = 1;
    private int q = 0;

    public lcb(Context context, View view, IndoorEquipViewModel indoorEquipViewModel, boolean z) {
        this.u = "";
        this.j = context;
        this.r = view;
        this.ar = indoorEquipViewModel;
        this.f = z;
        if (indoorEquipViewModel != null) {
            this.v = indoorEquipViewModel.getSportType();
            this.u = indoorEquipViewModel.r();
        }
        a();
        this.d = new lca(this.f14758a);
    }

    public void d(SupportDataRange supportDataRange) {
        this.n = new SportEquipItemDrawer(this.j);
        this.k = new SportEquipItemDrawer(this.j);
        HashMap hashMap = new HashMap();
        this.e = new lbu(this.j, this.v, true, supportDataRange);
        d(hashMap);
    }

    public void b(float f, float f2, float f3, SupportDataRange supportDataRange) {
        this.l = new SportEquipItemDrawer(this.j);
        HashMap hashMap = new HashMap();
        lbs lbsVar = new lbs(this.j, this.v, true, f, f2, supportDataRange);
        this.b = lbsVar;
        lbsVar.c(f3);
        a(hashMap);
        lca lcaVar = this.d;
        if (lcaVar != null) {
            lcaVar.c();
        }
        b(this.ar.k());
    }

    public void c(Map<Integer, Object> map) {
        if (this.r == null) {
            LogUtil.h("HeartRateControlSportViewHolder", "root view is null");
            return;
        }
        if (map == null) {
            LogUtil.h("HeartRateControlSportViewHolder", "sport data map is null");
            return;
        }
        g(map);
        h(map);
        j(map);
        d(map);
        if (this.n.getParent() != null && (this.n.getParent() instanceof ViewGroup)) {
            ((ViewGroup) this.n.getParent()).removeView(this.n);
        }
        if (this.k.getParent() != null && (this.k.getParent() instanceof ViewGroup)) {
            ((ViewGroup) this.k.getParent()).removeView(this.k);
        }
        this.p.addView(this.k);
        this.o.addView(this.n);
        LogUtil.c("HeartRateControlSportViewHolder", "add view");
    }

    public void e(Map<Integer, Object> map) {
        if (this.r == null) {
            LogUtil.h("HeartRateControlSportViewHolder", "tv root view is null");
            return;
        }
        if (map == null) {
            LogUtil.h("HeartRateControlSportViewHolder", "tv sport data map is null");
            return;
        }
        h(map);
        j(map);
        a(map);
        if (this.l.getParent() != null && (this.l.getParent() instanceof ViewGroup)) {
            ((ViewGroup) this.l.getParent()).removeView(this.l);
        }
        this.ai.addView(this.l);
        LogUtil.c("HeartRateControlSportViewHolder", "add tv sport view");
    }

    public void b(Map<String, Object> map) {
        if (map == null) {
            LogUtil.h("HeartRateControlSportViewHolder", "course data map is null");
            return;
        }
        if (map.containsKey("COURSE_MIN_HEART_RATE") && map.containsKey("COURSE_MAX_HEART_RATE")) {
            int e = dtk.b().e();
            int d = dtk.b().d();
            LogUtil.a("HeartRateControlSportViewHolder", "update chart, maxHeartRate ", Integer.valueOf(e), " restHeartRate ", Integer.valueOf(d));
            this.d.a(d - ((Integer) map.get("COURSE_MIN_HEART_RATE")).intValue(), e + ((Integer) map.get("COURSE_MAX_HEART_RATE")).intValue());
        }
        if (map.containsKey("COURSE_NAME_LIST")) {
            this.ac = (List) map.get("COURSE_NAME_LIST");
        }
        if (map.containsKey("COURSE_DURATION_LIST")) {
            this.y = (List) map.get("COURSE_DURATION_LIST");
            d();
        }
        if (map.containsKey("COURSE_HEART_RATE_LIST")) {
            this.x = (List) map.get("COURSE_HEART_RATE_LIST");
            d();
        }
        if (map.containsKey("COURSE_CADENCE_LIST") && (map.get("COURSE_CADENCE_LIST") instanceof List)) {
            this.w = (List) map.get("COURSE_CADENCE_LIST");
        }
        if (map.containsKey("COURSE_DURATION")) {
            this.ad = ((Integer) map.get("COURSE_DURATION")).intValue();
            if (b() && !this.h) {
                this.h = true;
                e();
                j();
            }
        }
        if (map.containsKey("CURRENT_STAGE_NUMBER")) {
            d(((Integer) map.get("CURRENT_STAGE_NUMBER")).intValue());
        }
    }

    private void a() {
        this.ae = (HealthTextView) this.r.findViewById(R.id.tv_current_stage);
        this.an = (HealthTextView) this.r.findViewById(R.id.tv_total_stage);
        this.al = (HealthTextView) this.r.findViewById(R.id.tv_stage_name);
        this.ak = (HealthTextView) this.r.findViewById(R.id.tv_stage_duration);
        this.ah = (HealthTextView) this.r.findViewById(R.id.tv_recommend_hr_interval);
        this.af = (HealthTextView) this.r.findViewById(R.id.tv_current_hr_value);
        this.aj = (HealthTextView) this.r.findViewById(R.id.tv_stage_divider);
        this.ag = (HealthTextView) this.r.findViewById(R.id.tv_recommend_hr_title);
        this.ab = (HealthTextView) this.r.findViewById(R.id.tv_chart_legend);
        this.z = (HealthTextView) this.r.findViewById(R.id.tv_recommend_cadence_interval);
        this.c = (LinearLayout) this.r.findViewById(R.id.layout_cadence_data);
        this.g = this.r.findViewById(R.id.center_divider);
        this.aa = (HealthTextView) this.r.findViewById(R.id.tv_current_cadence_value);
        this.t = (LinearLayout) this.r.findViewById(R.id.realtime_hr_layout);
        HeartRateControlSportChart heartRateControlSportChart = (HeartRateControlSportChart) this.r.findViewById(R.id.recommend_interval_chart);
        this.f14758a = heartRateControlSportChart;
        heartRateControlSportChart.setNoDataText("");
        if (this.f) {
            this.ai = (LinearLayout) this.r.findViewById(R.id.ll_sport_data_layout);
        } else {
            this.o = (LinearLayout) this.r.findViewById(R.id.ll_left_data_layout);
            this.p = (LinearLayout) this.r.findViewById(R.id.ll_right_data_layout);
        }
        this.af.setText("--");
        this.aa.setText("--");
        this.ab.setText(this.j.getString(R.string._2130841430_res_0x7f020f56));
        f();
    }

    private void f() {
        if (this.v == 264) {
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
            layoutParams.alignWithParent = true;
            layoutParams.addRule(14);
            this.t.setLayoutParams(layoutParams);
        }
        if (this.v == 265) {
            this.g.setVisibility(0);
            this.c.setVisibility(0);
        }
    }

    private void d(int i) {
        LogUtil.a("HeartRateControlSportViewHolder", "enter updateStageInfo num: ", Integer.valueOf(i));
        if (!c()) {
            LogUtil.h("HeartRateControlSportViewHolder", "updateStageInfo course data not received");
            return;
        }
        if (i < 1 || i < this.i || i > this.ac.size()) {
            LogUtil.h("HeartRateControlSportViewHolder", "updateStageInfo stage num error");
            return;
        }
        this.i = i;
        int i2 = i - 1;
        this.ae.setText(String.valueOf(i));
        this.an.setText(String.valueOf(this.ac.size()));
        this.al.setText(this.ac.get(i2));
        this.ak.setText(this.j.getString(R.string._2130837641_res_0x7f020089, dju.a(this.y.get(i2).intValue())).replaceAll(" ", ""));
        Pair<Integer, Integer> pair = this.x.get(i2);
        this.ah.setText(pair.first + " - " + pair.second);
        this.aj.setVisibility(0);
        this.ag.setVisibility(0);
        if (this.v != 265 || koq.b(this.w)) {
            return;
        }
        Pair<Integer, Integer> pair2 = this.w.get(i2);
        this.z.setText(pair2.first + " - " + pair2.second);
    }

    private void d() {
        if (b()) {
            ArrayList arrayList = new ArrayList();
            int i = 0;
            for (int i2 = 0; i2 < this.x.size(); i2++) {
                int intValue = this.y.get(i2).intValue();
                Pair<Integer, Integer> pair = this.x.get(i2);
                i += intValue;
                arrayList.add(new nns(i, ((Integer) pair.second).intValue(), i, ((Integer) pair.first).intValue()));
            }
            this.d.e(arrayList);
            this.d.a(i);
        }
    }

    private void g(Map<Integer, Object> map) {
        int intValue = map.containsKey(2) ? ((Integer) map.get(2)).intValue() : 0;
        if (intValue > 0 && intValue <= this.ad && intValue != this.q) {
            this.q = intValue;
            LogUtil.a("HeartRateControlSportViewHolder", "saveLastSportDuration:", Integer.valueOf(intValue));
            b("lastSportDuration", intValue);
        }
        IndoorEquipViewModel indoorEquipViewModel = this.ar;
        if (indoorEquipViewModel == null || indoorEquipViewModel.getMotionPath() == null) {
            LogUtil.h("HeartRateControlSportViewHolder", "saveSportDuration mViewModel or MotionPath is null");
            return;
        }
        MotionPath motionPath = this.ar.getMotionPath();
        ArrayList<HeartRateData> requestHeartRateList = motionPath.requestHeartRateList();
        ArrayList<HeartRateData> requestInvalidHeartRateList = motionPath.requestInvalidHeartRateList();
        if (koq.b(requestHeartRateList) && koq.b(requestInvalidHeartRateList)) {
            LogUtil.a("HeartRateControlSportViewHolder", "saveStartSportDuration:", Integer.valueOf(intValue));
            b("startSportDuration", intValue);
        }
    }

    private void j() {
        IndoorEquipViewModel indoorEquipViewModel = this.ar;
        if (indoorEquipViewModel == null) {
            LogUtil.h("HeartRateControlSportViewHolder", "showHistoryHeartRate mViewModel is null");
            return;
        }
        List<HeartRateData> a2 = a(indoorEquipViewModel.getMotionPath());
        if (koq.b(a2)) {
            return;
        }
        LogUtil.a("HeartRateControlSportViewHolder", "showHistoryHeartRate start show history heart rate data,", Integer.valueOf(a2.size()));
        ArrayList arrayList = new ArrayList();
        for (HeartRateData heartRateData : a2) {
            int acquireTime = (int) heartRateData.acquireTime();
            int acquireHeartRate = heartRateData.acquireHeartRate();
            if (acquireTime >= 0 && acquireTime < this.ad && acquireHeartRate >= 40 && acquireHeartRate <= 220) {
                arrayList.add(new Entry(acquireTime, acquireHeartRate));
            }
        }
        this.d.a(arrayList);
    }

    private void e() {
        IndoorEquipViewModel indoorEquipViewModel = this.ar;
        if (indoorEquipViewModel == null) {
            LogUtil.h("HeartRateControlSportViewHolder", "adjustChartWithHistoryHr mViewModel is null");
            return;
        }
        if (indoorEquipViewModel.getMotionPath() == null || koq.b(this.ar.getMotionPath().requestHeartRateList())) {
            LogUtil.h("HeartRateControlSportViewHolder", "requestHeartRateList is empty");
            return;
        }
        ArrayList<HeartRateData> requestHeartRateList = this.ar.getMotionPath().requestHeartRateList();
        LogUtil.a("HeartRateControlSportViewHolder", "heartRateList start show history heart rate data,", requestHeartRateList);
        int acquireHeartRate = requestHeartRateList.get(0).acquireHeartRate();
        int acquireHeartRate2 = requestHeartRateList.get(0).acquireHeartRate();
        Iterator<HeartRateData> it = requestHeartRateList.iterator();
        while (it.hasNext()) {
            int acquireHeartRate3 = it.next().acquireHeartRate();
            acquireHeartRate = Math.max(acquireHeartRate, acquireHeartRate3);
            acquireHeartRate2 = Math.min(acquireHeartRate2, acquireHeartRate3);
        }
        this.d.b(acquireHeartRate);
        this.d.b(acquireHeartRate2);
    }

    private List<HeartRateData> a(MotionPath motionPath) {
        if (motionPath == null || koq.b(motionPath.requestHeartRateList())) {
            LogUtil.h("HeartRateControlSportViewHolder", "getHistoryHeartRateData heart rate data is null");
            return null;
        }
        if (b("lastSportDuration") == 0) {
            LogUtil.h("HeartRateControlSportViewHolder", "getHistoryHeartRateData last sport duration is zero");
            return null;
        }
        ArrayList arrayList = new ArrayList(16);
        ArrayList<HeartRateData> requestHeartRateList = motionPath.requestHeartRateList();
        List<HeartRateData> requestInvalidHeartRateList = motionPath.requestInvalidHeartRateList();
        if (!koq.b(requestInvalidHeartRateList)) {
            e(requestHeartRateList, requestInvalidHeartRateList, arrayList);
        } else {
            arrayList.addAll(requestHeartRateList);
        }
        int b = b("startSportDuration");
        float size = (r2 - b) / (arrayList.size() > 1 ? arrayList.size() - 1 : 1);
        ArrayList arrayList2 = new ArrayList(16);
        for (int i = 0; i < arrayList.size(); i++) {
            HeartRateData heartRateData = (HeartRateData) arrayList.get(i);
            if (heartRateData != null) {
                arrayList2.add(new HeartRateData((long) (b + (i * size)), heartRateData.acquireHeartRate()));
            }
        }
        return arrayList2;
    }

    private void e(List<HeartRateData> list, List<HeartRateData> list2, List<HeartRateData> list3) {
        int i = 0;
        for (HeartRateData heartRateData : list) {
            while (i < list2.size()) {
                HeartRateData heartRateData2 = list2.get(i);
                if (heartRateData2.acquireTime() >= heartRateData.acquireTime()) {
                    break;
                }
                i++;
                list3.add(new HeartRateData(heartRateData2.acquireTime(), heartRateData2.acquireHeartRate()));
            }
            if (list3 != null) {
                list3.add(new HeartRateData(heartRateData.acquireTime(), heartRateData.acquireHeartRate()));
            }
        }
    }

    private int b(String str) {
        return SharedPreferenceManager.a(Integer.toString(20002), str, 0);
    }

    private void b(String str, int i) {
        SharedPreferenceManager.b(Integer.toString(20002), str, i);
    }

    private boolean b() {
        if (koq.b(this.y)) {
            LogUtil.h("HeartRateControlSportViewHolder", "stage duration list is null");
            return false;
        }
        if (koq.b(this.x)) {
            LogUtil.h("HeartRateControlSportViewHolder", "stage hr scope list is null");
            return false;
        }
        if (this.y.size() == this.x.size()) {
            return true;
        }
        LogUtil.h("HeartRateControlSportViewHolder", "stage size diff");
        return false;
    }

    private boolean c() {
        if (koq.b(this.ac)) {
            LogUtil.h("HeartRateControlSportViewHolder", "stage name list is null");
            return false;
        }
        if (!b() || this.y.size() == this.ac.size()) {
            return true;
        }
        LogUtil.h("HeartRateControlSportViewHolder", "stage size diff");
        return false;
    }

    private void d(Map<Integer, Object> map) {
        int i = this.v;
        if (i == 264 || i == 265) {
            b(map, 3);
            d(map, 3);
        } else {
            LogUtil.a("HeartRateControlSportViewHolder", "other sportType =", Integer.valueOf(i));
        }
    }

    private void a(Map<Integer, Object> map) {
        int i = this.v;
        if (i == 264 || i == 265) {
            a(map, 6);
        } else {
            LogUtil.a("HeartRateControlSportViewHolder", "other tv sportType =", Integer.valueOf(i));
        }
    }

    private void h(Map<Integer, Object> map) {
        if (this.d == null) {
            LogUtil.h("HeartRateControlSportViewHolder", "updateChartData mChartHolder is null");
            return;
        }
        int intValue = map.containsKey(2) ? ((Integer) map.get(2)).intValue() : 0;
        int intValue2 = map.containsKey(20004) ? ((Integer) map.get(20004)).intValue() : 0;
        if (intValue >= 0) {
            this.d.b(intValue, intValue2);
        }
    }

    private void j(Map<Integer, Object> map) {
        int intValue = map.containsKey(20004) ? ((Integer) map.get(20004)).intValue() : 0;
        if (intValue > 0 && intValue < 255) {
            this.af.setText(String.valueOf(intValue));
        } else {
            this.af.setText("--");
        }
        if (this.v == 265) {
            r2 = map.get(31) != null ? ((Integer) map.get(31)).intValue() : 0;
            this.aa.setText(String.valueOf(r2));
        }
        LogUtil.a("HeartRateControlSportViewHolder", "updateHeartRateData hr: ", Integer.valueOf(intValue), " cadence ", Integer.valueOf(r2));
    }

    private void b(Map<Integer, Object> map, int i) {
        this.n.e(i);
        this.n.setLayoutParams(this.m);
        int[] d = lbj.d(this.v, 8, -1, this.u);
        LogUtil.a("HeartRateControlSportViewHolder", "left sport data items: ", Arrays.toString(d));
        this.e.e(this.n, map, d);
    }

    private void d(Map<Integer, Object> map, int i) {
        this.k.e(i);
        this.k.setLayoutParams(this.s);
        int[] d = lbj.d(this.v, 9, -1, this.u);
        LogUtil.a("HeartRateControlSportViewHolder", "right sport data items: ", Arrays.toString(d));
        this.e.e(this.k, map, d);
    }

    private void a(Map<Integer, Object> map, int i) {
        this.l.e(i);
        this.l.setLayoutParams(this.am);
        int[] d = lbj.d(this.v, 10, -1, this.u);
        LogUtil.a("HeartRateControlSportViewHolder", "tv sport data items: ", Arrays.toString(d));
        this.b.a(this.l, map, d);
    }
}
