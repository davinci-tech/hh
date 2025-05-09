package com.huawei.health.h5pro.jsbridge.system.share;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.text.TextUtils;
import androidx.core.content.FileProvider;
import com.huawei.health.h5pro.jsbridge.system.share.Share;
import com.huawei.health.h5pro.utils.CommonUtil;
import com.huawei.openalliance.ad.constant.MimeType;
import com.huawei.operation.beans.TitleBean;
import java.io.File;
import java.util.Iterator;
import java.util.Locale;

/* loaded from: classes3.dex */
public class AndroidShare implements Share {
    public final Context e;

    @Override // com.huawei.health.h5pro.jsbridge.system.share.Share
    public void shareText(String str, Share.ShareCallback shareCallback) {
        String str2;
        if (TextUtils.isEmpty(str)) {
            str2 = "shareTextContent is empty";
        } else {
            Intent intent = new Intent();
            intent.setAction("android.intent.action.SEND");
            intent.putExtra("android.intent.extra.TEXT", str);
            intent.setType("text/plain");
            intent.addFlags(268435456);
            Intent createChooser = Intent.createChooser(intent, TitleBean.RIGHT_BTN_TYPE_SHARE);
            createChooser.addFlags(268435456);
            try {
                this.e.startActivity(createChooser);
                shareCallback.onSuccess();
                return;
            } catch (ActivityNotFoundException e) {
                str2 = e.getMessage();
            }
        }
        shareCallback.onFailure(-1, str2);
    }

    @Override // com.huawei.health.h5pro.jsbridge.system.share.Share
    public void shareLink(ShareLinkObj shareLinkObj, Share.ShareCallback shareCallback) {
        shareText(String.format(Locale.ENGLISH, "%s %s %s", shareLinkObj.getTitle(), shareLinkObj.getDesc(), shareLinkObj.getUrl()), shareCallback);
    }

    @Override // com.huawei.health.h5pro.jsbridge.system.share.Share
    public void shareImage(ShareImageObj shareImageObj, Share.ShareCallback shareCallback) {
        File file = new File(shareImageObj.getFilePath());
        if (!file.exists()) {
            shareCallback.onFailure(-1, "the file does not exist");
        }
        b(file);
        shareCallback.onSuccess();
    }

    private void b(File file) {
        Context context = this.e;
        Uri uriForFile = FileProvider.getUriForFile(context, CommonUtil.getProviderAuthority(context), file);
        Intent intent = new Intent("android.intent.action.SEND");
        intent.setType(MimeType.JPEG);
        intent.putExtra("android.intent.extra.STREAM", uriForFile);
        Intent createChooser = Intent.createChooser(intent, "");
        createChooser.addFlags(268435456);
        createChooser.setFlags(1);
        createChooser.setFlags(2);
        grantUriPermission(this.e, uriForFile, intent);
        this.e.startActivity(createChooser);
    }

    public static void grantUriPermission(Context context, Uri uri, Intent intent) {
        Iterator<ResolveInfo> it = context.getPackageManager().queryIntentActivities(intent, 65536).iterator();
        while (it.hasNext()) {
            context.grantUriPermission(it.next().activityInfo.packageName, uri, 3);
        }
    }

    public AndroidShare(Context context) {
        this.e = context;
    }
}
