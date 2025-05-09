package com.huawei.featureuserprofile.sos.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.recyclerview.widget.RecyclerView;
import com.huawei.featureuserprofile.sos.manager.EmergencyInfoManager;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.divider.HealthDivider;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import defpackage.gmx;
import defpackage.nmn;
import defpackage.nrf;
import health.compact.a.EnvironmentInfo;
import java.util.List;

/* loaded from: classes7.dex */
public class EditContactAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    /* renamed from: a, reason: collision with root package name */
    private boolean f2023a;
    private Context c;
    private List<gmx> d;
    private EditContactAdapterCallback e;

    /* loaded from: classes.dex */
    public interface EditContactAdapterCallback {
        void onAddContact();

        void onManuallyAddContact();

        void showRemoveContactDialog(EmergencyInfoManager.c cVar);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public EditContactAdapter(Context context, List<gmx> list) {
        this.c = context;
        if (context instanceof EditContactAdapterCallback) {
            this.e = (EditContactAdapterCallback) context;
        }
        this.d = list;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        if (viewGroup == null) {
            LogUtil.h("EditContactAdapter", "onCreateViewHolder parent is null");
            return null;
        }
        if (i == 1) {
            return new d(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.preference_simple_text, viewGroup, false));
        }
        return new c(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.edit_contact_item, viewGroup, false));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        if (viewHolder instanceof c) {
            EmergencyInfoManager.c d2 = i < this.d.size() ? d(null, this.d.get(i)) : null;
            if (d2 == null) {
                LogUtil.h("EditContactAdapter", "onBindViewHolder emergencyContact is null");
                return;
            } else {
                ((c) viewHolder).b(d2);
                return;
            }
        }
        if (EnvironmentInfo.k()) {
            LogUtil.a("EditContactAdapter", "isMobileAppEngine");
            HealthDivider healthDivider = (HealthDivider) viewHolder.itemView.findViewById(R.id.emergency_divider);
            LinearLayout linearLayout = (LinearLayout) viewHolder.itemView.findViewById(R.id.emergency_linear);
            healthDivider.setVisibility(8);
            linearLayout.setVisibility(8);
        }
        HealthTextView healthTextView = (HealthTextView) viewHolder.itemView.findViewById(R.id.tips_text);
        ((ImageView) viewHolder.itemView.findViewById(R.id.book_item_iamge)).setImageDrawable(this.c.getResources().getDrawable(R.drawable._2131429695_res_0x7f0b093f));
        healthTextView.setText(this.c.getString(R.string._2130848895_res_0x7f022c7f));
        viewHolder.itemView.findViewById(R.id.tips_text).setOnClickListener(new View.OnClickListener() { // from class: com.huawei.featureuserprofile.sos.adapter.EditContactAdapter.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (EditContactAdapter.this.e != null) {
                    EditContactAdapter.this.e.onAddContact();
                }
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        HealthTextView healthTextView2 = (HealthTextView) viewHolder.itemView.findViewById(R.id.tips_manually_add_text);
        ((ImageView) viewHolder.itemView.findViewById(R.id.manuall_add_item_image)).setImageDrawable(this.c.getResources().getDrawable(R.drawable._2131430174_res_0x7f0b0b1e));
        healthTextView2.setText(this.c.getString(R.string._2130844166_res_0x7f021a06));
        viewHolder.itemView.findViewById(R.id.tips_manually_add_text).setOnClickListener(new View.OnClickListener() { // from class: com.huawei.featureuserprofile.sos.adapter.EditContactAdapter.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (EditContactAdapter.this.e != null) {
                    EditContactAdapter.this.e.onManuallyAddContact();
                }
                ViewClickInstrumentation.clickOnView(view);
            }
        });
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemViewType(int i) {
        if (this.f2023a && i == getItemCount() - 1) {
            return 1;
        }
        return super.getItemViewType(i);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        List<gmx> list = this.d;
        int size = list == null ? 0 : list.size();
        return this.f2023a ? size + 1 : size;
    }

    private EmergencyInfoManager.c d(EmergencyInfoManager.c cVar, gmx gmxVar) {
        if (gmxVar == null) {
            return cVar;
        }
        if (!gmxVar.b()) {
            return new EmergencyInfoManager.c(Uri.parse(gmxVar.c()), gmxVar.a(), gmxVar.c());
        }
        return EmergencyInfoManager.c().ve_(Uri.parse(gmxVar.d()));
    }

    public void d(boolean z) {
        this.f2023a = z;
    }

    class c extends RecyclerView.ViewHolder {
        private ImageView b;
        private ImageView c;
        private HealthTextView d;
        private HealthTextView e;

        c(View view) {
            super(view);
            this.c = (ImageView) view.findViewById(R.id.head_view);
            this.d = (HealthTextView) view.findViewById(R.id.double_title);
            this.e = (HealthTextView) view.findViewById(R.id.double_summary);
            this.b = (ImageView) view.findViewById(R.id.remove_contact);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void b(final EmergencyInfoManager.c cVar) {
            this.c.setImageDrawable(uU_(cVar));
            this.d.setText(cVar.b());
            this.e.setText(cVar.a());
            this.b.setImageDrawable(EditContactAdapter.this.c.getResources().getDrawable(R.drawable._2131429938_res_0x7f0b0a32));
            this.b.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.featureuserprofile.sos.adapter.EditContactAdapter.c.5
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    if (EditContactAdapter.this.e != null) {
                        EditContactAdapter.this.e.showRemoveContactDialog(cVar);
                    }
                    ViewClickInstrumentation.clickOnView(view);
                }
            });
        }

        private Drawable uU_(EmergencyInfoManager.c cVar) {
            Bitmap vi_ = cVar.vi_();
            if (vi_ != null) {
                return new BitmapDrawable(EditContactAdapter.this.c.getResources(), nrf.cHX_(vi_));
            }
            return nmn.cBh_(EditContactAdapter.this.c.getResources(), new nmn.c(null, String.valueOf(cVar.c()), true));
        }
    }

    static class d extends RecyclerView.ViewHolder {
        d(View view) {
            super(view);
        }
    }
}
