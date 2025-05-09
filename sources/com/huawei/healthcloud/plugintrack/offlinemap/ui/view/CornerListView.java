package com.huawei.healthcloud.plugintrack.offlinemap.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ListView;
import com.huawei.health.R;

/* loaded from: classes8.dex */
public class CornerListView extends ListView {
    public CornerListView(Context context) {
        super(context);
    }

    public CornerListView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public CornerListView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    @Override // android.widget.AbsListView, android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        int pointToPosition;
        if (motionEvent == null) {
            return false;
        }
        if (motionEvent.getAction() == 0 && (pointToPosition = pointToPosition((int) motionEvent.getX(), (int) motionEvent.getY())) != -1) {
            if (pointToPosition == 0) {
                if (pointToPosition == getAdapter().getCount() - 1) {
                    setSelector(R.drawable._2131431910_res_0x7f0b11e6);
                } else {
                    setSelector(R.drawable._2131431907_res_0x7f0b11e3);
                }
            } else if (pointToPosition == getAdapter().getCount() - 1) {
                setSelector(R.drawable._2131431908_res_0x7f0b11e4);
            } else {
                setSelector(R.drawable._2131431909_res_0x7f0b11e5);
            }
        }
        return super.onInterceptTouchEvent(motionEvent);
    }
}
