# OCC Webservices in Hybris

This project is based on the blog post: [OCC Webservices in Hybris](https://community.sap.com/t5/crm-and-cx-blogs-by-members/occ-webservices-in-hybris/ba-p/13525173). It demonstrates the setup of OCC Webservices in SAP Hybris, extending it with a **many-to-many relationship** between **Teachers and Subjects**.

## 📌 Features

- Exposes REST endpoints using **OCC Webservices**
- Implements **Students API** with GET requests
- Demonstrates **Many-to-Many Relationship** with **Teachers and Subjects**
- Uses **FlexibleSearch Queries** to fetch relationships

---

## 🚀 Step 1: Accessing the Swagger Page

To explore the APIs, open the **Swagger UI**:

🔗 **URL:** `https://localhost:9002/delltrainingcommercewebservices/v2/swagger-ui.html`

Or, you can query via `curl`.

---

### Populate the Database

```sql
INSERT_UPDATE UserGroup;uid[unique=true];locname[lang=de];locname[lang=en];groups(uid)
;customermanagergroup;Kunden Manager-Gruppe;Customer Manager Group;employeegroup

INSERT_UPDATE StudentDetails;studentId[unique=true];studentName;studentPlace;studentGuardian
                            ;101;John;Manila;Dad
                            ;102;Jane;Taguig;Mom
```

## 📌 Step 2: Authentication

```
INSERT_UPDATE OAuthClientDetails;clientId[unique=true];resourceIds;scope;authorizedGrantTypes;clientSecret;authorities
                                ;students-api;hybris;basic;authorization_code,refresh_token,password,client_credentials;S3cr3tP@ss!;ROLE_TRUSTED_CLIENT

```

Check the OAuth client details using the following FlexSearch query:

```
Select * from {OAuthClientDetails};
```

Authenticate with the OAuth client using the following `curl` command:

```sh
curl -X POST "https://localhost:9002/authorizationserver/oauth/token" \
     -H "Content-Type: application/x-www-form-urlencoded" \
     -d "grant_type=client_credentials" \
     -d "client_id=students-api" \
     -d "client_secret=S3cr3tP@ss!" -k
```

### 📌 Example Response:

```json
{
  "access_token": "f9c64689-c8aa-41a4-be60-ccae5da18805",
  "token_type": "bearer",
  "expires_in": 43199,
  "scope": "basic openid"
}
```

---

## 📌 Step 3: Fetching Student Details

Once authenticated, use the access token to fetch a student’s details:

```sh
curl -X GET "https://localhost:9002/delltrainingcommercewebservices/v2/electronics/102" \
     -H "Authorization: Bearer f9c64689-c8aa-41a4-be60-ccae5da18805" \
     -H "Content-Type: application/json" -k
```

### 📌 Example Response:

```json
{
  "student": [
    {
      "studentId": "102",
      "studentName": "Jane",
      "studentPlace": "Taguig"
    }
  ]
}
```

---

## 📌 Step 4: Implementing Many-to-Many (Teachers & Subjects)

We extend the project by adding **Teachers and Subjects**, demonstrating a many-to-many relationship.

### **1️⃣ Define the Relation in `delltrainingcommercewebservices-items.xml`**

```xml

      <itemtype code="StudentDetails" autocreate="true" generate="true">
            <deployment table="StudentTable" typecode="15020" />
            <attributes>
                <attribute qualifier="studentId" type="java.lang.String">
                    <description>Student Id</description>
                    <modifiers unique="true" read="true" write="true" search="true" />
                    <persistence type="property" />
                </attribute>

                <attribute qualifier="studentName" type="java.lang.String">
                    <description>Student Name</description>
                    <modifiers read="true" write="true" search="true" />
                    <persistence type="property" />
                </attribute>

                <attribute qualifier="studentPlace" type="java.lang.String">
                    <description>Students Place name</description>
                    <modifiers read="true" write="true" search="true" />
                    <persistence type="property" />
                </attribute>

                <attribute qualifier="studentGuardian" type="java.lang.String">
                    <description>Student Guardian Name</description>
                    <modifiers read="true" write="true" search="true" />
                    <persistence type="property" />
                </attribute>
            </attributes>
        </itemtype>

        <itemtype code="Subject" autocreate="true" generate="true">
            <deployment table="SubjectTable" typecode="15021" />
            <attributes>
                <attribute qualifier="code" type="java.lang.String">
                    <modifiers read="true" write="true" optional="false" unique="true" />
                    <persistence type="property" />
                </attribute>
                <attribute qualifier="name" type="java.lang.String">
                    <modifiers read="true" write="true" optional="false" />
                    <persistence type="property" />
                </attribute>
            </attributes>
        </itemtype>


```

### **2️⃣ Fetch Sturent Details (FlexibleSearch Query)**

```java


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

```
