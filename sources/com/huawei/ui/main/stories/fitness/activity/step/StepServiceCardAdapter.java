package com.huawei.ui.main.stories.fitness.activity.step;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.recyclerview.widget.RecyclerView;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.columnsystem.HealthColumnSystem;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.openservice.OpenServiceUtil;
import com.huawei.ui.openservice.db.model.ChildService;
import defpackage.koq;
import defpackage.nrr;
import java.util.List;

/* loaded from: classes6.dex */
public class StepServiceCardAdapter extends RecyclerView.Adapter<c> {

    /* renamed from: a, reason: collision with root package name */
    private OnItemClickListener f9902a;
    private Context b;
    private List<ChildService> c;

    public interface OnItemClickListener {
        void onItemClick(View view, int i);
    }

    public StepServiceCardAdapter(Context context, List<ChildService> list) {
        this.b = context;
        this.c = list;
    }

    public void e(OnItemClickListener onItemClickListener) {
        this.f9902a = onItemClickListener;
    }

    private boolean a() {
        List<ChildService> list = this.c;
        if (list != null && !list.isEmpty()) {
            Bitmap icon = OpenServiceUtil.getIcon(this.b, this.c.get(0).getImageUrl());
            if (icon != null) {
                if (icon.getWidth() == icon.getHeight()) {
                    icon.recycle();
                    return true;
                }
                icon.recycle();
                return false;
            }
            LogUtil.h("ServiceCardAdapter", "icon is null");
        }
        return false;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: e, reason: merged with bridge method [inline-methods] */
    public void onBindViewHolder(final c cVar, int i) {
        if (koq.b(this.c, i)) {
            LogUtil.h("ServiceCardAdapter", "mServices isOutOfBounds!");
            return;
        }
        ChildService childService = this.c.get(i);
        cVar.b.setText(childService.getServiceName());
        Bitmap icon = OpenServiceUtil.getIcon(this.b, childService.getImageUrl());
        if (icon != null) {
            cVar.e.setImageBitmap(icon);
            cVar.c.setImageBitmap(icon);
        }
        if (a()) {
            cVar.b.setVisibility(0);
            cVar.c.setVisibility(0);
            cVar.e.setVisibility(8);
        } else {
            cVar.b.setVisibility(8);
            cVar.c.setVisibility(8);
            cVar.e.setVisibility(0);
        }
        if (cVar.e.getLayoutParams() instanceof LinearLayout.LayoutParams) {
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) cVar.e.getLayoutParams();
            layoutParams.width = (int) new HealthColumnSystem(this.b, 1).d(2);
            layoutParams.setMarginEnd(nrr.b(this.b));
            cVar.e.setLayoutParams(layoutParams);
        }
        if (this.f9902a != null) {
            cVar.itemView.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.ui.main.stories.fitness.activity.step.StepServiceCardAdapter.5
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    StepServiceCardAdapter.this.f9902a.onItemClick(cVar.itemView, cVar.getLayoutPosition());
                    ViewClickInstrumentation.clickOnView(view);
                }
            });
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: duj_, reason: merged with bridge method [inline-methods] */
    public c onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new c(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.step_service_recyclerview_item, viewGroup, false));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        List<ChildService> list = this.c;
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    class c extends RecyclerView.ViewHolder {
        HealthTextView b;
        ImageView c;
        ImageView e;

        c(View view) {
            super(view);
            this.e = (ImageView) view.findViewById(R.id.service_icon);
            this.c = (ImageView) view.findViewById(R.id.service_icon_square);
            this.b = (HealthTextView) view.findViewById(R.id.service_name);
        }
    }
}
