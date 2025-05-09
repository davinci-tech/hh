package com.tencent.connect.share;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.Base64;
import com.huawei.hms.support.api.entity.common.CommonConstant;
import com.tencent.connect.auth.QQToken;
import com.tencent.connect.common.BaseApi;
import com.tencent.connect.common.Constants;
import com.tencent.connect.common.UIListenerManager;
import com.tencent.open.b.e;
import com.tencent.open.log.SLog;
import com.tencent.open.utils.c;
import com.tencent.open.utils.d;
import com.tencent.open.utils.g;
import com.tencent.open.utils.k;
import com.tencent.open.utils.m;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.UiError;
import java.util.ArrayList;

/* loaded from: classes7.dex */
public class QQShare extends BaseApi {
    public static final int QQ_SHARE_SUMMARY_MAX_LENGTH = 512;
    public static final int QQ_SHARE_TITLE_MAX_LENGTH = 128;
    public static final String SHARE_TO_QQ_APP_NAME = "appName";
    public static final String SHARE_TO_QQ_ARK_INFO = "share_to_qq_ark_info";
    public static final String SHARE_TO_QQ_AUDIO_URL = "audio_url";
    public static final String SHARE_TO_QQ_EXT_INT = "cflag";
    public static final String SHARE_TO_QQ_EXT_STR = "share_qq_ext_str";
    public static final int SHARE_TO_QQ_FLAG_QZONE_AUTO_OPEN = 1;
    public static final int SHARE_TO_QQ_FLAG_QZONE_ITEM_HIDE = 2;
    public static final String SHARE_TO_QQ_GAME_MESSAGE_EXT = "game_message_ext";
    public static final String SHARE_TO_QQ_GAME_TAG_NAME = "game_tag_name";
    public static final String SHARE_TO_QQ_IMAGE_LOCAL_URL = "imageLocalUrl";
    public static final String SHARE_TO_QQ_IMAGE_URL = "imageUrl";
    public static final String SHARE_TO_QQ_KEY_TYPE = "req_type";
    public static final int SHARE_TO_QQ_MINI_PROGRAM = 7;
    public static final String SHARE_TO_QQ_MINI_PROGRAM_APPID = "mini_program_appid";
    public static final String SHARE_TO_QQ_MINI_PROGRAM_PATH = "mini_program_path";
    public static final String SHARE_TO_QQ_MINI_PROGRAM_TYPE = "mini_program_type";
    public static final String SHARE_TO_QQ_SITE = "site";
    public static final String SHARE_TO_QQ_SUMMARY = "summary";
    public static final String SHARE_TO_QQ_TARGET_URL = "targetUrl";
    public static final String SHARE_TO_QQ_TITLE = "title";
    public static final int SHARE_TO_QQ_TYPE_AUDIO = 2;
    public static final int SHARE_TO_QQ_TYPE_DEFAULT = 1;
    public static final int SHARE_TO_QQ_TYPE_IMAGE = 5;
    public String mViaShareQQType;

    @Override // com.tencent.connect.common.BaseApi
    public void releaseResource() {
    }

    public QQShare(Context context, QQToken qQToken) {
        super(qQToken);
        this.mViaShareQQType = "";
    }

