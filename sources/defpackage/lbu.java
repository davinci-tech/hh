package defpackage;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;
import com.huawei.health.R;
import com.huawei.indoorequip.datastruct.SupportDataRange;
import com.huawei.indoorequip.ui.view.CombineSportEquipItemDrawer;
import com.huawei.indoorequip.ui.view.SportEquipItemDetail;
import com.huawei.indoorequip.ui.view.SportEquipItemDrawer;
import com.huawei.openalliance.ad.constant.Constants;
import health.compact.a.CommonUtil;
import health.compact.a.util.LogUtil;
import java.util.Map;

/* loaded from: classes5.dex */
public class lbu extends CombineSportEquipItemDrawer {
    private int e;

    public lbu(Context context, int i, boolean z, SupportDataRange supportDataRange) {
        super(context, i, z, supportDataRange);
        this.e = i;
    }

    public void e(SportEquipItemDrawer sportEquipItemDrawer, Map<Integer, Object> map, int[] iArr, int i) {
        setPageItem(iArr, map);
        c(sportEquipItemDrawer, i);
    }

    public void e(SportEquipItemDrawer sportEquipItemDrawer, Map<Integer, Object> map, int[] iArr) {
        setPageItem(iArr, map);
        a(sportEquipItemDrawer);
    }

    private void a(SportEquipItemDrawer sportEquipItemDrawer) {
        if (koq.b(this.mItemDataList)) {
            LogUtil.c("Track_IDEQ_CombineSportEquipLangItemDrawer", "fillHrControlContainer mItemDataList is null");
            return;
        }
        if (sportEquipItemDrawer == null) {
            LogUtil.c("Track_IDEQ_CombineSportEquipLangItemDrawer", "fillHrControlContainer container is null");
            return;
        }
        sportEquipItemDrawer.removeAllViews();
        int size = this.mItemDataList.size();
        Object systemService = this.mContext.getSystemService(Constants.NATIVE_WINDOW_SUB_DIR);
        if (!(systemService instanceof WindowManager)) {
            LogUtil.c("Track_IDEQ_CombineSportEquipLangItemDrawer", "object is invalid type");
            return;
        }
        Display defaultDisplay = ((WindowManager) systemService).getDefaultDisplay();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        defaultDisplay.getMetrics(displayMetrics);
        int c = ((displayMetrics.widthPixels - nsn.c(this.mContext, 100.0f)) / 2) / size;
        int c2 = nsn.c(this.mContext, 80.0f);
        for (int i = 0; i < size; i++) {
            SportEquipItemDetail.e eVar = this.mItemDataList.get(i);
            SportEquipItemDetail sportEquipItemDetail = new SportEquipItemDetail(this.mContext, this.e, c, eVar.b());
            sportEquipItemDetail.setGroupSize(c, c2);
            sportEquipItemDetail.a(nsn.c(this.mContext, 20.0f));
            sportEquipItemDetail.c(nsn.c(this.mContext, 12.0f));
            sportEquipItemDetail.setItemView(eVar);
            if (sportEquipItemDrawer.getChildAt(i) != null) {
                sportEquipItemDrawer.removeViewAt(i);
            }
            sportEquipItemDrawer.addView(sportEquipItemDetail, i);
        }
    }

    public void c(SportEquipItemDrawer sportEquipItemDrawer, Map<Integer, Object> map, int[] iArr, int i) {
        setPageItem(iArr, map);
        c(sportEquipItemDrawer, i);
    }

    protected void c(SportEquipItemDrawer sportEquipItemDrawer, int i) {
        if (this.mItemDataList == null || this.mItemDataList.isEmpty()) {
            LogUtil.e("Track_IDEQ_CombineSportEquipLangItemDrawer", "fillTitleContainer mItemDataList is null");
            return;
        }
        if (sportEquipItemDrawer == null) {
            LogUtil.e("Track_IDEQ_CombineSportEquipLangItemDrawer", "fillTitleContainer container is null");
            return;
        }
        sportEquipItemDrawer.removeAllViews();
        int size = this.mItemDataList.size();
        if (size == 0) {
            return;
        }
        Object systemService = this.mContext.getSystemService(Constants.NATIVE_WINDOW_SUB_DIR);
        if (!(systemService instanceof WindowManager)) {
            LogUtil.e("Track_IDEQ_CombineSportEquipLangItemDrawer", "object is invalid type");
            return;
        }
        Display defaultDisplay = ((WindowManager) systemService).getDefaultDisplay();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        defaultDisplay.getMetrics(displayMetrics);
        int c = displayMetrics.heightPixels - nsn.c(this.mContext, 146.0f);
        int ceil = i > 0 ? (int) Math.ceil(size / i) : 0;
        boolean ag = nsn.ag(this.mContext);
        if (ag) {
            c = bVG_(displayMetrics, size);
            if (size > 6) {
                c -= nsn.c(this.mContext, 50.0f);
            }
        }
        e(sportEquipItemDrawer, size, ag, ceil > 0 ? c / ceil : 0);
    }

