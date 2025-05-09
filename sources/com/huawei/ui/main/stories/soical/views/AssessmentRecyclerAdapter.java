package com.huawei.ui.main.stories.soical.views;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.huawei.health.R;
import com.huawei.health.marketing.datatype.SingleGridContent;
import com.huawei.ui.commonui.adapter.BaseRecyclerAdapter;
import com.huawei.ui.commonui.adapter.RecyclerHolder;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import java.util.List;

/* loaded from: classes8.dex */
public class AssessmentRecyclerAdapter extends BaseRecyclerAdapter<SingleGridContent> {

    /* renamed from: a, reason: collision with root package name */
    private OnItemVisibleListener<RecyclerHolder, SingleGridContent> f10499a;
    private int b;
    private int c;

    public AssessmentRecyclerAdapter(List<SingleGridContent> list) {
        super(list, R.layout.item_assessment_health);
        this.c = -1;
        this.b = -2;
    }

    public void e(int i, int i2) {
        if (i == this.c && i2 == this.b) {
            return;
        }
        this.c = i;
        this.b = i2;
        notifyDataSetChanged();
    }

    @Override // com.huawei.ui.commonui.adapter.BaseRecyclerAdapter
    /* renamed from: d, reason: merged with bridge method [inline-methods] */
    public void convert(RecyclerHolder recyclerHolder, int i, SingleGridContent singleGridContent) {
        if (singleGridContent == null) {
            notifyItemRemoved(i);
            notifyItemRangeChanged(i, getItemCount());
            return;
        }
        ViewGroup.LayoutParams layoutParams = recyclerHolder.itemView.getLayoutParams();
        if (layoutParams != null) {
            layoutParams.width = this.c;
            layoutParams.height = this.b;
            recyclerHolder.itemView.setLayoutParams(layoutParams);
        }
        ViewGroup.LayoutParams layoutParams2 = recyclerHolder.itemView.findViewById(R.id.assessment_dataLayout).getLayoutParams();
        if (layoutParams2 != null) {
            layoutParams2.width = -1;
            layoutParams2.height = this.b / 3;
            recyclerHolder.itemView.findViewById(R.id.assessment_dataLayout).setLayoutParams(layoutParams2);
        }
        ImageView imageView = (ImageView) recyclerHolder.cwK_(R.id.assessment_themeImg);
        Context context = recyclerHolder.itemView.getContext();
        Glide.with(context).load(singleGridContent.getDetailPicture()).into(imageView);
        ((HealthTextView) recyclerHolder.cwK_(R.id.assessment_questionQuantityTxt)).setText(context.getResources().getQuantityString(R.plurals._2130903436_res_0x7f03018c, singleGridContent.getQuestionNumber(), Integer.valueOf(singleGridContent.getQuestionNumber())));
        HealthTextView healthTextView = (HealthTextView) recyclerHolder.cwK_(R.id.assessment_personQuantityTxt);
        healthTextView.setVisibility(singleGridContent.getParticipantCount() > 0 ? 0 : 8);
        healthTextView.setText(context.getResources().getQuantityString(R.plurals._2130903437_res_0x7f03018d, singleGridContent.getParticipantCount() <= 1 ? 1 : Integer.MAX_VALUE, String.valueOf(singleGridContent.getParticipantCount())));
        ((HealthTextView) recyclerHolder.cwK_(R.id.assessment_timeTakeTxt)).setText(context.getResources().getQuantityString(R.plurals._2130903232_res_0x7f0300c0, singleGridContent.getCostTime(), Integer.valueOf(singleGridContent.getCostTime())));
        recyclerHolder.itemView.setTag(R.layout.item_assessment_health, Long.valueOf(System.currentTimeMillis()));
        OnItemVisibleListener<RecyclerHolder, SingleGridContent> onItemVisibleListener = this.f10499a;
        if (onItemVisibleListener != null) {
            onItemVisibleListener.onItemVisible(recyclerHolder, i, singleGridContent);
        }
    }

    public void a(OnItemVisibleListener<RecyclerHolder, SingleGridContent> onItemVisibleListener) {
        this.f10499a = onItemVisibleListener;
    }
}
