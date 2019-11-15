<%-- 
    Document   : standardScript
    Created on : Oct 19, 2019, 6:40:57 PM
    Author     : admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<script>
    let goToTop = document.getElementById('go-to-top');
    let header = document.getElementsByTagName('header')[0];
    let logo = document.getElementsByClassName('logo')[0];

    let isInViewport = function (element) {

        let bounding = element.getBoundingClientRect();
        if (
                bounding.top >= 0 &&
                bounding.left >= 0 &&
                bounding.right <= (window.innerWidth || document.documentElement.clientWidth) &&
                bounding.bottom <= (window.innerHeight || document.documentElement.clientHeight)
                ) {
            return true;
        }
        return false;
    }

    window.onscroll = function () {
        goToTop.style.transition = 'opacity 1s';
        if (window.innerWidth > 768) {
            if (isInViewport(header)) {
                goToTop.style.opacity = 0;
            } else
                goToTop.style.opacity = 1;
        }else{
            if (isInViewport(document.getElementsByClassName("show-image")[0])) {
                console.log("abc");
                goToTop.style.opacity = 0;
            } else
                goToTop.style.opacity = 1;
        }
    };

    window.onresize = function () {
        if (window.innerWidth <= 768) {
            logo.src = '${pageContext.request.contextPath}/resources/images/landingPage/favicon.png';
        } else {
            logo.src = '${pageContext.request.contextPath}/resources/images/landingPage/logo.jpg'
        }
    };

//create go-to-top button
    if (isInViewport(header)) {
        goToTop.style.opacity = 0;
    }
    goToTop.innerHTML = '<a href="#"><img src="${pageContext.request.contextPath}/resources/images/landingPage/up_arrow_icon.svg" alt="up_arrow_icon"></a>';

// Set default logo
    if (window.innerWidth <= 768) {
        logo.src = '${pageContext.request.contextPath}/resources/images/landingPage/favicon.png';
    } else {
        logo.src = '${pageContext.request.contextPath}/resources/images/landingPage/logo.jpg'
    }
    
    function displayMenu(id) {
        console.log(id);
        document.getElementById(id).style.display = 'flex';
        document.getElementById(id).onclick = function (e) {
            if (e.target == this)
                document.getElementById(id).style.display = 'none';
        }
    }
</script>
