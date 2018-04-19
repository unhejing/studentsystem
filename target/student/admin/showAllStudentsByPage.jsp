<%--
  Created by IntelliJ IDEA.
  User: jingyujie
  Date: 2018/4/16
  Time: 上午11:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored ="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
    <meta name="renderer" content="webkit">
    <title></title>
    <link rel="stylesheet" href="admin/css/pintuer.css">
    <link rel="stylesheet" href="admin/css/admin.css">
    <script src="admin/js/jquery.js"></script>
    <script src="admin/js/pintuer.js"></script>
</head>
<body>
<form method="post" action="" id="listform">
    <div class="panel admin-panel">

        <div class="panel-head"><strong class="icon-reorder"> 学生列表</strong> <a href="" style="float:right; display:none;">添加字段</a></div>
        <div class="padding border-bottom">
            <ul class="search" style="padding-left:10px;">
                <li> <a class="button border-main icon-plus-square-o" href="admin/addStudent.jsp"> 添加内容</a> </li>
            </ul>
        </div>
        <table class="table table-hover text-center">
            <tr>
                <th width="100" style="text-align:left; padding-left:20px;">ID</th>
                <th>姓名</th>
                <th>生日</th>
                <th>平均分</th>
                <th>备注</th>
                <th width="310">操作</th>
            </tr>
            <volist name="list" id="vo">
                <c:forEach items="${studentList}" var="student">
                    <tr>
                        <td style="text-align:left; padding-left:20px;">${student.id}</td>
                        <td>${student.name}</td>
                        <td>${student.birthday}
                        </td>
                        <td>${student.avgscore}</td>
                        <td>${student.description}</td>
                        <td><div class="button-group"> <a class="button border-main" href="${pageContext.request.contextPath}/editStudent?id=${student.id}"><span class="icon-edit"></span> 修改</a> <a class="button border-red" href="javascript:void(0)" onclick="return del('${student.id}')"><span class="icon-trash-o"></span> 删除</a> </div></td>
                    </tr>
                </c:forEach>


                    <%
                        long num = (long) request.getAttribute("num");
                        num = (long) Math.floor(num/10)+1;
                        int current = (Integer) request.getAttribute("pageNum");
                        int last = (int)num;
                     %>
                    <tr>
                        <td colspan="8">
                            <div class="pagelist">
                                <%if(current-1!=0){ %>
                                <a href="${pageContext.request.contextPath}/studentlist?showPage=<%=current-1 %>">上一页</a>
                                <%} %>
                                <%for(int i=current-2; i <= current+2; i++){ %>
                                <%if(i>0 && i<=last){ %>
                                <%if(current==i){ %>
                                <span class="current"><%=i %></span>
                                <%}else{ %>
                                <a href="${pageContext.request.contextPath}/studentlist?showPage=<%=i %>"><%=i %></a></li>
                                <%} %>
                                <%} %>
                                <%} %>

                                <%if(current+1 <= last){ %>
                                <a href="${pageContext.request.contextPath}/studentlist?showPage=<%=current+1 %>">下一页</a>
                                <%} %>
                                <%if(last>1){ %>
                                <a href="${pageContext.request.contextPath}/studentlist?showPage=<%=last %>">尾页</a>
                                <%} %>
                            </div>
                        </td>
                    </tr>
        </table>
    </div>
</form>
<script type="text/javascript">

    //搜索
    function changesearch(){

    }

    //单个删除
    function del(id){
        if(confirm("您确定要删除吗?")){
            window.location.href = "${pageContext.request.contextPath}/deleteStudent?id="+id;
        }
    }

    //全选
    $("#checkall").click(function(){
        $("input[name='id[]']").each(function(){
            if (this.checked) {
                this.checked = false;
            }
            else {
                this.checked = true;
            }
        });
    })

    //批量删除
    function DelSelect(){
        var Checkbox=false;
        $("input[name='id[]']").each(function(){
            if (this.checked==true) {
                Checkbox=true;
            }
        });
        if (Checkbox){
            var t=confirm("您确认要删除选中的内容吗？");
            if (t==false) return false;
            $("#listform").submit();
        }
        else{
            alert("请选择您要删除的内容!");
            return false;
        }
    }

    //批量排序
    function sorts(){
        var Checkbox=false;
        $("input[name='id[]']").each(function(){
            if (this.checked==true) {
                Checkbox=true;
            }
        });
        if (Checkbox){

            $("#listform").submit();
        }
        else{
            alert("请选择要操作的内容!");
            return false;
        }
    }


    //批量首页显示
    function changeishome(o){
        var Checkbox=false;
        $("input[name='id[]']").each(function(){
            if (this.checked==true) {
                Checkbox=true;
            }
        });
        if (Checkbox){

            $("#listform").submit();
        }
        else{
            alert("请选择要操作的内容!");

            return false;
        }
    }

    //批量推荐
    function changeisvouch(o){
        var Checkbox=false;
        $("input[name='id[]']").each(function(){
            if (this.checked==true) {
                Checkbox=true;
            }
        });
        if (Checkbox){


            $("#listform").submit();
        }
        else{
            alert("请选择要操作的内容!");

            return false;
        }
    }

    //批量置顶
    function changeistop(o){
        var Checkbox=false;
        $("input[name='id[]']").each(function(){
            if (this.checked==true) {
                Checkbox=true;
            }
        });
        if (Checkbox){

            $("#listform").submit();
        }
        else{
            alert("请选择要操作的内容!");

            return false;
        }
    }


    //批量移动
    function changecate(o){
        var Checkbox=false;
        $("input[name='id[]']").each(function(){
            if (this.checked==true) {
                Checkbox=true;
            }
        });
        if (Checkbox){

            $("#listform").submit();
        }
        else{
            alert("请选择要操作的内容!");

            return false;
        }
    }

    //批量复制
    function changecopy(o){
        var Checkbox=false;
        $("input[name='id[]']").each(function(){
            if (this.checked==true) {
                Checkbox=true;
            }
        });
        if (Checkbox){
            var i = 0;
            $("input[name='id[]']").each(function(){
                if (this.checked==true) {
                    i++;
                }
            });
            if(i>1){
                alert("只能选择一条信息!");
                $(o).find("option:first").prop("selected","selected");
            }else{

                $("#listform").submit();
            }
        }
        else{
            alert("请选择要复制的内容!");
            $(o).find("option:first").prop("selected","selected");
            return false;
        }
    }

</script>
</body>
</html>