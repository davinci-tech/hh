package defpackage;

import com.huawei.hihealth.HiHealthData;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.pluginachievement.manager.model.MedalConstants;
import com.huawei.ui.homehealth.runcard.operation.recommendalgo.model.SportLevel;
import com.huawei.ui.homehealth.runcard.operation.recommendalgo.sportlevelmapping.SportLevelByVo2Max;
import com.huawei.up.model.UserInfomation;
import java.util.List;

/* loaded from: classes9.dex */
public class oqj {
    public SportLevel a(List<HiHealthData> list, UserInfomation userInfomation) {
        if (userInfomation == null) {
            LogUtil.h("Track_Vo2MaxToSportLevel", "calSportLevel userInformation is null");
            return new SportLevel(-1, 1);
        }
        int gender = userInfomation.getGender();
        if (gender != 0 && gender != 1) {
            LogUtil.h("Track_Vo2MaxToSportLevel", "undefined gender");
            return new SportLevel(-1, 2);
        }
        return d(list, gender, userInfomation.getAgeOrDefaultValue());
    }

    private SportLevel d(List<HiHealthData> list, int i, int i2) {
        LogUtil.a("Track_Vo2MaxToSportLevel", "analyzeVo2MaxData ");
        if (koq.b(list)) {
            return new SportLevel(-1, 5);
        }
        float f = 0.0f;
        for (HiHealthData hiHealthData : list) {
            if (f < hiHealthData.getInt(MedalConstants.EVENT_KEY)) {
                f = hiHealthData.getInt(MedalConstants.EVENT_KEY);
            }
        }
        LogUtil.a("Track_Vo2MaxToSportLevel", "analyzeVo2MaxData ", Float.valueOf(f));
        SportLevelByVo2Max e = e(i, i2);
        if (e == null) {
            LogUtil.a("Track_Vo2MaxToSportLevel", "analyzeVo2MaxData cal null");
            return new SportLevel(-1, 0);
        }
        return e.calSportLevel(Math.round(f));
    }

    private SportLevelByVo2Max e(int i, int i2) {
        if (i2 <= 19) {
            return null;
        }
        if (i2 <= 24) {
            LogUtil.a("Track_Vo2MaxToSportLevel", "SportLevelByVo2Max20s");
            return new oqi(i);
        }
        if (i2 <= 29) {
            LogUtil.a("Track_Vo2MaxToSportLevel", "SportLevelByVo2Max25s");
            return new oqg(i);
        }
        if (i2 <= 34) {
            LogUtil.a("Track_Vo2MaxToSportLevel", "SportLevelByVo2Max30s");
            return new oqf(i);
        }
        if (i2 <= 39) {
            LogUtil.a("Track_Vo2MaxToSportLevel", "SportLevelByVo2Max35s");
            return new oqh(i);
        }
        if (i2 <= 44) {
            LogUtil.a("Track_Vo2MaxToSportLevel", "SportLevelByVo2Max40s");
            return new oqe(i);
        }
        if (i2 <= 49) {
            LogUtil.a("Track_Vo2MaxToSportLevel", "SportLevelByVo2Max45s");
            return new oqn(i);
        }
        if (i2 <= 54) {
            LogUtil.a("Track_Vo2MaxToSportLevel", "SportLevelByVo2Max50s");
            return new oqk(i);
        }
        if (i2 <= 59) {
            LogUtil.a("Track_Vo2MaxToSportLevel", "SportLevelByVo2Max55s");
            return new oqm(i);
        }
        if (i2 <= 65) {
            LogUtil.a("Track_Vo2MaxToSportLevel", "SportLevelByVo2Max60s");
            return new oql(i);
        }
        LogUtil.a("Track_Vo2MaxToSportLevel", "Age larger than 65.");
        return null;
    }
}
