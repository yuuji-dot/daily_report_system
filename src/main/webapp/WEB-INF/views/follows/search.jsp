<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="constants.ForwardConst" %>

<c:set var="action" value="${ForwardConst.ACT_FOL.getValue()}" />
<c:set var="commIdx" value="${ForwardConst.CMD_INDEX.getValue()}" />
<c:set var="commCrt" value="${ForwardConst.CMD_CREATE.getValue()}" />
<c:set var="actRep" value="${ForwardConst.ACT_REP.getValue()}" />
<c:set var="actFol" value="${ForwardConst.ACT_FOL.getValue()}" />
<c:set var="commsearch" value="${ForwardConst.CMD_SEARCH.getValue()}" />
<c:set var="actEmp" value="${ForwardConst.ACT_EMP.getValue()}" />
<c:set var="commDel" value="${ForwardConst.CMD_DESTROY.getValue()}" />

<c:import url="/WEB-INF/views/layout/app.jsp">
    <c:param name="content">
        <c:if test="${flush != null}">
            <div id="flush_success">
                <c:out value="${flush}"></c:out>
            </div>
        </c:if>
        <h2>社員一覧</h2>
        <table id="emplyee_list">
            <tbody>
                <tr>
                    <th class="follow_number">社員番号</th>
                    <th class="follow_name">氏名</th>
                    <th class="follow_action">操作</th>
                </tr>
                <c:forEach var="employee" items="${employees}" varStatus="status">
                    <tr class="row${status.count % 2}">
                        <td class="follow_number"><c:out value="${employee.code}" /></td>
                        <td class="follow_name"><c:out value="${employee.name}" /></td>
                        <c:choose>
                        <c:when test="${sessionScope.login_employee.id == employee.id}">
                            <td class="follow_action"><a href="<c:url value='?action=${actFol}&command=${commCrt}&id=${employee.id}' />">フォローできない</a></td>
                        </c:when>
                        <c:otherwise>
                            <td class="follow_action"><a href="<c:url value='?action=${actFol}&command=${commCrt}&id=${employee.id}' />">フォローする</a></td>
                        </c:otherwise>
                        </c:choose>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

        <div id="pagination">

            <c:forEach var="i" begin="1" end="${((employees_count - 1) / maxRow) + 1}" step="1">
                <c:choose>
                    <c:when test="${i == page}">
                        <c:out value="${i}" />&nbsp;
                    </c:when>
                    <c:otherwise>
                        <a href="<c:url value='?action=${actFol}&command=${commIdx}&page=${i}' />"><c:out value="${i}" /></a>&nbsp;
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </div>
        <p><a href="<c:url value='?action=${actFol}&command=${commIdx}' />">トップに戻る</a></p>
    </c:param>
</c:import>