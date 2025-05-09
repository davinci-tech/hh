package defpackage;

import android.content.Context;
import android.text.TextUtils;
import android.util.SparseArray;
import com.huawei.haf.application.BaseApplication;
import com.huawei.health.healthmodel.bean.PlaceholderBean;
import com.huawei.health.healthmodel.bean.TextBean;
import com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.constant.WatchFaceConstant;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.pluginachievement.manager.model.ParsedFieldTag;
import com.huawei.watchface.videoedit.gles.Constant;
import health.compact.a.CommonUtil;
import health.compact.a.HiDateUtil;
import health.compact.a.IoUtils;
import health.compact.a.LanguageUtil;
import health.compact.a.LogAnonymous;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.MissingFormatArgumentException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/* loaded from: classes.dex */
public class bcc {
    private static final String[] e = {"health_model_share.json", "health_model_smile.json", "health_model_benefits.json", "health_model_task_tips.json", "health_model_suggestions.json", "health_model_task_finish.json", "health_model_reticent_user_wake_up.json"};

    private static boolean d(String str) {
        if (TextUtils.isEmpty(str)) {
            return true;
        }
        int i = Calendar.getInstance().get(7);
        switch (i) {
            case 1:
            case 7:
                return "weekend".equals(str);
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
                return "workday".equals(str);
            default:
                LogUtil.h("HealthLife_SemanticUtils", "checkDateType default dayOfWeek ", Integer.valueOf(i));
                return false;
        }
    }

