package com.huawei.ui.commonui.view.shareSelector;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.ui.commonui.R$drawable;
import com.huawei.ui.commonui.R$string;
import com.huawei.ui.commonui.healthtextview.HealthTextView;

/* loaded from: classes8.dex */
public class ShareSelector extends LinearLayout implements View.OnClickListener {

    /* renamed from: a, reason: collision with root package name */
    private LinearLayout f8979a;
    private ShareSelectorClickCallback b;
    private LinearLayout c;
    private Context d;
    private LinearLayout e;
    private LinearLayout g;
    private LinearLayout j;

    public ShareSelector(Context context) {
        this(context, null);
    }

    public ShareSelector(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public ShareSelector(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.d = context;
        b();
    }

    public void setClickCallback(ShareSelectorClickCallback shareSelectorClickCallback) {
        this.b = shareSelectorClickCallback;
    }

    private void b() {
        View inflate = ((LayoutInflater) this.d.getSystemService("layout_inflater")).inflate(R.layout.hw_health_share_selector_bar, this);
        this.g = (LinearLayout) inflate.findViewById(R.id.share_wechat_chat_layout);
        this.j = (LinearLayout) inflate.findViewById(R.id.share_wechat_friends_layout);
        this.c = (LinearLayout) inflate.findViewById(R.id.share_weibo_layout);
        this.f8979a = (LinearLayout) inflate.findViewById(R.id.share_save_to_local_layout);
        this.e = (LinearLayout) inflate.findViewById(R.id.share_more_layout);
        this.g.setClickable(true);
        this.j.setClickable(true);
        this.c.setClickable(true);
        this.f8979a.setClickable(true);
        this.e.setClickable(true);
        this.g.setOnClickListener(this);
        this.j.setOnClickListener(this);
        this.c.setOnClickListener(this);
        this.f8979a.setOnClickListener(this);
        this.e.setOnClickListener(this);
        cNc_(this.j).setImageResource(R$drawable.share_wechat_friends_icon);
        cNc_(this.g).setImageResource(R$drawable.share_wechat_chat_icon);
        cNc_(this.c).setImageResource(R$drawable.share_weibo_icon);
        cNc_(this.e).setImageResource(R$drawable.share_more_icon);
        cNc_(this.f8979a).setImageResource(R$drawable.share_save_local);
        cNd_(this.j).setText(R$string.IDS_plugin_socialshare_wechat_friends);
        cNd_(this.j).setGravity(17);
        cNd_(this.g).setText(R$string.IDS_plugin_socialshare_wechat_chat);
        cNd_(this.g).setGravity(17);
        cNd_(this.c).setText(R$string.IDS_plugin_socialshare_weibo);
        cNd_(this.e).setText(R$string.IDS_user_profile_more);
        cNd_(this.f8979a).setText(R$string.IDS_hwh_show_save_local);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view == null || this.b == null) {
            ViewClickInstrumentation.clickOnView(view);
            return;
        }
        int id = view.getId();
        if (id == R.id.share_wechat_chat_layout) {
            this.b.onWeChatShare();
        } else if (id == R.id.share_wechat_friends_layout) {
            this.b.onWeChatFriendShare();
        } else if (id == R.id.share_weibo_layout) {
            this.b.onSinaShare();
        } else if (id == R.id.share_save_to_local_layout) {
            this.b.onLocalShare();
        } else if (id == R.id.share_more_layout) {
            this.b.onMoreShare();
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    public void setShareWeChatVisibility(int i) {
        this.g.setVisibility(i);
    }

    public void setShareWeChatFriendsVisibility(int i) {
        this.j.setVisibility(i);
    }

    public void setSinaVisibility(int i) {
        this.c.setVisibility(i);
    }

    public void setLocalShareVisibility(int i) {
        this.f8979a.setVisibility(i);
    }

    public void setMoreVisibility(int i) {
        this.e.setVisibility(i);
    }

    private HealthTextView cNd_(LinearLayout linearLayout) {
        return (HealthTextView) linearLayout.findViewById(R.id.share_tv);
    }

    public void setMoreLayoutText(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        cNd_(this.e).setText(str);
    }

    private ImageView cNc_(LinearLayout linearLayout) {
        return (ImageView) linearLayout.findViewById(R.id.share_img);
    }
}
