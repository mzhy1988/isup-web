package com.hns.iusp.ws.po;

import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name="ReceiveLine")
public class ReceiveLine extends AbstractMessageObj{
	/**
	 * 单据站点中的收货单行号
	 */
	private String DocSiteReceiveLineID;
	/**
	 * 发送站点中的收货单行号
	 */
	private String ReceiveLineID;

	//货主机构编码
	private String StorageOrgCode;

	/**
	 * 发送站点中的订单号
	 */
	private String SiteOrderID;
	/**
	 * 发送站点中的订单行号
	 */
	private String SiteOrderLineID;

	/**
	 * 订单单据站点编码
	 */
	private String OrderSiteCode;
	/**
	 * 订单单据站点中的订单号
	 * 当订单单据站点为空时，表示接收站点的订单号
	 */
	private String OrderID;
	
	/**
	 * 订单单据站点中的订单行号
	 * 当订单单据站点为空时，表示接收站点的订单行号
	 */
	private String OrderLineID;
//	/*
//	 * 是否期初订单.
//	 * 期初订单指平台上线前ERP内完成的订单，这些订单不上传到平台
//	 */
//	private String IsLegacyOrder;

	/**
	 * 产品目录站点编码
	 */
	private String ProductSiteCode;
	/**
	 * 产品目录站点中的产品编码
	 */
	private String ProductID;

	private String Lot;

	private String GuaranteeDate;

	private String ProductionDate;
	
	private String VendorSiteCode;

	private String VendorID;
	//private String VendorUID;

	private String VendorName;

	private String Qty;

	private String QtyRejected;

	private String RejectReason;
//
//	private String MissingDocFlag;

	private String StorageStatus;

	private String PricePO;

	private String Price;
	
	private String Remark;

	private String MedicineName;

	private String ProductName;

	private String ProductStyle;

	private String UOM;

	private String ProductSpec;

	private String Manufacturer;

	private String CertificateNo;
	private String BaseUOM;
	private String PackageQty;
	
	private String PackageMaterial;
	private String medicineSpec;
	/**
	 * ASN单据站点
	 */
	private String ASNSiteCode;
	
	/**
	 * ASN单据站点中的ASN行号
	 */
	private String ASNLineID;

	/**
	 * ASN单据站点中的ASN号
	 */
	private String ASNID;

	
	
	//Since 2014/1/1
	//add by jameszz
	private String DateReceived; 
	
	//Since 2014/1/1
	//add by jameszz
	private String PriceCost;

	//Since 2014/1/1
	//add by jameszz
	private String BatchID;
	/*
	 * since 2014-01-10 james add
	 */
	private String LargePackageQty;
	private String MiddlePackageQty;
	private String ProductArea;
	private String SerNo;
	

	
//	public String getInvoiceTypeNo() {
//		return InvoiceTypeNo;
//	}
//
//	public void setInvoiceTypeNo(String invoiceTypeNo) {
//		InvoiceTypeNo = invoiceTypeNo;
//	}

	public String getASNID() {
		return ASNID;
	}

	public void setASNID(String asnid) {
		ASNID = asnid;
	}

	public String getASNLineID() {
		return ASNLineID;
	}

	public void setASNLineID(String lineID) {
		ASNLineID = lineID;
	}

//	public String getEntryBatchNo() {
//		return EntryBatchNo;
//	}
//
//	public void setEntryBatchNo(String entryBatchNo) {
//		EntryBatchNo = entryBatchNo;
//	}

	public String getGuaranteeDate() {
		return GuaranteeDate;
	}

	public void setGuaranteeDate(String guaranteeDate) {
		GuaranteeDate = guaranteeDate;
	}

//	public String getInvoiceNo() {
//		return InvoiceNo;
//	}
//
//	public void setInvoiceNo(String invoiceNo) {
//		InvoiceNo = invoiceNo;
//	}

	public String getLot() {
		return Lot;
	}

	public void setLot(String lot) {
		Lot = lot;
	}

	public String getManufacturer() {
		return Manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		Manufacturer = manufacturer;
	}

	public String getMedicineName() {
		return MedicineName;
	}

	public void setMedicineName(String medicineName) {
		MedicineName = medicineName;
	}

//	public String getMissingDocFlag() {
//		return MissingDocFlag;
//	}
//
//	public void setMissingDocFlag(String missingDocFlag) {
//		MissingDocFlag = missingDocFlag;
//	}

	public String getOrderLineID() {
		return OrderLineID;
	}

	public void setOrderLineID(String orderLineID) {
		OrderLineID = orderLineID;
	}

	public String getPricePO() {
		return PricePO;
	}

	public void setPricePO(String pricePO) {
		PricePO = pricePO;
	}

	public String getProductID() {
		return ProductID;
	}

	public void setProductID(String productID) {
		ProductID = productID;
	}

	public String getProductionDate() {
		return ProductionDate;
	}

	public void setProductionDate(String productionDate) {
		ProductionDate = productionDate;
	}

	public String getProductName() {
		return ProductName;
	}

	public void setProductName(String productName) {
		ProductName = productName;
	}

	public String getProductSpec() {
		return ProductSpec;
	}

	public void setProductSpec(String productSpec) {
		ProductSpec = productSpec;
	}

	public String getProductStyle() {
		return ProductStyle;
	}

