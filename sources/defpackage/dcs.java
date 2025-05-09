package defpackage;

import android.text.TextUtils;
import androidx.core.app.NotificationCompat;
import com.huawei.featureuserprofile.route.hwgpxmodel.Wpt;
import com.huawei.health.ecologydevice.connectivity.ScanFilter;
import com.huawei.hms.network.embedded.x2;
import com.huawei.hwdevice.phoneprocess.mgr.exercise.utils.seuqneceutils.SequenceDetailFieldConfig;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.nfc.PluginPayAdapter;
import com.huawei.ui.main.stories.recommendcloud.constants.RecommendConstants;
import com.huawei.watchface.videoedit.gles.Constant;
import defpackage.bjf;
import defpackage.cev;
import health.compact.a.CommonUtil;
import health.compact.a.IoUtils;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/* loaded from: classes3.dex */
public class dcs {
    private static Map<String, Integer> b = new HashMap<String, Integer>(16) { // from class: dcs.3
        {
            put(ScanFilter.MatchRule.STRICT.toString(), 1);
            put(ScanFilter.MatchRule.INCLUSIVE.toString(), 4);
            put(ScanFilter.MatchRule.FRONT.toString(), 2);
            put(ScanFilter.MatchRule.REAR.toString(), 3);
        }
    };

    public static dcz b(String str) {
        FileInputStream fileInputStream;
        Element documentElement;
        String c = CommonUtil.c(str);
        FileInputStream fileInputStream2 = null;
        if (c == null) {
            LogUtil.h("ProductFileParser", "ProductFileParser loadProductList tempPathList == null");
            return null;
        }
        if (!new File(c).exists()) {
            LogUtil.h("ProductFileParser", "ProductFileParser loadProductList file not exists");
            return null;
        }
        dcz dczVar = new dcz();
        DocumentBuilderFactory newInstance = DocumentBuilderFactory.newInstance();
        try {
            dea.c(newInstance);
        } catch (ParserConfigurationException e) {
            LogUtil.b("ProductFileParser", "ProductFileParser loadProductList e:", e.getMessage());
        }
        try {
            try {
                DocumentBuilder newDocumentBuilder = newInstance.newDocumentBuilder();
                fileInputStream = new FileInputStream(c);
                try {
                    try {
                        documentElement = newDocumentBuilder.parse(fileInputStream).getDocumentElement();
                    } catch (IOException | ParserConfigurationException | SAXException unused) {
                        fileInputStream2 = fileInputStream;
                        LogUtil.b("ProductFileParser", "ProductFileParser loadProductList: ParserConfigurationException | IOException | SAXException.");
                        IoUtils.e(fileInputStream2);
                        return dczVar;
                    }
                } catch (Throwable th) {
                    th = th;
                    IoUtils.e(fileInputStream);
                    throw th;
                }
            } catch (IOException | ParserConfigurationException | SAXException unused2) {
            }
            if (documentElement == null) {
                LogUtil.h("ProductFileParser", "ProductFileParser loadProductList: element is null.");
                IoUtils.e(fileInputStream);
                IoUtils.e(fileInputStream);
                return dczVar;
            }
            d(dczVar, documentElement);
            NodeList childNodes = documentElement.getChildNodes();
            if (childNodes != null) {
                e(dczVar, childNodes);
            }
            IoUtils.e(fileInputStream);
            return dczVar;
        } catch (Throwable th2) {
            th = th2;
            fileInputStream = fileInputStream2;
        }
    }

    private static void d(dcz dczVar, Element element) {
        NamedNodeMap attributes = element.getAttributes();
        for (int i = 0; i < attributes.getLength(); i++) {
            e(dczVar, attributes, i);
        }
    }

