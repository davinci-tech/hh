package com.huawei.ui.homewear21.home.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import androidx.recyclerview.widget.RecyclerView;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.switchbutton.HealthSwitchButton;
import com.huawei.ui.homewear21.home.listener.WearHomeListener;
import defpackage.nsy;
import defpackage.obz;
import health.compact.a.LanguageUtil;
import java.util.List;

/* loaded from: classes6.dex */
public class WearHomeGeneralAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    /* renamed from: a, reason: collision with root package name */
    private LayoutInflater f9662a;
    private List<obz> c;
    private WearHomeListener d;
    private Context e;

    public WearHomeGeneralAdapter(Context context, List<obz> list) {
        this.e = context;
        this.c = list;
        this.f9662a = LayoutInflater.from(context);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        if (i == 3) {
            return new WearHomeTitleImageHolder(this.f9662a.inflate(R.layout.activity_device_settings_title_image_item, viewGroup, false));
        }
        if (i == 0) {
            return new WearHomeTitleSwitchHolder(this.f9662a.inflate(R.layout.activity_device_settings_two_title_switch_item, viewGroup, false));
        }
        if (i == 1) {
            return new WearHomeOneTitleSwitchHolder(this.f9662a.inflate(R.layout.activity_device_settings_title_switch_item, viewGroup, false));
        }
        return null;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        if (i >= this.c.size()) {
            return;
        }
        if (viewHolder instanceof WearHomeTitleImageHolder) {
            b((WearHomeTitleImageHolder) viewHolder, i);
            return;
        }
        if (viewHolder instanceof WearHomeTitleSwitchHolder) {
            e((WearHomeTitleSwitchHolder) viewHolder, i);
        } else if (viewHolder instanceof WearHomeOneTitleSwitchHolder) {
            b((WearHomeOneTitleSwitchHolder) viewHolder, i);
        } else {
            LogUtil.h("WearHomeGeneralAdapter", "onBindViewHolder");
        }
    }

    private void b(WearHomeOneTitleSwitchHolder wearHomeOneTitleSwitchHolder, int i) {
        obz obzVar = this.c.get(i);
        wearHomeOneTitleSwitchHolder.d.setText(obzVar.d());
        wearHomeOneTitleSwitchHolder.f9663a.setImageResource(obzVar.b());
        wearHomeOneTitleSwitchHolder.c.setChecked(obzVar.f());
        wearHomeOneTitleSwitchHolder.c.setOnCheckedChangeListener(obzVar.cVn_());
        wearHomeOneTitleSwitchHolder.c.setEnabled(obzVar.g());
        if (this.c.size() - 1 == i) {
            wearHomeOneTitleSwitchHolder.e.setVisibility(8);
        } else {
            wearHomeOneTitleSwitchHolder.e.setVisibility(0);
        }
    }

    private void b(WearHomeTitleImageHolder wearHomeTitleImageHolder, final int i) {
        obz obzVar = this.c.get(i);
        wearHomeTitleImageHolder.b.setText(obzVar.d());
        wearHomeTitleImageHolder.e.setText(obzVar.c());
        wearHomeTitleImageHolder.d.setImageResource(obzVar.b());
        if (LanguageUtil.bc(this.e)) {
            wearHomeTitleImageHolder.g.setBackgroundResource(R.drawable._2131427841_res_0x7f0b0201);
        } else {
            wearHomeTitleImageHolder.g.setBackgroundResource(R.drawable._2131427842_res_0x7f0b0202);
        }
        wearHomeTitleImageHolder.c.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.ui.homewear21.home.adapter.WearHomeGeneralAdapter.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (WearHomeGeneralAdapter.this.d != null) {
                    WearHomeGeneralAdapter.this.d.onItemClick(i);
                } else {
                    LogUtil.h("WearHomeGeneralAdapter", "mWearHomeListener is null");
                }
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        wearHomeTitleImageHolder.c.setEnabled(obzVar.g());
        if (this.c.size() - 1 == i) {
            wearHomeTitleImageHolder.f9664a.setVisibility(8);
        } else {
            wearHomeTitleImageHolder.f9664a.setVisibility(0);
        }
    }

    private void e(WearHomeTitleSwitchHolder wearHomeTitleSwitchHolder, int i) {
        obz obzVar = this.c.get(i);
        wearHomeTitleSwitchHolder.b.setText(obzVar.d());
        wearHomeTitleSwitchHolder.e.setText(obzVar.j());
        wearHomeTitleSwitchHolder.f9665a.setImageResource(obzVar.b());
        wearHomeTitleSwitchHolder.c.setChecked(obzVar.f());
        wearHomeTitleSwitchHolder.c.setOnCheckedChangeListener(obzVar.cVn_());
        wearHomeTitleSwitchHolder.c.setEnabled(obzVar.g());
        if (this.c.size() - 1 == i) {
            wearHomeTitleSwitchHolder.d.setVisibility(8);
        }
    }

    public void b(List<obz> list) {
        this.c = list;
        notifyDataSetChanged();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemViewType(int i) {
        if (i >= this.c.size()) {
            return -1;
        }
        return this.c.get(i).i();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.c.size();
    }

    public void e(WearHomeListener wearHomeListener) {
        this.d = wearHomeListener;
    }

    public static class WearHomeOneTitleSwitchHolder extends RecyclerView.ViewHolder {

        /* renamed from: a, reason: collision with root package name */
        private ImageView f9663a;
        private HealthSwitchButton c;
        private HealthTextView d;
        private View e;

        public WearHomeOneTitleSwitchHolder(View view) {
            super(view);
            this.f9663a = (ImageView) nsy.cMd_(view, R.id.item_icon);
            this.d = (HealthTextView) nsy.cMd_(view, R.id.content);
            if (LanguageUtil.bc(BaseApplication.getContext())) {
                this.d.setGravity(5);
            }
            this.c = (HealthSwitchButton) nsy.cMd_(view, R.id.switch_button);
            this.e = nsy.cMd_(view, R.id.item_line);
        }
    }

    public static class WearHomeTitleSwitchHolder extends RecyclerView.ViewHolder {

        /* renamed from: a, reason: collision with root package name */
        private ImageView f9665a;
        private HealthTextView b;
        private HealthSwitchButton c;
        private View d;
        private HealthTextView e;

        public WearHomeTitleSwitchHolder(View view) {
            super(view);
            this.f9665a = (ImageView) nsy.cMd_(view, R.id.item_icon);
            this.b = (HealthTextView) nsy.cMd_(view, R.id.content);
            this.e = (HealthTextView) nsy.cMd_(view, R.id.sub_content);
            this.c = (HealthSwitchButton) nsy.cMd_(view, R.id.switch_button);
            this.d = nsy.cMd_(view, R.id.item_line);
        }
    }

    public static class WearHomeTitleImageHolder extends RecyclerView.ViewHolder {

        /* renamed from: a, reason: collision with root package name */
        private View f9664a;
        private HealthTextView b;
        private RelativeLayout c;
        private ImageView d;
        private HealthTextView e;
        private ImageView g;

        public WearHomeTitleImageHolder(View view) {
            super(view);
            this.d = (ImageView) nsy.cMd_(view, R.id.item_icon);
            this.c = (RelativeLayout) nsy.cMd_(view, R.id.setting_device_rela);
            this.b = (HealthTextView) nsy.cMd_(view, R.id.content);
            this.g = (ImageView) nsy.cMd_(view, R.id.settings_switch);
            this.e = (HealthTextView) nsy.cMd_(view, R.id.right_text);
            this.f9664a = nsy.cMd_(view, R.id.item_line);
        }
    }
}
