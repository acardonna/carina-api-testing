package com.solvd.carinaapitesting.api.deprecated;

import com.zebrunner.carina.api.AbstractApiMethodV2;
import com.zebrunner.carina.api.annotation.SuccessfulHttpStatus;
import com.zebrunner.carina.api.http.HttpResponseStatusType;

@SuccessfulHttpStatus(status = HttpResponseStatusType.OK_200)
public class DeletePostMethod extends AbstractApiMethodV2 {
    public DeletePostMethod(int id) {
        super(null, "api/posts/_delete/rs.json", "api/posts/post.properties");
        replaceUrlPlaceholder("id", String.valueOf(id));
    }
}
