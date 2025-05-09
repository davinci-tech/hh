package defpackage;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.text.TextUtils;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.core.content.ContextCompat;
import com.huawei.health.R;
import com.huawei.health.h5pro.utils.GeneralUtil;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.pluginachievement.manager.model.MedalConfigInfo;
import com.huawei.pluginachievement.manager.model.MedalInfo;
import com.huawei.pluginachievement.manager.model.MedalInfoDesc;
import com.huawei.pluginachievement.manager.model.NewMedalTabDataBean;
import com.huawei.pluginachievement.manager.model.ParsedFieldTag;
import com.huawei.pluginachievement.manager.model.PersonalData;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.columnsystem.HealthColumnSystem;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import health.compact.a.LanguageUtil;
import health.compact.a.UnitUtil;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes6.dex */
public class mls {

    /* renamed from: a, reason: collision with root package name */
    protected Context f15055a;
    protected Map<Integer, String> b;
    protected Drawable c;
    protected Drawable d;
    protected Map<String, ArrayList<MedalInfoDesc>> e;
    private Pair<Integer, Integer> f = BaseActivity.getSafeRegionWidth();
    private boolean i;
    private Map<String, MedalInfo> j;

    public mls(Context context, Map<String, ArrayList<MedalInfoDesc>> map, Map<Integer, String> map2, Map<String, MedalInfo> map3) {
        if (context == null) {
            this.f15055a = BaseApplication.getContext();
        } else {
            this.f15055a = context;
        }
        this.b = map2;
        this.e = map;
        this.j = map3;
        c();
    }

    private void c() {
        this.c = ContextCompat.getDrawable(this.f15055a, R.drawable.medal_black_background_up);
        this.d = ContextCompat.getDrawable(this.f15055a, R.drawable.medal_black_background);
        this.i = Build.VERSION.SDK_INT == 26 || Build.VERSION.SDK_INT == 27;
    }

