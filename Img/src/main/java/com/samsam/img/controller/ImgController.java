package com.samsam.img.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import com.samsam.img.dto.ImgDTO;
import com.samsam.img.jpa.entity.Img;
import com.samsam.img.service.ImgService;

import jakarta.inject.Inject;
import lombok.RequiredArgsConstructor;

// 방법1. 
// @RequiredArgsConstructor를 쓰면 멤버변수 자동 초기화
// (final 필드의 파라미터 생성자를 자동으로 생성)
@RequiredArgsConstructor

// 빈으로 컨트롤러 스캔을 구성하고 컨트롤러 구현
@Controller
public class ImgController {
	private static final Logger logger = LogManager.getLogger(ImgController.class);
	
	// 의존 관계를 자동으로 설정
	@Inject
	private final ImgService imgService; // 멤버변수 생성
	
	
	
// 방법2. 
//	@Inject
//    public ImgController(ImgService imgService) {
//        this.imgService = imgService; // 멤버변수 초기화
//    }
	
	// 이미지 목록 조회
	@GetMapping("/ImgSelect")
	public String list(Model model) {
		// 모든 이미지 목록을 모델에 추가
		model.addAttribute("list", imgService.findAllImgs());
		logger.info("list", model);
		return "./img/img_select";
	}
	
	// 특정 이미지의 상세 정보를 조회
	@GetMapping("/ImgSelectDetail")
	public String detail(Model model, @RequestParam("img_number") Integer img_number) {
		// 특정 이미지의 상세 정보 조회
		model.addAttribute("imgDTO", imgService.findImgById(img_number));
		return "./img/img_select_detail_view";
	}
	
	// 이미지를 추가하는 페이지 표시
	@GetMapping("/ImgInsert")
	public String insert() {
		return "./img/img_insert";
	}
	
	// 이미지 추가
		@PostMapping("/ImgInsert")
		public String insert(Model model, @RequestParam("fileName") MultipartFile file, ImgDTO imgDTO) {
			
			String img_path = ("C:\\samsamimg\\");
			
			// ImgDTO를 Img 엔티티로 변환
			Img img = new Img();
			// img.setImg_number(imgDTO.getImg_number()); // 시퀀스 설정을 함
			img.setImg_update(imgDTO.getImg_update());
			img.setImg_upload(imgDTO.getImg_upload());
			//img.setImg_url(imgDTO.getImg_url());
			
			// 이미지 경로에 저장
			try {
	            // 파일을 바이트 배열로 변환하여 저장
	            byte[] bytes = file.getBytes();
	            Path path = Paths.get(img_path + file.getOriginalFilename());
	            Files.write(path, bytes);
				img.setImg_url(file.getOriginalFilename());

	          

	        } catch (IOException e) {
	            e.printStackTrace();
	        }
			
			// 모든 이미지 목록을 모델에 추가
			model.addAttribute("list", imgService.findAllImgs());

			
			// 이미지 정보 저장
			imgService.saveImg(img);
			return "./img/img_insert_view";	
		}
	
	// 이미지 정보를 수정하는 페이지 표시
	@GetMapping("/ImgUpdate")
	public String update(Model model, @RequestParam("img_number") Integer img_number) {
		// 이미지 정보 조회
		Img img = imgService.findImgById(img_number);
		if (img != null) {
			// Img 엔티티를 ImgDTO로 변환하여 모델에 추가
			ImgDTO imgDTO = new ImgDTO();
			imgDTO.setImg_number(img.getImg_number());
			imgDTO.setImg_update(img.getImg_update());
			imgDTO.setImg_upload(img.getImg_upload());
			imgDTO.setImg_url(img.getImg_url());
			
			model.addAttribute("imgDTO", imgDTO);
		}
		return "./img/img_update";
	}
	
	// 이미지 정보 수정
	@PostMapping("/ImgUpdate")
	public String update(ImgDTO imgDTO) {
		Img img = new Img();
		img.setImg_number(imgDTO.getImg_number());
		img.setImg_update(imgDTO.getImg_update());
		img.setImg_upload(imgDTO.getImg_upload());
		img.setImg_url(imgDTO.getImg_url());
		
		// 이미지 정보 저장
		imgService.saveImg(img);
		return "./img/img_update_view";
	}
	
	// 이미지 삭제 페이지 표시
	@GetMapping("/ImgDelete")
	public String delete() {
		return "./img/img_delete";
	}
	
	// 이미지 정보 삭제
	@PostMapping("/ImgDelete")
	public String delete(@RequestParam("img_number") Integer img_number) {
		// 특정 이미지 삭제
		imgService.deleteImgById(img_number);
		return "./img/img_delete_view";
	}
	
	// @RestController 어노테이션에서는 <a> 태그로 매핑명에 접근할 수 없음
	// @Controller 어노테이션에서 설정
	@GetMapping("/ImgSelectView")
	public String selectAllView() {
		return "./img/img_ajax";
	}	
	
	@GetMapping("/ImgView")
	@ResponseBody
	public byte[] imgView(@RequestParam("img_url") String img_url) throws IOException {
		
		String dir = "C:\\samsamimg\\";
		File imgFile = new File(dir+img_url);
		Path imgPath = Paths.get(imgFile.getAbsolutePath());
		
		return Files.readAllBytes(imgPath);
	}
}
