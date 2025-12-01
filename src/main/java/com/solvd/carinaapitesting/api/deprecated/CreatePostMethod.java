package com.solvd.carinaapitesting.api.deprecated;

import com.zebrunner.carina.api.AbstractApiMethodV2;
import com.zebrunner.carina.api.annotation.SuccessfulHttpStatus;
import com.zebrunner.carina.api.http.HttpResponseStatusType;

@SuccessfulHttpStatus(status = HttpResponseStatusType.CREATED_201)
public class CreatePostMethod extends AbstractApiMethodV2 {
    public CreatePostMethod() {
        super("api/posts/_post/rq.json", "api/posts/_post/rs.json", "api/posts/post.properties");
    }
}
