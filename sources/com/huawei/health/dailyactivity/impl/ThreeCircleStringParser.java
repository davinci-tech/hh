package com.huawei.health.dailyactivity.impl;

import android.content.res.Resources;
import android.text.TextUtils;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.openalliance.ad.constant.TradeMode;
import defpackage.mtj;
import defpackage.njg;
import health.compact.a.CommonUtil;
import health.compact.a.LogAnonymous;
import health.compact.a.Utils;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.IllegalFormatPrecisionException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.eclipse.californium.elements.util.JceNames;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

/* loaded from: classes3.dex */
public class ThreeCircleStringParser {
    private static final String BRAZIL = "BR";
    private static final String DEFAULT_LANGUAGE = "en";
    private static final String ENCODING = "UTF-8";
    private static final String LANGUAGE_LOCAL_LINK = "-r";
    private static final String MYANMAR = "my";
    private static final String PARAM_KEY = "params";
    private static final String RELEASE_TAG = "R_3CircStrParser";
    private static final String RULE_KEY = "rules";
    private static final String SECURE_ALGORITHM = "SHA1PRNG";
    private static final String SPECIAL_CATEGORY = "Zero";
    private static final String SPECIAL_PARAM = "remainActiveDay";
    private static final String SPECIAL_PROMPT = "ActiveWeek";
    private static final String STRING_KEY = "str";
    private static final String STRING_NAME_KEY = "string";
    private static final String TAG = "ThreeCircleStringParser";
    private static final String UK = "GB";
    private static final String XML_SUFFIX = ".xml";
    private static final String ZH_HANS = "zh-Hans";
    private static final String ZH_HANT = "zh-Hant";
    private static final String ZH_LANGUAGE = "zh";
    private String mJsonPath;
    private String mStringDir;
    private static final Map<String, String> LOCALE_LANGUAGE_MAP = new LinkedHashMap<String, String>() { // from class: com.huawei.health.dailyactivity.impl.ThreeCircleStringParser.3
        {
            put("my-Qaag", "my-rZG");
            put("my-ZG", "my-rZG");
            put("zh-Hant-CN", "zh-rTW");
            put("zh-Hant-TW", "zh-rTW");
            put("zh-Hant-HK", "zh-rHK");
            put("zh-Hant-MO", "zh-rHK");
            put("zh-CN", "zh-rCN");
            put("zh-HK", "zh-rHK");
            put("zh-TW", "zh-rTW");
        }
    };
    private static final Map<String, List<String>> SPECIAL_LANGUAGE_TO_LOCALE = new LinkedHashMap<String, List<String>>() { // from class: com.huawei.health.dailyactivity.impl.ThreeCircleStringParser.2
        {
            put(ThreeCircleStringParser.SPANISH, new ArrayList(Arrays.asList("AR", "BO", "CL", "CO", "CR", "CU", "DO", JceNames.EC, "GT", "HN", "MX", "NI", TradeMode.PA, "PE", "PR", "PY", "SV", "UY", "VE", "US")));
        }
    };
    private static final String LATIN_AMERICAN = "es-rUS";
    private static final String SPANISH = "es";
    private static final String MYANMAR_U_CODE = "my-rMM";
    private static final String EU_PORTUGUESE = "pt-rPT";
    private static final String PORTUGUESE = "pt";
    private static final List<String> ALL_COUNTRY = new ArrayList(Arrays.asList("am", "ar", "az", "be", "bg", "bn", "bo", "bs", "ca", "cs", "da", "de", "el", "en-rGB", LATIN_AMERICAN, SPANISH, "et", "eu", "fa", "fi", "fr", "gl", "hi", "hr", "hu", "in", "it", "iw", "ja", "jv", "ka", "kk", "km", "ko", "lo", "lt", "lv", "mi", "mk", "mn", "ms", MYANMAR_U_CODE, "my-rZG", "nb", "ne", "nl", "pl", EU_PORTUGUESE, PORTUGUESE, "ro", "ru", "si", "sk", "sl", "sr", "sv", "sw", "th", "tl", "tr", "ug", "uk", Constants.URDU_LANG, "uz", "vi", "zh-rCN", "zh-rHK", "zh-rTW"));

    public ThreeCircleStringParser(String str, String str2) {
        this.mJsonPath = str;
        this.mStringDir = str2;
    }

    private JSONObject parseJson(String str) {
        JSONObject jSONObject = null;
        if (!isFileExist(str)) {
            ReleaseLogUtil.d(RELEASE_TAG, "parseJson getFilePath empty: ", str);
            return null;
        }
        String c = Utils.c(str);
        if (TextUtils.isEmpty(c)) {
            ReleaseLogUtil.d(RELEASE_TAG, "parseJson readJson empty");
            return null;
        }
        try {
            jSONObject = new JSONObject(c);
        } catch (JSONException e) {
            ReleaseLogUtil.c(RELEASE_TAG, "parseJson JSONException: ", ExceptionUtils.d(e));
        }
        LogUtil.c(TAG, "leave parseJson: ", jSONObject);
        return jSONObject;
    }

