package com.huawei.watchface.videoedit.gles.template;

import android.content.Context;
import com.huawei.watchface.videoedit.gles.glutils.ModelUtils;
import com.huawei.watchface.videoedit.param.TemplateModel;
import com.huawei.watchface.videoedit.presenter.ModelVisitor;

/* loaded from: classes9.dex */
public class RatioVisitor extends ModelVisitor<int[]> {
    private Context mContext;

    public RatioVisitor(Context context, int i) {
        super(i);
        this.mContext = context;
    }

    /* JADX WARN: Type inference failed for: r2v1, types: [T, int[]] */
    @Override // com.huawei.watchface.videoedit.presenter.ModelVisitor
    public void onDataSetChanged(TemplateModel templateModel) {
        this.selfStorage = ModelUtils.getModelHeightAndWidth(this.mContext, templateModel);
    }
}
