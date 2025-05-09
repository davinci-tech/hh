package defpackage;

import android.content.Context;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.TextAppearanceSpan;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.common.utils.CollectionUtils;
import com.huawei.health.R;
import com.huawei.health.health.utils.functionsetcard.ICardChangedCallback;
import com.huawei.health.health.utils.functionsetcard.manager.constructor.CardConstructor;
import com.huawei.health.health.utils.functionsetcard.reader.FunctionSetSubCardData;
import com.huawei.hwcommonmodel.utils.DateFormatUtil;
import com.huawei.hwlogsmodel.LogUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* loaded from: classes6.dex */
public class owm {
    public static String b(long j) {
        if (jdl.g(j, System.currentTimeMillis())) {
            return DateFormatUtil.d(j, DateFormatUtil.DateFormatType.DATE_FORMAT_MD);
        }
        return DateFormatUtil.d(j, DateFormatUtil.DateFormatType.DATE_FORMAT_YYYYMD);
    }

    public static CharSequence b(String str, String str2) {
        int i;
        int i2;
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("FunctionSetCardUtil", "sourceStr is empty");
            return str2;
        }
        Context e = BaseApplication.e();
        if (nsn.p()) {
            i = R.style.function_card_unit_large_font_Style;
            i2 = R.style.function_card_data_large_font_Style;
        } else {
            i = R.style.function_card_unit_normal_Style;
            i2 = R.style.function_card_data_normal_Style;
        }
        SpannableString spannableString = new SpannableString(str);
        nsi.cKH_(spannableString, str, new TextAppearanceSpan(e, i));
        if (TextUtils.isEmpty(str2)) {
            LogUtil.h("FunctionSetCardUtil", "dataStr is empty");
            return spannableString;
        }
        nsi.cKH_(spannableString, str2, new TextAppearanceSpan(e, i2));
        nsi.cKF_(spannableString, str2);
        return spannableString;
    }

    public static CharSequence e(String str, String str2) {
        int i;
        int i2;
        if (TextUtils.isEmpty(str2)) {
            LogUtil.h("FunctionSetCardUtil", "origin is empty");
            return "";
        }
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("FunctionSetCardUtil", "patternKeys is empty");
            return str2;
        }
        if (nsn.p()) {
            i = R.style.function_card_unit_large_font_Style;
            i2 = R.style.function_card_data_large_font_Style;
        } else {
            i = R.style.function_card_unit_normal_Style;
            i2 = R.style.function_card_data_normal_Style;
        }
        Context e = BaseApplication.e();
        SpannableString spannableString = new SpannableString(str2);
        nsi.cKH_(spannableString, str2, new TextAppearanceSpan(e, i));
        Matcher matcher = Pattern.compile(str, 2).matcher(spannableString);
        while (matcher.find()) {
            int start = matcher.start();
            int end = matcher.end();
            nsi.cKG_(spannableString, start, end, new TextAppearanceSpan(e, i2));
            nsi.cKE_(spannableString, start, end);
        }
        return spannableString;
    }

    public static List<FunctionSetSubCardData> c(Context context, List<CardConstructor> list, ICardChangedCallback iCardChangedCallback) {
        if (context == null) {
            LogUtil.h("FunctionSetCardUtil", "context is null");
            return new ArrayList();
        }
        if (CollectionUtils.d(list)) {
            LogUtil.h("FunctionSetCardUtil", "cardConstructors is empty");
            return new ArrayList();
        }
        LogUtil.h("FunctionSetCardUtil", "initCardReaders cardConstructors size = ", Integer.valueOf(list.size()));
        ArrayList arrayList = new ArrayList(list.size());
        for (CardConstructor cardConstructor : list) {
            if (cardConstructor != null) {
                FunctionSetSubCardData createCardReader = cardConstructor.createCardReader(context);
                createCardReader.setNotifyCardChangedCallback(iCardChangedCallback);
                createCardReader.setCardConstructor(cardConstructor);
                arrayList.add(createCardReader);
            }
        }
        LogUtil.a("FunctionSetCardUtil", "initCardReader end");
        return arrayList;
    }

    public static List<FunctionSetSubCardData> a(Context context, List<FunctionSetSubCardData> list, List<CardConstructor> list2, ICardChangedCallback iCardChangedCallback) {
        if (context == null) {
            LogUtil.h("FunctionSetCardUtil", "context is null");
            return new ArrayList();
        }
        if (CollectionUtils.d(list2)) {
            LogUtil.h("FunctionSetCardUtil", "cardConstructors is empty");
            return new ArrayList();
        }
        LogUtil.h("FunctionSetCardUtil", "updateCardReaders cardConstructors size = ", Integer.valueOf(list2.size()));
        if (CollectionUtils.d(list)) {
            return c(context, list2, iCardChangedCallback);
        }
        HashMap hashMap = new HashMap(list.size());
        for (FunctionSetSubCardData functionSetSubCardData : list) {
            if (functionSetSubCardData != null) {
                hashMap.put(functionSetSubCardData.getCardId(), functionSetSubCardData);
            }
        }
        ArrayList arrayList = new ArrayList(list2.size());
        ArrayList arrayList2 = new ArrayList(10);
        for (CardConstructor cardConstructor : list2) {
            if (cardConstructor != null) {
                FunctionSetSubCardData functionSetSubCardData2 = (FunctionSetSubCardData) hashMap.get(cardConstructor.getCardId());
                if (functionSetSubCardData2 == null) {
                    functionSetSubCardData2 = cardConstructor.createCardReader(context);
                    functionSetSubCardData2.setNotifyCardChangedCallback(iCardChangedCallback);
                    functionSetSubCardData2.setCardConstructor(cardConstructor);
                    owi.a(functionSetSubCardData2, 2, oia.c().h());
                } else {
                    arrayList2.add(functionSetSubCardData2);
                }
                arrayList.add(functionSetSubCardData2);
            }
        }
        list.removeAll(arrayList2);
        for (FunctionSetSubCardData functionSetSubCardData3 : list) {
            if (functionSetSubCardData3 != null) {
                owi.a(functionSetSubCardData3, 3, oia.c().h());
                functionSetSubCardData3.onDestroy();
            }
        }
        LogUtil.a("FunctionSetCardUtil", "updateCardReaders end");
        return arrayList;
    }
}
