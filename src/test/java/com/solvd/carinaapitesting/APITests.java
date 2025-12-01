package com.solvd.carinaapitesting;

import org.skyscreamer.jsonassert.JSONCompareMode;
import org.testng.annotations.Test;

import com.solvd.carinaapitesting.api.declarative.PostService;
import com.solvd.carinaapitesting.api.deprecated.CreatePostMethod;
import com.solvd.carinaapitesting.api.deprecated.DeletePostMethod;
import com.solvd.carinaapitesting.api.deprecated.GetPostMethod;
import com.solvd.carinaapitesting.api.deprecated.GetPostsMethod;
import com.solvd.carinaapitesting.api.deprecated.UpdatePostMethod;
import com.zebrunner.carina.api.AbstractApiMethodV2;
import com.zebrunner.carina.api.apitools.validation.JsonCompareKeywords;
import com.zebrunner.carina.api.binding.TemplateFactory;
import com.zebrunner.carina.api.http.HttpResponseStatusType;
import com.zebrunner.carina.core.IAbstractTest;

public class APITests implements IAbstractTest {

    // Deprecated Approach Tests

    @Test
    public void testGetPostsDeprecated() {
        GetPostsMethod api = new GetPostsMethod();
        api.callAPIExpectSuccess();
        api.validateResponse(JSONCompareMode.LENIENT, JsonCompareKeywords.ARRAY_CONTAINS.getKey());
    }

    @Test
    public void testGetPostDeprecated() {
        GetPostMethod api = new GetPostMethod(1);
        api.callAPIExpectSuccess();
        api.validateResponse();
    }

    @Test
    public void testCreatePostDeprecated() {
        CreatePostMethod api = new CreatePostMethod();
        api.expectResponseStatus(HttpResponseStatusType.CREATED_201);
        api.callAPI();
        api.validateResponse();
    }

    @Test
    public void testUpdatePostDeprecated() {
        UpdatePostMethod api = new UpdatePostMethod(1);
        api.callAPIExpectSuccess();
        api.validateResponse();
    }

    @Test
    public void testDeletePostDeprecated() {
        DeletePostMethod api = new DeletePostMethod(1);
        api.callAPIExpectSuccess();
        api.validateResponse();
    }

    // Declarative Approach Tests

    @Test
    public void testGetPostsDeclarative() {
        PostService postService = TemplateFactory.prepareTemplate(PostService.class);
        AbstractApiMethodV2 method = postService.getPosts();
        method.callAPIExpectSuccess();
        method.validateResponse(JSONCompareMode.LENIENT, JsonCompareKeywords.ARRAY_CONTAINS.getKey());
    }

    @Test
    public void testGetPostDeclarative() {
        PostService postService = TemplateFactory.prepareTemplate(PostService.class);
        AbstractApiMethodV2 method = postService.getPost(1);
        method.callAPIExpectSuccess();
        method.validateResponse();
    }

    @Test
    public void testCreatePostDeclarative() {
        PostService postService = TemplateFactory.prepareTemplate(PostService.class);
        AbstractApiMethodV2 method = postService.createPost();
        method.callAPIExpectSuccess();
        method.validateResponse();
    }

    @Test
    public void testUpdatePostDeclarative() {
        PostService postService = TemplateFactory.prepareTemplate(PostService.class);
        AbstractApiMethodV2 method = postService.updatePost(1);
        method.callAPIExpectSuccess();
        method.validateResponse();
    }

    @Test
    public void testDeletePostDeclarative() {
        PostService postService = TemplateFactory.prepareTemplate(PostService.class);
        AbstractApiMethodV2 method = postService.deletePost(1);
        method.callAPIExpectSuccess();
        method.validateResponse();
    }
}
