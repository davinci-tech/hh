package defpackage;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.hihealth.HiDataFilter;
import com.huawei.hms.network.inner.api.NetworkService;
import com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.constant.WatchFaceConstant;
import com.huawei.hwdevice.phoneprocess.mgr.exercise.utils.seuqneceutils.SequenceDetailFieldConfig;
import com.huawei.pluginachievement.manager.model.MedalConstants;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

/* loaded from: classes5.dex */
public class lnf {
    public static lne b(Context context, String str) {
        return c(context, str, -1);
    }

    public static lne c(Context context, String str, int i) {
        if (context == null) {
            loq.c("CarrierConfigResolveXml", "Get carrier config info from XML failed, context is null");
            return null;
        }
        if (str == null) {
            str = lop.b(context, i);
        }
        if (loq.b.booleanValue()) {
            loq.c("CarrierConfigResolveXml", "CarrierConfigInfo resolveXML simoperator:" + str);
        }
        if (!TextUtils.isEmpty(str)) {
            try {
                XmlPullParser newPullParser = XmlPullParserFactory.newInstance().newPullParser();
                StringBuffer append = new StringBuffer("carrier_config_").append(str).append(WatchFaceConstant.XML_SUFFIX);
                if (newPullParser != null) {
                    newPullParser.setInput(context.getAssets().open(append.toString()), "utf-8");
                }
                return d(newPullParser);
            } catch (IOException | XmlPullParserException unused) {
                loq.c("CarrierConfigResolveXml", "Get carrier config info from XML failed, an exception occured, maybe the card is not support multi-sim");
                return null;
            }
        }
        loq.b("CarrierConfigResolveXml", "SimOperator is empty , card is missing or the context is null...");
        return null;
    }

    private static lne d(XmlPullParser xmlPullParser) throws IOException, XmlPullParserException {
        if (xmlPullParser != null) {
            lne lneVar = new lne();
            int eventType = xmlPullParser.getEventType();
            while (eventType != 1) {
                if (eventType == 2 && !"carrier_config".equals(xmlPullParser.getName())) {
                    if ("BindDevicesAndCardState".equals(xmlPullParser.getName())) {
                        lneVar.c(xmlPullParser.nextText());
                    } else if ("SignTimes".equals(xmlPullParser.getName())) {
                        lneVar.i(xmlPullParser.nextText());
                    } else if ("CDMURL".equals(xmlPullParser.getName())) {
                        lneVar.a(xmlPullParser.nextText());
                    } else if ("ESURL".equals(xmlPullParser.getName())) {
                        lneVar.h(xmlPullParser.nextText());
                    } else if ("BSFURL".equals(xmlPullParser.getName())) {
                        lneVar.b(xmlPullParser.nextText());
                    } else if ("DeleteProfile".equals(xmlPullParser.getName())) {
                        lneVar.e(xmlPullParser.nextText());
                    } else if ("AuthenType".equals(xmlPullParser.getName())) {
                        lneVar.d(xmlPullParser.nextText());
                    } else if ("SMDPURL".equals(xmlPullParser.getName())) {
                        lneVar.j(xmlPullParser.nextText());
                    } else if ("SupportedProtocol".equals(xmlPullParser.getName())) {
                        lneVar.f(xmlPullParser.nextText());
                    } else if ("NeedImsi".equals(xmlPullParser.getName())) {
                        lneVar.g(xmlPullParser.nextText());
                    }
                }
                eventType = xmlPullParser.next();
            }
            return lneVar;
        }
        loq.b("CarrierConfigResolveXml", "parser or config message is null");
        return null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r6v4, types: [java.util.Map] */
    public static Map a(Context context, String str) {
        ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap();
        if (context == null) {
            loq.c("CarrierConfigResolveXml", "Get carrier config HashMap from XML failed, context is null");
            return concurrentHashMap;
        }
        if (loq.b.booleanValue()) {
            loq.c("CarrierConfigResolveXml", "resolveXMLToHashMap simOperator:" + str);
        }
        if (!TextUtils.isEmpty(str)) {
            InputStream inputStream = null;
            try {
                try {
                    XmlPullParser newPullParser = XmlPullParserFactory.newInstance().newPullParser();
                    inputStream = context.getAssets().open("hwcarrierconfig_" + str + WatchFaceConstant.XML_SUFFIX);
                    if (newPullParser != null) {
                        newPullParser.setInput(inputStream, "utf-8");
                    }
                    concurrentHashMap = b(newPullParser);
                } catch (IOException | XmlPullParserException unused) {
                    loq.b("CarrierConfigResolveXml", "Get carrier config HashMap from XML failed");
                }
            } finally {
                b(inputStream);
            }
        }
        return concurrentHashMap;
    }

    private static void b(InputStream inputStream) {
        if (inputStream != null) {
            try {
                inputStream.close();
            } catch (IOException unused) {
                loq.b("CarrierConfigResolveXml", "IOException in closing inputStream");
            }
        }
    }

    private static Map b(XmlPullParser xmlPullParser) {
        char c;
        ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap();
        if (xmlPullParser == null) {
            return concurrentHashMap;
        }
        try {
            int next = xmlPullParser.next();
            while (next != 1) {
                if (next != 2) {
                    next = xmlPullParser.next();
                } else if (NetworkService.Constants.CONFIG_SERVICE.equalsIgnoreCase(xmlPullParser.getName())) {
                    next = xmlPullParser.next();
                } else if ("dict".equalsIgnoreCase(xmlPullParser.getName())) {
                    next = xmlPullParser.next();
                } else {
                    String name = xmlPullParser.getName();
                    switch (name.hashCode()) {
                        case -1325958191:
                            if (name.equals(SequenceDetailFieldConfig.DOUBLE)) {
                                c = 3;
                                break;
                            }
                            c = 65535;
                            break;
                        case -891985903:
                            if (name.equals("string")) {
                                c = 0;
                                break;
                            }
                            c = 65535;
                            break;
                        case 104431:
                            if (name.equals("int")) {
                                c = 1;
                                break;
                            }
                            c = 65535;
                            break;
                        case 3029738:
                            if (name.equals("bool")) {
                                c = 2;
                                break;
                            }
                            c = 65535;
                            break;
                        default:
                            c = 65535;
                            break;
                    }
                    if (c == 0) {
                        concurrentHashMap.put(xmlPullParser.getAttributeValue(null, MedalConstants.EVENT_KEY), xmlPullParser.nextText());
                    } else if (c == 1) {
                        concurrentHashMap.put(xmlPullParser.getAttributeValue(null, MedalConstants.EVENT_KEY), Integer.valueOf(Integer.parseInt(xmlPullParser.nextText().trim())));
                    } else if (c == 2) {
                        concurrentHashMap.put(xmlPullParser.getAttributeValue(null, MedalConstants.EVENT_KEY), Boolean.valueOf(xmlPullParser.nextText().trim()));
                    } else if (c == 3) {
                        concurrentHashMap.put(xmlPullParser.getAttributeValue(null, MedalConstants.EVENT_KEY), Double.valueOf(xmlPullParser.nextText().trim()));
                    } else {
                        loq.b("CarrierConfigResolveXml", "No unknown type for parse <" + xmlPullParser.getName() + HiDataFilter.DataFilterExpression.BIGGER_THAN);
                    }
                    next = xmlPullParser.next();
                }
            }
        } catch (Exception unused) {
            loq.b("CarrierConfigResolveXml", "Get carrier config info from XML failed, an exception occured");
        }
        return concurrentHashMap;
    }
}
