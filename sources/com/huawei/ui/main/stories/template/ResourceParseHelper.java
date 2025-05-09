package com.huawei.ui.main.stories.template;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import androidx.core.util.Consumer;
import com.huawei.health.R;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.constant.WatchFaceConstant;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.main.stories.template.ResourceDownloadHelper;
import com.huawei.ui.main.stories.template.ResourceParseHelper;
import defpackage.jdx;
import defpackage.nrt;
import defpackage.nsn;
import defpackage.rxx;
import defpackage.rya;
import defpackage.rye;
import defpackage.ryk;
import health.compact.a.CommonUtil;
import health.compact.a.IoUtils;
import health.compact.a.LanguageUtil;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Vector;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/* loaded from: classes7.dex */
public class ResourceParseHelper implements ResourceDownloadHelper.FileResult {
    private static ConfigInfoCallback d;
    private static Handler e = new Handler(new Handler.Callback() { // from class: com.huawei.ui.main.stories.template.ResourceParseHelper.5
        @Override // android.os.Handler.Callback
        public boolean handleMessage(Message message) {
            int i = message.what;
            if (i == 1) {
                if (!(message.obj instanceof String)) {
                    return false;
                }
                String str = (String) message.obj;
                if (ResourceParseHelper.d == null) {
                    return false;
                }
                ResourceParseHelper.d.getTitleName(str);
                return false;
            }
            if (i == 2) {
                if (!(message.obj instanceof String)) {
                    return false;
                }
                String str2 = (String) message.obj;
                if (ResourceParseHelper.d == null) {
                    return false;
                }
                ResourceParseHelper.d.getDescription(str2);
                return false;
            }
            if (i != 3) {
                if (i != 4) {
                    return false;
                }
                LogUtil.h("ResourceParseHelper", "parse data error");
                if (ResourceParseHelper.d == null) {
                    return false;
                }
                ResourceParseHelper.d.showParseErrorAlert();
                return false;
            }
            if (!(message.obj instanceof String)) {
                return false;
            }
            String str3 = (String) message.obj;
            if (ResourceParseHelper.d == null) {
                return false;
            }
            ResourceParseHelper.d.getImagePath(str3);
            return false;
        }
    });

    /* renamed from: a, reason: collision with root package name */
    private JsonResult f10512a;
    private ryk b;
    private HashMap<String, String> c;
    private String h = "";

    public interface ConfigInfoCallback {
        void getDescription(String str);

        void getImagePath(String str);

        void getTitleName(String str);

        void showParseErrorAlert();
    }

    public interface JsonResult {
        void onFail();

        void onResult(ryk rykVar);
    }

