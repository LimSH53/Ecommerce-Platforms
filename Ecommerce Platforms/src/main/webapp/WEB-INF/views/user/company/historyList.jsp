<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html lang="ko">
<head>
<title>내담씨앤씨</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta property="og:url" content="https://www.jhta.co.kr">

<!-- include  -->
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/user/css/history.css">
<jsp:include page="/WEB-INF/views/user/common/script_css_js.jsp" />
<!-- include end  -->
<!-- 사용자 정의 끝 -->
</head>
<style>
.content {
	padding-top: 40px;
	padding-bottom: 40px;
	width: 1170px;w
	margin-right: auto;
	margin-left: auto;
	padding-left: 15px;
	padding-right: 15px;
}
</style>
<script type="text/javascript">
	/* 연혁 처음 값에 class 추가 */
	$(document).ready(function() {
		$('ol').children('li:eq(0)').addClass("first");
	})
</script>
<body>
	<!-- haeder -->
	<jsp:include page="/WEB-INF/views/user/common/header.jsp" />
	<!-- haeder -->
	<div class="history" id="wrap">
		<input type="hidden" class="scrollYN" value="N">
		<section id="container">
			<div id="contents" class="contents_top">
				<div class="sub_title_area history_sub_title_area">
					<div class="common-swiper_area sub_history_area_top">
						<div class="inner_container">
							<h2 class="sub_title sub_title_area">연혁</h2>
							<div class="layout_head">
								<p class="top_txt1 service_txt1 history_txt1">(주)내담C&C의 연혁을
									알려드립니다.</p>
							</div>
							<div class="common-swiper_tab">
								<ul class="swiper-wrapper history_swiper">
									<li class="swiper-slide active" ><a href="javascript:void(0);" id="cont1" class="is-link">회사연혁</a></li>
									<li class="swiper-slide"><a href="javascript:void(0);" id="cont2" class="is-link">수상내역</a>
									</li>
								</ul>
							</div>
						</div>
					</div>
				</div>
				<!-- 서브 타이틀 -->
				<div class="common-swiper_area2 history_swiper_area2"
					style="width: none;">
					<div class="inner_container">
						<div class="common-swiper_tab">
							<ul>
								<div class="layout_section_outer history_sec">
									<div>
										<div class="tab-cont-wrap">
											<div class="tab-cont active" id="tab-cont1">
												<ol>
													<c:forEach var="year" items="${years}">
														<li>
															<h3 class="history_years">
																<c:out value="${year.year}" />
															</h3>
															<dl>
																<c:forEach var="month" items="${months}">
																	<c:if test="${month.year == year.year}">
																		<!-- 해당하는 연혁 -->
																		<c:forEach var="content" items="${history}">
																			<c:if
																				test="${(month.month == content.month)&&(month.year == content.year)}">
																				<dd>
																					<c:out value="${content.content}" />
																				</dd>
																			</c:if>
																		</c:forEach>
																	</c:if>
																</c:forEach>
															</dl>
														</li>
													</c:forEach>
												</ol>
											</div>
											</div>
											<div class="award_sec">
											<div class="tab-cont" id="tab-cont2">
												<ol>
													<c:forEach var="awardyear" items="${awardYears}">
														<li>
															<h3 class="history_years">
																<c:out value="${awardyear.year}" />
															</h3>
															<dl>
																<c:forEach var="awardmonth" items="${awardMonths}">
																	<c:if test="${awardmonth.year == awardyear.year}">
																		<!-- 해당하는 연혁 -->
																		<c:forEach var="awardcontent" items="${award}">
																			<c:if
																				test="${(awardmonth.month == awardcontent.month)&&(awardmonth.year == awardcontent.year)}">
																				<dd>
																					<span class=thumnail>
																						<c:if test="${!empty awardcontent.imgUrl}">
																							<img class="img-thumbnail regist_thumbnail"  src="${pageContext.request.contextPath}/resources/user/images/company/award/${awardcontent.imgUrl}">
																						</c:if>
																					</span>
																					<span class="title">
																						<c:out value="'${awardcontent.host} 주최'" />
																						<br>
																						<c:out value="${awardcontent.content}" />
																					</span>																				</dd>
																			</c:if>
																		</c:forEach>
																	</c:if>
																</c:forEach>
															</dl>
														</li>
													</c:forEach>
												</ol>
											</div>
										</div>
									</div>
								</div>
							</ul>
						</div>
					</div>
				</div>
			</div>
		</section>
		<!-- // #container -->
	</div>

	<!-- footer -->
	<jsp:include page="/WEB-INF/views/user/common/footer.jsp" />
	<!-- footer -->
	<script>
	
		$('#cont1').click(function() {
			$('#cont2').parent().removeClass('active');
			$('#cont1').parent().addClass('active');
			$('#tab-cont2').removeClass('active');
			$('#tab-cont1').addClass('active');
		})
			
		$('#cont2').click(function() {
			$('#cont1').parent().removeClass('active');
			$('#cont2').parent().addClass('active');
			$('#tab-cont1').removeClass('active');
			$('#tab-cont2').addClass('active');
		})
		
	</script>
</body>
</html>