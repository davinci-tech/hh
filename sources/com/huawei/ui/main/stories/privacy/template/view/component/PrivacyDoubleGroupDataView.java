package com.huawei.ui.main.stories.privacy.template.view.component;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
import com.huawei.health.R;
import com.huawei.ui.main.stories.privacy.template.contract.PrivacyDetailFragmentContract;

/* loaded from: classes7.dex */
public class PrivacyDoubleGroupDataView extends RecyclerView implements BaseComponent {
    @Override // com.huawei.ui.main.stories.privacy.template.view.component.BaseComponent
    public View getView(Context context) {
        return this;
    }

    @Override // com.huawei.ui.main.stories.privacy.template.view.component.BaseComponent
    public void setPresenter(PrivacyDetailFragmentContract.PrivacyFragmentPresenter privacyFragmentPresenter) {
    }

    public PrivacyDoubleGroupDataView(Context context) {
        this(context, null);
    }

    public PrivacyDoubleGroupDataView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R.attr._2131100998_res_0x7f060546);
    }

    public PrivacyDoubleGroupDataView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }
}
