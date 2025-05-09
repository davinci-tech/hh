package com.huawei.health.ecologydevice.ui.measure.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import androidx.recyclerview.widget.RecyclerView;
import com.huawei.health.R;
import com.huawei.health.ecologydevice.ui.measure.adapter.RopeDeviceSettingsAdapter;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.button.HealthButton;
import com.huawei.ui.commonui.divider.HealthDivider;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.imageview.HealthImageView;
import com.huawei.ui.commonui.radiobutton.HealthRadioButton;
import com.huawei.ui.commonui.switchbutton.HealthSwitchButton;
import defpackage.dja;
import defpackage.dkv;
import defpackage.koq;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes3.dex */
public class RopeDeviceSettingsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    /* renamed from: a, reason: collision with root package name */
    private LayoutInflater f2331a;
    private OnItemClickListener c;
    private List<dkv> d = new ArrayList(10);
    private Context e;

    public interface OnItemClickListener {
        void onItemClick(View view, int i, dkv dkvVar);
    }

    private boolean d(int i) {
        return i == R.drawable.list_item_background_bottom_normal || i == R.drawable.list_item_background_single_normal;
    }

    public RopeDeviceSettingsAdapter(Context context, OnItemClickListener onItemClickListener) {
        this.f2331a = LayoutInflater.from(context);
        this.e = context;
        this.c = onItemClickListener;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        switch (i) {
            case 1:
                return new b(this.f2331a.inflate(R.layout.item_divider_layout, viewGroup, false));
            case 2:
            case 3:
            case 4:
            case 5:
                return new a(this.f2331a.inflate(R.layout.item_rope_voice_settings_content, viewGroup, false));
            case 6:
                return new e(this.f2331a.inflate(R.layout.item_rope_settings_intermittent, viewGroup, false));
            case 7:
                return new d(this.f2331a.inflate(R.layout.item_rope_settings_intermittent_training, viewGroup, false));
            default:
                LogUtil.h("RopeDeviceVoiceSettingsAdapter", "onCreateViewHolder error viewType");
                return null;
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        dkv dkvVar = i < this.d.size() ? this.d.get(i) : null;
        if (dkvVar == null || viewHolder == null) {
            LogUtil.h("RopeDeviceVoiceSettingsAdapter", "onBindViewHolder model or holder is null");
            return;
        }
        switch (dkvVar.e()) {
            case 1:
                d(viewHolder, i, dkvVar);
                break;
            case 2:
            case 3:
            case 4:
            case 5:
                a(viewHolder, i, dkvVar);
                break;
            case 6:
                c(viewHolder, i, dkvVar);
                break;
            case 7:
                e(viewHolder, i, dkvVar);
                break;
            default:
                LogUtil.h("RopeDeviceVoiceSettingsAdapter", "onBindViewHolder error viewType");
                break;
        }
    }

    private void d(RecyclerView.ViewHolder viewHolder, final int i, final dkv dkvVar) {
        if (viewHolder instanceof b) {
            b bVar = (b) viewHolder;
            if (TextUtils.isEmpty(dkvVar.h())) {
                bVar.b.setVisibility(8);
                return;
            }
            bVar.b.setVisibility(0);
            bVar.b.setText(dkvVar.h());
            bVar.b.setOnClickListener(new View.OnClickListener() { // from class: dgq
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    RopeDeviceSettingsAdapter.this.Uz_(i, dkvVar, view);
                }
            });
        }
    }

    public /* synthetic */ void Uz_(int i, dkv dkvVar, View view) {
        this.c.onItemClick(null, i, dkvVar);
        ViewClickInstrumentation.clickOnView(view);
    }

    private void a(RecyclerView.ViewHolder viewHolder, final int i, final dkv dkvVar) {
        if (viewHolder instanceof a) {
            final a aVar = (a) viewHolder;
            a(aVar, dkvVar);
            aVar.e.setBackgroundResource(dkvVar.a());
            aVar.h.setText(dkvVar.h());
            if (!TextUtils.isEmpty(dkvVar.d())) {
                aVar.c.setVisibility(0);
                aVar.c.setText(dkvVar.d());
            } else {
                aVar.c.setVisibility(8);
            }
            if (dkvVar.e() == 2) {
                aVar.d.setVisibility(0);
                aVar.e.setOnClickListener(new View.OnClickListener() { // from class: dgn
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        RopeDeviceSettingsAdapter.this.Uw_(i, dkvVar, view);
                    }
                });
                aVar.i.setVisibility(8);
                aVar.b.setVisibility(8);
            } else if (dkvVar.e() == 4) {
                aVar.d.setVisibility(8);
                aVar.i.setVisibility(0);
                aVar.i.setChecked(dkvVar.g());
                aVar.i.setOnClickListener(new View.OnClickListener() { // from class: dgp
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        RopeDeviceSettingsAdapter.this.Ux_(aVar, i, dkvVar, view);
                    }
                });
                aVar.b.setVisibility(8);
            } else {
                aVar.d.setVisibility(8);
                aVar.i.setVisibility(8);
                aVar.b.setVisibility(0);
                aVar.b.setChecked(dkvVar.g());
                aVar.b.setOnClickListener(new View.OnClickListener() { // from class: dgv
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        RopeDeviceSettingsAdapter.this.Uy_(i, dkvVar, view);
                    }
                });
            }
            if (d(dkvVar.a())) {
                aVar.f2332a.setVisibility(8);
            } else {
                aVar.f2332a.setVisibility(0);
            }
        }
    }

    public /* synthetic */ void Uw_(int i, dkv dkvVar, View view) {
        this.c.onItemClick(null, i, dkvVar);
        ViewClickInstrumentation.clickOnView(view);
    }

    public /* synthetic */ void Ux_(a aVar, int i, dkv dkvVar, View view) {
        this.c.onItemClick(aVar.i, i, dkvVar);
        ViewClickInstrumentation.clickOnView(view);
    }

    public /* synthetic */ void Uy_(int i, dkv dkvVar, View view) {
        this.c.onItemClick(null, i, dkvVar);
        ViewClickInstrumentation.clickOnView(view);
    }

    private void c(RecyclerView.ViewHolder viewHolder, final int i, final dkv dkvVar) {
        if (viewHolder instanceof e) {
            final e eVar = (e) viewHolder;
            eVar.c.setBackgroundResource(dkvVar.a());
            eVar.d.setText(dkvVar.h());
            eVar.f2334a.setText(dkvVar.d());
            eVar.b.setBackgroundResource("intermitmode_type".equals(dkvVar.b()) ? R.drawable._2131429716_res_0x7f0b0954 : R.drawable._2131427842_res_0x7f0b0202);
            if (d(dkvVar.a())) {
                eVar.e.setVisibility(8);
            } else {
                eVar.e.setVisibility(0);
            }
            eVar.c.setOnClickListener(new View.OnClickListener() { // from class: dgu
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    RopeDeviceSettingsAdapter.this.UA_(eVar, i, dkvVar, view);
                }
            });
        }
    }

    public /* synthetic */ void UA_(e eVar, int i, dkv dkvVar, View view) {
        this.c.onItemClick(eVar.f2334a, i, dkvVar);
        ViewClickInstrumentation.clickOnView(view);
    }

    private void a(a aVar, dkv dkvVar) {
        int dimensionPixelOffset;
        if (TextUtils.isEmpty(dkvVar.d())) {
            dimensionPixelOffset = this.e.getResources().getDimensionPixelOffset(R.dimen._2131363076_res_0x7f0a0504);
        } else if (dkvVar.e() == 3) {
            int dimensionPixelOffset2 = this.e.getResources().getDimensionPixelOffset(R.dimen._2131362886_res_0x7f0a0446);
            aVar.h.setPadding(0, dimensionPixelOffset2, 0, 0);
            aVar.c.setPadding(0, 0, 0, dimensionPixelOffset2);
            dimensionPixelOffset = -2;
        } else {
            dimensionPixelOffset = this.e.getResources().getDimensionPixelOffset(R.dimen._2131363092_res_0x7f0a0514);
            aVar.c.setSingleLine();
            aVar.c.setEllipsize(TextUtils.TruncateAt.END);
        }
        aVar.e.setLayoutParams(new FrameLayout.LayoutParams(-1, dimensionPixelOffset));
    }

    private void e(RecyclerView.ViewHolder viewHolder, final int i, final dkv dkvVar) {
        if (viewHolder instanceof d) {
            d dVar = (d) viewHolder;
            if ("intermitmode_custom_type".equals(dkvVar.b())) {
                dVar.c.setVisibility(0);
                dVar.c.setText(dkvVar.h());
                dVar.b.setVisibility(8);
                dVar.d.setVisibility(8);
                dVar.e.setBackgroundResource(R.color._2131296971_res_0x7f0902cb);
                dVar.c.setOnClickListener(new View.OnClickListener() { // from class: dgt
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        RopeDeviceSettingsAdapter.this.UB_(i, dkvVar, view);
                    }
                });
                return;
            }
            dVar.c.setVisibility(8);
            dVar.e.setBackgroundResource(dkvVar.a());
            Bitmap decodeFile = BitmapFactory.decodeFile(dja.a(dkvVar.b()));
            if (decodeFile == null) {
                dVar.b.setVisibility(8);
                dVar.d.setVisibility(0);
                dVar.e.setBackgroundResource(R.color._2131296690_res_0x7f0901b2);
                dVar.f.setTextColor(this.e.getResources().getColor(R.color._2131299236_res_0x7f090ba4));
                dVar.f2333a.setTextColor(this.e.getResources().getColor(R.color._2131299241_res_0x7f090ba9));
                dVar.f.setText(dkvVar.h());
                dVar.f2333a.setText(dkvVar.d());
            } else {
                dVar.b.setVisibility(0);
                dVar.d.setVisibility(8);
                dVar.e.setBackgroundResource(R.color._2131296971_res_0x7f0902cb);
                dVar.f.setTextColor(this.e.getResources().getColor(R.color._2131299238_res_0x7f090ba6));
                dVar.f2333a.setTextColor(this.e.getResources().getColor(R.color._2131299238_res_0x7f090ba6));
                dVar.b.setImageBitmap(decodeFile);
            }
            dVar.e.setOnClickListener(new View.OnClickListener() { // from class: dgr
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    RopeDeviceSettingsAdapter.this.UC_(i, dkvVar, view);
                }
            });
        }
    }

    public /* synthetic */ void UB_(int i, dkv dkvVar, View view) {
        this.c.onItemClick(null, i, dkvVar);
        ViewClickInstrumentation.clickOnView(view);
    }

    public /* synthetic */ void UC_(int i, dkv dkvVar, View view) {
        this.c.onItemClick(null, i, dkvVar);
        ViewClickInstrumentation.clickOnView(view);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemViewType(int i) {
        if (koq.b(this.d, i)) {
            return 1;
        }
        return this.d.get(i).e();
    }

    public void a(List<dkv> list) {
        if (list == null) {
            return;
        }
        if (koq.c(this.d)) {
            this.d.clear();
        }
        this.d.addAll(list);
        notifyDataSetChanged();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        List<dkv> list = this.d;
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    static class b extends RecyclerView.ViewHolder {
        HealthTextView b;

        b(View view) {
            super(view);
            this.b = (HealthTextView) view.findViewById(R.id.divider_content);
        }
    }

    public static class a extends RecyclerView.ViewHolder {

        /* renamed from: a, reason: collision with root package name */
        HealthDivider f2332a;
        HealthRadioButton b;
        HealthTextView c;
        HealthImageView d;
        RelativeLayout e;
        HealthTextView h;
        HealthSwitchButton i;

        a(View view) {
            super(view);
            this.e = (RelativeLayout) view.findViewById(R.id.item_content_rl);
            this.h = (HealthTextView) view.findViewById(R.id.voice_settings_content_title);
            this.c = (HealthTextView) view.findViewById(R.id.voice_settings_content_detail);
            this.d = (HealthImageView) view.findViewById(R.id.voice_settings_content_right);
            this.i = (HealthSwitchButton) view.findViewById(R.id.voice_settings_content_switch);
            this.b = (HealthRadioButton) view.findViewById(R.id.voice_settings_content_radio_image);
            this.f2332a = (HealthDivider) view.findViewById(R.id.list_content_line);
        }
    }

    public static class e extends RecyclerView.ViewHolder {

        /* renamed from: a, reason: collision with root package name */
        HealthTextView f2334a;
        HealthImageView b;
        RelativeLayout c;
        HealthTextView d;
        HealthDivider e;

        e(View view) {
            super(view);
            this.c = (RelativeLayout) view.findViewById(R.id.item_content_rl);
            this.d = (HealthTextView) view.findViewById(R.id.voice_settings_content_title);
            this.f2334a = (HealthTextView) view.findViewById(R.id.voice_settings_content_detail);
            this.b = (HealthImageView) view.findViewById(R.id.voice_settings_content_right);
            this.e = (HealthDivider) view.findViewById(R.id.list_content_line);
        }
    }

    static class d extends RecyclerView.ViewHolder {

        /* renamed from: a, reason: collision with root package name */
        HealthTextView f2333a;
        HealthImageView b;
        HealthButton c;
        LinearLayout d;
        RelativeLayout e;
        HealthTextView f;

        d(View view) {
            super(view);
            this.e = (RelativeLayout) view.findViewById(R.id.item_content_rl);
            this.b = (HealthImageView) view.findViewById(R.id.voice_settings_bg_image);
            this.d = (LinearLayout) view.findViewById(R.id.item_content_ll);
            this.f = (HealthTextView) view.findViewById(R.id.voice_settings_content_title);
            this.f2333a = (HealthTextView) view.findViewById(R.id.voice_settings_content_detail);
            this.c = (HealthButton) view.findViewById(R.id.rope_device_btn_custom);
        }
    }
}
