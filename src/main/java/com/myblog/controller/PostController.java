package com.myblog.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ser.std.StdKeySerializers.Default;
import com.myblog.playload.PostDto;
import com.myblog.service.impl.PostService;

import net.bytebuddy.implementation.bytecode.constant.DefaultValue;

@RestController
@RequestMapping("/api/posts")
public class PostController {

	private PostService postservice;
	
	

	public PostController(PostService postservice) {
	this.postservice=postservice;
	
	}
	//Insert feature by API
	//http:	//localhost:8080/api/posts
	@PostMapping
public ResponseEntity<PostDto>	createPost(@RequestBody PostDto postDto){
	
PostDto dto = postservice.createPost(postDto);
	return new ResponseEntity<>(dto,HttpStatus.CREATED);
	
	}
	//Retrieve feature by API
	//http://localhost:8080/api/posts
	@GetMapping("/{id}")
	
    public List<PostDto> getAllPosts(@PathVariable("id")long id){
        return postservice.getAllPosts();

	}
	//update post feature by id API
	//http://localhost:8080/api/posts/5
 @PutMapping("/{id}")
 public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto,@PathVariable(name = "id") long id){
	PostDto postResponse = postservice.updatePost(postDto, id);
	 
	 return new ResponseEntity<>(postResponse,HttpStatus.OK);
 }
 //delete Feature by API
 //http://localhost:8080/api/posts/5
 @DeleteMapping("/{id}")
 public ResponseEntity<String> deletePost(@PathVariable("id")long id){
	 postservice.deletePostById(id);
	 return new ResponseEntity<>("Post entity deleted sucessfully",HttpStatus.OK);
 }
 
 
 
// //Pagination and Sorting in rest API
// 
// //http://localhost:8080/api/posts?PageNo=0&PazeSize=5
  @GetMapping
public List<PostDto> getAllPost(@RequestParam(value = "PageNo",defaultValue = "0",required = false) int pageNo,
								@RequestParam(value = "PageSize",defaultValue="10",required=false)int pageSize,
								 @RequestParam(value ="SortBy",defaultValue="id", required=false)String sortBy,
								@RequestParam(value = "SortDir",defaultValue="acs",required=false)String sortDir) {	 
	 
     return postservice.getAllPosts(pageNo,pageSize,sortBy,sortDir);
	}
}
