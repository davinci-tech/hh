package defpackage;

import android.text.TextUtils;
import com.huawei.health.suggestion.ui.voice.IFitRunVoiceContentConstructor;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.utils.Constants;
import com.huawei.pluginfitnessadvice.TargetConfig;
import health.compact.a.CommonUtil;
import health.compact.a.CommonUtils;
import health.compact.a.LanguageUtil;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes4.dex */
public class gfq implements IFitRunVoiceContentConstructor {
    private String b;
    private String c;
    private String e;

    public gfq(String str, String str2, String str3) {
        this.b = str;
        this.e = str2;
        this.c = str3;
    }

    @Override // com.huawei.health.suggestion.ui.voice.IFitRunVoiceContentConstructor
    public Object getSpeedLowerLimit() {
        return c("K013");
    }

    @Override // com.huawei.health.suggestion.ui.voice.IFitRunVoiceContentConstructor
    public Object getSpeedUpperLimit() {
        return c("K012");
    }

    @Override // com.huawei.health.suggestion.ui.voice.IFitRunVoiceContentConstructor
    public Object getRelativeLowerLimit() {
        return c("K015");
    }

    @Override // com.huawei.health.suggestion.ui.voice.IFitRunVoiceContentConstructor
    public Object getRelativeUpperLimit() {
        return c("K014");
    }

    @Override // com.huawei.health.suggestion.ui.voice.IFitRunVoiceContentConstructor
    public Object getAbsoluteLowerLimit() {
        return c("K015");
    }

    @Override // com.huawei.health.suggestion.ui.voice.IFitRunVoiceContentConstructor
    public Object getAbsoluteUpperLimit() {
        return c("K014");
    }

    @Override // com.huawei.health.suggestion.ui.voice.IFitRunVoiceContentConstructor
    public Object getHeartRateDeviceError() {
        return c("K016");
    }

    @Override // com.huawei.health.suggestion.ui.voice.IFitRunVoiceContentConstructor
    public Object getHeartRateDeviceConnected() {
        return c("K017");
    }

    @Override // com.huawei.health.suggestion.ui.voice.IFitRunVoiceContentConstructor
    public Object getNormalHeartRate() {
        return c("K027");
    }

    @Override // com.huawei.health.suggestion.ui.voice.IFitRunVoiceContentConstructor
    public Object getNormalPace() {
        return c("K028");
    }

    @Override // com.huawei.health.suggestion.ui.voice.IFitRunVoiceContentConstructor
    public Object getNormalSpeed() {
        return c("K029");
    }

    @Override // com.huawei.health.suggestion.ui.voice.IFitRunVoiceContentConstructor
    public Object getNormalStepRate() {
        return c("K030");
    }

    @Override // com.huawei.health.suggestion.ui.voice.IFitRunVoiceContentConstructor
    public Object getChangeNextAction(String str, int i, int i2, TargetConfig targetConfig) {
        return e(str, i, i2, "K002", targetConfig);
    }

    @Override // com.huawei.health.suggestion.ui.voice.IFitRunVoiceContentConstructor
    public Object getChangeLastAction(String str, int i, int i2, TargetConfig targetConfig) {
        return e(str, i, i2, "K003", targetConfig);
    }

    @Override // com.huawei.health.suggestion.ui.voice.IFitRunVoiceContentConstructor
    public Object getChangeFirstAction(String str, int i, int i2, TargetConfig targetConfig) {
        return e(str, i, i2, "K001", targetConfig);
    }

    @Override // com.huawei.health.suggestion.ui.voice.IFitRunVoiceContentConstructor
    public Object getReserveHRLowerLimit() {
        return c("K015");
    }

    @Override // com.huawei.health.suggestion.ui.voice.IFitRunVoiceContentConstructor
    public Object getReserveHRUpperLimit() {
        return c("K014");
    }

    @Override // com.huawei.health.suggestion.ui.voice.IFitRunVoiceContentConstructor
    public Object getReserveHRIntervalLowerLimit() {
        return c("K015");
    }

    @Override // com.huawei.health.suggestion.ui.voice.IFitRunVoiceContentConstructor
    public Object getReserveHRIntervalUpperLimit() {
        return c("K014");
    }

    @Override // com.huawei.health.suggestion.ui.voice.IFitRunVoiceContentConstructor
    public Object getMAF180LowerLimit() {
        return c("K015");
    }

