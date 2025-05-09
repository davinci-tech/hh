package com.huawei.ui.main.stories.me.activity.thirdparty.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import androidx.recyclerview.widget.RecyclerView;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.checkbox.HealthCheckBox;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.subheader.HealthSubHeader;
import com.huawei.ui.main.R$string;
import defpackage.koq;
import defpackage.rhg;
import java.util.HashMap;
import java.util.List;

/* loaded from: classes9.dex */
public class ThirdPartAuthAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    /* renamed from: a, reason: collision with root package name */
    private String f10364a;
    private HashMap<Integer, Integer> b;
    private Context c;
    private List<rhg> d;
    private List<Boolean> e;
    private SetSwitchButtonChanged f = null;
    private String h;

    public interface SetSwitchButtonChanged {
        void switchButtonChanged(boolean z, List<rhg> list);
    }

    public ThirdPartAuthAdapter(List<rhg> list, String str, String str2, List<Boolean> list2) {
        this.d = list;
        this.f10364a = str2;
        this.h = str;
        this.e = list2;
        e();
    }

    private void e() {
        HashMap<Integer, Integer> hashMap = new HashMap<>(16);
        this.b = hashMap;
        hashMap.put(101001, Integer.valueOf(R$string.IDS_hw_show_main_permission_sub_content_user_profile_basic_information));
        this.b.put(101002, Integer.valueOf(R$string.IDS_hw_show_main_permission_sub_content_user_profile_basic_feature));
        this.b.put(101201, Integer.valueOf(R$string.IDS_hw_show_main_permission_sub_content_device_information));
        this.b.put(101003, Integer.valueOf(R$string.IDS_hw_show_main_permission_sub_content_user_realtime_sport_data));
        this.b.put(101202, Integer.valueOf(R$string.IDS_hw_show_main_permission_sub_content_device_data));
        this.b.put(101204, Integer.valueOf(R$string.IDS_hw_show_main_permission_sub_content_device_advanced_control));
        this.b.put(30005, Integer.valueOf(R$string.IDS_hw_show_main_permission_sub_content_user_track));
        this.b.put(30006, Integer.valueOf(R$string.IDS_hw_show_main_permission_sub_content_user_track));
        this.b.put(30007, Integer.valueOf(R$string.IDS_hw_show_main_permission_sub_content_user_track));
        this.b.put(10008, Integer.valueOf(R$string.IDS_hw_show_main_permission_sub_content_user_stat_heart_rate));
        this.b.put(50001, Integer.valueOf(R$string.IDS_hw_show_main_permission_sub_content_user_heart_rate));
        this.b.put(10006, Integer.valueOf(R$string.IDS_hw_show_main_permission_sub_content_user_weight));
        this.b.put(10007, Integer.valueOf(R$string.IDS_hw_show_main_permission_sub_content_user_sleep));
        this.b.put(44000, Integer.valueOf(R$string.IDS_hw_show_main_permission_sub_content_user_sleep));
        this.b.put(31001, Integer.valueOf(R$string.IDS_permission_desc_heart_health));
        this.b.put(101000, Integer.valueOf(R$string.IDS_hw_show_main_permission_app_sync_sub));
        this.b.put(10010, Integer.valueOf(R$string.IDS_hw_show_main_permission_sub_content_user_menstruation));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        if (koq.b(this.d, i)) {
            LogUtil.c("ThirdPartAuthAdapter", "onBindViewHolder isOutOfBounds");
            return;
        }
        rhg rhgVar = this.d.get(i);
        LogUtil.c("ThirdPartAuthAdapter", "onBindViewHolder position = ", Integer.valueOf(i), ", and data = ", rhgVar.toString());
        if (viewHolder instanceof IconViewHolder) {
            LogUtil.c("ThirdPartAuthAdapter", "onBindViewHolder IconViewHolder ");
            ((IconViewHolder) viewHolder).f10368a.setVisibility(0);
            e(viewHolder, rhgVar);
        } else if (viewHolder instanceof c) {
            d(viewHolder, rhgVar);
        } else {
            d((ContentViewHolder) viewHolder, i, rhgVar);
        }
    }

    private void e(RecyclerView.ViewHolder viewHolder, rhg rhgVar) {
        IconViewHolder iconViewHolder = (IconViewHolder) viewHolder;
        iconViewHolder.d.setText(this.f10364a);
        iconViewHolder.b.setText(this.c.getString(R$string.IDS_third_part_auth_head_note, this.f10364a));
        iconViewHolder.e.setImageDrawable(rhgVar.dOn_());
        if (rhgVar.i() && rhgVar.e() != null) {
            iconViewHolder.c.setText(this.c.getString(R$string.IDS_third_part_auth_date, rhgVar.e()));
        } else {
            iconViewHolder.c.setVisibility(8);
        }
    }

    private void d(RecyclerView.ViewHolder viewHolder, rhg rhgVar) {
        c cVar = (c) viewHolder;
        if ("TYPE_TWO".equals(this.h) && !rhgVar.g()) {
            cVar.e.setVisibility(8);
        } else {
            LogUtil.b("ThirdPartAuthAdapter", "unrecognized bottomType");
        }
    }

    private void d(ContentViewHolder contentViewHolder, final int i, rhg rhgVar) {
        LogUtil.a("ThirdPartAuthAdapter", "HiHealthUserPermission Visibility= ", Boolean.valueOf(rhgVar.g()));
        if (rhgVar.g()) {
            b(rhgVar, contentViewHolder);
        } else if (rhgVar.d() == 4) {
            contentViewHolder.c.setVisibility(8);
            contentViewHolder.d.setVisibility(8);
        } else {
            contentViewHolder.f10367a.setVisibility(8);
        }
        contentViewHolder.e.setText(rhgVar.c());
        e(contentViewHolder.j, rhgVar.a());
        contentViewHolder.b.setOnCheckedChangeListener(null);
        contentViewHolder.b.setChecked(rhgVar.j());
        if (rhgVar.d() == 3) {
            contentViewHolder.b.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.huawei.ui.main.stories.me.activity.thirdparty.adapter.ThirdPartAuthAdapter$$ExternalSyntheticLambda0
                @Override // android.widget.CompoundButton.OnCheckedChangeListener
                public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                    ThirdPartAuthAdapter.this.dOl_(compoundButton, z);
                }
            });
        } else if (rhgVar.d() == 1 || rhgVar.d() == 4) {
            contentViewHolder.b.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.huawei.ui.main.stories.me.activity.thirdparty.adapter.ThirdPartAuthAdapter$$ExternalSyntheticLambda1
                @Override // android.widget.CompoundButton.OnCheckedChangeListener
                public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                    ThirdPartAuthAdapter.this.dOm_(i, compoundButton, z);
                }
            });
        } else {
            LogUtil.a("ThirdPartAuthAdapter", "buildContentViewHolder other condition");
        }
    }

    /* synthetic */ void dOl_(CompoundButton compoundButton, boolean z) {
        d(z);
        this.f.switchButtonChanged(d(), this.d);
        ViewClickInstrumentation.clickOnView(compoundButton);
    }

    /* synthetic */ void dOm_(int i, CompoundButton compoundButton, boolean z) {
        a(i, z);
        this.f.switchButtonChanged(d(), this.d);
        ViewClickInstrumentation.clickOnView(compoundButton);
    }

    public boolean d() {
        if (this.d == null) {
            return false;
        }
        for (int i = 0; i < this.d.size(); i++) {
            if (!this.e.get(i).equals(Boolean.valueOf(this.d.get(i).j()))) {
                return true;
            }
        }
        return false;
    }

    private void b(rhg rhgVar, ContentViewHolder contentViewHolder) {
        if (rhgVar.f() == 2) {
            contentViewHolder.c.setVisibility(0);
            contentViewHolder.d.setVisibility(8);
        } else if (rhgVar.d() == 1) {
            d(contentViewHolder.f10367a, rhgVar.f());
        } else if (rhgVar.d() == 4) {
            d(contentViewHolder.d, rhgVar.f());
            contentViewHolder.c.setVisibility(0);
        } else {
            LogUtil.a("ThirdPartAuthAdapter", "handleFirstContentItem unexpected ViewType");
        }
    }

    private void d(HealthSubHeader healthSubHeader, int i) {
        if (healthSubHeader == null) {
            LogUtil.b("ThirdPartAuthAdapter", "setTitleText null header");
            return;
        }
        healthSubHeader.setVisibility(0);
        healthSubHeader.setSubHeaderBackgroundColor(0);
        if (i == 0) {
            healthSubHeader.setHeadTitleText(this.c.getString(R$string.IDS_hw_show_main_permission_app_read, this.f10364a));
        } else if (i == 1) {
            healthSubHeader.setHeadTitleText(this.c.getString(R$string.IDS_hw_show_main_permission_app_write, this.f10364a));
        } else {
            LogUtil.h("ThirdPartAuthAdapter", "handleFirstContentItem unexpected operationType");
        }
    }

    private void e(HealthTextView healthTextView, int i) {
        if (healthTextView != null && this.b.containsKey(Integer.valueOf(i))) {
            healthTextView.setText(this.b.get(Integer.valueOf(i)).intValue());
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        if (viewGroup == null) {
            LogUtil.a("ThirdPartAuthAdapter", "onCreateViewHolder parent is null");
            return null;
        }
        if (this.c == null) {
            this.c = viewGroup.getContext();
        }
        if (i == 0) {
            return new IconViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_third_part_auth_icon, viewGroup, false));
        }
        if (i == 1) {
            return new ContentViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_third_part_auth_item, viewGroup, false));
        }
        if (i == 2) {
            return new c(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_third_part_auth_bottom, viewGroup, false));
        }
        if (i == 3) {
            return new ContentViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_third_part_auth_all_item, viewGroup, false));
        }
        if (i == 4) {
            return new ContentViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_third_part_auth_with_sub_item, viewGroup, false));
        }
        LogUtil.a("ThirdPartAuthAdapter", "viewType default branch");
        return null;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        List<rhg> list = this.d;
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemViewType(int i) {
        if (koq.b(this.d, i)) {
            LogUtil.c("ThirdPartAuthAdapter", "getItemViewType isOutOfBounds");
            return -1;
        }
        return this.d.get(i).d();
    }

    public static class ContentViewHolder extends RecyclerView.ViewHolder {

        /* renamed from: a, reason: collision with root package name */
        HealthSubHeader f10367a;
        HealthCheckBox b;
        View c;
        HealthSubHeader d;
        HealthTextView e;
        HealthTextView j;

        public ContentViewHolder(View view) {
            super(view);
            this.c = view.findViewById(R.id.third_party_auth_splitter_only);
            this.f10367a = (HealthSubHeader) view.findViewById(R.id.third_party_auth_header_view);
            this.d = (HealthSubHeader) view.findViewById(R.id.third_party_auth_header_view1);
            this.b = (HealthCheckBox) view.findViewById(R.id.csb_switch_button);
            this.e = (HealthTextView) view.findViewById(R.id.htv_item_name);
            this.j = (HealthTextView) view.findViewById(R.id.htv_item_sub_name);
        }
    }

    public static class IconViewHolder extends RecyclerView.ViewHolder {

        /* renamed from: a, reason: collision with root package name */
        RelativeLayout f10368a;
        HealthTextView b;
        HealthTextView c;
        HealthTextView d;
        ImageView e;

        public IconViewHolder(View view) {
            super(view);
            this.f10368a = (RelativeLayout) view.findViewById(R.id.third_party_auth_icon);
            this.e = (ImageView) view.findViewById(R.id.iv_third_party_auth_app_icon);
            this.d = (HealthTextView) view.findViewById(R.id.third_party_auth_app_name);
            this.c = (HealthTextView) view.findViewById(R.id.third_party_auth_app_date);
            this.b = (HealthTextView) view.findViewById(R.id.third_party_head_note);
        }
    }

    static class c extends RecyclerView.ViewHolder {
        HealthTextView e;

        c(View view) {
            super(view);
            HealthTextView healthTextView = (HealthTextView) view.findViewById(R.id.hw_show_about_rights);
            this.e = healthTextView;
            healthTextView.setText(view.getContext().getResources().getString(R$string.IDS_hw_show_main_permission_delete_tips));
        }
    }

    private void d(boolean z) {
        if (koq.b(this.d)) {
            return;
        }
        for (rhg rhgVar : this.d) {
            if (rhgVar == null) {
                return;
            } else {
                rhgVar.d(z);
            }
        }
        notifyDataSetChanged();
    }

    /* JADX WARN: Code restructure failed: missing block: B:25:0x0059, code lost:
    
        if (r5 == false) goto L29;
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x005b, code lost:
    
        a(r5);
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void a(int r4, boolean r5) {
        /*
            r3 = this;
            java.util.List<rhg> r0 = r3.d
            boolean r0 = defpackage.koq.b(r0)
            if (r0 != 0) goto L61
            java.util.List<rhg> r0 = r3.d
            boolean r0 = defpackage.koq.b(r0, r4)
            if (r0 != 0) goto L61
            java.util.List<rhg> r0 = r3.d
            java.lang.Object r0 = r0.get(r4)
            if (r0 != 0) goto L19
            goto L61
        L19:
            java.util.List<rhg> r0 = r3.d
            java.lang.Object r4 = r0.get(r4)
            rhg r4 = (defpackage.rhg) r4
            r4.d(r5)
            if (r5 != 0) goto L2a
            r3.b()
            goto L5e
        L2a:
            java.util.List<rhg> r4 = r3.d
            java.util.Iterator r4 = r4.iterator()
            r5 = 0
            r0 = r5
        L32:
            boolean r1 = r4.hasNext()
            if (r1 == 0) goto L58
            java.lang.Object r0 = r4.next()
            rhg r0 = (defpackage.rhg) r0
            if (r0 != 0) goto L41
            return
        L41:
            boolean r1 = r0.j()
            r2 = 1
            if (r1 != 0) goto L56
            int r1 = r0.d()
            if (r1 == r2) goto L59
            int r0 = r0.d()
            r1 = 4
            if (r0 != r1) goto L56
            goto L59
        L56:
            r0 = r2
            goto L32
        L58:
            r5 = r0
        L59:
            if (r5 == 0) goto L5e
            r3.a(r5)
        L5e:
            r3.notifyDataSetChanged()
        L61:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.ui.main.stories.me.activity.thirdparty.adapter.ThirdPartAuthAdapter.a(int, boolean):void");
    }

    private void b() {
        for (rhg rhgVar : this.d) {
            if (rhgVar != null && (rhgVar.d() == 3 || rhgVar.d() == 0 || rhgVar.c() == null)) {
                if (rhgVar.j()) {
                    rhgVar.d(false);
                }
            }
        }
    }

    private void a(boolean z) {
        for (rhg rhgVar : this.d) {
            if (rhgVar != null && (rhgVar.d() == 3 || rhgVar.d() == 0 || rhgVar.c() == null)) {
                rhgVar.d(z);
            }
        }
    }

    public void b(SetSwitchButtonChanged setSwitchButtonChanged) {
        this.f = setSwitchButtonChanged;
    }
}
