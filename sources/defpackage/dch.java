package defpackage;

import android.text.TextUtils;
import com.huawei.health.device.api.HonourDeviceConstantsApi;
import com.huawei.health.device.model.HealthDevice;
import com.huawei.health.ecologydevice.manager.ResourceManager;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.beans.TitleBean;
import com.huawei.ui.main.stories.recommendcloud.constants.RecommendConstants;
import health.compact.a.CommonUtil;
import health.compact.a.IoUtils;
import health.compact.a.Services;
import health.compact.a.Utils;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/* loaded from: classes3.dex */
public class dch {

    /* renamed from: a, reason: collision with root package name */
    private static String f11580a;
    private static final Object c = new Object();
    private static ArrayList<dcu> d = new ArrayList<>();
    private static String e;
    private ArrayList<dcr> b = new ArrayList<>();

    public ArrayList<dcr> c() {
        return (ArrayList) cpt.d(this.b);
    }

    public static void a() {
        if (b() > 0) {
            LogUtil.a("PluginDevice_GroupFileParser", "parseSupportDevice supportDevice size is ", Integer.valueOf(b()));
            return;
        }
        ArrayList<dcu> arrayList = new ArrayList<>();
        DocumentBuilderFactory newInstance = DocumentBuilderFactory.newInstance();
        try {
            dea.c(newInstance);
            NodeList childNodes = newInstance.newDocumentBuilder().parse(cpp.a().getAssets().open("SupportDevices.xml")).getDocumentElement().getChildNodes();
            if (childNodes != null) {
                LogUtil.a("PluginDevice_GroupFileParser", "parseSupportDevice groups size is ", Integer.valueOf(childNodes.getLength()));
                for (int i = 0; i < childNodes.getLength(); i++) {
                    Node item = childNodes.item(i);
                    if (item.getNodeType() == 1) {
                        NamedNodeMap attributes = item.getAttributes();
                        Node namedItem = attributes.getNamedItem(RecommendConstants.FILE_ID);
                        dcu dcuVar = new dcu(c(attributes, "id"), c(attributes, RecommendConstants.VER), c(attributes, "hash"), namedItem == null ? null : namedItem.getNodeValue());
                        LogUtil.a("PluginDevice_GroupFileParser", "parseSupportDevice ProductFileInfo id:", dcuVar.e());
                        arrayList.add(dcuVar);
                    }
                }
            } else {
                LogUtil.h("PluginDevice_GroupFileParser", "parseSupportDevice NodeList is null");
            }
            LogUtil.a("PluginDevice_GroupFileParser", "parseSupportDevice supportDevice size is ", Integer.valueOf(b()));
        } catch (IOException | ParserConfigurationException | SAXException e2) {
            LogUtil.b("PluginDevice_GroupFileParser", "parseSupportDevice ex = ", e2.getMessage());
        }
        synchronized (c) {
            d = arrayList;
        }
    }

    private static int b() {
        int size;
        synchronized (c) {
            size = d.size();
        }
        return size;
    }

