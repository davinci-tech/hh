package defpackage;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.TextUtils;
import com.huawei.health.device.api.HonourDeviceConstantsApi;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.IoUtils;
import health.compact.a.Services;
import health.compact.a.UnitUtil;
import health.compact.a.Utils;
import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;

/* loaded from: classes3.dex */
public class dcx {
    private static ArrayList<String> e = new ArrayList<>(10);
    private static HashMap<String, int[]> b = new HashMap<>(10);
    private static HashMap<String, Integer> c = new HashMap<>(10);
    private static HashMap<String, Double> d = new HashMap<>(10);

    /* renamed from: a, reason: collision with root package name */
    private static HashMap<String, double[]> f11595a = new HashMap<>(10);
    private static HashMap<String, String> j = new HashMap<>(10);
    private static HashMap<String, String[]> f = new HashMap<>(10);

    static {
        c.put("IDS_device_Loop_bind_1", 3);
        d.put("IDS_DEVICE_EASY_TO_READ_2052_334", Double.valueOf(3.5d));
        c.put("IDS_DEVICE_CON_43_41", 30);
        c.put("IDS_DEVICE_1212_314_41", 3);
        c.put("IDS_DEVICE_MAKE_SURE_TH_2960_41", 2);
        c.put("IDS_DEVICE_DATA_STORAGE_1950_169", 500);
        c.put("IDS_DEVICE_ELECTRONIC_I_766_226", 7);
        c.put("IDS_DEVICE_HOLD_TRANSFE_275_784", 2);
        c.put("IDS_device_omron9200_silent_guid_text_2", 2);
        c.put("IDS_device_auto_read_omron_introductions_2", 2);
        c.put("IDS_device_auto_read_omron_introductions_3", 3);
        c.put("IDS_device_auto_read_yolanda_introduction_1", 1);
        c.put("IDS_device_auto_read_johnson_introductions_2", 2);
        c.put("IDS_device_auto_read_honor_scale_introduction_2", 2);
        c.put("IDS_device_hygride_bind_fail_prompt_2", 1);
        c.put("IDS_device_hygride_pair_fail_prompt_2", 2);
        c.put("IDS_device_hygride_bind_fail_prompt_1", 1);
        c.put("IDS_device_bluetooth_scale_bind_fail_prompt_2", 2);
        c.put("IDS_device_hygride_wlan_fail_prompt_1", 1);
        c.put("IDS_device_hygride_wlan_fail_prompt_2", 2);
        int[] iArr = {1, 2};
        int[] iArr2 = {2, 3};
        int[] iArr3 = {50, 3};
        int[] iArr4 = {3, 2, 3};
        c.put("IDS_DEVICE_1UL_MICRO_BL_990_317", 1);
        b.put("IDS_DEVICE_EN3_43_169", iArr3);
        b.put("IDS_DEVICE_THE_PHONE_WI_5660_41", new int[]{8, 3});
        b.put("IDS_device_guid_omron9200_text_1", iArr2);
        b.put("IDS_DEVICE_CUFF_IS_2_3C_324_829", iArr2);
        b.put("IDS_device_omron9200_silent_guid_text_1", iArr);
        b.put("IDS_device_auto_read_omron_introductions_1", new int[]{1, 3});
        b.put("IDS_device_auto_read_omron_introductions_4", iArr4);
        b.put("IDS_device_auto_read_omron_introductions_5", new int[]{4, 2, 3});
        b.put("IDS_device_auto_read_yuwell_introductions_1", iArr);
        b.put("IDS_device_auto_read_yolanda_introduction_2", new int[]{2, 30});
        b.put("IDS_device_auto_read_yolanda_introduction_3", new int[]{3, 10, 80});
        b.put("IDS_device_auto_read_johnson_introductions_1", iArr);
        b.put("IDS_device_auto_read_johnson_introductions_3", iArr4);
        b.put("IDS_device_introductionactivity_yolanda_introduction_2", iArr3);
        if (Utils.o()) {
            b.put("IDS_device_hygride_scale_description_1", new int[]{10, 12});
            c.put("IDS_device_huawei_body_fat_scale_description_2", 10);
            c.put("IDS_device_honor_scale_description_1", 11);
        } else {
            b.put("IDS_device_hygride_scale_description_1", new int[]{14, 15});
            c.put("IDS_device_huawei_body_fat_scale_description_2", 12);
            c.put("IDS_device_honor_scale_description_1", 14);
        }
        b.put("IDS_device_hygride_measure_fail_prompt_3", new int[]{3, 20, 20});
        b.put("IDS_device_herm_measure_fail_prompt_3", new int[]{3, 10, 10});
        b.put("IDS_device_hag2021_measure_fail_prompt_3", new int[]{3, 10, 10});
        b.put("IDS_device_hagrid_b29_meausre_new", new int[]{1, 2, 3, 4});
        b.put("IDS_device_hagrid_b19_meausre_new", new int[]{1, 2, 3, 4});
        b.put("IDS_DEVICE_2_USERS_99_668_849", new int[]{2, 99});
        int[] iArr5 = {1, 2, 3};
        b.put("IDS_device_measure_pressure_guide_prompt", iArr5);
        b.put("IDS_DEVICE_PRESS_THE_ST_931_543", iArr5);
        d.put("IDS_device_8806_fest_2", Double.valueOf(0.5d));
        d.put("IDS_device_8806_desc_1", Double.valueOf(0.5d));
        b.put("IDS_device_8806_desc_4", new int[]{12, 1, 15});
        b.put("IDS_device_BP_RBP56_DRBP_HRBP_measure_1", iArr2);
        int[] iArr6 = {600, 15};
        b.put("IDS_device_A9_SH_T9119A_01_fest_2", iArr6);
        b.put("IDS_device_A9_SH_T9119A_01_desc_2", iArr6);
        b.put("IDS_device_A9_SH_T9119A_01_desc_3", new int[]{1, 16});
    }

