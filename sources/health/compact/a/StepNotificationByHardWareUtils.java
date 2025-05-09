package health.compact.a;

import android.os.Build;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.phoneservice.faq.base.constants.TrackConstants$Events;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/* loaded from: classes.dex */
public class StepNotificationByHardWareUtils {
    private static final String e = File.separator + "odm" + File.separator + "etc" + File.separator + "permissions" + File.separator + "huawei.sensor.features.unavailable.xml";
    private static final ArrayList<String> b = new ArrayList(16) { // from class: health.compact.a.StepNotificationByHardWareUtils.1
        {
            add("MT6873");
            add("MT6853");
            add("MT6768");
        }
    };

    private StepNotificationByHardWareUtils() {
    }

    private static boolean e(String str) {
        if (str != null) {
            return str.toUpperCase(Locale.ENGLISH).startsWith("MT") || str.toUpperCase(Locale.ENGLISH).startsWith("HELIO");
        }
        com.huawei.hwlogsmodel.LogUtil.h("Step_StepNotificationByHardW", "hardware is null");
        return false;
    }

    public static boolean a() {
        if (CommonUtil.bh() && CommonUtil.ch()) {
            return a("sensor.ext_stepcounter");
        }
        if (CommonUtil.bh() && CommonUtil.av()) {
            return true;
        }
        String str = Build.HARDWARE;
        if (CommonUtil.bh() && CommonUtil.be() && e(str)) {
            return b.contains(str.toUpperCase(Locale.ENGLISH));
        }
        return (CommonUtil.bh() && !CommonUtil.au() && e(str)) ? false : true;
    }

    public static boolean a(String str) {
        File file = new File(e);
        if (!file.exists()) {
            com.huawei.hwlogsmodel.LogUtil.h("Step_StepNotificationByHardW", "isSupportByParseXmlFile configFile is null.");
            return true;
        }
        try {
            Element documentElement = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(file).getDocumentElement();
            if (documentElement == null) {
                com.huawei.hwlogsmodel.LogUtil.h("Step_StepNotificationByHardW", "isSupportByParseXmlFile root is null.");
                return true;
            }
            NodeList childNodes = documentElement.getChildNodes();
            if (childNodes == null) {
                com.huawei.hwlogsmodel.LogUtil.h("Step_StepNotificationByHardW", "isSupportByParseXmlFile motions is null.");
                return true;
            }
            return e(childNodes, str);
        } catch (FileNotFoundException unused) {
            com.huawei.hwlogsmodel.LogUtil.b("Step_StepNotificationByHardW", "isSupportByParseXmlFile fileNotFoundException");
            return true;
        } catch (IOException unused2) {
            com.huawei.hwlogsmodel.LogUtil.b("Step_StepNotificationByHardW", "isSupportByParseXmlFile Exception");
            return true;
        } catch (ParserConfigurationException e2) {
            com.huawei.hwlogsmodel.LogUtil.b("Step_StepNotificationByHardW", "isSupportByParseXmlFile Exception", e2.getMessage());
            return true;
        } catch (SAXException e3) {
            com.huawei.hwlogsmodel.LogUtil.b("Step_StepNotificationByHardW", "isSupportByParseXmlFile sAXException", e3.getMessage());
            return true;
        }
    }

    private static boolean e(NodeList nodeList, String str) {
        for (int i = 0; i < nodeList.getLength(); i++) {
            try {
                Node item = nodeList.item(i);
                if (item != null && item.getNodeType() == 1 && item.getAttributes().getNamedItem(TrackConstants$Events.FEATURE) != null && str.equals(item.getAttributes().getNamedItem(TrackConstants$Events.FEATURE).getNodeValue())) {
                    return false;
                }
            } catch (NumberFormatException e2) {
                com.huawei.hwlogsmodel.LogUtil.b("Step_StepNotificationByHardW", ExceptionUtils.d(e2));
                return false;
            }
        }
        return true;
    }
}
