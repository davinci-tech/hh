package defpackage;

import com.huawei.hihealth.HiGoalInfo;
import com.huawei.hihealth.HiUserInfo;
import com.huawei.hihealth.HiUserPreference;
import com.huawei.hihealth.data.type.HiGoalType;
import com.huawei.hwcloudmodel.model.userprofile.UserBasicInfo;
import com.huawei.hwcloudmodel.model.userprofile.UserGoalsInfo;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.CommonUtil;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes7.dex */
public class isx {
    public UserBasicInfo d(HiUserInfo hiUserInfo) {
        if (hiUserInfo == null) {
            return null;
        }
        UserBasicInfo userBasicInfo = new UserBasicInfo();
        if (hiUserInfo.isBirthdayValid()) {
            userBasicInfo.setBirthday(Integer.valueOf(hiUserInfo.getBirthday()));
        }
        userBasicInfo.setEmail(hiUserInfo.getEmail());
        if (hiUserInfo.isGenderValid()) {
            userBasicInfo.setGender(Integer.valueOf(hiUserInfo.getGender()));
        }
        userBasicInfo.setMobile(hiUserInfo.getMobile());
        userBasicInfo.setName(hiUserInfo.getName());
        userBasicInfo.setPortraitUrl(hiUserInfo.getHeadImgUrl());
        if (hiUserInfo.isHeightValid()) {
            userBasicInfo.setHeight(Integer.valueOf(hiUserInfo.getHeight()));
            userBasicInfo.setUnitType(Integer.valueOf(hiUserInfo.getUnitType()));
        }
        if (hiUserInfo.isWeightValid()) {
            userBasicInfo.setWeight(Integer.valueOf((int) hiUserInfo.getWeight()));
            try {
                userBasicInfo.setWeightDb(Double.valueOf(new BigDecimal(hiUserInfo.getWeight()).setScale(2, 1).doubleValue()));
            } catch (NumberFormatException unused) {
                ReleaseLogUtil.c("HiH_UserInfoSwitch", "switchHiUserInfo Exception ", Float.valueOf(hiUserInfo.getWeight()));
            }
            userBasicInfo.setUnitType(Integer.valueOf(hiUserInfo.getUnitType()));
        }
        userBasicInfo.setModifyTime(Long.valueOf(hiUserInfo.getCreateTime()));
        return userBasicInfo;
    }

    public HiUserInfo e(UserBasicInfo userBasicInfo) {
        if (userBasicInfo == null) {
            return null;
        }
        HiUserInfo hiUserInfo = new HiUserInfo();
        if (userBasicInfo.getBirthday() != null) {
            hiUserInfo.setBirthday(userBasicInfo.getBirthday().intValue());
        }
        hiUserInfo.setEmail(userBasicInfo.getEmail());
        if (userBasicInfo.getGender() != null) {
            hiUserInfo.setGender(userBasicInfo.getGender().intValue());
        }
        if (userBasicInfo.getHeight() != null) {
            hiUserInfo.setHeight(userBasicInfo.getHeight().intValue());
        }
        hiUserInfo.setMobile(userBasicInfo.getMobile());
        hiUserInfo.setName(userBasicInfo.getName());
        hiUserInfo.setHeadImgUrl(userBasicInfo.getPortraitUrl());
        if (userBasicInfo.getWeight() != null) {
            hiUserInfo.setWeight(userBasicInfo.getWeight().intValue());
        }
        if (userBasicInfo.getWeightDb() != null && userBasicInfo.getWeightDb().doubleValue() > 0.0d) {
            hiUserInfo.setWeight(userBasicInfo.getWeightDb().floatValue());
        }
        if (userBasicInfo.getUnitType() != null) {
            hiUserInfo.setUnitType(userBasicInfo.getUnitType().intValue());
        }
        Long modifyTime = userBasicInfo.getModifyTime();
        if (modifyTime == null || modifyTime.longValue() <= 0) {
            LogUtil.a("HiH_UserInfoSwitch", "switchUserBasicInfo old cloud modifyTime , it is ", modifyTime);
            modifyTime = Long.valueOf(System.currentTimeMillis());
        }
        hiUserInfo.setCreateTime(modifyTime.longValue());
        return hiUserInfo;
    }