    @Override // com.huawei.health.suggestion.ui.voice.IFitRunVoiceContentConstructor
    public Object getMAF180UpperLimit() {
        return c("K014");
    }

    @Override // com.huawei.health.suggestion.ui.voice.IFitRunVoiceContentConstructor
    public Object getPaceZoneLowerLimit() {
        return c("K023");
    }

    @Override // com.huawei.health.suggestion.ui.voice.IFitRunVoiceContentConstructor
    public Object getPaceZoneUpperLimit() {
        return c("K022");
    }

    @Override // com.huawei.health.suggestion.ui.voice.IFitRunVoiceContentConstructor
    public Object getStepRateUpperLimit() {
        return c("K031");
    }

    @Override // com.huawei.health.suggestion.ui.voice.IFitRunVoiceContentConstructor
    public Object getStepRateLowerLimit() {
        return c("K032");
    }

    private Object e(String str, int i, int i2, String str2, TargetConfig targetConfig) {
        String c;
        if (str == null || "".equals(str)) {
            LogUtil.b("Suggestion_FitRunVoiceBaseContentConstructor", "getChangeAction null == nameUrl || \"\".equals(nameUrl)");
            return null;
        }
        Object[] objArr = new Object[10];
        objArr[0] = "value = ";
        objArr[1] = Integer.valueOf(i);
        objArr[2] = "nameUrl = ";
        objArr[3] = str;
        objArr[4] = "measurementType = ";
        objArr[5] = Integer.valueOf(i2);
        objArr[6] = "firstAudio = ";
        objArr[7] = str2;
        objArr[8] = "intensityConfigId: = ";
        objArr[9] = targetConfig == null ? Constants.NULL : targetConfig.getId();
        LogUtil.a("Suggestion_FitRunVoiceBaseContentConstructor", objArr);
        if (str.startsWith("http")) {
            c = b(str);
        } else {
            c = c(str);
            if (!new File(c).exists()) {
                LogUtil.b("Suggestion_FitRunVoiceBaseContentConstructor", "getChangeAction !new File(audiosFilePath).exists() = true , audiosFilePath = ", c);
                return null;
            }
        }
        String str3 = c;
        if (i2 == 1) {
            return c(i, str2, str3, targetConfig);
        }
        if (i2 == 0) {
            return a(i, str2, str3, targetConfig);
        }
        if (i2 == 3) {
            return b(i, str2, str3, targetConfig);
        }
        if (i2 == 4 || i2 == 5) {
            return d(i, str2, str3, targetConfig, i2);
        }
        if (i2 == 255) {
            String[] strArr = {c(str2), str3};
            return d(targetConfig) ? c(strArr, targetConfig) : strArr;
        }
        LogUtil.b("Suggestion_FitRunVoiceBaseContentConstructor", "getChangeAction other measurementType:", Integer.valueOf(i2));
        return null;
    }

    public boolean d(TargetConfig targetConfig) {
        if (targetConfig == null || TextUtils.isEmpty(targetConfig.getId())) {
            LogUtil.h("Suggestion_FitRunVoiceBaseContentConstructor", "hasIntensityConfig intensityConfig false");
            return false;
        }
        int h = CommonUtils.h(targetConfig.getId());
        LogUtil.a("Suggestion_FitRunVoiceBaseContentConstructor", "hasIntensityConfig intensityConfigType :", Integer.valueOf(h));
        if (!CommonUtil.c(targetConfig.getValueL()) || !CommonUtil.c(targetConfig.getValueH())) {
            return h != 255 && ftd.e().a();
        }
        LogUtil.h("Suggestion_FitRunVoiceBaseContentConstructor", "hasIntensityConfig value == zero");
        return false;
    }

    private String[] d(int i, String str, String str2, TargetConfig targetConfig, int i2) {
        ArrayList arrayList = new ArrayList();
        List<String> b = b(i);
        arrayList.add(str);
        arrayList.add(str2);
        if (i2 == 4) {
            arrayList.add("K024");
        } else {
            arrayList.add("K025");
        }
        arrayList.addAll(b);
        arrayList.add("I013");
        String[] strArr = new String[arrayList.size()];
        for (int i3 = 0; i3 < arrayList.size(); i3++) {
            if (i3 == 1) {
                strArr[1] = str2;
            } else {
                strArr[i3] = c((String) arrayList.get(i3));
            }
        }
        return d(targetConfig) ? c(strArr, targetConfig) : strArr;
    }

