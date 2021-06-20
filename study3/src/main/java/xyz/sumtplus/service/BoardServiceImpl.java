package xyz.sumtplus.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;
import xyz.sumtplus.domain.BoardAttachVO;
import xyz.sumtplus.domain.BoardVO;
import xyz.sumtplus.domain.Criteria;
import xyz.sumtplus.mapper.BoardAttachMapper;
import xyz.sumtplus.mapper.BoardMapper;

/**
 * 게시판 서비스 구현
 *
 * @author 이여름
 * @date   2021. 5. 20.
 */

@Log4j
@Service
@AllArgsConstructor
public class BoardServiceImpl implements BoardService {
	
	private BoardMapper mapper;
	private BoardAttachMapper boardAttachMapper;
	// 게시글 상세조회
	@Override
	@Transactional
	public BoardVO get(Long bno) {
		log.info("get......" + bno);
		mapper.updateHitCount(bno); // 조회수 업데이트 추가
		return mapper.read(bno);
	}
	// 게시글 수정
	@Override
	@Transactional
	public boolean modify(BoardVO board) {
		log.info("modify......." + board);
		boardAttachMapper.deleteAll(board.getBno());
		board.getAttachList().forEach(a -> {
			a.setBno(board.getBno());
			boardAttachMapper.insert(a);
		});
		return mapper.update(board) == 1;
	}
	// 게시글 삭제
	@Override
	@Transactional
	public boolean remove(Long bno) {
		log.info("remove......." + bno);
		boardAttachMapper.deleteAll(bno);
		return mapper.delete(bno) == 1;
	}
	// 게시글 목록
	@Override
	public List<BoardVO> getList(Criteria cri) {
		log.info("답글 포함 List..");
		return mapper.getList2(cri);
	}
	
	// 답글포함 게시글 등록
	@Override
	@Transactional
	public void register(BoardVO board) {
		log.info("register......" + board);
		if(board.getParentNo() == null) {
			board.setParentNo(0L) ;
		}
		mapper.insertSelectKey(board);
		board.getAttachList().forEach(a -> {
			a.setBno(board.getBno());
			boardAttachMapper.insert(a);
		});
	}
	// 첨부파일조회
	@Override
	public List<BoardAttachVO> getAttachList(Long bno) {
		return boardAttachMapper.findBy(bno);
	}
	/**
	 *  총 게시글 수
	 */
	@Override
	public int getTotalCount(Criteria cri) {
		log.warn("getTotal... ");
		return mapper.getTotalCount(cri);
	}

	@Override
	public int getNoticeTotalCount(Criteria cri) {
		log.warn("NoticeTotal... ");
		return mapper.getNoticeTotalCount(cri);
	}

	@Override
	public int getQATotalCount(Criteria cri) {
		log.warn("Q&ATotal.... ");
		return mapper.getQATotalCount(cri);
	}

	/**
	 * 게시글의 조회수를 늘리는 메소드
	 * @author 김보경
	 * @Date 21. 6. 3.
	 */
	@Override
	public int increaseHitCount(Long bno) {
		log.warn("조회한 글 번호:: " + bno);
		return mapper.updateHitCount(bno);
	}

	
	
}
