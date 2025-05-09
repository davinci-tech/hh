package com.huawei.ui.main.stories.soical.views;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.huawei.health.R;
import com.huawei.health.messagecenter.model.CommonUtil;
import com.huawei.health.messagecenter.model.MessageObject;
import com.huawei.health.suggestion.ui.view.BounceScrollView;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.utils.OperationWebActivityIntentBuilderApi;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.recycleview.HealthRecycleView;
import defpackage.ixx;
import health.compact.a.LanguageUtil;
import health.compact.a.Services;
import java.util.HashMap;

/* loaded from: classes9.dex */
public class SocialRecyclerview extends LinearLayout implements View.OnClickListener {

    /* renamed from: a, reason: collision with root package name */
    private ImageView f10508a;
    private OperationWebActivityIntentBuilderApi b;
    private MessageObject c;
    private boolean d;
    private Context e;
    private HealthTextView f;
    private HealthRecycleView g;
    private BounceScrollView h;
    private HealthRecycleView i;
    private HealthTextView j;

    public SocialRecyclerview(Context context) {
        super(context);
        this.d = false;
        c(context);
    }

    public SocialRecyclerview(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.d = false;
        c(context);
    }

    public SocialRecyclerview(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.d = false;
        c(context);
    }

    private void c(Context context) {
        this.e = context;
        LayoutInflater.from(context).inflate(R.layout.recycler_view_social, this);
        this.f = (HealthTextView) findViewById(R.id.social_recycler_title);
        this.j = (HealthTextView) findViewById(R.id.social_recycler_more);
        this.f10508a = (ImageView) findViewById(R.id.social_recycler_arrow);
        this.g = (HealthRecycleView) findViewById(R.id.social_recycler_view);
        ((RelativeLayout) findViewById(R.id.social_recycler_title_layout)).setOnClickListener(this);
        this.i = (HealthRecycleView) findViewById(R.id.social_recycler_view_horizontal);
        this.h = (BounceScrollView) findViewById(R.id.social_scrollView_horizontal);
        if (LanguageUtil.bc(this.e)) {
            this.f10508a.setBackgroundResource(R.drawable._2131427841_res_0x7f0b0201);
        } else {
            this.f10508a.setBackgroundResource(R.drawable.ic_health_list_arrow_gray);
        }
        this.g.setNestedScrollingEnabled(false);
        this.g.setHasFixedSize(true);
        this.b = (OperationWebActivityIntentBuilderApi) Services.a("PluginOperation", OperationWebActivityIntentBuilderApi.class);
    }

    private void setImageTextLayout(SocialMessageAdapter socialMessageAdapter) {
        this.g.setLayoutManager(new LinearLayoutManager(this.e) { // from class: com.huawei.ui.main.stories.soical.views.SocialRecyclerview.4
            @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.LayoutManager
            public boolean canScrollHorizontally() {
                return false;
            }

            @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.LayoutManager
            public boolean canScrollVertically() {
                return false;
            }
        });
        this.g.setAdapter(socialMessageAdapter);
        this.h.setVisibility(8);
        this.g.setVisibility(0);
    }

    public RecyclerView getRecyclerView() {
        return this.g;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        MessageObject messageObject = this.c;
        if (messageObject != null && !TextUtils.isEmpty(messageObject.getDetailUri())) {
            try {
                Context context = this.e;
                context.startActivity(this.b.builder(context, this.c.getDetailUri()).build());
            } catch (ActivityNotFoundException e) {
                LogUtil.a("SocialRecyclerview", "ActivityNotFoundException", e.getMessage());
            }
            HashMap hashMap = new HashMap();
            hashMap.put("click", "1");
            hashMap.put("msgId", this.c.getMsgId());
            hashMap.put(CommonUtil.MSG_TITLE, this.c.getMsgTitle());
            if (this.c.getMsgPosition() == 27) {
                ixx.d().d(this.e, AnalyticsValue.HEALTH_WONDERFUL_EVENT_MORE_SCROLL_2020026.value(), hashMap, 0);
            } else {
                ixx.d().d(this.e, AnalyticsValue.HEALTH_UNMISSABLE_TOPIC_MORE_SCROLL_2020027.value(), hashMap, 0);
            }
        }
        ViewClickInstrumentation.clickOnView(view);
    }
}
