package com.huawei.health.section.section;

import android.content.Context;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import com.huawei.health.R;
import com.huawei.health.marketing.request.GlobalSearchContent;
import com.huawei.health.marketrouter.api.MarketRouterApi;
import com.huawei.health.section.section.SimpleListAdapter;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import defpackage.eie;
import health.compact.a.GRSManager;
import health.compact.a.LanguageUtil;
import health.compact.a.Services;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes3.dex */
public class SimpleListAdapter extends RecyclerView.Adapter<ItemHolder> {
    private Context b;
    private List<GlobalSearchContent> d = new ArrayList();
    private static final String e = GRSManager.a(BaseApplication.getContext()).getUrl("knowledgeUrl");

    /* renamed from: a, reason: collision with root package name */
    private static final String f2982a = GRSManager.a(BaseApplication.getContext()).getUrl("knowledgeContextUrl");

    public SimpleListAdapter(Context context) {
        this.b = context;
    }

    public void b(List<GlobalSearchContent> list) {
        this.d.clear();
        if (list != null) {
            this.d.addAll(list);
        }
        notifyDataSetChanged();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: avT_, reason: merged with bridge method [inline-methods] */
    public ItemHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ItemHolder(LayoutInflater.from(this.b).inflate(R.layout.section_simple_list_item, viewGroup, false));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public void onBindViewHolder(ItemHolder itemHolder, int i) {
        itemHolder.a(this.d, i);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.d.size();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static final class ItemHolder extends RecyclerView.ViewHolder {

        /* renamed from: a, reason: collision with root package name */
        private final HealthTextView f2983a;
        private final View e;

        ItemHolder(View view) {
            super(view);
            this.f2983a = (HealthTextView) view.findViewById(R.id.simple_title);
            this.e = view.findViewById(R.id.item_divider);
        }

        void a(List<GlobalSearchContent> list, int i) {
            GlobalSearchContent globalSearchContent = list.get(i);
            int size = list.size();
            String title = globalSearchContent.getTitle();
            SpannableString avU_ = avU_(title);
            this.f2983a.setText(avU_);
            if (i == size - 1) {
                this.e.setVisibility(8);
            }
            String b = b(globalSearchContent, avU_.toString());
            if (TextUtils.isEmpty(b)) {
                LogUtil.b("SimpleListAdapter", "set link value for title ", title, ", failed, cause linkValue is empty!");
            } else {
                this.itemView.setOnClickListener(new AnonymousClass2(b, title));
            }
        }

        /* renamed from: com.huawei.health.section.section.SimpleListAdapter$ItemHolder$2, reason: invalid class name */
        public class AnonymousClass2 implements View.OnClickListener {
            final /* synthetic */ String c;
            final /* synthetic */ String e;

            AnonymousClass2(String str, String str2) {
                this.e = str;
                this.c = str2;
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (!eie.b(this.e)) {
                    ItemHolder.this.a(this.e);
                    ViewClickInstrumentation.clickOnView(view);
                    return;
                }
                LoginInit loginInit = LoginInit.getInstance(BaseApplication.getContext());
                final String str = this.e;
                final String str2 = this.c;
                loginInit.browsingToLogin(new IBaseResponseCallback() { // from class: fbw
                    @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                    /* renamed from: onResponse */
                    public final void d(int i, Object obj) {
                        SimpleListAdapter.ItemHolder.AnonymousClass2.this.c(str, str2, i, obj);
                    }
                }, "");
                ViewClickInstrumentation.clickOnView(view);
            }

            public /* synthetic */ void c(String str, String str2, int i, Object obj) {
                if (i == 0) {
                    ItemHolder.this.a(str);
                } else {
                    LogUtil.h("SimpleListAdapter", "click simple item: ", str2, " go to detail failed, errorCode = ", Integer.valueOf(i));
                }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void a(String str) {
            MarketRouterApi marketRouterApi = (MarketRouterApi) Services.a("FeatureMarketing", MarketRouterApi.class);
            if (marketRouterApi != null) {
                marketRouterApi.router(str);
            }
        }

        SpannableString avU_(String str) {
            if (str == null) {
                return null;
            }
            int indexOf = str.indexOf("<em>");
            String replace = str.replace("<em>", "");
            int indexOf2 = replace.indexOf("</em>");
            String replace2 = replace.replace("</em>", "");
            SpannableString spannableString = new SpannableString(replace2);
            if (indexOf >= 0 && indexOf < replace2.length() && indexOf2 >= 0 && indexOf2 < replace2.length()) {
                spannableString.setSpan(new ForegroundColorSpan(ContextCompat.getColor(this.itemView.getContext(), R.color._2131296651_res_0x7f09018b)), indexOf, indexOf2, 33);
            }
            return spannableString;
        }

        private static String b(GlobalSearchContent globalSearchContent, String str) {
            if (globalSearchContent == null) {
                return "";
            }
            if (globalSearchContent.getType() != 207) {
                return globalSearchContent.getDeepLink();
            }
            if (globalSearchContent.getId() == null) {
                return "";
            }
            Context context = BaseApplication.getContext();
            String language = LanguageUtil.h(context) ? "zh-CN" : context.getResources().getConfiguration().locale.getLanguage();
            StringBuilder sb = new StringBuilder();
            String str2 = SimpleListAdapter.f2982a + "?knowId=" + globalSearchContent.getId();
            sb.append(SimpleListAdapter.e);
            sb.append("?lang=");
            sb.append(language);
            sb.append("&prodId=health_help_all&title=");
            sb.append(str);
            sb.append("&url=");
            sb.append(str2);
            return sb.toString();
        }
    }
}
