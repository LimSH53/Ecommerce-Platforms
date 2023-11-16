<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html lang="ko">
<head>
<title>사업소개 | 사업소개 | ${businessPost.businessPostTitle} | 내담씨앤씨</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport"
	content="initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, width=device-width, user-scalable=no">
<meta name="robots" content="index,nofollow">
<meta name="title" content="사업소개 | 사업소개 | ${businessPost.businessPostTitle} | 내담씨앤씨">
<meta property="og:title" content="사업소개 | 사업소개 | ${businessPost.businessPostTitle} | 내담씨앤씨">
<meta name="description" content="내담씨앤씨의 ${businessPost.businessPostTitle}을 소개합니다.">
<meta property="og:description" content="내담씨앤씨의 ${businessPost.businessPostTitle}을 소개합니다.">
<meta name="keywords" content="내담C&C, 내담씨앤씨, ndcnc, 내담, NDCNC, 내담씨앤씨 ${businessPost.businessPostTitle}, 내담C&C ${businessPost.businessPostTitle}, 내담 ${businessPost.businessPostTitle}">
<meta property="og:url" content="http://ndcc.co.kr/user/introduction//introduction?businessPostNo=${businessPost.businessPostNo}">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/user/css/company.css">
<jsp:include page="/WEB-INF/views/user/common/script_css_js.jsp" />
</head>
<body>
	<jsp:include page="/WEB-INF/views/user/common/header.jsp" />
	<div id="wrap" class="visual-center">
		<section id="container">
			<div id="contents" class="contents_top">
				<div class="sub_title_area">
					<div class="inner_container">
						<h2 class="sub_title">${businessPost.businessPostTitle}</h2>
					</div>
				</div>
				<!-- // .sub_title_area -->

				<div class="top_visual_area service_top_area type02">
					<div class="inner_container aos-init aos-animate"
						data-aos="fade-up" data-aos-offset="0" data-aos-duration="300"
						data-aos-delay="150" data-aos-easing="linear">
						<div class="top_txt_outer">
							<div class="top_txt_area">
								<span class="top_txt1" style="white-space: pre-line;">${businessPost.businessPostText2}</span>
								<p class="top_txt2">${businessPost.businessPostSubTitle}</p>
								<p class="top_txt3" style="white-space: pre-line;">${businessPost.businessPostText1}</p>
							</div>
						</div>
					</div>
				</div>
				<!-- // .top_visual_area -->

				<div class="layout_section_outer biz_difference is-gray"
					data-js="anchor-target">
					<div class="inner_container">
						<div class="layout_body">
							<ol class="difference_list">
								<c:forEach var="businessContents"
									items="${businessContentsList}" varStatus="status">
									<c:if test="${businessContents.businessContentsType eq '1'}">
										<c:set var="i" value="${ i+1 }" />
										<c:if test="${i%2 == 1}">
											<li>
										</c:if>
										<c:if test="${i%2 == 0}">
											<li class="is-right">
										</c:if>
										<div class="difference_img aos-init" data-aos="fade-up"
											data-aos-offset="0" data-aos-duration="250"
											data-aos-easing="linear">
											<img
												src="${pageContext.request.contextPath}/resources/user/images/introduction/${businessContents.image}"
												alt="">
										</div>
										<div class="difference_box aos-init" data-aos="fade-up"
											data-aos-offset="0" data-aos-duration="250"
											data-aos-easing="linear">
											<p class="difference_title" style="white-space: pre-line;">${businessContents.businessContentsTitle}</p>
											<p class="difference_desc" style="white-space: pre-line;">${businessContents.businessContentsContent}</p>
										</div>
										</li>
									</c:if>
								</c:forEach>
							</ol>
						</div>
					</div>
				</div>
				<!-- // .biz_difference -->
			</div>
			<!-- // #contents -->
			<div class="layout_section_outer card-ui type12"
				data-js="anchor-target">
				<div class="inner_container">
					<div class="layout_head">
						<p class="businessList_top_txt1">
							${businessPost.businessPostBottomTitle}</p>
						<p class="businessList_top_txt2">
							${businessPost.businessPostBottomContents}</p>
					</div>
					<div class="layout_body">
						<ul class="common-layout_3">
							<c:forEach var="businessContents" items="${businessContentsList}"
								varStatus="status">
								<c:if test="${businessContents.businessContentsType eq '2'}">
									<li class="items">
										<div class="image_box">
											<a href="#" onclick="return false;">
												<div class="img_cover">
													<img
														src="${pageContext.request.contextPath}/resources/user/images/introduction/${businessContents.image}">
												</div>
											</a>
										</div>
										<div class="desc_box">
											<p class="ui_category" onclick="return false;">${businessContents.businessContentsTitle }</p>
											<p class="ui_title">${businessContents.businessContentsContent}</p>
										</div>
									</li>
								</c:if>
							</c:forEach>
						</ul>
					</div>
				</div>
			</div>
		</section>
		<!-- // #container -->
	</div>
	<!-- // #wrap -->
	<jsp:include page="/WEB-INF/views/user/common/footer.jsp" />
</body>
</html>
