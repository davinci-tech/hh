package com.huawei.health.section.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.huawei.health.R;
import com.huawei.health.section.adapter.SectionX_YList_01Adapter;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.ui.commonui.imageview.HealthImageView;
import defpackage.eeh;
import defpackage.eet;

/* loaded from: classes3.dex */
public class Section1_1List_01Adapter extends SectionX_YList_01Adapter {
    public Section1_1List_01Adapter(Context context, eeh eehVar) {
        super(context, eehVar);
    }

    @Override // com.huawei.health.section.adapter.SectionX_YList_01Adapter, androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: avJ_, reason: merged with bridge method [inline-methods] */
    public d onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new d(LayoutInflater.from(this.mContext).inflate(i != 2 ? R.layout.section1_1list_01_item : R.layout.sectionx_ylist_02_item, viewGroup, false), i);
    }

    @Override // com.huawei.health.section.adapter.SectionX_YList_01Adapter, androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(SectionX_YList_01Adapter.b bVar, int i) {
        this.viewHolder = bVar;
        if (bVar.h != null) {
            c(bVar, i);
        }
    }

    private void c(SectionX_YList_01Adapter.b bVar, int i) {
        if (getItemViewType(i) == 2) {
            b(bVar, i);
        } else {
            a(bVar, i);
        }
    }

    private void a(SectionX_YList_01Adapter.b bVar, int i) {
        ViewGroup.LayoutParams layoutParams = bVar.h.getLayoutParams();
        if (getItemCount() == 1) {
            layoutParams.width = -1;
        } else {
            layoutParams.width = this.mContext.getResources().getDimensionPixelSize(R.dimen._2131363005_res_0x7f0a04bd);
        }
        bVar.h.setLayoutParams(layoutParams);
        setItemImage(bVar, i);
        e(bVar, i);
    }

    private void e(SectionX_YList_01Adapter.b bVar, int i) {
        if (eet.b(this.nameList, i) && bVar.j != null) {
            bVar.j.setText(this.nameList.get(i));
        }
        if (eet.b(this.difficultyList, i) && bVar.e != null) {
            bVar.e.setText(this.difficultyList.get(i));
        }
        if (eet.b(this.intervalList, i) && bVar.g != null) {
            bVar.g.setText(this.intervalList.get(i));
        }
        if (eet.b(this.durationList, i) && bVar.f2971a != null) {
            bVar.f2971a.setText(this.durationList.get(i));
        }
        bindHolderTag(bVar, i);
        setItemClickListener(bVar, i);
    }

    private void b(SectionX_YList_01Adapter.b bVar, int i) {
        BITMAP_DEFAULT_CORNER = (int) BaseApplication.getContext().getResources().getDimension(R.dimen._2131362886_res_0x7f0a0446);
        setItemImage(bVar, i);
        if (eet.b(this.nameList, i) && bVar.j != null) {
            bVar.j.setText(this.nameList.get(i));
        }
        bindHolderTag(bVar, i);
        setItemClickListener(bVar, i);
    }

    @Override // com.huawei.health.section.adapter.SectionX_YList_01Adapter, androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemViewType(int i) {
        return getItemCount() > 3 ? 2 : 1;
    }

    static class d extends SectionX_YList_01Adapter.b {
        public d(View view, int i) {
            super(view);
            if (i == 2) {
                e();
            } else {
                b();
            }
        }

        private void b() {
            this.h = (RelativeLayout) this.itemView.findViewById(R.id.recycle_item);
            this.d = (HealthImageView) this.itemView.findViewById(R.id.item_picture);
            this.j = (TextView) this.itemView.findViewById(R.id.item_title);
            this.g = (TextView) this.itemView.findViewById(R.id.item_interval);
            this.e = (TextView) this.itemView.findViewById(R.id.item_difficulty);
            this.f2971a = (TextView) this.itemView.findViewById(R.id.item_duration);
            this.i = (TextView) this.itemView.findViewById(R.id.sectionX_Ylist_course_tag);
        }

        private void e() {
            this.h = (RelativeLayout) this.itemView.findViewById(R.id.recycle_item);
            this.d = (HealthImageView) this.itemView.findViewById(R.id.item_picture);
            this.j = (TextView) this.itemView.findViewById(R.id.item_title);
            this.i = (TextView) this.itemView.findViewById(R.id.sectionX_Ylist_course_tag);
        }
    }
}
