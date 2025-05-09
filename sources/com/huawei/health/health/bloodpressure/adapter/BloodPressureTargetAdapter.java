package com.huawei.health.health.bloodpressure.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import com.huawei.haf.application.BaseApplication;
import com.huawei.health.R;
import com.huawei.health.health.bloodpressure.adapter.BloodPressureTargetAdapter;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwbasemgr.ResponseCallback;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.R$drawable;
import com.huawei.ui.commonui.R$string;
import com.huawei.ui.commonui.checkbox.HealthCheckBox;
import com.huawei.ui.commonui.divider.HealthDivider;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import defpackage.cbi;
import defpackage.cbk;
import defpackage.koq;
import defpackage.nrh;
import health.compact.a.LanguageUtil;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes8.dex */
public class BloodPressureTargetAdapter extends RecyclerView.Adapter<Holder> {

    /* renamed from: a, reason: collision with root package name */
    private OnItemClickListener f2478a;
    private List<cbk> b;
    private Map<cbk, Boolean> e;
    private final WeakReference<Activity> g;
    private int d = 0;
    private Context c = BaseApplication.e();

    public interface OnItemClickListener<T> {
        void onItemClick(View view, int i);
    }

    public BloodPressureTargetAdapter(Activity activity, List<cbk> list) {
        this.g = new WeakReference<>(activity);
        this.b = list;
        this.b = c(list);
        d();
    }

    public void a(cbk cbkVar) {
        LogUtil.a("BloodPressureTargetAdapter", "sort from Add");
        if (b(cbkVar) && this.b.size() + 1 <= 10) {
            this.b.add(cbkVar);
            this.b = c(this.b);
            d();
            notifyDataSetChanged();
        }
    }

    public void d(cbk cbkVar, int i) {
        LogUtil.a("BloodPressureTargetAdapter", "sort from Update, position: ", Integer.valueOf(i));
        if (c(cbkVar, i)) {
            this.b.remove(i);
            this.b.add(cbkVar);
            this.b = c(this.b);
            d();
            notifyDataSetChanged();
        }
    }

    public void b(int i) {
        LogUtil.a("BloodPressureTargetAdapter", "sort from remove, position: ", Integer.valueOf(i));
        this.b.remove(i);
        this.b = c(this.b);
        notifyDataSetChanged();
    }

    public void b() {
        for (Map.Entry<cbk, Boolean> entry : this.e.entrySet()) {
            if (entry.getValue().booleanValue()) {
                this.b.remove(entry.getKey());
            }
        }
        this.b = c(this.b);
        d();
        notifyDataSetChanged();
    }

    private boolean b(cbk cbkVar) {
        if (koq.b(this.b)) {
            return true;
        }
        if (cbkVar.compareTo(this.b.get(0)) < 0) {
            return c(-1);
        }
        List<cbk> list = this.b;
        if (cbkVar.compareTo(list.get(list.size() - 1)) > 0) {
            return c(1);
        }
        int e = e(cbkVar);
        Iterator<cbk> it = this.b.iterator();
        while (it.hasNext()) {
            int e2 = e(it.next());
            LogUtil.a("BloodPressureTargetAdapter", "newTime:", Integer.valueOf(e), "temp:", Integer.valueOf(e2));
            int i = e - e2;
            if (Math.abs(i) < 45 || Math.abs(i) > 1395) {
                return c(0);
            }
        }
        return true;
    }

    private boolean c(cbk cbkVar, int i) {
        if (i == 0 && cbkVar.compareTo(this.b.get(1)) > 0) {
            return c(-1);
        }
        if (i == this.b.size() - 1) {
            if (cbkVar.compareTo(this.b.get(r2.size() - 2)) < 0) {
                return c(1);
            }
        }
        if (i != 0 && i != this.b.size() - 1) {
            if (cbkVar.compareTo(this.b.get(0)) < 0) {
                return c(-1);
            }
            List<cbk> list = this.b;
            if (cbkVar.compareTo(list.get(list.size() - 1)) > 0) {
                return c(1);
            }
        }
        int e = e(cbkVar);
        for (int i2 = 0; i2 < this.b.size(); i2++) {
            int e2 = e(this.b.get(i2));
            LogUtil.a("BloodPressureTargetAdapter", "newTime:", Integer.valueOf(e), "temp:", Integer.valueOf(e2));
            if (i2 != i) {
                int i3 = e - e2;
                if (Math.abs(i3) < 45 || Math.abs(i3) > 1395) {
                    return c(0);
                }
            }
        }
        return true;
    }

    private boolean c(int i) {
        if (i == -1) {
            Context context = this.c;
            nrh.d(context, context.getString(R$string.IDS_blood_pressure_wake_up_toast));
            return false;
        }
        if (i == 0) {
            Context context2 = this.c;
            nrh.c(context2, context2.getString(R$string.IDS_blood_pressure_customize_toast, 45));
            return false;
        }
        if (i == 1) {
            Context context3 = this.c;
            nrh.d(context3, context3.getString(R$string.IDS_blood_pressure_before_sleep_toast));
            return false;
        }
        LogUtil.h("BloodPressureTargetAdapter", "undefined type, type : ", Integer.valueOf(i));
        return false;
    }

    private int e(cbk cbkVar) {
        return (cbkVar.a() * 60) + cbkVar.e();
    }

    public void a() {
        d();
    }

    private void d() {
        this.e = new HashMap(this.b.size());
        for (int i = 0; i < this.b.size(); i++) {
            this.e.put(this.b.get(i), false);
        }
    }

