package com.huawei.operation.h5pro.bridgeimpl;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.Base64;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.json.JsonSanitizer;
import com.huawei.haf.application.BaseApplication;
import com.huawei.health.h5pro.jsbridge.system.share.Share;
import com.huawei.health.h5pro.jsbridge.system.share.ShareExtApi;
import com.huawei.health.h5pro.jsbridge.system.share.ShareImageObj;
import com.huawei.health.h5pro.jsbridge.system.share.ShareLinkObj;
import com.huawei.health.h5pro.utils.GeneralUtil;
import com.huawei.health.h5pro.utils.LogUtil;
import com.huawei.health.h5pro.vengine.H5ProAppInfo;
import com.huawei.health.socialshare.api.SocialShareCenterApi;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.operation.h5pro.jsmodules.sharecaas.ShareToCaasUtil;
import com.huawei.ui.commonui.R$drawable;
import defpackage.fdu;
import defpackage.fdz;
import defpackage.feb;
import defpackage.jei;
import defpackage.nrf;
import health.compact.a.Services;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

/* loaded from: classes.dex */
public class ShareImpl implements ShareExtApi {
    private static final String COLUMN_IS_NEED_PRINT = "isNeedPrint";
    private static final String COLUMN_IS_NEED_SAVE_PDF = "isNeedSavePdf";
    private static final String COLUMN_IS_SHARE_FAMILY = "isShareFamily";
    private static final String COLUMN_IS_SHOW_USERINFO_BUTTON = "isShowUserinfoButton";
    private static final String COLUMN_SAVE_FILE_IMAGES = "saveFileImages";
    private static final String COLUMN_SHARE_FROM = "shareFrom";
    private static final String COLUMN_SHARE_TITLE = "shareTitleContent";
    private static final String IMAGE_BASE64_HEAD_REGEX = "data:image/(jpeg|png|webp);base64,";
    private static final String IMAGE_BASE64_REGEX = "data:image/(jpeg|png|webp);base64,.*";
    private static final String TAG = "H5pro_ShareImpl";

    @Override // com.huawei.health.h5pro.jsbridge.system.share.Share
    public void shareText(String str, Share.ShareCallback shareCallback) {
        fdu fduVar = new fdu(0);
        fduVar.a(str);
        fduVar.b(AnalyticsValue.SHARE_1140001.value());
        fduVar.c(false);
        ((SocialShareCenterApi) Services.c("PluginSocialShare", SocialShareCenterApi.class)).exeShare(fduVar, BaseApplication.e());
        shareCallback.onSuccess();
    }

    @Override // com.huawei.health.h5pro.jsbridge.system.share.Share
    public void shareImage(ShareImageObj shareImageObj, Share.ShareCallback shareCallback) {
        fdu fduVar;
        String filePath = shareImageObj.getFilePath();
        if (!GeneralUtil.isSafePath(filePath)) {
            LogUtil.w(TAG, "shareImage: untrusted -> " + filePath);
            return;
        }
        if (shareImageObj.isReport()) {
            fduVar = new fdu(7);
            Bitmap cHB_ = nrf.cHB_(filePath);
            if (cHB_ == null) {
                shareCallback.onFailure(-1, "shareImage gallery fail: bitmap is fail");
                return;
            }
            fduVar.awp_(cHB_);
        } else {
            fduVar = new fdu(4);
            fduVar.d(filePath);
        }
        fduVar.b(shareImageObj.getModuleId());
        Map<String, Object> shareBiMap = shareImageObj.getShareBiMap();
        if (shareBiMap != null && !shareBiMap.isEmpty()) {
            fduVar.b(shareBiMap);
        }
        parseShareExtra(fduVar, shareImageObj.getExtra());
        fduVar.e(1);
        fduVar.i(false);
        fduVar.c(false);
        try {
            if (fduVar.aa() == 0) {
                fduVar.b(Integer.parseInt(shareImageObj.getModuleId()));
            }
        } catch (NumberFormatException e) {
            LogUtil.e(TAG, "shareImage ", e.getMessage());
        }
        exeShare(fduVar, shareImageObj.getSharePlatform());
        shareCallback.onSuccess();
    }

