<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>이미지 등록</title>
<link rel="stylesheet" type="text/css" href="./css/bootstrap.min_4.5.0.css">
<link rel="stylesheet" type="text/css" href="./css/global.css">
<script src="./js/jquery-3.5.1.min.js" type="text/javascript"></script>
<script src="./js/bootstrap.min_4.5.0.js" type="text/javascript"></script>
<script src="./js/jquery.validate.min.js" type="text/javascript"></script>
<script src="./js/dept_validity.js" type="text/javascript"></script>
</head>
<body>
	<header id="main-header" class="py-2 btn-dark text-white">
		<div class="container">
			<div class="row">
				<div class="col-md-6">
					<h1>이미지 등록</h1>
				</div>
			</div>
		</div>
	</header>
	<section id="actions" class="py-4 mb-4 bg-light"></section>
	<section id="details">
		<div class="container">
			<div class="row">
				<div class="col-md-12">
					<div class="card">
						<div class="card-header">
							<h5>이미지 등록</h5>
						</div>
						<div class="card-body">
							<form method="post" id="sign_img" enctype="multipart/form-data" id="signupForm" onsubmit="return validateForm()">
								<fieldset>
									<div class="form-group row">
										<label for="fileName" class="ml-sm-4 col-form-label">이미지를 선택해주세요. </label>
										<div class="ml-sm-3">
											<!-- 파일 선택 버튼의 스타일 변경 -->
											<input type="file" name="fileName" id="fileName" class="form-control-file" style="visibility: hidden;" onchange="updateFileLabel(this)"> 
											<label class="btn btn-primary" for="fileName">이미지 선택</label> 
											<span id="fileLabel">선택된 이미지가 없습니다.</span>
											<!-- 이미지를 표시할 태그 -->
											<img src="" id="previewImage" style="display: none; max-width: 200px; max-height: 200px;">
										</div>
									</div>
									<!-- 이미지 등록 -->
									<div class="form-group row">
										<div class="ml-sm-3">
											<input type="hidden" name="img_upload" id="img_upload" class="form-control-file">
											<script type="text/javascript">
												document.getElementById('img_upload').value = new Date().toISOString().split('T')[0];
											</script>
										</div>
									</div>
									<!-- 이미지 수정 -->
									<div class="form-group row">
		                                <div class="ml-sm-3">
		                                    <input type="hidden" name="img_update" id="img_update" class="form-control-file">
		                                    <script type="text/javascript">
		                                        document.getElementById('img_update').value = new Date().toISOString().split('T')[0];
		                                    </script>
		                                </div>
	                           		 </div>
	                           		 <!-- 이미지 경로 -->
									<div class="form-group row">
		                                <div class="ml-sm-3">
		                                    <input type="hidden" name="img_url" id="img_url" class="form-control-file">
		                                </div>
		                            </div>
									<div class="form-group">
										<button type="submit" class="btn btn-secondary">등록</button>
										<button type="reset" class="btn btn-secondary">취소</button>
									</div>
								</fieldset>
							</form>
							<div>
								<a href="./ImgSelect" class="btn btn-primary btn-block"> 이미지 목록 </a>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		
	</section>
	
	<script type="text/javascript">
        function validateForm() {
            var fileInput = document.getElementById('fileName');
            var filePath = fileInput.value;
            var allowedExtensions = /(\.jpg|\.jpeg|\.png|\.gif)$/i;
            if (!allowedExtensions.exec(filePath)) {
                alert('jpg, jpeg, png, gif 형식의 이미지 파일만 업로드할 수 있습니다.');
                // 파일 선택 초기화
                fileInput.value = '';
                // 파일 선택 텍스트 초기화
                document.getElementById('fileLabel').innerHTML = '선택된 이미지가 없습니다.';
                // 이미지 미리보기 초기화
                document.getElementById('previewImage').style.display = 'none';
                document.getElementById('previewImage').src = '';
                return false;
            }
            return true;
        }

        // 파일 선택 시 이미지를 미리 보여주는 함수
        function updateFileLabel(input) {
            var fileLabel = document.getElementById('fileLabel');
            var previewImage = document.getElementById('previewImage');
            if (input.files.length > 0) {
                fileLabel.innerHTML = input.files[0].name;
                // 이미지 파일인 경우에만 이미지를 미리 보여줌
                var reader = new FileReader();
                reader.onload = function (e) {
                    previewImage.src = e.target.result;
                    previewImage.style.display = 'block';
                }
                reader.readAsDataURL(input.files[0]);
            } else {
                fileLabel.innerHTML = '선택된 이미지가 없습니다.';
                previewImage.style.display = 'none';
                previewImage.src = '';
            }
        }
    </script>
	
</body>
</html>
