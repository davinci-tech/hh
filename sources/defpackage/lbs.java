package defpackage;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import com.huawei.health.R;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.indoorequip.datastruct.SupportDataRange;
import com.huawei.indoorequip.ui.view.CombineSportEquipItemDrawer;
import com.huawei.indoorequip.ui.view.SportEquipItemDetail;
import com.huawei.indoorequip.ui.view.SportEquipItemDrawer;
import com.huawei.openalliance.ad.constant.Constants;
import java.util.Map;

/* loaded from: classes5.dex */
public class lbs extends CombineSportEquipItemDrawer {
    private float b;
    private float c;
    private int d;
    private float e;

    public lbs(Context context, int i, boolean z, float f, float f2, SupportDataRange supportDataRange) {
        super(context, i, z, supportDataRange);
        this.c = 1920.0f;
        this.d = i;
        this.b = f;
        this.e = f2;
    }

    public void d(SportEquipItemDrawer sportEquipItemDrawer, Map<Integer, Object> map, int[] iArr, int i) {
        setPageItem(iArr, map);
        b(sportEquipItemDrawer, i);
    }

    public void b(SportEquipItemDrawer sportEquipItemDrawer, Map<Integer, Object> map, int[] iArr, int i) {
        setPageItem(iArr, map);
        b(sportEquipItemDrawer, i);
    }

    public void a(SportEquipItemDrawer sportEquipItemDrawer, Map<Integer, Object> map, int[] iArr) {
        setPageItem(iArr, map);
        e(sportEquipItemDrawer);
    }

    public void c(float f) {
        this.c = f;
    }

    private void e(SportEquipItemDrawer sportEquipItemDrawer) {
        if (koq.b(this.mItemDataList)) {
            LogUtil.h("Track_IDEQ_CombineSportEquipTvItemDrawer", "fillHrControlContainer mItemDataList is null");
            return;
        }
        if (sportEquipItemDrawer == null) {
            LogUtil.h("Track_IDEQ_CombineSportEquipTvItemDrawer", "fillHrControlContainer container is null");
            return;
        }
        sportEquipItemDrawer.removeAllViews();
        int size = this.mItemDataList.size();
        int a2 = (int) (this.c - (this.b * a(this.mContext, 24.0f)));
        int i = a2 / size;
        int a3 = ((int) this.b) * a(this.mContext, 80.0f);
        LogUtil.a("Track_IDEQ_CombineSportEquipTvItemDrawer", "scale:", Float.valueOf(this.b), " mDisplayWidth", Float.valueOf(this.c), " itemAllWidth:", Integer.valueOf(a2), " itemWidth:", Integer.valueOf(i), " itemHeight:", Integer.valueOf(a3));
        for (int i2 = 0; i2 < size; i2++) {
            SportEquipItemDetail a4 = a(i, a3, this.mItemDataList.get(i2));
            if (sportEquipItemDrawer.getChildAt(i2) != null) {
                sportEquipItemDrawer.removeViewAt(i2);
            }
            sportEquipItemDrawer.addView(a4, i2);
        }
    }

    private SportEquipItemDetail a(int i, int i2, SportEquipItemDetail.e eVar) {
        SportEquipItemDetail sportEquipItemDetail = new SportEquipItemDetail(this.mContext, this.d, i, eVar.b());
        sportEquipItemDetail.setGroupSize(i, i2);
        sportEquipItemDetail.a(((int) this.b) * a(this.mContext, 20.0f));
        sportEquipItemDetail.c(((int) this.b) * a(this.mContext, 12.0f));
        sportEquipItemDetail.setItemView(eVar);
        if (eVar.a() == null) {
            sportEquipItemDetail.d(0, this.mContext.getResources().getDimensionPixelSize(R.dimen._2131362058_res_0x7f0a010a));
        }
        return sportEquipItemDetail;
    }

    private void b(SportEquipItemDrawer sportEquipItemDrawer, int i) {
        if (this.mItemDataList == null || this.mItemDataList.isEmpty()) {
            LogUtil.b("Track_IDEQ_CombineSportEquipTvItemDrawer", "fillTitleContainer mItemDataList is null");
            return;
        }
        if (sportEquipItemDrawer == null) {
            LogUtil.b("Track_IDEQ_CombineSportEquipTvItemDrawer", "fillTitleContainer container is null");
            return;
        }
        sportEquipItemDrawer.removeAllViews();
        Object systemService = this.mContext.getSystemService(Constants.NATIVE_WINDOW_SUB_DIR);
        if (!(systemService instanceof WindowManager)) {
            LogUtil.b("Track_IDEQ_CombineSportEquipTvItemDrawer", "object is invalid type");
            return;
        }
        ((WindowManager) systemService).getDefaultDisplay().getMetrics(new DisplayMetrics());
        LogUtil.a("Track_IDEQ_CombineSportEquipTvItemDrawer", "scale:", Float.valueOf(this.b), " height:", 787);
        int size = this.mItemDataList.size();
        if (size == 0) {
            return;
        }
        int i2 = (i <= 0 || ((int) Math.ceil((double) (((float) size) / ((float) i)))) != 3) ? 102 : 58;
        for (int i3 = 0; i3 < size; i3++) {
            SportEquipItemDetail d = d(108, i2, this.mItemDataList.get(i3));
            if (sportEquipItemDrawer.getChildAt(i3) != null) {
                sportEquipItemDrawer.removeViewAt(i3);
            }
            sportEquipItemDrawer.addView(d, i3);
            LogUtil.c("Track_IDEQ_CombineSportEquipTvItemDrawer", "container addView index", Integer.valueOf(i3));
        }
    }

    private SportEquipItemDetail d(int i, int i2, SportEquipItemDetail.e eVar) {
        float f = i;
        SportEquipItemDetail sportEquipItemDetail = new SportEquipItemDetail(this.mContext, this.d, (int) (this.b * a(this.mContext, f)), eVar.b());
        sportEquipItemDetail.setValueTextSize(this.b * a(this.mContext, 18.0f));
        sportEquipItemDetail.setNameTextSize(this.b * a(this.mContext, 12.0f));
        if (sportEquipItemDetail.getName().getText().length() > 10) {
            sportEquipItemDetail.setNameTextSize(sportEquipItemDetail.getName().getTextSize() * 0.7f);
        }
        if (eVar.bVM_() != null) {
            sportEquipItemDetail.getNameImage().getLayoutParams().width = (int) (this.b * a(this.mContext, 16.0f));
            sportEquipItemDetail.getNameImage().getLayoutParams().height = (int) (this.b * a(this.mContext, 16.0f));
        }
        sportEquipItemDetail.setGroupSize(((int) this.b) * a(this.mContext, f), ((int) this.b) * a(this.mContext, i2));
        sportEquipItemDetail.setItemView(eVar);
        if (eVar.a() == null) {
            sportEquipItemDetail.d(0, this.mContext.getResources().getDimensionPixelSize(R.dimen._2131362058_res_0x7f0a010a));
        }
        return sportEquipItemDetail;
    }

    private int a(Context context, float f) {
        return (int) ((f * this.e) + 0.5d);
    }
}
