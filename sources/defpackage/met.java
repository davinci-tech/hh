package defpackage;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import com.huawei.haf.application.BaseApplication;
import com.huawei.health.messagecenter.model.MessageConstant;
import com.huawei.hihealth.util.HiJsonUtil;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.utils.Constants;
import com.huawei.pluginachievement.manager.model.AchieveMessage;
import com.huawei.pluginachievement.manager.model.MedalConfigInfo;
import com.huawei.pluginachievement.manager.model.MessageObject;
import com.huawei.pluginachievement.manager.model.PersonalData;
import com.huawei.pluginachievement.manager.service.AchieveMedalDialogScenario;
import java.util.HashMap;
import java.util.List;

/* loaded from: classes6.dex */
public class met {
    private static HashMap<String, Integer> b;
    private static final Object e = new Object();
    private static final Object c = new Object();

    private static void e() {
        char c2;
        HashMap<String, Integer> hashMap = b;
        if (hashMap == null || hashMap.isEmpty()) {
            List<String> d = mlb.d();
            b = new HashMap<>(d.size());
            for (String str : d) {
                str.hashCode();
                int hashCode = str.hashCode();
                if (hashCode == 2102) {
                    if (str.equals("B8")) {
                        c2 = 0;
                    }
                    c2 = 65535;
                } else if (hashCode == 64993) {
                    if (str.equals("B10")) {
                        c2 = 3;
                    }
                    c2 = 65535;
                } else if (hashCode != 2131) {
                    if (hashCode == 2132 && str.equals("C7")) {
                        c2 = 2;
                    }
                    c2 = 65535;
                } else {
                    if (str.equals("C6")) {
                        c2 = 1;
                    }
                    c2 = 65535;
                }
                if (c2 == 0) {
                    b.put(str, 1);
                } else if (c2 == 1) {
                    b.put(str, 3);
                } else if (c2 == 2) {
                    b.put(str, 0);
                } else if (c2 == 3) {
                    b.put(str, 2);
                } else {
                    b.put(str, 4);
                }
            }
            b.put("C6_50", 5);
        }
    }

    private static int d(String str) {
        e();
        Integer num = b.get(str);
        if (num == null) {
            return 0;
        }
        return num.intValue();
    }

    private static void e(String str, AchieveMessage achieveMessage) {
        mct.b(BaseApplication.e(), str, achieveMessage == null ? "" : HiJsonUtil.e(achieveMessage));
    }

    private static boolean c(AchieveMessage achieveMessage) {
        LogUtil.a("PLGACHIEVE_AchieveMedalDialogInterceptor", "interceptThreeCircleMedal");
        if (achieveMessage == null || !mlb.n(achieveMessage.acquireMedalType())) {
            LogUtil.h("PLGACHIEVE_AchieveMedalDialogInterceptor", "interceptThreeCircleMedal: not the three circle medal");
            return false;
        }
        a(achieveMessage.acquireMedalId());
        String acquireMedalType = achieveMessage.acquireMedalType();
        if ("D1".equals(acquireMedalType) || "D2".equals(acquireMedalType)) {
            LogUtil.h("PLGACHIEVE_AchieveMedalDialogInterceptor", "interceptThreeCircleMedal: xxx% medal -> no save");
            return true;
        }
        synchronized (e) {
            b(achieveMessage);
        }
        return true;
    }

    private static void e(AchieveMessage achieveMessage) {
        if (TextUtils.isEmpty(achieveMessage.getGainTime()) || "0".equals(achieveMessage.getGainTime()) || Constants.NULL.equals(achieveMessage.getGainTime())) {
            achieveMessage.setGainTime(String.valueOf(System.currentTimeMillis()));
        }
        if (!"C6".equals(achieveMessage.acquireMedalType()) || achieveMessage.acquireMedalLevel() < 50) {
            return;
        }
        achieveMessage.saveMedalType("C6_50");
    }

