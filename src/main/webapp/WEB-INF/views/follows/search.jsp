<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="constants.ForwardConst" %>

<c:set var="action" value="${ForwardConst.ACT_FOL.getValue()}" />
<c:set var="commIdx" value="${ForwardConst.CMD_INDEX.getValue()}" />
<c:set var="commCrt" value="${ForwardConst.CMD_CREATE.getValue()}" />
<c:set var="actRep" value="${ForwardConst.ACT_REP.getValue()}" />
<c:set var="actFol" value="${ForwardConst.ACT_FOL.getValue()}" />
<c:set var="commsearch" value="${AttributeConst.CMD_SEARCH.getValue()}" />
<c:set var="commfolsu" value="${AttributeConst.FOL_FOLLOWED_SUCCESS.getValue()}" />
<c:set var="commfolou" value="${AttributeConst.FOL_FOLLOWED_OUT.getValue()}" />
<c:set var="commTop" value="${AttributeConst.FOL_TOP.getValue()}" />

<c:import url="/WEB-INF/views/layout/app.jsp">
    <c:param name="content">
        <h2>社員一覧</h2>
        <c:if test="${flush != null}">
            <div id="flush_success">
                <c:out value="${flush}"></c:out>
            </div>
        </c:if>
               <!--  <form method="POST" action="<c:url value='/?action=${action}&command=${commsearch}' />">
                    <c:import url="search.jsp" />
                </form>-->
        <table id="follow_list">
            <tbody>
                <tr>
                    <th class="follow_number">社員番号</th>
                    <th class="follow_name">氏名</th>
                    <th class="follow_action">操作</th>
                </tr>
                <c:forEach var="follow" items="${follows}" varStatus="status">
                    <tr class="row${status.count % 2}">
                        <td class="follow_number"><c:out value="${follow.employee.code}" /></td>
                        <td class="follow_name"><c:out value="${follow.emoloyee.name}"/></td>
                        <td class="reoport_action"><a href="<c:url value='?action=${actRep}&command={commshow}&id=${follow.id}' />">日報一覧へ</a></td>
                        <c:if test="${sessionScope.follow.id == follower.id}">
                            <td class="follow_action"><a href="<c:url value='?action=&{actFol}&command={commfolou}' />">フォローを外す</a></td>
                        </c:if>
                        <c:if test="${sessionScope.follow.id != follower.id}">
                            <td class="follow_action"><a href="<c:url value='?action=&{actFol}&command={commfolsu}' />">フォローする</a></td>
                        </c:if>
                </c:forEach>
            </tbody>
        </table>

        <div id="pagination">
            (全 ${follows_count} 件）<br />
            <c:forEach var="i" begin="1" end="${((follows_count - 1) / maxRow) + 1}" step="1">
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
        <p><a href="<c:url value='?action=${actFol}&command=${commTop}' />">トップに戻る</a></p>
    </c:param>
</c:import>