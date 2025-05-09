package health.compact.a;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.hardware.SensorManager;
import android.os.Build;
import com.huawei.health.manager.util.HwCfgFilePolicy;
import com.huawei.hms.network.embedded.k;
import com.huawei.motiondetection.MotionTypeApps;
import defpackage.gnr;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/* loaded from: classes.dex */
public class StepCounterSupport {

    /* renamed from: a, reason: collision with root package name */
    private static int f13138a = -1;
    private static int b = -1;
    private static String c = "/system/etc/xml/hw_motion_config.xml";
    private static String d = "/data/cust/xml/hw_motion_config.xml";

    private StepCounterSupport() {
    }

    private static int i(Context context) {
        boolean a2 = a();
        boolean h = h(context);
        ReleaseLogUtil.b("Step_CounterSupport", "supportPrivateStandStepcounter = ", Boolean.valueOf(a2), " isSupportStandStepCounter = ", Boolean.valueOf(h));
        if (a2 && h) {
            return 1;
        }
        return h ? 2 : 3;
    }

    public static int d(Context context) {
        if (context == null) {
            LogUtil.e("Step_CounterSupport", "getDeviceSupportClass context is null");
            return 0;
        }
        int i = b;
        if (i != -1) {
            return i;
        }
        int i2 = i(context);
        b = i2;
        f13138a = i2;
        SharedPerferenceUtils.e(context, i2);
        return b;
    }

    public static int a(Context context) {
        if (context == null) {
            LogUtil.e("Step_CounterSupport", "getDeviceOriginalSupportClass context is null");
            return 0;
        }
        int i = f13138a;
        if (i != -1) {
            return i;
        }
        int i2 = i(context);
        f13138a = i2;
        return i2;
    }

    public static void c(Context context, int i) {
        if (context == null) {
            LogUtil.e("Step_CounterSupport", "setDeviceSupportClass context is null");
        } else {
            b = i;
            SharedPerferenceUtils.e(context, i);
        }
    }

    public static void e(Context context, int i) {
        if (context == null) {
            LogUtil.e("Step_CounterSupport", "setDeviceSupportClassForce context is null");
        } else {
            f13138a = i;
            c(context, i);
        }
    }

    public static boolean a() {
        LogUtil.c("Step_CounterSupport", "readXmlIfSupportEX");
        boolean z = false;
        File file = null;
        try {
            file = HwCfgFilePolicy.b("xml/hw_motion_config.xml", 0);
            if (MagicBuild.f13130a && MagicBuild.d >= 33) {
                file = HwCfgFilePolicy.b("xml/hn_motion_config.xml", 0);
            }
            if (file != null && file.exists()) {
                z = d(file);
            } else {
                LogUtil.c("Step_CounterSupport", "HwCfgFilePolicy file not exists.");
            }
        } catch (NoClassDefFoundError unused) {
            LogUtil.e("Step_CounterSupport", "class HwCfgFilePolicy not found error");
        }
        if (file == null || !file.exists()) {
            if (MagicBuild.f13130a && MagicBuild.d >= 33) {
                c = "/system/etc/xml/hn_motion_config.xml";
            }
            File file2 = new File(c);
            if (file2.exists()) {
                z = d(file2);
            } else {
                LogUtil.c("HwXmlParse", "sysFile not exists.");
            }
            if (MagicBuild.f13130a && MagicBuild.d >= 33) {
                d = "/data/cust/xml/hn_motion_config.xml";
            }
            File file3 = new File(d);
            if (file3.exists()) {
                z = d(file3);
            } else {
                LogUtil.c("HwXmlParse", "sysFile not exists.");
            }
        }
        LogUtil.c("Step_CounterSupport", "readXmlIfSupportEX result = ", Boolean.valueOf(z));
        return z;
    }

    private static boolean d(File file) {
        if (file == null || !file.exists()) {
            LogUtil.c("Step_CounterSupport", "configFile is null or not exists.");
            return false;
        }
        try {
            Element documentElement = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(file).getDocumentElement();
            if (documentElement == null) {
                LogUtil.c("Step_CounterSupport", "root == null");
                return false;
            }
            NodeList childNodes = documentElement.getChildNodes();
            if (childNodes == null) {
                LogUtil.c("Step_CounterSupport", "motions == null");
                return false;
            }
            return b(childNodes);
        } catch (FileNotFoundException unused) {
            LogUtil.e("Step_CounterSupport", "isParseMyXmlSuccess fileNotFoundException");
            return false;
        } catch (IOException e) {
            LogUtil.e("Step_CounterSupport", "isParseMyXmlSuccess Exception", e.getMessage());
            return false;
        } catch (ParserConfigurationException e2) {
            LogUtil.e("Step_CounterSupport", "isParseMyXmlSuccess Exception", e2.getMessage());
            return false;
        } catch (SAXException e3) {
            LogUtil.e("Step_CounterSupport", "isParseMyXmlSuccess sAXException", e3.getMessage());
            return false;
        }
    }