    private String[] b(int i, String str, String str2, TargetConfig targetConfig) {
        ArrayList arrayList = new ArrayList();
        List<String> b = b(i);
        arrayList.add(str);
        arrayList.add(str2);
        arrayList.add("K026");
        arrayList.addAll(b);
        arrayList.add("I010");
        String[] strArr = new String[arrayList.size()];
        for (int i2 = 0; i2 < arrayList.size(); i2++) {
            if (i2 == 1) {
                strArr[1] = str2;
            } else {
                strArr[i2] = c((String) arrayList.get(i2));
            }
        }
        return d(targetConfig) ? c(strArr, targetConfig) : strArr;
    }

    private String[] a(int i, String str, String str2, TargetConfig targetConfig) {
        String[] a2;
        if (i < 1000) {
            ArrayList arrayList = new ArrayList();
            arrayList.add(str);
            arrayList.add(str2);
            arrayList.addAll(b(i));
            arrayList.add("I001");
            a2 = new String[arrayList.size()];
            for (int i2 = 0; i2 < arrayList.size(); i2++) {
                if (i2 == 1) {
                    a2[i2] = str2;
                } else {
                    a2[i2] = c((String) arrayList.get(i2));
                }
            }
        } else {
            a2 = a(i, str, str2);
        }
        return d(targetConfig) ? c(a2, targetConfig) : a2;
    }

    private String[] a(int i, String str, String str2) {
        int i2 = i / 1000;
        int i3 = (i % 1000) / 100;
        int i4 = (i % 100) / 10;
        ArrayList arrayList = new ArrayList();
        arrayList.add(str);
        arrayList.add(str2);
        arrayList.addAll(b(i2));
        if (i3 == 0 && i4 != 0) {
            arrayList.add("I017");
            arrayList.add("H999");
            arrayList.add(e(i4));
        } else if (i3 != 0 && i4 == 0) {
            arrayList.add("I017");
            arrayList.add(e(i3));
        } else if (i3 != 0) {
            arrayList.add("I017");
            arrayList.add(e(i3));
            arrayList.add(e(i4));
        } else {
            LogUtil.a("Suggestion_FitRunVoiceBaseContentConstructor", "handleTenThousandDistance hundredNum and tenNumber zero");
        }
        arrayList.add("I006");
        String[] strArr = new String[arrayList.size()];
        for (int i5 = 0; i5 < arrayList.size(); i5++) {
            if (i5 == 1) {
                strArr[i5] = str2;
            } else {
                strArr[i5] = c((String) arrayList.get(i5));
            }
        }
        return strArr;
    }

    private String[] c(int i, String str, String str2, TargetConfig targetConfig) {
        String[] strArr;
        if (i < 60) {
            strArr = new String[]{c(str), str2, c(i < 10 ? "H00" + i : "H0" + i), c("I009")};
        } else {
            int i2 = i / 3600;
            int i3 = (i % 3600) / 60;
            int i4 = i % 60;
            ArrayList arrayList = new ArrayList();
            arrayList.add(str);
            arrayList.add(str2);
            if (i2 != 0) {
                arrayList.add(e(i2));
                arrayList.add("I018");
            }
            if (i3 != 0) {
                arrayList.add(e(i3));
                arrayList.add("I007");
            }
            if (i4 != 0) {
                arrayList.add(e(i4));
                arrayList.add("I009");
            }
            strArr = new String[arrayList.size()];
            for (int i5 = 0; i5 < arrayList.size(); i5++) {
                if (i5 == 1) {
                    strArr[i5] = str2;
                } else {
                    strArr[i5] = c((String) arrayList.get(i5));
                }
            }
        }
        return d(targetConfig) ? c(strArr, targetConfig) : strArr;
    }