    private static void b(AchieveMessage achieveMessage) {
        LogUtil.a("PLGACHIEVE_AchieveMedalDialogInterceptor", "saveThreeCircleMedalMessage: medalId -> " + achieveMessage.acquireMedalId());
        e(achieveMessage);
        AchieveMessage achieveMessage2 = (AchieveMessage) HiJsonUtil.e(mct.b(BaseApplication.e(), "three_circle_medal_dialog_intercept_message"), AchieveMessage.class);
        if (achieveMessage2 == null) {
            e("three_circle_medal_dialog_intercept_message", achieveMessage);
            LogUtil.a("PLGACHIEVE_AchieveMedalDialogInterceptor", "saveThreeCircleMedalMessage: oldMedalMessage is null -> save");
            return;
        }
        long h = nsn.h(achieveMessage.getGainTime());
        long h2 = nsn.h(achieveMessage2.getGainTime());
        if (!nsj.a(h, h2)) {
            if (h > h2) {
                e("three_circle_medal_dialog_intercept_message", achieveMessage);
                LogUtil.a("PLGACHIEVE_AchieveMedalDialogInterceptor", "saveThreeCircleMedalMessage: !isSameDay -> save");
                return;
            } else {
                LogUtil.h("PLGACHIEVE_AchieveMedalDialogInterceptor", "saveThreeCircleMedalMessage: !isSameDay -> no save");
                return;
            }
        }
        int d = d(achieveMessage.acquireMedalType());
        int d2 = d(achieveMessage2.acquireMedalType());
        if (d < d2) {
            LogUtil.h("PLGACHIEVE_AchieveMedalDialogInterceptor", "saveThreeCircleMedalMessage: medalPriority -> no save");
            return;
        }
        if (d > d2) {
            e("three_circle_medal_dialog_intercept_message", achieveMessage);
            LogUtil.a("PLGACHIEVE_AchieveMedalDialogInterceptor", "saveThreeCircleMedalMessage: medalPriority -> save");
            return;
        }
        int acquireMedalLevel = achieveMessage.acquireMedalLevel();
        int acquireMedalLevel2 = achieveMessage2.acquireMedalLevel();
        if (acquireMedalLevel < acquireMedalLevel2) {
            LogUtil.h("PLGACHIEVE_AchieveMedalDialogInterceptor", "saveThreeCircleMedalMessage: medalLevel -> no save");
            return;
        }
        if (acquireMedalLevel > acquireMedalLevel2) {
            e("three_circle_medal_dialog_intercept_message", achieveMessage);
            LogUtil.a("PLGACHIEVE_AchieveMedalDialogInterceptor", "saveThreeCircleMedalMessage: medalLevel -> save");
        } else if (h >= h2) {
            e("three_circle_medal_dialog_intercept_message", achieveMessage);
            LogUtil.a("PLGACHIEVE_AchieveMedalDialogInterceptor", "saveThreeCircleMedalMessage: medalGainTime -> save");
        }
    }

    public static void d() {
        synchronized (e) {
            LogUtil.a("PLGACHIEVE_AchieveMedalDialogInterceptor", "showThreeCircleMedal");
            Context e2 = BaseApplication.e();
            String b2 = mct.b(e2, "three_circle_medal_dialog_intercept_message");
            if (TextUtils.isEmpty(b2)) {
                LogUtil.a("PLGACHIEVE_AchieveMedalDialogInterceptor", "showThreeCircleMedal: medalMessageJson is empty");
                return;
            }
            e("three_circle_medal_dialog_intercept_message", (AchieveMessage) null);
            AchieveMessage achieveMessage = (AchieveMessage) HiJsonUtil.e(b2, AchieveMessage.class);
            if (achieveMessage == null) {
                LogUtil.h("PLGACHIEVE_AchieveMedalDialogInterceptor", "showThreeCircleMedal: medalMessage is null");
            } else {
                if (!nsj.a(System.currentTimeMillis(), nsn.h(achieveMessage.getGainTime()))) {
                    LogUtil.h("PLGACHIEVE_AchieveMedalDialogInterceptor", "showThreeCircleMedal: !isSameDay");
                    return;
                }
                if ("C6_50".equals(achieveMessage.acquireMedalType())) {
                    achieveMessage.saveMedalType("C6");
                }
                b(e2, achieveMessage);
            }
        }
    }

