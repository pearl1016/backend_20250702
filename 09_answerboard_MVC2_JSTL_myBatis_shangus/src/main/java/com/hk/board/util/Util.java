package com.hk.board.util;

//Action Tag: <usebean/> -> 주로 DTO객체를 사용
//              <setproperty>: dto의 setter메서드호출
//              <getproperty>: dto의 getter메서드호출
public class Util {
	
	private String arrowNbsp;//공백+화살표이미지 문자열 저장

	public String getArrowNbsp() {
		return arrowNbsp;
	}

	// "&nbsp;&nbsp;&nbsp;<img src='img/arrow.png'/>"
	public void setArrowNbsp(String depth) {
		String nbsp="";//공백을 나타낼 문자열
		
		int depthInt=Integer.parseInt(depth);
		for (int i = 0; i < depthInt; i++) {
			nbsp+="&nbsp;&nbsp;&nbsp;&nbsp;";
		}
		this.arrowNbsp = 
	    nbsp+(depthInt>0?
	    	"<img src='img/arrow.png' width='15px' height='15px'/>":"");
	}
	
}