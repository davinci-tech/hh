package com.huawei.health.marketing.views.dialog;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Interpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;
import com.google.gson.Gson;
import com.huawei.health.R;
import com.huawei.health.marketing.datatype.ResourceBriefInfo;
import com.huawei.health.marketing.datatype.SinglePopUp;
import com.huawei.health.marketing.datatype.templates.PopUpMultiTemplate;
import com.huawei.health.marketing.utils.MarketingBiUtils;
import com.huawei.health.marketing.views.dialog.MarketingDialogFragment;
import com.huawei.hianalytics.visual.autocollect.instrument.FragmentInstrumentation;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.dotspageindicator.HealthDotsPageIndicator;
import com.huawei.ui.commonui.viewpager.HealthFragmentStatePagerAdapter;
import com.huawei.ui.commonui.viewpager.HealthViewPager;
import com.huawei.watchface.videoedit.gles.Constant;
import defpackage.koq;
import defpackage.ntd;
import defpackage.nto;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes3.dex */
public class MarketingDialogFragment extends DialogFragment {

    /* renamed from: a, reason: collision with root package name */
    private HealthFragmentStatePagerAdapter f2906a;
    private ImageView b;
    private FragmentManager c;
    private HealthDotsPageIndicator e;
    private ResourceBriefInfo f;
    private View g;
    private int h;
    private LinearLayout i;
    private HealthViewPager n;
    private long j = 0;
    private boolean d = false;

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setStyle(1, R.style.DialogFullScreen);
    }

    public void b(int i, ResourceBriefInfo resourceBriefInfo) {
        this.h = i;
        this.f = resourceBriefInfo;
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.introduction_dialog, viewGroup, true);
        this.g = inflate;
        inflate.setVisibility(4);
        return this.g;
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.n = (HealthViewPager) this.g.findViewById(R.id.introduction_view_pager_dialog);
        this.e = (HealthDotsPageIndicator) this.g.findViewById(R.id.dialog_indicator);
        this.i = (LinearLayout) this.g.findViewById(R.id.introduction_dialog_rotate);
        this.b = (ImageView) this.g.findViewById(R.id.introduction_cancel_dialog);
        this.c = getChildFragmentManager();
        e();
        FragmentInstrumentation.onViewCreatedByFragment(this, view, bundle);
    }

    private void e() {
        ArrayList arrayList = new ArrayList(((PopUpMultiTemplate) new Gson().fromJson(this.f.getContent().getContent(), PopUpMultiTemplate.class)).getGridContents());
        b(arrayList);
        if (this.d) {
            Collections.sort(arrayList, new Comparator() { // from class: eln
                @Override // java.util.Comparator
                public final int compare(Object obj, Object obj2) {
                    return MarketingDialogFragment.d((SinglePopUp) obj, (SinglePopUp) obj2);
                }
            });
            if (this.c != null && koq.c(arrayList)) {
                OnPageClickListener onPageClickListener = new OnPageClickListener() { // from class: els
                    @Override // com.huawei.health.marketing.views.dialog.OnPageClickListener
                    public final void onPageClick(int i) {
                        MarketingDialogFragment.this.e(i);
                    }
                };
                ArrayList arrayList2 = new ArrayList(arrayList.size());
                int i = 0;
                for (SinglePopUp singlePopUp : arrayList) {
                    InnerMarketingFragment innerMarketingFragment = new InnerMarketingFragment();
                    innerMarketingFragment.e(this.h, i, this.f, singlePopUp);
                    innerMarketingFragment.e(onPageClickListener);
                    arrayList2.add(innerMarketingFragment);
                    i++;
                }
                nto ntoVar = new nto(this.c, arrayList2);
                this.f2906a = ntoVar;
                this.n.setAdapter(ntoVar);
                this.e.setViewPager(this.n);
                if (arrayList.size() <= 1) {
                    this.e.setVisibility(8);
                    this.n.setIsScroll(false);
                } else {
                    this.e.setVisibility(0);
                }
            }
            this.b.setOnClickListener(new View.OnClickListener() { // from class: elu
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    MarketingDialogFragment.this.aqO_(view);
                }
            });
        }
    }

    public static /* synthetic */ int d(SinglePopUp singlePopUp, SinglePopUp singlePopUp2) {
        return singlePopUp2.getPriority() - singlePopUp.getPriority();
    }

    public /* synthetic */ void e(int i) {
        if (i == this.f2906a.getCount() - 1) {
            dismiss();
        } else {
            this.n.setCurrentItem(i + 1);
        }
    }

    public /* synthetic */ void aqO_(View view) {
        MarketingBiUtils.c(this.h, this.f, this.j);
        dismiss();
        ViewClickInstrumentation.clickOnView(view);
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        ntd.b().cME_(this, this.g);
        FragmentInstrumentation.onResumeByFragment(this);
    }

    private void a(int i) {
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this.i, "scaleX", 0.3f, 1.0f);
        long j = i;
        ofFloat.setDuration(j);
        ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(this.i, "scaleY", 0.3f, 1.0f);
        ofFloat2.setDuration(j);
        ObjectAnimator ofFloat3 = ObjectAnimator.ofFloat(this.i, "rotationY", 280.0f, 360.0f);
        ofFloat3.setDuration(j);
        ObjectAnimator ofFloat4 = ObjectAnimator.ofFloat(this.i, "alpha", 0.1f, 1.0f);
        ofFloat4.setStartDelay(50L);
        ofFloat4.setDuration(j);
        ofFloat.addListener(new AnimatorListenerAdapter() { // from class: com.huawei.health.marketing.views.dialog.MarketingDialogFragment.2
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationStart(Animator animator) {
                if (MarketingDialogFragment.this.g != null) {
                    MarketingDialogFragment.this.g.setVisibility(0);
                }
                super.onAnimationStart(animator);
            }
        });
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(ofFloat3, ofFloat4, ofFloat, ofFloat2);
        animatorSet.setStartDelay(500L);
        animatorSet.setInterpolator(new Interpolator() { // from class: com.huawei.health.marketing.views.dialog.MarketingDialogFragment.3
            @Override // android.animation.TimeInterpolator
            public float getInterpolation(float f) {
                return (float) Math.sin((float) (f * 1.5707963267948966d));
            }
        });
        animatorSet.start();
    }

    @Override // androidx.fragment.app.DialogFragment
    public void showNow(FragmentManager fragmentManager, String str) {
        try {
            super.showNow(fragmentManager, str);
            if (getDialog() != null) {
                a(400);
                this.j = System.currentTimeMillis();
            }
        } catch (IllegalStateException e) {
            LogUtil.a("MarketingDialogFragment", String.valueOf(e.getMessage()));
        }
    }

    private void b(List<SinglePopUp> list) {
        int contentType = this.f.getContentType();
        boolean z = (contentType == 50 || contentType == 45) && koq.c(list);
        this.d = z;
        if (z) {
            Iterator<SinglePopUp> it = list.iterator();
            while (it.hasNext()) {
                String mediaType = it.next().getMediaType();
                if (!"video".equals(mediaType) && !Constant.TYPE_PHOTO.equals(mediaType)) {
                    it.remove();
                }
            }
            this.d = koq.c(list);
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void setUserVisibleHint(boolean z) {
        super.setUserVisibleHint(z);
        FragmentInstrumentation.setUserVisibleHintByFragment(this, z);
    }

    @Override // androidx.fragment.app.Fragment
    public void onPause() {
        super.onPause();
        FragmentInstrumentation.onPauseByFragment(this);
    }

    @Override // androidx.fragment.app.Fragment
    public void onHiddenChanged(boolean z) {
        super.onHiddenChanged(z);
        FragmentInstrumentation.onHiddenChangedByFragment(this, z);
    }
}
