package com.huawei.health.suggestion.ui.plan.fragment;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.huawei.basefitnessadvice.callback.DataCallback;
import com.huawei.basefitnessadvice.callback.UiCallback;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.R;
import com.huawei.health.suggestion.ui.plan.adapter.DietRecordAdapter;
import com.huawei.health.suggestion.ui.plan.fragment.DietRecordFragment;
import com.huawei.health.suggestion.ui.plan.viewholder.AiPlanWeekDetailViewHolder;
import com.huawei.health.suggestion.util.JumpUtil;
import com.huawei.health.weight.bean.WeightTargetDifferences;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwbasemgr.ResponseCallback;
import com.huawei.hwcommonmodel.utils.DateFormatUtil;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.ui.commonui.base.BaseFragment;
import com.huawei.ui.commonui.cardview.HealthCardView;
import com.huawei.ui.commonui.dialog.CustomViewDialog;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import defpackage.dpe;
import defpackage.fiw;
import defpackage.fje;
import defpackage.ful;
import defpackage.fuq;
import defpackage.grz;
import defpackage.koq;
import defpackage.nsn;
import defpackage.qts;
import defpackage.quh;
import defpackage.quk;
import defpackage.qul;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: classes4.dex */
public class DietRecordFragment extends BaseFragment implements View.OnClickListener {

    /* renamed from: a, reason: collision with root package name */
    private ImageView f3264a;
    private ImageView b;
    private ImageView c;
    private AiPlanWeekDetailViewHolder f;
    private LinearLayout i;
    private ImageView j;
    private DietRecordAdapter k;
    private Context l;
    private HealthCardView m;
    private int q;
    private boolean r;
    private float t;
    private boolean w;
    private boolean x;
    private RecyclerView y;
    private long z;
    private boolean v = true;
    private int g = 0;
    private int e = 0;
    private int d = 0;
    private TreeMap<Integer, fuq> n = new TreeMap<>();
    private HashMap<Integer, Float> ac = new HashMap<>();
    private List<fuq> ab = new ArrayList();
    private Set<Integer> h = new HashSet();
    private Set<String> p = new HashSet();
    private Set<String> o = new HashSet();
    private int u = 0;
    private Handler s = new Handler(Looper.getMainLooper());

    public static DietRecordFragment c() {
        LogUtil.a("Suggestion_DietRecordFragment", "newInstance()");
        Bundle bundle = new Bundle();
        DietRecordFragment dietRecordFragment = new DietRecordFragment();
        dietRecordFragment.setArguments(bundle);
        return dietRecordFragment;
    }