    public static Bitmap TK_(String str) {
        InputStream inputStream;
        Bitmap decodeStream;
        InputStream inputStream2 = null;
        try {
            if (new File(str).exists()) {
                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inPreferredConfig = Bitmap.Config.RGB_565;
                options.inInputShareable = true;
                decodeStream = BitmapFactory.decodeFile(str, options);
            } else {
                BitmapFactory.Options options2 = new BitmapFactory.Options();
                options2.inPreferredConfig = Bitmap.Config.RGB_565;
                options2.inPurgeable = true;
                options2.inInputShareable = true;
                inputStream = cpp.a().getResources().openRawResource(a("hw_device_default"));
                try {
                    try {
                        decodeStream = BitmapFactory.decodeStream(inputStream, null, options2);
                        inputStream2 = inputStream;
                    } catch (Exception unused) {
                        LogUtil.b("ResourceLoader", "loadImage Exception");
                        IoUtils.e(inputStream);
                        return null;
                    }
                } catch (Throwable th) {
                    th = th;
                    inputStream2 = inputStream;
                    IoUtils.e(inputStream2);
                    throw th;
                }
            }
            IoUtils.e(inputStream2);
            return decodeStream;
        } catch (Exception unused2) {
            inputStream = null;
        } catch (Throwable th2) {
            th = th2;
            IoUtils.e(inputStream2);
            throw th;
        }
    }

    public static String d(String str, String str2) {
        return a(str, str2, "");
    }