	public void setProductStyle(String productStyle) {
		ProductStyle = productStyle;
	}

	public String getQty() {
		return Qty;
	}

	public void setQty(String qty) {
		Qty = qty;
	}

	public String getQtyRejected() {
		return QtyRejected;
	}

	public void setQtyRejected(String qtyRejected) {
		QtyRejected = qtyRejected;
	}

	public String getReceiveLineID() {
		return ReceiveLineID;
	}

	public void setReceiveLineID(String receiveLineID) {
		ReceiveLineID = receiveLineID;
	}

	public String getRejectReason() {
		return RejectReason;
	}

	public void setRejectReason(String rejectReason) {
		RejectReason = rejectReason;
	}

	public String getRemark() {
		return Remark;
	}

	public void setRemark(String remark) {
		Remark = remark;
	}

	public String getStorageStatus() {
		return StorageStatus;
	}

	public void setStorageStatus(String storageStatus) {
		StorageStatus = storageStatus;
	}

	public String getUOM() {
		return UOM;
	}

	public void setUOM(String uom) {
		UOM = uom;
	}

	public String getVendorID() {
		return VendorID;
	}

	public void setVendorID(String vendorID) {
		VendorID = vendorID;
	}

	public String getVendorName() {
		return VendorName;
	}

	public void setVendorName(String vendorName) {
		VendorName = vendorName;
	}

	public String getCertificateNo() {
		return CertificateNo;
	}

	public void setCertificateNo(String certificateNo) {
		CertificateNo = certificateNo;
	}

	public String getOrderID() {
		return OrderID;
	}

	public void setOrderID(String orderID) {
		OrderID = orderID;
	}

	public String getSiteOrderID() {
		return SiteOrderID;
	}

	public void setSiteOrderID(String siteOrderID) {
		SiteOrderID = siteOrderID;
	}

	public String getSiteOrderLineID() {
		return SiteOrderLineID;
	}

	public void setSiteOrderLineID(String siteOrderLineID) {
		SiteOrderLineID = siteOrderLineID;
	}

	public String getPackageMaterial() {
		return PackageMaterial;
	}

	public void setPackageMaterial(String packageMaterial) {
		PackageMaterial = packageMaterial;
	}

	public String getPackageQty() {
		return PackageQty;
	}

	public void setPackageQty(String packageQty) {
		PackageQty = packageQty;
	}

	public String getMedicineSpec() {
		return medicineSpec;
	}

	public void setMedicineSpec(String medicineSpec) {
		this.medicineSpec = medicineSpec;
	}

//	public String getIsLegacyOrder() {
//		return IsLegacyOrder;
//	}
//
//	public void setIsLegacyOrder(String isLeagalOrder) {
//		IsLegacyOrder = isLeagalOrder;
//	}

	public String getDocSiteReceiveLineID() {
		return DocSiteReceiveLineID;
	}

	public void setDocSiteReceiveLineID(String docSiteReceiveLineID) {
		DocSiteReceiveLineID = docSiteReceiveLineID;
	}

	public String getOrderSiteCode() {
		return OrderSiteCode;
	}

	public void setOrderSiteCode(String orderSiteCode) {
		OrderSiteCode = orderSiteCode;
	}

	public String getProductSiteCode() {
		return ProductSiteCode;
	}

	public void setProductSiteCode(String productSiteCode) {
		ProductSiteCode = productSiteCode;
	}

	public String getASNSiteCode() {
		return ASNSiteCode;
	}

	public void setASNSiteCode(String siteCode) {
		ASNSiteCode = siteCode;
	}

	public String getBaseUOM() {
		return BaseUOM;
	}

	public void setBaseUOM(String baseUOM) {
		BaseUOM = baseUOM;
	}

	public String toString() {
		return ReceiveLineID;
	}

	public String getVendorSiteCode() {
		return VendorSiteCode;
	}

	public void setVendorSiteCode(String vendorSiteCode) {
		VendorSiteCode = vendorSiteCode;
	}

	public String getStorageOrgCode() {
		return StorageOrgCode;
	}

	public void setStorageOrgCode(String storageOrgCode) {
		StorageOrgCode = storageOrgCode;
	}

	public String getBatchID() {
		return BatchID;
	}

	public void setBatchID(String batchNo) {
		BatchID = batchNo;
	}

	public String getDateReceived() {
		return DateReceived;
	}

	public void setDateReceived(String dateReceived) {
		DateReceived = dateReceived;
	}

	public String getPriceCost() {
		return PriceCost;
	}

	public void setPriceCost(String priceCost) {
		PriceCost = priceCost;
	}

	public String getLargePackageQty() {
		return LargePackageQty;
	}

	public void setLargePackageQty(String largePackageQty) {
		LargePackageQty = largePackageQty;
	}

	public String getMiddlePackageQty() {
		return MiddlePackageQty;
	}

	public void setMiddlePackageQty(String middlePackageQty) {
		MiddlePackageQty = middlePackageQty;
	}

	public String getProductArea() {
		return ProductArea;
	}

	public void setProductArea(String productArea) {
		ProductArea = productArea;
	}

	public String getSerNo() {
		return SerNo;
	}

	public void setSerNo(String serNo) {
		SerNo = serNo;
	}

	public String getPrice() {
		return Price;
	}

	public void setPrice(String price) {
		Price = price;
	}

}
