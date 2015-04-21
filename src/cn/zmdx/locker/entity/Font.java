package cn.zmdx.locker.entity;



public class Font{
	private int id;
	private String language;
	private String ttfName;
	private String previewUrl;
	private String ttfUrl;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public String getTtfName() {
		return ttfName;
	}
	public void setTtfName(String ttfName) {
		this.ttfName = ttfName;
	}
	public String getPreviewUrl() {
		return previewUrl;
	}
	public void setPreviewUrl(String previewUrl) {
		this.previewUrl = previewUrl;
	}
	public String getTtfUrl() {
		return ttfUrl;
	}
	public void setTtfUrl(String ttfUrl) {
		this.ttfUrl = ttfUrl;
	}
}