    protected View cla_(ArrayList<MedalInfoDesc> arrayList, final int i) {
        View inflate = LayoutInflater.from(this.f15055a).inflate(R.layout.medal_item, (ViewGroup) null);
        HealthTextView healthTextView = (HealthTextView) inflate.findViewById(R.id.gridview_medal_tv);
        ImageView imageView = (ImageView) inflate.findViewById(R.id.gridview_medal_iv);
        final HealthTextView healthTextView2 = (HealthTextView) inflate.findViewById(R.id.gridview_medal_jincheng);
        HealthTextView healthTextView3 = (HealthTextView) inflate.findViewById(R.id.gridview_medal_num);
        LinearLayout linearLayout = (LinearLayout) inflate.findViewById(R.id.medal_item_layout);
        if (koq.b(arrayList, i)) {
            return inflate;
        }
        final MedalInfoDesc medalInfoDesc = arrayList.get(i);
        ckY_(medalInfoDesc, healthTextView, healthTextView2, linearLayout, imageView);
        if (medalInfoDesc.acquireGainCount() > 1 && mlb.n(medalInfoDesc.acquireMedalType())) {
            healthTextView3.setText(d(medalInfoDesc.acquireGainCount()));
            healthTextView3.setVisibility(0);
        } else {
            healthTextView3.setVisibility(4);
        }
        linearLayout.setOnClickListener(new View.OnClickListener() { // from class: mls.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (mlb.b()) {
                    LogUtil.h("PLGACHIEVE_MedalExpandableBase", "showAchieveMedalDetail isMedalFastClick!");
                    ViewClickInstrumentation.clickOnView(view);
                } else {
                    view.getLocationOnScreen(new int[2]);
                    mls.this.c(healthTextView2, medalInfoDesc, i);
                    ViewClickInstrumentation.clickOnView(view);
                }
            }
        });
        linearLayout.setLayoutParams(ckX_());
        return inflate;
    }

    private String d(int i) {
        if (LanguageUtil.m(this.f15055a)) {
            return UnitUtil.e(i, 1, 0) + this.f15055a.getString(R.string._2130841409_res_0x7f020f41);
        }
        return UnitUtil.e(i, 1, 0);
    }

    private LinearLayout.LayoutParams ckX_() {
        int i = 0;
        if (nsn.ag(this.f15055a)) {
            i = (int) (r0.c() + new HealthColumnSystem(this.f15055a, 0).g() + r0.a());
        }
        return new LinearLayout.LayoutParams(((mlu.f(this.f15055a) - ((this.f15055a.getResources().getDimensionPixelSize(R.dimen._2131362009_res_0x7f0a00d9) + ((Integer) this.f.first).intValue()) * 2)) - (i * 2)) / 3, -2);
    }

    private void ckY_(MedalInfoDesc medalInfoDesc, HealthTextView healthTextView, HealthTextView healthTextView2, LinearLayout linearLayout, ImageView imageView) {
        String c;
        boolean z = true;
        if (medalInfoDesc.acquireGainCount() >= 1) {
            c = mlb.c(medalInfoDesc.acquireMedalId(), ParsedFieldTag.LIGHT_LIST_STYLE);
        } else {
            c = mlb.c(medalInfoDesc.acquireMedalId(), ParsedFieldTag.GRAY_LIST_STYLE);
            z = false;
        }
        String b = mlb.b(medalInfoDesc.acquireMedalId());
        String str = mlb.c(b) + File.separator + c + ".png";
        healthTextView.setText(medalInfoDesc.acquireText());
        if (medalInfoDesc.acquireIsNewConfig() > 0) {
            healthTextView2.setVisibility(0);
        } else {
            healthTextView2.setVisibility(4);
        }
        healthTextView2.setText(this.f15055a.getResources().getString(R.string._2130840761_res_0x7f020cb9));
        Bitmap clc_ = clc_(str, b);
        if (clc_ != null) {
            LogUtil.a("PLGACHIEVE_MedalExpandableBase", "bitmap not null");
            linearLayout.setVisibility(0);
            if (this.i && !z) {
                imageView.setImageDrawable(nrf.cJH_(nrf.cHq_(clc_), this.f15055a.getResources().getColor(R.color._2131296803_res_0x7f090223)));
                return;
            } else {
                imageView.setImageBitmap(clc_);
                return;
            }
        }
        ckZ_(medalInfoDesc, z, linearLayout, imageView);
    }

    public Bitmap clc_(String str, String str2) {
        Bitmap bitmap = null;
        if (!GeneralUtil.isSafePath(str)) {
            LogUtil.h("PLGACHIEVE_MedalExpandableBase", "loadImage: untrusted -> " + str);
            return null;
        }
        if (new File(str).exists()) {
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inPreferredConfig = Bitmap.Config.RGB_565;
            try {
                bitmap = BitmapFactory.decodeFile(str, options);
            } catch (OutOfMemoryError unused) {
                LogUtil.b("PLGACHIEVE_MedalExpandableBase", "loadImage:OutOfMemoryError");
            }
        }
        if (bitmap == null) {
            LogUtil.h("PLGACHIEVE_MedalExpandableBase", "showMedalBitmap loadImage is null! medal ", str2);
        }
        return bitmap;
    }

    private void ckZ_(MedalInfoDesc medalInfoDesc, boolean z, LinearLayout linearLayout, ImageView imageView) {
        Bitmap cko_ = mlb.cko_(medalInfoDesc.acquireMedalId(), z, false);
        if (cko_ != null) {
            LogUtil.a("PLGACHIEVE_MedalExpandableBase", "bmp not null");
            linearLayout.setVisibility(0);
            imageView.setImageBitmap(cko_);
            return;
        }
        String acquireMedalTypeLevel = medalInfoDesc.acquireMedalTypeLevel();
        LogUtil.a("PLGACHIEVE_MedalExpandableBase", "typeLevel is ", acquireMedalTypeLevel);
        MedalInfo medalInfo = this.j.get(acquireMedalTypeLevel);
        if (medalInfo != null) {
            if (medalInfoDesc.acquireGainCount() >= 1) {
                LogUtil.a("PLGACHIEVE_MedalExpandableBase", "medalInfo getEnableResId is ", Integer.valueOf(medalInfo.getEnableResId()));
                imageView.setImageResource(medalInfo.getEnableResId());
                return;
            } else {
                LogUtil.a("PLGACHIEVE_MedalExpandableBase", "medalInfo getDisableResId is ", Integer.valueOf(medalInfo.getDisableResId()));
                imageView.setImageResource(medalInfo.getDisableResId());
                return;
            }
        }
        LogUtil.a("PLGACHIEVE_MedalExpandableBase", "medalInfo medalMessage is ", medalInfoDesc.acquireMedalId());
        MedalConfigInfo medalConfigInfo = new MedalConfigInfo();
        medalConfigInfo.saveMedalID(medalInfoDesc.acquireMedalId());
        medalConfigInfo.saveIsNewConfig(0);
        medalConfigInfo.saveEventStatus(1);
        meh.c(this.f15055a).e((mcz) medalConfigInfo);
        imageView.setVisibility(4);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(HealthTextView healthTextView, MedalInfoDesc medalInfoDesc, int i) {
        Intent intent = new Intent();
        intent.setClassName(this.f15055a, PersonalData.CLASS_NAME_PERSONAL_MEDAL_DETAIL);
        String acquireMedalId = medalInfoDesc.acquireMedalId();
        MedalConfigInfo medalConfigInfo = new MedalConfigInfo();
        medalConfigInfo.saveMedalID(acquireMedalId);
        medalConfigInfo.saveIsNewConfig(0);
        intent.putExtra("medal_res_id", String.valueOf(acquireMedalId));
        if (medalInfoDesc.acquireGainCount() > 0) {
            intent.putExtra("medal_des_id", String.valueOf(medalInfoDesc.acquireLightDescription()));
        } else {
            intent.putExtra("medal_des_id", String.valueOf(medalInfoDesc.acquireGrayDescription()));
        }
        intent.putExtra("medal_content_id", String.valueOf(medalInfoDesc.acquireText()));
        intent.putExtra("medal_type_level", String.valueOf(medalInfoDesc.acquireMedalTypeLevel()));
        intent.putExtra("medal_gain_time", String.valueOf(medalInfoDesc.acquireGainTime()));
        intent.putExtra("medal_gain_count", medalInfoDesc.acquireGainCount());
        intent.putExtra("click_x", mlu.f(this.f15055a) / 2);
        intent.putExtra("click_y", mlu.j(this.f15055a) / 2);
        intent.putExtra("medal_type", medalInfoDesc.acquireMedalType());
        if (medalInfoDesc.acquireGainCount() >= 1) {
            intent.putExtra("medal_obtain_id", "true");
        } else {
            intent.putExtra("medal_obtain_id", "false");
        }
        intent.putExtra("promotion_name", String.valueOf(medalInfoDesc.acquirePromotionName()));
        intent.putExtra("promotion_url", String.valueOf(medalInfoDesc.acquirePromotionURL()));
        this.f15055a.startActivity(intent);
        c(medalInfoDesc, this.b.get(Integer.valueOf(i)));
        if (medalInfoDesc.acquireIsNewConfig() > 0) {
            healthTextView.setText("");
            healthTextView.setVisibility(4);
            medalInfoDesc.saveIsNewConfig(0);
            meh.c(this.f15055a).e((mcz) medalConfigInfo);
            c(medalInfoDesc.acquireMedalId());
        }
    }

    private void c(String str) {
        Map<String, ArrayList<MedalInfoDesc>> secondTabRelationship;
        LogUtil.a("PLGACHIEVE_MedalExpandableBase", "changeOneData enter");
        NewMedalTabDataBean b = mjp.b();
        if (TextUtils.isEmpty(str) || b == null || (secondTabRelationship = b.getSecondTabRelationship()) == null) {
            return;
        }
        LogUtil.a("PLGACHIEVE_MedalExpandableBase", "changeOneData medalId =", str);
        for (Map.Entry<String, ArrayList<MedalInfoDesc>> entry : secondTabRelationship.entrySet()) {
            String key = entry.getKey();
            ArrayList<MedalInfoDesc> value = entry.getValue();
            int i = 0;
            while (true) {
                if (i < value.size()) {
                    MedalInfoDesc medalInfoDesc = value.get(i);
                    if (str.equals(medalInfoDesc.acquireMedalId()) && medalInfoDesc.acquireIsNewConfig() > 0) {
                        LogUtil.a("PLGACHIEVE_MedalExpandableBase", "changeOneData secondTab =", key, " medalId=", str);
                        medalInfoDesc.saveIsNewConfig(0);
                        value.set(i, medalInfoDesc);
                        secondTabRelationship.put(key, value);
                        break;
                    }
                    i++;
                }
            }
        }
        b.setSecondTabRelationship(secondTabRelationship);
        mjp.e(b);
    }

    protected View clb_() {
        View inflate = LayoutInflater.from(this.f15055a).inflate(R.layout.medal_item, (ViewGroup) null);
        inflate.setLayoutParams(new GridLayout.LayoutParams(GridLayout.spec(Integer.MIN_VALUE, 1.0f), GridLayout.spec(Integer.MIN_VALUE, 1.0f)));
        inflate.setVisibility(4);
        return inflate;
    }

    private void c(MedalInfoDesc medalInfoDesc, String str) {
        HashMap hashMap = new HashMap(8);
        hashMap.put("cilck", 1);
        hashMap.put("name", medalInfoDesc.acquireText());
        hashMap.put("type", medalInfoDesc.acquireMedalType());
        hashMap.put("className", str);
        hashMap.put("label", Integer.valueOf(medalInfoDesc.acquireMedalLabel()));
        hashMap.put("from", "1");
        ixx.d().d(BaseApplication.getContext(), AnalyticsValue.SUCCESSES_REPORT_1100010.value(), hashMap, 0);
    }

    static class d {
        GridLayout b;

        d() {
        }
    }
}
