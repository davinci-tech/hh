package com.huawei.phoneservice.feedback.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.huawei.health.R;
import com.huawei.phoneservice.feedback.widget.a;
import com.huawei.phoneservice.feedbackcommon.db.FeedbackMediaData;
import com.huawei.phoneservice.feedbackcommon.entity.FeedMedia;
import com.huawei.phoneservice.feedbackcommon.entity.MediaEntity;
import com.huawei.phoneservice.feedbackcommon.photolibrary.MimeType;

/* loaded from: classes5.dex */
public class b extends com.huawei.phoneservice.feedback.widget.a<FeedMedia> {
    private Context b;
    private a.InterfaceC0234a e;

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemViewType(int i) {
        return MimeType.isImageFromUrl(a(i).getDownloadURL()) ? 1 : 0;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: c, reason: merged with bridge method [inline-methods] */
    public void onBindViewHolder(com.huawei.phoneservice.feedback.widget.c cVar, int i) {
        int itemViewType = getItemViewType(i);
        FeedMedia a2 = a(i);
        MediaEntity b = FeedbackMediaData.a(this.b).b(a2.getAttachId());
        if (1 == itemViewType) {
            ImageView imageView = (ImageView) cVar.cfe_(R.id.img_success);
            RelativeLayout relativeLayout = (RelativeLayout) cVar.cfe_(R.id.rl_img_failed);
            a(this.b, imageView, (ImageView) cVar.cfe_(R.id.img_img_failed), relativeLayout, (RelativeLayout) cVar.cfe_(R.id.rl_img_progress), a2, b, i, this.e);
        }
        c(cVar, i, itemViewType, a2, b);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: cdJ_, reason: merged with bridge method [inline-methods] */
    public com.huawei.phoneservice.feedback.widget.c onCreateViewHolder(ViewGroup viewGroup, int i) {
        return i == 0 ? new g(LayoutInflater.from(this.b).inflate(R.layout.feedback_sdk_item_videofrom, viewGroup, false)) : new com.huawei.phoneservice.feedback.widget.c(LayoutInflater.from(this.b).inflate(R.layout.feedback_sdk_item_imagefrom, viewGroup, false));
    }

    private void c(com.huawei.phoneservice.feedback.widget.c cVar, int i, int i2, FeedMedia feedMedia, MediaEntity mediaEntity) {
        if (i2 == 0) {
            ImageView imageView = (ImageView) cVar.cfe_(R.id.video_frame);
            RelativeLayout relativeLayout = (RelativeLayout) cVar.cfe_(R.id.rl_video_frame);
            b(this.b, imageView, (ImageView) cVar.cfe_(R.id.img_video_failed), relativeLayout, (RelativeLayout) cVar.cfe_(R.id.rl_video_progress), feedMedia, mediaEntity, i, this.e);
        }
    }

    public b(Context context, a.InterfaceC0234a interfaceC0234a) {
        this.b = context;
        this.e = interfaceC0234a;
    }
}
