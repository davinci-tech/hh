package com.huawei.health.suggestion.customizecourse.manager.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.huawei.health.R;
import com.huawei.health.basesport.helper.HeartRateConfigHelper;
import com.huawei.health.suggestion.customizecourse.manager.adapter.interfaceadapter.ItemEventListener;
import com.huawei.health.suggestion.customizecourse.manager.adapter.interfaceadapter.OnStartDragListener;
import com.huawei.health.suggestion.customizecourse.manager.adapter.viewholder.CourseActionViewHolder;
import com.huawei.health.suggestion.customizecourse.manager.adapter.viewholder.CourseCourseHeadViewHolder;
import com.huawei.health.suggestion.customizecourse.manager.adapter.viewholder.CourseMultiBottomViewHolder;
import com.huawei.health.suggestion.customizecourse.manager.adapter.viewholder.CourseMultiViewHolder;
import com.huawei.health.suggestion.customizecourse.manager.adapter.viewholder.CustomCourseDragViewHolder;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.fitnessdatatype.HeartZoneConf;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.pluginfitnessadvice.AtomicAction;
import com.huawei.pluginfitnessadvice.ChoreographedMultiActions;
import com.huawei.pluginfitnessadvice.ChoreographedSingleAction;
import com.huawei.pluginfitnessadvice.FitWorkout;
import com.huawei.pluginfitnessadvice.TargetConfig;
import defpackage.ffg;
import defpackage.ffu;
import defpackage.fhq;
import defpackage.fhx;
import defpackage.fjd;
import defpackage.koq;
import defpackage.nrh;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes4.dex */
public class CustomCourseActionAdapter extends CustomCourseCanDragAdapter implements CourseMultiViewHolder.OnMultiCloseListener {

    /* renamed from: a, reason: collision with root package name */
    private ArrayList<fjd> f3033a;
    private int b;
    private final Context d;
    private int e;
    private TargetConfig f;
    private final OnStartDragListener g;
    private HeartRateConfigHelper h;
    private ChoreographedSingleAction i;
    private int l;
    private fhx m;
    private final ItemEventListener o;
    private ChoreographedMultiActions p;
    private ffg q;
    private fhx r;
    private int k = -1;
    private int c = -1;
    private int j = -1;
    private long n = System.currentTimeMillis();

    public CustomCourseActionAdapter(Context context, OnStartDragListener onStartDragListener, ItemEventListener itemEventListener) {
        this.d = context;
        this.g = onStartDragListener;
        this.o = itemEventListener;
    }

    public void a(HeartRateConfigHelper heartRateConfigHelper, ffg ffgVar) {
        this.h = heartRateConfigHelper;
        this.q = ffgVar;
    }

    public void a(int i) {
        this.k = i;
    }

    public void c(FitWorkout fitWorkout) {
        if (fitWorkout == null || koq.b(fitWorkout.getCourseActions())) {
            LogUtil.h("Suggestion_CustomCourseActionAdapter", "setFitWorkout, fitWorkout == null or CollectionUtils.isEmpty(fitWorkout.getCourseActions())");
            return;
        }
        ArrayList<fjd> arrayList = this.f3033a;
        if (arrayList == null) {
            this.f3033a = new ArrayList<>();
        } else {
            arrayList.clear();
        }
        fjd fjdVar = new fjd();
        fjdVar.e(5);
        fjdVar.e(fitWorkout.acquireName());
        fjdVar.b(fitWorkout.acquireDescription());
        this.f3033a.add(fjdVar);
        for (ChoreographedMultiActions choreographedMultiActions : fitWorkout.getCourseActions()) {
            if (choreographedMultiActions != null && koq.c(choreographedMultiActions.getActionList())) {
                a(choreographedMultiActions);
            } else {
                LogUtil.h("Suggestion_CustomCourseActionAdapter", "setFitWorkout, multiActions.getActionList() isEmpty");
            }
        }
        notifyDataSetChanged();
    }

