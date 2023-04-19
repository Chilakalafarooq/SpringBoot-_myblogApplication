package com.myblog.service.impl;

import java.awt.print.Pageable;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.myblog.Exception.ResourceNotFoundException;
import com.myblog.entite.Post;
import com.myblog.playload.PostDto;
import com.myblog.repository.PostRepository;

import net.bytebuddy.asm.Advice.OffsetMapping.Sort;

@Service
public class postServiceImpl implements PostService {
	@Autowired
	private PostRepository postRepository;

	//Another way to inject dependency constructor based
	public postServiceImpl(PostRepository postRepository) {
		super();
		this.postRepository = postRepository;
		//there are two ways to inject the beans in spring boot one is 
		//@Autowired And another way constructor base injection
	}
//Insert Feature
	
	@Override
	public PostDto createPost(PostDto postDto) {
		Post post=maptoEntity(postDto);
		Post newPost=postRepository.save(post);
	
		PostDto postResponse= mapToDTO(newPost);
		
		return postResponse;
	
	}
//Convert Entity to DTO

	PostDto mapToDTO(Post Post) {
        PostDto postDto = new PostDto();
        postDto.setId(Post.getId());
        postDto.setId(Post.getId());
        postDto.setTitle(Post.getTitle());
        postDto.setDescription(Post.getDescription());
        postDto.setContent(Post.getContent());
        return postDto;

	}

//Convert DTO to Entity
	//http://localhost:8080/api/posts
		Post maptoEntity(PostDto postDto) {

		Post post = new Post();
        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
        post.setContent(postDto.getContent());
        return post;
	

}
//Reterieve  Feature
//http://localhost:8080/api/posts
		

	        @Override
			public List<PostDto> getAllPosts() {
		        List<Post> posts = postRepository.findAll();
		        return posts.stream().map(post -> mapToDTO(post)).collect(Collectors.toList());
		}
//update Feature
//http://localhost:8080/api/posts/5
		@Override
		public PostDto updatePost(PostDto postDto, long id) {
			
		Post post =	postRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Post", "id", id));
		post.setTitle(postDto.getTitle());
		post.setDescription(postDto.getDescription());
		post.setContent(postDto.getContent());
		Post updatepost = postRepository.save(post);
		
			return mapToDTO(updatepost);
			
		}

//Delete Feature
//http://localhost:8080/api/posts/5
		@Override
		public void deletePostById(long id) {
			Post post = postRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Post", "id", id));
			postRepository.delete(post);
		}
//Pagination Feature by API

		@Override
		public List<PostDto> getAllPosts(int pageNo, int pageSize, String sortBy, String sortDir) {
			// TODO Auto-generated method stub
			return null;
		}

//		@Override
//		public List<PostDto> getAllPosts(int pageNo, int pageSize, String sortBy, String sortDir) {
//			
//		      Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
//		                : Sort.by(sortBy).descending();
//		       
//		        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
//		        Page<Post> posts = postRepository.findAll(pageable);
//		        // get content for page object
//		        List<Post> listOfPosts = posts.getContent();
//		        List<PostDto> content= listOfPosts.stream().map(post -> mapToDTO(post)).collect(Collectors.toList());
//		        PostResponse postResponse = new PostResponse();
//		        postResponse.setContent(content);
//		        postResponse.setPageNo(posts.getNumber());
//		        postResponse.setPageSize(posts.getSize());
//		        postResponse.setTotalElements(posts.getTotalElements());
//		        postResponse.setTotalPages(posts.getTotalPages());
//		        postResponse.setLast(posts.isLast());
//		        return postResponse;
//		}
//
//
		
		}