    private HiGoalInfo e(UserGoalsInfo userGoalsInfo) {
        if (userGoalsInfo == null) {
            return null;
        }
        int e = HiGoalType.e(userGoalsInfo.getGoalType().intValue(), userGoalsInfo.getGoalPeriod().intValue());
        if (e <= 0) {
            LogUtil.h("HiH_UserInfoSwitch", "switchUserGoalsInfo no such local goal type");
            return null;
        }
        HiGoalInfo hiGoalInfo = new HiGoalInfo();
        hiGoalInfo.setGoalType(e);
        hiGoalInfo.setGoalValue(CommonUtil.a(userGoalsInfo.getGoalValue()));
        return hiGoalInfo;
    }

    public List<HiGoalInfo> b(List<UserGoalsInfo> list) {
        if (list == null || list.isEmpty()) {
            return new ArrayList();
        }
        ArrayList arrayList = new ArrayList(list.size());
        for (UserGoalsInfo userGoalsInfo : list) {
            LogUtil.a("HiH_UserInfoSwitch", "switchUserGoalsInfos userGoalInfo is ", userGoalsInfo);
            HiGoalInfo e = e(userGoalsInfo);
            if (e != null) {
                arrayList.add(e);
            }
        }
        return arrayList;
    }

    private UserGoalsInfo a(HiGoalInfo hiGoalInfo) {
        if (hiGoalInfo == null || !a(hiGoalInfo.getGoalType())) {
            return null;
        }
        int e = HiGoalType.e(hiGoalInfo.getGoalType());
        if (e <= 0) {
            LogUtil.h("HiH_UserInfoSwitch", "switchHiGoalInfo no such goalPeriod, goalPeriod is ", Integer.valueOf(e));
            return null;
        }
        int c = HiGoalType.c(hiGoalInfo.getGoalType(), e);
        if (c <= 0) {
            LogUtil.h("HiH_UserInfoSwitch", "switchHiGoalInfo no such cloudGoalType,cloudGoalType is ", Integer.valueOf(c));
            return null;
        }
        UserGoalsInfo userGoalsInfo = new UserGoalsInfo();
        userGoalsInfo.setGoalPeriod(Integer.valueOf(e));
        userGoalsInfo.setGoalType(Integer.valueOf(c));
        userGoalsInfo.setGoalValue(Double.toString(hiGoalInfo.getGoalValue()));
        return userGoalsInfo;
    }

    public List<UserGoalsInfo> a(List<HiGoalInfo> list) {
        if (list == null || list.isEmpty()) {
            return new ArrayList();
        }
        ArrayList arrayList = new ArrayList(list.size());
        Iterator<HiGoalInfo> it = list.iterator();
        while (it.hasNext()) {
            UserGoalsInfo a2 = a(it.next());
            LogUtil.a("HiH_UserInfoSwitch", "switchHiGoalInfos userGoalInfo is ", a2);
            if (a2 != null) {
                arrayList.add(a2);
            }
        }
        return arrayList;
    }

    public Map<String, String> c(List<HiUserPreference> list) {
        if (list == null || list.isEmpty()) {
            return null;
        }
        HashMap hashMap = new HashMap(list.size());
        for (HiUserPreference hiUserPreference : list) {
            hashMap.put(hiUserPreference.getKey(), hiUserPreference.getValue());
        }
        return hashMap;
    }

    public List<HiUserPreference> e(Map<String, String> map) {
        if (map == null || map.isEmpty()) {
            return new ArrayList();
        }
        ArrayList arrayList = new ArrayList(map.entrySet().size());
        for (Map.Entry<String, String> entry : map.entrySet()) {
            HiUserPreference hiUserPreference = new HiUserPreference();
            hiUserPreference.setKey(entry.getKey());
            hiUserPreference.setValue(entry.getValue());
            hiUserPreference.setSyncStatus(1);
            arrayList.add(hiUserPreference);
        }
        return arrayList;
    }

    private boolean a(int i) {
        if (i >= 1 && i <= 46) {
            return true;
        }
        LogUtil.c("HiH_UserInfoSwitch", "no such goalType, goalType is ", Integer.valueOf(i));
        return false;
    }
}