    private void e(SportEquipItemDrawer sportEquipItemDrawer, int i, boolean z, int i2) {
        for (int i3 = 0; i3 < i; i3++) {
            SportEquipItemDetail e = e(z, i, e(z, i, i3), i2, this.mItemDataList.get(i3));
            if (sportEquipItemDrawer.getChildAt(i3) != null) {
                sportEquipItemDrawer.removeViewAt(i3);
            }
            sportEquipItemDrawer.addView(e, i3);
        }
    }

    private int e(boolean z, int i, int i2) {
        if (z) {
            return b(i, i2);
        }
        return i > 6 ? 110 : 134;
    }

    private int b(int i, int i2) {
        if (i > 6) {
            return d(i2);
        }
        return i > 3 ? 136 : 180;
    }

    private int d(int i) {
        return i % 2 != 0 ? 110 : 136;
    }

    private SportEquipItemDetail e(boolean z, int i, int i2, int i3, SportEquipItemDetail.e eVar) {
        float f = i2;
        SportEquipItemDetail sportEquipItemDetail = new SportEquipItemDetail(this.mContext, this.e, nsn.c(this.mContext, f), eVar.b());
        sportEquipItemDetail.setGroupSize(nsn.c(this.mContext, f), i3);
        LogUtil.d("Track_IDEQ_CombineSportEquipLangItemDrawer", "SIZE IS ", Integer.valueOf(i), "  isTahitiModel", Boolean.valueOf(z));
        if (z) {
            b(i, sportEquipItemDetail, eVar);
        } else {
            a(i, sportEquipItemDetail, eVar);
        }
        if (!CommonUtil.u(this.mContext)) {
            sportEquipItemDetail.a(nsn.c(this.mContext, 18.0f));
            sportEquipItemDetail.c(nsn.c(this.mContext, 14.0f));
        }
        sportEquipItemDetail.setItemView(eVar);
        if (eVar.a() == null) {
            sportEquipItemDetail.d(0, this.mContext.getResources().getDimensionPixelSize(R.dimen._2131363589_res_0x7f0a0705));
        }
        return sportEquipItemDetail;
    }

    private void a(int i, SportEquipItemDetail sportEquipItemDetail, SportEquipItemDetail.e eVar) {
        if (eVar.a() != null && eVar.a().equals(this.mContext.getString(R.string._2130840274_res_0x7f020ad2))) {
            sportEquipItemDetail.a(nsn.c(this.mContext, 24.0f));
        }
        if (i > 6) {
            sportEquipItemDetail.a(nsn.c(this.mContext, 24.0f));
            sportEquipItemDetail.c(nsn.c(this.mContext, 14.0f));
        } else if (i > 3 && i <= 6) {
            sportEquipItemDetail.a(nsn.c(this.mContext, 30.0f));
            sportEquipItemDetail.c(nsn.c(this.mContext, 16.0f));
        } else {
            sportEquipItemDetail.a(nsn.c(this.mContext, 34.0f));
            sportEquipItemDetail.c(nsn.c(this.mContext, 14.0f));
        }
    }

    private void b(int i, SportEquipItemDetail sportEquipItemDetail, SportEquipItemDetail.e eVar) {
        if (eVar.a() != null && eVar.a().equals(this.mContext.getString(R.string._2130840274_res_0x7f020ad2))) {
            sportEquipItemDetail.a(nsn.c(this.mContext, 24.0f));
        }
        if (i > 6) {
            sportEquipItemDetail.a(nsn.c(this.mContext, 34.0f));
            sportEquipItemDetail.c(nsn.c(this.mContext, 14.0f));
        } else {
            if (i <= 3 || i > 6) {
                return;
            }
            sportEquipItemDetail.a(nsn.c(this.mContext, 36.0f));
            sportEquipItemDetail.c(nsn.c(this.mContext, 16.0f));
        }
    }

    private int bVG_(DisplayMetrics displayMetrics, int i) {
        int i2;
        int c;
        if (i <= 3) {
            i2 = displayMetrics.heightPixels;
            c = nsn.c(this.mContext, 316.0f);
        } else if (i <= 4) {
            i2 = displayMetrics.heightPixels;
            c = nsn.c(this.mContext, 388.0f);
        } else if (i <= 6) {
            i2 = displayMetrics.heightPixels;
            c = nsn.c(this.mContext, 388.0f);
        } else {
            i2 = displayMetrics.heightPixels;
            c = nsn.c(this.mContext, 316.0f);
        }
        return i2 - c;
    }
}
