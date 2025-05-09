package com.huawei.health.suggestion.customizecourse.manager.adapter;

import android.content.Context;
import android.os.Vibrator;
import androidx.recyclerview.widget.RecyclerView;
import com.huawei.health.suggestion.customizecourse.manager.adapter.viewholder.CustomCourseDragViewHolder;

/* loaded from: classes4.dex */
public abstract class CustomCourseCanDragAdapter extends RecyclerView.Adapter<CustomCourseDragViewHolder> {
    private static final int MILLISECONDS_VIBRATOR = 200;
    private Vibrator mVibrator;

    public abstract void itemMove(int i, int i2, CustomCourseDragViewHolder customCourseDragViewHolder);

    public abstract void setDragState(CustomCourseDragViewHolder customCourseDragViewHolder, boolean z);

    public void startVibrator(RecyclerView.ViewHolder viewHolder) {
        Context context;
        if (viewHolder == null) {
            return;
        }
        if (this.mVibrator == null && (context = viewHolder.itemView.getContext()) != null && (context.getSystemService("vibrator") instanceof Vibrator)) {
            this.mVibrator = (Vibrator) context.getSystemService("vibrator");
        }
        Vibrator vibrator = this.mVibrator;
        if (vibrator != null) {
            vibrator.vibrate(200L);
        }
    }

    public void releaseVibrator() {
        Vibrator vibrator = this.mVibrator;
        if (vibrator != null) {
            vibrator.cancel();
            this.mVibrator = null;
        }
    }
}