    private static void a(String str, String str2) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("PLGACHIEVE_AchieveMedalDialogInterceptor", "removeMedalCacheMessage: medalId is empty");
            return;
        }
        if (TextUtils.isEmpty(str2)) {
            LogUtil.h("PLGACHIEVE_AchieveMedalDialogInterceptor", "removeMedalCacheMessage: medalMessageSpKey is empty");
            return;
        }
        String b2 = mct.b(BaseApplication.e(), str2);
        if (TextUtils.isEmpty(b2)) {
            return;
        }
        AchieveMessage achieveMessage = (AchieveMessage) HiJsonUtil.e(b2, AchieveMessage.class);
        if (achieveMessage == null) {
            LogUtil.h("PLGACHIEVE_AchieveMedalDialogInterceptor", "removeMedalCacheMessage: medalMessage is null");
        } else if (TextUtils.equals(achieveMessage.acquireMedalId(), str)) {
            LogUtil.h("PLGACHIEVE_AchieveMedalDialogInterceptor", "removeMedalCacheMessage: remove -> " + str);
            e(str2, (AchieveMessage) null);
        }
    }

    public static void c(String str) {
        synchronized (e) {
            a(str, "three_circle_medal_dialog_intercept_message");
        }
        synchronized (c) {
            for (AchieveMedalDialogScenario achieveMedalDialogScenario : AchieveMedalDialogScenario.values()) {
                a(str, achieveMedalDialogScenario.name());
            }
        }
    }

    private static void a(String str) {
        HashMap hashMap = new HashMap(1);
        hashMap.put("medalID", str);
        mcz d = meh.c(BaseApplication.e()).d(9, hashMap);
        if (d instanceof MedalConfigInfo) {
            MessageObject e2 = meh.c(BaseApplication.e()).e((MedalConfigInfo) d);
            if (mcv.d(BaseApplication.e()).getAdapter() != null) {
                LogUtil.a("PLGACHIEVE_AchieveMedalDialogInterceptor", "generateMessage: generate Medal Message");
                e2.setType(MessageConstant.ACQUIRE_MEDAL_TYPE);
                mcv.d(BaseApplication.e()).getAdapter().generateMessage(e2);
            }
        }
    }

    public static boolean d(AchieveMessage achieveMessage) {
        LogUtil.a("PLGACHIEVE_AchieveMedalDialogInterceptor", "interceptMedal");
        if (achieveMessage == null) {
            LogUtil.h("PLGACHIEVE_AchieveMedalDialogInterceptor", "interceptMedal: medalMessage is null");
            return false;
        }
        if (c(achieveMessage)) {
            LogUtil.h("PLGACHIEVE_AchieveMedalDialogInterceptor", "interceptMedal: interceptThreeCircleMedal -> true");
            return true;
        }
        String acquireMedalType = achieveMessage.acquireMedalType();
        AchieveMedalDialogScenario medalDialogScenario = AchieveMedalDialogScenario.getMedalDialogScenario(acquireMedalType);
        if (medalDialogScenario == null) {
            LogUtil.h("PLGACHIEVE_AchieveMedalDialogInterceptor", "interceptMedal: medalDialogScenario is null -> " + acquireMedalType);
            return false;
        }
        a(achieveMessage.acquireMedalId());
        if (b(acquireMedalType, medalDialogScenario) == 0) {
            LogUtil.h("PLGACHIEVE_AchieveMedalDialogInterceptor", "interceptMedal: priority is 0, no save -> " + acquireMedalType);
            return true;
        }
        synchronized (c) {
            e(achieveMessage, medalDialogScenario);
        }
        return true;
    }

    private static void e(AchieveMessage achieveMessage, AchieveMedalDialogScenario achieveMedalDialogScenario) {
        LogUtil.a("PLGACHIEVE_AchieveMedalDialogInterceptor", "saveMedalMessage: medalId -> " + achieveMessage.acquireMedalId());
        e(achieveMessage);
        String name = achieveMedalDialogScenario.name();
        AchieveMessage achieveMessage2 = (AchieveMessage) HiJsonUtil.e(mct.b(BaseApplication.e(), name), AchieveMessage.class);
        if (achieveMessage2 == null) {
            e(name, achieveMessage);
            LogUtil.a("PLGACHIEVE_AchieveMedalDialogInterceptor", "saveMedalMessage: oldMedalMessage is null -> save");
            return;
        }
        long h = nsn.h(achieveMessage.getGainTime());
        long h2 = nsn.h(achieveMessage2.getGainTime());
        if (!nsj.a(h, h2)) {
            if (h > h2) {
                e(name, achieveMessage);
                LogUtil.a("PLGACHIEVE_AchieveMedalDialogInterceptor", "saveMedalMessage: !isSameDay -> save");
                return;
            } else {
                LogUtil.h("PLGACHIEVE_AchieveMedalDialogInterceptor", "saveMedalMessage: !isSameDay -> no save");
                return;
            }
        }
        int b2 = b(achieveMessage.acquireMedalType(), achieveMedalDialogScenario);
        int b3 = b(achieveMessage2.acquireMedalType(), achieveMedalDialogScenario);
        if (b2 < b3) {
            LogUtil.h("PLGACHIEVE_AchieveMedalDialogInterceptor", "saveMedalMessage: medalPriority -> no save");
            return;
        }
        if (b2 > b3) {
            e(name, achieveMessage);
            LogUtil.a("PLGACHIEVE_AchieveMedalDialogInterceptor", "saveMedalMessage: medalPriority -> save");
            return;
        }
        int acquireMedalLevel = achieveMessage.acquireMedalLevel();
        int acquireMedalLevel2 = achieveMessage2.acquireMedalLevel();
        if (acquireMedalLevel < acquireMedalLevel2) {
            LogUtil.h("PLGACHIEVE_AchieveMedalDialogInterceptor", "saveMedalMessage: medalLevel -> no save");
            return;
        }
        if (acquireMedalLevel > acquireMedalLevel2) {
            e(name, achieveMessage);
            LogUtil.a("PLGACHIEVE_AchieveMedalDialogInterceptor", "saveMedalMessage: medalLevel -> save");
        } else if (h >= h2) {
            e(name, achieveMessage);
            LogUtil.a("PLGACHIEVE_AchieveMedalDialogInterceptor", "saveMedalMessage: medalGainTime -> save");
        }
    }

    public static boolean a(AchieveMedalDialogScenario achieveMedalDialogScenario) {
        synchronized (c) {
            LogUtil.a("PLGACHIEVE_AchieveMedalDialogInterceptor", "showMedalDialog");
            if (achieveMedalDialogScenario == null) {
                LogUtil.h("PLGACHIEVE_AchieveMedalDialogInterceptor", "showMedalDialog: medalDialogScenario is null");
                return false;
            }
            Context e2 = BaseApplication.e();
            String name = achieveMedalDialogScenario.name();
            String b2 = mct.b(e2, name);
            if (TextUtils.isEmpty(b2)) {
                LogUtil.a("PLGACHIEVE_AchieveMedalDialogInterceptor", "showMedalDialog: medalMessageJson is empty");
                return false;
            }
            e(name, (AchieveMessage) null);
            AchieveMessage achieveMessage = (AchieveMessage) HiJsonUtil.e(b2, AchieveMessage.class);
            if (achieveMessage == null) {
                LogUtil.h("PLGACHIEVE_AchieveMedalDialogInterceptor", "showMedalDialog: medalMessage is null");
                return false;
            }
            if (!nsj.a(System.currentTimeMillis(), nsn.h(achieveMessage.getGainTime()))) {
                LogUtil.h("PLGACHIEVE_AchieveMedalDialogInterceptor", "showMedalDialog: !isSameDay");
                return false;
            }
            b(e2, achieveMessage);
            return true;
        }
    }

    private static int b(String str, AchieveMedalDialogScenario achieveMedalDialogScenario) {
        HashMap<String, Integer> hashMap;
        Integer num = 0;
        if (!TextUtils.isEmpty(str) && achieveMedalDialogScenario != null && (hashMap = achieveMedalDialogScenario.medalPriority) != null) {
            num = hashMap.get(str);
        }
        if (num == null) {
            return 0;
        }
        return num.intValue();
    }

    private static void b(Context context, AchieveMessage achieveMessage) {
        if (context == null || achieveMessage == null) {
            LogUtil.h("PLGACHIEVE_AchieveMedalDialogInterceptor", "startMedalDialogActivity: context or medalMessage is null");
            return;
        }
        LogUtil.a("PLGACHIEVE_AchieveMedalDialogInterceptor", "startMedalDialogActivity: " + achieveMessage.acquireMedalId());
        try {
            Intent intent = new Intent();
            intent.setClassName(context, PersonalData.CLASS_NAME_PERSONAL_MEDAL_MESSAGE_DIALOG);
            intent.putExtra("message", achieveMessage);
            intent.putExtra("isMedalDialogInterceptorJump", true);
            intent.addFlags(268435456);
            context.startActivity(intent);
        } catch (ActivityNotFoundException unused) {
            LogUtil.b("PLGACHIEVE_AchieveMedalDialogInterceptor", "startMedalDialogActivity: activityNotFoundException");
        }
    }
}
