package defpackage;

import android.content.Context;
import com.huawei.health.R;
import com.huawei.hihealth.HiAggregateOption;
import com.huawei.hms.network.embedded.k;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.main.R$string;
import com.huawei.ui.main.stories.fitness.views.coresleep.CoreSleepTotalData;
import health.compact.a.CommonUtil;
import health.compact.a.LanguageUtil;
import health.compact.a.UnitUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* loaded from: classes6.dex */
public class pxb {
    private Context c = BaseApplication.getContext();

    public HiAggregateOption b(long j, int i) {
        long j2;
        long j3;
        long j4;
        LogUtil.a("SleepInteractorHelper", "getBaseTimeHiOption， startTime: ", Long.valueOf(j));
        if (i == 7) {
            j2 = (j - ((i - 7) * k.b.m)) * 1000;
            j4 = 518400;
        } else {
            if (i != 30) {
                LogUtil.h("SleepInteractorHelper", "dayNum: ", Integer.valueOf(i));
                j2 = 0;
                j3 = 0;
                LogUtil.a("SleepInteractorHelper", "optionEndTime: ", Long.valueOf(j3), " optionStartTime:", Long.valueOf(j2));
                return e(j2, j3);
            }
            j2 = (j - ((i - 30) * k.b.m)) * 1000;
            j4 = 2505600;
        }
        j3 = (j4 + j) * 1000;
        LogUtil.a("SleepInteractorHelper", "optionEndTime: ", Long.valueOf(j3), " optionStartTime:", Long.valueOf(j2));
        return e(j2, j3);
    }

    private HiAggregateOption e(long j, long j2) {
        HiAggregateOption hiAggregateOption = new HiAggregateOption();
        hiAggregateOption.setStartTime(j);
        hiAggregateOption.setEndTime(j2);
        hiAggregateOption.setAggregateType(1);
        hiAggregateOption.setConstantsKey(new String[]{"core_sleep_fall_key", "core_sleep_wake_up_key"});
        hiAggregateOption.setType(new int[]{44201, 44202});
        hiAggregateOption.setGroupUnitSize(1);
        hiAggregateOption.setGroupUnitType(3);
        hiAggregateOption.setReadType(0);
        return hiAggregateOption;
    }

    public String i(int i) {
        Context context = BaseApplication.getContext();
        if (i == 3008) {
            return context.getResources().getString(R$string.IDS_sleep_advice_3008, context.getResources().getQuantityString(R.plurals._2130903213_res_0x7f0300ad, 3, 3));
        }
        if (i == 3012) {
            return context.getResources().getString(R$string.IDS_sleep_advice_3012, j(4));
        }
        if (i == 4018) {
            return context.getResources().getString(R$string.IDS_sleep_advice_4018, j(14));
        }
        if (i == 4022) {
            return context.getResources().getString(R$string.IDS_sleep_advice_4022, j(4));
        }
        if (i == 5008) {
            return context.getResources().getString(R$string.IDS_sleep_advice_5008, context.getResources().getQuantityString(R.plurals._2130903041_res_0x7f030001, 30, 30));
        }
        String string = context.getResources().getString(pwt.e(i));
        return (LanguageUtil.b(BaseApplication.getContext()) && (i == 2002 || i == 2008 || i == 2010 || i == 4026)) ? d(string) : string;
    }

    public static String d(String str) {
        Matcher matcher = Pattern.compile("\\d+", 2).matcher(str);
        String str2 = str;
        while (matcher.find()) {
            String group = matcher.group();
            int m = CommonUtil.m(BaseApplication.getContext(), group);
            if (m == 0) {
                return str;
            }
            str2 = str2.replace(group, UnitUtil.e(m, 1, 0));
        }
        return str2;
    }

    private String r(int i, CoreSleepTotalData coreSleepTotalData) {
        return c(coreSleepTotalData) ? "" : this.c.getResources().getString(pwt.e(i), e(coreSleepTotalData.getResultInfoArr().getCurr().getDays().getOverSleep()), j(10));
    }

    private String t(int i, CoreSleepTotalData coreSleepTotalData) {
        return (c(coreSleepTotalData) || d(coreSleepTotalData)) ? "" : e(i, coreSleepTotalData.getResultInfoArr().getCurr().getDays().getLackSleep(), coreSleepTotalData.getResultInfoArr().getLast().getDays().getLackSleep());
    }

