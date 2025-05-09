package com.huawei.ui.main.stories.history;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.WindowManager;
import android.widget.LinearLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.huawei.basefitnessadvice.model.FitnessTrackRecord;
import com.huawei.haf.application.BaseApplication;
import com.huawei.health.R;
import com.huawei.health.courseplanservice.api.RecordApi;
import com.huawei.healthcloud.plugintrack.ui.sporttypeconfig.bean.HwSportDataStaticsInfo;
import com.huawei.healthcloud.plugintrack.ui.sporttypeconfig.bean.HwSportTypeInfo;
import com.huawei.healthcloud.plugintrack.ui.view.DetailItemContainer;
import com.huawei.healthcloud.plugintrack.ui.view.SportDetailItem;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwcommonmodel.fitnessdatatype.HeartRateThresholdConfig;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.ui.commonui.cardview.HealthCardView;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.recycleview.HealthRecycleView;
import com.huawei.ui.main.R$string;
import com.huawei.ui.main.stories.history.adapter.AllSportRankListAdapter;
import com.huawei.ui.main.stories.history.adapter.AllSportStatSumAdapter;
import com.huawei.ui.main.stories.history.adapter.DivingDataAdapter;
import com.huawei.ui.main.stories.history.model.SportRecordDataAdapter;
import com.huawei.ui.main.stories.history.model.SportRecordStatData;
import defpackage.gts;
import defpackage.hkc;
import defpackage.hln;
import defpackage.ixx;
import defpackage.koq;
import defpackage.kor;
import defpackage.kpw;
import defpackage.kwy;
import defpackage.nrn;
import defpackage.nsn;
import defpackage.nsy;
import defpackage.rci;
import defpackage.rdu;
import defpackage.rdw;
import defpackage.red;
import health.compact.a.Services;
import health.compact.a.UnitUtil;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes7.dex */
public class SportDataInteractor implements SportRecordDataAdapter {

    /* renamed from: a, reason: collision with root package name */
    private HealthTextView f10283a;
    private HealthTextView aa;
    private HealthRecycleView ac;
    private DetailItemContainer ae;
    private Resources ai;
    private int aj;
    private List<HwSportDataStaticsInfo> ak;
    private d am;
    private hln an;
    private int ap;
    private LinearLayout ar;
    private HealthRecycleView as;
    private AllSportRankListAdapter b;
    private HealthTextView c;
    private AllSportStatSumAdapter e;
    private View f;
    private HealthTextView g;
    private HealthTextView h;
    private View i;
    private HealthTextView j;
    private DetailItemContainer k;
    private WeakReference<Context> l;
    private HealthTextView o;
    private int q;
    private DivingDataAdapter r;
    private HealthTextView z;
    private int d = 3;
    private int ao = nrn.d(R.color._2131299236_res_0x7f090ba4);
    private boolean w = true;
    private List<HiHealthData> p = new ArrayList();
    private List<HiHealthData> t = new ArrayList();
    private List<HiHealthData> af = new ArrayList();
    private List<SportDetailItem> v = new ArrayList(10);
    private List<SportDetailItem> ah = new ArrayList(10);
    private Handler ab = new e();
    private int ag = 0;
    private int u = 0;
    private final Object s = new Object();
    private List<rdw> aq = new ArrayList(this.d);
    private List<rci> ad = new ArrayList();
    private int n = 0;
    private int m = 0;
    private double y = -1.0d;
    private double x = -1.0d;
    private SportRecordStatData al = new SportRecordStatData(BaseApplication.e());

    public SportDataInteractor(Context context) {
        this.l = new WeakReference<>(context);
    }

    public void b(int i) {
        this.aj = i;
        hln c = hln.c(b());
        this.an = c;
        if (c.d(this.aj) == null || this.an.d(this.aj).getSportDataStatics() == null) {
            LogUtil.b("Track_SportDataInteractor", "can not find sport type in json");
        } else {
            LogUtil.a("Track_SportDataInteractor", "mSportType:", Integer.valueOf(i));
            this.ak = this.an.d(this.aj).getSportDataStatics();
        }
    }

    public void dJM_(Activity activity) {
        if (activity == null) {
            return;
        }
        int i = this.aj;
        if (i == 0) {
            this.am = new d(this, 2);
            LogUtil.a("Track_SportDataInteractor", "all sport type, query two times");
        } else if (i == 287) {
            this.am = new d(this, 2);
            LogUtil.a("Track_SportDataInteractor", "diving type");
        } else {
            this.am = new d(this, 1);
            LogUtil.a("Track_SportDataInteractor", "single sport type, query one time");
        }
        dJK_(activity);
        this.n = 0;
        this.m = 0;
        if (this.aj == 0) {
            dJH_(activity);
            a(AnalyticsValue.SPORT_RECORD_CHANGE_ALL_SPORT_CARD_2040190.value());
        }
    }

