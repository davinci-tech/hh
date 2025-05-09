package com.tencent.connect.share;

import android.content.Context;
import com.tencent.connect.auth.QQToken;
import com.tencent.connect.common.BaseApi;

/* loaded from: classes10.dex */
public class QzoneShare extends BaseApi {
    public static final String SHARE_TO_QQ_APP_NAME = "appName";
    public static final String SHARE_TO_QQ_AUDIO_URL = "audio_url";
    public static final String SHARE_TO_QQ_EXT_INT = "cflag";
    public static final String SHARE_TO_QQ_EXT_STR = "share_qq_ext_str";
    public static final String SHARE_TO_QQ_IMAGE_LOCAL_URL = "imageLocalUrl";
    public static final String SHARE_TO_QQ_IMAGE_URL = "imageUrl";
    public static final String SHARE_TO_QQ_SITE = "site";
    public static final String SHARE_TO_QQ_SUMMARY = "summary";
    public static final String SHARE_TO_QQ_TARGET_URL = "targetUrl";
    public static final String SHARE_TO_QQ_TITLE = "title";
    public static final String SHARE_TO_QZONE_EXTMAP = "extMap";
    public static final String SHARE_TO_QZONE_KEY_TYPE = "req_type";
    public static final int SHARE_TO_QZONE_TYPE_IMAGE = 5;
    public static final int SHARE_TO_QZONE_TYPE_IMAGE_TEXT = 1;
    public static final int SHARE_TO_QZONE_TYPE_MINI_PROGRAM = 7;
    public static final int SHARE_TO_QZONE_TYPE_NO_TYPE = 0;

    /* renamed from: a, reason: collision with root package name */
    private boolean f11310a;
    private boolean d;
    private boolean e;
    private boolean f;
    public String mViaShareQzoneType;

    @Override // com.tencent.connect.common.BaseApi
    public void releaseResource() {
    }

    public QzoneShare(Context context, QQToken qQToken) {
        super(qQToken);
        this.mViaShareQzoneType = "";
        this.f11310a = true;
        this.d = false;
        this.e = false;
        this.f = false;
    }

    /* JADX WARN: Removed duplicated region for block: B:102:0x0351  */
    /* JADX WARN: Removed duplicated region for block: B:104:0x025e  */
    /* JADX WARN: Removed duplicated region for block: B:131:0x0179  */
    /* JADX WARN: Removed duplicated region for block: B:133:0x00cf  */
    /* JADX WARN: Removed duplicated region for block: B:15:0x00c5  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x00d4  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x01d1  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x0251  */
    /* JADX WARN: Removed duplicated region for block: B:52:0x02d8  */
    /* JADX WARN: Removed duplicated region for block: B:77:0x0391  */
    /* JADX WARN: Removed duplicated region for block: B:80:0x039b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void shareToQzone(android.app.Activity r29, android.os.Bundle r30, com.tencent.tauth.IUiListener r31) {
        /*
            Method dump skipped, instructions count: 1081
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.connect.share.QzoneShare.shareToQzone(android.app.Activity, android.os.Bundle, com.tencent.tauth.IUiListener):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:23:0x00ea  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x011b  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x0149  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x0169  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x0189  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x01a0  */
    /* JADX WARN: Removed duplicated region for block: B:44:0x01c0  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x01e0  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x0200  */
    /* JADX WARN: Removed duplicated region for block: B:53:0x023f  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x0262  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x0286  */
    /* JADX WARN: Removed duplicated region for block: B:62:0x02aa  */
    /* JADX WARN: Removed duplicated region for block: B:65:0x02ca  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x0375  */
    /* JADX WARN: Removed duplicated region for block: B:72:0x0387  */
    /* JADX WARN: Removed duplicated region for block: B:76:0x02e5  */
    /* JADX WARN: Removed duplicated region for block: B:77:0x021b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void b(android.app.Activity r24, android.os.Bundle r25, com.tencent.tauth.IUiListener r26) {
        /*
            Method dump skipped, instructions count: 939
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.connect.share.QzoneShare.b(android.app.Activity, android.os.Bundle, com.tencent.tauth.IUiListener):void");
    }
}