    private void a(ChoreographedMultiActions choreographedMultiActions) {
        if (choreographedMultiActions.getRepeatTimes() <= 1 && choreographedMultiActions.getActionList().size() == 1) {
            d(choreographedMultiActions);
        } else {
            c(choreographedMultiActions);
        }
    }

    private fjd e(ChoreographedMultiActions choreographedMultiActions, long j) {
        fjd fjdVar = new fjd();
        fjdVar.d(j);
        fjdVar.e(3);
        fjdVar.b(choreographedMultiActions.getRepeatTimes());
        return fjdVar;
    }

    private fjd a(ChoreographedSingleAction choreographedSingleAction, int i, long j) {
        fjd fjdVar = new fjd();
        fjdVar.d(j);
        fjdVar.e(i);
        if (choreographedSingleAction.getAction() != null) {
            fjdVar.b(choreographedSingleAction.getAction());
            fjdVar.e(d(choreographedSingleAction.getAction()));
            fjdVar.a(choreographedSingleAction.getAction().getId());
        }
        fjdVar.b(1);
        if (this.m == null || this.r == null) {
            f();
        }
        fjdVar.e(this.d, choreographedSingleAction.getTargetConfig(), this.m);
        fjdVar.c(this.d, choreographedSingleAction.getIntensityConfig(), this.m, this.r, this.b, this.q);
        return fjdVar;
    }

    private String d(AtomicAction atomicAction) {
        int e = new ffu().e(atomicAction.getId().trim());
        if (e != 0) {
            return BaseApplication.getContext().getResources().getString(e);
        }
        return atomicAction.getName();
    }

    public ArrayList<fjd> b() {
        return this.f3033a;
    }

    public void c(ChoreographedMultiActions choreographedMultiActions) {
        if (choreographedMultiActions == null || koq.b(choreographedMultiActions.getActionList())) {
            LogUtil.h("Suggestion_CustomCourseActionAdapter", "addMultiActions, multiActions is null or multiActions.getActionList() is empty");
            return;
        }
        if (this.f3033a == null) {
            this.f3033a = new ArrayList<>();
        }
        long j = this.n + 1;
        this.n = j;
        this.f3033a.add(e(choreographedMultiActions, j));
        int size = choreographedMultiActions.getActionList().size();
        for (int i = 0; i < size; i++) {
            ChoreographedSingleAction choreographedSingleAction = choreographedMultiActions.getActionList().get(i);
            if (choreographedSingleAction != null) {
                if (i + 1 < size) {
                    this.f3033a.add(a(choreographedSingleAction, 1, j));
                } else {
                    this.f3033a.add(a(choreographedSingleAction, 2, j));
                }
            }
        }
        fjd fjdVar = new fjd();
        fjdVar.d(j);
        fjdVar.e(4);
        this.f3033a.add(fjdVar);
    }

    public void d(ChoreographedMultiActions choreographedMultiActions) {
        if (choreographedMultiActions == null || koq.b(choreographedMultiActions.getActionList())) {
            LogUtil.h("Suggestion_CustomCourseActionAdapter", "addMultiSingleActions, multiActions is null or multiActions.getActionList() is empty");
            return;
        }
        if (this.f3033a == null) {
            this.f3033a = new ArrayList<>();
        }
        ChoreographedSingleAction choreographedSingleAction = choreographedMultiActions.getActionList().get(0);
        if (choreographedSingleAction != null) {
            this.f3033a.add(a(choreographedSingleAction, 0, -1L));
        }
    }

    public void e(FitWorkout fitWorkout) {
        if (fitWorkout == null || koq.b(this.f3033a)) {
            LogUtil.h("Suggestion_CustomCourseActionAdapter", "setCourseNameAndDescription, fitWorkout is null or mActions is empty");
            return;
        }
        fjd fjdVar = this.f3033a.get(0);
        if (fjdVar.i() != 5) {
            LogUtil.h("Suggestion_CustomCourseActionAdapter", "setCourseNameAndDescription, headItemConfig.getItemType() = ", Integer.valueOf(fjdVar.i()));
        } else {
            fitWorkout.saveName(fjdVar.j());
            fitWorkout.saveDescription(fjdVar.o());
        }
    }

