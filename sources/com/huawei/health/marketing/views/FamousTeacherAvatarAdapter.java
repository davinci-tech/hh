package com.huawei.health.marketing.views;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.huawei.haf.application.BaseApplication;
import com.huawei.health.R;
import com.huawei.health.marketing.datatype.templates.SingleFamousTeacherStandardContent;
import com.huawei.health.marketing.views.FamousTeacherAvatarAdapter;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.imageview.HealthImageView;
import defpackage.jcf;
import defpackage.koq;
import defpackage.nrf;
import defpackage.nrt;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes3.dex */
public class FamousTeacherAvatarAdapter extends RecyclerView.Adapter<ViewHolder> {

    /* renamed from: a, reason: collision with root package name */
    private List<SingleFamousTeacherStandardContent> f2866a;
    private ViewHolder b;
    private boolean c;
    private onItemClickListener d;
    private Context e;
    private int f;

    public interface onItemClickListener {
        void onItemClick(ViewHolder viewHolder, SingleFamousTeacherStandardContent singleFamousTeacherStandardContent, int i, int i2);
    }

    public FamousTeacherAvatarAdapter(Context context, List<SingleFamousTeacherStandardContent> list) {
        new ArrayList();
        this.e = context;
        this.f2866a = list;
        this.c = nrt.a(BaseApplication.e());
    }

    public ViewHolder d() {
        return this.b;
    }

    public void d(List<SingleFamousTeacherStandardContent> list) {
        this.f2866a = list;
        notifyDataSetChanged();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: apf_, reason: merged with bridge method [inline-methods] */
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(this.e).inflate(R.layout.item_famous_teacher_head, viewGroup, false));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: c, reason: merged with bridge method [inline-methods] */
    public void onBindViewHolder(final ViewHolder viewHolder, final int i) {
        if (koq.b(this.f2866a, i)) {
            LogUtil.h("FamousTeacherAvatarAdapter", "mSingleArticleContent isOutOfBounds");
            return;
        }
        final SingleFamousTeacherStandardContent singleFamousTeacherStandardContent = this.f2866a.get(i);
        nrf.b(singleFamousTeacherStandardContent.getHeadPicture(), viewHolder.d, (int) BaseApplication.e().getResources().getDimension(R.dimen._2131362954_res_0x7f0a048a));
        if (i == this.f) {
            viewHolder.d.setImageAlpha(255);
            viewHolder.d.setBackgroundResource(R.mipmap._2131820983_res_0x7f1101b7);
            jcf.bEJ_(viewHolder.d, singleFamousTeacherStandardContent.getTabName(), true);
            this.b = viewHolder;
        } else {
            viewHolder.d.setImageAlpha(102);
            viewHolder.d.setBackgroundResource(R.drawable._2131428142_res_0x7f0b032e);
            jcf.bEJ_(viewHolder.d, singleFamousTeacherStandardContent.getTabName(), false);
        }
        if (this.c) {
            viewHolder.d.getBackground().setAlpha(102);
        } else {
            viewHolder.d.getBackground().setAlpha(255);
        }
        viewHolder.d.setOnClickListener(new View.OnClickListener() { // from class: eju
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                FamousTeacherAvatarAdapter.this.ape_(i, viewHolder, singleFamousTeacherStandardContent, view);
            }
        });
    }

    public /* synthetic */ void ape_(int i, ViewHolder viewHolder, SingleFamousTeacherStandardContent singleFamousTeacherStandardContent, View view) {
        int i2 = this.f;
        this.f = i;
        onItemClickListener onitemclicklistener = this.d;
        if (onitemclicklistener != null) {
            onitemclicklistener.onItemClick(viewHolder, singleFamousTeacherStandardContent, i2, i);
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.f2866a.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private HealthImageView d;

        ViewHolder(View view) {
            super(view);
            this.d = (HealthImageView) view.findViewById(R.id.account_head_picture);
        }
    }

    public void e(onItemClickListener onitemclicklistener) {
        this.d = onitemclicklistener;
    }
}