    public static ArrayList<dcr> a(String str) {
        FileInputStream fileInputStream;
        Document parse;
        Element documentElement;
        LogUtil.a("PluginDevice_GroupFileParser", "Enter parseGroup");
        a();
        ArrayList<dcr> arrayList = new ArrayList<>();
        DocumentBuilderFactory newInstance = DocumentBuilderFactory.newInstance();
        try {
            dea.c(newInstance);
        } catch (ParserConfigurationException e2) {
            LogUtil.b("PluginDevice_GroupFileParser", "parseGroup e:", e2.getClass().getSimpleName());
        }
        FileInputStream fileInputStream2 = null;
        try {
            try {
                DocumentBuilder newDocumentBuilder = newInstance.newDocumentBuilder();
                if (ResourceManager.e().g(cos.b + str)) {
                    LogUtil.a("PluginDevice_GroupFileParser", "parseGroup from sdcard");
                    fileInputStream = new FileInputStream(cos.b + str);
                    try {
                        parse = newDocumentBuilder.parse(fileInputStream);
                        fileInputStream2 = fileInputStream;
                    } catch (IOException e3) {
                        e = e3;
                        fileInputStream2 = fileInputStream;
                        LogUtil.b("PluginDevice_GroupFileParser", "parseGroup ex = ", e.getMessage());
                        IoUtils.e(fileInputStream2);
                        LogUtil.a("PluginDevice_GroupFileParser", "parseGroup finally groupListTemp size is ", Integer.valueOf(arrayList.size()));
                        return arrayList;
                    } catch (ParserConfigurationException e4) {
                        e = e4;
                        fileInputStream2 = fileInputStream;
                        LogUtil.b("PluginDevice_GroupFileParser", "parseGroup ex = ", e.getMessage());
                        IoUtils.e(fileInputStream2);
                        LogUtil.a("PluginDevice_GroupFileParser", "parseGroup finally groupListTemp size is ", Integer.valueOf(arrayList.size()));
                        return arrayList;
                    } catch (SAXException e5) {
                        e = e5;
                        fileInputStream2 = fileInputStream;
                        LogUtil.b("PluginDevice_GroupFileParser", "parseGroup ex = ", e.getMessage());
                        IoUtils.e(fileInputStream2);
                        LogUtil.a("PluginDevice_GroupFileParser", "parseGroup finally groupListTemp size is ", Integer.valueOf(arrayList.size()));
                        return arrayList;
                    } catch (Throwable th) {
                        th = th;
                        IoUtils.e(fileInputStream);
                        LogUtil.a("PluginDevice_GroupFileParser", "parseGroup finally groupListTemp size is ", Integer.valueOf(arrayList.size()));
                        throw th;
                    }
                } else {
                    LogUtil.a("PluginDevice_GroupFileParser", "parseGroup from assets");
                    parse = newDocumentBuilder.parse(cpp.a().getAssets().open(str));
                }
                documentElement = parse.getDocumentElement();
                e = c(documentElement.getAttributes(), RecommendConstants.VER);
                String c2 = c(documentElement.getAttributes(), "time");
                f11580a = c2;
                LogUtil.a("PluginDevice_GroupFileParser", "parseGroup version = ", e, " time=", c2);
            } catch (Throwable th2) {
                th = th2;
                fileInputStream = null;
            }
        } catch (IOException e6) {
            e = e6;
        } catch (ParserConfigurationException e7) {
            e = e7;
        } catch (SAXException e8) {
            e = e8;
        }
        if (!TextUtils.isEmpty(e) && !TextUtils.isEmpty(f11580a)) {
            NodeList childNodes = documentElement.getChildNodes();
            if (childNodes != null) {
                LogUtil.a("PluginDevice_GroupFileParser", "parseGroup groups size is ", Integer.valueOf(childNodes.getLength()));
                for (int i = 0; i < childNodes.getLength(); i++) {
                    Node item = childNodes.item(i);
                    if (item.getNodeType() == 1) {
                        c(arrayList, item);
                    }
                }
            }
            IoUtils.e(fileInputStream2);
            LogUtil.a("PluginDevice_GroupFileParser", "parseGroup finally groupListTemp size is ", Integer.valueOf(arrayList.size()));
            return arrayList;
        }
        IoUtils.e(fileInputStream2);
        LogUtil.a("PluginDevice_GroupFileParser", "parseGroup finally groupListTemp size is ", Integer.valueOf(arrayList.size()));
        return arrayList;
    }

    private static String c(NamedNodeMap namedNodeMap, String str) {
        Node namedItem;
        return (namedNodeMap == null || (namedItem = namedNodeMap.getNamedItem(str)) == null) ? "" : namedItem.getNodeValue();
    }

    public static String d() {
        return e;
    }

    public String e(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        Iterator<dcr> it = this.b.iterator();
        while (it.hasNext()) {
            dcr next = it.next();
            Iterator<dcu> it2 = next.d().iterator();
            while (it2.hasNext()) {
                dcu next2 = it2.next();
                if (str.equals(next2.e())) {
                    return next2.d();
                }
            }
            Iterator<dcu> it3 = next.b().iterator();
            while (it3.hasNext()) {
                dcu next3 = it3.next();
                if (str.equals(next3.e())) {
                    return next3.d();
                }
            }
        }
        return null;
    }

    public ArrayList<dcu> e(ArrayList<dcr> arrayList) {
        ArrayList<dcu> arrayList2 = new ArrayList<>();
        if (arrayList == null) {
            LogUtil.c("PluginDevice_GroupFileParser", "removeDuplicate groups is null");
            return arrayList2;
        }
        LogUtil.a("PluginDevice_GroupFileParser", "removeDuplicate groups size=", Integer.valueOf(arrayList.size()));
        Iterator<dcr> it = this.b.iterator();
        while (it.hasNext()) {
            dcr next = it.next();
            Iterator<dcr> it2 = arrayList.iterator();
            while (it2.hasNext()) {
                dcr next2 = it2.next();
                if (next.c() == next2.c()) {
                    c(next2, next, arrayList2);
                }
            }
        }
        this.b = arrayList;
        return arrayList2;
    }

    private void c(dcr dcrVar, dcr dcrVar2, ArrayList<dcu> arrayList) {
        Iterator<dcu> it = dcrVar.d().iterator();
        while (it.hasNext()) {
            dcu next = it.next();
            String e2 = next.e();
            if ((!dcrVar2.d().contains(next) && !dcrVar2.b().contains(next)) || !dbw.c(e2)) {
                if (b(e2, next.b(), true)) {
                    b(e2, arrayList, next);
                }
            }
        }
    }

