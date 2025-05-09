package defpackage;

import android.content.Context;
import com.huawei.health.R;
import com.huawei.hwlogsmodel.LogUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* loaded from: classes6.dex */
public class oai {

    /* renamed from: a, reason: collision with root package name */
    private static oai f15580a;
    private long e = 0;
    private HashMap<Integer, Integer> c = new HashMap(16) { // from class: oai.1
        private static final long serialVersionUID = -4282859716577390994L;

        {
            put(1, Integer.valueOf(R.string._2130842246_res_0x7f021286));
            put(2, Integer.valueOf(R.string._2130842248_res_0x7f021288));
            put(3, Integer.valueOf(R.string._2130842249_res_0x7f021289));
            put(4, Integer.valueOf(R.string._2130842250_res_0x7f02128a));
            put(5, Integer.valueOf(R.string._2130842251_res_0x7f02128b));
            put(6, Integer.valueOf(R.string._2130842252_res_0x7f02128c));
            put(7, Integer.valueOf(R.string._2130842253_res_0x7f02128d));
            put(8, Integer.valueOf(R.string._2130842254_res_0x7f02128e));
            put(9, Integer.valueOf(R.string._2130842255_res_0x7f02128f));
            put(10, Integer.valueOf(R.string._2130842236_res_0x7f02127c));
            put(11, Integer.valueOf(R.string._2130842237_res_0x7f02127d));
            put(12, Integer.valueOf(R.string._2130842238_res_0x7f02127e));
            put(13, Integer.valueOf(R.string._2130842239_res_0x7f02127f));
            put(14, Integer.valueOf(R.string.IDS_settings_one_level_menu_settings_item_text_id14));
            put(15, Integer.valueOf(R.string._2130842241_res_0x7f021281));
            put(16, Integer.valueOf(R.string._2130842242_res_0x7f021282));
            put(17, Integer.valueOf(R.string._2130842243_res_0x7f021283));
            put(18, Integer.valueOf(R.string._2130842244_res_0x7f021284));
            put(19, Integer.valueOf(R.string._2130842245_res_0x7f021285));
            put(20, Integer.valueOf(R.string._2130842247_res_0x7f021287));
            put(21, Integer.valueOf(R.string._2130842143_res_0x7f02121f));
            put(22, Integer.valueOf(R.string._2130842142_res_0x7f02121e));
            put(23, Integer.valueOf(R.string._2130842144_res_0x7f021220));
            put(24, Integer.valueOf(R.string._2130842458_res_0x7f02135a));
            put(25, Integer.valueOf(R.string._2130841754_res_0x7f02109a));
            put(26, Integer.valueOf(R.string._2130842459_res_0x7f02135b));
            put(27, Integer.valueOf(R.string._2130842460_res_0x7f02135c));
            put(28, Integer.valueOf(R.string.IDS_settings_steps));
            put(29, Integer.valueOf(R.string._2130841530_res_0x7f020fba));
            put(30, Integer.valueOf(R.string._2130841791_res_0x7f0210bf));
            put(31, Integer.valueOf(R.string._2130837803_res_0x7f02012b));
            put(32, Integer.valueOf(R.string._2130842746_res_0x7f02147a));
            put(33, Integer.valueOf(R.string._2130842404_res_0x7f021324));
            put(34, Integer.valueOf(R.string.IDS_hw_health_blood_oxygen));
            put(35, Integer.valueOf(R.string._2130843425_res_0x7f021721));
            put(36, Integer.valueOf(R.string._2130843426_res_0x7f021722));
        }
    };

    public int d(byte b) {
        if ((b & 128) <= 0) {
            return b;
        }
        int i = b - 128;
        return i < 0 ? b + 128 : i;
    }

    public static oai a() {
        oai oaiVar;
        synchronized (oai.class) {
            if (f15580a == null) {
                f15580a = new oai();
            }
            oaiVar = f15580a;
        }
        return oaiVar;
    }

    public String a(Context context, int i) {
        if (this.c.containsKey(Integer.valueOf(i))) {
            return context.getResources().getString(this.c.get(Integer.valueOf(i)).intValue());
        }
        return context.getResources().getString(R.string._2130842246_res_0x7f021286);
    }

    public boolean e(int i) {
        return this.c.containsKey(Integer.valueOf(i));
    }

    public ArrayList<Integer> e(ArrayList<Integer> arrayList, ArrayList<Integer> arrayList2) {
        HashMap hashMap = new HashMap(arrayList.size() + arrayList2.size());
        ArrayList<Integer> arrayList3 = new ArrayList<>(16);
        if (arrayList2.size() > arrayList.size()) {
            arrayList2 = arrayList;
            arrayList = arrayList2;
        }
        Iterator<Integer> it = arrayList.iterator();
        while (it.hasNext()) {
            hashMap.put(it.next(), 1);
        }
        Iterator<Integer> it2 = arrayList2.iterator();
        while (it2.hasNext()) {
            Integer next = it2.next();
            Integer num = (Integer) hashMap.get(next);
            if (num != null) {
                hashMap.put(next, Integer.valueOf(num.intValue() + 1));
            } else {
                hashMap.put(next, 1);
            }
        }
        for (Map.Entry entry : hashMap.entrySet()) {
            if (((Integer) entry.getValue()).intValue() == 1) {
                arrayList3.add((Integer) entry.getKey());
            }
        }
        return arrayList3;
    }

    public boolean e() {
        long currentTimeMillis = System.currentTimeMillis();
        if (currentTimeMillis - this.e < 1000) {
            LogUtil.a("OneLevelMenuInteractor", "onClick", ">_< >_< click too much");
            this.e = currentTimeMillis;
            return true;
        }
        this.e = currentTimeMillis;
        return false;
    }
}
