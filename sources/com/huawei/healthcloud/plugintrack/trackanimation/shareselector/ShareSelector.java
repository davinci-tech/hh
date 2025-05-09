package com.huawei.healthcloud.plugintrack.trackanimation.shareselector;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.huawei.health.R;
import com.huawei.healthcloud.plugintrack.trackanimation.shareselector.ShareSelector;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.ui.commonui.R$drawable;
import com.huawei.ui.commonui.R$string;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.horizontalscrollview.HealthHorizontalScrollView;
import defpackage.hcl;
import health.compact.a.LanguageUtil;

/* loaded from: classes4.dex */
public class ShareSelector extends LinearLayout implements View.OnClickListener {

    /* renamed from: a, reason: collision with root package name */
    private ShareSelectorClickCallback f3617a;
    private LinearLayout b;
    private Context c;
    private int d;
    private LinearLayout e;
    private LinearLayout f;
    private LinearLayout g;
    private HealthHorizontalScrollView h;
    private LinearLayout i;
    private LinearLayout j;

    public ShareSelector(Context context) {
        this(context, null);
    }

    public ShareSelector(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public ShareSelector(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.c = context;
        d();
    }

    public void setClickCallback(ShareSelectorClickCallback shareSelectorClickCallback) {
        this.f3617a = shareSelectorClickCallback;
    }

    private void d() {
        this.d = hcl.a(this.c);
        View inflate = ((LayoutInflater) this.c.getSystemService("layout_inflater")).inflate(R.layout.hw_health_share_selector_bar, this);
        this.i = (LinearLayout) inflate.findViewById(R.id.share_wechat_chat_layout);
        this.f = (LinearLayout) inflate.findViewById(R.id.share_wechat_friends_layout);
        this.j = (LinearLayout) inflate.findViewById(R.id.share_weibo_layout);
        this.e = (LinearLayout) inflate.findViewById(R.id.share_family_layout);
        this.b = (LinearLayout) inflate.findViewById(R.id.share_save_to_local_layout);
        this.g = (LinearLayout) inflate.findViewById(R.id.share_more_layout);
        this.h = (HealthHorizontalScrollView) inflate.findViewById(R.id.share_container_scroll);
        this.i.setClickable(true);
        this.f.setClickable(true);
        this.j.setClickable(true);
        this.e.setClickable(true);
        this.b.setClickable(true);
        this.g.setClickable(true);
        this.i.setOnClickListener(this);
        this.f.setOnClickListener(this);
        this.j.setOnClickListener(this);
        this.e.setOnClickListener(this);
        this.b.setOnClickListener(this);
        this.g.setOnClickListener(this);
        this.e.setVisibility(0);
        aZv_(this.f).setImageResource(R$drawable.share_wechat_friends_icon);
        aZv_(this.i).setImageResource(R$drawable.share_wechat_chat_icon);
        aZv_(this.j).setImageResource(R$drawable.share_weibo_icon);
        aZv_(this.e).setImageResource(R.mipmap._2131821471_res_0x7f11039f);
        aZv_(this.g).setImageResource(R$drawable.share_more_icon);
        aZv_(this.b).setImageResource(R$drawable.share_save_local);
        aZw_(this.f).setText(R$string.IDS_plugin_socialshare_wechat_friends);
        aZw_(this.i).setText(R$string.IDS_plugin_socialshare_wechat_chat);
        aZw_(this.j).setText(R$string.IDS_plugin_socialshare_weibo);
        aZw_(this.e).setText(R$string.IDS_hwh_family_health_zone);
        aZw_(this.g).setText(R.string._2130839758_res_0x7f0208ce);
        aZw_(this.b).setText(R$string.IDS_hwh_show_save_local);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view == null || this.f3617a == null) {
            ViewClickInstrumentation.clickOnView(view);
            return;
        }
        int id = view.getId();
        if (id == R.id.share_wechat_chat_layout) {
            this.f3617a.onWeChatShare();
        } else if (id == R.id.share_wechat_friends_layout) {
            this.f3617a.onWeChatFriendShare();
        } else if (id == R.id.share_weibo_layout) {
            this.f3617a.onSinaShare();
        } else if (id == R.id.share_family_layout) {
            this.f3617a.onFamilyShare();
        } else if (id == R.id.share_save_to_local_layout) {
            this.f3617a.onLocalShare();
        } else if (id == R.id.share_more_layout) {
            this.f3617a.onMoreShare();
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    public void setShareWeChatVisibility(int i) {
        this.i.setVisibility(i);
    }

    public void setShareWeChatFriendsVisibility(int i) {
        this.f.setVisibility(i);
    }

    public void setSinaVisibility(int i) {
        this.j.setVisibility(i);
    }

    public void setFamilyVisibility(int i) {
        this.e.setVisibility(i);
    }

    public void setLocalShareVisibility(int i) {
        this.b.setVisibility(i);
    }

    public void setMoreVisibility(int i) {
        this.g.setVisibility(i);
    }

    private HealthTextView aZw_(LinearLayout linearLayout) {
        return (HealthTextView) linearLayout.findViewById(R.id.share_tv);
    }

    public void setMoreLayoutText(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        aZw_(this.g).setText(str);
    }

    private ImageView aZv_(LinearLayout linearLayout) {
        return (ImageView) linearLayout.findViewById(R.id.share_img);
    }

    public void b() {
        setVisibility(0);
        post(new Runnable() { // from class: hcc
            @Override // java.lang.Runnable
            public final void run() {
                ShareSelector.this.e();
            }
        });
    }

    public /* synthetic */ void e() {
        HealthHorizontalScrollView healthHorizontalScrollView = this.h;
        if (healthHorizontalScrollView != null) {
            healthHorizontalScrollView.smoothScrollTo(LanguageUtil.bc(this.c) ? this.d : 0, 0);
        }
    }
}
