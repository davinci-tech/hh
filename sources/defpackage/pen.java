package defpackage;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.constant.WatchFaceConstant;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.nfc.PluginPayAdapter;
import com.huawei.operation.utils.Constants;
import com.huawei.ui.commonui.webview.WebViewActivity;
import com.huawei.ui.homewear21.home.bean.WearHomeXmlParseBean;
import com.huawei.ui.homewear21.home.legal.EnterprisePrivacyActivity;
import com.huawei.ui.homewear21.home.legal.WearHomeOpenBaseServiceActivity;
import com.huawei.ui.main.stories.me.views.privacy.ServiceItemActivity;
import health.compact.a.CommonUtil;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.apache.commons.io.FileUtils;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/* loaded from: classes6.dex */
public class pen {

    /* renamed from: a, reason: collision with root package name */
    private static String[] f16098a;
    private static List<nzf> b = new ArrayList(10);
    private static List<nyu> d;

    public static void e(String str, String str2, Context context) {
        if (TextUtils.isEmpty(str) || str2 == null || context == null) {
            LogUtil.h("LegalInformationUtils", " title : context is null:");
            return;
        }
        Intent intent = new Intent(context, (Class<?>) WebViewActivity.class);
        intent.putExtra("WebViewActivity.REQUEST_URL_KEY", "file:" + str);
        intent.putExtra(Constants.JUMP_MODE_KEY, 7);
        intent.putExtra("WebViewActivity.TITLE", str2);
        try {
            context.startActivity(intent);
        } catch (ActivityNotFoundException unused) {
            LogUtil.b("LegalInformationUtils", "jumpWebView ActivityNotFoundException");
        }
    }

    public static void e(Context context, String str, String str2, String str3, int i) {
        if (context == null || str == null) {
            LogUtil.h("LegalInformationUtils", "context and url is null");
            return;
        }
        Intent intent = new Intent(context, (Class<?>) ServiceItemActivity.class);
        if (!TextUtils.isEmpty(str2)) {
            intent.putExtra("device_id", str2);
        }
        if (!TextUtils.isEmpty(str3)) {
            intent.putExtra(PluginPayAdapter.KEY_DEVICE_INFO_NAME, str3);
        }
        intent.putExtra("Agreement_key", str);
        intent.putExtra("product_type", i);
        try {
            context.startActivity(intent);
        } catch (ActivityNotFoundException unused) {
            LogUtil.b("LegalInformationUtils", "goServiceItemActivity ActivityNotFoundException");
        }
    }

    public static void e(Context context, int i, int i2) {
        if (context == null) {
            LogUtil.h("LegalInformationUtils", "context is null");
            return;
        }
        if (i == 3) {
            Intent intent = new Intent(context, (Class<?>) WearHomeOpenBaseServiceActivity.class);
            try {
                intent.putExtra("device_version_type", i2);
                context.startActivity(intent);
                return;
            } catch (ActivityNotFoundException unused) {
                LogUtil.b("LegalInformationUtils", "goServiceStatement ActivityNotFoundException");
                return;
            }
        }
        if (i == 10) {
            try {
                context.startActivity(new Intent(context, (Class<?>) EnterprisePrivacyActivity.class));
                return;
            } catch (ActivityNotFoundException unused2) {
                LogUtil.b("LegalInformationUtils", "goServiceStatement ActivityNotFoundException");
                return;
            }
        }
        LogUtil.h("LegalInformationUtils", "jumpType is ", Integer.valueOf(i));
    }

    public static List<nyu> a(String str, int i, String str2) {
        nuj.d(i, str2);
        b.clear();
        String[] a2 = nuj.a();
        f16098a = a2;
        for (String str3 : a2) {
            c(str3, str);
        }
        List<nyu> c = new nyv().c(b);
        d = c;
        return c;
    }

    private static void c(String str, String str2) {
        nzf e = nuj.e(str, str2);
        if (!TextUtils.isEmpty(e.a())) {
            b.add(e);
        } else {
            LogUtil.h("LegalInformationUtils", "featureId", str);
        }
    }

