package defpackage;

import android.content.Context;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.huawei.health.health.utils.functionsetcard.AbstractBaseCardData;
import com.huawei.health.health.utils.functionsetcard.CardFlowInteractors;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.homehealth.adapter.HomeCardAdapter;
import health.compact.a.CommonUtil;
import health.compact.a.Utils;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes6.dex */
public class odo {
    public static void b(RecyclerView recyclerView, Context context, HomeCardAdapter homeCardAdapter) {
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(homeCardAdapter);
        recyclerView.setLayerType(2, null);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
    }

    public static Map<String, Integer> c(Context context) {
        HashMap hashMap = new HashMap();
        hashMap.put(CardFlowInteractors.CardNameConstants.ACHIEVEMENT_CARD.getName(), Integer.valueOf(CardFlowInteractors.CardIDConstants.ACHIEVEMENT_CARD.getDefaultIndex()));
        hashMap.put(CardFlowInteractors.CardNameConstants.SPORT_RECORDING.getName(), Integer.valueOf(CardFlowInteractors.CardIDConstants.SPORT_RECORDING.getDefaultIndex()));
        hashMap.put(CardFlowInteractors.CardNameConstants.STEP_CARD.getName(), Integer.valueOf(CardFlowInteractors.CardIDConstants.STEP_CARD.getDefaultIndex()));
        hashMap.put(CardFlowInteractors.CardNameConstants.DIALOG_CARD.getName(), Integer.valueOf(CardFlowInteractors.CardIDConstants.DIALOG_CARD.getDefaultIndex()));
        hashMap.put(CardFlowInteractors.CardNameConstants.HEALTH_HEAD_LINES_CARD.getName(), Integer.valueOf(CardFlowInteractors.CardIDConstants.HEALTH_HEAD_LINES_CARD.getDefaultIndex()));
        hashMap.put(CardFlowInteractors.CardNameConstants.FUNCTION_SET_CARD.getName(), Integer.valueOf(CardFlowInteractors.CardIDConstants.FUNCTION_SET_CARD.getDefaultIndex()));
        hashMap.put(CardFlowInteractors.CardNameConstants.FUNCTION_MENU_CARD.getName(), Integer.valueOf(CardFlowInteractors.CardIDConstants.FUNCTION_MENU_CARD.getDefaultIndex()));
        hashMap.put(CardFlowInteractors.CardNameConstants.OPERATION_CARD.getName(), Integer.valueOf(CardFlowInteractors.CardIDConstants.OPERATION_CARD.getDefaultIndex()));
        hashMap.put(CardFlowInteractors.CardNameConstants.HEALTH_MODEL_CARD.getName(), Integer.valueOf(CardFlowInteractors.CardIDConstants.HEALTH_MODEL_CARD.getDefaultIndex()));
        hashMap.put(CardFlowInteractors.CardNameConstants.PREVIEW_CARD.getName(), Integer.valueOf(CardFlowInteractors.CardIDConstants.PREVIEW_CARD.getDefaultIndex()));
        hashMap.put(CardFlowInteractors.CardNameConstants.TODO_CARD.getName(), Integer.valueOf(CardFlowInteractors.CardIDConstants.TODO_CARD.getDefaultIndex()));
        hashMap.put(CardFlowInteractors.CardNameConstants.STEP_TREND_CARD.getName(), Integer.valueOf(CardFlowInteractors.CardIDConstants.STEP_TREND_CARD.getDefaultIndex()));
        hashMap.put(CardFlowInteractors.CardNameConstants.RUN_TREND_CARD.getName(), Integer.valueOf(CardFlowInteractors.CardIDConstants.RUN_TREND_CARD.getDefaultIndex()));
        hashMap.put(CardFlowInteractors.CardNameConstants.SMART_CARD.getName(), Integer.valueOf(CardFlowInteractors.CardIDConstants.SMART_CARD.getDefaultIndex()));
        hashMap.put(CardFlowInteractors.CardNameConstants.OPERA_MSG_CARD.getName(), Integer.valueOf(CardFlowInteractors.CardIDConstants.OPERA_MSG_CARD.getDefaultIndex()));
        hashMap.put(CardFlowInteractors.CardNameConstants.RUN_CARD.getName(), Integer.valueOf(CardFlowInteractors.CardIDConstants.RUN_CARD.getDefaultIndex()));
        hashMap.put(CardFlowInteractors.CardNameConstants.SPORTS_CARD.getName(), Integer.valueOf(CardFlowInteractors.CardIDConstants.SPORTS_CARD.getDefaultIndex()));
        hashMap.put(CardFlowInteractors.CardNameConstants.WEIGHT_CARD.getName(), Integer.valueOf(CardFlowInteractors.CardIDConstants.WEIGHT_CARD.getDefaultIndex()));
        hashMap.put(CardFlowInteractors.CardNameConstants.PLAN_CARD.getName(), Integer.valueOf(CardFlowInteractors.CardIDConstants.PLAN_CARD.getDefaultIndex()));
        hashMap.put(CardFlowInteractors.CardNameConstants.SLEEP_CARD.getName(), Integer.valueOf(CardFlowInteractors.CardIDConstants.SLEEP_CARD.getDefaultIndex()));
        hashMap.put(CardFlowInteractors.CardNameConstants.DEVICE_MANAGER_CARD.getName(), Integer.valueOf(CardFlowInteractors.CardIDConstants.DEVICE_MANAGER_CARD.getDefaultIndex()));
        hashMap.put(CardFlowInteractors.CardNameConstants.HEARTRATE_CARD.getName(), Integer.valueOf(CardFlowInteractors.CardIDConstants.HEARTRATE_CARD.getDefaultIndex()));
        hashMap.put(CardFlowInteractors.CardNameConstants.PHYSIOLOGICAL_CYCLE_CARD.getName(), Integer.valueOf(CardFlowInteractors.CardIDConstants.PHYSIOLOGICAL_CYCLE_CARD.getDefaultIndex()));
        a(hashMap);
        d(hashMap);
        return CardFlowInteractors.b(context, hashMap);
    }

