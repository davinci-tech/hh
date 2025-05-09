package com.huawei.health.suggestion.ui.plan.util;

import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;
import com.huawei.basefitnessadvice.callback.UiCallback;
import com.huawei.basefitnessadvice.model.intplan.DayInfo;
import com.huawei.basefitnessadvice.model.intplan.IntAction;
import com.huawei.basefitnessadvice.model.intplan.IntDayPlan;
import com.huawei.basefitnessadvice.model.intplan.IntPlan;
import com.huawei.basefitnessadvice.model.intplan.IntWeekPlan;
import com.huawei.health.R;
import com.huawei.health.suggestion.ui.plan.util.WeekPlanAdjustHelper;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.R$string;
import com.huawei.ui.commonui.dialog.CustomAlertDialog;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.listener.RecyclerViewItemTouchListener;
import com.huawei.ui.commonui.recycleview.HealthLinearLayoutManager;
import com.huawei.ui.commonui.recycleview.HealthRecycleView;
import defpackage.ase;
import defpackage.ffy;
import defpackage.fys;
import defpackage.ggl;
import defpackage.gib;
import defpackage.koq;
import defpackage.nrh;
import defpackage.nsn;
import health.compact.a.CommonUtil;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes4.dex */
public class WeekPlanAdjustHelper {

    /* renamed from: a, reason: collision with root package name */
    private IntPlan f3276a;
    private d b;
    private a c;
    private ItemTouchHelper d;
    private Context e;
    private View h;

    public WeekPlanAdjustHelper(Context context) {
        this.e = context;
    }

