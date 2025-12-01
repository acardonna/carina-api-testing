package com.solvd.carinaapitesting.api.deprecated;

import com.zebrunner.carina.api.AbstractApiMethodV2;
import com.zebrunner.carina.api.annotation.SuccessfulHttpStatus;
import com.zebrunner.carina.api.http.HttpResponseStatusType;

@SuccessfulHttpStatus(status = HttpResponseStatusType.OK_200)
public class GetPostsMethod extends AbstractApiMethodV2 {
    public GetPostsMethod() {
        super(null, "api/posts/_get/rs.json", "api/posts/post.properties");
    }
}