    private String s(int i, CoreSleepTotalData coreSleepTotalData) {
        return (a(coreSleepTotalData) || e(coreSleepTotalData)) ? "" : b(i, coreSleepTotalData.getResultInfoArr().getCurr(), coreSleepTotalData.getResultInfoArr().getLast());
    }

    private String q(int i, CoreSleepTotalData coreSleepTotalData) {
        return c(coreSleepTotalData) ? "" : this.c.getResources().getString(pwt.e(i), e(coreSleepTotalData.getResultInfoArr().getCurr().getDays().getLackSleep()));
    }

    public String g(int i, CoreSleepTotalData coreSleepTotalData) {
        switch (i % 100) {
            case 1:
            case 2:
                return r(i, coreSleepTotalData);
            case 3:
            case 4:
            case 5:
            case 6:
                return t(i, coreSleepTotalData);
            case 7:
            case 8:
                return s(i, coreSleepTotalData);
            case 9:
                return q(i, coreSleepTotalData);
            case 10:
                if (c(coreSleepTotalData)) {
                    return "";
                }
                CoreSleepTotalData.ResultInfoArrBean.CurrBean curr = coreSleepTotalData.getResultInfoArr().getCurr();
                return d(i, curr.getDays().getLackSleep(), curr.getDays().getIrregularFallSleep());
            case 11:
                if (c(coreSleepTotalData)) {
                    return "";
                }
                CoreSleepTotalData.ResultInfoArrBean.CurrBean curr2 = coreSleepTotalData.getResultInfoArr().getCurr();
                return a(i, curr2.getDays().getLackSleep(), curr2.getDays().getIrregularWakeUp());
            case 12:
                return (c(coreSleepTotalData) || a(coreSleepTotalData)) ? "" : b(i, coreSleepTotalData.getResultInfoArr().getCurr());
            case 13:
                return (c(coreSleepTotalData) || a(coreSleepTotalData)) ? "" : c(i, coreSleepTotalData.getResultInfoArr().getCurr());
            default:
                return this.c.getResources().getString(pwt.e(i));
        }
    }

    private String e(int i, int i2, int i3) {
        return this.c.getResources().getString(pwt.e(i), e(i2), e(i3));
    }

    private String a(int i, int i2, int i3) {
        return this.c.getResources().getString(pwt.e(i), e(i2), e(i3));
    }

    private String d(int i, int i2, int i3) {
        return this.c.getResources().getString(pwt.e(i), e(i2), e(i3));
    }

    private String b(int i, CoreSleepTotalData.ResultInfoArrBean.CurrBean currBean, CoreSleepTotalData.ResultInfoArrBean.LastBean lastBean) {
        return this.c.getResources().getString(pwt.e(i), e(currBean.getDays().getLackSleep()), b(Math.abs(currBean.getMean().getAllSleepTime() - lastBean.getMean().getAllSleepTime())));
    }

    private String b(int i, CoreSleepTotalData.ResultInfoArrBean.CurrBean currBean) {
        return this.c.getResources().getString(pwt.e(i), e(currBean.getDays().getLackSleep()), e(currBean.getDays().getLateFallSleep()), Integer.valueOf(currBean.getMean().getFallSleepTime() / 60));
    }

    private String c(int i, CoreSleepTotalData.ResultInfoArrBean.CurrBean currBean) {
        if (i == 12213) {
            return this.c.getResources().getString(pwt.e(i), e(currBean.getDays().getLowAllSleepLateFallSleep()), Integer.valueOf(currBean.getMean().getFallSleepTime() / 60));
        }
        return this.c.getResources().getString(pwt.e(i), e(currBean.getDays().getLowAllSleepLateFallSleep()), UnitUtil.d(c("00:00")));
    }

    private String l(int i, CoreSleepTotalData coreSleepTotalData) {
        if (a(coreSleepTotalData)) {
            return "";
        }
        int wakeUpTime = coreSleepTotalData.getResultInfoArr().getCurr().getMean().getWakeUpTime();
        return this.c.getResources().getString(pwt.e(i), scn.t(wakeUpTime / 60) + ":" + scn.t(wakeUpTime % 60));
    }

