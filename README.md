"# OCC-Webservices-in-Hybris"

```
INSERT_UPDATE OAuthClientDetails;clientId[unique=true];resourceIds;scope;authorizedGrantTypes;clientSecret;authorities
                                ;students-api;hybris;basic;authorization_code,refresh_token,password,client_credentials;S3cr3tP@ss!;ROLE_TRUSTED_CLIENT

```

```
INSERT_UPDATE OAuthClientDetails;clientId[unique=true];resourceIds;scope;authorizedGrantTypes;clientSecret;authorities
                                ;students-api;hybris;basic;authorization_code,refresh_token,password,client_credentials;S3cr3tP@ss!;ROLE_TRUSTED_CLIENT

```

```
Select * from OAuthClientDetails;
```

```
curl --location 'https://localhost:9002/authorizationserver/oauth/token' \
--header 'Content-Type: application/x-www-form-urlencoded' \
--header 'Authorization: Bearer 5deb17a4-d74e-40df-99a7-e4305e05751d' \
--header 'Cookie: JSESSIONID=D31CB1F7F44C2C6F29F7CB571C7E92A6; COOKIE_SUPPORT=true; GUEST_LANGUAGE_ID=en_US' \
--data-urlencode 'grant_type=client_credentials' \
--data-urlencode 'client_id=students-api' \
--data-urlencode 'client_secret=S3cr3tP@ss!'
```

```

curl --location 'https://localhost:9002/authorizationserver/oauth/token' \
--header 'Content-Type: application/x-www-form-urlencoded' \
--header 'Authorization: Bearer 5deb17a4-d74e-40df-99a7-e4305e05751d' \
--header 'Cookie: JSESSIONID=D31CB1F7F44C2C6F29F7CB571C7E92A6; COOKIE_SUPPORT=true; GUEST_LANGUAGE_ID=en_US' \
--data-urlencode 'grant_type=client_credentials' \
--data-urlencode 'client_id=students-api' \
--data-urlencode 'client_secret=S3cr3tP@ss!'

```

```
curl -X POST "https://localhost:9002/authorizationserver/oauth/token" \
     -H "Content-Type: application/x-www-form-urlencoded" \
     -d "grant_type=client_credentials" \
     -d "client_id=students-api" \
     -d "client_secret=S3cr3tP@ss!"
```

```
curl -X GET "https://localhost:9002/delltrainingcommercewebservices/v2/electronics/102" \
     -H "Authorization: Bearer 5deb17a4-d74e-40df-99a7-e4305e05751d" \
     -H "Content-Type: application/json" -k
```

```
{
   "student" : [ {
      "studentId" : "102",
      "studentName" : "Jane",
      "studentPlace" : "Taguig"
   } ]
}%
```

```

```
