package kz.gaziza.emdikmed.model.dmp.configuration;

import jdk.nashorn.internal.runtime.regexp.joni.exception.InternalException;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
public class DMPCategoryFilter {
    private String name;
    private String code;

    public static List<DMPCategoryFilter> getFilter() throws InternalException {
        List<DMPCategoryFilter> filter = new ArrayList<>();

        DMPCategoryFilter filter1 = new DMPCategoryFilter();
        filter1.code = "diseases";
        filter1.name = "Заболевания";

        DMPCategoryFilter filter2 = new DMPCategoryFilter();
        filter2.code = "laboratory";
        filter2.name = "Лаборатория";

        DMPCategoryFilter filter3 = new DMPCategoryFilter();
        filter3.code = "diagnostics";
        filter3.name = "Диагностика";

        DMPCategoryFilter filter4 = new DMPCategoryFilter();
        filter4.code = "medication";
        filter4.name = "Лекарства";

        DMPCategoryFilter filter5 = new DMPCategoryFilter();
        filter5.code = "protocols";
        filter5.name = "Протокола";

        DMPCategoryFilter filter6 = new DMPCategoryFilter();
        filter6.code = "proceduresAndInterventions";
        filter6.name = "Процедуры и вмешательства";

        DMPCategoryFilter filter7 = new DMPCategoryFilter();
        filter7.code = "services";
        filter7.name = "Услуги";

        filter.add(filter1);
        filter.add(filter2);
        filter.add(filter3);
        filter.add(filter4);
        filter.add(filter5);
        filter.add(filter6);
        filter.add(filter7);

        return filter;
    }
}