    public String d(int i, CoreSleepTotalData coreSleepTotalData) {
        switch (i % 100) {
            case 1:
            case 2:
            case 3:
            case 4:
                if (c(coreSleepTotalData) || d(coreSleepTotalData)) {
                    return "";
                }
                CoreSleepTotalData.ResultInfoArrBean.CurrBean curr = coreSleepTotalData.getResultInfoArr().getCurr();
                return this.c.getResources().getString(pwt.e(i), e(curr.getDays().getEarlyWakeUp()), e(Math.abs(curr.getDays().getEarlyWakeUp() - coreSleepTotalData.getResultInfoArr().getLast().getDays().getEarlyWakeUp())));
            case 5:
            case 6:
                return k(i, coreSleepTotalData);
            case 7:
                return l(i, coreSleepTotalData);
            case 8:
            case 9:
            case 10:
                if (c(coreSleepTotalData) || d(coreSleepTotalData)) {
                    return "";
                }
                CoreSleepTotalData.ResultInfoArrBean.CurrBean curr2 = coreSleepTotalData.getResultInfoArr().getCurr();
                return this.c.getResources().getString(pwt.e(i), e(curr2.getDays().getEasyAwake()), e(Math.abs(curr2.getDays().getEasyAwake() - coreSleepTotalData.getResultInfoArr().getLast().getDays().getEasyAwake())));
            case 11:
                if (a(coreSleepTotalData)) {
                    return "";
                }
                return this.c.getResources().getString(pwt.e(i), c(coreSleepTotalData.getResultInfoArr().getCurr().getMean().getAwakeCnt()));
            default:
                return this.c.getResources().getString(pwt.e(i));
        }
    }

    private String k(int i, CoreSleepTotalData coreSleepTotalData) {
        if (a(coreSleepTotalData) || e(coreSleepTotalData)) {
            return "";
        }
        CoreSleepTotalData.ResultInfoArrBean.CurrBean curr = coreSleepTotalData.getResultInfoArr().getCurr();
        CoreSleepTotalData.ResultInfoArrBean.LastBean last = coreSleepTotalData.getResultInfoArr().getLast();
        int wakeUpTime = curr.getMean().getWakeUpTime();
        int abs = Math.abs(wakeUpTime - last.getMean().getWakeUpTime());
        return this.c.getResources().getString(pwt.e(i), scn.t(wakeUpTime / 60) + ":" + scn.t(wakeUpTime % 60), b(abs));
    }

    public String b(int i, CoreSleepTotalData coreSleepTotalData) {
        int i2 = i % 100;
        if (i2 == 1) {
            return this.c.getResources().getString(pwt.e(i));
        }
        if (i2 == 2) {
            if (a(coreSleepTotalData) || e(coreSleepTotalData)) {
                return "";
            }
            CoreSleepTotalData.ResultInfoArrBean.CurrBean curr = coreSleepTotalData.getResultInfoArr().getCurr();
            CoreSleepTotalData.ResultInfoArrBean.LastBean last = coreSleepTotalData.getResultInfoArr().getLast();
            return this.c.getResources().getString(pwt.e(i), g(curr.getMean().getScore() - last.getMean().getScore()));
        }
        if (i2 == 3) {
            if (c(coreSleepTotalData)) {
                return "";
            }
            return this.c.getResources().getString(pwt.e(i), e(coreSleepTotalData.getResultInfoArr().getCurr().getDays().getUpperScore()), g(90));
        }
        if (i2 == 4) {
            if (c(coreSleepTotalData) || d(coreSleepTotalData)) {
                return "";
            }
            CoreSleepTotalData.ResultInfoArrBean.CurrBean curr2 = coreSleepTotalData.getResultInfoArr().getCurr();
            CoreSleepTotalData.ResultInfoArrBean.LastBean last2 = coreSleepTotalData.getResultInfoArr().getLast();
            return this.c.getResources().getString(pwt.e(i), e(curr2.getDays().getUpperScore()), g(90), e(last2.getDays().getUpperScore()));
        }
        return this.c.getResources().getString(pwt.e(i));
    }

    private String o(int i, CoreSleepTotalData coreSleepTotalData) {
        return c(coreSleepTotalData) ? "" : this.c.getResources().getString(pwt.e(i), e(coreSleepTotalData.getResultInfoArr().getCurr().getDays().getLowDeepSleep()), UnitUtil.e(20.0d, 2, 0), e(coreSleepTotalData.getResultInfoArr().getCurr().getDays().getIrregularFallSleep()));
    }

