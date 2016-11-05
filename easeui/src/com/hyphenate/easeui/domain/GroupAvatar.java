package com.hyphenate.easeui.domain;

import java.io.Serializable;

public class GroupAvatar implements Serializable{
	private static final long serialVersionUID = 1L;
	private Integer mavatarId;
	private String mavatarUserName;
	private String mavatarPath;
	private String mavatarSuffix;
	private Integer mavatarType;
	private String mavatarLastUpdateTime;
	private Integer mgroupId;
	private String mgroupHxid;
	private String mgroupName;
	private String mgroupDescription;
	private String mgroupOwner;
	private String mgroupLastModifiedTime;
	private Integer mgroupMaxUsers;
	private Integer mgroupAffiliationsCount;
	private Boolean mgroupIsPublic;
	private Boolean mgroupAllowInvites;
	public GroupAvatar() {
		super();
	}
	public GroupAvatar(Integer mavatarId, String mavatarUserName, String mavatarPath, String mavatarSuffix, Integer mavatarType,
			String mavatarLastUpdateTime, Integer mgroupId, String mgroupHxid, String mgroupName,
			String mgroupDescription, String mgroupOwner, String mgroupLastModifiedTime, Integer mgroupMaxUsers,
			Integer mgroupAffiliationsCount, Boolean mgroupIsPublic, Boolean mgroupAllowInvites) {
		super();
		this.mavatarId = mavatarId;
		this.mavatarUserName = mavatarUserName;
		this.mavatarPath = mavatarPath;
		this.mavatarSuffix = mavatarSuffix;
		this.mavatarType = mavatarType;
		this.mavatarLastUpdateTime = mavatarLastUpdateTime;
		this.mgroupId = mgroupId;
		this.mgroupHxid = mgroupHxid;
		this.mgroupName = mgroupName;
		this.mgroupDescription = mgroupDescription;
		this.mgroupOwner = mgroupOwner;
		this.mgroupLastModifiedTime = mgroupLastModifiedTime;
		this.mgroupMaxUsers = mgroupMaxUsers;
		this.mgroupAffiliationsCount = mgroupAffiliationsCount;
		this.mgroupIsPublic = mgroupIsPublic;
		this.mgroupAllowInvites = mgroupAllowInvites;
	}
	public Integer getMAvatarId() {
		return mavatarId;
	}
	public void setMAvatarId(Integer mavatarId) {
		this.mavatarId = mavatarId;
	}
	public String getMAvatarUserName() {
		return mavatarUserName;
	}
	public void setMAvatarUserName(String mavatarUserName) {
		this.mavatarUserName = mavatarUserName;
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
	public Integer getMGroupId() {
		return mgroupId;
	}
	public void setMGroupId(Integer mgroupId) {
		this.mgroupId = mgroupId;
	}
	public String getMGroupHxid() {
		return mgroupHxid;
	}
	public void setMGroupHxid(String mgroupHxid) {
		this.mgroupHxid = mgroupHxid;
	}
	public String getMGroupName() {
		return mgroupName;
	}
	public void setMGroupName(String mgroupName) {
		this.mgroupName = mgroupName;
	}
	public String getMGroupDescription() {
		return mgroupDescription;
	}
	public void setMGroupDescription(String mgroupDescription) {
		this.mgroupDescription = mgroupDescription;
	}
	public String getMGroupOwner() {
		return mgroupOwner;
	}
	public void setMGroupOwner(String mgroupOwner) {
		this.mgroupOwner = mgroupOwner;
	}
	public String getMGroupLastModifiedTime() {
		return mgroupLastModifiedTime;
	}
	public void setMGroupLastModifiedTime(String mgroupLastModifiedTime) {
		this.mgroupLastModifiedTime = mgroupLastModifiedTime;
	}
	public Integer getMGroupMaxUsers() {
		return mgroupMaxUsers;
	}
	public void setMGroupMaxUsers(Integer mgroupMaxUsers) {
		this.mgroupMaxUsers = mgroupMaxUsers;
	}
	public Integer getMGroupAffiliationsCount() {
		return mgroupAffiliationsCount;
	}
	public void setMGroupAffiliationsCount(Integer mgroupAffiliationsCount) {
		this.mgroupAffiliationsCount = mgroupAffiliationsCount;
	}
	public Boolean getMGroupIsPublic() {
		return mgroupIsPublic;
	}
	public void setMGroupIsPublic(Boolean mgroupIsPublic) {
		this.mgroupIsPublic = mgroupIsPublic;
	}
	public Boolean getMGroupAllowInvites() {
		return mgroupAllowInvites;
	}
	public void setMGroupAllowInvites(Boolean mgroupAllowInvites) {
		this.mgroupAllowInvites = mgroupAllowInvites;
	}
	
	public String getMAvatarSuffix() {
		return mavatarSuffix;
	}
	public void setMAvatarSuffix(String mavatarSuffix) {
		this.mavatarSuffix = mavatarSuffix;
	}
	
	@Override
	public String toString() {
		return "GroupAvatar [mavatarId=" + mavatarId + ", mavatarUserName=" + mavatarUserName + ", mavatarPath="
				+ mavatarPath + ", mavatarSuffix=" + mavatarSuffix + ", mavatarType=" + mavatarType
				+ ", mavatarLastUpdateTime=" + mavatarLastUpdateTime + ", mgroupId=" + mgroupId + ", mgroupHxid="
				+ mgroupHxid + ", mgroupName=" + mgroupName + ", mgroupDescription=" + mgroupDescription
				+ ", mgroupOwner=" + mgroupOwner + ", mgroupLastModifiedTime=" + mgroupLastModifiedTime
				+ ", mgroupMaxUsers=" + mgroupMaxUsers + ", mgroupAffiliationsCount=" + mgroupAffiliationsCount
				+ ", mgroupIsPublic=" + mgroupIsPublic + ", mgroupAllowInvites=" + mgroupAllowInvites + "]";
	}
	
}
