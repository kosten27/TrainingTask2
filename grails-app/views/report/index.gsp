<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
</head>
<body>
<a href="#list-post" class="skip" tabindex="-1"><g:message code="default.link.skip.label"
                                                           default="Skip to content&hellip;"/></a>
<div class="nav" role="navigation">
    <ul>
        <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
    </ul>
</div>
<g:jasperReport
        controller="report"
        action="create"
        inline="true"
        jasper="report.jrxml"
        format="PDF,CSV,XLSX,DOCX"
        name="Create report"/>
</body>
</html>