    public String h(int i, CoreSleepTotalData coreSleepTotalData) {
        switch (i % 100) {
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
                if (c(coreSleepTotalData) || d(coreSleepTotalData)) {
                    return "";
                }
                return this.c.getResources().getString(pwt.e(i), e(coreSleepTotalData.getResultInfoArr().getCurr().getDays().getLowDeepSleep()), UnitUtil.e(20.0d, 2, 0), e(coreSleepTotalData.getResultInfoArr().getLast().getDays().getLowDeepSleep()));
            case 7:
            case 8:
                return n(i, coreSleepTotalData);
            case 9:
                if (c(coreSleepTotalData)) {
                    return "";
                }
                return this.c.getResources().getString(pwt.e(i), e(coreSleepTotalData.getResultInfoArr().getCurr().getDays().getLowDeepSleep()));
            case 10:
                return o(i, coreSleepTotalData);
            case 11:
                if (c(coreSleepTotalData)) {
                    return "";
                }
                CoreSleepTotalData.ResultInfoArrBean.CurrBean curr = coreSleepTotalData.getResultInfoArr().getCurr();
                return this.c.getResources().getString(pwt.e(i), e(curr.getDays().getLowDeepSleep()), UnitUtil.e(20.0d, 2, 0), e(curr.getDays().getIrregularWakeUp()));
            case 12:
                return m(i, coreSleepTotalData);
            default:
                return this.c.getResources().getString(pwt.e(i));
        }
    }

    private String n(int i, CoreSleepTotalData coreSleepTotalData) {
        if (a(coreSleepTotalData) || e(coreSleepTotalData) || c(coreSleepTotalData)) {
            return "";
        }
        return this.c.getResources().getString(pwt.e(i), e(coreSleepTotalData.getResultInfoArr().getCurr().getDays().getLowDeepSleep()), UnitUtil.e(20.0d, 2, 0), UnitUtil.e(Math.abs(r0.getMean().getDeepSleepScale() - coreSleepTotalData.getResultInfoArr().getLast().getMean().getDeepSleepScale()), 2, 0));
    }

    private String m(int i, CoreSleepTotalData coreSleepTotalData) {
        if (c(coreSleepTotalData) || a(coreSleepTotalData)) {
            return "";
        }
        CoreSleepTotalData.ResultInfoArrBean.CurrBean curr = coreSleepTotalData.getResultInfoArr().getCurr();
        return this.c.getResources().getString(pwt.e(i), e(curr.getDays().getLateFallSleep()), Integer.valueOf(curr.getMean().getFallSleepTime() / 60), UnitUtil.e(20.0d, 2, 0));
    }

    public String e(int i, CoreSleepTotalData coreSleepTotalData) {
        switch (i % 100) {
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
                if (c(coreSleepTotalData) || d(coreSleepTotalData)) {
                    return "";
                }
                return this.c.getResources().getString(pwt.e(i), e(coreSleepTotalData.getResultInfoArr().getCurr().getDays().getLowBreathQuality()), e(coreSleepTotalData.getResultInfoArr().getLast().getDays().getLowBreathQuality()));
            case 6:
            case 7:
                if (c(coreSleepTotalData) || a(coreSleepTotalData) || e(coreSleepTotalData)) {
                    return "";
                }
                CoreSleepTotalData.ResultInfoArrBean.CurrBean curr = coreSleepTotalData.getResultInfoArr().getCurr();
                return this.c.getResources().getString(pwt.e(i), e(curr.getDays().getLowBreathQuality()), g(Math.abs(curr.getMean().getBreathQuality() - coreSleepTotalData.getResultInfoArr().getLast().getMean().getBreathQuality())));
            case 8:
                if (c(coreSleepTotalData)) {
                    return "";
                }
                return this.c.getResources().getString(pwt.e(i), e(coreSleepTotalData.getResultInfoArr().getCurr().getDays().getLowBreathQuality()));
            default:
                return this.c.getResources().getString(pwt.e(i));
        }
    }

