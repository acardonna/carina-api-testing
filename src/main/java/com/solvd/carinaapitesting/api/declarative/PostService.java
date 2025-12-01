package com.solvd.carinaapitesting.api.declarative;

import com.zebrunner.carina.api.AbstractApiMethodV2;
import com.zebrunner.carina.api.annotation.EndpointTemplate;
import com.zebrunner.carina.api.annotation.EndpointTemplateMethod;
import com.zebrunner.carina.api.annotation.PathParam;
import com.zebrunner.carina.api.annotation.PropertiesPath;
import com.zebrunner.carina.api.annotation.Property;
import com.zebrunner.carina.api.annotation.RequestTemplatePath;
import com.zebrunner.carina.api.annotation.ResponseTemplatePath;
import com.zebrunner.carina.api.annotation.SuccessfulHttpStatus;
import com.zebrunner.carina.api.http.HttpMethodType;
import com.zebrunner.carina.api.http.HttpResponseStatusType;

@EndpointTemplate(url = "${config.api_url}/posts")
public interface PostService {

    @EndpointTemplateMethod(url = "/", methodType = HttpMethodType.GET)
    @ResponseTemplatePath(path = "api/posts/_get/rs.json")
    @SuccessfulHttpStatus(status = HttpResponseStatusType.OK_200)
    AbstractApiMethodV2 getPosts();

    @EndpointTemplateMethod(url = "/${id}", methodType = HttpMethodType.GET)
    @ResponseTemplatePath(path = "api/posts/_get/one_rs.json")
    @SuccessfulHttpStatus(status = HttpResponseStatusType.OK_200)
    AbstractApiMethodV2 getPost(@PathParam(key = "id") int id);

    @EndpointTemplateMethod(url = "/", methodType = HttpMethodType.POST)
    @RequestTemplatePath(path = "api/posts/_post/rq.json")
    @ResponseTemplatePath(path = "api/posts/_post/rs.json")
    @PropertiesPath(path = "api/posts/post.properties")
    @SuccessfulHttpStatus(status = HttpResponseStatusType.CREATED_201)
    AbstractApiMethodV2 createPost();

    @EndpointTemplateMethod(url = "/${id}", methodType = HttpMethodType.PUT)
    @RequestTemplatePath(path = "api/posts/_put/rq.json")
    @ResponseTemplatePath(path = "api/posts/_put/rs.json")
    @PropertiesPath(path = "api/posts/post.properties")
    @SuccessfulHttpStatus(status = HttpResponseStatusType.OK_200)
    AbstractApiMethodV2 updatePost(@PathParam(key = "id") @Property("id") int id);

    @EndpointTemplateMethod(url = "/${id}", methodType = HttpMethodType.DELETE)
    @ResponseTemplatePath(path = "api/posts/_delete/rs.json")
    @SuccessfulHttpStatus(status = HttpResponseStatusType.OK_200)
    AbstractApiMethodV2 deletePost(@PathParam(key = "id") int id);
}
