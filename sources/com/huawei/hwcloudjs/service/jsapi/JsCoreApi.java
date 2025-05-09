package com.huawei.hwcloudjs.service.jsapi;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.webkit.WebView;
import android.widget.Toast;
import com.huawei.health.R;
import com.huawei.hms.network.embedded.r3;
import com.huawei.hms.support.api.entity.pay.HwPayConstant;
import com.huawei.hwcloudjs.JsClientApi;
import com.huawei.hwcloudjs.annotation.JSClassAttributes;
import com.huawei.hwcloudjs.annotation.JSField;
import com.huawei.hwcloudjs.annotation.JSMethod;
import com.huawei.hwcloudjs.annotation.JSNativeMsg;
import com.huawei.hwcloudjs.core.JSRequest;
import com.huawei.hwcloudjs.core.JsCallback;
import com.huawei.hwcloudjs.e.a.e;
import com.huawei.hwcloudjs.f.b;
import com.huawei.hwcloudjs.f.d;
import com.huawei.hwcloudjs.f.f;
import com.huawei.hwcloudjs.f.h;
import com.huawei.hwcloudjs.service.jsmsg.NativeMsg;
import com.huawei.operation.beans.TitleBean;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

@JSClassAttributes
/* loaded from: classes5.dex */
public class JsCoreApi extends JSRequest {
    private static final String b = "JsCoreApi";

    @JSMethod(isOpen = true, name = "showToast", permission = JsClientApi.Permission.BASE)
    public void showToast(ToastReq toastReq, JsCallback jsCallback) {
        d.c(b, "showToast begin", true);
        if (toastReq == null) {
            d.b(b, "showToast JS_RET_CODE_PARSE_PARAM_ERROR", true);
            jsCallback.failure(13);
            return;
        }
        WebView webView = jsCallback.getWebView();
        if (webView == null || webView.getContext() == null) {
            jsCallback.failure();
        } else {
            Toast.makeText(webView.getContext(), toastReq.getTitle(), toastReq.getDuration() != 1 ? 0 : 1).show();
        }
    }

