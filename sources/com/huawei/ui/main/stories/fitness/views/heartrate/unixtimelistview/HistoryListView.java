package com.huawei.ui.main.stories.fitness.views.heartrate.unixtimelistview;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import com.huawei.ui.main.stories.fitness.views.heartrate.unixtimelistview.model.icommon.IHistoryModel;

/* loaded from: classes6.dex */
public class HistoryListView extends ExpandableListView {
    public HistoryListView(Context context) {
        super(context);
    }

    public HistoryListView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public HistoryListView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public static abstract class DetailView extends LinearLayout {
        public abstract void load(IHistoryModel iHistoryModel);

        public DetailView(Context context) {
            super(context);
        }
    }
}
