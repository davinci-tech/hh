package defpackage;

import com.huawei.haf.application.BaseApplication;
import com.huawei.hwcommonmodel.utils.DateFormatUtil;
import com.huawei.hwfoundationmodel.trackmodel.MotionPathSimplify;
import com.huawei.ui.homehealth.sportsrecordingcard.SportsRecordingBean;
import health.compact.a.UnitUtil;
import java.util.Formatter;
import java.util.Locale;

/* loaded from: classes9.dex */
public class oth {
    protected MotionPathSimplify e;

    public int c() {
        return 0;
    }

    public SportsRecordingBean b(MotionPathSimplify motionPathSimplify) {
        SportsRecordingBean sportsRecordingBean = new SportsRecordingBean();
        if (motionPathSimplify == null) {
            return sportsRecordingBean;
        }
        this.e = motionPathSimplify;
        sportsRecordingBean.setSportStartTime(motionPathSimplify.requestStartTime());
        sportsRecordingBean.setSportEndTime(motionPathSimplify.requestEndTime());
        sportsRecordingBean.setShowType(c());
        sportsRecordingBean.setSportTime(b(motionPathSimplify.requestStartTime()));
        sportsRecordingBean.setSportData(b());
        sportsRecordingBean.setSportUnit(e());
        sportsRecordingBean.setSportSpeed(d());
        sportsRecordingBean.setSportSpeedUnit(a());
        sportsRecordingBean.setSportTypeName(a(motionPathSimplify.requestSportType()));
        sportsRecordingBean.setSportKeepTime(a(motionPathSimplify.requestTotalTime()));
        if (koq.c(motionPathSimplify.requestChildSportItems())) {
            sportsRecordingBean.setChildSportItems(motionPathSimplify.requestChildSportItems());
        }
        return sportsRecordingBean;
    }

    public String b(long j) {
        if (jdl.g(j, System.currentTimeMillis())) {
            return DateFormatUtil.d(j, DateFormatUtil.DateFormatType.DATE_FORMAT_MONTH_DAY_MD);
        }
        return DateFormatUtil.d(j, DateFormatUtil.DateFormatType.DATE_FORMAT_YYYYMD);
    }

    public String a(int i) {
        return this.e == null ? "" : gwg.e(BaseApplication.e(), i);
    }

    public String a(long j) {
        return d(j);
    }

    private static String d(long j) {
        StringBuilder sb = new StringBuilder();
        Formatter formatter = new Formatter(sb, Locale.getDefault());
        int i = (int) (j / 1000);
        sb.setLength(0);
        return formatter.format("%02d:%02d:%02d", Integer.valueOf(i / 3600), Integer.valueOf((i / 60) % 60), Integer.valueOf(i % 60)).toString();
    }

    protected String e(double d, double d2) {
        if (d <= 0.0d || d >= d2) {
            d = d2;
        }
        return UnitUtil.e(d / 1000.0d, 1, 0);
    }

    public String a() {
        return "";
    }

    public String d() {
        return "";
    }

    public String e() {
        return "";
    }

    public String b() {
        return "";
    }
}
