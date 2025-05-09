package com.huawei.ui.commonui.imageview;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.util.AttributeSet;
import androidx.appcompat.widget.AppCompatImageView;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.utils.Constants;
import defpackage.nsn;

/* loaded from: classes6.dex */
public class HealthImageView extends AppCompatImageView {
    public HealthImageView(Context context) {
        super(context);
    }

    public HealthImageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public HealthImageView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    @Override // android.widget.ImageView, android.view.View
    protected void onDraw(Canvas canvas) {
        if (nsn.cLi_(getDrawable())) {
            try {
                int id = getId();
                LogUtil.b("HealthImageView", "onDraw recycled bitmap exception happened, id = ", id != 0 ? getResources().getResourceName(id) : Constants.NULL);
                return;
            } catch (Resources.NotFoundException unused) {
                LogUtil.h("HealthImageView", "get resource by id fail");
                return;
            }
        }
        super.onDraw(canvas);
    }
}