    private static boolean b(NodeList nodeList) throws SAXException, IOException, ParserConfigurationException {
        boolean z = false;
        for (int i = 0; i < nodeList.getLength(); i++) {
            try {
                Node item = nodeList.item(i);
                if (item != null && item.getNodeType() == 1) {
                    String nodeValue = item.getAttributes().getNamedItem("name").getNodeValue();
                    int parseInt = Integer.parseInt(item.getAttributes().getNamedItem(k.g).getNodeValue());
                    if ((MotionTypeApps.KEY_HW_STEP_COUNTER.equals(nodeValue) || "motion_hn_step_counter".equals(nodeValue)) && parseInt == 1) {
                        z = true;
                    }
                    if (e(item.getChildNodes(), z)) {
                        return true;
                    }
                }
            } catch (NumberFormatException e) {
                LogUtil.a("Step_CounterSupport", "pareData numberFormatException = ", e.getMessage());
            }
        }
        return false;
    }

    private static boolean e(NodeList nodeList, boolean z) {
        if (nodeList == null) {
            return false;
        }
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node item = nodeList.item(i);
            if (item != null && item.getNodeType() == 1) {
                String nodeValue = item.getAttributes().getNamedItem("name").getNodeValue();
                try {
                    boolean z2 = Integer.parseInt(item.getAttributes().getNamedItem(k.g).getNodeValue()) == 1 && z;
                    if ((MotionTypeApps.KEY_HW_STEP_COUNTER_HEALTH.equals(nodeValue) || "motion_hn_step_counter_health".equals(nodeValue)) && z2) {
                        LogUtil.c("Step_CounterSupport", "support extend step counter!,return true");
                        return true;
                    }
                } catch (NumberFormatException e) {
                    LogUtil.e("Step_CounterSupport", "NumberFormatException = ", LogAnonymous.b((Throwable) e));
                }
            }
        }
        return false;
    }

    public static boolean f(Context context) {
        if (context != null) {
            return d(context) == 1;
        }
        LogUtil.e("Step_CounterSupport", "isSupportExtendStepCounter context is null");
        return false;
    }

    public static boolean h(Context context) {
        if (context == null) {
            ReleaseLogUtil.a("Step_CounterSupport", "isSupportStandStepCounter context is null ");
            return false;
        }
        if (!e(context)) {
            ReleaseLogUtil.a("Step_CounterSupport", "isSupportStandStepCounter authorization not admitted");
            return true;
        }
        if (!(context.getSystemService("sensor") instanceof SensorManager)) {
            ReleaseLogUtil.a("Step_CounterSupport", "isSupportStandStepCounter object is not instanceof SensorManager");
            return false;
        }
        SensorManager sensorManager = (SensorManager) context.getSystemService("sensor");
        if (sensorManager != null && sensorManager.getDefaultSensor(19) != null) {
            return true;
        }
        ReleaseLogUtil.b("isSupportStandStepCounter", "isSupportStandStepCounter false");
        return false;
    }

    public static boolean g(Context context) {
        if (context != null) {
            return d(context) == 1 && aaC_(context) != null;
        }
        LogUtil.e("Step_CounterSupport", "isSupportMidWareStepCounter context is null");
        return false;
    }

    public static Intent aaC_(Context context) {
        if (context == null) {
            LogUtil.e("Step_CounterSupport", "getMidWareStepCountService context is null");
            return null;
        }
        PackageManager packageManager = context.getPackageManager();
        Intent intent = new Intent("com.huawei.ihealth.action.StepCountService");
        if ("HONOR".equalsIgnoreCase(Build.BRAND) && MagicBuild.f13130a && MagicBuild.d >= 33) {
            intent = new Intent("com.hihonor.ihealth.action.StepCountService");
        }
        List<ResolveInfo> queryIntentServices = packageManager.queryIntentServices(intent, 0);
        if (queryIntentServices == null || queryIntentServices.size() == 0) {
            LogUtil.e("Step_CounterSupport", "getMidWareStepCountService: listInfo is empty");
            return null;
        }
        ApplicationInfo applicationInfo = null;
        for (ResolveInfo resolveInfo : queryIntentServices) {
            if (resolveInfo != null) {
                LogUtil.c("Step_CounterSupport", "getMidWareStepCountService: pkgName = ", resolveInfo.serviceInfo.packageName, " service = ", resolveInfo.serviceInfo.name);
                try {
                    applicationInfo = packageManager.getApplicationInfo(resolveInfo.serviceInfo.packageName, 0);
                } catch (PackageManager.NameNotFoundException e) {
                    LogUtil.e("Step_CounterSupport", e.getMessage());
                }
                if (applicationInfo == null) {
                    LogUtil.e("Step_CounterSupport", "getMidWareStepCountService application is null.");
                } else {
                    boolean z = (applicationInfo.flags & 1) != 0;
                    LogUtil.c("Step_CounterSupport", "getMidWareStepCountService: isSystemApp = ", Boolean.valueOf(z));
                    if (!z) {
                        LogUtil.e("Step_CounterSupport", "getMidWareStepCountService: not system app");
                    } else {
                        intent.setComponent(new ComponentName(resolveInfo.serviceInfo.packageName, resolveInfo.serviceInfo.name));
                        return intent;
                    }
                }
            }
        }
        return null;
    }

    public static boolean c(Context context) {
        return !CommonUtil.bv() || StepCounterSupportUtils.e(context, CommonUtil.bv() ? "remove_reduce_class_config" : "remove_reduce_class_config_beta");
    }

    public static boolean e(Context context) {
        if (!StepCounterSupportUtils.c(context) || gnr.b(context)) {
            return true;
        }
        return AuthorizationUtils.a(context);
    }
}