    @JSMethod(isOpen = true, name = "showDialog", permission = JsClientApi.Permission.BASE)
    public void showDialog(DialogReq dialogReq, JsCallback jsCallback) {
        if (dialogReq == null) {
            jsCallback.failure(13);
            return;
        }
        WebView webView = jsCallback.getWebView();
        if (webView == null || webView.getContext() == null) {
            jsCallback.failure();
            return;
        }
        List<String> buttons = dialogReq.getButtons();
        if (buttons == null || buttons.size() < 1 || buttons.size() > 2) {
            jsCallback.failure(13);
            return;
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(webView.getContext());
        builder.setTitle(dialogReq.getTitle());
        builder.setMessage(dialogReq.getMessage());
        AlertDialog create = builder.create();
        create.setCanceledOnTouchOutside(false);
        create.show();
    }

    @JSNativeMsg(isOpen = true, permission = JsClientApi.Permission.BASE, type = "channelMessage")
    public static final class ChannelMessageReq extends NativeMsg {

        @JSField("channelName")
        private String channelName;

        @JSField("data")
        private String data;

        @JSField("origin")
        private String origin;

        @JSField("source")
        private String source;

        public void setSource(String str) {
            this.source = str;
        }

        public void setOrigin(String str) {
            this.origin = str;
        }

        public void setData(String str) {
            this.data = str;
        }

        public void setChannelName(String str) {
            this.channelName = str;
        }

        public String getSource() {
            return this.source;
        }

        public String getOrigin() {
            return this.origin;
        }

        public String getData() {
            return this.data;
        }

        public String getChannelName() {
            return this.channelName;
        }
    }

    /* loaded from: classes9.dex */
    public static final class ShareReq {
        private String desc;
        private String imgUrl;
        private String link;
        private String title;

        public void setTitle(String str) {
            this.title = str;
        }

        public void setLink(String str) {
            this.link = str;
        }

        public void setImgUrl(String str) {
            this.imgUrl = str;
        }

        public void setDesc(String str) {
            this.desc = str;
        }

        public String getTitle() {
            return this.title;
        }

        public String getLink() {
            return this.link;
        }

        public String getImgUrl() {
            return this.imgUrl;
        }

        public String getDesc() {
            return this.desc;
        }
    }

    @JSMethod(isOpen = true, name = TitleBean.RIGHT_BTN_TYPE_SHARE, permission = JsClientApi.Permission.BASE)
    public void share(ShareReq shareReq, JsCallback jsCallback) {
        d.c(b, "share begin", true);
        WebView webView = jsCallback.getWebView();
        if (webView == null) {
            jsCallback.failure();
            return;
        }
        if (shareReq == null) {
            jsCallback.failure(13);
            return;
        }
        String title = shareReq.getTitle();
        String desc = shareReq.getDesc();
        String link = shareReq.getLink();
        if (desc == null) {
            desc = "";
        }
        if (link == null) {
            link = "";
        }
        Intent intent = new Intent();
        intent.setAction("android.intent.action.SEND");
        intent.putExtra("android.intent.extra.TEXT", title + ":" + desc + " " + link);
        intent.setType("text/plain");
        try {
            webView.getContext().startActivity(Intent.createChooser(intent, webView.getContext().getString(R.string._2130851361_res_0x7f023621)));
        } catch (ActivityNotFoundException unused) {
            d.b(b, "share ActivityNotFoundException", true);
            jsCallback.failure();
        }
    }

    @JSMethod(isOpen = true, name = "preload", permission = JsClientApi.Permission.BASE)
    public void preload(PreloadReq preloadReq, JsCallback jsCallback) {
        d.c(b, "preload begin", true);
        if (preloadReq == null || preloadReq.getUrlList() == null) {
            d.b(b, "preload JS_RET_CODE_PARSE_PARAM_ERROR", true);
            jsCallback.failure(13);
            return;
        }
        WebView webView = jsCallback.getWebView();
        if (webView == null || webView.getContext() == null) {
            jsCallback.failure();
        } else {
            e.a((String[]) preloadReq.getUrlList().toArray(new String[0]), preloadReq.getFlag(), webView.getContext());
        }
    }

    /* loaded from: classes9.dex */
    public static final class DialogReq {
        private List<String> buttons;
        private String message;
        private String title;

        public void setTitle(String str) {
            this.title = str;
        }

        public void setMessage(String str) {
            this.message = str;
        }

        public void setButtons(List<String> list) {
            this.buttons = list;
        }

        public String getTitle() {
            return this.title;
        }

        public String getMessage() {
            return this.message;
        }

        public List<String> getButtons() {
            return this.buttons;
        }
    }

    @JSMethod(isOpen = true, name = "postChannelMessage", permission = JsClientApi.Permission.BASE)
    public void postChannelMessage(ChannelMessageReq channelMessageReq, JsCallback jsCallback) {
        String str;
        d.c(b, "postChannelMessage begin", true);
        if (channelMessageReq == null) {
            d.b(b, "postChannelMessage JS_CONFIG_RET_CODE_PARSE_PARAM_ERROR", true);
            jsCallback.failure(13);
            return;
        }
        WebView webView = jsCallback.getWebView();
        if (webView == null) {
            jsCallback.failure();
            return;
        }
        String a2 = b.a(webView);
        String str2 = jsCallback.getwebviewId();
        try {
            URL url = new URL(a2);
            str = url.getProtocol() + "://" + url.getAuthority();
        } catch (MalformedURLException unused) {
            d.b(b, "postChannelMessage MalformedURLException", true);
            jsCallback.failure();
            str = null;
        }
        channelMessageReq.setOrigin(str);
        channelMessageReq.setSource(str2);
        com.huawei.hwcloudjs.service.jsmsg.a.a().a((com.huawei.hwcloudjs.service.jsmsg.a) channelMessageReq);
    }

    @JSMethod(isOpen = true, name = "getSystemInfo", permission = JsClientApi.Permission.BASE)
    public void getSystemInfo(JsCallback jsCallback) {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("model", b.c());
            jSONObject.put("region", b.a());
            jSONObject.put("language", b.b());
            jsCallback.success(jSONObject.toString());
        } catch (JSONException unused) {
            jsCallback.failure();
        }
    }

    /* loaded from: classes9.dex */
    public static final class PreloadReq {
        private int flag;
        private List<String> urlList;

        public void setUrlList(List<String> list) {
            this.urlList = list;
        }