    public String c(int i, CoreSleepTotalData coreSleepTotalData) {
        switch (i % 100) {
            case 1:
            case 2:
                if (c(coreSleepTotalData)) {
                    return "";
                }
                return this.c.getResources().getString(pwt.e(i), e(coreSleepTotalData.getResultInfoArr().getCurr().getDays().getOverREMSleep()));
            case 3:
            case 4:
                if (c(coreSleepTotalData) || d(coreSleepTotalData)) {
                    return "";
                }
                return this.c.getResources().getString(pwt.e(i), e(coreSleepTotalData.getResultInfoArr().getCurr().getDays().getOverREMSleep()), e(coreSleepTotalData.getResultInfoArr().getLast().getDays().getOverREMSleep()));
            case 5:
            case 6:
                if (c(coreSleepTotalData)) {
                    return "";
                }
                return this.c.getResources().getString(pwt.e(i), e(coreSleepTotalData.getResultInfoArr().getCurr().getDays().getLowREMSleep()));
            case 7:
                if (c(coreSleepTotalData) || a(coreSleepTotalData)) {
                    return "";
                }
                CoreSleepTotalData.ResultInfoArrBean.CurrBean curr = coreSleepTotalData.getResultInfoArr().getCurr();
                return this.c.getResources().getString(pwt.e(i), e(curr.getDays().getOverREMLateFallSleep()), Integer.valueOf(curr.getMean().getFallSleepTime() / 60));
            default:
                return this.c.getResources().getString(pwt.e(i));
        }
    }

    public String a(int i, CoreSleepTotalData coreSleepTotalData) {
        CoreSleepTotalData.ResultInfoArrBean.CurrBean.MinBean minBean;
        CoreSleepTotalData.ResultInfoArrBean.CurrBean.MaxBean maxBean = null;
        CoreSleepTotalData.ResultInfoArrBean.CurrBean curr = coreSleepTotalData.getResultInfoArr() != null ? coreSleepTotalData.getResultInfoArr().getCurr() : null;
        if (curr != null) {
            maxBean = curr.getMax();
            minBean = curr.getMin();
        } else {
            minBean = null;
        }
        switch (i % 100) {
            case 1:
                return (maxBean == null || minBean == null) ? "" : this.c.getResources().getString(pwt.e(i), a(maxBean.getAllSleepTime(), this.c), a(minBean.getAllSleepTime(), this.c));
            case 2:
            case 3:
                if (maxBean == null || minBean == null) {
                    return "";
                }
                if (maxBean.getDeepSleepScale() - minBean.getDeepSleepScale() < 5) {
                    return this.c.getResources().getString(R$string.IDS_details_sleep_content_4_no_harvard);
                }
                return this.c.getResources().getString(pwt.e(i), UnitUtil.e(maxBean.getDeepSleepScale(), 2, 0), UnitUtil.e(minBean.getDeepSleepScale(), 2, 0));
            case 4:
                return (maxBean == null || minBean == null) ? "" : this.c.getResources().getString(pwt.e(i), UnitUtil.e(maxBean.getREMScale(), 2, 0), UnitUtil.e(minBean.getREMScale(), 2, 0));
            case 5:
            case 6:
                return (maxBean == null || minBean == null) ? "" : this.c.getResources().getString(pwt.e(i), Integer.valueOf(maxBean.getBreathQuality()), Integer.valueOf(minBean.getBreathQuality()));
            default:
                return this.c.getResources().getString(pwt.e(i));
        }
    }

    public String a(int i, CoreSleepTotalData coreSleepTotalData, int i2, int i3) {
        switch (i % 100) {
            case 1:
                return j(i, coreSleepTotalData);
            case 2:
                return d(i, coreSleepTotalData, i2);
            case 3:
                return i(i, coreSleepTotalData);
            case 4:
                return f(i, coreSleepTotalData);
            case 5:
            case 6:
            case 7:
                if (a(coreSleepTotalData)) {
                    return "";
                }
                return this.c.getResources().getString(pwt.e(i), UnitUtil.e(coreSleepTotalData.getResultInfoArr().getCurr().getMean().getDeepSleepScale(), 2, 0));
            case 8:
            case 9:
            case 10:
                return this.c.getResources().getString(pwt.e(i), a(i2, this.c));
            case 11:
            case 12:
                return this.c.getResources().getString(pwt.e(i), g(i3));
            case 13:
            case 14:
                if (a(coreSleepTotalData)) {
                    return "";
                }
                return this.c.getResources().getString(pwt.e(i), UnitUtil.e(coreSleepTotalData.getResultInfoArr().getCurr().getMean().getREMScale(), 2, 0));
            case 15:
                return this.c.getResources().getString(pwt.e(i), g(i3), a(i2, this.c));
            default:
                return this.c.getResources().getString(pwt.e(i));
        }
    }

