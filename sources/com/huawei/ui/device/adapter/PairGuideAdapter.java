package com.huawei.ui.device.adapter;

import android.graphics.Bitmap;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.BitmapDrawable;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.recyclerview.widget.RecyclerView;
import com.huawei.health.R;
import com.huawei.health.devicemgr.api.mainprocess.DownloadManagerApi;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import defpackage.cuw;
import defpackage.jfu;
import defpackage.koq;
import defpackage.nyp;
import defpackage.obb;
import health.compact.a.Services;
import health.compact.a.util.LogUtil;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes6.dex */
public class PairGuideAdapter extends RecyclerView.Adapter<a> {
    private List<nyp> b;

    public PairGuideAdapter(List<nyp> list) {
        this.b = list;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: cTk_, reason: merged with bridge method [inline-methods] */
    public a onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new a(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_devic_pairing_guide, viewGroup, false));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: d, reason: merged with bridge method [inline-methods] */
    public void onBindViewHolder(a aVar, int i) {
        cuw e;
        List<nyp> list = this.b;
        if (list == null || list.isEmpty()) {
            LogUtil.c("PairGuideAdapter", "mPairGuideInfos is empty");
            return;
        }
        if (i < 0 || i >= this.b.size()) {
            LogUtil.c("PairGuideAdapter", "mPairGuideInfos, array out of bounds");
            return;
        }
        nyp nypVar = this.b.get(i);
        if (nypVar == null) {
            LogUtil.c("PairGuideAdapter", "PairGuideInfo is empty");
            return;
        }
        String e2 = nypVar.e();
        if (TextUtils.isEmpty(e2) && (e = jfu.e(nypVar.i())) != null) {
            e2 = e.f();
        }
        aVar.c.setText(e2);
        if (TextUtils.isEmpty(nypVar.c())) {
            SpannableStringBuilder cTL_ = obb.cTL_(nypVar.i(), BaseApplication.getContext());
            if (cTL_ != null) {
                aVar.f9278a.setText(cTL_);
            } else {
                aVar.f9278a.setText(R.string.IDS_device_paring_type_le_des_info_21);
            }
        } else {
            aVar.f9278a.setText(nypVar.c());
        }
        a(aVar, nypVar);
    }

    private void a(a aVar, nyp nypVar) {
        if (!nypVar.f()) {
            e(aVar, nypVar);
        } else {
            d(aVar, nypVar);
        }
    }

    private void e(a aVar, nyp nypVar) {
        aVar.d.setImageResource(nypVar.h());
        if (nypVar.d() == 0) {
            aVar.b.setVisibility(8);
            return;
        }
        aVar.b.setVisibility(0);
        aVar.b.setImageResource(nypVar.d());
        if (aVar.b.getDrawable() instanceof AnimationDrawable) {
            AnimationDrawable animationDrawable = (AnimationDrawable) aVar.b.getDrawable();
            if (animationDrawable != null) {
                animationDrawable.start();
            } else {
                LogUtil.c("PairGuideAdapter", "mB3PairGuideAnim is null!");
            }
        }
    }

    private void d(a aVar, nyp nypVar) {
        if (nypVar.cTt_() != null) {
            aVar.d.setImageBitmap(nypVar.cTt_());
            LogUtil.d("PairGuideAdapter", "PairGuide img is setted");
        } else {
            aVar.d.setVisibility(4);
        }
        Bitmap loadImageByImagePath = ((DownloadManagerApi) Services.c("DownloadDeviceResource", DownloadManagerApi.class)).loadImageByImagePath(nypVar.b());
        if (loadImageByImagePath == null) {
            return;
        }
        aVar.d.setVisibility(0);
        aVar.d.setImageBitmap(loadImageByImagePath);
        if (koq.b(nypVar.j())) {
            aVar.b.setVisibility(8);
            return;
        }
        aVar.b.setVisibility(0);
        aVar.b.setImageResource(R.drawable._2131430724_res_0x7f0b0d44);
        if (aVar.b.getDrawable() instanceof AnimationDrawable) {
            AnimationDrawable animationDrawable = (AnimationDrawable) aVar.b.getDrawable();
            Iterator<String> it = nypVar.j().iterator();
            while (it.hasNext()) {
                animationDrawable.addFrame(new BitmapDrawable(((DownloadManagerApi) Services.c("DownloadDeviceResource", DownloadManagerApi.class)).loadImageByImagePath(it.next())), 100);
            }
            animationDrawable.start();
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.b.size();
    }

    static class a extends RecyclerView.ViewHolder {

        /* renamed from: a, reason: collision with root package name */
        private HealthTextView f9278a;
        private ImageView b;
        private HealthTextView c;
        private ImageView d;

        public a(View view) {
            super(view);
            this.f9278a = (HealthTextView) view.findViewById(R.id.pair_guide_text);
            this.c = (HealthTextView) view.findViewById(R.id.device_name);
            this.d = (ImageView) view.findViewById(R.id.device_image);
            this.b = (ImageView) view.findViewById(R.id.device_image_anim);
        }
    }
}
