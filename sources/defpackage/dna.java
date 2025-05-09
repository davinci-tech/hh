package defpackage;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.haf.application.BaseApplication;
import com.huawei.health.featuremarketing.db.MarketingEventDbMgr;
import com.huawei.health.marketing.datatype.CycleRuleBase;
import com.huawei.health.marketing.datatype.ResourceBriefInfo;
import com.huawei.health.marketing.datatype.SpecificDateTimePeriod;
import com.huawei.health.vip.datatypes.UserMemberInfo;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.utils.DateFormatUtil;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.trade.PayApi;
import com.huawei.trade.datatype.RepeatResourceBenefitInfo;
import health.compact.a.CommonUtil;
import health.compact.a.Services;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.Calendar;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes3.dex */
public class dna {

    /* renamed from: a, reason: collision with root package name */
    private boolean f11725a = false;
    private String b;
    private Context c;
    private ResourceBriefInfo d;
    private int e;
    private String g;

    public dna(int i, ResourceBriefInfo resourceBriefInfo) {
        Context e = BaseApplication.e();
        this.c = e;
        this.e = i;
        this.b = LoginInit.getInstance(e).getHuidOrDefault();
        this.d = resourceBriefInfo;
        this.g = resourceBriefInfo.getResourceId();
    }

    public boolean a() {
        if (this.e == 8002 && b()) {
            return false;
        }
        CycleRuleBase triggerCycle = this.d.getMarketingRule().getTriggerCycle();
        if (triggerCycle == null || d()) {
            return c();
        }
        return m(triggerCycle);
    }

    private boolean b() {
        long b = SharedPreferenceManager.b(Integer.toString(10000), "marketing_float_bar_close_time", 0L);
        ReleaseLogUtil.e("R_MarketingCyclesMgr", "isShowFloatBarInSevenDay nowTime = ", Long.valueOf(System.currentTimeMillis()), "; lastCloseTime = ", Long.valueOf(b));
        if (System.currentTimeMillis() - b >= 604800000) {
            return false;
        }
        ReleaseLogUtil.e("R_MarketingCyclesMgr", "CLOSE BEFORE 7 DAYS");
        return true;
    }

    private boolean d() {
        if (this.e != 4048 || this.d.getContentType() != 15) {
            return false;
        }
        LogUtil.a("MarketingCyclesMgr", "time rule bypassed resource, positionId: ", Integer.valueOf(this.e), " contentType: ", Integer.valueOf(this.d.getContentType()));
        return true;
    }

    private boolean m(CycleRuleBase cycleRuleBase) {
        LogUtil.a("MarketingCyclesMgr", "isTimeRules. positionId: ", Integer.valueOf(this.e));
        if (c()) {
            int cycleType = cycleRuleBase.getCycleType();
            LogUtil.a("MarketingCyclesMgr", "cycle Type is:", Integer.valueOf(cycleType), " positionId: ", Integer.valueOf(this.e));
            switch (cycleType) {
                case 1:
                    LogUtil.a("MarketingCyclesMgr", "time rules one single, positionId: ", Integer.valueOf(this.e));
                    return j();
                case 2:
                    LogUtil.a("MarketingCyclesMgr", "time rules everyday, positionId: ", Integer.valueOf(this.e));
                    return f();
                case 3:
                    LogUtil.a("MarketingCyclesMgr", "time rules every week, positionId: ", Integer.valueOf(this.e));
                    return e(cycleRuleBase) && f();
                case 4:
                    LogUtil.a("MarketingCyclesMgr", "time rules several days, positionId: ", Integer.valueOf(this.e));
                    return c(cycleRuleBase) && f();
                case 5:
                    LogUtil.a("MarketingCyclesMgr", "time rules every month, positionId: ", Integer.valueOf(this.e));
                    return a(cycleRuleBase) && f();
                case 6:
                    LogUtil.a("MarketingCyclesMgr", "time rules specific, positionId: ", Integer.valueOf(this.e));
                    return h(cycleRuleBase) && f();
                case 7:
                    LogUtil.a("MarketingCyclesMgr", "time rules login days. cycle: ", Integer.valueOf(cycleType), " positionId: ", Integer.valueOf(this.e));
                    return b(cycleRuleBase) && f();
                case 8:
                case 9:
                    LogUtil.a("MarketingCyclesMgr", "vip time rule. cycleType: ", Integer.valueOf(cycleType), " positionId: ", Integer.valueOf(this.e));
                    return n(cycleRuleBase) && f();
                case 10:
                case 11:
                    LogUtil.a("MarketingCyclesMgr", "equity time rule. cycleType: ", Integer.valueOf(cycleType), " positionId: ", Integer.valueOf(this.e));
                    LogUtil.a("MarketingCyclesMgr", "isEquityGapForDays.", Boolean.valueOf(d(cycleRuleBase)));
                    return d(cycleRuleBase) && f();
                case 12:
                    return g(cycleRuleBase) && f();
                case 13:
                    LogUtil.a("MarketingCyclesMgr", "spec date time period. cyclevalue: ", cycleRuleBase.getCycleValue());
                    return j(cycleRuleBase) && f();
                case 14:
                    return g(cycleRuleBase) && j();
                default:
                    LogUtil.c("MarketingCyclesMgr", "No cycle match. match failed.");
                    return false;
            }
        }
        LogUtil.c("MarketingCyclesMgr", "cycle is not in effective Time.");
        return false;
    }

