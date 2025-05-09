package com.huawei.ui.main.stories.soical.store;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.recyclerview.widget.RecyclerView;
import com.huawei.health.R;
import com.huawei.health.messagecenter.model.MessageExt;
import com.huawei.health.suggestion.FitnessNavigationApi;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwcloudmodel.callback.GrsQueryCallback;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.utils.ActivityHtmlPathApi;
import com.huawei.operation.utils.OperationWebActivityIntentBuilderApi;
import com.huawei.pluginachievement.AchieveNavigationApi;
import com.huawei.ui.commonui.divider.HealthDivider;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.main.stories.soical.store.SocialMessageAdapter;
import defpackage.ixx;
import defpackage.nrf;
import defpackage.nsn;
import defpackage.sdl;
import health.compact.a.GRSManager;
import health.compact.a.Services;
import java.util.HashMap;
import java.util.List;

/* loaded from: classes7.dex */
public class SocialMessageAdapter extends RecyclerView.Adapter<SocialMessageHolder> {

    /* renamed from: a, reason: collision with root package name */
    private final Context f10491a;
    private final int c;
    private final List<MessageExt> e;
    private final int g;
    private long d = 0;
    private final OperationWebActivityIntentBuilderApi i = (OperationWebActivityIntentBuilderApi) Services.a("PluginOperation", OperationWebActivityIntentBuilderApi.class);
    private final AchieveNavigationApi h = (AchieveNavigationApi) Services.a("PluginAchievement", AchieveNavigationApi.class);
    private final FitnessNavigationApi b = (FitnessNavigationApi) Services.a("PluginFitnessAdvice", FitnessNavigationApi.class);

