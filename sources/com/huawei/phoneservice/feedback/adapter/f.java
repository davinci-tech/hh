package com.huawei.phoneservice.feedback.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import java.util.List;

/* loaded from: classes5.dex */
public class f extends RecyclerView.Adapter<a> {
    private List<com.huawei.phoneservice.feedback.entity.c> b;
    private int c = -1;
    private c d;
    private LayoutInflater e;

    public interface c {
        void a(int i);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.b.size();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: b, reason: merged with bridge method [inline-methods] */
    public void onBindViewHolder(a aVar, int i) {
        TextView textView;
        int i2;
        if (i == this.c) {
            textView = aVar.e;
            i2 = R.drawable.feedback_common_toggle_4_corner_press_bg;
        } else {
            textView = aVar.e;
            i2 = R.drawable.feedback_common_toggle_4_corner_normal_bg;
        }
        textView.setBackgroundResource(i2);
        aVar.e.setAccessibilityDelegate(cdL_(i == this.c));
        aVar.e.setText(this.b.get(i).b);
        aVar.e.setOnClickListener(new e(i));
    }

    public void a(c cVar) {
        this.d = cVar;
    }

    public void e(int i) {
        this.c = i;
        notifyDataSetChanged();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: cdM_, reason: merged with bridge method [inline-methods] */
    public a onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new a(this.e.inflate(R.layout.feedback_sdk_question_item_type, viewGroup, false));
    }

    static class a extends RecyclerView.ViewHolder {
        private TextView e;

        a(View view) {
            super(view);
            this.e = (TextView) view.findViewById(R.id.txt_question_type_item);
        }
    }

    class d extends View.AccessibilityDelegate {
        final /* synthetic */ boolean b;

        @Override // android.view.View.AccessibilityDelegate
        public void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfo accessibilityNodeInfo) {
            super.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfo);
            accessibilityNodeInfo.setCheckable(true);
            accessibilityNodeInfo.setChecked(this.b);
        }

        d(boolean z) {
            this.b = z;
        }
    }

    class e implements View.OnClickListener {
        final /* synthetic */ int e;

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            f.this.c = this.e;
            f.this.d.a(this.e);
            f.this.notifyDataSetChanged();
            ViewClickInstrumentation.clickOnView(view);
        }

        e(int i) {
            this.e = i;
        }
    }

    private View.AccessibilityDelegate cdL_(boolean z) {
        return new d(z);
    }

    public f(List<com.huawei.phoneservice.feedback.entity.c> list, Context context) {
        this.b = list;
        this.e = LayoutInflater.from(context);
    }
}
