<#assign wp=JspTaglibs["/aps-core"]>
<#if (Session.currentUser.username != "guest") >
    <script crossorigin src="https://unpkg.com/react@16/umd/react.development.js"></script>
    <script crossorigin src="https://unpkg.com/react-dom@16/umd/react-dom.development.js"></script>
    <link href="<@wp.info key="systemParam" paramName="applicationBaseURL" />resources/entando-survey/static/css/2.chunk.css" rel="stylesheet">
    <link href="<@wp.info key="systemParam" paramName="applicationBaseURL" />resources/entando-survey/static/css/main.chunk.css" rel="stylesheet">
    <script src="<@wp.info key="systemParam" paramName="applicationBaseURL" />resources/entando-survey/static/js/runtime~main.js"></script>
    <script src="<@wp.info key="systemParam" paramName="applicationBaseURL" />resources/entando-survey/static/js/2.chunk.js"></script>
    <script src="<@wp.info key="systemParam" paramName="applicationBaseURL" />resources/entando-survey/static/js/main.chunk.js"></script>

    <en-survey service-url="{{insert service url}}" survey-id="{{ inser survey id }}"/>
<#else>
    You have to be logged in to fill the survey
</#if>

