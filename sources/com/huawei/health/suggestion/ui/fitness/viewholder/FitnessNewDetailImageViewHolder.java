package com.huawei.health.suggestion.ui.fitness.viewholder;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.huawei.health.R;
import com.huawei.health.suggestion.ui.fitness.viewholder.FitnessNewDetailImageViewHolder;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hms.support.api.entity.common.CommonConstant;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.pluginfitnessadvice.ShowLayout;
import com.huawei.ui.commonui.webview.WebViewActivity;
import defpackage.gnm;
import defpackage.nrh;
import health.compact.a.CommonUtil;
import java.util.Locale;

/* loaded from: classes4.dex */
public class FitnessNewDetailImageViewHolder extends RecyclerView.ViewHolder {

    /* renamed from: a, reason: collision with root package name */
    private ImageView f3214a;

    public FitnessNewDetailImageViewHolder(ViewGroup viewGroup) {
        super(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.new_view_detail_info_picture, viewGroup, false));
        this.f3214a = (ImageView) this.itemView.findViewById(R.id.tv_rvlinear_picture_id);
    }

    public void a(final ShowLayout showLayout) {
        if (showLayout != null) {
            String imageUrl = showLayout.getImageUrl();
            if (this.f3214a == null) {
                return;
            }
            Glide.with(BaseApplication.getContext()).load(imageUrl).diskCacheStrategy(DiskCacheStrategy.ALL).into(this.f3214a);
            LogUtil.h("Suggestion_FitnessNewDetailImageViewHolder", "onBind , Glide.with DiskCacheStrategy.NONE!");
            this.f3214a.setOnClickListener(new View.OnClickListener() { // from class: fsb
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    FitnessNewDetailImageViewHolder.this.aFC_(showLayout, view);
                }
            });
        }
    }

    public /* synthetic */ void aFC_(ShowLayout showLayout, View view) {
        if (!CommonUtil.aa(BaseApplication.getContext())) {
            nrh.e(BaseApplication.getContext(), R.string._2130841083_res_0x7f020dfb);
            ViewClickInstrumentation.clickOnView(view);
        } else {
            c(showLayout);
            LogUtil.a("Suggestion_FitnessNewDetailImageViewHolder", "mImageView.setOnClickListener ok");
            ViewClickInstrumentation.clickOnView(view);
        }
    }

    public void c(ShowLayout showLayout) {
        if (showLayout != null) {
            String imageJumpUrl = showLayout.getImageJumpUrl();
            if (TextUtils.isEmpty(imageJumpUrl)) {
                return;
            }
            if (imageJumpUrl.toLowerCase(Locale.ENGLISH).startsWith("http://") || imageJumpUrl.toLowerCase(Locale.ENGLISH).startsWith("https://")) {
                Intent intent = new Intent(BaseApplication.getContext(), (Class<?>) WebViewActivity.class);
                intent.putExtra("WebViewActivity.REQUEST_URL_KEY", imageJumpUrl);
                intent.addFlags(268435456);
                gnm.aPB_(BaseApplication.getContext(), intent);
                return;
            }
            try {
                Uri parse = Uri.parse(imageJumpUrl);
                LogUtil.a("Suggestion_FitnessNewDetailImageViewHolder", "onImageJump url deep Link:", parse);
                Intent intent2 = new Intent(CommonConstant.ACTION.HWID_SCHEME_URL, parse);
                intent2.addFlags(268435456);
                BaseApplication.getContext().startActivity(intent2);
            } catch (ActivityNotFoundException unused) {
                LogUtil.b("Suggestion_FitnessNewDetailImageViewHolder", "onImageJump ActivityNotFoundException, url:", imageJumpUrl);
            }
        }
    }
}