    public static ArrayList<WearHomeXmlParseBean> b(String str) {
        File[] listFiles = new File(str).listFiles();
        ArrayList<WearHomeXmlParseBean> arrayList = new ArrayList<>(16);
        if (listFiles == null) {
            LogUtil.h("LegalInformationUtils", "mFiles is null");
            return arrayList;
        }
        for (File file : listFiles) {
            if (!file.isFile()) {
                LogUtil.a("LegalInformationUtils", 0, "is not File");
            } else {
                String c = CommonUtil.c(file.getPath());
                if (!TextUtils.isEmpty(c) && file.getName().endsWith(WatchFaceConstant.XML_SUFFIX)) {
                    return d(c);
                }
            }
        }
        return arrayList;
    }

    private static ArrayList<WearHomeXmlParseBean> d(String str) {
        ArrayList<WearHomeXmlParseBean> arrayList = new ArrayList<>(16);
        DocumentBuilderFactory newInstance = DocumentBuilderFactory.newInstance();
        try {
            newInstance.setFeature("http://xml.org/sax/features/external-general-entities", false);
            newInstance.setFeature("http://xml.org/sax/features/external-parameter-entities", false);
            newInstance.setFeature("http://javax.xml.XMLConstants/feature/secure-processing", true);
            newInstance.setFeature("http://apache.org/xml/features/disallow-doctype-decl", true);
            newInstance.setValidating(true);
        } catch (ParserConfigurationException unused) {
            LogUtil.b("LegalInformationUtils", "ParserConfigurationException");
        }
        FileInputStream fileInputStream = null;
        try {
            try {
                try {
                    DocumentBuilder newDocumentBuilder = newInstance.newDocumentBuilder();
                    String c = CommonUtil.c(str);
                    if (c != null) {
                        fileInputStream = FileUtils.openInputStream(FileUtils.getFile(c));
                        d(arrayList, newDocumentBuilder.parse(fileInputStream).getDocumentElement().getElementsByTagName("app"));
                    }
                    if (fileInputStream != null) {
                        try {
                            fileInputStream.close();
                        } catch (IOException unused2) {
                            LogUtil.b("LegalInformationUtils", "close IOException");
                        }
                    }
                    return arrayList;
                } catch (Throwable th) {
                    if (fileInputStream != null) {
                        try {
                            fileInputStream.close();
                        } catch (IOException unused3) {
                            LogUtil.b("LegalInformationUtils", "close IOException");
                        }
                    }
                    throw th;
                }
            } catch (SAXException unused4) {
                LogUtil.b("LegalInformationUtils", "SAXException");
                if (fileInputStream != null) {
                    try {
                        fileInputStream.close();
                    } catch (IOException unused5) {
                        LogUtil.b("LegalInformationUtils", "close IOException");
                    }
                }
                return arrayList;
            }
        } catch (IOException unused6) {
            LogUtil.b("LegalInformationUtils", "IOException");
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (IOException unused7) {
                    LogUtil.b("LegalInformationUtils", "close IOException");
                }
            }
            return arrayList;
        } catch (ParserConfigurationException unused8) {
            LogUtil.b("LegalInformationUtils", "ParserConfigurationException");
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (IOException unused9) {
                    LogUtil.b("LegalInformationUtils", "close IOException");
                }
            }
            return arrayList;
        }
    }

    private static void d(ArrayList<WearHomeXmlParseBean> arrayList, NodeList nodeList) {
        int length = nodeList.getLength();
        for (int i = 0; i < length; i++) {
            WearHomeXmlParseBean wearHomeXmlParseBean = new WearHomeXmlParseBean();
            if (nodeList.item(i) instanceof Element) {
                b(nodeList, wearHomeXmlParseBean, i);
            }
            arrayList.add(wearHomeXmlParseBean);
        }
    }

    private static void b(NodeList nodeList, WearHomeXmlParseBean wearHomeXmlParseBean, int i) {
        NodeList childNodes = ((Element) nodeList.item(i)).getChildNodes();
        int length = childNodes.getLength();
        for (int i2 = 0; i2 < length; i2++) {
            Node item = childNodes.item(i2);
            String nodeName = item.getNodeName();
            nodeName.hashCode();
            if (nodeName.equals("file-name")) {
                wearHomeXmlParseBean.setValueName(item.getTextContent());
            } else if (nodeName.equals("name")) {
                wearHomeXmlParseBean.setXmlName(item.getTextContent());
            } else {
                LogUtil.h("LegalInformationUtils", "xml is default");
            }
        }
    }
}