    @Override // com.huawei.health.h5pro.jsbridge.system.share.Share
    public void shareLink(ShareLinkObj shareLinkObj, Share.ShareCallback shareCallback) {
        Bitmap bGs_;
        if (TextUtils.isEmpty(shareLinkObj.getUrl()) || TextUtils.isEmpty(shareLinkObj.getTitle()) || TextUtils.isEmpty(shareLinkObj.getDesc())) {
            shareCallback.onFailure(-1, "param is invalid");
        }
        String iconUrl = shareLinkObj.getIconUrl();
        if (!TextUtils.isEmpty(iconUrl) && iconUrl.matches(IMAGE_BASE64_REGEX)) {
            try {
                String replaceFirst = iconUrl.replaceFirst(IMAGE_BASE64_HEAD_REGEX, "");
                if (!TextUtils.isEmpty(replaceFirst)) {
                    byte[] decode = Base64.decode(replaceFirst, 0);
                    bGs_ = BitmapFactory.decodeByteArray(decode, 0, decode.length);
                }
            } catch (IllegalArgumentException e) {
                LogUtil.w(TAG, "shareLink: exception -> " + e.getMessage());
            }
            bGs_ = null;
        } else {
            bGs_ = jei.bGs_(iconUrl);
        }
        if (bGs_ == null) {
            bGs_ = getAppIcon();
        }
        fdu fduVar = new fdu(2);
        fduVar.a(shareLinkObj.getDesc());
        fduVar.awp_(bGs_);
        fduVar.f(shareLinkObj.getUrl());
        fduVar.c(shareLinkObj.getTitle());
        fduVar.b(shareLinkObj.getModuleId());
        parseShareExtra(fduVar, shareLinkObj.getExtra());
        Map<String, Object> shareBiMap = shareLinkObj.getShareBiMap();
        if (shareBiMap != null && !shareBiMap.isEmpty()) {
            fduVar.b(shareBiMap);
        }
        fduVar.c(false);
        try {
            if (fduVar.aa() == 0) {
                fduVar.b(Integer.parseInt(shareLinkObj.getModuleId()));
            }
        } catch (NumberFormatException e2) {
            LogUtil.e(TAG, "shareImage ", e2.getMessage());
        }
        exeShare(fduVar, shareLinkObj.getSharePlatform());
        shareCallback.onSuccess();
    }

    private void exeShare(fdu fduVar, int[] iArr) {
        SocialShareCenterApi socialShareCenterApi = (SocialShareCenterApi) Services.c("PluginSocialShare", SocialShareCenterApi.class);
        Context e = BaseApplication.e();
        if (iArr == null || iArr.length == 0) {
            socialShareCenterApi.exeShare(fduVar, e);
        } else {
            socialShareCenterApi.exeShare(e, iArr[0], fduVar);
        }
    }

    private Bitmap getAppIcon() {
        Drawable loadIcon = com.huawei.hwcommonmodel.application.BaseApplication.getContext().getApplicationInfo().loadIcon(com.huawei.hwcommonmodel.application.BaseApplication.getContext().getPackageManager());
        if (loadIcon instanceof BitmapDrawable) {
            return ((BitmapDrawable) loadIcon).getBitmap();
        }
        return null;
    }

