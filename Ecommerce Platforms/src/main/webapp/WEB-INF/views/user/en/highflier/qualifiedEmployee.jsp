<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html lang="ko">
<head>
<title>인재정보 | 인재상 | 내담씨앤씨</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="viewport" content="initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, width=device-width, user-scalable=no">
<meta name="robots" content="index,nofollow">
<meta name="title" content="인재정보 | 인재상 | 내담씨앤씨">
<meta property="og:title" content="인재정보 | 인재상 | 내담씨앤씨">
<meta name="description" content="내담씨앤씨는 이러한 인재를 찾고 있습니다.">
<meta property="og:description" content="내담씨앤씨는 이러한 인재를 찾고 있습니다.">
<meta name="keywords" content="내담C&C, 내담씨앤씨, ndcnc, 내담, NDCNC, 내담씨앤씨 신입사원, 내담C&C 신입사원, 내담씨앤씨 인재상, 내담씨앤씨 인재, 내담C&C 인재상, 내담C&C 인재">
<meta property="og:url" content="https://ndcc.co.kr/user/highflier/qualifiedEmployee">

<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/user/css/employee.css">
<jsp:include page="/WEB-INF/views/user/common/script_css_js.jsp" />
</head>
<body>
<script type="text/javascript">

function over(num){
	let p = document.getElementById('p'+num);
	p.classList.remove("overP"+num);
}

function out(num){
	let p = document.getElementById('p'+num);
	p.classList.add("overP"+num);
}

