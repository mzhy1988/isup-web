package com.hns.iusp.ws.po;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

import com.hns.iusp.ws.xml.XmlHeadElement;


@XmlRootElement(name="Receive")
@XmlHeadElement
public class Receive extends AbstractMessageObj {
	/**
	 * 单据站点编码
	 */
	private String DocSiteCode;
	/**
	 * 单据站点中的收货单号
	 */
	private String DocSiteReceiveID;
	/**
	 * 发送站点编码
	 */
	private String SiteCode;

	/**
	 * 发送站点中的收货单号
	 */
	private String ReceiveID;

	private String ReceiveDocNo;
	/**
	 * 订单单据站点编码
	 */
	private String OrderSiteCode;

	/**
	 * 发送站点中的采购订单编号
	 * 非必填
	 */
	private String SiteOrderID;

	/**
	 * 订单单据站点中的采购订单编号
	 * 当订单单据站点为空时，表示接收站点中的订单号
	 * 非必填
	 * 
	 */
	private String OrderID;
	
//	/*
//	 * 是否期初订单.
//	 * 期初订单指平台上线前ERP内完成的订单，这些订单不上传到平台
//	 */
//	private String IsLegacyOrder;
	

	private String OrgCode;
	
	private String BPartnerSiteCode;

	private String BPartnerID;

//	private String BPartnerUID;

	private String BPartnerName;
	
	private String WarehouseSiteCode;

	private String WarehouseID;

	private String WarehouseName;

	private String DateReceived;

	private String LineCount;

	private String IsReturnDoc;

//	private String DocFlag;

	private String Remark;

	private String dateAcct;
	private String createUser;
	private String createUserCode;
	private String salesRep;
	private String salesRepCode;
	private String department;
	private String departmentCode;
	private String isLegacy;

	private String Timestamp;
	/**
	 * @since 20140127
	 */
	private String createTime;
	private String commitUser;
	private String commitUserCode;
	private String commitTime;
	private String confirmUser;
	private String confirmUserCode;
	private String confirmTime;

	private ReceiveLine[] Lines;
	public String getBPartnerID() {
		return BPartnerID;
	}

	public void setBPartnerID(String bpartnerID) {
		BPartnerID = bpartnerID;
	}

	public String getBPartnerName() {
		return BPartnerName;
	}

	public void setBPartnerName(String partnerName) {
		BPartnerName = partnerName;
	}

	public String getDateReceived() {
		return DateReceived;
	}

	public void setDateReceived(String dateReceived) {
		DateReceived = dateReceived;
	}

//	public String getDocFlag() {
//		return DocFlag;
//	}
//
//	public void setDocFlag(String docFlag) {
//		DocFlag = docFlag;
//	}

	public String getIsReturnDoc() {
		return IsReturnDoc;
	}

	public void setIsReturnDoc(String isReturnDoc) {
		IsReturnDoc = isReturnDoc;
	}

	

	public String getLineCount() {
		return LineCount;
	}

	public void setLineCount(String lineCount) {
		LineCount = lineCount;
	}

	public String getOrderID() {
		return OrderID;
	}

	public void setOrderID(String orderID) {
		OrderID = orderID;
	}

	public String getOrgCode() {
		return OrgCode;
	}

	public void setOrgCode(String orgCode) {
		OrgCode = orgCode;
	}

	public String getReceiveID() {
		return ReceiveID;
	}

	public void setReceiveID(String receiveID) {
		ReceiveID = receiveID;
	}

	public String getRemark() {
		return Remark;
	}

	public void setRemark(String remark) {
		Remark = remark;
	}

	public String getSiteCode() {
		return SiteCode;
	}

	public void setSiteCode(String siteCode) {
		SiteCode = siteCode;
	}

	public String getTimestamp() {
		return Timestamp;
	}

	public void setTimestamp(String timestamp) {
		Timestamp = timestamp;
	}

	public String getWarehouseID() {
		return WarehouseID;
	}

	public void setWarehouseID(String warehouseID) {
		WarehouseID = warehouseID;
	}

	public String getWarehouseName() {
		return WarehouseName;
	}

	public void setWarehouseName(String warehouseName) {
		WarehouseName = warehouseName;
	}

	public String getSiteOrderID() {
		return SiteOrderID;
	}

