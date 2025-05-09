package com.huawei.ui.main.stories.privacy.template.view.component;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.huawei.health.R;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.cardview.HealthCardView;
import com.huawei.ui.commonui.recycleview.HealthRecycleView;
import com.huawei.ui.main.stories.privacy.template.contract.PrivacyDetailFragmentContract;
import com.huawei.ui.main.stories.privacy.template.model.adapter.DayDataViewAdapter;

/* loaded from: classes7.dex */
public class PrivacyDayDataView extends LinearLayout implements BaseComponent {

    /* renamed from: a, reason: collision with root package name */
    private Context f10428a;
    private HealthRecycleView b;
    private HealthCardView c;

    @Override // com.huawei.ui.main.stories.privacy.template.view.component.BaseComponent
    public View getView(Context context) {
        return this;
    }

    @Override // com.huawei.ui.main.stories.privacy.template.view.component.BaseComponent
    public void setPresenter(PrivacyDetailFragmentContract.PrivacyFragmentPresenter privacyFragmentPresenter) {
    }

    public PrivacyDayDataView(Context context) {
        this(context, null);
    }

    public PrivacyDayDataView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f10428a = context;
        c();
    }

    private void c() {
        Context context = this.f10428a;
        if (context != null) {
            LayoutInflater from = LayoutInflater.from(context);
            if (from != null) {
                from.inflate(R.layout.privacy_day_data_view, this);
            }
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.f10428a);
            this.c = (HealthCardView) findViewById(R.id.privacy_card_view);
            HealthRecycleView healthRecycleView = (HealthRecycleView) findViewById(R.id.privacy_day_data_recycle_view);
            this.b = healthRecycleView;
            healthRecycleView.setLayoutManager(linearLayoutManager);
            this.b.a(false);
            this.b.d(false);
            setBackgroundColor(getResources().getColor(R.color._2131299296_res_0x7f090be0));
            return;
        }
        LogUtil.h("PrivacyDayDataView", "mContext is null");
    }

    public void setAdapter(DayDataViewAdapter dayDataViewAdapter) {
        this.b.setAdapter(dayDataViewAdapter);
    }

    public void setCardViewBackground(Drawable drawable) {
        this.c.setBackground(drawable);
    }
}
