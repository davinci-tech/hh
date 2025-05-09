package com.huawei.ui.device.activity.donotdisturb;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.huawei.health.R;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.uikit.hwsubheader.widget.HwSubHeader;

/* loaded from: classes6.dex */
public class NoDisturbSettingSubHeaderAdapter extends HwSubHeader.SubHeaderRecyclerAdapter {
    private int b;
    private Context e;

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return 1;
    }

    @Override // com.huawei.uikit.hwsubheader.widget.HwSubHeader.SubHeaderRecyclerAdapter
    public int getItemType(int i) {
        return 1;
    }

    public NoDisturbSettingSubHeaderAdapter(Context context, int i) {
        this.e = context;
        this.b = i;
    }

    @Override // com.huawei.uikit.hwsubheader.widget.HwSubHeader.SubHeaderRecyclerAdapter
    public View getHeaderViewAsPos(int i, Context context) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.hwsubhearder_alarm, (ViewGroup) null, false);
        onBindViewHolder(new HeaderHolder(inflate), i);
        return inflate;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        if (i == 1) {
            return new HeaderHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.hwsubhearder_alarm, viewGroup, false));
        }
        return null;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        if ((viewHolder instanceof HeaderHolder) && this.b == 0) {
            ((HeaderHolder) viewHolder).b.setText(this.e.getResources().getString(R.string._2130842350_res_0x7f0212ee));
        }
    }

    public static class HeaderHolder extends RecyclerView.ViewHolder {
        HealthTextView b;

        public HeaderHolder(View view) {
            super(view);
            this.b = (HealthTextView) view.findViewById(R.id.heanth_data_subheader);
        }
    }
}
