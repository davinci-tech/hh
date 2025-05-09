package com.huawei.health.suggestion.model.customcourse;

import android.content.Context;
import com.huawei.health.R;
import com.huawei.pluginfitnessadvice.Attribute;
import com.huawei.pluginfitnessadvice.TargetConfig;
import defpackage.gdg;
import health.compact.a.UnitUtil;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes4.dex */
public class TypeConfig {
    public static List<gdg> getTargetTypeList(Context context, int i) {
        ArrayList arrayList = new ArrayList(5);
        if (context == null) {
            return arrayList;
        }
        gdg createChoiceModel = createChoiceModel(context, R.string._2130848438_res_0x7f022ab6, 1);
        gdg createChoiceModel2 = createChoiceModel(context, R.string._2130848437_res_0x7f022ab5, 0);
        gdg createChoiceModel3 = createChoiceModel(context, R.string._2130848731_res_0x7f022bdb, 3);
        gdg createChoiceModel4 = createChoiceModel(context, R.string._2130841430_res_0x7f020f56, 5);
        gdg createChoiceModel5 = createChoiceModel(context, R.string._2130848598_res_0x7f022b56, 255);
        if (i == 0) {
            createChoiceModel2.a(true);
        } else if (i == 1) {
            createChoiceModel.a(true);
        } else if (i == 3) {
            createChoiceModel3.a(true);
        } else if (i == 4 || i == 5) {
            createChoiceModel4.a(true);
        } else if (i == 255) {
            createChoiceModel5.a(true);
        }
        arrayList.add(createChoiceModel);
        arrayList.add(createChoiceModel2);
        arrayList.add(createChoiceModel3);
        arrayList.add(createChoiceModel4);
        arrayList.add(createChoiceModel5);
        return arrayList;
    }

    private static gdg createChoiceModel(Context context, int i, int i2) {
        Attribute attribute = new Attribute();
        attribute.saveName(context.getResources().getString(i));
        attribute.saveId(Integer.toString(i2));
        return new gdg(false, attribute);
    }

    public static List<gdg> getIntensityTypeList(Context context, int i) {
        ArrayList arrayList = new ArrayList(6);
        if (context == null) {
            return arrayList;
        }
        gdg createChoiceModel = createChoiceModel(context, R.string._2130848598_res_0x7f022b56, 255);
        gdg createChoiceModel2 = createChoiceModel(context, R.string._2130848714_res_0x7f022bca, 17);
        gdg createChoiceModel3 = createChoiceModel(context, R.string._2130848703_res_0x7f022bbf, 13);
        gdg createChoiceModel4 = createChoiceModel(context, R.string._2130848717_res_0x7f022bcd, 15);
        gdg createChoiceModel5 = createChoiceModel(context, R.string._2130848716_res_0x7f022bcc, 16);
        gdg createChoiceModel6 = createChoiceModel(context, R.string._2130848715_res_0x7f022bcb, 1);
        if (i == 1) {
            createChoiceModel6.a(true);
        } else if (i == 13) {
            createChoiceModel3.a(true);
        } else if (i == 255) {
            createChoiceModel.a(true);
        } else {
            switch (i) {
                case 15:
                    createChoiceModel4.a(true);
                    break;
                case 16:
                    createChoiceModel5.a(true);
                    break;
                case 17:
                    createChoiceModel2.a(true);
                    break;
            }
        }
        arrayList.add(createChoiceModel);
        arrayList.add(createChoiceModel2);
        arrayList.add(createChoiceModel3);
        arrayList.add(createChoiceModel4);
        arrayList.add(createChoiceModel5);
        arrayList.add(createChoiceModel6);
        return arrayList;
    }

    public static String[] getRepeatTimeArray(Context context, int i) {
        int i2 = i - 1;
        String[] strArr = new String[i2];
        for (int i3 = 0; i3 < i2; i3++) {
            int i4 = i3 + 2;
            strArr[i3] = context.getResources().getQuantityString(R.plurals._2130903395_res_0x7f030163, i4, Integer.valueOf(i4));
        }
        return strArr;
    }

    public static void setDefaultImperialData(TargetConfig targetConfig) {
        if (UnitUtil.h()) {
            if (targetConfig.getId().equals(Integer.toString(0))) {
                targetConfig.setValueL(UnitUtil.d(1.0d, 3) * 1000.0d);
            }
            if (targetConfig.getId().equals(Integer.toString(15))) {
                targetConfig.setValueL(UnitUtil.e(630, 3) * 1000.0d);
                targetConfig.setValueH(UnitUtil.e(720, 3) * 1000.0d);
            }
        }
    }
}
