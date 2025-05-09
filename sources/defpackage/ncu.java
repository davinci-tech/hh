package defpackage;

import android.R;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.os.Build;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import com.huawei.openalliance.ad.db.bean.TemplateStyleRecord;
import com.huawei.skinner.attrentry.SkinAttr;
import com.huawei.skinner.facade.template.ILogger;
import com.huawei.skinner.internal.ISkinnableViewManager;
import com.huawei.watchface.videoedit.gles.Constant;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes6.dex */
public class ncu implements LayoutInflater.Factory2 {

    /* renamed from: a, reason: collision with root package name */
    private static final String f15258a = "SkinnableViewFactory2";
    private static boolean e;
    private static boolean f;
    private ISkinnableViewManager i;
    private static final String[] b = {"android.widget.", "android.app.", "android.view.", "android.webkit."};
    private static final String[] d = {"color", "drawable", TemplateStyleRecord.STYLE};
    private static final String[] c = {"id", "layout_height", "layout_width", Constant.TEXT, "textSize"};
    private static Field j = ndd.a(LayoutInflater.class, "mConstructorArgs");

    static {
        f = Build.VERSION.SDK_INT < 29;
        e = false;
    }

    public ncu(ISkinnableViewManager iSkinnableViewManager) {
        this.i = iSkinnableViewManager;
    }

    @Override // android.view.LayoutInflater.Factory2
    public View onCreateView(View view, String str, Context context, AttributeSet attributeSet) {
        return onCreateView(str, context, attributeSet);
    }

    @Override // android.view.LayoutInflater.Factory
    public View onCreateView(String str, Context context, AttributeSet attributeSet) {
        View csX_;
        if (!attributeSet.getAttributeBooleanValue("http://schemas.android.com/apk/res-auto", "hwSkinEnable", false) || (csX_ = csX_(context, str, attributeSet)) == null) {
            return null;
        }
        csZ_(context, attributeSet, csX_);
        return csX_;
    }

