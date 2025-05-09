package com.huawei.hihealth.data.type;

import android.util.SparseArray;

/* loaded from: classes.dex */
public class HiDeviceType {

    /* renamed from: a, reason: collision with root package name */
    private static final SparseArray<String> f4117a;

    static {
        SparseArray<String> sparseArray = new SparseArray<>();
        f4117a = sparseArray;
        sparseArray.put(21, "B1手环");
        sparseArray.put(22, "B2手环");
        sparseArray.put(23, "color band");
        sparseArray.put(28, "糖护士血糖仪");
        sparseArray.put(24, "康康血压");
        sparseArray.put(29, "乐心血压");
        sparseArray.put(31, "乐心体重计");
        sparseArray.put(26, "Mio");
        sparseArray.put(25, "木木血压");
        sparseArray.put(27, "有品体重计");
        sparseArray.put(30, "手机记步");
        sparseArray.put(1, "手动输入");
        sparseArray.put(33, "欧姆龙血压计");
        sparseArray.put(34, "强生血糖仪");
        sparseArray.put(32, "手机GPS");
        sparseArray.put(35, "B0手环");
        sparseArray.put(36, "w1手表");
        sparseArray.put(37, "W1_INTERNAL");
        sparseArray.put(38, "N1");
        sparseArray.put(39, "B3");
        sparseArray.put(40, "JOHNSON1");
        sparseArray.put(41, "METIS");
        sparseArray.put(42, "NYX手环");
        sparseArray.put(43, "R1耳机");
        sparseArray.put(44, "A1_PLUS");
        sparseArray.put(45, "GRUS");
        sparseArray.put(46, "LEO");
        sparseArray.put(47, "ERIS");
        sparseArray.put(48, "华为体脂称");
        sparseArray.put(50, "JOHNSON2");
        sparseArray.put(51, "AW600");
        sparseArray.put(52, "jabra耳机");
        sparseArray.put(53, "polar心率带");
        sparseArray.put(54, "鱼跃设备");
        sparseArray.put(55, "S1pro");
        sparseArray.put(56, "云康宝智能体脂称");
        sparseArray.put(61, "k1手环");
        sparseArray.put(62, "K2手环");
        sparseArray.put(57, "华为智能体脂秤");
        sparseArray.put(70, "宝莱特血压计");
        sparseArray.put(71, "怡成血糖仪");
        sparseArray.put(72, "鱼跃血糖仪");
        sparseArray.put(73, "香山体脂称");
        sparseArray.put(74, "JANUS");
        sparseArray.put(75, "荣耀有线耳机");
        sparseArray.put(76, "NYX-B10");
        sparseArray.put(77, "CRIUS BLE手环");
        sparseArray.put(78, "TERRA BLE手环");
        sparseArray.put(79, "TALOS");
        sparseArray.put(80, "FORTUNA");
        sparseArray.put(81, "HUAWEI_AW70");
        sparseArray.put(83, "HONOR_AW70");
        sparseArray.put(82, "华为智能体脂秤-云版");
        sparseArray.put(90, "HUAWEI_AW70_PRO");
        sparseArray.put(91, "HONOR_AW70_PRO");
        sparseArray.put(92, "HONOR_ANDES_B19");
        sparseArray.put(93, "HUAWEI_ANDES_B29");
    }

    public static String e(int i) {
        String str = f4117a.get(i);
        return str == null ? "" : str;
    }
}
