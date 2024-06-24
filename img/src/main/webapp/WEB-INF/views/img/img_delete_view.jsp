<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>이미지 삭제</title>
</head>
<body>
	<script type="text/javascript">
		alert("입력하신 ${imgDTO.img_number}번의 이미지를 삭제하였습니다.")
		location.href = "./ImgSelect";
	</script>
</body>
</html>