    /* JADX WARN: Removed duplicated region for block: B:59:0x011d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private android.view.View csX_(android.content.Context r10, java.lang.String r11, android.util.AttributeSet r12) {
        /*
            Method dump skipped, instructions count: 288
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.ncu.csX_(android.content.Context, java.lang.String, android.util.AttributeSet):android.view.View");
    }

    private void csW_(Context context, String str, AttributeSet attributeSet, View view) {
        int resourceId;
        if (view == null || context == null || attributeSet == null) {
            return;
        }
        TypedArray typedArray = null;
        try {
            try {
                typedArray = context.obtainStyledAttributes(attributeSet, new int[]{R.attr.id});
                if (typedArray != null && (resourceId = typedArray.getResourceId(0, -1)) != -1 && view.getId() != resourceId) {
                    ncs.d.error(f15258a, "checkViewId Error name = " + str + ", view.id = " + view.getId() + ", idInAttrs = " + resourceId);
                    view.setId(resourceId);
                }
                if (typedArray == null) {
                    return;
                }
            } catch (Exception e2) {
                ILogger iLogger = ncs.d;
                String str2 = f15258a;
                iLogger.error(str2, "checkViewId() Error", e2);
                if (ncs.d.isShowLog()) {
                    ncs.d.debug(str2, "checkViewId() catch error, name:" + str);
                }
                if (typedArray == null) {
                    return;
                }
            }
            typedArray.recycle();
        } catch (Throwable th) {
            if (typedArray != null) {
                typedArray.recycle();
            }
            throw th;
        }
    }

    private Object[] csY_(LayoutInflater layoutInflater) {
        if (j == null) {
            Field a2 = ndd.a(LayoutInflater.class, "mConstructorArgs");
            j = a2;
            if (a2 == null) {
                ncs.d.error(f15258a, "getConstructorArgsValue() Error1: no field mConstructorArgs in LayoutInflater class founded!");
                return null;
            }
        }
        Object d2 = ndd.d(j, layoutInflater);
        if (d2 instanceof Object[]) {
            return (Object[]) d2;
        }
        ncs.d.error(f15258a, "getConstructorArgsValue() Error2: Value of field mConstructorArgs in LayoutInflater is null or type is not we need!");
        return null;
    }

    private void csZ_(Context context, AttributeSet attributeSet, View view) {
        int parseInt;
        String resourceEntryName;
        String resourceTypeName;
        SkinAttr a2;
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < attributeSet.getAttributeCount(); i++) {
            String attributeName = attributeSet.getAttributeName(i);
            String attributeValue = attributeSet.getAttributeValue(i);
            if ("hwThemeServiceEnable".equals(attributeName) && "true".equals(attributeValue)) {
                view.setTag(com.huawei.health.R.id.hw_theme_service_tag, "hwThemeServiceEnable");
            }
            if (attributeValue.startsWith("@") && c(attributeName)) {
                try {
                    if (attributeValue.contains("/")) {
                        int indexOf = attributeValue.indexOf("/");
                        resourceEntryName = attributeValue.substring(indexOf + 1);
                        parseInt = context.getResources().getIdentifier(resourceEntryName, attributeValue.substring(1, indexOf), context.getPackageName());
                        resourceTypeName = context.getResources().getResourceTypeName(parseInt);
                    } else {
                        parseInt = Integer.parseInt(attributeValue.substring(1));
                        resourceEntryName = context.getResources().getResourceEntryName(parseInt);
                        resourceTypeName = context.getResources().getResourceTypeName(parseInt);
                    }
                    int i2 = 0;
                    while (true) {
                        String[] strArr = d;
                        if (i2 >= strArr.length) {
                            break;
                        }
                        if (!strArr[i2].equals(resourceTypeName)) {
                            i2++;
                        } else if (ncl.d(attributeName, view.getClass()) && (a2 = ncl.a(attributeName, parseInt, resourceEntryName, resourceTypeName, view.getClass())) != null) {
                            arrayList.add(a2);
                        }
                    }
                } catch (Resources.NotFoundException | NumberFormatException e2) {
                    ILogger iLogger = ncs.d;
                    String str = f15258a;
                    iLogger.error(str, "parseSkinableAttr() Error", e2);
                    if (ncs.d.isShowLog()) {
                        ncs.d.debug(str, "parseSkinableAttr() catch error , view:" + view.getClass().getName() + ";attrName:" + attributeName + ";attrValue:" + attributeValue + ";entryName:;typeName:");
                    }
                }
            }
        }
        view.setTag(com.huawei.health.R.id.hw_skinner_tag, "hwSkinEnable");
        if (ndb.a(arrayList)) {
            return;
        }
        nci nciVar = new nci();
        nciVar.csT_(view);
        nciVar.d().addAll(arrayList);
        nci addSkinnableView = this.i.addSkinnableView(nciVar);
        if (ncs.c(context).a()) {
            addSkinnableView.e(true);
            if (ncs.b().c() && ncs.b().e() && view.getTag(com.huawei.health.R.id.hw_theme_service_tag) != null) {
                addSkinnableView.a();
            }
        }
    }

    private boolean c(String str) {
        int i = 0;
        while (true) {
            String[] strArr = c;
            if (i >= strArr.length) {
                return (str.startsWith("layout_margin") || str.startsWith("padding")) ? false : true;
            }
            if (strArr[i].equals(str)) {
                return false;
            }
            i++;
        }
    }

    public void ctb_(Context context, View view, List<ncg> list) {
        if (view == null) {
            return;
        }
        ArrayList arrayList = new ArrayList();
        for (ncg ncgVar : list) {
            if (!ncl.d(ncgVar.e, view.getClass())) {
                arrayList.add(ncgVar);
            }
        }
        if (arrayList.size() > 0) {
            list.removeAll(arrayList);
            if (list.size() == 0) {
                return;
            }
        }
        ArrayList arrayList2 = new ArrayList();
        for (ncg ncgVar2 : list) {
            int i = ncgVar2.b;
            SkinAttr a2 = ncl.a(ncgVar2.e, i, context.getResources().getResourceEntryName(i), context.getResources().getResourceTypeName(i), view.getClass());
            if (a2 != null) {
                arrayList2.add(a2);
            }
        }
        if (ndb.a(arrayList2)) {
            return;
        }
        nci nciVar = new nci();
        nciVar.csT_(view);
        nciVar.d().addAll(arrayList2);
        nci addSkinnableView = this.i.addSkinnableView(nciVar);
        addSkinnableView.e(true);
        if (ncs.b().a() && ncs.b().c() && ncs.b().e() && view.getTag(com.huawei.health.R.id.hw_theme_service_tag) != null) {
            addSkinnableView.a();
        }
    }

    public void cta_(Context context, View view, String str, int i) {
        SkinAttr a2;
        if (view == null || !ncl.d(str, view.getClass()) || (a2 = ncl.a(str, i, context.getResources().getResourceEntryName(i), context.getResources().getResourceTypeName(i), view.getClass())) == null) {
            return;
        }
        nci nciVar = new nci();
        nciVar.csT_(view);
        nciVar.d().add(a2);
        nci addSkinnableView = this.i.addSkinnableView(nciVar);
        addSkinnableView.e(true);
        if (ncs.b().a() && ncs.b().c() && ncs.b().e() && view.getTag(com.huawei.health.R.id.hw_theme_service_tag) != null) {
            addSkinnableView.a();
        }
    }
}