    private boolean c() {
        long effectiveTime = this.d.getEffectiveTime();
        long expirationTime = this.d.getExpirationTime();
        long currentTimeMillis = System.currentTimeMillis();
        if (effectiveTime <= currentTimeMillis && currentTimeMillis <= expirationTime) {
            LogUtil.a("MarketingCyclesMgr", "time is effective time, positionId: ", Integer.valueOf(this.e));
            return true;
        }
        LogUtil.a("MarketingCyclesMgr", "time is not in effective time, positionId: ", Integer.valueOf(this.e));
        return false;
    }

    private boolean j() {
        return MarketingEventDbMgr.b(this.c).c(this.b, this.e, this.g) == null;
    }

    private boolean f() {
        String str = " and triggerTime >= " + String.valueOf(jdl.t(System.currentTimeMillis()));
        LogUtil.a("MarketingCyclesMgr", "condition: ", str);
        if (MarketingEventDbMgr.b(this.c).c(this.b, this.e, this.g, str).isEmpty()) {
            LogUtil.a("MarketingCyclesMgr", "marketingEventDBMgrList is null");
            return true;
        }
        LogUtil.a("MarketingCyclesMgr", "marketingEventDBMgrList is not null");
        return false;
    }

    private boolean e(CycleRuleBase cycleRuleBase) {
        int cycleSubType = cycleRuleBase.getCycleSubType();
        if (cycleSubType == 1) {
            return f(cycleRuleBase);
        }
        if (cycleSubType != 2) {
            return false;
        }
        return i(cycleRuleBase);
    }

    private boolean f(CycleRuleBase cycleRuleBase) {
        int i;
        try {
            i = Integer.parseInt(cycleRuleBase.getCycleValue());
        } catch (NumberFormatException e) {
            LogUtil.b("MarketingCyclesMgr", "NumberFormatException", e.getMessage());
            i = 0;
        }
        String str = " and triggerTime >= " + jdl.c(System.currentTimeMillis(), 2, 0);
        LogUtil.a("MarketingCyclesMgr", "condition: ", str);
        return MarketingEventDbMgr.b(this.c).c(this.b, this.e, this.g, str).size() < i;
    }

    private boolean i(CycleRuleBase cycleRuleBase) {
        String[] split = cycleRuleBase.getCycleValue().split(",");
        int i = Calendar.getInstance().get(7);
        for (String str : split) {
            if (String.valueOf(i).equals(str)) {
                return true;
            }
        }
        return false;
    }

    private boolean c(CycleRuleBase cycleRuleBase) {
        int i;
        try {
            i = Integer.parseInt(cycleRuleBase.getCycleValue());
        } catch (NumberFormatException e) {
            LogUtil.b("MarketingCyclesMgr", "parseInt exception", e.getMessage());
            i = 0;
        }
        return d(i);
    }

    private boolean a(CycleRuleBase cycleRuleBase) {
        int i;
        try {
            i = Integer.parseInt(cycleRuleBase.getCycleValue());
        } catch (NumberFormatException e) {
            LogUtil.b("MarketingCyclesMgr", "parseInt exception", e.getMessage());
            i = 0;
        }
        String str = " and triggerTime >= " + jdl.s(System.currentTimeMillis());
        LogUtil.a("MarketingCyclesMgr", "condition: ", str);
        return MarketingEventDbMgr.b(this.c).c(this.b, this.e, this.g, str).size() < i;
    }

    private boolean h(CycleRuleBase cycleRuleBase) {
        String[] split = cycleRuleBase.getCycleValue().split(",");
        String b = DateFormatUtil.b(System.currentTimeMillis(), DateFormatUtil.DateFormatType.DATE_FORMAT_YYYY_MM_DD);
        for (String str : split) {
            if (b.equals(str)) {
                return true;
            }
        }
        return false;
    }