    @Override // com.huawei.health.h5pro.jsbridge.system.share.ShareExtApi
    public void share(Context context, H5ProAppInfo h5ProAppInfo, String str, Share.ShareCallback shareCallback) {
        LogUtil.i(TAG, "share start");
        if (TextUtils.isEmpty(str)) {
            shareCallback.onFailure(-1, "param is empty");
            return;
        }
        try {
            fdu fduVar = (fdu) new Gson().fromJson(JsonSanitizer.sanitize(str), fdu.class);
            if (ShareType.MULTI_IMAGE_SHARING.shareType != fduVar.u()) {
                shareCallback.onFailure(-1, "Not implemented -> " + fduVar.u());
                return;
            }
            LogUtil.i(TAG, "MULTI_IMAGE_SHARING");
            Iterator<fdz> it = fduVar.b().iterator();
            while (it.hasNext()) {
                fdz next = it.next();
                if (next == null) {
                    LogUtil.w(TAG, "shareEditContent is null");
                } else if (next.h()) {
                    ArrayList arrayList = new ArrayList();
                    arrayList.add(Integer.valueOf(R$drawable.fitness_background_1));
                    arrayList.add(Integer.valueOf(R$drawable.share_geometry_1));
                    arrayList.add(Integer.valueOf(R$drawable.share_geometry_2));
                    arrayList.add(Integer.valueOf(R$drawable.share_geometry_3));
                    next.d(arrayList);
                    feb febVar = new feb();
                    febVar.d(1127);
                    febVar.e(R$drawable.watermark1127);
                    ArrayList arrayList2 = new ArrayList();
                    arrayList2.add(febVar);
                    next.b(arrayList2);
                } else {
                    next.awt_(ShareToCaasUtil.imgResource2Bitmap(next.e()));
                }
            }
            ((SocialShareCenterApi) Services.c("PluginSocialShare", SocialShareCenterApi.class)).exeShare(fduVar, context);
            shareCallback.onSuccess();
        } catch (JsonSyntaxException e) {
            shareCallback.onFailure(-1, e.getMessage());
        }
    }

    /* loaded from: classes9.dex */
    enum ShareType {
        MULTI_IMAGE_SHARING(6);

        private final int shareType;

        ShareType(int i) {
            this.shareType = i;
        }
    }

    private void parseShareExtra(fdu fduVar, Map<String, Object> map) {
        if (map == null || map.size() == 0) {
            return;
        }
        if (map.get(COLUMN_IS_SHARE_FAMILY) != null && (map.get(COLUMN_IS_SHARE_FAMILY) instanceof Boolean)) {
            fduVar.b(((Boolean) map.get(COLUMN_IS_SHARE_FAMILY)).booleanValue());
        }
        if (map.get(COLUMN_IS_NEED_SAVE_PDF) != null && (map.get(COLUMN_IS_NEED_SAVE_PDF) instanceof Boolean)) {
            fduVar.a(((Boolean) map.get(COLUMN_IS_NEED_SAVE_PDF)).booleanValue());
        }
        if (map.get(COLUMN_IS_NEED_PRINT) != null && (map.get(COLUMN_IS_NEED_PRINT) instanceof Boolean)) {
            fduVar.e(((Boolean) map.get(COLUMN_IS_NEED_PRINT)).booleanValue());
        }
        if (map.get(COLUMN_SAVE_FILE_IMAGES) != null && (map.get(COLUMN_SAVE_FILE_IMAGES) instanceof ArrayList)) {
            fduVar.e((ArrayList<String>) map.get(COLUMN_SAVE_FILE_IMAGES));
        }
        if (map.get(COLUMN_SHARE_TITLE) != null && (map.get(COLUMN_SHARE_TITLE) instanceof String)) {
            fduVar.c((String) map.get(COLUMN_SHARE_TITLE));
        }
        if (map.get(COLUMN_SHARE_FROM) != null && (map.get(COLUMN_SHARE_FROM) instanceof Integer)) {
            fduVar.b(((Integer) map.get(COLUMN_SHARE_FROM)).intValue());
        }
        if (map.containsKey(COLUMN_IS_SHOW_USERINFO_BUTTON)) {
            Object obj = map.get(COLUMN_IS_SHOW_USERINFO_BUTTON);
            if (obj instanceof Boolean) {
                fduVar.h(((Boolean) obj).booleanValue());
            }
        }
    }
}
