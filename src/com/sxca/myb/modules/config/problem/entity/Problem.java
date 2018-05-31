package com.sxca.myb.modules.config.problem.entity;

import java.util.List;

import com.sxca.myb.common.persistence.DataEntity;

public class Problem extends DataEntity<Problem> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String feedbackProblem;
	private String pic;
	private String contact;
	private String score;
	private List picList;
	
	public String getFeedbackProblem() {
		return feedbackProblem;
	}
	public void setFeedbackProblem(String feedbackProblem) {
		this.feedbackProblem = feedbackProblem;
	}
	public String getPic() {
		return pic;
	}
	public void setPic(String pic) {
		this.pic = pic;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public String getScore() {
		return score;
	}
	public void setScore(String score) {
		this.score = score;
	}
	public List getPicList() {
		return picList;
	}
	public void setPicList(List picList) {
		this.picList = picList;
	}
	
	

}
