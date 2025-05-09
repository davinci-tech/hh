package defpackage;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.constant.WatchFaceConstant;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.main.R$string;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

/* loaded from: classes7.dex */
public class qro {
    public static String a(int i, Context context) {
        Context applicationContext = context.getApplicationContext();
        Double valueOf = Double.valueOf(10.0d);
        Double valueOf2 = Double.valueOf(4.4d);
        switch (i) {
            case 2008:
                return applicationContext.getString(R$string.IDS_hw_show_healthdata_bloodsugar_limosis_range, valueOf2, Double.valueOf(7.0d));
            case 2009:
                return applicationContext.getString(R$string.IDS_hw_show_healthdata_bloodsugar_after_breakfast_range, valueOf2, valueOf);
            case 2010:
                return applicationContext.getString(R$string.IDS_hw_show_healthdata_bloodsugar_before_lunch_range, valueOf2, valueOf);
            case 2011:
                return applicationContext.getString(R$string.IDS_hw_show_healthdata_bloodsugar_after_lunch_range, valueOf2, valueOf);
            default:
                return c(i, applicationContext);
        }
    }

    private static String c(int i, Context context) {
        Double valueOf = Double.valueOf(10.0d);
        Double valueOf2 = Double.valueOf(4.4d);
        switch (i) {
            case 2012:
                return context.getString(R$string.IDS_hw_show_healthdata_bloodsugar_before_dinner_range, valueOf2, valueOf);
            case 2013:
                return context.getString(R$string.IDS_hw_show_healthdata_bloodsugar_after_dinner_range, valueOf2, valueOf);
            case 2014:
                return context.getString(R$string.IDS_hw_show_healthdata_bloodsugar_before_sleep_range, valueOf2, valueOf);
            case 2015:
                return context.getString(R$string.IDS_hw_show_healthdata_bloodsugar_night_range, valueOf2, valueOf);
            default:
                return context.getString(R$string.IDS_hw_show_healthdata_bloodsugar_random_range, valueOf2, valueOf);
        }
    }

    public static String b(int i, Context context) {
        Context applicationContext = context.getApplicationContext();
        if (i != 2106) {
            switch (i) {
                case 2008:
                    return applicationContext.getString(R$string.IDS_hw_show_healthdata_bloodsugar_before_breakfast);
                case 2009:
                    return applicationContext.getString(R$string.IDS_hw_show_healthdata_bloodsugar_after_breakfast);
                case 2010:
                    return applicationContext.getString(R$string.IDS_hw_show_healthdata_bloodsugar_before_lunch);
                case 2011:
                    return applicationContext.getString(R$string.IDS_hw_show_healthdata_bloodsugar_after_lunch);
                case 2012:
                    return applicationContext.getString(R$string.IDS_hw_show_healthdata_bloodsugar_before_dinner);
                case 2013:
                    return applicationContext.getString(R$string.IDS_hw_show_healthdata_bloodsugar_after_dinner);
                case 2014:
                    return applicationContext.getString(R$string.IDS_hw_show_healthdata_bloodsugar_before_sleep);
                case 2015:
                    return applicationContext.getString(R$string.IDS_hw_show_healthdata_bloodsugar_early_morning);
                default:
                    return applicationContext.getString(R$string.IDS_hw_show_healthdata_bloodsugar_before_breakfast);
            }
        }
        return applicationContext.getString(R$string.IDS_hw_show_healthdata_bloodsugar_random_time);
    }

    public static String d(String str, String str2, String str3, String str4) {
        File[] listFiles;
        File file = new File(str2);
        String str5 = null;
        if (!file.exists() || !file.isDirectory() || (listFiles = file.listFiles()) == null) {
            return null;
        }
        try {
            for (File file2 : listFiles) {
                String replace = file2.getName().replace("strings", "").replace(WatchFaceConstant.XML_SUFFIX, "");
                if (replace.contains(str4)) {
                    if (replace.contains(str3)) {
                        return c(file2.getCanonicalPath(), str);
                    }
                    if (str5 == null) {
                        str5 = c(file2.getCanonicalPath(), str);
                    }
                }
            }
            return str5 == null ? c(listFiles[listFiles.length - 1].getCanonicalPath(), str) : str5;
        } catch (IOException unused) {
            LogUtil.b("StringUtil", "IOException");
            return null;
        }
    }

    public static String c(String str, String str2) {
        String str3;
        FileInputStream fileInputStream;
        XmlPullParser newPullParser;
        FileInputStream fileInputStream2 = null;
        r4 = null;
        r4 = null;
        r4 = null;
        String str4 = null;
        FileInputStream fileInputStream3 = null;
        FileInputStream fileInputStream4 = null;
        try {
            try {
                XmlPullParserFactory newInstance = XmlPullParserFactory.newInstance();
                newInstance.setNamespaceAware(true);
                newPullParser = newInstance.newPullParser();
                fileInputStream = new FileInputStream(new File(str));
            } catch (Throwable th) {
                th = th;
                fileInputStream = null;
            }
        } catch (FileNotFoundException unused) {
            str3 = null;
        } catch (IOException unused2) {
            str3 = null;
        } catch (XmlPullParserException unused3) {
            str3 = null;
        }
        try {
            newPullParser.setInput(fileInputStream, "UTF-8");
            for (int eventType = newPullParser.getEventType(); eventType != 1; eventType = newPullParser.next()) {
                if (eventType == 2) {
                    if ("string".equals(newPullParser.getName())) {
                        str4 = e(str2, newPullParser, newPullParser.getAttributeCount());
                        if (!TextUtils.isEmpty(str4)) {
                            break;
                        }
                    } else {
                        continue;
                    }
                }
            }
            try {
                fileInputStream.close();
                return str4;
            } catch (IOException unused4) {
                LogUtil.b("StringUtil", "close file stream error");
                return str4;
            }
        } catch (FileNotFoundException unused5) {
            str3 = str4;
            fileInputStream3 = fileInputStream;
            LogUtil.b("StringUtil", "not find xml file");
            if (fileInputStream3 != null) {
                try {
                    fileInputStream3.close();
                } catch (IOException unused6) {
                    LogUtil.b("StringUtil", "close file stream error");
                }
            }
            return str3;
        } catch (IOException unused7) {
            str3 = str4;
            fileInputStream4 = fileInputStream;
            LogUtil.b("StringUtil", "not read text");
            if (fileInputStream4 != null) {
                try {
                    fileInputStream4.close();
                } catch (IOException unused8) {
                    LogUtil.b("StringUtil", "close file stream error");
                }
            }
            return str3;
        } catch (XmlPullParserException unused9) {
            str3 = str4;
            fileInputStream2 = fileInputStream;
            LogUtil.b("StringUtil", "xml pull error");
            if (fileInputStream2 != null) {
                try {
                    fileInputStream2.close();
                } catch (IOException unused10) {
                    LogUtil.b("StringUtil", "close file stream error");
                }
            }
            return str3;
        } catch (Throwable th2) {
            th = th2;
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (IOException unused11) {
                    LogUtil.b("StringUtil", "close file stream error");
                }
            }
            throw th;
        }
    }

    private static String e(String str, XmlPullParser xmlPullParser, int i) throws IOException, XmlPullParserException {
        if (i == 1 && str.equals(xmlPullParser.getAttributeValue(0))) {
            return xmlPullParser.nextText();
        }
        return null;
    }
}