    private boolean b(CycleRuleBase cycleRuleBase) {
        return TextUtils.equals(cycleRuleBase.getCycleValue(), SharedPreferenceManager.b(BaseApplication.e(), String.valueOf(10000), "user_last_login_days"));
    }

    private boolean n(CycleRuleBase cycleRuleBase) {
        UserMemberInfo e = e();
        if (e == null || e.getExpireTime() == 0) {
            LogUtil.h("MarketingCyclesMgr", "vip userMemberInfo is null or expireTime is null.");
            return false;
        }
        String[] split = cycleRuleBase.getCycleValue().split(Constants.LINK);
        int m = CommonUtil.m(BaseApplication.e(), split[0]);
        int m2 = CommonUtil.m(BaseApplication.e(), split[1]);
        long currentTimeMillis = System.currentTimeMillis();
        long expireTime = e.getExpireTime();
        LogUtil.a("MarketingCyclesMgr", "vip expiredTime: ", Long.valueOf(expireTime));
        int b = eil.b(currentTimeMillis, expireTime);
        LogUtil.a("MarketingCyclesMgr", "vip gapDay: ", Integer.valueOf(b));
        if (b < m || b > m2) {
            LogUtil.h("MarketingCyclesMgr", "date out of range.");
            return false;
        }
        int cycleType = cycleRuleBase.getCycleType();
        LogUtil.a("MarketingCyclesMgr", "vip time rule. vip time rule CYCLETYPE: ", Integer.valueOf(cycleType));
        if (cycleType != 8) {
            if (cycleType == 9 && !gpn.d(e)) {
                return d(m2 - b);
            }
            return false;
        }
        if (gpn.d(e)) {
            return d(b - m);
        }
        return false;
    }

