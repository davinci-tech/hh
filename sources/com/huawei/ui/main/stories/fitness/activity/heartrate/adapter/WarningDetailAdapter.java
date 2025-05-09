package com.huawei.ui.main.stories.fitness.activity.heartrate.adapter;

import com.huawei.health.R;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.adapter.BaseRecyclerAdapter;
import com.huawei.ui.commonui.adapter.RecyclerHolder;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.main.stories.fitness.activity.heartrate.helper.WarningListData;
import java.util.List;

/* loaded from: classes6.dex */
public class WarningDetailAdapter extends BaseRecyclerAdapter<WarningListData> {
    public WarningDetailAdapter(List<WarningListData> list) {
        super(list, R.layout.item_heart_rate_warning_details);
    }

    @Override // com.huawei.ui.commonui.adapter.BaseRecyclerAdapter
    /* renamed from: e, reason: merged with bridge method [inline-methods] */
    public void convert(RecyclerHolder recyclerHolder, int i, WarningListData warningListData) {
        if (warningListData == null) {
            LogUtil.h("HealthHeartRate_WarningDetailAdapter", "convert itemData is null");
            return;
        }
        ((HealthTextView) recyclerHolder.cwK_(R.id.item_warning_hr_title)).setText(warningListData.getTitle());
        ((HealthTextView) recyclerHolder.cwK_(R.id.item_warning_hr_time)).setText(warningListData.getDateTime());
        if (i == getItemCount() - 1) {
            recyclerHolder.cwK_(R.id.item_warning_hr_line).setVisibility(8);
        } else {
            recyclerHolder.cwK_(R.id.item_warning_hr_line).setVisibility(0);
        }
    }
}