    private List<cbk> c(List<cbk> list) {
        Collections.sort(list, cbi.c());
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < list.size(); i++) {
            if (i == this.b.size() - 1) {
                arrayList.add(new cbk(9, this.b.get(i).a(), this.b.get(i).e(), 9));
            } else {
                arrayList.add(new cbk(i, this.b.get(i).a(), this.b.get(i).e(), i));
            }
        }
        return arrayList;
    }

    public void d(int i, boolean z) {
        a(i, z, 0);
    }

    public void a(int i, boolean z, final int i2) {
        LogUtil.a("BloodPressureTargetAdapter", "daysOfWeek:", Integer.valueOf(i), ", isEnabled:", Boolean.valueOf(z), ",saveType:", Integer.valueOf(i2));
        for (cbk cbkVar : this.b) {
            cbkVar.b(i);
            cbkVar.b(z);
        }
        cbi.d(this.b, z, new ResponseCallback() { // from class: dok
            @Override // com.huawei.hwbasemgr.ResponseCallback
            public final void onResponse(int i3, Object obj) {
                BloodPressureTargetAdapter.this.b(i2, i3, (List) obj);
            }
        });
    }

    public /* synthetic */ void b(int i, int i2, List list) {
        LogUtil.a("BloodPressureTargetAdapter", "savePlans code ", Integer.valueOf(i2), " data ", list);
        if (i2 == -1) {
            LogUtil.h("BloodPressureTargetAdapter", "updateAllAlarm failed");
        }
        if (i == 1) {
            return;
        }
        Activity activity = this.g.get();
        if (activity == null) {
            LogUtil.h("BloodPressureTargetAdapter", "savePlans activity is null");
        } else {
            activity.finish();
        }
    }

    public void a(int i) {
        this.d = i;
        notifyDataSetChanged();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: XZ_, reason: merged with bridge method [inline-methods] */
    public Holder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new Holder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_blood_pressure_target_complete, viewGroup, false));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public void onBindViewHolder(final Holder holder, int i) {
        int i2;
        cbk cbkVar = this.b.get(i);
        if (cbkVar == null) {
            return;
        }
        LogUtil.a("BloodPressureTargetAdapter", cbkVar.toString());
        holder.b.setVisibility(8);
        holder.c.setVisibility(0);
        holder.f.setVisibility(0);
        holder.e.setVisibility(8);
        holder.f2479a.setText(cbi.a(cbkVar));
        holder.i.setText(cbi.a(cbkVar.a(), cbkVar.e()));
        ImageView imageView = holder.g;
        if (LanguageUtil.bc(this.c)) {
            i2 = R$drawable.common_ui_arrow_left;
        } else {
            i2 = R$drawable.common_ui_arrow_right;
        }
        imageView.setImageResource(i2);
        if (cbkVar.b() == 0) {
            holder.e.setText(this.c.getString(com.huawei.health.servicesui.R$string.IDS_blood_pressure_customize_title_desc1, 1));
            holder.e.setVisibility(0);
        }
        if (cbkVar.b() != 9 && cbkVar.b() != 0) {
            holder.f.setVisibility(8);
            if (this.d == 1) {
                holder.b.setVisibility(0);
            }
        }
        holder.b.setOnCheckedChangeListener(null);
        if (holder.d != null && this.f2478a != null) {
            holder.d.setOnClickListener(new View.OnClickListener() { // from class: don
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    BloodPressureTargetAdapter.this.XY_(holder, view);
                }
            });
        }
        holder.b.setChecked(this.e.get(cbkVar).booleanValue());
    }

    public /* synthetic */ void XY_(Holder holder, View view) {
        this.f2478a.onItemClick(view, holder.getLayoutPosition());
        ViewClickInstrumentation.clickOnView(view);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        List<cbk> list = this.b;
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    public static class Holder extends RecyclerView.ViewHolder {

        /* renamed from: a, reason: collision with root package name */
        private HealthTextView f2479a;
        private HealthCheckBox b;
        private HealthDivider c;
        private ConstraintLayout d;
        private HealthTextView e;
        private HealthTextView f;
        private ImageView g;
        private HealthTextView i;

        public Holder(View view) {
            super(view);
            a();
        }

        private void a() {
            this.f2479a = (HealthTextView) this.itemView.findViewById(R.id.plan_name);
            this.f = (HealthTextView) this.itemView.findViewById(R.id.plan_necessity);
            this.e = (HealthTextView) this.itemView.findViewById(R.id.plan_desc);
            this.i = (HealthTextView) this.itemView.findViewById(R.id.plan_time);
            this.g = (ImageView) this.itemView.findViewById(R.id.time_arrow);
            this.c = (HealthDivider) this.itemView.findViewById(R.id.item_divider);
            this.b = (HealthCheckBox) this.itemView.findViewById(R.id.checkbox);
            this.d = (ConstraintLayout) this.itemView.findViewById(R.id.item_blood_pressure_target_layout);
        }
    }

    public void b(OnItemClickListener onItemClickListener) {
        this.f2478a = onItemClickListener;
    }

    public List<cbk> c() {
        return this.b;
    }

    public void e(int i) {
        if (i != 0 && i != this.b.size() - 1) {
            this.e.put(this.b.get(i), Boolean.valueOf(!this.e.get(this.b.get(i)).booleanValue()));
        }
        notifyDataSetChanged();
    }
}