    private boolean d(final CycleRuleBase cycleRuleBase) {
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        PayApi payApi = (PayApi) Services.a("TradeService", PayApi.class);
        if (payApi != null) {
            payApi.queryBenefitInfo(9, "", new IBaseResponseCallback() { // from class: dnd
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public final void d(int i, Object obj) {
                    dna.this.a(cycleRuleBase, countDownLatch, i, obj);
                }
            });
        } else {
            LogUtil.h("MarketingCyclesMgr", "payApi is null");
        }
        try {
            countDownLatch.await(1000L, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            LogUtil.b("MarketingCyclesMgr", e.toString());
        }
        return this.f11725a;
    }

    /* synthetic */ void a(CycleRuleBase cycleRuleBase, CountDownLatch countDownLatch, int i, Object obj) {
        if (i == 0 && (obj instanceof RepeatResourceBenefitInfo)) {
            LogUtil.a("MarketingCyclesMgr", "hasEquity");
            this.f11725a = c(cycleRuleBase, (RepeatResourceBenefitInfo) obj);
            countDownLatch.countDown();
        }
    }

    private boolean g(CycleRuleBase cycleRuleBase) {
        String cycleValue = cycleRuleBase.getCycleValue();
        LogUtil.a("MarketingCyclesMgr", "isTimePeriodTrigger ", cycleValue);
        if (TextUtils.isEmpty(cycleValue)) {
            LogUtil.h("MarketingCyclesMgr", "isTimePeriodTrigger cycleValue is empty, return false");
            return false;
        }
        String[] split = cycleValue.split(",");
        String b = DateFormatUtil.b(System.currentTimeMillis(), DateFormatUtil.DateFormatType.DATE_FORMAT_HOUR_MINUTE_SEC);
        for (String str : split) {
            if (TextUtils.isEmpty(str)) {
                LogUtil.h("MarketingCyclesMgr", "timePeriod is empty");
            } else {
                String[] split2 = str.split(Constants.LINK);
                if (split2 == null || split2.length <= 1) {
                    LogUtil.a("MarketingCyclesMgr", "invalid timePeriod. timePeriod = ", str);
                } else {
                    String str2 = split2[0];
                    String str3 = split2[1];
                    if (b.compareTo(str2) > 0 && b.compareTo(str3) < 0) {
                        LogUtil.a("MarketingCyclesMgr", "isTimePeriodTrigger true");
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private boolean j(CycleRuleBase cycleRuleBase) {
        String cycleValue = cycleRuleBase.getCycleValue();
        LogUtil.a("MarketingCyclesMgr", "cycleValue", cycleRuleBase.getCycleValue());
        long currentTimeMillis = System.currentTimeMillis();
        String b = DateFormatUtil.b(currentTimeMillis, DateFormatUtil.DateFormatType.DATE_FORMAT_YYYY_MM_DD);
        String b2 = DateFormatUtil.b(currentTimeMillis, DateFormatUtil.DateFormatType.DATE_FORMAT_HOUR_MINUTE_SEC);
        try {
            JSONArray jSONArray = new JSONArray(cycleValue);
            for (int i = 0; i < jSONArray.length(); i++) {
                if (b(b, b2, b(jSONArray.getJSONObject(i)))) {
                    return true;
                }
            }
        } catch (JSONException unused) {
            LogUtil.b("MarketingCyclesMgr", "cycleValue jsonArray fail");
        }
        return false;
    }

    private boolean b(String str, String str2, SpecificDateTimePeriod specificDateTimePeriod) {
        if (specificDateTimePeriod == null) {
            return false;
        }
        String dates = specificDateTimePeriod.getDates();
        String startTime = specificDateTimePeriod.getStartTime();
        String endTime = specificDateTimePeriod.getEndTime();
        String[] split = dates.split(",");
        if (str2.compareTo(startTime) < 0 || str2.compareTo(endTime) > 0) {
            LogUtil.a("MarketingCyclesMgr", "time period not hit");
            return false;
        }
        LogUtil.a("MarketingCyclesMgr", "time period hit");
        for (String str3 : split) {
            if (str.equals(str3)) {
                LogUtil.a("MarketingCyclesMgr", "date hit");
                return true;
            }
        }
        LogUtil.a("MarketingCyclesMgr", "date not hit");
        return false;
    }

    private SpecificDateTimePeriod b(JSONObject jSONObject) {
        if (jSONObject == null) {
            return null;
        }
        SpecificDateTimePeriod specificDateTimePeriod = new SpecificDateTimePeriod();
        try {
            if (!jSONObject.isNull("dates")) {
                specificDateTimePeriod.setDates(jSONObject.getString("dates"));
            }
            if (!jSONObject.isNull("startTime")) {
                specificDateTimePeriod.setStartTime(jSONObject.getString("startTime"));
            }
            if (!jSONObject.isNull("endTime")) {
                specificDateTimePeriod.setEndTime(jSONObject.getString("endTime"));
            }
            return specificDateTimePeriod;
        } catch (JSONException e) {
            LogUtil.b("MarketingCyclesMgr", "expoundOperationActivity parse json meet exception: ", e.getMessage());
            return null;
        }
    }

    private boolean c(CycleRuleBase cycleRuleBase, RepeatResourceBenefitInfo repeatResourceBenefitInfo) {
        String[] split = cycleRuleBase.getCycleValue().split(Constants.LINK);
        int m = CommonUtil.m(BaseApplication.e(), split[0]);
        int m2 = CommonUtil.m(BaseApplication.e(), split[1]);
        long nowTime = repeatResourceBenefitInfo.getNowTime();
        long longValue = repeatResourceBenefitInfo.getExpireTime().longValue();
        LogUtil.a("MarketingCyclesMgr", "benefit expiredTime: ", Long.valueOf(longValue));
        int b = eil.b(nowTime, longValue);
        LogUtil.a("MarketingCyclesMgr", "benefit gapDay: ", Integer.valueOf(b));
        if (b < m || b > m2) {
            LogUtil.h("MarketingCyclesMgr", "date out of range.");
            return false;
        }
        int cycleType = cycleRuleBase.getCycleType();
        LogUtil.a("MarketingCyclesMgr", "benefit time rule. benefit time rule CYCLETYPE: ", Integer.valueOf(cycleType));
        if (cycleType != 10) {
            return cycleType == 11 && nowTime <= longValue;
        }
        if (nowTime > longValue) {
            return j();
        }
        return false;
    }

    private boolean d(int i) {
        String str = " and triggerTime >= " + jdl.b(System.currentTimeMillis(), -i);
        LogUtil.a("MarketingCyclesMgr", "condition: ", str);
        return MarketingEventDbMgr.b(this.c).c(this.b, this.e, this.g, str).size() == 0;
    }

    private UserMemberInfo e() {
        String b = SharedPreferenceManager.b(this.c, Integer.toString(10000), "USER_VIP_INFO_KEY");
        if (TextUtils.isEmpty(b)) {
            LogUtil.h("MarketingCyclesMgr", "get vipInfo is null.");
            return null;
        }
        return (UserMemberInfo) moj.e(b, UserMemberInfo.class);
    }
}
