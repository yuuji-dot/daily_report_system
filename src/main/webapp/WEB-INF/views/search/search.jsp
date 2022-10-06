<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<label for="${AttributeConst.EMP_NAME.getValue()}">氏名</label><br />
<input type="text" name="${AttributeConst.EMP_NAME.getValue()}" id="${AttributeConst.EMP_NAME.getValue()}" value="${name}" />
<br /><br />
<input type="hidden" name="${AttributeConst.TOKEN.getValue()}" value="${_token}" />
<button type="submit">検索</button>

<label for="${AttributeConst.EMP_CODE.getValue()}">社員番号</label><br />
<input type="text" name="${AttributeConst.EMP_CODE.getValue()}" id="${AttributeConst.EMP_CODE.getValue()}" value="${code}" />
<br /><br />
<input type="hidden" name="${AttributeConst.TOKEN.getValue()}" value="${_token}" />
<button type="submit">検索</button>