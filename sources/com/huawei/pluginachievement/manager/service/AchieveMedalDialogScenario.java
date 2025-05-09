package com.huawei.pluginachievement.manager.service;

import com.huawei.hihealthservice.old.model.OldToNewMotionPath;
import com.huawei.ui.main.stories.nps.interactors.mode.TypeParams;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/* JADX WARN: Enum visitor error
jadx.core.utils.exceptions.JadxRuntimeException: Init of enum field 'RUNNING' uses external variables
	at jadx.core.dex.visitors.EnumVisitor.createEnumFieldByConstructor(EnumVisitor.java:451)
	at jadx.core.dex.visitors.EnumVisitor.processEnumFieldByRegister(EnumVisitor.java:395)
	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromFilledArray(EnumVisitor.java:324)
	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromInsn(EnumVisitor.java:262)
	at jadx.core.dex.visitors.EnumVisitor.convertToEnum(EnumVisitor.java:151)
	at jadx.core.dex.visitors.EnumVisitor.visit(EnumVisitor.java:100)
 */
/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* loaded from: classes6.dex */
public final class AchieveMedalDialogScenario {
    private static final /* synthetic */ AchieveMedalDialogScenario[] $VALUES;
    public static final AchieveMedalDialogScenario CYCLING;
    public static final AchieveMedalDialogScenario FITNESS;
    public static final AchieveMedalDialogScenario RUNNING;
    public static final AchieveMedalDialogScenario SKIPPING;
    public static final AchieveMedalDialogScenario STEPS;
    public static final int SUB_SCENARIO_FITNESS = 0;
    public static final int SUB_SCENARIO_STEPS = 1;
    public static final AchieveMedalDialogScenario WALKING;
    public final HashMap<String, Integer> medalPriority;
    public final List<Integer> subScenario;

    public static AchieveMedalDialogScenario valueOf(String str) {
        return (AchieveMedalDialogScenario) Enum.valueOf(AchieveMedalDialogScenario.class, str);
    }

    public static AchieveMedalDialogScenario[] values() {
        return (AchieveMedalDialogScenario[]) $VALUES.clone();
    }

    static {
        AchieveMedalDialogScenario achieveMedalDialogScenario = new AchieveMedalDialogScenario("STEPS", 0, new HashMap<String, Integer>() { // from class: com.huawei.pluginachievement.manager.service.AchieveMedalDialogScenario.4
            {
                put("D", 0);
                put(TypeParams.SEARCH_CODE, 1);
                put("B", 2);
            }
        }, Collections.singletonList(1));
        STEPS = achieveMedalDialogScenario;
        AchieveMedalDialogScenario achieveMedalDialogScenario2 = new AchieveMedalDialogScenario("FITNESS", 1, new HashMap<String, Integer>() { // from class: com.huawei.pluginachievement.manager.service.AchieveMedalDialogScenario.3
            {
                put("B5", 1);
                put("C3", 2);
            }
        }, Collections.singletonList(0));
        FITNESS = achieveMedalDialogScenario2;
        AchieveMedalDialogScenario achieveMedalDialogScenario3 = new AchieveMedalDialogScenario("WALKING", 2, new HashMap<String, Integer>() { // from class: com.huawei.pluginachievement.manager.service.AchieveMedalDialogScenario.1
            {
                put("B2", 1);
            }
        }, Arrays.asList(257, 281, 282, 282));
        WALKING = achieveMedalDialogScenario3;
        HashMap<String, Integer> hashMap = new HashMap<String, Integer>() { // from class: com.huawei.pluginachievement.manager.service.AchieveMedalDialogScenario.2
            {
                put("A", 1);
                put("C1", 2);
                put("B1", 3);
            }
        };
        Integer valueOf = Integer.valueOf(OldToNewMotionPath.SPORT_TYPE_CROSS_COUNTRY_RACE);
        AchieveMedalDialogScenario achieveMedalDialogScenario4 = new AchieveMedalDialogScenario("RUNNING", 3, hashMap, Arrays.asList(258, 264, valueOf));
        RUNNING = achieveMedalDialogScenario4;
        AchieveMedalDialogScenario achieveMedalDialogScenario5 = new AchieveMedalDialogScenario("CYCLING", 4, new HashMap<String, Integer>() { // from class: com.huawei.pluginachievement.manager.service.AchieveMedalDialogScenario.5
            {
                put("A2", 1);
                put("C2", 1);
                put("B3", 2);
            }
        }, Arrays.asList(259, Integer.valueOf(OldToNewMotionPath.SPORT_TYPE_INDOOR_BIKE), valueOf));
        CYCLING = achieveMedalDialogScenario5;
        AchieveMedalDialogScenario achieveMedalDialogScenario6 = new AchieveMedalDialogScenario("SKIPPING", 5, new HashMap<String, Integer>() { // from class: com.huawei.pluginachievement.manager.service.AchieveMedalDialogScenario.7
            {
                put("A5", 1);
                put("C5", 1);
                put("B7", 2);
            }
        }, Collections.singletonList(283));
        SKIPPING = achieveMedalDialogScenario6;
        $VALUES = new AchieveMedalDialogScenario[]{achieveMedalDialogScenario, achieveMedalDialogScenario2, achieveMedalDialogScenario3, achieveMedalDialogScenario4, achieveMedalDialogScenario5, achieveMedalDialogScenario6};
    }

    private AchieveMedalDialogScenario(String str, int i, HashMap hashMap, List list) {
        this.medalPriority = hashMap;
        this.subScenario = list;
    }

    public static AchieveMedalDialogScenario getMedalDialogScenario(int i) {
        for (AchieveMedalDialogScenario achieveMedalDialogScenario : values()) {
            List<Integer> list = achieveMedalDialogScenario.subScenario;
            if (list != null && list.contains(Integer.valueOf(i))) {
                return achieveMedalDialogScenario;
            }
        }
        return null;
    }

    public static AchieveMedalDialogScenario getMedalDialogScenario(String str) {
        for (AchieveMedalDialogScenario achieveMedalDialogScenario : values()) {
            HashMap<String, Integer> hashMap = achieveMedalDialogScenario.medalPriority;
            if (hashMap != null && hashMap.containsKey(str)) {
                return achieveMedalDialogScenario;
            }
        }
        return null;
    }
}
