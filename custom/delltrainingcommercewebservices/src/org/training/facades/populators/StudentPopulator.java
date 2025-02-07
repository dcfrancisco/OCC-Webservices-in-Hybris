package org.training.facades.populators;

import de.hybris.platform.commercefacades.Student.data.StudentData;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import org.training.model.StudentDetailsModel;

public class StudentPopulator implements Populator<StudentDetailsModel, StudentData>
{
    @Override
    public void populate(final StudentDetailsModel source, final StudentData target)
            throws ConversionException
    {
        target.setStudentId(source.getStudentId());
        target.setStudentPlace(source.getStudentPlace());
        target.setStudentName(source.getStudentName());
        target.setStudentGuardian(source.getStudentGuardian());
    }

}
