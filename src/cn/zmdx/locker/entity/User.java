package cn.zmdx.locker.entity;

public class User {
	
	public int getWorker_no() {
		return worker_no;
	}
	public void setWorker_no(int worker_no) {
		this.worker_no = worker_no;
	}
	public String getWorker_name() {
		return worker_name;
	}
	public void setWorker_name(String worker_name) {
		this.worker_name = worker_name;
	}
	public String getWorker_phone() {
		return worker_phone;
	}
	public void setWorker_phone(String worker_phone) {
		this.worker_phone = worker_phone;
	}
	public String getIdentitycard_no() {
		return identitycard_no;
	}
	public void setIdentitycard_no(String identitycard_no) {
		this.identitycard_no = identitycard_no;
	}
	public String getArea_no() {
		return area_no;
	}
	public void setArea_no(String area_no) {
		this.area_no = area_no;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	private int worker_no;
	private String worker_name;
	private String worker_phone;
	private String identitycard_no;
	private String area_no;
	private String password;

}
