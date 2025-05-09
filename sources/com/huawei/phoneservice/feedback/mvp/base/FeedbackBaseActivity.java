package com.huawei.phoneservice.feedback.mvp.base;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import androidx.core.content.FileProvider;
import com.huawei.health.R;
import com.huawei.hms.support.api.entity.common.CommonConstant;
import com.huawei.operation.utils.Constants;
import com.huawei.phoneservice.faq.base.util.i;
import com.huawei.phoneservice.faq.base.util.n;
import com.huawei.phoneservice.feedback.entity.FeedbackBean;
import com.huawei.phoneservice.feedback.mvp.base.f;
import com.huawei.phoneservice.feedback.ui.FeedBaseActivity;
import com.huawei.phoneservice.feedbackcommon.photolibrary.MimeType;
import com.huawei.phoneservice.feedbackcommon.photolibrary.internal.entity.MediaItem;
import com.huawei.secure.android.common.intent.SafeIntent;
import com.huawei.uikit.hwcolumnsystem.widget.HwColumnSystem;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes5.dex */
public abstract class FeedbackBaseActivity<P extends f> extends FeedBaseActivity {
    private P e;

    protected abstract P w();

    public void x() {
        Intent intent = new Intent("android.intent.action.PICK");
        intent.setType("image/*,video/*");
        intent.addCategory("android.intent.category.DEFAULT");
        try {
            startActivityForResult(intent, 100);
        } catch (ActivityNotFoundException e) {
            i.c("FeedbackBaseActivity", e.getMessage());
        }
    }

    public int v() {
        int f = new HwColumnSystem(this).f();
        if (!com.huawei.phoneservice.feedback.media.impl.utils.b.d()) {
            if (f < 4) {
                return 4;
            }
            return f;
        }
        if (f <= 4) {
            f = 6;
        }
        int i = getResources().getConfiguration().orientation;
        if (i == 1 && f >= 8) {
            f = 6;
        }
        if (i != 2 || f < 12) {
            return f;
        }
        return 10;
    }

    @Override // com.huawei.phoneservice.feedback.ui.FeedBaseActivity, com.huawei.phoneservice.feedback.ui.FeedbackAbstractBaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        P p = this.e;
        if (p != null) {
            p.b();
        }
    }

    @Override // com.huawei.phoneservice.feedback.ui.FeedBaseActivity, com.huawei.phoneservice.feedback.ui.FeedbackAbstractBaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        if (this.e == null) {
            this.e = w();
        }
        this.e.a();
        super.onCreate(bundle);
    }

    public void a(MediaItem mediaItem) {
        Uri uri;
        if (mediaItem == null || mediaItem.getFilePath() == null) {
            return;
        }
        File file = new File(mediaItem.getFilePath());
        String c = com.huawei.phoneservice.feedbackcommon.utils.b.c();
        try {
            if (TextUtils.isEmpty(c)) {
                c = getPackageName() + ".fileprovider";
            }
            uri = FileProvider.getUriForFile(this, c, file);
        } catch (IllegalArgumentException e) {
            i.c("FeedbackBaseActivity", e.getMessage());
            uri = null;
        }
        if (uri == null) {
            return;
        }
        Intent intent = new Intent(CommonConstant.ACTION.HWID_SCHEME_URL);
        intent.addFlags(1);
        intent.addCategory("android.intent.category.DEFAULT");
        intent.setDataAndType(uri, MimeType.isVideo(mediaItem.getMimeType()) ? "video/*" : Constants.IMAGE_TYPE);
        try {
            startActivity(intent);
        } catch (ActivityNotFoundException e2) {
            i.c("FeedbackBaseActivity", e2.getMessage());
        }
    }

    public void a(FeedbackBean feedbackBean, int i) {
        MediaItem mediaItem;
        String filePath;
        if (feedbackBean == null || feedbackBean.getMedias() == null) {
            return;
        }
        List<MediaItem> medias = feedbackBean.getMedias();
        if (i < 0 || i >= medias.size() || (mediaItem = medias.get(i)) == null || (filePath = mediaItem.getFilePath()) == null) {
            return;
        }
        File file = new File(filePath);
        if (file.exists()) {
            file.delete();
        }
    }

    public void a(FeedbackBean feedbackBean) {
        if (feedbackBean == null || feedbackBean.getMedias() == null) {
            return;
        }
        Iterator<MediaItem> it = feedbackBean.getMedias().iterator();
        while (it.hasNext()) {
            String filePath = it.next().getFilePath();
            if (filePath != null) {
                File file = new File(filePath);
                if (file.exists()) {
                    file.delete();
                }
            }
        }
    }

    public List<MediaItem> a(SafeIntent safeIntent, FeedbackBean feedbackBean) {
        boolean z;
        String string;
        if (safeIntent == null || safeIntent.getData() == null || feedbackBean == null) {
            return null;
        }
        Uri data = safeIntent.getData();
        List<MediaItem> medias = feedbackBean.getMedias();
        if (medias == null) {
            medias = new ArrayList<>();
        }
        Iterator<MediaItem> it = medias.iterator();
        while (true) {
            if (!it.hasNext()) {
                z = false;
                break;
            }
            if (MimeType.isVideo(it.next().getMimeType())) {
                z = true;
                break;
            }
        }
        File cfp_ = com.huawei.phoneservice.feedbackcommon.utils.e.cfp_(this, data, String.valueOf(System.currentTimeMillis()));
        if (!cfp_.exists()) {
            return null;
        }
        String path = cfp_.getPath();
        String mimeType = MimeType.getMimeType(path);
        long length = cfp_.length();
        if (MimeType.isVideo(mimeType)) {
            if (z) {
                string = getString(R.string._2130850945_res_0x7f023481);
            } else if (length > com.huawei.phoneservice.feedbackcommon.utils.b.b()) {
                string = getString(R.string._2130850946_res_0x7f023482, new Object[]{50});
            }
            n.a(this, string);
            cfp_.delete();
            return null;
        }
        medias.add(new MediaItem(data.toString(), path, mimeType, length));
        return medias;
    }
}
