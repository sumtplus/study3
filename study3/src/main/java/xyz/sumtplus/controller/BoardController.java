package xyz.sumtplus.controller;

import java.io.File;
import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;
import xyz.sumtplus.domain.BoardAttachVO;
import xyz.sumtplus.domain.BoardVO;
import xyz.sumtplus.domain.Criteria;
import xyz.sumtplus.domain.PageDTO;
import xyz.sumtplus.service.BoardService;

/**
 * 게시판 컨트롤러
 *
 * @author 여름
 * @date   2021. 5. 18.
 */

@Controller
@Log4j
@RequestMapping("/board/*")
@AllArgsConstructor
public class BoardController {
	
	private BoardService service;
	//게시판 목록
	@GetMapping("/list")
	public void list(Criteria cri, Model model){
		log.info("게시판 페이지");
		model.addAttribute("list", service.getList(cri));
		model.addAttribute("pageMaker", new PageDTO(service.getTotalCount(cri), cri));
		model.addAttribute("pageMaker1", new PageDTO(service.getNoticeTotalCount(cri), cri));
		model.addAttribute("pageMaker2", new PageDTO(service.getQATotalCount(cri), cri));
		log.warn("category : " + cri.getCategory());
	}
	//게시글 등록페이지요청(get)
	@GetMapping("/register")
	@PreAuthorize("isAuthenticated()")
	public void register(Integer category, Long parentNo, Model model) {
		log.warn(parentNo);
		// parentNo가 null이 아니면 부모글을 모델에 부여
		model.addAttribute("board", parentNo != null ? service.get(parentNo) : null);
		model.addAttribute("category", category);
	}
	//게시글 등록(post)
	@PostMapping("/register")
	public String register(BoardVO board, RedirectAttributes rttr) {
		log.warn("register : " + board);
		service.register(board);
		board.getAttachList().forEach(log::info);
		rttr.addFlashAttribute("result", board.getBno());
		return "redirect:/board/list?category=" +board.getCategory();
		//return null;
	}
	//게시글 상세조회 또는 수정페이지 요청
	@GetMapping({"/get", "/modify"})
	public void get(@RequestParam("bno") Long bno, @ModelAttribute("cri") Criteria cri, Model model) {
		log.info("/get or modify");
		model.addAttribute("board", service.get(bno));
		model.addAttribute("cri",cri);
	}
	//게시글 수정(post)
	@PostMapping("/modify")
	public String modify(BoardVO board, RedirectAttributes rttr, @ModelAttribute("cri") Criteria cri) {
		log.info("modify : " + board);
		board.getAttachList().forEach(log::info);
		
		if(service.modify(board)) {
			rttr.addFlashAttribute("result", "sucess");
		}
		return "redirect:/board/list" + cri.getListLink();
	}
	//게시글 삭제
	@PostMapping("/remove")
	public String remove(@RequestParam("bno") Long bno, @ModelAttribute("cri") Criteria cri, RedirectAttributes rttr) {
		log.info("remove : " + bno);
		List<BoardAttachVO> list = service.getAttachList(bno);
		if(service.remove(bno)) {
			deleteFiles(list);
			rttr.addFlashAttribute("result", "sucess");
		}
		return "redirect:/board/list" + cri.getListLink();
	}
	// bno를 파라미터로 받아서 첨부파일의 목록을 반환
	@ResponseBody
	@GetMapping("getAttachList")
	public List<BoardAttachVO> getAttachList(Long bno){
		log.info("getAttachList..." + bno);
		return service.getAttachList(bno);
	}
	// 첨부파일을 삭제하는 메서드
	private void deleteFiles(List<BoardAttachVO> attachList) {
		log.info("deleteFiles....");
		log.info(attachList);
		// 이미지일 경우 썸네일도 같이 삭제
		attachList.forEach(attach -> {			
			new File(UploadController.UPLOAD_PATH, attach.getDownPath()).delete();
			if(attach.isImage()){
				new File(UploadController.UPLOAD_PATH, attach.getThumbPath()).delete();
			}
		});
	}
	
}