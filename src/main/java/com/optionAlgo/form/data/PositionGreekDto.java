package com.optionAlgo.form.data;

public class PositionGreekDto {

	Double delta =0.0;
	Double gamma = 0.0;
	Double iv = 0.0;
	String position;
	Double theta =0.0;
	Double vega =0.0;
	
	
	public Double getDelta() {
		return delta;
	}
	public void setDelta(Double delta) {
		this.delta = delta;
	}
	public Double getGamma() {
		return gamma;
	}
	public void setGamma(Double gamma) {
		this.gamma = gamma;
	}
	public Double getIv() {
		return iv;
	}
	public void setIv(Double iv) {
		this.iv = iv;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public Double getTheta() {
		return theta;
	}
	public void setTheta(Double theta) {
		this.theta = theta;
	}
	public Double getVega() {
		return vega;
	}
	public void setVega(Double vega) {
		this.vega = vega;
	}
	
	
	
}