	public void setSiteOrderID(String siteOrderID) {
		SiteOrderID = siteOrderID;
	}

//	public String getBPartnerUID() {
//		return BPartnerUID;
//	}
//
//	public void setBPartnerUID(String partnerUID) {
//		BPartnerUID = partnerUID;
//	}
//
//	public String getReceiveUID() {
//		return ReceiveUID;
//	}
//
//	public void setReceiveUID(String receiveUID) {
//		ReceiveUID = receiveUID;
//	}

//	public String getIsLegacyOrder() {
//		return IsLegacyOrder;
//	}
//
//	public void setIsLegacyOrder(String isLeagalOrder) {
//		IsLegacyOrder = isLeagalOrder;
//	}

	public String getDocSiteCode() {
		return DocSiteCode;
	}

	public void setDocSiteCode(String docSiteCode) {
		DocSiteCode = docSiteCode;
	}

	public String getDocSiteReceiveID() {
		return DocSiteReceiveID;
	}

	public void setDocSiteReceiveID(String docSiteReceiveID) {
		DocSiteReceiveID = docSiteReceiveID;
	}

	public String getOrderSiteCode() {
		return OrderSiteCode;
	}

	public void setOrderSiteCode(String orderSiteCode) {
		OrderSiteCode = orderSiteCode;
	}

	public String toString() {
		return DocSiteReceiveID!=null&&DocSiteReceiveID.length()>0?DocSiteReceiveID:ReceiveID;
	}

	@XmlElementWrapper (name="detail")
	@XmlElement(name = "line")
	public ReceiveLine[] getLines() {
		return Lines;
	}

	public void setLines(ReceiveLine[] lines) {
		Lines = lines;
	}

	public String getBPartnerSiteCode() {
		return BPartnerSiteCode;
	}

	public void setBPartnerSiteCode(String partnerSiteCode) {
		BPartnerSiteCode = partnerSiteCode;
	}

	public String getWarehouseSiteCode() {
		return WarehouseSiteCode;
	}

	public void setWarehouseSiteCode(String warehouseSiteCode) {
		WarehouseSiteCode = warehouseSiteCode;
	}

	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	public String getCreateUserCode() {
		return createUserCode;
	}

	public void setCreateUserCode(String createUserCode) {
		this.createUserCode = createUserCode;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getDepartmentCode() {
		return departmentCode;
	}

	public void setDepartmentCode(String departmentCode) {
		this.departmentCode = departmentCode;
	}

	public String getIsLegacy() {
		return isLegacy;
	}

	public void setIsLegacy(String isLegacy) {
		this.isLegacy = isLegacy;
	}

	public String getSalesRep() {
		return salesRep;
	}

	public void setSalesRep(String salesRep) {
		this.salesRep = salesRep;
	}

	public String getSalesRepCode() {
		return salesRepCode;
	}

	public void setSalesRepCode(String salesRepCode) {
		this.salesRepCode = salesRepCode;
	}

	public String getDateAcct() {
		return dateAcct;
	}

	public void setDateAcct(String dateAcct) {
		this.dateAcct = dateAcct;
	}

	public String getReceiveDocNo() {
		return ReceiveDocNo;
	}

	public void setReceiveDocNo(String receiveDocNo) {
		ReceiveDocNo = receiveDocNo;
	}

	public String getCommitTime() {
		return commitTime;
	}

	public void setCommitTime(String commitTime) {
		this.commitTime = commitTime;
	}

	public String getCommitUser() {
		return commitUser;
	}

	public void setCommitUser(String commitUser) {
		this.commitUser = commitUser;
	}

	public String getCommitUserCode() {
		return commitUserCode;
	}

	public void setCommitUserCode(String commitUserCode) {
		this.commitUserCode = commitUserCode;
	}

	public String getConfirmTime() {
		return confirmTime;
	}

	public void setConfirmTime(String completeTime) {
		this.confirmTime = completeTime;
	}

	public String getConfirmUser() {
		return confirmUser;
	}

	public void setConfirmUser(String completeUser) {
		this.confirmUser = completeUser;
	}

	public String getConfirmUserCode() {
		return confirmUserCode;
	}

	public void setConfirmUserCode(String completeUserCode) {
		this.confirmUserCode = completeUserCode;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

}
