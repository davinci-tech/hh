package defpackage;

import androidx.core.view.PointerIconCompat;
import com.huawei.health.knit.section.view.BarChartCommonSection;
import com.huawei.health.knit.section.view.BarChartCustomSection;
import com.huawei.health.knit.section.view.BaseSection;
import com.huawei.health.knit.section.view.CollapsibleSectionGroup;
import com.huawei.health.knit.section.view.CollapsibleSectionGroup_01;
import com.huawei.health.knit.section.view.FavoritesSection;
import com.huawei.health.knit.section.view.FunctionSetCardSection;
import com.huawei.health.knit.section.view.HealthNoDataDesSection;
import com.huawei.health.knit.section.view.HealthNoDataTopSection;
import com.huawei.health.knit.section.view.MemberPlanSection;
import com.huawei.health.knit.section.view.ObserverRecyclerViewSection;
import com.huawei.health.knit.section.view.OperaMsgCardSection;
import com.huawei.health.knit.section.view.Section16_9Card_01;
import com.huawei.health.knit.section.view.Section16_9List_02;
import com.huawei.health.knit.section.view.Section16_9Series_01;
import com.huawei.health.knit.section.view.Section16_9Series_02;
import com.huawei.health.knit.section.view.Section1_1Card_01;
import com.huawei.health.knit.section.view.Section1_1Card_02;
import com.huawei.health.knit.section.view.Section1_1List_01;
import com.huawei.health.knit.section.view.Section21_9List_01;
import com.huawei.health.knit.section.view.Section21_9List_02;
import com.huawei.health.knit.section.view.Section21_9Target_01;
import com.huawei.health.knit.section.view.Section21_9Target_02;
import com.huawei.health.knit.section.view.Section21_9Target_03;
import com.huawei.health.knit.section.view.Section3_2List_01;
import com.huawei.health.knit.section.view.Section4_5Card_01;
import com.huawei.health.knit.section.view.SectionActiveHoursBarChart;
import com.huawei.health.knit.section.view.SectionActiveRecordCalendar;
import com.huawei.health.knit.section.view.SectionActiveRecordThreeCircle;
import com.huawei.health.knit.section.view.SectionActiveRecordTips;
import com.huawei.health.knit.section.view.SectionActiveRecordTrendList;
import com.huawei.health.knit.section.view.SectionActiveRecordWeek;
import com.huawei.health.knit.section.view.SectionActiveStandCard;
import com.huawei.health.knit.section.view.SectionActiveStandDataCard;
import com.huawei.health.knit.section.view.SectionArticleCombination;
import com.huawei.health.knit.section.view.SectionBannerCard;
import com.huawei.health.knit.section.view.SectionBarChart_01;
import com.huawei.health.knit.section.view.SectionBloodOxygenBarChart;
import com.huawei.health.knit.section.view.SectionBloodOxygenBaseBarChart;
import com.huawei.health.knit.section.view.SectionConfigurationArea;
import com.huawei.health.knit.section.view.SectionDialog;
import com.huawei.health.knit.section.view.SectionDoubleButton;
import com.huawei.health.knit.section.view.SectionDynamicBp;
import com.huawei.health.knit.section.view.SectionEntryCard;
import com.huawei.health.knit.section.view.SectionEntryCardThreeItems;
import com.huawei.health.knit.section.view.SectionEnvNoiseLineChart;
import com.huawei.health.knit.section.view.SectionExpandReport;
import com.huawei.health.knit.section.view.SectionGolfClubsEntrance;
import com.huawei.health.knit.section.view.SectionGridRecord;
import com.huawei.health.knit.section.view.SectionHeartRateCombinedChart;
import com.huawei.health.knit.section.view.SectionIntroductionCard;
import com.huawei.health.knit.section.view.SectionLacticLineChart;
import com.huawei.health.knit.section.view.SectionLineChart;
import com.huawei.health.knit.section.view.SectionLineChart_01;
import com.huawei.health.knit.section.view.SectionListRecord;
import com.huawei.health.knit.section.view.SectionMemberType;
import com.huawei.health.knit.section.view.SectionMessageWindow;
import com.huawei.health.knit.section.view.SectionMultiLineChart;
import com.huawei.health.knit.section.view.SectionNewSocial;
import com.huawei.health.knit.section.view.SectionNoSlide;
import com.huawei.health.knit.section.view.SectionOperationCard;
import com.huawei.health.knit.section.view.SectionPieChart;
import com.huawei.health.knit.section.view.SectionPopularRoutes;
import com.huawei.health.knit.section.view.SectionRangeView;
import com.huawei.health.knit.section.view.SectionRecommendRecycleCards;
import com.huawei.health.knit.section.view.SectionRecordCard;
import com.huawei.health.knit.section.view.SectionRemindView;
import com.huawei.health.knit.section.view.SectionRingChart;
import com.huawei.health.knit.section.view.SectionScoring;
import com.huawei.health.knit.section.view.SectionScoringMgmt;
import com.huawei.health.knit.section.view.SectionScrollCard;
import com.huawei.health.knit.section.view.SectionShuteyeShare;
import com.huawei.health.knit.section.view.SectionSideSlip;
import com.huawei.health.knit.section.view.SectionSimpleRecord;
import com.huawei.health.knit.section.view.SectionSleepBreatheBanner;
import com.huawei.health.knit.section.view.SectionSleepInterpretations;
import com.huawei.health.knit.section.view.SectionSleepManagement;
import com.huawei.health.knit.section.view.SectionSmartWearableCard;
import com.huawei.health.knit.section.view.SectionSportList;
import com.huawei.health.knit.section.view.SectionStatistic;
import com.huawei.health.knit.section.view.SectionStepCard;
import com.huawei.health.knit.section.view.SectionSummary;
import com.huawei.health.knit.section.view.SectionTabSwitch;
import com.huawei.health.knit.section.view.SectionText;
import com.huawei.health.knit.section.view.SectionToastCard;
import com.huawei.health.knit.section.view.SectionTopReminder;
import com.huawei.health.knit.section.view.SectionWebview;
import com.huawei.health.knit.section.view.Section_Card_Text_01;
import com.huawei.health.knit.section.view.Section_SleepOrigin_Text;
import com.huawei.health.knit.section.view.Section_SleepReport_Text_01;
import com.huawei.health.knit.section.view.Section_SleepReport_Text_02;
import com.huawei.health.knit.section.view.Section_home_BusinessCard_01;
import com.huawei.health.knit.section.view.SingleButtonSection;
import com.huawei.health.knit.section.view.SingleTextSection;
import com.huawei.health.section.section.Section16_9List_01;
import com.huawei.health.section.section.Section1_1Search_01;
import com.huawei.health.section.section.Section1_1Search_02;
import com.huawei.health.section.section.Section1_1Search_03;
import com.huawei.health.section.section.Section21_9Search_01;
import com.huawei.health.section.section.SectionSimpleList;
import com.huawei.hms.framework.network.download.internal.constants.ExceptionCode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/* loaded from: classes3.dex */
public class egv {
    private static final HashMap<Integer, Class<? extends BaseSection>> b = new HashMap<Integer, Class<? extends BaseSection>>() { // from class: egv.2
        {
            put(31, Section4_5Card_01.class);
            put(33, Section1_1List_01.class);
            put(86, Section16_9List_02.class);
            put(32, Section16_9Series_01.class);
            put(34, Section1_1Card_01.class);
            put(77, Section21_9Target_01.class);
            put(1000, Section21_9Search_01.class);
            put(1001, Section21_9Search_01.class);
            put(1002, Section1_1Search_02.class);
            put(1003, Section1_1Search_01.class);
            put(1024, Section1_1Search_03.class);
            put(1038, SectionSimpleList.class);
            put(1008, Section16_9List_01.class);
            put(1009, Section3_2List_01.class);
            put(1120, SectionArticleCombination.class);
            put(80, SectionRecommendRecycleCards.class);
            put(1010, Section21_9List_01.class);
            put(1016, Section21_9List_02.class);
            put(1004, SectionMultiLineChart.class);
            put(1005, SectionPieChart.class);
            put(1006, SectionListRecord.class);
            put(1007, SectionGridRecord.class);
            put(1100, CollapsibleSectionGroup.class);
            put(1102, CollapsibleSectionGroup.class);
            put(1069, CollapsibleSectionGroup.class);
            put(Integer.valueOf(ExceptionCode.CREATE_DOWNLOAD_FILE_FAILED), CollapsibleSectionGroup.class);
            put(61, Section16_9Card_01.class);
            put(1251, Section16_9Series_02.class);
            put(73, Section21_9Target_02.class);
            put(72, Section21_9Target_03.class);
            put(1012, BarChartCustomSection.class);
            put(1013, BarChartCommonSection.class);
            put(1014, SectionEntryCard.class);
            put(1068, SectionEntryCardThreeItems.class);
            put(1017, SectionStepCard.class);
            put(1019, FunctionSetCardSection.class);
            put(Integer.valueOf(PointerIconCompat.TYPE_GRAB), OperaMsgCardSection.class);
            put(1028, SectionOperationCard.class);
            put(1021, ObserverRecyclerViewSection.class);
            put(1022, Section1_1Card_02.class);
            put(1025, SectionLacticLineChart.class);
            put(1026, Section_Card_Text_01.class);
            put(79, FavoritesSection.class);
            put(92, FavoritesSection.class);
            put(1111, SectionShuteyeShare.class);
            put(1250, Section_home_BusinessCard_01.class);
            put(1200, SectionLineChart.class);
            put(1106, CollapsibleSectionGroup_01.class);
            put(1201, SectionMessageWindow.class);
            put(1040, SectionLineChart_01.class);
            put(1041, SectionBarChart_01.class);
            put(1042, SectionScrollCard.class);
            put(1043, SectionRemindView.class);
            put(1032, SectionBloodOxygenBarChart.class);
            put(1044, SectionToastCard.class);
            put(1031, Section_SleepReport_Text_02.class);
            put(1033, SectionBannerCard.class);
            put(1118, SectionRangeView.class);
            put(1119, SectionRecordCard.class);
            put(1034, SectionBloodOxygenBaseBarChart.class);
            put(100, SectionPopularRoutes.class);
            put(1103, SectionTopReminder.class);
            put(Integer.valueOf(ExceptionCode.CHECK_FILE_HASH_FAILED), SectionNewSocial.class);
            put(Integer.valueOf(ExceptionCode.CHECK_FILE_SIZE_FAILED), SectionMemberType.class);
            put(1055, SectionSmartWearableCard.class);
            put(1035, SectionHeartRateCombinedChart.class);
            put(1036, SingleButtonSection.class);
            put(1037, SingleTextSection.class);
            put(1039, HealthNoDataTopSection.class);
            put(1067, HealthNoDataDesSection.class);
            put(1045, SectionDoubleButton.class);
            put(1046, SectionSideSlip.class);
            put(1047, SectionSimpleRecord.class);
            put(99, SectionNoSlide.class);
            put(1048, SectionText.class);
            put(1056, SectionIntroductionCard.class);
            put(1057, SectionActiveStandCard.class);
            put(1058, SectionActiveHoursBarChart.class);
            put(1059, SectionActiveRecordCalendar.class);
            put(1060, SectionActiveRecordThreeCircle.class);
            put(1061, SectionActiveRecordTips.class);
            put(1062, SectionActiveRecordTrendList.class);
            put(1070, SectionActiveRecordWeek.class);
            put(1063, Section_SleepOrigin_Text.class);
            put(1108, SectionSleepInterpretations.class);
            put(1072, SectionSleepBreatheBanner.class);
            put(1064, SectionActiveStandDataCard.class);
            put(198, MemberPlanSection.class);
            put(1066, SectionTabSwitch.class);
            put(1065, SectionSleepManagement.class);
            put(1071, SectionSportList.class);
            put(1073, SectionSummary.class);
            put(1074, SectionDynamicBp.class);
            put(1075, SectionSummary.class);
            put(1077, SectionSummary.class);
            put(1076, SectionGolfClubsEntrance.class);
        }
    };
    private static final HashMap<Integer, List<Class<? extends BaseSection>>> c = new HashMap<Integer, List<Class<? extends BaseSection>>>() { // from class: egv.5
        {
            put(1102, new ArrayList<Class<? extends BaseSection>>() { // from class: egv.5.1
                {
                    add(SectionScoring.class);
                    add(SectionDialog.class);
                    add(SectionRingChart.class);
                    add(SectionExpandReport.class);
                    add(SectionWebview.class);
                    add(SectionEnvNoiseLineChart.class);
                    add(Section_SleepReport_Text_01.class);
                    add(Section_SleepOrigin_Text.class);
                }
            });
            put(1069, new ArrayList<Class<? extends BaseSection>>() { // from class: egv.5.2
                {
                    add(SectionScoringMgmt.class);
                    add(SectionDialog.class);
                    add(SectionRingChart.class);
                    add(SectionExpandReport.class);
                    add(SectionWebview.class);
                    add(SectionEnvNoiseLineChart.class);
                    add(Section_SleepReport_Text_01.class);
                    add(Section_SleepOrigin_Text.class);
                }
            });
            put(1100, new ArrayList<Class<? extends BaseSection>>() { // from class: egv.5.4
                {
                    add(SectionScoring.class);
                    add(SectionRingChart.class);
                    add(SectionExpandReport.class);
                }
            });
            put(Integer.valueOf(ExceptionCode.CREATE_DOWNLOAD_FILE_FAILED), new ArrayList<Class<? extends BaseSection>>() { // from class: egv.5.3
                {
                    add(SectionScoring.class);
                    add(SectionExpandReport.class);
                    add(SectionRingChart.class);
                    add(SectionExpandReport.class);
                    add(SectionExpandReport.class);
                    add(SectionExpandReport.class);
                    add(SectionRingChart.class);
                }
            });
            put(1106, new ArrayList<Class<? extends BaseSection>>() { // from class: egv.5.5
                {
                    add(SectionStatistic.class);
                }
            });
        }
    };

    public static Class<? extends BaseSection> a(int i) {
        Class<? extends BaseSection> cls = b.get(Integer.valueOf(i));
        return cls == null ? SectionConfigurationArea.class : cls;
    }

    public static List<Class<? extends BaseSection>> c(int i) {
        return c.get(Integer.valueOf(i));
    }
}