    private String f(int i, CoreSleepTotalData coreSleepTotalData) {
        if (a(coreSleepTotalData) || e(coreSleepTotalData)) {
            return "";
        }
        return this.c.getResources().getString(pwt.e(i), g(coreSleepTotalData.getResultInfoArr().getCurr().getMean().getScore() - coreSleepTotalData.getResultInfoArr().getLast().getMean().getScore()), UnitUtil.e(r0.getMean().getREMScale(), 2, 0));
    }

    private String i(int i, CoreSleepTotalData coreSleepTotalData) {
        if (a(coreSleepTotalData) || e(coreSleepTotalData)) {
            return "";
        }
        CoreSleepTotalData.ResultInfoArrBean.CurrBean curr = coreSleepTotalData.getResultInfoArr().getCurr();
        CoreSleepTotalData.ResultInfoArrBean.LastBean last = coreSleepTotalData.getResultInfoArr().getLast();
        return this.c.getResources().getString(pwt.e(i), g(curr.getMean().getScore() - last.getMean().getScore()));
    }

    private String d(int i, CoreSleepTotalData coreSleepTotalData, int i2) {
        if (a(coreSleepTotalData) || e(coreSleepTotalData)) {
            return "";
        }
        CoreSleepTotalData.ResultInfoArrBean.CurrBean curr = coreSleepTotalData.getResultInfoArr().getCurr();
        CoreSleepTotalData.ResultInfoArrBean.LastBean last = coreSleepTotalData.getResultInfoArr().getLast();
        return this.c.getResources().getString(pwt.e(i), g(curr.getMean().getScore() - last.getMean().getScore()), a(i2, this.c));
    }

    private String j(int i, CoreSleepTotalData coreSleepTotalData) {
        if (a(coreSleepTotalData) || e(coreSleepTotalData)) {
            return "";
        }
        return this.c.getResources().getString(pwt.e(i), g(coreSleepTotalData.getResultInfoArr().getCurr().getMean().getScore() - coreSleepTotalData.getResultInfoArr().getLast().getMean().getScore()), UnitUtil.e(r0.getMean().getDeepSleepScale(), 2, 0));
    }

    private String j(int i) {
        return BaseApplication.getContext().getResources().getQuantityString(R.plurals._2130903223_res_0x7f0300b7, i, UnitUtil.e(i, 1, 0));
    }

    public String b(int i) {
        return BaseApplication.getContext().getResources().getQuantityString(R.plurals._2130903041_res_0x7f030001, i, Integer.valueOf(i));
    }

    public String e(int i) {
        return String.format(BaseApplication.getContext().getResources().getQuantityString(R.plurals.IDS_user_profile_achieve_num_day, i), UnitUtil.e(i, 1, 0));
    }

    public String c(int i) {
        return BaseApplication.getContext().getResources().getQuantityString(R.plurals._2130903213_res_0x7f0300ad, i, UnitUtil.e(i, 1, 0));
    }

    private String g(int i) {
        return BaseApplication.getContext().getResources().getQuantityString(R.plurals._2130903221_res_0x7f0300b5, i, UnitUtil.e(i, 1, 0));
    }

    private int c(String str) {
        String[] split = str.split(":");
        return (CommonUtil.h(split[0]) * 3600) + (CommonUtil.h(split[1]) * 60);
    }

    private String a(int i, Context context) {
        return String.format(context.getResources().getString(R$string.IDS_h_min_unit), UnitUtil.e(i / 60, 1, 0), UnitUtil.e(i % 60, 1, 0));
    }

