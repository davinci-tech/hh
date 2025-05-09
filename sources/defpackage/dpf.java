package defpackage;

import android.animation.Animator;
import android.animation.LayoutTransition;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.CharacterStyle;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.huawei.health.R;
import com.huawei.health.marketing.api.MarketingApi;
import com.huawei.health.marketing.datatype.InputBoxTemplate;
import com.huawei.health.marketing.datatype.ResourceBriefInfo;
import com.huawei.health.marketing.datatype.ResourceResultInfo;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hmf.tasks.OnFailureListener;
import com.huawei.hmf.tasks.OnSuccessListener;
import com.huawei.hmf.tasks.Task;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.ui.commonui.listener.OnClickSectionListener;
import com.huawei.ui.commonui.searchview.HealthSearchView;
import com.huawei.ui.commonui.subheader.HealthSubHeader;
import com.huawei.ui.commonui.titlebar.CustomTitleBar;
import health.compact.a.LanguageUtil;
import health.compact.a.Services;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;
import health.compact.a.Utils;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

/* loaded from: classes3.dex */
public class dpf {
    public static boolean a(int i, int i2, int i3) {
        return i + i2 < i3;
    }

    public static void Yv_(View view, View.OnClickListener onClickListener) {
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            int childCount = viewGroup.getChildCount();
            for (int i = 0; i < childCount; i++) {
                View childAt = viewGroup.getChildAt(i);
                if ((childAt instanceof LinearLayout) || (childAt instanceof RelativeLayout)) {
                    Yv_(childAt, onClickListener);
                }
                if (childAt instanceof TextView) {
                    ((TextView) childAt).setFocusable(false);
                }
                childAt.setOnClickListener(onClickListener);
            }
        }
    }

    public static void Yu_(View view, TextView.OnEditorActionListener onEditorActionListener) {
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            int childCount = viewGroup.getChildCount();
            for (int i = 0; i < childCount; i++) {
                View childAt = viewGroup.getChildAt(i);
                if ((childAt instanceof LinearLayout) || (childAt instanceof RelativeLayout)) {
                    Yu_(childAt, onEditorActionListener);
                }
                if (childAt instanceof EditText) {
                    ((EditText) childAt).setOnEditorActionListener(onEditorActionListener);
                }
            }
        }
    }

    public static void b(HealthSearchView healthSearchView, InputBoxTemplate inputBoxTemplate) {
        if (healthSearchView == null || inputBoxTemplate == null || TextUtils.isEmpty(inputBoxTemplate.getTheme())) {
            LogUtil.h("SearchViewUtil", "setQueryHint searchView or template is null");
        } else {
            healthSearchView.setQueryHint(inputBoxTemplate.getTheme());
        }
    }

    public static InputBoxTemplate d(MarketingApi marketingApi, int i, Map<Integer, ResourceResultInfo> map) {
        if (map == null || map.isEmpty()) {
            LogUtil.b("SearchViewUtil", "marketingResponse is invalid");
            return null;
        }
        Map<Integer, ResourceResultInfo> filterMarketingRules = marketingApi.filterMarketingRules(map);
        if (filterMarketingRules == null || filterMarketingRules.get(Integer.valueOf(i)) == null) {
            LogUtil.b("SearchViewUtil", "filterResultInfoMap is invalid");
            return null;
        }
        return b(filterMarketingRules.get(Integer.valueOf(i)));
    }

    public static InputBoxTemplate b(ResourceResultInfo resourceResultInfo) {
        InputBoxTemplate inputBoxTemplate;
        if (resourceResultInfo == null) {
            return null;
        }
        List<ResourceBriefInfo> resources = resourceResultInfo.getResources();
        if (koq.b(resources)) {
            return null;
        }
        for (ResourceBriefInfo resourceBriefInfo : resources) {
            long currentTimeMillis = System.currentTimeMillis();
            if (resourceBriefInfo.getContentType() == 42 && currentTimeMillis >= resourceBriefInfo.getEffectiveTime() && currentTimeMillis <= resourceBriefInfo.getExpirationTime()) {
                String content = resourceBriefInfo.getContent().getContent();
                if (!TextUtils.isEmpty(content) && (inputBoxTemplate = (InputBoxTemplate) moj.e(content, InputBoxTemplate.class)) != null) {
                    return inputBoxTemplate;
                }
            }
        }
        return null;
    }

    public static void Yn_(final Handler handler, final int i) {
        if (handler == null) {
            LogUtil.b("SearchViewUtil", "requestInputBoxTemplate handler is null");
            return;
        }
        final MarketingApi marketingApi = (MarketingApi) Services.a("FeatureMarketing", MarketingApi.class);
        Task<Map<Integer, ResourceResultInfo>> resourceResultInfo = marketingApi.getResourceResultInfo(i);
        resourceResultInfo.addOnSuccessListener(new OnSuccessListener<Map<Integer, ResourceResultInfo>>() { // from class: dpf.2
            @Override // com.huawei.hmf.tasks.OnSuccessListener
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public void onSuccess(Map<Integer, ResourceResultInfo> map) {
                LogUtil.a("SearchViewUtil", "requestMarketResource onSuccess");
                if (map == null || map.isEmpty()) {
                    LogUtil.b("SearchViewUtil", "marketingResponse is invalid");
                    return;
                }
                Map<Integer, ResourceResultInfo> filterMarketingRules = MarketingApi.this.filterMarketingRules(map);
                if (filterMarketingRules == null || filterMarketingRules.get(Integer.valueOf(i)) == null) {
                    LogUtil.b("SearchViewUtil", "filterResultInfoMap is invalid");
                    return;
                }
                InputBoxTemplate b = dpf.b(filterMarketingRules.get(Integer.valueOf(i)));
                if (b != null) {
                    Message obtainMessage = handler.obtainMessage();
                    obtainMessage.what = 42;
                    obtainMessage.obj = b;
                    handler.sendMessage(obtainMessage);
                }
            }
        });
        c(resourceResultInfo);
    }

    public static void a(Context context, InputBoxTemplate inputBoxTemplate) {
        Intent intent = new Intent();
        intent.setClassName(context, "com.huawei.ui.homehealth.search.GlobalSearchActivity");
        intent.setFlags(131072);
        intent.putExtra("BUNDLE_KEY_INPUT_BOX", inputBoxTemplate);
        try {
            context.startActivity(intent);
        } catch (ActivityNotFoundException e) {
            LogUtil.b("SearchViewUtil", "ActivityNotFoundException", e.getMessage());
        }
    }

    public static SpannableString Ym_(String str, String str2) {
        if (TextUtils.isEmpty(str)) {
            return new SpannableString("");
        }
        SpannableString spannableString = new SpannableString(str);
        if (TextUtils.isEmpty(str2)) {
            LogUtil.h("SearchViewUtil", "plainText or keyword is empty");
            return spannableString;
        }
        HashSet hashSet = new HashSet();
        int length = str2.length();
        for (int i = 0; i < length; i++) {
            char charAt = str2.charAt(i);
            if (Character.isUpperCase(charAt)) {
                charAt = Character.toLowerCase(charAt);
            }
            hashSet.add(Character.valueOf(charAt));
        }
        ForegroundColorSpan foregroundColorSpan = new ForegroundColorSpan(BaseApplication.getContext().getResources().getColor(R.color._2131296651_res_0x7f09018b));
        int length2 = str.length();
        for (int i2 = 0; i2 < length2; i2++) {
            char charAt2 = str.charAt(i2);
            if (Character.isUpperCase(charAt2)) {
                charAt2 = Character.toLowerCase(charAt2);
            }
            if (hashSet.contains(Character.valueOf(charAt2))) {
                spannableString.setSpan(CharacterStyle.wrap(foregroundColorSpan), i2, i2 + 1, 33);
            }
        }
        return spannableString;
    }

    public static boolean c(String str, String str2) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            LogUtil.h("SearchViewUtil", "isIncludeHighLightedString plainText or keyword is empty");
            return false;
        }
        int length = str.length();
        for (int i = 0; i < length; i++) {
            if (str2.contains(String.valueOf(str.charAt(i)))) {
                return true;
            }
        }
        return false;
    }

    public static void c(final CustomTitleBar customTitleBar, final boolean z) {
        if (customTitleBar == null) {
            return;
        }
        LogUtil.a("SearchViewUtil", "hideSearchIcon");
        View rightSoftKey = z ? customTitleBar.getRightSoftKey() : customTitleBar.getRightThirdKey();
        rightSoftKey.animate().cancel();
        rightSoftKey.animate().translationX(rightSoftKey.getWidth()).alpha(0.0f).setListener(new Animator.AnimatorListener() { // from class: dpf.5
            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationCancel(Animator animator) {
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationRepeat(Animator animator) {
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationStart(Animator animator) {
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                if (z) {
                    customTitleBar.setRightSoftkeyVisibility(8);
                } else {
                    customTitleBar.setRightThirdKeyVisibility(8);
                }
            }
        });
    }

    public static void d(CustomTitleBar customTitleBar, boolean z) {
        if (customTitleBar == null) {
            return;
        }
        LogUtil.a("SearchViewUtil", "showSearchIcon");
        View rightSoftKey = z ? customTitleBar.getRightSoftKey() : customTitleBar.getRightThirdKey();
        rightSoftKey.animate().cancel();
        if (z) {
            customTitleBar.setRightSoftkeyVisibility(0);
        } else {
            customTitleBar.setRightThirdKeyVisibility(0);
        }
        rightSoftKey.animate().translationX(0.0f).alpha(1.0f).setListener(null);
    }

    public static void Yt_(ImageView imageView, int i) {
        LogUtil.a("SearchViewUtil", "setSearchIconVisibility, isVisible: ", Integer.valueOf(i));
        if (imageView == null) {
            return;
        }
        imageView.animate().cancel();
        if (i == 0) {
            imageView.setVisibility(0);
            imageView.animate().translationX(0.0f).alpha(1.0f).setListener(null);
        } else {
            imageView.setVisibility(8);
            imageView.animate().translationX(imageView.getWidth()).alpha(0.0f);
        }
    }

    public static void d(HealthSearchView healthSearchView) {
        if (healthSearchView == null || healthSearchView.getVisibility() == 8) {
            return;
        }
        LogUtil.a("SearchViewUtil", "hideSearchView");
        healthSearchView.setVisibility(8);
    }

    public static void e(HealthSearchView healthSearchView) {
        if (healthSearchView == null || healthSearchView.getVisibility() == 0) {
            return;
        }
        LogUtil.a("SearchViewUtil", "showSearchView");
        healthSearchView.setVisibility(0);
    }

    private static void c(Task<Map<Integer, ResourceResultInfo>> task) {
        task.addOnFailureListener(new OnFailureListener() { // from class: dpf.1
            @Override // com.huawei.hmf.tasks.OnFailureListener
            public void onFailure(Exception exc) {
                LogUtil.a("SearchViewUtil", "requestMarketResource onFailure");
            }
        });
    }

    public static void b(Map<String, Object> map, HealthSubHeader healthSubHeader) {
        String b = nru.b(map, "SHOWMORE", null);
        final OnClickSectionListener a2 = nru.a(map, "SHOW_MORE_CLICK_EVENT", null);
        if (b != null && a2 != null) {
            healthSubHeader.setMoreText(b);
            healthSubHeader.setMoreTextVisibility(0);
            healthSubHeader.setMoreLayoutVisibility(0);
            healthSubHeader.setMoreViewClickListener(new View.OnClickListener() { // from class: dpf.3
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    OnClickSectionListener.this.onClick("SHOW_MORE_CLICK_EVENT");
                    ViewClickInstrumentation.clickOnView(view);
                }
            });
            return;
        }
        healthSubHeader.setMoreText("");
        healthSubHeader.setMoreTextVisibility(4);
        healthSubHeader.setMoreLayoutVisibility(4);
    }

    public static void e(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        SharedPreferenceManager.e(BaseApplication.getContext(), String.valueOf(12000), "DEFAULT_SEARCH_HINT", str, new StorageParams());
    }

    public static void c(HealthSearchView healthSearchView) {
        String b = b();
        if (healthSearchView == null || TextUtils.isEmpty(b)) {
            return;
        }
        healthSearchView.setQueryHint(b);
    }

    private static String b() {
        return SharedPreferenceManager.b(BaseApplication.getContext(), String.valueOf(12000), "DEFAULT_SEARCH_HINT");
    }

    public static void Ys_(View view) {
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            if (viewGroup.getLayoutTransition() != null) {
                return;
            }
            LogUtil.a("SearchViewUtil", "setRootLayoutTransition");
            LayoutTransition layoutTransition = new LayoutTransition();
            layoutTransition.setAnimateParentHierarchy(false);
            viewGroup.setLayoutTransition(layoutTransition);
        }
    }

    public static LayoutTransition Yq_(ViewGroup viewGroup) {
        if (viewGroup == null) {
            LogUtil.a("SearchViewUtil", "setContainerLayoutTransition container is null");
            return null;
        }
        LayoutTransition layoutTransition = viewGroup.getLayoutTransition();
        if (layoutTransition != null) {
            LogUtil.a("SearchViewUtil", "containerView already has a layoutTransition");
            return layoutTransition;
        }
        LayoutTransition layoutTransition2 = new LayoutTransition();
        layoutTransition2.enableTransitionType(4);
        layoutTransition2.disableTransitionType(2);
        layoutTransition2.disableTransitionType(3);
        layoutTransition2.disableTransitionType(0);
        layoutTransition2.disableTransitionType(1);
        LogUtil.a("SearchViewUtil", "setContainerLayoutTransition");
        viewGroup.setLayoutTransition(layoutTransition2);
        return layoutTransition;
    }

    public static LayoutTransition[] Yr_(ViewGroup[] viewGroupArr) {
        if (viewGroupArr == null || viewGroupArr.length == 0) {
            return new LayoutTransition[0];
        }
        LayoutTransition[] layoutTransitionArr = new LayoutTransition[viewGroupArr.length];
        for (int i = 0; i < viewGroupArr.length; i++) {
            layoutTransitionArr[i] = Yq_(viewGroupArr[i]);
        }
        return layoutTransitionArr;
    }

    public static void Yo_(Handler handler, final ViewGroup viewGroup, final LayoutTransition layoutTransition) {
        if (handler == null || viewGroup == null) {
            LogUtil.a("SearchViewUtil", "handler or container is null, handler: ", handler, " container: ", viewGroup);
        } else {
            handler.postDelayed(new Runnable() { // from class: dpf.4
                @Override // java.lang.Runnable
                public void run() {
                    LogUtil.a("SearchViewUtil", "restore old layoutTransition: ", layoutTransition);
                    viewGroup.setLayoutTransition(layoutTransition);
                }
            }, 100L);
        }
    }

    public static void Yp_(Handler handler, ViewGroup[] viewGroupArr, LayoutTransition[] layoutTransitionArr) {
        if (viewGroupArr == null || layoutTransitionArr == null || viewGroupArr.length != layoutTransitionArr.length) {
            LogUtil.b("SearchViewUtil", "restoreOldTransitions invalid params");
            return;
        }
        for (int i = 0; i < viewGroupArr.length; i++) {
            Yo_(handler, viewGroupArr[i], layoutTransitionArr[i]);
        }
    }

    public static int b(RecyclerView recyclerView) {
        if (recyclerView == null) {
            return 0;
        }
        int childCount = recyclerView.getChildCount();
        int i = 0;
        for (int i2 = 0; i2 < childCount; i2++) {
            View childAt = recyclerView.getChildAt(i2);
            if (childAt != null) {
                i += childAt.getHeight();
            }
        }
        return i;
    }

    public static boolean e() {
        return (Utils.o() || !LanguageUtil.m(BaseApplication.getContext()) || LoginInit.getInstance(BaseApplication.getContext()).isKidAccount()) ? false : true;
    }
}
