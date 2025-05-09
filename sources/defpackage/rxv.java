package defpackage;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.huawei.health.R;
import com.huawei.health.messagecenter.model.MessageObject;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.openalliance.ad.views.PPSNativeView;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.viewpager.HealthPagerAdapter;
import com.huawei.ui.main.stories.soical.NewSocialFragment;
import defpackage.rxq;
import java.lang.ref.WeakReference;
import java.util.List;

/* loaded from: classes7.dex */
public class rxv extends HealthPagerAdapter {

    /* renamed from: a, reason: collision with root package name */
    private final List<ImageView> f16958a;
    private final Context b;
    private final WeakReference<NewSocialFragment> c;
    private final rxq d;
    private final List<MessageObject> e;

    @Override // com.huawei.uikit.hwviewpager.widget.HwPagerAdapter
    public int getItemPosition(Object obj) {
        return -2;
    }

    @Override // com.huawei.uikit.hwviewpager.widget.HwPagerAdapter
    public boolean isViewFromObject(View view, Object obj) {
        return obj == view;
    }

    public rxv(NewSocialFragment newSocialFragment, List<ImageView> list, List<MessageObject> list2, rxq rxqVar) {
        this.c = new WeakReference<>(newSocialFragment);
        this.b = newSocialFragment.getActivity();
        this.f16958a = list;
        this.e = list2;
        this.d = rxqVar;
    }

    @Override // com.huawei.uikit.hwviewpager.widget.HwPagerAdapter
    public int getCount() {
        return this.f16958a.size();
    }

    @Override // com.huawei.uikit.hwviewpager.widget.HwPagerAdapter
    public void destroyItem(ViewGroup viewGroup, int i, Object obj) {
        if (obj instanceof View) {
            viewGroup.removeView((View) obj);
        }
    }

    @Override // com.huawei.uikit.hwviewpager.widget.HwPagerAdapter
    public Object instantiateItem(ViewGroup viewGroup, int i) {
        NewSocialFragment newSocialFragment = this.c.get();
        if (newSocialFragment == null || !newSocialFragment.isVisible() || newSocialFragment.isRemoving() || newSocialFragment.isDetached()) {
            return null;
        }
        return dTK_(viewGroup, i, this.f16958a);
    }

    private View dTK_(ViewGroup viewGroup, int i, List<ImageView> list) {
        MessageObject messageObject;
        if (koq.b(list, i)) {
            return null;
        }
        View inflate = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_new_social_cardview, (ViewGroup) null);
        if (inflate instanceof PPSNativeView) {
            inflate = this.d.dTr_(list.get(i).getDrawable(), viewGroup, i, (PPSNativeView) inflate);
        }
        viewGroup.addView(inflate);
        inflate.setTag(Integer.valueOf(i));
        inflate.setTag(R.layout.layout_new_social_cardview, Long.valueOf(System.currentTimeMillis()));
        if (koq.b(this.e, i)) {
            this.d.dTy_(inflate);
            return inflate;
        }
        if (this.e.get(i).getNativeAd() == null && (messageObject = this.e.get(i)) != null && !TextUtils.isEmpty(messageObject.getMsgId())) {
            dTL_(messageObject, inflate);
        }
        return inflate;
    }

    private void dTL_(MessageObject messageObject, View view) {
        if (messageObject instanceof pgq) {
            pgq pgqVar = (pgq) messageObject;
            TextView textView = (TextView) view.findViewById(R.id.card_txt_btn);
            if (pgqVar.b()) {
                mdf d = pgqVar.d();
                GradientDrawable gradientDrawable = new GradientDrawable();
                gradientDrawable.setCornerRadius(this.b.getResources().getDimensionPixelSize(R.dimen._2131362906_res_0x7f0a045a));
                gradientDrawable.setColor(Color.parseColor(d.v()));
                textView.setBackgroundDrawable(gradientDrawable);
                this.d.dTz_(d, textView);
            }
            textView.setVisibility(pgqVar.b() ? 0 : 8);
        }
        Context context = this.b;
        if (context instanceof BaseActivity) {
            view.setOnClickListener(nkx.cwZ_(new rxq.e(this.d, messageObject), (BaseActivity) context, this.d.e().isNotSupportBrowseUrl(messageObject.getDetailUri()), AnalyticsValue.SOCIAL_1070004.value()));
        } else {
            view.setOnClickListener(new rxq.e(this.d, messageObject));
        }
    }
}