    public static Map<String, Integer> b(List<pvz> list) {
        pvz pvzVar;
        if (list == null) {
            return new HashMap(16);
        }
        ArrayList arrayList = new ArrayList(list);
        ArrayList arrayList2 = new ArrayList(16);
        HashMap hashMap = new HashMap(16);
        int i = 0;
        while (i < arrayList.size() && (pvzVar = (pvz) arrayList.get(i)) != null) {
            if (hashMap.get("core_sleep_start_time_key") == null && pvzVar.b() != 69 && pvzVar.b() != 67) {
                hashMap.put("core_sleep_start_time_key", Integer.valueOf(pvzVar.e()));
            }
            if (i == arrayList.size() - 1) {
                if (arrayList.size() == 1) {
                    hashMap.put("core_sleep_end_time_key", Integer.valueOf(pvzVar.c()));
                }
                if (hashMap.get("core_sleep_start_time_key") != null && hashMap.get("core_sleep_end_time_key") != null) {
                    arrayList2.add(hashMap);
                }
            }
            int i2 = i + 1;
            if (arrayList.size() <= i2) {
                break;
            }
            pvz pvzVar2 = (pvz) arrayList.get(i2);
            if (pvzVar2 != null) {
                if (pvzVar2.e() - pvzVar.c() > 30 && pvzVar.b() != 69) {
                    hashMap.put("core_sleep_end_time_key", Integer.valueOf(pvzVar.c()));
                    if (hashMap.get("core_sleep_start_time_key") != null && hashMap.get("core_sleep_end_time_key") != null) {
                        arrayList2.add(hashMap);
                        hashMap = new HashMap(16);
                    }
                } else if (pvzVar2.b() != 69) {
                    hashMap.put("core_sleep_end_time_key", Integer.valueOf(pvzVar2.c()));
                } else {
                    LogUtil.h("SleepInteractorHelper", "getSleepSegmentedMaps takeHistogramHeight = 69, i = ", Integer.valueOf(i));
                }
            }
            i = i2;
        }
        return d(arrayList2);
    }

    private static Map<String, Integer> d(List<Map<String, Integer>> list) {
        HashMap hashMap = new HashMap(16);
        if (koq.b(list)) {
            return null;
        }
        Map<String, Integer> map = list.get(0);
        for (Map<String, Integer> map2 : list) {
            if (map2.get("core_sleep_end_time_key").intValue() - map2.get("core_sleep_start_time_key").intValue() > map.get("core_sleep_end_time_key").intValue() - map.get("core_sleep_start_time_key").intValue()) {
                map = map2;
            }
        }
        hashMap.put("core_sleep_start_time_key", map.get("core_sleep_start_time_key"));
        hashMap.put("core_sleep_end_time_key", map.get("core_sleep_end_time_key"));
        return hashMap;
    }

    private boolean a(CoreSleepTotalData coreSleepTotalData) {
        return coreSleepTotalData == null || coreSleepTotalData.getResultInfoArr() == null || coreSleepTotalData.getResultInfoArr().getCurr() == null || coreSleepTotalData.getResultInfoArr().getCurr().getMean() == null;
    }

    private boolean c(CoreSleepTotalData coreSleepTotalData) {
        return coreSleepTotalData == null || coreSleepTotalData.getResultInfoArr() == null || coreSleepTotalData.getResultInfoArr().getCurr() == null || coreSleepTotalData.getResultInfoArr().getCurr().getDays() == null;
    }

    private boolean d(CoreSleepTotalData coreSleepTotalData) {
        return coreSleepTotalData == null || coreSleepTotalData.getResultInfoArr() == null || coreSleepTotalData.getResultInfoArr().getLast() == null || coreSleepTotalData.getResultInfoArr().getLast().getDays() == null;
    }

    private boolean e(CoreSleepTotalData coreSleepTotalData) {
        return coreSleepTotalData == null || coreSleepTotalData.getResultInfoArr() == null || coreSleepTotalData.getResultInfoArr().getLast() == null || coreSleepTotalData.getResultInfoArr().getLast().getMean() == null;
    }

