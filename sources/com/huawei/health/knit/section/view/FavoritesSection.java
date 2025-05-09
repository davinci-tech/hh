package com.huawei.health.knit.section.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import com.huawei.health.R;
import com.huawei.health.knit.section.listener.IViewCreatedCallback;
import defpackage.nru;
import java.util.HashMap;

/* loaded from: classes3.dex */
public class FavoritesSection extends BaseSection {

    /* renamed from: a, reason: collision with root package name */
    private LinearLayout f2625a;
    private Context c;
    private LinearLayout e;

    @Override // com.huawei.health.knit.section.view.BaseSection
    protected boolean isSupportSafeRegion() {
        return false;
    }

    @Override // com.huawei.health.knit.section.view.BaseSection
    public boolean isSupportShare() {
        return false;
    }

    public FavoritesSection(Context context) {
        super(context);
    }

    public FavoritesSection(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public FavoritesSection(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    @Override // com.huawei.health.knit.section.view.BaseSection
    public View onCreateView(Context context) {
        this.c = context;
        View inflate = LayoutInflater.from(context).inflate(R.layout.favorites_layout, (ViewGroup) this, false);
        this.f2625a = (LinearLayout) inflate.findViewById(R.id.sleep_day_operation_config_layout);
        this.e = (LinearLayout) inflate.findViewById(R.id.sleep_favorites_audios);
        return inflate;
    }

    @Override // com.huawei.health.knit.section.view.BaseSection
    protected void bindParamsToView(HashMap<String, Object> hashMap) {
        IViewCreatedCallback iViewCreatedCallback = (IViewCreatedCallback) nru.c(hashMap, "CONFIG_CALL_BACK", IViewCreatedCallback.class, null);
        if (iViewCreatedCallback != null) {
            iViewCreatedCallback.onLayoutUpdate(this.f2625a);
        }
        IViewCreatedCallback iViewCreatedCallback2 = (IViewCreatedCallback) nru.c(hashMap, "FAVORITES_CALL_BACK", IViewCreatedCallback.class, null);
        if (iViewCreatedCallback2 != null) {
            iViewCreatedCallback2.onLayoutUpdate(this.e);
        }
    }

    @Override // com.huawei.health.knit.section.view.BaseSection
    public String getLogTag() {
        return "FavoritesSection";
    }
}