    private static void a(Map<String, Integer> map) {
        if (Utils.g()) {
            return;
        }
        map.put(CardFlowInteractors.CardNameConstants.TRAIN_CARD.getName(), Integer.valueOf(CardFlowInteractors.CardIDConstants.TRAIN_CARD.getDefaultIndex()));
        map.put(CardFlowInteractors.CardNameConstants.BLOODSUGAR_CARD.getName(), Integer.valueOf(CardFlowInteractors.CardIDConstants.BLOODSUGAR_CARD.getDefaultIndex()));
        map.put(CardFlowInteractors.CardNameConstants.BLOODPRESSURE_CARD.getName(), Integer.valueOf(CardFlowInteractors.CardIDConstants.BLOODPRESSURE_CARD.getDefaultIndex()));
        map.put(CardFlowInteractors.CardNameConstants.BLOODOXYGEN_CARD.getName(), Integer.valueOf(CardFlowInteractors.CardIDConstants.BLOODOXYGEN_CARD.getDefaultIndex()));
    }

    private static void d(Map<String, Integer> map) {
        if (efb.m()) {
            map.put(CardFlowInteractors.CardNameConstants.HEALTH_TREND_CARD.getName(), Integer.valueOf(CardFlowInteractors.CardIDConstants.HEALTH_TREND_CARD.getDefaultIndex()));
        }
    }

    public static void c(Context context, b bVar, RecyclerView recyclerView, HomeCardAdapter homeCardAdapter) {
        CommonUtil.u("TimeEat_HomeFragmentUiManager-initData enter");
        Map<String, Integer> c = c(context);
        ArrayList<AbstractBaseCardData> b2 = bVar.b();
        AbstractBaseCardData c2 = bVar.c();
        if (c2 != null) {
            c2.setCardPositionTag(c.get(CardFlowInteractors.CardNameConstants.ACHIEVEMENT_CARD.getName()).intValue());
            b2.add(c2);
        }
        AbstractBaseCardData h = bVar.h();
        if (h != null) {
            h.setCardPositionTag(c.get(CardFlowInteractors.CardNameConstants.FUNCTION_SET_CARD.getName()).intValue());
            b2.add(h);
        }
        AbstractBaseCardData d = bVar.d();
        if (d != null) {
            d.setCardPositionTag(c.get(CardFlowInteractors.CardNameConstants.FUNCTION_MENU_CARD.getName()).intValue());
            b2.add(d);
        }
        AbstractBaseCardData e = bVar.e();
        if (e != null) {
            e.setCardPositionTag(c.get(CardFlowInteractors.CardNameConstants.DIALOG_CARD.getName()).intValue());
            b2.add(e);
        }
        AbstractBaseCardData o = bVar.o();
        if (o != null) {
            o.setCardPositionTag(c.get(CardFlowInteractors.CardNameConstants.TODO_CARD.getName()).intValue());
            b2.add(o);
        }
        e(bVar, c, b2);
        AbstractBaseCardData m = bVar.m();
        if (m != null) {
            m.setCardPositionTag(c.get(CardFlowInteractors.CardNameConstants.OPERATION_CARD.getName()).intValue());
            b2.add(m);
        }
        AbstractBaseCardData g = bVar.g();
        if (g != null) {
            g.setCardPositionTag(c.get(CardFlowInteractors.CardNameConstants.OPERA_MSG_CARD.getName()).intValue());
            b2.add(g);
        }
        AbstractBaseCardData i = bVar.i();
        if (i != null) {
            i.setCardPositionTag(c.get(CardFlowInteractors.CardNameConstants.FUNCTION_SET_CARD.getName()).intValue() - 1);
            b2.add(i);
        }
        c(bVar, b2, context);
        d(c, b2, bVar);
        ArrayList<AbstractBaseCardData> a2 = bVar.a();
        LogUtil.c("HomeFragmentUiManager", "initData cardAllDatas=", a2, ", mCardDatas=", b2);
        c(homeCardAdapter, recyclerView, b2, a2);
    }