    private static void e(dcz dczVar, NamedNodeMap namedNodeMap, int i) {
        String nodeName = namedNodeMap.item(i).getNodeName();
        String nodeValue = namedNodeMap.item(i).getNodeValue();
        if ("uuid".equals(nodeName)) {
            dczVar.n(nodeValue);
            return;
        }
        if ("kind".equals(nodeName)) {
            dczVar.b(dks.c(nodeValue));
            return;
        }
        if (RecommendConstants.VER.equals(nodeName)) {
            dczVar.o(nodeValue);
            return;
        }
        if ("h5Type".equals(nodeName)) {
            dczVar.j(nodeValue);
        } else if (x2.PROTOCOL.equals(nodeName)) {
            dczVar.m(nodeValue);
        } else {
            LogUtil.h("ProductFileParser", "ProductFileParser loadProductList: can not equals getNodeName ", nodeName);
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:56:0x0087, code lost:
    
        if (r3.equals("resolution") == false) goto L49;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static void e(defpackage.dcz r6, org.w3c.dom.NodeList r7) {
        /*
            r0 = 0
            r1 = r0
        L2:
            int r2 = r7.getLength()
            if (r1 >= r2) goto Lc5
            org.w3c.dom.Node r2 = r7.item(r1)
            short r3 = r2.getNodeType()
            r4 = 1
            if (r3 != r4) goto Lc1
            java.lang.String r3 = r2.getNodeName()
            r3.hashCode()
            int r5 = r3.hashCode()
            switch(r5) {
                case -1724546052: goto L8a;
                case -1600030548: goto L81;
                case 3023933: goto L76;
                case 3524221: goto L6b;
                case 96965648: goto L60;
                case 98712316: goto L55;
                case 103668165: goto L4a;
                case 130625071: goto L3f;
                case 938321246: goto L31;
                case 1539594266: goto L23;
                default: goto L21;
            }
        L21:
            goto L95
        L23:
            java.lang.String r4 = "introduction"
            boolean r3 = r3.equals(r4)
            if (r3 != 0) goto L2d
            goto L95
        L2d:
            r4 = 9
            goto L96
        L31:
            java.lang.String r4 = "measure"
            boolean r3 = r3.equals(r4)
            if (r3 != 0) goto L3b
            goto L95
        L3b:
            r4 = 8
            goto L96
        L3f:
            java.lang.String r4 = "manifest"
            boolean r3 = r3.equals(r4)
            if (r3 != 0) goto L48
            goto L95
        L48:
            r4 = 7
            goto L96
        L4a:
            java.lang.String r4 = "match"
            boolean r3 = r3.equals(r4)
            if (r3 != 0) goto L53
            goto L95
        L53:
            r4 = 6
            goto L96
        L55:
            java.lang.String r4 = "guide"
            boolean r3 = r3.equals(r4)
            if (r3 != 0) goto L5e
            goto L95
        L5e:
            r4 = 5
            goto L96
        L60:
            java.lang.String r4 = "extra"
            boolean r3 = r3.equals(r4)
            if (r3 != 0) goto L69
            goto L95
        L69:
            r4 = 4
            goto L96
        L6b:
            java.lang.String r4 = "scan"
            boolean r3 = r3.equals(r4)
            if (r3 != 0) goto L74
            goto L95
        L74:
            r4 = 3
            goto L96
        L76:
            java.lang.String r4 = "bind"
            boolean r3 = r3.equals(r4)
            if (r3 != 0) goto L7f
            goto L95
        L7f:
            r4 = 2
            goto L96
        L81:
            java.lang.String r5 = "resolution"
            boolean r3 = r3.equals(r5)
            if (r3 != 0) goto L96
            goto L95
        L8a:
            java.lang.String r4 = "description"
            boolean r3 = r3.equals(r4)
            if (r3 != 0) goto L93
            goto L95
        L93:
            r4 = r0
            goto L96
        L95:
            r4 = -1
        L96:
            switch(r4) {
                case 0: goto Lbe;
                case 1: goto Lba;
                case 2: goto Lb6;
                case 3: goto Lb2;
                case 4: goto Lae;
                case 5: goto Laa;
                case 6: goto La6;
                case 7: goto La2;
                case 8: goto L9e;
                case 9: goto L9a;
                default: goto L99;
            }
        L99:
            goto Lc1
        L9a:
            e(r6, r2)
            goto Lc1
        L9e:
            g(r6, r2)
            goto Lc1
        La2:
            f(r6, r2)
            goto Lc1
        La6:
            i(r6, r2)
            goto Lc1
        Laa:
            c(r6, r2)
            goto Lc1
        Lae:
            a(r6, r2)
            goto Lc1
        Lb2:
            h(r6, r2)
            goto Lc1
        Lb6:
            d(r6, r2)
            goto Lc1
        Lba:
            j(r6, r2)
            goto Lc1
        Lbe:
            b(r6, r2)
        Lc1:
            int r1 = r1 + 1
            goto L2
        Lc5:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.dcs.e(dcz, org.w3c.dom.NodeList):void");
    }

    private static void i(dcz dczVar, Node node) {
        NodeList childNodes = node.getChildNodes();
        if (childNodes != null) {
            for (int i = 0; i < childNodes.getLength(); i++) {
                Node item = childNodes.item(i);
                if ("content".equals(item.getNodeName())) {
                    dczVar.d(item.getAttributes().getNamedItem("kind").getNodeValue(), item.getAttributes().getNamedItem("uuid").getNodeValue());
                }
            }
        }
    }

    private static void f(dcz dczVar, Node node) {
        NamedNodeMap attributes = node.getAttributes();
        String nodeValue = attributes.getNamedItem("name").getNodeValue();
        d(nodeValue, attributes);
        String nodeValue2 = attributes.getNamedItem("summary").getNodeValue();
        e(nodeValue2, attributes);
        dczVar.a(attributes.getNamedItem(Constant.TYPE_PHOTO).getNodeValue(), nodeValue, nodeValue2);
        if (attributes.getNamedItem("company") != null) {
            String nodeValue3 = attributes.getNamedItem("company").getNodeValue();
            LogUtil.a("ProductFileParser", "ProductFileParser company = ", nodeValue3);
            dczVar.n().a(nodeValue3);
        }
    }

    private static void b(dcz dczVar, Node node) {
        NodeList childNodes = node.getChildNodes();
        if (childNodes != null) {
            for (int i = 0; i < childNodes.getLength(); i++) {
                Node item = childNodes.item(i);
                if ("content".equals(item.getNodeName())) {
                    String nodeValue = item.getAttributes().getNamedItem(Constant.TEXT).getNodeValue();
                    dczVar.e(nodeValue, item.getAttributes().getNamedItem("img").getNodeValue());
                    e(nodeValue, item.getAttributes());
                }
            }
        }
    }

    private static void e(String str, NamedNodeMap namedNodeMap) {
        if (namedNodeMap == null) {
            return;
        }
        LogUtil.c("ProductFileParser", "handleDefaultChar textId = ", str);
        Node namedItem = namedNodeMap.getNamedItem("defaultchartype");
        Node namedItem2 = namedNodeMap.getNamedItem("defaultcharvalue");
        if (namedItem == null || namedItem2 == null) {
            return;
        }
        a(str, namedItem.getNodeValue(), namedItem2.getNodeValue());
    }

    private static void d(String str, NamedNodeMap namedNodeMap) {
        if (namedNodeMap == null) {
            return;
        }
        LogUtil.c("ProductFileParser", "handleMainChar textId = ", str);
        Node namedItem = namedNodeMap.getNamedItem("mainchartype");
        Node namedItem2 = namedNodeMap.getNamedItem("maincharvalue");
        if (namedItem == null || namedItem2 == null) {
            return;
        }
        a(str, namedItem.getNodeValue(), namedItem2.getNodeValue());
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    private static void a(String str, String str2, String str3) {
        char c;
        str2.hashCode();
        switch (str2.hashCode()) {
            case -1615941336:
                if (str2.equals("doublearray")) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case -1508543256:
                if (str2.equals("stringarray")) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case -1325958191:
                if (str2.equals(SequenceDetailFieldConfig.DOUBLE)) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case -891985903:
                if (str2.equals("string")) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            case 104431:
                if (str2.equals("int")) {
                    c = 4;
                    break;
                }
                c = 65535;
                break;
            case 566720458:
                if (str2.equals("intarray")) {
                    c = 5;
                    break;
                }
                c = 65535;
                break;
            default:
                c = 65535;
                break;
        }
        if (c == 0) {
            a(str, str3);
            return;
        }
        if (c == 1) {
            i(str, str3);
            return;
        }
        if (c == 2) {
            b(str, str3);
            return;
        }
        if (c == 3) {
            d(str, str3);
        } else if (c == 4) {
            c(str, str3);
        } else {
            if (c != 5) {
                return;
            }
            e(str, str3);
        }
    }

    private static void c(String str, String str2) {
        LogUtil.c("ProductFileParser", "handleIntString textId = ", str);
        LogUtil.c("ProductFileParser", "handleIntString charValue = ", str2);
        try {
            dcx.d(str, Integer.parseInt(str2));
        } catch (NumberFormatException unused) {
            LogUtil.b("ProductFileParser", "handleIntString NumberFormatException.");
        }
    }

    private static void e(String str, String str2) {
        LogUtil.c("ProductFileParser", "handleIntArrayString textId = ", str);
        LogUtil.c("ProductFileParser", "handleIntArrayString charValue = ", str2);
        if (str2.length() > 2) {
            if (str2.startsWith("[") || str2.endsWith("]")) {
                dcx.c(str, b(str2.substring(str2.indexOf("[") + 1, str2.indexOf("]")).split(",")));
            }
        }
    }

    private static int[] b(String[] strArr) {
        if (strArr == null || strArr.length == 0) {
            return new int[0];
        }
        int[] iArr = new int[strArr.length];
        for (int i = 0; i < strArr.length; i++) {
            try {
                iArr[i] = Integer.parseInt(strArr[i]);
            } catch (NumberFormatException unused) {
                LogUtil.b("ProductFileParser", "tranStringArrayToIntArray NumberFormatException.");
                return new int[0];
            }
        }
        return iArr;
    }

    private static void b(String str, String str2) {
        LogUtil.c("ProductFileParser", "handleDoubleString textId = ", str);
        LogUtil.c("ProductFileParser", "handleDoubleString charValue = ", str2);
        try {
            dcx.d(str, Double.parseDouble(str2));
        } catch (NumberFormatException unused) {
            LogUtil.b("ProductFileParser", "handleDoubleString NumberFormatException.");
        }
    }

    private static void a(String str, String str2) {
        LogUtil.c("ProductFileParser", "handleDoubleArrayString textId = ", str);
        LogUtil.c("ProductFileParser", "handleDoubleArrayString charValue = ", str2);
        if (str2.length() > 2) {
            if (str2.startsWith("[") || str2.endsWith("]")) {
                dcx.e(str, d(str2.substring(str2.indexOf("[") + 1, str2.indexOf("]")).split(",")));
            }
        }
    }

    private static double[] d(String[] strArr) {
        if (strArr == null || strArr.length == 0) {
            return new double[0];
        }
        double[] dArr = new double[strArr.length];
        for (int i = 0; i < strArr.length; i++) {
            try {
                dArr[i] = Double.parseDouble(strArr[i]);
            } catch (NumberFormatException unused) {
                LogUtil.b("ProductFileParser", "tranStringArrayToDoubleArray NumberFormatException.");
                return new double[0];
            }
        }
        return dArr;
    }

    private static void d(String str, String str2) {
        LogUtil.c("ProductFileParser", "handleString textId = ", str, ", handleString charValue = ", str2);
        try {
            dcx.c(str, str2);
        } catch (NumberFormatException unused) {
            LogUtil.b("ProductFileParser", "handleString NumberFormatException.");
        }
    }

    private static void i(String str, String str2) {
        LogUtil.c("ProductFileParser", "handleStringArray textId = ", str, ", handleStringArray charValue = ", str2);
        if (str2.length() > 2) {
            if (str2.startsWith("[") || str2.endsWith("]")) {
                dcx.a(str, str2.substring(str2.indexOf("[") + 1, str2.indexOf("]")).split(","));
            }
        }
    }

    private static void c(dcz dczVar, Node node) {
        LogUtil.a("ProductFileParser", "nodeNameEqualsGuide---");
        String nodeValue = node.getAttributes().getNamedItem("type").getNodeValue();
        if ("measure".equals(nodeValue)) {
            l(dczVar, node);
            return;
        }
        if ("bind".equals(nodeValue)) {
            n(dczVar, node);
            return;
        }
        if (NotificationCompat.GROUP_KEY_SILENT.equals(nodeValue)) {
            k(dczVar, node);
        } else if ("use_guide".equals(nodeValue)) {
            m(dczVar, node);
        } else {
            LogUtil.h("ProductFileParser", "nodeNameEqualsGuide nodeValue is not support");
        }
    }

    private static void k(dcz dczVar, Node node) {
        NodeList childNodes = node.getChildNodes();
        if (childNodes == null) {
            return;
        }
        for (int i = 0; i < childNodes.getLength(); i++) {
            Node item = childNodes.item(i);
            if ("content".equals(item.getNodeName())) {
                String nodeValue = item.getAttributes().getNamedItem(Constant.TEXT).getNodeValue();
                dczVar.b(nodeValue, item.getAttributes().getNamedItem("img").getNodeValue());
                e(nodeValue, item.getAttributes());
            }
        }
    }

    private static void m(dcz dczVar, Node node) {
        NodeList childNodes = node.getChildNodes();
        if (childNodes == null) {
            return;
        }
        for (int i = 0; i < childNodes.getLength(); i++) {
            Node item = childNodes.item(i);
            if ("content".equals(item.getNodeName())) {
                String nodeValue = item.getAttributes().getNamedItem("title") != null ? item.getAttributes().getNamedItem("title").getNodeValue() : "";
                String nodeValue2 = item.getAttributes().getNamedItem(Constant.TEXT).getNodeValue();
                dczVar.c(nodeValue, nodeValue2, item.getAttributes().getNamedItem("img").getNodeValue());
                e(nodeValue2, item.getAttributes());
            }
        }
    }

    private static void n(dcz dczVar, Node node) {
        NodeList childNodes = node.getChildNodes();
        if (childNodes == null) {
            return;
        }
        for (int i = 0; i < childNodes.getLength(); i++) {
            Node item = childNodes.item(i);
            if ("content".equals(item.getNodeName())) {
                String nodeValue = item.getAttributes().getNamedItem(Constant.TEXT).getNodeValue();
                dczVar.c(nodeValue, item.getAttributes().getNamedItem("img").getNodeValue());
                e(nodeValue, item.getAttributes());
            }
        }
    }

    private static void l(dcz dczVar, Node node) {
        NodeList childNodes = node.getChildNodes();
        if (childNodes == null) {
            return;
        }
        for (int i = 0; i < childNodes.getLength(); i++) {
            Node item = childNodes.item(i);
            if ("content".equals(item.getNodeName())) {
                String nodeValue = item.getAttributes().getNamedItem(Constant.TEXT).getNodeValue();
                dczVar.a(nodeValue, item.getAttributes().getNamedItem("img").getNodeValue());
                e(nodeValue, item.getAttributes());
            }
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    private static void a(dcz dczVar, Node node) {
        char c;
        NamedNodeMap attributes = node.getAttributes();
        for (int i = 0; i < attributes.getLength(); i++) {
            String nodeName = attributes.item(i).getNodeName();
            String nodeValue = attributes.item(i).getNodeValue();
            nodeName.hashCode();
            switch (nodeName.hashCode()) {
                case -2054444593:
                    if (nodeName.equals("isSupportAutoPauseSuprression")) {
                        c = 0;
                        break;
                    }
                    c = 65535;
                    break;
                case -2052799751:
                    if (nodeName.equals("is_support_weight_data_claiming")) {
                        c = 1;
                        break;
                    }
                    c = 65535;
                    break;
                case -1725405834:
                    if (nodeName.equals("app_oversea_version")) {
                        c = 2;
                        break;
                    }
                    c = 65535;
                    break;
                case -1691944104:
                    if (nodeName.equals("marketing_position_id")) {
                        c = 3;
                        break;
                    }
                    c = 65535;
                    break;
                case -1543071020:
                    if (nodeName.equals(PluginPayAdapter.KEY_DEVICE_INFO_NAME)) {
                        c = 4;
                        break;
                    }
                    c = 65535;
                    break;
                case -1485638852:
                    if (nodeName.equals("is_support_sync_while_connected")) {
                        c = 5;
                        break;
                    }
                    c = 65535;
                    break;
                case -1074076728:
                    if (nodeName.equals("device_manager_img")) {
                        c = 6;
                        break;
                    }
                    c = 65535;
                    break;
                case -814800055:
                    if (nodeName.equals("pageVersion")) {
                        c = 7;
                        break;
                    }
                    c = 65535;
                    break;
                case -610886354:
                    if (nodeName.equals("is_support_other_activity_connect")) {
                        c = '\b';
                        break;
                    }
                    c = 65535;
                    break;
                case -545996111:
                    if (nodeName.equals("is_dual_mode")) {
                        c = '\t';
                        break;
                    }
                    c = 65535;
                    break;
                case -486433098:
                    if (nodeName.equals("is_support_multi_user")) {
                        c = '\n';
                        break;
                    }
                    c = 65535;
                    break;
                case -446443396:
                    if (nodeName.equals("is_huawei_wsp_scale")) {
                        c = 11;
                        break;
                    }
                    c = 65535;
                    break;
                case -279310820:
                    if (nodeName.equals("otaVersion")) {
                        c = '\f';
                        break;
                    }
                    c = 65535;
                    break;
                case 110364:
                    if (nodeName.equals("ota")) {
                        c = '\r';
                        break;
                    }
                    c = 65535;
                    break;
                case 245350454:
                    if (nodeName.equals("buy_url")) {
                        c = 14;
                        break;
                    }
                    c = 65535;
                    break;
                case 478205205:
                    if (nodeName.equals("app_domestic_version")) {
                        c = 15;
                        break;
                    }
                    c = 65535;
                    break;
                case 712563297:
                    if (nodeName.equals("isSupportIntermit")) {
                        c = 16;
                        break;
                    }
                    c = 65535;
                    break;
                case 826513207:
                    if (nodeName.equals("device_ota_img")) {
                        c = 17;
                        break;
                    }
                    c = 65535;
                    break;
                case 896325356:
                    if (nodeName.equals("is_support_nps")) {
                        c = 18;
                        break;
                    }
                    c = 65535;
                    break;
                case 896331711:
                    if (nodeName.equals("is_support_uds")) {
                        c = 19;
                        break;
                    }
                    c = 65535;
                    break;
                case 913803662:
                    if (nodeName.equals("miaomall_url")) {
                        c = 20;
                        break;
                    }
                    c = 65535;
                    break;
                case 1045360705:
                    if (nodeName.equals("is_support_keep_connected_when_back")) {
                        c = 21;
                        break;
                    }
                    c = 65535;
                    break;
                case 1094676056:
                    if (nodeName.equals("isSupportControl")) {
                        c = 22;
                        break;
                    }
                    c = 65535;
                    break;
                case 1109191185:
                    if (nodeName.equals("deviceId")) {
                        c = 23;
                        break;
                    }
                    c = 65535;
                    break;
                case 1224176480:
                    if (nodeName.equals("is_support_question_and_suggestion")) {
                        c = 24;
                        break;
                    }
                    c = 65535;
                    break;
                case 1249881585:
                    if (nodeName.equals("manger_support_info_invisible")) {
                        c = 25;
                        break;
                    }
                    c = 65535;
                    break;
                case 1291234845:
                    if (nodeName.equals("isSupportWeight")) {
                        c = 26;
                        break;
                    }
                    c = 65535;
                    break;
                case 1977567821:
                    if (nodeName.equals("isSupportScore")) {
                        c = 27;
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
                case 1:
                case 3:
                case 4:
                case 5:
                case 6:
                case 7:
                case '\b':
                case '\t':
                case '\n':
                case 11:
                case '\f':
                case '\r':
                case 14:
                case 16:
                case 17:
                case 18:
                case 19:
                case 20:
                case 21:
                case 22:
                case 24:
                case 25:
                case 26:
                case 27:
                    dczVar.h(nodeName, nodeValue);
                    break;
                case 2:
                    dczVar.d(nodeValue);
                    break;
                case 15:
                    dczVar.e(nodeValue);
                    break;
                case 23:
                    dczVar.b(nodeValue);
                    break;
                default:
                    LogUtil.c("ProductFileParser", "resource is not exist");
                    break;
            }
        }
    }

    private static void h(dcz dczVar, Node node) {
        dczVar.b(e(node.getAttributes()));
        NodeList childNodes = node.getChildNodes();
        if (childNodes == null) {
            return;
        }
        ArrayList arrayList = new ArrayList(16);
        boolean z = false;
        for (int i = 0; i < childNodes.getLength(); i++) {
            Node item = childNodes.item(i);
            if (Constant.FILTER.equals(item.getNodeName())) {
                String[] c = c(item.getAttributes());
                if (!z) {
                    dczVar.a(e(c));
                    z = true;
                }
                arrayList.addAll(c(c));
            }
        }
        dczVar.a(arrayList);
    }

    private static cev e(NamedNodeMap namedNodeMap) {
        cev.b bVar = new cev.b();
        Node namedItem = namedNodeMap.getNamedItem(Wpt.MODE);
        if (namedItem != null) {
            bVar.a(a(namedItem.getNodeValue()));
        }
        if (namedNodeMap.getNamedItem("duration") != null) {
            bVar.c(CommonUtil.h(r1.getNodeValue()), TimeUnit.SECONDS);
        }
        Node namedItem2 = namedNodeMap.getNamedItem("pair");
        if (namedItem2 != null) {
            bVar.e(namedItem2.getNodeValue());
        }
        return bVar.c();
    }

    private static int a(String str) {
        if ("BLE".equals(str)) {
            return 1;
        }
        if ("CLASSIC".equals(str)) {
            return 2;
        }
        if ("AUDIO".equals(str)) {
            return 4;
        }
        if ("NO_BLE".equals(str)) {
            return 32;
        }
        return "WIFI".equals(str) ? 8 : 0;
    }

    private static String[] c(NamedNodeMap namedNodeMap) {
        String[] strArr = {"", "", ""};
        if (namedNodeMap == null) {
            return strArr;
        }
        Node namedItem = namedNodeMap.getNamedItem("name");
        if (namedItem != null) {
            strArr[0] = namedItem.getNodeValue();
        }
        Node namedItem2 = namedNodeMap.getNamedItem("match");
        if (namedItem2 != null) {
            strArr[1] = namedItem2.getNodeValue();
        }
        Node namedItem3 = namedNodeMap.getNamedItem("address");
        if (namedItem3 != null) {
            strArr[2] = namedItem3.getNodeValue();
        }
        return strArr;
    }

    private static ScanFilter e(String[] strArr) {
        ScanFilter.MatchRule matchRule = ScanFilter.MatchRule.FRONT;
        if (!TextUtils.isEmpty(strArr[1])) {
            matchRule = ScanFilter.MatchRule.valueOf(strArr[1]);
        }
        return ScanFilter.b(strArr[0], strArr[2], matchRule);
    }

    private static List<bjf> c(String[] strArr) {
        ArrayList arrayList = new ArrayList(16);
        bjf.a aVar = new bjf.a();
        aVar.e(!TextUtils.isEmpty(strArr[1]) ? b.get(strArr[1]).intValue() : 0);
        for (String str : strArr[0].split(";")) {
            aVar.a(str);
            arrayList.add(aVar.a());
        }
        return arrayList;
    }

    private static void g(dcz dczVar, Node node) {
        NamedNodeMap attributes = node.getAttributes();
        dczVar.g(attributes.getNamedItem("kit").getNodeValue());
        dczVar.i(attributes.getNamedItem(Wpt.MODE).getNodeValue());
        LogUtil.c("ProductFileParser", "content--entry:", attributes.getNamedItem("entry"));
        dczVar.h(attributes.getNamedItem("entry") != null ? attributes.getNamedItem("entry").getNodeValue() : null);
        dczVar.k(attributes.getNamedItem("type") != null ? attributes.getNamedItem("type").getNodeValue() : null);
    }

    private static void d(dcz dczVar, Node node) {
        if (dczVar != null) {
            NamedNodeMap attributes = node.getAttributes();
            LogUtil.a("ProductFileParser", "content--entry:", attributes.getNamedItem("entry"));
            dczVar.a(attributes.getNamedItem("type") != null ? attributes.getNamedItem("type").getNodeValue() : null);
        }
    }

    private static void e(dcz dczVar, Node node) {
        NamedNodeMap attributes = node.getAttributes();
        dczVar.m().a(attributes.getNamedItem("type") != null ? attributes.getNamedItem("type").getNodeValue() : "");
    }

    private static void j(dcz dczVar, Node node) {
        NamedNodeMap attributes = node.getAttributes();
        if (attributes.getNamedItem("resolution") != null) {
            dczVar.v().a(attributes.getNamedItem("resolution").getNodeValue());
            LogUtil.c("ProductFileParser", "resolution is ", attributes.getNamedItem("resolution").getNodeValue());
        } else {
            dczVar.v().a("0");
        }
    }
}
