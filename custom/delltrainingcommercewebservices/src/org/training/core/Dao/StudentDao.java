package org.training.core.Dao;

import de.hybris.platform.servicelayer.internal.dao.Dao;
import org.training.model.StudentDetailsModel;

import java.util.List;

public interface StudentDao extends Dao
{
 List<StudentDetailsModel> getStudentDetailsByCode(String studentId);
}