    public List<ChoreographedMultiActions> c() {
        if (koq.b(this.f3033a)) {
            LogUtil.h("Suggestion_CustomCourseActionAdapter", "getCourseActions, mActions is empty");
            return Collections.emptyList();
        }
        ArrayList arrayList = new ArrayList();
        Iterator<fjd> it = this.f3033a.iterator();
        int i = 0;
        while (it.hasNext()) {
            fjd next = it.next();
            if (next.i() != 5) {
                if (next.i() == 0) {
                    ChoreographedMultiActions choreographedMultiActions = new ChoreographedMultiActions();
                    b(choreographedMultiActions, new ArrayList(1), next, 1);
                    arrayList.add(choreographedMultiActions);
                } else if (next.i() == 3) {
                    ChoreographedMultiActions choreographedMultiActions2 = new ChoreographedMultiActions();
                    choreographedMultiActions2.setRepeatTimes(next.g());
                    choreographedMultiActions2.setActionList(new ArrayList());
                    arrayList.add(choreographedMultiActions2);
                } else if (next.i() != 4) {
                    ChoreographedMultiActions choreographedMultiActions3 = (ChoreographedMultiActions) arrayList.get(i - 1);
                    b(choreographedMultiActions3, choreographedMultiActions3.getActionList(), next, choreographedMultiActions3.getRepeatTimes());
                } else {
                    LogUtil.a("Suggestion_CustomCourseActionAdapter", "getCourseActions, itemConfig.getItemType() = ", Integer.valueOf(next.i()));
                }
                i++;
            }
        }
        return arrayList;
    }

    public void d(int i) {
        int i2 = 0;
        this.e = 0;
        this.l = 1;
        if (koq.b(this.f3033a)) {
            LogUtil.h("Suggestion_CustomCourseActionAdapter", "refreshActionsTotalNum, mActions is empty.");
            return;
        }
        if (i >= this.f3033a.size()) {
            LogUtil.h("Suggestion_CustomCourseActionAdapter", "refreshActionsTotalNum, out-of-bounds array, position =", Integer.valueOf(i), ", mActions.size() = ", Integer.valueOf(this.f3033a.size()));
            return;
        }
        long f = this.f3033a.get(i).f();
        Iterator<fjd> it = this.f3033a.iterator();
        long j = -1;
        while (it.hasNext()) {
            fjd next = it.next();
            if (next != null) {
                if (next.i() == 3) {
                    if (f == next.f()) {
                        this.l = next.g();
                    }
                    j = next.f();
                    i2 = next.g();
                } else if (next.i() != 4) {
                    this.e = next.f() == j ? this.e + i2 : this.e + 1;
                }
            }
        }
    }

    private void b(ChoreographedMultiActions choreographedMultiActions, List<ChoreographedSingleAction> list, fjd fjdVar, int i) {
        ChoreographedSingleAction choreographedSingleAction = new ChoreographedSingleAction();
        choreographedSingleAction.setAction(fjdVar.d());
        choreographedSingleAction.setTargetConfig(fjdVar.h());
        choreographedSingleAction.setIntensityConfig(fjdVar.e());
        list.add(choreographedSingleAction);
        choreographedMultiActions.setActionList(list);
        choreographedMultiActions.setRepeatTimes(i);
    }

    public ChoreographedMultiActions b(int i, int i2) {
        if (koq.b(this.f3033a) || koq.b(this.f3033a, i)) {
            LogUtil.h("Suggestion_CustomCourseActionAdapter", "getRepeatTimeData, mActions is empty");
            return null;
        }
        this.c = i;
        this.j = i2;
        fjd fjdVar = this.f3033a.get(i);
        if (fjdVar == null) {
            LogUtil.h("Suggestion_CustomCourseActionAdapter", "getRepeatTimeData, itemConfig is null");
            return null;
        }
        ChoreographedMultiActions choreographedMultiActions = new ChoreographedMultiActions();
        this.p = choreographedMultiActions;
        choreographedMultiActions.setRepeatTimes(fjdVar.g());
        return this.p;
    }

