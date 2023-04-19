package com.myblog.playload;

import lombok.Data;

@Data
public class PostDto {//it is POJO class(plain old java object) is a java class not extended 
private long Id;
private String title;
private String description;
private String content;
public long getId() {
	return Id;
}
public void setId(long id) {
	Id = id;
}
public String getTitle() {
	return title;
}
public void setTitle(String title) {
	this.title = title;
}
public String getDescription() {
	return description;
}
public void setDescription(String description) {
	this.description = description;
}
public String getContent() {
	return content;
}
public void setContent(String content) {
	this.content = content;
}

}