    public SocialMessageAdapter(Context context, List<MessageExt> list, int i, int i2) {
        this.f10491a = context;
        this.e = list;
        this.c = i;
        this.g = i2;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: dTb_, reason: merged with bridge method [inline-methods] */
    public SocialMessageHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View inflate;
        if (i == 1 || i == 2 || i == 3) {
            inflate = LayoutInflater.from(this.f10491a).inflate(R.layout.item_social_image_grid, viewGroup, false);
        } else if (i == 4) {
            inflate = LayoutInflater.from(this.f10491a).inflate(R.layout.item_social_image_text_list, viewGroup, false);
        } else {
            inflate = LayoutInflater.from(this.f10491a).inflate(R.layout.item_social_image_horizontal, viewGroup, false);
        }
        return new SocialMessageHolder(inflate, i);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemViewType(int i) {
        return this.c;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: d, reason: merged with bridge method [inline-methods] */
    public void onBindViewHolder(SocialMessageHolder socialMessageHolder, final int i) {
        List<MessageExt> list = this.e;
        if (list == null || i >= list.size()) {
            return;
        }
        nrf.cIS_(socialMessageHolder.d, this.e.get(i).getImgUrl(), nrf.e, 0, 0);
        int i2 = this.c;
        if (i2 == 4) {
            e(socialMessageHolder, i);
        } else if (i2 == 0 || i2 > 4 || i2 < 0) {
            a(socialMessageHolder, i);
        } else {
            LogUtil.h("SocialMessageAdapter", "no branch!");
        }
        final int jumpType = this.e.get(i).getJumpType();
        socialMessageHolder.c.setOnClickListener(new View.OnClickListener() { // from class: rxo
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SocialMessageAdapter.this.dTa_(jumpType, i, view);
            }
        });
    }

    public /* synthetic */ void dTa_(int i, int i2, View view) {
        if (System.currentTimeMillis() - this.d >= 1000) {
            this.d = System.currentTimeMillis();
            b(i, i2);
            b(i2);
            ViewClickInstrumentation.clickOnView(view);
            return;
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    private void e(SocialMessageHolder socialMessageHolder, int i) {
        String description = this.e.get(i).getDescription();
        if (!TextUtils.isEmpty(description)) {
            socialMessageHolder.g.setText(description);
        }
        socialMessageHolder.f.setText(sdl.d(this.e.get(i).getBeginTime(), this.f10491a));
        socialMessageHolder.f.setVisibility(8);
        if (i != this.e.size() - 1) {
            socialMessageHolder.f10492a.setVisibility(0);
        } else {
            socialMessageHolder.f10492a.setVisibility(4);
        }
    }

    private void a(SocialMessageHolder socialMessageHolder, int i) {
        if (i == 0) {
            socialMessageHolder.e.setVisibility(0);
        } else {
            socialMessageHolder.e.setVisibility(8);
        }
        if (i == this.e.size() - 1) {
            socialMessageHolder.j.setVisibility(0);
        } else {
            socialMessageHolder.j.setVisibility(8);
        }
    }

    private void b(int i, int i2) {
        if (i2 < this.e.size()) {
            if (i == 0) {
                String jumpUrl = this.e.get(i2).getJumpUrl();
                if (TextUtils.isEmpty(jumpUrl)) {
                    return;
                }
                try {
                    Context context = this.f10491a;
                    context.startActivity(this.i.builder(context, jumpUrl).build());
                    return;
                } catch (ActivityNotFoundException e) {
                    LogUtil.b("SocialMessageAdapter", "mOperationWebActivity NotFound ", e.getMessage());
                    return;
                }
            }
            if (i == 1) {
                final String jumpUrl2 = this.e.get(i2).getJumpUrl();
                GRSManager.a(this.f10491a).e("messageCenterUrl", new GrsQueryCallback() { // from class: com.huawei.ui.main.stories.soical.store.SocialMessageAdapter.3
                    @Override // com.huawei.hwcloudmodel.callback.GrsQueryCallback
                    public void onCallBackSuccess(String str) {
                        LogUtil.c("SocialMessageAdapter", "GRSManager onCallBackSuccess url = ", str);
                        SocialMessageAdapter.this.b.gotoFitnessPage(jumpUrl2, str + "/messageH5/html/launchFitness.html?url=", SocialMessageAdapter.this.f10491a);
                    }

                    @Override // com.huawei.hwcloudmodel.callback.GrsQueryCallback
                    public void onCallBackFail(int i3) {
                        LogUtil.c("SocialMessageAdapter", "GRSManager onCallBackFail code = ", Integer.valueOf(i3));
                    }
                });
                return;
            }
            if (i == 2) {
                this.h.showAchieveReport(this.f10491a);
                return;
            }
            if (i == 3) {
                this.h.showAchieveMedal(this.f10491a);
                return;
            }
            if (i == 4) {
                this.b.startMoreFitness(this.f10491a);
            } else if (i == 5) {
                this.h.showAchieveKaka(this.f10491a);
            } else {
                d();
            }
        }
    }

    private void d() {
        GRSManager.a(this.f10491a).e("domainContentcenterDbankcdnNew", new GrsQueryCallback() { // from class: com.huawei.ui.main.stories.soical.store.SocialMessageAdapter.4
            @Override // com.huawei.hwcloudmodel.callback.GrsQueryCallback
            public void onCallBackSuccess(String str) {
                LogUtil.c("SocialMessageAdapter", "GRSManager onCallBackSuccess url = ", str);
                try {
                    SocialMessageAdapter.this.f10491a.startActivity(SocialMessageAdapter.this.i.builder(SocialMessageAdapter.this.f10491a, str + ((ActivityHtmlPathApi) Services.c("PluginOperation", ActivityHtmlPathApi.class)).getActivityHtmlPath() + "lowVersionTips.html").build());
                } catch (ActivityNotFoundException e) {
                    LogUtil.b("SocialMessageAdapter", "ActivityNotFoundException", e.getMessage());
                }
            }

            @Override // com.huawei.hwcloudmodel.callback.GrsQueryCallback
            public void onCallBackFail(int i) {
                LogUtil.c("SocialMessageAdapter", "GRSManager onCallBackFail code = ", Integer.valueOf(i));
            }
        });
    }

    private void b(int i) {
        HashMap hashMap = new HashMap();
        hashMap.put("click", "1");
        if (i < this.e.size()) {
            hashMap.put("title", this.e.get(i).getTitle());
            hashMap.put("description", this.e.get(i).getDescription());
            if (this.g == 27) {
                hashMap.put("jumpType", Integer.valueOf(this.e.get(i).getJumpType()));
                ixx.d().d(this.f10491a, AnalyticsValue.HEALTH_WONDERFUL_EVENT_SCROLL_2020025.value(), hashMap, 0);
            } else {
                ixx.d().d(this.f10491a, AnalyticsValue.HEALTH_UNMISSABLE_TOPIC_SCROLL_2020024.value(), hashMap, 0);
            }
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        List<MessageExt> list = this.e;
        if (list != null) {
            return list.size();
        }
        return 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int b(Context context, float f) {
        return (int) ((f * context.getResources().getDisplayMetrics().density) + 0.5f);
    }

    public class SocialMessageHolder extends RecyclerView.ViewHolder {

        /* renamed from: a, reason: collision with root package name */
        private HealthDivider f10492a;
        private final LinearLayout c;
        private final ImageView d;
        private View e;
        private HealthTextView f;
        private HealthTextView g;
        private View j;

        public SocialMessageHolder(View view, int i) {
            super(view);
            int n = nsn.n();
            this.c = (LinearLayout) view.findViewById(R.id.item_social_message_layout);
            this.d = (ImageView) view.findViewById(R.id.img_item_social);
            if (i != 1) {
                if (i == 2) {
                    if (nsn.ag(view.getContext())) {
                        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
                        layoutParams.height = (int) ((((n - SocialMessageAdapter.this.b(SocialMessageAdapter.this.f10491a, 40.0f)) / 4.0f) / 2.0f) + SocialMessageAdapter.this.b(SocialMessageAdapter.this.f10491a, 8.0f));
                        view.setLayoutParams(layoutParams);
                        LogUtil.c("SocialMessageAdapter", "SocialMessageHodler width = ", Integer.valueOf(layoutParams.width), ", height = ", Integer.valueOf(layoutParams.height));
                        return;
                    }
                    return;
                }
                if (i == 3) {
                    if (nsn.ag(view.getContext())) {
                        ViewGroup.LayoutParams layoutParams2 = view.getLayoutParams();
                        layoutParams2.height = (int) ((((n - SocialMessageAdapter.this.b(SocialMessageAdapter.this.f10491a, 32.0f)) / 3.0f) / 2.0f) + SocialMessageAdapter.this.b(SocialMessageAdapter.this.f10491a, 8.0f));
                        view.setLayoutParams(layoutParams2);
                        LogUtil.c("SocialMessageAdapter", "SocialMessageHodler width = ", Integer.valueOf(layoutParams2.width), ", height = ", Integer.valueOf(layoutParams2.height));
                        return;
                    }
                    return;
                }
                if (i == 4) {
                    this.g = (HealthTextView) view.findViewById(R.id.tv_item_describe);
                    this.f = (HealthTextView) view.findViewById(R.id.tv_item_date);
                    this.f10492a = (HealthDivider) view.findViewById(R.id.img_item_line);
                } else {
                    this.e = view.findViewById(R.id.item_padding_left);
                    this.j = view.findViewById(R.id.item_padding_right);
                }
            }
        }
    }
}