</script>
<jsp:include page="/WEB-INF/views/user/common/header.jsp" />
	<div id="wrap qulified">
        <section id="container">
            <div id="contents" class="contents_top employee_contents">
                <div class="sub_title_area">
                    <div class="inner_container">
                        <h2 class="sub_title">Corporate Talent</h2>
                    </div>
                </div><!-- // .sub_title_area -->
				<div class="top_visual_area recruit">
                    <div class="inner_container">
                        <div class="top_txt_outer">
                            <div class="top_txt_area txt_notice">
                                <p class="top_txt2">
                                	Naedam C&C People - Cultivating the Most Competitive Talent
                                </p>
                                <p class="top_txt3">
                                    Naedam C&C is a company that values proactive and professional Talents, 
                                    and we look forward to your application to join us in shaping the future together.
                                </p>
                            </div>
                        </div>
                    </div>
                </div>                
            </div><!-- // #contents -->
                <div class="layout_section_outer layout_employee" data-js="anchor-target">
                    <div class="inner_container">
                        <div class="employee_layout_body">
                    		<div class="layout_section_outer recruit2">
                    			<div class="talent_section">
		                    		<div class="talentbox">
			                    		<p class="main_title">Talents that We Seek</p>
		    							<p class="talent_txt">Naedam C&C is a company that values proactive and professional Talents</p>                		
		                    		</div>
			                            <div class="culture_card_area">
			                                <ul class="culture_card_list common-layout_2 box-type02 is-vertical">
			                                   <li class="bg-type01 employee_li_items" onmouseover="over(1)" onmouseout="out(1)">
			                                    	<div class="employee_box">
				                                        <a href="#self" class="employee_image_box qualify_1">
				                                        	<div class="employee_box_contents">
					                                            <p class="culture_card_title">탁월함</p>
					                                            <p class="sub_txt">Excellence</p>
					                                            <p class="sub_txt2 overP1" id="p1">Talented individuals who pursue the best and strive for excellence</p>
					                                            <i class="icon culture-icon-01"></i>
					                                              <span class="icon card_ui-more employee_icon_white"></span>
					                                        </div>
				                                        </a>
			                                        </div>
			                                    </li>
			                                   <li class="bg-type02 employee_li_items" onmouseover="over(2)" onmouseout="out(2)">
			                                    	<div class="employee_box">
				                                        <a href="#self" class="employee_image_box qualify_2">
					                                       <div class="employee_box_contents" id="innovation">
					                                        	<p class="culture_card_title">혁신</p>
					                                            <p class="sub_txt">Innovation</p>
					                                            <p class="sub_txt2 overP2" id="p2">Talented individuals who constantly seek innovation</p>
					                                            <i class="icon culture-icon-02"></i>
					                                            <span class="icon card_ui-more employee_icon_white"></span>
				                                            </div>
				                                        </a>
				                                    </div>
			                                    </li>                                                              
			                                    <li class="bg-type03 employee_li_items" onmouseover="over(3)" onmouseout="out(3)">
				                                    <div class="employee_box">
				                                        <a href="#self" class="employee_image_box qualify_3">
					                                        <div class="employee_box_contents" id="fixation">
					                                            <p class="culture_card_title">통찰력</p>
					                                            <p class="sub_txt">Customer Fixation</p>
					                                            <p class="sub_txt2 overP3" id="p3">Talented individuals who anticipate and meet customer expectations and demands</p>
					                                            <i class="icon culture-icon-03"></i>
					                                            <span class="icon card_ui-more employee_icon_white"></span>
				                                            </div>
				                                        </a>
			                                        </div>
			                                    </li>
			                                   <li class="bg-type04 employee_li_items" onmouseover="over(4)" onmouseout="out(4)">
				                                    <div class="employee_box">
				                                        <a href="#self" class="employee_image_box qualify_4">
					                                        <div class="employee_box_contents" id="teamwork">
					                                            <p class="culture_card_title">협동심</p>
					                                            <p class="sub_txt">Teamwork</p>
					                                            <p class="sub_txt2 overP4" id="p4">Talented individuals who generate greater</p>
					                                            <i class="icon culture-icon-04"></i>
					                                            <span class="icon card_ui-more employee_icon_white"></span>
				                                            </div>
				                                        </a>
			                                        </div>
			                                    </li>
			                                   <li class="bg-type05 employee_li_items" onmouseover="over(5)" onmouseout="out(5)">
				                                    <div class="employee_box">
				                                        <a href="#self" class="employee_image_box qualify_5">
					                                        <div class="employee_box_contents" id="community">
					                                            <p class="culture_card_title">책임감</p>
					                                            <p class="sub_txt">Community</p>
					                                            <p class="sub_txt2 overP5" id="p5">Talented individuals who understand and fulfill the role and responsibility of the company</p>
					                                            <i class="icon culture-icon-04"></i>
					                                            <span class="icon card_ui-more employee_icon_white"></span>
				                                            </div>
				                                        </a>
			                                        </div>
			                                    </li>
			                                    <li class="bg-type06 employee_li_items" onmouseover="over(6)" onmouseout="out(6)">
				                                    <div class="employee_box">
				                                        <a href="#self" class="employee_image_box qualify_6">
				                                        	<div class="employee_box_contents" id="flexible">
					                                            <p class="culture_card_title">유연함</p>
					                                            <p class="sub_txt">Flexible</p>
					                                            <p class="sub_txt2 overP6" id="p6">Talented individuals who use humor and talent to create a happy workplace</p>
					                                            <i class="icon culture-icon-04"></i>
					                                            <span class="icon card_ui-more employee_icon_white"></span>
				                                            </div>
				                                        </a>
			                                        </div>
			                                    </li>
			                                </ul>
			                                </div>
		                                </div>
		                                <section class="contents_section">
											<div class="main_title">
												<span>Corporate Culture</span>
											</div>
											<p class="culture_txt">Naedam C&C is a company that pursues mutual growth between the company and employees.<p>
											<div class="company_culture">
			                                	<div class="culture_section">
			                                		<div class="company_culture_imgbox_left_01">
			                                		</div>
			                                		<div class="company_culture_box_right">
														<p class="culture_sub_title">Naedam C&C values trust and respect. </p>
														<span class="culture_txt">
															We value every opinion, freely expressing your own opinion for better results, and listening to the opinions of others even if they differ from your own.
															We focus on content rather than packaging in our communication style.
															We make efforts to convey a single sentence that contains the core message rather than using flashy PPTs.
															
														</span>
			                                		</div>
			                               		</div>
		                               		</div>
											<div class="company_culture2">
			                                	<div class="culture_section">
			                                		<div class="company_culture_imgbox_right_02">
			                                		</div>
			                                		<div class="company_culture_box_left">
														<p class="culture_sub_title">Naedam C&C spares no effort in nurturing talent.</p>
														<span class="culture_txt">
															We believe that the growth of employees and the growth of the company are interconnected. Therefore, Naedam C&C does not spare investment in education programs.
															We support the growth of employees through various programs such as in-house training, external training, job skill improvement training, and certification acquisition.
															If necessary equipment is required during work, the company provides support.
														</span>
			                                		</div>
			                               		</div>
			                               	</div>
											<div class="company_culture3">
			                                	<div class="culture_section end">
			                                		<div class="company_culture_imgbox_left_03">
			                                		</div>
			                                		<div class="company_culture_box_right">
														<p class="culture_sub_title">Naedam C&C provides clear processes for employees.</p>
														<span class="culture_txt">
															The time and effort of employees are valuable assets, and it requires significant concentration to focus on all processes. Therefore, Naedam C&C provides clear processes to allow employees to work efficiently.
															We provide clear processes to enable employees to work more efficiently, rather than focusing on all aspects of the work.
														</span>
			                                		</div>
			                               		</div>
			                                </div>
		                                </section>
	                        </div>
                        </div>
                    </div>
                </div>
        </section><!-- // #container -->
	</div>
</body>
<jsp:include page="/WEB-INF/views/user/common/en_footer.jsp" />
</html>