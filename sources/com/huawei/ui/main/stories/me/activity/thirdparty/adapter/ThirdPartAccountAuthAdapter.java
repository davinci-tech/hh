package com.huawei.ui.main.stories.me.activity.thirdparty.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.checkbox.HealthCheckBox;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.subheader.HealthSubHeader;
import com.huawei.ui.main.R$string;
import defpackage.koq;
import defpackage.nrf;
import defpackage.rhb;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes8.dex */
public class ThirdPartAccountAuthAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    /* renamed from: a, reason: collision with root package name */
    private List<Boolean> f10361a;
    private String b;
    private Context c;
    private CheckButtonClickListener d = null;
    private List<rhb> e;

    public interface CheckButtonClickListener {
        void onCheckButtonClick(int i, boolean z, List<rhb> list);
    }

    public ThirdPartAccountAuthAdapter(List<rhb> list, String str, List<Boolean> list2) {
        this.e = list;
        this.b = str;
        this.f10361a = list2;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        if (koq.b(this.e, i)) {
            LogUtil.c("ThirdPartAccountAuthAdapter", "onBindViewHolder isOutOfBounds");
            return;
        }
        rhb rhbVar = this.e.get(i);
        LogUtil.c("ThirdPartAccountAuthAdapter", "onBindViewHolder position = ", Integer.valueOf(i), ", and data = ", rhbVar.toString());
        if (viewHolder instanceof IconViewHolder) {
            c((IconViewHolder) viewHolder, rhbVar);
        } else if (viewHolder instanceof ContentViewHolder) {
            d((ContentViewHolder) viewHolder, i, rhbVar);
        } else {
            LogUtil.c("ThirdPartAccountAuthAdapter", "holder isOutOfBounds");
        }
    }

    private void c(IconViewHolder iconViewHolder, rhb rhbVar) {
        iconViewHolder.e.setText(this.b);
        iconViewHolder.b.setText(this.c.getString(R$string.IDS_third_part_auth_head_note, this.b));
        iconViewHolder.f10363a.setText(this.c.getString(R$string.IDS_third_part_auth_date, rhbVar.a()));
        nrf.cIv_(rhbVar.b(), RequestOptions.bitmapTransform(new RoundedCorners((int) this.c.getResources().getDimension(R.dimen._2131362360_res_0x7f0a0238))), iconViewHolder.d);
    }

    private void d(ContentViewHolder contentViewHolder, final int i, rhb rhbVar) {
        if (rhbVar.j()) {
            contentViewHolder.f10362a.setVisibility(0);
            d(contentViewHolder.b, rhbVar.i());
        } else if (rhbVar.d() == 1) {
            contentViewHolder.f10362a.setVisibility(8);
            contentViewHolder.b.setVisibility(8);
        } else {
            LogUtil.a("ThirdPartAccountAuthAdapter", "buildContentViewHolder other condition");
        }
        contentViewHolder.d.setText(rhbVar.c());
        contentViewHolder.e.setOnCheckedChangeListener(null);
        contentViewHolder.e.setChecked(rhbVar.h());
        if (rhbVar.d() == 3) {
            contentViewHolder.e.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.huawei.ui.main.stories.me.activity.thirdparty.adapter.ThirdPartAccountAuthAdapter$$ExternalSyntheticLambda0
                @Override // android.widget.CompoundButton.OnCheckedChangeListener
                public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                    ThirdPartAccountAuthAdapter.this.dOj_(i, compoundButton, z);
                }
            });
        } else if (rhbVar.d() == 1) {
            contentViewHolder.e.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.huawei.ui.main.stories.me.activity.thirdparty.adapter.ThirdPartAccountAuthAdapter$$ExternalSyntheticLambda1
                @Override // android.widget.CompoundButton.OnCheckedChangeListener
                public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                    ThirdPartAccountAuthAdapter.this.dOk_(i, compoundButton, z);
                }
            });
        } else {
            LogUtil.a("ThirdPartAccountAuthAdapter", "buildContentViewHolder other condition");
        }
    }

    /* synthetic */ void dOj_(int i, CompoundButton compoundButton, boolean z) {
        d(z);
        this.d.onCheckButtonClick(i, a(), this.e);
        ViewClickInstrumentation.clickOnView(compoundButton);
    }

    /* synthetic */ void dOk_(int i, CompoundButton compoundButton, boolean z) {
        b(i, z);
        this.d.onCheckButtonClick(i, a(), this.e);
        ViewClickInstrumentation.clickOnView(compoundButton);
    }

    private void d(HealthSubHeader healthSubHeader, int i) {
        if (healthSubHeader == null) {
            LogUtil.b("ThirdPartAccountAuthAdapter", "setTitleText null header");
            return;
        }
        healthSubHeader.setVisibility(0);
        healthSubHeader.setSubHeaderBackgroundColor(0);
        if (i == 0) {
            healthSubHeader.setHeadTitleText(this.c.getString(R$string.IDS_hw_show_main_permission_app_read, this.b));
        } else if (i == 1) {
            healthSubHeader.setHeadTitleText(this.c.getString(R$string.IDS_hw_show_main_permission_app_write, this.b));
        } else {
            LogUtil.h("ThirdPartAccountAuthAdapter", "handleFirstContentItem unexpected operationType");
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        RecyclerView.ViewHolder iconViewHolder;
        if (this.c == null) {
            this.c = viewGroup.getContext();
        }
        if (i == 0) {
            iconViewHolder = new IconViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_third_part_account_auth_icon, viewGroup, false));
        } else if (i == 1) {
            iconViewHolder = new ContentViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_third_part_account_auth_dot_item, viewGroup, false));
        } else if (i == 3) {
            iconViewHolder = new ContentViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_third_part_account_auth_item, viewGroup, false));
        } else {
            LogUtil.a("ThirdPartAccountAuthAdapter", "viewType default branch");
            return null;
        }
        return iconViewHolder;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        List<rhb> list = this.e;
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemViewType(int i) {
        if (koq.b(this.e, i)) {
            LogUtil.c("ThirdPartAccountAuthAdapter", "getItemViewType isOutOfBounds");
            return -1;
        }
        return this.e.get(i).d();
    }

    public static class IconViewHolder extends RecyclerView.ViewHolder {

        /* renamed from: a, reason: collision with root package name */
        HealthTextView f10363a;
        HealthTextView b;
        ImageView d;
        HealthTextView e;

        public IconViewHolder(View view) {
            super(view);
            this.d = (ImageView) view.findViewById(R.id.iv_third_party_account_auth_app_icon);
            this.e = (HealthTextView) view.findViewById(R.id.third_party_account_auth_app_name);
            this.f10363a = (HealthTextView) view.findViewById(R.id.third_party_account_auth_app_date);
            this.b = (HealthTextView) view.findViewById(R.id.third_party_account_head_note);
        }
    }

    public static class ContentViewHolder extends RecyclerView.ViewHolder {

        /* renamed from: a, reason: collision with root package name */
        View f10362a;
        HealthSubHeader b;
        HealthTextView d;
        HealthCheckBox e;

        public ContentViewHolder(View view) {
            super(view);
            this.f10362a = view.findViewById(R.id.third_party_account_auth_splitter_only);
            this.b = (HealthSubHeader) view.findViewById(R.id.third_party_auth_header_view);
            this.e = (HealthCheckBox) view.findViewById(R.id.csb_switch_button);
            this.d = (HealthTextView) view.findViewById(R.id.htv_item_name);
        }
    }

    public boolean a() {
        if (this.e == null) {
            return false;
        }
        for (int i = 0; i < this.e.size(); i++) {
            if (!this.f10361a.get(i).equals(Boolean.valueOf(this.e.get(i).h()))) {
                return true;
            }
        }
        return false;
    }

    private void d(boolean z) {
        if (koq.b(this.e)) {
            return;
        }
        for (rhb rhbVar : this.e) {
            if (rhbVar != null) {
                rhbVar.d(z);
            }
        }
        notifyDataSetChanged();
    }

    private void b(int i, boolean z) {
        if (koq.b(this.e) || koq.b(this.e, i) || this.e.get(i) == null) {
            return;
        }
        this.e.get(i).d(z);
        if (!z) {
            e();
        } else {
            Iterator<rhb> it = this.e.iterator();
            boolean z2 = false;
            while (true) {
                if (it.hasNext()) {
                    rhb next = it.next();
                    if (next != null) {
                        if (!next.h() && (next.d() == 1 || next.d() == 4)) {
                            break;
                        } else {
                            z2 = true;
                        }
                    }
                } else if (z2) {
                    a(true);
                }
            }
        }
        notifyDataSetChanged();
    }

    private void e() {
        for (rhb rhbVar : this.e) {
            if (rhbVar != null && (rhbVar.d() == 3 || rhbVar.d() == 0)) {
                if (rhbVar.h()) {
                    rhbVar.d(false);
                }
            }
        }
    }

    private void a(boolean z) {
        for (rhb rhbVar : this.e) {
            if (rhbVar != null && (rhbVar.d() == 3 || rhbVar.d() == 0)) {
                rhbVar.d(z);
            }
        }
    }

    public void e(CheckButtonClickListener checkButtonClickListener) {
        this.d = checkButtonClickListener;
    }
}
