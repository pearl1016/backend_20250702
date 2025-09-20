/**
 * cookieFunc.js
 */
// setCookie()함수 구현
	function setCookie(name,value,expires,domain,path,secure){
		let cookies="";
		
		//인코딩 처리3가지
		//escape() : ASCII코드로 변환, 나머지 유니코드 변환 -> 이제 안씀
		//encodeURI() : 주소전체를 인코딩할때 사용
		//              일반 문자는 인코딩처리, 주소와 관련된 특수문자는 제외
		//              예) https://www.hankyung.com/user?id=hk&name=han
		//encodeURIComponent():모든 문자/특수문자 등등을 인코딩 처리함
		//                     파라미터에 대한 내용도 모두 처리 가능
		
		cookies+=name+"="+encodeURIComponent(value);//인코딩처리해서 저장
 		
		const date=new Date();
		date.setDate(date.getDate()+expires);//2025.08.29 + 5 -> 09.03까지
		cookies+=";expires="+date.toUTCString();//유효기간 설정(세계표준시)
	//  cookies+=";max_age="+(1000*1*60*60*24);// ms단위로 설정
		
		if(domain){
			cookies+=";domain="+domain;
		}
		if(path){
			cookies+=";path="+path;
		}
		if(secure){
			cookies+=";secure="+secure;
		}
	
		document.cookie=cookies;//쿠키 저장하기
	}
	
 	//getCookie 기능 구현
 	function getCookie(cookieName){
 		//cookie="user=hk;name=han;admin=kkk" -> 문자열 추출
 		//  ;    =  -> split(): 반환타입배열 , indexOf()
 		// split(";") -> [?] -> cookieName검색 -> "key=value" -> split("=")
 		
 		const cookieValue=document.cookie;// "k=v;k=v;.."
 		console.log(cookieValue);
 		const cookieSplit=cookieValue.split(";");
 		console.log(cookieSplit);
 		let value="";
//  		console.log(cookieSplit.indexOf(cookieName));
 		 	// ["user3=hk3","user1=hk1"]
 		for (var i = 0; i < cookieSplit.length; i++) {
			if(cookieSplit[i].indexOf(cookieName)!=-1){
				value=cookieSplit[i].split("=")[1];//["user3","hk3"]
				console.log("저장된 쿠키값:",value);
				return decodeURIComponent(value);
			}
		}
 		return null;
 	}
 	
 	//cookie 삭제 기능: expires 값을 재설정
 	function removeCookie(name){
 		const date=new Date();
 		date.setDate(date.getDate()-1);// 유효기간 지남
 		document.cookie=name+"=;expires="+date.toUTCString();
//  		document.cookie=name+"=;max-age=0";
 	}