    /* JADX WARN: Removed duplicated region for block: B:21:0x005e  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x0068  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private java.lang.String[] c(java.lang.String[] r6, com.huawei.pluginfitnessadvice.TargetConfig r7) {
        /*
            r5 = this;
            java.lang.String r0 = "Suggestion_FitRunVoiceBaseContentConstructor"
            r1 = 0
            if (r6 != 0) goto Lf
            java.lang.String r6 = "combineAudios nextAudios == null"
            java.lang.Object[] r6 = new java.lang.Object[]{r6}
            com.huawei.hwlogsmodel.LogUtil.h(r0, r6)
            return r1
        Lf:
            java.lang.String r2 = r7.getId()
            int r2 = health.compact.a.CommonUtils.h(r2)
            if (r2 == 0) goto L58
            r3 = 1
            if (r2 == r3) goto L58
            r3 = 2
            if (r2 == r3) goto L58
            r3 = 4
            if (r2 == r3) goto L58
            r3 = 7
            if (r2 == r3) goto L58
            r3 = 8
            if (r2 == r3) goto L58
            switch(r2) {
                case 13: goto L53;
                case 14: goto L3f;
                case 15: goto L53;
                case 16: goto L3a;
                case 17: goto L58;
                default: goto L2c;
            }
        L2c:
            java.lang.String r2 = "combineAudios other intensityConfig.getId():"
            java.lang.String r7 = r7.getId()
            java.lang.Object[] r7 = new java.lang.Object[]{r2, r7}
            com.huawei.hwlogsmodel.LogUtil.b(r0, r7)
            goto L5c
        L3a:
            java.lang.String[] r1 = r5.b(r7)
            goto L5c
        L3f:
            ftd r1 = defpackage.ftd.e()
            boolean r1 = r1.d()
            if (r1 == 0) goto L4e
            java.lang.String[] r1 = r5.c(r7)
            goto L5c
        L4e:
            java.lang.String[] r1 = r5.a(r7)
            goto L5c
        L53:
            java.lang.String[] r1 = r5.a(r7)
            goto L5c
        L58:
            java.lang.String[] r1 = r5.c(r7)
        L5c:
            if (r1 != 0) goto L68
            java.lang.String r7 = "combineAudios intensityAudio == null"
            java.lang.Object[] r7 = new java.lang.Object[]{r7}
            com.huawei.hwlogsmodel.LogUtil.h(r0, r7)
            return r6
        L68:
            int r7 = r6.length
            int r0 = r1.length
            int r7 = r7 + r0
            java.lang.String[] r0 = new java.lang.String[r7]
            r2 = 0
        L6e:
            if (r2 >= r7) goto L83
            int r3 = r6.length
            if (r2 >= r3) goto L78
            r3 = r6[r2]
            r0[r2] = r3
            goto L80
        L78:
            int r3 = r1.length
            int r4 = r7 - r2
            int r3 = r3 - r4
            r3 = r1[r3]
            r0[r2] = r3
        L80:
            int r2 = r2 + 1
            goto L6e
        L83:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.gfq.c(java.lang.String[], com.huawei.pluginfitnessadvice.TargetConfig):java.lang.String[]");
    }

    private String[] a(TargetConfig targetConfig) {
        int b;
        int b2;
        LogUtil.a("Suggestion_FitRunVoiceBaseContentConstructor", "enter getPaceAudioFilePath intensityConfig.getId()", targetConfig.getId());
        int valueL = (int) targetConfig.getValueL();
        int valueH = (int) targetConfig.getValueH();
        if (CommonUtils.h(targetConfig.getId()) == 15) {
            b = valueL / 1000;
            b2 = valueH / 1000;
        } else {
            ffg c = ftd.e().c();
            if (c != null && c.j() != 0) {
                b = c.b(valueL, true);
                b2 = c.b(valueH, false);
                LogUtil.a("Suggestion_FitRunVoiceBaseContentConstructor", "getPaceAudioFilePath valueLower:", Integer.valueOf(b), "and valueUpper:", Integer.valueOf(b2));
            } else {
                LogUtil.h("Suggestion_FitRunVoiceBaseContentConstructor", "getPaceAudioFilePath paceZoneConfig == null");
                return null;
            }
        }
        String[] d = d(b);
        String[] d2 = d(b2);
        if (d != null && d2 != null) {
            String[] e = e(d, d2);
            return e != null ? e : new String[]{c("K018"), c(d[0]), c("I007"), c(d[1]), c("I009"), c("K021"), c(d2[0]), c("I007"), c(d2[1]), c("I009"), c("I011")};
        }
        LogUtil.h("Suggestion_FitRunVoiceBaseContentConstructor", "getPaceAudioFilePath", "lowerValue == null || upperValue == null");
        return null;
    }

    private String[] e(String[] strArr, String[] strArr2) {
        if ("H999".equals(strArr[1]) && !"H999".equals(strArr2[1])) {
            return new String[]{c("K018"), c(strArr[0]), c("I007"), c("K021"), c(strArr2[0]), c("I007"), c(strArr2[1]), c("I009"), c("I011")};
        }
        if (!"H999".equals(strArr[1]) && "H999".equals(strArr2[1])) {
            return new String[]{c("K018"), c(strArr[0]), c("I007"), c(strArr[1]), c("I009"), c("K021"), c(strArr2[0]), c("I007"), c("I011")};
        }
        if ("H999".equals(strArr[1])) {
            return new String[]{c("K018"), c(strArr[0]), c("I007"), c("K021"), c(strArr2[0]), c("I007"), c("I011")};
        }
        LogUtil.a("Suggestion_FitRunVoiceBaseContentConstructor", "dealZeroSecondVoice no FILE_HEAD_ZERO_NUMBER");
        return null;
    }

    private String[] c(TargetConfig targetConfig) {
        String str;
        String str2;
        LogUtil.a("Suggestion_FitRunVoiceBaseContentConstructor", "enter getZoneAudioFilePath intensityConfig.getId()", targetConfig.getId());
        int c = c(targetConfig, (int) targetConfig.getValueL(), true);
        int c2 = c(targetConfig, (int) targetConfig.getValueH(), false);
        if (c <= 0 || c2 <= 0) {
            LogUtil.h("Suggestion_FitRunVoiceBaseContentConstructor", "getZoneAudioFilePath valueLower <= 0 || valueUpper <= 0");
            return null;
        }
        if (CommonUtils.h(targetConfig.getId()) == 0) {
            str2 = "K020";
            str = "I012";
        } else {
            str = "I013";
            str2 = "K019";
        }
        ArrayList arrayList = new ArrayList();
        List<String> b = b(c);
        arrayList.add(str2);
        arrayList.addAll(b);
        arrayList.add("K021");
        List<String> b2 = b(c2);
        b2.add(str);
        arrayList.addAll(b2);
        String[] strArr = new String[arrayList.size()];
        for (int i = 0; i < arrayList.size(); i++) {
            strArr[i] = c((String) arrayList.get(i));
        }
        return strArr;
    }

    private String[] b(TargetConfig targetConfig) {
        LogUtil.a("Suggestion_FitRunVoiceBaseContentConstructor", "enter getZoneAudioFilePath intensityConfig.getId()", targetConfig.getId());
        int valueL = (int) targetConfig.getValueL();
        int valueH = (int) targetConfig.getValueH();
        if (valueL <= 0 || valueH <= 0) {
            LogUtil.h("Suggestion_FitRunVoiceBaseContentConstructor", "getZoneAudioFilePath valueLower <= 0 || valueUpper <= 0");
            return null;
        }
        ArrayList arrayList = new ArrayList();
        List<String> b = b(valueL);
        arrayList.add("K045");
        arrayList.addAll(b);
        arrayList.add("K021");
        List<String> b2 = b(valueH);
        b2.add("I013");
        arrayList.addAll(b2);
        String[] strArr = new String[arrayList.size()];
        for (int i = 0; i < arrayList.size(); i++) {
            strArr[i] = c((String) arrayList.get(i));
        }
        return strArr;
    }

    public int c(TargetConfig targetConfig, int i, boolean z) {
        int h = CommonUtils.h(targetConfig.getId());
        if (h == 0) {
            return (int) moe.c(i);
        }
        if (h == 1 || h == 4 || h == 14 || h == 17 || h == 7 || h == 8) {
            i = ghp.b(ftd.e().b(), h, i, z);
        }
        LogUtil.a("Suggestion_FitRunVoiceBaseContentConstructor", "dealZoneLimitValue tempValue:", Integer.valueOf(i), "and isZoneLower:", Boolean.valueOf(z), " intensityId:", Integer.valueOf(h));
        return i;
    }

    private String[] d(int i) {
        String[] strArr = new String[2];
        int i2 = i / 60;
        int i3 = i % 60;
        if (i3 >= 10) {
            strArr[1] = "H0" + i3;
        } else if (i3 == 0) {
            strArr[1] = "H999";
        } else {
            strArr[1] = "H00" + i3;
        }
        if (i2 < 10) {
            strArr[0] = "H00" + i2;
        } else if (i2 <= 60) {
            strArr[0] = "H0" + i2;
        } else {
            LogUtil.b("Suggestion_FitRunVoiceBaseContentConstructor", "MEASUREMENT TYPE_TIME minute = ", Integer.valueOf(i2));
            return null;
        }
        return strArr;
    }

    private String e(int i) {
        if (i >= 10) {
            return "H0" + i;
        }
        if (i == 0) {
            return "H999";
        }
        return "H00" + i;
    }

    private List<String> b(int i) {
        if (!LanguageUtil.j(BaseApplication.getContext())) {
            return a(i);
        }
        ArrayList arrayList = new ArrayList();
        if (i < 1000) {
            a(i, arrayList);
        } else if (i < 10000) {
            int i2 = i / 1000;
            int i3 = i % 1000;
            arrayList.add("H00" + i2);
            arrayList.add("I005");
            if (i3 != 0) {
                if (i3 / 100 == 0) {
                    arrayList.add("H999");
                }
                a(i3, arrayList);
            }
        } else {
            LogUtil.a("Suggestion_FitRunVoiceBaseContentConstructor", "multiNumberAudio dealValue is greater than 10000");
        }
        return arrayList;
    }

    private void a(int i, List<String> list) {
        if (i <= 60) {
            list.add(e(i));
            return;
        }
        if (i < 100) {
            int i2 = i / 10;
            int i3 = i % 10;
            list.add("H00" + i2);
            list.add("H010");
            if (i3 != 0) {
                list.add("H00" + i3);
                return;
            }
            return;
        }
        int i4 = i / 100;
        int i5 = (i / 10) % 10;
        int i6 = i % 10;
        list.add("H00" + i4);
        list.add("I004");
        if (i5 == 0 && i6 != 0) {
            list.add("H999");
        }
        if (i5 != 0) {
            list.add(e(i5));
            list.add("H010");
        }
        if (i6 != 0) {
            list.add("H00" + i6);
        }
    }

    private List<String> a(int i) {
        LogUtil.a("Suggestion_FitRunVoiceBaseContentConstructor", "multiNumberAudioForEnglish dealValue:", Integer.valueOf(i));
        ArrayList arrayList = new ArrayList();
        if (i < 1000) {
            e(i, arrayList);
        } else if (i < 10000) {
            int i2 = i / 1000;
            int i3 = i % 1000;
            arrayList.add("H00" + i2);
            arrayList.add("I005");
            if (i3 != 0) {
                if (i3 / 100 == 0) {
                    arrayList.add("K021");
                }
                e(i3, arrayList);
            }
        } else {
            LogUtil.a("Suggestion_FitRunVoiceBaseContentConstructor", "multiNumberAudioForEnglish dealValue is greater than 10000");
        }
        return arrayList;
    }

    private void e(int i, List<String> list) {
        if (i < 100) {
            b(i, list);
            return;
        }
        list.add("H00" + (i / 100));
        list.add("I004");
        if ((i / 10) % 10 == 0 && i % 10 == 0) {
            return;
        }
        list.add("K021");
        b(i % 100, list);
    }

    private void b(int i, List<String> list) {
        LogUtil.a("Suggestion_FitRunVoiceBaseContentConstructor", "twoDigitsForEnglish dealValue", Integer.valueOf(i));
        if (i <= 60) {
            list.add(e(i));
            return;
        }
        if (i < 100) {
            int i2 = i / 10;
            int i3 = i % 10;
            switch (i2) {
                case 6:
                    list.add("H060");
                    break;
                case 7:
                    list.add("H061");
                    break;
                case 8:
                    list.add("H062");
                    break;
                case 9:
                    list.add("H063");
                    break;
            }
            if (i3 != 0) {
                list.add("H00" + i3);
                return;
            }
            return;
        }
        LogUtil.h("Suggestion_FitRunVoiceBaseContentConstructor", "twoDigitsForEnglish error value:", Integer.valueOf(i));
    }

    public static String b(String str) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("Suggestion_FitRunVoiceBaseContentConstructor", "getAudiosFilePath() audioId is null");
            return "";
        }
        return squ.c(str);
    }

    public String c(String str) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("Suggestion_FitRunVoiceBaseContentConstructor", "getAudiosBaseFilePath() audioId is null");
            return "";
        }
        return c(str, this.b, this.e, this.c);
    }

    private String c(String str, String str2, String str3, String str4) {
        return squ.a(str, str2, str3, str4);
    }
}