    public static String a(String str, String str2, String str3) {
        String str4 = "";
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return "";
        }
        if (Utils.o() && !((HonourDeviceConstantsApi) Services.c("PluginDevice", HonourDeviceConstantsApi.class)).isHuaweiWspScaleProduct(str)) {
            try {
                str4 = cpp.a().getResources().getString(e(str2));
                if (!TextUtils.isEmpty(str4.trim())) {
                    return str4;
                }
            } catch (Resources.NotFoundException e2) {
                LogUtil.b("ResourceLoader", "nameKey:", str2, " is not found for String.xml ", e2.getMessage());
            }
        }
        return b(str, str2, str4, str3);
    }

    /* JADX WARN: Removed duplicated region for block: B:40:0x00b1 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:41:0x00b2 A[RETURN] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static java.lang.String b(java.lang.String r8, java.lang.String r9, java.lang.String r10, java.lang.String r11) {
        /*
            java.lang.String r0 = "ResourceLoader"
            javax.xml.parsers.DocumentBuilderFactory r1 = javax.xml.parsers.DocumentBuilderFactory.newInstance()
            defpackage.dea.c(r1)     // Catch: javax.xml.parsers.ParserConfigurationException -> La
            goto L18
        La:
            r2 = move-exception
            java.lang.String r3 = "ResourceLoader getStringFormat e:"
            java.lang.String r2 = r2.getMessage()
            java.lang.Object[] r2 = new java.lang.Object[]{r3, r2}
            com.huawei.hwlogsmodel.LogUtil.b(r0, r2)
        L18:
            r2 = 0
            r3 = 1
            r4 = 0
            dcq r5 = defpackage.dcq.b()     // Catch: java.lang.Throwable -> L8d org.xml.sax.SAXException -> L8f java.io.IOException -> L91 javax.xml.parsers.ParserConfigurationException -> L93
            java.lang.String r11 = r5.d(r8, r11)     // Catch: java.lang.Throwable -> L8d org.xml.sax.SAXException -> L8f java.io.IOException -> L91 javax.xml.parsers.ParserConfigurationException -> L93
            java.io.FileInputStream r5 = new java.io.FileInputStream     // Catch: java.lang.Throwable -> L8d org.xml.sax.SAXException -> L8f java.io.IOException -> L91 javax.xml.parsers.ParserConfigurationException -> L93
            java.lang.String r11 = health.compact.a.CommonUtil.c(r11)     // Catch: java.lang.Throwable -> L8d org.xml.sax.SAXException -> L8f java.io.IOException -> L91 javax.xml.parsers.ParserConfigurationException -> L93
            r5.<init>(r11)     // Catch: java.lang.Throwable -> L8d org.xml.sax.SAXException -> L8f java.io.IOException -> L91 javax.xml.parsers.ParserConfigurationException -> L93
            javax.xml.parsers.DocumentBuilder r11 = r1.newDocumentBuilder()     // Catch: java.lang.Throwable -> L84 org.xml.sax.SAXException -> L86 java.io.IOException -> L88 javax.xml.parsers.ParserConfigurationException -> L8a
            org.w3c.dom.Document r11 = r11.parse(r5)     // Catch: java.lang.Throwable -> L84 org.xml.sax.SAXException -> L86 java.io.IOException -> L88 javax.xml.parsers.ParserConfigurationException -> L8a
            org.w3c.dom.Element r11 = r11.getDocumentElement()     // Catch: java.lang.Throwable -> L84 org.xml.sax.SAXException -> L86 java.io.IOException -> L88 javax.xml.parsers.ParserConfigurationException -> L8a
            org.w3c.dom.NodeList r11 = r11.getChildNodes()     // Catch: java.lang.Throwable -> L84 org.xml.sax.SAXException -> L86 java.io.IOException -> L88 javax.xml.parsers.ParserConfigurationException -> L8a
            if (r11 != 0) goto L42
            health.compact.a.IoUtils.e(r5)
            return r4
        L42:
            r1 = r2
        L43:
            int r4 = r11.getLength()     // Catch: java.lang.Throwable -> L84 org.xml.sax.SAXException -> L86 java.io.IOException -> L88 javax.xml.parsers.ParserConfigurationException -> L8a
            if (r1 >= r4) goto L80
            org.w3c.dom.Node r4 = r11.item(r1)     // Catch: java.lang.Throwable -> L84 org.xml.sax.SAXException -> L86 java.io.IOException -> L88 javax.xml.parsers.ParserConfigurationException -> L8a
            short r6 = r4.getNodeType()     // Catch: java.lang.Throwable -> L84 org.xml.sax.SAXException -> L86 java.io.IOException -> L88 javax.xml.parsers.ParserConfigurationException -> L8a
            if (r6 != r3) goto L7d
            org.w3c.dom.NamedNodeMap r6 = r4.getAttributes()     // Catch: java.lang.Throwable -> L84 org.xml.sax.SAXException -> L86 java.io.IOException -> L88 javax.xml.parsers.ParserConfigurationException -> L8a
            if (r6 != 0) goto L5a
            goto L7d
        L5a:
            java.lang.String r7 = "name"
            org.w3c.dom.Node r6 = r6.getNamedItem(r7)     // Catch: java.lang.Throwable -> L84 org.xml.sax.SAXException -> L86 java.io.IOException -> L88 javax.xml.parsers.ParserConfigurationException -> L8a
            if (r6 != 0) goto L63
            goto L7d
        L63:
            if (r9 == 0) goto L7d
            java.lang.String r6 = r6.getNodeValue()     // Catch: java.lang.Throwable -> L84 org.xml.sax.SAXException -> L86 java.io.IOException -> L88 javax.xml.parsers.ParserConfigurationException -> L8a
            boolean r6 = r9.equals(r6)     // Catch: java.lang.Throwable -> L84 org.xml.sax.SAXException -> L86 java.io.IOException -> L88 javax.xml.parsers.ParserConfigurationException -> L8a
            if (r6 == 0) goto L7d
            java.lang.String r4 = r4.getTextContent()     // Catch: java.lang.Throwable -> L84 org.xml.sax.SAXException -> L86 java.io.IOException -> L88 javax.xml.parsers.ParserConfigurationException -> L8a
            java.lang.String r6 = "\\n"
            java.lang.String r7 = java.lang.System.lineSeparator()     // Catch: java.lang.Throwable -> L84 org.xml.sax.SAXException -> L86 java.io.IOException -> L88 javax.xml.parsers.ParserConfigurationException -> L8a
            java.lang.String r10 = r4.replace(r6, r7)     // Catch: java.lang.Throwable -> L84 org.xml.sax.SAXException -> L86 java.io.IOException -> L88 javax.xml.parsers.ParserConfigurationException -> L8a
        L7d:
            int r1 = r1 + 1
            goto L43
        L80:
            health.compact.a.IoUtils.e(r5)
            goto La7
        L84:
            r8 = move-exception
            goto Lb4
        L86:
            r11 = move-exception
            goto L8b
        L88:
            r11 = move-exception
            goto L8b
        L8a:
            r11 = move-exception
        L8b:
            r4 = r5
            goto L94
        L8d:
            r8 = move-exception
            goto Lb3
        L8f:
            r11 = move-exception
            goto L94
        L91:
            r11 = move-exception
            goto L94
        L93:
            r11 = move-exception
        L94:
            r1 = 2
            java.lang.Object[] r1 = new java.lang.Object[r1]     // Catch: java.lang.Throwable -> L8d
            java.lang.String r5 = "getStringFormat"
            r1[r2] = r5     // Catch: java.lang.Throwable -> L8d
            java.lang.String r11 = r11.getMessage()     // Catch: java.lang.Throwable -> L8d
            r1[r3] = r11     // Catch: java.lang.Throwable -> L8d
            com.huawei.hwlogsmodel.LogUtil.b(r0, r1)     // Catch: java.lang.Throwable -> L8d
            health.compact.a.IoUtils.e(r4)
        La7:
            java.lang.String r8 = d(r8, r9, r10)
            boolean r9 = android.text.TextUtils.isEmpty(r8)
            if (r9 == 0) goto Lb2
            return r10
        Lb2:
            return r8
        Lb3:
            r5 = r4
        Lb4:
            health.compact.a.IoUtils.e(r5)
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.dcx.b(java.lang.String, java.lang.String, java.lang.String, java.lang.String):java.lang.String");
    }

    public static int e(String str) {
        return cpp.a().getResources().getIdentifier(str, "string", cpp.a().getPackageName());
    }

    public static int a(String str) {
        return cpp.a().getResources().getIdentifier(str, "drawable", cpp.a().getPackageName());
    }

    private static String d(String str, String str2, String str3) {
        if (c.containsKey(str2)) {
            return a(c.get(str2).intValue(), 1, 0, str3);
        }
        if (d.containsKey(str2)) {
            return a(d.get(str2).doubleValue(), 1, 1, str3);
        }
        if (j.containsKey(str2)) {
            if (j.get(str2).equals("default")) {
                String i = nsn.i(cpp.a());
                return !i.isEmpty() ? String.format(Locale.ENGLISH, str3, i) : "";
            }
            return String.format(Locale.ROOT, str3, j.get(str2));
        }
        if (b.containsKey(str2)) {
            e.clear();
            if (str2.equals("IDS_device_hygride_measure_fail_prompt_3") && str.equals("b29df4e3-b1f7-4e40-960d-4cfb63ccca05")) {
                str2 = "IDS_device_hag2021_measure_fail_prompt_3";
            }
            int length = b.get(str2).length;
            for (int i2 = 0; i2 < length; i2++) {
                e.add(UnitUtil.e(r7[i2], 1, 0));
            }
            String c2 = c(str3);
            return c2 != null ? c2 : "";
        }
        if (f.containsKey(str2)) {
            e.clear();
            for (String str4 : f.get(str2)) {
                if (str4.equals("default")) {
                    e.add(nsn.i(cpp.a()));
                } else {
                    e.add(str4);
                }
            }
            String c3 = c(str3);
            return c3 != null ? c3 : "";
        }
        LogUtil.h("ResourceLoader", "ResourceLoader stringFormat nameKey :", str2);
        return "";
    }

    private static String c(String str) {
        int size = e.size();
        if (size == 2) {
            return String.format(Locale.ROOT, str, e.get(0), e.get(1));
        }
        if (size == 3) {
            return String.format(Locale.ROOT, str, e.get(0), e.get(1), e.get(2));
        }
        if (size == 4) {
            return String.format(Locale.ROOT, str, e.get(0), e.get(1), e.get(2), e.get(3));
        }
        LogUtil.h("ResourceLoader", "getFormatString default");
        return "";
    }

    private static String a(double d2, int i, int i2, String str) {
        String e2 = UnitUtil.e(d2, i, i2);
        return !e2.isEmpty() ? String.format(Locale.ROOT, str, e2) : "";
    }

    public static void d(String str, int i) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        c.put(str, Integer.valueOf(i));
    }

    public static void d(String str, double d2) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        d.put(str, Double.valueOf(d2));
    }

    public static void c(String str, String str2) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        j.put(str, str2);
    }

    public static void c(String str, int[] iArr) {
        if (TextUtils.isEmpty(str) || iArr == null || iArr.length == 0) {
            return;
        }
        b.put(str, iArr);
    }

    public static void e(String str, double[] dArr) {
        if (TextUtils.isEmpty(str) || dArr == null || dArr.length == 0) {
            return;
        }
        f11595a.put(str, dArr);
    }

    public static void a(String str, String[] strArr) {
        if (TextUtils.isEmpty(str) || strArr == null || strArr.length == 0) {
            return;
        }
        f.put(str, strArr);
    }
}
