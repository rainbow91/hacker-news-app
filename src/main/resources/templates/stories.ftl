<html>
   <head>
      <title>Stories List</title>
      <link rel="stylesheet"
           type="text/css"
           href="/css/style.css"/>
   </head>
<body>
    <center> <h3>Hacker News Top Stories</h3><br>
      <div>
         <table>
<#--            <tr>-->
<#--               <th></th>-->
<#--               <th>Title</th>-->
<#--            </tr>-->
            <#list stories as storyentry>
            <tr>
               <td>${storyentry?counter}</td>
               <td><a href="${storyentry.url}"> ${storyentry.title}</a></td>
             </tr>
            </#list>
         </table>
      </div>
   </body>
   </center>
</html>