    public static int a(int i) {
        if (i >= 0 && i < 240) {
            return 20;
        }
        if (i >= 240 && i < 288) {
            return e(0, new double[]{20.0d, i, 240.0d, 40.0d, 20.0d, 288.0d, 240.0d});
        }
        if (i >= 288 && i < 336) {
            return e(0, new double[]{40.0d, i, 288.0d, 60.0d, 40.0d, 336.0d, 288.0d});
        }
        if (i >= 336 && i < 384) {
            return e(0, new double[]{60.0d, i, 336.0d, 70.0d, 60.0d, 384.0d, 336.0d});
        }
        if (i >= 384 && i < 432) {
            return e(0, new double[]{70.0d, i, 384.0d, 80.0d, 70.0d, 432.0d, 384.0d});
        }
        if (i >= 432 && i < 480) {
            return e(0, new double[]{80.0d, i, 432.0d, 90.0d, 80.0d, 480.0d, 432.0d});
        }
        if (i >= 480 && i < 504) {
            return e(0, new double[]{90.0d, i, 480.0d, 100.0d, 90.0d, 504.0d, 480.0d});
        }
        if (i >= 504 && i < 516) {
            return e(0, new double[]{100.0d, i, 504.0d, 90.0d, 100.0d, 516.0d, 504.0d});
        }
        if (i >= 516 && i < 528) {
            return e(0, new double[]{90.0d, i, 516.0d, 80.0d, 90.0d, 528.0d, 516.0d});
        }
        if (i >= 528 && i < 570) {
            return e(0, new double[]{80.0d, i, 528.0d, 70.0d, 80.0d, 570.0d, 528.0d});
        }
        if (i >= 570 && i < 600) {
            return e(0, new double[]{70.0d, i, 570.0d, 60.0d, 70.0d, 600.0d, 570.0d});
        }
        return e(0, new double[]{60.0d, i, 600.0d, 40.0d, 60.0d, 1440.0d, 600.0d});
    }

    public static int a(float f) {
        if (f >= 0.0f && f < 0.5f) {
            return 20;
        }
        if (f >= 0.5f && f < 0.6f) {
            return e(1, new double[]{f, 0.5d, 40.0d, 20.0d, 0.6000000238418579d, 0.5d, 20.0d});
        }
        if (f >= 0.6f && f < 0.8f) {
            return e(1, new double[]{f, 0.6000000238418579d, 60.0d, 40.0d, 0.800000011920929d, 0.6000000238418579d, 40.0d});
        }
        if (f >= 0.8f && f < 0.85f) {
            return e(1, new double[]{f, 0.800000011920929d, 70.0d, 60.0d, 0.8500000238418579d, 0.800000011920929d, 60.0d});
        }
        if (f >= 0.85f && f < 0.9f) {
            return e(1, new double[]{f, 0.8500000238418579d, 80.0d, 70.0d, 0.8999999761581421d, 0.8500000238418579d, 70.0d});
        }
        if (f >= 0.9f && f < 0.95f) {
            return e(1, new double[]{f, 0.8999999761581421d, 90.0d, 80.0d, 0.949999988079071d, 0.8999999761581421d, 80.0d});
        }
        if (f >= 0.95f) {
            return e(1, new double[]{f, 0.949999988079071d, 100.0d, 90.0d, 1.0d, 0.949999988079071d, 90.0d});
        }
        LogUtil.a("SleepInteractorHelper", "effieiceny wrong !");
        return 100;
    }

    public static int d(int i) {
        if (i < 0) {
            return 0;
        }
        if (i >= 0 && i < 10) {
            return e(0, new double[]{100.0d, i, 0.0d, 90.0d, 100.0d, 10.0d, 0.0d});
        }
        if (i >= 10 && i < 20) {
            return e(0, new double[]{90.0d, i, 10.0d, 80.0d, 90.0d, 20.0d, 10.0d});
        }
        if (i >= 20 && i < 30) {
            return e(0, new double[]{80.0d, i, 20.0d, 70.0d, 80.0d, 30.0d, 20.0d});
        }
        if (i >= 30 && i < 60) {
            return e(0, new double[]{70.0d, i, 30.0d, 60.0d, 70.0d, 60.0d, 30.0d});
        }
        if (i < 60 || i >= 120) {
            return 40;
        }
        return e(0, new double[]{60.0d, i, 60.0d, 40.0d, 60.0d, 120.0d, 60.0d});
    }

    private static int e(int i, double[] dArr) {
        double d;
        if (i == 0) {
            d = dArr[0] + (((dArr[1] - dArr[2]) * (dArr[3] - dArr[4])) / (dArr[5] - dArr[6]));
        } else {
            if (i != 1) {
                return 0;
            }
            d = (((dArr[0] - dArr[1]) * (dArr[2] - dArr[3])) / (dArr[4] - dArr[5])) + dArr[6];
        }
        return (int) d;
    }
}
