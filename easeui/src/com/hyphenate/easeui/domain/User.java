package com.hyphenate.easeui.domain;

import com.hyphenate.easeui.utils.EaseCommonUtils;

import java.io.Serializable;


public class User implements Serializable {
	private String muserName;
	private String muserNick;
	private Integer mavatarId;
	private String mavatarPath;
	private String mavatarSuffix;
	private Integer mavatarType;
	private String mavatarLastUpdateTime;



	public String getInitialLetter() {
		if(initialLetter == null){
			EaseCommonUtils.setAppUserInitialLetter(this);
		}
		return initialLetter;
	}

	public void setInitialLetter(String initialLetter) {
		this.initialLetter = initialLetter;
	}


	/**
	 * initial letter for nickname
	 */
	protected String initialLetter;
	
	public User() {
		super();
	}

	public User(String muserName) {
		this.muserName = muserName;
	}

	public User(String muserName, String muserNick,
				Integer mavatarId, String mavatarPath, String mavatarSuffix, Integer mavatarType,
				String mavatarLastUpdateTime) {
		super();
		this.muserName = muserName;
		this.muserNick = muserNick;
		this.mavatarId = mavatarId;
		this.mavatarPath = mavatarPath;
		this.mavatarSuffix = mavatarSuffix;
		this.mavatarType = mavatarType;
		this.mavatarLastUpdateTime = mavatarLastUpdateTime;
	}

	public String getMUserName() {
		return muserName;
	}

	public void setMUserName(String muserName) {
		this.muserName = muserName;
	}

	public String getMUserNick() {
		return muserNick;
	}

	public void setMUserNick(String muserNick) {
		this.muserNick = muserNick;
	}
	
	public Integer getMAvatarId() {
		return mavatarId;
	}

	public void setMAvatarId(Integer mavatarId) {
		this.mavatarId = mavatarId;
	}
	public String getMAvatarPath() {
		return mavatarPath;
	}

	public void setMAvatarPath(String mavatarPath) {
		this.mavatarPath = mavatarPath;
	}

	public Integer getMAvatarType() {
		return mavatarType;
	}

	public void setMAvatarType(Integer mavatarType) {
		this.mavatarType = mavatarType;
	}

	public String getMAvatarLastUpdateTime() {
		return mavatarLastUpdateTime;
	}

	public void setMAvatarLastUpdateTime(String mavatarLastUpdateTime) {
		this.mavatarLastUpdateTime = mavatarLastUpdateTime;
	}
	
	public void setMAvatarSuffix(String mavatarSuffix) {
		this.mavatarSuffix =mavatarSuffix;
	}

	public String getMAvatarSuffix() {
		return mavatarSuffix ==null?".jpg":mavatarSuffix;
	}

//	public String getInitialLetter() {
//		if(initialLetter == null){
//			EaseCommonUtils.setUserInitialLetter(this);
//		}
//		return initialLetter;
//	}
//
//	public void setInitialLetter(String initialLetter) {
//		this.initialLetter = initialLetter;
//	}

	@Override
	public String toString() {
		return "User [muserName=" + muserName + ", muserNick=" + muserNick + ", mavatarId=" + mavatarId
				+ ", mavatarPath=" + mavatarPath + ", mavatarSuffix=" + mavatarSuffix + ", mavatarType=" + mavatarType
				+ ", mavatarLastUpdateTime=" + mavatarLastUpdateTime + "]";
	}
	public String getAvater(){
		String path = "http://101.251.196.90:8000/SuperWeChatServerV2.0/downloadAvatar?name_or_hxid="+getMUserName()+"&avatarType=user_avatar&m_avatar_suffix="+getMAvatarSuffix();
		return path;
	}
}