    public ChoreographedSingleAction d(int i, int i2) {
        if (koq.b(this.f3033a)) {
            LogUtil.h("Suggestion_CustomCourseActionAdapter", "getEditActionData, mActions is empty");
            return null;
        }
        if (koq.b(this.f3033a, i)) {
            LogUtil.h("Suggestion_CustomCourseActionAdapter", "getEditActionData, position = ", Integer.valueOf(i));
            return null;
        }
        this.c = i;
        this.j = i2;
        fjd fjdVar = this.f3033a.get(i);
        if (fjdVar.d() != null) {
            ChoreographedSingleAction choreographedSingleAction = new ChoreographedSingleAction();
            this.i = choreographedSingleAction;
            choreographedSingleAction.setAction(fjdVar.d());
        }
        return this.i;
    }

    public TargetConfig a(int i, int i2) {
        if (koq.b(this.f3033a)) {
            LogUtil.h("Suggestion_CustomCourseActionAdapter", "getEditTargetConfig, mActions is empty");
            return null;
        }
        if (koq.b(this.f3033a, i)) {
            LogUtil.h("Suggestion_CustomCourseActionAdapter", "getEditTargetConfig, position = ", Integer.valueOf(i));
            return null;
        }
        this.c = i;
        this.j = i2;
        fjd fjdVar = this.f3033a.get(i);
        this.f = new TargetConfig();
        int i3 = this.j;
        if (i3 == 5 || i3 == 6) {
            this.f = fjdVar.h();
        } else {
            this.f = fjdVar.e();
        }
        return this.f;
    }

    public void i() {
        int i;
        if (koq.b(this.f3033a) || (i = this.j) < 0 || this.c < 0) {
            LogUtil.h("Suggestion_CustomCourseActionAdapter", "notifyItemChanged, mActions is empty or mCurrentItemClickType mCurrentItemClickPosition = -1");
            return;
        }
        LogUtil.a("Suggestion_CustomCourseActionAdapter", "notifyItemChanged, mCurrentItemClickType = ", Integer.valueOf(i), " mCurrentItemClickPosition = ", Integer.valueOf(this.c));
        fjd fjdVar = this.f3033a.get(this.c);
        int i2 = this.j;
        if (i2 == 3) {
            if (this.p == null) {
                LogUtil.h("Suggestion_CustomCourseActionAdapter", "notifyItemChanged, mRepeatTimeData is null");
                return;
            } else {
                if (d(fjdVar.f(), this.p.getRepeatTimes())) {
                    b(R.string._2130848638_res_0x7f022b7e);
                    return;
                }
                fjdVar.b(this.p.getRepeatTimes());
                this.p = null;
                notifyItemChanged(this.c);
                return;
            }
        }
        if (i2 == 4) {
            ChoreographedSingleAction choreographedSingleAction = this.i;
            if (choreographedSingleAction == null || choreographedSingleAction.getAction() == null) {
                LogUtil.h("Suggestion_CustomCourseActionAdapter", "notifyItemChanged, mChangeActionData is null or mChangeActionData.getAction() is null");
                return;
            }
            fjdVar.b(this.i.getAction());
            fjdVar.e(d(this.i.getAction()));
            if (this.m == null || this.r == null) {
                f();
            }
            fjdVar.e(this.d, this.i.getTargetConfig(), this.m);
            fjdVar.c(this.d, this.i.getIntensityConfig(), this.m, this.r, this.b, this.q);
            this.i = null;
            notifyItemChanged(this.c);
            return;
        }
        c(fjdVar);
    }

    private void b(int i) {
        Context context = this.d;
        if (context == null) {
            LogUtil.h("Suggestion_CustomCourseActionAdapter", "showToast, mContext is null");
        } else {
            nrh.d(context, context.getResources().getString(i));
        }
    }

