package com.huawei.ui.device.adapter.smsquickreply;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.recyclerview.widget.RecyclerView;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import java.util.Collections;
import java.util.List;

/* loaded from: classes6.dex */
public class SmsItemMoveAdapter extends RecyclerView.Adapter<SlideViewHolder> implements ItemTouchHelperAdapter {

    /* renamed from: a, reason: collision with root package name */
    private OnMessageOperationListener f9284a;
    private final LayoutInflater b;
    private Context d;
    private List<String> e;

    public interface OnMessageOperationListener {
        void onMessageDeleteListener(View view, int i);

        void onMessageEditListener(View view, int i);
    }

    public SmsItemMoveAdapter(Context context, List<String> list) {
        this.d = context;
        this.e = list;
        this.b = LayoutInflater.from(context);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: cTr_, reason: merged with bridge method [inline-methods] */
    public SlideViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new SlideViewHolder(this.b.inflate(R.layout.activity_sms_list_item, viewGroup, false));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: b, reason: merged with bridge method [inline-methods] */
    public void onBindViewHolder(SlideViewHolder slideViewHolder, final int i) {
        slideViewHolder.d.setText(this.e.get(i));
        slideViewHolder.e.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.ui.device.adapter.smsquickreply.SmsItemMoveAdapter.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (SmsItemMoveAdapter.this.f9284a != null) {
                    SmsItemMoveAdapter.this.f9284a.onMessageDeleteListener(view, i);
                }
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        slideViewHolder.d.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.ui.device.adapter.smsquickreply.SmsItemMoveAdapter.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (SmsItemMoveAdapter.this.f9284a != null) {
                    SmsItemMoveAdapter.this.f9284a.onMessageEditListener(view, i);
                }
                ViewClickInstrumentation.clickOnView(view);
            }
        });
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.e.size();
    }

    @Override // com.huawei.ui.device.adapter.smsquickreply.ItemTouchHelperAdapter
    public void onItemMove(int i, int i2) {
        List<String> list = this.e;
        if (list == null) {
            LogUtil.h("SmsListSlideAdapter", "onItemMove mDataList is null");
            return;
        }
        if (i < 0 || i >= list.size()) {
            LogUtil.h("SmsListSlideAdapter", "onItemMove fromPosition IndexOutOfBoundsException");
        } else if (i2 < 0 || i2 >= this.e.size()) {
            LogUtil.h("SmsListSlideAdapter", "onItemMove toPosition IndexOutOfBoundsException");
        } else {
            Collections.swap(this.e, i, i2);
            notifyItemMoved(i, i2);
        }
    }

    public void c(OnMessageOperationListener onMessageOperationListener) {
        this.f9284a = onMessageOperationListener;
    }

    public static class SlideViewHolder extends RecyclerView.ViewHolder {
        private HealthTextView d;
        private ImageView e;

        public SlideViewHolder(View view) {
            super(view);
            this.d = (HealthTextView) view.findViewById(R.id.message_content);
            this.e = (ImageView) view.findViewById(R.id.delete_button);
        }
    }
}
