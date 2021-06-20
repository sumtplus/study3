package xyz.sumtplus.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;

import xyz.sumtplus.domain.BoardAttachVO;


/**
 * 첨부파일 매퍼 클래스
 *
 * @author 박재우
 * @date   2021. 5. 29.
 */
public interface BoardAttachMapper {
	void insert(BoardAttachVO vo);
	
	void delete(String uuid);
	
	List<BoardAttachVO> findBy(Long bno);
	// 첨부파일 삭제
	@Delete("DELETE TBL_FILE WHERE BNO = #{bno}")
	void deleteAll(Long bno);
	
	@Delete("DELETE TBL_FILE")
	void deleteAllComplete();
	// 어제 첨부파일 목록
	@Select("SELECT * FROM TBL_FILE WHERE UPLOADPATH = TO_CHAR(SYSDATE -1, 'YYYY/MM/DD')")
	List<BoardAttachVO> getOldFiles();
	
}
