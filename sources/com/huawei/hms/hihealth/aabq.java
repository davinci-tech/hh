package com.huawei.hms.hihealth;

import com.huawei.hms.health.aabz;
import com.huawei.hms.support.api.entity.auth.Scope;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* loaded from: classes4.dex */
public final class aabq {
    private static final Map<Scope, Scope> aab;

    public static Set<Scope> aab(List<Scope> list) {
        if (list == null) {
            aabz.aab("ScopeWrapper", "scopeList is null.");
            return Collections.emptySet();
        }
        StringBuilder aab2 = com.huawei.hms.health.aab.aab("input scopeList: ");
        aab2.append(list.toString());
        aab2.toString();
        HashSet hashSet = new HashSet();
        for (Scope scope : list) {
            Scope scope2 = aab.get(scope);
            if (scope2 == null) {
                aabz.aabc("ScopeWrapper", "input scope is invalid");
                scope2 = new Scope("invalid scope");
            }
            if (scope2.equals(scope) || !list.contains(scope2)) {
                hashSet.add(scope);
            }
        }
        StringBuilder aab3 = com.huawei.hms.health.aab.aab("scopeHashSet: ");
        aab3.append(hashSet.toString());
        aab3.toString();
        return hashSet;
    }

    static {
        HashMap hashMap = new HashMap();
        hashMap.put(HuaweiHiHealth.SCOPE_HEALTHKIT_HEIGHTWEIGHT_READ, HuaweiHiHealth.SCOPE_HEALTHKIT_HEIGHTWEIGHT_BOTH);
        hashMap.put(HuaweiHiHealth.SCOPE_HEALTHKIT_HEIGHTWEIGHT_WRITE, HuaweiHiHealth.SCOPE_HEALTHKIT_HEIGHTWEIGHT_BOTH);
        Scope scope = HuaweiHiHealth.SCOPE_HEALTHKIT_HEIGHTWEIGHT_BOTH;
        hashMap.put(scope, scope);
        hashMap.put(HuaweiHiHealth.SCOPE_HEALTHKIT_STEP_READ, HuaweiHiHealth.SCOPE_HEALTHKIT_STEP_BOTH);
        hashMap.put(HuaweiHiHealth.SCOPE_HEALTHKIT_STEP_WRITE, HuaweiHiHealth.SCOPE_HEALTHKIT_STEP_BOTH);
        Scope scope2 = HuaweiHiHealth.SCOPE_HEALTHKIT_STEP_BOTH;
        hashMap.put(scope2, scope2);
        hashMap.put(HuaweiHiHealth.SCOPE_HEALTHKIT_LOCATION_READ, HuaweiHiHealth.SCOPE_HEALTHKIT_LOCATION_BOTH);
        hashMap.put(HuaweiHiHealth.SCOPE_HEALTHKIT_LOCATION_WRITE, HuaweiHiHealth.SCOPE_HEALTHKIT_LOCATION_BOTH);
        Scope scope3 = HuaweiHiHealth.SCOPE_HEALTHKIT_LOCATION_BOTH;
        hashMap.put(scope3, scope3);
        hashMap.put(HuaweiHiHealth.SCOPE_HEALTHKIT_HEARTRATE_READ, HuaweiHiHealth.SCOPE_HEALTHKIT_HEARTRATE_BOTH);
        hashMap.put(HuaweiHiHealth.SCOPE_HEALTHKIT_HEARTRATE_WRITE, HuaweiHiHealth.SCOPE_HEALTHKIT_HEARTRATE_BOTH);
        Scope scope4 = HuaweiHiHealth.SCOPE_HEALTHKIT_HEARTRATE_BOTH;
        hashMap.put(scope4, scope4);
        hashMap.put(HuaweiHiHealth.SCOPE_HEALTHKIT_BLOODGLUCOSE_READ, HuaweiHiHealth.SCOPE_HEALTHKIT_BLOODGLUCOSE_BOTH);
        hashMap.put(HuaweiHiHealth.SCOPE_HEALTHKIT_BLOODGLUCOSE_WRITE, HuaweiHiHealth.SCOPE_HEALTHKIT_BLOODGLUCOSE_BOTH);
        Scope scope5 = HuaweiHiHealth.SCOPE_HEALTHKIT_BLOODGLUCOSE_BOTH;
        hashMap.put(scope5, scope5);
        hashMap.put(HuaweiHiHealth.SCOPE_HEALTHKIT_DISTANCE_READ, HuaweiHiHealth.SCOPE_HEALTHKIT_DISTANCE_BOTH);
        hashMap.put(HuaweiHiHealth.SCOPE_HEALTHKIT_DISTANCE_WRITE, HuaweiHiHealth.SCOPE_HEALTHKIT_DISTANCE_BOTH);
        Scope scope6 = HuaweiHiHealth.SCOPE_HEALTHKIT_DISTANCE_BOTH;
        hashMap.put(scope6, scope6);
        hashMap.put(HuaweiHiHealth.SCOPE_HEALTHKIT_SPEED_READ, HuaweiHiHealth.SCOPE_HEALTHKIT_SPEED_BOTH);
        hashMap.put(HuaweiHiHealth.SCOPE_HEALTHKIT_SPEED_WRITE, HuaweiHiHealth.SCOPE_HEALTHKIT_SPEED_BOTH);
        Scope scope7 = HuaweiHiHealth.SCOPE_HEALTHKIT_SPEED_BOTH;
        hashMap.put(scope7, scope7);
        hashMap.put(HuaweiHiHealth.SCOPE_HEALTHKIT_CALORIES_READ, HuaweiHiHealth.SCOPE_HEALTHKIT_CALORIES_BOTH);
        hashMap.put(HuaweiHiHealth.SCOPE_HEALTHKIT_CALORIES_WRITE, HuaweiHiHealth.SCOPE_HEALTHKIT_CALORIES_BOTH);
        Scope scope8 = HuaweiHiHealth.SCOPE_HEALTHKIT_CALORIES_BOTH;
        hashMap.put(scope8, scope8);
        hashMap.put(HuaweiHiHealth.SCOPE_HEALTHKIT_PULMONARY_READ, HuaweiHiHealth.SCOPE_HEALTHKIT_PULMONARY_BOTH);
        hashMap.put(HuaweiHiHealth.SCOPE_HEALTHKIT_PULMONARY_WRITE, HuaweiHiHealth.SCOPE_HEALTHKIT_PULMONARY_BOTH);
        Scope scope9 = HuaweiHiHealth.SCOPE_HEALTHKIT_PULMONARY_BOTH;
        hashMap.put(scope9, scope9);
        hashMap.put(HuaweiHiHealth.SCOPE_HEALTHKIT_STRENGTH_READ, HuaweiHiHealth.SCOPE_HEALTHKIT_STRENGTH_BOTH);
        hashMap.put(HuaweiHiHealth.SCOPE_HEALTHKIT_STRENGTH_WRITE, HuaweiHiHealth.SCOPE_HEALTHKIT_STRENGTH_BOTH);
        Scope scope10 = HuaweiHiHealth.SCOPE_HEALTHKIT_STRENGTH_BOTH;
        hashMap.put(scope10, scope10);
        hashMap.put(HuaweiHiHealth.SCOPE_HEALTHKIT_ACTIVITY_READ, HuaweiHiHealth.SCOPE_HEALTHKIT_ACTIVITY_BOTH);
        hashMap.put(HuaweiHiHealth.SCOPE_HEALTHKIT_ACTIVITY_WRITE, HuaweiHiHealth.SCOPE_HEALTHKIT_ACTIVITY_BOTH);
        Scope scope11 = HuaweiHiHealth.SCOPE_HEALTHKIT_ACTIVITY_BOTH;
        hashMap.put(scope11, scope11);
        hashMap.put(HuaweiHiHealth.SCOPE_HEALTHKIT_BODYFAT_READ, HuaweiHiHealth.SCOPE_HEALTHKIT_BODYFAT_BOTH);
        hashMap.put(HuaweiHiHealth.SCOPE_HEALTHKIT_BODYFAT_WRITE, HuaweiHiHealth.SCOPE_HEALTHKIT_BODYFAT_BOTH);
        Scope scope12 = HuaweiHiHealth.SCOPE_HEALTHKIT_BODYFAT_BOTH;
        hashMap.put(scope12, scope12);
        hashMap.put(HuaweiHiHealth.SCOPE_HEALTHKIT_SLEEP_READ, HuaweiHiHealth.SCOPE_HEALTHKIT_SLEEP_BOTH);
        hashMap.put(HuaweiHiHealth.SCOPE_HEALTHKIT_SLEEP_WRITE, HuaweiHiHealth.SCOPE_HEALTHKIT_SLEEP_BOTH);
        Scope scope13 = HuaweiHiHealth.SCOPE_HEALTHKIT_SLEEP_BOTH;
        hashMap.put(scope13, scope13);
        hashMap.put(HuaweiHiHealth.SCOPE_HEALTHKIT_NUTRITION_READ, HuaweiHiHealth.SCOPE_HEALTHKIT_NUTRITION_BOTH);
        hashMap.put(HuaweiHiHealth.SCOPE_HEALTHKIT_NUTRITION_WRITE, HuaweiHiHealth.SCOPE_HEALTHKIT_NUTRITION_BOTH);
        Scope scope14 = HuaweiHiHealth.SCOPE_HEALTHKIT_NUTRITION_BOTH;
        hashMap.put(scope14, scope14);
        hashMap.put(HuaweiHiHealth.SCOPE_HEALTHKIT_BLOODPRESSURE_READ, HuaweiHiHealth.SCOPE_HEALTHKIT_BLOODPRESSURE_BOTH);
        hashMap.put(HuaweiHiHealth.SCOPE_HEALTHKIT_BLOODPRESSURE_WRITE, HuaweiHiHealth.SCOPE_HEALTHKIT_BLOODPRESSURE_BOTH);
        Scope scope15 = HuaweiHiHealth.SCOPE_HEALTHKIT_BLOODPRESSURE_BOTH;
        hashMap.put(scope15, scope15);
        hashMap.put(HuaweiHiHealth.SCOPE_HEALTHKIT_OXYGENSTATURATION_READ, HuaweiHiHealth.SCOPE_HEALTHKIT_OXYGENSTATURATION_BOTH);
        hashMap.put(HuaweiHiHealth.SCOPE_HEALTHKIT_OXYGENSTATURATION_WRITE, HuaweiHiHealth.SCOPE_HEALTHKIT_OXYGENSTATURATION_BOTH);
        Scope scope16 = HuaweiHiHealth.SCOPE_HEALTHKIT_OXYGENSTATURATION_BOTH;
        hashMap.put(scope16, scope16);
        hashMap.put(HuaweiHiHealth.SCOPE_HEALTHKIT_OXYGEN_SATURATION_READ, HuaweiHiHealth.SCOPE_HEALTHKIT_OXYGEN_SATURATION_BOTH);
        hashMap.put(HuaweiHiHealth.SCOPE_HEALTHKIT_OXYGEN_SATURATION_WRITE, HuaweiHiHealth.SCOPE_HEALTHKIT_OXYGEN_SATURATION_BOTH);
        Scope scope17 = HuaweiHiHealth.SCOPE_HEALTHKIT_OXYGEN_SATURATION_BOTH;
        hashMap.put(scope17, scope17);
        hashMap.put(HuaweiHiHealth.SCOPE_HEALTHKIT_BODYTEMPERATURE_READ, HuaweiHiHealth.SCOPE_HEALTHKIT_BODYTEMPERATURE_BOTH);
        hashMap.put(HuaweiHiHealth.SCOPE_HEALTHKIT_BODYTEMPERATURE_WRITE, HuaweiHiHealth.SCOPE_HEALTHKIT_BODYTEMPERATURE_BOTH);
        Scope scope18 = HuaweiHiHealth.SCOPE_HEALTHKIT_BODYTEMPERATURE_BOTH;
        hashMap.put(scope18, scope18);
        hashMap.put(HuaweiHiHealth.SCOPE_HEALTHKIT_REPRODUCTIVE_WRITE, HuaweiHiHealth.SCOPE_HEALTHKIT_REPRODUCTIVE_BOTH);
        hashMap.put(HuaweiHiHealth.SCOPE_HEALTHKIT_REPRODUCTIVE_READ, HuaweiHiHealth.SCOPE_HEALTHKIT_REPRODUCTIVE_BOTH);
        Scope scope19 = HuaweiHiHealth.SCOPE_HEALTHKIT_REPRODUCTIVE_BOTH;
        hashMap.put(scope19, scope19);
        hashMap.put(HuaweiHiHealth.SCOPE_HEALTHKIT_ACTIVITY_RECORD_READ, HuaweiHiHealth.SCOPE_HEALTHKIT_ACTIVITY_RECORD_BOTH);
        hashMap.put(HuaweiHiHealth.SCOPE_HEALTHKIT_ACTIVITY_RECORD_WRITE, HuaweiHiHealth.SCOPE_HEALTHKIT_ACTIVITY_RECORD_BOTH);
        Scope scope20 = HuaweiHiHealth.SCOPE_HEALTHKIT_ACTIVITY_RECORD_BOTH;
        hashMap.put(scope20, scope20);
        hashMap.put(HuaweiHiHealth.SCOPE_HEALTHKIT_STRESS_READ, HuaweiHiHealth.SCOPE_HEALTHKIT_STRESS_BOTH);
        hashMap.put(HuaweiHiHealth.SCOPE_HEALTHKIT_STRESS_WRITE, HuaweiHiHealth.SCOPE_HEALTHKIT_STRESS_BOTH);
        Scope scope21 = HuaweiHiHealth.SCOPE_HEALTHKIT_STRESS_BOTH;
        hashMap.put(scope21, scope21);
        hashMap.put(HuaweiHiHealth.SCOPE_HEALTHKIT_HEARTHEALTH_READ, HuaweiHiHealth.SCOPE_HEALTHKIT_HEARTHEALTH_BOTH);
        hashMap.put(HuaweiHiHealth.SCOPE_HEALTHKIT_HEARTHEALTH_WRITE, HuaweiHiHealth.SCOPE_HEALTHKIT_HEARTHEALTH_BOTH);
        Scope scope22 = HuaweiHiHealth.SCOPE_HEALTHKIT_HEARTHEALTH_BOTH;
        hashMap.put(scope22, scope22);
        aab = Collections.unmodifiableMap(hashMap);
    }
}