    private boolean d(long j, int i) {
        Iterator<fjd> it = this.f3033a.iterator();
        long j2 = -1;
        int i2 = 0;
        int i3 = 0;
        while (it.hasNext()) {
            fjd next = it.next();
            if (next != null) {
                if (next.i() == 3) {
                    j2 = next.f();
                    i3 = j2 == j ? i : next.g();
                } else if (next.i() != 4) {
                    i2 = next.f() == j2 ? i2 + i3 : i2 + 1;
                }
            }
        }
        if (this.k <= 0) {
            return false;
        }
        LogUtil.a("Suggestion_CustomCourseActionAdapter", "isMaxActionNum, actionTotalNum = ", Integer.valueOf(i2));
        return i2 > this.k;
    }

    public fhx a() {
        if (this.m == null) {
            f();
        }
        return this.m;
    }

    public fhx d() {
        if (this.r == null) {
            f();
        }
        return this.r;
    }

    public int e() {
        return this.b;
    }

    private void f() {
        HeartRateConfigHelper heartRateConfigHelper = this.h;
        if (heartRateConfigHelper == null) {
            LogUtil.h("Suggestion_CustomCourseActionAdapter", "initHeartRateZoneConfig, mHeartRateConfigHelper is null");
            return;
        }
        HeartZoneConf a2 = heartRateConfigHelper.a();
        if (a2 != null) {
            this.m = fhq.e(a2);
            this.r = fhq.d(a2);
            int classifyMethod = a2.getClassifyMethod();
            this.b = classifyMethod;
            LogUtil.a("Suggestion_CustomCourseActionAdapter", "initHeartRateZoneConfig, classifyMethod:", Integer.valueOf(classifyMethod), " ", a2.toString());
            return;
        }
        LogUtil.h("Suggestion_CustomCourseActionAdapter", "initHeartRateZoneConfig, heartZoneConf == null");
    }

