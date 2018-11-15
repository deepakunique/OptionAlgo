package com.optionAlgo.form.data;

public class SystemStrategyRequestFormDto {
	
		String scripName;
		String view;
		Double target1;
		Double target2;
		String payOffDate;
		String pricingType;
		Integer lotSize;
		
		
		
		public Integer getLotSize() {
			return lotSize;
		}
		public void setLotSize(Integer lotSize) {
			this.lotSize = lotSize;
		}
		public String getPricingType() {
			return pricingType;
		}
		public void setPricingType(String pricingType) {
			this.pricingType = pricingType;
		}
		public String getScripName() {
			return scripName;
		}
		public void setScripName(String scripName) {
			this.scripName = scripName;
		}
		public String getView() {
			return view;
		}
		public void setView(String view) {
			this.view = view;
		}
		public Double getTarget1() {
			return target1;
		}
		public void setTarget1(Double target1) {
			this.target1 = target1;
		}
		public Double getTarget2() {
			return target2;
		}
		public void setTarget2(Double target2) {
			this.target2 = target2;
		}
		public String getPayOffDate() {
			return payOffDate;
		}
		public void setPayOffDate(String payOffDate) {
			this.payOffDate = payOffDate;
		}
		
		
		

}