    public ResourceParseHelper() {
        HashMap<String, String> hashMap = new HashMap<>();
        this.c = hashMap;
        hashMap.clear();
        this.c.put("StressCardConstructor", "1.0.0");
        this.c.put("SleepCardConstructor", "1.0.0");
        this.c.put("HeartRateConstructor", "1.0.0");
        this.c.put("BloodSugarCardConstructor", "1.0.0");
        this.c.put("BloodPressureCardConstructor", "1.0.0");
        this.c.put("WeightCardConstructor", "1.0.6");
        this.c.put("BloodOxygenCardConstructor", "1.0.0");
        this.c.put("BodyTemperatureCardConstructor", "1.0.0");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void a(String str, String str2) {
        FileInputStream fileInputStream;
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("ResourceParseHelper", "getString name is empty");
            d();
            return;
        }
        String c = CommonUtil.c(b(str2));
        if (TextUtils.isEmpty(c)) {
            LogUtil.h("ResourceParseHelper", "getString path is empty");
        }
        FileInputStream fileInputStream2 = null;
        fileInputStream2 = null;
        fileInputStream2 = null;
        try {
            try {
            } catch (Throwable th) {
                th = th;
                fileInputStream = null;
            }
        } catch (IOException e2) {
            e = e2;
        } catch (ParserConfigurationException e3) {
            e = e3;
        } catch (SAXException e4) {
            e = e4;
        }
        if (!new File(c).exists()) {
            LogUtil.h("ResourceParseHelper", "file is null");
            d();
            IoUtils.e((Closeable) null);
            return;
        }
        fileInputStream = c != null ? new FileInputStream(c) : null;
        try {
            DocumentBuilder newDocumentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            if (newDocumentBuilder == null) {
                LogUtil.h("ResourceParseHelper", "getString documentBuilder is null");
            }
            Document parse = (newDocumentBuilder == null || fileInputStream == null) ? null : newDocumentBuilder.parse(fileInputStream);
            if (parse == null) {
                LogUtil.h("ResourceParseHelper", "getString document is null");
            }
            Element documentElement = parse != null ? parse.getDocumentElement() : null;
            if (documentElement == null) {
                LogUtil.h("ResourceParseHelper", "getString element is null");
            }
            NodeList childNodes = documentElement != null ? documentElement.getChildNodes() : null;
            if (childNodes == null) {
                LogUtil.h("ResourceParseHelper", "getString nodeList is null");
            } else {
                e(str, childNodes);
            }
            IoUtils.e(fileInputStream);
        } catch (IOException e5) {
            e = e5;
            fileInputStream2 = fileInputStream;
            LogUtil.b("ResourceParseHelper", "getString exception", e);
            IoUtils.e(fileInputStream2);
            Message obtain = Message.obtain();
            obtain.what = 0;
            e.sendMessage(obtain);
        } catch (ParserConfigurationException e6) {
            e = e6;
            fileInputStream2 = fileInputStream;
            LogUtil.b("ResourceParseHelper", "getString exception", e);
            IoUtils.e(fileInputStream2);
            Message obtain2 = Message.obtain();
            obtain2.what = 0;
            e.sendMessage(obtain2);
        } catch (SAXException e7) {
            e = e7;
            fileInputStream2 = fileInputStream;
            LogUtil.b("ResourceParseHelper", "getString exception", e);
            IoUtils.e(fileInputStream2);
            Message obtain22 = Message.obtain();
            obtain22.what = 0;
            e.sendMessage(obtain22);
        } catch (Throwable th2) {
            th = th2;
            IoUtils.e(fileInputStream);
            throw th;
        }
        Message obtain222 = Message.obtain();
        obtain222.what = 0;
        e.sendMessage(obtain222);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    private int e() {
        char c;
        String str = TextUtils.isEmpty(this.h) ? "" : this.h;
        str.hashCode();
        switch (str.hashCode()) {
            case 331334480:
                if (str.equals("BloodOxygenCardConstructor")) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case 354423382:
                if (str.equals("StressCardConstructor")) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case 1135464211:
                if (str.equals("SleepCardConstructor")) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case 1487186862:
                if (str.equals("BloodSugarCardConstructor")) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            case 1698958836:
                if (str.equals("HeartRateConstructor")) {
                    c = 4;
                    break;
                }
                c = 65535;
                break;
            case 1783641739:
                if (str.equals("BloodPressureCardConstructor")) {
                    c = 5;
                    break;
                }
                c = 65535;
                break;
            case 1836318994:
                if (str.equals("WeightCardConstructor")) {
                    c = 6;
                    break;
                }
                c = 65535;
                break;
            case 2131694104:
                if (str.equals("BodyTemperatureCardConstructor")) {
                    c = 7;
                    break;
                }
                c = 65535;
                break;
            default:
                c = 65535;
                break;
        }
        switch (c) {
            case 0:
                return R.raw._2131886081_res_0x7f120001;
            case 1:
                return R.raw._2131886241_res_0x7f1200a1;
            case 2:
                return R.raw._2131886233_res_0x7f120099;
            case 3:
                return R.raw._2131886083_res_0x7f120003;
            case 4:
                return R.raw._2131886188_res_0x7f12006c;
            case 5:
                return R.raw._2131886082_res_0x7f120002;
            case 6:
                return R.raw._2131886295_res_0x7f1200d7;
            case 7:
                return R.raw._2131886084_res_0x7f120004;
            default:
                return -1;
        }
    }

    public void a(String str, JsonResult jsonResult) {
        this.f10512a = jsonResult;
        this.h = str;
        Vector vector = new Vector();
        vector.add(str);
        new ResourceDownloadHelper().d(vector, this);
    }

    @Override // com.huawei.ui.main.stories.template.ResourceDownloadHelper.FileResult
    public void onFail(List<String> list) {
        String b = rye.b(this.h);
        if (b != null && !TextUtils.isEmpty(b) && !rye.e(rya.d(this.h))) {
            rxx.b().e(this.h, new Consumer() { // from class: com.huawei.ui.main.stories.template.ResourceParseHelper$$ExternalSyntheticLambda2
                @Override // androidx.core.util.Consumer
                public final void accept(Object obj) {
                    ResourceParseHelper.this.c((ryk) obj);
                }
            });
            return;
        }
        int e2 = e();
        if (e2 == -1) {
            JsonResult jsonResult = this.f10512a;
            if (jsonResult != null) {
                jsonResult.onFail();
                return;
            }
            return;
        }
        rxx.b().d(e2, new Consumer() { // from class: com.huawei.ui.main.stories.template.ResourceParseHelper$$ExternalSyntheticLambda3
            @Override // androidx.core.util.Consumer
            public final void accept(Object obj) {
                ResourceParseHelper.this.e((ryk) obj);
            }
        });
    }

    /* synthetic */ void c(ryk rykVar) {
        this.b = rykVar;
        JsonResult jsonResult = this.f10512a;
        if (jsonResult != null) {
            jsonResult.onResult(rykVar);
        }
    }

    /* synthetic */ void e(ryk rykVar) {
        this.b = rykVar;
        JsonResult jsonResult = this.f10512a;
        if (jsonResult != null) {
            jsonResult.onResult(rykVar);
        }
    }

    @Override // com.huawei.ui.main.stories.template.ResourceDownloadHelper.FileResult
    public void onSuccess() {
        rxx.b().e(this.h, new Consumer() { // from class: com.huawei.ui.main.stories.template.ResourceParseHelper$$ExternalSyntheticLambda0
            @Override // androidx.core.util.Consumer
            public final void accept(Object obj) {
                ResourceParseHelper.this.d((ryk) obj);
            }
        });
    }

    /* synthetic */ void d(ryk rykVar) {
        this.b = rykVar;
        JsonResult jsonResult = this.f10512a;
        if (jsonResult != null) {
            jsonResult.onResult(rykVar);
        }
    }

    public static void a(final String str, final String str2, ConfigInfoCallback configInfoCallback) {
        d = configInfoCallback;
        jdx.b(new Runnable() { // from class: ryc
            @Override // java.lang.Runnable
            public final void run() {
                ResourceParseHelper.a(str, str2);
            }
        });
    }

    public static void d(final String str, final String str2, ConfigInfoCallback configInfoCallback) {
        d = configInfoCallback;
        jdx.b(new Runnable() { // from class: ryh
            @Override // java.lang.Runnable
            public final void run() {
                ResourceParseHelper.e(str, str2);
            }
        });
    }

    public static void d(ConfigInfoCallback configInfoCallback) {
        d = configInfoCallback;
    }

    /* JADX WARN: Removed duplicated region for block: B:19:0x0057  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean e(java.lang.String r10) {
        /*
            r9 = this;
            boolean r0 = android.text.TextUtils.isEmpty(r10)
            java.lang.String r1 = "ResourceParseHelper"
            if (r0 == 0) goto L14
            java.lang.String r10 = "uuid=null "
            java.lang.Object[] r10 = new java.lang.Object[]{r10}
            com.huawei.hwlogsmodel.LogUtil.b(r1, r10)
            r10 = 0
            return r10
        L14:
            java.lang.String r0 = defpackage.rye.b(r10)
            java.util.HashMap<java.lang.String, java.lang.String> r2 = r9.c
            java.lang.Object r2 = r2.get(r10)
            java.lang.String r2 = (java.lang.String) r2
            boolean r3 = android.text.TextUtils.isEmpty(r0)
            if (r3 != 0) goto L60
            java.lang.String r3 = "."
            java.lang.String r4 = ""
            java.lang.String r0 = r0.replace(r3, r4)
            boolean r5 = android.text.TextUtils.isEmpty(r2)
            if (r5 == 0) goto L37
            java.lang.String r2 = "0"
            goto L3b
        L37:
            java.lang.String r2 = r2.replace(r3, r4)
        L3b:
            r3 = 0
            long r5 = java.lang.Long.parseLong(r0)     // Catch: java.lang.NumberFormatException -> L46
            long r3 = java.lang.Long.parseLong(r2)     // Catch: java.lang.NumberFormatException -> L47
            goto L52
        L46:
            r5 = r3
        L47:
            java.lang.String r7 = "NumberFormatException: oldResourceVersion= "
            java.lang.String r8 = " presetResourceVersion="
            java.lang.Object[] r0 = new java.lang.Object[]{r7, r0, r8, r2}
            com.huawei.hwlogsmodel.LogUtil.b(r1, r0)
        L52:
            int r0 = (r5 > r3 ? 1 : (r5 == r3 ? 0 : -1))
            if (r0 >= 0) goto L57
            goto L60
        L57:
            java.lang.String r10 = defpackage.rya.d(r10)
            boolean r10 = defpackage.rye.e(r10)
            goto L61
        L60:
            r10 = 1
        L61:
            return r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.ui.main.stories.template.ResourceParseHelper.e(java.lang.String):boolean");
    }

    private static void d() {
        Message obtain = Message.obtain();
        obtain.what = 4;
        obtain.obj = "";
        e.sendMessage(obtain);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void e(String str, String str2) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("ResourceParseHelper", "getString name is empty");
        }
        String d2 = d(BaseApplication.getContext(), str, str2);
        Message obtain = Message.obtain();
        obtain.what = 3;
        obtain.obj = d2;
        e.sendMessage(obtain);
    }

    private static String d(Context context, String str, String str2) {
        boolean ag = nsn.ag(context);
        boolean a2 = nrt.a(context);
        if (!ag && !a2) {
            return CommonUtil.c(rya.d(str2) + "img/" + str + ".webp");
        }
        if (ag && a2) {
            return CommonUtil.c(rya.d(str2) + "img/" + str + "_tahidi_dark.webp");
        }
        if (ag) {
            return CommonUtil.c(rya.d(str2) + "img/" + str + "_tahidi.webp");
        }
        return CommonUtil.c(rya.d(str2) + "img/" + str + "_dark.webp");
    }

    private static void e(String str, NodeList nodeList) {
        NamedNodeMap attributes;
        Node namedItem;
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node item = nodeList.item(i);
            if (item != null && item.getNodeType() == 1 && (attributes = item.getAttributes()) != null && (namedItem = attributes.getNamedItem("name")) != null && str.equals(namedItem.getNodeValue())) {
                String textContent = item.getTextContent();
                Message obtain = Message.obtain();
                if (str.contains("IDS_name")) {
                    obtain.what = 1;
                }
                if (str.contains("IDS_desc")) {
                    obtain.what = 2;
                }
                obtain.obj = textContent;
                e.sendMessage(obtain);
                return;
            }
        }
        Message obtain2 = Message.obtain();
        if (str.contains("IDS_name")) {
            obtain2.what = 1;
        }
        if (str.contains("IDS_desc")) {
            obtain2.what = 2;
        }
        obtain2.obj = "";
        e.sendMessage(obtain2);
    }

    private static String b(String str) {
        String str2;
        Locale locale = Locale.getDefault();
        String language = locale.getLanguage();
        String country = locale.getCountry();
        String q = CommonUtil.q(language);
        if (q != null) {
            country = q;
        }
        String str3 = rya.d(str) + "lang/strings";
        String c = CommonUtil.c(str3 + com.huawei.openalliance.ad.constant.Constants.LINK + language + WatchFaceConstant.XML_SUFFIX);
        String c2 = CommonUtil.c(str3 + com.huawei.openalliance.ad.constant.Constants.LINK + language + com.huawei.openalliance.ad.constant.Constants.LINK + country + WatchFaceConstant.XML_SUFFIX);
        if (!TextUtils.isEmpty(c2) && new File(c2).exists()) {
            return c2;
        }
        if (!TextUtils.isEmpty(c) && new File(c).exists()) {
            return c;
        }
        Context context = BaseApplication.getContext();
        if (!LanguageUtil.j(context)) {
            return str3 + WatchFaceConstant.XML_SUFFIX;
        }
        String str4 = str3 + com.huawei.openalliance.ad.constant.Constants.LINK + language + com.huawei.openalliance.ad.constant.Constants.LINK;
        if (LanguageUtil.m(context)) {
            str2 = str4 + "CN";
        } else {
            str2 = str4 + "TW";
        }
        return str2 + WatchFaceConstant.XML_SUFFIX;
    }

    public static void a() {
        if (d != null) {
            d = null;
        }
    }
}
