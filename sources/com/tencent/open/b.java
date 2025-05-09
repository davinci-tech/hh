package com.tencent.open;

import android.net.Uri;
import android.webkit.WebView;
import com.huawei.hianalytics.visual.autocollect.instrument.WebViewInstrumentation;
import com.huawei.operation.utils.Constants;
import com.tencent.open.log.SLog;
import java.io.UnsupportedEncodingException;
import java.lang.ref.WeakReference;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/* loaded from: classes7.dex */
public class b {

    /* renamed from: a, reason: collision with root package name */
    protected HashMap<String, C0290b> f11334a = new HashMap<>();

    public static class a {

        /* renamed from: a, reason: collision with root package name */
        protected WeakReference<WebView> f11336a;
        protected long b;
        protected String c;

        public a(WebView webView, long j, String str) {
            this.f11336a = new WeakReference<>(webView);
            this.b = j;
            this.c = str;
        }

        public void a(Object obj) {
            String obj2;
            WebView webView = this.f11336a.get();
            if (webView == null) {
                return;
            }
            if (obj instanceof String) {
                obj2 = "'" + ((Object) ((String) obj).replace("\\", "\\\\").replace("'", "\\'")) + "'";
            } else if ((obj instanceof Number) || (obj instanceof Long) || (obj instanceof Integer) || (obj instanceof Double) || (obj instanceof Float)) {
                obj2 = obj.toString();
            } else {
                obj2 = obj instanceof Boolean ? obj.toString() : "'undefined'";
            }
            String str = "javascript:window.JsBridge&&JsBridge.callback(" + this.b + ",{'r':0,'result':" + obj2 + "});";
            webView.loadUrl(str);
            WebViewInstrumentation.loadUrl(webView, str);
        }

        public void a() {
            WebView webView = this.f11336a.get();
            if (webView == null) {
                return;
            }
            String str = "javascript:window.JsBridge&&JsBridge.callback(" + this.b + ",{'r':1,'result':'no such method'})";
            webView.loadUrl(str);
            WebViewInstrumentation.loadUrl(webView, str);
        }

        public void a(String str) {
            WebView webView = this.f11336a.get();
            if (webView != null) {
                String str2 = Constants.JAVA_SCRIPT + str;
                webView.loadUrl(str2);
                WebViewInstrumentation.loadUrl(webView, str2);
            }
        }
    }

    /* renamed from: com.tencent.open.b$b, reason: collision with other inner class name */
    public static class C0290b {
        public boolean customCallback() {
            return false;
        }

        /* JADX WARN: Code restructure failed: missing block: B:44:0x013d, code lost:
        
            r13.a((java.lang.Object) null);
         */
        /* JADX WARN: Code restructure failed: missing block: B:45:0x0140, code lost:
        
            return;
         */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public void call(java.lang.String r11, java.util.List<java.lang.String> r12, com.tencent.open.b.a r13) {
            /*
                Method dump skipped, instructions count: 351
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.tencent.open.b.C0290b.call(java.lang.String, java.util.List, com.tencent.open.b$a):void");
        }
    }

    public void a(C0290b c0290b, String str) {
        this.f11334a.put(str, c0290b);
    }

    public void a(String str, String str2, List<String> list, a aVar) {
        SLog.v("openSDK_LOG.JsBridge", "getResult---objName = " + str + " methodName = " + str2);
        int size = list.size();
        for (int i = 0; i < size; i++) {
            try {
                list.set(i, URLDecoder.decode(list.get(i), "UTF-8"));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        C0290b c0290b = this.f11334a.get(str);
        if (c0290b != null) {
            SLog.d("openSDK_LOG.JsBridge", "call----");
            c0290b.call(str2, list, aVar);
        } else {
            SLog.d("openSDK_LOG.JsBridge", "not call----objName NOT FIND");
            if (aVar != null) {
                aVar.a();
            }
        }
    }

    public boolean a(WebView webView, String str) {
        SLog.v("openSDK_LOG.JsBridge", "-->canHandleUrl---url = " + str);
        if (str == null || !Uri.parse(str).getScheme().equals("jsbridge")) {
            return false;
        }
        ArrayList arrayList = new ArrayList(Arrays.asList((str + "/#").split("/")));
        if (arrayList.size() < 6) {
            return false;
        }
        String str2 = (String) arrayList.get(2);
        String str3 = (String) arrayList.get(3);
        List<String> subList = arrayList.subList(4, arrayList.size() - 1);
        a aVar = new a(webView, 4L, str);
        webView.getUrl();
        a(str2, str3, subList, aVar);
        return true;
    }
}