    public njg queryRules(String str, String str2) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            ReleaseLogUtil.d(RELEASE_TAG, "queryParams params empty");
            return null;
        }
        return parseJsonCreateBean(str, str2);
    }

    public String getPromptMessage(njg njgVar, Map<String, String> map, int i) {
        String valueOf;
        if (njgVar == null) {
            ReleaseLogUtil.c(RELEASE_TAG, "bean is null");
            return null;
        }
        String c = njgVar.c();
        String b = njgVar.b();
        if (TextUtils.isEmpty(c) || TextUtils.isEmpty(b)) {
            ReleaseLogUtil.d(RELEASE_TAG, "getPromptMessage promptType or category empty");
            return null;
        }
        Map<String, List<String>> d = njgVar.d();
        if (d == null || d.isEmpty()) {
            ReleaseLogUtil.d(RELEASE_TAG, "ruleSet null");
            return null;
        }
        Set<String> keySet = d.keySet();
        if (i < 0) {
            int randomIndex = getRandomIndex(keySet.size());
            ReleaseLogUtil.e(RELEASE_TAG, "getPromptMessage random randomIndex: ", Integer.valueOf(randomIndex));
            valueOf = String.valueOf(keySet.toArray()[randomIndex]);
        } else {
            ReleaseLogUtil.e(RELEASE_TAG, "getPromptMessage in order");
            if (i >= keySet.size()) {
                ReleaseLogUtil.e(RELEASE_TAG, "index out of bound");
                return null;
            }
            valueOf = String.valueOf(keySet.toArray()[i]);
        }
        Map<String, String> loadXmlStrings = loadXmlStrings(this.mStringDir);
        String str = loadXmlStrings.get(valueOf);
        List<String> list = d.get(valueOf);
        if (SPECIAL_PROMPT.equals(c) && SPECIAL_CATEGORY.equals(b)) {
            String str2 = map.get(SPECIAL_PARAM);
            if (str2 == null) {
                ReleaseLogUtil.c(TAG, "ActiveWeek param is null");
                return null;
            }
            valueOf = valueOf + "_" + str2;
            str = loadXmlStrings.get(valueOf);
        }
        LogUtil.a(TAG, "strName: ", valueOf, ",chosenStr: ", str, ",eachParams: ", list);
        if (list == null || list.isEmpty() || TextUtils.isEmpty(str)) {
            ReleaseLogUtil.d(RELEASE_TAG, "params null or chosenStr null");
            return str;
        }
        if (map == null || map.isEmpty()) {
            ReleaseLogUtil.d(RELEASE_TAG, "get bean params null");
            return null;
        }
        String[] strArr = new String[list.size()];
        int i2 = 0;
        for (String str3 : list) {
            strArr[i2] = map.get(str3);
            LogUtil.a(TAG, "param: ", str3, " index: ", Integer.valueOf(i2), " sortedParams: ", strArr[i2]);
            i2++;
        }
        try {
            return String.format(str, strArr);
        } catch (IllegalFormatPrecisionException e) {
            ReleaseLogUtil.c(TAG, "getPromptMessage exception:", LogAnonymous.b((Throwable) e));
            return str;
        }
    }

    public String getPromptMessage(njg njgVar, Map<String, String> map) {
        return getPromptMessage(njgVar, map, -1);
    }

    private njg parseJsonCreateBean(String str, String str2) {
        JSONObject parseJson = parseJson(this.mJsonPath);
        if (parseJson == null) {
            ReleaseLogUtil.d(RELEASE_TAG, "ruleJson null");
            return null;
        }
        try {
            JSONObject jSONObject = parseJson.getJSONObject(str).getJSONObject(str2);
            JSONArray jSONArray = jSONObject.getJSONArray(RULE_KEY);
            if (jSONArray.length() == 0) {
                ReleaseLogUtil.d(RELEASE_TAG, "rules is empty");
                return null;
            }
            Map<String, List<String>> parseRules = parseRules(jSONArray);
            List<String> jsonArrayToList = jsonArrayToList(jSONObject.getJSONArray("params"));
            njg njgVar = new njg();
            njgVar.e(str);
            njgVar.b(str2);
            njgVar.d(parseRules);
            njgVar.d(jsonArrayToList);
            LogUtil.a(TAG, "ThreeCircleRulesBean: ", njgVar);
            return njgVar;
        } catch (JSONException e) {
            ReleaseLogUtil.c(RELEASE_TAG, "queryParams JSONException: ", ExceptionUtils.d(e));
            return null;
        }
    }

    private Map<String, List<String>> parseRules(JSONArray jSONArray) {
        int length = jSONArray.length();
        HashMap hashMap = new HashMap(length);
        for (int i = 0; i < length; i++) {
            try {
                JSONObject jSONObject = (JSONObject) jSONArray.get(i);
                hashMap.put(jSONObject.getString(STRING_KEY), jsonArrayToList(jSONObject.getJSONArray("params")));
            } catch (JSONException e) {
                ReleaseLogUtil.c(RELEASE_TAG, "get string JSONException: ", ExceptionUtils.d(e));
                return null;
            }
        }
        return hashMap;
    }

    private List<String> jsonArrayToList(JSONArray jSONArray) {
        int length = jSONArray.length();
        ArrayList arrayList = new ArrayList(length);
        for (int i = 0; i < length; i++) {
            try {
                arrayList.add(jSONArray.getString(i));
            } catch (JSONException e) {
                ReleaseLogUtil.c(RELEASE_TAG, "jsonArrayToList JSONException: ", ExceptionUtils.d(e));
                return null;
            }
        }
        return arrayList;
    }

    private int getRandomIndex(int i) {
        SecureRandom secureRandom;
        try {
            secureRandom = SecureRandom.getInstance(SECURE_ALGORITHM);
        } catch (NoSuchAlgorithmException unused) {
            secureRandom = new SecureRandom();
            ReleaseLogUtil.c(RELEASE_TAG, "NoSuchAlgorithmException");
        }
        if (i > 0) {
            return secureRandom.nextInt(i);
        }
        return 0;
    }

    private boolean isFileExist(String str) {
        if (TextUtils.isEmpty(str)) {
            ReleaseLogUtil.d(RELEASE_TAG, "isFileExist filePath null");
            return false;
        }
        File file = new File(str);
        return file.exists() && !file.isDirectory();
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r6v11 */
    /* JADX WARN: Type inference failed for: r6v12 */
    /* JADX WARN: Type inference failed for: r6v13, types: [java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r6v14, types: [java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r6v18 */
    /* JADX WARN: Type inference failed for: r6v19 */
    private Map<String, String> loadXmlStrings(String str) {
        XmlPullParser newPullParser;
        FileInputStream fileInputStream;
        HashMap hashMap = new HashMap();
        String language = getLanguage();
        String str2 = str + File.separator + language + ".xml";
        if (!isFileExist(str2)) {
            ReleaseLogUtil.e(RELEASE_TAG, "first xml file not exists: ", str2);
            if (ALL_COUNTRY.contains(language)) {
                ReleaseLogUtil.d(RELEASE_TAG, "language valid, but file not exist!");
                return hashMap;
            }
            str2 = str + File.separator + "en.xml";
            if (!isFileExist(str2)) {
                ReleaseLogUtil.c(RELEASE_TAG, "default xml file not exists");
                return hashMap;
            }
        }
        FileInputStream fileInputStream2 = null;
        ?? r6 = 0;
        FileInputStream fileInputStream3 = null;
        FileInputStream fileInputStream4 = null;
        FileInputStream fileInputStream5 = null;
        try {
            try {
                newPullParser = XmlPullParserFactory.newInstance().newPullParser();
                fileInputStream = new FileInputStream(str2);
            } catch (Throwable th) {
                th = th;
            }
        } catch (FileNotFoundException e) {
            e = e;
        } catch (IOException e2) {
            e = e2;
        } catch (XmlPullParserException e3) {
            e = e3;
        }
        try {
            newPullParser.setInput(fileInputStream, "UTF-8");
            int eventType = newPullParser.getEventType();
            while (eventType != 1) {
                if (eventType == 2) {
                    r6 = newPullParser.getName();
                    if ("string".equals(r6)) {
                        String attributeValue = newPullParser.getAttributeValue(0);
                        r6 = newPullParser.nextText();
                        hashMap.put(attributeValue, r6);
                    }
                }
                eventType = newPullParser.next();
                r6 = r6;
            }
            try {
                fileInputStream.close();
                fileInputStream2 = r6;
            } catch (IOException e4) {
                ReleaseLogUtil.c(RELEASE_TAG, "InputStream close IOException: ", ExceptionUtils.d(e4));
                fileInputStream2 = r6;
            }
        } catch (FileNotFoundException e5) {
            e = e5;
            fileInputStream3 = fileInputStream;
            ReleaseLogUtil.c(RELEASE_TAG, "FileNotFoundException: ", ExceptionUtils.d(e));
            fileInputStream2 = fileInputStream3;
            if (fileInputStream3 != null) {
                try {
                    fileInputStream3.close();
                    fileInputStream2 = fileInputStream3;
                } catch (IOException e6) {
                    ReleaseLogUtil.c(RELEASE_TAG, "InputStream close IOException: ", ExceptionUtils.d(e6));
                    fileInputStream2 = fileInputStream3;
                }
            }
            return hashMap;
        } catch (IOException e7) {
            e = e7;
            fileInputStream4 = fileInputStream;
            ReleaseLogUtil.c(RELEASE_TAG, "IOException: ", ExceptionUtils.d(e));
            fileInputStream2 = fileInputStream4;
            if (fileInputStream4 != null) {
                try {
                    fileInputStream4.close();
                    fileInputStream2 = fileInputStream4;
                } catch (IOException e8) {
                    ReleaseLogUtil.c(RELEASE_TAG, "InputStream close IOException: ", ExceptionUtils.d(e8));
                    fileInputStream2 = fileInputStream4;
                }
            }
            return hashMap;
        } catch (XmlPullParserException e9) {
            e = e9;
            fileInputStream5 = fileInputStream;
            ReleaseLogUtil.c(RELEASE_TAG, "XmlPullParserException: ", ExceptionUtils.d(e));
            fileInputStream2 = fileInputStream5;
            if (fileInputStream5 != null) {
                try {
                    fileInputStream5.close();
                    fileInputStream2 = fileInputStream5;
                } catch (IOException e10) {
                    ReleaseLogUtil.c(RELEASE_TAG, "InputStream close IOException: ", ExceptionUtils.d(e10));
                    fileInputStream2 = fileInputStream5;
                }
            }
            return hashMap;
        } catch (Throwable th2) {
            th = th2;
            fileInputStream2 = fileInputStream;
            if (fileInputStream2 != null) {
                try {
                    fileInputStream2.close();
                } catch (IOException e11) {
                    ReleaseLogUtil.c(RELEASE_TAG, "InputStream close IOException: ", ExceptionUtils.d(e11));
                }
            }
            throw th;
        }
        return hashMap;
    }

    private String getLanguage() {
        char c;
        String x = CommonUtil.x();
        String c2 = CommonUtil.c();
        String languageTag = Resources.getSystem().getConfiguration().locale.toLanguageTag();
        ReleaseLogUtil.e(RELEASE_TAG, "language: ", x, ", country: ", c2, ", tag: ", languageTag);
        x.hashCode();
        int hashCode = x.hashCode();
        if (hashCode == 3241) {
            if (x.equals("en")) {
                c = 0;
            }
            c = 65535;
        } else if (hashCode == 3246) {
            if (x.equals(SPANISH)) {
                c = 1;
            }
            c = 65535;
        } else if (hashCode == 3500) {
            if (x.equals(MYANMAR)) {
                c = 2;
            }
            c = 65535;
        } else if (hashCode != 3588) {
            if (hashCode == 3886 && x.equals("zh")) {
                c = 4;
            }
            c = 65535;
        } else {
            if (x.equals(PORTUGUESE)) {
                c = 3;
            }
            c = 65535;
        }
        if (c != 0) {
            if (c == 1) {
                x = SPECIAL_LANGUAGE_TO_LOCALE.get(SPANISH).contains(c2) ? LATIN_AMERICAN : SPANISH;
            } else if (c == 2) {
                x = getMyanmarLanguage(languageTag);
            } else if (c == 3) {
                x = BRAZIL.equals(c2) ? PORTUGUESE : EU_PORTUGUESE;
            } else if (c == 4) {
                x = getCNLanguage();
                LogUtil.a(TAG, "resLanguage: ", x);
            } else {
                ReleaseLogUtil.e(RELEASE_TAG, "get language directly");
            }
        } else if (UK.equals(c2)) {
            x = x + LANGUAGE_LOCAL_LINK + c2;
        } else {
            x = "en";
        }
        LogUtil.a(TAG, "localeLanguage: ", x);
        return x;
    }

    private String getCNLanguage() {
        String e = mtj.e(null);
        LogUtil.a(TAG, "supportLanguageName: ", e);
        if (TextUtils.isEmpty(e)) {
            LogUtil.h(TAG, "supportLanguageName is null, leave getCNLanguage");
            return "zh-rCN";
        }
        return LOCALE_LANGUAGE_MAP.get(e);
    }

    private String getMyanmarLanguage(String str) {
        if (str.length() < 4) {
            LogUtil.h(TAG, "languageTag size too short");
            return MYANMAR_U_CODE;
        }
        int indexOf = str.indexOf(45, 3);
        LogUtil.a(TAG, "languageTag: ", str, ", index: ", Integer.valueOf(indexOf));
        if (indexOf <= 0) {
            ReleaseLogUtil.d(RELEASE_TAG, "toLanguageTag indexOf error: ", Integer.valueOf(indexOf));
            return MYANMAR_U_CODE;
        }
        String str2 = LOCALE_LANGUAGE_MAP.get(str.substring(0, indexOf));
        return str2 == null ? MYANMAR_U_CODE : str2;
    }
}
