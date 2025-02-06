package org.training.core.services;

import org.training.core.Dao.StudentDao;
import org.training.model.StudentDetailsModel;

import javax.annotation.Resource;
import java.util.List;

public class StudentServices
{
    @Resource
    private StudentDao studentDao;

    public List<StudentDetailsModel> getStudentDetails(final String studentId)
    {
        List<StudentDetailsModel> studentModels=studentDao.getStudentDetailsByCode(studentId);
        return studentModels;
    }
}