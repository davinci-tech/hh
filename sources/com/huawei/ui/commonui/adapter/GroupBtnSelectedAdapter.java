package com.huawei.ui.commonui.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import androidx.recyclerview.widget.RecyclerView;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.R$color;
import com.huawei.ui.commonui.R$drawable;
import com.huawei.ui.commonui.adapter.GroupBtnSelectedAdapter;
import com.huawei.ui.commonui.button.HealthButton;
import defpackage.jcf;
import defpackage.koq;
import health.compact.a.ReleaseLogUtil;

/* loaded from: classes6.dex */
public class GroupBtnSelectedAdapter extends RecyclerView.Adapter<ViewHolder> {

    /* renamed from: a, reason: collision with root package name */
    private int f8759a;
    private Drawable b;
    private int c;
    private Drawable d;
    private String[] e;
    private boolean[] f;
    private Context h;
    private int i;
    private OnItemClickListener j;

    public interface OnItemClickListener {
        void onItemClick(View view, HealthButton healthButton, int i);
    }

    public GroupBtnSelectedAdapter(Context context, String[] strArr, boolean[] zArr) {
        this(context, strArr, zArr, R.layout.item_train_date_select);
    }

    public GroupBtnSelectedAdapter(Context context, String[] strArr, boolean[] zArr, int i) {
        this.h = context;
        b(strArr);
        b(zArr);
        this.i = i;
        e();
    }

    private void b(String[] strArr) {
        if (strArr == null) {
            return;
        }
        this.e = strArr;
    }

    private void b(boolean[] zArr) {
        if (zArr == null) {
            return;
        }
        this.f = zArr;
    }

    private void e() {
        this.d = this.h.getResources().getDrawable(R$drawable.blood_pressure_plan_selected_bg);
        this.f8759a = this.h.getResources().getColor(R$color.textColorPrimaryInverse);
        this.b = this.h.getResources().getDrawable(R$drawable.train_date_unselect_bg);
        this.c = this.h.getResources().getColor(R$color.textColorPrimary);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: cwH_, reason: merged with bridge method [inline-methods] */
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(this.h).inflate(this.i, viewGroup, false));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: c, reason: merged with bridge method [inline-methods] */
    public void onBindViewHolder(final ViewHolder viewHolder, final int i) {
        if (koq.b(this.e, i)) {
            LogUtil.h("Suggestion_GroupBtnSelectedAdapter", "onBindViewHolder position out of bounds.");
            return;
        }
        viewHolder.b.setText(this.e[i]);
        cwF_(viewHolder.itemView, viewHolder.b, i);
        viewHolder.b.setOnClickListener(new View.OnClickListener() { // from class: nkt
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                GroupBtnSelectedAdapter.this.cwG_(viewHolder, i, view);
            }
        });
    }

    public /* synthetic */ void cwG_(ViewHolder viewHolder, int i, View view) {
        this.j.onItemClick(viewHolder.itemView, viewHolder.b, i);
        ViewClickInstrumentation.clickOnView(view);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        String[] strArr = this.e;
        if (strArr == null || strArr.length <= 0) {
            return 0;
        }
        return strArr.length;
    }

    public void cwF_(View view, HealthButton healthButton, int i) {
        if (healthButton == null) {
            ReleaseLogUtil.a("Suggestion_GroupBtnSelectedAdapter", "changeSelectStatus button is null");
            return;
        }
        boolean[] zArr = this.f;
        if (zArr == null || i < 0 || i >= zArr.length) {
            ReleaseLogUtil.a("Suggestion_GroupBtnSelectedAdapter", "changeSelectStatus mIsSelectedArrays ", zArr, " position ", Integer.valueOf(i));
            return;
        }
        if (zArr[i]) {
            healthButton.setBackground(this.d);
            healthButton.setTextColor(this.f8759a);
        } else {
            healthButton.setBackground(this.b);
            healthButton.setTextColor(this.c);
        }
        if (koq.b(this.e, i)) {
            ReleaseLogUtil.a("Suggestion_GroupBtnSelectedAdapter", "changeSelectStatus position ", Integer.valueOf(i), " mBtnNames ", this.e);
        } else {
            jcf.bEx_(view, this.e[i], this.f[i], CheckBox.class);
        }
    }

    public void cwI_(Drawable drawable, int i) {
        this.b = drawable;
        this.c = i;
    }

    public void cwJ_(Drawable drawable, int i) {
        this.d = drawable;
        this.f8759a = i;
    }

    public void c(OnItemClickListener onItemClickListener) {
        this.j = onItemClickListener;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private HealthButton b;

        public ViewHolder(View view) {
            super(view);
            this.b = (HealthButton) view.findViewById(R.id.btn_text);
        }
    }
}
