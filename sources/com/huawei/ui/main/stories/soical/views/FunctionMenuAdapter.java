package com.huawei.ui.main.stories.soical.views;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.recyclerview.widget.RecyclerView;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.huawei.health.R;
import com.huawei.health.marketing.datatype.SingleGridContent;
import com.huawei.health.marketing.request.ColumnRequestUtils;
import com.huawei.health.marketing.request.CustomConfigValue;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import defpackage.ixx;
import defpackage.koq;
import defpackage.nrf;
import defpackage.nrh;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes8.dex */
public class FunctionMenuAdapter extends RecyclerView.Adapter<c> {

    /* renamed from: a, reason: collision with root package name */
    private List<SingleGridContent> f10501a;
    private String c;
    private Context d;
    private List<CustomConfigValue> e = new ArrayList(10);

    public FunctionMenuAdapter(Context context) {
        this.d = context;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: dTH_, reason: merged with bridge method [inline-methods] */
    public c onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new c(LayoutInflater.from(this.d).inflate(R.layout.item_function_menu, viewGroup, false));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: e, reason: merged with bridge method [inline-methods] */
    public void onBindViewHolder(c cVar, int i) {
        if (koq.b(this.f10501a)) {
            LogUtil.h("FunctionMenuAdapter", "onBindViewHolder() holder or briefInfoList or position is null.");
            return;
        }
        if (koq.b(this.f10501a, i)) {
            LogUtil.h("FunctionMenuAdapter", "onBindViewHolder() outOfBounds, position = ", Integer.valueOf(i));
        }
        SingleGridContent singleGridContent = this.f10501a.get(i);
        nrf.cIS_(cVar.c, singleGridContent.getPicture(), nrf.e, 0, 0);
        cVar.b.setText(singleGridContent.getTheme());
        if (singleGridContent.getFavoriteTime() > 0) {
            cVar.f10503a.setImageResource(R.drawable._2131429874_res_0x7f0b09f2);
        } else {
            cVar.f10503a.setImageResource(R.drawable._2131430218_res_0x7f0b0b4a);
        }
        c(cVar, singleGridContent);
    }

    private void c(c cVar, final SingleGridContent singleGridContent) {
        cVar.f10503a.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.ui.main.stories.soical.views.FunctionMenuAdapter.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (TextUtils.isEmpty(singleGridContent.getItemId())) {
                    LogUtil.c("FunctionMenuAdapter", "onBindViewHolder() onClick itemId null");
                    ViewClickInstrumentation.clickOnView(view);
                    return;
                }
                if (singleGridContent.getFavoriteTime() > 0) {
                    Iterator it = FunctionMenuAdapter.this.e.iterator();
                    while (true) {
                        if (!it.hasNext()) {
                            break;
                        }
                        CustomConfigValue customConfigValue = (CustomConfigValue) it.next();
                        if (TextUtils.equals(singleGridContent.getItemId(), customConfigValue.getId())) {
                            FunctionMenuAdapter.this.e.remove(customConfigValue);
                            break;
                        }
                    }
                    FunctionMenuAdapter functionMenuAdapter = FunctionMenuAdapter.this;
                    functionMenuAdapter.f10501a = ColumnRequestUtils.getFunctionMenuFinallyData(functionMenuAdapter.c(functionMenuAdapter.c), FunctionMenuAdapter.this.e);
                    FunctionMenuAdapter.this.notifyDataSetChanged();
                    FunctionMenuAdapter.this.b(2, singleGridContent.getTheme(), singleGridContent.getItemId());
                } else {
                    FunctionMenuAdapter functionMenuAdapter2 = FunctionMenuAdapter.this;
                    if (functionMenuAdapter2.c((List<SingleGridContent>) functionMenuAdapter2.f10501a)) {
                        nrh.b(FunctionMenuAdapter.this.d, R.string._2130847587_res_0x7f022763);
                        ViewClickInstrumentation.clickOnView(view);
                        return;
                    }
                    if (koq.b(FunctionMenuAdapter.this.e)) {
                        FunctionMenuAdapter.this.e = new ArrayList(10);
                    }
                    CustomConfigValue customConfigValue2 = new CustomConfigValue();
                    customConfigValue2.setFavoriteTime(System.currentTimeMillis());
                    customConfigValue2.setId(singleGridContent.getItemId());
                    FunctionMenuAdapter.this.e.add(customConfigValue2);
                    FunctionMenuAdapter functionMenuAdapter3 = FunctionMenuAdapter.this;
                    functionMenuAdapter3.f10501a = ColumnRequestUtils.getFunctionMenuFinallyData(functionMenuAdapter3.c(functionMenuAdapter3.c), FunctionMenuAdapter.this.e);
                    FunctionMenuAdapter.this.notifyDataSetChanged();
                    FunctionMenuAdapter.this.b(1, singleGridContent.getTheme(), singleGridContent.getItemId());
                }
                ViewClickInstrumentation.clickOnView(view);
            }
        });
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        List<SingleGridContent> list = this.f10501a;
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    static class c extends RecyclerView.ViewHolder {

        /* renamed from: a, reason: collision with root package name */
        private ImageView f10503a;
        private HealthTextView b;
        private ImageView c;

        public c(View view) {
            super(view);
            this.c = (ImageView) view.findViewById(R.id.item_all_function_menu_image);
            this.f10503a = (ImageView) view.findViewById(R.id.item_all_function_menu_favorites_image);
            this.b = (HealthTextView) view.findViewById(R.id.item_all_function_menu_name);
        }
    }

    public void b(List<SingleGridContent> list, List<CustomConfigValue> list2) {
        if (koq.b(list)) {
            LogUtil.h("FunctionMenuAdapter", "setData() singleGridContents is null.");
            return;
        }
        this.c = new Gson().toJson(list);
        this.e = list2;
        this.f10501a = ColumnRequestUtils.getFunctionMenuFinallyData(list, list2);
        notifyDataSetChanged();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(int i, String str, String str2) {
        HashMap hashMap = new HashMap(1);
        hashMap.put("event", Integer.valueOf(i));
        hashMap.put("menuName", str);
        hashMap.put("menuId", str2);
        ixx.d().d(this.d, AnalyticsValue.FUNCTION_MENU_COLLECTION_2020038.value(), hashMap, 0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean c(List<SingleGridContent> list) {
        Iterator<SingleGridContent> it = list.iterator();
        int i = 0;
        while (it.hasNext()) {
            if (it.next().getFavoriteTime() > 0) {
                i++;
            }
        }
        return i >= 5;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public List<SingleGridContent> c(String str) {
        List arrayList = new ArrayList(10);
        try {
            List list = (List) new Gson().fromJson(str, new TypeToken<List<SingleGridContent>>() { // from class: com.huawei.ui.main.stories.soical.views.FunctionMenuAdapter.4
            }.getType());
            try {
                LogUtil.h("FunctionMenuAdapter", "getOriginalData size: ", Integer.valueOf(list.size()));
                return list;
            } catch (JsonSyntaxException unused) {
                arrayList = list;
                LogUtil.h("FunctionMenuAdapter", "request Exception.");
                return arrayList;
            }
        } catch (JsonSyntaxException unused2) {
        }
    }
}
