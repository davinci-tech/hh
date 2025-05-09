package com.huawei.openalliance.ad;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import com.huawei.watchface.mvp.ui.activity.ScanImageActivity;
import com.sina.weibo.sdk.api.ImageObject;
import com.sina.weibo.sdk.api.TextObject;
import com.sina.weibo.sdk.api.WebpageObject;
import com.sina.weibo.sdk.api.WeiboMultiMessage;
import com.sina.weibo.sdk.auth.AuthInfo;
import com.sina.weibo.sdk.openapi.IWBAPI;
import com.sina.weibo.sdk.openapi.SdkListener;
import com.sina.weibo.sdk.openapi.WBAPIFactory;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.UUID;

/* loaded from: classes5.dex */
public class tx implements to {

    /* renamed from: a, reason: collision with root package name */
    private static final Integer f7545a = 100;
    private Boolean b = Boolean.TRUE;

    @Override // com.huawei.openalliance.ad.to
    public boolean a() {
        return tv.a("com.sina.weibo.sdk.auth.AuthInfo");
    }

    @Override // com.huawei.openalliance.ad.to
    public void a(Activity activity, ts tsVar, tu tuVar) {
        ho.b("WeiBoShare", "start WeiBo share");
        IWBAPI createWBAPI = WBAPIFactory.createWBAPI(activity);
        createWBAPI.setLoggerEnable(true);
        AuthInfo authInfo = new AuthInfo(activity, tuVar.a(), "", "");
        if (!this.b.booleanValue()) {
            b(activity, tsVar, createWBAPI);
        } else {
            a(activity, tsVar, createWBAPI, authInfo);
            this.b = false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void b(Activity activity, ts tsVar, IWBAPI iwbapi) {
        WeiboMultiMessage weiboMultiMessage = new WeiboMultiMessage();
        a(tsVar, weiboMultiMessage);
        a(BitmapFactory.decodeStream(new ByteArrayInputStream(tv.a(activity, tsVar, ScanImageActivity.SCAN_IMAGE_LENGTH_LIMIT))), weiboMultiMessage);
        a(tsVar, BitmapFactory.decodeStream(new ByteArrayInputStream(tv.a(activity, tsVar, 32768))), weiboMultiMessage);
        iwbapi.shareMessage(activity, weiboMultiMessage, true);
    }

    private static void a(ts tsVar, WeiboMultiMessage weiboMultiMessage) {
        TextObject textObject = new TextObject();
        textObject.text = a(tsVar);
        weiboMultiMessage.textObject = textObject;
    }

    private static void a(ts tsVar, Bitmap bitmap, WeiboMultiMessage weiboMultiMessage) {
        WebpageObject webpageObject = new WebpageObject();
        webpageObject.actionUrl = tsVar.d();
        webpageObject.identify = UUID.randomUUID().toString();
        webpageObject.defaultText = "分享网页";
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            bitmap.compress(Bitmap.CompressFormat.JPEG, f7545a.intValue(), byteArrayOutputStream);
            webpageObject.thumbData = byteArrayOutputStream.toByteArray();
        } finally {
            try {
                com.huawei.openalliance.ad.utils.cy.a(byteArrayOutputStream);
                weiboMultiMessage.mediaObject = webpageObject;
            } catch (Throwable th) {
            }
        }
        com.huawei.openalliance.ad.utils.cy.a(byteArrayOutputStream);
        weiboMultiMessage.mediaObject = webpageObject;
    }

    private static void a(Bitmap bitmap, WeiboMultiMessage weiboMultiMessage) {
        ImageObject imageObject = new ImageObject();
        imageObject.setImageData(bitmap);
        weiboMultiMessage.imageObject = imageObject;
    }

    private static void a(final Activity activity, final ts tsVar, final IWBAPI iwbapi, AuthInfo authInfo) {
        iwbapi.registerApp(activity, authInfo, new SdkListener() { // from class: com.huawei.openalliance.ad.tx.1
            public void onInitFailure(Exception exc) {
            }

            public void onInitSuccess() {
                tx.b(activity, tsVar, iwbapi);
            }
        });
    }

    private static String a(ts tsVar) {
        return String.format("#%s# %s", tv.a(tsVar.b(), 30), tv.a(tsVar.c(), 110));
    }
}