    private void c(fjd fjdVar) {
        if (this.f == null) {
            LogUtil.h("Suggestion_CustomCourseActionAdapter", "updateActionTargetConfig, mEditTargetConfig is null");
            return;
        }
        if (this.m == null || this.r == null) {
            f();
        }
        LogUtil.a("Suggestion_CustomCourseActionAdapter", "updateActionTargetConfig, mEditTargetConfig.getId() = ", this.f.getId(), ", mEditTargetConfig.getValueL() = ", Double.valueOf(this.f.getValueL()), ", mEditTargetConfig.getValueH() = ", Double.valueOf(this.f.getValueH()));
        int i = this.j;
        if (i == 5 || i == 6) {
            fjdVar.e(this.d, this.f, this.m);
        } else if (i == 7 || i == 8) {
            fjdVar.c(this.d, this.f, this.m, this.r, this.b, this.q);
        } else {
            LogUtil.h("Suggestion_CustomCourseActionAdapter", "onComItemClick, position = ", Integer.valueOf(this.c), " type = ", Integer.valueOf(this.j));
        }
        notifyItemChanged(this.c);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemViewType(int i) {
        if (koq.b(this.f3033a) || koq.b(this.f3033a, i)) {
            LogUtil.h("Suggestion_CustomCourseActionAdapter", "getItemViewType, CollectionUtils.isEmpty(mActions)");
            return 0;
        }
        return this.f3033a.get(i).i();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: ayb_, reason: merged with bridge method [inline-methods] */
    public CustomCourseDragViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        LayoutInflater from = LayoutInflater.from(this.d);
        if (i == 3) {
            return new CourseMultiViewHolder(from.inflate(R.layout.sug_custom_course_item_multi, viewGroup, false), this.d, this.g, this.o, this);
        }
        if (i == 4) {
            return new CourseMultiBottomViewHolder(from.inflate(R.layout.sug_custom_course_item_multi_space, viewGroup, false));
        }
        if (i == 5) {
            return new CourseCourseHeadViewHolder(from.inflate(R.layout.sug_custom_course_item_head, viewGroup, false));
        }
        return new CourseActionViewHolder(from.inflate(R.layout.sug_custom_course_item_action, viewGroup, false), this.d, this.g, this.o);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: c, reason: merged with bridge method [inline-methods] */
    public void onBindViewHolder(CustomCourseDragViewHolder customCourseDragViewHolder, int i, List<Object> list) {
        super.onBindViewHolder(customCourseDragViewHolder, i, list);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: e, reason: merged with bridge method [inline-methods] */
    public void onBindViewHolder(CustomCourseDragViewHolder customCourseDragViewHolder, int i) {
        if (koq.b(this.f3033a)) {
            LogUtil.h("Suggestion_CustomCourseActionAdapter", "onBindViewHolder, CollectionUtils.isEmpty(mActions)");
            return;
        }
        if (i < this.f3033a.size()) {
            fjd fjdVar = this.f3033a.get(i);
            customCourseDragViewHolder.init(fjdVar, i);
            if (customCourseDragViewHolder instanceof CourseMultiViewHolder) {
                CourseMultiViewHolder courseMultiViewHolder = (CourseMultiViewHolder) customCourseDragViewHolder;
                ArrayList arrayList = new ArrayList();
                Iterator<fjd> it = this.f3033a.iterator();
                while (it.hasNext()) {
                    fjd next = it.next();
                    if (next != null && next.f() == fjdVar.f()) {
                        arrayList.add(next);
                    }
                }
                courseMultiViewHolder.a(arrayList);
            }
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        if (koq.b(this.f3033a)) {
            return 0;
        }
        return this.f3033a.size();
    }

    @Override // com.huawei.health.suggestion.customizecourse.manager.adapter.CustomCourseCanDragAdapter
    public void itemMove(int i, int i2, CustomCourseDragViewHolder customCourseDragViewHolder) {
        if (customCourseDragViewHolder == null || koq.b(this.f3033a)) {
            LogUtil.h("Suggestion_CustomCourseActionAdapter", "itemMove, dragViewHolder == null or CollectionUtils.isEmpty(mActions)");
            return;
        }
        LogUtil.a("Suggestion_CustomCourseActionAdapter", "itemMove, fromPosition=", Integer.valueOf(i), ", toPosition =", Integer.valueOf(i2));
        if (i < 1 || i >= this.f3033a.size() || i2 < 1 || i2 >= this.f3033a.size() || e(i, i2) || c(i, i2)) {
            return;
        }
        if (i < i2) {
            int i3 = i;
            while (i3 < i2) {
                int i4 = i3 + 1;
                Collections.swap(this.f3033a, i3, i4);
                i3 = i4;
            }
        } else {
            for (int i5 = i; i5 > i2; i5--) {
                Collections.swap(this.f3033a, i5, i5 - 1);
            }
        }
        notifyItemMoved(i, i2);
    }

    private boolean c(int i, int i2) {
        int i3;
        int i4;
        fjd fjdVar = this.f3033a.get(i);
        fjd fjdVar2 = this.f3033a.get(i2);
        if (fjdVar.i() == 3 || fjdVar2.i() == 0 || i2 == 1 || (i3 = i2 + 1) >= this.f3033a.size() || fjdVar.f() == fjdVar2.f()) {
            return false;
        }
        if (i <= i2 && fjdVar2.f() != this.f3033a.get(i3).f()) {
            return false;
        }
        if (i > i2 && fjdVar2.f() != this.f3033a.get(i2 - 1).f()) {
            return false;
        }
        long f = fjdVar2.f();
        Iterator<fjd> it = this.f3033a.iterator();
        int i5 = 0;
        while (it.hasNext()) {
            fjd next = it.next();
            if (next.f() == f) {
                if (next.i() == 3) {
                    this.e += next.g();
                }
                if (next.i() == 1 || next.i() == 2) {
                    i5++;
                }
                if (next.i() == 4) {
                    break;
                }
            }
        }
        int i6 = this.e - this.l;
        this.e = i6;
        return i5 >= 10 || ((i4 = this.k) > 0 && i6 > i4);
    }

    private boolean e(int i, int i2) {
        int i3;
        int i4;
        if (this.f3033a.get(i).i() != 3 || i2 == 1 || (i3 = i2 + 1) >= this.f3033a.size() || (i4 = this.f3033a.get(i2).i()) == 0) {
            return false;
        }
        if (i4 == 1 || i4 == 2) {
            return true;
        }
        if (i4 == 3) {
            if (i <= i2) {
                return true;
            }
            int i5 = i2 - 1;
            int i6 = this.f3033a.get(i5).i();
            if (i5 > 1 && i6 != 4 && i6 != 0) {
                return true;
            }
        }
        if (i4 == 4) {
            if (i >= i2) {
                return true;
            }
            int i7 = this.f3033a.get(i3).i();
            if (i3 < this.f3033a.size() && i7 != 3 && i7 != 0) {
                return true;
            }
        }
        return false;
    }

    @Override // com.huawei.health.suggestion.customizecourse.manager.adapter.CustomCourseCanDragAdapter
    public void setDragState(CustomCourseDragViewHolder customCourseDragViewHolder, boolean z) {
        if (customCourseDragViewHolder == null) {
            LogUtil.h("Suggestion_CustomCourseActionAdapter", "setDragState, viewHolder == null");
            return;
        }
        customCourseDragViewHolder.setDragState(z);
        if (z) {
            return;
        }
        if (customCourseDragViewHolder instanceof CourseActionViewHolder) {
            d((CourseActionViewHolder) customCourseDragViewHolder);
        } else {
            if (customCourseDragViewHolder instanceof CourseMultiViewHolder) {
                CourseMultiViewHolder courseMultiViewHolder = (CourseMultiViewHolder) customCourseDragViewHolder;
                courseMultiViewHolder.a(false);
                b(courseMultiViewHolder);
                return;
            }
            LogUtil.h("Suggestion_CustomCourseActionAdapter", "setDragState, viewHolder is error");
        }
    }

    private void d(CourseActionViewHolder courseActionViewHolder) {
        int i;
        if (koq.b(this.f3033a)) {
            LogUtil.h("Suggestion_CustomCourseActionAdapter", "checkActionState, CollectionUtils.isEmpty(mActions)");
            return;
        }
        fjd b = courseActionViewHolder.b();
        int indexOf = this.f3033a.indexOf(b);
        if (b.i() == 0) {
            int i2 = indexOf - 1;
            if (i2 < 1 || (i = indexOf + 1) >= this.f3033a.size()) {
                return;
            }
            fjd fjdVar = this.f3033a.get(i2);
            fjd fjdVar2 = this.f3033a.get(i);
            if (fjdVar == null || fjdVar2 == null) {
                LogUtil.h("Suggestion_CustomCourseActionAdapter", "checkActionState, COURSE_ITEM_TYPE_ACTION lastAction is null nextAction is null");
                return;
            }
            if (fjdVar.f() != fjdVar2.f() || fjdVar.i() == 0 || fjdVar2.i() == 0) {
                return;
            }
            b.d(fjdVar.f());
            b.e(1);
            e(-1L);
            return;
        }
        d(courseActionViewHolder, b, indexOf);
    }

    private void d(CourseActionViewHolder courseActionViewHolder, fjd fjdVar, int i) {
        long f = fjdVar.f();
        int i2 = i + 1;
        if (i == 1 || i2 == this.f3033a.size()) {
            b(courseActionViewHolder, fjdVar);
        } else {
            int i3 = i - 1;
            if (koq.b(this.f3033a, i3) || koq.b(this.f3033a, i2)) {
                LogUtil.h("Suggestion_CustomCourseActionAdapter", "checkAfterDragMultiActionState outOfBounds");
                return;
            }
            fjd fjdVar2 = this.f3033a.get(i3);
            fjd fjdVar3 = this.f3033a.get(i2);
            if (fjdVar2 == null || fjdVar3 == null) {
                LogUtil.h("Suggestion_CustomCourseActionAdapter", "checkActionState, COURSE_ITEM_TYPE_ACTION, lastAction is null or nextAction is null");
                return;
            }
            if (fjdVar2.i() == 0 || fjdVar3.i() == 0) {
                b(courseActionViewHolder, fjdVar);
            } else if (fjdVar2.f() != fjdVar3.f()) {
                b(courseActionViewHolder, fjdVar);
            } else {
                if (fjdVar2.f() != fjdVar.f() || fjdVar3.f() != fjdVar.f()) {
                    fjdVar.d(fjdVar2.f() != fjdVar.f() ? fjdVar2.f() : fjdVar3.f());
                }
                fjdVar.e(1);
            }
        }
        e(f);
    }

    private void e(long j) {
        int i = -1;
        int i2 = 0;
        long j2 = -1;
        int i3 = -1;
        for (int i4 = 1; i4 < this.f3033a.size(); i4++) {
            fjd fjdVar = this.f3033a.get(i4);
            if (fjdVar.i() != 0) {
                if (j != -1 && fjdVar.f() == j) {
                    i2++;
                    if (fjdVar.i() == 3) {
                        i3 = i4;
                    }
                    if (fjdVar.i() == 4) {
                        i = i4;
                    }
                }
                if (fjdVar.i() == 3) {
                    j2 = fjdVar.f();
                }
                if (fjdVar.f() == j2 && fjdVar.i() == 2 && this.f3033a.get(i4 + 1).i() != 4) {
                    fjdVar.e(1);
                }
                if (fjdVar.f() == j2 && fjdVar.i() == 1 && this.f3033a.get(i4 + 1).i() == 4) {
                    fjdVar.e(2);
                }
            }
        }
        LogUtil.a("Suggestion_CustomCourseActionAdapter", "checkOriginalMultiState, oldMultiSize = ", Integer.valueOf(i2));
        if (j != -1 && i2 <= 2) {
            this.f3033a.remove(i);
            this.f3033a.remove(i3);
        }
        notifyDataSetChanged();
    }

    private void b(CourseActionViewHolder courseActionViewHolder, fjd fjdVar) {
        fjdVar.d(-1L);
        fjdVar.e(0);
        courseActionViewHolder.a();
    }

    private void b(CourseMultiViewHolder courseMultiViewHolder) {
        if (courseMultiViewHolder == null || courseMultiViewHolder.d() == null) {
            LogUtil.h("Suggestion_CustomCourseActionAdapter", "openMulti, multiViewHolder is null or getActionItemConfig() is null");
            return;
        }
        if (koq.b(courseMultiViewHolder.b()) || koq.b(this.f3033a)) {
            LogUtil.h("Suggestion_CustomCourseActionAdapter", "openMulti, getMultiActionList is empty or mActions is empty");
            return;
        }
        int indexOf = this.f3033a.indexOf(courseMultiViewHolder.d());
        for (int i = 0; i < courseMultiViewHolder.b().size(); i++) {
            fjd fjdVar = courseMultiViewHolder.b().get(i);
            if (fjdVar.i() != 3) {
                this.f3033a.add(indexOf + i + 1, fjdVar);
            }
        }
        ItemEventListener itemEventListener = this.o;
        if (itemEventListener != null) {
            itemEventListener.onComItemClick(indexOf, 9);
        }
    }

    @Override // com.huawei.health.suggestion.customizecourse.manager.adapter.viewholder.CourseMultiViewHolder.OnMultiCloseListener
    public void onCloseClick(CourseMultiViewHolder courseMultiViewHolder) {
        if (courseMultiViewHolder == null || courseMultiViewHolder.d() == null || koq.b(this.f3033a)) {
            LogUtil.h("Suggestion_CustomCourseActionAdapter", "onCloseClick, multiViewHolder is null or actionItemConfig is null or mActions is null");
            return;
        }
        LogUtil.a("Suggestion_CustomCourseActionAdapter", "multi close");
        ArrayList arrayList = new ArrayList();
        fjd d = courseMultiViewHolder.d();
        int size = this.f3033a.size();
        Iterator<fjd> it = this.f3033a.iterator();
        while (it.hasNext()) {
            fjd next = it.next();
            if (next.f() == d.f() && next.i() != 3) {
                arrayList.add(next);
                it.remove();
            }
        }
        if (koq.c(courseMultiViewHolder.b())) {
            courseMultiViewHolder.b().clear();
            courseMultiViewHolder.b().addAll(arrayList);
        } else {
            courseMultiViewHolder.a(arrayList);
        }
        notifyItemRangeChanged(this.f3033a.indexOf(d) + 1, size);
    }
}
