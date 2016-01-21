package kkamnyang.domain;

public class EventVO {
	
	private Integer eventno;
	private Integer routeno;
	private String title;
	private String content; //nullê°??Š¥
	private double lat;
	private double lng;
	private String img; //nullê°??Š¥
	private String video; //nullê°??Š¥
	private boolean camera; //nullê°??Š¥
	private String camMessage; //nullê°??Š¥
	private Integer eorder; //nullê°??Š¥
	private String efiles;
	
	
	
	public String getEfiles() {
		return efiles;
	}
	public void setEfiles(String efiles) {
		this.efiles = efiles;
	}
	
	public Integer getEventno() {
		return eventno;
	}
	public void setEventno(Integer eventno) {
		this.eventno = eventno;
	}
	public Integer getRouteno() {
		return routeno;
	}
	public void setRouteno(Integer routeno) {
		this.routeno = routeno;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public double getLat() {
		return lat;
	}
	public void setLat(double lat) {
		this.lat = lat;
	}
	public double getLng() {
		return lng;
	}
	public void setLng(double lng) {
		this.lng = lng;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public String getVideo() {
		return video;
	}
	public void setVideo(String video) {
		this.video = video;
	}
	public boolean isCamera() {
		return camera;
	}
	public void setCamera(boolean camera) {
		this.camera = camera;
	}
	public String getCamMessage() {
		return camMessage;
	}
	public void setCamMessage(String camMessage) {
		this.camMessage = camMessage;
	}
	public Integer getEorder() {
		return eorder;
	}
	public void setEorder(Integer eorder) {
		this.eorder = eorder;
	}
	@Override
	public String toString() {
		return "EventVO [eventno=" + eventno + ", routeno=" + routeno + ", title=" + title + ", content=" + content
				+ ", lat=" + lat + ", lng=" + lng + ", img=" + img + ", video=" + video + ", camera=" + camera
				+ ", camMessage=" + camMessage + ", eorder=" + eorder + "]";
	}
}
