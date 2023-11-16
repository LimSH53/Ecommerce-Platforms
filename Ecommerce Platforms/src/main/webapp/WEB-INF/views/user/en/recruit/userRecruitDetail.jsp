<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html lang="ko">
<head>
<title>내담씨앤씨</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, width=device-width, user-scalable=no">
<jsp:include page="/WEB-INF/views/user/common/script_css_js.jsp" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/user/css/recruitDetail.css">
</head>
<body>
<jsp:include page="/WEB-INF/views/user/common/header.jsp" />
	<div id="contents" style="padding-top: 47px;">
		<div class="area_sub_title">
		    <div class="mod_title mod_title_type">
		    	<h3 class="title">Job posting</h3>
			</div>
		</div>
		<!-- area_visual -->
		<div class="area_visual visual_bg recruit_detail">
			<p class="poster">
				
			</p>
			<div class="inner_wrap">
				<div class="copy_txt">
					<div class="copy_header">
						<div class="inner_copy_header">
							<!-- 제목 -->
							<p>${recruitData.recruitTitle}</p>
							<div>
								<strong class="recruit_status">
									<span class="closing">
									<c:choose>
										<c:when test="${recruitData.contentsStatus eq 'Y'}">hiring</c:when>
										<c:when test="${recruitData.contentsStatus eq 'R'}">Ongoing recruitment</c:when>
									</c:choose>
									</span>
								</strong>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<!-- 상세 body -->
		<div calss="detail_body">
			<div class="mod_tb tb_detail">
				<div class="recruit_detail">
					<dl class="tb_wrap">
						<dt>Job Information</dt>
						<dd>
							<table>
								<colgroup>
									<col style="width: 141px">
									<col style="width: *">
								</colgroup>
								<tbody>
									<tr>
										<th scope="row">Job Title</th>
										<td>
											${recruitData.jobTitle}
										</td>
									</tr>
									<tr>
										<th scope="row">Employment Type</th>
										<td scope="row" rowspan="1">${recruitData.recruitType}</td>
									</tr>
									<tr>
										<th scope="row">Work Experience</th>
										<c:choose>
											<c:when test="${recruitData.career == '경력'}">
												<td>
												work Experience
												</td>
											</c:when>
											<c:when test="${recruitData.career == '신입'}">
												<td>
												Newcomer
												</td>
											</c:when>
											<c:otherwise>
												<td>
												irrelevant
												</td>
											</c:otherwise>
										</c:choose>
									</tr>
									<tr>
										<th scope="row">Work Location</th>
										<td>
											${recruitData.recruitPlace}
										</td>
									</tr>
									<tr>
										<th scope="row">Application deadline</th>
										<c:choose>
											<c:when test="${recruitData.recruitEnd != null}">
												<td scope="row" rowspan="1">
												${recruitData.recruitEnd}
												</td>
											</c:when>
											<c:otherwise>
												<td scope="row" rowspan="1">
												Ongoing recruitment
												</td>
											</c:otherwise>
										</c:choose>
										
									</tr>
									<tr>
										<th scope="row">Hiring Manager</th>
										<td>
											<p>${recruitData.recruitManager}</p>
										</td>
									</tr>
								</tbody>
							</table>
						</dd>
					</dl>
					<dl>
						<dt style="font-weight: bold;">Job Description</dt>
						<dd>
							<p>${recruitData.jobIntro}</p>
						</dd>
					</dl>
					<dl>
						<dt style="font-weight: bold;">Qualifications</dt>
						<dd>
							<p>${recruitData.qualification}</p>
						</dd>
					</dl>
					<!--S: 추가내용들 반복 -->
					<c:if test="${contents != null}">
					<c:forEach var="contnets" items="${contents}">
						<dl>
							<dt style="font-weight: bold;">${contnets.recruitSubTitle}</dt>
							<dd>
								<p>${contnets.recruitContents}</p>
							</dd>
						</dl>
					</c:forEach>
					</c:if>
					<!--E: 추가내용들 반복 -->
					<c:if test="${recruitData.orgFileName != null}">
					<dl>
						<dt style="font-weight: bold;">Upload File</dt>
						<dd>
							<a href="/user/recruit/fileDownload/${recruitData.recruitNo}">
								${recruitData.orgFileName}
							</a>
						</dd>
					</dl>
					</c:if>
				</div>
					<div class="btn_area">
						<a href="/user/recruit/userRecruitList?locale=${cookie.locale.value}">
							<span class="back_btn">List</span>
						</a>
					</div>
			</div>
		</div>
		<!-- // 상세 body -->
	</div>
	<!-- // 상세 wrap -->
	<jsp:include page="/WEB-INF/views/user/common/en_footer.jsp" />
<script type="text/javascript">

</script>

</body>
</html>