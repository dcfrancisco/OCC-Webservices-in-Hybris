package org.training.facades.student.impl;

import de.hybris.platform.commercefacades.Student.data.StudentData;
import de.hybris.platform.converters.Converters;
import de.hybris.platform.servicelayer.dto.converter.Converter;

import java.util.List;

import org.training.core.service.StudentServices;
import org.training.facades.student.StudentFacade;
import org.training.model.StudentDetailsModel;

public class StudentFacadeImpl implements StudentFacade
{

	private StudentServices studentServices;
	private Converter<StudentDetailsModel, StudentData> studentConverter;

	@Override
	public List<StudentData> getStudentDetails(final String studentId)
	{
		final List<StudentDetailsModel> studentDetailsModels = studentServices.getStudentDetails(studentId);
		return Converters.convertAll(studentDetailsModels, getStudentConverter());
	}

	public StudentServices getStudentServices()
	{
		return studentServices;
	}

	public void setStudentServices(final StudentServices studentServices)
	{
		this.studentServices = studentServices;
	}

	public Converter<StudentDetailsModel, StudentData> getStudentConverter()
	{
		return studentConverter;
	}

	public void setStudentConverter(final Converter<StudentDetailsModel, StudentData> studentConverter)
	{
		this.studentConverter = studentConverter;
	}


}