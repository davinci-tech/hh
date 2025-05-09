package com.huawei.ui.main.stories.soical;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Handler;
import android.text.TextUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;
import androidx.webkit.ProxyConfig;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;
import com.huawei.health.R;
import com.huawei.health.messagecenter.model.MessageObject;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwlogsmodel.LogUtil;
import defpackage.ixx;
import defpackage.koq;
import defpackage.nrf;
import defpackage.pgq;
import defpackage.rxh;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

/* loaded from: classes7.dex */
public class SocialFragmentHelper {

    /* renamed from: a, reason: collision with root package name */
    private final Context f10472a;

    public SocialFragmentHelper(Context context) {
        this.f10472a = context;
    }

    public void dSJ_(MessageObject messageObject, int[] iArr, Handler handler) {
        String imgUri;
        int i;
        LogUtil.a("UIDV_SocialFragmentHelper", "downloadPic");
        if (!TextUtils.isEmpty(messageObject.getHarmonyImgUrl())) {
            imgUri = messageObject.getHarmonyImgUrl();
        } else if (!TextUtils.isEmpty(messageObject.getImgBigUri())) {
            imgUri = messageObject.getImgBigUri();
        } else {
            imgUri = !TextUtils.isEmpty(messageObject.getImgUri()) ? messageObject.getImgUri() : "";
        }
        String str = imgUri;
        String msgId = messageObject.getMsgId();
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(msgId)) {
            LogUtil.h("UIDV_SocialFragmentHelper", "downloadPic() msgId or url is empty.");
            return;
        }
        String scheme = Uri.parse(str).getScheme();
        ImageView imageView = new ImageView(this.f10472a);
        int e = rxh.e(this.f10472a);
        if (rxh.e(iArr)) {
            i = (iArr[1] * e) / iArr[0];
        } else {
            i = (e * 9) / 21;
        }
        imageView.setLayoutParams(new FrameLayout.LayoutParams(e, i));
        if ("http".equalsIgnoreCase(scheme) || ProxyConfig.MATCH_HTTPS.equalsIgnoreCase(scheme)) {
            LogUtil.a("UIDV_SocialFragmentHelper", "HTTP scheme = ", scheme);
            dSF_(str, imageView, messageObject, iArr, handler);
        } else {
            LogUtil.a("UIDV_SocialFragmentHelper", "scheme is not http or https，scheme = ", scheme);
        }
    }

    private void dSF_(String str, final ImageView imageView, final MessageObject messageObject, final int[] iArr, final Handler handler) {
        if (a(this.f10472a)) {
            nrf.d(str, new CustomTarget<Drawable>() { // from class: com.huawei.ui.main.stories.soical.SocialFragmentHelper.5
                @Override // com.bumptech.glide.request.target.Target
                public void onLoadCleared(Drawable drawable) {
                }

                @Override // com.bumptech.glide.request.target.Target
                /* renamed from: dSK_, reason: merged with bridge method [inline-methods] */
                public void onResourceReady(Drawable drawable, Transition<? super Drawable> transition) {
                    SocialFragmentHelper.this.dSG_(drawable, imageView, messageObject, iArr, handler);
                }

                @Override // com.bumptech.glide.request.target.CustomTarget, com.bumptech.glide.request.target.Target
                public void onLoadFailed(Drawable drawable) {
                    super.onLoadFailed(drawable);
                    LogUtil.a("UIDV_SocialFragmentHelper", "picture download failture，msgId = ", messageObject.getMsgId());
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dSG_(Drawable drawable, ImageView imageView, MessageObject messageObject, int[] iArr, Handler handler) {
        Bitmap cHN_ = nrf.cHN_(R.drawable._2131430872_res_0x7f0b0dd8, this.f10472a);
        String string = this.f10472a.getResources().getString(R.string._2130842470_res_0x7f021366);
        if (!(drawable instanceof BitmapDrawable)) {
            LogUtil.h("UIDV_SocialFragmentHelper", "loadPic onResourceReady drawable is not BitmapDrawable");
            return;
        }
        Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();
        if (bitmap == null || bitmap.isRecycled()) {
            LogUtil.h("UIDV_SocialFragmentHelper", "loadPic onResourceReady bitmap is null");
            return;
        }
        Bitmap cJK_ = nrf.cJK_(bitmap, imageView);
        if (cJK_ == null || cJK_.isRecycled()) {
            LogUtil.h("UIDV_SocialFragmentHelper", "loadPic onResourceReady zoomBitmap is null");
            return;
        }
        Bitmap cHw_ = nrf.cHw_(cJK_, imageView, 3);
        if (cHw_ == null || cHw_.isRecycled()) {
            LogUtil.h("UIDV_SocialFragmentHelper", "loadPic onResourceReady cropBitmap is null");
            return;
        }
        int[] iArr2 = {0, 0};
        if (nrf.cIf_(imageView, iArr2)) {
            cHw_ = nrf.cJq_(cHw_, iArr2[0], iArr2[1], (int) this.f10472a.getResources().getDimension(R.dimen._2131362006_res_0x7f0a00d6));
        }
        if (cHw_ == null || cHw_.isRecycled()) {
            LogUtil.h("UIDV_SocialFragmentHelper", "loadPic onResourceReady roundRectangleBitmap is null");
            return;
        }
        a aVar = new a();
        imageView.setImageBitmap(cHw_);
        if ("2".equals(messageObject.getModule())) {
            LogUtil.a("UIDV_SocialFragmentHelper", "getModule is ad");
            Bitmap cIa_ = nrf.cIa_(cHw_, cHN_, string, this.f10472a);
            if (cIa_ != null && !cIa_.isRecycled()) {
                imageView.setImageBitmap(cIa_);
            }
        }
        aVar.dSM_(imageView);
        aVar.b(messageObject);
        aVar.e(iArr);
        handler.sendMessage(handler.obtainMessage(23, 0, 0, aVar));
    }

    private boolean a(Context context) {
        if (context == null) {
            return false;
        }
        if (context instanceof Activity) {
            return !((Activity) context).isDestroyed();
        }
        return true;
    }

    public static void a(Context context, String str, String str2, String str3) {
        HashMap hashMap = new HashMap();
        hashMap.put("click", 1);
        hashMap.put("type", str);
        hashMap.put("title", str2);
        hashMap.put("module", str3);
        ixx.d().d(context, AnalyticsValue.SOCIAL_1070004.value(), hashMap, 0);
    }

    public static class a {

        /* renamed from: a, reason: collision with root package name */
        private int[] f10474a;
        private MessageObject b;
        private ImageView c;

        public ImageView dSL_() {
            return this.c;
        }

        public void dSM_(ImageView imageView) {
            this.c = imageView;
        }

        public MessageObject e() {
            return this.b;
        }

        public void b(MessageObject messageObject) {
            this.b = messageObject;
        }

        public int[] b() {
            int[] iArr = this.f10474a;
            return iArr != null ? Arrays.copyOf(iArr, iArr.length) : new int[0];
        }

        public void e(int[] iArr) {
            if (iArr != null) {
                this.f10474a = Arrays.copyOf(iArr, iArr.length);
            }
        }
    }

    public boolean d(List<MessageObject> list, List<MessageObject> list2) {
        boolean z = false;
        for (int i = 0; i < list.size(); i++) {
            z = false;
            for (int i2 = 0; i2 < list2.size(); i2++) {
                if (a(list.get(i), list2.get(i2))) {
                    z = true;
                }
            }
            LogUtil.a("UIDV_SocialFragmentHelper", "isContains = ", Boolean.valueOf(z));
            if (!z) {
                break;
            }
        }
        return z;
    }

    public static boolean a(MessageObject messageObject, MessageObject messageObject2) {
        if (messageObject != null && messageObject2 != null) {
            boolean z = messageObject instanceof pgq;
            boolean z2 = messageObject2 instanceof pgq;
            if (!z && !z2) {
                return Objects.equals(messageObject.getMsgId(), messageObject2.getMsgId()) && messageObject.getCreateTime() == messageObject2.getCreateTime();
            }
            if (z && z2) {
                return Objects.equals(messageObject, messageObject2);
            }
        }
        return false;
    }

    public void dSI_(List<MessageObject> list, Handler handler) {
        if (koq.b(list)) {
            LogUtil.h("UIDV_SocialFragmentHelper", "checkDownPic() adDisplayList is empty. no Ad message");
            return;
        }
        LogUtil.a("UIDV_SocialFragmentHelper", "checkDownPic bannerMessageSize = ", Integer.valueOf(list.size()));
        int[] e = rxh.e(list);
        for (MessageObject messageObject : list) {
            if (messageObject == null) {
                LogUtil.h("UIDV_SocialFragmentHelper", "checkDownPic() messageObject is null.");
            } else {
                LogUtil.a("UIDV_SocialFragmentHelper", "checkDownPic messageId = ", messageObject.getMsgId());
                dSJ_(messageObject, e, handler);
            }
        }
    }

    public void dSH_(List<MessageObject> list, MessageObject messageObject, List<ImageView> list2, ImageView imageView) {
        boolean z = false;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) != null && messageObject.getMsgId().equals(list.get(i).getMsgId())) {
                list.set(i, messageObject);
                list2.set(i, imageView);
                z = true;
            }
        }
        if (!z) {
            list.add(messageObject);
            list2.add(imageView);
        }
        LogUtil.a("UIDV_SocialFragmentHelper", "adMessageDownloadList.size() = ", Integer.valueOf(list.size()));
    }
}
