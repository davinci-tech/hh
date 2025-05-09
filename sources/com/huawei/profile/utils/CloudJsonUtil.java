package com.huawei.profile.utils;

import com.huawei.profile.coordinator.bean.CloudProfileBean;
import com.huawei.profile.kv.ProfileJson;
import java.lang.reflect.Type;
import java.util.List;

/* loaded from: classes6.dex */
public abstract class CloudJsonUtil {
    public static List<CloudProfileBean> getProfileBeanFromJson(String str, Type type) {
        return (List) new ProfileJson().fromJson(str, type);
    }
}
