package defpackage;

import com.google.gson.JsonSyntaxException;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.data.model.HiTrackMetaData;
import com.huawei.hihealth.util.HiJsonUtil;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.homehealth.runcard.operation.recommendalgo.model.SportLevel;
import com.huawei.up.model.UserInfomation;
import java.util.List;

/* loaded from: classes9.dex */
public class oqb {

    /* renamed from: a, reason: collision with root package name */
    private double f15843a = 0.0d;
    private double e;

    public SportLevel b(List<HiHealthData> list, UserInfomation userInfomation) {
        if (userInfomation == null) {
            LogUtil.h("Track_RunHistoryToSportLevel", "calSportLevel userInfomation is null");
            return new SportLevel(-1, 1);
        }
        int gender = userInfomation.getGender();
        if (gender != 0 && gender != 1) {
            LogUtil.h("Track_RunHistoryToSportLevel", "undefined gender");
            return new SportLevel(-1, 2);
        }
        c(list);
        return b(gender);
    }

    private void c(List<HiHealthData> list) {
        LogUtil.a("Track_RunHistoryToSportLevel", "analyzeTrackSimplifyData");
        if (list == null) {
            LogUtil.a("Track_RunHistoryToSportLevel", "analyzeTrackSimplifyData datas is null");
            this.e = 0.0d;
        }
        if (koq.c(list)) {
            long j = 0;
            int i = 0;
            double d = 0.0d;
            for (HiHealthData hiHealthData : list) {
                if (hiHealthData == null) {
                    LogUtil.a("Track_RunHistoryToSportLevel", "analyzeTrackSimplifyData hiHealthData is null");
                } else {
                    String metaData = hiHealthData.getMetaData();
                    if (metaData == null) {
                        LogUtil.a("Track_RunHistoryToSportLevel", "metaData is null");
                    } else {
                        try {
                            HiTrackMetaData hiTrackMetaData = (HiTrackMetaData) HiJsonUtil.e(metaData, HiTrackMetaData.class);
                            if (hiTrackMetaData.getDuplicated() == 0 && hiTrackMetaData.getAbnormalTrack() == 0) {
                                d += hiTrackMetaData.getTotalDistance();
                                i++;
                                j += hiTrackMetaData.getTotalTime();
                            }
                        } catch (JsonSyntaxException unused) {
                            LogUtil.h("Track_RunHistoryToSportLevel", "analyzeTrackSimplifyData trackMetaData is jsonSyntaxException");
                        }
                    }
                }
            }
            if (i > 0) {
                this.e = d / i;
                if (d > 0.0d) {
                    this.f15843a = j / d;
                } else {
                    this.f15843a = 0.0d;
                }
            } else {
                this.e = 0.0d;
            }
            LogUtil.a("Track_RunHistoryToSportLevel", "analyzeTrackSimplifyData sum=", Double.valueOf(d), " count=", Integer.valueOf(i), " duration =", Long.valueOf(j));
        }
    }

    private SportLevel a(double d) {
        if (d >= 573.0d) {
            return new SportLevel(0, 7, d);
        }
        if (d >= 522.0d) {
            return new SportLevel(1, 7, d);
        }
        if (d >= 467.0d) {
            return new SportLevel(2, 7, d);
        }
        if (d >= 418.0d) {
            return new SportLevel(3, 7, d);
        }
        if (d >= 384.0d) {
            return new SportLevel(4, 7, d);
        }
        if (d >= 0.0d) {
            return new SportLevel(5, 7, d);
        }
        return new SportLevel(-1, 0, d);
    }

    private SportLevel e(double d) {
        if (d >= 500.0d) {
            return new SportLevel(0, 7, d);
        }
        if (d >= 450.0d) {
            return new SportLevel(1, 7, d);
        }
        if (d >= 396.0d) {
            return new SportLevel(2, 7, d);
        }
        if (d >= 354.0d) {
            return new SportLevel(3, 7, d);
        }
        if (d >= 321.0d) {
            return new SportLevel(4, 7, d);
        }
        if (d >= 0.0d) {
            return new SportLevel(5, 7, d);
        }
        return new SportLevel(-1, 0, d);
    }

    public SportLevel b(int i) {
        double d = this.e;
        if (d < 1.0d) {
            return new SportLevel(-1, 4);
        }
        if (d < 2000.0d) {
            return new SportLevel(0, 6, d);
        }
        return e(this.f15843a, i);
    }

    private SportLevel e(double d, int i) {
        if (i == 1) {
            return a(d);
        }
        if (i == 0) {
            return e(d);
        }
        return new SportLevel(-1, 0);
    }
}