        public void setFlag(int i) {
            this.flag = i;
        }

        public List<String> getUrlList() {
            return this.urlList;
        }

        public int getFlag() {
            return this.flag;
        }
    }

    /* loaded from: classes9.dex */
    public static final class ToastReq {
        private int duration = 0;
        private String title;

        public void setTitle(String str) {
            this.title = str;
        }

        public void setDuration(int i) {
            this.duration = i;
        }

        public String getTitle() {
            return this.title;
        }

        public int getDuration() {
            return this.duration;
        }
    }

    /* loaded from: classes9.dex */
    public static final class UrlDescription {
        private String title;
        private String url;

        public void setToUrl(String str) {
            this.url = str;
        }

        public void setTitle(String str) {
            this.title = str;
        }

        public String getToUrl() {
            return this.url;
        }

        public String getTitle() {
            return this.title;
        }
    }

    @JSMethod(isOpen = true, name = "getNetworkType", permission = JsClientApi.Permission.BASE)
    public void getNetworkType(JsCallback jsCallback) {
        d.c(b, "getNetworkType begin", true);
        String a2 = f.a();
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(r3.y, a2);
            jsCallback.success(jSONObject.toString());
        } catch (JSONException unused) {
            jsCallback.failure();
        }
    }

    @JSMethod(isOpen = true)
    public void getBrowserInfo(JsCallback jsCallback) {
        d.c(b, "getBrowserInfo begin", true);
        WebView webView = jsCallback.getWebView();
        if (webView == null) {
            jsCallback.failure();
        } else {
            jsCallback.success(a.a().a(webView.getContext()));
        }
    }

    /* loaded from: classes9.dex */
    public static final class CheckAvailReq {
        private List<String> apiList;

        public void setApiList(List<String> list) {
            this.apiList = list;
        }

        public List<String> getApiList() {
            return this.apiList;
        }
    }

    @JSMethod(isOpen = true, name = "getAppInfo", permission = JsClientApi.Permission.BASE)
    public void getAppInfo(String str, JsCallback jsCallback) {
        d.c(b, "getAppInfo begin", true);
        WebView webView = jsCallback.getWebView();
        if (webView == null) {
            jsCallback.failure();
            return;
        }
        Context context = webView.getContext();
        if (!h.c(context, str)) {
            d.b(b, "getAppInfo package not Installed", true);
            jsCallback.failure(11);
            return;
        }
        String a2 = a.a().a(context, str);
        String b2 = h.b(context, str);
        int a3 = h.a(context, str);
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put(HwPayConstant.KEY_SIGN, a2);
            jSONObject.put("versionName", b2);
            jSONObject.put("versionCode", a3 + "");
            jsCallback.success(jSONObject.toString());
        } catch (JSONException unused) {
            d.c(b, "getAppInfo JSONException", true);
            jsCallback.failure();
        }
    }

    @JSMethod(isOpen = true, name = "close", permission = JsClientApi.Permission.BASE)
    public void close(JsCallback jsCallback) {
        d.c(b, "close Webview", true);
        WebView webView = jsCallback.getWebView();
        if (webView == null) {
            d.c(b, "close Webview null", true);
            jsCallback.failure();
            return;
        }
        Context context = webView.getContext();
        if (context instanceof Activity) {
            ((Activity) context).finish();
        } else {
            jsCallback.failure();
        }
    }

    @JSMethod(isOpen = true)
    public void checkAvailability(CheckAvailReq checkAvailReq, JsCallback jsCallback) {
        d.c(b, "checkAvailability begin", true);
        if (checkAvailReq == null || checkAvailReq.getApiList() == null) {
            jsCallback.failure();
            return;
        }
        JSONObject jSONObject = new JSONObject();
        try {
            List<String> apiList = checkAvailReq.getApiList();
            int size = apiList.size();
            d.c(b, "apiList:" + apiList, false);
            for (int i = 0; i < size; i++) {
                String str = apiList.get(i);
                jSONObject.put(str, com.huawei.hwcloudjs.core.a.b.a().a(str) != null);
            }
            jsCallback.success(jSONObject.toString());
        } catch (JSONException unused) {
            d.b(b, "checkAvailability JSONException", true);
            jsCallback.failure();
        }
    }
}
