<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> <%@ taglib prefix="c"
uri="http://java.sun.com/jsp/jstl/core"%> <%@ taglib prefix="fmt"
uri="http://java.sun.com/jsp/jstl/fmt"%>
<html lang="ko">
<head>
<title>내담씨앤씨</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<jsp:include page="/WEB-INF/views/user/common/script_css_js.jsp" />
    <script>
      // url '+, &' 인코딩 처리
      function hashtagLink(url, param) {
        var encParam = param.replace(/&/g, "%26").replace(/\+/g, "%2B");
        location.href = url + encParam;
      }
    </script>
    <script>
      var business_num = "";
      var businessDetail_num = "";
    </script>
</head>
  <body>
  <jsp:include page="/WEB-INF/views/user/common/header.jsp" />
  
  
  
  <jsp:include page="/WEB-INF/views/user/common/footer.jsp" />	
  </body>
</html>
