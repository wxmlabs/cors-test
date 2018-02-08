<%@ page import="com.wxmlabs.test.cors.ConfigHolder" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>CORS Test Server</title>
</head>
<body>
<div>
    <h1>CORS Config</h1>
    api.rest.cors.enable=<%=ConfigHolder.isEnableCORS()%><br>
    api.rest.cors.allow.origin.pattern=<%=ConfigHolder.getAllowOriginPattern()%><br>
    api.rest.cors.allow.credentials=<%=ConfigHolder.getAllowCredentials()%><br>
    api.rest.cors.allow.headers=<%=ConfigHolder.getAllowHeaders()%><br>
    api.rest.cors.allow.methods=<%=ConfigHolder.getAllowMethods()%><br>
    api.rest.cors.max.age=<%=ConfigHolder.getMaxAge()%><br>
</div>
</body>
</html>