    public void c(IntPlan intPlan) {
        this.f3276a = intPlan;
        c();
        CustomAlertDialog.Builder builder = new CustomAlertDialog.Builder(this.e);
        builder.cyp_(this.h).cyo_(R$string.IDS_save, new DialogInterface.OnClickListener() { // from class: fzq
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i2) {
                WeekPlanAdjustHelper.this.aIQ_(dialogInterface, i2);
            }
        }).cyn_(R$string.IDS_settings_button_cancal, new DialogInterface.OnClickListener() { // from class: fzm
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i2) {
                WeekPlanAdjustHelper.aIP_(dialogInterface, i2);
            }
        });
        builder.e(this.e.getResources().getDimensionPixelSize(R.dimen._2131362886_res_0x7f0a0446), this.e.getResources().getDimensionPixelSize(R.dimen._2131362886_res_0x7f0a0446));
        builder.c().show();
    }

    public /* synthetic */ void aIQ_(DialogInterface dialogInterface, int i2) {
        e(new UiCallback<IntPlan>() { // from class: com.huawei.health.suggestion.ui.plan.util.WeekPlanAdjustHelper.4
            @Override // com.huawei.basefitnessadvice.callback.UiCallback
            public void onFailure(int i3, String str) {
                if (!CommonUtil.aa(WeekPlanAdjustHelper.this.e)) {
                    nrh.b(WeekPlanAdjustHelper.this.e, R.string._2130841762_res_0x7f0210a2);
                } else {
                    nrh.b(WeekPlanAdjustHelper.this.e, R.string._2130848806_res_0x7f022c26);
                }
            }

            @Override // com.huawei.basefitnessadvice.callback.UiCallback
            /* renamed from: c, reason: merged with bridge method [inline-methods] */
            public void onSuccess(IntPlan intPlan) {
                ReleaseLogUtil.e("Suggestion_WeekPlanAdjustHelper", "adjustPlanSchedule success.");
            }
        });
        ViewClickInstrumentation.clickOnDialog(dialogInterface, i2);
    }

    public static /* synthetic */ void aIP_(DialogInterface dialogInterface, int i2) {
        LogUtil.a("Suggestion_WeekPlanAdjustHelper", "dialog cancel");
        ViewClickInstrumentation.clickOnDialog(dialogInterface, i2);
    }

    private void e(UiCallback<IntPlan> uiCallback) {
        a aVar = this.c;
        if (aVar == null || this.b == null) {
            return;
        }
        List list = aVar.f3279a;
        List list2 = this.b.c;
        ArrayList arrayList = new ArrayList();
        int i2 = 0;
        while (true) {
            if (i2 >= list2.size()) {
                break;
            }
            if (koq.b(list, i2)) {
                LogUtil.h("Suggestion_WeekPlanAdjustHelper", "time out of bound.");
                break;
            }
            e eVar = (e) list2.get(i2);
            arrayList.add(new DayInfo.Builder().adjustDay(String.valueOf(ggl.b(((Long) list.get(i2)).longValue()))).dayStatus(Integer.valueOf(!eVar.b ? 1 : 0)).courseIdList(eVar.f3280a).build());
            i2++;
        }
        fys.b(this.f3276a.getPlanId(), this.f3276a.getPlanType().getType(), arrayList, uiCallback);
    }

    private void c() {
        View inflate = LayoutInflater.from(this.e).inflate(R.layout.ai_plan_adjust_week_courses, (ViewGroup) null);
        this.h = inflate;
        ((HealthTextView) inflate.findViewById(R.id.adjustWeekPlanTip)).setText(this.e.getResources().getQuantityString(R.plurals._2130903493_res_0x7f0301c5, 7, 7));
        HealthRecycleView healthRecycleView = (HealthRecycleView) this.h.findViewById(R.id.date_list);
        HealthRecycleView healthRecycleView2 = (HealthRecycleView) this.h.findViewById(R.id.course_item_list);
        ArrayList arrayList = new ArrayList();
        List<e> d2 = d(this.f3276a);
        boolean z = false;
        if (d2.size() < 7) {
            ((HealthTextView) this.h.findViewById(R.id.absentScheduleTip)).setVisibility(0);
        }
        Iterator<e> it = d2.iterator();
        while (it.hasNext()) {
            arrayList.add(Long.valueOf(it.next().d));
        }
        a aVar = new a(this.e, arrayList);
        this.c = aVar;
        healthRecycleView.setAdapter(aVar);
        int i2 = 1;
        healthRecycleView.setLayoutManager(new HealthLinearLayoutManager(this.e, i2, z) { // from class: com.huawei.health.suggestion.ui.plan.util.WeekPlanAdjustHelper.5
            @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.LayoutManager
            public boolean canScrollVertically() {
                return false;
            }
        });
        healthRecycleView.setNestedScrollingEnabled(false);
        healthRecycleView.a(false);
        healthRecycleView.d(false);
        this.b = new d(this.e, d2);
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new i(this.b));
        this.d = itemTouchHelper;
        itemTouchHelper.attachToRecyclerView(healthRecycleView2);
        healthRecycleView2.setAdapter(this.b);
        healthRecycleView2.setLayoutManager(new HealthLinearLayoutManager(this.e, i2, z) { // from class: com.huawei.health.suggestion.ui.plan.util.WeekPlanAdjustHelper.2
            @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.LayoutManager
            public boolean canScrollVertically() {
                return false;
            }
        });
        healthRecycleView2.setNestedScrollingEnabled(false);
        healthRecycleView2.a(false);
        healthRecycleView2.d(false);
        healthRecycleView2.addOnItemTouchListener(new RecyclerViewItemTouchListener(healthRecycleView2) { // from class: com.huawei.health.suggestion.ui.plan.util.WeekPlanAdjustHelper.3
            @Override // com.huawei.ui.commonui.listener.RecyclerViewItemTouchListener
            public void onItemTouchDown(RecyclerView.ViewHolder viewHolder) {
                LogUtil.a("Suggestion_WeekPlanAdjustHelper", "courseRecyclerView onItemTouch");
                if (WeekPlanAdjustHelper.this.d != null) {
                    WeekPlanAdjustHelper.this.d.startDrag(viewHolder);
                }
            }
        });
        this.c.notifyDataSetChanged();
        this.b.notifyDataSetChanged();
    }

    private List<e> d(IntPlan intPlan) {
        int g = ase.g(intPlan);
        ArrayList arrayList = new ArrayList();
        long b2 = gib.b(System.currentTimeMillis());
        int i2 = 0;
        for (int i3 = 0; i3 < intPlan.getMetaInfo().getWeekCount(); i3++) {
            IntWeekPlan weekInfoByIdx = intPlan.getWeekInfoByIdx(i3);
            if (weekInfoByIdx.getWeekOrder() >= g) {
                for (int i4 = 0; i4 < weekInfoByIdx.getDayCount(); i4++) {
                    IntDayPlan dayByIdx = weekInfoByIdx.getDayByIdx(i4);
                    if (dayByIdx != null) {
                        long a2 = gib.a(this.f3276a.getPlanTimeInfo().getBeginDate() * 1000, weekInfoByIdx.getWeekOrder() - 1, dayByIdx.getDayOrder());
                        if (a2 < b2) {
                            LogUtil.a("Suggestion_WeekPlanAdjustHelper", "dayOrder:", Integer.valueOf(dayByIdx.getDayOrder()), " ", Long.valueOf(a2), " ", Long.valueOf(b2));
                        } else {
                            if (arrayList.size() >= 7 - i2) {
                                break;
                            }
                            i2 += e(arrayList, dayByIdx, a2);
                        }
                    }
                }
            }
        }
        LogUtil.a("Suggestion_WeekPlanAdjustHelper", "getCourseScheduleDataList thisWeek:", Integer.valueOf(g), " absentDay:", Integer.valueOf(i2));
        return arrayList;
    }

    private int e(List<e> list, IntDayPlan intDayPlan, long j) {
        ArrayList arrayList = new ArrayList();
        if (intDayPlan.getDayStatus() == 2) {
            return 1;
        }
        if (intDayPlan.getInPlanActionCnt() == 0) {
            list.add(new e(BaseApplication.getContext().getString(R.string._2130848796_res_0x7f022c1c), arrayList, false, j));
            return 0;
        }
        if (c(intDayPlan, arrayList)) {
            return 1;
        }
        String d2 = intDayPlan.getInPlanAction(0).getActionInfo().d();
        if (intDayPlan.getInPlanActionCnt() > 1) {
            for (int i2 = 1; i2 < intDayPlan.getInPlanActionCnt(); i2++) {
                d2 = ffy.d(this.e, R.string._2130844422_res_0x7f021b06, d2, intDayPlan.getInPlanAction(i2).getActionInfo().d());
            }
        }
        list.add(new e(d2, arrayList, true, j));
        return 0;
    }

    private boolean c(IntDayPlan intDayPlan, List<String> list) {
        boolean z = false;
        for (int i2 = 0; i2 < intDayPlan.getInPlanActionCnt(); i2++) {
            IntAction inPlanAction = intDayPlan.getInPlanAction(i2);
            list.add(inPlanAction.getActionId());
            if (inPlanAction.getPunchFlag() == 1) {
                z = true;
            }
        }
        return z;
    }

    static class i extends ItemTouchHelper.Callback {
        private d d;

        @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
        public boolean isLongPressDragEnabled() {
            return false;
        }

        i(d dVar) {
            this.d = dVar;
        }

        @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
        public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
            return makeMovementFlags(3, 0);
        }

        @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
        public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder viewHolder2) {
            LogUtil.a("Suggestion_WeekPlanAdjustHelper", "onMove, source = ", Integer.valueOf(viewHolder.getBindingAdapterPosition()), ", target = ", Integer.valueOf(viewHolder2.getBindingAdapterPosition()));
            if (this.d == null) {
                LogUtil.h("Suggestion_WeekPlanAdjustHelper", "onMove, mAdapter is null");
                return false;
            }
            int bindingAdapterPosition = viewHolder.getBindingAdapterPosition();
            int bindingAdapterPosition2 = viewHolder2.getBindingAdapterPosition();
            if ((viewHolder instanceof b) && (viewHolder2 instanceof b)) {
                this.d.e(bindingAdapterPosition, bindingAdapterPosition2, (b) viewHolder);
                return true;
            }
            LogUtil.h("Suggestion_WeekPlanAdjustHelper", "source is not CourseScheduleViewHolder");
            return true;
        }

        @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
        public void onSwiped(RecyclerView.ViewHolder viewHolder, int i) {
            LogUtil.a("Suggestion_WeekPlanAdjustHelper", "onSwiped");
        }
    }

    static class a extends RecyclerView.Adapter<c> {

        /* renamed from: a, reason: collision with root package name */
        private List<Long> f3279a;
        private Context b;

        a(Context context, List<Long> list) {
            this.b = context;
            this.f3279a = list;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        /* renamed from: aIS_, reason: merged with bridge method [inline-methods] */
        public c onCreateViewHolder(ViewGroup viewGroup, int i) {
            LogUtil.a("Suggestion_WeekPlanAdjustHelper", "onCreateViewHolder.", Integer.valueOf(i));
            return new c(LayoutInflater.from(this.b).inflate(R.layout.item_date_unit_layout, viewGroup, false));
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public void onBindViewHolder(c cVar, int i) {
            if (koq.b(this.f3279a, i)) {
                LogUtil.b("Suggestion_WeekPlanAdjustHelper", "onBindViewHolder error,", Integer.valueOf(i));
            } else {
                LogUtil.a("Suggestion_WeekPlanAdjustHelper", "onBindViewHolder.", Integer.valueOf(i), " ", this.f3279a.get(i));
                cVar.e(this.f3279a.get(i).longValue());
            }
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemCount() {
            return this.f3279a.size();
        }
    }

    static class c extends RecyclerView.ViewHolder {
        private HealthTextView c;
        private HealthTextView d;

        c(View view) {
            super(view);
            this.d = (HealthTextView) view.findViewById(R.id.week_day_title);
            this.c = (HealthTextView) view.findViewById(R.id.date_title);
            if (nsn.t()) {
                this.d.setTextSize(1, 12.0f);
                this.c.setTextSize(1, 14.0f);
            }
        }

        void e(long j) {
            int[] iArr = {R.string._2130841437_res_0x7f020f5d, R.string._2130841539_res_0x7f020fc3, R.string._2130841558_res_0x7f020fd6, R.string._2130841538_res_0x7f020fc2, R.string._2130841414_res_0x7f020f46, R.string._2130841468_res_0x7f020f7c, R.string._2130841537_res_0x7f020fc1};
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(j);
            int d = gib.d(calendar.get(7));
            if (gib.j(j).equals(gib.j(Calendar.getInstance().getTimeInMillis()))) {
                this.d.setText(BaseApplication.getContext().getString(R.string._2130841407_res_0x7f020f3f));
            } else {
                this.d.setText(BaseApplication.getContext().getString(iArr[d - 1]));
            }
            this.c.setText(String.valueOf(calendar.get(5)));
        }
    }

    static class e {

        /* renamed from: a, reason: collision with root package name */
        private List<String> f3280a;
        private boolean b;
        private long d;
        private String e;

        e(String str, List<String> list, boolean z, long j) {
            this.e = str;
            this.f3280a = list;
            this.b = z;
            this.d = j;
        }
    }

    static class d extends RecyclerView.Adapter<b> {
        private List<e> c;
        private Context e;

        d(Context context, List<e> list) {
            this.e = context;
            this.c = list;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        /* renamed from: aIR_, reason: merged with bridge method [inline-methods] */
        public b onCreateViewHolder(ViewGroup viewGroup, int i) {
            LogUtil.a("Suggestion_WeekPlanAdjustHelper", "onCreateViewHolder.", Integer.valueOf(i));
            return new b(LayoutInflater.from(this.e).inflate(R.layout.item_course_schedule_layout, viewGroup, false));
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        /* renamed from: d, reason: merged with bridge method [inline-methods] */
        public void onBindViewHolder(b bVar, int i) {
            if (!koq.b(this.c, i)) {
                LogUtil.a("Suggestion_WeekPlanAdjustHelper", "onBindViewHolder.", Integer.valueOf(i), " ", this.c.get(i).e);
                bVar.e(this.c.get(i));
            } else {
                LogUtil.b("Suggestion_WeekPlanAdjustHelper", "onBindViewHolder error,", Integer.valueOf(i));
            }
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemCount() {
            return this.c.size();
        }

        public void e(int i, int i2, b bVar) {
            if (bVar == null || koq.b(this.c)) {
                LogUtil.h("Suggestion_WeekPlanAdjustHelper", "itemMove, dragViewHolder == null or CollectionUtils.isEmpty(mCourseNameList)");
                return;
            }
            LogUtil.a("Suggestion_WeekPlanAdjustHelper", "itemMove, fromPosition=", Integer.valueOf(i), ", toPosition =", Integer.valueOf(i2));
            if (i < 0 || i >= this.c.size() || i2 < 0 || i2 >= this.c.size()) {
                return;
            }
            c(i, i2);
        }

        private void c(int i, int i2) {
            if (i < i2) {
                int i3 = i;
                while (i3 < i2) {
                    int i4 = i3 + 1;
                    Collections.swap(this.c, i3, i4);
                    i3 = i4;
                }
            } else {
                int i5 = i;
                int i6 = i5;
                while (i5 > i2) {
                    int i7 = i5 - 1;
                    Collections.swap(this.c, i6, i7);
                    i5--;
                    i6 = i7;
                }
            }
            notifyItemMoved(i, i2);
        }
    }

    static class b extends RecyclerView.ViewHolder {
        private ConstraintLayout b;
        private HealthTextView c;

        b(View view) {
            super(view);
            this.b = (ConstraintLayout) view.findViewById(R.id.course_schedule_layout);
            this.c = (HealthTextView) view.findViewById(R.id.course_item_title);
        }

        void e(e eVar) {
            if (eVar.b) {
                this.b.setBackgroundResource(R.drawable._2131427600_res_0x7f0b0110);
            } else {
                this.b.setBackgroundResource(R.drawable._2131427603_res_0x7f0b0113);
            }
            this.c.setText(eVar.e);
        }
    }
}
