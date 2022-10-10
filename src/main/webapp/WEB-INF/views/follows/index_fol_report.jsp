<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="constants.ForwardConst" %>

<c:set var="action" value="${ForwardConst.ACT_FOL.getValue()}" />
<c:set var="commIdx" value="${ForwardConst.CMD_INDEX.getValue()}" />
<c:set var="commCrt" value="${ForwardConst.CMD_CREATE.getValue()}" />

<!-- 指定した社員の日報一覧を表示する -->
<c:import url="/WEB-INF/views/layout/app.jsp">
    <c:param name="content">
        <h2>社員番号○○　氏名　××さんの日報一覧</h2><!-- 指定した社員の社員番号と氏名を表示するように変更 -->
        <table>
            <tbody>
                <tr>
                    <th>日付</th>
                    <fmt:parseDate value="${report.reportDate}" pattern="yyyy-MM-dd" var="reportDay" type="date" />
                    <td><fmt:formatDate value='${reportDay}' pattern='yyyy-MM-dd' /></td>
                </tr>
                <tr>
                    <th>氏名</th>
                    <td><c:out value="${follow.employee.name}" /></td>
                </tr>
                <tr>
                    <th>操作</th>
                    <td class="report_action"><a href="<c:url value='?action=${actRep}&command=${commShow}&id=${report.id}' />">詳細を見る</a></td>
                </tr>
            </tbody>
        </table>
    </c:param>

    <p><a href="<c:url value='?action=${action}&command=${commIdx}' />">フォロー社員一覧に戻る</a></p>
</c:import>