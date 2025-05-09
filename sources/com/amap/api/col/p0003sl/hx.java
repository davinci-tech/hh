package com.amap.api.col.p0003sl;

import com.amap.api.col.p0003sl.hw;

/* loaded from: classes2.dex */
public final class hx {

    /* renamed from: a, reason: collision with root package name */
    public final hw.c f1161a;
    public final String b;

    hx(hw.c cVar, hz hzVar) {
        String format;
        this.f1161a = cVar;
        switch (AnonymousClass1.f1162a[cVar.ordinal()]) {
            case 1:
                format = String.format("***确保调用SDK任何接口前先调用更新隐私合规updatePrivacyShow、updatePrivacyAgree两个接口并且参数值都为true，若未正确设置有崩溃风险***\n使用%s SDK 功能前请设置隐私权政策是否弹窗告知用户", hzVar.a());
                break;
            case 2:
                format = String.format("***确保调用SDK任何接口前先调用更新隐私合规updatePrivacyShow、updatePrivacyAgree两个接口并且参数值都为true，若未正确设置有崩溃风险***\n使用%s SDK 功能前请确保隐私权政策已弹窗告知用户", hzVar.a());
                break;
            case 3:
                format = String.format("***确保调用SDK任何接口前先调用更新隐私合规updatePrivacyShow、updatePrivacyAgree两个接口并且参数值都为true，若未正确设置有崩溃风险***\n使用%s SDK 功能前请确保设置隐私权政策是否包含高德开平隐私权政策", hzVar.a());
                break;
            case 4:
                format = String.format("***确保调用SDK任何接口前先调用更新隐私合规updatePrivacyShow、updatePrivacyAgree两个接口并且参数值都为true，若未正确设置有崩溃风险***\n使用%s SDK 功能前请确保隐私权政策已经包含高德开平隐私权政策", hzVar.a());
                break;
            case 5:
                format = String.format("***确保调用SDK任何接口前先调用更新隐私合规updatePrivacyShow、updatePrivacyAgree两个接口并且参数值都为true，若未正确设置有崩溃风险***\n使用%s SDK 功能前请确保设置隐私权政策是否取得用户同意", hzVar.a());
                break;
            case 6:
                format = String.format("***确保调用SDK任何接口前先调用更新隐私合规updatePrivacyShow、updatePrivacyAgree两个接口并且参数值都为true，若未正确设置有崩溃风险***\n使用%s SDK 功能前请确保隐私权政策已取得用户同意", hzVar.a());
                break;
            case 7:
                format = String.format("***确保调用SDK任何接口前先调用更新隐私合规updatePrivacyShow、updatePrivacyAgree两个接口并且参数值都为true，若未正确设置有崩溃风险***\n使用%s SDK 功能使用前请确保已经正确设置apiKey，如有疑问请在高德开放平台官网中搜索【INVALID_USER_KEY】相关内容进行解决。", hzVar.a());
                break;
            case 8:
                format = String.format("***确保调用SDK任何接口前先调用更新隐私合规updatePrivacyShow、updatePrivacyAgree两个接口并且参数值都为true，若未正确设置有崩溃风险***\n参数非法，context 或 sdkInfo为空", new Object[0]);
                break;
            case 9:
                format = String.format("设置隐私政策成功", new Object[0]);
                break;
            default:
                format = "";
                break;
        }
        this.b = format;
    }

    /* renamed from: com.amap.api.col.3sl.hx$1, reason: invalid class name */
    static final /* synthetic */ class AnonymousClass1 {

        /* renamed from: a, reason: collision with root package name */
        static final /* synthetic */ int[] f1162a;

        static {
            int[] iArr = new int[hw.c.values().length];
            f1162a = iArr;
            try {
                iArr[hw.c.ShowUnknowCode.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f1162a[hw.c.ShowNoShowCode.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f1162a[hw.c.InfoUnknowCode.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f1162a[hw.c.InfoNotContainCode.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f1162a[hw.c.AgreeUnknowCode.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f1162a[hw.c.AgreeNotAgreeCode.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                f1162a[hw.c.InvaildUserKeyCode.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                f1162a[hw.c.IllegalArgument.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                f1162a[hw.c.SuccessCode.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
        }
    }
}
