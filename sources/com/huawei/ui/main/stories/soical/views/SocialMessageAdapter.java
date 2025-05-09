package com.huawei.ui.main.stories.soical.views;

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
import defpackage.ixx;
import defpackage.nrf;
import defpackage.nsn;
import defpackage.sdl;
import health.compact.a.GRSManager;
import health.compact.a.Services;
import java.util.HashMap;
import java.util.List;

/* loaded from: classes9.dex */
public class SocialMessageAdapter extends RecyclerView.Adapter<SocialMessageHodler> {

    /* renamed from: a, reason: collision with root package name */
    private Context f10505a;
    private long b;
    private int c;
    private List<MessageExt> d;
    private final FitnessNavigationApi e;
    private int g;
    private final AchieveNavigationApi h;
    private final OperationWebActivityIntentBuilderApi j;

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: dTQ_, reason: merged with bridge method [inline-methods] */
    public SocialMessageHodler onCreateViewHolder(ViewGroup viewGroup, int i) {
        View inflate;
        if (i == 0) {
            inflate = LayoutInflater.from(this.f10505a).inflate(R.layout.item_social_image_horizontal, viewGroup, false);
        } else if (i == 1 || i == 2 || i == 3) {
            inflate = LayoutInflater.from(this.f10505a).inflate(R.layout.item_social_image_grid, viewGroup, false);
        } else if (i == 4) {
            inflate = LayoutInflater.from(this.f10505a).inflate(R.layout.item_social_image_text_list, viewGroup, false);
        } else {
            inflate = LayoutInflater.from(this.f10505a).inflate(R.layout.item_social_image_horizontal, viewGroup, false);
        }
        return new SocialMessageHodler(inflate, i);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemViewType(int i) {
        return this.c;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public void onBindViewHolder(SocialMessageHodler socialMessageHodler, final int i) {
        List<MessageExt> list = this.d;
        if (list == null || i >= list.size()) {
            return;
        }
        if (!nsn.ag(this.f10505a) || this.c != 1 || this.d.get(i).getImgUrlDaxi() == null) {
            nrf.cIS_(socialMessageHodler.d, this.d.get(i).getImgUrl(), nrf.e, 0, 0);
        } else {
            nrf.cIS_(socialMessageHodler.d, this.d.get(i).getImgUrlDaxi(), nrf.e, 0, 0);
        }
        int i2 = this.c;
        if (i2 == 4) {
            d(socialMessageHodler, i);
        } else if (i2 == 0 || i2 > 4 || i2 < 0) {
            c(socialMessageHodler, i);
        } else {
            LogUtil.h("SocialMessageAdapter", "no branch!");
        }
        final int jumpType = this.d.get(i).getJumpType();
        socialMessageHodler.f10507a.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.ui.main.stories.soical.views.SocialMessageAdapter.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (System.currentTimeMillis() - SocialMessageAdapter.this.b >= 1000) {
                    SocialMessageAdapter.this.b = System.currentTimeMillis();
                    SocialMessageAdapter.this.d(jumpType, i);
                    SocialMessageAdapter.this.d(i);
                    ViewClickInstrumentation.clickOnView(view);
                    return;
                }
                ViewClickInstrumentation.clickOnView(view);
            }
        });
    }

    private void d(SocialMessageHodler socialMessageHodler, int i) {
        String description = this.d.get(i).getDescription();
        if (!TextUtils.isEmpty(description)) {
            socialMessageHodler.i.setText(description);
        }
        socialMessageHodler.h.setText(sdl.d(this.d.get(i).getBeginTime(), this.f10505a));
        if (i != this.d.size() - 1) {
            socialMessageHodler.e.setVisibility(0);
        } else {
            socialMessageHodler.e.setVisibility(4);
        }
    }

    private void c(SocialMessageHodler socialMessageHodler, int i) {
        if (i == 0) {
            socialMessageHodler.c.setVisibility(0);
        } else {
            socialMessageHodler.c.setVisibility(8);
        }
        if (i == this.d.size() - 1) {
            socialMessageHodler.f.setVisibility(0);
        } else {
            socialMessageHodler.f.setVisibility(8);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(int i, int i2) {
        if (i2 < this.d.size()) {
            if (i == 0) {
                String jumpUrl = this.d.get(i2).getJumpUrl();
                if (TextUtils.isEmpty(jumpUrl)) {
                    return;
                }
                try {
                    Context context = this.f10505a;
                    context.startActivity(this.j.builder(context, jumpUrl).build());
                    return;
                } catch (ActivityNotFoundException e) {
                    LogUtil.b("SocialMessageAdapter", "ActivityNotFound" + e.getMessage());
                    return;
                }
            }
            if (i == 1) {
                e(i, i2);
                return;
            }
            if (i == 2) {
                this.h.showAchieveReport(this.f10505a);
                return;
            }
            if (i == 3) {
                this.h.showAchieveMedal(this.f10505a);
                return;
            }
            if (i == 4) {
                this.e.startMoreFitness(this.f10505a);
            } else if (i == 5) {
                this.h.showAchieveKaka(this.f10505a);
            } else {
                e(i, 0);
            }
        }
    }

    private void e(int i, int i2) {
        if (i == 1) {
            if (this.d.size() <= i2) {
                return;
            }
            final String jumpUrl = this.d.get(i2).getJumpUrl();
            GRSManager.a(this.f10505a).e("messageCenterUrl", new GrsQueryCallback() { // from class: com.huawei.ui.main.stories.soical.views.SocialMessageAdapter.5
                @Override // com.huawei.hwcloudmodel.callback.GrsQueryCallback
                public void onCallBackSuccess(String str) {
                    LogUtil.c("SocialMessageAdapter", "GRSManager onCallBackSuccess url = " + str);
                    SocialMessageAdapter.this.e.gotoFitnessPage(jumpUrl, str + "/messageH5/html/launchFitness.html?url=", SocialMessageAdapter.this.f10505a);
                }

                @Override // com.huawei.hwcloudmodel.callback.GrsQueryCallback
                public void onCallBackFail(int i3) {
                    LogUtil.c("SocialMessageAdapter", "GRSManager onCallBackFail i = " + i3);
                }
            });
            return;
        }
        GRSManager.a(this.f10505a).e("domainContentcenterDbankcdnNew", new GrsQueryCallback() { // from class: com.huawei.ui.main.stories.soical.views.SocialMessageAdapter.3
            @Override // com.huawei.hwcloudmodel.callback.GrsQueryCallback
            public void onCallBackSuccess(String str) {
                LogUtil.c("SocialMessageAdapter", "GRSManager onCallBackSuccess url = " + str);
                try {
                    SocialMessageAdapter.this.f10505a.startActivity(SocialMessageAdapter.this.j.builder(SocialMessageAdapter.this.f10505a, str + ((ActivityHtmlPathApi) Services.c("PluginOperation", ActivityHtmlPathApi.class)).getActivityHtmlPath() + "lowVersionTips.html").build());
                } catch (ActivityNotFoundException e) {
                    LogUtil.b("SocialMessageAdapter", "ActivityNotFoundException" + e.getMessage());
                }
            }

            @Override // com.huawei.hwcloudmodel.callback.GrsQueryCallback
            public void onCallBackFail(int i3) {
                LogUtil.c("SocialMessageAdapter", "GRSManager onCallBackFail i = " + i3);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(int i) {
        HashMap hashMap = new HashMap();
        hashMap.put("click", "1");
        if (i < this.d.size()) {
            hashMap.put("title", this.d.get(i).getTitle());
            hashMap.put("description", this.d.get(i).getDescription());
            if (this.g == 27) {
                hashMap.put("jumpType", Integer.valueOf(this.d.get(i).getJumpType()));
                ixx.d().d(this.f10505a, AnalyticsValue.HEALTH_WONDERFUL_EVENT_SCROLL_2020025.value(), hashMap, 0);
            } else {
                ixx.d().d(this.f10505a, AnalyticsValue.HEALTH_UNMISSABLE_TOPIC_SCROLL_2020024.value(), hashMap, 0);
            }
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        List<MessageExt> list = this.d;
        if (list != null) {
            return list.size();
        }
        return 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int c(Context context, float f) {
        return (int) ((f * context.getResources().getDisplayMetrics().density) + 0.5f);
    }

    public class SocialMessageHodler extends RecyclerView.ViewHolder {

        /* renamed from: a, reason: collision with root package name */
        private LinearLayout f10507a;
        private View c;
        private ImageView d;
        private HealthDivider e;
        private View f;
        private HealthTextView h;
        private HealthTextView i;

        public SocialMessageHodler(View view, int i) {
            super(view);
            this.f10507a = (LinearLayout) view.findViewById(R.id.item_social_message_layout);
            this.d = (ImageView) view.findViewById(R.id.img_item_social);
            if (i == 1 || i == 2 || i == 3) {
                e(i);
                return;
            }
            if (i == 4) {
                this.i = (HealthTextView) view.findViewById(R.id.tv_item_describe);
                this.h = (HealthTextView) view.findViewById(R.id.tv_item_date);
                this.e = (HealthDivider) view.findViewById(R.id.img_item_line);
            } else {
                this.c = view.findViewById(R.id.item_padding_left);
                this.f = view.findViewById(R.id.item_padding_right);
            }
        }

        private void e(int i) {
            if (nsn.ag(this.itemView.getContext())) {
                int n = nsn.n();
                ViewGroup.LayoutParams layoutParams = this.itemView.getLayoutParams();
                if (i == 1) {
                    SocialMessageAdapter socialMessageAdapter = SocialMessageAdapter.this;
                    layoutParams.height = socialMessageAdapter.c(socialMessageAdapter.f10505a, 109.3f);
                } else if (i == 2) {
                    SocialMessageAdapter socialMessageAdapter2 = SocialMessageAdapter.this;
                    float c = ((n - socialMessageAdapter2.c(socialMessageAdapter2.f10505a, 40.0f)) / 4.0f) / 2.0f;
                    SocialMessageAdapter socialMessageAdapter3 = SocialMessageAdapter.this;
                    layoutParams.height = (int) (c + socialMessageAdapter3.c(socialMessageAdapter3.f10505a, 8.0f));
                    LogUtil.c("SocialMessageAdapter", "SocialMessageHodler width = ", Integer.valueOf(layoutParams.width), ", height = ", Integer.valueOf(layoutParams.height));
                } else if (i == 3) {
                    SocialMessageAdapter socialMessageAdapter4 = SocialMessageAdapter.this;
                    float c2 = ((n - socialMessageAdapter4.c(socialMessageAdapter4.f10505a, 32.0f)) / 3.0f) / 2.0f;
                    SocialMessageAdapter socialMessageAdapter5 = SocialMessageAdapter.this;
                    layoutParams.height = (int) (c2 + socialMessageAdapter5.c(socialMessageAdapter5.f10505a, 8.0f));
                    LogUtil.c("SocialMessageAdapter", "SocialMessageHodler width = ", Integer.valueOf(layoutParams.width), ", height = ", Integer.valueOf(layoutParams.height));
                }
                this.itemView.setLayoutParams(layoutParams);
            }
        }
    }
}
