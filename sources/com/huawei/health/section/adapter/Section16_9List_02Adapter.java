package com.huawei.health.section.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.huawei.health.R;
import com.huawei.health.section.adapter.SectionX_YList_01Adapter;
import com.huawei.ui.commonui.imageview.HealthImageView;
import defpackage.eeh;
import defpackage.nsn;

/* loaded from: classes3.dex */
public class Section16_9List_02Adapter extends SectionX_YList_01Adapter {
    public Section16_9List_02Adapter(Context context, eeh eehVar) {
        super(context, eehVar);
    }

    private void avG_(ViewGroup.LayoutParams layoutParams) {
        layoutParams.width = this.mContext.getResources().getDimensionPixelSize(R.dimen._2131362863_res_0x7f0a042f);
        layoutParams.height = this.mContext.getResources().getDimensionPixelSize(R.dimen._2131363076_res_0x7f0a0504);
    }

    private void avH_(ViewGroup.LayoutParams layoutParams) {
        layoutParams.width = this.mContext.getResources().getDimensionPixelSize(R.dimen._2131362863_res_0x7f0a042f);
        layoutParams.height = this.mContext.getResources().getDimensionPixelSize(R.dimen._2131363076_res_0x7f0a0504);
    }

    @Override // com.huawei.health.section.adapter.SectionX_YList_01Adapter, androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: avI_, reason: merged with bridge method [inline-methods] */
    public b onCreateViewHolder(ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(this.mContext).inflate(R.layout.sectionx_ylist_01_item, viewGroup, false);
        avH_(((RelativeLayout) inflate.findViewById(R.id.section1_1list_item_picture_layout)).getLayoutParams());
        avG_(((ImageView) inflate.findViewById(R.id.item_picture)).getLayoutParams());
        ((HealthImageView) inflate.findViewById(R.id.img_arrow)).setVisibility(0);
        return new b(inflate);
    }

    @Override // com.huawei.health.section.adapter.SectionX_YList_01Adapter, androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(SectionX_YList_01Adapter.b bVar, int i) {
        this.viewHolder = bVar;
        if (i == getItemCount() - 1) {
            setDividerViewVisibility(8);
        } else if (this.mContext != null && nsn.ag(this.mContext) && i == getItemCount() - 2) {
            setDividerViewVisibility(8);
        } else {
            setDividerViewVisibility(0);
        }
        setDataAndRefresh(bVar, i);
    }

    static class b extends SectionX_YList_01Adapter.b {
        public b(View view) {
            super(view);
            this.h = (RelativeLayout) view.findViewById(R.id.recycle_item);
            this.d = (HealthImageView) view.findViewById(R.id.item_picture);
            this.j = (TextView) view.findViewById(R.id.item_title);
            this.g = (TextView) view.findViewById(R.id.item_interval);
            this.e = (TextView) view.findViewById(R.id.item_difficulty);
            this.f2971a = (TextView) view.findViewById(R.id.item_duration);
            this.i = (TextView) view.findViewById(R.id.sectionX_Ylist_course_tag);
            this.c = (LinearLayout) view.findViewById(R.id.item_divider);
            this.b = (RelativeLayout) view.findViewById(R.id.section1_1list_item_picture_layout);
        }
    }
}
