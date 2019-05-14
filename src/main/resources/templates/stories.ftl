<html>
   <head>
      <title>Stories List</title>
      <link rel="stylesheet"
           type="text/css"
           href="/css/style.css"/>
   </head>
<body>
    <center> <h3>Hacker News Top Ten Stories</h3>
      <div>
         <table border="1">
            <tr>
               <th>Order</th>
               <th>Title</th>
            </tr>
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