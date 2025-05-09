package com.huawei.sleep.sleepmgmt;

/* loaded from: classes6.dex */
public class SleepMgmtInterface {
    public static native String DailyProcessInterface(String str);

    public static native String GetSleepMgmtVersion();

    public static native String MonthlyProcessInterface(String str);

    public static native String QuestionnaireProcessInterface(String str);

    public static native String SleepAdviceOriginalInterface(String str, String str2);

    static {
        System.loadLibrary("shlsleepmgmt");
    }
}