    /* JADX WARN: Removed duplicated region for block: B:101:0x02ce  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x01db  */
    /* JADX WARN: Removed duplicated region for block: B:90:0x02cc  */
    /* JADX WARN: Removed duplicated region for block: B:93:0x02d5  */
    /* JADX WARN: Removed duplicated region for block: B:96:0x02de A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void shareToQQ(android.app.Activity r23, android.os.Bundle r24, com.tencent.tauth.IUiListener r25) {
        /*
            Method dump skipped, instructions count: 787
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.connect.share.QQShare.shareToQQ(android.app.Activity, android.os.Bundle, com.tencent.tauth.IUiListener):void");
    }

    private void b(final Activity activity, final Bundle bundle, final IUiListener iUiListener) {
        SLog.i("openSDK_LOG.QQShare", "shareToMobileQQ() -- start.");
        String string = bundle.getString("imageUrl");
        final String string2 = bundle.getString("title");
        final String string3 = bundle.getString("summary");
        SLog.v("openSDK_LOG.QQShare", "shareToMobileQQ -- imageUrl: " + string);
        if (!TextUtils.isEmpty(string)) {
            if (m.h(string)) {
                if (!m.f(activity, "4.3.0")) {
                    d(activity, bundle, iUiListener);
                } else {
                    new c(activity).a(string, new d() { // from class: com.tencent.connect.share.QQShare.1
                        @Override // com.tencent.open.utils.d
                        public void a(int i, ArrayList<String> arrayList) {
                        }

                        @Override // com.tencent.open.utils.d
                        public void a(int i, String str) {
                            if (i == 0) {
                                bundle.putString("imageLocalUrl", str);
                            } else if (TextUtils.isEmpty(string2) && TextUtils.isEmpty(string3)) {
                                IUiListener iUiListener2 = iUiListener;
                                if (iUiListener2 != null) {
                                    iUiListener2.onError(new UiError(-6, Constants.MSG_SHARE_GETIMG_ERROR, null));
                                    SLog.e("openSDK_LOG.QQShare", "shareToMobileQQ -- error: 获取分享图片失败!");
                                }
                                e.a().a(1, "SHARE_CHECK_SDK", "1000", QQShare.this.c.getAppId(), String.valueOf(0), Long.valueOf(SystemClock.elapsedRealtime()), 0, 1, Constants.MSG_SHARE_GETIMG_ERROR);
                                return;
                            }
                            QQShare.this.d(activity, bundle, iUiListener);
                        }
                    });
                }
            } else {
                bundle.putString("imageUrl", null);
                if (m.f(activity, "4.3.0")) {
                    SLog.d("openSDK_LOG.QQShare", "shareToMobileQQ -- QQ Version is < 4.3.0 ");
                    d(activity, bundle, iUiListener);
                } else {
                    SLog.d("openSDK_LOG.QQShare", "shareToMobileQQ -- QQ Version is > 4.3.0:isAppSpecificDir=" + m.m(string) + ",hasSDPermission:" + m.c());
                    a.a(activity, string, new d() { // from class: com.tencent.connect.share.QQShare.2
                        @Override // com.tencent.open.utils.d
                        public void a(int i, String str) {
                            if (i == 0) {
                                bundle.putString("imageLocalUrl", str);
                            } else if (TextUtils.isEmpty(string2) && TextUtils.isEmpty(string3)) {
                                IUiListener iUiListener2 = iUiListener;
                                if (iUiListener2 != null) {
                                    iUiListener2.onError(new UiError(-6, Constants.MSG_SHARE_GETIMG_ERROR, null));
                                    SLog.e("openSDK_LOG.QQShare", "shareToMobileQQ -- error: 获取分享图片失败!");
                                }
                                e.a().a(1, "SHARE_CHECK_SDK", "1000", QQShare.this.c.getAppId(), String.valueOf(0), Long.valueOf(SystemClock.elapsedRealtime()), 0, 1, Constants.MSG_SHARE_GETIMG_ERROR);
                                return;
                            }
                            QQShare.this.d(activity, bundle, iUiListener);
                        }

                        @Override // com.tencent.open.utils.d
                        public void a(int i, ArrayList<String> arrayList) {
                            if (i == 0) {
                                bundle.putStringArrayList("imageLocalUrlArray", arrayList);
                            } else if (TextUtils.isEmpty(string2) && TextUtils.isEmpty(string3)) {
                                IUiListener iUiListener2 = iUiListener;
                                if (iUiListener2 != null) {
                                    iUiListener2.onError(new UiError(-6, Constants.MSG_SHARE_GETIMG_ERROR, null));
                                    SLog.e("openSDK_LOG.QQShare", "shareToMobileQQ -- error: 获取分享图片失败!");
                                }
                                e.a().a(1, "SHARE_CHECK_SDK", "1000", QQShare.this.c.getAppId(), String.valueOf(0), Long.valueOf(SystemClock.elapsedRealtime()), 0, 1, Constants.MSG_SHARE_GETIMG_ERROR);
                                return;
                            }
                            QQShare.this.d(activity, bundle, iUiListener);
                        }
                    });
                }
            }
        } else if (bundle.getInt("req_type", 1) == 5) {
            c(activity, bundle, iUiListener);
        } else {
            d(activity, bundle, iUiListener);
        }
        SLog.i("openSDK_LOG.QQShare", "shareToMobileQQ() -- end");
    }

    /* JADX WARN: Code restructure failed: missing block: B:16:0x00cc, code lost:
    
        if (r3 != false) goto L21;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void c(android.app.Activity r9, android.os.Bundle r10, com.tencent.tauth.IUiListener r11) {
        /*
            Method dump skipped, instructions count: 267
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.connect.share.QQShare.c(android.app.Activity, android.os.Bundle, com.tencent.tauth.IUiListener):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(Activity activity, Bundle bundle, IUiListener iUiListener) {
        SLog.i("openSDK_LOG.QQShare", "doShareToQQ() -- start");
        StringBuffer stringBuffer = new StringBuffer("mqqapi://share/to_fri?src_type=app&version=1&file_type=news");
        String string = bundle.getString("imageUrl");
        String string2 = bundle.getString("title");
        String string3 = bundle.getString("summary");
        String string4 = bundle.getString("targetUrl");
        String string5 = bundle.getString("audio_url");
        int i = bundle.getInt("req_type", 1);
        String string6 = bundle.getString(SHARE_TO_QQ_ARK_INFO);
        String string7 = bundle.getString(SHARE_TO_QQ_MINI_PROGRAM_APPID);
        String string8 = bundle.getString(SHARE_TO_QQ_MINI_PROGRAM_PATH);
        String string9 = bundle.getString(SHARE_TO_QQ_MINI_PROGRAM_TYPE);
        int i2 = bundle.getInt("cflag", 0);
        String string10 = bundle.getString("share_qq_ext_str");
        String a2 = m.a(activity);
        if (a2 == null) {
            a2 = bundle.getString("appName");
        }
        String str = a2;
        String string11 = bundle.getString("imageLocalUrl");
        ArrayList<String> stringArrayList = bundle.getStringArrayList("imageLocalUrlArray");
        String appId = this.c.getAppId();
        String openIdWithCache = this.c.getOpenIdWithCache();
        SLog.i("openSDK_LOG.QQShare", "doShareToQQ -- openid: " + openIdWithCache + ",appName=" + str);
        if (stringArrayList != null && stringArrayList.size() >= 2) {
            String str2 = stringArrayList.get(0);
            if (str2 == null) {
                str2 = "";
            }
            stringBuffer.append("&file_data=" + Base64.encodeToString(m.j(str2), 2));
            String str3 = stringArrayList.get(1);
            if (i == 7 && !TextUtils.isEmpty(str3) && k.c(activity, "8.3.3") < 0) {
                SLog.e("openSDK_LOG.QQShare", "doShareToQQ() share to mini program set file uri empty");
                str3 = null;
            }
            Uri a3 = m.a(activity, appId, str3);
            if (a3 != null) {
                stringBuffer.append("&file_uri=").append(Base64.encodeToString(m.j(a3.toString()), 2));
            }
        } else if (!TextUtils.isEmpty(string11)) {
            stringBuffer.append("&file_data=" + Base64.encodeToString(m.j(string11), 2));
        }
        if (!TextUtils.isEmpty(string)) {
            stringBuffer.append("&image_url=" + Base64.encodeToString(m.j(string), 2));
        }
        if (!TextUtils.isEmpty(string2)) {
            stringBuffer.append("&title=" + Base64.encodeToString(m.j(string2), 2));
        }
        if (!TextUtils.isEmpty(string3)) {
            stringBuffer.append("&description=" + Base64.encodeToString(m.j(string3), 2));
        }
        if (!TextUtils.isEmpty(appId)) {
            stringBuffer.append("&share_id=" + appId);
        }
        if (!TextUtils.isEmpty(string4)) {
            stringBuffer.append("&url=" + Base64.encodeToString(m.j(string4), 2));
        }
        if (!TextUtils.isEmpty(str)) {
            if (str.length() > 20) {
                str = str.substring(0, 20) + "...";
            }
            stringBuffer.append("&app_name=" + Base64.encodeToString(m.j(str), 2));
        }
        if (!TextUtils.isEmpty(openIdWithCache)) {
            stringBuffer.append("&open_id=" + Base64.encodeToString(m.j(openIdWithCache), 2));
        }
        if (!TextUtils.isEmpty(string5)) {
            stringBuffer.append("&audioUrl=" + Base64.encodeToString(m.j(string5), 2));
        }
        stringBuffer.append("&req_type=" + Base64.encodeToString(m.j(String.valueOf(i)), 2));
        if (!TextUtils.isEmpty(string7)) {
            stringBuffer.append("&mini_program_appid=" + Base64.encodeToString(m.j(String.valueOf(string7)), 2));
        }
        if (!TextUtils.isEmpty(string8)) {
            stringBuffer.append("&mini_program_path=" + Base64.encodeToString(m.j(String.valueOf(string8)), 2));
        }
        if (!TextUtils.isEmpty(string9)) {
            stringBuffer.append("&mini_program_type=" + Base64.encodeToString(m.j(String.valueOf(string9)), 2));
        }
        if (!TextUtils.isEmpty(string6)) {
            stringBuffer.append("&share_to_qq_ark_info=" + Base64.encodeToString(m.j(string6), 2));
        }
        if (!TextUtils.isEmpty(string10)) {
            stringBuffer.append("&share_qq_ext_str=" + Base64.encodeToString(m.j(string10), 2));
        }
        stringBuffer.append("&cflag=" + Base64.encodeToString(m.j(String.valueOf(i2)), 2));
        stringBuffer.append("&third_sd=" + Base64.encodeToString(m.j(String.valueOf(m.c())), 2));
        SLog.v("openSDK_LOG.QQShare", "doShareToQQ -- url: " + stringBuffer.toString());
        com.tencent.connect.a.a.a(g.a(), this.c, "requireApi", "shareToNativeQQ");
        Intent intent = new Intent(CommonConstant.ACTION.HWID_SCHEME_URL);
        intent.setData(Uri.parse(stringBuffer.toString()));
        intent.putExtra(Constants.KEY_PASS_REPORT_VIA_PARAM, m.a(this.c.getOpenId(), i2 == 1 ? "11" : "10", "3", Constants.VIA_SHARE_TO_QQ, this.c.getAppId(), this.mViaShareQQType, "", "", "0", "1", "0"));
        intent.putExtra(Constants.PARAM_PKG_NAME, activity.getPackageName());
        if (m.f(activity, "4.6.0")) {
            SLog.i("openSDK_LOG.QQShare", "doShareToQQ, qqver below 4.6.");
            UIListenerManager.getInstance().setListenerWithRequestcode(Constants.REQUEST_OLD_SHARE, iUiListener);
            a(activity, intent, Constants.REQUEST_OLD_SHARE);
        } else {
            SLog.i("openSDK_LOG.QQShare", "doShareToQQ, qqver greater than 4.6.");
            if (UIListenerManager.getInstance().setListnerWithAction("shareToQQ", iUiListener) != null) {
                SLog.i("openSDK_LOG.QQShare", "doShareToQQ, last listener is not null, cancel it.");
            }
            a(activity, 10103, intent, true);
        }
        SLog.i("openSDK_LOG.QQShare", "doShareToQQ() --end");
    }
}