    private void b(String str, ArrayList<dcu> arrayList, dcu dcuVar) {
        if ("6d5416d9-2167-41df-ab10-c492e152b44f".equals(str)) {
            if (((HonourDeviceConstantsApi) Services.c("PluginDevice", HonourDeviceConstantsApi.class)).getUserSureDownload()) {
                arrayList.add(dcuVar);
                return;
            }
            return;
        }
        arrayList.add(dcuVar);
    }

    public ArrayList<dcr> e() {
        LogUtil.a("PluginDevice_GroupFileParser", "getGroupList groupList size is ", Integer.valueOf(this.b.size()));
        if (this.b.size() <= 0) {
            if (!Utils.o()) {
                LogUtil.a("PluginDevice_GroupFileParser", "getGroupList groups.xml");
                this.b = a("groups.xml");
            } else {
                LogUtil.a("PluginDevice_GroupFileParser", "getGroupList groups_abroad.xml");
                if (CommonUtil.cg()) {
                    this.b = a("groups.xml");
                } else {
                    this.b = a("groups_abroad.xml");
                }
            }
        }
        return this.b;
    }

    public dcr c(HealthDevice.HealthDeviceKind healthDeviceKind) {
        if (healthDeviceKind == null) {
            LogUtil.c("PluginDevice_GroupFileParser", "getProductGroup kind is null");
            return null;
        }
        LogUtil.a("PluginDevice_GroupFileParser", "getProductGroup kind = ", healthDeviceKind.name());
        e();
        Iterator<dcr> it = this.b.iterator();
        while (it.hasNext()) {
            dcr next = it.next();
            if (next.c() == healthDeviceKind) {
                int size = next.d().size();
                LogUtil.a("PluginDevice_GroupFileParser", "getProductGroup homeProducts size is ", Integer.valueOf(size));
                if (size <= 0) {
                    if (!Utils.o()) {
                        LogUtil.a("PluginDevice_GroupFileParser", "getProductGroup parse groups.xml");
                        a("groups.xml");
                    } else {
                        LogUtil.a("PluginDevice_GroupFileParser", "getProductGroup parse groups_abroad.xml");
                        if (CommonUtil.cg()) {
                            a("groups.xml");
                        } else {
                            a("groups_abroad.xml");
                        }
                    }
                }
                return next;
            }
        }
        return null;
    }

    public void b(HealthDevice.HealthDeviceKind healthDeviceKind, String str, dcu dcuVar) {
        if (healthDeviceKind == null || TextUtils.isEmpty(str) || dcuVar == null) {
            LogUtil.c("PluginDevice_GroupFileParser", "addProduct param is invalid");
            return;
        }
        Iterator<dcr> it = this.b.iterator();
        while (it.hasNext()) {
            dcr next = it.next();
            if (next.c() == healthDeviceKind) {
                d(dcuVar.e());
                if ("home".equals(str)) {
                    next.c(dcuVar);
                    LogUtil.a("PluginDevice_GroupFileParser", "addProduct homeProducts size = ", Integer.valueOf(next.d().size()));
                    return;
                } else {
                    if (TitleBean.RIGHT_BTN_TYPE_MORE.equals(str)) {
                        next.b(dcuVar);
                        return;
                    }
                    return;
                }
            }
        }
    }

    private void d(String str) {
        Iterator<dcr> it = this.b.iterator();
        while (it.hasNext()) {
            dcr next = it.next();
            ArrayList<dcu> d2 = next.d();
            Iterator<dcu> it2 = d2.iterator();
            while (true) {
                if (!it2.hasNext()) {
                    break;
                }
                dcu next2 = it2.next();
                String e2 = next2.e();
                if (e2 != null && e2.equals(str)) {
                    d2.remove(next2);
                    break;
                }
            }
            ArrayList<dcu> b = next.b();
            Iterator<dcu> it3 = b.iterator();
            while (true) {
                if (!it3.hasNext()) {
                    break;
                }
                dcu next3 = it3.next();
                String e3 = next3.e();
                if (e3 != null && e3.equals(str)) {
                    b.remove(next3);
                    break;
                }
            }
        }
    }

    public dcu b(String str) {
        Iterator<dcr> it = this.b.iterator();
        while (it.hasNext()) {
            dcr next = it.next();
            Iterator<dcu> it2 = next.d().iterator();
            while (it2.hasNext()) {
                dcu next2 = it2.next();
                String e2 = next2.e();
                if (e2 != null && e2.equals(str)) {
                    return next2;
                }
            }
            Iterator<dcu> it3 = next.b().iterator();
            while (it3.hasNext()) {
                dcu next3 = it3.next();
                String e3 = next3.e();
                if (e3 != null && e3.equals(str)) {
                    return next3;
                }
            }
        }
        return null;
    }

