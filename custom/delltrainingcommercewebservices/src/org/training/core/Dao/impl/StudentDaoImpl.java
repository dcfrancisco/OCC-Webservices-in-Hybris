package org.training.core.Dao.impl;

import de.hybris.platform.servicelayer.internal.dao.AbstractItemDao;
import de.hybris.platform.servicelayer.search.SearchResult;
import de.hybris.platform.servicelayer.util.ServicesUtil;
import org.training.core.Dao.StudentDao;
import org.training.model.StudentDetailsModel;
import org.apache.log4j.Logger;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class StudentDaoImpl extends AbstractItemDao implements StudentDao
{
    private static final Logger LOGGER= Logger.getLogger(StudentDaoImpl.class);
    private static final String STUDENT_QUERY="SELECT{" +StudentDetailsModel.PK+ "}FROM{"+StudentDetailsModel._TYPECODE+ "}WHERE{" +StudentDetailsModel.STUDENTID+ "}=?code";

    @Override
    public List<StudentDetailsModel> getStudentDetailsByCode(String studentId)
    {
        ServicesUtil.validateParameterNotNull(studentId,"Student ID must not be null");
        final Map<String, Object> params =new HashMap<>();
        params.put("code",studentId);
        LOGGER.info(getFlexibleSearchService().search(STUDENT_QUERY,params).getClass());
        final SearchResult<StudentDetailsModel> student=getFlexibleSearchService().search(STUDENT_QUERY,params);
        return student.getResult()==null ? Collections.emptyList(): student.getResult();
    }
}
