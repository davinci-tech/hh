package defpackage;

import android.content.Context;
import android.text.SpannableString;
import android.text.TextPaint;
import android.text.style.ClickableSpan;
import android.view.View;
import androidx.core.content.ContextCompat;
import com.huawei.haf.application.BaseApplication;
import com.huawei.health.R;
import com.huawei.health.sleep.sleepviewdata.SleepViewConstants;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.dialog.CustomViewDialog;
import com.huawei.ui.main.R$string;
import com.huawei.ui.main.stories.health.views.FitnessDataOriginView;
import com.huawei.ui.main.stories.privacy.template.model.bean.PageModelArgs;
import com.huawei.ui.main.stories.privacy.template.view.showdata.PrivacyDataModelActivity;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

/* loaded from: classes9.dex */
public class qnj {
    public static void c(String str, int i, List<pwb> list, CustomViewDialog.Builder builder, final Context context) {
        SpannableString spannableString;
        String h;
        SleepViewConstants.SleepTypeEnum sleepTypeEnum;
        String str2;
        FitnessDataOriginView fitnessDataOriginView = new FitnessDataOriginView(context, (Boolean) true);
        fitnessDataOriginView.setIsSleepType();
        SleepViewConstants.SleepTypeEnum sleepTypeEnum2 = SleepViewConstants.SleepTypeEnum.SCIENCE;
        boolean z = i != 1;
        int[] iArr = new int[4];
        String str3 = "";
        for (pwb pwbVar : list) {
            boolean booleanValue = pwbVar.b() instanceof Boolean ? ((Boolean) pwbVar.b()).booleanValue() : true;
            b(pwbVar, iArr);
            if (str.equals(pwbVar.e()) && z == booleanValue) {
                if (pwbVar.c() == 32) {
                    if (pwbVar.a() != null) {
                        str2 = pwbVar.a();
                    } else {
                        str2 = nsf.h(R$string.IDS_origin_phone);
                    }
                    sleepTypeEnum = SleepViewConstants.SleepTypeEnum.PHONE;
                } else {
                    if (pwbVar.e() != null && pwbVar.e().equals("-1")) {
                        str2 = nsf.h(R$string.IDS_manual_sleep_manual_sleep_input);
                        sleepTypeEnum = SleepViewConstants.SleepTypeEnum.MANUAL;
                    } else {
                        if (pwbVar.d() != null) {
                            h = pwbVar.d();
                        } else {
                            h = nsf.h(R$string.IDS_hw_data_origin_unknow_device);
                        }
                        String str4 = h;
                        sleepTypeEnum = sleepTypeEnum2;
                        str2 = str4;
                    }
                    if (!z) {
                        sleepTypeEnum = SleepViewConstants.SleepTypeEnum.COMMON;
                    }
                }
                SleepViewConstants.SleepTypeEnum sleepTypeEnum3 = sleepTypeEnum;
                str3 = str2;
                sleepTypeEnum2 = sleepTypeEnum3;
            }
        }
        String h2 = nsf.h(R$string.IDS_manual_sleep_more_data_module_name);
        String c = c(sleepTypeEnum2, str3, h2, iArr);
        int indexOf = c.indexOf(h2);
        int length = h2.length() + indexOf;
        int i2 = length - 1;
        if (indexOf == -1 || i2 == -1 || indexOf == c.length() - 1) {
            spannableString = null;
        } else {
            spannableString = new SpannableString(c);
            spannableString.setSpan(new ClickableSpan() { // from class: qnj.5
                @Override // android.text.style.ClickableSpan
                public void onClick(View view) {
                    PrivacyDataModelActivity.b(context, new PageModelArgs(103, "PrivacyDataConstructor", 3, 1));
                    ViewClickInstrumentation.clickOnView(view);
                }

                @Override // android.text.style.ClickableSpan, android.text.style.CharacterStyle
                public void updateDrawState(TextPaint textPaint) {
                    super.updateDrawState(textPaint);
                    textPaint.setColor(ContextCompat.getColor(BaseApplication.e(), R.color._2131299093_res_0x7f090b15));
                    textPaint.setUnderlineText(false);
                }
            }, indexOf, length, 18);
        }
        if (spannableString != null) {
            fitnessDataOriginView.setInfoTextSpannableString(spannableString);
        } else {
            fitnessDataOriginView.setInfoText(c);
        }
        fitnessDataOriginView.setmListdata(list);
        builder.a(nsf.h(R$string.IDS_sleep_data_source)).czg_(fitnessDataOriginView).cze_(R$string.IDS_common_notification_know_tips, new View.OnClickListener() { // from class: qng
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                qnj.dGl_(view);
            }
        });
    }

    static /* synthetic */ void dGl_(View view) {
        LogUtil.a("SleepDayBarChartUtils", "showFitnessDataOriginDialog() PositiveButton onClick.");
        ViewClickInstrumentation.clickOnView(view);
    }

    public static void b(pwb pwbVar, int[] iArr) {
        if (pwbVar == null || iArr == null || iArr.length != 4) {
            LogUtil.b("SleepDayBarChartUtils", "wrong input");
            return;
        }
        if (pwbVar.e() != null && pwbVar.e().equals("-1")) {
            iArr[2] = iArr[2] + 1;
            return;
        }
        if (pwbVar.b() == null) {
            iArr[1] = iArr[1] + 1;
            return;
        }
        if (pwbVar.b() instanceof Boolean) {
            if (((Boolean) pwbVar.b()).booleanValue()) {
                iArr[0] = iArr[0] + 1;
                return;
            } else {
                iArr[3] = iArr[3] + 1;
                return;
            }
        }
        LogUtil.b("SleepDayBarChartUtils", "wrong device Type", pwbVar);
    }

    /* renamed from: qnj$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] b;

        static {
            int[] iArr = new int[SleepViewConstants.SleepTypeEnum.values().length];
            b = iArr;
            try {
                iArr[SleepViewConstants.SleepTypeEnum.MANUAL.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                b[SleepViewConstants.SleepTypeEnum.COMMON.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                b[SleepViewConstants.SleepTypeEnum.PHONE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                b[SleepViewConstants.SleepTypeEnum.SCIENCE.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    public static String c(SleepViewConstants.SleepTypeEnum sleepTypeEnum, String str, String str2, int[] iArr) {
        String h;
        int i = AnonymousClass1.b[sleepTypeEnum.ordinal()];
        if (i == 1) {
            int i2 = iArr[2];
            if (i2 == 1) {
                h = String.format(Locale.ENGLISH, nsf.h(R$string.IDS_manual_sleep_more_data_manual_new), str2);
            } else if (i2 > 1) {
                h = nsf.h(R$string.IDS_sleep_data_source_multi_manual_device);
            } else {
                LogUtil.b("SleepDayBarChartUtils", "MANUAL wrong size:", Integer.valueOf(i2));
                h = "";
            }
        } else if (i == 2) {
            int i3 = iArr[3];
            if (i3 == 1) {
                h = String.format(Locale.ENGLISH, nsf.h(R$string.IDS_manual_sleep_more_data_standard_new), str, str2);
            } else if (i3 > 1) {
                h = nsf.h(R$string.IDS_sleep_data_source_multi_common_device);
            } else {
                LogUtil.b("SleepDayBarChartUtils", "COMMON wrong size:", Integer.valueOf(i3));
                h = "";
            }
        } else if (i != 3) {
            if (i == 4) {
                int i4 = iArr[0];
                if (i4 == 1) {
                    h = b(str);
                } else if (i4 > 1) {
                    h = nsf.h(R$string.IDS_sleep_data_source_multi_wearable_device);
                } else {
                    LogUtil.b("SleepDayBarChartUtils", "SCIENCE wrong size:", Integer.valueOf(i4));
                }
            } else {
                LogUtil.b("SleepDayBarChartUtils", "error sleepType");
            }
            h = "";
        } else {
            int i5 = iArr[1];
            if (i5 == 1) {
                h = b(str);
            } else if (i5 > 1) {
                h = nsf.h(R$string.IDS_sleep_data_source_multi_phone_device);
            } else {
                LogUtil.b("SleepDayBarChartUtils", "PHONE wrong size:", Integer.valueOf(i5));
                h = "";
            }
        }
        LogUtil.a("SleepDayBarChartUtils", "sleepType: ", sleepTypeEnum, " finalString:", h, " deviceStatistics: ", Arrays.toString(iArr));
        return h;
    }

    private static String b(String str) {
        String h = nsf.h(R$string.IDS_detail_title_txtsleep_value);
        return String.format(Locale.ENGLISH, nsf.h(R$string.IDS_manual_sleep_more_data), str, h);
    }
}
