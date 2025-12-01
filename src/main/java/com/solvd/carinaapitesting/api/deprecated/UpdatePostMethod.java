package com.solvd.carinaapitesting.api.deprecated;

import com.zebrunner.carina.api.AbstractApiMethodV2;
import com.zebrunner.carina.api.annotation.SuccessfulHttpStatus;
import com.zebrunner.carina.api.http.HttpResponseStatusType;

@SuccessfulHttpStatus(status = HttpResponseStatusType.OK_200)
public class UpdatePostMethod extends AbstractApiMethodV2 {
    public UpdatePostMethod(int id) {
        super("api/posts/_put/rq.json", "api/posts/_put/rs.json", "api/posts/post.properties");
        replaceUrlPlaceholder("id", String.valueOf(id));
        addProperty("id", id);
    }
}