    private dcu f(String str) {
        Iterator<dcr> it = this.b.iterator();
        while (it.hasNext()) {
            dcr next = it.next();
            Iterator<dcu> it2 = next.d().iterator();
            while (it2.hasNext()) {
                dcu next2 = it2.next();
                String d2 = next2.d();
                if (d2 != null && d2.equals(str)) {
                    return next2;
                }
            }
            Iterator<dcu> it3 = next.b().iterator();
            while (it3.hasNext()) {
                dcu next3 = it3.next();
                String d3 = next3.d();
                if (d3 != null && d3.equals(str)) {
                    return next3;
                }
            }
        }
        return null;
    }

    public String c(String str) {
        dcu f;
        return (str == null || (f = f(str)) == null) ? "" : f.a();
    }

    private static void c(ArrayList<dcr> arrayList, Node node) {
        NodeList childNodes = node.getChildNodes();
        if (childNodes == null) {
            return;
        }
        String c2 = c(node.getAttributes(), "kind");
        LogUtil.a("PluginDevice_GroupFileParser", "getGroupNodeList kind =", c2);
        dcr dcrVar = new dcr(dks.c(c2), c(node.getAttributes(), "name"), c(node.getAttributes(), "icon"));
        for (int i = 0; i < childNodes.getLength(); i++) {
            Node item = childNodes.item(i);
            if (item.getNodeType() == 1) {
                String c3 = c(item.getAttributes(), "view");
                if ("home".equals(c3)) {
                    a(item, dcrVar);
                } else if (TitleBean.RIGHT_BTN_TYPE_MORE.equals(c3)) {
                    e(item, dcrVar);
                }
            }
        }
        arrayList.add(dcrVar);
    }

    private static void e(Node node, dcr dcrVar) {
        LogUtil.a("PluginDevice_GroupFileParser", "Enter doMoreCase");
        Node firstChild = node.getFirstChild();
        while (firstChild != null) {
            if (firstChild.getNodeType() != 1) {
                firstChild = firstChild.getNextSibling();
            } else {
                NamedNodeMap attributes = firstChild.getAttributes();
                Node namedItem = attributes.getNamedItem(RecommendConstants.FILE_ID);
                dcrVar.b(new dcu(c(attributes, "id"), c(attributes, RecommendConstants.VER), c(attributes, "hash"), namedItem == null ? null : namedItem.getNodeValue()));
                firstChild = firstChild.getNextSibling();
            }
        }
    }

    private static void a(Node node, dcr dcrVar) {
        LogUtil.a("PluginDevice_GroupFileParser", "Enter doHomeCase");
        Node firstChild = node.getFirstChild();
        while (firstChild != null) {
            if (firstChild.getNodeType() != 1) {
                firstChild = firstChild.getNextSibling();
            } else {
                NamedNodeMap attributes = firstChild.getAttributes();
                Node namedItem = attributes.getNamedItem(RecommendConstants.FILE_ID);
                Node namedItem2 = attributes.getNamedItem("isCheck");
                dcu dcuVar = new dcu(c(attributes, "id"), c(attributes, RecommendConstants.VER), c(attributes, "hash"), namedItem == null ? null : namedItem.getNodeValue());
                if (namedItem2 != null) {
                    LogUtil.a("PluginDevice_GroupFileParser", "getGroupNodeList isCheck ", namedItem2, dcuVar.e());
                    dcuVar.a("true".equals(namedItem2.getNodeValue().trim()));
                }
                if (b(dcuVar.e(), dcuVar.b(), false)) {
                    dcrVar.c(dcuVar);
                }
                firstChild = firstChild.getNextSibling();
            }
        }
    }

    private static boolean b(String str, boolean z, boolean z2) {
        HonourDeviceConstantsApi honourDeviceConstantsApi = (HonourDeviceConstantsApi) Services.c("PluginDevice", HonourDeviceConstantsApi.class);
        if ((z2 && !honourDeviceConstantsApi.isHonourDevice(str)) || TextUtils.isEmpty(str) || !honourDeviceConstantsApi.isShowWiFiDevice(str)) {
            return false;
        }
        if (!z) {
            LogUtil.a("PluginDevice_GroupFileParser", "isAddProduct true");
            return true;
        }
        synchronized (c) {
            Iterator<dcu> it = d.iterator();
            while (it.hasNext()) {
                if (str.equals(it.next().e())) {
                    LogUtil.a("PluginDevice_GroupFileParser", "isAddProduct true");
                    return true;
                }
            }
            LogUtil.a("PluginDevice_GroupFileParser", "isAddProduct false");
            return false;
        }
    }
}
