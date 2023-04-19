package com.myblog.service.impl;

import java.util.List;

//import com.myblog.controller.PostResponse;
import com.myblog.playload.PostDto;

public interface PostService {
List<PostDto> getAllPosts();
PostDto createPost(PostDto postDto);
PostDto updatePost(PostDto postDto, long id);
void deletePostById(long id);
List<PostDto> getAllPosts(int pageNo, int pageSize, String sortBy, String sortDir);

	
}
