package com.huawei.health.suggestion.ui.plan.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import androidx.core.content.ContextCompat;
import com.huawei.basefitnessadvice.model.intplan.IntAction;
import com.huawei.haf.application.BaseApplication;
import com.huawei.health.R;
import com.huawei.health.suggestion.ui.plan.adapter.CourseAdapter;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.pluginfitnessadvice.FitWorkout;
import com.huawei.ui.commonui.adapter.BaseRecyclerAdapter;
import com.huawei.ui.commonui.adapter.RecyclerHolder;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import defpackage.ffy;
import defpackage.ggm;
import defpackage.gic;
import defpackage.koq;
import defpackage.mnt;
import defpackage.moe;
import defpackage.mwt;
import defpackage.nrf;
import defpackage.nsf;
import health.compact.a.ReleaseLogUtil;
import health.compact.a.UnitUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes4.dex */
public class CourseAdapter extends BaseRecyclerAdapter<IntAction> {

    /* renamed from: a, reason: collision with root package name */
    private final boolean f3241a;
    private Drawable b;
    private final Context c;
    private boolean d;
    private boolean e;
    private List<IntAction> f;
    private int g;
    private Map<String, FitWorkout> h;
    private OnItemClickListener i;
    private final int j;
    private float k;

    public interface OnItemClickListener {
        void onCourseClick(boolean z, int i, int i2, IntAction intAction);

        void onCourseRecordClick(boolean z, int i, int i2, IntAction intAction);

        void onCourseStatusClick(boolean z, int i, int i2, IntAction intAction);

        void onLongClick(int i, int i2, IntAction intAction);
    }

    public CourseAdapter(boolean z) {
        super(new ArrayList(), R.layout.item_recycler_course);
        Context e = BaseApplication.e();
        this.c = e;
        this.f = new ArrayList();
        this.h = new ConcurrentHashMap();
        this.f3241a = z;
        this.j = (int) e.getResources().getDimension(R.dimen._2131362360_res_0x7f0a0238);
        if (mwt.d().getModelType() != -1) {
            this.b = ContextCompat.getDrawable(e, R.drawable._2131430242_res_0x7f0b0b62);
        }
    }

    public void c(Map<String, FitWorkout> map, List<IntAction> list, boolean z, int i, float f) {
        this.h = map;
        this.f = list;
        this.g = i;
        this.k = f;
        this.e = z;
        refreshDataChange(list);
    }

    public void d(boolean z) {
        this.d = z;
        refreshDataChange(this.f);
    }

