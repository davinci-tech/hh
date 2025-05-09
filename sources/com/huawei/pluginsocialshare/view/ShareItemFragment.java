package com.huawei.pluginsocialshare.view;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import com.huawei.health.R;
import com.huawei.health.userprofilemgr.api.UserProfileMgrApi;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hms.network.embedded.y5;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.base.BaseFragment;
import com.huawei.ui.commonui.cardview.HealthCardView;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import defpackage.fdu;
import defpackage.fdz;
import defpackage.ixx;
import defpackage.koq;
import defpackage.mto;
import defpackage.mwd;
import defpackage.nrf;
import defpackage.nsn;
import health.compact.a.LanguageUtil;
import health.compact.a.Services;
import java.util.ArrayList;
import java.util.HashMap;

/* loaded from: classes6.dex */
public class ShareItemFragment extends BaseFragment {

    /* renamed from: a, reason: collision with root package name */
    private int f8530a;
    private boolean b;
    private HealthTextView c;
    private fdu d;
    private Context e;
    private fdz g;
    private SharedPreferences j;

    public static ShareItemFragment a(int i) {
        ShareItemFragment shareItemFragment = new ShareItemFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("position", i);
        shareItemFragment.setArguments(bundle);
        return shareItemFragment;
    }

    @Override // com.huawei.ui.commonui.base.BaseFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Bundle arguments = getArguments();
        if (arguments != null) {
            this.f8530a = arguments.getInt("position", -1);
        }
        fdu d = mto.d();
        this.d = d;
        ArrayList<fdz> b = d == null ? null : d.b();
        if (koq.d(b, this.f8530a)) {
            this.g = b.get(this.f8530a);
        }
        this.e = getContext();
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.share_viewpager_item, viewGroup, false);
        crl_(inflate);
        return inflate;
    }

    private void crl_(View view) {
        RelativeLayout relativeLayout = (RelativeLayout) view.findViewById(R.id.share_content);
        if (LanguageUtil.bc(this.e)) {
            relativeLayout.setRotationY(180.0f);
        }
        ImageView imageView = (ImageView) view.findViewById(R.id.img_card_item);
        ImageView imageView2 = (ImageView) view.findViewById(R.id.img_webpage_background);
        fdz fdzVar = this.g;
        if (fdzVar != null) {
            Bitmap cri_ = cri_(fdzVar);
            if (this.g.i() && cri_ != null) {
                imageView2.setImageBitmap(cri_);
            } else {
                imageView.setImageBitmap(cri_);
            }
            if (this.g.h()) {
                crj_(view, this.g);
                crk_(view);
            }
        }
        if (this.d.o()) {
            return;
        }
        HealthCardView healthCardView = (HealthCardView) view.findViewById(R.id.share_item_card_view);
        if (healthCardView.getLayoutParams() instanceof LinearLayout.LayoutParams) {
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) healthCardView.getLayoutParams();
            layoutParams.setMarginStart(nsn.c(this.e, 8.0f));
            layoutParams.setMarginEnd(nsn.c(this.e, 8.0f));
            healthCardView.setLayoutParams(layoutParams);
        }
    }

    private Bitmap cri_(fdz fdzVar) {
        Bitmap aws_ = fdzVar.aws_();
        if (aws_ == null) {
            LogUtil.a("Share_ShareItemFragment", "getPreviewBitmap:read from path");
            String e = fdzVar.e();
            if (TextUtils.isEmpty(e)) {
                LogUtil.h("Share_ShareItemFragment", "getPreviewBitmap:bitmap and image path are both null!");
                return aws_;
            }
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            options.inPreferredConfig = Bitmap.Config.RGB_565;
            BitmapFactory.decodeFile(e, options);
            if (options.outHeight > 8192) {
                options.inSampleSize = 2;
            }
            options.inJustDecodeBounds = false;
            aws_ = nrf.cHC_(e, options);
        }
        return mwd.cqs_(aws_, y5.h);
    }

    private void crj_(View view, final fdz fdzVar) {
        LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.content_edit_button);
        ImageView imageView = (ImageView) view.findViewById(R.id.track_share_new_arrow);
        if (LanguageUtil.bc(this.e)) {
            imageView.setImageResource(R.drawable._2131427841_res_0x7f0b0201);
        }
        linearLayout.setVisibility(0);
        linearLayout.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.pluginsocialshare.view.ShareItemFragment.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                ShareItemFragment.this.c();
                mto.b().b(ShareItemFragment.this.e, fdzVar, ((UserProfileMgrApi) Services.c("HWUserProfileMgr", UserProfileMgrApi.class)).getUserInfo());
                HashMap hashMap = new HashMap(1);
                hashMap.put("click", 1);
                ixx.d().d(ShareItemFragment.this.e, AnalyticsValue.MOTION_TRACK_1040031.value(), hashMap, 0);
                ViewClickInstrumentation.clickOnView(view2);
            }
        });
    }

    private void crk_(View view) {
        this.c = (HealthTextView) view.findViewById(R.id.guide_user_edit_content);
        if (!mwd.j()) {
            LogUtil.h("Share_ShareItemFragment", "initGuideUserEditShareView The account language is not supported.");
            return;
        }
        this.c.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.pluginsocialshare.view.ShareItemFragment.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                ShareItemFragment.this.c();
                ViewClickInstrumentation.clickOnView(view2);
            }
        });
        SharedPreferences sharedPreferences = this.e.getSharedPreferences(Integer.toString(20002), 0);
        this.j = sharedPreferences;
        if (sharedPreferences == null) {
            return;
        }
        boolean z = sharedPreferences.getBoolean("is_first_time_guide_user_edit_share", true);
        this.b = z;
        if (z) {
            this.c.setVisibility(0);
        } else {
            this.c.setVisibility(8);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c() {
        if (!mwd.j()) {
            LogUtil.h("Share_ShareItemFragment", "hideGuideUserEditShare The account language is not supported.");
            return;
        }
        this.c.setVisibility(8);
        if (this.j == null) {
            LogUtil.h("Share_ShareItemFragment", "hideGuideUserEditShare Hide bubble sharedPreferences is null.");
            this.j = this.e.getSharedPreferences(Integer.toString(20002), 0);
        }
        SharedPreferences.Editor edit = this.j.edit();
        if (edit == null) {
            return;
        }
        edit.putBoolean("is_first_time_guide_user_edit_share", false);
        edit.commit();
    }
}
