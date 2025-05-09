package com.huawei.health.suggestion.customizecourse.manager.adapter;

import android.animation.Animator;
import android.animation.ValueAnimator;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;
import com.huawei.health.suggestion.customizecourse.manager.adapter.viewholder.CustomCourseDragViewHolder;
import com.huawei.hwlogsmodel.LogUtil;

/* loaded from: classes4.dex */
public class CustomCourseTouchHelperCallBack extends ItemTouchHelper.Callback {
    private final CustomCourseCanDragAdapter d;

    @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
    public boolean isItemViewSwipeEnabled() {
        return false;
    }

    @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
    public boolean isLongPressDragEnabled() {
        return false;
    }

    public CustomCourseTouchHelperCallBack(CustomCourseCanDragAdapter customCourseCanDragAdapter) {
        this.d = customCourseCanDragAdapter;
    }

    @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int i) {
        LogUtil.a("Suggestion_CustomCourseTouchHelperCallBack", "onSwiped");
        if (this.d == null) {
            LogUtil.h("Suggestion_CustomCourseTouchHelperCallBack", "mAdapter is null");
        }
    }

    @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        if (!(viewHolder instanceof CustomCourseDragViewHolder) || !((CustomCourseDragViewHolder) viewHolder).isIsCanDrag()) {
            LogUtil.a("Suggestion_CustomCourseTouchHelperCallBack", "getMovementFlags, viewHolder isIsCanDrag = ", Boolean.valueOf(((CustomCourseDragViewHolder) viewHolder).isIsCanDrag()));
            return 0;
        }
        return makeMovementFlags(3, 0);
    }

    @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder viewHolder2) {
        if (viewHolder == null || viewHolder2 == null) {
            LogUtil.h("Suggestion_CustomCourseTouchHelperCallBack", "onMove, source or target is null");
            return false;
        }
        LogUtil.a("Suggestion_CustomCourseTouchHelperCallBack", "onMove, source = ", Integer.valueOf(viewHolder.getAdapterPosition()), ", target = ", Integer.valueOf(viewHolder2.getAdapterPosition()));
        if (this.d == null) {
            LogUtil.h("Suggestion_CustomCourseTouchHelperCallBack", "onMove, mAdapter is null");
            return false;
        }
        int adapterPosition = viewHolder.getAdapterPosition();
        int adapterPosition2 = viewHolder2.getAdapterPosition();
        if (viewHolder instanceof CustomCourseDragViewHolder) {
            CustomCourseDragViewHolder customCourseDragViewHolder = (CustomCourseDragViewHolder) viewHolder;
            LogUtil.a("Suggestion_CustomCourseTouchHelperCallBack", "onMove, source isIsCanDrag = ", Boolean.valueOf(customCourseDragViewHolder.isIsCanDrag()));
            if (!customCourseDragViewHolder.isIsCanDrag()) {
                return false;
            }
            this.d.itemMove(adapterPosition, adapterPosition2, customCourseDragViewHolder);
            return true;
        }
        LogUtil.h("Suggestion_CustomCourseTouchHelperCallBack", "source is not CustomCourseMoveViewHolder");
        return true;
    }

    @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
    public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int i) {
        if (viewHolder == null) {
            LogUtil.h("Suggestion_CustomCourseTouchHelperCallBack", "onSelectedChanged, viewHolder is null");
            return;
        }
        LogUtil.a("Suggestion_CustomCourseTouchHelperCallBack", "onSelectedChanged");
        if (i != 0) {
            this.d.startVibrator(viewHolder);
            d(viewHolder, 1.0f, 0.85f, 280L, true);
        }
        super.onSelectedChanged(viewHolder, i);
    }

    @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
    public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        if (viewHolder == null) {
            LogUtil.h("Suggestion_CustomCourseTouchHelperCallBack", "clearView, viewHolder is null");
            return;
        }
        LogUtil.a("Suggestion_CustomCourseTouchHelperCallBack", "clearView");
        super.clearView(recyclerView, viewHolder);
        d(viewHolder, 0.85f, 1.0f, 280L, false);
    }

    private void d(final RecyclerView.ViewHolder viewHolder, float f, float f2, long j, final boolean z) {
        ValueAnimator ofFloat = ValueAnimator.ofFloat(f, f2);
        ofFloat.setDuration(j);
        ofFloat.setStartDelay(0L);
        ofFloat.setRepeatCount(0);
        ofFloat.setRepeatMode(1);
        ofFloat.addUpdateListener(new e(viewHolder));
        ofFloat.addListener(new Animator.AnimatorListener() { // from class: com.huawei.health.suggestion.customizecourse.manager.adapter.CustomCourseTouchHelperCallBack.3
            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationCancel(Animator animator) {
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationRepeat(Animator animator) {
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationStart(Animator animator) {
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                if (viewHolder instanceof CustomCourseDragViewHolder) {
                    CustomCourseTouchHelperCallBack.this.d.setDragState((CustomCourseDragViewHolder) viewHolder, z);
                }
            }
        });
        ofFloat.start();
    }

    static final class e implements ValueAnimator.AnimatorUpdateListener {
        RecyclerView.ViewHolder d;

        e(RecyclerView.ViewHolder viewHolder) {
            this.d = viewHolder;
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            if (valueAnimator == null) {
                LogUtil.h("Suggestion_CustomCourseTouchHelperCallBack", "onAnimationUpdate, animation is null");
                return;
            }
            float floatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
            this.d.itemView.setScaleX(floatValue);
            this.d.itemView.setScaleY(floatValue);
        }
    }
}