    private void dJH_(final Activity activity) {
        this.e = new AllSportStatSumAdapter(b(), this.aq);
        this.b = new AllSportRankListAdapter(b(), this.ad);
        LinearLayout linearLayout = (LinearLayout) nsy.cMc_(activity, R.id.all_sport_data);
        this.ar = linearLayout;
        this.as = (HealthRecycleView) linearLayout.findViewById(R.id.all_sport_sum);
        this.ac = (HealthRecycleView) this.ar.findViewById(R.id.all_sport_rank_list);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(b());
        linearLayoutManager.setOrientation(0);
        this.as.setHasFixedSize(true);
        this.as.setNestedScrollingEnabled(false);
        this.as.setAdapter(this.e);
        this.as.setLayoutManager(linearLayoutManager);
        this.aa = (HealthTextView) this.ar.findViewById(R.id.all_sport_data_subheader);
        this.z = (HealthTextView) this.ar.findViewById(R.id.all_sport_time_period);
        LinearLayoutManager linearLayoutManager2 = new LinearLayoutManager(b());
        linearLayoutManager2.setOrientation(1);
        this.ac.setHasFixedSize(true);
        this.ac.setNestedScrollingEnabled(false);
        this.ac.setAdapter(this.b);
        this.ac.setLayoutManager(linearLayoutManager2);
        this.e.b(new AllSportStatSumAdapter.OnItemClickListener() { // from class: com.huawei.ui.main.stories.history.SportDataInteractor.1
            @Override // com.huawei.ui.main.stories.history.adapter.AllSportStatSumAdapter.OnItemClickListener
            public void onItemClick(String str, int i) {
                Activity activity2 = activity;
                if (activity2 instanceof SportDataActivity) {
                    ((SportDataActivity) activity2).e(i);
                }
                SportDataInteractor.this.n = i;
                SportDataInteractor.this.a(AnalyticsValue.SPORT_RECORD_CHANGE_ALL_SPORT_CARD_2040190.value());
                SportDataInteractor.this.e(str);
                SportDataInteractor.this.f();
                SportDataInteractor.this.e.b(SportDataInteractor.this.n);
                SportDataInteractor.this.b.c(SportDataInteractor.this.n);
            }
        });
    }

