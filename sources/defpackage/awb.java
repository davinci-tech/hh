package defpackage;

import android.text.TextUtils;
import android.util.SparseArray;
import com.google.gson.JsonParseException;
import com.huawei.basichealthmodel.bean.ChallengeConfigBean;
import com.huawei.basichealthmodel.bean.ConfigBean;
import com.huawei.basichealthmodel.bean.TaskConfigBean;
import com.huawei.hihealth.util.HiJsonUtil;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.CommonUtil;
import health.compact.a.IoUtils;
import health.compact.a.LogAnonymous;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes3.dex */
public class awb {
    private static final AtomicBoolean c = new AtomicBoolean(false);

    public ConfigBean d(boolean z, boolean z2) {
        if (!z2) {
            LogUtil.a("HealthLife_HealthTaskFileLoader", "loadTasks ifAllowLogin is false");
            return d(b("healthModel/health_model_task_config_oversea.json"));
        }
        String c2 = bad.b().c("health_model_task_config.json");
        AtomicBoolean atomicBoolean = c;
        atomicBoolean.set(false);
        if (TextUtils.isEmpty(c2) || z) {
            c2 = b(z ? "healthModel/health_model_task_config_oversea.json" : "healthModel/health_model_task_config.json");
            atomicBoolean.set(true);
        }
        return d(c2);
    }

    public ConfigBean b(boolean z) {
        ConfigBean d = d(b(z ? "challenge/health_model_challenge_config_oversea.json" : "challenge/health_model_challenge_config.json"));
        if (d == null) {
            return null;
        }
        List<ChallengeConfigBean> challengeList = d.getChallengeList();
        if (!CommonUtil.bh() && koq.c(challengeList)) {
            for (ChallengeConfigBean challengeConfigBean : challengeList) {
                if (challengeConfigBean != null) {
                    List<String> challengeTaskId = challengeConfigBean.getChallengeTaskId();
                    if (koq.c(challengeTaskId)) {
                        challengeTaskId.remove(String.valueOf(9));
                    }
                }
            }
        }
        return d;
    }

    private ConfigBean d(String str) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("HealthLife_HealthTaskFileLoader", "jsonToBean json is empty");
            return null;
        }
        try {
            return (ConfigBean) HiJsonUtil.e(str, ConfigBean.class);
        } catch (JsonParseException | IllegalStateException | NullPointerException e) {
            LogUtil.b("HealthLife_HealthTaskFileLoader", "jsonToBean exception ", LogAnonymous.b(e));
            return null;
        }
    }

    public static AtomicBoolean e() {
        if (!CommonUtil.aa(BaseApplication.getContext())) {
            return new AtomicBoolean(false);
        }
        return c;
    }

    public static void e(boolean z) {
        c.set(z);
    }

    public static SparseArray<TaskConfigBean> kg_(ConfigBean configBean) {
        if (configBean == null) {
            LogUtil.h("HealthLife_HealthTaskFileLoader", "getTaskSparseArray configEntity is null");
            return new SparseArray<>();
        }
        List<TaskConfigBean> taskList = configBean.getTaskList();
        SparseArray<TaskConfigBean> sparseArray = new SparseArray<>();
        if (koq.b(taskList)) {
            LogUtil.h("HealthLife_HealthTaskFileLoader", "getTaskSparseArray configList is empty");
            return sparseArray;
        }
        for (TaskConfigBean taskConfigBean : taskList) {
            if (taskConfigBean != null) {
                sparseArray.put(taskConfigBean.getId(), taskConfigBean);
            }
        }
        return sparseArray;
    }

    public static SparseArray<ChallengeConfigBean> kf_(ConfigBean configBean) {
        if (configBean == null) {
            LogUtil.h("HealthLife_HealthTaskFileLoader", "getChallengeSparseArray configEntity is null");
            return new SparseArray<>();
        }
        List<ChallengeConfigBean> challengeList = configBean.getChallengeList();
        SparseArray<ChallengeConfigBean> sparseArray = new SparseArray<>();
        if (koq.b(challengeList)) {
            LogUtil.h("HealthLife_HealthTaskFileLoader", "getChallengeSparseArray configList is empty");
            return sparseArray;
        }
        for (ChallengeConfigBean challengeConfigBean : challengeList) {
            if (challengeConfigBean != null) {
                sparseArray.put(challengeConfigBean.getChallengeId(), challengeConfigBean);
            }
        }
        return sparseArray;
    }

    private String b(String str) {
        LogUtil.a("HealthLife_HealthTaskFileLoader", "getLocalFile enter");
        InputStream inputStream = null;
        try {
            try {
                inputStream = BaseApplication.getContext().getAssets().open(str);
                byte[] bArr = new byte[inputStream.available()];
                if (inputStream.read(bArr) > 0) {
                    return new String(bArr, StandardCharsets.UTF_8);
                }
            } catch (IOException e) {
                LogUtil.b("HealthLife_HealthTaskFileLoader", "getLocalFile ioException ", LogAnonymous.b((Throwable) e));
            }
            IoUtils.e(inputStream);
            return "";
        } finally {
            IoUtils.e(inputStream);
        }
    }
}