    private static boolean b(ArrayList<String> arrayList) {
        if (koq.b(arrayList)) {
            return true;
        }
        Date date = new Date();
        String e2 = HiDateUtil.e(date, "yyyyMMdd HH:mm");
        if (TextUtils.isEmpty(e2)) {
            LogUtil.h("HealthLife_SemanticUtils", "checkTimeList formatDate ", e2);
            return true;
        }
        String[] split = e2.split(" ");
        if (split.length < 2) {
            LogUtil.h("HealthLife_SemanticUtils", "checkTimeList splitArray ", split);
            return true;
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd HH:mm", Locale.ENGLISH);
        String str = split[0];
        for (int i = 0; i < arrayList.size(); i += 2) {
            try {
                Date parse = simpleDateFormat.parse(str + " " + arrayList.get(i));
                Date parse2 = simpleDateFormat.parse(str + " " + arrayList.get(i + 1));
                if (parse != null && parse2 != null && date.getTime() - parse.getTime() >= 0 && parse2.getTime() - date.getTime() >= 0) {
                    return true;
                }
            } catch (ParseException e3) {
                LogUtil.b("HealthLife_SemanticUtils", "checkTimeList exception ", LogAnonymous.b((Throwable) e3));
            }
        }
        return false;
    }

    private static boolean c(auc aucVar) {
        return (aucVar != null && d(aucVar.c()) && b(aucVar.f())) ? false : true;
    }

    private static void c(auc aucVar, auc aucVar2) {
        aucVar2.b(aucVar.i());
        aucVar2.d(aucVar.e());
        aucVar2.b(aucVar.d());
        aucVar2.a(aucVar.b());
        aucVar2.d(aucVar.c());
        aucVar2.b(aucVar.f());
        aucVar2.a(aucVar.h());
        aucVar2.d(aucVar.a());
    }

    private static auc c(String str, int i) {
        ArrayList<auc> a2 = a(bad.b().c(str));
        if (koq.b(a2)) {
            LogUtil.h("HealthLife_SemanticUtils", "getInteractiveSemanticBean interactiveSemanticBeanList is empty type ", Integer.valueOf(i), " fileName ", str);
            return new auc();
        }
        auc aucVar = new auc();
        Iterator<auc> it = a2.iterator();
        while (it.hasNext()) {
            auc next = it.next();
            if (!c(next) && next.i() == i) {
                c(next, aucVar);
            }
        }
        return aucVar;
    }

    private static auc b(String str, String str2) {
        ArrayList<auc> a2 = a(bad.b().c(str));
        if (koq.b(a2)) {
            LogUtil.h("HealthLife_SemanticUtils", "getInteractiveSemanticBean interactiveSemanticBeanList is empty scenario ", str2, " fileName ", str);
            return new auc();
        }
        boolean isEmpty = TextUtils.isEmpty(str2);
        auc aucVar = new auc();
        Iterator<auc> it = a2.iterator();
        while (it.hasNext()) {
            auc next = it.next();
            if (!c(next)) {
                String b = next.b();
                if (!isEmpty || TextUtils.isEmpty(b)) {
                    if (isEmpty || str2.equals(b)) {
                        c(next, aucVar);
                    }
                }
            }
        }
        return aucVar;
    }

    private static auc c(String str, int i, int i2) {
        ArrayList<auc> a2 = a(bad.b().c(str));
        if (koq.b(a2)) {
            LogUtil.h("HealthLife_SemanticUtils", "getInteractiveSemanticBean interactiveSemanticBeanList is empty fileName ", str, " type ", Integer.valueOf(i), " challengeId ", Integer.valueOf(i2));
            return new auc();
        }
        auc aucVar = new auc();
        Iterator<auc> it = a2.iterator();
        while (it.hasNext()) {
            auc next = it.next();
            if (!c(next) && next.i() == i && next.e() == i2) {
                c(next, aucVar);
            }
        }
        return aucVar;
    }

    public static String[] b() {
        return (String[]) e.clone();
    }

    public static SparseArray<ArrayList<TextBean>> na_() {
        ArrayList<auc> a2 = a(bad.b().c("health_model_smile.json"));
        if (koq.b(a2)) {
            LogUtil.h("HealthLife_SemanticUtils", "getSmile interactiveSemanticBeanList is empty");
            return new SparseArray<>(16);
        }
        SparseArray<ArrayList<TextBean>> sparseArray = new SparseArray<>(16);
        Iterator<auc> it = a2.iterator();
        while (it.hasNext()) {
            auc next = it.next();
            if (next != null) {
                sparseArray.append(next.i(), next.a());
            }
        }
        return sparseArray;
    }

    public static auc e() {
        ArrayList<auc> a2 = a(bad.b().c("health_model_share.json"));
        if (koq.b(a2)) {
            LogUtil.h("HealthLife_SemanticUtils", "getShare interactiveSemanticBeanList is empty");
            return new auc();
        }
        auc aucVar = new auc();
        Iterator<auc> it = a2.iterator();
        while (it.hasNext()) {
            auc next = it.next();
            if (!c(next)) {
                c(next, aucVar);
            }
        }
        return aucVar;
    }

    public static auc c(int i) {
        return c("health_model_task_finish.json", i);
    }

    public static auc e(String str) {
        return b("health_model_reticent_user_wake_up.json", str);
    }

    public static auc d(String str, int i) {
        ArrayList<auc> a2 = a(bad.b().c("health_model_task_tips.json"));
        if (koq.b(a2)) {
            LogUtil.h("HealthLife_SemanticUtils", "getTaskTips interactiveSemanticBeanList is empty scenario ", str, " taskId ", Integer.valueOf(i));
            return new auc();
        }
        boolean isEmpty = TextUtils.isEmpty(str);
        auc aucVar = new auc();
        Iterator<auc> it = a2.iterator();
        while (it.hasNext()) {
            auc next = it.next();
            if (!c(next) && next.i() == i) {
                String b = next.b();
                if (!isEmpty || TextUtils.isEmpty(b)) {
                    if (isEmpty || str.equals(b)) {
                        c(next, aucVar);
                    }
                }
            }
        }
        return aucVar;
    }

    public static auc e(int i, int i2) {
        return c("health_model_benefits.json", i, i2);
    }

    public static auc a(int i, int i2) {
        return c("health_model_suggestions.json", i, i2);
    }

    private static ArrayList<auc> a(String str) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("HealthLife_SemanticUtils", "parsingJsonForInteractive json ", str);
            return new ArrayList<>(16);
        }
        try {
            JSONArray optJSONArray = new JSONObject(str).optJSONArray("list");
            if (optJSONArray == null) {
                LogUtil.h("HealthLife_SemanticUtils", "parsingJsonForInteractive jsonArray is null");
                return new ArrayList<>(16);
            }
            ArrayList<auc> arrayList = new ArrayList<>(16);
            for (int i = 0; i < optJSONArray.length(); i++) {
                JSONObject optJSONObject = optJSONArray.optJSONObject(i);
                if (optJSONObject != null) {
                    auc aucVar = new auc();
                    aucVar.b(optJSONObject.optInt("type"));
                    aucVar.d(optJSONObject.optInt("challengeId"));
                    aucVar.a(optJSONObject.optString(ParsedFieldTag.KAKA_TASK_SCENARIO));
                    aucVar.b(optJSONObject.optString("module"));
                    aucVar.d(optJSONObject.optString("dateType"));
                    aucVar.b(e(optJSONObject, "timeList"));
                    aucVar.a(e(optJSONObject, "variableList"));
                    aucVar.d(d(optJSONObject));
                    arrayList.add(aucVar);
                }
            }
            return arrayList;
        } catch (JSONException e2) {
            LogUtil.b("HealthLife_SemanticUtils", "parsingJsonForInteractive exception ", LogAnonymous.b((Throwable) e2));
            return new ArrayList<>(16);
        }
    }

    private static ArrayList<String> e(JSONObject jSONObject, String str) {
        JSONArray optJSONArray = jSONObject.optJSONArray(str);
        if (optJSONArray == null) {
            return new ArrayList<>(16);
        }
        ArrayList<String> arrayList = new ArrayList<>(16);
        for (int i = 0; i < optJSONArray.length(); i++) {
            arrayList.add(optJSONArray.optString(i));
        }
        return arrayList;
    }

    public static ArrayList<TextBean> d(JSONObject jSONObject) {
        if (jSONObject == null) {
            LogUtil.h("HealthLife_SemanticUtils", "parsingTextList jsonObject is empty");
            return new ArrayList<>(16);
        }
        JSONArray optJSONArray = jSONObject.optJSONArray("textList");
        if (optJSONArray == null) {
            return new ArrayList<>(16);
        }
        ArrayList<TextBean> arrayList = new ArrayList<>(16);
        for (int i = 0; i < optJSONArray.length(); i++) {
            JSONObject optJSONObject = optJSONArray.optJSONObject(i);
            if (optJSONObject != null) {
                TextBean textBean = new TextBean();
                textBean.setText(optJSONObject.optString(Constant.TEXT));
                textBean.setPlaceholderList(a(optJSONObject));
                arrayList.add(textBean);
            }
        }
        return arrayList;
    }

    private static ArrayList<PlaceholderBean> a(JSONObject jSONObject) {
        if (jSONObject == null) {
            LogUtil.h("HealthLife_SemanticUtils", "parsingPlaceholder jsonObject is null");
            return new ArrayList<>(16);
        }
        JSONArray optJSONArray = jSONObject.optJSONArray("placeholderList");
        if (optJSONArray == null) {
            return new ArrayList<>(16);
        }
        ArrayList<PlaceholderBean> arrayList = new ArrayList<>(16);
        for (int i = 0; i < optJSONArray.length(); i++) {
            JSONObject optJSONObject = optJSONArray.optJSONObject(i);
            if (optJSONObject != null) {
                PlaceholderBean placeholderBean = new PlaceholderBean();
                placeholderBean.setType(optJSONObject.optString("type"));
                placeholderBean.setValue(optJSONObject.optString("value"));
                placeholderBean.setResourcesId(optJSONObject.optString("resourcesId"));
                arrayList.add(placeholderBean);
            }
        }
        return arrayList;
    }

    private static String e(String str, NodeList nodeList) {
        NamedNodeMap attributes;
        Node namedItem;
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node item = nodeList.item(i);
            if (item != null && item.getNodeType() == 1 && (attributes = item.getAttributes()) != null && (namedItem = attributes.getNamedItem("name")) != null && str.equals(namedItem.getNodeValue())) {
                String textContent = item.getTextContent();
                if (!TextUtils.isEmpty(textContent)) {
                    return textContent;
                }
            }
        }
        return "";
    }

    private static String b(String str) {
        String str2;
        Context e2 = BaseApplication.e();
        Locale locale = Locale.getDefault();
        String language = locale.getLanguage();
        String country = locale.getCountry();
        String str3 = str + "strings/strings";
        if (LanguageUtil.j(e2)) {
            String str4 = str3 + Constants.LINK + language + Constants.LINK;
            if (LanguageUtil.m(e2)) {
                str2 = str4 + "CN";
            } else if ("HK".equals(country)) {
                str2 = str4 + country;
            } else {
                str2 = str4 + "TW";
            }
            return str2 + WatchFaceConstant.XML_SUFFIX;
        }
        if (LanguageUtil.g(e2)) {
            return str3 + Constants.LINK + language + "-ZG.xml";
        }
        String c = CommonUtil.c(str3 + Constants.LINK + language + WatchFaceConstant.XML_SUFFIX);
        String c2 = CommonUtil.c(str3 + Constants.LINK + language + Constants.LINK + country + WatchFaceConstant.XML_SUFFIX);
        if (!TextUtils.isEmpty(c2) && new File(c2).exists()) {
            return c2;
        }
        if (!TextUtils.isEmpty(c) && new File(c).exists()) {
            return c;
        }
        return str3 + WatchFaceConstant.XML_SUFFIX;
    }

    private static String c(String str, String str2) {
        FileInputStream fileInputStream;
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("HealthLife_SemanticUtils", "getString name is empty");
            return "";
        }
        if (azi.h(str2)) {
            LogUtil.h("HealthLife_SemanticUtils", "getString path ", str2);
            return "";
        }
        DocumentBuilderFactory newInstance = DocumentBuilderFactory.newInstance();
        try {
            newInstance.setFeature("http://xml.org/sax/features/external-general-entities", false);
            newInstance.setFeature("http://xml.org/sax/features/external-parameter-entities", false);
            newInstance.setFeature("http://javax.xml.XMLConstants/feature/secure-processing", true);
            newInstance.setFeature("http://apache.org/xml/features/disallow-doctype-decl", true);
        } catch (ParserConfigurationException e2) {
            LogUtil.b("HealthLife_SemanticUtils", "getString exception ", LogAnonymous.b((Throwable) e2));
        }
        FileInputStream fileInputStream2 = null;
        try {
            try {
                DocumentBuilder newDocumentBuilder = newInstance.newDocumentBuilder();
                if (newDocumentBuilder == null) {
                    LogUtil.h("HealthLife_SemanticUtils", "getString documentBuilder is null");
                    IoUtils.e((Closeable) null);
                    return "";
                }
                fileInputStream = new FileInputStream(str2);
                try {
                    try {
                        Document parse = newDocumentBuilder.parse(fileInputStream);
                        if (parse == null) {
                            LogUtil.h("HealthLife_SemanticUtils", "getString document is null");
                            IoUtils.e(fileInputStream);
                            return "";
                        }
                        Element documentElement = parse.getDocumentElement();
                        if (documentElement == null) {
                            LogUtil.h("HealthLife_SemanticUtils", "getString element is null");
                            IoUtils.e(fileInputStream);
                            return "";
                        }
                        NodeList childNodes = documentElement.getChildNodes();
                        if (childNodes == null) {
                            LogUtil.h("HealthLife_SemanticUtils", "getString nodeList is null");
                            IoUtils.e(fileInputStream);
                            return "";
                        }
                        String e3 = e(str, childNodes);
                        IoUtils.e(fileInputStream);
                        return e3;
                    } catch (IOException | ParserConfigurationException | SAXException unused) {
                        fileInputStream2 = fileInputStream;
                        LogUtil.b("HealthLife_SemanticUtils", "getString exception");
                        IoUtils.e(fileInputStream2);
                        return "";
                    }
                } catch (Throwable th) {
                    th = th;
                    IoUtils.e(fileInputStream);
                    throw th;
                }
            } catch (IOException | ParserConfigurationException | SAXException unused2) {
            }
        } catch (Throwable th2) {
            th = th2;
            fileInputStream = null;
        }
    }

    public static ArrayList<String> d(ArrayList<TextBean> arrayList, HashMap<String, String> hashMap) {
        if (koq.b(arrayList)) {
            LogUtil.h("HealthLife_SemanticUtils", "getStringList textBeans is empty");
            return new ArrayList<>(16);
        }
        ArrayList<String> arrayList2 = new ArrayList<>(16);
        Iterator<TextBean> it = arrayList.iterator();
        while (it.hasNext()) {
            TextBean next = it.next();
            if (next != null) {
                String e2 = e(next.getText(), next.getPlaceholderList(), hashMap);
                if (!TextUtils.isEmpty(e2)) {
                    arrayList2.add(e2);
                }
            }
        }
        return arrayList2;
    }

    public static String e(String str, ArrayList<PlaceholderBean> arrayList, HashMap<String, String> hashMap) {
        String c = c(str, CommonUtil.c(b(bad.b().e())));
        if (TextUtils.isEmpty(c) || koq.b(arrayList)) {
            return c;
        }
        ArrayList arrayList2 = new ArrayList(16);
        Iterator<PlaceholderBean> it = arrayList.iterator();
        while (it.hasNext()) {
            PlaceholderBean next = it.next();
            if (next == null) {
                arrayList2.add("");
            } else {
                arrayList2.add(bbs.b(next.getType(), d(next, hashMap), next.getResourcesId()));
            }
        }
        try {
            return String.format(c, arrayList2.toArray(new Object[0]));
        } catch (MissingFormatArgumentException unused) {
            LogUtil.b("HealthLife_SemanticUtils", "getText missingFormatArgumentException");
            return "";
        }
    }

    private static String d(PlaceholderBean placeholderBean, HashMap<String, String> hashMap) {
        String value = placeholderBean.getValue();
        if (hashMap == null || hashMap.size() <= 0) {
            return value;
        }
        for (Map.Entry<String, String> entry : hashMap.entrySet()) {
            if (entry != null) {
                String key = entry.getKey();
                if (!TextUtils.isEmpty(key) && key.equals(value)) {
                    return entry.getValue();
                }
            }
        }
        return value;
    }
}