    private static void c(b bVar, ArrayList<AbstractBaseCardData> arrayList, Context context) {
        Map<String, Integer> c = c(context);
        AbstractBaseCardData n = bVar.n();
        if (n != null) {
            n.setCardPositionTag(c.get(CardFlowInteractors.CardNameConstants.STEP_CARD.getName()).intValue());
            arrayList.add(n);
        }
        AbstractBaseCardData l = bVar.l();
        if (l != null) {
            l.setCardPositionTag(c.get(CardFlowInteractors.CardNameConstants.STEP_CARD.getName()).intValue());
            arrayList.add(l);
        }
    }

    public static void e(AbstractBaseCardData abstractBaseCardData, Context context) {
        abstractBaseCardData.setCardPositionTag(c(context).get(CardFlowInteractors.CardNameConstants.STEP_CARD.getName()).intValue());
    }

    private static void e(b bVar, Map<String, Integer> map, ArrayList<AbstractBaseCardData> arrayList) {
        AbstractBaseCardData j = bVar.j();
        if (j != null) {
            j.setCardPositionTag(map.get(CardFlowInteractors.CardNameConstants.HEALTH_TREND_CARD.getName()).intValue());
            arrayList.add(j);
        }
    }

    private static void c(HomeCardAdapter homeCardAdapter, RecyclerView recyclerView, ArrayList<AbstractBaseCardData> arrayList, ArrayList<AbstractBaseCardData> arrayList2) {
        if (homeCardAdapter != null && recyclerView != null) {
            Collections.sort(arrayList);
            arrayList2.addAll(arrayList);
            homeCardAdapter.b(arrayList);
            recyclerView.getRecycledViewPool().clear();
            recyclerView.setAdapter(homeCardAdapter);
        }
        CommonUtil.u("TimeEat_HomeFragmentUiManager-Leave initData");
    }

    static void d(Map<String, Integer> map, ArrayList<AbstractBaseCardData> arrayList, b bVar) {
        AbstractBaseCardData f = bVar.f();
        if (f != null) {
            f.setCardPositionTag(map.get(CardFlowInteractors.CardNameConstants.HEALTH_MODEL_CARD.getName()).intValue());
            arrayList.add(f);
        }
    }

    public static class b {

        /* renamed from: a, reason: collision with root package name */
        private ArrayList<AbstractBaseCardData> f15627a;
        private AbstractBaseCardData b;
        private AbstractBaseCardData c;
        private AbstractBaseCardData d;
        private ArrayList<AbstractBaseCardData> e;
        private AbstractBaseCardData f;
        private AbstractBaseCardData g;
        private AbstractBaseCardData h;
        private AbstractBaseCardData i;
        private AbstractBaseCardData j;
        private AbstractBaseCardData l;
        private AbstractBaseCardData m;
        private AbstractBaseCardData n;
        private AbstractBaseCardData o;

        public AbstractBaseCardData n() {
            return this.m;
        }

        public AbstractBaseCardData i() {
            return this.i;
        }

        public void a(AbstractBaseCardData abstractBaseCardData) {
            this.i = abstractBaseCardData;
        }

        public void f(AbstractBaseCardData abstractBaseCardData) {
            this.n = abstractBaseCardData;
        }

        public AbstractBaseCardData l() {
            return this.n;
        }

        public AbstractBaseCardData c() {
            return this.c;
        }

        public void d(AbstractBaseCardData abstractBaseCardData) {
            this.c = abstractBaseCardData;
        }

        public AbstractBaseCardData h() {
            return this.f;
        }

        public void c(AbstractBaseCardData abstractBaseCardData) {
            this.f = abstractBaseCardData;
        }

        public AbstractBaseCardData d() {
            return this.b;
        }

        public void b(AbstractBaseCardData abstractBaseCardData) {
            this.b = abstractBaseCardData;
        }

        public AbstractBaseCardData j() {
            return this.g;
        }

        public void e(AbstractBaseCardData abstractBaseCardData) {
            this.g = abstractBaseCardData;
        }

        public AbstractBaseCardData m() {
            return this.o;
        }

        public void h(AbstractBaseCardData abstractBaseCardData) {
            this.o = abstractBaseCardData;
        }

        public AbstractBaseCardData o() {
            return this.l;
        }

        public ArrayList<AbstractBaseCardData> a() {
            return this.e;
        }

        public void b(ArrayList<AbstractBaseCardData> arrayList) {
            this.e = arrayList;
        }

        public ArrayList<AbstractBaseCardData> b() {
            return this.f15627a;
        }

        public void a(ArrayList<AbstractBaseCardData> arrayList) {
            this.f15627a = arrayList;
        }

        public AbstractBaseCardData f() {
            return this.j;
        }

        public AbstractBaseCardData g() {
            return this.h;
        }

        public void i(AbstractBaseCardData abstractBaseCardData) {
            this.h = abstractBaseCardData;
        }

        public AbstractBaseCardData e() {
            return this.d;
        }

        public void b(ohr ohrVar) {
            this.d = ohrVar;
        }
    }
}
