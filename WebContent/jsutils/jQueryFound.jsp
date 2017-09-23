<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script type="text/javascript">
	$(function(){
		/* 给所有的  A 标签 都加上点击事件。加上条件的查询。 */
		$("a").each(function(){
			this.onclick = function(){
				var serializeVal = $(":hidden").serialize();
				var href = this.href + "&" + serializeVal;
				window.location.href = href;
				return false;
			};
		});
	});
</script>
<input type="hidden" name="minPrice" value="${param.minPrice }"/>
<input type="hidden" name="maxPrice" value="${param.maxPrice }"/>