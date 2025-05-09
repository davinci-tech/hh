package com.huawei.openalliance.ad.constant;

import java.util.Arrays;
import java.util.List;

/* loaded from: classes5.dex */
public interface TradeMode {
    public static final String PDB = "PDB";
    public static final String PD = "PD";
    public static final String NPD = "NPD";
    public static final String PA = "PA";
    public static final String RTB = "RTB";
    public static final List<String> OPTIONAL_VALUE = Arrays.asList(PDB, PD, NPD, PA, RTB);
    public static final List<String> DEFAULT_VALUE = Arrays.asList(RTB);
}
