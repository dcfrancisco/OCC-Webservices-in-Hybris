package org.training.core.Dao;

import de.hybris.platform.servicelayer.internal.dao.Dao;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;
import org.training.model.TeacherModel;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;
import java.util.List;

@Component
public class TeacherDao extends Dao {

    @Resource
    private FlexibleSearchService flexibleSearchService;

    public List<TeacherModel> getTeachersBySubject(String subjectCode) {
        String query = "SELECT {t.pk} FROM {Teacher AS t JOIN TeacherSubjectRelation AS r ON {t.pk}={r.source} "
                + "JOIN Subject AS s ON {r.target}={s.pk}} WHERE {s.code}=?subjectCode";
        FlexibleSearchQuery fsQuery = new FlexibleSearchQuery(query);
        fsQuery.addQueryParameter("subjectCode", subjectCode);
        return flexibleSearchService.<TeacherModel>search(fsQuery).getResult();
    }
}
