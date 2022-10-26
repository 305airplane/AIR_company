 window.onload=function (){
    document.getElementById("btn").onclick=function () {
        displayuser();
    }
}
function displayuser(){
    var xhr=new XMLHttpRequest();
    xhr.onreadystatechange=function(){
        if(this.readyState==4){
            if(this.status==200){
                console.log(this.responseText)
                var userlist=JSON.parse(this.responseText);

                 var html="<table border='1px'><tr><th>用户名</th><th>密码</th></tr>";
                for(var i=0;i<userlist.length;i++)
                {
                   var user=userlist[i];
                    html+="<tr>";
                    html+="<td>"+user.用户名+"</td>";
                    html+="<td>"+user.密码+"</td>";
                    html+="</tr>";
                 }
                 html+="</table>"
                document.getElementById("username").innerHTML=html;
            }
            else{
                alert(this.status);
            }
        }
    }
    xhr.open("post","/displayusertable",true)
    xhr.send();
}