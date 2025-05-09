package com.amap.api.col.p0003sl;

import android.os.Build;
import android.text.TextUtils;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* loaded from: classes2.dex */
public final class iq {

    /* renamed from: a, reason: collision with root package name */
    private static volatile ip f1194a;
    private static Properties b = b();

    private iq() {
    }

    public static ip a() {
        if (f1194a == null) {
            synchronized (iq.class) {
                if (f1194a == null) {
                    try {
                        ip a2 = a(Build.MANUFACTURER);
                        if ("".equals(a2.a())) {
                            Iterator it = Arrays.asList(ip.MIUI.a(), ip.Flyme.a(), ip.EMUI.a(), ip.ColorOS.a(), ip.FuntouchOS.a(), ip.SmartisanOS.a(), ip.AmigoOS.a(), ip.Sense.a(), ip.LG.a(), ip.Google.a(), ip.NubiaUI.a()).iterator();
                            while (true) {
                                if (it.hasNext()) {
                                    ip a3 = a((String) it.next());
                                    if (!"".equals(a3.a())) {
                                        a2 = a3;
                                        break;
                                    }
                                } else {
                                    a2 = ip.Other;
                                    break;
                                }
                            }
                        }
                        f1194a = a2;
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return f1194a;
    }

    private static ip a(String str) {
        if (str == null || str.length() <= 0) {
            return ip.Other;
        }
        if (str.equals(ip.MIUI.a())) {
            ip ipVar = ip.MIUI;
            if (a(ipVar)) {
                return ipVar;
            }
        } else if (str.equals(ip.Flyme.a())) {
            ip ipVar2 = ip.Flyme;
            if (b(ipVar2)) {
                return ipVar2;
            }
        } else if (str.equals(ip.EMUI.a())) {
            ip ipVar3 = ip.EMUI;
            if (c(ipVar3)) {
                return ipVar3;
            }
        } else if (str.equals(ip.ColorOS.a())) {
            ip ipVar4 = ip.ColorOS;
            if (d(ipVar4)) {
                return ipVar4;
            }
        } else if (str.equals(ip.FuntouchOS.a())) {
            ip ipVar5 = ip.FuntouchOS;
            if (e(ipVar5)) {
                return ipVar5;
            }
        } else if (str.equals(ip.SmartisanOS.a())) {
            ip ipVar6 = ip.SmartisanOS;
            if (f(ipVar6)) {
                return ipVar6;
            }
        } else if (str.equals(ip.AmigoOS.a())) {
            ip ipVar7 = ip.AmigoOS;
            if (g(ipVar7)) {
                return ipVar7;
            }
        } else if (str.equals(ip.EUI.a())) {
            ip ipVar8 = ip.EUI;
            if (h(ipVar8)) {
                return ipVar8;
            }
        } else if (str.equals(ip.Sense.a())) {
            ip ipVar9 = ip.Sense;
            if (i(ipVar9)) {
                return ipVar9;
            }
        } else if (str.equals(ip.LG.a())) {
            ip ipVar10 = ip.LG;
            if (j(ipVar10)) {
                return ipVar10;
            }
        } else if (str.equals(ip.Google.a())) {
            ip ipVar11 = ip.Google;
            if (k(ipVar11)) {
                return ipVar11;
            }
        } else if (str.equals(ip.NubiaUI.a())) {
            ip ipVar12 = ip.NubiaUI;
            if (l(ipVar12)) {
                return ipVar12;
            }
        }
        return ip.Other;
    }

    private static void a(ip ipVar, String str) {
        Matcher matcher = Pattern.compile("([\\d.]+)[^\\d]*").matcher(str);
        if (matcher.find()) {
            try {
                String group = matcher.group(1);
                ipVar.a(group);
                ipVar.a(Integer.parseInt(group.split("\\.")[0]));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private static String b(String str) {
        String property = b.getProperty("[" + str + "]", null);
        if (TextUtils.isEmpty(property)) {
            return c(str);
        }
        return property.replace("[", "").replace("]", "");
    }

    private static String c(String str) {
        BufferedReader bufferedReader;
        BufferedReader bufferedReader2 = null;
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(Runtime.getRuntime().exec("getprop ".concat(String.valueOf(str))).getInputStream()), 1024);
            try {
                String readLine = bufferedReader.readLine();
                bufferedReader.close();
                try {
                    bufferedReader.close();
                } catch (IOException unused) {
                }
                return readLine;
            } catch (IOException unused2) {
                if (bufferedReader != null) {
                    try {
                        bufferedReader.close();
                    } catch (IOException unused3) {
                    }
                }
                return null;
            } catch (Throwable th) {
                th = th;
                bufferedReader2 = bufferedReader;
                if (bufferedReader2 != null) {
                    try {
                        bufferedReader2.close();
                    } catch (IOException unused4) {
                    }
                }
                throw th;
            }
        } catch (IOException unused5) {
            bufferedReader = null;
        } catch (Throwable th2) {
            th = th2;
        }
    }

    private static Properties b() {
        Properties properties = new Properties();
        try {
            properties.load(Runtime.getRuntime().exec("getprop").getInputStream());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return properties;
    }

    private static boolean a(ip ipVar) {
        if (TextUtils.isEmpty(b("ro.miui.ui.version.name"))) {
            return false;
        }
        String b2 = b("ro.build.version.incremental");
        a(ipVar, b2);
        ipVar.b(b2);
        return true;
    }

    private static boolean b(ip ipVar) {
        String b2 = b("ro.flyme.published");
        String b3 = b("ro.meizu.setupwizard.flyme");
        if (TextUtils.isEmpty(b2) && TextUtils.isEmpty(b3)) {
            return false;
        }
        String b4 = b("ro.build.display.id");
        a(ipVar, b4);
        ipVar.b(b4);
        return true;
    }

    private static boolean c(ip ipVar) {
        String b2 = b("ro.build.version.emui");
        if (TextUtils.isEmpty(b2)) {
            return false;
        }
        a(ipVar, b2);
        ipVar.b(b2);
        return true;
    }

    private static boolean d(ip ipVar) {
        String b2 = b("ro.build.version.opporom");
        if (TextUtils.isEmpty(b2)) {
            return false;
        }
        a(ipVar, b2);
        ipVar.b(b2);
        return true;
    }

    private static boolean e(ip ipVar) {
        String b2 = b("ro.vivo.os.build.display.id");
        if (TextUtils.isEmpty(b2)) {
            return false;
        }
        a(ipVar, b2);
        ipVar.b(b2);
        return true;
    }

    private static boolean f(ip ipVar) {
        String b2 = b("ro.smartisan.version");
        if (TextUtils.isEmpty(b2)) {
            return false;
        }
        a(ipVar, b2);
        ipVar.b(b2);
        return true;
    }

    private static boolean g(ip ipVar) {
        String b2 = b("ro.build.display.id");
        if (TextUtils.isEmpty(b2) || !b2.matches("amigo([\\d.]+)[a-zA-Z]*")) {
            return false;
        }
        a(ipVar, b2);
        ipVar.b(b2);
        return true;
    }

    private static boolean h(ip ipVar) {
        String b2 = b("ro.letv.release.version");
        if (TextUtils.isEmpty(b2)) {
            return false;
        }
        a(ipVar, b2);
        ipVar.b(b2);
        return true;
    }

    private static boolean i(ip ipVar) {
        String b2 = b("ro.build.sense.version");
        if (TextUtils.isEmpty(b2)) {
            return false;
        }
        a(ipVar, b2);
        ipVar.b(b2);
        return true;
    }

    private static boolean j(ip ipVar) {
        String b2 = b("sys.lge.lgmdm_version");
        if (TextUtils.isEmpty(b2)) {
            return false;
        }
        a(ipVar, b2);
        ipVar.b(b2);
        return true;
    }

    private static boolean k(ip ipVar) {
        if (!"android-google".equals(b("ro.com.google.clientidbase"))) {
            return false;
        }
        String b2 = b("ro.build.version.release");
        ipVar.a(Build.VERSION.SDK_INT);
        ipVar.b(b2);
        return true;
    }

    private static boolean l(ip ipVar) {
        String b2 = b("ro.build.nubia.rom.code");
        if (TextUtils.isEmpty(b2)) {
            return false;
        }
        a(ipVar, b2);
        ipVar.b(b2);
        return true;
    }
}
