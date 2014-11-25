package cn.zmdx.locker.entity;

import java.sql.Timestamp;


public class WallPaper {
	
	private int id;
	private String name;
	private String desc;
	private String author;
	private String thumbURL;
	private String imageURL;
	private String imageNAME;
	private String imageEXT;
	private Timestamp publishDATE;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getThumbURL() {
		return thumbURL;
	}
	public void setThumbURL(String thumbURL) {
		this.thumbURL = thumbURL;
	}
	public String getImageURL() {
		return imageURL;
	}
	public void setImageURL(String imageURL) {
		this.imageURL = imageURL;
	}
	public String getImageNAME() {
		return imageNAME;
	}
	public void setImageNAME(String imageNAME) {
		this.imageNAME = imageNAME;
	}
	public String getImageEXT() {
		return imageEXT;
	}
	public void setImageEXT(String imageEXT) {
		this.imageEXT = imageEXT;
	}
	public Timestamp getPublishDATE() {
		return publishDATE;
	}
	public void setPublishDATE(Timestamp publishDATE) {
		this.publishDATE = publishDATE;
	}
	
}
