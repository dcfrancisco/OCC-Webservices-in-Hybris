package org.training.facades.student;

import de.hybris.platform.commercefacades.Student.data.StudentData;

import java.util.List;

public interface StudentFacade
{
    public List<StudentData> getStudentDetails(String studentId);
}