    @Override // com.huawei.ui.commonui.adapter.BaseRecyclerAdapter, androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        if (koq.b(this.f)) {
            return 0;
        }
        return this.f.size();
    }

    @Override // com.huawei.ui.commonui.adapter.BaseRecyclerAdapter
    /* renamed from: e, reason: merged with bridge method [inline-methods] */
    public void convert(RecyclerHolder recyclerHolder, int i, IntAction intAction) {
        if (recyclerHolder == null || intAction == null || this.h == null) {
            ReleaseLogUtil.a("CourseAdapter", "convert holder ", recyclerHolder, " itemData ", intAction, " position ", Integer.valueOf(i), " mMap ", this.h);
            return;
        }
        String actionId = intAction.getActionId();
        if (TextUtils.isEmpty(actionId)) {
            ReleaseLogUtil.a("CourseAdapter", "convert actionId ", actionId);
            return;
        }
        FitWorkout fitWorkout = this.h.get(actionId);
        if (fitWorkout == null) {
            ReleaseLogUtil.a("CourseAdapter", "convert fitWorkout is null mMap ", this.h);
            return;
        }
        e(recyclerHolder, intAction, fitWorkout, i);
        d(recyclerHolder, intAction, fitWorkout, i);
        a(recyclerHolder, intAction, fitWorkout, i);
        b(recyclerHolder, intAction, fitWorkout, i);
        e(recyclerHolder, intAction, i);
        aGv_(recyclerHolder.itemView, intAction, i, this.g);
        setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener() { // from class: ftr
            @Override // com.huawei.ui.commonui.adapter.BaseRecyclerAdapter.OnItemClickListener
            public final void onItemClicked(RecyclerHolder recyclerHolder2, int i2, Object obj) {
                CourseAdapter.this.a(recyclerHolder2, i2, obj);
            }
        });
        if (this.f3241a) {
            recyclerHolder.a(R.id.divider, i >= getItemCount() + (-1) ? 8 : 0);
            return;
        }
        if (i >= getItemCount() - 1 && !this.d) {
            r1 = 8;
        }
        recyclerHolder.a(R.id.divider, r1);
    }

    public /* synthetic */ void a(RecyclerHolder recyclerHolder, int i, Object obj) {
        OnItemClickListener onItemClickListener = this.i;
        if (onItemClickListener == null || !(obj instanceof IntAction)) {
            ReleaseLogUtil.a("CourseAdapter", "convert mOnItemClickListener ", onItemClickListener, " object ", obj);
        } else {
            onItemClickListener.onCourseClick(this.f3241a, this.g, i, (IntAction) obj);
        }
    }

    private void e(RecyclerHolder recyclerHolder, IntAction intAction, FitWorkout fitWorkout, int i) {
        ImageView imageView = (ImageView) recyclerHolder.cwK_(R.id.course_icon);
        if (imageView == null) {
            ReleaseLogUtil.a("CourseAdapter", "setIcon imageView is null");
            return;
        }
        String topicPreviewPicUrl = fitWorkout.getTopicPreviewPicUrl();
        if (TextUtils.isEmpty(topicPreviewPicUrl)) {
            ReleaseLogUtil.a("CourseAdapter", "setIcon url ", topicPreviewPicUrl);
            topicPreviewPicUrl = fitWorkout.getSupplierLogoUrl();
        }
        if (TextUtils.isEmpty(topicPreviewPicUrl)) {
            mnt actionInfo = intAction.getActionInfo();
            ReleaseLogUtil.a("CourseAdapter", "setIcon url ", topicPreviewPicUrl, " courseInfoBean ", actionInfo);
            if (actionInfo != null) {
                topicPreviewPicUrl = actionInfo.e();
            }
        }
        nrf.cIS_(imageView, topicPreviewPicUrl, this.j, 0, R.drawable._2131429871_res_0x7f0b09ef);
        aGv_(imageView, intAction, i, this.g);
    }

    private void d(RecyclerHolder recyclerHolder, IntAction intAction, FitWorkout fitWorkout, int i) {
        HealthTextView healthTextView = (HealthTextView) recyclerHolder.cwK_(R.id.course_name);
        if (healthTextView == null) {
            ReleaseLogUtil.a("CourseAdapter", "setName imageView is null");
            return;
        }
        String acquireName = fitWorkout.acquireName();
        if (TextUtils.isEmpty(acquireName)) {
            ReleaseLogUtil.a("CourseAdapter", "setName name ", acquireName);
            healthTextView.setVisibility(8);
            return;
        }
        healthTextView.setVisibility(0);
        healthTextView.setText(acquireName);
        aGv_(healthTextView, intAction, i, this.g);
        int isAi = fitWorkout.getIsAi();
        ReleaseLogUtil.b("CourseAdapter", "setName ai ", Integer.valueOf(isAi));
        if (isAi != 1) {
            healthTextView.setCompoundDrawablesWithIntrinsicBounds((Drawable) null, (Drawable) null, (Drawable) null, (Drawable) null);
            return;
        }
        Drawable drawable = this.b;
        if (drawable == null) {
            ReleaseLogUtil.a("CourseAdapter", "setName mAiDrawable is null");
            healthTextView.setCompoundDrawablesWithIntrinsicBounds((Drawable) null, (Drawable) null, (Drawable) null, (Drawable) null);
        } else {
            drawable.setBounds(0, 0, (int) UnitUtil.a(drawable.getIntrinsicWidth() * 0.8d, 0), (int) UnitUtil.a(this.b.getIntrinsicHeight() * 0.8d, 0));
            healthTextView.setCompoundDrawables(null, null, this.b, null);
            healthTextView.setCompoundDrawablePadding(this.c.getResources().getDimensionPixelSize(R.dimen._2131363122_res_0x7f0a0532));
        }
    }

    private void a(RecyclerHolder recyclerHolder, IntAction intAction, FitWorkout fitWorkout, int i) {
        HealthTextView healthTextView = (HealthTextView) recyclerHolder.cwK_(R.id.course_info);
        if (healthTextView == null) {
            ReleaseLogUtil.a("CourseAdapter", "setInfo textView is null");
            return;
        }
        float acquireDuration = fitWorkout.acquireDuration();
        float b = moe.b(fitWorkout.acquireCalorie() * this.k);
        if (acquireDuration <= 0.0f || b <= 0.0f) {
            healthTextView.setVisibility(8);
            ReleaseLogUtil.a("CourseAdapter", "setInfo duration ", Float.valueOf(acquireDuration), " kiloCalorie ", Float.valueOf(b));
        } else {
            healthTextView.setVisibility(0);
            healthTextView.setText(nsf.b(R.string._2130848795_res_0x7f022c1b, ggm.d(fitWorkout.acquireDifficulty()), ffy.b(R.plurals._2130903305_res_0x7f030109, UnitUtil.e(UnitUtil.a(acquireDuration / 60.0f, 0)), gic.e(acquireDuration)), ffy.b(R.plurals._2130903474_res_0x7f0301b2, UnitUtil.e(b), moe.i(b))));
            aGv_(healthTextView, intAction, i, this.g);
        }
    }

    private void b(RecyclerHolder recyclerHolder, final IntAction intAction, FitWorkout fitWorkout, final int i) {
        HealthTextView healthTextView = (HealthTextView) recyclerHolder.cwK_(R.id.course_exercises);
        if (healthTextView == null) {
            ReleaseLogUtil.a("CourseAdapter", "setExercises textView is null");
            return;
        }
        if (intAction.getPunchFlag() == 1) {
            healthTextView.setAlpha(1.0f);
            healthTextView.setText(R.string._2130848700_res_0x7f022bbc);
            healthTextView.setTextColor(ContextCompat.getColor(this.c, R.color._2131296651_res_0x7f09018b));
            healthTextView.setOnClickListener(new View.OnClickListener() { // from class: ftp
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    CourseAdapter.this.aGx_(i, intAction, view);
                }
            });
            return;
        }
        int acquireUsers = fitWorkout.acquireUsers();
        healthTextView.setText(ffy.b(R.plurals._2130903040_res_0x7f030000, acquireUsers, UnitUtil.e(acquireUsers, 1, 0)));
        healthTextView.setTextColor(ContextCompat.getColor(this.c, R.color._2131299241_res_0x7f090ba9));
        healthTextView.setAlpha(0.8f);
        aGv_(healthTextView, intAction, i, this.g);
    }

    public /* synthetic */ void aGx_(int i, IntAction intAction, View view) {
        OnItemClickListener onItemClickListener = this.i;
        if (onItemClickListener == null) {
            ReleaseLogUtil.a("CourseAdapter", "setExercises mOnItemClickListener is null");
            ViewClickInstrumentation.clickOnView(view);
        } else {
            onItemClickListener.onCourseRecordClick(this.f3241a, this.g, i, intAction);
            ViewClickInstrumentation.clickOnView(view);
        }
    }

    private void e(RecyclerHolder recyclerHolder, IntAction intAction, int i) {
        ImageView imageView = (ImageView) recyclerHolder.cwK_(R.id.course_status);
        if (imageView == null) {
            ReleaseLogUtil.a("CourseAdapter", "setStatus textView is null");
            return;
        }
        if (this.g == 3) {
            if (this.e) {
                aGw_(imageView, intAction, i);
                return;
            } else {
                imageView.setImageResource(R.drawable._2131430559_res_0x7f0b0c9f);
                aGv_(imageView, intAction, i, this.g);
                return;
            }
        }
        if (intAction.getPunchFlag() == 1) {
            imageView.setImageResource(R.drawable._2131430245_res_0x7f0b0b65);
            aGv_(imageView, intAction, i, this.g);
        } else if (this.g == 0) {
            imageView.setImageResource(R.drawable._2131430247_res_0x7f0b0b67);
            aGv_(imageView, intAction, i, this.g);
        } else {
            aGw_(imageView, intAction, i);
        }
    }

    private void aGw_(ImageView imageView, final IntAction intAction, final int i) {
        if (this.f3241a) {
            imageView.setImageResource(R.drawable._2131430251_res_0x7f0b0b6b);
            imageView.setOnClickListener(new View.OnClickListener() { // from class: ftn
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    CourseAdapter.this.aGA_(i, intAction, view);
                }
            });
        } else {
            imageView.setImageResource(this.g == 3 ? R.drawable._2131430559_res_0x7f0b0c9f : R.drawable._2131430247_res_0x7f0b0b67);
            aGv_(imageView, intAction, i, this.g);
        }
    }

    public /* synthetic */ void aGA_(int i, IntAction intAction, View view) {
        OnItemClickListener onItemClickListener = this.i;
        if (onItemClickListener == null) {
            ReleaseLogUtil.a("CourseAdapter", "setStatus mOnItemClickListener is null");
            ViewClickInstrumentation.clickOnView(view);
        } else {
            onItemClickListener.onCourseStatusClick(true, this.g, i, intAction);
            ViewClickInstrumentation.clickOnView(view);
        }
    }

    private void aGv_(View view, final IntAction intAction, final int i, final int i2) {
        view.setOnClickListener(new View.OnClickListener() { // from class: ftl
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                CourseAdapter.this.aGy_(i2, i, intAction, view2);
            }
        });
        if (this.f3241a) {
            return;
        }
        view.setOnLongClickListener(new View.OnLongClickListener() { // from class: ftq
            @Override // android.view.View.OnLongClickListener
            public final boolean onLongClick(View view2) {
                return CourseAdapter.this.aGz_(i2, i, intAction, view2);
            }
        });
    }

    public /* synthetic */ void aGy_(int i, int i2, IntAction intAction, View view) {
        OnItemClickListener onItemClickListener = this.i;
        if (onItemClickListener == null) {
            ReleaseLogUtil.a("CourseAdapter", "setOnCourseClick mOnItemClickListener is null");
            ViewClickInstrumentation.clickOnView(view);
        } else {
            onItemClickListener.onCourseClick(this.f3241a, i, i2, intAction);
            ViewClickInstrumentation.clickOnView(view);
        }
    }

    public /* synthetic */ boolean aGz_(int i, int i2, IntAction intAction, View view) {
        OnItemClickListener onItemClickListener = this.i;
        if (onItemClickListener == null) {
            return true;
        }
        onItemClickListener.onLongClick(i, i2, intAction);
        return true;
    }

    public void b(OnItemClickListener onItemClickListener) {
        this.i = onItemClickListener;
    }
}