    @Override // com.huawei.ui.commonui.base.BaseFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.l = getContext();
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.fragment_diet_record, viewGroup, false);
        aHH_(inflate);
        return inflate;
    }

    private void aHH_(View view) {
        LogUtil.a("Suggestion_DietRecordFragment", "initView()");
        view.measure(0, 0);
        int measuredHeight = view.getMeasuredHeight();
        this.q = measuredHeight;
        LogUtil.a("Suggestion_DietRecordFragment", "initView() mInitLayoutHeight: ", Integer.valueOf(measuredHeight));
        this.i = (LinearLayout) view.findViewById(R.id.diet_record_container_layout);
        this.m = (HealthCardView) view.findViewById(R.id.diet_record_card_view);
        ((LinearLayout) view.findViewById(R.id.diet_record_add_breakfast)).setOnClickListener(this);
        ((LinearLayout) view.findViewById(R.id.diet_record_add_lunch)).setOnClickListener(this);
        ((LinearLayout) view.findViewById(R.id.diet_record_add_dinner)).setOnClickListener(this);
        ((LinearLayout) view.findViewById(R.id.diet_record_add_meal)).setOnClickListener(this);
        this.b = (ImageView) view.findViewById(R.id.diet_record_add_breakfast_icon);
        this.f3264a = (ImageView) view.findViewById(R.id.diet_record_add_lunch_icon);
        this.c = (ImageView) view.findViewById(R.id.diet_record_add_dinner_icon);
        this.j = (ImageView) view.findViewById(R.id.diet_record_add_meal_icon);
        this.y = (RecyclerView) view.findViewById(R.id.diet_record_recycler_view);
    }

    public void a() {
        LogUtil.a("Suggestion_DietRecordFragment", "resetLayout()");
        this.s.post(new Runnable() { // from class: fvy
            @Override // java.lang.Runnable
            public final void run() {
                DietRecordFragment.this.e();
            }
        });
    }

    public /* synthetic */ void e() {
        this.m.setVisibility(0);
        this.b.setImageResource(R.drawable._2131428035_res_0x7f0b02c3);
        this.f3264a.setImageResource(R.drawable._2131428040_res_0x7f0b02c8);
        this.c.setImageResource(R.drawable._2131428038_res_0x7f0b02c6);
        this.j.setImageResource(R.drawable._2131428033_res_0x7f0b02c1);
        h();
        this.y.setVisibility(8);
        this.f.e(1, this.q);
    }

    private void h() {
        this.b.setAlpha(1.0f);
        this.f3264a.setAlpha(1.0f);
        this.c.setAlpha(1.0f);
        this.j.setAlpha(1.0f);
    }

    private void j() {
        this.s.post(new Runnable() { // from class: fwa
            @Override // java.lang.Runnable
            public final void run() {
                DietRecordFragment.this.d();
            }
        });
    }

    public /* synthetic */ void d() {
        this.b.setAlpha(0.6f);
        this.f3264a.setAlpha(0.6f);
        this.c.setAlpha(0.6f);
        this.j.setAlpha(0.6f);
    }

    @Override // androidx.fragment.app.Fragment
    public void onStart() {
        LogUtil.a("Suggestion_DietRecordFragment", "onStart()");
        super.onStart();
    }

    @Override // com.huawei.ui.commonui.base.BaseFragment, androidx.fragment.app.Fragment
    public void onResume() {
        LogUtil.a("Suggestion_DietRecordFragment", "onResume()");
        super.onResume();
        if (this.w) {
            this.w = false;
            e(this.z);
        }
    }

    public void d(AiPlanWeekDetailViewHolder aiPlanWeekDetailViewHolder) {
        this.f = aiPlanWeekDetailViewHolder;
    }

    public void d(boolean z, long j) {
        LogUtil.a("Suggestion_DietRecordFragment", "setInitData() isFutureTime: ", Boolean.valueOf(z), ", timeStamp: ", Long.valueOf(j), ", mTimeStamp: ", Long.valueOf(this.z));
        this.x = z;
        if (z) {
            a();
            j();
            return;
        }
        long j2 = this.z;
        if (j2 != 0 && j2 != j) {
            a();
        }
        this.z = j;
        e(j);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (this.x) {
            LogUtil.h("Suggestion_DietRecordFragment", "onClick() mIsFutureTime = true, return");
            ViewClickInstrumentation.clickOnView(view);
            return;
        }
        if (view == null) {
            LogUtil.b("Suggestion_DietRecordFragment", "onClick() view is null");
            ViewClickInstrumentation.clickOnView(view);
            return;
        }
        if (nsn.a(500)) {
            LogUtil.h("Suggestion_DietRecordFragment", "onClick() isFastClick()");
            ViewClickInstrumentation.clickOnView(view);
            return;
        }
        if (view.getId() == R.id.diet_record_add_breakfast) {
            a(10, 0);
        } else if (view.getId() == R.id.diet_record_add_lunch) {
            a(20, 0);
        } else if (view.getId() == R.id.diet_record_add_dinner) {
            a(30, 0);
        } else if (view.getId() == R.id.diet_record_add_meal) {
            l();
        } else {
            LogUtil.h("Suggestion_DietRecordFragment", "error view id: ", view);
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    private void l() {
        View inflate = ((LayoutInflater) this.l.getSystemService("layout_inflater")).inflate(R.layout.dialog_diet_record_add_meal, (ViewGroup) null);
        CustomViewDialog.Builder builder = new CustomViewDialog.Builder(this.l);
        if (inflate == null) {
            LogUtil.h("Suggestion_DietRecordFragment", "showAddMealDialog() layout error");
            return;
        }
        builder.czh_(inflate, 0, 0).cze_(R.string._2130837555_res_0x7f020033, new View.OnClickListener() { // from class: com.huawei.health.suggestion.ui.plan.fragment.DietRecordFragment.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        CustomViewDialog e = builder.e();
        aHG_(inflate, e);
        e.show();
    }

    private void aHG_(View view, final CustomViewDialog customViewDialog) {
        HealthTextView healthTextView = (HealthTextView) view.findViewById(R.id.dialog_add_meal_morning);
        HealthTextView healthTextView2 = (HealthTextView) view.findViewById(R.id.dialog_add_meal_afternoon);
        HealthTextView healthTextView3 = (HealthTextView) view.findViewById(R.id.dialog_add_meal_evening);
        healthTextView.setOnClickListener(new View.OnClickListener() { // from class: fwb
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                DietRecordFragment.this.aHI_(customViewDialog, view2);
            }
        });
        healthTextView2.setOnClickListener(new View.OnClickListener() { // from class: fwd
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                DietRecordFragment.this.aHJ_(customViewDialog, view2);
            }
        });
        healthTextView3.setOnClickListener(new View.OnClickListener() { // from class: fwe
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                DietRecordFragment.this.aHK_(customViewDialog, view2);
            }
        });
    }

    public /* synthetic */ void aHI_(CustomViewDialog customViewDialog, View view) {
        a(11, this.g);
        customViewDialog.dismiss();
        ViewClickInstrumentation.clickOnView(view);
    }

    public /* synthetic */ void aHJ_(CustomViewDialog customViewDialog, View view) {
        a(21, this.e);
        customViewDialog.dismiss();
        ViewClickInstrumentation.clickOnView(view);
    }

    public /* synthetic */ void aHK_(CustomViewDialog customViewDialog, View view) {
        a(31, this.d);
        customViewDialog.dismiss();
        ViewClickInstrumentation.clickOnView(view);
    }

    private void a(int i, int i2) {
        LogUtil.a("Suggestion_DietRecordFragment", "jumpToH5RecordDietPage() mealType: ", Integer.valueOf(i), ", index: ", Integer.valueOf(i2), ", mTimeStamp: ", Long.valueOf(this.z));
        this.w = true;
        JumpUtil.c(this.z, i, i2);
    }

    private void e(final long j) {
        LogUtil.a("Suggestion_DietRecordFragment", "requestDietDataInThread() timeStamp: ", Long.valueOf(j), ", currentTime: ", Long.valueOf(System.currentTimeMillis()));
        if (j == 0) {
            LogUtil.h("Suggestion_DietRecordFragment", "requestDietDataInThread() timeStamp == 0L,return");
        } else {
            if (this.v) {
                this.v = false;
                m();
                ThreadPoolManager.d().execute(new Runnable() { // from class: fvz
                    @Override // java.lang.Runnable
                    public final void run() {
                        DietRecordFragment.this.c(j);
                    }
                });
                return;
            }
            LogUtil.a("Suggestion_DietRecordFragment", "requestDietDataInThread() mIsRequestDataEnd = false");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: b, reason: merged with bridge method [inline-methods] */
    public void c(long j) {
        int b = DateFormatUtil.b(j);
        ReleaseLogUtil.e("Suggestion_DietRecordFragment", "requestDietData dayFormat ", Integer.valueOf(b));
        grz.b(b, b, new c(this));
    }

    static class c implements ResponseCallback<List<quh>> {
        private final WeakReference<DietRecordFragment> c;

        c(DietRecordFragment dietRecordFragment) {
            this.c = new WeakReference<>(dietRecordFragment);
        }

        @Override // com.huawei.hwbasemgr.ResponseCallback
        /* renamed from: e, reason: merged with bridge method [inline-methods] */
        public void onResponse(int i, List<quh> list) {
            qts d;
            LogUtil.a("Suggestion_DietRecordFragment", "InnerResponseCallback errorCode ", Integer.valueOf(i), " list ", list);
            DietRecordFragment dietRecordFragment = this.c.get();
            if (dietRecordFragment == null) {
                ReleaseLogUtil.d("Suggestion_DietRecordFragment", "InnerResponseCallback fragment is null");
                return;
            }
            if (koq.b(list)) {
                dietRecordFragment.v = true;
                dietRecordFragment.a();
                return;
            }
            for (quh quhVar : list) {
                if (quhVar != null && (d = quhVar.d()) != null) {
                    dietRecordFragment.t = d.c();
                    if (koq.b(quhVar.a())) {
                        dietRecordFragment.v = true;
                        dietRecordFragment.a();
                        return;
                    }
                    dietRecordFragment.c(quhVar);
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(quh quhVar) {
        HashSet hashSet = new HashSet();
        List<qul> a2 = quhVar.a();
        ArrayList arrayList = new ArrayList();
        for (qul qulVar : a2) {
            hashSet.add(Integer.valueOf(qulVar.h()));
            for (quk qukVar : qulVar.c()) {
                ful fulVar = new ful();
                fulVar.c(qulVar.h());
                fulVar.d(qukVar.a());
                fulVar.a(qukVar.c());
                fulVar.c(qukVar.b());
                fulVar.b(qukVar.n());
                fulVar.e(qukVar.g());
                fulVar.a(qukVar.i());
                if (qukVar.j() == 0) {
                    fulVar.c(true);
                    this.o.add(qukVar.a());
                } else {
                    fulVar.c(false);
                    this.p.add(qukVar.a());
                }
                arrayList.add(fulVar);
            }
        }
        LogUtil.a("Suggestion_DietRecordFragment", "dietRecordItemDataList size: ", Integer.valueOf(arrayList.size()));
        a(hashSet);
        LogUtil.a("Suggestion_DietRecordFragment", "requestDietData() mFoodIdSet: ", this.p, ", mCustomFoodIdSet: ", this.o);
        if (koq.b(this.p) && koq.b(this.o)) {
            LogUtil.h("Suggestion_DietRecordFragment", "mFoodIdSet and mCustomFoodIdSet is empty");
            this.v = true;
            return;
        }
        if (koq.c(this.p) && koq.c(this.o)) {
            this.r = true;
            a(this.p, arrayList);
        } else if (koq.c(this.p)) {
            a(this.p, arrayList);
        } else if (koq.c(this.o)) {
            e(this.o, arrayList);
        } else {
            LogUtil.h("Suggestion_DietRecordFragment", "handleDietRecordData() wrong case");
            this.v = true;
        }
    }

    private void a(final Set<Integer> set) {
        LogUtil.a("Suggestion_DietRecordFragment", "displayColorIcon() iconSet: ", set);
        this.s.post(new Runnable() { // from class: fwc
            @Override // java.lang.Runnable
            public final void run() {
                DietRecordFragment.this.d(set);
            }
        });
    }

    public /* synthetic */ void d(Set set) {
        Iterator it = set.iterator();
        while (it.hasNext()) {
            Integer num = (Integer) it.next();
            if (num.intValue() == 10) {
                this.b.setImageResource(R.drawable._2131428036_res_0x7f0b02c4);
            } else if (num.intValue() == 20) {
                this.f3264a.setImageResource(R.drawable._2131428041_res_0x7f0b02c9);
            } else if (num.intValue() == 30) {
                this.c.setImageResource(R.drawable._2131428039_res_0x7f0b02c7);
            } else if (num.intValue() == 11 || num.intValue() == 21 || num.intValue() == 31) {
                this.j.setImageResource(R.drawable._2131428034_res_0x7f0b02c2);
            } else {
                LogUtil.h("Suggestion_DietRecordFragment", "wrong mealType: ", num);
            }
            h();
            this.m.setVisibility(0);
            this.f.e(1, this.q);
        }
    }

    private void a(Set<String> set, final List<ful> list) {
        fiw.d().batchGetFood(set, new DataCallback() { // from class: com.huawei.health.suggestion.ui.plan.fragment.DietRecordFragment.2
            @Override // com.huawei.basefitnessadvice.callback.DataCallback
            public void onSuccess(JSONObject jSONObject) {
                LogUtil.c("Suggestion_DietRecordFragment", "requestFoodIconData() batchGetFood success data: ", jSONObject);
                if (jSONObject != null && jSONObject.has("foodList")) {
                    DietRecordFragment.this.d(jSONObject, (List<ful>) list);
                }
                DietRecordFragment.this.v = true;
            }

            @Override // com.huawei.basefitnessadvice.callback.DataCallback
            public void onFailure(int i, String str) {
                LogUtil.b("Suggestion_DietRecordFragment", "requestFoodIconData() batchGetFood errorCode: ", Integer.valueOf(i), ", errorInfo: ", str);
                DietRecordFragment.this.v = true;
            }
        });
    }

    private void e(Set<String> set, final List<ful> list) {
        int size = set.size();
        if (size > 100) {
            size = 100;
        }
        fje.c().getCustomFoods(1, size, set, new UiCallback<JSONObject>() { // from class: com.huawei.health.suggestion.ui.plan.fragment.DietRecordFragment.3
            @Override // com.huawei.basefitnessadvice.callback.UiCallback
            /* renamed from: c, reason: merged with bridge method [inline-methods] */
            public void onSuccess(JSONObject jSONObject) {
                LogUtil.c("Suggestion_DietRecordFragment", "getCustomFoods() data: ", jSONObject);
                if (jSONObject != null && jSONObject.has("customFoodList")) {
                    DietRecordFragment.this.c(jSONObject, list);
                }
                DietRecordFragment.this.v = true;
            }

            @Override // com.huawei.basefitnessadvice.callback.UiCallback
            public void onFailure(int i, String str) {
                LogUtil.h("Suggestion_DietRecordFragment", "getCustomFoods() errorCode: ", Integer.valueOf(i), ", errorInfo: ", str);
                DietRecordFragment.this.v = true;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(JSONObject jSONObject, List<ful> list) {
        JSONArray optJSONArray = jSONObject.optJSONArray("foodList");
        if (optJSONArray == null || optJSONArray.length() == 0) {
            LogUtil.h("Suggestion_DietRecordFragment", "handleFoodIconData() foodJsonArray is empty");
            return;
        }
        HashMap hashMap = new HashMap();
        for (int i = 0; i < optJSONArray.length(); i++) {
            JSONObject optJSONObject = optJSONArray.optJSONObject(i);
            if (optJSONObject != null) {
                hashMap.put(optJSONObject.optString("foodId"), optJSONObject.optString("imageUrl"));
            }
        }
        for (ful fulVar : list) {
            if (fulVar != null && !fulVar.f()) {
                fulVar.e((String) hashMap.get(fulVar.d()));
            }
        }
        if (this.r) {
            e(this.o, list);
        } else {
            a(list);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(JSONObject jSONObject, List<ful> list) {
        JSONArray optJSONArray = jSONObject.optJSONArray("customFoodList");
        if (optJSONArray == null || optJSONArray.length() == 0) {
            LogUtil.h("Suggestion_DietRecordFragment", "handleCustomFoodIconData() foodJsonArray is empty");
            return;
        }
        HashMap hashMap = new HashMap();
        for (int i = 0; i < optJSONArray.length(); i++) {
            JSONObject optJSONObject = optJSONArray.optJSONObject(i);
            if (optJSONObject != null) {
                String optString = optJSONObject.optString("foodId");
                JSONObject optJSONObject2 = optJSONObject.optJSONObject("foodInfo");
                if (optJSONObject2 != null) {
                    hashMap.put(optString, optJSONObject2.optString("pictureUrl"));
                }
            }
        }
        for (ful fulVar : list) {
            if (fulVar != null && fulVar.f()) {
                String str = (String) hashMap.get(fulVar.d());
                if (!TextUtils.isEmpty(str) && !str.startsWith("http")) {
                    ReleaseLogUtil.d("Suggestion_DietRecordFragment", "wrong foodIconUrl, foodId: ", fulVar.d(), ", url: ", str);
                }
                fulVar.e(str);
            }
        }
        a(list);
    }

    private void a(List<ful> list) {
        fuq fuqVar;
        LogUtil.a("Suggestion_DietRecordFragment", "mergeData() dietRecordItemDataList: ", list);
        for (ful fulVar : list) {
            int h = fulVar.h();
            LogUtil.a("Suggestion_DietRecordFragment", "dataTreeMap mealType: ", Integer.valueOf(h), ", data: ", this.n.get(Integer.valueOf(h)));
            if (this.n.get(Integer.valueOf(h)) != null) {
                fuqVar = this.n.get(Integer.valueOf(h));
            } else {
                fuqVar = new fuq();
            }
            fuqVar.c(h);
            if (h == 11 || h == 21 || h == 31) {
                this.h.add(Integer.valueOf(h));
            }
            d(h, fulVar.b());
            if (fuqVar.b() == null) {
                ArrayList arrayList = new ArrayList();
                arrayList.add(fulVar);
                fuqVar.a(arrayList);
            } else {
                fuqVar.b().add(fulVar);
            }
            this.n.put(Integer.valueOf(h), fuqVar);
        }
        i();
    }

    private void d(int i, float f) {
        if (this.ac.get(Integer.valueOf(i)) != null) {
            f += this.ac.get(Integer.valueOf(i)).floatValue();
        }
        LogUtil.a("Suggestion_DietRecordFragment", "calculateTotalCalories() mealType: ", Integer.valueOf(i), ", tempCalories: ", Float.valueOf(f));
        this.ac.put(Integer.valueOf(i), Float.valueOf(f));
    }

    private void i() {
        grz.b((ResponseCallback<WeightTargetDifferences>) new ResponseCallback() { // from class: fwh
            @Override // com.huawei.hwbasemgr.ResponseCallback
            public final void onResponse(int i, Object obj) {
                DietRecordFragment.this.d(i, (WeightTargetDifferences) obj);
            }
        });
    }

    public /* synthetic */ void d(int i, WeightTargetDifferences weightTargetDifferences) {
        if (weightTargetDifferences == null) {
            LogUtil.h("Suggestion_DietRecordFragment", "weightTargetDifferences is null");
            g();
            return;
        }
        WeightTargetDifferences.WeightTargetType d = weightTargetDifferences.d();
        if (d == null) {
            LogUtil.h("Suggestion_DietRecordFragment", "targetType is null");
            g();
            return;
        }
        int i2 = AnonymousClass1.e[d.ordinal()];
        if (i2 == 1) {
            this.u = 0;
            o();
        } else if (i2 == 2) {
            this.u = 2;
            o();
        } else if (i2 == 3) {
            this.u = 1;
            o();
        } else {
            LogUtil.h("Suggestion_DietRecordFragment", "targetType error case");
            g();
        }
    }

    /* renamed from: com.huawei.health.suggestion.ui.plan.fragment.DietRecordFragment$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] e;

        static {
            int[] iArr = new int[WeightTargetDifferences.WeightTargetType.values().length];
            e = iArr;
            try {
                iArr[WeightTargetDifferences.WeightTargetType.WEIGHT_KEE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                e[WeightTargetDifferences.WeightTargetType.WEIGHT_GAIN.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                e[WeightTargetDifferences.WeightTargetType.WEIGHT_LOSS.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    private void g() {
        grz.d((ResponseCallback<HashMap<String, Double>>) new ResponseCallback() { // from class: fvw
            @Override // com.huawei.hwbasemgr.ResponseCallback
            public final void onResponse(int i, Object obj) {
                DietRecordFragment.this.b(i, (HashMap) obj);
            }
        });
    }

    public /* synthetic */ void b(int i, HashMap hashMap) {
        if (hashMap == null) {
            LogUtil.h("Suggestion_DietRecordFragment", "judgeUserPlanType() map == null");
            o();
            return;
        }
        Double d = (Double) hashMap.get("startWeight");
        double doubleValue = d != null ? d.doubleValue() : 0.0d;
        Double d2 = (Double) hashMap.get("targetWeight");
        double doubleValue2 = d2 != null ? d2.doubleValue() : 0.0d;
        if (doubleValue <= 0.0d || doubleValue2 <= 0.0d) {
            this.u = 0;
        } else if (doubleValue2 < doubleValue) {
            this.u = 1;
        } else if (doubleValue2 > doubleValue) {
            this.u = 2;
        } else {
            this.u = 0;
        }
        o();
    }

    private void o() {
        n();
        f();
    }

    private void n() {
        String str;
        String accountInfo = LoginInit.getInstance(BaseApplication.e()).getAccountInfo(1010);
        LogUtil.a("Suggestion_DietRecordFragment", "countryCode: ", accountInfo);
        if (!TextUtils.isEmpty(accountInfo) && ("de".equals(accountInfo.toLowerCase(Locale.ENGLISH)) || "it".equals(accountInfo.toLowerCase(Locale.ENGLISH)))) {
            str = accountInfo.toLowerCase(Locale.ENGLISH);
        } else {
            LogUtil.h("Suggestion_DietRecordFragment", "countryCode is error: ", accountInfo);
            str = "";
        }
        for (Map.Entry<Integer, fuq> entry : this.n.entrySet()) {
            if (entry == null || !(entry instanceof Map.Entry)) {
                LogUtil.h("Suggestion_DietRecordFragment", "dataEntry == null or not instanceof Map.Entry");
            } else {
                int intValue = entry.getKey().intValue();
                if (!(entry.getValue() instanceof fuq)) {
                    LogUtil.h("Suggestion_DietRecordFragment", "entry.getValue() not instanceof DietRecordSummaryData");
                } else {
                    fuq value = entry.getValue();
                    value.c(this.ac.get(Integer.valueOf(intValue)).floatValue());
                    value.b(this.z);
                    if (this.t > 0.0f && !TextUtils.isEmpty(str)) {
                        Pair<Float, Float> Yy_ = dpe.Yy_(str, this.u, c(intValue), intValue);
                        value.b(this.t * ((Float) Yy_.first).floatValue());
                        value.e(this.t * ((Float) Yy_.second).floatValue());
                    }
                    this.ab.add(value);
                }
            }
        }
    }

    private boolean c(int i) {
        if (koq.b(this.h)) {
            return false;
        }
        if (i == 10 || i == 11) {
            return this.h.contains(11);
        }
        if (i == 20 || i == 21) {
            return this.h.contains(21);
        }
        return this.h.contains(31);
    }

    private void f() {
        LogUtil.a("Suggestion_DietRecordFragment", "mSummaryDataList: ", this.ab);
        if (koq.c(this.ab)) {
            this.s.post(new Runnable() { // from class: fvx
                @Override // java.lang.Runnable
                public final void run() {
                    DietRecordFragment.this.b();
                }
            });
        }
    }

    public /* synthetic */ void b() {
        this.k = new DietRecordAdapter(this.l, this.ab);
        this.y.setLayoutManager(new LinearLayoutManager(this.l));
        this.y.setAdapter(this.k);
        this.y.setVisibility(0);
        int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(this.f.itemView.getMeasuredWidth(), Integer.MIN_VALUE);
        this.i.measure(makeMeasureSpec, 0);
        int measuredHeight = this.i.getMeasuredHeight();
        LogUtil.a("Suggestion_DietRecordFragment", "widthMeasureSpec: ", Integer.valueOf(makeMeasureSpec), ", getMeasuredHeight: ", Integer.valueOf(measuredHeight));
        this.f.e(1, measuredHeight);
    }

    private void m() {
        this.k = null;
        this.w = false;
        this.x = false;
        this.t = 0.0f;
        this.g = 0;
        this.e = 0;
        this.d = 0;
        this.n.clear();
        this.ac.clear();
        this.ab.clear();
        this.h.clear();
        this.p.clear();
        this.o.clear();
        this.r = false;
        this.u = 0;
    }

    @Override // com.huawei.ui.commonui.base.BaseFragment, androidx.fragment.app.Fragment
    public void onPause() {
        super.onPause();
        LogUtil.a("Suggestion_DietRecordFragment", "onPause()");
    }

    @Override // androidx.fragment.app.Fragment
    public void onStop() {
        super.onStop();
        LogUtil.a("Suggestion_DietRecordFragment", "onStop()");
    }
}