    public int c() {
        return this.n;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(String str) {
        if (str.equals("Track_Duration_Sum")) {
            this.aa.setText(this.ai.getString(R$string.IDS_hwh_sport_duration_percentage));
            return;
        }
        if (str.equals("Track_Count_Sum")) {
            this.aa.setText(this.ai.getString(R$string.IDS_hwh_sport_exercise_times_percentage));
        } else if (str.equals("Track_Calorie_Sum")) {
            this.aa.setText(this.ai.getString(R$string.IDS_hwh_sport_calories_percentage));
        } else {
            LogUtil.a("Track_SportDataInteractor", "invalid type");
        }
    }

    public void c(String str) {
        HealthTextView healthTextView = this.z;
        if (healthTextView == null) {
            LogUtil.h("Track_SportDataInteractor", "mRankPeriod is null, sportType:", Integer.valueOf(this.aj));
        } else {
            healthTextView.setText(str);
        }
    }

    private void a() {
        LogUtil.a("Track_SportDataInteractor", "clear all sport data");
        this.aq.clear();
        this.ad.clear();
        this.ah.clear();
        this.ae.removeAllViews();
    }

    private void dJK_(Activity activity) {
        Resources resources = b().getResources();
        this.ai = resources;
        this.q = resources.getDimensionPixelSize(R.dimen._2131363709_res_0x7f0a077d);
        Object systemService = b().getSystemService(Constants.NATIVE_WINDOW_SUB_DIR);
        if (systemService instanceof WindowManager) {
            this.ap = ((WindowManager) systemService).getDefaultDisplay().getWidth() - (nsn.c(b(), 16.0f) * 2);
        }
        this.i = nsy.cMc_(activity, R.id.sport_data_data);
        this.f = nsy.cMc_(activity, R.id.share_track_detail_show_distance);
        this.c = (HealthTextView) nsy.cMc_(activity, R.id.sport_data_distance);
        this.f10283a = (HealthTextView) nsy.cMc_(activity, R.id.sport_data_total);
        this.j = (HealthTextView) nsy.cMc_(activity, R.id.sport_data_distance_unit);
        this.h = (HealthTextView) nsy.cMc_(activity, R.id.share_sport_data_distance);
        this.g = (HealthTextView) nsy.cMc_(activity, R.id.tv_sport_title);
        this.o = (HealthTextView) nsy.cMc_(activity, R.id.share_sport_data_distance_unit);
        this.ae = (DetailItemContainer) nsy.cMc_(activity, R.id.share_sport_data_container);
        if (this.aj != 0) {
            dJJ_(activity);
            h();
            LinearLayout linearLayout = (LinearLayout) nsy.cMc_(activity, R.id.track_data_diving_card);
            linearLayout.setVisibility(8);
            HealthCardView healthCardView = (HealthCardView) nsy.cMc_(activity, R.id.track_data_card);
            if (this.aj == 287) {
                dJI_(linearLayout);
                healthCardView.setCardBackgroundColor(b().getColor(R.color._2131296666_res_0x7f09019a));
            } else {
                healthCardView.setCardBackgroundColor(b().getColor(R.color._2131296971_res_0x7f0902cb));
            }
        }
    }

    private void dJI_(LinearLayout linearLayout) {
        linearLayout.setVisibility(0);
        HealthRecycleView healthRecycleView = (HealthRecycleView) linearLayout.findViewById(R.id.diving_data_card);
        this.r = new DivingDataAdapter(b(), this.m);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(b());
        linearLayoutManager.setOrientation(0);
        healthRecycleView.setHasFixedSize(true);
        healthRecycleView.setNestedScrollingEnabled(false);
        healthRecycleView.setAdapter(this.r);
        healthRecycleView.setLayoutManager(linearLayoutManager);
        this.r.e(new DivingDataAdapter.OnItemClickListener() { // from class: com.huawei.ui.main.stories.history.SportDataInteractor.5
            @Override // com.huawei.ui.main.stories.history.adapter.DivingDataAdapter.OnItemClickListener
            public void onItemClick(int i) {
                SportDataInteractor.this.m = i;
                SportDataInteractor.this.r.d(SportDataInteractor.this.m, SportDataInteractor.this.t, SportDataInteractor.this.af);
                SportDataInteractor.this.h();
            }
        });
    }

    private void dJJ_(Activity activity) {
        this.k = (DetailItemContainer) nsy.cMc_(activity, R.id.sport_data_container);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void h() {
        this.v.clear();
        this.ah.clear();
        n();
        this.k.removeAllViews();
        this.ae.removeAllViews();
        this.k.e(2);
        for (int i = 0; i < this.v.size(); i++) {
            SportDetailItem sportDetailItem = this.v.get(i);
            if (sportDetailItem == null) {
                LogUtil.h("Track_SportDataInteractor", "child is null");
                return;
            }
            ViewParent parent = sportDetailItem.getParent();
            if (parent instanceof ViewGroup) {
                ((ViewGroup) parent).removeAllViews();
            }
            this.k.addView(sportDetailItem);
            if ((i & 1) == 0 && i != this.v.size() - 1) {
                this.k.addView(dJL_());
            }
        }
        i();
        this.k.requestLayout();
    }

    private void i() {
        this.ae.e(2);
        for (int i = 0; i < this.ah.size(); i++) {
            SportDetailItem sportDetailItem = this.ah.get(i);
            ViewParent parent = sportDetailItem.getParent();
            if (parent instanceof ViewGroup) {
                ((ViewGroup) parent).removeAllViews();
            }
            this.ae.addView(sportDetailItem);
            if ((i & 1) == 0 && i != this.v.size() - 1) {
                this.ae.addView(dJL_());
            }
        }
    }

    private SportDetailItem d(SportDetailItem.b bVar) {
        if (bVar == null) {
            LogUtil.h("Track_SportDataInteractor", "setItemView data is null");
            return null;
        }
        SportDetailItem sportDetailItem = new SportDetailItem(b());
        sportDetailItem.c(b());
        sportDetailItem.setGroupSize(this.ap / 2, this.q);
        sportDetailItem.setItemView(bVar);
        sportDetailItem.setTextColor(this.ao);
        sportDetailItem.a();
        sportDetailItem.setGravity(17);
        return sportDetailItem;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void m() {
        int i = this.aj;
        if (i == 0) {
            this.f.setVisibility(0);
            this.ae.setVisibility(0);
            i();
            this.as.setVisibility(0);
            this.e.b(this.n);
            this.b.c(this.n);
            if (this.aq.isEmpty() || this.aq.get(0).d() == 0.0d) {
                this.aa.setVisibility(8);
                this.z.setVisibility(8);
                this.ac.setVisibility(8);
                return;
            } else {
                e(this.e.c(this.n));
                this.aa.setVisibility(0);
                this.z.setVisibility(0);
                this.ac.setVisibility(0);
                return;
            }
        }
        if (i == 287) {
            this.r.d(this.m, this.t, this.af);
        }
        h();
    }

    private void n() {
        if (koq.b(this.p) || d() == null || "--".equals(d().e())) {
            this.w = true;
        } else {
            this.w = false;
            o();
            k();
        }
        LogUtil.a("Track_SportDataInteractor", "mIsHideData ", Boolean.valueOf(this.w));
        if (this.w) {
            this.i.setVisibility(8);
            this.f.setVisibility(8);
            this.k.setVisibility(8);
            this.ae.setVisibility(8);
            return;
        }
        this.i.setVisibility(0);
        this.f.setVisibility(0);
        this.k.setVisibility(0);
        this.ae.setVisibility(0);
    }

    private SportDetailItem.b d() {
        HwSportDataStaticsInfo hwSportDataStaticsInfo;
        if (koq.b(this.ak)) {
            LogUtil.b("Track_SportDataInteractor", "mSportDataStatics is null");
            return null;
        }
        if (this.aj == 287 && this.ak.size() > 1) {
            hwSportDataStaticsInfo = this.ak.get(1);
        } else {
            hwSportDataStaticsInfo = this.ak.get(0);
        }
        if (hwSportDataStaticsInfo == null) {
            return null;
        }
        Map<String, Double> c = c(hwSportDataStaticsInfo.getItemDataTypeStringMap());
        for (Map.Entry<String, Double> entry : c.entrySet()) {
            if (entry.getKey() == null || entry.getValue() == null || entry.getValue().doubleValue() < 0.0d) {
                return null;
            }
        }
        if (kpw.a(this.aj) == 286) {
            a(c, HeartRateThresholdConfig.HEART_RATE_LIMIT, 0);
        }
        return red.a(c, hwSportDataStaticsInfo.getStaticsType(), this.aj);
    }

    private void a(Map<String, Double> map, int i, int i2) {
        HwSportTypeInfo d2 = this.an.d(i);
        if (d2 == null) {
            LogUtil.h("Track_SportDataInteractor", "addExtraStat sportTypeInfoById is null.");
            return;
        }
        List<HwSportDataStaticsInfo> sportDataStatics = d2.getSportDataStatics();
        if (koq.b(sportDataStatics, i2)) {
            LogUtil.h("Track_SportDataInteractor", "addExtraStat extraSportDataStatic is out of bounds.");
            return;
        }
        HwSportDataStaticsInfo hwSportDataStaticsInfo = sportDataStatics.get(i2);
        if (hwSportDataStaticsInfo == null) {
            return;
        }
        for (Map.Entry<String, Double> entry : c(hwSportDataStaticsInfo.getItemDataTypeStringMap()).entrySet()) {
            if (entry.getKey() != null && entry.getValue() != null) {
                String key = entry.getKey();
                if (map.containsKey(key)) {
                    map.put(key, Double.valueOf(map.get(key).doubleValue() + entry.getValue().doubleValue()));
                }
            }
        }
    }

    private SportDetailItem.b a(HwSportDataStaticsInfo hwSportDataStaticsInfo, int i) {
        Map<String, Double> c = c(hwSportDataStaticsInfo.getItemDataTypeStringMap());
        if (kpw.a(this.aj) == 286) {
            a(c, HeartRateThresholdConfig.HEART_RATE_LIMIT, i);
        }
        return red.a(c, hwSportDataStaticsInfo.getStaticsType(), this.aj);
    }

    private void k() {
        if (koq.b(this.ak)) {
            LogUtil.b("Track_SportDataInteractor", "mSportDataStatics is wrong");
            return;
        }
        for (int i = this.aj == 287 ? 2 : 1; i < this.ak.size(); i++) {
            HwSportDataStaticsInfo hwSportDataStaticsInfo = this.ak.get(i);
            if (hwSportDataStaticsInfo != null) {
                SportDetailItem.b a2 = a(hwSportDataStaticsInfo, i);
                SportDetailItem d2 = d(a2);
                SportDetailItem d3 = d(a2);
                if (d2 != null || d3 != null) {
                    this.v.add(d2);
                    this.ah.add(d3);
                }
            }
        }
    }

    private Map<String, Double> c(Map<String, String> map) {
        double e2;
        HashMap hashMap = new HashMap();
        if (map == null) {
            LogUtil.b("Track_SportDataInteractor", "itemDataTypeStringMap is null");
            return hashMap;
        }
        for (Map.Entry<String, String> entry : map.entrySet()) {
            if (entry.getKey() != null && entry.getValue() != null) {
                if (this.aj == 287) {
                    e2 = a(entry);
                } else {
                    e2 = e(entry);
                }
                if ("TOTAL_MAX_DISTANCE".equals(entry.getKey())) {
                    hashMap.put(entry.getKey(), Double.valueOf(this.m == 0 ? this.y : this.x));
                } else {
                    hashMap.put(entry.getKey(), Double.valueOf(e2));
                }
            }
        }
        return hashMap;
    }

    private double e(Map.Entry<String, String> entry) {
        double d2 = 0.0d;
        for (HiHealthData hiHealthData : this.p) {
            if (hiHealthData != null && hiHealthData.getInt("hihealth_type") != 289 && hiHealthData.getInt("hihealth_type") != 288) {
                d2 += hiHealthData.getDouble(entry.getValue());
                if (kpw.a(this.aj) != 286) {
                    break;
                }
            }
        }
        return d2;
    }

    private double a(Map.Entry<String, String> entry) {
        List<HiHealthData> list;
        if (this.m == 0) {
            list = this.t;
        } else {
            list = this.af;
        }
        double d2 = 0.0d;
        if (koq.b(list)) {
            LogUtil.h("Track_SportDataInteractor", "divingDataList is empty in collectDivingData");
            return 0.0d;
        }
        for (HiHealthData hiHealthData : list) {
            if (hiHealthData != null) {
                d2 += hiHealthData.getDouble(entry.getValue());
            }
        }
        return d2;
    }

    private void o() {
        SportDetailItem.b d2 = d();
        if (d2 == null) {
            LogUtil.b("Track_SportDataInteractor", "itemData is null");
            return;
        }
        this.c.setText(d2.e());
        this.h.setText(d2.e());
        this.f10283a.setText(d2.d());
        this.g.setText(d2.d());
        this.j.setText(d2.c());
        this.o.setText(d2.c());
    }

    private View dJL_() {
        View view = new View(b());
        view.setBackgroundColor(this.ao);
        view.setAlpha(0.3f);
        view.setLayoutParams(new ViewGroup.LayoutParams(this.ai.getDimensionPixelSize(R.dimen._2131364940_res_0x7f0a0c4c), this.ai.getDimensionPixelSize(R.dimen._2131363763_res_0x7f0a07b3)));
        int dimensionPixelSize = this.ai.getDimensionPixelSize(R.dimen._2131363629_res_0x7f0a072d);
        LinearLayout linearLayout = new LinearLayout(b());
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, this.ai.getDimensionPixelSize(R.dimen._2131363708_res_0x7f0a077c));
        linearLayout.setGravity(17);
        layoutParams.setMargins(0, dimensionPixelSize, 0, dimensionPixelSize);
        linearLayout.setLayoutParams(layoutParams);
        linearLayout.addView(view);
        return linearLayout;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(String str) {
        HashMap hashMap = new HashMap(2);
        hashMap.put("click", 1);
        hashMap.put("dataType", Integer.valueOf(this.n));
        ixx.d().d(BaseApplication.e(), str, hashMap, 0);
    }

    @Override // com.huawei.ui.main.stories.history.model.SportRecordDataAdapter
    public void updateUi(List<HiHealthData> list) {
        this.p = list;
        m();
    }

    static class e extends Handler {
        WeakReference<SportDataInteractor> d;

        private e(SportDataInteractor sportDataInteractor) {
            this.d = new WeakReference<>(sportDataInteractor);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            if (message == null) {
                return;
            }
            super.handleMessage(message);
            SportDataInteractor sportDataInteractor = this.d.get();
            if (sportDataInteractor == null) {
                return;
            }
            int i = message.what;
            if (i == 101) {
                sportDataInteractor.m();
            } else {
                if (i != 102) {
                    return;
                }
                sportDataInteractor.j(message.obj);
            }
        }
    }

    static class d implements IBaseResponseCallback {

        /* renamed from: a, reason: collision with root package name */
        private static final Object f10286a = new Object();
        private int[] b;
        int c;
        WeakReference<SportDataInteractor> d;
        private List<Object> e;
        private int f;

        d(SportDataInteractor sportDataInteractor, int i) {
            synchronized (f10286a) {
                this.f = i;
            }
            this.b = new int[i];
            for (int i2 = 0; i2 < this.f; i2++) {
                this.b[i2] = -1;
            }
            this.e = new ArrayList(this.f);
            WeakReference<SportDataInteractor> weakReference = new WeakReference<>(sportDataInteractor);
            this.d = weakReference;
            SportDataInteractor sportDataInteractor2 = weakReference.get();
            if (sportDataInteractor2 != null) {
                synchronized (sportDataInteractor2.s) {
                    this.c = sportDataInteractor2.ag;
                }
            }
        }

        @Override // com.huawei.hwbasemgr.IBaseResponseCallback
        /* renamed from: onResponse */
        public void d(int i, Object obj) {
            SportDataInteractor sportDataInteractor = this.d.get();
            if (sportDataInteractor == null) {
                return;
            }
            synchronized (f10286a) {
                this.f--;
                this.e.add(obj);
                if (this.f == 0) {
                    Message obtainMessage = sportDataInteractor.ab.obtainMessage();
                    obtainMessage.what = 102;
                    obtainMessage.obj = this.e;
                    synchronized (sportDataInteractor.s) {
                        if (c(sportDataInteractor.u)) {
                            sportDataInteractor.u = this.c;
                            sportDataInteractor.ab.sendMessage(obtainMessage);
                        }
                    }
                }
            }
        }

        private boolean c(int i) {
            if (Math.abs(this.c - i) > 100000 || this.c <= i) {
                return Math.abs(this.c - i) > 100000 && this.c < i;
            }
            return true;
        }
    }

    public void d(long j, long j2, int i) {
        LogUtil.a("Track_SportDataInteractor", "mRequestDataList , startTime is ", Long.valueOf(j), " ,endTime is ", Long.valueOf(j2), " ,timeUnit is ", Integer.valueOf(i));
        synchronized (this.s) {
            int i2 = this.ag + 1;
            this.ag = i2;
            if (i2 > 1000000) {
                this.ag = 0;
            }
            int i3 = this.aj;
            if (i3 == 0) {
                this.am = new d(this, 2);
            } else if (i3 == 287) {
                this.am = new d(this, 2);
            } else {
                this.am = new d(this, 1);
            }
        }
        b(j, j2, i);
    }

    private void b(long j, long j2, int i) {
        LogUtil.a("Track_SportDataInteractor", "getData mSportType:", Integer.valueOf(this.aj));
        RecordApi recordApi = (RecordApi) Services.a("CoursePlanService", RecordApi.class);
        if (recordApi == null) {
            LogUtil.h("Track_SportDataInteractor", "getData recordApi is null.");
            return;
        }
        kwy d2 = new kwy.a().a(j).e(j2).c(i).d();
        int i2 = this.aj;
        if (i2 == 10001) {
            recordApi.acquireSummaryFitnessRecord(d2, this.am);
            LogUtil.c("Track_SportDataInteractor", "acquire data form fitness ");
            return;
        }
        if (i2 == 0) {
            recordApi.acquireSummaryFitnessRecord(d2, this.am);
            gts.b(b()).e(j, j2 - 1000, i, 0, this.am);
        } else {
            if (i2 == 287) {
                long j3 = j2 - 1000;
                kor.a().c(j, j3, i, (IBaseResponseCallback) this.am, true);
                kor.a().c(j, j3, i, this.aj, this.am);
                return;
            }
            this.al.d(j, j2 - 1000, i2, i, this);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void j(Object obj) {
        LogUtil.a("Track_SportDataInteractor", "refresh data");
        this.p = null;
        if (this.aj == 287) {
            g();
            try {
                Iterator it = ((List) obj).iterator();
                while (it.hasNext()) {
                    c(it.next());
                }
            } catch (ClassCastException unused) {
                LogUtil.b("Track_SportDataInteractor", "refreshData diving");
            }
        }
        int i = this.aj;
        if (i == 10001) {
            b(obj);
        } else if (i == 0) {
            a(obj);
        } else {
            i(obj);
        }
        LogUtil.a("Track_SportDataInteractor", "update ui");
        this.ab.sendEmptyMessage(101);
    }

    private void g() {
        this.t = null;
        this.af = null;
        this.y = -1.0d;
        this.x = -1.0d;
    }

    private void c(Object obj) {
        if (obj instanceof Map) {
            Map map = (Map) obj;
            if (!map.isEmpty()) {
                for (Map.Entry entry : map.entrySet()) {
                    String str = (String) entry.getKey();
                    if (!TextUtils.isEmpty(str)) {
                        String str2 = str.split("#")[0];
                        if (!TextUtils.isEmpty(str2)) {
                            if (str2.equals(String.valueOf(291))) {
                                this.x = Math.max(this.x, ((Double) entry.getValue()).doubleValue());
                            } else {
                                this.y = Math.max(this.y, ((Double) entry.getValue()).doubleValue());
                            }
                        }
                    }
                }
                return;
            }
        }
        LogUtil.h("Track_SportDataInteractor", "object is not map or is empty in handleDivingDepthData");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void f() {
        final String c = this.e.c(this.n);
        for (rci rciVar : this.ad) {
            double d2 = 100.0d;
            if (Math.abs(this.aq.get(this.n).d()) >= 1.0E-10d) {
                d2 = 100.0d * (rciVar.e(c) / this.aq.get(this.n).d());
            }
            rciVar.a(d2);
        }
        Collections.sort(this.ad, new Comparator<rci>() { // from class: com.huawei.ui.main.stories.history.SportDataInteractor.3
            @Override // java.util.Comparator
            /* renamed from: e, reason: merged with bridge method [inline-methods] */
            public int compare(rci rciVar2, rci rciVar3) {
                return (int) (rciVar3.e(c) - rciVar2.e(c));
            }
        });
    }

    private void e(int i, double d2, double d3, double d4) {
        if (d3 == 0.0d) {
            LogUtil.a("Track_SportDataInteractor", "sportType = ", Integer.valueOf(i));
            return;
        }
        int d5 = rdu.d(i);
        for (rci rciVar : this.ad) {
            if (rciVar.d() == d5) {
                rciVar.e(rciVar.c() + d2);
                rciVar.d(rciVar.e() + d3);
                rciVar.b(rciVar.a() + d4);
                return;
            }
        }
        this.ad.add(new rci(d5, d2, d3, d4));
    }

    private void f(Object obj) {
        LogUtil.a("Track_SportDataInteractor", "handleRankList");
        if (!(obj instanceof HiHealthData)) {
            this.ab.sendEmptyMessage(101);
            LogUtil.b("Track_SportDataInteractor", "handleRankList wrong data");
            return;
        }
        HiHealthData hiHealthData = (HiHealthData) obj;
        int i = hiHealthData.getInt("hihealth_type");
        if (i != 30001) {
            if (hkc.b(i)) {
                LogUtil.h("Track_SportDataInteractor", "Old Sport Type with invalid data");
                return;
            }
            e(i, hiHealthData.getDouble("stat4"), hiHealthData.getDouble("stat5") - hiHealthData.getDouble("stat6"), hiHealthData.getDouble("stat3"));
            return;
        }
        try {
            String[] d2 = gts.b(b()).d();
            char c = 0;
            int i2 = 0;
            while (i2 < 8) {
                int parseInt = Integer.parseInt(d2[i2].substring(6, 9));
                Object[] objArr = new Object[2];
                objArr[c] = "handleRankList sportType = ";
                objArr[1] = Integer.valueOf(parseInt);
                LogUtil.a("Track_SportDataInteractor", objArr);
                if (parseInt != 287) {
                    e(parseInt, hiHealthData.getDouble("Track_" + d2[i2].substring(6, 9) + 4), hiHealthData.getDouble("Track_" + d2[i2].substring(6, 9) + 5) - hiHealthData.getDouble("Track_" + d2[i2].substring(6, 9) + 6), hiHealthData.getDouble("Track_" + d2[i2].substring(6, 9) + 3));
                }
                i2++;
                c = 0;
            }
        } catch (NumberFormatException unused) {
            LogUtil.b("Track_SportDataInteractor", "handleTrackRankList NumberFormatException.");
        }
    }

    private List<Double> e(Object obj) {
        Iterator it = ((List) obj).iterator();
        double d2 = 0.0d;
        double d3 = 0.0d;
        double d4 = 0.0d;
        while (it.hasNext()) {
            Object next = it.next();
            if (koq.e(next, HiHealthData.class)) {
                List<HiHealthData> list = (List) next;
                LogUtil.a("Track_SportDataInteractor", "collectTrackSum dataList.size:", Integer.valueOf(list.size()));
                double d5 = 0.0d;
                double d6 = 0.0d;
                for (HiHealthData hiHealthData : list) {
                    Iterator it2 = it;
                    if (hiHealthData.getInt("hihealth_type") == 289 || hiHealthData.getInt("hihealth_type") == 288) {
                        d6 += hiHealthData.getDouble("stat5");
                        d5 += hiHealthData.getDouble("stat4");
                        LogUtil.a("Track_SportDataInteractor", "collectTrackSum divingDuration:", Double.valueOf(d5));
                    }
                    d2 += hiHealthData.getDouble("Track_Duration_Sum") + hiHealthData.getDouble("Track_5124");
                    d3 += (hiHealthData.getDouble("Track_Count_Sum") + hiHealthData.getDouble("Track_5125")) - hiHealthData.getDouble("Track_Abnormal_Count_Sum");
                    d4 += hiHealthData.getDouble("Track_Calorie_Sum") + hiHealthData.getDouble("Track_5123");
                    LogUtil.a("Track_SportDataInteractor", "collectTrackSum trackDuration:", Double.valueOf(d2));
                    it = it2;
                }
                d2 -= d5;
                d3 -= d6;
            }
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add(Double.valueOf(Math.max(d2, 0.0d)));
        arrayList.add(Double.valueOf(Math.max(d3, 0.0d)));
        arrayList.add(Double.valueOf(d4));
        return arrayList;
    }

    private List<Double> d(Object obj) {
        double d2 = 0.0d;
        double d3 = 0.0d;
        double d4 = 0.0d;
        for (Object obj2 : (List) obj) {
            if (koq.e(obj2, FitnessTrackRecord.class)) {
                List<FitnessTrackRecord> list = (List) obj2;
                LogUtil.a("Track_SportDataInteractor", "fitnessTrackRecords size:", Integer.valueOf(list.size()));
                for (FitnessTrackRecord fitnessTrackRecord : list) {
                    d2 += fitnessTrackRecord.acquireSumExerciseTime();
                    d3 += fitnessTrackRecord.acquireSumExerciseTimes();
                    d4 += fitnessTrackRecord.acquireSumCalorie();
                }
            }
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add(Double.valueOf(d2));
        arrayList.add(Double.valueOf(d3));
        arrayList.add(Double.valueOf(d4));
        return arrayList;
    }

    private void b(List<Double> list, List<Double> list2) {
        if (koq.b(list, 2) || koq.b(list2, 2)) {
            LogUtil.a("Track_SportDataInteractor", "trackSum or fitnessSum CALORIES_INDEX is out of bounds");
            return;
        }
        String string = this.ai.getString(R$string.IDS_hwh_input_history_data_duration);
        double doubleValue = list.get(0).doubleValue();
        rdw rdwVar = new rdw(string, list2.get(0).doubleValue() + doubleValue, this.n == 0, R.color._2131299282_res_0x7f090bd2);
        rdw rdwVar2 = new rdw(this.ai.getString(R$string.IDS_hwh_motiontrack_sport_data_times), list.get(1).doubleValue() + list2.get(1).doubleValue(), this.n == 1, R.color._2131299292_res_0x7f090bdc);
        rdw rdwVar3 = new rdw(this.ai.getString(R$string.IDS_track_total_calories), list.get(2).doubleValue() + list2.get(2).doubleValue(), this.n == 2, R.color._2131299271_res_0x7f090bc7);
        this.aq.add(rdwVar);
        this.aq.add(rdwVar2);
        this.aq.add(rdwVar3);
    }

    private void d(Object obj, List<Double> list) {
        for (Object obj2 : (List) obj) {
            if (obj2 != null) {
                if (koq.e(obj2, FitnessTrackRecord.class)) {
                    e(10001, list.get(0).doubleValue(), list.get(1).doubleValue(), list.get(2).doubleValue());
                } else if (koq.e(obj2, HiHealthData.class)) {
                    for (HiHealthData hiHealthData : (List) obj2) {
                        if (hiHealthData.getInt("hihealth_type") != 289 && hiHealthData.getInt("hihealth_type") != 288) {
                            f(hiHealthData);
                        }
                    }
                }
            }
        }
    }

    private void e() {
        if (koq.b(this.aq, 2)) {
            LogUtil.a("Track_SportDataInteractor", "mSumData CALORIES_INDEX is out of bounds");
            return;
        }
        this.g.setText(this.aq.get(0).b());
        this.h.setText(this.e.e(this.aq.get(0).d()));
        this.o.setText(this.e.a(this.aq.get(0).d()));
        SportDetailItem.b bVar = new SportDetailItem.b(null, this.aq.get(1).b(), UnitUtil.e(this.aq.get(1).d(), 1, 0), this.ai.getQuantityString(R.plurals._2130903213_res_0x7f0300ad, 0, ""));
        SportDetailItem.b bVar2 = new SportDetailItem.b(null, this.aq.get(2).b(), this.e.b(this.aq.get(2).d()), this.e.c(this.aq.get(2).d()));
        this.ah.add(d(bVar));
        this.ah.add(d(bVar2));
    }

    private void a(Object obj) {
        LogUtil.a("Track_SportDataInteractor", "handleAllSportData");
        a();
        List list = (List) obj;
        Iterator it = list.iterator();
        int i = 0;
        while (it.hasNext() && it.next() == null) {
            i++;
        }
        if (i == list.size()) {
            return;
        }
        List<Double> e2 = e(obj);
        List<Double> d2 = d(obj);
        b(e2, d2);
        d(obj, d2);
        f();
        e();
    }

    private void i(Object obj) {
        if (!koq.e(obj, Object.class)) {
            this.ab.sendEmptyMessage(101);
            LogUtil.b("Track_SportDataInteractor", "wrong data ");
            return;
        }
        List<Object> list = (List) obj;
        if (this.aj == 287) {
            c(list);
        }
        for (Object obj2 : list) {
            if (koq.e(obj2, HiHealthData.class)) {
                List<HiHealthData> list2 = (List) obj2;
                if (list2.size() > 0) {
                    this.p = list2;
                } else {
                    LogUtil.h("Track_SportDataInteractor", "data size =", Integer.valueOf(list2.size()));
                }
            } else {
                LogUtil.h("Track_SportDataInteractor", "class type is wrong");
            }
        }
    }

    private void c(List<Object> list) {
        for (Object obj : list) {
            if (koq.e(obj, HiHealthData.class)) {
                List list2 = (List) obj;
                if (!koq.b(list2)) {
                    Iterator it = list2.iterator();
                    while (it.hasNext()) {
                        e((HiHealthData) it.next());
                    }
                }
            }
        }
    }

    private void e(HiHealthData hiHealthData) {
        if (hiHealthData == null) {
            LogUtil.h("Track_SportDataInteractor", "hiHealthData is null in handleSingleData");
            return;
        }
        if (hiHealthData.getInt("hihealth_type") == 287) {
            if (this.t == null) {
                this.t = new ArrayList(10);
            }
            this.t.add(hiHealthData);
        } else {
            if (hiHealthData.getInt("hihealth_type") == 291) {
                if (this.af == null) {
                    this.af = new ArrayList(10);
                }
                this.af.add(hiHealthData);
                return;
            }
            LogUtil.h("Track_SportDataInteractor", "not free or scuba diving");
        }
    }

    private void b(Object obj) {
        if (obj == null || !koq.e(obj, Object.class)) {
            LogUtil.h("Track_SportDataInteractor", "objData error");
            return;
        }
        List list = (List) obj;
        if (!koq.e(list.get(0), FitnessTrackRecord.class)) {
            LogUtil.h("Track_SportDataInteractor", "class type is wrong");
            return;
        }
        List list2 = (List) list.get(0);
        if (list2.size() == 0) {
            LogUtil.a("Track_SportDataInteractor", "fitnessTrackRecords.size() == 0");
            return;
        }
        LogUtil.a("Track_SportDataInteractor", "fitnessTrackRecords:", Integer.valueOf(list2.size()));
        FitnessTrackRecord fitnessTrackRecord = (FitnessTrackRecord) list2.get(0);
        if (fitnessTrackRecord.acquireSumExerciseTime() <= 0) {
            LogUtil.b("Track_SportDataInteractor", "fitnessTrackRecord <= 0");
            return;
        }
        HiHealthData hiHealthData = new HiHealthData();
        hiHealthData.putFloat("Track_Fitness_Duration_Sum", fitnessTrackRecord.acquireSumExerciseTime());
        hiHealthData.putInt("Track_Fitness_Count_Sum", fitnessTrackRecord.acquireSumExerciseTimes());
        hiHealthData.putFloat("Track_Fitness_Calorie_Sum", fitnessTrackRecord.acquireSumCalorie());
        List<HiHealthData> list3 = this.p;
        if (list3 != null) {
            list3.clear();
        } else {
            this.p = new ArrayList();
        }
        this.p.add(hiHealthData);
    }

    private Context b() {
        WeakReference<Context> weakReference = this.l;
        if (weakReference == null) {
            return BaseApplication.e();
        }
        return weakReference.get();
    }
}
