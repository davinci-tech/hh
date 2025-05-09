package com.huawei.ui.homehealth.functionsetcardmanagement;

import android.content.Context;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import androidx.core.view.MotionEventCompat;
import com.huawei.health.R;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.homehealth.homeinterface.OnStartDragListener;
import com.huawei.ui.homehealth.refreshcard.CardViewHolder;
import defpackage.ojs;
import defpackage.owo;

/* loaded from: classes6.dex */
public class FunctionSetCardManagementViewHolder extends CardViewHolder {
    private ImageView b;
    private final HealthTextView d;
    private final OnStartDragListener e;

    FunctionSetCardManagementViewHolder(View view, Context context, boolean z, OnStartDragListener onStartDragListener) {
        super(view, context, z);
        this.d = (HealthTextView) view.findViewById(R.id.itemText);
        ImageView imageView = (ImageView) view.findViewById(R.id.itemhandle);
        this.e = onStartDragListener;
        this.b = (ImageView) view.findViewById(R.id.itemDelete);
        imageView.setOnTouchListener(new View.OnTouchListener() { // from class: com.huawei.ui.homehealth.functionsetcardmanagement.FunctionSetCardManagementViewHolder.4
            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View view2, MotionEvent motionEvent) {
                if (FunctionSetCardManagementViewHolder.this.e != null && MotionEventCompat.getActionMasked(motionEvent) == 0) {
                    FunctionSetCardManagementViewHolder.this.e.onStartDrag(FunctionSetCardManagementViewHolder.this);
                }
                return false;
            }
        });
    }

    public void d(ojs ojsVar) {
        owo.e(ojsVar, this.d);
    }

    public ImageView dcc_() {
        return this.b;
    }
}
