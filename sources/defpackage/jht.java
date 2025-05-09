package defpackage;

import com.huawei.datatype.FitnessUserInfo;
import com.huawei.hwcommonmodel.fitnessdatatype.DataTotalMotion;
import com.huawei.hwcommonmodel.fitnessdatatype.HeartRateDetect;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.up.model.UserInfomation;
import health.compact.a.CommonUtil;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes5.dex */
public class jht {
    public static UserInfomation d(cwe cweVar) {
        UserInfomation userInfomation = new UserInfomation(0);
        if (cweVar == null) {
            LogUtil.h("FitnessUnTlvUtil", "unTlvGetStudentInfo tlvFather is null.");
            return userInfomation;
        }
        for (cwd cwdVar : cweVar.e()) {
            int w = CommonUtil.w(cwdVar.e());
            if (w == 1) {
                userInfomation.setHeight(CommonUtil.w(cwdVar.c()));
            } else if (w == 2) {
                userInfomation.setWeight(CommonUtil.w(cwdVar.c()));
            } else if (w == 4) {
                b(cwdVar, userInfomation);
            } else if (w == 5) {
                int w2 = CommonUtil.w(cwdVar.c());
                if (w2 == 1) {
                    userInfomation.setGender(0);
                } else if (w2 == 2) {
                    userInfomation.setGender(1);
                } else {
                    userInfomation.setGender(-1);
                }
            } else {
                LogUtil.h("FitnessUnTlvUtil", "unTlvGetStudentInfo unknown");
            }
        }
        return userInfomation;
    }

    private static void b(cwd cwdVar, UserInfomation userInfomation) {
        String c = cwdVar.c();
        if (c.length() == 8 && !"00000000".equals(c)) {
            int w = CommonUtil.w(c.substring(0, 4));
            String valueOf = String.valueOf(CommonUtil.w(c.substring(4, 6)));
            String valueOf2 = String.valueOf(CommonUtil.w(c.substring(6)));
            if (valueOf.length() == 1) {
                valueOf = "0" + valueOf;
            }
            if (valueOf2.length() == 1) {
                valueOf2 = "0" + valueOf2;
            }
            userInfomation.setBirthday(String.valueOf(w) + valueOf + valueOf2);
            return;
        }
        LogUtil.h("FitnessUnTlvUtil", "unTlvGetStudentBirthday birthdayResult is error.");
        userInfomation.setBirthday("");
    }

    public static jhw c(cwe cweVar) {
        jhw jhwVar = new jhw();
        if (cweVar == null) {
            return jhwVar;
        }
        List<cwe> a2 = cweVar.a();
        for (int i = 0; i < a2.size(); i++) {
            cwe cweVar2 = a2.get(i);
            List<cwd> e = cweVar2.e();
            for (int i2 = 0; i2 < e.size(); i2++) {
                int w = CommonUtil.w(e.get(i2).e());
                if (w == 2) {
                    jhwVar.c(CommonUtil.w(e.get(i2).c()));
                } else if (w == 9) {
                    HeartRateDetect heartRateDetect = new HeartRateDetect();
                    String c = e.get(i2).c();
                    LogUtil.a("FitnessUnTlvUtil", "unTlv HeartRateDetect value:", c);
                    d(heartRateDetect, c);
                    jhwVar.d(heartRateDetect);
                } else {
                    LogUtil.h("FitnessUnTlvUtil", "unTlvGetHealthDataCurrentDay default");
                }
            }
            Iterator<cwe> it = cweVar2.a().iterator();
            while (it.hasNext()) {
                jhwVar.d().add(a(it.next().e()));
            }
        }
        return jhwVar;
    }

    private static void d(HeartRateDetect heartRateDetect, String str) {
        if (str.length() == 10) {
            heartRateDetect.setTimestamp(CommonUtil.w(str.substring(0, 8)));
            heartRateDetect.setHeartRate(CommonUtil.w(str.substring(8, 10)));
        }
    }

    public static FitnessUserInfo b(cwe cweVar) {
        FitnessUserInfo fitnessUserInfo = new FitnessUserInfo();
        if (cweVar == null) {
            return fitnessUserInfo;
        }
        Iterator<cwe> it = cweVar.a().iterator();
        while (it.hasNext()) {
            List<cwd> e = it.next().e();
            for (int i = 0; i < e.size(); i++) {
                String c = e.get(i).c();
                int w = CommonUtil.w(e.get(i).e());
                if (w == 2) {
                    fitnessUserInfo.setTime(CommonUtil.y(c));
                } else if (w == 3) {
                    fitnessUserInfo.setHeight(CommonUtil.w(c));
                } else if (w == 4) {
                    fitnessUserInfo.setWeight(CommonUtil.w(c));
                } else {
                    LogUtil.h("FitnessUnTlvUtil", "unPackGetUserInfo default");
                }
            }
        }
        return fitnessUserInfo;
    }

    private static DataTotalMotion a(List<cwd> list) {
        DataTotalMotion dataTotalMotion = new DataTotalMotion();
        if (list == null) {
            return dataTotalMotion;
        }
        for (cwd cwdVar : list) {
            switch (CommonUtil.w(cwdVar.e())) {
                case 4:
                    dataTotalMotion.setMotion_type(CommonUtil.w(cwdVar.c()));
                    break;
                case 5:
                    dataTotalMotion.setStep(CommonUtil.w(cwdVar.c()));
                    break;
                case 6:
                    dataTotalMotion.setCalorie(CommonUtil.w(cwdVar.c()));
                    break;
                case 7:
                    dataTotalMotion.setDistance(CommonUtil.w(cwdVar.c()));
                    break;
                case 8:
                    dataTotalMotion.setSleep_time(CommonUtil.w(cwdVar.c()));
                    break;
                case 9:
                default:
                    LogUtil.h("FitnessUnTlvUtil", "unPackMotion default");
                    break;
                case 10:
                    dataTotalMotion.setHeight(CommonUtil.w(cwdVar.c()));
                    break;
            }
        }
        return dataTotalMotion;
    }
}
