package org.training.v2.controller;
import de.hybris.platform.commercewebservicescommons.dto.Student.StudentDataListWSDTO;
import de.hybris.platform.webservicescommons.swagger.ApiBaseSiteIdParam;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.Authorization;
import org.anchor.queues.data.StudentDataList;
import org.training.facades.student.StudentFacade;
import org.apache.log4j.Logger;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;

@Controller
@RequestMapping(value="/{baseSiteId}")
@Api(tags="Student")
public class StudentController extends BaseCommerceController
{
private static final Logger LOGGER= Logger.getLogger(StudentController.class);
@Resource(name="studentFacade")
private StudentFacade studentFacade;

@Secured("ROLE_TRUSTED_CLIENT")

    @RequestMapping(value="/{studentId}", method= RequestMethod.GET)
    @ResponseBody
    @ApiOperation(nickname="getStudentDetails", value="Get a Specific Student Details",
                        notes="Return a specific Student based on studentId",authorizations={@Authorization
(value="oauth2_client_credentials")})

    @ApiBaseSiteIdParam
    public StudentDataListWSDTO getStudentDetails(@ApiParam(value="studentId", required=true)

    @PathVariable
     final String studentId,@ApiParam(value="Response configuaration. This is the list of filelds that should be returned in the response body",allowableValues="BASIC,DEFAULT,FULL")
       @RequestParam(defaultValue=DEFAULT_FIELD_SET) final String fields)
    {
        LOGGER.info("Student is "+studentId);
        final StudentDataList studentDataList=new StudentDataList();
        studentDataList.setStudent(studentFacade.getStudentDetails(studentId));
        return getDataMapper().map(studentDataList,StudentDataListWSDTO.class,fields);
    }
}